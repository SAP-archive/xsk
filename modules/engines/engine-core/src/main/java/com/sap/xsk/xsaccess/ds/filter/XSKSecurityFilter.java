/*
 * Copyright (c) 2019-2020 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2019-2020 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.xsaccess.ds.filter;

import java.io.IOException;
import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.dirigible.api.v3.utils.EscapeFacade;
import org.eclipse.dirigible.commons.api.module.StaticInjector;
import org.eclipse.dirigible.commons.config.Configuration;
import org.eclipse.dirigible.repository.api.IRepositoryStructure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.xsk.xsaccess.ds.api.IXSKAccessCoreService;
import com.sap.xsk.xsaccess.ds.api.XSKAccessException;
import com.sap.xsk.xsaccess.ds.model.access.XSKAccessDefinition;
import com.sap.xsk.xsaccess.ds.service.XSKAccessCoreService;
import com.sap.xsk.xsaccess.ds.verifier.XSKAccessVerifier;

@WebFilter(urlPatterns = {

        "/services/v3/js/*",
        "/services/v3/rhino/*",
        "/services/v3/nashorn/*",
        "/services/v3/v8/*",
        "/services/v3/public/*",
        "/services/v3/web/*",
        "/services/v3/wiki/*",
        "/services/v3/command/*",
        "/services/v3/xsk/*",

        "/public/v3/js/*",
        "/public/v3/rhino/*",
        "/public/v3/nashorn/*",
        "/public/v3/v8/*",
        "/public/v3/public/*",
        "/public/v3/web/*",
        "/public/v3/wiki/*",
        "/public/v3/command/*",
        "/public/v3/xsk/*",

        "/services/v4/js/*",
        "/services/v4/rhino/*",
        "/services/v4/nashorn/*",
        "/services/v4/v8/*",
        "/services/v4/public/*",
        "/services/v4/web/*",
        "/services/v4/wiki/*",
        "/services/v4/command/*",
        "/services/v4/xsk/*",

        "/public/v4/js/*",
        "/public/v4/rhino/*",
        "/public/v4/nashorn/*",
        "/public/v4/v8/*",
        "/public/v4/public/*",
        "/public/v4/web/*",
        "/public/v4/wiki/*",
        "/public/v4/command/*",
        "/public/v4/xsk/*"

}, filterName = "XSKSecurityFilter", description = "Check all the URIs for access permissions")
public class XSKSecurityFilter implements Filter {
    private static final String PATH_WEB_RESOURCES = "/web/resources";

    private static final String ROLE_PUBLIC = "Public";

    private static final Logger logger = LoggerFactory.getLogger(XSKSecurityFilter.class);

    private static IXSKAccessCoreService xskAccessCoreService = StaticInjector.getInjector().getInstance(XSKAccessCoreService.class);

    private static final Set<String> SECURED_PREFIXES = new HashSet<String>();

    /*
     * (non-Javadoc)
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        SECURED_PREFIXES.add("/js");
        SECURED_PREFIXES.add("/rhino");
        SECURED_PREFIXES.add("/nashorn");
        SECURED_PREFIXES.add("/v8");
        SECURED_PREFIXES.add("/public");
        SECURED_PREFIXES.add("/web");
        SECURED_PREFIXES.add("/wiki");
        SECURED_PREFIXES.add("/command");
        SECURED_PREFIXES.add("/xsk");
    }

    /*
     * (non-Javadoc)
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse,
     * javax.servlet.FilterChain)
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        try {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;

            String path = httpServletRequest.getPathInfo() != null ? httpServletRequest.getPathInfo() : IRepositoryStructure.SEPARATOR;
            if (!path.startsWith(PATH_WEB_RESOURCES)) {
                for (String prefix : SECURED_PREFIXES) {
                    if (path.startsWith(prefix)) {
                        path = path.substring(prefix.length());
                        break;
                    }
                }
                String method = httpServletRequest.getMethod();

                boolean isInRole = false;
                Principal principal = httpServletRequest.getUserPrincipal();

                XSKAccessDefinition xskAccessDefinition = XSKAccessVerifier.getMatchingAccessDefinitions(xskAccessCoreService, path, method);

                if (xskAccessDefinition != null) {
                    List<String> xskAccessRoles = xskAccessDefinition.getAuthorizationRolesAsList();
                    if (xskAccessRoles.isEmpty()) {
                    	chain.doFilter(request, response);
                    	return;
                    }
                    if (principal == null) {
                        // white list check
                        for (String role : xskAccessRoles) {
                            if (ROLE_PUBLIC.equalsIgnoreCase(role)) {
                                isInRole = true;
                                break;
                            }
                        }

                        if (!isInRole) {
                            forbidden(path, "No logged in user", httpServletResponse);
                            return;
                        }
                    } else {
                        for (String role : xskAccessRoles) {
                            if (ROLE_PUBLIC.equalsIgnoreCase(role) || httpServletRequest.isUserInRole(role)) {
                                isInRole = true;
                                break;
                            }
                        }
                        if (!isInRole) {
                            forbidden(path, "The logged in user does not have any of the required roles for the requested URI", httpServletResponse);
                            return;
                        }
                    }
                } else {
                    if (!Configuration.isAnonymousModeEnabled() && principal == null) {
                        forbidden(path, "No logged in user and no white list constraints", httpServletResponse);
                        return;
                    }
                }
            }
        } catch (IllegalArgumentException e) {
            throw new ServletException(e);
        } catch (XSKAccessException e) {
            throw new ServletException(e);
        }

        chain.doFilter(request, response);
    }

    /**
     * Forbidden.
     *
     * @param uri
     *            the uri
     * @param message
     *            the message
     * @param response
     *            the response
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    private void forbidden(String uri, String message, HttpServletResponse response) throws IOException {
        String error = String.format("Requested URI [%s] is forbidden: %s", uri, message);
        logger.warn(error);
        error = EscapeFacade.escapeHtml4(error);
        error = EscapeFacade.escapeJavascript(error);
        response.sendError(HttpServletResponse.SC_FORBIDDEN, error);
    }

    /*
     * (non-Javadoc)
     * @see javax.servlet.Filter#destroy()
     */
    @Override
    public void destroy() {
        // Not Used
    }

}
