---
title: Helm
---

Setup with Helm
===

## Overview
---

You can deploy XSK via Helm chart in a Kubernetes cluster.

!!! info "Prerequisites"
    - Install the [Helm CLI](https://helm.sh/docs/intro/install/#helm).
    - Make sure you have access to a [Kubernetes Cluster](https://kubernetes.io/docs/setup/).

## Steps
---

1. Add the XSK Helm repository:

    ```
    helm repo add xsk https://www.xsk.io

    helm repo update
    ```

1. Deployment

    To deploy XSK, you can either use the default or the Kyma setup instructions:

    #### Default Setup

    ```
    helm install xsk xsk/xsk
    ```

    ???+ info "Accessing the XSK Instance"
        Running this command will install XSK Deployment and Service with ClusterIP only.
        To access the XSK instance, execute the command that was printed in the console.

        Example:

        ```
        export POD_NAME=$(kubectl get pods --namespace default -l "app.kubernetes.io/name=xsk,app.kubernetes.io/instance=xsk" -o jsonpath="{.items[0].metadata.name}")
        echo "Visit http://127.0.0.1:8080 to use your application"
        kubectl --namespace default port-forward $POD_NAME 8080:8080    
        ```

        - Navigate to: [http://127.0.0.1:8080](http://127.0.0.1:8080)
        - Log in with these username and password: `dirigible`/`dirigible`

    #### Setup on Kyma

    You can choose to set up XSK in Helm on Kyma following either the basic or the PostgreSQL setup instructions:

    === "Basic"

        ```
        helm install xsk xsk/xsk \
        --set kyma.enabled=true \
        --set kyma.host=<kyma-host>
        ```

        This will install additionally an ApiRule and XSUAA ServiceInstance and ServiceBinding. The appropriate roles should be assigned to the user.
        
        ???+ info "Configuration Options"
            You can also use other configuration parameters. See [List of All Configurable Parameters and Their Values](#list-of-all-configurable-parameters-and-their-values).

    === "SAP HANA database"

        ```
        helm install xsk xsk \
        --set hana.enabled=true \
        --set hana.url=<hana-url> \
        --set hana.username=<hana-username> \
        --set hana.password=<hana-password> \
        --set kyma.enabled=true \
        --set kyma.host=<kyma-apirule-host>
        ```

        This will install additionally an ApiRule, XSUAA ServiceInstance, ServiceBinding and HANA instance. The appropriate roles should be assigned to the user.
        
        ???+ info "Configuration Options"
            You can also use other configuration parameters. See [List of All Configurable Parameters and Their Values](#list-of-all-configurable-parameters-and-their-values).


    === "PostgreSQL"

        ```
        helm install xsk xsk/xsk \
        --set kyma.enabled=true \
        --set kyma.host=<kyma-host> \
        --set database.enabled=true
        ```

        This will install also PostgreSQL database with 1Gi storage and update the XSK datasource configuration to consume the database.
        
        ???+ info "Configuration Options"
            You can also use other configuration parameters. See [List of All Configurable Parameters and Their Values](#list-of-all-configurable-parameters-and-their-values).


## Next Steps
---

- If you want to uninstall Helm, run:
    ```
    helm uninstall xsk
    ```

## Configuration Options for xsk chart
---

The following table lists all the configurable parameters exposed by the XSK chart and their default values:

#### List of All Configurable Parameters and Their Values

|             Name             |          Description            |            Default                 |
|------------------------------|---------------------------------|------------------------------------|
| `application.image`          | XSK image repo                  | `dirigiblelabs/xsk`                |
| `image.pullPolicy`           | Image pull policy               | `Always`                           |
| `replicaCount`               | Number of replicas              | `1`                                |
| `homeUrl`                    | Application Home url            | `false`                            |
| `fastBootstrap`              | Fast Boostrap secret            | `false`                            |


## Kpack Setup

You can choose to set up your application source with Helm and Kpack in XSK following setup instructions:

1. Add the XSK kpack Helm repository:

    ```
    helm repo add xsk https://www.xsk.io

    helm repo update
    ```

2. Deployment

    If you don't have kpack you can install with https://github.com/pivotal/kpack/blob/main/docs/install.md
    
    === "Kpack"

        ```
        helm install xsk-kpack xsk/xsk-kpack \
        --set install.clusterBuilder=true \
        --set install.imageBuilder=true \
        --set create.image=true \
        --set docker.server=https://index.docker.io/v1/ \
        --set docker.username=<your-docker-username> \
        --set docker.password=<your-docker-password> \
        --set docker.email=<your-email> \
        --set image.repository=<your-repository-for-your-OCI-image> \
        --set imageBuilder.repository=<builder-image> \
        --set image.source=<your-application-source>
        ```

        This will build your application source with Kpack and XSK and place your source code in repository/public/.

        You can tail the logs for your image that is currently building using the [kp cli](https://github.com/vmware-tanzu/kpack-cli/blob/main/docs/kp_build_logs.md).

        `kp build logs xsk-kpack -n default`

        You can check your image with

        `kubectl -n default get image xsk-kpack`

        and download the OCI image and run it with docker

        docker run -p 8080:8080 <latest-image-with-digest>

        ???+ info "Configuration Options"
            You can also use other configuration parameters. See [List of All Configurable Parameters and Their Values](#list-of-all-configurable-parameters-and-their-values-for-xsk-kpack-chart).

## Configuration Options for xsk-kpack chart
---

The following table lists all the configurable parameters exposed by the xsk-kpack chart and their default values:

#### List of All Configurable Parameters and Their Values for XSK kpack chart

|             Name                    |          Description            |            Default                                 |
|-------------------------------------|---------------------------------|----------------------------------------------------|
| `replicaCount`                      | Number instanof replicas        | `1`                                                |
| `install.clusterBuilder`            | Kpack cluster store and stack   | `false`                                            |
| `install.imageBuilder`              | Kpack builder                   | `false`                                            |
| `create.image`                      | Kpack create OCI image          | `false`                                            |
| `fastBootstrap`                     | Fast Boostrap secret            | `false`                                            |
| `docker.server`                     | Docker server url               | ``                                                 |
| `docker.username`                   | Docker username                 | ``                                                 |
| `docker.password`                   | Docker password                 | ``                                                 |
| `docker.email`                      | Docker email                    | ``                                                 |
| `docker.secretName`                 | Docker secret name              | `docker-registry-secret`                           |
| `docker.serviceAccountName`         | Docker service account name     | `docker-registry-service-account`                  |
| `imageBuilder.repository`           | Docker service account name     | ``                                                 |
| `imageBuilder.buildpack`            | Docker service account name     | `dirigiblelabs/buildpacks-xsk`                     |
| `image.repository`                  | Docker service account name     | ``                                                 |
| `image.source`                      | Docker service account name     | ``                                                 |
| `image.serviceAccountName`          | Docker service account name     | `dirigiblelabs/buildpacks-stack-build-xsk:latest`  |
| `clusterBuilder.buildImage`         | Docker service account name     | `docker-registry-service-account`                  |
| `clusterBuilder.runImage`           | Docker service account name     | `docker-registry-service-account`                  |
| `clusterBuilder.serviceAccountName` | Docker service account name     | `docker-registry-service-account`                  |
