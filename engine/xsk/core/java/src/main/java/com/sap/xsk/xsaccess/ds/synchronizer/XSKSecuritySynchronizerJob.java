package com.sap.xsk.xsaccess.ds.synchronizer;

import org.eclipse.dirigible.commons.api.module.StaticInjector;
import org.eclipse.dirigible.core.scheduler.api.AbstractSynchronizerJob;
import org.eclipse.dirigible.core.scheduler.api.ISynchronizer;

public class XSKSecuritySynchronizerJob extends AbstractSynchronizerJob {
    /** The extensions synchronizer. */
    private XSKSecuritySynchronizer extensionsSynchronizer = StaticInjector.getInjector().getInstance(XSKSecuritySynchronizer.class);

    /*
     * (non-Javadoc)
     * @see org.eclipse.dirigible.core.scheduler.api.AbstractSynchronizerJob#getSynchronizer()
     */
    @Override
    public ISynchronizer getSynchronizer() {
        return extensionsSynchronizer;
    }
}
