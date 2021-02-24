/*
 * Copyright (c) 2019-2020 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2019-2020 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
/*
 * HANA XS Classic Bridge for DB API
 */
var database = require('db/v4/database');

exports.getConnection = function() {
	var dConnection = database.getConnection();
	return new XscConnection(dConnection);
};

function XscCallableStatement(callableStatement) {
	var isExecuteCalled = false;
	this.close = function() {
		callableStatement.close();
	};

	this.execute = function() {
		isExecuteCalled = true;
		return callableStatement.execute();
	};

	this.getBigInt = function(index) {
		return callableStatement.getBigInt(index);
	};

	this.getBlob = function(index) {
		return callableStatement.getBlob(index);
	};

	this.getBString = function(index) {
		// won't fix
		return callableStatement.getString(index);
	};

	this.getClob = function(index) {
		return callableStatement.getClob(index);
	};

	this.getDate = function(index) {
		return callableStatement.getDate(index);
	};

	this.getDecimal = function(index) {
		return callableStatement.getBigDecimal(index);
	};

	this.getDouble = function(index) {
		return callableStatement.getDouble(index);
	};

	this.getFloat = function(index) {
		return callableStatement.getFloat(index);
	};

	this.getInteger = function(index) {
		return callableStatement.getInteger(index);
	};

	this.getMoreResults = function() {
		return callableStatement.getMoreResults();
	};

	this.getNClob = function(index) {
		return callableStatement.getNClob(index);
	};

	this.getNString = function(index) {
		return callableStatement.getNString(index);
	};

	this.getParameterMetaData = function() {
		return callableStatement.getParameterMetaData();
	};

	this.getReal = function(columnIndex) {
		// when persisting in DB setFloat actually is mapped to REAL SQL value
		return callableStatement.getFloat(columnIndex);
	};

	this.getResultSet = function() {
		if (isExecuteCalled) {
			return new XscResultSet(callableStatement.getResultSet());
		}
		throw new SQLException();
	};

	this.getSeconddate = function(index) {
		// won't implement
	};

	this.getSQLWarning = function() {
		// won't implement
	};

	this.getString = function(index) {
		return callableStatement.getString(index);
	};

	this.getText = function(index) {
		//are text and clob the same ?
		return callableStatement.getClob(index);
	};

	this.getTime = function(index) {
		return callableStatement.getTime(index);
	};

	this.getTimestamp = function(index) {
		return callableStatement.getTimestamp(index);
	};

	this.isClosed = function() {
		return callableStatement.isClosed();
	};

	this.setBigInt = function(index, value) {
		callableStatement.setLong(index, value);
	};

	this.setBlob = function(index, value) {
		callableStatement.setBlob(index, value);
	};

	this.setBString = function(index, value) {
		// won't implement
	};

	this.setClob = function(index, value) {
		callableStatement.setClob(index, value);
	};

	this.setDate = function(index, value, format) {
		callableStatement.setDate(index, value);
	};

	this.setDecimal = function(index, value) {
		callableStatement.setBigDecimal(index, value);
	};

	this.setDouble = function(index, value) {
		callableStatement.setDouble(index, value);
	};

	this.setFloat = function(index, value) {
		callableStatement.setFloat(index, value);
	};

	this.setInteger = function(index, value) {
		callableStatement.setInt(index, value);
	};

	this.setNClob = function(index, value) {
		callableStatement.setNClob(index, value);
	};

	this.setNString = function(index, value) {
		callableStatement.setNString(index, value);
	};

	this.setNull = function(index) {
		var sqlTypeStr = callableStatement.getResultSet().getMetaData()
				.getColumnTypeName(index);
		callableStatement.setNull(index, sqlTypeStr);
	};

	this.setReal = function(index, value) {
		// setFloat sets REAL in DB
		callableStatement.setFloat(index, value);
	};

	this.setSmallInt = function(index, value) {
		// SmallInt = Short
		callableStatement.setShort(index, value);
	};

	this.setString = function(columnIndex, value) {
		callableStatement.setString(columnIndex, value);
	};

	this.setText = function(columnIndex, value) {
		// setText doesn't exist
		callableStatement.setClob(columnIndex, value);
	};

	this.setTime = function(index, value, format) {
		callableStatement.setTime(index, value);
	};

	this.setTimestamp = function(index, value, format) {
		callableStatement.setTimestamp(index, value);
	};

	this.setTinyInt = function(index, value) {
		// TinyInt = Byte
		callableStatement.setByte(index, value);
	};
}

function XscConnection(dConnection) {
	this.close = function() {
		dConnection.close();
	};
	this.commit = function() {
		dConnection.commit();
	};
	this.isClosed = function() {
		return dConnection.isClosed();
	};

	this.prepareCall = function(sql) {
		var callableStatement = dConnection.prepareCall(sql);
		return new XscCallableStatement(callableStatement);
	};

	this.prepareStatement = function(sql) {
		var dPreparedStatement = dConnection.prepareStatement(sql);
		return new XscPreparedStatement(dPreparedStatement);
	};

	this.rollback = function() {
		return dConnection.rollback();
	};

	this.setAutoCommit = function(autoCommit) {
		dConnection.setAutoCommit(autoCommit);
	};

	this.getAutoCommit = function() {
		return dConnection.getAutoCommit();
	};
}

// ParameterMetaData should be provided in Dirigible API
function XscParameterMetaData(dParameterMetaData) {

	this.getParameterCount = function() {
		return dParameterMetaData.getParameterCount();
	};

	this.getParameterMode = function(paramIndex) {
		return dParameterMetaData.getParameterMode(paramIndex);
	};

	// no such method in native ParameterMetaData
	this.getParameterName = function(paramIndex) {

	};

	this.getParameterType = function(paramIndex) {
		return dParameterMetaData.getParameterType(paramIndex);
	};

	this.getParameterTypeName = function(paramIndex) {
		return dParameterMetaData.getParameterTypeName(paramIndex);
	};

	this.getPrecision = function(paramIndex) {
		return dParameterMetaData.getPrecision(paramIndex);
	};

	this.getScale = function(paramIndex) {
		return dParameterMetaData.getScale(paramIndex);
	};

	// no such method in native ParameterMetaData
	this.hasDefault = function(paramIndex) {

	};

	// although I put not null constraints, this method always returns 2 for all
	// columns which is strange
	this.isNullable = function(paramIndex) {
		return dParameterMetaData.isNullable(paramIndex);
	};

	// although I set some integer fields to be unsigned, this method always
	// returns true, which is also strange
	this.isSigned = function(paramIndex) {
		return dParameterMetaData.isSigned(paramIndex);
	};
}

function XscPreparedStatement(dPreparedStatement) {
	this.addBatch = function() {
		dPreparedStatement.addBatch();
	};

	this.close = function() {
		dPreparedStatement.close();
	};

	this.execute = function() {
		return dPreparedStatement.execute();
	};

	this.executeBatch = function() {
		return dPreparedStatement.executeBatch();
	};

	this.executeQuery = function() {
		var dResultSet = dPreparedStatement.executeQuery();
		return new XscResultSet(dResultSet);
	};
	this.executeUpdate = function() {
		return dPreparedStatement.executeUpdate();
	};

	this.getMetaData = function() {
		var dResultSetMetaData = dPreparedStatement.getMetaData();
		return new XscResultSetMetaData(dResultSetMetaData);
	};

	this.getMoreResults = function() {
		return dPreparedStatement.getMoreResults();
	};

	this.getParameterMetaData = function() {
		var dParameterMetaData = dPreparedStatement.getParameterMetaData();
		return new XscParameterMetaData(dParameterMetaData);
	};
	this.getResultSet = function() {
		var dResultSet = dPreparedStatement.executeQuery();
		return new XscResultSet(dResultSet);
	};

	// calling this method returns always null, I need to find a way to simulate
	// conditions when it returns something
	this.getSQLWarning = function() {
		return dPreparedStatement.getWarnings();
	};

	// currently not working and throwing the following error: Cannot find
	// function isCLosed in object prep55985
	this.isClosed = function() {
		return dPreparedStatement.isCLosed();
	};

	// No such method in Dirigible API, neither in JDBC Statements
	this.setBatchSize = function(size) {

	};

	// it sets inaccurate values if the number consists of more than 16 digits
	this.setBigInt = function(index, value) {
		dPreparedStatement.setLong(index, value);
	};

	this.setBlob = function(index, value) {
		dPreparedStatement.setBlob(index, value);
	};

	//No such method in Dirigible API, neither in JDBC Statements
	this.setBString = function(index, value) {

	};

	this.setClob = function(index, value) {
		dPreparedStatement.setClob(index, value);
	};

	this.setDate = function(index, value, format) {
		dPreparedStatement.setDate(index, value);
	};

	// probably because we are passing a double value instead of BigDecimal
	// object
	this.setDecimal = function(index, value) {
		dPreparedStatement.setBigDecimal(index, value);
	};

	this.setDouble = function(index, value) {
		dPreparedStatement.setDouble(index, value);
	};

	this.setFloat = function(index, value) {
		dPreparedStatement.setFloat(index, value);
	};
	this.setInteger = function(index, value) {
		dPreparedStatement.setInt(index, value);
	};

	// probably because we are passing a string value instead of NClob object
	this.setNClob = function(index, value) {
		dPreparedStatement.setNClob(index, value);
	}

	this.setNString = function(index, value) {
		dPreparedStatement.setNString(index, value);
	}

	this.setNull = function(index, sqlType) {
		dPreparedStatement.setNull(index, sqlType);
	};

	this.setReal = function(index, value) {
		dPreparedStatement.setFloat(index, value);
	}

	this.setSmallInt = function(index, value) {
		dPreparedStatement.setShort(index, value);
	}

	this.setString = function(index, value) {
		dPreparedStatement.setString(index, value);
	};

	//same case as setClob()
	this.setText = function(index, value) {
		dPreparedStatement.setClob(index, value);
	}

	this.setTime = function(index, value, format) {
		dPreparedStatement.setTime(index, value);
	};

	this.setTimestamp = function(index, value, format) {
		dPreparedStatement.setTimestamp(index, value);
	};

	this.setTinyInt = function(index, value) {
		dPreparedStatement.setByte(index, value);
	}
}

function XscResultSet(dResultSet) {
	this.close = function() {
		dResultSet.close();
	};

	this.getBigInt = function(columnIndex) {
		return dResultSet.getLong(columnIndex);
	};

	this.getBlob = function(columnIndex) {
		return dResultSet.getBlob(columnIndex);
	};

	//No such method in Dirigible API, neither in JDBC
	this.getBString = function(columnIndex) {

	};

	// it works but it prefixes the string with clobXX: where XX is some random
	// 2 digits number. Should I substring it?
	this.getClob = function(columnIndex) {
		return dResultSet.getClob(columnIndex);
	};

	this.getDate = function(columnIndex) {
		return dResultSet.getDate(columnIndex);
	};

	this.getDecimal = function(columnIndex) {
		return dResultSet.getBigDecimal(columnIndex);
	};

	this.getDouble = function(columnIndex) {
		return dResultSet.getDouble(columnIndex);
	};

	this.getFloat = function(columnIndex) {
		return dResultSet.getFloat(columnIndex);
	};

	this.getInteger = function(columnIndex) {
		return dResultSet.getInt(columnIndex);
	};

	this.getMetaData = function() {
		var dResultSetMetaData = dResultSet.getMetaData();
		return new XscResultSetMetaData(dResultSetMetaData);
	};

	// returns data in the following format: clobXX:
	// STRINGDECODE('\u0431\u044a\u043b\u0433\u0430\u0440\u0441\u043a\u0438\u0438')
	this.getNClob = function(columnIndex) {
		return dResultSet.getNClob(columnIndex);
	};

	this.getNString = function(columnIndex) {
		return dResultSet.getNString(columnIndex);
	};

	this.getReal = function(columnIndex) {
		return dResultSet.getFloat(columnIndex);
	};

	// No such method in Dirigible API, neither in JDBC ResultSet
	this.getSeconddate = function(columnIndex) {

	};

	this.getString = function(columnIndex) {
		return dResultSet.getString(columnIndex);
	};

	// same behaviour as getClob()
	this.getText = function(columnIndex) {
		return dResultSet.getClob(columnIndex);
	};

	this.getTime = function(columnIndex) {
		return dResultSet.getTime(columnIndex);
	};

	this.getTimestamp = function(columnIndex) {
		return dResultSet.getTimestamp(columnIndex);
	};

	this.isClosed = function() {
		return dResultSet.isClosed();
	};

	this.next = function() {
		return dResultSet.next();
	};
}

// ResultSetMetaData should be provided in Dirigible API
function XscResultSetMetaData(dResultSetMetaData) {

	this.getCatalogName = function(columnIndex) {
		return dResultSetMetaData.getCatalogName(columnIndex);
	};

	this.getColumnCount = function() {
		return dResultSetMetaData.getColumnCount();
	};

	this.getColumnDisplaySize = function(columnIndex) {
		return dResultSetMetaData.getColumnDisplaySize(columnIndex);
	};

	this.getColumnLabel = function(columnIndex) {
		return dResultSetMetaData.getColumnLabel(columnIndex);
	};

	this.getColumnName = function(columnIndex) {
		return dResultSetMetaData.getColumnName(columnIndex);
	};

	this.getColumnType = function(columnIndex) {
		return dResultSetMetaData.getColumnType(columnIndex);
	};

	this.getColumnTypeName = function(columnIndex) {
		return dResultSetMetaData.getColumnTypeName(columnIndex);
	};

	this.getPrecision = function(columnIndex) {
		return dResultSetMetaData.getPrecision(columnIndex);
	};

	this.getScale = function(columnIndex) {
		return dResultSetMetaData.getScale(columnIndex);
	};

	this.getTableName = function(columnIndex) {
		return dResultSetMetaData.getTableName(columnIndex);
	};
}

function SQLException() {

}

exports.CallableStatement = XscCallableStatement;
exports.Connection = XscConnection;
exports.ParameterMetaData = XscParameterMetaData;
exports.PreparedStatement = XscPreparedStatement;
exports.ResultSet = XscResultSet;
exports.ResultSetMetaData = XscResultSetMetaData;
exports.SQLException = SQLException;