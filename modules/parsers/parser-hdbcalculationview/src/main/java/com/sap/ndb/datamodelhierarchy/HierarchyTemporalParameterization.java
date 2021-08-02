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


package com.sap.ndb.datamodelhierarchy;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * Generates a time-dependent hierarchy on recursive parent-child source data whose edges are additionally
 * qualified by validity intervals.
 * Docs :	https://help.sap.com/viewer/4fe29514fd584807ac9f2a04f6754767/2.0.02/en-US/ffc24d42a29443b5aa1ad2a8feeb0e0e.html
 *
 *
 * <p>Java class for hierarchyTemporalParameterization complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="hierarchyTemporalParameterization"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="startWhere" type="{http://www.sap.com/ndb/DataModelType.ecore}ExpressionString" minOccurs="0"/&gt;
 *         &lt;choice&gt;
 *           &lt;element name="validFrom" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *           &lt;element name="validFromParameter" type="{http://www.sap.com/ndb/BaseModelBase.ecore}DbName"/&gt;
 *         &lt;/choice&gt;
 *         &lt;choice&gt;
 *           &lt;element name="validUntil" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *           &lt;element name="validUntilParameter" type="{http://www.sap.com/ndb/BaseModelBase.ecore}DbName"/&gt;
 *         &lt;/choice&gt;
 *         &lt;choice&gt;
 *           &lt;element name="depth" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *           &lt;element name="depthParameter" type="{http://www.sap.com/ndb/BaseModelBase.ecore}DbName"/&gt;
 *         &lt;/choice&gt;
 *         &lt;element name="cache" type="{http://www.sap.com/ndb/DataModelHierarchy.ecore}Cache"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "hierarchyTemporalParameterization", propOrder = {
    "startWhere",
    "validFrom",
    "validFromParameter",
    "validUntil",
    "validUntilParameter",
    "depth",
    "depthParameter",
    "cache"
})
public class HierarchyTemporalParameterization {

  protected String startWhere;
  @XmlSchemaType(name = "date")
  protected XMLGregorianCalendar validFrom;
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlSchemaType(name = "token")
  protected String validFromParameter;
  @XmlSchemaType(name = "date")
  protected XMLGregorianCalendar validUntil;
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlSchemaType(name = "token")
  protected String validUntilParameter;
  protected BigInteger depth;
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlSchemaType(name = "token")
  protected String depthParameter;
  @XmlElement(required = true)
  @XmlSchemaType(name = "NMTOKEN")
  protected Cache cache;

  /**
   * Gets the value of the startWhere property.
   *
   * @return possible object is
   * {@link String }
   */
  public String getStartWhere() {
    return startWhere;
  }

  /**
   * Sets the value of the startWhere property.
   *
   * @param value allowed object is
   *              {@link String }
   */
  public void setStartWhere(String value) {
    this.startWhere = value;
  }

  /**
   * Gets the value of the validFrom property.
   *
   * @return possible object is
   * {@link XMLGregorianCalendar }
   */
  public XMLGregorianCalendar getValidFrom() {
    return validFrom;
  }

  /**
   * Sets the value of the validFrom property.
   *
   * @param value allowed object is
   *              {@link XMLGregorianCalendar }
   */
  public void setValidFrom(XMLGregorianCalendar value) {
    this.validFrom = value;
  }

  /**
   * Gets the value of the validFromParameter property.
   *
   * @return possible object is
   * {@link String }
   */
  public String getValidFromParameter() {
    return validFromParameter;
  }

  /**
   * Sets the value of the validFromParameter property.
   *
   * @param value allowed object is
   *              {@link String }
   */
  public void setValidFromParameter(String value) {
    this.validFromParameter = value;
  }

  /**
   * Gets the value of the validUntil property.
   *
   * @return possible object is
   * {@link XMLGregorianCalendar }
   */
  public XMLGregorianCalendar getValidUntil() {
    return validUntil;
  }

  /**
   * Sets the value of the validUntil property.
   *
   * @param value allowed object is
   *              {@link XMLGregorianCalendar }
   */
  public void setValidUntil(XMLGregorianCalendar value) {
    this.validUntil = value;
  }

  /**
   * Gets the value of the validUntilParameter property.
   *
   * @return possible object is
   * {@link String }
   */
  public String getValidUntilParameter() {
    return validUntilParameter;
  }

  /**
   * Sets the value of the validUntilParameter property.
   *
   * @param value allowed object is
   *              {@link String }
   */
  public void setValidUntilParameter(String value) {
    this.validUntilParameter = value;
  }

  /**
   * Gets the value of the depth property.
   *
   * @return possible object is
   * {@link BigInteger }
   */
  public BigInteger getDepth() {
    return depth;
  }

  /**
   * Sets the value of the depth property.
   *
   * @param value allowed object is
   *              {@link BigInteger }
   */
  public void setDepth(BigInteger value) {
    this.depth = value;
  }

  /**
   * Gets the value of the depthParameter property.
   *
   * @return possible object is
   * {@link String }
   */
  public String getDepthParameter() {
    return depthParameter;
  }

  /**
   * Sets the value of the depthParameter property.
   *
   * @param value allowed object is
   *              {@link String }
   */
  public void setDepthParameter(String value) {
    this.depthParameter = value;
  }

  /**
   * Gets the value of the cache property.
   *
   * @return possible object is
   * {@link Cache }
   */
  public Cache getCache() {
    return cache;
  }

  /**
   * Sets the value of the cache property.
   *
   * @param value allowed object is
   *              {@link Cache }
   */
  public void setCache(Cache value) {
    this.cache = value;
  }

}
