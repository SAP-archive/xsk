/*
 * Copyright (c) 2022 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2022 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.modificators;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import com.sap.xsk.parser.hana.core.HanaLexer;
import com.sap.xsk.parser.hana.core.HanaParser;
import com.sap.xsk.utils.XSKCommonsConstants;
import com.sap.xsk.utils.XSKCommonsUtils;
import custom.HanaProcedureUpdateStatementListener;
import models.FromClauseDefinitionModel;
import models.JoinClauseDefinitionModel;
import models.ProcedureDefinitionModel;
import models.TableReferenceModel;
import models.UpdateSetClauseDefinitionModel;
import models.UpdateStatementDefinitionModel;
import models.WhereClauseDefinitionModel;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.dirigible.core.workspace.api.IFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XSKProjectFilesModificator {

  private static final Logger logger = LoggerFactory.getLogger(XSKProjectFilesModificator.class);

  private static final String CALC_VIEW_REFERENCE_MATCH_PATTERN = "\"_SYS_BIC\".\"(.*)\\/(.*)\"";
  private static final String XSLT_RESOURCE_PATH = "META-INF/modificators/xslt/analyticprivilege.xslt";
  private static final String HDB_PROCEDURE_FILE_EXTENSION = "hdbprocedure";
  private static final List<String> REPLACE_SESSION_USER_FILE_EXTENSIONS = List.of("xsjs", "xsjslib", HDB_PROCEDURE_FILE_EXTENSION,
      "hdbtablefunction", "analyticprivilege", "hdbanalyticprivilege");
  private static final List<String> ANALYTIC_PRIVILEGE_FILE_EXTENSIONS = List.of("analyticprivilege", "hdbanalyticprivilege");
  private static final String ROW_DEFINITION = "(?i)\\bin row\\b";
  private static final String ROW_DEFINITION_REPLACEMENT = "IN row1";
  private static final String ROW_VALUE = "(?i)\\:row[^0-9a-z]";
  private static final String ROW_VALUE_REPLACEMENT = ":row1;";
  private static final String MERGE_INTO = "MERGE INTO ";
  private static final String AS = "AS ";
  private static final String USING = "USING ";
  private static final String UPDATE = "UPDATE ";
  private static final String ON = "ON";
  private static final String AND = "AND";
  private static final String ON_TRUE = "ON (1 = 1)";
  private static final String WHEN_MATCHED_THEN_UPDATE = "WHEN MATCHED THEN UPDATE";
  private static final String WHEN_MATCHED = "WHEN MATCHED ";
  private static final String THEN_UPDATE = "THEN UPDATE";
  private static final String TAB = "\t";
  private static final String NEW_LINE = "\n";
  private static final String SPACE = " ";
  private static final String CASE_INSENSITIVE_WHERE = "(?i)WHERE";
  /**
   * Modify a list of delivery unit project files during the migration process.
   *
   * @param projectFiles the list of project files which will be modified
   */
  public void modifyProjectFiles(List<IFile> projectFiles) throws IOException {
    for (IFile projectFile : projectFiles) {
      String fileExtension = getProjectFileExtension(projectFile);
      replaceSessionUser(fileExtension, projectFile);
      modifyAnalyticPrivilegeFile(fileExtension, projectFile);
      replaceReservedWordRow(fileExtension, projectFile);
      modifyUpdateFromStatement(fileExtension, projectFile);
    }
  }

  /**
   * Replace SESSION_USER in the content of the specified file.
   *
   * @param fileExtension the extension of the file being modified
   * @param projectFile   the file being modified
   */
  private void replaceSessionUser(String fileExtension, IFile projectFile) {
    if (REPLACE_SESSION_USER_FILE_EXTENSIONS.contains(fileExtension)) {
      byte[] currentContent = projectFile.getContent();
      projectFile.setContent(new String(currentContent).replace("SESSION_USER", "SESSION_CONTEXT('APPLICATIONUSER')").getBytes());
    }
  }

  /**
   * Make modifications on the modelUri and whereSql elements of an analytic privilege file.
   *
   * @param fileExtension         the extension of the file being modified
   * @param analyticPrivilegeFile the file being modified
   */
  private void modifyAnalyticPrivilegeFile(String fileExtension, IFile analyticPrivilegeFile) throws IOException {
    if (ANALYTIC_PRIVILEGE_FILE_EXTENSIONS.contains(fileExtension)) {
      byte[] currentContent = analyticPrivilegeFile.getContent();

      if (currentContent.length != 0) {
        try {
          String xslt = IOUtils.toString(
              XSKProjectFilesModificator.class.getClassLoader().getResourceAsStream(XSLT_RESOURCE_PATH),
              StandardCharsets.UTF_8);

          StringWriter writer = new StringWriter();
          StreamResult result = new StreamResult(writer);

          TransformerFactory factory = TransformerFactory.newInstance();
          Source xsltSource = new StreamSource(new StringReader(xslt));
          Transformer transformer = factory.newTransformer(xsltSource);

          Source contentSource = new StreamSource(new StringReader(new String(currentContent)));
          transformer.transform(contentSource, result);

          String processedAnalyticPrivilege = writer.toString();

          analyticPrivilegeFile.setContent(processedAnalyticPrivilege.getBytes());
        } catch (TransformerException exception) {
          logger.error("Analytic privilege" + analyticPrivilegeFile.getName() + "modification has failed due to an error.", exception);
          XSKCommonsUtils.logCustomErrors(analyticPrivilegeFile.getPath(), XSKCommonsConstants.MIGRATION_ERROR, "", "",
              exception.getMessage(),
              "", XSKCommonsConstants.HDB_ANALYTIC_PRIVILEGE, XSKCommonsConstants.MODULE_PARSERS,
              XSKCommonsConstants.SOURCE_DELIVERY_UNIT_MIGRATION, XSKCommonsConstants.PROGRAM_XSK);
        }
      }
    }
  }

  /**
   * Get the extension of the specified file.
   *
   * @param projectFile the file to get extension for
   * @return the file extension string
   */
  private String getProjectFileExtension(IFile projectFile) {
    return StringUtils.substringAfterLast(projectFile.getName(), ".");
  }

  /**
   * Modify analytic privilege file modelUri element by removing the reference of calculation view for the / syntax.
   *
   * @param value the value of the modelUri element
   * @return the modified value of the modelUri
   */
  public static String processModelUri(String value) {
    boolean uriIsNotWithPackageAndFolder = value.indexOf("/") == -1;

    if (uriIsNotWithPackageAndFolder) {
      return value;
    }

    return value.substring(value.lastIndexOf("/") + 1);
  }

  /**
   * Modify analytic privilege file whereSql element by removing calculation view references.
   *
   * @param value the value of the whereSql element
   * @return the modified value of the whereSql on regex pattern match for the second group
   */
  public static String processWhereSql(String value) {
    return value.replaceAll(CALC_VIEW_REFERENCE_MATCH_PATTERN, "\"$2\"");
  }

  /**
   * Replace reserved word row in the content of the specified file.
   *
   * @param fileExtension the extension of the file being modified
   * @param projectFile   the file being modified
   */
  private void replaceReservedWordRow(String fileExtension, IFile projectFile) {
    if (fileExtension.equalsIgnoreCase(HDB_PROCEDURE_FILE_EXTENSION)) {
      byte[] currentContent = projectFile.getContent();
      projectFile.setContent(new String(currentContent).replaceAll(ROW_DEFINITION, ROW_DEFINITION_REPLACEMENT)
          .replaceAll(ROW_VALUE, ROW_VALUE_REPLACEMENT).getBytes());
    }
  }

  /**
   * Modifies the update from statement in hdbprocedure files.
   *
   * @param fileExtension the extension of the file being modified
   * @param projectFile   the file being modified
   */
  private void modifyUpdateFromStatement(String fileExtension, IFile projectFile) {
    if (fileExtension.equalsIgnoreCase(HDB_PROCEDURE_FILE_EXTENSION) && !projectFile.isEmpty()) {
      String hdbprocedureFileContent = new String(projectFile.getContent());
      Map<String, String> modifiedUpdateStatementsMapping = new HashMap<>();
      ProcedureDefinitionModel procedureDefinitionModel = getProcedureModel(hdbprocedureFileContent);
      List<UpdateStatementDefinitionModel> updateStatements = procedureDefinitionModel.getUpdateStatements();

      for (UpdateStatementDefinitionModel updateStatement : updateStatements) {
        String modifiedUpdateStatement;
        FromClauseDefinitionModel fromClause = updateStatement.getFromClause();
        if (fromClause != null) {
          List<JoinClauseDefinitionModel> joinClauses = fromClause.getJoinClauses();
          UpdateSetClauseDefinitionModel updateSetClause = updateStatement.getUpdateSetClause();
          WhereClauseDefinitionModel whereClause = updateStatement.getWhereClause();

          if (joinClauses.isEmpty()) {
            modifiedUpdateStatement = modifyHdbprocedureUpdateFromWithoutJoinClauses(updateStatement, fromClause, updateSetClause, whereClause);
          } else {
            modifiedUpdateStatement = modifyHdbprocedureUpdateFromWithJoinClauses(updateStatement, fromClause, joinClauses, updateSetClause,
                whereClause);
          }
          modifiedUpdateStatementsMapping.put(updateStatement.getRawContent(), modifiedUpdateStatement);
        }
      }
      for (Map.Entry<String, String> mapping : modifiedUpdateStatementsMapping.entrySet()) {
        String originalUpdateFromStatement = mapping.getKey();
        String modifiedUpdateFromStatement = mapping.getValue();
        hdbprocedureFileContent = hdbprocedureFileContent.replace(originalUpdateFromStatement + ";", modifiedUpdateFromStatement + ";");
      }
      projectFile.setContent(hdbprocedureFileContent.getBytes());
    }
  }

  private ProcedureDefinitionModel getProcedureModel(String fileContent){
    CharStream inputStream = CharStreams.fromString(fileContent);
    HanaLexer lexer = new HanaLexer(inputStream);
    lexer.removeErrorListeners();
    CommonTokenStream tokenStream = new CommonTokenStream(lexer);

    HanaParser parser = new HanaParser(tokenStream);
    parser.setBuildParseTree(true);
    parser.removeErrorListeners();

    ParseTree parseTree = parser.sql_script();

    HanaProcedureUpdateStatementListener listener = new HanaProcedureUpdateStatementListener();
    ParseTreeWalker parseTreeWalker = new ParseTreeWalker();
    parseTreeWalker.walk(listener, parseTree);
    return listener.getProcedureModel();
  }

  private String modifyHdbprocedureUpdateFromWithoutJoinClauses(UpdateStatementDefinitionModel updateStatement, FromClauseDefinitionModel fromClause,
      UpdateSetClauseDefinitionModel updateSetClause, WhereClauseDefinitionModel whereClause) {
    StringBuilder modifiedUpdateStatement = new StringBuilder();
    List<TableReferenceModel> fromClauseTableReferences = fromClause.getTableReferences();

    if (fromClauseTableReferences.size() > 1) {
      String updatedTableName = updateStatement.getName();
      TableReferenceModel updatedTableReference = new TableReferenceModel();
      TableReferenceModel usingTableReference = new TableReferenceModel();

      for (TableReferenceModel tableReferenceModel : fromClauseTableReferences) {
        if (updatedTableName.equals(tableReferenceModel.getName()) || updatedTableName.equals(tableReferenceModel.getAlias())) {
          updatedTableReference = tableReferenceModel;
        }
        else {
          usingTableReference = tableReferenceModel;
        }
      }
      modifiedUpdateStatement.append(MERGE_INTO).append(updatedTableReference.getName());

      if (updatedTableReference.getAlias() != null) {
        modifiedUpdateStatement.append(AS).append(updatedTableReference.getAlias()).append(NEW_LINE);
      }
      modifiedUpdateStatement.append(TAB).append(USING);
      modifiedUpdateStatement.append(usingTableReference.getName()).append(SPACE);

      if (usingTableReference.getAlias() != null) {
        modifiedUpdateStatement.append(AS).append(usingTableReference.getAlias()).append(NEW_LINE);
      }

      if (whereClause != null) {
        modifiedUpdateStatement.append(ON).append(whereClause.getRawContent().replaceFirst(CASE_INSENSITIVE_WHERE, "")).append(SPACE);
      }
      else {
        modifiedUpdateStatement.append(ON_TRUE);
      }
      modifiedUpdateStatement.append(TAB).append(WHEN_MATCHED_THEN_UPDATE);
      modifiedUpdateStatement.append(TAB).append(updateSetClause.getRawContent());
    }
    else {
      TableReferenceModel fromClauseTableReference = fromClauseTableReferences.get(0);
      String fromClauseTableName = fromClauseTableReference.getName();
      String fromClauseTableAlias = fromClauseTableReference.getAlias();

      modifiedUpdateStatement.append(UPDATE);
      modifiedUpdateStatement.append(fromClauseTableName).append(SPACE);

      if (fromClauseTableAlias != null) {
        modifiedUpdateStatement.append(AS).append(fromClauseTableAlias).append(NEW_LINE);
      }
      modifiedUpdateStatement.append(TAB).append(updateSetClause.getRawContent()).append(NEW_LINE);

      if (whereClause != null) {
        modifiedUpdateStatement.append(TAB).append(whereClause.getRawContent());
      }
    }
    return modifiedUpdateStatement.toString();
  }

  private String modifyHdbprocedureUpdateFromWithJoinClauses(UpdateStatementDefinitionModel updateStatement, FromClauseDefinitionModel fromClause,
      List<JoinClauseDefinitionModel> joinClauses, UpdateSetClauseDefinitionModel updateSetClause, WhereClauseDefinitionModel whereClause) {
    StringBuilder modifiedUpdateStatement = new StringBuilder();
    JoinClauseDefinitionModel joinClauseForUpdate = new JoinClauseDefinitionModel();
    StringBuilder joinClausesRawContent = new StringBuilder();

    List<TableReferenceModel> fromClauseTableReferences = fromClause.getTableReferences();
    TableReferenceModel fromClauseTableReference = fromClauseTableReferences.get(0);
    String fromClauseTableName = fromClauseTableReference.getName();
    String fromClauseTableAlias = fromClauseTableReference.getAlias();
    String updatedTableName = updateStatement.getName();

    modifiedUpdateStatement.append(MERGE_INTO);
    if (updatedTableName.equals(fromClauseTableName) || updatedTableName.equals(fromClauseTableAlias)) {
      modifiedUpdateStatement.append(fromClauseTableName).append(SPACE);

      if (fromClauseTableAlias != null) {
        modifiedUpdateStatement.append(AS).append(fromClauseTableAlias).append(NEW_LINE);
      }
      joinClauseForUpdate = joinClauses.get(0);
      joinClauses.remove(joinClauseForUpdate);

      for (JoinClauseDefinitionModel joinClause : joinClauses) {
          joinClausesRawContent.append(TAB).append(joinClause.getRawContent()).append(NEW_LINE);
      }
      modifiedUpdateStatement.append(TAB).append(USING);
      modifiedUpdateStatement.append(joinClauseForUpdate.getName()).append(SPACE);

      if (joinClauseForUpdate.getAlias() != null) {
        modifiedUpdateStatement.append(AS).append(joinClauseForUpdate.getAlias()).append(NEW_LINE);
      }
      modifiedUpdateStatement.append(TAB).append(joinClausesRawContent);

    } else {
      for (JoinClauseDefinitionModel joinClause : joinClauses) {
        if (updatedTableName.equals(joinClause.getName()) || updatedTableName.equals(joinClause.getAlias())) {
          joinClauseForUpdate = joinClause;
        } else {
          joinClausesRawContent.append(TAB).append(joinClause.getRawContent());
        }
      }
      modifiedUpdateStatement.append(joinClauseForUpdate.getName()).append(SPACE);

      if (joinClauseForUpdate.getAlias() != null) {
        modifiedUpdateStatement.append(AS).append(joinClauseForUpdate.getAlias()).append(NEW_LINE);
      }
      modifiedUpdateStatement.append(TAB).append(USING);
      modifiedUpdateStatement.append(fromClauseTableName).append(SPACE);

      if (fromClauseTableAlias != null) {
        modifiedUpdateStatement.append(AS).append(fromClauseTableAlias).append(NEW_LINE);
      }
      modifiedUpdateStatement.append(TAB).append(joinClausesRawContent).append(NEW_LINE);
    }
    modifiedUpdateStatement.append(TAB).append(joinClauseForUpdate.getOnPart()).append(NEW_LINE);
    modifiedUpdateStatement.append(TAB).append(WHEN_MATCHED);

    if (whereClause != null) {
      modifiedUpdateStatement.append(AND).append(whereClause.getRawContent().replaceFirst(CASE_INSENSITIVE_WHERE, "")).append(SPACE);
    }
    modifiedUpdateStatement.append(THEN_UPDATE).append(NEW_LINE);
    modifiedUpdateStatement.append(TAB).append(updateSetClause.getRawContent());
    return modifiedUpdateStatement.toString();
  }
}
