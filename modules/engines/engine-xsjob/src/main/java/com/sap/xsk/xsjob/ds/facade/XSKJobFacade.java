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

  private static XSKJobCoreService jobService = new XSKJobCoreService();

  public static final ArrayList<String> newJob(String job) throws ParseException, SchedulerException {
    XSKJobArtifact xskJobArtifact = jobService.parseJob(job);
    XSKJobToXSKJobDefinitionTransformer xskJobToXSKJobDefinitionTransformer = new XSKJobToXSKJobDefinitionTransformer();
    ArrayList<XSKJobDefinition> xskJobDefinitions = xskJobToXSKJobDefinitionTransformer.transform(xskJobArtifact);
    ArrayList<String> scheduleNames = new ArrayList<>();
    for (XSKJobDefinition jobDefinition : xskJobDefinitions) {
      if (!jobService.existsJob(jobDefinition.getName())) {
        jobService.createJob(jobDefinition.getName(), jobDefinition.getGroup(), jobDefinition.getDescription(),
            jobDefinition.getModule(), jobDefinition.getFunction(), jobDefinition.getCronExpression(), jobDefinition.getParametersAsMap());
      }
      scheduleNames.add(jobDefinition.getName());
    }

    return scheduleNames;
  }

  public static final void activate(ArrayList<String> names) throws SchedulerException {
    for (String name : names) {
      XSKJobDefinition jobDefinition = jobService.getJob(name);

      XSKSchedulerManager.scheduleJob(jobDefinition);
    }
  }

  public static final void deactivate(ArrayList<String> names) throws SchedulerException {
    for (String name : names) {
      XSKJobDefinition jobDefinition = jobService.getJob(name);

      XSKSchedulerManager.unscheduleJob(name, jobDefinition.getGroup());
    }
  }

  public static final void configure(ArrayList<String> names, boolean status, Timestamp startAt, Timestamp endAt)
      throws SchedulerException {
    deactivate(names);

    for (String name : names) {
      XSKJobDefinition jobDefinition = jobService.getJob(name);

      jobService.updateJob(jobDefinition.getName(), jobDefinition.getGroup(), jobDefinition.getDescription(),
          jobDefinition.getModule(), jobDefinition.getFunction(), jobDefinition.getCronExpression(), startAt,
          endAt,
          jobDefinition.getParametersAsMap());
    }

    if (status) {
      activate(names);
    }
  }

  public static final XSKJobDefinition getConfiguration(String name) throws SchedulerException {
    return jobService.getJob(name);
  }

  public static final boolean isActive(String name) throws SchedulerException {
    return XSKSchedulerManager.existsJob(name);
  }

}
