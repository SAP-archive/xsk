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
package com.sap.xsk.hdb.ds.model.hdbprocedure;

import com.sap.xsk.hdb.ds.model.XSKDataStructureModel;

public class XSKDataStructureHDBProcedureModel extends XSKDataStructureModel {

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public XSKDataStructureHDBProcedureModel() {

    }

    private XSKDataStructureHDBProcedureModel(HDBProcedureModelBuilder builder) {
        super(builder);
        content = builder.content;
    }


    public static HDBProcedureModelBuilder builder() {
        return new HDBProcedureModelBuilder();
    }

    public static final class HDBProcedureModelBuilder extends XSKDataStructureModel.Builder<HDBProcedureModelBuilder> {
        private String content;

        HDBProcedureModelBuilder() {
        }

        public HDBProcedureModelBuilder content(String content) {
            this.content = content;
            return self();
        }

        @Override
        public XSKDataStructureHDBProcedureModel build() {
            return new XSKDataStructureHDBProcedureModel(this);
        }

        @Override
        protected HDBProcedureModelBuilder self() {
            return this;
        }
    }
}
