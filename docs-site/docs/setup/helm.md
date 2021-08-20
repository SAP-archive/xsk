---
title: Helm
---

Setup with Helm
===


You can deploy XSK via Helm Chart in a Kubernetes cluster.

!!! info "Prerequisites"
    - [Helm](https://helm.sh/)
    - [Kubernetes Cluster](https://kubernetes.io/docs/setup/pick-right-solution/) on IaaS provider of your choice

## Steps
---

1. Add the XSK Helm repository:

    ```
    helm repo add xsk https://www.xsk.io

    helm repo update
    ```

1. Basic:

    ```
    helm install xsk xsk/xsk
    ```

    ???+ info "Access"
        This will install XSK Deployment and Service with ClusterIP only.
        To access the XSK instance execute the command that was printed in the console.

        Example:

        ```
        export POD_NAME=$(kubectl get pods --namespace default -l "app.kubernetes.io/name=xsk,app.kubernetes.io/instance=xsk" -o jsonpath="{.items[0].metadata.name}")
        echo "Visit http://127.0.0.1:8080 to use your application"
        kubectl --namespace default port-forward $POD_NAME 8080:8080    
        ```

        - Navigate to: [http://127.0.0.1:8080](http://127.0.0.1:8080)
        - Login with: `dirigible`/`dirigible`

1. Kyma:

    === "Basic"

        ```
        helm install xsk xsk/xsk \
        --set kyma.enabled=true \
        --set kyma.apirule.host=<kyma-host>
        ```

        This will install additionally an ApiRule and XSUAA ServiceInstance and ServiceBinding. The appropriate roles should be assigned to the user.

    === "PostgreSQL"

        ```
        helm install xsk xsk/xsk \
        --set kyma.enabled=true \
        --set kyma.apirule.host=<kyma-host> \
        --set database.enabled=true
        ```

        This will install also PostgreSQL database with 1Gi storage and update the XSK datasource configuration to consume the database.

1. Uninstall:
    ```
    helm uninstall xsk
    ```

Configuration
---

The following table lists all the configurable parameters expose by the XSK chart and their default values.

#### Generic

|             Name             |          Description            |            Default                 |
|------------------------------|---------------------------------|------------------------------------|
| `dirigible.image`            | Custom Dirigible image          | `""`                               |
| `image.repository`           | Dirigible image repo            | `dirigiblelabs/dirigible-all`      |
| `image.repositoryKyma`       | Dirigible Kyma image repo       | `dirigiblelabs/dirigible-sap-kyma` |
| `image.repositoryKeycloak`   | Dirigible Keycloak image repo   | `dirigiblelabs/dirigible-keycloak` |
| `image.pullPolicy`           | Image pull policy               | `IfNotPresent`                     |
| `service.type`               | Service type                    | `ClusterIP`                        |
| `service.port`               | Service port                    | `8080`                             |
| `replicaCount`               | Number of replicas              | `1`                                |
| `imagePullSecrets`           | Image pull secrets              | `[]`                               |
| `nameOverride`               | Name override                   | `""`                               |
| `fullnameOverride`           | Fullname override               | `""`                               |
| `podSecurityContext`         | Pod security context            | `{}`                               |
| `nodeSelector`               | Node selector                   | `{}`                               |
| `tolerations`                | Tolerations                     | `[]`                               |
| `affinity`                   | Affinity                        | `{}`                               |
| `resources`                  | Resources                       | `{}`                               |
