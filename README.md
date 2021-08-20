# Project "XSK"

[![XSK - Try it Out](https://img.shields.io/badge/XSK-Try%20it%20Out-white.svg)](https://xsk-trial.kneo.promart.shoot.canary.k8s-hana.ondemand.com)
[![Build Status](https://github.com/sap/xsk/workflows/Build/badge.svg)](https://github.com/sap/xsk/actions?query=workflow%3ABuild)
[![REUSE status](https://api.reuse.software/badge/github.com/SAP/xsk)](https://api.reuse.software/info/github.com/SAP/xsk)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![GitHub contributors](https://img.shields.io/github/contributors/sap/xsk.svg)](https://github.com/sap/xsk/graphs/contributors)


## Description

Compatible environment for [SAP HANA Extended Application Services](https://help.sap.com/viewer/52715f71adba4aaeb480d946c742d1f6/2.0.03/en-US/a6c0749255d84a81a154a7fc87dd33ce.html) (XS) based applications. It is deployed outside of [SAP HANA](https://www.sap.com/products/hana.html?btp=991d50bf-fa15-4979-ac4b-b280b0eb951f) instance as a [Docker](https://www.docker.com/) a container on [Kubernetes](https://kubernetes.io/). Hence, some of the features can work against any other JDBC compliant RDBMS such as [PostgreSQL](https://www.postgresql.org/). The compatibility stack is an extension of the [Eclipse Dirigible](https://github.com/eclipse/dirigible) cloud development platform.

> _**Note:** the project is not yet ready to be used productively_


## Try it Out

`Try it Out` version is available [here](https://xsk-trial.kneo.promart.shoot.canary.k8s-hana.ondemand.com).

> _**Note**: the `Try it Out` instance is a shared one and it is intended for test & demo purposes only_

## Overview
- [Project XSK](#project-xsk)
  - [Background](#background)
  - [Development Experience](#development-experience)
  - [Life-cycle Management](#life-cycle-management)
  - [Artifacts Coverage](#artifacts-coverage)
  - [XSJS APIs Coverage](#xsjs-apis-coverage)
- [Build](#installation)
  - [Maven Build](#how-to-build)
  - [Docker Build](#build-docker-images)
  - [Push to Docker Hub](#how-to-push-on-docker-hub)
- [Deploy](#how-to-run)
- [Configuration](#configuration)
  - [Environment Variables](#environment-variables)
  - [Chrome Extentions](#chrome-extentions)
- [Limitations](#limitations)
- [Known Issues](#known-issues)
- [Support](#how-to-obtain-support)
- [Contributing](#contributing)


### Background

In the SAP Cloud Platform Neo environment XS classic programming model (XSC) is supported via the HANA 1.x database. The XSC engine resides in the HANA system itself. It can scale up, but cannot scale out. It is based on the multi-threaded JavaScript runtime Mozilla Spydermonkey (Firefox's engine). It supports synchronous programming model. It can serve backend services in JavaScript, HTML5 and other static content. It supports OData v2.0 compatible services defined in an \*.xsodata descriptors. It supports automatic tables, views, calculationviews materialisation based on proprietary formats.

XSK approach is to provide a custom [Eclipse Dirigible](https://www.dirigible.io/) stack coming with XSC engine type. This engine is based on the standard Dirigible's JavaScript engine with several enhancements such as predefined XSC API pre-loaded ($.* APIs), execution based on \*.xsjs instead of \*.js only, imports based on \*.xsjslib instead of \*.js.

There are corresponding APIs in Dirigible playing the same role as the ones in XSC (e.g. request, response, connection, etc.). A thin XSC bridge is provided for full compatibility.

The programming model is synchronous in multi-threaded environment (based on [GraalJS](https://github.com/graalvm/graaljs)). The descriptors for the table, views and calculationviews currently exists with the same life-cycle management, only the format is different. OData via descriptor approach is also available as part of the stack as well.

XSK stack is based on Java (JVM), so all the available plugins and/or new frameworks from Apache, Eclipse, and other open source providers can be easily integrated as well.

XSK stack can run within the HANA box, also in the virtual HANA system or outside in e.g. Kubernetes cluster, Kyma, Cloud Foundry, Open Stack. 

## Requirements

- Java suported versions 11 - 13
- Maven 3.6.2 or later

## Download and Installation

```
git clone https://github.com/SAP/xsk
cd xsk
mvn clean install
```

Integration Tests:
- Main focus is to test against PostgreSQL, MySQL and SAP HANA Cloud.
- There is a specific name pattern for each integration test (ending in ITTest).
- The itests profile is to be run as follows:
```
mvn verify -Pitests "-Dhana.url=jdbcUrl" "-Dhana.username=jdbcUsername" "-Dhana.password=jdbcPassword"
```
> _**Note** that you have to provide the credentials of your own HANA Cloud instance and more importantly to wrap them in quotes! Otherwise you might not be able to connect to the db instance due some special characters in respective db properties._

## How to run

- [Local](https://www.xsk.io/setup/local/)
- [Cloud Foundry](https://www.xsk.io/setup/cloud-foundry/)
- [Kyma](https://www.xsk.io/setup/kyma/)
- [Helm](https://www.xsk.io/setup/helm/)
    
#### Environment Variables

* **XSK_HDI_SUPPORTED** - whether the HDI API is supported by the database (e.g. HANA). Default is *true*.
* **XSK_HDI_ONLY** - all the database models to be processed only via HDI API (no built-in processing). Default is *false*.

#### Chrome Extentions

To Visualize XML insatll chrome extention [xml-tree](https://chrome.google.com/webstore/detail/xml-tree/gbammbheopgpmaagmckhpjbfgdfkpadb)
It will be used when showing xml related data, for example as xsodata

## Cheat Sheet

Visit the cheat sheet [here](https://github.com/SAP/xsk/wiki/Cheat-Sheet).

## Development Experience

| Aspect                         | Scope | Description  |
| ------------------------------ |:-----:| -------------|
| Preserve hdb* descriptors      |  ✅   |              |
| Preserve XSJS code             |  ✅   |              |
| Preserve XSOData descriptors   |  ✅   |              |
| Preserve XSC development model |  ✅   |              |
| Preserve XSC security model    |  ⚠️   | Authentication is managed by the runtime container |
| Support for XSJS code          |  ✅   |              |


### Life-cycle Management

| Aspect                                | Scope | Description  |
| ------------------------------------- |:-----:| -------------|
| End-to-end life-cycle management      |  ✅   |              |
| Single-step migration                 |  ✅   |              |
| Can be deployed as a monolith         |  ✅   |              |
| Can be deployed as a microservices    |  ✅   |              |
| Can be deployed on Kubernetes         |  ✅   |              |
| Can be deployed on Cloud Foundry      |  ✅   |              |


### Artifacts Coverage

| Aspect                | Scope | Description  |
| --------------------- |:-----:| -------------|
| .xsjs                 |  ✅   |              |
| .xsjslib              |  ✅   |              |
| .calculationview      |  ⚠️   |              |
| [.hdbprocedure](https://help.sap.com/viewer/3823b0f33420468ba5f1cf7f59bd6bd9/2.0.05/en-US/93de88bf2c8242179647e40f958c24e5.html)         |  ✅   |              |
| [.hdbrole](https://help.sap.com/viewer/3823b0f33420468ba5f1cf7f59bd6bd9/2.0.05/en-US/625d7733c30b4666b4a522d7fa68a550.html)              |  ❌   |              |
| [.hdbsequence](https://help.sap.com/viewer/3823b0f33420468ba5f1cf7f59bd6bd9/2.0.05/en-US/b295c2e0a5d547f8b1717ad7dd52cc90.html)          |  ✅   |              |
| .xsodata              |  ⚠️   |              |
| .hdbdd                |  ⚠️   |              |
| .xsaccess             |  ✅   |              |
| .xsjob                |  ✅   |              |
| .xssecurestore        |  ✅   |              |
| .hdbti (+csv)         |  ✅   |              |
| .xshttpdest           |  ✅   |              |
| .hdbschema            |  ✅   |              |



### XSJS APIs Coverage

| Aspect                                                                                                     | Scope | Description                        |
| ---------------------------------------------------------------------------------------------------------- |:-----:| -----------------------------------|
| [$.session](https://help.sap.com/doc/3de842783af24336b6305a3c0223a369/2.0.03/en-US/$.Session.html)         |  ⚠️    | Represents an SAP HANA XS session   |
| [$.request](https://help.sap.com/doc/3de842783af24336b6305a3c0223a369/2.0.03/en-US/$.web.WebRequest.html)  |  ✅   | Represents the client HTTP request currently being processed. |
| [$.response](https://help.sap.com/doc/3de842783af24336b6305a3c0223a369/2.0.03/en-US/$.web.WebResponse.html)|  ✅   | Represents the HTTP response currently being populated. |
| [$.hdb](https://help.sap.com/doc/3de842783af24336b6305a3c0223a369/2.0.03/en-US/$.hdb.html)                 |  ✅   | This namespace provides means for seamless HANA database access. It is intended to be a replacement of the older $.db namespace |
| [$.db](https://help.sap.com/doc/3de842783af24336b6305a3c0223a369/2.0.03/en-US/$.db.html)                   |  ✅   | Namespace for HANA database access |
| [$.util](https://help.sap.com/doc/3de842783af24336b6305a3c0223a369/2.0.03/en-US/$.util.html)               |  ✅   | Util namespace                     |
| [$.trace](https://help.sap.com/doc/3de842783af24336b6305a3c0223a369/2.0.03/en-US/$.trace.html)             |  ✅   | Trace namespace                    |
| [$.import](https://help.sap.com/doc/3de842783af24336b6305a3c0223a369/2.0.03/en-US/$.html#import)           |  ✅   | Imports a server-side JavaScript library artifact |
| [$.net](https://help.sap.com/doc/3de842783af24336b6305a3c0223a369/2.0.03/en-US/$.net.html)                 |  ✅   | Network namespace                  |
| [$.net.http](https://help.sap.com/doc/3de842783af24336b6305a3c0223a369/2.0.03/en-US/$.net.http.html)       |  ✅   | HTTP namespace                     |
| [$.util.codec](https://help.sap.com/doc/3de842783af24336b6305a3c0223a369/2.0.03/en-US/$.util.codec.html)   |  ✅   | Codec namespace                    |
| [$.web](https://help.sap.com/doc/3de842783af24336b6305a3c0223a369/2.0.03/en-US/$.web.html)                 |  ✅   | Web namespace                      |
| [$.security](https://help.sap.com/doc/3de842783af24336b6305a3c0223a369/2.0.03/en-US/$.security.html)       |  ✅   | Security namespace                 |

## Limitations

Not all of the XS classic artifacts are supported as well as not all the features of the supported artifacts are covered so far.

## Known Issues

* Hard coded FQDN URLs have to be manually changed
* Hard coded SCHEMA names within SQL statements have to be manually changed
* Authentication is managed by the runtime container
* Authorization checks in the application layer only (no HANA database security/user management)

## How to obtain support

All the bug reports as well as the feature requests have to be registered as issues.

## Contributing

If you want to contribute, please check the [Contribution Guidelines](CONTRIBUTING.md)


