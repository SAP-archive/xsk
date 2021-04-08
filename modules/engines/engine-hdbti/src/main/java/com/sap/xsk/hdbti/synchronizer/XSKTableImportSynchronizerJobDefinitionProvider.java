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
package com.sap.xsk.hdbti.synchronizer;

import org.eclipse.dirigible.commons.config.Configuration;
import org.eclipse.dirigible.core.scheduler.api.IJobDefinitionProvider;
import org.eclipse.dirigible.core.scheduler.api.ISchedulerCoreService;
import org.eclipse.dirigible.core.scheduler.service.definition.JobDefinition;

public class XSKTableImportSynchronizerJobDefinitionProvider implements IJobDefinitionProvider {

    private static final String DIRIGIBLE_JOB_EXPRESSION_XSK_TABLE_IMPORT = "DIRIGIBLE_JOB_EXPRESSION_XSK_TABLE_IMPORT";
	
	private static final String DIRIGIBLE_INTERNAL_XSK_TABLE_IMPORT_SYNCHRONIZER_JOB = "dirigible-internal-xsk-table-import-synchronizer-job";
	
	static final String XSK_TABLE_IMPORT_SYNCHRONIZER_JOB = "XSK Table Import Synchronizer Job";

	@Override
    public JobDefinition getJobDefinition() {
        JobDefinition jobDefinition = new JobDefinition();
        jobDefinition.setName(DIRIGIBLE_INTERNAL_XSK_TABLE_IMPORT_SYNCHRONIZER_JOB);
        jobDefinition.setGroup(ISchedulerCoreService.JOB_GROUP_INTERNAL);
        jobDefinition.setClazz(XSKTableImportSynchronizerJob.class.getCanonicalName());
        jobDefinition.setDescription(XSK_TABLE_IMPORT_SYNCHRONIZER_JOB);
        jobDefinition.setExpression(Configuration.get(DIRIGIBLE_JOB_EXPRESSION_XSK_TABLE_IMPORT, "0/45 * * * * ?"));
        jobDefinition.setSingleton(true);
        return jobDefinition;
    }
}
