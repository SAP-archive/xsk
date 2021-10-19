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
package com.sap.xsk.xsjob.ds.scheduler.handler;

import com.sap.xsk.engine.XSKJavascriptEngineExecutor;
import com.sap.xsk.utils.XSKCommonsConstants;
import com.sap.xsk.utils.XSKCommonsUtils;
import com.sap.xsk.xsjob.ds.api.IXSKJobCoreService;
import java.util.HashMap;
import java.util.Map;
import org.eclipse.dirigible.commons.api.scripting.ScriptingException;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class XSKJobHandler implements Job {

  private static final String XSJOB_HANDLER = "xsjob/wrappers/handler.xsjs";

  private XSKJavascriptEngineExecutor xskJavascriptEngineExecutor = new XSKJavascriptEngineExecutor();

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
    executionContext.put(IXSKJobCoreService.XSK_JOB_MODULE, module);
    try {
      xskJavascriptEngineExecutor.executeService(XSJOB_HANDLER, executionContext, true, true);
    } catch (ScriptingException e) {
      XSKCommonsUtils.logProcessorErrors(e.getMessage(), XSKCommonsConstants.PROCESSOR_ERROR, context.getJobDetail().getDescription(), XSKCommonsConstants.XSK_JOB_PARSER);
      throw new JobExecutionException(e);
    }
  }
}
