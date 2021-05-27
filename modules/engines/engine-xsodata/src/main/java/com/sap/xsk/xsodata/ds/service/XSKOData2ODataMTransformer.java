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
package com.sap.xsk.xsodata.ds.service;

import com.sap.xsk.xsodata.ds.model.XSKODataModel;
import com.sap.xsk.xsodata.utils.XSKODataUtils;
import org.eclipse.dirigible.commons.config.Configuration;
import org.eclipse.dirigible.engine.odata2.transformers.DBMetadataUtil;
import org.eclipse.dirigible.engine.odata2.transformers.OData2ODataMTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.sql.SQLException;

@Singleton
public class XSKOData2ODataMTransformer {

    private static final Logger logger = LoggerFactory.getLogger(XSKOData2ODataMTransformer.class);

    @Inject
    private DBMetadataUtil dbMetadataUtil;

    @Inject
    private OData2ODataMTransformer oData2ODataMTransformer;

    public String[] transform(XSKODataModel xskoDataModel) throws SQLException {
        if (xskoDataModel.getService() == null) {
            logger.error("Service element is null for xsodata file {}, so it will be skipped. Maybe the format is wrong and cannot be parsed.",
                    xskoDataModel.getName());
        }

        //The xsk classic generate the odata properties without prettying them
        String oldValue = Configuration.get(DBMetadataUtil.DIRIGIBLE_GENERATE_PRETTY_NAMES);
        Configuration.set(DBMetadataUtil.DIRIGIBLE_GENERATE_PRETTY_NAMES, "false");
        String[] result = oData2ODataMTransformer.transform(XSKODataUtils.convertXSKODataModelToODataDefinition(xskoDataModel, dbMetadataUtil));
        Configuration.set(DBMetadataUtil.DIRIGIBLE_GENERATE_PRETTY_NAMES, oldValue);

        return result;
    }
}
