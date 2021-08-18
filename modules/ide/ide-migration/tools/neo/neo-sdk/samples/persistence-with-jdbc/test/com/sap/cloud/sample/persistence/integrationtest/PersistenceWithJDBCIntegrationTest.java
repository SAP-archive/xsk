package com.sap.cloud.sample.persistence.integrationtest;

import static org.hamcrest.Matchers.containsString;

import java.io.IOException;
import java.util.Random;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.SAXException;

import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebForm;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;

/**
 * Integration test using HttpUnit.
 */
public class PersistenceWithJDBCIntegrationTest {
    private static String serverUrl;
    private static String nameSupplement;

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

        // Compute random name supplement that is appended and checked for later during the tests
        nameSupplement = Long.toHexString(new Random(System.currentTimeMillis()).nextLong());
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
    public void testPersistenceWithJDBCServlet() throws IOException, SAXException {
        // Call servlet
        request = new GetMethodWebRequest(serverUrl + "/persistence-with-jdbc/");
        response = wc.getResponse(request);
        Assert.assertThat(response.getText(), containsString("Persistence with JDBC"));
    }

    /**
     * Try to add one person and check that only this person is found.
     */
    @Test
    public void testAddingPerson() throws IOException, SAXException {
        // Call servlet
        request = new GetMethodWebRequest(serverUrl + "/persistence-with-jdbc/");
        response = wc.getResponse(request);

        // Fill out form and submit a person
        WebForm form = response.getForms()[0];
        form.setParameter("FirstName", "John_" + nameSupplement);
        form.setParameter("LastName", "Doe_" + nameSupplement);
        response = form.submit();
        Assert.assertThat(response.getText(), containsString("John_" + nameSupplement));
        Assert.assertThat(response.getText(), containsString("Doe_" + nameSupplement));
    }
}
