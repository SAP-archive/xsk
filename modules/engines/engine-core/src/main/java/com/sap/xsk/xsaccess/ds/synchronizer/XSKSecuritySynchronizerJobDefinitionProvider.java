/*
 * Copyright (c) 2019-2020 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2019-2020 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.xsaccess.ds.synchronizer;

import org.eclipse.dirigible.commons.config.Configuration;
import org.eclipse.dirigible.core.scheduler.api.IJobDefinitionProvider;
import org.eclipse.dirigible.core.scheduler.api.ISchedulerCoreService;
import org.eclipse.dirigible.core.scheduler.service.definition.JobDefinition;

public class XSKSecuritySynchronizerJobDefinitionProvider implements IJobDefinitionProvider {

    private static final String DIRIGIBLE_JOB_EXPRESSION_XSK_SECURITY = "DIRIGIBLE_JOB_EXPRESSION_XSK_SECURITY";
	
	private static final String DIRIGIBLE_INTERNAL_XSK_ACCESS_SYNCHRONIZER_JOB = "dirigible-internal-xsk-access-synchronizer-job";
	
	static final String XSK_PRIVILEGES_AND_ACCESS_SYNCHRONIZER_JOB = "XSK Privileges and Access Synchronizer Job";

	@Override
    public JobDefinition getJobDefinition() {
        JobDefinition jobDefinition = new JobDefinition();
        jobDefinition.setName(DIRIGIBLE_INTERNAL_XSK_ACCESS_SYNCHRONIZER_JOB);
        jobDefinition.setGroup(ISchedulerCoreService.JOB_GROUP_INTERNAL);
        jobDefinition.setClazz(XSKSecuritySynchronizerJob.class.getCanonicalName());
        jobDefinition.setDescription(XSK_PRIVILEGES_AND_ACCESS_SYNCHRONIZER_JOB);
        jobDefinition.setExpression(Configuration.get(DIRIGIBLE_JOB_EXPRESSION_XSK_SECURITY, "0/20 * * * * ?"));
        jobDefinition.setSingleton(true);
        return jobDefinition;
    }
}
