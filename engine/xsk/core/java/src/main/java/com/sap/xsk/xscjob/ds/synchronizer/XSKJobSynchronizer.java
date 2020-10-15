package com.sap.xsk.xscjob.ds.synchronizer;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.apache.commons.io.IOUtils;
import org.eclipse.dirigible.commons.api.module.StaticInjector;
import org.eclipse.dirigible.core.scheduler.api.AbstractSynchronizer;
import org.eclipse.dirigible.core.scheduler.api.SchedulerException;
import org.eclipse.dirigible.core.scheduler.api.SynchronizationException;
import org.eclipse.dirigible.repository.api.IResource;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.xsk.xscjob.ds.api.IXSKJobCoreService;
import com.sap.xsk.xscjob.ds.api.IXSKJobModel;
import com.sap.xsk.xscjob.ds.model.XSKJobArtifact;
import com.sap.xsk.xscjob.ds.model.XSKJobDefinition;
import com.sap.xsk.xscjob.ds.scheduler.XSKSchedulerManager;
import com.sap.xsk.xscjob.ds.service.XSKJobCoreService;
import com.sap.xsk.xscjob.ds.tronsformer.XSKJobToXSKJobDefinitionTransformer;

@Singleton
public class XSKJobSynchronizer extends AbstractSynchronizer {

    private static final Logger logger = LoggerFactory.getLogger(XSKJobSynchronizer.class);

    private static final Map<String, XSKJobDefinition> JOBS_PREDELIVERED = Collections.synchronizedMap(new HashMap<String, XSKJobDefinition>());

    private static final List<String> JOBS_SYNCHRONIZED = Collections.synchronizedList(new ArrayList<String>());

    @Inject
    private XSKJobCoreService schedulerCoreService;

    @Inject
    private XSKJobToXSKJobDefinitionTransformer xscJobToXSKJobDefinitionTransformer;

    // @Inject
    // private SchedulerManager schedulerManager;

    /**
     * Force synchronization.
     */
    public static final void forceSynchronization() {
        XSKJobSynchronizer extensionsSynchronizer = StaticInjector.getInjector().getInstance(XSKJobSynchronizer.class);
        extensionsSynchronizer.synchronize();
    }

    /**
     * Register predelivered job.
     *
     * @param jobPath
     *            the job path
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    public void registerPredeliveredJob(String jobPath) throws IOException, ParseException {
        InputStream in = XSKJobSynchronizer.class.getResourceAsStream(jobPath);
        try {
            String json = IOUtils.toString(in, StandardCharsets.UTF_8);
            XSKJobArtifact xscJobArtifact = schedulerCoreService.parseJob(json);
            XSKJobDefinition jobDefinition = xscJobToXSKJobDefinitionTransformer.transform(xscJobArtifact);
            jobDefinition.setName(jobPath);
            JOBS_PREDELIVERED.put(jobPath, jobDefinition);
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.dirigible.core.scheduler.api.ISynchronizer#synchronize()
     */
    @Override
    public void synchronize() {
        synchronized (XSKJobSynchronizer.class) {
            logger.trace("Synchronizing Jobs...");
            try {
                clearCache();
                synchronizePredelivered();
                synchronizeRegistry();
                startJobs();
                cleanup();
                clearCache();
            } catch (Exception e) {
                logger.error("Synchronizing process for Jobs failed.", e);
            }
            logger.trace("Done synchronizing Jobs.");
        }
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.dirigible.core.scheduler.api.AbstractSynchronizer#synchronizeRegistry()
     */
    @Override
    protected void synchronizeRegistry() throws SynchronizationException {
        logger.trace("Synchronizing Jobs from Registry...");

        super.synchronizeRegistry();

        logger.trace("Done synchronizing Jobs from Registry.");
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.dirigible.core.scheduler.api.AbstractSynchronizer#synchronizeResource(com.sap.xsk.hdb.ds.parser.XSKDataStructureParser
     * repository.api.IResource)
     */
    @Override
    protected void synchronizeResource(IResource resource) throws SynchronizationException {
        String resourceName = resource.getName();
        if (resourceName.endsWith(IXSKJobModel.XSK_JOB_FILE_EXTENSION)) {
            XSKJobArtifact xscJobArtifact = schedulerCoreService.parseJob(resource.getContent());
            try {
                XSKJobDefinition xscJobDefinition = xscJobToXSKJobDefinitionTransformer.transform(xscJobArtifact);
                xscJobDefinition.setGroup(IXSKJobCoreService.XSK_DEFINED_GROUP);
                synchronizeJob(xscJobDefinition);
            } catch (ParseException e) {
                throw new SynchronizationException();
            }
        }

    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.dirigible.core.scheduler.api.AbstractSynchronizer#cleanup()
     */
    @Override
    protected void cleanup() throws SynchronizationException {
        logger.trace("Cleaning up Jobs...");

        try {
            List<XSKJobDefinition> jobDefinitions = schedulerCoreService.getJobs();
            for (XSKJobDefinition jobDefinition : jobDefinitions) {
                if (!JOBS_SYNCHRONIZED.contains(jobDefinition.getName())) {
                    schedulerCoreService.removeJob(jobDefinition.getName());
                    logger.warn("Cleaned up Job [{}] from group: {}", jobDefinition.getName(), jobDefinition.getGroup());
                }
            }
        } catch (SchedulerException e) {
            throw new SynchronizationException(e);
        }

        logger.trace("Done cleaning up Jobs.");
    }

    private void startJobs() throws SchedulerException {
        logger.trace("Start Jobs...");

        for (String jobName : JOBS_SYNCHRONIZED) {
            if (!XSKSchedulerManager.existsJob(jobName)) {
                try {
                    XSKJobDefinition jobDefinition = schedulerCoreService.getJob(jobName);
                    XSKSchedulerManager.scheduleJob(jobDefinition);
                } catch (SchedulerException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }

        Set<TriggerKey> runningJobs = XSKSchedulerManager.listJobs();
        for (TriggerKey jobKey : runningJobs) {
            try {
                if (!JOBS_SYNCHRONIZED.contains(jobKey.getName())) {
                    XSKSchedulerManager.unscheduleJob(jobKey.getName(), jobKey.getGroup());
                }
            } catch (SchedulerException e) {
                logger.error(e.getMessage(), e);
            }
        }

        logger.trace("Running Jobs: " + runningJobs.size());
        logger.trace("Done starting Jobs.");
    }

    private void clearCache() {
        JOBS_SYNCHRONIZED.clear();
    }

    private void synchronizePredelivered() throws SynchronizationException {
        logger.trace("Synchronizing predelivered Jobs...");
        // Jobs
        for (XSKJobDefinition jobDefinition : JOBS_PREDELIVERED.values()) {
            synchronizeJob(jobDefinition);
        }
        logger.trace("Done synchronizing predelivered Jobs.");
    }

    private void synchronizeJob(XSKJobDefinition xscJob) throws SynchronizationException {
        try {
            if (!schedulerCoreService.existsJob(xscJob.getName())) {
                schedulerCoreService.createJob(xscJob.getName(), xscJob.getGroup(), xscJob.getDescription(),
                        xscJob.getModule(), xscJob.getFunction(), xscJob.getCronExpression(), xscJob.getParametersAsMap());
                logger.info("Synchronized a new Job [{}] from group: {}", xscJob.getName(), xscJob.getGroup());
            } else {
                XSKJobDefinition existing = schedulerCoreService.getJob(xscJob.getName());
                if (!xscJob.equals(existing)) {
                    schedulerCoreService.updateJob(xscJob.getName(), xscJob.getGroup(), xscJob.getDescription(),
                            xscJob.getModule(), xscJob.getFunction(), xscJob.getCronExpression(), xscJob.getParametersAsMap());
                    logger.info("Synchronized a modified Job [{}] from group: {}", xscJob.getName(), xscJob.getGroup());
                }
            }
            JOBS_SYNCHRONIZED.add(xscJob.getName());
        } catch (SchedulerException e) {
            throw new SynchronizationException(e);
        }
    }
}
