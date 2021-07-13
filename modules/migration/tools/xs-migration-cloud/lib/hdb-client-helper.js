/**
 * Created by SAP SE on 24.06.16.
 */

var hdb = require('hdb');
var logUtil = require('./log-util');
var fs = require('fs');
var async = require('async');
const path = require('path');
const {loggerConfig} = require("./bas/logging/logger-config");

function isBasEnvironment() {
    return process.env.NODE_ENV && process.env.NODE_ENV === 'bas';
}

function prepareAndConnect(system, callback){

    var clientConfig = {
        host: system.host,
        port: system.sqlport,
        user: system.user,
        password: system.password,
        useCesu8: true
    };
    // The following would only work with new hana-client, but not with old node-hdb:
    // clientConfig['sessionVariable:APPLICATION'] = 'SAP_XSAC_MIGRATION_HDI';
    // clientConfig['sessionVariable:APPLICATIONVERSION'] = getApplicationVersion();

    var clientConfigLog = Object.assign({}, clientConfig);

    if (loggerConfig.maskPasswords) {
        clientConfigLog.password = "*******";
    }

    logUtil.debug('database configuration: \n' + JSON.stringify(clientConfigLog, null, 4));
    
    if(system.certificate){
        logUtil.info('Certificate parameter set: ' + system.certificate);
        clientConfig.ca = [system.certificateContent];
    }

    var client = hdb.createClient(clientConfig);

    client.on('error', function (err) {
        logUtil.error('Network connection error: ' + err);
    });

    client.on('close', function () {
        logUtil.info('Hana connection closed');
    });


    client.connect(function(error){
        if(error) {
            logUtil.error('Error while connecting to database: ' + error);
            return callback(error);
        }
        logUtil.info('Database connection successful');

        logUtil.logProgress(1);

        //set AppId and AppVersion on connection
        async.waterfall([function(cb) {
            var setAppId = "SET 'APPLICATION' = 'SAP_XSAC_MIGRATION_HDI'";
            client.exec(setAppId, cb);
        }, function(cb) {
            var setAppVersion = "SET 'APPLICATIONVERSION' = '"+getApplicationVersion()+"'";
            client.exec(setAppVersion, cb);
        }],function(error) {
            return callback(error);
        });

    });

    return client;
}

function getApplicationVersion() {
    try {
        const pathToThePackageJson = path.join(path.dirname(module.filename), '..', 'package.json');
        var pkg = JSON.parse(fs.readFileSync(pathToThePackageJson, 'utf8'));
        return pkg.name + '@' + pkg.version;
    } catch (err) {
        console.log(err);
    }
  }

module.exports.prepareAndConnect = prepareAndConnect;
