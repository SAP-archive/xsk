---
title: Cloud Foundry
---

Setup in Cloud Foundry
===

## Overview
---

You can deploy XSK in the SAP BTP[^1], Cloud Foundry environment.

[^1]: SAP Cloud Platform is called SAP Business Technology Platform (SAP BTP) as of 2021.
    
!!! info "Prerequisites"
    - Install the [Cloud Foundry Command Line Interface](http://docs.cloudfoundry.org/devguide/installcf/install-go-cli.html).
    - Access to an SAP BTP account. To create an SAP BTP Trial account, navigate to the [SAP BTP Trial home page](https://account.hanatrial.ondemand.com/).
    - Create a `HANA Cloud` service instance in SAP BTP Cloud Foundry space.
    - Create a separate `HANA Cloud` user that will be used from the XSK engine.

## Steps
---

1. Set the SAP BTP Cloud Foundry API host:

    ```
    cf api <cloud-foundry-api-host>
    ```

1. Log in to the SAP BTP, Cloud Foundry environment with:

    ```
    cf login
    ```

1. Create an XSUAA service instance:

    - Copy and paste the following content into `xs-security.json`:

        ```json
        {
           "xsappname": "<applicationName>-xsuaa",
           "tenant-mode": "shared",
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
            Replace the `<applicationName>` placeholder with your application name, e.g. **`xsk`**.

    - Create an XSUAA service instance:

        ```
        cf create-service xsuaa application <applicationName>-xsuaa -c xs-security.json
        ```

        !!! Note
            Use the same `<applicationName>` as in the previous step.

1. Deploy XSK:


    === "Docker"

        ```
        cf push xsk \
        --docker-image=dirigiblelabs/xsk-cf:latest \
        --hostname xsk-<org-name> \
        -m 2G -k 2G
        ```
        !!! info "Note"
            - Replace the `<org-name>` placeholder with your subaccount's **Subdomain** value.
            
        !!! tip "XSK versions"
            Instead of using the `latest` tag (version), for production and development use cases it is recommended that you use a stable release version:
            
            - You can find all released versions at [https://github.com/sap/xsk/releases/](https://github.com/sap/xsk/releases/).
            - You can find all XSK Docker images and tags (versions) at [https://hub.docker.com/u/dirigiblelabs](https://hub.docker.com/u/dirigiblelabs).

        - Bind the XSUAA and HANA Cloud service instances to the XSK deployment:

            ```
            cf bind-service xsk <applicationName>-xsuaa
            cf bind-service xsk <hanaCloudInstanceName>
            cf set-env xsk HANA_USERNAME <hanaCloudUsername>
            cf set-env xsk HANA_PASSWORD <hanaCloudPassword>
            ```

            !!! Note
                - Replace the `<applicationName>` placeholder with the application name used in the previous steps.
                - Replace the `<hanaCloudInstanceName>` placeholder with the HANA Cloud service instance name that will be used.
                - Replace the `<hanaUsername>` placeholder with the HANA Cloud username that will be used.
                - Replace the `<hanaPassword>` placeholder with the HANA Cloud password that will be used.

        - Restart the `xsk` deployment:

            ```
            cf restart xsk
            ```

    === "Buildpack"

        - Download the `sap-cf` binaries from the downloads site: [https://github.com/SAP/xsk/releases](https://github.com/SAP/xsk/releases)
        - Unzip the downloaded archive to extract the `ROOT.war` file.
        - Create `manifest.yaml` file in the same directory where the `ROOT.war` is located:
            ```yaml
            applications:
            - name: xsk
              host: xsk-<org-name>
              memory: 2G
              buildpack: sap_java_buildpack
              path: ROOT.war
              env:
                JBP_CONFIG_COMPONENTS: "jres: ['com.sap.xs.java.buildpack.jdk.SAPMachineJDK']"
                JBP_CONFIG_SAP_MACHINE_JRE: 'jre: { version: 11.+ }'
                HANA_USERNAME: <hanaUsername>
                HANA_PASSWORD: <hanaPassword>
              services:
                - <applicationName>-xsuaa
                - <hanaCloudInstanceName>
            ```

            !!! Note
                - Replace the `<org-name>` placeholder with your subaccount's Subdomain value.
                - Replace the `<applicationName>` placeholder with the application name used in the previous steps.
                - Replace the `<hanaUsername>` placeholder with the HANA Cloud username that will be used.
                - Replace the `<hanaPassword>` placeholder with the HANA Cloud password that will be used.
                - Replace the `<hanaCloudInstanceName>` placeholder with the HANA Cloud service instance name that will be used.

        - Deploy with:
            ```
            cf push
            ```

1. Assign the `Developer` and `Operator` roles.

1. Log in.

!!! example "Additional Information"
    For more details, see the step-by-step tutorial [How to deploy Eclipse Dirigible in the SAP Cloud Platform Cloud Foundry environment](https://blogs.sap.com/2020/03/15/how-to-deploy-eclipse-dirigible-in-the-sap-cloud-platform-cloud-foundry-environment/) on SAP Community.
