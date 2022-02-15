---
title: XSHTTPDest
---

XSHTTPDest
===

## Overview
---

In SAP HANA XS Classic the concept of destinations is represented with a declarative definition in an .xshttpdest file and optional OAuth configurations. Administrative tasks are available in the XS Admin UI and require special privileges.
XSK leverages the SAP BTP Destination service with which these configurations are externalized from the application, thus separating their lifecycle.

## Transition path 
---

Below are all artifacts from XS Classic related to destination configuration and how to move them to SAP BTP Destination service.

### xshttpdest
!!! warning
    Artifact not supported.
    Destinations can be created in the [SAP BTP Destination service](https://help.sap.com/viewer/cca91383641e40ffbe03bdc78f00f681/Cloud/en-US/daca64dacc6148fcb5c70ed86082ef91.html). 

| SAP HANA XS Classic | SAP BTP Destination service                                                                                                                                                                                                                                                                                             |
|---------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| host                | set explicitly as part of `URL`                                                                                                                                                                                                                                                                                         |
| port                | set explicitly as part of `URL`                                                                                                                                                                                                                                                                                         |
| useSSL              | set protocol explicitly as part of `URL`                                                                                                                                                                                                                                                                                |
| description         | availabe                                                                                                                                                                                                                                                                                                                |
| sslAuth             | available when using [client certificate authentication](https://help.sap.com/viewer/cca91383641e40ffbe03bdc78f00f681/Cloud/en-US/4e13a04147314e8e9e54321f25d93fdc.html)                                                                                                                                                |
| sslHostCheck        | available as `TrustAll`                                                                                                                                                                                                                                                                                                 |
| pathPrefix          | set explicitly as part of `URL`                                                                                                                                                                                                                                                                                         |
| authType            | changed to `Authentication` - see [options](https://help.sap.com/viewer/cca91383641e40ffbe03bdc78f00f681/Cloud/en-US/42a0e6b966924f2e902090bdf435e1b2.html)                                                                                                                                                             |
| samlProvider        | not available                                                                                                                                                                                                                                                                                                           |
| samlACS             | not available                                                                                                                                                                                                                                                                                                           |
| samlAttributes      | not available                                                                                                                                                                                                                                                                                                           |
| samlNameId          | availabe as `nameIdFormat` when using [SAML Assertion](https://help.sap.com/viewer/cca91383641e40ffbe03bdc78f00f681/Cloud/en-US/d81e1683bd434823abf3ceefc4ff157f.html) or [OAuth SAML Bearer Assertion](https://help.sap.com/viewer/cca91383641e40ffbe03bdc78f00f681/Cloud/en-US/c69ea6aacd714ad2ae8ceb5fc3ceea56.html) |
| proxyType           | use `Internet` when connecting to services on the Internet and `OnPremise` when accessing systems through Cloud Connector                                                                                                                                                                                               |
| proxyHost           | not needed                                                                                                                                                                                                                                                                                                              |
| proxyPort           | not needed                                                                                                                                                                                                                                                                                                              |
| timeout             | not available - configured explicitly on client level                                                                                                                                                                                                                                                                   |
| remoteSID           | available as `RecipientSID` using [SAP Assertion SSO Authentication](https://help.sap.com/viewer/cca91383641e40ffbe03bdc78f00f681/Cloud/en-US/ceb8c4fa61e6443190185696c6d0342d.html)                                                                                                                                    |
| remoteClient          | available as `RecipientClient` using [SAP Assertion SSO Authentication](https://help.sap.com/viewer/cca91383641e40ffbe03bdc78f00f681/Cloud/en-US/ceb8c4fa61e6443190185696c6d0342d.html)|             | oAuthAppConfigPackage | not available|
| oAuthAppConfig        | `mandatoryScopes` can be set when using any of the OAuth flows - ie see [OAuth Client Credentials Authentication](https://help.sap.com/viewer/cca91383641e40ffbe03bdc78f00f681/Cloud/en-US/4e1d742a3d45472d83b411e141729795.html) |             

### xsoauthappconfig
Documentation in [SAP Help Portal](https://help.sap.com/viewer/b3d0daf2a98e49ada00bf31b7ca7a42e/2.0.03/en-US/e5b5d3a830ff4beea17c3efafe452065.html)

!!! warning
    Artifact not supported.  
    Scopes can still be defined - use any of the OAuth flows and set the `scope` property in order to achieve the same as with `mandatoryScopes` ie see [OAuth Client Credentials Authentication](https://help.sap.com/viewer/cca91383641e40ffbe03bdc78f00f681/Cloud/en-US/4e1d742a3d45472d83b411e141729795.html) 


| SAP HANA XS Classic | SAP BTP Destination service                                                        |
|---------------------|------------------------------------------------------------------------------------|
| description         | description can be set on destination level                                        |
| clientConfig        | not available - configuration specific for each destination                        |
| mandatoryScopes     | if `scope` list is set on destination level they will be mandatory                 |
| optionalScopes      | not available - if `scope` list is set on destination level they will be mandatory |
| modifies            | not available                                                                      |


### xsoauthclientconfig
Documentation in [SAP Help Portal](https://help.sap.com/viewer/b3d0daf2a98e49ada00bf31b7ca7a42e/2.0.03/en-US/4b9d546faeff48f2b0c3cb33abd6daeb.html)

!!! warning
    Artifact not supported.
    Some of the properties are available as explicit configuration in the SAP BTP Destination service.

| SAP HANA XS Classic      | SAP BTP Destination service                                                                                                                                                                                                          |
|--------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| clientFlavor             | see [xsoauthclientflavor](#xsoauthclientflavor) on how to configure properties for each destination                                                                                                                                  |
| clientID                 | set per destination when using any of the OAuth flows                                                                                                                                                                                |
| clientAuthType           | use `tokenServiceUser` and `tokenServicePassword` for `basic`. See [OAuth with X.509 Client Certificates](https://help.sap.com/viewer/cca91383641e40ffbe03bdc78f00f681/Cloud/en-US/2c162aaa9e464480b2307879d1b865f5.html) for `cert` |
| authorizationEndpointURL | not available                                                                                                                                                                                                                        |
| tokenEndpointURL         | available as `tokenServiceURL` when using any of the OAuth flows                                                                                                                                                                     |
| revocationEndpointURL    | not available                                                                                                                                                                                                                        |
| flow                     | `saml2Bearer` - see other types [here](https://help.sap.com/viewer/cca91383641e40ffbe03bdc78f00f681/Cloud/en-US/42a0e6b966924f2e902090bdf435e1b2.html)                                                                               |
| description              | available on destination level                                                                                                                                                                                                       |
| samlIssuer               | available as `assertionIssuer` when using SAML Assertion                                                                                                                                                                             |
| redirectURL              | not available - handle authCode flow in code                                                                                                                                                                                         |
| scopeReq                 | not available                                                                                                                                                                                                                        |
| shared                   | not available                                                                                                                                                                                                                        |
| modifies                 | not available                                                                                                                                                                                                                        |


### xsoauthclientflavor
Documentation in [SAP Help Portal](https://help.sap.com/viewer/b3d0daf2a98e49ada00bf31b7ca7a42e/2.0.03/en-US/6757a4e3ed9f42bd86c5b5c3b264e090.html)

!!! warning
    Artifact not supported.
    Headers and query parameters can be defined as described in the [SAP BTP Destination service documentation](https://help.sap.com/viewer/cca91383641e40ffbe03bdc78f00f681/Cloud/en-US/4e1d742a3d45472d83b411e141729795.html)

## Reference
---

!!! note "SAP Help Portal"

    For more information, see [Destination Administration](https://help.sap.com/viewer/cca91383641e40ffbe03bdc78f00f681/Cloud/en-US/78198e8b58f949af977e579b5de42299.html).

## Usage
---

To use destinations in your project, an instance of Destination service must be created and bound to the XSK environment - see [Using Services in the Kyma Environment](https://help.sap.com/products/BTP/65de2977205c403bbc107264b8eccf4b/ea4dd81e49254dd482d32e3c20f4477a.html?locale=en-US)

After an instance of SAP BTP Destination service is bound to XSK, all destinations will be read from it when using [$.net.http.readDestination](../api/net/http/net.http.Destination.md)

## Importing destinations
---
Destinations can be imported directly in SAP BTP Destination service, but as the fields are different, some preprocessing is needed

=== "HANA XS Classic syntax"

    ```
    description = "My Destination";
    host = "example.com";
    port = 443;
    proxyType = none;
    proxyHost = "proxy";
    proxyPort = 8080;
    authType = basic;
    useSSL = true;
    timeout = 30000;
    sslHostCheck = false;
    sslAuth = anonymous;
    ```

=== "SAP BTP Destination Service"

    ```
    Description = My Destination
    Name = My Destination
    Type = HTTP
    URL = https://example.com:443
    ProxyType = Internet
    Authentication = BasicAuthentication
    ```
