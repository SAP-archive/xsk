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

public class XSLTTransform {

  String xslt = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n"
      + "<xsl:stylesheet version=\"1.0\" xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" xmlns:exsl=\"http://exslt.org/common\">\n"
      + "\n"
      + "  <!-- Remove from specified children. -->\n"
      + "  <xsl:variable name=\"remove\">\n"
      + "  </xsl:variable>\n"
      + "\n"
      + "  <!-- Match \"type\" attribute. -->\n"
      + "  <xsl:template match=\"DataSource/@type\">\n"
      + "    <xsl:if test=\"not(exsl:node-set($remove))\">\n"
      + "      <xsl:copy/>\n"
      + "    </xsl:if>\n"
      + "  </xsl:template>\n"
      + "\n"
      + "  <!-- Copy all nodes and attributes unless another rule indicates otherwise. -->\n"
      + "  <xsl:template match=\"@*|node()\">\n"
      + "    <xsl:copy>\n"
      + "      <xsl:apply-templates select=\"@*|node()\"/>\n"
      + "    </xsl:copy>\n"
      + "  </xsl:template>\n"
      + "</xsl:stylesheet>\n";

  public byte[] removeTypeArtifact(byte[] bytes) throws TransformerException {
    TransformerFactory factory = TransformerFactory.newInstance();
    Source source = new StreamSource(new StringReader(xslt));
    Transformer transformer = factory.newTransformer(source);
    StreamSource text = new StreamSource(new ByteArrayInputStream(bytes));
    ByteArrayOutputStream bout = new ByteArrayOutputStream();
    transformer.transform(text, new StreamResult(bout));
    return bout.toByteArray();
  }
}
