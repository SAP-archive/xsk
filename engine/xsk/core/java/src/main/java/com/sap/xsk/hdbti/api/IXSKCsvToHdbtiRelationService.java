package com.sap.xsk.hdbti.api;

import java.sql.Connection;
import java.util.List;

import com.sap.xsk.hdbti.model.XSKTableImportArtifact;
import com.sap.xsk.hdbti.model.XSKTableImportToCsvRelation;

public interface IXSKCsvToHdbtiRelationService {
    void persistNewCsvAndHdbtiRelations(XSKTableImportArtifact tableImportArtifact, Connection connection);

    void deleteCsvAndHdbtiRelations(String hdbtiFileName, Connection connection);

    List<XSKTableImportToCsvRelation> getAllHdbtiToCsvRelations();

    boolean hasCsvChanged(XSKTableImportToCsvRelation tableImportToCsvRelation, String csvContent);

    List<XSKTableImportToCsvRelation> getAffectedHdbtiToCsvRelations(String csvFilePath);
}
