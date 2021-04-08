package com.sap.xsk.models.xsodata.parser.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.

import org.antlr.runtime.BaseRecognizer;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.DFA;
import org.antlr.runtime.EarlyExitException;
import org.antlr.runtime.IntStream;
import org.antlr.runtime.MismatchedSetException;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.eclipse.xtext.parser.antlr.Lexer;

@SuppressWarnings("all")
public class InternalXSODataLexer extends Lexer {

  public static final int RULE_STRING = 6;
  public static final int RULE_SL_COMMENT = 8;
  public static final int T__19 = 19;
  public static final int T__15 = 15;
  public static final int T__37 = 37;
  public static final int T__16 = 16;
  public static final int T__17 = 17;
  public static final int T__18 = 18;
  public static final int T__11 = 11;
  public static final int T__33 = 33;
  public static final int T__12 = 12;
  public static final int T__34 = 34;
  public static final int T__13 = 13;
  public static final int T__35 = 35;
  public static final int T__14 = 14;
  public static final int T__36 = 36;
  public static final int EOF = -1;
  public static final int T__30 = 30;
  public static final int T__31 = 31;
  public static final int T__32 = 32;
  public static final int RULE_ID = 4;
  public static final int RULE_WS = 9;
  public static final int RULE_ANY_OTHER = 10;
  public static final int T__26 = 26;
  public static final int T__27 = 27;
  public static final int T__28 = 28;
  public static final int RULE_INT = 5;
  public static final int T__29 = 29;
  public static final int T__22 = 22;
  public static final int RULE_ML_COMMENT = 7;
  public static final int T__23 = 23;
  public static final int T__24 = 24;
  public static final int T__25 = 25;
  public static final int T__20 = 20;
  public static final int T__21 = 21;

  // delegates
  // delegators
  static final String DFA12_eotS =
      "\1\uffff\2\36\2\uffff\1\43\1\45\1\36\3\uffff\10\36\1\uffff\1\64\1\34\2\uffff\3\34\2\uffff\1\36\1\uffff\1\36\6\uffff\1\77\3\uffff\11\36\7\uffff\3\36\1\uffff\1\36\1\uffff\15\36\1\uffff\16\36\1\154\6\36\1\163\7\36\2\uffff\1\36\1\174\1\175\1\176\1\177\1\36\1\uffff\1\u0081\3\36\1\u0085\3\36\4\uffff\1\36\1\uffff\3\36\1\uffff\7\36\1\u0094\1\u0095\1\36\1\u0097\1\u0098\1\u0099\1\36\2\uffff\1\36\3\uffff\1\36\1\u009d\1\36\1\uffff\1\u009f\1\uffff";
  static final String DFA12_eofS =
      "\u00a0\uffff";
  static final String DFA12_minS =
      "\1\0\1\145\1\141\2\uffff\1\170\1\72\1\163\3\uffff\1\162\1\166\2\145\1\160\1\157\1\162\1\165\1\uffff\1\60\1\101\2\uffff\2\0\1\52\2\uffff\1\162\1\uffff\1\155\2\uffff\1\163\3\uffff\1\60\3\uffff\2\145\1\146\1\154\1\151\1\144\1\162\1\151\1\154\7\uffff\1\166\1\145\1\151\1\152\1\157\1\uffff\1\141\1\156\1\157\2\145\1\156\1\141\1\142\1\156\1\164\1\151\1\163\1\147\1\163\1\143\2\164\1\162\1\164\1\156\1\147\1\164\1\151\1\143\1\151\1\143\1\160\1\141\1\154\1\151\1\145\1\163\2\145\1\144\1\60\1\145\1\144\1\151\1\160\1\145\1\141\1\164\2\uffff\1\141\4\60\1\145\1\uffff\1\60\1\144\1\160\1\154\1\60\1\143\1\145\1\164\4\uffff\1\156\1\uffff\1\145\1\141\1\151\1\uffff\1\145\1\163\1\151\1\164\1\156\1\154\1\143\2\60\1\157\3\60\1\151\2\uffff\1\156\3\uffff\1\164\1\60\1\171\1\uffff\1\60\1\uffff";
  static final String DFA12_maxS =
      "\1\uffff\1\145\1\141\2\uffff\1\170\1\72\1\163\3\uffff\1\162\1\166\2\145\1\163\1\157\1\162\1\165\1\uffff\1\71\1\172\2\uffff\2\uffff\1\57\2\uffff\1\162\1\uffff\1\166\2\uffff\1\163\3\uffff\1\172\3\uffff\2\145\1\146\1\160\1\151\1\144\1\162\1\151\1\154\7\uffff\1\166\1\145\1\151\1\152\1\157\1\uffff\1\141\1\156\1\157\2\145\1\156\1\141\1\142\1\156\1\164\1\151\1\163\1\147\1\163\1\143\2\164\1\162\1\164\1\156\1\147\1\164\1\151\1\143\1\151\1\143\1\160\1\141\1\154\1\151\1\145\1\163\2\145\1\144\1\172\1\145\1\144\1\151\1\160\1\145\1\141\1\164\2\uffff\1\141\4\172\1\145\1\uffff\1\172\1\144\1\160\1\154\1\172\1\143\1\145\1\164\4\uffff\1\156\1\uffff\1\145\1\141\1\151\1\uffff\1\145\1\163\1\151\1\164\1\156\1\154\1\143\2\172\1\157\3\172\1\151\2\uffff\1\156\3\uffff\1\164\1\172\1\171\1\uffff\1\172\1\uffff";
  static final String DFA12_acceptS =
      "\3\uffff\1\3\1\4\3\uffff\1\10\1\12\1\13\10\uffff\1\32\2\uffff\1\34\1\35\3\uffff\1\41\1\42\1\uffff\1\34\1\uffff\1\3\1\4\1\uffff\1\5\1\6\1\20\1\uffff\1\10\1\12\1\13\11\uffff\1\32\1\33\1\35\1\36\1\37\1\40\1\41\5\uffff\1\7\53\uffff\1\21\1\17\6\uffff\1\23\10\uffff\1\14\1\15\1\16\1\22\1\uffff\1\24\3\uffff\1\1\16\uffff\1\2\1\11\1\uffff\1\31\1\25\1\27\3\uffff\1\26\1\uffff\1\30";
  // $ANTLR end "T__11"
  static final String DFA12_specialS =
      "\1\1\27\uffff\1\0\1\2\u0086\uffff}>";
  // $ANTLR end "T__12"
  static final String[] DFA12_transitionS = {
      "\11\34\2\33\2\34\1\33\22\34\1\33\1\34\1\30\4\34\1\31\1\11\1\12\1\23\3\34\1\5\1\32\1\27\1\24\10\27\1\6\1\10\5\34\32\26\3\34\1\25\1\26\1\34\1\7\1\15\1\13\1\16\1\14\1\20\6\26\1\22\1\2\1\26\1\21\2\26\1\1\1\26\1\17\5\26\1\3\1\34\1\4\uff82\34",
      "\1\35",
      "\1\37",
      "",
      "",
      "\1\42",
      "\1\44",
      "\1\46",
      "",
      "",
      "",
      "\1\52",
      "\1\53",
      "\1\54",
      "\1\55",
      "\1\57\2\uffff\1\56",
      "\1\60",
      "\1\61",
      "\1\62",
      "",
      "\12\65",
      "\32\36\4\uffff\1\36\1\uffff\32\36",
      "",
      "",
      "\0\66",
      "\0\66",
      "\1\67\4\uffff\1\70",
      "",
      "",
      "\1\72",
      "",
      "\1\73\10\uffff\1\74",
      "",
      "",
      "\1\75",
      "",
      "",
      "",
      "\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\22\36\1\76\7\36",
      "",
      "",
      "",
      "\1\100",
      "\1\101",
      "\1\102",
      "\1\103\3\uffff\1\104",
      "\1\105",
      "\1\106",
      "\1\107",
      "\1\110",
      "\1\111",
      "",
      "",
      "",
      "",
      "",
      "",
      "",
      "\1\112",
      "\1\113",
      "\1\114",
      "\1\115",
      "\1\116",
      "",
      "\1\117",
      "\1\120",
      "\1\121",
      "\1\122",
      "\1\123",
      "\1\124",
      "\1\125",
      "\1\126",
      "\1\127",
      "\1\130",
      "\1\131",
      "\1\132",
      "\1\133",
      "\1\134",
      "\1\135",
      "\1\136",
      "\1\137",
      "\1\140",
      "\1\141",
      "\1\142",
      "\1\143",
      "\1\144",
      "\1\145",
      "\1\146",
      "\1\147",
      "\1\150",
      "\1\151",
      "\1\152",
      "\1\153",
      "\1\155",
      "\1\156",
      "\1\157",
      "\1\160",
      "\1\161",
      "\1\162",
      "\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
      "\1\164",
      "\1\165",
      "\1\166",
      "\1\167",
      "\1\170",
      "\1\171",
      "\1\172",
      "",
      "",
      "\1\173",
      "\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
      "\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
      "\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
      "\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
      "\1\u0080",
      "",
      "\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
      "\1\u0082",
      "\1\u0083",
      "\1\u0084",
      "\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
      "\1\u0086",
      "\1\u0087",
      "\1\u0088",
      "",
      "",
      "",
      "",
      "\1\u0089",
      "",
      "\1\u008a",
      "\1\u008b",
      "\1\u008c",
      "",
      "\1\u008d",
      "\1\u008e",
      "\1\u008f",
      "\1\u0090",
      "\1\u0091",
      "\1\u0092",
      "\1\u0093",
      "\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
      "\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
      "\1\u0096",
      "\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
      "\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
      "\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
      "\1\u009a",
      "",
      "",
      "\1\u009b",
      "",
      "",
      "",
      "\1\u009c",
      "\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
      "\1\u009e",
      "",
      "\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
      ""
  };
  // $ANTLR end "T__13"
  static final short[] DFA12_eot = DFA.unpackEncodedString(DFA12_eotS);
  // $ANTLR end "T__14"
  static final short[] DFA12_eof = DFA.unpackEncodedString(DFA12_eofS);
  // $ANTLR end "T__15"
  static final char[] DFA12_min = DFA.unpackEncodedStringToUnsignedChars(DFA12_minS);
  // $ANTLR end "T__16"
  static final char[] DFA12_max = DFA.unpackEncodedStringToUnsignedChars(DFA12_maxS);
  // $ANTLR end "T__17"
  static final short[] DFA12_accept = DFA.unpackEncodedString(DFA12_acceptS);
  // $ANTLR end "T__18"
  static final short[] DFA12_special = DFA.unpackEncodedString(DFA12_specialS);
  // $ANTLR end "T__19"
  static final short[][] DFA12_transition;
  // $ANTLR end "T__20"

  static {
    int numStates = DFA12_transitionS.length;
    DFA12_transition = new short[numStates][];
    for (int i = 0; i < numStates; i++) {
      DFA12_transition[i] = DFA.unpackEncodedString(DFA12_transitionS[i]);
    }
  }
  // $ANTLR end "T__21"

  protected DFA12 dfa12 = new DFA12(this);
  // $ANTLR end "T__22"

  public InternalXSODataLexer() {
    ;
  }
  // $ANTLR end "T__23"

  public InternalXSODataLexer(CharStream input) {
    this(input, new RecognizerSharedState());
  }
  // $ANTLR end "T__24"

  public InternalXSODataLexer(CharStream input, RecognizerSharedState state) {
    super(input, state);

  }
  // $ANTLR end "T__25"

  public String getGrammarFileName() {
    return "InternalXSOData.g";
  }
  // $ANTLR end "T__26"

  // $ANTLR start "T__11"
  public final void mT__11() throws RecognitionException {
    try {
      int _type = T__11;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // InternalXSOData.g:11:7: ( 'service' )
      // InternalXSOData.g:11:9: 'service'
      {
        match("service");


      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }
  // $ANTLR end "T__27"

  // $ANTLR start "T__12"
  public final void mT__12() throws RecognitionException {
    try {
      int _type = T__12;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // InternalXSOData.g:12:7: ( 'namespace' )
      // InternalXSOData.g:12:9: 'namespace'
      {
        match("namespace");


      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }
  // $ANTLR end "T__28"

  // $ANTLR start "T__13"
  public final void mT__13() throws RecognitionException {
    try {
      int _type = T__13;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // InternalXSOData.g:13:7: ( '{' )
      // InternalXSOData.g:13:9: '{'
      {
        match('{');

      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }
  // $ANTLR end "T__29"

  // $ANTLR start "T__14"
  public final void mT__14() throws RecognitionException {
    try {
      int _type = T__14;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // InternalXSOData.g:14:7: ( '}' )
      // InternalXSOData.g:14:9: '}'
      {
        match('}');

      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }
  // $ANTLR end "T__30"

  // $ANTLR start "T__15"
  public final void mT__15() throws RecognitionException {
    try {
      int _type = T__15;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // InternalXSOData.g:15:7: ( '.' )
      // InternalXSOData.g:15:9: '.'
      {
        match('.');

      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }
  // $ANTLR end "T__31"

  // $ANTLR start "T__16"
  public final void mT__16() throws RecognitionException {
    try {
      int _type = T__16;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // InternalXSOData.g:16:7: ( '::' )
      // InternalXSOData.g:16:9: '::'
      {
        match("::");


      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }
  // $ANTLR end "T__32"

  // $ANTLR start "T__17"
  public final void mT__17() throws RecognitionException {
    try {
      int _type = T__17;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // InternalXSOData.g:17:7: ( 'as' )
      // InternalXSOData.g:17:9: 'as'
      {
        match("as");


      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }
  // $ANTLR end "T__33"

  // $ANTLR start "T__18"
  public final void mT__18() throws RecognitionException {
    try {
      int _type = T__18;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // InternalXSOData.g:18:7: ( ';' )
      // InternalXSOData.g:18:9: ';'
      {
        match(';');

      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }
  // $ANTLR end "T__34"

  // $ANTLR start "T__19"
  public final void mT__19() throws RecognitionException {
    try {
      int _type = T__19;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // InternalXSOData.g:19:7: ( 'navigates' )
      // InternalXSOData.g:19:9: 'navigates'
      {
        match("navigates");


      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }
  // $ANTLR end "T__35"

  // $ANTLR start "T__20"
  public final void mT__20() throws RecognitionException {
    try {
      int _type = T__20;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // InternalXSOData.g:20:7: ( '(' )
      // InternalXSOData.g:20:9: '('
      {
        match('(');

      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }
  // $ANTLR end "T__36"

  // $ANTLR start "T__21"
  public final void mT__21() throws RecognitionException {
    try {
      int _type = T__21;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // InternalXSOData.g:21:7: ( ')' )
      // InternalXSOData.g:21:9: ')'
      {
        match(')');

      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }
  // $ANTLR end "T__37"

  // $ANTLR start "T__22"
  public final void mT__22() throws RecognitionException {
    try {
      int _type = T__22;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // InternalXSOData.g:22:7: ( 'create' )
      // InternalXSOData.g:22:9: 'create'
      {
        match("create");


      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }
  // $ANTLR end "RULE_ID"

  // $ANTLR start "T__23"
  public final void mT__23() throws RecognitionException {
    try {
      int _type = T__23;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // InternalXSOData.g:23:7: ( 'events' )
      // InternalXSOData.g:23:9: 'events'
      {
        match("events");


      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }
  // $ANTLR end "RULE_INT"

  // $ANTLR start "T__24"
  public final void mT__24() throws RecognitionException {
    try {
      int _type = T__24;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // InternalXSOData.g:24:7: ( 'before' )
      // InternalXSOData.g:24:9: 'before'
      {
        match("before");


      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }
  // $ANTLR end "RULE_STRING"

  // $ANTLR start "T__25"
  public final void mT__25() throws RecognitionException {
    try {
      int _type = T__25;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // InternalXSOData.g:25:7: ( '.xsjs' )
      // InternalXSOData.g:25:9: '.xsjs'
      {
        match(".xsjs");


      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }
  // $ANTLR end "RULE_ML_COMMENT"

  // $ANTLR start "T__26"
  public final void mT__26() throws RecognitionException {
    try {
      int _type = T__26;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // InternalXSOData.g:26:7: ( ':' )
      // InternalXSOData.g:26:9: ':'
      {
        match(':');

      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }
  // $ANTLR end "RULE_SL_COMMENT"

  // $ANTLR start "T__27"
  public final void mT__27() throws RecognitionException {
    try {
      int _type = T__27;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // InternalXSOData.g:27:7: ( '.xsjslib' )
      // InternalXSOData.g:27:9: '.xsjslib'
      {
        match(".xsjslib");


      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }
  // $ANTLR end "RULE_WS"

  // $ANTLR start "T__28"
  public final void mT__28() throws RecognitionException {
    try {
      int _type = T__28;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // InternalXSOData.g:28:7: ( 'delete' )
      // InternalXSOData.g:28:9: 'delete'
      {
        match("delete");


      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }
  // $ANTLR end "RULE_ANY_OTHER"

  // $ANTLR start "T__29"
  public final void mT__29() throws RecognitionException {
    try {
      int _type = T__29;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // InternalXSOData.g:29:7: ( 'using' )
      // InternalXSOData.g:29:9: 'using'
      {
        match("using");


      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  // $ANTLR start "T__30"
  public final void mT__30() throws RecognitionException {
    try {
      int _type = T__30;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // InternalXSOData.g:30:7: ( 'update' )
      // InternalXSOData.g:30:9: 'update'
      {
        match("update");


      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  // $ANTLR start "T__31"
  public final void mT__31() throws RecognitionException {
    try {
      int _type = T__31;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // InternalXSOData.g:31:7: ( 'forbidden' )
      // InternalXSOData.g:31:9: 'forbidden'
      {
        match("forbidden");


      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  // $ANTLR start "T__32"
  public final void mT__32() throws RecognitionException {
    try {
      int _type = T__32;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // InternalXSOData.g:32:7: ( 'association' )
      // InternalXSOData.g:32:9: 'association'
      {
        match("association");


      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  // $ANTLR start "T__33"
  public final void mT__33() throws RecognitionException {
    try {
      int _type = T__33;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // InternalXSOData.g:33:7: ( 'principal' )
      // InternalXSOData.g:33:9: 'principal'
      {
        match("principal");


      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  // $ANTLR start "T__34"
  public final void mT__34() throws RecognitionException {
    try {
      int _type = T__34;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // InternalXSOData.g:34:7: ( 'multiplicity' )
      // InternalXSOData.g:34:9: 'multiplicity'
      {
        match("multiplicity");


      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  // $ANTLR start "T__35"
  public final void mT__35() throws RecognitionException {
    try {
      int _type = T__35;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // InternalXSOData.g:35:7: ( 'dependent' )
      // InternalXSOData.g:35:9: 'dependent'
      {
        match("dependent");


      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  // $ANTLR start "T__36"
  public final void mT__36() throws RecognitionException {
    try {
      int _type = T__36;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // InternalXSOData.g:36:7: ( '*' )
      // InternalXSOData.g:36:9: '*'
      {
        match('*');

      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  // $ANTLR start "T__37"
  public final void mT__37() throws RecognitionException {
    try {
      int _type = T__37;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // InternalXSOData.g:37:7: ( '1' )
      // InternalXSOData.g:37:9: '1'
      {
        match('1');

      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  // $ANTLR start "RULE_ID"
  public final void mRULE_ID() throws RecognitionException {
    try {
      int _type = RULE_ID;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // InternalXSOData.g:1120:9: ( ( '^' )? ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )* )
      // InternalXSOData.g:1120:11: ( '^' )? ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
      {
        // InternalXSOData.g:1120:11: ( '^' )?
        int alt1 = 2;
        int LA1_0 = input.LA(1);

        if ((LA1_0 == '^')) {
          alt1 = 1;
        }
        switch (alt1) {
          case 1:
            // InternalXSOData.g:1120:11: '^'
          {
            match('^');

          }
          break;

        }

        if ((input.LA(1) >= 'A' && input.LA(1) <= 'Z') || input.LA(1) == '_' || (input.LA(1) >= 'a' && input.LA(1) <= 'z')) {
          input.consume();

        } else {
          MismatchedSetException mse = new MismatchedSetException(null, input);
          recover(mse);
          throw mse;
        }

        // InternalXSOData.g:1120:40: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
        loop2:
        do {
          int alt2 = 2;
          int LA2_0 = input.LA(1);

          if (((LA2_0 >= '0' && LA2_0 <= '9') || (LA2_0 >= 'A' && LA2_0 <= 'Z') || LA2_0 == '_' || (LA2_0 >= 'a' && LA2_0 <= 'z'))) {
            alt2 = 1;
          }

          switch (alt2) {
            case 1:
              // InternalXSOData.g:
            {
              if ((input.LA(1) >= '0' && input.LA(1) <= '9') || (input.LA(1) >= 'A' && input.LA(1) <= 'Z') || input.LA(1) == '_' || (
                  input.LA(1) >= 'a' && input.LA(1) <= 'z')) {
                input.consume();

              } else {
                MismatchedSetException mse = new MismatchedSetException(null, input);
                recover(mse);
                throw mse;
              }


            }
            break;

            default:
              break loop2;
          }
        } while (true);


      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  // $ANTLR start "RULE_INT"
  public final void mRULE_INT() throws RecognitionException {
    try {
      int _type = RULE_INT;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // InternalXSOData.g:1122:10: ( ( '0' .. '9' )+ )
      // InternalXSOData.g:1122:12: ( '0' .. '9' )+
      {
        // InternalXSOData.g:1122:12: ( '0' .. '9' )+
        int cnt3 = 0;
        loop3:
        do {
          int alt3 = 2;
          int LA3_0 = input.LA(1);

          if (((LA3_0 >= '0' && LA3_0 <= '9'))) {
            alt3 = 1;
          }

          switch (alt3) {
            case 1:
              // InternalXSOData.g:1122:13: '0' .. '9'
            {
              matchRange('0', '9');

            }
            break;

            default:
              if (cnt3 >= 1) {
                break loop3;
              }
              EarlyExitException eee =
                  new EarlyExitException(3, input);
              throw eee;
          }
          cnt3++;
        } while (true);


      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  // $ANTLR start "RULE_STRING"
  public final void mRULE_STRING() throws RecognitionException {
    try {
      int _type = RULE_STRING;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // InternalXSOData.g:1124:13: ( ( '\"' ( '\\\\' . | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' . | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' ) )
      // InternalXSOData.g:1124:15: ( '\"' ( '\\\\' . | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' . | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' )
      {
        // InternalXSOData.g:1124:15: ( '\"' ( '\\\\' . | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' . | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' )
        int alt6 = 2;
        int LA6_0 = input.LA(1);

        if ((LA6_0 == '\"')) {
          alt6 = 1;
        } else if ((LA6_0 == '\'')) {
          alt6 = 2;
        } else {
          NoViableAltException nvae =
              new NoViableAltException("", 6, 0, input);

          throw nvae;
        }
        switch (alt6) {
          case 1:
            // InternalXSOData.g:1124:16: '\"' ( '\\\\' . | ~ ( ( '\\\\' | '\"' ) ) )* '\"'
          {
            match('\"');
            // InternalXSOData.g:1124:20: ( '\\\\' . | ~ ( ( '\\\\' | '\"' ) ) )*
            loop4:
            do {
              int alt4 = 3;
              int LA4_0 = input.LA(1);

              if ((LA4_0 == '\\')) {
                alt4 = 1;
              } else if (((LA4_0 >= '\u0000' && LA4_0 <= '!') || (LA4_0 >= '#' && LA4_0 <= '[') || (LA4_0 >= ']' && LA4_0 <= '\uFFFF'))) {
                alt4 = 2;
              }

              switch (alt4) {
                case 1:
                  // InternalXSOData.g:1124:21: '\\\\' .
                {
                  match('\\');
                  matchAny();

                }
                break;
                case 2:
                  // InternalXSOData.g:1124:28: ~ ( ( '\\\\' | '\"' ) )
                {
                  if ((input.LA(1) >= '\u0000' && input.LA(1) <= '!') || (input.LA(1) >= '#' && input.LA(1) <= '[') || (input.LA(1) >= ']'
                      && input.LA(1) <= '\uFFFF')) {
                    input.consume();

                  } else {
                    MismatchedSetException mse = new MismatchedSetException(null, input);
                    recover(mse);
                    throw mse;
                  }


                }
                break;

                default:
                  break loop4;
              }
            } while (true);

            match('\"');

          }
          break;
          case 2:
            // InternalXSOData.g:1124:48: '\\'' ( '\\\\' . | ~ ( ( '\\\\' | '\\'' ) ) )* '\\''
          {
            match('\'');
            // InternalXSOData.g:1124:53: ( '\\\\' . | ~ ( ( '\\\\' | '\\'' ) ) )*
            loop5:
            do {
              int alt5 = 3;
              int LA5_0 = input.LA(1);

              if ((LA5_0 == '\\')) {
                alt5 = 1;
              } else if (((LA5_0 >= '\u0000' && LA5_0 <= '&') || (LA5_0 >= '(' && LA5_0 <= '[') || (LA5_0 >= ']' && LA5_0 <= '\uFFFF'))) {
                alt5 = 2;
              }

              switch (alt5) {
                case 1:
                  // InternalXSOData.g:1124:54: '\\\\' .
                {
                  match('\\');
                  matchAny();

                }
                break;
                case 2:
                  // InternalXSOData.g:1124:61: ~ ( ( '\\\\' | '\\'' ) )
                {
                  if ((input.LA(1) >= '\u0000' && input.LA(1) <= '&') || (input.LA(1) >= '(' && input.LA(1) <= '[') || (input.LA(1) >= ']'
                      && input.LA(1) <= '\uFFFF')) {
                    input.consume();

                  } else {
                    MismatchedSetException mse = new MismatchedSetException(null, input);
                    recover(mse);
                    throw mse;
                  }


                }
                break;

                default:
                  break loop5;
              }
            } while (true);

            match('\'');

          }
          break;

        }


      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  // $ANTLR start "RULE_ML_COMMENT"
  public final void mRULE_ML_COMMENT() throws RecognitionException {
    try {
      int _type = RULE_ML_COMMENT;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // InternalXSOData.g:1126:17: ( '/*' ( options {greedy=false; } : . )* '*/' )
      // InternalXSOData.g:1126:19: '/*' ( options {greedy=false; } : . )* '*/'
      {
        match("/*");

        // InternalXSOData.g:1126:24: ( options {greedy=false; } : . )*
        loop7:
        do {
          int alt7 = 2;
          int LA7_0 = input.LA(1);

          if ((LA7_0 == '*')) {
            int LA7_1 = input.LA(2);

            if ((LA7_1 == '/')) {
              alt7 = 2;
            } else if (((LA7_1 >= '\u0000' && LA7_1 <= '.') || (LA7_1 >= '0' && LA7_1 <= '\uFFFF'))) {
              alt7 = 1;
            }


          } else if (((LA7_0 >= '\u0000' && LA7_0 <= ')') || (LA7_0 >= '+' && LA7_0 <= '\uFFFF'))) {
            alt7 = 1;
          }

          switch (alt7) {
            case 1:
              // InternalXSOData.g:1126:52: .
            {
              matchAny();

            }
            break;

            default:
              break loop7;
          }
        } while (true);

        match("*/");


      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  // $ANTLR start "RULE_SL_COMMENT"
  public final void mRULE_SL_COMMENT() throws RecognitionException {
    try {
      int _type = RULE_SL_COMMENT;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // InternalXSOData.g:1128:17: ( '//' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )? )
      // InternalXSOData.g:1128:19: '//' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )?
      {
        match("//");

        // InternalXSOData.g:1128:24: (~ ( ( '\\n' | '\\r' ) ) )*
        loop8:
        do {
          int alt8 = 2;
          int LA8_0 = input.LA(1);

          if (((LA8_0 >= '\u0000' && LA8_0 <= '\t') || (LA8_0 >= '\u000B' && LA8_0 <= '\f') || (LA8_0 >= '\u000E' && LA8_0 <= '\uFFFF'))) {
            alt8 = 1;
          }

          switch (alt8) {
            case 1:
              // InternalXSOData.g:1128:24: ~ ( ( '\\n' | '\\r' ) )
            {
              if ((input.LA(1) >= '\u0000' && input.LA(1) <= '\t') || (input.LA(1) >= '\u000B' && input.LA(1) <= '\f') || (
                  input.LA(1) >= '\u000E' && input.LA(1) <= '\uFFFF')) {
                input.consume();

              } else {
                MismatchedSetException mse = new MismatchedSetException(null, input);
                recover(mse);
                throw mse;
              }


            }
            break;

            default:
              break loop8;
          }
        } while (true);

        // InternalXSOData.g:1128:40: ( ( '\\r' )? '\\n' )?
        int alt10 = 2;
        int LA10_0 = input.LA(1);

        if ((LA10_0 == '\n' || LA10_0 == '\r')) {
          alt10 = 1;
        }
        switch (alt10) {
          case 1:
            // InternalXSOData.g:1128:41: ( '\\r' )? '\\n'
          {
            // InternalXSOData.g:1128:41: ( '\\r' )?
            int alt9 = 2;
            int LA9_0 = input.LA(1);

            if ((LA9_0 == '\r')) {
              alt9 = 1;
            }
            switch (alt9) {
              case 1:
                // InternalXSOData.g:1128:41: '\\r'
              {
                match('\r');

              }
              break;

            }

            match('\n');

          }
          break;

        }


      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  // $ANTLR start "RULE_WS"
  public final void mRULE_WS() throws RecognitionException {
    try {
      int _type = RULE_WS;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // InternalXSOData.g:1130:9: ( ( ' ' | '\\t' | '\\r' | '\\n' )+ )
      // InternalXSOData.g:1130:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
      {
        // InternalXSOData.g:1130:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
        int cnt11 = 0;
        loop11:
        do {
          int alt11 = 2;
          int LA11_0 = input.LA(1);

          if (((LA11_0 >= '\t' && LA11_0 <= '\n') || LA11_0 == '\r' || LA11_0 == ' ')) {
            alt11 = 1;
          }

          switch (alt11) {
            case 1:
              // InternalXSOData.g:
            {
              if ((input.LA(1) >= '\t' && input.LA(1) <= '\n') || input.LA(1) == '\r' || input.LA(1) == ' ') {
                input.consume();

              } else {
                MismatchedSetException mse = new MismatchedSetException(null, input);
                recover(mse);
                throw mse;
              }


            }
            break;

            default:
              if (cnt11 >= 1) {
                break loop11;
              }
              EarlyExitException eee =
                  new EarlyExitException(11, input);
              throw eee;
          }
          cnt11++;
        } while (true);


      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  // $ANTLR start "RULE_ANY_OTHER"
  public final void mRULE_ANY_OTHER() throws RecognitionException {
    try {
      int _type = RULE_ANY_OTHER;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // InternalXSOData.g:1132:16: ( . )
      // InternalXSOData.g:1132:18: .
      {
        matchAny();

      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  public void mTokens() throws RecognitionException {
    // InternalXSOData.g:1:8: ( T__11 | T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | RULE_ID | RULE_INT | RULE_STRING | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_WS | RULE_ANY_OTHER )
    int alt12 = 34;
    alt12 = dfa12.predict(input);
    switch (alt12) {
      case 1:
        // InternalXSOData.g:1:10: T__11
      {
        mT__11();

      }
      break;
      case 2:
        // InternalXSOData.g:1:16: T__12
      {
        mT__12();

      }
      break;
      case 3:
        // InternalXSOData.g:1:22: T__13
      {
        mT__13();

      }
      break;
      case 4:
        // InternalXSOData.g:1:28: T__14
      {
        mT__14();

      }
      break;
      case 5:
        // InternalXSOData.g:1:34: T__15
      {
        mT__15();

      }
      break;
      case 6:
        // InternalXSOData.g:1:40: T__16
      {
        mT__16();

      }
      break;
      case 7:
        // InternalXSOData.g:1:46: T__17
      {
        mT__17();

      }
      break;
      case 8:
        // InternalXSOData.g:1:52: T__18
      {
        mT__18();

      }
      break;
      case 9:
        // InternalXSOData.g:1:58: T__19
      {
        mT__19();

      }
      break;
      case 10:
        // InternalXSOData.g:1:64: T__20
      {
        mT__20();

      }
      break;
      case 11:
        // InternalXSOData.g:1:70: T__21
      {
        mT__21();

      }
      break;
      case 12:
        // InternalXSOData.g:1:76: T__22
      {
        mT__22();

      }
      break;
      case 13:
        // InternalXSOData.g:1:82: T__23
      {
        mT__23();

      }
      break;
      case 14:
        // InternalXSOData.g:1:88: T__24
      {
        mT__24();

      }
      break;
      case 15:
        // InternalXSOData.g:1:94: T__25
      {
        mT__25();

      }
      break;
      case 16:
        // InternalXSOData.g:1:100: T__26
      {
        mT__26();

      }
      break;
      case 17:
        // InternalXSOData.g:1:106: T__27
      {
        mT__27();

      }
      break;
      case 18:
        // InternalXSOData.g:1:112: T__28
      {
        mT__28();

      }
      break;
      case 19:
        // InternalXSOData.g:1:118: T__29
      {
        mT__29();

      }
      break;
      case 20:
        // InternalXSOData.g:1:124: T__30
      {
        mT__30();

      }
      break;
      case 21:
        // InternalXSOData.g:1:130: T__31
      {
        mT__31();

      }
      break;
      case 22:
        // InternalXSOData.g:1:136: T__32
      {
        mT__32();

      }
      break;
      case 23:
        // InternalXSOData.g:1:142: T__33
      {
        mT__33();

      }
      break;
      case 24:
        // InternalXSOData.g:1:148: T__34
      {
        mT__34();

      }
      break;
      case 25:
        // InternalXSOData.g:1:154: T__35
      {
        mT__35();

      }
      break;
      case 26:
        // InternalXSOData.g:1:160: T__36
      {
        mT__36();

      }
      break;
      case 27:
        // InternalXSOData.g:1:166: T__37
      {
        mT__37();

      }
      break;
      case 28:
        // InternalXSOData.g:1:172: RULE_ID
      {
        mRULE_ID();

      }
      break;
      case 29:
        // InternalXSOData.g:1:180: RULE_INT
      {
        mRULE_INT();

      }
      break;
      case 30:
        // InternalXSOData.g:1:189: RULE_STRING
      {
        mRULE_STRING();

      }
      break;
      case 31:
        // InternalXSOData.g:1:201: RULE_ML_COMMENT
      {
        mRULE_ML_COMMENT();

      }
      break;
      case 32:
        // InternalXSOData.g:1:217: RULE_SL_COMMENT
      {
        mRULE_SL_COMMENT();

      }
      break;
      case 33:
        // InternalXSOData.g:1:233: RULE_WS
      {
        mRULE_WS();

      }
      break;
      case 34:
        // InternalXSOData.g:1:241: RULE_ANY_OTHER
      {
        mRULE_ANY_OTHER();

      }
      break;

    }

  }

  class DFA12 extends DFA {

    public DFA12(BaseRecognizer recognizer) {
      this.recognizer = recognizer;
      this.decisionNumber = 12;
      this.eot = DFA12_eot;
      this.eof = DFA12_eof;
      this.min = DFA12_min;
      this.max = DFA12_max;
      this.accept = DFA12_accept;
      this.special = DFA12_special;
      this.transition = DFA12_transition;
    }

    public String getDescription() {
      return "1:1: Tokens : ( T__11 | T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | RULE_ID | RULE_INT | RULE_STRING | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_WS | RULE_ANY_OTHER );";
    }

    public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
      IntStream input = _input;
      int _s = s;
      switch (s) {
        case 0:
          int LA12_24 = input.LA(1);

          s = -1;
          if (((LA12_24 >= '\u0000' && LA12_24 <= '\uFFFF'))) {
            s = 54;
          } else {
            s = 28;
          }

          if (s >= 0) {
            return s;
          }
          break;
        case 1:
          int LA12_0 = input.LA(1);

          s = -1;
          if ((LA12_0 == 's')) {
            s = 1;
          } else if ((LA12_0 == 'n')) {
            s = 2;
          } else if ((LA12_0 == '{')) {
            s = 3;
          } else if ((LA12_0 == '}')) {
            s = 4;
          } else if ((LA12_0 == '.')) {
            s = 5;
          } else if ((LA12_0 == ':')) {
            s = 6;
          } else if ((LA12_0 == 'a')) {
            s = 7;
          } else if ((LA12_0 == ';')) {
            s = 8;
          } else if ((LA12_0 == '(')) {
            s = 9;
          } else if ((LA12_0 == ')')) {
            s = 10;
          } else if ((LA12_0 == 'c')) {
            s = 11;
          } else if ((LA12_0 == 'e')) {
            s = 12;
          } else if ((LA12_0 == 'b')) {
            s = 13;
          } else if ((LA12_0 == 'd')) {
            s = 14;
          } else if ((LA12_0 == 'u')) {
            s = 15;
          } else if ((LA12_0 == 'f')) {
            s = 16;
          } else if ((LA12_0 == 'p')) {
            s = 17;
          } else if ((LA12_0 == 'm')) {
            s = 18;
          } else if ((LA12_0 == '*')) {
            s = 19;
          } else if ((LA12_0 == '1')) {
            s = 20;
          } else if ((LA12_0 == '^')) {
            s = 21;
          } else if (((LA12_0 >= 'A' && LA12_0 <= 'Z') || LA12_0 == '_' || (LA12_0 >= 'g' && LA12_0 <= 'l') || LA12_0 == 'o' || (
              LA12_0 >= 'q' && LA12_0 <= 'r') || LA12_0 == 't' || (LA12_0 >= 'v' && LA12_0 <= 'z'))) {
            s = 22;
          } else if ((LA12_0 == '0' || (LA12_0 >= '2' && LA12_0 <= '9'))) {
            s = 23;
          } else if ((LA12_0 == '\"')) {
            s = 24;
          } else if ((LA12_0 == '\'')) {
            s = 25;
          } else if ((LA12_0 == '/')) {
            s = 26;
          } else if (((LA12_0 >= '\t' && LA12_0 <= '\n') || LA12_0 == '\r' || LA12_0 == ' ')) {
            s = 27;
          } else if (((LA12_0 >= '\u0000' && LA12_0 <= '\b') || (LA12_0 >= '\u000B' && LA12_0 <= '\f') || (LA12_0 >= '\u000E'
              && LA12_0 <= '\u001F') || LA12_0 == '!' || (LA12_0 >= '#' && LA12_0 <= '&') || (LA12_0 >= '+' && LA12_0 <= '-') || (
              LA12_0 >= '<' && LA12_0 <= '@') || (LA12_0 >= '[' && LA12_0 <= ']') || LA12_0 == '`' || LA12_0 == '|' || (LA12_0 >= '~'
              && LA12_0 <= '\uFFFF'))) {
            s = 28;
          }

          if (s >= 0) {
            return s;
          }
          break;
        case 2:
          int LA12_25 = input.LA(1);

          s = -1;
          if (((LA12_25 >= '\u0000' && LA12_25 <= '\uFFFF'))) {
            s = 54;
          } else {
            s = 28;
          }

          if (s >= 0) {
            return s;
          }
          break;
      }
      NoViableAltException nvae =
          new NoViableAltException(getDescription(), 12, _s, input);
      error(nvae);
      throw nvae;
    }
  }


}