package models;


public class DefinitionModel {
    private final String schema;
    private final String name;

    public DefinitionModel(String schema, String name) {
        this.schema = schema;
        this.name = name;
    }

    public String getSchema() {
        return schema;
    }

    public String getName() {
        return name;
    }

}
