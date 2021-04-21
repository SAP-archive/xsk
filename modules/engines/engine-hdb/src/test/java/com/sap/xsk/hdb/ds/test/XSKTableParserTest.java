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
package com.sap.xsk.hdb.ds.test;
/**
 * Copyright (c) 2010-2018 SAP and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * <p>
 * Contributors:
 * SAP - initial API and implementation
 */

import com.sap.xsk.hdb.ds.model.XSKDataStructureModelFactory;
import com.sap.xsk.hdb.ds.model.XSKHanaVersion;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableColumnModel;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableModel;
import com.sap.xsk.hdb.ds.parser.hdbtable.XSKTableParser;
import com.sap.xsk.hdb.ds.synchronizer.XSKDataStructuresSynchronizer;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import javax.inject.Inject;
import javax.sql.DataSource;
import com.sap.xsk.parser.hdbtable.exceptions.XSKHDBTableDuplicatePropertyException;
import com.sap.xsk.parser.hdbtable.exceptions.XSKHDBTableMissingPropertyException;
import org.apache.commons.io.IOUtils;
import org.eclipse.dirigible.core.test.AbstractGuiceTest;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * The Class DataStructureSchemaTest.
 */
public class XSKTableParserTest extends AbstractGuiceTest {

  /**
   * The data structure core service.
   */
  @Inject
  private XSKDataStructuresSynchronizer dataStructuresSynchronizer;

  /**
   * The datasource
   */
  @Inject
  private DataSource dataSource;

  /**
   * Sets the up.
   *
   * @throws Exception the exception
   */
  @Before
  public void setUp() throws Exception {
    this.dataStructuresSynchronizer = getInjector().getInstance(XSKDataStructuresSynchronizer.class);
    this.dataSource = getInjector().getInstance(DataSource.class);
  }

  /**
   * Parses the entities
   */
  @Test
  public void parseTable() {
//		Configuration.set("DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE", "true");
    try {
      InputStream in = XSKTableParserTest.class.getResourceAsStream("/Sports.hdbtable");
      try {
        String contents = IOUtils.toString(in, StandardCharsets.UTF_8);
        XSKDataStructureHDBTableModel model = new XSKDataStructureModelFactory().parseTable("/Sports.hdbtable", contents);
        assertEquals("SPORTS", model.getSchema());
        assertEquals("COLUMNSTORE", model.getTableType());
        assertEquals("Team players", model.getDescription());
        for (XSKDataStructureHDBTableColumnModel column : model.getColumns()) {
          if ("MATCH_ID".equals(column.getName())) {
            assertEquals("NVARCHAR", column.getType());
            assertEquals("32", column.getLength());
//						assertEquals("test", column.getComment());
            assertEquals(false, column.isNullable());
//						assertEquals(true, column.isPrimaryKey());
          }
          if ("PERSON_RATE".equals(column.getName())) {
            assertEquals("DECIMAL", column.getType());
            assertEquals("2", column.getPrecision());
            assertEquals("3", column.getScale());
          }
        }
      } finally {
        if (in != null) {
          in.close();
        }
      }

    } catch (Exception e) {
      e.printStackTrace();
      fail(e.getMessage());
    }
  }

  @Test
  public void failIfParsingRepetitiveProperties() throws Exception {
    InputStream in = XSKTableParserTest.class.getResourceAsStream("/DuplicateTableProperties.hdbtable");
    String content = IOUtils.toString(in, StandardCharsets.UTF_8);

    assertThrows(XSKHDBTableDuplicatePropertyException.class, () -> {
      new XSKTableParser().parse("/DuplicateTableProperties.hdbtable", content);
    });
  }

  @Test
  public void failIfParsingMissingMandatoryProperties() throws Exception {
    InputStream in = XSKTableParserTest.class.getResourceAsStream("/MissingMandatoryTableProperties.hdbtable");
    String content = IOUtils.toString(in, StandardCharsets.UTF_8);

    assertThrows(XSKHDBTableMissingPropertyException.class, () -> {
      new XSKTableParser().parse("/MissingMandatoryTableProperties.hdbtable", content);
    });
  }

  @Test
  public void parseHanaXSAdvancedContent() throws Exception {
      InputStream in = XSKTableParserTest.class.getResourceAsStream("/HdbtableHanaXSAdvancedContent.hdbtable");
      String content = IOUtils.toString(in, StandardCharsets.UTF_8);
      XSKDataStructureHDBTableModel model = new XSKDataStructureModelFactory().parseTable("/HdbtableHanaXSAdvancedContent.hdbtable", content);
      assertEquals(XSKHanaVersion.VERSION_2, model.getHanaVersion());
      assertEquals(content, model.getRawContent());
  }

}
