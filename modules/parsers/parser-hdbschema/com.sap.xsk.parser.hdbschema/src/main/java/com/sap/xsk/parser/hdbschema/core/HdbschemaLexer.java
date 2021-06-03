// Generated from com/sap/xsk/parser/hdbschema/core/Hdbschema.g4 by ANTLR 4.3
package com.sap.xsk.parser.hdbschema.core;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class HdbschemaLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, STRING=2, EQ=3, SEMICOLON=4, COMMA=5, WS=6, ESC=7;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"'\\u0000'", "'\\u0001'", "'\\u0002'", "'\\u0003'", "'\\u0004'", "'\\u0005'", 
		"'\\u0006'", "'\\u0007'"
	};
	public static final String[] ruleNames = {
		"T__0", "STRING", "EQ", "SEMICOLON", "COMMA", "WS", "ESC"
	};


	public HdbschemaLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Hdbschema.g4"; }

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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\t\65\b\1\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\3\3\3\3\3\7\3\34\n\3\f\3\16\3\37\13\3\3\3\3\3\3\4\3\4\3\5\3\5"+
		"\3\6\3\6\3\7\6\7*\n\7\r\7\16\7+\3\7\3\7\3\b\3\b\3\b\3\b\5\b\64\n\b\3\35"+
		"\2\t\3\3\5\4\7\5\t\6\13\7\r\b\17\t\3\2\3\5\2\13\f\16\17\"\"8\2\3\3\2\2"+
		"\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3"+
		"\2\2\2\3\21\3\2\2\2\5\30\3\2\2\2\7\"\3\2\2\2\t$\3\2\2\2\13&\3\2\2\2\r"+
		")\3\2\2\2\17\63\3\2\2\2\21\22\7u\2\2\22\23\7e\2\2\23\24\7j\2\2\24\25\7"+
		"g\2\2\25\26\7o\2\2\26\27\7c\2\2\27\4\3\2\2\2\30\35\7$\2\2\31\34\5\17\b"+
		"\2\32\34\13\2\2\2\33\31\3\2\2\2\33\32\3\2\2\2\34\37\3\2\2\2\35\36\3\2"+
		"\2\2\35\33\3\2\2\2\36 \3\2\2\2\37\35\3\2\2\2 !\7$\2\2!\6\3\2\2\2\"#\7"+
		"?\2\2#\b\3\2\2\2$%\7=\2\2%\n\3\2\2\2&\'\7.\2\2\'\f\3\2\2\2(*\t\2\2\2)"+
		"(\3\2\2\2*+\3\2\2\2+)\3\2\2\2+,\3\2\2\2,-\3\2\2\2-.\b\7\2\2.\16\3\2\2"+
		"\2/\60\7^\2\2\60\64\7$\2\2\61\62\7^\2\2\62\64\7^\2\2\63/\3\2\2\2\63\61"+
		"\3\2\2\2\64\20\3\2\2\2\7\2\33\35+\63\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}