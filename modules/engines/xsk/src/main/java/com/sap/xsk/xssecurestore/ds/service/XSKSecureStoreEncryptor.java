package com.sap.xsk.xssecurestore.ds.service;

import javax.inject.Singleton;

import org.eclipse.dirigible.api.v3.utils.Base64Facade;

import com.sap.xsk.xssecurestore.ds.api.IXSKSecureStoreEncryptor;

@Singleton
public class XSKSecureStoreEncryptor implements IXSKSecureStoreEncryptor {

    @Override
    public byte[] encode(byte[] input) {
        return Base64Facade.encodeNative(input);
    }

    @Override
    public byte[] decode(byte[] input) {
        return Base64Facade.decodeNative(input);
    }
}
