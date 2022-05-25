package models;

public class UpdateStatementDefinitionModel {
  private String tableName ;
  private String tableAliasName ;

  private FromClauseDefinitionModel fromClauseDefinitionModel;
  private WhereClauseDefinitionModel whereClauseDefinitionModel;
  private UpdateSetClauseDefinitionModel updateSetClauseDefinitionModel;

  public String getTableName() {
    return tableName;
  }

  public void setTableName(String tableName) {
    this.tableName = tableName;
  }

  public String getTableAliasName() {
    return tableAliasName;
  }

  public void setTableAliasName(String tableAliasName) {
    this.tableAliasName = tableAliasName;
  }

  public FromClauseDefinitionModel getFromClauseDefinitionModel() {
    return fromClauseDefinitionModel;
  }

  public void setFromClauseDefinitionModel(FromClauseDefinitionModel fromClauseDefinitionModel) {
    this.fromClauseDefinitionModel = fromClauseDefinitionModel;
  }

  public WhereClauseDefinitionModel getWhereClauseDefinitionModel() {
    return whereClauseDefinitionModel;
  }

  public void setWhereClauseDefinitionModel(WhereClauseDefinitionModel whereClauseDefinitionModel) {
    this.whereClauseDefinitionModel = whereClauseDefinitionModel;
  }

  public UpdateSetClauseDefinitionModel getSetClauseDefinitionModel() {
    return updateSetClauseDefinitionModel;
  }

  public void setSetClauseDefinitionModel(UpdateSetClauseDefinitionModel updateSetClauseDefinitionModel) {
    this.updateSetClauseDefinitionModel = updateSetClauseDefinitionModel;
  }
}
