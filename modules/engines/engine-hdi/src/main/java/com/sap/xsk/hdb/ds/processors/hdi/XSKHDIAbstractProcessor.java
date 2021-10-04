/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2021 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.hdb.ds.processors.hdi;

import com.sap.xsk.utils.XSKCommonsUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class XSKHDIAbstractProcessor {

  private static final Logger logger = LoggerFactory.getLogger(XSKHDIAbstractProcessor.class);

  protected void executeUpdate(Connection connection, String sql) throws SQLException {
    PreparedStatement statement = null;
    try {
      statement = connection.prepareStatement(sql);
      logger.info(sql);
      statement.executeUpdate();
    } catch (SQLException e) {
      logger.error(sql);
      logger.error(e.getMessage(), e);
      XSKCommonsUtils.logProcessorErrors(e.getMessage(), "PROCESSOR", "-", "HDI");
    } finally {
      if (statement != null) {
        statement.close();
      }
    }
  }

  protected void executeQuery(Connection connection, String sql) throws SQLException {
    PreparedStatement statement = null;
    try {
      statement = connection.prepareStatement(sql);
      logger.info(sql);
      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()) {
        StringBuffer buff = new StringBuffer();
        boolean error = false;
        for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
          String text = resultSet.getString(i);
          String column = resultSet.getMetaData().getColumnName(i);
          if ("ERROR".equals(text)) {
            error = true;
          }
          buff.append(column)
              .append(": ")
              .append(text)
              .append(" ");
        }
        if (error) {
          logger.error(buff.toString());
        } else {
          logger.info(buff.toString());
        }
      }
    } catch (SQLException e) {
      logger.error(sql);
      logger.error(e.getMessage(), e);
    } finally {
      if (statement != null) {
        statement.close();
      }
    }
  }

}
