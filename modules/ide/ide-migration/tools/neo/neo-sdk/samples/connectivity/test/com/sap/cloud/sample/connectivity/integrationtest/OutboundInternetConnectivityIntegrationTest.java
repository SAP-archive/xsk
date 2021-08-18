package com.sap.cloud.sample.connectivity.integrationtest;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.SAXException;

import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.HttpUnitOptions;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;

import static java.net.HttpURLConnection.HTTP_OK;

/**
 * Integration test using HttpUnit.
 */
public class OutboundInternetConnectivityIntegrationTest {
    private static String serverUrl;
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
    public void testOutboundInternetConnectivityServlet() throws IOException, SAXException {
        // Call servlet
        HttpUnitOptions.setScriptingEnabled(false);
        request = new GetMethodWebRequest(serverUrl + "/connectivity");
        response = wc.getResponse(request);
        int responseCode = response.getResponseCode();
        Assert.assertEquals(HTTP_OK, responseCode);
    }

}
