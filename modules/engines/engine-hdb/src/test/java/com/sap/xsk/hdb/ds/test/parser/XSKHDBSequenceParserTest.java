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
package com.sap.xsk.hdb.ds.test.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;

import com.sap.xsk.exceptions.XSKArtifactParserException;
import com.sap.xsk.hdb.ds.model.XSKDBContentType;
import com.sap.xsk.hdb.ds.model.XSKDataStructureModelFactory;
import com.sap.xsk.hdb.ds.model.XSKDataStructureParametersModel;
import com.sap.xsk.hdb.ds.model.hdbsequence.XSKDataStructureHDBSequenceModel;
import com.sap.xsk.hdb.ds.parser.hdbsequence.XSKHDBSequenceParser;
import com.sap.xsk.hdb.ds.test.module.HdbTestModule;
import com.sap.xsk.parser.hdbsequence.exceptions.XSKHDBSequenceDuplicatePropertyException;
import com.sap.xsk.parser.hdbsequence.exceptions.XSKHDBSequenceMissingPropertyException;
import java.nio.charset.StandardCharsets;
import org.junit.Before;
import org.junit.Test;

public class XSKHDBSequenceParserTest {

  @Before
  public void setUp() {
    HdbTestModule testModule = new HdbTestModule();
    testModule.configure();
  }

    @Test
    public void parseHanaXSClassicContent() throws Exception {
      String content = org.apache.commons.io.IOUtils
              .toString(XSKHDBSequenceParserTest.class.getResourceAsStream("/test/xsk/com/sap/SampleSequence_HanaXSClassic.hdbsequence"),
                      StandardCharsets.UTF_8);

      XSKDataStructureParametersModel parametersModel =
          new XSKDataStructureParametersModel(null, "/test/xsk/com/sap/SampleSequence_HanaXSClassic.hdbsequence", content, null);
      XSKDataStructureHDBSequenceModel model = (XSKDataStructureHDBSequenceModel) new XSKHDBSequenceParser()
              .parse(parametersModel);

      assertEquals("MYSCHEMA", model.getSchema());
      assertEquals(Integer.valueOf(10), model.getStartWith());
      assertEquals(Integer.valueOf(30), model.getMaxValue());
      assertEquals(false, model.getNoMaxValue());
      assertEquals(true, model.getNoMinValue());
      assertEquals(false, model.getCycles());
      assertEquals(XSKDBContentType.XS_CLASSIC, model.getDBContentType());
    }

    @Test
    public void parseDefaultValues() throws Exception {
      String content = org.apache.commons.io.IOUtils
          .toString(XSKHDBSequenceParserTest.class.getResourceAsStream("/test/xsk/com/sap/SchemaOnlySequence.hdbsequence"),
              StandardCharsets.UTF_8);
      XSKDataStructureParametersModel parametersModel =
          new XSKDataStructureParametersModel(null, "/test/xsk/com/sap/SchemaOnlySequence.hdbsequence", content, null);
      XSKDataStructureHDBSequenceModel model = (XSKDataStructureHDBSequenceModel) new XSKHDBSequenceParser()
          .parse(parametersModel);
      assertFalse(model.isPublic());
      assertEquals(Integer.valueOf(1), model.getStartWith());
      assertEquals(Integer.valueOf(1), model.getIncrementBy());
      assertEquals(Integer.valueOf(1), model.getMinValue());
    }

    @Test
    public void parseDependsOnContent() throws Exception {
      String content = org.apache.commons.io.IOUtils
          .toString(XSKHDBSequenceParserTest.class.getResourceAsStream("/test/xsk/com/sap/DependsOnSequence.hdbsequence"),
              StandardCharsets.UTF_8);
      XSKDataStructureParametersModel parametersModel =
          new XSKDataStructureParametersModel(null, "/test/xsk/com/sap/DependsOnSequence.hdbsequence", content, null);
      XSKDataStructureHDBSequenceModel model = (XSKDataStructureHDBSequenceModel) new XSKHDBSequenceParser()
          .parse(parametersModel);
      assertEquals("sap.ino.db.iam::t_identity", model.getDependsOnTable());
      assertEquals("sap.ino.db.iam::t_view", model.getDependsOnView());
    }

    @Test
    public void parseHanaXSAdvancedContent() throws Exception {
      String content = org.apache.commons.io.IOUtils
              .toString(XSKHDBSequenceParserTest.class.getResourceAsStream("/test/xsk/com/sap/CustomerId_HanaXSAdvanced.hdbsequence"),
                      StandardCharsets.UTF_8);
      XSKDataStructureParametersModel parametersModel =
          new XSKDataStructureParametersModel(null, "/test/xsk/com/sap/CustomerId_HanaXSAdvanced.hdbsequence", content, null);
      XSKDataStructureHDBSequenceModel model = (XSKDataStructureHDBSequenceModel) new XSKHDBSequenceParser()
              .parse(parametersModel);
      assertEquals(XSKDBContentType.OTHERS, model.getDBContentType());
      assertEquals(content, model.getRawContent());
    }


    @Test
    public void parseGrammarUnreadableContent() throws Exception {
      String content = "Some invalid content.";
      XSKDataStructureParametersModel parametersModel =
          new XSKDataStructureParametersModel(null, "/test/xsk/com/sap/InvalidContent.hdbsequence", content, null);
      assertThrows(XSKArtifactParserException.class, () -> new XSKHDBSequenceParser().parse(parametersModel));
    }


    @Test
    public void parseRepetitiveProperties() throws Exception {
      String content = org.apache.commons.io.IOUtils
              .toString(XSKHDBSequenceParserTest.class.getResourceAsStream("/test/xsk/com/sap/RepetitivePropsSequence.hdbsequence"),
                      StandardCharsets.UTF_8);
      XSKDataStructureParametersModel parametersModel =
          new XSKDataStructureParametersModel(null, "/test/xsk/com/sap/RepetitivePropsSequence.hdbsequence", content, null);
      assertThrows(XSKHDBSequenceDuplicatePropertyException.class, () -> new XSKHDBSequenceParser().parse(parametersModel));
    }

    @Test
    public void parseRandomlyOrderedContent() throws Exception {
      String content = org.apache.commons.io.IOUtils
              .toString(XSKHDBSequenceParserTest.class.getResourceAsStream("/test/xsk/com/sap/RandomlyOrderedSequence.hdbsequence"),
                      StandardCharsets.UTF_8);
      XSKDataStructureParametersModel parametersModel =
          new XSKDataStructureParametersModel(null, "/test/xsk/com/sap/RandomlyOrderedSequence.hdbsequence", content, null);
      XSKDataStructureHDBSequenceModel model = (XSKDataStructureHDBSequenceModel) new XSKHDBSequenceParser()
              .parse(parametersModel);

      assertEquals("USR_9TCD6SXS67DP7AFLFE420B8EB", model.getSchema());
      assertEquals(Integer.valueOf(10), model.getStartWith());
      assertEquals(Integer.valueOf(30), model.getMaxValue());
      assertEquals(false, model.getNoMaxValue());
      assertEquals(true, model.getNoMinValue());
      assertEquals(false, model.getCycles());
      assertFalse(model.isPublic());
      assertEquals(Integer.valueOf(-2), model.getIncrementBy());
      assertEquals(XSKDBContentType.XS_CLASSIC, model.getDBContentType());
    }


    @Test
    public void parseMissingMandatoryProperty() throws Exception {
      String content = org.apache.commons.io.IOUtils
              .toString(XSKViewParserTest.class.getResourceAsStream("/test/xsk/com/sap/MissingPropSequence.hdbsequence"), StandardCharsets.UTF_8);

      XSKDataStructureParametersModel parametersModel =
          new XSKDataStructureParametersModel(null, "/test/xsk/com/sap/MissingPropSequence.hdbsequence", content, null);
      assertThrows(XSKHDBSequenceMissingPropertyException.class, () -> new XSKHDBSequenceParser().parse(parametersModel));
    }

    @Test
    public void parseNonQuotedSeq() throws Exception {
      String content = "  SEQUENCE seq";
      XSKDataStructureParametersModel parametersModel =
          new XSKDataStructureParametersModel(null, "/test/xsk/com/sap/someFileName.hdbsequence", content, null);
      XSKDataStructureHDBSequenceModel model = (XSKDataStructureHDBSequenceModel) new XSKHDBSequenceParser()
              .parse(parametersModel);
      assertEquals(XSKDBContentType.OTHERS, model.getDBContentType());
    }

    @Test(expected = XSKArtifactParserException.class)
    public void parseHanaXSClassicContentWithLexerErrorFail() throws Exception {
        String content = "start_with= 10;\n" +
                "nomaxvalue=dddddfalse;";
        XSKDataStructureModelFactory.parseView("db/test.hdbsequence", content);
    }

    @Test(expected = XSKArtifactParserException.class)
    public void parseHanaXSClassicContentWithSyntaxErrorFail() throws Exception {
        String content = "start_with= 10;\n" +
                "nomaxvalue=";
        XSKDataStructureModelFactory.parseView("db/test.hdbsequence", content);
    }

}
