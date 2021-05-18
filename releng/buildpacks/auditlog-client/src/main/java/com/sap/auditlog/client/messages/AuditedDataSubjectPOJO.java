package com.sap.auditlog.client.messages;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonIgnoreProperties(
    ignoreUnknown = true
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"type", "role", "id"})
public class AuditedDataSubjectPOJO {

  @JsonProperty("type")
  private final String type;
  @JsonProperty("role")
  private final String role;
  @JsonProperty("id")
  private final Map<String, String> id;

  public AuditedDataSubjectPOJO(String type, String role, Map<String, String> identifiers) {
    this.type = type;
    this.role = role;
    this.id = new LinkedHashMap<>(identifiers);
  }

  public AuditedDataSubjectPOJO(String type, Map<String, String> identifiers) {
    this.type = type;
    this.id = new LinkedHashMap<>(identifiers);
    this.role = null;
  }

  @JsonIgnore
  public String getType() {
    return this.type;
  }

  @JsonIgnore
  public String getRole() {
    return this.role;
  }


  @JsonIgnore
  public LinkedHashMap<String, String> getId() {
    return new LinkedHashMap<>(id);
  }
}

