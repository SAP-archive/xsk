---
title: Local
---

Local Setup
===

## Overview
---

You can deploy XSK locally using Docker or Tomcat server.


## Steps
---

1. Deploy as a Docker container or on Tomcat server.

    === "Docker"

        !!! info "Prerequisites"
            Install [Docker](https://docs.docker.com/engine/installation/).

        1. Pull the XSK Docker image:

            ```
            docker pull dirigiblelabs/xsk:latest
            ```

        1. Start the container:

        === "Run"

            ```
            docker run --name xsk \
            --rm -p 8080:8080 -p 8081:8081 \
            dirigiblelabs/xsk:latest
            ```

        === "with Mounted Volume"

            ```
            docker run --name xsk \
            -v <your-local-directory>:/usr/local/tomcat/target \
            --rm -p 8080:8080 -p 8081:8081 \
            dirigiblelabs/xsk:latest
            ```

        === "with HANA Cloud"

            ```
            docker run --name xsk \
            -e DIRIGIBLE_DATABASE_PROVIDER="custom" \
            -e DIRIGIBLE_DATABASE_CUSTOM_DATASOURCES="HANA" \
            -e DIRIGIBLE_DATABASE_DATASOURCE_NAME_DEFAULT="HANA" \
            -e HANA_DRIVER="com.sap.db.jdbc.Driver" \
            -e HANA_URL="jdbc:sap://<hanaHost>?encrypt=true&validateCertificate=true" \
            -e HANA_USERNAME="<hanaUsername>" \
            -e HANA_PASSWORD="<hanaPassword>" \
            --rm -p 8080:8080 -p 8081:8081 \
            dirigiblelabs/xsk:latest
            ```

            !!! Note
                - Replace the `<hanaHost>` placeholder with the HANA Cloud host that you're going to use.
                - Replace the `<hanaUsername>` placeholder with the HANA Cloud username that you're going to use.
                - Replace the `<hanaPassword>` placeholder with the HANA Cloud password that you're going to use.

            !!! info "Windows"
                For setup on Windows OS, issues may appear with the way the environment variables _(`-e XXX=YYY`)_ are provided. Either they should be properly escaped, or they could be supplied as `*.env` [file](https://docs.docker.com/compose/environment-variables/):
            
                ```
                docker run --name xsk \
                --env-file env-variables.env \
                --rm -p 8080:8080 -p 8081:8081 \
                dirigiblelabs/xsk:latest
                ```

    === "Tomcat"

        !!! info "Prerequisites"
            - Java 11 or Java 13 installed (default, required during runtime).
            - Java 8 JRE/JDK installed (additional, required for compatability reasons).
            

        1. Download `ROOT.war` for Tomcat from: [https://github.com/SAP/xsk/releases/latest](https://github.com/SAP/xsk/releases/latest)

            !!! Note
                    For local test & development purposes, we recommend the **server** distribution.

        1. Configure the Users store under `$CATALINA_HOME/conf/tomcat-users.xml`:

            ```xml
            <tomcat-users>
            	<role rolename="Developer"/>
            	<role rolename="Operator"/>
            	<role rolename="Everyone"/>
            	<user username="dirigible" password="dirigible" roles="Developer,Operator,Everyone"/>
            </tomcat-users>
            ```

        1. Copy the XSK's `ROOT.war` to `$TOMCAT/webapps` folder.

        1. Provide the Java 8 JRE/JDK path.

            ```
            export JAVA8_HOME=<pathToJava8>
            ```

            !!! Note
                Replace the `<pathToJava8>` placeholder with the actual path to your Java 8 installation _(e.g. `/usr/lib/jvm/java-8-openjdk-amd64/`)_.

        1. Configure connection to HANA Cloud instance.

            ```
            export HANA_DRIVER=com.sap.db.jdbc.Driver
            export HANA_URL=jdbc:sap://<hanaHost>?encrypt=true&validateCertificate=true
            export HANA_USERNAME=<hanaUsername>
            export HANA_PASSWORD=<hanaPassword>
            ```

            !!! Note
                - Replace the `<hanaHost>` placeholder with the HANA Cloud host that you're going to use.
                - Replace the `<hanaUsername>` placeholder with the HANA Cloud username that you're going to use.
                - Replace the `<hanaPassword>` placeholder with the HANA Cloud password that you're going to use.

        1. Start the Tomcat server.

    !!! tip "XSK versions"
        Instead of using the `latest` tag (version), for production and development use cases it is recommended that you use a stable release version:
        
        - You can find all released versions [here](https://github.com/sap/xsk/releases/).
        - You can find all XSK Docker images and tags (versions) [here](https://hub.docker.com/u/dirigiblelabs).


1. Open a web browser and go to: [http://localhost:8080/](http://localhost:8080/)

    !!! Note
        The default user name and password are **`dirigible/dirigible`**.

1. Stop the container:

    ```
    docker stop dirigible
    ```
