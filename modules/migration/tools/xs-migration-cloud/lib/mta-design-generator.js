
var path = require('path');
var fs = require('fs');


var TEMPLATE_DIR = path.join(path.dirname(module.filename),
                             '..',
                             'templates', 'mta');

const MTA_NAME = "mta.yaml";
const MTA_RESSOURCES = 'mta-resources.yaml';
const REPLACE_PATTERN = /\s+\{{(.*)}}/; 

function MTADesignDescriptorGenerator() {

    function loadTemplate(context) {
        var replacementConfiguration = getReplacementConfiguration(context);
        
        var mta = fs.readFileSync(path.join(TEMPLATE_DIR, MTA_NAME), 'utf-8');
        
        var modules = generateModules(context, replacementConfiguration);
        mta = mta.replace('{{modules}}', modules);
        
        var resources = generateResources(context, replacementConfiguration);
        mta = mta.replace('{{resources}}', resources);
        
        return mta.toString("utf8");
    }
    
    function generateModules(context, replacementConfiguration){
        var modules = '';

        for(container of context.containersPresent){
            if(container !== 'todo'){
                let moduleName = 'mta-' + container + '-module.yaml';
                let module = fs.readFileSync(path.join(TEMPLATE_DIR, moduleName), 'utf-8');
                module = findReplace(module, replacementConfiguration, context.synonymTargetProviders.grantorServiceNames);
                modules += module + '\n\n';
            }
        }
        
        return modules;
    }

    
    function generateResources(context, replacementConfiguration){
        
        var resourcesBase = fs.readFileSync(path.join(TEMPLATE_DIR, MTA_RESSOURCES), 'utf-8');
        var finalResources = findReplace(resourcesBase, replacementConfiguration, context.synonymTargetProviders.grantorServiceNames);
        
        
        return finalResources;
    }
    
    
    function findReplace(string, replacementConfiguration, grantorServiceNames){
        var found;
        
        while((found = REPLACE_PATTERN.exec(string)) !== null){
            let replacement = '';
            if(replacementConfiguration[found[1]]){
                if(found[1] === 'db-synonyms' && grantorServiceNames.length > 0){
                    replacement = '\n      - name: ' + grantorServiceNames.join('\n      - name: ');
                } else if(found[1] === 'resource-synonym') {
                    
                    let replacementFilename = found[1] + '.yaml';
                    let replacementRaw = fs.readFileSync(path.join(TEMPLATE_DIR, replacementFilename), 'utf-8');
                    replacement += '\n';
                    
                    grantorServiceNames.forEach(function (grantorServiceName) {
                        let replacementFinal = replacementRaw.replace(/\{\{grantor-service-name\}\}/g, grantorServiceName);
                        replacement += replacementFinal;
                    });
                    
                } else if (found[1] === 'resource-uaa') {
                    
                    let replacementFilename = found[1] + '.yaml';
                    let replacementRaw = fs.readFileSync(path.join(TEMPLATE_DIR, replacementFilename), 'utf-8');
                    replacement += '\n';
                    
                    let securityReplacement = '';
                    if(replacementConfiguration['xs-security']) {
                        securityReplacement = 'parameters:\n' +
                            '      path: xs-security.json';
                    }
                    
                    replacement = replacementRaw.replace(/\{\{security-json\}\}/g, securityReplacement);
                    
                    
                } else if(found[1] !== 'db-synonyms') {
                    let replacementFilename = found[1] + '.yaml';
                    replacement = '\n' +fs.readFileSync(path.join(TEMPLATE_DIR, replacementFilename), 'utf-8');    
                }
            }
            string = string.replace(found[0], replacement);
        }
        
        return string;
    }

    
    function getReplacementConfiguration(context){
        return {
            'db-synonyms': context.hasSynonyms,
            'resource-uaa': ((context.containersPresent.indexOf('web') > -1 || context.containersPresent.indexOf('xsjs') > -1)),
            'resource-ui5': (context.containersPresent.indexOf('web') > -1),
            'resource-hdi': (context.containersPresent.indexOf('db') > -1),
            'resource-jobs': (context.hasJobs),
            'resource-synonym': (context.hasSynonyms),
            'xsjs-db': (context.containersPresent.indexOf('db') > -1),
            'xsjs-jobs': (context.hasJobs),
            'web-xsjs': (context.containersPresent.indexOf('xsjs') > -1),
            'xs-security': (context.securityFileExists)
        }
    }
    
    
    function saveMta(filename, mta) {
        fs.writeFileSync(filename, mta);
    }

    function  replaceVar(str, inputObj)
    {
        for (var varname in inputObj) {
            var pt = new RegExp("\\$\\{" + varname + "\\}", "g");
            str = str.replace(pt, inputObj[varname]);
        }
        return str;
    }

    this.generateMtaDesignDescriptor = function(context, callback) {
        var mta = loadTemplate(context);
        var replObj = {
            name: context.project.name,
            version: context.project.version,
            description: context.project.description
        };
        mta = replaceVar(mta, replObj);
        var filename = path.join(context.pathMap["xs2-app-root"], MTA_NAME);
        saveMta(filename, mta);
        callback();
    }
}

module.exports = new MTADesignDescriptorGenerator();
