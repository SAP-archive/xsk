/**
 * Created by SAP SE.
 */
require('dotenv').config({silent: true});

var HdbClientHelper = require('../lib/hdb-client-helper');
var async = require('async');
var fs = require('fs');

var system = {
    host: process.env.HANA_HOST,
    sqlport: process.env.HANA_SQL_PORT,
    user: process.env.HANA_USER,
    password: process.env.HANA_PASSWD
};

var command = "SELECT SCHEMA_NAME, OBJECT_NAME, PRIVILEGE, IS_GRANTABLE FROM SYS.GRANTED_PRIVILEGES WHERE GRANTEE = '_SYS_DI_OO_DEFAULTS'";

var hdbClient;

async.waterfall([
    function (callback) {
       hdbClient = HdbClientHelper.prepareAndConnect(system, callback);       
   }, 
   function (callback) {
       console.log('\nexecuting command');
       hdbClient.exec(command, function(err, rows) {
           callback(err, rows);
       });       
   },
   function (rows, callback) {
       console.log('\nexecuted');
       fs.writeFile('hdi-default-privileges.json', JSON.stringify(rows, null, 4), callback);
   }
], function (error) {
    console.log('finished');
    console.log(error);
    process.exit(0);
});

