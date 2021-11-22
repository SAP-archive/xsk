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

import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableConstraintUniqueModel;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableIndexModel;
import com.sap.xsk.parser.hdbtable.exceptions.XSKHDBTableMissingPropertyException;
import com.sap.xsk.parser.hdbtable.model.XSKHDBTABLEColumnsModel;
import com.sap.xsk.parser.hdbtable.model.XSKHDBTABLEDefinitionModel;
import com.sap.xsk.parser.hdbtable.model.XSKHDBTABLEIndexesModel;
import com.sap.xsk.utils.XSKCommonsConstants;
import com.sap.xsk.utils.XSKCommonsUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.sap.xsk.utils.XSKConstants;

public class HDBTableDefinitionModelToHDBTableIndexModelTransformer {

  public List<XSKDataStructureHDBTableIndexModel> transform(XSKHDBTABLEDefinitionModel hdbtableDefinitionModel, String location,
      XSKDataStructureHDBTableIndexModel tableIndexModel) {

    List<XSKDataStructureHDBTableIndexModel> indexes = new ArrayList<>();

    for (XSKHDBTABLEIndexesModel index : hdbtableDefinitionModel.getIndexes()) {
      validateIndex(hdbtableDefinitionModel, location, index);
      if (!index.isUnique()) {
        tableIndexModel.setIndexName(index.getIndexName());
        if (index.getIndexType() != null) {
          tableIndexModel.setIndexType(convertIfHanaClassicSyntax(index));
        }
        tableIndexModel.setUnique(index.isUnique());
        if (index.getOrder() != null) {
          tableIndexModel
              .setOrder(index.getOrder().equals(XSKConstants.HDBTABLE_INDEX_ORDER_HANA_V1_DSC) ? XSKConstants.HDBTABLE_INDEX_ORDER_HANA_DESC : index.getOrder());
        }
        tableIndexModel.setIndexColumns(index.getIndexColumns());
        indexes.add(tableIndexModel);
      }
    }
    return indexes;
  }

  public List<XSKDataStructureHDBTableConstraintUniqueModel> transform(XSKHDBTABLEDefinitionModel hdbtableDefinitionModel, String location,
      XSKDataStructureHDBTableConstraintUniqueModel uniqueIndexModel) {

    List<XSKDataStructureHDBTableConstraintUniqueModel> uniqueIndexes = new ArrayList<>();

    for (XSKHDBTABLEIndexesModel index : hdbtableDefinitionModel.getIndexes()) {
      validateIndex(hdbtableDefinitionModel, location, index);
      if (index.isUnique()) {
        uniqueIndexModel.setName(index.getIndexName());
        if (index.getIndexType() != null) {
          uniqueIndexModel.setIndexType(this.convertIfHanaClassicSyntax(index));
        }
        if (index.getOrder() != null) {
          uniqueIndexModel
              .setOrder(index.getOrder().equals(XSKConstants.HDBTABLE_INDEX_ORDER_HANA_V1_DSC) ? XSKConstants.HDBTABLE_INDEX_ORDER_HANA_DESC : index.getOrder());
        }
        uniqueIndexModel.setColumns(index.getIndexColumns().toArray(String[]::new));

        uniqueIndexes.add(uniqueIndexModel);
      }
    }
    return uniqueIndexes;
  }

  private void validateIndex(XSKHDBTABLEDefinitionModel hdbtableDefinitionModel, String location, XSKHDBTABLEIndexesModel index) {
    try {
      index.checkForAllIndexMandatoryFieldsPresence();
    } catch (Exception e) {
      XSKCommonsUtils.logCustomErrors(location, XSKCommonsConstants.PARSER_ERROR, "", "", e.getMessage(),
          XSKCommonsConstants.EXPECTED_FIELDS, XSKCommonsConstants.HDB_TABLE_PARSER, XSKCommonsConstants.MODULE_PARSERS,
          XSKCommonsConstants.SOURCE_PUBLISH_REQUEST, XSKCommonsConstants.PROGRAM_XSK);
      throw new XSKHDBTableMissingPropertyException(
          String.format("Wrong format of table definition: [%s]. [%s]", location, e.getMessage()));
    }

    index.getIndexColumns().forEach(col -> {
      List<XSKHDBTABLEColumnsModel> foundMatch = hdbtableDefinitionModel.getColumns().stream().filter(x -> x.getName().equals(col))
          .collect(Collectors.toList());
      if (foundMatch.size() != 1) {
        String errMsg = String.format("%s: the column does not have a definition but is specified as an index", col);
        XSKCommonsUtils.logCustomErrors(location, XSKCommonsConstants.PARSER_ERROR, "", "", errMsg,
            "", XSKCommonsConstants.HDB_TABLE_PARSER, XSKCommonsConstants.MODULE_PARSERS,
            XSKCommonsConstants.SOURCE_PUBLISH_REQUEST, XSKCommonsConstants.PROGRAM_XSK);
        throw new IllegalStateException(errMsg);
      }
    });
  }

  private String convertIfHanaClassicSyntax(XSKHDBTABLEIndexesModel index) {
    if (index.getIndexType().equals("CPB_TREE")) {
      return "CPBTREE";
    } else if (index.getIndexType().equals("B_TREE")) {
      return "BTREE";
    } else {
      return index.getIndexType();
    }
  }
}
