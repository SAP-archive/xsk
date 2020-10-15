package com.sap.xsk.xscjob.ds.api;

import java.util.List;
import java.util.Map;

import org.eclipse.dirigible.core.scheduler.api.SchedulerException;

import com.sap.xsk.xscjob.ds.model.XSKJobArtifact;
import com.sap.xsk.xscjob.ds.model.XSKJobDefinition;

public interface IXSKJobCoreService {

    String XSK_DEFINED_GROUP = "xsc-defined";

    String XSK_JOB_PARAMETERS = "xsc-job-parameters";

    String XSK_JOB_FUNCTION = "xsc-job-function";

    String XSK_JOB_MODULE = "xsc-jo-module";

    XSKJobDefinition createJob(String name, String group, String description, String module, String action, String cronExpression, Map<String, String> parametersAsMap) throws SchedulerException;

    XSKJobDefinition updateJob(String name, String group, String description, String module, String action, String cronExpression, Map<String, String> parametersAsMap) throws SchedulerException;

    XSKJobDefinition getJob(String name) throws SchedulerException;

    void removeJob(String name) throws SchedulerException;

    List<XSKJobDefinition> getJobs() throws SchedulerException;

    boolean existsJob(String name) throws SchedulerException;

    XSKJobArtifact parseJob(String json);

    XSKJobArtifact parseJob(byte[] content);
}
