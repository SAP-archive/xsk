# project "XSK"

## Description

Compatible environment for [SAP HANA Extended Application Services](https://help.sap.com/viewer/52715f71adba4aaeb480d946c742d1f6/2.0.03/en-US/a6c0749255d84a81a154a7fc87dd33ce.html) (XS) based applications. It is deployed outside of [SAP HANA](https://www.sap.com/products/hana.html?btp=991d50bf-fa15-4979-ac4b-b280b0eb951f) instance as a [Docker](https://www.docker.com/) a container on [Kubernetes](https://kubernetes.io/). Hence, some of the features can work against any other JDBC compliant RDBMS such as [PostgreSQL](https://www.postgresql.org/). The compatibility stack is an extension of the [Eclipse Dirigible](https://github.com/eclipse/dirigible) cloud development platform.

> Note: the project is not yet ready to be used productively


### Development Experience

| Aspect                         | Scope | Description  |
| ------------------------------ |:-----:| ------------:|
| Preserve hdb* descriptors      |  ✅   |              |
| Preserve XSJS code             |  ✅   |              |
| Preserve XSOData descriptors   |  ✅   |              |
| Preserve XSC development model |  ✅   |              |
| Preserve XSC security model    |  ⚠️   | Authentication is managed by the runtime container |
| Support for XSJS code          |  ✅   |              |


### Life-cycle Management

| Aspect                                | Scope | Description  |
| ------------------------------------- |:-----:| ------------:|
| End-to-end life-cycle management      |  ✅   |              |
| Single-step migration                 |  ✅   |              |
| Can be deployed as a monolith         |  ✅   |              |
| Can be deployed as a microservices    |  ✅   |              |
| Can be deployed on Kubernetes         |  ✅   |              |
| Can be deployed on Cloud Foundry      |  ✅   |              |


### Artifacts Coverage

| Aspect                | Scope | Description  |
| --------------------- |:-----:| ------------:|
| .xsjs                 |  ✅   |              |
| .xsjslib              |  ✅   |              |
| .calculationview      |  ⚠️   |              |
| .hdbprocedure         |  ✅   |              |
| .hdbrole              |  ❌   |              |
| .hdbsequence          |  ✅   |              |
| .xsodata              |  ⚠️   |              |
| .hdbdd                |  ⚠️   |              |
| .xsaccess             |  ✅   |              |
| .xsjob                |  ✅   |              |
| .xssecurestore        |  ✅   |              |
| .hdbti (+csv)         |  ✅   |              |
| .xshttpdest           |  ✅   |              |
| .hdbschema            |  ✅   |              |



### Artifacts Coverage

| Aspect                | Scope | Description  |
| --------------------- |:-----:| ------------:|
| $.session             |  ⚠️   |              |
| $.request             |  ✅   |              |
| $.response            |  ✅   |              |
| $.hdb                 |  ✅   |              |
| $.db                  |  ✅   |              |
| $.util                |  ✅   |              |
| $.trace               |  ✅   |              |
| $.import              |  ✅   |              |
| $.net                 |  ✅   |              |
| $.net.http            |  ✅   |              |
| $.util.codec          |  ✅   |              |
| $.web                 |  ✅   |              |
| $.security            |  ✅   |              |


---


## Installation

### How to build

    mvn clean install
    
#### Build Docker images

    docker build -t dirigiblelabs/dirigible-xsk:0.0.1 .
    
    docker build -t dirigiblelabs/dirigible-xsk:0.0.1-keycloak . -f Dockerfile-keycloak

    docker build -t dirigiblelabs/dirigible-xsk:0.0.1-application . -f Dockerfile-application

    docker build -t dirigiblelabs/dirigible-xsk:0.0.1-application-keycloak . -f Dockerfile-application-keycloak

### How to run

#### Local database

    docker run -p 8888:8080 dirigiblelabs/dirigible-xsk:0.0.1

#### Remote HANA Cloud instance

    docker run -p 8888:8080 dirigiblelabs/dirigible-xsk:0.0.1 \
    -e DIRIGIBLE_DATABASE_PROVIDER=custom \
    -e DIRIGIBLE_DATABASE_CUSTOM_DATASOURCES=HANA \
    -e DIRIGIBLE_DATABASE_DATASOURCE_NAME_DEFAULT=HANA \
    -e HANA_DRIVER=com.sap.db.jdbc.Driver \
    -e HANA_URL=jdbc:sap://<uid>.hana.prod-eu10.hanacloud.ondemand.com:443/?encrypt=true\&validateCertificate=false \
    -e HANA_USERNAME=DBADMIN \
    -e HANA_PASSWORD=<password> \
    -e DIRIGIBLE_SCHEDULER_DATABASE_DRIVER=com.sap.db.jdbc.Driver \
    -e DIRIGIBLE_SCHEDULER_DATABASE_URL=jdbc:sap://<uid>.hana.prod-eu10.hanacloud.ondemand.com:443/?encrypt=true\&validateCertificate=false \
    -e DIRIGIBLE_SCHEDULER_DATABASE_USER=DBADMIN \
    -e DIRIGIBLE_SCHEDULER_DATABASE_PASSWORD=<password> \
    -e DIRIGIBLE_MESSAGING_USE_DEFAULT_DATABASE=false \
    -e DIRIGIBLE_FLOWABLE_USE_DEFAULT_DATABASE=false \
    -e DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE=true

#### With persistent volume

    docker run -p 8888:8080 -v <your-local-directory>:/usr/local/tomcat/target dirigiblelabs/dirigible-xsk:0.0.1
    
#### Go to:

> http://localhost:8888

### How to push on Docker Hub

    docker login
    
    docker push dirigiblelabs/dirigible-xsk

    docker push dirigiblelabs/dirigible-xsk:0.0.1-keycloak

    docker push dirigiblelabs/dirigible-xsk:0.0.1-application

    docker push dirigiblelabs/dirigible-xsk:0.0.1-application-keycloak
    
    
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


