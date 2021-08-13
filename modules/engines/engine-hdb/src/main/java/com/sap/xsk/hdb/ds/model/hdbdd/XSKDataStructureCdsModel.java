package com.sap.xsk.hdb.ds.model.hdbdd;

import com.sap.xsk.hdb.ds.model.XSKDataStructureModel;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableModel;
import java.util.List;

public class XSKDataStructureCdsModel extends XSKDataStructureModel {

  private List<XSKDataStructureHDBTableModel> tableModels;

  public List<XSKDataStructureHDBTableModel> getTableModels() {
    return tableModels;
  }

  public void setTableModels(List<XSKDataStructureHDBTableModel> tableModels) {
    this.tableModels = tableModels;
  }
}
