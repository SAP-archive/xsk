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
package com.sap.xsk.parser.xsodata.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class XSKHDBXSODATAEntity {

  private XSKHDBXSODATARepositoryObject repositoryObject;
  private String alias;

  private List<String> withPropertyProjections = new ArrayList<>();
  private List<String> withoutPropertyProjections = new ArrayList<>();

  private List<String> keyList = new ArrayList<>();
  private String keyGenerated;

  private List<XSKHDBXSODATANavigation> navigates = new ArrayList<>();

  private List<XSKHDBXSODATAAggregation> aggregations = new ArrayList<>();
  private XSKHDBXSODATAAggregationType aggregationType;

  private XSKHDBXSODATAParameterType parameterType;
  private XSKHDBXSODATAParameter parameterEntitySet;

  private List<XSKHDBXSODATAModification> modifications = new ArrayList<>();

  private boolean concurrencyToken;
  private List<String> eTags = new ArrayList<>();

  public XSKHDBXSODATARepositoryObject getRepositoryObject() {
    return repositoryObject;
  }

  public void setRepositoryObject(XSKHDBXSODATARepositoryObject repositoryObject) {
    this.repositoryObject = repositoryObject;
  }

  public String getAlias() {
    return alias;
  }

  public void setAlias(String alias) {
    this.alias = alias;
  }

  public List<String> getWithPropertyProjections() {
    return withPropertyProjections;
  }

  public void setWithPropertyProjections(List<String> withPropertyProjections) {
    this.withPropertyProjections = withPropertyProjections;
  }

  public List<String> getWithoutPropertyProjections() {
    return withoutPropertyProjections;
  }

  public void setWithoutPropertyProjections(List<String> withoutPropertyProjections) {
    this.withoutPropertyProjections = withoutPropertyProjections;
  }

  public List<String> getKeyList() {
    return keyList;
  }

  public void setKeyList(List<String> keyList) {
    this.keyList = keyList;
  }

  public String getKeyGenerated() {
    return keyGenerated;
  }

  public void setKeyGenerated(String keyGenerated) {
    this.keyGenerated = keyGenerated;
  }

  public List<XSKHDBXSODATANavigation> getNavigates() {
    return navigates;
  }

  public void setNavigates(List<XSKHDBXSODATANavigation> navigates) {
    this.navigates = navigates;
  }

  public List<XSKHDBXSODATAAggregation> getAggregations() {
    return aggregations;
  }

  public void setAggregations(List<XSKHDBXSODATAAggregation> aggregations) {
    this.aggregations = aggregations;
  }

  public XSKHDBXSODATAAggregationType getAggregationType() {
    return aggregationType;
  }

  public void setAggregationType(XSKHDBXSODATAAggregationType aggregationType) {
    this.aggregationType = aggregationType;
  }

  public XSKHDBXSODATAParameterType getParameterType() {
    return parameterType;
  }

  public void setParameterType(XSKHDBXSODATAParameterType parameterType) {
    this.parameterType = parameterType;
  }

  public XSKHDBXSODATAParameter getParameterEntitySet() {
    return parameterEntitySet;
  }

  public void setParameterEntitySet(XSKHDBXSODATAParameter parameterEntitySet) {
    this.parameterEntitySet = parameterEntitySet;
  }

  public List<XSKHDBXSODATAModification> getModifications() {
    return modifications;
  }

  public void setModifications(List<XSKHDBXSODATAModification> modifications) {
    this.modifications = modifications;
  }

  public boolean isConcurrencyToken() {
    return concurrencyToken;
  }

  public void setConcurrencyToken(boolean concurrencyToken) {
    this.concurrencyToken = concurrencyToken;
  }

  public List<String> getETags() {
    return eTags;
  }

  public void setETags(List<String> eTags) {
    this.eTags = eTags;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    XSKHDBXSODATAEntity that = (XSKHDBXSODATAEntity) o;
    return concurrencyToken == that.concurrencyToken && Objects.equals(repositoryObject, that.repositoryObject) && Objects.equals(alias,
        that.alias) && Objects.equals(withPropertyProjections, that.withPropertyProjections) && Objects.equals(withoutPropertyProjections,
        that.withoutPropertyProjections) && Objects.equals(keyList, that.keyList) && Objects.equals(keyGenerated, that.keyGenerated)
        && Objects.equals(navigates, that.navigates) && Objects.equals(aggregations, that.aggregations)
        && aggregationType == that.aggregationType && parameterType == that.parameterType && Objects.equals(parameterEntitySet,
        that.parameterEntitySet) && Objects.equals(modifications, that.modifications) && Objects.equals(eTags, that.eTags);
  }

  @Override
  public int hashCode() {
    return Objects.hash(repositoryObject, alias, withPropertyProjections, withoutPropertyProjections, keyList, keyGenerated, navigates,
        aggregations, aggregationType, parameterType, parameterEntitySet, modifications, concurrencyToken, eTags);
  }
}
