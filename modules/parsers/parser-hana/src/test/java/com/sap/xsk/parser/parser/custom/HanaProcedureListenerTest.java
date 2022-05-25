package com.sap.xsk.parser.parser.custom;

import com.sap.xsk.parser.hana.core.HanaLexer;
import com.sap.xsk.parser.hana.core.HanaParser;
import custom.HanaProcedureListener;
import models.ProcedureDefinitionModel;
import models.UpdateStatementDefinitionModel;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Test;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class HanaProcedureListenerTest {

  @Test
  public void parseHdbProcedure() throws Exception {
    String tableFunctionSample = getSample("/test.hdbprocedure");
    ProcedureDefinitionModel model = parseHDBPProcedureModel(tableFunctionSample);

    System.out.println();
  }

  private String getMergeIntoStatement(UpdateStatementDefinitionModel model){
    String tableName = model.getFromClauseDefinitionModel().getTableName();
    String mergeStmt = "MERGE INTO" + tableName;
    return mergeStmt;
  }

  private String getSample(String sampleName) throws IOException {
    return org.apache.commons.io.IOUtils
        .toString(HanaTableFunctionListenerTest.class.getResourceAsStream(sampleName), StandardCharsets.UTF_8);
  }

  private ProcedureDefinitionModel parseHDBPProcedureModel(String sample) {
    CharStream inputStream = CharStreams.fromString(sample);
    HanaLexer lexer = new HanaLexer(inputStream);
    CommonTokenStream tokenStream = new CommonTokenStream(lexer);

    HanaParser parser = new HanaParser(tokenStream);
    parser.setBuildParseTree(true);
    ParseTree parseTree = parser.sql_script();

    HanaProcedureListener listener = new HanaProcedureListener();
    ParseTreeWalker parseTreeWalker = new ParseTreeWalker();
    parseTreeWalker.walk(listener, parseTree);

    return listener.getProcedureModel();
  }
}


