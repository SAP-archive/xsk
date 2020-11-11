package com.sap.xsk.models.hdbview.parser.antlr.internal;

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import com.sap.xsk.models.hdbview.services.HdbViewGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalHdbViewParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_STRING", "RULE_BOOL", "RULE_ID", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'schema'", "'='", "';'", "'query'", "'depends_on'", "'['", "','", "']'"
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
    public static final int RULE_INT=7;
    public static final int RULE_ML_COMMENT=8;

    // delegates
    // delegators


        public InternalHdbViewParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalHdbViewParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalHdbViewParser.tokenNames; }
    public String getGrammarFileName() { return "InternalHdbView.g"; }



     	private HdbViewGrammarAccess grammarAccess;

        public InternalHdbViewParser(TokenStream input, HdbViewGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }

        @Override
        protected String getFirstRuleName() {
        	return "HdbViewModel";
       	}

       	@Override
       	protected HdbViewGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}




    // $ANTLR start "entryRuleHdbViewModel"
    // InternalHdbView.g:64:1: entryRuleHdbViewModel returns [EObject current=null] : iv_ruleHdbViewModel= ruleHdbViewModel EOF ;
    public final EObject entryRuleHdbViewModel() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleHdbViewModel = null;


        try {
            // InternalHdbView.g:64:53: (iv_ruleHdbViewModel= ruleHdbViewModel EOF )
            // InternalHdbView.g:65:2: iv_ruleHdbViewModel= ruleHdbViewModel EOF
            {
             newCompositeNode(grammarAccess.getHdbViewModelRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleHdbViewModel=ruleHdbViewModel();

            state._fsp--;

             current =iv_ruleHdbViewModel; 
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
    // $ANTLR end "entryRuleHdbViewModel"


    // $ANTLR start "ruleHdbViewModel"
    // InternalHdbView.g:71:1: ruleHdbViewModel returns [EObject current=null] : ( (lv_viewElement_0_0= ruleView ) ) ;
    public final EObject ruleHdbViewModel() throws RecognitionException {
        EObject current = null;

        EObject lv_viewElement_0_0 = null;



        	enterRule();

        try {
            // InternalHdbView.g:77:2: ( ( (lv_viewElement_0_0= ruleView ) ) )
            // InternalHdbView.g:78:2: ( (lv_viewElement_0_0= ruleView ) )
            {
            // InternalHdbView.g:78:2: ( (lv_viewElement_0_0= ruleView ) )
            // InternalHdbView.g:79:3: (lv_viewElement_0_0= ruleView )
            {
            // InternalHdbView.g:79:3: (lv_viewElement_0_0= ruleView )
            // InternalHdbView.g:80:4: lv_viewElement_0_0= ruleView
            {

            				newCompositeNode(grammarAccess.getHdbViewModelAccess().getViewElementViewParserRuleCall_0());
            			
            pushFollow(FOLLOW_2);
            lv_viewElement_0_0=ruleView();

            state._fsp--;


            				if (current==null) {
            					current = createModelElementForParent(grammarAccess.getHdbViewModelRule());
            				}
            				set(
            					current,
            					"viewElement",
            					lv_viewElement_0_0,
            					"com.sap.xsk.models.hdbview.HdbView.View");
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
    // $ANTLR end "ruleHdbViewModel"


    // $ANTLR start "entryRuleView"
    // InternalHdbView.g:100:1: entryRuleView returns [EObject current=null] : iv_ruleView= ruleView EOF ;
    public final EObject entryRuleView() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleView = null;


        try {
            // InternalHdbView.g:100:45: (iv_ruleView= ruleView EOF )
            // InternalHdbView.g:101:2: iv_ruleView= ruleView EOF
            {
             newCompositeNode(grammarAccess.getViewRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleView=ruleView();

            state._fsp--;

             current =iv_ruleView; 
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
    // $ANTLR end "entryRuleView"


    // $ANTLR start "ruleView"
    // InternalHdbView.g:107:1: ruleView returns [EObject current=null] : ( ( ( ( ({...}? => ( ({...}? => ( ( (lv_schema_1_0= 'schema' ) ) otherlv_2= '=' ( (lv_schemaName_3_0= RULE_STRING ) ) otherlv_4= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_query_5_0= 'query' ) ) otherlv_6= '=' ( (lv_queryValue_7_0= RULE_STRING ) ) otherlv_8= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_dependsOn_9_0= 'depends_on' ) ) otherlv_10= '=' otherlv_11= '[' ( ( (lv_dependsOnValues_12_0= RULE_STRING ) ) (otherlv_13= ',' ( (lv_dependsOnValues_14_0= RULE_STRING ) ) )* )? otherlv_15= ']' otherlv_16= ';' ) ) ) ) )+ {...}?) ) ) ;
    public final EObject ruleView() throws RecognitionException {
        EObject current = null;

        Token lv_schema_1_0=null;
        Token otherlv_2=null;
        Token lv_schemaName_3_0=null;
        Token otherlv_4=null;
        Token lv_query_5_0=null;
        Token otherlv_6=null;
        Token lv_queryValue_7_0=null;
        Token otherlv_8=null;
        Token lv_dependsOn_9_0=null;
        Token otherlv_10=null;
        Token otherlv_11=null;
        Token lv_dependsOnValues_12_0=null;
        Token otherlv_13=null;
        Token lv_dependsOnValues_14_0=null;
        Token otherlv_15=null;
        Token otherlv_16=null;


        	enterRule();

        try {
            // InternalHdbView.g:113:2: ( ( ( ( ( ({...}? => ( ({...}? => ( ( (lv_schema_1_0= 'schema' ) ) otherlv_2= '=' ( (lv_schemaName_3_0= RULE_STRING ) ) otherlv_4= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_query_5_0= 'query' ) ) otherlv_6= '=' ( (lv_queryValue_7_0= RULE_STRING ) ) otherlv_8= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_dependsOn_9_0= 'depends_on' ) ) otherlv_10= '=' otherlv_11= '[' ( ( (lv_dependsOnValues_12_0= RULE_STRING ) ) (otherlv_13= ',' ( (lv_dependsOnValues_14_0= RULE_STRING ) ) )* )? otherlv_15= ']' otherlv_16= ';' ) ) ) ) )+ {...}?) ) ) )
            // InternalHdbView.g:114:2: ( ( ( ( ({...}? => ( ({...}? => ( ( (lv_schema_1_0= 'schema' ) ) otherlv_2= '=' ( (lv_schemaName_3_0= RULE_STRING ) ) otherlv_4= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_query_5_0= 'query' ) ) otherlv_6= '=' ( (lv_queryValue_7_0= RULE_STRING ) ) otherlv_8= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_dependsOn_9_0= 'depends_on' ) ) otherlv_10= '=' otherlv_11= '[' ( ( (lv_dependsOnValues_12_0= RULE_STRING ) ) (otherlv_13= ',' ( (lv_dependsOnValues_14_0= RULE_STRING ) ) )* )? otherlv_15= ']' otherlv_16= ';' ) ) ) ) )+ {...}?) ) )
            {
            // InternalHdbView.g:114:2: ( ( ( ( ({...}? => ( ({...}? => ( ( (lv_schema_1_0= 'schema' ) ) otherlv_2= '=' ( (lv_schemaName_3_0= RULE_STRING ) ) otherlv_4= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_query_5_0= 'query' ) ) otherlv_6= '=' ( (lv_queryValue_7_0= RULE_STRING ) ) otherlv_8= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_dependsOn_9_0= 'depends_on' ) ) otherlv_10= '=' otherlv_11= '[' ( ( (lv_dependsOnValues_12_0= RULE_STRING ) ) (otherlv_13= ',' ( (lv_dependsOnValues_14_0= RULE_STRING ) ) )* )? otherlv_15= ']' otherlv_16= ';' ) ) ) ) )+ {...}?) ) )
            // InternalHdbView.g:115:3: ( ( ( ({...}? => ( ({...}? => ( ( (lv_schema_1_0= 'schema' ) ) otherlv_2= '=' ( (lv_schemaName_3_0= RULE_STRING ) ) otherlv_4= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_query_5_0= 'query' ) ) otherlv_6= '=' ( (lv_queryValue_7_0= RULE_STRING ) ) otherlv_8= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_dependsOn_9_0= 'depends_on' ) ) otherlv_10= '=' otherlv_11= '[' ( ( (lv_dependsOnValues_12_0= RULE_STRING ) ) (otherlv_13= ',' ( (lv_dependsOnValues_14_0= RULE_STRING ) ) )* )? otherlv_15= ']' otherlv_16= ';' ) ) ) ) )+ {...}?) )
            {
            // InternalHdbView.g:115:3: ( ( ( ({...}? => ( ({...}? => ( ( (lv_schema_1_0= 'schema' ) ) otherlv_2= '=' ( (lv_schemaName_3_0= RULE_STRING ) ) otherlv_4= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_query_5_0= 'query' ) ) otherlv_6= '=' ( (lv_queryValue_7_0= RULE_STRING ) ) otherlv_8= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_dependsOn_9_0= 'depends_on' ) ) otherlv_10= '=' otherlv_11= '[' ( ( (lv_dependsOnValues_12_0= RULE_STRING ) ) (otherlv_13= ',' ( (lv_dependsOnValues_14_0= RULE_STRING ) ) )* )? otherlv_15= ']' otherlv_16= ';' ) ) ) ) )+ {...}?) )
            // InternalHdbView.g:116:4: ( ( ({...}? => ( ({...}? => ( ( (lv_schema_1_0= 'schema' ) ) otherlv_2= '=' ( (lv_schemaName_3_0= RULE_STRING ) ) otherlv_4= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_query_5_0= 'query' ) ) otherlv_6= '=' ( (lv_queryValue_7_0= RULE_STRING ) ) otherlv_8= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_dependsOn_9_0= 'depends_on' ) ) otherlv_10= '=' otherlv_11= '[' ( ( (lv_dependsOnValues_12_0= RULE_STRING ) ) (otherlv_13= ',' ( (lv_dependsOnValues_14_0= RULE_STRING ) ) )* )? otherlv_15= ']' otherlv_16= ';' ) ) ) ) )+ {...}?)
            {
             
            			  getUnorderedGroupHelper().enter(grammarAccess.getViewAccess().getUnorderedGroup());
            			
            // InternalHdbView.g:119:4: ( ( ({...}? => ( ({...}? => ( ( (lv_schema_1_0= 'schema' ) ) otherlv_2= '=' ( (lv_schemaName_3_0= RULE_STRING ) ) otherlv_4= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_query_5_0= 'query' ) ) otherlv_6= '=' ( (lv_queryValue_7_0= RULE_STRING ) ) otherlv_8= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_dependsOn_9_0= 'depends_on' ) ) otherlv_10= '=' otherlv_11= '[' ( ( (lv_dependsOnValues_12_0= RULE_STRING ) ) (otherlv_13= ',' ( (lv_dependsOnValues_14_0= RULE_STRING ) ) )* )? otherlv_15= ']' otherlv_16= ';' ) ) ) ) )+ {...}?)
            // InternalHdbView.g:120:5: ( ({...}? => ( ({...}? => ( ( (lv_schema_1_0= 'schema' ) ) otherlv_2= '=' ( (lv_schemaName_3_0= RULE_STRING ) ) otherlv_4= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_query_5_0= 'query' ) ) otherlv_6= '=' ( (lv_queryValue_7_0= RULE_STRING ) ) otherlv_8= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_dependsOn_9_0= 'depends_on' ) ) otherlv_10= '=' otherlv_11= '[' ( ( (lv_dependsOnValues_12_0= RULE_STRING ) ) (otherlv_13= ',' ( (lv_dependsOnValues_14_0= RULE_STRING ) ) )* )? otherlv_15= ']' otherlv_16= ';' ) ) ) ) )+ {...}?
            {
            // InternalHdbView.g:120:5: ( ({...}? => ( ({...}? => ( ( (lv_schema_1_0= 'schema' ) ) otherlv_2= '=' ( (lv_schemaName_3_0= RULE_STRING ) ) otherlv_4= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_query_5_0= 'query' ) ) otherlv_6= '=' ( (lv_queryValue_7_0= RULE_STRING ) ) otherlv_8= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_dependsOn_9_0= 'depends_on' ) ) otherlv_10= '=' otherlv_11= '[' ( ( (lv_dependsOnValues_12_0= RULE_STRING ) ) (otherlv_13= ',' ( (lv_dependsOnValues_14_0= RULE_STRING ) ) )* )? otherlv_15= ']' otherlv_16= ';' ) ) ) ) )+
            int cnt3=0;
            loop3:
            do {
                int alt3=4;
                int LA3_0 = input.LA(1);

                if ( LA3_0 == 12 && getUnorderedGroupHelper().canSelect(grammarAccess.getViewAccess().getUnorderedGroup(), 0) ) {
                    alt3=1;
                }
                else if ( LA3_0 == 15 && getUnorderedGroupHelper().canSelect(grammarAccess.getViewAccess().getUnorderedGroup(), 1) ) {
                    alt3=2;
                }
                else if ( LA3_0 == 16 && getUnorderedGroupHelper().canSelect(grammarAccess.getViewAccess().getUnorderedGroup(), 2) ) {
                    alt3=3;
                }


                switch (alt3) {
            	case 1 :
            	    // InternalHdbView.g:121:3: ({...}? => ( ({...}? => ( ( (lv_schema_1_0= 'schema' ) ) otherlv_2= '=' ( (lv_schemaName_3_0= RULE_STRING ) ) otherlv_4= ';' ) ) ) )
            	    {
            	    // InternalHdbView.g:121:3: ({...}? => ( ({...}? => ( ( (lv_schema_1_0= 'schema' ) ) otherlv_2= '=' ( (lv_schemaName_3_0= RULE_STRING ) ) otherlv_4= ';' ) ) ) )
            	    // InternalHdbView.g:122:4: {...}? => ( ({...}? => ( ( (lv_schema_1_0= 'schema' ) ) otherlv_2= '=' ( (lv_schemaName_3_0= RULE_STRING ) ) otherlv_4= ';' ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getViewAccess().getUnorderedGroup(), 0) ) {
            	        throw new FailedPredicateException(input, "ruleView", "getUnorderedGroupHelper().canSelect(grammarAccess.getViewAccess().getUnorderedGroup(), 0)");
            	    }
            	    // InternalHdbView.g:122:98: ( ({...}? => ( ( (lv_schema_1_0= 'schema' ) ) otherlv_2= '=' ( (lv_schemaName_3_0= RULE_STRING ) ) otherlv_4= ';' ) ) )
            	    // InternalHdbView.g:123:5: ({...}? => ( ( (lv_schema_1_0= 'schema' ) ) otherlv_2= '=' ( (lv_schemaName_3_0= RULE_STRING ) ) otherlv_4= ';' ) )
            	    {

            	    					getUnorderedGroupHelper().select(grammarAccess.getViewAccess().getUnorderedGroup(), 0);
            	    				
            	    // InternalHdbView.g:126:8: ({...}? => ( ( (lv_schema_1_0= 'schema' ) ) otherlv_2= '=' ( (lv_schemaName_3_0= RULE_STRING ) ) otherlv_4= ';' ) )
            	    // InternalHdbView.g:126:9: {...}? => ( ( (lv_schema_1_0= 'schema' ) ) otherlv_2= '=' ( (lv_schemaName_3_0= RULE_STRING ) ) otherlv_4= ';' )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleView", "true");
            	    }
            	    // InternalHdbView.g:126:18: ( ( (lv_schema_1_0= 'schema' ) ) otherlv_2= '=' ( (lv_schemaName_3_0= RULE_STRING ) ) otherlv_4= ';' )
            	    // InternalHdbView.g:126:19: ( (lv_schema_1_0= 'schema' ) ) otherlv_2= '=' ( (lv_schemaName_3_0= RULE_STRING ) ) otherlv_4= ';'
            	    {
            	    // InternalHdbView.g:126:19: ( (lv_schema_1_0= 'schema' ) )
            	    // InternalHdbView.g:127:9: (lv_schema_1_0= 'schema' )
            	    {
            	    // InternalHdbView.g:127:9: (lv_schema_1_0= 'schema' )
            	    // InternalHdbView.g:128:10: lv_schema_1_0= 'schema'
            	    {
            	    lv_schema_1_0=(Token)match(input,12,FOLLOW_3); 

            	    										newLeafNode(lv_schema_1_0, grammarAccess.getViewAccess().getSchemaSchemaKeyword_0_0_0());
            	    									

            	    										if (current==null) {
            	    											current = createModelElement(grammarAccess.getViewRule());
            	    										}
            	    										setWithLastConsumed(current, "schema", true, "schema");
            	    									

            	    }


            	    }

            	    otherlv_2=(Token)match(input,13,FOLLOW_4); 

            	    								newLeafNode(otherlv_2, grammarAccess.getViewAccess().getEqualsSignKeyword_0_1());
            	    							
            	    // InternalHdbView.g:144:8: ( (lv_schemaName_3_0= RULE_STRING ) )
            	    // InternalHdbView.g:145:9: (lv_schemaName_3_0= RULE_STRING )
            	    {
            	    // InternalHdbView.g:145:9: (lv_schemaName_3_0= RULE_STRING )
            	    // InternalHdbView.g:146:10: lv_schemaName_3_0= RULE_STRING
            	    {
            	    lv_schemaName_3_0=(Token)match(input,RULE_STRING,FOLLOW_5); 

            	    										newLeafNode(lv_schemaName_3_0, grammarAccess.getViewAccess().getSchemaNameSTRINGTerminalRuleCall_0_2_0());
            	    									

            	    										if (current==null) {
            	    											current = createModelElement(grammarAccess.getViewRule());
            	    										}
            	    										setWithLastConsumed(
            	    											current,
            	    											"schemaName",
            	    											lv_schemaName_3_0,
            	    											"org.eclipse.xtext.common.Terminals.STRING");
            	    									

            	    }


            	    }

            	    otherlv_4=(Token)match(input,14,FOLLOW_6); 

            	    								newLeafNode(otherlv_4, grammarAccess.getViewAccess().getSemicolonKeyword_0_3());
            	    							

            	    }


            	    }

            	     
            	    					getUnorderedGroupHelper().returnFromSelection(grammarAccess.getViewAccess().getUnorderedGroup());
            	    				

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalHdbView.g:172:3: ({...}? => ( ({...}? => ( ( (lv_query_5_0= 'query' ) ) otherlv_6= '=' ( (lv_queryValue_7_0= RULE_STRING ) ) otherlv_8= ';' ) ) ) )
            	    {
            	    // InternalHdbView.g:172:3: ({...}? => ( ({...}? => ( ( (lv_query_5_0= 'query' ) ) otherlv_6= '=' ( (lv_queryValue_7_0= RULE_STRING ) ) otherlv_8= ';' ) ) ) )
            	    // InternalHdbView.g:173:4: {...}? => ( ({...}? => ( ( (lv_query_5_0= 'query' ) ) otherlv_6= '=' ( (lv_queryValue_7_0= RULE_STRING ) ) otherlv_8= ';' ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getViewAccess().getUnorderedGroup(), 1) ) {
            	        throw new FailedPredicateException(input, "ruleView", "getUnorderedGroupHelper().canSelect(grammarAccess.getViewAccess().getUnorderedGroup(), 1)");
            	    }
            	    // InternalHdbView.g:173:98: ( ({...}? => ( ( (lv_query_5_0= 'query' ) ) otherlv_6= '=' ( (lv_queryValue_7_0= RULE_STRING ) ) otherlv_8= ';' ) ) )
            	    // InternalHdbView.g:174:5: ({...}? => ( ( (lv_query_5_0= 'query' ) ) otherlv_6= '=' ( (lv_queryValue_7_0= RULE_STRING ) ) otherlv_8= ';' ) )
            	    {

            	    					getUnorderedGroupHelper().select(grammarAccess.getViewAccess().getUnorderedGroup(), 1);
            	    				
            	    // InternalHdbView.g:177:8: ({...}? => ( ( (lv_query_5_0= 'query' ) ) otherlv_6= '=' ( (lv_queryValue_7_0= RULE_STRING ) ) otherlv_8= ';' ) )
            	    // InternalHdbView.g:177:9: {...}? => ( ( (lv_query_5_0= 'query' ) ) otherlv_6= '=' ( (lv_queryValue_7_0= RULE_STRING ) ) otherlv_8= ';' )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleView", "true");
            	    }
            	    // InternalHdbView.g:177:18: ( ( (lv_query_5_0= 'query' ) ) otherlv_6= '=' ( (lv_queryValue_7_0= RULE_STRING ) ) otherlv_8= ';' )
            	    // InternalHdbView.g:177:19: ( (lv_query_5_0= 'query' ) ) otherlv_6= '=' ( (lv_queryValue_7_0= RULE_STRING ) ) otherlv_8= ';'
            	    {
            	    // InternalHdbView.g:177:19: ( (lv_query_5_0= 'query' ) )
            	    // InternalHdbView.g:178:9: (lv_query_5_0= 'query' )
            	    {
            	    // InternalHdbView.g:178:9: (lv_query_5_0= 'query' )
            	    // InternalHdbView.g:179:10: lv_query_5_0= 'query'
            	    {
            	    lv_query_5_0=(Token)match(input,15,FOLLOW_3); 

            	    										newLeafNode(lv_query_5_0, grammarAccess.getViewAccess().getQueryQueryKeyword_1_0_0());
            	    									

            	    										if (current==null) {
            	    											current = createModelElement(grammarAccess.getViewRule());
            	    										}
            	    										setWithLastConsumed(current, "query", true, "query");
            	    									

            	    }


            	    }

            	    otherlv_6=(Token)match(input,13,FOLLOW_4); 

            	    								newLeafNode(otherlv_6, grammarAccess.getViewAccess().getEqualsSignKeyword_1_1());
            	    							
            	    // InternalHdbView.g:195:8: ( (lv_queryValue_7_0= RULE_STRING ) )
            	    // InternalHdbView.g:196:9: (lv_queryValue_7_0= RULE_STRING )
            	    {
            	    // InternalHdbView.g:196:9: (lv_queryValue_7_0= RULE_STRING )
            	    // InternalHdbView.g:197:10: lv_queryValue_7_0= RULE_STRING
            	    {
            	    lv_queryValue_7_0=(Token)match(input,RULE_STRING,FOLLOW_5); 

            	    										newLeafNode(lv_queryValue_7_0, grammarAccess.getViewAccess().getQueryValueSTRINGTerminalRuleCall_1_2_0());
            	    									

            	    										if (current==null) {
            	    											current = createModelElement(grammarAccess.getViewRule());
            	    										}
            	    										setWithLastConsumed(
            	    											current,
            	    											"queryValue",
            	    											lv_queryValue_7_0,
            	    											"org.eclipse.xtext.common.Terminals.STRING");
            	    									

            	    }


            	    }

            	    otherlv_8=(Token)match(input,14,FOLLOW_6); 

            	    								newLeafNode(otherlv_8, grammarAccess.getViewAccess().getSemicolonKeyword_1_3());
            	    							

            	    }


            	    }

            	     
            	    					getUnorderedGroupHelper().returnFromSelection(grammarAccess.getViewAccess().getUnorderedGroup());
            	    				

            	    }


            	    }


            	    }
            	    break;
            	case 3 :
            	    // InternalHdbView.g:223:3: ({...}? => ( ({...}? => ( ( (lv_dependsOn_9_0= 'depends_on' ) ) otherlv_10= '=' otherlv_11= '[' ( ( (lv_dependsOnValues_12_0= RULE_STRING ) ) (otherlv_13= ',' ( (lv_dependsOnValues_14_0= RULE_STRING ) ) )* )? otherlv_15= ']' otherlv_16= ';' ) ) ) )
            	    {
            	    // InternalHdbView.g:223:3: ({...}? => ( ({...}? => ( ( (lv_dependsOn_9_0= 'depends_on' ) ) otherlv_10= '=' otherlv_11= '[' ( ( (lv_dependsOnValues_12_0= RULE_STRING ) ) (otherlv_13= ',' ( (lv_dependsOnValues_14_0= RULE_STRING ) ) )* )? otherlv_15= ']' otherlv_16= ';' ) ) ) )
            	    // InternalHdbView.g:224:4: {...}? => ( ({...}? => ( ( (lv_dependsOn_9_0= 'depends_on' ) ) otherlv_10= '=' otherlv_11= '[' ( ( (lv_dependsOnValues_12_0= RULE_STRING ) ) (otherlv_13= ',' ( (lv_dependsOnValues_14_0= RULE_STRING ) ) )* )? otherlv_15= ']' otherlv_16= ';' ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getViewAccess().getUnorderedGroup(), 2) ) {
            	        throw new FailedPredicateException(input, "ruleView", "getUnorderedGroupHelper().canSelect(grammarAccess.getViewAccess().getUnorderedGroup(), 2)");
            	    }
            	    // InternalHdbView.g:224:98: ( ({...}? => ( ( (lv_dependsOn_9_0= 'depends_on' ) ) otherlv_10= '=' otherlv_11= '[' ( ( (lv_dependsOnValues_12_0= RULE_STRING ) ) (otherlv_13= ',' ( (lv_dependsOnValues_14_0= RULE_STRING ) ) )* )? otherlv_15= ']' otherlv_16= ';' ) ) )
            	    // InternalHdbView.g:225:5: ({...}? => ( ( (lv_dependsOn_9_0= 'depends_on' ) ) otherlv_10= '=' otherlv_11= '[' ( ( (lv_dependsOnValues_12_0= RULE_STRING ) ) (otherlv_13= ',' ( (lv_dependsOnValues_14_0= RULE_STRING ) ) )* )? otherlv_15= ']' otherlv_16= ';' ) )
            	    {

            	    					getUnorderedGroupHelper().select(grammarAccess.getViewAccess().getUnorderedGroup(), 2);
            	    				
            	    // InternalHdbView.g:228:8: ({...}? => ( ( (lv_dependsOn_9_0= 'depends_on' ) ) otherlv_10= '=' otherlv_11= '[' ( ( (lv_dependsOnValues_12_0= RULE_STRING ) ) (otherlv_13= ',' ( (lv_dependsOnValues_14_0= RULE_STRING ) ) )* )? otherlv_15= ']' otherlv_16= ';' ) )
            	    // InternalHdbView.g:228:9: {...}? => ( ( (lv_dependsOn_9_0= 'depends_on' ) ) otherlv_10= '=' otherlv_11= '[' ( ( (lv_dependsOnValues_12_0= RULE_STRING ) ) (otherlv_13= ',' ( (lv_dependsOnValues_14_0= RULE_STRING ) ) )* )? otherlv_15= ']' otherlv_16= ';' )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleView", "true");
            	    }
            	    // InternalHdbView.g:228:18: ( ( (lv_dependsOn_9_0= 'depends_on' ) ) otherlv_10= '=' otherlv_11= '[' ( ( (lv_dependsOnValues_12_0= RULE_STRING ) ) (otherlv_13= ',' ( (lv_dependsOnValues_14_0= RULE_STRING ) ) )* )? otherlv_15= ']' otherlv_16= ';' )
            	    // InternalHdbView.g:228:19: ( (lv_dependsOn_9_0= 'depends_on' ) ) otherlv_10= '=' otherlv_11= '[' ( ( (lv_dependsOnValues_12_0= RULE_STRING ) ) (otherlv_13= ',' ( (lv_dependsOnValues_14_0= RULE_STRING ) ) )* )? otherlv_15= ']' otherlv_16= ';'
            	    {
            	    // InternalHdbView.g:228:19: ( (lv_dependsOn_9_0= 'depends_on' ) )
            	    // InternalHdbView.g:229:9: (lv_dependsOn_9_0= 'depends_on' )
            	    {
            	    // InternalHdbView.g:229:9: (lv_dependsOn_9_0= 'depends_on' )
            	    // InternalHdbView.g:230:10: lv_dependsOn_9_0= 'depends_on'
            	    {
            	    lv_dependsOn_9_0=(Token)match(input,16,FOLLOW_3); 

            	    										newLeafNode(lv_dependsOn_9_0, grammarAccess.getViewAccess().getDependsOnDepends_onKeyword_2_0_0());
            	    									

            	    										if (current==null) {
            	    											current = createModelElement(grammarAccess.getViewRule());
            	    										}
            	    										setWithLastConsumed(current, "dependsOn", true, "depends_on");
            	    									

            	    }


            	    }

            	    otherlv_10=(Token)match(input,13,FOLLOW_7); 

            	    								newLeafNode(otherlv_10, grammarAccess.getViewAccess().getEqualsSignKeyword_2_1());
            	    							
            	    otherlv_11=(Token)match(input,17,FOLLOW_8); 

            	    								newLeafNode(otherlv_11, grammarAccess.getViewAccess().getLeftSquareBracketKeyword_2_2());
            	    							
            	    // InternalHdbView.g:250:8: ( ( (lv_dependsOnValues_12_0= RULE_STRING ) ) (otherlv_13= ',' ( (lv_dependsOnValues_14_0= RULE_STRING ) ) )* )?
            	    int alt2=2;
            	    int LA2_0 = input.LA(1);

            	    if ( (LA2_0==RULE_STRING) ) {
            	        alt2=1;
            	    }
            	    switch (alt2) {
            	        case 1 :
            	            // InternalHdbView.g:251:9: ( (lv_dependsOnValues_12_0= RULE_STRING ) ) (otherlv_13= ',' ( (lv_dependsOnValues_14_0= RULE_STRING ) ) )*
            	            {
            	            // InternalHdbView.g:251:9: ( (lv_dependsOnValues_12_0= RULE_STRING ) )
            	            // InternalHdbView.g:252:10: (lv_dependsOnValues_12_0= RULE_STRING )
            	            {
            	            // InternalHdbView.g:252:10: (lv_dependsOnValues_12_0= RULE_STRING )
            	            // InternalHdbView.g:253:11: lv_dependsOnValues_12_0= RULE_STRING
            	            {
            	            lv_dependsOnValues_12_0=(Token)match(input,RULE_STRING,FOLLOW_9); 

            	            											newLeafNode(lv_dependsOnValues_12_0, grammarAccess.getViewAccess().getDependsOnValuesSTRINGTerminalRuleCall_2_3_0_0());
            	            										

            	            											if (current==null) {
            	            												current = createModelElement(grammarAccess.getViewRule());
            	            											}
            	            											addWithLastConsumed(
            	            												current,
            	            												"dependsOnValues",
            	            												lv_dependsOnValues_12_0,
            	            												"org.eclipse.xtext.common.Terminals.STRING");
            	            										

            	            }


            	            }

            	            // InternalHdbView.g:269:9: (otherlv_13= ',' ( (lv_dependsOnValues_14_0= RULE_STRING ) ) )*
            	            loop1:
            	            do {
            	                int alt1=2;
            	                int LA1_0 = input.LA(1);

            	                if ( (LA1_0==18) ) {
            	                    alt1=1;
            	                }


            	                switch (alt1) {
            	            	case 1 :
            	            	    // InternalHdbView.g:270:10: otherlv_13= ',' ( (lv_dependsOnValues_14_0= RULE_STRING ) )
            	            	    {
            	            	    otherlv_13=(Token)match(input,18,FOLLOW_4); 

            	            	    										newLeafNode(otherlv_13, grammarAccess.getViewAccess().getCommaKeyword_2_3_1_0());
            	            	    									
            	            	    // InternalHdbView.g:274:10: ( (lv_dependsOnValues_14_0= RULE_STRING ) )
            	            	    // InternalHdbView.g:275:11: (lv_dependsOnValues_14_0= RULE_STRING )
            	            	    {
            	            	    // InternalHdbView.g:275:11: (lv_dependsOnValues_14_0= RULE_STRING )
            	            	    // InternalHdbView.g:276:12: lv_dependsOnValues_14_0= RULE_STRING
            	            	    {
            	            	    lv_dependsOnValues_14_0=(Token)match(input,RULE_STRING,FOLLOW_9); 

            	            	    												newLeafNode(lv_dependsOnValues_14_0, grammarAccess.getViewAccess().getDependsOnValuesSTRINGTerminalRuleCall_2_3_1_1_0());
            	            	    											

            	            	    												if (current==null) {
            	            	    													current = createModelElement(grammarAccess.getViewRule());
            	            	    												}
            	            	    												addWithLastConsumed(
            	            	    													current,
            	            	    													"dependsOnValues",
            	            	    													lv_dependsOnValues_14_0,
            	            	    													"org.eclipse.xtext.common.Terminals.STRING");
            	            	    											

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

            	    otherlv_15=(Token)match(input,19,FOLLOW_5); 

            	    								newLeafNode(otherlv_15, grammarAccess.getViewAccess().getRightSquareBracketKeyword_2_4());
            	    							
            	    otherlv_16=(Token)match(input,14,FOLLOW_6); 

            	    								newLeafNode(otherlv_16, grammarAccess.getViewAccess().getSemicolonKeyword_2_5());
            	    							

            	    }


            	    }

            	     
            	    					getUnorderedGroupHelper().returnFromSelection(grammarAccess.getViewAccess().getUnorderedGroup());
            	    				

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt3 >= 1 ) break loop3;
                        EarlyExitException eee =
                            new EarlyExitException(3, input);
                        throw eee;
                }
                cnt3++;
            } while (true);

            if ( ! getUnorderedGroupHelper().canLeave(grammarAccess.getViewAccess().getUnorderedGroup()) ) {
                throw new FailedPredicateException(input, "ruleView", "getUnorderedGroupHelper().canLeave(grammarAccess.getViewAccess().getUnorderedGroup())");
            }

            }


            }

             
            			  getUnorderedGroupHelper().leave(grammarAccess.getViewAccess().getUnorderedGroup());
            			

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
    // $ANTLR end "ruleView"

    // Delegated rules


 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000019002L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000080010L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x00000000000C0000L});

}
