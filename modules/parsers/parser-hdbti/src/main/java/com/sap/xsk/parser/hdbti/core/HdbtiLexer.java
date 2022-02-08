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
		T__16=1, T__15=2, T__14=3, T__13=4, T__12=5, T__11=6, T__10=7, T__9=8, 
		T__8=9, T__7=10, T__6=11, T__5=12, T__4=13, T__3=14, T__2=15, T__1=16, 
		T__0=17, STRING=18, BOOLEAN=19, TRUE=20, FALSE=21, WS=22, RB=23, LB=24, 
		EQ=25, LINE_COMMENT=26, COMMENT=27;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"'\\u0000'", "'\\u0001'", "'\\u0002'", "'\\u0003'", "'\\u0004'", "'\\u0005'", 
		"'\\u0006'", "'\\u0007'", "'\b'", "'\t'", "'\n'", "'\\u000B'", "'\f'", 
		"'\r'", "'\\u000E'", "'\\u000F'", "'\\u0010'", "'\\u0011'", "'\\u0012'", 
		"'\\u0013'", "'\\u0014'", "'\\u0015'", "'\\u0016'", "'\\u0017'", "'\\u0018'", 
		"'\\u0019'", "'\\u001A'", "'\\u001B'"
	};
	public static final String[] ruleNames = {
		"T__16", "T__15", "T__14", "T__13", "T__12", "T__11", "T__10", "T__9", 
		"T__8", "T__7", "T__6", "T__5", "T__4", "T__3", "T__2", "T__1", "T__0", 
		"STRING", "EscapeSequence", "HexDigits", "HexDigit", "BOOLEAN", "TRUE", 
		"FALSE", "WS", "RB", "LB", "EQ", "LINE_COMMENT", "COMMENT"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\35\u012a\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\3\2"+
		"\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3"+
		"\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3"+
		"\21\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3"+
		"\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3"+
		"\22\3\22\3\22\3\22\3\23\3\23\3\23\7\23\u00c6\n\23\f\23\16\23\u00c9\13"+
		"\23\3\23\3\23\3\24\3\24\3\24\3\24\5\24\u00d1\n\24\3\24\5\24\u00d4\n\24"+
		"\3\24\3\24\3\24\6\24\u00d9\n\24\r\24\16\24\u00da\3\24\3\24\3\24\3\24\3"+
		"\24\5\24\u00e2\n\24\3\25\3\25\3\25\7\25\u00e7\n\25\f\25\16\25\u00ea\13"+
		"\25\3\25\5\25\u00ed\n\25\3\26\3\26\3\27\3\27\5\27\u00f3\n\27\3\30\3\30"+
		"\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\32\6\32\u0101\n\32\r\32"+
		"\16\32\u0102\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3\36\3"+
		"\36\7\36\u0111\n\36\f\36\16\36\u0114\13\36\3\36\5\36\u0117\n\36\3\36\3"+
		"\36\3\36\3\36\3\37\3\37\3\37\3\37\7\37\u0121\n\37\f\37\16\37\u0124\13"+
		"\37\3\37\3\37\3\37\3\37\3\37\4\u0112\u0122\2 \3\3\5\4\7\5\t\6\13\7\r\b"+
		"\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\2)\2"+
		"+\2-\25/\26\61\27\63\30\65\31\67\329\33;\34=\35\3\2\b\6\2\f\f\17\17$$"+
		"^^\n\2$$))^^ddhhppttvv\3\2\62\65\3\2\629\5\2\62;CHch\6\2\13\f\17\17\""+
		"\"^^\u0135\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2"+
		"\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27"+
		"\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2"+
		"\2\2#\3\2\2\2\2%\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2"+
		"\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\3?\3\2\2"+
		"\2\5D\3\2\2\2\7O\3\2\2\2\t^\3\2\2\2\13g\3\2\2\2\ri\3\2\2\2\17k\3\2\2\2"+
		"\21m\3\2\2\2\23o\3\2\2\2\25t\3\2\2\2\27{\3\2\2\2\31\u0081\3\2\2\2\33\u0090"+
		"\3\2\2\2\35\u0097\3\2\2\2\37\u00a0\3\2\2\2!\u00a2\3\2\2\2#\u00a9\3\2\2"+
		"\2%\u00c2\3\2\2\2\'\u00e1\3\2\2\2)\u00e3\3\2\2\2+\u00ee\3\2\2\2-\u00f2"+
		"\3\2\2\2/\u00f4\3\2\2\2\61\u00f9\3\2\2\2\63\u0100\3\2\2\2\65\u0106\3\2"+
		"\2\2\67\u0108\3\2\2\29\u010a\3\2\2\2;\u010c\3\2\2\2=\u011c\3\2\2\2?@\7"+
		"h\2\2@A\7k\2\2AB\7n\2\2BC\7g\2\2C\4\3\2\2\2DE\7f\2\2EF\7g\2\2FG\7n\2\2"+
		"GH\7k\2\2HI\7o\2\2IJ\7H\2\2JK\7k\2\2KL\7g\2\2LM\7n\2\2MN\7f\2\2N\6\3\2"+
		"\2\2OP\7w\2\2PQ\7u\2\2QR\7g\2\2RS\7J\2\2ST\7g\2\2TU\7c\2\2UV\7f\2\2VW"+
		"\7g\2\2WX\7t\2\2XY\7P\2\2YZ\7c\2\2Z[\7o\2\2[\\\7g\2\2\\]\7u\2\2]\b\3\2"+
		"\2\2^_\7j\2\2_`\7f\2\2`a\7d\2\2ab\7v\2\2bc\7c\2\2cd\7d\2\2de\7n\2\2ef"+
		"\7g\2\2f\n\3\2\2\2gh\7<\2\2h\f\3\2\2\2ij\7}\2\2j\16\3\2\2\2kl\7=\2\2l"+
		"\20\3\2\2\2mn\7\177\2\2n\22\3\2\2\2op\7m\2\2pq\7g\2\2qr\7{\2\2rs\7u\2"+
		"\2s\24\3\2\2\2tu\7u\2\2uv\7e\2\2vw\7j\2\2wx\7g\2\2xy\7o\2\2yz\7c\2\2z"+
		"\26\3\2\2\2{|\7v\2\2|}\7c\2\2}~\7d\2\2~\177\7n\2\2\177\u0080\7g\2\2\u0080"+
		"\30\3\2\2\2\u0081\u0082\7f\2\2\u0082\u0083\7g\2\2\u0083\u0084\7n\2\2\u0084"+
		"\u0085\7k\2\2\u0085\u0086\7o\2\2\u0086\u0087\7G\2\2\u0087\u0088\7p\2\2"+
		"\u0088\u0089\7e\2\2\u0089\u008a\7n\2\2\u008a\u008b\7q\2\2\u008b\u008c"+
		"\7u\2\2\u008c\u008d\7k\2\2\u008d\u008e\7p\2\2\u008e\u008f\7i\2\2\u008f"+
		"\32\3\2\2\2\u0090\u0091\7j\2\2\u0091\u0092\7g\2\2\u0092\u0093\7c\2\2\u0093"+
		"\u0094\7f\2\2\u0094\u0095\7g\2\2\u0095\u0096\7t\2\2\u0096\34\3\2\2\2\u0097"+
		"\u0098\7e\2\2\u0098\u0099\7f\2\2\u0099\u009a\7u\2\2\u009a\u009b\7v\2\2"+
		"\u009b\u009c\7c\2\2\u009c\u009d\7d\2\2\u009d\u009e\7n\2\2\u009e\u009f"+
		"\7g\2\2\u009f\36\3\2\2\2\u00a0\u00a1\7.\2\2\u00a1 \3\2\2\2\u00a2\u00a3"+
		"\7k\2\2\u00a3\u00a4\7o\2\2\u00a4\u00a5\7r\2\2\u00a5\u00a6\7q\2\2\u00a6"+
		"\u00a7\7t\2\2\u00a7\u00a8\7v\2\2\u00a8\"\3\2\2\2\u00a9\u00aa\7f\2\2\u00aa"+
		"\u00ab\7k\2\2\u00ab\u00ac\7u\2\2\u00ac\u00ad\7v\2\2\u00ad\u00ae\7k\2\2"+
		"\u00ae\u00af\7p\2\2\u00af\u00b0\7i\2\2\u00b0\u00b1\7w\2\2\u00b1\u00b2"+
		"\7k\2\2\u00b2\u00b3\7u\2\2\u00b3\u00b4\7j\2\2\u00b4\u00b5\7G\2\2\u00b5"+
		"\u00b6\7o\2\2\u00b6\u00b7\7r\2\2\u00b7\u00b8\7v\2\2\u00b8\u00b9\7{\2\2"+
		"\u00b9\u00ba\7H\2\2\u00ba\u00bb\7t\2\2\u00bb\u00bc\7q\2\2\u00bc\u00bd"+
		"\7o\2\2\u00bd\u00be\7P\2\2\u00be\u00bf\7w\2\2\u00bf\u00c0\7n\2\2\u00c0"+
		"\u00c1\7n\2\2\u00c1$\3\2\2\2\u00c2\u00c7\7$\2\2\u00c3\u00c6\n\2\2\2\u00c4"+
		"\u00c6\5\'\24\2\u00c5\u00c3\3\2\2\2\u00c5\u00c4\3\2\2\2\u00c6\u00c9\3"+
		"\2\2\2\u00c7\u00c5\3\2\2\2\u00c7\u00c8\3\2\2\2\u00c8\u00ca\3\2\2\2\u00c9"+
		"\u00c7\3\2\2\2\u00ca\u00cb\7$\2\2\u00cb&\3\2\2\2\u00cc\u00cd\7^\2\2\u00cd"+
		"\u00e2\t\3\2\2\u00ce\u00d3\7^\2\2\u00cf\u00d1\t\4\2\2\u00d0\u00cf\3\2"+
		"\2\2\u00d0\u00d1\3\2\2\2\u00d1\u00d2\3\2\2\2\u00d2\u00d4\t\5\2\2\u00d3"+
		"\u00d0\3\2\2\2\u00d3\u00d4\3\2\2\2\u00d4\u00d5\3\2\2\2\u00d5\u00e2\t\5"+
		"\2\2\u00d6\u00d8\7^\2\2\u00d7\u00d9\7w\2\2\u00d8\u00d7\3\2\2\2\u00d9\u00da"+
		"\3\2\2\2\u00da\u00d8\3\2\2\2\u00da\u00db\3\2\2\2\u00db\u00dc\3\2\2\2\u00dc"+
		"\u00dd\5+\26\2\u00dd\u00de\5+\26\2\u00de\u00df\5+\26\2\u00df\u00e0\5+"+
		"\26\2\u00e0\u00e2\3\2\2\2\u00e1\u00cc\3\2\2\2\u00e1\u00ce\3\2\2\2\u00e1"+
		"\u00d6\3\2\2\2\u00e2(\3\2\2\2\u00e3\u00ec\5+\26\2\u00e4\u00e7\5+\26\2"+
		"\u00e5\u00e7\7a\2\2\u00e6\u00e4\3\2\2\2\u00e6\u00e5\3\2\2\2\u00e7\u00ea"+
		"\3\2\2\2\u00e8\u00e6\3\2\2\2\u00e8\u00e9\3\2\2\2\u00e9\u00eb\3\2\2\2\u00ea"+
		"\u00e8\3\2\2\2\u00eb\u00ed\5+\26\2\u00ec\u00e8\3\2\2\2\u00ec\u00ed\3\2"+
		"\2\2\u00ed*\3\2\2\2\u00ee\u00ef\t\6\2\2\u00ef,\3\2\2\2\u00f0\u00f3\5/"+
		"\30\2\u00f1\u00f3\5\61\31\2\u00f2\u00f0\3\2\2\2\u00f2\u00f1\3\2\2\2\u00f3"+
		".\3\2\2\2\u00f4\u00f5\7v\2\2\u00f5\u00f6\7t\2\2\u00f6\u00f7\7w\2\2\u00f7"+
		"\u00f8\7g\2\2\u00f8\60\3\2\2\2\u00f9\u00fa\7h\2\2\u00fa\u00fb\7c\2\2\u00fb"+
		"\u00fc\7n\2\2\u00fc\u00fd\7u\2\2\u00fd\u00fe\7g\2\2\u00fe\62\3\2\2\2\u00ff"+
		"\u0101\t\7\2\2\u0100\u00ff\3\2\2\2\u0101\u0102\3\2\2\2\u0102\u0100\3\2"+
		"\2\2\u0102\u0103\3\2\2\2\u0103\u0104\3\2\2\2\u0104\u0105\b\32\2\2\u0105"+
		"\64\3\2\2\2\u0106\u0107\7]\2\2\u0107\66\3\2\2\2\u0108\u0109\7_\2\2\u0109"+
		"8\3\2\2\2\u010a\u010b\7?\2\2\u010b:\3\2\2\2\u010c\u010d\7\61\2\2\u010d"+
		"\u010e\7\61\2\2\u010e\u0112\3\2\2\2\u010f\u0111\13\2\2\2\u0110\u010f\3"+
		"\2\2\2\u0111\u0114\3\2\2\2\u0112\u0113\3\2\2\2\u0112\u0110\3\2\2\2\u0113"+
		"\u0116\3\2\2\2\u0114\u0112\3\2\2\2\u0115\u0117\7\17\2\2\u0116\u0115\3"+
		"\2\2\2\u0116\u0117\3\2\2\2\u0117\u0118\3\2\2\2\u0118\u0119\7\f\2\2\u0119"+
		"\u011a\3\2\2\2\u011a\u011b\b\36\2\2\u011b<\3\2\2\2\u011c\u011d\7\61\2"+
		"\2\u011d\u011e\7,\2\2\u011e\u0122\3\2\2\2\u011f\u0121\13\2\2\2\u0120\u011f"+
		"\3\2\2\2\u0121\u0124\3\2\2\2\u0122\u0123\3\2\2\2\u0122\u0120\3\2\2\2\u0123"+
		"\u0125\3\2\2\2\u0124\u0122\3\2\2\2\u0125\u0126\7,\2\2\u0126\u0127\7\61"+
		"\2\2\u0127\u0128\3\2\2\2\u0128\u0129\b\37\2\2\u0129>\3\2\2\2\21\2\u00c5"+
		"\u00c7\u00d0\u00d3\u00da\u00e1\u00e6\u00e8\u00ec\u00f2\u0102\u0112\u0116"+
		"\u0122\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}