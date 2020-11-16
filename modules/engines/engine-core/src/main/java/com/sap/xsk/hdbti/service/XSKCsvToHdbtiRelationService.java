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
package com.sap.xsk.hdbti.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.commons.codec.digest.DigestUtils;
import org.eclipse.dirigible.database.persistence.PersistenceManager;
import org.eclipse.dirigible.database.sql.SqlFactory;
import org.eclipse.dirigible.database.sql.builders.records.DeleteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.xsk.hdbti.api.IXSKCsvToHdbtiRelationService;
import com.sap.xsk.hdbti.model.XSKTableImportArtifact;
import com.sap.xsk.hdbti.model.XSKTableImportToCsvRelation;
import com.sap.xsk.utils.XSKUtils;

public class XSKCsvToHdbtiRelationService implements IXSKCsvToHdbtiRelationService {

    private static final Logger logger = LoggerFactory.getLogger(XSKCsvToHdbtiRelationService.class);

    @Inject
    private PersistenceManager<XSKTableImportToCsvRelation> xskTableImportToCsvRelationPersistenceManager;

    @Inject
    private DataSource dataSource;

    @Override
    public void persistNewCsvAndHdbtiRelations(XSKTableImportArtifact tableImportArtifact, Connection connection) {
        for(XSKTableImportToCsvRelation relation : tableImportArtifact.getTableImportToCsvRelations()){
            relation.setHdbti(XSKUtils.convertToFullPath(relation.getHdbti()));
            xskTableImportToCsvRelationPersistenceManager.insert(connection, relation);
        }
    }

    @Override
    public void deleteCsvAndHdbtiRelations(String hdbtiFileName, Connection connection) {
        DeleteBuilder deleteBuilder = new DeleteBuilder(SqlFactory.deriveDialect(connection));
        String sql = deleteBuilder.from("XSK_TABLE_IMPORT_TO_CSV").where("HDBTI_LOCATION = " + "'" + XSKUtils.convertToFullPath(hdbtiFileName) + "'").generate();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.execute();
        } catch (SQLException e) {
            logger.error("Error occurred while clearing the HdbtiToCsv relations from the DB", e);
        }
    }

    @Override
    public List<XSKTableImportToCsvRelation> getAllHdbtiToCsvRelations() {
        List<XSKTableImportToCsvRelation> listOfcsvToHdbtiRelations = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            listOfcsvToHdbtiRelations = xskTableImportToCsvRelationPersistenceManager.findAll(connection, XSKTableImportToCsvRelation.class);
        } catch (SQLException e) {
            logger.error("Error occured while retrieving the HdbtiToCsv relations from DB", e);
        }
        return listOfcsvToHdbtiRelations;
    }

    @Override
    public boolean hasCsvChanged(XSKTableImportToCsvRelation tableImportToCsvRelation, String csvContent) {
        return !tableImportToCsvRelation.getCsvHash().equals(DigestUtils.md5Hex(csvContent));
    }

    @Override
    public List<XSKTableImportToCsvRelation> getAffectedHdbtiToCsvRelations(String csvFilePath) {
        List<XSKTableImportToCsvRelation> relations = getAllHdbtiToCsvRelations();
        return relations.stream().filter(relation -> relation.getCsv().equals(XSKUtils.convertToFullPath(csvFilePath))).collect(Collectors.toList());
    }

    public String convertToActualFileName(String fileNamePath) {
        String fileName = fileNamePath.substring(fileNamePath.lastIndexOf(':') + 1);

        return "/registry/public/" + fileNamePath.substring(0, fileNamePath.indexOf(':')).replaceAll("\\.", "/") + "/" + fileName;
    }
}
