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
package com.sap.xsk.parser.hdbti.models;

import java.util.ArrayList;
import java.util.List;

public class XSKHDBTIImportModel {

  private List<XSKHDBTIImportConfigModel> configModels = new ArrayList<>();

  public List<XSKHDBTIImportConfigModel> getConfigModels() {
    return configModels;
  }

  public void setConfigModels(List<XSKHDBTIImportConfigModel> configModels) {
    this.configModels = configModels;
  }

  @Override
  public String toString() {
    return "import = " + configModels.toString() + ";";
  }

  public void checkMandatoryFieldsInAllConfigModels() {
    configModels.forEach(XSKHDBTIImportConfigModel::checkForAllMandatoryFieldsPresence);
  }
}
