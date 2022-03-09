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
package com.sap.xsk.xsodata.ds.service;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class XSODataPropertyNameEscaperTest {

    private XSODataPropertyNameEscaper escaper;

    @Before
    public void setUp() {
        this.escaper = new XSODataPropertyNameEscaper();
    }

    @Test
    public void testEscapeWithDots() {
        String propertyName = "Property.Name";
        assertEquals("Unexpected property name", propertyName, escaper.escape(propertyName));
    }

    @Test
    public void testEscapeWithUnderscore() {
        String propertyName = "Property_Name";
        assertEquals("Unexpected property name", propertyName, escaper.escape(propertyName));
    }

}