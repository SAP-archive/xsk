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


package com.sap.ndb.bimodelcalculation;

import com.sap.ndb.basemodelbase.Cardinality;
import com.sap.ndb.basemodelbase.JoinType;
import com.sap.ndb.basemodelbase.ReferentialDirection;
import com.sap.ndb.basemodelbase.TemporalJoinProperties;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * A view/node that combines exactly two inputs by a join The first input is treated as left table and the second	input is treated
 * as right table. In case of a text join the second node is the text table
 *
 *
 * <p>Java class for JoinView complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="JoinView"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.sap.com/ndb/BiModelCalculation.ecore}CalculationView"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="joinAttribute" type="{http://www.sap.com/ndb/BiModelCalculation.ecore}JoinAttribute" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="temporalJoinProperties" type="{http://www.sap.com/ndb/BaseModelBase.ecore}TemporalJoinProperties" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="joinType" use="required" type="{http://www.sap.com/ndb/BaseModelBase.ecore}JoinType" /&gt;
 *       &lt;attribute name="cardinality" type="{http://www.sap.com/ndb/BaseModelBase.ecore}Cardinality" /&gt;
 *       &lt;attribute name="referentialDirection" type="{http://www.sap.com/ndb/BaseModelBase.ecore}ReferentialDirection" /&gt;
 *       &lt;attribute name="languageColumn" type="{http://www.sap.com/ndb/BiModelCalculation.ecore}ViewAttributeName" /&gt;
 *       &lt;attribute name="dynamic" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *       &lt;attribute name="optimizeJoinColumns" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *       &lt;attribute name="multiJoinNode" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="centralTable" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="joinOrder" type="{http://www.sap.com/ndb/BiModelCalculation.ecore}JoinOrder" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "JoinView", propOrder = {
    "joinAttribute",
    "temporalJoinProperties"
})
public class JoinView
    extends CalculationView {

  protected List<JoinAttribute> joinAttribute;
  protected TemporalJoinProperties temporalJoinProperties;
  @XmlAttribute(name = "joinType", required = true)
  protected JoinType joinType;
  @XmlAttribute(name = "cardinality")
  protected Cardinality cardinality;
  @XmlAttribute(name = "referentialDirection")
  protected ReferentialDirection referentialDirection;
  @XmlAttribute(name = "languageColumn")
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  protected String languageColumn;
  @XmlAttribute(name = "dynamic")
  protected Boolean dynamic;
  @XmlAttribute(name = "optimizeJoinColumns")
  protected Boolean optimizeJoinColumns;
  @XmlAttribute(name = "multiJoinNode")
  protected String multiJoinNode;
  @XmlAttribute(name = "centralTable")
  protected String centralTable;
  @XmlAttribute(name = "joinOrder")
  protected JoinOrder joinOrder;

  /**
   * Gets the value of the joinAttribute property.
   *
   * <p>
   * This accessor method returns a reference to the live list,
   * not a snapshot. Therefore any modification you make to the
   * returned list will be present inside the JAXB object.
   * This is why there is not a <CODE>set</CODE> method for the joinAttribute property.
   *
   * <p>
   * For example, to add a new item, do as follows:
   * <pre>
   *    getJoinAttribute().add(newItem);
   * </pre>
   *
   *
   * <p>
   * Objects of the following type(s) are allowed in the list
   * {@link JoinAttribute }
   */
  public List<JoinAttribute> getJoinAttribute() {
    if (joinAttribute == null) {
      joinAttribute = new ArrayList<JoinAttribute>();
    }
    return this.joinAttribute;
  }

  /**
   * Gets the value of the temporalJoinProperties property.
   *
   * @return possible object is
   * {@link TemporalJoinProperties }
   */
  public TemporalJoinProperties getTemporalJoinProperties() {
    return temporalJoinProperties;
  }

  /**
   * Sets the value of the temporalJoinProperties property.
   *
   * @param value allowed object is
   *              {@link TemporalJoinProperties }
   */
  public void setTemporalJoinProperties(TemporalJoinProperties value) {
    this.temporalJoinProperties = value;
  }

  /**
   * Gets the value of the joinType property.
   *
   * @return possible object is
   * {@link JoinType }
   */
  public JoinType getJoinType() {
    return joinType;
  }

  /**
   * Sets the value of the joinType property.
   *
   * @param value allowed object is
   *              {@link JoinType }
   */
  public void setJoinType(JoinType value) {
    this.joinType = value;
  }

  /**
   * Gets the value of the cardinality property.
   *
   * @return possible object is
   * {@link Cardinality }
   */
  public Cardinality getCardinality() {
    return cardinality;
  }

  /**
   * Sets the value of the cardinality property.
   *
   * @param value allowed object is
   *              {@link Cardinality }
   */
  public void setCardinality(Cardinality value) {
    this.cardinality = value;
  }

  /**
   * Gets the value of the referentialDirection property.
   *
   * @return possible object is
   * {@link ReferentialDirection }
   */
  public ReferentialDirection getReferentialDirection() {
    return referentialDirection;
  }

  /**
   * Sets the value of the referentialDirection property.
   *
   * @param value allowed object is
   *              {@link ReferentialDirection }
   */
  public void setReferentialDirection(ReferentialDirection value) {
    this.referentialDirection = value;
  }

  /**
   * Gets the value of the languageColumn property.
   *
   * @return possible object is
   * {@link String }
   */
  public String getLanguageColumn() {
    return languageColumn;
  }

  /**
   * Sets the value of the languageColumn property.
   *
   * @param value allowed object is
   *              {@link String }
   */
  public void setLanguageColumn(String value) {
    this.languageColumn = value;
  }

  /**
   * Gets the value of the dynamic property.
   *
   * @return possible object is
   * {@link Boolean }
   */
  public Boolean isDynamic() {
    return dynamic;
  }

  /**
   * Sets the value of the dynamic property.
   *
   * @param value allowed object is
   *              {@link Boolean }
   */
  public void setDynamic(Boolean value) {
    this.dynamic = value;
  }

  /**
   * Gets the value of the optimizeJoinColumns property.
   *
   * @return possible object is
   * {@link Boolean }
   */
  public Boolean isOptimizeJoinColumns() {
    return optimizeJoinColumns;
  }

  /**
   * Sets the value of the optimizeJoinColumns property.
   *
   * @param value allowed object is
   *              {@link Boolean }
   */
  public void setOptimizeJoinColumns(Boolean value) {
    this.optimizeJoinColumns = value;
  }

  /**
   * Gets the value of the multiJoinNode property.
   *
   * @return possible object is
   * {@link String }
   */
  public String getMultiJoinNode() {
    return multiJoinNode;
  }

  /**
   * Sets the value of the multiJoinNode property.
   *
   * @param value allowed object is
   *              {@link String }
   */
  public void setMultiJoinNode(String value) {
    this.multiJoinNode = value;
  }

  /**
   * Gets the value of the centralTable property.
   *
   * @return possible object is
   * {@link String }
   */
  public String getCentralTable() {
    return centralTable;
  }

  /**
   * Sets the value of the centralTable property.
   *
   * @param value allowed object is
   *              {@link String }
   */
  public void setCentralTable(String value) {
    this.centralTable = value;
  }

  /**
   * Gets the value of the joinOrder property.
   *
   * @return possible object is
   * {@link JoinOrder }
   */
  public JoinOrder getJoinOrder() {
    return joinOrder;
  }

  /**
   * Sets the value of the joinOrder property.
   *
   * @param value allowed object is
   *              {@link JoinOrder }
   */
  public void setJoinOrder(JoinOrder value) {
    this.joinOrder = value;
  }

}
