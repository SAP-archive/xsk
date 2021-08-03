/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2021 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.xsuaa;

import javax.servlet.http.HttpServlet;

//@WebServlet("/hello-token-client")
//@ServletSecurity(@HttpConstraint(rolesAllowed = { "Display" }))
public class HelloTokenClientServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
//	 * response)
//	 */
//	@Override
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		response.setContentType("text/plain");
//
//		JSONObject jsonObject = createJsonObjectFromVCAPServices();
//		String clientSecret = extractString(jsonObject, "/xsuaa/0/credentials/clientsecret");
//		String clientid = extractString(jsonObject, "/xsuaa/0/credentials/clientid");
//		String url = extractString(jsonObject, "/xsuaa/0/credentials/url");
//
//		XsuaaTokenFlows tokenFlows = new XsuaaTokenFlows(
//				new DefaultOAuth2TokenService(),
//				new XsuaaDefaultEndpoints(url), new ClientCredentials(clientid, clientSecret));
//		OAuth2TokenResponse tokenResponse = tokenFlows.clientCredentialsTokenFlow().execute();
//
//		writeLine(response, "Access-Token: " + tokenResponse.getAccessToken());
//		writeLine(response, "Access-Token-Payload: " + tokenResponse.getDecodedAccessToken().getPayload());
//		writeLine(response, "Expired-At: " + tokenResponse.getExpiredAtDate());
//
//	}
//
//	private String extractString(JSONObject jsonObject, String jsonPointer) {
//		return jsonObject.query(jsonPointer).toString();
//	}
//
//	private JSONObject createJsonObjectFromVCAPServices() {
//		String vcapServices = System.getenv("VCAP_SERVICES");
//		return new JSONObject(vcapServices);
//	}
//
//	private void writeLine(HttpServletResponse response, String string) throws IOException {
//		response.getWriter().append(string);
//		response.getWriter().append("\n");
//	}

}