---
title: Kyma
---

Setup in Kyma
===

## Overview
---

You can deploy XSK in the SAP BTP[^1], Kyma environment.

[^1]: SAP Cloud Platform is called SAP Business Technology Platform (SAP BTP) as of 2021.

!!! info "Prerequisites"
    - Install [kubectl](https://kubernetes.io/docs/tasks/tools/install-kubectl/) (optional)
    - Navigate to your SAP BTP global account. You can access your Trial account in the SAP BTP cockpit from [here](https://account.hanatrial.ondemand.com/).

!!! warning "Warning"
    At the time of writing these setup instructions _(19.08.2021)_, creating a HANA Cloud service instance in the SAP BTP Kyma environment was not possible, thus the setup is currently suitable only for **test** & **demo** purposes. You can find a workaround solution [here](https://github.com/SAP/xsk/discussions/394).

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
                - name: xsk-volume
                  mountPath: /usr/local/tomcat/target/dirigible/repository
                ports:
                - containerPort: 8080
                  name: xsk
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
            Replace the **`<your-kyma-cluster-host>`** placeholder with your Kyma cluster host _(e.g. **`c-xxxxxxx.kyma.xxx.xxx.xxx.ondemand.com`**)_.

        !!! tip "XSK versions"
            Instead of using the `latest` tag (version), for production and development use cases it is recommended that you use a stable release version:

            - You can find all released versions [here](https://github.com/sap/xsk/releases/).
            - You can find all XSK Docker images and tags (versions) [here](https://hub.docker.com/u/dirigiblelabs).

    - Navigate to your Kyma dashboard and select the **`default`** namespace.

    - Click the **Deploy new resource** button and select the `deployment.yaml` file.

    !!! info "Note"
        Alternatively, you can use the `kubectl -f deployment.yaml` to deploy the resources.

1. Create an XSUAA service instance:

    === "Json"
        - From the Kyma dashboard, go to **Service Management** **&rarr;** **Catalog**.
        - Find the `Authorization & Trust Management` service.
        - Create a new service instance.
        - Provide the following additional parameters:

        ```json
        {
              "xsappname": "xsk-xsuaa",
              "oauth2-configuration": {
                  "token-validity": 7200,
                  "redirect-uris": [
                    "https://xsk.<your-kyma-cluster-host>"
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

         - Bind the servce instance to the **`xsk`** application.

    === "Yaml"

        ```yaml
        apiVersion: servicecatalog.k8s.io/v1beta1
        kind: ServiceInstance
        metadata:
          name: xsuaa-xsk
        spec:
          clusterServiceClassExternalName: xsuaa
          clusterServiceClassRef:
            name: xsuaa
          clusterServicePlanExternalName: broker
          parameters:
            xsappname: xsk-xsuaa
            oauth2-configuration:
              redirect-uris:
              - https://xsk.<your-kyma-host>
              token-validity: 7200
            role-collections:
            - description: XSK Developer
              name: XSK Developer
              role-template-references:
              - $XSAPPNAME.Developer
            - description: XSK Operator
              name: XSK Operator
              role-template-references:
              - $XSAPPNAME.Operator  
            role-templates:
            - description: Developer related roles
              name: Developer
              scope-references:
              - $XSAPPNAME.Developer
            - description: Operator related roles
              name: Operator
              scope-references:
              - $XSAPPNAME.Operator
            scopes:
            - description: Developer scope
              name: $XSAPPNAME.Developer
            - description: Operator scope
              name: $XSAPPNAME.Operator
        ---
        apiVersion: servicecatalog.k8s.io/v1beta1
        kind: ServiceBinding
        metadata:
          name: xsuaa-xsk-binding
        spec:
          instanceRef:
            name: xsuaa-xsk
          parameters: {}
          secretName: xsuaa-xsk-binding
        ---
        apiVersion: servicecatalog.kyma-project.io/v1alpha1
        kind: ServiceBindingUsage
        metadata:
          name: xsuaa-xsk-usage
        spec:
          parameters:
            envPrefix:
              name: ""
          serviceBindingRef:
            name: xsuaa-xsk-binding
          usedBy:
            kind: deployment
            name: xsk
        ```

    !!! Note
            Replace the **`<your-kyma-cluster-host>`** placeholder with your Kyma cluster host (e.g. **`c-xxxxxxx.kyma.xxx.xxx.xxx.ondemand.com`**).
    
1. Assign the `Developer` and `Operator` roles.

1. Log in.

!!! example "Additional Materials"
    You can find a step-by-step tutorial [here](https://blogs.sap.com/2020/10/13/how-to-deploy-eclipse-dirigible-in-the-sap-cloud-platform-kyma-environment/).
