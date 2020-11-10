package com.sap.xsk.xssecurestore.ds.facade;

import org.eclipse.dirigible.api.v3.security.UserFacade;
import org.eclipse.dirigible.commons.api.module.StaticInjector;
import org.eclipse.dirigible.commons.api.scripting.IScriptingFacade;

import com.sap.xsk.xssecurestore.ds.api.IXSKSecureStoreModel;
import com.sap.xsk.xssecurestore.ds.api.XSKSecureStoreException;
import com.sap.xsk.xssecurestore.ds.model.XSKSecureStoreContent;
import com.sap.xsk.xssecurestore.ds.service.XSKSecureStoreCoreService;

public class XSKSecureStoreFacade implements IScriptingFacade {

    private static final XSKSecureStoreCoreService xskSecureStoreCoreService = StaticInjector.getInjector().getInstance(XSKSecureStoreCoreService.class);

    public XSKSecureStoreFacade() {
    }

    public static final void store(String storeId, String dataId, String value) throws XSKSecureStoreException {
        xskSecureStoreCoreService.createSecureStoreValue(storeId, IXSKSecureStoreModel.VALUE_APP_USER, dataId, value);
    }

    public static final boolean existsStore(String storeId) throws XSKSecureStoreException {
        return xskSecureStoreCoreService.existsSecureStore(storeId);
    }

    public static final String read(String storeId, String dataId) throws XSKSecureStoreException {
        XSKSecureStoreContent xskSecureStoreContent = xskSecureStoreCoreService.findSecureStoreContent(storeId, IXSKSecureStoreModel.VALUE_APP_USER, dataId);

        if (xskSecureStoreContent == null) {
            return null;
        }

        return new String(xskSecureStoreContent.getDataValue());
    }

    public static final void remove(String storeId, String dataId) throws XSKSecureStoreException {
        xskSecureStoreCoreService.deleteSecureStoreValue(storeId, IXSKSecureStoreModel.VALUE_APP_USER, dataId);
    }

    public static final String readForUser(String storeId, String dataId) throws XSKSecureStoreException {
        XSKSecureStoreContent xskSecureStoreContent = xskSecureStoreCoreService.findSecureStoreContent(storeId, UserFacade.getName(), dataId);

        if (xskSecureStoreContent == null) {
            return null;
        }

        return new String(xskSecureStoreContent.getDataValue());
    }

    public static final void storeForUser(String storeId, String dataId, String value) throws XSKSecureStoreException {
        xskSecureStoreCoreService.createSecureStoreValue(storeId, UserFacade.getName(), dataId, value);
    }

    public static final void removeForUser(String storeId, String dataId) throws XSKSecureStoreException {
        xskSecureStoreCoreService.deleteSecureStoreValue(storeId, UserFacade.getName(), dataId);
    }
}
