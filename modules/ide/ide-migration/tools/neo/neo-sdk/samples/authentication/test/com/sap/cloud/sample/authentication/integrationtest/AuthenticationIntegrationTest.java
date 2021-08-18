package com.sap.cloud.sample.authentication.integrationtest;

import static org.hamcrest.Matchers.containsString;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.SAXException;

import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;

/**
 * Integration test using HttpUnit.
 */
public class AuthenticationIntegrationTest {
    private static String serverUrl, username, password;

    private WebConversation wc;
    private WebRequest request;
    private WebResponse response;

    /**
     * Take a provided server URL usually fed in from outside through the build process or default to the local server
     * as provided through Eclipse to run the integration test against.
     */
    @BeforeClass
    public static void setupSuite() {
        // Get integration test server URL
        serverUrl = System.getProperty("integration.test.server.url");
        if (serverUrl == null) {
            serverUrl = "http://localhost:8080";
        }
        System.out.println("Running against " + serverUrl);

        // Guess integration test scenario, set user name and password
        if (serverUrl.contains("localhost")) {
            username = "john";
            password = "doe";
        } else {
            username = System.getProperty("sap.cloud.username");
            password = System.getProperty("sap.cloud.password");
        }
        System.out.println("Using user " + username);
    }

    /**
     * Prepare a new web conversation for each test (grouping the requests/responses exchanged with the server).
     */
    @Before
    public void setupTest() {
        // Open web conversation
        wc = new WebConversation();
    }

    /**
     * Call the main servlet and check that it contains the expected content.
     */
    @Test
    public void testAuthenticationServlet() throws IOException, SAXException {
        // Call servlet
        request = new GetMethodWebRequest(serverUrl + "/authentication/");
        response = wc.getResponse(request);
        Assert.assertThat(response.getText(), containsString("Authentication"));
    }

    /**
     * Try to login, but without credentials which should fail.
     */
    @Test(expected = com.meterware.httpunit.AuthorizationRequiredException.class)
    public void testBasicAuthenticationRequired() throws IOException, SAXException {
        // Call protected servlet without authentication
        request = new GetMethodWebRequest(serverUrl + "/authentication/protected");
        response = wc.getResponse(request);
    }

    /**
     * Try to login, but with wrong password which should fail.
     */
    @Test(expected = com.meterware.httpunit.AuthorizationRequiredException.class)
    public void testWrongPassword() throws IOException, SAXException {
        // Call protected servlet with wrong authentication
        wc.setAuthorization(username, "");
        request = new GetMethodWebRequest(serverUrl + "/authentication/protected");
        response = wc.getResponse(request);
    }

    /**
     * Try to login with correct credentials which should pass.
     */
    @Test
    public void testSuccessfulLogin() throws IOException, SAXException {
        // Call protected servlet with correct authentication
        wc.setAuthorization(username, password);
        request = new GetMethodWebRequest(serverUrl + "/authentication/protected");
        response = wc.getResponse(request);
        Assert.assertThat(response.getText().toLowerCase(), containsString("welcome"));
    }

    /**
     * Try to logout which should pass.
     */
    @Test
    public void testSuccessfulLogout() throws IOException, SAXException {
        // Call logout servlet with correct authentication
        request = new GetMethodWebRequest(serverUrl + "/authentication/logout");
        response = wc.getResponse(request);
        Assert.assertThat(response.getText(), containsString("No user logged in anymore"));
    }
}
