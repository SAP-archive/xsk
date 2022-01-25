/*
 * Copyright (c) 2022 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2022 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.xsjob.ds.service;

import com.sap.xsk.utils.XSKUtils;
import com.sap.xsk.xsjob.ds.api.IXSKJobCoreService;
import com.sap.xsk.xsjob.ds.model.XSKJobArtifact;
import com.sap.xsk.xsjob.ds.model.XSKJobDefinition;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.eclipse.dirigible.api.v3.security.UserFacade;
import org.eclipse.dirigible.commons.api.helpers.GsonHelper;
import org.eclipse.dirigible.commons.config.StaticObjects;
import org.eclipse.dirigible.core.scheduler.api.SchedulerException;
import org.eclipse.dirigible.database.persistence.PersistenceManager;

public class XSKJobCoreService implements IXSKJobCoreService {

  private DataSource dataSource = (DataSource) StaticObjects.get(StaticObjects.SYSTEM_DATASOURCE);

  private PersistenceManager<XSKJobDefinition> xskJobPersistenceManager = new PersistenceManager<XSKJobDefinition>();

  public XSKJobCoreService() {
  }

  @Override
  public XSKJobDefinition
  createJob(String name, String group, String description, String module, String action, String cronExpression,
      Map<String, String> parametersAsMap) throws SchedulerException {

    try {
      Connection connection = null;
      try {
        XSKJobDefinition xskJobDefinition = new XSKJobDefinition();
        xskJobDefinition.setName(name);
        xskJobDefinition.setGroup(XSK_DEFINED_GROUP);
        xskJobDefinition.setDescription(description);
        xskJobDefinition.setModule(module);
        xskJobDefinition.setFunction(action);
        xskJobDefinition.setCronExpression(cronExpression);
        xskJobDefinition.setParameters(XSKUtils.objectToByteArray(parametersAsMap));
        xskJobDefinition.setCreatedBy(UserFacade.getName());
        xskJobDefinition.setCreatedAt(new Timestamp(new java.util.Date().getTime()));

        connection = dataSource.getConnection();
        xskJobPersistenceManager.insert(connection, xskJobDefinition);
        return xskJobDefinition;
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
  public XSKJobDefinition updateJob(String name, String group, String description, String module, String action, String cronExpression,
      Timestamp startAt, Timestamp endAt,
      Map<String, String> parametersAsMap) throws SchedulerException {
    try {
      Connection connection = null;
      try {
        connection = dataSource.getConnection();
        XSKJobDefinition xskJobDefinition = getJob(name);
        xskJobDefinition.setGroup(group);
        xskJobDefinition.setDescription(description);
        xskJobDefinition.setModule(module);
        xskJobDefinition.setFunction(action);
        xskJobDefinition.setCronExpression(cronExpression);
        xskJobDefinition.setStartAt(startAt);
        xskJobDefinition.setEndAt(endAt);
        xskJobDefinition.setParameters(XSKUtils.objectToByteArray(parametersAsMap));
        xskJobPersistenceManager.update(connection, xskJobDefinition);

        return xskJobDefinition;
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
        XSKJobDefinition xskJobDefinition = xskJobPersistenceManager.find(connection, XSKJobDefinition.class, name);
        if (xskJobDefinition != null) {
          Map<String, String> parametersMap = XSKUtils.byteArrayToObject(xskJobDefinition.getParameters());
          xskJobDefinition.setParametersAsMap(parametersMap);
        }

        return xskJobDefinition;
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
        xskJobPersistenceManager.delete(connection, XSKJobDefinition.class, name);
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
        List<XSKJobDefinition> foundJobs = xskJobPersistenceManager.findAll(connection, XSKJobDefinition.class);
        for (XSKJobDefinition xskJobDefinition : foundJobs) {

          Map<String, String> parametersMap = XSKUtils.byteArrayToObject(xskJobDefinition.getParameters());
          xskJobDefinition.setParametersAsMap(parametersMap);
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
    XSKJobArtifact jobDefinition = GsonHelper.GSON
        .fromJson(new InputStreamReader(new ByteArrayInputStream(content), StandardCharsets.UTF_8),
            XSKJobArtifact.class);

    return jobDefinition;
  }
}
