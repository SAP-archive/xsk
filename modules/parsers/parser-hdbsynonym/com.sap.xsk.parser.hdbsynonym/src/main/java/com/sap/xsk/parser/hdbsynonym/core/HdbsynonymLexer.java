// Generated from com\sap\xsk\parser\hdbsynonym\core\Hdbsynonym.g4 by ANTLR 4.3
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
		T__4=1, T__3=2, T__2=3, T__1=4, T__0=5, AA=6, STRING=7, COMMA=8, COLON=9, 
		WS=10, ESC=11, LINE_COMMENT=12, COMMENT=13;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"'\\u0000'", "'\\u0001'", "'\\u0002'", "'\\u0003'", "'\\u0004'", "'\\u0005'", 
		"'\\u0006'", "'\\u0007'", "'\b'", "'\t'", "'\n'", "'\\u000B'", "'\f'", 
		"'\r'"
	};
	public static final String[] ruleNames = {
		"T__4", "T__3", "T__2", "T__1", "T__0", "AA", "STRING", "COMMA", "COLON", 
		"WS", "ESC", "LINE_COMMENT", "COMMENT"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\17\u0087\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\5\7M\n\7\3\b\3\b\3\b\7\bR\n\b\f\b\16\bU\13\b\3\b\3\b\3\t"+
		"\3\t\3\n\3\n\3\13\6\13^\n\13\r\13\16\13_\3\13\3\13\3\f\3\f\3\f\3\f\5\f"+
		"h\n\f\3\r\3\r\3\r\3\r\7\rn\n\r\f\r\16\rq\13\r\3\r\5\rt\n\r\3\r\3\r\3\r"+
		"\3\r\3\16\3\16\3\16\3\16\7\16~\n\16\f\16\16\16\u0081\13\16\3\16\3\16\3"+
		"\16\3\16\3\16\5So\177\2\17\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25"+
		"\f\27\r\31\16\33\17\3\2\4\4\2}}\177\177\5\2\13\f\16\17\"\"\u008e\2\3\3"+
		"\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2"+
		"\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3"+
		"\2\2\2\2\33\3\2\2\2\3\35\3\2\2\2\5&\3\2\2\2\7/\3\2\2\2\t\61\3\2\2\2\13"+
		"\63\3\2\2\2\rL\3\2\2\2\17N\3\2\2\2\21X\3\2\2\2\23Z\3\2\2\2\25]\3\2\2\2"+
		"\27g\3\2\2\2\31i\3\2\2\2\33y\3\2\2\2\35\36\7$\2\2\36\37\7v\2\2\37 \7c"+
		"\2\2 !\7t\2\2!\"\7i\2\2\"#\7g\2\2#$\7v\2\2$%\7$\2\2%\4\3\2\2\2&\'\7$\2"+
		"\2\'(\7q\2\2()\7d\2\2)*\7l\2\2*+\7g\2\2+,\7e\2\2,-\7v\2\2-.\7$\2\2.\6"+
		"\3\2\2\2/\60\7}\2\2\60\b\3\2\2\2\61\62\7\177\2\2\62\n\3\2\2\2\63\64\7"+
		"$\2\2\64\65\7u\2\2\65\66\7e\2\2\66\67\7j\2\2\678\7g\2\289\7o\2\29:\7c"+
		"\2\2:;\7$\2\2;\f\3\2\2\2<=\7$\2\2=>\7q\2\2>?\7d\2\2?@\7l\2\2@A\7g\2\2"+
		"AB\7e\2\2BC\7v\2\2CM\7$\2\2DE\7$\2\2EF\7u\2\2FG\7e\2\2GH\7j\2\2HI\7g\2"+
		"\2IJ\7o\2\2JK\7c\2\2KM\7$\2\2L<\3\2\2\2LD\3\2\2\2M\16\3\2\2\2NS\7$\2\2"+
		"OR\5\27\f\2PR\n\2\2\2QO\3\2\2\2QP\3\2\2\2RU\3\2\2\2ST\3\2\2\2SQ\3\2\2"+
		"\2TV\3\2\2\2US\3\2\2\2VW\7$\2\2W\20\3\2\2\2XY\7.\2\2Y\22\3\2\2\2Z[\7<"+
		"\2\2[\24\3\2\2\2\\^\t\3\2\2]\\\3\2\2\2^_\3\2\2\2_]\3\2\2\2_`\3\2\2\2`"+
		"a\3\2\2\2ab\b\13\2\2b\26\3\2\2\2cd\7^\2\2dh\7$\2\2ef\7^\2\2fh\7^\2\2g"+
		"c\3\2\2\2ge\3\2\2\2h\30\3\2\2\2ij\7\61\2\2jk\7\61\2\2ko\3\2\2\2ln\13\2"+
		"\2\2ml\3\2\2\2nq\3\2\2\2op\3\2\2\2om\3\2\2\2ps\3\2\2\2qo\3\2\2\2rt\7\17"+
		"\2\2sr\3\2\2\2st\3\2\2\2tu\3\2\2\2uv\7\f\2\2vw\3\2\2\2wx\b\r\2\2x\32\3"+
		"\2\2\2yz\7\61\2\2z{\7,\2\2{\177\3\2\2\2|~\13\2\2\2}|\3\2\2\2~\u0081\3"+
		"\2\2\2\177\u0080\3\2\2\2\177}\3\2\2\2\u0080\u0082\3\2\2\2\u0081\177\3"+
		"\2\2\2\u0082\u0083\7,\2\2\u0083\u0084\7\61\2\2\u0084\u0085\3\2\2\2\u0085"+
		"\u0086\b\16\2\2\u0086\34\3\2\2\2\13\2LQS_gos\177\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}