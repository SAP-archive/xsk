package com.sap.xsk.integration.tests.applications.status;

public class XSKProjectHttpCheck {

  private final String endpointToCall;
  private final Integer expectedStatusCode;
  private String expectedBodyMessage;

  public XSKProjectHttpCheck(String endpointToCall, Integer expectedStatusCode, String expectedBodyMessage) {
    this(endpointToCall, expectedStatusCode);
    this.expectedBodyMessage = expectedBodyMessage;
  }

  public XSKProjectHttpCheck(String endpointToCall, Integer expectedStatusCode) {
    this.endpointToCall = endpointToCall;
    this.expectedStatusCode = expectedStatusCode;
  }

  public String getEndpointToCall() {
    return endpointToCall;
  }

  public Integer getExpectedStatusCode() {
    return expectedStatusCode;
  }

  public String getExpectedBodyMessage() {
    return expectedBodyMessage;
  }
}
