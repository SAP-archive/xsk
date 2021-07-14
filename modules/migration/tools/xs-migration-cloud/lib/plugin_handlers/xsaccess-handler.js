var logUtil = require('../log-util');
var path = require('path');
var plugin_utils = require('./plugin-utils');
var msgs = require('../messages.js');
var mc = require('../message-categories');
var infoHandler = require('../information-handler');

const web_standardTemplate =
{
    "welcomeFile" : "index.html",
    "authenticationMethod": "route"
};

const STANDARD_ROUTES = [
	{
		"source": ".*\\.xsjs.*",
		"destination": "xsjs"
	},
	{
		"source": ".*\\.xsodata.*",
		"destination": "xsjs"
	}];

var deleteItems = {};


var howtoItems = {
		rewrite_rules: function(content){
			var ret = {
				property: "routes",
				content: []
			};
			for(var i = 0; i < content.length; i++){
				var rule = content[i];
				ret.content.push(rule);
			}
			return ret;
		},
		authorization: function(content, runlocation){
			var ret = {
				property: "securityConstraints",
				content: []
			};	
            if(content == null || content == "null"){
                content = [];
            }
			ret.content.push({
				protectedPaths: path.dirname(runlocation),
				permission:content});

			return ret;
		}
};

function xsaccessHandler() {

    function analyzeContent(file){
    	var content = file.content.toString('utf8');
		var ret = {};
        try{
            var contentObj = JSON.parse(content);
        }
        catch(err){
            return ret;
        }
    	
    	//remove not applicable attributes
    	for(var prop in contentObj){
    		if(deleteItems[prop]){
    			delete contentObj[prop];
    			continue;
    		}
    		if(howtoItems[prop]){
    			var tmp = howtoItems[prop](contentObj[prop], file.RunLocation);
    			ret[tmp.property] = tmp.content;
    			
    		}
    		else{
    			ret[prop] = contentObj[prop];
    		}
    	}
    	return ret;
    }

    function filterFiles(accessfilesObj, filelist, globalContext){
    	var xsjsAccess = {};
    	var odataAccess = {};
    	var webAccess = {};			

		for(var i = 0; i < filelist.length; i++){
    		var fileObj = filelist[i];
            var filePath = path.normalize(fileObj.name);
            
			var webPattern = 'web' + path.sep + 'resources';
			var xsjsPattern = 'xsjs' + path.sep + 'lib';
			
			filePath = filePath.replace(webPattern, '');			
			filePath = filePath.replace(xsjsPattern, '');
			var filePathDir = path.normalize(path.dirname(filePath));
			
			if(accessfilesObj[filePathDir]){
				if(!webAccess[path.dirname(filePath)]){
    				webAccess[path.dirname(filePath)] = accessfilesObj[path.dirname(filePath)].update_content;
    			}
    		}
		}
    	return {
    		xsjs: xsjsAccess,
    		odata: odataAccess,
    		web: webAccess
    	};
    }
    
    function mergeFile(filesObj, globalContext){
    	var newObj = {};
		for(var prop in filesObj){
    		var obj = filesObj[prop];
    		if(obj.routes){
    			if(!newObj.routes){
    				newObj.routes = [];
    			}
    			newObj.routes = newObj.routes.concat(obj.routes);
    		}
    		if(obj.securityConstraints){
    			if(!newObj.securityConstraints){
    				newObj.securityConstraints = [];
    			}
    			newObj.securityConstraints = newObj.securityConstraints.concat(obj.securityConstraints);
    		}
			if(obj.default_file && prop === globalContext.app_root_path){
				newObj.welcomeFile = path.join('resources', globalContext.app_root_path, obj.default_file);	
			}
    	}
    	return newObj;
    }
    //post process function, will be triggered after all files have been processed in xs1toxs2 function
    //globalContext will store the xsaccess temp content, which is accessfilesObj
    this.combineFiles = function(accessfilesObj, filelist, globalContext){
    	var files = filterFiles(accessfilesObj, filelist, globalContext);
    	logUtil.debug("filtered files" + JSON.stringify(files, null, 4));
    	var xsjsAccess =  files.xsjs;
    	var webAccess = files.web;
    	var odataAccess = files.odata;
    	var xsjsObj = mergeFile(xsjsAccess, globalContext);
    	var webObj = mergeFile(webAccess, globalContext);
		
		if(!webObj.welcomeFile) {
			webObj["welcomeFile"] = web_standardTemplate.welcomeFile;
		} else {
			webObj['welcomeFile'] = webObj['welcomeFile'].replace(/\\/g, '/');
		}
    	
    	webObj["authenticationMethod"] = web_standardTemplate.authenticationMethod;
    	
		if(webObj["routes"] && webObj["routes"].length > 0){
			let msg = msgs.getMessage(mc.SECURITY, 13);
			infoHandler.logMessage(msg);
		}
		webObj["routes"] = Array.from(STANDARD_ROUTES);
        if(webObj["securityConstraints"] && webObj["securityConstraints"].length > 0){
            for(var i = 0; i < webObj["securityConstraints"].length; i++){
                var constraint =  webObj["securityConstraints"][i];
                for(var p_i = 0; p_i < constraint["permission"].length; p_i++){
					let newPermission = constraint["permission"][p_i].replace('::', '.');
                    constraint["permission"][p_i] = "$XSAPPNAME" + "." + newPermission;
                }
				
				let sourcePath = "^" + constraint["protectedPaths"] + "/(.*)$";
				let newRoute = {
					"source": sourcePath,
					"localDir": "resources"
				};
				
				if(constraint.permission.length > 0) {
					newRoute.scope = constraint["permission"];
				}
				
                webObj["routes"].push(newRoute);
            }
        }
        delete webObj["securityConstraints"];
    	var odataObj = mergeFile(odataAccess);
    	logUtil.trace("combined files" + JSON.stringify({
    		xsjs: xsjsObj,
    		web: webObj,
    		odata: odataObj
    	}, null, 4));
    	return {
    		xsjs: xsjsObj,
    		web: webObj,
    		odata: odataObj
    	}
	};
	

    this.handleFile = function(file, globalContext, callback) {
    	
    	//create web/xs-app.json
    	globalContext["xs-app"][path.normalize(path.dirname(file.RunLocation))] = {
                filename: file.RunLocation,
                "package": path.dirname(file.RunLocation),
                content: file.content
            };
    	var content = analyzeContent(file);
    	globalContext["xs-app"][path.normalize(path.dirname(file.RunLocation))].update_content = content;
        //analyse reportable properties:
        if(content["anonymous_connection"] || content["default_connection"]){
            let msg = msgs.getMessage(mc.SECURITY, 1);
            msg.file = path.join(globalContext.config.directories['xs1-src'], file.RunLocation);
            plugin_utils.pushMessage(file, msg);
        }
		
		if(content.cors && content.cors.enabled == true){
			let msg = msgs.getMessage(mc.SECURITY, 14);
			msg.file = path.join(globalContext.config.directories['xs1-src'], file.RunLocation);
			plugin_utils.pushMessage(file, msg);
		}

		file.doNotWriteContent();

        var msg = msgs.getMessage(mc.SECURITY, 2);
        msg.file = path.join(globalContext.config.directories['xs1-src'], file.RunLocation);
    	plugin_utils.pushMessage(file, msg);
    	//use dump file to check origin part
        callback(null, file);
    }
}

module.exports = new xsaccessHandler();
