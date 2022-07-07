package com.sap.xsk.integration.tests.applications.status;

public class ProjectHttpCheck {

  private final String endpointToCall;
  private final Integer expectedStatusCode;
  private String expectedBodyMessage;

  public ProjectHttpCheck(String endpointToCall, Integer expectedStatusCode, String expectedBodyMessage) {
    this(endpointToCall, expectedStatusCode);
    this.expectedBodyMessage = expectedBodyMessage;
  }

  public ProjectHttpCheck(String endpointToCall, Integer expectedStatusCode) {
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
