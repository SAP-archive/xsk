package com.sap.xsk.xssecurestore.ds.facade;

import org.eclipse.dirigible.api.v3.security.UserFacade;
import org.eclipse.dirigible.commons.api.module.StaticInjector;
import org.eclipse.dirigible.commons.api.scripting.IScriptingFacade;

import com.sap.xsk.xssecurestore.ds.api.IXSKSecureStoreModel;
import com.sap.xsk.xssecurestore.ds.api.XSKSecureStoreException;
import com.sap.xsk.xssecurestore.ds.model.XSKSecureStoreContent;
import com.sap.xsk.xssecurestore.ds.service.XSKSecureStoreCoreService;

public class XSKSecureStoreFacade implements IScriptingFacade {

    private static final XSKSecureStoreCoreService xscSecureStoreCoreService = StaticInjector.getInjector().getInstance(XSKSecureStoreCoreService.class);

    public XSKSecureStoreFacade() {
    }

    public static final void store(String storeId, String dataId, String value) throws XSKSecureStoreException {
        xscSecureStoreCoreService.createSecureStoreValue(storeId, IXSKSecureStoreModel.VALUE_APP_USER, dataId, value);
    }

    public static final boolean existsStore(String storeId) throws XSKSecureStoreException {
        return xscSecureStoreCoreService.existsSecureStore(storeId);
    }

    public static final String read(String storeId, String dataId) throws XSKSecureStoreException {
        XSKSecureStoreContent xscSecureStoreContent = xscSecureStoreCoreService.findSecureStoreContent(storeId, IXSKSecureStoreModel.VALUE_APP_USER, dataId);

        if (xscSecureStoreContent == null) {
            return null;
        }

        return new String(xscSecureStoreContent.getDataValue());
    }

    public static final void remove(String storeId, String dataId) throws XSKSecureStoreException {
        xscSecureStoreCoreService.deleteSecureStoreValue(storeId, IXSKSecureStoreModel.VALUE_APP_USER, dataId);
    }

    public static final String readForUser(String storeId, String dataId) throws XSKSecureStoreException {
        XSKSecureStoreContent xscSecureStoreContent = xscSecureStoreCoreService.findSecureStoreContent(storeId, UserFacade.getName(), dataId);

        if (xscSecureStoreContent == null) {
            return null;
        }

        return new String(xscSecureStoreContent.getDataValue());
    }

    public static final void storeForUser(String storeId, String dataId, String value) throws XSKSecureStoreException {
        xscSecureStoreCoreService.createSecureStoreValue(storeId, UserFacade.getName(), dataId, value);
    }

    public static final void removeForUser(String storeId, String dataId) throws XSKSecureStoreException {
        xscSecureStoreCoreService.deleteSecureStoreValue(storeId, UserFacade.getName(), dataId);
    }
}
