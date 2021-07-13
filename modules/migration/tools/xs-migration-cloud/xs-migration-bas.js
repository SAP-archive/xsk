const vscode = require('vscode');

require('dotenv').config({silent: true});

var fs = require('fs');
var path = require('path');
var async = require('async');
var packageJson = require('./package');

var reportLib = require('./lib/information-handler');
var pluginHandler = require('./lib/plugin-handler');

var utils = require('./lib/generator-utils');
var logUtil = require('./lib/log-util');
var inputParser = require("./lib/input-parser");
var xsaccessHandler = require('./lib/plugin_handlers/xsaccess-handler');
var projectCreator = require('./lib/project-structure-creator');
var report = require('./lib/report-generator');
var stepGen = require('./lib/step-generator');
var mtaGen = require('./lib/mta-design-generator');
var fileUtils = require('./lib/file-utils');
var hdbClientHelper = require('./lib/hdb-client-helper');
var SourceDb = require('./lib/source-db');
var SqlParser = require('./lib/sql-parser');
var HanaRepository = require('./lib/hana-repository');
var ErrorMessages = require('./lib/error-messages');
var preprocessor = require('./lib/preprocessor');
var ConfigValidationError = require('./lib/config-validation-error');
var ZipArchiver = require('./lib/zip-archiver');

const MigrationUtils = require('./lib/migration-utils');
const NeoTunnelUtils = require('./lib/neo/neo-tunnel');

function preprocessing(globalContext, fileList, packages, sourceDb, sqlParser, callback){
    preprocessor.process(fileList, globalContext, sourceDb, function (error, fileObjects) {
        if(error){
            return callback(error);
        }

        callback(null, globalContext, fileList, packages, sourceDb, sqlParser);
    });
}


function processing(context, list, packages, sourceDb, sqlParser, callback) {
    logUtil.info('Start processing...');
    pluginHandler.process(list, context, packages, sourceDb, sqlParser, function (error) {
        callback(error, context, list, packages);
    });
}

function postprocessing(context, list, packages, callback) {
    if(context.containersPresent.indexOf('web') > -1){
        logUtil.debug('web container present, generating xs-app.json');
        var filelist = context.filePathIndex.toArray();
        var appfilesObj = xsaccessHandler.combineFiles(context["xs-app"], filelist, context);
        utils.writeFileSync(context.pathMap["web-full-path"], "xs-app.json", JSON.stringify(appfilesObj.web, null, 4));
    } else {
        logUtil.debug('no web container present, skipping generation of xs-app.json');
    }


    callback(null, context, list, packages);
}


function getSystem(context, sourceDb, callback) {

    async.series([
        function(callback){
            sourceDb.getSystemInformation(callback);
        }
    ], function(error, result){
        if(error){
            return callback(error);
        }

        var systemInfo =  {
            host: context.system.host,
            port: context.system.sqlport,
            user: context.system.user,
            sid: result[0].SYSTEM_ID,
            hana_version: result[0].VERSION
        };

        return callback(null, systemInfo);
    });

}

function getTask(context) {
    var task = {
        dus: [],
        packages: []
    };
    context.task.deliveryUnits.forEach(function (du) {
        task.dus.push({
            name: du.name,
            vendor: du.vendor,
            version: getDuVersion(du)
        });
    });
    context.task.packages.forEach(function (p) {
        task.packages.push(p.packageName);
    });
    return task;
}

function writeReport(context, list, sourceDb, callback) {

    logUtil.info("Writing report data");
    var f_msg = reportLib.formatMessages(list, pluginHandler.containers);
    var issuesArray = (f_msg.errors.list.concat(f_msg.warnings.list)).concat(f_msg.infos.list);
    f_msg.steps = stepGen.generateMigrationSteps(context, issuesArray);
    f_msg.task = getTask(context);
    f_msg.project = context.project;
    f_msg.cmdline = context.cmdline;
    f_msg.isoTime = new Date().toISOString();
    f_msg["mig-tool-version"] = context["mig-tool-version"];

    async.series([
        function (callback) {
            getSystem(context, sourceDb, callback)
        }
    ], function(error, results){
        if(error){
            return callback(error);
        }
        f_msg.system = results[0];
        writeReportData(path.resolve(path.join(context.targetDir, context.config.directories["migration"])), JSON.stringify(f_msg, null,4), "data");
        var filelist = context.filePathIndex.toArray();
        writeReportData(path.resolve(path.join(context.targetDir, context.config.directories["migration"])), JSON.stringify(filelist,null,4), "filelist");
        writeReportData(path.resolve(path.join(context.targetDir, context.config.directories["migration"])), JSON.stringify(context.filePathIndex,null,4), "filedata");
        logUtil.info("Report url: " + path.join(context.targetDir, report.properties.rootFolder, "report.html"));

        return callback(null, f_msg);
    });

}


function writeReportData(npath, content, fname) {
    var fullPath = path.join(npath, report.properties.rootFolder, "report", "data");
    //readable file
    utils.writeFileSync(fullPath, fname + ".json", content);
    //html load file
    utils.writeFileSync(fullPath, fname + ".js", "var main_" + fname + " = " + content + ";");
}


function dumpUsedObjects(context) {
    var globalContextFilename = path.join(context.targetDir, context.config.directories['migration'], "globalContext.json");

    delete context.system.password;
    if(context.system['ddl-parse-system']) delete context.system['ddl-parse-system'].password;

    if(context.calculationViews) {
        for(let calcview of context.calculationViews.values()) {
            delete calcview._content;
        }
    }

    if(context['xs-app']) {
        for (let key in context['xs-app']) {
            let file = context['xs-app'][key];
            delete file.content;
        }
    }

    if(context.roles) {
        for (let roleName in context.roles) {
            let role = context.roles[roleName];
            delete role._content;
        }
    }


    fs.writeFileSync(globalContextFilename, JSON.stringify(context, null, 4));

    logUtil.info("Global Context written to: " + globalContextFilename);
}


function dumpContextObjects(context) {

    var contextObjectsFilename = path.join(context.targetDir, context.config.directories['migration'], "contextObjects.json");
    fs.writeFileSync(contextObjectsFilename, JSON.stringify(context.contextObjects.values(), null, 4));

}


function dumpProviderInformation(context) {


    for(let provider of context.synonymTargetProviders.providers) {
        let filename = path.join(context.targetDir, context.config.directories['migration'], provider.providerName + ".txt");

        let fileContent = 'Type: ' + provider.getTargetProviderType() + '\n' +
            'Subpath: ' + provider.synonymSubpath + '\n' +
            'Covered Objects: \n\n';

        for(let coveredObject of provider.coveredObjects) {
            fileContent += '- ' + coveredObject.schema_name + '.' + coveredObject.object_name + '\n';
            fileContent += '  Synonymname: ' + coveredObject.synonymName + '\n';
            fileContent += '  Users: \n';

            for(let username of coveredObject.userNames) {
                fileContent += '  - ' + username + '\n';
            }

            fileContent += '\n\n';
        }

        fs.writeFileSync(filename, fileContent);
    }

}


function dumpSourceFiles(context, lists, packages, callback) {
    var xs1src = path.join(context.targetDir, context.config.directories['xs1-src']);
    logUtil.info("Writing original application source to " + xs1src);

    async.eachSeries(lists,
        function (file, cb) {
            var sanitzedFilename = fileUtils.sanitizeFilename(file.RunLocation);
            var fp = path.join(xs1src, sanitzedFilename);
            var p = path.dirname(fp);
            var fn = path.basename(fp);
            utils.writeFile(p, fn, file.content, cb);
        },
        function (error) {
            if(error){
                logUtil.error('Error while writing files');
                return callback(error);
            } else {
                logUtil.info(lists.length + ' Files written');
                return callback(null, context, lists, packages);
            }
        }
    );
}


function showNonCoverableObjects(globalContext) {
    if(globalContext.nonCoverableObjects.size > 0) logUtil.info('\nThe following referenced objects are outside of the migration context and cannot be referenced by synonyms: \n');
    for(let externalObject of globalContext.nonCoverableObjects){
        let users = '[' + Array.from(externalObject.userNames).join(', ') + ']';
        logUtil.info('   - ' + externalObject.schema_name + '.' + externalObject.object_name + ' referenced by ' + users);
    }
    logUtil.info('\n');
}



function printTask(context, dus) {

    function getDUasText(dus) {
        var t = "";
        dus.forEach(function(d) {
            t += d.name + " (" + d.vendor + ")" + "\n";
        });
        return t
    }

    var msg = "Exporting and migrating delivery units:\n"
        + getDUasText(dus)
        + "from system: " + context.system.host + ":" + context.system.sqlport + "\n";
    logUtil.info(msg);
}

function defaultVersion(str, def) {
    if (str === undefined || str === "") {
        return def;
    } else {
        return str;
    }
}

function getDuVersion(du) {
    if(!du){
        return "0";
    }
    return defaultVersion(du.version, "0")
        + "." + defaultVersion(du.version_sp, "0")
        + "." + defaultVersion(du.version_patch, "0");
}



async function runMain() {

    var context;
    var selectedDUs;
    var actualDUs;
    var fileList;
    var hdbClient;
    var hdbExtClient;
    var sourceDb;
    var sqlParser;
    var hanaRepository;
    let neoTunnelSessionId;

    process.env.NODE_ENV = 'bas';

    vscode.window.withProgress({
        location: vscode.ProgressLocation.Notification,
        title: 'XS Migration',
        cancellable: true
    }, async (progress, token) => {
        return new Promise(async (resolve, reject) => {
            try {
                async.waterfall([
                        function(callback) {
                            logUtil.info('Starting XS Migration Assistant Version ' + packageJson.version);
                            logUtil.info('Reading input');

                            progress.report({ message: 'Reading input' });

                            const readInput = inputParser.readConsoleInput();

                            context = readInput.context;
                            selectedDUs = readInput.deliveryUnits;

                            return callback();
                        },
                        function(callback) {
                            if (NeoTunnelUtils.isTunnelRequired(context.system)) {
                                logUtil.info('Initializing the NEO DB Tunnel...');

                                progress.report({ message: 'Initializing  NEO DB Tunnel...' });

                                const tunnelInfo = NeoTunnelUtils.openTunnel(context.system);

                                if (tunnelInfo && tunnelInfo.errorMsg) {
                                    throw new Error(`Error during establish the NEO DB Tunnel connection: ${tunnelInfo.errorMsg}`);
                                }

                                const { host: dbHost, port: dbPort, tunnelId } = tunnelInfo;

                                neoTunnelSessionId = tunnelId;

                                if (!neoTunnelSessionId || !neoTunnelSessionId.length) {
                                    throw new Error('Error during establish the NEO DB Tunnel connection, please check the credentials');
                                }

                                logUtil.info(`The NEO DB Tunnel has been opened with session id ${neoTunnelSessionId}...`)

                                context.system.host = dbHost;
                                context.system.sqlport = dbPort;
                            }

                            return callback();
                        },
                        function(callback){
                            logUtil.info('Initializing database connection to source database...');

                            progress.report({ message: 'Initializing database connection to source database...' });

                            hdbClient = hdbClientHelper.prepareAndConnect(context.system, callback);
                        },
                        function(callback){
                            logUtil.info('Preparing SQL parser...');
                            progress.report({ message: 'Preparing SQL parser...' });

                            if(context.hasOwnProperty("ddl-parse-system")){
                                logUtil.info('External parse system specified');
                                // vscode.window.showInformationMessage('External parse system specified');
                                logUtil.debug('External parse system found, initialize second node client');
                                // vscode.window.showInformationMessage('External parse system found, initialize second node client');
                                hdbExtClient = hdbClientHelper.prepareAndConnect(context["ddl-parse-system"], callback);
                            } else {
                                logUtil.debug('No external parse system found, using default client');
                                // vscode.window.showInformationMessage('No external parse system found, using default client');
                                hdbExtClient = hdbClient;
                                callback();
                            }
                        },
                        function(callback){
                            logUtil.info('SQL parser prepared');
                            // vscode.window.showInformationMessage('SQL parser prepared');

                            progress.report({ message: 'Initializing sourceDb' });
                            logUtil.debug('Initializing sourceDb');
                            sourceDb = new SourceDb(hdbClient);

                            progress.report({ message: 'Initializing sqlParser' });
                            logUtil.debug('Initializing sqlParser');
                            sqlParser = new SqlParser(hdbExtClient);

                            callback();
                        },
                        function (callback) {
                            logUtil.debug('Initializing hanaRepository');
                            progress.report({ message: 'Initializing hanaRepository' });
                            hanaRepository = new HanaRepository(hdbClient);

                            callback();
                        },
                        function (callback) {
                            if(selectedDUs && selectedDUs.length > 0) {
                                logUtil.info('Verifying specified Delivery Units...');
                                progress.report({ message: 'Verifying specified Delivery Units...' });
                                hanaRepository.getDeliveryUnits(selectedDUs, callback);
                            } else {
                                callback(null, []);
                            }
                        },
                        function (result, callback) {
                            actualDUs = result;

                            if(result.length > 0){
                                logUtil.info('Delivery Units verified');
                                //vscode.window.showInformationMessage('Delivery Units verified');

                                printTask(context, actualDUs);
                            }

                            context.task = {deliveryUnits: actualDUs};


                            // if (context.project.name === "") {
                            //     context.project.name = actualDUs[0].name;
                            // }

                            if (context.project.version === "") {
                                context.project.version = getDuVersion(actualDUs[0]);
                            }

                            if (context.project.vendor === "" && actualDUs.length > 0) {
                                context.project.vendor = actualDUs[0].vendor;
                            } else {
                                context.project.vendor = "No-vendor";
                            }

                            if (context.project.description === "" && actualDUs.length > 0) {
                                context.project.description = actualDUs[0].caption;
                            }

                            callback();
                        },
                        function (callback) {
                            logUtil.info('Retrieving Public Synonyms...');
                            progress.report({ message: 'Retrieving Public Synonyms...' });
                            sourceDb.getPublicSynonyms(context, callback);
                        },
                        function (callback) {
                            logUtil.info('Checking target directory...');
                            progress.report({ message: 'Checking target directory...' });
                            if (fs.existsSync(context.targetDir)) {
                                callback("Target directory \"" + context.targetDir + "\" does exist.");
                            } else {
                                logUtil.info('Target directory ok, generating project structure...');
                                progress.report({ message: 'Target directory ok, generating project structure...' });
                                projectCreator.createProjectStructure(context, function (err) {
                                    if (err) {
                                        logUtil.error('Failed to create project structure');
                                        callback(err);
                                    } else {
                                        callback(null, context, actualDUs);
                                    }
                                });
                            }
                        },
                        function(context, deliveryUnits, callback){
                            logUtil.info('Collecting files of Delivery Units...');
                            progress.report({ message: 'Collecting files of Delivery Units...' });
                            hanaRepository.getAllFilesForDus(context, deliveryUnits, callback);
                        },
                        dumpSourceFiles,
                        function(context, list, packages, callback){
                            context.task.packages = packages;
                            callback(null, context, list, packages, sourceDb, sqlParser);
                        },
                        preprocessing,
                        processing,
                        postprocessing,
                        function (context, list, packages, callback) {
                            fileList = list;
                            callback();
                        },
                        function (callback) {
                            // generate and save mta descriptor
                            mtaGen.generateMtaDesignDescriptor(context, callback);
                        },
                        function (callback) {
                            // make sure this is the very last thing we do
                            writeReport(context, fileList, sourceDb, callback);
                        },
                        function(fmsg, callback) {
                            if(context.zip_result) {
                                ZipArchiver.generateZip(context, callback);
                            } else {
                                return callback();
                            }
                        }
                    ], function (error) {
                        if (error) {
                            let errorMessage;

                            if(error.message || error instanceof Array) {
                                errorMessage = ErrorMessages.getErrorMessage(error);
                            } else {
                                errorMessage = error;
                            }

                            console.error("\nERROR OCCURED!");
                            console.error(errorMessage);
                            if(error.filename) {
                                console.error('In ' + error.filename);
                            }
                            vscode.window.showErrorMessage(errorMessage);
                            return reject(error);
                        }

                        logUtil.info("Migration finished.");
                        vscode.window.showInformationMessage('Migration finished');
                        progress.report({ message: 'Migration almost done' });

                        showNonCoverableObjects(context);

                        dumpProviderInformation(context);
                        dumpContextObjects(context);
                        dumpUsedObjects(context);

                        MigrationUtils.createTableRenameScript(context);

                        logUtil.info('Check if the NEO Tunnel connection is established and available for closing');

                        if (NeoTunnelUtils.isTunnelRequired(context.system) && neoTunnelSessionId) {
                            //vscode.window.showInformationMessage(`Closing the NEO DB Tunnel with session id ${neoTunnelSessionId} ...`);
                            logUtil.info(`Closing the NEO DB Tunnel with session id ${neoTunnelSessionId} ...`);
                            NeoTunnelUtils.closeTunnel(neoTunnelSessionId);
                            //vscode.window.showInformationMessage(`The NEO DB Tunnel with session id ${neoTunnelSessionId} is closed...`);
                            logUtil.info(`The NEO DB Tunnel with session id ${neoTunnelSessionId} is closed...`);
                        }

                        progress.report({ message: 'Migration completed' });

                        resolve();
                    }
                );
            } catch (e) {
                console.error("\nError:\n" + e.message);
                if(e instanceof ConfigValidationError) {
                    console.error('File: ' + e.configFile);
                } else {
                    console.error(inputParser.USAGE);
                }
                vscode.window.showErrorMessage(e.message);
                reject(e);
            }
        });
    });
}

module.exports.runMain = async function () {
    await runMain();
}