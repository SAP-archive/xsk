// Generated from com\sap\xsk\parser\hdbschema\core\Hdbschema.g4 by ANTLR 4.3
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
		T__0=1, STRING=2, EQ=3, SEMICOLON=4, COMMA=5, WS=6, ESC=7, LINE_COMMENT=8, 
		COMMENT=9;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"'\\u0000'", "'\\u0001'", "'\\u0002'", "'\\u0003'", "'\\u0004'", "'\\u0005'", 
		"'\\u0006'", "'\\u0007'", "'\b'", "'\t'"
	};
	public static final String[] ruleNames = {
		"T__0", "STRING", "EQ", "SEMICOLON", "COMMA", "WS", "ESC", "LINE_COMMENT", 
		"COMMENT"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\13\\\b\1\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\7\3%\n\3\f\3\16"+
		"\3(\13\3\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\6\7\63\n\7\r\7\16\7\64\3"+
		"\7\3\7\3\b\3\b\3\b\3\b\5\b=\n\b\3\t\3\t\3\t\3\t\7\tC\n\t\f\t\16\tF\13"+
		"\t\3\t\5\tI\n\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\7\nS\n\n\f\n\16\nV\13"+
		"\n\3\n\3\n\3\n\3\n\3\n\5&DT\2\13\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23"+
		"\13\3\2\3\5\2\13\f\16\17\"\"b\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t"+
		"\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2"+
		"\2\3\25\3\2\2\2\5!\3\2\2\2\7+\3\2\2\2\t-\3\2\2\2\13/\3\2\2\2\r\62\3\2"+
		"\2\2\17<\3\2\2\2\21>\3\2\2\2\23N\3\2\2\2\25\26\7u\2\2\26\27\7e\2\2\27"+
		"\30\7j\2\2\30\31\7g\2\2\31\32\7o\2\2\32\33\7c\2\2\33\34\7a\2\2\34\35\7"+
		"p\2\2\35\36\7c\2\2\36\37\7o\2\2\37 \7g\2\2 \4\3\2\2\2!&\7$\2\2\"%\5\17"+
		"\b\2#%\13\2\2\2$\"\3\2\2\2$#\3\2\2\2%(\3\2\2\2&\'\3\2\2\2&$\3\2\2\2\'"+
		")\3\2\2\2(&\3\2\2\2)*\7$\2\2*\6\3\2\2\2+,\7?\2\2,\b\3\2\2\2-.\7=\2\2."+
		"\n\3\2\2\2/\60\7.\2\2\60\f\3\2\2\2\61\63\t\2\2\2\62\61\3\2\2\2\63\64\3"+
		"\2\2\2\64\62\3\2\2\2\64\65\3\2\2\2\65\66\3\2\2\2\66\67\b\7\2\2\67\16\3"+
		"\2\2\289\7^\2\29=\7$\2\2:;\7^\2\2;=\7^\2\2<8\3\2\2\2<:\3\2\2\2=\20\3\2"+
		"\2\2>?\7\61\2\2?@\7\61\2\2@D\3\2\2\2AC\13\2\2\2BA\3\2\2\2CF\3\2\2\2DE"+
		"\3\2\2\2DB\3\2\2\2EH\3\2\2\2FD\3\2\2\2GI\7\17\2\2HG\3\2\2\2HI\3\2\2\2"+
		"IJ\3\2\2\2JK\7\f\2\2KL\3\2\2\2LM\b\t\2\2M\22\3\2\2\2NO\7\61\2\2OP\7,\2"+
		"\2PT\3\2\2\2QS\13\2\2\2RQ\3\2\2\2SV\3\2\2\2TU\3\2\2\2TR\3\2\2\2UW\3\2"+
		"\2\2VT\3\2\2\2WX\7,\2\2XY\7\61\2\2YZ\3\2\2\2Z[\b\n\2\2[\24\3\2\2\2\n\2"+
		"$&\64<DHT\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}