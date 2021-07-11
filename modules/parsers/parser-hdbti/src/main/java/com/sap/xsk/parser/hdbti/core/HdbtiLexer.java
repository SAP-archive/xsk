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
		T__15=1, T__14=2, T__13=3, T__12=4, T__11=5, T__10=6, T__9=7, T__8=8, 
		T__7=9, T__6=10, T__5=11, T__4=12, T__3=13, T__2=14, T__1=15, T__0=16, 
		STRING=17, BOOLEAN=18, TRUE=19, FALSE=20, WS=21, RB=22, LB=23, EQ=24;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"'\\u0000'", "'\\u0001'", "'\\u0002'", "'\\u0003'", "'\\u0004'", "'\\u0005'", 
		"'\\u0006'", "'\\u0007'", "'\b'", "'\t'", "'\n'", "'\\u000B'", "'\f'", 
		"'\r'", "'\\u000E'", "'\\u000F'", "'\\u0010'", "'\\u0011'", "'\\u0012'", 
		"'\\u0013'", "'\\u0014'", "'\\u0015'", "'\\u0016'", "'\\u0017'", "'\\u0018'"
	};
	public static final String[] ruleNames = {
		"T__15", "T__14", "T__13", "T__12", "T__11", "T__10", "T__9", "T__8", 
		"T__7", "T__6", "T__5", "T__4", "T__3", "T__2", "T__1", "T__0", "STRING", 
		"EscapeSequence", "HexDigits", "HexDigit", "BOOLEAN", "TRUE", "FALSE", 
		"WS", "RB", "LB", "EQ"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\32\u00fd\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\7\3"+
		"\7\3\b\3\b\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\20\3"+
		"\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3"+
		"\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3"+
		"\21\3\21\3\21\3\22\3\22\3\22\7\22\u00b7\n\22\f\22\16\22\u00ba\13\22\3"+
		"\22\3\22\3\23\3\23\3\23\3\23\5\23\u00c2\n\23\3\23\5\23\u00c5\n\23\3\23"+
		"\3\23\3\23\6\23\u00ca\n\23\r\23\16\23\u00cb\3\23\3\23\3\23\3\23\3\23\5"+
		"\23\u00d3\n\23\3\24\3\24\3\24\7\24\u00d8\n\24\f\24\16\24\u00db\13\24\3"+
		"\24\5\24\u00de\n\24\3\25\3\25\3\26\3\26\5\26\u00e4\n\26\3\27\3\27\3\27"+
		"\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30\3\31\6\31\u00f2\n\31\r\31\16"+
		"\31\u00f3\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\2\2\35\3\3\5\4\7\5\t"+
		"\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23"+
		"%\2\'\2)\2+\24-\25/\26\61\27\63\30\65\31\67\32\3\2\b\6\2\f\f\17\17$$^"+
		"^\n\2$$))^^ddhhppttvv\3\2\62\65\3\2\629\5\2\62;CHch\6\2\13\f\17\17\"\""+
		"^^\u0105\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2"+
		"\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27"+
		"\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2"+
		"\2\2#\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2"+
		"\2\2\65\3\2\2\2\2\67\3\2\2\2\39\3\2\2\2\5>\3\2\2\2\7I\3\2\2\2\tX\3\2\2"+
		"\2\13a\3\2\2\2\rc\3\2\2\2\17e\3\2\2\2\21g\3\2\2\2\23i\3\2\2\2\25n\3\2"+
		"\2\2\27u\3\2\2\2\31{\3\2\2\2\33\u008a\3\2\2\2\35\u0091\3\2\2\2\37\u0093"+
		"\3\2\2\2!\u009a\3\2\2\2#\u00b3\3\2\2\2%\u00d2\3\2\2\2\'\u00d4\3\2\2\2"+
		")\u00df\3\2\2\2+\u00e3\3\2\2\2-\u00e5\3\2\2\2/\u00ea\3\2\2\2\61\u00f1"+
		"\3\2\2\2\63\u00f7\3\2\2\2\65\u00f9\3\2\2\2\67\u00fb\3\2\2\29:\7h\2\2:"+
		";\7k\2\2;<\7n\2\2<=\7g\2\2=\4\3\2\2\2>?\7f\2\2?@\7g\2\2@A\7n\2\2AB\7k"+
		"\2\2BC\7o\2\2CD\7H\2\2DE\7k\2\2EF\7g\2\2FG\7n\2\2GH\7f\2\2H\6\3\2\2\2"+
		"IJ\7w\2\2JK\7u\2\2KL\7g\2\2LM\7J\2\2MN\7g\2\2NO\7c\2\2OP\7f\2\2PQ\7g\2"+
		"\2QR\7t\2\2RS\7P\2\2ST\7c\2\2TU\7o\2\2UV\7g\2\2VW\7u\2\2W\b\3\2\2\2XY"+
		"\7j\2\2YZ\7f\2\2Z[\7d\2\2[\\\7v\2\2\\]\7c\2\2]^\7d\2\2^_\7n\2\2_`\7g\2"+
		"\2`\n\3\2\2\2ab\7<\2\2b\f\3\2\2\2cd\7}\2\2d\16\3\2\2\2ef\7=\2\2f\20\3"+
		"\2\2\2gh\7\177\2\2h\22\3\2\2\2ij\7m\2\2jk\7g\2\2kl\7{\2\2lm\7u\2\2m\24"+
		"\3\2\2\2no\7u\2\2op\7e\2\2pq\7j\2\2qr\7g\2\2rs\7o\2\2st\7c\2\2t\26\3\2"+
		"\2\2uv\7v\2\2vw\7c\2\2wx\7d\2\2xy\7n\2\2yz\7g\2\2z\30\3\2\2\2{|\7f\2\2"+
		"|}\7g\2\2}~\7n\2\2~\177\7k\2\2\177\u0080\7o\2\2\u0080\u0081\7G\2\2\u0081"+
		"\u0082\7p\2\2\u0082\u0083\7e\2\2\u0083\u0084\7n\2\2\u0084\u0085\7q\2\2"+
		"\u0085\u0086\7u\2\2\u0086\u0087\7k\2\2\u0087\u0088\7p\2\2\u0088\u0089"+
		"\7i\2\2\u0089\32\3\2\2\2\u008a\u008b\7j\2\2\u008b\u008c\7g\2\2\u008c\u008d"+
		"\7c\2\2\u008d\u008e\7f\2\2\u008e\u008f\7g\2\2\u008f\u0090\7t\2\2\u0090"+
		"\34\3\2\2\2\u0091\u0092\7.\2\2\u0092\36\3\2\2\2\u0093\u0094\7k\2\2\u0094"+
		"\u0095\7o\2\2\u0095\u0096\7r\2\2\u0096\u0097\7q\2\2\u0097\u0098\7t\2\2"+
		"\u0098\u0099\7v\2\2\u0099 \3\2\2\2\u009a\u009b\7f\2\2\u009b\u009c\7k\2"+
		"\2\u009c\u009d\7u\2\2\u009d\u009e\7v\2\2\u009e\u009f\7k\2\2\u009f\u00a0"+
		"\7p\2\2\u00a0\u00a1\7i\2\2\u00a1\u00a2\7w\2\2\u00a2\u00a3\7k\2\2\u00a3"+
		"\u00a4\7u\2\2\u00a4\u00a5\7j\2\2\u00a5\u00a6\7G\2\2\u00a6\u00a7\7o\2\2"+
		"\u00a7\u00a8\7r\2\2\u00a8\u00a9\7v\2\2\u00a9\u00aa\7{\2\2\u00aa\u00ab"+
		"\7H\2\2\u00ab\u00ac\7t\2\2\u00ac\u00ad\7q\2\2\u00ad\u00ae\7o\2\2\u00ae"+
		"\u00af\7P\2\2\u00af\u00b0\7w\2\2\u00b0\u00b1\7n\2\2\u00b1\u00b2\7n\2\2"+
		"\u00b2\"\3\2\2\2\u00b3\u00b8\7$\2\2\u00b4\u00b7\n\2\2\2\u00b5\u00b7\5"+
		"%\23\2\u00b6\u00b4\3\2\2\2\u00b6\u00b5\3\2\2\2\u00b7\u00ba\3\2\2\2\u00b8"+
		"\u00b6\3\2\2\2\u00b8\u00b9\3\2\2\2\u00b9\u00bb\3\2\2\2\u00ba\u00b8\3\2"+
		"\2\2\u00bb\u00bc\7$\2\2\u00bc$\3\2\2\2\u00bd\u00be\7^\2\2\u00be\u00d3"+
		"\t\3\2\2\u00bf\u00c4\7^\2\2\u00c0\u00c2\t\4\2\2\u00c1\u00c0\3\2\2\2\u00c1"+
		"\u00c2\3\2\2\2\u00c2\u00c3\3\2\2\2\u00c3\u00c5\t\5\2\2\u00c4\u00c1\3\2"+
		"\2\2\u00c4\u00c5\3\2\2\2\u00c5\u00c6\3\2\2\2\u00c6\u00d3\t\5\2\2\u00c7"+
		"\u00c9\7^\2\2\u00c8\u00ca\7w\2\2\u00c9\u00c8\3\2\2\2\u00ca\u00cb\3\2\2"+
		"\2\u00cb\u00c9\3\2\2\2\u00cb\u00cc\3\2\2\2\u00cc\u00cd\3\2\2\2\u00cd\u00ce"+
		"\5)\25\2\u00ce\u00cf\5)\25\2\u00cf\u00d0\5)\25\2\u00d0\u00d1\5)\25\2\u00d1"+
		"\u00d3\3\2\2\2\u00d2\u00bd\3\2\2\2\u00d2\u00bf\3\2\2\2\u00d2\u00c7\3\2"+
		"\2\2\u00d3&\3\2\2\2\u00d4\u00dd\5)\25\2\u00d5\u00d8\5)\25\2\u00d6\u00d8"+
		"\7a\2\2\u00d7\u00d5\3\2\2\2\u00d7\u00d6\3\2\2\2\u00d8\u00db\3\2\2\2\u00d9"+
		"\u00d7\3\2\2\2\u00d9\u00da\3\2\2\2\u00da\u00dc\3\2\2\2\u00db\u00d9\3\2"+
		"\2\2\u00dc\u00de\5)\25\2\u00dd\u00d9\3\2\2\2\u00dd\u00de\3\2\2\2\u00de"+
		"(\3\2\2\2\u00df\u00e0\t\6\2\2\u00e0*\3\2\2\2\u00e1\u00e4\5-\27\2\u00e2"+
		"\u00e4\5/\30\2\u00e3\u00e1\3\2\2\2\u00e3\u00e2\3\2\2\2\u00e4,\3\2\2\2"+
		"\u00e5\u00e6\7v\2\2\u00e6\u00e7\7t\2\2\u00e7\u00e8\7w\2\2\u00e8\u00e9"+
		"\7g\2\2\u00e9.\3\2\2\2\u00ea\u00eb\7h\2\2\u00eb\u00ec\7c\2\2\u00ec\u00ed"+
		"\7n\2\2\u00ed\u00ee\7u\2\2\u00ee\u00ef\7g\2\2\u00ef\60\3\2\2\2\u00f0\u00f2"+
		"\t\7\2\2\u00f1\u00f0\3\2\2\2\u00f2\u00f3\3\2\2\2\u00f3\u00f1\3\2\2\2\u00f3"+
		"\u00f4\3\2\2\2\u00f4\u00f5\3\2\2\2\u00f5\u00f6\b\31\2\2\u00f6\62\3\2\2"+
		"\2\u00f7\u00f8\7]\2\2\u00f8\64\3\2\2\2\u00f9\u00fa\7_\2\2\u00fa\66\3\2"+
		"\2\2\u00fb\u00fc\7?\2\2\u00fc8\3\2\2\2\16\2\u00b6\u00b8\u00c1\u00c4\u00cb"+
		"\u00d2\u00d7\u00d9\u00dd\u00e3\u00f3\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}