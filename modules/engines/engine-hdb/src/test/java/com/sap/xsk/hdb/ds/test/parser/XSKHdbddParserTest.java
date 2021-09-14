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
import static org.junit.Assert.assertThrows;

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
        "Wrong format of HDB HDBDD: [gstr2/ITC_EXPIRED_CONFIG.hdbdd] during parsing. Ensure you are using the correct format for the correct compatibility version.",
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
    assertEquals(null, orderDataStructure.getConstraints().getPrimaryKey());
    assertEquals(1, orderDataStructure.getConstraints().getForeignKeys().size());

    assertEquals("gstr2::ProductsWithManagedAss.Country", orderDataStructure.getConstraints().getForeignKeys().get(0).getReferencedTable());
    assertEquals("gstr2::ProductsWithManagedAss.Orders.Country", orderDataStructure.getConstraints().getForeignKeys().get(0).getName());
    assertEquals(null, orderDataStructure.getConstraints().getForeignKeys().get(0).getModifiers());
    assertEquals(1, orderDataStructure.getConstraints().getForeignKeys().get(0).getReferencedColumns().length);
    assertEquals("Id", orderDataStructure.getConstraints().getForeignKeys().get(0).getReferencedColumns()[0]);
    assertEquals(1, orderDataStructure.getConstraints().getForeignKeys().get(0).getColumns().length);
    assertEquals("Country.Id", orderDataStructure.getConstraints().getForeignKeys().get(0).getColumns()[0]);

    assertEquals(0, orderDataStructure.getConstraints().getUniqueIndices().size());
    assertEquals(0, orderDataStructure.getConstraints().getChecks().size());
    assertEquals(null, orderDataStructure.getPublicProp());
    assertEquals(null, orderDataStructure.getLoggingType());
    assertEquals(null, orderDataStructure.getTemporary());
    assertEquals(null, orderDataStructure.getLocation());
    assertEquals(null, orderDataStructure.getType());
    assertEquals(null, orderDataStructure.getTableType());
    assertEquals(null, orderDataStructure.getDescription());

    assertEquals(3, orderDataStructure.getColumns().size());

    XSKDataStructureHDBTableColumnModel OrderId = orderDataStructure.getColumns().get(0);
    assertEquals("Id", OrderId.getName());
    assertEquals(true, OrderId.isPrimaryKey());
    assertEquals("32", OrderId.getLength());
    assertEquals(true, OrderId.isNullable());
    assertEquals(null, OrderId.getDefaultValue());
    assertEquals(null, OrderId.getPrecision());
    assertEquals(null, OrderId.getScale());
    assertEquals(false, OrderId.isUnique());
    assertEquals(null, OrderId.getComment());

    XSKDataStructureHDBTableColumnModel CustomerName = orderDataStructure.getColumns().get(1);
    assertEquals("CustomerName", CustomerName.getName());
    assertEquals(false, CustomerName.isPrimaryKey());
    assertEquals("500", CustomerName.getLength());
    assertEquals(true, CustomerName.isNullable());
    assertEquals(null, CustomerName.getDefaultValue());
    assertEquals(null, CustomerName.getPrecision());
    assertEquals(null, CustomerName.getScale());
    assertEquals(false, CustomerName.isUnique());
    assertEquals(null, CustomerName.getComment());

    XSKDataStructureHDBTableColumnModel CountryId = orderDataStructure.getColumns().get(2);
    assertEquals("Country.Id", CountryId.getName());
    assertEquals(false, CountryId.isPrimaryKey());
    assertEquals("32", CountryId.getLength());
    assertEquals(true, CountryId.isNullable());
    assertEquals(null, CountryId.getDefaultValue());
    assertEquals(null, CountryId.getPrecision());
    assertEquals(null, CountryId.getScale());
    assertEquals(false, CountryId.isUnique());
    assertEquals(null, CountryId.getComment());

    XSKDataStructureHDBTableModel countryDataStructure = ((XSKDataStructureCdsModel) parsedModel).getTableModels().get(0);
    assertEquals("gstr2::ProductsWithManagedAss.Country", countryDataStructure.getName());
    assertEquals("ADMIN", countryDataStructure.getSchema());
    assertEquals(XSKDBContentType.XS_CLASSIC, countryDataStructure.getDBContentType());
    assertEquals(null, countryDataStructure.getConstraints().getPrimaryKey());
    assertEquals(0, countryDataStructure.getConstraints().getForeignKeys().size());
    assertEquals(0, countryDataStructure.getConstraints().getUniqueIndices().size());
    assertEquals(0, countryDataStructure.getConstraints().getChecks().size());
    assertEquals(2, countryDataStructure.getColumns().size());
    CountryId = countryDataStructure.getColumns().get(0);
    assertEquals("Id", CountryId.getName());
    assertEquals(true, CountryId.isPrimaryKey());
    XSKDataStructureHDBTableColumnModel CountryName = countryDataStructure.getColumns().get(1);
    assertEquals("Name", CountryName.getName());
    assertEquals(false, CountryName.isPrimaryKey());

    assertEquals("gstr2/ProductsWithManagedAss.hdbdd", parsedModel.getLocation());
    assertEquals("gstr2/ProductsWithManagedAss.hdbdd", parsedModel.getName());
    assertEquals("HDBDD", parsedModel.getType());
    assertEquals("guest", parsedModel.getCreatedBy());
    assertEquals(0, parsedModel.getDependencies().size());
    assertEquals(null, parsedModel.getSchema());
    assertEquals(null, parsedModel.getRawContent());
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
    assertEquals(null, orderDataStructure.getConstraints().getPrimaryKey());
    assertEquals(0, orderDataStructure.getConstraints().getForeignKeys().size());
    assertEquals(0, orderDataStructure.getConstraints().getUniqueIndices().size());
    assertEquals(0, orderDataStructure.getConstraints().getChecks().size());
    assertEquals(null, orderDataStructure.getPublicProp());
    assertEquals(null, orderDataStructure.getLoggingType());
    assertEquals(null, orderDataStructure.getTemporary());
    assertEquals(null, orderDataStructure.getLocation());
    assertEquals(null, orderDataStructure.getType());
    assertEquals(null, orderDataStructure.getTableType());
    assertEquals(null, orderDataStructure.getDescription());

   assertEquals(2, orderDataStructure.getColumns().size());

    XSKDataStructureHDBTableColumnModel id = orderDataStructure.getColumns().get(0);
    assertEquals("Id", id.getName());
    assertEquals(true, id.isPrimaryKey());
    assertEquals("32", id.getLength());
    assertEquals(true, id.isNullable());
    assertEquals(null, id.getDefaultValue());
    assertEquals(null, id.getPrecision());
    assertEquals(null, id.getScale());
    assertEquals(false, id.isUnique());
    assertEquals(null, id.getComment());

    XSKDataStructureHDBTableColumnModel CustomerName = orderDataStructure.getColumns().get(1);
    assertEquals("CustomerName", CustomerName.getName());
    assertEquals(false, CustomerName.isPrimaryKey());
    assertEquals("500", CustomerName.getLength());
    assertEquals(true, CustomerName.isNullable());
    assertEquals(null, CustomerName.getDefaultValue());
    assertEquals(null, CustomerName.getPrecision());
    assertEquals(null, CustomerName.getScale());
    assertEquals(false, CustomerName.isUnique());
    assertEquals(null, CustomerName.getComment());

    XSKDataStructureHDBTableModel itemDataStructure = ((XSKDataStructureCdsModel) parsedModel).getTableModels().get(1);
    assertEquals("gstr2::ProductsWithUnManagedAss.Item", itemDataStructure.getName());
    assertEquals("ADMIN", itemDataStructure.getSchema());
    assertEquals(XSKDBContentType.XS_CLASSIC, itemDataStructure.getDBContentType());
    assertEquals(null, itemDataStructure.getConstraints().getPrimaryKey());

    assertEquals(1, itemDataStructure.getConstraints().getForeignKeys().size());
    assertEquals("gstr2::ProductsWithUnManagedAss.Orders", itemDataStructure.getConstraints().getForeignKeys().get(0).getReferencedTable());
    assertEquals("gstr2::ProductsWithUnManagedAss.Item.OrderId", itemDataStructure.getConstraints().getForeignKeys().get(0).getName());
    assertEquals(null, itemDataStructure.getConstraints().getForeignKeys().get(0).getModifiers());
    assertEquals(1, itemDataStructure.getConstraints().getForeignKeys().get(0).getReferencedColumns().length);
    assertEquals("Id", itemDataStructure.getConstraints().getForeignKeys().get(0).getReferencedColumns()[0]);
    assertEquals(1, itemDataStructure.getConstraints().getForeignKeys().get(0).getColumns().length);
    assertEquals("OrderId", itemDataStructure.getConstraints().getForeignKeys().get(0).getColumns()[0]);

    assertEquals(0, itemDataStructure.getConstraints().getUniqueIndices().size());
    assertEquals(0, itemDataStructure.getConstraints().getChecks().size());
    assertEquals(2, itemDataStructure.getColumns().size());
    XSKDataStructureHDBTableColumnModel ItemId = itemDataStructure.getColumns().get(0);
    assertEquals("ItemId", ItemId.getName());
    assertEquals(true, ItemId.isPrimaryKey());
    XSKDataStructureHDBTableColumnModel OrderId = itemDataStructure.getColumns().get(1);
    assertEquals("OrderId", OrderId.getName());
    assertEquals(false, OrderId.isPrimaryKey());

    assertEquals("gstr2/ProductsWithUnManagedAss.hdbdd", parsedModel.getLocation());
    assertEquals("gstr2/ProductsWithUnManagedAss.hdbdd", parsedModel.getName());
    assertEquals("HDBDD", parsedModel.getType());
    assertEquals("guest", parsedModel.getCreatedBy());
    assertEquals(0, parsedModel.getDependencies().size());
    assertEquals(null, parsedModel.getSchema());
    assertEquals(null, parsedModel.getRawContent());
    assertEquals(XSKDBContentType.XS_CLASSIC, parsedModel.getDBContentType());
  }
}