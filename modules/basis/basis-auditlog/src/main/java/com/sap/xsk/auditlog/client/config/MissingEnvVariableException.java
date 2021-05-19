package com.sap.xsk.auditlog.client.config;

public class MissingEnvVariableException extends Exception {

  public MissingEnvVariableException(final String message) {
    super(message);
  }
}
