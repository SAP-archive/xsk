package com.sap.xsk.auditlog.client.messages;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(
    ignoreUnknown = true
)
@JsonInclude(Include.NON_NULL)
public class SecurityAttribute {

  @JsonProperty("name")
  private final String name;
  @JsonProperty("value")
  private final String value;

  public SecurityAttribute(String name, String value) {
    this.name = name;
    this.value = value;
  }

  public String getName() {
    return this.name;
  }

  public String getValue() {
    return this.value;
  }
}

