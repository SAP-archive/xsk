// Generated from com/sap/xsk/parser/hdbsynonym/core/Hdbsynonym.g4 by ANTLR 4.3
package com.sap.xsk.parser.hdbsynonym.core;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class HdbsynonymLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__4=1, T__3=2, T__2=3, T__1=4, T__0=5, STRING=6, COMMA=7, COLON=8, ESC=9, 
		WS=10, LINE_COMMENT=11, COMMENT=12;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"'\\u0000'", "'\\u0001'", "'\\u0002'", "'\\u0003'", "'\\u0004'", "'\\u0005'", 
		"'\\u0006'", "'\\u0007'", "'\b'", "'\t'", "'\n'", "'\\u000B'", "'\f'"
	};
	public static final String[] ruleNames = {
		"T__4", "T__3", "T__2", "T__1", "T__0", "STRING", "COMMA", "COLON", "ESC", 
		"WS", "LINE_COMMENT", "COMMENT"
	};


	public HdbsynonymLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Hdbsynonym.g4"; }

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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\16s\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\7\3\7\3\7\7\7>\n\7\f\7\16\7A\13\7\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3"+
		"\n\3\n\3\n\5\nM\n\n\3\13\6\13P\n\13\r\13\16\13Q\3\13\3\13\3\f\3\f\3\f"+
		"\3\f\7\fZ\n\f\f\f\16\f]\13\f\3\f\5\f`\n\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r"+
		"\3\r\7\rj\n\r\f\r\16\rm\13\r\3\r\3\r\3\r\3\r\3\r\5?[k\2\16\3\3\5\4\7\5"+
		"\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\3\2\4\4\2}}\177\177\5\2"+
		"\13\f\16\17\"\"y\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13"+
		"\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2"+
		"\2\2\27\3\2\2\2\2\31\3\2\2\2\3\33\3\2\2\2\5$\3\2\2\2\7-\3\2\2\2\t/\3\2"+
		"\2\2\13\61\3\2\2\2\r:\3\2\2\2\17D\3\2\2\2\21F\3\2\2\2\23L\3\2\2\2\25O"+
		"\3\2\2\2\27U\3\2\2\2\31e\3\2\2\2\33\34\7$\2\2\34\35\7v\2\2\35\36\7c\2"+
		"\2\36\37\7t\2\2\37 \7i\2\2 !\7g\2\2!\"\7v\2\2\"#\7$\2\2#\4\3\2\2\2$%\7"+
		"$\2\2%&\7q\2\2&\'\7d\2\2\'(\7l\2\2()\7g\2\2)*\7e\2\2*+\7v\2\2+,\7$\2\2"+
		",\6\3\2\2\2-.\7}\2\2.\b\3\2\2\2/\60\7\177\2\2\60\n\3\2\2\2\61\62\7$\2"+
		"\2\62\63\7u\2\2\63\64\7e\2\2\64\65\7j\2\2\65\66\7g\2\2\66\67\7o\2\2\67"+
		"8\7c\2\289\7$\2\29\f\3\2\2\2:?\7$\2\2;>\5\23\n\2<>\n\2\2\2=;\3\2\2\2="+
		"<\3\2\2\2>A\3\2\2\2?@\3\2\2\2?=\3\2\2\2@B\3\2\2\2A?\3\2\2\2BC\7$\2\2C"+
		"\16\3\2\2\2DE\7.\2\2E\20\3\2\2\2FG\7<\2\2G\22\3\2\2\2HI\7^\2\2IM\7$\2"+
		"\2JK\7^\2\2KM\7^\2\2LH\3\2\2\2LJ\3\2\2\2M\24\3\2\2\2NP\t\3\2\2ON\3\2\2"+
		"\2PQ\3\2\2\2QO\3\2\2\2QR\3\2\2\2RS\3\2\2\2ST\b\13\2\2T\26\3\2\2\2UV\7"+
		"\61\2\2VW\7\61\2\2W[\3\2\2\2XZ\13\2\2\2YX\3\2\2\2Z]\3\2\2\2[\\\3\2\2\2"+
		"[Y\3\2\2\2\\_\3\2\2\2][\3\2\2\2^`\7\17\2\2_^\3\2\2\2_`\3\2\2\2`a\3\2\2"+
		"\2ab\7\f\2\2bc\3\2\2\2cd\b\f\2\2d\30\3\2\2\2ef\7\61\2\2fg\7,\2\2gk\3\2"+
		"\2\2hj\13\2\2\2ih\3\2\2\2jm\3\2\2\2kl\3\2\2\2ki\3\2\2\2ln\3\2\2\2mk\3"+
		"\2\2\2no\7,\2\2op\7\61\2\2pq\3\2\2\2qr\b\r\2\2r\32\3\2\2\2\n\2=?LQ[_k"+
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