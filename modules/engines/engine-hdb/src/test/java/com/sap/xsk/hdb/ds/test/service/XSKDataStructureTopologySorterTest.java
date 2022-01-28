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
package com.sap.xsk.hdb.ds.test.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.sap.xsk.hdb.ds.model.XSKDataStructureDependencyModel;
import com.sap.xsk.hdb.ds.model.XSKDataStructureModel;
import com.sap.xsk.hdb.ds.service.XSKDataStructureTopologicalSorter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.eclipse.dirigible.database.ds.model.DataStructureModelException;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class DataStructureTopologySorter.
 */
public class XSKDataStructureTopologySorterTest {

  private static final Logger logger = LoggerFactory.getLogger(XSKDataStructureTopologySorterTest.class);

  /**
	 * Test sort.
	 */
	@Test
	public void testSort() {
		Map<String, XSKDataStructureModel> models = new HashMap<String, XSKDataStructureModel>();

		XSKDataStructureModel customers_view = new XSKDataStructureModel();
		customers_view.setName("customers_view");
		customers_view.getDependencies().add(new XSKDataStructureDependencyModel("customer", "TABLE"));
		customers_view.getDependencies().add(new XSKDataStructureDependencyModel("external", "TABLE"));
		models.put("customers_view", customers_view);
		XSKDataStructureModel users_view = new XSKDataStructureModel();
		users_view.setName("users_view");
		users_view.getDependencies().add(new XSKDataStructureDependencyModel("user", "TABLE"));
		models.put("users_view", users_view);
		XSKDataStructureModel customer = new XSKDataStructureModel();
		customer.setName("customer");
		customer.getDependencies().add(new XSKDataStructureDependencyModel("address", "TABLE"));
		models.put("customer", customer);
		XSKDataStructureModel address = new XSKDataStructureModel();
		address.setName("address");
		address.getDependencies().add(new XSKDataStructureDependencyModel("city", "TABLE"));
		models.put("address", address);
		XSKDataStructureModel city = new XSKDataStructureModel();
		city.setName("city");
		models.put("city", city);
		XSKDataStructureModel user = new XSKDataStructureModel();
		user.setName("user");
		user.getDependencies().add(new XSKDataStructureDependencyModel("address", "TABLE"));
		models.put("user", user);

		System.out.println("======= Unsorted =======");

		for (Entry<String, XSKDataStructureModel> entry : models.entrySet()) {
			System.out.println(entry.getKey());
		}

		List<String> output = new ArrayList<String>();
		List<String> external = new ArrayList<String>();

		try {
			XSKDataStructureTopologicalSorter.sort(models, output, external);
		} catch (DataStructureModelException e) {
			logger.error(e.getMessage(), e);
			fail(e.getMessage());
		}

		System.out.println("======= Sorted =======");

		for (String name : output) {
			System.out.println(name);
		}

		System.out.println("======= External =======");

		for (String name : external) {
			System.out.println(name);
		}

		assertEquals(output.get(0), "city");
		assertEquals(output.get(5), "users_view");
		assertEquals(external.get(0), "external");
	}

	/**
	 * Test sort cyclic.
	 */
	@Test
	public void testSortCyclic() {
		Map<String, XSKDataStructureModel> models = new HashMap<String, XSKDataStructureModel>();

		XSKDataStructureModel customers_view = new XSKDataStructureModel();
		customers_view.setName("customers_view");
		customers_view.getDependencies().add(new XSKDataStructureDependencyModel("customer", "TABLE"));
		customers_view.getDependencies().add(new XSKDataStructureDependencyModel("external", "TABLE"));
		models.put("customers_view", customers_view);
		XSKDataStructureModel users_view = new XSKDataStructureModel();
		users_view.setName("users_view");
		users_view.getDependencies().add(new XSKDataStructureDependencyModel("user", "TABLE"));
		models.put("users_view", users_view);
		XSKDataStructureModel customer = new XSKDataStructureModel();
		customer.setName("customer");
		customer.getDependencies().add(new XSKDataStructureDependencyModel("address", "TABLE"));
		models.put("customer", customer);
		XSKDataStructureModel address = new XSKDataStructureModel();
		address.setName("address");
		address.getDependencies().add(new XSKDataStructureDependencyModel("city", "TABLE"));

		address.getDependencies().add(new XSKDataStructureDependencyModel("customers_view", "TABLE"));

		models.put("address", address);
		XSKDataStructureModel city = new XSKDataStructureModel();
		city.setName("city");
		models.put("city", city);
		XSKDataStructureModel user = new XSKDataStructureModel();
		user.setName("user");
		user.getDependencies().add(new XSKDataStructureDependencyModel("address", "TABLE"));
		models.put("user", user);

		List<String> output = new ArrayList<String>();
		List<String> external = new ArrayList<String>();

		try {
			XSKDataStructureTopologicalSorter.sort(models, output, external);
		} catch ( DataStructureModelException e) {
			assertTrue(e.getMessage().startsWith("Cyclic"));
			System.out.println("Sort Cyclic Dependencies - caught!");
			return;
		}

	}
}
