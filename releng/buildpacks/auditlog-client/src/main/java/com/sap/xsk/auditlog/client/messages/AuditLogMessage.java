package com.sap.xsk.auditlog.client.messages;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.sap.xsk.auditlog.client.deserializers.InstantDeserializer;
import java.time.Instant;
import java.util.Objects;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public abstract class AuditLogMessage {

  private static final String SUBSCRIBER_TENANT = "$SUBSCRIBER";
  private static final String PROVIDER_TENANT = "$PROVIDER";
  @JsonProperty(value = "user", required = true)
  private final String user = "$USER";
  @JsonProperty(value = "uuid", required = true)
  private final String uuid;
  @JsonProperty(value = "tenant", required = true)
  private final String tenant;
  @JsonProperty(value = "time", required = true)
  @JsonSerialize(using = ToStringSerializer.class)
  @JsonDeserialize(using = InstantDeserializer.class)
  private final Instant time;
  @JsonIgnore
  private final String subscriberTokenIssuer;

  public AuditLogMessage(AuditLogDetail config) {
    this.uuid = config.uuid;
    this.time = config.time;
    this.subscriberTokenIssuer = config.subscriberTokenIssuer;
    if (Objects.isNull(this.subscriberTokenIssuer)) {
      this.tenant = PROVIDER_TENANT;
    } else {
      this.tenant = SUBSCRIBER_TENANT;
    }
  }

  public String getUuid() {
    return this.uuid;
  }

  public String getTenant() {
    return this.tenant;
  }

  public String getSubscriberTokenIssuer() {
    return this.subscriberTokenIssuer;
  }

  public Instant getTime() {
    return this.time;
  }

  @JsonIgnore
  public abstract AuditLogCategory getCategory();

  public static class AuditLogDetail {

    private final String uuid;
    private final Instant time;
    private final String subscriberTokenIssuer;

    public AuditLogDetail(String uuid, Instant time, String subscriberTokenIssuer) {
      this.uuid = uuid;
      this.time = time;
      this.subscriberTokenIssuer = subscriberTokenIssuer;
    }

    public AuditLogDetail(String uuid, Instant time) {
      this.uuid = uuid;
      this.time = time;
      this.subscriberTokenIssuer = null;
    }
  }
}

