/**
 * Copyright (c) 2010-2018 SAP
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
     * Type table
     */
    public static final String TYPE_TABLE = "TABLE";
    /**
     * Type view
     */
    public static final String TYPE_VIEW = "VIEW";
    /**
     * Type entities
     */
    public static final String TYPE_ENTITIES = "ENTITIES";
    /**
     * Type calculation view
     */
    public static final String TYPE_CALCVIEW = "CALCVIEW";
    /**
     * Type hdbprocedure
     */
    public static final String TYPE_HDB_PROCEDURE = "HDBPROC";
    /**
     * Type hdbdschema
     */
    public static final String TYPE_HDB_SCHEMA = "SCHEMA";
    
    /**
     * Type hdbtablefunction
     */
    public static final String TYPE_HDB_TABLE_FUNCTION = "HDBTABLEFUNC";

}
