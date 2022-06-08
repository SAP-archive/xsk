/*
 * Copyright (c) 2022 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2022 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.modificators;

import static org.junit.Assert.assertArrayEquals;

import org.eclipse.dirigible.core.test.AbstractDirigibleTest;
import org.junit.Test;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public class CalculationViewTransformationTest extends AbstractDirigibleTest {

  @Test
  public void checkCalculationViewTransformation() throws IOException, TransformerException {
    CalculationViewTransformation testTransformator = new CalculationViewTransformation();
    byte[] sourceXML = getByteArrayFromResourceName("/META-INF/modificators/files-to-modify/testCalculationView.calculationview");
    byte[] expectedXML = getByteArrayFromResourceName("/META-INF/modificators/expected-results/testCalculationView.calculationview");
    byte[] resultXML = testTransformator.removeTypeArtifact(sourceXML);
    assertArrayEquals(expectedXML, resultXML);
  }

  private byte[] getByteArrayFromResourceName(String resourceName) throws IOException {
    return CalculationViewTransformation.class.getResourceAsStream(resourceName).readAllBytes();
  }
}

