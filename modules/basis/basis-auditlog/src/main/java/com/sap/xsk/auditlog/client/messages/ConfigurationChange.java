package com.sap.xsk.auditlog.client.messages;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class ConfigurationChange extends AuditLogMessage {

  @JsonProperty(value = "attributes", required = true)
  private final List<Attribute> attributes;
  @JsonProperty(value = "object", required = true)
  private final AuditedObject object;

  public ConfigurationChange(ConfigurationChangeDetail changeDetails, AuditLogDetail logDetails) {
    super(logDetails);
    this.attributes = changeDetails.attributes;
    this.object = changeDetails.object;
  }

  public List<Attribute> getAttributes() {
    return new ArrayList<>(this.attributes);
  }

  public AuditedObject getObject() {
    return this.object;
  }

  @JsonIgnore
  @Override
  public AuditLogCategory getCategory() {
    return AuditLogCategory.CONFIGURATION_CHANGE;
  }

  public static class ConfigurationChangeDetail {

    private final List<Attribute> attributes;
    private final AuditedObject object;

    public ConfigurationChangeDetail(List<Attribute> attributes, AuditedObject object) {
      this.attributes = new ArrayList<>(attributes);
      this.object = object;
    }
  }
}

