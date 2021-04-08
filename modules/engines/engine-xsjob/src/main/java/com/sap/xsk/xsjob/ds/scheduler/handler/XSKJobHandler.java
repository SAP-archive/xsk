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
package com.sap.xsk.xsjob.ds.scheduler.handler;

import com.sap.xsk.engine.XSKJavascriptEngineExecutor;
import com.sap.xsk.xsjob.ds.api.IXSKJobCoreService;
import java.util.HashMap;
import java.util.Map;
import org.eclipse.dirigible.commons.api.module.StaticInjector;
import org.eclipse.dirigible.commons.api.scripting.ScriptingException;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class XSKJobHandler implements Job {

  private XSKJavascriptEngineExecutor xskJavascriptEngineExecutor = StaticInjector.getInjector()
      .getInstance(XSKJavascriptEngineExecutor.class);

  @Override
  public void execute(JobExecutionContext context) throws JobExecutionException {
    String module = (String) context.getJobDetail().getJobDataMap().get(IXSKJobCoreService.XSK_JOB_MODULE);

    Map<String, String> parametersAsMap = (Map<String, String>) context.getJobDetail().getJobDataMap()
        .get(IXSKJobCoreService.XSK_JOB_PARAMETERS);
    Object[] parametersArray = parametersAsMap.values().toArray();

    Map<Object, Object> executionContext = new HashMap<>();
    executionContext
        .put(IXSKJobCoreService.XSK_JOB_FUNCTION, context.getJobDetail().getJobDataMap().get(IXSKJobCoreService.XSK_JOB_FUNCTION));
    executionContext.put(IXSKJobCoreService.XSK_JOB_PARAMETERS, parametersArray);

    try {
      xskJavascriptEngineExecutor.executeService(module, executionContext, true);
    } catch (ScriptingException e) {
      throw new JobExecutionException(e);
    }
  }
}
