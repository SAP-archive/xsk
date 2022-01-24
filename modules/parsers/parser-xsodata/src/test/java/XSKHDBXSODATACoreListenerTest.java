/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2022 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.sap.xsk.parser.xsodata.core.HdbxsodataLexer;
import com.sap.xsk.parser.xsodata.core.HdbxsodataParser;
import com.sap.xsk.parser.xsodata.custom.XSKHDBXSODATACoreListener;
import com.sap.xsk.parser.xsodata.model.XSKHDBXSODATAAggregation;
import com.sap.xsk.parser.xsodata.model.XSKHDBXSODATAAggregationType;
import com.sap.xsk.parser.xsodata.model.XSKHDBXSODATAAssociation;
import com.sap.xsk.parser.xsodata.model.XSKHDBXSODATAAssociationTable;
import com.sap.xsk.parser.xsodata.model.XSKHDBXSODATABinding;
import com.sap.xsk.parser.xsodata.model.XSKHDBXSODATABindingRole;
import com.sap.xsk.parser.xsodata.model.XSKHDBXSODATABindingType;
import com.sap.xsk.parser.xsodata.model.XSKHDBXSODATAEntity;
import com.sap.xsk.parser.xsodata.model.XSKHDBXSODATAEvent;
import com.sap.xsk.parser.xsodata.model.XSKHDBXSODATAEventType;
import com.sap.xsk.parser.xsodata.model.XSKHDBXSODATAHandlerMethod;
import com.sap.xsk.parser.xsodata.model.XSKHDBXSODATAModification;
import com.sap.xsk.parser.xsodata.model.XSKHDBXSODATAModificationSpec;
import com.sap.xsk.parser.xsodata.model.XSKHDBXSODATAMultiplicityType;
import com.sap.xsk.parser.xsodata.model.XSKHDBXSODATANavigation;
import com.sap.xsk.parser.xsodata.model.XSKHDBXSODATARepositoryObject;
import com.sap.xsk.parser.xsodata.model.XSKHDBXSODATAService;
import com.sap.xsk.parser.xsodata.model.XSKHDBXSODATASetting;
import com.sap.xsk.parser.xsodata.model.XSKHDBXSODATAStorage;
import com.sap.xsk.parser.xsodata.model.XSKHDBXSODATAStorageType;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Test;

public class XSKHDBXSODATACoreListenerTest {
    private final XSKHDBXSODATACoreListener listener = new XSKHDBXSODATACoreListener();

    @Test
    public void parseEmptyServiceSuccessfully() throws Exception {
        HdbxsodataParser parser = parseSampleFile("xsodata/empty_service.xsodata");

        XSKHDBXSODATAService model = listener.getServiceModel();
        assertNotNull(model);
        assertEquals(parser.getNumberOfSyntaxErrors(), 0);
        assertNull(model.getNamespace());
        assertEquals(model.getEntities().size(), 0);
        assertFalse(model.isEnableOData4SAPAnnotations());
    }

    @Test
    public void parseEmptyServiceWithNamespaceDefinitionSuccessfully() throws Exception {
        HdbxsodataParser parser = parseSampleFile("xsodata/empty_service_with_namespace.xsodata");

        XSKHDBXSODATAService model = listener.getServiceModel();
        assertNotNull(model);
        assertEquals(parser.getNumberOfSyntaxErrors(), 0);
        assertEquals(model.getNamespace(), "my.demo.namespace");
        assertEquals(model.getEntities().size(), 0);
        assertFalse(model.isEnableOData4SAPAnnotations());
    }

    @Test
    public void parseEmptyServiceWithAnnotationSuccessfully() throws Exception {
        HdbxsodataParser parser = parseSampleFile("xsodata/empty_service_with_annotations.xsodata");

        XSKHDBXSODATAService model = listener.getServiceModel();
        assertNotNull(model);
        assertEquals(parser.getNumberOfSyntaxErrors(), 0);
        assertNull(model.getNamespace());
        assertEquals(model.getEntities().size(), 0);
        assertTrue(model.isEnableOData4SAPAnnotations());
    }

    @Test
    public void parseEmptyServiceWithSettingsSuccessfully() throws Exception {
        HdbxsodataParser parser = parseSampleFile("xsodata/empty_service_with_settings.xsodata");

        XSKHDBXSODATAService model = listener.getServiceModel();
        assertNotNull(model);
        assertEquals(parser.getNumberOfSyntaxErrors(), 0);
        assertNull(model.getNamespace());
        assertEquals(model.getEntities().size(), 0);
        assertEquals(model.getAssociations().size(), 0);
        assertNotNull(model.getSetting());
        XSKHDBXSODATASetting actualSetting = new XSKHDBXSODATASetting();
        actualSetting.setEnableSupportNull(true);
        actualSetting.setMaxExpandedRecords("30");
        actualSetting.setMaxRecords("10");
        actualSetting.setContentCacheControl(Collections.singletonList("no-store"));
        actualSetting.setMetadataCacheControl(Arrays.asList("no-cache", "no-store", "max-age=86401", "must-revalidate"));
        actualSetting.setHints(Arrays.asList("NO_CALC_VIEW_UNFOLDING", "ABC"));
        assertEquals(model.getSetting(), actualSetting);
    }

    @Test
    public void parseEmptyServiceWithSettingsWithHintsAsNullSuccessfully() throws Exception {
        HdbxsodataParser parser = parseSampleFile("xsodata/empty_service_without_hints.xsodata");

        XSKHDBXSODATAService model = listener.getServiceModel();
        assertNotNull(model);
        assertEquals(parser.getNumberOfSyntaxErrors(), 0);
        assertNull(model.getNamespace());
        assertEquals(model.getEntities().size(), 0);
        assertEquals(model.getAssociations().size(), 0);
        assertNotNull(model.getSetting());
        XSKHDBXSODATASetting actualSetting = new XSKHDBXSODATASetting();
        actualSetting.setHints(Collections.singletonList("null"));
        assertEquals(model.getSetting(), actualSetting);
    }

    @Test
    public void parseServiceWithObjectsSuccessfully() throws Exception {
        HdbxsodataParser parser = parseSampleFile("xsodata/entity_with_object_exposure.xsodata");

        XSKHDBXSODATAService model = listener.getServiceModel();
        assertNotNull(model);
        assertEquals(parser.getNumberOfSyntaxErrors(), 0);
        assertEquals(model.getEntities().size(), 2);

        XSKHDBXSODATAEntity actualEntity = new XSKHDBXSODATAEntity();
        actualEntity.setRepositoryObject(new XSKHDBXSODATARepositoryObject().setCatalogObjectName("sample.odata::table").setCatalogObjectSchema("XSODATA_TEST"));
        actualEntity.setAlias("MyTable");
        assertEquals(model.getEntities().get(0), actualEntity);

        actualEntity = new XSKHDBXSODATAEntity();
        actualEntity.setRepositoryObject(new XSKHDBXSODATARepositoryObject().setCatalogObjectName("test/AN_AGENCY_REV.analyticView"));
        actualEntity.setAlias("Revenue");
        assertEquals(model.getEntities().get(1), actualEntity);
    }

    @Test
    public void parseServiceWithPropertyProjectionSuccessfully() throws Exception {
        HdbxsodataParser parser = parseSampleFile("xsodata/entity_with_prop.xsodata");

        XSKHDBXSODATAService model = listener.getServiceModel();
        assertNotNull(model);
        assertEquals(parser.getNumberOfSyntaxErrors(), 0);
        assertEquals(model.getEntities().size(), 2);

        XSKHDBXSODATAEntity actualEntity = new XSKHDBXSODATAEntity();
        actualEntity.setRepositoryObject(new XSKHDBXSODATARepositoryObject().setCatalogObjectName("sample.odata::table"));
        actualEntity.setAlias("MyTable");
        actualEntity.setWithPropertyProjections(Arrays.asList("ID", "Text"));
        assertEquals(model.getEntities().get(0), actualEntity);

        actualEntity = new XSKHDBXSODATAEntity();
        actualEntity.setRepositoryObject(new XSKHDBXSODATARepositoryObject().setCatalogObjectName("sample.odata::table"));
        actualEntity.setAlias("MyTable");
        actualEntity.setWithoutPropertyProjections(Arrays.asList("Text", "Time"));
        assertEquals(model.getEntities().get(1), actualEntity);
    }

    @Test
    public void parseServiceWithKeysSuccessfully() throws Exception {
        HdbxsodataParser parser = parseSampleFile("xsodata/entity_with_keys.xsodata");

        XSKHDBXSODATAService model = listener.getServiceModel();
        assertNotNull(model);
        assertEquals(parser.getNumberOfSyntaxErrors(), 0);
        assertEquals(model.getEntities().size(), 4);

        XSKHDBXSODATAEntity actualEntity = new XSKHDBXSODATAEntity();
        actualEntity.setRepositoryObject(new XSKHDBXSODATARepositoryObject().setCatalogObjectName("sample.odata::view"));
        actualEntity.setAlias("MyView");
        actualEntity.setKeyList(Arrays.asList("ID", "Text"));
        assertEquals(model.getEntities().get(0), actualEntity);
        assertEquals(model.getEntities().get(1), actualEntity);


        actualEntity = new XSKHDBXSODATAEntity();
        actualEntity.setRepositoryObject(new XSKHDBXSODATARepositoryObject().setCatalogObjectName("sample.odata::view"));
        actualEntity.setAlias("MyView");
        actualEntity.setKeyGenerated("GenID");
        assertEquals(model.getEntities().get(2), actualEntity);

        actualEntity = new XSKHDBXSODATAEntity();
        actualEntity.setRepositoryObject(new XSKHDBXSODATARepositoryObject().setCatalogObjectName("test/AN_AGENCY.analyticView"));
        actualEntity.setAlias("Revenue");
        actualEntity.setKeyGenerated("GENERATED_ID");
        assertEquals(model.getEntities().get(3), actualEntity);
    }

    @Test
    public void parseServiceWithNavigationsSuccessfully() throws Exception {
        HdbxsodataParser parser = parseSampleFile("xsodata/entity_with_navigation.xsodata");

        XSKHDBXSODATAService model = listener.getServiceModel();
        assertNotNull(model);
        assertEquals(parser.getNumberOfSyntaxErrors(), 0);
        assertEquals(5, model.getEntities().size());

        XSKHDBXSODATAEntity actualEntity = new XSKHDBXSODATAEntity();
        actualEntity.setRepositoryObject(new XSKHDBXSODATARepositoryObject().setCatalogObjectName("sample.odata::customer"));
        actualEntity.setAlias("Customers");
        XSKHDBXSODATANavigation nav1 = new XSKHDBXSODATANavigation().setAssociation("Customer_Orders").setAliasNavigation("HisOrders");
        XSKHDBXSODATANavigation nav2 = new XSKHDBXSODATANavigation().setAssociation("Customer_Recruit").setAliasNavigation("Recruit").setFromBindingType(XSKHDBXSODATABindingType.DEPENDENT);
        actualEntity.setNavigates(Arrays.asList(nav1, nav2));
        assertEquals(model.getEntities().get(0), actualEntity);

        actualEntity = new XSKHDBXSODATAEntity();
        actualEntity.setRepositoryObject(new XSKHDBXSODATARepositoryObject().setCatalogObjectName("sample.odata::student"));
        actualEntity.setAlias("Students");
        actualEntity.setNavigates(Collections.singletonList(new XSKHDBXSODATANavigation().setAssociation("Students_Grades").setAliasNavigation("HisGrades").setFromBindingType(XSKHDBXSODATABindingType.PRINCIPAL)));
        assertEquals(model.getEntities().get(1), actualEntity);
    }

    @Test
    public void parseServiceWithAggregationsSuccessfully() throws Exception {
        HdbxsodataParser parser = parseSampleFile("xsodata/entity_with_aggregations.xsodata");

        XSKHDBXSODATAService model = listener.getServiceModel();
        assertNotNull(model);
        assertEquals(parser.getNumberOfSyntaxErrors(), 0);
        assertEquals(model.getEntities().size(), 3);

        XSKHDBXSODATAEntity actualEntity = new XSKHDBXSODATAEntity();
        actualEntity.setRepositoryObject(new XSKHDBXSODATARepositoryObject().setCatalogObjectName("package::revenues"));
        actualEntity.setAlias("Revenues");
        XSKHDBXSODATAAggregation agg1 = new XSKHDBXSODATAAggregation().setAggregateColumnName("Amount").setAggregateFunction("SUM");
        XSKHDBXSODATAAggregation agg2 = new XSKHDBXSODATAAggregation().setAggregateColumnName("Amount").setAggregateFunction("AVG");
        XSKHDBXSODATAAggregation agg3 = new XSKHDBXSODATAAggregation().setAggregateColumnName("Amount").setAggregateFunction("MIN");
        actualEntity.setAggregations(Arrays.asList(agg1, agg2, agg3));
        actualEntity.setKeyGenerated("ID");
        actualEntity.setAggregationType(XSKHDBXSODATAAggregationType.EXPLICIT);
        assertEquals(model.getEntities().get(0), actualEntity);

        actualEntity = new XSKHDBXSODATAEntity();
        actualEntity.setRepositoryObject(new XSKHDBXSODATARepositoryObject().setCatalogObjectName("package::revenues"));
        actualEntity.setAlias("Revenues");
        actualEntity.setKeyGenerated("ID");
        actualEntity.setAggregations(Collections.singletonList((new XSKHDBXSODATAAggregation()).setAggregateColumnName("Amount").setAggregateFunction("MAX")));
        actualEntity.setAggregationType(XSKHDBXSODATAAggregationType.EXPLICIT);
        assertEquals(model.getEntities().get(1), actualEntity);

        actualEntity = new XSKHDBXSODATAEntity();
        actualEntity.setRepositoryObject(new XSKHDBXSODATARepositoryObject().setCatalogObjectName("package::PLANNED_ACTUAL_SALES"));
        actualEntity.setAlias("PlannedCalcView");
        actualEntity.setKeyGenerated("ID");
        actualEntity.setAggregationType(XSKHDBXSODATAAggregationType.IMPLICIT);
        assertEquals(model.getEntities().get(2), actualEntity);
    }

    @Test
    public void parseServiceWithEventsSuccessfully() throws Exception {
        HdbxsodataParser parser = parseSampleFile("xsodata/entity_with_events.xsodata");

        XSKHDBXSODATAService model = listener.getServiceModel();
        assertNotNull(model);
        assertEquals(parser.getNumberOfSyntaxErrors(), 0);
        assertEquals(model.getEntities().size(), 4);

        XSKHDBXSODATAEntity actualEntity = new XSKHDBXSODATAEntity();
        actualEntity.setRepositoryObject(new XSKHDBXSODATARepositoryObject().setCatalogObjectName("sample.odata::table"));
        XSKHDBXSODATAModification updateModification = new XSKHDBXSODATAModification().setMethod(XSKHDBXSODATAHandlerMethod.UPDATE);
        updateModification.setSpecification(new XSKHDBXSODATAModificationSpec().setEvents(Arrays.asList(new XSKHDBXSODATAEvent(XSKHDBXSODATAEventType.BEFORE, "sample.odata::beforeMethod"), new XSKHDBXSODATAEvent(XSKHDBXSODATAEventType.PRECOMMIT, "sample.odata::beforeMethod"))));
        XSKHDBXSODATAModification deleteModification = new XSKHDBXSODATAModification().setMethod(XSKHDBXSODATAHandlerMethod.DELETE);
        deleteModification.setSpecification(new XSKHDBXSODATAModificationSpec().setEvents(Collections.singletonList(new XSKHDBXSODATAEvent(XSKHDBXSODATAEventType.AFTER, "sample.odata::afterMethod"))));
        XSKHDBXSODATAModification createModification = new XSKHDBXSODATAModification().setMethod(XSKHDBXSODATAHandlerMethod.CREATE);
        createModification.setSpecification(new XSKHDBXSODATAModificationSpec().setForbidden(true));
        actualEntity.setModifications(Arrays.asList(updateModification, deleteModification, createModification));
        assertEquals(model.getEntities().get(0), actualEntity);

        actualEntity = new XSKHDBXSODATAEntity();
        actualEntity.setRepositoryObject(new XSKHDBXSODATARepositoryObject().setCatalogObjectName("sample.odata::table"));
        createModification = new XSKHDBXSODATAModification().setMethod(XSKHDBXSODATAHandlerMethod.CREATE);
        createModification.setSpecification(new XSKHDBXSODATAModificationSpec().setModificationAction("sample.odata::createMethod").setEvents(Collections.singletonList(new XSKHDBXSODATAEvent(XSKHDBXSODATAEventType.AFTER, "sample.odata::afterMethod"))));
        updateModification = new XSKHDBXSODATAModification().setMethod(XSKHDBXSODATAHandlerMethod.UPDATE);
        updateModification.setSpecification(new XSKHDBXSODATAModificationSpec().setModificationAction("sample.odata::updateMethod"));
        deleteModification = new XSKHDBXSODATAModification().setMethod(XSKHDBXSODATAHandlerMethod.DELETE);
        deleteModification.setSpecification(new XSKHDBXSODATAModificationSpec().setModificationAction("sample.odata::deleteMethod"));
        actualEntity.setModifications(Arrays.asList(createModification, updateModification, deleteModification));
        assertEquals(model.getEntities().get(1), actualEntity);

        actualEntity = new XSKHDBXSODATAEntity();
        actualEntity.setRepositoryObject(new XSKHDBXSODATARepositoryObject().setCatalogObjectName("sample.odata::table"));
        createModification = new XSKHDBXSODATAModification().setMethod(XSKHDBXSODATAHandlerMethod.CREATE);
        createModification.setSpecification(new XSKHDBXSODATAModificationSpec().setModificationAction("sample.odata::createMethod"));
        updateModification = new XSKHDBXSODATAModification().setMethod(XSKHDBXSODATAHandlerMethod.UPDATE);
        updateModification.setSpecification(new XSKHDBXSODATAModificationSpec().setEvents(Collections.singletonList(new XSKHDBXSODATAEvent(XSKHDBXSODATAEventType.PRECOMMIT, "sample.odata::precommitMethod"))));
        deleteModification = new XSKHDBXSODATAModification().setMethod(XSKHDBXSODATAHandlerMethod.DELETE).setSpecification(new XSKHDBXSODATAModificationSpec().setForbidden(true));
        actualEntity.setModifications(Arrays.asList(createModification, updateModification, deleteModification));
        assertEquals(model.getEntities().get(2), actualEntity);

        actualEntity = new XSKHDBXSODATAEntity();
        actualEntity.setRepositoryObject(new XSKHDBXSODATARepositoryObject().setCatalogObjectName("sample.odata::table"));
        createModification = new XSKHDBXSODATAModification().setMethod(XSKHDBXSODATAHandlerMethod.CREATE).setSpecification(new XSKHDBXSODATAModificationSpec().setForbidden(true));
        updateModification = new XSKHDBXSODATAModification().setMethod(XSKHDBXSODATAHandlerMethod.UPDATE).setSpecification(new XSKHDBXSODATAModificationSpec().setForbidden(true));
        deleteModification = new XSKHDBXSODATAModification().setMethod(XSKHDBXSODATAHandlerMethod.DELETE).setSpecification(new XSKHDBXSODATAModificationSpec().setForbidden(true));
        actualEntity.setModifications(Arrays.asList(createModification, updateModification, deleteModification));
        assertEquals(model.getEntities().get(3), actualEntity);
    }

    @Test
    public void parseServiceWithAssociationsSuccessfully() throws Exception {
        HdbxsodataParser parser = parseSampleFile("xsodata/entity_with_associations.xsodata");

        XSKHDBXSODATAService model = listener.getServiceModel();
        assertNotNull(model);
        assertEquals(parser.getNumberOfSyntaxErrors(), 0);
        assertEquals(model.getAssociations().size(), 6);

        XSKHDBXSODATAAssociation actualAssociation = new XSKHDBXSODATAAssociation();
        actualAssociation.setName("complex_atob");
        actualAssociation.setWithReferentialConstraint(false);
        XSKHDBXSODATABinding principal = new XSKHDBXSODATABinding();
        principal.setEntitySetName("complex_a");
        principal.setBindingRole(new XSKHDBXSODATABindingRole().setBindingType(XSKHDBXSODATABindingType.PRINCIPAL).setKeys(Collections.singletonList("ID")));
        principal.setMultiplicityType(XSKHDBXSODATAMultiplicityType.MANY);
        actualAssociation.setPrincipal(principal);
        XSKHDBXSODATABinding dependent = new XSKHDBXSODATABinding();
        dependent.setEntitySetName("complex_b");
        dependent.setBindingRole(new XSKHDBXSODATABindingRole().setBindingType(XSKHDBXSODATABindingType.DEPENDENT).setKeys(Collections.singletonList("ID_A")));
        dependent.setMultiplicityType(XSKHDBXSODATAMultiplicityType.MANY);
        actualAssociation.setDependent(dependent);
        XSKHDBXSODATAAssociationTable table = new XSKHDBXSODATAAssociationTable();
        table.setRepositoryObject("xsodata.test.tables::complex_assoc_atob");
        table.setPrincipal(new XSKHDBXSODATABindingRole().setBindingType(XSKHDBXSODATABindingType.PRINCIPAL).setKeys(Collections.singletonList("ID_A")));
        table.setDependent(new XSKHDBXSODATABindingRole().setBindingType(XSKHDBXSODATABindingType.DEPENDENT).setKeys(Collections.singletonList("ID_B")));
        actualAssociation.setAssociationTable(table);
        assertEquals(model.getAssociations().get(0), actualAssociation);


        XSKHDBXSODATAModification updateModification = new XSKHDBXSODATAModification().setMethod(XSKHDBXSODATAHandlerMethod.UPDATE);
        updateModification.setSpecification(new XSKHDBXSODATAModificationSpec().setEvents(Collections.singletonList(new XSKHDBXSODATAEvent(XSKHDBXSODATAEventType.BEFORE, "sample.odata::updateMethod"))));
        XSKHDBXSODATAModification deleteModification = new XSKHDBXSODATAModification().setMethod(XSKHDBXSODATAHandlerMethod.DELETE);
        deleteModification.setSpecification(new XSKHDBXSODATAModificationSpec().setEvents(Collections.singletonList(new XSKHDBXSODATAEvent(XSKHDBXSODATAEventType.BEFORE, "sample.odata::deleteMethod"))));
        XSKHDBXSODATAModification createModification = new XSKHDBXSODATAModification().setMethod(XSKHDBXSODATAHandlerMethod.CREATE);
        createModification.setSpecification(new XSKHDBXSODATAModificationSpec().setEvents(Collections.singletonList(new XSKHDBXSODATAEvent(XSKHDBXSODATAEventType.BEFORE, "sample.odata::createMethod"))));
        table.setModifications(Arrays.asList(deleteModification, createModification, updateModification));
        actualAssociation.setAssociationTable(table);
        assertEquals(model.getAssociations().get(1), actualAssociation);

        actualAssociation = new XSKHDBXSODATAAssociation();
        actualAssociation.setName("complex_b_to_c");
        actualAssociation.setWithReferentialConstraint(false);
        principal = new XSKHDBXSODATABinding();
        principal.setEntitySetName("complex_b");
        principal.setBindingRole(new XSKHDBXSODATABindingRole().setBindingType(XSKHDBXSODATABindingType.PRINCIPAL).setKeys(Collections.singletonList("ID")));
        principal.setMultiplicityType(XSKHDBXSODATAMultiplicityType.ONE);
        actualAssociation.setPrincipal(principal);
        dependent = new XSKHDBXSODATABinding();
        dependent.setEntitySetName("complex_c");
        dependent.setBindingRole(new XSKHDBXSODATABindingRole().setBindingType(XSKHDBXSODATABindingType.DEPENDENT).setKeys(Collections.singletonList("ID_A")));
        dependent.setMultiplicityType(XSKHDBXSODATAMultiplicityType.MANY);
        actualAssociation.setDependent(dependent);
        updateModification = new XSKHDBXSODATAModification().setMethod(XSKHDBXSODATAHandlerMethod.UPDATE).setSpecification(new XSKHDBXSODATAModificationSpec().setModificationAction("sap.test:oDataExtendedRules.xsjslib::associationToConditions"));
        table.setModifications(Arrays.asList(deleteModification, createModification, updateModification));
        actualAssociation.setStorage(new XSKHDBXSODATAStorage().setStorageType(XSKHDBXSODATAStorageType.STORAGE_ON_DEPENDENT).setModifications(Collections.singletonList(updateModification)));
        assertEquals(model.getAssociations().get(2), actualAssociation);

        actualAssociation = new XSKHDBXSODATAAssociation();
        actualAssociation.setName("complex_b_to_c");
        actualAssociation.setWithReferentialConstraint(false);
        principal = new XSKHDBXSODATABinding();
        principal.setEntitySetName("complex_b");
        principal.setBindingRole(new XSKHDBXSODATABindingRole().setBindingType(XSKHDBXSODATABindingType.PRINCIPAL).setKeys(Collections.singletonList("ID")));
        principal.setMultiplicityType(XSKHDBXSODATAMultiplicityType.ZERO_TO_ONE);
        actualAssociation.setPrincipal(principal);
        dependent = new XSKHDBXSODATABinding();
        dependent.setEntitySetName("complex_c");
        dependent.setBindingRole(new XSKHDBXSODATABindingRole().setBindingType(XSKHDBXSODATABindingType.DEPENDENT).setKeys(Collections.singletonList("ID_B")));
        dependent.setMultiplicityType(XSKHDBXSODATAMultiplicityType.ONE);
        actualAssociation.setDependent(dependent);
        actualAssociation.setStorage(new XSKHDBXSODATAStorage().setStorageType(XSKHDBXSODATAStorageType.STORAGE_ON_PRINCIPAL));
        assertEquals(model.getAssociations().get(3), actualAssociation);

        actualAssociation = new XSKHDBXSODATAAssociation();
        actualAssociation.setName("complex_b_to_c");
        actualAssociation.setWithReferentialConstraint(false);
        principal = new XSKHDBXSODATABinding();
        principal.setEntitySetName("complex_b");
        principal.setBindingRole(new XSKHDBXSODATABindingRole().setBindingType(XSKHDBXSODATABindingType.PRINCIPAL).setKeys(Collections.singletonList("ID")));
        principal.setMultiplicityType(XSKHDBXSODATAMultiplicityType.ONE_TO_MANY);
        actualAssociation.setPrincipal(principal);
        dependent = new XSKHDBXSODATABinding();
        dependent.setEntitySetName("complex_c");
        dependent.setBindingRole(new XSKHDBXSODATABindingRole().setBindingType(XSKHDBXSODATABindingType.DEPENDENT).setKeys(Collections.singletonList("ID_B")));
        dependent.setMultiplicityType(XSKHDBXSODATAMultiplicityType.MANY);
        actualAssociation.setDependent(dependent);
        actualAssociation.setStorage(new XSKHDBXSODATAStorage().setStorageType(XSKHDBXSODATAStorageType.NO_STORAGE));
        assertEquals(model.getAssociations().get(4), actualAssociation);

        actualAssociation = new XSKHDBXSODATAAssociation();
        actualAssociation.setName("complex_b_to_c");
        actualAssociation.setWithReferentialConstraint(true);
        principal = new XSKHDBXSODATABinding();
        principal.setEntitySetName("complex_b");
        principal.setBindingRole(new XSKHDBXSODATABindingRole().setBindingType(XSKHDBXSODATABindingType.PRINCIPAL).setKeys(Collections.singletonList("ID")));
        principal.setMultiplicityType(XSKHDBXSODATAMultiplicityType.ONE);
        actualAssociation.setPrincipal(principal);
        dependent = new XSKHDBXSODATABinding();
        dependent.setEntitySetName("complex_c");
        dependent.setBindingRole(new XSKHDBXSODATABindingRole().setBindingType(XSKHDBXSODATABindingType.DEPENDENT).setKeys(Collections.singletonList("ID_B")));
        dependent.setMultiplicityType(XSKHDBXSODATAMultiplicityType.MANY);
        actualAssociation.setDependent(dependent);
        updateModification = new XSKHDBXSODATAModification().setMethod(XSKHDBXSODATAHandlerMethod.UPDATE);
        updateModification.setSpecification(new XSKHDBXSODATAModificationSpec().setEvents(Collections.singletonList(new XSKHDBXSODATAEvent(XSKHDBXSODATAEventType.BEFORE, "sample.odata::updateMethod"))));
        deleteModification = new XSKHDBXSODATAModification().setMethod(XSKHDBXSODATAHandlerMethod.DELETE);
        deleteModification.setSpecification(new XSKHDBXSODATAModificationSpec().setEvents(Collections.singletonList(new XSKHDBXSODATAEvent(XSKHDBXSODATAEventType.BEFORE, "sample.odata::deleteMethod"))));
        createModification = new XSKHDBXSODATAModification().setMethod(XSKHDBXSODATAHandlerMethod.CREATE);
        createModification.setSpecification(new XSKHDBXSODATAModificationSpec().setEvents(Collections.singletonList(new XSKHDBXSODATAEvent(XSKHDBXSODATAEventType.BEFORE, "sample.odata::createMethod"))));
        actualAssociation.setModifications(Arrays.asList(deleteModification, createModification, updateModification));
        assertEquals(model.getAssociations().get(5), actualAssociation);
    }

    @Test
    public void parseServiceWithETagsSuccessfully() throws Exception {
        HdbxsodataParser parser = parseSampleFile("xsodata/empty_service_with_etag.xsodata");

        XSKHDBXSODATAService model = listener.getServiceModel();
        assertNotNull(model);
        assertEquals(parser.getNumberOfSyntaxErrors(), 0);
        assertEquals(model.getEntities().size(), 4);

        XSKHDBXSODATAEntity actualEntity = new XSKHDBXSODATAEntity();
        actualEntity.setRepositoryObject(new XSKHDBXSODATARepositoryObject().setCatalogObjectName("xsodata.test.tables::all_types"));
        actualEntity.setAlias("all_types_etag");
        actualEntity.setConcurrencyToken(true);
        assertEquals(model.getEntities().get(0), actualEntity);

        actualEntity = new XSKHDBXSODATAEntity();
        actualEntity.setRepositoryObject(new XSKHDBXSODATARepositoryObject().setCatalogObjectName("xsodata.test.tables::BusinessPartner.BusinessPartner"));
        actualEntity.setAlias("BusinessPartner");
        actualEntity.setConcurrencyToken(true);
        actualEntity.setWithoutPropertyProjections(Collections.singletonList("isContactPerson"));
        XSKHDBXSODATANavigation nav1 = new XSKHDBXSODATANavigation().setAssociation("BusinessPartner_To_N_BPRole").setAliasNavigation("Roles");
        actualEntity.setNavigates(Collections.singletonList(nav1));
        assertEquals(model.getEntities().get(1), actualEntity);

        actualEntity = new XSKHDBXSODATAEntity();
        actualEntity.setRepositoryObject(new XSKHDBXSODATARepositoryObject().setCatalogObjectName("sap.test.odata.db.views::Etag"));
        actualEntity.setAlias("EtagAll");
        actualEntity.setConcurrencyToken(true);
        actualEntity.setKeyList(Arrays.asList("KEY_00", "KEY_01"));
        assertEquals(model.getEntities().get(2), actualEntity);

        actualEntity.setETags(Arrays.asList("NVARCHAR_01", "INTEGER_02"));
        assertEquals(model.getEntities().get(3), actualEntity);
    }

    @Test
    public void parseServiceSuccessfully() throws Exception {
        HdbxsodataParser parser = parseSampleFile("xsodata/entity_with_no_associations.xsodata");

        XSKHDBXSODATAService model = listener.getServiceModel();
        assertNotNull(model);
        assertEquals(parser.getNumberOfSyntaxErrors(), 0);
        assertEquals(model.getEntities().size(), 20);
    }


    private HdbxsodataParser parseSampleFile(String sampleFileName) throws Exception {
        String sample =
                org.apache.commons.io.IOUtils.toString(
                        XSKHDBXSODATACoreListenerTest.class.getResourceAsStream(sampleFileName),
                        StandardCharsets.UTF_8);

        ANTLRInputStream inputStream = new ANTLRInputStream(sample);
        HdbxsodataLexer lexer = new HdbxsodataLexer(inputStream);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        HdbxsodataParser parser = new HdbxsodataParser(tokenStream);
        parser.setBuildParseTree(true);
        ParseTree parseTree = parser.xsodataDefinition();

        ParseTreeWalker parseTreeWalker = new ParseTreeWalker();
        parseTreeWalker.walk(listener, parseTree);

        return parser;
    }

}