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

1. Build `Kneo Stack`:

```
cd stack/

docker build . -t dirigiblelabs/kneo-java-stack-base --target base
docker push dirigiblelabs/kneo-java-stack-base

docker build . -t dirigiblelabs/kneo-java-stack-run --target run
docker push dirigiblelabs/kneo-java-stack-run

docker build . -t dirigiblelabs/kneo-java-stack-build --target build
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
        name: kneo-builder
        kind: Builder
    source:
        blob:
            url: https://github.com/SAP/xsk/raw/main/modules/basis/basis-datasource/samples/test_db.war
```

Monitor Logs:

```
logs -image xsk-application -namespace default
```

After the successful build execute 
```bash
kubectl get images
```
and extract the image name and tag, then deploy it on Kyma with this yml
```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: dynamic-db
  namespace: default
spec:
  replicas: 1
  selector:
    matchLabels:
      app: dynamic-db
  template:
    metadata:
      labels:
        app: dynamic-db
    spec:
      containers:
        - name: dynamic-db
          image: <image-from-kpack>
          env:
            - name: DefaultDB_url
              value: jdbc:sap://<hana-db-url>/<your-db>
            - name: DefaultDB_username
              value: <db-username> # It is DBADMIN by default on HANA
            - name: DefaultDB_password
              value: <db-password>
            - name: DefaultDB_driverClassName
              value: com.sap.db.jdbc.Driver
            - name: DefaultDB_maxWait
              value: "10000"
            - name: DefaultDB_maxIdle
              value: "30"
            - name: DefaultDB_maxActive
              value: "100"
          ports:
            - containerPort: 8080
              name: dynamic-db
---
apiVersion: v1
kind: Service
metadata:
  name: dynamic-db
  namespace: default
  labels:
    app: dynamic-db
spec:
  ports:
    - port: 8080
      name: dynamic-db
  selector:
    app: dynamic-db

```
