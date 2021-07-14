
function Constants() {

    var that = this;

     // UNUSED
    /**
     * An error that must be fixed as part of the migration effort. 
     * This included system issues that need to be resolved.
     */
    // this.ERROR_MUST_FIX = 1;

    /**
     * We don't understand this, it might be ok but it may well be
     * an issue that needs to be fixed.
     */
    // this.ERROR_PARSE_ERROR = 2;

    /**
      * We cannot handle this currently but will do so in the future
      */
    // this.ERROR_MIGRATION = 3;



    // UI5 files

    /**
     *
     */
    this.UI5_LIBRARY_PARAMETERS_JSON = 'library-parameters.json';

    /**
     *
     */
    this.UI5_LIBRARY_PRELOAD = 'library-preload.json';

    /**
     * All json files for UI5
     */
    this.UI5_JSON_FILES = [this.UI5_LIBRARY_PARAMETERS_JSON, this.UI5_LIBRARY_PRELOAD];

    /**
     *
     */
    this.UI5_LIBRARY_PROPERTIES = 'library.properties';

    this.UI5_PROPERTY_FILES = [this.UI5_LIBRARY_PROPERTIES];

    this.criticalObjectTypes = {
    	
    	
    	".calculationview":{container: "db"},
    	".hdbrole":{container: "db"},
    	".hdbsequence":{container: "db"},
    	".hdbdd":{container: "db"},
    	".xswidget":{container: "xsjs"},
    	".xsappsite":{container: "xsjs"},
    	".attributeview":{container: "db"},
    	".analyticview":{container: "db"},
    	".analyticprivilege":{container: "db"},
    	".xshttpdest":{container: "xsjs"},
    	".xssecurestore":{container: "xsjs"},
    	".hdbruldec":{container: "db"},    	
    	
    	
    	".xsjs":{container: "xsjs"},
    	".xsjslib":{container: "xsjs"},
    	".xsodata":{container: "xsjs"},
    	".xsjob":{container: "xsjs"},
    	".hdbtable":{container: "db"},
    	".hdbprodedure":{container: "db"},
    	".hdbtabletype":{container: "db"},
    	".csv":{container: "db"},
    	".hdbsqlview":{container: "db"},
    	".hdbscalarfunction":{container: "db"},
    	".hdbtablefunction":{container: "db"},
    	".hdbindex":{container: "db"},
    	".hdbfulltextindex":{container: "db"},
    	".hdbafllangprocedure":{container: "db"},
    	
    	".xscfgd":{container: "xsjs"},
    	".xscfgm":{container: "xsjs"},
    	".xsrtcfgd":{container: "xsjs"},
    	".hanawebidextn":{container: "xsjs"},
    };
}

module.exports = new Constants();
