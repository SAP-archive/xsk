/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2021 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.hdb.ds.transformer;

import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableColumnModel;
import com.sap.xsk.parser.hdbtable.exceptions.XSKHDBTableMissingPropertyException;
import com.sap.xsk.parser.hdbtable.model.XSKHDBTABLEColumnsModel;
import com.sap.xsk.parser.hdbtable.model.XSKHDBTABLEDefinitionModel;
import com.sap.xsk.utils.XSKCommonsConstants;
import com.sap.xsk.utils.XSKCommonsUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HDBTableDefinitionModelToHDBTableColumnModelTransformer {


  public List<XSKDataStructureHDBTableColumnModel> transform(XSKHDBTABLEDefinitionModel hdbtableDefinitionModel, String location) {

    List<XSKDataStructureHDBTableColumnModel> columns = new ArrayList<>();

    for (XSKHDBTABLEColumnsModel column : hdbtableDefinitionModel.getColumns()) {
      try {
        column.checkForAllMandatoryColumnFieldsPresence();
      } catch (Exception e) {
        XSKCommonsUtils.logCustomErrors(location, XSKCommonsConstants.PARSER_ERROR, "", "", e.getMessage(),
            XSKCommonsConstants.EXPECTED_FIELDS, XSKCommonsConstants.HDB_TABLE_PARSER,XSKCommonsConstants.MODULE_PARSERS,
            XSKCommonsConstants.SOURCE_PUBLISH_REQUEST, XSKCommonsConstants.PROGRAM_XSK);
        throw new XSKHDBTableMissingPropertyException(String.format("Wrong format of table definition: [%s]. [%s]", location, e.getMessage()));
      }
      XSKDataStructureHDBTableColumnModel dataStructureHDBTableColumnModel = new XSKDataStructureHDBTableColumnModel();
      dataStructureHDBTableColumnModel.setLength(column.getLength());
      dataStructureHDBTableColumnModel.setName(column.getName());
      dataStructureHDBTableColumnModel.setType(column.getSqlType());
      dataStructureHDBTableColumnModel.setComment(column.getComment());
      dataStructureHDBTableColumnModel.setNullable(column.isNullable());
      dataStructureHDBTableColumnModel.setDefaultValue(column.getDefaultValue());
      dataStructureHDBTableColumnModel.setPrecision(column.getPrecision());
      dataStructureHDBTableColumnModel.setScale(column.getScale());
      dataStructureHDBTableColumnModel.setUnique(column.isUnique());
      List<String> foundMatchKey = hdbtableDefinitionModel.getPkColumns().stream().filter(key -> key.equals(column.getName())).collect(
          Collectors.toList());
      if (foundMatchKey.size() == 1) {
        dataStructureHDBTableColumnModel.setPrimaryKey(true);
      }
      columns.add(dataStructureHDBTableColumnModel);
    }

    return columns;
  }
}
