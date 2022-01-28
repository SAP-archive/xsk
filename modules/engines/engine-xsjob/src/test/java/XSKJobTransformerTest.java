
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
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.eclipse.dirigible.api.v3.problems.ProblemsFacade;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import com.sap.xsk.xsjob.ds.model.XSKJobArtifact;
import com.sap.xsk.xsjob.ds.model.XSKJobDefinition;
import com.sap.xsk.xsjob.ds.service.XSKJobCoreService;
import com.sap.xsk.xsjob.ds.transformer.XSKCronToQuartzCronTransformer;
import com.sap.xsk.xsjob.ds.transformer.XSKJobToXSKJobDefinitionTransformer;

@RunWith(MockitoJUnitRunner.class)
public class XSKJobTransformerTest {

	@InjectMocks
	private XSKJobToXSKJobDefinitionTransformer xskJobToXSKJobDefinitionTransformer = new XSKJobToXSKJobDefinitionTransformer();
	@Mock
	private XSKCronToQuartzCronTransformer xskCronToQuartzCronTransformer;

	@Before
	public void openMocks() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void executeTransformSuccessfully() throws Exception {
		XSKJobCoreService xskJobCoreService = new XSKJobCoreService();
		String xsjobSample = IOUtils.toString(XSKJobTransformerTest.class.getResourceAsStream("/TestXSKJobTransformSuccess.xsjob"), StandardCharsets.UTF_8);
		XSKJobArtifact xskJobArtifact = xskJobCoreService.parseJob(xsjobSample);
		String cronExpression = xskCronToQuartzCronTransformer.transform(xskJobArtifact.getSchedules().get(0).getXscron());
		Map<String, String> parametersAsMap = xskJobArtifact.getSchedules().get(0).getParameter();
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
		try (MockedStatic<ProblemsFacade> problemsFacade = Mockito.mockStatic(ProblemsFacade.class)) {
			problemsFacade.when(() -> ProblemsFacade.save(any(), any(), any(), any(), any(), any(), any(), any(), any(), any())).thenAnswer((Answer<Void>) invocation -> null);
			XSKJobCoreService xskJobCoreService = new XSKJobCoreService();
			String xsjobSample = IOUtils.toString(XSKJobTransformerTest.class.getResourceAsStream("/TestXSKJobTransformFailure.xsjob"), StandardCharsets.UTF_8);
			XSKJobArtifact xskJobArtifact = xskJobCoreService.parseJob(xsjobSample);
			xskJobToXSKJobDefinitionTransformer.transform(xskJobArtifact);
		}
	}

	@Test
	public void executeTransformWithEmptySchedule() throws Exception {
		XSKJobCoreService xskJobCoreService = new XSKJobCoreService();
		String xsjobSample = IOUtils.toString(XSKJobTransformerTest.class.getResourceAsStream("/TestXSKJobTransformWithEmptySchedule.xsjob"), StandardCharsets.UTF_8);
		XSKJobArtifact xskJobArtifact = xskJobCoreService.parseJob(xsjobSample);
		ArrayList<XSKJobDefinition> jobDefinitions = xskJobToXSKJobDefinitionTransformer.transform(xskJobArtifact);
		assertEquals(jobDefinitions.size(), 0);
	}

}
