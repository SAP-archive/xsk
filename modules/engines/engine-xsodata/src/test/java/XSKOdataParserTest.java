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
import com.sap.xsk.xsodata.ds.model.XSKODataModel;
import com.sap.xsk.xsodata.ds.service.XSKODataParser;
import org.eclipse.dirigible.core.test.AbstractGuiceTest;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class XSKOdataParserTest extends AbstractGuiceTest {

    @Test
    public void parseXsodataFileSuccessfully() throws Exception {
        XSKODataParser parser = new XSKODataParser();
        String content = org.apache.commons.io.IOUtils
                .toString(XSKODataUtilsTest.class.getResourceAsStream("/entity_with_all_set_of_navigations.xsodata"), StandardCharsets.UTF_8);
        XSKODataModel xskoDataModel = parser.parseXSODataArtifact("np/entity_with_all_set_of_navigations.xsodata", content);
        assertEquals("entity_with_all_set_of_navigations.xsodata", xskoDataModel.getName());
        assertEquals("np/entity_with_all_set_of_navigations.xsodata", xskoDataModel.getLocation());
        assertNotNull( xskoDataModel.getHash());
        assertEquals("guest", xskoDataModel.getCreatedBy());
        assertNotNull(xskoDataModel.getCreatedAt());
        assertNotNull(xskoDataModel.getService());

        //no need to check the service content the parser module unit tests cover it
    }

    @Test(expected = IllegalArgumentException.class)
    public void testValidateEdmMultiplicity() throws Exception {
        XSKODataParser parser = new XSKODataParser();
        String content = org.apache.commons.io.IOUtils.toString(XSKODataUtilsTest.class.getResourceAsStream("/entity_wrong_syntax.xsodata"), StandardCharsets.UTF_8);
        parser.parseXSODataArtifact("np/entity_wrong_syntax.xsodata", content);
    }
}
