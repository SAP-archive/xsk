/**
 * Created by SAP SE.
 */
    

function ConfigValidationError(message, configFile) {
    
    var error = new Error(message);
    Object.setPrototypeOf(error, ConfigValidationError.prototype);
    
    error.configFile = configFile;   
    
    return error;
}

ConfigValidationError.prototype = Object.create(
    Error.prototype,
    {name: {value: 'ConfigValidationError', enumerable: false}}
);


module.exports = ConfigValidationError;