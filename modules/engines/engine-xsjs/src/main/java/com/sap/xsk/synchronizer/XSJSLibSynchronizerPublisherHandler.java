package com.sap.xsk.synchronizer;

import org.eclipse.dirigible.core.publisher.api.handlers.MetadataPublisherHandler;
import org.eclipse.dirigible.core.scheduler.api.SchedulerException;

public class XSJSLibSynchronizerPublisherHandler extends MetadataPublisherHandler {

  @Override
  public void beforePublish(String location) throws SchedulerException {
    XSJSLibSynchronizer.forceSynchronization();
  }
}
