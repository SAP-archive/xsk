var logUtil = require('../log-util');
var utils = require('../generator-utils');
var path = require('path');
var mc = require('../message-categories');
var msgs = require('../messages');
var infoHandler = require('../information-handler');
var async = require('async');


function XSSecurityHandler() {
    var plugin_utils = require('./plugin-utils');

    var that = this;

    var localSourceDb;

    this.handleFile = function (file, globalContext, callback) {

        var fileContent = file.content.toString('utf8');
        if (file && (file.RunLocation.indexOf(".xsprivileges", file.RunLocation.length - ".xsprivileges".length) !== -1) && fileContent) {
            var privObj = JSON.parse(fileContent);
            if (privObj.privileges) {
                for (var privItemIndex in privObj.privileges) {
                    var privItem = privObj.privileges[privItemIndex];

                    var pkg = path.dirname(file.RunLocation).replace(/\//g, '.');
                    if (pkg.substring(0, 1) == '.') {
                        pkg = pkg.substring(1);
                    }
                    privItem.name = pkg + "." + privItem.name;
                    if (!globalContext.privileges) {
                        globalContext.privileges = [];
                    }
                    globalContext.privileges.push(privItem);
                }
            }
            let msg = msgs.getMessage(mc.SECURITY, 7);
            msg.file = path.join(globalContext.config.directories['xs1-src'], file.RunLocation);
            plugin_utils.pushMessage(file, msg);
        } else if (file && (file.RunLocation.indexOf(".xssecurestore", file.RunLocation.length - ".xssecurestore".length) !== -1) && fileContent) {
            let msg = msgs.getMessage(mc.UNSUPPORTED_FEATURE, 6);
            msg.message.push(".xssecurestore");
            msg.file = path.join(globalContext.config.directories['xs1-src'], file.RunLocation);
            plugin_utils.pushMessage(file, msg);
        } else if (file && (file.RunLocation.indexOf(".xssqlcc", file.RunLocation.length - ".xssqlcc".length) !== -1) && fileContent) {
            let msg = msgs.getMessage(mc.SECURITY, 8);
            msg.file = path.join(globalContext.config.directories['xs1-src'], file.RunLocation);
            plugin_utils.pushMessage(file, msg);
        }
        file.doNotWriteContent();
        callback(null, file);
    };

    this.getAllPrivs = function (name, map, direct) {
        var ret = [];
        var appPrivs = map[name];
        if (!appPrivs) {
            if (direct)
                logUtil.error("privilege " + name + " has been used as extension for role " + name + ", but definition is not found in project");
                return [];
        }
        if (appPrivs["roles"] && appPrivs["roles"].length > 0) {
            for (var i = 0; i < appPrivs["roles"].length; i++) {
                ret = ret.concat(that.getAllPrivs(appPrivs["roles"][i].name, map, false));
            }
        }
        if (appPrivs["applicationPrivileges"]) {
            for (var i = 0; i < appPrivs["applicationPrivileges"].length; i++) {
                var item = appPrivs["applicationPrivileges"][i];
                ret.push(item.name);
            }
        }
        logUtil.trace("privilege " + name + " has " + ret);
        return ret;
    };



    function replaceUnsupportedChars(strArray, unsupportedChars, replacement) {
        var ret = [];
        for (var i = 0; i < strArray.length; i++) {
            var tmpStr = strArray[i];
            for (var j = 0; j < unsupportedChars.length; j++) {
                var tmp = new RegExp(unsupportedChars[j], 'g');
                tmpStr = tmpStr.replace(tmp, replacement);
            }
            ret.push(tmpStr);
        }
        return ret;
    }


    this.generateSecurityFiles = function (prjName, privileges, roles, config, fpath, callback) {
        var contentObj = {};
        contentObj.xsappname = prjName;
        contentObj.scopes = [];
        var appName = "$XSAPPNAME";
        var foreignAppName = "$FOREIGNXSAPPNAME";
        var privMap = {};
        if (privileges && privileges.length) {
            for (let i = 0; i < privileges.length; i++) {
                //update format to "$XSAPPNAME.privilege"
                privMap[privileges[i].name] = true;
                let tempName = appName + "." + privileges[i].name;
                contentObj.scopes.push({
                    "name": tempName,
                    "description": privileges[i].description ? privileges[i].description : "your description"
                });
            }
        }

        contentObj["role-templates"] = [];
        for (var i = 0; i < roles.length; i++) {
            var item = roles[i];
            for (var pi in item["scope-references"]) {
                if (privMap[item["scope-references"][pi]]) {
                    let tempName = appName + "." + item["scope-references"][pi];
                    item["scope-references"][pi] = tempName;
                } else {
                    var msg = {
                        type: mc.ERROR,
                        message: ["You have used foreign scope {0} in role {1}, you need to replace $FOREIGNXSAPPNAME with the real service name in XS Advanced", item["scope-references"][pi], item.role],
                        category: mc.SECURITY,
                        id: mc.SECURITY + '_3',
                        file: ""
                    };
                    infoHandler.logMessage(msg);
                    item["scope-references"][pi] = foreignAppName + "." + item["scope-references"][pi];
                }

            }
            let tempName = item.role;
            tempName = replaceUnsupportedChars([tempName], ['::', ':', '\\.', '\\\\', '/'], "_")[0];
            contentObj["role-templates"].push({
                "name": tempName,
                "scope-references": item["scope-references"]
            });
        }
        if (contentObj["role-templates"].length == 0) {
            delete contentObj["role-templates"];
        }
        //create File
        logUtil.trace("write xs-security.json: " + fpath + "/xs-security.json");
        utils.writeFile(fpath, "xs-security.json", JSON.stringify(contentObj, null, 4), function (err) {
            if (err) {
                logUtil.error("error found:" + err);
            }
            return callback(err);
        });
    };


    function collectApplicationPrivileges(globalRoles, sourceDb, methodCallback) {
        if(!globalRoles) {
            return methodCallback(null, []);
        }
        var roleNames = Object.keys(globalRoles);

        async.map(roleNames, function(roleName, callback){
            collectApplicationPrivilege(roleName, sourceDb, callback);
        }, function (error, rolesWithPrivileges) {
            return methodCallback(error, rolesWithPrivileges);
        });

    }


    function collectApplicationPrivilege(roleName, sourceDb, methodCallback){

        sourceDb.getApplicationPrivileges(roleName.toString(), function (error, applicationPrivileges) {
            if(error){
                return methodCallback(error);
            }
            var roleWithPrivileges = {
                role: roleName,
                "scope-references" : applicationPrivileges
            };

            return methodCallback(null, roleWithPrivileges);
        });

    }




    function writeSecurityFiles(globalContext, privileges, roles, callback){

        if((privileges && privileges.length > 0) || roles.length > 0){
            var appName = globalContext.project.name ? globalContext.project.name : "";
            var rootPath = globalContext.pathMap["xs2-app-root"];

            return that.generateSecurityFiles(appName, privileges, roles, globalContext.config, rootPath, callback);
        }

        return callback();
    }


    this.postcalculation = function (globalContext, pluginHandler, packages, sourceDb, methodCallback) {

        localSourceDb = sourceDb;
        var privileges = globalContext.privileges;
        var globalRoles = globalContext.roles;

        if((!privileges || privileges.length <= 0) && (!globalRoles || Object.getOwnPropertyNames(globalRoles).length <= 0)){
            let msg = {
                type: mc.WARNING,
                message: ["Application does not have any role or privileges defined. Therefore we could not generate xs-security.json and default_access_role.hdbrole for you."],
                category: mc.SECURITY,
                id: mc.SECURITY + '_1',
                file: ""
            };
            infoHandler.logMessage(msg);
            return methodCallback();
        }

        globalContext.securityFileExists = true;

        async.waterfall([
            function (callback) {
                collectApplicationPrivileges(globalRoles, sourceDb, callback);
            },
            function (rolesWithPrivileges, callback) {
                writeSecurityFiles(globalContext, privileges, rolesWithPrivileges, callback);
            }
        ], function (error) {
            return methodCallback(error);
        });


    };

}

module.exports = new XSSecurityHandler();
