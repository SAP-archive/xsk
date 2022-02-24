---
title: CI/CD
---

CI/CD with GitHub Actions
===

## Overview
---

TODO

## Setup
---

1. Setup


=== "All in One"

    ```yaml
    name: Release Application Image

    on:
      workflow_dispatch:
        inputs:
          releaseType:
            type: choice
            required: true
            description: Release Type
            options: 
            - image
            - image-release
            - image-release-deploy
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
            default: 'thuf/test-xsjs'
          applicationReleaseVersion:
            description: Application Release Version
            required: true
          kymaAccessToken:
            description: Kyma Access Token

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
            run: docker login -u ${{secrets.DOCKER_USERNAME}} -p ${{secrets.DOCKER_PASSWORD}} ${{secrets.DOCKER_REGISTRY}}
          - name: Build Application Image
            run: |
              docker build . -t ${{ github.event.inputs.applicationRepository }}:${{ github.event.inputs.applicationReleaseVersion }}
              docker tag ${{ github.event.inputs.applicationRepository }}:${{ github.event.inputs.applicationReleaseVersion }} ${{ github.event.inputs.applicationRepository }}:latest
              docker push ${{ github.event.inputs.applicationRepository }}:${{ github.event.inputs.applicationReleaseVersion }}
              docker push ${{ github.event.inputs.applicationRepository }}:latest
      release:
        if: "${{ github.event.inputs.releaseType == 'image-release'}} || ${{ github.event.inputs.releaseType == 'image-release-deploy'}}"
        needs: [build]
        runs-on: ubuntu-latest
        steps:
          - name: Create Release
            uses: actions/create-release@v1
            env:
              GITHUB_TOKEN: ${{ secrets.GH_TOKEN }}
            with:
              tag_name: ${{ github.event.inputs.applicationReleaseVersion }}
              release_name: ${{ github.event.inputs.applicationReleaseVersion }}
              draft: false
              prerelease: false
              body: |
                ## Application Image - ${{ github.event.inputs.applicationReleaseVersion }}
                #### Docker images:
                  - [${{ github.event.inputs.applicationRepository }}](https://hub.docker.com/r/${{ github.event.inputs.applicationRepository }}/tags?page=1&ordering=last_updated) - Application Image
      deploy:
        if: "${{ github.event.inputs.releaseType == 'image-release-deploy'}}"
        needs: [build, release]
        runs-on: ubuntu-latest
        steps:
          - name: Setup Kube Config File
            run: |
              mkdir $HOME/.kube
              echo "${{ secrets.KUBE_CONFIG }}" >> $HOME/.kube/config
              find $HOME/.kube/config -type f -exec sed -i ''s/#{AccessToken}#/${{ github.event.inputs.kymaAccessToken }}/g'' {} \;
          - name: Helm Upgrade Application Instance
            run: |
              helm repo add xsk https://www.xsk.io
              helm repo update
              helm upgrade --install xsk xsk/xsk \
              --set kyma.enabled=true \
              --set kyma.host=${{ secrets.KYMA_HOST }} \
              --set hana.enabled=true \
              --set hana.url='${{ secrets.HANA_URL }}' \
              --set hana.username=${{ secrets.HANA_USERNAME }} \
              --set hana.password='${{ secrets.HANA_PASSWORD }}' \
              --set persistentVolumeClaim.enabled=false \
              --set deployment.strategyType=RollingUpdate \
              --set application.image=${{ github.event.inputs.applicationRepository }}:${{ github.event.inputs.applicationReleaseVersion }}
    ```

=== "Build (Only)"

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
            default: 'thuf/test-xsjs'
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
            run: docker login -u ${{secrets.DOCKER_USERNAME}} -p ${{secrets.DOCKER_PASSWORD}} ${{secrets.DOCKER_REGISTRY}}
          - name: Build Application Image
            run: |
              docker build . -t ${{ github.event.inputs.applicationRepository }}:${{ github.event.inputs.applicationReleaseVersion }}
              docker tag ${{ github.event.inputs.applicationRepository }}:${{ github.event.inputs.applicationReleaseVersion }} ${{ github.event.inputs.applicationRepository }}:latest
              docker push ${{ github.event.inputs.applicationRepository }}:${{ github.event.inputs.applicationReleaseVersion }}
              docker push ${{ github.event.inputs.applicationRepository }}:latest
    ```

=== "Deployment (Only)"

    ```yaml
    name: Deploy Application Image

    on:
      workflow_dispatch:
        inputs:
          applicationRepository:
            description: Application Repository
            required: true
            default: 'thuf/test-xsjs'
          applicationReleaseVersion:
            description: Application Release Version
            required: true
          kymaAccessToken:
            description: Kyma Access Token

    jobs:
      deploy:
        runs-on: ubuntu-latest
        steps:
          - name: Setup Kube Config File
            run: |
              mkdir $HOME/.kube
              echo "${{ secrets.KUBE_CONFIG }}" >> $HOME/.kube/config
              find $HOME/.kube/config -type f -exec sed -i ''s/#{AccessToken}#/${{ github.event.inputs.kymaAccessToken }}/g'' {} \;
          - name: Helm Upgrade Application Instance
            run: |
              helm repo add xsk https://www.xsk.io
              helm repo update
              helm upgrade --install xsk xsk/xsk \
              --set kyma.enabled=true \
              --set kyma.host=${{ secrets.KYMA_HOST }} \
              --set hana.enabled=true \
              --set hana.url='${{ secrets.HANA_URL }}' \
              --set hana.username=${{ secrets.HANA_USERNAME }} \
              --set hana.password='${{ secrets.HANA_PASSWORD }}' \
              --set persistentVolumeClaim.enabled=false \
              --set deployment.strategyType=RollingUpdate \
              --set application.image=${{ github.event.inputs.applicationRepository }}:${{ github.event.inputs.applicationReleaseVersion }}
    ```

**Required GitHub Secrets:**

|       Name        |          Description                                           |
|-------------------|----------------------------------------------------------------|
| `DOCKER_REGISTRY` | The Docker Registry URL _(e.g. `https://index.docker.io/v1/`)_ |
| `DOCKER_USERNAME` | The Docker Username _(`<your-docker-username>`)_               |
| `DOCKER_PASSWORD` | The Docker Password _(`<your-docker-password>`)_               |
| `HANA_URL`        | The HANA Cloud URL _(e.g. `jdbc:sap://7512c2q1-.../?encrypt=true&validateCertificate=false`)_ |
| `HANA_USERNAME`   | The HANA Cloud Username _(`<your-hana-cloud-username>`)_       |
| `HANA_PASSWORD`   | The HANA Cloud Password _(`<your-hana-cloud-password>`)_       |
| `KYMA_HOST`       | The Kyma host _(e.g. `c-a7db1c6...ondemand.com`)_              |
| `GH_TOKEN`        | The GitHub Token _(`<your-github-token>`)_                     |
| `KUBE_CONFIG`     | The Kyma kubeconfig file template* _(see more details bellow)_ |

!!! info "Kyma kubeconfig file template"

    ```yaml
    apiVersion: v1
    clusters:
    - cluster:
        certificate-authority-data: LS0tLS1CRUdJTiBD...==
        server: https://apiserver.c-a7db1c6...ondemand.com
      name: c-a7db1c6...ondemand.com
    contexts:
    - context:
        cluster: c-a7db1c6...ondemand.com
        user: OIDCUser
      name: c-a7db1c6...ondemand.com
    current-context: c-a7db1c6...ondemand.com
    kind: Config
    preferences: {}
    users:
    - name: OIDCUser
      user:
        token: #{AccessToken}#
    ```
