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
package models;

import exceptions.TableFunctionMissingPropertyException;

public class TableFunctionDefinitionModel extends DefinitionModel {


    public TableFunctionDefinitionModel(String schema, String name) {
        super(schema, name);
    }

    public void checkForAllMandatoryFieldsPresence() {
        if (this.getName() == null) {
            throw new TableFunctionMissingPropertyException("Missing mandatory field name");
        }

    }

}
