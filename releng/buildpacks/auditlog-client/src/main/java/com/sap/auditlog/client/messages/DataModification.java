package com.sap.auditlog.client.messages;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(
    ignoreUnknown = true
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"uuid", "user", "time", "object_id", "object", "data_subject", "attributes", "data"})
public class DataModification extends AuditLogMessage {

  @JsonProperty(value = "attributes", required = true)
  private final List<Attribute> attributes;
  @JsonProperty(value = "data_subject", required = true)
  private final AuditedDataSubjectPOJO subject;
  @JsonProperty(value = "object", required = true)
  private final AuditedObjectPOJO object;

  public DataModification(DataModificationDetail modificationDetails, AuditLogDetail logDetails) {
    super(logDetails);
    this.object = modificationDetails.object;
    this.subject = modificationDetails.subject;
    this.attributes = modificationDetails.attributes;
  }

  public List<Attribute> getAttributes() {
    return new ArrayList<>(this.attributes);
  }

  public AuditedDataSubjectPOJO getSubject() {
    return subject;
  }

  public AuditedObjectPOJO getObject() {
    return object;
  }

  @Override
  public AuditLogCategory getCategory() {
    return AuditLogCategory.DATA_MODIFICATION;
  }

  public static class DataModificationDetail {

    private final AuditedDataSubjectPOJO subject;
    private final AuditedObjectPOJO object;
    private final List<Attribute> attributes;

    public DataModificationDetail(AuditedDataSubjectPOJO subject, AuditedObjectPOJO object, List<Attribute> attributes) {
      this.subject = subject;
      this.object = object;
      this.attributes = new ArrayList<>(attributes);
    }

  }
}

