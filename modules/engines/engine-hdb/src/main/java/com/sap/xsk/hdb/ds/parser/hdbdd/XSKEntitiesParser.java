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
package com.sap.xsk.hdb.ds.parser.hdbdd;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.eclipse.dirigible.api.v3.security.UserFacade;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Injector;
import com.sap.xsk.models.hdbdd.HdbDDStandaloneSetupGenerated;
import com.sap.xsk.models.hdbdd.hdbDD.HdbDD;
import com.sap.xsk.models.hdbdd.hdbDD.impl.ContextImpl;
import com.sap.xsk.models.hdbdd.hdbDD.impl.EntityImpl;
import com.sap.xsk.models.hdbdd.hdbDD.impl.FieldImpl;
import com.sap.xsk.models.hdbdd.hdbDD.impl.FieldPrimitiveImpl;
import com.sap.xsk.models.hdbdd.hdbDD.impl.FieldReferenceImpl;
import com.sap.xsk.models.hdbdd.hdbDD.impl.FieldTypeImpl;
import com.sap.xsk.models.hdbdd.hdbDD.impl.NamespaceImpl;
import com.sap.xsk.models.hdbdd.hdbDD.impl.SchemaImpl;
import com.sap.xsk.models.hdbdd.hdbDD.impl.TypeDefinitionImpl;
import com.sap.xsk.hdb.ds.api.IXSKDataStructureModel;
import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.model.hdbdd.XSKDataStructureContextModel;
import com.sap.xsk.hdb.ds.model.hdbdd.XSKDataStructureEntitiesModel;
import com.sap.xsk.hdb.ds.model.hdbdd.XSKDataStructureEntityModel;
import com.sap.xsk.hdb.ds.model.hdbdd.XSKDataStructureTypeDefinitionModel;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableColumnModel;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableConstraintForeignKeyModel;
import com.sap.xsk.hdb.ds.parser.XSKDataStructureParser;

public class XSKEntitiesParser implements XSKDataStructureParser {
	
	private static final Logger logger = LoggerFactory.getLogger(XSKEntitiesParser.class);
	
    static Map<String, String> TYPES_MAP = new HashMap<String, String>();

    static {
        TYPES_MAP.put("String", "VARCHAR");
        TYPES_MAP.put("UTCTimestamp", "TIMESTAMP");
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
	@Override
    public XSKDataStructureEntitiesModel parse(String location, String content) throws XSKDataStructuresException, IOException {
        content = content.replaceAll("'", "");
        content = content.replaceAll("\"", "");
        
        content = content.replaceAll(" Entity ", " entity ");
        content = content.replaceAll("	Entity ", "	entity ");
        content = content.replaceAll(" Type ", " type ");

        new org.eclipse.emf.mwe.utils.StandaloneSetup().setPlatformUri("../");
        Injector injector = new HdbDDStandaloneSetupGenerated().createInjectorAndDoEMFRegistration();
        XtextResourceSet resourceSet = injector.getInstance(XtextResourceSet.class);
        resourceSet.addLoadOption(XtextResource.OPTION_RESOLVE_ALL, Boolean.TRUE);

        Resource resource = resourceSet.createResource(URI.createURI("dummy:/example.hdbdd"));
        InputStream in = new ByteArrayInputStream(content.getBytes());
        resource.load(in, resourceSet.getLoadOptions());
        HdbDD hdbDD = (HdbDD) resource.getContents().get(0);
//      Not used?
        EList errors = hdbDD.eResource().getErrors();
        Iterator errorsIterator = errors.iterator();
        while (errorsIterator.hasNext()) {
        	logger.error(errorsIterator.next().toString());
        }
        XSKDataStructureEntitiesModel hdbddModel = new XSKDataStructureEntitiesModel();

        hdbddModel.setName(new File(location).getName());
        hdbddModel.setLocation(location);
        hdbddModel.setType(IXSKDataStructureModel.TYPE_HDB_ENTITIES);
        hdbddModel.setHash(DigestUtils.md5Hex(content));
        hdbddModel.setCreatedBy(UserFacade.getName());
        hdbddModel.setCreatedAt(new Timestamp(new java.util.Date().getTime()));

        for (Iterator iterator = hdbDD.getElements().iterator(); iterator.hasNext();) {
            com.sap.xsk.models.hdbdd.hdbDD.Type type = (com.sap.xsk.models.hdbdd.hdbDD.Type) iterator.next();
            if (type instanceof NamespaceImpl) {
                hdbddModel.setNamespace(((NamespaceImpl) type).getName());
            } else if (type instanceof SchemaImpl) {
                hdbddModel.setSchema(((SchemaImpl) type).getName());
            } else if (type instanceof ContextImpl) {
                ContextImpl eContext = ((ContextImpl) type);
                XSKDataStructureContextModel context = new XSKDataStructureContextModel();
                context.setName(eContext.getName());
                hdbddModel.setContext(context);
                List<XSKDataStructureHDBTableConstraintForeignKeyModel> foreignKeys = new ArrayList<XSKDataStructureHDBTableConstraintForeignKeyModel>();
                for (Iterator iteratorEntries = eContext.getEntities().iterator(); iteratorEntries.hasNext();) {

                    Object entry = iteratorEntries.next();
                    if (entry instanceof EntityImpl) {
                        EntityImpl eEntity = (EntityImpl) entry;
                        XSKDataStructureEntityModel entity = new XSKDataStructureEntityModel();
                        entity.setNamespace(hdbddModel.getNamespace());
                        entity.setContext(context.getName());
                        entity.setName(eEntity.getName());
                        for (Iterator iteratorColumns = eEntity.getFields().iterator(); iteratorColumns.hasNext();) {
                            FieldImpl eField = (FieldImpl) iteratorColumns.next();
                            if (eField instanceof FieldPrimitiveImpl) {
                                FieldPrimitiveImpl eFieldPrimitive = (FieldPrimitiveImpl) eField;
                                XSKDataStructureHDBTableColumnModel column = new XSKDataStructureHDBTableColumnModel();
                                //column.setName(CaseFormat.UPPER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, eFieldPrimitive.getName()));
                                column.setName(eFieldPrimitive.getName());
                                column.setPrimaryKey(eFieldPrimitive.isKey());
                                String mappedType = TYPES_MAP.get(eFieldPrimitive.getFieldType());
                                column.setType(mappedType != null ? mappedType: "INTEGER");
                                if (eFieldPrimitive.getFieldLength() != 0) {
                                    column.setLength(eFieldPrimitive.getFieldLength() + "");
                                }
                                entity.getColumns().add(column);
                            } else if (eField instanceof FieldTypeImpl) {
                                FieldTypeImpl eFieldType = (FieldTypeImpl) eField;
                                XSKDataStructureTypeDefinitionModel typeDefinition = context.getTypes().get(eFieldType.getName());
                                if (typeDefinition != null) {
                                    XSKDataStructureHDBTableColumnModel column = new XSKDataStructureHDBTableColumnModel();
                                    // column.setName(CaseFormat.UPPER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, typeDefinition.getName()));
                                    column.setName(typeDefinition.getName());
                                    column.setPrimaryKey(eFieldType.isKey());
                                    String mappedType = TYPES_MAP.get(typeDefinition.getType());
                                    column.setType(mappedType != null ? mappedType: "INTEGER");
                                    if (typeDefinition.getLength() != 0) {
                                        column.setLength(typeDefinition.getLength() + "");
                                    }
                                    entity.getColumns().add(column);
                                } else {
                                    throw new IllegalArgumentException("Undefined field type: " + eFieldType.getName());
                                }
                            }  else if (eField instanceof FieldReferenceImpl) {
                            	FieldReferenceImpl eFieldReference = (FieldReferenceImpl) eField;
                                XSKDataStructureHDBTableConstraintForeignKeyModel referenceDefinition = new XSKDataStructureHDBTableConstraintForeignKeyModel();
                                referenceDefinition.setName(eFieldReference.getFieldReferenceEntity());
                                referenceDefinition.setReferencedTable(entity.getName());
                                String fieldReferenceEntityLocalId = eFieldReference.getFieldReferenceEntityLocalId();
                                fieldReferenceEntityLocalId = fieldReferenceEntityLocalId.substring(fieldReferenceEntityLocalId.indexOf(".") + 1);
                                referenceDefinition.setColumns(new String[] {fieldReferenceEntityLocalId});
								referenceDefinition.setReferencedColumns(new String[] {eFieldReference.getFieldReferenceEntityForeignId()});
								foreignKeys.add(referenceDefinition);
                            } else {
                                throw new IllegalArgumentException("Unknown field type: " + eField.getClass().getCanonicalName());
                            }
                        }
                        context.getЕntities().add(entity);
                    } else if (entry instanceof TypeDefinitionImpl) {
                        TypeDefinitionImpl eTypeDefinition = (TypeDefinitionImpl) entry;
                        XSKDataStructureTypeDefinitionModel typeDefinition = new XSKDataStructureTypeDefinitionModel(
                                eTypeDefinition.getName(), eTypeDefinition.getFieldType(), eTypeDefinition.getFieldLength());
                        context.getTypes().put(typeDefinition.getName(), typeDefinition);
                    } else {
                        throw new IllegalArgumentException("Unknown field entry: " + entry.getClass().getCanonicalName());
                    }
                }
                
                for (XSKDataStructureHDBTableConstraintForeignKeyModel referenceDefinition : foreignKeys) {
                	for (XSKDataStructureEntityModel entity : context.getЕntities()) {
                		if (entity.getName().equals(referenceDefinition.getName())) {
                			entity.getConstraints().getForeignKeys().add(referenceDefinition);
                			break;
                		}
                	}
                }
            }
        }
        return hdbddModel;
    }

    @Override
    public String getType() {
        return IXSKDataStructureModel.TYPE_HDB_ENTITIES;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
	@Override
    public Class getDataStructureClass() {
        return XSKDataStructureEntitiesModel.class;
    }
}
