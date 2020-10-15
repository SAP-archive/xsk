package com.sap.xsk.xsaccess.ds.api;

import java.util.List;

import com.sap.xsk.xsaccess.ds.model.privilege.XSKPrivilegeDefinition;

public interface IXSKPrivilegeCoreService {
    String XSK_FILE_EXTENSION_PRIVILEGE = ".xsprivileges";

    String XSK_PRIVILEGES_TABLE_NAME = "XSK_PRIVILEGES";

    XSKPrivilegeDefinition createXSKPrivilege(String name, String description) throws XSKPrivilegeException;

    XSKPrivilegeDefinition updateXSKPrivileges(String name, String description) throws XSKPrivilegeException;

    List<XSKPrivilegeDefinition> getXSKPrivileges() throws XSKPrivilegeException;

    void removeXSKPrivilegeByName(String name) throws XSKPrivilegeException;

    XSKPrivilegeDefinition getXSKPrivilegeByName(String name) throws XSKPrivilegeException;

    boolean xscPrivilegeExists(String name) throws XSKPrivilegeException;
}