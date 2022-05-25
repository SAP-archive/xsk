package models;

import java.util.ArrayList;
import java.util.List;

public class ProcedureDefinitionModel {

  private List<UpdateStatementDefinitionModel> updateStatements;

  public ProcedureDefinitionModel() {
    this.updateStatements = new ArrayList<>();
  }

  public List<UpdateStatementDefinitionModel> getUpdateStatements() {
    return updateStatements;
  }

  public void addUpdateStatement(UpdateStatementDefinitionModel updateStatement) {
    this.updateStatements.add(updateStatement);
  }
}
