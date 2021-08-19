---
title: Kyma
---

Setup in Kyma
===

Deploy XSK in SAP BTP[^1], Kyma environment.

[^1]: SAP Cloud Platform is called SAP Business Technology Platform (SAP BTP) as of 2021.

!!! info "Prerequisites"
    - Install [kubectl](https://kubernetes.io/docs/tasks/tools/install-kubectl/) - this step is optional.
    - Access to SAP BTP account _(the Trial landscape can be accessed [here](https://account.hanatrial.ondemand.com/))._

!!! warning "Warning"
    At the time of writing the setup manual _(19.08.2021)_, creation of HANA Cloud service instance in the SAP BTP Kyma environment was not possible, thus the setup is suitable only for **test** & **demo** purposes. Workaround solution could be found [here](https://github.com/SAP/xsk/discussions/394).

## Steps
---

1. Access the SAP BTP, Kyma environment via the SAP BTP cockpit:

1. Deploy XSK:

    - Copy and paste the following content into `deployment.yaml`:

        ```yaml
        apiVersion: apps/v1
        kind: Deployment
        metadata:
          name: xsk
          namespace: default
        spec:
          replicas: 1
          selector:
            matchLabels:
              app: xsk
          template:
            metadata:
              labels:
                app: xsk
            spec:
              containers:
              - name: xsk
                image: dirigiblelabs/xsk-kyma:latest
                imagePullPolicy: Always
                env:
                - name: DIRIGIBLE_THEME_DEFAULT
                  value: fiori
                - name: DIRIGIBLE_HOST
                  value: https://xsk.<your-kyma-cluster-host>
                volumeMounts:
                - name: dirigible-volume
                  mountPath: /usr/local/tomcat/target/dirigible/repository
                ports:
                - containerPort: 8080
                  name: dirigible
                  protocol: TCP
              volumes:
              - name: xsk-volume
                persistentVolumeClaim:
                  claimName: xsk-claim
        ---
        apiVersion: v1
        kind: Service
        metadata:
          labels:
            app: xsk
          name: xsk
          namespace: default
        spec:
          ports:
          - name: xsk
            port: 8080
            protocol: TCP
            targetPort: 8080
          selector:
            app: xsk
          type: ClusterIP
        ---
        apiVersion: v1
        kind: PersistentVolumeClaim
        metadata:
          name: xsk-claim
        spec:
          accessModes:
          - ReadWriteOnce
          resources:
            requests:
              storage: 1Gi
        ---
        apiVersion: gateway.kyma-project.io/v1alpha1
        kind: APIRule
        metadata:
          name: xsk
          namespace: default
        spec:
          gateway: kyma-gateway.kyma-system.svc.cluster.local
          rules:
          - accessStrategies:
            - config: {}
              handler: noop
            methods:
            - GET
            - POST
            - PUT
            - PATCH
            - DELETE
            - HEAD
            path: /.*
          service:
            host: xsk.<your-kyma-cluster-host>
            name: xsk
            port: 8080
        ```
	
        !!! Note
            Replace the **`<your-kyma-cluster-host>`** placeholder with your Kyma cluster host (e.g. **`c-xxxxxxx.kyma.xxx.xxx.xxx.ondemand.com`**)._

        !!! tip "XSK versions"
            Instead of using the `latest` tag (version), for production and development use cases it is recomended to use stable release version:

            - All released versions can be found [here](https://github.com/sap/xsk/releases/).
            - All XSK Docker images and tags (versions) can be found [here](https://hub.docker.com/u/dirigiblelabs).

    - Navigate to your Kyma dashboard and select the **`default`** namespace.

    - Click on the **Deploy new resource** button and select the `deployment.yaml` file.

    !!! info "Note"
        Alternatively the `kubectl -f deployment.yaml` could be used to deploy the resources.

1. Create XSUAA service instance:

    - From the Kyma dashboard, go to **Service Management** **&rarr;** **Catalog**.
    - Find the `Authorization & Trust Management` service.
    - Create new service instance.
    - Provide the following additional parameters.

        ```json
        {
           "xsappname": "dirigible-xsuaa",
           "oauth2-configuration": {
              "token-validity": 7200,
              "redirect-uris": [
                 "https://dirigible.<your-kyma-cluster-host>"
              ]
           },
           "scopes": [
              {
                 "name": "$XSAPPNAME.Developer",
                 "description": "Developer scope"
              },
              {
                 "name": "$XSAPPNAME.Operator",
                 "description": "Operator scope"
              }
           ],
           "role-templates": [
              {
                 "name": "Developer",
                 "description": "Developer related roles",
                 "scope-references": [
                    "$XSAPPNAME.Developer"
                 ]
              },
              {
                 "name": "Operator",
                 "description": "Operator related roles",
                 "scope-references": [
                    "$XSAPPNAME.Operator"
                 ]
              }
           ],
           "role-collections": [
              {
                 "name": "XSK Developer",
                 "description": "XSK Developer",
                 "role-template-references": [ 
                    "$XSAPPNAME.Developer"
                 ]
              },
              {
                 "name": "XSK Operator",
                 "description": "XSK Operator",
                 "role-template-references": [ 
                    "$XSAPPNAME.Operator"
                 ]
              }
           ]	
        }
        ```

        !!! Note
            Replace the **`<your-kyma-cluster-host>`** placeholder with your Kyma cluster host (e.g. **`c-xxxxxxx.kyma.xxx.xxx.xxx.ondemand.com`**).

    - Bind the servce instance to the **`xsk`** application.

1. Assign the `Developer` and `Operator` roles.

1. Log in.

!!! example "Additional Materials"
    Step by step tutorial can be found [here](https://blogs.sap.com/2020/10/13/how-to-deploy-eclipse-dirigible-in-the-sap-cloud-platform-kyma-environment/).
