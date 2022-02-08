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

1. Package Helm Chart and sign with gpg key:

    Obtain gpg key for XSK and convert keyring to the legacy gpg format.

    `gpg --export-secret-keys > ~/.gnupg/secring.gpg`

    Package with gpg key.

    `helm package --sign --key 'XSK-gpg' --keyring ~/.gnupg/secring.gpg <chart>`

    Verify the package.

    helm verify --keyring ~/.gnupg/pubring.gpg `xsk-<version>.tgz`

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
