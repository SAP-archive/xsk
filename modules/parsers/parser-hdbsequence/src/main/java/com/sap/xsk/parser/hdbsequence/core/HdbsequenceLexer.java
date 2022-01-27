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
// Generated from com/sap/xsk/parser/hdbsequence/core/Hdbsequence.g4 by ANTLR 4.3
package com.sap.xsk.parser.hdbsequence.core;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class HdbsequenceLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__11=1, T__10=2, T__9=3, T__8=4, T__7=5, T__6=6, T__5=7, T__4=8, T__3=9, 
		T__2=10, T__1=11, T__0=12, STRING=13, INT=14, BOOLEAN=15, TRUE=16, FALSE=17, 
		WS=18, LB=19, RB=20, EQ=21, SC=22, SIGNED_INT=23, LINE_COMMENT=24, COMMENT=25;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"'\\u0000'", "'\\u0001'", "'\\u0002'", "'\\u0003'", "'\\u0004'", "'\\u0005'", 
		"'\\u0006'", "'\\u0007'", "'\b'", "'\t'", "'\n'", "'\\u000B'", "'\f'", 
		"'\r'", "'\\u000E'", "'\\u000F'", "'\\u0010'", "'\\u0011'", "'\\u0012'", 
		"'\\u0013'", "'\\u0014'", "'\\u0015'", "'\\u0016'", "'\\u0017'", "'\\u0018'", 
		"'\\u0019'"
	};
	public static final String[] ruleNames = {
		"T__11", "T__10", "T__9", "T__8", "T__7", "T__6", "T__5", "T__4", "T__3", 
		"T__2", "T__1", "T__0", "STRING", "INT", "BOOLEAN", "TRUE", "FALSE", "WS", 
		"LB", "RB", "EQ", "SC", "SIGNED_INT", "LINE_COMMENT", "COMMENT", "EscapeSequence", 
		"HexDigits", "HexDigit"
	};


	public HdbsequenceLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Hdbsequence.g4"; }

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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\33\u012f\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3"+
		"\r\3\16\3\16\3\16\3\16\7\16\u00bf\n\16\f\16\16\16\u00c2\13\16\3\16\3\16"+
		"\3\17\5\17\u00c7\n\17\3\17\6\17\u00ca\n\17\r\17\16\17\u00cb\3\20\3\20"+
		"\5\20\u00d0\n\20\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\23\6\23\u00de\n\23\r\23\16\23\u00df\3\23\3\23\3\24\3\24\3\25\3\25\3"+
		"\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\31\3\31\7\31\u00f2\n\31\f\31"+
		"\16\31\u00f5\13\31\3\31\5\31\u00f8\n\31\3\31\3\31\3\31\3\31\3\32\3\32"+
		"\3\32\3\32\7\32\u0102\n\32\f\32\16\32\u0105\13\32\3\32\3\32\3\32\3\32"+
		"\3\32\3\33\3\33\3\33\3\33\5\33\u0110\n\33\3\33\5\33\u0113\n\33\3\33\3"+
		"\33\3\33\6\33\u0118\n\33\r\33\16\33\u0119\3\33\3\33\3\33\3\33\3\33\5\33"+
		"\u0121\n\33\3\34\3\34\3\34\7\34\u0126\n\34\f\34\16\34\u0129\13\34\3\34"+
		"\5\34\u012c\n\34\3\35\3\35\4\u00f3\u0103\2\36\3\3\5\4\7\5\t\6\13\7\r\b"+
		"\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26"+
		"+\27-\30/\31\61\32\63\33\65\2\67\29\2\3\2\t\6\2\f\f\17\17$$^^\3\2\62;"+
		"\6\2\13\f\17\17\"\"^^\n\2$$))^^ddhhppttvv\3\2\62\65\3\2\629\5\2\62;CH"+
		"ch\u013d\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2"+
		"\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27"+
		"\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2"+
		"\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2"+
		"\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\3;\3\2\2\2\5F\3\2\2\2\7O\3\2\2\2"+
		"\tV\3\2\2\2\13g\3\2\2\2\rr\3\2\2\2\17y\3\2\2\2\21\u0082\3\2\2\2\23\u008f"+
		"\3\2\2\2\25\u009f\3\2\2\2\27\u00a6\3\2\2\2\31\u00b1\3\2\2\2\33\u00ba\3"+
		"\2\2\2\35\u00c6\3\2\2\2\37\u00cf\3\2\2\2!\u00d1\3\2\2\2#\u00d6\3\2\2\2"+
		"%\u00dd\3\2\2\2\'\u00e3\3\2\2\2)\u00e5\3\2\2\2+\u00e7\3\2\2\2-\u00e9\3"+
		"\2\2\2/\u00eb\3\2\2\2\61\u00ed\3\2\2\2\63\u00fd\3\2\2\2\65\u0120\3\2\2"+
		"\2\67\u0122\3\2\2\29\u012d\3\2\2\2;<\7p\2\2<=\7q\2\2=>\7o\2\2>?\7k\2\2"+
		"?@\7p\2\2@A\7x\2\2AB\7c\2\2BC\7n\2\2CD\7w\2\2DE\7g\2\2E\4\3\2\2\2FG\7"+
		"t\2\2GH\7g\2\2HI\7u\2\2IJ\7g\2\2JK\7v\2\2KL\7a\2\2LM\7d\2\2MN\7{\2\2N"+
		"\6\3\2\2\2OP\7u\2\2PQ\7e\2\2QR\7j\2\2RS\7g\2\2ST\7o\2\2TU\7c\2\2U\b\3"+
		"\2\2\2VW\7f\2\2WX\7g\2\2XY\7r\2\2YZ\7g\2\2Z[\7p\2\2[\\\7f\2\2\\]\7u\2"+
		"\2]^\7a\2\2^_\7q\2\2_`\7p\2\2`a\7a\2\2ab\7v\2\2bc\7c\2\2cd\7d\2\2de\7"+
		"n\2\2ef\7g\2\2f\n\3\2\2\2gh\7u\2\2hi\7v\2\2ij\7c\2\2jk\7t\2\2kl\7v\2\2"+
		"lm\7a\2\2mn\7y\2\2no\7k\2\2op\7v\2\2pq\7j\2\2q\f\3\2\2\2rs\7e\2\2st\7"+
		"{\2\2tu\7e\2\2uv\7n\2\2vw\7g\2\2wx\7u\2\2x\16\3\2\2\2yz\7o\2\2z{\7c\2"+
		"\2{|\7z\2\2|}\7x\2\2}~\7c\2\2~\177\7n\2\2\177\u0080\7w\2\2\u0080\u0081"+
		"\7g\2\2\u0081\20\3\2\2\2\u0082\u0083\7k\2\2\u0083\u0084\7p\2\2\u0084\u0085"+
		"\7e\2\2\u0085\u0086\7t\2\2\u0086\u0087\7g\2\2\u0087\u0088\7o\2\2\u0088"+
		"\u0089\7g\2\2\u0089\u008a\7p\2\2\u008a\u008b\7v\2\2\u008b\u008c\7a\2\2"+
		"\u008c\u008d\7d\2\2\u008d\u008e\7{\2\2\u008e\22\3\2\2\2\u008f\u0090\7"+
		"f\2\2\u0090\u0091\7g\2\2\u0091\u0092\7r\2\2\u0092\u0093\7g\2\2\u0093\u0094"+
		"\7p\2\2\u0094\u0095\7f\2\2\u0095\u0096\7u\2\2\u0096\u0097\7a\2\2\u0097"+
		"\u0098\7q\2\2\u0098\u0099\7p\2\2\u0099\u009a\7a\2\2\u009a\u009b\7x\2\2"+
		"\u009b\u009c\7k\2\2\u009c\u009d\7g\2\2\u009d\u009e\7y\2\2\u009e\24\3\2"+
		"\2\2\u009f\u00a0\7r\2\2\u00a0\u00a1\7w\2\2\u00a1\u00a2\7d\2\2\u00a2\u00a3"+
		"\7n\2\2\u00a3\u00a4\7k\2\2\u00a4\u00a5\7e\2\2\u00a5\26\3\2\2\2\u00a6\u00a7"+
		"\7p\2\2\u00a7\u00a8\7q\2\2\u00a8\u00a9\7o\2\2\u00a9\u00aa\7c\2\2\u00aa"+
		"\u00ab\7z\2\2\u00ab\u00ac\7x\2\2\u00ac\u00ad\7c\2\2\u00ad\u00ae\7n\2\2"+
		"\u00ae\u00af\7w\2\2\u00af\u00b0\7g\2\2\u00b0\30\3\2\2\2\u00b1\u00b2\7"+
		"o\2\2\u00b2\u00b3\7k\2\2\u00b3\u00b4\7p\2\2\u00b4\u00b5\7x\2\2\u00b5\u00b6"+
		"\7c\2\2\u00b6\u00b7\7n\2\2\u00b7\u00b8\7w\2\2\u00b8\u00b9\7g\2\2\u00b9"+
		"\32\3\2\2\2\u00ba\u00c0\7$\2\2\u00bb\u00bf\n\2\2\2\u00bc\u00bf\5\65\33"+
		"\2\u00bd\u00bf\5%\23\2\u00be\u00bb\3\2\2\2\u00be\u00bc\3\2\2\2\u00be\u00bd"+
		"\3\2\2\2\u00bf\u00c2\3\2\2\2\u00c0\u00be\3\2\2\2\u00c0\u00c1\3\2\2\2\u00c1"+
		"\u00c3\3\2\2\2\u00c2\u00c0\3\2\2\2\u00c3\u00c4\7$\2\2\u00c4\34\3\2\2\2"+
		"\u00c5\u00c7\5/\30\2\u00c6\u00c5\3\2\2\2\u00c6\u00c7\3\2\2\2\u00c7\u00c9"+
		"\3\2\2\2\u00c8\u00ca\t\3\2\2\u00c9\u00c8\3\2\2\2\u00ca\u00cb\3\2\2\2\u00cb"+
		"\u00c9\3\2\2\2\u00cb\u00cc\3\2\2\2\u00cc\36\3\2\2\2\u00cd\u00d0\5!\21"+
		"\2\u00ce\u00d0\5#\22\2\u00cf\u00cd\3\2\2\2\u00cf\u00ce\3\2\2\2\u00d0 "+
		"\3\2\2\2\u00d1\u00d2\7v\2\2\u00d2\u00d3\7t\2\2\u00d3\u00d4\7w\2\2\u00d4"+
		"\u00d5\7g\2\2\u00d5\"\3\2\2\2\u00d6\u00d7\7h\2\2\u00d7\u00d8\7c\2\2\u00d8"+
		"\u00d9\7n\2\2\u00d9\u00da\7u\2\2\u00da\u00db\7g\2\2\u00db$\3\2\2\2\u00dc"+
		"\u00de\t\4\2\2\u00dd\u00dc\3\2\2\2\u00de\u00df\3\2\2\2\u00df\u00dd\3\2"+
		"\2\2\u00df\u00e0\3\2\2\2\u00e0\u00e1\3\2\2\2\u00e1\u00e2\b\23\2\2\u00e2"+
		"&\3\2\2\2\u00e3\u00e4\7]\2\2\u00e4(\3\2\2\2\u00e5\u00e6\7_\2\2\u00e6*"+
		"\3\2\2\2\u00e7\u00e8\7?\2\2\u00e8,\3\2\2\2\u00e9\u00ea\7=\2\2\u00ea.\3"+
		"\2\2\2\u00eb\u00ec\7/\2\2\u00ec\60\3\2\2\2\u00ed\u00ee\7\61\2\2\u00ee"+
		"\u00ef\7\61\2\2\u00ef\u00f3\3\2\2\2\u00f0\u00f2\13\2\2\2\u00f1\u00f0\3"+
		"\2\2\2\u00f2\u00f5\3\2\2\2\u00f3\u00f4\3\2\2\2\u00f3\u00f1\3\2\2\2\u00f4"+
		"\u00f7\3\2\2\2\u00f5\u00f3\3\2\2\2\u00f6\u00f8\7\17\2\2\u00f7\u00f6\3"+
		"\2\2\2\u00f7\u00f8\3\2\2\2\u00f8\u00f9\3\2\2\2\u00f9\u00fa\7\f\2\2\u00fa"+
		"\u00fb\3\2\2\2\u00fb\u00fc\b\31\2\2\u00fc\62\3\2\2\2\u00fd\u00fe\7\61"+
		"\2\2\u00fe\u00ff\7,\2\2\u00ff\u0103\3\2\2\2\u0100\u0102\13\2\2\2\u0101"+
		"\u0100\3\2\2\2\u0102\u0105\3\2\2\2\u0103\u0104\3\2\2\2\u0103\u0101\3\2"+
		"\2\2\u0104\u0106\3\2\2\2\u0105\u0103\3\2\2\2\u0106\u0107\7,\2\2\u0107"+
		"\u0108\7\61\2\2\u0108\u0109\3\2\2\2\u0109\u010a\b\32\2\2\u010a\64\3\2"+
		"\2\2\u010b\u010c\7^\2\2\u010c\u0121\t\5\2\2\u010d\u0112\7^\2\2\u010e\u0110"+
		"\t\6\2\2\u010f\u010e\3\2\2\2\u010f\u0110\3\2\2\2\u0110\u0111\3\2\2\2\u0111"+
		"\u0113\t\7\2\2\u0112\u010f\3\2\2\2\u0112\u0113\3\2\2\2\u0113\u0114\3\2"+
		"\2\2\u0114\u0121\t\7\2\2\u0115\u0117\7^\2\2\u0116\u0118\7w\2\2\u0117\u0116"+
		"\3\2\2\2\u0118\u0119\3\2\2\2\u0119\u0117\3\2\2\2\u0119\u011a\3\2\2\2\u011a"+
		"\u011b\3\2\2\2\u011b\u011c\59\35\2\u011c\u011d\59\35\2\u011d\u011e\59"+
		"\35\2\u011e\u011f\59\35\2\u011f\u0121\3\2\2\2\u0120\u010b\3\2\2\2\u0120"+
		"\u010d\3\2\2\2\u0120\u0115\3\2\2\2\u0121\66\3\2\2\2\u0122\u012b\59\35"+
		"\2\u0123\u0126\59\35\2\u0124\u0126\7a\2\2\u0125\u0123\3\2\2\2\u0125\u0124"+
		"\3\2\2\2\u0126\u0129\3\2\2\2\u0127\u0125\3\2\2\2\u0127\u0128\3\2\2\2\u0128"+
		"\u012a\3\2\2\2\u0129\u0127\3\2\2\2\u012a\u012c\59\35\2\u012b\u0127\3\2"+
		"\2\2\u012b\u012c\3\2\2\2\u012c8\3\2\2\2\u012d\u012e\t\b\2\2\u012e:\3\2"+
		"\2\2\23\2\u00be\u00c0\u00c6\u00cb\u00cf\u00df\u00f3\u00f7\u0103\u010f"+
		"\u0112\u0119\u0120\u0125\u0127\u012b\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}