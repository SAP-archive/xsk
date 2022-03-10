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
package com.sap.xsk.hdb.ds.itest.hdbsequence;

import static org.junit.Assert.assertTrue;

import com.sap.xsk.hdb.ds.itest.AbstractXSKHDBITTest;
import com.sap.xsk.hdb.ds.itest.module.XSKHDBTestModule;
import com.sap.xsk.hdb.ds.itest.utils.HanaITestUtils;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import org.eclipse.dirigible.commons.config.Configuration;
import org.eclipse.dirigible.database.ds.model.IDataStructureModel;
import org.eclipse.dirigible.repository.local.LocalResource;
import org.junit.Before;
import org.junit.Test;

public class XSKHDBSequenceParserHanaITTest extends AbstractXSKHDBITTest {

    @Before
    public void setUpBeforeTest() throws SQLException {
        HanaITestUtils.clearDataFromXSKDataStructure(systemDatasource, Arrays.asList( //
            "'/sequence-itest/SampleSequence_HanaXSClassic.hdbsequence'", //
            "'/sequence-itest/SampleSequence_HanaXSClassicDiffSchema.hdbsequence'" //
        ));
        Configuration.set(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "true");
        facade.clearCache();
    }

    @Test
    public void testHDBSequenceCreateSameSchema() throws Exception {
        try (Connection connection = datasource.getConnection(); Statement stmt = connection.createStatement()) {

            String userSchema = Configuration.get("hana.username");
            String artifactName = "sequence-itest::SampleSequence_HanaXSClassic";

            String fileContent = String.format("schema= \"%s\";\n" + "increment_by=1;\n" + "start_with= 10;\n"
                + "maxvalue= 30;\n" + "nomaxvalue=false;\n" + "nominvalue= false;\n" + "minvalue= 10;\n"
                + "cycles=false;\n" + "public=false;", userSchema);
            LocalResource resource = XSKHDBTestModule.getResourceFromString( //
                "/usr/local/target/dirigible/repository/root", //
                "/registry/public/sequence-itest/SampleSequence_HanaXSClassic.hdbsequence", //
                fileContent);
            try {
                facade.handleResourceSynchronization(resource);
                facade.updateEntities();
                assertTrue(HanaITestUtils.checkExistOfSequence(stmt, artifactName, userSchema));
            } finally {
                HanaITestUtils.dropSequence(stmt, artifactName, userSchema);
            }
        }
    }

    @Test
    public void testHDBSequenceCreateDifferentSchema() throws Exception {
        try (Connection connection = datasource.getConnection(); Statement stmt = connection.createStatement()) {

            HanaITestUtils.createSchema(stmt, TEST_SCHEMA);
            String artifactName = "sequence-itest::SampleSequence_HanaXSClassicDiffSchema";
            LocalResource resource = XSKHDBTestModule.getResources("/usr/local/target/dirigible/repository/root", //
                "/registry/public/sequence-itest/SampleSequence_HanaXSClassicDiffSchema.hdbsequence", //
                "/sequence-itest/SampleSequence_HanaXSClassicDiffSchema.hdbsequence" //
            );

            try {
                facade.handleResourceSynchronization(resource);
                facade.updateEntities();
                assertTrue(HanaITestUtils.checkExistOfSequence(stmt, artifactName, TEST_SCHEMA));
            } finally {
                HanaITestUtils.dropSequence(stmt, artifactName, TEST_SCHEMA);
                HanaITestUtils.dropSchema(stmt, TEST_SCHEMA);
            }
        }
    }
}
