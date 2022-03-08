/*
 * Copyright (c) 2022 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2022 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.xsodata.ds.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter(urlPatterns = {"/*"}, filterName = "XSODataForwardFilter")
public class XSODataForwardFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        //only on production case
        if (httpServletRequest.getHeader("Dirigible-Editor") == null) {
            String uri = httpServletRequest.getRequestURI();
            int index = uri.indexOf(".xsodata");
            if (index > 0) {
                String parameters = "";
                if (uri.length() > index + (".xsodata".length() - 1)) {
                    parameters = uri.substring(index + ".xsodata".length());
                }
                RequestDispatcher dispatcher = request.getRequestDispatcher("/odata/v2/" + parameters);
                dispatcher.forward(request, response);
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        //
    }

}
