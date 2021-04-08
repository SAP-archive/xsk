// Generated from com/sap/xsk/parser/hdbtable/core/Hdbtable.g4 by ANTLR 4.3
package com.sap.xsk.parser.hdbtable.core;

import java.util.List;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.RuntimeMetaData;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class HdbtableParser extends Parser {

  public static final int
      T__26 = 1, T__25 = 2, T__24 = 3, T__23 = 4, T__22 = 5, T__21 = 6, T__20 = 7, T__19 = 8,
      T__18 = 9, T__17 = 10, T__16 = 11, T__15 = 12, T__14 = 13, T__13 = 14, T__12 = 15, T__11 = 16,
      T__10 = 17, T__9 = 18, T__8 = 19, T__7 = 20, T__6 = 21, T__5 = 22, T__4 = 23, T__3 = 24,
      T__2 = 25, T__1 = 26, T__0 = 27, STRING = 28, WS = 29, TABLE = 30, DOT = 31, EQ = 32,
      SEMICOLON = 33, SQLTYPES = 34, BOOLEAN = 35, ORDER = 36, INDEXTYPE = 37, INT = 38,
      TABLETYPE = 39, TABLELOGGINGTYPE = 40, DATETIMEDEFAULTVALUES = 41, LINE_COMMENT = 42,
      COMMENT = 43;
  public static final String[] tokenNames = {
      "<INVALID>", "'loggingType'", "'sqlType'", "'name'", "'length'", "'scale'",
      "'tableType'", "'{'", "'order'", "'}'", "'indexes'", "'nullable'", "'pkcolumns'",
      "'indexType'", "'indexColumns'", "'description'", "','", "'unique'", "'primaryKey'",
      "'columns'", "'precision'", "'defaultValue'", "'['", "']'", "'public'",
      "'comment'", "'temporary'", "'schemaName'", "STRING", "WS", "'table'",
      "'.'", "'='", "';'", "SQLTYPES", "BOOLEAN", "ORDER", "INDEXTYPE", "INT",
      "TABLETYPE", "TABLELOGGINGTYPE", "DATETIMEDEFAULTVALUES", "LINE_COMMENT",
      "COMMENT"
  };
  public static final int
      RULE_hdbtableDefinition = 0, RULE_schemaNameProp = 1, RULE_temporaryProp = 2,
      RULE_tableTypeProp = 3, RULE_publicProp = 4, RULE_loggingTypeProp = 5,
      RULE_tableColumnsProp = 6, RULE_tableIndexesProp = 7, RULE_tablePrimaryKeyProp = 8,
      RULE_tablePrimaryKeyColumnsProp = 9, RULE_tablePrimaryKeyIndexTypeProp = 10,
      RULE_descriptionProp = 11, RULE_columnsObject = 12, RULE_indexesObject = 13,
      RULE_columnAssignName = 14, RULE_columnAssignSQLType = 15, RULE_columnAssignNullable = 16,
      RULE_columnAssignUnique = 17, RULE_columnAssignLength = 18, RULE_columnAssignComment = 19,
      RULE_columnAssignDefaultValue = 20, RULE_columnAssignPrecision = 21, RULE_columnAssignScale = 22,
      RULE_indexAssignName = 23, RULE_indexAssignUnique = 24, RULE_indexAssignOrder = 25,
      RULE_indexAssignIndexColumns = 26, RULE_indexAssignIndexType = 27, RULE_indexColumnsArray = 28;
  public static final String[] ruleNames = {
      "hdbtableDefinition", "schemaNameProp", "temporaryProp", "tableTypeProp",
      "publicProp", "loggingTypeProp", "tableColumnsProp", "tableIndexesProp",
      "tablePrimaryKeyProp", "tablePrimaryKeyColumnsProp", "tablePrimaryKeyIndexTypeProp",
      "descriptionProp", "columnsObject", "indexesObject", "columnAssignName",
      "columnAssignSQLType", "columnAssignNullable", "columnAssignUnique", "columnAssignLength",
      "columnAssignComment", "columnAssignDefaultValue", "columnAssignPrecision",
      "columnAssignScale", "indexAssignName", "indexAssignUnique", "indexAssignOrder",
      "indexAssignIndexColumns", "indexAssignIndexType", "indexColumnsArray"
  };
  public static final String _serializedATN =
      "\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3-\u0139\4\2\t\2\4" +
          "\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t" +
          "\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22" +
          "\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31" +
          "\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\3\2\3\2\5\2?\n\2\3" +
          "\2\5\2B\n\2\3\2\5\2E\n\2\3\2\5\2H\n\2\3\2\3\2\5\2L\n\2\3\2\5\2O\n\2\3" +
          "\2\5\2R\n\2\3\2\5\2U\n\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3" +
          "\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7" +
          "\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\7\b\u0082\n\b" +
          "\f\b\16\b\u0085\13\b\5\b\u0087\n\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t" +
          "\3\t\3\t\7\t\u0094\n\t\f\t\16\t\u0097\13\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n" +
          "\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\7\13\u00a9\n\13\f\13\16\13\u00ac" +
          "\13\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3" +
          "\r\3\r\3\r\3\16\3\16\3\16\3\16\5\16\u00c4\n\16\3\16\5\16\u00c7\n\16\3" +
          "\16\5\16\u00ca\n\16\3\16\5\16\u00cd\n\16\3\16\5\16\u00d0\n\16\3\16\5\16" +
          "\u00d3\n\16\3\16\5\16\u00d6\n\16\3\16\3\16\3\17\3\17\3\17\3\17\5\17\u00de" +
          "\n\17\3\17\3\17\5\17\u00e2\n\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\21" +
          "\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23" +
          "\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26" +
          "\3\26\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31" +
          "\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\34\3\34" +
          "\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\36\7\36\u012f\n\36" +
          "\f\36\16\36\u0132\13\36\5\36\u0134\n\36\3\36\3\36\3\36\3\36\2\2\37\2\4" +
          "\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:\2\3\5\2\36" +
          "\36((++\u0132\2<\3\2\2\2\4V\3\2\2\2\6]\3\2\2\2\bd\3\2\2\2\nk\3\2\2\2\f" +
          "r\3\2\2\2\16y\3\2\2\2\20\u008b\3\2\2\2\22\u009b\3\2\2\2\24\u00a4\3\2\2" +
          "\2\26\u00af\3\2\2\2\30\u00b8\3\2\2\2\32\u00bf\3\2\2\2\34\u00d9\3\2\2\2" +
          "\36\u00e5\3\2\2\2 \u00ea\3\2\2\2\"\u00ef\3\2\2\2$\u00f4\3\2\2\2&\u00f9" +
          "\3\2\2\2(\u00fe\3\2\2\2*\u0103\3\2\2\2,\u0108\3\2\2\2.\u010d\3\2\2\2\60" +
          "\u0112\3\2\2\2\62\u0117\3\2\2\2\64\u011c\3\2\2\2\66\u0121\3\2\2\28\u0125" +
          "\3\2\2\2:\u012a\3\2\2\2<>\5\4\3\2=?\5\6\4\2>=\3\2\2\2>?\3\2\2\2?A\3\2" +
          "\2\2@B\5\b\5\2A@\3\2\2\2AB\3\2\2\2BD\3\2\2\2CE\5\n\6\2DC\3\2\2\2DE\3\2" +
          "\2\2EG\3\2\2\2FH\5\f\7\2GF\3\2\2\2GH\3\2\2\2HI\3\2\2\2IK\5\16\b\2JL\5" +
          "\20\t\2KJ\3\2\2\2KL\3\2\2\2LN\3\2\2\2MO\5\22\n\2NM\3\2\2\2NO\3\2\2\2O" +
          "Q\3\2\2\2PR\5\26\f\2QP\3\2\2\2QR\3\2\2\2RT\3\2\2\2SU\5\30\r\2TS\3\2\2" +
          "\2TU\3\2\2\2U\3\3\2\2\2VW\7 \2\2WX\7!\2\2XY\7\35\2\2YZ\7\"\2\2Z[\7\36" +
          "\2\2[\\\7#\2\2\\\5\3\2\2\2]^\7 \2\2^_\7!\2\2_`\7\34\2\2`a\7\"\2\2ab\7" +
          "%\2\2bc\7#\2\2c\7\3\2\2\2de\7 \2\2ef\7!\2\2fg\7\b\2\2gh\7\"\2\2hi\7)\2" +
          "\2ij\7#\2\2j\t\3\2\2\2kl\7 \2\2lm\7!\2\2mn\7\32\2\2no\7\"\2\2op\7%\2\2" +
          "pq\7#\2\2q\13\3\2\2\2rs\7 \2\2st\7!\2\2tu\7\3\2\2uv\7\"\2\2vw\7*\2\2w" +
          "x\7#\2\2x\r\3\2\2\2yz\7 \2\2z{\7!\2\2{|\7\25\2\2|}\7\"\2\2}\u0086\7\30" +
          "\2\2~\u0083\5\32\16\2\177\u0080\7\22\2\2\u0080\u0082\5\32\16\2\u0081\177" +
          "\3\2\2\2\u0082\u0085\3\2\2\2\u0083\u0081\3\2\2\2\u0083\u0084\3\2\2\2\u0084" +
          "\u0087\3\2\2\2\u0085\u0083\3\2\2\2\u0086~\3\2\2\2\u0086\u0087\3\2\2\2" +
          "\u0087\u0088\3\2\2\2\u0088\u0089\7\31\2\2\u0089\u008a\7#\2\2\u008a\17" +
          "\3\2\2\2\u008b\u008c\7 \2\2\u008c\u008d\7!\2\2\u008d\u008e\7\f\2\2\u008e" +
          "\u008f\7\"\2\2\u008f\u0090\7\30\2\2\u0090\u0095\5\34\17\2\u0091\u0092" +
          "\7\22\2\2\u0092\u0094\5\34\17\2\u0093\u0091\3\2\2\2\u0094\u0097\3\2\2" +
          "\2\u0095\u0093\3\2\2\2\u0095\u0096\3\2\2\2\u0096\u0098\3\2\2\2\u0097\u0095" +
          "\3\2\2\2\u0098\u0099\7\31\2\2\u0099\u009a\7#\2\2\u009a\21\3\2\2\2\u009b" +
          "\u009c\7 \2\2\u009c\u009d\7!\2\2\u009d\u009e\7\24\2\2\u009e\u009f\7!\2" +
          "\2\u009f\u00a0\7\16\2\2\u00a0\u00a1\7\"\2\2\u00a1\u00a2\5\24\13\2\u00a2" +
          "\u00a3\7#\2\2\u00a3\23\3\2\2\2\u00a4\u00a5\7\30\2\2\u00a5\u00aa\7\36\2" +
          "\2\u00a6\u00a7\7\22\2\2\u00a7\u00a9\7\36\2\2\u00a8\u00a6\3\2\2\2\u00a9" +
          "\u00ac\3\2\2\2\u00aa\u00a8\3\2\2\2\u00aa\u00ab\3\2\2\2\u00ab\u00ad\3\2" +
          "\2\2\u00ac\u00aa\3\2\2\2\u00ad\u00ae\7\31\2\2\u00ae\25\3\2\2\2\u00af\u00b0" +
          "\7 \2\2\u00b0\u00b1\7!\2\2\u00b1\u00b2\7\24\2\2\u00b2\u00b3\7!\2\2\u00b3" +
          "\u00b4\7\17\2\2\u00b4\u00b5\7\"\2\2\u00b5\u00b6\7\'\2\2\u00b6\u00b7\7" +
          "#\2\2\u00b7\27\3\2\2\2\u00b8\u00b9\7 \2\2\u00b9\u00ba\7!\2\2\u00ba\u00bb" +
          "\7\21\2\2\u00bb\u00bc\7\"\2\2\u00bc\u00bd\7\36\2\2\u00bd\u00be\7#\2\2" +
          "\u00be\31\3\2\2\2\u00bf\u00c0\7\t\2\2\u00c0\u00c1\5\36\20\2\u00c1\u00c3" +
          "\5 \21\2\u00c2\u00c4\5$\23\2\u00c3\u00c2\3\2\2\2\u00c3\u00c4\3\2\2\2\u00c4" +
          "\u00c6\3\2\2\2\u00c5\u00c7\5&\24\2\u00c6\u00c5\3\2\2\2\u00c6\u00c7\3\2" +
          "\2\2\u00c7\u00c9\3\2\2\2\u00c8\u00ca\5\"\22\2\u00c9\u00c8\3\2\2\2\u00c9" +
          "\u00ca\3\2\2\2\u00ca\u00cc\3\2\2\2\u00cb\u00cd\5(\25\2\u00cc\u00cb\3\2" +
          "\2\2\u00cc\u00cd\3\2\2\2\u00cd\u00cf\3\2\2\2\u00ce\u00d0\5*\26\2\u00cf" +
          "\u00ce\3\2\2\2\u00cf\u00d0\3\2\2\2\u00d0\u00d2\3\2\2\2\u00d1\u00d3\5," +
          "\27\2\u00d2\u00d1\3\2\2\2\u00d2\u00d3\3\2\2\2\u00d3\u00d5\3\2\2\2\u00d4" +
          "\u00d6\5.\30\2\u00d5\u00d4\3\2\2\2\u00d5\u00d6\3\2\2\2\u00d6\u00d7\3\2" +
          "\2\2\u00d7\u00d8\7\13\2\2\u00d8\33\3\2\2\2\u00d9\u00da\7\t\2\2\u00da\u00db" +
          "\5\60\31\2\u00db\u00dd\5\62\32\2\u00dc\u00de\5\64\33\2\u00dd\u00dc\3\2" +
          "\2\2\u00dd\u00de\3\2\2\2\u00de\u00df\3\2\2\2\u00df\u00e1\5\66\34\2\u00e0" +
          "\u00e2\58\35\2\u00e1\u00e0\3\2\2\2\u00e1\u00e2\3\2\2\2\u00e2\u00e3\3\2" +
          "\2\2\u00e3\u00e4\7\13\2\2\u00e4\35\3\2\2\2\u00e5\u00e6\7\5\2\2\u00e6\u00e7" +
          "\7\"\2\2\u00e7\u00e8\7\36\2\2\u00e8\u00e9\7#\2\2\u00e9\37\3\2\2\2\u00ea" +
          "\u00eb\7\4\2\2\u00eb\u00ec\7\"\2\2\u00ec\u00ed\7$\2\2\u00ed\u00ee\7#\2" +
          "\2\u00ee!\3\2\2\2\u00ef\u00f0\7\r\2\2\u00f0\u00f1\7\"\2\2\u00f1\u00f2" +
          "\7%\2\2\u00f2\u00f3\7#\2\2\u00f3#\3\2\2\2\u00f4\u00f5\7\23\2\2\u00f5\u00f6" +
          "\7\"\2\2\u00f6\u00f7\7%\2\2\u00f7\u00f8\7#\2\2\u00f8%\3\2\2\2\u00f9\u00fa" +
          "\7\6\2\2\u00fa\u00fb\7\"\2\2\u00fb\u00fc\7(\2\2\u00fc\u00fd\7#\2\2\u00fd" +
          "\'\3\2\2\2\u00fe\u00ff\7\33\2\2\u00ff\u0100\7\"\2\2\u0100\u0101\7\36\2" +
          "\2\u0101\u0102\7#\2\2\u0102)\3\2\2\2\u0103\u0104\7\27\2\2\u0104\u0105" +
          "\7\"\2\2\u0105\u0106\t\2\2\2\u0106\u0107\7#\2\2\u0107+\3\2\2\2\u0108\u0109" +
          "\7\26\2\2\u0109\u010a\7\"\2\2\u010a\u010b\7(\2\2\u010b\u010c\7#\2\2\u010c" +
          "-\3\2\2\2\u010d\u010e\7\7\2\2\u010e\u010f\7\"\2\2\u010f\u0110\7(\2\2\u0110" +
          "\u0111\7#\2\2\u0111/\3\2\2\2\u0112\u0113\7\5\2\2\u0113\u0114\7\"\2\2\u0114" +
          "\u0115\7\36\2\2\u0115\u0116\7#\2\2\u0116\61\3\2\2\2\u0117\u0118\7\23\2" +
          "\2\u0118\u0119\7\"\2\2\u0119\u011a\7%\2\2\u011a\u011b\7#\2\2\u011b\63" +
          "\3\2\2\2\u011c\u011d\7\n\2\2\u011d\u011e\7\"\2\2\u011e\u011f\7&\2\2\u011f" +
          "\u0120\7#\2\2\u0120\65\3\2\2\2\u0121\u0122\7\20\2\2\u0122\u0123\7\"\2" +
          "\2\u0123\u0124\5:\36\2\u0124\67\3\2\2\2\u0125\u0126\7\17\2\2\u0126\u0127" +
          "\7\"\2\2\u0127\u0128\7\'\2\2\u0128\u0129\7#\2\2\u01299\3\2\2\2\u012a\u0133" +
          "\7\30\2\2\u012b\u0130\7\36\2\2\u012c\u012d\7\22\2\2\u012d\u012f\7\36\2" +
          "\2\u012e\u012c\3\2\2\2\u012f\u0132\3\2\2\2\u0130\u012e\3\2\2\2\u0130\u0131" +
          "\3\2\2\2\u0131\u0134\3\2\2\2\u0132\u0130\3\2\2\2\u0133\u012b\3\2\2\2\u0133" +
          "\u0134\3\2\2\2\u0134\u0135\3\2\2\2\u0135\u0136\7\31\2\2\u0136\u0137\7" +
          "#\2\2\u0137;\3\2\2\2\31>ADGKNQT\u0083\u0086\u0095\u00aa\u00c3\u00c6\u00c9" +
          "\u00cc\u00cf\u00d2\u00d5\u00dd\u00e1\u0130\u0133";
  public static final ATN _ATN =
      new ATNDeserializer().deserialize(_serializedATN.toCharArray());
  protected static final DFA[] _decisionToDFA;
  protected static final PredictionContextCache _sharedContextCache =
      new PredictionContextCache();

  static {
    RuntimeMetaData.checkVersion("4.3", RuntimeMetaData.VERSION);
  }

  static {
    _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
    for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
      _decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
    }
  }

  public HdbtableParser(TokenStream input) {
    super(input);
    _interp = new ParserATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
  }

  @Override
  public String getGrammarFileName() {
    return "Hdbtable.g4";
  }

  @Override
  public String[] getTokenNames() {
    return tokenNames;
  }

  @Override
  public String[] getRuleNames() {
    return ruleNames;
  }

  @Override
  public String getSerializedATN() {
    return _serializedATN;
  }

  @Override
  public ATN getATN() {
    return _ATN;
  }

  public final HdbtableDefinitionContext hdbtableDefinition() throws RecognitionException {
    HdbtableDefinitionContext _localctx = new HdbtableDefinitionContext(_ctx, getState());
    enterRule(_localctx, 0, RULE_hdbtableDefinition);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(58);
        schemaNameProp();
        setState(60);
        switch (getInterpreter().adaptivePredict(_input, 0, _ctx)) {
          case 1: {
            setState(59);
            temporaryProp();
          }
          break;
        }
        setState(63);
        switch (getInterpreter().adaptivePredict(_input, 1, _ctx)) {
          case 1: {
            setState(62);
            tableTypeProp();
          }
          break;
        }
        setState(66);
        switch (getInterpreter().adaptivePredict(_input, 2, _ctx)) {
          case 1: {
            setState(65);
            publicProp();
          }
          break;
        }
        setState(69);
        switch (getInterpreter().adaptivePredict(_input, 3, _ctx)) {
          case 1: {
            setState(68);
            loggingTypeProp();
          }
          break;
        }
        setState(71);
        tableColumnsProp();
        setState(73);
        switch (getInterpreter().adaptivePredict(_input, 4, _ctx)) {
          case 1: {
            setState(72);
            tableIndexesProp();
          }
          break;
        }
        setState(76);
        switch (getInterpreter().adaptivePredict(_input, 5, _ctx)) {
          case 1: {
            setState(75);
            tablePrimaryKeyProp();
          }
          break;
        }
        setState(79);
        switch (getInterpreter().adaptivePredict(_input, 6, _ctx)) {
          case 1: {
            setState(78);
            tablePrimaryKeyIndexTypeProp();
          }
          break;
        }
        setState(82);
        _la = _input.LA(1);
        if (_la == TABLE) {
          {
            setState(81);
            descriptionProp();
          }
        }

      }
    } catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    } finally {
      exitRule();
    }
    return _localctx;
  }

  public final SchemaNamePropContext schemaNameProp() throws RecognitionException {
    SchemaNamePropContext _localctx = new SchemaNamePropContext(_ctx, getState());
    enterRule(_localctx, 2, RULE_schemaNameProp);
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(84);
        match(TABLE);
        setState(85);
        match(DOT);
        setState(86);
        match(T__0);
        setState(87);
        match(EQ);
        setState(88);
        match(STRING);
        setState(89);
        match(SEMICOLON);
      }
    } catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    } finally {
      exitRule();
    }
    return _localctx;
  }

  public final TemporaryPropContext temporaryProp() throws RecognitionException {
    TemporaryPropContext _localctx = new TemporaryPropContext(_ctx, getState());
    enterRule(_localctx, 4, RULE_temporaryProp);
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(91);
        match(TABLE);
        setState(92);
        match(DOT);
        setState(93);
        match(T__1);
        setState(94);
        match(EQ);
        setState(95);
        match(BOOLEAN);
        setState(96);
        match(SEMICOLON);
      }
    } catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    } finally {
      exitRule();
    }
    return _localctx;
  }

  public final TableTypePropContext tableTypeProp() throws RecognitionException {
    TableTypePropContext _localctx = new TableTypePropContext(_ctx, getState());
    enterRule(_localctx, 6, RULE_tableTypeProp);
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(98);
        match(TABLE);
        setState(99);
        match(DOT);
        setState(100);
        match(T__21);
        setState(101);
        match(EQ);
        setState(102);
        match(TABLETYPE);
        setState(103);
        match(SEMICOLON);
      }
    } catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    } finally {
      exitRule();
    }
    return _localctx;
  }

  public final PublicPropContext publicProp() throws RecognitionException {
    PublicPropContext _localctx = new PublicPropContext(_ctx, getState());
    enterRule(_localctx, 8, RULE_publicProp);
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(105);
        match(TABLE);
        setState(106);
        match(DOT);
        setState(107);
        match(T__3);
        setState(108);
        match(EQ);
        setState(109);
        match(BOOLEAN);
        setState(110);
        match(SEMICOLON);
      }
    } catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    } finally {
      exitRule();
    }
    return _localctx;
  }

  public final LoggingTypePropContext loggingTypeProp() throws RecognitionException {
    LoggingTypePropContext _localctx = new LoggingTypePropContext(_ctx, getState());
    enterRule(_localctx, 10, RULE_loggingTypeProp);
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(112);
        match(TABLE);
        setState(113);
        match(DOT);
        setState(114);
        match(T__26);
        setState(115);
        match(EQ);
        setState(116);
        match(TABLELOGGINGTYPE);
        setState(117);
        match(SEMICOLON);
      }
    } catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    } finally {
      exitRule();
    }
    return _localctx;
  }

  public final TableColumnsPropContext tableColumnsProp() throws RecognitionException {
    TableColumnsPropContext _localctx = new TableColumnsPropContext(_ctx, getState());
    enterRule(_localctx, 12, RULE_tableColumnsProp);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(119);
        match(TABLE);
        setState(120);
        match(DOT);
        setState(121);
        match(T__8);
        setState(122);
        match(EQ);
        setState(123);
        match(T__5);
        setState(132);
        _la = _input.LA(1);
        if (_la == T__20) {
          {
            setState(124);
            columnsObject();
            setState(129);
            _errHandler.sync(this);
            _la = _input.LA(1);
            while (_la == T__11) {
              {
                {
                  setState(125);
                  match(T__11);
                  setState(126);
                  columnsObject();
                }
              }
              setState(131);
              _errHandler.sync(this);
              _la = _input.LA(1);
            }
          }
        }

        setState(134);
        match(T__4);
        setState(135);
        match(SEMICOLON);
      }
    } catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    } finally {
      exitRule();
    }
    return _localctx;
  }

  public final TableIndexesPropContext tableIndexesProp() throws RecognitionException {
    TableIndexesPropContext _localctx = new TableIndexesPropContext(_ctx, getState());
    enterRule(_localctx, 14, RULE_tableIndexesProp);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(137);
        match(TABLE);
        setState(138);
        match(DOT);
        setState(139);
        match(T__17);
        setState(140);
        match(EQ);
        setState(141);
        match(T__5);
        setState(142);
        indexesObject();
        setState(147);
        _errHandler.sync(this);
        _la = _input.LA(1);
        while (_la == T__11) {
          {
            {
              setState(143);
              match(T__11);
              setState(144);
              indexesObject();
            }
          }
          setState(149);
          _errHandler.sync(this);
          _la = _input.LA(1);
        }
        setState(150);
        match(T__4);
        setState(151);
        match(SEMICOLON);
      }
    } catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    } finally {
      exitRule();
    }
    return _localctx;
  }

  public final TablePrimaryKeyPropContext tablePrimaryKeyProp() throws RecognitionException {
    TablePrimaryKeyPropContext _localctx = new TablePrimaryKeyPropContext(_ctx, getState());
    enterRule(_localctx, 16, RULE_tablePrimaryKeyProp);
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(153);
        match(TABLE);
        setState(154);
        match(DOT);
        setState(155);
        match(T__9);
        setState(156);
        match(DOT);
        setState(157);
        match(T__15);
        setState(158);
        match(EQ);
        setState(159);
        tablePrimaryKeyColumnsProp();
        setState(160);
        match(SEMICOLON);
      }
    } catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    } finally {
      exitRule();
    }
    return _localctx;
  }

  public final TablePrimaryKeyColumnsPropContext tablePrimaryKeyColumnsProp() throws RecognitionException {
    TablePrimaryKeyColumnsPropContext _localctx = new TablePrimaryKeyColumnsPropContext(_ctx, getState());
    enterRule(_localctx, 18, RULE_tablePrimaryKeyColumnsProp);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(162);
        match(T__5);
        setState(163);
        match(STRING);
        setState(168);
        _errHandler.sync(this);
        _la = _input.LA(1);
        while (_la == T__11) {
          {
            {
              setState(164);
              match(T__11);
              setState(165);
              match(STRING);
            }
          }
          setState(170);
          _errHandler.sync(this);
          _la = _input.LA(1);
        }
        setState(171);
        match(T__4);
      }
    } catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    } finally {
      exitRule();
    }
    return _localctx;
  }

  public final TablePrimaryKeyIndexTypePropContext tablePrimaryKeyIndexTypeProp() throws RecognitionException {
    TablePrimaryKeyIndexTypePropContext _localctx = new TablePrimaryKeyIndexTypePropContext(_ctx, getState());
    enterRule(_localctx, 20, RULE_tablePrimaryKeyIndexTypeProp);
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(173);
        match(TABLE);
        setState(174);
        match(DOT);
        setState(175);
        match(T__9);
        setState(176);
        match(DOT);
        setState(177);
        match(T__14);
        setState(178);
        match(EQ);
        setState(179);
        match(INDEXTYPE);
        setState(180);
        match(SEMICOLON);
      }
    } catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    } finally {
      exitRule();
    }
    return _localctx;
  }

  public final DescriptionPropContext descriptionProp() throws RecognitionException {
    DescriptionPropContext _localctx = new DescriptionPropContext(_ctx, getState());
    enterRule(_localctx, 22, RULE_descriptionProp);
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(182);
        match(TABLE);
        setState(183);
        match(DOT);
        setState(184);
        match(T__12);
        setState(185);
        match(EQ);
        setState(186);
        match(STRING);
        setState(187);
        match(SEMICOLON);
      }
    } catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    } finally {
      exitRule();
    }
    return _localctx;
  }

  public final ColumnsObjectContext columnsObject() throws RecognitionException {
    ColumnsObjectContext _localctx = new ColumnsObjectContext(_ctx, getState());
    enterRule(_localctx, 24, RULE_columnsObject);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(189);
        match(T__20);
        setState(190);
        columnAssignName();
        setState(191);
        columnAssignSQLType();
        setState(193);
        _la = _input.LA(1);
        if (_la == T__10) {
          {
            setState(192);
            columnAssignUnique();
          }
        }

        setState(196);
        _la = _input.LA(1);
        if (_la == T__23) {
          {
            setState(195);
            columnAssignLength();
          }
        }

        setState(199);
        _la = _input.LA(1);
        if (_la == T__16) {
          {
            setState(198);
            columnAssignNullable();
          }
        }

        setState(202);
        _la = _input.LA(1);
        if (_la == T__2) {
          {
            setState(201);
            columnAssignComment();
          }
        }

        setState(205);
        _la = _input.LA(1);
        if (_la == T__6) {
          {
            setState(204);
            columnAssignDefaultValue();
          }
        }

        setState(208);
        _la = _input.LA(1);
        if (_la == T__7) {
          {
            setState(207);
            columnAssignPrecision();
          }
        }

        setState(211);
        _la = _input.LA(1);
        if (_la == T__22) {
          {
            setState(210);
            columnAssignScale();
          }
        }

        setState(213);
        match(T__18);
      }
    } catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    } finally {
      exitRule();
    }
    return _localctx;
  }

  public final IndexesObjectContext indexesObject() throws RecognitionException {
    IndexesObjectContext _localctx = new IndexesObjectContext(_ctx, getState());
    enterRule(_localctx, 26, RULE_indexesObject);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(215);
        match(T__20);
        setState(216);
        indexAssignName();
        setState(217);
        indexAssignUnique();
        setState(219);
        _la = _input.LA(1);
        if (_la == T__19) {
          {
            setState(218);
            indexAssignOrder();
          }
        }

        setState(221);
        indexAssignIndexColumns();
        setState(223);
        _la = _input.LA(1);
        if (_la == T__14) {
          {
            setState(222);
            indexAssignIndexType();
          }
        }

        setState(225);
        match(T__18);
      }
    } catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    } finally {
      exitRule();
    }
    return _localctx;
  }

  public final ColumnAssignNameContext columnAssignName() throws RecognitionException {
    ColumnAssignNameContext _localctx = new ColumnAssignNameContext(_ctx, getState());
    enterRule(_localctx, 28, RULE_columnAssignName);
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(227);
        match(T__24);
        setState(228);
        match(EQ);
        setState(229);
        match(STRING);
        setState(230);
        match(SEMICOLON);
      }
    } catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    } finally {
      exitRule();
    }
    return _localctx;
  }

  public final ColumnAssignSQLTypeContext columnAssignSQLType() throws RecognitionException {
    ColumnAssignSQLTypeContext _localctx = new ColumnAssignSQLTypeContext(_ctx, getState());
    enterRule(_localctx, 30, RULE_columnAssignSQLType);
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(232);
        match(T__25);
        setState(233);
        match(EQ);
        setState(234);
        match(SQLTYPES);
        setState(235);
        match(SEMICOLON);
      }
    } catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    } finally {
      exitRule();
    }
    return _localctx;
  }

  public final ColumnAssignNullableContext columnAssignNullable() throws RecognitionException {
    ColumnAssignNullableContext _localctx = new ColumnAssignNullableContext(_ctx, getState());
    enterRule(_localctx, 32, RULE_columnAssignNullable);
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(237);
        match(T__16);
        setState(238);
        match(EQ);
        setState(239);
        match(BOOLEAN);
        setState(240);
        match(SEMICOLON);
      }
    } catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    } finally {
      exitRule();
    }
    return _localctx;
  }

  public final ColumnAssignUniqueContext columnAssignUnique() throws RecognitionException {
    ColumnAssignUniqueContext _localctx = new ColumnAssignUniqueContext(_ctx, getState());
    enterRule(_localctx, 34, RULE_columnAssignUnique);
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(242);
        match(T__10);
        setState(243);
        match(EQ);
        setState(244);
        match(BOOLEAN);
        setState(245);
        match(SEMICOLON);
      }
    } catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    } finally {
      exitRule();
    }
    return _localctx;
  }

  public final ColumnAssignLengthContext columnAssignLength() throws RecognitionException {
    ColumnAssignLengthContext _localctx = new ColumnAssignLengthContext(_ctx, getState());
    enterRule(_localctx, 36, RULE_columnAssignLength);
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(247);
        match(T__23);
        setState(248);
        match(EQ);
        setState(249);
        match(INT);
        setState(250);
        match(SEMICOLON);
      }
    } catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    } finally {
      exitRule();
    }
    return _localctx;
  }

  public final ColumnAssignCommentContext columnAssignComment() throws RecognitionException {
    ColumnAssignCommentContext _localctx = new ColumnAssignCommentContext(_ctx, getState());
    enterRule(_localctx, 38, RULE_columnAssignComment);
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(252);
        match(T__2);
        setState(253);
        match(EQ);
        setState(254);
        match(STRING);
        setState(255);
        match(SEMICOLON);
      }
    } catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    } finally {
      exitRule();
    }
    return _localctx;
  }

  public final ColumnAssignDefaultValueContext columnAssignDefaultValue() throws RecognitionException {
    ColumnAssignDefaultValueContext _localctx = new ColumnAssignDefaultValueContext(_ctx, getState());
    enterRule(_localctx, 40, RULE_columnAssignDefaultValue);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(257);
        match(T__6);
        setState(258);
        match(EQ);
        setState(259);
        _la = _input.LA(1);
        if (!((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << STRING) | (1L << INT) | (1L << DATETIMEDEFAULTVALUES))) != 0))) {
          _errHandler.recoverInline(this);
        }
        consume();
        setState(260);
        match(SEMICOLON);
      }
    } catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    } finally {
      exitRule();
    }
    return _localctx;
  }

  public final ColumnAssignPrecisionContext columnAssignPrecision() throws RecognitionException {
    ColumnAssignPrecisionContext _localctx = new ColumnAssignPrecisionContext(_ctx, getState());
    enterRule(_localctx, 42, RULE_columnAssignPrecision);
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(262);
        match(T__7);
        setState(263);
        match(EQ);
        setState(264);
        match(INT);
        setState(265);
        match(SEMICOLON);
      }
    } catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    } finally {
      exitRule();
    }
    return _localctx;
  }

  public final ColumnAssignScaleContext columnAssignScale() throws RecognitionException {
    ColumnAssignScaleContext _localctx = new ColumnAssignScaleContext(_ctx, getState());
    enterRule(_localctx, 44, RULE_columnAssignScale);
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(267);
        match(T__22);
        setState(268);
        match(EQ);
        setState(269);
        match(INT);
        setState(270);
        match(SEMICOLON);
      }
    } catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    } finally {
      exitRule();
    }
    return _localctx;
  }

  public final IndexAssignNameContext indexAssignName() throws RecognitionException {
    IndexAssignNameContext _localctx = new IndexAssignNameContext(_ctx, getState());
    enterRule(_localctx, 46, RULE_indexAssignName);
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(272);
        match(T__24);
        setState(273);
        match(EQ);
        setState(274);
        match(STRING);
        setState(275);
        match(SEMICOLON);
      }
    } catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    } finally {
      exitRule();
    }
    return _localctx;
  }

  public final IndexAssignUniqueContext indexAssignUnique() throws RecognitionException {
    IndexAssignUniqueContext _localctx = new IndexAssignUniqueContext(_ctx, getState());
    enterRule(_localctx, 48, RULE_indexAssignUnique);
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(277);
        match(T__10);
        setState(278);
        match(EQ);
        setState(279);
        match(BOOLEAN);
        setState(280);
        match(SEMICOLON);
      }
    } catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    } finally {
      exitRule();
    }
    return _localctx;
  }

  public final IndexAssignOrderContext indexAssignOrder() throws RecognitionException {
    IndexAssignOrderContext _localctx = new IndexAssignOrderContext(_ctx, getState());
    enterRule(_localctx, 50, RULE_indexAssignOrder);
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(282);
        match(T__19);
        setState(283);
        match(EQ);
        setState(284);
        match(ORDER);
        setState(285);
        match(SEMICOLON);
      }
    } catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    } finally {
      exitRule();
    }
    return _localctx;
  }

  public final IndexAssignIndexColumnsContext indexAssignIndexColumns() throws RecognitionException {
    IndexAssignIndexColumnsContext _localctx = new IndexAssignIndexColumnsContext(_ctx, getState());
    enterRule(_localctx, 52, RULE_indexAssignIndexColumns);
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(287);
        match(T__13);
        setState(288);
        match(EQ);
        setState(289);
        indexColumnsArray();
      }
    } catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    } finally {
      exitRule();
    }
    return _localctx;
  }

  public final IndexAssignIndexTypeContext indexAssignIndexType() throws RecognitionException {
    IndexAssignIndexTypeContext _localctx = new IndexAssignIndexTypeContext(_ctx, getState());
    enterRule(_localctx, 54, RULE_indexAssignIndexType);
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(291);
        match(T__14);
        setState(292);
        match(EQ);
        setState(293);
        match(INDEXTYPE);
        setState(294);
        match(SEMICOLON);
      }
    } catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    } finally {
      exitRule();
    }
    return _localctx;
  }

  public final IndexColumnsArrayContext indexColumnsArray() throws RecognitionException {
    IndexColumnsArrayContext _localctx = new IndexColumnsArrayContext(_ctx, getState());
    enterRule(_localctx, 56, RULE_indexColumnsArray);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(296);
        match(T__5);
        setState(305);
        _la = _input.LA(1);
        if (_la == STRING) {
          {
            setState(297);
            match(STRING);
            setState(302);
            _errHandler.sync(this);
            _la = _input.LA(1);
            while (_la == T__11) {
              {
                {
                  setState(298);
                  match(T__11);
                  setState(299);
                  match(STRING);
                }
              }
              setState(304);
              _errHandler.sync(this);
              _la = _input.LA(1);
            }
          }
        }

        setState(307);
        match(T__4);
        setState(308);
        match(SEMICOLON);
      }
    } catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    } finally {
      exitRule();
    }
    return _localctx;
  }

  public static class HdbtableDefinitionContext extends ParserRuleContext {

    public HdbtableDefinitionContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }

    public LoggingTypePropContext loggingTypeProp() {
      return getRuleContext(LoggingTypePropContext.class, 0);
    }

    public PublicPropContext publicProp() {
      return getRuleContext(PublicPropContext.class, 0);
    }

    public TableIndexesPropContext tableIndexesProp() {
      return getRuleContext(TableIndexesPropContext.class, 0);
    }

    public TemporaryPropContext temporaryProp() {
      return getRuleContext(TemporaryPropContext.class, 0);
    }

    public DescriptionPropContext descriptionProp() {
      return getRuleContext(DescriptionPropContext.class, 0);
    }

    public TableTypePropContext tableTypeProp() {
      return getRuleContext(TableTypePropContext.class, 0);
    }

    public TableColumnsPropContext tableColumnsProp() {
      return getRuleContext(TableColumnsPropContext.class, 0);
    }

    public TablePrimaryKeyIndexTypePropContext tablePrimaryKeyIndexTypeProp() {
      return getRuleContext(TablePrimaryKeyIndexTypePropContext.class, 0);
    }

    public SchemaNamePropContext schemaNameProp() {
      return getRuleContext(SchemaNamePropContext.class, 0);
    }

    public TablePrimaryKeyPropContext tablePrimaryKeyProp() {
      return getRuleContext(TablePrimaryKeyPropContext.class, 0);
    }

    @Override
    public int getRuleIndex() {
      return RULE_hdbtableDefinition;
    }

    @Override
    public void enterRule(ParseTreeListener listener) {
      if (listener instanceof HdbtableListener) {
        ((HdbtableListener) listener).enterHdbtableDefinition(this);
      }
    }

    @Override
    public void exitRule(ParseTreeListener listener) {
      if (listener instanceof HdbtableListener) {
        ((HdbtableListener) listener).exitHdbtableDefinition(this);
      }
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof HdbtableVisitor) {
        return ((HdbtableVisitor<? extends T>) visitor).visitHdbtableDefinition(this);
      } else {
        return visitor.visitChildren(this);
      }
    }
  }

  public static class SchemaNamePropContext extends ParserRuleContext {

    public SchemaNamePropContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }

    public TerminalNode DOT() {
      return getToken(HdbtableParser.DOT, 0);
    }

    public TerminalNode SEMICOLON() {
      return getToken(HdbtableParser.SEMICOLON, 0);
    }

    public TerminalNode EQ() {
      return getToken(HdbtableParser.EQ, 0);
    }

    public TerminalNode STRING() {
      return getToken(HdbtableParser.STRING, 0);
    }

    public TerminalNode TABLE() {
      return getToken(HdbtableParser.TABLE, 0);
    }

    @Override
    public int getRuleIndex() {
      return RULE_schemaNameProp;
    }

    @Override
    public void enterRule(ParseTreeListener listener) {
      if (listener instanceof HdbtableListener) {
        ((HdbtableListener) listener).enterSchemaNameProp(this);
      }
    }

    @Override
    public void exitRule(ParseTreeListener listener) {
      if (listener instanceof HdbtableListener) {
        ((HdbtableListener) listener).exitSchemaNameProp(this);
      }
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof HdbtableVisitor) {
        return ((HdbtableVisitor<? extends T>) visitor).visitSchemaNameProp(this);
      } else {
        return visitor.visitChildren(this);
      }
    }
  }

  public static class TemporaryPropContext extends ParserRuleContext {

    public TemporaryPropContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }

    public TerminalNode DOT() {
      return getToken(HdbtableParser.DOT, 0);
    }

    public TerminalNode SEMICOLON() {
      return getToken(HdbtableParser.SEMICOLON, 0);
    }

    public TerminalNode EQ() {
      return getToken(HdbtableParser.EQ, 0);
    }

    public TerminalNode BOOLEAN() {
      return getToken(HdbtableParser.BOOLEAN, 0);
    }

    public TerminalNode TABLE() {
      return getToken(HdbtableParser.TABLE, 0);
    }

    @Override
    public int getRuleIndex() {
      return RULE_temporaryProp;
    }

    @Override
    public void enterRule(ParseTreeListener listener) {
      if (listener instanceof HdbtableListener) {
        ((HdbtableListener) listener).enterTemporaryProp(this);
      }
    }

    @Override
    public void exitRule(ParseTreeListener listener) {
      if (listener instanceof HdbtableListener) {
        ((HdbtableListener) listener).exitTemporaryProp(this);
      }
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof HdbtableVisitor) {
        return ((HdbtableVisitor<? extends T>) visitor).visitTemporaryProp(this);
      } else {
        return visitor.visitChildren(this);
      }
    }
  }

  public static class TableTypePropContext extends ParserRuleContext {

    public TableTypePropContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }

    public TerminalNode TABLETYPE() {
      return getToken(HdbtableParser.TABLETYPE, 0);
    }

    public TerminalNode DOT() {
      return getToken(HdbtableParser.DOT, 0);
    }

    public TerminalNode SEMICOLON() {
      return getToken(HdbtableParser.SEMICOLON, 0);
    }

    public TerminalNode EQ() {
      return getToken(HdbtableParser.EQ, 0);
    }

    public TerminalNode TABLE() {
      return getToken(HdbtableParser.TABLE, 0);
    }

    @Override
    public int getRuleIndex() {
      return RULE_tableTypeProp;
    }

    @Override
    public void enterRule(ParseTreeListener listener) {
      if (listener instanceof HdbtableListener) {
        ((HdbtableListener) listener).enterTableTypeProp(this);
      }
    }

    @Override
    public void exitRule(ParseTreeListener listener) {
      if (listener instanceof HdbtableListener) {
        ((HdbtableListener) listener).exitTableTypeProp(this);
      }
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof HdbtableVisitor) {
        return ((HdbtableVisitor<? extends T>) visitor).visitTableTypeProp(this);
      } else {
        return visitor.visitChildren(this);
      }
    }
  }

  public static class PublicPropContext extends ParserRuleContext {

    public PublicPropContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }

    public TerminalNode DOT() {
      return getToken(HdbtableParser.DOT, 0);
    }

    public TerminalNode SEMICOLON() {
      return getToken(HdbtableParser.SEMICOLON, 0);
    }

    public TerminalNode EQ() {
      return getToken(HdbtableParser.EQ, 0);
    }

    public TerminalNode BOOLEAN() {
      return getToken(HdbtableParser.BOOLEAN, 0);
    }

    public TerminalNode TABLE() {
      return getToken(HdbtableParser.TABLE, 0);
    }

    @Override
    public int getRuleIndex() {
      return RULE_publicProp;
    }

    @Override
    public void enterRule(ParseTreeListener listener) {
      if (listener instanceof HdbtableListener) {
        ((HdbtableListener) listener).enterPublicProp(this);
      }
    }

    @Override
    public void exitRule(ParseTreeListener listener) {
      if (listener instanceof HdbtableListener) {
        ((HdbtableListener) listener).exitPublicProp(this);
      }
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof HdbtableVisitor) {
        return ((HdbtableVisitor<? extends T>) visitor).visitPublicProp(this);
      } else {
        return visitor.visitChildren(this);
      }
    }
  }

  public static class LoggingTypePropContext extends ParserRuleContext {

    public LoggingTypePropContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }

    public TerminalNode DOT() {
      return getToken(HdbtableParser.DOT, 0);
    }

    public TerminalNode SEMICOLON() {
      return getToken(HdbtableParser.SEMICOLON, 0);
    }

    public TerminalNode TABLELOGGINGTYPE() {
      return getToken(HdbtableParser.TABLELOGGINGTYPE, 0);
    }

    public TerminalNode EQ() {
      return getToken(HdbtableParser.EQ, 0);
    }

    public TerminalNode TABLE() {
      return getToken(HdbtableParser.TABLE, 0);
    }

    @Override
    public int getRuleIndex() {
      return RULE_loggingTypeProp;
    }

    @Override
    public void enterRule(ParseTreeListener listener) {
      if (listener instanceof HdbtableListener) {
        ((HdbtableListener) listener).enterLoggingTypeProp(this);
      }
    }

    @Override
    public void exitRule(ParseTreeListener listener) {
      if (listener instanceof HdbtableListener) {
        ((HdbtableListener) listener).exitLoggingTypeProp(this);
      }
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof HdbtableVisitor) {
        return ((HdbtableVisitor<? extends T>) visitor).visitLoggingTypeProp(this);
      } else {
        return visitor.visitChildren(this);
      }
    }
  }

  public static class TableColumnsPropContext extends ParserRuleContext {

    public TableColumnsPropContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }

    public TerminalNode DOT() {
      return getToken(HdbtableParser.DOT, 0);
    }

    public TerminalNode SEMICOLON() {
      return getToken(HdbtableParser.SEMICOLON, 0);
    }

    public ColumnsObjectContext columnsObject(int i) {
      return getRuleContext(ColumnsObjectContext.class, i);
    }

    public List<ColumnsObjectContext> columnsObject() {
      return getRuleContexts(ColumnsObjectContext.class);
    }

    public TerminalNode EQ() {
      return getToken(HdbtableParser.EQ, 0);
    }

    public TerminalNode TABLE() {
      return getToken(HdbtableParser.TABLE, 0);
    }

    @Override
    public int getRuleIndex() {
      return RULE_tableColumnsProp;
    }

    @Override
    public void enterRule(ParseTreeListener listener) {
      if (listener instanceof HdbtableListener) {
        ((HdbtableListener) listener).enterTableColumnsProp(this);
      }
    }

    @Override
    public void exitRule(ParseTreeListener listener) {
      if (listener instanceof HdbtableListener) {
        ((HdbtableListener) listener).exitTableColumnsProp(this);
      }
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof HdbtableVisitor) {
        return ((HdbtableVisitor<? extends T>) visitor).visitTableColumnsProp(this);
      } else {
        return visitor.visitChildren(this);
      }
    }
  }

  public static class TableIndexesPropContext extends ParserRuleContext {

    public TableIndexesPropContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }

    public List<IndexesObjectContext> indexesObject() {
      return getRuleContexts(IndexesObjectContext.class);
    }

    public TerminalNode DOT() {
      return getToken(HdbtableParser.DOT, 0);
    }

    public TerminalNode SEMICOLON() {
      return getToken(HdbtableParser.SEMICOLON, 0);
    }

    public TerminalNode EQ() {
      return getToken(HdbtableParser.EQ, 0);
    }

    public IndexesObjectContext indexesObject(int i) {
      return getRuleContext(IndexesObjectContext.class, i);
    }

    public TerminalNode TABLE() {
      return getToken(HdbtableParser.TABLE, 0);
    }

    @Override
    public int getRuleIndex() {
      return RULE_tableIndexesProp;
    }

    @Override
    public void enterRule(ParseTreeListener listener) {
      if (listener instanceof HdbtableListener) {
        ((HdbtableListener) listener).enterTableIndexesProp(this);
      }
    }

    @Override
    public void exitRule(ParseTreeListener listener) {
      if (listener instanceof HdbtableListener) {
        ((HdbtableListener) listener).exitTableIndexesProp(this);
      }
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof HdbtableVisitor) {
        return ((HdbtableVisitor<? extends T>) visitor).visitTableIndexesProp(this);
      } else {
        return visitor.visitChildren(this);
      }
    }
  }

  public static class TablePrimaryKeyPropContext extends ParserRuleContext {

    public TablePrimaryKeyPropContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }

    public List<TerminalNode> DOT() {
      return getTokens(HdbtableParser.DOT);
    }

    public TerminalNode SEMICOLON() {
      return getToken(HdbtableParser.SEMICOLON, 0);
    }

    public TerminalNode EQ() {
      return getToken(HdbtableParser.EQ, 0);
    }

    public TablePrimaryKeyColumnsPropContext tablePrimaryKeyColumnsProp() {
      return getRuleContext(TablePrimaryKeyColumnsPropContext.class, 0);
    }

    public TerminalNode TABLE() {
      return getToken(HdbtableParser.TABLE, 0);
    }

    public TerminalNode DOT(int i) {
      return getToken(HdbtableParser.DOT, i);
    }

    @Override
    public int getRuleIndex() {
      return RULE_tablePrimaryKeyProp;
    }

    @Override
    public void enterRule(ParseTreeListener listener) {
      if (listener instanceof HdbtableListener) {
        ((HdbtableListener) listener).enterTablePrimaryKeyProp(this);
      }
    }

    @Override
    public void exitRule(ParseTreeListener listener) {
      if (listener instanceof HdbtableListener) {
        ((HdbtableListener) listener).exitTablePrimaryKeyProp(this);
      }
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof HdbtableVisitor) {
        return ((HdbtableVisitor<? extends T>) visitor).visitTablePrimaryKeyProp(this);
      } else {
        return visitor.visitChildren(this);
      }
    }
  }

  public static class TablePrimaryKeyColumnsPropContext extends ParserRuleContext {

    public TablePrimaryKeyColumnsPropContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }

    public TerminalNode STRING(int i) {
      return getToken(HdbtableParser.STRING, i);
    }

    public List<TerminalNode> STRING() {
      return getTokens(HdbtableParser.STRING);
    }

    @Override
    public int getRuleIndex() {
      return RULE_tablePrimaryKeyColumnsProp;
    }

    @Override
    public void enterRule(ParseTreeListener listener) {
      if (listener instanceof HdbtableListener) {
        ((HdbtableListener) listener).enterTablePrimaryKeyColumnsProp(this);
      }
    }

    @Override
    public void exitRule(ParseTreeListener listener) {
      if (listener instanceof HdbtableListener) {
        ((HdbtableListener) listener).exitTablePrimaryKeyColumnsProp(this);
      }
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof HdbtableVisitor) {
        return ((HdbtableVisitor<? extends T>) visitor).visitTablePrimaryKeyColumnsProp(this);
      } else {
        return visitor.visitChildren(this);
      }
    }
  }

  public static class TablePrimaryKeyIndexTypePropContext extends ParserRuleContext {

    public TablePrimaryKeyIndexTypePropContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }

    public List<TerminalNode> DOT() {
      return getTokens(HdbtableParser.DOT);
    }

    public TerminalNode SEMICOLON() {
      return getToken(HdbtableParser.SEMICOLON, 0);
    }

    public TerminalNode INDEXTYPE() {
      return getToken(HdbtableParser.INDEXTYPE, 0);
    }

    public TerminalNode EQ() {
      return getToken(HdbtableParser.EQ, 0);
    }

    public TerminalNode TABLE() {
      return getToken(HdbtableParser.TABLE, 0);
    }

    public TerminalNode DOT(int i) {
      return getToken(HdbtableParser.DOT, i);
    }

    @Override
    public int getRuleIndex() {
      return RULE_tablePrimaryKeyIndexTypeProp;
    }

    @Override
    public void enterRule(ParseTreeListener listener) {
      if (listener instanceof HdbtableListener) {
        ((HdbtableListener) listener).enterTablePrimaryKeyIndexTypeProp(this);
      }
    }

    @Override
    public void exitRule(ParseTreeListener listener) {
      if (listener instanceof HdbtableListener) {
        ((HdbtableListener) listener).exitTablePrimaryKeyIndexTypeProp(this);
      }
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof HdbtableVisitor) {
        return ((HdbtableVisitor<? extends T>) visitor).visitTablePrimaryKeyIndexTypeProp(this);
      } else {
        return visitor.visitChildren(this);
      }
    }
  }

  public static class DescriptionPropContext extends ParserRuleContext {

    public DescriptionPropContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }

    public TerminalNode DOT() {
      return getToken(HdbtableParser.DOT, 0);
    }

    public TerminalNode SEMICOLON() {
      return getToken(HdbtableParser.SEMICOLON, 0);
    }

    public TerminalNode EQ() {
      return getToken(HdbtableParser.EQ, 0);
    }

    public TerminalNode STRING() {
      return getToken(HdbtableParser.STRING, 0);
    }

    public TerminalNode TABLE() {
      return getToken(HdbtableParser.TABLE, 0);
    }

    @Override
    public int getRuleIndex() {
      return RULE_descriptionProp;
    }

    @Override
    public void enterRule(ParseTreeListener listener) {
      if (listener instanceof HdbtableListener) {
        ((HdbtableListener) listener).enterDescriptionProp(this);
      }
    }

    @Override
    public void exitRule(ParseTreeListener listener) {
      if (listener instanceof HdbtableListener) {
        ((HdbtableListener) listener).exitDescriptionProp(this);
      }
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof HdbtableVisitor) {
        return ((HdbtableVisitor<? extends T>) visitor).visitDescriptionProp(this);
      } else {
        return visitor.visitChildren(this);
      }
    }
  }

  public static class ColumnsObjectContext extends ParserRuleContext {

    public ColumnsObjectContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }

    public ColumnAssignLengthContext columnAssignLength() {
      return getRuleContext(ColumnAssignLengthContext.class, 0);
    }

    public ColumnAssignDefaultValueContext columnAssignDefaultValue() {
      return getRuleContext(ColumnAssignDefaultValueContext.class, 0);
    }

    public ColumnAssignSQLTypeContext columnAssignSQLType() {
      return getRuleContext(ColumnAssignSQLTypeContext.class, 0);
    }

    public ColumnAssignPrecisionContext columnAssignPrecision() {
      return getRuleContext(ColumnAssignPrecisionContext.class, 0);
    }

    public ColumnAssignNameContext columnAssignName() {
      return getRuleContext(ColumnAssignNameContext.class, 0);
    }

    public ColumnAssignScaleContext columnAssignScale() {
      return getRuleContext(ColumnAssignScaleContext.class, 0);
    }

    public ColumnAssignNullableContext columnAssignNullable() {
      return getRuleContext(ColumnAssignNullableContext.class, 0);
    }

    public ColumnAssignUniqueContext columnAssignUnique() {
      return getRuleContext(ColumnAssignUniqueContext.class, 0);
    }

    public ColumnAssignCommentContext columnAssignComment() {
      return getRuleContext(ColumnAssignCommentContext.class, 0);
    }

    @Override
    public int getRuleIndex() {
      return RULE_columnsObject;
    }

    @Override
    public void enterRule(ParseTreeListener listener) {
      if (listener instanceof HdbtableListener) {
        ((HdbtableListener) listener).enterColumnsObject(this);
      }
    }

    @Override
    public void exitRule(ParseTreeListener listener) {
      if (listener instanceof HdbtableListener) {
        ((HdbtableListener) listener).exitColumnsObject(this);
      }
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof HdbtableVisitor) {
        return ((HdbtableVisitor<? extends T>) visitor).visitColumnsObject(this);
      } else {
        return visitor.visitChildren(this);
      }
    }
  }

  public static class IndexesObjectContext extends ParserRuleContext {

    public IndexesObjectContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }

    public IndexAssignOrderContext indexAssignOrder() {
      return getRuleContext(IndexAssignOrderContext.class, 0);
    }

    public IndexAssignIndexTypeContext indexAssignIndexType() {
      return getRuleContext(IndexAssignIndexTypeContext.class, 0);
    }

    public IndexAssignUniqueContext indexAssignUnique() {
      return getRuleContext(IndexAssignUniqueContext.class, 0);
    }

    public IndexAssignIndexColumnsContext indexAssignIndexColumns() {
      return getRuleContext(IndexAssignIndexColumnsContext.class, 0);
    }

    public IndexAssignNameContext indexAssignName() {
      return getRuleContext(IndexAssignNameContext.class, 0);
    }

    @Override
    public int getRuleIndex() {
      return RULE_indexesObject;
    }

    @Override
    public void enterRule(ParseTreeListener listener) {
      if (listener instanceof HdbtableListener) {
        ((HdbtableListener) listener).enterIndexesObject(this);
      }
    }

    @Override
    public void exitRule(ParseTreeListener listener) {
      if (listener instanceof HdbtableListener) {
        ((HdbtableListener) listener).exitIndexesObject(this);
      }
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof HdbtableVisitor) {
        return ((HdbtableVisitor<? extends T>) visitor).visitIndexesObject(this);
      } else {
        return visitor.visitChildren(this);
      }
    }
  }

  public static class ColumnAssignNameContext extends ParserRuleContext {

    public ColumnAssignNameContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }

    public TerminalNode SEMICOLON() {
      return getToken(HdbtableParser.SEMICOLON, 0);
    }

    public TerminalNode EQ() {
      return getToken(HdbtableParser.EQ, 0);
    }

    public TerminalNode STRING() {
      return getToken(HdbtableParser.STRING, 0);
    }

    @Override
    public int getRuleIndex() {
      return RULE_columnAssignName;
    }

    @Override
    public void enterRule(ParseTreeListener listener) {
      if (listener instanceof HdbtableListener) {
        ((HdbtableListener) listener).enterColumnAssignName(this);
      }
    }

    @Override
    public void exitRule(ParseTreeListener listener) {
      if (listener instanceof HdbtableListener) {
        ((HdbtableListener) listener).exitColumnAssignName(this);
      }
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof HdbtableVisitor) {
        return ((HdbtableVisitor<? extends T>) visitor).visitColumnAssignName(this);
      } else {
        return visitor.visitChildren(this);
      }
    }
  }

  public static class ColumnAssignSQLTypeContext extends ParserRuleContext {

    public ColumnAssignSQLTypeContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }

    public TerminalNode SEMICOLON() {
      return getToken(HdbtableParser.SEMICOLON, 0);
    }

    public TerminalNode SQLTYPES() {
      return getToken(HdbtableParser.SQLTYPES, 0);
    }

    public TerminalNode EQ() {
      return getToken(HdbtableParser.EQ, 0);
    }

    @Override
    public int getRuleIndex() {
      return RULE_columnAssignSQLType;
    }

    @Override
    public void enterRule(ParseTreeListener listener) {
      if (listener instanceof HdbtableListener) {
        ((HdbtableListener) listener).enterColumnAssignSQLType(this);
      }
    }

    @Override
    public void exitRule(ParseTreeListener listener) {
      if (listener instanceof HdbtableListener) {
        ((HdbtableListener) listener).exitColumnAssignSQLType(this);
      }
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof HdbtableVisitor) {
        return ((HdbtableVisitor<? extends T>) visitor).visitColumnAssignSQLType(this);
      } else {
        return visitor.visitChildren(this);
      }
    }
  }

  public static class ColumnAssignNullableContext extends ParserRuleContext {

    public ColumnAssignNullableContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }

    public TerminalNode SEMICOLON() {
      return getToken(HdbtableParser.SEMICOLON, 0);
    }

    public TerminalNode EQ() {
      return getToken(HdbtableParser.EQ, 0);
    }

    public TerminalNode BOOLEAN() {
      return getToken(HdbtableParser.BOOLEAN, 0);
    }

    @Override
    public int getRuleIndex() {
      return RULE_columnAssignNullable;
    }

    @Override
    public void enterRule(ParseTreeListener listener) {
      if (listener instanceof HdbtableListener) {
        ((HdbtableListener) listener).enterColumnAssignNullable(this);
      }
    }

    @Override
    public void exitRule(ParseTreeListener listener) {
      if (listener instanceof HdbtableListener) {
        ((HdbtableListener) listener).exitColumnAssignNullable(this);
      }
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof HdbtableVisitor) {
        return ((HdbtableVisitor<? extends T>) visitor).visitColumnAssignNullable(this);
      } else {
        return visitor.visitChildren(this);
      }
    }
  }

  public static class ColumnAssignUniqueContext extends ParserRuleContext {

    public ColumnAssignUniqueContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }

    public TerminalNode SEMICOLON() {
      return getToken(HdbtableParser.SEMICOLON, 0);
    }

    public TerminalNode EQ() {
      return getToken(HdbtableParser.EQ, 0);
    }

    public TerminalNode BOOLEAN() {
      return getToken(HdbtableParser.BOOLEAN, 0);
    }

    @Override
    public int getRuleIndex() {
      return RULE_columnAssignUnique;
    }

    @Override
    public void enterRule(ParseTreeListener listener) {
      if (listener instanceof HdbtableListener) {
        ((HdbtableListener) listener).enterColumnAssignUnique(this);
      }
    }

    @Override
    public void exitRule(ParseTreeListener listener) {
      if (listener instanceof HdbtableListener) {
        ((HdbtableListener) listener).exitColumnAssignUnique(this);
      }
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof HdbtableVisitor) {
        return ((HdbtableVisitor<? extends T>) visitor).visitColumnAssignUnique(this);
      } else {
        return visitor.visitChildren(this);
      }
    }
  }

  public static class ColumnAssignLengthContext extends ParserRuleContext {

    public ColumnAssignLengthContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }

    public TerminalNode SEMICOLON() {
      return getToken(HdbtableParser.SEMICOLON, 0);
    }

    public TerminalNode EQ() {
      return getToken(HdbtableParser.EQ, 0);
    }

    public TerminalNode INT() {
      return getToken(HdbtableParser.INT, 0);
    }

    @Override
    public int getRuleIndex() {
      return RULE_columnAssignLength;
    }

    @Override
    public void enterRule(ParseTreeListener listener) {
      if (listener instanceof HdbtableListener) {
        ((HdbtableListener) listener).enterColumnAssignLength(this);
      }
    }

    @Override
    public void exitRule(ParseTreeListener listener) {
      if (listener instanceof HdbtableListener) {
        ((HdbtableListener) listener).exitColumnAssignLength(this);
      }
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof HdbtableVisitor) {
        return ((HdbtableVisitor<? extends T>) visitor).visitColumnAssignLength(this);
      } else {
        return visitor.visitChildren(this);
      }
    }
  }

  public static class ColumnAssignCommentContext extends ParserRuleContext {

    public ColumnAssignCommentContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }

    public TerminalNode SEMICOLON() {
      return getToken(HdbtableParser.SEMICOLON, 0);
    }

    public TerminalNode EQ() {
      return getToken(HdbtableParser.EQ, 0);
    }

    public TerminalNode STRING() {
      return getToken(HdbtableParser.STRING, 0);
    }

    @Override
    public int getRuleIndex() {
      return RULE_columnAssignComment;
    }

    @Override
    public void enterRule(ParseTreeListener listener) {
      if (listener instanceof HdbtableListener) {
        ((HdbtableListener) listener).enterColumnAssignComment(this);
      }
    }

    @Override
    public void exitRule(ParseTreeListener listener) {
      if (listener instanceof HdbtableListener) {
        ((HdbtableListener) listener).exitColumnAssignComment(this);
      }
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof HdbtableVisitor) {
        return ((HdbtableVisitor<? extends T>) visitor).visitColumnAssignComment(this);
      } else {
        return visitor.visitChildren(this);
      }
    }
  }

  public static class ColumnAssignDefaultValueContext extends ParserRuleContext {

    public ColumnAssignDefaultValueContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }

    public TerminalNode SEMICOLON() {
      return getToken(HdbtableParser.SEMICOLON, 0);
    }

    public TerminalNode EQ() {
      return getToken(HdbtableParser.EQ, 0);
    }

    public TerminalNode STRING() {
      return getToken(HdbtableParser.STRING, 0);
    }

    public TerminalNode DATETIMEDEFAULTVALUES() {
      return getToken(HdbtableParser.DATETIMEDEFAULTVALUES, 0);
    }

    public TerminalNode INT() {
      return getToken(HdbtableParser.INT, 0);
    }

    @Override
    public int getRuleIndex() {
      return RULE_columnAssignDefaultValue;
    }

    @Override
    public void enterRule(ParseTreeListener listener) {
      if (listener instanceof HdbtableListener) {
        ((HdbtableListener) listener).enterColumnAssignDefaultValue(this);
      }
    }

    @Override
    public void exitRule(ParseTreeListener listener) {
      if (listener instanceof HdbtableListener) {
        ((HdbtableListener) listener).exitColumnAssignDefaultValue(this);
      }
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof HdbtableVisitor) {
        return ((HdbtableVisitor<? extends T>) visitor).visitColumnAssignDefaultValue(this);
      } else {
        return visitor.visitChildren(this);
      }
    }
  }

  public static class ColumnAssignPrecisionContext extends ParserRuleContext {

    public ColumnAssignPrecisionContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }

    public TerminalNode SEMICOLON() {
      return getToken(HdbtableParser.SEMICOLON, 0);
    }

    public TerminalNode EQ() {
      return getToken(HdbtableParser.EQ, 0);
    }

    public TerminalNode INT() {
      return getToken(HdbtableParser.INT, 0);
    }

    @Override
    public int getRuleIndex() {
      return RULE_columnAssignPrecision;
    }

    @Override
    public void enterRule(ParseTreeListener listener) {
      if (listener instanceof HdbtableListener) {
        ((HdbtableListener) listener).enterColumnAssignPrecision(this);
      }
    }

    @Override
    public void exitRule(ParseTreeListener listener) {
      if (listener instanceof HdbtableListener) {
        ((HdbtableListener) listener).exitColumnAssignPrecision(this);
      }
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof HdbtableVisitor) {
        return ((HdbtableVisitor<? extends T>) visitor).visitColumnAssignPrecision(this);
      } else {
        return visitor.visitChildren(this);
      }
    }
  }

  public static class ColumnAssignScaleContext extends ParserRuleContext {

    public ColumnAssignScaleContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }

    public TerminalNode SEMICOLON() {
      return getToken(HdbtableParser.SEMICOLON, 0);
    }

    public TerminalNode EQ() {
      return getToken(HdbtableParser.EQ, 0);
    }

    public TerminalNode INT() {
      return getToken(HdbtableParser.INT, 0);
    }

    @Override
    public int getRuleIndex() {
      return RULE_columnAssignScale;
    }

    @Override
    public void enterRule(ParseTreeListener listener) {
      if (listener instanceof HdbtableListener) {
        ((HdbtableListener) listener).enterColumnAssignScale(this);
      }
    }

    @Override
    public void exitRule(ParseTreeListener listener) {
      if (listener instanceof HdbtableListener) {
        ((HdbtableListener) listener).exitColumnAssignScale(this);
      }
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof HdbtableVisitor) {
        return ((HdbtableVisitor<? extends T>) visitor).visitColumnAssignScale(this);
      } else {
        return visitor.visitChildren(this);
      }
    }
  }

  public static class IndexAssignNameContext extends ParserRuleContext {

    public IndexAssignNameContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }

    public TerminalNode SEMICOLON() {
      return getToken(HdbtableParser.SEMICOLON, 0);
    }

    public TerminalNode EQ() {
      return getToken(HdbtableParser.EQ, 0);
    }

    public TerminalNode STRING() {
      return getToken(HdbtableParser.STRING, 0);
    }

    @Override
    public int getRuleIndex() {
      return RULE_indexAssignName;
    }

    @Override
    public void enterRule(ParseTreeListener listener) {
      if (listener instanceof HdbtableListener) {
        ((HdbtableListener) listener).enterIndexAssignName(this);
      }
    }

    @Override
    public void exitRule(ParseTreeListener listener) {
      if (listener instanceof HdbtableListener) {
        ((HdbtableListener) listener).exitIndexAssignName(this);
      }
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof HdbtableVisitor) {
        return ((HdbtableVisitor<? extends T>) visitor).visitIndexAssignName(this);
      } else {
        return visitor.visitChildren(this);
      }
    }
  }

  public static class IndexAssignUniqueContext extends ParserRuleContext {

    public IndexAssignUniqueContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }

    public TerminalNode SEMICOLON() {
      return getToken(HdbtableParser.SEMICOLON, 0);
    }

    public TerminalNode EQ() {
      return getToken(HdbtableParser.EQ, 0);
    }

    public TerminalNode BOOLEAN() {
      return getToken(HdbtableParser.BOOLEAN, 0);
    }

    @Override
    public int getRuleIndex() {
      return RULE_indexAssignUnique;
    }

    @Override
    public void enterRule(ParseTreeListener listener) {
      if (listener instanceof HdbtableListener) {
        ((HdbtableListener) listener).enterIndexAssignUnique(this);
      }
    }

    @Override
    public void exitRule(ParseTreeListener listener) {
      if (listener instanceof HdbtableListener) {
        ((HdbtableListener) listener).exitIndexAssignUnique(this);
      }
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof HdbtableVisitor) {
        return ((HdbtableVisitor<? extends T>) visitor).visitIndexAssignUnique(this);
      } else {
        return visitor.visitChildren(this);
      }
    }
  }

  public static class IndexAssignOrderContext extends ParserRuleContext {

    public IndexAssignOrderContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }

    public TerminalNode SEMICOLON() {
      return getToken(HdbtableParser.SEMICOLON, 0);
    }

    public TerminalNode ORDER() {
      return getToken(HdbtableParser.ORDER, 0);
    }

    public TerminalNode EQ() {
      return getToken(HdbtableParser.EQ, 0);
    }

    @Override
    public int getRuleIndex() {
      return RULE_indexAssignOrder;
    }

    @Override
    public void enterRule(ParseTreeListener listener) {
      if (listener instanceof HdbtableListener) {
        ((HdbtableListener) listener).enterIndexAssignOrder(this);
      }
    }

    @Override
    public void exitRule(ParseTreeListener listener) {
      if (listener instanceof HdbtableListener) {
        ((HdbtableListener) listener).exitIndexAssignOrder(this);
      }
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof HdbtableVisitor) {
        return ((HdbtableVisitor<? extends T>) visitor).visitIndexAssignOrder(this);
      } else {
        return visitor.visitChildren(this);
      }
    }
  }

  public static class IndexAssignIndexColumnsContext extends ParserRuleContext {

    public IndexAssignIndexColumnsContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }

    public TerminalNode EQ() {
      return getToken(HdbtableParser.EQ, 0);
    }

    public IndexColumnsArrayContext indexColumnsArray() {
      return getRuleContext(IndexColumnsArrayContext.class, 0);
    }

    @Override
    public int getRuleIndex() {
      return RULE_indexAssignIndexColumns;
    }

    @Override
    public void enterRule(ParseTreeListener listener) {
      if (listener instanceof HdbtableListener) {
        ((HdbtableListener) listener).enterIndexAssignIndexColumns(this);
      }
    }

    @Override
    public void exitRule(ParseTreeListener listener) {
      if (listener instanceof HdbtableListener) {
        ((HdbtableListener) listener).exitIndexAssignIndexColumns(this);
      }
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof HdbtableVisitor) {
        return ((HdbtableVisitor<? extends T>) visitor).visitIndexAssignIndexColumns(this);
      } else {
        return visitor.visitChildren(this);
      }
    }
  }

  public static class IndexAssignIndexTypeContext extends ParserRuleContext {

    public IndexAssignIndexTypeContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }

    public TerminalNode SEMICOLON() {
      return getToken(HdbtableParser.SEMICOLON, 0);
    }

    public TerminalNode INDEXTYPE() {
      return getToken(HdbtableParser.INDEXTYPE, 0);
    }

    public TerminalNode EQ() {
      return getToken(HdbtableParser.EQ, 0);
    }

    @Override
    public int getRuleIndex() {
      return RULE_indexAssignIndexType;
    }

    @Override
    public void enterRule(ParseTreeListener listener) {
      if (listener instanceof HdbtableListener) {
        ((HdbtableListener) listener).enterIndexAssignIndexType(this);
      }
    }

    @Override
    public void exitRule(ParseTreeListener listener) {
      if (listener instanceof HdbtableListener) {
        ((HdbtableListener) listener).exitIndexAssignIndexType(this);
      }
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof HdbtableVisitor) {
        return ((HdbtableVisitor<? extends T>) visitor).visitIndexAssignIndexType(this);
      } else {
        return visitor.visitChildren(this);
      }
    }
  }

  public static class IndexColumnsArrayContext extends ParserRuleContext {

    public IndexColumnsArrayContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }

    public TerminalNode SEMICOLON() {
      return getToken(HdbtableParser.SEMICOLON, 0);
    }

    public TerminalNode STRING(int i) {
      return getToken(HdbtableParser.STRING, i);
    }

    public List<TerminalNode> STRING() {
      return getTokens(HdbtableParser.STRING);
    }

    @Override
    public int getRuleIndex() {
      return RULE_indexColumnsArray;
    }

    @Override
    public void enterRule(ParseTreeListener listener) {
      if (listener instanceof HdbtableListener) {
        ((HdbtableListener) listener).enterIndexColumnsArray(this);
      }
    }

    @Override
    public void exitRule(ParseTreeListener listener) {
      if (listener instanceof HdbtableListener) {
        ((HdbtableListener) listener).exitIndexColumnsArray(this);
      }
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof HdbtableVisitor) {
        return ((HdbtableVisitor<? extends T>) visitor).visitIndexColumnsArray(this);
      } else {
        return visitor.visitChildren(this);
      }
    }
  }
}