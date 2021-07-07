/*
 * Copyright (c) 2019-2021 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2019-2021 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.name.Names;
import com.sap.xsk.hdb.ds.itest.module.XSKHDBTestModule;
import com.sap.xsk.hdb.ds.test.itest.model.JDBCModel;
import com.sap.xsk.xsodata.ds.api.IXSKODataArtifactDao;
import com.sap.xsk.xsodata.ds.api.XSKODataException;
import com.sap.xsk.xsodata.ds.model.XSKODataModel;
import com.sap.xsk.xsodata.ds.service.XSKODataParser;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.testcontainers.containers.PostgreSQLContainer;

import javax.sql.DataSource;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class XSKODataArtifactDaoPostgreSQLITTest {

    private static Connection connection;
    private static IXSKODataArtifactDao dao;

    @BeforeClass
    public static void setUp() throws SQLException {
        PostgreSQLContainer jdbcContainer = new PostgreSQLContainer<>("postgres:alpine");
        jdbcContainer.start();
        JDBCModel model = new JDBCModel(jdbcContainer.getDriverClassName(), jdbcContainer.getJdbcUrl(), jdbcContainer.getUsername(),
                jdbcContainer.getPassword());
        Injector injector = Guice.createInjector(new XSKHDBTestModule(model));
        connection = injector.getInstance(DataSource.class).getConnection();
        dao = injector.getInstance(Key.get(IXSKODataArtifactDao.class, Names.named("xskXSODataArtifactDao")));
    }

    @BeforeAll
    public static void initializeDBStructure() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("CREATE TABLE public.XSK_ODATA (OD_LOCATION VARCHAR(255) NOT NULL , OD_NAME VARCHAR(255) NOT NULL , OD_HASH VARCHAR(32) NOT NULL , OD_CREATED_BY VARCHAR(32) NOT NULL , OD_CREATED_AT timestamp NOT NULL , PRIMARY KEY (OD_LOCATION))");
    }

    public static void clearTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("DELETE FROM public.XSK_ODATA");
    }

    public static XSKODataModel parseXSKODataModel() throws IOException, SQLException {
        XSKODataParser parser = new XSKODataParser();
        String content = org.apache.commons.io.IOUtils
                .toString(XSKODataUtilsTest.class.getResourceAsStream("/entity_with_all_set_of_navigations.xsodata"), StandardCharsets.UTF_8);
        return parser.parseXSODataArtifact("np/entity_with_all_set_of_navigations.xsodata", content);
    }

    public static XSKODataModel parseSecondXSKODataModel() throws IOException, SQLException {
        XSKODataParser parser = new XSKODataParser();
        String content = org.apache.commons.io.IOUtils
                .toString(XSKODataUtilsTest.class.getResourceAsStream("/entity_with_events.xsodata"), StandardCharsets.UTF_8);
        return parser.parseXSODataArtifact("my.demo.namespace/entity_with_events.xsodata", content);
    }

    @Test
    public void testCreateXSKODataArtifact() throws IOException, SQLException, XSKODataException {
        XSKODataModel xskoDataModel = parseXSKODataModel();

        dao.createXSKODataArtifact(xskoDataModel);
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT COUNT(*) as rawsCount FROM public.XSK_ODATA");
        assertTrue(rs.next());
        assertEquals(1, rs.getInt("rawsCount"));

        rs = stmt.executeQuery("SELECT OD_LOCATION, OD_NAME, OD_HASH, OD_CREATED_BY, OD_CREATED_AT FROM public.XSK_ODATA");
        assertTrue(rs.next());
        assertEquals(xskoDataModel.getLocation(), rs.getString("OD_LOCATION"));
        assertEquals(xskoDataModel.getName(), rs.getString("OD_NAME"));
        assertEquals(xskoDataModel.getHash(), rs.getString("OD_HASH"));
        assertEquals(xskoDataModel.getCreatedBy(), rs.getString("OD_CREATED_BY"));
        assertEquals(xskoDataModel.getCreatedAt(), rs.getTimestamp("OD_CREATED_AT"));

        clearTable();
    }

    @Test
    public void testGetXSKODataArtifact() throws IOException, SQLException, XSKODataException {
        XSKODataModel xskoDataModel = parseXSKODataModel();

        dao.createXSKODataArtifact(xskoDataModel);
        assertEquals(xskoDataModel, dao.getXSKODataArtifact(xskoDataModel.getLocation()));

        clearTable();
    }

    @Test
    public void testGetXSKODataArtifactByName() throws IOException, SQLException, XSKODataException {
        XSKODataModel xskoDataModel = parseXSKODataModel();

        dao.createXSKODataArtifact(xskoDataModel);
        assertEquals(xskoDataModel, dao.getXSKODataArtifactByName(xskoDataModel.getName()));

        clearTable();
    }

    @Test
    public void testRemoveXSKODataArtifact() throws IOException, SQLException, XSKODataException {
        XSKODataModel xskoDataModel = parseXSKODataModel();

        dao.createXSKODataArtifact(xskoDataModel);
        dao.removeXSKODataArtifact(xskoDataModel.getLocation());

        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT COUNT(*) as rawsCount FROM public.XSK_ODATA");
        assertTrue(rs.next());
        assertEquals(0, rs.getInt("rawsCount"));

        clearTable();
    }

    @Test
    public void testGetAllXSKODataArtifacts() throws IOException, SQLException, XSKODataException {
        XSKODataModel xskoDataModel = parseXSKODataModel();
        dao.createXSKODataArtifact(xskoDataModel);

        XSKODataModel xskoDataModel2 = parseSecondXSKODataModel();
        dao.createXSKODataArtifact(xskoDataModel2);

        List<XSKODataModel> persistedAllArtifacts = dao.getAllXSKODataArtifacts();
        assertEquals(2, persistedAllArtifacts.size());
        assertEquals(xskoDataModel, persistedAllArtifacts.get(0));
        assertEquals(xskoDataModel2, persistedAllArtifacts.get(1));

        clearTable();
    }

    @Test
    public void testUpdateXSKODataArtifact() throws IOException, SQLException, XSKODataException {
        XSKODataModel xskoDataModel = parseXSKODataModel();
        dao.createXSKODataArtifact(xskoDataModel);

        dao.updateXSKODataArtifact(xskoDataModel.getLocation(), xskoDataModel.getName() + "_new", "new_hash");

        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT OD_NAME, OD_HASH FROM public.XSK_ODATA");
        assertTrue(rs.next());
        assertEquals(xskoDataModel.getName() + "_new", rs.getString("OD_NAME"));
        assertEquals("new_hash", rs.getString("OD_HASH"));

        clearTable();
    }
}
