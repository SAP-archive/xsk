/**
 * Copyright (c) 2010-2018 SAP
 */
package com.sap.xsk.xsodata.ds.synchronizer;

import org.eclipse.dirigible.commons.api.content.AbstractClasspathContentHandler;
import org.eclipse.dirigible.commons.api.module.StaticInjector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.xsk.xsodata.ds.api.IXSKODataModel;

/**
 * The XSK OData Classpath Content Handler.
 */
public class XSKODataClasspathContentHandler extends AbstractClasspathContentHandler {

	private static final Logger logger = LoggerFactory.getLogger(XSKODataClasspathContentHandler.class);

	private XSKODataSynchronizer odataSynchronizer = StaticInjector.getInjector().getInstance(XSKODataSynchronizer.class);

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.dirigible.commons.api.content.AbstractClasspathContentHandler#isValid(java.lang.String)
	 */
	@Override
	protected boolean isValid(String path) {

		try {
			if (path.endsWith(IXSKODataModel.FILE_EXTENSION_XSODATA)) {
				odataSynchronizer.registerPredeliveredOData(path);
				return true;
			}
		} catch (Exception e) {
			logger.error("Predelivered Table or View is not valid", e);
		}

		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.dirigible.commons.api.content.AbstractClasspathContentHandler#getLogger()
	 */
	@Override
	protected Logger getLogger() {
		return logger;
	}

}
