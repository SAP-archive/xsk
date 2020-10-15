package com.sap.xsk.xscjob.ds.synchronizer;

import org.eclipse.dirigible.commons.config.Configuration;
import org.eclipse.dirigible.core.scheduler.api.IJobDefinitionProvider;
import org.eclipse.dirigible.core.scheduler.api.ISchedulerCoreService;
import org.eclipse.dirigible.core.scheduler.service.definition.JobDefinition;

public class XSKJobSynchronizerJobDefinitionProvider implements IJobDefinitionProvider {

    private static final String DIRIGIBLE_JOB_EXPRESSION_XSK_JOB = "DIRIGIBLE_JOB_EXPRESSION_XSK_JOB";
	private static final String XSK_JOB_SYNCHRONIZER_JOB = "XSK Job Synchronizer Job";
	private static final String DIRIGIBLE_INTERNAL_XSK_JOB_SYNCHRONIZER_JOB = "dirigible-internal-xsc-job-synchronizer-job";

	@Override
    public JobDefinition getJobDefinition() {
        JobDefinition jobDefinition = new JobDefinition();
        jobDefinition.setName(DIRIGIBLE_INTERNAL_XSK_JOB_SYNCHRONIZER_JOB);
        jobDefinition.setGroup(ISchedulerCoreService.JOB_GROUP_INTERNAL);
        jobDefinition.setClazz(XSKJobSynchronizerJob.class.getCanonicalName());
        jobDefinition.setDescription(XSK_JOB_SYNCHRONIZER_JOB);
        jobDefinition.setExpression(Configuration.get(DIRIGIBLE_JOB_EXPRESSION_XSK_JOB, "0/20 * * * * ?"));
        jobDefinition.setSingleton(true);
        return jobDefinition;
    }
}
