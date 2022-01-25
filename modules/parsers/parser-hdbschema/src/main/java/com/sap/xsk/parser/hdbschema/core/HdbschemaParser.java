/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2022 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
// Generated from com/sap/xsk/parser/hdbschema/core/Hdbschema.g4 by ANTLR 4.3
package com.sap.xsk.parser.hdbschema.core;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class HdbschemaParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, STRING=2, EQ=3, SEMICOLON=4, COMMA=5, WS=6, ESC=7, LINE_COMMENT=8, 
		COMMENT=9;
	public static final String[] tokenNames = {
		"<INVALID>", "'schema_name'", "STRING", "'='", "';'", "','", "WS", "ESC", 
		"LINE_COMMENT", "COMMENT"
	};
	public static final int
		RULE_hdbschemaDefinition = 0, RULE_schemaNameProp = 1;
	public static final String[] ruleNames = {
		"hdbschemaDefinition", "schemaNameProp"
	};

	@Override
	public String getGrammarFileName() { return "Hdbschema.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public HdbschemaParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class HdbschemaDefinitionContext extends ParserRuleContext {
		public SchemaNamePropContext schemaNameProp() {
			return getRuleContext(SchemaNamePropContext.class,0);
		}
		public HdbschemaDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_hdbschemaDefinition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbschemaListener ) ((HdbschemaListener)listener).enterHdbschemaDefinition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbschemaListener ) ((HdbschemaListener)listener).exitHdbschemaDefinition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbschemaVisitor ) return ((HdbschemaVisitor<? extends T>)visitor).visitHdbschemaDefinition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HdbschemaDefinitionContext hdbschemaDefinition() throws RecognitionException {
		HdbschemaDefinitionContext _localctx = new HdbschemaDefinitionContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_hdbschemaDefinition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(4); schemaNameProp();
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
		public TerminalNode SEMICOLON() { return getToken(HdbschemaParser.SEMICOLON, 0); }
		public TerminalNode EQ() { return getToken(HdbschemaParser.EQ, 0); }
		public TerminalNode STRING() { return getToken(HdbschemaParser.STRING, 0); }
		public SchemaNamePropContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_schemaNameProp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbschemaListener ) ((HdbschemaListener)listener).enterSchemaNameProp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbschemaListener ) ((HdbschemaListener)listener).exitSchemaNameProp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbschemaVisitor ) return ((HdbschemaVisitor<? extends T>)visitor).visitSchemaNameProp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SchemaNamePropContext schemaNameProp() throws RecognitionException {
		SchemaNamePropContext _localctx = new SchemaNamePropContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_schemaNameProp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(6); match(T__0);
			setState(7); match(EQ);
			setState(8); match(STRING);
			setState(9); match(SEMICOLON);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\13\16\4\2\t\2\4\3"+
		"\t\3\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\2\2\4\2\4\2\2\13\2\6\3\2\2\2\4\b"+
		"\3\2\2\2\6\7\5\4\3\2\7\3\3\2\2\2\b\t\7\3\2\2\t\n\7\5\2\2\n\13\7\4\2\2"+
		"\13\f\7\6\2\2\f\5\3\2\2\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}