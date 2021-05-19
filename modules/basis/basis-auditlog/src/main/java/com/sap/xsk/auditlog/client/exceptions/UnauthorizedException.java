package com.sap.xsk.auditlog.client.exceptions;

public class UnauthorizedException extends ServiceException {

  public UnauthorizedException(String message) {
    super(message);
  }

  public UnauthorizedException(String message, Exception e) {
    super(message, e);
  }
}
