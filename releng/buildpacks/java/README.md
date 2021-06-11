### Prerequisites

1. [Install Pack](https://buildpacks.io/docs/tools/pack/#install)
1. [Install Kpack](https://github.com/pivotal/kpack/blob/main/docs/install.md)
1. [Install logging tool](https://github.com/pivotal/kpack/blob/main/docs/logs.md)

1. Create Docker Registry Secret
    ```
    kubectl create secret docker-registry tutorial-registry-credentials \
        --docker-username=<your-username> \
        --docker-password=<your-password> \
        --docker-server=https://index.docker.io/v1/ \
        --namespace default
    ```


1. Create Service Account
    ```
    kubectl apply -f service-account.yaml
    ```

1. Build `Kneo Java Stack`:

    ```
    docker build -t dirigiblelabs/kneo-java-stack-base . --target base
    docker push dirigiblelabs/kneo-java-stack-base

    docker build -t dirigiblelabs/kneo-java-stack-run . --target run
    docker push dirigiblelabs/kneo-java-stack-run

    docker build -t dirigiblelabs/kneo-java-stack-build . --target build
    docker push dirigiblelabs/kneo-java-stack-build
    ```

1. Build `Kneo Buildpack`:

    ```
    cd buildpack/

    pack buildpack package dirigiblelabs/kneo-java-buildpack --config ./package.toml
    docker push dirigiblelabs/kneo-java-buildpack
    ```

1. Create `ClusterStore`, `ClusterStack` and `Builder`:

    ```
    cd kpack/

    kubectl apply -f kpack.yaml
    ```

1. Create Image:
Prerequisite is having hanadb from CF.

    ```yaml
    apiVersion: kpack.io/v1alpha1
    kind: Image
    metadata:
      name: java-application
      namespace: default
    spec:
      tag: dirigiblelabs/java-application
      serviceAccount: tutorial-service-account
      builder:
        name: kneo-java
        kind: Builder
      source:
        blob:
          url: https://github.com/SAP/xsk/raw/main/modules/basis/basis-datasource/samples/test_db.war
    ```

1. Monitor Logs:

    ```
    logs -image java-application -namespace default
    ```
