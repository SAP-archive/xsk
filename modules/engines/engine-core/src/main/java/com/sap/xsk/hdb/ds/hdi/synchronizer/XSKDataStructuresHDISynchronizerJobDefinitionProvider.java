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
package com.sap.xsk.hdb.ds.hdi.synchronizer;

import org.eclipse.dirigible.commons.config.Configuration;
import org.eclipse.dirigible.core.scheduler.api.IJobDefinitionProvider;
import org.eclipse.dirigible.core.scheduler.api.ISchedulerCoreService;
import org.eclipse.dirigible.core.scheduler.service.definition.JobDefinition;

/**
 * The XSK Data Structures HDI Synchronizer Job Definition Provider.
 */
public class XSKDataStructuresHDISynchronizerJobDefinitionProvider implements IJobDefinitionProvider {

	private static final String DIRIGIBLE_JOB_EXPRESSION_XSK_DATA_HDI_STRUCTURE = "DIRIGIBLE_JOB_EXPRESSION_XSK_DATA_STRUCTURE_HDI";
	
	private static final String DIRIGIBLE_INTERNAL_XSK_DATA_STRUCTURES_HDI_SYNCHRONIZER_JOB = "dirigible-internal-xsk-data-structures-hdi-synchronizer-job";
	
	static final String XSK_DATA_STRUCTURES_HDI_SYNCHRONIZER_JOB = "XSK Data Structures HDI Synchronizer Job";

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.dirigible.core.scheduler.api.IJobDefinitionProvider#getJobDefinition()
	 */
	@Override
	public JobDefinition getJobDefinition() {
		JobDefinition jobDefinition = new JobDefinition();
		jobDefinition.setName(DIRIGIBLE_INTERNAL_XSK_DATA_STRUCTURES_HDI_SYNCHRONIZER_JOB);
		jobDefinition.setGroup(ISchedulerCoreService.JOB_GROUP_INTERNAL);
		jobDefinition.setClazz(XSKDataStructuresHDISynchronizerJob.class.getCanonicalName());
		jobDefinition.setDescription(XSK_DATA_STRUCTURES_HDI_SYNCHRONIZER_JOB);
		jobDefinition.setExpression(Configuration.get(DIRIGIBLE_JOB_EXPRESSION_XSK_DATA_HDI_STRUCTURE ,"0/55 * * * * ?"));
		jobDefinition.setSingleton(true);
		return jobDefinition;
	}

}
