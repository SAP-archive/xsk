## XSJS

This sample contains a single `xsjs` service using the [$.db](https://help.sap.com/doc/3de842783af24336b6305a3c0223a369/2.0.03/en-US/%24.db.html), [$.sessioon](https://help.sap.com/doc/3de842783af24336b6305a3c0223a369/2.0.03/en-US/%24.session.html) and [$.response](https://help.sap.com/doc/3de842783af24336b6305a3c0223a369/2.0.03/en-US/$.web.WebResponse.html) APIs.

- [XSJS](#xsjs)
  - [Kpack Configurations](#kpack-configurations)
  - [Building XSK Docker Image from Source](#building-xsk-docker-image-from-source)
  - [Deploying XSK Docker Image on Kyma](#deploying-xsk-docker-image-on-kyma)

### Kpack Configurations

- Install [Kpack](https://github.com/pivotal/kpack) on your Kubernetes/Kyma cluster.
- Install Kpack `ClusterStore` & `ClusterStack` resources:
    ```
    helm install xsk-cluster-builder xsk/xsk \
    --set install.clusterBuilder=true \
    --set create.secret=true \
    --set docker.server=<docker-server> \
    --set docker.username=<docker-username> \
    --set docker.password=<docker-password> \
    --set docker.email=<docker-email>
    ```

    > _**Note:** Before running the command, replace the placelohers, where:_

    - > _`<docker-server>` is the target docker registry (e.g. https://index.docker.io/v1/)._
    
    - > _`<docker-username>` is the username that will be used to push images._
    
    - > _`<docker-password>` is the password for the docker user._
    
    - > _`<docker-email>` is the email of the docker user._

- Install Kpack `Builder` resource:
    ```
    helm install xsk-image-builder xsk/xsk \
    --set install.imageBuilder=true \
    --set imageBuilder.repository=<image-builder-repository>
    ```

    > _**Note:** Before running the command, replace the `<image-builder-repository>` placeholder with a docker repository, where the Kpack builder will be stored (e.g. `myDockerOrg/myKpackImageBuilderRepository`)._

### Building XSK Docker Image from Source

```
helm install xsk-image-application xsk/xsk \
--set create.image=true \
--set image.repository=dirigiblelabs/xsk-image-application \
--set image.source=<zipped-application-content-url>
```

> _**Note:** Before running the command, replace the `<zipped-application-content-url>` placeholder with URL to your XSK application content (e.g. https://github.com/SAP/xsk/releases/download/v0.6.2/xsjs.zip)_

- To monitor the build log execute the following command:

    ```
    logs -image xsk-image-application -namespace default
    ```

    > _**Note:** Install the `logs` command from the Kpack documentation._

- Also the following Kubernetes resources could be monitored:

    ```
    kubectl get clusterstore,clusterstack,builders,pods,images
    ```

### Deploying XSK Docker Image on Kyma

```
helm install my-application xsk/xsk \
--set create.application=true \
--set application.image=<docker-application-image> \
--set application.homeUrl=/services/v4/xsk/xsjs/demo.xsjs \
--set application.fastBootstrap=true \
--set create.secret=true \
--set hana.url=<hana-url> \
--set hana.username=<hana-username> \
--set hana.password=<hana-password> \
--set kyma.host=<kyma-host>
```

> _**Note:** Before running the command, replace the placelohers, where:_

- > _`<docker-application-image>` is the target XSK application docker image that was built in the previous step._

- > _`<xsk-application-home-url>` is the default home url, once the application is accessed (e.g. `/services/v4/xsk/my-application/my-service.xsjs`)._

- > _`<hana-url>` is the URL of the target HANA instance (e.g. `199f1737-...hanacloud.ondemand.com:443`)._

- > _`<hana-username>` is the user that will be used to connect to the target HANA instance._

- > _`<hana-password>` is the password that will be used to connect to the target HANA instance._

- > _`<kyma-host>` is the Kyma instance host, where the XSK application will be deployed (e.g. `c-1632...ondemand.com`)._
