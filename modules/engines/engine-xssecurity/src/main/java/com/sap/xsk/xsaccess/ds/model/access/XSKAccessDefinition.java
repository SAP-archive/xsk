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
package com.sap.xsk.xsaccess.ds.model.access;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "XSK_SECURITY_ACCESS")
public class XSKAccessDefinition {

  @Id
  @Column(name = "ACCESS_PATH", columnDefinition = "VARCHAR", nullable = false, length = 255)
  private String path;

  @Column(name = "AUTHENTICATION_METHODS", columnDefinition = "BLOB", nullable = false, length = 1000)
  private byte[] authenticationMethods;

  private List<String> authenticationMethodsAsList;

  @Column(name = "AUTHORIZATION_ROLES", columnDefinition = "BLOB", nullable = false, length = 1000)
  private byte[] authorizationRoles;

  @Column(name = "EXPOSED", columnDefinition = "BOOLEAN", nullable = false, length = 1)
  private boolean exposed = true;

  @Column(name = "ACCESS_HASH", columnDefinition = "VARCHAR", nullable = true, length = 32)
  private String hash;

  private List<String> authorizationRolesAsList;

  @Column(name = "ACCESS_CREATED_BY", columnDefinition = "VARCHAR", nullable = false, length = 32)
  private String createdBy;

  @Column(name = "ACCESS_CREATED_AT", columnDefinition = "TIMESTAMP", nullable = false)
  private Timestamp createdAt;

  public XSKAccessDefinition() {
  }

  public boolean isExposed() {
    return exposed;
  }

  public void setExposed(boolean exposed) {
    this.exposed = exposed;
  }

  public String getHash() {
    return hash;
  }

  public void setHash(String hash) {
    this.hash = hash;
  }

  public byte[] getAuthorizationRoles() {
    return authorizationRoles;
  }

  public void setAuthorizationRoles(byte[] authorizationRoles) {
    this.authorizationRoles = authorizationRoles;
  }

  public List<String> getAuthorizationRolesAsList() {
    return authorizationRolesAsList;
  }

  public void setAuthorizationRolesAsList(List<String> authorizationRolesAsList) {
    this.authorizationRolesAsList = authorizationRolesAsList;
  }

  public byte[] getAuthenticationMethods() {
    return authenticationMethods;
  }

  public void setAuthenticationMethods(byte[] authenticationMethods) {
    this.authenticationMethods = authenticationMethods;
  }

  public List<String> getAuthenticationMethodsAsList() {
    return authenticationMethodsAsList;
  }

  public void setAuthenticationMethodsAsList(List<String> authenticationMethodsAsList) {
    this.authenticationMethodsAsList = authenticationMethodsAsList;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public Timestamp getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Timestamp createdAt) {
    this.createdAt = createdAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    XSKAccessDefinition that = (XSKAccessDefinition) o;
    return exposed == that.exposed &&
        path.equals(that.path) &&
        authenticationMethodsAsList.equals(that.authenticationMethodsAsList) &&
        Arrays.equals(authorizationRoles, that.authorizationRoles) &&
        hash.equals(that.hash) &&
        authorizationRolesAsList.equals(that.authorizationRolesAsList) &&
        createdBy.equals(that.createdBy) &&
        createdAt.equals(that.createdAt);
  }

  @Override
  public int hashCode() {
    int result = Objects.hash(path, authenticationMethodsAsList, exposed, hash, authorizationRolesAsList, createdBy, createdAt);
    result = 31 * result + Arrays.hashCode(authorizationRoles);
    return result;
  }
}