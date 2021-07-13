var utils = require('./plugin-utils');
var mc = require('../message-categories');
var msgs = require('../messages');

var UsedObject = require('../used-object');

var path = require('path');

const CURRENCY_TABLES = ["TCURV", "TCURR", "TCURF", "TCURN", "TCURX"];

module.exports = {


    addCurrencyTablesToRelatedObjects: function addCurrencyTablesToRelatedObjects(defaultSchema, ddl, globalContext, userName) {

        let CE_CONVERSION_PATTERN = /CE_CONVERSION\s*\(/;
        
        let currencyObjects = new Map();

        // add currency conversion tables to related objects if conversion is used
        if (ddl.match(CE_CONVERSION_PATTERN)) {

            CURRENCY_TABLES.forEach((tableName) => {
                let currencyObject = new UsedObject(defaultSchema, tableName);
                currencyObject.addUserName(userName);
                utils.handleUsedObject(currencyObject, globalContext);
                
                currencyObjects.set(tableName, currencyObject);                
            });
            return currencyObjects;
        }
        return null;
    },

    
    addMessage: function (globalContext, file, schema, location, messageId) {
        let msg = msgs.getMessage(mc.HDI, messageId);
        msg.id = mc.getId(mc.HDI, messageId);
        msg.file = path.join(globalContext.pathMap['db-relative-path'], file.RunLocation);
        //msg.message.push(file.fileName);
        //msg.loc = [{start: location}];

        utils.pushMessage(file, msg);
    },

    
    addSynonymsToCeConversion: function (file, defaultSchema, globalContext, ddl, currencyObjects) {

        let util = this;
     

        // create configuration parameters
        let currencyConfiguration = `configuration_table = '${currencyObjects.get("TCURV").synonymName}', rates_table = '${currencyObjects.get("TCURR").synonymName}', prefactors_table = '${currencyObjects.get("TCURF").synonymName}', notations_table = '${currencyObjects.get("TCURN").synonymName}', precisions_table = '${currencyObjects.get("TCURX").synonymName}', `;

        let changes = [];
        let regex = /ce_conversion\s*\(\s*.*\s*,\s*\[/gi;
        while (result = regex.exec(ddl)) {
            changes.push(result.index + result[0].length);
        }

        changes.reverse();

        changes.forEach((changeLocation) => {
            ddl = [ddl.slice(0, changeLocation), currencyConfiguration, ddl.slice(changeLocation)].join('');
            util.addMessage(globalContext, file, defaultSchema, changeLocation, 16);
        });

        return ddl;
    }
};