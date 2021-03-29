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
package com.sap.xsk.hdb.ds.parser.hdbtable;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
import com.sap.xsk.models.hdbtable.HdbTableStandaloneSetupGenerated;
import com.sap.xsk.models.hdbtable.hdbTable.HdbTableModel;
import com.sap.xsk.hdb.ds.api.IXSKDataStructureModel;
import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableColumnModel;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableConstraintPrimaryKeyModel;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableModel;
import com.sap.xsk.hdb.ds.parser.XSKDataStructureParser;
import com.sap.xsk.utils.XSKUtils;

public class XSKTableParser implements XSKDataStructureParser<XSKDataStructureHDBTableModel> {
	
	private static final Logger logger = LoggerFactory.getLogger(XSKTableParser.class);

    @Override
    public String getType() {
        return IXSKDataStructureModel.TYPE_HDB_TABLE;
    }

    @Override
    public Class<XSKDataStructureHDBTableModel> getDataStructureClass() {
        return XSKDataStructureHDBTableModel.class;
    }

    @Override
    public XSKDataStructureHDBTableModel parse(String location, String content) throws XSKDataStructuresException, IOException {
//    	content = content.replaceAll("'", "");
//        content = content.replaceAll("\"", "");
        
        new org.eclipse.emf.mwe.utils.StandaloneSetup().setPlatformUri("../");
        Injector injector = new HdbTableStandaloneSetupGenerated().createInjectorAndDoEMFRegistration();
        XtextResourceSet resourceSet = injector.getInstance(XtextResourceSet.class);
        resourceSet.addLoadOption(XtextResource.OPTION_RESOLVE_ALL, Boolean.TRUE);

        Resource resource = resourceSet.createResource(URI.createURI("dummy:/example.hdbtable"));
        InputStream in = new ByteArrayInputStream(content.getBytes());
        resource.load(in, resourceSet.getLoadOptions());
        HdbTableModel hdbTable = (HdbTableModel) resource.getContents().get(0);
//      Not used?
        EList errors = hdbTable.eResource().getErrors();
        Iterator errorsIterator = errors.iterator();
        while (errorsIterator.hasNext()) {
        	logger.error(errorsIterator.next().toString());
        }
        XSKDataStructureHDBTableModel hdbTableModel = new XSKDataStructureHDBTableModel();

        hdbTableModel.setName(XSKUtils.getRepositoryBaseObjectName(location));
        hdbTableModel.setLocation(location);
        hdbTableModel.setType(IXSKDataStructureModel.TYPE_HDB_TABLE);
        hdbTableModel.setHash(DigestUtils.md5Hex(content));
        hdbTableModel.setCreatedBy(UserFacade.getName());
        hdbTableModel.setCreatedAt(new Timestamp(new java.util.Date().getTime()));
        
        if (hdbTable.getTableElement() != null) {
	        hdbTableModel.setSchema(hdbTable.getTableElement().getSchemaName());
	        hdbTableModel.setTableType(hdbTable.getTableElement().getTypeValue());
	        hdbTableModel.setDescription(hdbTable.getTableElement().getDescriptionText());
	        
	        for (Iterator iterator = hdbTable.getTableElement().getColumnsValues().iterator(); iterator.hasNext();) {
	            com.sap.xsk.models.hdbtable.hdbTable.ColumnType columnType = (com.sap.xsk.models.hdbtable.hdbTable.ColumnType) iterator.next();
	            XSKDataStructureHDBTableColumnModel column = new XSKDataStructureHDBTableColumnModel();
	            column.setName(columnType.getColumnName());
	            column.setLength(columnType.getColumnLength() + "");
	            column.setNullable(Boolean.parseBoolean(columnType.getColumnNullable()));
	            column.setType(columnType.getColumnSqlType());
	            if (columnType.getColumnPrecision() > 0) {
	            	column.setPrecision(columnType.getColumnPrecision() + "");
	            }
	            if (columnType.getColumnScale() > 0) {
	            	column.setScale(columnType.getColumnScale() + "");
	            }
	            if (columnType.getColumnDefaultValue() != null) {
	            	column.setDefaultValue(columnType.getColumnDefaultValue());
	            }
	            if (columnType.getColumnComment() != null) {
	            	//column.setComment(columnType.getColumnComment());
	            }
	            hdbTableModel.getColumns().add(column);
	        }
	        
	        for (Iterator iterator = hdbTable.getTableElement().getIndexesValues().iterator(); iterator.hasNext();) {
	            com.sap.xsk.models.hdbtable.hdbTable.IndexType indexType = (com.sap.xsk.models.hdbtable.hdbTable.IndexType) iterator.next();
//	            XSKDataStructureHDBTableIndexModel index = new XSKDataStructureHDBTableIndexModel();
	            
	            logger.warn("Index processing not yet supported: {}", location);
	            
	        }
	        
	        List<String> keys = new ArrayList<>();
	        for (Iterator iterator = hdbTable.getTableElement().getTablePrimaryKeyColumnsValues().iterator(); iterator.hasNext();) {
	        	keys.add((String) iterator.next());
	        }
	        if (!keys.isEmpty()) {
	        	XSKDataStructureHDBTableConstraintPrimaryKeyModel primaryKey = new XSKDataStructureHDBTableConstraintPrimaryKeyModel();
	        	primaryKey.setColumns(keys.toArray(new String[]{}));
	        	hdbTableModel.getConstraints().setPrimaryKey(primaryKey);
	        }
        } else {
        	throw new XSKDataStructuresException(String.format("Wrong format of HDB Table: [%s] during parsing. Ensure you are using the correct format for the correct compatibility version.", location));
        }
        
        return hdbTableModel;
    }
}
