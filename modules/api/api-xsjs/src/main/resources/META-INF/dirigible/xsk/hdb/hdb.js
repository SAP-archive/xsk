/*
 * Copyright (c) 2022 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2022 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
/*
 * HANA XS Classic Bridge for HDB API
 */
var database = require('db/v4/database');
const PROCEDURE_IN_PARAMETER = 1;
const PROCEDURE_IN_OUT_PARAMETER = 2;
const PROCEDURE_OUT_PARAMETER = 4;
const SQL_TABLE_TYPE = 0;
exports.getConnection = function () {
	var dConnection = database.getConnection();
	return new XscConnection(dConnection);
}
exports.ProcedureResult = function () {
	this.$resultSets = [];
}
exports.ResultSet = XscResultSet;

function XscConnection(dConnection) {
	this.close = function () {
		dConnection.close();
	};
	this.commit = function () {
		dConnection.commit();
	};
	this.executeQuery = function (query) {
		var dPreparedStatement = dConnection.prepareStatement(query);
		var args = Array.prototype.slice.call(arguments, 1);
		setStatementParams(dPreparedStatement, args);
		var dResultSet = dPreparedStatement.executeQuery();
		return new XscResultSet(dResultSet);
	}
	this.executeUpdate = function (statement) {
		var dPreparedStatement = dConnection.prepareStatement(statement);
		var args = Array.prototype.slice.call(arguments, 1);
		if (args.length === 1 && Array.isArray(args[0]) && Array.isArray(args[0][0])) {
			return executeBatchUpdate(dConnection, dPreparedStatement, args[0]);
		} else {
			setStatementParams(dPreparedStatement, args);
			return dPreparedStatement.executeUpdate();
		}
	};
	// Returns always null. I need to think of conditions where it returns an object
	this.getLastWarning = function () {
		return dConnection.native.getWarnings();
	}
	this.loadProcedure = function (schema, procedure) {
		function procedureCall() {
			// Make an array of the procedure call arguments
			let procedureCallArgs = Array.prototype.slice.call(arguments);
			let procedureResult = new $.hdb.ProcedureResult;
			let dConnection = null;
			let procedureCallStatement = null;
			let procedureParameters = [];
			try {
				dConnection = database.getConnection();
				// Get the procedure parameters
				const procedureParametersResultSet = dConnection.native.getMetaData().getProcedureColumns(null, null, procedure, "%");
				while (procedureParametersResultSet.next()) {
					const parameterName = procedureParametersResultSet.getString('COLUMN_NAME');
					const parameterType = procedureParametersResultSet.getShort('COLUMN_TYPE');
					const parameterTypeName = procedureParametersResultSet.getString('TYPE_NAME');
					const parameterDataType = procedureParametersResultSet.getInt('DATA_TYPE');
					procedureParameters.push({
						parameterName: parameterName,
						parameterType: parameterType,
						parameterTypeName: parameterTypeName,
						parameterDataType: parameterDataType,
						parameterValue: null
					})
				}
				// Process the procedure arguments and set them to the in parameters
				if (procedureCallArgs.length === 1 && typeof procedureCallArgs[0] === 'object' && !Array.isArray(procedureCallArgs[0]) && procedureCallArgs[0] !== null) {
					procedureCallArgs = procedureCallArgs[0];
					for (let procedureCallArg in procedureCallArgs) {
						procedureParameters.forEach(procedureParameter => {
							if (procedureParameter.parameterName === procedureCallArg.toUpperCase()) {
								procedureParameter.parameterValue = procedureCallArgs[procedureCallArg];
							}
						})
					}
				} else {
					for (let i = 0, argIndex = 0; i < procedureParameters.length; i++) {
						if (procedureParameters[i].parameterType === PROCEDURE_IN_PARAMETER || procedureParameters[i].parameterType === PROCEDURE_IN_OUT_PARAMETER) {
							procedureParameters[i].parameterValue = procedureCallArgs[argIndex];
							argIndex++;
						}
					}
				}
				// Build procedure call sql
				let procedureCallSql = `CALL "${schema}"."${procedure}" (` + procedureParameters.map(() => "?").toString() + `)`;
				// Prepare call statement, set in params and register out params
				procedureCallStatement = dConnection.prepareCall(procedureCallSql);
				setProcedureParams(procedureCallStatement, procedureParameters);
				// Execute the procedure call and get the resultsets
				let hasResults = procedureCallStatement.execute();
				let resultSets = [];
				do {
					if (hasResults) {
						let resultSet = procedureCallStatement.getResultSet();
						resultSets.push(new $.hdb.ResultSet(resultSet));
						resultSet.close();
					}
					hasResults = procedureCallStatement.getMoreResults();
				} while (hasResults);
				procedureResult.$resultSets = resultSets;
				// Get the out paramter values
				getOUTParamsValues(procedureCallStatement, procedureParameters, procedureResult, resultSets);
				return procedureResult;
			} catch (e) {
				throw new Error(e);
			} finally {
				if (procedureCallStatement !== null) {
					procedureCallStatement.close();
				}
				if (dConnection !== null) {
					dConnection.close();
				}
			}
		}

		return procedureCall;
	}
	this.rollback = function () {
		return dConnection.rollback();
	};
	this.setAutoCommit = function (autoCommit) {
		dConnection.setAutoCommit(autoCommit);
	};
	// this method is not part of XS API, I exposed it only for testing purposes
	this.getAutoCommit = function () {
		return dConnection.getAutoCommit();
	};
	// not part of the API, just for testing purposes
	this.isClosed = function () {
		return dConnection.isClosed();
	}
}

function XscResultSet(dResultSet) {
	this.length = 0;
	this.metadata = new XscResultSetMetaData(dResultSet.getMetaData());
	syncResultSet.call(this);
	this.getIterator = function () {
		return new XscResultSetIterator(this);
	};

	function syncResultSet() {
		var count = 0;
		while (dResultSet.next()) {
			this[count] = getResultSetRow(dResultSet, this.metadata);
			count++;
		}
		this.length = count;
	}

	function getResultSetRow(dResultSet, metadata) {
		let objToReturn = {};
		let len = metadata.columns.length;
		for (var i = 0; i < len; i++) {
			let dataType = metadata.columns[i].typeName;
			let value = getResultSetValueByDataTypeAndRowNumber(dResultSet, dataType, i + 1);
			//          response.println("type: " + dataType + " / toString: " + value.toString());
			let propertyName = metadata.columns[i].name;
			objToReturn[propertyName] = value;
		}
		return objToReturn;
	}
}

function XscResultSetIterator(myResultSet) {
	var currentSetRow = -1;
	this.next = function () {
		++currentSetRow;
		return currentSetRow < myResultSet.length;
	};
	this.value = function () {
		if (currentSetRow < 0 || myResultSet.length === 0) {
			throw new Error("Error: ResultSet is empty or you haven't called next() before calling value()");
		}
		return myResultSet[currentSetRow];
	};
}

function XscResultSetMetaData(dResultSetMetaData) {
	this.columns = getColumnMetadataArray(dResultSetMetaData);
}

// To Do
function SQLException() {
}

// UTIL FUNCTIONS
function setProcedureParams(dPreparedCallableStatement, procedureParameters) {
	for (var i = 0, paramIndex = 1; i < procedureParameters.length; i++, paramIndex++) {
		const procedureParameter = procedureParameters[i];
		if (procedureParameter.parameterType === PROCEDURE_IN_PARAMETER || procedureParameter.parameterType === PROCEDURE_IN_OUT_PARAMETER) {
			setParamByType(dPreparedCallableStatement, procedureParameter.parameterTypeName, procedureParameter.parameterValue, paramIndex);
		}
	}
}

function setStatementParams(dPreparedStatement, args) {
	var parameterMetaData = dPreparedStatement.native.getParameterMetaData();
	var paramsCount = parameterMetaData.getParameterCount();
	if (paramsCount !== args.length) {
		throw new Error('Invalid arguments count!');
	}
	for (var i = 0, paramIndex = 1; i < paramsCount; i++, paramIndex++) {
		var paramType = parameterMetaData.getParameterTypeName(paramIndex);
		var paramValue = args[i];
		setParamByType(dPreparedStatement, paramType, paramValue, paramIndex);
	}
}

function setParamByType(dPreparedStatement, paramType, paramValue, paramIndex) {
	switch (paramType) {
		case 'TINYINT':
			dPreparedStatement.setByte(paramIndex, paramValue);
			break;
		case 'SMALLINT':
			dPreparedStatement.setShort(paramIndex, paramValue);
			break;
		case 'INTEGER':
			dPreparedStatement.setInt(paramIndex, paramValue);
			break;
		case 'BIGINT':
			dPreparedStatement.setLong(paramIndex, paramValue);
			break;
		case 'SMALLDECIMAL':
		case 'DECIMAL':
			//TODO setBigDecimal doesn't exist
			dPreparedStatement.setDouble(paramIndex, paramValue);
			break;
		case 'REAL':
		case 'FLOAT':
			dPreparedStatement.setFloat(paramIndex, paramValue);
			break;
		case 'DOUBLE':
			dPreparedStatement.setDouble(paramIndex, paramValue);
			break;
		case 'VARCHAR':
		case 'ALPHANUM':
			dPreparedStatement.setString(paramIndex, paramValue);
			break;
		case 'NVARCHAR':
		case 'NCHAR':
		case 'SHORTTEXT':
			dPreparedStatement.native.setNString(paramIndex, paramValue);
			break;
		case 'VARBINARY':
			dPreparedStatement.setBytes(paramIndex, paramValue);
			break;
		case 'BOOLEAN':
			dPreparedStatement.setBoolean(paramIndex, paramValue);
			break;
		case 'DATE':
			dPreparedStatement.setDate(paramIndex, paramValue);
			break;
		case 'TIME':
			dPreparedStatement.setTime(paramIndex, paramValue);
			break;
		case 'TIMESTAMP':
			dPreparedStatement.setTimestamp(paramIndex, paramValue);
			break;
		case 'SECONDDATE':
			// TODO
			break;
		case 'BLOB':
			dPreparedStatement.setBlob(paramIndex, paramValue);
			break;
		case 'TEXT':
		case 'CLOB':
			dPreparedStatement.setClob(paramIndex, paramValue);
			break;
		case 'ARRAY':
			// TODO
			break;
		case 'NCLOB':
			dPreparedStatement.setNClob(paramIndex, paramValue);
			break;
		case 'ST_GEOMETRY':
			// TODO
			break;
		case 'ST_POINT':
			// TODO
			break;
	}
}

function executeBatchUpdate(dConnection, dPreparedStatement, batchArguments) {
	try {
		var resultsArray = [];
		dConnection.setAutoCommit(false);
		for (var i = 0; i < batchArguments.length; i++) {
			setStatementParams(dPreparedStatement, batchArguments[i]);
			var currentResult = dPreparedStatement.executeUpdate();
			resultsArray.push(currentResult);
		}
		dConnection.commit();
		return resultsArray;
	} catch (e) {
		dConnection.rollback();
		throw new Error("Batch Update Failed: " + e.message);
	}
}

function getColumnMetadataArray(dResultSetMetaData) {
	var columnsCount = dResultSetMetaData.getColumnCount();
	var columnMetadataArray = [];
	for (var metaDataColumnIndex = 1; metaDataColumnIndex <= columnsCount; metaDataColumnIndex++) {
		columnMetadataArray.push({
			catalogName: dResultSetMetaData.getCatalogName(metaDataColumnIndex),
			displaySize: dResultSetMetaData.getColumnDisplaySize(metaDataColumnIndex),
			isNullable: dResultSetMetaData.isNullable(metaDataColumnIndex),
			label: dResultSetMetaData.getColumnLabel(metaDataColumnIndex),
			name: dResultSetMetaData.getColumnName(metaDataColumnIndex),
			precision: dResultSetMetaData.getPrecision(metaDataColumnIndex),
			scale: dResultSetMetaData.getScale(metaDataColumnIndex),
			tableName: dResultSetMetaData.getTableName(metaDataColumnIndex),
			type: dResultSetMetaData.getColumnType(metaDataColumnIndex),
			typeName: dResultSetMetaData.getColumnTypeName(metaDataColumnIndex)
		});
	}
	return columnMetadataArray;
}

function getResultSetValueByDataTypeAndRowNumber(dResultSet, dataType, colNumber) {
	switch (dataType) {
		case 'TINYINT':
			return dResultSet.getByte(colNumber);
		case 'SMALLINT':
			return dResultSet.getShort(colNumber);
		case 'INTEGER':
			return dResultSet.getInt(colNumber);
		case 'BIGINT':
			return dResultSet.getLong(colNumber);
		case 'SMALLDECIMAL':
		case 'DECIMAL':
			//TODO: fix when getBigDecimal is fixed
			return dResultSet.getBigDecimal(colNumber);
		case 'REAL':
		case 'FLOAT':
			return dResultSet.getFloat(colNumber);
		case 'DOUBLE':
			return dResultSet.getDouble(colNumber);
		case 'VARCHAR':
		case 'ALPHANUM':
			return dResultSet.getString(colNumber);
		case 'NVARCHAR':
		case 'SHORTTEXT':
			return dResultSet.native.getNString(colNumber);
		case 'VARBINARY':
			return dResultSet.getBytes(colNumber);
		case 'BOOLEAN':
			return dResultSet.getBoolean(colNumber);
		case 'DATE':
			return dResultSet.getDate(colNumber);
		case 'TIME':
			return dResultSet.getTime(colNumber);
		case 'TIMESTAMP':
			return dResultSet.getTimestamp(colNumber);
		case 'SECONDDATE':
			// TODO
			break;
		case 'BLOB':
			return dResultSet.getBlob(colNumber);
		case 'TEXT':
		case 'CLOB':
			//TODO needs work
			return dResultSet.getClob(colNumber);
		case 'ARRAY':
			//TODO to do
			break;
		case 'NCLOB':
			return dResultSet.getNClob(colNumber);
		case 'ST_GEOMETRY':
			// TODOto do
			break;
		case 'ST_POINT':
			//TODO to do
			break;
	}
}

function getOUTParamsValues(procedureCallStatement, procedureParameters, procedureResult, resultSets) {
	for (let i = 0, rsIndex = 0; i < procedureParameters.length; i++) {
		const procedureParameter = procedureParameters[i];
		if (procedureParameter.parameterType === PROCEDURE_IN_OUT_PARAMETER || procedureParameter.parameterType === PROCEDURE_OUT_PARAMETER) {
			switch (procedureParameter.parameterDataType) {
				case java.sql.Types.TINYINT:
					procedureResult[procedureParameter.parameterName] = procedureCallStatement.getByte(procedureParameter.parameterName);
					break;
				case java.sql.Types.VARCHAR:
					procedureResult[procedureParameter.parameterName] = procedureCallStatement.getString(procedureParameter.parameterName);
					break;
				case java.sql.Types.NVARCHAR:
					procedureResult[procedureParameter.parameterName] = procedureCallStatement.getNString(procedureParameter.parameterName);
					break;
				case java.sql.Types.ARRAY:
					procedureResult[procedureParameter.parameterName] = procedureCallStatement.getArray(procedureParameter.parameterName);
					break;
				case java.sql.Types.BIGINT:
					procedureResult[procedureParameter.parameterName] = procedureCallStatement.getInt(procedureParameter.parameterName);
					break;
				case java.sql.Types.BOOLEAN:
					procedureResult[procedureParameter.parameterName] = procedureCallStatement.getBoolean(procedureParameter.parameterName);
					break;
				case java.sql.Types.INTEGER:
					procedureResult[procedureParameter.parameterName] = procedureCallStatement.getInt(procedureParameter.parameterName);
					break;
				case java.sql.Types.TIME:
					procedureResult[procedureParameter.parameterName] = procedureCallStatement.getTime(procedureParameter.parameterName);
					break;
				case java.sql.Types.TIMESTAMP:
					procedureResult[procedureParameter.parameterName] = procedureCallStatement.getTimestamp(procedureParameter.parameterName);
					break;
				case java.sql.Types.BLOB:
					procedureResult[procedureParameter.parameterName] = procedureCallStatement.getBlob(procedureParameter.parameterName);
					break;
				case java.sql.Types.DATE:
					procedureResult[procedureParameter.parameterName] = procedureCallStatement.getDate(procedureParameter.parameterName);
					break;
				case java.sql.Types.DOUBLE:
					procedureResult[procedureParameter.parameterName] = procedureCallStatement.getDouble(procedureParameter.parameterName);
					break;
				case java.sql.Types.FLOAT:
					procedureResult[procedureParameter.parameterName] = procedureCallStatement.getFloat(procedureParameter.parameterName);
					break;
				case java.sql.Types.BIGINT:
					procedureResult[procedureParameter.parameterName] = procedureCallStatement.getInt(procedureParameter.parameterName);
					break;
				case java.sql.Types.DECIMAL:
					procedureResult[procedureParameter.parameterName] = procedureCallStatement.getBigDecimal(procedureParameter.parameterName);
					break;
				case java.sql.Types.SQLXML:
					procedureResult[procedureParameter.parameterName] = procedureCallStatement.getSQLXML(procedureParameter.parameterName);
					break;
				case java.sql.Types.URL:
					procedureResult[procedureParameter.parameterName] = procedureCallStatement.getUrl(procedureParameter.parameterName);
					break;
				case java.sql.Types.NUMERIC:
					procedureResult[procedureParameter.parameterName] = procedureCallStatement.getInt(procedureParameter.parameterName);
					break;
				case java.sql.Types.NCLOB:
					procedureResult[procedureParameter.parameterName] = procedureCallStatement.getNClob(procedureParameter.parameterName);
					break;
				case java.sql.Types.CLOB:
					procedureResult[procedureParameter.parameterName] = procedureCallStatement.getClob(procedureParameter.parameterName);
					break;
				case java.sql.Types.NCHAR:
					procedureResult[procedureParameter.parameterName] = procedureCallStatement.getNString(procedureParameter.parameterName);
					break;
				case java.sql.Types.REF:
					procedureResult[procedureParameter.parameterName] = procedureCallStatement.getRef(procedureParameter.parameterName);
					break;
				case java.sql.Types.JAVA_OBJECT:
					procedureResult[procedureParameter.parameterName] = procedureCallStatement.getObject(procedureParameter.parameterName);
					break;
				case java.sql.Types.LONG:
					procedureResult[procedureParameter.parameterName] = procedureCallStatement.getLong(procedureParameter.parameterName);
					break;
				case java.sql.Types.ROWID:
					procedureResult[procedureParameter.parameterName] = procedureCallStatement.getRowId(procedureParameter.parameterName);
					break;
				case SQL_TABLE_TYPE:
					const tableResultSet = resultSets[rsIndex];
					let tableObj = {};
					let length = 0;
					for (let i = 0; i < tableResultSet.length; i++) {
						let rowObj = tableResultSet[i];
						tableObj[i] = rowObj;
						length++;
					}
					tableObj.length = length;
					procedureResult[procedureParameter.parameterName] = tableObj;
					rsIndex++;
					break;
			}
		}
	}
}
