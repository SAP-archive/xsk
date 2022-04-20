package com.xsk.integration.tests.applications.kyma;

import com.xsk.integration.tests.applications.deployment.XSKKymaException;
import org.apache.http.HttpHeaders;
import org.apache.http.client.utils.URIBuilder;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

public class TokenProvider {
    KymaCredentials kymaCredentials = new KymaCredentials();

    public String getToken() {
        try {
            String token64 = Base64.getEncoder().encodeToString((kymaCredentials.getClientId() + ":" + kymaCredentials.getClientSecret()).getBytes());
            URIBuilder queryBuilder = new URIBuilder(kymaCredentials.getTokenUrl());
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(queryBuilder.setParameter("grant_type", kymaCredentials.getGrantType())
                            .setParameter("username", kymaCredentials.getUsername())
                            .setParameter("password", kymaCredentials.getPassword())
                            .build())
                    .setHeader(HttpHeaders.AUTHORIZATION, "Basic " + token64)
                    .build();

            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
            JSONObject objJsonObject = new JSONObject(response.body());
            return objJsonObject.getString("access_token");
        } catch (RuntimeException | IOException | InterruptedException | URISyntaxException e) {
            throw new XSKKymaException("Can't access JWT Token for kyma instance"+ e);
        }
    }
}
