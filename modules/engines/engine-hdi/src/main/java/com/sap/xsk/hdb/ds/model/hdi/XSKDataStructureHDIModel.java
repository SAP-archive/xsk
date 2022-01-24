/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2022 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.hdb.ds.model.hdi;

import com.sap.xsk.hdb.ds.model.XSKDataStructureModel;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class XSKDataStructureHDIModel extends XSKDataStructureModel {

  private String configuration;

  private String[] users;

  private String group;

  private String container;

  private String[] deploy;

  private String[] undeploy;

  private String content;

  public String getConfiguration() {
    return configuration;
  }

  public void setConfiguration(String configuration) {
    this.configuration = configuration;
  }

  public String[] getUsers() {
    return users;
  }

  public void setUsers(String[] users) {
    this.users = users;
  }

  public String getGroup() {
    return group;
  }

  public void setGroup(String group) {
    this.group = group;
  }

  public String getContainer() {
    return container;
  }

  public void setContainer(String container) {
    this.container = container;
  }

  public String[] getDeploy() {
    return deploy;
  }

  public void setDeploy(String[] deploy) {
    this.deploy = deploy;
  }

  public String[] getUndeploy() {
    return undeploy;
  }

  public void setUndeploy(String[] undeploy) {
    this.undeploy = undeploy;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public boolean isMandatoryFieldMissing() {

    return Objects.isNull(this.configuration)
        || Objects.isNull(this.group)
        || Objects.isNull(this.container);
  }

  public boolean hasDeploymentFile() {
    return (Objects.nonNull(this.deploy) && Objects.nonNull(this.undeploy))
        && this.deploy.length >= 1 || this.undeploy.length >= 1;
  }

  public boolean hasMisusedDeploymentFile() {
    if ((Objects.nonNull(this.deploy) && Objects.nonNull(this.undeploy))) {
      Set<String> deploy = new HashSet<>(Arrays.asList(this.deploy));
      Set<String> undeploy = new HashSet<>(Arrays.asList(this.undeploy));
      deploy.retainAll(undeploy);
      return deploy.size() > 0;
    }

    return false;
  }
}
