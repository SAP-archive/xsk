---
title: On-premise connectivity
---

On-premise connectivity
===

## Prerequisite

As a prerequisite, the [SAP Cloud Connector](https://help.sap.com/docs/CP_CONNECTIVITY/cca91383641e40ffbe03bdc78f00f681/e6c7616abb5710148cfcf3e75d96d596.html?locale=en-US) must be configured. A connection to the subaccount in which XSK is deployed needs to be established and the appropriate endpoints to the target system need to be exposed.

## Setup connectivity-proxy
---

XSK running in Kyma needs to have the Connectivity proxy enabled which enables the connectivity between XSK and the on-premise system.

Follow the steps in [SAP Help Portal](https://help.sap.com/products/BTP/65de2977205c403bbc107264b8eccf4b/0c035010a9d64cc8a02d872829c7fa75.html?locale=en-US) on how to enable the connectivity proxy.

## Optional: Mail configuration
---

If the application needs to send e-mails via an on-premise mail server, a destination needs to be configured.

1. Create a destination on subaccount level with the following configuration
```
Name: <destination-name>
Proxy Type: OnPremise
Type: Mail
Authentication: <Select>
----> Additional properties
mail.transport.protocol: smtp
mail.smtp.host: <virtual host>
mail.smtp.port: <virtual port>
mail.smtp.socks.host: connectivity-proxy.kyma-system
mail.smtp.socks.port: 20004
mail.smtp.socks.username: 2.
```

Note: If the connection to the mail server is secured, change the additional property keys from `smtp` to `smtps`.

2. Add the following environment variables to the XSK deployment
```
DIRIGIBLE_MAIL_CONFIG_PROVIDER:  destination
MAIL_SERVER_DESTINATION_NAME:   <destination-name>
```

3. Use the [$.net.Mail API](../../api/net/net.Mail.md) as usual


## HTTP Requests to on-premise system

1. Create a destination on subaccount level with the following configuration
```
Name: <destination-name>
Proxy Type: OnPremise
Type: HTTP
Authentication: <Select>
URL: <Virtual Host and Port>
```

1. Make request with [`$.net.http` API](../../api/net/http/index.md)


### Troubleshooting

If TCP traffic between XSK and connectivity proxy fails you can try to allow port 20004 on both sides:
```
$ kubectl -n kyma-system patch statefulset connectivity-proxy -p '{"spec": {"template":{"metadata":{"annotations":{"traffic.sidecar.istio.io/excludeInboundPorts": "20004"}}}} }'
$ kubectl patch deployment xsk -p '{"spec": {"template":{"metadata":{"annotations":{"traffic.sidecar.istio.io/excludeOutoundPorts": "20004"}}}} }'
```