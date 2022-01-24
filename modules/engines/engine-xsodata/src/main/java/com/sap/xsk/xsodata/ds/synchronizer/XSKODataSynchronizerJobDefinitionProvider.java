/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2022 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.xsodata.ds.synchronizer;

import org.eclipse.dirigible.commons.config.Configuration;
import org.eclipse.dirigible.core.scheduler.api.IJobDefinitionProvider;
import org.eclipse.dirigible.core.scheduler.api.ISchedulerCoreService;
import org.eclipse.dirigible.core.scheduler.service.definition.JobDefinition;

/**
 * The XSK Data Structures Synchronizer Job Definition Provider.
 */
public class XSKODataSynchronizerJobDefinitionProvider implements IJobDefinitionProvider {

  static final String XSK_ODATA_SYNCHRONIZER_JOB = "XSK OData Synchronizer Job";
  private static final String DIRIGIBLE_JOB_EXPRESSION_XSK_ODATA = "DIRIGIBLE_JOB_EXPRESSION_XSK_ODATA";
  private static final String DIRIGIBLE_INTERNAL_XSK_ODATA_SYNCHRONIZER_JOB = "dirigible-internal-xsk-odata-synchronizer-job";

  /*
   * (non-Javadoc)
   * @see org.eclipse.dirigible.core.scheduler.api.IJobDefinitionProvider#getJobDefinition()
   */
  @Override
  public JobDefinition getJobDefinition() {
    JobDefinition jobDefinition = new JobDefinition();
    jobDefinition.setName(DIRIGIBLE_INTERNAL_XSK_ODATA_SYNCHRONIZER_JOB);
    jobDefinition.setGroup(ISchedulerCoreService.JOB_GROUP_INTERNAL);
    jobDefinition.setClazz(XSKODataSynchronizerJob.class.getCanonicalName());
    jobDefinition.setDescription(XSK_ODATA_SYNCHRONIZER_JOB);
    jobDefinition.setExpression(Configuration.get(DIRIGIBLE_JOB_EXPRESSION_XSK_ODATA, "0/55 * * * * ?"));
    jobDefinition.setSingleton(true);
    return jobDefinition;
  }

}
