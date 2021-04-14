/*
 * Copyright (c) 2019-2021 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2019-2021 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.hdbti.repository;

import org.eclipse.dirigible.repository.api.*;

public class TestResource implements IResource {
    private byte[] content;
    @Override
    public byte[] getContent() throws RepositoryReadException {
        return content;
    }

    @Override
    public void setContent(byte[] bytes) throws RepositoryWriteException {
        this.content = bytes;
    }

    @Override
    public void setContent(byte[] bytes, boolean b, String s) throws RepositoryWriteException {

    }

    @Override
    public boolean isBinary() {
        return false;
    }

    @Override
    public String getContentType() {
        return null;
    }

    @Override
    public IRepository getRepository() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getPath() {
        return null;
    }

    @Override
    public ICollection getParent() {
        return null;
    }

    @Override
    public IEntityInformation getInformation() throws RepositoryReadException {
        return null;
    }

    @Override
    public void create() throws RepositoryWriteException {

    }

    @Override
    public void delete() throws RepositoryWriteException {

    }

    @Override
    public void renameTo(String s) throws RepositoryWriteException {

    }

    @Override
    public void moveTo(String s) throws RepositoryWriteException {

    }

    @Override
    public void copyTo(String s) throws RepositoryWriteException {

    }

    @Override
    public boolean exists() throws RepositoryReadException {
        return false;
    }

    @Override
    public boolean isEmpty() throws RepositoryReadException {
        return false;
    }
}
