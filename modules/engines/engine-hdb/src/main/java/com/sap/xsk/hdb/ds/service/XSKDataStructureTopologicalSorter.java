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
package com.sap.xsk.hdb.ds.service;

import com.sap.xsk.hdb.ds.model.XSKDataStructureDependencyModel;
import com.sap.xsk.hdb.ds.model.XSKDataStructureModel;
import org.eclipse.dirigible.database.ds.model.DataStructureModelException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Data models sorter utility.
 */
public class XSKDataStructureTopologicalSorter {

	/**
	 * Sorts the data models.
	 *
	 * @param input            the models that will be sorted
	 * @param output            the output of the sorting
	 * @param external            the external dependencies
	 * @throws DataStructureModelException the data structure model exception
	 */
	public static void sort(Map<String, XSKDataStructureModel> input, List<String> output, List<String> external) throws DataStructureModelException {
		List<String> processing = new ArrayList<String>();
		for (Entry<String, XSKDataStructureModel> entry : input.entrySet()) {
			XSKDataStructureModel dataStructureModel = entry.getValue();
			followDependencies(input, output, external, processing, dataStructureModel);
			String name = dataStructureModel.getName();
			if (!output.contains(name)) {
				output.add(name);
			}
		}

	}

	/**
	 * Follow dependencies.
	 *
	 * @param input the input
	 * @param output the output
	 * @param external the external
	 * @param processing the processing
	 * @param dataStructureModel the data structure model
	 * @throws DataStructureModelException the data structure model exception
	 */
	protected static void followDependencies(Map<String, XSKDataStructureModel> input, List<String> output, List<String> external,
			List<String> processing, XSKDataStructureModel dataStructureModel) throws DataStructureModelException {
		processing.add(dataStructureModel.getName());
		List<XSKDataStructureDependencyModel> dependencies = dataStructureModel.getDependencies();
		for (XSKDataStructureDependencyModel dependencyModel : dependencies) {
			String dependencyName = dependencyModel.getName();
			if (input.containsKey(dependencyName)) {
				if (processing.contains(dependencyName)) {
					throw new DataStructureModelException(String.format("Cyclic dependency %s in %s", dataStructureModel.getName(), dependencyName));
				}
				XSKDataStructureModel dependentModel = input.get(dependencyName);
				if (!output.contains(dependencyName)) {
					followDependencies(input, output, external, processing, dependentModel);
					output.add(dependencyName);
				}
			} else {
				external.add(dependencyName);
			}
		}
		processing.remove(dataStructureModel.getName());
	}

}
