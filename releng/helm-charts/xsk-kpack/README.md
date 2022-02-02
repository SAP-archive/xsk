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

You can use for build Kyma (which is default), local and Cloud Foundry.
- For ClusterStack build image you can use `--set clusterBuilder.buildImage` and choose one of this:

`dirigiblelabs/buildpacks-stack-build-xsk-kyma` - Kyma
`dirigiblelabs/buildpacks-stack-build-xsk-cf` - Cloud Foundry
`dirigiblelabs/buildpacks-stack-build-xsk` - Local

- For ClusterStack run image you can use `--set clusterBuilder.runImage` and choose one of this:

`dirigiblelabs/buildpacks-stack-run-xsk-kyma` - Kyma
`dirigiblelabs/buildpacks-stack-run-xsk-cf` - Cloud Foundry
`dirigiblelabs/buildpacks-stack-run-xsk` - Local

- For Builder image you can use `--set imageBuilder.buildpack` and choose one of this:

`--set imageBuilder.buildpack=dirigiblelabs/buildpacks-xsk-kyma` - Kyma
`--set imageBuilder.buildpack=dirigiblelabs/buildpacks-xsk-cf` - Cloud Foundry
`--set imageBuilder.buildpack=dirigiblelabs/buildpacks-xsk` - Local

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

1. Package Helm Chart and sign with gpg key:

    Obtain gpg key for XSK and convert keyring to the legacy gpg format.

    `gpg --export-secret-keys > ~/.gnupg/secring.gpg`

    Package with gpg key.

    `helm package --sign --key 'XSK-gpg' --keyring ~/.gnupg/secring.gpg <chart>`

    Verify the package.

    helm verify --keyring ~/.gnupg/pubring.gpg xsk-<version>.tgz

2. Copy the `xsk-<chart>-<version>.tgz` and `xsk-<chart>-<version>.tgz.prov` somewhere outside the Git repository.

3. Reset all changes:

    ```
    git add .
    git reset --hard
    cd ../../
    ```

4. Switch to the `gh-pages` branch:

    ```
    git checkout gh-pages
    git pull origin gh-pages
    ```

5. Paste the `xsk-<chart>-<version>.tgz` and `xsk-<chart>-<version>.tgz.prov` chart into the `charts` directory.

6. Build Helm Index:

    ```
    helm repo index charts/ --url https://sap.github.io/xsk/charts
    ```

7. Move the `charts/index.yaml` to the root folder:

    ```
    mv charts/index.yaml .
    ```

8. Push the changes:

    ```
    git add index.yaml
    git add charts/

    git commit -m "Helm Charts Updated"

    git push origin gh-pages
    ```