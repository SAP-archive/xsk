package com.sap.xsk.xscjob.ds.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.sql.DataSource;

import org.eclipse.dirigible.api.v3.security.UserFacade;
import org.eclipse.dirigible.commons.api.helpers.GsonHelper;
import org.eclipse.dirigible.core.scheduler.api.SchedulerException;
import org.eclipse.dirigible.database.persistence.PersistenceManager;

import com.sap.xsk.utils.XSKUtils;
import com.sap.xsk.xscjob.ds.api.IXSKJobCoreService;
import com.sap.xsk.xscjob.ds.model.XSKJobArtifact;
import com.sap.xsk.xscjob.ds.model.XSKJobDefinition;

@Singleton
public class XSKJobCoreService implements IXSKJobCoreService {
    @Inject
    private DataSource dataSource;

    @Inject
    private PersistenceManager<XSKJobDefinition> xscJobPersistenceManager;

    public XSKJobCoreService() {
    }

    @Override
    public XSKJobDefinition createJob(String name, String group, String description, String module, String action, String cronExpression, Map<String, String> parametersAsMap) throws SchedulerException {

        try {
            Connection connection = null;
            try {
                XSKJobDefinition xscJobDefinition = new XSKJobDefinition();
                xscJobDefinition.setName(name);
                xscJobDefinition.setGroup(group);
                xscJobDefinition.setDescription(description);
                xscJobDefinition.setModule(module);
                xscJobDefinition.setFunction(action);
                xscJobDefinition.setCronExpression(cronExpression);
                xscJobDefinition.setParameters(XSKUtils.objectToByteArray(parametersAsMap));
                xscJobDefinition.setCreatedBy(UserFacade.getName());
                xscJobDefinition.setCreatedAt(new Timestamp(new java.util.Date().getTime()));

                connection = dataSource.getConnection();
                xscJobPersistenceManager.insert(connection, xscJobDefinition);
                return xscJobDefinition;
            } finally {
                if (connection != null) {
                    connection.close();
                }
            }
        } catch (SQLException | IOException e) {
            throw new SchedulerException(e);
        }
    }

    @Override
    public XSKJobDefinition updateJob(String name, String group, String description, String module, String action, String cronExpression, Map<String, String> parametersAsMap) throws SchedulerException {
        try {
            Connection connection = null;
            try {
                connection = dataSource.getConnection();
                XSKJobDefinition xscJobDefinition = getJob(name);
                xscJobDefinition.setGroup(group);
                xscJobDefinition.setDescription(description);
                xscJobDefinition.setModule(module);
                xscJobDefinition.setFunction(action);
                xscJobDefinition.setCronExpression(cronExpression);
                xscJobDefinition.setParameters(XSKUtils.objectToByteArray(parametersAsMap));
                xscJobPersistenceManager.update(connection, xscJobDefinition);

                return xscJobDefinition;
            } finally {
                if (connection != null) {
                    connection.close();
                }
            }
        } catch (SQLException | IOException e) {
            throw new SchedulerException(e);
        }
    }

    @Override
    public XSKJobDefinition getJob(String name) throws SchedulerException {
        try {
            Connection connection = null;
            try {
                connection = dataSource.getConnection();
                XSKJobDefinition xscJobDefinition = xscJobPersistenceManager.find(connection, XSKJobDefinition.class, name);
                if (xscJobDefinition != null) {
                    Map<String, String> parametersMap = XSKUtils.byteArrayToObject(xscJobDefinition.getParameters());
                    xscJobDefinition.setParametersAsMap(parametersMap);
                }

                return xscJobDefinition;
            } finally {
                if (connection != null) {
                    connection.close();
                }
            }
        } catch (SQLException | IOException | ClassNotFoundException e) {
            throw new SchedulerException(e);
        }
    }

    @Override
    public void removeJob(String name) throws SchedulerException {
        try {
            Connection connection = null;
            try {
                connection = dataSource.getConnection();
                xscJobPersistenceManager.delete(connection, XSKJobDefinition.class, name);
            } finally {
                if (connection != null) {
                    connection.close();
                }
            }
        } catch (SQLException e) {
            throw new SchedulerException(e);
        }
    }

    @Override
    public List<XSKJobDefinition> getJobs() throws SchedulerException {
        try {
            Connection connection = null;
            try {
                connection = dataSource.getConnection();
                List<XSKJobDefinition> foundJobs = xscJobPersistenceManager.findAll(connection, XSKJobDefinition.class);
                for (XSKJobDefinition xscJobDefinition: foundJobs) {

                    Map<String, String> parametersMap = XSKUtils.byteArrayToObject(xscJobDefinition.getParameters());
                    xscJobDefinition.setParametersAsMap(parametersMap);
                }

                return foundJobs;
            } finally {
                if (connection != null) {
                    connection.close();
                }
            }
        } catch (SQLException | IOException | ClassNotFoundException e) {
            throw new SchedulerException(e);
        }
    }

    @Override
    public boolean existsJob(String name) throws SchedulerException {
        return getJob(name) != null;
    }

    @Override
    public XSKJobArtifact parseJob(String json) {
        XSKJobArtifact jobDefinition = GsonHelper.GSON.fromJson(json, XSKJobArtifact.class);
        return jobDefinition;
    }

    @Override
    public XSKJobArtifact parseJob(byte[] content) {
        XSKJobArtifact jobDefinition = GsonHelper.GSON.fromJson(new InputStreamReader(new ByteArrayInputStream(content), StandardCharsets.UTF_8),
                XSKJobArtifact.class);

        return jobDefinition;
    }
}
