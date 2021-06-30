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
// Generated from com/sap/xsk/parser/hdbview/core/Hdbview.g4 by ANTLR 4.3
package com.sap.xsk.parser.hdbview.core;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class HdbviewLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__7=1, T__6=2, T__5=3, T__4=4, T__3=5, T__2=6, T__1=7, T__0=8, BOOLEAN=9, 
		STRING=10, EQ=11, SEMICOLON=12, COMMA=13, WS=14, ESC=15;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"'\\u0000'", "'\\u0001'", "'\\u0002'", "'\\u0003'", "'\\u0004'", "'\\u0005'", 
		"'\\u0006'", "'\\u0007'", "'\b'", "'\t'", "'\n'", "'\\u000B'", "'\f'", 
		"'\r'", "'\\u000E'", "'\\u000F'"
	};
	public static final String[] ruleNames = {
		"T__7", "T__6", "T__5", "T__4", "T__3", "T__2", "T__1", "T__0", "BOOLEAN", 
		"STRING", "EQ", "SEMICOLON", "COMMA", "WS", "ESC"
	};


	public HdbviewLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Hdbview.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\21\u008d\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\5\no\n\n\3\13\3\13\3\13\7\13t\n\13\f\13\16\13w\13\13\3\13"+
		"\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\6\17\u0082\n\17\r\17\16\17\u0083"+
		"\3\17\3\17\3\20\3\20\3\20\3\20\5\20\u008c\n\20\3u\2\21\3\3\5\4\7\5\t\6"+
		"\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21\3\2\3\5\2"+
		"\13\f\16\17\"\"\u0091\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2"+
		"\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25"+
		"\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2"+
		"\2\2\3!\3\2\2\2\5(\3\2\2\2\79\3\2\2\2\t;\3\2\2\2\13F\3\2\2\2\rV\3\2\2"+
		"\2\17X\3\2\2\2\21^\3\2\2\2\23n\3\2\2\2\25p\3\2\2\2\27z\3\2\2\2\31|\3\2"+
		"\2\2\33~\3\2\2\2\35\u0081\3\2\2\2\37\u008b\3\2\2\2!\"\7u\2\2\"#\7e\2\2"+
		"#$\7j\2\2$%\7g\2\2%&\7o\2\2&\'\7c\2\2\'\4\3\2\2\2()\7f\2\2)*\7g\2\2*+"+
		"\7r\2\2+,\7g\2\2,-\7p\2\2-.\7f\2\2./\7u\2\2/\60\7a\2\2\60\61\7q\2\2\61"+
		"\62\7p\2\2\62\63\7a\2\2\63\64\7v\2\2\64\65\7c\2\2\65\66\7d\2\2\66\67\7"+
		"n\2\2\678\7g\2\28\6\3\2\2\29:\7]\2\2:\b\3\2\2\2;<\7f\2\2<=\7g\2\2=>\7"+
		"r\2\2>?\7g\2\2?@\7p\2\2@A\7f\2\2AB\7u\2\2BC\7a\2\2CD\7q\2\2DE\7p\2\2E"+
		"\n\3\2\2\2FG\7f\2\2GH\7g\2\2HI\7r\2\2IJ\7g\2\2JK\7p\2\2KL\7f\2\2LM\7u"+
		"\2\2MN\7a\2\2NO\7q\2\2OP\7p\2\2PQ\7a\2\2QR\7x\2\2RS\7k\2\2ST\7g\2\2TU"+
		"\7y\2\2U\f\3\2\2\2VW\7_\2\2W\16\3\2\2\2XY\7s\2\2YZ\7w\2\2Z[\7g\2\2[\\"+
		"\7t\2\2\\]\7{\2\2]\20\3\2\2\2^_\7r\2\2_`\7w\2\2`a\7d\2\2ab\7n\2\2bc\7"+
		"k\2\2cd\7e\2\2d\22\3\2\2\2ef\7v\2\2fg\7t\2\2gh\7w\2\2ho\7g\2\2ij\7h\2"+
		"\2jk\7c\2\2kl\7n\2\2lm\7u\2\2mo\7g\2\2ne\3\2\2\2ni\3\2\2\2o\24\3\2\2\2"+
		"pu\7$\2\2qt\5\37\20\2rt\13\2\2\2sq\3\2\2\2sr\3\2\2\2tw\3\2\2\2uv\3\2\2"+
		"\2us\3\2\2\2vx\3\2\2\2wu\3\2\2\2xy\7$\2\2y\26\3\2\2\2z{\7?\2\2{\30\3\2"+
		"\2\2|}\7=\2\2}\32\3\2\2\2~\177\7.\2\2\177\34\3\2\2\2\u0080\u0082\t\2\2"+
		"\2\u0081\u0080\3\2\2\2\u0082\u0083\3\2\2\2\u0083\u0081\3\2\2\2\u0083\u0084"+
		"\3\2\2\2\u0084\u0085\3\2\2\2\u0085\u0086\b\17\2\2\u0086\36\3\2\2\2\u0087"+
		"\u0088\7^\2\2\u0088\u008c\7$\2\2\u0089\u008a\7^\2\2\u008a\u008c\7^\2\2"+
		"\u008b\u0087\3\2\2\2\u008b\u0089\3\2\2\2\u008c \3\2\2\2\b\2nsu\u0083\u008b"+
		"\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}