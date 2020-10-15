/**
 * Copyright (c) 2010-2018 SAP
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

	private static final String DIRIGIBLE_JOB_EXPRESSION_XSK_ODATA = "DIRIGIBLE_JOB_EXPRESSION_XSK_ODATA";
	private static final String XSK_O_DATA_SYNCHRONIZER_JOB = "XSK OData Synchronizer Job";
	private static final String DIRIGIBLE_INTERNAL_XSK_ODATA_SYNCHRONIZER_JOB = "dirigible-internal-xsc-odata-synchronizer-job";

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
		jobDefinition.setDescription(XSK_O_DATA_SYNCHRONIZER_JOB);
		jobDefinition.setExpression(Configuration.get(DIRIGIBLE_JOB_EXPRESSION_XSK_ODATA, "0/10 * * * * ?"));
		jobDefinition.setSingleton(true);
		return jobDefinition;
	}

}
