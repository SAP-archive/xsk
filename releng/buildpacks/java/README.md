### Prerequisites

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

1. Kpack installation

1. Build `Kneo XSK Stack`:

```
cd stack/

docker build . -t dirigiblelabs/kneo-xsk-stack-base --target base
docker push dirigiblelabs/kneo-xsk-stack-base

docker build . -t dirigiblelabs/kneo-xsk-stack-run --target run
docker push dirigiblelabs/kneo-xsk-stack-run

docker build . -t dirigiblelabs/kneo-xsk-stack-build --target build
docker push dirigiblelabs/kneo-xsk-stack-build
```

1. Build `Kneo Buildpack`:

```
cd buildpack/

pack buildpack package dirigiblelabs/kneo-xsk-buildpack --config ./package.toml
docker push dirigiblelabs/kneo-xsk-buildpack
```

1. Create `ClusterStore`, `ClusterStack` and `Builder`:

```
cd kpack/

kubectl apply -f store.yaml
kubectl apply -f stack.yaml
kubectl apply -f builder.yaml
```

1. Create Image:

```yaml
apiVersion: kpack.io/v1alpha1
kind: Image
metadata:
  name: xsk-application
  namespace: default
spec:
  tag: dirigiblelabs/xsk-application
  serviceAccount: tutorial-service-account
  builder:
    name: kneo-xsk
    kind: Builder
  source:
    blob:
      url: https://github.com/eclipse/dirigible/releases/download/v5.10.0/server-all.zip
```

1. Monitor Logs:

```
logs -image tutorial-image -namespace default
```