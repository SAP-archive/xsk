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
package com.sap.xsk.resources.neo.sdk.synchronizer;

import org.eclipse.dirigible.core.scheduler.api.IJobDefinitionProvider;
import org.eclipse.dirigible.core.scheduler.api.ISchedulerCoreService;
import org.eclipse.dirigible.core.scheduler.service.definition.JobDefinition;

/**
 * The XSK Resources Neo Sdk Synchronizer Job Definition Provider.
 */
public class XSKResourcesNeoSdkSynchronizerJobDefinitionProvider implements IJobDefinitionProvider {

	private static final String DIRIGIBLE_INTERNAL_XSK_RESOURCES_NEO_SDK_SYNCHRONIZER_JOB = "dirigible-internal-xsk-resources-neo-sdk-synchronizer-job";
	
	static final String RESOURCES_NEO_SDK_SYNCHRONIZER_JOB = "XSK Resources Neo SDK Synchronizer Job";

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.dirigible.core.scheduler.api.IJobDefinitionProvider#getJobDefinition()
	 */
	@Override
	public JobDefinition getJobDefinition() {
		JobDefinition jobDefinition = new JobDefinition();
		jobDefinition.setName(DIRIGIBLE_INTERNAL_XSK_RESOURCES_NEO_SDK_SYNCHRONIZER_JOB);
		jobDefinition.setGroup(ISchedulerCoreService.JOB_GROUP_INTERNAL);
		jobDefinition.setClazz(XSKResourcesNeoSdkSynchronizerJob.class.getCanonicalName());
		jobDefinition.setDescription(RESOURCES_NEO_SDK_SYNCHRONIZER_JOB);
		jobDefinition.setExpression("");
		jobDefinition.setSingleton(true);
		return jobDefinition;
	}

}