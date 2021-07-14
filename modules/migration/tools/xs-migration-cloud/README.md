XSC to XSA Migration
============

Tool for migration of XS Classic application to XS Advanced.
 
## Get it
You can either use a milestone version or take the risk with the latest and greatest development state.

### Downloading the latest milestone
Just click on the link below, then unpack.
* [Linux](http://nexus.wdf.sap.corp:8081/nexus/service/local/artifact/maven/redirect?r=build.releases.xmake&g=com.sap.xs.migration&a=xs-migration.linuxx86_64&e=zip&v=LATEST)
* [Windows](http://nexus.wdf.sap.corp:8081/nexus/service/local/artifact/maven/redirect?r=build.releases.xmake&g=com.sap.xs.migration&a=xs-migration.ntamd64&e=zip&v=LATEST)

### Done!
Note that executing the development version slighlty differs (see below).

### NEO Tunnel Credentials

To be able to open the NEO Tunnel for further DB connection through it, you need to specify some credentials in the `.env` file.

*Note: To tell the script to use the NEO Tunneling, the `NEO_TUNNEL_IS_ACTIVE` must be set to `true` (it's `false` by default even if it's not specified in the `.env` file).*

Below is the list with the credentials which required to be in the `.env` file and how to find them properly:

`NEO_TUNNEL_ACCOUNT` - the technical name of the sub-account (usually contains lower-case letters and can contain numbers) where the needed DB is located

`NEO_TUNNEL_HOST` - the host of the landscape (for ex. eu2.hana.ondemand.com)

`NEO_TUNNEL_USER` - the user (C/D/I-number)

`NEO_TUNNEL_PASSWD` - the password of the user (make sure to escape any `$` symbols in the password - e.g. `pas$word` should become `pas\$word`)

`NEO_TUNNEL_DB_NAME` - the name of the DB to which the tunnel connection must be established

### NOTE: After migration is completed and artifacts are generated

The version of @sap/hdi-deploy dependency in the dependencies section of the package.json (file location is `results/<result-folder>/db/package.json`) file must be changed to ^3 like

```
  ...
  "dependencies": {
    "@sap/hdi-deploy": "^3"
  },
  ...
```


## Use it  
### (Windows only!) Installing the required Microsoft C++ runtime  
The migration assistant makes use of the SAP JVM so that the installation of the right C++ library is required on Windows.
Install the [x64] (http://www.microsoft.com/download/details.aspx?id=14632) variant. See also https://service.sap.com/sap/support/notes/1837221

### Running the program    
Change to the unzipped directory and run the start script to display the usage instructions.  

```
Usage: xs-migration [OPTION]... DU_Name[,DU_Vendor]
Migrate a XSC Delivery Unit identified by its Name (and Vendor) to a XSA-compatible MTA-project.

Options:

  --name                                   project name (default: name of first DU or package)

  --version                                project version (default: version of first DU)

  --description                            project description (default: description of first DU)

  --target-dir <directory>                 directory where project is created - directory must not exist

  --packages <pkg[,...]>                   packages to include in migration
                                           [pkg] = package-name[:include-subpackages]
                                           include-subpackages = true | false (default=true)
                                           example: com.sap.db:true

  --exclude-packages <pkg[,...]>           packages to exclude from migration
                                           [pkg] = package-name[:exclude-subpackages]
                                           exclude-subpackages = true | false (default=false)
                                           example: com.sap.db:true

 --exclude-object-types <type[,...]>       one or more object types that should be excluded from the migration (komma-separated list)

 --include-object-types <type[,...]>       one or more object types that should be included from the migration (komma-separated list)

  --overwrite-root-namespace <namespace>   overwrite the calculated root namespace for the database module
                                           only possible if the configured namespace is a shortened variant of the computed variant
                                           example: calculated: com.test.element, valid overwrite: com.test, invalid: com2.test

  --generate-manifests                     generate a manifest.yml and manifest-op.yml file

  --zip                                    add the migration result to a zip folder for import into SAP Web IDE

  --synonym-target-provider                location of synonym-target-provider file
                                           needs target-release to be >= 2.0SP01

  --activate-public-access <type>          specify what public-access-role should be active, valid values for
                                           <type> are 'objects' and 'schema'

  --generate-providers                     generate a synonym-target-provider configuration for every found schema 
                                           (see staged migration documentation for details)
                                           needs target-release to be >= 2.0SP01

  --generated-provider-type <type>         specify the type of the generated providers 
                                           valid values for type are GRANTOR_SERVICE and LOGICAL_SCHEMA
                                           default is GRANTOR_SERVICE
                                           works only in combination with --generate-providers

  --hta                                    enable hta-mode
                                           this mode produces a hta-compatible output without hdbgrants files and public-access roles

  --generate-local-slash-synonyms          enable the generation of local slash-synonyms for all calculationviews in the context
                                           needs target-release to be >= 2.0SP02
                                           
  --integrated-synonymtargets              integrate the synonym target configuration in hdbsynonym files rather than separate 
                                           hdbsynonymconfig files in the cfg directory. Works only for providers of type LOGICAL_SCHEMA


  --target-release <release>               specify the version of the target hana system
                                           valid entries are 2.0SP00, 2.0SP01, 2.0SP02 and 2.0SP03 - default is 2.0SP03

One View Migration options  

  --join-order-type <type>                 specify the join order type
                                           valid values for type are insideOut and outsideIn

  --unhide-hidden-columns                    unhide hidden view columns



The database connection needs to be specified with the following environment variables

There are two options of migration tool usage:

1. Using the on-premise DB or DB without requirements to open the NEO tunnel

  HANA_HOST         Hana server host name
  HANA_SQL_PORT     SQL Port
  HANA_USER         User name
  HANA_PASSWD       Password
  HANA_CERTIFICATE  certificate file when using ssl encryption (X509 PEM)
  
  NEO_TUNNEL_IS_ACTIVE=false    Responsible for open the NEO tunnel
  
2. Using the DB which is required to use the NEO tunnel

  HANA_USER         User name
  HANA_PASSWD       Password
  
  NEO_TUNNEL_IS_ACTIVE=true    Responsible for open the NEO tunnel

Using the second option (migration with NEO Tunnel opening) the following environment variables must be provided to open the NEO database tunnel connection

  NEO_TUNNEL_ACCOUNT   NEO sub-account name
  NEO_TUNNEL_HOST      NEO sub-account host (ex. eu2.hana.ondemand.com)
  NEO_TUNNEL_USER      NEO sub-account user
  NEO_TUNNEL_PASSWD    NEO sub-account user password
  NEO_TUNNEL_DB_NAME   NEO DB Name or Schema

If the source database does not support the procedure SYS.GET_OBJECTS_IN_DDL_STATEMENT, 
an external parse system can be specified with the following environment variables


  HANAEXT_HOST         Hana server host name
  HANAEXT_SQL_PORT     SQL Port
  HANAEXT_USER         User name
  HANAEXT_PASSWD       Password
  HANAEXT_CERTIFICATE  certificate file when using ssl encryption (X509 PEM)


Version 1.1.21



```

### Dotenv support
The tool supports dotenv (https://www.npmjs.com/package/dotenv). The supported parameters are the following:
```
HANA_HOST=
HANA_SQL_PORT=
HANA_USER=
HANA_PASSWD=
HANA_CERTIFICATE=

NEO_TUNNEL_IS_ACTIVE=false

NEO_TUNNEL_ACCOUNT=
NEO_TUNNEL_HOST=
NEO_TUNNEL_USER=
NEO_TUNNEL_PASSWD=
NEO_TUNNEL_DB_NAME=
```

## Learn about it

The latest external documentation can be found here: https://help.sap.com/viewer/58d81eb4c9bc4899ba972c9fe7a1a115/ 
Internal draft for comments: https://help.sap.com/viewer/DRAFT/58d81eb4c9bc4899ba972c9fe7a1a115/ 

Internal Wiki (concepts and raw documentation input): https://github.wdf.sap.corp/XSA-Migration/xs-migration/wiki/Design-Time-Object-Types

## Develop it
We use grunt to build the migration assistant in three simple steps. Change to the cloned directory.

Ensure you configured SAP Nexus as the npm repository and that the proxy and no-proxy variables are set correctly (see ```cfg/build.py```).

### Setting up the development version
Make sure ```JAVA_HOME``` points to a JRE 8 and ```NODE_HOME``` points to a node 6 installation.
```
npm run install-dev
```

### Running the development version  
Change to the cloned directory and execute:
```
node xs-migration.js
```
## VSCode or BAS Development

This tool allows usage in the BAS or VSCode as an extension to properly run the XS Migration using the commands from the Command Pallet.

There are two ways of how to maintain it.

### VSCode Development (local machine)

1. Download the [code-snippet](https://github.com/SAP/code-snippet/releases) vsix extension file and install it on your local machine in the VSCode. [Here](https://code.visualstudio.com/docs/editor/extension-gallery#:~:text=Install%20from%20a%20VSIX%23&text=Using%20the%20Install%20from%20VSIX,vsix%20file.) you can find an official documentation of how to do that.
2. Open the workspace with the extension sources and press key/combination of keys:
    * `F5` - run the extension locally with debug mode (you can debug the extension passing the breakpoints in the source code)
    * `CTRL + F5` - run the extension locally without debug mode
3. Select `"View" -> "Command Pallet..."` or `F1` or `CTRL + SHIFT + P (on windows)` and choose the "Start XS Migration" command.

### BAS Development (remote)

*Note:* the full documentation of development/deployment/usage/testing and other BAS features you can find [here](https://github.wdf.sap.corp/pages/devx-wing/CookBook/extension-guides/00-App-Studio-extension-overview/) (SAP VPN is required)

To be able to develop and maintain this extension you can choose the following ways:

1. Write code and Run / Debug
    * If you need access to the sources for hotfixes or something else then just do the following:
        * create a new space in the BAS (open your Cloud Foundry environment, select the sub-account and go to the Instances and Subscriptions, there you need to subscribe to the SAP Business Application Studio and grant user roles for development)
        * using the drag & drop copy your sources (or use git clone) into your opened workspace in the BAS
        * to run the extension press `F1` and select `Hosted Plugin: Start Instance`
        * select the root folder of the extension sources
        * **Note:** if you have encountered some permissions errors, run the following command from the project root folder in terminal:
            * `chmod +x ./tools/neo-tunnel.sh && chmod +x ./tools/neo/tools/neo.sh`
 2. To only run the extension you can use [this](https://github.wdf.sap.corp/devx-wing/extensions-cli) guide to be able to deploy an extension and run it in the BAS. Also, the following links could be useful: [Deploying Extensions](https://github.wdf.sap.corp/pages/devx-wing/CookBook/extension-guides/04-Deploy-extension/), [Testing an Extension](https://github.wdf.sap.corp/pages/devx-wing/CookBook/extension-guides/07-Test-your-extension/)
      * To build the extension go to the project root folder in terminal and run the `npm i && npm run build-ext` command, then `*.vsix` file will be generated automatically
      * Open the Command Pallet (using View or `F1` or `CTRL + SHIFT + P`) and select the `Plugin: Deploy plugin by id` command and type the following `local-dir:/home/user/projects/<project-folder>`  (`local-dir:/home/user/projects/xs-migration-cloud`).
      * If the `Start XS Migration` command did not appear then just refresh the page with the opened BAS Space.

## Build it

### Install dependencies and run grunt.
```
npm install
npm run download-manual-deps
npm run package
```
### Done!
Find the built packages for Windows and Linux in the output folder ```dist```.


## Integration test backend
Repository artefacts are located in  
https://github.wdf.sap.corp/XSA-Migration/migration-testing   

Stuff that could not be exported easily can be created by running  
https://github.wdf.sap.corp/XSA-Migration/xs-migration/blob/master/manual_creation.sql  
  
  
**Additional steps:**     
- DU HCO_DEMOCONTENT needs to be installed on the system.
- Vendor needs to be set in Configuration -> nameserver.ini (or indexserver.ini if the database is not the system-db of an mdc system) -> repository -> content_vendor
- DU MIGRATION_TESTING needs to be created manually via studio or /sap/hana/xs/lm webui including the package `com.sap.migration.testing`


## Troubleshooting

### BAS process spawning (exec) issue

If you have encountered issues with the process spawning using exec or child spawning and facing the `Failed to chdir /theia/browswer-app` (or something like that) error during the extension execution in BAS, then just try to spawn your process from the user workspace folder, for example if you have the following process spawning:

```javascript
exec('java -jar file.jar <options>', function (err, stdout, stderr) {
    //...
});
```

Just add the `cwd` in the exec options like

```javascript
var absolute = pathIsAbsolute(context.targetDir);
var targetDirToolLocFolder = (absolute ? "": "./") + context.targetDir;

exec('java -jar file.jar <options>', {
    cwd: targetDirToolLocFolder
},function (err, stdout, stderr) {
    //...
});
```

### Disable showing the sensitive data from the NEO Tools in BAS

To disable that kind of issues with the sensitive data displaying in the BAS Kibana logs the following changes were made:

1. The `<project-root>/tools/neo/tools/lib/cmd/com.sap.jpaas.infrastacture.console.framework` package has been decompiled and compiled back with some changes that allows to use the properties file with the sensitive data (especially the `password` field) due to before such kind of properties were in the blacklist of properties usage due to confidential information.
2. For the time being the `xs-tmp.properties` is being created during the NEO Tunnel usage and contains the sensitive password. This file is deleted as soon as the tunnel closed and cannot be opened.