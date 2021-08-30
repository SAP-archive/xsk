---
title: Local
---

Local Setup
===


You can deploy XSK locally using Docker.

## Prerequisites

Install [Docker](https://docs.docker.com/engine/installation/).


## Steps
---
      
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
        --rm -p 8080:8080 -p 8081:8081 \
        -v <your-local-directory>:/usr/local/tomcat/target \
        dirigiblelabs/xsk:latest
        ```

    === "with HANA Cloud"

        ```
        docker run --name xsk \
        --rm -p 8080:8080 -p 8081:8081 \
        dirigiblelabs/xsk:latest \
        -e DIRIGIBLE_DATABASE_PROVIDER=custom \
        -e DIRIGIBLE_DATABASE_CUSTOM_DATASOURCES=HANA \
        -e DIRIGIBLE_DATABASE_DATASOURCE_NAME_DEFAULT=HANA \
        -e HANA_DRIVER=com.sap.db.jdbc.Driver \
        -e HANA_URL=jdbc:sap://<hanaHost>?encrypt=true&validateCertificate=true \
        -e HANA_USERNAME=<hanaUsername> \
        -e HANA_PASSWORD=<hanaPassword>
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
