/*
 * Copyright (c) 2022 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2022 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.api.destination;

import com.google.gson.JsonObject;
import com.sap.cloud.sdk.cloudplatform.ScpCfCloudPlatform;
import com.sap.cloud.sdk.cloudplatform.exception.CloudPlatformException;
import org.eclipse.dirigible.commons.config.Configuration;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CloudPlatform extends ScpCfCloudPlatform {

	private static final String CLIENT_ID = "clientid";
	private static final String CLIENT_SECRET = "clientsecret";
	private static final String URL = "url";
	private static final String URI = "uri";

	private static final String ONPREMISE_PROXY_HOST = "onpremise_proxy_host";
	private static final String ONPREMISE_PROXY_HTTP_PORT = "onpremise_proxy_http_port";
	private static final String ONPREMISE_PROXY_LDAP_PORT = "onpremise_proxy_ldap_port";
	private static final String ONPREMISE_PROXY_PORT = "onpremise_proxy_port";
	private static final String ONPREMISE_PROXY_RFC_PORT = "onpremise_proxy_rfc_port";
	private static final String ONPREMISE_SOCKS5_PROXY_PORT = "onpremise_socks5_proxy_port";

	private static final String DIRIGIBLE_DESTINATION_CLIENT_ID = "DIRIGIBLE_DESTINATION_CLIENT_ID";
	private static final String DIRIGIBLE_DESTINATION_CLIENT_SECRET = "DIRIGIBLE_DESTINATION_CLIENT_SECRET";
	private static final String DIRIGIBLE_DESTINATION_URL = "DIRIGIBLE_DESTINATION_URL";
	private static final String DIRIGIBLE_DESTINATION_URI = "DIRIGIBLE_DESTINATION_URI";

	private static final String DIRIGIBLE_CONNECTIVITY_CLIENT_ID = "DIRIGIBLE_CONNECTIVITY_CLIENT_ID";
	private static final String DIRIGIBLE_CONNECTIVITY_CLIENT_SECRET = "DIRIGIBLE_CONNECTIVITY_CLIENT_SECRET";
	private static final String DIRIGIBLE_CONNECTIVITY_URL = "DIRIGIBLE_CONNECTIVITY_URL";
	private static final String DIRIGIBLE_CONNECTIVITY_URI = "DIRIGIBLE_CONNECTIVITY_URI";
	private static final String DIRIGIBLE_CONNECTIVITY_ONPREMISE_PROXY_HOST = "DIRIGIBLE_CONNECTIVITY_ONPREMISE_PROXY_HOST";
	private static final String DIRIGIBLE_CONNECTIVITY_ONPREMISE_PROXY_HTTP_PORT = "DIRIGIBLE_CONNECTIVITY_ONPREMISE_PROXY_HTTP_PORT";
	private static final String DIRIGIBLE_CONNECTIVITY_ONPREMISE_PROXY_LDAP_PORT = "DIRIGIBLE_CONNECTIVITY_ONPREMISE_PROXY_LDAP_PORT";
	private static final String DIRIGIBLE_CONNECTIVITY_ONPREMISE_PROXY_PORT = "DIRIGIBLE_CONNECTIVITY_ONPREMISE_PROXY_PORT";
	private static final String DIRIGIBLE_CONNECTIVITY_ONPREMISE_PROXY_RFC_PORT = "DIRIGIBLE_CONNECTIVITY_ONPREMISE_PROXY_RFC_PORT";
	private static final String DIRIGIBLE_CONNECTIVITY_ONPREMISE_SOCKS5_PROXY_PORT = "DIRIGIBLE_CONNECTIVITY_ONPREMISE_SOCKS5_PROXY_PORT";

	@Nonnull
	@Override
	public JsonObject getServiceCredentials(@Nonnull String serviceName, @Nullable String servicePlan) throws CloudPlatformException {
		JsonObject credentialsObject = new JsonObject();
		switch (serviceName) {
		case "destination":
			credentialsObject.addProperty(CLIENT_ID, Configuration.get(DIRIGIBLE_DESTINATION_CLIENT_ID));
			credentialsObject.addProperty(CLIENT_SECRET, Configuration.get(DIRIGIBLE_DESTINATION_CLIENT_SECRET));
			credentialsObject.addProperty(URL, Configuration.get(DIRIGIBLE_DESTINATION_URL));
			credentialsObject.addProperty(URI, Configuration.get(DIRIGIBLE_DESTINATION_URI));
			break;
		case "connectivity":
			credentialsObject.addProperty(CLIENT_ID, Configuration.get(DIRIGIBLE_CONNECTIVITY_CLIENT_ID));
			credentialsObject.addProperty(CLIENT_SECRET, Configuration.get(DIRIGIBLE_CONNECTIVITY_CLIENT_SECRET));
			credentialsObject.addProperty(URL, Configuration.get(DIRIGIBLE_CONNECTIVITY_URL));
			credentialsObject.addProperty(URI, Configuration.get(DIRIGIBLE_CONNECTIVITY_URI));
			credentialsObject.addProperty(ONPREMISE_PROXY_HOST, Configuration.get(DIRIGIBLE_CONNECTIVITY_ONPREMISE_PROXY_HOST));
			credentialsObject.addProperty(ONPREMISE_PROXY_HTTP_PORT, Configuration.get(DIRIGIBLE_CONNECTIVITY_ONPREMISE_PROXY_HTTP_PORT));
			credentialsObject.addProperty(ONPREMISE_PROXY_LDAP_PORT, Configuration.get(DIRIGIBLE_CONNECTIVITY_ONPREMISE_PROXY_LDAP_PORT));
			credentialsObject.addProperty(ONPREMISE_PROXY_PORT, Configuration.get(DIRIGIBLE_CONNECTIVITY_ONPREMISE_PROXY_PORT));
			credentialsObject.addProperty(ONPREMISE_PROXY_RFC_PORT, Configuration.get(DIRIGIBLE_CONNECTIVITY_ONPREMISE_PROXY_RFC_PORT));
			credentialsObject.addProperty(ONPREMISE_SOCKS5_PROXY_PORT, Configuration.get(DIRIGIBLE_CONNECTIVITY_ONPREMISE_SOCKS5_PROXY_PORT));
			break;
		default:
			throw new IllegalArgumentException(
					"Retrieving credentials for service [" + serviceName + "] not supported");
		}

		return credentialsObject;
	}

}
