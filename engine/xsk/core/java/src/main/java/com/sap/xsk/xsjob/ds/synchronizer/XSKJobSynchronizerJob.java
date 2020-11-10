package com.sap.xsk.xsjob.ds.synchronizer;

import org.eclipse.dirigible.commons.api.module.StaticInjector;
import org.eclipse.dirigible.core.scheduler.api.AbstractSynchronizerJob;
import org.eclipse.dirigible.core.scheduler.api.ISynchronizer;

public class XSKJobSynchronizerJob extends AbstractSynchronizerJob {

    private XSKJobSynchronizer xskJobSynchronizer = StaticInjector.getInjector().getInstance(XSKJobSynchronizer.class);

    /*
     * (non-Javadoc)
     * @see org.eclipse.dirigible.core.scheduler.api.AbstractSynchronizerJob#getSynchronizer()
     */
    @Override
    public ISynchronizer getSynchronizer() {
        return xskJobSynchronizer;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.dirigible.core.scheduler.api.AbstractSynchronizerJob#getName()
     */
	@Override
	public String getName() {
		return XSKJobSynchronizerJobDefinitionProvider.XSK_JOB_SYNCHRONIZER_JOB;
	}
}
