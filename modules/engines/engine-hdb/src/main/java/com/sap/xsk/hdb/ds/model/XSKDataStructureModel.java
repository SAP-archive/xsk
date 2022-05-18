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
package com.sap.xsk.hdb.ds.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.eclipse.dirigible.commons.api.helpers.GsonHelper;

/**
 * The basis for all the data structure models.
 */
@Table(name = "XSK_DATA_STRUCTURES")
public class XSKDataStructureModel {

  @Id
  @Column(name = "DS_LOCATION", columnDefinition = "VARCHAR", nullable = false, length = 255)
  private String location;

  @Column(name = "DS_NAME", columnDefinition = "VARCHAR", nullable = false, length = 255)
  private String name;

  @Column(name = "DS_TYPE", columnDefinition = "VARCHAR", nullable = false, length = 20)
  private String type;

  @Column(name = "DS_HASH", columnDefinition = "VARCHAR", nullable = false, length = 32)
  private String hash;

  @Column(name = "DS_CREATED_BY", columnDefinition = "VARCHAR", nullable = false, length = 32)
  private String createdBy;

  @Column(name = "DS_CREATED_AT", columnDefinition = "TIMESTAMP", nullable = false)
  private Timestamp createdAt;

  private List<XSKDataStructureDependencyModel> dependencies = new ArrayList<>();

  private String schema;

  @Transient
  private transient String rawContent;

  @Transient
  private transient XSKDBContentType dbContentType;

  /**
   * Gets the location.
   *
   * @return the location
   */
  public String getLocation() {
    return location;
  }

  /**
   * Sets the location.
   *
   * @param location the new location
   */
  public void setLocation(String location) {
    this.location = location;
  }

  /**
   * Gets the name.
   *
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name.
   *
   * @param name the new name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets the type.
   *
   * @return the type
   */
  public String getType() {
    return type;
  }

  /**
   * Sets the type.
   *
   * @param type the new type
   */
  public void setType(String type) {
    this.type = type;
  }

  /**
   * Gets the hash.
   *
   * @return the hash
   */
  public String getHash() {
    return hash;
  }

  /**
   * Sets the hash.
   *
   * @param hash the new hash
   */
  public void setHash(String hash) {
    this.hash = hash;
  }

  /**
   * Gets the dependencies.
   *
   * @return the dependencies
   */
  public List<XSKDataStructureDependencyModel> getDependencies() {
    return dependencies;
  }

  /**
   * Gets the created by.
   *
   * @return the created by
   */
  public String getCreatedBy() {
    return createdBy;
  }

  /**
   * Sets the created by.
   *
   * @param createdBy the new created by
   */
  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  /**
   * Gets the created at.
   *
   * @return the created at
   */
  public Timestamp getCreatedAt() {
    if (createdAt == null) {
      return null;
    }
    return new Timestamp(createdAt.getTime());
  }

  /**
   * Sets the created at.
   *
   * @param createdAt the new created at
   */
  public void setCreatedAt(Timestamp createdAt) {
    if (createdAt == null) {
      this.createdAt = null;
      return;
    }
    this.createdAt = new Timestamp(createdAt.getTime());
  }

  /**
   * @return the schema
   */
  public String getSchema() {
    return schema;
  }

  /**
   * @param schema the schema to set
   */
  public void setSchema(String schema) {
    this.schema = schema;
  }

  /**
   * Get Hana2 syntax content
   *
   * @return the rawContent
   */
  public String getRawContent() {
    return rawContent;
  }

  /**
   * In case the model contains Hana2 version syntax, the rawContent should be set.
   *
   * @param rawContent the new HANA2 syntax content
   */
  public void setRawContent(String rawContent) {
    this.rawContent = rawContent;
  }

  public XSKDBContentType getDBContentType() {
    return dbContentType;
  }

  public void setDbContentType(XSKDBContentType dbContentType) {
    this.dbContentType = dbContentType;
  }

  /**
   * To json.
   *
   * @return the string
   */
  public String toJson() {
    return GsonHelper.GSON.toJson(this);
  }

  /*
   * (non-Javadoc)
   *
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return toJson();
  }

  /*
   * (non-Javadoc)
   *
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = (prime * result) + ((hash == null) ? 0 : hash.hashCode());
    result = (prime * result) + ((location == null) ? 0 : location.hashCode());
    result = (prime * result) + ((name == null) ? 0 : name.hashCode());
    result = (prime * result) + ((type == null) ? 0 : type.hashCode());
    return result;
  }

  /*
   * (non-Javadoc)
   *
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    XSKDataStructureModel other = (XSKDataStructureModel) obj;
    if (hash == null) {
      if (other.hash != null) {
        return false;
      }
    } else if (!hash.equals(other.hash)) {
      return false;
    }
    if (location == null) {
      if (other.location != null) {
        return false;
      }
    } else if (!location.equals(other.location)) {
      return false;
    }
    return true;
  }

}
