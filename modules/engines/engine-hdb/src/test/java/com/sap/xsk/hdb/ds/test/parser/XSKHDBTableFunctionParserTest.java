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
package com.sap.xsk.hdb.ds.test.parser;

import com.sap.xsk.exceptions.XSKArtifactParserException;
import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.artefacts.HDBTableFunctionSynchronizationArtefactType;
import com.sap.xsk.hdb.ds.model.hdbtablefunction.XSKDataStructureHDBTableFunctionModel;
import com.sap.xsk.hdb.ds.parser.hdbtablefunction.XSKHDBTableFunctionLogger;
import com.sap.xsk.hdb.ds.parser.hdbtablefunction.XSKHDBTableFunctionParser;
import com.sap.xsk.hdb.ds.synchronizer.XSKDataStructuresSynchronizer;
import com.sap.xsk.utils.XSKCommonsConstants;
import com.sap.xsk.utils.XSKCommonsUtils;
import org.eclipse.dirigible.core.scheduler.api.ISynchronizerArtefactType.ArtefactState;
import org.eclipse.dirigible.core.test.AbstractDirigibleTest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class XSKHDBTableFunctionParserTest extends AbstractDirigibleTest {

   TestContentProvider contentProvider = new TestContentProvider();
   TestTableFunctionParser tableFunctionParser = new TestTableFunctionParser();
    private XSKHDBTableFunctionParser parser;

    private XSKDataStructuresSynchronizer dataStructuresSynchronizerMock;
    private XSKHDBTableFunctionLogger tableFunctionLoggerMock;
    private HDBTableFunctionSynchronizationArtefactType tableFunctionSynchronizationArtefactType;

    @Before
    public void setup() {
        dataStructuresSynchronizerMock = Mockito.mock(XSKDataStructuresSynchronizer.class);
        doNothing().when(dataStructuresSynchronizerMock).applyArtefactState(any(), any(), any(), any(), any());
        tableFunctionLoggerMock = Mockito.mock(XSKHDBTableFunctionLogger.class);
        doNothing().when(tableFunctionLoggerMock).logError(any(), any(), any());

        tableFunctionSynchronizationArtefactType = new HDBTableFunctionSynchronizationArtefactType();
        parser = new XSKHDBTableFunctionParser(dataStructuresSynchronizerMock, tableFunctionSynchronizationArtefactType, tableFunctionLoggerMock);
    }

    @Test
    public void parseTableFunction() throws IOException, XSKDataStructuresException, XSKArtifactParserException {
        String location = "/OrderTableFunction.hdbtablefunction";
        String content = contentProvider.getTestContent(location);

        XSKDataStructureHDBTableFunctionModel model = tableFunctionParser.parseTableFunction(parser, location, content);
        assertEquals("Unexpected tablefunction schema.", "MYSCHEMA", model.getSchema());
        assertEquals("Unexpected tablefunction name.", "hdb_view::OrderTableFunction", model.getName());
        assertEquals("Unexpected tablefunction content.", content, model.getRawContent());
        assertEquals("Unexpected tablefunction raw content.", content, model.getRawContent());
        assertEquals("Unexpected tablefunction location.", location, model.getLocation());
        assertEquals("Unexpected tablefunction type.", "HDBTABLEFUNC", model.getType());
        assertEquals("Unexpected tablefunction dependencies.", 0, model.getDependencies().size());
        assertNotNull("Null value for tablefunction createdAt", model.getCreatedAt());
        assertNotNull("Null value for tablefunction createdBy", model.getCreatedBy());
        assertEquals("Cant access data structure model class", "class com.sap.xsk.hdb.ds.model.hdbtablefunction.XSKDataStructureHDBTableFunctionModel", parser.getDataStructureClass().toString());

    }

    @Test
    public void parseNoSchemaTableFunction() throws IOException, XSKDataStructuresException, XSKArtifactParserException {
        String location = "/OrderTableFunctionNoSchema.hdbtablefunction";
        String content = contentProvider.getTestContent(location);

        XSKDataStructureHDBTableFunctionModel model = tableFunctionParser.parseTableFunction(parser, location, content);
        assertNull("Unexpected tablefunction schema.", model.getSchema());
        assertEquals("Unexpected tablefunction name.", "hdb_view::OrderTableFunction", model.getName());
        assertEquals("Unexpected tablefunction content.", content, model.getRawContent());
        assertEquals("Unexpected tablefunction raw content.", content, model.getRawContent());
        assertEquals("Unexpected tablefunction location.", location, model.getLocation());
        assertEquals("Unexpected tablefunction type.", "HDBTABLEFUNC", model.getType());
        assertEquals("Unexpected tablefunction dependencies.", 0, model.getDependencies().size());
        assertNotNull("Null value for tablefunction createdAt", model.getCreatedAt());
        assertNotNull("Null value for tablefunction createdBy", model.getCreatedBy());
    }

    @Test
    public void parseNoSchemaNoNameTableFunction() throws IOException {
        String location = "/OrderTableFunctionNoSchemaNoName.hdbtablefunction";
        String content = contentProvider.getTestContent(location);

        XSKDataStructuresException caughtException = assertThrows(
                "Unexpected missing exception",
                XSKDataStructuresException.class,
                () -> tableFunctionParser.parseTableFunction(parser, location, content));
        Throwable caughtExceptionCause = caughtException.getCause();

        assertEquals("Unexpected exception message", "Wrong format of HDB Table Function: " + location + " during parsing. ",
                caughtException.getMessage());
        verify(tableFunctionLoggerMock).logError(location, XSKCommonsConstants.EXPECTED_FIELDS, caughtExceptionCause.getMessage());
        verify(dataStructuresSynchronizerMock).applyArtefactState(
                XSKCommonsUtils.getRepositoryBaseObjectName(location),
                location,
                tableFunctionSynchronizationArtefactType,
                ArtefactState.FAILED_CREATE,
                caughtExceptionCause.getMessage()
        );
    }

}
