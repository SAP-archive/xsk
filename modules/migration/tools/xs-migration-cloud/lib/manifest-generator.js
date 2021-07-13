// --------------------------------------------------------------------------
//
// Manifest Geneartor for Clound Foundry's manifest.yml
//
// --------------------------------------------------------------------------

var fs = require('fs');
var path = require('path');
var async = require('async');

var logUtil = require('./log-util');
var utils = require('./generator-utils');

var TEMPLATE_DIR = path.join(
                    path.dirname(module.filename),
                    '..',
                    'templates');

var manifestNameCf = 'manifest';
var manifestNameOp = 'manifest-op';


const DB_SERVICE_NAME = '- ${app-name}-hdi';


function ManifestGenerator(){
	var that = this;

	this.generateManifest = function(fpath, filename, infoHandler, context, callback)
	{
		logUtil.trace("got containers" + JSON.stringify(infoHandler.messages, null, 4));
		
		var content = that.getManifestContent(infoHandler, context, manifestNameCf);
        var content_op = that.getManifestContent(infoHandler, context, manifestNameOp);
        var op_filename = filename.substring(0, filename.length - path.extname(filename).length) + "-op" + path.extname(filename);
		
        logUtil.debug("write dir: " + path.join(fpath,filename));
        logUtil.debug("write dir op: " + path.join(fpath,op_filename));
		
		async.parallel({
			manifest: function (callback) {
				utils.writeFile(fpath, filename, content, callback);
			},
			manifestOp: function (callback) {
				utils.writeFile(fpath, op_filename, content_op, callback);
			}
		}, function (error) {
			return callback(error);
		});		
		
	};
	
	
	this.getManifestContent = function(infoHandler, context, manifestName) {

		try {
			var content = fs.readFileSync(
                           path.join(TEMPLATE_DIR, manifestName + '.yml'),
                           { encoding : 'utf8'});
			
			for(container of context.containersPresent){
				if(container !== 'todo') {
					let partContentName = manifestName + '-' + container + '.yml';
					let partContent = fs.readFileSync(path.join(TEMPLATE_DIR, partContentName), {encoding: 'utf8'});
					content += partContent + '\n\n';
				}
			}
			
			logUtil.trace("content: " + content);
		} catch (e) {
			logUtil.error("Error occurred on file reading");
			logUtil.error(e.message);
            return;
		}

        var replaceValues = {
            "app-name": context.project.name,
            "xsjs-url": "http://" + context.project.name + "-xsjs-service"
        };
        if (context.hasSynonyms && context.synonymTargetProviders.grantorServiceNames.length > 0) {
			replaceValues['grantor-service'] = '- ' + context.synonymTargetProviders.grantorServiceNames.join('\n      - ');			
        } else {
            replaceValues['grantor-service'] = "";
        }
		if(context.containersPresent.indexOf('db') > -1) {
			replaceValues['db-service'] = DB_SERVICE_NAME;
		} else {
			replaceValues['db-service'] = '';
		}
		
        content = replaceVar(content, replaceValues);
		content = replaceVar(content, replaceValues);
        return content;
	};
    
}

function  replaceVar(str, inputObj)
{

    for (var varname in inputObj) {
        var pt = new RegExp("\\$\\{" + varname + "\\}", "g");
        str = str.replace(pt, inputObj[varname]);
    }
    return str;
}

module.exports = new ManifestGenerator();
