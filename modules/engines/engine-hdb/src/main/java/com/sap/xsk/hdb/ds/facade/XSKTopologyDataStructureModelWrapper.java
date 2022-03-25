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
package com.sap.xsk.hdb.ds.facade;

import com.sap.xsk.hdb.ds.model.XSKDataStructureDependencyModel;
import com.sap.xsk.hdb.ds.model.XSKDataStructureModel;
import com.sap.xsk.hdb.ds.service.manager.IXSKDataStructureManager;
import com.sap.xsk.hdb.ds.synchronizer.XSKDataStructuresSynchronizer;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.eclipse.dirigible.commons.api.topology.ITopologicallyDepletable;
import org.eclipse.dirigible.commons.api.topology.ITopologicallySortable;
import org.eclipse.dirigible.core.scheduler.api.AbstractSynchronizationArtefactType;
import org.eclipse.dirigible.core.scheduler.api.ISynchronizerArtefactType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XSKTopologyDataStructureModelWrapper<T extends XSKDataStructureModel> implements ITopologicallySortable, ITopologicallyDepletable {

    private static final Logger logger = LoggerFactory.getLogger(XSKTopologyDataStructureModelWrapper.class);
    private static final XSKDataStructuresSynchronizer DATA_STRUCTURES_SYNCHRONIZER = new XSKDataStructuresSynchronizer();
    private final AbstractSynchronizationArtefactType artefactType;

    private final IXSKDataStructureManager<T> modelManager;
    private final T model;
    private final Connection connection;
    private final Map<String, XSKTopologyDataStructureModelWrapper<XSKDataStructureModel>> wrappers;

    public XSKTopologyDataStructureModelWrapper(Connection connection, IXSKDataStructureManager<T> modelManager, T model, AbstractSynchronizationArtefactType artefactType,
        Map<String, XSKTopologyDataStructureModelWrapper<XSKDataStructureModel>> wrappers) {
        this.connection = connection;
        this.modelManager = modelManager;
        this.model = model;
        this.artefactType = artefactType;
        this.wrappers = wrappers;
        this.wrappers.put(getId(), (XSKTopologyDataStructureModelWrapper<XSKDataStructureModel>) this);
    }

    public XSKDataStructureModel getModel() {
        return model;
    }

    public AbstractSynchronizationArtefactType getArtefactType() {
        return artefactType;
    }

    @Override
    public String getId() {
        return this.model.getName();
    }

    @Override
    public List<ITopologicallySortable> getDependencies() {
        List<ITopologicallySortable> dependencies = new ArrayList<>();
        for (XSKDataStructureDependencyModel dependency : this.model.getDependencies()) {
            String dependencyName = dependency.getName();
            if (!wrappers.containsKey(dependencyName)) {
                logger.warn("Dependency is not present in this cycle: {}", dependencyName);
            } else {
                dependencies.add(wrappers.get(dependencyName));
            }
        }
        return dependencies;
    }

    @Override
    public boolean complete(String flow) {
        try {
            return modelManager.createDataStructure(connection, model);
        } catch (SQLException e) {
            logger.warn("Failed on trying to complete the artefact: " + e.getMessage(), e);
            return false;
        }
    }

    public void applyArtefactState(String artefactName, String artefactLocation, AbstractSynchronizationArtefactType type, ISynchronizerArtefactType.ArtefactState state, String message) {
        DATA_STRUCTURES_SYNCHRONIZER.applyArtefactState(artefactName, artefactLocation, type, state, message);
    }

}
