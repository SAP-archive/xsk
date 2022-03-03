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
// Generated from com/sap/xsk/parser/hdbtable/core/Hdbtable.g4 by ANTLR 4.9.3
package com.sap.xsk.parser.hdbtable.core;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class HdbtableParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, STRING=28, WS=29, TABLE=30, DOT=31, EQ=32, 
		SEMICOLON=33, SQLTYPES=34, BOOLEAN=35, ORDER=36, INDEXTYPE=37, INT=38, 
		TABLETYPE=39, TABLELOGGINGTYPE=40, DATETIMEDEFAULTVALUES=41, LINE_COMMENT=42, 
		COMMENT=43;
	public static final int
		RULE_hdbtableDefinition = 0, RULE_hdbtableProperties = 1, RULE_schemaNameProp = 2, 
		RULE_temporaryProp = 3, RULE_tableTypeProp = 4, RULE_publicProp = 5, RULE_loggingTypeProp = 6, 
		RULE_tableColumnsProp = 7, RULE_tableIndexesProp = 8, RULE_tablePrimaryKeyProp = 9, 
		RULE_tablePrimaryKeyColumnsProp = 10, RULE_tablePrimaryKeyIndexTypeProp = 11, 
		RULE_descriptionProp = 12, RULE_columnsObject = 13, RULE_columnsProperties = 14, 
		RULE_indexesObject = 15, RULE_indexProperties = 16, RULE_columnAssignName = 17, 
		RULE_columnAssignSQLType = 18, RULE_columnAssignNullable = 19, RULE_columnAssignUnique = 20, 
		RULE_columnAssignLength = 21, RULE_columnAssignComment = 22, RULE_columnAssignDefaultValue = 23, 
		RULE_columnAssignPrecision = 24, RULE_columnAssignScale = 25, RULE_indexAssignName = 26, 
		RULE_indexAssignUnique = 27, RULE_indexAssignOrder = 28, RULE_indexAssignIndexColumns = 29, 
		RULE_indexAssignIndexType = 30, RULE_indexColumnsArray = 31;
	private static String[] makeRuleNames() {
		return new String[] {
			"hdbtableDefinition", "hdbtableProperties", "schemaNameProp", "temporaryProp", 
			"tableTypeProp", "publicProp", "loggingTypeProp", "tableColumnsProp", 
			"tableIndexesProp", "tablePrimaryKeyProp", "tablePrimaryKeyColumnsProp", 
			"tablePrimaryKeyIndexTypeProp", "descriptionProp", "columnsObject", "columnsProperties", 
			"indexesObject", "indexProperties", "columnAssignName", "columnAssignSQLType", 
			"columnAssignNullable", "columnAssignUnique", "columnAssignLength", "columnAssignComment", 
			"columnAssignDefaultValue", "columnAssignPrecision", "columnAssignScale", 
			"indexAssignName", "indexAssignUnique", "indexAssignOrder", "indexAssignIndexColumns", 
			"indexAssignIndexType", "indexColumnsArray"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'schemaName'", "'temporary'", "'tableType'", "'public'", "'loggingType'", 
			"'columns'", "'['", "','", "']'", "'indexes'", "'primaryKey'", "'pkcolumns'", 
			"'indexType'", "'description'", "'{'", "'}'", "'name'", "'sqlType'", 
			"'nullable'", "'unique'", "'length'", "'comment'", "'defaultValue'", 
			"'precision'", "'scale'", "'order'", "'indexColumns'", null, null, "'table'", 
			"'.'", "'='", "';'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, "STRING", "WS", "TABLE", "DOT", "EQ", "SEMICOLON", 
			"SQLTYPES", "BOOLEAN", "ORDER", "INDEXTYPE", "INT", "TABLETYPE", "TABLELOGGINGTYPE", 
			"DATETIMEDEFAULTVALUES", "LINE_COMMENT", "COMMENT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Hdbtable.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public HdbtableParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class HdbtableDefinitionContext extends ParserRuleContext {
		public List<HdbtablePropertiesContext> hdbtableProperties() {
			return getRuleContexts(HdbtablePropertiesContext.class);
		}
		public HdbtablePropertiesContext hdbtableProperties(int i) {
			return getRuleContext(HdbtablePropertiesContext.class,i);
		}
		public HdbtableDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_hdbtableDefinition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).enterHdbtableDefinition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).exitHdbtableDefinition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbtableVisitor ) return ((HdbtableVisitor<? extends T>)visitor).visitHdbtableDefinition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HdbtableDefinitionContext hdbtableDefinition() throws RecognitionException {
		HdbtableDefinitionContext _localctx = new HdbtableDefinitionContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_hdbtableDefinition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(65); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(64);
				hdbtableProperties();
				}
				}
				setState(67); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==TABLE );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class HdbtablePropertiesContext extends ParserRuleContext {
		public SchemaNamePropContext schemaNameProp() {
			return getRuleContext(SchemaNamePropContext.class,0);
		}
		public TemporaryPropContext temporaryProp() {
			return getRuleContext(TemporaryPropContext.class,0);
		}
		public TableTypePropContext tableTypeProp() {
			return getRuleContext(TableTypePropContext.class,0);
		}
		public PublicPropContext publicProp() {
			return getRuleContext(PublicPropContext.class,0);
		}
		public LoggingTypePropContext loggingTypeProp() {
			return getRuleContext(LoggingTypePropContext.class,0);
		}
		public TableColumnsPropContext tableColumnsProp() {
			return getRuleContext(TableColumnsPropContext.class,0);
		}
		public TableIndexesPropContext tableIndexesProp() {
			return getRuleContext(TableIndexesPropContext.class,0);
		}
		public TablePrimaryKeyPropContext tablePrimaryKeyProp() {
			return getRuleContext(TablePrimaryKeyPropContext.class,0);
		}
		public TablePrimaryKeyIndexTypePropContext tablePrimaryKeyIndexTypeProp() {
			return getRuleContext(TablePrimaryKeyIndexTypePropContext.class,0);
		}
		public DescriptionPropContext descriptionProp() {
			return getRuleContext(DescriptionPropContext.class,0);
		}
		public HdbtablePropertiesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_hdbtableProperties; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).enterHdbtableProperties(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).exitHdbtableProperties(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbtableVisitor ) return ((HdbtableVisitor<? extends T>)visitor).visitHdbtableProperties(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HdbtablePropertiesContext hdbtableProperties() throws RecognitionException {
		HdbtablePropertiesContext _localctx = new HdbtablePropertiesContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_hdbtableProperties);
		try {
			setState(79);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(69);
				schemaNameProp();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(70);
				temporaryProp();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(71);
				tableTypeProp();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(72);
				publicProp();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(73);
				loggingTypeProp();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(74);
				tableColumnsProp();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(75);
				tableIndexesProp();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(76);
				tablePrimaryKeyProp();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(77);
				tablePrimaryKeyIndexTypeProp();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(78);
				descriptionProp();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SchemaNamePropContext extends ParserRuleContext {
		public TerminalNode TABLE() { return getToken(HdbtableParser.TABLE, 0); }
		public TerminalNode DOT() { return getToken(HdbtableParser.DOT, 0); }
		public TerminalNode EQ() { return getToken(HdbtableParser.EQ, 0); }
		public TerminalNode STRING() { return getToken(HdbtableParser.STRING, 0); }
		public TerminalNode SEMICOLON() { return getToken(HdbtableParser.SEMICOLON, 0); }
		public SchemaNamePropContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_schemaNameProp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).enterSchemaNameProp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).exitSchemaNameProp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbtableVisitor ) return ((HdbtableVisitor<? extends T>)visitor).visitSchemaNameProp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SchemaNamePropContext schemaNameProp() throws RecognitionException {
		SchemaNamePropContext _localctx = new SchemaNamePropContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_schemaNameProp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(81);
			match(TABLE);
			setState(82);
			match(DOT);
			setState(83);
			match(T__0);
			setState(84);
			match(EQ);
			setState(85);
			match(STRING);
			setState(86);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TemporaryPropContext extends ParserRuleContext {
		public TerminalNode TABLE() { return getToken(HdbtableParser.TABLE, 0); }
		public TerminalNode DOT() { return getToken(HdbtableParser.DOT, 0); }
		public TerminalNode EQ() { return getToken(HdbtableParser.EQ, 0); }
		public TerminalNode BOOLEAN() { return getToken(HdbtableParser.BOOLEAN, 0); }
		public TerminalNode SEMICOLON() { return getToken(HdbtableParser.SEMICOLON, 0); }
		public TemporaryPropContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_temporaryProp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).enterTemporaryProp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).exitTemporaryProp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbtableVisitor ) return ((HdbtableVisitor<? extends T>)visitor).visitTemporaryProp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TemporaryPropContext temporaryProp() throws RecognitionException {
		TemporaryPropContext _localctx = new TemporaryPropContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_temporaryProp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(88);
			match(TABLE);
			setState(89);
			match(DOT);
			setState(90);
			match(T__1);
			setState(91);
			match(EQ);
			setState(92);
			match(BOOLEAN);
			setState(93);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TableTypePropContext extends ParserRuleContext {
		public TerminalNode TABLE() { return getToken(HdbtableParser.TABLE, 0); }
		public TerminalNode DOT() { return getToken(HdbtableParser.DOT, 0); }
		public TerminalNode EQ() { return getToken(HdbtableParser.EQ, 0); }
		public TerminalNode TABLETYPE() { return getToken(HdbtableParser.TABLETYPE, 0); }
		public TerminalNode SEMICOLON() { return getToken(HdbtableParser.SEMICOLON, 0); }
		public TableTypePropContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tableTypeProp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).enterTableTypeProp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).exitTableTypeProp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbtableVisitor ) return ((HdbtableVisitor<? extends T>)visitor).visitTableTypeProp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TableTypePropContext tableTypeProp() throws RecognitionException {
		TableTypePropContext _localctx = new TableTypePropContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_tableTypeProp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(95);
			match(TABLE);
			setState(96);
			match(DOT);
			setState(97);
			match(T__2);
			setState(98);
			match(EQ);
			setState(99);
			match(TABLETYPE);
			setState(100);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PublicPropContext extends ParserRuleContext {
		public TerminalNode TABLE() { return getToken(HdbtableParser.TABLE, 0); }
		public TerminalNode DOT() { return getToken(HdbtableParser.DOT, 0); }
		public TerminalNode EQ() { return getToken(HdbtableParser.EQ, 0); }
		public TerminalNode BOOLEAN() { return getToken(HdbtableParser.BOOLEAN, 0); }
		public TerminalNode SEMICOLON() { return getToken(HdbtableParser.SEMICOLON, 0); }
		public PublicPropContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_publicProp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).enterPublicProp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).exitPublicProp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbtableVisitor ) return ((HdbtableVisitor<? extends T>)visitor).visitPublicProp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PublicPropContext publicProp() throws RecognitionException {
		PublicPropContext _localctx = new PublicPropContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_publicProp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(102);
			match(TABLE);
			setState(103);
			match(DOT);
			setState(104);
			match(T__3);
			setState(105);
			match(EQ);
			setState(106);
			match(BOOLEAN);
			setState(107);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LoggingTypePropContext extends ParserRuleContext {
		public TerminalNode TABLE() { return getToken(HdbtableParser.TABLE, 0); }
		public TerminalNode DOT() { return getToken(HdbtableParser.DOT, 0); }
		public TerminalNode EQ() { return getToken(HdbtableParser.EQ, 0); }
		public TerminalNode TABLELOGGINGTYPE() { return getToken(HdbtableParser.TABLELOGGINGTYPE, 0); }
		public TerminalNode SEMICOLON() { return getToken(HdbtableParser.SEMICOLON, 0); }
		public LoggingTypePropContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_loggingTypeProp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).enterLoggingTypeProp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).exitLoggingTypeProp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbtableVisitor ) return ((HdbtableVisitor<? extends T>)visitor).visitLoggingTypeProp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LoggingTypePropContext loggingTypeProp() throws RecognitionException {
		LoggingTypePropContext _localctx = new LoggingTypePropContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_loggingTypeProp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(109);
			match(TABLE);
			setState(110);
			match(DOT);
			setState(111);
			match(T__4);
			setState(112);
			match(EQ);
			setState(113);
			match(TABLELOGGINGTYPE);
			setState(114);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TableColumnsPropContext extends ParserRuleContext {
		public TerminalNode TABLE() { return getToken(HdbtableParser.TABLE, 0); }
		public TerminalNode DOT() { return getToken(HdbtableParser.DOT, 0); }
		public TerminalNode EQ() { return getToken(HdbtableParser.EQ, 0); }
		public TerminalNode SEMICOLON() { return getToken(HdbtableParser.SEMICOLON, 0); }
		public List<ColumnsObjectContext> columnsObject() {
			return getRuleContexts(ColumnsObjectContext.class);
		}
		public ColumnsObjectContext columnsObject(int i) {
			return getRuleContext(ColumnsObjectContext.class,i);
		}
		public TableColumnsPropContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tableColumnsProp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).enterTableColumnsProp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).exitTableColumnsProp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbtableVisitor ) return ((HdbtableVisitor<? extends T>)visitor).visitTableColumnsProp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TableColumnsPropContext tableColumnsProp() throws RecognitionException {
		TableColumnsPropContext _localctx = new TableColumnsPropContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_tableColumnsProp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(116);
			match(TABLE);
			setState(117);
			match(DOT);
			setState(118);
			match(T__5);
			setState(119);
			match(EQ);
			setState(120);
			match(T__6);
			setState(129);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__14) {
				{
				setState(121);
				columnsObject();
				setState(126);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__7) {
					{
					{
					setState(122);
					match(T__7);
					setState(123);
					columnsObject();
					}
					}
					setState(128);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(131);
			match(T__8);
			setState(132);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TableIndexesPropContext extends ParserRuleContext {
		public TerminalNode TABLE() { return getToken(HdbtableParser.TABLE, 0); }
		public TerminalNode DOT() { return getToken(HdbtableParser.DOT, 0); }
		public TerminalNode EQ() { return getToken(HdbtableParser.EQ, 0); }
		public TerminalNode SEMICOLON() { return getToken(HdbtableParser.SEMICOLON, 0); }
		public List<IndexesObjectContext> indexesObject() {
			return getRuleContexts(IndexesObjectContext.class);
		}
		public IndexesObjectContext indexesObject(int i) {
			return getRuleContext(IndexesObjectContext.class,i);
		}
		public TableIndexesPropContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tableIndexesProp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).enterTableIndexesProp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).exitTableIndexesProp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbtableVisitor ) return ((HdbtableVisitor<? extends T>)visitor).visitTableIndexesProp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TableIndexesPropContext tableIndexesProp() throws RecognitionException {
		TableIndexesPropContext _localctx = new TableIndexesPropContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_tableIndexesProp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(134);
			match(TABLE);
			setState(135);
			match(DOT);
			setState(136);
			match(T__9);
			setState(137);
			match(EQ);
			setState(138);
			match(T__6);
			setState(147);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__14) {
				{
				setState(139);
				indexesObject();
				setState(144);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__7) {
					{
					{
					setState(140);
					match(T__7);
					setState(141);
					indexesObject();
					}
					}
					setState(146);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(149);
			match(T__8);
			setState(150);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TablePrimaryKeyPropContext extends ParserRuleContext {
		public TerminalNode TABLE() { return getToken(HdbtableParser.TABLE, 0); }
		public List<TerminalNode> DOT() { return getTokens(HdbtableParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(HdbtableParser.DOT, i);
		}
		public TerminalNode EQ() { return getToken(HdbtableParser.EQ, 0); }
		public TablePrimaryKeyColumnsPropContext tablePrimaryKeyColumnsProp() {
			return getRuleContext(TablePrimaryKeyColumnsPropContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(HdbtableParser.SEMICOLON, 0); }
		public TablePrimaryKeyPropContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tablePrimaryKeyProp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).enterTablePrimaryKeyProp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).exitTablePrimaryKeyProp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbtableVisitor ) return ((HdbtableVisitor<? extends T>)visitor).visitTablePrimaryKeyProp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TablePrimaryKeyPropContext tablePrimaryKeyProp() throws RecognitionException {
		TablePrimaryKeyPropContext _localctx = new TablePrimaryKeyPropContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_tablePrimaryKeyProp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(152);
			match(TABLE);
			setState(153);
			match(DOT);
			setState(154);
			match(T__10);
			setState(155);
			match(DOT);
			setState(156);
			match(T__11);
			setState(157);
			match(EQ);
			setState(158);
			tablePrimaryKeyColumnsProp();
			setState(159);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TablePrimaryKeyColumnsPropContext extends ParserRuleContext {
		public List<TerminalNode> STRING() { return getTokens(HdbtableParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(HdbtableParser.STRING, i);
		}
		public TablePrimaryKeyColumnsPropContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tablePrimaryKeyColumnsProp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).enterTablePrimaryKeyColumnsProp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).exitTablePrimaryKeyColumnsProp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbtableVisitor ) return ((HdbtableVisitor<? extends T>)visitor).visitTablePrimaryKeyColumnsProp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TablePrimaryKeyColumnsPropContext tablePrimaryKeyColumnsProp() throws RecognitionException {
		TablePrimaryKeyColumnsPropContext _localctx = new TablePrimaryKeyColumnsPropContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_tablePrimaryKeyColumnsProp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(161);
			match(T__6);
			setState(162);
			match(STRING);
			setState(167);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__7) {
				{
				{
				setState(163);
				match(T__7);
				setState(164);
				match(STRING);
				}
				}
				setState(169);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(170);
			match(T__8);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TablePrimaryKeyIndexTypePropContext extends ParserRuleContext {
		public TerminalNode TABLE() { return getToken(HdbtableParser.TABLE, 0); }
		public List<TerminalNode> DOT() { return getTokens(HdbtableParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(HdbtableParser.DOT, i);
		}
		public TerminalNode EQ() { return getToken(HdbtableParser.EQ, 0); }
		public TerminalNode INDEXTYPE() { return getToken(HdbtableParser.INDEXTYPE, 0); }
		public TerminalNode SEMICOLON() { return getToken(HdbtableParser.SEMICOLON, 0); }
		public TablePrimaryKeyIndexTypePropContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tablePrimaryKeyIndexTypeProp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).enterTablePrimaryKeyIndexTypeProp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).exitTablePrimaryKeyIndexTypeProp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbtableVisitor ) return ((HdbtableVisitor<? extends T>)visitor).visitTablePrimaryKeyIndexTypeProp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TablePrimaryKeyIndexTypePropContext tablePrimaryKeyIndexTypeProp() throws RecognitionException {
		TablePrimaryKeyIndexTypePropContext _localctx = new TablePrimaryKeyIndexTypePropContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_tablePrimaryKeyIndexTypeProp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(172);
			match(TABLE);
			setState(173);
			match(DOT);
			setState(174);
			match(T__10);
			setState(175);
			match(DOT);
			setState(176);
			match(T__12);
			setState(177);
			match(EQ);
			setState(178);
			match(INDEXTYPE);
			setState(179);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DescriptionPropContext extends ParserRuleContext {
		public TerminalNode TABLE() { return getToken(HdbtableParser.TABLE, 0); }
		public TerminalNode DOT() { return getToken(HdbtableParser.DOT, 0); }
		public TerminalNode EQ() { return getToken(HdbtableParser.EQ, 0); }
		public TerminalNode STRING() { return getToken(HdbtableParser.STRING, 0); }
		public TerminalNode SEMICOLON() { return getToken(HdbtableParser.SEMICOLON, 0); }
		public DescriptionPropContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_descriptionProp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).enterDescriptionProp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).exitDescriptionProp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbtableVisitor ) return ((HdbtableVisitor<? extends T>)visitor).visitDescriptionProp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DescriptionPropContext descriptionProp() throws RecognitionException {
		DescriptionPropContext _localctx = new DescriptionPropContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_descriptionProp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(181);
			match(TABLE);
			setState(182);
			match(DOT);
			setState(183);
			match(T__13);
			setState(184);
			match(EQ);
			setState(185);
			match(STRING);
			setState(186);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ColumnsObjectContext extends ParserRuleContext {
		public List<ColumnsPropertiesContext> columnsProperties() {
			return getRuleContexts(ColumnsPropertiesContext.class);
		}
		public ColumnsPropertiesContext columnsProperties(int i) {
			return getRuleContext(ColumnsPropertiesContext.class,i);
		}
		public ColumnsObjectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_columnsObject; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).enterColumnsObject(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).exitColumnsObject(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbtableVisitor ) return ((HdbtableVisitor<? extends T>)visitor).visitColumnsObject(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ColumnsObjectContext columnsObject() throws RecognitionException {
		ColumnsObjectContext _localctx = new ColumnsObjectContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_columnsObject);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(188);
			match(T__14);
			setState(190); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(189);
				columnsProperties();
				}
				}
				setState(192); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24))) != 0) );
			setState(194);
			match(T__15);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ColumnsPropertiesContext extends ParserRuleContext {
		public ColumnAssignNameContext columnAssignName() {
			return getRuleContext(ColumnAssignNameContext.class,0);
		}
		public ColumnAssignSQLTypeContext columnAssignSQLType() {
			return getRuleContext(ColumnAssignSQLTypeContext.class,0);
		}
		public ColumnAssignUniqueContext columnAssignUnique() {
			return getRuleContext(ColumnAssignUniqueContext.class,0);
		}
		public ColumnAssignLengthContext columnAssignLength() {
			return getRuleContext(ColumnAssignLengthContext.class,0);
		}
		public ColumnAssignNullableContext columnAssignNullable() {
			return getRuleContext(ColumnAssignNullableContext.class,0);
		}
		public ColumnAssignCommentContext columnAssignComment() {
			return getRuleContext(ColumnAssignCommentContext.class,0);
		}
		public ColumnAssignDefaultValueContext columnAssignDefaultValue() {
			return getRuleContext(ColumnAssignDefaultValueContext.class,0);
		}
		public ColumnAssignPrecisionContext columnAssignPrecision() {
			return getRuleContext(ColumnAssignPrecisionContext.class,0);
		}
		public ColumnAssignScaleContext columnAssignScale() {
			return getRuleContext(ColumnAssignScaleContext.class,0);
		}
		public ColumnsPropertiesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_columnsProperties; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).enterColumnsProperties(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).exitColumnsProperties(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbtableVisitor ) return ((HdbtableVisitor<? extends T>)visitor).visitColumnsProperties(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ColumnsPropertiesContext columnsProperties() throws RecognitionException {
		ColumnsPropertiesContext _localctx = new ColumnsPropertiesContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_columnsProperties);
		try {
			setState(205);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__16:
				enterOuterAlt(_localctx, 1);
				{
				setState(196);
				columnAssignName();
				}
				break;
			case T__17:
				enterOuterAlt(_localctx, 2);
				{
				setState(197);
				columnAssignSQLType();
				}
				break;
			case T__19:
				enterOuterAlt(_localctx, 3);
				{
				setState(198);
				columnAssignUnique();
				}
				break;
			case T__20:
				enterOuterAlt(_localctx, 4);
				{
				setState(199);
				columnAssignLength();
				}
				break;
			case T__18:
				enterOuterAlt(_localctx, 5);
				{
				setState(200);
				columnAssignNullable();
				}
				break;
			case T__21:
				enterOuterAlt(_localctx, 6);
				{
				setState(201);
				columnAssignComment();
				}
				break;
			case T__22:
				enterOuterAlt(_localctx, 7);
				{
				setState(202);
				columnAssignDefaultValue();
				}
				break;
			case T__23:
				enterOuterAlt(_localctx, 8);
				{
				setState(203);
				columnAssignPrecision();
				}
				break;
			case T__24:
				enterOuterAlt(_localctx, 9);
				{
				setState(204);
				columnAssignScale();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IndexesObjectContext extends ParserRuleContext {
		public List<IndexPropertiesContext> indexProperties() {
			return getRuleContexts(IndexPropertiesContext.class);
		}
		public IndexPropertiesContext indexProperties(int i) {
			return getRuleContext(IndexPropertiesContext.class,i);
		}
		public IndexesObjectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_indexesObject; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).enterIndexesObject(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).exitIndexesObject(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbtableVisitor ) return ((HdbtableVisitor<? extends T>)visitor).visitIndexesObject(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IndexesObjectContext indexesObject() throws RecognitionException {
		IndexesObjectContext _localctx = new IndexesObjectContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_indexesObject);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(207);
			match(T__14);
			setState(209); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(208);
				indexProperties();
				}
				}
				setState(211); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__12) | (1L << T__16) | (1L << T__19) | (1L << T__25) | (1L << T__26))) != 0) );
			setState(213);
			match(T__15);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IndexPropertiesContext extends ParserRuleContext {
		public IndexAssignNameContext indexAssignName() {
			return getRuleContext(IndexAssignNameContext.class,0);
		}
		public IndexAssignUniqueContext indexAssignUnique() {
			return getRuleContext(IndexAssignUniqueContext.class,0);
		}
		public IndexAssignOrderContext indexAssignOrder() {
			return getRuleContext(IndexAssignOrderContext.class,0);
		}
		public IndexAssignIndexColumnsContext indexAssignIndexColumns() {
			return getRuleContext(IndexAssignIndexColumnsContext.class,0);
		}
		public IndexAssignIndexTypeContext indexAssignIndexType() {
			return getRuleContext(IndexAssignIndexTypeContext.class,0);
		}
		public IndexPropertiesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_indexProperties; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).enterIndexProperties(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).exitIndexProperties(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbtableVisitor ) return ((HdbtableVisitor<? extends T>)visitor).visitIndexProperties(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IndexPropertiesContext indexProperties() throws RecognitionException {
		IndexPropertiesContext _localctx = new IndexPropertiesContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_indexProperties);
		try {
			setState(220);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__16:
				enterOuterAlt(_localctx, 1);
				{
				setState(215);
				indexAssignName();
				}
				break;
			case T__19:
				enterOuterAlt(_localctx, 2);
				{
				setState(216);
				indexAssignUnique();
				}
				break;
			case T__25:
				enterOuterAlt(_localctx, 3);
				{
				setState(217);
				indexAssignOrder();
				}
				break;
			case T__26:
				enterOuterAlt(_localctx, 4);
				{
				setState(218);
				indexAssignIndexColumns();
				}
				break;
			case T__12:
				enterOuterAlt(_localctx, 5);
				{
				setState(219);
				indexAssignIndexType();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ColumnAssignNameContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(HdbtableParser.EQ, 0); }
		public TerminalNode STRING() { return getToken(HdbtableParser.STRING, 0); }
		public TerminalNode SEMICOLON() { return getToken(HdbtableParser.SEMICOLON, 0); }
		public ColumnAssignNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_columnAssignName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).enterColumnAssignName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).exitColumnAssignName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbtableVisitor ) return ((HdbtableVisitor<? extends T>)visitor).visitColumnAssignName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ColumnAssignNameContext columnAssignName() throws RecognitionException {
		ColumnAssignNameContext _localctx = new ColumnAssignNameContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_columnAssignName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(222);
			match(T__16);
			setState(223);
			match(EQ);
			setState(224);
			match(STRING);
			setState(225);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ColumnAssignSQLTypeContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(HdbtableParser.EQ, 0); }
		public TerminalNode SQLTYPES() { return getToken(HdbtableParser.SQLTYPES, 0); }
		public TerminalNode SEMICOLON() { return getToken(HdbtableParser.SEMICOLON, 0); }
		public ColumnAssignSQLTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_columnAssignSQLType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).enterColumnAssignSQLType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).exitColumnAssignSQLType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbtableVisitor ) return ((HdbtableVisitor<? extends T>)visitor).visitColumnAssignSQLType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ColumnAssignSQLTypeContext columnAssignSQLType() throws RecognitionException {
		ColumnAssignSQLTypeContext _localctx = new ColumnAssignSQLTypeContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_columnAssignSQLType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(227);
			match(T__17);
			setState(228);
			match(EQ);
			setState(229);
			match(SQLTYPES);
			setState(230);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ColumnAssignNullableContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(HdbtableParser.EQ, 0); }
		public TerminalNode BOOLEAN() { return getToken(HdbtableParser.BOOLEAN, 0); }
		public TerminalNode SEMICOLON() { return getToken(HdbtableParser.SEMICOLON, 0); }
		public ColumnAssignNullableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_columnAssignNullable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).enterColumnAssignNullable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).exitColumnAssignNullable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbtableVisitor ) return ((HdbtableVisitor<? extends T>)visitor).visitColumnAssignNullable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ColumnAssignNullableContext columnAssignNullable() throws RecognitionException {
		ColumnAssignNullableContext _localctx = new ColumnAssignNullableContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_columnAssignNullable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(232);
			match(T__18);
			setState(233);
			match(EQ);
			setState(234);
			match(BOOLEAN);
			setState(235);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ColumnAssignUniqueContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(HdbtableParser.EQ, 0); }
		public TerminalNode BOOLEAN() { return getToken(HdbtableParser.BOOLEAN, 0); }
		public TerminalNode SEMICOLON() { return getToken(HdbtableParser.SEMICOLON, 0); }
		public ColumnAssignUniqueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_columnAssignUnique; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).enterColumnAssignUnique(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).exitColumnAssignUnique(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbtableVisitor ) return ((HdbtableVisitor<? extends T>)visitor).visitColumnAssignUnique(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ColumnAssignUniqueContext columnAssignUnique() throws RecognitionException {
		ColumnAssignUniqueContext _localctx = new ColumnAssignUniqueContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_columnAssignUnique);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(237);
			match(T__19);
			setState(238);
			match(EQ);
			setState(239);
			match(BOOLEAN);
			setState(240);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ColumnAssignLengthContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(HdbtableParser.EQ, 0); }
		public TerminalNode INT() { return getToken(HdbtableParser.INT, 0); }
		public TerminalNode SEMICOLON() { return getToken(HdbtableParser.SEMICOLON, 0); }
		public ColumnAssignLengthContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_columnAssignLength; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).enterColumnAssignLength(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).exitColumnAssignLength(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbtableVisitor ) return ((HdbtableVisitor<? extends T>)visitor).visitColumnAssignLength(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ColumnAssignLengthContext columnAssignLength() throws RecognitionException {
		ColumnAssignLengthContext _localctx = new ColumnAssignLengthContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_columnAssignLength);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(242);
			match(T__20);
			setState(243);
			match(EQ);
			setState(244);
			match(INT);
			setState(245);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ColumnAssignCommentContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(HdbtableParser.EQ, 0); }
		public TerminalNode STRING() { return getToken(HdbtableParser.STRING, 0); }
		public TerminalNode SEMICOLON() { return getToken(HdbtableParser.SEMICOLON, 0); }
		public ColumnAssignCommentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_columnAssignComment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).enterColumnAssignComment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).exitColumnAssignComment(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbtableVisitor ) return ((HdbtableVisitor<? extends T>)visitor).visitColumnAssignComment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ColumnAssignCommentContext columnAssignComment() throws RecognitionException {
		ColumnAssignCommentContext _localctx = new ColumnAssignCommentContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_columnAssignComment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(247);
			match(T__21);
			setState(248);
			match(EQ);
			setState(249);
			match(STRING);
			setState(250);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ColumnAssignDefaultValueContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(HdbtableParser.EQ, 0); }
		public TerminalNode SEMICOLON() { return getToken(HdbtableParser.SEMICOLON, 0); }
		public TerminalNode STRING() { return getToken(HdbtableParser.STRING, 0); }
		public TerminalNode INT() { return getToken(HdbtableParser.INT, 0); }
		public TerminalNode DATETIMEDEFAULTVALUES() { return getToken(HdbtableParser.DATETIMEDEFAULTVALUES, 0); }
		public ColumnAssignDefaultValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_columnAssignDefaultValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).enterColumnAssignDefaultValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).exitColumnAssignDefaultValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbtableVisitor ) return ((HdbtableVisitor<? extends T>)visitor).visitColumnAssignDefaultValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ColumnAssignDefaultValueContext columnAssignDefaultValue() throws RecognitionException {
		ColumnAssignDefaultValueContext _localctx = new ColumnAssignDefaultValueContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_columnAssignDefaultValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(252);
			match(T__22);
			setState(253);
			match(EQ);
			setState(254);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << STRING) | (1L << INT) | (1L << DATETIMEDEFAULTVALUES))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(255);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ColumnAssignPrecisionContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(HdbtableParser.EQ, 0); }
		public TerminalNode INT() { return getToken(HdbtableParser.INT, 0); }
		public TerminalNode SEMICOLON() { return getToken(HdbtableParser.SEMICOLON, 0); }
		public ColumnAssignPrecisionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_columnAssignPrecision; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).enterColumnAssignPrecision(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).exitColumnAssignPrecision(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbtableVisitor ) return ((HdbtableVisitor<? extends T>)visitor).visitColumnAssignPrecision(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ColumnAssignPrecisionContext columnAssignPrecision() throws RecognitionException {
		ColumnAssignPrecisionContext _localctx = new ColumnAssignPrecisionContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_columnAssignPrecision);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(257);
			match(T__23);
			setState(258);
			match(EQ);
			setState(259);
			match(INT);
			setState(260);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ColumnAssignScaleContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(HdbtableParser.EQ, 0); }
		public TerminalNode INT() { return getToken(HdbtableParser.INT, 0); }
		public TerminalNode SEMICOLON() { return getToken(HdbtableParser.SEMICOLON, 0); }
		public ColumnAssignScaleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_columnAssignScale; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).enterColumnAssignScale(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).exitColumnAssignScale(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbtableVisitor ) return ((HdbtableVisitor<? extends T>)visitor).visitColumnAssignScale(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ColumnAssignScaleContext columnAssignScale() throws RecognitionException {
		ColumnAssignScaleContext _localctx = new ColumnAssignScaleContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_columnAssignScale);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(262);
			match(T__24);
			setState(263);
			match(EQ);
			setState(264);
			match(INT);
			setState(265);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IndexAssignNameContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(HdbtableParser.EQ, 0); }
		public TerminalNode STRING() { return getToken(HdbtableParser.STRING, 0); }
		public TerminalNode SEMICOLON() { return getToken(HdbtableParser.SEMICOLON, 0); }
		public IndexAssignNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_indexAssignName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).enterIndexAssignName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).exitIndexAssignName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbtableVisitor ) return ((HdbtableVisitor<? extends T>)visitor).visitIndexAssignName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IndexAssignNameContext indexAssignName() throws RecognitionException {
		IndexAssignNameContext _localctx = new IndexAssignNameContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_indexAssignName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(267);
			match(T__16);
			setState(268);
			match(EQ);
			setState(269);
			match(STRING);
			setState(270);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IndexAssignUniqueContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(HdbtableParser.EQ, 0); }
		public TerminalNode BOOLEAN() { return getToken(HdbtableParser.BOOLEAN, 0); }
		public TerminalNode SEMICOLON() { return getToken(HdbtableParser.SEMICOLON, 0); }
		public IndexAssignUniqueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_indexAssignUnique; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).enterIndexAssignUnique(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).exitIndexAssignUnique(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbtableVisitor ) return ((HdbtableVisitor<? extends T>)visitor).visitIndexAssignUnique(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IndexAssignUniqueContext indexAssignUnique() throws RecognitionException {
		IndexAssignUniqueContext _localctx = new IndexAssignUniqueContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_indexAssignUnique);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(272);
			match(T__19);
			setState(273);
			match(EQ);
			setState(274);
			match(BOOLEAN);
			setState(275);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IndexAssignOrderContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(HdbtableParser.EQ, 0); }
		public TerminalNode ORDER() { return getToken(HdbtableParser.ORDER, 0); }
		public TerminalNode SEMICOLON() { return getToken(HdbtableParser.SEMICOLON, 0); }
		public IndexAssignOrderContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_indexAssignOrder; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).enterIndexAssignOrder(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).exitIndexAssignOrder(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbtableVisitor ) return ((HdbtableVisitor<? extends T>)visitor).visitIndexAssignOrder(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IndexAssignOrderContext indexAssignOrder() throws RecognitionException {
		IndexAssignOrderContext _localctx = new IndexAssignOrderContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_indexAssignOrder);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(277);
			match(T__25);
			setState(278);
			match(EQ);
			setState(279);
			match(ORDER);
			setState(280);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IndexAssignIndexColumnsContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(HdbtableParser.EQ, 0); }
		public IndexColumnsArrayContext indexColumnsArray() {
			return getRuleContext(IndexColumnsArrayContext.class,0);
		}
		public IndexAssignIndexColumnsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_indexAssignIndexColumns; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).enterIndexAssignIndexColumns(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).exitIndexAssignIndexColumns(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbtableVisitor ) return ((HdbtableVisitor<? extends T>)visitor).visitIndexAssignIndexColumns(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IndexAssignIndexColumnsContext indexAssignIndexColumns() throws RecognitionException {
		IndexAssignIndexColumnsContext _localctx = new IndexAssignIndexColumnsContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_indexAssignIndexColumns);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(282);
			match(T__26);
			setState(283);
			match(EQ);
			setState(284);
			indexColumnsArray();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IndexAssignIndexTypeContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(HdbtableParser.EQ, 0); }
		public TerminalNode INDEXTYPE() { return getToken(HdbtableParser.INDEXTYPE, 0); }
		public TerminalNode SEMICOLON() { return getToken(HdbtableParser.SEMICOLON, 0); }
		public IndexAssignIndexTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_indexAssignIndexType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).enterIndexAssignIndexType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).exitIndexAssignIndexType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbtableVisitor ) return ((HdbtableVisitor<? extends T>)visitor).visitIndexAssignIndexType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IndexAssignIndexTypeContext indexAssignIndexType() throws RecognitionException {
		IndexAssignIndexTypeContext _localctx = new IndexAssignIndexTypeContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_indexAssignIndexType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(286);
			match(T__12);
			setState(287);
			match(EQ);
			setState(288);
			match(INDEXTYPE);
			setState(289);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IndexColumnsArrayContext extends ParserRuleContext {
		public TerminalNode SEMICOLON() { return getToken(HdbtableParser.SEMICOLON, 0); }
		public List<TerminalNode> STRING() { return getTokens(HdbtableParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(HdbtableParser.STRING, i);
		}
		public IndexColumnsArrayContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_indexColumnsArray; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).enterIndexColumnsArray(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).exitIndexColumnsArray(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbtableVisitor ) return ((HdbtableVisitor<? extends T>)visitor).visitIndexColumnsArray(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IndexColumnsArrayContext indexColumnsArray() throws RecognitionException {
		IndexColumnsArrayContext _localctx = new IndexColumnsArrayContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_indexColumnsArray);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(291);
			match(T__6);
			setState(300);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==STRING) {
				{
				setState(292);
				match(STRING);
				setState(297);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__7) {
					{
					{
					setState(293);
					match(T__7);
					setState(294);
					match(STRING);
					}
					}
					setState(299);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(302);
			match(T__8);
			setState(303);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3-\u0134\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\3\2\6\2D\n\2\r\2\16\2E\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3"+
		"R\n\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\7\t\177\n\t\f\t\16\t\u0082\13\t"+
		"\5\t\u0084\n\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\7\n\u0091\n"+
		"\n\f\n\16\n\u0094\13\n\5\n\u0096\n\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\7\f\u00a8\n\f\f\f\16\f\u00ab\13"+
		"\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\17\3\17\6\17\u00c1\n\17\r\17\16\17\u00c2\3\17\3\17\3\20\3"+
		"\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\5\20\u00d0\n\20\3\21\3\21\6\21"+
		"\u00d4\n\21\r\21\16\21\u00d5\3\21\3\21\3\22\3\22\3\22\3\22\3\22\5\22\u00df"+
		"\n\22\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25"+
		"\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\30\3\30"+
		"\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\33"+
		"\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35"+
		"\3\36\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3 \3 \3 \3 \3 \3!\3!\3!"+
		"\3!\7!\u012a\n!\f!\16!\u012d\13!\5!\u012f\n!\3!\3!\3!\3!\2\2\"\2\4\6\b"+
		"\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@\2\3\5\2\36\36"+
		"((++\2\u0132\2C\3\2\2\2\4Q\3\2\2\2\6S\3\2\2\2\bZ\3\2\2\2\na\3\2\2\2\f"+
		"h\3\2\2\2\16o\3\2\2\2\20v\3\2\2\2\22\u0088\3\2\2\2\24\u009a\3\2\2\2\26"+
		"\u00a3\3\2\2\2\30\u00ae\3\2\2\2\32\u00b7\3\2\2\2\34\u00be\3\2\2\2\36\u00cf"+
		"\3\2\2\2 \u00d1\3\2\2\2\"\u00de\3\2\2\2$\u00e0\3\2\2\2&\u00e5\3\2\2\2"+
		"(\u00ea\3\2\2\2*\u00ef\3\2\2\2,\u00f4\3\2\2\2.\u00f9\3\2\2\2\60\u00fe"+
		"\3\2\2\2\62\u0103\3\2\2\2\64\u0108\3\2\2\2\66\u010d\3\2\2\28\u0112\3\2"+
		"\2\2:\u0117\3\2\2\2<\u011c\3\2\2\2>\u0120\3\2\2\2@\u0125\3\2\2\2BD\5\4"+
		"\3\2CB\3\2\2\2DE\3\2\2\2EC\3\2\2\2EF\3\2\2\2F\3\3\2\2\2GR\5\6\4\2HR\5"+
		"\b\5\2IR\5\n\6\2JR\5\f\7\2KR\5\16\b\2LR\5\20\t\2MR\5\22\n\2NR\5\24\13"+
		"\2OR\5\30\r\2PR\5\32\16\2QG\3\2\2\2QH\3\2\2\2QI\3\2\2\2QJ\3\2\2\2QK\3"+
		"\2\2\2QL\3\2\2\2QM\3\2\2\2QN\3\2\2\2QO\3\2\2\2QP\3\2\2\2R\5\3\2\2\2ST"+
		"\7 \2\2TU\7!\2\2UV\7\3\2\2VW\7\"\2\2WX\7\36\2\2XY\7#\2\2Y\7\3\2\2\2Z["+
		"\7 \2\2[\\\7!\2\2\\]\7\4\2\2]^\7\"\2\2^_\7%\2\2_`\7#\2\2`\t\3\2\2\2ab"+
		"\7 \2\2bc\7!\2\2cd\7\5\2\2de\7\"\2\2ef\7)\2\2fg\7#\2\2g\13\3\2\2\2hi\7"+
		" \2\2ij\7!\2\2jk\7\6\2\2kl\7\"\2\2lm\7%\2\2mn\7#\2\2n\r\3\2\2\2op\7 \2"+
		"\2pq\7!\2\2qr\7\7\2\2rs\7\"\2\2st\7*\2\2tu\7#\2\2u\17\3\2\2\2vw\7 \2\2"+
		"wx\7!\2\2xy\7\b\2\2yz\7\"\2\2z\u0083\7\t\2\2{\u0080\5\34\17\2|}\7\n\2"+
		"\2}\177\5\34\17\2~|\3\2\2\2\177\u0082\3\2\2\2\u0080~\3\2\2\2\u0080\u0081"+
		"\3\2\2\2\u0081\u0084\3\2\2\2\u0082\u0080\3\2\2\2\u0083{\3\2\2\2\u0083"+
		"\u0084\3\2\2\2\u0084\u0085\3\2\2\2\u0085\u0086\7\13\2\2\u0086\u0087\7"+
		"#\2\2\u0087\21\3\2\2\2\u0088\u0089\7 \2\2\u0089\u008a\7!\2\2\u008a\u008b"+
		"\7\f\2\2\u008b\u008c\7\"\2\2\u008c\u0095\7\t\2\2\u008d\u0092\5 \21\2\u008e"+
		"\u008f\7\n\2\2\u008f\u0091\5 \21\2\u0090\u008e\3\2\2\2\u0091\u0094\3\2"+
		"\2\2\u0092\u0090\3\2\2\2\u0092\u0093\3\2\2\2\u0093\u0096\3\2\2\2\u0094"+
		"\u0092\3\2\2\2\u0095\u008d\3\2\2\2\u0095\u0096\3\2\2\2\u0096\u0097\3\2"+
		"\2\2\u0097\u0098\7\13\2\2\u0098\u0099\7#\2\2\u0099\23\3\2\2\2\u009a\u009b"+
		"\7 \2\2\u009b\u009c\7!\2\2\u009c\u009d\7\r\2\2\u009d\u009e\7!\2\2\u009e"+
		"\u009f\7\16\2\2\u009f\u00a0\7\"\2\2\u00a0\u00a1\5\26\f\2\u00a1\u00a2\7"+
		"#\2\2\u00a2\25\3\2\2\2\u00a3\u00a4\7\t\2\2\u00a4\u00a9\7\36\2\2\u00a5"+
		"\u00a6\7\n\2\2\u00a6\u00a8\7\36\2\2\u00a7\u00a5\3\2\2\2\u00a8\u00ab\3"+
		"\2\2\2\u00a9\u00a7\3\2\2\2\u00a9\u00aa\3\2\2\2\u00aa\u00ac\3\2\2\2\u00ab"+
		"\u00a9\3\2\2\2\u00ac\u00ad\7\13\2\2\u00ad\27\3\2\2\2\u00ae\u00af\7 \2"+
		"\2\u00af\u00b0\7!\2\2\u00b0\u00b1\7\r\2\2\u00b1\u00b2\7!\2\2\u00b2\u00b3"+
		"\7\17\2\2\u00b3\u00b4\7\"\2\2\u00b4\u00b5\7\'\2\2\u00b5\u00b6\7#\2\2\u00b6"+
		"\31\3\2\2\2\u00b7\u00b8\7 \2\2\u00b8\u00b9\7!\2\2\u00b9\u00ba\7\20\2\2"+
		"\u00ba\u00bb\7\"\2\2\u00bb\u00bc\7\36\2\2\u00bc\u00bd\7#\2\2\u00bd\33"+
		"\3\2\2\2\u00be\u00c0\7\21\2\2\u00bf\u00c1\5\36\20\2\u00c0\u00bf\3\2\2"+
		"\2\u00c1\u00c2\3\2\2\2\u00c2\u00c0\3\2\2\2\u00c2\u00c3\3\2\2\2\u00c3\u00c4"+
		"\3\2\2\2\u00c4\u00c5\7\22\2\2\u00c5\35\3\2\2\2\u00c6\u00d0\5$\23\2\u00c7"+
		"\u00d0\5&\24\2\u00c8\u00d0\5*\26\2\u00c9\u00d0\5,\27\2\u00ca\u00d0\5("+
		"\25\2\u00cb\u00d0\5.\30\2\u00cc\u00d0\5\60\31\2\u00cd\u00d0\5\62\32\2"+
		"\u00ce\u00d0\5\64\33\2\u00cf\u00c6\3\2\2\2\u00cf\u00c7\3\2\2\2\u00cf\u00c8"+
		"\3\2\2\2\u00cf\u00c9\3\2\2\2\u00cf\u00ca\3\2\2\2\u00cf\u00cb\3\2\2\2\u00cf"+
		"\u00cc\3\2\2\2\u00cf\u00cd\3\2\2\2\u00cf\u00ce\3\2\2\2\u00d0\37\3\2\2"+
		"\2\u00d1\u00d3\7\21\2\2\u00d2\u00d4\5\"\22\2\u00d3\u00d2\3\2\2\2\u00d4"+
		"\u00d5\3\2\2\2\u00d5\u00d3\3\2\2\2\u00d5\u00d6\3\2\2\2\u00d6\u00d7\3\2"+
		"\2\2\u00d7\u00d8\7\22\2\2\u00d8!\3\2\2\2\u00d9\u00df\5\66\34\2\u00da\u00df"+
		"\58\35\2\u00db\u00df\5:\36\2\u00dc\u00df\5<\37\2\u00dd\u00df\5> \2\u00de"+
		"\u00d9\3\2\2\2\u00de\u00da\3\2\2\2\u00de\u00db\3\2\2\2\u00de\u00dc\3\2"+
		"\2\2\u00de\u00dd\3\2\2\2\u00df#\3\2\2\2\u00e0\u00e1\7\23\2\2\u00e1\u00e2"+
		"\7\"\2\2\u00e2\u00e3\7\36\2\2\u00e3\u00e4\7#\2\2\u00e4%\3\2\2\2\u00e5"+
		"\u00e6\7\24\2\2\u00e6\u00e7\7\"\2\2\u00e7\u00e8\7$\2\2\u00e8\u00e9\7#"+
		"\2\2\u00e9\'\3\2\2\2\u00ea\u00eb\7\25\2\2\u00eb\u00ec\7\"\2\2\u00ec\u00ed"+
		"\7%\2\2\u00ed\u00ee\7#\2\2\u00ee)\3\2\2\2\u00ef\u00f0\7\26\2\2\u00f0\u00f1"+
		"\7\"\2\2\u00f1\u00f2\7%\2\2\u00f2\u00f3\7#\2\2\u00f3+\3\2\2\2\u00f4\u00f5"+
		"\7\27\2\2\u00f5\u00f6\7\"\2\2\u00f6\u00f7\7(\2\2\u00f7\u00f8\7#\2\2\u00f8"+
		"-\3\2\2\2\u00f9\u00fa\7\30\2\2\u00fa\u00fb\7\"\2\2\u00fb\u00fc\7\36\2"+
		"\2\u00fc\u00fd\7#\2\2\u00fd/\3\2\2\2\u00fe\u00ff\7\31\2\2\u00ff\u0100"+
		"\7\"\2\2\u0100\u0101\t\2\2\2\u0101\u0102\7#\2\2\u0102\61\3\2\2\2\u0103"+
		"\u0104\7\32\2\2\u0104\u0105\7\"\2\2\u0105\u0106\7(\2\2\u0106\u0107\7#"+
		"\2\2\u0107\63\3\2\2\2\u0108\u0109\7\33\2\2\u0109\u010a\7\"\2\2\u010a\u010b"+
		"\7(\2\2\u010b\u010c\7#\2\2\u010c\65\3\2\2\2\u010d\u010e\7\23\2\2\u010e"+
		"\u010f\7\"\2\2\u010f\u0110\7\36\2\2\u0110\u0111\7#\2\2\u0111\67\3\2\2"+
		"\2\u0112\u0113\7\26\2\2\u0113\u0114\7\"\2\2\u0114\u0115\7%\2\2\u0115\u0116"+
		"\7#\2\2\u01169\3\2\2\2\u0117\u0118\7\34\2\2\u0118\u0119\7\"\2\2\u0119"+
		"\u011a\7&\2\2\u011a\u011b\7#\2\2\u011b;\3\2\2\2\u011c\u011d\7\35\2\2\u011d"+
		"\u011e\7\"\2\2\u011e\u011f\5@!\2\u011f=\3\2\2\2\u0120\u0121\7\17\2\2\u0121"+
		"\u0122\7\"\2\2\u0122\u0123\7\'\2\2\u0123\u0124\7#\2\2\u0124?\3\2\2\2\u0125"+
		"\u012e\7\t\2\2\u0126\u012b\7\36\2\2\u0127\u0128\7\n\2\2\u0128\u012a\7"+
		"\36\2\2\u0129\u0127\3\2\2\2\u012a\u012d\3\2\2\2\u012b\u0129\3\2\2\2\u012b"+
		"\u012c\3\2\2\2\u012c\u012f\3\2\2\2\u012d\u012b\3\2\2\2\u012e\u0126\3\2"+
		"\2\2\u012e\u012f\3\2\2\2\u012f\u0130\3\2\2\2\u0130\u0131\7\13\2\2\u0131"+
		"\u0132\7#\2\2\u0132A\3\2\2\2\17EQ\u0080\u0083\u0092\u0095\u00a9\u00c2"+
		"\u00cf\u00d5\u00de\u012b\u012e";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}