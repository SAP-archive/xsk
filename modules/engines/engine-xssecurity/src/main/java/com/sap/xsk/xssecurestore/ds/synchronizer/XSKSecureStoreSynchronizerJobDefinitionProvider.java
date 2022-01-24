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
package com.sap.xsk.xssecurestore.ds.synchronizer;

import org.eclipse.dirigible.commons.config.Configuration;
import org.eclipse.dirigible.core.scheduler.api.IJobDefinitionProvider;
import org.eclipse.dirigible.core.scheduler.api.ISchedulerCoreService;
import org.eclipse.dirigible.core.scheduler.service.definition.JobDefinition;

public class XSKSecureStoreSynchronizerJobDefinitionProvider implements IJobDefinitionProvider {

  static final String XSK_SECURE_STORE_SYNCHRONIZER_JOB = "XSK Secure Store Synchronizer Job";
  private static final String DIRIGIBLE_JOB_EXPRESSION_XSK_SECURE_STORE = "DIRIGIBLE_JOB_EXPRESSION_XSK_SECURE_STORE";
  private static final String DIRIGIBLE_INTERNAL_XSK_SECURE_CREATE_SECURE_STORE_VALUE_SYNCHRONIZER_JOB = "dirigible-internal-xsk-secure-store-synchronizer-job";

  @Override
  public JobDefinition getJobDefinition() {
    JobDefinition jobDefinition = new JobDefinition();
    jobDefinition.setName(DIRIGIBLE_INTERNAL_XSK_SECURE_CREATE_SECURE_STORE_VALUE_SYNCHRONIZER_JOB);
    jobDefinition.setGroup(ISchedulerCoreService.JOB_GROUP_INTERNAL);
    jobDefinition.setClazz(XSKSecureStoreSynchronizerJob.class.getCanonicalName());
    jobDefinition.setDescription(XSK_SECURE_STORE_SYNCHRONIZER_JOB);
    jobDefinition.setExpression(Configuration.get(DIRIGIBLE_JOB_EXPRESSION_XSK_SECURE_STORE, "0/25 * * * * ?"));
    jobDefinition.setSingleton(true);
    return jobDefinition;
  }
}
