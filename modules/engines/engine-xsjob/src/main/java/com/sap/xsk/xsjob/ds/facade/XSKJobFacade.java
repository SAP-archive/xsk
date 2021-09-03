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

import com.sap.xsk.xsjob.ds.model.XSKJobDefinition;
import com.sap.xsk.xsjob.ds.scheduler.XSKSchedulerManager;
import com.sap.xsk.xsjob.ds.service.XSKJobCoreService;
import com.sap.xsk.xsjob.ds.synchronizer.XSKJobSynchronizer;
import org.eclipse.dirigible.core.scheduler.api.SchedulerException;
import java.io.IOException;
import java.text.ParseException;

public class XSKJobFacade {

  public static final void newJob(String jobPath) throws IOException, ParseException {
    XSKJobSynchronizer  jobSynchronizer = new XSKJobSynchronizer();
    jobSynchronizer.registerPredeliveredJob(jobPath);

    jobSynchronizer.synchronize();
  }

  public static final void activate(String name) throws SchedulerException {
    XSKJobCoreService jobService = new XSKJobCoreService();
    XSKJobDefinition jobDefinition = jobService.getJob(name);

    XSKSchedulerManager.scheduleJob(jobDefinition);
  }

  public static final void deactivate(String name) throws SchedulerException {
    XSKJobCoreService jobService = new XSKJobCoreService();
    XSKJobDefinition jobDefinition = jobService.getJob(name);

    XSKSchedulerManager.unscheduleJob(name, jobDefinition.getGroup());
  }

}
