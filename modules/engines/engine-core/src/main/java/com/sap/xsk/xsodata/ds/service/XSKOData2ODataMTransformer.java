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
package com.sap.xsk.xsodata.ds.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.eclipse.dirigible.database.persistence.model.PersistenceTableColumnModel;
import org.eclipse.dirigible.database.persistence.model.PersistenceTableModel;
import org.eclipse.dirigible.engine.odata2.transformers.DBMetadataUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.xsk.utils.XSKUtils;
import com.sap.xsk.xsodata.ds.model.XSKODataAssociation;
import com.sap.xsk.xsodata.ds.model.XSKODataEntity;
import com.sap.xsk.xsodata.ds.model.XSKODataModel;

@Singleton
public class XSKOData2ODataMTransformer {
	
	private static final Logger logger = LoggerFactory.getLogger(XSKOData2ODataMTransformer.class);

    @Inject
    private DBMetadataUtil dbMetadataUtil;

    public String[] transform(XSKODataModel model) throws SQLException {
    	
    	if (model.getService() == null) {
        	logger.error("Service element is null for xsodata file {}, so it will be skipped. Maybe the format is wrong and cannot be parsed.", model.getName());
        }

        List<String> result = new ArrayList<>();

        for (XSKODataEntity entity : model.getService().getEntities()) {
        	String namespace = model.getService().getNamespace() != null ? model.getService().getNamespace() : "Default";
        	String tableName = XSKUtils.getTableName(entity);
            StringBuilder buff = new StringBuilder();
            buff.append("{\n")
                    .append("  \"edmType\" : \"" + entity.getAlias() + "Type\",\n")
                    .append("  \"edmTypeFqn\" : \"" + namespace + "." + entity.getAlias() + "Type\",\n")
                    .append("  \"sqlTable\" : \"" + tableName + "\",\n");
            
            PersistenceTableModel tableMetadata = dbMetadataUtil.getTableMetadata(tableName);
            List<PersistenceTableColumnModel> idColumns = tableMetadata.getColumns().stream().filter(PersistenceTableColumnModel::isPrimaryKey).collect(Collectors.toList());
            
            if (idColumns == null || idColumns.isEmpty()) {
            	logger.error("Table {} not available for entity {}, so it will be skipped.", tableName, entity.getName());
            	continue;
            }
            
            tableMetadata.getColumns().forEach(column -> buff.append("  \"" + column.getName() + "\" : \"" + column.getName() + "\",\n"));
            tableMetadata.getRelations().stream().forEach(relation -> {
				String toEntityName = relation.getToTableName();
				toEntityName = toEntityName.substring(toEntityName.lastIndexOf(".") + 1);
				buff.append("  \"_ref_" + toEntityName + "Type\":{ \"joinColumn\" : \"" +
						relation.getFkColumnName() + "\"\n").append("}\n").append(",\n");
			});
            entity.getNavigates().forEach(navigate -> {
            	XSKODataAssociation association = XSKODataCoreService.getAssociation(model, navigate.getAssociation(), navigate.getAlias());
				String toRole = association.getDependent();
				String fromRoleProperty = association.getPrincipalKey();
				XSKODataEntity toSetEntity = XSKODataCoreService.getEntity(model, toRole, navigate.getAlias());
				String dependentEntity = toSetEntity.getAlias();
				buff.append("  \"_ref_" + dependentEntity + "Type\":{ \"joinColumn\" : \"" +
						fromRoleProperty + "\"\n").append("}\n").append(",\n");
			});
            
            String[] pks = idColumns.stream().map(PersistenceTableColumnModel::getName).collect(Collectors.toList()).toArray(new String[]{});
            buff.append("  \"_pk_\" : \"" + String.join(",", pks) + "\"}\n");
            
            result.add(buff.toString());
        }
        return result.toArray(new String[]{});

    }

}
