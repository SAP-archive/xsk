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
// Generated from com/sap/xsk/parser/hdbti/core/Hdbti.g4 by ANTLR 4.3
package com.sap.xsk.parser.hdbti.core;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class HdbtiLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__14=1, T__13=2, T__12=3, T__11=4, T__10=5, T__9=6, T__8=7, T__7=8, T__6=9, 
		T__5=10, T__4=11, T__3=12, T__2=13, T__1=14, T__0=15, STRING=16, BOOLEAN=17, 
		TRUE=18, FALSE=19, WS=20, RB=21, LB=22, EQ=23;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"'\\u0000'", "'\\u0001'", "'\\u0002'", "'\\u0003'", "'\\u0004'", "'\\u0005'", 
		"'\\u0006'", "'\\u0007'", "'\b'", "'\t'", "'\n'", "'\\u000B'", "'\f'", 
		"'\r'", "'\\u000E'", "'\\u000F'", "'\\u0010'", "'\\u0011'", "'\\u0012'", 
		"'\\u0013'", "'\\u0014'", "'\\u0015'", "'\\u0016'", "'\\u0017'"
	};
	public static final String[] ruleNames = {
		"T__14", "T__13", "T__12", "T__11", "T__10", "T__9", "T__8", "T__7", "T__6", 
		"T__5", "T__4", "T__3", "T__2", "T__1", "T__0", "STRING", "EscapeSequence", 
		"HexDigits", "HexDigit", "BOOLEAN", "TRUE", "FALSE", "WS", "RB", "LB", 
		"EQ"
	};


	public HdbtiLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Hdbti.g4"; }

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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\31\u00f2\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3"+
		"\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3"+
		"\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3"+
		"\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\7\21\u00ac\n\21\f\21\16\21"+
		"\u00af\13\21\3\21\3\21\3\22\3\22\3\22\3\22\5\22\u00b7\n\22\3\22\5\22\u00ba"+
		"\n\22\3\22\3\22\3\22\6\22\u00bf\n\22\r\22\16\22\u00c0\3\22\3\22\3\22\3"+
		"\22\3\22\5\22\u00c8\n\22\3\23\3\23\3\23\7\23\u00cd\n\23\f\23\16\23\u00d0"+
		"\13\23\3\23\5\23\u00d3\n\23\3\24\3\24\3\25\3\25\5\25\u00d9\n\25\3\26\3"+
		"\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\30\6\30\u00e7\n\30"+
		"\r\30\16\30\u00e8\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33\2\2\34\3\3\5"+
		"\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21"+
		"!\22#\2%\2\'\2)\23+\24-\25/\26\61\27\63\30\65\31\3\2\b\6\2\f\f\17\17$"+
		"$^^\n\2$$))^^ddhhppttvv\3\2\62\65\3\2\629\5\2\62;CHch\5\2\13\13\"\"^^"+
		"\u00fa\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2"+
		"\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3"+
		"\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2"+
		"\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2"+
		"\2\65\3\2\2\2\3\67\3\2\2\2\5<\3\2\2\2\7G\3\2\2\2\tV\3\2\2\2\13X\3\2\2"+
		"\2\rZ\3\2\2\2\17\\\3\2\2\2\21^\3\2\2\2\23c\3\2\2\2\25j\3\2\2\2\27p\3\2"+
		"\2\2\31\177\3\2\2\2\33\u0086\3\2\2\2\35\u0088\3\2\2\2\37\u008f\3\2\2\2"+
		"!\u00a8\3\2\2\2#\u00c7\3\2\2\2%\u00c9\3\2\2\2\'\u00d4\3\2\2\2)\u00d8\3"+
		"\2\2\2+\u00da\3\2\2\2-\u00df\3\2\2\2/\u00e6\3\2\2\2\61\u00ec\3\2\2\2\63"+
		"\u00ee\3\2\2\2\65\u00f0\3\2\2\2\678\7h\2\289\7k\2\29:\7n\2\2:;\7g\2\2"+
		";\4\3\2\2\2<=\7f\2\2=>\7g\2\2>?\7n\2\2?@\7k\2\2@A\7o\2\2AB\7H\2\2BC\7"+
		"k\2\2CD\7g\2\2DE\7n\2\2EF\7f\2\2F\6\3\2\2\2GH\7w\2\2HI\7u\2\2IJ\7g\2\2"+
		"JK\7J\2\2KL\7g\2\2LM\7c\2\2MN\7f\2\2NO\7g\2\2OP\7t\2\2PQ\7P\2\2QR\7c\2"+
		"\2RS\7o\2\2ST\7g\2\2TU\7u\2\2U\b\3\2\2\2VW\7<\2\2W\n\3\2\2\2XY\7}\2\2"+
		"Y\f\3\2\2\2Z[\7=\2\2[\16\3\2\2\2\\]\7\177\2\2]\20\3\2\2\2^_\7m\2\2_`\7"+
		"g\2\2`a\7{\2\2ab\7u\2\2b\22\3\2\2\2cd\7u\2\2de\7e\2\2ef\7j\2\2fg\7g\2"+
		"\2gh\7o\2\2hi\7c\2\2i\24\3\2\2\2jk\7v\2\2kl\7c\2\2lm\7d\2\2mn\7n\2\2n"+
		"o\7g\2\2o\26\3\2\2\2pq\7f\2\2qr\7g\2\2rs\7n\2\2st\7k\2\2tu\7o\2\2uv\7"+
		"G\2\2vw\7p\2\2wx\7e\2\2xy\7n\2\2yz\7q\2\2z{\7u\2\2{|\7k\2\2|}\7p\2\2}"+
		"~\7i\2\2~\30\3\2\2\2\177\u0080\7j\2\2\u0080\u0081\7g\2\2\u0081\u0082\7"+
		"c\2\2\u0082\u0083\7f\2\2\u0083\u0084\7g\2\2\u0084\u0085\7t\2\2\u0085\32"+
		"\3\2\2\2\u0086\u0087\7.\2\2\u0087\34\3\2\2\2\u0088\u0089\7k\2\2\u0089"+
		"\u008a\7o\2\2\u008a\u008b\7r\2\2\u008b\u008c\7q\2\2\u008c\u008d\7t\2\2"+
		"\u008d\u008e\7v\2\2\u008e\36\3\2\2\2\u008f\u0090\7f\2\2\u0090\u0091\7"+
		"k\2\2\u0091\u0092\7u\2\2\u0092\u0093\7v\2\2\u0093\u0094\7k\2\2\u0094\u0095"+
		"\7p\2\2\u0095\u0096\7i\2\2\u0096\u0097\7w\2\2\u0097\u0098\7k\2\2\u0098"+
		"\u0099\7u\2\2\u0099\u009a\7j\2\2\u009a\u009b\7G\2\2\u009b\u009c\7o\2\2"+
		"\u009c\u009d\7r\2\2\u009d\u009e\7v\2\2\u009e\u009f\7{\2\2\u009f\u00a0"+
		"\7H\2\2\u00a0\u00a1\7t\2\2\u00a1\u00a2\7q\2\2\u00a2\u00a3\7o\2\2\u00a3"+
		"\u00a4\7P\2\2\u00a4\u00a5\7w\2\2\u00a5\u00a6\7n\2\2\u00a6\u00a7\7n\2\2"+
		"\u00a7 \3\2\2\2\u00a8\u00ad\7$\2\2\u00a9\u00ac\n\2\2\2\u00aa\u00ac\5#"+
		"\22\2\u00ab\u00a9\3\2\2\2\u00ab\u00aa\3\2\2\2\u00ac\u00af\3\2\2\2\u00ad"+
		"\u00ab\3\2\2\2\u00ad\u00ae\3\2\2\2\u00ae\u00b0\3\2\2\2\u00af\u00ad\3\2"+
		"\2\2\u00b0\u00b1\7$\2\2\u00b1\"\3\2\2\2\u00b2\u00b3\7^\2\2\u00b3\u00c8"+
		"\t\3\2\2\u00b4\u00b9\7^\2\2\u00b5\u00b7\t\4\2\2\u00b6\u00b5\3\2\2\2\u00b6"+
		"\u00b7\3\2\2\2\u00b7\u00b8\3\2\2\2\u00b8\u00ba\t\5\2\2\u00b9\u00b6\3\2"+
		"\2\2\u00b9\u00ba\3\2\2\2\u00ba\u00bb\3\2\2\2\u00bb\u00c8\t\5\2\2\u00bc"+
		"\u00be\7^\2\2\u00bd\u00bf\7w\2\2\u00be\u00bd\3\2\2\2\u00bf\u00c0\3\2\2"+
		"\2\u00c0\u00be\3\2\2\2\u00c0\u00c1\3\2\2\2\u00c1\u00c2\3\2\2\2\u00c2\u00c3"+
		"\5\'\24\2\u00c3\u00c4\5\'\24\2\u00c4\u00c5\5\'\24\2\u00c5\u00c6\5\'\24"+
		"\2\u00c6\u00c8\3\2\2\2\u00c7\u00b2\3\2\2\2\u00c7\u00b4\3\2\2\2\u00c7\u00bc"+
		"\3\2\2\2\u00c8$\3\2\2\2\u00c9\u00d2\5\'\24\2\u00ca\u00cd\5\'\24\2\u00cb"+
		"\u00cd\7a\2\2\u00cc\u00ca\3\2\2\2\u00cc\u00cb\3\2\2\2\u00cd\u00d0\3\2"+
		"\2\2\u00ce\u00cc\3\2\2\2\u00ce\u00cf\3\2\2\2\u00cf\u00d1\3\2\2\2\u00d0"+
		"\u00ce\3\2\2\2\u00d1\u00d3\5\'\24\2\u00d2\u00ce\3\2\2\2\u00d2\u00d3\3"+
		"\2\2\2\u00d3&\3\2\2\2\u00d4\u00d5\t\6\2\2\u00d5(\3\2\2\2\u00d6\u00d9\5"+
		"+\26\2\u00d7\u00d9\5-\27\2\u00d8\u00d6\3\2\2\2\u00d8\u00d7\3\2\2\2\u00d9"+
		"*\3\2\2\2\u00da\u00db\7v\2\2\u00db\u00dc\7t\2\2\u00dc\u00dd\7w\2\2\u00dd"+
		"\u00de\7g\2\2\u00de,\3\2\2\2\u00df\u00e0\7h\2\2\u00e0\u00e1\7c\2\2\u00e1"+
		"\u00e2\7n\2\2\u00e2\u00e3\7u\2\2\u00e3\u00e4\7g\2\2\u00e4.\3\2\2\2\u00e5"+
		"\u00e7\t\7\2\2\u00e6\u00e5\3\2\2\2\u00e7\u00e8\3\2\2\2\u00e8\u00e6\3\2"+
		"\2\2\u00e8\u00e9\3\2\2\2\u00e9\u00ea\3\2\2\2\u00ea\u00eb\b\30\2\2\u00eb"+
		"\60\3\2\2\2\u00ec\u00ed\7]\2\2\u00ed\62\3\2\2\2\u00ee\u00ef\7_\2\2\u00ef"+
		"\64\3\2\2\2\u00f0\u00f1\7?\2\2\u00f1\66\3\2\2\2\16\2\u00ab\u00ad\u00b6"+
		"\u00b9\u00c0\u00c7\u00cc\u00ce\u00d2\u00d8\u00e8\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}