package com.sap.xsk.hdbti.synchronizer;

import org.eclipse.dirigible.commons.api.module.StaticInjector;
import org.eclipse.dirigible.core.scheduler.api.AbstractSynchronizerJob;
import org.eclipse.dirigible.core.scheduler.api.ISynchronizer;

public class XSKTableImportSynchronizerJob extends AbstractSynchronizerJob {

    private XSKTableImportSynchronizer xskTableImportSynchronizer = StaticInjector.getInjector().getInstance(XSKTableImportSynchronizer.class);

    /*
     * (non-Javadoc)
     * @see org.eclipse.dirigible.core.scheduler.api.AbstractSynchronizerJob#getSynchronizer()
     */
    @Override
    public ISynchronizer getSynchronizer() {
        return xskTableImportSynchronizer;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.dirigible.core.scheduler.api.AbstractSynchronizerJob#getName()
     */
	@Override
	public String getName() {
		return XSKTableImportSynchronizerJobDefinitionProvider.XSK_TABLE_IMPORT_SYNCHRONIZER_JOB;
	}
}
