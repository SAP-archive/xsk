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

import org.eclipse.dirigible.commons.config.Configuration;

public class TestConstants {

  private TestConstants() {
  }

  public static final String HDBSEQUENCE_POSTGRESQL_DOCKER_IMAGE = "postgres:alpine";
  public static final String HDBSEQUENCE_POSTGRESQL_DOCKER_NETWORK_ALIAS = "postgres";
  public static final String HDBSEQUENCE_POSTGRESQL_EXPECTED_SEQUENCE_NAME = "sequence-itest::SampleSequence_HanaXSClassic";
  public static final int HDBSEQUENCE_POSTGRESQL_EXPECTED_SEQUENCE_COUNT = 1;
  public static final String HDBSEQUENCE_POSTGRESQL_ROOT_FOLDER = "/usr/local/target/dirigible/repository/root";
  public static final String HDBSEQUENCE_POSTGRESQL_REPO_PATH = "/registry/public/sequence-itest/SampleSequence_HanaXSClassic.hdbsequence";
  public static final String HDBSEQUENCE_POSTGRESQL_RELATIVE_RESOURCES_PATH = "/registry.public.sequence-itest/SampleSequence_HanaXSClassic.hdbsequence";

  public static final String HDBTABLE_POSTGRESQL_DOCKER_IMAGE = "postgres:alpine";
  public static final String HDBTABLE_POSTGRESQL_ROOT_FOLDER = "/usr/local/target/dirigible/repository/root";
  public static final String HDBTABLE_POSTGRESQL_REPO_PATH = "/registry/public/hdbtable-itest/SamplePostgreXSClassicTable.hdbtable";
  public static final String HDBTABLE_POSTGRESQL_RELATIVE_RESOURCES_PATH = "/registry.public.hdbtable-itest/SamplePostgreXSClassicTable.hdbtable";
  public static final int HDBTABLE_POSTGRESQL_EXPECTED_CREATED_RAWS_COUNT = 0;
  public static final String HDBTABLE_POSTGRESQL_EXPECTED_TABLE_NAME = "hdbtable-itest::SamplePostgreXSClassicTable";

  public static final String HDBTABLE_MYSQL_DOCKER_IMAGE = "mysql:5.7";
  public static final String HDBTABLE_MYSQL_ROOT_FOLDER = "/usr/local/target/dirigible/repository/root";
  public static final String HDBTABLE_MYSQL_REPO_PATH = "/registry/public/hdbtable-itest/SamplePostgreXSClassicTable.hdbtable";
  public static final String HDBTABLE_MYSQL_RELATIVE_RESOURCES_PATH = "/registry.public.hdbtable-itest/SamplePostgreXSClassicTable.hdbtable";
  public static final int HDBTABLE_MYSQL_EXPECTED_CREATED_RAWS_COUNT = 0;
  public static final String HDBTABLE_MYSQL_EXPECTED_TABLE_NAME = "hdbtable-itest::SamplePostgreXSClassicTable";

  public static final String HDBVIEW_POSTGRESQL_DOCKER_IMAGE = "postgres:alpine";
  public static final String HDBVIEW_POSTGRESQL_ROOT_FOLDER = "/usr/local/target/dirigible/repository/root";
  public static final String HDBVIEW_POSTGRESQL_REPO_PATH = "/registry/public/hdbview-itest/SamplePostgreXSClassicView.hdbview";
  public static final String HDBVIEW_POSTGRESQL_RELATIVE_RESOURCES_PATH = "/registry.public.hdbview-itest/SamplePostgreXSClassicView.hdbview";
  public static final int HDBVIEW_POSTGRESQL_EXPECTED_CREATED_RAWS_COUNT = 0;
  public static final String HDBVIEW_POSTGRESQL_EXPECTED_VIEW_NAME = "hdbview-itest::SamplePostgreXSClassicView";
  public static final String HDBVIEW_POSTGRESQL_CREATE_TABLE1_SQL = "create table \"public\".\"acme.com.test.tables::MY_TABLE1\"(Column1 integer,Column2 integer)";
  public static final String HDBVIEW_POSTGRESQL_CREATE_MY_VIEW1_SQL = "create table \"public\".\"acme.com.test.views::MY_VIEW1\"(Column1 integer,Column2 integer)";
  public static final String HDBVIEW_POSTGRESQL_DROP_TABLE1_SQL = "drop table \"public\".\"acme.com.test.tables::MY_TABLE1\"";
  public static final String HDBVIEW_POSTGRESQL_DROP_MY_VIEW1_SQL = "drop table \"public\".\"acme.com.test.views::MY_VIEW1\"";

  public static final String HDBVIEW_MYSQL_DOCKER_IMAGE = "mysql:5.7";
  public static final String HDBVIEW_MYSQL_ROOT_FOLDER = "/usr/local/target/dirigible/repository/root";
  public static final String HDBVIEW_MYSQL_REPO_PATH = "/registry/public/hdbview-itest/SampleMySQLXSClassicView.hdbview";
  public static final String HDBVIEW_MYSQL_RELATIVE_RESOURCES_PATH = "/registry.public.hdbview-itest/SampleMySQLXSClassicView.hdbview";
  public static final int HDBVIEW_MYSQL_EXPECTED_CREATED_RAWS_COUNT = 0;
  public static final String HDBVIEW_MYSQL_EXPECTED_VIEW_NAME = "`hdbview-itest::SampleMySQLXSClassicView`";
  public static final String HDBVIEW_MYSQL_CREATE_TABLE1_SQL = "create table `test`.`acme.com.test.tables::MY_TABLE1`(Column1 integer,Column2 integer)";
  public static final String HDBVIEW_MYSQL_CREATE_MY_VIEW1_SQL = "create table `test`.`acme.com.test.views::MY_VIEW1`(Column1 integer,Column2 integer)";
  public static final String HDBVIEW_MYSQL_DROP_TABLE1_SQL = "drop table `test`.`acme.com.test.tables::MY_TABLE1`";
  public static final String HDBVIEW_MYSQL_DROP_MY_VIEW1_SQL = "drop table `test`.`acme.com.test.views::MY_VIEW1`";

  public static final String HANA_DRIVER = "com.sap.db.jdbc.Driver";

  public static final String HDBVIEW_HANA_ROOT_FOLDER = "/usr/local/target/dirigible/repository/root";
  public static final String HDBVIEW_HANA_REPO_PATH = "/registry/public/hdbview-itest/SampleHANAXSClassicView.hdbview";
  public static final String HDBVIEW_HANA_RELATIVE_RESOURCES_PATH = "/registry.public.hdbview-itest/SampleHANAXSClassicView.hdbview";
  public static final int HDBVIEW_HANA_EXPECTED_CREATED_RAWS_COUNT = 0;
  public static final String HDBVIEW_HANA_EXPECTED_VIEW_NAME = "hdbview-itest::SampleHANAXSClassicView";

  public static final String HDBVIEW_HANA_CREATE_TABLE1_SQL = String
      .format("create table \"%s\".\"acme.com.test.tables::MY_TABLE1\"(COLUMN1 integer,COLUMN2 integer)", Configuration
          .get("db.username"));
  public static final String HDBVIEW_HANA_CREATE_MY_VIEW1_SQL = String
      .format("create table \"%s\".\"acme.com.test.views::MY_VIEW1\"(COLUMN1 integer,COLUMN2 integer)", Configuration
          .get("db.username"));
  public static final String HDBVIEW_HANA_DROP_TABLE1_SQL = String
      .format("drop table \"%s\".\"acme.com.test.tables::MY_TABLE1\"", Configuration
          .get("db.username"));
  public static final String HDBVIEW_HANA_DROP_MY_VIEW1_SQL = String
      .format("drop table \"%s\".\"acme.com.test.views::MY_VIEW1\"", Configuration
          .get("db.username"));
  public static final String HDBVIEW_HANA_DROP_XSK_DATASTRUCTURES = String
      .format("drop table \"%s\".\"XSK_DATA_STRUCTURES\"", Configuration
          .get("db.username"));
}
