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
// Generated from com/sap/xsk/parser/xsodata/core/Hdbxsodata.g4 by ANTLR 4.3
package com.sap.xsk.parser.xsodata.core;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link HdbxsodataParser}.
 */
public interface HdbxsodataListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#multiplicity}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicity(@NotNull HdbxsodataParser.MultiplicityContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#multiplicity}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicity(@NotNull HdbxsodataParser.MultiplicityContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#assocrefconstraint}.
	 * @param ctx the parse tree
	 */
	void enterAssocrefconstraint(@NotNull HdbxsodataParser.AssocrefconstraintContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#assocrefconstraint}.
	 * @param ctx the parse tree
	 */
	void exitAssocrefconstraint(@NotNull HdbxsodataParser.AssocrefconstraintContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#storageend}.
	 * @param ctx the parse tree
	 */
	void enterStorageend(@NotNull HdbxsodataParser.StorageendContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#storageend}.
	 * @param ctx the parse tree
	 */
	void exitStorageend(@NotNull HdbxsodataParser.StorageendContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#annotations}.
	 * @param ctx the parse tree
	 */
	void enterAnnotations(@NotNull HdbxsodataParser.AnnotationsContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#annotations}.
	 * @param ctx the parse tree
	 */
	void exitAnnotations(@NotNull HdbxsodataParser.AnnotationsContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#body}.
	 * @param ctx the parse tree
	 */
	void enterBody(@NotNull HdbxsodataParser.BodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#body}.
	 * @param ctx the parse tree
	 */
	void exitBody(@NotNull HdbxsodataParser.BodyContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#keylist}.
	 * @param ctx the parse tree
	 */
	void enterKeylist(@NotNull HdbxsodataParser.KeylistContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#keylist}.
	 * @param ctx the parse tree
	 */
	void exitKeylist(@NotNull HdbxsodataParser.KeylistContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#parameterskeyand}.
	 * @param ctx the parse tree
	 */
	void enterParameterskeyand(@NotNull HdbxsodataParser.ParameterskeyandContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#parameterskeyand}.
	 * @param ctx the parse tree
	 */
	void exitParameterskeyand(@NotNull HdbxsodataParser.ParameterskeyandContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#associationdef}.
	 * @param ctx the parse tree
	 */
	void enterAssociationdef(@NotNull HdbxsodataParser.AssociationdefContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#associationdef}.
	 * @param ctx the parse tree
	 */
	void exitAssociationdef(@NotNull HdbxsodataParser.AssociationdefContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#joinpropertieslist}.
	 * @param ctx the parse tree
	 */
	void enterJoinpropertieslist(@NotNull HdbxsodataParser.JoinpropertieslistContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#joinpropertieslist}.
	 * @param ctx the parse tree
	 */
	void exitJoinpropertieslist(@NotNull HdbxsodataParser.JoinpropertieslistContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#aggregatefunction}.
	 * @param ctx the parse tree
	 */
	void enterAggregatefunction(@NotNull HdbxsodataParser.AggregatefunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#aggregatefunction}.
	 * @param ctx the parse tree
	 */
	void exitAggregatefunction(@NotNull HdbxsodataParser.AggregatefunctionContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#hintlist}.
	 * @param ctx the parse tree
	 */
	void enterHintlist(@NotNull HdbxsodataParser.HintlistContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#hintlist}.
	 * @param ctx the parse tree
	 */
	void exitHintlist(@NotNull HdbxsodataParser.HintlistContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#create}.
	 * @param ctx the parse tree
	 */
	void enterCreate(@NotNull HdbxsodataParser.CreateContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#create}.
	 * @param ctx the parse tree
	 */
	void exitCreate(@NotNull HdbxsodataParser.CreateContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#action}.
	 * @param ctx the parse tree
	 */
	void enterAction(@NotNull HdbxsodataParser.ActionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#action}.
	 * @param ctx the parse tree
	 */
	void exitAction(@NotNull HdbxsodataParser.ActionContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#maxrecords}.
	 * @param ctx the parse tree
	 */
	void enterMaxrecords(@NotNull HdbxsodataParser.MaxrecordsContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#maxrecords}.
	 * @param ctx the parse tree
	 */
	void exitMaxrecords(@NotNull HdbxsodataParser.MaxrecordsContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#overdependentend}.
	 * @param ctx the parse tree
	 */
	void enterOverdependentend(@NotNull HdbxsodataParser.OverdependentendContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#overdependentend}.
	 * @param ctx the parse tree
	 */
	void exitOverdependentend(@NotNull HdbxsodataParser.OverdependentendContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#parameterentitysetname}.
	 * @param ctx the parse tree
	 */
	void enterParameterentitysetname(@NotNull HdbxsodataParser.ParameterentitysetnameContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#parameterentitysetname}.
	 * @param ctx the parse tree
	 */
	void exitParameterentitysetname(@NotNull HdbxsodataParser.ParameterentitysetnameContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#dependent}.
	 * @param ctx the parse tree
	 */
	void enterDependent(@NotNull HdbxsodataParser.DependentContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#dependent}.
	 * @param ctx the parse tree
	 */
	void exitDependent(@NotNull HdbxsodataParser.DependentContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#modificationaction}.
	 * @param ctx the parse tree
	 */
	void enterModificationaction(@NotNull HdbxsodataParser.ModificationactionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#modificationaction}.
	 * @param ctx the parse tree
	 */
	void exitModificationaction(@NotNull HdbxsodataParser.ModificationactionContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#events}.
	 * @param ctx the parse tree
	 */
	void enterEvents(@NotNull HdbxsodataParser.EventsContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#events}.
	 * @param ctx the parse tree
	 */
	void exitEvents(@NotNull HdbxsodataParser.EventsContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#nostorage}.
	 * @param ctx the parse tree
	 */
	void enterNostorage(@NotNull HdbxsodataParser.NostorageContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#nostorage}.
	 * @param ctx the parse tree
	 */
	void exitNostorage(@NotNull HdbxsodataParser.NostorageContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#nullvalue}.
	 * @param ctx the parse tree
	 */
	void enterNullvalue(@NotNull HdbxsodataParser.NullvalueContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#nullvalue}.
	 * @param ctx the parse tree
	 */
	void exitNullvalue(@NotNull HdbxsodataParser.NullvalueContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#settings}.
	 * @param ctx the parse tree
	 */
	void enterSettings(@NotNull HdbxsodataParser.SettingsContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#settings}.
	 * @param ctx the parse tree
	 */
	void exitSettings(@NotNull HdbxsodataParser.SettingsContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#withProp}.
	 * @param ctx the parse tree
	 */
	void enterWithProp(@NotNull HdbxsodataParser.WithPropContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#withProp}.
	 * @param ctx the parse tree
	 */
	void exitWithProp(@NotNull HdbxsodataParser.WithPropContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#withoutProp}.
	 * @param ctx the parse tree
	 */
	void enterWithoutProp(@NotNull HdbxsodataParser.WithoutPropContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#withoutProp}.
	 * @param ctx the parse tree
	 */
	void exitWithoutProp(@NotNull HdbxsodataParser.WithoutPropContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#settingsbody}.
	 * @param ctx the parse tree
	 */
	void enterSettingsbody(@NotNull HdbxsodataParser.SettingsbodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#settingsbody}.
	 * @param ctx the parse tree
	 */
	void exitSettingsbody(@NotNull HdbxsodataParser.SettingsbodyContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#catalogobjectschema}.
	 * @param ctx the parse tree
	 */
	void enterCatalogobjectschema(@NotNull HdbxsodataParser.CatalogobjectschemaContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#catalogobjectschema}.
	 * @param ctx the parse tree
	 */
	void exitCatalogobjectschema(@NotNull HdbxsodataParser.CatalogobjectschemaContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#hints}.
	 * @param ctx the parse tree
	 */
	void enterHints(@NotNull HdbxsodataParser.HintsContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#hints}.
	 * @param ctx the parse tree
	 */
	void exitHints(@NotNull HdbxsodataParser.HintsContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#settingselement}.
	 * @param ctx the parse tree
	 */
	void enterSettingselement(@NotNull HdbxsodataParser.SettingselementContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#settingselement}.
	 * @param ctx the parse tree
	 */
	void exitSettingselement(@NotNull HdbxsodataParser.SettingselementContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#aggregatestuple}.
	 * @param ctx the parse tree
	 */
	void enterAggregatestuple(@NotNull HdbxsodataParser.AggregatestupleContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#aggregatestuple}.
	 * @param ctx the parse tree
	 */
	void exitAggregatestuple(@NotNull HdbxsodataParser.AggregatestupleContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#modification}.
	 * @param ctx the parse tree
	 */
	void enterModification(@NotNull HdbxsodataParser.ModificationContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#modification}.
	 * @param ctx the parse tree
	 */
	void exitModification(@NotNull HdbxsodataParser.ModificationContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#contentcashecontrol}.
	 * @param ctx the parse tree
	 */
	void enterContentcashecontrol(@NotNull HdbxsodataParser.ContentcashecontrolContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#contentcashecontrol}.
	 * @param ctx the parse tree
	 */
	void exitContentcashecontrol(@NotNull HdbxsodataParser.ContentcashecontrolContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#principalend}.
	 * @param ctx the parse tree
	 */
	void enterPrincipalend(@NotNull HdbxsodataParser.PrincipalendContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#principalend}.
	 * @param ctx the parse tree
	 */
	void exitPrincipalend(@NotNull HdbxsodataParser.PrincipalendContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#entry}.
	 * @param ctx the parse tree
	 */
	void enterEntry(@NotNull HdbxsodataParser.EntryContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#entry}.
	 * @param ctx the parse tree
	 */
	void exitEntry(@NotNull HdbxsodataParser.EntryContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#assocname}.
	 * @param ctx the parse tree
	 */
	void enterAssocname(@NotNull HdbxsodataParser.AssocnameContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#assocname}.
	 * @param ctx the parse tree
	 */
	void exitAssocname(@NotNull HdbxsodataParser.AssocnameContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#naventry}.
	 * @param ctx the parse tree
	 */
	void enterNaventry(@NotNull HdbxsodataParser.NaventryContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#naventry}.
	 * @param ctx the parse tree
	 */
	void exitNaventry(@NotNull HdbxsodataParser.NaventryContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#metadatacashecontrol}.
	 * @param ctx the parse tree
	 */
	void enterMetadatacashecontrol(@NotNull HdbxsodataParser.MetadatacashecontrolContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#metadatacashecontrol}.
	 * @param ctx the parse tree
	 */
	void exitMetadatacashecontrol(@NotNull HdbxsodataParser.MetadatacashecontrolContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#entitysetname}.
	 * @param ctx the parse tree
	 */
	void enterEntitysetname(@NotNull HdbxsodataParser.EntitysetnameContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#entitysetname}.
	 * @param ctx the parse tree
	 */
	void exitEntitysetname(@NotNull HdbxsodataParser.EntitysetnameContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#multiplicityvalue}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicityvalue(@NotNull HdbxsodataParser.MultiplicityvalueContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#multiplicityvalue}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicityvalue(@NotNull HdbxsodataParser.MultiplicityvalueContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#aggregates}.
	 * @param ctx the parse tree
	 */
	void enterAggregates(@NotNull HdbxsodataParser.AggregatesContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#aggregates}.
	 * @param ctx the parse tree
	 */
	void exitAggregates(@NotNull HdbxsodataParser.AggregatesContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#eventtype}.
	 * @param ctx the parse tree
	 */
	void enterEventtype(@NotNull HdbxsodataParser.EventtypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#eventtype}.
	 * @param ctx the parse tree
	 */
	void exitEventtype(@NotNull HdbxsodataParser.EventtypeContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#overprincipalend}.
	 * @param ctx the parse tree
	 */
	void enterOverprincipalend(@NotNull HdbxsodataParser.OverprincipalendContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#overprincipalend}.
	 * @param ctx the parse tree
	 */
	void exitOverprincipalend(@NotNull HdbxsodataParser.OverprincipalendContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#parameters}.
	 * @param ctx the parse tree
	 */
	void enterParameters(@NotNull HdbxsodataParser.ParametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#parameters}.
	 * @param ctx the parse tree
	 */
	void exitParameters(@NotNull HdbxsodataParser.ParametersContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#repoobject}.
	 * @param ctx the parse tree
	 */
	void enterRepoobject(@NotNull HdbxsodataParser.RepoobjectContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#repoobject}.
	 * @param ctx the parse tree
	 */
	void exitRepoobject(@NotNull HdbxsodataParser.RepoobjectContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#object}.
	 * @param ctx the parse tree
	 */
	void enterObject(@NotNull HdbxsodataParser.ObjectContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#object}.
	 * @param ctx the parse tree
	 */
	void exitObject(@NotNull HdbxsodataParser.ObjectContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#entityset}.
	 * @param ctx the parse tree
	 */
	void enterEntityset(@NotNull HdbxsodataParser.EntitysetContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#entityset}.
	 * @param ctx the parse tree
	 */
	void exitEntityset(@NotNull HdbxsodataParser.EntitysetContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#parametersresultsprop}.
	 * @param ctx the parse tree
	 */
	void enterParametersresultsprop(@NotNull HdbxsodataParser.ParametersresultspropContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#parametersresultsprop}.
	 * @param ctx the parse tree
	 */
	void exitParametersresultsprop(@NotNull HdbxsodataParser.ParametersresultspropContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#keys}.
	 * @param ctx the parse tree
	 */
	void enterKeys(@NotNull HdbxsodataParser.KeysContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#keys}.
	 * @param ctx the parse tree
	 */
	void exitKeys(@NotNull HdbxsodataParser.KeysContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#modificationBody}.
	 * @param ctx the parse tree
	 */
	void enterModificationBody(@NotNull HdbxsodataParser.ModificationBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#modificationBody}.
	 * @param ctx the parse tree
	 */
	void exitModificationBody(@NotNull HdbxsodataParser.ModificationBodyContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#update}.
	 * @param ctx the parse tree
	 */
	void enterUpdate(@NotNull HdbxsodataParser.UpdateContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#update}.
	 * @param ctx the parse tree
	 */
	void exitUpdate(@NotNull HdbxsodataParser.UpdateContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#association}.
	 * @param ctx the parse tree
	 */
	void enterAssociation(@NotNull HdbxsodataParser.AssociationContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#association}.
	 * @param ctx the parse tree
	 */
	void exitAssociation(@NotNull HdbxsodataParser.AssociationContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#annotationconfig}.
	 * @param ctx the parse tree
	 */
	void enterAnnotationconfig(@NotNull HdbxsodataParser.AnnotationconfigContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#annotationconfig}.
	 * @param ctx the parse tree
	 */
	void exitAnnotationconfig(@NotNull HdbxsodataParser.AnnotationconfigContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#storage}.
	 * @param ctx the parse tree
	 */
	void enterStorage(@NotNull HdbxsodataParser.StorageContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#storage}.
	 * @param ctx the parse tree
	 */
	void exitStorage(@NotNull HdbxsodataParser.StorageContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#delete}.
	 * @param ctx the parse tree
	 */
	void enterDelete(@NotNull HdbxsodataParser.DeleteContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#delete}.
	 * @param ctx the parse tree
	 */
	void exitDelete(@NotNull HdbxsodataParser.DeleteContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#content}.
	 * @param ctx the parse tree
	 */
	void enterContent(@NotNull HdbxsodataParser.ContentContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#content}.
	 * @param ctx the parse tree
	 */
	void exitContent(@NotNull HdbxsodataParser.ContentContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#endref}.
	 * @param ctx the parse tree
	 */
	void enterEndref(@NotNull HdbxsodataParser.EndrefContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#endref}.
	 * @param ctx the parse tree
	 */
	void exitEndref(@NotNull HdbxsodataParser.EndrefContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#aggregate}.
	 * @param ctx the parse tree
	 */
	void enterAggregate(@NotNull HdbxsodataParser.AggregateContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#aggregate}.
	 * @param ctx the parse tree
	 */
	void exitAggregate(@NotNull HdbxsodataParser.AggregateContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#principal}.
	 * @param ctx the parse tree
	 */
	void enterPrincipal(@NotNull HdbxsodataParser.PrincipalContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#principal}.
	 * @param ctx the parse tree
	 */
	void exitPrincipal(@NotNull HdbxsodataParser.PrincipalContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#eventlist}.
	 * @param ctx the parse tree
	 */
	void enterEventlist(@NotNull HdbxsodataParser.EventlistContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#eventlist}.
	 * @param ctx the parse tree
	 */
	void exitEventlist(@NotNull HdbxsodataParser.EventlistContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#assoctable}.
	 * @param ctx the parse tree
	 */
	void enterAssoctable(@NotNull HdbxsodataParser.AssoctableContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#assoctable}.
	 * @param ctx the parse tree
	 */
	void exitAssoctable(@NotNull HdbxsodataParser.AssoctableContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#endtype}.
	 * @param ctx the parse tree
	 */
	void enterEndtype(@NotNull HdbxsodataParser.EndtypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#endtype}.
	 * @param ctx the parse tree
	 */
	void exitEndtype(@NotNull HdbxsodataParser.EndtypeContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#end}.
	 * @param ctx the parse tree
	 */
	void enterEnd(@NotNull HdbxsodataParser.EndContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#end}.
	 * @param ctx the parse tree
	 */
	void exitEnd(@NotNull HdbxsodataParser.EndContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#keygenerated}.
	 * @param ctx the parse tree
	 */
	void enterKeygenerated(@NotNull HdbxsodataParser.KeygeneratedContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#keygenerated}.
	 * @param ctx the parse tree
	 */
	void exitKeygenerated(@NotNull HdbxsodataParser.KeygeneratedContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#eventlistElement}.
	 * @param ctx the parse tree
	 */
	void enterEventlistElement(@NotNull HdbxsodataParser.EventlistElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#eventlistElement}.
	 * @param ctx the parse tree
	 */
	void exitEventlistElement(@NotNull HdbxsodataParser.EventlistElementContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#concurrencytoken}.
	 * @param ctx the parse tree
	 */
	void enterConcurrencytoken(@NotNull HdbxsodataParser.ConcurrencytokenContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#concurrencytoken}.
	 * @param ctx the parse tree
	 */
	void exitConcurrencytoken(@NotNull HdbxsodataParser.ConcurrencytokenContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#limits}.
	 * @param ctx the parse tree
	 */
	void enterLimits(@NotNull HdbxsodataParser.LimitsContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#limits}.
	 * @param ctx the parse tree
	 */
	void exitLimits(@NotNull HdbxsodataParser.LimitsContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#xsodataDefinition}.
	 * @param ctx the parse tree
	 */
	void enterXsodataDefinition(@NotNull HdbxsodataParser.XsodataDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#xsodataDefinition}.
	 * @param ctx the parse tree
	 */
	void exitXsodataDefinition(@NotNull HdbxsodataParser.XsodataDefinitionContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#fromend}.
	 * @param ctx the parse tree
	 */
	void enterFromend(@NotNull HdbxsodataParser.FromendContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#fromend}.
	 * @param ctx the parse tree
	 */
	void exitFromend(@NotNull HdbxsodataParser.FromendContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#navlist}.
	 * @param ctx the parse tree
	 */
	void enterNavlist(@NotNull HdbxsodataParser.NavlistContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#navlist}.
	 * @param ctx the parse tree
	 */
	void exitNavlist(@NotNull HdbxsodataParser.NavlistContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#navpropname}.
	 * @param ctx the parse tree
	 */
	void enterNavpropname(@NotNull HdbxsodataParser.NavpropnameContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#navpropname}.
	 * @param ctx the parse tree
	 */
	void exitNavpropname(@NotNull HdbxsodataParser.NavpropnameContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#supportnull}.
	 * @param ctx the parse tree
	 */
	void enterSupportnull(@NotNull HdbxsodataParser.SupportnullContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#supportnull}.
	 * @param ctx the parse tree
	 */
	void exitSupportnull(@NotNull HdbxsodataParser.SupportnullContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#forbidden}.
	 * @param ctx the parse tree
	 */
	void enterForbidden(@NotNull HdbxsodataParser.ForbiddenContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#forbidden}.
	 * @param ctx the parse tree
	 */
	void exitForbidden(@NotNull HdbxsodataParser.ForbiddenContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#annotationsbody}.
	 * @param ctx the parse tree
	 */
	void enterAnnotationsbody(@NotNull HdbxsodataParser.AnnotationsbodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#annotationsbody}.
	 * @param ctx the parse tree
	 */
	void exitAnnotationsbody(@NotNull HdbxsodataParser.AnnotationsbodyContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#limitvalue}.
	 * @param ctx the parse tree
	 */
	void enterLimitvalue(@NotNull HdbxsodataParser.LimitvalueContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#limitvalue}.
	 * @param ctx the parse tree
	 */
	void exitLimitvalue(@NotNull HdbxsodataParser.LimitvalueContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#columnname}.
	 * @param ctx the parse tree
	 */
	void enterColumnname(@NotNull HdbxsodataParser.ColumnnameContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#columnname}.
	 * @param ctx the parse tree
	 */
	void exitColumnname(@NotNull HdbxsodataParser.ColumnnameContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#propertylist}.
	 * @param ctx the parse tree
	 */
	void enterPropertylist(@NotNull HdbxsodataParser.PropertylistContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#propertylist}.
	 * @param ctx the parse tree
	 */
	void exitPropertylist(@NotNull HdbxsodataParser.PropertylistContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#maxexpandedrecords}.
	 * @param ctx the parse tree
	 */
	void enterMaxexpandedrecords(@NotNull HdbxsodataParser.MaxexpandedrecordsContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#maxexpandedrecords}.
	 * @param ctx the parse tree
	 */
	void exitMaxexpandedrecords(@NotNull HdbxsodataParser.MaxexpandedrecordsContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#with}.
	 * @param ctx the parse tree
	 */
	void enterWith(@NotNull HdbxsodataParser.WithContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#with}.
	 * @param ctx the parse tree
	 */
	void exitWith(@NotNull HdbxsodataParser.WithContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#dependentend}.
	 * @param ctx the parse tree
	 */
	void enterDependentend(@NotNull HdbxsodataParser.DependentendContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#dependentend}.
	 * @param ctx the parse tree
	 */
	void exitDependentend(@NotNull HdbxsodataParser.DependentendContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#overend}.
	 * @param ctx the parse tree
	 */
	void enterOverend(@NotNull HdbxsodataParser.OverendContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#overend}.
	 * @param ctx the parse tree
	 */
	void exitOverend(@NotNull HdbxsodataParser.OverendContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#service}.
	 * @param ctx the parse tree
	 */
	void enterService(@NotNull HdbxsodataParser.ServiceContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#service}.
	 * @param ctx the parse tree
	 */
	void exitService(@NotNull HdbxsodataParser.ServiceContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#namespace}.
	 * @param ctx the parse tree
	 */
	void enterNamespace(@NotNull HdbxsodataParser.NamespaceContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#namespace}.
	 * @param ctx the parse tree
	 */
	void exitNamespace(@NotNull HdbxsodataParser.NamespaceContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#modificationspec}.
	 * @param ctx the parse tree
	 */
	void enterModificationspec(@NotNull HdbxsodataParser.ModificationspecContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#modificationspec}.
	 * @param ctx the parse tree
	 */
	void exitModificationspec(@NotNull HdbxsodataParser.ModificationspecContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#catalogobjectname}.
	 * @param ctx the parse tree
	 */
	void enterCatalogobjectname(@NotNull HdbxsodataParser.CatalogobjectnameContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#catalogobjectname}.
	 * @param ctx the parse tree
	 */
	void exitCatalogobjectname(@NotNull HdbxsodataParser.CatalogobjectnameContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#navigates}.
	 * @param ctx the parse tree
	 */
	void enterNavigates(@NotNull HdbxsodataParser.NavigatesContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#navigates}.
	 * @param ctx the parse tree
	 */
	void exitNavigates(@NotNull HdbxsodataParser.NavigatesContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#entity}.
	 * @param ctx the parse tree
	 */
	void enterEntity(@NotNull HdbxsodataParser.EntityContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#entity}.
	 * @param ctx the parse tree
	 */
	void exitEntity(@NotNull HdbxsodataParser.EntityContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbxsodataParser#hintvalue}.
	 * @param ctx the parse tree
	 */
	void enterHintvalue(@NotNull HdbxsodataParser.HintvalueContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbxsodataParser#hintvalue}.
	 * @param ctx the parse tree
	 */
	void exitHintvalue(@NotNull HdbxsodataParser.HintvalueContext ctx);
}