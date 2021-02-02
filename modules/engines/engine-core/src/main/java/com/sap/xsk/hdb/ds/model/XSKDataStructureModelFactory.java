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
import com.sap.xsk.hdb.ds.parser.hdbdd.XSKEntitiesParser;
import com.sap.xsk.hdb.ds.parser.hdbtable.XSKTableParser;
import com.sap.xsk.hdb.ds.parser.hdbview.XSKViewParser;

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
	public static XSKDataStructureHDBTableModel parseTable(String location, String content) throws Exception {
		XSKTableParser parser = new XSKTableParser();
		XSKDataStructureHDBTableModel result = parser.parse(location, content);
		return result;
	}

	/**
	 * Creates a table model from the raw content.
	 *
	 * @param bytes
	 *            the table definition
	 * @return the table model instance
	 */
	public static XSKDataStructureHDBTableModel parseTable(String location, byte[] bytes) throws Exception {
		return parseTable(location, new String(bytes));
	}

	/**
	 * Creates a view model from the raw content.
	 *
	 * @param content
	 *            the view definition
	 * @return the view model instance
	 */
	public static XSKDataStructureHDBViewModel parseView(String location, String content) throws Exception {
		XSKViewParser parser = new XSKViewParser();
		XSKDataStructureHDBViewModel result = parser.parse(location, content);
		return result;
	}

	/**
	 * Creates a view model from the raw content.
	 *
	 * @param bytes
	 *            the view definition
	 * @return the view model instance
	 */
	public static XSKDataStructureHDBViewModel parseView(String location, byte[] bytes) throws Exception {
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
		XSKEntitiesParser parser = new XSKEntitiesParser();
		XSKDataStructureEntitiesModel result = parser.parse(location, content);
		return result;
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

//	/**
//	 * Creates a calculation view model from the raw content.
//	 *
//	 * @param xml
//	 *            the XML definition
//	 * @return the calculation view model instance
//	 * @throws Exception 
//	 */
//	public static XSKDataStructureCalculationViewModel parseCalcView(String location, String xml) {
//		XSKDataStructureCalculationViewModel calcviewModel = new XSKDataStructureCalculationViewModel();
//		calcviewModel.setName(new File(location).getName());
//		calcviewModel.setLocation(location);
//		calcviewModel.setType(IXSKDataStructureModel.TYPE_CALCVIEW);
//		calcviewModel.setHash(DigestUtils.md5Hex(xml));
//		calcviewModel.setCreatedBy(UserFacade.getName());
//		calcviewModel.setCreatedAt(new Timestamp(new java.util.Date().getTime()));
//		calcviewModel.setXml(xml);
//		return calcviewModel;
//	}
//
//	/**
//	 * Creates a calculation view model from the raw content.
//	 *
//	 * @param xml
//	 *            the XML definition
//	 * @return the calculation view model instance
//	 * @throws Exception 
//	 */
//	public static XSKDataStructureCalculationViewModel parseCalcView(String location, byte[] xml) {
//		return parseCalcView(location, new String(xml));
//	}

}
