package com.sap.cloud.sample.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet implementing lookup of currently logged in user.
 */
public class AuthenticationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationServlet.class);

    /** {@inheritDoc} */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Greet guest user
            response.getWriter().println("<p>Authentication Sample!</p>");

            // Render link to protected area
            response.getWriter().println(
                    "<p><a href=\"protected\">Access Protected Area Now</a> "
                            + "(in the local scenario the user name is 'john' and the password is 'doe' and "
                            + "in cloud scenario use your SAP BTP credentials)</p>");
        } catch (Exception e) {
            // Lookup operation failed
            response.getWriter().println("Lookup operation failed with reason: " + e.getMessage());
            LOGGER.error("Lookup operation failed", e);
        }
    }
}
