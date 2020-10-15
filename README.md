# xsk
Compatible environment for SAP HANA Extended Application Services (XS) based applications outside of SAP HANA instance running in a container deployed on Kubernetes


## How to build

    mvn clean install
    
### Build Docker images

    docker build -t dirigiblelabs/dirigible-xsk:0.0.1 .
    
    docker build -t dirigiblelabs/dirigible-xsk:0.0.1-keycloak . -f Dockerfile-keycloak

    docker build -t dirigiblelabs/dirigible-xsk:0.0.1-application . -f Dockerfile-application

    docker build -t dirigiblelabs/dirigible-xsk:0.0.1-application-keycloak . -f Dockerfile-application-keycloak

## How to run

    docker run -p 8888:8080 dirigiblelabs/dirigible-xsk:0.0.1

With persistent volume

    docker run -p 8888:8080 -v <your-local-directory>:/usr/local/tomcat/target dirigiblelabs/dirigible-xsk:0.0.1
    
Go to:

> http://localhost:8888

## How to push on Docker Hub

    docker login
    
    docker push dirigiblelabs/dirigible-xsk

    docker push dirigiblelabs/dirigible-xsk:0.0.1-keycloak

    docker push dirigiblelabs/dirigible-xsk:0.0.1-application

    docker push dirigiblelabs/dirigible-xsk:0.0.1-application-keycloak


## Development Environment Details

API Root

> https://github.com/SAP/xsk/blob/master/xsk/modules/api/src/main/resources/xsk/api.js

Sample API

> https://github.com/SAP/xsk/blob/master/xsk/modules/api/src/main/resources/xsk/response.js

Sample Test

> https://github.com/SAP/xsk/blob/master/xsk/core/java/src/test/resources/test/xsk/response.xsjs

Adding to the Tests Suite

> https://github.com/SAP/xsk/blob/master/xsk/core/java/src/test/java/com/sap/xsk/engine/api/test/XSKApiSuiteTest.java

