package com.sap.xsk.hdb.ds.processors.hdi;

import com.sap.xsk.hdb.ds.model.hdi.XSKDataStructureHDIModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.sql.Connection;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class XSKHDIContainerCreateProcessorTest {

  @Mock(answer = Answers.RETURNS_DEEP_STUBS)
  private Connection mockConnection;

  @Test
  public void testExecute() {
    XSKHDIContainerCreateProcessor processorSpy = spy(XSKHDIContainerCreateProcessor.class);
    XSKDataStructureHDIModel hdiModel = new XSKDataStructureHDIModel();

    processorSpy.execute(mockConnection, hdiModel);
    verify(processorSpy, times(1)).execute(mockConnection, hdiModel);
  }

}
