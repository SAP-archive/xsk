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
package com.sap.xsk.hdb.ds.test.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.model.XSKDBContentType;
import com.sap.xsk.hdb.ds.model.XSKDataStructureModel;
import com.sap.xsk.hdb.ds.model.XSKDataStructureModelFactory;
import com.sap.xsk.hdb.ds.model.hdbdd.XSKDataStructureCdsModel;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableColumnModel;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableModel;
import com.sap.xsk.hdb.ds.test.module.HdbTestModule;
import org.eclipse.dirigible.core.test.AbstractDirigibleTest;
import org.junit.Before;
import org.junit.Test;

public class XSKHdbddParserTest extends AbstractDirigibleTest {

  @Before
  public void setUp() {
    HdbTestModule testModule = new HdbTestModule();
    testModule.configure();
  }

  @Test
  public void parseHanaXSClassicContentWithSyntaxErrorFail() {
    XSKDataStructuresException exception = assertThrows(
        XSKDataStructuresException.class,
        () -> XSKDataStructureModelFactory.parseHdbdd("gstr2/ITC_EXPIRED_CONFIG.hdbdd", "")
    );
    assertEquals(
        "Failed to parse file: gstr2/ITC_EXPIRED_CONFIG.hdbdd. Error at line: 6  - 'KeY1' is not a valid artifact type.",
        exception.getMessage());
  }

  @Test
  public void parseHanaXSClassicContentWithLexerErrorFail() {
    XSKDataStructuresException exception = assertThrows(
        XSKDataStructuresException.class,
        () -> XSKDataStructureModelFactory.parseHdbdd("gstr2/ITC_EXPIRED_CONFIG1.hdbdd", "")
    );
    assertEquals(
        "Wrong format of HDB HDBDD: [gstr2/ITC_EXPIRED_CONFIG1.hdbdd] during parsing. Ensure you are using the correct format for the correct compatibility version.",
        exception.getMessage());
  }

  @Test
  public void parseHDBDDWithManagedAss() throws Exception {
    XSKDataStructureModel parsedModel = XSKDataStructureModelFactory.parseHdbdd("gstr2/ProductsWithManagedAss.hdbdd", "");

    assertEquals(2, ((XSKDataStructureCdsModel) parsedModel).getTableModels().size());

    XSKDataStructureHDBTableModel orderDataStructure = ((XSKDataStructureCdsModel) parsedModel).getTableModels().get(1);
    assertEquals("gstr2::ProductsWithManagedAss.Orders", orderDataStructure.getName());
    assertEquals("ADMIN", orderDataStructure.getSchema());
    assertEquals(XSKDBContentType.XS_CLASSIC, orderDataStructure.getDBContentType());
    assertNotNull(orderDataStructure.getConstraints().getPrimaryKey());
    assertEquals(1, orderDataStructure.getConstraints().getPrimaryKey().getColumns().length);
    assertEquals("Id", orderDataStructure.getConstraints().getPrimaryKey().getColumns()[0]);
    assertEquals("PK_gstr2::ProductsWithManagedAss.Orders", orderDataStructure.getConstraints().getPrimaryKey().getName());
    assertNull(orderDataStructure.getConstraints().getPrimaryKey().getModifiers());
    assertEquals(1, orderDataStructure.getConstraints().getForeignKeys().size());

    assertEquals("gstr2::ProductsWithManagedAss.Country", orderDataStructure.getConstraints().getForeignKeys().get(0).getReferencedTable());
    assertEquals("gstr2::ProductsWithManagedAss.Orders.Country", orderDataStructure.getConstraints().getForeignKeys().get(0).getName());
    assertNull(orderDataStructure.getConstraints().getForeignKeys().get(0).getModifiers());
    assertEquals(1, orderDataStructure.getConstraints().getForeignKeys().get(0).getReferencedColumns().length);
    assertEquals("Id", orderDataStructure.getConstraints().getForeignKeys().get(0).getReferencedColumns()[0]);
    assertEquals(1, orderDataStructure.getConstraints().getForeignKeys().get(0).getColumns().length);
    assertEquals("Country.Id", orderDataStructure.getConstraints().getForeignKeys().get(0).getColumns()[0]);

    assertEquals(0, orderDataStructure.getConstraints().getUniqueIndices().size());
    assertEquals(0, orderDataStructure.getConstraints().getChecks().size());
    assertNull(orderDataStructure.getPublicProp());
    assertNull(orderDataStructure.getLoggingType());
    assertNull(orderDataStructure.getTemporary());
    assertNull(orderDataStructure.getLocation());
    assertNull(orderDataStructure.getType());
    assertNull(orderDataStructure.getTableType());
    assertNull(orderDataStructure.getDescription());

    assertEquals(3, orderDataStructure.getColumns().size());

    XSKDataStructureHDBTableColumnModel OrderId = orderDataStructure.getColumns().get(0);
    assertEquals("Id", OrderId.getName());
    assertTrue(OrderId.isPrimaryKey());
    assertEquals("32", OrderId.getLength());
    assertTrue(OrderId.isNullable());
    assertNull(OrderId.getDefaultValue());
    assertNull(OrderId.getPrecision());
    assertNull(OrderId.getScale());
    assertFalse(OrderId.isUnique());
    assertNull(OrderId.getComment());

    XSKDataStructureHDBTableColumnModel CustomerName = orderDataStructure.getColumns().get(1);
    assertEquals("CustomerName", CustomerName.getName());
    assertFalse(CustomerName.isPrimaryKey());
    assertEquals("500", CustomerName.getLength());
    assertTrue(CustomerName.isNullable());
    assertNull(CustomerName.getDefaultValue());
    assertNull(CustomerName.getPrecision());
    assertNull(CustomerName.getScale());
    assertFalse(CustomerName.isUnique());
    assertNull(CustomerName.getComment());

    XSKDataStructureHDBTableColumnModel CountryId = orderDataStructure.getColumns().get(2);
    assertEquals("Country.Id", CountryId.getName());
    assertFalse(CountryId.isPrimaryKey());
    assertEquals("32", CountryId.getLength());
    assertTrue(CountryId.isNullable());
    assertNull(CountryId.getDefaultValue());
    assertNull(CountryId.getPrecision());
    assertNull(CountryId.getScale());
    assertFalse(CountryId.isUnique());
    assertNull(CountryId.getComment());

    XSKDataStructureHDBTableModel countryDataStructure = ((XSKDataStructureCdsModel) parsedModel).getTableModels().get(0);
    assertEquals("gstr2::ProductsWithManagedAss.Country", countryDataStructure.getName());
    assertEquals("ADMIN", countryDataStructure.getSchema());
    assertEquals(XSKDBContentType.XS_CLASSIC, countryDataStructure.getDBContentType());
    assertNotNull(countryDataStructure.getConstraints().getPrimaryKey());
    assertEquals(2, countryDataStructure.getConstraints().getPrimaryKey().getColumns().length);
    assertEquals("Id", countryDataStructure.getConstraints().getPrimaryKey().getColumns()[0]);
    assertEquals("Id2", countryDataStructure.getConstraints().getPrimaryKey().getColumns()[1]);
    assertEquals("PK_gstr2::ProductsWithManagedAss.Country", countryDataStructure.getConstraints().getPrimaryKey().getName());
    assertNull(countryDataStructure.getConstraints().getPrimaryKey().getModifiers());
    assertEquals(0, countryDataStructure.getConstraints().getForeignKeys().size());
    assertEquals(0, countryDataStructure.getConstraints().getUniqueIndices().size());
    assertEquals(0, countryDataStructure.getConstraints().getChecks().size());
    assertEquals(3, countryDataStructure.getColumns().size());
    CountryId = countryDataStructure.getColumns().get(0);
    assertEquals("Id", CountryId.getName());
    assertTrue(CountryId.isPrimaryKey());
    XSKDataStructureHDBTableColumnModel CountryId2 = countryDataStructure.getColumns().get(1);
    assertEquals("Id2", CountryId2.getName());
    assertTrue(CountryId2.isPrimaryKey());
    XSKDataStructureHDBTableColumnModel CountryName = countryDataStructure.getColumns().get(2);
    assertEquals("Name", CountryName.getName());
    assertFalse(CountryName.isPrimaryKey());

    assertEquals("gstr2/ProductsWithManagedAss.hdbdd", parsedModel.getLocation());
    assertEquals("gstr2/ProductsWithManagedAss.hdbdd", parsedModel.getName());
    assertEquals("HDBDD", parsedModel.getType());
    assertEquals("guest", parsedModel.getCreatedBy());
    assertEquals(0, parsedModel.getDependencies().size());
    assertEquals("ADMIN", parsedModel.getSchema());
    assertNull(parsedModel.getRawContent());
    assertEquals(XSKDBContentType.XS_CLASSIC, parsedModel.getDBContentType());

  }

  @Test
  public void parseHDBDDWithUnManagedAss() throws Exception {
    XSKDataStructureModel parsedModel = XSKDataStructureModelFactory.parseHdbdd("gstr2/ProductsWithUnManagedAss.hdbdd", "");

    assertEquals(2, ((XSKDataStructureCdsModel) parsedModel).getTableModels().size());

    XSKDataStructureHDBTableModel orderDataStructure = ((XSKDataStructureCdsModel) parsedModel).getTableModels().get(0);
    assertEquals("gstr2::ProductsWithUnManagedAss.Orders", orderDataStructure.getName());
    assertEquals("ADMIN", orderDataStructure.getSchema());
    assertEquals(XSKDBContentType.XS_CLASSIC, orderDataStructure.getDBContentType());
    assertNotNull(orderDataStructure.getConstraints().getPrimaryKey());
    assertEquals(1, orderDataStructure.getConstraints().getPrimaryKey().getColumns().length);
    assertEquals("Id", orderDataStructure.getConstraints().getPrimaryKey().getColumns()[0]);
    assertEquals("PK_gstr2::ProductsWithUnManagedAss.Orders", orderDataStructure.getConstraints().getPrimaryKey().getName());
    assertNull(orderDataStructure.getConstraints().getPrimaryKey().getModifiers());
    assertEquals(0, orderDataStructure.getConstraints().getForeignKeys().size());
    assertEquals(0, orderDataStructure.getConstraints().getUniqueIndices().size());
    assertEquals(0, orderDataStructure.getConstraints().getChecks().size());
    assertNull(orderDataStructure.getPublicProp());
    assertNull(orderDataStructure.getLoggingType());
    assertNull(orderDataStructure.getTemporary());
    assertNull(orderDataStructure.getLocation());
    assertNull(orderDataStructure.getType());
    assertNull(orderDataStructure.getTableType());
    assertNull(orderDataStructure.getDescription());

    assertEquals(2, orderDataStructure.getColumns().size());

    XSKDataStructureHDBTableColumnModel id = orderDataStructure.getColumns().get(0);
    assertEquals("Id", id.getName());
    assertTrue(id.isPrimaryKey());
    assertEquals("32", id.getLength());
    assertTrue(id.isNullable());
    assertNull(id.getDefaultValue());
    assertNull(id.getPrecision());
    assertNull(id.getScale());
    assertFalse(id.isUnique());
    assertNull(id.getComment());

    XSKDataStructureHDBTableColumnModel CustomerName = orderDataStructure.getColumns().get(1);
    assertEquals("CustomerName", CustomerName.getName());
    assertFalse(CustomerName.isPrimaryKey());
    assertEquals("500", CustomerName.getLength());
    assertTrue(CustomerName.isNullable());
    assertNull(CustomerName.getDefaultValue());
    assertNull(CustomerName.getPrecision());
    assertNull(CustomerName.getScale());
    assertFalse(CustomerName.isUnique());
    assertNull(CustomerName.getComment());

    XSKDataStructureHDBTableModel itemDataStructure = ((XSKDataStructureCdsModel) parsedModel).getTableModels().get(1);
    assertEquals("gstr2::ProductsWithUnManagedAss.Item", itemDataStructure.getName());
    assertEquals("ADMIN", itemDataStructure.getSchema());
    assertEquals(XSKDBContentType.XS_CLASSIC, itemDataStructure.getDBContentType());
    assertNotNull(itemDataStructure.getConstraints().getPrimaryKey());
    assertEquals(1, itemDataStructure.getConstraints().getPrimaryKey().getColumns().length);
    assertEquals("ItemId", itemDataStructure.getConstraints().getPrimaryKey().getColumns()[0]);
    assertEquals("PK_gstr2::ProductsWithUnManagedAss.Item", itemDataStructure.getConstraints().getPrimaryKey().getName());
    assertNull(itemDataStructure.getConstraints().getPrimaryKey().getModifiers());

    assertEquals(1, itemDataStructure.getConstraints().getForeignKeys().size());
    assertEquals("gstr2::ProductsWithUnManagedAss.Orders", itemDataStructure.getConstraints().getForeignKeys().get(0).getReferencedTable());
    assertEquals("gstr2::ProductsWithUnManagedAss.Item.OrderId", itemDataStructure.getConstraints().getForeignKeys().get(0).getName());
    assertNull(itemDataStructure.getConstraints().getForeignKeys().get(0).getModifiers());
    assertEquals(1, itemDataStructure.getConstraints().getForeignKeys().get(0).getReferencedColumns().length);
    assertEquals("Id", itemDataStructure.getConstraints().getForeignKeys().get(0).getReferencedColumns()[0]);
    assertEquals(1, itemDataStructure.getConstraints().getForeignKeys().get(0).getColumns().length);
    assertEquals("OrderId", itemDataStructure.getConstraints().getForeignKeys().get(0).getColumns()[0]);

    assertEquals(0, itemDataStructure.getConstraints().getUniqueIndices().size());
    assertEquals(0, itemDataStructure.getConstraints().getChecks().size());
    assertEquals(2, itemDataStructure.getColumns().size());
    XSKDataStructureHDBTableColumnModel ItemId = itemDataStructure.getColumns().get(0);
    assertEquals("ItemId", ItemId.getName());
    assertTrue(ItemId.isPrimaryKey());
    XSKDataStructureHDBTableColumnModel OrderId = itemDataStructure.getColumns().get(1);
    assertEquals("OrderId", OrderId.getName());
    assertFalse(OrderId.isPrimaryKey());

    assertEquals("gstr2/ProductsWithUnManagedAss.hdbdd", parsedModel.getLocation());
    assertEquals("gstr2/ProductsWithUnManagedAss.hdbdd", parsedModel.getName());
    assertEquals("HDBDD", parsedModel.getType());
    assertEquals("guest", parsedModel.getCreatedBy());
    assertEquals(0, parsedModel.getDependencies().size());
    assertEquals("ADMIN",parsedModel.getSchema());
    assertNull(parsedModel.getRawContent());
    assertEquals(XSKDBContentType.XS_CLASSIC, parsedModel.getDBContentType());
  }
}