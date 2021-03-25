package com.sap.xsk.hdb.ds.test.parser;

import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.model.XSKHanaVersion;
import com.sap.xsk.hdb.ds.model.hdbsequence.XSKDataStructureHDBSequenceModel;
import com.sap.xsk.hdb.ds.parser.hdbsequence.XSKHDBSequenceParser;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class XSKHDBSequenceParserTest {
    @Test
    public void parseHanav1Content() throws Exception {
        String content = org.apache.commons.io.IOUtils.toString(XSKViewParserTest.class.getResourceAsStream("/test/xsk/com/sap/SampleSequence_hanav1.hdbsequence"), StandardCharsets.UTF_8);
        XSKDataStructureHDBSequenceModel model = (XSKDataStructureHDBSequenceModel) new XSKHDBSequenceParser().parse("/test/xsk/com/sap/SampleSequence_hanav1.hdbsequence", content);

        assertEquals("MYSCHEMA", model.getSchema());
        assertEquals(Integer.valueOf(10),model.getStart_with());
        assertEquals(Integer.valueOf(30), model.getMaxvalue());
        assertEquals(false, model.getNomaxvalue());
        assertEquals(true, model.getNominvalue());
        assertEquals(false,model.getCycles());
        assertEquals(XSKHanaVersion.VERSION_1, model.getHanaVersion());
    }

    @Test
    public void parseHanav2Content() throws Exception {
        String content = org.apache.commons.io.IOUtils.toString(XSKViewParserTest.class.getResourceAsStream("/test/xsk/com/sap/CustomerId_hanav2.hdbsequence"), StandardCharsets.UTF_8);
        XSKDataStructureHDBSequenceModel model = (XSKDataStructureHDBSequenceModel) new XSKHDBSequenceParser().parse("/test/xsk/com/sap/CustomerId_hanav2.hdbsequence", content);
        assertEquals(XSKHanaVersion.VERSION_2, model.getHanaVersion());
        assertEquals(content, model.getRawContent());
    }

    @Test
    public void parseInvalidContent() throws Exception {
        String content = org.apache.commons.io.IOUtils.toString(XSKViewParserTest.class.getResourceAsStream("/test/xsk/com/sap/InvalidContent.hdbsequence"), StandardCharsets.UTF_8);

        assertThrows(XSKDataStructuresException.class, () -> {
            new XSKHDBSequenceParser().parse("/test/xsk/com/sap/InvalidContent.hdbsequence", content);
        });
    }
}
