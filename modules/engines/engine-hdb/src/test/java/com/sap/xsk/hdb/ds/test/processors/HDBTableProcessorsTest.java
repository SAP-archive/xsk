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
package com.sap.xsk.hdb.ds.test.processors;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.doReturn;

import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableModel;
import com.sap.xsk.hdb.ds.processors.table.XSKTableAlterProcessor;
import com.sap.xsk.hdb.ds.processors.table.utils.XSKTableAlterHandler;
import java.sql.Connection;
import org.eclipse.dirigible.commons.config.Configuration;
import org.eclipse.dirigible.core.test.AbstractDirigibleTest;
import org.eclipse.dirigible.database.sql.SqlFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({SqlFactory.class, Configuration.class, XSKTableAlterProcessor.class})
public class HDBTableProcessorsTest extends AbstractDirigibleTest {

  @Mock(answer = Answers.RETURNS_DEEP_STUBS)
  private Connection mockConnection;


  @Mock
  private XSKDataStructureHDBTableModel mockModel;

  @Mock
  private XSKTableAlterHandler mockHandler;

  private XSKTableAlterProcessor xskTableAlterProcessor = spy(new XSKTableAlterProcessor());

  @Before
  public void openMocks() {
    MockitoAnnotations.initMocks(this);
  }

  @Test

  public void executeAlterSuccessfully() throws Exception {

    doReturn(mockHandler).when( xskTableAlterProcessor).createTableAlterHandler(mockConnection, mockModel);

    doNothing().when(mockHandler).addColumns(mockConnection);
    doNothing().when(mockHandler).removeColumns(mockConnection);
    doNothing().when(mockHandler).updateColumns(mockConnection);
    doNothing().when(mockHandler).rebuildIndeces(mockConnection);
    doNothing().when(mockHandler).ensurePrimaryKeyIsUnchanged(mockConnection);

    xskTableAlterProcessor.execute(mockConnection,mockModel);

    verify(mockHandler,times(1)).addColumns(mockConnection);
    verify(mockHandler,times(1)).removeColumns(mockConnection);
    verify(mockHandler,times(1)).updateColumns(mockConnection);
    verify(mockHandler,times(1)).rebuildIndeces(mockConnection);
    verify(mockHandler,times(1)).ensurePrimaryKeyIsUnchanged(mockConnection);

  }

}
