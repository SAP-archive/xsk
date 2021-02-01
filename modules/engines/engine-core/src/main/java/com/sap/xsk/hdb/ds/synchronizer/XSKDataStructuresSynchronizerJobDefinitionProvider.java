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
package com.sap.xsk.hdb.ds.synchronizer;

import org.eclipse.dirigible.commons.config.Configuration;
import org.eclipse.dirigible.core.scheduler.api.IJobDefinitionProvider;
import org.eclipse.dirigible.core.scheduler.api.ISchedulerCoreService;
import org.eclipse.dirigible.core.scheduler.service.definition.JobDefinition;

/**
 * The XSK Data Structures Synchronizer Job Definition Provider.
 */
public class XSKDataStructuresSynchronizerJobDefinitionProvider implements IJobDefinitionProvider {

	private static final String DIRIGIBLE_JOB_EXPRESSION_XSK_DATA_STRUCTURE = "DIRIGIBLE_JOB_EXPRESSION_XSK_DATA_STRUCTURE";
	
	private static final String DIRIGIBLE_INTERNAL_XSK_DATA_STRUCTURES_SYNCHRONIZER_JOB = "dirigible-internal-xsk-data-structures-synchronizer-job";
	
	static final String XSK_DATA_STRUCTURES_SYNCHRONIZER_JOB = "XSK Data Structures Synchronizer Job";

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.dirigible.core.scheduler.api.IJobDefinitionProvider#getJobDefinition()
	 */
	@Override
	public JobDefinition getJobDefinition() {
		JobDefinition jobDefinition = new JobDefinition();
		jobDefinition.setName(DIRIGIBLE_INTERNAL_XSK_DATA_STRUCTURES_SYNCHRONIZER_JOB);
		jobDefinition.setGroup(ISchedulerCoreService.JOB_GROUP_INTERNAL);
		jobDefinition.setClazz(XSKDataStructuresSynchronizerJob.class.getCanonicalName());
		jobDefinition.setDescription(XSK_DATA_STRUCTURES_SYNCHRONIZER_JOB);
		jobDefinition.setExpression(Configuration.get(DIRIGIBLE_JOB_EXPRESSION_XSK_DATA_STRUCTURE ,"0/30 * * * * ?"));
		jobDefinition.setSingleton(true);
		return jobDefinition;
	}

}
