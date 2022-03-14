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
package com.sap.xsk.hdb.ds.test.parser;

import com.sap.xsk.exceptions.XSKArtifactParserException;
import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.model.XSKDataStructureModelFactory;
import com.sap.xsk.hdb.ds.model.hdbtablefunction.XSKDataStructureHDBTableFunctionModel;
import org.apache.commons.io.IOUtils;
import org.eclipse.dirigible.core.test.AbstractDirigibleTest;
import org.junit.Test;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class XSKHDBTableFunctionParserTest extends AbstractDirigibleTest {

  @Test(expected = XSKArtifactParserException.class) // Exception expected due to incomplete grammar
  public void parseTableFunction() throws IOException, XSKDataStructuresException, XSKArtifactParserException {
    String location = "/OrderTableFunction.hdbtablefunction";
    InputStream in = XSKTableParserTest.class.getResourceAsStream(location);
    String content = IOUtils.toString(in, StandardCharsets.UTF_8);
    XSKDataStructureHDBTableFunctionModel model = XSKDataStructureModelFactory.parseTableFunction(location, content);

    assertEquals("Unexpected tablefunction schema.", model.getSchema(), "MYSCHEMA");
    assertEquals("Unexpected tablefunction name.", model.getName(), "hdb_view::OrderTableFunction");
    assertEquals("Unexpected tablefunction content.", model.getContent(), content);
    assertEquals("Unexpected tablefunction raw content.", model.getRawContent(), content);
    assertEquals("Unexpected tablefunction location.", model.getLocation(), location);
    assertEquals("Unexpected tablefunction type.", model.getType(), "HDBTABLEFUNC");
    assertEquals("Unexpected tablefunction dependencies.", model.getDependencies().size(), 0);
    assertNotNull("Null value for tablefunction createdAt", model.getCreatedAt());
    assertNotNull("Null value for tablefunction createdBy", model.getCreatedBy());
  }
}
