/**
 * Copyright (c) 2010-2018 SAP
 */
package com.sap.xsk.hdb.ds.synchronizer;

import org.eclipse.dirigible.commons.api.module.StaticInjector;
import org.eclipse.dirigible.core.scheduler.api.AbstractSynchronizerJob;
import org.eclipse.dirigible.core.scheduler.api.ISynchronizer;

/**
 * The XSK Data Structures Synchronizer Job.
 */
public class XSKDataStructuresSynchronizerJob extends AbstractSynchronizerJob {

	private XSKDataStructuresSynchronizer dataStructureSynchronizer = StaticInjector.getInjector().getInstance(XSKDataStructuresSynchronizer.class);

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.dirigible.core.scheduler.api.AbstractSynchronizerJob#getSynchronizer()
	 */
	@Override
	public ISynchronizer getSynchronizer() {
		return dataStructureSynchronizer;
	}

}
