/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2021 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.when;

import com.sap.xsk.exceptions.XSKArtifactParserException;
import com.sap.xsk.xsodata.ds.model.XSKODataModel;
import com.sap.xsk.xsodata.ds.service.XSKOData2TransformerException;
import com.sap.xsk.xsodata.ds.service.XSKODataParser;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import javax.sql.DataSource;
import org.apache.commons.io.IOUtils;
import org.eclipse.dirigible.core.test.AbstractDirigibleTest;
import org.eclipse.dirigible.database.sql.ISqlKeywords;
import org.eclipse.dirigible.engine.odata2.transformers.DBMetadataUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore("javax.management.*")
public class XSKOdataParserTest extends AbstractDirigibleTest {

  @Mock(answer = Answers.RETURNS_DEEP_STUBS)
  private Connection mockConnection;
  @Mock(answer = Answers.RETURNS_DEEP_STUBS)
  private DBMetadataUtil dbMetadataUtil;
  @Mock
  private DatabaseMetaData mockDatabaseMetaData;
  @Mock
  private HashMap<String, String> mockSynonymTargetObjectMetadata;
  @Mock
  private ResultSet mockResultSet;
  @Mock
  private ResultSet mockResultSetWhenSynonym;
  @Mock
  private ResultSet mockResultSetEntityExist;
  @Mock
  private DataSource mockDataSource;
  @InjectMocks
  private final XSKODataParser parser = new XSKODataParser();

  @Before
  public void openMocks() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void parseXsodataFileSuccessfully() throws Exception {
    mockGetTablesSuccessfully();
    String content = IOUtils
        .toString(XSKODataUtilsTest.class.getResourceAsStream("/entity_with_all_set_of_navigations.xsodata"), StandardCharsets.UTF_8);
    XSKODataModel xskoDataModel = parser.parseXSODataArtifact("np/entity_with_all_set_of_navigations.xsodata", content);
    assertEquals("entity_with_all_set_of_navigations.xsodata", xskoDataModel.getName());
    assertEquals("np/entity_with_all_set_of_navigations.xsodata", xskoDataModel.getLocation());
    assertNotNull(xskoDataModel.getHash());
    assertEquals("guest", xskoDataModel.getCreatedBy());
    assertNotNull(xskoDataModel.getCreatedAt());
    assertNotNull(xskoDataModel.getService());

    //no need to check the service content the parser module unit tests cover it
  }

  @Test(expected = XSKArtifactParserException.class)
  public void testValidateEdmMultiplicity() throws Exception {
    mockGetTablesSuccessfully();
    String content = IOUtils.toString(XSKODataUtilsTest.class.getResourceAsStream("/entity_wrong_syntax.xsodata"), StandardCharsets.UTF_8);
    parser.parseXSODataArtifact("np/entity_wrong_syntax.xsodata", content);
  }

  @Test
  public void testApplyEmptyNamespaceCondition() throws IOException, SQLException, XSKArtifactParserException {
    mockGetTablesSuccessfully();
    String content = IOUtils
        .toString(XSKODataUtilsTest.class.getResourceAsStream("/entity_with_no_namespace.xsodata"), StandardCharsets.UTF_8);
    XSKODataModel xskoDataModel = parser.parseXSODataArtifact("/a_1/b-2/c/entity_with_no_namespace.xsodata", content);
    assertEquals("a_1.b-2.c.entity_with_no_namespace", xskoDataModel.getService().getNamespace());
  }

  @Test
  public void testApplyKeysConditionSuccessfully() throws IOException, SQLException, XSKArtifactParserException {
    mockGetTablesSuccessfully();
    String content = IOUtils.toString(XSKODataUtilsTest.class.getResourceAsStream("/entity_with_keys.xsodata"), StandardCharsets.UTF_8);
    parser.parseXSODataArtifact("/a_1/b-2/c/entity_with_no_namespace.xsodata", content);
  }

  @Test
  public void testApplyKeysConditionSuccessfullyWhenSynonym() throws IOException, SQLException, XSKArtifactParserException {
    mockGetTablesSuccessfullyWhenSynonym();
    String content = IOUtils.toString(XSKODataUtilsTest.class.getResourceAsStream("/entity_with_keys.xsodata"), StandardCharsets.UTF_8);
    parser.parseXSODataArtifact("/a_1/b-2/c/entity_with_no_namespace.xsodata", content);
  }

  @Test(expected = XSKOData2TransformerException.class)
  public void testApplyKeysConditionFail() throws IOException, SQLException, XSKArtifactParserException {
    mockGetTablesFail();
    String content = IOUtils.toString(XSKODataUtilsTest.class.getResourceAsStream("/entity_with_keys.xsodata"), StandardCharsets.UTF_8);
    parser.parseXSODataArtifact("/a_1/b-2/c/entity_with_no_namespace.xsodata", content);
  }

  @Test(expected = XSKOData2TransformerException.class)
  public void testApplyKeysConditionFailWhenSynonym() throws IOException, SQLException, XSKArtifactParserException {
    mockGetTablesFailWhenSynonym();
    String content = IOUtils.toString(XSKODataUtilsTest.class.getResourceAsStream("/entity_with_keys.xsodata"), StandardCharsets.UTF_8);
    parser.parseXSODataArtifact("/a_1/b-2/c/entity_with_no_namespace.xsodata", content);
  }

  @Test
  public void testApplyEntitySetCondition() throws IOException, SQLException, XSKArtifactParserException {
    mockGetTablesSuccessfully();
    String content = IOUtils
        .toString(XSKODataUtilsTest.class.getResourceAsStream("/entity_with_no_namespace.xsodata"), StandardCharsets.UTF_8);
    XSKODataModel xskoDataModel = parser.parseXSODataArtifact("/entity_with_no_namespace.xsodata", content);
    assertEquals(xskoDataModel.getService().getEntities().get(0).getAlias(), "MyView");
    assertEquals(xskoDataModel.getService().getEntities().get(1).getAlias(), "view");
    assertEquals(xskoDataModel.getService().getEntities().get(2).getAlias(), "view");
    assertEquals(xskoDataModel.getService().getEntities().get(3).getAlias(), "view");
  }

  @Test
  public void testApplyNavEntryFromEndConditionSuccessfully()
      throws IOException, SQLException, XSKArtifactParserException {
    mockGetTablesSuccessfully();
    String content = IOUtils
        .toString(XSKODataUtilsTest.class.getResourceAsStream("/entity_with_navigation.xsodata"), StandardCharsets.UTF_8);
    XSKODataModel xskoDataModel = parser.parseXSODataArtifact("/entity_with_navigation.xsodata", content);
    assertEquals(2, xskoDataModel.getService().getEntities().size());
    assertEquals(1, xskoDataModel.getService().getAssociations().size());
  }

  @Test(expected = XSKOData2TransformerException.class)
  public void testApplyNavEntryFromEndConditionFail() throws IOException, SQLException, XSKArtifactParserException {
    mockGetTablesSuccessfully();
    String content = IOUtils
        .toString(XSKODataUtilsTest.class.getResourceAsStream("/entity_with_navigation_error.xsodata"), StandardCharsets.UTF_8);
    parser.parseXSODataArtifact("/entity_with_navigation_error.xsodata", content);
  }

  @Test(expected = XSKOData2TransformerException.class)
  public void testApplyNumberOfJoinPropertiesConditionFail()
      throws IOException, SQLException, XSKArtifactParserException {
    mockGetTablesSuccessfully();
    String content = IOUtils
        .toString(XSKODataUtilsTest.class.getResourceAsStream("/entity_with_wrong_join_prop.xsodata"), StandardCharsets.UTF_8);
    parser.parseXSODataArtifact("/entity_with_wrong_join_prop.xsodata", content);
  }

  @Test(expected = XSKOData2TransformerException.class)
  public void testApplyOrderOfJoinPropertiesCondition() throws IOException, SQLException, XSKArtifactParserException {
    mockGetTablesSuccessfully();
    String content = IOUtils
        .toString(XSKODataUtilsTest.class.getResourceAsStream("/entity_with_wrong_over_join_prop.xsodata"), StandardCharsets.UTF_8);
    parser.parseXSODataArtifact("/entity_with_wrong_over_join_prop.xsodata", content);
  }

  @Test(expected = XSKOData2TransformerException.class)
  public void testApplyImplicitAggregatesWithKeyGeneratedCondition()
      throws IOException, SQLException, XSKArtifactParserException {
    mockGetTablesSuccessfully();
    String content = IOUtils
        .toString(XSKODataUtilsTest.class.getResourceAsStream("/entity_with_wrong_implicit_aggregation.xsodata"), StandardCharsets.UTF_8);
    parser.parseXSODataArtifact("/entity_with_wrong_implicit_aggregation.xsodata", content);
  }

  @Test(expected = XSKOData2TransformerException.class)
  public void testApplyExplicitAggregatesWithKeyGeneratedCondition()
      throws IOException, SQLException, XSKArtifactParserException {
    mockGetTablesSuccessfully();
    String content = IOUtils
        .toString(XSKODataUtilsTest.class.getResourceAsStream("/entity_with_wrong_explicit_aggregation.xsodata"), StandardCharsets.UTF_8);
    parser.parseXSODataArtifact("/entity_with_wrong_explicit_aggregation.xsodata", content);
  }

  @Test(expected = XSKOData2TransformerException.class)
  public void testApplyParametersToViewsCondition() throws IOException, SQLException, XSKArtifactParserException {
    mockGetTablesFail();
    String content = IOUtils.toString(XSKOdataParserTest.class.getResourceAsStream("/entity_with_params.xsodata"), StandardCharsets.UTF_8);
    parser.parseXSODataArtifact("/entity_with_params.xsodata", content);
  }

  @Test(expected = XSKOData2TransformerException.class)
  public void testApplyParametersToViewsConditionWhenSynonym() throws IOException, SQLException, XSKArtifactParserException {
    mockGetTablesFailWhenSynonym();
    String content = IOUtils.toString(XSKOdataParserTest.class.getResourceAsStream("/entity_with_params.xsodata"), StandardCharsets.UTF_8);
    parser.parseXSODataArtifact("/entity_with_params.xsodata", content);
  }

  @Test
  public void testApplyOmittedParamResultCondition() throws IOException, SQLException, XSKArtifactParserException {
    mockGetTablesSuccessfully();
    String content = IOUtils.toString(XSKODataUtilsTest.class.getResourceAsStream("/entity_with_params.xsodata"), StandardCharsets.UTF_8);
    XSKODataModel xskoDataModel = parser.parseXSODataArtifact("/entity_with_params.xsodata", content);
    assertEquals("CalcViewParameters", xskoDataModel.getService().getEntities().get(0).getParameterEntitySet().getParameterEntitySetName());
    assertEquals("Results", xskoDataModel.getService().getEntities().get(0).getParameterEntitySet().getParameterResultsProperty());
  }

  private void mockGetTablesSuccessfully() throws SQLException {
    mockGetTable();
    when(mockResultSetEntityExist.next()).thenReturn(true);
    when(mockResultSetWhenSynonym.next()).thenReturn(false);
    when(mockResultSet.next()).thenReturn(true);
  }

  private void mockGetTablesSuccessfullyWhenSynonym() throws SQLException {
    mockGetTable();
    when(mockResultSetEntityExist.next()).thenReturn(true);
    when(mockResultSetWhenSynonym.next()).thenReturn(true);
    when(dbMetadataUtil.getSynonymTargetObjectMetadata(any(String.class), any(String.class))).thenReturn(mockSynonymTargetObjectMetadata);
    when(mockSynonymTargetObjectMetadata.isEmpty()).thenReturn(false);
    when(mockSynonymTargetObjectMetadata.get(ISqlKeywords.KEYWORD_TABLE)).thenReturn("MyTestSynonym");
    when(mockResultSet.next()).thenReturn(true);
  }

  private void mockGetTablesFail() throws SQLException {
    mockGetTable();
    when(mockResultSetEntityExist.next()).thenReturn(true);
    when(mockResultSetWhenSynonym.next()).thenReturn(false);
    when(mockResultSet.next()).thenReturn(false);
  }

  private void mockGetTablesFailWhenSynonym() throws SQLException {
    mockGetTable();
    when(mockResultSetEntityExist.next()).thenReturn(true);
    when(mockResultSetWhenSynonym.next()).thenReturn(true);
    when(dbMetadataUtil.getSynonymTargetObjectMetadata(any(String.class), any(String.class))).thenReturn(mockSynonymTargetObjectMetadata);
    when(mockSynonymTargetObjectMetadata.isEmpty()).thenReturn(false);
    when(mockSynonymTargetObjectMetadata.get(ISqlKeywords.KEYWORD_TABLE)).thenReturn("MyTestSynonym");
    when(mockResultSet.next()).thenReturn(false);
  }

  private void mockGetTable() throws SQLException {
    when(mockDataSource.getConnection()).thenReturn(mockConnection);
    when(mockConnection.getMetaData()).thenReturn(mockDatabaseMetaData);
    when(mockDatabaseMetaData.getTables(isNull(), isNull(), anyString(), any(String[].class))).thenReturn(mockResultSet);
    when(mockDatabaseMetaData.getTables(isNull(), isNull(), anyString(), eq(List.of(ISqlKeywords.METADATA_SYNONYM).toArray(String[]::new)))).thenReturn(mockResultSetWhenSynonym);
    when(mockDatabaseMetaData.getTables(isNull(), isNull(), anyString(), isNull())).thenReturn(mockResultSetEntityExist);
  }
}
