package com.sap.xsk.hdb.ds.parser.hdbschema;

import java.sql.Timestamp;

import org.apache.commons.codec.digest.DigestUtils;
import org.eclipse.dirigible.api.v3.security.UserFacade;

import com.sap.xsk.hdb.ds.api.IXSKDataStructureModel;
import com.sap.xsk.hdb.ds.model.hdbschema.XSKDataStructureHDBSchema;
import com.sap.xsk.hdb.ds.parser.XSKDataStructureParser;

public class XSKHDBSchemaParser implements XSKDataStructureParser {
  @Override
  public String getType() {
    return IXSKDataStructureModel.TYPE_HDB_SCHEMA;
  }

  @Override
  public Class getDataStructureClass() {
    return XSKDataStructureHDBSchema.class;
  }

  @Override
  public XSKDataStructureHDBSchema parse(String location, String content) {
    XSKDataStructureHDBSchema hdbProcedure = new XSKDataStructureHDBSchema();
    hdbProcedure.setName(extractSchemaNameFromContent(content));
    hdbProcedure.setLocation(location);
    hdbProcedure.setType(IXSKDataStructureModel.TYPE_HDB_SCHEMA);
    hdbProcedure.setHash(DigestUtils.md5Hex(content));
    hdbProcedure.setCreatedBy(UserFacade.getName());
    hdbProcedure.setCreatedAt(new Timestamp(new java.util.Date().getTime()));
    return hdbProcedure;
  }

  private String extractSchemaNameFromContent(String content) {
    Integer indexOfEquals = content.indexOf('=');
    String schemaName = content.substring(indexOfEquals + 1);
    return schemaName.replaceAll("\\s", "");
  }
}
