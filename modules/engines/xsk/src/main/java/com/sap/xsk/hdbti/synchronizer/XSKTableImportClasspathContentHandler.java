package com.sap.xsk.hdbti.synchronizer;

import java.io.IOException;

import org.eclipse.dirigible.commons.api.content.AbstractClasspathContentHandler;
import org.eclipse.dirigible.commons.api.module.StaticInjector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.xsk.hdbti.api.IXSKTableImportModel;

public class XSKTableImportClasspathContentHandler extends AbstractClasspathContentHandler {
    private static final Logger logger = LoggerFactory.getLogger(com.sap.xsk.hdbti.synchronizer.XSKTableImportClasspathContentHandler.class);

    private XSKTableImportSynchronizer tableImportSynchronizer = StaticInjector.getInjector().getInstance(XSKTableImportSynchronizer.class);

    /*
     * (non-Javadoc)
     * @see org.eclipse.dirigible.commons.api.content.AbstractClasspathContentHandler#isValid(java.lang.String)
     */
    @Override
    protected boolean isValid(String path) {
            try{
                if(path.endsWith(IXSKTableImportModel.FILE_EXTENSION_TABLE_IMPORT)){
                    tableImportSynchronizer.registerPredeliveredTableImports(path);
                    return true;
                }
            }catch(IOException e) {
                logger.error("Predelvered table import is not valid", e);
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
