package models;

import exceptions.TableFunctionMissingPropertyException;
import java.util.Objects;

public class TableFunctionDefinitionModel {

  private String schema;
  private String name;

  public String getSchema() {
    return schema;
  }

  public void setSchema(String schema) {
    this.schema = schema;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void checkForAllMandatoryFieldsPresence() {
    checkPresence(schema, "schema");
    checkPresence(name, "name");
  }

  private <T> void checkPresence(T field, String fieldName) {
    if (Objects.isNull(field)) {
      throw new TableFunctionMissingPropertyException(String.format("Missing mandatory field %s!", fieldName));
    }
  }
}
