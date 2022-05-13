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
// Generated from com/sap/xsk/parser/xsodata/core/Hdbxsodata.g4 by ANTLR 4.10.1
package com.sap.xsk.parser.xsodata.core;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link HdbxsodataParser}.
 */
public interface HdbxsodataListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#xsodataDefinition}.
	 * @param ctx the parse tree
	 */
	void enterXsodataDefinition(HdbxsodataParser.XsodataDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#xsodataDefinition}.
	 * @param ctx the parse tree
	 */
	void exitXsodataDefinition(HdbxsodataParser.XsodataDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#service}.
	 * @param ctx the parse tree
	 */
	void enterService(HdbxsodataParser.ServiceContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#service}.
	 * @param ctx the parse tree
	 */
	void exitService(HdbxsodataParser.ServiceContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#namespace}.
	 * @param ctx the parse tree
	 */
	void enterNamespace(HdbxsodataParser.NamespaceContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#namespace}.
	 * @param ctx the parse tree
	 */
	void exitNamespace(HdbxsodataParser.NamespaceContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#body}.
	 * @param ctx the parse tree
	 */
	void enterBody(HdbxsodataParser.BodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#body}.
	 * @param ctx the parse tree
	 */
	void exitBody(HdbxsodataParser.BodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#content}.
	 * @param ctx the parse tree
	 */
	void enterContent(HdbxsodataParser.ContentContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#content}.
	 * @param ctx the parse tree
	 */
	void exitContent(HdbxsodataParser.ContentContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#entry}.
	 * @param ctx the parse tree
	 */
	void enterEntry(HdbxsodataParser.EntryContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#entry}.
	 * @param ctx the parse tree
	 */
	void exitEntry(HdbxsodataParser.EntryContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#entity}.
	 * @param ctx the parse tree
	 */
	void enterEntity(HdbxsodataParser.EntityContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#entity}.
	 * @param ctx the parse tree
	 */
	void exitEntity(HdbxsodataParser.EntityContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#object}.
	 * @param ctx the parse tree
	 */
	void enterObject(HdbxsodataParser.ObjectContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#object}.
	 * @param ctx the parse tree
	 */
	void exitObject(HdbxsodataParser.ObjectContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#catalogobjectschema}.
	 * @param ctx the parse tree
	 */
	void enterCatalogobjectschema(HdbxsodataParser.CatalogobjectschemaContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#catalogobjectschema}.
	 * @param ctx the parse tree
	 */
	void exitCatalogobjectschema(HdbxsodataParser.CatalogobjectschemaContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#catalogobjectname}.
	 * @param ctx the parse tree
	 */
	void enterCatalogobjectname(HdbxsodataParser.CatalogobjectnameContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#catalogobjectname}.
	 * @param ctx the parse tree
	 */
	void exitCatalogobjectname(HdbxsodataParser.CatalogobjectnameContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#entityset}.
	 * @param ctx the parse tree
	 */
	void enterEntityset(HdbxsodataParser.EntitysetContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#entityset}.
	 * @param ctx the parse tree
	 */
	void exitEntityset(HdbxsodataParser.EntitysetContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#entitysetname}.
	 * @param ctx the parse tree
	 */
	void enterEntitysetname(HdbxsodataParser.EntitysetnameContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#entitysetname}.
	 * @param ctx the parse tree
	 */
	void exitEntitysetname(HdbxsodataParser.EntitysetnameContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#with}.
	 * @param ctx the parse tree
	 */
	void enterWith(HdbxsodataParser.WithContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#with}.
	 * @param ctx the parse tree
	 */
	void exitWith(HdbxsodataParser.WithContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#withProp}.
	 * @param ctx the parse tree
	 */
	void enterWithProp(HdbxsodataParser.WithPropContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#withProp}.
	 * @param ctx the parse tree
	 */
	void exitWithProp(HdbxsodataParser.WithPropContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#withoutProp}.
	 * @param ctx the parse tree
	 */
	void enterWithoutProp(HdbxsodataParser.WithoutPropContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#withoutProp}.
	 * @param ctx the parse tree
	 */
	void exitWithoutProp(HdbxsodataParser.WithoutPropContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#propertylist}.
	 * @param ctx the parse tree
	 */
	void enterPropertylist(HdbxsodataParser.PropertylistContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#propertylist}.
	 * @param ctx the parse tree
	 */
	void exitPropertylist(HdbxsodataParser.PropertylistContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#columnname}.
	 * @param ctx the parse tree
	 */
	void enterColumnname(HdbxsodataParser.ColumnnameContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#columnname}.
	 * @param ctx the parse tree
	 */
	void exitColumnname(HdbxsodataParser.ColumnnameContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#keys}.
	 * @param ctx the parse tree
	 */
	void enterKeys(HdbxsodataParser.KeysContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#keys}.
	 * @param ctx the parse tree
	 */
	void exitKeys(HdbxsodataParser.KeysContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#keylist}.
	 * @param ctx the parse tree
	 */
	void enterKeylist(HdbxsodataParser.KeylistContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#keylist}.
	 * @param ctx the parse tree
	 */
	void exitKeylist(HdbxsodataParser.KeylistContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#keygenerated}.
	 * @param ctx the parse tree
	 */
	void enterKeygenerated(HdbxsodataParser.KeygeneratedContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#keygenerated}.
	 * @param ctx the parse tree
	 */
	void exitKeygenerated(HdbxsodataParser.KeygeneratedContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#concurrencytoken}.
	 * @param ctx the parse tree
	 */
	void enterConcurrencytoken(HdbxsodataParser.ConcurrencytokenContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#concurrencytoken}.
	 * @param ctx the parse tree
	 */
	void exitConcurrencytoken(HdbxsodataParser.ConcurrencytokenContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#navigates}.
	 * @param ctx the parse tree
	 */
	void enterNavigates(HdbxsodataParser.NavigatesContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#navigates}.
	 * @param ctx the parse tree
	 */
	void exitNavigates(HdbxsodataParser.NavigatesContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#navlist}.
	 * @param ctx the parse tree
	 */
	void enterNavlist(HdbxsodataParser.NavlistContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#navlist}.
	 * @param ctx the parse tree
	 */
	void exitNavlist(HdbxsodataParser.NavlistContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#naventry}.
	 * @param ctx the parse tree
	 */
	void enterNaventry(HdbxsodataParser.NaventryContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#naventry}.
	 * @param ctx the parse tree
	 */
	void exitNaventry(HdbxsodataParser.NaventryContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#assocname}.
	 * @param ctx the parse tree
	 */
	void enterAssocname(HdbxsodataParser.AssocnameContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#assocname}.
	 * @param ctx the parse tree
	 */
	void exitAssocname(HdbxsodataParser.AssocnameContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#navpropname}.
	 * @param ctx the parse tree
	 */
	void enterNavpropname(HdbxsodataParser.NavpropnameContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#navpropname}.
	 * @param ctx the parse tree
	 */
	void exitNavpropname(HdbxsodataParser.NavpropnameContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#fromend}.
	 * @param ctx the parse tree
	 */
	void enterFromend(HdbxsodataParser.FromendContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#fromend}.
	 * @param ctx the parse tree
	 */
	void exitFromend(HdbxsodataParser.FromendContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#principal}.
	 * @param ctx the parse tree
	 */
	void enterPrincipal(HdbxsodataParser.PrincipalContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#principal}.
	 * @param ctx the parse tree
	 */
	void exitPrincipal(HdbxsodataParser.PrincipalContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#dependent}.
	 * @param ctx the parse tree
	 */
	void enterDependent(HdbxsodataParser.DependentContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#dependent}.
	 * @param ctx the parse tree
	 */
	void exitDependent(HdbxsodataParser.DependentContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#aggregates}.
	 * @param ctx the parse tree
	 */
	void enterAggregates(HdbxsodataParser.AggregatesContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#aggregates}.
	 * @param ctx the parse tree
	 */
	void exitAggregates(HdbxsodataParser.AggregatesContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#aggregatestuple}.
	 * @param ctx the parse tree
	 */
	void enterAggregatestuple(HdbxsodataParser.AggregatestupleContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#aggregatestuple}.
	 * @param ctx the parse tree
	 */
	void exitAggregatestuple(HdbxsodataParser.AggregatestupleContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#aggregate}.
	 * @param ctx the parse tree
	 */
	void enterAggregate(HdbxsodataParser.AggregateContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#aggregate}.
	 * @param ctx the parse tree
	 */
	void exitAggregate(HdbxsodataParser.AggregateContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#aggregatefunction}.
	 * @param ctx the parse tree
	 */
	void enterAggregatefunction(HdbxsodataParser.AggregatefunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#aggregatefunction}.
	 * @param ctx the parse tree
	 */
	void exitAggregatefunction(HdbxsodataParser.AggregatefunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#parameters}.
	 * @param ctx the parse tree
	 */
	void enterParameters(HdbxsodataParser.ParametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#parameters}.
	 * @param ctx the parse tree
	 */
	void exitParameters(HdbxsodataParser.ParametersContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#parameterskeyand}.
	 * @param ctx the parse tree
	 */
	void enterParameterskeyand(HdbxsodataParser.ParameterskeyandContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#parameterskeyand}.
	 * @param ctx the parse tree
	 */
	void exitParameterskeyand(HdbxsodataParser.ParameterskeyandContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#parameterentitysetname}.
	 * @param ctx the parse tree
	 */
	void enterParameterentitysetname(HdbxsodataParser.ParameterentitysetnameContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#parameterentitysetname}.
	 * @param ctx the parse tree
	 */
	void exitParameterentitysetname(HdbxsodataParser.ParameterentitysetnameContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#parametersresultsprop}.
	 * @param ctx the parse tree
	 */
	void enterParametersresultsprop(HdbxsodataParser.ParametersresultspropContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#parametersresultsprop}.
	 * @param ctx the parse tree
	 */
	void exitParametersresultsprop(HdbxsodataParser.ParametersresultspropContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#modificationBody}.
	 * @param ctx the parse tree
	 */
	void enterModificationBody(HdbxsodataParser.ModificationBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#modificationBody}.
	 * @param ctx the parse tree
	 */
	void exitModificationBody(HdbxsodataParser.ModificationBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#modification}.
	 * @param ctx the parse tree
	 */
	void enterModification(HdbxsodataParser.ModificationContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#modification}.
	 * @param ctx the parse tree
	 */
	void exitModification(HdbxsodataParser.ModificationContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#create}.
	 * @param ctx the parse tree
	 */
	void enterCreate(HdbxsodataParser.CreateContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#create}.
	 * @param ctx the parse tree
	 */
	void exitCreate(HdbxsodataParser.CreateContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#update}.
	 * @param ctx the parse tree
	 */
	void enterUpdate(HdbxsodataParser.UpdateContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#update}.
	 * @param ctx the parse tree
	 */
	void exitUpdate(HdbxsodataParser.UpdateContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#delete}.
	 * @param ctx the parse tree
	 */
	void enterDelete(HdbxsodataParser.DeleteContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#delete}.
	 * @param ctx the parse tree
	 */
	void exitDelete(HdbxsodataParser.DeleteContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#modificationspec}.
	 * @param ctx the parse tree
	 */
	void enterModificationspec(HdbxsodataParser.ModificationspecContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#modificationspec}.
	 * @param ctx the parse tree
	 */
	void exitModificationspec(HdbxsodataParser.ModificationspecContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#modificationaction}.
	 * @param ctx the parse tree
	 */
	void enterModificationaction(HdbxsodataParser.ModificationactionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#modificationaction}.
	 * @param ctx the parse tree
	 */
	void exitModificationaction(HdbxsodataParser.ModificationactionContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#forbidden}.
	 * @param ctx the parse tree
	 */
	void enterForbidden(HdbxsodataParser.ForbiddenContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#forbidden}.
	 * @param ctx the parse tree
	 */
	void exitForbidden(HdbxsodataParser.ForbiddenContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#action}.
	 * @param ctx the parse tree
	 */
	void enterAction(HdbxsodataParser.ActionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#action}.
	 * @param ctx the parse tree
	 */
	void exitAction(HdbxsodataParser.ActionContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#events}.
	 * @param ctx the parse tree
	 */
	void enterEvents(HdbxsodataParser.EventsContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#events}.
	 * @param ctx the parse tree
	 */
	void exitEvents(HdbxsodataParser.EventsContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#eventlist}.
	 * @param ctx the parse tree
	 */
	void enterEventlist(HdbxsodataParser.EventlistContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#eventlist}.
	 * @param ctx the parse tree
	 */
	void exitEventlist(HdbxsodataParser.EventlistContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#eventlistElement}.
	 * @param ctx the parse tree
	 */
	void enterEventlistElement(HdbxsodataParser.EventlistElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#eventlistElement}.
	 * @param ctx the parse tree
	 */
	void exitEventlistElement(HdbxsodataParser.EventlistElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#eventtype}.
	 * @param ctx the parse tree
	 */
	void enterEventtype(HdbxsodataParser.EventtypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#eventtype}.
	 * @param ctx the parse tree
	 */
	void exitEventtype(HdbxsodataParser.EventtypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#association}.
	 * @param ctx the parse tree
	 */
	void enterAssociation(HdbxsodataParser.AssociationContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#association}.
	 * @param ctx the parse tree
	 */
	void exitAssociation(HdbxsodataParser.AssociationContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#associationdef}.
	 * @param ctx the parse tree
	 */
	void enterAssociationdef(HdbxsodataParser.AssociationdefContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#associationdef}.
	 * @param ctx the parse tree
	 */
	void exitAssociationdef(HdbxsodataParser.AssociationdefContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#assocrefconstraint}.
	 * @param ctx the parse tree
	 */
	void enterAssocrefconstraint(HdbxsodataParser.AssocrefconstraintContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#assocrefconstraint}.
	 * @param ctx the parse tree
	 */
	void exitAssocrefconstraint(HdbxsodataParser.AssocrefconstraintContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#principalend}.
	 * @param ctx the parse tree
	 */
	void enterPrincipalend(HdbxsodataParser.PrincipalendContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#principalend}.
	 * @param ctx the parse tree
	 */
	void exitPrincipalend(HdbxsodataParser.PrincipalendContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#dependentend}.
	 * @param ctx the parse tree
	 */
	void enterDependentend(HdbxsodataParser.DependentendContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#dependentend}.
	 * @param ctx the parse tree
	 */
	void exitDependentend(HdbxsodataParser.DependentendContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#end}.
	 * @param ctx the parse tree
	 */
	void enterEnd(HdbxsodataParser.EndContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#end}.
	 * @param ctx the parse tree
	 */
	void exitEnd(HdbxsodataParser.EndContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#endref}.
	 * @param ctx the parse tree
	 */
	void enterEndref(HdbxsodataParser.EndrefContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#endref}.
	 * @param ctx the parse tree
	 */
	void exitEndref(HdbxsodataParser.EndrefContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#endtype}.
	 * @param ctx the parse tree
	 */
	void enterEndtype(HdbxsodataParser.EndtypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#endtype}.
	 * @param ctx the parse tree
	 */
	void exitEndtype(HdbxsodataParser.EndtypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#joinpropertieslist}.
	 * @param ctx the parse tree
	 */
	void enterJoinpropertieslist(HdbxsodataParser.JoinpropertieslistContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#joinpropertieslist}.
	 * @param ctx the parse tree
	 */
	void exitJoinpropertieslist(HdbxsodataParser.JoinpropertieslistContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#multiplicity}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicity(HdbxsodataParser.MultiplicityContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#multiplicity}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicity(HdbxsodataParser.MultiplicityContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#multiplicityvalue}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicityvalue(HdbxsodataParser.MultiplicityvalueContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#multiplicityvalue}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicityvalue(HdbxsodataParser.MultiplicityvalueContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#assoctable}.
	 * @param ctx the parse tree
	 */
	void enterAssoctable(HdbxsodataParser.AssoctableContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#assoctable}.
	 * @param ctx the parse tree
	 */
	void exitAssoctable(HdbxsodataParser.AssoctableContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#repoobject}.
	 * @param ctx the parse tree
	 */
	void enterRepoobject(HdbxsodataParser.RepoobjectContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#repoobject}.
	 * @param ctx the parse tree
	 */
	void exitRepoobject(HdbxsodataParser.RepoobjectContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#overprincipalend}.
	 * @param ctx the parse tree
	 */
	void enterOverprincipalend(HdbxsodataParser.OverprincipalendContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#overprincipalend}.
	 * @param ctx the parse tree
	 */
	void exitOverprincipalend(HdbxsodataParser.OverprincipalendContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#overdependentend}.
	 * @param ctx the parse tree
	 */
	void enterOverdependentend(HdbxsodataParser.OverdependentendContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#overdependentend}.
	 * @param ctx the parse tree
	 */
	void exitOverdependentend(HdbxsodataParser.OverdependentendContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#overend}.
	 * @param ctx the parse tree
	 */
	void enterOverend(HdbxsodataParser.OverendContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#overend}.
	 * @param ctx the parse tree
	 */
	void exitOverend(HdbxsodataParser.OverendContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#storage}.
	 * @param ctx the parse tree
	 */
	void enterStorage(HdbxsodataParser.StorageContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#storage}.
	 * @param ctx the parse tree
	 */
	void exitStorage(HdbxsodataParser.StorageContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#nostorage}.
	 * @param ctx the parse tree
	 */
	void enterNostorage(HdbxsodataParser.NostorageContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#nostorage}.
	 * @param ctx the parse tree
	 */
	void exitNostorage(HdbxsodataParser.NostorageContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#storageend}.
	 * @param ctx the parse tree
	 */
	void enterStorageend(HdbxsodataParser.StorageendContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#storageend}.
	 * @param ctx the parse tree
	 */
	void exitStorageend(HdbxsodataParser.StorageendContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#annotations}.
	 * @param ctx the parse tree
	 */
	void enterAnnotations(HdbxsodataParser.AnnotationsContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#annotations}.
	 * @param ctx the parse tree
	 */
	void exitAnnotations(HdbxsodataParser.AnnotationsContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#annotationsbody}.
	 * @param ctx the parse tree
	 */
	void enterAnnotationsbody(HdbxsodataParser.AnnotationsbodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#annotationsbody}.
	 * @param ctx the parse tree
	 */
	void exitAnnotationsbody(HdbxsodataParser.AnnotationsbodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#annotationconfig}.
	 * @param ctx the parse tree
	 */
	void enterAnnotationconfig(HdbxsodataParser.AnnotationconfigContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#annotationconfig}.
	 * @param ctx the parse tree
	 */
	void exitAnnotationconfig(HdbxsodataParser.AnnotationconfigContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#settings}.
	 * @param ctx the parse tree
	 */
	void enterSettings(HdbxsodataParser.SettingsContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#settings}.
	 * @param ctx the parse tree
	 */
	void exitSettings(HdbxsodataParser.SettingsContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#settingsbody}.
	 * @param ctx the parse tree
	 */
	void enterSettingsbody(HdbxsodataParser.SettingsbodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#settingsbody}.
	 * @param ctx the parse tree
	 */
	void exitSettingsbody(HdbxsodataParser.SettingsbodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#settingselement}.
	 * @param ctx the parse tree
	 */
	void enterSettingselement(HdbxsodataParser.SettingselementContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#settingselement}.
	 * @param ctx the parse tree
	 */
	void exitSettingselement(HdbxsodataParser.SettingselementContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#supportnull}.
	 * @param ctx the parse tree
	 */
	void enterSupportnull(HdbxsodataParser.SupportnullContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#supportnull}.
	 * @param ctx the parse tree
	 */
	void exitSupportnull(HdbxsodataParser.SupportnullContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#contentcashecontrol}.
	 * @param ctx the parse tree
	 */
	void enterContentcashecontrol(HdbxsodataParser.ContentcashecontrolContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#contentcashecontrol}.
	 * @param ctx the parse tree
	 */
	void exitContentcashecontrol(HdbxsodataParser.ContentcashecontrolContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#metadatacashecontrol}.
	 * @param ctx the parse tree
	 */
	void enterMetadatacashecontrol(HdbxsodataParser.MetadatacashecontrolContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#metadatacashecontrol}.
	 * @param ctx the parse tree
	 */
	void exitMetadatacashecontrol(HdbxsodataParser.MetadatacashecontrolContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#hints}.
	 * @param ctx the parse tree
	 */
	void enterHints(HdbxsodataParser.HintsContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#hints}.
	 * @param ctx the parse tree
	 */
	void exitHints(HdbxsodataParser.HintsContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#hintlist}.
	 * @param ctx the parse tree
	 */
	void enterHintlist(HdbxsodataParser.HintlistContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#hintlist}.
	 * @param ctx the parse tree
	 */
	void exitHintlist(HdbxsodataParser.HintlistContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#hintvalue}.
	 * @param ctx the parse tree
	 */
	void enterHintvalue(HdbxsodataParser.HintvalueContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#hintvalue}.
	 * @param ctx the parse tree
	 */
	void exitHintvalue(HdbxsodataParser.HintvalueContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#nullvalue}.
	 * @param ctx the parse tree
	 */
	void enterNullvalue(HdbxsodataParser.NullvalueContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#nullvalue}.
	 * @param ctx the parse tree
	 */
	void exitNullvalue(HdbxsodataParser.NullvalueContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#limits}.
	 * @param ctx the parse tree
	 */
	void enterLimits(HdbxsodataParser.LimitsContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#limits}.
	 * @param ctx the parse tree
	 */
	void exitLimits(HdbxsodataParser.LimitsContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#limitvalue}.
	 * @param ctx the parse tree
	 */
	void enterLimitvalue(HdbxsodataParser.LimitvalueContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#limitvalue}.
	 * @param ctx the parse tree
	 */
	void exitLimitvalue(HdbxsodataParser.LimitvalueContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#maxrecords}.
	 * @param ctx the parse tree
	 */
	void enterMaxrecords(HdbxsodataParser.MaxrecordsContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#maxrecords}.
	 * @param ctx the parse tree
	 */
	void exitMaxrecords(HdbxsodataParser.MaxrecordsContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#maxexpandedrecords}.
	 * @param ctx the parse tree
	 */
	void enterMaxexpandedrecords(HdbxsodataParser.MaxexpandedrecordsContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#maxexpandedrecords}.
	 * @param ctx the parse tree
	 */
	void exitMaxexpandedrecords(HdbxsodataParser.MaxexpandedrecordsContext ctx);
}