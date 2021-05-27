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

import com.sap.xsk.xsodata.ds.model.XSKODataModel;
import com.sap.xsk.xsodata.ds.service.XSKODataParser;
import com.sap.xsk.xsodata.utils.XSKODataUtils;
import org.eclipse.dirigible.database.persistence.model.PersistenceTableColumnModel;
import org.eclipse.dirigible.database.persistence.model.PersistenceTableModel;
import org.eclipse.dirigible.database.persistence.model.PersistenceTableRelationModel;
import org.eclipse.dirigible.engine.odata2.definition.ODataDefinition;
import org.eclipse.dirigible.engine.odata2.transformers.DBMetadataUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class XSKODataUtilsTest {

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private DBMetadataUtil dbMetadataUtil;

    @Test
    public void testConvertWithoutSetOfPropAndLimitedExposedNavigations() throws Exception {
        XSKODataParser parser = new XSKODataParser();
        String content = org.apache.commons.io.IOUtils
                .toString(XSKODataUtilsTest.class.getResourceAsStream("/entity_without_set_of_prop.xsodata"), StandardCharsets.UTF_8);
        XSKODataModel xskoDataModel = parser.parseXSODataArtifact("np/entity_without_set_of_prop.xsodata", content);

        PersistenceTableColumnModel column1 = new PersistenceTableColumnModel("COMPANY_ID", "Edm.Int32", true);
        PersistenceTableColumnModel column2 = new PersistenceTableColumnModel("EMPLOYEE_NUMBER", "Edm.Int32", true);
        PersistenceTableColumnModel column9 = new PersistenceTableColumnModel("ORDER_ID", "Edm.Int32", true);
        column9.setNullable(true);
        PersistenceTableModel model = new PersistenceTableModel("kneo.test.helloodata.CompositeKey::employee", Arrays.asList(column1, column2, column9), new ArrayList<>());
        when(dbMetadataUtil.getTableMetadata("kneo.test.helloodata.CompositeKey::employee")).thenReturn(model);

        PersistenceTableColumnModel column7 = new PersistenceTableColumnModel("ID", "Edm.Int32", true);
        PersistenceTableColumnModel column8 = new PersistenceTableColumnModel("FK_PHONE", "Edm.Int32", false);
        PersistenceTableRelationModel relPhone = new PersistenceTableRelationModel("kneo.test.helloodata.CompositeKey::address", "PHONES", "FK_PHONE", "ID", "CONSTRAINT_8C9F7", "CONSTRAINT_INDEX_E67");
        model = new PersistenceTableModel("kneo.test.helloodata.CompositeKey::address", Arrays.asList(column7, column8), Collections.singletonList(relPhone));
        when(dbMetadataUtil.getTableMetadata("kneo.test.helloodata.CompositeKey::address")).thenReturn(model);

        PersistenceTableColumnModel column3 = new PersistenceTableColumnModel("NUMBER", "Edm.Int32", true);
        PersistenceTableColumnModel column4 = new PersistenceTableColumnModel("FK_COMPANY_ID", "Edm.Int32", false);
        PersistenceTableColumnModel column5 = new PersistenceTableColumnModel("FK_EMPLOYEE_NUMBER", "Edm.Int32", false);
        PersistenceTableColumnModel column6 = new PersistenceTableColumnModel("FK_ADDRESS_ID", "Edm.Int32", false);
        PersistenceTableRelationModel rel = new PersistenceTableRelationModel("kneo.test.helloodata.CompositeKey::phones", "kneo.test.helloodata.CompositeKey::employee", "FK_COMPANY_ID", "COMPANY_ID", "CONSTRAINT_8C", "CONSTRAINT_INDEX_4");
        PersistenceTableRelationModel rel2 = new PersistenceTableRelationModel("kneo.test.helloodata.CompositeKey::phones", "kneo.test.helloodata.CompositeKey::employee", "FK_EMPLOYEE_NUMBER", "EMPLOYEE_NUMBER", "CONSTRAINT_8C9", "CONSTRAINT_INDEX_43");
        PersistenceTableRelationModel rel3 = new PersistenceTableRelationModel("kneo.test.helloodata.CompositeKey::phones", "kneo.test.helloodata.CompositeKey::address", "FK_ADDRESS_ID", "ID", "CONSTRAINT_8C9F", "CONSTRAINT_INDEX_E6");
        model = new PersistenceTableModel("kneo.test.helloodata.CompositeKey::phones", Arrays.asList(column3, column4, column5, column6), Arrays.asList(rel, rel2, rel3));
        when(dbMetadataUtil.getTableMetadata("kneo.test.helloodata.CompositeKey::phones")).thenReturn(model);

        ODataDefinition oDataDefinition = XSKODataUtils.convertXSKODataModelToODataDefinition(xskoDataModel, dbMetadataUtil);

        assertEquals(2, oDataDefinition.getEntities().size());
        assertEquals(1, oDataDefinition.getAssociations().size());
        assertEquals("np", oDataDefinition.getNamespace());
        assertNull(oDataDefinition.getCreatedAt());
        assertNull(oDataDefinition.getHash());
        assertNull(oDataDefinition.getLocation());

        assertEquals("Employees", oDataDefinition.getEntities().get(0).getName());
        assertEquals("Employees", oDataDefinition.getEntities().get(0).getAlias());
        assertEquals("kneo.test.helloodata.CompositeKey::employee", oDataDefinition.getEntities().get(0).getTable());
        assertEquals(2, oDataDefinition.getEntities().get(0).getProperties().size());
        assertEquals("COMPANY_ID", oDataDefinition.getEntities().get(0).getProperties().get(0).getName());
        assertEquals("COMPANY_ID", oDataDefinition.getEntities().get(0).getProperties().get(0).getColumn());
        assertFalse(oDataDefinition.getEntities().get(0).getProperties().get(0).isNullable());
        assertEquals("Edm.Int32", oDataDefinition.getEntities().get(0).getProperties().get(0).getType());
        assertEquals("EMPLOYEE_NUMBER", oDataDefinition.getEntities().get(0).getProperties().get(1).getName());
        assertEquals("EMPLOYEE_NUMBER", oDataDefinition.getEntities().get(0).getProperties().get(1).getColumn());
        assertFalse(oDataDefinition.getEntities().get(0).getProperties().get(1).isNullable());
        assertEquals("Edm.Int32", oDataDefinition.getEntities().get(0).getProperties().get(1).getType());
        assertEquals(0, oDataDefinition.getEntities().get(0).getHandlers().size());
        assertEquals(1, oDataDefinition.getEntities().get(0).getNavigations().size());
        assertEquals("HisPhones", oDataDefinition.getEntities().get(0).getNavigations().get(0).getName());
        assertEquals("Employees_Phones", oDataDefinition.getEntities().get(0).getNavigations().get(0).getAssociation());

        assertEquals("Phones", oDataDefinition.getEntities().get(1).getName());
        assertEquals("Phones", oDataDefinition.getEntities().get(1).getAlias());
        assertEquals("kneo.test.helloodata.CompositeKey::phones", oDataDefinition.getEntities().get(1).getTable());
        assertEquals(0, oDataDefinition.getEntities().get(1).getProperties().size());
        assertEquals(0, oDataDefinition.getEntities().get(1).getHandlers().size());
        assertEquals(0, oDataDefinition.getEntities().get(1).getNavigations().size());

        assertEquals("Employees_Phones", oDataDefinition.getAssociations().get(0).getName());
        assertEquals("Employees", oDataDefinition.getAssociations().get(0).getFrom().getEntity());
        assertNull(oDataDefinition.getAssociations().get(0).getFrom().getColumn());
        assertEquals("1", oDataDefinition.getAssociations().get(0).getFrom().getMultiplicity());
        assertEquals(2, oDataDefinition.getAssociations().get(0).getFrom().getProperty().size());
        assertEquals("COMPANY_ID", oDataDefinition.getAssociations().get(0).getFrom().getProperty().get(0));
        assertEquals("EMPLOYEE_NUMBER", oDataDefinition.getAssociations().get(0).getFrom().getProperty().get(1));

        assertEquals("Phones", oDataDefinition.getAssociations().get(0).getTo().getEntity());
        assertNull(oDataDefinition.getAssociations().get(0).getTo().getColumn());
        assertEquals("*", oDataDefinition.getAssociations().get(0).getTo().getMultiplicity());
        assertEquals(2, oDataDefinition.getAssociations().get(0).getTo().getProperty().size());
        assertEquals("FK_COMPANY_ID", oDataDefinition.getAssociations().get(0).getTo().getProperty().get(0));
        assertEquals("FK_EMPLOYEE_NUMBER", oDataDefinition.getAssociations().get(0).getTo().getProperty().get(1));
    }

    @Test
    public void testConvertWithSetOfPropAndLimitedExposedNavigations() throws Exception {
        XSKODataParser parser = new XSKODataParser();
        String content = org.apache.commons.io.IOUtils
                .toString(XSKODataUtilsTest.class.getResourceAsStream("/entity_with_set_of_prop.xsodata"), StandardCharsets.UTF_8);
        XSKODataModel xskoDataModel = parser.parseXSODataArtifact("np/entity_with_set_of_prop.xsodata", content);

        PersistenceTableColumnModel column1 = new PersistenceTableColumnModel("COMPANY_ID", "Edm.Int32", true);
        PersistenceTableColumnModel column2 = new PersistenceTableColumnModel("EMPLOYEE_NUMBER", "Edm.Int32", true);
        PersistenceTableColumnModel column9 = new PersistenceTableColumnModel("ORDER_ID", "Edm.Int32", false);
        column9.setNullable(true);
        PersistenceTableColumnModel column10 = new PersistenceTableColumnModel("ORDER_ID_2", "Edm.Int32", false);
        column10.setNullable(true);
        PersistenceTableModel model = new PersistenceTableModel("kneo.test.helloodata.CompositeKey::employee", Arrays.asList(column1, column2, column9, column10), new ArrayList<>());
        when(dbMetadataUtil.getTableMetadata("kneo.test.helloodata.CompositeKey::employee")).thenReturn(model);

        PersistenceTableColumnModel column7 = new PersistenceTableColumnModel("ID", "Edm.Int32", true);
        PersistenceTableColumnModel column8 = new PersistenceTableColumnModel("FK_PHONE", "Edm.Int32", false);
        PersistenceTableRelationModel relPhone = new PersistenceTableRelationModel("kneo.test.helloodata.CompositeKey::address", "PHONES", "FK_PHONE", "ID", "CONSTRAINT_8C9F7", "CONSTRAINT_INDEX_E67");
        model = new PersistenceTableModel("kneo.test.helloodata.CompositeKey::address", Arrays.asList(column7, column8), Collections.singletonList(relPhone));
        when(dbMetadataUtil.getTableMetadata("kneo.test.helloodata.CompositeKey::address")).thenReturn(model);

        PersistenceTableColumnModel column3 = new PersistenceTableColumnModel("NUMBER", "Edm.Int32", true);
        PersistenceTableColumnModel column4 = new PersistenceTableColumnModel("FK_COMPANY_ID", "Edm.Int32", false);
        PersistenceTableColumnModel column5 = new PersistenceTableColumnModel("FK_EMPLOYEE_NUMBER", "Edm.Int32", false);
        PersistenceTableColumnModel column6 = new PersistenceTableColumnModel("FK_ADDRESS_ID", "Edm.Int32", false);
        PersistenceTableRelationModel rel = new PersistenceTableRelationModel("kneo.test.helloodata.CompositeKey::phones", "kneo.test.helloodata.CompositeKey::employee", "FK_COMPANY_ID", "COMPANY_ID", "CONSTRAINT_8C", "CONSTRAINT_INDEX_4");
        PersistenceTableRelationModel rel2 = new PersistenceTableRelationModel("kneo.test.helloodata.CompositeKey::phones", "kneo.test.helloodata.CompositeKey::employee", "FK_EMPLOYEE_NUMBER", "EMPLOYEE_NUMBER", "CONSTRAINT_8C9", "CONSTRAINT_INDEX_43");
        PersistenceTableRelationModel rel3 = new PersistenceTableRelationModel("kneo.test.helloodata.CompositeKey::phones", "kneo.test.helloodata.CompositeKey::address", "FK_ADDRESS_ID", "ID", "CONSTRAINT_8C9F", "CONSTRAINT_INDEX_E6");
        model = new PersistenceTableModel("kneo.test.helloodata.CompositeKey::phones", Arrays.asList(column3, column4, column5, column6), Arrays.asList(rel, rel2, rel3));
        when(dbMetadataUtil.getTableMetadata("kneo.test.helloodata.CompositeKey::phones")).thenReturn(model);

        ODataDefinition oDataDefinition = XSKODataUtils.convertXSKODataModelToODataDefinition(xskoDataModel, dbMetadataUtil);
        assertEquals(2, oDataDefinition.getEntities().size());
        assertEquals(1, oDataDefinition.getAssociations().size());
        assertEquals("np", oDataDefinition.getNamespace());
        assertNull(oDataDefinition.getCreatedAt());
        assertNull(oDataDefinition.getHash());
        assertNull(oDataDefinition.getLocation());

        assertEquals("Employees", oDataDefinition.getEntities().get(0).getName());
        assertEquals("Employees", oDataDefinition.getEntities().get(0).getAlias());
        assertEquals("kneo.test.helloodata.CompositeKey::employee", oDataDefinition.getEntities().get(0).getTable());

        assertEquals(3, oDataDefinition.getEntities().get(0).getProperties().size());
        assertEquals("COMPANY_ID", oDataDefinition.getEntities().get(0).getProperties().get(0).getName());
        assertEquals("COMPANY_ID", oDataDefinition.getEntities().get(0).getProperties().get(0).getColumn());
        assertFalse(oDataDefinition.getEntities().get(0).getProperties().get(0).isNullable());
        assertEquals("Edm.Int32", oDataDefinition.getEntities().get(0).getProperties().get(0).getType());
        assertEquals("EMPLOYEE_NUMBER", oDataDefinition.getEntities().get(0).getProperties().get(1).getName());
        assertEquals("EMPLOYEE_NUMBER", oDataDefinition.getEntities().get(0).getProperties().get(1).getColumn());
        assertFalse(oDataDefinition.getEntities().get(0).getProperties().get(1).isNullable());
        assertEquals("Edm.Int32", oDataDefinition.getEntities().get(0).getProperties().get(1).getType());
        assertEquals("ORDER_ID", oDataDefinition.getEntities().get(0).getProperties().get(2).getName());
        assertEquals("ORDER_ID", oDataDefinition.getEntities().get(0).getProperties().get(2).getColumn());
        assertTrue(oDataDefinition.getEntities().get(0).getProperties().get(2).isNullable());
        assertEquals("Edm.Int32", oDataDefinition.getEntities().get(0).getProperties().get(2).getType());

        assertEquals(0, oDataDefinition.getEntities().get(0).getHandlers().size());
        assertEquals(1, oDataDefinition.getEntities().get(0).getNavigations().size());
        assertEquals("HisPhones", oDataDefinition.getEntities().get(0).getNavigations().get(0).getName());
        assertEquals("Employees_Phones", oDataDefinition.getEntities().get(0).getNavigations().get(0).getAssociation());

        assertEquals("Phones", oDataDefinition.getEntities().get(1).getName());
        assertEquals("Phones", oDataDefinition.getEntities().get(1).getAlias());
        assertEquals("kneo.test.helloodata.CompositeKey::phones", oDataDefinition.getEntities().get(1).getTable());
        assertEquals(0, oDataDefinition.getEntities().get(1).getProperties().size());
        assertEquals(0, oDataDefinition.getEntities().get(1).getHandlers().size());
        assertEquals(0, oDataDefinition.getEntities().get(1).getNavigations().size());

        assertEquals("Employees_Phones", oDataDefinition.getAssociations().get(0).getName());
        assertEquals("Employees", oDataDefinition.getAssociations().get(0).getFrom().getEntity());
        assertNull(oDataDefinition.getAssociations().get(0).getFrom().getColumn());
        assertEquals("1", oDataDefinition.getAssociations().get(0).getFrom().getMultiplicity());
        assertEquals(2, oDataDefinition.getAssociations().get(0).getFrom().getProperty().size());
        assertEquals("COMPANY_ID", oDataDefinition.getAssociations().get(0).getFrom().getProperty().get(0));
        assertEquals("EMPLOYEE_NUMBER", oDataDefinition.getAssociations().get(0).getFrom().getProperty().get(1));

        assertEquals("Phones", oDataDefinition.getAssociations().get(0).getTo().getEntity());
        assertNull(oDataDefinition.getAssociations().get(0).getTo().getColumn());
        assertEquals("*", oDataDefinition.getAssociations().get(0).getTo().getMultiplicity());
        assertEquals(2, oDataDefinition.getAssociations().get(0).getTo().getProperty().size());
        assertEquals("FK_COMPANY_ID", oDataDefinition.getAssociations().get(0).getTo().getProperty().get(0));
        assertEquals("FK_EMPLOYEE_NUMBER", oDataDefinition.getAssociations().get(0).getTo().getProperty().get(1));
    }
}



