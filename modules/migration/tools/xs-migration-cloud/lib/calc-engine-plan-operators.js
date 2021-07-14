/**
 * Created by SAP SE.
 */

var path = require('path');

var pluginUtils = require('./plugin_handlers/plugin-utils');
var messages = require('./messages');
var mc = require('./message-categories');
var UsedObject = require('./used-object');

const planOperators = [ 
    { pattern: /CE_COLUMN_TABLE\s*\(/, name: 'CE_COLUMN_TABLE' },
    { pattern: /CE_JOIN_VIEW\s*\(/, name: 'CE_JOIN_VIEW' },
    { pattern: /CE_OLAP_VIEW\s*\(/, name: 'CE_OLAP_VIEW' },
    { pattern: /CE_CALC_VIEW\s*\(/, name: 'CE_CALC_VIEW' },
    { pattern: /CE_JOIN\s*\(/, name: 'CE_JOIN' },
    { pattern: /CE_LEFT_OUTER_JOIN\s*\(/, name: 'CE_LEFT_OUTER_JOIN' },
    { pattern: /CE_RIGHT_OUTER_JOIN\s*\(/, name: 'CE_RIGHT_OUTER_JOIN' },
    { pattern: /CE_PROJECTION\s*\(/, name: 'CE_PROJECTION' },
    { pattern: /CE_UNION_ALL\s*\(/, name: 'CE_UNION_ALL' },
    { pattern: /CE_CONVERSION\s*\(/, name: 'CE_CONVERSION' },
    { pattern: /CE_AGGREGATION\s*\(/, name: 'CE_AGGREGATION' },
    { pattern: /CE_CALC\s*\(/, name: 'CE_CALC' }
]; 
    

const dataSourceAccessOperators = [
    /ce_column_table\s*\(\s*([\S\s]*?)(,[\S\s]*?)?\);/gi,
    /ce_join_view\s*\(\s*([\S\s]*?)(,[\S\s]*?)?\);/gi,
    /ce_calc_view\s*\(\s*([\S\s]*?)(,[\S\s]*?)?\);/gi
];

    
class CalcEnginePlanOperators {
    
    static _getUsedOperators(ddl) {
        
        var usedOperators = [];
        
        planOperators.forEach(function(planOperator) {           
            if(ddl.match(planOperator.pattern)) {
                usedOperators.push(planOperator.name);
            }
        });
        
        return usedOperators;        
    }
    
    
    static logMessagesForUsedOperators(ddl, file, globalContext) {
        
        var usedOperators = CalcEnginePlanOperators._getUsedOperators(ddl);
        
        usedOperators.forEach(function (usedOperator) {
            let message = messages.getMessage(mc.HDI, 18);
            
            message.message.push(usedOperator);
            message.file = path.join(globalContext.pathMap['db-relative-path'], file.RunLocation);
            
            pluginUtils.pushMessage(file, message);
        });
        
    }
    
    
    
    static handleDataSourceAccessOperators(ddl, defaultSchema, globalContext, userName) {
    
        for(let operator of dataSourceAccessOperators) {
            ddl = CalcEnginePlanOperators._handleDataSourceAccessOperator(operator, ddl, defaultSchema, globalContext, userName);   
        }        
        
        return ddl;
    }
    
    
    
    static _handleDataSourceAccessOperator(operator, ddl, defaultSchema, globalContext, userName) {
        
        let matches;
        let changes = [];
        
        while((matches = operator.exec(ddl)) !== null) {

            let referencedObjectString = matches[1];
            let schema = pluginUtils.getSchemaName(referencedObjectString);
            
            if(schema === null){
                schema = defaultSchema;
            }
             
            let object = pluginUtils.getObjectName(referencedObjectString);

            let usedObject = new UsedObject(schema, object);
            usedObject.addUserName(userName);


            if(!globalContext.handledObjectNames.includes(object)) {
                pluginUtils.handleUsedObject(usedObject, globalContext);
            }
            
            let end = matches.index + matches[0].length;
            changes.push({synonymName: usedObject.synonymName, begin: matches.index, end: end, oldString: referencedObjectString});
        }
        
        changes.reverse();
        
        for(let change of changes) {
            
            let before = ddl.slice(0, change.begin);
            let after = ddl.slice(change.end);            
            let between = ddl.slice(change.begin, change.end);
            
            between = between.replace(change.oldString, '"' + change.synonymName + '"');
          
            ddl = [before, between, after].join('');            
        }
        
        return ddl;
    }
    
    
}

module.exports = CalcEnginePlanOperators;