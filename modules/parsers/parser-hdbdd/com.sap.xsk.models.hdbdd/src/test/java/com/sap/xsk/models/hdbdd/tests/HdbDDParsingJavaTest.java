/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2021 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.models.hdbdd.tests;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.sap.xsk.models.hdbdd.HdbDDStandaloneSetup;
import com.sap.xsk.models.hdbdd.hdbDD.HdbDD;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.junit.Assert;
import org.junit.Test;

public class HdbDDParsingJavaTest {

  @Inject
  ParseHelper<HdbDD> parseHelper;

  public HdbDDParsingJavaTest() {
    setupParser();
  }

  static String convertStreamToString(java.io.InputStream is) {
    java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
    return s.hasNext() ? s.next() : "";
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
}
