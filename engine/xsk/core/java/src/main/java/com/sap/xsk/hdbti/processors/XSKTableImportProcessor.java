package com.sap.xsk.hdbti.processors;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.IOUtils;
import org.eclipse.dirigible.database.persistence.model.PersistenceTableModel;
import org.eclipse.dirigible.database.sql.SqlFactory;
import org.eclipse.dirigible.database.sql.builders.records.DeleteBuilder;
import org.eclipse.dirigible.database.sql.builders.records.InsertBuilder;
import org.eclipse.dirigible.engine.odata2.transformers.DBMetadataUtil;
import org.eclipse.dirigible.repository.api.IRepository;
import org.eclipse.dirigible.repository.api.IResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.xsk.hdbti.model.XSKTableImportConfigurationDefinition;
import com.sap.xsk.hdbti.service.XSKCsvToHdbtiRelationService;
import com.sap.xsk.hdbti.service.XSKTableImportCoreService;

@Singleton
public class XSKTableImportProcessor implements IXSKTableImportProcessor{

    private static final Logger logger = LoggerFactory.getLogger(XSKTableImportProcessor.class);
    @Inject
    private DBMetadataUtil dbMetadataUtil;

    @Inject
    private IRepository repository;

    @Inject
    private XSKTableImportCoreService xscTableImportCoreService;

    @Inject
    private XSKCsvToHdbtiRelationService xscCsvToHdbtiRelationService;

    @Override
    public void process(XSKTableImportConfigurationDefinition tableImportConfigurationDefinition, Connection connection) throws IOException, SQLException {
        IResource resource = repository.getResource(xscCsvToHdbtiRelationService.convertToActualFileName(tableImportConfigurationDefinition.getFile()));
        String contentAsString = IOUtils
                .toString(new InputStreamReader(new ByteArrayInputStream(resource.getContent()), StandardCharsets.UTF_8));
        CSVFormat csvFormat = createCSVFormat(tableImportConfigurationDefinition);
        CSVParser csvParser = CSVParser.parse(contentAsString, csvFormat);
        deleteFromTable(tableImportConfigurationDefinition, connection);
        for (CSVRecord csvRecord : csvParser) {
            insertTableData(tableImportConfigurationDefinition, csvRecord, connection);
        }
    }

    private CSVFormat createCSVFormat(XSKTableImportConfigurationDefinition tableImportConfigurationDefinition) {
        char delimiter = Objects.isNull(tableImportConfigurationDefinition.getDelimField()) ? ',' : tableImportConfigurationDefinition.getDelimField().charAt(0);
        return CSVFormat.newFormat(delimiter);
    }


    private void deleteFromTable(XSKTableImportConfigurationDefinition tableImportConfigurationDefinition, Connection connection) {
        DeleteBuilder deleteBuilder = new DeleteBuilder(SqlFactory.deriveDialect(connection));
        String tableName = xscTableImportCoreService.convertToActualTableName(tableImportConfigurationDefinition.getTable());
        String sql = deleteBuilder.from(tableName).generate();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.execute();
        } catch (SQLException e) {
            logger.error("Error occurred while clearing the table pointed by HDBTI file before importing new values from csv", e);
        }
    }

    private void insertTableData(XSKTableImportConfigurationDefinition tableImportConfigurationDefinition, CSVRecord csvRecord, Connection connection) throws SQLException {
        String tableName = xscTableImportCoreService.convertToActualTableName(tableImportConfigurationDefinition.getTable());
        PersistenceTableModel tableMetadata = dbMetadataUtil.getTableMetadata(tableName);
        InsertBuilder insertBuilder = new InsertBuilder(SqlFactory.deriveDialect(connection));
        insertBuilder.into(tableName);
        for (int i = 0; i < csvRecord.size(); i++) {
            if (!csvRecord.get(i).isEmpty()) {
                insertBuilder.column("\"" + tableMetadata.getColumns().get(i).getName() + "\"").value("\'" + csvRecord.get(i) + "\'");
            }
        }
        try (PreparedStatement statement = connection.prepareStatement(insertBuilder.generate())) {
            statement.execute();
        } catch (SQLException e) {
            logger.error("Error occured while inserting the csv values in the table pointed by HDBTI file", e);
        }
    }
}
