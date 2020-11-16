/*
 * Copyright (c) 2019-2020 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2019-2020 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.hdb.ds.model;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.eclipse.dirigible.api.v3.security.UserFacade;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;

import com.google.inject.Injector;
import com.sap.xsk.models.hdbdd.HdbDDStandaloneSetup;
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
import com.sap.xsk.hdb.ds.model.calculationview.XSKDataStructureCalculationViewModel;
import com.sap.xsk.hdb.ds.model.hdbdd.XSKDataStructureContextModel;
import com.sap.xsk.hdb.ds.model.hdbdd.XSKDataStructureEntitiesModel;
import com.sap.xsk.hdb.ds.model.hdbdd.XSKDataStructureEntityModel;
import com.sap.xsk.hdb.ds.model.hdbdd.XSKDataStructureTypeDefinitionModel;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableColumnModel;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableModel;
import com.sap.xsk.hdb.ds.model.hdbview.XSKDataStructureHDBViewModel;

/**
 * The factory for creation of the data structure models from source content.
 */
public class XSKDataStructureModelFactory {
	
	static Map<String, String> TYPES_MAP = new HashMap<String, String>();
	
	static {
		TYPES_MAP.put("String", "VARCHAR");
		TYPES_MAP.put("UTCTimestamp", "TIMESTAMP");
	}
	
	public XSKDataStructureModelFactory() {
        setupParser();
    }
	
	private void setupParser() {
        Injector injector = new HdbDDStandaloneSetup().createInjectorAndDoEMFRegistration();
        injector.injectMembers(this);
    }

	/**
	 * Creates a table model from the raw content.
	 *
	 * @param content
	 *            the table definition
	 * @return the table model instance
	 */
	public static XSKDataStructureHDBTableModel parseTable(String location, String content) {
		
		content = content.replace('"', ' ');
		
		XSKDataStructureHDBTableModel result = new XSKDataStructureHDBTableModel();
		result.setName(new File(location).getName());
		result.setLocation(location);
		result.setType(IXSKDataStructureModel.TYPE_HDB_TABLE);
		result.setHash(DigestUtils.md5Hex(content));
		result.setCreatedBy(UserFacade.getName());
		result.setCreatedAt(new Timestamp(new java.util.Date().getTime()));
		
		// ... actual parsing
		
		
		return result;
	}

	/**
	 * Creates a table model from the raw content.
	 *
	 * @param bytes
	 *            the table definition
	 * @return the table model instance
	 */
	public static XSKDataStructureHDBTableModel parseTable(String location, byte[] bytes) {
		return parseTable(location, new String(bytes));
	}

	/**
	 * Creates a view model from the raw content.
	 *
	 * @param content
	 *            the view definition
	 * @return the view model instance
	 */
	public static XSKDataStructureHDBViewModel parseView(String location, String content) {
		
		content = content.replace('"', ' ');
		
		XSKDataStructureHDBViewModel result = new XSKDataStructureHDBViewModel();
		result.setName(new File(location).getName());
		result.setLocation(location);
		result.setType(IXSKDataStructureModel.TYPE_HDB_VIEW);
		result.setHash(DigestUtils.md5Hex(content));
		result.setCreatedBy(UserFacade.getName());
		result.setCreatedAt(new Timestamp(new java.util.Date().getTime()));
		
		// ... actual parsing
		
		
		return result;
	}

	/**
	 * Creates a view model from the raw content.
	 *
	 * @param bytes
	 *            the view definition
	 * @return the view model instance
	 */
	public static XSKDataStructureHDBViewModel parseView(String location, byte[] bytes) {
		return parseView(location, new String(bytes));
	}
	
	/**
	 * Creates a entities model from the raw content.
	 *
	 * @param content
	 *            the entities definition
	 * @return the entities model instance
	 * @throws Exception 
	 */
	public XSKDataStructureEntitiesModel parseEntities(String location, String content) throws Exception {
		
		content = content.replace('"', ' ');
		
		new org.eclipse.emf.mwe.utils.StandaloneSetup().setPlatformUri("../");
		Injector injector = new HdbDDStandaloneSetupGenerated().createInjectorAndDoEMFRegistration();
		XtextResourceSet resourceSet = injector.getInstance(XtextResourceSet.class);
		resourceSet.addLoadOption(XtextResource.OPTION_RESOLVE_ALL, Boolean.TRUE);
		
		Resource resource = resourceSet.createResource(URI.createURI("dummy:/example.hdbdd"));
		InputStream in = new ByteArrayInputStream(content.getBytes()); 
		resource.load(in, resourceSet.getLoadOptions());
		HdbDD hdbDD = (HdbDD) resource.getContents().get(0);
		
		EList errors = hdbDD.eResource().getErrors();
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
			}
		}
		return hdbddModel;
	}

	/**
	 * Creates a entities model from the raw content.
	 *
	 * @param bytes
	 *            the entities definition
	 * @return the entities model instance
	 * @throws Exception 
	 */
	public XSKDataStructureEntitiesModel parseEntities(String location, byte[] bytes) throws Exception {
		return parseEntities(location, new String(bytes));
	}

	/**
	 * Creates a calculation view model from the raw content.
	 *
	 * @param xml
	 *            the XML definition
	 * @return the calculation view model instance
	 * @throws Exception 
	 */
	public static XSKDataStructureCalculationViewModel parseCalcView(String location, String xml) {
		XSKDataStructureCalculationViewModel calcviewModel = new XSKDataStructureCalculationViewModel();
		calcviewModel.setName(new File(location).getName());
		calcviewModel.setLocation(location);
		calcviewModel.setType(IXSKDataStructureModel.TYPE_CALCVIEW);
		calcviewModel.setHash(DigestUtils.md5Hex(xml));
		calcviewModel.setCreatedBy(UserFacade.getName());
		calcviewModel.setCreatedAt(new Timestamp(new java.util.Date().getTime()));
		calcviewModel.setXml(xml);
		return calcviewModel;
	}

	/**
	 * Creates a calculation view model from the raw content.
	 *
	 * @param xml
	 *            the XML definition
	 * @return the calculation view model instance
	 * @throws Exception 
	 */
	public static XSKDataStructureCalculationViewModel parseCalcView(String location, byte[] xml) {
		return parseCalcView(location, new String(xml));
	}

}
