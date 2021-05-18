package com.sap.auditlog.client.messages;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(
    ignoreUnknown = true
)
@JsonPropertyOrder({"uuid", "user", "time", "id", "success", "object_id", "object", "attributes"})
public class ConfigurationChange extends AuditLogMessage {

  @JsonProperty(value = "attributes", required = true)
  private final List<Attribute> attributes;
  @JsonProperty(value = "object", required = true)
  private final AuditedObjectPOJO object;

  public ConfigurationChange(ConfigurationChangeDetail changeDetails, AuditLogDetail logDetails) {
    super(logDetails);
    this.attributes = changeDetails.attributes;
    this.object = changeDetails.object;
  }

  public List<Attribute> getAttributes() {
    return new ArrayList<>(this.attributes);
  }

  public AuditedObjectPOJO getObject() {
    return this.object;
  }

  @Override
  public AuditLogCategory getCategory() {
    return AuditLogCategory.CONFIGURATION_CHANGE;
  }

  public static class ConfigurationChangeDetail {

    private final List<Attribute> attributes;
    private final AuditedObjectPOJO object;

    public ConfigurationChangeDetail(List<Attribute> attributes, AuditedObjectPOJO object) {
      this.attributes = new ArrayList<>(attributes);
      this.object = object;
    }
  }
}

