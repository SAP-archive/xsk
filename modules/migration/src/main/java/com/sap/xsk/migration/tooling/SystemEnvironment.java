package com.sap.xsk.migration.tooling;

class SystemEnvironment {

  public String getEnvironmentVariableValue(String environmentVariableName) {
    return System.getenv(environmentVariableName);
  }
}
