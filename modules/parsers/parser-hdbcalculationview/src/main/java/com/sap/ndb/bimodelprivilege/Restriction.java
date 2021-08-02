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
//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.11.26 at 10:54:28 AM EET 
//


package com.sap.ndb.bimodelprivilege;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * A restriction either applies to a dimension or to an attribute. In case several attributes of a dimension
 * are filtered they must be put into the same Restriction.
 *
 *
 * <p>Java class for Restriction complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="Restriction"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.sap.com/ndb/BiModelPrivilege.ecore}RestrictionNode"&gt;
 *       &lt;choice&gt;
 *         &lt;element name="dimensionUri" type="{http://www.sap.com/ndb/RepositoryModelResource.ecore}RepositoryUri" minOccurs="0"/&gt;
 *         &lt;sequence&gt;
 *           &lt;element name="attributeName" type="{http://www.sap.com/ndb/BiModelDataFoundation.ecore}BIName" minOccurs="0"/&gt;
 *           &lt;element name="originInformationModelUri" type="{http://www.sap.com/ndb/RepositoryModelResource.ecore}RepositoryUri" minOccurs="0"/&gt;
 *         &lt;/sequence&gt;
 *       &lt;/choice&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Restriction", propOrder = {
    "dimensionUri",
    "attributeName",
    "originInformationModelUri"
})
public class Restriction
    extends RestrictionNode {

  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlSchemaType(name = "token")
  protected String dimensionUri;
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlSchemaType(name = "token")
  protected String attributeName;
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlSchemaType(name = "token")
  protected String originInformationModelUri;

  /**
   * Gets the value of the dimensionUri property.
   *
   * @return possible object is
   * {@link String }
   */
  public String getDimensionUri() {
    return dimensionUri;
  }

  /**
   * Sets the value of the dimensionUri property.
   *
   * @param value allowed object is
   *              {@link String }
   */
  public void setDimensionUri(String value) {
    this.dimensionUri = value;
  }

  /**
   * Gets the value of the attributeName property.
   *
   * @return possible object is
   * {@link String }
   */
  public String getAttributeName() {
    return attributeName;
  }

  /**
   * Sets the value of the attributeName property.
   *
   * @param value allowed object is
   *              {@link String }
   */
  public void setAttributeName(String value) {
    this.attributeName = value;
  }

  /**
   * Gets the value of the originInformationModelUri property.
   *
   * @return possible object is
   * {@link String }
   */
  public String getOriginInformationModelUri() {
    return originInformationModelUri;
  }

  /**
   * Sets the value of the originInformationModelUri property.
   *
   * @param value allowed object is
   *              {@link String }
   */
  public void setOriginInformationModelUri(String value) {
    this.originInformationModelUri = value;
  }

}
