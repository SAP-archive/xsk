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


package com.sap.ndb.datamodelgraph;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * Parameterization for the graph action GET_SHORTEST_PATHS_ONE_TO_ONE
 * This action returns the shortest path from the provided start vertex to the target vertex in the graph,
 * also known as single-target shortest path (SSSTSP).
 * The non-negative edge weights are read from the column provided in the edge table.
 * Output columns:
 * -ORDERING (fixed name for ordering the result)
 * -KEY (fixed name - values from the key column of the vertices table)
 * -source column of the edges table
 * -target column of the edges table)
 * -WEIGHT (fixed name corresponds to input weight column)
 *
 *
 * <p>Java class for GetShortestPathParameterization complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="GetShortestPathParameterization"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.sap.com/ndb/DataModelGraph.ecore}GetShortestPathsParameterization"&gt;
 *       &lt;sequence&gt;
 *         &lt;choice&gt;
 *           &lt;element name="targetVertex" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *           &lt;element name="targetVertexParameter" type="{http://www.sap.com/ndb/BaseModelBase.ecore}DbName"/&gt;
 *         &lt;/choice&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetShortestPathParameterization", propOrder = {
    "targetVertex",
    "targetVertexParameter"
})
public class GetShortestPathParameterization
    extends GetShortestPathsParameterization {

  protected String targetVertex;
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlSchemaType(name = "token")
  protected String targetVertexParameter;

  /**
   * Gets the value of the targetVertex property.
   *
   * @return possible object is
   * {@link String }
   */
  public String getTargetVertex() {
    return targetVertex;
  }

  /**
   * Sets the value of the targetVertex property.
   *
   * @param value allowed object is
   *              {@link String }
   */
  public void setTargetVertex(String value) {
    this.targetVertex = value;
  }

  /**
   * Gets the value of the targetVertexParameter property.
   *
   * @return possible object is
   * {@link String }
   */
  public String getTargetVertexParameter() {
    return targetVertexParameter;
  }

  /**
   * Sets the value of the targetVertexParameter property.
   *
   * @param value allowed object is
   *              {@link String }
   */
  public void setTargetVertexParameter(String value) {
    this.targetVertexParameter = value;
  }

}
