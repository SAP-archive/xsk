/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2022 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.resources.neo.sdk.synchronizer;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.eclipse.dirigible.commons.api.scripting.ScriptingException;
import org.eclipse.dirigible.commons.config.StaticObjects;
import org.eclipse.dirigible.commons.health.HealthStatus;
import org.eclipse.dirigible.commons.health.HealthStatus.Jobs.JobStatus;
import org.eclipse.dirigible.core.scheduler.api.AbstractSynchronizer;
import org.eclipse.dirigible.core.scheduler.api.SynchronizationException;
import org.eclipse.dirigible.engine.js.api.IJavascriptEngineExecutor;
import org.eclipse.dirigible.repository.api.IResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XSKResourcesNeoSdkSynchronizer extends AbstractSynchronizer {

	private static final Logger logger = LoggerFactory.getLogger(XSKResourcesNeoSdkSynchronizer.class);

	private IJavascriptEngineExecutor engine  = (IJavascriptEngineExecutor) StaticObjects.get(StaticObjects.JAVASCRIPT_ENGINE);

	@Override
	public void synchronize() {
		try {
			ExecutorService executor = Executors.newSingleThreadExecutor();
			executor.submit(() -> {
				HealthStatus.getInstance().getJobs().setStatus(XSKResourcesNeoSdkSynchronizerJobDefinitionProvider.RESOURCES_NEO_SDK_SYNCHRONIZER_JOB, JobStatus.Succeeded);
				String code = new StringBuilder()
						.append("var synchronizer = require(\"neo-sdk-synchronizer/synchronizer\");\n")
						.append("synchronizer.synchronize();").toString();
				try {
					engine.executeServiceCode(code, new HashMap<Object, Object>());
				} catch (ScriptingException e) {
					logger.error(e.getMessage(), e);
				}
			});
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
		}

	}

	@Override
	protected void synchronizeResource(IResource resource) throws SynchronizationException {
		// Do nothing
	}

}