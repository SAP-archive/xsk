---
title: Environment Variables
hide:
  - toc
---

Environment Variables
===

## Configuration Types

Based on the layer, they are defined, configuration variables have the following priorities:

=== "Runtime"

    Highest precedence:
    
    - No rebuild or restart of the application is required when configuration is changed.
    - The [Configuration API](../../../api/core/configurations/) could be used to apply changes in the **Runtime** configuration.

=== "Environment"

    Second precedence:
    
    - No rebuild is required when configuration is changed, however the application should be restarted, to apply the environment changes.
    - Usually the **Environment** configurations are provided during the application deployment, as part of application descriptor _(e.g. [Define environment variable for container in Kubernetes](https://kubernetes.io/docs/tasks/inject-data-application/define-environment-variable-container/) or in [Cloud Foundry App Manifest](https://docs.cloudfoundry.org/devguide/deploy-apps/manifest-attributes.html#-add-variables-to-a-manifest))_.


=== "Deployment"

    Third precedence:
    
    - Rebuild and re-deployment is required.
    - "Default" deployment _(`ROOT.war`)_ configuration variables are taken from `dirigible.properties` properties file _(sample could be found [here](https://github.com/eclipse/dirigible/blob/master/releng/server-all/src/main/resources/dirigible.properties))_.

=== "Module"

    Lowest precedence:
    
    - Rebuild and re-deployment is required.
    - "Default" module _(e.g. `dirigible-database-custom.jar`, `dirigible-database-h2.jar`)_ configuration variables are taken from `dirigible-xxx.properties` properties files _(sample could be found [here](https://github.com/eclipse/dirigible/blob/master/modules/database/database-h2/src/main/resources/dirigible-database-h2.properties) and [here](https://github.com/eclipse/dirigible/blob/master/modules/database/database-custom/src/main/resources/dirigible-database-custom.properties))_

!!! Note
	The precedence order means that, if the there is an **Environment** variable with name `DIRIGIBLE_TEST` and **Runtime** variable with the same name, the **Runtime** variable will have high prority and will be applied.

All applied configuration values could be found under the [Configurations View](https://www.dirigible.io/help/development/ide/views/configurations/).

## Configuration Parameters

### HDI
---

Parameter               | Description                                                    | Default*
----------------------- | -------------------------------------------------------------- | --------
**XSK_HDI_SUPPORTED**   | Whether the HDI API is supported by the database _(e.g. HANA)_ | _`true`_
**XSK_HDI_ONLY**        | Database models to be processed only via the HDI API           | _`false`_

> _**Note:** All Eclipse Dirigible related environment variables could be found [here](https://www.dirigible.io/help/setup/setup-environment-variables/)._
