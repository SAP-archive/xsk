---
title: Changes
---

Changes
===

## Overview
---

Due to the changes between HANA Cloud and HANA on Neo, XSK performs modification to existing artifacts and introduces new artifacts in an attempt to keep application functionality. Below are 

## List of changes
---

### HDI Container

1. In HANA Cloud, calculation views can live only in an HDI container. This is why XSK is creating an HDI container and puts the calculation views in it. See [below](#calculation-views) for changes on calculation views.

2. A new file is introduced to model the HDI container.
```json
{
    "configuration": "path/to/hdiconfig", 
    "users": ["HDI_CONTAINER_USER"],
    "group": "CONTAINER_GROUP_NAME", 
    "container": "CONTAINER_NAME", 
    "deploy": [
        "path/to/artifact/to/be/deployed1",
        "path/to/artifact/to/be/deployed2"
    ], 
    "undeploy": [
        "path/to/artifact/to/be/undeployed"
    ] 
}
```
If you'd like to deploy new artifacts to the HDI container add them to the `deploy` section.  
If you'd like to undeploy existing artifacts from the HDI container move them to the `undeploy` section.

3. A default hdiconfig file is created which contains all plugins as described [here](https://help.sap.com/docs/HANA_CLOUD_DATABASE/c2cc2e43458d4abda6788049c58143dc/c1df57a55f774cbea9097bded789fd36.html?version=2021_3_QRC&locale=en-US) 

4. Syonyms - as calculation views live in an HDI container visibility to objects outside the container is managed via synonyms. This is why a new file `hdi-synonyms.hdbsynonym` is created which contains synonyms pointing to objects outside the HDI container.

5. Public synonyms - in order to make artifacts in the HDI container accessible to XSOdata and XSJS services, public synonyms are created for them.

### Artifact Changes

#### Calculation Views

1. Only graphical calulation views are supported. Follow the migration steps in SAP HANA Studio to convert scripted calculation views to 
2. Default schema is not supported in HDI and is removed during migration.
3. `ColumnObject` attribute is changed to `resourceUri` pointing to a synonym

#### Analytic Privileges
1. `SESION_USER` variable is changed to `SESSION_CONTEXT('APPLICATIONUSER')` which is the named user accessing the application, whereas the session user is the technical user used by XSK to establish connection.
2. XML based analytic privileges must be converted to SQL analytic privileges

#### HDB Role
As database communication happens with a technical user, hdbroles are not applicable for restricting access to database resources within XSK. Thest must be migrated to application-level roles/xsprivileges.

#### Table functions
HDB Table functions now live in the HDI container and access data from the outside via synonyms.

#### Destinations
The following artifacts are **not supported** and must be moved to SAP BTP Destination service: `.xshttpdest`, `.xsoauthappconfig`, `.xsoauthclientflavor`. See more details [here](../artifacts/xshttpdest.md)

#### SQL syntax

1. `UPDATE FROM` statements are automatically converted to `MERGE INTO` statements
2. Reserved keywords such as `row` will be modified to `row1`
3. `_SYS_REPO` is not available and any code using must be adjusted

### API Changes

1. `$.repo` is undocumented as is not supported
2. `$.net.http.readDestination` now uses SAP BTP Destination service and requires a service instance bound to XSK
3. `$.text` API is not available
4. `$.net.Mail` requires a destination of type Mail in SAP BTP Destination service pointing to a dedicated mail server