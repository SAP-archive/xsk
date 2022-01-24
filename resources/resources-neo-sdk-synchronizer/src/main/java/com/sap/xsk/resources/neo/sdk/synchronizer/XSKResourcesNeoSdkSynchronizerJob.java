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

import java.util.concurrent.TimeUnit;

import org.eclipse.dirigible.core.scheduler.api.AbstractSynchronizerJob;
import org.eclipse.dirigible.core.scheduler.api.ISynchronizer;

/**
 * The XSK Resources Neo Sdk Synchronizer Job.
 */
public class XSKResourcesNeoSdkSynchronizerJob extends AbstractSynchronizerJob {

	private static final int TIMEOUT_TIME = 10;

	/** The extensions synchronizer. */
	private XSKResourcesNeoSdkSynchronizer synchronizer = new XSKResourcesNeoSdkSynchronizer();

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.dirigible.core.scheduler.api.AbstractSynchronizerJob#getSynchronizer()
	 */
	@Override
	public ISynchronizer getSynchronizer() {
		return synchronizer;
	}

	@Override
	public String getName() {
		return XSKResourcesNeoSdkSynchronizerJobDefinitionProvider.RESOURCES_NEO_SDK_SYNCHRONIZER_JOB;
	}

	@Override
	protected int getTimeout() {
		return TIMEOUT_TIME;
	}
	
	@Override
	protected TimeUnit getTimeoutUnit() {
		return TimeUnit.MINUTES;
	}
}