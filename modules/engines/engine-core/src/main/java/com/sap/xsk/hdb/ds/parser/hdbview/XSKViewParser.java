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
package com.sap.xsk.hdb.ds.parser.hdbview;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Iterator;

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
import com.sap.xsk.models.hdbview.HdbViewStandaloneSetupGenerated;
import com.sap.xsk.models.hdbview.hdbView.HdbViewModel;
import com.sap.xsk.hdb.ds.api.IXSKDataStructureModel;
import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.model.XSKDataStructureDependencyModel;
import com.sap.xsk.hdb.ds.model.hdbview.XSKDataStructureHDBViewModel;
import com.sap.xsk.hdb.ds.parser.XSKDataStructureParser;
import com.sap.xsk.utils.XSKUtils;

public class XSKViewParser implements XSKDataStructureParser {
	
	private static final Logger logger = LoggerFactory.getLogger(XSKViewParser.class);
	
    @Override
    public XSKDataStructureHDBViewModel parse(String location, String content) throws XSKDataStructuresException, IOException {
//    	content = content.replaceAll("'", "");
//        content = content.replaceAll("\"", "");
        
        new org.eclipse.emf.mwe.utils.StandaloneSetup().setPlatformUri("../");
        Injector injector = new HdbViewStandaloneSetupGenerated().createInjectorAndDoEMFRegistration();
        XtextResourceSet resourceSet = injector.getInstance(XtextResourceSet.class);
        resourceSet.addLoadOption(XtextResource.OPTION_RESOLVE_ALL, Boolean.TRUE);

        Resource resource = resourceSet.createResource(URI.createURI("dummy:/example.hdbview"));
        InputStream in = new ByteArrayInputStream(content.getBytes());
        resource.load(in, resourceSet.getLoadOptions());
        HdbViewModel hdbView = (HdbViewModel) resource.getContents().get(0);
//      Not used?
        EList errors = hdbView.eResource().getErrors();
        Iterator errorsIterator = errors.iterator();
        while (errorsIterator.hasNext()) {
        	logger.error(errorsIterator.next().toString());
        }
        XSKDataStructureHDBViewModel hdbViewModel = new XSKDataStructureHDBViewModel();

        hdbViewModel.setName(XSKUtils.getTableName(location));
        hdbViewModel.setLocation(location);
        hdbViewModel.setType(IXSKDataStructureModel.TYPE_HDB_VIEW);
        hdbViewModel.setHash(DigestUtils.md5Hex(content));
        hdbViewModel.setCreatedBy(UserFacade.getName());
        hdbViewModel.setCreatedAt(new Timestamp(new java.util.Date().getTime()));
        
        if (hdbView.getViewElement() != null) {
        
	        hdbViewModel.setSchema(hdbView.getViewElement().getSchemaName());
	        hdbViewModel.setQuery(hdbView.getViewElement().getQueryValue());
	        
	        for (Iterator iterator = hdbView.getViewElement().getDependsOnValues().iterator(); iterator.hasNext();) {
	        	XSKDataStructureDependencyModel dependency = new XSKDataStructureDependencyModel((String)iterator.next(), "");
	        }
        } else {
        	throw new XSKDataStructuresException(String.format("Wrong format of HDB View: [%s] during parsing. Ensure you are using the correct format for the correct compatibility version.", location));
        }
        
        return hdbViewModel;
    }

    @Override
    public String getType() {
        return IXSKDataStructureModel.TYPE_HDB_VIEW;
    }

    @Override
    public Class getDataStructureClass() {
        return XSKDataStructureHDBViewModel.class;
    }
}
