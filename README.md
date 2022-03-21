# Project "XSK"

[![XSK - Try it Out](https://img.shields.io/badge/XSK-Try%20it%20Out-white.svg)](https://trial.apps.xsk.io)
[![Build Status](https://github.com/sap/xsk/workflows/Build/badge.svg)](https://github.com/sap/xsk/actions?query=workflow%3ABuild)
[![REUSE status](https://api.reuse.software/badge/github.com/SAP/xsk)](https://api.reuse.software/info/github.com/SAP/xsk)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![GitHub contributors](https://img.shields.io/github/contributors/sap/xsk.svg)](https://github.com/sap/xsk/graphs/contributors)


## Description

Compatible environment for [SAP HANA Extended Application Services](https://help.sap.com/viewer/52715f71adba4aaeb480d946c742d1f6/2.0.03/en-US/a6c0749255d84a81a154a7fc87dd33ce.html) (XS) based applications. It is deployed outside of [SAP HANA](https://www.sap.com/products/hana.html?btp=991d50bf-fa15-4979-ac4b-b280b0eb951f) instance as a [Docker](https://www.docker.com/) a container on [Kubernetes](https://kubernetes.io/). Hence, some of the features can work against any other JDBC compliant RDBMS such as [PostgreSQL](https://www.postgresql.org/). The compatibility stack is an extension of the [Eclipse Dirigible](https://github.com/eclipse/dirigible) cloud development platform.

> _**Note:** the project is not yet ready to be used productively_


## Try it Out

`Try it Out` version is available [here](https://trial.apps.xsk.io).

> _**Note**: the `Try it Out` instance is a shared one and it is intended for test & demo purposes only_

## Overview

_Project documentation is available at: https://www.xsk.io_

- [Project XSK](#background)
  - [Setup](#setup)
  - [Readiness](#readiness)
- [Support](#how-to-obtain-support)
- [Contributing](#contributing)


### Background

In the SAP Cloud Platform Neo environment XS classic programming model (XSC) is supported via the HANA 1.x database. The XSC engine resides in the HANA system itself. It can scale up, but cannot scale out. It is based on the multi-threaded JavaScript runtime Mozilla Spydermonkey (Firefox's engine). It supports synchronous programming model. It can serve backend services in JavaScript, HTML5 and other static content. It supports OData v2.0 compatible services defined in an \*.xsodata descriptors. It supports automatic tables, views, calculationviews materialisation based on proprietary formats.

XSK approach is to provide a custom [Eclipse Dirigible](https://www.dirigible.io/) stack coming with XSC engine type. This engine is based on the standard Dirigible's JavaScript engine with several enhancements such as predefined XSC API pre-loaded ($.* APIs), execution based on \*.xsjs instead of \*.js only, imports based on \*.xsjslib instead of \*.js.

There are corresponding APIs in Dirigible playing the same role as the ones in XSC (e.g. request, response, connection, etc.). A thin XSC bridge is provided for full compatibility.

The programming model is synchronous in multi-threaded environment (based on [GraalJS](https://github.com/graalvm/graaljs)). The descriptors for the table, views and calculationviews currently exists with the same life-cycle management, only the format is different. OData via descriptor approach is also available as part of the stack as well.

XSK stack is based on Java (JVM), so all the available plugins and/or new frameworks from Apache, Eclipse, and other open source providers can be easily integrated as well.

XSK stack can run within the HANA box, also in the virtual HANA system or outside in e.g. Kubernetes cluster, Kyma, Cloud Foundry, Open Stack.

_**To learn more go to: https://www.xsk.io**_

## Requirements

- Java suported versions 11 - 13
- Maven 3.6.2 or later
- Access to SAP BTP account (optional)
- Access to SAP HANA Cloud instance (optional)

## Download and Installation

```
git clone https://github.com/SAP/xsk
cd xsk
mvn clean install
```

## Setup

- [Local](https://www.xsk.io/setup/)
- [Cloud Foundry](https://www.xsk.io/setup/cloud-foundry/)
- [Kyma](https://www.xsk.io/setup/kyma/)
- [Helm](https://www.xsk.io/setup/helm/)
- [Environment Variables](https://www.xsk.io/setup/environment-variables/)

## FAQ

- How long will XSK be supported by SAP?

  > XSK is an open source project with community support as of now. Everyone can join and make a [PR](CONTRIBUTING.md).

- Should future developments be based on XSK?

  > Yes, you can use XSK for future development.

- What about the tooling? Do we get state of the art tooling for maintaining and enhancing XSK?

  > XSK tooling is based on [Eclipse Dirigible](https://www.dirigible.io/) and in the near future it will be possible to maintain XSK projects with any modern IDE like VSCode, Eclipse Theia, etc.

- What about the ops aspects - will XSK be smoothly integrated into a state-of-the-art lifecycle and ops management (be it SAP-based or non-SAP based like GitHub Actions?

  > Yes, the XSK itself uses [GitHub actions](https://github.com/SAP/xsk/actions) for CI/CD

- Will there be limitations that will not be mitigated?

  > You can get the up-to-date list of covered [features](https://github.com/SAP/xsk/wiki/Readiness) as well as the [limitations](https://github.com/SAP/xsk/wiki/Limitations)

- Is the support of PostgreSQL only possible in principle or will this be officially supported?

  > Yes, it is officially supported. Features specific for HANA Cloud like HDI, Calculation Views are not available for PostgreSQL.

## Cheat Sheet

Visit the cheat sheet [here](https://github.com/SAP/xsk/wiki/Cheat-Sheet).

## Readiness

Visit the readiness page [here](https://github.com/SAP/xsk/wiki/Readiness).

## How to obtain support

All the bug reports as well as the feature requests have to be registered as issues.

## Contributing

If you want to contribute, please check the [Contribution Guidelines](CONTRIBUTING.md)



