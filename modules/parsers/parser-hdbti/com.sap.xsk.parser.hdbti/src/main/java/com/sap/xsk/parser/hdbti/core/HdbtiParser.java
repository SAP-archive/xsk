// Generated from com/sap/xsk/parser/hdbti/core/Hdbti.g4 by ANTLR 4.3
package com.sap.xsk.parser.hdbti.core;

import java.util.List;
import org.antlr.v4.runtime.NoViableAltException;
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
public class HdbtiParser extends Parser {

  public static final int
      T__14 = 1, T__13 = 2, T__12 = 3, T__11 = 4, T__10 = 5, T__9 = 6, T__8 = 7, T__7 = 8, T__6 = 9,
      T__5 = 10, T__4 = 11, T__3 = 12, T__2 = 13, T__1 = 14, T__0 = 15, STRING = 16, BOOLEAN = 17,
      TRUE = 18, FALSE = 19, WS = 20, RB = 21, LB = 22, EQ = 23;
  public static final String[] tokenNames = {
      "<INVALID>", "'file'", "'delimField'", "'useHeaderNames'", "':'", "'{'",
      "';'", "'}'", "'keys'", "'schema'", "'table'", "'delimEnclosing'", "'header'",
      "','", "'import'", "'distinguishEmptyFromNull'", "STRING", "BOOLEAN",
      "'true'", "'false'", "WS", "'['", "']'", "'='"
  };
  public static final int
      RULE_importArr = 0, RULE_objConfig = 1, RULE_assignExpression = 2, RULE_assignTable = 3,
      RULE_assignSchema = 4, RULE_assignFile = 5, RULE_assignHeader = 6, RULE_assignUseHeaderNames = 7,
      RULE_assignDelimField = 8, RULE_assignDelimEnclosing = 9, RULE_assignDistinguishEmptyFromNull = 10,
      RULE_assignKeys = 11, RULE_keyArr = 12, RULE_pair = 13, RULE_pairKey = 14,
      RULE_pairValue = 15;
  public static final String[] ruleNames = {
      "importArr", "objConfig", "assignExpression", "assignTable", "assignSchema",
      "assignFile", "assignHeader", "assignUseHeaderNames", "assignDelimField",
      "assignDelimEnclosing", "assignDistinguishEmptyFromNull", "assignKeys",
      "keyArr", "pair", "pairKey", "pairValue"
  };
  public static final String _serializedATN =
      "\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\31\u0089\4\2\t\2" +
          "\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13" +
          "\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\3\2\3\2" +
          "\3\2\3\2\3\2\3\2\7\2)\n\2\f\2\16\2,\13\2\5\2.\n\2\3\2\3\2\3\2\3\3\3\3" +
          "\7\3\65\n\3\f\3\16\38\13\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4" +
          "\5\4E\n\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7" +
          "\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\13\3\13" +
          "\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16" +
          "\7\16w\n\16\f\16\16\16z\13\16\5\16|\n\16\3\16\3\16\3\16\3\17\3\17\3\17" +
          "\3\17\3\20\3\20\3\21\3\21\3\21\2\2\22\2\4\6\b\n\f\16\20\22\24\26\30\32" +
          "\34\36 \2\2\u0085\2\"\3\2\2\2\4\62\3\2\2\2\6D\3\2\2\2\bF\3\2\2\2\nK\3" +
          "\2\2\2\fP\3\2\2\2\16U\3\2\2\2\20Z\3\2\2\2\22_\3\2\2\2\24d\3\2\2\2\26i" +
          "\3\2\2\2\30n\3\2\2\2\32r\3\2\2\2\34\u0080\3\2\2\2\36\u0084\3\2\2\2 \u0086" +
          "\3\2\2\2\"#\7\20\2\2#$\7\31\2\2$-\7\27\2\2%*\5\4\3\2&\'\7\17\2\2\')\5" +
          "\4\3\2(&\3\2\2\2),\3\2\2\2*(\3\2\2\2*+\3\2\2\2+.\3\2\2\2,*\3\2\2\2-%\3" +
          "\2\2\2-.\3\2\2\2./\3\2\2\2/\60\7\30\2\2\60\61\7\b\2\2\61\3\3\2\2\2\62" +
          "\66\7\7\2\2\63\65\5\6\4\2\64\63\3\2\2\2\658\3\2\2\2\66\64\3\2\2\2\66\67" +
          "\3\2\2\2\679\3\2\2\28\66\3\2\2\29:\7\t\2\2:\5\3\2\2\2;E\5\b\5\2<E\5\n" +
          "\6\2=E\5\f\7\2>E\5\16\b\2?E\5\20\t\2@E\5\22\n\2AE\5\24\13\2BE\5\26\f\2" +
          "CE\5\30\r\2D;\3\2\2\2D<\3\2\2\2D=\3\2\2\2D>\3\2\2\2D?\3\2\2\2D@\3\2\2" +
          "\2DA\3\2\2\2DB\3\2\2\2DC\3\2\2\2E\7\3\2\2\2FG\7\f\2\2GH\7\31\2\2HI\7\22" +
          "\2\2IJ\7\b\2\2J\t\3\2\2\2KL\7\13\2\2LM\7\31\2\2MN\7\22\2\2NO\7\b\2\2O" +
          "\13\3\2\2\2PQ\7\3\2\2QR\7\31\2\2RS\7\22\2\2ST\7\b\2\2T\r\3\2\2\2UV\7\16" +
          "\2\2VW\7\31\2\2WX\7\23\2\2XY\7\b\2\2Y\17\3\2\2\2Z[\7\5\2\2[\\\7\31\2\2" +
          "\\]\7\23\2\2]^\7\b\2\2^\21\3\2\2\2_`\7\4\2\2`a\7\31\2\2ab\7\22\2\2bc\7" +
          "\b\2\2c\23\3\2\2\2de\7\r\2\2ef\7\31\2\2fg\7\22\2\2gh\7\b\2\2h\25\3\2\2" +
          "\2ij\7\21\2\2jk\7\31\2\2kl\7\23\2\2lm\7\b\2\2m\27\3\2\2\2no\7\n\2\2op" +
          "\7\31\2\2pq\5\32\16\2q\31\3\2\2\2r{\7\27\2\2sx\5\34\17\2tu\7\17\2\2uw" +
          "\5\34\17\2vt\3\2\2\2wz\3\2\2\2xv\3\2\2\2xy\3\2\2\2y|\3\2\2\2zx\3\2\2\2" +
          "{s\3\2\2\2{|\3\2\2\2|}\3\2\2\2}~\7\30\2\2~\177\7\b\2\2\177\33\3\2\2\2" +
          "\u0080\u0081\5\36\20\2\u0081\u0082\7\6\2\2\u0082\u0083\5 \21\2\u0083\35" +
          "\3\2\2\2\u0084\u0085\7\22\2\2\u0085\37\3\2\2\2\u0086\u0087\7\22\2\2\u0087" +
          "!\3\2\2\2\b*-\66Dx{";
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

  public HdbtiParser(TokenStream input) {
    super(input);
    _interp = new ParserATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
  }

  @Override
  public String getGrammarFileName() {
    return "Hdbti.g4";
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

  public final ImportArrContext importArr() throws RecognitionException {
    ImportArrContext _localctx = new ImportArrContext(_ctx, getState());
    enterRule(_localctx, 0, RULE_importArr);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(32);
        match(T__1);
        setState(33);
        match(EQ);
        setState(34);
        match(RB);
        setState(43);
        _la = _input.LA(1);
        if (_la == T__10) {
          {
            setState(35);
            objConfig();
            setState(40);
            _errHandler.sync(this);
            _la = _input.LA(1);
            while (_la == T__2) {
              {
                {
                  setState(36);
                  match(T__2);
                  setState(37);
                  objConfig();
                }
              }
              setState(42);
              _errHandler.sync(this);
              _la = _input.LA(1);
            }
          }
        }

        setState(45);
        match(LB);
        setState(46);
        match(T__9);
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

  public final ObjConfigContext objConfig() throws RecognitionException {
    ObjConfigContext _localctx = new ObjConfigContext(_ctx, getState());
    enterRule(_localctx, 2, RULE_objConfig);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(48);
        match(T__10);
        setState(52);
        _errHandler.sync(this);
        _la = _input.LA(1);
        while ((((_la) & ~0x3f) == 0 &&
            ((1L << _la) & ((1L << T__14) | (1L << T__13) | (1L << T__12) | (1L << T__7) | (1L << T__6) | (1L << T__5) | (1L << T__4) | (1L
                << T__3) | (1L << T__0))) != 0)) {
          {
            {
              setState(49);
              assignExpression();
            }
          }
          setState(54);
          _errHandler.sync(this);
          _la = _input.LA(1);
        }
        setState(55);
        match(T__8);
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

  public final AssignExpressionContext assignExpression() throws RecognitionException {
    AssignExpressionContext _localctx = new AssignExpressionContext(_ctx, getState());
    enterRule(_localctx, 4, RULE_assignExpression);
    try {
      setState(66);
      switch (_input.LA(1)) {
        case T__5:
          enterOuterAlt(_localctx, 1);
        {
          setState(57);
          assignTable();
        }
        break;
        case T__6:
          enterOuterAlt(_localctx, 2);
        {
          setState(58);
          assignSchema();
        }
        break;
        case T__14:
          enterOuterAlt(_localctx, 3);
        {
          setState(59);
          assignFile();
        }
        break;
        case T__3:
          enterOuterAlt(_localctx, 4);
        {
          setState(60);
          assignHeader();
        }
        break;
        case T__12:
          enterOuterAlt(_localctx, 5);
        {
          setState(61);
          assignUseHeaderNames();
        }
        break;
        case T__13:
          enterOuterAlt(_localctx, 6);
        {
          setState(62);
          assignDelimField();
        }
        break;
        case T__4:
          enterOuterAlt(_localctx, 7);
        {
          setState(63);
          assignDelimEnclosing();
        }
        break;
        case T__0:
          enterOuterAlt(_localctx, 8);
        {
          setState(64);
          assignDistinguishEmptyFromNull();
        }
        break;
        case T__7:
          enterOuterAlt(_localctx, 9);
        {
          setState(65);
          assignKeys();
        }
        break;
        default:
          throw new NoViableAltException(this);
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

  public final AssignTableContext assignTable() throws RecognitionException {
    AssignTableContext _localctx = new AssignTableContext(_ctx, getState());
    enterRule(_localctx, 6, RULE_assignTable);
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(68);
        match(T__5);
        setState(69);
        match(EQ);
        setState(70);
        match(STRING);
        setState(71);
        match(T__9);
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

  public final AssignSchemaContext assignSchema() throws RecognitionException {
    AssignSchemaContext _localctx = new AssignSchemaContext(_ctx, getState());
    enterRule(_localctx, 8, RULE_assignSchema);
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(73);
        match(T__6);
        setState(74);
        match(EQ);
        setState(75);
        match(STRING);
        setState(76);
        match(T__9);
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

  public final AssignFileContext assignFile() throws RecognitionException {
    AssignFileContext _localctx = new AssignFileContext(_ctx, getState());
    enterRule(_localctx, 10, RULE_assignFile);
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(78);
        match(T__14);
        setState(79);
        match(EQ);
        setState(80);
        match(STRING);
        setState(81);
        match(T__9);
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

  public final AssignHeaderContext assignHeader() throws RecognitionException {
    AssignHeaderContext _localctx = new AssignHeaderContext(_ctx, getState());
    enterRule(_localctx, 12, RULE_assignHeader);
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(83);
        match(T__3);
        setState(84);
        match(EQ);
        setState(85);
        match(BOOLEAN);
        setState(86);
        match(T__9);
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

  public final AssignUseHeaderNamesContext assignUseHeaderNames() throws RecognitionException {
    AssignUseHeaderNamesContext _localctx = new AssignUseHeaderNamesContext(_ctx, getState());
    enterRule(_localctx, 14, RULE_assignUseHeaderNames);
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(88);
        match(T__12);
        setState(89);
        match(EQ);
        setState(90);
        match(BOOLEAN);
        setState(91);
        match(T__9);
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

  public final AssignDelimFieldContext assignDelimField() throws RecognitionException {
    AssignDelimFieldContext _localctx = new AssignDelimFieldContext(_ctx, getState());
    enterRule(_localctx, 16, RULE_assignDelimField);
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(93);
        match(T__13);
        setState(94);
        match(EQ);
        setState(95);
        match(STRING);
        setState(96);
        match(T__9);
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

  public final AssignDelimEnclosingContext assignDelimEnclosing() throws RecognitionException {
    AssignDelimEnclosingContext _localctx = new AssignDelimEnclosingContext(_ctx, getState());
    enterRule(_localctx, 18, RULE_assignDelimEnclosing);
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(98);
        match(T__4);
        setState(99);
        match(EQ);
        setState(100);
        match(STRING);
        setState(101);
        match(T__9);
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

  public final AssignDistinguishEmptyFromNullContext assignDistinguishEmptyFromNull() throws RecognitionException {
    AssignDistinguishEmptyFromNullContext _localctx = new AssignDistinguishEmptyFromNullContext(_ctx, getState());
    enterRule(_localctx, 20, RULE_assignDistinguishEmptyFromNull);
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(103);
        match(T__0);
        setState(104);
        match(EQ);
        setState(105);
        match(BOOLEAN);
        setState(106);
        match(T__9);
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

  public final AssignKeysContext assignKeys() throws RecognitionException {
    AssignKeysContext _localctx = new AssignKeysContext(_ctx, getState());
    enterRule(_localctx, 22, RULE_assignKeys);
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(108);
        match(T__7);
        setState(109);
        match(EQ);
        setState(110);
        keyArr();
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

  public final KeyArrContext keyArr() throws RecognitionException {
    KeyArrContext _localctx = new KeyArrContext(_ctx, getState());
    enterRule(_localctx, 24, RULE_keyArr);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(112);
        match(RB);
        setState(121);
        _la = _input.LA(1);
        if (_la == STRING) {
          {
            setState(113);
            pair();
            setState(118);
            _errHandler.sync(this);
            _la = _input.LA(1);
            while (_la == T__2) {
              {
                {
                  setState(114);
                  match(T__2);
                  setState(115);
                  pair();
                }
              }
              setState(120);
              _errHandler.sync(this);
              _la = _input.LA(1);
            }
          }
        }

        setState(123);
        match(LB);
        setState(124);
        match(T__9);
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

  public final PairContext pair() throws RecognitionException {
    PairContext _localctx = new PairContext(_ctx, getState());
    enterRule(_localctx, 26, RULE_pair);
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(126);
        pairKey();
        setState(127);
        match(T__11);
        setState(128);
        pairValue();
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

  public final PairKeyContext pairKey() throws RecognitionException {
    PairKeyContext _localctx = new PairKeyContext(_ctx, getState());
    enterRule(_localctx, 28, RULE_pairKey);
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(130);
        match(STRING);
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

  public final PairValueContext pairValue() throws RecognitionException {
    PairValueContext _localctx = new PairValueContext(_ctx, getState());
    enterRule(_localctx, 30, RULE_pairValue);
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(132);
        match(STRING);
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

  public static class ImportArrContext extends ParserRuleContext {

    public ImportArrContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }

    public List<ObjConfigContext> objConfig() {
      return getRuleContexts(ObjConfigContext.class);
    }

    public ObjConfigContext objConfig(int i) {
      return getRuleContext(ObjConfigContext.class, i);
    }

    @Override
    public int getRuleIndex() {
      return RULE_importArr;
    }

    @Override
    public void enterRule(ParseTreeListener listener) {
      if (listener instanceof HdbtiListener) {
        ((HdbtiListener) listener).enterImportArr(this);
      }
    }

    @Override
    public void exitRule(ParseTreeListener listener) {
      if (listener instanceof HdbtiListener) {
        ((HdbtiListener) listener).exitImportArr(this);
      }
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof HdbtiVisitor) {
        return ((HdbtiVisitor<? extends T>) visitor).visitImportArr(this);
      } else {
        return visitor.visitChildren(this);
      }
    }
  }

  public static class ObjConfigContext extends ParserRuleContext {

    public ObjConfigContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }

    public List<AssignExpressionContext> assignExpression() {
      return getRuleContexts(AssignExpressionContext.class);
    }

    public AssignExpressionContext assignExpression(int i) {
      return getRuleContext(AssignExpressionContext.class, i);
    }

    @Override
    public int getRuleIndex() {
      return RULE_objConfig;
    }

    @Override
    public void enterRule(ParseTreeListener listener) {
      if (listener instanceof HdbtiListener) {
        ((HdbtiListener) listener).enterObjConfig(this);
      }
    }

    @Override
    public void exitRule(ParseTreeListener listener) {
      if (listener instanceof HdbtiListener) {
        ((HdbtiListener) listener).exitObjConfig(this);
      }
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof HdbtiVisitor) {
        return ((HdbtiVisitor<? extends T>) visitor).visitObjConfig(this);
      } else {
        return visitor.visitChildren(this);
      }
    }
  }

  public static class AssignExpressionContext extends ParserRuleContext {

    public AssignExpressionContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }

    public AssignDistinguishEmptyFromNullContext assignDistinguishEmptyFromNull() {
      return getRuleContext(AssignDistinguishEmptyFromNullContext.class, 0);
    }

    public AssignKeysContext assignKeys() {
      return getRuleContext(AssignKeysContext.class, 0);
    }

    public AssignDelimFieldContext assignDelimField() {
      return getRuleContext(AssignDelimFieldContext.class, 0);
    }

    public AssignDelimEnclosingContext assignDelimEnclosing() {
      return getRuleContext(AssignDelimEnclosingContext.class, 0);
    }

    public AssignSchemaContext assignSchema() {
      return getRuleContext(AssignSchemaContext.class, 0);
    }

    public AssignHeaderContext assignHeader() {
      return getRuleContext(AssignHeaderContext.class, 0);
    }

    public AssignUseHeaderNamesContext assignUseHeaderNames() {
      return getRuleContext(AssignUseHeaderNamesContext.class, 0);
    }

    public AssignFileContext assignFile() {
      return getRuleContext(AssignFileContext.class, 0);
    }

    public AssignTableContext assignTable() {
      return getRuleContext(AssignTableContext.class, 0);
    }

    @Override
    public int getRuleIndex() {
      return RULE_assignExpression;
    }

    @Override
    public void enterRule(ParseTreeListener listener) {
      if (listener instanceof HdbtiListener) {
        ((HdbtiListener) listener).enterAssignExpression(this);
      }
    }

    @Override
    public void exitRule(ParseTreeListener listener) {
      if (listener instanceof HdbtiListener) {
        ((HdbtiListener) listener).exitAssignExpression(this);
      }
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof HdbtiVisitor) {
        return ((HdbtiVisitor<? extends T>) visitor).visitAssignExpression(this);
      } else {
        return visitor.visitChildren(this);
      }
    }
  }

  public static class AssignTableContext extends ParserRuleContext {

    public AssignTableContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }

    public TerminalNode STRING() {
      return getToken(HdbtiParser.STRING, 0);
    }

    @Override
    public int getRuleIndex() {
      return RULE_assignTable;
    }

    @Override
    public void enterRule(ParseTreeListener listener) {
      if (listener instanceof HdbtiListener) {
        ((HdbtiListener) listener).enterAssignTable(this);
      }
    }

    @Override
    public void exitRule(ParseTreeListener listener) {
      if (listener instanceof HdbtiListener) {
        ((HdbtiListener) listener).exitAssignTable(this);
      }
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof HdbtiVisitor) {
        return ((HdbtiVisitor<? extends T>) visitor).visitAssignTable(this);
      } else {
        return visitor.visitChildren(this);
      }
    }
  }

  public static class AssignSchemaContext extends ParserRuleContext {

    public AssignSchemaContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }

    public TerminalNode STRING() {
      return getToken(HdbtiParser.STRING, 0);
    }

    @Override
    public int getRuleIndex() {
      return RULE_assignSchema;
    }

    @Override
    public void enterRule(ParseTreeListener listener) {
      if (listener instanceof HdbtiListener) {
        ((HdbtiListener) listener).enterAssignSchema(this);
      }
    }

    @Override
    public void exitRule(ParseTreeListener listener) {
      if (listener instanceof HdbtiListener) {
        ((HdbtiListener) listener).exitAssignSchema(this);
      }
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof HdbtiVisitor) {
        return ((HdbtiVisitor<? extends T>) visitor).visitAssignSchema(this);
      } else {
        return visitor.visitChildren(this);
      }
    }
  }

  public static class AssignFileContext extends ParserRuleContext {

    public AssignFileContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }

    public TerminalNode STRING() {
      return getToken(HdbtiParser.STRING, 0);
    }

    @Override
    public int getRuleIndex() {
      return RULE_assignFile;
    }

    @Override
    public void enterRule(ParseTreeListener listener) {
      if (listener instanceof HdbtiListener) {
        ((HdbtiListener) listener).enterAssignFile(this);
      }
    }

    @Override
    public void exitRule(ParseTreeListener listener) {
      if (listener instanceof HdbtiListener) {
        ((HdbtiListener) listener).exitAssignFile(this);
      }
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof HdbtiVisitor) {
        return ((HdbtiVisitor<? extends T>) visitor).visitAssignFile(this);
      } else {
        return visitor.visitChildren(this);
      }
    }
  }

  public static class AssignHeaderContext extends ParserRuleContext {

    public AssignHeaderContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }

    public TerminalNode BOOLEAN() {
      return getToken(HdbtiParser.BOOLEAN, 0);
    }

    @Override
    public int getRuleIndex() {
      return RULE_assignHeader;
    }

    @Override
    public void enterRule(ParseTreeListener listener) {
      if (listener instanceof HdbtiListener) {
        ((HdbtiListener) listener).enterAssignHeader(this);
      }
    }

    @Override
    public void exitRule(ParseTreeListener listener) {
      if (listener instanceof HdbtiListener) {
        ((HdbtiListener) listener).exitAssignHeader(this);
      }
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof HdbtiVisitor) {
        return ((HdbtiVisitor<? extends T>) visitor).visitAssignHeader(this);
      } else {
        return visitor.visitChildren(this);
      }
    }
  }

  public static class AssignUseHeaderNamesContext extends ParserRuleContext {

    public AssignUseHeaderNamesContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }

    public TerminalNode BOOLEAN() {
      return getToken(HdbtiParser.BOOLEAN, 0);
    }

    @Override
    public int getRuleIndex() {
      return RULE_assignUseHeaderNames;
    }

    @Override
    public void enterRule(ParseTreeListener listener) {
      if (listener instanceof HdbtiListener) {
        ((HdbtiListener) listener).enterAssignUseHeaderNames(this);
      }
    }

    @Override
    public void exitRule(ParseTreeListener listener) {
      if (listener instanceof HdbtiListener) {
        ((HdbtiListener) listener).exitAssignUseHeaderNames(this);
      }
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof HdbtiVisitor) {
        return ((HdbtiVisitor<? extends T>) visitor).visitAssignUseHeaderNames(this);
      } else {
        return visitor.visitChildren(this);
      }
    }
  }

  public static class AssignDelimFieldContext extends ParserRuleContext {

    public AssignDelimFieldContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }

    public TerminalNode STRING() {
      return getToken(HdbtiParser.STRING, 0);
    }

    @Override
    public int getRuleIndex() {
      return RULE_assignDelimField;
    }

    @Override
    public void enterRule(ParseTreeListener listener) {
      if (listener instanceof HdbtiListener) {
        ((HdbtiListener) listener).enterAssignDelimField(this);
      }
    }

    @Override
    public void exitRule(ParseTreeListener listener) {
      if (listener instanceof HdbtiListener) {
        ((HdbtiListener) listener).exitAssignDelimField(this);
      }
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof HdbtiVisitor) {
        return ((HdbtiVisitor<? extends T>) visitor).visitAssignDelimField(this);
      } else {
        return visitor.visitChildren(this);
      }
    }
  }

  public static class AssignDelimEnclosingContext extends ParserRuleContext {

    public AssignDelimEnclosingContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }

    public TerminalNode STRING() {
      return getToken(HdbtiParser.STRING, 0);
    }

    @Override
    public int getRuleIndex() {
      return RULE_assignDelimEnclosing;
    }

    @Override
    public void enterRule(ParseTreeListener listener) {
      if (listener instanceof HdbtiListener) {
        ((HdbtiListener) listener).enterAssignDelimEnclosing(this);
      }
    }

    @Override
    public void exitRule(ParseTreeListener listener) {
      if (listener instanceof HdbtiListener) {
        ((HdbtiListener) listener).exitAssignDelimEnclosing(this);
      }
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof HdbtiVisitor) {
        return ((HdbtiVisitor<? extends T>) visitor).visitAssignDelimEnclosing(this);
      } else {
        return visitor.visitChildren(this);
      }
    }
  }

  public static class AssignDistinguishEmptyFromNullContext extends ParserRuleContext {

    public AssignDistinguishEmptyFromNullContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }

    public TerminalNode BOOLEAN() {
      return getToken(HdbtiParser.BOOLEAN, 0);
    }

    @Override
    public int getRuleIndex() {
      return RULE_assignDistinguishEmptyFromNull;
    }

    @Override
    public void enterRule(ParseTreeListener listener) {
      if (listener instanceof HdbtiListener) {
        ((HdbtiListener) listener).enterAssignDistinguishEmptyFromNull(this);
      }
    }

    @Override
    public void exitRule(ParseTreeListener listener) {
      if (listener instanceof HdbtiListener) {
        ((HdbtiListener) listener).exitAssignDistinguishEmptyFromNull(this);
      }
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof HdbtiVisitor) {
        return ((HdbtiVisitor<? extends T>) visitor).visitAssignDistinguishEmptyFromNull(this);
      } else {
        return visitor.visitChildren(this);
      }
    }
  }

  public static class AssignKeysContext extends ParserRuleContext {

    public AssignKeysContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }

    public KeyArrContext keyArr() {
      return getRuleContext(KeyArrContext.class, 0);
    }

    @Override
    public int getRuleIndex() {
      return RULE_assignKeys;
    }

    @Override
    public void enterRule(ParseTreeListener listener) {
      if (listener instanceof HdbtiListener) {
        ((HdbtiListener) listener).enterAssignKeys(this);
      }
    }

    @Override
    public void exitRule(ParseTreeListener listener) {
      if (listener instanceof HdbtiListener) {
        ((HdbtiListener) listener).exitAssignKeys(this);
      }
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof HdbtiVisitor) {
        return ((HdbtiVisitor<? extends T>) visitor).visitAssignKeys(this);
      } else {
        return visitor.visitChildren(this);
      }
    }
  }

  public static class KeyArrContext extends ParserRuleContext {

    public KeyArrContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }

    public PairContext pair(int i) {
      return getRuleContext(PairContext.class, i);
    }

    public List<PairContext> pair() {
      return getRuleContexts(PairContext.class);
    }

    @Override
    public int getRuleIndex() {
      return RULE_keyArr;
    }

    @Override
    public void enterRule(ParseTreeListener listener) {
      if (listener instanceof HdbtiListener) {
        ((HdbtiListener) listener).enterKeyArr(this);
      }
    }

    @Override
    public void exitRule(ParseTreeListener listener) {
      if (listener instanceof HdbtiListener) {
        ((HdbtiListener) listener).exitKeyArr(this);
      }
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof HdbtiVisitor) {
        return ((HdbtiVisitor<? extends T>) visitor).visitKeyArr(this);
      } else {
        return visitor.visitChildren(this);
      }
    }
  }

  public static class PairContext extends ParserRuleContext {

    public PairContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }

    public PairValueContext pairValue() {
      return getRuleContext(PairValueContext.class, 0);
    }

    public PairKeyContext pairKey() {
      return getRuleContext(PairKeyContext.class, 0);
    }

    @Override
    public int getRuleIndex() {
      return RULE_pair;
    }

    @Override
    public void enterRule(ParseTreeListener listener) {
      if (listener instanceof HdbtiListener) {
        ((HdbtiListener) listener).enterPair(this);
      }
    }

    @Override
    public void exitRule(ParseTreeListener listener) {
      if (listener instanceof HdbtiListener) {
        ((HdbtiListener) listener).exitPair(this);
      }
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof HdbtiVisitor) {
        return ((HdbtiVisitor<? extends T>) visitor).visitPair(this);
      } else {
        return visitor.visitChildren(this);
      }
    }
  }

  public static class PairKeyContext extends ParserRuleContext {

    public PairKeyContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }

    public TerminalNode STRING() {
      return getToken(HdbtiParser.STRING, 0);
    }

    @Override
    public int getRuleIndex() {
      return RULE_pairKey;
    }

    @Override
    public void enterRule(ParseTreeListener listener) {
      if (listener instanceof HdbtiListener) {
        ((HdbtiListener) listener).enterPairKey(this);
      }
    }

    @Override
    public void exitRule(ParseTreeListener listener) {
      if (listener instanceof HdbtiListener) {
        ((HdbtiListener) listener).exitPairKey(this);
      }
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof HdbtiVisitor) {
        return ((HdbtiVisitor<? extends T>) visitor).visitPairKey(this);
      } else {
        return visitor.visitChildren(this);
      }
    }
  }

  public static class PairValueContext extends ParserRuleContext {

    public PairValueContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }

    public TerminalNode STRING() {
      return getToken(HdbtiParser.STRING, 0);
    }

    @Override
    public int getRuleIndex() {
      return RULE_pairValue;
    }

    @Override
    public void enterRule(ParseTreeListener listener) {
      if (listener instanceof HdbtiListener) {
        ((HdbtiListener) listener).enterPairValue(this);
      }
    }

    @Override
    public void exitRule(ParseTreeListener listener) {
      if (listener instanceof HdbtiListener) {
        ((HdbtiListener) listener).exitPairValue(this);
      }
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof HdbtiVisitor) {
        return ((HdbtiVisitor<? extends T>) visitor).visitPairValue(this);
      } else {
        return visitor.visitChildren(this);
      }
    }
  }
}