package com.sap.auditlog.client.exceptions;

public class ResourceLimitException extends ServiceException {

  public ResourceLimitException(String message) {
    super(message);
  }

  public ResourceLimitException(String message, Exception e) {
    super(message, e);
  }
}
