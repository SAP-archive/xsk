package com.sap.xsk.xscjob.ds.synchronizer;

import org.eclipse.dirigible.commons.api.module.StaticInjector;
import org.eclipse.dirigible.core.scheduler.api.AbstractSynchronizerJob;
import org.eclipse.dirigible.core.scheduler.api.ISynchronizer;

public class XSKJobSynchronizerJob extends AbstractSynchronizerJob {

    private XSKJobSynchronizer xscJobSynchronizer = StaticInjector.getInjector().getInstance(XSKJobSynchronizer.class);

    /*
     * (non-Javadoc)
     * @see org.eclipse.dirigible.core.scheduler.api.AbstractSynchronizerJob#getSynchronizer()
     */
    @Override
    public ISynchronizer getSynchronizer() {
        return xscJobSynchronizer;
    }
}
