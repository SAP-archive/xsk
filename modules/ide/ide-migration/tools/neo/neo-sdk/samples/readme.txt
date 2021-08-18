##############################################################
# Welcome to the SAP BTP SDK for the Neo environment Samples #
##############################################################

Runtime    : Java Web Tomcat 9
Version    : 4.2.4
Build Date : 2021-05-19


1. Introduction

All samples can be imported as Eclipse projects or as Maven projects.
We focused on Eclipse and Maven for their wide adoption, but the principles apply also to other IDEs and build systems.

2a. Prerequisites and Import Steps (for Eclipse projects)

(1) As a prerequisite you will need to install the SAP BTP Tools for Java (Eclipse).
For more information about installing and configuring these tools, see https://tools.hana.ondemand.com.

(2) As a prerequisite you will have to create a server runtime environment called "Java Web Tomcat 9".
You can do so through the menu: Window->Preferences->Server->Runtime Environment->Add...->SAP->Java Web Tomcat 9...
If you imported a sample not having done the above you will have an error: "Faceted Project Problem: Target runtime Java Web Tomcat 9 is not defined".
You can now do as described above or simply run the sample which will guide you through the creation of the runtime: Run As->Run on Server...

Import Steps for Eclipse projects:
  Import as Eclipse Project:
    Menu: File -> Import... -> General -> Existing Projects into Workspace

2b. Prerequisites and Import Steps (for Maven projects)

(1) As a prerequisite for the Maven import you will need to do the above and in addition install the appropriate Eclipse Maven tools as well.
You require the Maven-Eclipse integration and the Maven-Eclipse-WTP integration that bridges between Maven, Eclipse, and the WTP tooling which is useful for web projects.

To install the Maven Integration for Eclipse WTP, proceed as follows:
  From the main menu of the Eclipse IDE, choose  Help -> Eclipse Marketplace.
  Enter 'Maven' in the Find field and choose Go.
  Locate the 'Maven Integration for Eclipse WTP' item and choose the Install button.

Import Steps for Maven projects:
  Import as Maven Project:
    Menu: File -> Import... -> Maven -> Existing Maven Projects

3. Maven Support

The Maven build shows how to completely automate a headless build & test that:
- builds a Java web application based on the SAP BTP API
- shows how to run rudimentary unit tests (not present in all samples)
- installs, starts, waits for, and stops the local server runtime
- deploys the application to the local server runtime and runs the integration tests
- starts, waits for, and stops the cloud server runtime
- deploys the application to the cloud server runtime and runs the integration tests

The above build procedure makes use of the Maven Plugin for SAP BTP,
see https://help.sap.com/viewer/65de2977205c403bbc107264b8eccf4b/Cloud/en-US/4cbdab6e2eb14c92ab76540ffb32174c.html. 

You can activate the local integration tests with the following Maven profile:
mvn clean verify -P local-integration-tests ... (see below for additional Maven properties you need to define as well) 

You can activate the cloud integration tests with the following Maven profile:
mvn clean verify -P cloud-integration-tests ... (see below for additional Maven properties you need to define as well)

In order for the cloud integration tests to pass, you need to provide Maven properties for account, user name, and
password to access the SAP data center:
mvn clean verify -P cloud-integration-tests -Dsap.cloud.account=... -Dsap.cloud.username=... -Dsap.cloud.password=...

Hint: To avoid retyping the above whenever you call Maven, you can of course add the properties directly to the pom.xml.
      You may like to use environment variables and set the properties in the pom.xml based on their values,
      especially when it comes to sensitive information like passwords which you should avoid to store in clear text.

If you have a (trial) developer account only, you will have to additionally override the default data center host:
mvn clean verify -P cloud-integration-tests ... -Dsap.cloud.host=hanatrial.ondemand.com

If HTTPS Internet access requires a proxy, you will have to additionally provide the following Maven property/ies:
mvn clean verify ... -Dhttps.proxyHost=... -Dhttps.proxyPort=...

Hint: If your proxy requires authentication, you may like to pass along proxy user name and password and implement
      an Authenticator (http://docs.oracle.com/javase/7/docs/api/java/net/Authenticator.html). That is not shown
      in these SDK samples for the sake of better readability.
