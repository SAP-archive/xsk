package com.sap.xsk.hdb.ds.parser.entities;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
import com.sap.xsk.models.hdbdd.ModelStandaloneSetupGenerated;
import com.sap.xsk.models.hdbdd.model.HdbDD;
import com.sap.xsk.models.hdbdd.model.impl.ContextImpl;
import com.sap.xsk.models.hdbdd.model.impl.EntityImpl;
import com.sap.xsk.models.hdbdd.model.impl.FieldImpl;
import com.sap.xsk.models.hdbdd.model.impl.FieldPrimitiveImpl;
import com.sap.xsk.models.hdbdd.model.impl.FieldReferenceImpl;
import com.sap.xsk.models.hdbdd.model.impl.FieldTypeImpl;
import com.sap.xsk.models.hdbdd.model.impl.NamespaceImpl;
import com.sap.xsk.models.hdbdd.model.impl.SchemaImpl;
import com.sap.xsk.models.hdbdd.model.impl.TypeDefinitionImpl;
import com.sap.xsk.hdb.ds.api.IXSKDataStructureModel;
import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.model.XSKDataStructureContextModel;
import com.sap.xsk.hdb.ds.model.XSKDataStructureEntitiesModel;
import com.sap.xsk.hdb.ds.model.XSKDataStructureEntityModel;
import com.sap.xsk.hdb.ds.model.XSKDataStructureTableColumnModel;
import com.sap.xsk.hdb.ds.model.XSKDataStructureTableConstraintForeignKeyModel;
import com.sap.xsk.hdb.ds.model.XSKDataStructureTypeDefinitionModel;
import com.sap.xsk.hdb.ds.parser.XSKDataStructureParser;

public class XSKEntitiesParser implements XSKDataStructureParser {
	
	private static final Logger logger = LoggerFactory.getLogger(XSKEntitiesParser.class);
	
    static Map<String, String> TYPES_MAP = new HashMap<String, String>();

    static {
        TYPES_MAP.put("String", "VARCHAR");
        TYPES_MAP.put("UTCTimestamp", "TIMESTAMP");
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
	@Override
    public XSKDataStructureEntitiesModel parse(String location, String content) throws XSKDataStructuresException, IOException {
        content = content.replaceAll("'", "");
        content = content.replaceAll("\"", "");
        
        content = content.replaceAll(" Entity ", " entity ");
        content = content.replaceAll("	Entity ", "	entity ");
        content = content.replaceAll(" Type ", " type ");

        new org.eclipse.emf.mwe.utils.StandaloneSetup().setPlatformUri("../");
        Injector injector = new ModelStandaloneSetupGenerated().createInjectorAndDoEMFRegistration();
        XtextResourceSet resourceSet = injector.getInstance(XtextResourceSet.class);
        resourceSet.addLoadOption(XtextResource.OPTION_RESOLVE_ALL, Boolean.TRUE);

        Resource resource = resourceSet.createResource(URI.createURI("dummy:/example.hdbdd"));
        InputStream in = new ByteArrayInputStream(content.getBytes());
        resource.load(in, resourceSet.getLoadOptions());
        HdbDD hdbDD = (HdbDD) resource.getContents().get(0);
//      Not used?
        EList errors = hdbDD.eResource().getErrors();
        Iterator errorsIterator = errors.iterator();
        while (errorsIterator.hasNext()) {
        	logger.error(errorsIterator.next().toString());
        }
        XSKDataStructureEntitiesModel hdbddModel = new XSKDataStructureEntitiesModel();

        hdbddModel.setName(new File(location).getName());
        hdbddModel.setLocation(location);
        hdbddModel.setType(IXSKDataStructureModel.TYPE_ENTITIES);
        hdbddModel.setHash(DigestUtils.md5Hex(content));
        hdbddModel.setCreatedBy(UserFacade.getName());
        hdbddModel.setCreatedAt(new Timestamp(new java.util.Date().getTime()));

        for (Iterator iterator = hdbDD.getElements().iterator(); iterator.hasNext();) {
            com.sap.xsk.models.hdbdd.model.Type type = (com.sap.xsk.models.hdbdd.model.Type) iterator.next();
            if (type instanceof NamespaceImpl) {
                hdbddModel.setNamespace(((NamespaceImpl) type).getName());
            } else if (type instanceof SchemaImpl) {
                hdbddModel.setSchema(((SchemaImpl) type).getName());
            } else if (type instanceof ContextImpl) {
                ContextImpl eContext = ((ContextImpl) type);
                XSKDataStructureContextModel context = new XSKDataStructureContextModel();
                context.setName(eContext.getName());
                hdbddModel.setContext(context);
                List<XSKDataStructureTableConstraintForeignKeyModel> foreignKeys = new ArrayList<XSKDataStructureTableConstraintForeignKeyModel>();
                for (Iterator iteratorEntries = eContext.getEntities().iterator(); iteratorEntries.hasNext();) {

                    Object entry = iteratorEntries.next();
                    if (entry instanceof EntityImpl) {
                        EntityImpl eEntity = (EntityImpl) entry;
                        XSKDataStructureEntityModel entity = new XSKDataStructureEntityModel();
                        entity.setNamespace(hdbddModel.getNamespace());
                        entity.setContext(context.getName());
                        entity.setName(eEntity.getName());
                        for (Iterator iteratorColumns = eEntity.getFields().iterator(); iteratorColumns.hasNext();) {
                            FieldImpl eField = (FieldImpl) iteratorColumns.next();
                            if (eField instanceof FieldPrimitiveImpl) {
                                FieldPrimitiveImpl eFieldPrimitive = (FieldPrimitiveImpl) eField;
                                XSKDataStructureTableColumnModel column = new XSKDataStructureTableColumnModel();
                                //column.setName(CaseFormat.UPPER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, eFieldPrimitive.getName()));
                                column.setName(eFieldPrimitive.getName());
                                column.setPrimaryKey(eFieldPrimitive.isKey());
                                String mappedType = TYPES_MAP.get(eFieldPrimitive.getFieldType());
                                column.setType(mappedType != null ? mappedType: "INTEGER");
                                if (eFieldPrimitive.getFieldLength() != 0) {
                                    column.setLength(eFieldPrimitive.getFieldLength() + "");
                                }
                                entity.getColumns().add(column);
                            } else if (eField instanceof FieldTypeImpl) {
                                FieldTypeImpl eFieldType = (FieldTypeImpl) eField;
                                XSKDataStructureTypeDefinitionModel typeDefinition = context.getTypes().get(eFieldType.getName());
                                if (typeDefinition != null) {
                                    XSKDataStructureTableColumnModel column = new XSKDataStructureTableColumnModel();
                                    // column.setName(CaseFormat.UPPER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, typeDefinition.getName()));
                                    column.setName(typeDefinition.getName());
                                    column.setPrimaryKey(eFieldType.isKey());
                                    String mappedType = TYPES_MAP.get(typeDefinition.getType());
                                    column.setType(mappedType != null ? mappedType: "INTEGER");
                                    if (typeDefinition.getLength() != 0) {
                                        column.setLength(typeDefinition.getLength() + "");
                                    }
                                    entity.getColumns().add(column);
                                } else {
                                    throw new IllegalArgumentException("Undefined field type: " + eFieldType.getName());
                                }
                            }  else if (eField instanceof FieldReferenceImpl) {
                            	FieldReferenceImpl eFieldReference = (FieldReferenceImpl) eField;
                                XSKDataStructureTableConstraintForeignKeyModel referenceDefinition = new XSKDataStructureTableConstraintForeignKeyModel();
                                referenceDefinition.setName(eFieldReference.getFieldReferenceEntity());
                                referenceDefinition.setReferencedTable(entity.getName());
                                String fieldReferenceEntityLocalId = eFieldReference.getFieldReferenceEntityLocalId();
                                fieldReferenceEntityLocalId = fieldReferenceEntityLocalId.substring(fieldReferenceEntityLocalId.indexOf(".") + 1);
                                referenceDefinition.setColumns(new String[] {fieldReferenceEntityLocalId});
								referenceDefinition.setReferencedColumns(new String[] {eFieldReference.getFieldReferenceEntityForeignId()});
								foreignKeys.add(referenceDefinition);
                            } else {
                                throw new IllegalArgumentException("Unknown field type: " + eField.getClass().getCanonicalName());
                            }
                        }
                        context.getЕntities().add(entity);
                    } else if (entry instanceof TypeDefinitionImpl) {
                        TypeDefinitionImpl eTypeDefinition = (TypeDefinitionImpl) entry;
                        XSKDataStructureTypeDefinitionModel typeDefinition = new XSKDataStructureTypeDefinitionModel(
                                eTypeDefinition.getName(), eTypeDefinition.getFieldType(), eTypeDefinition.getFieldLength());
                        context.getTypes().put(typeDefinition.getName(), typeDefinition);
                    } else {
                        throw new IllegalArgumentException("Unknown field entry: " + entry.getClass().getCanonicalName());
                    }
                }
                
                for (XSKDataStructureTableConstraintForeignKeyModel referenceDefinition : foreignKeys) {
                	for (XSKDataStructureEntityModel entity : context.getЕntities()) {
                		if (entity.getName().equals(referenceDefinition.getName())) {
                			entity.getConstraints().getForeignKeys().add(referenceDefinition);
                			break;
                		}
                	}
                }
            }
        }
        return hdbddModel;
    }

    @Override
    public String getType() {
        return IXSKDataStructureModel.TYPE_ENTITIES;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
	@Override
    public Class getDataStructureClass() {
        return XSKDataStructureEntitiesModel.class;
    }
}
