/**
 * Copyright (c) 2010-2018 SAP
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
		jobDefinition.setExpression(Configuration.get(DIRIGIBLE_JOB_EXPRESSION_XSK_DATA_STRUCTURE ,"0/5 * * * * ?"));
		jobDefinition.setSingleton(true);
		return jobDefinition;
	}

}
