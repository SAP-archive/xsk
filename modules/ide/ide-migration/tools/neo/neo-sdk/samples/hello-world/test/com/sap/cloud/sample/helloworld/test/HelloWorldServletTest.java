package com.sap.cloud.sample.helloworld.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.SAXException;

import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;
import com.meterware.servletunit.ServletRunner;
import com.meterware.servletunit.ServletUnitClient;
import com.sap.cloud.sample.helloworld.HelloWorldServlet;

/**
 * Unit test for hello world servlet using EasyMock and ServletUnit.
 */
public class HelloWorldServletTest {
    /**
     * Example for a test using EasyMock on a SAP BTP application.
     */
    @Test
    public void testHelloWorldUsingEasyMock() throws IOException, SAXException, ServletException {
        // Create required mock objects
        HttpServletRequest mockRequest = EasyMock.createMock(HttpServletRequest.class);
        HttpServletResponse mockResponse = EasyMock.createMock(HttpServletResponse.class);
        StringWriter mockWriter = new StringWriter();
        EasyMock.expect(mockResponse.getWriter()).andReturn(new PrintWriter(mockWriter));
        EasyMock.replay(mockRequest, mockResponse);

        // Call servlet
        new HelloWorldServlet().doGet(mockRequest, mockResponse);

        // Check for string "Hello" in the returned response
        EasyMock.verify(mockRequest, mockResponse);
        Assert.assertTrue(mockWriter.getBuffer().indexOf("Hello World") != -1);
    }

    /**
     * Example for a test using ServletUnit on a SAP BTP application.
     */
    @Test
    public void testHelloWorldUsingServletUnit() throws IOException, SAXException {
        // Prepare servlet emulation environment
        ServletRunner sr = new ServletRunner();
        sr.registerServlet("hello-world", HelloWorldServlet.class.getName());
        ServletUnitClient sc = sr.newClient();

        // Call servlet
        WebRequest request = new GetMethodWebRequest("http://test.meterware.com/hello-world");
        WebResponse response = sc.getResponse(request);

        // Check for string "Hello" in the returned response
        Assert.assertTrue(response.getText().indexOf("Hello World") != -1);
    }
}
