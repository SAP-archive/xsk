package com.sap.xsk.hdbti.processors;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.sap.xsk.hdbti.model.XSKTableImportConfigurationDefinition;

public interface IXSKTableImportProcessor {
    void process(XSKTableImportConfigurationDefinition tableImportConfigurationDefinition, Connection connection) throws IOException, SQLException;
}
