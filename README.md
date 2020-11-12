# project "XSK"

## Description

Compatible environment for SAP HANA Extended Application Services (XS) based applications outside of SAP HANA instance running in a container deployed on Kubernetes.

> Note: the project is not yet ready to be used productively

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
    -e DIRIGIBLE_FLOWABLE_USE_DEFAULT_DATABASE=false

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

## Configuration

### Environment Variables

* **XSK_HDI_SUPPORTED** - whether the HDI API is supported by the database (e.g. HANA). Default is *true*.
* **XSK_HDI_ONLY** - all the database models to be processed only via HDI API (no built-in processing). Default is *false*.

## Limitations

Not all of the XS classic artifacts are supported as well as not all the features of the supported artifacts are covered so far.

## Known Issues

* Hard coded URLs have to be manually changed
* Hard coded SCHEMA names within SQL statements have to be manually changed

## How to obtain support

All the bug reports as well as the feature requests have to be registered as issues.

## Contributing

If you want to contribute, please check the [Contribution Guidelines](CONTRIBUTING.md)


