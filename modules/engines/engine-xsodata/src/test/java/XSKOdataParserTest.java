/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2022 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.doNothing;

import com.sap.xsk.exceptions.XSKArtifactParserException;
import com.sap.xsk.xsodata.ds.model.XSKDBArtifactModel;
import com.sap.xsk.xsodata.ds.model.XSKODataModel;
import com.sap.xsk.xsodata.ds.service.XSKOData2TransformerException;
import com.sap.xsk.xsodata.ds.service.XSKODataParser;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import javax.sql.DataSource;
import org.apache.commons.io.IOUtils;
import org.eclipse.dirigible.api.v3.problems.ProblemsFacade;
import org.eclipse.dirigible.core.test.AbstractDirigibleTest;
import org.eclipse.dirigible.database.persistence.model.PersistenceTableModel;
import org.eclipse.dirigible.database.sql.ISqlKeywords;
import org.eclipse.dirigible.engine.odata2.transformers.DBMetadataUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore("javax.management.*")
@PrepareForTest({ProblemsFacade.class})
public class XSKOdataParserTest extends AbstractDirigibleTest {

  @Mock(answer = Answers.RETURNS_DEEP_STUBS)
  private Connection mockConnection;
  @Mock(answer = Answers.RETURNS_DEEP_STUBS)
  private DBMetadataUtil dbMetadataUtil;
  @Mock
  private PersistenceTableModel mockSynonymTargetObjectMetadata;
  @Mock
  private ResultSet mockResultSet;
  @Mock
  private ResultSet mockResultSetWhenSynonym;
  @Mock
  private ResultSet mockResultSetEntityExist;
  @Mock
  private DataSource mockDataSource;
  @Mock
  private XSKDBArtifactModel artifactReturnType;
  @Mock
  private PreparedStatement mockPreparedStatement;
  @Spy
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
        .toString(this.getClass().getResourceAsStream("/entity_with_all_set_of_navigations.xsodata"), StandardCharsets.UTF_8);
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
    String content = IOUtils.toString(this.getClass().getResourceAsStream("/entity_wrong_syntax.xsodata"), StandardCharsets.UTF_8);
    parser.parseXSODataArtifact("np/entity_wrong_syntax.xsodata", content);
  }

  @Test
  public void testApplyEmptyNamespaceCondition() throws Exception {
    mockGetTablesSuccessfully();
    String content = IOUtils
        .toString(this.getClass().getResourceAsStream("/entity_with_no_namespace.xsodata"), StandardCharsets.UTF_8);
    XSKODataModel xskoDataModel = parser.parseXSODataArtifact("/a_1/b-2/c/entity_with_no_namespace.xsodata", content);
    assertEquals("a_1.b-2.c.entity_with_no_namespace", xskoDataModel.getService().getNamespace());
  }

  @Test
  public void testApplyKeysConditionSuccessfully() throws Exception {
    mockGetTablesSuccessfully();
    String content = IOUtils.toString(this.getClass().getResourceAsStream("/entity_with_keys.xsodata"), StandardCharsets.UTF_8);
    parser.parseXSODataArtifact("/a_1/b-2/c/entity_with_no_namespace.xsodata", content);
  }

  @Test
  public void testApplyKeysConditionSuccessfullyWhenSynonym() throws Exception {
    mockGetTablesSuccessfullyWhenSynonym();
    String content = IOUtils.toString(this.getClass().getResourceAsStream("/entity_with_keys.xsodata"), StandardCharsets.UTF_8);
    parser.parseXSODataArtifact("/a_1/b-2/c/entity_with_no_namespace.xsodata", content);
  }

  @Test(expected = XSKOData2TransformerException.class)
  public void testApplyKeysConditionFail() throws Exception {
    mockGetTablesFail();
    String content = IOUtils.toString(this.getClass().getResourceAsStream("/entity_with_keys.xsodata"), StandardCharsets.UTF_8);
    parser.parseXSODataArtifact("/a_1/b-2/c/entity_with_no_namespace.xsodata", content);
  }

  @Test(expected = XSKOData2TransformerException.class)
  public void testApplyKeysConditionFailWhenSynonym() throws Exception {
    mockGetTablesFailWhenSynonym();
    mockGetTable();
    String content = IOUtils.toString(this.getClass().getResourceAsStream("/entity_with_keys.xsodata"), StandardCharsets.UTF_8);
    parser.parseXSODataArtifact("/a_1/b-2/c/entity_with_no_namespace.xsodata", content);
  }

  @Test
  public void testApplyEntitySetCondition() throws Exception {
    mockGetTablesSuccessfully();
    String content = IOUtils
        .toString(this.getClass().getResourceAsStream("/entity_with_no_namespace.xsodata"), StandardCharsets.UTF_8);
    XSKODataModel xskoDataModel = parser.parseXSODataArtifact("/entity_with_no_namespace.xsodata", content);
    assertEquals(xskoDataModel.getService().getEntities().get(0).getAlias(), "MyView");
    assertEquals(xskoDataModel.getService().getEntities().get(1).getAlias(), "view");
    assertEquals(xskoDataModel.getService().getEntities().get(2).getAlias(), "view");
    assertEquals(xskoDataModel.getService().getEntities().get(3).getAlias(), "view");
  }

  @Test
  public void testApplyNavEntryFromEndConditionSuccessfully()
      throws Exception {
    mockGetTablesSuccessfully();
    String content = IOUtils
        .toString(this.getClass().getResourceAsStream("/entity_with_navigation.xsodata"), StandardCharsets.UTF_8);
    XSKODataModel xskoDataModel = parser.parseXSODataArtifact("/entity_with_navigation.xsodata", content);
    assertEquals(2, xskoDataModel.getService().getEntities().size());
    assertEquals(1, xskoDataModel.getService().getAssociations().size());
  }

  @Test(expected = XSKOData2TransformerException.class)
  public void testApplyNavEntryFromEndConditionFail() throws Exception {
    mockGetTablesSuccessfully();
    String content = IOUtils
        .toString(this.getClass().getResourceAsStream("/entity_with_navigation_error.xsodata"), StandardCharsets.UTF_8);
    parser.parseXSODataArtifact("/entity_with_navigation_error.xsodata", content);
  }

  @Test(expected = XSKOData2TransformerException.class)
  public void testApplyNumberOfJoinPropertiesConditionFail()
      throws Exception {
    mockGetTablesSuccessfully();
    String content = IOUtils
        .toString(this.getClass().getResourceAsStream("/entity_with_wrong_join_prop.xsodata"), StandardCharsets.UTF_8);
    parser.parseXSODataArtifact("/entity_with_wrong_join_prop.xsodata", content);
  }

  @Test(expected = XSKOData2TransformerException.class)
  public void testApplyOrderOfJoinPropertiesCondition() throws Exception {
    mockGetTablesSuccessfully();
    String content = IOUtils
        .toString(this.getClass().getResourceAsStream("/entity_with_wrong_over_join_prop.xsodata"), StandardCharsets.UTF_8);
    parser.parseXSODataArtifact("/entity_with_wrong_over_join_prop.xsodata", content);
  }

  @Test(expected = XSKOData2TransformerException.class)
  public void testApplyImplicitAggregatesWithKeyGeneratedCondition()
      throws Exception {
    mockGetTablesSuccessfully();
    String content = IOUtils
        .toString(this.getClass().getResourceAsStream("/entity_with_wrong_implicit_aggregation.xsodata"), StandardCharsets.UTF_8);
    parser.parseXSODataArtifact("/entity_with_wrong_implicit_aggregation.xsodata", content);
  }

  @Test(expected = XSKOData2TransformerException.class)
  public void testApplyExplicitAggregatesWithKeyGeneratedCondition()
      throws Exception {
    mockGetTablesSuccessfully();
    String content = IOUtils
        .toString(this.getClass().getResourceAsStream("/entity_with_wrong_explicit_aggregation.xsodata"), StandardCharsets.UTF_8);
    parser.parseXSODataArtifact("/entity_with_wrong_explicit_aggregation.xsodata", content);
  }

  @Test(expected = XSKOData2TransformerException.class)
  public void testApplyParametersToViewsCondition() throws Exception {
    mockGetTablesFail();
    String content = IOUtils.toString(this.getClass().getResourceAsStream("/entity_with_params.xsodata"), StandardCharsets.UTF_8);
    parser.parseXSODataArtifact("/entity_with_params.xsodata", content);
  }

  @Test(expected = XSKOData2TransformerException.class)
  public void testApplyParametersToViewsConditionWhenSynonym() throws Exception {
    mockGetTablesFailWhenSynonym();
    String content = IOUtils.toString(this.getClass().getResourceAsStream("/entity_with_params.xsodata"), StandardCharsets.UTF_8);
    parser.parseXSODataArtifact("/entity_with_params.xsodata", content);
  }

  @Test
  public void testApplyOmittedParamResultCondition() throws Exception {
    mockGetTablesSuccessfully();
    String content = IOUtils.toString(this.getClass().getResourceAsStream("/entity_with_params.xsodata"), StandardCharsets.UTF_8);
    XSKODataModel xskoDataModel = parser.parseXSODataArtifact("/entity_with_params.xsodata", content);
    assertEquals("CalcViewParameters", xskoDataModel.getService().getEntities().get(0).getParameterEntitySet().getParameterEntitySetName());
    assertEquals("Results", xskoDataModel.getService().getEntities().get(0).getParameterEntitySet().getParameterResultsProperty());
  }

  private void mockGetTablesSuccessfully() throws Exception {
    mockGetTable();
    mockGetDBArtifactsByName();
    mockProblemsFacade();
    when(mockResultSetEntityExist.next()).thenReturn(true).thenReturn(false);
    when(mockResultSetWhenSynonym.next()).thenReturn(false);
    when(mockResultSet.next()).thenReturn(true);
  }

  private void mockGetTablesSuccessfullyWhenSynonym() throws Exception {
    mockGetTable();
    mockGetDBArtifactsByName();
    mockProblemsFacade();
    when(mockResultSetEntityExist.next()).thenReturn(true).thenReturn(false);
    when(mockResultSetWhenSynonym.next()).thenReturn(true).thenReturn(false);
    when(mockSynonymTargetObjectMetadata.getTableName()).thenReturn("MyTestSynonym");
  }

  private void mockGetTablesFail() throws Exception {
    mockGetTable();
    mockProblemsFacade();
    mockDBArtifactsByNameMissing();
    when(mockResultSetEntityExist.next()).thenReturn(true).thenReturn(false);
    when(mockResultSetWhenSynonym.next()).thenReturn(false);
    when(artifactReturnType.getType()).thenReturn("PLACEHOLDER");
  }

  private void mockGetTablesFailWhenSynonym() throws Exception {
    mockGetTable();
    mockProblemsFacade();
    mockDBArtifactsByNameMissing();
    when(mockResultSetEntityExist.next()).thenReturn(true).thenReturn(false);
    when(mockResultSetWhenSynonym.next()).thenReturn(true).thenReturn(false);
    when(mockSynonymTargetObjectMetadata.getTableName()).thenReturn("MyTestSynonym");
    when(artifactReturnType.getType()).thenReturn("PLACEHOLDER");
  }

  private void mockGetTable() throws Exception {
    when(mockDataSource.getConnection()).thenReturn(mockConnection);
    when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
    when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
  }

  private void mockDBArtifactsByNameMissing() throws SQLException {
    XSKDBArtifactModel xskDBArtifactModelPlaceholder = new XSKDBArtifactModel("PLACEHOLDER", "PLACEHOLDER", "PLACEHOLDER");
    doReturn(List.of(xskDBArtifactModelPlaceholder)).when(parser).getDBArtifactsByName(anyString());
  }

  private void mockGetDBArtifactsByName() throws SQLException {
    XSKDBArtifactModel xskDBArtifactModelView = new XSKDBArtifactModel(ISqlKeywords.METADATA_VIEW, ISqlKeywords.METADATA_VIEW,
        ISqlKeywords.METADATA_VIEW);
    XSKDBArtifactModel xskDBArtifactModelCalcView = new XSKDBArtifactModel(ISqlKeywords.METADATA_CALC_VIEW, ISqlKeywords.METADATA_CALC_VIEW,
        ISqlKeywords.METADATA_CALC_VIEW);
    XSKDBArtifactModel xskDBArtifactModelSynonym = new XSKDBArtifactModel(ISqlKeywords.METADATA_SYNONYM, ISqlKeywords.METADATA_SYNONYM,
        ISqlKeywords.METADATA_SYNONYM);
    doReturn(List.of(xskDBArtifactModelView, xskDBArtifactModelCalcView, xskDBArtifactModelSynonym)).when(parser).getDBArtifactsByName(anyString());
  }

  private void mockProblemsFacade() throws Exception {
    PowerMockito.mockStatic(ProblemsFacade.class);
    doNothing().when(ProblemsFacade.class, "save", any(), any(), any(), any(), any(), any(), any(), any(), any(), any());
  }
}
