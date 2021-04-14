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
package com.sap.xsk.parser.xsodata.custom;

import com.sap.xsk.parser.xsodata.core.HdbxsodataBaseListener;
import com.sap.xsk.parser.xsodata.core.HdbxsodataParser;
import com.sap.xsk.parser.xsodata.model.*;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.*;
import java.util.stream.Collectors;

public class XSKHDBXSODATACoreListener extends HdbxsodataBaseListener {

    private final XSKHDBXSODATAService serviceModel = new XSKHDBXSODATAService();
    private ArrayList<HdbxsodataParser.NaventryContext> navEntries = new ArrayList<>();

    @Override
    public void exitXsodataDefinition(HdbxsodataParser.XsodataDefinitionContext ctx) {
        //set Annotations
        if (ctx.annotations() != null) {
            if (ctx.annotations().annotationsbody() != null) {
                ctx.annotations().annotationsbody().annotationconfig().forEach(el -> {
                    if (el.getChild(1).getText().equals("OData4SAP")) {
                        serviceModel.setEnableOData4SAPAnnotations(true);
                    }
                });
            }
        }

        //set Service
        if (ctx.service() != null) {
            if (ctx.service().namespace() != null) {
                serviceModel.setNamespace(handleStringLiteral(ctx.service().namespace().QUATED_STRING().getText()));
            }
        }

        //set Settings
        if (ctx.settings() != null) {
            if (ctx.settings().settingsbody() != null && ctx.settings().settingsbody().settingselement() != null) {
                XSKHDBXSODATASetting setting = new XSKHDBXSODATASetting();
                ctx.settings().settingsbody().settingselement().forEach(el -> {
                    if (el.supportnull() != null) {
                        setting.setEnableSupportNull(true);
                    }
                    if (el.contentcashecontrol() != null) {
                        setting.setContentCacheControl(Arrays.asList(handleStringLiteral(el.contentcashecontrol().QUATED_STRING().getText()).split(",")));
                    }
                    if (el.metadatacashecontrol() != null) {
                        setting.setMetadataCacheControl(Arrays.asList(handleStringLiteral(el.metadatacashecontrol().QUATED_STRING().getText()).split(",")));
                    }
                    if (el.hints() != null) {
                        if (el.hints().hintlist() != null) {
                            setting.setHints(el.hints().hintlist().hintvalue().stream().map(value -> handleStringLiteral(value.getText())).collect(Collectors.toList()));
                        }
                        if (el.hints().nullvalue() != null) {
                            setting.setHints(Collections.singletonList("null"));
                        }
                    }
                    if (el.limits() != null) {
                        el.limits().limitvalue().forEach(limit -> {
                            if (limit.maxrecords() != null) {
                                setting.setMaxRecords(limit.INT().getText());
                            }
                            if (limit.maxexpandedrecords() != null) {
                                setting.setMaxExpandedRecords(limit.INT().getText());
                            }
                        });
                    }
                    serviceModel.setSetting(setting);
                });
            }
        }
    }

    @Override
    public void exitEntity(HdbxsodataParser.EntityContext ctx) {
        XSKHDBXSODATAEntity entity = new XSKHDBXSODATAEntity();

        //set Object
        if (ctx.object() != null) {
            XSKHDBXSODATARepositoryObject repObject = new XSKHDBXSODATARepositoryObject();
            if (ctx.object().catalogobjectschema() != null) {
                repObject.setCatalogObjectSchema(handleStringLiteral(ctx.object().catalogobjectschema().getText()));
            }
            if (ctx.object().catalogobjectname() != null) {
                repObject.setCatalogObjectName(handleStringLiteral(ctx.object().catalogobjectname().getText()));
            }
            entity.setRepositoryObject(repObject);
        }

        //set EntitySet
        if (ctx.entityset() != null) {
            entity.setAlias(handleStringLiteral(ctx.entityset().entitysetname().getText()));
        }

        //set Navigations
        this.navEntries.forEach(el -> {
            XSKHDBXSODATANavigation navProp = new XSKHDBXSODATANavigation();
            navProp.setAssociation(handleStringLiteral(el.assocname().getText()));
            navProp.setAliasNavigation(handleStringLiteral(el.navpropname().getText()));
            entity.getNavigates().add(navProp);
        });

        //set Property Projection
        if (ctx.with() != null) {
            if (ctx.with().withoutProp() != null && ctx.with().propertylist() != null) {
                entity.setWithoutPropertyProjections(ctx.with().propertylist().columnname().stream().map(el -> handleStringLiteral(el.getText())).collect(Collectors.toList()));
            }
            if (ctx.with().withProp() != null && ctx.with().propertylist() != null) {
                entity.setWithPropertyProjections(ctx.with().propertylist().columnname().stream().map(el -> handleStringLiteral(el.getText())).collect(Collectors.toList()));
            }
        }

        //set Keys
        if (ctx.keys() != null) {
            if (ctx.keys().keygenerated() != null) {
                entity.setKeyGenerated(handleStringLiteral(ctx.keys().keygenerated().columnname().getText()));
            }
            if (ctx.keys().keylist() != null) {
                entity.setKeyList(ctx.keys().keylist().propertylist().columnname().stream().map(el -> handleStringLiteral(el.getText())).collect(Collectors.toList()));
            }
        }

        //set Aggregations
        if (ctx.aggregates() != null) {
            if (ctx.aggregates().aggregatestuple() != null) {
                ctx.aggregates().aggregatestuple().aggregate().forEach(el -> {
                    XSKHDBXSODATAAggregation newAggregation = new XSKHDBXSODATAAggregation();
                    newAggregation.setAggregateFunction(el.aggregatefunction().getText());
                    newAggregation.setAggregateColumnName(handleStringLiteral(el.columnname().getText()));
                    entity.getAggregations().add(newAggregation);
                });
                entity.setAggregationType(XSKHDBXSODATAAggregationType.EXPLICIT);
            } else {
                entity.setAggregationType(XSKHDBXSODATAAggregationType.IMPLICIT);
            }
        }

        //set Parameters
        if (ctx.parameters() != null) {
            if (ctx.parameters().parameterskeyand() != null) {
                entity.setParameterType(XSKHDBXSODATAParameterType.KEY_AND_ENTITY);
            } else {
                entity.setParameterType(XSKHDBXSODATAParameterType.ENTITY);
            }
            XSKHDBXSODATAParameter parameterEntitySets = new XSKHDBXSODATAParameter();
            if (ctx.parameters().parameterentitysetname() != null) {
                parameterEntitySets.setParameterEntitySetName(handleStringLiteral(ctx.parameters().parameterentitysetname().getText()));
            }
            if (ctx.parameters().parametersresultsprop() != null) {
                parameterEntitySets.setParameterResultsProperty(handleStringLiteral(ctx.parameters().parametersresultsprop().QUATED_STRING().getText()));
            }
            entity.setParameterEntitySet(parameterEntitySets);
        }

        //set Events
        if (ctx.modificationBody() != null) {
            entity.setModifications(assembleModificationsFromContext(ctx.modificationBody()));
        }

        serviceModel.getEntities().add(entity);
        this.navEntries = new ArrayList<>();
    }

    @Override
    public void exitAssociation(HdbxsodataParser.AssociationContext ctx) {
        XSKHDBXSODATAAssociation association = new XSKHDBXSODATAAssociation();

        //set AssociationName
        if (ctx.associationdef() != null) {
            association.setName(handleStringLiteral(ctx.associationdef().assocname().getText()));
        }

        //set WithReferentialConstraint
        if (ctx.assocrefconstraint() != null) {
            association.setWithReferentialConstraint(true);
        }

        //set Principal
        if (ctx.principalend() != null) {
            XSKHDBXSODATABinding principal = assembleBindingFromAssociationContext(ctx.principalend(), XSKHDBXSODATABindingType.PRINCIPAL);
            association.setPrincipal(principal);
        }

        //set Dependent
        if (ctx.dependentend() != null) {
            XSKHDBXSODATABinding dependent = assembleBindingFromAssociationContext(ctx.dependentend(), XSKHDBXSODATABindingType.DEPENDENT);
            association.setDependent(dependent);
        }

        //set AssociationTable
        if (ctx.assoctable() != null) {
            XSKHDBXSODATAAssociationTable associationTable = new XSKHDBXSODATAAssociationTable();
            associationTable.setRepositoryObject(handleStringLiteral(ctx.assoctable().repoobject().QUATED_STRING().getText()));
            //set Principal
            if (ctx.assoctable().overprincipalend() != null) {
                associationTable.setPrincipal(assembleBindingRoleFromAssociationTableContext(ctx.assoctable().overprincipalend(), XSKHDBXSODATABindingType.PRINCIPAL));
            }
            //set Dependent
            if (ctx.assoctable().overdependentend() != null) {
                associationTable.setDependent(assembleBindingRoleFromAssociationTableContext(ctx.assoctable().overdependentend(), XSKHDBXSODATABindingType.DEPENDENT));
            }
            //set Events
            if (ctx.assoctable().modificationBody() != null) {
                associationTable.setModifications(assembleModificationsFromContext(ctx.assoctable().modificationBody()));
            }
            association.setAssociationTable(associationTable);
        }

        //set Storage
        if (ctx.storage() != null) {
            XSKHDBXSODATAStorage storage = new XSKHDBXSODATAStorage();
            if (ctx.storage().nostorage() != null) {
                storage.setStorageType(XSKHDBXSODATAStorageType.NO_STORAGE);
            }
            if (ctx.storage().storageend() != null && XSKHDBXSODATAStorageType.fromValue(ctx.storage().storageend().getText()).isPresent()) {
                storage.setStorageType(XSKHDBXSODATAStorageType.fromValue(ctx.storage().storageend().getText()).get());
            }
            if (ctx.storage().modificationBody() != null) {
                storage.setModifications(assembleModificationsFromContext(ctx.storage().modificationBody()));
            }
            association.setStorage(storage);
        }

        //set Events
        if (ctx.modificationBody() != null) {
            association.setModifications(assembleModificationsFromContext(ctx.modificationBody()));
        }

        serviceModel.getAssociations().add(association);
    }

    @Override
    public void exitNaventry(HdbxsodataParser.NaventryContext ctx) {
        this.navEntries.add(ctx);
    }

    public XSKHDBXSODATAService getServiceModel() {
        return serviceModel;
    }

    private String handleStringLiteral(String value) {
        if (value != null && value.length() > 1) {
            String subStr = value.substring(1, value.length() - 1);
            String escapedQuote = subStr.replace("\\\"", "\"");
            return escapedQuote.replace("\\\\", "\\");
        }
        return null;
    }

    private XSKHDBXSODATABinding assembleBindingFromAssociationContext(ParserRuleContext context, XSKHDBXSODATABindingType type) {
        XSKHDBXSODATABindingRole role = new XSKHDBXSODATABindingRole();
        XSKHDBXSODATABinding binding = new XSKHDBXSODATABinding();
        role.setBindingType(type);
        HdbxsodataParser.EndContext endContext = (HdbxsodataParser.EndContext) context.children.stream().filter(x -> x instanceof HdbxsodataParser.EndContext).findAny().orElse(null);
        if (endContext != null) {
            role.setKeys(endContext.endref().joinpropertieslist().propertylist().columnname().stream().map(el -> handleStringLiteral(el.getText())).collect(Collectors.toList()));
            binding.setEntitySetName(handleStringLiteral(endContext.endref().endtype().entitysetname().getText()));
            binding.setBindingRole(role);
            Optional<XSKHDBXSODATAMultiplicityType> multiType = XSKHDBXSODATAMultiplicityType.fromValue(handleStringLiteral(endContext.multiplicity().multiplicityvalue().getText()));
            multiType.ifPresent(binding::setMultiplicityType);
        }
        return binding;
    }

    private XSKHDBXSODATABindingRole assembleBindingRoleFromAssociationTableContext(ParserRuleContext context, XSKHDBXSODATABindingType type) {
        XSKHDBXSODATABindingRole bindingRole = new XSKHDBXSODATABindingRole();
        bindingRole.setBindingType(type);
        HdbxsodataParser.OverendContext endContext = (HdbxsodataParser.OverendContext) context.children.stream().filter(x -> x instanceof HdbxsodataParser.OverendContext).findAny().orElse(null);
        if (endContext != null) {
            bindingRole.setKeys(endContext.propertylist().columnname().stream().map(el -> handleStringLiteral(el.getText())).collect(Collectors.toList()));
        }
        return bindingRole;
    }

    private List<XSKHDBXSODATAModification> assembleModificationsFromContext(HdbxsodataParser.ModificationBodyContext modificationBody) {
        List<XSKHDBXSODATAModification> modifications = new ArrayList<>();

        modificationBody.modification().forEach(el -> {
            XSKHDBXSODATAModification modification = new XSKHDBXSODATAModification();
            XSKHDBXSODATAModificationSpec modificationSpec = new XSKHDBXSODATAModificationSpec();
            HdbxsodataParser.ModificationspecContext spec = null;
            if (el.update() != null) {
                modification.setType(XSKHDBXSODATAModificationType.UPDATE);
                spec = el.update().modificationspec();
            } else if (el.create() != null) {
                modification.setType(XSKHDBXSODATAModificationType.CREATE);
                spec = el.create().modificationspec();
            } else if (el.delete() != null) {
                modification.setType(XSKHDBXSODATAModificationType.DELETE);
                spec = el.delete().modificationspec();
            }

            if (spec != null && spec.events() != null) {
                spec.events().eventlist().eventlistElement().forEach(eventListEl -> {
                    XSKHDBXSODATAEvent event = new XSKHDBXSODATAEvent();
                    Optional<XSKHDBXSODATAEventType> eventType = XSKHDBXSODATAEventType.fromValue(eventListEl.eventtype().getText());
                    eventType.ifPresent(event::setType);
                    event.setAction(handleStringLiteral(eventListEl.action().getText()));
                    modificationSpec.getEvents().add(event);
                });
            }
            if (spec != null && spec.modificationaction() != null) {
                modificationSpec.setModificationAction(handleStringLiteral(spec.modificationaction().action().getText()));
            }
            if (spec != null && spec.forbidden() != null) {
                modificationSpec.setForbidden(true);
            }
            modification.setSpecification(modificationSpec);
            modifications.add(modification);
        });
        return modifications;
    }

}
