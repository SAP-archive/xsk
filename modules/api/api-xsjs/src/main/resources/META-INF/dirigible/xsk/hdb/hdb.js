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
var PROCEDURE_UTILS = require('xsk/hdb/procedureUtils');
var HDB_UTILS = require('xsk/hdb/hdbUtils');
const TYPE_CONVERTER = require('xsk/db/sqlToXSCColumnTypeConverter');

exports.getConnection = function () {
	var dConnection = database.getConnection();
	return new XscConnection(dConnection);
};

exports.ProcedureResult = function () {
	this.$resultSets = [];
};

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
	};

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
	};

	this.loadProcedure = function (schema, procedure) {
		function procedureCall() {
			// Make an array of the procedure call arguments
			let procedureCallArgs = Array.prototype.slice.call(arguments);
			let procedureResult = new $.hdb.ProcedureResult;
			let dConnection = null;
			let procedureCallStatement = null;
			let temporaryTables = [];
			try {
				dConnection = database.getConnection();
				let procedureParameters = PROCEDURE_UTILS.getProcedureParameters(dConnection, procedure);
				// Process the procedure arguments and set them to the in parameters
				setProcedureParameterValues(dConnection, procedureParameters, procedureCallArgs);

				let sql = `CALL "${schema}"."${procedure}" (${procedureParameters.map(e => {
					if (e.isTableType && PROCEDURE_UTILS.isProcedureInParamType(e.parameterType)) {
						PROCEDURE_UTILS.createTemporaryTable(dConnection, e.temporaryTableName, e.temporaryTableType);
						for (let i = 0; i < e.parameterValue.length; i++) {
							PROCEDURE_UTILS.insertTemporaryTableData(dConnection, e.temporaryTableName, e.parameterValue[i]);
						}
						temporaryTables.push(e.temporaryTableName);
						return e.temporaryTableName;
					}
					return "?";
				}).join(",")})`;
				procedureCallStatement = dConnection.prepareCall(sql);
				PROCEDURE_UTILS.setProcedureParameters(procedureCallStatement, procedureParameters);
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
				for (let i = 0, rsIndex = 0; i < procedureParameters.length; i++) {
					const procedureParameter = procedureParameters[i];
					if (PROCEDURE_UTILS.isProcedureOutParamType(procedureParameter.parameterType)) {
						let value = PROCEDURE_UTILS.getProcedureOutParamValue(procedureCallStatement, procedureParameter, resultSets[rsIndex]);
						procedureResult[procedureParameter.parameterName] = value;
						if (procedureParameter.parameterDataType === PROCEDURE_UTILS.SQL_TABLE_TYPE) {
							rsIndex++;
						}
					}
				}
				return procedureResult;
			} catch (e) {
				throw new Error(e);
			} finally {
				for (let i = 0; i < temporaryTables.length; i++) {
					try {
						PROCEDURE_UTILS.dropTemporaryTable(dConnection, temporaryTables[i]);
					} catch (e) {
						console.error(`Error occurred when droping temporary table ${e}`);
					}

				}

				if (procedureCallStatement !== null && procedureCallStatement !== undefined) {
					procedureCallStatement.close();
				}
				if (dConnection !== null && dConnection !== undefined) {
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
			let value = HDB_UTILS.getResultSetValueByDataTypeAndRowNumber(dResultSet, dataType, i + 1);
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
function setStatementParams(dPreparedStatement, args) {
	var parameterMetaData = dPreparedStatement.native.getParameterMetaData();
	var paramsCount = parameterMetaData.getParameterCount();
	if (paramsCount !== args.length) {
		throw new Error('Invalid arguments count!');
	}
	for (var i = 0, paramIndex = 1; i < paramsCount; i++, paramIndex++) {
		var paramType = parameterMetaData.getParameterTypeName(paramIndex);
		var paramValue = args[i];
		HDB_UTILS.setParamByType(dPreparedStatement, paramType, paramValue, paramIndex);
	}
}

function setProcedureParameterValues(connection, procedureParameters, procedureCallArgs) {
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
			if (PROCEDURE_UTILS.isProcedureInParamType(procedureParameters[i].parameterType)) {
				procedureParameters[i].parameterValue = procedureCallArgs[argIndex];
				argIndex++;
			}
		}
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
			type: TYPE_CONVERTER.convert(dResultSetMetaData.getColumnType(metaDataColumnIndex)),
			typeName: dResultSetMetaData.getColumnTypeName(metaDataColumnIndex)
		});
	}
	return columnMetadataArray;
}