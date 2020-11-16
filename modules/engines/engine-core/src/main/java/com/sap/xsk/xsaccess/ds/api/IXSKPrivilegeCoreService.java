/*
 * Copyright (c) 2019-2020 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2019-2020 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
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

    boolean xskPrivilegeExists(String name) throws XSKPrivilegeException;
}