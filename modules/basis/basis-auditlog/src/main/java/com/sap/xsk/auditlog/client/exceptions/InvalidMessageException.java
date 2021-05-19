package com.sap.xsk.auditlog.client.exceptions;

public class InvalidMessageException extends ServiceException {

  public InvalidMessageException(String message) {
    super(message);
  }

  public InvalidMessageException(String message, Exception e) {
    super(message, e);
  }
}
