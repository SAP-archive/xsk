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
package com.sap.xsk.hdb.ds.model.hdbtablefunction;

import com.sap.xsk.hdb.ds.model.XSKDataStructureModel;

public class XSKDataStructureHDBTableFunctionModel extends XSKDataStructureModel {

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public XSKDataStructureHDBTableFunctionModel() {
    }

    public XSKDataStructureHDBTableFunctionModel(HDBTableFunctionModelBuilder builder) {
        super(builder);
        content = builder.content;
    }

    public static HDBTableFunctionModelBuilder builder() {
        return new HDBTableFunctionModelBuilder();
    }


    public static final class HDBTableFunctionModelBuilder extends XSKDataStructureModel.Builder<HDBTableFunctionModelBuilder> {
        private String content;

        HDBTableFunctionModelBuilder() {
        }

        public HDBTableFunctionModelBuilder content(String content) {
            this.content = content;
            return self();
        }

        @Override
        public XSKDataStructureHDBTableFunctionModel build() {
            return new XSKDataStructureHDBTableFunctionModel(this);
        }

        @Override
        protected HDBTableFunctionModelBuilder self() {
            return this;
        }
    }
}
