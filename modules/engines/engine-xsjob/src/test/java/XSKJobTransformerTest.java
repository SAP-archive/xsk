/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2021 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
import com.sap.xsk.utils.XSKUtils;
import com.sap.xsk.xsjob.ds.facade.XSKJobFacade;
import com.sap.xsk.xsjob.ds.model.XSKJobArtifact;
import com.sap.xsk.xsjob.ds.model.XSKJobDefinition;
import com.sap.xsk.xsjob.ds.service.XSKJobCoreService;
import com.sap.xsk.xsjob.ds.synchronizer.XSKJobSynchronizer;
import com.sap.xsk.xsjob.ds.transformer.XSKCronToQuartzCronTransformer;
import com.sap.xsk.xsjob.ds.transformer.XSKJobToXSKJobDefinitionTransformer;
import org.apache.commons.io.IOUtils;
import org.eclipse.dirigible.api.v3.security.UserFacade;
import org.eclipse.dirigible.commons.config.StaticObjects;
import org.eclipse.dirigible.core.test.AbstractDirigibleTest;
import org.eclipse.dirigible.database.persistence.PersistenceManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.eclipse.dirigible.database.api.DatabaseModule;

import javax.sql.DataSource;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class XSKJobTransformerTest extends AbstractDirigibleTest {

  @InjectMocks
  private XSKJobToXSKJobDefinitionTransformer xskJobToXSKJobDefinitionTransformer= new XSKJobToXSKJobDefinitionTransformer();
  @Mock
  private XSKCronToQuartzCronTransformer xskCronToQuartzCronTransformer;
  @Before
  public void openMocks() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void executeTransformSuccessfully() throws Exception {
    XSKJobCoreService xskJobCoreService = new XSKJobCoreService();
    String xsjobSample = org.apache.commons.io.IOUtils
        .toString(XSKJobTransformerTest.class.getResourceAsStream("/TestXSKJobTransformSuccess.xsjob"), StandardCharsets.UTF_8);
    XSKJobArtifact xskJobArtifact = xskJobCoreService.parseJob(xsjobSample);
    String cronExpression = xskCronToQuartzCronTransformer.transform(xskJobArtifact.getSchedules().get(0).getXscron());
    Map<String,String> parametersAsMap = xskJobArtifact.getSchedules().get(0).getParameter();
    ArrayList<XSKJobDefinition> jobDefinitions = xskJobToXSKJobDefinitionTransformer.transform(xskJobArtifact);

    assertEquals("XSJOB:bugXsjob.xsjs::logFunc-0", jobDefinitions.get(0).getName());
    assertEquals("XSJOB/bugXsjob.xsjs", jobDefinitions.get(0).getModule());
    assertEquals("logFunc", jobDefinitions.get(0).getFunction());
    assertEquals("My Job configuration My Schedule configuration for execution every second", jobDefinitions.get(0).getDescription());
    assertEquals(cronExpression, jobDefinitions.get(0).getCronExpression());
    assertEquals(parametersAsMap, jobDefinitions.get(0).getParametersAsMap());
    assertEquals(parametersAsMap.size(), 1);
    assertEquals(jobDefinitions.size(), 1);
  }

  @Test(expected = IllegalStateException.class)
  public void executeTransformFailed() throws Exception {
    XSKJobCoreService xskJobCoreService = new XSKJobCoreService();
    String xsjobSample = org.apache.commons.io.IOUtils
        .toString(XSKJobTransformerTest.class.getResourceAsStream("/TestXSKJobTransformFailure.xsjob"), StandardCharsets.UTF_8);
    XSKJobArtifact xskJobArtifact = xskJobCoreService.parseJob(xsjobSample);
    xskJobToXSKJobDefinitionTransformer.transform(xskJobArtifact);
  }

  @Test
  public void executeTransformWithEmptySchedule() throws Exception {
    XSKJobCoreService xskJobCoreService = new XSKJobCoreService();
    String xsjobSample = org.apache.commons.io.IOUtils
        .toString(XSKJobTransformerTest.class.getResourceAsStream("/TestXSKJobTransformWithEmptySchedule.xsjob"), StandardCharsets.UTF_8);
    XSKJobArtifact xskJobArtifact = xskJobCoreService.parseJob(xsjobSample);
    ArrayList<XSKJobDefinition> jobDefinitions = xskJobToXSKJobDefinitionTransformer.transform(xskJobArtifact);
    assertEquals(jobDefinitions.size(), 0);
  }

  @Test
  public void test() throws Exception {

//    DatabaseModule db = new DatabaseModule();
//    db.configure();

    String jobPath = "/TestXSKJobTransformSuccess.xsjob";
    XSKJobCoreService s = new XSKJobCoreService();
    XSKJobFacade.newJob(jobPath);

    DataSource dataSource = (DataSource) StaticObjects.get(StaticObjects.DATASOURCE);
    PersistenceManager<XSKJobDefinition> xskJobPersistenceManager = new PersistenceManager<XSKJobDefinition>();

    xskJobPersistenceManager.tableDrop(dataSource.getConnection(), XSKJobDefinition.class);

//    XSKJobDefinition xskJobDefinition = new XSKJobDefinition();
//    xskJobDefinition.setName("nameeee");
//    xskJobDefinition.setGroup("group");
//    xskJobDefinition.setDescription("description");
//    xskJobDefinition.setModule("module");
//    xskJobDefinition.setFunction("action");
//    xskJobDefinition.setCronExpression("cronExpression");
//    xskJobDefinition.setParameters(XSKUtils.objectToByteArray(new HashMap<String, String>()));
//    xskJobDefinition.setCreatedBy(UserFacade.getName());
//    xskJobDefinition.setCreatedAt(new Timestamp(new java.util.Date().getTime()));
//    xskJobPersistenceManager.insert(dataSource.getConnection(), xskJobDefinition);

//    System.out.println(xskJobPersistenceManager.findAll(dataSource.getConnection(), XSKJobDefinition.class).get(0).getName());


    XSKJobCoreService jobService = new XSKJobCoreService();
    InputStream in = XSKJobSynchronizer.class.getResourceAsStream(jobPath);
    try {
      String json = IOUtils.toString(in, StandardCharsets.UTF_8);
      XSKJobArtifact xskJobArtifact = jobService.parseJob(json);
      ArrayList<XSKJobDefinition> xskJobDefinitions = xskJobToXSKJobDefinitionTransformer.transform(xskJobArtifact);
      for (XSKJobDefinition jobDefinition:xskJobDefinitions) {
        jobDefinition.setName(jobPath);
      }

      System.out.println(xskJobDefinitions.get(0).getName());
    } finally {
      if (in != null) {
        in.close();
      }
    }

//    XSKJobDefinition jobDefinition = jobService.getJob("nameeee");
//    System.out.println(jobDefinition.getName());

//    System.out.println(xskJobPersistenceManager.find(dataSource.getConnection(), XSKJobDefinition.class, "name"));

//    XSKJobFacade.activate("/TestXSKJobTransformSuccess.xsjob");
//    XSKJobFacade.deactivate("/TestXSKJobTransformSuccess.xsjob");
  }
}
