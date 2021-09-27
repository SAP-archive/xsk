/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2021 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.xsjob.ds.facade;

import com.sap.xsk.xsjob.ds.model.XSKJobArtifact;
import com.sap.xsk.xsjob.ds.model.XSKJobDefinition;
import com.sap.xsk.xsjob.ds.scheduler.XSKSchedulerManager;
import com.sap.xsk.xsjob.ds.service.XSKJobCoreService;
import com.sap.xsk.xsjob.ds.transformer.XSKJobToXSKJobDefinitionTransformer;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import org.eclipse.dirigible.core.scheduler.api.SchedulerException;

public class XSKJobFacade {

  public static final void newJob(String job, String jobPath) throws ParseException, SchedulerException {
    XSKJobCoreService jobService = new XSKJobCoreService();
    XSKJobArtifact xskJobArtifact = jobService.parseJob(job);
    XSKJobToXSKJobDefinitionTransformer xskJobToXSKJobDefinitionTransformer = new XSKJobToXSKJobDefinitionTransformer();
    ArrayList<XSKJobDefinition> xskJobDefinitions = xskJobToXSKJobDefinitionTransformer.transform(xskJobArtifact);
    for (XSKJobDefinition jobDefinition : xskJobDefinitions) {
      if (!jobService.existsJob(jobPath)) {
        jobService.createJob(jobPath, jobDefinition.getGroup(), jobDefinition.getDescription(),
            jobDefinition.getModule(), jobDefinition.getFunction(), jobDefinition.getCronExpression(), jobDefinition.getParametersAsMap());
      }
    }
  }

  public static final void activate(String name) throws SchedulerException {
    XSKJobCoreService jobService = new XSKJobCoreService();
    XSKJobDefinition jobDefinition = jobService.getJob(name);

    if (jobDefinition.getStartAt() != null && jobDefinition.getEndAt() != null) {
      XSKSchedulerManager.scheduleJob(jobDefinition);
    }
  }

  public static final void deactivate(String name) throws SchedulerException {
    XSKJobCoreService jobService = new XSKJobCoreService();
    XSKJobDefinition jobDefinition = jobService.getJob(name);

    XSKSchedulerManager.unscheduleJob(name, jobDefinition.getGroup());
  }

  public static final void configure(String name, boolean status, Timestamp startAt, Timestamp endAt) throws SchedulerException {
    XSKJobCoreService jobService = new XSKJobCoreService();
    XSKJobDefinition jobDefinition = jobService.getJob(name);

    deactivate(name);

    jobService.updateJob(jobDefinition.getName(), jobDefinition.getGroup(), jobDefinition.getDescription(),
        jobDefinition.getModule(), jobDefinition.getFunction(), jobDefinition.getCronExpression(), startAt,
        endAt,
        jobDefinition.getParametersAsMap());

    if (status) {
      activate(name);
    }
  }

}
