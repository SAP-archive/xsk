// --------------------------------------------------------------------------
//
// Messages by category
//
// --------------------------------------------------------------------------

var mc = require('./message-categories');

function Messages() {}

Messages.prototype.INFRASTRUCTURE = {
    "1": {
        type: mc.ERROR,
        message: ["Unable to retrieve the original language and translation texts for DU {0}: {1}."],
        parameters: ["delivery unit name", "error message"],
        description:
  'The migration assistant could not retrieve translation texts for the delivery unit due to an error. Most likely, you do not have access to the following tables in schema `_SYS_REPO`:\n'
+ '\n'
+ '    ACTIVE_CONTENT_TEXT\n'
+ '    ACTIVE_CONTEXT_TEXT_CONTENT\n'
+ '    ACTIVE_OBJECT_TEXT\n'
+ '    ACTIVE_OBJECT_TEXT_CONTENT\n'
+ '\n'
+ 'You need to add `SELECT` object privilege to these tables for the operation to run successfully.'
    },
    "2": {
        type: mc.ERROR,
        message: ["Unable to retrieve DDL from schema {0}: {1}."],
        parameters: [ "schema name", "error message"],
        description: 'The download of the DDL object for a table, view or another database artifact failed. Make sure you have `SELECT` privilege for the application schema.'
    },
    "4": {
        type: mc.ERROR,
        message: ["Unable to retrieve the DDL for the table type {0}: {1}."],
        parameters: ["table type name", "error message"],
        description: 'The download of DDL object for a table type file failed. Make sure you have `SELECT` privilege for the application schema.'
    },
    "5": {
        type: mc.ERROR,
        message: ["Unable to retrieve the table types for the procedure {0}: {1}."],
        parameters: ["procedure name", "error message"],
        description: 
  'Make sure the migration user has `SELECT` access for the following tables:\n'
+ '\n'
+ '    SYS.TABLES\n'
+ '    SYS.OBJECT_DEPENDENCIES\n'
    },
    "6": {
        type: mc.ERROR,
        message: ["Unable to retrieve the table columns for the table {0}: {1}."],
        parameters: [ "table name", "error message"],
        description:
  'Make sure the migration user has `SELECT` access for the following table:\n'
+ '\n'
+ '    SYS.TABLES\n'
    },
    "10": {
        type: mc.ERROR,
        message: ["Error downloading the translation for the object: {0}."],
        parameters: ["error message"],
        description: "The translations for the named object could not be downloaded from the SAP HANA System. The reason ist most likely a permission problem. Check the error message and read the SAP HANA XS Advanced Migration Guide for further information."
    }
};

Messages.prototype.MIGRATION_PLUGIN = {
    "1": {
        type: mc.ERROR,
        message: ["The hdbrole file could not be parsed: {0}."],
        description: "The XS Classic hdbrole file could not be parsed. Report this issue to SAP and provide the file as an attachment."
    }
};

Messages.prototype.VIEW_MIGRATION = {
    "1": {
        type: mc.WARNING,
        message: ['This object is a script-based calculation view which needs to be migrated manually in the SAP HANA Studio.'],
        description: "Script based calculation views cannot be migrated automatically by using the XS Advanced Migration Assistant. For more information, see the SAP HANA Modeler Guide."
    },
    "2": {
        type: mc.WARNING,
        message: ['This object is an Application Function Modeler object which needs to be migrated manually in the SAP HANA Studio.'],
        parameters: ["name of object"],
        description: "Application Function Modeler objects (.aflpmml) hare deprecated and not supported in XS Advanced. For more information, see the SAP HANA Developer Guide for SAP HANA Studio."
    },
    // TODO remove (after adaption references)
    "3": {
        type: mc.ERROR,
        message: ['Object {0} is a Scripted Calculation view which is not supported by the migration assistant.'],
        description: 'Scripted calculation views must be migrated manually using the SAP HANA Studio. For more information, see the SAP HANA XS Advanced Migration Guide and the SAP HANA Modeler Guide.'
    },
    "4": {
        type: mc.ERROR,
        message: ['This object needs to be migrated manually in the SAP HANA Studio: {0}.'],
        description: "This object cannot be migrated automatically by the XS Advanced Migration Assistant. For more information, see the SAP HANA XS Advanced Migration Guide and the SAP HANA Modeler Guide."
    },
    "6": {
        type: mc.ERROR,
        message: ['The Object {0} is of type {1}. Objects of type {1} need to be migrated manually before running the migration assistant.'],
        parameters: ["object name", "object type"],
        description: "Attribute views, analytic views, analytic privileges, and scripted calculation views need to be migrated before running the automated migration assistant. The manual migration procedures are described in the migration guide and the SAP HANA Modeler Guide."
    },
    '7': {
        type: mc.ERROR,
        message: ['Decision tables are no longer supported.'],
        description: "The involved artifacts need to be migrated by the respective SAP HANA Studio plugin before running the migration assistant. For more information, see the SAP HANA XS Advanced Migration Guide."
    }
};

Messages.prototype.SECURITY = {
    "1": {
        type: mc.ERROR,
        message: ['The .xsaccess file defines an anonymous or default xssqlcc connection. The concept has changed in XS Advanced. Check the migration guide for further detail.'],
        description: 'The anonymous_connection keyword enables you to define the name of the .xssqlcc file that is used for SQL access if no user credentials are provided. If the default_connection is set in the .xsaccess file, the specified SQL connection configuration (for example, defined in sqlcc) is used for all SQL executions in this package. Neither mechanism is supported in XS Advanced. You need to adapt the code to ensure that the application performs the same way. For more information, see the SAP HANA Developer Guide.'
    },
    "2": {
        type: mc.WARNING,
        message: ['Features in xsaccess can only be partially migrated to new XS Advanced security concept (xsjs/xs-app.json). Check the migration guide for further detail.'],
        description: "For more information, see the Security Migration Guide"
    },
    // hasAppPrivilege, assertAppPrivilege
    "3": {
        type: mc.ERROR,
        message: ['You need to change the privilege name to the scope name in {0}.'],
        parameters: ["API"],
        description: "The scope names have been defined in the xs-security.json file which is located in the xsjs folder. Scope names are like the application privilege names, except that colons and double colons have been replaced by dots and the scope name has been prefixed by \"$XSAPPNAME\"."
    },
    // $db.getConnection, $.hdb.getConnection()
    "4": {
        type: mc.WARNING,
        message: ['The role access privileges need to be checked when using {0}. Check the migration guide for further detail.'],
        parameters: ["API"],
        description: "'You need to check if the current business user can access the resource (table, view, procedure) that is associated with this database connection. Unlike XS Classic, it is not possible to assign database access privileges to business users."
    },
    // hasSystemPrivilege, assertSystemPrivilege
    "5": {
        type: mc.ERROR,
        message: ['Found unsupported statement related to security topic: {0}.'],
        parameters: ["API"],
        description: "This API does not exist in XS Advanced. For more information, see the chapter on security migration."
    },
    // hdbrole
    "6": {
        type: mc.INFO,
        message: ["The Security Concept has changed; new and different files will be generated. Check the migration guide for further details."]
    },
    // .xsprivilege
    "7": {
        type: mc.INFO,
        message: ["The application privileges have been migrated to scopes in xs-security.json. Check the migration guide for further details."],
        description: "Scope names are like the application privilege names, except that colons and double colons have been replaced by dots and the scope name has been prefixed by \"$XSAPPNAME\"."
    },
    // .sqlcc file
    "8": {
        type: mc.INFO,
        message: ["The xssqlcc files are no longer supported by XS Advanced. Check the migration guide for further details."],
        description: "The concept behind xssqlcc is supported in XS Advanced; sqlcc connections can be configured via user defined services."
    },
    // unclear
    "9": {
        type: mc.INFO,
        message: ["You need to review the security concept of your application. Check the migration guide for further detail."],
        description: "'For more information, see the migration guide."
    },
    "10": {
        type: mc.ERROR,
        message: ["The table \"{0}\" has different semantics in XS Advanced."],
        parameters: ["table name"],
        description: "'This table refers to SAP HANA user and permission management which does not apply to the business user concept of XS Advanced. For information on user management, see the XS Advanced documentation."
    },
    "11": {
        type: mc.ERROR,
        message: ["\"ALTER USER\" or \"GRANT ALTER ON\" cannot be used in XS Advanced applications."],
        description: "User management cannot be performed from within XS Advanced applications. For information on user management, see the XS Advanced documentation."
    },
    "100": {
        type: mc.ERROR,
        message: ["The \"SESSION_USER\" has different semantics in XS Advanced."],
        description: "The SESSION_USER variable contains the name of the current user. In XS Advanced this is the technical user that is used for all users which can log on to the application. Use the $user variable to access the business user name. For more information, see the migration guide."
    },
    "12": {
        type: mc.ERROR,
        message: ["An analytical privilege was referred in a role that is not part of the migration. References to analytical privileges need to be schema-local."],
        description: ""
    },
    "13": {
        type: mc.WARNING,
        message: ["The rewrite rules are not migrated."],
        description: "The format of rewrite rules has changed. In addition, the new MTA concept separates XSJS and Web content. Therefore, you have to manually add the necessary rewrite rules to the xs-app.json file."
    },
    "14": {
        type: mc.WARNING,
        message: ["CORS is currently not supported by configuration."],
        description: "It is currently not possible to configure CORS centrally with XS Advanced. You have to manually set the required headers in each response involved."
    }
};

Messages.prototype.XSJS = {
    "1": {
        type: mc.ERROR,
        message: ['The JavaScript file could not be parsed: {0} The parser error is: {1}.'],
        parameters: ["error message"],
        description: "The reason for the parse error could be a syntax error in the xsjs or xsjslib JavaScript file or that the JavaScript parser does not support the latest JavaScript standard. Two issues are currently known:\n\n."
+ ' * an instanceof clause in a catch statement\n'
+ '    try {\n'
+ '    [...]\n'
+ '} catch (ex if ex instanceof utils.UserReportableException) {\n'
    + '[...]\n'
+ '}\n'
+ '* a yield statement\n'
    },
    // usage of $.repo API
    "2": {
        type: mc.ERROR,
        message: ['Found unsupported statement {0}.'],
        parameters: ["API method"],
        description: "Due to a conceptual change the $.repo API is not supported in XS Advanced. For more information, see the migration guide."
    },
    // $.config
    "3": {
        type: mc.ERROR,
        message: ['Found reference to unsupported feature {0}.'],
        parameters: ["$.config"],
        description: "The $.config API is not supported in XS Advanced. For more information, see the migration guide."
    },
    "4": {
        type: mc.WARNING,
        message: ['Found reference to unsupported JSVM grammar {0}. Check in detail.'],
        parameters: ['"let"'],
        description: "The let statement is only supported in XS Advanced when running on node.js 6 or higher."
    },
    "5": {
        type: mc.ERROR,
        message: ['Found reference to unsupported properties or functions in XS Advanced.'],
        description: "The function and properties are not supported in XS Advanced"
    },
    "6": {
        type: mc.WARNING,
        message: ['XSJS does not support instanceof to built-in types'],
        description: "XSJS does not support instanceof to built-in types. For more information, see the migration guide."
    },
    "8": {
        type: mc.ERROR,
        message: ["The library sap.hana.xs.libs.dbutils no longer exists."],
        description: "The library sap.hana.xs.libs.dbutils no longer exists. For more information, see the migration guide."
    },
    "9": {
        type: mc.WARNING,
        message: ["Database connection found."],
        description: "Check SQL statements and make sure the schemas of removed objects' names are correct"
    },
    // todo: need to be described in migration guide
    "10": {
        type: mc.ERROR,
        container: 'xsjs',
        message: ['The schema {0} was used in xsjs or xsjslib file.'],
        parameters: ["schema name"],
        description: "The schema prefix is not supported in XS Advanced. You need to remove the schema prefix from the code and add a synonym if you are accessing an object in another schema."
    },
    "11": {
        type: mc.INFO,
        container: 'xsjs',
        message: ['The application schema {0} was used in xsjs or xsjslib file and has been removed.'],
        parameters: ["schema name"],
        description: "Direct schema references are not supported in XS Advanced. The schema reference has been removed."
    },
    "12": {
        type: mc.INFO,
        container: 'xsjs',
        message: ['The _SYS_BIC schema has been removed from the reference {0}.'],
        parameters: ["reference"],
        description: "The object is now available locally in the container. The reference to schema _SYS_BIC has been removed."
    },
    "13": {
        type: mc.INFO,
        container: 'xsjs',
        message: ['The schema has been removed from the reference {0}.'],
        parameters: ["reference"],
        description: "The object is accessible either locally in the container or by using a configured synonym."
    },
    "14": {
        type: mc.INFO,
        container: 'xsjs',
        message: ['The schema has been removed from the reference {0}.'],
        parameters: ["reference"],
        description: "The referenced object cannot be covered by a synonym and needs to be refactored."
    },
    "15" : {
        type: mc.WARNING,
        container: 'xsjs',
        message: ['A loadProcedure statement with Identifier as parameter has been detected in {0}.'],
        description: "A loadProcedure statement has been used. The parameters are Identifiers; therefore, a synonym handling could not be performed. The statement most likely contains a schema reference and needs to be checked manually."
    },
    "16" : {
        type: mc.WARNING,
        container : 'xsjs',
        message: ['The $.hdb.getConnection options have been removed'],
        description: "The connection options have been removed due to the different behavior in XS Advanced. For more information, see the migration guide."
    }
    
};

Messages.prototype.UNSUPPORTED_FEATURE = {
    "1": {
        type: mc.ERROR,
        message: ["The object type {0} is not supported in XS Advanced. A manual migration is required."],
        parameters: ["object type"],
        description: "For more information, see the mgration guide."
    },
    "2": {
        type: mc.ERROR,
        message: ["The unsupported table {0} is used by the application."],
        parameters: ["table name"],
        description: "This table contains SQL connections from xssqlcc files in the SAP HANA repository. This feature is not supported in XS Advanced and not required as the database connections are based on technical instead of business users."
    },
    "3": {
        type: mc.ERROR,
        message: ["The migration of object type {0} is currently not supported."],
        parameters: ["object type"],
        description: "For more information on alternative solutions, see the section on supported object types in the migration guide."
    },
    "4": {
        type: mc.ERROR,
        message: ["The object type {0} is not supported in XS Advanced."],
        parameters: ["object type"],
        description: "For more information on alternative solutions, see the section on supported object types in the migration guide."
    },
    "5": {
        type: mc.ERROR,
        message: ["The semantics of table _SYS_XS.SQL_CONNECTION has changed in XS Advanced. Please, adjust your code."]
    },
    "6": {
        type: mc.ERROR,
        message: ["The object type {0} is not supported. The feature is implemented in a different concept."],
        parameters: ["object type"],
        description: "For more information on alternative solutions, see the section on supported object types in the migration guide."
    }
};

Messages.prototype.HDI = {
    "1": {
        type: mc.WARNING,
        message: ["The ALTER TABLE for CONCAT_ATTRIBUTE has been removed."],
        description:
   'Concat attributes are no longer required and will be removed. This is an example concat alter statement:\n.'
+ '\n'
+ '    ALTER TABLE "SAP_BI_LAUNCHPAD"."sap.bi.launchpad.db.tables::BI_ITEM_SHARE_ACCESS" \n'
+ '             WITH PARAMETERS (\'CONCAT_ATTRIBUTE\'=(\'$uc_ITEM_SHARED_PRINCIPAL$\',\n'
+ '                              \'ITEM_ID\',\'SHARED_WITH_PRINCIPAL_ID\'))\n'
    },
    "2": {
        type: mc.ERROR,
        message: ['The .hdbtable file contains more than one DDL statement which will cause the deployment to fail.'],
        description: 'A .hdbtable file may only contain a single DDL statement. For example, if table triggers are used, the migration assistant is not able to migrate the table object correctly and migration has to be done manually.'
    },
    "3": {
        type: mc.WARNING,
        message: ["Comments on table columns are not supported."],
        description: "The hdbtable object has been migrated, but the comment has been removed."
    },
    "4": {
        type: mc.ERROR,
        message: ["Cannot determine schema name from file."],
        description: "The migration of the named object could not be completed since the schema name could not be read from the file. Report this issue to SAP and provide the file as an attachment."
    },
    "5": {
        type: mc.ERROR,
        message: ["Unable to parse the .hdbti file: {1}."],
        parameters: ["parse error"],
        description: "As part of the migration process, the hdbti file will be migrated to a JSON based .hdbtabledata file. This error message means that the original file could not be parsed. Report this issue to SAP and provide the file as an attachment."
    },
    // placeholder for CDS migration error messages
    "6": {
        type: mc.ERROR,
        message: ["Error migrating a CDS object."],
        description: "A CDS object could not be migrated (from .hdbdd to .hdbcds). Report this issue to SAP and provide the file as an attachment."
    },
    "7": {
        type: mc.ERROR,
        message: ["The columns could not be retrieved for hdbti file: {0}."],
        parameters: ["error message"],
        description: "While the XS Classic .hdbti file did not require table columns to be specified, the XS Advanced .hdbtabledate file does. There was an error retrieving the column names from the system and the error message should provide additional information about the cause of the error."
    },
    "8": {
        type: mc.WARNING,
        message: ["The behavior of distinction of empty and null values has changed."],
        description: "The default behavior of HDI equals the hdbti setting distinguishEmptyFromNull=true. The processed hdbti file did not specify this setting or set it to false. This could now lead to different behavior of the generated hdbtabledata file if your CSV files contain two subsequent delimEnclosing characters (as specified in your .hdbti or double quotes by default). HDI will now interpret this as an empty string value instead of null. If this is OK for you, you can ignore this warning. If not, please check your CSV files for such cases and remove the delimiters from values that should be interpreted as null."
    },
    "9": {
        type: mc.ERROR,
        message: ["The table or procedure {0} is used by the application, which is not supported in XS Advanced."],
        parameters: ["table or procedure name"],
        description: "This table contains SAP HANA Repository contents that are no longer relevant for XS Advanced applications. Refactor the application to implement the desired functionality."
    },   
    "10": {
        type: mc.ERROR,
        message: ["SYS_REPOSITORY_REST is not supported in XS Advanced."]
    },
    "11": {
        type: mc.WARNING,
        message: ["Use the ALTER TABLE with care in XS Advanced / HDI."],
        description: "The table may get dropped first and then recreated in a certain situation and data may get lost."
    },
    // TODO no idea what we should suggest here
    "12": {
        type: mc.WARNING,
        message: ["The ALTER SYSTEM/USER requires additional privileges."],
        description: "The ALTER SYSTEM/USER requires additional privileges for technical user. Note that DB user is not business user anymore in XS Advanced."
    },
    "13": {
        type: mc.WARNING,
        message: ["Make sure ALTER permission is granted to the technical user."],
        description: "Make sure ALTER permission is granted to the technical user. For alternatives, see the migration guide and the XS Advanced documentation."
    },
    "14": {
        type: mc.ERROR,
        message: ["The migration of the \".hdbdd\" file failed: {0}."],
        parameters: ["error message"]
    },
    "15": {
        type: mc.WARNING,
        message: ["Please check the migration warnings for the \".hdbdd\" file: {0}."]
    },
    "16": {
        type: mc.INFO,
        message: ["The currency conversion configuration has been added to ce_conversion call."]
    },
    "17": {
        type: mc.ERROR,
        message: ["The synonym for currency conversion tables could not be found. Please check call of ce_conversion manually."]
    },
    "18": {
        type: mc.WARNING,
        message: ["The Calculation Engine Plan Operator {0} has been found."],
        description: "SAP recommends that you use SQL rather than the Calculation Engine Plan Operators with SQLScript. For more information, see the SAP HANA SQLScript Documentation."
    },
    "19": {
        type: mc.ERROR,
        message: ["A reference to a Scripted Calcview {0} has been found."],
        description: "A reference to the procedure part of a scripted calcview has been found. This needs to be refactored manually."
    },
    // Flowgraph messages
    "20": {
        type: mc.WARNING,
        message: ['The flowgraph {0} uses virtual table(s) {1} for which hdbsynonyms are created. The hdbvirtualtable generation is not supported yet.'],
        parameters: ["name of flowgraph", "names of the virtual tables"],
        description: "For more information about the transform names, see the flowgraphSummary file."
    },
    "21": {
        type: mc.WARNING,
        message: ['The flowgraph {0} is marked for real-time. The user needs to grant necessary privileges on remote source to the HDI user.'],
        parameters: ["name of flowgraph"],
        description: "For more information, see the SAP HANA EIM Admin Guide."
	},
    "22": {
        type: mc.WARNING,
        message: ['The flowgraph {0} uses lookup transform. Please check the lookup conditions and replace the table names with synonym names.'],
        parameters: ["name of flowgraph"],
        description: "For more information about the transform names, see the flowgraphSummary file."
	},
    "23": {
        type: mc.WARNING,
        message: ['The flowgraph {0} uses TableComparison transform. Please check the filter condition and replace the table names with synonym names.'],
        parameters: ["name of flowgraph"],
        description: "For more information about the transform names, see the flowgraphSummary file."
	},
    "24": {
        type: mc.ERROR,
        message: ["Reference to non-coverable {0} object found."],
        description: "The referred object cannot be covered by a synonym. This is most likely because it is deprecated in XS Advanced or has no longer the same meaning."
    },
    "25": {
        type: mc.WARNING,
        message: ["Procedures of type Rlang require special privileges."],
        description: "Using this procedure requires the privilege 'CREATE R SCRIPT'. This needs to be granted to the _SYS_DI_OO_DEFAULTS role. For more information, see the migration guide."
    }
}
    
;

Messages.prototype.TODO = {
    "1": {
        type: mc.ERROR,
        message: ['The type of the JSON file could not be determined. Copy the file manually either to the xsjs or web container folder.'],
        description: 'The migration assistant tries to read the JSON file and determine its usage and type, especially whether the contents of the file are UI relevant or are required on the server side.'
    },
    "2": {
        type: mc.ERROR,
        message: ['The type of the XML file {0} could not be determined. Please copy the file manually either to the xsjs or web container folder.'],
        description: 'The migration assistant tries to read the XML file and determine its usage and type, especially whether the contents of the file are UI relevant or are required on the server side.'
    },
    "3": {
        type: mc.ERROR,
        message: ['The .theming file does not appear to be a SAP UI5 file. Copy the file manually to the correct container.'],
        description: "The migration assistant expects .theming files to be related to the SAP UI5 theming feature. This is not the case for this object."
    },
    "4": {
        type: mc.WARNING,
        message: ['Unknown file type. Migrate the file manually if necessary.'],
        description: "The migration assistant does not know the object type of this object. If this file is related to your application and still needed, copy it to the appropriate container (web or xsjs)."
    }
};

Messages.prototype.TRANSLATION = {
    "1": {
        type: mc.INFO,
        message: ["The \".hdbtextbundle\" files have been replaced in XS Advanced with \".properties\" files . The reference to \".hdbtextbundle\" has been replaced with \".properties\"."],
        description: "All \".hdbtextbundle\" files as well as their translations have been migrated to \".properties\" files. The semantics of loading text files in the browser has not changed."
    },
    "4": {
        type: mc.INFO,
        message: ["Copy the text property file(s) {0} (including translations) into the web or xsjs folder."],
        parameters: ["property file name"],
        description:  "The migration assistant cannot determine whether this is a text or translation file which needs to be put into the web, xsjs, or db container directory. Copy the file(s) into the correct container and use the same sub-directory as in the original application."
    },
    "5": {
        type: mc.WARNING,
        message: ["The .properties file {0}, found in the original sources, is most likely a translation file. Check the file and copy it to the appropriate container."],
        parameters: ["property file name"],
        description: "This .properties file was found in the original XS Classic sources. Although .properties files were not officially supported as text files in XS Classic (the official way was to use .hdbtextbundle files), there are several applications which use this mechanism. Review the file, copy it into the appropriate container (web, xsjs, db) and preserve the folder structure."
    },
    "6": {
        type: mc.ERROR,
        message: ["Reference to {0} found in {1}. Refered object is not part of the migration context."],
        description: "The migration assistant tries to automatically replace references to deprecated hdbtextbundle files to their equivalent properties files. This failed because the referenced object is not part of the migration assistant. You have to either include the object or adjust the reference manually."
    },
    "7": {
        type: mc.INFO,
        message: ["The text property file(s) {0} (including translations) has/have been referenced in {1} and thus copied to the Web Module."],
        description:  "A reference to this hdbtextbundle file was found in a javascript file. Therefore, the migration assistant decided to copy it to the Web Module folder."
    }
};

Messages.prototype.DELETE = {
    "1": {
        type: mc.INFO,
        message: ["The {0} file is no longer needed in XS Advanced projects."],
        parameters: ["object name"],
        description: "The object type has not been migrated and is no longer relevant for XS Advanced projects. There is no loss of functionality."
    }
};

Messages.prototype.JS = {
   "1": {
        type: mc.INFO,
        message: ["The AJAX request URL may have changed."],
        description: "Since the new MTA-concept separates backend and frontend to different services, the URL of AJAX calls may have to be adjusted to the new locations."
   },
    "2": {
        type: mc.INFO,
        message : ["XSOData only supports the json format."],
        description: "XSOData only supports the json format. The call has been updated to 'new sap.ui.model.odata.ODataModel(url, true)'."
    },
}    


function cloneObject(obj) {

    if (null == obj || typeof obj != "object") {
        return obj;
    }
    if (obj instanceof Array) {
        var copy = [];
        for (var i = 0, len = obj.length; i < len; i++) {
            copy[i] = cloneObject(obj[i]);
        }
        return copy;
    }
    if (obj instanceof Object) {
        var copy = {};
        for (var attr in obj) {
            if (obj.hasOwnProperty(attr)) {
                copy[attr] = cloneObject(obj[attr]);
            }
        }
        return copy;
    }
    throw new Error("Unable to copy the object. Its type is not supported.");
}

Messages.prototype.getMessage = function(cat, id) {
    if (id === undefined) {
        // get were invoked with something like
        // cat = XSJS_3
        var v = cat.split("_");
        var cat = v[0];
        var ids = v[1];
        id = parseInt(ids);
    } else {
        var ids = id.toString();
    }
    var c = cloneObject(this[cat][ids]);
    c.category = cat;
    c.id = mc.getId(cat, id)
    // those are only for documentation purposes
    delete c.parameters;
    return c;
}

module.exports = new Messages();
