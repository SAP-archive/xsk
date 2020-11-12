package com.sap.xsk.xsaccess.ds.api;

import java.util.List;

import com.sap.xsk.xsaccess.ds.model.access.XSKAccessDefinition;

public interface IXSKAccessCoreService {
    String XSK_FILE_EXTENSION_ACCESS = ".xsaccess";

    XSKAccessDefinition createXSKAccessDefinition(String path, String authenticationMethod, String hash, boolean exposed, List<String> authorizationRolesAsList) throws XSKAccessException;

    XSKAccessDefinition updateXSKAccessDefinition(String path, String authenticationMethod, String hash, boolean exposed, List<String> authorizationRolesAsList) throws XSKAccessException;

    XSKAccessDefinition getXSKAccessDefinition(String id) throws XSKAccessException;

    List<XSKAccessDefinition> getAccessXSKDefinitions() throws XSKAccessException;

    void removeXSKAccessDefinition(String path) throws XSKAccessException;

    boolean existsXSKAccessDefinition(String path) throws XSKAccessException;

    XSKAccessDefinition parseXSAccessArtifact(byte[] json);

    void clearCache();
}