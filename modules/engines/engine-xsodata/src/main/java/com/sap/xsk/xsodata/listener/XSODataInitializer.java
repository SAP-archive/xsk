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
package com.sap.xsk.xsodata.listener;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.regex.Pattern;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.olingo.odata2.core.edm.provider.EdmNamedImplProv;

public class XSODataInitializer implements ServletContextListener {

    // OData property name pattern, Olingo default pattern do not allow dot in the property name.
    // Which is the case when you have a data types in HDBDD file, to support XS Classic use cases we need to allow dots in OData property names.
    private static final Pattern ODATA_PROPERTY_NAME_PATTERN = Pattern.compile("\\A[_\\p{L}\\p{Nl}][_.\\p{L}\\p{Nl}\\p{Nd}\\p{Mn}\\p{Mc}\\p{Pc}\\p{Cf}]{0,}\\Z");

    private static final String NAME_OF_THE_PROPERTY_NAME_PATTERN_FIELD = "PATTERN_VALID_NAME";

    public void contextInitialized(ServletContextEvent sce) {
        try {
            setFinalStatic(EdmNamedImplProv.class.getDeclaredField(NAME_OF_THE_PROPERTY_NAME_PATTERN_FIELD), ODATA_PROPERTY_NAME_PATTERN);
        } catch (Exception e) {
            throw new IllegalStateException("Failed to replace default Olingo OData parameter name pattern.", e);
        }
    }

    private void setFinalStatic(Field field, Object newValue) throws Exception {
        field.setAccessible(true);

        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

        field.set(null, newValue);
    }

}
