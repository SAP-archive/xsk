---
title: Migration guide
---

Migration guide
===

## Overview
---

Follow this guide for steps how to migrate an SAP HANA XS Classic application to SAP HANA Cloud.

## Supported artifacts and APIs
---

1. Check out the [APIs page](../api/index.md) for a list of APIs and their compatibility status.
2. Checkout the [Artifacts page](../artifacts/index.md) for a list of artifacts and their compatibility status.

## Preparation
---

Before starting the migration process, make sure to follow the steps in the [Prerequisites section](../setup/prerequisites/index.md).
Next, make sure to setup XSK on [Kyma](../setup/kyma.md).

## Steps

### Catalog migration

Some applications use tables which were not created via the standard XS means (ie. hdbdd, hdbtable). These tables need to be created in HANA Cloud before proceeding with the migration of XS artifacts.  

**Note:** Access to objects in these schemas must be manually granted to the XSK technical user.

If there are no such artifacts, proceed to [XS artifacts migration](#xs-artifacts-migration)


### XS artifacts migration

After the necessary objects are created in the target HANA Cloud database, migration of the XS artifacts that use these objects can be executed.

1. Open XSK
2. Navigate to the Migration perspective in the left vertical menu
3. Choose `Migrate live project(s)`
4. Follow the wizard
    1. Enter your SAP BTP credentials
    2. Choose the HANA Database in which the Delivery Unit resides
    3. Select the workspace in which the sources will be copied and the delivery unit for migration
    4. Review the changed made by XSK - see [this page](./changes.md) for more details
    5. Click migrate and open the workspace
5. Publish the project - this will activate all artifacts (HANA artifacts, xsjs and xsodata services)
6. Validate the functionality of the application

### Data migration

Next, you can stop the old HANA database and proceed with migration of the data.  
Following this order of execution can minimize the downtime of the application.