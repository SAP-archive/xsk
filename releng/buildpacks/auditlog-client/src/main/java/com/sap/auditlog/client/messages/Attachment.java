package com.sap.auditlog.client.messages;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonIgnoreProperties(
    ignoreUnknown = true
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"id", "name"})
public class Attachment {

  @JsonProperty(value = "id", required = true)
  private final String id;
  @JsonProperty(value = "name", required = true)
  private final String name;
  @JsonProperty("successful")
  private final Boolean successful;

  public Attachment(String id, String name) {
    this.id = id;
    this.name = name;
    this.successful = null;
  }

  public Attachment(String id, String name, Boolean successful) {
    this.id = id;
    this.name = name;
    this.successful = successful;
  }

  public String getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public Boolean getSuccessful() {
    return this.successful;
  }
}

