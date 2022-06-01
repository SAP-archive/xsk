package com.sap.xsk.synchronizer;

import com.sap.xsk.synchronizer.XSJSLibSynchronizerPathTypeResolver.ResolvedPathType;
import com.sap.xsk.synchronizer.cleaners.XSJSLibSynchronizerCleaner;
import com.sap.xsk.synchronizer.cleaners.XSJSLibSynchronizerDBCleaner;
import com.sap.xsk.synchronizer.cleaners.XSJSLibSynchronizerFileCleaner;
import org.eclipse.dirigible.repository.api.IRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.sql.DataSource;

public class XSJSLibSynchronizerUnpublisher {
  private static final Logger logger = LoggerFactory.getLogger(XSJSLibSynchronizerUnpublisher.class);

  private final XSJSLibSynchronizerPathTypeResolver resolver;

  private final XSJSLibSynchronizerCleaner fileCleaner;

  private final XSJSLibSynchronizerCleaner dbCleaner;

  XSJSLibSynchronizerUnpublisher(
      XSJSLibSynchronizerPathTypeResolver resolver,
      XSJSLibSynchronizerCleaner fileCleaner,
      XSJSLibSynchronizerCleaner dbCleaner
  ) {
    this.resolver = resolver;
    this.fileCleaner = fileCleaner;
    this.dbCleaner = dbCleaner;
  }

  public void unpublish(String targetRegistryPath) {
      ResolvedPathType type = resolver.resolveWithCollectionFirst(targetRegistryPath);

      switch (type) {
        case EXISTENT_XSJSLIB_FILE: {
          dbCleaner.cleanup(targetRegistryPath);
          fileCleaner.cleanup(targetRegistryPath);
        }
        break;

        case EXISTENT_FOLDER:
          dbCleaner.cleanup(targetRegistryPath);
          break;

        default:
          logger.info("XSJSLibSynchronizer: Nothing to cleanup.");
          break;
      }
  }
}
