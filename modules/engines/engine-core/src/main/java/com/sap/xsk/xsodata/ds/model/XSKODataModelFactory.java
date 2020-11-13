/**
 * Copyright (c) 2010-2018 SAP
 */
package com.sap.xsk.xsodata.ds.model;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Injector;
import com.sap.xsk.models.xsodata.XSODataStandaloneSetup;
import com.sap.xsk.models.xsodata.XSODataStandaloneSetup;
import com.sap.xsk.models.xsodata.XSODataStandaloneSetupGenerated;
import com.sap.xsk.models.xsodata.xsOData.Navigation;
import com.sap.xsk.models.xsodata.xsOData.Service;
import com.sap.xsk.models.xsodata.xsOData.XSOData;
import com.sap.xsk.models.xsodata.xsOData.impl.AssociationImpl;
import com.sap.xsk.models.xsodata.xsOData.impl.EntityImpl;

/**
 * The factory for creation of the data structure models from source content.
 */
public class XSKODataModelFactory {
	
	private static final Logger logger = LoggerFactory.getLogger(XSKODataModelFactory.class);
	
	static Map<String, String> TYPES_MAP = new HashMap<String, String>();
	
	static {
		TYPES_MAP.put("String", "VARCHAR");
		TYPES_MAP.put("UTCTimestamp", "TIMESTAMP");
	}
	
	public XSKODataModelFactory() {
        setupParser();
    }
	
	private void setupParser() {
        Injector injector = new XSODataStandaloneSetup().createInjectorAndDoEMFRegistration();
        injector.injectMembers(this);
    }
	
	/**
	 * Creates a odata model from the raw content.
	 *
	 * @param content
	 *            the odata definition
	 * @return the odata model instance
	 * @throws Exception 
	 */
	public XSKODataModel parseOData(String location, String content) throws Exception {
		
		content = content.replace('"', ' ');
		
		new org.eclipse.emf.mwe.utils.StandaloneSetup().setPlatformUri("../");
		Injector injector = new XSODataStandaloneSetupGenerated().createInjectorAndDoEMFRegistration();
		XtextResourceSet resourceSet = injector.getInstance(XtextResourceSet.class);
		resourceSet.addLoadOption(XtextResource.OPTION_RESOLVE_ALL, Boolean.TRUE);
		
		Resource resource = resourceSet.createResource(URI.createURI("dummy:/example.xsodata"));
		InputStream in = new ByteArrayInputStream(content.getBytes()); 
		resource.load(in, resourceSet.getLoadOptions());
		XSOData xsOData = (XSOData) resource.getContents().get(0);
		
		
		EList errors = xsOData.eResource().getErrors();
		Iterator errorsIterator = errors.iterator();
        while (errorsIterator.hasNext()) {
        	logger.error(errorsIterator.next().toString());
        }
		XSKODataModel odataModel = new XSKODataModel();
		
		odataModel.setName(new File(location).getName());
		odataModel.setLocation(location);
		odataModel.setHash(DigestUtils.md5Hex(content));
		odataModel.setCreatedBy(UserFacade.getName());
		odataModel.setCreatedAt(new Timestamp(new java.util.Date().getTime()));
		
		for (Iterator iterator = xsOData.getElements().iterator(); iterator.hasNext();) {
			com.sap.xsk.models.xsodata.xsOData.Type type = (com.sap.xsk.models.xsodata.xsOData.Type) iterator.next();
			if (type instanceof Service) {
				com.sap.xsk.models.xsodata.xsOData.Service service = (com.sap.xsk.models.xsodata.xsOData.Service) type;
				XSKODataService odataService = new XSKODataService();
				odataService.setNamespace(service.getName());
				odataModel.setService(odataService);
				Iterator iteratorEntities = service.getEntities().iterator();
				while (iteratorEntities.hasNext()) {
					EntityImpl eEntity = (EntityImpl) iteratorEntities.next();
					XSKODataEntity odataEntity = new XSKODataEntity();
					odataEntity.setNamespace(eEntity.getNamespace());
					odataEntity.setName(eEntity.getName());
					odataEntity.setAlias(eEntity.getAlias());
					for (Iterator iteratorNavigates = eEntity.getNavigates().iterator(); iteratorNavigates.hasNext();) {
						Navigation eNavigate = (Navigation) iteratorNavigates.next();
						XSKODataNavigation odataNavigation = new XSKODataNavigation();
						odataNavigation.setAssociation(eNavigate.getAssociation());
						odataNavigation.setAlias(eNavigate.getAlias());
						odataEntity.getNavigates().add(odataNavigation);
					}
					odataService.getEntities().add(odataEntity);
				}
				Iterator iteratorAssociations = service.getAssociations().iterator();
				while (iteratorAssociations.hasNext()) {
					AssociationImpl eAssociation = (AssociationImpl) iteratorAssociations.next();
					XSKODataAssociation odataAssociation = new XSKODataAssociation();
					odataAssociation.setName(eAssociation.getName());
					odataAssociation.setPrincipal(eAssociation.getPrincipal());
					odataAssociation.setPrincipalKey(eAssociation.getPrincipalKey());
					odataAssociation.setPrincipalMultiplicity(eAssociation.getPrincipalMultiplicity());
					odataAssociation.setDependent(eAssociation.getDependent());
					odataAssociation.setDependentProperty(eAssociation.getDependentProperty());
					odataAssociation.setDependentMultiplicity(eAssociation.getDependentMultiplicity());					
					odataService.getAssociations().add(odataAssociation);
				}
			}
		}
		return odataModel;
	}

	/**
	 * Creates a odata model from the raw content.
	 *
	 * @param bytes
	 *            the odata definition
	 * @return the odata model instance
	 * @throws Exception 
	 */
	public XSKODataModel parseOData(String location, byte[] bytes) throws Exception {
		return parseOData(location, new String(bytes));
	}

}
