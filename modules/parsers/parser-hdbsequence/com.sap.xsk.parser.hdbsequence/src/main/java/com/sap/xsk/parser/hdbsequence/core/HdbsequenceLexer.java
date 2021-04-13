// Generated from com\sap\xsk\parser\hdbsequence\core\Hdbsequence.g4 by ANTLR 4.3
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
		T__9=1, T__8=2, T__7=3, T__6=4, T__5=5, T__4=6, T__3=7, T__2=8, T__1=9, 
		T__0=10, STRING=11, INT=12, BOOLEAN=13, TRUE=14, FALSE=15, WS=16, LB=17, 
		RB=18, EQ=19, SC=20, SIGNED_INT=21, LINE_COMMENT=22, COMMENT=23;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"'\\u0000'", "'\\u0001'", "'\\u0002'", "'\\u0003'", "'\\u0004'", "'\\u0005'", 
		"'\\u0006'", "'\\u0007'", "'\b'", "'\t'", "'\n'", "'\\u000B'", "'\f'", 
		"'\r'", "'\\u000E'", "'\\u000F'", "'\\u0010'", "'\\u0011'", "'\\u0012'", 
		"'\\u0013'", "'\\u0014'", "'\\u0015'", "'\\u0016'", "'\\u0017'"
	};
	public static final String[] ruleNames = {
		"T__9", "T__8", "T__7", "T__6", "T__5", "T__4", "T__3", "T__2", "T__1", 
		"T__0", "STRING", "INT", "BOOLEAN", "TRUE", "FALSE", "WS", "LB", "RB", 
		"EQ", "SC", "SIGNED_INT", "LINE_COMMENT", "COMMENT", "EscapeSequence", 
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\31\u0109\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f"+
		"\3\f\7\f\u0099\n\f\f\f\16\f\u009c\13\f\3\f\3\f\3\r\5\r\u00a1\n\r\3\r\6"+
		"\r\u00a4\n\r\r\r\16\r\u00a5\3\16\3\16\5\16\u00aa\n\16\3\17\3\17\3\17\3"+
		"\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\21\6\21\u00b8\n\21\r\21\16\21"+
		"\u00b9\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27"+
		"\3\27\3\27\3\27\7\27\u00cc\n\27\f\27\16\27\u00cf\13\27\3\27\5\27\u00d2"+
		"\n\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\7\30\u00dc\n\30\f\30\16"+
		"\30\u00df\13\30\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\5\31\u00ea"+
		"\n\31\3\31\5\31\u00ed\n\31\3\31\3\31\3\31\6\31\u00f2\n\31\r\31\16\31\u00f3"+
		"\3\31\3\31\3\31\3\31\3\31\5\31\u00fb\n\31\3\32\3\32\3\32\7\32\u0100\n"+
		"\32\f\32\16\32\u0103\13\32\3\32\5\32\u0106\n\32\3\33\3\33\4\u00cd\u00dd"+
		"\2\34\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35"+
		"\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\2\63\2\65\2\3\2\t\6\2\f"+
		"\f\17\17$$^^\3\2\62;\6\2\13\f\17\17\"\"^^\n\2$$))^^ddhhppttvv\3\2\62\65"+
		"\3\2\629\5\2\62;CHch\u0116\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2"+
		"\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2"+
		"\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3"+
		"\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2"+
		"\2\2\2-\3\2\2\2\2/\3\2\2\2\3\67\3\2\2\2\5B\3\2\2\2\7K\3\2\2\2\tR\3\2\2"+
		"\2\13]\3\2\2\2\rd\3\2\2\2\17m\3\2\2\2\21z\3\2\2\2\23\u0081\3\2\2\2\25"+
		"\u008c\3\2\2\2\27\u0095\3\2\2\2\31\u00a0\3\2\2\2\33\u00a9\3\2\2\2\35\u00ab"+
		"\3\2\2\2\37\u00b0\3\2\2\2!\u00b7\3\2\2\2#\u00bd\3\2\2\2%\u00bf\3\2\2\2"+
		"\'\u00c1\3\2\2\2)\u00c3\3\2\2\2+\u00c5\3\2\2\2-\u00c7\3\2\2\2/\u00d7\3"+
		"\2\2\2\61\u00fa\3\2\2\2\63\u00fc\3\2\2\2\65\u0107\3\2\2\2\678\7p\2\28"+
		"9\7q\2\29:\7o\2\2:;\7k\2\2;<\7p\2\2<=\7x\2\2=>\7c\2\2>?\7n\2\2?@\7w\2"+
		"\2@A\7g\2\2A\4\3\2\2\2BC\7t\2\2CD\7g\2\2DE\7u\2\2EF\7g\2\2FG\7v\2\2GH"+
		"\7a\2\2HI\7d\2\2IJ\7{\2\2J\6\3\2\2\2KL\7u\2\2LM\7e\2\2MN\7j\2\2NO\7g\2"+
		"\2OP\7o\2\2PQ\7c\2\2Q\b\3\2\2\2RS\7u\2\2ST\7v\2\2TU\7c\2\2UV\7t\2\2VW"+
		"\7v\2\2WX\7a\2\2XY\7y\2\2YZ\7k\2\2Z[\7v\2\2[\\\7j\2\2\\\n\3\2\2\2]^\7"+
		"e\2\2^_\7{\2\2_`\7e\2\2`a\7n\2\2ab\7g\2\2bc\7u\2\2c\f\3\2\2\2de\7o\2\2"+
		"ef\7c\2\2fg\7z\2\2gh\7x\2\2hi\7c\2\2ij\7n\2\2jk\7w\2\2kl\7g\2\2l\16\3"+
		"\2\2\2mn\7k\2\2no\7p\2\2op\7e\2\2pq\7t\2\2qr\7g\2\2rs\7o\2\2st\7g\2\2"+
		"tu\7p\2\2uv\7v\2\2vw\7a\2\2wx\7d\2\2xy\7{\2\2y\20\3\2\2\2z{\7r\2\2{|\7"+
		"w\2\2|}\7d\2\2}~\7n\2\2~\177\7k\2\2\177\u0080\7e\2\2\u0080\22\3\2\2\2"+
		"\u0081\u0082\7p\2\2\u0082\u0083\7q\2\2\u0083\u0084\7o\2\2\u0084\u0085"+
		"\7c\2\2\u0085\u0086\7z\2\2\u0086\u0087\7x\2\2\u0087\u0088\7c\2\2\u0088"+
		"\u0089\7n\2\2\u0089\u008a\7w\2\2\u008a\u008b\7g\2\2\u008b\24\3\2\2\2\u008c"+
		"\u008d\7o\2\2\u008d\u008e\7k\2\2\u008e\u008f\7p\2\2\u008f\u0090\7x\2\2"+
		"\u0090\u0091\7c\2\2\u0091\u0092\7n\2\2\u0092\u0093\7w\2\2\u0093\u0094"+
		"\7g\2\2\u0094\26\3\2\2\2\u0095\u009a\7$\2\2\u0096\u0099\n\2\2\2\u0097"+
		"\u0099\5\61\31\2\u0098\u0096\3\2\2\2\u0098\u0097\3\2\2\2\u0099\u009c\3"+
		"\2\2\2\u009a\u0098\3\2\2\2\u009a\u009b\3\2\2\2\u009b\u009d\3\2\2\2\u009c"+
		"\u009a\3\2\2\2\u009d\u009e\7$\2\2\u009e\30\3\2\2\2\u009f\u00a1\5+\26\2"+
		"\u00a0\u009f\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1\u00a3\3\2\2\2\u00a2\u00a4"+
		"\t\3\2\2\u00a3\u00a2\3\2\2\2\u00a4\u00a5\3\2\2\2\u00a5\u00a3\3\2\2\2\u00a5"+
		"\u00a6\3\2\2\2\u00a6\32\3\2\2\2\u00a7\u00aa\5\35\17\2\u00a8\u00aa\5\37"+
		"\20\2\u00a9\u00a7\3\2\2\2\u00a9\u00a8\3\2\2\2\u00aa\34\3\2\2\2\u00ab\u00ac"+
		"\7v\2\2\u00ac\u00ad\7t\2\2\u00ad\u00ae\7w\2\2\u00ae\u00af\7g\2\2\u00af"+
		"\36\3\2\2\2\u00b0\u00b1\7h\2\2\u00b1\u00b2\7c\2\2\u00b2\u00b3\7n\2\2\u00b3"+
		"\u00b4\7u\2\2\u00b4\u00b5\7g\2\2\u00b5 \3\2\2\2\u00b6\u00b8\t\4\2\2\u00b7"+
		"\u00b6\3\2\2\2\u00b8\u00b9\3\2\2\2\u00b9\u00b7\3\2\2\2\u00b9\u00ba\3\2"+
		"\2\2\u00ba\u00bb\3\2\2\2\u00bb\u00bc\b\21\2\2\u00bc\"\3\2\2\2\u00bd\u00be"+
		"\7]\2\2\u00be$\3\2\2\2\u00bf\u00c0\7_\2\2\u00c0&\3\2\2\2\u00c1\u00c2\7"+
		"?\2\2\u00c2(\3\2\2\2\u00c3\u00c4\7=\2\2\u00c4*\3\2\2\2\u00c5\u00c6\7/"+
		"\2\2\u00c6,\3\2\2\2\u00c7\u00c8\7\61\2\2\u00c8\u00c9\7\61\2\2\u00c9\u00cd"+
		"\3\2\2\2\u00ca\u00cc\13\2\2\2\u00cb\u00ca\3\2\2\2\u00cc\u00cf\3\2\2\2"+
		"\u00cd\u00ce\3\2\2\2\u00cd\u00cb\3\2\2\2\u00ce\u00d1\3\2\2\2\u00cf\u00cd"+
		"\3\2\2\2\u00d0\u00d2\7\17\2\2\u00d1\u00d0\3\2\2\2\u00d1\u00d2\3\2\2\2"+
		"\u00d2\u00d3\3\2\2\2\u00d3\u00d4\7\f\2\2\u00d4\u00d5\3\2\2\2\u00d5\u00d6"+
		"\b\27\2\2\u00d6.\3\2\2\2\u00d7\u00d8\7\61\2\2\u00d8\u00d9\7,\2\2\u00d9"+
		"\u00dd\3\2\2\2\u00da\u00dc\13\2\2\2\u00db\u00da\3\2\2\2\u00dc\u00df\3"+
		"\2\2\2\u00dd\u00de\3\2\2\2\u00dd\u00db\3\2\2\2\u00de\u00e0\3\2\2\2\u00df"+
		"\u00dd\3\2\2\2\u00e0\u00e1\7,\2\2\u00e1\u00e2\7\61\2\2\u00e2\u00e3\3\2"+
		"\2\2\u00e3\u00e4\b\30\2\2\u00e4\60\3\2\2\2\u00e5\u00e6\7^\2\2\u00e6\u00fb"+
		"\t\5\2\2\u00e7\u00ec\7^\2\2\u00e8\u00ea\t\6\2\2\u00e9\u00e8\3\2\2\2\u00e9"+
		"\u00ea\3\2\2\2\u00ea\u00eb\3\2\2\2\u00eb\u00ed\t\7\2\2\u00ec\u00e9\3\2"+
		"\2\2\u00ec\u00ed\3\2\2\2\u00ed\u00ee\3\2\2\2\u00ee\u00fb\t\7\2\2\u00ef"+
		"\u00f1\7^\2\2\u00f0\u00f2\7w\2\2\u00f1\u00f0\3\2\2\2\u00f2\u00f3\3\2\2"+
		"\2\u00f3\u00f1\3\2\2\2\u00f3\u00f4\3\2\2\2\u00f4\u00f5\3\2\2\2\u00f5\u00f6"+
		"\5\65\33\2\u00f6\u00f7\5\65\33\2\u00f7\u00f8\5\65\33\2\u00f8\u00f9\5\65"+
		"\33\2\u00f9\u00fb\3\2\2\2\u00fa\u00e5\3\2\2\2\u00fa\u00e7\3\2\2\2\u00fa"+
		"\u00ef\3\2\2\2\u00fb\62\3\2\2\2\u00fc\u0105\5\65\33\2\u00fd\u0100\5\65"+
		"\33\2\u00fe\u0100\7a\2\2\u00ff\u00fd\3\2\2\2\u00ff\u00fe\3\2\2\2\u0100"+
		"\u0103\3\2\2\2\u0101\u00ff\3\2\2\2\u0101\u0102\3\2\2\2\u0102\u0104\3\2"+
		"\2\2\u0103\u0101\3\2\2\2\u0104\u0106\5\65\33\2\u0105\u0101\3\2\2\2\u0105"+
		"\u0106\3\2\2\2\u0106\64\3\2\2\2\u0107\u0108\t\b\2\2\u0108\66\3\2\2\2\23"+
		"\2\u0098\u009a\u00a0\u00a5\u00a9\u00b9\u00cd\u00d1\u00dd\u00e9\u00ec\u00f3"+
		"\u00fa\u00ff\u0101\u0105\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}