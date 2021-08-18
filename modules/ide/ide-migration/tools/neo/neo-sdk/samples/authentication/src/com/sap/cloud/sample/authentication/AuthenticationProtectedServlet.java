package com.sap.cloud.sample.authentication;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.security.um.service.UserManagementAccessor;
import com.sap.security.um.user.PersistenceException;
import com.sap.security.um.user.UnsupportedUserAttributeException;
import com.sap.security.um.user.User;
import com.sap.security.um.user.UserProvider;

/**
 * Servlet allowing access only to authenticated users.
 */
public class AuthenticationProtectedServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationProtectedServlet.class);

    /** {@inheritDoc} */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Show name of logged in user
            response.getWriter().println("<p>Welcome " + getUserAttributes(request.getUserPrincipal()) + "</p>");

            // Render link to logout
            response.getWriter().println("<p><a href=\"logout\">Logout Now</a></p>");
        } catch (Exception e) {
            // Login operation failed
            response.getWriter().println("Protected operation failed with reason: " + e.getMessage());
            LOGGER.error("Protected operation failed", e);
        }
    }

    /**
     * Get name and e-mail user attributes and return them as condensed string.
     */
    private String getUserAttributes(Principal principal) throws PersistenceException,
            UnsupportedUserAttributeException {
        // Get user from user storage based on principal name
        UserProvider userProvider = UserManagementAccessor.getUserProvider();
        User user = userProvider.getUser(principal.getName());

        // Extract and return user name and e-mail address if present
        String firstName = user.getAttribute("firstname");
        String lastName = user.getAttribute("lastname");
        String eMail = user.getAttribute("email");
        return (firstName != null && lastName != null ? firstName + " " + lastName + " [" + principal.getName() + "]"
                : principal.getName()) + (eMail != null ? " (" + eMail + ")" : "");
    }
}
