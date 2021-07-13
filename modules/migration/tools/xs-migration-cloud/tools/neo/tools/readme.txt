###################################################################
# Welcome to the SAP Cloud Platform SDK for Neo Environment Tools #
###################################################################

Runtime    : Java Web Tomcat 8
Version    : 3.117.9
Build Date : 2020-10-22


1. Working Directory

Open the command prompt and change the current directory to the tools folder of the SAP Cloud Platform SDK for Neo environment.

Example:
    cd sdk-3.117.9
    cd tools

2. Proxy Settings

If you have to configure proxy settings, specify them using the environment variables.

Examples:
  Windows:
    set HTTP_PROXY_HOST=proxy
    set HTTP_PROXY_PORT=8080
    set HTTPS_PROXY_HOST=proxy
    set HTTPS_PROXY_PORT=8080
    set HTTP_NON_PROXY_HOSTS="localhost"

    If you need basic proxy authentication, enter your user name and password:

    set HTTP_PROXY_USER=<user name>
    set HTTP_PROXY_PASSWORD=<password>
    set HTTPS_PROXY_USER=<user name>
    set HTTPS_PROXY_PASSWORD=<password>

  Linux:
    export http_proxy=http://proxy:8080
    export https_proxy=https://proxy:8080
    export no_proxy="localhost"

    If you need basic proxy authentication, add your user name and password:

    export http_proxy=http://<user name>:<password>@proxy:8080
    export https_proxy=https://<user name>:<password>@proxy:8080

3. Additional Information

To see a list of all available commands for operations you can perform with the command line, enter neo in the console prompt.

To get a help for a particular command call "neo help <command>".

The parameters used by the command line can be defined either in a .properties file, or directly in the command line as parameters.

For more information about exit code types and states,  see page  https://help.sap.com/viewer/65de2977205c403bbc107264b8eccf4b/Cloud/en-US/7886796eb9b9419fa6cecf1d215c38d8.html

Samples:
  See file example_war.properties in the samples/deploy_war folder for an example of a property file for deployment of a WAR file. In the template, enter your own user and account name. Then execute the deployment with the following line:
    neo deploy ../samples/deploy_war/example_war.properties

  You can deploy the same application as in the example above by executing the following command:
    neo deploy --account <your account> --application warapp --source ../samples/deploy_war/example.war --user <SDN user>

  You can find all parameters in samples/deploy_update_site/template.properties file.

  If you want to deploy more than one Web application on one and the same VM, put all WAR files in the same folder and execute the deployment with this source.

  After you have deployed and started an application, you can manage some of its logging configurations. In the template.properties file in the samples/logging folder, enter your own user, account and application name, and specify the logger which log level you want to change and the new log level. Then execute the following command:
    neo set-log-level ../samples/logging/template.properties

  You can also list all loggers with their effective log level, list all log files and download a log file.

  You can find the sample weather destination in the samples/connectivity directory. You can upload it using the template weather_destination.properties file by inserting your user, account and application.
    neo put-destination ../samples/connectivity/weather_destination.properties

  You can find more samples in the "samples" folder.
