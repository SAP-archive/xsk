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
// Generated from com/sap/xsk/parser/hdbview/core/Hdbview.g4 by ANTLR 4.10.1
package com.sap.xsk.parser.hdbview.core;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class HdbviewParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.10.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		BOOLEAN=10, STRING=11, EQ=12, SEMICOLON=13, WS=14, ESC=15, LINE_COMMENT=16, 
		COMMENT=17;
	public static final int
		RULE_hdbviewDefinition = 0, RULE_property = 1, RULE_schemaProp = 2, RULE_publicProp = 3, 
		RULE_queryProp = 4, RULE_dependsOnProp = 5, RULE_dependsOnTable = 6, RULE_dependsOnView = 7;
	private static String[] makeRuleNames() {
		return new String[] {
			"hdbviewDefinition", "property", "schemaProp", "publicProp", "queryProp", 
			"dependsOnProp", "dependsOnTable", "dependsOnView"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'schema'", "'public'", "'query'", "'depends_on'", "'['", "','", 
			"']'", "'depends_on_table'", "'depends_on_view'", null, null, "'='", 
			"';'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, "BOOLEAN", 
			"STRING", "EQ", "SEMICOLON", "WS", "ESC", "LINE_COMMENT", "COMMENT"
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
	public String getGrammarFileName() { return "Hdbview.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public HdbviewParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class HdbviewDefinitionContext extends ParserRuleContext {
		public List<PropertyContext> property() {
			return getRuleContexts(PropertyContext.class);
		}
		public PropertyContext property(int i) {
			return getRuleContext(PropertyContext.class,i);
		}
		public HdbviewDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_hdbviewDefinition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbviewListener ) ((HdbviewListener)listener).enterHdbviewDefinition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbviewListener ) ((HdbviewListener)listener).exitHdbviewDefinition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbviewVisitor ) return ((HdbviewVisitor<? extends T>)visitor).visitHdbviewDefinition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HdbviewDefinitionContext hdbviewDefinition() throws RecognitionException {
		HdbviewDefinitionContext _localctx = new HdbviewDefinitionContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_hdbviewDefinition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(17); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(16);
				property();
				}
				}
				setState(19); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__2) | (1L << T__3) | (1L << T__7) | (1L << T__8))) != 0) );
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

	public static class PropertyContext extends ParserRuleContext {
		public SchemaPropContext schemaProp() {
			return getRuleContext(SchemaPropContext.class,0);
		}
		public PublicPropContext publicProp() {
			return getRuleContext(PublicPropContext.class,0);
		}
		public QueryPropContext queryProp() {
			return getRuleContext(QueryPropContext.class,0);
		}
		public DependsOnPropContext dependsOnProp() {
			return getRuleContext(DependsOnPropContext.class,0);
		}
		public DependsOnTableContext dependsOnTable() {
			return getRuleContext(DependsOnTableContext.class,0);
		}
		public DependsOnViewContext dependsOnView() {
			return getRuleContext(DependsOnViewContext.class,0);
		}
		public PropertyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_property; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbviewListener ) ((HdbviewListener)listener).enterProperty(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbviewListener ) ((HdbviewListener)listener).exitProperty(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbviewVisitor ) return ((HdbviewVisitor<? extends T>)visitor).visitProperty(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PropertyContext property() throws RecognitionException {
		PropertyContext _localctx = new PropertyContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_property);
		try {
			setState(27);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
				enterOuterAlt(_localctx, 1);
				{
				setState(21);
				schemaProp();
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 2);
				{
				setState(22);
				publicProp();
				}
				break;
			case T__2:
				enterOuterAlt(_localctx, 3);
				{
				setState(23);
				queryProp();
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 4);
				{
				setState(24);
				dependsOnProp();
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 5);
				{
				setState(25);
				dependsOnTable();
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 6);
				{
				setState(26);
				dependsOnView();
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

	public static class SchemaPropContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(HdbviewParser.EQ, 0); }
		public TerminalNode STRING() { return getToken(HdbviewParser.STRING, 0); }
		public TerminalNode SEMICOLON() { return getToken(HdbviewParser.SEMICOLON, 0); }
		public SchemaPropContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_schemaProp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbviewListener ) ((HdbviewListener)listener).enterSchemaProp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbviewListener ) ((HdbviewListener)listener).exitSchemaProp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbviewVisitor ) return ((HdbviewVisitor<? extends T>)visitor).visitSchemaProp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SchemaPropContext schemaProp() throws RecognitionException {
		SchemaPropContext _localctx = new SchemaPropContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_schemaProp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(29);
			match(T__0);
			setState(30);
			match(EQ);
			setState(31);
			match(STRING);
			setState(32);
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
		public TerminalNode EQ() { return getToken(HdbviewParser.EQ, 0); }
		public TerminalNode BOOLEAN() { return getToken(HdbviewParser.BOOLEAN, 0); }
		public TerminalNode SEMICOLON() { return getToken(HdbviewParser.SEMICOLON, 0); }
		public PublicPropContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_publicProp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbviewListener ) ((HdbviewListener)listener).enterPublicProp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbviewListener ) ((HdbviewListener)listener).exitPublicProp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbviewVisitor ) return ((HdbviewVisitor<? extends T>)visitor).visitPublicProp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PublicPropContext publicProp() throws RecognitionException {
		PublicPropContext _localctx = new PublicPropContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_publicProp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(34);
			match(T__1);
			setState(35);
			match(EQ);
			setState(36);
			match(BOOLEAN);
			setState(37);
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

	public static class QueryPropContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(HdbviewParser.EQ, 0); }
		public TerminalNode STRING() { return getToken(HdbviewParser.STRING, 0); }
		public TerminalNode SEMICOLON() { return getToken(HdbviewParser.SEMICOLON, 0); }
		public QueryPropContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_queryProp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbviewListener ) ((HdbviewListener)listener).enterQueryProp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbviewListener ) ((HdbviewListener)listener).exitQueryProp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbviewVisitor ) return ((HdbviewVisitor<? extends T>)visitor).visitQueryProp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QueryPropContext queryProp() throws RecognitionException {
		QueryPropContext _localctx = new QueryPropContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_queryProp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(39);
			match(T__2);
			setState(40);
			match(EQ);
			setState(41);
			match(STRING);
			setState(42);
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

	public static class DependsOnPropContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(HdbviewParser.EQ, 0); }
		public TerminalNode SEMICOLON() { return getToken(HdbviewParser.SEMICOLON, 0); }
		public List<TerminalNode> STRING() { return getTokens(HdbviewParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(HdbviewParser.STRING, i);
		}
		public DependsOnPropContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dependsOnProp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbviewListener ) ((HdbviewListener)listener).enterDependsOnProp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbviewListener ) ((HdbviewListener)listener).exitDependsOnProp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbviewVisitor ) return ((HdbviewVisitor<? extends T>)visitor).visitDependsOnProp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DependsOnPropContext dependsOnProp() throws RecognitionException {
		DependsOnPropContext _localctx = new DependsOnPropContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_dependsOnProp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(44);
			match(T__3);
			setState(45);
			match(EQ);
			setState(46);
			match(T__4);
			setState(55);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==STRING) {
				{
				setState(47);
				match(STRING);
				setState(52);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__5) {
					{
					{
					setState(48);
					match(T__5);
					setState(49);
					match(STRING);
					}
					}
					setState(54);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(57);
			match(T__6);
			setState(58);
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

	public static class DependsOnTableContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(HdbviewParser.EQ, 0); }
		public TerminalNode SEMICOLON() { return getToken(HdbviewParser.SEMICOLON, 0); }
		public List<TerminalNode> STRING() { return getTokens(HdbviewParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(HdbviewParser.STRING, i);
		}
		public DependsOnTableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dependsOnTable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbviewListener ) ((HdbviewListener)listener).enterDependsOnTable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbviewListener ) ((HdbviewListener)listener).exitDependsOnTable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbviewVisitor ) return ((HdbviewVisitor<? extends T>)visitor).visitDependsOnTable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DependsOnTableContext dependsOnTable() throws RecognitionException {
		DependsOnTableContext _localctx = new DependsOnTableContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_dependsOnTable);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(60);
			match(T__7);
			setState(61);
			match(EQ);
			setState(62);
			match(T__4);
			setState(71);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==STRING) {
				{
				setState(63);
				match(STRING);
				setState(68);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__5) {
					{
					{
					setState(64);
					match(T__5);
					setState(65);
					match(STRING);
					}
					}
					setState(70);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(73);
			match(T__6);
			setState(74);
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

	public static class DependsOnViewContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(HdbviewParser.EQ, 0); }
		public TerminalNode SEMICOLON() { return getToken(HdbviewParser.SEMICOLON, 0); }
		public List<TerminalNode> STRING() { return getTokens(HdbviewParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(HdbviewParser.STRING, i);
		}
		public DependsOnViewContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dependsOnView; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbviewListener ) ((HdbviewListener)listener).enterDependsOnView(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbviewListener ) ((HdbviewListener)listener).exitDependsOnView(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbviewVisitor ) return ((HdbviewVisitor<? extends T>)visitor).visitDependsOnView(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DependsOnViewContext dependsOnView() throws RecognitionException {
		DependsOnViewContext _localctx = new DependsOnViewContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_dependsOnView);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(76);
			match(T__8);
			setState(77);
			match(EQ);
			setState(78);
			match(T__4);
			setState(87);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==STRING) {
				{
				setState(79);
				match(STRING);
				setState(84);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__5) {
					{
					{
					setState(80);
					match(T__5);
					setState(81);
					match(STRING);
					}
					}
					setState(86);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(89);
			match(T__6);
			setState(90);
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
		"\u0004\u0001\u0011]\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0001"+
		"\u0000\u0004\u0000\u0012\b\u0000\u000b\u0000\f\u0000\u0013\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u0001"+
		"\u001c\b\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0005\u00053\b\u0005"+
		"\n\u0005\f\u00056\t\u0005\u0003\u00058\b\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0005\u0006C\b\u0006\n\u0006\f\u0006F\t\u0006\u0003\u0006"+
		"H\b\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0005\u0007S\b\u0007"+
		"\n\u0007\f\u0007V\t\u0007\u0003\u0007X\b\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0000\u0000\b\u0000\u0002\u0004\u0006\b\n\f\u000e"+
		"\u0000\u0000`\u0000\u0011\u0001\u0000\u0000\u0000\u0002\u001b\u0001\u0000"+
		"\u0000\u0000\u0004\u001d\u0001\u0000\u0000\u0000\u0006\"\u0001\u0000\u0000"+
		"\u0000\b\'\u0001\u0000\u0000\u0000\n,\u0001\u0000\u0000\u0000\f<\u0001"+
		"\u0000\u0000\u0000\u000eL\u0001\u0000\u0000\u0000\u0010\u0012\u0003\u0002"+
		"\u0001\u0000\u0011\u0010\u0001\u0000\u0000\u0000\u0012\u0013\u0001\u0000"+
		"\u0000\u0000\u0013\u0011\u0001\u0000\u0000\u0000\u0013\u0014\u0001\u0000"+
		"\u0000\u0000\u0014\u0001\u0001\u0000\u0000\u0000\u0015\u001c\u0003\u0004"+
		"\u0002\u0000\u0016\u001c\u0003\u0006\u0003\u0000\u0017\u001c\u0003\b\u0004"+
		"\u0000\u0018\u001c\u0003\n\u0005\u0000\u0019\u001c\u0003\f\u0006\u0000"+
		"\u001a\u001c\u0003\u000e\u0007\u0000\u001b\u0015\u0001\u0000\u0000\u0000"+
		"\u001b\u0016\u0001\u0000\u0000\u0000\u001b\u0017\u0001\u0000\u0000\u0000"+
		"\u001b\u0018\u0001\u0000\u0000\u0000\u001b\u0019\u0001\u0000\u0000\u0000"+
		"\u001b\u001a\u0001\u0000\u0000\u0000\u001c\u0003\u0001\u0000\u0000\u0000"+
		"\u001d\u001e\u0005\u0001\u0000\u0000\u001e\u001f\u0005\f\u0000\u0000\u001f"+
		" \u0005\u000b\u0000\u0000 !\u0005\r\u0000\u0000!\u0005\u0001\u0000\u0000"+
		"\u0000\"#\u0005\u0002\u0000\u0000#$\u0005\f\u0000\u0000$%\u0005\n\u0000"+
		"\u0000%&\u0005\r\u0000\u0000&\u0007\u0001\u0000\u0000\u0000\'(\u0005\u0003"+
		"\u0000\u0000()\u0005\f\u0000\u0000)*\u0005\u000b\u0000\u0000*+\u0005\r"+
		"\u0000\u0000+\t\u0001\u0000\u0000\u0000,-\u0005\u0004\u0000\u0000-.\u0005"+
		"\f\u0000\u0000.7\u0005\u0005\u0000\u0000/4\u0005\u000b\u0000\u000001\u0005"+
		"\u0006\u0000\u000013\u0005\u000b\u0000\u000020\u0001\u0000\u0000\u0000"+
		"36\u0001\u0000\u0000\u000042\u0001\u0000\u0000\u000045\u0001\u0000\u0000"+
		"\u000058\u0001\u0000\u0000\u000064\u0001\u0000\u0000\u00007/\u0001\u0000"+
		"\u0000\u000078\u0001\u0000\u0000\u000089\u0001\u0000\u0000\u00009:\u0005"+
		"\u0007\u0000\u0000:;\u0005\r\u0000\u0000;\u000b\u0001\u0000\u0000\u0000"+
		"<=\u0005\b\u0000\u0000=>\u0005\f\u0000\u0000>G\u0005\u0005\u0000\u0000"+
		"?D\u0005\u000b\u0000\u0000@A\u0005\u0006\u0000\u0000AC\u0005\u000b\u0000"+
		"\u0000B@\u0001\u0000\u0000\u0000CF\u0001\u0000\u0000\u0000DB\u0001\u0000"+
		"\u0000\u0000DE\u0001\u0000\u0000\u0000EH\u0001\u0000\u0000\u0000FD\u0001"+
		"\u0000\u0000\u0000G?\u0001\u0000\u0000\u0000GH\u0001\u0000\u0000\u0000"+
		"HI\u0001\u0000\u0000\u0000IJ\u0005\u0007\u0000\u0000JK\u0005\r\u0000\u0000"+
		"K\r\u0001\u0000\u0000\u0000LM\u0005\t\u0000\u0000MN\u0005\f\u0000\u0000"+
		"NW\u0005\u0005\u0000\u0000OT\u0005\u000b\u0000\u0000PQ\u0005\u0006\u0000"+
		"\u0000QS\u0005\u000b\u0000\u0000RP\u0001\u0000\u0000\u0000SV\u0001\u0000"+
		"\u0000\u0000TR\u0001\u0000\u0000\u0000TU\u0001\u0000\u0000\u0000UX\u0001"+
		"\u0000\u0000\u0000VT\u0001\u0000\u0000\u0000WO\u0001\u0000\u0000\u0000"+
		"WX\u0001\u0000\u0000\u0000XY\u0001\u0000\u0000\u0000YZ\u0005\u0007\u0000"+
		"\u0000Z[\u0005\r\u0000\u0000[\u000f\u0001\u0000\u0000\u0000\b\u0013\u001b"+
		"47DGTW";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}