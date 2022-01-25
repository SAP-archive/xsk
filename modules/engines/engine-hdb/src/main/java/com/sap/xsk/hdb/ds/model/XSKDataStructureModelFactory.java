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

import java.util.HashMap;
import java.util.Map;

//import com.sap.xsk.hdb.ds.model.hdbdd.XSKDataStructureEntitiesModel;
import com.sap.xsk.hdb.ds.model.hdbschema.XSKDataStructureHDBSchemaModel;
import com.sap.xsk.hdb.ds.model.hdbsynonym.XSKDataStructureHDBSynonymModel;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableModel;
import com.sap.xsk.hdb.ds.model.hdbtabletype.XSKDataStructureHDBTableTypeModel;
import com.sap.xsk.hdb.ds.model.hdbview.XSKDataStructureHDBViewModel;
import com.sap.xsk.hdb.ds.parser.hdbdd.XSKHdbddParser;
import com.sap.xsk.hdb.ds.parser.hdbschema.XSKSchemaParser;
import com.sap.xsk.hdb.ds.parser.hdbsynonym.XSKSynonymParser;
import com.sap.xsk.hdb.ds.parser.hdbtable.XSKTableParser;
import com.sap.xsk.hdb.ds.parser.hdbtabletype.XSKTableTypeParser;
import com.sap.xsk.hdb.ds.parser.hdbview.XSKViewParser;

//import com.sap.xsk.models.hdbdd.HdbDDStandaloneSetup;

/**
 * The factory for creation of the data structure models from source content.
 */
public class XSKDataStructureModelFactory {

  static Map<String, String> TYPES_MAP = new HashMap<String, String>();

  static {
    TYPES_MAP.put("String", "VARCHAR");
    TYPES_MAP.put("UTCTimestamp", "TIMESTAMP");
  }

  public XSKDataStructureModelFactory() {
    setupParser();
  }

  /**
   * Creates a table model from the raw content.
   *
   * @param content the table definition
   * @return the table model instance
   */
  public static XSKDataStructureHDBTableModel parseTable(String location, String content) throws Exception {
    XSKTableParser parser = new XSKTableParser();
    XSKDataStructureHDBTableModel result = parser.parse(location, content);
    return result;
  }

  /**
   * Creates a table model from the raw content.
   *
   * @param bytes the table definition
   * @return the table model instance
   */
  public static XSKDataStructureHDBTableModel parseTable(String location, byte[] bytes) throws Exception {
    return parseTable(location, new String(bytes));
  }

  /**
   * Creates a view model from the raw content.
   *
   * @param content the view definition
   * @return the view model instance
   */
  public static XSKDataStructureHDBViewModel parseView(String location, String content) throws Exception {
    XSKViewParser parser = new XSKViewParser();
    XSKDataStructureHDBViewModel result = parser.parse(location, content);
    return result;
  }

  public static XSKDataStructureModel parseHdbdd(String location, String content) throws Exception {
    XSKHdbddParser parser = new XSKHdbddParser();
    XSKDataStructureModel result = parser.parse(location, content);
    return result;
  }

  /**
   * Creates a view model from the raw content.
   *
   * @param bytes the view definition
   * @return the view model instance
   */
  public static XSKDataStructureHDBViewModel parseView(String location, byte[] bytes) throws Exception {
    return parseView(location, new String(bytes));
  }

  /**
   * Creates a synonym model from the raw content.
   *
   * @param content the synonym definition
   * @return the synonym model instance
   */
  public static XSKDataStructureHDBSynonymModel parseSynonym(String location, String content) throws Exception {
    XSKSynonymParser parser = new XSKSynonymParser();
    XSKDataStructureHDBSynonymModel result = parser.parse(location, content);
    return result;
  }

  private void setupParser() {
  }

//  /**
//   * Creates a entities model from the raw content.
//   *
//   * @param content the entities definition
//   * @return the entities model instance
//   * @throws Exception
//   */
//  public XSKDataStructureEntitiesModel parseEntities(String location, String content) throws Exception {
//    XSKEntitiesParser parser = new XSKEntitiesParser();
//    XSKDataStructureEntitiesModel result = parser.parse(location, content);
//    return result;
//  }

//  /**
//   * Creates a entities model from the raw content.
//   *
//   * @param bytes the entities definition
//   * @return the entities model instance
//   * @throws Exception
//   */
//  public XSKDataStructureEntitiesModel parseEntities(String location, byte[] bytes) throws Exception {
//    return parseEntities(location, new String(bytes));
//  }

  /**
   * Creates a schema model from the raw content.
   *
   * @param content the schema definition
   * @return the schema model instance
   */
  public static XSKDataStructureHDBSchemaModel parseSchema(String location, String content) throws Exception {
    XSKSchemaParser parser = new XSKSchemaParser();
    XSKDataStructureHDBSchemaModel result = parser.parse(location, content);
    return result;
  }

  /**
   * Creates a schema model from the raw content.
   *
   * @param bytes the schema definition
   * @return the schema model instance
   */
  public static XSKDataStructureHDBSchemaModel parseSchema(String location, byte[] bytes) throws Exception {
    return parseSchema(location, new String(bytes));
  }

//	/**
//	 * Creates a calculation view model from the raw content.
//	 *
//	 * @param xml
//	 *            the XML definition
//	 * @return the calculation view model instance
//	 * @throws Exception
//	 */
//	public static XSKDataStructureCalculationViewModel parseCalcView(String location, String xml) {
//		XSKDataStructureCalculationViewModel calcviewModel = new XSKDataStructureCalculationViewModel();
//		calcviewModel.setName(new File(location).getName());
//		calcviewModel.setLocation(location);
//		calcviewModel.setType(IXSKDataStructureModel.TYPE_CALCVIEW);
//		calcviewModel.setHash(DigestUtils.md5Hex(xml));
//		calcviewModel.setCreatedBy(UserFacade.getName());
//		calcviewModel.setCreatedAt(new Timestamp(new java.util.Date().getTime()));
//		calcviewModel.setXml(xml);
//		return calcviewModel;
//	}
//
//	/**
//	 * Creates a calculation view model from the raw content.
//	 *
//	 * @param xml
//	 *            the XML definition
//	 * @return the calculation view model instance
//	 * @throws Exception
//	 */
//	public static XSKDataStructureCalculationViewModel parseCalcView(String location, byte[] xml) {
//		return parseCalcView(location, new String(xml));
//	}

  /**
   * Creates a table type model from the raw content.
   *
   * @param content the table type definition
   * @return the table type model instance
   */
  public static XSKDataStructureHDBTableTypeModel parseTableType(String location, String content) throws Exception {
    XSKTableTypeParser parser = new XSKTableTypeParser();
    XSKDataStructureHDBTableTypeModel result = parser.parse(location, content);
    return result;
  }

  /**
   * Creates a table type model from the raw content.
   *
   * @param bytes the table type definition
   * @return the table type model instance
   */
  public static XSKDataStructureHDBTableTypeModel parseTableType(String location, byte[] bytes) throws Exception {
    return parseTableType(location, new String(bytes));
  }
}