package com.sap.cloud.sample.helloworld;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementing simplest possible hello world application for SAP BTP Neo Environment.
 */
public class HelloWorldServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /** {@inheritDoc} */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().println("<p>Hello World!</p>");
    }
}
