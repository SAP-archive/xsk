/*
 * Copyright (c) 2019-2021 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2019-2021 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.xsjob.ds.synchronizer;

import static java.text.MessageFormat.format;

import com.sap.xsk.xsjob.ds.api.IXSKJobCoreService;
import com.sap.xsk.xsjob.ds.api.IXSKJobModel;
import com.sap.xsk.xsjob.ds.model.XSKJobArtifact;
import com.sap.xsk.xsjob.ds.model.XSKJobDefinition;
import com.sap.xsk.xsjob.ds.scheduler.XSKSchedulerManager;
import com.sap.xsk.xsjob.ds.service.XSKJobCoreService;
import com.sap.xsk.xsjob.ds.tronsformer.XSKJobToXSKJobDefinitionTransformer;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.apache.commons.io.IOUtils;
import org.eclipse.dirigible.commons.api.module.StaticInjector;
import org.eclipse.dirigible.core.scheduler.api.AbstractSynchronizer;
import org.eclipse.dirigible.core.scheduler.api.SchedulerException;
import org.eclipse.dirigible.core.scheduler.api.SynchronizationException;
import org.eclipse.dirigible.repository.api.IResource;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class XSKJobSynchronizer extends AbstractSynchronizer {

  private static final Logger logger = LoggerFactory.getLogger(XSKJobSynchronizer.class);

  private static final Map<String, XSKJobDefinition> JOBS_PREDELIVERED = Collections
      .synchronizedMap(new HashMap<String, XSKJobDefinition>());

  private static final List<String> JOBS_SYNCHRONIZED = Collections.synchronizedList(new ArrayList<String>());
  private final String SYNCHRONIZER_NAME = this.getClass().getCanonicalName();
  @Inject
  private XSKJobCoreService schedulerCoreService;

  // @Inject
  // private SchedulerManager schedulerManager;
  @Inject
  private XSKJobToXSKJobDefinitionTransformer xskJobToXSKJobDefinitionTransformer;

  /**
   * Force synchronization.
   */
  public static final void forceSynchronization() {
    XSKJobSynchronizer synchronizer = StaticInjector.getInjector().getInstance(XSKJobSynchronizer.class);
    synchronizer.setForcedSynchronization(true);
    try {
      synchronizer.synchronize();
    } finally {
      synchronizer.setForcedSynchronization(false);
    }
  }

  /*
   * (non-Javadoc)
   * @see org.eclipse.dirigible.core.scheduler.api.ISynchronizer#synchronize()
   */
  @Override
  public void synchronize() {
    synchronized (XSKJobSynchronizer.class) {
      if (beforeSynchronizing()) {
        logger.trace("Synchronizing Jobs...");
        try {
          startSynchronization(SYNCHRONIZER_NAME);
          clearCache();
          synchronizePredelivered();
          synchronizeRegistry();
          startJobs();
          int immutableCount = JOBS_PREDELIVERED.size();
          int mutableCount = JOBS_SYNCHRONIZED.size();
          cleanup();
          clearCache();
          successfulSynchronization(SYNCHRONIZER_NAME, format("Immutable: {0}, Mutable: {1}", immutableCount, mutableCount));
        } catch (Exception e) {
          logger.error("Synchronizing process for Jobs failed.", e);
          try {
            failedSynchronization(SYNCHRONIZER_NAME, e.getMessage());
          } catch (SchedulerException e1) {
            logger.error("Synchronizing process for Jobs files failed in registering the state log.", e);
          }
        }
        logger.trace("Done synchronizing Jobs.");
        afterSynchronizing();
      }
    }
  }

  /**
   * Register predelivered job.
   *
   * @param jobPath the job path
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public void registerPredeliveredJob(String jobPath) throws IOException, ParseException {
    InputStream in = XSKJobSynchronizer.class.getResourceAsStream(jobPath);
    try {
      String json = IOUtils.toString(in, StandardCharsets.UTF_8);
      XSKJobArtifact xskJobArtifact = schedulerCoreService.parseJob(json);
      XSKJobDefinition jobDefinition = xskJobToXSKJobDefinitionTransformer.transform(xskJobArtifact);
      jobDefinition.setName(jobPath);
      JOBS_PREDELIVERED.put(jobPath, jobDefinition);
    } finally {
      if (in != null) {
        in.close();
      }
    }
  }

  /*
   * (non-Javadoc)
   * @see org.eclipse.dirigible.core.scheduler.api.AbstractSynchronizer#synchronizeRegistry()
   */
  @Override
  protected void synchronizeRegistry() throws SynchronizationException {
    logger.trace("Synchronizing Jobs from Registry...");

    super.synchronizeRegistry();

    logger.trace("Done synchronizing Jobs from Registry.");
  }

  /*
   * (non-Javadoc)
   * @see org.eclipse.dirigible.core.scheduler.api.AbstractSynchronizer#synchronizeResource(com.sap.xsk.hdb.ds.parser.XSKDataStructureParser
   * repository.api.IResource)
   */
  @Override
  protected void synchronizeResource(IResource resource) throws SynchronizationException {
    String resourceName = resource.getName();
    if (resourceName.endsWith(IXSKJobModel.XSK_JOB_FILE_EXTENSION)) {
      XSKJobArtifact xskJobArtifact = schedulerCoreService.parseJob(resource.getContent());
      try {
        XSKJobDefinition xskJobDefinition = xskJobToXSKJobDefinitionTransformer.transform(xskJobArtifact);
        xskJobDefinition.setGroup(IXSKJobCoreService.XSK_DEFINED_GROUP);
        synchronizeJob(xskJobDefinition);
      } catch (ParseException e) {
        throw new SynchronizationException();
      }
    }

  }

  /*
   * (non-Javadoc)
   * @see org.eclipse.dirigible.core.scheduler.api.AbstractSynchronizer#cleanup()
   */
  @Override
  protected void cleanup() throws SynchronizationException {
    logger.trace("Cleaning up Jobs...");

    try {
      List<XSKJobDefinition> jobDefinitions = schedulerCoreService.getJobs();
      for (XSKJobDefinition jobDefinition : jobDefinitions) {
        if (!JOBS_SYNCHRONIZED.contains(jobDefinition.getName())) {
          schedulerCoreService.removeJob(jobDefinition.getName());
          logger.warn("Cleaned up Job [{}] from group: {}", jobDefinition.getName(), jobDefinition.getGroup());
        }
      }
    } catch (SchedulerException e) {
      throw new SynchronizationException(e);
    }

    logger.trace("Done cleaning up Jobs.");
  }

  private void startJobs() throws SchedulerException {
    logger.trace("Start Jobs...");

    for (String jobName : JOBS_SYNCHRONIZED) {
      if (!XSKSchedulerManager.existsJob(jobName)) {
        try {
          XSKJobDefinition jobDefinition = schedulerCoreService.getJob(jobName);
          XSKSchedulerManager.scheduleJob(jobDefinition);
        } catch (SchedulerException e) {
          logger.error(e.getMessage(), e);
        }
      }
    }

    Set<TriggerKey> runningJobs = XSKSchedulerManager.listJobs();
    for (TriggerKey jobKey : runningJobs) {
      try {
        if (!JOBS_SYNCHRONIZED.contains(jobKey.getName())) {
          XSKSchedulerManager.unscheduleJob(jobKey.getName(), jobKey.getGroup());
        }
      } catch (SchedulerException e) {
        logger.error(e.getMessage(), e);
      }
    }

    logger.trace("Running Jobs: " + runningJobs.size());
    logger.trace("Done starting Jobs.");
  }

  private void clearCache() {
    JOBS_SYNCHRONIZED.clear();
  }

  private void synchronizePredelivered() throws SynchronizationException {
    logger.trace("Synchronizing predelivered Jobs...");
    // Jobs
    for (XSKJobDefinition jobDefinition : JOBS_PREDELIVERED.values()) {
      synchronizeJob(jobDefinition);
    }
    logger.trace("Done synchronizing predelivered Jobs.");
  }

  private void synchronizeJob(XSKJobDefinition xskJob) throws SynchronizationException {
    try {
      if (!schedulerCoreService.existsJob(xskJob.getName())) {
        schedulerCoreService.createJob(xskJob.getName(), xskJob.getGroup(), xskJob.getDescription(),
            xskJob.getModule(), xskJob.getFunction(), xskJob.getCronExpression(), xskJob.getParametersAsMap());
        logger.info("Synchronized a new Job [{}] from group: {}", xskJob.getName(), xskJob.getGroup());
      } else {
        XSKJobDefinition existing = schedulerCoreService.getJob(xskJob.getName());
        if (!xskJob.equals(existing)) {
          schedulerCoreService.updateJob(xskJob.getName(), xskJob.getGroup(), xskJob.getDescription(),
              xskJob.getModule(), xskJob.getFunction(), xskJob.getCronExpression(), xskJob.getParametersAsMap());
          logger.info("Synchronized a modified Job [{}] from group: {}", xskJob.getName(), xskJob.getGroup());
        }
      }
      JOBS_SYNCHRONIZED.add(xskJob.getName());
    } catch (SchedulerException e) {
      throw new SynchronizationException(e);
    }
  }
}
