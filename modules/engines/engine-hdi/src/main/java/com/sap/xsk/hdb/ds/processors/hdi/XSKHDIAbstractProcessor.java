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
package com.sap.xsk.hdb.ds.processors.hdi;

import com.sap.xsk.hdb.ds.synchronizer.XSKDataStructuresSynchronizer;
import com.sap.xsk.hdb.ds.util.Message;
import com.sap.xsk.utils.XSKCommonsConstants;
import com.sap.xsk.utils.XSKCommonsUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.eclipse.dirigible.core.scheduler.api.AbstractSynchronizationArtefactType;
import org.eclipse.dirigible.core.scheduler.api.ISynchronizerArtefactType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class XSKHDIAbstractProcessor {

	private static final String ERROR_LOCATION = "-";
	private static final String MESSAGE_SEVERITY_ERROR = "ERROR";
  private static final String MESSAGE_SEVERITY_WARNING = "WARNING";
	private static final Logger LOGGER = LoggerFactory.getLogger(XSKHDIAbstractProcessor.class);
  private static final XSKDataStructuresSynchronizer DATA_STRUCTURES_SYNCHRONIZER = new XSKDataStructuresSynchronizer();

	/**
	 * Execute non-select SQL statement with String parameters
	 * 
	 * @param connection - DB connection
	 * @param sql        - SQL to be executed
	 * @param parameters - SQL parameters
	 * @throws SQLException - in case of failure
	 */
  public void executeUpdate(Connection connection, String sql, String... parameters) throws SQLException {
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			setStatementParams(statement, parameters);
			statement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Failed to execute SQL statement - " + sql, e);
			XSKCommonsUtils.logProcessorErrors(e.getMessage(), XSKCommonsConstants.PROCESSOR_ERROR, ERROR_LOCATION,
					XSKCommonsConstants.HDI_PROCESSOR);
		}
	}

	/**
	 * Execute SQL statement with String parameters that might return a result object
	 * 
	 * @param connection - DB connection
	 * @param sql        - SQL to be executed
	 * @param parameters - SQL parameters
	 * @throws SQLException - in case of failure
	 */
	protected void executeQuery(Connection connection, String sql, String... parameters) throws SQLException {
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			setStatementParams(statement, parameters);
			try (ResultSet resultSet = statement.executeQuery()) {
        parseResultSet(resultSet);
			}
		} catch (SQLException e) {
			LOGGER.error("Failed to execute SQL statement - " + sql, e);
			XSKCommonsUtils.logProcessorErrors(e.getMessage(), XSKCommonsConstants.PROCESSOR_ERROR, ERROR_LOCATION,
					XSKCommonsConstants.HDI_PROCESSOR);
		}
	}

	protected void parseResultSet(ResultSet resultSet) throws SQLException {
    ArrayList<Message> messages = new ArrayList<>();
    while (resultSet.next())
    {
      messages.add(new Message(resultSet));
    }
    for(Message message : messages) {
      if(message.severity.equals(MESSAGE_SEVERITY_ERROR)) {
        LOGGER.error(message.message);
        XSKCommonsUtils.logProcessorErrors(message.message, XSKCommonsConstants.PROCESSOR_ERROR, message.path,
            XSKCommonsConstants.HDI_PROCESSOR);
      }else if(message.severity.equals(MESSAGE_SEVERITY_WARNING)){
        LOGGER.warn(message.message);
      }else {
        LOGGER.info(message.message);
      }
    }
	}

	protected void setStatementParams(PreparedStatement statement, String... parameters) throws SQLException {
		int paramIndex = 0;
		for (String param : parameters) {
			statement.setString(++paramIndex, param);
		}
	}


  public void applyArtefactState(String artefactName, String artefactLocation, AbstractSynchronizationArtefactType type, ISynchronizerArtefactType.ArtefactState state, String message) {
    DATA_STRUCTURES_SYNCHRONIZER.applyArtefactState(artefactName, artefactLocation, type, state, message);
  }

}
