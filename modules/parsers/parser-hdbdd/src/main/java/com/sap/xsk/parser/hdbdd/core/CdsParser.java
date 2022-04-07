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
// Generated from com/sap/xsk/parser/hdbdd/core/Cds.g4 by ANTLR 4.9.3
package com.sap.xsk.parser.hdbdd.core;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CdsParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, NAMESPACE=19, AS=20, ON=21, SELECT=22, FROM=23, WHERE=24, DEFINE=25, 
		UNION=26, DISTINCT=27, JOIN_TYPES=28, INNER_JOIN=29, LEFT_JOIN=30, LEFT_OUTER_JOIN=31, 
		RIGHT_OUTER_JOIN=32, FULL_OUTER_JOIN=33, REFERNTIAL_JOIN=34, TEXT_JOIN=35, 
		BUILT_IN_HANA_TYPE=36, DATETIME_VALUE_FUNCTION=37, USING=38, NULL=39, 
		CONCATENATION=40, NOT_EQUAL_TO=41, DEFAULT=42, ASSOCIATION_MIN=43, BOOLEAN=44, 
		CONTEXT=45, ENTITY=46, VIEW=47, TYPE=48, ASSOCIATION=49, TO=50, ID=51, 
		SEMICOLUMN=52, INTEGER=53, DECIMAL=54, LOCAL_TIME=55, LOCAL_DATE=56, UTC_DATE_TIME=57, 
		UTC_TIMESTAMP=58, STRING=59, VARBINARY=60, TYPE_OF=61, WS=62, LINE_COMMENT1=63, 
		LINE_COMMENT2=64, A=65, B=66, C=67, D=68, E=69, F=70, G=71, H=72, I=73, 
		J=74, K=75, L=76, M=77, N=78, O=79, P=80, Q=81, R=82, S=83, T=84, U=85, 
		V=86, W=87, X=88, Y=89, Z=90;
	public static final int
		RULE_cdsFile = 0, RULE_namespaceRule = 1, RULE_usingRule = 2, RULE_topLevelSymbol = 3, 
		RULE_dataTypeRule = 4, RULE_contextRule = 5, RULE_structuredTypeRule = 6, 
		RULE_entityRule = 7, RULE_viewRule = 8, RULE_fieldDeclRule = 9, RULE_typeAssignRule = 10, 
		RULE_elementDeclRule = 11, RULE_elementDetails = 12, RULE_elementConstraints = 13, 
		RULE_associationConstraints = 14, RULE_constraints = 15, RULE_association = 16, 
		RULE_associationTarget = 17, RULE_unmanagedForeignKey = 18, RULE_managedForeignKeys = 19, 
		RULE_foreignKey = 20, RULE_cardinality = 21, RULE_defaultValue = 22, RULE_annotationRule = 23, 
		RULE_annValue = 24, RULE_enumRule = 25, RULE_arrRule = 26, RULE_obj = 27, 
		RULE_keyValue = 28, RULE_selectRule = 29, RULE_joinRule = 30, RULE_joinFields = 31, 
		RULE_selectedColumnsRule = 32, RULE_whereRule = 33, RULE_identifier = 34;
	private static String[] makeRuleNames() {
		return new String[] {
			"cdsFile", "namespaceRule", "usingRule", "topLevelSymbol", "dataTypeRule", 
			"contextRule", "structuredTypeRule", "entityRule", "viewRule", "fieldDeclRule", 
			"typeAssignRule", "elementDeclRule", "elementDetails", "elementConstraints", 
			"associationConstraints", "constraints", "association", "associationTarget", 
			"unmanagedForeignKey", "managedForeignKeys", "foreignKey", "cardinality", 
			"defaultValue", "annotationRule", "annValue", "enumRule", "arrRule", 
			"obj", "keyValue", "selectRule", "joinRule", "joinFields", "selectedColumnsRule", 
			"whereRule", "identifier"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'.'", "'::'", "':'", "'{'", "'}'", "'\"'", "'('", "','", "')'", 
			"'not null'", "'NULL'", "'NOT NULL'", "'='", "'['", "'*'", "']'", "'@'", 
			"'#'", null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, "'null'", "'||'", 
			"'<>'", null, null, null, null, null, null, null, null, null, null, "';'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, "NAMESPACE", "AS", "ON", "SELECT", 
			"FROM", "WHERE", "DEFINE", "UNION", "DISTINCT", "JOIN_TYPES", "INNER_JOIN", 
			"LEFT_JOIN", "LEFT_OUTER_JOIN", "RIGHT_OUTER_JOIN", "FULL_OUTER_JOIN", 
			"REFERNTIAL_JOIN", "TEXT_JOIN", "BUILT_IN_HANA_TYPE", "DATETIME_VALUE_FUNCTION", 
			"USING", "NULL", "CONCATENATION", "NOT_EQUAL_TO", "DEFAULT", "ASSOCIATION_MIN", 
			"BOOLEAN", "CONTEXT", "ENTITY", "VIEW", "TYPE", "ASSOCIATION", "TO", 
			"ID", "SEMICOLUMN", "INTEGER", "DECIMAL", "LOCAL_TIME", "LOCAL_DATE", 
			"UTC_DATE_TIME", "UTC_TIMESTAMP", "STRING", "VARBINARY", "TYPE_OF", "WS", 
			"LINE_COMMENT1", "LINE_COMMENT2", "A", "B", "C", "D", "E", "F", "G", 
			"H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", 
			"V", "W", "X", "Y", "Z"
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
	public String getGrammarFileName() { return "Cds.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public CdsParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class CdsFileContext extends ParserRuleContext {
		public NamespaceRuleContext namespaceRule() {
			return getRuleContext(NamespaceRuleContext.class,0);
		}
		public TopLevelSymbolContext topLevelSymbol() {
			return getRuleContext(TopLevelSymbolContext.class,0);
		}
		public List<UsingRuleContext> usingRule() {
			return getRuleContexts(UsingRuleContext.class);
		}
		public UsingRuleContext usingRule(int i) {
			return getRuleContext(UsingRuleContext.class,i);
		}
		public CdsFileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cdsFile; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).enterCdsFile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).exitCdsFile(this);
		}
	}

	public final CdsFileContext cdsFile() throws RecognitionException {
		CdsFileContext _localctx = new CdsFileContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_cdsFile);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(70);
			namespaceRule();
			setState(74);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==USING) {
				{
				{
				setState(71);
				usingRule();
				}
				}
				setState(76);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(77);
			topLevelSymbol();
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

	public static class NamespaceRuleContext extends ParserRuleContext {
		public IdentifierContext identifier;
		public List<IdentifierContext> members = new ArrayList<IdentifierContext>();
		public TerminalNode NAMESPACE() { return getToken(CdsParser.NAMESPACE, 0); }
		public TerminalNode SEMICOLUMN() { return getToken(CdsParser.SEMICOLUMN, 0); }
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public NamespaceRuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_namespaceRule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).enterNamespaceRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).exitNamespaceRule(this);
		}
	}

	public final NamespaceRuleContext namespaceRule() throws RecognitionException {
		NamespaceRuleContext _localctx = new NamespaceRuleContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_namespaceRule);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(79);
			match(NAMESPACE);
			setState(80);
			((NamespaceRuleContext)_localctx).identifier = identifier();
			((NamespaceRuleContext)_localctx).members.add(((NamespaceRuleContext)_localctx).identifier);
			setState(85);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(81);
				match(T__0);
				setState(82);
				((NamespaceRuleContext)_localctx).identifier = identifier();
				((NamespaceRuleContext)_localctx).members.add(((NamespaceRuleContext)_localctx).identifier);
				}
				}
				setState(87);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(88);
			match(SEMICOLUMN);
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

	public static class UsingRuleContext extends ParserRuleContext {
		public IdentifierContext identifier;
		public List<IdentifierContext> pack = new ArrayList<IdentifierContext>();
		public List<IdentifierContext> members = new ArrayList<IdentifierContext>();
		public IdentifierContext alias;
		public TerminalNode USING() { return getToken(CdsParser.USING, 0); }
		public TerminalNode SEMICOLUMN() { return getToken(CdsParser.SEMICOLUMN, 0); }
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public TerminalNode AS() { return getToken(CdsParser.AS, 0); }
		public UsingRuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_usingRule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).enterUsingRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).exitUsingRule(this);
		}
	}

	public final UsingRuleContext usingRule() throws RecognitionException {
		UsingRuleContext _localctx = new UsingRuleContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_usingRule);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(90);
			match(USING);
			setState(91);
			((UsingRuleContext)_localctx).identifier = identifier();
			((UsingRuleContext)_localctx).pack.add(((UsingRuleContext)_localctx).identifier);
			setState(96);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(92);
				match(T__0);
				setState(93);
				((UsingRuleContext)_localctx).identifier = identifier();
				((UsingRuleContext)_localctx).pack.add(((UsingRuleContext)_localctx).identifier);
				}
				}
				setState(98);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(99);
			match(T__1);
			setState(100);
			((UsingRuleContext)_localctx).identifier = identifier();
			((UsingRuleContext)_localctx).members.add(((UsingRuleContext)_localctx).identifier);
			setState(105);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(101);
				match(T__0);
				setState(102);
				((UsingRuleContext)_localctx).identifier = identifier();
				((UsingRuleContext)_localctx).members.add(((UsingRuleContext)_localctx).identifier);
				}
				}
				setState(107);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(110);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==AS) {
				{
				setState(108);
				match(AS);
				setState(109);
				((UsingRuleContext)_localctx).alias = identifier();
				}
			}

			setState(112);
			match(SEMICOLUMN);
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

	public static class TopLevelSymbolContext extends ParserRuleContext {
		public List<DataTypeRuleContext> dataTypeRule() {
			return getRuleContexts(DataTypeRuleContext.class);
		}
		public DataTypeRuleContext dataTypeRule(int i) {
			return getRuleContext(DataTypeRuleContext.class,i);
		}
		public ContextRuleContext contextRule() {
			return getRuleContext(ContextRuleContext.class,0);
		}
		public StructuredTypeRuleContext structuredTypeRule() {
			return getRuleContext(StructuredTypeRuleContext.class,0);
		}
		public EntityRuleContext entityRule() {
			return getRuleContext(EntityRuleContext.class,0);
		}
		public ViewRuleContext viewRule() {
			return getRuleContext(ViewRuleContext.class,0);
		}
		public TopLevelSymbolContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_topLevelSymbol; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).enterTopLevelSymbol(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).exitTopLevelSymbol(this);
		}
	}

	public final TopLevelSymbolContext topLevelSymbol() throws RecognitionException {
		TopLevelSymbolContext _localctx = new TopLevelSymbolContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_topLevelSymbol);
		int _la;
		try {
			setState(132);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(117);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__16 || _la==TYPE) {
					{
					{
					setState(114);
					dataTypeRule();
					}
					}
					setState(119);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(121);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__16 || _la==CONTEXT) {
					{
					setState(120);
					contextRule();
					}
				}

				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(124);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__16 || _la==TYPE) {
					{
					setState(123);
					structuredTypeRule();
					}
				}

				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(127);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__16 || _la==ENTITY) {
					{
					setState(126);
					entityRule();
					}
				}

				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(130);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__16) | (1L << DEFINE) | (1L << VIEW))) != 0)) {
					{
					setState(129);
					viewRule();
					}
				}

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

	public static class DataTypeRuleContext extends ParserRuleContext {
		public Token artifactType;
		public IdentifierContext artifactName;
		public TypeAssignRuleContext typeAssignRule() {
			return getRuleContext(TypeAssignRuleContext.class,0);
		}
		public TerminalNode SEMICOLUMN() { return getToken(CdsParser.SEMICOLUMN, 0); }
		public TerminalNode TYPE() { return getToken(CdsParser.TYPE, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public List<AnnotationRuleContext> annotationRule() {
			return getRuleContexts(AnnotationRuleContext.class);
		}
		public AnnotationRuleContext annotationRule(int i) {
			return getRuleContext(AnnotationRuleContext.class,i);
		}
		public DataTypeRuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dataTypeRule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).enterDataTypeRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).exitDataTypeRule(this);
		}
	}

	public final DataTypeRuleContext dataTypeRule() throws RecognitionException {
		DataTypeRuleContext _localctx = new DataTypeRuleContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_dataTypeRule);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(137);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__16) {
				{
				{
				setState(134);
				annotationRule();
				}
				}
				setState(139);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(140);
			((DataTypeRuleContext)_localctx).artifactType = match(TYPE);
			setState(141);
			((DataTypeRuleContext)_localctx).artifactName = identifier();
			setState(142);
			match(T__2);
			setState(143);
			typeAssignRule();
			setState(144);
			match(SEMICOLUMN);
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

	public static class ContextRuleContext extends ParserRuleContext {
		public Token artifactType;
		public IdentifierContext artifactName;
		public TerminalNode CONTEXT() { return getToken(CdsParser.CONTEXT, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public List<AnnotationRuleContext> annotationRule() {
			return getRuleContexts(AnnotationRuleContext.class);
		}
		public AnnotationRuleContext annotationRule(int i) {
			return getRuleContext(AnnotationRuleContext.class,i);
		}
		public List<ContextRuleContext> contextRule() {
			return getRuleContexts(ContextRuleContext.class);
		}
		public ContextRuleContext contextRule(int i) {
			return getRuleContext(ContextRuleContext.class,i);
		}
		public List<DataTypeRuleContext> dataTypeRule() {
			return getRuleContexts(DataTypeRuleContext.class);
		}
		public DataTypeRuleContext dataTypeRule(int i) {
			return getRuleContext(DataTypeRuleContext.class,i);
		}
		public List<StructuredTypeRuleContext> structuredTypeRule() {
			return getRuleContexts(StructuredTypeRuleContext.class);
		}
		public StructuredTypeRuleContext structuredTypeRule(int i) {
			return getRuleContext(StructuredTypeRuleContext.class,i);
		}
		public List<EntityRuleContext> entityRule() {
			return getRuleContexts(EntityRuleContext.class);
		}
		public EntityRuleContext entityRule(int i) {
			return getRuleContext(EntityRuleContext.class,i);
		}
		public List<ViewRuleContext> viewRule() {
			return getRuleContexts(ViewRuleContext.class);
		}
		public ViewRuleContext viewRule(int i) {
			return getRuleContext(ViewRuleContext.class,i);
		}
		public TerminalNode SEMICOLUMN() { return getToken(CdsParser.SEMICOLUMN, 0); }
		public ContextRuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_contextRule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).enterContextRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).exitContextRule(this);
		}
	}

	public final ContextRuleContext contextRule() throws RecognitionException {
		ContextRuleContext _localctx = new ContextRuleContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_contextRule);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(149);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__16) {
				{
				{
				setState(146);
				annotationRule();
				}
				}
				setState(151);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(152);
			((ContextRuleContext)_localctx).artifactType = match(CONTEXT);
			setState(153);
			((ContextRuleContext)_localctx).artifactName = identifier();
			setState(154);
			match(T__3);
			setState(162);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__16) | (1L << DEFINE) | (1L << CONTEXT) | (1L << ENTITY) | (1L << VIEW) | (1L << TYPE))) != 0)) {
				{
				setState(160);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
				case 1:
					{
					setState(155);
					contextRule();
					}
					break;
				case 2:
					{
					setState(156);
					dataTypeRule();
					}
					break;
				case 3:
					{
					setState(157);
					structuredTypeRule();
					}
					break;
				case 4:
					{
					setState(158);
					entityRule();
					}
					break;
				case 5:
					{
					setState(159);
					viewRule();
					}
					break;
				}
				}
				setState(164);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(165);
			match(T__4);
			setState(167);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMICOLUMN) {
				{
				setState(166);
				match(SEMICOLUMN);
				}
			}

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

	public static class StructuredTypeRuleContext extends ParserRuleContext {
		public Token artifactType;
		public IdentifierContext artifactName;
		public TerminalNode TYPE() { return getToken(CdsParser.TYPE, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public List<AnnotationRuleContext> annotationRule() {
			return getRuleContexts(AnnotationRuleContext.class);
		}
		public AnnotationRuleContext annotationRule(int i) {
			return getRuleContext(AnnotationRuleContext.class,i);
		}
		public List<FieldDeclRuleContext> fieldDeclRule() {
			return getRuleContexts(FieldDeclRuleContext.class);
		}
		public FieldDeclRuleContext fieldDeclRule(int i) {
			return getRuleContext(FieldDeclRuleContext.class,i);
		}
		public TerminalNode SEMICOLUMN() { return getToken(CdsParser.SEMICOLUMN, 0); }
		public StructuredTypeRuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_structuredTypeRule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).enterStructuredTypeRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).exitStructuredTypeRule(this);
		}
	}

	public final StructuredTypeRuleContext structuredTypeRule() throws RecognitionException {
		StructuredTypeRuleContext _localctx = new StructuredTypeRuleContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_structuredTypeRule);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(172);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__16) {
				{
				{
				setState(169);
				annotationRule();
				}
				}
				setState(174);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(175);
			((StructuredTypeRuleContext)_localctx).artifactType = match(TYPE);
			setState(176);
			((StructuredTypeRuleContext)_localctx).artifactName = identifier();
			setState(177);
			match(T__3);
			setState(181);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << NAMESPACE) | (1L << AS) | (1L << ON) | (1L << SELECT) | (1L << FROM) | (1L << WHERE) | (1L << DEFINE) | (1L << UNION) | (1L << DISTINCT) | (1L << JOIN_TYPES) | (1L << USING) | (1L << DEFAULT) | (1L << CONTEXT) | (1L << ENTITY) | (1L << VIEW) | (1L << TYPE) | (1L << ASSOCIATION) | (1L << TO) | (1L << ID))) != 0)) {
				{
				{
				setState(178);
				fieldDeclRule();
				}
				}
				setState(183);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(184);
			match(T__4);
			setState(186);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMICOLUMN) {
				{
				setState(185);
				match(SEMICOLUMN);
				}
			}

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

	public static class EntityRuleContext extends ParserRuleContext {
		public Token artifactType;
		public IdentifierContext artifactName;
		public TerminalNode ENTITY() { return getToken(CdsParser.ENTITY, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public List<AnnotationRuleContext> annotationRule() {
			return getRuleContexts(AnnotationRuleContext.class);
		}
		public AnnotationRuleContext annotationRule(int i) {
			return getRuleContext(AnnotationRuleContext.class,i);
		}
		public List<ElementDeclRuleContext> elementDeclRule() {
			return getRuleContexts(ElementDeclRuleContext.class);
		}
		public ElementDeclRuleContext elementDeclRule(int i) {
			return getRuleContext(ElementDeclRuleContext.class,i);
		}
		public List<AssociationContext> association() {
			return getRuleContexts(AssociationContext.class);
		}
		public AssociationContext association(int i) {
			return getRuleContext(AssociationContext.class,i);
		}
		public TerminalNode SEMICOLUMN() { return getToken(CdsParser.SEMICOLUMN, 0); }
		public EntityRuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_entityRule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).enterEntityRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).exitEntityRule(this);
		}
	}

	public final EntityRuleContext entityRule() throws RecognitionException {
		EntityRuleContext _localctx = new EntityRuleContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_entityRule);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(191);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__16) {
				{
				{
				setState(188);
				annotationRule();
				}
				}
				setState(193);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(194);
			((EntityRuleContext)_localctx).artifactType = match(ENTITY);
			setState(195);
			((EntityRuleContext)_localctx).artifactName = identifier();
			setState(196);
			match(T__3);
			setState(201);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__16) | (1L << NAMESPACE) | (1L << AS) | (1L << ON) | (1L << SELECT) | (1L << FROM) | (1L << WHERE) | (1L << DEFINE) | (1L << UNION) | (1L << DISTINCT) | (1L << JOIN_TYPES) | (1L << USING) | (1L << DEFAULT) | (1L << CONTEXT) | (1L << ENTITY) | (1L << VIEW) | (1L << TYPE) | (1L << ASSOCIATION) | (1L << TO) | (1L << ID))) != 0)) {
				{
				setState(199);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
				case 1:
					{
					setState(197);
					elementDeclRule();
					}
					break;
				case 2:
					{
					setState(198);
					association();
					}
					break;
				}
				}
				setState(203);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(204);
			match(T__4);
			setState(206);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMICOLUMN) {
				{
				setState(205);
				match(SEMICOLUMN);
				}
			}

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

	public static class ViewRuleContext extends ParserRuleContext {
		public Token artifactType;
		public IdentifierContext artifactName;
		public TerminalNode AS() { return getToken(CdsParser.AS, 0); }
		public TerminalNode VIEW() { return getToken(CdsParser.VIEW, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public List<AnnotationRuleContext> annotationRule() {
			return getRuleContexts(AnnotationRuleContext.class);
		}
		public AnnotationRuleContext annotationRule(int i) {
			return getRuleContext(AnnotationRuleContext.class,i);
		}
		public TerminalNode DEFINE() { return getToken(CdsParser.DEFINE, 0); }
		public List<SelectRuleContext> selectRule() {
			return getRuleContexts(SelectRuleContext.class);
		}
		public SelectRuleContext selectRule(int i) {
			return getRuleContext(SelectRuleContext.class,i);
		}
		public ViewRuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_viewRule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).enterViewRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).exitViewRule(this);
		}
	}

	public final ViewRuleContext viewRule() throws RecognitionException {
		ViewRuleContext _localctx = new ViewRuleContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_viewRule);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(211);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__16) {
				{
				{
				setState(208);
				annotationRule();
				}
				}
				setState(213);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(215);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DEFINE) {
				{
				setState(214);
				match(DEFINE);
				}
			}

			setState(217);
			((ViewRuleContext)_localctx).artifactType = match(VIEW);
			setState(218);
			((ViewRuleContext)_localctx).artifactName = identifier();
			setState(219);
			match(AS);
			setState(223);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SELECT || _la==UNION) {
				{
				{
				setState(220);
				selectRule();
				}
				}
				setState(225);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
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

	public static class FieldDeclRuleContext extends ParserRuleContext {
		public TypeAssignRuleContext typeAssignRule() {
			return getRuleContext(TypeAssignRuleContext.class,0);
		}
		public TerminalNode SEMICOLUMN() { return getToken(CdsParser.SEMICOLUMN, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public FieldDeclRuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fieldDeclRule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).enterFieldDeclRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).exitFieldDeclRule(this);
		}
	}

	public final FieldDeclRuleContext fieldDeclRule() throws RecognitionException {
		FieldDeclRuleContext _localctx = new FieldDeclRuleContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_fieldDeclRule);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(231);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NAMESPACE:
			case AS:
			case ON:
			case SELECT:
			case FROM:
			case WHERE:
			case DEFINE:
			case UNION:
			case DISTINCT:
			case JOIN_TYPES:
			case USING:
			case DEFAULT:
			case CONTEXT:
			case ENTITY:
			case VIEW:
			case TYPE:
			case ASSOCIATION:
			case TO:
			case ID:
				{
				setState(226);
				identifier();
				}
				break;
			case T__5:
				{
				setState(227);
				match(T__5);
				setState(228);
				identifier();
				setState(229);
				match(T__5);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(233);
			match(T__2);
			setState(234);
			typeAssignRule();
			setState(235);
			match(SEMICOLUMN);
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

	public static class TypeAssignRuleContext extends ParserRuleContext {
		public TypeAssignRuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeAssignRule; }
	 
		public TypeAssignRuleContext() { }
		public void copyFrom(TypeAssignRuleContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class AssignBuiltInTypeWithArgsContext extends TypeAssignRuleContext {
		public IdentifierContext ref;
		public Token INTEGER;
		public List<Token> args = new ArrayList<Token>();
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public List<TerminalNode> INTEGER() { return getTokens(CdsParser.INTEGER); }
		public TerminalNode INTEGER(int i) {
			return getToken(CdsParser.INTEGER, i);
		}
		public AssignBuiltInTypeWithArgsContext(TypeAssignRuleContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).enterAssignBuiltInTypeWithArgs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).exitAssignBuiltInTypeWithArgs(this);
		}
	}
	public static class AssignHanaTypeWithArgsContext extends TypeAssignRuleContext {
		public Token hanaType;
		public Token INTEGER;
		public List<Token> args = new ArrayList<Token>();
		public TerminalNode BUILT_IN_HANA_TYPE() { return getToken(CdsParser.BUILT_IN_HANA_TYPE, 0); }
		public List<TerminalNode> INTEGER() { return getTokens(CdsParser.INTEGER); }
		public TerminalNode INTEGER(int i) {
			return getToken(CdsParser.INTEGER, i);
		}
		public AssignHanaTypeWithArgsContext(TypeAssignRuleContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).enterAssignHanaTypeWithArgs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).exitAssignHanaTypeWithArgs(this);
		}
	}
	public static class AssignHanaTypeContext extends TypeAssignRuleContext {
		public Token hanaType;
		public TerminalNode BUILT_IN_HANA_TYPE() { return getToken(CdsParser.BUILT_IN_HANA_TYPE, 0); }
		public AssignHanaTypeContext(TypeAssignRuleContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).enterAssignHanaType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).exitAssignHanaType(this);
		}
	}
	public static class AssignTypeContext extends TypeAssignRuleContext {
		public IdentifierContext identifier;
		public List<IdentifierContext> pathSubMembers = new ArrayList<IdentifierContext>();
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public TerminalNode TYPE_OF() { return getToken(CdsParser.TYPE_OF, 0); }
		public AssignTypeContext(TypeAssignRuleContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).enterAssignType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).exitAssignType(this);
		}
	}

	public final TypeAssignRuleContext typeAssignRule() throws RecognitionException {
		TypeAssignRuleContext _localctx = new TypeAssignRuleContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_typeAssignRule);
		int _la;
		try {
			setState(272);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
			case 1:
				_localctx = new AssignBuiltInTypeWithArgsContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(237);
				((AssignBuiltInTypeWithArgsContext)_localctx).ref = identifier();
				setState(238);
				match(T__6);
				setState(239);
				((AssignBuiltInTypeWithArgsContext)_localctx).INTEGER = match(INTEGER);
				((AssignBuiltInTypeWithArgsContext)_localctx).args.add(((AssignBuiltInTypeWithArgsContext)_localctx).INTEGER);
				setState(244);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__7) {
					{
					{
					setState(240);
					match(T__7);
					setState(241);
					((AssignBuiltInTypeWithArgsContext)_localctx).INTEGER = match(INTEGER);
					((AssignBuiltInTypeWithArgsContext)_localctx).args.add(((AssignBuiltInTypeWithArgsContext)_localctx).INTEGER);
					}
					}
					setState(246);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(247);
				match(T__8);
				}
				break;
			case 2:
				_localctx = new AssignHanaTypeContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(249);
				((AssignHanaTypeContext)_localctx).hanaType = match(BUILT_IN_HANA_TYPE);
				}
				break;
			case 3:
				_localctx = new AssignHanaTypeWithArgsContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(250);
				((AssignHanaTypeWithArgsContext)_localctx).hanaType = match(BUILT_IN_HANA_TYPE);
				setState(251);
				match(T__6);
				setState(252);
				((AssignHanaTypeWithArgsContext)_localctx).INTEGER = match(INTEGER);
				((AssignHanaTypeWithArgsContext)_localctx).args.add(((AssignHanaTypeWithArgsContext)_localctx).INTEGER);
				setState(257);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__7) {
					{
					{
					setState(253);
					match(T__7);
					setState(254);
					((AssignHanaTypeWithArgsContext)_localctx).INTEGER = match(INTEGER);
					((AssignHanaTypeWithArgsContext)_localctx).args.add(((AssignHanaTypeWithArgsContext)_localctx).INTEGER);
					}
					}
					setState(259);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(260);
				match(T__8);
				}
				break;
			case 4:
				_localctx = new AssignTypeContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(262);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==TYPE_OF) {
					{
					setState(261);
					match(TYPE_OF);
					}
				}

				setState(264);
				((AssignTypeContext)_localctx).identifier = identifier();
				((AssignTypeContext)_localctx).pathSubMembers.add(((AssignTypeContext)_localctx).identifier);
				setState(269);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__0) {
					{
					{
					setState(265);
					match(T__0);
					setState(266);
					((AssignTypeContext)_localctx).identifier = identifier();
					((AssignTypeContext)_localctx).pathSubMembers.add(((AssignTypeContext)_localctx).identifier);
					}
					}
					setState(271);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
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

	public static class ElementDeclRuleContext extends ParserRuleContext {
		public IdentifierContext key;
		public IdentifierContext name;
		public TypeAssignRuleContext typeAssignRule() {
			return getRuleContext(TypeAssignRuleContext.class,0);
		}
		public TerminalNode SEMICOLUMN() { return getToken(CdsParser.SEMICOLUMN, 0); }
		public List<AnnotationRuleContext> annotationRule() {
			return getRuleContexts(AnnotationRuleContext.class);
		}
		public AnnotationRuleContext annotationRule(int i) {
			return getRuleContext(AnnotationRuleContext.class,i);
		}
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public List<ElementDetailsContext> elementDetails() {
			return getRuleContexts(ElementDetailsContext.class);
		}
		public ElementDetailsContext elementDetails(int i) {
			return getRuleContext(ElementDetailsContext.class,i);
		}
		public ElementDeclRuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elementDeclRule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).enterElementDeclRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).exitElementDeclRule(this);
		}
	}

	public final ElementDeclRuleContext elementDeclRule() throws RecognitionException {
		ElementDeclRuleContext _localctx = new ElementDeclRuleContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_elementDeclRule);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(277);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__16) {
				{
				{
				setState(274);
				annotationRule();
				}
				}
				setState(279);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(281);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,33,_ctx) ) {
			case 1:
				{
				setState(280);
				((ElementDeclRuleContext)_localctx).key = identifier();
				}
				break;
			}
			setState(288);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NAMESPACE:
			case AS:
			case ON:
			case SELECT:
			case FROM:
			case WHERE:
			case DEFINE:
			case UNION:
			case DISTINCT:
			case JOIN_TYPES:
			case USING:
			case DEFAULT:
			case CONTEXT:
			case ENTITY:
			case VIEW:
			case TYPE:
			case ASSOCIATION:
			case TO:
			case ID:
				{
				setState(283);
				((ElementDeclRuleContext)_localctx).name = identifier();
				}
				break;
			case T__5:
				{
				setState(284);
				match(T__5);
				setState(285);
				((ElementDeclRuleContext)_localctx).name = identifier();
				setState(286);
				match(T__5);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(290);
			match(T__2);
			setState(291);
			typeAssignRule();
			setState(295);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << NULL) | (1L << DEFAULT))) != 0)) {
				{
				{
				setState(292);
				elementDetails();
				}
				}
				setState(297);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(298);
			match(SEMICOLUMN);
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

	public static class ElementDetailsContext extends ParserRuleContext {
		public DefaultValueContext defaultValue() {
			return getRuleContext(DefaultValueContext.class,0);
		}
		public ElementConstraintsContext elementConstraints() {
			return getRuleContext(ElementConstraintsContext.class,0);
		}
		public ElementDetailsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elementDetails; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).enterElementDetails(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).exitElementDetails(this);
		}
	}

	public final ElementDetailsContext elementDetails() throws RecognitionException {
		ElementDetailsContext _localctx = new ElementDetailsContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_elementDetails);
		try {
			setState(302);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DEFAULT:
				enterOuterAlt(_localctx, 1);
				{
				setState(300);
				defaultValue();
				}
				break;
			case T__9:
			case T__10:
			case T__11:
			case NULL:
				enterOuterAlt(_localctx, 2);
				{
				setState(301);
				elementConstraints();
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

	public static class ElementConstraintsContext extends ParserRuleContext {
		public ConstraintsContext constraints() {
			return getRuleContext(ConstraintsContext.class,0);
		}
		public ElementConstraintsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elementConstraints; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).enterElementConstraints(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).exitElementConstraints(this);
		}
	}

	public final ElementConstraintsContext elementConstraints() throws RecognitionException {
		ElementConstraintsContext _localctx = new ElementConstraintsContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_elementConstraints);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(304);
			constraints();
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

	public static class AssociationConstraintsContext extends ParserRuleContext {
		public ConstraintsContext constraints() {
			return getRuleContext(ConstraintsContext.class,0);
		}
		public AssociationConstraintsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_associationConstraints; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).enterAssociationConstraints(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).exitAssociationConstraints(this);
		}
	}

	public final AssociationConstraintsContext associationConstraints() throws RecognitionException {
		AssociationConstraintsContext _localctx = new AssociationConstraintsContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_associationConstraints);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(306);
			constraints();
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

	public static class ConstraintsContext extends ParserRuleContext {
		public TerminalNode NULL() { return getToken(CdsParser.NULL, 0); }
		public ConstraintsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constraints; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).enterConstraints(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).exitConstraints(this);
		}
	}

	public final ConstraintsContext constraints() throws RecognitionException {
		ConstraintsContext _localctx = new ConstraintsContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_constraints);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(308);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << NULL))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
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

	public static class AssociationContext extends ParserRuleContext {
		public IdentifierContext key;
		public IdentifierContext ascId;
		public TerminalNode ASSOCIATION() { return getToken(CdsParser.ASSOCIATION, 0); }
		public TerminalNode TO() { return getToken(CdsParser.TO, 0); }
		public AssociationTargetContext associationTarget() {
			return getRuleContext(AssociationTargetContext.class,0);
		}
		public TerminalNode SEMICOLUMN() { return getToken(CdsParser.SEMICOLUMN, 0); }
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public CardinalityContext cardinality() {
			return getRuleContext(CardinalityContext.class,0);
		}
		public List<ManagedForeignKeysContext> managedForeignKeys() {
			return getRuleContexts(ManagedForeignKeysContext.class);
		}
		public ManagedForeignKeysContext managedForeignKeys(int i) {
			return getRuleContext(ManagedForeignKeysContext.class,i);
		}
		public List<UnmanagedForeignKeyContext> unmanagedForeignKey() {
			return getRuleContexts(UnmanagedForeignKeyContext.class);
		}
		public UnmanagedForeignKeyContext unmanagedForeignKey(int i) {
			return getRuleContext(UnmanagedForeignKeyContext.class,i);
		}
		public AssociationConstraintsContext associationConstraints() {
			return getRuleContext(AssociationConstraintsContext.class,0);
		}
		public AssociationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_association; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).enterAssociation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).exitAssociation(this);
		}
	}

	public final AssociationContext association() throws RecognitionException {
		AssociationContext _localctx = new AssociationContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_association);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(311);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
			case 1:
				{
				setState(310);
				((AssociationContext)_localctx).key = identifier();
				}
				break;
			}
			setState(313);
			((AssociationContext)_localctx).ascId = identifier();
			setState(314);
			match(T__2);
			setState(315);
			match(ASSOCIATION);
			setState(317);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__13) {
				{
				setState(316);
				cardinality();
				}
			}

			setState(319);
			match(TO);
			setState(320);
			associationTarget();
			setState(325);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3 || _la==ON) {
				{
				setState(323);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__3:
					{
					setState(321);
					managedForeignKeys();
					}
					break;
				case ON:
					{
					setState(322);
					unmanagedForeignKey();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(327);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(329);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << NULL))) != 0)) {
				{
				setState(328);
				associationConstraints();
				}
			}

			setState(331);
			match(SEMICOLUMN);
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

	public static class AssociationTargetContext extends ParserRuleContext {
		public IdentifierContext identifier;
		public List<IdentifierContext> pathSubMembers = new ArrayList<IdentifierContext>();
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public AssociationTargetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_associationTarget; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).enterAssociationTarget(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).exitAssociationTarget(this);
		}
	}

	public final AssociationTargetContext associationTarget() throws RecognitionException {
		AssociationTargetContext _localctx = new AssociationTargetContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_associationTarget);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(333);
			((AssociationTargetContext)_localctx).identifier = identifier();
			((AssociationTargetContext)_localctx).pathSubMembers.add(((AssociationTargetContext)_localctx).identifier);
			setState(338);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(334);
				match(T__0);
				setState(335);
				((AssociationTargetContext)_localctx).identifier = identifier();
				((AssociationTargetContext)_localctx).pathSubMembers.add(((AssociationTargetContext)_localctx).identifier);
				}
				}
				setState(340);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
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

	public static class UnmanagedForeignKeyContext extends ParserRuleContext {
		public IdentifierContext identifier;
		public List<IdentifierContext> pathSubMembers = new ArrayList<IdentifierContext>();
		public IdentifierContext source;
		public TerminalNode ON() { return getToken(CdsParser.ON, 0); }
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public UnmanagedForeignKeyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unmanagedForeignKey; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).enterUnmanagedForeignKey(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).exitUnmanagedForeignKey(this);
		}
	}

	public final UnmanagedForeignKeyContext unmanagedForeignKey() throws RecognitionException {
		UnmanagedForeignKeyContext _localctx = new UnmanagedForeignKeyContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_unmanagedForeignKey);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(341);
			match(ON);
			setState(342);
			((UnmanagedForeignKeyContext)_localctx).identifier = identifier();
			((UnmanagedForeignKeyContext)_localctx).pathSubMembers.add(((UnmanagedForeignKeyContext)_localctx).identifier);
			setState(347);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(343);
				match(T__0);
				setState(344);
				((UnmanagedForeignKeyContext)_localctx).identifier = identifier();
				((UnmanagedForeignKeyContext)_localctx).pathSubMembers.add(((UnmanagedForeignKeyContext)_localctx).identifier);
				}
				}
				setState(349);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(350);
			match(T__12);
			setState(351);
			((UnmanagedForeignKeyContext)_localctx).source = identifier();
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

	public static class ManagedForeignKeysContext extends ParserRuleContext {
		public List<ForeignKeyContext> foreignKey() {
			return getRuleContexts(ForeignKeyContext.class);
		}
		public ForeignKeyContext foreignKey(int i) {
			return getRuleContext(ForeignKeyContext.class,i);
		}
		public ManagedForeignKeysContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_managedForeignKeys; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).enterManagedForeignKeys(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).exitManagedForeignKeys(this);
		}
	}

	public final ManagedForeignKeysContext managedForeignKeys() throws RecognitionException {
		ManagedForeignKeysContext _localctx = new ManagedForeignKeysContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_managedForeignKeys);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(353);
			match(T__3);
			setState(354);
			foreignKey();
			setState(359);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__7) {
				{
				{
				setState(355);
				match(T__7);
				setState(356);
				foreignKey();
				}
				}
				setState(361);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(362);
			match(T__4);
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

	public static class ForeignKeyContext extends ParserRuleContext {
		public IdentifierContext identifier;
		public List<IdentifierContext> pathSubMembers = new ArrayList<IdentifierContext>();
		public IdentifierContext alias;
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public TerminalNode AS() { return getToken(CdsParser.AS, 0); }
		public ForeignKeyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_foreignKey; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).enterForeignKey(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).exitForeignKey(this);
		}
	}

	public final ForeignKeyContext foreignKey() throws RecognitionException {
		ForeignKeyContext _localctx = new ForeignKeyContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_foreignKey);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(364);
			((ForeignKeyContext)_localctx).identifier = identifier();
			((ForeignKeyContext)_localctx).pathSubMembers.add(((ForeignKeyContext)_localctx).identifier);
			setState(369);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(365);
				match(T__0);
				setState(366);
				((ForeignKeyContext)_localctx).identifier = identifier();
				((ForeignKeyContext)_localctx).pathSubMembers.add(((ForeignKeyContext)_localctx).identifier);
				}
				}
				setState(371);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(374);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==AS) {
				{
				setState(372);
				match(AS);
				setState(373);
				((ForeignKeyContext)_localctx).alias = identifier();
				}
			}

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

	public static class CardinalityContext extends ParserRuleContext {
		public CardinalityContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cardinality; }
	 
		public CardinalityContext() { }
		public void copyFrom(CardinalityContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class NoCardinalityContext extends CardinalityContext {
		public NoCardinalityContext(CardinalityContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).enterNoCardinality(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).exitNoCardinality(this);
		}
	}
	public static class MaxCardinalityContext extends CardinalityContext {
		public Token max;
		public Token many;
		public TerminalNode INTEGER() { return getToken(CdsParser.INTEGER, 0); }
		public MaxCardinalityContext(CardinalityContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).enterMaxCardinality(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).exitMaxCardinality(this);
		}
	}
	public static class MinMaxCardinalityContext extends CardinalityContext {
		public Token max;
		public Token many;
		public TerminalNode ASSOCIATION_MIN() { return getToken(CdsParser.ASSOCIATION_MIN, 0); }
		public TerminalNode INTEGER() { return getToken(CdsParser.INTEGER, 0); }
		public MinMaxCardinalityContext(CardinalityContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).enterMinMaxCardinality(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).exitMinMaxCardinality(this);
		}
	}

	public final CardinalityContext cardinality() throws RecognitionException {
		CardinalityContext _localctx = new CardinalityContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_cardinality);
		try {
			setState(391);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,49,_ctx) ) {
			case 1:
				_localctx = new MinMaxCardinalityContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(376);
				match(T__13);
				setState(377);
				match(ASSOCIATION_MIN);
				setState(380);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case INTEGER:
					{
					setState(378);
					((MinMaxCardinalityContext)_localctx).max = match(INTEGER);
					}
					break;
				case T__14:
					{
					setState(379);
					((MinMaxCardinalityContext)_localctx).many = match(T__14);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(382);
				match(T__15);
				}
				break;
			case 2:
				_localctx = new MaxCardinalityContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(383);
				match(T__13);
				setState(386);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case INTEGER:
					{
					setState(384);
					((MaxCardinalityContext)_localctx).max = match(INTEGER);
					}
					break;
				case T__14:
					{
					setState(385);
					((MaxCardinalityContext)_localctx).many = match(T__14);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(388);
				match(T__15);
				}
				break;
			case 3:
				_localctx = new NoCardinalityContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(389);
				match(T__13);
				setState(390);
				match(T__15);
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

	public static class DefaultValueContext extends ParserRuleContext {
		public Token value;
		public TerminalNode DEFAULT() { return getToken(CdsParser.DEFAULT, 0); }
		public TerminalNode STRING() { return getToken(CdsParser.STRING, 0); }
		public TerminalNode INTEGER() { return getToken(CdsParser.INTEGER, 0); }
		public TerminalNode DECIMAL() { return getToken(CdsParser.DECIMAL, 0); }
		public TerminalNode BOOLEAN() { return getToken(CdsParser.BOOLEAN, 0); }
		public TerminalNode LOCAL_TIME() { return getToken(CdsParser.LOCAL_TIME, 0); }
		public TerminalNode LOCAL_DATE() { return getToken(CdsParser.LOCAL_DATE, 0); }
		public TerminalNode UTC_DATE_TIME() { return getToken(CdsParser.UTC_DATE_TIME, 0); }
		public TerminalNode UTC_TIMESTAMP() { return getToken(CdsParser.UTC_TIMESTAMP, 0); }
		public TerminalNode VARBINARY() { return getToken(CdsParser.VARBINARY, 0); }
		public TerminalNode DATETIME_VALUE_FUNCTION() { return getToken(CdsParser.DATETIME_VALUE_FUNCTION, 0); }
		public TerminalNode NULL() { return getToken(CdsParser.NULL, 0); }
		public DefaultValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defaultValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).enterDefaultValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).exitDefaultValue(this);
		}
	}

	public final DefaultValueContext defaultValue() throws RecognitionException {
		DefaultValueContext _localctx = new DefaultValueContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_defaultValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(393);
			match(DEFAULT);
			setState(394);
			((DefaultValueContext)_localctx).value = _input.LT(1);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << DATETIME_VALUE_FUNCTION) | (1L << NULL) | (1L << BOOLEAN) | (1L << INTEGER) | (1L << DECIMAL) | (1L << LOCAL_TIME) | (1L << LOCAL_DATE) | (1L << UTC_DATE_TIME) | (1L << UTC_TIMESTAMP) | (1L << STRING) | (1L << VARBINARY))) != 0)) ) {
				((DefaultValueContext)_localctx).value = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
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

	public static class AnnotationRuleContext extends ParserRuleContext {
		public AnnotationRuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotationRule; }
	 
		public AnnotationRuleContext() { }
		public void copyFrom(AnnotationRuleContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class AnnPropertyRuleContext extends AnnotationRuleContext {
		public IdentifierContext annId;
		public IdentifierContext prop;
		public AnnValueContext annValue() {
			return getRuleContext(AnnValueContext.class,0);
		}
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public AnnPropertyRuleContext(AnnotationRuleContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).enterAnnPropertyRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).exitAnnPropertyRule(this);
		}
	}
	public static class AnnObjectRuleContext extends AnnotationRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public AnnValueContext annValue() {
			return getRuleContext(AnnValueContext.class,0);
		}
		public AnnObjectRuleContext(AnnotationRuleContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).enterAnnObjectRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).exitAnnObjectRule(this);
		}
	}
	public static class AnnMarkerRuleContext extends AnnotationRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public AnnMarkerRuleContext(AnnotationRuleContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).enterAnnMarkerRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).exitAnnMarkerRule(this);
		}
	}

	public final AnnotationRuleContext annotationRule() throws RecognitionException {
		AnnotationRuleContext _localctx = new AnnotationRuleContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_annotationRule);
		int _la;
		try {
			setState(415);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,51,_ctx) ) {
			case 1:
				_localctx = new AnnObjectRuleContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(396);
				match(T__16);
				setState(397);
				identifier();
				setState(398);
				match(T__2);
				setState(399);
				annValue();
				}
				break;
			case 2:
				_localctx = new AnnPropertyRuleContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(401);
				match(T__16);
				setState(402);
				((AnnPropertyRuleContext)_localctx).annId = identifier();
				setState(407);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__0) {
					{
					{
					setState(403);
					match(T__0);
					setState(404);
					((AnnPropertyRuleContext)_localctx).prop = identifier();
					}
					}
					setState(409);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(410);
				match(T__2);
				setState(411);
				annValue();
				}
				break;
			case 3:
				_localctx = new AnnMarkerRuleContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(413);
				match(T__16);
				setState(414);
				identifier();
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

	public static class AnnValueContext extends ParserRuleContext {
		public Token literal;
		public ArrRuleContext arrRule() {
			return getRuleContext(ArrRuleContext.class,0);
		}
		public EnumRuleContext enumRule() {
			return getRuleContext(EnumRuleContext.class,0);
		}
		public ObjContext obj() {
			return getRuleContext(ObjContext.class,0);
		}
		public TerminalNode STRING() { return getToken(CdsParser.STRING, 0); }
		public TerminalNode BOOLEAN() { return getToken(CdsParser.BOOLEAN, 0); }
		public AnnValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).enterAnnValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).exitAnnValue(this);
		}
	}

	public final AnnValueContext annValue() throws RecognitionException {
		AnnValueContext _localctx = new AnnValueContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_annValue);
		int _la;
		try {
			setState(421);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__13:
				enterOuterAlt(_localctx, 1);
				{
				setState(417);
				arrRule();
				}
				break;
			case T__17:
				enterOuterAlt(_localctx, 2);
				{
				setState(418);
				enumRule();
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 3);
				{
				setState(419);
				obj();
				}
				break;
			case BOOLEAN:
			case STRING:
				enterOuterAlt(_localctx, 4);
				{
				setState(420);
				((AnnValueContext)_localctx).literal = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==BOOLEAN || _la==STRING) ) {
					((AnnValueContext)_localctx).literal = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
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

	public static class EnumRuleContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public EnumRuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enumRule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).enterEnumRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).exitEnumRule(this);
		}
	}

	public final EnumRuleContext enumRule() throws RecognitionException {
		EnumRuleContext _localctx = new EnumRuleContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_enumRule);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(423);
			match(T__17);
			setState(424);
			identifier();
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

	public static class ArrRuleContext extends ParserRuleContext {
		public List<AnnValueContext> annValue() {
			return getRuleContexts(AnnValueContext.class);
		}
		public AnnValueContext annValue(int i) {
			return getRuleContext(AnnValueContext.class,i);
		}
		public ArrRuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrRule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).enterArrRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).exitArrRule(this);
		}
	}

	public final ArrRuleContext arrRule() throws RecognitionException {
		ArrRuleContext _localctx = new ArrRuleContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_arrRule);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(426);
			match(T__13);
			setState(427);
			annValue();
			setState(432);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__7) {
				{
				{
				setState(428);
				match(T__7);
				setState(429);
				annValue();
				}
				}
				setState(434);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(435);
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

	public static class ObjContext extends ParserRuleContext {
		public List<KeyValueContext> keyValue() {
			return getRuleContexts(KeyValueContext.class);
		}
		public KeyValueContext keyValue(int i) {
			return getRuleContext(KeyValueContext.class,i);
		}
		public ObjContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_obj; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).enterObj(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).exitObj(this);
		}
	}

	public final ObjContext obj() throws RecognitionException {
		ObjContext _localctx = new ObjContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_obj);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(437);
			match(T__3);
			setState(438);
			keyValue();
			setState(443);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__7) {
				{
				{
				setState(439);
				match(T__7);
				setState(440);
				keyValue();
				}
				}
				setState(445);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(446);
			match(T__4);
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

	public static class KeyValueContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public AnnValueContext annValue() {
			return getRuleContext(AnnValueContext.class,0);
		}
		public KeyValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_keyValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).enterKeyValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).exitKeyValue(this);
		}
	}

	public final KeyValueContext keyValue() throws RecognitionException {
		KeyValueContext _localctx = new KeyValueContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_keyValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(448);
			identifier();
			setState(449);
			match(T__2);
			setState(450);
			annValue();
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

	public static class SelectRuleContext extends ParserRuleContext {
		public Token isUnion;
		public IdentifierContext dependsOnTable;
		public IdentifierContext dependingTableAlias;
		public Token isDistinct;
		public TerminalNode SELECT() { return getToken(CdsParser.SELECT, 0); }
		public TerminalNode FROM() { return getToken(CdsParser.FROM, 0); }
		public SelectedColumnsRuleContext selectedColumnsRule() {
			return getRuleContext(SelectedColumnsRuleContext.class,0);
		}
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public List<JoinRuleContext> joinRule() {
			return getRuleContexts(JoinRuleContext.class);
		}
		public JoinRuleContext joinRule(int i) {
			return getRuleContext(JoinRuleContext.class,i);
		}
		public List<TerminalNode> SEMICOLUMN() { return getTokens(CdsParser.SEMICOLUMN); }
		public TerminalNode SEMICOLUMN(int i) {
			return getToken(CdsParser.SEMICOLUMN, i);
		}
		public TerminalNode WHERE() { return getToken(CdsParser.WHERE, 0); }
		public WhereRuleContext whereRule() {
			return getRuleContext(WhereRuleContext.class,0);
		}
		public TerminalNode UNION() { return getToken(CdsParser.UNION, 0); }
		public TerminalNode DISTINCT() { return getToken(CdsParser.DISTINCT, 0); }
		public TerminalNode AS() { return getToken(CdsParser.AS, 0); }
		public SelectRuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selectRule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).enterSelectRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).exitSelectRule(this);
		}
	}

	public final SelectRuleContext selectRule() throws RecognitionException {
		SelectRuleContext _localctx = new SelectRuleContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_selectRule);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(453);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==UNION) {
				{
				setState(452);
				((SelectRuleContext)_localctx).isUnion = match(UNION);
				}
			}

			setState(455);
			match(SELECT);
			setState(456);
			match(FROM);
			setState(457);
			((SelectRuleContext)_localctx).dependsOnTable = identifier();
			setState(461);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,56,_ctx) ) {
			case 1:
				{
				{
				setState(458);
				match(AS);
				setState(459);
				((SelectRuleContext)_localctx).dependingTableAlias = identifier();
				}
				}
				break;
			case 2:
				{
				setState(460);
				((SelectRuleContext)_localctx).dependingTableAlias = identifier();
				}
				break;
			}
			setState(466);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==JOIN_TYPES) {
				{
				{
				setState(463);
				joinRule();
				}
				}
				setState(468);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(470);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DISTINCT) {
				{
				setState(469);
				((SelectRuleContext)_localctx).isDistinct = match(DISTINCT);
				}
			}

			setState(472);
			match(T__3);
			setState(473);
			selectedColumnsRule();
			setState(474);
			match(T__4);
			setState(476);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMICOLUMN) {
				{
				setState(475);
				match(SEMICOLUMN);
				}
			}

			setState(483);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(478);
				match(WHERE);
				setState(479);
				whereRule();
				setState(481);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SEMICOLUMN) {
					{
					setState(480);
					match(SEMICOLUMN);
					}
				}

				}
			}

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

	public static class JoinRuleContext extends ParserRuleContext {
		public Token joinType;
		public IdentifierContext joinArtifactName;
		public IdentifierContext joinTableAlias;
		public JoinFieldsContext joinFields() {
			return getRuleContext(JoinFieldsContext.class,0);
		}
		public TerminalNode JOIN_TYPES() { return getToken(CdsParser.JOIN_TYPES, 0); }
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public TerminalNode AS() { return getToken(CdsParser.AS, 0); }
		public JoinRuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_joinRule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).enterJoinRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).exitJoinRule(this);
		}
	}

	public final JoinRuleContext joinRule() throws RecognitionException {
		JoinRuleContext _localctx = new JoinRuleContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_joinRule);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(485);
			((JoinRuleContext)_localctx).joinType = match(JOIN_TYPES);
			setState(486);
			((JoinRuleContext)_localctx).joinArtifactName = identifier();
			setState(490);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,62,_ctx) ) {
			case 1:
				{
				{
				setState(487);
				match(AS);
				setState(488);
				((JoinRuleContext)_localctx).joinTableAlias = identifier();
				}
				}
				break;
			case 2:
				{
				setState(489);
				((JoinRuleContext)_localctx).joinTableAlias = identifier();
				}
				break;
			}
			setState(492);
			joinFields();
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

	public static class JoinFieldsContext extends ParserRuleContext {
		public JoinFieldsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_joinFields; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).enterJoinFields(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).exitJoinFields(this);
		}
	}

	public final JoinFieldsContext joinFields() throws RecognitionException {
		JoinFieldsContext _localctx = new JoinFieldsContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_joinFields);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(497);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,63,_ctx);
			while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(494);
					matchWildcard();
					}
					} 
				}
				setState(499);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,63,_ctx);
			}
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

	public static class SelectedColumnsRuleContext extends ParserRuleContext {
		public SelectedColumnsRuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selectedColumnsRule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).enterSelectedColumnsRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).exitSelectedColumnsRule(this);
		}
	}

	public final SelectedColumnsRuleContext selectedColumnsRule() throws RecognitionException {
		SelectedColumnsRuleContext _localctx = new SelectedColumnsRuleContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_selectedColumnsRule);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(503);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,64,_ctx);
			while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(500);
					matchWildcard();
					}
					} 
				}
				setState(505);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,64,_ctx);
			}
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

	public static class WhereRuleContext extends ParserRuleContext {
		public WhereRuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whereRule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).enterWhereRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).exitWhereRule(this);
		}
	}

	public final WhereRuleContext whereRule() throws RecognitionException {
		WhereRuleContext _localctx = new WhereRuleContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_whereRule);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(509);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,65,_ctx);
			while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(506);
					matchWildcard();
					}
					} 
				}
				setState(511);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,65,_ctx);
			}
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

	public static class IdentifierContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(CdsParser.ID, 0); }
		public TerminalNode NAMESPACE() { return getToken(CdsParser.NAMESPACE, 0); }
		public TerminalNode AS() { return getToken(CdsParser.AS, 0); }
		public TerminalNode ON() { return getToken(CdsParser.ON, 0); }
		public TerminalNode SELECT() { return getToken(CdsParser.SELECT, 0); }
		public TerminalNode FROM() { return getToken(CdsParser.FROM, 0); }
		public TerminalNode WHERE() { return getToken(CdsParser.WHERE, 0); }
		public TerminalNode DEFINE() { return getToken(CdsParser.DEFINE, 0); }
		public TerminalNode UNION() { return getToken(CdsParser.UNION, 0); }
		public TerminalNode DISTINCT() { return getToken(CdsParser.DISTINCT, 0); }
		public TerminalNode CONTEXT() { return getToken(CdsParser.CONTEXT, 0); }
		public TerminalNode ENTITY() { return getToken(CdsParser.ENTITY, 0); }
		public TerminalNode TYPE() { return getToken(CdsParser.TYPE, 0); }
		public TerminalNode VIEW() { return getToken(CdsParser.VIEW, 0); }
		public TerminalNode ASSOCIATION() { return getToken(CdsParser.ASSOCIATION, 0); }
		public TerminalNode TO() { return getToken(CdsParser.TO, 0); }
		public TerminalNode JOIN_TYPES() { return getToken(CdsParser.JOIN_TYPES, 0); }
		public TerminalNode USING() { return getToken(CdsParser.USING, 0); }
		public TerminalNode DEFAULT() { return getToken(CdsParser.DEFAULT, 0); }
		public IdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).enterIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).exitIdentifier(this);
		}
	}

	public final IdentifierContext identifier() throws RecognitionException {
		IdentifierContext _localctx = new IdentifierContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_identifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(512);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NAMESPACE) | (1L << AS) | (1L << ON) | (1L << SELECT) | (1L << FROM) | (1L << WHERE) | (1L << DEFINE) | (1L << UNION) | (1L << DISTINCT) | (1L << JOIN_TYPES) | (1L << USING) | (1L << DEFAULT) | (1L << CONTEXT) | (1L << ENTITY) | (1L << VIEW) | (1L << TYPE) | (1L << ASSOCIATION) | (1L << TO) | (1L << ID))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\\\u0205\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\3\2\3\2\7\2K\n\2\f\2\16\2N\13\2\3\2\3\2\3\3\3"+
		"\3\3\3\3\3\7\3V\n\3\f\3\16\3Y\13\3\3\3\3\3\3\4\3\4\3\4\3\4\7\4a\n\4\f"+
		"\4\16\4d\13\4\3\4\3\4\3\4\3\4\7\4j\n\4\f\4\16\4m\13\4\3\4\3\4\5\4q\n\4"+
		"\3\4\3\4\3\5\7\5v\n\5\f\5\16\5y\13\5\3\5\5\5|\n\5\3\5\5\5\177\n\5\3\5"+
		"\5\5\u0082\n\5\3\5\5\5\u0085\n\5\5\5\u0087\n\5\3\6\7\6\u008a\n\6\f\6\16"+
		"\6\u008d\13\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\7\7\u0096\n\7\f\7\16\7\u0099"+
		"\13\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\7\7\u00a3\n\7\f\7\16\7\u00a6\13"+
		"\7\3\7\3\7\5\7\u00aa\n\7\3\b\7\b\u00ad\n\b\f\b\16\b\u00b0\13\b\3\b\3\b"+
		"\3\b\3\b\7\b\u00b6\n\b\f\b\16\b\u00b9\13\b\3\b\3\b\5\b\u00bd\n\b\3\t\7"+
		"\t\u00c0\n\t\f\t\16\t\u00c3\13\t\3\t\3\t\3\t\3\t\3\t\7\t\u00ca\n\t\f\t"+
		"\16\t\u00cd\13\t\3\t\3\t\5\t\u00d1\n\t\3\n\7\n\u00d4\n\n\f\n\16\n\u00d7"+
		"\13\n\3\n\5\n\u00da\n\n\3\n\3\n\3\n\3\n\7\n\u00e0\n\n\f\n\16\n\u00e3\13"+
		"\n\3\13\3\13\3\13\3\13\3\13\5\13\u00ea\n\13\3\13\3\13\3\13\3\13\3\f\3"+
		"\f\3\f\3\f\3\f\7\f\u00f5\n\f\f\f\16\f\u00f8\13\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\7\f\u0102\n\f\f\f\16\f\u0105\13\f\3\f\3\f\5\f\u0109\n\f\3\f"+
		"\3\f\3\f\7\f\u010e\n\f\f\f\16\f\u0111\13\f\5\f\u0113\n\f\3\r\7\r\u0116"+
		"\n\r\f\r\16\r\u0119\13\r\3\r\5\r\u011c\n\r\3\r\3\r\3\r\3\r\3\r\5\r\u0123"+
		"\n\r\3\r\3\r\3\r\7\r\u0128\n\r\f\r\16\r\u012b\13\r\3\r\3\r\3\16\3\16\5"+
		"\16\u0131\n\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22\5\22\u013a\n\22\3\22"+
		"\3\22\3\22\3\22\5\22\u0140\n\22\3\22\3\22\3\22\3\22\7\22\u0146\n\22\f"+
		"\22\16\22\u0149\13\22\3\22\5\22\u014c\n\22\3\22\3\22\3\23\3\23\3\23\7"+
		"\23\u0153\n\23\f\23\16\23\u0156\13\23\3\24\3\24\3\24\3\24\7\24\u015c\n"+
		"\24\f\24\16\24\u015f\13\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\7\25\u0168"+
		"\n\25\f\25\16\25\u016b\13\25\3\25\3\25\3\26\3\26\3\26\7\26\u0172\n\26"+
		"\f\26\16\26\u0175\13\26\3\26\3\26\5\26\u0179\n\26\3\27\3\27\3\27\3\27"+
		"\5\27\u017f\n\27\3\27\3\27\3\27\3\27\5\27\u0185\n\27\3\27\3\27\3\27\5"+
		"\27\u018a\n\27\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31"+
		"\3\31\7\31\u0198\n\31\f\31\16\31\u019b\13\31\3\31\3\31\3\31\3\31\3\31"+
		"\5\31\u01a2\n\31\3\32\3\32\3\32\3\32\5\32\u01a8\n\32\3\33\3\33\3\33\3"+
		"\34\3\34\3\34\3\34\7\34\u01b1\n\34\f\34\16\34\u01b4\13\34\3\34\3\34\3"+
		"\35\3\35\3\35\3\35\7\35\u01bc\n\35\f\35\16\35\u01bf\13\35\3\35\3\35\3"+
		"\36\3\36\3\36\3\36\3\37\5\37\u01c8\n\37\3\37\3\37\3\37\3\37\3\37\3\37"+
		"\5\37\u01d0\n\37\3\37\7\37\u01d3\n\37\f\37\16\37\u01d6\13\37\3\37\5\37"+
		"\u01d9\n\37\3\37\3\37\3\37\3\37\5\37\u01df\n\37\3\37\3\37\3\37\5\37\u01e4"+
		"\n\37\5\37\u01e6\n\37\3 \3 \3 \3 \3 \5 \u01ed\n \3 \3 \3!\7!\u01f2\n!"+
		"\f!\16!\u01f5\13!\3\"\7\"\u01f8\n\"\f\"\16\"\u01fb\13\"\3#\7#\u01fe\n"+
		"#\f#\16#\u0201\13#\3$\3$\3$\5\u01f3\u01f9\u01ff\2%\2\4\6\b\n\f\16\20\22"+
		"\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BDF\2\6\4\2\f\16))\6\2\'\'"+
		"))..\67>\4\2..==\6\2\25\36((,,/\65\2\u0231\2H\3\2\2\2\4Q\3\2\2\2\6\\\3"+
		"\2\2\2\b\u0086\3\2\2\2\n\u008b\3\2\2\2\f\u0097\3\2\2\2\16\u00ae\3\2\2"+
		"\2\20\u00c1\3\2\2\2\22\u00d5\3\2\2\2\24\u00e9\3\2\2\2\26\u0112\3\2\2\2"+
		"\30\u0117\3\2\2\2\32\u0130\3\2\2\2\34\u0132\3\2\2\2\36\u0134\3\2\2\2 "+
		"\u0136\3\2\2\2\"\u0139\3\2\2\2$\u014f\3\2\2\2&\u0157\3\2\2\2(\u0163\3"+
		"\2\2\2*\u016e\3\2\2\2,\u0189\3\2\2\2.\u018b\3\2\2\2\60\u01a1\3\2\2\2\62"+
		"\u01a7\3\2\2\2\64\u01a9\3\2\2\2\66\u01ac\3\2\2\28\u01b7\3\2\2\2:\u01c2"+
		"\3\2\2\2<\u01c7\3\2\2\2>\u01e7\3\2\2\2@\u01f3\3\2\2\2B\u01f9\3\2\2\2D"+
		"\u01ff\3\2\2\2F\u0202\3\2\2\2HL\5\4\3\2IK\5\6\4\2JI\3\2\2\2KN\3\2\2\2"+
		"LJ\3\2\2\2LM\3\2\2\2MO\3\2\2\2NL\3\2\2\2OP\5\b\5\2P\3\3\2\2\2QR\7\25\2"+
		"\2RW\5F$\2ST\7\3\2\2TV\5F$\2US\3\2\2\2VY\3\2\2\2WU\3\2\2\2WX\3\2\2\2X"+
		"Z\3\2\2\2YW\3\2\2\2Z[\7\66\2\2[\5\3\2\2\2\\]\7(\2\2]b\5F$\2^_\7\3\2\2"+
		"_a\5F$\2`^\3\2\2\2ad\3\2\2\2b`\3\2\2\2bc\3\2\2\2ce\3\2\2\2db\3\2\2\2e"+
		"f\7\4\2\2fk\5F$\2gh\7\3\2\2hj\5F$\2ig\3\2\2\2jm\3\2\2\2ki\3\2\2\2kl\3"+
		"\2\2\2lp\3\2\2\2mk\3\2\2\2no\7\26\2\2oq\5F$\2pn\3\2\2\2pq\3\2\2\2qr\3"+
		"\2\2\2rs\7\66\2\2s\7\3\2\2\2tv\5\n\6\2ut\3\2\2\2vy\3\2\2\2wu\3\2\2\2w"+
		"x\3\2\2\2x\u0087\3\2\2\2yw\3\2\2\2z|\5\f\7\2{z\3\2\2\2{|\3\2\2\2|\u0087"+
		"\3\2\2\2}\177\5\16\b\2~}\3\2\2\2~\177\3\2\2\2\177\u0087\3\2\2\2\u0080"+
		"\u0082\5\20\t\2\u0081\u0080\3\2\2\2\u0081\u0082\3\2\2\2\u0082\u0087\3"+
		"\2\2\2\u0083\u0085\5\22\n\2\u0084\u0083\3\2\2\2\u0084\u0085\3\2\2\2\u0085"+
		"\u0087\3\2\2\2\u0086w\3\2\2\2\u0086{\3\2\2\2\u0086~\3\2\2\2\u0086\u0081"+
		"\3\2\2\2\u0086\u0084\3\2\2\2\u0087\t\3\2\2\2\u0088\u008a\5\60\31\2\u0089"+
		"\u0088\3\2\2\2\u008a\u008d\3\2\2\2\u008b\u0089\3\2\2\2\u008b\u008c\3\2"+
		"\2\2\u008c\u008e\3\2\2\2\u008d\u008b\3\2\2\2\u008e\u008f\7\62\2\2\u008f"+
		"\u0090\5F$\2\u0090\u0091\7\5\2\2\u0091\u0092\5\26\f\2\u0092\u0093\7\66"+
		"\2\2\u0093\13\3\2\2\2\u0094\u0096\5\60\31\2\u0095\u0094\3\2\2\2\u0096"+
		"\u0099\3\2\2\2\u0097\u0095\3\2\2\2\u0097\u0098\3\2\2\2\u0098\u009a\3\2"+
		"\2\2\u0099\u0097\3\2\2\2\u009a\u009b\7/\2\2\u009b\u009c\5F$\2\u009c\u00a4"+
		"\7\6\2\2\u009d\u00a3\5\f\7\2\u009e\u00a3\5\n\6\2\u009f\u00a3\5\16\b\2"+
		"\u00a0\u00a3\5\20\t\2\u00a1\u00a3\5\22\n\2\u00a2\u009d\3\2\2\2\u00a2\u009e"+
		"\3\2\2\2\u00a2\u009f\3\2\2\2\u00a2\u00a0\3\2\2\2\u00a2\u00a1\3\2\2\2\u00a3"+
		"\u00a6\3\2\2\2\u00a4\u00a2\3\2\2\2\u00a4\u00a5\3\2\2\2\u00a5\u00a7\3\2"+
		"\2\2\u00a6\u00a4\3\2\2\2\u00a7\u00a9\7\7\2\2\u00a8\u00aa\7\66\2\2\u00a9"+
		"\u00a8\3\2\2\2\u00a9\u00aa\3\2\2\2\u00aa\r\3\2\2\2\u00ab\u00ad\5\60\31"+
		"\2\u00ac\u00ab\3\2\2\2\u00ad\u00b0\3\2\2\2\u00ae\u00ac\3\2\2\2\u00ae\u00af"+
		"\3\2\2\2\u00af\u00b1\3\2\2\2\u00b0\u00ae\3\2\2\2\u00b1\u00b2\7\62\2\2"+
		"\u00b2\u00b3\5F$\2\u00b3\u00b7\7\6\2\2\u00b4\u00b6\5\24\13\2\u00b5\u00b4"+
		"\3\2\2\2\u00b6\u00b9\3\2\2\2\u00b7\u00b5\3\2\2\2\u00b7\u00b8\3\2\2\2\u00b8"+
		"\u00ba\3\2\2\2\u00b9\u00b7\3\2\2\2\u00ba\u00bc\7\7\2\2\u00bb\u00bd\7\66"+
		"\2\2\u00bc\u00bb\3\2\2\2\u00bc\u00bd\3\2\2\2\u00bd\17\3\2\2\2\u00be\u00c0"+
		"\5\60\31\2\u00bf\u00be\3\2\2\2\u00c0\u00c3\3\2\2\2\u00c1\u00bf\3\2\2\2"+
		"\u00c1\u00c2\3\2\2\2\u00c2\u00c4\3\2\2\2\u00c3\u00c1\3\2\2\2\u00c4\u00c5"+
		"\7\60\2\2\u00c5\u00c6\5F$\2\u00c6\u00cb\7\6\2\2\u00c7\u00ca\5\30\r\2\u00c8"+
		"\u00ca\5\"\22\2\u00c9\u00c7\3\2\2\2\u00c9\u00c8\3\2\2\2\u00ca\u00cd\3"+
		"\2\2\2\u00cb\u00c9\3\2\2\2\u00cb\u00cc\3\2\2\2\u00cc\u00ce\3\2\2\2\u00cd"+
		"\u00cb\3\2\2\2\u00ce\u00d0\7\7\2\2\u00cf\u00d1\7\66\2\2\u00d0\u00cf\3"+
		"\2\2\2\u00d0\u00d1\3\2\2\2\u00d1\21\3\2\2\2\u00d2\u00d4\5\60\31\2\u00d3"+
		"\u00d2\3\2\2\2\u00d4\u00d7\3\2\2\2\u00d5\u00d3\3\2\2\2\u00d5\u00d6\3\2"+
		"\2\2\u00d6\u00d9\3\2\2\2\u00d7\u00d5\3\2\2\2\u00d8\u00da\7\33\2\2\u00d9"+
		"\u00d8\3\2\2\2\u00d9\u00da\3\2\2\2\u00da\u00db\3\2\2\2\u00db\u00dc\7\61"+
		"\2\2\u00dc\u00dd\5F$\2\u00dd\u00e1\7\26\2\2\u00de\u00e0\5<\37\2\u00df"+
		"\u00de\3\2\2\2\u00e0\u00e3\3\2\2\2\u00e1\u00df\3\2\2\2\u00e1\u00e2\3\2"+
		"\2\2\u00e2\23\3\2\2\2\u00e3\u00e1\3\2\2\2\u00e4\u00ea\5F$\2\u00e5\u00e6"+
		"\7\b\2\2\u00e6\u00e7\5F$\2\u00e7\u00e8\7\b\2\2\u00e8\u00ea\3\2\2\2\u00e9"+
		"\u00e4\3\2\2\2\u00e9\u00e5\3\2\2\2\u00ea\u00eb\3\2\2\2\u00eb\u00ec\7\5"+
		"\2\2\u00ec\u00ed\5\26\f\2\u00ed\u00ee\7\66\2\2\u00ee\25\3\2\2\2\u00ef"+
		"\u00f0\5F$\2\u00f0\u00f1\7\t\2\2\u00f1\u00f6\7\67\2\2\u00f2\u00f3\7\n"+
		"\2\2\u00f3\u00f5\7\67\2\2\u00f4\u00f2\3\2\2\2\u00f5\u00f8\3\2\2\2\u00f6"+
		"\u00f4\3\2\2\2\u00f6\u00f7\3\2\2\2\u00f7\u00f9\3\2\2\2\u00f8\u00f6\3\2"+
		"\2\2\u00f9\u00fa\7\13\2\2\u00fa\u0113\3\2\2\2\u00fb\u0113\7&\2\2\u00fc"+
		"\u00fd\7&\2\2\u00fd\u00fe\7\t\2\2\u00fe\u0103\7\67\2\2\u00ff\u0100\7\n"+
		"\2\2\u0100\u0102\7\67\2\2\u0101\u00ff\3\2\2\2\u0102\u0105\3\2\2\2\u0103"+
		"\u0101\3\2\2\2\u0103\u0104\3\2\2\2\u0104\u0106\3\2\2\2\u0105\u0103\3\2"+
		"\2\2\u0106\u0113\7\13\2\2\u0107\u0109\7?\2\2\u0108\u0107\3\2\2\2\u0108"+
		"\u0109\3\2\2\2\u0109\u010a\3\2\2\2\u010a\u010f\5F$\2\u010b\u010c\7\3\2"+
		"\2\u010c\u010e\5F$\2\u010d\u010b\3\2\2\2\u010e\u0111\3\2\2\2\u010f\u010d"+
		"\3\2\2\2\u010f\u0110\3\2\2\2\u0110\u0113\3\2\2\2\u0111\u010f\3\2\2\2\u0112"+
		"\u00ef\3\2\2\2\u0112\u00fb\3\2\2\2\u0112\u00fc\3\2\2\2\u0112\u0108\3\2"+
		"\2\2\u0113\27\3\2\2\2\u0114\u0116\5\60\31\2\u0115\u0114\3\2\2\2\u0116"+
		"\u0119\3\2\2\2\u0117\u0115\3\2\2\2\u0117\u0118\3\2\2\2\u0118\u011b\3\2"+
		"\2\2\u0119\u0117\3\2\2\2\u011a\u011c\5F$\2\u011b\u011a\3\2\2\2\u011b\u011c"+
		"\3\2\2\2\u011c\u0122\3\2\2\2\u011d\u0123\5F$\2\u011e\u011f\7\b\2\2\u011f"+
		"\u0120\5F$\2\u0120\u0121\7\b\2\2\u0121\u0123\3\2\2\2\u0122\u011d\3\2\2"+
		"\2\u0122\u011e\3\2\2\2\u0123\u0124\3\2\2\2\u0124\u0125\7\5\2\2\u0125\u0129"+
		"\5\26\f\2\u0126\u0128\5\32\16\2\u0127\u0126\3\2\2\2\u0128\u012b\3\2\2"+
		"\2\u0129\u0127\3\2\2\2\u0129\u012a\3\2\2\2\u012a\u012c\3\2\2\2\u012b\u0129"+
		"\3\2\2\2\u012c\u012d\7\66\2\2\u012d\31\3\2\2\2\u012e\u0131\5.\30\2\u012f"+
		"\u0131\5\34\17\2\u0130\u012e\3\2\2\2\u0130\u012f\3\2\2\2\u0131\33\3\2"+
		"\2\2\u0132\u0133\5 \21\2\u0133\35\3\2\2\2\u0134\u0135\5 \21\2\u0135\37"+
		"\3\2\2\2\u0136\u0137\t\2\2\2\u0137!\3\2\2\2\u0138\u013a\5F$\2\u0139\u0138"+
		"\3\2\2\2\u0139\u013a\3\2\2\2\u013a\u013b\3\2\2\2\u013b\u013c\5F$\2\u013c"+
		"\u013d\7\5\2\2\u013d\u013f\7\63\2\2\u013e\u0140\5,\27\2\u013f\u013e\3"+
		"\2\2\2\u013f\u0140\3\2\2\2\u0140\u0141\3\2\2\2\u0141\u0142\7\64\2\2\u0142"+
		"\u0147\5$\23\2\u0143\u0146\5(\25\2\u0144\u0146\5&\24\2\u0145\u0143\3\2"+
		"\2\2\u0145\u0144\3\2\2\2\u0146\u0149\3\2\2\2\u0147\u0145\3\2\2\2\u0147"+
		"\u0148\3\2\2\2\u0148\u014b\3\2\2\2\u0149\u0147\3\2\2\2\u014a\u014c\5\36"+
		"\20\2\u014b\u014a\3\2\2\2\u014b\u014c\3\2\2\2\u014c\u014d\3\2\2\2\u014d"+
		"\u014e\7\66\2\2\u014e#\3\2\2\2\u014f\u0154\5F$\2\u0150\u0151\7\3\2\2\u0151"+
		"\u0153\5F$\2\u0152\u0150\3\2\2\2\u0153\u0156\3\2\2\2\u0154\u0152\3\2\2"+
		"\2\u0154\u0155\3\2\2\2\u0155%\3\2\2\2\u0156\u0154\3\2\2\2\u0157\u0158"+
		"\7\27\2\2\u0158\u015d\5F$\2\u0159\u015a\7\3\2\2\u015a\u015c\5F$\2\u015b"+
		"\u0159\3\2\2\2\u015c\u015f\3\2\2\2\u015d\u015b\3\2\2\2\u015d\u015e\3\2"+
		"\2\2\u015e\u0160\3\2\2\2\u015f\u015d\3\2\2\2\u0160\u0161\7\17\2\2\u0161"+
		"\u0162\5F$\2\u0162\'\3\2\2\2\u0163\u0164\7\6\2\2\u0164\u0169\5*\26\2\u0165"+
		"\u0166\7\n\2\2\u0166\u0168\5*\26\2\u0167\u0165\3\2\2\2\u0168\u016b\3\2"+
		"\2\2\u0169\u0167\3\2\2\2\u0169\u016a\3\2\2\2\u016a\u016c\3\2\2\2\u016b"+
		"\u0169\3\2\2\2\u016c\u016d\7\7\2\2\u016d)\3\2\2\2\u016e\u0173\5F$\2\u016f"+
		"\u0170\7\3\2\2\u0170\u0172\5F$\2\u0171\u016f\3\2\2\2\u0172\u0175\3\2\2"+
		"\2\u0173\u0171\3\2\2\2\u0173\u0174\3\2\2\2\u0174\u0178\3\2\2\2\u0175\u0173"+
		"\3\2\2\2\u0176\u0177\7\26\2\2\u0177\u0179\5F$\2\u0178\u0176\3\2\2\2\u0178"+
		"\u0179\3\2\2\2\u0179+\3\2\2\2\u017a\u017b\7\20\2\2\u017b\u017e\7-\2\2"+
		"\u017c\u017f\7\67\2\2\u017d\u017f\7\21\2\2\u017e\u017c\3\2\2\2\u017e\u017d"+
		"\3\2\2\2\u017f\u0180\3\2\2\2\u0180\u018a\7\22\2\2\u0181\u0184\7\20\2\2"+
		"\u0182\u0185\7\67\2\2\u0183\u0185\7\21\2\2\u0184\u0182\3\2\2\2\u0184\u0183"+
		"\3\2\2\2\u0185\u0186\3\2\2\2\u0186\u018a\7\22\2\2\u0187\u0188\7\20\2\2"+
		"\u0188\u018a\7\22\2\2\u0189\u017a\3\2\2\2\u0189\u0181\3\2\2\2\u0189\u0187"+
		"\3\2\2\2\u018a-\3\2\2\2\u018b\u018c\7,\2\2\u018c\u018d\t\3\2\2\u018d/"+
		"\3\2\2\2\u018e\u018f\7\23\2\2\u018f\u0190\5F$\2\u0190\u0191\7\5\2\2\u0191"+
		"\u0192\5\62\32\2\u0192\u01a2\3\2\2\2\u0193\u0194\7\23\2\2\u0194\u0199"+
		"\5F$\2\u0195\u0196\7\3\2\2\u0196\u0198\5F$\2\u0197\u0195\3\2\2\2\u0198"+
		"\u019b\3\2\2\2\u0199\u0197\3\2\2\2\u0199\u019a\3\2\2\2\u019a\u019c\3\2"+
		"\2\2\u019b\u0199\3\2\2\2\u019c\u019d\7\5\2\2\u019d\u019e\5\62\32\2\u019e"+
		"\u01a2\3\2\2\2\u019f\u01a0\7\23\2\2\u01a0\u01a2\5F$\2\u01a1\u018e\3\2"+
		"\2\2\u01a1\u0193\3\2\2\2\u01a1\u019f\3\2\2\2\u01a2\61\3\2\2\2\u01a3\u01a8"+
		"\5\66\34\2\u01a4\u01a8\5\64\33\2\u01a5\u01a8\58\35\2\u01a6\u01a8\t\4\2"+
		"\2\u01a7\u01a3\3\2\2\2\u01a7\u01a4\3\2\2\2\u01a7\u01a5\3\2\2\2\u01a7\u01a6"+
		"\3\2\2\2\u01a8\63\3\2\2\2\u01a9\u01aa\7\24\2\2\u01aa\u01ab\5F$\2\u01ab"+
		"\65\3\2\2\2\u01ac\u01ad\7\20\2\2\u01ad\u01b2\5\62\32\2\u01ae\u01af\7\n"+
		"\2\2\u01af\u01b1\5\62\32\2\u01b0\u01ae\3\2\2\2\u01b1\u01b4\3\2\2\2\u01b2"+
		"\u01b0\3\2\2\2\u01b2\u01b3\3\2\2\2\u01b3\u01b5\3\2\2\2\u01b4\u01b2\3\2"+
		"\2\2\u01b5\u01b6\7\22\2\2\u01b6\67\3\2\2\2\u01b7\u01b8\7\6\2\2\u01b8\u01bd"+
		"\5:\36\2\u01b9\u01ba\7\n\2\2\u01ba\u01bc\5:\36\2\u01bb\u01b9\3\2\2\2\u01bc"+
		"\u01bf\3\2\2\2\u01bd\u01bb\3\2\2\2\u01bd\u01be\3\2\2\2\u01be\u01c0\3\2"+
		"\2\2\u01bf\u01bd\3\2\2\2\u01c0\u01c1\7\7\2\2\u01c19\3\2\2\2\u01c2\u01c3"+
		"\5F$\2\u01c3\u01c4\7\5\2\2\u01c4\u01c5\5\62\32\2\u01c5;\3\2\2\2\u01c6"+
		"\u01c8\7\34\2\2\u01c7\u01c6\3\2\2\2\u01c7\u01c8\3\2\2\2\u01c8\u01c9\3"+
		"\2\2\2\u01c9\u01ca\7\30\2\2\u01ca\u01cb\7\31\2\2\u01cb\u01cf\5F$\2\u01cc"+
		"\u01cd\7\26\2\2\u01cd\u01d0\5F$\2\u01ce\u01d0\5F$\2\u01cf\u01cc\3\2\2"+
		"\2\u01cf\u01ce\3\2\2\2\u01cf\u01d0\3\2\2\2\u01d0\u01d4\3\2\2\2\u01d1\u01d3"+
		"\5> \2\u01d2\u01d1\3\2\2\2\u01d3\u01d6\3\2\2\2\u01d4\u01d2\3\2\2\2\u01d4"+
		"\u01d5\3\2\2\2\u01d5\u01d8\3\2\2\2\u01d6\u01d4\3\2\2\2\u01d7\u01d9\7\35"+
		"\2\2\u01d8\u01d7\3\2\2\2\u01d8\u01d9\3\2\2\2\u01d9\u01da\3\2\2\2\u01da"+
		"\u01db\7\6\2\2\u01db\u01dc\5B\"\2\u01dc\u01de\7\7\2\2\u01dd\u01df\7\66"+
		"\2\2\u01de\u01dd\3\2\2\2\u01de\u01df\3\2\2\2\u01df\u01e5\3\2\2\2\u01e0"+
		"\u01e1\7\32\2\2\u01e1\u01e3\5D#\2\u01e2\u01e4\7\66\2\2\u01e3\u01e2\3\2"+
		"\2\2\u01e3\u01e4\3\2\2\2\u01e4\u01e6\3\2\2\2\u01e5\u01e0\3\2\2\2\u01e5"+
		"\u01e6\3\2\2\2\u01e6=\3\2\2\2\u01e7\u01e8\7\36\2\2\u01e8\u01ec\5F$\2\u01e9"+
		"\u01ea\7\26\2\2\u01ea\u01ed\5F$\2\u01eb\u01ed\5F$\2\u01ec\u01e9\3\2\2"+
		"\2\u01ec\u01eb\3\2\2\2\u01ec\u01ed\3\2\2\2\u01ed\u01ee\3\2\2\2\u01ee\u01ef"+
		"\5@!\2\u01ef?\3\2\2\2\u01f0\u01f2\13\2\2\2\u01f1\u01f0\3\2\2\2\u01f2\u01f5"+
		"\3\2\2\2\u01f3\u01f4\3\2\2\2\u01f3\u01f1\3\2\2\2\u01f4A\3\2\2\2\u01f5"+
		"\u01f3\3\2\2\2\u01f6\u01f8\13\2\2\2\u01f7\u01f6\3\2\2\2\u01f8\u01fb\3"+
		"\2\2\2\u01f9\u01fa\3\2\2\2\u01f9\u01f7\3\2\2\2\u01faC\3\2\2\2\u01fb\u01f9"+
		"\3\2\2\2\u01fc\u01fe\13\2\2\2\u01fd\u01fc\3\2\2\2\u01fe\u0201\3\2\2\2"+
		"\u01ff\u0200\3\2\2\2\u01ff\u01fd\3\2\2\2\u0200E\3\2\2\2\u0201\u01ff\3"+
		"\2\2\2\u0202\u0203\t\5\2\2\u0203G\3\2\2\2DLWbkpw{~\u0081\u0084\u0086\u008b"+
		"\u0097\u00a2\u00a4\u00a9\u00ae\u00b7\u00bc\u00c1\u00c9\u00cb\u00d0\u00d5"+
		"\u00d9\u00e1\u00e9\u00f6\u0103\u0108\u010f\u0112\u0117\u011b\u0122\u0129"+
		"\u0130\u0139\u013f\u0145\u0147\u014b\u0154\u015d\u0169\u0173\u0178\u017e"+
		"\u0184\u0189\u0199\u01a1\u01a7\u01b2\u01bd\u01c7\u01cf\u01d4\u01d8\u01de"+
		"\u01e3\u01e5\u01ec\u01f3\u01f9\u01ff";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}