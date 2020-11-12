package com.sap.xsk.xssecurestore.ds.synchronizer;

import org.eclipse.dirigible.commons.config.Configuration;
import org.eclipse.dirigible.core.scheduler.api.IJobDefinitionProvider;
import org.eclipse.dirigible.core.scheduler.api.ISchedulerCoreService;
import org.eclipse.dirigible.core.scheduler.service.definition.JobDefinition;

public class XSKSecureStoreSynchronizerJobDefinitionProvider implements IJobDefinitionProvider {

    private static final String DIRIGIBLE_JOB_EXPRESSION_XSK_SECURE_STORE = "DIRIGIBLE_JOB_EXPRESSION_XSK_SECURE_STORE";
	
	private static final String DIRIGIBLE_INTERNAL_XSK_SECURE_CREATE_SECURE_STORE_VALUE_SYNCHRONIZER_JOB = "dirigible-internal-xsk-secure-store-synchronizer-job";
	
	static final String XSK_SECURE_STORE_SYNCHRONIZER_JOB = "XSK Secure Store Synchronizer Job";

	@Override
    public JobDefinition getJobDefinition() {
        JobDefinition jobDefinition = new JobDefinition();
        jobDefinition.setName(DIRIGIBLE_INTERNAL_XSK_SECURE_CREATE_SECURE_STORE_VALUE_SYNCHRONIZER_JOB);
        jobDefinition.setGroup(ISchedulerCoreService.JOB_GROUP_INTERNAL);
        jobDefinition.setClazz(XSKSecureStoreSynchronizerJob.class.getCanonicalName());
        jobDefinition.setDescription(XSK_SECURE_STORE_SYNCHRONIZER_JOB);
        jobDefinition.setExpression(Configuration.get(DIRIGIBLE_JOB_EXPRESSION_XSK_SECURE_STORE, "0/15 * * * * ?"));
        jobDefinition.setSingleton(true);
        return jobDefinition;
    }
}
