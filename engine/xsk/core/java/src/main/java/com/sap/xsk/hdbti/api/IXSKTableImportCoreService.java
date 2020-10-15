package com.sap.xsk.hdbti.api;

import java.io.IOException;
import java.util.List;

import com.sap.xsk.hdbti.model.XSKTableImportArtifact;

public interface IXSKTableImportCoreService {

    XSKTableImportArtifact createTableImportArtifact(XSKTableImportArtifact xscTableImportArtifact) throws XSKTableImportException;

    void updateTableImportArtifact(XSKTableImportArtifact artifact) throws XSKTableImportException;

    XSKTableImportArtifact getTableImportArtifact(String location) throws XSKTableImportException;

    void removeTableImportArtifact(String location);

    List<XSKTableImportArtifact> getTableImportArtifacts() throws XSKTableImportException;

    boolean existsTableImportArtifact(String location) throws XSKTableImportException;

    XSKTableImportArtifact parseTableImportArtifact(String location, String content) throws IOException;
}
