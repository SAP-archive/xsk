package com.sap.xsk.hdb.ds.parser.table;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.eclipse.dirigible.api.v3.security.UserFacade;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Injector;
import com.sap.xsk.models.hdbtable.HdbTableStandaloneSetupGenerated;
import com.sap.xsk.models.hdbtable.hdbTable.HdbTableModel;
import com.sap.xsk.hdb.ds.api.IXSKDataStructureModel;
import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.model.XSKDataStructureTableColumnModel;
import com.sap.xsk.hdb.ds.model.XSKDataStructureTableConstraintPrimaryKeyModel;
import com.sap.xsk.hdb.ds.model.XSKDataStructureTableModel;
import com.sap.xsk.hdb.ds.parser.XSKDataStructureParser;
import com.sap.xsk.utils.XSKUtils;

public class XSKTableParser implements XSKDataStructureParser {
	
	private static final Logger logger = LoggerFactory.getLogger(XSKTableParser.class);

    @Override
    public String getType() {
        return IXSKDataStructureModel.TYPE_TABLE;
    }

    @Override
    public Class getDataStructureClass() {
        return XSKDataStructureTableModel.class;
    }

    @Override
    public XSKDataStructureTableModel parse(String location, String content) throws XSKDataStructuresException, IOException {
//    	content = content.replaceAll("'", "");
//        content = content.replaceAll("\"", "");
        
        new org.eclipse.emf.mwe.utils.StandaloneSetup().setPlatformUri("../");
        Injector injector = new HdbTableStandaloneSetupGenerated().createInjectorAndDoEMFRegistration();
        XtextResourceSet resourceSet = injector.getInstance(XtextResourceSet.class);
        resourceSet.addLoadOption(XtextResource.OPTION_RESOLVE_ALL, Boolean.TRUE);

        Resource resource = resourceSet.createResource(URI.createURI("dummy:/example.hdbtable"));
        InputStream in = new ByteArrayInputStream(content.getBytes());
        resource.load(in, resourceSet.getLoadOptions());
        HdbTableModel hdbTable = (HdbTableModel) resource.getContents().get(0);
//      Not used?
        EList errors = hdbTable.eResource().getErrors();
        Iterator errorsIterator = errors.iterator();
        while (errorsIterator.hasNext()) {
        	logger.error(errorsIterator.next().toString());
        }
        XSKDataStructureTableModel hdbTableModel = new XSKDataStructureTableModel();

        hdbTableModel.setName(XSKUtils.getTableName(location));
        hdbTableModel.setLocation(location);
        hdbTableModel.setType(IXSKDataStructureModel.TYPE_TABLE);
        hdbTableModel.setHash(DigestUtils.md5Hex(content));
        hdbTableModel.setCreatedBy(UserFacade.getName());
        hdbTableModel.setCreatedAt(new Timestamp(new java.util.Date().getTime()));
        
        hdbTableModel.setSchema(hdbTable.getTableElement().getSchemaName());
        hdbTableModel.setTableType(hdbTable.getTableElement().getTypeValue());
        hdbTableModel.setDescription(hdbTable.getTableElement().getDescriptionText());
        
        for (Iterator iterator = hdbTable.getTableElement().getColumnsValues().iterator(); iterator.hasNext();) {
            com.sap.xsk.models.hdbtable.hdbTable.ColumnType columnType = (com.sap.xsk.models.hdbtable.hdbTable.ColumnType) iterator.next();
            XSKDataStructureTableColumnModel column = new XSKDataStructureTableColumnModel();
            column.setName(columnType.getColumnName());
            column.setLength(columnType.getColumnLength() + "");
            column.setNullable(Boolean.parseBoolean(columnType.getColumnNullable()));
            column.setType(columnType.getColumnSqlType());
            // TODO more attributes
            hdbTableModel.getColumns().add(column);
        }
        
        List<String> keys = new ArrayList<>();
        for (Iterator iterator = hdbTable.getTableElement().getTablePrimaryKeyColumnsValues().iterator(); iterator.hasNext();) {
        	keys.add((String) iterator.next());
        }
        if (!keys.isEmpty()) {
        	XSKDataStructureTableConstraintPrimaryKeyModel primaryKey = new XSKDataStructureTableConstraintPrimaryKeyModel();
        	primaryKey.setColumns(keys.toArray(new String[]{}));
        	hdbTableModel.getConstraints().setPrimaryKey(primaryKey);
        }
        
        return hdbTableModel;
    }
}
