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

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.StringReader;

public class CalculationViewDataSourceTransformation {

  private static final String CALCULATION_VIEW_DATA_SOURCE_TRANSFORMATION_XSLT = "<xsl:stylesheet version=\"1.0\" xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\">\n"
      + "    <xsl:template match=\"DataSource/@type\" />\n"
      + "    <xsl:template match=\"@*|node()\">\n"
      + "        <xsl:copy>\n"
      + "            <xsl:apply-templates select=\"@*|node()\"/>\n"
      + "        </xsl:copy>\n"
      + "    </xsl:template>\n"
      + "</xsl:stylesheet>";

  public byte[] removeTypeArtifact(byte[] bytes) throws TransformerException {
    TransformerFactory factory = TransformerFactory.newInstance();
    Source source = new StreamSource(new StringReader(CALCULATION_VIEW_DATA_SOURCE_TRANSFORMATION_XSLT));
    Transformer transformer = factory.newTransformer(source);
    StreamSource text = new StreamSource(new ByteArrayInputStream(bytes));
    ByteArrayOutputStream bout = new ByteArrayOutputStream();
    transformer.transform(text, new StreamResult(bout));
    return bout.toByteArray();
  }
}
