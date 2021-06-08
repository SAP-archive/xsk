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
import com.sap.xsk.parser.xsodata.model.XSKHDBXSODATAService;
import com.sap.xsk.xsodata.ds.model.XSKODataModel;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class XSKOdataModelTest {

    @Test
    public void testEqualsOfTwoObjects(){
        XSKODataModel model1 = new XSKODataModel();
        model1.setName("model");
        model1.setLocation("np/model.xsodata");
        model1.setHash("111");
        model1.setCreatedBy("user1");
        model1.setService(new XSKHDBXSODATAService());

        XSKODataModel model2 = new XSKODataModel();
        model2.setName("model");
        model2.setLocation("np/model.xsodata");
        model2.setHash("111");
        model2.setCreatedBy("user2");
        model2.setService(new XSKHDBXSODATAService());

        assertEquals(model1, model2);
    }
}
