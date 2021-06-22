// Generated from com/sap/xsk/parser/xsodata/core/Hdbxsodata.g4 by ANTLR 4.3
package com.sap.xsk.parser.xsodata.core;
import org.antlr.v4.runtime.misc.NotNull;
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
	 * Visit a parse tree produced by {@link HdbxsodataParser#multiplicity}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicity(@NotNull HdbxsodataParser.MultiplicityContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#assocrefconstraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssocrefconstraint(@NotNull HdbxsodataParser.AssocrefconstraintContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#storageend}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStorageend(@NotNull HdbxsodataParser.StorageendContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#annotations}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnnotations(@NotNull HdbxsodataParser.AnnotationsContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBody(@NotNull HdbxsodataParser.BodyContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#keylist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeylist(@NotNull HdbxsodataParser.KeylistContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#parameterskeyand}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterskeyand(@NotNull HdbxsodataParser.ParameterskeyandContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#associationdef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssociationdef(@NotNull HdbxsodataParser.AssociationdefContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#joinpropertieslist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJoinpropertieslist(@NotNull HdbxsodataParser.JoinpropertieslistContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#aggregatefunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAggregatefunction(@NotNull HdbxsodataParser.AggregatefunctionContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#hintlist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHintlist(@NotNull HdbxsodataParser.HintlistContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#create}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreate(@NotNull HdbxsodataParser.CreateContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#action}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAction(@NotNull HdbxsodataParser.ActionContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#maxrecords}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMaxrecords(@NotNull HdbxsodataParser.MaxrecordsContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#overdependentend}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOverdependentend(@NotNull HdbxsodataParser.OverdependentendContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#parameterentitysetname}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterentitysetname(@NotNull HdbxsodataParser.ParameterentitysetnameContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#modificationaction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModificationaction(@NotNull HdbxsodataParser.ModificationactionContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#events}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEvents(@NotNull HdbxsodataParser.EventsContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#nostorage}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNostorage(@NotNull HdbxsodataParser.NostorageContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#nullvalue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNullvalue(@NotNull HdbxsodataParser.NullvalueContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#settings}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSettings(@NotNull HdbxsodataParser.SettingsContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#withProp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWithProp(@NotNull HdbxsodataParser.WithPropContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#withoutProp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWithoutProp(@NotNull HdbxsodataParser.WithoutPropContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#settingsbody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSettingsbody(@NotNull HdbxsodataParser.SettingsbodyContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#catalogobjectschema}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCatalogobjectschema(@NotNull HdbxsodataParser.CatalogobjectschemaContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#hints}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHints(@NotNull HdbxsodataParser.HintsContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#settingselement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSettingselement(@NotNull HdbxsodataParser.SettingselementContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#aggregatestuple}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAggregatestuple(@NotNull HdbxsodataParser.AggregatestupleContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#modification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModification(@NotNull HdbxsodataParser.ModificationContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#contentcashecontrol}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContentcashecontrol(@NotNull HdbxsodataParser.ContentcashecontrolContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#principalend}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrincipalend(@NotNull HdbxsodataParser.PrincipalendContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#entry}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEntry(@NotNull HdbxsodataParser.EntryContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#assocname}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssocname(@NotNull HdbxsodataParser.AssocnameContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#naventry}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNaventry(@NotNull HdbxsodataParser.NaventryContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#metadatacashecontrol}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMetadatacashecontrol(@NotNull HdbxsodataParser.MetadatacashecontrolContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#entitysetname}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEntitysetname(@NotNull HdbxsodataParser.EntitysetnameContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#multiplicityvalue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicityvalue(@NotNull HdbxsodataParser.MultiplicityvalueContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#aggregates}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAggregates(@NotNull HdbxsodataParser.AggregatesContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#eventtype}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEventtype(@NotNull HdbxsodataParser.EventtypeContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#overprincipalend}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOverprincipalend(@NotNull HdbxsodataParser.OverprincipalendContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#parameters}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameters(@NotNull HdbxsodataParser.ParametersContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#repoobject}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRepoobject(@NotNull HdbxsodataParser.RepoobjectContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#object}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObject(@NotNull HdbxsodataParser.ObjectContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#entityset}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEntityset(@NotNull HdbxsodataParser.EntitysetContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#parametersresultsprop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParametersresultsprop(@NotNull HdbxsodataParser.ParametersresultspropContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#keys}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeys(@NotNull HdbxsodataParser.KeysContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#modificationBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModificationBody(@NotNull HdbxsodataParser.ModificationBodyContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#update}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUpdate(@NotNull HdbxsodataParser.UpdateContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#association}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssociation(@NotNull HdbxsodataParser.AssociationContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#annotationconfig}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnnotationconfig(@NotNull HdbxsodataParser.AnnotationconfigContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#storage}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStorage(@NotNull HdbxsodataParser.StorageContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#delete}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDelete(@NotNull HdbxsodataParser.DeleteContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#content}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContent(@NotNull HdbxsodataParser.ContentContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#endref}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEndref(@NotNull HdbxsodataParser.EndrefContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#aggregate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAggregate(@NotNull HdbxsodataParser.AggregateContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#eventlist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEventlist(@NotNull HdbxsodataParser.EventlistContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#assoctable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssoctable(@NotNull HdbxsodataParser.AssoctableContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#endtype}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEndtype(@NotNull HdbxsodataParser.EndtypeContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#end}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnd(@NotNull HdbxsodataParser.EndContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#keygenerated}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeygenerated(@NotNull HdbxsodataParser.KeygeneratedContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#eventlistElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEventlistElement(@NotNull HdbxsodataParser.EventlistElementContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#concurrencytoken}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConcurrencytoken(@NotNull HdbxsodataParser.ConcurrencytokenContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#limits}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLimits(@NotNull HdbxsodataParser.LimitsContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#xsodataDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXsodataDefinition(@NotNull HdbxsodataParser.XsodataDefinitionContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#fromend}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFromend(@NotNull HdbxsodataParser.FromendContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#navlist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNavlist(@NotNull HdbxsodataParser.NavlistContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#navpropname}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNavpropname(@NotNull HdbxsodataParser.NavpropnameContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#supportnull}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSupportnull(@NotNull HdbxsodataParser.SupportnullContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#forbidden}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForbidden(@NotNull HdbxsodataParser.ForbiddenContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#annotationsbody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnnotationsbody(@NotNull HdbxsodataParser.AnnotationsbodyContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#limitvalue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLimitvalue(@NotNull HdbxsodataParser.LimitvalueContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#columnname}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumnname(@NotNull HdbxsodataParser.ColumnnameContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#propertylist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPropertylist(@NotNull HdbxsodataParser.PropertylistContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#maxexpandedrecords}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMaxexpandedrecords(@NotNull HdbxsodataParser.MaxexpandedrecordsContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#with}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWith(@NotNull HdbxsodataParser.WithContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#dependentend}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDependentend(@NotNull HdbxsodataParser.DependentendContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#overend}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOverend(@NotNull HdbxsodataParser.OverendContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#service}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitService(@NotNull HdbxsodataParser.ServiceContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#namespace}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNamespace(@NotNull HdbxsodataParser.NamespaceContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#modificationspec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModificationspec(@NotNull HdbxsodataParser.ModificationspecContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#catalogobjectname}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCatalogobjectname(@NotNull HdbxsodataParser.CatalogobjectnameContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#navigates}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNavigates(@NotNull HdbxsodataParser.NavigatesContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#entity}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEntity(@NotNull HdbxsodataParser.EntityContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbxsodataParser#hintvalue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHintvalue(@NotNull HdbxsodataParser.HintvalueContext ctx);
}