# XSK

[XSK](https://github.com/SAP/xsk) is compatible environment for SAP HANA Extended Application Services (XS) based applications outside of SAP HANA instance running in a container deployed on Kubernetes

Add XSK Helm repo:

```console
helm repo add xsk https://sap.github.io/xsk
```

Update repo:

```console
helm repo update
```

Install XSK with Helm:

```console
helm install xsk xsk/xsk
```

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

1. Package Helm Chart:

    ```
    helm package xsk
    ```

1. Copy the `xsk-1.0.0.tgz` somewhere outside the Git repository.

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

1. Paste the `xsk-1.0.0.tgz` chart into the `charts` directory.

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
