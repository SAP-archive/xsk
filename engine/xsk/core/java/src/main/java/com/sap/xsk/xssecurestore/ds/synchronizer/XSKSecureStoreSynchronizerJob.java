package com.sap.xsk.xssecurestore.ds.synchronizer;

import org.eclipse.dirigible.commons.api.module.StaticInjector;
import org.eclipse.dirigible.core.scheduler.api.AbstractSynchronizerJob;
import org.eclipse.dirigible.core.scheduler.api.ISynchronizer;

public class XSKSecureStoreSynchronizerJob extends AbstractSynchronizerJob {
    private XSKSecureStoreSynchronizer xscSecureStoreSynchronizer = StaticInjector.getInjector().getInstance(XSKSecureStoreSynchronizer.class);

    @Override
    protected ISynchronizer getSynchronizer() {
        return xscSecureStoreSynchronizer;
    }
}
