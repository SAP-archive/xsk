package com.sap.xsk.xssecurestore.ds.synchronizer;

import org.eclipse.dirigible.commons.api.module.StaticInjector;
import org.eclipse.dirigible.core.scheduler.api.AbstractSynchronizerJob;
import org.eclipse.dirigible.core.scheduler.api.ISynchronizer;

public class XSKSecureStoreSynchronizerJob extends AbstractSynchronizerJob {
	
    private XSKSecureStoreSynchronizer xskSecureStoreSynchronizer = StaticInjector.getInjector().getInstance(XSKSecureStoreSynchronizer.class);

    /*
     * (non-Javadoc)
     * @see org.eclipse.dirigible.core.scheduler.api.AbstractSynchronizerJob#getSynchronizer()
     */
    @Override
    protected ISynchronizer getSynchronizer() {
        return xskSecureStoreSynchronizer;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.dirigible.core.scheduler.api.AbstractSynchronizerJob#getName()
     */
	@Override
	public String getName() {
		return XSKSecureStoreSynchronizerJobDefinitionProvider.XSK_SECURE_STORE_SYNCHRONIZER_JOB;
	}
}
