package com.sap.xsk.xsodata.ds.handler;

import com.sap.xsk.xsodata.utils.XSKOData2EventHandlerUtils;
import org.apache.olingo.odata2.api.edm.EdmType;
import org.apache.olingo.odata2.api.uri.UriInfo;
import org.eclipse.dirigible.database.sql.ISqlKeywords;
import org.eclipse.dirigible.database.sql.SqlFactory;
import org.eclipse.dirigible.database.sql.dialects.hana.HanaSqlDialect;
import org.eclipse.dirigible.engine.odata2.sql.api.SQLStatementParam;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class XSKOData2EventHandlerUtilsTest {

  @Mock
  private Connection connection;

  @Mock
  private EdmType edmType;

  private static MockedStatic<SqlFactory> sqlFactory = Mockito.mockStatic(SqlFactory.class, Mockito.CALLS_REAL_METHODS);

  @Test
  public void testBuildCreateTemporaryTableLikeTableSql() {
    sqlFactory.when(() -> SqlFactory.deriveDialect(any())).thenReturn(new HanaSqlDialect());

    assertEquals("CREATE LOCAL TEMPORARY TABLE #TEST_TEMP_TABLE LIKE TEST_SCHEMA.test-table WITH NO DATA",
        XSKOData2EventHandlerUtils.buildCreateTemporaryTableLikeTableSql(connection, ISqlKeywords.METADATA_TABLE, "TEST_SCHEMA",
            "#TEST_TEMP_TABLE", "test-table"));
  }

  @Test
  public void testBuildCreateTemporaryTableAsSelect() {
    sqlFactory.when(() -> SqlFactory.deriveDialect(any())).thenReturn(new HanaSqlDialect());

    List<SQLStatementParam> parameters = List.of(new SQLStatementParam("123", edmType, null));
    assertEquals("CREATE LOCAL TEMPORARY TABLE #TEST_TEMP_TABLE AS (SELECT * FROM test-table WHERE ID = '123')",
        XSKOData2EventHandlerUtils.buildCreateTemporaryTableAsSelect(connection, "#TEST_TEMP_TABLE",
            "SELECT * FROM test-table WHERE ID = ?",
            parameters));
  }

}
