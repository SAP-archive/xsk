package com.sap.cloud.sample.connectivity;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.cloud.account.TenantContext;
import com.sap.core.connectivity.api.configuration.ConnectivityConfiguration;
import com.sap.core.connectivity.api.configuration.DestinationConfiguration;

/**
 * Servlet class making http calls to specified http destinations.
 * Destinations are used in the following example connectivity scenarios:<br>
 * - Connecting to an outbound Internet resource using HTTP destinations<br>
 * - Connecting to an on-premise backend using on premise HTTP destinations,<br>
 *   where the destinations have no authentication.<br>
 */
public class ConnectivityServlet extends HttpServlet {
    @Resource
    private TenantContext  tenantContext;

    private static final long serialVersionUID = 1L;
    private static final int COPY_CONTENT_BUFFER_SIZE = 1024;
    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectivityServlet.class);

    private static final String ON_PREMISE_PROXY = "OnPremise";

    /** {@inheritDoc} */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpURLConnection urlConnection = null;
        String destinationName = request.getParameter("destname");

        // The default request to the Servlet will use outbound-internet-destination
        if (destinationName == null) {
            destinationName = "outbound-internet-destination";
        }

        try {
            // Look up the connectivity configuration API
            Context ctx = new InitialContext();
            ConnectivityConfiguration configuration =
                    (ConnectivityConfiguration) ctx.lookup("java:comp/env/connectivityConfiguration");

            // Get destination configuration for "destinationName"
            DestinationConfiguration destConfiguration = configuration.getConfiguration(destinationName);

            if (destConfiguration == null) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                        String.format("Destination %s is not found. Hint:"
                                + " Make sure to have the destination configured.", destinationName));
                return;
            }

            // Get the destination URL
            String value = destConfiguration.getProperty("URL");
            URL url = new URL(value);

            String proxyType = destConfiguration.getProperty("ProxyType");
            Proxy proxy = getProxy(proxyType);

            urlConnection = (HttpURLConnection) url.openConnection(proxy);

            injectHeader(urlConnection, proxyType);

            // Copy content from the incoming response to the outgoing response
            InputStream instream = urlConnection.getInputStream();
            OutputStream outstream = response.getOutputStream();
            copyStream(instream, outstream);
        } catch (Exception e) {
            // Connectivity operation failed
            String errorMessage = "Connectivity operation failed with reason: "
                    + e.getMessage()
                    + ". See "
                    + "logs for details. Hint: Make sure to have an HTTP proxy configured in your "
                    + "local environment in case your environment uses "
                    + "an HTTP proxy for the outbound Internet "
                    + "communication.";
            LOGGER.error("Connectivity operation failed", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    errorMessage);
        }
    }

    private Proxy getProxy(String proxyType) {
        Proxy proxy = Proxy.NO_PROXY;
        String proxyHost = null;
        String proxyPort = null;

        if (ON_PREMISE_PROXY.equals(proxyType)) {
            // Get proxy for on-premise destinations
            proxyHost = System.getenv("HC_OP_HTTP_PROXY_HOST");
            proxyPort = System.getenv("HC_OP_HTTP_PROXY_PORT");
        } else {
            // Get proxy for internet destinations
            proxyHost = System.getProperty("https.proxyHost");
            proxyPort = System.getProperty("https.proxyPort");
        }

        if (proxyPort != null && proxyHost != null) {
            int proxyPortNumber = Integer.parseInt(proxyPort);
            proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPortNumber));
        }

        return proxy;
    }

    private void injectHeader(HttpURLConnection urlConnection, String proxyType) {
        if (ON_PREMISE_PROXY.equals(proxyType)) {
            // Insert header for on-premise connectivity with the consumer account name
            urlConnection.setRequestProperty("SAP-Connectivity-ConsumerAccount",
                    tenantContext.getTenant().getAccount().getId());
        }
    }

    private void copyStream(InputStream inStream, OutputStream outStream) throws IOException {
        byte[] buffer = new byte[COPY_CONTENT_BUFFER_SIZE];
        int len;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
    }
}