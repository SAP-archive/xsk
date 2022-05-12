package com.sap.xsk.hdb.ds.model.hdi;

public class XSKInvalidNameException extends RuntimeException {
  public XSKInvalidNameException (String message, Throwable cause) {
    super(message, cause);
  }
  public XSKInvalidNameException (String message) {
    super(message);
  }
}
