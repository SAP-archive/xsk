package com.sap.xsk.models.hdbdd.tests;

import org.eclipse.emf.common.util.EList;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.junit.Assert;
import org.junit.Test;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.sap.xsk.models.hdbdd.HdbDDStandaloneSetup;
import com.sap.xsk.models.hdbdd.hdbDD.HdbDD;

public class HdbDDParsingJavaTest {
	
	@Inject
	ParseHelper<HdbDD> parseHelper;
	
	public HdbDDParsingJavaTest() {
        setupParser();
    }
	
	private void setupParser() {
        Injector injector = new HdbDDStandaloneSetup().createInjectorAndDoEMFRegistration();
        injector.injectMembers(this);
    }
	
	@Test
	public void loadModel() throws Exception {
		String metadata = convertStreamToString(HdbDDParsingJavaTest.class.getResourceAsStream("/Products.hdbdd"));
		HdbDD result = parseHelper.parse(metadata);
		Assert.assertNotNull(result);
		EList errors = result.eResource().getErrors();
		if (!errors.isEmpty()) {
			System.out.println(errors);
		}
		Assert.assertTrue("Unexpected errors: ", errors.isEmpty());
		
	}
	
	static String convertStreamToString(java.io.InputStream is) {
	    java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
	    return s.hasNext() ? s.next() : "";
	}
}
