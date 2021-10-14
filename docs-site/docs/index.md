---
title: Welcome
---

Project XSK
===

## What is XSK?
---

**Project XSK** is a compatible environment for applications based on [SAP HANA Extended Application Services](https://help.sap.com/viewer/52715f71adba4aaeb480d946c742d1f6/2.0.03/en-US/a6c0749255d84a81a154a7fc87dd33ce.html) (XS).

![asd](/img/xsk-migration-architecture.png)

#### Comparison with other options

| Aspect                         | XSK  | Others  | Description |
| ------------------------------ |:----:| :-----:| -----------
| No code modifications          |  ✅  |    ❌   | Preserving the existing artifacts and APIs
| No change of development model |  ✅  |    ❌   | No further education needed
| Single-step guided migration   |  ✅  |    ❌   | Wizard-based migration assistant is built-in
| Complete end-to-end stack      |  ✅  |    ❌   | Covers all the features in a single distribution
| Can be deployed anywhere       |  ✅  |    ❌   | Kubernetes, Kyma, Docker, Cloud Foundry, Buildpack, XSA, and on your PC
| Zero-cost migration            |  ✅  |    ❌   | No additional cost for the migration itself as well as the running cost is expected to be less
| Open source                    |  ✅  |    ❌   | Entirely open source and free


## Who uses it?
---

If you have an **existing** [SAP HANA Extended Application Services](https://help.sap.com/viewer/52715f71adba4aaeb480d946c742d1f6/2.0.03/en-US/a6c0749255d84a81a154a7fc87dd33ce.html) (**XS classic application**), which currently runs on your SAP HANA database and you want to scale it out horizontally on a hyperscaler of your choice, XSK is your solution! It also provides the migration tooling for transferring the application code from your existing SAP HANA. By preserving the existing artifacts, APIs and development model, you can leverage your existing knowledge and experience to continue running existing applications and even to build new ones, if you want.

## How to use it?
---

You can deploy it separately from a [SAP HANA](https://www.sap.com/products/hana.html?btp=991d50bf-fa15-4979-ac4b-b280b0eb951f) instance as a [Docker](https://www.docker.com/) container on [Kubernetes](https://kubernetes.io/), on [Cloud Foundry](https://www.xsk.io/setup/cloud-foundry/), or even [locally](https://www.xsk.io/setup/) in standalone mode. The compatibility stack is an extension of the [Eclipse Dirigible](https://github.com/eclipse/dirigible) cloud development platform.

[Try it Out](https://trial.apps.xsk.io/){ .md-button .md-button--primary }

## Is it free?
---

XSK is an **open source project** provided by SAP on [GitHub](https://github.com/SAP/xsk). It is under the [Apache 2.0 License](https://github.com/SAP/xsk/blob/main/LICENSE). You can help us improve the project by following the [Contribution Guidelines](https://github.com/SAP/xsk/blob/main/CONTRIBUTING.md) and creating [issues](https://github.com/SAP/xsk/issues).

## Releases
---

You can find all the different distributions of the XSK project at [https://github.com/SAP/xsk/releases](https://github.com/SAP/xsk/releases) along with the instructions how to deploy them on the target platforms.

## Contact us
---

We run the project in compliance with the high standard of transparency and openness. In case of questions or suggestions you are welcome to our public [discussions](https://github.com/SAP/xsk/discussions) space. We have a public [Slack channel](https://slack.xsk.io) as well.

