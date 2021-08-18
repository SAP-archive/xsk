##################################################################################
# Welcome to the SAP BTP SDK for the Neo environment Persistence with JPA Sample #
##################################################################################

1. Prerequisites for execution on the cloud

This sample project requires access to a database to show the basic usage of JPA in the SAP Cloud
Platform. Make sure your account has access to a database system where you can create a schema. That
schema may be created on a productive SAP HANA Instance, a SAP HANA database with multitenant database
container support, a SAP ASE database system or another database system available for your account.

2. Provide access to a schema for the sample application

1) Deploy the sample application to a subaccount on SAP BTP, but do not yet start it.
   Starting the application would fail with an error because no database has been bound so far.
2) Create a schema on a database system that is accessible to your account.
3) Bind the sample application to the schema you created.
4) Start the sample application.

For more information, see the documentation at
https://help.sap.com/viewer/65de2977205c403bbc107264b8eccf4b/Cloud/en-US/e4aeacd2bb5710148ee99255136d96a5.html#loioe4aeacd2bb5710148ee99255136d96a5__section_deployCloud.