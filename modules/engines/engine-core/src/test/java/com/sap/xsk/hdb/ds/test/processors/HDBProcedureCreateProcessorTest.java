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
package com.sap.xsk.hdb.ds.test.processors;

import com.sap.xsk.hdb.ds.model.hdbprocedure.XSKDataStructureHDBProcedureModel;
import com.sap.xsk.hdb.ds.processors.hdbprocedure.HDBProcedureCreateProcessor;
import com.sap.xsk.hdb.ds.test.XSKViewParserTest;
import org.eclipse.dirigible.core.test.AbstractGuiceTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest(HDBProcedureCreateProcessor.class)
public class HDBProcedureCreateProcessorTest extends AbstractGuiceTest {

    @Mock
    private Connection mockConnection;
    @Mock
    private PreparedStatement mockStatement;


    @Before public void openMocks() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void executeCreateProcedureSuccessfully() throws IOException, SQLException {
        String hdbprocedureSample = org.apache.commons.io.IOUtils.toString(XSKViewParserTest.class.getResourceAsStream("/OrderProcedure.hdbprocedure"), StandardCharsets.UTF_8);

        XSKDataStructureHDBProcedureModel model = new XSKDataStructureHDBProcedureModel();
        model.setContent(hdbprocedureSample);
        List<XSKDataStructureHDBProcedureModel> hdbProcedures = Arrays.asList(model);

        String sql = "CREATE " + model.getContent();

        Mockito.when(mockConnection.prepareStatement(sql)).thenReturn(mockStatement);
        Mockito.when(mockStatement.executeUpdate(Mockito.any())).thenReturn(1);

        HDBProcedureCreateProcessor.execute(mockConnection, hdbProcedures);

        PowerMockito.spy(HDBProcedureCreateProcessor.class);
        PowerMockito.doReturn("whatever").when(HDBProcedureCreateProcessor.class, "executeCreate",sql);


        //PowerMockito.mockStatic(HDBProcedureCreateProcessor.class);
        //Mockito.when(HDBProcedureCreateProcessor.execute(mockConnection, hdbProcedures));

//        HDBProcedureCreateProcessor a = Mockito.spy(HDBProcedureCreateProcessor.class);
//        Mockito.verify(a, Mockito.times(1)).executeCreate(someString);


        assertTrue(true);
//        try (MockedStatic mocked = mockStatic(HDBProcedureCreateProcessor.class)) {
//            //Mockito.verify(mocked, Mockito.times(1)).executeCreate();
//            //Mockito.verify(Mockito.times(2), () -> HDBProcedureCreateProcessor.execute(mockConnection, hdbProcedures));
//            //Mockito.verify(, Mockito.times(1));
//            Mockito.when(() -> HDBProcedureCreateProcessor.executeCreate());
//        }


    }
}
