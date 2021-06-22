### Manual Build

1. Build `Kneo XSK Stack`:

    ```
    docker build -t dirigiblelabs/kneo-xsk-stack-base . --target base
    docker push dirigiblelabs/kneo-xsk-stack-base

    docker build -t dirigiblelabs/kneo-xsk-stack-run . --target run
    docker push dirigiblelabs/kneo-xsk-stack-run

    docker build -t dirigiblelabs/kneo-xsk-stack-build . --target build
    docker push dirigiblelabs/kneo-xsk-stack-build
    ```

1. Build `Kneo Buildpack`:

    ```
    cd buildpack/

    pack buildpack package dirigiblelabs/kneo-xsk-buildpack --config ./package.toml
    docker push dirigiblelabs/kneo-xsk-buildpack
    ```

### Kpack Installation

1. [Install Pack](https://buildpacks.io/docs/tools/pack/#install)
1. [Install Kpack](https://github.com/pivotal/kpack/blob/main/docs/install.md)
1. [Install logging tool](https://github.com/pivotal/kpack/blob/main/docs/logs.md)
1. Create Docker Registry Secret:
    ```
    kubectl create secret docker-registry docker-registry-secret \
        --docker-username=<your-username> \
        --docker-password=<your-password> \
        --docker-server=https://index.docker.io/v1/ \
        --namespace default
    ```


1. Create Service Account
    ```
    kubectl apply -f service-account.yaml
    ```


1. Create `ClusterStore`, `ClusterStack` and `Builder`:

    ```
    cd kpack/

    kubectl apply -f kpack.yaml
    ```

### Image Building

1. Create Image:

    ```yaml
    apiVersion: kpack.io/v1alpha1
    kind: Image
    metadata:
      name: xsk-application
      namespace: default
    spec:
      tag: dirigiblelabs/xsk-application
      serviceAccount: docker-registry-service-account
      builder:
        name: kneo-xsk
        kind: Builder
      source:
        blob:
          url: https://github.com/SAP/xsk/raw/main/samples/xsjs-simple.zip
    ```

1. Monitor Logs:

    ```
    logs -image xsk-application -namespace default
    ```
### Image Building with different version

1. Change the version for ClusterStore, ClusterStack, Builder

```yaml
apiVersion: kpack.io/v1alpha1
kind: ClusterStore
metadata:
  name: kneo-xsk-store
spec:
  sources:
  - image: dirigiblelabs/kneo-xsk-buildpack:<tag>
---
apiVersion: kpack.io/v1alpha1
kind: ClusterStack
metadata:
  name: kneo-xsk-stack
spec:
  id: "com.sap.kneo.xsk"
  buildImage:
    image: "dirigiblelabs/kneo-xsk-stack-build:<tag>"
  runImage:
    image: "dirigiblelabs/kneo-xsk-stack-run:<tag>"
---
apiVersion: kpack.io/v1alpha1
kind: Builder
metadata:
  name: kneo-xsk
spec:
  serviceAccount: docker-registry-service-account
  tag: dirigiblelabs/kneo-xsk-builder:<tag>
  stack:
    name: kneo-xsk-stack
    kind: ClusterStack
  store:
    name: kneo-xsk-store
    kind: ClusterStore
  order:
  - group:
    - id: dirigiblelabs/kneo-xsk-buildpack
```
2. Change the version for the build image - add field _imageTaggingStrategy_

```yaml
    apiVersion: kpack.io/v1alpha1
    kind: Image
    metadata:
      name: xsk-application
      namespace: default
    spec:
      tag: dirigiblelabs/xsk-application:<tag>
      serviceAccount: docker-registry-service-account
      imageTaggingStrategy: <tag>
      builder:
        name: kneo-xsk
        kind: Builder
      source:
        blob:
          url: https://github.com/SAP/xsk/raw/main/samples/xsjs-simple.zip
    ```
