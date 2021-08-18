package com.sap.cloud.sample.mail.integrationtest;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebForm;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;

/**
 * Integration test using HttpUnit.
 */
public class MailIntegrationTest {
    private static String serverUrl;
    private WebConversation wc;
    private WebRequest request;
    private WebResponse response;

    /**
     * Take a provided server URL usually fed in from outside through the build process or default
     * to the local server as provided through Eclipse to run the integration test against.
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
     * Prepare a new web conversation for each test (grouping the requests/responses exchanged with
     * the server).
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
    public void testMailServlet() throws Exception {
        // Call servlet
        request = new GetMethodWebRequest(serverUrl + "/mail/");
        response = wc.getResponse(request);

        // Fill out form and send mail
        final WebForm form = response.getForms()[0];
        if (serverUrl.contains("localhost")) {
            form.setParameter("fromaddress", "local-test@sap.com");
            form.setParameter("toaddress", "local-test@sap.com");
        } else {
            form.setParameter("fromaddress", System.getProperty("mail.from.address"));
            form.setParameter("toaddress", System.getProperty("mail.to.address"));
        }
        form.setParameter("subjecttext", "Hello World!");
        form.setParameter("mailtext", "Hello World!");
        response = form.submit();

        // Check that we could send mail
        assertThat(response.getResponseCode(), is(equalTo(HttpServletResponse.SC_OK)));
        assertThat(response.getText(), containsString("E-mail was sent"));
    }
}
