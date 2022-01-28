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
package com.sap.xsk.xsodata.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.sql.DataSource;

import org.apache.commons.io.IOUtils;
import org.eclipse.dirigible.database.persistence.model.PersistenceTableColumnModel;
import org.eclipse.dirigible.database.persistence.model.PersistenceTableModel;
import org.eclipse.dirigible.database.persistence.model.PersistenceTableRelationModel;
import org.eclipse.dirigible.database.sql.ISqlKeywords;
import org.eclipse.dirigible.database.sql.SqlFactory;
import org.eclipse.dirigible.engine.odata2.definition.ODataDefinition;
import org.eclipse.dirigible.engine.odata2.definition.ODataEntityDefinition;
import org.eclipse.dirigible.engine.odata2.definition.ODataHandlerMethods;
import org.eclipse.dirigible.engine.odata2.definition.ODataHandlerTypes;
import org.eclipse.dirigible.engine.odata2.transformers.DBMetadataUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.sap.xsk.exceptions.XSKArtifactParserException;
import com.sap.xsk.parser.xsodata.model.XSKHDBXSODATAEntity;
import com.sap.xsk.parser.xsodata.model.XSKHDBXSODATAEventType;
import com.sap.xsk.parser.xsodata.model.XSKHDBXSODATAHandlerMethod;
import com.sap.xsk.utils.XSKCommonsDBUtils;
import com.sap.xsk.xsodata.ds.model.XSKODataModel;
import com.sap.xsk.xsodata.ds.service.XSKOData2TransformerException;
import com.sap.xsk.xsodata.ds.service.XSKODataParser;

@RunWith(MockitoJUnitRunner.class)
public class XSKODataUtilsTest {

	@Mock(answer = Answers.RETURNS_DEEP_STUBS)
	private DBMetadataUtil dbMetadataUtil;

	@Mock
	private DataSource mockDataSource;

	@Test
	public void testConvertMultiplicityOneToMany() throws Exception {
		XSKODataParser parser = new XSKODataParser();
		String content = IOUtils.toString(this.getClass().getResourceAsStream("/entity_multiplicity_one_to_many.xsodata"), StandardCharsets.UTF_8);
		XSKODataModel xskoDataModel = parser.parseXSODataArtifact("np/entity_multiplicity_one_to_many.xsodata", content);

		PersistenceTableColumnModel column1 = new PersistenceTableColumnModel("COMPANY_ID", "Edm.Int32", false, true);
		PersistenceTableColumnModel column2 = new PersistenceTableColumnModel("EMPLOYEE_NUMBER", "Edm.Int32", false, true);
		PersistenceTableColumnModel column9 = new PersistenceTableColumnModel("ORDER_ID", "Edm.Int32", false, true);
		column9.setNullable(true);
		PersistenceTableModel model = new PersistenceTableModel("kneo.test.helloodata.CompositeKey::employee", Arrays.asList(column1, column2, column9), new ArrayList<>());
		model.setTableType(ISqlKeywords.METADATA_TABLE);
		when(dbMetadataUtil.getTableMetadata("kneo.test.helloodata.CompositeKey::employee", null)).thenReturn(model);

		PersistenceTableColumnModel column7 = new PersistenceTableColumnModel("ID", "Edm.Int32", false, true);
		PersistenceTableColumnModel column8 = new PersistenceTableColumnModel("FK_PHONE", "Edm.Int32", true, false);
		PersistenceTableRelationModel relPhone = new PersistenceTableRelationModel("kneo.test.helloodata.CompositeKey::address", "PHONES", "FK_PHONE", "ID", "CONSTRAINT_8C9F7", "CONSTRAINT_INDEX_E67");
		model = new PersistenceTableModel("kneo.test.helloodata.CompositeKey::address", Arrays.asList(column7, column8), Collections.singletonList(relPhone));
		model.setTableType(ISqlKeywords.METADATA_TABLE);
		when(dbMetadataUtil.getTableMetadata("kneo.test.helloodata.CompositeKey::address", null)).thenReturn(model);

		PersistenceTableColumnModel column3 = new PersistenceTableColumnModel("NUMBER", "Edm.Int32", false, true);
		PersistenceTableColumnModel column4 = new PersistenceTableColumnModel("FK_COMPANY_ID", "Edm.Int32", true, false);
		PersistenceTableColumnModel column5 = new PersistenceTableColumnModel("FK_EMPLOYEE_NUMBER", "Edm.Int32", true, false);
		PersistenceTableColumnModel column6 = new PersistenceTableColumnModel("FK_ADDRESS_ID", "Edm.Int32", true, false);
		PersistenceTableRelationModel rel = new PersistenceTableRelationModel("kneo.test.helloodata.CompositeKey::phones", "kneo.test.helloodata.CompositeKey::employee", "FK_COMPANY_ID", "COMPANY_ID", "CONSTRAINT_8C", "CONSTRAINT_INDEX_4");
		PersistenceTableRelationModel rel2 = new PersistenceTableRelationModel("kneo.test.helloodata.CompositeKey::phones", "kneo.test.helloodata.CompositeKey::employee", "FK_EMPLOYEE_NUMBER", "EMPLOYEE_NUMBER", "CONSTRAINT_8C9", "CONSTRAINT_INDEX_43");
		PersistenceTableRelationModel rel3 = new PersistenceTableRelationModel("kneo.test.helloodata.CompositeKey::phones", "kneo.test.helloodata.CompositeKey::address", "FK_ADDRESS_ID", "ID", "CONSTRAINT_8C9F", "CONSTRAINT_INDEX_E6");
		model = new PersistenceTableModel("kneo.test.helloodata.CompositeKey::phones", Arrays.asList(column3, column4, column5, column6), Arrays.asList(rel, rel2, rel3));
		model.setTableType(ISqlKeywords.METADATA_TABLE);
		when(dbMetadataUtil.getTableMetadata("kneo.test.helloodata.CompositeKey::phones", null)).thenReturn(model);

		ODataDefinition oDataDefinition = XSKODataUtils.convertXSKODataModelToODataDefinition(xskoDataModel, dbMetadataUtil);

		assertEquals("1", oDataDefinition.getAssociations().get(0).getFrom().getMultiplicity());
		assertEquals("*", oDataDefinition.getAssociations().get(0).getTo().getMultiplicity());
	}

	@Test
	public void testConvertWithoutSetOfPropAndLimitedExposedNavigations() throws Exception {
		XSKODataParser parser = new XSKODataParser();
		String content = IOUtils.toString(this.getClass().getResourceAsStream("/entity_without_set_of_prop.xsodata"), StandardCharsets.UTF_8);
		XSKODataModel xskoDataModel = parser.parseXSODataArtifact("np/entity_without_set_of_prop.xsodata", content);

		PersistenceTableColumnModel column1 = new PersistenceTableColumnModel("COMPANY_ID", "Edm.Int32", false, true);
		PersistenceTableColumnModel column2 = new PersistenceTableColumnModel("EMPLOYEE_NUMBER", "Edm.Int32", false, true);
		PersistenceTableColumnModel column9 = new PersistenceTableColumnModel("ORDER_ID", "Edm.Int32", false, true);
		column9.setNullable(true);
		PersistenceTableModel model = new PersistenceTableModel("kneo.test.helloodata.CompositeKey::employee", Arrays.asList(column1, column2, column9), new ArrayList<>());
		model.setTableType(ISqlKeywords.METADATA_TABLE);
		when(dbMetadataUtil.getTableMetadata("kneo.test.helloodata.CompositeKey::employee", null)).thenReturn(model);

		PersistenceTableColumnModel column7 = new PersistenceTableColumnModel("ID", "Edm.Int32", false, true);
		PersistenceTableColumnModel column8 = new PersistenceTableColumnModel("FK_PHONE", "Edm.Int32", true, false);
		PersistenceTableRelationModel relPhone = new PersistenceTableRelationModel("kneo.test.helloodata.CompositeKey::address", "PHONES", "FK_PHONE", "ID", "CONSTRAINT_8C9F7", "CONSTRAINT_INDEX_E67");
		model = new PersistenceTableModel("kneo.test.helloodata.CompositeKey::address", Arrays.asList(column7, column8), Collections.singletonList(relPhone));
		model.setTableType(ISqlKeywords.METADATA_TABLE);
		when(dbMetadataUtil.getTableMetadata("kneo.test.helloodata.CompositeKey::address", null)).thenReturn(model);

		PersistenceTableColumnModel column3 = new PersistenceTableColumnModel("NUMBER", "Edm.Int32", false, true);
		PersistenceTableColumnModel column4 = new PersistenceTableColumnModel("FK_COMPANY_ID", "Edm.Int32", true, false);
		PersistenceTableColumnModel column5 = new PersistenceTableColumnModel("FK_EMPLOYEE_NUMBER", "Edm.Int32", true, false);
		PersistenceTableColumnModel column6 = new PersistenceTableColumnModel("FK_ADDRESS_ID", "Edm.Int32", true, false);
		PersistenceTableRelationModel rel = new PersistenceTableRelationModel("kneo.test.helloodata.CompositeKey::phones", "kneo.test.helloodata.CompositeKey::employee", "FK_COMPANY_ID", "COMPANY_ID", "CONSTRAINT_8C", "CONSTRAINT_INDEX_4");
		PersistenceTableRelationModel rel2 = new PersistenceTableRelationModel("kneo.test.helloodata.CompositeKey::phones", "kneo.test.helloodata.CompositeKey::employee", "FK_EMPLOYEE_NUMBER", "EMPLOYEE_NUMBER", "CONSTRAINT_8C9", "CONSTRAINT_INDEX_43");
		PersistenceTableRelationModel rel3 = new PersistenceTableRelationModel("kneo.test.helloodata.CompositeKey::phones", "kneo.test.helloodata.CompositeKey::address", "FK_ADDRESS_ID", "ID", "CONSTRAINT_8C9F", "CONSTRAINT_INDEX_E6");
		model = new PersistenceTableModel("kneo.test.helloodata.CompositeKey::phones", Arrays.asList(column3, column4, column5, column6), Arrays.asList(rel, rel2, rel3));
		model.setTableType(ISqlKeywords.METADATA_TABLE);
		when(dbMetadataUtil.getTableMetadata("kneo.test.helloodata.CompositeKey::phones", null)).thenReturn(model);

		ODataDefinition oDataDefinition = XSKODataUtils.convertXSKODataModelToODataDefinition(xskoDataModel, dbMetadataUtil);

		assertEquals(2, oDataDefinition.getEntities().size());
		assertEquals(1, oDataDefinition.getAssociations().size());
		assertEquals("np", oDataDefinition.getNamespace());
		assertNull(oDataDefinition.getCreatedAt());
		assertNull(oDataDefinition.getHash());
		assertEquals("np/entity_without_set_of_prop.xsodata", oDataDefinition.getLocation());

		ODataEntityDefinition employeeEntity = oDataDefinition.getEntities().get(0);
		assertEquals("Employees", employeeEntity.getName());
		assertEquals("Employees", employeeEntity.getAlias());
		assertEquals("kneo.test.helloodata.CompositeKey::employee", employeeEntity.getTable());
		assertEquals(2, employeeEntity.getProperties().size());
		assertEquals("COMPANY_ID", employeeEntity.getProperties().get(0).getName());
		assertEquals("COMPANY_ID", employeeEntity.getProperties().get(0).getColumn());
		assertFalse(employeeEntity.getProperties().get(0).isNullable());
		assertEquals("Edm.Int32", employeeEntity.getProperties().get(0).getType());
		assertEquals("EMPLOYEE_NUMBER", employeeEntity.getProperties().get(1).getName());
		assertEquals("EMPLOYEE_NUMBER", employeeEntity.getProperties().get(1).getColumn());
		assertFalse(employeeEntity.getProperties().get(1).isNullable());
		assertEquals("Edm.Int32", employeeEntity.getProperties().get(1).getType());
		assertEquals(0, employeeEntity.getHandlers().size());
		assertEquals(1, employeeEntity.getNavigations().size());
		assertEquals("HisPhones", employeeEntity.getNavigations().get(0).getName());
		assertEquals("Employees_Phones", employeeEntity.getNavigations().get(0).getAssociation());

		ODataEntityDefinition phoneEntity = oDataDefinition.getEntities().get(1);
		assertEquals("Phones", phoneEntity.getName());
		assertEquals("Phones", phoneEntity.getAlias());
		assertEquals("kneo.test.helloodata.CompositeKey::phones", phoneEntity.getTable());
		assertEquals(0, phoneEntity.getProperties().size());
		assertEquals(0, phoneEntity.getHandlers().size());
		assertEquals(0, phoneEntity.getNavigations().size());

		assertEquals("Employees_Phones", oDataDefinition.getAssociations().get(0).getName());
		assertEquals("Employees", oDataDefinition.getAssociations().get(0).getFrom().getEntity());
		assertNull(oDataDefinition.getAssociations().get(0).getFrom().getColumn());
		assertEquals("1", oDataDefinition.getAssociations().get(0).getFrom().getMultiplicity());
		assertEquals(2, oDataDefinition.getAssociations().get(0).getFrom().getProperties().size());
		assertEquals("COMPANY_ID", oDataDefinition.getAssociations().get(0).getFrom().getProperties().get(0));
		assertEquals("EMPLOYEE_NUMBER", oDataDefinition.getAssociations().get(0).getFrom().getProperties().get(1));

		assertEquals("Phones", oDataDefinition.getAssociations().get(0).getTo().getEntity());
		assertNull(oDataDefinition.getAssociations().get(0).getTo().getColumn());
		assertEquals("*", oDataDefinition.getAssociations().get(0).getTo().getMultiplicity());
		assertEquals(2, oDataDefinition.getAssociations().get(0).getTo().getProperties().size());
		assertEquals("FK_COMPANY_ID", oDataDefinition.getAssociations().get(0).getTo().getProperties().get(0));
		assertEquals("FK_EMPLOYEE_NUMBER", oDataDefinition.getAssociations().get(0).getTo().getProperties().get(1));
	}

	@Test
	public void testConvertWithSetOfPropAndLimitedExposedNavigations() throws Exception {
		XSKODataParser parser = new XSKODataParser();
		String content = IOUtils.toString(this.getClass().getResourceAsStream("/entity_with_set_of_prop.xsodata"), StandardCharsets.UTF_8);
		XSKODataModel xskoDataModel = parser.parseXSODataArtifact("np/entity_with_set_of_prop.xsodata", content);

		PersistenceTableColumnModel column1 = new PersistenceTableColumnModel("COMPANY_ID", "Edm.Int32", false, true);
		PersistenceTableColumnModel column2 = new PersistenceTableColumnModel("EMPLOYEE_NUMBER", "Edm.Int32", false, true);
		PersistenceTableColumnModel column9 = new PersistenceTableColumnModel("ORDER_ID", "Edm.Int32", true, false);
		column9.setNullable(true);
		PersistenceTableColumnModel column10 = new PersistenceTableColumnModel("ORDER_ID_2", "Edm.Int32", true, false);
		column10.setNullable(true);
		PersistenceTableModel model = new PersistenceTableModel("kneo.test.helloodata.CompositeKey::employee", Arrays.asList(column1, column2, column9, column10), new ArrayList<>());
		model.setTableType(ISqlKeywords.METADATA_TABLE);
		when(dbMetadataUtil.getTableMetadata("kneo.test.helloodata.CompositeKey::employee", null)).thenReturn(model);

		PersistenceTableColumnModel column7 = new PersistenceTableColumnModel("ID", "Edm.Int32", false, true);
		PersistenceTableColumnModel column8 = new PersistenceTableColumnModel("FK_PHONE", "Edm.Int32", true, false);
		PersistenceTableRelationModel relPhone = new PersistenceTableRelationModel("kneo.test.helloodata.CompositeKey::address", "PHONES", "FK_PHONE", "ID", "CONSTRAINT_8C9F7", "CONSTRAINT_INDEX_E67");
		model = new PersistenceTableModel("kneo.test.helloodata.CompositeKey::address", Arrays.asList(column7, column8), Collections.singletonList(relPhone));
		model.setTableType(ISqlKeywords.METADATA_TABLE);
		when(dbMetadataUtil.getTableMetadata("kneo.test.helloodata.CompositeKey::address", null)).thenReturn(model);

		PersistenceTableColumnModel column3 = new PersistenceTableColumnModel("NUMBER", "Edm.Int32", false, true);
		PersistenceTableColumnModel column4 = new PersistenceTableColumnModel("FK_COMPANY_ID", "Edm.Int32", true, false);
		PersistenceTableColumnModel column5 = new PersistenceTableColumnModel("FK_EMPLOYEE_NUMBER", "Edm.Int32", true, false);
		PersistenceTableColumnModel column6 = new PersistenceTableColumnModel("FK_ADDRESS_ID", "Edm.Int32", true, false);
		PersistenceTableRelationModel rel = new PersistenceTableRelationModel("kneo.test.helloodata.CompositeKey::phones", "kneo.test.helloodata.CompositeKey::employee", "FK_COMPANY_ID", "COMPANY_ID", "CONSTRAINT_8C", "CONSTRAINT_INDEX_4");
		PersistenceTableRelationModel rel2 = new PersistenceTableRelationModel("kneo.test.helloodata.CompositeKey::phones", "kneo.test.helloodata.CompositeKey::employee", "FK_EMPLOYEE_NUMBER", "EMPLOYEE_NUMBER", "CONSTRAINT_8C9", "CONSTRAINT_INDEX_43");
		PersistenceTableRelationModel rel3 = new PersistenceTableRelationModel("kneo.test.helloodata.CompositeKey::phones", "kneo.test.helloodata.CompositeKey::address", "FK_ADDRESS_ID", "ID", "CONSTRAINT_8C9F", "CONSTRAINT_INDEX_E6");
		model = new PersistenceTableModel("kneo.test.helloodata.CompositeKey::phones", Arrays.asList(column3, column4, column5, column6), Arrays.asList(rel, rel2, rel3));
		model.setTableType(ISqlKeywords.METADATA_TABLE);
		when(dbMetadataUtil.getTableMetadata("kneo.test.helloodata.CompositeKey::phones", null)).thenReturn(model);

		ODataDefinition oDataDefinition = XSKODataUtils.convertXSKODataModelToODataDefinition(xskoDataModel, dbMetadataUtil);
		assertEquals(2, oDataDefinition.getEntities().size());
		assertEquals(1, oDataDefinition.getAssociations().size());
		assertEquals("np", oDataDefinition.getNamespace());
		assertNull(oDataDefinition.getCreatedAt());
		assertNull(oDataDefinition.getHash());
		assertEquals("np/entity_with_set_of_prop.xsodata", oDataDefinition.getLocation());

		ODataEntityDefinition employeeEntity = oDataDefinition.getEntities().get(0);
		assertEquals("Employees", employeeEntity.getName());
		assertEquals("Employees", employeeEntity.getAlias());
		assertEquals("kneo.test.helloodata.CompositeKey::employee", employeeEntity.getTable());

		assertEquals(3, employeeEntity.getProperties().size());
		assertEquals("COMPANY_ID", employeeEntity.getProperties().get(0).getName());
		assertEquals("COMPANY_ID", employeeEntity.getProperties().get(0).getColumn());
		assertFalse(employeeEntity.getProperties().get(0).isNullable());
		assertEquals("Edm.Int32", employeeEntity.getProperties().get(0).getType());
		assertEquals("EMPLOYEE_NUMBER", employeeEntity.getProperties().get(1).getName());
		assertEquals("EMPLOYEE_NUMBER", employeeEntity.getProperties().get(1).getColumn());
		assertFalse(employeeEntity.getProperties().get(1).isNullable());
		assertEquals("Edm.Int32", employeeEntity.getProperties().get(1).getType());
		assertEquals("ORDER_ID", employeeEntity.getProperties().get(2).getName());
		assertEquals("ORDER_ID", employeeEntity.getProperties().get(2).getColumn());
		assertTrue(employeeEntity.getProperties().get(2).isNullable());
		assertEquals("Edm.Int32", employeeEntity.getProperties().get(2).getType());

		assertEquals(0, employeeEntity.getHandlers().size());
		assertEquals(1, employeeEntity.getNavigations().size());
		assertEquals("HisPhones", employeeEntity.getNavigations().get(0).getName());
		assertEquals("Employees_Phones", employeeEntity.getNavigations().get(0).getAssociation());

		ODataEntityDefinition phoneEntity = oDataDefinition.getEntities().get(1);
		assertEquals("Phones", phoneEntity.getName());
		assertEquals("Phones", phoneEntity.getAlias());
		assertEquals("kneo.test.helloodata.CompositeKey::phones", phoneEntity.getTable());
		assertEquals(0, phoneEntity.getProperties().size());
		assertEquals(0, phoneEntity.getHandlers().size());
		assertEquals(0, phoneEntity.getNavigations().size());

		assertEquals("Employees_Phones", oDataDefinition.getAssociations().get(0).getName());
		assertEquals("Employees", oDataDefinition.getAssociations().get(0).getFrom().getEntity());
		assertNull(oDataDefinition.getAssociations().get(0).getFrom().getColumn());
		assertEquals("1", oDataDefinition.getAssociations().get(0).getFrom().getMultiplicity());
		assertEquals(2, oDataDefinition.getAssociations().get(0).getFrom().getProperties().size());
		assertEquals("COMPANY_ID", oDataDefinition.getAssociations().get(0).getFrom().getProperties().get(0));
		assertEquals("EMPLOYEE_NUMBER", oDataDefinition.getAssociations().get(0).getFrom().getProperties().get(1));

		assertEquals("Phones", oDataDefinition.getAssociations().get(0).getTo().getEntity());
		assertNull(oDataDefinition.getAssociations().get(0).getTo().getColumn());
		assertEquals("*", oDataDefinition.getAssociations().get(0).getTo().getMultiplicity());
		assertEquals(2, oDataDefinition.getAssociations().get(0).getTo().getProperties().size());
		assertEquals("FK_COMPANY_ID", oDataDefinition.getAssociations().get(0).getTo().getProperties().get(0));
		assertEquals("FK_EMPLOYEE_NUMBER", oDataDefinition.getAssociations().get(0).getTo().getProperties().get(1));
	}

	@Test
	public void testConvertOfEvents() throws Exception {
		XSKODataParser parser = new XSKODataParser();
		String content = IOUtils.toString(this.getClass().getResourceAsStream("/entity_with_events.xsodata"), StandardCharsets.UTF_8);
		XSKODataModel xskoDataModel = parser.parseXSODataArtifact("np/entity_with_events.xsodata", content);

    mockTableMetadataInvocations("sample.odata::table1");
    mockTableMetadataInvocations("sample.odata::table2");
    mockTableMetadataInvocations("sample.odata::table3");
    mockTableMetadataInvocations("sample.odata::table4");

    ODataDefinition oDataDefinition = XSKODataUtils.convertXSKODataModelToODataDefinition(xskoDataModel, dbMetadataUtil);
		assertEquals(4, oDataDefinition.getEntities().size());

		ODataEntityDefinition entity1 = oDataDefinition.getEntities().get(0);
		assertEquals(3, entity1.getHandlers().size());
		assertEquals(ODataHandlerMethods.update.name(), entity1.getHandlers().get(0).getMethod());
		assertEquals(ODataHandlerTypes.before.name(), entity1.getHandlers().get(0).getType());
		assertEquals("sample.odata::beforeMethod", entity1.getHandlers().get(0).getHandler());
		assertEquals(ODataHandlerMethods.delete.name(), entity1.getHandlers().get(1).getMethod());
		assertEquals(ODataHandlerTypes.after.name(), entity1.getHandlers().get(1).getType());
		assertEquals("sample.odata::afterMethod", entity1.getHandlers().get(1).getHandler());
		assertEquals(ODataHandlerMethods.create.name(), entity1.getHandlers().get(2).getMethod());
		assertEquals(ODataHandlerTypes.forbid.name(), entity1.getHandlers().get(2).getType());
		assertNull(entity1.getHandlers().get(2).getHandler());
		assertEquals("false", entity1.getAnnotationsEntitySet().get(XSKHDBXSODATAHandlerMethod.CREATE.getOdataSAPAnnotation()));
		assertNull(entity1.getAnnotationsEntitySet().get(XSKHDBXSODATAHandlerMethod.UPDATE.getOdataSAPAnnotation()));
		assertNull(entity1.getAnnotationsEntitySet().get(XSKHDBXSODATAHandlerMethod.DELETE.getOdataSAPAnnotation()));

		ODataEntityDefinition entity2 = oDataDefinition.getEntities().get(1);
		assertEquals(4, entity2.getHandlers().size());
		assertEquals(ODataHandlerMethods.create.name(), entity2.getHandlers().get(0).getMethod());
		assertEquals(ODataHandlerTypes.after.name(), entity2.getHandlers().get(0).getType());
		assertEquals("sample.odata::afterMethod", entity2.getHandlers().get(0).getHandler());
		assertEquals(ODataHandlerMethods.create.name(), entity2.getHandlers().get(1).getMethod());
		assertEquals(ODataHandlerTypes.on.name(), entity2.getHandlers().get(1).getType());
		assertEquals("sample.odata::createMethod", entity2.getHandlers().get(1).getHandler());
		assertEquals(ODataHandlerMethods.update.name(), entity2.getHandlers().get(2).getMethod());
		assertEquals(ODataHandlerTypes.on.name(), entity2.getHandlers().get(2).getType());
		assertEquals("sample.odata::updateMethod", entity2.getHandlers().get(2).getHandler());
		assertEquals(ODataHandlerMethods.delete.name(), entity2.getHandlers().get(3).getMethod());
		assertEquals(ODataHandlerTypes.on.name(), entity2.getHandlers().get(3).getType());
		assertEquals("sample.odata::deleteMethod", entity2.getHandlers().get(3).getHandler());
		assertNull(entity2.getAnnotationsEntitySet().get(XSKHDBXSODATAHandlerMethod.CREATE.getOdataSAPAnnotation()));
		assertNull(entity2.getAnnotationsEntitySet().get(XSKHDBXSODATAHandlerMethod.UPDATE.getOdataSAPAnnotation()));
		assertNull(entity2.getAnnotationsEntitySet().get(XSKHDBXSODATAHandlerMethod.DELETE.getOdataSAPAnnotation()));

		ODataEntityDefinition entity3 = oDataDefinition.getEntities().get(2);
		assertEquals(2, entity3.getHandlers().size());
		assertEquals(ODataHandlerMethods.create.name(), entity3.getHandlers().get(0).getMethod());
		assertEquals(ODataHandlerTypes.on.name(), entity3.getHandlers().get(0).getType());
		assertEquals("sample.odata::createMethod", entity3.getHandlers().get(0).getHandler());
		assertEquals(ODataHandlerMethods.delete.name(), entity3.getHandlers().get(1).getMethod());
		assertEquals(ODataHandlerTypes.forbid.name(), entity3.getHandlers().get(1).getType());
		assertNull(entity3.getHandlers().get(1).getHandler());
		assertNull(entity3.getAnnotationsEntitySet().get(XSKHDBXSODATAHandlerMethod.CREATE.getOdataSAPAnnotation()));
		assertNull(entity3.getAnnotationsEntitySet().get(XSKHDBXSODATAHandlerMethod.UPDATE.getOdataSAPAnnotation()));
		assertEquals("false", entity3.getAnnotationsEntitySet().get(XSKHDBXSODATAHandlerMethod.DELETE.getOdataSAPAnnotation()));

		ODataEntityDefinition entity4 = oDataDefinition.getEntities().get(3);
		assertEquals(3, entity4.getHandlers().size());
		assertEquals(ODataHandlerMethods.create.name(), entity4.getHandlers().get(0).getMethod());
		assertEquals(ODataHandlerTypes.forbid.name(), entity4.getHandlers().get(0).getType());
		assertNull(entity4.getHandlers().get(0).getHandler());
		assertEquals(ODataHandlerMethods.update.name(), entity4.getHandlers().get(1).getMethod());
		assertEquals(ODataHandlerTypes.forbid.name(), entity4.getHandlers().get(1).getType());
		assertNull(entity4.getHandlers().get(1).getHandler());
		assertEquals(ODataHandlerMethods.delete.name(), entity4.getHandlers().get(2).getMethod());
		assertEquals(ODataHandlerTypes.forbid.name(), entity4.getHandlers().get(2).getType());
		assertNull(entity4.getHandlers().get(2).getHandler());
		assertEquals("false", entity4.getAnnotationsEntitySet().get(XSKHDBXSODATAHandlerMethod.CREATE.getOdataSAPAnnotation()));
		assertEquals("false", entity4.getAnnotationsEntitySet().get(XSKHDBXSODATAHandlerMethod.UPDATE.getOdataSAPAnnotation()));
		assertEquals("false", entity4.getAnnotationsEntitySet().get(XSKHDBXSODATAHandlerMethod.DELETE.getOdataSAPAnnotation()));
	}

  private void mockTableMetadataInvocations(String tableName) throws SQLException {
    PersistenceTableModel model = new PersistenceTableModel(tableName, null, null);
    when(dbMetadataUtil.getTableMetadata(tableName, null)).thenReturn(model);
  }

  @Test(expected = XSKOData2TransformerException.class)
	public void testValidateEdmMultiplicityFailed() {
		XSKODataUtils.validateEdmMultiplicity("1..*", "ass_name");
	}

	@Test
	public void testValidateEdmMultiplicitySuccessfully() {
		XSKODataUtils.validateEdmMultiplicity("0..1", "ass_name");
		XSKODataUtils.validateEdmMultiplicity("*", "ass_name");
		XSKODataUtils.validateEdmMultiplicity("1", "ass_name");
	}

	@Test
	public void testValidateHandlerTypeFailed() {
		assertFalse(XSKODataUtils.validateHandlerType(XSKHDBXSODATAEventType.POSTCOMMIT));
		assertFalse(XSKODataUtils.validateHandlerType(XSKHDBXSODATAEventType.PRECOMMIT));
	}

	@Test
	public void testValidateHandlerTypeSuccessfully() {
		assertTrue(XSKODataUtils.validateHandlerType(XSKHDBXSODATAEventType.AFTER));
		assertTrue(XSKODataUtils.validateHandlerType(XSKHDBXSODATAEventType.BEFORE));
	}

	@Test
	public void testCalcView() throws Exception {
		XSKODataParser parser = new XSKODataParser();
		String content = IOUtils.toString(this.getClass().getResourceAsStream("/entity_calc_view.xsodata"), StandardCharsets.UTF_8);
		XSKODataModel xskoDataModel = parser.parseXSODataArtifact("np/entity_calc_view.xsodata", content);

		PersistenceTableColumnModel column1 = new PersistenceTableColumnModel("COLUMN1", "Edm.Int32", true, false);
		PersistenceTableColumnModel column2 = new PersistenceTableColumnModel("COLUMN2", "Edm.Int32", true, false);
		PersistenceTableColumnModel column3 = new PersistenceTableColumnModel("COLUMN3", "Edm.Int32", true, false);
		PersistenceTableModel model = new PersistenceTableModel("kneo.test.calcviews::calc", Arrays.asList(column1, column2, column3), new ArrayList<>());
		model.setTableType("CALC VIEW");
		when(dbMetadataUtil.getTableMetadata("kneo.test.calcviews::calc", null)).thenReturn(model);

		ODataDefinition oDataDefinition = XSKODataUtils.convertXSKODataModelToODataDefinition(xskoDataModel, dbMetadataUtil);

		assertEquals(3, oDataDefinition.getEntities().get(0).getProperties().size());
		assertEquals(1, oDataDefinition.getEntities().get(1).getProperties().size());
		assertEquals(2, oDataDefinition.getEntities().get(2).getProperties().size());
	}

	@Test
	public void testSynonym() throws Exception {
		try (MockedStatic<XSKCommonsDBUtils> xskCommonsDBUtils = Mockito.mockStatic(XSKCommonsDBUtils.class)) {
			
			XSKODataParser parser = new XSKODataParser();
			String content = IOUtils.toString(this.getClass().getResourceAsStream("/entity_synonym.xsodata"), StandardCharsets.UTF_8);
			XSKODataModel xskoDataModel = parser.parseXSODataArtifact("np/entity_synonym.xsodata", content);
			
			PersistenceTableColumnModel column1 = new PersistenceTableColumnModel("COLUMN1", "Edm.Int32", true, false);
			PersistenceTableColumnModel column2 = new PersistenceTableColumnModel("COLUMN2", "Edm.Int32", true, false);
			PersistenceTableColumnModel column3 = new PersistenceTableColumnModel("COLUMN3", "Edm.Int32", true, false);
			
			PersistenceTableModel calcViewModel = new PersistenceTableModel("kneo.test.calcviews::calc", Arrays.asList(column1, column2, column3), new ArrayList<>());
			calcViewModel.setTableType(ISqlKeywords.METADATA_CALC_VIEW);
			
			PersistenceTableModel synonymModel = new PersistenceTableModel("TestCalcView", Arrays.asList(column1, column2, column3), new ArrayList<>());
			synonymModel.setTableType(ISqlKeywords.METADATA_SYNONYM);
			
			PersistenceTableModel synonymTargetObjectMetadata = new PersistenceTableModel();
			synonymTargetObjectMetadata.setTableName("kneo.test.calcviews::calc");
			synonymTargetObjectMetadata.setSchemaName(null);
			synonymTargetObjectMetadata.setTableType(ISqlKeywords.METADATA_CALC_VIEW);
			
			when(dbMetadataUtil.getTableMetadata("TestCalcView", null)).thenReturn(synonymModel);
			when(dbMetadataUtil.getTableMetadata("kneo.test.calcviews::calc", null)).thenReturn(calcViewModel);
			xskCommonsDBUtils.when(() -> XSKCommonsDBUtils.getSynonymTargetObjectMetadata(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(synonymTargetObjectMetadata);
//			doReturn(synonymTargetObjectMetadata).when(XSKCommonsDBUtils.class, "getSynonymTargetObjectMetadata", null, synonymModel.getTableName(), null);
//          doReturn(synonymTargetObjectMetadata).when(XSKCommonsDBUtils.class, "getSynonymTargetObjectMetadata", mockDataSource, anyString(), null);
			
			ODataDefinition oDataDefinition = XSKODataUtils.convertXSKODataModelToODataDefinition(xskoDataModel, dbMetadataUtil);
			
			assertEquals(3, oDataDefinition.getEntities().get(0).getProperties().size());
			assertEquals(1, oDataDefinition.getEntities().get(1).getProperties().size());
			assertEquals(2, oDataDefinition.getEntities().get(2).getProperties().size());
		}

	}

	@Test
	public void testProperNavigationConstruction() throws IOException, XSKArtifactParserException, SQLException {
		XSKODataParser parser = new XSKODataParser();
		String content = IOUtils.toString(this.getClass().getResourceAsStream("/entity_with_3_navigations.xsodata"), StandardCharsets.UTF_8);
		XSKODataModel xskoDataModel = parser.parseXSODataArtifact("np/entity_with_3_navigations.xsodata", content);

		ODataDefinition oDataDefinitionModel = new ODataDefinition();
		for (XSKHDBXSODATAEntity entity : xskoDataModel.getService().getEntities()) {
			ODataEntityDefinition oDataEntityDefinition = new ODataEntityDefinition();
			entity.getNavigates().forEach(XSKODataUtils.processNavigation(xskoDataModel, oDataDefinitionModel, oDataEntityDefinition));

			assertEquals("Unexpected number of navigations for entity: " + entity.getAlias(), entity.getNavigates().size(), oDataEntityDefinition.getNavigations().size());
		}

	}
}
