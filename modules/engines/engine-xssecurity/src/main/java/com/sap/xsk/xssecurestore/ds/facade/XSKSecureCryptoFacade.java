/*
 * Copyright (c) 2022 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2022 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.xssecurestore.ds.facade;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class XSKSecureCryptoFacade {
    private static final String hmacMD5Algorithm = "HmacMD5";
    private static final String hmacSHA1Algorithm = "HmacSHA1";
    private static final String hmacSHA256Algorithm = "HmacSHA256";
    private static final String SHA256 = "SHA-256";
    private static final String MD5 = "MD5";
    private static final String SHA1 = "SHA1";


    public static byte[] md5(String data, String key) throws NoSuchAlgorithmException, InvalidKeyException {
        return encryptHash(data, key, MD5, hmacMD5Algorithm);
    }

    public static byte[] md5(byte[] data, String key) throws NoSuchAlgorithmException, InvalidKeyException {
        return encryptHash(data, key, MD5, hmacMD5Algorithm);
    }

    public static byte[] sha1(String data, String key) throws NoSuchAlgorithmException, InvalidKeyException {
        return encryptHash(data, key, SHA1, hmacSHA1Algorithm);
    }

    public static byte[] sha1(byte[] data, String key) throws NoSuchAlgorithmException, InvalidKeyException {
        return encryptHash(data, key, SHA1, hmacSHA1Algorithm);
    }

    public static byte[] sha256(String data, String key) throws NoSuchAlgorithmException, InvalidKeyException {
        return encryptHash(data, key, SHA256, hmacSHA256Algorithm);
    }

    public static byte[] sha256(byte[] data, String key) throws NoSuchAlgorithmException, InvalidKeyException {
        return encryptHash(data, key, SHA256, hmacSHA256Algorithm);
    }

    private static byte[] encryptHash(String data, String key, String encryptType, String algorithm) throws NoSuchAlgorithmException, InvalidKeyException {
        if (key != null) {
            SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), algorithm);
            Mac mac = Mac.getInstance(algorithm);
            mac.init(secretKeySpec);
            return mac.doFinal(data.getBytes());
        } else {
            MessageDigest digest = MessageDigest.getInstance(encryptType);
            return digest.digest(data.getBytes(StandardCharsets.UTF_8));
        }
    }

    private static byte[] encryptHash(byte[] data, String key, String encryptType, String algorithm) throws NoSuchAlgorithmException, InvalidKeyException {
        if (key != null) {
            SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), algorithm);
            Mac mac = Mac.getInstance(algorithm);
            mac.init(secretKeySpec);
            return mac.doFinal(data);
        } else {
            MessageDigest digest = MessageDigest.getInstance(encryptType);
            return digest.digest(data);
        }
    }
}
