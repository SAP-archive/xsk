/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2021 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.hdbti.processors;

import com.sap.xsk.exceptions.XSKArtifactParserException;
import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdbti.api.IXSKHDBTICoreService;
import com.sap.xsk.hdbti.api.IXSKHDBTIProcessor;
import com.sap.xsk.hdbti.api.IXSKTableImportModel;
import com.sap.xsk.hdbti.api.XSKTableImportException;
import com.sap.xsk.hdbti.model.*;
import com.sap.xsk.hdbti.module.HdbtiTestModule;
import com.sap.xsk.hdbti.service.XSKHDBTICoreService;
import com.sap.xsk.parser.hdbti.exception.XSKHDBTISyntaxErrorException;
import com.sap.xsk.parser.hdbti.models.XSKHDBTIImportConfigModel;
import org.eclipse.dirigible.commons.config.StaticObjects;
import org.eclipse.dirigible.database.persistence.PersistenceManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class XSKHDBTIProcessorTest {

    private static final String STUDENTS_CSV_LOCATION = "registry/public/university/students/students.csv";
    public static final String HDBTI_LOCATION = "university/data/Students.hdbti";
    public static final String CSV_FILE_LOCATION = "university.students:students.csv";
    public static final String STUDENTS_TABLE_NAME = "STUDENTS";

    private DataSource datasource;
    private IXSKHDBTIProcessor processor;
    private IXSKHDBTICoreService xskHdbtiCoreService;
    private final PersistenceManager<Student> studentManager = new PersistenceManager<>();
    private final PersistenceManager<XSKImportedCSVRecordModel> importedCsvRecordsManager = new PersistenceManager<>();
    private final PersistenceManager<XSKTableImportArtifact> importArtifactManager = new PersistenceManager<>();
    private final PersistenceManager<XSKTableImportToCsvRelation> importToCsvRelationManager = new PersistenceManager<>();

    @Before
    public void setUp() throws SQLException {
        HdbtiTestModule hdbtiTestModule = new HdbtiTestModule();
        hdbtiTestModule.configure();
        this.datasource = (DataSource) StaticObjects.get(StaticObjects.DATASOURCE);
        processor = new XSKHDBTIProcessor();
        xskHdbtiCoreService = new XSKHDBTICoreService();
        try (Connection connection = this.datasource.getConnection()) {
            studentManager.tableCreate(connection, Student.class);
        }
    }

    @After
    public void cleanup() throws SQLException {
        try (Connection connection = this.datasource.getConnection()) {
            if (studentManager.tableExists(connection, Student.class))
                studentManager.tableDrop(connection, Student.class);
            if (importedCsvRecordsManager.tableExists(connection, XSKImportedCSVRecordModel.class))
                importedCsvRecordsManager.tableDrop(connection, XSKImportedCSVRecordModel.class);
        }
    }

    @Test
    public void testDataImportedCorrectly() throws XSKDataStructuresException, SQLException, IOException, XSKTableImportException {
        writeToFile(STUDENTS_CSV_LOCATION, getInitialContent());
        XSKTableImportConfigurationDefinition importConfigurationDefinition = new XSKTableImportConfigurationDefinition();
        importConfigurationDefinition.setFile(CSV_FILE_LOCATION);
        importConfigurationDefinition.setHdbtiFileName(HDBTI_LOCATION);
        importConfigurationDefinition.setTable(STUDENTS_TABLE_NAME);

        try (Connection connection = this.datasource.getConnection()) {
            processor.process(importConfigurationDefinition, connection);
            List<Student> students = studentManager.findAll(connection, Student.class);
            assertCorrectDataImported(students);

            List<XSKImportedCSVRecordModel> importedCsvRecords = importedCsvRecordsManager.findAll(connection, XSKImportedCSVRecordModel.class);
            assertCorrectCsvRecordMetadataImported(importedCsvRecords);
            writeToFile(STUDENTS_CSV_LOCATION, "");
        }
    }

    @Test
    public void testChangeOfDataProperlyReflectedInDb()
            throws XSKDataStructuresException, SQLException, IOException, XSKTableImportException {
        XSKTableImportConfigurationDefinition importConfigurationDefinition = new XSKTableImportConfigurationDefinition();
        importConfigurationDefinition.setFile(CSV_FILE_LOCATION);
        importConfigurationDefinition.setHdbtiFileName(HDBTI_LOCATION);
        importConfigurationDefinition.setTable(STUDENTS_TABLE_NAME);

        try (Connection connection = this.datasource.getConnection()) {
            writeToFile(STUDENTS_CSV_LOCATION, getInitialContent());
            processor.process(importConfigurationDefinition, connection);

            writeToFile(STUDENTS_CSV_LOCATION, "");
            writeToFile(STUDENTS_CSV_LOCATION, getUpdatedContent());
            processor.process(importConfigurationDefinition, connection);

            List<Student> students1 = studentManager.findAll(connection, Student.class);
            assertEquals("Changed", students1.get(1).getFirstName());
            assertEquals("Changed", students1.get(1).getLastName());
            assertEquals(Timestamp.valueOf("2018-09-21 14:00:12"), students1.get(1).getSigned());
        }
    }

    @Test
    public void testCsvRecordRemovedFromCsvFileShouldRemoveFromDb()
            throws XSKDataStructuresException, SQLException, IOException, XSKTableImportException {
        XSKTableImportConfigurationDefinition importConfigurationDefinitionWithRemovedRecord = new XSKTableImportConfigurationDefinition();
        importConfigurationDefinitionWithRemovedRecord.setFile(CSV_FILE_LOCATION);
        importConfigurationDefinitionWithRemovedRecord.setHdbtiFileName(HDBTI_LOCATION);
        importConfigurationDefinitionWithRemovedRecord.setTable(STUDENTS_TABLE_NAME);

        try (Connection connection = this.datasource.getConnection()) {
            writeToFile(STUDENTS_CSV_LOCATION, getInitialContent());
            processor.process(importConfigurationDefinitionWithRemovedRecord, connection);

            writeToFile(STUDENTS_CSV_LOCATION, "");
            writeToFile(STUDENTS_CSV_LOCATION, getRemovedContent());
            processor.process(importConfigurationDefinitionWithRemovedRecord, connection);

            List<Student> students = studentManager.findAll(connection, Student.class);
            assertEquals(2, students.size());

            List<XSKImportedCSVRecordModel> importedCsvRecords = importedCsvRecordsManager.findAll(connection, XSKImportedCSVRecordModel.class);
            assertEquals(2, importedCsvRecords.size());

            Student deletedStudent = studentManager.find(connection, Student.class, 1L);
            assertNull(deletedStudent);
            List<XSKImportedCSVRecordModel> importedCSVRecordModel = importedCsvRecordsManager.findAll(connection, XSKImportedCSVRecordModel.class);
            boolean isCsvRecordRemovedFromMetadataTable = importedCSVRecordModel.stream().anyMatch(i -> i.getRowId().equals("1"));
            assertFalse(isCsvRecordRemovedFromMetadataTable);

            writeToFile(STUDENTS_CSV_LOCATION, "");
        }
    }

    @Test
    public void testCsvRecordAddedShouldReflectDataInDb()
            throws XSKDataStructuresException, SQLException, IOException, XSKTableImportException {
        XSKTableImportConfigurationDefinition importConfigurationDefinitionWithRemovedRecord = new XSKTableImportConfigurationDefinition();
        importConfigurationDefinitionWithRemovedRecord.setFile(CSV_FILE_LOCATION);
        importConfigurationDefinitionWithRemovedRecord.setHdbtiFileName(HDBTI_LOCATION);
        importConfigurationDefinitionWithRemovedRecord.setTable(STUDENTS_TABLE_NAME);

        try (Connection connection = this.datasource.getConnection()) {
            writeToFile(STUDENTS_CSV_LOCATION, getInitialContent());
            processor.process(importConfigurationDefinitionWithRemovedRecord, connection);

            writeToFile(STUDENTS_CSV_LOCATION, "");
            writeToFile(STUDENTS_CSV_LOCATION, getAddedContent());

            processor.process(importConfigurationDefinitionWithRemovedRecord, connection);

            List<Student> students = studentManager.findAll(connection, Student.class);
            assertEquals(4, students.size());
            List<XSKImportedCSVRecordModel> importedCSVRecordModel = importedCsvRecordsManager.findAll(connection, XSKImportedCSVRecordModel.class);
            assertEquals(4, importedCSVRecordModel.size());
            writeToFile(STUDENTS_CSV_LOCATION, "");
        }
    }

    @Test
    public void testCsvRecordWithCustomDelimFieldDelimEnclosingAndEscapeCharShouldInsertProperData()
            throws XSKDataStructuresException, SQLException, IOException, XSKTableImportException {
        XSKTableImportConfigurationDefinition importConfigurationDefinition = new XSKTableImportConfigurationDefinition();
        importConfigurationDefinition.setFile(CSV_FILE_LOCATION);
        importConfigurationDefinition.setHdbtiFileName(HDBTI_LOCATION);
        importConfigurationDefinition.setTable(STUDENTS_TABLE_NAME);
        importConfigurationDefinition.setDelimEnclosing("'");
        importConfigurationDefinition.setDelimField(";");

        try (Connection connection = this.datasource.getConnection()) {
            writeToFile(STUDENTS_CSV_LOCATION, getQuoteContent());
            processor.process(importConfigurationDefinition, connection);
            List<Student> students = studentManager.findAll(connection, Student.class);
            assertEquals(1, students.size());
            assertEquals("Georgi, 'Junior'", students.get(0).getFirstName());

            writeToFile(STUDENTS_CSV_LOCATION, "");
        }
    }

    @Test
    public void testInsertCsvRecordWithHeaders()
            throws XSKDataStructuresException, SQLException, IOException, XSKTableImportException {
        XSKTableImportConfigurationDefinition importConfigurationDefinition = new XSKTableImportConfigurationDefinition();
        importConfigurationDefinition.setFile(CSV_FILE_LOCATION);
        importConfigurationDefinition.setHdbtiFileName(HDBTI_LOCATION);
        importConfigurationDefinition.setTable(STUDENTS_TABLE_NAME);
        importConfigurationDefinition.setHeader(true);

        try (Connection connection = this.datasource.getConnection()) {
            writeToFile(STUDENTS_CSV_LOCATION, getInitialContentWithHeaders());
            processor.process(importConfigurationDefinition, connection);
            List<Student> students = studentManager.findAll(connection, Student.class);
            assertCorrectDataImported(students);

            List<XSKImportedCSVRecordModel> importedCsvRecords = importedCsvRecordsManager.findAll(connection, XSKImportedCSVRecordModel.class);
            assertCorrectCsvRecordMetadataImported(importedCsvRecords);
            writeToFile(STUDENTS_CSV_LOCATION, "");
        }
    }

    @Test
    public void testUpdateCsvRecordWithHeadersShouldReflectDbData()
            throws XSKDataStructuresException, SQLException, IOException, XSKTableImportException {
        XSKTableImportConfigurationDefinition importConfigurationDefinition = new XSKTableImportConfigurationDefinition();
        importConfigurationDefinition.setFile(CSV_FILE_LOCATION);
        importConfigurationDefinition.setHdbtiFileName(HDBTI_LOCATION);
        importConfigurationDefinition.setTable(STUDENTS_TABLE_NAME);
        importConfigurationDefinition.setHeader(true);

        try (Connection connection = this.datasource.getConnection()) {
            writeToFile(STUDENTS_CSV_LOCATION, getInitialContentWithHeaders());
            processor.process(importConfigurationDefinition, connection);

            writeToFile(STUDENTS_CSV_LOCATION, "");
            writeToFile(STUDENTS_CSV_LOCATION, getChangedContentWithHeaders());
            processor.process(importConfigurationDefinition, connection);

            List<Student> students = studentManager.findAll(connection, Student.class);
            assertEquals("Changed", students.get(0).getFirstName());
            assertEquals("Changed", students.get(0).getLastName());
            assertEquals(25L, students.get(0).getAge().longValue());

            assertEquals("Changed", students.get(1).getFirstName());
            assertEquals("Changed", students.get(1).getLastName());
            assertEquals(25L, students.get(1).getAge().longValue());

            assertEquals("Changed", students.get(2).getFirstName());
            assertEquals("Changed", students.get(2).getLastName());
            assertEquals(25L, students.get(2).getAge().longValue());

            writeToFile(STUDENTS_CSV_LOCATION, "");
        }
    }

    @Test
    public void testCleanUpDeletesEverythingRelatedToHdbtiFile()
            throws XSKDataStructuresException, SQLException, IOException, XSKTableImportException {
        XSKTableImportConfigurationDefinition importConfigurationDefinition = new XSKTableImportConfigurationDefinition();
        importConfigurationDefinition.setFile(CSV_FILE_LOCATION);
        importConfigurationDefinition.setHdbtiFileName(HDBTI_LOCATION);
        importConfigurationDefinition.setTable(STUDENTS_TABLE_NAME);

        try (Connection connection = this.datasource.getConnection()) {
            writeToFile(STUDENTS_CSV_LOCATION, getInitialContent());
            importArtifactManager.insert(connection, getTableImportArtifact());
            importToCsvRelationManager.insert(connection, getXskTableImportToCsvRelation());
            importToCsvRelationManager.insert(connection, getXskTableImportToCsvRelation());

            processor.process(importConfigurationDefinition, connection);
            xskHdbtiCoreService.cleanUpHdbtiRelatedData();
            List<XSKTableImportToCsvRelation> csvRelations = importToCsvRelationManager.findAll(connection, XSKTableImportToCsvRelation.class);
            List<XSKTableImportArtifact> importArtifacts = importArtifactManager.findAll(connection, XSKTableImportArtifact.class);
            List<Student> students = studentManager.findAll(connection, Student.class);
            List<XSKImportedCSVRecordModel> importedCSVRecordModels = importedCsvRecordsManager
                    .findAll(connection, XSKImportedCSVRecordModel.class);

            assertEquals(0, csvRelations.size());
            assertEquals(0, importArtifacts.size());
            assertEquals(0, students.size());
            assertEquals(0, importedCSVRecordModels.size());
        }
    }

    private XSKTableImportToCsvRelation getXskTableImportToCsvRelation() {
        XSKTableImportToCsvRelation csvRelation = new XSKTableImportToCsvRelation();
        csvRelation.setCsv(CSV_FILE_LOCATION);
        csvRelation.setHdbti(HDBTI_LOCATION);
        csvRelation.setCsvHash("H@sh!");
        return csvRelation;
    }

    private XSKTableImportArtifact getTableImportArtifact() {
        XSKTableImportArtifact xskTableImportArtifact = new XSKTableImportArtifact();
        xskTableImportArtifact.setLocation(HDBTI_LOCATION);
        xskTableImportArtifact.setName("DS_NAME");
        xskTableImportArtifact.setType(IXSKTableImportModel.TYPE_HDBTI);
        xskTableImportArtifact.setCreatedBy("TestUser");
        xskTableImportArtifact.setHash("H@sh!");
        xskTableImportArtifact.setCreatedAt(new Timestamp(new Date().getTime()));
        return xskTableImportArtifact;
    }

    private void assertCorrectDataImported(List<Student> students) {
        int importedStudentsCount = 3;
        assertEquals(importedStudentsCount, students.size());
        assertEquals(1L, students.get(0).getId().longValue());
        assertEquals("Georgi", students.get(0).getFirstName());
        assertEquals("Georgiev", students.get(0).getLastName());
        assertEquals(27L, students.get(0).getAge().longValue());
        assertEquals(Timestamp.valueOf("2017-09-20 14:00:12"), students.get(0).getSigned());

        assertEquals(2L, students.get(1).getId().longValue());
        assertEquals("Sara", students.get(1).getFirstName());
        assertEquals("Toshkova", students.get(1).getLastName());
        assertEquals(21L, students.get(1).getAge().longValue());
        assertEquals(Timestamp.valueOf("2019-09-21 14:00:12"), students.get(1).getSigned());

        assertEquals(3L, students.get(2).getId().longValue());
        assertEquals("Toshko", students.get(2).getFirstName());
        assertEquals("Ivanov", students.get(2).getLastName());
        assertEquals(25L, students.get(2).getAge().longValue());
        assertEquals(Timestamp.valueOf("2018-09-23 14:00:12"), students.get(2).getSigned());
    }

    private void assertCorrectCsvRecordMetadataImported(List<XSKImportedCSVRecordModel> importedCsvRecords) {
        assertEquals(3, importedCsvRecords.size());

        assertEquals("1", importedCsvRecords.get(0).getRowId());
        assertEquals("STUDENTS", importedCsvRecords.get(0).getTableName());
        assertEquals("university.students:students.csv", importedCsvRecords.get(0).getCsvLocation());
        assertEquals(HDBTI_LOCATION, importedCsvRecords.get(0).getHdbtiLocation());

        assertEquals("2", importedCsvRecords.get(1).getRowId());
        assertEquals("STUDENTS", importedCsvRecords.get(1).getTableName());
        assertEquals("university.students:students.csv", importedCsvRecords.get(1).getCsvLocation());
        assertEquals(HDBTI_LOCATION, importedCsvRecords.get(1).getHdbtiLocation());

        assertEquals("3", importedCsvRecords.get(2).getRowId());
        assertEquals("STUDENTS", importedCsvRecords.get(2).getTableName());
        assertEquals("university.students:students.csv", importedCsvRecords.get(2).getCsvLocation());
        assertEquals(HDBTI_LOCATION, importedCsvRecords.get(2).getHdbtiLocation());
    }

    private String getInitialContent() {
        return "1,Georgi,Georgiev,27,2017-09-20 14:00:12" + System.lineSeparator()
                + "2,Sara,Toshkova,21,2019-09-21 14:00:12" + System.lineSeparator()
                + "3,Toshko,Ivanov,25,2018-09-23 14:00:12" + System.lineSeparator();
    }

    private String getInitialContentWithHeaders() {
        return "\"LAST_NAME\",\"FIRST_NAME\",\"ID\",\"AGE\",\"SIGNED\"" + System.lineSeparator()
                + "Georgiev,Georgi,1,27,2017-09-20 14:00:12" + System.lineSeparator()
                + "Toshkova,Sara,2,21,2019-09-21 14:00:12" + System.lineSeparator()
                + "Ivanov,Toshko,3,25,2018-09-23 14:00:12" + System.lineSeparator();
    }

    private String getChangedContentWithHeaders() {
        return "\"LAST_NAME\",\"FIRST_NAME\",\"ID\",\"AGE\",\"SIGNED\"" + System.lineSeparator()
                + "Changed,Changed,1,25,2017-09-20 14:00:12" + System.lineSeparator()
                + "Changed,Changed,2,25,2019-09-21 14:00:12" + System.lineSeparator()
                + "Changed,Changed,3,25,2018-09-23 14:00:12" + System.lineSeparator();
    }

    private String getQuoteContent() {
        return "1;'Georgi, \\'Junior\\'';Georgiev;27;2017-09-20 14:00:12" + System.lineSeparator();
    }

    private String getAddedContent() {
        return "1,Georgi,Georgiev,27,2017-09-20 14:00:12" + System.lineSeparator()
                + "2,Sara,Toshkova,21,2019-09-21 14:00:12" + System.lineSeparator()
                + "3,Toshko,Ivanov,25,2018-09-23 14:00:12" + System.lineSeparator()
                + "4,Pesho,Peshev,25,2014-09-23 13:10:12" + System.lineSeparator();
    }

    private String getUpdatedContent() {
        return "1,Georgi,Georgiev,27,2017-09-20 14:00:12" + System.lineSeparator()
                + "2,Changed,Changed,21,2018-09-21 14:00:12" + System.lineSeparator()
                + "3,Toshko,Ivanov,25,2018-09-23 14:00:12" + System.lineSeparator();
    }

    private String getRemovedContent() {
        return "2,Sara,Toshkova,21,2019-09-21 14:00:12" + System.lineSeparator()
                + "3,Toshko,Ivanov,25,2018-09-23 14:00:12" + System.lineSeparator();
    }

    private void writeToFile(String path, String content) {
        File fileToBeModified = getFileFromResource(path);
        try {
            assert fileToBeModified != null;
            try (FileWriter fileWriter = new FileWriter(fileToBeModified)) {
                fileWriter.write(content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private File getFileFromResource(String fileName) {

        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {

            // failed if files have whitespaces or special characters
            //return new File(resource.getFile());

            try {
                return new File(resource.toURI());
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }

            return null;
        }

    }

    @Test
    public void testParseHdbtiToJSONSuccessfully() throws IOException, XSKArtifactParserException, XSKHDBTISyntaxErrorException {
        String hdbtiSample = org.apache.commons.io.IOUtils
                .toString(XSKHDBTIProcessorTest.class.getResourceAsStream("/randomOrder.hdbti"), StandardCharsets.UTF_8);

        List<XSKHDBTIImportConfigModel> model = processor.parseHdbtiToJSON("randomOrder.hdbti", hdbtiSample.getBytes(StandardCharsets.UTF_8));
        assertEquals(model.size(), 2);

        XSKHDBTIImportConfigModel import1 = model.get(0);
        assertEquals(import1.getDelimEnclosing(), "\"");
        assertEquals(import1.getSchemaName(), "mySchema");
        assertTrue(import1.getDistinguishEmptyFromNull());
        assertFalse(import1.getHeader());
        assertEquals(import1.getTableName(), "myTable");
        assertFalse(import1.getUseHeaderNames());
        assertEquals(import1.getDelimField(), ";");
        assertEquals(import1.getFileName(), "/sap/ti2/demo/myData.csv");
        assertEquals(import1.getKeys().size(), 1);
        assertEquals(import1.getKeys().get(0).getColumn(), "GROUP_TYPE");
        assertEquals(import1.getKeys().get(0).getValues().size(), 3);
        assertEquals(import1.getKeys().get(0).getValues().get(0), "BW_CUBE");
        assertEquals(import1.getKeys().get(0).getValues().get(1), "BW_DSO");
        assertEquals(import1.getKeys().get(0).getValues().get(2), "BW_PSA");

        XSKHDBTIImportConfigModel import2 = model.get(1);
        assertEquals(import2.getDelimEnclosing(), "#");
        assertEquals(import2.getSchemaName(), "mySchema2");
        assertFalse(import2.getDistinguishEmptyFromNull());
        assertFalse(import2.getHeader());
        assertEquals(import2.getTableName(), "myTable2");
        assertFalse(import2.getUseHeaderNames());
        assertEquals(import2.getDelimField(), ";");
        assertEquals(import2.getFileName(), "/sap/ti2/demo/myData2.csv");
        assertEquals(import2.getKeys().size(), 2);
        assertEquals(import2.getKeys().get(0).getColumn(), "MAIN_TYPE");
        assertEquals(import2.getKeys().get(0).getValues().size(), 1);
        assertEquals(import2.getKeys().get(0).getValues().get(0), "BW_CO");
        assertEquals(import2.getKeys().get(1).getColumn(), "SECOND_TYPE");
        assertEquals(import2.getKeys().get(1).getValues().size(), 1);
        assertEquals(import2.getKeys().get(1).getValues().get(0), "BW_PO");
    }

    @Test
    public void testParseJSONtoHdbtiSuccessfully() throws IOException, XSKArtifactParserException, XSKHDBTISyntaxErrorException {
        String hdbtiSample = org.apache.commons.io.IOUtils
                .toString(XSKHDBTIProcessorTest.class.getResourceAsStream("/randomOrder.hdbti"), StandardCharsets.UTF_8);

        List<XSKHDBTIImportConfigModel> model = processor.parseHdbtiToJSON("randomOrder.hdbti", hdbtiSample.getBytes(StandardCharsets.UTF_8));
        String actualValue = processor.parseJSONtoHdbti((ArrayList<XSKHDBTIImportConfigModel>) model);
        String expectedValue = "import = [\n" +
                "{\n" +
                "\tdelimEnclosing=\"\\\"\";\n" +
                "\tschema = \"mySchema\";\n" +
                "\tdistinguishEmptyFromNull = true;\n" +
                "\theader = false;\n" +
                "\ttable = \"myTable\";\n" +
                "\tuseHeaderNames = false;\n" +
                "\tdelimField = \";\";\n" +
                "\tkeys = [\"GROUP_TYPE\":\"BW_CUBE\",\"GROUP_TYPE\":\"BW_DSO\",\"GROUP_TYPE\":\"BW_PSA\"];\n" +
                "\tfile = \"sap.ti2.demo:myData.csv\";\n" +
                "}, \n" +
                "{\n" +
                "\tdelimEnclosing=\"#\";\n" +
                "\tschema = \"mySchema2\";\n" +
                "\tdistinguishEmptyFromNull = false;\n" +
                "\theader = false;\n" +
                "\ttable = \"myTable2\";\n" +
                "\tuseHeaderNames = false;\n" +
                "\tdelimField = \";\";\n" +
                "\tkeys = [\"MAIN_TYPE\":\"BW_CO\", \"SECOND_TYPE\":\"BW_PO\"];\n" +
                "\tfile = \"sap.ti2.demo:myData2.csv\";\n" +
                "}];";

        assertEquals(expectedValue, actualValue);
    }
}
