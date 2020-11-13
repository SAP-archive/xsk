package com.sap.xsk.hdb.ds.test;


import java.io.InputStream;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.junit.Assert;
import org.junit.Test;

import com.google.inject.Injector;
import com.sap.xsk.models.hdbdd.HdbDDStandaloneSetup;
import com.sap.xsk.models.hdbdd.HdbDDStandaloneSetup;
import com.sap.xsk.models.hdbdd.HdbDDStandaloneSetupGenerated;
import com.sap.xsk.models.hdbdd.hdbDD.HdbDD;

public class ModelParsingJavaTest {
	
	public ModelParsingJavaTest() {
        setupParser();
    }
	
	private void setupParser() {
        Injector injector = new HdbDDStandaloneSetup().createInjectorAndDoEMFRegistration();
        injector.injectMembers(this);
    }
	
	@Test
	public void loadModel() throws Exception {
		new org.eclipse.emf.mwe.utils.StandaloneSetup().setPlatformUri("../");
		Injector injector = new HdbDDStandaloneSetupGenerated().createInjectorAndDoEMFRegistration();
		XtextResourceSet resourceSet = injector.getInstance(XtextResourceSet.class);
		resourceSet.addLoadOption(XtextResource.OPTION_RESOLVE_ALL, Boolean.TRUE);
		
		Resource resource = resourceSet.createResource(URI.createURI("dummy:/example.hdbdd"));
		InputStream in = ModelParsingJavaTest.class.getResourceAsStream("/Products.hdbdd");
		resource.load(in, resourceSet.getLoadOptions());
		HdbDD result = (HdbDD) resource.getContents().get(0);
				
		Assert.assertNotNull(result);
		EList errors = result.eResource().getErrors();
		Assert.assertTrue("Unexpected errors: ", errors.isEmpty());
	}
	
	static String convertStreamToString(java.io.InputStream is) {
	    java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
	    return s.hasNext() ? s.next() : "";
	}
}
