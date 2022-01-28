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

import com.sap.xsk.utils.XSKCommonsConstants;
import com.sap.xsk.utils.XSKCommonsUtils;
import java.sql.SQLException;
import java.util.List;
import org.eclipse.dirigible.engine.odata2.definition.ODataDefinition;
import org.eclipse.dirigible.engine.odata2.definition.ODataHandlerDefinition;
import org.eclipse.dirigible.engine.odata2.transformers.OData2ODataHTransformer;

public class XSKOData2ODataHTransformer {

  private OData2ODataHTransformer oData2ODataHTransformer = new OData2ODataHTransformer();

  public List<ODataHandlerDefinition> transform(ODataDefinition oDataDefinition) throws SQLException {
    try {
      return oData2ODataHTransformer.transform(oDataDefinition);
    } catch (Exception e) {
      XSKCommonsUtils.logProcessorErrors(e.getMessage(), XSKCommonsConstants.PROCESSOR_ERROR, oDataDefinition.getLocation(), XSKCommonsConstants.XSK_ODATA_PARSER);
      throw e;
    }
  }
}
