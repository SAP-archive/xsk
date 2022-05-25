package models;

import java.util.ArrayList;
import java.util.List;

public class FromClauseDefinitionModel {

  private List<JoinClauseDefinitionModel> joinClauseDefinitionModels;

  private String tableName ;
  private String tableAliasName ;

  public FromClauseDefinitionModel() {
    this.joinClauseDefinitionModels = new ArrayList<>();
  }
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

  public List<JoinClauseDefinitionModel> getJoinClauseDefinitionModels() {
    return joinClauseDefinitionModels;
  }

  public void addJoinClauseDefinitionModel(JoinClauseDefinitionModel joinClauseDefinitionModel) {
    this.joinClauseDefinitionModels.add(joinClauseDefinitionModel);
  }
}
