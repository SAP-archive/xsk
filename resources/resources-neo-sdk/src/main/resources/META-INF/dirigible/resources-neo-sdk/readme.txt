######################################################
# Welcome to the SAP BTP SDK for the Neo environment #
######################################################

Runtime    : Java Web Tomcat 9
Version    : 4.2.4
Build Date : 2021-05-19


1. Introduction

The SAP BTP SDK for the Neo environment contains everything required to build applications in the Neo environment of SAP BTP.
The following section will show you its inner structure.
It gives you the libraries required for compilation of applications, contains documentation, samples, and the tools for command line usage.

2. File System Structure

   <root>
   |- api           Platform API JARs required for compilation of SAP BTP applications
   |- javadoc       JavaDoc of the above platform API
   |- license       Licenses of third party components contained in the SAP BTP SDK for the Neo environment
   |- repository    Repository from which the local server runtime can be installed
   |- samples       Samples demonstrating how to develop for SAP BTP Neo Environment
   |- server        Initially not present, but created once you install a local server runtime
   |- tools         Neo console client required for development, e.g. to install a SAP BTP local runtime
   |- readme.txt    Brief introduction into the SAP BTP SDK for the Neo environment, its content and how to set it up
   |- sdk.version   SAP BTP SDK for the Neo environment version information for use by other tools interacting with the SDK

3. Online Documentation

Instructions, tutorials, and samples can be found at https://help.sap.com/viewer/65de2977205c403bbc107264b8eccf4b/Cloud/en-US.

4. Update

SDKs for all platform runtimes in their latest version are available at https://tools.hana.ondemand.com/#cloud.

5. SAP BTP Tools for Java (Eclipse)

The SAP BTP SDK for the Neo environment is used by the SAP BTP Tools for Java (Eclipse).
Quick start developer information is available at https://tools.hana.ondemand.com/#cloud.
