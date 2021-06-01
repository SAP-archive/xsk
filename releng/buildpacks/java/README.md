### Prerequisites

1. [Install Pack](https://buildpacks.io/docs/tools/pack/#install)
2. [Install Kpack](https://github.com/pivotal/kpack/blob/main/docs/install.md)
3. [Install logging tool](https://github.com/pivotal/kpack/blob/main/docs/logs.md)

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

1. Build `Kneo XSK Stack`:

```
cd stack/

docker build . -t dirigiblelabs/neo-stack-base --target base
docker push dirigiblelabs/neo-stack-base

docker build . -t dirigiblelabs/neo-stack-run --target run
docker push dirigiblelabs/neo-stack-run

docker build . -t dirigiblelabs/neo-stack-build --target build
docker push dirigiblelabs/neo-stack-build
```

1. Build `Kneo Buildpack`:

```
cd buildpack/

pack buildpack package dirigiblelabs/neo-buildpack --config ./package.toml
docker push dirigiblelabs/neo-buildpack
```

1. Create `ClusterStore`, `ClusterStack` and `Builder`:

```
cd kpack/

kubectl apply -f kpack.yaml
```

1. Create Image:

```yaml
apiVersion: kpack.io/v1alpha1
kind: Image
metadata:
    name: tutorial-image
    namespace: default
spec:
    tag: dirigiblelabs/neo-test
    serviceAccount: tutorial-service-account
    builder:
        name: neo-builder
        kind: Builder
    source:
        blob:
            url: https://github.com/SAP/xsk/raw/main/modules/basis/basis-datasource/samples/test_db.war
```

1. Monitor Logs:

```
logs -image xsk-application -namespace default
```