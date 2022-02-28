---
title: CI/CD
---

CI/CD with GitHub Actions
===

## Overview
---

The current setup is leveraging GitHub Actions and Kyma to create a CI/CD pipeline for building, releasing and deploying Docker image with XSK packaged with your application content. 

!!! Prerequisites

    Create `Service Account` in Kyma for the CI/CD pipeline, as described bellow:

      1. Navigate to your Kyma cluster.
      1. Select your namespace _(e.g. `default`)_.
      1. Go to `Configuration` &#8594; `Service Accounts`.
      1. Click the `Create Service Account` button.
      1. Enter the service account name _(e.g. `xsk`)_.

    Create `Cluster Role` in Kyma for the CI/CD pipeline, as described bellow:

      1. Navigate to your Kyma cluster.
      1. Go to `Configuration` &#8594; `Cluster Roles`.
      1. Click the `Create Cluster Role` button.
      1. Enter the cluster role name _(e.g. `xsk`)_.
      1. Add the following `API Groups`:
        - **(core)**
        - **apps**
        - **servicecatalog.k8s.io**
        - **networking.istio.io**
        - **servicecatalog.kyma-project.io**
        - **gateway.kyma-project.io**
      1. Select `*` in the `Resources` dropdown, to match all resources.
      1. Select `*` in the `Verbs` dropdown, to match all verbs.
      1. Click the `Create` button.

    Create `Cluster Role Binding` of the `Cluster Role` to the `Service Account`:

      1. Navigate to your Kyma cluster.
      1. Go to `Cluster Role Bindings` &#8594; `Cluster Roles`.
      1. Enter the cluster role binding name _(e.g. `xsk-default`, note that the name is not namespace specific and should be unique for the whole cluster)_.
      1. Select the `Cluster Role` _(e.g. `xsk`)_.
      1. Switch the `Kind` to `ServiceAccount`.
      1. Select the desired namespace _(e.g. `default`)_.
      1. Select the service account _(e.g. `xsk`)_.

    Copy the `Service Account` token as later will be needed for the `KYMA_TOKEN` secret:

      1. Navigate to your Kyma cluster.
      1. Go to `Configuration` &#8594; `Service Accounts`.
      1. Select the `Service Account` _(e.g. `xsk`)_.
      1. Select the secret for more details _(e.g. `xsk-token-wf6jk`)_.
      1. Click the `Decode` button to decode the secret. 
      1. Copy the `token` value _(e.g. `eyJhbGciOiJS...`)_.


## Setup
---

1. Navigate to your GitHub repository.
1. Create `.github/workflows/<pipeline-name>.yaml` file with the following content:

=== "Build _(`build.yaml`)_"

    !!! info
        The following GitHub Action builds XSK based Docker image for your application and push it to your Docker registry.

        _**Note:** Replace the `<your-organization>/<your-repository>` placeholder with a default organization and repository where the Docker image will be pushed (can be changed when triggering the GitHub Action)_.

    ```yaml
    name: Build Application Image

    on:
      workflow_dispatch:
        inputs:
          xskRepository:
            description: XSK Repository
            required: true
            type: choice
            options: 
            - 'dirigiblelabs/xsk-kyma'
            - 'dirigiblelabs/xsk-cf'
            - 'dirigiblelabs/xsk'
          xskVersion:
            description: XSK Version
            required: true
            default: 'latest'
          applicationRepository:
            description: Application Repository
            required: true
            default: '<your-organization>/<your-repository>'
          applicationReleaseVersion:
            description: Application Release Version
            required: true

    jobs:
      build:
        runs-on: ubuntu-latest
        steps:
          - name: Release Input Parameters
            run: |
              echo "Release Type: ${{ github.event.inputs.releaseType }}"
              echo "Application Repository: ${{ github.event.inputs.applicationRepository }}"
              echo "Application Release Version: ${{ github.event.inputs.applicationReleaseVersion }}"
          - uses: actions/checkout@v2
            with:
              fetch-depth: 0
          - name: Build Dockerfile
            run: |
              DOCKERFILE_CONTENT=$(cat << EOF
              FROM ${{ github.event.inputs.xskRepository }}:${{ github.event.inputs.xskVersion }}
              RUN mkdir -p /usr/local/tomcat/target/dirigible/repository/root/registry/public/
              COPY . /usr/local/tomcat/target/dirigible/repository/root/registry/public/
              RUN rm -rf /usr/local/tomcat/target/dirigible/repository/root/registry/public/Dockerfile
              RUN rm -rf /usr/local/tomcat/target/dirigible/repository/root/registry/public/.github/
              EOF
              )
              echo "$DOCKERFILE_CONTENT" >> Dockerfile
              echo "$DOCKERFILE_CONTENT"
          - name: Docker Login
            run: docker login ${{secrets.DOCKER_REGISTRY}} -u ${{secrets.DOCKER_USERNAME}} -p ${{secrets.DOCKER_PASSWORD}}
          - name: Build Application Image
            run: |
              docker build . -t ${{secrets.DOCKER_REGISTRY}}/${{ github.event.inputs.applicationRepository }}:${{ github.event.inputs.applicationReleaseVersion }}
              docker tag ${{secrets.DOCKER_REGISTRY}}/${{ github.event.inputs.applicationRepository }}:${{ github.event.inputs.applicationReleaseVersion }} ${{ github.event.inputs.applicationRepository }}:latest
              docker push ${{secrets.DOCKER_REGISTRY}}/${{ github.event.inputs.applicationRepository }}:${{ github.event.inputs.applicationReleaseVersion }}
              docker push ${{secrets.DOCKER_REGISTRY}}/${{ github.event.inputs.applicationRepository }}:latest
    ```

=== "Deployment _(`deploy.yaml`)_"

    !!! info
        The following GitHub Action deploys your XSK based Docker image to your Kyma cluster via Helm.
        
        _**Note:** Replace the `<your-organization>/<your-repository>` placeholder with a default organization and repository where the Docker image will be pushed (can be changed when triggering the GitHub Action)_.

    ```yaml
    name: Deploy Application Image

    on:
      workflow_dispatch:
        inputs:
          applicationRepository:
            description: Application Repository
            required: true
            default: '<your-organization>/<your-repository>'
          applicationReleaseVersion:
            description: Application Release Version
            required: true

    jobs:
      deploy:
        runs-on: ubuntu-latest
        steps:
          - name: Setup Kube Config File
            env:
              KYMA_CERTIFICATE: ${{ secrets.KYMA_CERTIFICATE }}
              KYMA_SERVER: ${{ secrets.KYMA_SERVER }}
              KYMA_TOKEN: ${{ secrets.KYMA_TOKEN }}
            run: |
              mkdir $HOME/.kube
              echo "
              apiVersion: v1
              kind: Config
              current-context: xsk
              clusters:
              - name: xsk
                cluster:
                  certificate-authority-data: $KYMA_CERTIFICATE
                  server: $KYMA_SERVER
              contexts:
              - name: xsk
                context:
                  cluster: xsk
                  user: xsk
              users:
              - name: xsk
                user:
                  token: $KYMA_TOKEN" > $HOME/.kube/config
          - name: Export Kyma Host
            run: |
              export KYMA_API_SERVER=${{ secrets.KYMA_SERVER }}
              echo "KYMA_HOST=${KYMA_API_SERVER:12}" >> $GITHUB_ENV
          - name: Helm Upgrade Application Instance
            run: |
              helm repo add xsk https://www.xsk.io
              helm repo update
              helm upgrade --install xsk xsk/xsk \
              --set kyma.enabled=true \
              --set kyma.host=$KYMA_HOST \
              --set hana.enabled=true \
              --set hana.url='jdbc:sap://${{ secrets.HANA_URL }}/?encrypt=true&validateCertificate=false`' \
              --set hana.username=${{ secrets.HANA_USERNAME }} \
              --set hana.password='${{ secrets.HANA_PASSWORD }}' \
              --set persistentVolumeClaim.enabled=false \
              --set deployment.strategyType=RollingUpdate \
              --set application.image=${{secrets.DOCKER_REGISTRY}}/${{ github.event.inputs.applicationRepository }}:${{ github.event.inputs.applicationReleaseVersion }}
    ```

!!! note "GitHub Secrets"

    The following GitHub Secrets are required in order to successfully run the previously created GitHub Actions. To create GitHub secret:
    
      1. Navigate to your GitHub repository.
      1. Open the `Settings` tab.
      1. Go to `Secrets` &#8594; `Actions`.
      1. Click the `New Repository Secret` button.

    |       Name         |          Description                                           |
    |--------------------|----------------------------------------------------------------|
    | `DOCKER_REGISTRY`  | The Docker Registry _(e.g. `docker.io`, `ghcr.io`, etc.)_      |
    | `DOCKER_USERNAME`  | The Docker Username _(`<your-docker-username>`)_               |
    | `DOCKER_PASSWORD`  | The Docker Password _(`<your-docker-password>`)_               |
    | `HANA_URL`         | The HANA Cloud URL _(e.g. `7512c2q1-...:443`)_                 |
    | `HANA_USERNAME`    | The HANA Cloud Username _(`<your-hana-cloud-username>`)_       |
    | `HANA_PASSWORD`    | The HANA Cloud Password _(`<your-hana-cloud-password>`)_       |
    | `KYMA_CERTIFICATE` | The Kyma Certificate _(e.g. `LS0tLS1CRUdJTiBDRVJUS...`)_       |
    | `KYMA_SERVER`      | The Kyma Server _(e.g. `https://api.c-a7b1c6...ondemand.com`)_ |
    | `KYMA_TOKEN`       | The Kyma Token _(e.g. `eyJhbGciOiJSUzI1NiIsImtpZCI6In...`)_    |
    | `GH_TOKEN`         | The GitHub Token _(e.g. `a385f4...`)_                          |

