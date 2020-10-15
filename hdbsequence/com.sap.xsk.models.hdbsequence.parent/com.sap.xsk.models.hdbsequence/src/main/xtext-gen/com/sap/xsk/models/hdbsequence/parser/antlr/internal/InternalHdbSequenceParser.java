package com.sap.xsk.models.hdbsequence.parser.antlr.internal;

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import com.sap.xsk.models.hdbsequence.services.HdbSequenceGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalHdbSequenceParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_STRING", "RULE_INT", "RULE_BOOL", "RULE_ID", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'['", "','", "']'", "'schema'", "'='", "';'", "'increment_by'", "'start_with'", "'maxvalue'", "'nomaxvalue'", "'minvalue'", "'nominvalue'", "'cycles'", "'public'", "'depends_on_table'", "'depends_on_view'", "'depends_on'"
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
    public static final int RULE_ID=7;
    public static final int RULE_WS=10;
    public static final int RULE_ANY_OTHER=11;
    public static final int RULE_BOOL=6;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int RULE_INT=5;
    public static final int T__22=22;
    public static final int RULE_ML_COMMENT=8;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__20=20;
    public static final int T__21=21;

    // delegates
    // delegators


        public InternalHdbSequenceParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalHdbSequenceParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalHdbSequenceParser.tokenNames; }
    public String getGrammarFileName() { return "InternalHdbSequence.g"; }



     	private HdbSequenceGrammarAccess grammarAccess;

        public InternalHdbSequenceParser(TokenStream input, HdbSequenceGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }

        @Override
        protected String getFirstRuleName() {
        	return "HdbSequenceModel";
       	}

       	@Override
       	protected HdbSequenceGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}




    // $ANTLR start "entryRuleHdbSequenceModel"
    // InternalHdbSequence.g:64:1: entryRuleHdbSequenceModel returns [EObject current=null] : iv_ruleHdbSequenceModel= ruleHdbSequenceModel EOF ;
    public final EObject entryRuleHdbSequenceModel() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleHdbSequenceModel = null;


        try {
            // InternalHdbSequence.g:64:57: (iv_ruleHdbSequenceModel= ruleHdbSequenceModel EOF )
            // InternalHdbSequence.g:65:2: iv_ruleHdbSequenceModel= ruleHdbSequenceModel EOF
            {
             newCompositeNode(grammarAccess.getHdbSequenceModelRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleHdbSequenceModel=ruleHdbSequenceModel();

            state._fsp--;

             current =iv_ruleHdbSequenceModel; 
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
    // $ANTLR end "entryRuleHdbSequenceModel"


    // $ANTLR start "ruleHdbSequenceModel"
    // InternalHdbSequence.g:71:1: ruleHdbSequenceModel returns [EObject current=null] : ( (lv_elements_0_0= ruleHdbSequenceElements ) ) ;
    public final EObject ruleHdbSequenceModel() throws RecognitionException {
        EObject current = null;

        EObject lv_elements_0_0 = null;



        	enterRule();

        try {
            // InternalHdbSequence.g:77:2: ( ( (lv_elements_0_0= ruleHdbSequenceElements ) ) )
            // InternalHdbSequence.g:78:2: ( (lv_elements_0_0= ruleHdbSequenceElements ) )
            {
            // InternalHdbSequence.g:78:2: ( (lv_elements_0_0= ruleHdbSequenceElements ) )
            // InternalHdbSequence.g:79:3: (lv_elements_0_0= ruleHdbSequenceElements )
            {
            // InternalHdbSequence.g:79:3: (lv_elements_0_0= ruleHdbSequenceElements )
            // InternalHdbSequence.g:80:4: lv_elements_0_0= ruleHdbSequenceElements
            {

            				newCompositeNode(grammarAccess.getHdbSequenceModelAccess().getElementsHdbSequenceElementsParserRuleCall_0());
            			
            pushFollow(FOLLOW_2);
            lv_elements_0_0=ruleHdbSequenceElements();

            state._fsp--;


            				if (current==null) {
            					current = createModelElementForParent(grammarAccess.getHdbSequenceModelRule());
            				}
            				add(
            					current,
            					"elements",
            					lv_elements_0_0,
            					"com.sap.xsk.models.hdbsequence.HdbSequence.HdbSequenceElements");
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
    // $ANTLR end "ruleHdbSequenceModel"


    // $ANTLR start "entryRuleListString"
    // InternalHdbSequence.g:100:1: entryRuleListString returns [EObject current=null] : iv_ruleListString= ruleListString EOF ;
    public final EObject entryRuleListString() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleListString = null;


        try {
            // InternalHdbSequence.g:100:51: (iv_ruleListString= ruleListString EOF )
            // InternalHdbSequence.g:101:2: iv_ruleListString= ruleListString EOF
            {
             newCompositeNode(grammarAccess.getListStringRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleListString=ruleListString();

            state._fsp--;

             current =iv_ruleListString; 
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
    // $ANTLR end "entryRuleListString"


    // $ANTLR start "ruleListString"
    // InternalHdbSequence.g:107:1: ruleListString returns [EObject current=null] : (otherlv_0= '[' ( ( (lv_values_1_0= RULE_STRING ) ) (otherlv_2= ',' ( (lv_values_3_0= RULE_STRING ) ) )* )? otherlv_4= ']' ) ;
    public final EObject ruleListString() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_values_1_0=null;
        Token otherlv_2=null;
        Token lv_values_3_0=null;
        Token otherlv_4=null;


        	enterRule();

        try {
            // InternalHdbSequence.g:113:2: ( (otherlv_0= '[' ( ( (lv_values_1_0= RULE_STRING ) ) (otherlv_2= ',' ( (lv_values_3_0= RULE_STRING ) ) )* )? otherlv_4= ']' ) )
            // InternalHdbSequence.g:114:2: (otherlv_0= '[' ( ( (lv_values_1_0= RULE_STRING ) ) (otherlv_2= ',' ( (lv_values_3_0= RULE_STRING ) ) )* )? otherlv_4= ']' )
            {
            // InternalHdbSequence.g:114:2: (otherlv_0= '[' ( ( (lv_values_1_0= RULE_STRING ) ) (otherlv_2= ',' ( (lv_values_3_0= RULE_STRING ) ) )* )? otherlv_4= ']' )
            // InternalHdbSequence.g:115:3: otherlv_0= '[' ( ( (lv_values_1_0= RULE_STRING ) ) (otherlv_2= ',' ( (lv_values_3_0= RULE_STRING ) ) )* )? otherlv_4= ']'
            {
            otherlv_0=(Token)match(input,12,FOLLOW_3); 

            			newLeafNode(otherlv_0, grammarAccess.getListStringAccess().getLeftSquareBracketKeyword_0());
            		
            // InternalHdbSequence.g:119:3: ( ( (lv_values_1_0= RULE_STRING ) ) (otherlv_2= ',' ( (lv_values_3_0= RULE_STRING ) ) )* )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==RULE_STRING) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // InternalHdbSequence.g:120:4: ( (lv_values_1_0= RULE_STRING ) ) (otherlv_2= ',' ( (lv_values_3_0= RULE_STRING ) ) )*
                    {
                    // InternalHdbSequence.g:120:4: ( (lv_values_1_0= RULE_STRING ) )
                    // InternalHdbSequence.g:121:5: (lv_values_1_0= RULE_STRING )
                    {
                    // InternalHdbSequence.g:121:5: (lv_values_1_0= RULE_STRING )
                    // InternalHdbSequence.g:122:6: lv_values_1_0= RULE_STRING
                    {
                    lv_values_1_0=(Token)match(input,RULE_STRING,FOLLOW_4); 

                    						newLeafNode(lv_values_1_0, grammarAccess.getListStringAccess().getValuesSTRINGTerminalRuleCall_1_0_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getListStringRule());
                    						}
                    						addWithLastConsumed(
                    							current,
                    							"values",
                    							lv_values_1_0,
                    							"org.eclipse.xtext.common.Terminals.STRING");
                    					

                    }


                    }

                    // InternalHdbSequence.g:138:4: (otherlv_2= ',' ( (lv_values_3_0= RULE_STRING ) ) )*
                    loop1:
                    do {
                        int alt1=2;
                        int LA1_0 = input.LA(1);

                        if ( (LA1_0==13) ) {
                            alt1=1;
                        }


                        switch (alt1) {
                    	case 1 :
                    	    // InternalHdbSequence.g:139:5: otherlv_2= ',' ( (lv_values_3_0= RULE_STRING ) )
                    	    {
                    	    otherlv_2=(Token)match(input,13,FOLLOW_5); 

                    	    					newLeafNode(otherlv_2, grammarAccess.getListStringAccess().getCommaKeyword_1_1_0());
                    	    				
                    	    // InternalHdbSequence.g:143:5: ( (lv_values_3_0= RULE_STRING ) )
                    	    // InternalHdbSequence.g:144:6: (lv_values_3_0= RULE_STRING )
                    	    {
                    	    // InternalHdbSequence.g:144:6: (lv_values_3_0= RULE_STRING )
                    	    // InternalHdbSequence.g:145:7: lv_values_3_0= RULE_STRING
                    	    {
                    	    lv_values_3_0=(Token)match(input,RULE_STRING,FOLLOW_4); 

                    	    							newLeafNode(lv_values_3_0, grammarAccess.getListStringAccess().getValuesSTRINGTerminalRuleCall_1_1_1_0());
                    	    						

                    	    							if (current==null) {
                    	    								current = createModelElement(grammarAccess.getListStringRule());
                    	    							}
                    	    							addWithLastConsumed(
                    	    								current,
                    	    								"values",
                    	    								lv_values_3_0,
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

            otherlv_4=(Token)match(input,14,FOLLOW_2); 

            			newLeafNode(otherlv_4, grammarAccess.getListStringAccess().getRightSquareBracketKeyword_2());
            		

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
    // $ANTLR end "ruleListString"


    // $ANTLR start "entryRuleHdbSequenceElements"
    // InternalHdbSequence.g:171:1: entryRuleHdbSequenceElements returns [EObject current=null] : iv_ruleHdbSequenceElements= ruleHdbSequenceElements EOF ;
    public final EObject entryRuleHdbSequenceElements() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleHdbSequenceElements = null;


        try {
            // InternalHdbSequence.g:171:60: (iv_ruleHdbSequenceElements= ruleHdbSequenceElements EOF )
            // InternalHdbSequence.g:172:2: iv_ruleHdbSequenceElements= ruleHdbSequenceElements EOF
            {
             newCompositeNode(grammarAccess.getHdbSequenceElementsRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleHdbSequenceElements=ruleHdbSequenceElements();

            state._fsp--;

             current =iv_ruleHdbSequenceElements; 
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
    // $ANTLR end "entryRuleHdbSequenceElements"


    // $ANTLR start "ruleHdbSequenceElements"
    // InternalHdbSequence.g:178:1: ruleHdbSequenceElements returns [EObject current=null] : ( ( ( ( ({...}? => ( ({...}? => ( ( (lv_schema_1_0= 'schema' ) ) otherlv_2= '=' ( (lv_schemaValue_3_0= RULE_STRING ) ) otherlv_4= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_incrementBy_5_0= 'increment_by' ) ) otherlv_6= '=' ( (lv_incrementByValue_7_0= RULE_INT ) ) otherlv_8= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_startWith_9_0= 'start_with' ) ) otherlv_10= '=' ( (lv_startWithValue_11_0= RULE_INT ) ) otherlv_12= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_maxValue_13_0= 'maxvalue' ) ) otherlv_14= '=' ( (lv_maxValueValue_15_0= RULE_INT ) ) otherlv_16= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_noMaxValue_17_0= 'nomaxvalue' ) ) otherlv_18= '=' ( (lv_noMaxValueValue_19_0= RULE_BOOL ) ) otherlv_20= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_minValue_21_0= 'minvalue' ) ) otherlv_22= '=' ( (lv_minVlaueValue_23_0= RULE_INT ) ) otherlv_24= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_noMinValue_25_0= 'nominvalue' ) ) otherlv_26= '=' ( (lv_noMinValueValue_27_0= RULE_BOOL ) ) otherlv_28= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_cycles_29_0= 'cycles' ) ) otherlv_30= '=' ( (lv_cyclesValue_31_0= RULE_BOOL ) ) otherlv_32= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_public_33_0= 'public' ) ) otherlv_34= '=' ( (lv_publicValue_35_0= RULE_BOOL ) ) otherlv_36= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_dependsOnTable_37_0= 'depends_on_table' ) ) otherlv_38= '=' ( (lv_dependsOnTableValue_39_0= RULE_STRING ) ) otherlv_40= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_dependsOnView_41_0= 'depends_on_view' ) ) otherlv_42= '=' ( (lv_dependsOnViewValue_43_0= RULE_STRING ) ) otherlv_44= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_45= 'depends_on' otherlv_46= '=' ( (lv_value_47_0= ruleListString ) ) otherlv_48= ';' ) ) ) ) )+ {...}?) ) ) ;
    public final EObject ruleHdbSequenceElements() throws RecognitionException {
        EObject current = null;

        Token lv_schema_1_0=null;
        Token otherlv_2=null;
        Token lv_schemaValue_3_0=null;
        Token otherlv_4=null;
        Token lv_incrementBy_5_0=null;
        Token otherlv_6=null;
        Token lv_incrementByValue_7_0=null;
        Token otherlv_8=null;
        Token lv_startWith_9_0=null;
        Token otherlv_10=null;
        Token lv_startWithValue_11_0=null;
        Token otherlv_12=null;
        Token lv_maxValue_13_0=null;
        Token otherlv_14=null;
        Token lv_maxValueValue_15_0=null;
        Token otherlv_16=null;
        Token lv_noMaxValue_17_0=null;
        Token otherlv_18=null;
        Token lv_noMaxValueValue_19_0=null;
        Token otherlv_20=null;
        Token lv_minValue_21_0=null;
        Token otherlv_22=null;
        Token lv_minVlaueValue_23_0=null;
        Token otherlv_24=null;
        Token lv_noMinValue_25_0=null;
        Token otherlv_26=null;
        Token lv_noMinValueValue_27_0=null;
        Token otherlv_28=null;
        Token lv_cycles_29_0=null;
        Token otherlv_30=null;
        Token lv_cyclesValue_31_0=null;
        Token otherlv_32=null;
        Token lv_public_33_0=null;
        Token otherlv_34=null;
        Token lv_publicValue_35_0=null;
        Token otherlv_36=null;
        Token lv_dependsOnTable_37_0=null;
        Token otherlv_38=null;
        Token lv_dependsOnTableValue_39_0=null;
        Token otherlv_40=null;
        Token lv_dependsOnView_41_0=null;
        Token otherlv_42=null;
        Token lv_dependsOnViewValue_43_0=null;
        Token otherlv_44=null;
        Token otherlv_45=null;
        Token otherlv_46=null;
        Token otherlv_48=null;
        EObject lv_value_47_0 = null;



        	enterRule();

        try {
            // InternalHdbSequence.g:184:2: ( ( ( ( ( ({...}? => ( ({...}? => ( ( (lv_schema_1_0= 'schema' ) ) otherlv_2= '=' ( (lv_schemaValue_3_0= RULE_STRING ) ) otherlv_4= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_incrementBy_5_0= 'increment_by' ) ) otherlv_6= '=' ( (lv_incrementByValue_7_0= RULE_INT ) ) otherlv_8= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_startWith_9_0= 'start_with' ) ) otherlv_10= '=' ( (lv_startWithValue_11_0= RULE_INT ) ) otherlv_12= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_maxValue_13_0= 'maxvalue' ) ) otherlv_14= '=' ( (lv_maxValueValue_15_0= RULE_INT ) ) otherlv_16= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_noMaxValue_17_0= 'nomaxvalue' ) ) otherlv_18= '=' ( (lv_noMaxValueValue_19_0= RULE_BOOL ) ) otherlv_20= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_minValue_21_0= 'minvalue' ) ) otherlv_22= '=' ( (lv_minVlaueValue_23_0= RULE_INT ) ) otherlv_24= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_noMinValue_25_0= 'nominvalue' ) ) otherlv_26= '=' ( (lv_noMinValueValue_27_0= RULE_BOOL ) ) otherlv_28= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_cycles_29_0= 'cycles' ) ) otherlv_30= '=' ( (lv_cyclesValue_31_0= RULE_BOOL ) ) otherlv_32= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_public_33_0= 'public' ) ) otherlv_34= '=' ( (lv_publicValue_35_0= RULE_BOOL ) ) otherlv_36= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_dependsOnTable_37_0= 'depends_on_table' ) ) otherlv_38= '=' ( (lv_dependsOnTableValue_39_0= RULE_STRING ) ) otherlv_40= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_dependsOnView_41_0= 'depends_on_view' ) ) otherlv_42= '=' ( (lv_dependsOnViewValue_43_0= RULE_STRING ) ) otherlv_44= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_45= 'depends_on' otherlv_46= '=' ( (lv_value_47_0= ruleListString ) ) otherlv_48= ';' ) ) ) ) )+ {...}?) ) ) )
            // InternalHdbSequence.g:185:2: ( ( ( ( ({...}? => ( ({...}? => ( ( (lv_schema_1_0= 'schema' ) ) otherlv_2= '=' ( (lv_schemaValue_3_0= RULE_STRING ) ) otherlv_4= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_incrementBy_5_0= 'increment_by' ) ) otherlv_6= '=' ( (lv_incrementByValue_7_0= RULE_INT ) ) otherlv_8= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_startWith_9_0= 'start_with' ) ) otherlv_10= '=' ( (lv_startWithValue_11_0= RULE_INT ) ) otherlv_12= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_maxValue_13_0= 'maxvalue' ) ) otherlv_14= '=' ( (lv_maxValueValue_15_0= RULE_INT ) ) otherlv_16= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_noMaxValue_17_0= 'nomaxvalue' ) ) otherlv_18= '=' ( (lv_noMaxValueValue_19_0= RULE_BOOL ) ) otherlv_20= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_minValue_21_0= 'minvalue' ) ) otherlv_22= '=' ( (lv_minVlaueValue_23_0= RULE_INT ) ) otherlv_24= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_noMinValue_25_0= 'nominvalue' ) ) otherlv_26= '=' ( (lv_noMinValueValue_27_0= RULE_BOOL ) ) otherlv_28= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_cycles_29_0= 'cycles' ) ) otherlv_30= '=' ( (lv_cyclesValue_31_0= RULE_BOOL ) ) otherlv_32= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_public_33_0= 'public' ) ) otherlv_34= '=' ( (lv_publicValue_35_0= RULE_BOOL ) ) otherlv_36= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_dependsOnTable_37_0= 'depends_on_table' ) ) otherlv_38= '=' ( (lv_dependsOnTableValue_39_0= RULE_STRING ) ) otherlv_40= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_dependsOnView_41_0= 'depends_on_view' ) ) otherlv_42= '=' ( (lv_dependsOnViewValue_43_0= RULE_STRING ) ) otherlv_44= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_45= 'depends_on' otherlv_46= '=' ( (lv_value_47_0= ruleListString ) ) otherlv_48= ';' ) ) ) ) )+ {...}?) ) )
            {
            // InternalHdbSequence.g:185:2: ( ( ( ( ({...}? => ( ({...}? => ( ( (lv_schema_1_0= 'schema' ) ) otherlv_2= '=' ( (lv_schemaValue_3_0= RULE_STRING ) ) otherlv_4= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_incrementBy_5_0= 'increment_by' ) ) otherlv_6= '=' ( (lv_incrementByValue_7_0= RULE_INT ) ) otherlv_8= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_startWith_9_0= 'start_with' ) ) otherlv_10= '=' ( (lv_startWithValue_11_0= RULE_INT ) ) otherlv_12= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_maxValue_13_0= 'maxvalue' ) ) otherlv_14= '=' ( (lv_maxValueValue_15_0= RULE_INT ) ) otherlv_16= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_noMaxValue_17_0= 'nomaxvalue' ) ) otherlv_18= '=' ( (lv_noMaxValueValue_19_0= RULE_BOOL ) ) otherlv_20= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_minValue_21_0= 'minvalue' ) ) otherlv_22= '=' ( (lv_minVlaueValue_23_0= RULE_INT ) ) otherlv_24= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_noMinValue_25_0= 'nominvalue' ) ) otherlv_26= '=' ( (lv_noMinValueValue_27_0= RULE_BOOL ) ) otherlv_28= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_cycles_29_0= 'cycles' ) ) otherlv_30= '=' ( (lv_cyclesValue_31_0= RULE_BOOL ) ) otherlv_32= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_public_33_0= 'public' ) ) otherlv_34= '=' ( (lv_publicValue_35_0= RULE_BOOL ) ) otherlv_36= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_dependsOnTable_37_0= 'depends_on_table' ) ) otherlv_38= '=' ( (lv_dependsOnTableValue_39_0= RULE_STRING ) ) otherlv_40= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_dependsOnView_41_0= 'depends_on_view' ) ) otherlv_42= '=' ( (lv_dependsOnViewValue_43_0= RULE_STRING ) ) otherlv_44= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_45= 'depends_on' otherlv_46= '=' ( (lv_value_47_0= ruleListString ) ) otherlv_48= ';' ) ) ) ) )+ {...}?) ) )
            // InternalHdbSequence.g:186:3: ( ( ( ({...}? => ( ({...}? => ( ( (lv_schema_1_0= 'schema' ) ) otherlv_2= '=' ( (lv_schemaValue_3_0= RULE_STRING ) ) otherlv_4= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_incrementBy_5_0= 'increment_by' ) ) otherlv_6= '=' ( (lv_incrementByValue_7_0= RULE_INT ) ) otherlv_8= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_startWith_9_0= 'start_with' ) ) otherlv_10= '=' ( (lv_startWithValue_11_0= RULE_INT ) ) otherlv_12= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_maxValue_13_0= 'maxvalue' ) ) otherlv_14= '=' ( (lv_maxValueValue_15_0= RULE_INT ) ) otherlv_16= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_noMaxValue_17_0= 'nomaxvalue' ) ) otherlv_18= '=' ( (lv_noMaxValueValue_19_0= RULE_BOOL ) ) otherlv_20= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_minValue_21_0= 'minvalue' ) ) otherlv_22= '=' ( (lv_minVlaueValue_23_0= RULE_INT ) ) otherlv_24= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_noMinValue_25_0= 'nominvalue' ) ) otherlv_26= '=' ( (lv_noMinValueValue_27_0= RULE_BOOL ) ) otherlv_28= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_cycles_29_0= 'cycles' ) ) otherlv_30= '=' ( (lv_cyclesValue_31_0= RULE_BOOL ) ) otherlv_32= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_public_33_0= 'public' ) ) otherlv_34= '=' ( (lv_publicValue_35_0= RULE_BOOL ) ) otherlv_36= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_dependsOnTable_37_0= 'depends_on_table' ) ) otherlv_38= '=' ( (lv_dependsOnTableValue_39_0= RULE_STRING ) ) otherlv_40= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_dependsOnView_41_0= 'depends_on_view' ) ) otherlv_42= '=' ( (lv_dependsOnViewValue_43_0= RULE_STRING ) ) otherlv_44= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_45= 'depends_on' otherlv_46= '=' ( (lv_value_47_0= ruleListString ) ) otherlv_48= ';' ) ) ) ) )+ {...}?) )
            {
            // InternalHdbSequence.g:186:3: ( ( ( ({...}? => ( ({...}? => ( ( (lv_schema_1_0= 'schema' ) ) otherlv_2= '=' ( (lv_schemaValue_3_0= RULE_STRING ) ) otherlv_4= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_incrementBy_5_0= 'increment_by' ) ) otherlv_6= '=' ( (lv_incrementByValue_7_0= RULE_INT ) ) otherlv_8= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_startWith_9_0= 'start_with' ) ) otherlv_10= '=' ( (lv_startWithValue_11_0= RULE_INT ) ) otherlv_12= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_maxValue_13_0= 'maxvalue' ) ) otherlv_14= '=' ( (lv_maxValueValue_15_0= RULE_INT ) ) otherlv_16= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_noMaxValue_17_0= 'nomaxvalue' ) ) otherlv_18= '=' ( (lv_noMaxValueValue_19_0= RULE_BOOL ) ) otherlv_20= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_minValue_21_0= 'minvalue' ) ) otherlv_22= '=' ( (lv_minVlaueValue_23_0= RULE_INT ) ) otherlv_24= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_noMinValue_25_0= 'nominvalue' ) ) otherlv_26= '=' ( (lv_noMinValueValue_27_0= RULE_BOOL ) ) otherlv_28= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_cycles_29_0= 'cycles' ) ) otherlv_30= '=' ( (lv_cyclesValue_31_0= RULE_BOOL ) ) otherlv_32= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_public_33_0= 'public' ) ) otherlv_34= '=' ( (lv_publicValue_35_0= RULE_BOOL ) ) otherlv_36= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_dependsOnTable_37_0= 'depends_on_table' ) ) otherlv_38= '=' ( (lv_dependsOnTableValue_39_0= RULE_STRING ) ) otherlv_40= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_dependsOnView_41_0= 'depends_on_view' ) ) otherlv_42= '=' ( (lv_dependsOnViewValue_43_0= RULE_STRING ) ) otherlv_44= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_45= 'depends_on' otherlv_46= '=' ( (lv_value_47_0= ruleListString ) ) otherlv_48= ';' ) ) ) ) )+ {...}?) )
            // InternalHdbSequence.g:187:4: ( ( ({...}? => ( ({...}? => ( ( (lv_schema_1_0= 'schema' ) ) otherlv_2= '=' ( (lv_schemaValue_3_0= RULE_STRING ) ) otherlv_4= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_incrementBy_5_0= 'increment_by' ) ) otherlv_6= '=' ( (lv_incrementByValue_7_0= RULE_INT ) ) otherlv_8= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_startWith_9_0= 'start_with' ) ) otherlv_10= '=' ( (lv_startWithValue_11_0= RULE_INT ) ) otherlv_12= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_maxValue_13_0= 'maxvalue' ) ) otherlv_14= '=' ( (lv_maxValueValue_15_0= RULE_INT ) ) otherlv_16= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_noMaxValue_17_0= 'nomaxvalue' ) ) otherlv_18= '=' ( (lv_noMaxValueValue_19_0= RULE_BOOL ) ) otherlv_20= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_minValue_21_0= 'minvalue' ) ) otherlv_22= '=' ( (lv_minVlaueValue_23_0= RULE_INT ) ) otherlv_24= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_noMinValue_25_0= 'nominvalue' ) ) otherlv_26= '=' ( (lv_noMinValueValue_27_0= RULE_BOOL ) ) otherlv_28= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_cycles_29_0= 'cycles' ) ) otherlv_30= '=' ( (lv_cyclesValue_31_0= RULE_BOOL ) ) otherlv_32= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_public_33_0= 'public' ) ) otherlv_34= '=' ( (lv_publicValue_35_0= RULE_BOOL ) ) otherlv_36= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_dependsOnTable_37_0= 'depends_on_table' ) ) otherlv_38= '=' ( (lv_dependsOnTableValue_39_0= RULE_STRING ) ) otherlv_40= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_dependsOnView_41_0= 'depends_on_view' ) ) otherlv_42= '=' ( (lv_dependsOnViewValue_43_0= RULE_STRING ) ) otherlv_44= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_45= 'depends_on' otherlv_46= '=' ( (lv_value_47_0= ruleListString ) ) otherlv_48= ';' ) ) ) ) )+ {...}?)
            {
             
            			  getUnorderedGroupHelper().enter(grammarAccess.getHdbSequenceElementsAccess().getUnorderedGroup());
            			
            // InternalHdbSequence.g:190:4: ( ( ({...}? => ( ({...}? => ( ( (lv_schema_1_0= 'schema' ) ) otherlv_2= '=' ( (lv_schemaValue_3_0= RULE_STRING ) ) otherlv_4= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_incrementBy_5_0= 'increment_by' ) ) otherlv_6= '=' ( (lv_incrementByValue_7_0= RULE_INT ) ) otherlv_8= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_startWith_9_0= 'start_with' ) ) otherlv_10= '=' ( (lv_startWithValue_11_0= RULE_INT ) ) otherlv_12= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_maxValue_13_0= 'maxvalue' ) ) otherlv_14= '=' ( (lv_maxValueValue_15_0= RULE_INT ) ) otherlv_16= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_noMaxValue_17_0= 'nomaxvalue' ) ) otherlv_18= '=' ( (lv_noMaxValueValue_19_0= RULE_BOOL ) ) otherlv_20= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_minValue_21_0= 'minvalue' ) ) otherlv_22= '=' ( (lv_minVlaueValue_23_0= RULE_INT ) ) otherlv_24= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_noMinValue_25_0= 'nominvalue' ) ) otherlv_26= '=' ( (lv_noMinValueValue_27_0= RULE_BOOL ) ) otherlv_28= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_cycles_29_0= 'cycles' ) ) otherlv_30= '=' ( (lv_cyclesValue_31_0= RULE_BOOL ) ) otherlv_32= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_public_33_0= 'public' ) ) otherlv_34= '=' ( (lv_publicValue_35_0= RULE_BOOL ) ) otherlv_36= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_dependsOnTable_37_0= 'depends_on_table' ) ) otherlv_38= '=' ( (lv_dependsOnTableValue_39_0= RULE_STRING ) ) otherlv_40= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_dependsOnView_41_0= 'depends_on_view' ) ) otherlv_42= '=' ( (lv_dependsOnViewValue_43_0= RULE_STRING ) ) otherlv_44= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_45= 'depends_on' otherlv_46= '=' ( (lv_value_47_0= ruleListString ) ) otherlv_48= ';' ) ) ) ) )+ {...}?)
            // InternalHdbSequence.g:191:5: ( ({...}? => ( ({...}? => ( ( (lv_schema_1_0= 'schema' ) ) otherlv_2= '=' ( (lv_schemaValue_3_0= RULE_STRING ) ) otherlv_4= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_incrementBy_5_0= 'increment_by' ) ) otherlv_6= '=' ( (lv_incrementByValue_7_0= RULE_INT ) ) otherlv_8= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_startWith_9_0= 'start_with' ) ) otherlv_10= '=' ( (lv_startWithValue_11_0= RULE_INT ) ) otherlv_12= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_maxValue_13_0= 'maxvalue' ) ) otherlv_14= '=' ( (lv_maxValueValue_15_0= RULE_INT ) ) otherlv_16= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_noMaxValue_17_0= 'nomaxvalue' ) ) otherlv_18= '=' ( (lv_noMaxValueValue_19_0= RULE_BOOL ) ) otherlv_20= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_minValue_21_0= 'minvalue' ) ) otherlv_22= '=' ( (lv_minVlaueValue_23_0= RULE_INT ) ) otherlv_24= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_noMinValue_25_0= 'nominvalue' ) ) otherlv_26= '=' ( (lv_noMinValueValue_27_0= RULE_BOOL ) ) otherlv_28= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_cycles_29_0= 'cycles' ) ) otherlv_30= '=' ( (lv_cyclesValue_31_0= RULE_BOOL ) ) otherlv_32= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_public_33_0= 'public' ) ) otherlv_34= '=' ( (lv_publicValue_35_0= RULE_BOOL ) ) otherlv_36= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_dependsOnTable_37_0= 'depends_on_table' ) ) otherlv_38= '=' ( (lv_dependsOnTableValue_39_0= RULE_STRING ) ) otherlv_40= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_dependsOnView_41_0= 'depends_on_view' ) ) otherlv_42= '=' ( (lv_dependsOnViewValue_43_0= RULE_STRING ) ) otherlv_44= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_45= 'depends_on' otherlv_46= '=' ( (lv_value_47_0= ruleListString ) ) otherlv_48= ';' ) ) ) ) )+ {...}?
            {
            // InternalHdbSequence.g:191:5: ( ({...}? => ( ({...}? => ( ( (lv_schema_1_0= 'schema' ) ) otherlv_2= '=' ( (lv_schemaValue_3_0= RULE_STRING ) ) otherlv_4= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_incrementBy_5_0= 'increment_by' ) ) otherlv_6= '=' ( (lv_incrementByValue_7_0= RULE_INT ) ) otherlv_8= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_startWith_9_0= 'start_with' ) ) otherlv_10= '=' ( (lv_startWithValue_11_0= RULE_INT ) ) otherlv_12= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_maxValue_13_0= 'maxvalue' ) ) otherlv_14= '=' ( (lv_maxValueValue_15_0= RULE_INT ) ) otherlv_16= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_noMaxValue_17_0= 'nomaxvalue' ) ) otherlv_18= '=' ( (lv_noMaxValueValue_19_0= RULE_BOOL ) ) otherlv_20= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_minValue_21_0= 'minvalue' ) ) otherlv_22= '=' ( (lv_minVlaueValue_23_0= RULE_INT ) ) otherlv_24= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_noMinValue_25_0= 'nominvalue' ) ) otherlv_26= '=' ( (lv_noMinValueValue_27_0= RULE_BOOL ) ) otherlv_28= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_cycles_29_0= 'cycles' ) ) otherlv_30= '=' ( (lv_cyclesValue_31_0= RULE_BOOL ) ) otherlv_32= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_public_33_0= 'public' ) ) otherlv_34= '=' ( (lv_publicValue_35_0= RULE_BOOL ) ) otherlv_36= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_dependsOnTable_37_0= 'depends_on_table' ) ) otherlv_38= '=' ( (lv_dependsOnTableValue_39_0= RULE_STRING ) ) otherlv_40= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_dependsOnView_41_0= 'depends_on_view' ) ) otherlv_42= '=' ( (lv_dependsOnViewValue_43_0= RULE_STRING ) ) otherlv_44= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_45= 'depends_on' otherlv_46= '=' ( (lv_value_47_0= ruleListString ) ) otherlv_48= ';' ) ) ) ) )+
            int cnt3=0;
            loop3:
            do {
                int alt3=13;
                alt3 = dfa3.predict(input);
                switch (alt3) {
            	case 1 :
            	    // InternalHdbSequence.g:192:3: ({...}? => ( ({...}? => ( ( (lv_schema_1_0= 'schema' ) ) otherlv_2= '=' ( (lv_schemaValue_3_0= RULE_STRING ) ) otherlv_4= ';' ) ) ) )
            	    {
            	    // InternalHdbSequence.g:192:3: ({...}? => ( ({...}? => ( ( (lv_schema_1_0= 'schema' ) ) otherlv_2= '=' ( (lv_schemaValue_3_0= RULE_STRING ) ) otherlv_4= ';' ) ) ) )
            	    // InternalHdbSequence.g:193:4: {...}? => ( ({...}? => ( ( (lv_schema_1_0= 'schema' ) ) otherlv_2= '=' ( (lv_schemaValue_3_0= RULE_STRING ) ) otherlv_4= ';' ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getHdbSequenceElementsAccess().getUnorderedGroup(), 0) ) {
            	        throw new FailedPredicateException(input, "ruleHdbSequenceElements", "getUnorderedGroupHelper().canSelect(grammarAccess.getHdbSequenceElementsAccess().getUnorderedGroup(), 0)");
            	    }
            	    // InternalHdbSequence.g:193:113: ( ({...}? => ( ( (lv_schema_1_0= 'schema' ) ) otherlv_2= '=' ( (lv_schemaValue_3_0= RULE_STRING ) ) otherlv_4= ';' ) ) )
            	    // InternalHdbSequence.g:194:5: ({...}? => ( ( (lv_schema_1_0= 'schema' ) ) otherlv_2= '=' ( (lv_schemaValue_3_0= RULE_STRING ) ) otherlv_4= ';' ) )
            	    {

            	    					getUnorderedGroupHelper().select(grammarAccess.getHdbSequenceElementsAccess().getUnorderedGroup(), 0);
            	    				
            	    // InternalHdbSequence.g:197:8: ({...}? => ( ( (lv_schema_1_0= 'schema' ) ) otherlv_2= '=' ( (lv_schemaValue_3_0= RULE_STRING ) ) otherlv_4= ';' ) )
            	    // InternalHdbSequence.g:197:9: {...}? => ( ( (lv_schema_1_0= 'schema' ) ) otherlv_2= '=' ( (lv_schemaValue_3_0= RULE_STRING ) ) otherlv_4= ';' )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleHdbSequenceElements", "true");
            	    }
            	    // InternalHdbSequence.g:197:18: ( ( (lv_schema_1_0= 'schema' ) ) otherlv_2= '=' ( (lv_schemaValue_3_0= RULE_STRING ) ) otherlv_4= ';' )
            	    // InternalHdbSequence.g:197:19: ( (lv_schema_1_0= 'schema' ) ) otherlv_2= '=' ( (lv_schemaValue_3_0= RULE_STRING ) ) otherlv_4= ';'
            	    {
            	    // InternalHdbSequence.g:197:19: ( (lv_schema_1_0= 'schema' ) )
            	    // InternalHdbSequence.g:198:9: (lv_schema_1_0= 'schema' )
            	    {
            	    // InternalHdbSequence.g:198:9: (lv_schema_1_0= 'schema' )
            	    // InternalHdbSequence.g:199:10: lv_schema_1_0= 'schema'
            	    {
            	    lv_schema_1_0=(Token)match(input,15,FOLLOW_6); 

            	    										newLeafNode(lv_schema_1_0, grammarAccess.getHdbSequenceElementsAccess().getSchemaSchemaKeyword_0_0_0());
            	    									

            	    										if (current==null) {
            	    											current = createModelElement(grammarAccess.getHdbSequenceElementsRule());
            	    										}
            	    										setWithLastConsumed(current, "schema", lv_schema_1_0, "schema");
            	    									

            	    }


            	    }

            	    otherlv_2=(Token)match(input,16,FOLLOW_5); 

            	    								newLeafNode(otherlv_2, grammarAccess.getHdbSequenceElementsAccess().getEqualsSignKeyword_0_1());
            	    							
            	    // InternalHdbSequence.g:215:8: ( (lv_schemaValue_3_0= RULE_STRING ) )
            	    // InternalHdbSequence.g:216:9: (lv_schemaValue_3_0= RULE_STRING )
            	    {
            	    // InternalHdbSequence.g:216:9: (lv_schemaValue_3_0= RULE_STRING )
            	    // InternalHdbSequence.g:217:10: lv_schemaValue_3_0= RULE_STRING
            	    {
            	    lv_schemaValue_3_0=(Token)match(input,RULE_STRING,FOLLOW_7); 

            	    										newLeafNode(lv_schemaValue_3_0, grammarAccess.getHdbSequenceElementsAccess().getSchemaValueSTRINGTerminalRuleCall_0_2_0());
            	    									

            	    										if (current==null) {
            	    											current = createModelElement(grammarAccess.getHdbSequenceElementsRule());
            	    										}
            	    										setWithLastConsumed(
            	    											current,
            	    											"schemaValue",
            	    											lv_schemaValue_3_0,
            	    											"org.eclipse.xtext.common.Terminals.STRING");
            	    									

            	    }


            	    }

            	    otherlv_4=(Token)match(input,17,FOLLOW_8); 

            	    								newLeafNode(otherlv_4, grammarAccess.getHdbSequenceElementsAccess().getSemicolonKeyword_0_3());
            	    							

            	    }


            	    }

            	     
            	    					getUnorderedGroupHelper().returnFromSelection(grammarAccess.getHdbSequenceElementsAccess().getUnorderedGroup());
            	    				

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalHdbSequence.g:243:3: ({...}? => ( ({...}? => ( ( (lv_incrementBy_5_0= 'increment_by' ) ) otherlv_6= '=' ( (lv_incrementByValue_7_0= RULE_INT ) ) otherlv_8= ';' ) ) ) )
            	    {
            	    // InternalHdbSequence.g:243:3: ({...}? => ( ({...}? => ( ( (lv_incrementBy_5_0= 'increment_by' ) ) otherlv_6= '=' ( (lv_incrementByValue_7_0= RULE_INT ) ) otherlv_8= ';' ) ) ) )
            	    // InternalHdbSequence.g:244:4: {...}? => ( ({...}? => ( ( (lv_incrementBy_5_0= 'increment_by' ) ) otherlv_6= '=' ( (lv_incrementByValue_7_0= RULE_INT ) ) otherlv_8= ';' ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getHdbSequenceElementsAccess().getUnorderedGroup(), 1) ) {
            	        throw new FailedPredicateException(input, "ruleHdbSequenceElements", "getUnorderedGroupHelper().canSelect(grammarAccess.getHdbSequenceElementsAccess().getUnorderedGroup(), 1)");
            	    }
            	    // InternalHdbSequence.g:244:113: ( ({...}? => ( ( (lv_incrementBy_5_0= 'increment_by' ) ) otherlv_6= '=' ( (lv_incrementByValue_7_0= RULE_INT ) ) otherlv_8= ';' ) ) )
            	    // InternalHdbSequence.g:245:5: ({...}? => ( ( (lv_incrementBy_5_0= 'increment_by' ) ) otherlv_6= '=' ( (lv_incrementByValue_7_0= RULE_INT ) ) otherlv_8= ';' ) )
            	    {

            	    					getUnorderedGroupHelper().select(grammarAccess.getHdbSequenceElementsAccess().getUnorderedGroup(), 1);
            	    				
            	    // InternalHdbSequence.g:248:8: ({...}? => ( ( (lv_incrementBy_5_0= 'increment_by' ) ) otherlv_6= '=' ( (lv_incrementByValue_7_0= RULE_INT ) ) otherlv_8= ';' ) )
            	    // InternalHdbSequence.g:248:9: {...}? => ( ( (lv_incrementBy_5_0= 'increment_by' ) ) otherlv_6= '=' ( (lv_incrementByValue_7_0= RULE_INT ) ) otherlv_8= ';' )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleHdbSequenceElements", "true");
            	    }
            	    // InternalHdbSequence.g:248:18: ( ( (lv_incrementBy_5_0= 'increment_by' ) ) otherlv_6= '=' ( (lv_incrementByValue_7_0= RULE_INT ) ) otherlv_8= ';' )
            	    // InternalHdbSequence.g:248:19: ( (lv_incrementBy_5_0= 'increment_by' ) ) otherlv_6= '=' ( (lv_incrementByValue_7_0= RULE_INT ) ) otherlv_8= ';'
            	    {
            	    // InternalHdbSequence.g:248:19: ( (lv_incrementBy_5_0= 'increment_by' ) )
            	    // InternalHdbSequence.g:249:9: (lv_incrementBy_5_0= 'increment_by' )
            	    {
            	    // InternalHdbSequence.g:249:9: (lv_incrementBy_5_0= 'increment_by' )
            	    // InternalHdbSequence.g:250:10: lv_incrementBy_5_0= 'increment_by'
            	    {
            	    lv_incrementBy_5_0=(Token)match(input,18,FOLLOW_6); 

            	    										newLeafNode(lv_incrementBy_5_0, grammarAccess.getHdbSequenceElementsAccess().getIncrementByIncrement_byKeyword_1_0_0());
            	    									

            	    										if (current==null) {
            	    											current = createModelElement(grammarAccess.getHdbSequenceElementsRule());
            	    										}
            	    										setWithLastConsumed(current, "incrementBy", lv_incrementBy_5_0, "increment_by");
            	    									

            	    }


            	    }

            	    otherlv_6=(Token)match(input,16,FOLLOW_9); 

            	    								newLeafNode(otherlv_6, grammarAccess.getHdbSequenceElementsAccess().getEqualsSignKeyword_1_1());
            	    							
            	    // InternalHdbSequence.g:266:8: ( (lv_incrementByValue_7_0= RULE_INT ) )
            	    // InternalHdbSequence.g:267:9: (lv_incrementByValue_7_0= RULE_INT )
            	    {
            	    // InternalHdbSequence.g:267:9: (lv_incrementByValue_7_0= RULE_INT )
            	    // InternalHdbSequence.g:268:10: lv_incrementByValue_7_0= RULE_INT
            	    {
            	    lv_incrementByValue_7_0=(Token)match(input,RULE_INT,FOLLOW_7); 

            	    										newLeafNode(lv_incrementByValue_7_0, grammarAccess.getHdbSequenceElementsAccess().getIncrementByValueINTTerminalRuleCall_1_2_0());
            	    									

            	    										if (current==null) {
            	    											current = createModelElement(grammarAccess.getHdbSequenceElementsRule());
            	    										}
            	    										setWithLastConsumed(
            	    											current,
            	    											"incrementByValue",
            	    											lv_incrementByValue_7_0,
            	    											"org.eclipse.xtext.common.Terminals.INT");
            	    									

            	    }


            	    }

            	    otherlv_8=(Token)match(input,17,FOLLOW_8); 

            	    								newLeafNode(otherlv_8, grammarAccess.getHdbSequenceElementsAccess().getSemicolonKeyword_1_3());
            	    							

            	    }


            	    }

            	     
            	    					getUnorderedGroupHelper().returnFromSelection(grammarAccess.getHdbSequenceElementsAccess().getUnorderedGroup());
            	    				

            	    }


            	    }


            	    }
            	    break;
            	case 3 :
            	    // InternalHdbSequence.g:294:3: ({...}? => ( ({...}? => ( ( (lv_startWith_9_0= 'start_with' ) ) otherlv_10= '=' ( (lv_startWithValue_11_0= RULE_INT ) ) otherlv_12= ';' ) ) ) )
            	    {
            	    // InternalHdbSequence.g:294:3: ({...}? => ( ({...}? => ( ( (lv_startWith_9_0= 'start_with' ) ) otherlv_10= '=' ( (lv_startWithValue_11_0= RULE_INT ) ) otherlv_12= ';' ) ) ) )
            	    // InternalHdbSequence.g:295:4: {...}? => ( ({...}? => ( ( (lv_startWith_9_0= 'start_with' ) ) otherlv_10= '=' ( (lv_startWithValue_11_0= RULE_INT ) ) otherlv_12= ';' ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getHdbSequenceElementsAccess().getUnorderedGroup(), 2) ) {
            	        throw new FailedPredicateException(input, "ruleHdbSequenceElements", "getUnorderedGroupHelper().canSelect(grammarAccess.getHdbSequenceElementsAccess().getUnorderedGroup(), 2)");
            	    }
            	    // InternalHdbSequence.g:295:113: ( ({...}? => ( ( (lv_startWith_9_0= 'start_with' ) ) otherlv_10= '=' ( (lv_startWithValue_11_0= RULE_INT ) ) otherlv_12= ';' ) ) )
            	    // InternalHdbSequence.g:296:5: ({...}? => ( ( (lv_startWith_9_0= 'start_with' ) ) otherlv_10= '=' ( (lv_startWithValue_11_0= RULE_INT ) ) otherlv_12= ';' ) )
            	    {

            	    					getUnorderedGroupHelper().select(grammarAccess.getHdbSequenceElementsAccess().getUnorderedGroup(), 2);
            	    				
            	    // InternalHdbSequence.g:299:8: ({...}? => ( ( (lv_startWith_9_0= 'start_with' ) ) otherlv_10= '=' ( (lv_startWithValue_11_0= RULE_INT ) ) otherlv_12= ';' ) )
            	    // InternalHdbSequence.g:299:9: {...}? => ( ( (lv_startWith_9_0= 'start_with' ) ) otherlv_10= '=' ( (lv_startWithValue_11_0= RULE_INT ) ) otherlv_12= ';' )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleHdbSequenceElements", "true");
            	    }
            	    // InternalHdbSequence.g:299:18: ( ( (lv_startWith_9_0= 'start_with' ) ) otherlv_10= '=' ( (lv_startWithValue_11_0= RULE_INT ) ) otherlv_12= ';' )
            	    // InternalHdbSequence.g:299:19: ( (lv_startWith_9_0= 'start_with' ) ) otherlv_10= '=' ( (lv_startWithValue_11_0= RULE_INT ) ) otherlv_12= ';'
            	    {
            	    // InternalHdbSequence.g:299:19: ( (lv_startWith_9_0= 'start_with' ) )
            	    // InternalHdbSequence.g:300:9: (lv_startWith_9_0= 'start_with' )
            	    {
            	    // InternalHdbSequence.g:300:9: (lv_startWith_9_0= 'start_with' )
            	    // InternalHdbSequence.g:301:10: lv_startWith_9_0= 'start_with'
            	    {
            	    lv_startWith_9_0=(Token)match(input,19,FOLLOW_6); 

            	    										newLeafNode(lv_startWith_9_0, grammarAccess.getHdbSequenceElementsAccess().getStartWithStart_withKeyword_2_0_0());
            	    									

            	    										if (current==null) {
            	    											current = createModelElement(grammarAccess.getHdbSequenceElementsRule());
            	    										}
            	    										setWithLastConsumed(current, "startWith", lv_startWith_9_0, "start_with");
            	    									

            	    }


            	    }

            	    otherlv_10=(Token)match(input,16,FOLLOW_9); 

            	    								newLeafNode(otherlv_10, grammarAccess.getHdbSequenceElementsAccess().getEqualsSignKeyword_2_1());
            	    							
            	    // InternalHdbSequence.g:317:8: ( (lv_startWithValue_11_0= RULE_INT ) )
            	    // InternalHdbSequence.g:318:9: (lv_startWithValue_11_0= RULE_INT )
            	    {
            	    // InternalHdbSequence.g:318:9: (lv_startWithValue_11_0= RULE_INT )
            	    // InternalHdbSequence.g:319:10: lv_startWithValue_11_0= RULE_INT
            	    {
            	    lv_startWithValue_11_0=(Token)match(input,RULE_INT,FOLLOW_7); 

            	    										newLeafNode(lv_startWithValue_11_0, grammarAccess.getHdbSequenceElementsAccess().getStartWithValueINTTerminalRuleCall_2_2_0());
            	    									

            	    										if (current==null) {
            	    											current = createModelElement(grammarAccess.getHdbSequenceElementsRule());
            	    										}
            	    										setWithLastConsumed(
            	    											current,
            	    											"startWithValue",
            	    											lv_startWithValue_11_0,
            	    											"org.eclipse.xtext.common.Terminals.INT");
            	    									

            	    }


            	    }

            	    otherlv_12=(Token)match(input,17,FOLLOW_8); 

            	    								newLeafNode(otherlv_12, grammarAccess.getHdbSequenceElementsAccess().getSemicolonKeyword_2_3());
            	    							

            	    }


            	    }

            	     
            	    					getUnorderedGroupHelper().returnFromSelection(grammarAccess.getHdbSequenceElementsAccess().getUnorderedGroup());
            	    				

            	    }


            	    }


            	    }
            	    break;
            	case 4 :
            	    // InternalHdbSequence.g:345:3: ({...}? => ( ({...}? => ( ( (lv_maxValue_13_0= 'maxvalue' ) ) otherlv_14= '=' ( (lv_maxValueValue_15_0= RULE_INT ) ) otherlv_16= ';' ) ) ) )
            	    {
            	    // InternalHdbSequence.g:345:3: ({...}? => ( ({...}? => ( ( (lv_maxValue_13_0= 'maxvalue' ) ) otherlv_14= '=' ( (lv_maxValueValue_15_0= RULE_INT ) ) otherlv_16= ';' ) ) ) )
            	    // InternalHdbSequence.g:346:4: {...}? => ( ({...}? => ( ( (lv_maxValue_13_0= 'maxvalue' ) ) otherlv_14= '=' ( (lv_maxValueValue_15_0= RULE_INT ) ) otherlv_16= ';' ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getHdbSequenceElementsAccess().getUnorderedGroup(), 3) ) {
            	        throw new FailedPredicateException(input, "ruleHdbSequenceElements", "getUnorderedGroupHelper().canSelect(grammarAccess.getHdbSequenceElementsAccess().getUnorderedGroup(), 3)");
            	    }
            	    // InternalHdbSequence.g:346:113: ( ({...}? => ( ( (lv_maxValue_13_0= 'maxvalue' ) ) otherlv_14= '=' ( (lv_maxValueValue_15_0= RULE_INT ) ) otherlv_16= ';' ) ) )
            	    // InternalHdbSequence.g:347:5: ({...}? => ( ( (lv_maxValue_13_0= 'maxvalue' ) ) otherlv_14= '=' ( (lv_maxValueValue_15_0= RULE_INT ) ) otherlv_16= ';' ) )
            	    {

            	    					getUnorderedGroupHelper().select(grammarAccess.getHdbSequenceElementsAccess().getUnorderedGroup(), 3);
            	    				
            	    // InternalHdbSequence.g:350:8: ({...}? => ( ( (lv_maxValue_13_0= 'maxvalue' ) ) otherlv_14= '=' ( (lv_maxValueValue_15_0= RULE_INT ) ) otherlv_16= ';' ) )
            	    // InternalHdbSequence.g:350:9: {...}? => ( ( (lv_maxValue_13_0= 'maxvalue' ) ) otherlv_14= '=' ( (lv_maxValueValue_15_0= RULE_INT ) ) otherlv_16= ';' )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleHdbSequenceElements", "true");
            	    }
            	    // InternalHdbSequence.g:350:18: ( ( (lv_maxValue_13_0= 'maxvalue' ) ) otherlv_14= '=' ( (lv_maxValueValue_15_0= RULE_INT ) ) otherlv_16= ';' )
            	    // InternalHdbSequence.g:350:19: ( (lv_maxValue_13_0= 'maxvalue' ) ) otherlv_14= '=' ( (lv_maxValueValue_15_0= RULE_INT ) ) otherlv_16= ';'
            	    {
            	    // InternalHdbSequence.g:350:19: ( (lv_maxValue_13_0= 'maxvalue' ) )
            	    // InternalHdbSequence.g:351:9: (lv_maxValue_13_0= 'maxvalue' )
            	    {
            	    // InternalHdbSequence.g:351:9: (lv_maxValue_13_0= 'maxvalue' )
            	    // InternalHdbSequence.g:352:10: lv_maxValue_13_0= 'maxvalue'
            	    {
            	    lv_maxValue_13_0=(Token)match(input,20,FOLLOW_6); 

            	    										newLeafNode(lv_maxValue_13_0, grammarAccess.getHdbSequenceElementsAccess().getMaxValueMaxvalueKeyword_3_0_0());
            	    									

            	    										if (current==null) {
            	    											current = createModelElement(grammarAccess.getHdbSequenceElementsRule());
            	    										}
            	    										setWithLastConsumed(current, "maxValue", lv_maxValue_13_0, "maxvalue");
            	    									

            	    }


            	    }

            	    otherlv_14=(Token)match(input,16,FOLLOW_9); 

            	    								newLeafNode(otherlv_14, grammarAccess.getHdbSequenceElementsAccess().getEqualsSignKeyword_3_1());
            	    							
            	    // InternalHdbSequence.g:368:8: ( (lv_maxValueValue_15_0= RULE_INT ) )
            	    // InternalHdbSequence.g:369:9: (lv_maxValueValue_15_0= RULE_INT )
            	    {
            	    // InternalHdbSequence.g:369:9: (lv_maxValueValue_15_0= RULE_INT )
            	    // InternalHdbSequence.g:370:10: lv_maxValueValue_15_0= RULE_INT
            	    {
            	    lv_maxValueValue_15_0=(Token)match(input,RULE_INT,FOLLOW_7); 

            	    										newLeafNode(lv_maxValueValue_15_0, grammarAccess.getHdbSequenceElementsAccess().getMaxValueValueINTTerminalRuleCall_3_2_0());
            	    									

            	    										if (current==null) {
            	    											current = createModelElement(grammarAccess.getHdbSequenceElementsRule());
            	    										}
            	    										setWithLastConsumed(
            	    											current,
            	    											"maxValueValue",
            	    											lv_maxValueValue_15_0,
            	    											"org.eclipse.xtext.common.Terminals.INT");
            	    									

            	    }


            	    }

            	    otherlv_16=(Token)match(input,17,FOLLOW_8); 

            	    								newLeafNode(otherlv_16, grammarAccess.getHdbSequenceElementsAccess().getSemicolonKeyword_3_3());
            	    							

            	    }


            	    }

            	     
            	    					getUnorderedGroupHelper().returnFromSelection(grammarAccess.getHdbSequenceElementsAccess().getUnorderedGroup());
            	    				

            	    }


            	    }


            	    }
            	    break;
            	case 5 :
            	    // InternalHdbSequence.g:396:3: ({...}? => ( ({...}? => ( ( (lv_noMaxValue_17_0= 'nomaxvalue' ) ) otherlv_18= '=' ( (lv_noMaxValueValue_19_0= RULE_BOOL ) ) otherlv_20= ';' ) ) ) )
            	    {
            	    // InternalHdbSequence.g:396:3: ({...}? => ( ({...}? => ( ( (lv_noMaxValue_17_0= 'nomaxvalue' ) ) otherlv_18= '=' ( (lv_noMaxValueValue_19_0= RULE_BOOL ) ) otherlv_20= ';' ) ) ) )
            	    // InternalHdbSequence.g:397:4: {...}? => ( ({...}? => ( ( (lv_noMaxValue_17_0= 'nomaxvalue' ) ) otherlv_18= '=' ( (lv_noMaxValueValue_19_0= RULE_BOOL ) ) otherlv_20= ';' ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getHdbSequenceElementsAccess().getUnorderedGroup(), 4) ) {
            	        throw new FailedPredicateException(input, "ruleHdbSequenceElements", "getUnorderedGroupHelper().canSelect(grammarAccess.getHdbSequenceElementsAccess().getUnorderedGroup(), 4)");
            	    }
            	    // InternalHdbSequence.g:397:113: ( ({...}? => ( ( (lv_noMaxValue_17_0= 'nomaxvalue' ) ) otherlv_18= '=' ( (lv_noMaxValueValue_19_0= RULE_BOOL ) ) otherlv_20= ';' ) ) )
            	    // InternalHdbSequence.g:398:5: ({...}? => ( ( (lv_noMaxValue_17_0= 'nomaxvalue' ) ) otherlv_18= '=' ( (lv_noMaxValueValue_19_0= RULE_BOOL ) ) otherlv_20= ';' ) )
            	    {

            	    					getUnorderedGroupHelper().select(grammarAccess.getHdbSequenceElementsAccess().getUnorderedGroup(), 4);
            	    				
            	    // InternalHdbSequence.g:401:8: ({...}? => ( ( (lv_noMaxValue_17_0= 'nomaxvalue' ) ) otherlv_18= '=' ( (lv_noMaxValueValue_19_0= RULE_BOOL ) ) otherlv_20= ';' ) )
            	    // InternalHdbSequence.g:401:9: {...}? => ( ( (lv_noMaxValue_17_0= 'nomaxvalue' ) ) otherlv_18= '=' ( (lv_noMaxValueValue_19_0= RULE_BOOL ) ) otherlv_20= ';' )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleHdbSequenceElements", "true");
            	    }
            	    // InternalHdbSequence.g:401:18: ( ( (lv_noMaxValue_17_0= 'nomaxvalue' ) ) otherlv_18= '=' ( (lv_noMaxValueValue_19_0= RULE_BOOL ) ) otherlv_20= ';' )
            	    // InternalHdbSequence.g:401:19: ( (lv_noMaxValue_17_0= 'nomaxvalue' ) ) otherlv_18= '=' ( (lv_noMaxValueValue_19_0= RULE_BOOL ) ) otherlv_20= ';'
            	    {
            	    // InternalHdbSequence.g:401:19: ( (lv_noMaxValue_17_0= 'nomaxvalue' ) )
            	    // InternalHdbSequence.g:402:9: (lv_noMaxValue_17_0= 'nomaxvalue' )
            	    {
            	    // InternalHdbSequence.g:402:9: (lv_noMaxValue_17_0= 'nomaxvalue' )
            	    // InternalHdbSequence.g:403:10: lv_noMaxValue_17_0= 'nomaxvalue'
            	    {
            	    lv_noMaxValue_17_0=(Token)match(input,21,FOLLOW_6); 

            	    										newLeafNode(lv_noMaxValue_17_0, grammarAccess.getHdbSequenceElementsAccess().getNoMaxValueNomaxvalueKeyword_4_0_0());
            	    									

            	    										if (current==null) {
            	    											current = createModelElement(grammarAccess.getHdbSequenceElementsRule());
            	    										}
            	    										setWithLastConsumed(current, "noMaxValue", lv_noMaxValue_17_0, "nomaxvalue");
            	    									

            	    }


            	    }

            	    otherlv_18=(Token)match(input,16,FOLLOW_10); 

            	    								newLeafNode(otherlv_18, grammarAccess.getHdbSequenceElementsAccess().getEqualsSignKeyword_4_1());
            	    							
            	    // InternalHdbSequence.g:419:8: ( (lv_noMaxValueValue_19_0= RULE_BOOL ) )
            	    // InternalHdbSequence.g:420:9: (lv_noMaxValueValue_19_0= RULE_BOOL )
            	    {
            	    // InternalHdbSequence.g:420:9: (lv_noMaxValueValue_19_0= RULE_BOOL )
            	    // InternalHdbSequence.g:421:10: lv_noMaxValueValue_19_0= RULE_BOOL
            	    {
            	    lv_noMaxValueValue_19_0=(Token)match(input,RULE_BOOL,FOLLOW_7); 

            	    										newLeafNode(lv_noMaxValueValue_19_0, grammarAccess.getHdbSequenceElementsAccess().getNoMaxValueValueBOOLTerminalRuleCall_4_2_0());
            	    									

            	    										if (current==null) {
            	    											current = createModelElement(grammarAccess.getHdbSequenceElementsRule());
            	    										}
            	    										setWithLastConsumed(
            	    											current,
            	    											"noMaxValueValue",
            	    											lv_noMaxValueValue_19_0,
            	    											"com.sap.xsk.models.hdbsequence.HdbSequence.BOOL");
            	    									

            	    }


            	    }

            	    otherlv_20=(Token)match(input,17,FOLLOW_8); 

            	    								newLeafNode(otherlv_20, grammarAccess.getHdbSequenceElementsAccess().getSemicolonKeyword_4_3());
            	    							

            	    }


            	    }

            	     
            	    					getUnorderedGroupHelper().returnFromSelection(grammarAccess.getHdbSequenceElementsAccess().getUnorderedGroup());
            	    				

            	    }


            	    }


            	    }
            	    break;
            	case 6 :
            	    // InternalHdbSequence.g:447:3: ({...}? => ( ({...}? => ( ( (lv_minValue_21_0= 'minvalue' ) ) otherlv_22= '=' ( (lv_minVlaueValue_23_0= RULE_INT ) ) otherlv_24= ';' ) ) ) )
            	    {
            	    // InternalHdbSequence.g:447:3: ({...}? => ( ({...}? => ( ( (lv_minValue_21_0= 'minvalue' ) ) otherlv_22= '=' ( (lv_minVlaueValue_23_0= RULE_INT ) ) otherlv_24= ';' ) ) ) )
            	    // InternalHdbSequence.g:448:4: {...}? => ( ({...}? => ( ( (lv_minValue_21_0= 'minvalue' ) ) otherlv_22= '=' ( (lv_minVlaueValue_23_0= RULE_INT ) ) otherlv_24= ';' ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getHdbSequenceElementsAccess().getUnorderedGroup(), 5) ) {
            	        throw new FailedPredicateException(input, "ruleHdbSequenceElements", "getUnorderedGroupHelper().canSelect(grammarAccess.getHdbSequenceElementsAccess().getUnorderedGroup(), 5)");
            	    }
            	    // InternalHdbSequence.g:448:113: ( ({...}? => ( ( (lv_minValue_21_0= 'minvalue' ) ) otherlv_22= '=' ( (lv_minVlaueValue_23_0= RULE_INT ) ) otherlv_24= ';' ) ) )
            	    // InternalHdbSequence.g:449:5: ({...}? => ( ( (lv_minValue_21_0= 'minvalue' ) ) otherlv_22= '=' ( (lv_minVlaueValue_23_0= RULE_INT ) ) otherlv_24= ';' ) )
            	    {

            	    					getUnorderedGroupHelper().select(grammarAccess.getHdbSequenceElementsAccess().getUnorderedGroup(), 5);
            	    				
            	    // InternalHdbSequence.g:452:8: ({...}? => ( ( (lv_minValue_21_0= 'minvalue' ) ) otherlv_22= '=' ( (lv_minVlaueValue_23_0= RULE_INT ) ) otherlv_24= ';' ) )
            	    // InternalHdbSequence.g:452:9: {...}? => ( ( (lv_minValue_21_0= 'minvalue' ) ) otherlv_22= '=' ( (lv_minVlaueValue_23_0= RULE_INT ) ) otherlv_24= ';' )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleHdbSequenceElements", "true");
            	    }
            	    // InternalHdbSequence.g:452:18: ( ( (lv_minValue_21_0= 'minvalue' ) ) otherlv_22= '=' ( (lv_minVlaueValue_23_0= RULE_INT ) ) otherlv_24= ';' )
            	    // InternalHdbSequence.g:452:19: ( (lv_minValue_21_0= 'minvalue' ) ) otherlv_22= '=' ( (lv_minVlaueValue_23_0= RULE_INT ) ) otherlv_24= ';'
            	    {
            	    // InternalHdbSequence.g:452:19: ( (lv_minValue_21_0= 'minvalue' ) )
            	    // InternalHdbSequence.g:453:9: (lv_minValue_21_0= 'minvalue' )
            	    {
            	    // InternalHdbSequence.g:453:9: (lv_minValue_21_0= 'minvalue' )
            	    // InternalHdbSequence.g:454:10: lv_minValue_21_0= 'minvalue'
            	    {
            	    lv_minValue_21_0=(Token)match(input,22,FOLLOW_6); 

            	    										newLeafNode(lv_minValue_21_0, grammarAccess.getHdbSequenceElementsAccess().getMinValueMinvalueKeyword_5_0_0());
            	    									

            	    										if (current==null) {
            	    											current = createModelElement(grammarAccess.getHdbSequenceElementsRule());
            	    										}
            	    										setWithLastConsumed(current, "minValue", lv_minValue_21_0, "minvalue");
            	    									

            	    }


            	    }

            	    otherlv_22=(Token)match(input,16,FOLLOW_9); 

            	    								newLeafNode(otherlv_22, grammarAccess.getHdbSequenceElementsAccess().getEqualsSignKeyword_5_1());
            	    							
            	    // InternalHdbSequence.g:470:8: ( (lv_minVlaueValue_23_0= RULE_INT ) )
            	    // InternalHdbSequence.g:471:9: (lv_minVlaueValue_23_0= RULE_INT )
            	    {
            	    // InternalHdbSequence.g:471:9: (lv_minVlaueValue_23_0= RULE_INT )
            	    // InternalHdbSequence.g:472:10: lv_minVlaueValue_23_0= RULE_INT
            	    {
            	    lv_minVlaueValue_23_0=(Token)match(input,RULE_INT,FOLLOW_7); 

            	    										newLeafNode(lv_minVlaueValue_23_0, grammarAccess.getHdbSequenceElementsAccess().getMinVlaueValueINTTerminalRuleCall_5_2_0());
            	    									

            	    										if (current==null) {
            	    											current = createModelElement(grammarAccess.getHdbSequenceElementsRule());
            	    										}
            	    										setWithLastConsumed(
            	    											current,
            	    											"minVlaueValue",
            	    											lv_minVlaueValue_23_0,
            	    											"org.eclipse.xtext.common.Terminals.INT");
            	    									

            	    }


            	    }

            	    otherlv_24=(Token)match(input,17,FOLLOW_8); 

            	    								newLeafNode(otherlv_24, grammarAccess.getHdbSequenceElementsAccess().getSemicolonKeyword_5_3());
            	    							

            	    }


            	    }

            	     
            	    					getUnorderedGroupHelper().returnFromSelection(grammarAccess.getHdbSequenceElementsAccess().getUnorderedGroup());
            	    				

            	    }


            	    }


            	    }
            	    break;
            	case 7 :
            	    // InternalHdbSequence.g:498:3: ({...}? => ( ({...}? => ( ( (lv_noMinValue_25_0= 'nominvalue' ) ) otherlv_26= '=' ( (lv_noMinValueValue_27_0= RULE_BOOL ) ) otherlv_28= ';' ) ) ) )
            	    {
            	    // InternalHdbSequence.g:498:3: ({...}? => ( ({...}? => ( ( (lv_noMinValue_25_0= 'nominvalue' ) ) otherlv_26= '=' ( (lv_noMinValueValue_27_0= RULE_BOOL ) ) otherlv_28= ';' ) ) ) )
            	    // InternalHdbSequence.g:499:4: {...}? => ( ({...}? => ( ( (lv_noMinValue_25_0= 'nominvalue' ) ) otherlv_26= '=' ( (lv_noMinValueValue_27_0= RULE_BOOL ) ) otherlv_28= ';' ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getHdbSequenceElementsAccess().getUnorderedGroup(), 6) ) {
            	        throw new FailedPredicateException(input, "ruleHdbSequenceElements", "getUnorderedGroupHelper().canSelect(grammarAccess.getHdbSequenceElementsAccess().getUnorderedGroup(), 6)");
            	    }
            	    // InternalHdbSequence.g:499:113: ( ({...}? => ( ( (lv_noMinValue_25_0= 'nominvalue' ) ) otherlv_26= '=' ( (lv_noMinValueValue_27_0= RULE_BOOL ) ) otherlv_28= ';' ) ) )
            	    // InternalHdbSequence.g:500:5: ({...}? => ( ( (lv_noMinValue_25_0= 'nominvalue' ) ) otherlv_26= '=' ( (lv_noMinValueValue_27_0= RULE_BOOL ) ) otherlv_28= ';' ) )
            	    {

            	    					getUnorderedGroupHelper().select(grammarAccess.getHdbSequenceElementsAccess().getUnorderedGroup(), 6);
            	    				
            	    // InternalHdbSequence.g:503:8: ({...}? => ( ( (lv_noMinValue_25_0= 'nominvalue' ) ) otherlv_26= '=' ( (lv_noMinValueValue_27_0= RULE_BOOL ) ) otherlv_28= ';' ) )
            	    // InternalHdbSequence.g:503:9: {...}? => ( ( (lv_noMinValue_25_0= 'nominvalue' ) ) otherlv_26= '=' ( (lv_noMinValueValue_27_0= RULE_BOOL ) ) otherlv_28= ';' )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleHdbSequenceElements", "true");
            	    }
            	    // InternalHdbSequence.g:503:18: ( ( (lv_noMinValue_25_0= 'nominvalue' ) ) otherlv_26= '=' ( (lv_noMinValueValue_27_0= RULE_BOOL ) ) otherlv_28= ';' )
            	    // InternalHdbSequence.g:503:19: ( (lv_noMinValue_25_0= 'nominvalue' ) ) otherlv_26= '=' ( (lv_noMinValueValue_27_0= RULE_BOOL ) ) otherlv_28= ';'
            	    {
            	    // InternalHdbSequence.g:503:19: ( (lv_noMinValue_25_0= 'nominvalue' ) )
            	    // InternalHdbSequence.g:504:9: (lv_noMinValue_25_0= 'nominvalue' )
            	    {
            	    // InternalHdbSequence.g:504:9: (lv_noMinValue_25_0= 'nominvalue' )
            	    // InternalHdbSequence.g:505:10: lv_noMinValue_25_0= 'nominvalue'
            	    {
            	    lv_noMinValue_25_0=(Token)match(input,23,FOLLOW_6); 

            	    										newLeafNode(lv_noMinValue_25_0, grammarAccess.getHdbSequenceElementsAccess().getNoMinValueNominvalueKeyword_6_0_0());
            	    									

            	    										if (current==null) {
            	    											current = createModelElement(grammarAccess.getHdbSequenceElementsRule());
            	    										}
            	    										setWithLastConsumed(current, "noMinValue", lv_noMinValue_25_0, "nominvalue");
            	    									

            	    }


            	    }

            	    otherlv_26=(Token)match(input,16,FOLLOW_10); 

            	    								newLeafNode(otherlv_26, grammarAccess.getHdbSequenceElementsAccess().getEqualsSignKeyword_6_1());
            	    							
            	    // InternalHdbSequence.g:521:8: ( (lv_noMinValueValue_27_0= RULE_BOOL ) )
            	    // InternalHdbSequence.g:522:9: (lv_noMinValueValue_27_0= RULE_BOOL )
            	    {
            	    // InternalHdbSequence.g:522:9: (lv_noMinValueValue_27_0= RULE_BOOL )
            	    // InternalHdbSequence.g:523:10: lv_noMinValueValue_27_0= RULE_BOOL
            	    {
            	    lv_noMinValueValue_27_0=(Token)match(input,RULE_BOOL,FOLLOW_7); 

            	    										newLeafNode(lv_noMinValueValue_27_0, grammarAccess.getHdbSequenceElementsAccess().getNoMinValueValueBOOLTerminalRuleCall_6_2_0());
            	    									

            	    										if (current==null) {
            	    											current = createModelElement(grammarAccess.getHdbSequenceElementsRule());
            	    										}
            	    										setWithLastConsumed(
            	    											current,
            	    											"noMinValueValue",
            	    											lv_noMinValueValue_27_0,
            	    											"com.sap.xsk.models.hdbsequence.HdbSequence.BOOL");
            	    									

            	    }


            	    }

            	    otherlv_28=(Token)match(input,17,FOLLOW_8); 

            	    								newLeafNode(otherlv_28, grammarAccess.getHdbSequenceElementsAccess().getSemicolonKeyword_6_3());
            	    							

            	    }


            	    }

            	     
            	    					getUnorderedGroupHelper().returnFromSelection(grammarAccess.getHdbSequenceElementsAccess().getUnorderedGroup());
            	    				

            	    }


            	    }


            	    }
            	    break;
            	case 8 :
            	    // InternalHdbSequence.g:549:3: ({...}? => ( ({...}? => ( ( (lv_cycles_29_0= 'cycles' ) ) otherlv_30= '=' ( (lv_cyclesValue_31_0= RULE_BOOL ) ) otherlv_32= ';' ) ) ) )
            	    {
            	    // InternalHdbSequence.g:549:3: ({...}? => ( ({...}? => ( ( (lv_cycles_29_0= 'cycles' ) ) otherlv_30= '=' ( (lv_cyclesValue_31_0= RULE_BOOL ) ) otherlv_32= ';' ) ) ) )
            	    // InternalHdbSequence.g:550:4: {...}? => ( ({...}? => ( ( (lv_cycles_29_0= 'cycles' ) ) otherlv_30= '=' ( (lv_cyclesValue_31_0= RULE_BOOL ) ) otherlv_32= ';' ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getHdbSequenceElementsAccess().getUnorderedGroup(), 7) ) {
            	        throw new FailedPredicateException(input, "ruleHdbSequenceElements", "getUnorderedGroupHelper().canSelect(grammarAccess.getHdbSequenceElementsAccess().getUnorderedGroup(), 7)");
            	    }
            	    // InternalHdbSequence.g:550:113: ( ({...}? => ( ( (lv_cycles_29_0= 'cycles' ) ) otherlv_30= '=' ( (lv_cyclesValue_31_0= RULE_BOOL ) ) otherlv_32= ';' ) ) )
            	    // InternalHdbSequence.g:551:5: ({...}? => ( ( (lv_cycles_29_0= 'cycles' ) ) otherlv_30= '=' ( (lv_cyclesValue_31_0= RULE_BOOL ) ) otherlv_32= ';' ) )
            	    {

            	    					getUnorderedGroupHelper().select(grammarAccess.getHdbSequenceElementsAccess().getUnorderedGroup(), 7);
            	    				
            	    // InternalHdbSequence.g:554:8: ({...}? => ( ( (lv_cycles_29_0= 'cycles' ) ) otherlv_30= '=' ( (lv_cyclesValue_31_0= RULE_BOOL ) ) otherlv_32= ';' ) )
            	    // InternalHdbSequence.g:554:9: {...}? => ( ( (lv_cycles_29_0= 'cycles' ) ) otherlv_30= '=' ( (lv_cyclesValue_31_0= RULE_BOOL ) ) otherlv_32= ';' )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleHdbSequenceElements", "true");
            	    }
            	    // InternalHdbSequence.g:554:18: ( ( (lv_cycles_29_0= 'cycles' ) ) otherlv_30= '=' ( (lv_cyclesValue_31_0= RULE_BOOL ) ) otherlv_32= ';' )
            	    // InternalHdbSequence.g:554:19: ( (lv_cycles_29_0= 'cycles' ) ) otherlv_30= '=' ( (lv_cyclesValue_31_0= RULE_BOOL ) ) otherlv_32= ';'
            	    {
            	    // InternalHdbSequence.g:554:19: ( (lv_cycles_29_0= 'cycles' ) )
            	    // InternalHdbSequence.g:555:9: (lv_cycles_29_0= 'cycles' )
            	    {
            	    // InternalHdbSequence.g:555:9: (lv_cycles_29_0= 'cycles' )
            	    // InternalHdbSequence.g:556:10: lv_cycles_29_0= 'cycles'
            	    {
            	    lv_cycles_29_0=(Token)match(input,24,FOLLOW_6); 

            	    										newLeafNode(lv_cycles_29_0, grammarAccess.getHdbSequenceElementsAccess().getCyclesCyclesKeyword_7_0_0());
            	    									

            	    										if (current==null) {
            	    											current = createModelElement(grammarAccess.getHdbSequenceElementsRule());
            	    										}
            	    										setWithLastConsumed(current, "cycles", lv_cycles_29_0, "cycles");
            	    									

            	    }


            	    }

            	    otherlv_30=(Token)match(input,16,FOLLOW_10); 

            	    								newLeafNode(otherlv_30, grammarAccess.getHdbSequenceElementsAccess().getEqualsSignKeyword_7_1());
            	    							
            	    // InternalHdbSequence.g:572:8: ( (lv_cyclesValue_31_0= RULE_BOOL ) )
            	    // InternalHdbSequence.g:573:9: (lv_cyclesValue_31_0= RULE_BOOL )
            	    {
            	    // InternalHdbSequence.g:573:9: (lv_cyclesValue_31_0= RULE_BOOL )
            	    // InternalHdbSequence.g:574:10: lv_cyclesValue_31_0= RULE_BOOL
            	    {
            	    lv_cyclesValue_31_0=(Token)match(input,RULE_BOOL,FOLLOW_7); 

            	    										newLeafNode(lv_cyclesValue_31_0, grammarAccess.getHdbSequenceElementsAccess().getCyclesValueBOOLTerminalRuleCall_7_2_0());
            	    									

            	    										if (current==null) {
            	    											current = createModelElement(grammarAccess.getHdbSequenceElementsRule());
            	    										}
            	    										setWithLastConsumed(
            	    											current,
            	    											"cyclesValue",
            	    											lv_cyclesValue_31_0,
            	    											"com.sap.xsk.models.hdbsequence.HdbSequence.BOOL");
            	    									

            	    }


            	    }

            	    otherlv_32=(Token)match(input,17,FOLLOW_8); 

            	    								newLeafNode(otherlv_32, grammarAccess.getHdbSequenceElementsAccess().getSemicolonKeyword_7_3());
            	    							

            	    }


            	    }

            	     
            	    					getUnorderedGroupHelper().returnFromSelection(grammarAccess.getHdbSequenceElementsAccess().getUnorderedGroup());
            	    				

            	    }


            	    }


            	    }
            	    break;
            	case 9 :
            	    // InternalHdbSequence.g:600:3: ({...}? => ( ({...}? => ( ( (lv_public_33_0= 'public' ) ) otherlv_34= '=' ( (lv_publicValue_35_0= RULE_BOOL ) ) otherlv_36= ';' ) ) ) )
            	    {
            	    // InternalHdbSequence.g:600:3: ({...}? => ( ({...}? => ( ( (lv_public_33_0= 'public' ) ) otherlv_34= '=' ( (lv_publicValue_35_0= RULE_BOOL ) ) otherlv_36= ';' ) ) ) )
            	    // InternalHdbSequence.g:601:4: {...}? => ( ({...}? => ( ( (lv_public_33_0= 'public' ) ) otherlv_34= '=' ( (lv_publicValue_35_0= RULE_BOOL ) ) otherlv_36= ';' ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getHdbSequenceElementsAccess().getUnorderedGroup(), 8) ) {
            	        throw new FailedPredicateException(input, "ruleHdbSequenceElements", "getUnorderedGroupHelper().canSelect(grammarAccess.getHdbSequenceElementsAccess().getUnorderedGroup(), 8)");
            	    }
            	    // InternalHdbSequence.g:601:113: ( ({...}? => ( ( (lv_public_33_0= 'public' ) ) otherlv_34= '=' ( (lv_publicValue_35_0= RULE_BOOL ) ) otherlv_36= ';' ) ) )
            	    // InternalHdbSequence.g:602:5: ({...}? => ( ( (lv_public_33_0= 'public' ) ) otherlv_34= '=' ( (lv_publicValue_35_0= RULE_BOOL ) ) otherlv_36= ';' ) )
            	    {

            	    					getUnorderedGroupHelper().select(grammarAccess.getHdbSequenceElementsAccess().getUnorderedGroup(), 8);
            	    				
            	    // InternalHdbSequence.g:605:8: ({...}? => ( ( (lv_public_33_0= 'public' ) ) otherlv_34= '=' ( (lv_publicValue_35_0= RULE_BOOL ) ) otherlv_36= ';' ) )
            	    // InternalHdbSequence.g:605:9: {...}? => ( ( (lv_public_33_0= 'public' ) ) otherlv_34= '=' ( (lv_publicValue_35_0= RULE_BOOL ) ) otherlv_36= ';' )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleHdbSequenceElements", "true");
            	    }
            	    // InternalHdbSequence.g:605:18: ( ( (lv_public_33_0= 'public' ) ) otherlv_34= '=' ( (lv_publicValue_35_0= RULE_BOOL ) ) otherlv_36= ';' )
            	    // InternalHdbSequence.g:605:19: ( (lv_public_33_0= 'public' ) ) otherlv_34= '=' ( (lv_publicValue_35_0= RULE_BOOL ) ) otherlv_36= ';'
            	    {
            	    // InternalHdbSequence.g:605:19: ( (lv_public_33_0= 'public' ) )
            	    // InternalHdbSequence.g:606:9: (lv_public_33_0= 'public' )
            	    {
            	    // InternalHdbSequence.g:606:9: (lv_public_33_0= 'public' )
            	    // InternalHdbSequence.g:607:10: lv_public_33_0= 'public'
            	    {
            	    lv_public_33_0=(Token)match(input,25,FOLLOW_6); 

            	    										newLeafNode(lv_public_33_0, grammarAccess.getHdbSequenceElementsAccess().getPublicPublicKeyword_8_0_0());
            	    									

            	    										if (current==null) {
            	    											current = createModelElement(grammarAccess.getHdbSequenceElementsRule());
            	    										}
            	    										setWithLastConsumed(current, "public", lv_public_33_0, "public");
            	    									

            	    }


            	    }

            	    otherlv_34=(Token)match(input,16,FOLLOW_10); 

            	    								newLeafNode(otherlv_34, grammarAccess.getHdbSequenceElementsAccess().getEqualsSignKeyword_8_1());
            	    							
            	    // InternalHdbSequence.g:623:8: ( (lv_publicValue_35_0= RULE_BOOL ) )
            	    // InternalHdbSequence.g:624:9: (lv_publicValue_35_0= RULE_BOOL )
            	    {
            	    // InternalHdbSequence.g:624:9: (lv_publicValue_35_0= RULE_BOOL )
            	    // InternalHdbSequence.g:625:10: lv_publicValue_35_0= RULE_BOOL
            	    {
            	    lv_publicValue_35_0=(Token)match(input,RULE_BOOL,FOLLOW_7); 

            	    										newLeafNode(lv_publicValue_35_0, grammarAccess.getHdbSequenceElementsAccess().getPublicValueBOOLTerminalRuleCall_8_2_0());
            	    									

            	    										if (current==null) {
            	    											current = createModelElement(grammarAccess.getHdbSequenceElementsRule());
            	    										}
            	    										setWithLastConsumed(
            	    											current,
            	    											"publicValue",
            	    											lv_publicValue_35_0,
            	    											"com.sap.xsk.models.hdbsequence.HdbSequence.BOOL");
            	    									

            	    }


            	    }

            	    otherlv_36=(Token)match(input,17,FOLLOW_8); 

            	    								newLeafNode(otherlv_36, grammarAccess.getHdbSequenceElementsAccess().getSemicolonKeyword_8_3());
            	    							

            	    }


            	    }

            	     
            	    					getUnorderedGroupHelper().returnFromSelection(grammarAccess.getHdbSequenceElementsAccess().getUnorderedGroup());
            	    				

            	    }


            	    }


            	    }
            	    break;
            	case 10 :
            	    // InternalHdbSequence.g:651:3: ({...}? => ( ({...}? => ( ( (lv_dependsOnTable_37_0= 'depends_on_table' ) ) otherlv_38= '=' ( (lv_dependsOnTableValue_39_0= RULE_STRING ) ) otherlv_40= ';' ) ) ) )
            	    {
            	    // InternalHdbSequence.g:651:3: ({...}? => ( ({...}? => ( ( (lv_dependsOnTable_37_0= 'depends_on_table' ) ) otherlv_38= '=' ( (lv_dependsOnTableValue_39_0= RULE_STRING ) ) otherlv_40= ';' ) ) ) )
            	    // InternalHdbSequence.g:652:4: {...}? => ( ({...}? => ( ( (lv_dependsOnTable_37_0= 'depends_on_table' ) ) otherlv_38= '=' ( (lv_dependsOnTableValue_39_0= RULE_STRING ) ) otherlv_40= ';' ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getHdbSequenceElementsAccess().getUnorderedGroup(), 9) ) {
            	        throw new FailedPredicateException(input, "ruleHdbSequenceElements", "getUnorderedGroupHelper().canSelect(grammarAccess.getHdbSequenceElementsAccess().getUnorderedGroup(), 9)");
            	    }
            	    // InternalHdbSequence.g:652:113: ( ({...}? => ( ( (lv_dependsOnTable_37_0= 'depends_on_table' ) ) otherlv_38= '=' ( (lv_dependsOnTableValue_39_0= RULE_STRING ) ) otherlv_40= ';' ) ) )
            	    // InternalHdbSequence.g:653:5: ({...}? => ( ( (lv_dependsOnTable_37_0= 'depends_on_table' ) ) otherlv_38= '=' ( (lv_dependsOnTableValue_39_0= RULE_STRING ) ) otherlv_40= ';' ) )
            	    {

            	    					getUnorderedGroupHelper().select(grammarAccess.getHdbSequenceElementsAccess().getUnorderedGroup(), 9);
            	    				
            	    // InternalHdbSequence.g:656:8: ({...}? => ( ( (lv_dependsOnTable_37_0= 'depends_on_table' ) ) otherlv_38= '=' ( (lv_dependsOnTableValue_39_0= RULE_STRING ) ) otherlv_40= ';' ) )
            	    // InternalHdbSequence.g:656:9: {...}? => ( ( (lv_dependsOnTable_37_0= 'depends_on_table' ) ) otherlv_38= '=' ( (lv_dependsOnTableValue_39_0= RULE_STRING ) ) otherlv_40= ';' )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleHdbSequenceElements", "true");
            	    }
            	    // InternalHdbSequence.g:656:18: ( ( (lv_dependsOnTable_37_0= 'depends_on_table' ) ) otherlv_38= '=' ( (lv_dependsOnTableValue_39_0= RULE_STRING ) ) otherlv_40= ';' )
            	    // InternalHdbSequence.g:656:19: ( (lv_dependsOnTable_37_0= 'depends_on_table' ) ) otherlv_38= '=' ( (lv_dependsOnTableValue_39_0= RULE_STRING ) ) otherlv_40= ';'
            	    {
            	    // InternalHdbSequence.g:656:19: ( (lv_dependsOnTable_37_0= 'depends_on_table' ) )
            	    // InternalHdbSequence.g:657:9: (lv_dependsOnTable_37_0= 'depends_on_table' )
            	    {
            	    // InternalHdbSequence.g:657:9: (lv_dependsOnTable_37_0= 'depends_on_table' )
            	    // InternalHdbSequence.g:658:10: lv_dependsOnTable_37_0= 'depends_on_table'
            	    {
            	    lv_dependsOnTable_37_0=(Token)match(input,26,FOLLOW_6); 

            	    										newLeafNode(lv_dependsOnTable_37_0, grammarAccess.getHdbSequenceElementsAccess().getDependsOnTableDepends_on_tableKeyword_9_0_0());
            	    									

            	    										if (current==null) {
            	    											current = createModelElement(grammarAccess.getHdbSequenceElementsRule());
            	    										}
            	    										setWithLastConsumed(current, "dependsOnTable", lv_dependsOnTable_37_0, "depends_on_table");
            	    									

            	    }


            	    }

            	    otherlv_38=(Token)match(input,16,FOLLOW_5); 

            	    								newLeafNode(otherlv_38, grammarAccess.getHdbSequenceElementsAccess().getEqualsSignKeyword_9_1());
            	    							
            	    // InternalHdbSequence.g:674:8: ( (lv_dependsOnTableValue_39_0= RULE_STRING ) )
            	    // InternalHdbSequence.g:675:9: (lv_dependsOnTableValue_39_0= RULE_STRING )
            	    {
            	    // InternalHdbSequence.g:675:9: (lv_dependsOnTableValue_39_0= RULE_STRING )
            	    // InternalHdbSequence.g:676:10: lv_dependsOnTableValue_39_0= RULE_STRING
            	    {
            	    lv_dependsOnTableValue_39_0=(Token)match(input,RULE_STRING,FOLLOW_7); 

            	    										newLeafNode(lv_dependsOnTableValue_39_0, grammarAccess.getHdbSequenceElementsAccess().getDependsOnTableValueSTRINGTerminalRuleCall_9_2_0());
            	    									

            	    										if (current==null) {
            	    											current = createModelElement(grammarAccess.getHdbSequenceElementsRule());
            	    										}
            	    										setWithLastConsumed(
            	    											current,
            	    											"dependsOnTableValue",
            	    											lv_dependsOnTableValue_39_0,
            	    											"org.eclipse.xtext.common.Terminals.STRING");
            	    									

            	    }


            	    }

            	    otherlv_40=(Token)match(input,17,FOLLOW_8); 

            	    								newLeafNode(otherlv_40, grammarAccess.getHdbSequenceElementsAccess().getSemicolonKeyword_9_3());
            	    							

            	    }


            	    }

            	     
            	    					getUnorderedGroupHelper().returnFromSelection(grammarAccess.getHdbSequenceElementsAccess().getUnorderedGroup());
            	    				

            	    }


            	    }


            	    }
            	    break;
            	case 11 :
            	    // InternalHdbSequence.g:702:3: ({...}? => ( ({...}? => ( ( (lv_dependsOnView_41_0= 'depends_on_view' ) ) otherlv_42= '=' ( (lv_dependsOnViewValue_43_0= RULE_STRING ) ) otherlv_44= ';' ) ) ) )
            	    {
            	    // InternalHdbSequence.g:702:3: ({...}? => ( ({...}? => ( ( (lv_dependsOnView_41_0= 'depends_on_view' ) ) otherlv_42= '=' ( (lv_dependsOnViewValue_43_0= RULE_STRING ) ) otherlv_44= ';' ) ) ) )
            	    // InternalHdbSequence.g:703:4: {...}? => ( ({...}? => ( ( (lv_dependsOnView_41_0= 'depends_on_view' ) ) otherlv_42= '=' ( (lv_dependsOnViewValue_43_0= RULE_STRING ) ) otherlv_44= ';' ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getHdbSequenceElementsAccess().getUnorderedGroup(), 10) ) {
            	        throw new FailedPredicateException(input, "ruleHdbSequenceElements", "getUnorderedGroupHelper().canSelect(grammarAccess.getHdbSequenceElementsAccess().getUnorderedGroup(), 10)");
            	    }
            	    // InternalHdbSequence.g:703:114: ( ({...}? => ( ( (lv_dependsOnView_41_0= 'depends_on_view' ) ) otherlv_42= '=' ( (lv_dependsOnViewValue_43_0= RULE_STRING ) ) otherlv_44= ';' ) ) )
            	    // InternalHdbSequence.g:704:5: ({...}? => ( ( (lv_dependsOnView_41_0= 'depends_on_view' ) ) otherlv_42= '=' ( (lv_dependsOnViewValue_43_0= RULE_STRING ) ) otherlv_44= ';' ) )
            	    {

            	    					getUnorderedGroupHelper().select(grammarAccess.getHdbSequenceElementsAccess().getUnorderedGroup(), 10);
            	    				
            	    // InternalHdbSequence.g:707:8: ({...}? => ( ( (lv_dependsOnView_41_0= 'depends_on_view' ) ) otherlv_42= '=' ( (lv_dependsOnViewValue_43_0= RULE_STRING ) ) otherlv_44= ';' ) )
            	    // InternalHdbSequence.g:707:9: {...}? => ( ( (lv_dependsOnView_41_0= 'depends_on_view' ) ) otherlv_42= '=' ( (lv_dependsOnViewValue_43_0= RULE_STRING ) ) otherlv_44= ';' )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleHdbSequenceElements", "true");
            	    }
            	    // InternalHdbSequence.g:707:18: ( ( (lv_dependsOnView_41_0= 'depends_on_view' ) ) otherlv_42= '=' ( (lv_dependsOnViewValue_43_0= RULE_STRING ) ) otherlv_44= ';' )
            	    // InternalHdbSequence.g:707:19: ( (lv_dependsOnView_41_0= 'depends_on_view' ) ) otherlv_42= '=' ( (lv_dependsOnViewValue_43_0= RULE_STRING ) ) otherlv_44= ';'
            	    {
            	    // InternalHdbSequence.g:707:19: ( (lv_dependsOnView_41_0= 'depends_on_view' ) )
            	    // InternalHdbSequence.g:708:9: (lv_dependsOnView_41_0= 'depends_on_view' )
            	    {
            	    // InternalHdbSequence.g:708:9: (lv_dependsOnView_41_0= 'depends_on_view' )
            	    // InternalHdbSequence.g:709:10: lv_dependsOnView_41_0= 'depends_on_view'
            	    {
            	    lv_dependsOnView_41_0=(Token)match(input,27,FOLLOW_6); 

            	    										newLeafNode(lv_dependsOnView_41_0, grammarAccess.getHdbSequenceElementsAccess().getDependsOnViewDepends_on_viewKeyword_10_0_0());
            	    									

            	    										if (current==null) {
            	    											current = createModelElement(grammarAccess.getHdbSequenceElementsRule());
            	    										}
            	    										setWithLastConsumed(current, "dependsOnView", lv_dependsOnView_41_0, "depends_on_view");
            	    									

            	    }


            	    }

            	    otherlv_42=(Token)match(input,16,FOLLOW_5); 

            	    								newLeafNode(otherlv_42, grammarAccess.getHdbSequenceElementsAccess().getEqualsSignKeyword_10_1());
            	    							
            	    // InternalHdbSequence.g:725:8: ( (lv_dependsOnViewValue_43_0= RULE_STRING ) )
            	    // InternalHdbSequence.g:726:9: (lv_dependsOnViewValue_43_0= RULE_STRING )
            	    {
            	    // InternalHdbSequence.g:726:9: (lv_dependsOnViewValue_43_0= RULE_STRING )
            	    // InternalHdbSequence.g:727:10: lv_dependsOnViewValue_43_0= RULE_STRING
            	    {
            	    lv_dependsOnViewValue_43_0=(Token)match(input,RULE_STRING,FOLLOW_7); 

            	    										newLeafNode(lv_dependsOnViewValue_43_0, grammarAccess.getHdbSequenceElementsAccess().getDependsOnViewValueSTRINGTerminalRuleCall_10_2_0());
            	    									

            	    										if (current==null) {
            	    											current = createModelElement(grammarAccess.getHdbSequenceElementsRule());
            	    										}
            	    										setWithLastConsumed(
            	    											current,
            	    											"dependsOnViewValue",
            	    											lv_dependsOnViewValue_43_0,
            	    											"org.eclipse.xtext.common.Terminals.STRING");
            	    									

            	    }


            	    }

            	    otherlv_44=(Token)match(input,17,FOLLOW_8); 

            	    								newLeafNode(otherlv_44, grammarAccess.getHdbSequenceElementsAccess().getSemicolonKeyword_10_3());
            	    							

            	    }


            	    }

            	     
            	    					getUnorderedGroupHelper().returnFromSelection(grammarAccess.getHdbSequenceElementsAccess().getUnorderedGroup());
            	    				

            	    }


            	    }


            	    }
            	    break;
            	case 12 :
            	    // InternalHdbSequence.g:753:3: ({...}? => ( ({...}? => (otherlv_45= 'depends_on' otherlv_46= '=' ( (lv_value_47_0= ruleListString ) ) otherlv_48= ';' ) ) ) )
            	    {
            	    // InternalHdbSequence.g:753:3: ({...}? => ( ({...}? => (otherlv_45= 'depends_on' otherlv_46= '=' ( (lv_value_47_0= ruleListString ) ) otherlv_48= ';' ) ) ) )
            	    // InternalHdbSequence.g:754:4: {...}? => ( ({...}? => (otherlv_45= 'depends_on' otherlv_46= '=' ( (lv_value_47_0= ruleListString ) ) otherlv_48= ';' ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getHdbSequenceElementsAccess().getUnorderedGroup(), 11) ) {
            	        throw new FailedPredicateException(input, "ruleHdbSequenceElements", "getUnorderedGroupHelper().canSelect(grammarAccess.getHdbSequenceElementsAccess().getUnorderedGroup(), 11)");
            	    }
            	    // InternalHdbSequence.g:754:114: ( ({...}? => (otherlv_45= 'depends_on' otherlv_46= '=' ( (lv_value_47_0= ruleListString ) ) otherlv_48= ';' ) ) )
            	    // InternalHdbSequence.g:755:5: ({...}? => (otherlv_45= 'depends_on' otherlv_46= '=' ( (lv_value_47_0= ruleListString ) ) otherlv_48= ';' ) )
            	    {

            	    					getUnorderedGroupHelper().select(grammarAccess.getHdbSequenceElementsAccess().getUnorderedGroup(), 11);
            	    				
            	    // InternalHdbSequence.g:758:8: ({...}? => (otherlv_45= 'depends_on' otherlv_46= '=' ( (lv_value_47_0= ruleListString ) ) otherlv_48= ';' ) )
            	    // InternalHdbSequence.g:758:9: {...}? => (otherlv_45= 'depends_on' otherlv_46= '=' ( (lv_value_47_0= ruleListString ) ) otherlv_48= ';' )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleHdbSequenceElements", "true");
            	    }
            	    // InternalHdbSequence.g:758:18: (otherlv_45= 'depends_on' otherlv_46= '=' ( (lv_value_47_0= ruleListString ) ) otherlv_48= ';' )
            	    // InternalHdbSequence.g:758:19: otherlv_45= 'depends_on' otherlv_46= '=' ( (lv_value_47_0= ruleListString ) ) otherlv_48= ';'
            	    {
            	    otherlv_45=(Token)match(input,28,FOLLOW_6); 

            	    								newLeafNode(otherlv_45, grammarAccess.getHdbSequenceElementsAccess().getDepends_onKeyword_11_0());
            	    							
            	    otherlv_46=(Token)match(input,16,FOLLOW_11); 

            	    								newLeafNode(otherlv_46, grammarAccess.getHdbSequenceElementsAccess().getEqualsSignKeyword_11_1());
            	    							
            	    // InternalHdbSequence.g:766:8: ( (lv_value_47_0= ruleListString ) )
            	    // InternalHdbSequence.g:767:9: (lv_value_47_0= ruleListString )
            	    {
            	    // InternalHdbSequence.g:767:9: (lv_value_47_0= ruleListString )
            	    // InternalHdbSequence.g:768:10: lv_value_47_0= ruleListString
            	    {

            	    										newCompositeNode(grammarAccess.getHdbSequenceElementsAccess().getValueListStringParserRuleCall_11_2_0());
            	    									
            	    pushFollow(FOLLOW_7);
            	    lv_value_47_0=ruleListString();

            	    state._fsp--;


            	    										if (current==null) {
            	    											current = createModelElementForParent(grammarAccess.getHdbSequenceElementsRule());
            	    										}
            	    										set(
            	    											current,
            	    											"value",
            	    											lv_value_47_0,
            	    											"com.sap.xsk.models.hdbsequence.HdbSequence.ListString");
            	    										afterParserOrEnumRuleCall();
            	    									

            	    }


            	    }

            	    otherlv_48=(Token)match(input,17,FOLLOW_8); 

            	    								newLeafNode(otherlv_48, grammarAccess.getHdbSequenceElementsAccess().getSemicolonKeyword_11_3());
            	    							

            	    }


            	    }

            	     
            	    					getUnorderedGroupHelper().returnFromSelection(grammarAccess.getHdbSequenceElementsAccess().getUnorderedGroup());
            	    				

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

            if ( ! getUnorderedGroupHelper().canLeave(grammarAccess.getHdbSequenceElementsAccess().getUnorderedGroup()) ) {
                throw new FailedPredicateException(input, "ruleHdbSequenceElements", "getUnorderedGroupHelper().canLeave(grammarAccess.getHdbSequenceElementsAccess().getUnorderedGroup())");
            }

            }


            }

             
            			  getUnorderedGroupHelper().leave(grammarAccess.getHdbSequenceElementsAccess().getUnorderedGroup());
            			

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
    // $ANTLR end "ruleHdbSequenceElements"

    // Delegated rules


    protected DFA3 dfa3 = new DFA3(this);
    static final String dfa_1s = "\16\uffff";
    static final String dfa_2s = "\1\1\15\uffff";
    static final String dfa_3s = "\1\17\15\uffff";
    static final String dfa_4s = "\1\34\15\uffff";
    static final String dfa_5s = "\1\uffff\1\15\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14";
    static final String dfa_6s = "\1\0\15\uffff}>";
    static final String[] dfa_7s = {
            "\1\2\2\uffff\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1\15",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] dfa_1 = DFA.unpackEncodedString(dfa_1s);
    static final short[] dfa_2 = DFA.unpackEncodedString(dfa_2s);
    static final char[] dfa_3 = DFA.unpackEncodedStringToUnsignedChars(dfa_3s);
    static final char[] dfa_4 = DFA.unpackEncodedStringToUnsignedChars(dfa_4s);
    static final short[] dfa_5 = DFA.unpackEncodedString(dfa_5s);
    static final short[] dfa_6 = DFA.unpackEncodedString(dfa_6s);
    static final short[][] dfa_7 = unpackEncodedStringArray(dfa_7s);

    class DFA3 extends DFA {

        public DFA3(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 3;
            this.eot = dfa_1;
            this.eof = dfa_2;
            this.min = dfa_3;
            this.max = dfa_4;
            this.accept = dfa_5;
            this.special = dfa_6;
            this.transition = dfa_7;
        }
        public String getDescription() {
            return "()+ loopback of 191:5: ( ({...}? => ( ({...}? => ( ( (lv_schema_1_0= 'schema' ) ) otherlv_2= '=' ( (lv_schemaValue_3_0= RULE_STRING ) ) otherlv_4= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_incrementBy_5_0= 'increment_by' ) ) otherlv_6= '=' ( (lv_incrementByValue_7_0= RULE_INT ) ) otherlv_8= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_startWith_9_0= 'start_with' ) ) otherlv_10= '=' ( (lv_startWithValue_11_0= RULE_INT ) ) otherlv_12= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_maxValue_13_0= 'maxvalue' ) ) otherlv_14= '=' ( (lv_maxValueValue_15_0= RULE_INT ) ) otherlv_16= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_noMaxValue_17_0= 'nomaxvalue' ) ) otherlv_18= '=' ( (lv_noMaxValueValue_19_0= RULE_BOOL ) ) otherlv_20= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_minValue_21_0= 'minvalue' ) ) otherlv_22= '=' ( (lv_minVlaueValue_23_0= RULE_INT ) ) otherlv_24= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_noMinValue_25_0= 'nominvalue' ) ) otherlv_26= '=' ( (lv_noMinValueValue_27_0= RULE_BOOL ) ) otherlv_28= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_cycles_29_0= 'cycles' ) ) otherlv_30= '=' ( (lv_cyclesValue_31_0= RULE_BOOL ) ) otherlv_32= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_public_33_0= 'public' ) ) otherlv_34= '=' ( (lv_publicValue_35_0= RULE_BOOL ) ) otherlv_36= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_dependsOnTable_37_0= 'depends_on_table' ) ) otherlv_38= '=' ( (lv_dependsOnTableValue_39_0= RULE_STRING ) ) otherlv_40= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_dependsOnView_41_0= 'depends_on_view' ) ) otherlv_42= '=' ( (lv_dependsOnViewValue_43_0= RULE_STRING ) ) otherlv_44= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_45= 'depends_on' otherlv_46= '=' ( (lv_value_47_0= ruleListString ) ) otherlv_48= ';' ) ) ) ) )+";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA3_0 = input.LA(1);

                         
                        int index3_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA3_0==EOF) ) {s = 1;}

                        else if ( LA3_0 == 15 && getUnorderedGroupHelper().canSelect(grammarAccess.getHdbSequenceElementsAccess().getUnorderedGroup(), 0) ) {s = 2;}

                        else if ( LA3_0 == 18 && getUnorderedGroupHelper().canSelect(grammarAccess.getHdbSequenceElementsAccess().getUnorderedGroup(), 1) ) {s = 3;}

                        else if ( LA3_0 == 19 && getUnorderedGroupHelper().canSelect(grammarAccess.getHdbSequenceElementsAccess().getUnorderedGroup(), 2) ) {s = 4;}

                        else if ( LA3_0 == 20 && getUnorderedGroupHelper().canSelect(grammarAccess.getHdbSequenceElementsAccess().getUnorderedGroup(), 3) ) {s = 5;}

                        else if ( LA3_0 == 21 && getUnorderedGroupHelper().canSelect(grammarAccess.getHdbSequenceElementsAccess().getUnorderedGroup(), 4) ) {s = 6;}

                        else if ( LA3_0 == 22 && getUnorderedGroupHelper().canSelect(grammarAccess.getHdbSequenceElementsAccess().getUnorderedGroup(), 5) ) {s = 7;}

                        else if ( LA3_0 == 23 && getUnorderedGroupHelper().canSelect(grammarAccess.getHdbSequenceElementsAccess().getUnorderedGroup(), 6) ) {s = 8;}

                        else if ( LA3_0 == 24 && getUnorderedGroupHelper().canSelect(grammarAccess.getHdbSequenceElementsAccess().getUnorderedGroup(), 7) ) {s = 9;}

                        else if ( LA3_0 == 25 && getUnorderedGroupHelper().canSelect(grammarAccess.getHdbSequenceElementsAccess().getUnorderedGroup(), 8) ) {s = 10;}

                        else if ( LA3_0 == 26 && getUnorderedGroupHelper().canSelect(grammarAccess.getHdbSequenceElementsAccess().getUnorderedGroup(), 9) ) {s = 11;}

                        else if ( LA3_0 == 27 && getUnorderedGroupHelper().canSelect(grammarAccess.getHdbSequenceElementsAccess().getUnorderedGroup(), 10) ) {s = 12;}

                        else if ( LA3_0 == 28 && getUnorderedGroupHelper().canSelect(grammarAccess.getHdbSequenceElementsAccess().getUnorderedGroup(), 11) ) {s = 13;}

                         
                        input.seek(index3_0);
                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 3, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000004010L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x000000001FFC8002L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000001000L});

}
