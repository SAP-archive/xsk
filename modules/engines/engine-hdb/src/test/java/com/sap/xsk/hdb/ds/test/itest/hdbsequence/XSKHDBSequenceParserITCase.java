package com.sap.xsk.hdb.ds.test.itest.hdbsequence;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.name.Names;
import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.facade.IXSKHDBCoreFacade;
import com.sap.xsk.hdb.ds.test.itest.hdbsequence.module.XSKHDBTestModule;
import org.eclipse.dirigible.core.scheduler.api.SynchronizationException;
import org.eclipse.dirigible.repository.api.RepositoryPath;
import org.eclipse.dirigible.repository.fs.FileSystemRepository;
import org.eclipse.dirigible.repository.local.LocalRepository;
import org.eclipse.dirigible.repository.local.LocalResource;
import org.junit.Before;
import org.junit.Test;
import java.sql.SQLException;

public class XSKHDBSequenceParserITCase {


  private IXSKHDBCoreFacade facade;

  @Before
  public void setUp() throws SQLException {
    Injector injector = Guice.createInjector(new XSKHDBTestModule());
    facade = injector.getInstance(Key.get(IXSKHDBCoreFacade.class, Names.named("xskHDBCoreFacade")));
  }

  @Test
  public void testHDBSequenceCreate() throws XSKDataStructuresException, SynchronizationException {
    FileSystemRepository fileRepo = new LocalRepository("/SampleSequence_HanaXSClassic11.hdbsequence");
    RepositoryPath path = new RepositoryPath("/SampleSequence_HanaXSClassic11.hdbsequence");
    LocalResource resource = new LocalResource(fileRepo, path);
    facade.handleResourceSynchronization(resource);
  }
}
