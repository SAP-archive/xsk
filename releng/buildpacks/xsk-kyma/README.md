## XSK Kyma Buildpack

1. Set the XSK version:
    > Replace the `#{XSKVersion}#` placeholder (e.g. `latest`, `0.7.1`, `1.0.0`) in `buildpack/*.toml` files.

1. Build `XSK Kyma Stack`:

    ```
    docker build -t dirigiblelabs/buildpacks-stack-base-xsk-kyma . --target base
    docker push dirigiblelabs/buildpacks-stack-base-xsk-kyma

    docker build -t dirigiblelabs/buildpacks-stack-run-xsk-kyma . --target run
    docker push dirigiblelabs/buildpacks-stack-run-xsk-kyma

    docker build -t dirigiblelabs/buildpacks-stack-build-xsk-kyma . --target build
    docker push dirigiblelabs/buildpacks-stack-build-xsk-kyma
    ```

1. Build `XSK Kyma Buildpack`:

    ```
    cd buildpack/

    pack buildpack package dirigiblelabs/buildpacks-xsk-kyma --config ./package.toml
    docker push dirigiblelabs/buildpacks-xsk-kyma

    pack builder create dirigiblelabs/buildpacks-builder-xsk-kyma --config ./builder.toml
    docker push dirigiblelabs/buildpacks-builder-xsk-kyma
    ```

1. Usage with `pack`:

    ```
    pack build --builder dirigiblelabs/buildpacks-builder-xsk-kyma <my-org>/<my-repository>
    ```

## Kpack Installation

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
    kubectl apply -f kpack.yaml
    ```
    
    > _**Note:** Before creating the Kpack resources, replace the **`<tag>`** placeholder with a valid XSK version (e.g. 0.5.0, 0.6.0, ...). All available XSK versions could be found [here](https://github.com/SAP/xsk/releases) and the respective Docker images [here](https://hub.docker.com/r/dirigiblelabs/kneo-xsk-buildpack/tags?page=1&ordering=last_updated)._

## Image Building

1. Create Image:

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
        name: xsk-builder
        kind: Builder
      source:
        blob:
          url: https://github.com/SAP/xsk/raw/main/samples/xsjs-simple.zip
    ```

    > _**Note:** Replace the **`<tag>`** placeholder with your Docker image tag._

1. Monitor Logs:

    ```
    logs -image xsk-application -namespace default
    ```
