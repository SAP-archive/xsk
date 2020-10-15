package com.sap.xsk.hdb.ds.test;
/**
 * Copyright (c) 2010-2018 SAP and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   SAP - initial API and implementation
 */

/**
 * The Class DataStructureSchemaTest.
 */
public class XSKDataStructureEntitiesTest { //extends AbstractGuiceTest {

//	/** The data structure core service. */
//	@Inject
//	private XSKDataStructuresSynchronizer dataStructuresSynchronizer;
//
//	/** The datasource */
//	@Inject
//	private DataSource dataSource;
//
//	/**
//	 * Sets the up.
//	 *
//	 * @throws Exception
//	 *             the exception
//	 */
//	@Before
//	public void setUp() throws Exception {
//		this.dataStructuresSynchronizer = getInjector().getInstance(XSKDataStructuresSynchronizer.class);
//		this.dataSource = getInjector().getInstance(DataSource.class);
//	}
//
//	/**
//	 * Parses the entities
//	 */
//	@Test
//	public void updateEntities() {
//		try {
//			InputStream in = XSKDataStructureEntitiesTest.class.getResourceAsStream("/Products.hdbdd");
//			try {
//				String schemaFile = IOUtils.toString(in, StandardCharsets.UTF_8);
//				Injector injector = new ModelStandaloneSetup().createInjectorAndDoEMFRegistration();
//				XSKDataStructureEntitiesModel entities = new XSKDataStructureModelFactory().parseEntities(schemaFile);
//				assertEquals("product_project.db", entities.getNamespace());
//				Connection connection = null;
//				try {
//					connection = dataSource.getConnection();
//
//					PersistenceManager<Orders> persistenceManager = new PersistenceManager<Orders>();
//
//					if (persistenceManager.tableExists(connection, Orders.class)) {
//						persistenceManager.tableDrop(connection, Orders.class);
//					}
//
//					for (XSKDataStructureEntitiesEntityModel entity : entities.getContext().getЕntities()) {
//						if ("Item".equals(entity.getName())) {
//							dataStructuresSynchronizer.executeEntityUpdate(connection, entity);
//							break;
//						}
//					}
//
//					for (XSKDataStructureEntitiesEntityModel entity : entities.getContext().getЕntities()) {
//						if ("Orders".equals(entity.getName())) {
//							dataStructuresSynchronizer.executeEntityUpdate(connection, entity);
//							break;
//						}
//					}
//
//					boolean exists = persistenceManager.tableExists(connection, Orders.class);
//					assertTrue(exists);
//
//					for (XSKDataStructureEntitiesEntityModel table : entities.getContext().getЕntities()) {
//						if ("Orders".equals(table.getName())) {
//							dataStructuresSynchronizer.executeEntityDrop(connection, table);
//							break;
//						}
//					}
//
//					for (XSKDataStructureEntitiesEntityModel table : entities.getContext().getЕntities()) {
//						dataStructuresSynchronizer.executeEntityDrop(connection, table);
//					}
//
//					exists = persistenceManager.tableExists(connection, Orders.class);
//					assertFalse(exists);
//
//				} finally {
//					if (connection != null) {
//						connection.close();
//					}
//				} 
//			} finally {
//				if (in != null) {
//					in.close();
//				}
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			fail(e.getMessage());
//		}
//	}

}
