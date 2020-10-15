package com.sap.xsk.xsaccess.ds.verifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.xsk.xsaccess.ds.api.IXSKAccessCoreService;
import com.sap.xsk.xsaccess.ds.api.XSKAccessException;
import com.sap.xsk.xsaccess.ds.model.access.XSKAccessDefinition;

public class XSKAccessVerifier {
    private static final Logger logger = LoggerFactory.getLogger(XSKAccessVerifier.class);

    /**
     * Checks whether the URI is secured via the *.access file or not
     *
     * @param ixscAccessCoreService
     *            the security core service
     * @param path
     *            the path
     * @param method
     *            the method
     * @return all the most specific AccessDefinition entry matching the URI if any
     * @throws XSKAccessException
     *             the access exception
     */
    public static XSKAccessDefinition getMatchingAccessDefinitions(IXSKAccessCoreService ixscAccessCoreService, String path, String method)
            throws XSKAccessException {
        XSKAccessDefinition current = null;
        for (XSKAccessDefinition xscAccessDefinition : ixscAccessCoreService.getAccessXSKDefinitions()) {
            if (path.startsWith(xscAccessDefinition.getPath())) {
                logger.debug(String.format("URI [%s] with HTTP method [%s] is secured because of definition: %s", path, method,
                        xscAccessDefinition.getPath()));
                if ((current == null) || (xscAccessDefinition.getPath().length() > current.getPath().length())) {
                    current = xscAccessDefinition;
                }
            }
        }
        if (current == null) {
            logger.trace(String.format("URI [%s] with HTTP method [%s] is NOT secured", path, method));
        }

        return current;
    }
}
