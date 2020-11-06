/**
 * Copyright (c) 2010-2018 SAP
 */
package com.sap.xsk.hdb.ds.synchronizer;

import org.eclipse.dirigible.commons.api.content.AbstractClasspathContentHandler;
import org.eclipse.dirigible.commons.api.module.StaticInjector;
import org.eclipse.dirigible.commons.config.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.xsk.hdb.ds.api.IXSKDataStructureModel;
import com.sap.xsk.hdb.ds.api.IXSKEnvironmentVariables;

/**
 * The XSK Data Structures Classpath Content Handler.
 */
public class XSKDataStructuresClasspathContentHandler extends AbstractClasspathContentHandler {

    private static final Logger logger = LoggerFactory.getLogger(XSKDataStructuresClasspathContentHandler.class);

    private XSKDataStructuresSynchronizer dataStructuresSynchronizer = StaticInjector.getInjector().getInstance(XSKDataStructuresSynchronizer.class);

    /*
     * (non-Javadoc)
     * @see org.eclipse.dirigible.commons.api.content.AbstractClasspathContentHandler#isValid(java.lang.String)
     */
    @Override
    protected boolean isValid(String path) {

        try {
        	
        	boolean hdiSupported = Boolean.parseBoolean(Configuration.get(IXSKEnvironmentVariables.XSK_HDI_SUPPORTED, "true"));
            if (hdiSupported) {
            	if (path.endsWith(IXSKDataStructureModel.FILE_EXTENSION_HDI)) {
                    dataStructuresSynchronizer.registerPredeliveredHDBSchema(path);
                    return true;
                }
            }
            
            boolean hdiOnly = Boolean.parseBoolean(Configuration.get(IXSKEnvironmentVariables.XSK_HDI_ONLY, "false"));
            if (!hdiOnly) {
            	if (path.endsWith(IXSKDataStructureModel.FILE_EXTENSION_ENTITIES)) {
	                dataStructuresSynchronizer.registerPredeliveredEntities(path);
	                return true;
	            }
	            if (path.endsWith(IXSKDataStructureModel.FILE_EXTENSION_TABLE)) {
	                dataStructuresSynchronizer.registerPredeliveredTable(path);
	                return true;
	            }
	            if (path.endsWith(IXSKDataStructureModel.FILE_EXTENSION_VIEW)) {
	                dataStructuresSynchronizer.registerPredeliveredView(path);
	                return true;
	            }
	//            if (path.endsWith(IXSKDataStructureModel.FILE_EXTENSION_CALCULATION_VIEW)) {
	//                dataStructuresSynchronizer.registerPredeliveredCalculationView(path);
	//                return true;
	//            }
	//            if (path.endsWith(IXSKDataStructureModel.FILE_EXTENSION_HDBCALCULATION_VIEW)) {
	//                dataStructuresSynchronizer.registerPredeliveredHDBCalculationView(path);
	//                return true;
	//            }
	            if (path.endsWith(IXSKDataStructureModel.FILE_EXTENSION_HDBPROCEDURE)) {
	                dataStructuresSynchronizer.registerPredeliveredHDBProcedure(path);
	                return true;
	            }
	            if (path.endsWith(IXSKDataStructureModel.FILE_EXTENSION_HDBTABLEFUNCTION)) {
	                dataStructuresSynchronizer.registerPredeliveredHDBTableFunction(path);
	                return true;
	            }
	            if (path.endsWith(IXSKDataStructureModel.FILE_EXTENSION_HDBSCHEMA)) {
	                dataStructuresSynchronizer.registerPredeliveredHDBSchema(path);
	                return true;
	            }
            }
            
        } catch (Exception e) {
            logger.error("Predelivered Artifact is not valid", e);
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
