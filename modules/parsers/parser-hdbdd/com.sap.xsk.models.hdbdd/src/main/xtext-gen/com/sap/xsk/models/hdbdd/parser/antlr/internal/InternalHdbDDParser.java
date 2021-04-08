package com.sap.xsk.models.hdbdd.parser.antlr.internal;

import com.sap.xsk.models.hdbdd.services.HdbDDGrammarAccess;
import org.antlr.runtime.BaseRecognizer;
import org.antlr.runtime.BitSet;
import org.antlr.runtime.DFA;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;

@SuppressWarnings("all")
public class InternalHdbDDParser extends AbstractInternalAntlrParser {

  public static final String[] tokenNames = new String[]{
      "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_INT", "RULE_STRING", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS",
      "RULE_ANY_OTHER", "'namespace'", "';'", "'.'", "'@Schema'", "':'", "'context'", "'{'", "'}'", "'type'", "'('", "')'", "'entity'",
      "'key'", "'[*]'", "'to'", "'on'", "'='"
  };
  public static final int RULE_STRING = 6;
  public static final int RULE_SL_COMMENT = 8;
  public static final int T__19 = 19;
  public static final int T__15 = 15;
  public static final int T__16 = 16;
  public static final int T__17 = 17;
  public static final int T__18 = 18;
  public static final int T__11 = 11;
  public static final int T__12 = 12;
  public static final int T__13 = 13;
  public static final int T__14 = 14;
  public static final int EOF = -1;
  public static final int RULE_ID = 4;
  public static final int RULE_WS = 9;
  public static final int RULE_ANY_OTHER = 10;
  public static final int T__26 = 26;
  public static final int T__27 = 27;
  public static final int RULE_INT = 5;
  public static final int T__22 = 22;
  public static final int RULE_ML_COMMENT = 7;
  public static final int T__23 = 23;
  public static final int T__24 = 24;
  public static final int T__25 = 25;
  public static final int T__20 = 20;
  public static final int T__21 = 21;

  // delegates
  // delegators
  public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
  public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000014802L});
  public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000000010L});
  public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000001000L});
  public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000002002L});
  public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000008000L});
  public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000020000L});
  public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x00000000004C0000L});
  // $ANTLR end "entryRuleHdbDD"
  public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000440000L});
  // $ANTLR end "ruleHdbDD"
  public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000101000L});
  // $ANTLR end "entryRuleType"
  public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000000020L});
  // $ANTLR end "ruleType"
  public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000000200000L});
  // $ANTLR end "entryRuleNamespace"
  public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000000840010L});
  // $ANTLR end "ruleNamespace"
  public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000000101040L});
  // $ANTLR end "entryRuleQualifiedName"
  public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000000001040L});
  // $ANTLR end "ruleQualifiedName"
  public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000001000000L});
  // $ANTLR end "entryRuleSchema"
  public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000002000000L});
  // $ANTLR end "ruleSchema"
  public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000000004000000L});
  // $ANTLR end "entryRuleContext"
  public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000000008000000L});
  // $ANTLR end "ruleContext"
  static final String dfa_1s = "\12\uffff";
  // $ANTLR end "entryRuleTypeDefinition"
  static final String dfa_2s = "\2\4\2\17\2\4\1\6\3\uffff";
  // $ANTLR end "ruleTypeDefinition"
  static final String dfa_3s = "\1\27\1\4\2\17\2\14\1\30\3\uffff";
  // $ANTLR end "entryRuleEntity"
  static final String dfa_4s = "\7\uffff\1\3\1\1\1\2";
  // $ANTLR end "ruleEntity"
  static final String dfa_5s = "\12\uffff}>";
  // $ANTLR end "entryRuleField"
  static final String[] dfa_6s = {
      "\1\2\22\uffff\1\1",
      "\1\3",
      "\1\4",
      "\1\5",
      "\1\6\1\uffff\1\7\5\uffff\1\7",
      "\1\10\1\uffff\1\7\5\uffff\1\7",
      "\1\10\5\uffff\1\10\7\uffff\1\10\3\uffff\1\11",
      "",
      "",
      ""
  };
  // $ANTLR end "ruleField"
  static final short[] dfa_1 = DFA.unpackEncodedString(dfa_1s);
  // $ANTLR end "entryRuleFieldPrimitive"
  static final char[] dfa_2 = DFA.unpackEncodedStringToUnsignedChars(dfa_2s);
  // $ANTLR end "ruleFieldPrimitive"
  static final char[] dfa_3 = DFA.unpackEncodedStringToUnsignedChars(dfa_3s);
  // $ANTLR end "entryRuleFieldType"
  static final short[] dfa_4 = DFA.unpackEncodedString(dfa_4s);
  // $ANTLR end "ruleFieldType"
  static final short[] dfa_5 = DFA.unpackEncodedString(dfa_5s);
  // $ANTLR end "entryRuleFieldReference"
  static final short[][] dfa_6 = unpackEncodedStringArray(dfa_6s);
  // $ANTLR end "ruleFieldReference"

  // Delegated rules
  protected DFA8 dfa8 = new DFA8(this);
  private HdbDDGrammarAccess grammarAccess;
  public InternalHdbDDParser(TokenStream input) {
    this(input, new RecognizerSharedState());
  }
  public InternalHdbDDParser(TokenStream input, RecognizerSharedState state) {
    super(input, state);

  }
  public InternalHdbDDParser(TokenStream input, HdbDDGrammarAccess grammarAccess) {
    this(input);
    this.grammarAccess = grammarAccess;
    registerRules(grammarAccess.getGrammar());
  }

  public String[] getTokenNames() {
    return InternalHdbDDParser.tokenNames;
  }

  public String getGrammarFileName() {
    return "InternalHdbDD.g";
  }

  @Override
  protected String getFirstRuleName() {
    return "HdbDD";
  }

  @Override
  protected HdbDDGrammarAccess getGrammarAccess() {
    return grammarAccess;
  }

  // $ANTLR start "entryRuleHdbDD"
  // InternalHdbDD.g:64:1: entryRuleHdbDD returns [EObject current=null] : iv_ruleHdbDD= ruleHdbDD EOF ;
  public final EObject entryRuleHdbDD() throws RecognitionException {
    EObject current = null;

    EObject iv_ruleHdbDD = null;

    try {
      // InternalHdbDD.g:64:46: (iv_ruleHdbDD= ruleHdbDD EOF )
      // InternalHdbDD.g:65:2: iv_ruleHdbDD= ruleHdbDD EOF
      {
        newCompositeNode(grammarAccess.getHdbDDRule());
        pushFollow(FOLLOW_1);
        iv_ruleHdbDD = ruleHdbDD();

        state._fsp--;

        current = iv_ruleHdbDD;
        match(input, EOF, FOLLOW_2);

      }

    } catch (RecognitionException re) {
      recover(input, re);
      appendSkippedTokens();
    } finally {
    }
    return current;
  }

  // $ANTLR start "ruleHdbDD"
  // InternalHdbDD.g:71:1: ruleHdbDD returns [EObject current=null] : ( (lv_elements_0_0= ruleType ) )* ;
  public final EObject ruleHdbDD() throws RecognitionException {
    EObject current = null;

    EObject lv_elements_0_0 = null;

    enterRule();

    try {
      // InternalHdbDD.g:77:2: ( ( (lv_elements_0_0= ruleType ) )* )
      // InternalHdbDD.g:78:2: ( (lv_elements_0_0= ruleType ) )*
      {
        // InternalHdbDD.g:78:2: ( (lv_elements_0_0= ruleType ) )*
        loop1:
        do {
          int alt1 = 2;
          int LA1_0 = input.LA(1);

          if ((LA1_0 == 11 || LA1_0 == 14 || LA1_0 == 16)) {
            alt1 = 1;
          }

          switch (alt1) {
            case 1:
              // InternalHdbDD.g:79:3: (lv_elements_0_0= ruleType )
            {
              // InternalHdbDD.g:79:3: (lv_elements_0_0= ruleType )
              // InternalHdbDD.g:80:4: lv_elements_0_0= ruleType
              {

                newCompositeNode(grammarAccess.getHdbDDAccess().getElementsTypeParserRuleCall_0());

                pushFollow(FOLLOW_3);
                lv_elements_0_0 = ruleType();

                state._fsp--;

                if (current == null) {
                  current = createModelElementForParent(grammarAccess.getHdbDDRule());
                }
                add(
                    current,
                    "elements",
                    lv_elements_0_0,
                    "com.sap.xsk.models.hdbdd.HdbDD.Type");
                afterParserOrEnumRuleCall();


              }


            }
            break;

            default:
              break loop1;
          }
        } while (true);


      }

      leaveRule();

    } catch (RecognitionException re) {
      recover(input, re);
      appendSkippedTokens();
    } finally {
    }
    return current;
  }

  // $ANTLR start "entryRuleType"
  // InternalHdbDD.g:100:1: entryRuleType returns [EObject current=null] : iv_ruleType= ruleType EOF ;
  public final EObject entryRuleType() throws RecognitionException {
    EObject current = null;

    EObject iv_ruleType = null;

    try {
      // InternalHdbDD.g:100:45: (iv_ruleType= ruleType EOF )
      // InternalHdbDD.g:101:2: iv_ruleType= ruleType EOF
      {
        newCompositeNode(grammarAccess.getTypeRule());
        pushFollow(FOLLOW_1);
        iv_ruleType = ruleType();

        state._fsp--;

        current = iv_ruleType;
        match(input, EOF, FOLLOW_2);

      }

    } catch (RecognitionException re) {
      recover(input, re);
      appendSkippedTokens();
    } finally {
    }
    return current;
  }

  // $ANTLR start "ruleType"
  // InternalHdbDD.g:107:1: ruleType returns [EObject current=null] : (this_Namespace_0= ruleNamespace | this_Schema_1= ruleSchema | this_Context_2= ruleContext ) ;
  public final EObject ruleType() throws RecognitionException {
    EObject current = null;

    EObject this_Namespace_0 = null;

    EObject this_Schema_1 = null;

    EObject this_Context_2 = null;

    enterRule();

    try {
      // InternalHdbDD.g:113:2: ( (this_Namespace_0= ruleNamespace | this_Schema_1= ruleSchema | this_Context_2= ruleContext ) )
      // InternalHdbDD.g:114:2: (this_Namespace_0= ruleNamespace | this_Schema_1= ruleSchema | this_Context_2= ruleContext )
      {
        // InternalHdbDD.g:114:2: (this_Namespace_0= ruleNamespace | this_Schema_1= ruleSchema | this_Context_2= ruleContext )
        int alt2 = 3;
        switch (input.LA(1)) {
          case 11: {
            alt2 = 1;
          }
          break;
          case 14: {
            alt2 = 2;
          }
          break;
          case 16: {
            alt2 = 3;
          }
          break;
          default:
            NoViableAltException nvae =
                new NoViableAltException("", 2, 0, input);

            throw nvae;
        }

        switch (alt2) {
          case 1:
            // InternalHdbDD.g:115:3: this_Namespace_0= ruleNamespace
          {

            newCompositeNode(grammarAccess.getTypeAccess().getNamespaceParserRuleCall_0());

            pushFollow(FOLLOW_2);
            this_Namespace_0 = ruleNamespace();

            state._fsp--;

            current = this_Namespace_0;
            afterParserOrEnumRuleCall();


          }
          break;
          case 2:
            // InternalHdbDD.g:124:3: this_Schema_1= ruleSchema
          {

            newCompositeNode(grammarAccess.getTypeAccess().getSchemaParserRuleCall_1());

            pushFollow(FOLLOW_2);
            this_Schema_1 = ruleSchema();

            state._fsp--;

            current = this_Schema_1;
            afterParserOrEnumRuleCall();


          }
          break;
          case 3:
            // InternalHdbDD.g:133:3: this_Context_2= ruleContext
          {

            newCompositeNode(grammarAccess.getTypeAccess().getContextParserRuleCall_2());

            pushFollow(FOLLOW_2);
            this_Context_2 = ruleContext();

            state._fsp--;

            current = this_Context_2;
            afterParserOrEnumRuleCall();


          }
          break;

        }


      }

      leaveRule();

    } catch (RecognitionException re) {
      recover(input, re);
      appendSkippedTokens();
    } finally {
    }
    return current;
  }

  // $ANTLR start "entryRuleNamespace"
  // InternalHdbDD.g:145:1: entryRuleNamespace returns [EObject current=null] : iv_ruleNamespace= ruleNamespace EOF ;
  public final EObject entryRuleNamespace() throws RecognitionException {
    EObject current = null;

    EObject iv_ruleNamespace = null;

    try {
      // InternalHdbDD.g:145:50: (iv_ruleNamespace= ruleNamespace EOF )
      // InternalHdbDD.g:146:2: iv_ruleNamespace= ruleNamespace EOF
      {
        newCompositeNode(grammarAccess.getNamespaceRule());
        pushFollow(FOLLOW_1);
        iv_ruleNamespace = ruleNamespace();

        state._fsp--;

        current = iv_ruleNamespace;
        match(input, EOF, FOLLOW_2);

      }

    } catch (RecognitionException re) {
      recover(input, re);
      appendSkippedTokens();
    } finally {
    }
    return current;
  }

  // $ANTLR start "ruleNamespace"
  // InternalHdbDD.g:152:1: ruleNamespace returns [EObject current=null] : (otherlv_0= 'namespace' ( (lv_name_1_0= ruleQualifiedName ) ) otherlv_2= ';' ) ;
  public final EObject ruleNamespace() throws RecognitionException {
    EObject current = null;

    Token otherlv_0 = null;
    Token otherlv_2 = null;
    AntlrDatatypeRuleToken lv_name_1_0 = null;

    enterRule();

    try {
      // InternalHdbDD.g:158:2: ( (otherlv_0= 'namespace' ( (lv_name_1_0= ruleQualifiedName ) ) otherlv_2= ';' ) )
      // InternalHdbDD.g:159:2: (otherlv_0= 'namespace' ( (lv_name_1_0= ruleQualifiedName ) ) otherlv_2= ';' )
      {
        // InternalHdbDD.g:159:2: (otherlv_0= 'namespace' ( (lv_name_1_0= ruleQualifiedName ) ) otherlv_2= ';' )
        // InternalHdbDD.g:160:3: otherlv_0= 'namespace' ( (lv_name_1_0= ruleQualifiedName ) ) otherlv_2= ';'
        {
          otherlv_0 = (Token) match(input, 11, FOLLOW_4);

          newLeafNode(otherlv_0, grammarAccess.getNamespaceAccess().getNamespaceKeyword_0());

          // InternalHdbDD.g:164:3: ( (lv_name_1_0= ruleQualifiedName ) )
          // InternalHdbDD.g:165:4: (lv_name_1_0= ruleQualifiedName )
          {
            // InternalHdbDD.g:165:4: (lv_name_1_0= ruleQualifiedName )
            // InternalHdbDD.g:166:5: lv_name_1_0= ruleQualifiedName
            {

              newCompositeNode(grammarAccess.getNamespaceAccess().getNameQualifiedNameParserRuleCall_1_0());

              pushFollow(FOLLOW_5);
              lv_name_1_0 = ruleQualifiedName();

              state._fsp--;

              if (current == null) {
                current = createModelElementForParent(grammarAccess.getNamespaceRule());
              }
              set(
                  current,
                  "name",
                  lv_name_1_0,
                  "com.sap.xsk.models.hdbdd.HdbDD.QualifiedName");
              afterParserOrEnumRuleCall();


            }


          }

          otherlv_2 = (Token) match(input, 12, FOLLOW_2);

          newLeafNode(otherlv_2, grammarAccess.getNamespaceAccess().getSemicolonKeyword_2());


        }


      }

      leaveRule();

    } catch (RecognitionException re) {
      recover(input, re);
      appendSkippedTokens();
    } finally {
    }
    return current;
  }

  // $ANTLR start "entryRuleQualifiedName"
  // InternalHdbDD.g:191:1: entryRuleQualifiedName returns [String current=null] : iv_ruleQualifiedName= ruleQualifiedName EOF ;
  public final String entryRuleQualifiedName() throws RecognitionException {
    String current = null;

    AntlrDatatypeRuleToken iv_ruleQualifiedName = null;

    try {
      // InternalHdbDD.g:191:53: (iv_ruleQualifiedName= ruleQualifiedName EOF )
      // InternalHdbDD.g:192:2: iv_ruleQualifiedName= ruleQualifiedName EOF
      {
        newCompositeNode(grammarAccess.getQualifiedNameRule());
        pushFollow(FOLLOW_1);
        iv_ruleQualifiedName = ruleQualifiedName();

        state._fsp--;

        current = iv_ruleQualifiedName.getText();
        match(input, EOF, FOLLOW_2);

      }

    } catch (RecognitionException re) {
      recover(input, re);
      appendSkippedTokens();
    } finally {
    }
    return current;
  }

  // $ANTLR start "ruleQualifiedName"
  // InternalHdbDD.g:198:1: ruleQualifiedName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* ) ;
  public final AntlrDatatypeRuleToken ruleQualifiedName() throws RecognitionException {
    AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

    Token this_ID_0 = null;
    Token kw = null;
    Token this_ID_2 = null;

    enterRule();

    try {
      // InternalHdbDD.g:204:2: ( (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* ) )
      // InternalHdbDD.g:205:2: (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* )
      {
        // InternalHdbDD.g:205:2: (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* )
        // InternalHdbDD.g:206:3: this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )*
        {
          this_ID_0 = (Token) match(input, RULE_ID, FOLLOW_6);

          current.merge(this_ID_0);

          newLeafNode(this_ID_0, grammarAccess.getQualifiedNameAccess().getIDTerminalRuleCall_0());

          // InternalHdbDD.g:213:3: (kw= '.' this_ID_2= RULE_ID )*
          loop3:
          do {
            int alt3 = 2;
            int LA3_0 = input.LA(1);

            if ((LA3_0 == 13)) {
              alt3 = 1;
            }

            switch (alt3) {
              case 1:
                // InternalHdbDD.g:214:4: kw= '.' this_ID_2= RULE_ID
              {
                kw = (Token) match(input, 13, FOLLOW_4);

                current.merge(kw);
                newLeafNode(kw, grammarAccess.getQualifiedNameAccess().getFullStopKeyword_1_0());

                this_ID_2 = (Token) match(input, RULE_ID, FOLLOW_6);

                current.merge(this_ID_2);

                newLeafNode(this_ID_2, grammarAccess.getQualifiedNameAccess().getIDTerminalRuleCall_1_1());


              }
              break;

              default:
                break loop3;
            }
          } while (true);


        }


      }

      leaveRule();

    } catch (RecognitionException re) {
      recover(input, re);
      appendSkippedTokens();
    } finally {
    }
    return current;
  }

  // $ANTLR start "entryRuleSchema"
  // InternalHdbDD.g:231:1: entryRuleSchema returns [EObject current=null] : iv_ruleSchema= ruleSchema EOF ;
  public final EObject entryRuleSchema() throws RecognitionException {
    EObject current = null;

    EObject iv_ruleSchema = null;

    try {
      // InternalHdbDD.g:231:47: (iv_ruleSchema= ruleSchema EOF )
      // InternalHdbDD.g:232:2: iv_ruleSchema= ruleSchema EOF
      {
        newCompositeNode(grammarAccess.getSchemaRule());
        pushFollow(FOLLOW_1);
        iv_ruleSchema = ruleSchema();

        state._fsp--;

        current = iv_ruleSchema;
        match(input, EOF, FOLLOW_2);

      }

    } catch (RecognitionException re) {
      recover(input, re);
      appendSkippedTokens();
    } finally {
    }
    return current;
  }

  // $ANTLR start "ruleSchema"
  // InternalHdbDD.g:238:1: ruleSchema returns [EObject current=null] : (otherlv_0= '@Schema' otherlv_1= ':' ( (lv_name_2_0= RULE_ID ) ) ) ;
  public final EObject ruleSchema() throws RecognitionException {
    EObject current = null;

    Token otherlv_0 = null;
    Token otherlv_1 = null;
    Token lv_name_2_0 = null;

    enterRule();

    try {
      // InternalHdbDD.g:244:2: ( (otherlv_0= '@Schema' otherlv_1= ':' ( (lv_name_2_0= RULE_ID ) ) ) )
      // InternalHdbDD.g:245:2: (otherlv_0= '@Schema' otherlv_1= ':' ( (lv_name_2_0= RULE_ID ) ) )
      {
        // InternalHdbDD.g:245:2: (otherlv_0= '@Schema' otherlv_1= ':' ( (lv_name_2_0= RULE_ID ) ) )
        // InternalHdbDD.g:246:3: otherlv_0= '@Schema' otherlv_1= ':' ( (lv_name_2_0= RULE_ID ) )
        {
          otherlv_0 = (Token) match(input, 14, FOLLOW_7);

          newLeafNode(otherlv_0, grammarAccess.getSchemaAccess().getSchemaKeyword_0());

          otherlv_1 = (Token) match(input, 15, FOLLOW_4);

          newLeafNode(otherlv_1, grammarAccess.getSchemaAccess().getColonKeyword_1());

          // InternalHdbDD.g:254:3: ( (lv_name_2_0= RULE_ID ) )
          // InternalHdbDD.g:255:4: (lv_name_2_0= RULE_ID )
          {
            // InternalHdbDD.g:255:4: (lv_name_2_0= RULE_ID )
            // InternalHdbDD.g:256:5: lv_name_2_0= RULE_ID
            {
              lv_name_2_0 = (Token) match(input, RULE_ID, FOLLOW_2);

              newLeafNode(lv_name_2_0, grammarAccess.getSchemaAccess().getNameIDTerminalRuleCall_2_0());

              if (current == null) {
                current = createModelElement(grammarAccess.getSchemaRule());
              }
              setWithLastConsumed(
                  current,
                  "name",
                  lv_name_2_0,
                  "org.eclipse.xtext.common.Terminals.ID");


            }


          }


        }


      }

      leaveRule();

    } catch (RecognitionException re) {
      recover(input, re);
      appendSkippedTokens();
    } finally {
    }
    return current;
  }

  // $ANTLR start "entryRuleContext"
  // InternalHdbDD.g:276:1: entryRuleContext returns [EObject current=null] : iv_ruleContext= ruleContext EOF ;
  public final EObject entryRuleContext() throws RecognitionException {
    EObject current = null;

    EObject iv_ruleContext = null;

    try {
      // InternalHdbDD.g:276:48: (iv_ruleContext= ruleContext EOF )
      // InternalHdbDD.g:277:2: iv_ruleContext= ruleContext EOF
      {
        newCompositeNode(grammarAccess.getContextRule());
        pushFollow(FOLLOW_1);
        iv_ruleContext = ruleContext();

        state._fsp--;

        current = iv_ruleContext;
        match(input, EOF, FOLLOW_2);

      }

    } catch (RecognitionException re) {
      recover(input, re);
      appendSkippedTokens();
    } finally {
    }
    return current;
  }

  // $ANTLR start "ruleContext"
  // InternalHdbDD.g:283:1: ruleContext returns [EObject current=null] : (otherlv_0= 'context' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' ( (lv_types_3_0= ruleTypeDefinition ) )* ( (lv_entities_4_0= ruleEntity ) )* otherlv_5= '}' otherlv_6= ';' ) ;
  public final EObject ruleContext() throws RecognitionException {
    EObject current = null;

    Token otherlv_0 = null;
    Token lv_name_1_0 = null;
    Token otherlv_2 = null;
    Token otherlv_5 = null;
    Token otherlv_6 = null;
    EObject lv_types_3_0 = null;

    EObject lv_entities_4_0 = null;

    enterRule();

    try {
      // InternalHdbDD.g:289:2: ( (otherlv_0= 'context' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' ( (lv_types_3_0= ruleTypeDefinition ) )* ( (lv_entities_4_0= ruleEntity ) )* otherlv_5= '}' otherlv_6= ';' ) )
      // InternalHdbDD.g:290:2: (otherlv_0= 'context' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' ( (lv_types_3_0= ruleTypeDefinition ) )* ( (lv_entities_4_0= ruleEntity ) )* otherlv_5= '}' otherlv_6= ';' )
      {
        // InternalHdbDD.g:290:2: (otherlv_0= 'context' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' ( (lv_types_3_0= ruleTypeDefinition ) )* ( (lv_entities_4_0= ruleEntity ) )* otherlv_5= '}' otherlv_6= ';' )
        // InternalHdbDD.g:291:3: otherlv_0= 'context' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' ( (lv_types_3_0= ruleTypeDefinition ) )* ( (lv_entities_4_0= ruleEntity ) )* otherlv_5= '}' otherlv_6= ';'
        {
          otherlv_0 = (Token) match(input, 16, FOLLOW_4);

          newLeafNode(otherlv_0, grammarAccess.getContextAccess().getContextKeyword_0());

          // InternalHdbDD.g:295:3: ( (lv_name_1_0= RULE_ID ) )
          // InternalHdbDD.g:296:4: (lv_name_1_0= RULE_ID )
          {
            // InternalHdbDD.g:296:4: (lv_name_1_0= RULE_ID )
            // InternalHdbDD.g:297:5: lv_name_1_0= RULE_ID
            {
              lv_name_1_0 = (Token) match(input, RULE_ID, FOLLOW_8);

              newLeafNode(lv_name_1_0, grammarAccess.getContextAccess().getNameIDTerminalRuleCall_1_0());

              if (current == null) {
                current = createModelElement(grammarAccess.getContextRule());
              }
              setWithLastConsumed(
                  current,
                  "name",
                  lv_name_1_0,
                  "org.eclipse.xtext.common.Terminals.ID");


            }


          }

          otherlv_2 = (Token) match(input, 17, FOLLOW_9);

          newLeafNode(otherlv_2, grammarAccess.getContextAccess().getLeftCurlyBracketKeyword_2());

          // InternalHdbDD.g:317:3: ( (lv_types_3_0= ruleTypeDefinition ) )*
          loop4:
          do {
            int alt4 = 2;
            int LA4_0 = input.LA(1);

            if ((LA4_0 == 19)) {
              alt4 = 1;
            }

            switch (alt4) {
              case 1:
                // InternalHdbDD.g:318:4: (lv_types_3_0= ruleTypeDefinition )
              {
                // InternalHdbDD.g:318:4: (lv_types_3_0= ruleTypeDefinition )
                // InternalHdbDD.g:319:5: lv_types_3_0= ruleTypeDefinition
                {

                  newCompositeNode(grammarAccess.getContextAccess().getTypesTypeDefinitionParserRuleCall_3_0());

                  pushFollow(FOLLOW_9);
                  lv_types_3_0 = ruleTypeDefinition();

                  state._fsp--;

                  if (current == null) {
                    current = createModelElementForParent(grammarAccess.getContextRule());
                  }
                  add(
                      current,
                      "types",
                      lv_types_3_0,
                      "com.sap.xsk.models.hdbdd.HdbDD.TypeDefinition");
                  afterParserOrEnumRuleCall();


                }


              }
              break;

              default:
                break loop4;
            }
          } while (true);

          // InternalHdbDD.g:336:3: ( (lv_entities_4_0= ruleEntity ) )*
          loop5:
          do {
            int alt5 = 2;
            int LA5_0 = input.LA(1);

            if ((LA5_0 == 22)) {
              alt5 = 1;
            }

            switch (alt5) {
              case 1:
                // InternalHdbDD.g:337:4: (lv_entities_4_0= ruleEntity )
              {
                // InternalHdbDD.g:337:4: (lv_entities_4_0= ruleEntity )
                // InternalHdbDD.g:338:5: lv_entities_4_0= ruleEntity
                {

                  newCompositeNode(grammarAccess.getContextAccess().getEntitiesEntityParserRuleCall_4_0());

                  pushFollow(FOLLOW_10);
                  lv_entities_4_0 = ruleEntity();

                  state._fsp--;

                  if (current == null) {
                    current = createModelElementForParent(grammarAccess.getContextRule());
                  }
                  add(
                      current,
                      "entities",
                      lv_entities_4_0,
                      "com.sap.xsk.models.hdbdd.HdbDD.Entity");
                  afterParserOrEnumRuleCall();


                }


              }
              break;

              default:
                break loop5;
            }
          } while (true);

          otherlv_5 = (Token) match(input, 18, FOLLOW_5);

          newLeafNode(otherlv_5, grammarAccess.getContextAccess().getRightCurlyBracketKeyword_5());

          otherlv_6 = (Token) match(input, 12, FOLLOW_2);

          newLeafNode(otherlv_6, grammarAccess.getContextAccess().getSemicolonKeyword_6());


        }


      }

      leaveRule();

    } catch (RecognitionException re) {
      recover(input, re);
      appendSkippedTokens();
    } finally {
    }
    return current;
  }

  // $ANTLR start "entryRuleTypeDefinition"
  // InternalHdbDD.g:367:1: entryRuleTypeDefinition returns [EObject current=null] : iv_ruleTypeDefinition= ruleTypeDefinition EOF ;
  public final EObject entryRuleTypeDefinition() throws RecognitionException {
    EObject current = null;

    EObject iv_ruleTypeDefinition = null;

    try {
      // InternalHdbDD.g:367:55: (iv_ruleTypeDefinition= ruleTypeDefinition EOF )
      // InternalHdbDD.g:368:2: iv_ruleTypeDefinition= ruleTypeDefinition EOF
      {
        newCompositeNode(grammarAccess.getTypeDefinitionRule());
        pushFollow(FOLLOW_1);
        iv_ruleTypeDefinition = ruleTypeDefinition();

        state._fsp--;

        current = iv_ruleTypeDefinition;
        match(input, EOF, FOLLOW_2);

      }

    } catch (RecognitionException re) {
      recover(input, re);
      appendSkippedTokens();
    } finally {
    }
    return current;
  }

  // $ANTLR start "ruleTypeDefinition"
  // InternalHdbDD.g:374:1: ruleTypeDefinition returns [EObject current=null] : (otherlv_0= 'type' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= ':' ( (lv_fieldType_3_0= RULE_ID ) ) (otherlv_4= '(' ( (lv_fieldLength_5_0= RULE_INT ) ) otherlv_6= ')' )? otherlv_7= ';' ) ;
  public final EObject ruleTypeDefinition() throws RecognitionException {
    EObject current = null;

    Token otherlv_0 = null;
    Token lv_name_1_0 = null;
    Token otherlv_2 = null;
    Token lv_fieldType_3_0 = null;
    Token otherlv_4 = null;
    Token lv_fieldLength_5_0 = null;
    Token otherlv_6 = null;
    Token otherlv_7 = null;

    enterRule();

    try {
      // InternalHdbDD.g:380:2: ( (otherlv_0= 'type' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= ':' ( (lv_fieldType_3_0= RULE_ID ) ) (otherlv_4= '(' ( (lv_fieldLength_5_0= RULE_INT ) ) otherlv_6= ')' )? otherlv_7= ';' ) )
      // InternalHdbDD.g:381:2: (otherlv_0= 'type' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= ':' ( (lv_fieldType_3_0= RULE_ID ) ) (otherlv_4= '(' ( (lv_fieldLength_5_0= RULE_INT ) ) otherlv_6= ')' )? otherlv_7= ';' )
      {
        // InternalHdbDD.g:381:2: (otherlv_0= 'type' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= ':' ( (lv_fieldType_3_0= RULE_ID ) ) (otherlv_4= '(' ( (lv_fieldLength_5_0= RULE_INT ) ) otherlv_6= ')' )? otherlv_7= ';' )
        // InternalHdbDD.g:382:3: otherlv_0= 'type' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= ':' ( (lv_fieldType_3_0= RULE_ID ) ) (otherlv_4= '(' ( (lv_fieldLength_5_0= RULE_INT ) ) otherlv_6= ')' )? otherlv_7= ';'
        {
          otherlv_0 = (Token) match(input, 19, FOLLOW_4);

          newLeafNode(otherlv_0, grammarAccess.getTypeDefinitionAccess().getTypeKeyword_0());

          // InternalHdbDD.g:386:3: ( (lv_name_1_0= RULE_ID ) )
          // InternalHdbDD.g:387:4: (lv_name_1_0= RULE_ID )
          {
            // InternalHdbDD.g:387:4: (lv_name_1_0= RULE_ID )
            // InternalHdbDD.g:388:5: lv_name_1_0= RULE_ID
            {
              lv_name_1_0 = (Token) match(input, RULE_ID, FOLLOW_7);

              newLeafNode(lv_name_1_0, grammarAccess.getTypeDefinitionAccess().getNameIDTerminalRuleCall_1_0());

              if (current == null) {
                current = createModelElement(grammarAccess.getTypeDefinitionRule());
              }
              setWithLastConsumed(
                  current,
                  "name",
                  lv_name_1_0,
                  "org.eclipse.xtext.common.Terminals.ID");


            }


          }

          otherlv_2 = (Token) match(input, 15, FOLLOW_4);

          newLeafNode(otherlv_2, grammarAccess.getTypeDefinitionAccess().getColonKeyword_2());

          // InternalHdbDD.g:408:3: ( (lv_fieldType_3_0= RULE_ID ) )
          // InternalHdbDD.g:409:4: (lv_fieldType_3_0= RULE_ID )
          {
            // InternalHdbDD.g:409:4: (lv_fieldType_3_0= RULE_ID )
            // InternalHdbDD.g:410:5: lv_fieldType_3_0= RULE_ID
            {
              lv_fieldType_3_0 = (Token) match(input, RULE_ID, FOLLOW_11);

              newLeafNode(lv_fieldType_3_0, grammarAccess.getTypeDefinitionAccess().getFieldTypeIDTerminalRuleCall_3_0());

              if (current == null) {
                current = createModelElement(grammarAccess.getTypeDefinitionRule());
              }
              setWithLastConsumed(
                  current,
                  "fieldType",
                  lv_fieldType_3_0,
                  "org.eclipse.xtext.common.Terminals.ID");


            }


          }

          // InternalHdbDD.g:426:3: (otherlv_4= '(' ( (lv_fieldLength_5_0= RULE_INT ) ) otherlv_6= ')' )?
          int alt6 = 2;
          int LA6_0 = input.LA(1);

          if ((LA6_0 == 20)) {
            alt6 = 1;
          }
          switch (alt6) {
            case 1:
              // InternalHdbDD.g:427:4: otherlv_4= '(' ( (lv_fieldLength_5_0= RULE_INT ) ) otherlv_6= ')'
            {
              otherlv_4 = (Token) match(input, 20, FOLLOW_12);

              newLeafNode(otherlv_4, grammarAccess.getTypeDefinitionAccess().getLeftParenthesisKeyword_4_0());

              // InternalHdbDD.g:431:4: ( (lv_fieldLength_5_0= RULE_INT ) )
              // InternalHdbDD.g:432:5: (lv_fieldLength_5_0= RULE_INT )
              {
                // InternalHdbDD.g:432:5: (lv_fieldLength_5_0= RULE_INT )
                // InternalHdbDD.g:433:6: lv_fieldLength_5_0= RULE_INT
                {
                  lv_fieldLength_5_0 = (Token) match(input, RULE_INT, FOLLOW_13);

                  newLeafNode(lv_fieldLength_5_0, grammarAccess.getTypeDefinitionAccess().getFieldLengthINTTerminalRuleCall_4_1_0());

                  if (current == null) {
                    current = createModelElement(grammarAccess.getTypeDefinitionRule());
                  }
                  setWithLastConsumed(
                      current,
                      "fieldLength",
                      lv_fieldLength_5_0,
                      "org.eclipse.xtext.common.Terminals.INT");


                }


              }

              otherlv_6 = (Token) match(input, 21, FOLLOW_5);

              newLeafNode(otherlv_6, grammarAccess.getTypeDefinitionAccess().getRightParenthesisKeyword_4_2());


            }
            break;

          }

          otherlv_7 = (Token) match(input, 12, FOLLOW_2);

          newLeafNode(otherlv_7, grammarAccess.getTypeDefinitionAccess().getSemicolonKeyword_5());


        }


      }

      leaveRule();

    } catch (RecognitionException re) {
      recover(input, re);
      appendSkippedTokens();
    } finally {
    }
    return current;
  }

  // $ANTLR start "entryRuleEntity"
  // InternalHdbDD.g:462:1: entryRuleEntity returns [EObject current=null] : iv_ruleEntity= ruleEntity EOF ;
  public final EObject entryRuleEntity() throws RecognitionException {
    EObject current = null;

    EObject iv_ruleEntity = null;

    try {
      // InternalHdbDD.g:462:47: (iv_ruleEntity= ruleEntity EOF )
      // InternalHdbDD.g:463:2: iv_ruleEntity= ruleEntity EOF
      {
        newCompositeNode(grammarAccess.getEntityRule());
        pushFollow(FOLLOW_1);
        iv_ruleEntity = ruleEntity();

        state._fsp--;

        current = iv_ruleEntity;
        match(input, EOF, FOLLOW_2);

      }

    } catch (RecognitionException re) {
      recover(input, re);
      appendSkippedTokens();
    } finally {
    }
    return current;
  }

  // $ANTLR start "ruleEntity"
  // InternalHdbDD.g:469:1: ruleEntity returns [EObject current=null] : (otherlv_0= 'entity' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' ( (lv_fields_3_0= ruleField ) )* otherlv_4= '}' otherlv_5= ';' ) ;
  public final EObject ruleEntity() throws RecognitionException {
    EObject current = null;

    Token otherlv_0 = null;
    Token lv_name_1_0 = null;
    Token otherlv_2 = null;
    Token otherlv_4 = null;
    Token otherlv_5 = null;
    EObject lv_fields_3_0 = null;

    enterRule();

    try {
      // InternalHdbDD.g:475:2: ( (otherlv_0= 'entity' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' ( (lv_fields_3_0= ruleField ) )* otherlv_4= '}' otherlv_5= ';' ) )
      // InternalHdbDD.g:476:2: (otherlv_0= 'entity' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' ( (lv_fields_3_0= ruleField ) )* otherlv_4= '}' otherlv_5= ';' )
      {
        // InternalHdbDD.g:476:2: (otherlv_0= 'entity' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' ( (lv_fields_3_0= ruleField ) )* otherlv_4= '}' otherlv_5= ';' )
        // InternalHdbDD.g:477:3: otherlv_0= 'entity' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' ( (lv_fields_3_0= ruleField ) )* otherlv_4= '}' otherlv_5= ';'
        {
          otherlv_0 = (Token) match(input, 22, FOLLOW_4);

          newLeafNode(otherlv_0, grammarAccess.getEntityAccess().getEntityKeyword_0());

          // InternalHdbDD.g:481:3: ( (lv_name_1_0= RULE_ID ) )
          // InternalHdbDD.g:482:4: (lv_name_1_0= RULE_ID )
          {
            // InternalHdbDD.g:482:4: (lv_name_1_0= RULE_ID )
            // InternalHdbDD.g:483:5: lv_name_1_0= RULE_ID
            {
              lv_name_1_0 = (Token) match(input, RULE_ID, FOLLOW_8);

              newLeafNode(lv_name_1_0, grammarAccess.getEntityAccess().getNameIDTerminalRuleCall_1_0());

              if (current == null) {
                current = createModelElement(grammarAccess.getEntityRule());
              }
              setWithLastConsumed(
                  current,
                  "name",
                  lv_name_1_0,
                  "org.eclipse.xtext.common.Terminals.ID");


            }


          }

          otherlv_2 = (Token) match(input, 17, FOLLOW_14);

          newLeafNode(otherlv_2, grammarAccess.getEntityAccess().getLeftCurlyBracketKeyword_2());

          // InternalHdbDD.g:503:3: ( (lv_fields_3_0= ruleField ) )*
          loop7:
          do {
            int alt7 = 2;
            int LA7_0 = input.LA(1);

            if ((LA7_0 == RULE_ID || LA7_0 == 23)) {
              alt7 = 1;
            }

            switch (alt7) {
              case 1:
                // InternalHdbDD.g:504:4: (lv_fields_3_0= ruleField )
              {
                // InternalHdbDD.g:504:4: (lv_fields_3_0= ruleField )
                // InternalHdbDD.g:505:5: lv_fields_3_0= ruleField
                {

                  newCompositeNode(grammarAccess.getEntityAccess().getFieldsFieldParserRuleCall_3_0());

                  pushFollow(FOLLOW_14);
                  lv_fields_3_0 = ruleField();

                  state._fsp--;

                  if (current == null) {
                    current = createModelElementForParent(grammarAccess.getEntityRule());
                  }
                  add(
                      current,
                      "fields",
                      lv_fields_3_0,
                      "com.sap.xsk.models.hdbdd.HdbDD.Field");
                  afterParserOrEnumRuleCall();


                }


              }
              break;

              default:
                break loop7;
            }
          } while (true);

          otherlv_4 = (Token) match(input, 18, FOLLOW_5);

          newLeafNode(otherlv_4, grammarAccess.getEntityAccess().getRightCurlyBracketKeyword_4());

          otherlv_5 = (Token) match(input, 12, FOLLOW_2);

          newLeafNode(otherlv_5, grammarAccess.getEntityAccess().getSemicolonKeyword_5());


        }


      }

      leaveRule();

    } catch (RecognitionException re) {
      recover(input, re);
      appendSkippedTokens();
    } finally {
    }
    return current;
  }

  // $ANTLR start "entryRuleField"
  // InternalHdbDD.g:534:1: entryRuleField returns [EObject current=null] : iv_ruleField= ruleField EOF ;
  public final EObject entryRuleField() throws RecognitionException {
    EObject current = null;

    EObject iv_ruleField = null;

    try {
      // InternalHdbDD.g:534:46: (iv_ruleField= ruleField EOF )
      // InternalHdbDD.g:535:2: iv_ruleField= ruleField EOF
      {
        newCompositeNode(grammarAccess.getFieldRule());
        pushFollow(FOLLOW_1);
        iv_ruleField = ruleField();

        state._fsp--;

        current = iv_ruleField;
        match(input, EOF, FOLLOW_2);

      }

    } catch (RecognitionException re) {
      recover(input, re);
      appendSkippedTokens();
    } finally {
    }
    return current;
  }

  // $ANTLR start "ruleField"
  // InternalHdbDD.g:541:1: ruleField returns [EObject current=null] : (this_FieldPrimitive_0= ruleFieldPrimitive | this_FieldReference_1= ruleFieldReference | this_FieldType_2= ruleFieldType ) ;
  public final EObject ruleField() throws RecognitionException {
    EObject current = null;

    EObject this_FieldPrimitive_0 = null;

    EObject this_FieldReference_1 = null;

    EObject this_FieldType_2 = null;

    enterRule();

    try {
      // InternalHdbDD.g:547:2: ( (this_FieldPrimitive_0= ruleFieldPrimitive | this_FieldReference_1= ruleFieldReference | this_FieldType_2= ruleFieldType ) )
      // InternalHdbDD.g:548:2: (this_FieldPrimitive_0= ruleFieldPrimitive | this_FieldReference_1= ruleFieldReference | this_FieldType_2= ruleFieldType )
      {
        // InternalHdbDD.g:548:2: (this_FieldPrimitive_0= ruleFieldPrimitive | this_FieldReference_1= ruleFieldReference | this_FieldType_2= ruleFieldType )
        int alt8 = 3;
        alt8 = dfa8.predict(input);
        switch (alt8) {
          case 1:
            // InternalHdbDD.g:549:3: this_FieldPrimitive_0= ruleFieldPrimitive
          {

            newCompositeNode(grammarAccess.getFieldAccess().getFieldPrimitiveParserRuleCall_0());

            pushFollow(FOLLOW_2);
            this_FieldPrimitive_0 = ruleFieldPrimitive();

            state._fsp--;

            current = this_FieldPrimitive_0;
            afterParserOrEnumRuleCall();


          }
          break;
          case 2:
            // InternalHdbDD.g:558:3: this_FieldReference_1= ruleFieldReference
          {

            newCompositeNode(grammarAccess.getFieldAccess().getFieldReferenceParserRuleCall_1());

            pushFollow(FOLLOW_2);
            this_FieldReference_1 = ruleFieldReference();

            state._fsp--;

            current = this_FieldReference_1;
            afterParserOrEnumRuleCall();


          }
          break;
          case 3:
            // InternalHdbDD.g:567:3: this_FieldType_2= ruleFieldType
          {

            newCompositeNode(grammarAccess.getFieldAccess().getFieldTypeParserRuleCall_2());

            pushFollow(FOLLOW_2);
            this_FieldType_2 = ruleFieldType();

            state._fsp--;

            current = this_FieldType_2;
            afterParserOrEnumRuleCall();


          }
          break;

        }


      }

      leaveRule();

    } catch (RecognitionException re) {
      recover(input, re);
      appendSkippedTokens();
    } finally {
    }
    return current;
  }

  // $ANTLR start "entryRuleFieldPrimitive"
  // InternalHdbDD.g:579:1: entryRuleFieldPrimitive returns [EObject current=null] : iv_ruleFieldPrimitive= ruleFieldPrimitive EOF ;
  public final EObject entryRuleFieldPrimitive() throws RecognitionException {
    EObject current = null;

    EObject iv_ruleFieldPrimitive = null;

    try {
      // InternalHdbDD.g:579:55: (iv_ruleFieldPrimitive= ruleFieldPrimitive EOF )
      // InternalHdbDD.g:580:2: iv_ruleFieldPrimitive= ruleFieldPrimitive EOF
      {
        newCompositeNode(grammarAccess.getFieldPrimitiveRule());
        pushFollow(FOLLOW_1);
        iv_ruleFieldPrimitive = ruleFieldPrimitive();

        state._fsp--;

        current = iv_ruleFieldPrimitive;
        match(input, EOF, FOLLOW_2);

      }

    } catch (RecognitionException re) {
      recover(input, re);
      appendSkippedTokens();
    } finally {
    }
    return current;
  }

  // $ANTLR start "ruleFieldPrimitive"
  // InternalHdbDD.g:586:1: ruleFieldPrimitive returns [EObject current=null] : ( ( (lv_key_0_0= 'key' ) )? ( (lv_name_1_0= RULE_ID ) ) otherlv_2= ':' ( (lv_fieldType_3_0= RULE_ID ) ) (otherlv_4= '(' ( (lv_fieldLength_5_0= RULE_INT ) ) otherlv_6= ')' )? ( (lv_fieldPredefinedType_7_0= RULE_STRING ) )? otherlv_8= ';' ) ;
  public final EObject ruleFieldPrimitive() throws RecognitionException {
    EObject current = null;

    Token lv_key_0_0 = null;
    Token lv_name_1_0 = null;
    Token otherlv_2 = null;
    Token lv_fieldType_3_0 = null;
    Token otherlv_4 = null;
    Token lv_fieldLength_5_0 = null;
    Token otherlv_6 = null;
    Token lv_fieldPredefinedType_7_0 = null;
    Token otherlv_8 = null;

    enterRule();

    try {
      // InternalHdbDD.g:592:2: ( ( ( (lv_key_0_0= 'key' ) )? ( (lv_name_1_0= RULE_ID ) ) otherlv_2= ':' ( (lv_fieldType_3_0= RULE_ID ) ) (otherlv_4= '(' ( (lv_fieldLength_5_0= RULE_INT ) ) otherlv_6= ')' )? ( (lv_fieldPredefinedType_7_0= RULE_STRING ) )? otherlv_8= ';' ) )
      // InternalHdbDD.g:593:2: ( ( (lv_key_0_0= 'key' ) )? ( (lv_name_1_0= RULE_ID ) ) otherlv_2= ':' ( (lv_fieldType_3_0= RULE_ID ) ) (otherlv_4= '(' ( (lv_fieldLength_5_0= RULE_INT ) ) otherlv_6= ')' )? ( (lv_fieldPredefinedType_7_0= RULE_STRING ) )? otherlv_8= ';' )
      {
        // InternalHdbDD.g:593:2: ( ( (lv_key_0_0= 'key' ) )? ( (lv_name_1_0= RULE_ID ) ) otherlv_2= ':' ( (lv_fieldType_3_0= RULE_ID ) ) (otherlv_4= '(' ( (lv_fieldLength_5_0= RULE_INT ) ) otherlv_6= ')' )? ( (lv_fieldPredefinedType_7_0= RULE_STRING ) )? otherlv_8= ';' )
        // InternalHdbDD.g:594:3: ( (lv_key_0_0= 'key' ) )? ( (lv_name_1_0= RULE_ID ) ) otherlv_2= ':' ( (lv_fieldType_3_0= RULE_ID ) ) (otherlv_4= '(' ( (lv_fieldLength_5_0= RULE_INT ) ) otherlv_6= ')' )? ( (lv_fieldPredefinedType_7_0= RULE_STRING ) )? otherlv_8= ';'
        {
          // InternalHdbDD.g:594:3: ( (lv_key_0_0= 'key' ) )?
          int alt9 = 2;
          int LA9_0 = input.LA(1);

          if ((LA9_0 == 23)) {
            alt9 = 1;
          }
          switch (alt9) {
            case 1:
              // InternalHdbDD.g:595:4: (lv_key_0_0= 'key' )
            {
              // InternalHdbDD.g:595:4: (lv_key_0_0= 'key' )
              // InternalHdbDD.g:596:5: lv_key_0_0= 'key'
              {
                lv_key_0_0 = (Token) match(input, 23, FOLLOW_4);

                newLeafNode(lv_key_0_0, grammarAccess.getFieldPrimitiveAccess().getKeyKeyKeyword_0_0());

                if (current == null) {
                  current = createModelElement(grammarAccess.getFieldPrimitiveRule());
                }
                setWithLastConsumed(current, "key", true, "key");


              }


            }
            break;

          }

          // InternalHdbDD.g:608:3: ( (lv_name_1_0= RULE_ID ) )
          // InternalHdbDD.g:609:4: (lv_name_1_0= RULE_ID )
          {
            // InternalHdbDD.g:609:4: (lv_name_1_0= RULE_ID )
            // InternalHdbDD.g:610:5: lv_name_1_0= RULE_ID
            {
              lv_name_1_0 = (Token) match(input, RULE_ID, FOLLOW_7);

              newLeafNode(lv_name_1_0, grammarAccess.getFieldPrimitiveAccess().getNameIDTerminalRuleCall_1_0());

              if (current == null) {
                current = createModelElement(grammarAccess.getFieldPrimitiveRule());
              }
              setWithLastConsumed(
                  current,
                  "name",
                  lv_name_1_0,
                  "org.eclipse.xtext.common.Terminals.ID");


            }


          }

          otherlv_2 = (Token) match(input, 15, FOLLOW_4);

          newLeafNode(otherlv_2, grammarAccess.getFieldPrimitiveAccess().getColonKeyword_2());

          // InternalHdbDD.g:630:3: ( (lv_fieldType_3_0= RULE_ID ) )
          // InternalHdbDD.g:631:4: (lv_fieldType_3_0= RULE_ID )
          {
            // InternalHdbDD.g:631:4: (lv_fieldType_3_0= RULE_ID )
            // InternalHdbDD.g:632:5: lv_fieldType_3_0= RULE_ID
            {
              lv_fieldType_3_0 = (Token) match(input, RULE_ID, FOLLOW_15);

              newLeafNode(lv_fieldType_3_0, grammarAccess.getFieldPrimitiveAccess().getFieldTypeIDTerminalRuleCall_3_0());

              if (current == null) {
                current = createModelElement(grammarAccess.getFieldPrimitiveRule());
              }
              setWithLastConsumed(
                  current,
                  "fieldType",
                  lv_fieldType_3_0,
                  "org.eclipse.xtext.common.Terminals.ID");


            }


          }

          // InternalHdbDD.g:648:3: (otherlv_4= '(' ( (lv_fieldLength_5_0= RULE_INT ) ) otherlv_6= ')' )?
          int alt10 = 2;
          int LA10_0 = input.LA(1);

          if ((LA10_0 == 20)) {
            alt10 = 1;
          }
          switch (alt10) {
            case 1:
              // InternalHdbDD.g:649:4: otherlv_4= '(' ( (lv_fieldLength_5_0= RULE_INT ) ) otherlv_6= ')'
            {
              otherlv_4 = (Token) match(input, 20, FOLLOW_12);

              newLeafNode(otherlv_4, grammarAccess.getFieldPrimitiveAccess().getLeftParenthesisKeyword_4_0());

              // InternalHdbDD.g:653:4: ( (lv_fieldLength_5_0= RULE_INT ) )
              // InternalHdbDD.g:654:5: (lv_fieldLength_5_0= RULE_INT )
              {
                // InternalHdbDD.g:654:5: (lv_fieldLength_5_0= RULE_INT )
                // InternalHdbDD.g:655:6: lv_fieldLength_5_0= RULE_INT
                {
                  lv_fieldLength_5_0 = (Token) match(input, RULE_INT, FOLLOW_13);

                  newLeafNode(lv_fieldLength_5_0, grammarAccess.getFieldPrimitiveAccess().getFieldLengthINTTerminalRuleCall_4_1_0());

                  if (current == null) {
                    current = createModelElement(grammarAccess.getFieldPrimitiveRule());
                  }
                  setWithLastConsumed(
                      current,
                      "fieldLength",
                      lv_fieldLength_5_0,
                      "org.eclipse.xtext.common.Terminals.INT");


                }


              }

              otherlv_6 = (Token) match(input, 21, FOLLOW_16);

              newLeafNode(otherlv_6, grammarAccess.getFieldPrimitiveAccess().getRightParenthesisKeyword_4_2());


            }
            break;

          }

          // InternalHdbDD.g:676:3: ( (lv_fieldPredefinedType_7_0= RULE_STRING ) )?
          int alt11 = 2;
          int LA11_0 = input.LA(1);

          if ((LA11_0 == RULE_STRING)) {
            alt11 = 1;
          }
          switch (alt11) {
            case 1:
              // InternalHdbDD.g:677:4: (lv_fieldPredefinedType_7_0= RULE_STRING )
            {
              // InternalHdbDD.g:677:4: (lv_fieldPredefinedType_7_0= RULE_STRING )
              // InternalHdbDD.g:678:5: lv_fieldPredefinedType_7_0= RULE_STRING
              {
                lv_fieldPredefinedType_7_0 = (Token) match(input, RULE_STRING, FOLLOW_5);

                newLeafNode(lv_fieldPredefinedType_7_0,
                    grammarAccess.getFieldPrimitiveAccess().getFieldPredefinedTypeSTRINGTerminalRuleCall_5_0());

                if (current == null) {
                  current = createModelElement(grammarAccess.getFieldPrimitiveRule());
                }
                setWithLastConsumed(
                    current,
                    "fieldPredefinedType",
                    lv_fieldPredefinedType_7_0,
                    "org.eclipse.xtext.common.Terminals.STRING");


              }


            }
            break;

          }

          otherlv_8 = (Token) match(input, 12, FOLLOW_2);

          newLeafNode(otherlv_8, grammarAccess.getFieldPrimitiveAccess().getSemicolonKeyword_6());


        }


      }

      leaveRule();

    } catch (RecognitionException re) {
      recover(input, re);
      appendSkippedTokens();
    } finally {
    }
    return current;
  }

  // $ANTLR start "entryRuleFieldType"
  // InternalHdbDD.g:702:1: entryRuleFieldType returns [EObject current=null] : iv_ruleFieldType= ruleFieldType EOF ;
  public final EObject entryRuleFieldType() throws RecognitionException {
    EObject current = null;

    EObject iv_ruleFieldType = null;

    try {
      // InternalHdbDD.g:702:50: (iv_ruleFieldType= ruleFieldType EOF )
      // InternalHdbDD.g:703:2: iv_ruleFieldType= ruleFieldType EOF
      {
        newCompositeNode(grammarAccess.getFieldTypeRule());
        pushFollow(FOLLOW_1);
        iv_ruleFieldType = ruleFieldType();

        state._fsp--;

        current = iv_ruleFieldType;
        match(input, EOF, FOLLOW_2);

      }

    } catch (RecognitionException re) {
      recover(input, re);
      appendSkippedTokens();
    } finally {
    }
    return current;
  }

  // $ANTLR start "ruleFieldType"
  // InternalHdbDD.g:709:1: ruleFieldType returns [EObject current=null] : ( ( (lv_key_0_0= 'key' ) )? ( (lv_name_1_0= RULE_ID ) ) otherlv_2= ':' ( (lv_fieldPredefinedType_3_0= RULE_STRING ) )? otherlv_4= ';' ) ;
  public final EObject ruleFieldType() throws RecognitionException {
    EObject current = null;

    Token lv_key_0_0 = null;
    Token lv_name_1_0 = null;
    Token otherlv_2 = null;
    Token lv_fieldPredefinedType_3_0 = null;
    Token otherlv_4 = null;

    enterRule();

    try {
      // InternalHdbDD.g:715:2: ( ( ( (lv_key_0_0= 'key' ) )? ( (lv_name_1_0= RULE_ID ) ) otherlv_2= ':' ( (lv_fieldPredefinedType_3_0= RULE_STRING ) )? otherlv_4= ';' ) )
      // InternalHdbDD.g:716:2: ( ( (lv_key_0_0= 'key' ) )? ( (lv_name_1_0= RULE_ID ) ) otherlv_2= ':' ( (lv_fieldPredefinedType_3_0= RULE_STRING ) )? otherlv_4= ';' )
      {
        // InternalHdbDD.g:716:2: ( ( (lv_key_0_0= 'key' ) )? ( (lv_name_1_0= RULE_ID ) ) otherlv_2= ':' ( (lv_fieldPredefinedType_3_0= RULE_STRING ) )? otherlv_4= ';' )
        // InternalHdbDD.g:717:3: ( (lv_key_0_0= 'key' ) )? ( (lv_name_1_0= RULE_ID ) ) otherlv_2= ':' ( (lv_fieldPredefinedType_3_0= RULE_STRING ) )? otherlv_4= ';'
        {
          // InternalHdbDD.g:717:3: ( (lv_key_0_0= 'key' ) )?
          int alt12 = 2;
          int LA12_0 = input.LA(1);

          if ((LA12_0 == 23)) {
            alt12 = 1;
          }
          switch (alt12) {
            case 1:
              // InternalHdbDD.g:718:4: (lv_key_0_0= 'key' )
            {
              // InternalHdbDD.g:718:4: (lv_key_0_0= 'key' )
              // InternalHdbDD.g:719:5: lv_key_0_0= 'key'
              {
                lv_key_0_0 = (Token) match(input, 23, FOLLOW_4);

                newLeafNode(lv_key_0_0, grammarAccess.getFieldTypeAccess().getKeyKeyKeyword_0_0());

                if (current == null) {
                  current = createModelElement(grammarAccess.getFieldTypeRule());
                }
                setWithLastConsumed(current, "key", true, "key");


              }


            }
            break;

          }

          // InternalHdbDD.g:731:3: ( (lv_name_1_0= RULE_ID ) )
          // InternalHdbDD.g:732:4: (lv_name_1_0= RULE_ID )
          {
            // InternalHdbDD.g:732:4: (lv_name_1_0= RULE_ID )
            // InternalHdbDD.g:733:5: lv_name_1_0= RULE_ID
            {
              lv_name_1_0 = (Token) match(input, RULE_ID, FOLLOW_7);

              newLeafNode(lv_name_1_0, grammarAccess.getFieldTypeAccess().getNameIDTerminalRuleCall_1_0());

              if (current == null) {
                current = createModelElement(grammarAccess.getFieldTypeRule());
              }
              setWithLastConsumed(
                  current,
                  "name",
                  lv_name_1_0,
                  "org.eclipse.xtext.common.Terminals.ID");


            }


          }

          otherlv_2 = (Token) match(input, 15, FOLLOW_16);

          newLeafNode(otherlv_2, grammarAccess.getFieldTypeAccess().getColonKeyword_2());

          // InternalHdbDD.g:753:3: ( (lv_fieldPredefinedType_3_0= RULE_STRING ) )?
          int alt13 = 2;
          int LA13_0 = input.LA(1);

          if ((LA13_0 == RULE_STRING)) {
            alt13 = 1;
          }
          switch (alt13) {
            case 1:
              // InternalHdbDD.g:754:4: (lv_fieldPredefinedType_3_0= RULE_STRING )
            {
              // InternalHdbDD.g:754:4: (lv_fieldPredefinedType_3_0= RULE_STRING )
              // InternalHdbDD.g:755:5: lv_fieldPredefinedType_3_0= RULE_STRING
              {
                lv_fieldPredefinedType_3_0 = (Token) match(input, RULE_STRING, FOLLOW_5);

                newLeafNode(lv_fieldPredefinedType_3_0,
                    grammarAccess.getFieldTypeAccess().getFieldPredefinedTypeSTRINGTerminalRuleCall_3_0());

                if (current == null) {
                  current = createModelElement(grammarAccess.getFieldTypeRule());
                }
                setWithLastConsumed(
                    current,
                    "fieldPredefinedType",
                    lv_fieldPredefinedType_3_0,
                    "org.eclipse.xtext.common.Terminals.STRING");


              }


            }
            break;

          }

          otherlv_4 = (Token) match(input, 12, FOLLOW_2);

          newLeafNode(otherlv_4, grammarAccess.getFieldTypeAccess().getSemicolonKeyword_4());


        }


      }

      leaveRule();

    } catch (RecognitionException re) {
      recover(input, re);
      appendSkippedTokens();
    } finally {
    }
    return current;
  }

  // $ANTLR start "entryRuleFieldReference"
  // InternalHdbDD.g:779:1: entryRuleFieldReference returns [EObject current=null] : iv_ruleFieldReference= ruleFieldReference EOF ;
  public final EObject entryRuleFieldReference() throws RecognitionException {
    EObject current = null;

    EObject iv_ruleFieldReference = null;

    try {
      // InternalHdbDD.g:779:55: (iv_ruleFieldReference= ruleFieldReference EOF )
      // InternalHdbDD.g:780:2: iv_ruleFieldReference= ruleFieldReference EOF
      {
        newCompositeNode(grammarAccess.getFieldReferenceRule());
        pushFollow(FOLLOW_1);
        iv_ruleFieldReference = ruleFieldReference();

        state._fsp--;

        current = iv_ruleFieldReference;
        match(input, EOF, FOLLOW_2);

      }

    } catch (RecognitionException re) {
      recover(input, re);
      appendSkippedTokens();
    } finally {
    }
    return current;
  }

  // $ANTLR start "ruleFieldReference"
  // InternalHdbDD.g:786:1: ruleFieldReference returns [EObject current=null] : ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_referenceType_2_0= RULE_ID ) ) otherlv_3= '[*]' otherlv_4= 'to' ( (lv_fieldReferenceEntity_5_0= RULE_ID ) ) otherlv_6= 'on' ( (lv_fieldReferenceEntityLocalId_7_0= ruleQualifiedName ) ) otherlv_8= '=' ( (lv_fieldReferenceEntityForeignId_9_0= RULE_ID ) ) otherlv_10= ';' ) ;
  public final EObject ruleFieldReference() throws RecognitionException {
    EObject current = null;

    Token lv_name_0_0 = null;
    Token otherlv_1 = null;
    Token lv_referenceType_2_0 = null;
    Token otherlv_3 = null;
    Token otherlv_4 = null;
    Token lv_fieldReferenceEntity_5_0 = null;
    Token otherlv_6 = null;
    Token otherlv_8 = null;
    Token lv_fieldReferenceEntityForeignId_9_0 = null;
    Token otherlv_10 = null;
    AntlrDatatypeRuleToken lv_fieldReferenceEntityLocalId_7_0 = null;

    enterRule();

    try {
      // InternalHdbDD.g:792:2: ( ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_referenceType_2_0= RULE_ID ) ) otherlv_3= '[*]' otherlv_4= 'to' ( (lv_fieldReferenceEntity_5_0= RULE_ID ) ) otherlv_6= 'on' ( (lv_fieldReferenceEntityLocalId_7_0= ruleQualifiedName ) ) otherlv_8= '=' ( (lv_fieldReferenceEntityForeignId_9_0= RULE_ID ) ) otherlv_10= ';' ) )
      // InternalHdbDD.g:793:2: ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_referenceType_2_0= RULE_ID ) ) otherlv_3= '[*]' otherlv_4= 'to' ( (lv_fieldReferenceEntity_5_0= RULE_ID ) ) otherlv_6= 'on' ( (lv_fieldReferenceEntityLocalId_7_0= ruleQualifiedName ) ) otherlv_8= '=' ( (lv_fieldReferenceEntityForeignId_9_0= RULE_ID ) ) otherlv_10= ';' )
      {
        // InternalHdbDD.g:793:2: ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_referenceType_2_0= RULE_ID ) ) otherlv_3= '[*]' otherlv_4= 'to' ( (lv_fieldReferenceEntity_5_0= RULE_ID ) ) otherlv_6= 'on' ( (lv_fieldReferenceEntityLocalId_7_0= ruleQualifiedName ) ) otherlv_8= '=' ( (lv_fieldReferenceEntityForeignId_9_0= RULE_ID ) ) otherlv_10= ';' )
        // InternalHdbDD.g:794:3: ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_referenceType_2_0= RULE_ID ) ) otherlv_3= '[*]' otherlv_4= 'to' ( (lv_fieldReferenceEntity_5_0= RULE_ID ) ) otherlv_6= 'on' ( (lv_fieldReferenceEntityLocalId_7_0= ruleQualifiedName ) ) otherlv_8= '=' ( (lv_fieldReferenceEntityForeignId_9_0= RULE_ID ) ) otherlv_10= ';'
        {
          // InternalHdbDD.g:794:3: ( (lv_name_0_0= RULE_ID ) )
          // InternalHdbDD.g:795:4: (lv_name_0_0= RULE_ID )
          {
            // InternalHdbDD.g:795:4: (lv_name_0_0= RULE_ID )
            // InternalHdbDD.g:796:5: lv_name_0_0= RULE_ID
            {
              lv_name_0_0 = (Token) match(input, RULE_ID, FOLLOW_7);

              newLeafNode(lv_name_0_0, grammarAccess.getFieldReferenceAccess().getNameIDTerminalRuleCall_0_0());

              if (current == null) {
                current = createModelElement(grammarAccess.getFieldReferenceRule());
              }
              setWithLastConsumed(
                  current,
                  "name",
                  lv_name_0_0,
                  "org.eclipse.xtext.common.Terminals.ID");


            }


          }

          otherlv_1 = (Token) match(input, 15, FOLLOW_4);

          newLeafNode(otherlv_1, grammarAccess.getFieldReferenceAccess().getColonKeyword_1());

          // InternalHdbDD.g:816:3: ( (lv_referenceType_2_0= RULE_ID ) )
          // InternalHdbDD.g:817:4: (lv_referenceType_2_0= RULE_ID )
          {
            // InternalHdbDD.g:817:4: (lv_referenceType_2_0= RULE_ID )
            // InternalHdbDD.g:818:5: lv_referenceType_2_0= RULE_ID
            {
              lv_referenceType_2_0 = (Token) match(input, RULE_ID, FOLLOW_17);

              newLeafNode(lv_referenceType_2_0, grammarAccess.getFieldReferenceAccess().getReferenceTypeIDTerminalRuleCall_2_0());

              if (current == null) {
                current = createModelElement(grammarAccess.getFieldReferenceRule());
              }
              setWithLastConsumed(
                  current,
                  "referenceType",
                  lv_referenceType_2_0,
                  "org.eclipse.xtext.common.Terminals.ID");


            }


          }

          otherlv_3 = (Token) match(input, 24, FOLLOW_18);

          newLeafNode(otherlv_3, grammarAccess.getFieldReferenceAccess().getLeftSquareBracketAsteriskRightSquareBracketKeyword_3());

          otherlv_4 = (Token) match(input, 25, FOLLOW_4);

          newLeafNode(otherlv_4, grammarAccess.getFieldReferenceAccess().getToKeyword_4());

          // InternalHdbDD.g:842:3: ( (lv_fieldReferenceEntity_5_0= RULE_ID ) )
          // InternalHdbDD.g:843:4: (lv_fieldReferenceEntity_5_0= RULE_ID )
          {
            // InternalHdbDD.g:843:4: (lv_fieldReferenceEntity_5_0= RULE_ID )
            // InternalHdbDD.g:844:5: lv_fieldReferenceEntity_5_0= RULE_ID
            {
              lv_fieldReferenceEntity_5_0 = (Token) match(input, RULE_ID, FOLLOW_19);

              newLeafNode(lv_fieldReferenceEntity_5_0,
                  grammarAccess.getFieldReferenceAccess().getFieldReferenceEntityIDTerminalRuleCall_5_0());

              if (current == null) {
                current = createModelElement(grammarAccess.getFieldReferenceRule());
              }
              setWithLastConsumed(
                  current,
                  "fieldReferenceEntity",
                  lv_fieldReferenceEntity_5_0,
                  "org.eclipse.xtext.common.Terminals.ID");


            }


          }

          otherlv_6 = (Token) match(input, 26, FOLLOW_4);

          newLeafNode(otherlv_6, grammarAccess.getFieldReferenceAccess().getOnKeyword_6());

          // InternalHdbDD.g:864:3: ( (lv_fieldReferenceEntityLocalId_7_0= ruleQualifiedName ) )
          // InternalHdbDD.g:865:4: (lv_fieldReferenceEntityLocalId_7_0= ruleQualifiedName )
          {
            // InternalHdbDD.g:865:4: (lv_fieldReferenceEntityLocalId_7_0= ruleQualifiedName )
            // InternalHdbDD.g:866:5: lv_fieldReferenceEntityLocalId_7_0= ruleQualifiedName
            {

              newCompositeNode(grammarAccess.getFieldReferenceAccess().getFieldReferenceEntityLocalIdQualifiedNameParserRuleCall_7_0());

              pushFollow(FOLLOW_20);
              lv_fieldReferenceEntityLocalId_7_0 = ruleQualifiedName();

              state._fsp--;

              if (current == null) {
                current = createModelElementForParent(grammarAccess.getFieldReferenceRule());
              }
              set(
                  current,
                  "fieldReferenceEntityLocalId",
                  lv_fieldReferenceEntityLocalId_7_0,
                  "com.sap.xsk.models.hdbdd.HdbDD.QualifiedName");
              afterParserOrEnumRuleCall();


            }


          }

          otherlv_8 = (Token) match(input, 27, FOLLOW_4);

          newLeafNode(otherlv_8, grammarAccess.getFieldReferenceAccess().getEqualsSignKeyword_8());

          // InternalHdbDD.g:887:3: ( (lv_fieldReferenceEntityForeignId_9_0= RULE_ID ) )
          // InternalHdbDD.g:888:4: (lv_fieldReferenceEntityForeignId_9_0= RULE_ID )
          {
            // InternalHdbDD.g:888:4: (lv_fieldReferenceEntityForeignId_9_0= RULE_ID )
            // InternalHdbDD.g:889:5: lv_fieldReferenceEntityForeignId_9_0= RULE_ID
            {
              lv_fieldReferenceEntityForeignId_9_0 = (Token) match(input, RULE_ID, FOLLOW_5);

              newLeafNode(lv_fieldReferenceEntityForeignId_9_0,
                  grammarAccess.getFieldReferenceAccess().getFieldReferenceEntityForeignIdIDTerminalRuleCall_9_0());

              if (current == null) {
                current = createModelElement(grammarAccess.getFieldReferenceRule());
              }
              setWithLastConsumed(
                  current,
                  "fieldReferenceEntityForeignId",
                  lv_fieldReferenceEntityForeignId_9_0,
                  "org.eclipse.xtext.common.Terminals.ID");


            }


          }

          otherlv_10 = (Token) match(input, 12, FOLLOW_2);

          newLeafNode(otherlv_10, grammarAccess.getFieldReferenceAccess().getSemicolonKeyword_10());


        }


      }

      leaveRule();

    } catch (RecognitionException re) {
      recover(input, re);
      appendSkippedTokens();
    } finally {
    }
    return current;
  }

  class DFA8 extends DFA {

    public DFA8(BaseRecognizer recognizer) {
      this.recognizer = recognizer;
      this.decisionNumber = 8;
      this.eot = dfa_1;
      this.eof = dfa_1;
      this.min = dfa_2;
      this.max = dfa_3;
      this.accept = dfa_4;
      this.special = dfa_5;
      this.transition = dfa_6;
    }

    public String getDescription() {
      return "548:2: (this_FieldPrimitive_0= ruleFieldPrimitive | this_FieldReference_1= ruleFieldReference | this_FieldType_2= ruleFieldType )";
    }
  }

}