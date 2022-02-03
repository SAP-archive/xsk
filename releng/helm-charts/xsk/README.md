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

### Basic:

```
helm install xsk xsk/xsk
```
Running this command will install XSK Deployment and Service with ClusterIP only. To access the XSK instance, execute the command that was printed in the console.

Example:

```
export POD_NAME=$(kubectl get pods --namespace default -l "app.kubernetes.io/name=xsk,app.kubernetes.io/instance=xsk" -o jsonpath="{.items[0].metadata.name}")
echo "Visit http://127.0.0.1:8080 to use your application"
kubectl --namespace default port-forward $POD_NAME 8080:8080    
```
* Navigate to: http://127.0.0.1:8080
* Log in with these username and password: dirigible/dirigible

### Kyma:
helm install xsk xsk/xsk \
--set kyma.enabled=true \
--set kyma.host=<kyma-host>


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
1. Set the XSK version in `xsk/Chart.yaml`:

    > Replace the `#{XSKVersion}#` placeholder.

1. Package Helm Chart and sign with gpg key:

    Obtain gpg key for XSK and convert keyring to the legacy gpg format.

    `gpg --export-secret-keys > ~/.gnupg/secring.gpg`

    Package with gpg key.

    `helm package --sign --key 'XSK-gpg' --keyring ~/.gnupg/secring.gpg <chart>`

    Verify the package.

    helm verify --keyring ~/.gnupg/pubring.gpg xsk-<version>.tgz

1. Copy the `xsk-<chart>-<version>.tgz` and `xsk-<chart>-<version>.tgz.prov` somewhere outside the Git repository.

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

1. Paste the `xsk-<chart>-<version>.tgz` and `xsk-<chart>-<version>.tgz.prov` chart into the `charts` directory.

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