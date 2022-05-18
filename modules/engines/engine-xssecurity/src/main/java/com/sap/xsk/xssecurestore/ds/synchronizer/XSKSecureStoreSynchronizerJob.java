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
package com.sap.xsk.xssecurestore.ds.synchronizer;

import org.eclipse.dirigible.core.scheduler.api.AbstractSynchronizerJob;
import org.eclipse.dirigible.core.scheduler.api.ISynchronizer;

public class XSKSecureStoreSynchronizerJob extends AbstractSynchronizerJob {

	private XSKSecureStoreSynchronizer xskSecureStoreSynchronizer = new XSKSecureStoreSynchronizer();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.dirigible.core.scheduler.api.AbstractSynchronizerJob#
	 * getSynchronizer()
	 */
	@Override
	public ISynchronizer getSynchronizer() {
		return xskSecureStoreSynchronizer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.dirigible.core.scheduler.api.AbstractSynchronizerJob#getName()
	 */
	@Override
	public String getName() {
		return XSKSecureStoreSynchronizerJobDefinitionProvider.XSK_SECURE_STORE_SYNCHRONIZER_JOB;
	}
}
