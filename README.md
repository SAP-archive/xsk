# Project "XSK"

[![Build Status](https://github.com/sap/xsk/workflows/Build/badge.svg)](https://github.com/sap/xsk/actions?query=workflow%3ABuild)
[![REUSE status](https://api.reuse.software/badge/github.com/SAP/xsk)](https://api.reuse.software/info/github.com/SAP/xsk)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![GitHub contributors](https://img.shields.io/github/contributors/sap/xsk.svg)](https://github.com/sap/xsk/graphs/contributors)


## Description

Compatible environment for [SAP HANA Extended Application Services](https://help.sap.com/viewer/52715f71adba4aaeb480d946c742d1f6/2.0.03/en-US/a6c0749255d84a81a154a7fc87dd33ce.html) (XS) based applications. It is deployed outside of [SAP HANA](https://www.sap.com/products/hana.html?btp=991d50bf-fa15-4979-ac4b-b280b0eb951f) instance as a [Docker](https://www.docker.com/) a container on [Kubernetes](https://kubernetes.io/). Hence, some of the features can work against any other JDBC compliant RDBMS such as [PostgreSQL](https://www.postgresql.org/). The compatibility stack is an extension of the [Eclipse Dirigible](https://github.com/eclipse/dirigible) cloud development platform.

> Note: the project is not yet ready to be used productively

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
- [Deploy](#how-to-run)
  - [Local](#local-tomcat-server-2)
  - [Cloud Foundry](#cloud-foundry-2)
  - [Kyma](#kyma-2)
  - [Helm](#helm)
- [Push to Docker Hub](#how-to-push-on-docker-hub)
- [Configuration](#configuration)
  - [Environment Variables](#environment-variables)
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

### Development Experience

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


---


## Installation

### How to build

Prerequisites:
- Java suported versions 11 - 13
- Maven 3.6.2 or later
```
mvn clean install
```

Integration Tests:
- Each integration test must end in ITCase as stated in the maven profile
- The profile is to be run as follows:
```
mvn verify -Pitests
```

#### Environment Variables for Local Instance

    export DIRIGIBLE_DATABASE_PROVIDER=custom
    export DIRIGIBLE_DATABASE_CUSTOM_DATASOURCES=HANA
    export DIRIGIBLE_DATABASE_DATASOURCE_NAME_DEFAULT=HANA
    export HANA_DRIVER=com.sap.db.jdbc.Driver
    export HANA_URL=jdbc:sap://<uid>.hana.prod-eu10.hanacloud.ondemand.com:443?encrypt=true&validateCertificate=true
    export HANA_USERNAME=DBADMIN
    export HANA_PASSWORD=<password>
    export DIRIGIBLE_SCHEDULER_DATABASE_DRIVER=com.sap.db.jdbc.Driver
    export DIRIGIBLE_SCHEDULER_DATABASE_URL=jdbc:sap://<uid>.hana.prod-eu10.hanacloud.ondemand.com:443?encrypt=true&validateCertificate=true
    export DIRIGIBLE_SCHEDULER_DATABASE_USER=DBADMIN
    export DIRIGIBLE_SCHEDULER_DATABASE_PASSWORD=<password>
    export DIRIGIBLE_MESSAGING_USE_DEFAULT_DATABASE=false
    export DIRIGIBLE_FLOWABLE_USE_DEFAULT_DATABASE=false
    export DIRIGIBLE_CMS_PROVIDER=database
    export DIRIGIBLE_CMS_DATABASE_DATASOURCE_TYPE=custom
    export DIRIGIBLE_CMS_DATABASE_DATASOURCE_NAME=HANA

#### Pull Docker images

##### Local (Tomcat Server)

```
docker pull dirigiblelabs/xsk
```

##### Cloud Foundry

```
docker pull dirigiblelabs/xsk-cf
```

##### Kyma

```
docker pull dirigiblelabs/xsk-kyma
```

#### Build Docker images

##### Local (Tomcat Server)

```
cd releng/server

docker build -t dirigiblelabs/xsk .
```
##### Cloud Foundry

```
cd releng/sap-cf

docker build -t dirigiblelabs/xsk-cf .
```

##### Kyma

```
cd releng/sap-kyma

docker build -t dirigiblelabs/xsk-kyma .
```

### How to run

#### Local (Tomcat Server)

##### With local database

    docker run -p 8080:8080 dirigiblelabs/xsk

##### With persistent volume

    docker run -p 8080:8080 -v <your-local-directory>:/usr/local/tomcat/target dirigiblelabs/xsk

##### With HANA Cloud instance(Mac)

    docker run -p 8080:8080 dirigiblelabs/xsk \
    -e DIRIGIBLE_DATABASE_PROVIDER=custom \
    -e DIRIGIBLE_DATABASE_CUSTOM_DATASOURCES=HANA \
    -e DIRIGIBLE_DATABASE_DATASOURCE_NAME_DEFAULT=HANA \
    -e HANA_DRIVER=com.sap.db.jdbc.Driver \
    -e HANA_URL=jdbc:sap://<uid>.hana.prod-eu10.hanacloud.ondemand.com:443?encrypt=true&validateCertificate=true \
    -e HANA_USERNAME=DBADMIN \
    -e HANA_PASSWORD=<password> \
    -e DIRIGIBLE_SCHEDULER_DATABASE_DRIVER=com.sap.db.jdbc.Driver \
    -e DIRIGIBLE_SCHEDULER_DATABASE_URL=jdbc:sap://<uid>.hana.prod-eu10.hanacloud.ondemand.com:443?encrypt=true&validateCertificate=true \
    -e DIRIGIBLE_SCHEDULER_DATABASE_USER=DBADMIN \
    -e DIRIGIBLE_SCHEDULER_DATABASE_PASSWORD=<password> \
    -e DIRIGIBLE_MESSAGING_USE_DEFAULT_DATABASE=false \
    -e DIRIGIBLE_FLOWABLE_USE_DEFAULT_DATABASE=false \
    -e DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE=true \
    -e DIRIGIBLE_CMS_PROVIDER=database \
    -e DIRIGIBLE_CMS_DATABASE_DATASOURCE_TYPE=custom \
    -e DIRIGIBLE_CMS_DATABASE_DATASOURCE_NAME=HANA
    
##### With HANA Cloud instance(Windows)
    docker run -p 8080:8080 --env-file env-variables.env dirigiblelabs/xsk

##### Go to:

> http://localhost:8080

#### Cloud Foundry
Prerequisites: Get access to the **SAP Cloud Platform Cloud Foundry** environment.

1. Set the correct Cloud Foundry **API Endpoint**:
    ```
    cf api https://api.cf.eu10.hana.ondemand.com
    ```
1. **Login** to the target Cloud Foundry space:
    ```
    cf login
    ```
1. **Deploy** the XSK engine to your Cloud Foundry space, with the following command:
    ```
    cf push xsk \
    --docker-image=dirigiblelabs/xsk-cf \
    --hostname xsk-<org-name> \
    -m 2G -k 2G
    ```
1. Create XSUAA Service Instance:
    - Create **security.json** file with the following content:
        ```json
        {
          "xsappname": "xsk-xsuaa",
          "scopes": [
            {
              "name": "$XSAPPNAME.Developer",
              "description": "Developer scope"
            },
            {
              "name": "$XSAPPNAME.Operator",
              "description": "Operator scope"
            }
          ],
          "role-templates": [
            {
              "name": "Developer",
              "description": "Developer related roles",
              "scope-references": [
                "$XSAPPNAME.Developer"
              ]
            },
            {
              "name": "Operator",
              "description": "Operator related roles",
              "scope-references": [
                "$XSAPPNAME.Operator"
              ]
            }
          ],
          "role-collections": [
            {
              "name": "xsk-developer",
              "description": "XSK Developer",
              "role-template-references": [ 
                "$XSAPPNAME.Developer",
                "$XSAPPNAME.Operator"
              ]
            }
          ]
        }
        ```
    - Execute the following command to create the XSUAA service instance:
        ```
        cf create-service xsuaa application xsk-xsuaa -c security.json
        ```
1. Bind the XSUAA Service Instance to the XSK engine:
    ```
    cf bind-service xsk xsk-xsuaa
    ```
1. Restart the XSK engine:
    ```
    cf restart xsk
    ```
1. Assign the **xsk-developer** role collection to your user, from the SAP Cloud Platform Cockpit.
1. Access the XSK engine:
    > You can retrieve the application URL from the SAP Cloud Platform Cockpit, or by executing the following command: **cf apps**

#### Kyma

Prerequisites: Get access to the **SAP Cloud Platform Kyma** environment. You can visit the following articles:
- [Kyma Environment](https://help.sap.com/viewer/65de2977205c403bbc107264b8eccf4b/Cloud/en-US/468c2f3c3ca24c2c8497ef9f83154c44.html)
- [SAP Cloud Platform, Kyma runtime: How to get started](https://blogs.sap.com/2020/05/13/sap-cloud-platform-extension-factory-kyma-runtime-how-to-get-started/)
- [How to deploy Eclipse Dirigible in the SAP Cloud Platform Kyma environment](https://blogs.sap.com/2020/10/13/how-to-deploy-eclipse-dirigible-in-the-sap-cloud-platform-kyma-environment/)

1. Copy the YAML deployment configuration and create new file `deployment.yaml`. Replace **your-kyma-cluster-host** with the Kyma cluster host.

```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: xsk
  namespace: default
spec:
  replicas: 1
  selector:
    matchLabels:
      app: xsk
  template:
    metadata:
      labels:
        app: xsk
    spec:
      containers:
      - env:
        - name: DIRIGIBLE_THEME_DEFAULT
          value: fiori
        - name: DIRIGIBLE_HOST
          value: https://xsk.<your-kyma-cluster-host>
        image: dirigiblelabs/xsk-kyma
        imagePullPolicy: Always
        name: xsk
        ports:
        - containerPort: 8080
          name: xsk
          protocol: TCP
---
apiVersion: v1
kind: Service
metadata:
  labels:
    app: xsk
  name: xsk
  namespace: default
spec:
  ports:
  - name: xsk
    port: 8080
    protocol: TCP
    targetPort: 8080
  selector:
    app: xsk
  type: ClusterIP
---
apiVersion: gateway.kyma-project.io/v1alpha1
kind: APIRule
metadata:
  name: xsk
  namespace: default
spec:
  gateway: kyma-gateway.kyma-system.svc.cluster.local
  rules:
  - accessStrategies:
    - config: {}
      handler: noop
    methods:
    - GET
    - POST
    - PUT
    - PATCH
    - DELETE
    - HEAD
    path: /.*
  service:
    host: xsk.<your-kyma-cluster-host>
    name: xsk
    port: 8080
```

1. Navigate to the Kyma Console and use the **Deploy new resource** button to upload the **deployment.yaml** file
1. Select the **Service Management > Catalog** tab
1. Open the **Authorization & Trust Management** service
1. Click on the **Add** button to create new service instance
    - Name: xsuaa-xsk
    - Plan: application
    - In the **Add parameters** section provide the following JSON and replace the **your-kyma-cluster-host** placeholder:

```json
{
    "xsappname": "xsk-xsuaa",
    "oauth2-configuration": {
       "token-validity": 7200,
       "redirect-uris": [
          "https://xsk.<your-kyma-cluster-host>"
       ]
    },
    "scopes": [
       {
          "name": "$XSAPPNAME.Developer",
          "description": "Developer scope"
       },
       {
          "name": "$XSAPPNAME.Operator",
          "description": "Operator scope"
       }
    ],
    "role-templates": [
       {
          "name": "Developer",
          "description": "Developer related roles",
          "scope-references": [
             "$XSAPPNAME.Developer"
          ]
       },
       {
          "name": "Operator",
          "description": "Operator related roles",
          "scope-references": [
             "$XSAPPNAME.Operator"
          ]
       }
    ],
    "role-collections": [
       {
          "name": "xsk-developer",
          "description": "XSK Developer",
          "role-template-references": [ 
             "$XSAPPNAME.Developer",
             "$XSAPPNAME.Operator"
          ]
       }
    ]
 }
```

1. Once the service instance is in **RUNNING** state, select the **Bound Applications** tab
1. Click on the **Bind Application** button and from the list of applications select the **xsk** deployment
1. Wait about **30sec - 1min** until the **XSK** deployment is updated with the bound service instance
1. Access the XSK instance at **https://xsk.your-kyma-cluster-host**
  - The XSK instance URL can be found also from the Kyma Console under the **Configuration -> API Rules** tab

##### With HANA Cloud instance

Create `hana-cloud-database` secret:

```
kubectl create secret generic hana-cloud-database \
--from-literal=DIRIGIBLE_DATABASE_PROVIDER=custom \
--from-literal=DIRIGIBLE_DATABASE_CUSTOM_DATASOURCES=HANA \
--from-literal=DIRIGIBLE_DATABASE_DATASOURCE_NAME_DEFAULT=HANA \
--from-literal=HANA_DRIVER=com.sap.db.jdbc.Driver \
--from-literal=HANA_URL='jdbc:sap://<hana-url>/?encrypt=true&validateCertificate=false' \
--from-literal=HANA_USERNAME=<hana-username> \
--from-literal=HANA_PASSWORD=<hana-password> \
--from-literal=DIRIGIBLE_MESSAGING_USE_DEFAULT_DATABASE=false \
--from-literal=DIRIGIBLE_FLOWABLE_USE_DEFAULT_DATABASE=false \
--from-literal=DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE=true \
--from-literal=DIRIGIBLE_SCHEDULER_DATABASE_DRIVER=com.sap.db.jdbc.Driver \
--from-literal=DIRIGIBLE_SCHEDULER_DATABASE_URL='jdbc:sap://<hana-url>/?encrypt=true&validateCertificate=false' \
--from-literal=DIRIGIBLE_SCHEDULER_DATABASE_USER=<hana-username> \
--from-literal=DIRIGIBLE_SCHEDULER_DATABASE_PASSWORD=<hana-password>
```

> _**Note:** `HANA_URL` should be enclosed with `'`._

Create `xsk` deployment:

```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: xsk
  namespace: default
spec:
  replicas: 1
  selector:
    matchLabels:
      app: xsk
  template:
    metadata:
      labels:
        app: xsk
    spec:
      containers:
      - name: xsk
        image: dirigiblelabs/xsk-kyma:latest
        imagePullPolicy: Always
        envFrom:
        - secretRef:
            name: hana-cloud-database
        env:
        - name: DIRIGIBLE_THEME_DEFAULT
          value: fiori
        - name: DIRIGIBLE_HOST
          value: https://xsk.<your-kyma-cluster-host>
        ports:
        - containerPort: 8080
          name: xsk
          protocol: TCP
---
apiVersion: v1
kind: Service
metadata:
  labels:
    app: xsk
  name: xsk
  namespace: default
spec:
  ports:
  - name: xsk
    port: 8080
    protocol: TCP
    targetPort: 8080
  selector:
    app: xsk
  type: ClusterIP
---
apiVersion: gateway.kyma-project.io/v1alpha1
kind: APIRule
metadata:
  name: xsk
  namespace: default
spec:
  gateway: kyma-gateway.kyma-system.svc.cluster.local
  rules:
  - accessStrategies:
    - config: {}
      handler: noop
    methods:
    - GET
    - POST
    - PUT
    - PATCH
    - DELETE
    - HEAD
    path: /.*
  service:
    host: xsk.<your-kyma-cluster-host>
    name: xsk
    port: 8080
---
apiVersion: servicecatalog.k8s.io/v1beta1
kind: ServiceInstance
metadata:
  name: xsuaa-xsk
spec:
  clusterServiceClassExternalName: xsuaa
  clusterServiceClassRef:
    name: xsuaa
  clusterServicePlanExternalName: broker
  externalID: 03a337ea-ad74-11eb-8529-0242ac130003
  parameters:
    xsappname: xsk-xsuaa
    oauth2-configuration:
      redirect-uris:
      - https://xsk.<your-kyma-cluster-host>
      token-validity: 7200
    role-collections:
    - description: XSK Developer
      name: xsk
      role-template-references:
      - $XSAPPNAME.Developer
      - $XSAPPNAME.Operator
    role-templates:
    - description: Developer related roles
      name: Developer
      scope-references:
      - $XSAPPNAME.Developer
    - description: Operator related roles
      name: Operator
      scope-references:
      - $XSAPPNAME.Operator
    scopes:
    - description: Developer scope
      name: $XSAPPNAME.Developer
    - description: Operator scope
      name: $XSAPPNAME.Operator
---
apiVersion: servicecatalog.k8s.io/v1beta1
kind: ServiceBinding
metadata:
  name: xsuaa-xsk-binding
spec:
  externalID: e4196b42-ad73-11eb-8529-0242ac130003
  instanceRef:
    name: xsuaa-xsk
  parameters: {}
  secretName: xsuaa-xsk-binding
---
apiVersion: servicecatalog.kyma-project.io/v1alpha1
kind: ServiceBindingUsage
metadata:
  name: xsuaa-xsk-usage
spec:
  parameters:
    envPrefix:
      name: ""
  serviceBindingRef:
    name: xsuaa-xsk-binding
  usedBy:
    kind: deployment
    name: xsk
```

> _**Note**: Replace the `<your-kyma-cluster-host>` placeholder with your Kyma cluster host (e.g. `c-xxxxxxx.kyma.xxx.xxx.xxx.ondemand.com`)._

### Helm

```
helm repo add dirigible https://eclipse.github.io/dirigible
helm repo update

helm install xsk dirigible/dirigible \
--set kyma.enabled=true \
--set kyma.apirule.host=<kyma-host> \
--set dirigible.image=dirigiblelabs/xsk-kyma:latest
```



### How to push on Docker Hub

    docker login
    
    docker push dirigiblelabs/xsk

    docker push dirigiblelabs/xsk-kyma

    docker push dirigiblelabs/xsk-cf  
    
---

## Configuration

### Environment Variables

* **XSK_HDI_SUPPORTED** - whether the HDI API is supported by the database (e.g. HANA). Default is *true*.
* **XSK_HDI_ONLY** - all the database models to be processed only via HDI API (no built-in processing). Default is *false*.

---

## Limitations

Not all of the XS classic artifacts are supported as well as not all the features of the supported artifacts are covered so far.

---

## Known Issues

* Hard coded FQDN URLs have to be manually changed
* Hard coded SCHEMA names within SQL statements have to be manually changed
* Authentication is managed by the runtime container
* Authorization checks in the application layer only (no HANA database security/user management)

---

## How to obtain support

All the bug reports as well as the feature requests have to be registered as issues.

---

## Contributing

If you want to contribute, please check the [Contribution Guidelines](CONTRIBUTING.md)


