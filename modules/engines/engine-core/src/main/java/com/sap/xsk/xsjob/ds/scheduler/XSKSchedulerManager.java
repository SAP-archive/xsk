/*
 * Copyright (c) 2019-2020 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2019-2020 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.xsjob.ds.scheduler;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.Set;

import org.eclipse.dirigible.core.scheduler.api.SchedulerException;
import org.eclipse.dirigible.core.scheduler.manager.SchedulerManager;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.ObjectAlreadyExistsException;
import org.quartz.Scheduler;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.xsk.xsjob.ds.api.IXSKJobCoreService;
import com.sap.xsk.xsjob.ds.model.XSKJobDefinition;
import com.sap.xsk.xsjob.ds.scheduler.handler.XSKJobHandler;

public class XSKSchedulerManager {

    private static final Logger logger = LoggerFactory.getLogger(SchedulerManager.class);

    private static Scheduler scheduler = SchedulerManager.getScheduler();

    public static void scheduleJob(XSKJobDefinition jobDefinition) throws SchedulerException {
        try {
            JobKey jobKey = new JobKey(jobDefinition.getName(), jobDefinition.getGroup());
            TriggerKey triggerKey = new TriggerKey(jobDefinition.getName(), jobDefinition.getGroup());
            if (!scheduler.checkExists(jobKey) && (!scheduler.checkExists(triggerKey))) {
                JobDetail job;
                if (IXSKJobCoreService.XSK_DEFINED_GROUP.equals(jobDefinition.getGroup())) {
                    // user defined jobs
                    job = newJob(XSKJobHandler.class).withIdentity(jobKey).withDescription(jobDefinition.getDescription()).build();
                    job.getJobDataMap().put(IXSKJobCoreService.XSK_JOB_PARAMETERS, jobDefinition.getParametersAsMap());
                    job.getJobDataMap().put(IXSKJobCoreService.XSK_JOB_FUNCTION, jobDefinition.getFunction());
                    job.getJobDataMap().put(IXSKJobCoreService.XSK_JOB_MODULE, jobDefinition.getModule());
                } else {
                    return;
                }

                CronTrigger trigger = newTrigger().withIdentity(triggerKey).withSchedule(cronSchedule(jobDefinition.getCronExpression())).build();

                scheduler.scheduleJob(job, trigger);

                logger.info("Scheduled Job: [{}] of group: [{}] at: [{}]", jobDefinition.getName(), jobDefinition.getGroup(),
                        jobDefinition.getCronExpression());
            }
        } catch (ObjectAlreadyExistsException e) {
            logger.warn(e.getMessage());
        }  catch (org.quartz.SchedulerException e) {
            throw new SchedulerException(e);
        }
    }

    public static void unscheduleJob(String name, String group) throws SchedulerException {
        if (!IXSKJobCoreService.XSK_DEFINED_GROUP.equals(group)) {
            return;
        }
        try {
            JobKey jobKey = new JobKey(name, group);
            TriggerKey triggerKey = new TriggerKey(name, group);
            if (scheduler.checkExists(triggerKey)) {
                scheduler.unscheduleJob(triggerKey);
                scheduler.deleteJob(jobKey);
                logger.info("Unscheduled Job: [{}] of group: [{}]", name, group);
            }
        } catch (ObjectAlreadyExistsException e) {
            logger.warn(e.getMessage());
        } catch (org.quartz.SchedulerException e) {
            throw new SchedulerException(e);
        }
    }

    public static Set<TriggerKey> listJobs() throws SchedulerException {
        try {
            Set<TriggerKey> triggerKeys = scheduler.getTriggerKeys(GroupMatcher.anyTriggerGroup());
            return triggerKeys;
        } catch (org.quartz.SchedulerException e) {
            throw new SchedulerException(e);
        }
    }

    public static boolean existsJob(String name) throws SchedulerException {
        Set<TriggerKey> triggerKeys = listJobs();
        for (TriggerKey triggerKey : triggerKeys) {
            if (triggerKey.getName().equals(name) && IXSKJobCoreService.XSK_DEFINED_GROUP.equals(triggerKey.getGroup())) {
                return true;
            }
        }
        return false;
    }
}
