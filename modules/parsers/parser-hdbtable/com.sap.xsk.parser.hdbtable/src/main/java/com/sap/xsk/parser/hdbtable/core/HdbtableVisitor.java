// Generated from com/sap/xsk/parser/hdbtable/core/Hdbtable.g4 by ANTLR 4.3
package com.sap.xsk.parser.hdbtable.core;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link HdbtableParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 *            operations with no return type.
 */
public interface HdbtableVisitor<T> extends ParseTreeVisitor<T> {

  /**
   * Visit a parse tree produced by {@link HdbtableParser#tableColumnsProp}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitTableColumnsProp(@NotNull HdbtableParser.TableColumnsPropContext ctx);

  /**
   * Visit a parse tree produced by {@link HdbtableParser#indexesObject}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitIndexesObject(@NotNull HdbtableParser.IndexesObjectContext ctx);

  /**
   * Visit a parse tree produced by {@link HdbtableParser#hdbtableDefinition}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitHdbtableDefinition(@NotNull HdbtableParser.HdbtableDefinitionContext ctx);

  /**
   * Visit a parse tree produced by {@link HdbtableParser#columnAssignUnique}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitColumnAssignUnique(@NotNull HdbtableParser.ColumnAssignUniqueContext ctx);

  /**
   * Visit a parse tree produced by {@link HdbtableParser#tableIndexesProp}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitTableIndexesProp(@NotNull HdbtableParser.TableIndexesPropContext ctx);

  /**
   * Visit a parse tree produced by {@link HdbtableParser#columnAssignDefaultValue}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitColumnAssignDefaultValue(@NotNull HdbtableParser.ColumnAssignDefaultValueContext ctx);

  /**
   * Visit a parse tree produced by {@link HdbtableParser#columnAssignName}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitColumnAssignName(@NotNull HdbtableParser.ColumnAssignNameContext ctx);

  /**
   * Visit a parse tree produced by {@link HdbtableParser#indexAssignIndexColumns}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitIndexAssignIndexColumns(@NotNull HdbtableParser.IndexAssignIndexColumnsContext ctx);

  /**
   * Visit a parse tree produced by {@link HdbtableParser#columnsObject}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitColumnsObject(@NotNull HdbtableParser.ColumnsObjectContext ctx);

  /**
   * Visit a parse tree produced by {@link HdbtableParser#tableTypeProp}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitTableTypeProp(@NotNull HdbtableParser.TableTypePropContext ctx);

  /**
   * Visit a parse tree produced by {@link HdbtableParser#columnAssignLength}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitColumnAssignLength(@NotNull HdbtableParser.ColumnAssignLengthContext ctx);

  /**
   * Visit a parse tree produced by {@link HdbtableParser#indexColumnsArray}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitIndexColumnsArray(@NotNull HdbtableParser.IndexColumnsArrayContext ctx);

  /**
   * Visit a parse tree produced by {@link HdbtableParser#columnAssignScale}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitColumnAssignScale(@NotNull HdbtableParser.ColumnAssignScaleContext ctx);

  /**
   * Visit a parse tree produced by {@link HdbtableParser#tablePrimaryKeyIndexTypeProp}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitTablePrimaryKeyIndexTypeProp(@NotNull HdbtableParser.TablePrimaryKeyIndexTypePropContext ctx);

  /**
   * Visit a parse tree produced by {@link HdbtableParser#columnAssignSQLType}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitColumnAssignSQLType(@NotNull HdbtableParser.ColumnAssignSQLTypeContext ctx);

  /**
   * Visit a parse tree produced by {@link HdbtableParser#indexAssignIndexType}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitIndexAssignIndexType(@NotNull HdbtableParser.IndexAssignIndexTypeContext ctx);

  /**
   * Visit a parse tree produced by {@link HdbtableParser#indexAssignUnique}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitIndexAssignUnique(@NotNull HdbtableParser.IndexAssignUniqueContext ctx);

  /**
   * Visit a parse tree produced by {@link HdbtableParser#loggingTypeProp}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitLoggingTypeProp(@NotNull HdbtableParser.LoggingTypePropContext ctx);

  /**
   * Visit a parse tree produced by {@link HdbtableParser#columnAssignNullable}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitColumnAssignNullable(@NotNull HdbtableParser.ColumnAssignNullableContext ctx);

  /**
   * Visit a parse tree produced by {@link HdbtableParser#temporaryProp}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitTemporaryProp(@NotNull HdbtableParser.TemporaryPropContext ctx);

  /**
   * Visit a parse tree produced by {@link HdbtableParser#publicProp}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitPublicProp(@NotNull HdbtableParser.PublicPropContext ctx);

  /**
   * Visit a parse tree produced by {@link HdbtableParser#indexAssignOrder}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitIndexAssignOrder(@NotNull HdbtableParser.IndexAssignOrderContext ctx);

  /**
   * Visit a parse tree produced by {@link HdbtableParser#tablePrimaryKeyProp}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitTablePrimaryKeyProp(@NotNull HdbtableParser.TablePrimaryKeyPropContext ctx);

  /**
   * Visit a parse tree produced by {@link HdbtableParser#columnAssignPrecision}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitColumnAssignPrecision(@NotNull HdbtableParser.ColumnAssignPrecisionContext ctx);

  /**
   * Visit a parse tree produced by {@link HdbtableParser#schemaNameProp}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitSchemaNameProp(@NotNull HdbtableParser.SchemaNamePropContext ctx);

  /**
   * Visit a parse tree produced by {@link HdbtableParser#tablePrimaryKeyColumnsProp}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitTablePrimaryKeyColumnsProp(@NotNull HdbtableParser.TablePrimaryKeyColumnsPropContext ctx);

  /**
   * Visit a parse tree produced by {@link HdbtableParser#columnAssignComment}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitColumnAssignComment(@NotNull HdbtableParser.ColumnAssignCommentContext ctx);

  /**
   * Visit a parse tree produced by {@link HdbtableParser#descriptionProp}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitDescriptionProp(@NotNull HdbtableParser.DescriptionPropContext ctx);

  /**
   * Visit a parse tree produced by {@link HdbtableParser#indexAssignName}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitIndexAssignName(@NotNull HdbtableParser.IndexAssignNameContext ctx);
}