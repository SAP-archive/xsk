package com.sap.xsk.xsjob.ds.scheduler.handler;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.dirigible.commons.api.module.StaticInjector;
import org.eclipse.dirigible.commons.api.scripting.ScriptingException;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.sap.xsk.engine.XSKJavascriptEngineExecutor;
import com.sap.xsk.xsjob.ds.api.IXSKJobCoreService;

public class XSKJobHandler implements Job {

    private XSKJavascriptEngineExecutor xskJavascriptEngineExecutor = StaticInjector.getInjector().getInstance(XSKJavascriptEngineExecutor.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        String module = (String) context.getJobDetail().getJobDataMap().get(IXSKJobCoreService.XSK_JOB_MODULE);

        Map<String, String> parametersAsMap = (Map<String, String>) context.getJobDetail().getJobDataMap().get(IXSKJobCoreService.XSK_JOB_PARAMETERS);
        Object[] parametersArray = parametersAsMap.values().toArray();

        Map<Object, Object> executionContext = new HashMap<>();
        executionContext.put(IXSKJobCoreService.XSK_JOB_FUNCTION, context.getJobDetail().getJobDataMap().get(IXSKJobCoreService.XSK_JOB_FUNCTION));
        executionContext.put(IXSKJobCoreService.XSK_JOB_PARAMETERS, parametersArray);

        try {
            xskJavascriptEngineExecutor.executeService(module, executionContext, true);
        } catch (ScriptingException e) {
            throw new JobExecutionException(e);
        }
    }
}
