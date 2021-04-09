package com.sap.xsk.models.xsodata.parser.antlr.internal;

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import com.sap.xsk.models.xsodata.services.XSODataGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalXSODataParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_INT", "RULE_STRING", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'service'", "'namespace'", "'{'", "'}'", "'.'", "'::'", "'as'", "';'", "'navigates'", "'('", "')'", "'create'", "'events'", "'before'", "'.xsjs'", "':'", "'.xsjslib'", "'delete'", "'using'", "'update'", "'forbidden'", "'association'", "'principal'", "'multiplicity'", "'dependent'", "'*'", "'1'"
    };
    public static final int RULE_STRING=6;
    public static final int RULE_SL_COMMENT=8;
    public static final int T__19=19;
    public static final int T__15=15;
    public static final int T__37=37;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__11=11;
    public static final int T__33=33;
    public static final int T__12=12;
    public static final int T__34=34;
    public static final int T__13=13;
    public static final int T__35=35;
    public static final int T__14=14;
    public static final int T__36=36;
    public static final int EOF=-1;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int RULE_ID=4;
    public static final int RULE_WS=9;
    public static final int RULE_ANY_OTHER=10;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int RULE_INT=5;
    public static final int T__29=29;
    public static final int T__22=22;
    public static final int RULE_ML_COMMENT=7;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__20=20;
    public static final int T__21=21;

    // delegates
    // delegators


        public InternalXSODataParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalXSODataParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalXSODataParser.tokenNames; }
    public String getGrammarFileName() { return "InternalXSOData.g"; }



     	private XSODataGrammarAccess grammarAccess;

        public InternalXSODataParser(TokenStream input, XSODataGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }

        @Override
        protected String getFirstRuleName() {
        	return "XSOData";
       	}

       	@Override
       	protected XSODataGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}




    // $ANTLR start "entryRuleXSOData"
    // InternalXSOData.g:64:1: entryRuleXSOData returns [EObject current=null] : iv_ruleXSOData= ruleXSOData EOF ;
    public final EObject entryRuleXSOData() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXSOData = null;


        try {
            // InternalXSOData.g:64:48: (iv_ruleXSOData= ruleXSOData EOF )
            // InternalXSOData.g:65:2: iv_ruleXSOData= ruleXSOData EOF
            {
             newCompositeNode(grammarAccess.getXSODataRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleXSOData=ruleXSOData();

            state._fsp--;

             current =iv_ruleXSOData; 
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
    // $ANTLR end "entryRuleXSOData"


    // $ANTLR start "ruleXSOData"
    // InternalXSOData.g:71:1: ruleXSOData returns [EObject current=null] : ( (lv_elements_0_0= ruleType ) )* ;
    public final EObject ruleXSOData() throws RecognitionException {
        EObject current = null;

        EObject lv_elements_0_0 = null;



        	enterRule();

        try {
            // InternalXSOData.g:77:2: ( ( (lv_elements_0_0= ruleType ) )* )
            // InternalXSOData.g:78:2: ( (lv_elements_0_0= ruleType ) )*
            {
            // InternalXSOData.g:78:2: ( (lv_elements_0_0= ruleType ) )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==11) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // InternalXSOData.g:79:3: (lv_elements_0_0= ruleType )
            	    {
            	    // InternalXSOData.g:79:3: (lv_elements_0_0= ruleType )
            	    // InternalXSOData.g:80:4: lv_elements_0_0= ruleType
            	    {

            	    				newCompositeNode(grammarAccess.getXSODataAccess().getElementsTypeParserRuleCall_0());
            	    			
            	    pushFollow(FOLLOW_3);
            	    lv_elements_0_0=ruleType();

            	    state._fsp--;


            	    				if (current==null) {
            	    					current = createModelElementForParent(grammarAccess.getXSODataRule());
            	    				}
            	    				add(
            	    					current,
            	    					"elements",
            	    					lv_elements_0_0,
            	    					"com.sap.xsk.models.xsodata.XSOData.Type");
            	    				afterParserOrEnumRuleCall();
            	    			

            	    }


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


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
    // $ANTLR end "ruleXSOData"


    // $ANTLR start "entryRuleType"
    // InternalXSOData.g:100:1: entryRuleType returns [EObject current=null] : iv_ruleType= ruleType EOF ;
    public final EObject entryRuleType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleType = null;


        try {
            // InternalXSOData.g:100:45: (iv_ruleType= ruleType EOF )
            // InternalXSOData.g:101:2: iv_ruleType= ruleType EOF
            {
             newCompositeNode(grammarAccess.getTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleType=ruleType();

            state._fsp--;

             current =iv_ruleType; 
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
    // $ANTLR end "entryRuleType"


    // $ANTLR start "ruleType"
    // InternalXSOData.g:107:1: ruleType returns [EObject current=null] : this_Service_0= ruleService ;
    public final EObject ruleType() throws RecognitionException {
        EObject current = null;

        EObject this_Service_0 = null;



        	enterRule();

        try {
            // InternalXSOData.g:113:2: (this_Service_0= ruleService )
            // InternalXSOData.g:114:2: this_Service_0= ruleService
            {

            		newCompositeNode(grammarAccess.getTypeAccess().getServiceParserRuleCall());
            	
            pushFollow(FOLLOW_2);
            this_Service_0=ruleService();

            state._fsp--;


            		current = this_Service_0;
            		afterParserOrEnumRuleCall();
            	

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
    // $ANTLR end "ruleType"


    // $ANTLR start "entryRuleService"
    // InternalXSOData.g:125:1: entryRuleService returns [EObject current=null] : iv_ruleService= ruleService EOF ;
    public final EObject entryRuleService() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleService = null;


        try {
            // InternalXSOData.g:125:48: (iv_ruleService= ruleService EOF )
            // InternalXSOData.g:126:2: iv_ruleService= ruleService EOF
            {
             newCompositeNode(grammarAccess.getServiceRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleService=ruleService();

            state._fsp--;

             current =iv_ruleService; 
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
    // $ANTLR end "entryRuleService"


    // $ANTLR start "ruleService"
    // InternalXSOData.g:132:1: ruleService returns [EObject current=null] : (otherlv_0= 'service' otherlv_1= 'namespace' ( (lv_name_2_0= ruleQualifiedName ) ) otherlv_3= '{' ( (lv_entities_4_0= ruleEntity ) )* ( (lv_associations_5_0= ruleAssociation ) )* otherlv_6= '}' ) ;
    public final EObject ruleService() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_6=null;
        AntlrDatatypeRuleToken lv_name_2_0 = null;

        EObject lv_entities_4_0 = null;

        EObject lv_associations_5_0 = null;



        	enterRule();

        try {
            // InternalXSOData.g:138:2: ( (otherlv_0= 'service' otherlv_1= 'namespace' ( (lv_name_2_0= ruleQualifiedName ) ) otherlv_3= '{' ( (lv_entities_4_0= ruleEntity ) )* ( (lv_associations_5_0= ruleAssociation ) )* otherlv_6= '}' ) )
            // InternalXSOData.g:139:2: (otherlv_0= 'service' otherlv_1= 'namespace' ( (lv_name_2_0= ruleQualifiedName ) ) otherlv_3= '{' ( (lv_entities_4_0= ruleEntity ) )* ( (lv_associations_5_0= ruleAssociation ) )* otherlv_6= '}' )
            {
            // InternalXSOData.g:139:2: (otherlv_0= 'service' otherlv_1= 'namespace' ( (lv_name_2_0= ruleQualifiedName ) ) otherlv_3= '{' ( (lv_entities_4_0= ruleEntity ) )* ( (lv_associations_5_0= ruleAssociation ) )* otherlv_6= '}' )
            // InternalXSOData.g:140:3: otherlv_0= 'service' otherlv_1= 'namespace' ( (lv_name_2_0= ruleQualifiedName ) ) otherlv_3= '{' ( (lv_entities_4_0= ruleEntity ) )* ( (lv_associations_5_0= ruleAssociation ) )* otherlv_6= '}'
            {
            otherlv_0=(Token)match(input,11,FOLLOW_4); 

            			newLeafNode(otherlv_0, grammarAccess.getServiceAccess().getServiceKeyword_0());
            		
            otherlv_1=(Token)match(input,12,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getServiceAccess().getNamespaceKeyword_1());
            		
            // InternalXSOData.g:148:3: ( (lv_name_2_0= ruleQualifiedName ) )
            // InternalXSOData.g:149:4: (lv_name_2_0= ruleQualifiedName )
            {
            // InternalXSOData.g:149:4: (lv_name_2_0= ruleQualifiedName )
            // InternalXSOData.g:150:5: lv_name_2_0= ruleQualifiedName
            {

            					newCompositeNode(grammarAccess.getServiceAccess().getNameQualifiedNameParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_6);
            lv_name_2_0=ruleQualifiedName();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getServiceRule());
            					}
            					set(
            						current,
            						"name",
            						lv_name_2_0,
            						"com.sap.xsk.models.xsodata.XSOData.QualifiedName");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_3=(Token)match(input,13,FOLLOW_7); 

            			newLeafNode(otherlv_3, grammarAccess.getServiceAccess().getLeftCurlyBracketKeyword_3());
            		
            // InternalXSOData.g:171:3: ( (lv_entities_4_0= ruleEntity ) )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==RULE_ID) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // InternalXSOData.g:172:4: (lv_entities_4_0= ruleEntity )
            	    {
            	    // InternalXSOData.g:172:4: (lv_entities_4_0= ruleEntity )
            	    // InternalXSOData.g:173:5: lv_entities_4_0= ruleEntity
            	    {

            	    					newCompositeNode(grammarAccess.getServiceAccess().getEntitiesEntityParserRuleCall_4_0());
            	    				
            	    pushFollow(FOLLOW_7);
            	    lv_entities_4_0=ruleEntity();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getServiceRule());
            	    					}
            	    					add(
            	    						current,
            	    						"entities",
            	    						lv_entities_4_0,
            	    						"com.sap.xsk.models.xsodata.XSOData.Entity");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);

            // InternalXSOData.g:190:3: ( (lv_associations_5_0= ruleAssociation ) )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==32) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // InternalXSOData.g:191:4: (lv_associations_5_0= ruleAssociation )
            	    {
            	    // InternalXSOData.g:191:4: (lv_associations_5_0= ruleAssociation )
            	    // InternalXSOData.g:192:5: lv_associations_5_0= ruleAssociation
            	    {

            	    					newCompositeNode(grammarAccess.getServiceAccess().getAssociationsAssociationParserRuleCall_5_0());
            	    				
            	    pushFollow(FOLLOW_8);
            	    lv_associations_5_0=ruleAssociation();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getServiceRule());
            	    					}
            	    					add(
            	    						current,
            	    						"associations",
            	    						lv_associations_5_0,
            	    						"com.sap.xsk.models.xsodata.XSOData.Association");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);

            otherlv_6=(Token)match(input,14,FOLLOW_2); 

            			newLeafNode(otherlv_6, grammarAccess.getServiceAccess().getRightCurlyBracketKeyword_6());
            		

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
    // $ANTLR end "ruleService"


    // $ANTLR start "entryRuleQualifiedName"
    // InternalXSOData.g:217:1: entryRuleQualifiedName returns [String current=null] : iv_ruleQualifiedName= ruleQualifiedName EOF ;
    public final String entryRuleQualifiedName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleQualifiedName = null;


        try {
            // InternalXSOData.g:217:53: (iv_ruleQualifiedName= ruleQualifiedName EOF )
            // InternalXSOData.g:218:2: iv_ruleQualifiedName= ruleQualifiedName EOF
            {
             newCompositeNode(grammarAccess.getQualifiedNameRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleQualifiedName=ruleQualifiedName();

            state._fsp--;

             current =iv_ruleQualifiedName.getText(); 
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
    // $ANTLR end "entryRuleQualifiedName"


    // $ANTLR start "ruleQualifiedName"
    // InternalXSOData.g:224:1: ruleQualifiedName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* ) ;
    public final AntlrDatatypeRuleToken ruleQualifiedName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token kw=null;
        Token this_ID_2=null;


        	enterRule();

        try {
            // InternalXSOData.g:230:2: ( (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* ) )
            // InternalXSOData.g:231:2: (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* )
            {
            // InternalXSOData.g:231:2: (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* )
            // InternalXSOData.g:232:3: this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )*
            {
            this_ID_0=(Token)match(input,RULE_ID,FOLLOW_9); 

            			current.merge(this_ID_0);
            		

            			newLeafNode(this_ID_0, grammarAccess.getQualifiedNameAccess().getIDTerminalRuleCall_0());
            		
            // InternalXSOData.g:239:3: (kw= '.' this_ID_2= RULE_ID )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==15) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // InternalXSOData.g:240:4: kw= '.' this_ID_2= RULE_ID
            	    {
            	    kw=(Token)match(input,15,FOLLOW_5); 

            	    				current.merge(kw);
            	    				newLeafNode(kw, grammarAccess.getQualifiedNameAccess().getFullStopKeyword_1_0());
            	    			
            	    this_ID_2=(Token)match(input,RULE_ID,FOLLOW_9); 

            	    				current.merge(this_ID_2);
            	    			

            	    				newLeafNode(this_ID_2, grammarAccess.getQualifiedNameAccess().getIDTerminalRuleCall_1_1());
            	    			

            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);


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
    // $ANTLR end "ruleQualifiedName"


    // $ANTLR start "entryRuleEntity"
    // InternalXSOData.g:257:1: entryRuleEntity returns [EObject current=null] : iv_ruleEntity= ruleEntity EOF ;
    public final EObject entryRuleEntity() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEntity = null;


        try {
            // InternalXSOData.g:257:47: (iv_ruleEntity= ruleEntity EOF )
            // InternalXSOData.g:258:2: iv_ruleEntity= ruleEntity EOF
            {
             newCompositeNode(grammarAccess.getEntityRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleEntity=ruleEntity();

            state._fsp--;

             current =iv_ruleEntity; 
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
    // $ANTLR end "entryRuleEntity"


    // $ANTLR start "ruleEntity"
    // InternalXSOData.g:264:1: ruleEntity returns [EObject current=null] : ( ( (lv_namespace_0_0= ruleEntityQualifiedNamespace ) ) otherlv_1= '::' ( (lv_name_2_0= ruleEntityQualifiedName ) ) otherlv_3= 'as' ( (lv_alias_4_0= ruleEntityQualifiedAlias ) ) ( (lv_navigates_5_0= ruleNavigation ) )* ( (lv_createEvents_6_0= ruleCreateEvent ) )* ( (lv_deleteUsing_7_0= ruleDeleteUsing ) )? ( (lv_update_8_0= ruleUpdate ) )? otherlv_9= ';' ) ;
    public final EObject ruleEntity() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_9=null;
        AntlrDatatypeRuleToken lv_namespace_0_0 = null;

        AntlrDatatypeRuleToken lv_name_2_0 = null;

        AntlrDatatypeRuleToken lv_alias_4_0 = null;

        EObject lv_navigates_5_0 = null;

        AntlrDatatypeRuleToken lv_createEvents_6_0 = null;

        AntlrDatatypeRuleToken lv_deleteUsing_7_0 = null;

        AntlrDatatypeRuleToken lv_update_8_0 = null;



        	enterRule();

        try {
            // InternalXSOData.g:270:2: ( ( ( (lv_namespace_0_0= ruleEntityQualifiedNamespace ) ) otherlv_1= '::' ( (lv_name_2_0= ruleEntityQualifiedName ) ) otherlv_3= 'as' ( (lv_alias_4_0= ruleEntityQualifiedAlias ) ) ( (lv_navigates_5_0= ruleNavigation ) )* ( (lv_createEvents_6_0= ruleCreateEvent ) )* ( (lv_deleteUsing_7_0= ruleDeleteUsing ) )? ( (lv_update_8_0= ruleUpdate ) )? otherlv_9= ';' ) )
            // InternalXSOData.g:271:2: ( ( (lv_namespace_0_0= ruleEntityQualifiedNamespace ) ) otherlv_1= '::' ( (lv_name_2_0= ruleEntityQualifiedName ) ) otherlv_3= 'as' ( (lv_alias_4_0= ruleEntityQualifiedAlias ) ) ( (lv_navigates_5_0= ruleNavigation ) )* ( (lv_createEvents_6_0= ruleCreateEvent ) )* ( (lv_deleteUsing_7_0= ruleDeleteUsing ) )? ( (lv_update_8_0= ruleUpdate ) )? otherlv_9= ';' )
            {
            // InternalXSOData.g:271:2: ( ( (lv_namespace_0_0= ruleEntityQualifiedNamespace ) ) otherlv_1= '::' ( (lv_name_2_0= ruleEntityQualifiedName ) ) otherlv_3= 'as' ( (lv_alias_4_0= ruleEntityQualifiedAlias ) ) ( (lv_navigates_5_0= ruleNavigation ) )* ( (lv_createEvents_6_0= ruleCreateEvent ) )* ( (lv_deleteUsing_7_0= ruleDeleteUsing ) )? ( (lv_update_8_0= ruleUpdate ) )? otherlv_9= ';' )
            // InternalXSOData.g:272:3: ( (lv_namespace_0_0= ruleEntityQualifiedNamespace ) ) otherlv_1= '::' ( (lv_name_2_0= ruleEntityQualifiedName ) ) otherlv_3= 'as' ( (lv_alias_4_0= ruleEntityQualifiedAlias ) ) ( (lv_navigates_5_0= ruleNavigation ) )* ( (lv_createEvents_6_0= ruleCreateEvent ) )* ( (lv_deleteUsing_7_0= ruleDeleteUsing ) )? ( (lv_update_8_0= ruleUpdate ) )? otherlv_9= ';'
            {
            // InternalXSOData.g:272:3: ( (lv_namespace_0_0= ruleEntityQualifiedNamespace ) )
            // InternalXSOData.g:273:4: (lv_namespace_0_0= ruleEntityQualifiedNamespace )
            {
            // InternalXSOData.g:273:4: (lv_namespace_0_0= ruleEntityQualifiedNamespace )
            // InternalXSOData.g:274:5: lv_namespace_0_0= ruleEntityQualifiedNamespace
            {

            					newCompositeNode(grammarAccess.getEntityAccess().getNamespaceEntityQualifiedNamespaceParserRuleCall_0_0());
            				
            pushFollow(FOLLOW_10);
            lv_namespace_0_0=ruleEntityQualifiedNamespace();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getEntityRule());
            					}
            					set(
            						current,
            						"namespace",
            						lv_namespace_0_0,
            						"com.sap.xsk.models.xsodata.XSOData.EntityQualifiedNamespace");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_1=(Token)match(input,16,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getEntityAccess().getColonColonKeyword_1());
            		
            // InternalXSOData.g:295:3: ( (lv_name_2_0= ruleEntityQualifiedName ) )
            // InternalXSOData.g:296:4: (lv_name_2_0= ruleEntityQualifiedName )
            {
            // InternalXSOData.g:296:4: (lv_name_2_0= ruleEntityQualifiedName )
            // InternalXSOData.g:297:5: lv_name_2_0= ruleEntityQualifiedName
            {

            					newCompositeNode(grammarAccess.getEntityAccess().getNameEntityQualifiedNameParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_11);
            lv_name_2_0=ruleEntityQualifiedName();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getEntityRule());
            					}
            					set(
            						current,
            						"name",
            						lv_name_2_0,
            						"com.sap.xsk.models.xsodata.XSOData.EntityQualifiedName");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_3=(Token)match(input,17,FOLLOW_5); 

            			newLeafNode(otherlv_3, grammarAccess.getEntityAccess().getAsKeyword_3());
            		
            // InternalXSOData.g:318:3: ( (lv_alias_4_0= ruleEntityQualifiedAlias ) )
            // InternalXSOData.g:319:4: (lv_alias_4_0= ruleEntityQualifiedAlias )
            {
            // InternalXSOData.g:319:4: (lv_alias_4_0= ruleEntityQualifiedAlias )
            // InternalXSOData.g:320:5: lv_alias_4_0= ruleEntityQualifiedAlias
            {

            					newCompositeNode(grammarAccess.getEntityAccess().getAliasEntityQualifiedAliasParserRuleCall_4_0());
            				
            pushFollow(FOLLOW_12);
            lv_alias_4_0=ruleEntityQualifiedAlias();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getEntityRule());
            					}
            					set(
            						current,
            						"alias",
            						lv_alias_4_0,
            						"com.sap.xsk.models.xsodata.XSOData.EntityQualifiedAlias");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalXSOData.g:337:3: ( (lv_navigates_5_0= ruleNavigation ) )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==19) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // InternalXSOData.g:338:4: (lv_navigates_5_0= ruleNavigation )
            	    {
            	    // InternalXSOData.g:338:4: (lv_navigates_5_0= ruleNavigation )
            	    // InternalXSOData.g:339:5: lv_navigates_5_0= ruleNavigation
            	    {

            	    					newCompositeNode(grammarAccess.getEntityAccess().getNavigatesNavigationParserRuleCall_5_0());
            	    				
            	    pushFollow(FOLLOW_12);
            	    lv_navigates_5_0=ruleNavigation();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getEntityRule());
            	    					}
            	    					add(
            	    						current,
            	    						"navigates",
            	    						lv_navigates_5_0,
            	    						"com.sap.xsk.models.xsodata.XSOData.Navigation");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);

            // InternalXSOData.g:356:3: ( (lv_createEvents_6_0= ruleCreateEvent ) )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==22) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // InternalXSOData.g:357:4: (lv_createEvents_6_0= ruleCreateEvent )
            	    {
            	    // InternalXSOData.g:357:4: (lv_createEvents_6_0= ruleCreateEvent )
            	    // InternalXSOData.g:358:5: lv_createEvents_6_0= ruleCreateEvent
            	    {

            	    					newCompositeNode(grammarAccess.getEntityAccess().getCreateEventsCreateEventParserRuleCall_6_0());
            	    				
            	    pushFollow(FOLLOW_13);
            	    lv_createEvents_6_0=ruleCreateEvent();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getEntityRule());
            	    					}
            	    					add(
            	    						current,
            	    						"createEvents",
            	    						lv_createEvents_6_0,
            	    						"com.sap.xsk.models.xsodata.XSOData.CreateEvent");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);

            // InternalXSOData.g:375:3: ( (lv_deleteUsing_7_0= ruleDeleteUsing ) )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==28) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // InternalXSOData.g:376:4: (lv_deleteUsing_7_0= ruleDeleteUsing )
                    {
                    // InternalXSOData.g:376:4: (lv_deleteUsing_7_0= ruleDeleteUsing )
                    // InternalXSOData.g:377:5: lv_deleteUsing_7_0= ruleDeleteUsing
                    {

                    					newCompositeNode(grammarAccess.getEntityAccess().getDeleteUsingDeleteUsingParserRuleCall_7_0());
                    				
                    pushFollow(FOLLOW_14);
                    lv_deleteUsing_7_0=ruleDeleteUsing();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getEntityRule());
                    					}
                    					add(
                    						current,
                    						"deleteUsing",
                    						lv_deleteUsing_7_0,
                    						"com.sap.xsk.models.xsodata.XSOData.DeleteUsing");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }

            // InternalXSOData.g:394:3: ( (lv_update_8_0= ruleUpdate ) )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==30) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // InternalXSOData.g:395:4: (lv_update_8_0= ruleUpdate )
                    {
                    // InternalXSOData.g:395:4: (lv_update_8_0= ruleUpdate )
                    // InternalXSOData.g:396:5: lv_update_8_0= ruleUpdate
                    {

                    					newCompositeNode(grammarAccess.getEntityAccess().getUpdateUpdateParserRuleCall_8_0());
                    				
                    pushFollow(FOLLOW_15);
                    lv_update_8_0=ruleUpdate();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getEntityRule());
                    					}
                    					add(
                    						current,
                    						"update",
                    						lv_update_8_0,
                    						"com.sap.xsk.models.xsodata.XSOData.Update");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }

            otherlv_9=(Token)match(input,18,FOLLOW_2); 

            			newLeafNode(otherlv_9, grammarAccess.getEntityAccess().getSemicolonKeyword_9());
            		

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
    // $ANTLR end "ruleEntity"


    // $ANTLR start "entryRuleEntityQualifiedNamespace"
    // InternalXSOData.g:421:1: entryRuleEntityQualifiedNamespace returns [String current=null] : iv_ruleEntityQualifiedNamespace= ruleEntityQualifiedNamespace EOF ;
    public final String entryRuleEntityQualifiedNamespace() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEntityQualifiedNamespace = null;


        try {
            // InternalXSOData.g:421:64: (iv_ruleEntityQualifiedNamespace= ruleEntityQualifiedNamespace EOF )
            // InternalXSOData.g:422:2: iv_ruleEntityQualifiedNamespace= ruleEntityQualifiedNamespace EOF
            {
             newCompositeNode(grammarAccess.getEntityQualifiedNamespaceRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleEntityQualifiedNamespace=ruleEntityQualifiedNamespace();

            state._fsp--;

             current =iv_ruleEntityQualifiedNamespace.getText(); 
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
    // $ANTLR end "entryRuleEntityQualifiedNamespace"


    // $ANTLR start "ruleEntityQualifiedNamespace"
    // InternalXSOData.g:428:1: ruleEntityQualifiedNamespace returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_QualifiedName_0= ruleQualifiedName ;
    public final AntlrDatatypeRuleToken ruleEntityQualifiedNamespace() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        AntlrDatatypeRuleToken this_QualifiedName_0 = null;



        	enterRule();

        try {
            // InternalXSOData.g:434:2: (this_QualifiedName_0= ruleQualifiedName )
            // InternalXSOData.g:435:2: this_QualifiedName_0= ruleQualifiedName
            {

            		newCompositeNode(grammarAccess.getEntityQualifiedNamespaceAccess().getQualifiedNameParserRuleCall());
            	
            pushFollow(FOLLOW_2);
            this_QualifiedName_0=ruleQualifiedName();

            state._fsp--;


            		current.merge(this_QualifiedName_0);
            	

            		afterParserOrEnumRuleCall();
            	

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
    // $ANTLR end "ruleEntityQualifiedNamespace"


    // $ANTLR start "entryRuleEntityQualifiedName"
    // InternalXSOData.g:448:1: entryRuleEntityQualifiedName returns [String current=null] : iv_ruleEntityQualifiedName= ruleEntityQualifiedName EOF ;
    public final String entryRuleEntityQualifiedName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEntityQualifiedName = null;


        try {
            // InternalXSOData.g:448:59: (iv_ruleEntityQualifiedName= ruleEntityQualifiedName EOF )
            // InternalXSOData.g:449:2: iv_ruleEntityQualifiedName= ruleEntityQualifiedName EOF
            {
             newCompositeNode(grammarAccess.getEntityQualifiedNameRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleEntityQualifiedName=ruleEntityQualifiedName();

            state._fsp--;

             current =iv_ruleEntityQualifiedName.getText(); 
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
    // $ANTLR end "entryRuleEntityQualifiedName"


    // $ANTLR start "ruleEntityQualifiedName"
    // InternalXSOData.g:455:1: ruleEntityQualifiedName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_QualifiedName_0= ruleQualifiedName ;
    public final AntlrDatatypeRuleToken ruleEntityQualifiedName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        AntlrDatatypeRuleToken this_QualifiedName_0 = null;



        	enterRule();

        try {
            // InternalXSOData.g:461:2: (this_QualifiedName_0= ruleQualifiedName )
            // InternalXSOData.g:462:2: this_QualifiedName_0= ruleQualifiedName
            {

            		newCompositeNode(grammarAccess.getEntityQualifiedNameAccess().getQualifiedNameParserRuleCall());
            	
            pushFollow(FOLLOW_2);
            this_QualifiedName_0=ruleQualifiedName();

            state._fsp--;


            		current.merge(this_QualifiedName_0);
            	

            		afterParserOrEnumRuleCall();
            	

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
    // $ANTLR end "ruleEntityQualifiedName"


    // $ANTLR start "entryRuleEntityQualifiedAlias"
    // InternalXSOData.g:475:1: entryRuleEntityQualifiedAlias returns [String current=null] : iv_ruleEntityQualifiedAlias= ruleEntityQualifiedAlias EOF ;
    public final String entryRuleEntityQualifiedAlias() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEntityQualifiedAlias = null;


        try {
            // InternalXSOData.g:475:60: (iv_ruleEntityQualifiedAlias= ruleEntityQualifiedAlias EOF )
            // InternalXSOData.g:476:2: iv_ruleEntityQualifiedAlias= ruleEntityQualifiedAlias EOF
            {
             newCompositeNode(grammarAccess.getEntityQualifiedAliasRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleEntityQualifiedAlias=ruleEntityQualifiedAlias();

            state._fsp--;

             current =iv_ruleEntityQualifiedAlias.getText(); 
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
    // $ANTLR end "entryRuleEntityQualifiedAlias"


    // $ANTLR start "ruleEntityQualifiedAlias"
    // InternalXSOData.g:482:1: ruleEntityQualifiedAlias returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_ID_0= RULE_ID ;
    public final AntlrDatatypeRuleToken ruleEntityQualifiedAlias() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;


        	enterRule();

        try {
            // InternalXSOData.g:488:2: (this_ID_0= RULE_ID )
            // InternalXSOData.g:489:2: this_ID_0= RULE_ID
            {
            this_ID_0=(Token)match(input,RULE_ID,FOLLOW_2); 

            		current.merge(this_ID_0);
            	

            		newLeafNode(this_ID_0, grammarAccess.getEntityQualifiedAliasAccess().getIDTerminalRuleCall());
            	

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
    // $ANTLR end "ruleEntityQualifiedAlias"


    // $ANTLR start "entryRuleNavigation"
    // InternalXSOData.g:499:1: entryRuleNavigation returns [EObject current=null] : iv_ruleNavigation= ruleNavigation EOF ;
    public final EObject entryRuleNavigation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNavigation = null;


        try {
            // InternalXSOData.g:499:51: (iv_ruleNavigation= ruleNavigation EOF )
            // InternalXSOData.g:500:2: iv_ruleNavigation= ruleNavigation EOF
            {
             newCompositeNode(grammarAccess.getNavigationRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleNavigation=ruleNavigation();

            state._fsp--;

             current =iv_ruleNavigation; 
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
    // $ANTLR end "entryRuleNavigation"


    // $ANTLR start "ruleNavigation"
    // InternalXSOData.g:506:1: ruleNavigation returns [EObject current=null] : (otherlv_0= 'navigates' otherlv_1= '(' ( (lv_association_2_0= ruleNavigationAssociation ) ) otherlv_3= 'as' ( (lv_alias_4_0= ruleNavigationAlias ) ) otherlv_5= ')' ) ;
    public final EObject ruleNavigation() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        AntlrDatatypeRuleToken lv_association_2_0 = null;

        AntlrDatatypeRuleToken lv_alias_4_0 = null;



        	enterRule();

        try {
            // InternalXSOData.g:512:2: ( (otherlv_0= 'navigates' otherlv_1= '(' ( (lv_association_2_0= ruleNavigationAssociation ) ) otherlv_3= 'as' ( (lv_alias_4_0= ruleNavigationAlias ) ) otherlv_5= ')' ) )
            // InternalXSOData.g:513:2: (otherlv_0= 'navigates' otherlv_1= '(' ( (lv_association_2_0= ruleNavigationAssociation ) ) otherlv_3= 'as' ( (lv_alias_4_0= ruleNavigationAlias ) ) otherlv_5= ')' )
            {
            // InternalXSOData.g:513:2: (otherlv_0= 'navigates' otherlv_1= '(' ( (lv_association_2_0= ruleNavigationAssociation ) ) otherlv_3= 'as' ( (lv_alias_4_0= ruleNavigationAlias ) ) otherlv_5= ')' )
            // InternalXSOData.g:514:3: otherlv_0= 'navigates' otherlv_1= '(' ( (lv_association_2_0= ruleNavigationAssociation ) ) otherlv_3= 'as' ( (lv_alias_4_0= ruleNavigationAlias ) ) otherlv_5= ')'
            {
            otherlv_0=(Token)match(input,19,FOLLOW_16); 

            			newLeafNode(otherlv_0, grammarAccess.getNavigationAccess().getNavigatesKeyword_0());
            		
            otherlv_1=(Token)match(input,20,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getNavigationAccess().getLeftParenthesisKeyword_1());
            		
            // InternalXSOData.g:522:3: ( (lv_association_2_0= ruleNavigationAssociation ) )
            // InternalXSOData.g:523:4: (lv_association_2_0= ruleNavigationAssociation )
            {
            // InternalXSOData.g:523:4: (lv_association_2_0= ruleNavigationAssociation )
            // InternalXSOData.g:524:5: lv_association_2_0= ruleNavigationAssociation
            {

            					newCompositeNode(grammarAccess.getNavigationAccess().getAssociationNavigationAssociationParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_11);
            lv_association_2_0=ruleNavigationAssociation();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getNavigationRule());
            					}
            					set(
            						current,
            						"association",
            						lv_association_2_0,
            						"com.sap.xsk.models.xsodata.XSOData.NavigationAssociation");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_3=(Token)match(input,17,FOLLOW_5); 

            			newLeafNode(otherlv_3, grammarAccess.getNavigationAccess().getAsKeyword_3());
            		
            // InternalXSOData.g:545:3: ( (lv_alias_4_0= ruleNavigationAlias ) )
            // InternalXSOData.g:546:4: (lv_alias_4_0= ruleNavigationAlias )
            {
            // InternalXSOData.g:546:4: (lv_alias_4_0= ruleNavigationAlias )
            // InternalXSOData.g:547:5: lv_alias_4_0= ruleNavigationAlias
            {

            					newCompositeNode(grammarAccess.getNavigationAccess().getAliasNavigationAliasParserRuleCall_4_0());
            				
            pushFollow(FOLLOW_17);
            lv_alias_4_0=ruleNavigationAlias();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getNavigationRule());
            					}
            					set(
            						current,
            						"alias",
            						lv_alias_4_0,
            						"com.sap.xsk.models.xsodata.XSOData.NavigationAlias");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_5=(Token)match(input,21,FOLLOW_2); 

            			newLeafNode(otherlv_5, grammarAccess.getNavigationAccess().getRightParenthesisKeyword_5());
            		

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
    // $ANTLR end "ruleNavigation"


    // $ANTLR start "entryRuleNavigationAssociation"
    // InternalXSOData.g:572:1: entryRuleNavigationAssociation returns [String current=null] : iv_ruleNavigationAssociation= ruleNavigationAssociation EOF ;
    public final String entryRuleNavigationAssociation() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleNavigationAssociation = null;


        try {
            // InternalXSOData.g:572:61: (iv_ruleNavigationAssociation= ruleNavigationAssociation EOF )
            // InternalXSOData.g:573:2: iv_ruleNavigationAssociation= ruleNavigationAssociation EOF
            {
             newCompositeNode(grammarAccess.getNavigationAssociationRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleNavigationAssociation=ruleNavigationAssociation();

            state._fsp--;

             current =iv_ruleNavigationAssociation.getText(); 
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
    // $ANTLR end "entryRuleNavigationAssociation"


    // $ANTLR start "ruleNavigationAssociation"
    // InternalXSOData.g:579:1: ruleNavigationAssociation returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_ID_0= RULE_ID ;
    public final AntlrDatatypeRuleToken ruleNavigationAssociation() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;


        	enterRule();

        try {
            // InternalXSOData.g:585:2: (this_ID_0= RULE_ID )
            // InternalXSOData.g:586:2: this_ID_0= RULE_ID
            {
            this_ID_0=(Token)match(input,RULE_ID,FOLLOW_2); 

            		current.merge(this_ID_0);
            	

            		newLeafNode(this_ID_0, grammarAccess.getNavigationAssociationAccess().getIDTerminalRuleCall());
            	

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
    // $ANTLR end "ruleNavigationAssociation"


    // $ANTLR start "entryRuleNavigationAlias"
    // InternalXSOData.g:596:1: entryRuleNavigationAlias returns [String current=null] : iv_ruleNavigationAlias= ruleNavigationAlias EOF ;
    public final String entryRuleNavigationAlias() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleNavigationAlias = null;


        try {
            // InternalXSOData.g:596:55: (iv_ruleNavigationAlias= ruleNavigationAlias EOF )
            // InternalXSOData.g:597:2: iv_ruleNavigationAlias= ruleNavigationAlias EOF
            {
             newCompositeNode(grammarAccess.getNavigationAliasRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleNavigationAlias=ruleNavigationAlias();

            state._fsp--;

             current =iv_ruleNavigationAlias.getText(); 
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
    // $ANTLR end "entryRuleNavigationAlias"


    // $ANTLR start "ruleNavigationAlias"
    // InternalXSOData.g:603:1: ruleNavigationAlias returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_ID_0= RULE_ID ;
    public final AntlrDatatypeRuleToken ruleNavigationAlias() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;


        	enterRule();

        try {
            // InternalXSOData.g:609:2: (this_ID_0= RULE_ID )
            // InternalXSOData.g:610:2: this_ID_0= RULE_ID
            {
            this_ID_0=(Token)match(input,RULE_ID,FOLLOW_2); 

            		current.merge(this_ID_0);
            	

            		newLeafNode(this_ID_0, grammarAccess.getNavigationAliasAccess().getIDTerminalRuleCall());
            	

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
    // $ANTLR end "ruleNavigationAlias"


    // $ANTLR start "entryRuleCreateEvent"
    // InternalXSOData.g:620:1: entryRuleCreateEvent returns [String current=null] : iv_ruleCreateEvent= ruleCreateEvent EOF ;
    public final String entryRuleCreateEvent() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleCreateEvent = null;


        try {
            // InternalXSOData.g:620:51: (iv_ruleCreateEvent= ruleCreateEvent EOF )
            // InternalXSOData.g:621:2: iv_ruleCreateEvent= ruleCreateEvent EOF
            {
             newCompositeNode(grammarAccess.getCreateEventRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleCreateEvent=ruleCreateEvent();

            state._fsp--;

             current =iv_ruleCreateEvent.getText(); 
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
    // $ANTLR end "entryRuleCreateEvent"


    // $ANTLR start "ruleCreateEvent"
    // InternalXSOData.g:627:1: ruleCreateEvent returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'create' kw= 'events' kw= '(' kw= 'before' this_ID_4= RULE_ID kw= '.xsjs' kw= ':' this_ID_7= RULE_ID kw= '.xsjslib' kw= '::' this_ID_10= RULE_ID kw= ')' ) ;
    public final AntlrDatatypeRuleToken ruleCreateEvent() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        Token this_ID_4=null;
        Token this_ID_7=null;
        Token this_ID_10=null;


        	enterRule();

        try {
            // InternalXSOData.g:633:2: ( (kw= 'create' kw= 'events' kw= '(' kw= 'before' this_ID_4= RULE_ID kw= '.xsjs' kw= ':' this_ID_7= RULE_ID kw= '.xsjslib' kw= '::' this_ID_10= RULE_ID kw= ')' ) )
            // InternalXSOData.g:634:2: (kw= 'create' kw= 'events' kw= '(' kw= 'before' this_ID_4= RULE_ID kw= '.xsjs' kw= ':' this_ID_7= RULE_ID kw= '.xsjslib' kw= '::' this_ID_10= RULE_ID kw= ')' )
            {
            // InternalXSOData.g:634:2: (kw= 'create' kw= 'events' kw= '(' kw= 'before' this_ID_4= RULE_ID kw= '.xsjs' kw= ':' this_ID_7= RULE_ID kw= '.xsjslib' kw= '::' this_ID_10= RULE_ID kw= ')' )
            // InternalXSOData.g:635:3: kw= 'create' kw= 'events' kw= '(' kw= 'before' this_ID_4= RULE_ID kw= '.xsjs' kw= ':' this_ID_7= RULE_ID kw= '.xsjslib' kw= '::' this_ID_10= RULE_ID kw= ')'
            {
            kw=(Token)match(input,22,FOLLOW_18); 

            			current.merge(kw);
            			newLeafNode(kw, grammarAccess.getCreateEventAccess().getCreateKeyword_0());
            		
            kw=(Token)match(input,23,FOLLOW_16); 

            			current.merge(kw);
            			newLeafNode(kw, grammarAccess.getCreateEventAccess().getEventsKeyword_1());
            		
            kw=(Token)match(input,20,FOLLOW_19); 

            			current.merge(kw);
            			newLeafNode(kw, grammarAccess.getCreateEventAccess().getLeftParenthesisKeyword_2());
            		
            kw=(Token)match(input,24,FOLLOW_5); 

            			current.merge(kw);
            			newLeafNode(kw, grammarAccess.getCreateEventAccess().getBeforeKeyword_3());
            		
            this_ID_4=(Token)match(input,RULE_ID,FOLLOW_20); 

            			current.merge(this_ID_4);
            		

            			newLeafNode(this_ID_4, grammarAccess.getCreateEventAccess().getIDTerminalRuleCall_4());
            		
            kw=(Token)match(input,25,FOLLOW_21); 

            			current.merge(kw);
            			newLeafNode(kw, grammarAccess.getCreateEventAccess().getXsjsKeyword_5());
            		
            kw=(Token)match(input,26,FOLLOW_5); 

            			current.merge(kw);
            			newLeafNode(kw, grammarAccess.getCreateEventAccess().getColonKeyword_6());
            		
            this_ID_7=(Token)match(input,RULE_ID,FOLLOW_22); 

            			current.merge(this_ID_7);
            		

            			newLeafNode(this_ID_7, grammarAccess.getCreateEventAccess().getIDTerminalRuleCall_7());
            		
            kw=(Token)match(input,27,FOLLOW_10); 

            			current.merge(kw);
            			newLeafNode(kw, grammarAccess.getCreateEventAccess().getXsjslibKeyword_8());
            		
            kw=(Token)match(input,16,FOLLOW_5); 

            			current.merge(kw);
            			newLeafNode(kw, grammarAccess.getCreateEventAccess().getColonColonKeyword_9());
            		
            this_ID_10=(Token)match(input,RULE_ID,FOLLOW_17); 

            			current.merge(this_ID_10);
            		

            			newLeafNode(this_ID_10, grammarAccess.getCreateEventAccess().getIDTerminalRuleCall_10());
            		
            kw=(Token)match(input,21,FOLLOW_2); 

            			current.merge(kw);
            			newLeafNode(kw, grammarAccess.getCreateEventAccess().getRightParenthesisKeyword_11());
            		

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
    // $ANTLR end "ruleCreateEvent"


    // $ANTLR start "entryRuleDeleteUsing"
    // InternalXSOData.g:705:1: entryRuleDeleteUsing returns [String current=null] : iv_ruleDeleteUsing= ruleDeleteUsing EOF ;
    public final String entryRuleDeleteUsing() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleDeleteUsing = null;


        try {
            // InternalXSOData.g:705:51: (iv_ruleDeleteUsing= ruleDeleteUsing EOF )
            // InternalXSOData.g:706:2: iv_ruleDeleteUsing= ruleDeleteUsing EOF
            {
             newCompositeNode(grammarAccess.getDeleteUsingRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleDeleteUsing=ruleDeleteUsing();

            state._fsp--;

             current =iv_ruleDeleteUsing.getText(); 
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
    // $ANTLR end "entryRuleDeleteUsing"


    // $ANTLR start "ruleDeleteUsing"
    // InternalXSOData.g:712:1: ruleDeleteUsing returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'delete' kw= 'using' this_ID_2= RULE_ID kw= '.xsjs' kw= ':' this_ID_5= RULE_ID kw= '.xsjslib' kw= '::' this_ID_8= RULE_ID ) ;
    public final AntlrDatatypeRuleToken ruleDeleteUsing() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        Token this_ID_2=null;
        Token this_ID_5=null;
        Token this_ID_8=null;


        	enterRule();

        try {
            // InternalXSOData.g:718:2: ( (kw= 'delete' kw= 'using' this_ID_2= RULE_ID kw= '.xsjs' kw= ':' this_ID_5= RULE_ID kw= '.xsjslib' kw= '::' this_ID_8= RULE_ID ) )
            // InternalXSOData.g:719:2: (kw= 'delete' kw= 'using' this_ID_2= RULE_ID kw= '.xsjs' kw= ':' this_ID_5= RULE_ID kw= '.xsjslib' kw= '::' this_ID_8= RULE_ID )
            {
            // InternalXSOData.g:719:2: (kw= 'delete' kw= 'using' this_ID_2= RULE_ID kw= '.xsjs' kw= ':' this_ID_5= RULE_ID kw= '.xsjslib' kw= '::' this_ID_8= RULE_ID )
            // InternalXSOData.g:720:3: kw= 'delete' kw= 'using' this_ID_2= RULE_ID kw= '.xsjs' kw= ':' this_ID_5= RULE_ID kw= '.xsjslib' kw= '::' this_ID_8= RULE_ID
            {
            kw=(Token)match(input,28,FOLLOW_23); 

            			current.merge(kw);
            			newLeafNode(kw, grammarAccess.getDeleteUsingAccess().getDeleteKeyword_0());
            		
            kw=(Token)match(input,29,FOLLOW_5); 

            			current.merge(kw);
            			newLeafNode(kw, grammarAccess.getDeleteUsingAccess().getUsingKeyword_1());
            		
            this_ID_2=(Token)match(input,RULE_ID,FOLLOW_20); 

            			current.merge(this_ID_2);
            		

            			newLeafNode(this_ID_2, grammarAccess.getDeleteUsingAccess().getIDTerminalRuleCall_2());
            		
            kw=(Token)match(input,25,FOLLOW_21); 

            			current.merge(kw);
            			newLeafNode(kw, grammarAccess.getDeleteUsingAccess().getXsjsKeyword_3());
            		
            kw=(Token)match(input,26,FOLLOW_5); 

            			current.merge(kw);
            			newLeafNode(kw, grammarAccess.getDeleteUsingAccess().getColonKeyword_4());
            		
            this_ID_5=(Token)match(input,RULE_ID,FOLLOW_22); 

            			current.merge(this_ID_5);
            		

            			newLeafNode(this_ID_5, grammarAccess.getDeleteUsingAccess().getIDTerminalRuleCall_5());
            		
            kw=(Token)match(input,27,FOLLOW_10); 

            			current.merge(kw);
            			newLeafNode(kw, grammarAccess.getDeleteUsingAccess().getXsjslibKeyword_6());
            		
            kw=(Token)match(input,16,FOLLOW_5); 

            			current.merge(kw);
            			newLeafNode(kw, grammarAccess.getDeleteUsingAccess().getColonColonKeyword_7());
            		
            this_ID_8=(Token)match(input,RULE_ID,FOLLOW_2); 

            			current.merge(this_ID_8);
            		

            			newLeafNode(this_ID_8, grammarAccess.getDeleteUsingAccess().getIDTerminalRuleCall_8());
            		

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
    // $ANTLR end "ruleDeleteUsing"


    // $ANTLR start "entryRuleUpdate"
    // InternalXSOData.g:775:1: entryRuleUpdate returns [String current=null] : iv_ruleUpdate= ruleUpdate EOF ;
    public final String entryRuleUpdate() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleUpdate = null;


        try {
            // InternalXSOData.g:775:46: (iv_ruleUpdate= ruleUpdate EOF )
            // InternalXSOData.g:776:2: iv_ruleUpdate= ruleUpdate EOF
            {
             newCompositeNode(grammarAccess.getUpdateRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleUpdate=ruleUpdate();

            state._fsp--;

             current =iv_ruleUpdate.getText(); 
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
    // $ANTLR end "entryRuleUpdate"


    // $ANTLR start "ruleUpdate"
    // InternalXSOData.g:782:1: ruleUpdate returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'update' kw= 'forbidden' ) ;
    public final AntlrDatatypeRuleToken ruleUpdate() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalXSOData.g:788:2: ( (kw= 'update' kw= 'forbidden' ) )
            // InternalXSOData.g:789:2: (kw= 'update' kw= 'forbidden' )
            {
            // InternalXSOData.g:789:2: (kw= 'update' kw= 'forbidden' )
            // InternalXSOData.g:790:3: kw= 'update' kw= 'forbidden'
            {
            kw=(Token)match(input,30,FOLLOW_24); 

            			current.merge(kw);
            			newLeafNode(kw, grammarAccess.getUpdateAccess().getUpdateKeyword_0());
            		
            kw=(Token)match(input,31,FOLLOW_2); 

            			current.merge(kw);
            			newLeafNode(kw, grammarAccess.getUpdateAccess().getForbiddenKeyword_1());
            		

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
    // $ANTLR end "ruleUpdate"


    // $ANTLR start "entryRuleAssociation"
    // InternalXSOData.g:804:1: entryRuleAssociation returns [EObject current=null] : iv_ruleAssociation= ruleAssociation EOF ;
    public final EObject entryRuleAssociation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAssociation = null;


        try {
            // InternalXSOData.g:804:52: (iv_ruleAssociation= ruleAssociation EOF )
            // InternalXSOData.g:805:2: iv_ruleAssociation= ruleAssociation EOF
            {
             newCompositeNode(grammarAccess.getAssociationRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAssociation=ruleAssociation();

            state._fsp--;

             current =iv_ruleAssociation; 
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
    // $ANTLR end "entryRuleAssociation"


    // $ANTLR start "ruleAssociation"
    // InternalXSOData.g:811:1: ruleAssociation returns [EObject current=null] : (otherlv_0= 'association' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= 'principal' ( (lv_principal_3_0= ruleAssociationPrincipal ) ) otherlv_4= '(' ( (lv_principalKey_5_0= ruleAssociationPrincipalKey ) ) otherlv_6= ')' otherlv_7= 'multiplicity' ( (lv_principalMultiplicity_8_0= ruleMultiplicityType ) ) otherlv_9= 'dependent' ( (lv_dependent_10_0= ruleAssociationDependent ) ) otherlv_11= '(' ( (lv_dependentProperty_12_0= ruleAssociationDependentProperty ) ) otherlv_13= ')' otherlv_14= 'multiplicity' ( (lv_dependentMultiplicity_15_0= ruleMultiplicityType ) ) otherlv_16= ';' ) ;
    public final EObject ruleAssociation() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        Token otherlv_13=null;
        Token otherlv_14=null;
        Token otherlv_16=null;
        AntlrDatatypeRuleToken lv_principal_3_0 = null;

        AntlrDatatypeRuleToken lv_principalKey_5_0 = null;

        AntlrDatatypeRuleToken lv_principalMultiplicity_8_0 = null;

        AntlrDatatypeRuleToken lv_dependent_10_0 = null;

        AntlrDatatypeRuleToken lv_dependentProperty_12_0 = null;

        AntlrDatatypeRuleToken lv_dependentMultiplicity_15_0 = null;



        	enterRule();

        try {
            // InternalXSOData.g:817:2: ( (otherlv_0= 'association' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= 'principal' ( (lv_principal_3_0= ruleAssociationPrincipal ) ) otherlv_4= '(' ( (lv_principalKey_5_0= ruleAssociationPrincipalKey ) ) otherlv_6= ')' otherlv_7= 'multiplicity' ( (lv_principalMultiplicity_8_0= ruleMultiplicityType ) ) otherlv_9= 'dependent' ( (lv_dependent_10_0= ruleAssociationDependent ) ) otherlv_11= '(' ( (lv_dependentProperty_12_0= ruleAssociationDependentProperty ) ) otherlv_13= ')' otherlv_14= 'multiplicity' ( (lv_dependentMultiplicity_15_0= ruleMultiplicityType ) ) otherlv_16= ';' ) )
            // InternalXSOData.g:818:2: (otherlv_0= 'association' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= 'principal' ( (lv_principal_3_0= ruleAssociationPrincipal ) ) otherlv_4= '(' ( (lv_principalKey_5_0= ruleAssociationPrincipalKey ) ) otherlv_6= ')' otherlv_7= 'multiplicity' ( (lv_principalMultiplicity_8_0= ruleMultiplicityType ) ) otherlv_9= 'dependent' ( (lv_dependent_10_0= ruleAssociationDependent ) ) otherlv_11= '(' ( (lv_dependentProperty_12_0= ruleAssociationDependentProperty ) ) otherlv_13= ')' otherlv_14= 'multiplicity' ( (lv_dependentMultiplicity_15_0= ruleMultiplicityType ) ) otherlv_16= ';' )
            {
            // InternalXSOData.g:818:2: (otherlv_0= 'association' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= 'principal' ( (lv_principal_3_0= ruleAssociationPrincipal ) ) otherlv_4= '(' ( (lv_principalKey_5_0= ruleAssociationPrincipalKey ) ) otherlv_6= ')' otherlv_7= 'multiplicity' ( (lv_principalMultiplicity_8_0= ruleMultiplicityType ) ) otherlv_9= 'dependent' ( (lv_dependent_10_0= ruleAssociationDependent ) ) otherlv_11= '(' ( (lv_dependentProperty_12_0= ruleAssociationDependentProperty ) ) otherlv_13= ')' otherlv_14= 'multiplicity' ( (lv_dependentMultiplicity_15_0= ruleMultiplicityType ) ) otherlv_16= ';' )
            // InternalXSOData.g:819:3: otherlv_0= 'association' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= 'principal' ( (lv_principal_3_0= ruleAssociationPrincipal ) ) otherlv_4= '(' ( (lv_principalKey_5_0= ruleAssociationPrincipalKey ) ) otherlv_6= ')' otherlv_7= 'multiplicity' ( (lv_principalMultiplicity_8_0= ruleMultiplicityType ) ) otherlv_9= 'dependent' ( (lv_dependent_10_0= ruleAssociationDependent ) ) otherlv_11= '(' ( (lv_dependentProperty_12_0= ruleAssociationDependentProperty ) ) otherlv_13= ')' otherlv_14= 'multiplicity' ( (lv_dependentMultiplicity_15_0= ruleMultiplicityType ) ) otherlv_16= ';'
            {
            otherlv_0=(Token)match(input,32,FOLLOW_5); 

            			newLeafNode(otherlv_0, grammarAccess.getAssociationAccess().getAssociationKeyword_0());
            		
            // InternalXSOData.g:823:3: ( (lv_name_1_0= RULE_ID ) )
            // InternalXSOData.g:824:4: (lv_name_1_0= RULE_ID )
            {
            // InternalXSOData.g:824:4: (lv_name_1_0= RULE_ID )
            // InternalXSOData.g:825:5: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_25); 

            					newLeafNode(lv_name_1_0, grammarAccess.getAssociationAccess().getNameIDTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getAssociationRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_1_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            otherlv_2=(Token)match(input,33,FOLLOW_5); 

            			newLeafNode(otherlv_2, grammarAccess.getAssociationAccess().getPrincipalKeyword_2());
            		
            // InternalXSOData.g:845:3: ( (lv_principal_3_0= ruleAssociationPrincipal ) )
            // InternalXSOData.g:846:4: (lv_principal_3_0= ruleAssociationPrincipal )
            {
            // InternalXSOData.g:846:4: (lv_principal_3_0= ruleAssociationPrincipal )
            // InternalXSOData.g:847:5: lv_principal_3_0= ruleAssociationPrincipal
            {

            					newCompositeNode(grammarAccess.getAssociationAccess().getPrincipalAssociationPrincipalParserRuleCall_3_0());
            				
            pushFollow(FOLLOW_16);
            lv_principal_3_0=ruleAssociationPrincipal();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getAssociationRule());
            					}
            					set(
            						current,
            						"principal",
            						lv_principal_3_0,
            						"com.sap.xsk.models.xsodata.XSOData.AssociationPrincipal");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_4=(Token)match(input,20,FOLLOW_5); 

            			newLeafNode(otherlv_4, grammarAccess.getAssociationAccess().getLeftParenthesisKeyword_4());
            		
            // InternalXSOData.g:868:3: ( (lv_principalKey_5_0= ruleAssociationPrincipalKey ) )
            // InternalXSOData.g:869:4: (lv_principalKey_5_0= ruleAssociationPrincipalKey )
            {
            // InternalXSOData.g:869:4: (lv_principalKey_5_0= ruleAssociationPrincipalKey )
            // InternalXSOData.g:870:5: lv_principalKey_5_0= ruleAssociationPrincipalKey
            {

            					newCompositeNode(grammarAccess.getAssociationAccess().getPrincipalKeyAssociationPrincipalKeyParserRuleCall_5_0());
            				
            pushFollow(FOLLOW_17);
            lv_principalKey_5_0=ruleAssociationPrincipalKey();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getAssociationRule());
            					}
            					set(
            						current,
            						"principalKey",
            						lv_principalKey_5_0,
            						"com.sap.xsk.models.xsodata.XSOData.AssociationPrincipalKey");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_6=(Token)match(input,21,FOLLOW_26); 

            			newLeafNode(otherlv_6, grammarAccess.getAssociationAccess().getRightParenthesisKeyword_6());
            		
            otherlv_7=(Token)match(input,34,FOLLOW_27); 

            			newLeafNode(otherlv_7, grammarAccess.getAssociationAccess().getMultiplicityKeyword_7());
            		
            // InternalXSOData.g:895:3: ( (lv_principalMultiplicity_8_0= ruleMultiplicityType ) )
            // InternalXSOData.g:896:4: (lv_principalMultiplicity_8_0= ruleMultiplicityType )
            {
            // InternalXSOData.g:896:4: (lv_principalMultiplicity_8_0= ruleMultiplicityType )
            // InternalXSOData.g:897:5: lv_principalMultiplicity_8_0= ruleMultiplicityType
            {

            					newCompositeNode(grammarAccess.getAssociationAccess().getPrincipalMultiplicityMultiplicityTypeParserRuleCall_8_0());
            				
            pushFollow(FOLLOW_28);
            lv_principalMultiplicity_8_0=ruleMultiplicityType();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getAssociationRule());
            					}
            					set(
            						current,
            						"principalMultiplicity",
            						lv_principalMultiplicity_8_0,
            						"com.sap.xsk.models.xsodata.XSOData.MultiplicityType");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_9=(Token)match(input,35,FOLLOW_5); 

            			newLeafNode(otherlv_9, grammarAccess.getAssociationAccess().getDependentKeyword_9());
            		
            // InternalXSOData.g:918:3: ( (lv_dependent_10_0= ruleAssociationDependent ) )
            // InternalXSOData.g:919:4: (lv_dependent_10_0= ruleAssociationDependent )
            {
            // InternalXSOData.g:919:4: (lv_dependent_10_0= ruleAssociationDependent )
            // InternalXSOData.g:920:5: lv_dependent_10_0= ruleAssociationDependent
            {

            					newCompositeNode(grammarAccess.getAssociationAccess().getDependentAssociationDependentParserRuleCall_10_0());
            				
            pushFollow(FOLLOW_16);
            lv_dependent_10_0=ruleAssociationDependent();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getAssociationRule());
            					}
            					set(
            						current,
            						"dependent",
            						lv_dependent_10_0,
            						"com.sap.xsk.models.xsodata.XSOData.AssociationDependent");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_11=(Token)match(input,20,FOLLOW_5); 

            			newLeafNode(otherlv_11, grammarAccess.getAssociationAccess().getLeftParenthesisKeyword_11());
            		
            // InternalXSOData.g:941:3: ( (lv_dependentProperty_12_0= ruleAssociationDependentProperty ) )
            // InternalXSOData.g:942:4: (lv_dependentProperty_12_0= ruleAssociationDependentProperty )
            {
            // InternalXSOData.g:942:4: (lv_dependentProperty_12_0= ruleAssociationDependentProperty )
            // InternalXSOData.g:943:5: lv_dependentProperty_12_0= ruleAssociationDependentProperty
            {

            					newCompositeNode(grammarAccess.getAssociationAccess().getDependentPropertyAssociationDependentPropertyParserRuleCall_12_0());
            				
            pushFollow(FOLLOW_17);
            lv_dependentProperty_12_0=ruleAssociationDependentProperty();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getAssociationRule());
            					}
            					set(
            						current,
            						"dependentProperty",
            						lv_dependentProperty_12_0,
            						"com.sap.xsk.models.xsodata.XSOData.AssociationDependentProperty");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_13=(Token)match(input,21,FOLLOW_26); 

            			newLeafNode(otherlv_13, grammarAccess.getAssociationAccess().getRightParenthesisKeyword_13());
            		
            otherlv_14=(Token)match(input,34,FOLLOW_27); 

            			newLeafNode(otherlv_14, grammarAccess.getAssociationAccess().getMultiplicityKeyword_14());
            		
            // InternalXSOData.g:968:3: ( (lv_dependentMultiplicity_15_0= ruleMultiplicityType ) )
            // InternalXSOData.g:969:4: (lv_dependentMultiplicity_15_0= ruleMultiplicityType )
            {
            // InternalXSOData.g:969:4: (lv_dependentMultiplicity_15_0= ruleMultiplicityType )
            // InternalXSOData.g:970:5: lv_dependentMultiplicity_15_0= ruleMultiplicityType
            {

            					newCompositeNode(grammarAccess.getAssociationAccess().getDependentMultiplicityMultiplicityTypeParserRuleCall_15_0());
            				
            pushFollow(FOLLOW_15);
            lv_dependentMultiplicity_15_0=ruleMultiplicityType();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getAssociationRule());
            					}
            					set(
            						current,
            						"dependentMultiplicity",
            						lv_dependentMultiplicity_15_0,
            						"com.sap.xsk.models.xsodata.XSOData.MultiplicityType");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_16=(Token)match(input,18,FOLLOW_2); 

            			newLeafNode(otherlv_16, grammarAccess.getAssociationAccess().getSemicolonKeyword_16());
            		

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
    // $ANTLR end "ruleAssociation"


    // $ANTLR start "entryRuleAssociationPrincipal"
    // InternalXSOData.g:995:1: entryRuleAssociationPrincipal returns [String current=null] : iv_ruleAssociationPrincipal= ruleAssociationPrincipal EOF ;
    public final String entryRuleAssociationPrincipal() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleAssociationPrincipal = null;


        try {
            // InternalXSOData.g:995:60: (iv_ruleAssociationPrincipal= ruleAssociationPrincipal EOF )
            // InternalXSOData.g:996:2: iv_ruleAssociationPrincipal= ruleAssociationPrincipal EOF
            {
             newCompositeNode(grammarAccess.getAssociationPrincipalRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAssociationPrincipal=ruleAssociationPrincipal();

            state._fsp--;

             current =iv_ruleAssociationPrincipal.getText(); 
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
    // $ANTLR end "entryRuleAssociationPrincipal"


    // $ANTLR start "ruleAssociationPrincipal"
    // InternalXSOData.g:1002:1: ruleAssociationPrincipal returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_ID_0= RULE_ID ;
    public final AntlrDatatypeRuleToken ruleAssociationPrincipal() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;


        	enterRule();

        try {
            // InternalXSOData.g:1008:2: (this_ID_0= RULE_ID )
            // InternalXSOData.g:1009:2: this_ID_0= RULE_ID
            {
            this_ID_0=(Token)match(input,RULE_ID,FOLLOW_2); 

            		current.merge(this_ID_0);
            	

            		newLeafNode(this_ID_0, grammarAccess.getAssociationPrincipalAccess().getIDTerminalRuleCall());
            	

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
    // $ANTLR end "ruleAssociationPrincipal"


    // $ANTLR start "entryRuleAssociationPrincipalKey"
    // InternalXSOData.g:1019:1: entryRuleAssociationPrincipalKey returns [String current=null] : iv_ruleAssociationPrincipalKey= ruleAssociationPrincipalKey EOF ;
    public final String entryRuleAssociationPrincipalKey() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleAssociationPrincipalKey = null;


        try {
            // InternalXSOData.g:1019:63: (iv_ruleAssociationPrincipalKey= ruleAssociationPrincipalKey EOF )
            // InternalXSOData.g:1020:2: iv_ruleAssociationPrincipalKey= ruleAssociationPrincipalKey EOF
            {
             newCompositeNode(grammarAccess.getAssociationPrincipalKeyRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAssociationPrincipalKey=ruleAssociationPrincipalKey();

            state._fsp--;

             current =iv_ruleAssociationPrincipalKey.getText(); 
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
    // $ANTLR end "entryRuleAssociationPrincipalKey"


    // $ANTLR start "ruleAssociationPrincipalKey"
    // InternalXSOData.g:1026:1: ruleAssociationPrincipalKey returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_ID_0= RULE_ID ;
    public final AntlrDatatypeRuleToken ruleAssociationPrincipalKey() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;


        	enterRule();

        try {
            // InternalXSOData.g:1032:2: (this_ID_0= RULE_ID )
            // InternalXSOData.g:1033:2: this_ID_0= RULE_ID
            {
            this_ID_0=(Token)match(input,RULE_ID,FOLLOW_2); 

            		current.merge(this_ID_0);
            	

            		newLeafNode(this_ID_0, grammarAccess.getAssociationPrincipalKeyAccess().getIDTerminalRuleCall());
            	

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
    // $ANTLR end "ruleAssociationPrincipalKey"


    // $ANTLR start "entryRuleAssociationDependent"
    // InternalXSOData.g:1043:1: entryRuleAssociationDependent returns [String current=null] : iv_ruleAssociationDependent= ruleAssociationDependent EOF ;
    public final String entryRuleAssociationDependent() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleAssociationDependent = null;


        try {
            // InternalXSOData.g:1043:60: (iv_ruleAssociationDependent= ruleAssociationDependent EOF )
            // InternalXSOData.g:1044:2: iv_ruleAssociationDependent= ruleAssociationDependent EOF
            {
             newCompositeNode(grammarAccess.getAssociationDependentRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAssociationDependent=ruleAssociationDependent();

            state._fsp--;

             current =iv_ruleAssociationDependent.getText(); 
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
    // $ANTLR end "entryRuleAssociationDependent"


    // $ANTLR start "ruleAssociationDependent"
    // InternalXSOData.g:1050:1: ruleAssociationDependent returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_ID_0= RULE_ID ;
    public final AntlrDatatypeRuleToken ruleAssociationDependent() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;


        	enterRule();

        try {
            // InternalXSOData.g:1056:2: (this_ID_0= RULE_ID )
            // InternalXSOData.g:1057:2: this_ID_0= RULE_ID
            {
            this_ID_0=(Token)match(input,RULE_ID,FOLLOW_2); 

            		current.merge(this_ID_0);
            	

            		newLeafNode(this_ID_0, grammarAccess.getAssociationDependentAccess().getIDTerminalRuleCall());
            	

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
    // $ANTLR end "ruleAssociationDependent"


    // $ANTLR start "entryRuleAssociationDependentProperty"
    // InternalXSOData.g:1067:1: entryRuleAssociationDependentProperty returns [String current=null] : iv_ruleAssociationDependentProperty= ruleAssociationDependentProperty EOF ;
    public final String entryRuleAssociationDependentProperty() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleAssociationDependentProperty = null;


        try {
            // InternalXSOData.g:1067:68: (iv_ruleAssociationDependentProperty= ruleAssociationDependentProperty EOF )
            // InternalXSOData.g:1068:2: iv_ruleAssociationDependentProperty= ruleAssociationDependentProperty EOF
            {
             newCompositeNode(grammarAccess.getAssociationDependentPropertyRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAssociationDependentProperty=ruleAssociationDependentProperty();

            state._fsp--;

             current =iv_ruleAssociationDependentProperty.getText(); 
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
    // $ANTLR end "entryRuleAssociationDependentProperty"


    // $ANTLR start "ruleAssociationDependentProperty"
    // InternalXSOData.g:1074:1: ruleAssociationDependentProperty returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_ID_0= RULE_ID ;
    public final AntlrDatatypeRuleToken ruleAssociationDependentProperty() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;


        	enterRule();

        try {
            // InternalXSOData.g:1080:2: (this_ID_0= RULE_ID )
            // InternalXSOData.g:1081:2: this_ID_0= RULE_ID
            {
            this_ID_0=(Token)match(input,RULE_ID,FOLLOW_2); 

            		current.merge(this_ID_0);
            	

            		newLeafNode(this_ID_0, grammarAccess.getAssociationDependentPropertyAccess().getIDTerminalRuleCall());
            	

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
    // $ANTLR end "ruleAssociationDependentProperty"


    // $ANTLR start "entryRuleMultiplicityType"
    // InternalXSOData.g:1091:1: entryRuleMultiplicityType returns [String current=null] : iv_ruleMultiplicityType= ruleMultiplicityType EOF ;
    public final String entryRuleMultiplicityType() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleMultiplicityType = null;


        try {
            // InternalXSOData.g:1091:56: (iv_ruleMultiplicityType= ruleMultiplicityType EOF )
            // InternalXSOData.g:1092:2: iv_ruleMultiplicityType= ruleMultiplicityType EOF
            {
             newCompositeNode(grammarAccess.getMultiplicityTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleMultiplicityType=ruleMultiplicityType();

            state._fsp--;

             current =iv_ruleMultiplicityType.getText(); 
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
    // $ANTLR end "entryRuleMultiplicityType"


    // $ANTLR start "ruleMultiplicityType"
    // InternalXSOData.g:1098:1: ruleMultiplicityType returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= '*' | kw= '1' ) ;
    public final AntlrDatatypeRuleToken ruleMultiplicityType() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalXSOData.g:1104:2: ( (kw= '*' | kw= '1' ) )
            // InternalXSOData.g:1105:2: (kw= '*' | kw= '1' )
            {
            // InternalXSOData.g:1105:2: (kw= '*' | kw= '1' )
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==36) ) {
                alt9=1;
            }
            else if ( (LA9_0==37) ) {
                alt9=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;
            }
            switch (alt9) {
                case 1 :
                    // InternalXSOData.g:1106:3: kw= '*'
                    {
                    kw=(Token)match(input,36,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getMultiplicityTypeAccess().getAsteriskKeyword_0());
                    		

                    }
                    break;
                case 2 :
                    // InternalXSOData.g:1112:3: kw= '1'
                    {
                    kw=(Token)match(input,37,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getMultiplicityTypeAccess().getDigitOneKeyword_1());
                    		

                    }
                    break;

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
    // $ANTLR end "ruleMultiplicityType"

    // Delegated rules


 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000100004010L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000100004000L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x00000000504C0000L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000050440000L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000040040000L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_27 = new BitSet(new long[]{0x0000003000000000L});
    public static final BitSet FOLLOW_28 = new BitSet(new long[]{0x0000000800000000L});

}