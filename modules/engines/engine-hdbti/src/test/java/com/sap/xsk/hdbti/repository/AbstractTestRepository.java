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

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;
import org.eclipse.dirigible.repository.api.ICollection;
import org.eclipse.dirigible.repository.api.IEntity;
import org.eclipse.dirigible.repository.api.IRepository;
import org.eclipse.dirigible.repository.api.IResource;
import org.eclipse.dirigible.repository.api.RepositoryExportException;
import org.eclipse.dirigible.repository.api.RepositoryImportException;
import org.eclipse.dirigible.repository.api.RepositoryInitializationException;
import org.eclipse.dirigible.repository.api.RepositoryReadException;
import org.eclipse.dirigible.repository.api.RepositorySearchException;
import org.eclipse.dirigible.repository.api.RepositoryWriteException;

public abstract class AbstractTestRepository implements IRepository {
    @Override
    public String getParameter(String s) {
        return null;
    }

    @Override
    public long getLastModified() {
        return 0;
    }

    @Override
    public byte[] exportZip(List<String> list) throws RepositoryExportException {
        return new byte[0];
    }

    @Override
    public byte[] exportZip(String s, boolean b) throws RepositoryExportException {
        return new byte[0];
    }

    @Override
    public void importZip(ZipInputStream zipInputStream, String s) throws RepositoryImportException {

    }

    @Override
    public void importZip(ZipInputStream zipInputStream, String s, boolean b) throws RepositoryImportException {

    }

    @Override
    public void importZip(ZipInputStream zipInputStream, String s, boolean b, boolean b1) throws RepositoryImportException {

    }

    @Override
    public void importZip(byte[] bytes, String s) throws RepositoryImportException {

    }

    @Override
    public void importZip(byte[] bytes, String s, boolean b) throws RepositoryImportException {

    }

    @Override
    public void importZip(byte[] bytes, String s, boolean b, boolean b1, Map<String, String> map) throws RepositoryImportException {

    }

    @Override
    public void initialize() throws RepositoryInitializationException {

    }

    @Override
    public ICollection getRoot() {
        return null;
    }

    @Override
    public ICollection getCollection(String s) {
        return null;
    }

    @Override
    public boolean hasCollection(String s) throws RepositoryReadException {
        return false;
    }

    @Override
    public IResource getResource(String s) {
        return null;
    }

    @Override
    public boolean hasResource(String s) throws RepositoryReadException {
        return false;
    }

    @Override
    public List<String> getAllResourcePaths() throws RepositoryReadException {
        return null;
    }

    @Override
    public List<IEntity> searchName(String s, boolean b) throws RepositorySearchException {
        return null;
    }

    @Override
    public List<IEntity> searchName(String s, String s1, boolean b) throws RepositorySearchException {
        return null;
    }

    @Override
    public List<IEntity> searchPath(String s, boolean b) throws RepositorySearchException {
        return null;
    }

    @Override
    public List<IEntity> searchText(String s) throws RepositorySearchException {
        return null;
    }

    @Override
    public void searchRefresh() throws RepositorySearchException {

    }

    @Override
    public List<String> find(String s, String s1) throws RepositorySearchException {
        return null;
    }

    @Override
    public ICollection createCollection(String s) throws RepositoryWriteException {
        return null;
    }

    @Override
    public void removeCollection(String s) throws RepositoryWriteException {

    }

    @Override
    public IResource createResource(String s) throws RepositoryWriteException {
        return null;
    }

    @Override
    public IResource createResource(String s, byte[] bytes) throws RepositoryWriteException {
        return null;
    }

    @Override
    public IResource createResource(String s, byte[] bytes, boolean b, String s1) throws RepositoryWriteException {
        return null;
    }

    @Override
    public IResource createResource(String s, byte[] bytes, boolean b, String s1, boolean b1) throws RepositoryWriteException {
        return null;
    }

    @Override
    public void removeResource(String s) throws RepositoryWriteException {

    }

    @Override
    public void dispose() {

    }

    @Override
    public boolean isLinkingPathsSupported() {
        return false;
    }

    @Override
    public void linkPath(String s, String s1) throws IOException {

    }

    @Override
    public boolean isLinkedPath(String s) {
        return false;
    }
}
