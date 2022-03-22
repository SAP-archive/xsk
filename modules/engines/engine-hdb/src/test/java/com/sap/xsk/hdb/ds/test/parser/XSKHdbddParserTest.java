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
import com.sap.xsk.hdb.ds.model.hdbtabletype.XSKDataStructureHDBTableTypeModel;
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

    assertEquals(3, ((XSKDataStructureCdsModel) parsedModel).getTableModels().size());

    XSKDataStructureHDBTableModel orderDataStructure = ((XSKDataStructureCdsModel) parsedModel).getTableModels().get(1);
    assertEquals("gstr2::ProductsWithManagedAss.Orders", orderDataStructure.getName());
    assertEquals("ADMIN", orderDataStructure.getSchema());
    assertEquals(XSKDBContentType.XS_CLASSIC, orderDataStructure.getDBContentType());
    assertNotNull(orderDataStructure.getConstraints().getPrimaryKey());
    assertEquals(1, orderDataStructure.getConstraints().getPrimaryKey().getColumns().length);
    assertEquals("Id", orderDataStructure.getConstraints().getPrimaryKey().getColumns()[0]);
    assertEquals("PK_gstr2::ProductsWithManagedAss.Orders", orderDataStructure.getConstraints().getPrimaryKey().getName());
    assertNull(orderDataStructure.getConstraints().getPrimaryKey().getModifiers());
    assertEquals(2, orderDataStructure.getConstraints().getForeignKeys().size());

    assertEquals("gstr2::ProductsWithManagedAss.Country", orderDataStructure.getConstraints().getForeignKeys().get(0).getReferencedTable());
    assertEquals("gstr2::ProductsWithManagedAss.Orders.Country", orderDataStructure.getConstraints().getForeignKeys().get(0).getName());
    assertNull(orderDataStructure.getConstraints().getForeignKeys().get(0).getModifiers());
    assertEquals(1, orderDataStructure.getConstraints().getForeignKeys().get(0).getReferencedColumns().length);
    assertEquals("Id", orderDataStructure.getConstraints().getForeignKeys().get(0).getReferencedColumns()[0]);
    assertEquals(1, orderDataStructure.getConstraints().getForeignKeys().get(0).getColumns().length);
    assertEquals("Country.Id", orderDataStructure.getConstraints().getForeignKeys().get(0).getColumns()[0]);

    assertEquals("gstr2::ProductsWithManagedAss.City", orderDataStructure.getConstraints().getForeignKeys().get(1).getReferencedTable());
    assertEquals("gstr2::ProductsWithManagedAss.Orders.City", orderDataStructure.getConstraints().getForeignKeys().get(1).getName());
    assertNull(orderDataStructure.getConstraints().getForeignKeys().get(1).getModifiers());
    assertEquals(3, orderDataStructure.getConstraints().getForeignKeys().get(1).getReferencedColumns().length);
    assertEquals(3, orderDataStructure.getConstraints().getForeignKeys().get(1).getColumns().length);
    assertEquals("Id", orderDataStructure.getConstraints().getForeignKeys().get(1).getReferencedColumns()[0]);
    assertEquals("City.Id", orderDataStructure.getConstraints().getForeignKeys().get(1).getColumns()[0]);
    assertEquals("Name", orderDataStructure.getConstraints().getForeignKeys().get(1).getReferencedColumns()[1]);
    assertEquals("City.Name", orderDataStructure.getConstraints().getForeignKeys().get(1).getColumns()[1]);
    assertEquals("PostalCode", orderDataStructure.getConstraints().getForeignKeys().get(1).getReferencedColumns()[2]);
    assertEquals("City.PostalCode", orderDataStructure.getConstraints().getForeignKeys().get(1).getColumns()[2]);

    assertEquals(0, orderDataStructure.getConstraints().getUniqueIndices().size());
    assertEquals(0, orderDataStructure.getConstraints().getChecks().size());
    assertNull(orderDataStructure.getPublicProp());
    assertNull(orderDataStructure.getLoggingType());
    assertNull(orderDataStructure.getTemporary());
    assertNotNull(orderDataStructure.getLocation());
    assertNull(orderDataStructure.getType());
    assertNull(orderDataStructure.getTableType());
    assertNull(orderDataStructure.getDescription());

    assertEquals(6, orderDataStructure.getColumns().size());

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
  public void parseHDBDDWithManagedAssAndAlias() throws Exception {
    XSKDataStructureModel parsedModel = XSKDataStructureModelFactory.parseHdbdd("gstr2/ClientsWithManagedAssAndAlias.hdbdd", "");

    assertEquals(2, ((XSKDataStructureCdsModel) parsedModel).getTableModels().size());

    XSKDataStructureHDBTableModel personDataStructure = ((XSKDataStructureCdsModel) parsedModel).getTableModels().get(1);
    assertEquals("gstr2::ClientsWithManagedAssAndAlias.Person", personDataStructure.getName());
    assertEquals("ADMIN", personDataStructure.getSchema());
    assertEquals(XSKDBContentType.XS_CLASSIC, personDataStructure.getDBContentType());
    assertNotNull(personDataStructure.getConstraints().getPrimaryKey());
    assertEquals(1, personDataStructure.getConstraints().getPrimaryKey().getColumns().length);
    assertEquals("id", personDataStructure.getConstraints().getPrimaryKey().getColumns()[0]);
    assertEquals("PK_gstr2::ClientsWithManagedAssAndAlias.Person", personDataStructure.getConstraints().getPrimaryKey().getName());
    assertNull(personDataStructure.getConstraints().getPrimaryKey().getModifiers());
    assertEquals(2, personDataStructure.getConstraints().getForeignKeys().size());

    assertEquals("gstr2::ClientsWithManagedAssAndAlias.Address",
        personDataStructure.getConstraints().getForeignKeys().get(0).getReferencedTable());
    assertEquals("gstr2::ClientsWithManagedAssAndAlias.Address",
        personDataStructure.getConstraints().getForeignKeys().get(1).getReferencedTable());
    assertEquals("gstr2::ClientsWithManagedAssAndAlias.Person.address1",
        personDataStructure.getConstraints().getForeignKeys().get(0).getName());
    assertEquals("gstr2::ClientsWithManagedAssAndAlias.Person.address2",
        personDataStructure.getConstraints().getForeignKeys().get(1).getName());
    assertNull(personDataStructure.getConstraints().getForeignKeys().get(0).getModifiers());
    assertNull(personDataStructure.getConstraints().getForeignKeys().get(1).getModifiers());
    assertEquals(4, personDataStructure.getConstraints().getForeignKeys().get(0).getReferencedColumns().length);
    assertEquals(4, personDataStructure.getConstraints().getForeignKeys().get(1).getReferencedColumns().length);
    assertEquals("id", personDataStructure.getConstraints().getForeignKeys().get(0).getReferencedColumns()[0]);
    assertEquals("country", personDataStructure.getConstraints().getForeignKeys().get(0).getReferencedColumns()[1]);
    assertEquals("city", personDataStructure.getConstraints().getForeignKeys().get(0).getReferencedColumns()[2]);
    assertEquals("zipCode", personDataStructure.getConstraints().getForeignKeys().get(0).getReferencedColumns()[3]);
    assertEquals("id", personDataStructure.getConstraints().getForeignKeys().get(1).getReferencedColumns()[0]);
    assertEquals("country", personDataStructure.getConstraints().getForeignKeys().get(1).getReferencedColumns()[1]);
    assertEquals("city", personDataStructure.getConstraints().getForeignKeys().get(1).getReferencedColumns()[2]);
    assertEquals("zipCode", personDataStructure.getConstraints().getForeignKeys().get(1).getReferencedColumns()[3]);
    assertEquals(4, personDataStructure.getConstraints().getForeignKeys().get(0).getColumns().length);
    assertEquals(4, personDataStructure.getConstraints().getForeignKeys().get(1).getColumns().length);
    assertEquals("PersonAddressId1", personDataStructure.getConstraints().getForeignKeys().get(0).getColumns()[0]);
    assertEquals("address1.country", personDataStructure.getConstraints().getForeignKeys().get(0).getColumns()[1]);
    assertEquals("address1.city", personDataStructure.getConstraints().getForeignKeys().get(0).getColumns()[2]);
    assertEquals("address1.zipCode", personDataStructure.getConstraints().getForeignKeys().get(0).getColumns()[3]);
    assertEquals("PersonAddressId2", personDataStructure.getConstraints().getForeignKeys().get(1).getColumns()[0]);
    assertEquals("address2.country", personDataStructure.getConstraints().getForeignKeys().get(1).getColumns()[1]);
    assertEquals("address2.city", personDataStructure.getConstraints().getForeignKeys().get(1).getColumns()[2]);
    assertEquals("address2.zipCode", personDataStructure.getConstraints().getForeignKeys().get(1).getColumns()[3]);

    assertEquals(0, personDataStructure.getConstraints().getUniqueIndices().size());
    assertEquals(0, personDataStructure.getConstraints().getChecks().size());
    assertNull(personDataStructure.getPublicProp());
    assertNull(personDataStructure.getLoggingType());
    assertNull(personDataStructure.getTemporary());
    assertNotNull(personDataStructure.getLocation());
    assertNull(personDataStructure.getType());
    assertNull(personDataStructure.getTableType());
    assertNull(personDataStructure.getDescription());

    assertEquals(9, personDataStructure.getColumns().size());

    XSKDataStructureHDBTableColumnModel personId = personDataStructure.getColumns().get(0);
    assertEquals("id", personId.getName());
    assertTrue(personId.isPrimaryKey());
    assertNull(personId.getLength());
    assertTrue(personId.isNullable());
    assertNull(personId.getDefaultValue());
    assertNull(personId.getPrecision());
    assertNull(personId.getScale());
    assertFalse(personId.isUnique());
    assertNull(personId.getComment());

    XSKDataStructureHDBTableColumnModel personAddress1Id = personDataStructure.getColumns().get(1);
    assertEquals("PersonAddressId1", personAddress1Id.getName());
    assertFalse(personAddress1Id.isPrimaryKey());
    assertNull(personAddress1Id.getLength());
    assertTrue(personAddress1Id.isNullable());
    assertNull(personAddress1Id.getDefaultValue());
    assertNull(personAddress1Id.getPrecision());
    assertNull(personAddress1Id.getScale());
    assertFalse(personAddress1Id.isUnique());
    assertNull(personAddress1Id.getComment());

    XSKDataStructureHDBTableColumnModel personAddress1Country = personDataStructure.getColumns().get(2);
    assertEquals("address1.country", personAddress1Country.getName());
    assertFalse(personAddress1Country.isPrimaryKey());
    assertEquals("30", personAddress1Country.getLength());
    assertTrue(personAddress1Country.isNullable());
    assertNull(personAddress1Country.getDefaultValue());
    assertNull(personAddress1Country.getPrecision());
    assertNull(personAddress1Country.getScale());
    assertFalse(personAddress1Country.isUnique());
    assertNull(personAddress1Country.getComment());

    XSKDataStructureHDBTableColumnModel personAddress1City = personDataStructure.getColumns().get(3);
    assertEquals("address1.city", personAddress1City.getName());
    assertFalse(personAddress1City.isPrimaryKey());
    assertEquals("30", personAddress1City.getLength());
    assertTrue(personAddress1City.isNullable());
    assertNull(personAddress1City.getDefaultValue());
    assertNull(personAddress1City.getPrecision());
    assertNull(personAddress1City.getScale());
    assertFalse(personAddress1City.isUnique());
    assertNull(personAddress1City.getComment());

    XSKDataStructureHDBTableColumnModel personAddress1Zip = personDataStructure.getColumns().get(4);
    assertEquals("address1.zipCode", personAddress1Zip.getName());
    assertFalse(personAddress1Zip.isPrimaryKey());
    assertEquals("30", personAddress1Zip.getLength());
    assertTrue(personAddress1Zip.isNullable());
    assertNull(personAddress1Zip.getDefaultValue());
    assertNull(personAddress1Zip.getPrecision());
    assertNull(personAddress1Zip.getScale());
    assertFalse(personAddress1Zip.isUnique());
    assertNull(personAddress1Zip.getComment());

    XSKDataStructureHDBTableColumnModel personAddress2Id = personDataStructure.getColumns().get(5);
    assertEquals("PersonAddressId2", personAddress2Id.getName());
    assertFalse(personAddress2Id.isPrimaryKey());
    assertNull(personAddress2Id.getLength());
    assertTrue(personAddress2Id.isNullable());
    assertNull(personAddress2Id.getDefaultValue());
    assertNull(personAddress2Id.getPrecision());
    assertNull(personAddress2Id.getScale());
    assertFalse(personAddress2Id.isUnique());
    assertNull(personAddress2Id.getComment());

    XSKDataStructureHDBTableColumnModel personAddress2Country = personDataStructure.getColumns().get(6);
    assertEquals("address2.country", personAddress2Country.getName());
    assertFalse(personAddress2Country.isPrimaryKey());
    assertEquals("30", personAddress2Country.getLength());
    assertTrue(personAddress2Country.isNullable());
    assertNull(personAddress2Country.getDefaultValue());
    assertNull(personAddress2Country.getPrecision());
    assertNull(personAddress2Country.getScale());
    assertFalse(personAddress2Country.isUnique());
    assertNull(personAddress2Country.getComment());

    XSKDataStructureHDBTableColumnModel personAddress2City = personDataStructure.getColumns().get(7);
    assertEquals("address2.city", personAddress2City.getName());
    assertFalse(personAddress2City.isPrimaryKey());
    assertEquals("30", personAddress2City.getLength());
    assertTrue(personAddress2City.isNullable());
    assertNull(personAddress2City.getDefaultValue());
    assertNull(personAddress2City.getPrecision());
    assertNull(personAddress2City.getScale());
    assertFalse(personAddress2City.isUnique());
    assertNull(personAddress2City.getComment());

    XSKDataStructureHDBTableColumnModel personAddress2Zip = personDataStructure.getColumns().get(8);
    assertEquals("address2.zipCode", personAddress2Zip.getName());
    assertFalse(personAddress2Zip.isPrimaryKey());
    assertEquals("30", personAddress2Zip.getLength());
    assertTrue(personAddress2Zip.isNullable());
    assertNull(personAddress2Zip.getDefaultValue());
    assertNull(personAddress2Zip.getPrecision());
    assertNull(personAddress2Zip.getScale());
    assertFalse(personAddress2Zip.isUnique());
    assertNull(personAddress2Zip.getComment());

    XSKDataStructureHDBTableModel addressDataStructure = ((XSKDataStructureCdsModel) parsedModel).getTableModels().get(0);
    assertEquals("gstr2::ClientsWithManagedAssAndAlias.Address", addressDataStructure.getName());
    assertEquals("ADMIN", addressDataStructure.getSchema());
    assertEquals(XSKDBContentType.XS_CLASSIC, addressDataStructure.getDBContentType());
    assertNotNull(addressDataStructure.getConstraints().getPrimaryKey());
    assertEquals(4, addressDataStructure.getConstraints().getPrimaryKey().getColumns().length);
    assertEquals("id", addressDataStructure.getConstraints().getPrimaryKey().getColumns()[0]);
    assertEquals("country", addressDataStructure.getConstraints().getPrimaryKey().getColumns()[1]);
    assertEquals("city", addressDataStructure.getConstraints().getPrimaryKey().getColumns()[2]);
    assertEquals("zipCode", addressDataStructure.getConstraints().getPrimaryKey().getColumns()[3]);
    assertEquals("PK_gstr2::ClientsWithManagedAssAndAlias.Address", addressDataStructure.getConstraints().getPrimaryKey().getName());
    assertNull(addressDataStructure.getConstraints().getPrimaryKey().getModifiers());
    assertEquals(0, addressDataStructure.getConstraints().getForeignKeys().size());
    assertEquals(0, addressDataStructure.getConstraints().getUniqueIndices().size());
    assertEquals(0, addressDataStructure.getConstraints().getChecks().size());
    assertEquals(4, addressDataStructure.getColumns().size());
    XSKDataStructureHDBTableColumnModel addressId = addressDataStructure.getColumns().get(0);
    assertEquals("id", addressId.getName());
    assertTrue(addressId.isPrimaryKey());
    XSKDataStructureHDBTableColumnModel addressCountry = addressDataStructure.getColumns().get(1);
    assertEquals("country", addressCountry.getName());
    assertTrue(addressCountry.isPrimaryKey());
    XSKDataStructureHDBTableColumnModel addressCity = addressDataStructure.getColumns().get(2);
    assertEquals("city", addressCity.getName());
    assertTrue(addressCity.isPrimaryKey());
    XSKDataStructureHDBTableColumnModel addressZipCode = addressDataStructure.getColumns().get(3);
    assertEquals("zipCode", addressZipCode.getName());
    assertTrue(addressZipCode.isPrimaryKey());

    assertEquals("gstr2/ClientsWithManagedAssAndAlias.hdbdd", parsedModel.getLocation());
    assertEquals("gstr2/ClientsWithManagedAssAndAlias.hdbdd", parsedModel.getName());
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
    assertNotNull(orderDataStructure.getLocation());
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
    assertEquals("ADMIN", parsedModel.getSchema());
    assertNull(parsedModel.getRawContent());
    assertEquals(XSKDBContentType.XS_CLASSIC, parsedModel.getDBContentType());
  }

  @Test
  public void parseHDBDDWithNoKeyAnnotation() throws Exception {
    XSKDataStructuresException exception = assertThrows(
        XSKDataStructuresException.class,
        () -> XSKDataStructureModelFactory.parseHdbdd("gstr2/NoKeyAnnSample.hdbdd", "")
    );
    assertEquals(
        "Failed to parse file: gstr2/NoKeyAnnSample.hdbdd. Error at line: 10 col: 1. Annotation nokey has been specified for entity with keys.",
        exception.getMessage());
  }

  @Test
  public void parseHDBDDWithGenerateTableTypeAnnotation() throws Exception {
    XSKDataStructureModel parsedModel = XSKDataStructureModelFactory.parseHdbdd("gstr2/GenerateTableTypeAnnotationSample.hdbdd", "");
    assertEquals(1, ((XSKDataStructureCdsModel) parsedModel).getTableTypeModels().size());

    XSKDataStructureHDBTableTypeModel tableTypeModel = ((XSKDataStructureCdsModel) parsedModel).getTableTypeModels().get(0);
    assertEquals("gstr2::GenerateTableTypeAnnotationSample.MyNestedStruct", tableTypeModel.getName());
    assertEquals("ADMIN", tableTypeModel.getSchema());
    assertEquals("name", tableTypeModel.getColumns().get(0).getName());
    assertEquals("NVARCHAR", tableTypeModel.getColumns().get(0).getType());
    assertEquals("nested.aNumber", tableTypeModel.getColumns().get(1).getName());
    assertEquals("INTEGER", tableTypeModel.getColumns().get(1).getType());
    assertEquals("nested.someText", tableTypeModel.getColumns().get(2).getName());
    assertEquals("NVARCHAR", tableTypeModel.getColumns().get(2).getType());
    assertEquals("nested.otherText", tableTypeModel.getColumns().get(3).getName());
    assertEquals("NVARCHAR", tableTypeModel.getColumns().get(3).getType());
  }

  @Test
  public void testParseHDBDDWithDateTimeFunctionDefaultValue() throws Exception {
    XSKDataStructureModel parsedModel = XSKDataStructureModelFactory.parseHdbdd("gstr2/DefaultValueWithDateTimeFunction.hdbdd", "");
    assertEquals(1, ((XSKDataStructureCdsModel) parsedModel).getTableModels().size());

    XSKDataStructureHDBTableModel tableModel = ((XSKDataStructureCdsModel) parsedModel).getTableModels().get(0);
    assertEquals("gstr2::DefaultValueWithDateTimeFunction.Orders", tableModel.getName());
    assertEquals("DBADMIN", tableModel.getSchema());
    assertEquals("Id", tableModel.getColumns().get(0).getName());
    assertEquals("NVARCHAR", tableModel.getColumns().get(0).getType());
    assertEquals("Date", tableModel.getColumns().get(1).getName());
    assertEquals("TIMESTAMP", tableModel.getColumns().get(1).getType());
    assertTrue(tableModel.getColumns().get(1).isDefaultValueDateTimeFunction());
  }

  @Test
  public void parseHDBDDWithTableTypeAnnotationColumn() throws Exception {
    XSKDataStructureModel parsedModel = XSKDataStructureModelFactory.parseHdbdd("gstr2/TableTypeColumn.hdbdd", "");
    assertEquals("COLUMN", ((XSKDataStructureCdsModel) parsedModel).getTableModels().get(0).getTableType());
    assertEquals("COLUMN", ((XSKDataStructureCdsModel) parsedModel).getTableModels().get(1).getTableType());
  }

  @Test
  public void parseHDBDDWithTableTypeAnnotationRow() throws Exception {
    XSKDataStructureModel parsedModel = XSKDataStructureModelFactory.parseHdbdd("gstr2/TableTypeRow.hdbdd", "");
    assertEquals("ROW", ((XSKDataStructureCdsModel) parsedModel).getTableModels().get(0).getTableType());
    assertEquals("ROW", ((XSKDataStructureCdsModel) parsedModel).getTableModels().get(1).getTableType());
  }

  /**
   * When the table type is null a COLUMN table is always created
   */
  @Test
  public void parseHDBDDWithNoTableTypeAnnotation() throws Exception {
    XSKDataStructureModel parsedModel = XSKDataStructureModelFactory.parseHdbdd("gstr2/NoTableType.hdbdd", "");
    assertEquals(((XSKDataStructureCdsModel) parsedModel).getTableModels().get(0).getTableType(), null);
  }
}