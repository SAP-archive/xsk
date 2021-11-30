# XSK

[XSK](https://github.com/sap/xsk) is compatible environment for SAP HANA Extended Application Services (XS) based applications outside of SAP HANA instance running in a container deployed on Kubernetes.

## Overview

This chart bootstraps a [XSK](https://github.com/sap/xsk) deployment on a [Kubernetes](http://kubernetes.io) cluster using the [Helm](https://helm.sh) package manager.

#### Prerequisites

- Kubernetes 1.19+
- Helm 3+

#### Setup

Add the XSK chart repository:

```
helm repo add xsk https://sap.github.io/xsk
helm repo update
```

## Deployment

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
Running this command will build your application source code with Kpack and XSK and place your source code in repository/public/.

You can tail the logs for your image that is currently building using the kp cli.

`kp build logs xsk-kpack -n default`

You can check your image with

`kubectl -n default get image xsk-kpack`

and download the OCI image and run it with docker

`docker run -p 8080:8080 <latest-image-with-digest>`

Resources:
- [XSK](https://github.com/SAP/xsk)
- [dirigible.io](https://www.dirigible.io)
- [github.com/eclipse/dirigible](https://github.com/eclipse/dirigible)
- [youtube.com/c/dirigibleio](https://www.youtube.com/c/dirigibleio)


## Manual Helm Charts Update:

1. Navigate to the `helm-chart` folder:
    ```
    cd releng/helm-charts/
    ```
1. Set the XSK version in `xsk-kpack/Chart.yaml`, `xsk-kpack/Chart.yaml`, `xsk-kpack/templates/kpack.yaml`:

    > Replace the `#{XSKVersion}#` placeholder.

1. Package Helm Chart:

    ```
    helm package xsk-kpack
    ```

1. Copy the `xsk-kpack-0.11.0.tgz` somewhere outside the Git repository.

1. Reset all changes:

    ```
    git add .
    git reset --hard
    cd ../../
    ```

1. Switch to the `gh-pages` branch:

    ```
    git checkout gh-pages
    git pull origin gh-pages
    ```

1. Paste the `xsk-kpack-0.11.0.tgz` chart into the `charts` directory.

1. Build Helm Index:

    ```
    helm repo index charts/ --url https://sap.github.io/xsk/charts
    ```

1. Move the `charts/index.yaml` to the root folder:

    ```
    mv charts/index.yaml .
    ```

1. Push the changes:

    ```
    git add index.yaml
    git add charts/

    git commit -m "Helm Charts Updated"

    git push origin gh-pages
    ```