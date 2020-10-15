package com.sap.xsk.xssecurestore.ds.api;

public interface IXSKSecureStoreEncryptor {
    byte[] encode(byte[] input);

    byte[] decode(byte[] input);
}
