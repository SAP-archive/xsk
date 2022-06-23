package com.sap.xsk.engine;

import org.eclipse.dirigible.commons.api.context.ContextException;
import org.junit.Test;

import javax.ws.rs.core.Response.Status;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class XSKContextExceptionHandlerTest {
  @Test
  public void XSKContextExceptionHandlerTest() {
    XSKContextExceptionHandler handler = new XSKContextExceptionHandler();
    ContextException testException = new ContextException("test");
    assertEquals(XSKContextExceptionHandler.class, handler.getType());
    assertNotNull(handler.getLogger());
    assertEquals(Status.INTERNAL_SERVER_ERROR, handler.getResponseStatus(testException));
    assertEquals("test", handler.getResponseMessage(testException));
  }
}
