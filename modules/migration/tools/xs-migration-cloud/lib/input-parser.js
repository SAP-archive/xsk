// --------------------------------------------------------------------------
//
// Read, parse, and validate all input for migration tool
//
// --------------------------------------------------------------------------

var minimist = require('minimist');
var fs = require('fs');
var path = require('path');
var mkdirp = require('mkdirp');

var logUtil = require('./log-util');
var utils = require('./generator-utils');
var defaultConfig = require('./default-configuration');
var db = require('./db-generator');
var xs = require('./xsjs-generator');
var web = require('./web-generator');
var todo = require('./todo-generator');
var pkgjson = require('../package');
var SynonymTargetProvider = require('./synonym-target-provider');
var SynonymTargetProviders = require('./synonym-target-providers');
var TargetProviderType = require('./target-provider-type');
var SynonymTargetProviderValidator = require('./synonym-target-provider-validator');
var ConfigValidationError = require('./config-validation-error');
var ErrorMessages = require('./error-messages');
var FilePathIndex = require('./file-path-index');
var ObjectSet = require('./object-set');
var basPreChecker = require('./bas/pre-checker');
const JOIN_ORDER_TYPE = require('./join-order-type');

const targetReleaseHANACloud = 'HANACloud';

const validTargetReleases = new Map([
    ['2.0SP00', {name: '2.0SP00', id: 20}],
    ['2.0SP01', {name: '2.0SP01', id: 21}],
    ['2.0SP02', {name: '2.0SP02', id: 22}],
    ['2.0SP03', {name: '2.0SP03', id: 23}],
    [targetReleaseHANACloud, {name: targetReleaseHANACloud, id: 24}],
]);

const defaultTargetRelease = validTargetReleases.get(targetReleaseHANACloud);
var readlineSync = require('readline-sync');

const MINIMUM_STAGED_MIGRATION_RELEASE = 21;

// utility functions
//
function reportError() {
    console.error('\nError: ');
    console.error.apply(null, arguments);
    if (isBasEnvironment()) {
        require('vscode').window.showErrorMessage(`Error: ${Array.isArray(arguments) ? arguments.join(',') : arguments}`);
    }
    process.exit(1);
}

function isEmpty(obj) {
    return Object.keys(obj).length === 0;
}

function get(obj, list, defaultValue) {
    for (var i=0; i < list.length; i++) {
        if (list[i] in obj) {
            return obj[list[i]];
        }
    }
    return defaultValue;
}

// --------------------------------------------------------------------------
// usage
//
var USAGE = "Usage: xs-migration [OPTION]... DU_Name[,DU_Vendor]\n"
          + "Migrate a XSC Delivery Unit identified by its Name (and Vendor) to a XSA-compatible MTA-project."
          + "\n\n"
          + "Options:\n\n"
          + "  --name                                   project name (default: name of first DU or package)\n\n"

          + "  --version                                project version (default: version of first DU)\n\n"

          + "  --description                            project description (default: description of first DU)\n\n"

          + "  --target-dir <directory>                 directory where project is created - directory must not exist\n\n"

          + "  --packages <pkg[,...]>                   packages to include in migration\n"
          + "                                           [pkg] = package-name[:include-subpackages]\n"
          + "                                           include-subpackages = true | false (default=true)\n"
          + "                                           example: com.sap.db:true\n\n"

          + "  --objects <object-list>                  path to a list with objects to migrate (json file)\n\n"

          + "  --exclude-packages <pkg[,...]>           packages to exclude from migration\n"
          + "                                           [pkg] = package-name[:exclude-subpackages]\n"
          + "                                           exclude-subpackages = true | false (default=false)\n"
          + "                                           example: com.sap.db:true\n\n"

          + " --exclude-object-types <type[,...]>       one or more object types that should be excluded from the migration (komma-separated list)\n\n"
          + " --include-object-types <type[,...]>       one or more object types that should be included from the migration (komma-separated list)\n\n"

          + "  --overwrite-root-namespace <namespace>   overwrite the calculated root namespace for the database module\n"
          + "                                           only possible if the configured namespace is a shortened variant of the computed variant\n"
          + "                                           example: calculated: com.test.element, valid overwrite: com.test, invalid: com2.test\n\n"

          + "  --generate-manifests                     generate a manifest.yml and manifest-op.yml file\n\n"

          + "  --zip                                    add the migration result to a zip folder for import into SAP Web IDE\n\n"

          + "  --synonym-target-provider                location of synonym-target-provider file\n"
          + "                                           needs target-release to be >= 2.0SP01\n\n"

          + "  --activate-public-access <type>          specify what public-access-role should be active, valid values for\n"
          + "                                           <type> are 'objects' and 'schema'\n\n"

          + "  --generate-providers                     generate a synonym-target-provider configuration for every found schema \n"
          + "                                           (see staged migration documentation for details)\n"
          + "                                           needs target-release to be >= 2.0SP01\n\n"

          + "  --generated-provider-type <type>         specify the type of the generated providers \n"
          + "                                           valid values for type are GRANTOR_SERVICE and LOGICAL_SCHEMA\n"
          + "                                           default is GRANTOR_SERVICE\n"
          + "                                           works only in combination with --generate-providers\n\n"

          + "  --hta                                    enable hta-mode\n"
          + "                                           this mode produces a hta-compatible output without hdbgrants files and public-access roles\n\n"

          + "  --generate-local-slash-synonyms          enable the generation of local slash-synonyms for all calculationviews in the context\n"
          + "                                           needs target-release to be >= 2.0SP02\n\n"

          + "  --integrated-synonymtargets              integrate the synonym target configuration in hdbsynonym files rather than separate \n"
          + "                                           hdbsynonymconfig files in the cfg directory. Works only for providers of type LOGICAL_SCHEMA\n\n"


          + "  --target-release <release>               specify the version of the target hana system\n"
          + "                                           valid entries are 2.0SP00, 2.0SP01, 2.0SP02 and 2.0SP03 - default is 2.0SP03\n\n"

          + "One View Migration options \n\n"

          + "  --join-order-type <type>                 specify the join order type\n"
          + "                                           valid values for type are insideOut and outsideIn\n\n"

          + "  --unhide-hidden-columns                    unhide hidden view columns\n\n"



          + "\n\n"
          + "The database connection needs to be specified with the following environment variables\n"
          + "\n"
          + "  HANA_HOST         Hana server host name\n"
          + "  HANA_SQL_PORT     SQL Port\n"
          + "  HANA_USER         User name\n"
          + "  HANA_PASSWD       Password\n"
          + "  HANA_CERTIFICATE  certificate file when using ssl encryption (X509 PEM)\n"
          + "\n\n"
          + "If the source database does not support the procedure SYS.GET_OBJECTS_IN_DDL_STATEMENT, \n"
          + "an external parse system can be specified with the following environment variables\n"
          + "\n\n"
          + "  HANAEXT_HOST         Hana server host name\n"
          + "  HANAEXT_SQL_PORT     SQL Port\n"
          + "  HANAEXT_USER         User name\n"
          + "  HANAEXT_PASSWD       Password\n"
          + "  HANAEXT_CERTIFICATE  certificate file when using ssl encryption (X509 PEM)\n"
          + "\n\n"
          + "Version " + pkgjson.version;



// --------------------------------------------------------------------------
// Command Line Options
//

var OPTIONS = [
    "name",
    "version",
    "description",
    "target-dir",
    "exclude-packages",
    "packages",
    "objects",
    "synonym-target-provider",
    "activate-public-access",
    "target-release",
    "join-order-type",
    "generated-provider-type",
    "overwrite-root-namespace",
    "exclude-object-types",
    "include-object-types"
];

var BOOLEAN_OPTIONS = [
    "h",
    "help",
    "generate-manifests",
    "zip",
    "hta",
    "generate-providers",
    "unhide-hidden-columns",
    "generate-local-slash-synonyms",
    "integrated-synonymtargets"
];

var MINIMIST_OPTIONS = {
    "string": OPTIONS,
    "boolean": BOOLEAN_OPTIONS,
    unknown: function(arg) {
        if (arg.indexOf('-') === 0) {
            reportError("Error: unknown option %s", arg);
        }
    }
};

function displayUsageInfo() {
    console.log(USAGE);
}

function displayUsageInfoAndExit() {
    displayUsageInfo();
    process.exit(1);
}

function isHelpArgumentExists(argv) {
    return argv['h'] || argv['help'];
}

function validateCommandLineArguments(argv) {
    if (argv._.length === 0 && !argv['packages'] && !argv['objects']) {
        reportError("Error: no Delivery Unit, Package List or Objects List specified");
    }
    if (argv._.length > 0 && argv['packages']) {
        reportError("Error: Specify either a Delivery Unit OR a Package List");
    }
}

function isBasEnvironment() {
    return process.env.NODE_ENV && process.env.NODE_ENV === 'bas';
}

function getBasCommandLineArguments() {
    return {
        _: [process.env['DELIVERY_UNIT']],
        'generate-manifests': true
    };
}

function getRegularCommandLineArguments() {
    if (process.argv.slice(2).length === 0) {
        console.log(USAGE);
        process.exit(1);
    }

    return minimist(process.argv.slice(2), MINIMIST_OPTIONS);
}

function getCommandLineArguments() {
    let argv;

    if (isBasEnvironment()) {
        argv = getBasCommandLineArguments();
    } else {
        argv = getRegularCommandLineArguments();
    }

    if (isHelpArgumentExists(argv)) {
        displayUsageInfoAndExit();
    }

    validateCommandLineArguments(argv);

    return argv;
}

// --------------------------------------------------------------------------
// Read hdbalm or regi environment
//

function getEnv(candidates, defaultValue) {
    for (let i=0; i < candidates.length; i++) {
        let c = candidates[i];

        if (c in process.env) {
            return process.env[c].trim();
        }
    }

    return defaultValue;
}


function neoTunnelIsActive(system) {
    return system.neo.isActive !== undefined && system.neo.isActive === 'true';
}

function neoTunnelIsAlreadyActive(system) {
    return system.neo.isAlreadyActive !== undefined && system.neo.isAlreadyActive === 'true';
}

function injectNeoEnvironment(system) {
    system.neo = {};

    const neoTunnelIsActiveEnvValue = getEnv(['NEO_TUNNEL_IS_ACTIVE']);
    system.neo.isActive = neoTunnelIsActiveEnvValue !== undefined ? neoTunnelIsActiveEnvValue : false;

    const neoTunnelIsAlreadyActiveEnvValue = getEnv(['NEO_TUNNEL_IS_ALREADY_ACTIVE']);
    system.neo.isAlreadyActive = neoTunnelIsAlreadyActiveEnvValue !== undefined ? neoTunnelIsAlreadyActiveEnvValue : false;

    if (neoTunnelIsActive(system)) {
        system.neo.account = getEnv(['NEO_TUNNEL_ACCOUNT']);
        system.neo.host = getEnv(['NEO_TUNNEL_HOST']);
        system.neo.user = getEnv(['NEO_TUNNEL_USER']);
        system.neo.password = getEnv(['NEO_TUNNEL_PASSWD']);
        system.neo.db = getEnv(['NEO_TUNNEL_DB_NAME']);
    }
}

function getEnvironmentExtraSystem() {

    var system = {};
    system.user = getEnv(['HANAEXT_USER']);
    if(system.user === undefined){
        delete system.user;
    }
    system.password = getEnv(['HANAEXT_PASSWD']);
    if(system.password === undefined){
        delete system.password;
    }
    system.host = getEnv(['HANAEXT_HOST']);
    if(system.host === undefined){
        delete system.host;
    }
    system.sqlport = getEnv(['HANAEXT_SQL_PORT']);
    if(system.sqlport === undefined){
        delete system.sqlport;
    }

    if (isEmpty(system)) {
        return {};
    } else {
        injectNeoEnvironment(system);
        system.certificate = getEnv(['HANAEXT_CERTIFICATE']);
        return system;
    }
}

function getSystemFromEnvironment() {

    const system = {};

    injectNeoEnvironment(system);

    system.user = getEnv(['HANA_USER']);
    system.password = getEnv(['HANA_PASSWD']);

    if (!neoTunnelIsActive(system) && !neoTunnelIsAlreadyActive(system)) {
        system.host = getEnv(['HANA_HOST']);
        system.sqlport = getEnv(['HANA_SQL_PORT']);
        system.certificate = getEnv(['HANA_CERTIFICATE']);
    }

    return system;
}

function validateSystemWithNeoTunnel(system, sysName) {
    if (!system.neo) {
        reportError("NEO_TUNNEL parameters are not specified", sysName);
    }
    if (!system.neo.account) {
        reportError("NEO_TUNNEL_ACCOUNT is not set in environment", sysName);
    }
    if (!system.neo.user) {
        reportError("NEO_TUNNEL_USER is not set in environment", sysName);
    }
    if (!system.neo.db) {
        reportError("NEO_TUNNEL_DB_NAME is not set in environment", sysName);
    }
    if (!system.neo.password) {
        if (isBasEnvironment()) {
            reportError("NEO_TUNNEL_PASSWD is not set in environment", sysName);
        } else {
            let passwordInput = passwordPrompt(system.neo.user);

            if (!passwordInput || passwordInput.length < 1) {
                reportError("Error: NEO_TUNNEL%s_PASSWD not set in environment", sysName);
            }

            system.neo.password = passwordInput;
        }
    }
}

function validateSystemWithoutNeoTunnel(system, sysName) {
    if (!system.host) {
        reportError("Error: HANA%s_HOST not set in environment", sysName);
    }
    if (!system.sqlport) {
        reportError("Error: HANA%s_SQL_PORT not set in environment", sysName);
    }
    if (isNaN(system.sqlport)) {
        reportError("Error: HANA%s_SQL_PORT is not a number", system.sqlport, sysName);
    }
}

function validateSystem(system, sysName) {
    if (!system.user) {
        reportError("Error: HANA%s_USER not set in environment", sysName);
    }
    if (!system.password) {
        if (isBasEnvironment()) {
            reportError("Error: HANA%s_PASSWD not set in environment", sysName);
        } else {
            let passwordInput = passwordPrompt(system.user);

            if (!passwordInput || passwordInput.length < 1) {
                reportError("Error: HANA%s_PASSWD not set in environment", sysName);
            }

            system.password = passwordInput;
        }
    }
    if (neoTunnelIsActive(system)) {
        validateSystemWithNeoTunnel(system, sysName);
    } else if (!neoTunnelIsAlreadyActive(system)) {
        validateSystemWithoutNeoTunnel(system, sysName);
    }
}


function passwordPrompt(username){
    let question = 'Please enter password for ' + username + ':\n';

    return readlineSync.question(question, {hideEchoBack: true});
}


function readCertificate(system, sysName) {

    if (system.certificate !== undefined && system.certificate !== '') {
        try {
            system.certificateContent = fs.readFileSync(system.certificate);
        } catch (e) {
            reportError("Error: cannot load certificate file %s for the %s: %s", system.certificate, sysName, e.message);
        }
    }
}

function getSystem() {

    const system = getSystemFromEnvironment();

    validateSystem(system, "");
    readCertificate(system, "SAP HANA system");

    return system;
}

function getCmdLine(argv) {
    var str = "";
    var first = true;
    for(var k in argv) {
        if (k !== '_') {
            if (!first) {
                str += " ";
            }
            first = false;
            str += "--" + k;
            if (argv[k] != "true") {
                str += ' "' + argv[k] + '"';
            }
        }
    }
    return str;
}

function generateProjectName(packageName){
    var projectName = packageName.replace(/\./g, "_");
    return projectName;
}

function getContext(argv, systemFromEnv, deliveryUnits) {

    var migrationDir = defaultConfig.directories["migration"];

    var packages;

    if('packages' in argv) {
        packages = parsePackageList(argv['packages'], true);
    }

    var projectName = get(argv, ["name"], "");

    if (!projectName) {
        if (deliveryUnits.length > 0) {
            projectName = deliveryUnits[0].name;
        } else if (packages && packages.length > 0) {
            projectName = generateProjectName(packages[0].package);
        } else {
            projectName = 'migration_project';
        }
    }

    let workspaceFolder;

    if (isBasEnvironment()) {
        const workspacesFolders = require('vscode').workspace.workspaceFolders;

        basPreChecker.basEnvWorkspaceFoldersPreCheck(workspacesFolders);

        workspaceFolder = workspacesFolders[0];
    }

    var targetDir = get(argv, ["targetDir", "target-dir"]);

    if (!targetDir) {
        logUtil.info('No target directory specified, generating one');
        mkdirp.sync('results');

        targetDir = utils.generateTargetDirectory(projectName);
    }

    if (isBasEnvironment()) {
        targetDir = path.join(workspaceFolder.uri.path, targetDir);
    }

    logUtil.info('Using target directory: ' + targetDir);


    var context = {
        cmdline: getCmdLine(argv),
        system: systemFromEnv,
        config: defaultConfig,
        targetDir: targetDir,
        project: {
            name: projectName,
            vendor: "",
            version: get(argv, ["version"], ""),
            description: get(argv, ["description"], "")
        },
        pathMap: {
                xsjs : xs.properties.rootFolder,
                'xsjs-name': xs.properties.folderName,
                db : db.properties.rootFolder,
                'db-name': db.properties.folderName,
                web : web.properties.rootFolder,
                'web-name': web.properties.folderName,
                todo: todo.properties.rootFolder,
                'todo-name': todo.properties.folderName,
                "db-tmp": db.properties.viewTmpFolder,
                "xs2-app-root": targetDir,

                // full path to container, e.g. "c:\...\db"
                "xsjs-full-path": path.join(targetDir, xs.properties.folderName),
                // path relative from project to container contents
                "xsjs-relative-path": path.join(defaultConfig.directories["xs2-app"], xs.properties.rootFolder),
                // full path to dev object, e.g. "c:\...\db\src"
                "xsjs-full-path-dev-objects": path.join(targetDir, xs.properties.rootFolder),
                // relative path in container to dev objects, e.g. "src"
                "xsjs-dev-object-folder": xs.properties.devObjectFolder,
                // relativ to report root with container root dir
                "xsjs-relative-container-root": path.join(defaultConfig.directories["xs2-app"], xs.properties.folderName),
                "db-full-path": path.join(targetDir, db.properties.folderName),
                "db-relative-path": path.join(defaultConfig.directories["xs2-app"], db.properties.rootFolder),
                "db-full-path-dev-objects": path.join(targetDir, db.properties.rootFolder),
                "db-full-path-cfg-objects": path.join(targetDir, db.properties.cfgFolder),
                "db-dev-object-folder": db.properties.devObjectFolder,
                "db-relative-container-root": path.join(defaultConfig.directories["xs2-app"], db.properties.folderName),
                "web-full-path": path.join(targetDir, web.properties.folderName),
                "web-relative-path": path.join(defaultConfig.directories["xs2-app"], web.properties.rootFolder),
                "web-full-path-dev-objects": path.join(targetDir, web.properties.rootFolder),
                "web-dev-object-folder": web.properties.devObjectFolder,
                "web-relative-container-root": path.join(defaultConfig.directories["xs2-app"], web.properties.folderName),
                "todo-root-path": path.join(targetDir, migrationDir),
                "report-root-path": path.join(targetDir, migrationDir),
                "todo-full-path": path.join(targetDir, migrationDir, todo.properties.folderName),
                "todo-relative-path": path.join(migrationDir, todo.properties.rootFolder),
                "todo-full-path-dev-objects": path.join(targetDir, migrationDir, todo.properties.rootFolder),
                "todo-dev-object-folder": todo.properties.devObjectFolder,
                "todo-relative-container-root": path.join(migrationDir, todo.properties.folderName)
            },
        used_objects: [],
        object_mapping: {},
        applicationSchemas: [],
        generate_manifests: argv['generate-manifests'],
        "xs-app": {},
        "mig-tool-version": pkgjson.version,
        containersPresent: [],
        zip_result: argv['zip'],
        analyticPrivileges: [],
        calculationViews: new Map(),
        textbundleReferences: [],
        textbundleFiles: [],
        rootHdiNamespace: null,
        contextObjects: new ObjectSet(),
        nonCoverableObjects: new ObjectSet(),
        htaMode: argv['hta'],
        generateSlashSynonyms: argv['generate-local-slash-synonyms'],
        filePathIndex: new FilePathIndex(),
        localizedObjects: new ObjectSet(),
        referencedCsvFiles: { withHeader: [] },
        sysRepoObjects: new Set(),
        nonCoverableCalcviews: new Set(),
        oneViewMigrationArtifacts: [],
        tableObjects: [],
        handledObjectNames: [],
        excludedObjectTypes: [],
        includedObjectTypes: [],
        securityFileExists: false,
        noSynonymConfigs: argv['integrated-synonymtargets'],
        procedures: [],
        objects: []
    };

    context.schemaPrivileges = new Set();

    context.packages = packages;

    var psys = getEnvironmentExtraSystem();
    if (!isEmpty(psys)) {
        validateSystem(psys, "EXT");
        readCertificate(psys, "SAP HANA system for extra migration tasks");
        context["ddl-parse-system"] = psys;
        logUtil.info("Using different system for SQL CALL GET_OBJECTS_IN_DDL_STATEMENT: " + context["ddl-parse-system"].host);
    }

    if ('exclude-packages' in argv) {
        context['exclude-packages'] = parsePackageList(argv['exclude-packages'], false);
    }

    context.targetRelease = defaultTargetRelease;

    if(argv['target-release']) {
        let targetReleaseString = argv['target-release'];
        if(validTargetReleases.has(targetReleaseString)){
            context.targetRelease = validTargetReleases.get(targetReleaseString);
        } else {
            reportError('"%s" is not a valid target release', targetReleaseString);
        }
    }

    if(argv['generate-providers']) {
        if(context.targetRelease.id < MINIMUM_STAGED_MIGRATION_RELEASE) {
            reportError("The specified target release version does not support generated providers");
        }
        if(argv['generated-provider-type'] && argv['generated-provider-type'].length > 0) {
            let providerType = TargetProviderType.get(argv['generated-provider-type']);
            if(!providerType) {
                let types = [];
                TargetProviderType.enums.forEach((element) => {types.push(element)});
                reportError("The specified type for --generated-provider-type is invalid, please use one of the following values: [" + types.join() + ']');
            } else {
                context.synonymTargetProviders = new SynonymTargetProviders(true, providerType);
            }
        } else {
            context.synonymTargetProviders = new SynonymTargetProviders(true);
        }
    } else {
        context.synonymTargetProviders = new SynonymTargetProviders();
    }

    if(argv['synonym-target-provider']) {
        if(context.targetRelease.id < MINIMUM_STAGED_MIGRATION_RELEASE) {
            reportError("The specified target release version does not support synonym target providers");
        }
        var synonymTargetProviders = JSON.parse(fs.readFileSync(argv['synonym-target-provider']));
        var validationErrors = [];

        for (let synonymTargetProvider of synonymTargetProviders) {
            let validationResult = SynonymTargetProviderValidator.validate(synonymTargetProvider);
            if(validationResult.valid) {
                context.synonymTargetProviders.addProvider(new SynonymTargetProvider(synonymTargetProvider));
            }
            else {
                validationResult.errors.forEach(function (error) {
                    validationErrors.push('- ' + ErrorMessages.getErrorMessage(error));
                });console.log(validationErrors)
            }
        }
        if(validationErrors.length > 0){
            throw new ConfigValidationError(validationErrors.join('\n'), argv['synonym-target-provider']);
        }
    }


    if(argv['activate-public-access']) {
        if(['objects', 'schema'].includes(argv['activate-public-access'])) {
            context.activatePublicAccess = argv['activate-public-access'].toUpperCase();
        } else {
            throw new Error("only 'objects' or 'schema' is allowed as parameter for activate-public-access\n");
        }
    }

    let joinOrderType = null;

    if(argv['join-order-type'] && JOIN_ORDER_TYPE.get(argv['join-order-type'])) {
        joinOrderType = JOIN_ORDER_TYPE.get(argv['join-order-type']);
    }

    context.joinOrderType = joinOrderType;
    context.unhideHiddenColumns = false;

    if(argv['unhide-hidden-columns']) {
        context.unhideHiddenColumns = true;
    }

    if(argv['overwrite-root-namespace']) {
        context.overwriteRootNamespace = argv['overwrite-root-namespace'];
    }

    if(argv['exclude-object-types']) {
        context.excludedObjectTypes = parseList(argv['exclude-object-types']);
    }

    if(argv['include-object-types']) {
        context.includedObjectTypes = parseList(argv['include-object-types']);
    }

    if(argv['objects']) {
        const objectsFile = fs.readFileSync(argv['objects'], 'utf-8');
        const objects = JSON.parse(objectsFile);
        if(objects && objects.length > 0) {
            context.objects = objects;
        }
    }


    return context;
}


function parseList(list) {
    list = list.replace(/\s/g, '');
    let splitted = list.split(',');

    return splitted;
}


function parsePackageList(packageList, subPathDefault) {
    var excludes = [];
    var pkgs = packageList.split(',');
    pkgs.forEach(function (pkg) {
        var pkg_sub = pkg.split(":");
        var subPackages = subPathDefault;
        if(pkg_sub.length == 2){
            subPackages = pkg_sub[1];
        }
        excludes.push({
            "package": pkg_sub[0],
            "subpackages": subPackages
        });
    });
    return excludes;
}


function splitDUString(argv){

    var candidateDusString = argv._;
    var candidateDus = [];

    candidateDusString.forEach(function (du) {
        var t = du.split(",");
        candidateDus.push({
            name: t[0],
            vendor: t[1]
        });
    });

    return candidateDus;
}


// --------------------------------------------------------------------------
// main section

function InputParser() {}

InputParser.prototype.readInput = function () {

    const argv = getCommandLineArguments();
    const system = getSystem();
    const deliveryUnits = splitDUString(argv);
    const context = getContext(argv, system, deliveryUnits);

    return {
        context,
        deliveryUnits
    };
};

InputParser.prototype.readConsoleInput = function () {

    const argv = getCommandLineArguments();
    const system = {};
    system.host = argv._[1];
    system.sqlport = argv._[2];
    system.user = argv._[3];
    system.password = argv._[4];

    const deliveryUnits = [];
    const deliveryUnit = argv._[0].split(",");
    deliveryUnits.push({
        name: deliveryUnit[0],
        vendor: deliveryUnit[1]
    });
    
    const context = getContext(argv, system, deliveryUnits);

    return {
        context,
        deliveryUnits
    };
};

InputParser.prototype.USAGE = USAGE;

module.exports = new InputParser();
