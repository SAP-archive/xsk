package com.sap.xsk.models.hdbti.parser.antlr.internal;

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import com.sap.xsk.models.hdbti.services.HdbtiGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalHdbtiParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_STRING", "RULE_BOOL", "RULE_ID", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'\"GROUP_TYPE\"'", "':'", "'{'", "'table'", "'='", "';'", "'schema'", "'file'", "'delimField'", "'header'", "'keys'", "'['", "','", "']'", "'delimEnclosing'", "'}'", "'import'"
    };
    public static final int RULE_STRING=4;
    public static final int RULE_SL_COMMENT=9;
    public static final int T__19=19;
    public static final int T__15=15;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__12=12;
    public static final int T__13=13;
    public static final int T__14=14;
    public static final int EOF=-1;
    public static final int RULE_ID=6;
    public static final int RULE_WS=10;
    public static final int RULE_ANY_OTHER=11;
    public static final int RULE_BOOL=5;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int RULE_INT=7;
    public static final int T__22=22;
    public static final int RULE_ML_COMMENT=8;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__20=20;
    public static final int T__21=21;

    // delegates
    // delegators


        public InternalHdbtiParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalHdbtiParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalHdbtiParser.tokenNames; }
    public String getGrammarFileName() { return "InternalHdbti.g"; }



     	private HdbtiGrammarAccess grammarAccess;

        public InternalHdbtiParser(TokenStream input, HdbtiGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }

        @Override
        protected String getFirstRuleName() {
        	return "HdbdtiModel";
       	}

       	@Override
       	protected HdbtiGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}




    // $ANTLR start "entryRuleHdbdtiModel"
    // InternalHdbti.g:64:1: entryRuleHdbdtiModel returns [EObject current=null] : iv_ruleHdbdtiModel= ruleHdbdtiModel EOF ;
    public final EObject entryRuleHdbdtiModel() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleHdbdtiModel = null;


        try {
            // InternalHdbti.g:64:52: (iv_ruleHdbdtiModel= ruleHdbdtiModel EOF )
            // InternalHdbti.g:65:2: iv_ruleHdbdtiModel= ruleHdbdtiModel EOF
            {
             newCompositeNode(grammarAccess.getHdbdtiModelRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleHdbdtiModel=ruleHdbdtiModel();

            state._fsp--;

             current =iv_ruleHdbdtiModel; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleHdbdtiModel"


    // $ANTLR start "ruleHdbdtiModel"
    // InternalHdbti.g:71:1: ruleHdbdtiModel returns [EObject current=null] : ( (lv_importElement_0_0= ruleImport ) ) ;
    public final EObject ruleHdbdtiModel() throws RecognitionException {
        EObject current = null;

        EObject lv_importElement_0_0 = null;



        	enterRule();

        try {
            // InternalHdbti.g:77:2: ( ( (lv_importElement_0_0= ruleImport ) ) )
            // InternalHdbti.g:78:2: ( (lv_importElement_0_0= ruleImport ) )
            {
            // InternalHdbti.g:78:2: ( (lv_importElement_0_0= ruleImport ) )
            // InternalHdbti.g:79:3: (lv_importElement_0_0= ruleImport )
            {
            // InternalHdbti.g:79:3: (lv_importElement_0_0= ruleImport )
            // InternalHdbti.g:80:4: lv_importElement_0_0= ruleImport
            {

            				newCompositeNode(grammarAccess.getHdbdtiModelAccess().getImportElementImportParserRuleCall_0());
            			
            pushFollow(FOLLOW_2);
            lv_importElement_0_0=ruleImport();

            state._fsp--;


            				if (current==null) {
            					current = createModelElementForParent(grammarAccess.getHdbdtiModelRule());
            				}
            				set(
            					current,
            					"importElement",
            					lv_importElement_0_0,
            					"com.sap.xsk.models.hdbti.Hdbti.Import");
            				afterParserOrEnumRuleCall();
            			

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleHdbdtiModel"


    // $ANTLR start "entryRuleGroupType"
    // InternalHdbti.g:100:1: entryRuleGroupType returns [EObject current=null] : iv_ruleGroupType= ruleGroupType EOF ;
    public final EObject entryRuleGroupType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGroupType = null;


        try {
            // InternalHdbti.g:100:50: (iv_ruleGroupType= ruleGroupType EOF )
            // InternalHdbti.g:101:2: iv_ruleGroupType= ruleGroupType EOF
            {
             newCompositeNode(grammarAccess.getGroupTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleGroupType=ruleGroupType();

            state._fsp--;

             current =iv_ruleGroupType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleGroupType"


    // $ANTLR start "ruleGroupType"
    // InternalHdbti.g:107:1: ruleGroupType returns [EObject current=null] : (otherlv_0= '\"GROUP_TYPE\"' otherlv_1= ':' ( (lv_value_2_0= RULE_STRING ) ) ) ;
    public final EObject ruleGroupType() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token lv_value_2_0=null;


        	enterRule();

        try {
            // InternalHdbti.g:113:2: ( (otherlv_0= '\"GROUP_TYPE\"' otherlv_1= ':' ( (lv_value_2_0= RULE_STRING ) ) ) )
            // InternalHdbti.g:114:2: (otherlv_0= '\"GROUP_TYPE\"' otherlv_1= ':' ( (lv_value_2_0= RULE_STRING ) ) )
            {
            // InternalHdbti.g:114:2: (otherlv_0= '\"GROUP_TYPE\"' otherlv_1= ':' ( (lv_value_2_0= RULE_STRING ) ) )
            // InternalHdbti.g:115:3: otherlv_0= '\"GROUP_TYPE\"' otherlv_1= ':' ( (lv_value_2_0= RULE_STRING ) )
            {
            otherlv_0=(Token)match(input,12,FOLLOW_3); 

            			newLeafNode(otherlv_0, grammarAccess.getGroupTypeAccess().getGROUP_TYPEKeyword_0());
            		
            otherlv_1=(Token)match(input,13,FOLLOW_4); 

            			newLeafNode(otherlv_1, grammarAccess.getGroupTypeAccess().getColonKeyword_1());
            		
            // InternalHdbti.g:123:3: ( (lv_value_2_0= RULE_STRING ) )
            // InternalHdbti.g:124:4: (lv_value_2_0= RULE_STRING )
            {
            // InternalHdbti.g:124:4: (lv_value_2_0= RULE_STRING )
            // InternalHdbti.g:125:5: lv_value_2_0= RULE_STRING
            {
            lv_value_2_0=(Token)match(input,RULE_STRING,FOLLOW_2); 

            					newLeafNode(lv_value_2_0, grammarAccess.getGroupTypeAccess().getValueSTRINGTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getGroupTypeRule());
            					}
            					setWithLastConsumed(
            						current,
            						"value",
            						lv_value_2_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleGroupType"


    // $ANTLR start "entryRuleImportConfig"
    // InternalHdbti.g:145:1: entryRuleImportConfig returns [EObject current=null] : iv_ruleImportConfig= ruleImportConfig EOF ;
    public final EObject entryRuleImportConfig() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleImportConfig = null;


        try {
            // InternalHdbti.g:145:53: (iv_ruleImportConfig= ruleImportConfig EOF )
            // InternalHdbti.g:146:2: iv_ruleImportConfig= ruleImportConfig EOF
            {
             newCompositeNode(grammarAccess.getImportConfigRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleImportConfig=ruleImportConfig();

            state._fsp--;

             current =iv_ruleImportConfig; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleImportConfig"


    // $ANTLR start "ruleImportConfig"
    // InternalHdbti.g:152:1: ruleImportConfig returns [EObject current=null] : ( ( ( ( ({...}? => ( ({...}? => (otherlv_1= '{' ( (lv_table_2_0= 'table' ) ) otherlv_3= '=' ( (lv_tableValue_4_0= RULE_STRING ) ) otherlv_5= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_schema_6_0= 'schema' ) ) otherlv_7= '=' ( (lv_schemaValue_8_0= RULE_STRING ) ) otherlv_9= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_file_10_0= 'file' ) ) otherlv_11= '=' ( (lv_fileValue_12_0= RULE_STRING ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_delimField_14_0= 'delimField' ) ) otherlv_15= '=' ( (lv_delimFieldValue_16_0= RULE_STRING ) ) otherlv_17= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_header_18_0= 'header' ) ) otherlv_19= '=' ( (lv_headerValue_20_0= RULE_BOOL ) ) otherlv_21= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_keys_22_0= 'keys' ) ) otherlv_23= '=' otherlv_24= '[' ( ( (lv_headerValues_25_0= ruleGroupType ) ) (otherlv_26= ',' ( (lv_headerValues_27_0= ruleGroupType ) ) )* )? otherlv_28= ']' otherlv_29= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_delimEnclosing_30_0= 'delimEnclosing' ) ) otherlv_31= '=' ( (lv_delimEnclosingValue_32_0= RULE_STRING ) ) otherlv_33= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_distinguishEmptyFromNull_34_0= 'delimEnclosing' ) ) otherlv_35= '=' ( (lv_distinguishEmptyFromNullValue_36_0= RULE_BOOL ) ) otherlv_37= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( ( (lv_useHeaderNames_38_0= 'delimEnclosing' ) ) otherlv_39= '=' ( (lv_useHeaderNamesValue_40_0= RULE_BOOL ) ) otherlv_41= ';' )? otherlv_42= '}' ) ) ) ) )+ {...}?) ) ) ;
    public final EObject ruleImportConfig() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_table_2_0=null;
        Token otherlv_3=null;
        Token lv_tableValue_4_0=null;
        Token otherlv_5=null;
        Token lv_schema_6_0=null;
        Token otherlv_7=null;
        Token lv_schemaValue_8_0=null;
        Token otherlv_9=null;
        Token lv_file_10_0=null;
        Token otherlv_11=null;
        Token lv_fileValue_12_0=null;
        Token otherlv_13=null;
        Token lv_delimField_14_0=null;
        Token otherlv_15=null;
        Token lv_delimFieldValue_16_0=null;
        Token otherlv_17=null;
        Token lv_header_18_0=null;
        Token otherlv_19=null;
        Token lv_headerValue_20_0=null;
        Token otherlv_21=null;
        Token lv_keys_22_0=null;
        Token otherlv_23=null;
        Token otherlv_24=null;
        Token otherlv_26=null;
        Token otherlv_28=null;
        Token otherlv_29=null;
        Token lv_delimEnclosing_30_0=null;
        Token otherlv_31=null;
        Token lv_delimEnclosingValue_32_0=null;
        Token otherlv_33=null;
        Token lv_distinguishEmptyFromNull_34_0=null;
        Token otherlv_35=null;
        Token lv_distinguishEmptyFromNullValue_36_0=null;
        Token otherlv_37=null;
        Token lv_useHeaderNames_38_0=null;
        Token otherlv_39=null;
        Token lv_useHeaderNamesValue_40_0=null;
        Token otherlv_41=null;
        Token otherlv_42=null;
        EObject lv_headerValues_25_0 = null;

        EObject lv_headerValues_27_0 = null;



        	enterRule();

        try {
            // InternalHdbti.g:158:2: ( ( ( ( ( ({...}? => ( ({...}? => (otherlv_1= '{' ( (lv_table_2_0= 'table' ) ) otherlv_3= '=' ( (lv_tableValue_4_0= RULE_STRING ) ) otherlv_5= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_schema_6_0= 'schema' ) ) otherlv_7= '=' ( (lv_schemaValue_8_0= RULE_STRING ) ) otherlv_9= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_file_10_0= 'file' ) ) otherlv_11= '=' ( (lv_fileValue_12_0= RULE_STRING ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_delimField_14_0= 'delimField' ) ) otherlv_15= '=' ( (lv_delimFieldValue_16_0= RULE_STRING ) ) otherlv_17= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_header_18_0= 'header' ) ) otherlv_19= '=' ( (lv_headerValue_20_0= RULE_BOOL ) ) otherlv_21= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_keys_22_0= 'keys' ) ) otherlv_23= '=' otherlv_24= '[' ( ( (lv_headerValues_25_0= ruleGroupType ) ) (otherlv_26= ',' ( (lv_headerValues_27_0= ruleGroupType ) ) )* )? otherlv_28= ']' otherlv_29= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_delimEnclosing_30_0= 'delimEnclosing' ) ) otherlv_31= '=' ( (lv_delimEnclosingValue_32_0= RULE_STRING ) ) otherlv_33= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_distinguishEmptyFromNull_34_0= 'delimEnclosing' ) ) otherlv_35= '=' ( (lv_distinguishEmptyFromNullValue_36_0= RULE_BOOL ) ) otherlv_37= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( ( (lv_useHeaderNames_38_0= 'delimEnclosing' ) ) otherlv_39= '=' ( (lv_useHeaderNamesValue_40_0= RULE_BOOL ) ) otherlv_41= ';' )? otherlv_42= '}' ) ) ) ) )+ {...}?) ) ) )
            // InternalHdbti.g:159:2: ( ( ( ( ({...}? => ( ({...}? => (otherlv_1= '{' ( (lv_table_2_0= 'table' ) ) otherlv_3= '=' ( (lv_tableValue_4_0= RULE_STRING ) ) otherlv_5= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_schema_6_0= 'schema' ) ) otherlv_7= '=' ( (lv_schemaValue_8_0= RULE_STRING ) ) otherlv_9= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_file_10_0= 'file' ) ) otherlv_11= '=' ( (lv_fileValue_12_0= RULE_STRING ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_delimField_14_0= 'delimField' ) ) otherlv_15= '=' ( (lv_delimFieldValue_16_0= RULE_STRING ) ) otherlv_17= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_header_18_0= 'header' ) ) otherlv_19= '=' ( (lv_headerValue_20_0= RULE_BOOL ) ) otherlv_21= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_keys_22_0= 'keys' ) ) otherlv_23= '=' otherlv_24= '[' ( ( (lv_headerValues_25_0= ruleGroupType ) ) (otherlv_26= ',' ( (lv_headerValues_27_0= ruleGroupType ) ) )* )? otherlv_28= ']' otherlv_29= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_delimEnclosing_30_0= 'delimEnclosing' ) ) otherlv_31= '=' ( (lv_delimEnclosingValue_32_0= RULE_STRING ) ) otherlv_33= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_distinguishEmptyFromNull_34_0= 'delimEnclosing' ) ) otherlv_35= '=' ( (lv_distinguishEmptyFromNullValue_36_0= RULE_BOOL ) ) otherlv_37= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( ( (lv_useHeaderNames_38_0= 'delimEnclosing' ) ) otherlv_39= '=' ( (lv_useHeaderNamesValue_40_0= RULE_BOOL ) ) otherlv_41= ';' )? otherlv_42= '}' ) ) ) ) )+ {...}?) ) )
            {
            // InternalHdbti.g:159:2: ( ( ( ( ({...}? => ( ({...}? => (otherlv_1= '{' ( (lv_table_2_0= 'table' ) ) otherlv_3= '=' ( (lv_tableValue_4_0= RULE_STRING ) ) otherlv_5= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_schema_6_0= 'schema' ) ) otherlv_7= '=' ( (lv_schemaValue_8_0= RULE_STRING ) ) otherlv_9= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_file_10_0= 'file' ) ) otherlv_11= '=' ( (lv_fileValue_12_0= RULE_STRING ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_delimField_14_0= 'delimField' ) ) otherlv_15= '=' ( (lv_delimFieldValue_16_0= RULE_STRING ) ) otherlv_17= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_header_18_0= 'header' ) ) otherlv_19= '=' ( (lv_headerValue_20_0= RULE_BOOL ) ) otherlv_21= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_keys_22_0= 'keys' ) ) otherlv_23= '=' otherlv_24= '[' ( ( (lv_headerValues_25_0= ruleGroupType ) ) (otherlv_26= ',' ( (lv_headerValues_27_0= ruleGroupType ) ) )* )? otherlv_28= ']' otherlv_29= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_delimEnclosing_30_0= 'delimEnclosing' ) ) otherlv_31= '=' ( (lv_delimEnclosingValue_32_0= RULE_STRING ) ) otherlv_33= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_distinguishEmptyFromNull_34_0= 'delimEnclosing' ) ) otherlv_35= '=' ( (lv_distinguishEmptyFromNullValue_36_0= RULE_BOOL ) ) otherlv_37= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( ( (lv_useHeaderNames_38_0= 'delimEnclosing' ) ) otherlv_39= '=' ( (lv_useHeaderNamesValue_40_0= RULE_BOOL ) ) otherlv_41= ';' )? otherlv_42= '}' ) ) ) ) )+ {...}?) ) )
            // InternalHdbti.g:160:3: ( ( ( ({...}? => ( ({...}? => (otherlv_1= '{' ( (lv_table_2_0= 'table' ) ) otherlv_3= '=' ( (lv_tableValue_4_0= RULE_STRING ) ) otherlv_5= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_schema_6_0= 'schema' ) ) otherlv_7= '=' ( (lv_schemaValue_8_0= RULE_STRING ) ) otherlv_9= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_file_10_0= 'file' ) ) otherlv_11= '=' ( (lv_fileValue_12_0= RULE_STRING ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_delimField_14_0= 'delimField' ) ) otherlv_15= '=' ( (lv_delimFieldValue_16_0= RULE_STRING ) ) otherlv_17= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_header_18_0= 'header' ) ) otherlv_19= '=' ( (lv_headerValue_20_0= RULE_BOOL ) ) otherlv_21= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_keys_22_0= 'keys' ) ) otherlv_23= '=' otherlv_24= '[' ( ( (lv_headerValues_25_0= ruleGroupType ) ) (otherlv_26= ',' ( (lv_headerValues_27_0= ruleGroupType ) ) )* )? otherlv_28= ']' otherlv_29= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_delimEnclosing_30_0= 'delimEnclosing' ) ) otherlv_31= '=' ( (lv_delimEnclosingValue_32_0= RULE_STRING ) ) otherlv_33= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_distinguishEmptyFromNull_34_0= 'delimEnclosing' ) ) otherlv_35= '=' ( (lv_distinguishEmptyFromNullValue_36_0= RULE_BOOL ) ) otherlv_37= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( ( (lv_useHeaderNames_38_0= 'delimEnclosing' ) ) otherlv_39= '=' ( (lv_useHeaderNamesValue_40_0= RULE_BOOL ) ) otherlv_41= ';' )? otherlv_42= '}' ) ) ) ) )+ {...}?) )
            {
            // InternalHdbti.g:160:3: ( ( ( ({...}? => ( ({...}? => (otherlv_1= '{' ( (lv_table_2_0= 'table' ) ) otherlv_3= '=' ( (lv_tableValue_4_0= RULE_STRING ) ) otherlv_5= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_schema_6_0= 'schema' ) ) otherlv_7= '=' ( (lv_schemaValue_8_0= RULE_STRING ) ) otherlv_9= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_file_10_0= 'file' ) ) otherlv_11= '=' ( (lv_fileValue_12_0= RULE_STRING ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_delimField_14_0= 'delimField' ) ) otherlv_15= '=' ( (lv_delimFieldValue_16_0= RULE_STRING ) ) otherlv_17= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_header_18_0= 'header' ) ) otherlv_19= '=' ( (lv_headerValue_20_0= RULE_BOOL ) ) otherlv_21= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_keys_22_0= 'keys' ) ) otherlv_23= '=' otherlv_24= '[' ( ( (lv_headerValues_25_0= ruleGroupType ) ) (otherlv_26= ',' ( (lv_headerValues_27_0= ruleGroupType ) ) )* )? otherlv_28= ']' otherlv_29= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_delimEnclosing_30_0= 'delimEnclosing' ) ) otherlv_31= '=' ( (lv_delimEnclosingValue_32_0= RULE_STRING ) ) otherlv_33= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_distinguishEmptyFromNull_34_0= 'delimEnclosing' ) ) otherlv_35= '=' ( (lv_distinguishEmptyFromNullValue_36_0= RULE_BOOL ) ) otherlv_37= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( ( (lv_useHeaderNames_38_0= 'delimEnclosing' ) ) otherlv_39= '=' ( (lv_useHeaderNamesValue_40_0= RULE_BOOL ) ) otherlv_41= ';' )? otherlv_42= '}' ) ) ) ) )+ {...}?) )
            // InternalHdbti.g:161:4: ( ( ({...}? => ( ({...}? => (otherlv_1= '{' ( (lv_table_2_0= 'table' ) ) otherlv_3= '=' ( (lv_tableValue_4_0= RULE_STRING ) ) otherlv_5= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_schema_6_0= 'schema' ) ) otherlv_7= '=' ( (lv_schemaValue_8_0= RULE_STRING ) ) otherlv_9= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_file_10_0= 'file' ) ) otherlv_11= '=' ( (lv_fileValue_12_0= RULE_STRING ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_delimField_14_0= 'delimField' ) ) otherlv_15= '=' ( (lv_delimFieldValue_16_0= RULE_STRING ) ) otherlv_17= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_header_18_0= 'header' ) ) otherlv_19= '=' ( (lv_headerValue_20_0= RULE_BOOL ) ) otherlv_21= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_keys_22_0= 'keys' ) ) otherlv_23= '=' otherlv_24= '[' ( ( (lv_headerValues_25_0= ruleGroupType ) ) (otherlv_26= ',' ( (lv_headerValues_27_0= ruleGroupType ) ) )* )? otherlv_28= ']' otherlv_29= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_delimEnclosing_30_0= 'delimEnclosing' ) ) otherlv_31= '=' ( (lv_delimEnclosingValue_32_0= RULE_STRING ) ) otherlv_33= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_distinguishEmptyFromNull_34_0= 'delimEnclosing' ) ) otherlv_35= '=' ( (lv_distinguishEmptyFromNullValue_36_0= RULE_BOOL ) ) otherlv_37= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( ( (lv_useHeaderNames_38_0= 'delimEnclosing' ) ) otherlv_39= '=' ( (lv_useHeaderNamesValue_40_0= RULE_BOOL ) ) otherlv_41= ';' )? otherlv_42= '}' ) ) ) ) )+ {...}?)
            {
             
            			  getUnorderedGroupHelper().enter(grammarAccess.getImportConfigAccess().getUnorderedGroup());
            			
            // InternalHdbti.g:164:4: ( ( ({...}? => ( ({...}? => (otherlv_1= '{' ( (lv_table_2_0= 'table' ) ) otherlv_3= '=' ( (lv_tableValue_4_0= RULE_STRING ) ) otherlv_5= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_schema_6_0= 'schema' ) ) otherlv_7= '=' ( (lv_schemaValue_8_0= RULE_STRING ) ) otherlv_9= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_file_10_0= 'file' ) ) otherlv_11= '=' ( (lv_fileValue_12_0= RULE_STRING ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_delimField_14_0= 'delimField' ) ) otherlv_15= '=' ( (lv_delimFieldValue_16_0= RULE_STRING ) ) otherlv_17= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_header_18_0= 'header' ) ) otherlv_19= '=' ( (lv_headerValue_20_0= RULE_BOOL ) ) otherlv_21= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_keys_22_0= 'keys' ) ) otherlv_23= '=' otherlv_24= '[' ( ( (lv_headerValues_25_0= ruleGroupType ) ) (otherlv_26= ',' ( (lv_headerValues_27_0= ruleGroupType ) ) )* )? otherlv_28= ']' otherlv_29= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_delimEnclosing_30_0= 'delimEnclosing' ) ) otherlv_31= '=' ( (lv_delimEnclosingValue_32_0= RULE_STRING ) ) otherlv_33= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_distinguishEmptyFromNull_34_0= 'delimEnclosing' ) ) otherlv_35= '=' ( (lv_distinguishEmptyFromNullValue_36_0= RULE_BOOL ) ) otherlv_37= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( ( (lv_useHeaderNames_38_0= 'delimEnclosing' ) ) otherlv_39= '=' ( (lv_useHeaderNamesValue_40_0= RULE_BOOL ) ) otherlv_41= ';' )? otherlv_42= '}' ) ) ) ) )+ {...}?)
            // InternalHdbti.g:165:5: ( ({...}? => ( ({...}? => (otherlv_1= '{' ( (lv_table_2_0= 'table' ) ) otherlv_3= '=' ( (lv_tableValue_4_0= RULE_STRING ) ) otherlv_5= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_schema_6_0= 'schema' ) ) otherlv_7= '=' ( (lv_schemaValue_8_0= RULE_STRING ) ) otherlv_9= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_file_10_0= 'file' ) ) otherlv_11= '=' ( (lv_fileValue_12_0= RULE_STRING ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_delimField_14_0= 'delimField' ) ) otherlv_15= '=' ( (lv_delimFieldValue_16_0= RULE_STRING ) ) otherlv_17= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_header_18_0= 'header' ) ) otherlv_19= '=' ( (lv_headerValue_20_0= RULE_BOOL ) ) otherlv_21= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_keys_22_0= 'keys' ) ) otherlv_23= '=' otherlv_24= '[' ( ( (lv_headerValues_25_0= ruleGroupType ) ) (otherlv_26= ',' ( (lv_headerValues_27_0= ruleGroupType ) ) )* )? otherlv_28= ']' otherlv_29= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_delimEnclosing_30_0= 'delimEnclosing' ) ) otherlv_31= '=' ( (lv_delimEnclosingValue_32_0= RULE_STRING ) ) otherlv_33= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_distinguishEmptyFromNull_34_0= 'delimEnclosing' ) ) otherlv_35= '=' ( (lv_distinguishEmptyFromNullValue_36_0= RULE_BOOL ) ) otherlv_37= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( ( (lv_useHeaderNames_38_0= 'delimEnclosing' ) ) otherlv_39= '=' ( (lv_useHeaderNamesValue_40_0= RULE_BOOL ) ) otherlv_41= ';' )? otherlv_42= '}' ) ) ) ) )+ {...}?
            {
            // InternalHdbti.g:165:5: ( ({...}? => ( ({...}? => (otherlv_1= '{' ( (lv_table_2_0= 'table' ) ) otherlv_3= '=' ( (lv_tableValue_4_0= RULE_STRING ) ) otherlv_5= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_schema_6_0= 'schema' ) ) otherlv_7= '=' ( (lv_schemaValue_8_0= RULE_STRING ) ) otherlv_9= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_file_10_0= 'file' ) ) otherlv_11= '=' ( (lv_fileValue_12_0= RULE_STRING ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_delimField_14_0= 'delimField' ) ) otherlv_15= '=' ( (lv_delimFieldValue_16_0= RULE_STRING ) ) otherlv_17= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_header_18_0= 'header' ) ) otherlv_19= '=' ( (lv_headerValue_20_0= RULE_BOOL ) ) otherlv_21= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_keys_22_0= 'keys' ) ) otherlv_23= '=' otherlv_24= '[' ( ( (lv_headerValues_25_0= ruleGroupType ) ) (otherlv_26= ',' ( (lv_headerValues_27_0= ruleGroupType ) ) )* )? otherlv_28= ']' otherlv_29= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_delimEnclosing_30_0= 'delimEnclosing' ) ) otherlv_31= '=' ( (lv_delimEnclosingValue_32_0= RULE_STRING ) ) otherlv_33= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_distinguishEmptyFromNull_34_0= 'delimEnclosing' ) ) otherlv_35= '=' ( (lv_distinguishEmptyFromNullValue_36_0= RULE_BOOL ) ) otherlv_37= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( ( (lv_useHeaderNames_38_0= 'delimEnclosing' ) ) otherlv_39= '=' ( (lv_useHeaderNamesValue_40_0= RULE_BOOL ) ) otherlv_41= ';' )? otherlv_42= '}' ) ) ) ) )+
            int cnt4=0;
            loop4:
            do {
                int alt4=10;
                alt4 = dfa4.predict(input);
                switch (alt4) {
            	case 1 :
            	    // InternalHdbti.g:166:3: ({...}? => ( ({...}? => (otherlv_1= '{' ( (lv_table_2_0= 'table' ) ) otherlv_3= '=' ( (lv_tableValue_4_0= RULE_STRING ) ) otherlv_5= ';' ) ) ) )
            	    {
            	    // InternalHdbti.g:166:3: ({...}? => ( ({...}? => (otherlv_1= '{' ( (lv_table_2_0= 'table' ) ) otherlv_3= '=' ( (lv_tableValue_4_0= RULE_STRING ) ) otherlv_5= ';' ) ) ) )
            	    // InternalHdbti.g:167:4: {...}? => ( ({...}? => (otherlv_1= '{' ( (lv_table_2_0= 'table' ) ) otherlv_3= '=' ( (lv_tableValue_4_0= RULE_STRING ) ) otherlv_5= ';' ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 0) ) {
            	        throw new FailedPredicateException(input, "ruleImportConfig", "getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 0)");
            	    }
            	    // InternalHdbti.g:167:106: ( ({...}? => (otherlv_1= '{' ( (lv_table_2_0= 'table' ) ) otherlv_3= '=' ( (lv_tableValue_4_0= RULE_STRING ) ) otherlv_5= ';' ) ) )
            	    // InternalHdbti.g:168:5: ({...}? => (otherlv_1= '{' ( (lv_table_2_0= 'table' ) ) otherlv_3= '=' ( (lv_tableValue_4_0= RULE_STRING ) ) otherlv_5= ';' ) )
            	    {

            	    					getUnorderedGroupHelper().select(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 0);
            	    				
            	    // InternalHdbti.g:171:8: ({...}? => (otherlv_1= '{' ( (lv_table_2_0= 'table' ) ) otherlv_3= '=' ( (lv_tableValue_4_0= RULE_STRING ) ) otherlv_5= ';' ) )
            	    // InternalHdbti.g:171:9: {...}? => (otherlv_1= '{' ( (lv_table_2_0= 'table' ) ) otherlv_3= '=' ( (lv_tableValue_4_0= RULE_STRING ) ) otherlv_5= ';' )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleImportConfig", "true");
            	    }
            	    // InternalHdbti.g:171:18: (otherlv_1= '{' ( (lv_table_2_0= 'table' ) ) otherlv_3= '=' ( (lv_tableValue_4_0= RULE_STRING ) ) otherlv_5= ';' )
            	    // InternalHdbti.g:171:19: otherlv_1= '{' ( (lv_table_2_0= 'table' ) ) otherlv_3= '=' ( (lv_tableValue_4_0= RULE_STRING ) ) otherlv_5= ';'
            	    {
            	    otherlv_1=(Token)match(input,14,FOLLOW_5); 

            	    								newLeafNode(otherlv_1, grammarAccess.getImportConfigAccess().getLeftCurlyBracketKeyword_0_0());
            	    							
            	    // InternalHdbti.g:175:8: ( (lv_table_2_0= 'table' ) )
            	    // InternalHdbti.g:176:9: (lv_table_2_0= 'table' )
            	    {
            	    // InternalHdbti.g:176:9: (lv_table_2_0= 'table' )
            	    // InternalHdbti.g:177:10: lv_table_2_0= 'table'
            	    {
            	    lv_table_2_0=(Token)match(input,15,FOLLOW_6); 

            	    										newLeafNode(lv_table_2_0, grammarAccess.getImportConfigAccess().getTableTableKeyword_0_1_0());
            	    									

            	    										if (current==null) {
            	    											current = createModelElement(grammarAccess.getImportConfigRule());
            	    										}
            	    										setWithLastConsumed(current, "table", true, "table");
            	    									

            	    }


            	    }

            	    otherlv_3=(Token)match(input,16,FOLLOW_4); 

            	    								newLeafNode(otherlv_3, grammarAccess.getImportConfigAccess().getEqualsSignKeyword_0_2());
            	    							
            	    // InternalHdbti.g:193:8: ( (lv_tableValue_4_0= RULE_STRING ) )
            	    // InternalHdbti.g:194:9: (lv_tableValue_4_0= RULE_STRING )
            	    {
            	    // InternalHdbti.g:194:9: (lv_tableValue_4_0= RULE_STRING )
            	    // InternalHdbti.g:195:10: lv_tableValue_4_0= RULE_STRING
            	    {
            	    lv_tableValue_4_0=(Token)match(input,RULE_STRING,FOLLOW_7); 

            	    										newLeafNode(lv_tableValue_4_0, grammarAccess.getImportConfigAccess().getTableValueSTRINGTerminalRuleCall_0_3_0());
            	    									

            	    										if (current==null) {
            	    											current = createModelElement(grammarAccess.getImportConfigRule());
            	    										}
            	    										setWithLastConsumed(
            	    											current,
            	    											"tableValue",
            	    											lv_tableValue_4_0,
            	    											"org.eclipse.xtext.common.Terminals.STRING");
            	    									

            	    }


            	    }

            	    otherlv_5=(Token)match(input,17,FOLLOW_8); 

            	    								newLeafNode(otherlv_5, grammarAccess.getImportConfigAccess().getSemicolonKeyword_0_4());
            	    							

            	    }


            	    }

            	     
            	    					getUnorderedGroupHelper().returnFromSelection(grammarAccess.getImportConfigAccess().getUnorderedGroup());
            	    				

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalHdbti.g:221:3: ({...}? => ( ({...}? => ( ( (lv_schema_6_0= 'schema' ) ) otherlv_7= '=' ( (lv_schemaValue_8_0= RULE_STRING ) ) otherlv_9= ';' ) ) ) )
            	    {
            	    // InternalHdbti.g:221:3: ({...}? => ( ({...}? => ( ( (lv_schema_6_0= 'schema' ) ) otherlv_7= '=' ( (lv_schemaValue_8_0= RULE_STRING ) ) otherlv_9= ';' ) ) ) )
            	    // InternalHdbti.g:222:4: {...}? => ( ({...}? => ( ( (lv_schema_6_0= 'schema' ) ) otherlv_7= '=' ( (lv_schemaValue_8_0= RULE_STRING ) ) otherlv_9= ';' ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 1) ) {
            	        throw new FailedPredicateException(input, "ruleImportConfig", "getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 1)");
            	    }
            	    // InternalHdbti.g:222:106: ( ({...}? => ( ( (lv_schema_6_0= 'schema' ) ) otherlv_7= '=' ( (lv_schemaValue_8_0= RULE_STRING ) ) otherlv_9= ';' ) ) )
            	    // InternalHdbti.g:223:5: ({...}? => ( ( (lv_schema_6_0= 'schema' ) ) otherlv_7= '=' ( (lv_schemaValue_8_0= RULE_STRING ) ) otherlv_9= ';' ) )
            	    {

            	    					getUnorderedGroupHelper().select(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 1);
            	    				
            	    // InternalHdbti.g:226:8: ({...}? => ( ( (lv_schema_6_0= 'schema' ) ) otherlv_7= '=' ( (lv_schemaValue_8_0= RULE_STRING ) ) otherlv_9= ';' ) )
            	    // InternalHdbti.g:226:9: {...}? => ( ( (lv_schema_6_0= 'schema' ) ) otherlv_7= '=' ( (lv_schemaValue_8_0= RULE_STRING ) ) otherlv_9= ';' )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleImportConfig", "true");
            	    }
            	    // InternalHdbti.g:226:18: ( ( (lv_schema_6_0= 'schema' ) ) otherlv_7= '=' ( (lv_schemaValue_8_0= RULE_STRING ) ) otherlv_9= ';' )
            	    // InternalHdbti.g:226:19: ( (lv_schema_6_0= 'schema' ) ) otherlv_7= '=' ( (lv_schemaValue_8_0= RULE_STRING ) ) otherlv_9= ';'
            	    {
            	    // InternalHdbti.g:226:19: ( (lv_schema_6_0= 'schema' ) )
            	    // InternalHdbti.g:227:9: (lv_schema_6_0= 'schema' )
            	    {
            	    // InternalHdbti.g:227:9: (lv_schema_6_0= 'schema' )
            	    // InternalHdbti.g:228:10: lv_schema_6_0= 'schema'
            	    {
            	    lv_schema_6_0=(Token)match(input,18,FOLLOW_6); 

            	    										newLeafNode(lv_schema_6_0, grammarAccess.getImportConfigAccess().getSchemaSchemaKeyword_1_0_0());
            	    									

            	    										if (current==null) {
            	    											current = createModelElement(grammarAccess.getImportConfigRule());
            	    										}
            	    										setWithLastConsumed(current, "schema", true, "schema");
            	    									

            	    }


            	    }

            	    otherlv_7=(Token)match(input,16,FOLLOW_4); 

            	    								newLeafNode(otherlv_7, grammarAccess.getImportConfigAccess().getEqualsSignKeyword_1_1());
            	    							
            	    // InternalHdbti.g:244:8: ( (lv_schemaValue_8_0= RULE_STRING ) )
            	    // InternalHdbti.g:245:9: (lv_schemaValue_8_0= RULE_STRING )
            	    {
            	    // InternalHdbti.g:245:9: (lv_schemaValue_8_0= RULE_STRING )
            	    // InternalHdbti.g:246:10: lv_schemaValue_8_0= RULE_STRING
            	    {
            	    lv_schemaValue_8_0=(Token)match(input,RULE_STRING,FOLLOW_7); 

            	    										newLeafNode(lv_schemaValue_8_0, grammarAccess.getImportConfigAccess().getSchemaValueSTRINGTerminalRuleCall_1_2_0());
            	    									

            	    										if (current==null) {
            	    											current = createModelElement(grammarAccess.getImportConfigRule());
            	    										}
            	    										setWithLastConsumed(
            	    											current,
            	    											"schemaValue",
            	    											lv_schemaValue_8_0,
            	    											"org.eclipse.xtext.common.Terminals.STRING");
            	    									

            	    }


            	    }

            	    otherlv_9=(Token)match(input,17,FOLLOW_8); 

            	    								newLeafNode(otherlv_9, grammarAccess.getImportConfigAccess().getSemicolonKeyword_1_3());
            	    							

            	    }


            	    }

            	     
            	    					getUnorderedGroupHelper().returnFromSelection(grammarAccess.getImportConfigAccess().getUnorderedGroup());
            	    				

            	    }


            	    }


            	    }
            	    break;
            	case 3 :
            	    // InternalHdbti.g:272:3: ({...}? => ( ({...}? => ( ( (lv_file_10_0= 'file' ) ) otherlv_11= '=' ( (lv_fileValue_12_0= RULE_STRING ) ) otherlv_13= ';' ) ) ) )
            	    {
            	    // InternalHdbti.g:272:3: ({...}? => ( ({...}? => ( ( (lv_file_10_0= 'file' ) ) otherlv_11= '=' ( (lv_fileValue_12_0= RULE_STRING ) ) otherlv_13= ';' ) ) ) )
            	    // InternalHdbti.g:273:4: {...}? => ( ({...}? => ( ( (lv_file_10_0= 'file' ) ) otherlv_11= '=' ( (lv_fileValue_12_0= RULE_STRING ) ) otherlv_13= ';' ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 2) ) {
            	        throw new FailedPredicateException(input, "ruleImportConfig", "getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 2)");
            	    }
            	    // InternalHdbti.g:273:106: ( ({...}? => ( ( (lv_file_10_0= 'file' ) ) otherlv_11= '=' ( (lv_fileValue_12_0= RULE_STRING ) ) otherlv_13= ';' ) ) )
            	    // InternalHdbti.g:274:5: ({...}? => ( ( (lv_file_10_0= 'file' ) ) otherlv_11= '=' ( (lv_fileValue_12_0= RULE_STRING ) ) otherlv_13= ';' ) )
            	    {

            	    					getUnorderedGroupHelper().select(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 2);
            	    				
            	    // InternalHdbti.g:277:8: ({...}? => ( ( (lv_file_10_0= 'file' ) ) otherlv_11= '=' ( (lv_fileValue_12_0= RULE_STRING ) ) otherlv_13= ';' ) )
            	    // InternalHdbti.g:277:9: {...}? => ( ( (lv_file_10_0= 'file' ) ) otherlv_11= '=' ( (lv_fileValue_12_0= RULE_STRING ) ) otherlv_13= ';' )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleImportConfig", "true");
            	    }
            	    // InternalHdbti.g:277:18: ( ( (lv_file_10_0= 'file' ) ) otherlv_11= '=' ( (lv_fileValue_12_0= RULE_STRING ) ) otherlv_13= ';' )
            	    // InternalHdbti.g:277:19: ( (lv_file_10_0= 'file' ) ) otherlv_11= '=' ( (lv_fileValue_12_0= RULE_STRING ) ) otherlv_13= ';'
            	    {
            	    // InternalHdbti.g:277:19: ( (lv_file_10_0= 'file' ) )
            	    // InternalHdbti.g:278:9: (lv_file_10_0= 'file' )
            	    {
            	    // InternalHdbti.g:278:9: (lv_file_10_0= 'file' )
            	    // InternalHdbti.g:279:10: lv_file_10_0= 'file'
            	    {
            	    lv_file_10_0=(Token)match(input,19,FOLLOW_6); 

            	    										newLeafNode(lv_file_10_0, grammarAccess.getImportConfigAccess().getFileFileKeyword_2_0_0());
            	    									

            	    										if (current==null) {
            	    											current = createModelElement(grammarAccess.getImportConfigRule());
            	    										}
            	    										setWithLastConsumed(current, "file", true, "file");
            	    									

            	    }


            	    }

            	    otherlv_11=(Token)match(input,16,FOLLOW_4); 

            	    								newLeafNode(otherlv_11, grammarAccess.getImportConfigAccess().getEqualsSignKeyword_2_1());
            	    							
            	    // InternalHdbti.g:295:8: ( (lv_fileValue_12_0= RULE_STRING ) )
            	    // InternalHdbti.g:296:9: (lv_fileValue_12_0= RULE_STRING )
            	    {
            	    // InternalHdbti.g:296:9: (lv_fileValue_12_0= RULE_STRING )
            	    // InternalHdbti.g:297:10: lv_fileValue_12_0= RULE_STRING
            	    {
            	    lv_fileValue_12_0=(Token)match(input,RULE_STRING,FOLLOW_7); 

            	    										newLeafNode(lv_fileValue_12_0, grammarAccess.getImportConfigAccess().getFileValueSTRINGTerminalRuleCall_2_2_0());
            	    									

            	    										if (current==null) {
            	    											current = createModelElement(grammarAccess.getImportConfigRule());
            	    										}
            	    										setWithLastConsumed(
            	    											current,
            	    											"fileValue",
            	    											lv_fileValue_12_0,
            	    											"org.eclipse.xtext.common.Terminals.STRING");
            	    									

            	    }


            	    }

            	    otherlv_13=(Token)match(input,17,FOLLOW_8); 

            	    								newLeafNode(otherlv_13, grammarAccess.getImportConfigAccess().getSemicolonKeyword_2_3());
            	    							

            	    }


            	    }

            	     
            	    					getUnorderedGroupHelper().returnFromSelection(grammarAccess.getImportConfigAccess().getUnorderedGroup());
            	    				

            	    }


            	    }


            	    }
            	    break;
            	case 4 :
            	    // InternalHdbti.g:323:3: ({...}? => ( ({...}? => ( ( (lv_delimField_14_0= 'delimField' ) ) otherlv_15= '=' ( (lv_delimFieldValue_16_0= RULE_STRING ) ) otherlv_17= ';' ) ) ) )
            	    {
            	    // InternalHdbti.g:323:3: ({...}? => ( ({...}? => ( ( (lv_delimField_14_0= 'delimField' ) ) otherlv_15= '=' ( (lv_delimFieldValue_16_0= RULE_STRING ) ) otherlv_17= ';' ) ) ) )
            	    // InternalHdbti.g:324:4: {...}? => ( ({...}? => ( ( (lv_delimField_14_0= 'delimField' ) ) otherlv_15= '=' ( (lv_delimFieldValue_16_0= RULE_STRING ) ) otherlv_17= ';' ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 3) ) {
            	        throw new FailedPredicateException(input, "ruleImportConfig", "getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 3)");
            	    }
            	    // InternalHdbti.g:324:106: ( ({...}? => ( ( (lv_delimField_14_0= 'delimField' ) ) otherlv_15= '=' ( (lv_delimFieldValue_16_0= RULE_STRING ) ) otherlv_17= ';' ) ) )
            	    // InternalHdbti.g:325:5: ({...}? => ( ( (lv_delimField_14_0= 'delimField' ) ) otherlv_15= '=' ( (lv_delimFieldValue_16_0= RULE_STRING ) ) otherlv_17= ';' ) )
            	    {

            	    					getUnorderedGroupHelper().select(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 3);
            	    				
            	    // InternalHdbti.g:328:8: ({...}? => ( ( (lv_delimField_14_0= 'delimField' ) ) otherlv_15= '=' ( (lv_delimFieldValue_16_0= RULE_STRING ) ) otherlv_17= ';' ) )
            	    // InternalHdbti.g:328:9: {...}? => ( ( (lv_delimField_14_0= 'delimField' ) ) otherlv_15= '=' ( (lv_delimFieldValue_16_0= RULE_STRING ) ) otherlv_17= ';' )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleImportConfig", "true");
            	    }
            	    // InternalHdbti.g:328:18: ( ( (lv_delimField_14_0= 'delimField' ) ) otherlv_15= '=' ( (lv_delimFieldValue_16_0= RULE_STRING ) ) otherlv_17= ';' )
            	    // InternalHdbti.g:328:19: ( (lv_delimField_14_0= 'delimField' ) ) otherlv_15= '=' ( (lv_delimFieldValue_16_0= RULE_STRING ) ) otherlv_17= ';'
            	    {
            	    // InternalHdbti.g:328:19: ( (lv_delimField_14_0= 'delimField' ) )
            	    // InternalHdbti.g:329:9: (lv_delimField_14_0= 'delimField' )
            	    {
            	    // InternalHdbti.g:329:9: (lv_delimField_14_0= 'delimField' )
            	    // InternalHdbti.g:330:10: lv_delimField_14_0= 'delimField'
            	    {
            	    lv_delimField_14_0=(Token)match(input,20,FOLLOW_6); 

            	    										newLeafNode(lv_delimField_14_0, grammarAccess.getImportConfigAccess().getDelimFieldDelimFieldKeyword_3_0_0());
            	    									

            	    										if (current==null) {
            	    											current = createModelElement(grammarAccess.getImportConfigRule());
            	    										}
            	    										setWithLastConsumed(current, "delimField", true, "delimField");
            	    									

            	    }


            	    }

            	    otherlv_15=(Token)match(input,16,FOLLOW_4); 

            	    								newLeafNode(otherlv_15, grammarAccess.getImportConfigAccess().getEqualsSignKeyword_3_1());
            	    							
            	    // InternalHdbti.g:346:8: ( (lv_delimFieldValue_16_0= RULE_STRING ) )
            	    // InternalHdbti.g:347:9: (lv_delimFieldValue_16_0= RULE_STRING )
            	    {
            	    // InternalHdbti.g:347:9: (lv_delimFieldValue_16_0= RULE_STRING )
            	    // InternalHdbti.g:348:10: lv_delimFieldValue_16_0= RULE_STRING
            	    {
            	    lv_delimFieldValue_16_0=(Token)match(input,RULE_STRING,FOLLOW_7); 

            	    										newLeafNode(lv_delimFieldValue_16_0, grammarAccess.getImportConfigAccess().getDelimFieldValueSTRINGTerminalRuleCall_3_2_0());
            	    									

            	    										if (current==null) {
            	    											current = createModelElement(grammarAccess.getImportConfigRule());
            	    										}
            	    										setWithLastConsumed(
            	    											current,
            	    											"delimFieldValue",
            	    											lv_delimFieldValue_16_0,
            	    											"org.eclipse.xtext.common.Terminals.STRING");
            	    									

            	    }


            	    }

            	    otherlv_17=(Token)match(input,17,FOLLOW_8); 

            	    								newLeafNode(otherlv_17, grammarAccess.getImportConfigAccess().getSemicolonKeyword_3_3());
            	    							

            	    }


            	    }

            	     
            	    					getUnorderedGroupHelper().returnFromSelection(grammarAccess.getImportConfigAccess().getUnorderedGroup());
            	    				

            	    }


            	    }


            	    }
            	    break;
            	case 5 :
            	    // InternalHdbti.g:374:3: ({...}? => ( ({...}? => ( ( (lv_header_18_0= 'header' ) ) otherlv_19= '=' ( (lv_headerValue_20_0= RULE_BOOL ) ) otherlv_21= ';' ) ) ) )
            	    {
            	    // InternalHdbti.g:374:3: ({...}? => ( ({...}? => ( ( (lv_header_18_0= 'header' ) ) otherlv_19= '=' ( (lv_headerValue_20_0= RULE_BOOL ) ) otherlv_21= ';' ) ) ) )
            	    // InternalHdbti.g:375:4: {...}? => ( ({...}? => ( ( (lv_header_18_0= 'header' ) ) otherlv_19= '=' ( (lv_headerValue_20_0= RULE_BOOL ) ) otherlv_21= ';' ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 4) ) {
            	        throw new FailedPredicateException(input, "ruleImportConfig", "getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 4)");
            	    }
            	    // InternalHdbti.g:375:106: ( ({...}? => ( ( (lv_header_18_0= 'header' ) ) otherlv_19= '=' ( (lv_headerValue_20_0= RULE_BOOL ) ) otherlv_21= ';' ) ) )
            	    // InternalHdbti.g:376:5: ({...}? => ( ( (lv_header_18_0= 'header' ) ) otherlv_19= '=' ( (lv_headerValue_20_0= RULE_BOOL ) ) otherlv_21= ';' ) )
            	    {

            	    					getUnorderedGroupHelper().select(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 4);
            	    				
            	    // InternalHdbti.g:379:8: ({...}? => ( ( (lv_header_18_0= 'header' ) ) otherlv_19= '=' ( (lv_headerValue_20_0= RULE_BOOL ) ) otherlv_21= ';' ) )
            	    // InternalHdbti.g:379:9: {...}? => ( ( (lv_header_18_0= 'header' ) ) otherlv_19= '=' ( (lv_headerValue_20_0= RULE_BOOL ) ) otherlv_21= ';' )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleImportConfig", "true");
            	    }
            	    // InternalHdbti.g:379:18: ( ( (lv_header_18_0= 'header' ) ) otherlv_19= '=' ( (lv_headerValue_20_0= RULE_BOOL ) ) otherlv_21= ';' )
            	    // InternalHdbti.g:379:19: ( (lv_header_18_0= 'header' ) ) otherlv_19= '=' ( (lv_headerValue_20_0= RULE_BOOL ) ) otherlv_21= ';'
            	    {
            	    // InternalHdbti.g:379:19: ( (lv_header_18_0= 'header' ) )
            	    // InternalHdbti.g:380:9: (lv_header_18_0= 'header' )
            	    {
            	    // InternalHdbti.g:380:9: (lv_header_18_0= 'header' )
            	    // InternalHdbti.g:381:10: lv_header_18_0= 'header'
            	    {
            	    lv_header_18_0=(Token)match(input,21,FOLLOW_6); 

            	    										newLeafNode(lv_header_18_0, grammarAccess.getImportConfigAccess().getHeaderHeaderKeyword_4_0_0());
            	    									

            	    										if (current==null) {
            	    											current = createModelElement(grammarAccess.getImportConfigRule());
            	    										}
            	    										setWithLastConsumed(current, "header", true, "header");
            	    									

            	    }


            	    }

            	    otherlv_19=(Token)match(input,16,FOLLOW_9); 

            	    								newLeafNode(otherlv_19, grammarAccess.getImportConfigAccess().getEqualsSignKeyword_4_1());
            	    							
            	    // InternalHdbti.g:397:8: ( (lv_headerValue_20_0= RULE_BOOL ) )
            	    // InternalHdbti.g:398:9: (lv_headerValue_20_0= RULE_BOOL )
            	    {
            	    // InternalHdbti.g:398:9: (lv_headerValue_20_0= RULE_BOOL )
            	    // InternalHdbti.g:399:10: lv_headerValue_20_0= RULE_BOOL
            	    {
            	    lv_headerValue_20_0=(Token)match(input,RULE_BOOL,FOLLOW_7); 

            	    										newLeafNode(lv_headerValue_20_0, grammarAccess.getImportConfigAccess().getHeaderValueBOOLTerminalRuleCall_4_2_0());
            	    									

            	    										if (current==null) {
            	    											current = createModelElement(grammarAccess.getImportConfigRule());
            	    										}
            	    										setWithLastConsumed(
            	    											current,
            	    											"headerValue",
            	    											lv_headerValue_20_0,
            	    											"com.sap.xsk.models.hdbti.Hdbti.BOOL");
            	    									

            	    }


            	    }

            	    otherlv_21=(Token)match(input,17,FOLLOW_8); 

            	    								newLeafNode(otherlv_21, grammarAccess.getImportConfigAccess().getSemicolonKeyword_4_3());
            	    							

            	    }


            	    }

            	     
            	    					getUnorderedGroupHelper().returnFromSelection(grammarAccess.getImportConfigAccess().getUnorderedGroup());
            	    				

            	    }


            	    }


            	    }
            	    break;
            	case 6 :
            	    // InternalHdbti.g:425:3: ({...}? => ( ({...}? => ( ( (lv_keys_22_0= 'keys' ) ) otherlv_23= '=' otherlv_24= '[' ( ( (lv_headerValues_25_0= ruleGroupType ) ) (otherlv_26= ',' ( (lv_headerValues_27_0= ruleGroupType ) ) )* )? otherlv_28= ']' otherlv_29= ';' ) ) ) )
            	    {
            	    // InternalHdbti.g:425:3: ({...}? => ( ({...}? => ( ( (lv_keys_22_0= 'keys' ) ) otherlv_23= '=' otherlv_24= '[' ( ( (lv_headerValues_25_0= ruleGroupType ) ) (otherlv_26= ',' ( (lv_headerValues_27_0= ruleGroupType ) ) )* )? otherlv_28= ']' otherlv_29= ';' ) ) ) )
            	    // InternalHdbti.g:426:4: {...}? => ( ({...}? => ( ( (lv_keys_22_0= 'keys' ) ) otherlv_23= '=' otherlv_24= '[' ( ( (lv_headerValues_25_0= ruleGroupType ) ) (otherlv_26= ',' ( (lv_headerValues_27_0= ruleGroupType ) ) )* )? otherlv_28= ']' otherlv_29= ';' ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 5) ) {
            	        throw new FailedPredicateException(input, "ruleImportConfig", "getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 5)");
            	    }
            	    // InternalHdbti.g:426:106: ( ({...}? => ( ( (lv_keys_22_0= 'keys' ) ) otherlv_23= '=' otherlv_24= '[' ( ( (lv_headerValues_25_0= ruleGroupType ) ) (otherlv_26= ',' ( (lv_headerValues_27_0= ruleGroupType ) ) )* )? otherlv_28= ']' otherlv_29= ';' ) ) )
            	    // InternalHdbti.g:427:5: ({...}? => ( ( (lv_keys_22_0= 'keys' ) ) otherlv_23= '=' otherlv_24= '[' ( ( (lv_headerValues_25_0= ruleGroupType ) ) (otherlv_26= ',' ( (lv_headerValues_27_0= ruleGroupType ) ) )* )? otherlv_28= ']' otherlv_29= ';' ) )
            	    {

            	    					getUnorderedGroupHelper().select(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 5);
            	    				
            	    // InternalHdbti.g:430:8: ({...}? => ( ( (lv_keys_22_0= 'keys' ) ) otherlv_23= '=' otherlv_24= '[' ( ( (lv_headerValues_25_0= ruleGroupType ) ) (otherlv_26= ',' ( (lv_headerValues_27_0= ruleGroupType ) ) )* )? otherlv_28= ']' otherlv_29= ';' ) )
            	    // InternalHdbti.g:430:9: {...}? => ( ( (lv_keys_22_0= 'keys' ) ) otherlv_23= '=' otherlv_24= '[' ( ( (lv_headerValues_25_0= ruleGroupType ) ) (otherlv_26= ',' ( (lv_headerValues_27_0= ruleGroupType ) ) )* )? otherlv_28= ']' otherlv_29= ';' )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleImportConfig", "true");
            	    }
            	    // InternalHdbti.g:430:18: ( ( (lv_keys_22_0= 'keys' ) ) otherlv_23= '=' otherlv_24= '[' ( ( (lv_headerValues_25_0= ruleGroupType ) ) (otherlv_26= ',' ( (lv_headerValues_27_0= ruleGroupType ) ) )* )? otherlv_28= ']' otherlv_29= ';' )
            	    // InternalHdbti.g:430:19: ( (lv_keys_22_0= 'keys' ) ) otherlv_23= '=' otherlv_24= '[' ( ( (lv_headerValues_25_0= ruleGroupType ) ) (otherlv_26= ',' ( (lv_headerValues_27_0= ruleGroupType ) ) )* )? otherlv_28= ']' otherlv_29= ';'
            	    {
            	    // InternalHdbti.g:430:19: ( (lv_keys_22_0= 'keys' ) )
            	    // InternalHdbti.g:431:9: (lv_keys_22_0= 'keys' )
            	    {
            	    // InternalHdbti.g:431:9: (lv_keys_22_0= 'keys' )
            	    // InternalHdbti.g:432:10: lv_keys_22_0= 'keys'
            	    {
            	    lv_keys_22_0=(Token)match(input,22,FOLLOW_6); 

            	    										newLeafNode(lv_keys_22_0, grammarAccess.getImportConfigAccess().getKeysKeysKeyword_5_0_0());
            	    									

            	    										if (current==null) {
            	    											current = createModelElement(grammarAccess.getImportConfigRule());
            	    										}
            	    										setWithLastConsumed(current, "keys", true, "keys");
            	    									

            	    }


            	    }

            	    otherlv_23=(Token)match(input,16,FOLLOW_10); 

            	    								newLeafNode(otherlv_23, grammarAccess.getImportConfigAccess().getEqualsSignKeyword_5_1());
            	    							
            	    otherlv_24=(Token)match(input,23,FOLLOW_11); 

            	    								newLeafNode(otherlv_24, grammarAccess.getImportConfigAccess().getLeftSquareBracketKeyword_5_2());
            	    							
            	    // InternalHdbti.g:452:8: ( ( (lv_headerValues_25_0= ruleGroupType ) ) (otherlv_26= ',' ( (lv_headerValues_27_0= ruleGroupType ) ) )* )?
            	    int alt2=2;
            	    int LA2_0 = input.LA(1);

            	    if ( (LA2_0==12) ) {
            	        alt2=1;
            	    }
            	    switch (alt2) {
            	        case 1 :
            	            // InternalHdbti.g:453:9: ( (lv_headerValues_25_0= ruleGroupType ) ) (otherlv_26= ',' ( (lv_headerValues_27_0= ruleGroupType ) ) )*
            	            {
            	            // InternalHdbti.g:453:9: ( (lv_headerValues_25_0= ruleGroupType ) )
            	            // InternalHdbti.g:454:10: (lv_headerValues_25_0= ruleGroupType )
            	            {
            	            // InternalHdbti.g:454:10: (lv_headerValues_25_0= ruleGroupType )
            	            // InternalHdbti.g:455:11: lv_headerValues_25_0= ruleGroupType
            	            {

            	            											newCompositeNode(grammarAccess.getImportConfigAccess().getHeaderValuesGroupTypeParserRuleCall_5_3_0_0());
            	            										
            	            pushFollow(FOLLOW_12);
            	            lv_headerValues_25_0=ruleGroupType();

            	            state._fsp--;


            	            											if (current==null) {
            	            												current = createModelElementForParent(grammarAccess.getImportConfigRule());
            	            											}
            	            											add(
            	            												current,
            	            												"headerValues",
            	            												lv_headerValues_25_0,
            	            												"com.sap.xsk.models.hdbti.Hdbti.GroupType");
            	            											afterParserOrEnumRuleCall();
            	            										

            	            }


            	            }

            	            // InternalHdbti.g:472:9: (otherlv_26= ',' ( (lv_headerValues_27_0= ruleGroupType ) ) )*
            	            loop1:
            	            do {
            	                int alt1=2;
            	                int LA1_0 = input.LA(1);

            	                if ( (LA1_0==24) ) {
            	                    alt1=1;
            	                }


            	                switch (alt1) {
            	            	case 1 :
            	            	    // InternalHdbti.g:473:10: otherlv_26= ',' ( (lv_headerValues_27_0= ruleGroupType ) )
            	            	    {
            	            	    otherlv_26=(Token)match(input,24,FOLLOW_13); 

            	            	    										newLeafNode(otherlv_26, grammarAccess.getImportConfigAccess().getCommaKeyword_5_3_1_0());
            	            	    									
            	            	    // InternalHdbti.g:477:10: ( (lv_headerValues_27_0= ruleGroupType ) )
            	            	    // InternalHdbti.g:478:11: (lv_headerValues_27_0= ruleGroupType )
            	            	    {
            	            	    // InternalHdbti.g:478:11: (lv_headerValues_27_0= ruleGroupType )
            	            	    // InternalHdbti.g:479:12: lv_headerValues_27_0= ruleGroupType
            	            	    {

            	            	    												newCompositeNode(grammarAccess.getImportConfigAccess().getHeaderValuesGroupTypeParserRuleCall_5_3_1_1_0());
            	            	    											
            	            	    pushFollow(FOLLOW_12);
            	            	    lv_headerValues_27_0=ruleGroupType();

            	            	    state._fsp--;


            	            	    												if (current==null) {
            	            	    													current = createModelElementForParent(grammarAccess.getImportConfigRule());
            	            	    												}
            	            	    												add(
            	            	    													current,
            	            	    													"headerValues",
            	            	    													lv_headerValues_27_0,
            	            	    													"com.sap.xsk.models.hdbti.Hdbti.GroupType");
            	            	    												afterParserOrEnumRuleCall();
            	            	    											

            	            	    }


            	            	    }


            	            	    }
            	            	    break;

            	            	default :
            	            	    break loop1;
            	                }
            	            } while (true);


            	            }
            	            break;

            	    }

            	    otherlv_28=(Token)match(input,25,FOLLOW_7); 

            	    								newLeafNode(otherlv_28, grammarAccess.getImportConfigAccess().getRightSquareBracketKeyword_5_4());
            	    							
            	    otherlv_29=(Token)match(input,17,FOLLOW_8); 

            	    								newLeafNode(otherlv_29, grammarAccess.getImportConfigAccess().getSemicolonKeyword_5_5());
            	    							

            	    }


            	    }

            	     
            	    					getUnorderedGroupHelper().returnFromSelection(grammarAccess.getImportConfigAccess().getUnorderedGroup());
            	    				

            	    }


            	    }


            	    }
            	    break;
            	case 7 :
            	    // InternalHdbti.g:512:3: ({...}? => ( ({...}? => ( ( (lv_delimEnclosing_30_0= 'delimEnclosing' ) ) otherlv_31= '=' ( (lv_delimEnclosingValue_32_0= RULE_STRING ) ) otherlv_33= ';' ) ) ) )
            	    {
            	    // InternalHdbti.g:512:3: ({...}? => ( ({...}? => ( ( (lv_delimEnclosing_30_0= 'delimEnclosing' ) ) otherlv_31= '=' ( (lv_delimEnclosingValue_32_0= RULE_STRING ) ) otherlv_33= ';' ) ) ) )
            	    // InternalHdbti.g:513:4: {...}? => ( ({...}? => ( ( (lv_delimEnclosing_30_0= 'delimEnclosing' ) ) otherlv_31= '=' ( (lv_delimEnclosingValue_32_0= RULE_STRING ) ) otherlv_33= ';' ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 6) ) {
            	        throw new FailedPredicateException(input, "ruleImportConfig", "getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 6)");
            	    }
            	    // InternalHdbti.g:513:106: ( ({...}? => ( ( (lv_delimEnclosing_30_0= 'delimEnclosing' ) ) otherlv_31= '=' ( (lv_delimEnclosingValue_32_0= RULE_STRING ) ) otherlv_33= ';' ) ) )
            	    // InternalHdbti.g:514:5: ({...}? => ( ( (lv_delimEnclosing_30_0= 'delimEnclosing' ) ) otherlv_31= '=' ( (lv_delimEnclosingValue_32_0= RULE_STRING ) ) otherlv_33= ';' ) )
            	    {

            	    					getUnorderedGroupHelper().select(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 6);
            	    				
            	    // InternalHdbti.g:517:8: ({...}? => ( ( (lv_delimEnclosing_30_0= 'delimEnclosing' ) ) otherlv_31= '=' ( (lv_delimEnclosingValue_32_0= RULE_STRING ) ) otherlv_33= ';' ) )
            	    // InternalHdbti.g:517:9: {...}? => ( ( (lv_delimEnclosing_30_0= 'delimEnclosing' ) ) otherlv_31= '=' ( (lv_delimEnclosingValue_32_0= RULE_STRING ) ) otherlv_33= ';' )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleImportConfig", "true");
            	    }
            	    // InternalHdbti.g:517:18: ( ( (lv_delimEnclosing_30_0= 'delimEnclosing' ) ) otherlv_31= '=' ( (lv_delimEnclosingValue_32_0= RULE_STRING ) ) otherlv_33= ';' )
            	    // InternalHdbti.g:517:19: ( (lv_delimEnclosing_30_0= 'delimEnclosing' ) ) otherlv_31= '=' ( (lv_delimEnclosingValue_32_0= RULE_STRING ) ) otherlv_33= ';'
            	    {
            	    // InternalHdbti.g:517:19: ( (lv_delimEnclosing_30_0= 'delimEnclosing' ) )
            	    // InternalHdbti.g:518:9: (lv_delimEnclosing_30_0= 'delimEnclosing' )
            	    {
            	    // InternalHdbti.g:518:9: (lv_delimEnclosing_30_0= 'delimEnclosing' )
            	    // InternalHdbti.g:519:10: lv_delimEnclosing_30_0= 'delimEnclosing'
            	    {
            	    lv_delimEnclosing_30_0=(Token)match(input,26,FOLLOW_6); 

            	    										newLeafNode(lv_delimEnclosing_30_0, grammarAccess.getImportConfigAccess().getDelimEnclosingDelimEnclosingKeyword_6_0_0());
            	    									

            	    										if (current==null) {
            	    											current = createModelElement(grammarAccess.getImportConfigRule());
            	    										}
            	    										setWithLastConsumed(current, "delimEnclosing", true, "delimEnclosing");
            	    									

            	    }


            	    }

            	    otherlv_31=(Token)match(input,16,FOLLOW_4); 

            	    								newLeafNode(otherlv_31, grammarAccess.getImportConfigAccess().getEqualsSignKeyword_6_1());
            	    							
            	    // InternalHdbti.g:535:8: ( (lv_delimEnclosingValue_32_0= RULE_STRING ) )
            	    // InternalHdbti.g:536:9: (lv_delimEnclosingValue_32_0= RULE_STRING )
            	    {
            	    // InternalHdbti.g:536:9: (lv_delimEnclosingValue_32_0= RULE_STRING )
            	    // InternalHdbti.g:537:10: lv_delimEnclosingValue_32_0= RULE_STRING
            	    {
            	    lv_delimEnclosingValue_32_0=(Token)match(input,RULE_STRING,FOLLOW_7); 

            	    										newLeafNode(lv_delimEnclosingValue_32_0, grammarAccess.getImportConfigAccess().getDelimEnclosingValueSTRINGTerminalRuleCall_6_2_0());
            	    									

            	    										if (current==null) {
            	    											current = createModelElement(grammarAccess.getImportConfigRule());
            	    										}
            	    										setWithLastConsumed(
            	    											current,
            	    											"delimEnclosingValue",
            	    											lv_delimEnclosingValue_32_0,
            	    											"org.eclipse.xtext.common.Terminals.STRING");
            	    									

            	    }


            	    }

            	    otherlv_33=(Token)match(input,17,FOLLOW_8); 

            	    								newLeafNode(otherlv_33, grammarAccess.getImportConfigAccess().getSemicolonKeyword_6_3());
            	    							

            	    }


            	    }

            	     
            	    					getUnorderedGroupHelper().returnFromSelection(grammarAccess.getImportConfigAccess().getUnorderedGroup());
            	    				

            	    }


            	    }


            	    }
            	    break;
            	case 8 :
            	    // InternalHdbti.g:563:3: ({...}? => ( ({...}? => ( ( (lv_distinguishEmptyFromNull_34_0= 'delimEnclosing' ) ) otherlv_35= '=' ( (lv_distinguishEmptyFromNullValue_36_0= RULE_BOOL ) ) otherlv_37= ';' ) ) ) )
            	    {
            	    // InternalHdbti.g:563:3: ({...}? => ( ({...}? => ( ( (lv_distinguishEmptyFromNull_34_0= 'delimEnclosing' ) ) otherlv_35= '=' ( (lv_distinguishEmptyFromNullValue_36_0= RULE_BOOL ) ) otherlv_37= ';' ) ) ) )
            	    // InternalHdbti.g:564:4: {...}? => ( ({...}? => ( ( (lv_distinguishEmptyFromNull_34_0= 'delimEnclosing' ) ) otherlv_35= '=' ( (lv_distinguishEmptyFromNullValue_36_0= RULE_BOOL ) ) otherlv_37= ';' ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 7) ) {
            	        throw new FailedPredicateException(input, "ruleImportConfig", "getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 7)");
            	    }
            	    // InternalHdbti.g:564:106: ( ({...}? => ( ( (lv_distinguishEmptyFromNull_34_0= 'delimEnclosing' ) ) otherlv_35= '=' ( (lv_distinguishEmptyFromNullValue_36_0= RULE_BOOL ) ) otherlv_37= ';' ) ) )
            	    // InternalHdbti.g:565:5: ({...}? => ( ( (lv_distinguishEmptyFromNull_34_0= 'delimEnclosing' ) ) otherlv_35= '=' ( (lv_distinguishEmptyFromNullValue_36_0= RULE_BOOL ) ) otherlv_37= ';' ) )
            	    {

            	    					getUnorderedGroupHelper().select(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 7);
            	    				
            	    // InternalHdbti.g:568:8: ({...}? => ( ( (lv_distinguishEmptyFromNull_34_0= 'delimEnclosing' ) ) otherlv_35= '=' ( (lv_distinguishEmptyFromNullValue_36_0= RULE_BOOL ) ) otherlv_37= ';' ) )
            	    // InternalHdbti.g:568:9: {...}? => ( ( (lv_distinguishEmptyFromNull_34_0= 'delimEnclosing' ) ) otherlv_35= '=' ( (lv_distinguishEmptyFromNullValue_36_0= RULE_BOOL ) ) otherlv_37= ';' )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleImportConfig", "true");
            	    }
            	    // InternalHdbti.g:568:18: ( ( (lv_distinguishEmptyFromNull_34_0= 'delimEnclosing' ) ) otherlv_35= '=' ( (lv_distinguishEmptyFromNullValue_36_0= RULE_BOOL ) ) otherlv_37= ';' )
            	    // InternalHdbti.g:568:19: ( (lv_distinguishEmptyFromNull_34_0= 'delimEnclosing' ) ) otherlv_35= '=' ( (lv_distinguishEmptyFromNullValue_36_0= RULE_BOOL ) ) otherlv_37= ';'
            	    {
            	    // InternalHdbti.g:568:19: ( (lv_distinguishEmptyFromNull_34_0= 'delimEnclosing' ) )
            	    // InternalHdbti.g:569:9: (lv_distinguishEmptyFromNull_34_0= 'delimEnclosing' )
            	    {
            	    // InternalHdbti.g:569:9: (lv_distinguishEmptyFromNull_34_0= 'delimEnclosing' )
            	    // InternalHdbti.g:570:10: lv_distinguishEmptyFromNull_34_0= 'delimEnclosing'
            	    {
            	    lv_distinguishEmptyFromNull_34_0=(Token)match(input,26,FOLLOW_6); 

            	    										newLeafNode(lv_distinguishEmptyFromNull_34_0, grammarAccess.getImportConfigAccess().getDistinguishEmptyFromNullDelimEnclosingKeyword_7_0_0());
            	    									

            	    										if (current==null) {
            	    											current = createModelElement(grammarAccess.getImportConfigRule());
            	    										}
            	    										setWithLastConsumed(current, "distinguishEmptyFromNull", true, "delimEnclosing");
            	    									

            	    }


            	    }

            	    otherlv_35=(Token)match(input,16,FOLLOW_9); 

            	    								newLeafNode(otherlv_35, grammarAccess.getImportConfigAccess().getEqualsSignKeyword_7_1());
            	    							
            	    // InternalHdbti.g:586:8: ( (lv_distinguishEmptyFromNullValue_36_0= RULE_BOOL ) )
            	    // InternalHdbti.g:587:9: (lv_distinguishEmptyFromNullValue_36_0= RULE_BOOL )
            	    {
            	    // InternalHdbti.g:587:9: (lv_distinguishEmptyFromNullValue_36_0= RULE_BOOL )
            	    // InternalHdbti.g:588:10: lv_distinguishEmptyFromNullValue_36_0= RULE_BOOL
            	    {
            	    lv_distinguishEmptyFromNullValue_36_0=(Token)match(input,RULE_BOOL,FOLLOW_7); 

            	    										newLeafNode(lv_distinguishEmptyFromNullValue_36_0, grammarAccess.getImportConfigAccess().getDistinguishEmptyFromNullValueBOOLTerminalRuleCall_7_2_0());
            	    									

            	    										if (current==null) {
            	    											current = createModelElement(grammarAccess.getImportConfigRule());
            	    										}
            	    										setWithLastConsumed(
            	    											current,
            	    											"distinguishEmptyFromNullValue",
            	    											lv_distinguishEmptyFromNullValue_36_0,
            	    											"com.sap.xsk.models.hdbti.Hdbti.BOOL");
            	    									

            	    }


            	    }

            	    otherlv_37=(Token)match(input,17,FOLLOW_8); 

            	    								newLeafNode(otherlv_37, grammarAccess.getImportConfigAccess().getSemicolonKeyword_7_3());
            	    							

            	    }


            	    }

            	     
            	    					getUnorderedGroupHelper().returnFromSelection(grammarAccess.getImportConfigAccess().getUnorderedGroup());
            	    				

            	    }


            	    }


            	    }
            	    break;
            	case 9 :
            	    // InternalHdbti.g:614:3: ({...}? => ( ({...}? => ( ( ( (lv_useHeaderNames_38_0= 'delimEnclosing' ) ) otherlv_39= '=' ( (lv_useHeaderNamesValue_40_0= RULE_BOOL ) ) otherlv_41= ';' )? otherlv_42= '}' ) ) ) )
            	    {
            	    // InternalHdbti.g:614:3: ({...}? => ( ({...}? => ( ( ( (lv_useHeaderNames_38_0= 'delimEnclosing' ) ) otherlv_39= '=' ( (lv_useHeaderNamesValue_40_0= RULE_BOOL ) ) otherlv_41= ';' )? otherlv_42= '}' ) ) ) )
            	    // InternalHdbti.g:615:4: {...}? => ( ({...}? => ( ( ( (lv_useHeaderNames_38_0= 'delimEnclosing' ) ) otherlv_39= '=' ( (lv_useHeaderNamesValue_40_0= RULE_BOOL ) ) otherlv_41= ';' )? otherlv_42= '}' ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 8) ) {
            	        throw new FailedPredicateException(input, "ruleImportConfig", "getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 8)");
            	    }
            	    // InternalHdbti.g:615:106: ( ({...}? => ( ( ( (lv_useHeaderNames_38_0= 'delimEnclosing' ) ) otherlv_39= '=' ( (lv_useHeaderNamesValue_40_0= RULE_BOOL ) ) otherlv_41= ';' )? otherlv_42= '}' ) ) )
            	    // InternalHdbti.g:616:5: ({...}? => ( ( ( (lv_useHeaderNames_38_0= 'delimEnclosing' ) ) otherlv_39= '=' ( (lv_useHeaderNamesValue_40_0= RULE_BOOL ) ) otherlv_41= ';' )? otherlv_42= '}' ) )
            	    {

            	    					getUnorderedGroupHelper().select(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 8);
            	    				
            	    // InternalHdbti.g:619:8: ({...}? => ( ( ( (lv_useHeaderNames_38_0= 'delimEnclosing' ) ) otherlv_39= '=' ( (lv_useHeaderNamesValue_40_0= RULE_BOOL ) ) otherlv_41= ';' )? otherlv_42= '}' ) )
            	    // InternalHdbti.g:619:9: {...}? => ( ( ( (lv_useHeaderNames_38_0= 'delimEnclosing' ) ) otherlv_39= '=' ( (lv_useHeaderNamesValue_40_0= RULE_BOOL ) ) otherlv_41= ';' )? otherlv_42= '}' )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleImportConfig", "true");
            	    }
            	    // InternalHdbti.g:619:18: ( ( ( (lv_useHeaderNames_38_0= 'delimEnclosing' ) ) otherlv_39= '=' ( (lv_useHeaderNamesValue_40_0= RULE_BOOL ) ) otherlv_41= ';' )? otherlv_42= '}' )
            	    // InternalHdbti.g:619:19: ( ( (lv_useHeaderNames_38_0= 'delimEnclosing' ) ) otherlv_39= '=' ( (lv_useHeaderNamesValue_40_0= RULE_BOOL ) ) otherlv_41= ';' )? otherlv_42= '}'
            	    {
            	    // InternalHdbti.g:619:19: ( ( (lv_useHeaderNames_38_0= 'delimEnclosing' ) ) otherlv_39= '=' ( (lv_useHeaderNamesValue_40_0= RULE_BOOL ) ) otherlv_41= ';' )?
            	    int alt3=2;
            	    int LA3_0 = input.LA(1);

            	    if ( (LA3_0==26) ) {
            	        alt3=1;
            	    }
            	    switch (alt3) {
            	        case 1 :
            	            // InternalHdbti.g:620:9: ( (lv_useHeaderNames_38_0= 'delimEnclosing' ) ) otherlv_39= '=' ( (lv_useHeaderNamesValue_40_0= RULE_BOOL ) ) otherlv_41= ';'
            	            {
            	            // InternalHdbti.g:620:9: ( (lv_useHeaderNames_38_0= 'delimEnclosing' ) )
            	            // InternalHdbti.g:621:10: (lv_useHeaderNames_38_0= 'delimEnclosing' )
            	            {
            	            // InternalHdbti.g:621:10: (lv_useHeaderNames_38_0= 'delimEnclosing' )
            	            // InternalHdbti.g:622:11: lv_useHeaderNames_38_0= 'delimEnclosing'
            	            {
            	            lv_useHeaderNames_38_0=(Token)match(input,26,FOLLOW_6); 

            	            											newLeafNode(lv_useHeaderNames_38_0, grammarAccess.getImportConfigAccess().getUseHeaderNamesDelimEnclosingKeyword_8_0_0_0());
            	            										

            	            											if (current==null) {
            	            												current = createModelElement(grammarAccess.getImportConfigRule());
            	            											}
            	            											setWithLastConsumed(current, "useHeaderNames", true, "delimEnclosing");
            	            										

            	            }


            	            }

            	            otherlv_39=(Token)match(input,16,FOLLOW_9); 

            	            									newLeafNode(otherlv_39, grammarAccess.getImportConfigAccess().getEqualsSignKeyword_8_0_1());
            	            								
            	            // InternalHdbti.g:638:9: ( (lv_useHeaderNamesValue_40_0= RULE_BOOL ) )
            	            // InternalHdbti.g:639:10: (lv_useHeaderNamesValue_40_0= RULE_BOOL )
            	            {
            	            // InternalHdbti.g:639:10: (lv_useHeaderNamesValue_40_0= RULE_BOOL )
            	            // InternalHdbti.g:640:11: lv_useHeaderNamesValue_40_0= RULE_BOOL
            	            {
            	            lv_useHeaderNamesValue_40_0=(Token)match(input,RULE_BOOL,FOLLOW_7); 

            	            											newLeafNode(lv_useHeaderNamesValue_40_0, grammarAccess.getImportConfigAccess().getUseHeaderNamesValueBOOLTerminalRuleCall_8_0_2_0());
            	            										

            	            											if (current==null) {
            	            												current = createModelElement(grammarAccess.getImportConfigRule());
            	            											}
            	            											setWithLastConsumed(
            	            												current,
            	            												"useHeaderNamesValue",
            	            												lv_useHeaderNamesValue_40_0,
            	            												"com.sap.xsk.models.hdbti.Hdbti.BOOL");
            	            										

            	            }


            	            }

            	            otherlv_41=(Token)match(input,17,FOLLOW_14); 

            	            									newLeafNode(otherlv_41, grammarAccess.getImportConfigAccess().getSemicolonKeyword_8_0_3());
            	            								

            	            }
            	            break;

            	    }

            	    otherlv_42=(Token)match(input,27,FOLLOW_8); 

            	    								newLeafNode(otherlv_42, grammarAccess.getImportConfigAccess().getRightCurlyBracketKeyword_8_1());
            	    							

            	    }


            	    }

            	     
            	    					getUnorderedGroupHelper().returnFromSelection(grammarAccess.getImportConfigAccess().getUnorderedGroup());
            	    				

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt4 >= 1 ) break loop4;
                        EarlyExitException eee =
                            new EarlyExitException(4, input);
                        throw eee;
                }
                cnt4++;
            } while (true);

            if ( ! getUnorderedGroupHelper().canLeave(grammarAccess.getImportConfigAccess().getUnorderedGroup()) ) {
                throw new FailedPredicateException(input, "ruleImportConfig", "getUnorderedGroupHelper().canLeave(grammarAccess.getImportConfigAccess().getUnorderedGroup())");
            }

            }


            }

             
            			  getUnorderedGroupHelper().leave(grammarAccess.getImportConfigAccess().getUnorderedGroup());
            			

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleImportConfig"


    // $ANTLR start "entryRuleImport"
    // InternalHdbti.g:682:1: entryRuleImport returns [EObject current=null] : iv_ruleImport= ruleImport EOF ;
    public final EObject entryRuleImport() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleImport = null;


        try {
            // InternalHdbti.g:682:47: (iv_ruleImport= ruleImport EOF )
            // InternalHdbti.g:683:2: iv_ruleImport= ruleImport EOF
            {
             newCompositeNode(grammarAccess.getImportRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleImport=ruleImport();

            state._fsp--;

             current =iv_ruleImport; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleImport"


    // $ANTLR start "ruleImport"
    // InternalHdbti.g:689:1: ruleImport returns [EObject current=null] : (otherlv_0= 'import' otherlv_1= '=' otherlv_2= '[' ( ( (lv_values_3_0= ruleImportConfig ) ) (otherlv_4= ',' ( (lv_values_5_0= ruleImportConfig ) ) )* )? otherlv_6= ']' otherlv_7= ';' ) ;
    public final EObject ruleImport() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_7=null;
        EObject lv_values_3_0 = null;

        EObject lv_values_5_0 = null;



        	enterRule();

        try {
            // InternalHdbti.g:695:2: ( (otherlv_0= 'import' otherlv_1= '=' otherlv_2= '[' ( ( (lv_values_3_0= ruleImportConfig ) ) (otherlv_4= ',' ( (lv_values_5_0= ruleImportConfig ) ) )* )? otherlv_6= ']' otherlv_7= ';' ) )
            // InternalHdbti.g:696:2: (otherlv_0= 'import' otherlv_1= '=' otherlv_2= '[' ( ( (lv_values_3_0= ruleImportConfig ) ) (otherlv_4= ',' ( (lv_values_5_0= ruleImportConfig ) ) )* )? otherlv_6= ']' otherlv_7= ';' )
            {
            // InternalHdbti.g:696:2: (otherlv_0= 'import' otherlv_1= '=' otherlv_2= '[' ( ( (lv_values_3_0= ruleImportConfig ) ) (otherlv_4= ',' ( (lv_values_5_0= ruleImportConfig ) ) )* )? otherlv_6= ']' otherlv_7= ';' )
            // InternalHdbti.g:697:3: otherlv_0= 'import' otherlv_1= '=' otherlv_2= '[' ( ( (lv_values_3_0= ruleImportConfig ) ) (otherlv_4= ',' ( (lv_values_5_0= ruleImportConfig ) ) )* )? otherlv_6= ']' otherlv_7= ';'
            {
            otherlv_0=(Token)match(input,28,FOLLOW_6); 

            			newLeafNode(otherlv_0, grammarAccess.getImportAccess().getImportKeyword_0());
            		
            otherlv_1=(Token)match(input,16,FOLLOW_10); 

            			newLeafNode(otherlv_1, grammarAccess.getImportAccess().getEqualsSignKeyword_1());
            		
            otherlv_2=(Token)match(input,23,FOLLOW_15); 

            			newLeafNode(otherlv_2, grammarAccess.getImportAccess().getLeftSquareBracketKeyword_2());
            		
            // InternalHdbti.g:709:3: ( ( (lv_values_3_0= ruleImportConfig ) ) (otherlv_4= ',' ( (lv_values_5_0= ruleImportConfig ) ) )* )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==14||(LA6_0>=18 && LA6_0<=22)||(LA6_0>=26 && LA6_0<=27)) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // InternalHdbti.g:710:4: ( (lv_values_3_0= ruleImportConfig ) ) (otherlv_4= ',' ( (lv_values_5_0= ruleImportConfig ) ) )*
                    {
                    // InternalHdbti.g:710:4: ( (lv_values_3_0= ruleImportConfig ) )
                    // InternalHdbti.g:711:5: (lv_values_3_0= ruleImportConfig )
                    {
                    // InternalHdbti.g:711:5: (lv_values_3_0= ruleImportConfig )
                    // InternalHdbti.g:712:6: lv_values_3_0= ruleImportConfig
                    {

                    						newCompositeNode(grammarAccess.getImportAccess().getValuesImportConfigParserRuleCall_3_0_0());
                    					
                    pushFollow(FOLLOW_12);
                    lv_values_3_0=ruleImportConfig();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getImportRule());
                    						}
                    						add(
                    							current,
                    							"values",
                    							lv_values_3_0,
                    							"com.sap.xsk.models.hdbti.Hdbti.ImportConfig");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalHdbti.g:729:4: (otherlv_4= ',' ( (lv_values_5_0= ruleImportConfig ) ) )*
                    loop5:
                    do {
                        int alt5=2;
                        int LA5_0 = input.LA(1);

                        if ( (LA5_0==24) ) {
                            alt5=1;
                        }


                        switch (alt5) {
                    	case 1 :
                    	    // InternalHdbti.g:730:5: otherlv_4= ',' ( (lv_values_5_0= ruleImportConfig ) )
                    	    {
                    	    otherlv_4=(Token)match(input,24,FOLLOW_16); 

                    	    					newLeafNode(otherlv_4, grammarAccess.getImportAccess().getCommaKeyword_3_1_0());
                    	    				
                    	    // InternalHdbti.g:734:5: ( (lv_values_5_0= ruleImportConfig ) )
                    	    // InternalHdbti.g:735:6: (lv_values_5_0= ruleImportConfig )
                    	    {
                    	    // InternalHdbti.g:735:6: (lv_values_5_0= ruleImportConfig )
                    	    // InternalHdbti.g:736:7: lv_values_5_0= ruleImportConfig
                    	    {

                    	    							newCompositeNode(grammarAccess.getImportAccess().getValuesImportConfigParserRuleCall_3_1_1_0());
                    	    						
                    	    pushFollow(FOLLOW_12);
                    	    lv_values_5_0=ruleImportConfig();

                    	    state._fsp--;


                    	    							if (current==null) {
                    	    								current = createModelElementForParent(grammarAccess.getImportRule());
                    	    							}
                    	    							add(
                    	    								current,
                    	    								"values",
                    	    								lv_values_5_0,
                    	    								"com.sap.xsk.models.hdbti.Hdbti.ImportConfig");
                    	    							afterParserOrEnumRuleCall();
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop5;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_6=(Token)match(input,25,FOLLOW_7); 

            			newLeafNode(otherlv_6, grammarAccess.getImportAccess().getRightSquareBracketKeyword_4());
            		
            otherlv_7=(Token)match(input,17,FOLLOW_2); 

            			newLeafNode(otherlv_7, grammarAccess.getImportAccess().getSemicolonKeyword_5());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleImport"

    // Delegated rules


    protected DFA4 dfa4 = new DFA4(this);
    static final String dfa_1s = "\17\uffff";
    static final String dfa_2s = "\1\1\16\uffff";
    static final String dfa_3s = "\1\16\7\uffff\1\20\1\uffff\1\4\1\21\1\uffff\1\0\1\uffff";
    static final String dfa_4s = "\1\33\7\uffff\1\20\1\uffff\1\5\1\21\1\uffff\1\0\1\uffff";
    static final String dfa_5s = "\1\uffff\1\12\1\1\1\2\1\3\1\4\1\5\1\6\1\uffff\1\11\2\uffff\1\7\1\uffff\1\10";
    static final String dfa_6s = "\1\1\7\uffff\1\0\1\uffff\1\2\1\3\1\uffff\1\4\1\uffff}>";
    static final String[] dfa_7s = {
            "\1\2\3\uffff\1\3\1\4\1\5\1\6\1\7\1\uffff\2\1\1\10\1\11",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\12",
            "",
            "\1\14\1\13",
            "\1\15",
            "",
            "\1\uffff",
            ""
    };

    static final short[] dfa_1 = DFA.unpackEncodedString(dfa_1s);
    static final short[] dfa_2 = DFA.unpackEncodedString(dfa_2s);
    static final char[] dfa_3 = DFA.unpackEncodedStringToUnsignedChars(dfa_3s);
    static final char[] dfa_4 = DFA.unpackEncodedStringToUnsignedChars(dfa_4s);
    static final short[] dfa_5 = DFA.unpackEncodedString(dfa_5s);
    static final short[] dfa_6 = DFA.unpackEncodedString(dfa_6s);
    static final short[][] dfa_7 = unpackEncodedStringArray(dfa_7s);

    class DFA4 extends DFA {

        public DFA4(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 4;
            this.eot = dfa_1;
            this.eof = dfa_2;
            this.min = dfa_3;
            this.max = dfa_4;
            this.accept = dfa_5;
            this.special = dfa_6;
            this.transition = dfa_7;
        }
        public String getDescription() {
            return "()+ loopback of 165:5: ( ({...}? => ( ({...}? => (otherlv_1= '{' ( (lv_table_2_0= 'table' ) ) otherlv_3= '=' ( (lv_tableValue_4_0= RULE_STRING ) ) otherlv_5= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_schema_6_0= 'schema' ) ) otherlv_7= '=' ( (lv_schemaValue_8_0= RULE_STRING ) ) otherlv_9= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_file_10_0= 'file' ) ) otherlv_11= '=' ( (lv_fileValue_12_0= RULE_STRING ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_delimField_14_0= 'delimField' ) ) otherlv_15= '=' ( (lv_delimFieldValue_16_0= RULE_STRING ) ) otherlv_17= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_header_18_0= 'header' ) ) otherlv_19= '=' ( (lv_headerValue_20_0= RULE_BOOL ) ) otherlv_21= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_keys_22_0= 'keys' ) ) otherlv_23= '=' otherlv_24= '[' ( ( (lv_headerValues_25_0= ruleGroupType ) ) (otherlv_26= ',' ( (lv_headerValues_27_0= ruleGroupType ) ) )* )? otherlv_28= ']' otherlv_29= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_delimEnclosing_30_0= 'delimEnclosing' ) ) otherlv_31= '=' ( (lv_delimEnclosingValue_32_0= RULE_STRING ) ) otherlv_33= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_distinguishEmptyFromNull_34_0= 'delimEnclosing' ) ) otherlv_35= '=' ( (lv_distinguishEmptyFromNullValue_36_0= RULE_BOOL ) ) otherlv_37= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( ( (lv_useHeaderNames_38_0= 'delimEnclosing' ) ) otherlv_39= '=' ( (lv_useHeaderNamesValue_40_0= RULE_BOOL ) ) otherlv_41= ';' )? otherlv_42= '}' ) ) ) ) )+";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA4_8 = input.LA(1);

                         
                        int index4_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( LA4_8 == 16 && ( getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 6) || getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 8) || getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 7) ) ) {s = 10;}

                         
                        input.seek(index4_8);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA4_0 = input.LA(1);

                         
                        int index4_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA4_0==EOF||(LA4_0>=24 && LA4_0<=25)) ) {s = 1;}

                        else if ( LA4_0 == 14 && getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 0) ) {s = 2;}

                        else if ( LA4_0 == 18 && getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 1) ) {s = 3;}

                        else if ( LA4_0 == 19 && getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 2) ) {s = 4;}

                        else if ( LA4_0 == 20 && getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 3) ) {s = 5;}

                        else if ( LA4_0 == 21 && getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 4) ) {s = 6;}

                        else if ( LA4_0 == 22 && getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 5) ) {s = 7;}

                        else if ( LA4_0 == 26 && ( getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 6) || getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 8) || getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 7) ) ) {s = 8;}

                        else if ( LA4_0 == 27 && getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 8) ) {s = 9;}

                         
                        input.seek(index4_0);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA4_10 = input.LA(1);

                         
                        int index4_10 = input.index();
                        input.rewind();
                        s = -1;
                        if ( LA4_10 == RULE_BOOL && ( getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 8) || getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 7) ) ) {s = 11;}

                        else if ( LA4_10 == RULE_STRING && getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 6) ) {s = 12;}

                         
                        input.seek(index4_10);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA4_11 = input.LA(1);

                         
                        int index4_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( LA4_11 == 17 && ( getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 8) || getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 7) ) ) {s = 13;}

                         
                        input.seek(index4_11);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA4_13 = input.LA(1);

                         
                        int index4_13 = input.index();
                        input.rewind();
                        s = -1;
                        if ( getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 7) ) {s = 14;}

                        else if ( getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 8) ) {s = 9;}

                         
                        input.seek(index4_13);
                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 4, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x000000000C7C4002L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000002001000L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000003000000L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x000000000E7C4000L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x000000000C7C4000L});

}
