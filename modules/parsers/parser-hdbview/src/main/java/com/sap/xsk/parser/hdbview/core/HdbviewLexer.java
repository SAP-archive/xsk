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
	static { RuntimeMetaData.checkVersion("4.10.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		BOOLEAN=10, STRING=11, EQ=12, SEMICOLON=13, WS=14, ESC=15, LINE_COMMENT=16, 
		COMMENT=17;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"BOOLEAN", "STRING", "EQ", "SEMICOLON", "WS", "ESC", "LINE_COMMENT", 
			"COMMENT"
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


	public HdbviewLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Hdbview.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\u0011\u00ad\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002"+
		"\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002"+
		"\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002"+
		"\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002"+
		"\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e"+
		"\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004"+
		"\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0003\ts\b\t\u0001\n\u0001\n\u0001"+
		"\n\u0005\nx\b\n\n\n\f\n{\t\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001"+
		"\f\u0001\f\u0001\r\u0004\r\u0084\b\r\u000b\r\f\r\u0085\u0001\r\u0001\r"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0003\u000e\u008e\b\u000e"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0005\u000f\u0094\b\u000f"+
		"\n\u000f\f\u000f\u0097\t\u000f\u0001\u000f\u0003\u000f\u009a\b\u000f\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0001"+
		"\u0010\u0001\u0010\u0005\u0010\u00a4\b\u0010\n\u0010\f\u0010\u00a7\t\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0003y\u0095"+
		"\u00a5\u0000\u0011\u0001\u0001\u0003\u0002\u0005\u0003\u0007\u0004\t\u0005"+
		"\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\n\u0015\u000b\u0017\f\u0019"+
		"\r\u001b\u000e\u001d\u000f\u001f\u0010!\u0011\u0001\u0000\u0001\u0003"+
		"\u0000\t\n\f\r  \u00b4\u0000\u0001\u0001\u0000\u0000\u0000\u0000\u0003"+
		"\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000\u0000\u0000\u0000\u0007"+
		"\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001"+
		"\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000"+
		"\u0000\u0000\u0000\u0011\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000"+
		"\u0000\u0000\u0000\u0015\u0001\u0000\u0000\u0000\u0000\u0017\u0001\u0000"+
		"\u0000\u0000\u0000\u0019\u0001\u0000\u0000\u0000\u0000\u001b\u0001\u0000"+
		"\u0000\u0000\u0000\u001d\u0001\u0000\u0000\u0000\u0000\u001f\u0001\u0000"+
		"\u0000\u0000\u0000!\u0001\u0000\u0000\u0000\u0001#\u0001\u0000\u0000\u0000"+
		"\u0003*\u0001\u0000\u0000\u0000\u00051\u0001\u0000\u0000\u0000\u00077"+
		"\u0001\u0000\u0000\u0000\tB\u0001\u0000\u0000\u0000\u000bD\u0001\u0000"+
		"\u0000\u0000\rF\u0001\u0000\u0000\u0000\u000fH\u0001\u0000\u0000\u0000"+
		"\u0011Y\u0001\u0000\u0000\u0000\u0013r\u0001\u0000\u0000\u0000\u0015t"+
		"\u0001\u0000\u0000\u0000\u0017~\u0001\u0000\u0000\u0000\u0019\u0080\u0001"+
		"\u0000\u0000\u0000\u001b\u0083\u0001\u0000\u0000\u0000\u001d\u008d\u0001"+
		"\u0000\u0000\u0000\u001f\u008f\u0001\u0000\u0000\u0000!\u009f\u0001\u0000"+
		"\u0000\u0000#$\u0005s\u0000\u0000$%\u0005c\u0000\u0000%&\u0005h\u0000"+
		"\u0000&\'\u0005e\u0000\u0000\'(\u0005m\u0000\u0000()\u0005a\u0000\u0000"+
		")\u0002\u0001\u0000\u0000\u0000*+\u0005p\u0000\u0000+,\u0005u\u0000\u0000"+
		",-\u0005b\u0000\u0000-.\u0005l\u0000\u0000./\u0005i\u0000\u0000/0\u0005"+
		"c\u0000\u00000\u0004\u0001\u0000\u0000\u000012\u0005q\u0000\u000023\u0005"+
		"u\u0000\u000034\u0005e\u0000\u000045\u0005r\u0000\u000056\u0005y\u0000"+
		"\u00006\u0006\u0001\u0000\u0000\u000078\u0005d\u0000\u000089\u0005e\u0000"+
		"\u00009:\u0005p\u0000\u0000:;\u0005e\u0000\u0000;<\u0005n\u0000\u0000"+
		"<=\u0005d\u0000\u0000=>\u0005s\u0000\u0000>?\u0005_\u0000\u0000?@\u0005"+
		"o\u0000\u0000@A\u0005n\u0000\u0000A\b\u0001\u0000\u0000\u0000BC\u0005"+
		"[\u0000\u0000C\n\u0001\u0000\u0000\u0000DE\u0005,\u0000\u0000E\f\u0001"+
		"\u0000\u0000\u0000FG\u0005]\u0000\u0000G\u000e\u0001\u0000\u0000\u0000"+
		"HI\u0005d\u0000\u0000IJ\u0005e\u0000\u0000JK\u0005p\u0000\u0000KL\u0005"+
		"e\u0000\u0000LM\u0005n\u0000\u0000MN\u0005d\u0000\u0000NO\u0005s\u0000"+
		"\u0000OP\u0005_\u0000\u0000PQ\u0005o\u0000\u0000QR\u0005n\u0000\u0000"+
		"RS\u0005_\u0000\u0000ST\u0005t\u0000\u0000TU\u0005a\u0000\u0000UV\u0005"+
		"b\u0000\u0000VW\u0005l\u0000\u0000WX\u0005e\u0000\u0000X\u0010\u0001\u0000"+
		"\u0000\u0000YZ\u0005d\u0000\u0000Z[\u0005e\u0000\u0000[\\\u0005p\u0000"+
		"\u0000\\]\u0005e\u0000\u0000]^\u0005n\u0000\u0000^_\u0005d\u0000\u0000"+
		"_`\u0005s\u0000\u0000`a\u0005_\u0000\u0000ab\u0005o\u0000\u0000bc\u0005"+
		"n\u0000\u0000cd\u0005_\u0000\u0000de\u0005v\u0000\u0000ef\u0005i\u0000"+
		"\u0000fg\u0005e\u0000\u0000gh\u0005w\u0000\u0000h\u0012\u0001\u0000\u0000"+
		"\u0000ij\u0005t\u0000\u0000jk\u0005r\u0000\u0000kl\u0005u\u0000\u0000"+
		"ls\u0005e\u0000\u0000mn\u0005f\u0000\u0000no\u0005a\u0000\u0000op\u0005"+
		"l\u0000\u0000pq\u0005s\u0000\u0000qs\u0005e\u0000\u0000ri\u0001\u0000"+
		"\u0000\u0000rm\u0001\u0000\u0000\u0000s\u0014\u0001\u0000\u0000\u0000"+
		"ty\u0005\"\u0000\u0000ux\u0003\u001d\u000e\u0000vx\t\u0000\u0000\u0000"+
		"wu\u0001\u0000\u0000\u0000wv\u0001\u0000\u0000\u0000x{\u0001\u0000\u0000"+
		"\u0000yz\u0001\u0000\u0000\u0000yw\u0001\u0000\u0000\u0000z|\u0001\u0000"+
		"\u0000\u0000{y\u0001\u0000\u0000\u0000|}\u0005\"\u0000\u0000}\u0016\u0001"+
		"\u0000\u0000\u0000~\u007f\u0005=\u0000\u0000\u007f\u0018\u0001\u0000\u0000"+
		"\u0000\u0080\u0081\u0005;\u0000\u0000\u0081\u001a\u0001\u0000\u0000\u0000"+
		"\u0082\u0084\u0007\u0000\u0000\u0000\u0083\u0082\u0001\u0000\u0000\u0000"+
		"\u0084\u0085\u0001\u0000\u0000\u0000\u0085\u0083\u0001\u0000\u0000\u0000"+
		"\u0085\u0086\u0001\u0000\u0000\u0000\u0086\u0087\u0001\u0000\u0000\u0000"+
		"\u0087\u0088\u0006\r\u0000\u0000\u0088\u001c\u0001\u0000\u0000\u0000\u0089"+
		"\u008a\u0005\\\u0000\u0000\u008a\u008e\u0005\"\u0000\u0000\u008b\u008c"+
		"\u0005\\\u0000\u0000\u008c\u008e\u0005\\\u0000\u0000\u008d\u0089\u0001"+
		"\u0000\u0000\u0000\u008d\u008b\u0001\u0000\u0000\u0000\u008e\u001e\u0001"+
		"\u0000\u0000\u0000\u008f\u0090\u0005/\u0000\u0000\u0090\u0091\u0005/\u0000"+
		"\u0000\u0091\u0095\u0001\u0000\u0000\u0000\u0092\u0094\t\u0000\u0000\u0000"+
		"\u0093\u0092\u0001\u0000\u0000\u0000\u0094\u0097\u0001\u0000\u0000\u0000"+
		"\u0095\u0096\u0001\u0000\u0000\u0000\u0095\u0093\u0001\u0000\u0000\u0000"+
		"\u0096\u0099\u0001\u0000\u0000\u0000\u0097\u0095\u0001\u0000\u0000\u0000"+
		"\u0098\u009a\u0005\r\u0000\u0000\u0099\u0098\u0001\u0000\u0000\u0000\u0099"+
		"\u009a\u0001\u0000\u0000\u0000\u009a\u009b\u0001\u0000\u0000\u0000\u009b"+
		"\u009c\u0005\n\u0000\u0000\u009c\u009d\u0001\u0000\u0000\u0000\u009d\u009e"+
		"\u0006\u000f\u0000\u0000\u009e \u0001\u0000\u0000\u0000\u009f\u00a0\u0005"+
		"/\u0000\u0000\u00a0\u00a1\u0005*\u0000\u0000\u00a1\u00a5\u0001\u0000\u0000"+
		"\u0000\u00a2\u00a4\t\u0000\u0000\u0000\u00a3\u00a2\u0001\u0000\u0000\u0000"+
		"\u00a4\u00a7\u0001\u0000\u0000\u0000\u00a5\u00a6\u0001\u0000\u0000\u0000"+
		"\u00a5\u00a3\u0001\u0000\u0000\u0000\u00a6\u00a8\u0001\u0000\u0000\u0000"+
		"\u00a7\u00a5\u0001\u0000\u0000\u0000\u00a8\u00a9\u0005*\u0000\u0000\u00a9"+
		"\u00aa\u0005/\u0000\u0000\u00aa\u00ab\u0001\u0000\u0000\u0000\u00ab\u00ac"+
		"\u0006\u0010\u0000\u0000\u00ac\"\u0001\u0000\u0000\u0000\t\u0000rwy\u0085"+
		"\u008d\u0095\u0099\u00a5\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}