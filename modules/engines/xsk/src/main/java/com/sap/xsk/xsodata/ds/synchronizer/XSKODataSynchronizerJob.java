/**
 * Copyright (c) 2010-2018 SAP
 */
package com.sap.xsk.xsodata.ds.synchronizer;

import org.eclipse.dirigible.commons.api.module.StaticInjector;
import org.eclipse.dirigible.core.scheduler.api.AbstractSynchronizerJob;
import org.eclipse.dirigible.core.scheduler.api.ISynchronizer;

/**
 * The XSK Data Structures Synchronizer Job.
 */
public class XSKODataSynchronizerJob extends AbstractSynchronizerJob {

	private XSKODataSynchronizer odataSynchronizer = StaticInjector.getInjector().getInstance(XSKODataSynchronizer.class);

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.dirigible.core.scheduler.api.AbstractSynchronizerJob#getSynchronizer()
	 */
	@Override
	public ISynchronizer getSynchronizer() {
		return odataSynchronizer;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.dirigible.core.scheduler.api.AbstractSynchronizerJob#getName()
	 */
	@Override
	public String getName() {
		return XSKODataSynchronizerJobDefinitionProvider.XSK_ODATA_SYNCHRONIZER_JOB;
	}

}
