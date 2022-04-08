---
title: APIs
---

APIs
===

## Overview
---

This page lists the SAP HANA XS Classic JavaScript APIs and whether they are supported by XSK.

## Reference
---

!!! note "SAP HANA XS Classic JavaScript APIs"

    For more information on the APIs, see [SAP HANA Extended Application Services (SAP HANA XS) API](https://help.sap.com/doc/3de842783af24336b6305a3c0223a369/2.0.03/en-US/$.html).

## Namespaces
---
### $

#### Classes

| Class       | Supported |
|-------------|-----------|
| Application | No        |
| Session     | Yes       |

#### Members

| Member      | Supported |
|-------------|-----------|
| application | No        |
| request     | Yes       |
| response    | Yes       |
| session     | Yes       |

#### Methods

| Method                   | Supported |
|--------------------------|-----------|
| import(package, library) | Yes       |
| import(path)             | Yes       |

### $.db

##### Classes

| Class             | Supported          |
|-------------------|--------------------|
| CallableStatement | Yes                |
| Connection        | Yes                |
| ParameterMetaData | Yes                |
| PreparedStatement | Yes                |
| ResultSet         | Yes                |
| ResultSetMetaData | Yes                |
| SQLException      | No (did not exist) |


#### Members

| Member    | Supported |
|-----------|-----------|
| isolation | No        |
| types     | No        |

#### Methods

| Method        | Supported                      |
|---------------|--------------------------------|
| getConnection | Partially (parameters ignored) |

#### Type Definitions

| Definition          | Supported |
|---------------------|-----------|
| configurationObject | No        |


### $.hdb

#### Classes

| Class             | Supported          |
|-------------------|--------------------|
| ColumnMetadata    | Yes                |
| Connection        | Yes                |
| ProcedureResult   | Yes                |
| ResultSet         | Yes                |
| ResultSetIterator | Yes                |
| ResultSetMetaData | Yes                |
| SQLException      | No (did not exist) |


#### Members

| Member    | Supported |
|-----------|-----------|
| isolation | No        |
| types     | No        |

#### Methods

| Method        | Supported                      |
|---------------|--------------------------------|
| getConnection | Partially (parameters ignored) |

### $.jobs

#### Classes

| Class        | Supported |
|--------------|-----------|
| Job          | Yes       |
| JobLog       | No        |
| JobSchedules | No        |


### $.net

#### Classes

| Class          | Supported |
|----------------|-----------|
| Destination    | Yes       |
| Mail           | Yes       |
| SMTPConnection | Yes       |

#### Members

| Member | Supported |
|--------|-----------|
| http   | Yes       |


### $.net.http

#### Classes

| Class       | Supported |
|-------------|-----------|
| Client      | Yes       |
| Destination | Yes       |
| Request     | Yes       |

#### Methods

| Method          | Supported                                                     |
|-----------------|---------------------------------------------------------------|
| readDestination | Yes* - package ignored, read from SAP BTP Destination service |


### $.security

#### Classes

| Class     | Supported |
|-----------|-----------|
| AntiVirus | No        |
| Store     | Yes       |



### $.security.crypto

#### Methods

| Member | Supported |
|--------|-----------|
| md5    | No        |
| sha1   | No        |
| sha256 | No        |


### $.security.x509

#### Methods

| Method     | Supported |
|------------|-----------|
| getIssuer  | No        |
| getSubject | No        |


### $.text.analysis

#### Classes

| Class   | Supported |
|---------|-----------|
| Session | No        |

### $.text.mining

#### Classes

| Class   | Supported |
|---------|-----------|
| Session | No        |


### $.trace

#### Methods

| Method           | Supported |
|------------------|-----------|
| debug            | Yes       |
| error            | Yes       |
| fatal            | Yes       |
| info             | Yes       |
| isDebugEnabled   | Yes       |
| isErrorEnabled   | Yes       |
| isFatalEnabled   | Yes       |
| isInfoEnabled    | Yes       |
| isWarningEnabled | Yes       |
| warning          | Yes       |


### $.util

#### Classes

| Class     | Supported                       |
|-----------|---------------------------------|
| SAXParser | No                              |
| Zip       | Partially - no password support |

#### Methods

| Method      | Supported |
|-------------|-----------|
| createdUuid | Yes       |
| stringify   | Yes       |

### $.util.codec

#### Methods

| Method       | Supported |
|--------------|-----------|
| decodeBase64 | Yes       |
| decodeHex    | Yes       |
| encodeBase64 | Yes       |
| encodeHex    | Yes       |


### $.util.compression

#### Methods

| Method | Supported |
|--------|-----------|
| gunzip | No        |
| gzip   | No        |

### $.util.sql

#### Methods

| Method       | Supported |
|--------------|-----------|
| isValidParam | No        |

### $.web

#### Classes

| Class             | Supported |
|-------------------|-----------|
| Body              | Yes       |
| EntityList        | Yes       |
| TupelList         | Yes       |
| WebEntityRequest  | Yes       |
| WebEntityResponse | Yes       |
| WebRequest        | Yes       |
| WebResponse       | Yes       |
