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
@JsonPropertyOrder({"type", "id"})
public class AuditedObjectPOJO {

  @JsonProperty("type")
  private final String type;
  @JsonProperty("id")
  private final Map<String, String> id;

  public AuditedObjectPOJO(String type) {
    this.type = type;
    this.id = new LinkedHashMap<>();
  }

  public AuditedObjectPOJO(String type, Map<String, String> identifiers) {
    this.type = type;
    this.id = new LinkedHashMap<>(identifiers);
  }

  @JsonIgnore
  public String getType() {
    return this.type;
  }

  @JsonIgnore
  public Map<String, String> getId() {
    return new LinkedHashMap<>(this.id);
  }
}

