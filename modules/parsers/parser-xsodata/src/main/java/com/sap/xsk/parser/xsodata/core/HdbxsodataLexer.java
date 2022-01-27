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
// Generated from com/sap/xsk/parser/xsodata/core/Hdbxsodata.g4 by ANTLR 4.3
package com.sap.xsk.parser.xsodata.core;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class HdbxsodataLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__66=1, T__65=2, T__64=3, T__63=4, T__62=5, T__61=6, T__60=7, T__59=8, 
		T__58=9, T__57=10, T__56=11, T__55=12, T__54=13, T__53=14, T__52=15, T__51=16, 
		T__50=17, T__49=18, T__48=19, T__47=20, T__46=21, T__45=22, T__44=23, 
		T__43=24, T__42=25, T__41=26, T__40=27, T__39=28, T__38=29, T__37=30, 
		T__36=31, T__35=32, T__34=33, T__33=34, T__32=35, T__31=36, T__30=37, 
		T__29=38, T__28=39, T__27=40, T__26=41, T__25=42, T__24=43, T__23=44, 
		T__22=45, T__21=46, T__20=47, T__19=48, T__18=49, T__17=50, T__16=51, 
		T__15=52, T__14=53, T__13=54, T__12=55, T__11=56, T__10=57, T__9=58, T__8=59, 
		T__7=60, T__6=61, T__5=62, T__4=63, T__3=64, T__2=65, T__1=66, T__0=67, 
		SEMICOLON=68, EQ=69, QUATED_STRING=70, COMMA=71, COLON=72, ESC=73, WS=74, 
		LINE_COMMENT=75, COMMENT=76, INT=77;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"'\\u0000'", "'\\u0001'", "'\\u0002'", "'\\u0003'", "'\\u0004'", "'\\u0005'", 
		"'\\u0006'", "'\\u0007'", "'\b'", "'\t'", "'\n'", "'\\u000B'", "'\f'", 
		"'\r'", "'\\u000E'", "'\\u000F'", "'\\u0010'", "'\\u0011'", "'\\u0012'", 
		"'\\u0013'", "'\\u0014'", "'\\u0015'", "'\\u0016'", "'\\u0017'", "'\\u0018'", 
		"'\\u0019'", "'\\u001A'", "'\\u001B'", "'\\u001C'", "'\\u001D'", "'\\u001E'", 
		"'\\u001F'", "' '", "'!'", "'\"'", "'#'", "'$'", "'%'", "'&'", "'''", 
		"'('", "')'", "'*'", "'+'", "','", "'-'", "'.'", "'/'", "'0'", "'1'", 
		"'2'", "'3'", "'4'", "'5'", "'6'", "'7'", "'8'", "'9'", "':'", "';'", 
		"'<'", "'='", "'>'", "'?'", "'@'", "'A'", "'B'", "'C'", "'D'", "'E'", 
		"'F'", "'G'", "'H'", "'I'", "'J'", "'K'", "'L'", "'M'"
	};
	public static final String[] ruleNames = {
		"T__66", "T__65", "T__64", "T__63", "T__62", "T__61", "T__60", "T__59", 
		"T__58", "T__57", "T__56", "T__55", "T__54", "T__53", "T__52", "T__51", 
		"T__50", "T__49", "T__48", "T__47", "T__46", "T__45", "T__44", "T__43", 
		"T__42", "T__41", "T__40", "T__39", "T__38", "T__37", "T__36", "T__35", 
		"T__34", "T__33", "T__32", "T__31", "T__30", "T__29", "T__28", "T__27", 
		"T__26", "T__25", "T__24", "T__23", "T__22", "T__21", "T__20", "T__19", 
		"T__18", "T__17", "T__16", "T__15", "T__14", "T__13", "T__12", "T__11", 
		"T__10", "T__9", "T__8", "T__7", "T__6", "T__5", "T__4", "T__3", "T__2", 
		"T__1", "T__0", "SEMICOLON", "EQ", "QUATED_STRING", "COMMA", "COLON", 
		"ESC", "WS", "LINE_COMMENT", "COMMENT", "INT"
	};


	public HdbxsodataLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Hdbxsodata.g4"; }

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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2O\u02ce\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4I"+
		"\tI\4J\tJ\4K\tK\4L\tL\4M\tM\4N\tN\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3"+
		"\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b"+
		"\3\b\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13"+
		"\3\13\3\13\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\25"+
		"\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\30\3\30"+
		"\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31"+
		"\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32"+
		"\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33"+
		"\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\36\3\36"+
		"\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3 \3 \3 \3!\3!\3!\3"+
		"!\3!\3!\3!\3!\3!\3!\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3#\3#\3#\3#\3#\3#\3#\3"+
		"#\3#\3#\3#\3#\3$\3$\3$\3$\3$\3$\3%\3%\3%\3%\3%\3%\3%\3%\3%\3&\3&\3&\3"+
		"&\3&\3&\3&\3&\3&\3&\3&\3\'\3\'\3\'\3(\3(\3)\3)\3)\3)\3)\3)\3*\3*\3*\3"+
		"*\3*\3*\3*\3*\3*\3*\3+\3+\3+\3+\3+\3,\3,\3,\3,\3,\3,\3,\3,\3,\3,\3,\3"+
		"-\3-\3-\3-\3-\3-\3-\3.\3.\3.\3.\3.\3.\3.\3.\3.\3.\3.\3.\3/\3/\3/\3/\3"+
		"/\3/\3/\3/\3/\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\61\3\61\3\61\3\61\3"+
		"\61\3\61\3\61\3\62\3\62\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3"+
		"\63\3\64\3\64\3\64\3\64\3\64\3\64\3\64\3\65\3\65\3\65\3\65\3\65\3\65\3"+
		"\65\3\65\3\65\3\66\3\66\3\66\3\66\3\67\3\67\3\67\3\67\38\38\38\38\38\3"+
		"8\38\38\39\39\39\39\39\39\39\39\39\39\39\39\3:\3:\3:\3:\3:\3:\3:\3:\3"+
		":\3:\3:\3:\3:\3:\3;\3;\3;\3;\3;\3;\3;\3;\3<\3<\3<\3<\3=\3=\3=\3=\3=\3"+
		"=\3=\3=\3=\3=\3=\3=\3=\3=\3=\3=\3=\3=\3=\3=\3=\3>\3>\3>\3>\3>\3?\3?\3"+
		"?\3?\3?\3?\3?\3?\3?\3?\3@\3@\3@\3@\3@\3@\3@\3A\3A\3A\3A\3A\3A\3A\3A\3"+
		"A\3A\3B\3B\3C\3C\3C\3C\3D\3D\3D\3D\3E\3E\3F\3F\3G\3G\3G\7G\u0294\nG\f"+
		"G\16G\u0297\13G\3G\3G\3H\3H\3I\3I\3J\3J\3J\3J\5J\u02a3\nJ\3K\6K\u02a6"+
		"\nK\rK\16K\u02a7\3K\3K\3L\3L\3L\3L\7L\u02b0\nL\fL\16L\u02b3\13L\3L\5L"+
		"\u02b6\nL\3L\3L\3L\3L\3M\3M\3M\3M\7M\u02c0\nM\fM\16M\u02c3\13M\3M\3M\3"+
		"M\3M\3M\3N\6N\u02cb\nN\rN\16N\u02cc\5\u0295\u02b1\u02c1\2O\3\3\5\4\7\5"+
		"\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23"+
		"%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G"+
		"%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61a\62c\63e\64g\65i\66k\67m8o9q:s;u<w=y>{"+
		"?}@\177A\u0081B\u0083C\u0085D\u0087E\u0089F\u008bG\u008dH\u008fI\u0091"+
		"J\u0093K\u0095L\u0097M\u0099N\u009bO\3\2\5\4\2}}\177\177\5\2\13\f\16\17"+
		"\"\"\3\2\62;\u02d5\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13"+
		"\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2"+
		"\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2"+
		"!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3"+
		"\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2"+
		"\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E"+
		"\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2"+
		"\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2"+
		"\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k"+
		"\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2\2q\3\2\2\2\2s\3\2\2\2\2u\3\2\2\2\2w\3\2"+
		"\2\2\2y\3\2\2\2\2{\3\2\2\2\2}\3\2\2\2\2\177\3\2\2\2\2\u0081\3\2\2\2\2"+
		"\u0083\3\2\2\2\2\u0085\3\2\2\2\2\u0087\3\2\2\2\2\u0089\3\2\2\2\2\u008b"+
		"\3\2\2\2\2\u008d\3\2\2\2\2\u008f\3\2\2\2\2\u0091\3\2\2\2\2\u0093\3\2\2"+
		"\2\2\u0095\3\2\2\2\2\u0097\3\2\2\2\2\u0099\3\2\2\2\2\u009b\3\2\2\2\3\u009d"+
		"\3\2\2\2\5\u00a5\3\2\2\2\7\u00a7\3\2\2\2\t\u00af\3\2\2\2\13\u00b9\3\2"+
		"\2\2\r\u00c0\3\2\2\2\17\u00c7\3\2\2\2\21\u00ca\3\2\2\2\23\u00cf\3\2\2"+
		"\2\25\u00d7\3\2\2\2\27\u00dc\3\2\2\2\31\u00de\3\2\2\2\33\u00e5\3\2\2\2"+
		"\35\u00ec\3\2\2\2\37\u00f4\3\2\2\2!\u00fa\3\2\2\2#\u0106\3\2\2\2%\u010a"+
		"\3\2\2\2\'\u0114\3\2\2\2)\u0121\3\2\2\2+\u0125\3\2\2\2-\u0129\3\2\2\2"+
		"/\u012e\3\2\2\2\61\u0139\3\2\2\2\63\u014a\3\2\2\2\65\u0155\3\2\2\2\67"+
		"\u015c\3\2\2\29\u0162\3\2\2\2;\u0166\3\2\2\2=\u016f\3\2\2\2?\u0172\3\2"+
		"\2\2A\u0175\3\2\2\2C\u017f\3\2\2\2E\u0186\3\2\2\2G\u0192\3\2\2\2I\u0198"+
		"\3\2\2\2K\u01a1\3\2\2\2M\u01ac\3\2\2\2O\u01af\3\2\2\2Q\u01b1\3\2\2\2S"+
		"\u01b7\3\2\2\2U\u01c1\3\2\2\2W\u01c6\3\2\2\2Y\u01d1\3\2\2\2[\u01d8\3\2"+
		"\2\2]\u01e4\3\2\2\2_\u01ed\3\2\2\2a\u01f4\3\2\2\2c\u01fb\3\2\2\2e\u01fd"+
		"\3\2\2\2g\u0207\3\2\2\2i\u020e\3\2\2\2k\u0217\3\2\2\2m\u021b\3\2\2\2o"+
		"\u021f\3\2\2\2q\u0227\3\2\2\2s\u0233\3\2\2\2u\u0241\3\2\2\2w\u0249\3\2"+
		"\2\2y\u024d\3\2\2\2{\u0262\3\2\2\2}\u0267\3\2\2\2\177\u0271\3\2\2\2\u0081"+
		"\u0278\3\2\2\2\u0083\u0282\3\2\2\2\u0085\u0284\3\2\2\2\u0087\u0288\3\2"+
		"\2\2\u0089\u028c\3\2\2\2\u008b\u028e\3\2\2\2\u008d\u0290\3\2\2\2\u008f"+
		"\u029a\3\2\2\2\u0091\u029c\3\2\2\2\u0093\u02a2\3\2\2\2\u0095\u02a5\3\2"+
		"\2\2\u0097\u02ab\3\2\2\2\u0099\u02bb\3\2\2\2\u009b\u02ca\3\2\2\2\u009d"+
		"\u009e\7u\2\2\u009e\u009f\7v\2\2\u009f\u00a0\7q\2\2\u00a0\u00a1\7t\2\2"+
		"\u00a1\u00a2\7c\2\2\u00a2\u00a3\7i\2\2\u00a3\u00a4\7g\2\2\u00a4\4\3\2"+
		"\2\2\u00a5\u00a6\7}\2\2\u00a6\6\3\2\2\2\u00a7\u00a8\7t\2\2\u00a8\u00a9"+
		"\7g\2\2\u00a9\u00aa\7u\2\2\u00aa\u00ab\7w\2\2\u00ab\u00ac\7n\2\2\u00ac"+
		"\u00ad\7v\2\2\u00ad\u00ae\7u\2\2\u00ae\b\3\2\2\2\u00af\u00b0\7p\2\2\u00b0"+
		"\u00b1\7c\2\2\u00b1\u00b2\7x\2\2\u00b2\u00b3\7k\2\2\u00b3\u00b4\7i\2\2"+
		"\u00b4\u00b5\7c\2\2\u00b5\u00b6\7v\2\2\u00b6\u00b7\7g\2\2\u00b7\u00b8"+
		"\7u\2\2\u00b8\n\3\2\2\2\u00b9\u00ba\7g\2\2\u00ba\u00bb\7p\2\2\u00bb\u00bc"+
		"\7v\2\2\u00bc\u00bd\7k\2\2\u00bd\u00be\7v\2\2\u00be\u00bf\7{\2\2\u00bf"+
		"\f\3\2\2\2\u00c0\u00c1\7f\2\2\u00c1\u00c2\7g\2\2\u00c2\u00c3\7n\2\2\u00c3"+
		"\u00c4\7g\2\2\u00c4\u00c5\7v\2\2\u00c5\u00c6\7g\2\2\u00c6\16\3\2\2\2\u00c7"+
		"\u00c8\7p\2\2\u00c8\u00c9\7q\2\2\u00c9\20\3\2\2\2\u00ca\u00cb\7q\2\2\u00cb"+
		"\u00cc\7x\2\2\u00cc\u00cd\7g\2\2\u00cd\u00ce\7t\2\2\u00ce\22\3\2\2\2\u00cf"+
		"\u00d0\7e\2\2\u00d0\u00d1\7q\2\2\u00d1\u00d2\7p\2\2\u00d2\u00d3\7v\2\2"+
		"\u00d3\u00d4\7g\2\2\u00d4\u00d5\7p\2\2\u00d5\u00d6\7v\2\2\u00d6\24\3\2"+
		"\2\2\u00d7\u00d8\7p\2\2\u00d8\u00d9\7w\2\2\u00d9\u00da\7n\2\2\u00da\u00db"+
		"\7n\2\2\u00db\26\3\2\2\2\u00dc\u00dd\7*\2\2\u00dd\30\3\2\2\2\u00de\u00df"+
		"\7g\2\2\u00df\u00e0\7x\2\2\u00e0\u00e1\7g\2\2\u00e1\u00e2\7p\2\2\u00e2"+
		"\u00e3\7v\2\2\u00e3\u00e4\7u\2\2\u00e4\32\3\2\2\2\u00e5\u00e6\7c\2\2\u00e6"+
		"\u00e7\7n\2\2\u00e7\u00e8\7y\2\2\u00e8\u00e9\7c\2\2\u00e9\u00ea\7{\2\2"+
		"\u00ea\u00eb\7u\2\2\u00eb\34\3\2\2\2\u00ec\u00ed\7y\2\2\u00ed\u00ee\7"+
		"k\2\2\u00ee\u00ef\7v\2\2\u00ef\u00f0\7j\2\2\u00f0\u00f1\7q\2\2\u00f1\u00f2"+
		"\7w\2\2\u00f2\u00f3\7v\2\2\u00f3\36\3\2\2\2\u00f4\u00f5\7c\2\2\u00f5\u00f6"+
		"\7h\2\2\u00f6\u00f7\7v\2\2\u00f7\u00f8\7g\2\2\u00f8\u00f9\7t\2\2\u00f9"+
		" \3\2\2\2\u00fa\u00fb\7c\2\2\u00fb\u00fc\7p\2\2\u00fc\u00fd\7p\2\2\u00fd"+
		"\u00fe\7q\2\2\u00fe\u00ff\7v\2\2\u00ff\u0100\7c\2\2\u0100\u0101\7v\2\2"+
		"\u0101\u0102\7k\2\2\u0102\u0103\7q\2\2\u0103\u0104\7p\2\2\u0104\u0105"+
		"\7u\2\2\u0105\"\3\2\2\2\u0106\u0107\7x\2\2\u0107\u0108\7k\2\2\u0108\u0109"+
		"\7c\2\2\u0109$\3\2\2\2\u010a\u010b\7r\2\2\u010b\u010c\7t\2\2\u010c\u010d"+
		"\7g\2\2\u010d\u010e\7e\2\2\u010e\u010f\7q\2\2\u010f\u0110\7o\2\2\u0110"+
		"\u0111\7o\2\2\u0111\u0112\7k\2\2\u0112\u0113\7v\2\2\u0113&\3\2\2\2\u0114"+
		"\u0115\7o\2\2\u0115\u0116\7w\2\2\u0116\u0117\7n\2\2\u0117\u0118\7v\2\2"+
		"\u0118\u0119\7k\2\2\u0119\u011a\7r\2\2\u011a\u011b\7n\2\2\u011b\u011c"+
		"\7k\2\2\u011c\u011d\7e\2\2\u011d\u011e\7k\2\2\u011e\u011f\7v\2\2\u011f"+
		"\u0120\7{\2\2\u0120(\3\2\2\2\u0121\u0122\7U\2\2\u0122\u0123\7W\2\2\u0123"+
		"\u0124\7O\2\2\u0124*\3\2\2\2\u0125\u0126\7$\2\2\u0126\u0127\7\63\2\2\u0127"+
		"\u0128\7$\2\2\u0128,\3\2\2\2\u0129\u012a\7m\2\2\u012a\u012b\7g\2\2\u012b"+
		"\u012c\7{\2\2\u012c\u012d\7u\2\2\u012d.\3\2\2\2\u012e\u012f\7r\2\2\u012f"+
		"\u0130\7q\2\2\u0130\u0131\7u\2\2\u0131\u0132\7v\2\2\u0132\u0133\7e\2\2"+
		"\u0133\u0134\7q\2\2\u0134\u0135\7o\2\2\u0135\u0136\7o\2\2\u0136\u0137"+
		"\7k\2\2\u0137\u0138\7v\2\2\u0138\60\3\2\2\2\u0139\u013a\7e\2\2\u013a\u013b"+
		"\7q\2\2\u013b\u013c\7p\2\2\u013c\u013d\7e\2\2\u013d\u013e\7w\2\2\u013e"+
		"\u013f\7t\2\2\u013f\u0140\7t\2\2\u0140\u0141\7g\2\2\u0141\u0142\7p\2\2"+
		"\u0142\u0143\7e\2\2\u0143\u0144\7{\2\2\u0144\u0145\7v\2\2\u0145\u0146"+
		"\7q\2\2\u0146\u0147\7m\2\2\u0147\u0148\7g\2\2\u0148\u0149\7p\2\2\u0149"+
		"\62\3\2\2\2\u014a\u014b\7r\2\2\u014b\u014c\7c\2\2\u014c\u014d\7t\2\2\u014d"+
		"\u014e\7c\2\2\u014e\u014f\7o\2\2\u014f\u0150\7g\2\2\u0150\u0151\7v\2\2"+
		"\u0151\u0152\7g\2\2\u0152\u0153\7t\2\2\u0153\u0154\7u\2\2\u0154\64\3\2"+
		"\2\2\u0155\u0156\7n\2\2\u0156\u0157\7k\2\2\u0157\u0158\7o\2\2\u0158\u0159"+
		"\7k\2\2\u0159\u015a\7v\2\2\u015a\u015b\7u\2\2\u015b\66\3\2\2\2\u015c\u015d"+
		"\7j\2\2\u015d\u015e\7k\2\2\u015e\u015f\7p\2\2\u015f\u0160\7v\2\2\u0160"+
		"\u0161\7u\2\2\u01618\3\2\2\2\u0162\u0163\7O\2\2\u0163\u0164\7C\2\2\u0164"+
		"\u0165\7Z\2\2\u0165:\3\2\2\2\u0166\u0167\7i\2\2\u0167\u0168\7g\2\2\u0168"+
		"\u0169\7p\2\2\u0169\u016a\7g\2\2\u016a\u016b\7t\2\2\u016b\u016c\7c\2\2"+
		"\u016c\u016d\7v\2\2\u016d\u016e\7g\2\2\u016e<\3\2\2\2\u016f\u0170\7c\2"+
		"\2\u0170\u0171\7u\2\2\u0171>\3\2\2\2\u0172\u0173\7q\2\2\u0173\u0174\7"+
		"h\2\2\u0174@\3\2\2\2\u0175\u0176\7h\2\2\u0176\u0177\7q\2\2\u0177\u0178"+
		"\7t\2\2\u0178\u0179\7d\2\2\u0179\u017a\7k\2\2\u017a\u017b\7f\2\2\u017b"+
		"\u017c\7f\2\2\u017c\u017d\7g\2\2\u017d\u017e\7p\2\2\u017eB\3\2\2\2\u017f"+
		"\u0180\7e\2\2\u0180\u0181\7t\2\2\u0181\u0182\7g\2\2\u0182\u0183\7c\2\2"+
		"\u0183\u0184\7v\2\2\u0184\u0185\7g\2\2\u0185D\3\2\2\2\u0186\u0187\7o\2"+
		"\2\u0187\u0188\7c\2\2\u0188\u0189\7z\2\2\u0189\u018a\7a\2\2\u018a\u018b"+
		"\7t\2\2\u018b\u018c\7g\2\2\u018c\u018d\7e\2\2\u018d\u018e\7q\2\2\u018e"+
		"\u018f\7t\2\2\u018f\u0190\7f\2\2\u0190\u0191\7u\2\2\u0191F\3\2\2\2\u0192"+
		"\u0193\7w\2\2\u0193\u0194\7u\2\2\u0194\u0195\7k\2\2\u0195\u0196\7p\2\2"+
		"\u0196\u0197\7i\2\2\u0197H\3\2\2\2\u0198\u0199\7r\2\2\u0199\u019a\7t\2"+
		"\2\u019a\u019b\7q\2\2\u019b\u019c\7r\2\2\u019c\u019d\7g\2\2\u019d\u019e"+
		"\7t\2\2\u019e\u019f\7v\2\2\u019f\u01a0\7{\2\2\u01a0J\3\2\2\2\u01a1\u01a2"+
		"\7c\2\2\u01a2\u01a3\7i\2\2\u01a3\u01a4\7i\2\2\u01a4\u01a5\7t\2\2\u01a5"+
		"\u01a6\7g\2\2\u01a6\u01a7\7i\2\2\u01a7\u01a8\7c\2\2\u01a8\u01a9\7v\2\2"+
		"\u01a9\u01aa\7g\2\2\u01aa\u01ab\7u\2\2\u01abL\3\2\2\2\u01ac\u01ad\7q\2"+
		"\2\u01ad\u01ae\7p\2\2\u01aeN\3\2\2\2\u01af\u01b0\7\177\2\2\u01b0P\3\2"+
		"\2\2\u01b1\u01b2\7n\2\2\u01b2\u01b3\7q\2\2\u01b3\u01b4\7e\2\2\u01b4\u01b5"+
		"\7c\2\2\u01b5\u01b6\7n\2\2\u01b6R\3\2\2\2\u01b7\u01b8\7f\2\2\u01b8\u01b9"+
		"\7g\2\2\u01b9\u01ba\7r\2\2\u01ba\u01bb\7g\2\2\u01bb\u01bc\7p\2\2\u01bc"+
		"\u01bd\7f\2\2\u01bd\u01be\7g\2\2\u01be\u01bf\7p\2\2\u01bf\u01c0\7v\2\2"+
		"\u01c0T\3\2\2\2\u01c1\u01c2\7h\2\2\u01c2\u01c3\7t\2\2\u01c3\u01c4\7q\2"+
		"\2\u01c4\u01c5\7o\2\2\u01c5V\3\2\2\2\u01c6\u01c7\7e\2\2\u01c7\u01c8\7"+
		"q\2\2\u01c8\u01c9\7p\2\2\u01c9\u01ca\7u\2\2\u01ca\u01cb\7v\2\2\u01cb\u01cc"+
		"\7t\2\2\u01cc\u01cd\7c\2\2\u01cd\u01ce\7k\2\2\u01ce\u01cf\7p\2\2\u01cf"+
		"\u01d0\7v\2\2\u01d0X\3\2\2\2\u01d1\u01d2\7$\2\2\u01d2\u01d3\7\63\2\2\u01d3"+
		"\u01d4\7\60\2\2\u01d4\u01d5\7\60\2\2\u01d5\u01d6\7,\2\2\u01d6\u01d7\7"+
		"$\2\2\u01d7Z\3\2\2\2\u01d8\u01d9\7c\2\2\u01d9\u01da\7u\2\2\u01da\u01db"+
		"\7u\2\2\u01db\u01dc\7q\2\2\u01dc\u01dd\7e\2\2\u01dd\u01de\7k\2\2\u01de"+
		"\u01df\7c\2\2\u01df\u01e0\7v\2\2\u01e0\u01e1\7k\2\2\u01e1\u01e2\7q\2\2"+
		"\u01e2\u01e3\7p\2\2\u01e3\\\3\2\2\2\u01e4\u01e5\7o\2\2\u01e5\u01e6\7g"+
		"\2\2\u01e6\u01e7\7v\2\2\u01e7\u01e8\7c\2\2\u01e8\u01e9\7f\2\2\u01e9\u01ea"+
		"\7c\2\2\u01ea\u01eb\7v\2\2\u01eb\u01ec\7c\2\2\u01ec^\3\2\2\2\u01ed\u01ee"+
		"\7w\2\2\u01ee\u01ef\7r\2\2\u01ef\u01f0\7f\2\2\u01f0\u01f1\7c\2\2\u01f1"+
		"\u01f2\7v\2\2\u01f2\u01f3\7g\2\2\u01f3`\3\2\2\2\u01f4\u01f5\7g\2\2\u01f5"+
		"\u01f6\7p\2\2\u01f6\u01f7\7c\2\2\u01f7\u01f8\7d\2\2\u01f8\u01f9\7n\2\2"+
		"\u01f9\u01fa\7g\2\2\u01fab\3\2\2\2\u01fb\u01fc\7\60\2\2\u01fcd\3\2\2\2"+
		"\u01fd\u01fe\7Q\2\2\u01fe\u01ff\7F\2\2\u01ff\u0200\7c\2\2\u0200\u0201"+
		"\7v\2\2\u0201\u0202\7c\2\2\u0202\u0203\7\66\2\2\u0203\u0204\7U\2\2\u0204"+
		"\u0205\7C\2\2\u0205\u0206\7R\2\2\u0206f\3\2\2\2\u0207\u0208\7d\2\2\u0208"+
		"\u0209\7g\2\2\u0209\u020a\7h\2\2\u020a\u020b\7q\2\2\u020b\u020c\7t\2\2"+
		"\u020c\u020d\7g\2\2\u020dh\3\2\2\2\u020e\u020f\7u\2\2\u020f\u0210\7g\2"+
		"\2\u0210\u0211\7v\2\2\u0211\u0212\7v\2\2\u0212\u0213\7k\2\2\u0213\u0214"+
		"\7p\2\2\u0214\u0215\7i\2\2\u0215\u0216\7u\2\2\u0216j\3\2\2\2\u0217\u0218"+
		"\7C\2\2\u0218\u0219\7X\2\2\u0219\u021a\7I\2\2\u021al\3\2\2\2\u021b\u021c"+
		"\7m\2\2\u021c\u021d\7g\2\2\u021d\u021e\7{\2\2\u021en\3\2\2\2\u021f\u0220"+
		"\7u\2\2\u0220\u0221\7g\2\2\u0221\u0222\7t\2\2\u0222\u0223\7x\2\2\u0223"+
		"\u0224\7k\2\2\u0224\u0225\7e\2\2\u0225\u0226\7g\2\2\u0226p\3\2\2\2\u0227"+
		"\u0228\7t\2\2\u0228\u0229\7g\2\2\u0229\u022a\7h\2\2\u022a\u022b\7g\2\2"+
		"\u022b\u022c\7t\2\2\u022c\u022d\7g\2\2\u022d\u022e\7p\2\2\u022e\u022f"+
		"\7v\2\2\u022f\u0230\7k\2\2\u0230\u0231\7c\2\2\u0231\u0232\7n\2\2\u0232"+
		"r\3\2\2\2\u0233\u0234\7e\2\2\u0234\u0235\7c\2\2\u0235\u0236\7e\2\2\u0236"+
		"\u0237\7j\2\2\u0237\u0238\7g\2\2\u0238\u0239\7/\2\2\u0239\u023a\7e\2\2"+
		"\u023a\u023b\7q\2\2\u023b\u023c\7p\2\2\u023c\u023d\7v\2\2\u023d\u023e"+
		"\7t\2\2\u023e\u023f\7q\2\2\u023f\u0240\7n\2\2\u0240t\3\2\2\2\u0241\u0242"+
		"\7u\2\2\u0242\u0243\7w\2\2\u0243\u0244\7r\2\2\u0244\u0245\7r\2\2\u0245"+
		"\u0246\7q\2\2\u0246\u0247\7t\2\2\u0247\u0248\7v\2\2\u0248v\3\2\2\2\u0249"+
		"\u024a\7O\2\2\u024a\u024b\7K\2\2\u024b\u024c\7P\2\2\u024cx\3\2\2\2\u024d"+
		"\u024e\7o\2\2\u024e\u024f\7c\2\2\u024f\u0250\7z\2\2\u0250\u0251\7a\2\2"+
		"\u0251\u0252\7g\2\2\u0252\u0253\7z\2\2\u0253\u0254\7r\2\2\u0254\u0255"+
		"\7c\2\2\u0255\u0256\7p\2\2\u0256\u0257\7f\2\2\u0257\u0258\7g\2\2\u0258"+
		"\u0259\7f\2\2\u0259\u025a\7a\2\2\u025a\u025b\7t\2\2\u025b\u025c\7g\2\2"+
		"\u025c\u025d\7e\2\2\u025d\u025e\7q\2\2\u025e\u025f\7t\2\2\u025f\u0260"+
		"\7f\2\2\u0260\u0261\7u\2\2\u0261z\3\2\2\2\u0262\u0263\7y\2\2\u0263\u0264"+
		"\7k\2\2\u0264\u0265\7v\2\2\u0265\u0266\7j\2\2\u0266|\3\2\2\2\u0267\u0268"+
		"\7p\2\2\u0268\u0269\7c\2\2\u0269\u026a\7o\2\2\u026a\u026b\7g\2\2\u026b"+
		"\u026c\7u\2\2\u026c\u026d\7r\2\2\u026d\u026e\7c\2\2\u026e\u026f\7e\2\2"+
		"\u026f\u0270\7g\2\2\u0270~\3\2\2\2\u0271\u0272\7$\2\2\u0272\u0273\7\62"+
		"\2\2\u0273\u0274\7\60\2\2\u0274\u0275\7\60\2\2\u0275\u0276\7\63\2\2\u0276"+
		"\u0277\7$\2\2\u0277\u0080\3\2\2\2\u0278\u0279\7r\2\2\u0279\u027a\7t\2"+
		"\2\u027a\u027b\7k\2\2\u027b\u027c\7p\2\2\u027c\u027d\7e\2\2\u027d\u027e"+
		"\7k\2\2\u027e\u027f\7r\2\2\u027f\u0280\7c\2\2\u0280\u0281\7n\2\2\u0281"+
		"\u0082\3\2\2\2\u0282\u0283\7+\2\2\u0283\u0084\3\2\2\2\u0284\u0285\7c\2"+
		"\2\u0285\u0286\7p\2\2\u0286\u0287\7f\2\2\u0287\u0086\3\2\2\2\u0288\u0289"+
		"\7$\2\2\u0289\u028a\7,\2\2\u028a\u028b\7$\2\2\u028b\u0088\3\2\2\2\u028c"+
		"\u028d\7=\2\2\u028d\u008a\3\2\2\2\u028e\u028f\7?\2\2\u028f\u008c\3\2\2"+
		"\2\u0290\u0295\7$\2\2\u0291\u0294\5\u0093J\2\u0292\u0294\n\2\2\2\u0293"+
		"\u0291\3\2\2\2\u0293\u0292\3\2\2\2\u0294\u0297\3\2\2\2\u0295\u0296\3\2"+
		"\2\2\u0295\u0293\3\2\2\2\u0296\u0298\3\2\2\2\u0297\u0295\3\2\2\2\u0298"+
		"\u0299\7$\2\2\u0299\u008e\3\2\2\2\u029a\u029b\7.\2\2\u029b\u0090\3\2\2"+
		"\2\u029c\u029d\7<\2\2\u029d\u0092\3\2\2\2\u029e\u029f\7^\2\2\u029f\u02a3"+
		"\7$\2\2\u02a0\u02a1\7^\2\2\u02a1\u02a3\7^\2\2\u02a2\u029e\3\2\2\2\u02a2"+
		"\u02a0\3\2\2\2\u02a3\u0094\3\2\2\2\u02a4\u02a6\t\3\2\2\u02a5\u02a4\3\2"+
		"\2\2\u02a6\u02a7\3\2\2\2\u02a7\u02a5\3\2\2\2\u02a7\u02a8\3\2\2\2\u02a8"+
		"\u02a9\3\2\2\2\u02a9\u02aa\bK\2\2\u02aa\u0096\3\2\2\2\u02ab\u02ac\7\61"+
		"\2\2\u02ac\u02ad\7\61\2\2\u02ad\u02b1\3\2\2\2\u02ae\u02b0\13\2\2\2\u02af"+
		"\u02ae\3\2\2\2\u02b0\u02b3\3\2\2\2\u02b1\u02b2\3\2\2\2\u02b1\u02af\3\2"+
		"\2\2\u02b2\u02b5\3\2\2\2\u02b3\u02b1\3\2\2\2\u02b4\u02b6\7\17\2\2\u02b5"+
		"\u02b4\3\2\2\2\u02b5\u02b6\3\2\2\2\u02b6\u02b7\3\2\2\2\u02b7\u02b8\7\f"+
		"\2\2\u02b8\u02b9\3\2\2\2\u02b9\u02ba\bL\2\2\u02ba\u0098\3\2\2\2\u02bb"+
		"\u02bc\7\61\2\2\u02bc\u02bd\7,\2\2\u02bd\u02c1\3\2\2\2\u02be\u02c0\13"+
		"\2\2\2\u02bf\u02be\3\2\2\2\u02c0\u02c3\3\2\2\2\u02c1\u02c2\3\2\2\2\u02c1"+
		"\u02bf\3\2\2\2\u02c2\u02c4\3\2\2\2\u02c3\u02c1\3\2\2\2\u02c4\u02c5\7,"+
		"\2\2\u02c5\u02c6\7\61\2\2\u02c6\u02c7\3\2\2\2\u02c7\u02c8\bM\2\2\u02c8"+
		"\u009a\3\2\2\2\u02c9\u02cb\t\4\2\2\u02ca\u02c9\3\2\2\2\u02cb\u02cc\3\2"+
		"\2\2\u02cc\u02ca\3\2\2\2\u02cc\u02cd\3\2\2\2\u02cd\u009c\3\2\2\2\13\2"+
		"\u0293\u0295\u02a2\u02a7\u02b1\u02b5\u02c1\u02cc\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}