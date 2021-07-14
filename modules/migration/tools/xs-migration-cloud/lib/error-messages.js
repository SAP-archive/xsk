/**
 * Created by SAP SE.
 */

const BUILTIN_PROCEDURE_ORIGINAL_MESSAGE = "feature not supported: not supported builtin procedure";
const BUILTIN_PROCEDURE_NEW_MESSAGE = "Builtin procedure get_objects_in_ddl_statement not activated\n" +
    " -> add entry enable_builtin_procedure_get_objects_in_ddl_statement with value true to configuration section sqlscript of indexserver.ini or nameserver.ini (in case the db is the system db of a mdc system).";

const SCHEMA_VALIDATION_ANY_ORIGINAL_MESSAGE = "is not any of [subschema 0],[subschema 1]";
const SCHEMA_VALIDATION_ANY_NEW_MESSAGE = "You must specify at least one of the properties package and schemaName.";

const SCHEMA_VALIDATION_ONE_ORIGINAL_MESSAGE = "instance is not exactly one from [subschema 0],[subschema 1]";
const SCHEMA_VALIDATION_ONE_NEW_MESSAGE = "You can either specify a logical-schema or a grantor-service as a target provider, but not both or none.";


function getErrorMessage(error) {


    if(error instanceof Array) {

        let errorMessage = '';
        
        if(error.length > 1){
            errorMessage += 'Multiple Errors occured:\n\n';
            errorMessage  += '\n-------------------------------------\n';

        }
        
        for(let err of error) {
            errorMessage += err.toString();
            errorMessage  += '\n-------------------------------------\n'; 
        }        
        
        return errorMessage;        
    }
    
    
    switch (error.message) {        
        case BUILTIN_PROCEDURE_ORIGINAL_MESSAGE:
            return BUILTIN_PROCEDURE_NEW_MESSAGE;
        case SCHEMA_VALIDATION_ANY_ORIGINAL_MESSAGE:
            return SCHEMA_VALIDATION_ANY_NEW_MESSAGE;
        case SCHEMA_VALIDATION_ONE_ORIGINAL_MESSAGE:
            return SCHEMA_VALIDATION_ONE_NEW_MESSAGE;
        default:
            return error.message;
    }    
    
}


module.exports.getErrorMessage = getErrorMessage;