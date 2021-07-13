// --------------------------------------------------------------------------

var step_prio_index = 0;
function getPrioIndex(){
	step_prio_index++;
	return step_prio_index;
}
var stepMetadata = {
    INFRASTRUCTURE:
    {
        priority: getPrioIndex(),
        name: "Configuration Issues",
        desc: "There are configuration issues which prevent the migration process from running successfully. Correct the configuration issues and re-run the migration assistant.",
        messages: {},
        link: {
            urlSuffix: '#infrastructure-issues',
            urlPrefixKey: 'step-description',
            info: "description"
        },
        ids:[]
    },
    MIGRATION_PLUGIN:
    {
        priority: getPrioIndex(),
        name: "Migration Plugin Exception",
        desc: "A plugin of the migration assistant has raised an exception. Reason could be that the original file format/grammar could not be recognized by the converting plugin." + 
        "Check details of each message below.",
        messages: {},
        link: {
            urlSuffix: '#infrastructure-issues',
            urlPrefixKey: 'step-description',
            info: "description"
        }
    },
    VIEW_MIGRATION: 
    {
        priority: getPrioIndex(),
        name: "Preparation Phase",
        desc: "Before commencing with the migration of your application you must manually migrate "
                      + "the listed artifacts in SAP HANA Studio. This applies to scripted calculation views, "
                      + "attribute views, analytic views, analytic privileges, AFL models (.aflpmml), and decision tables (.hdbruldec) files. Re-run the migration assistant once the manual migration has been "
                      + "completed.",
        link: {
            info: "description",
            urlSuffix: "#manual-migration-required",
            urlPrefixKey: 'step-description'
        },
        messages: {},
    },
    SECURITY: {
        priority: getPrioIndex(),
        "always-shown": true,
        name: "Migration of Security Concept Required",
        desc: "The security concept has changed with XS Advanced and is incompatible with XS Classic. Manual migration steps "
             + "are required in order to complete the migration of this application to XS Advanced. <br>"
             + "For information about the XS Advanced security concept read the XS Advanced Migration Guide.",
        link: {
            info: "description",
            urlSuffix: "#security",
            urlPrefixKey: 'step-description'
        },        
        messages: {},
    },
    UNSUPPORTED_FEATURE: {
        priority: getPrioIndex(),
        name: "Currently Unsupported or Discontinued Features",
        desc: "Some features have been discontinued. Manual migration is required.",
        link: {
            info: "description",
            urlSuffix: "#unsupported-feature",
            urlPrefixKey: 'step-description'
        }
    },
    HDI: {
        priority: getPrioIndex(),
        name: "Database Artifacts",
        desc: "Migration of database artifacts has raised the following messages.",
        link: {
            info: "description",
            urlSuffix: "#database-artifacts",
            urlPrefixKey: 'step-description'
        },
        messages: {},
    },
    XSJS: {
        priority: getPrioIndex(),
        name: "XSJS Migration Required",
        desc: "Statements in xsjs and xsjslib JavaScript files have been found that need to be migrated manually.",
        link: {
            info: "description",
            urlSuffix: "#xsjs",
            urlPrefixKey: 'step-description'
        },
        messages: {},
    },
    TRANSLATION: {
        priority: getPrioIndex(),
        name: "Translation",
        desc: "hdbtextbundle files and possible translations were found as part of the application. "
            + "Some actions are necessary as outlined in the messages below.",
        link: {
            info: "description",
            urlSuffix: "#translation",
            urlPrefixKey: 'step-description'
        },
        messages: {},
    },
    TODO: {
        priority: getPrioIndex(),
        name: "Unknown File Types or File Content",
        desc: "The XS migration assistant was not able to identify these file types. "
            + "You must decide whether these objects are still relevant, and copy them into the appropriate "
            + "module directory if needed.",
        link: {
            info: "description",
            urlSuffix: "#todo",
            urlPrefixKey: 'step-description'
        },
        messages: {},
    },
    OTHERS: {
        priority: getPrioIndex(),
        name: "Uncategorized Errors",
        desc: "TODO",
        link: {
            info: "description",
            urlSuffix: "#others",
            urlPrefixKey: 'step-description'
        },
        messages: {},
    },
    DELETE: {
        priority: getPrioIndex(),
        name: "Objects Which Have Not Been Migrated",
        desc: "The following objects have not been migrated because they are either not relevant anymore or have been successfully migrated to another object type.",
        link: {
            info: "description",
            urlSuffix: "#delete",
            urlPrefixKey: 'step-description'
        }   ,     
        messages: {},
    },
    VALIDATION: {
        priority: getPrioIndex(),
        name: "Messages Generated by Validation",
        desc: "todo",
        link: {
            info: "description",
            urlSuffix: "#delete",
            urlPrefixKey: 'step-description'
        }   ,     
        messages: {},        
    },
    JS: {
        priority: getPrioIndex(),
        name: "Javascript/UI5 files",
        desc: "Due to changes in the application structure some adjustments have to be made to the following files.",
        link: {
            info: "description",
            urlSuffix: "#js",
            urlPrefixKey: 'step-description'
        },
        messages: {}
    }
};

module.exports = stepMetadata;
