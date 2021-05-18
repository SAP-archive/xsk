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
package com.sap.xsk.hdb.ds.test.itest.utils;

public class HanaConstants {

  private HanaConstants() {

  }

  public static final String HANA_DRIVER = "com.sap.db.jdbc.Driver";
  public static final String HANA_URL = "jdbc:sap://ea8fb40d-1457-48e7-b5c0-7977f2c476db.hana.trial-eu10.hanacloud.ondemand.com:443?encrypt=true&validateCertificate=true";
  public static final String HANA_USERNAME = "DBADMIN";
  public static final String HANA_PASSWORD = "password";



  public static final String HDBVIEW_HANA_ROOT_FOLDER = "/usr/local/target/dirigible/repository/root";
  public static final String HDBVIEW_HANA_REPO_PATH = "/registry/public/hdbview-itest/SampleHANAXSClassicView.hdbview";
  public static final String HDBVIEW_HANA_RELATIVE_RESOURCES_PATH = "/registry.public.hdbview-itest/SampleHANAXSClassicView.hdbview";
  public static final int HDBVIEW_HANA_EXPECTED_CREATED_RAWS_COUNT = 0;
  public static final String HDBVIEW_HANA_EXPECTED_VIEW_NAME = "hdbview-itest::SampleHANAXSClassicView";
  //schema name to be provided as env var
  public static final String HDBVIEW_HANA_CREATE_TABLE1_SQL = "create table \"DBADMIN\".\"acme.com.test.tables::MY_TABLE1\"(COLUMN1 integer,COLUMN2 integer)";
  public static final String HDBVIEW_HANA_CREATE_MY_VIEW1_SQL = "create table \"DBADMIN\".\"acme.com.test.views::MY_VIEW1\"(COLUMN1 integer,COLUMN2 integer)";
  public static final String HDBVIEW_HANA_DROP_TABLE1_SQL = "drop table \"DBADMIN\".\"acme.com.test.tables::MY_TABLE1\"";
  public static final String HDBVIEW_HANA_DROP_MY_VIEW1_SQL = "drop table \"DBADMIN\".\"acme.com.test.views::MY_VIEW1\"";
}
