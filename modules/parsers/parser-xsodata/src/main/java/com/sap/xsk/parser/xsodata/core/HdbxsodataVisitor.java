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
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link HdbxsodataParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface HdbxsodataVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#xsodataDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXsodataDefinition(HdbxsodataParser.XsodataDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#service}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitService(HdbxsodataParser.ServiceContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#namespace}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNamespace(HdbxsodataParser.NamespaceContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBody(HdbxsodataParser.BodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#content}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContent(HdbxsodataParser.ContentContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#entry}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEntry(HdbxsodataParser.EntryContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#entity}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEntity(HdbxsodataParser.EntityContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#object}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObject(HdbxsodataParser.ObjectContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#catalogobjectschema}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCatalogobjectschema(HdbxsodataParser.CatalogobjectschemaContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#catalogobjectname}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCatalogobjectname(HdbxsodataParser.CatalogobjectnameContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#entityset}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEntityset(HdbxsodataParser.EntitysetContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#entitysetname}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEntitysetname(HdbxsodataParser.EntitysetnameContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#with}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWith(HdbxsodataParser.WithContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#withProp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWithProp(HdbxsodataParser.WithPropContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#withoutProp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWithoutProp(HdbxsodataParser.WithoutPropContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#propertylist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPropertylist(HdbxsodataParser.PropertylistContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#columnname}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumnname(HdbxsodataParser.ColumnnameContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#keys}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeys(HdbxsodataParser.KeysContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#keylist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeylist(HdbxsodataParser.KeylistContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#keygenerated}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeygenerated(HdbxsodataParser.KeygeneratedContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#concurrencytoken}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConcurrencytoken(HdbxsodataParser.ConcurrencytokenContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#navigates}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNavigates(HdbxsodataParser.NavigatesContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#navlist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNavlist(HdbxsodataParser.NavlistContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#naventry}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNaventry(HdbxsodataParser.NaventryContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#assocname}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssocname(HdbxsodataParser.AssocnameContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#navpropname}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNavpropname(HdbxsodataParser.NavpropnameContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#fromend}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFromend(HdbxsodataParser.FromendContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#principal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrincipal(HdbxsodataParser.PrincipalContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#dependent}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDependent(HdbxsodataParser.DependentContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#aggregates}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAggregates(HdbxsodataParser.AggregatesContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#aggregatestuple}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAggregatestuple(HdbxsodataParser.AggregatestupleContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#aggregate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAggregate(HdbxsodataParser.AggregateContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#aggregatefunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAggregatefunction(HdbxsodataParser.AggregatefunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#parameters}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameters(HdbxsodataParser.ParametersContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#parameterskeyand}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterskeyand(HdbxsodataParser.ParameterskeyandContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#parameterentitysetname}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterentitysetname(HdbxsodataParser.ParameterentitysetnameContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#parametersresultsprop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParametersresultsprop(HdbxsodataParser.ParametersresultspropContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#modificationBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModificationBody(HdbxsodataParser.ModificationBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#modification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModification(HdbxsodataParser.ModificationContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#create}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreate(HdbxsodataParser.CreateContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#update}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUpdate(HdbxsodataParser.UpdateContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#delete}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDelete(HdbxsodataParser.DeleteContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#modificationspec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModificationspec(HdbxsodataParser.ModificationspecContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#modificationaction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModificationaction(HdbxsodataParser.ModificationactionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#forbidden}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForbidden(HdbxsodataParser.ForbiddenContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#action}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAction(HdbxsodataParser.ActionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#events}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEvents(HdbxsodataParser.EventsContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#eventlist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEventlist(HdbxsodataParser.EventlistContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#eventlistElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEventlistElement(HdbxsodataParser.EventlistElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#eventtype}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEventtype(HdbxsodataParser.EventtypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#association}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssociation(HdbxsodataParser.AssociationContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#associationdef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssociationdef(HdbxsodataParser.AssociationdefContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#assocrefconstraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssocrefconstraint(HdbxsodataParser.AssocrefconstraintContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#principalend}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrincipalend(HdbxsodataParser.PrincipalendContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#dependentend}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDependentend(HdbxsodataParser.DependentendContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#end}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnd(HdbxsodataParser.EndContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#endref}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEndref(HdbxsodataParser.EndrefContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#endtype}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEndtype(HdbxsodataParser.EndtypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#joinpropertieslist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJoinpropertieslist(HdbxsodataParser.JoinpropertieslistContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#multiplicity}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicity(HdbxsodataParser.MultiplicityContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#multiplicityvalue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicityvalue(HdbxsodataParser.MultiplicityvalueContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#assoctable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssoctable(HdbxsodataParser.AssoctableContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#repoobject}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRepoobject(HdbxsodataParser.RepoobjectContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#overprincipalend}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOverprincipalend(HdbxsodataParser.OverprincipalendContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#overdependentend}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOverdependentend(HdbxsodataParser.OverdependentendContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#overend}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOverend(HdbxsodataParser.OverendContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#storage}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStorage(HdbxsodataParser.StorageContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#nostorage}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNostorage(HdbxsodataParser.NostorageContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#storageend}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStorageend(HdbxsodataParser.StorageendContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#annotations}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnnotations(HdbxsodataParser.AnnotationsContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#annotationsbody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnnotationsbody(HdbxsodataParser.AnnotationsbodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#annotationconfig}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnnotationconfig(HdbxsodataParser.AnnotationconfigContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#settings}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSettings(HdbxsodataParser.SettingsContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#settingsbody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSettingsbody(HdbxsodataParser.SettingsbodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#settingselement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSettingselement(HdbxsodataParser.SettingselementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#supportnull}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSupportnull(HdbxsodataParser.SupportnullContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#contentcashecontrol}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContentcashecontrol(HdbxsodataParser.ContentcashecontrolContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#metadatacashecontrol}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMetadatacashecontrol(HdbxsodataParser.MetadatacashecontrolContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#hints}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHints(HdbxsodataParser.HintsContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#hintlist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHintlist(HdbxsodataParser.HintlistContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#hintvalue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHintvalue(HdbxsodataParser.HintvalueContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#nullvalue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNullvalue(HdbxsodataParser.NullvalueContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#limits}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLimits(HdbxsodataParser.LimitsContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#limitvalue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLimitvalue(HdbxsodataParser.LimitvalueContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#maxrecords}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMaxrecords(HdbxsodataParser.MaxrecordsContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#maxexpandedrecords}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMaxexpandedrecords(HdbxsodataParser.MaxexpandedrecordsContext ctx);
}