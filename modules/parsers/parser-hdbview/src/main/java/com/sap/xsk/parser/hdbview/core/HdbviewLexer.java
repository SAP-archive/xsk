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
		T__8=1, T__7=2, T__6=3, T__5=4, T__4=5, T__3=6, T__2=7, T__1=8, T__0=9, 
		BOOLEAN=10, STRING=11, EQ=12, SEMICOLON=13, WS=14, ESC=15, LINE_COMMENT=16, 
		COMMENT=17;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"'\\u0000'", "'\\u0001'", "'\\u0002'", "'\\u0003'", "'\\u0004'", "'\\u0005'", 
		"'\\u0006'", "'\\u0007'", "'\b'", "'\t'", "'\n'", "'\\u000B'", "'\f'", 
		"'\r'", "'\\u000E'", "'\\u000F'", "'\\u0010'", "'\\u0011'"
	};
	public static final String[] ruleNames = {
		"T__8", "T__7", "T__6", "T__5", "T__4", "T__3", "T__2", "T__1", "T__0", 
		"BOOLEAN", "STRING", "EQ", "SEMICOLON", "WS", "ESC", "LINE_COMMENT", "COMMENT"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\23\u00af\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13u\n\13\3\f\3\f"+
		"\3\f\7\fz\n\f\f\f\16\f}\13\f\3\f\3\f\3\r\3\r\3\16\3\16\3\17\6\17\u0086"+
		"\n\17\r\17\16\17\u0087\3\17\3\17\3\20\3\20\3\20\3\20\5\20\u0090\n\20\3"+
		"\21\3\21\3\21\3\21\7\21\u0096\n\21\f\21\16\21\u0099\13\21\3\21\5\21\u009c"+
		"\n\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\7\22\u00a6\n\22\f\22\16"+
		"\22\u00a9\13\22\3\22\3\22\3\22\3\22\3\22\5{\u0097\u00a7\2\23\3\3\5\4\7"+
		"\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22"+
		"#\23\3\2\3\5\2\13\f\16\17\"\"\u00b6\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2"+
		"\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23"+
		"\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2"+
		"\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\3%\3\2\2\2\5,\3\2\2\2\7=\3\2\2"+
		"\2\t?\3\2\2\2\13J\3\2\2\2\rL\3\2\2\2\17\\\3\2\2\2\21^\3\2\2\2\23d\3\2"+
		"\2\2\25t\3\2\2\2\27v\3\2\2\2\31\u0080\3\2\2\2\33\u0082\3\2\2\2\35\u0085"+
		"\3\2\2\2\37\u008f\3\2\2\2!\u0091\3\2\2\2#\u00a1\3\2\2\2%&\7u\2\2&\'\7"+
		"e\2\2\'(\7j\2\2()\7g\2\2)*\7o\2\2*+\7c\2\2+\4\3\2\2\2,-\7f\2\2-.\7g\2"+
		"\2./\7r\2\2/\60\7g\2\2\60\61\7p\2\2\61\62\7f\2\2\62\63\7u\2\2\63\64\7"+
		"a\2\2\64\65\7q\2\2\65\66\7p\2\2\66\67\7a\2\2\678\7v\2\289\7c\2\29:\7d"+
		"\2\2:;\7n\2\2;<\7g\2\2<\6\3\2\2\2=>\7]\2\2>\b\3\2\2\2?@\7f\2\2@A\7g\2"+
		"\2AB\7r\2\2BC\7g\2\2CD\7p\2\2DE\7f\2\2EF\7u\2\2FG\7a\2\2GH\7q\2\2HI\7"+
		"p\2\2I\n\3\2\2\2JK\7.\2\2K\f\3\2\2\2LM\7f\2\2MN\7g\2\2NO\7r\2\2OP\7g\2"+
		"\2PQ\7p\2\2QR\7f\2\2RS\7u\2\2ST\7a\2\2TU\7q\2\2UV\7p\2\2VW\7a\2\2WX\7"+
		"x\2\2XY\7k\2\2YZ\7g\2\2Z[\7y\2\2[\16\3\2\2\2\\]\7_\2\2]\20\3\2\2\2^_\7"+
		"s\2\2_`\7w\2\2`a\7g\2\2ab\7t\2\2bc\7{\2\2c\22\3\2\2\2de\7r\2\2ef\7w\2"+
		"\2fg\7d\2\2gh\7n\2\2hi\7k\2\2ij\7e\2\2j\24\3\2\2\2kl\7v\2\2lm\7t\2\2m"+
		"n\7w\2\2nu\7g\2\2op\7h\2\2pq\7c\2\2qr\7n\2\2rs\7u\2\2su\7g\2\2tk\3\2\2"+
		"\2to\3\2\2\2u\26\3\2\2\2v{\7$\2\2wz\5\37\20\2xz\13\2\2\2yw\3\2\2\2yx\3"+
		"\2\2\2z}\3\2\2\2{|\3\2\2\2{y\3\2\2\2|~\3\2\2\2}{\3\2\2\2~\177\7$\2\2\177"+
		"\30\3\2\2\2\u0080\u0081\7?\2\2\u0081\32\3\2\2\2\u0082\u0083\7=\2\2\u0083"+
		"\34\3\2\2\2\u0084\u0086\t\2\2\2\u0085\u0084\3\2\2\2\u0086\u0087\3\2\2"+
		"\2\u0087\u0085\3\2\2\2\u0087\u0088\3\2\2\2\u0088\u0089\3\2\2\2\u0089\u008a"+
		"\b\17\2\2\u008a\36\3\2\2\2\u008b\u008c\7^\2\2\u008c\u0090\7$\2\2\u008d"+
		"\u008e\7^\2\2\u008e\u0090\7^\2\2\u008f\u008b\3\2\2\2\u008f\u008d\3\2\2"+
		"\2\u0090 \3\2\2\2\u0091\u0092\7\61\2\2\u0092\u0093\7\61\2\2\u0093\u0097"+
		"\3\2\2\2\u0094\u0096\13\2\2\2\u0095\u0094\3\2\2\2\u0096\u0099\3\2\2\2"+
		"\u0097\u0098\3\2\2\2\u0097\u0095\3\2\2\2\u0098\u009b\3\2\2\2\u0099\u0097"+
		"\3\2\2\2\u009a\u009c\7\17\2\2\u009b\u009a\3\2\2\2\u009b\u009c\3\2\2\2"+
		"\u009c\u009d\3\2\2\2\u009d\u009e\7\f\2\2\u009e\u009f\3\2\2\2\u009f\u00a0"+
		"\b\21\2\2\u00a0\"\3\2\2\2\u00a1\u00a2\7\61\2\2\u00a2\u00a3\7,\2\2\u00a3"+
		"\u00a7\3\2\2\2\u00a4\u00a6\13\2\2\2\u00a5\u00a4\3\2\2\2\u00a6\u00a9\3"+
		"\2\2\2\u00a7\u00a8\3\2\2\2\u00a7\u00a5\3\2\2\2\u00a8\u00aa\3\2\2\2\u00a9"+
		"\u00a7\3\2\2\2\u00aa\u00ab\7,\2\2\u00ab\u00ac\7\61\2\2\u00ac\u00ad\3\2"+
		"\2\2\u00ad\u00ae\b\22\2\2\u00ae$\3\2\2\2\13\2ty{\u0087\u008f\u0097\u009b"+
		"\u00a7\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}