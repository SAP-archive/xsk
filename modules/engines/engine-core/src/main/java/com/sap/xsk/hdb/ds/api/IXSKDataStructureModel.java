/*
 * Copyright (c) 2019-2021 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2019-2021 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.hdb.ds.api;

public interface IXSKDataStructureModel {

    /**
     * File extension for *.hdbtable files
     */
    public static final String FILE_EXTENSION_TABLE = ".hdbtable";
    /**
     * File extension for *.hdbview files
     */
    public static final String FILE_EXTENSION_VIEW = ".hdbview";
    /**
     * File extension for *.hdbdd files
     */
    public static final String FILE_EXTENSION_ENTITIES = ".hdbdd";
    /**
     * File extension for *.calculationview files
     */
    public static final String FILE_EXTENSION_CALCULATION_VIEW = ".calculationview";
    /**
     * File extension for *.hdbcalculationview files
     */
    public static final String FILE_EXTENSION_HDBCALCULATION_VIEW = ".hdbcalculationview";
    /**
     * File extension for *.hdbprocedure files
     */
    public static final String FILE_EXTENSION_HDBPROCEDURE = ".hdbprocedure";
    /**
     * File extension for *.hdbtablefunction files
     */
    public static final String FILE_EXTENSION_HDBTABLEFUNCTION = ".hdbtablefunction";
    /**
     * File extension for *.hdbschema files
     */
    public static final String FILE_EXTENSION_HDBSCHEMA = ".hdbschema";
    /**
     * File extension for *.hdi files
     */
    public static final String FILE_EXTENSION_HDI = ".hdi";

    /**
     * File extension for *.hdi files
     */
    public static final String FILE_EXTENSION_SYNONYM = ".hdbsynonym";

    /**
     * Type synonym
     */
    public static final String TYPE_HDB_SYNONYM = "HDBSYNONYM";
    
    /**
     * Type table
     */
    public static final String TYPE_HDB_TABLE = "HDBTABLE";
    /**
     * Type view
     */
    public static final String TYPE_HDB_VIEW = "HDBVIEW";
    /**
     * Type entities
     */
    public static final String TYPE_HDB_ENTITIES = "HDBENTITIES";
    /**
     * Type calculation view
     */
    public static final String TYPE_CALCVIEW = "CALCVIEW";
    /**
     * Type hdb calculation view
     */
    public static final String TYPE_HDB_CALCVIEW = "HDBCALCVIEW";
    /**
     * Type hdbprocedure
     */
    public static final String TYPE_HDB_PROCEDURE = "HDBPROC";
    /**
     * Type hdbdschema
     */
    public static final String TYPE_HDB_SCHEMA = "HDBSCHEMA";
    
    /**
     * Type hdbtablefunction
     */
    public static final String TYPE_HDB_TABLE_FUNCTION = "HDBTABLEFUNC";
    
    /**
     * Type hdi
     */
    public static final String TYPE_HDI = "HDI";

}
