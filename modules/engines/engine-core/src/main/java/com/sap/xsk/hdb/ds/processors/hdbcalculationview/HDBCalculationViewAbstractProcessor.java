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
package com.sap.xsk.hdb.ds.processors.hdbcalculationview;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.eclipse.dirigible.repository.api.RepositoryPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.xsk.hdb.ds.model.hdbcalculationview.XSKDataStructureHDBCalculationViewModel;

public abstract class HDBCalculationViewAbstractProcessor {
	
	private static final Logger logger = LoggerFactory.getLogger(HDBCalculationViewAbstractProcessor.class);

	private String user = "DBADMIN";
	private String group = "XSK_G";
	private String container = "XSK_C";
	
	public String getUser() {
		return user;
	}
	
	public String getGroup() {
		return group;
	}
	
	public String getContainer() {
		return container;
	}

    protected void grantPrivilegesToContainerSchema(Connection connection, String container, String user) throws SQLException {
    	executeUpdate(connection, "CREATE LOCAL TEMPORARY COLUMN TABLE #PRIVILEGES LIKE _SYS_DI.TT_SCHEMA_PRIVILEGES;");
    	executeUpdate(connection, "INSERT INTO #PRIVILEGES(PRIVILEGE_NAME, PRINCIPAL_SCHEMA_NAME, PRINCIPAL_NAME) VALUES ('SELECT', '', '" + user + "');");
    	executeQuery(connection, "CALL " + container + "#DI.GRANT_CONTAINER_SCHEMA_PRIVILEGES(#PRIVILEGES, _SYS_DI.T_NO_PARAMETERS, ?, ?, ?);");
    	executeUpdate(connection, "DROP TABLE #PRIVILEGES;");
	}

    protected void configureLibraries(Connection connection, String container) throws SQLException {
    	executeQuery(connection, "CALL " + container + "#DI.CONFIGURE_LIBRARIES(_SYS_DI.T_DEFAULT_LIBRARIES, _SYS_DI.T_NO_PARAMETERS, ?, ?, ?);");
	}

    protected void grantPrivilegesToContainerAPI(Connection connection, String group, String container,
			String user) throws SQLException {
    	executeUpdate(connection, "CREATE LOCAL TEMPORARY COLUMN TABLE #PRIVILEGES LIKE _SYS_DI.TT_API_PRIVILEGES;");
    	executeUpdate(connection, "INSERT INTO #PRIVILEGES (PRINCIPAL_NAME, PRIVILEGE_NAME, OBJECT_NAME) SELECT '" + user + "', PRIVILEGE_NAME, OBJECT_NAME FROM _SYS_DI.T_DEFAULT_CONTAINER_ADMIN_PRIVILEGES; ");
    	executeQuery(connection, "CALL _SYS_DI#" + group + ".GRANT_CONTAINER_API_PRIVILEGES('" + container + "', #PRIVILEGES, _SYS_DI.T_NO_PARAMETERS, ?, ?, ?);");
    	executeUpdate(connection, "DROP TABLE #PRIVILEGES;");
	}
    
    protected void writeContentInContainer(Connection connection, String container, Set<String> folders, List<XSKDataStructureHDBCalculationViewModel> hdbCalculationViews) throws SQLException {
    	executeUpdate(connection, "CREATE LOCAL TEMPORARY COLUMN TABLE #PATHS LIKE _SYS_DI.TT_FILESFOLDERS_CONTENT;");
    	executeUpdate(connection, "INSERT INTO #PATHS (PATH, CONTENT) VALUES ('.hdiconfig', '{ \"plugin_version\" : \"12.1.0\", \"file_suffixes\" : { \"hdbcalculationview\" : { \"plugin_name\" : \"com.sap.hana.di.calculationview\" }}}')");
    	for (String path : folders) {
    		executeUpdate(connection, "INSERT INTO #PATHS (PATH, CONTENT) VALUES ('" + path + "', NULL);");
    	}
    	for (XSKDataStructureHDBCalculationViewModel next : hdbCalculationViews) {
    		executeUpdate(connection, "INSERT INTO #PATHS (PATH, CONTENT) VALUES ('" + next.getLocation().substring(1) + "', '" + next.getContent() + "');");
    	}
    	executeQuery(connection, "CALL " + container + "#DI.WRITE(#PATHS, _SYS_DI.T_NO_PARAMETERS, ?, ?, ?);");
    	executeUpdate(connection, "DROP TABLE #PATHS;");
	}

    protected void createContainer(Connection connection, String group, String container) throws SQLException {
    	executeQuery(connection, "CALL _SYS_DI#" + group + ".CREATE_CONTAINER('" + container + "', _SYS_DI.T_NO_PARAMETERS, ?, ?, ?);");
	}

    protected void grantPrivilegesToContainerGroup(Connection connection, String group, String user) throws SQLException {
    	executeUpdate(connection, "CREATE LOCAL TEMPORARY COLUMN TABLE #PRIVILEGES LIKE _SYS_DI.TT_API_PRIVILEGES;");
    	executeUpdate(connection, "INSERT INTO #PRIVILEGES (PRINCIPAL_NAME, PRIVILEGE_NAME, OBJECT_NAME) SELECT '" + user + "', PRIVILEGE_NAME, OBJECT_NAME FROM _SYS_DI.T_DEFAULT_CONTAINER_GROUP_ADMIN_PRIVILEGES;");
    	executeQuery(connection, "CALL _SYS_DI.GRANT_CONTAINER_GROUP_API_PRIVILEGES('" + group + "', #PRIVILEGES, _SYS_DI.T_NO_PARAMETERS, ?, ?, ?);");
    	executeUpdate(connection, "DROP TABLE #PRIVILEGES;");
	}

    protected void createContainerGroup(Connection connection, String group) throws SQLException {
    	executeQuery(connection, "CALL _SYS_DI.CREATE_CONTAINER_GROUP('" + group + "', _SYS_DI.T_NO_PARAMETERS, ?, ?, ?);");
	}

	// Grant Privileges to Container Group API
    protected void grantPrivilegesToContainerGroupAPI(Connection connection, String user) throws SQLException {
    	executeUpdate(connection, "CREATE LOCAL TEMPORARY TABLE #PRIVILEGES LIKE _SYS_DI.TT_API_PRIVILEGES;");
    	executeUpdate(connection, "INSERT INTO #PRIVILEGES (PRINCIPAL_NAME, PRIVILEGE_NAME, OBJECT_NAME) SELECT '" + user + "', PRIVILEGE_NAME, OBJECT_NAME FROM _SYS_DI.T_DEFAULT_DI_ADMIN_PRIVILEGES;");
    	executeQuery(connection, "CALL _SYS_DI.GRANT_CONTAINER_GROUP_API_PRIVILEGES('_SYS_DI', #PRIVILEGES, _SYS_DI.T_NO_PARAMETERS, ?, ?, ?);");
    	executeUpdate(connection, "DROP TABLE #PRIVILEGES;");
	}
    
    protected static Set<String> enumerateFolders(List<XSKDataStructureHDBCalculationViewModel> hdbCalculationViews) {
		Set<String> folders = new TreeSet<>();
        for (XSKDataStructureHDBCalculationViewModel calculationview : hdbCalculationViews) {
        	RepositoryPath path = new RepositoryPath(calculationview.getLocation());
        	String current = "";
        	String[] segments = path.getSegments();
			for (int i=0; i<segments.length-1; i++) {
        		current += segments[i] + "/";
        		if (!folders.contains(current)) {
        			folders.add(current);
        		}
        	}
        }
		return folders;
	}

    protected void executeUpdate(Connection connection, String sql) throws SQLException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            logger.info(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(sql);
            logger.error(e.getMessage(), e);
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
				for (int i=1; i<=resultSet.getMetaData().getColumnCount();i++) {
				    String text = resultSet.getString(i);
				    String column = resultSet.getMetaData().getColumnName(i);
				    if ("ERROR".equals(text)) {
				    	logger.error(column + ": " + text);
				    } else {
				    	logger.info(column + ": " + text);
				    }
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
