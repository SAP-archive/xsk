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


package com.sap.ndb.datamodeltype;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * Usually the AggregationBehavior determines how measures are aggregated in analytics.
 * But in some cases, for a special element of the entity (dimension of a cube) different aggregation behavior is needed.
 * Example: a measure Inventory can be summed up for the different plants and other dimensions but not for time –
 * according to time the last or average value might be relevant.
 * Exception aggregation is optional and used to define different (to the standard AggregationBehavior) aggregation behavior
 * for specified Elements. In general there might be multiple elements in which a measure has to be aggregated differently.
 * So a list of ExceptionAggregationSteps can be assigned. The (List) order defines the order, in which the different aggregations
 * have to be performed. ExceptionAggregationBehavior defines the aggregation behavior. This is:
 * - SUM (sum),
 * - MAX (maximum),
 * - MIN (minimum),
 * - COUNT (counter),
 * - VAR (variance),
 * - STD (standard deviation),
 * - AVG (average), etc. .
 * The elements which should be aggregated in one step have to be determined in the list of ExceptionAggregationReferenceElement.
 * These have to be part of the same Entity as the Measure Element.
 * Example:
 * In a Cube-Entity there is a measure which should show the number of customers above a certain sales-amount.
 * For this, at first the sales has to be aggregated (SUM) on customer level and then COUNT has to be performed.
 * This means if the sales for a customer is above the level, then the sales is replaced by 1 otherwise it is set to 0.
 * This number can be summed up again. This measure element has the AggregationBehavior SUM and the ExcpetionAggregationBehavior
 * COUNT with the ReferenceElement customer.
 * Remarks:
 * 1) The (logical) order in which the aggregation is performed is as follows: At first the standard aggregation is performed.
 * This intermediate result is still grouped by all Ele-ments in the list of ExceptionAggregationSteps.
 * Then the result is aggregated by the exception aggregation in the (List) order of the steps.
 * 2) The first remark holds even if the AggregationBehavior is FORMULA. This means that the calculation is done when the result
 * is still grouped by the exception aggrega-tion elements. After calculating the formula the result is aggregated according to
 * the list of ExceptionAggregationSteps. With this the aggregation level on which has to be calculated can be defined precisely.
 *
 *
 * <p>Java class for ExceptionAggregationStep complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ExceptionAggregationStep"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="referenceElement" type="{http://www.sap.com/ndb/RepositoryModelResource.ecore}Identifier" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="exceptionAggregationBehavior" type="{http://www.sap.com/ndb/DataModelType.ecore}ExceptionAggregationBehavior" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExceptionAggregationStep", propOrder = {
    "referenceElement"
})
public class ExceptionAggregationStep {

  @XmlElement(required = true)
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlSchemaType(name = "token")
  protected List<String> referenceElement;
  @XmlAttribute(name = "exceptionAggregationBehavior")
  protected ExceptionAggregationBehavior exceptionAggregationBehavior;

  /**
   * Gets the value of the referenceElement property.
   *
   * <p>
   * This accessor method returns a reference to the live list,
   * not a snapshot. Therefore any modification you make to the
   * returned list will be present inside the JAXB object.
   * This is why there is not a <CODE>set</CODE> method for the referenceElement property.
   *
   * <p>
   * For example, to add a new item, do as follows:
   * <pre>
   *    getReferenceElement().add(newItem);
   * </pre>
   *
   *
   * <p>
   * Objects of the following type(s) are allowed in the list
   * {@link String }
   */
  public List<String> getReferenceElement() {
    if (referenceElement == null) {
      referenceElement = new ArrayList<String>();
    }
    return this.referenceElement;
  }

  /**
   * Gets the value of the exceptionAggregationBehavior property.
   *
   * @return possible object is
   * {@link ExceptionAggregationBehavior }
   */
  public ExceptionAggregationBehavior getExceptionAggregationBehavior() {
    return exceptionAggregationBehavior;
  }

  /**
   * Sets the value of the exceptionAggregationBehavior property.
   *
   * @param value allowed object is
   *              {@link ExceptionAggregationBehavior }
   */
  public void setExceptionAggregationBehavior(ExceptionAggregationBehavior value) {
    this.exceptionAggregationBehavior = value;
  }

}