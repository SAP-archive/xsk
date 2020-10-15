package com.sap.xsk.models.hdbtable.parser.antlr.internal;

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import com.sap.xsk.models.hdbtable.services.HdbTableGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalHdbTableParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_STRING", "RULE_ID", "RULE_INT", "RULE_BOOL", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'{'", "'name'", "'='", "';'", "'sqlType'", "'length'", "'nullable'", "'}'", "'table.schemaName'", "'table.tableType'", "'table.description'", "'table.columns'", "'['", "','", "']'", "'table.primaryKey.pkcolumns'"
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
    public static final int RULE_ID=5;
    public static final int RULE_WS=10;
    public static final int RULE_ANY_OTHER=11;
    public static final int RULE_BOOL=7;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int RULE_INT=6;
    public static final int T__22=22;
    public static final int RULE_ML_COMMENT=8;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__20=20;
    public static final int T__21=21;

    // delegates
    // delegators


        public InternalHdbTableParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalHdbTableParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalHdbTableParser.tokenNames; }
    public String getGrammarFileName() { return "InternalHdbTable.g"; }



     	private HdbTableGrammarAccess grammarAccess;

        public InternalHdbTableParser(TokenStream input, HdbTableGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }

        @Override
        protected String getFirstRuleName() {
        	return "HdbTableModel";
       	}

       	@Override
       	protected HdbTableGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}




    // $ANTLR start "entryRuleHdbTableModel"
    // InternalHdbTable.g:64:1: entryRuleHdbTableModel returns [EObject current=null] : iv_ruleHdbTableModel= ruleHdbTableModel EOF ;
    public final EObject entryRuleHdbTableModel() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleHdbTableModel = null;


        try {
            // InternalHdbTable.g:64:54: (iv_ruleHdbTableModel= ruleHdbTableModel EOF )
            // InternalHdbTable.g:65:2: iv_ruleHdbTableModel= ruleHdbTableModel EOF
            {
             newCompositeNode(grammarAccess.getHdbTableModelRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleHdbTableModel=ruleHdbTableModel();

            state._fsp--;

             current =iv_ruleHdbTableModel; 
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
    // $ANTLR end "entryRuleHdbTableModel"


    // $ANTLR start "ruleHdbTableModel"
    // InternalHdbTable.g:71:1: ruleHdbTableModel returns [EObject current=null] : ( (lv_tableElement_0_0= ruleTable ) ) ;
    public final EObject ruleHdbTableModel() throws RecognitionException {
        EObject current = null;

        EObject lv_tableElement_0_0 = null;



        	enterRule();

        try {
            // InternalHdbTable.g:77:2: ( ( (lv_tableElement_0_0= ruleTable ) ) )
            // InternalHdbTable.g:78:2: ( (lv_tableElement_0_0= ruleTable ) )
            {
            // InternalHdbTable.g:78:2: ( (lv_tableElement_0_0= ruleTable ) )
            // InternalHdbTable.g:79:3: (lv_tableElement_0_0= ruleTable )
            {
            // InternalHdbTable.g:79:3: (lv_tableElement_0_0= ruleTable )
            // InternalHdbTable.g:80:4: lv_tableElement_0_0= ruleTable
            {

            				newCompositeNode(grammarAccess.getHdbTableModelAccess().getTableElementTableParserRuleCall_0());
            			
            pushFollow(FOLLOW_2);
            lv_tableElement_0_0=ruleTable();

            state._fsp--;


            				if (current==null) {
            					current = createModelElementForParent(grammarAccess.getHdbTableModelRule());
            				}
            				set(
            					current,
            					"tableElement",
            					lv_tableElement_0_0,
            					"com.sap.xsk.models.hdbtable.HdbTable.Table");
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
    // $ANTLR end "ruleHdbTableModel"


    // $ANTLR start "entryRuleColumnType"
    // InternalHdbTable.g:100:1: entryRuleColumnType returns [EObject current=null] : iv_ruleColumnType= ruleColumnType EOF ;
    public final EObject entryRuleColumnType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleColumnType = null;


        try {
            // InternalHdbTable.g:100:51: (iv_ruleColumnType= ruleColumnType EOF )
            // InternalHdbTable.g:101:2: iv_ruleColumnType= ruleColumnType EOF
            {
             newCompositeNode(grammarAccess.getColumnTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleColumnType=ruleColumnType();

            state._fsp--;

             current =iv_ruleColumnType; 
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
    // $ANTLR end "entryRuleColumnType"


    // $ANTLR start "ruleColumnType"
    // InternalHdbTable.g:107:1: ruleColumnType returns [EObject current=null] : ( ( ( ( ({...}? => ( ({...}? => (otherlv_1= '{' otherlv_2= 'name' otherlv_3= '=' ( (lv_columnName_4_0= RULE_STRING ) ) otherlv_5= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'sqlType' otherlv_7= '=' ( (lv_columnSqlType_8_0= RULE_ID ) ) otherlv_9= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'length' otherlv_11= '=' ( (lv_columnLength_12_0= RULE_INT ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => ( (otherlv_14= 'nullable' otherlv_15= '=' ( (lv_columnNullable_16_0= RULE_BOOL ) ) )? otherlv_17= ';' otherlv_18= '}' ) ) ) ) )+ {...}?) ) ) ;
    public final EObject ruleColumnType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token lv_columnName_4_0=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        Token otherlv_7=null;
        Token lv_columnSqlType_8_0=null;
        Token otherlv_9=null;
        Token otherlv_10=null;
        Token otherlv_11=null;
        Token lv_columnLength_12_0=null;
        Token otherlv_13=null;
        Token otherlv_14=null;
        Token otherlv_15=null;
        Token lv_columnNullable_16_0=null;
        Token otherlv_17=null;
        Token otherlv_18=null;


        	enterRule();

        try {
            // InternalHdbTable.g:113:2: ( ( ( ( ( ({...}? => ( ({...}? => (otherlv_1= '{' otherlv_2= 'name' otherlv_3= '=' ( (lv_columnName_4_0= RULE_STRING ) ) otherlv_5= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'sqlType' otherlv_7= '=' ( (lv_columnSqlType_8_0= RULE_ID ) ) otherlv_9= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'length' otherlv_11= '=' ( (lv_columnLength_12_0= RULE_INT ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => ( (otherlv_14= 'nullable' otherlv_15= '=' ( (lv_columnNullable_16_0= RULE_BOOL ) ) )? otherlv_17= ';' otherlv_18= '}' ) ) ) ) )+ {...}?) ) ) )
            // InternalHdbTable.g:114:2: ( ( ( ( ({...}? => ( ({...}? => (otherlv_1= '{' otherlv_2= 'name' otherlv_3= '=' ( (lv_columnName_4_0= RULE_STRING ) ) otherlv_5= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'sqlType' otherlv_7= '=' ( (lv_columnSqlType_8_0= RULE_ID ) ) otherlv_9= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'length' otherlv_11= '=' ( (lv_columnLength_12_0= RULE_INT ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => ( (otherlv_14= 'nullable' otherlv_15= '=' ( (lv_columnNullable_16_0= RULE_BOOL ) ) )? otherlv_17= ';' otherlv_18= '}' ) ) ) ) )+ {...}?) ) )
            {
            // InternalHdbTable.g:114:2: ( ( ( ( ({...}? => ( ({...}? => (otherlv_1= '{' otherlv_2= 'name' otherlv_3= '=' ( (lv_columnName_4_0= RULE_STRING ) ) otherlv_5= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'sqlType' otherlv_7= '=' ( (lv_columnSqlType_8_0= RULE_ID ) ) otherlv_9= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'length' otherlv_11= '=' ( (lv_columnLength_12_0= RULE_INT ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => ( (otherlv_14= 'nullable' otherlv_15= '=' ( (lv_columnNullable_16_0= RULE_BOOL ) ) )? otherlv_17= ';' otherlv_18= '}' ) ) ) ) )+ {...}?) ) )
            // InternalHdbTable.g:115:3: ( ( ( ({...}? => ( ({...}? => (otherlv_1= '{' otherlv_2= 'name' otherlv_3= '=' ( (lv_columnName_4_0= RULE_STRING ) ) otherlv_5= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'sqlType' otherlv_7= '=' ( (lv_columnSqlType_8_0= RULE_ID ) ) otherlv_9= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'length' otherlv_11= '=' ( (lv_columnLength_12_0= RULE_INT ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => ( (otherlv_14= 'nullable' otherlv_15= '=' ( (lv_columnNullable_16_0= RULE_BOOL ) ) )? otherlv_17= ';' otherlv_18= '}' ) ) ) ) )+ {...}?) )
            {
            // InternalHdbTable.g:115:3: ( ( ( ({...}? => ( ({...}? => (otherlv_1= '{' otherlv_2= 'name' otherlv_3= '=' ( (lv_columnName_4_0= RULE_STRING ) ) otherlv_5= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'sqlType' otherlv_7= '=' ( (lv_columnSqlType_8_0= RULE_ID ) ) otherlv_9= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'length' otherlv_11= '=' ( (lv_columnLength_12_0= RULE_INT ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => ( (otherlv_14= 'nullable' otherlv_15= '=' ( (lv_columnNullable_16_0= RULE_BOOL ) ) )? otherlv_17= ';' otherlv_18= '}' ) ) ) ) )+ {...}?) )
            // InternalHdbTable.g:116:4: ( ( ({...}? => ( ({...}? => (otherlv_1= '{' otherlv_2= 'name' otherlv_3= '=' ( (lv_columnName_4_0= RULE_STRING ) ) otherlv_5= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'sqlType' otherlv_7= '=' ( (lv_columnSqlType_8_0= RULE_ID ) ) otherlv_9= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'length' otherlv_11= '=' ( (lv_columnLength_12_0= RULE_INT ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => ( (otherlv_14= 'nullable' otherlv_15= '=' ( (lv_columnNullable_16_0= RULE_BOOL ) ) )? otherlv_17= ';' otherlv_18= '}' ) ) ) ) )+ {...}?)
            {
             
            			  getUnorderedGroupHelper().enter(grammarAccess.getColumnTypeAccess().getUnorderedGroup());
            			
            // InternalHdbTable.g:119:4: ( ( ({...}? => ( ({...}? => (otherlv_1= '{' otherlv_2= 'name' otherlv_3= '=' ( (lv_columnName_4_0= RULE_STRING ) ) otherlv_5= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'sqlType' otherlv_7= '=' ( (lv_columnSqlType_8_0= RULE_ID ) ) otherlv_9= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'length' otherlv_11= '=' ( (lv_columnLength_12_0= RULE_INT ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => ( (otherlv_14= 'nullable' otherlv_15= '=' ( (lv_columnNullable_16_0= RULE_BOOL ) ) )? otherlv_17= ';' otherlv_18= '}' ) ) ) ) )+ {...}?)
            // InternalHdbTable.g:120:5: ( ({...}? => ( ({...}? => (otherlv_1= '{' otherlv_2= 'name' otherlv_3= '=' ( (lv_columnName_4_0= RULE_STRING ) ) otherlv_5= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'sqlType' otherlv_7= '=' ( (lv_columnSqlType_8_0= RULE_ID ) ) otherlv_9= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'length' otherlv_11= '=' ( (lv_columnLength_12_0= RULE_INT ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => ( (otherlv_14= 'nullable' otherlv_15= '=' ( (lv_columnNullable_16_0= RULE_BOOL ) ) )? otherlv_17= ';' otherlv_18= '}' ) ) ) ) )+ {...}?
            {
            // InternalHdbTable.g:120:5: ( ({...}? => ( ({...}? => (otherlv_1= '{' otherlv_2= 'name' otherlv_3= '=' ( (lv_columnName_4_0= RULE_STRING ) ) otherlv_5= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'sqlType' otherlv_7= '=' ( (lv_columnSqlType_8_0= RULE_ID ) ) otherlv_9= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'length' otherlv_11= '=' ( (lv_columnLength_12_0= RULE_INT ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => ( (otherlv_14= 'nullable' otherlv_15= '=' ( (lv_columnNullable_16_0= RULE_BOOL ) ) )? otherlv_17= ';' otherlv_18= '}' ) ) ) ) )+
            int cnt2=0;
            loop2:
            do {
                int alt2=5;
                int LA2_0 = input.LA(1);

                if ( LA2_0 == 12 && getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 0) ) {
                    alt2=1;
                }
                else if ( LA2_0 == 16 && getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 1) ) {
                    alt2=2;
                }
                else if ( LA2_0 == 17 && getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 2) ) {
                    alt2=3;
                }
                else if ( ( LA2_0 == 15 || LA2_0 == 18 ) && getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 3) ) {
                    alt2=4;
                }


                switch (alt2) {
            	case 1 :
            	    // InternalHdbTable.g:121:3: ({...}? => ( ({...}? => (otherlv_1= '{' otherlv_2= 'name' otherlv_3= '=' ( (lv_columnName_4_0= RULE_STRING ) ) otherlv_5= ';' ) ) ) )
            	    {
            	    // InternalHdbTable.g:121:3: ({...}? => ( ({...}? => (otherlv_1= '{' otherlv_2= 'name' otherlv_3= '=' ( (lv_columnName_4_0= RULE_STRING ) ) otherlv_5= ';' ) ) ) )
            	    // InternalHdbTable.g:122:4: {...}? => ( ({...}? => (otherlv_1= '{' otherlv_2= 'name' otherlv_3= '=' ( (lv_columnName_4_0= RULE_STRING ) ) otherlv_5= ';' ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 0) ) {
            	        throw new FailedPredicateException(input, "ruleColumnType", "getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 0)");
            	    }
            	    // InternalHdbTable.g:122:104: ( ({...}? => (otherlv_1= '{' otherlv_2= 'name' otherlv_3= '=' ( (lv_columnName_4_0= RULE_STRING ) ) otherlv_5= ';' ) ) )
            	    // InternalHdbTable.g:123:5: ({...}? => (otherlv_1= '{' otherlv_2= 'name' otherlv_3= '=' ( (lv_columnName_4_0= RULE_STRING ) ) otherlv_5= ';' ) )
            	    {

            	    					getUnorderedGroupHelper().select(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 0);
            	    				
            	    // InternalHdbTable.g:126:8: ({...}? => (otherlv_1= '{' otherlv_2= 'name' otherlv_3= '=' ( (lv_columnName_4_0= RULE_STRING ) ) otherlv_5= ';' ) )
            	    // InternalHdbTable.g:126:9: {...}? => (otherlv_1= '{' otherlv_2= 'name' otherlv_3= '=' ( (lv_columnName_4_0= RULE_STRING ) ) otherlv_5= ';' )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleColumnType", "true");
            	    }
            	    // InternalHdbTable.g:126:18: (otherlv_1= '{' otherlv_2= 'name' otherlv_3= '=' ( (lv_columnName_4_0= RULE_STRING ) ) otherlv_5= ';' )
            	    // InternalHdbTable.g:126:19: otherlv_1= '{' otherlv_2= 'name' otherlv_3= '=' ( (lv_columnName_4_0= RULE_STRING ) ) otherlv_5= ';'
            	    {
            	    otherlv_1=(Token)match(input,12,FOLLOW_3); 

            	    								newLeafNode(otherlv_1, grammarAccess.getColumnTypeAccess().getLeftCurlyBracketKeyword_0_0());
            	    							
            	    otherlv_2=(Token)match(input,13,FOLLOW_4); 

            	    								newLeafNode(otherlv_2, grammarAccess.getColumnTypeAccess().getNameKeyword_0_1());
            	    							
            	    otherlv_3=(Token)match(input,14,FOLLOW_5); 

            	    								newLeafNode(otherlv_3, grammarAccess.getColumnTypeAccess().getEqualsSignKeyword_0_2());
            	    							
            	    // InternalHdbTable.g:138:8: ( (lv_columnName_4_0= RULE_STRING ) )
            	    // InternalHdbTable.g:139:9: (lv_columnName_4_0= RULE_STRING )
            	    {
            	    // InternalHdbTable.g:139:9: (lv_columnName_4_0= RULE_STRING )
            	    // InternalHdbTable.g:140:10: lv_columnName_4_0= RULE_STRING
            	    {
            	    lv_columnName_4_0=(Token)match(input,RULE_STRING,FOLLOW_6); 

            	    										newLeafNode(lv_columnName_4_0, grammarAccess.getColumnTypeAccess().getColumnNameSTRINGTerminalRuleCall_0_3_0());
            	    									

            	    										if (current==null) {
            	    											current = createModelElement(grammarAccess.getColumnTypeRule());
            	    										}
            	    										setWithLastConsumed(
            	    											current,
            	    											"columnName",
            	    											lv_columnName_4_0,
            	    											"org.eclipse.xtext.common.Terminals.STRING");
            	    									

            	    }


            	    }

            	    otherlv_5=(Token)match(input,15,FOLLOW_7); 

            	    								newLeafNode(otherlv_5, grammarAccess.getColumnTypeAccess().getSemicolonKeyword_0_4());
            	    							

            	    }


            	    }

            	     
            	    					getUnorderedGroupHelper().returnFromSelection(grammarAccess.getColumnTypeAccess().getUnorderedGroup());
            	    				

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalHdbTable.g:166:3: ({...}? => ( ({...}? => (otherlv_6= 'sqlType' otherlv_7= '=' ( (lv_columnSqlType_8_0= RULE_ID ) ) otherlv_9= ';' ) ) ) )
            	    {
            	    // InternalHdbTable.g:166:3: ({...}? => ( ({...}? => (otherlv_6= 'sqlType' otherlv_7= '=' ( (lv_columnSqlType_8_0= RULE_ID ) ) otherlv_9= ';' ) ) ) )
            	    // InternalHdbTable.g:167:4: {...}? => ( ({...}? => (otherlv_6= 'sqlType' otherlv_7= '=' ( (lv_columnSqlType_8_0= RULE_ID ) ) otherlv_9= ';' ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 1) ) {
            	        throw new FailedPredicateException(input, "ruleColumnType", "getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 1)");
            	    }
            	    // InternalHdbTable.g:167:104: ( ({...}? => (otherlv_6= 'sqlType' otherlv_7= '=' ( (lv_columnSqlType_8_0= RULE_ID ) ) otherlv_9= ';' ) ) )
            	    // InternalHdbTable.g:168:5: ({...}? => (otherlv_6= 'sqlType' otherlv_7= '=' ( (lv_columnSqlType_8_0= RULE_ID ) ) otherlv_9= ';' ) )
            	    {

            	    					getUnorderedGroupHelper().select(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 1);
            	    				
            	    // InternalHdbTable.g:171:8: ({...}? => (otherlv_6= 'sqlType' otherlv_7= '=' ( (lv_columnSqlType_8_0= RULE_ID ) ) otherlv_9= ';' ) )
            	    // InternalHdbTable.g:171:9: {...}? => (otherlv_6= 'sqlType' otherlv_7= '=' ( (lv_columnSqlType_8_0= RULE_ID ) ) otherlv_9= ';' )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleColumnType", "true");
            	    }
            	    // InternalHdbTable.g:171:18: (otherlv_6= 'sqlType' otherlv_7= '=' ( (lv_columnSqlType_8_0= RULE_ID ) ) otherlv_9= ';' )
            	    // InternalHdbTable.g:171:19: otherlv_6= 'sqlType' otherlv_7= '=' ( (lv_columnSqlType_8_0= RULE_ID ) ) otherlv_9= ';'
            	    {
            	    otherlv_6=(Token)match(input,16,FOLLOW_4); 

            	    								newLeafNode(otherlv_6, grammarAccess.getColumnTypeAccess().getSqlTypeKeyword_1_0());
            	    							
            	    otherlv_7=(Token)match(input,14,FOLLOW_8); 

            	    								newLeafNode(otherlv_7, grammarAccess.getColumnTypeAccess().getEqualsSignKeyword_1_1());
            	    							
            	    // InternalHdbTable.g:179:8: ( (lv_columnSqlType_8_0= RULE_ID ) )
            	    // InternalHdbTable.g:180:9: (lv_columnSqlType_8_0= RULE_ID )
            	    {
            	    // InternalHdbTable.g:180:9: (lv_columnSqlType_8_0= RULE_ID )
            	    // InternalHdbTable.g:181:10: lv_columnSqlType_8_0= RULE_ID
            	    {
            	    lv_columnSqlType_8_0=(Token)match(input,RULE_ID,FOLLOW_6); 

            	    										newLeafNode(lv_columnSqlType_8_0, grammarAccess.getColumnTypeAccess().getColumnSqlTypeIDTerminalRuleCall_1_2_0());
            	    									

            	    										if (current==null) {
            	    											current = createModelElement(grammarAccess.getColumnTypeRule());
            	    										}
            	    										setWithLastConsumed(
            	    											current,
            	    											"columnSqlType",
            	    											lv_columnSqlType_8_0,
            	    											"org.eclipse.xtext.common.Terminals.ID");
            	    									

            	    }


            	    }

            	    otherlv_9=(Token)match(input,15,FOLLOW_7); 

            	    								newLeafNode(otherlv_9, grammarAccess.getColumnTypeAccess().getSemicolonKeyword_1_3());
            	    							

            	    }


            	    }

            	     
            	    					getUnorderedGroupHelper().returnFromSelection(grammarAccess.getColumnTypeAccess().getUnorderedGroup());
            	    				

            	    }


            	    }


            	    }
            	    break;
            	case 3 :
            	    // InternalHdbTable.g:207:3: ({...}? => ( ({...}? => (otherlv_10= 'length' otherlv_11= '=' ( (lv_columnLength_12_0= RULE_INT ) ) otherlv_13= ';' ) ) ) )
            	    {
            	    // InternalHdbTable.g:207:3: ({...}? => ( ({...}? => (otherlv_10= 'length' otherlv_11= '=' ( (lv_columnLength_12_0= RULE_INT ) ) otherlv_13= ';' ) ) ) )
            	    // InternalHdbTable.g:208:4: {...}? => ( ({...}? => (otherlv_10= 'length' otherlv_11= '=' ( (lv_columnLength_12_0= RULE_INT ) ) otherlv_13= ';' ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 2) ) {
            	        throw new FailedPredicateException(input, "ruleColumnType", "getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 2)");
            	    }
            	    // InternalHdbTable.g:208:104: ( ({...}? => (otherlv_10= 'length' otherlv_11= '=' ( (lv_columnLength_12_0= RULE_INT ) ) otherlv_13= ';' ) ) )
            	    // InternalHdbTable.g:209:5: ({...}? => (otherlv_10= 'length' otherlv_11= '=' ( (lv_columnLength_12_0= RULE_INT ) ) otherlv_13= ';' ) )
            	    {

            	    					getUnorderedGroupHelper().select(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 2);
            	    				
            	    // InternalHdbTable.g:212:8: ({...}? => (otherlv_10= 'length' otherlv_11= '=' ( (lv_columnLength_12_0= RULE_INT ) ) otherlv_13= ';' ) )
            	    // InternalHdbTable.g:212:9: {...}? => (otherlv_10= 'length' otherlv_11= '=' ( (lv_columnLength_12_0= RULE_INT ) ) otherlv_13= ';' )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleColumnType", "true");
            	    }
            	    // InternalHdbTable.g:212:18: (otherlv_10= 'length' otherlv_11= '=' ( (lv_columnLength_12_0= RULE_INT ) ) otherlv_13= ';' )
            	    // InternalHdbTable.g:212:19: otherlv_10= 'length' otherlv_11= '=' ( (lv_columnLength_12_0= RULE_INT ) ) otherlv_13= ';'
            	    {
            	    otherlv_10=(Token)match(input,17,FOLLOW_4); 

            	    								newLeafNode(otherlv_10, grammarAccess.getColumnTypeAccess().getLengthKeyword_2_0());
            	    							
            	    otherlv_11=(Token)match(input,14,FOLLOW_9); 

            	    								newLeafNode(otherlv_11, grammarAccess.getColumnTypeAccess().getEqualsSignKeyword_2_1());
            	    							
            	    // InternalHdbTable.g:220:8: ( (lv_columnLength_12_0= RULE_INT ) )
            	    // InternalHdbTable.g:221:9: (lv_columnLength_12_0= RULE_INT )
            	    {
            	    // InternalHdbTable.g:221:9: (lv_columnLength_12_0= RULE_INT )
            	    // InternalHdbTable.g:222:10: lv_columnLength_12_0= RULE_INT
            	    {
            	    lv_columnLength_12_0=(Token)match(input,RULE_INT,FOLLOW_6); 

            	    										newLeafNode(lv_columnLength_12_0, grammarAccess.getColumnTypeAccess().getColumnLengthINTTerminalRuleCall_2_2_0());
            	    									

            	    										if (current==null) {
            	    											current = createModelElement(grammarAccess.getColumnTypeRule());
            	    										}
            	    										setWithLastConsumed(
            	    											current,
            	    											"columnLength",
            	    											lv_columnLength_12_0,
            	    											"org.eclipse.xtext.common.Terminals.INT");
            	    									

            	    }


            	    }

            	    otherlv_13=(Token)match(input,15,FOLLOW_7); 

            	    								newLeafNode(otherlv_13, grammarAccess.getColumnTypeAccess().getSemicolonKeyword_2_3());
            	    							

            	    }


            	    }

            	     
            	    					getUnorderedGroupHelper().returnFromSelection(grammarAccess.getColumnTypeAccess().getUnorderedGroup());
            	    				

            	    }


            	    }


            	    }
            	    break;
            	case 4 :
            	    // InternalHdbTable.g:248:3: ({...}? => ( ({...}? => ( (otherlv_14= 'nullable' otherlv_15= '=' ( (lv_columnNullable_16_0= RULE_BOOL ) ) )? otherlv_17= ';' otherlv_18= '}' ) ) ) )
            	    {
            	    // InternalHdbTable.g:248:3: ({...}? => ( ({...}? => ( (otherlv_14= 'nullable' otherlv_15= '=' ( (lv_columnNullable_16_0= RULE_BOOL ) ) )? otherlv_17= ';' otherlv_18= '}' ) ) ) )
            	    // InternalHdbTable.g:249:4: {...}? => ( ({...}? => ( (otherlv_14= 'nullable' otherlv_15= '=' ( (lv_columnNullable_16_0= RULE_BOOL ) ) )? otherlv_17= ';' otherlv_18= '}' ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 3) ) {
            	        throw new FailedPredicateException(input, "ruleColumnType", "getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 3)");
            	    }
            	    // InternalHdbTable.g:249:104: ( ({...}? => ( (otherlv_14= 'nullable' otherlv_15= '=' ( (lv_columnNullable_16_0= RULE_BOOL ) ) )? otherlv_17= ';' otherlv_18= '}' ) ) )
            	    // InternalHdbTable.g:250:5: ({...}? => ( (otherlv_14= 'nullable' otherlv_15= '=' ( (lv_columnNullable_16_0= RULE_BOOL ) ) )? otherlv_17= ';' otherlv_18= '}' ) )
            	    {

            	    					getUnorderedGroupHelper().select(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 3);
            	    				
            	    // InternalHdbTable.g:253:8: ({...}? => ( (otherlv_14= 'nullable' otherlv_15= '=' ( (lv_columnNullable_16_0= RULE_BOOL ) ) )? otherlv_17= ';' otherlv_18= '}' ) )
            	    // InternalHdbTable.g:253:9: {...}? => ( (otherlv_14= 'nullable' otherlv_15= '=' ( (lv_columnNullable_16_0= RULE_BOOL ) ) )? otherlv_17= ';' otherlv_18= '}' )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleColumnType", "true");
            	    }
            	    // InternalHdbTable.g:253:18: ( (otherlv_14= 'nullable' otherlv_15= '=' ( (lv_columnNullable_16_0= RULE_BOOL ) ) )? otherlv_17= ';' otherlv_18= '}' )
            	    // InternalHdbTable.g:253:19: (otherlv_14= 'nullable' otherlv_15= '=' ( (lv_columnNullable_16_0= RULE_BOOL ) ) )? otherlv_17= ';' otherlv_18= '}'
            	    {
            	    // InternalHdbTable.g:253:19: (otherlv_14= 'nullable' otherlv_15= '=' ( (lv_columnNullable_16_0= RULE_BOOL ) ) )?
            	    int alt1=2;
            	    int LA1_0 = input.LA(1);

            	    if ( (LA1_0==18) ) {
            	        alt1=1;
            	    }
            	    switch (alt1) {
            	        case 1 :
            	            // InternalHdbTable.g:254:9: otherlv_14= 'nullable' otherlv_15= '=' ( (lv_columnNullable_16_0= RULE_BOOL ) )
            	            {
            	            otherlv_14=(Token)match(input,18,FOLLOW_4); 

            	            									newLeafNode(otherlv_14, grammarAccess.getColumnTypeAccess().getNullableKeyword_3_0_0());
            	            								
            	            otherlv_15=(Token)match(input,14,FOLLOW_10); 

            	            									newLeafNode(otherlv_15, grammarAccess.getColumnTypeAccess().getEqualsSignKeyword_3_0_1());
            	            								
            	            // InternalHdbTable.g:262:9: ( (lv_columnNullable_16_0= RULE_BOOL ) )
            	            // InternalHdbTable.g:263:10: (lv_columnNullable_16_0= RULE_BOOL )
            	            {
            	            // InternalHdbTable.g:263:10: (lv_columnNullable_16_0= RULE_BOOL )
            	            // InternalHdbTable.g:264:11: lv_columnNullable_16_0= RULE_BOOL
            	            {
            	            lv_columnNullable_16_0=(Token)match(input,RULE_BOOL,FOLLOW_6); 

            	            											newLeafNode(lv_columnNullable_16_0, grammarAccess.getColumnTypeAccess().getColumnNullableBOOLTerminalRuleCall_3_0_2_0());
            	            										

            	            											if (current==null) {
            	            												current = createModelElement(grammarAccess.getColumnTypeRule());
            	            											}
            	            											setWithLastConsumed(
            	            												current,
            	            												"columnNullable",
            	            												lv_columnNullable_16_0,
            	            												"com.sap.xsk.models.hdbtable.HdbTable.BOOL");
            	            										

            	            }


            	            }


            	            }
            	            break;

            	    }

            	    otherlv_17=(Token)match(input,15,FOLLOW_11); 

            	    								newLeafNode(otherlv_17, grammarAccess.getColumnTypeAccess().getSemicolonKeyword_3_1());
            	    							
            	    otherlv_18=(Token)match(input,19,FOLLOW_7); 

            	    								newLeafNode(otherlv_18, grammarAccess.getColumnTypeAccess().getRightCurlyBracketKeyword_3_2());
            	    							

            	    }


            	    }

            	     
            	    					getUnorderedGroupHelper().returnFromSelection(grammarAccess.getColumnTypeAccess().getUnorderedGroup());
            	    				

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt2 >= 1 ) break loop2;
                        EarlyExitException eee =
                            new EarlyExitException(2, input);
                        throw eee;
                }
                cnt2++;
            } while (true);

            if ( ! getUnorderedGroupHelper().canLeave(grammarAccess.getColumnTypeAccess().getUnorderedGroup()) ) {
                throw new FailedPredicateException(input, "ruleColumnType", "getUnorderedGroupHelper().canLeave(grammarAccess.getColumnTypeAccess().getUnorderedGroup())");
            }

            }


            }

             
            			  getUnorderedGroupHelper().leave(grammarAccess.getColumnTypeAccess().getUnorderedGroup());
            			

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
    // $ANTLR end "ruleColumnType"


    // $ANTLR start "entryRuleTable"
    // InternalHdbTable.g:306:1: entryRuleTable returns [EObject current=null] : iv_ruleTable= ruleTable EOF ;
    public final EObject entryRuleTable() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTable = null;


        try {
            // InternalHdbTable.g:306:46: (iv_ruleTable= ruleTable EOF )
            // InternalHdbTable.g:307:2: iv_ruleTable= ruleTable EOF
            {
             newCompositeNode(grammarAccess.getTableRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleTable=ruleTable();

            state._fsp--;

             current =iv_ruleTable; 
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
    // $ANTLR end "entryRuleTable"


    // $ANTLR start "ruleTable"
    // InternalHdbTable.g:313:1: ruleTable returns [EObject current=null] : ( ( ( ( ({...}? => ( ({...}? => ( ( (lv_schema_1_0= 'table.schemaName' ) ) otherlv_2= '=' ( (lv_schemaName_3_0= RULE_STRING ) ) otherlv_4= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_type_5_0= 'table.tableType' ) ) otherlv_6= '=' ( (lv_typeValue_7_0= RULE_ID ) ) otherlv_8= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_description_9_0= 'table.description' ) ) otherlv_10= '=' ( (lv_descriptionText_11_0= RULE_STRING ) ) otherlv_12= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_columns_13_0= 'table.columns' ) ) otherlv_14= '=' otherlv_15= '[' ( ( (lv_columnsValues_16_0= ruleColumnType ) ) (otherlv_17= ',' ( (lv_columnsValues_18_0= ruleColumnType ) ) )* )? otherlv_19= ']' otherlv_20= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_primaryKeyColumns_21_0= 'table.primaryKey.pkcolumns' ) ) otherlv_22= '=' otherlv_23= '[' ( ( (lv_tablePrimaryKeyColumnsValues_24_0= RULE_STRING ) ) (otherlv_25= ',' ( (lv_tablePrimaryKeyColumnsValues_26_0= RULE_STRING ) ) )* )? otherlv_27= ']' otherlv_28= ';' ) ) ) ) )+ {...}?) ) ) ;
    public final EObject ruleTable() throws RecognitionException {
        EObject current = null;

        Token lv_schema_1_0=null;
        Token otherlv_2=null;
        Token lv_schemaName_3_0=null;
        Token otherlv_4=null;
        Token lv_type_5_0=null;
        Token otherlv_6=null;
        Token lv_typeValue_7_0=null;
        Token otherlv_8=null;
        Token lv_description_9_0=null;
        Token otherlv_10=null;
        Token lv_descriptionText_11_0=null;
        Token otherlv_12=null;
        Token lv_columns_13_0=null;
        Token otherlv_14=null;
        Token otherlv_15=null;
        Token otherlv_17=null;
        Token otherlv_19=null;
        Token otherlv_20=null;
        Token lv_primaryKeyColumns_21_0=null;
        Token otherlv_22=null;
        Token otherlv_23=null;
        Token lv_tablePrimaryKeyColumnsValues_24_0=null;
        Token otherlv_25=null;
        Token lv_tablePrimaryKeyColumnsValues_26_0=null;
        Token otherlv_27=null;
        Token otherlv_28=null;
        EObject lv_columnsValues_16_0 = null;

        EObject lv_columnsValues_18_0 = null;



        	enterRule();

        try {
            // InternalHdbTable.g:319:2: ( ( ( ( ( ({...}? => ( ({...}? => ( ( (lv_schema_1_0= 'table.schemaName' ) ) otherlv_2= '=' ( (lv_schemaName_3_0= RULE_STRING ) ) otherlv_4= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_type_5_0= 'table.tableType' ) ) otherlv_6= '=' ( (lv_typeValue_7_0= RULE_ID ) ) otherlv_8= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_description_9_0= 'table.description' ) ) otherlv_10= '=' ( (lv_descriptionText_11_0= RULE_STRING ) ) otherlv_12= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_columns_13_0= 'table.columns' ) ) otherlv_14= '=' otherlv_15= '[' ( ( (lv_columnsValues_16_0= ruleColumnType ) ) (otherlv_17= ',' ( (lv_columnsValues_18_0= ruleColumnType ) ) )* )? otherlv_19= ']' otherlv_20= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_primaryKeyColumns_21_0= 'table.primaryKey.pkcolumns' ) ) otherlv_22= '=' otherlv_23= '[' ( ( (lv_tablePrimaryKeyColumnsValues_24_0= RULE_STRING ) ) (otherlv_25= ',' ( (lv_tablePrimaryKeyColumnsValues_26_0= RULE_STRING ) ) )* )? otherlv_27= ']' otherlv_28= ';' ) ) ) ) )+ {...}?) ) ) )
            // InternalHdbTable.g:320:2: ( ( ( ( ({...}? => ( ({...}? => ( ( (lv_schema_1_0= 'table.schemaName' ) ) otherlv_2= '=' ( (lv_schemaName_3_0= RULE_STRING ) ) otherlv_4= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_type_5_0= 'table.tableType' ) ) otherlv_6= '=' ( (lv_typeValue_7_0= RULE_ID ) ) otherlv_8= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_description_9_0= 'table.description' ) ) otherlv_10= '=' ( (lv_descriptionText_11_0= RULE_STRING ) ) otherlv_12= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_columns_13_0= 'table.columns' ) ) otherlv_14= '=' otherlv_15= '[' ( ( (lv_columnsValues_16_0= ruleColumnType ) ) (otherlv_17= ',' ( (lv_columnsValues_18_0= ruleColumnType ) ) )* )? otherlv_19= ']' otherlv_20= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_primaryKeyColumns_21_0= 'table.primaryKey.pkcolumns' ) ) otherlv_22= '=' otherlv_23= '[' ( ( (lv_tablePrimaryKeyColumnsValues_24_0= RULE_STRING ) ) (otherlv_25= ',' ( (lv_tablePrimaryKeyColumnsValues_26_0= RULE_STRING ) ) )* )? otherlv_27= ']' otherlv_28= ';' ) ) ) ) )+ {...}?) ) )
            {
            // InternalHdbTable.g:320:2: ( ( ( ( ({...}? => ( ({...}? => ( ( (lv_schema_1_0= 'table.schemaName' ) ) otherlv_2= '=' ( (lv_schemaName_3_0= RULE_STRING ) ) otherlv_4= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_type_5_0= 'table.tableType' ) ) otherlv_6= '=' ( (lv_typeValue_7_0= RULE_ID ) ) otherlv_8= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_description_9_0= 'table.description' ) ) otherlv_10= '=' ( (lv_descriptionText_11_0= RULE_STRING ) ) otherlv_12= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_columns_13_0= 'table.columns' ) ) otherlv_14= '=' otherlv_15= '[' ( ( (lv_columnsValues_16_0= ruleColumnType ) ) (otherlv_17= ',' ( (lv_columnsValues_18_0= ruleColumnType ) ) )* )? otherlv_19= ']' otherlv_20= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_primaryKeyColumns_21_0= 'table.primaryKey.pkcolumns' ) ) otherlv_22= '=' otherlv_23= '[' ( ( (lv_tablePrimaryKeyColumnsValues_24_0= RULE_STRING ) ) (otherlv_25= ',' ( (lv_tablePrimaryKeyColumnsValues_26_0= RULE_STRING ) ) )* )? otherlv_27= ']' otherlv_28= ';' ) ) ) ) )+ {...}?) ) )
            // InternalHdbTable.g:321:3: ( ( ( ({...}? => ( ({...}? => ( ( (lv_schema_1_0= 'table.schemaName' ) ) otherlv_2= '=' ( (lv_schemaName_3_0= RULE_STRING ) ) otherlv_4= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_type_5_0= 'table.tableType' ) ) otherlv_6= '=' ( (lv_typeValue_7_0= RULE_ID ) ) otherlv_8= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_description_9_0= 'table.description' ) ) otherlv_10= '=' ( (lv_descriptionText_11_0= RULE_STRING ) ) otherlv_12= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_columns_13_0= 'table.columns' ) ) otherlv_14= '=' otherlv_15= '[' ( ( (lv_columnsValues_16_0= ruleColumnType ) ) (otherlv_17= ',' ( (lv_columnsValues_18_0= ruleColumnType ) ) )* )? otherlv_19= ']' otherlv_20= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_primaryKeyColumns_21_0= 'table.primaryKey.pkcolumns' ) ) otherlv_22= '=' otherlv_23= '[' ( ( (lv_tablePrimaryKeyColumnsValues_24_0= RULE_STRING ) ) (otherlv_25= ',' ( (lv_tablePrimaryKeyColumnsValues_26_0= RULE_STRING ) ) )* )? otherlv_27= ']' otherlv_28= ';' ) ) ) ) )+ {...}?) )
            {
            // InternalHdbTable.g:321:3: ( ( ( ({...}? => ( ({...}? => ( ( (lv_schema_1_0= 'table.schemaName' ) ) otherlv_2= '=' ( (lv_schemaName_3_0= RULE_STRING ) ) otherlv_4= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_type_5_0= 'table.tableType' ) ) otherlv_6= '=' ( (lv_typeValue_7_0= RULE_ID ) ) otherlv_8= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_description_9_0= 'table.description' ) ) otherlv_10= '=' ( (lv_descriptionText_11_0= RULE_STRING ) ) otherlv_12= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_columns_13_0= 'table.columns' ) ) otherlv_14= '=' otherlv_15= '[' ( ( (lv_columnsValues_16_0= ruleColumnType ) ) (otherlv_17= ',' ( (lv_columnsValues_18_0= ruleColumnType ) ) )* )? otherlv_19= ']' otherlv_20= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_primaryKeyColumns_21_0= 'table.primaryKey.pkcolumns' ) ) otherlv_22= '=' otherlv_23= '[' ( ( (lv_tablePrimaryKeyColumnsValues_24_0= RULE_STRING ) ) (otherlv_25= ',' ( (lv_tablePrimaryKeyColumnsValues_26_0= RULE_STRING ) ) )* )? otherlv_27= ']' otherlv_28= ';' ) ) ) ) )+ {...}?) )
            // InternalHdbTable.g:322:4: ( ( ({...}? => ( ({...}? => ( ( (lv_schema_1_0= 'table.schemaName' ) ) otherlv_2= '=' ( (lv_schemaName_3_0= RULE_STRING ) ) otherlv_4= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_type_5_0= 'table.tableType' ) ) otherlv_6= '=' ( (lv_typeValue_7_0= RULE_ID ) ) otherlv_8= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_description_9_0= 'table.description' ) ) otherlv_10= '=' ( (lv_descriptionText_11_0= RULE_STRING ) ) otherlv_12= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_columns_13_0= 'table.columns' ) ) otherlv_14= '=' otherlv_15= '[' ( ( (lv_columnsValues_16_0= ruleColumnType ) ) (otherlv_17= ',' ( (lv_columnsValues_18_0= ruleColumnType ) ) )* )? otherlv_19= ']' otherlv_20= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_primaryKeyColumns_21_0= 'table.primaryKey.pkcolumns' ) ) otherlv_22= '=' otherlv_23= '[' ( ( (lv_tablePrimaryKeyColumnsValues_24_0= RULE_STRING ) ) (otherlv_25= ',' ( (lv_tablePrimaryKeyColumnsValues_26_0= RULE_STRING ) ) )* )? otherlv_27= ']' otherlv_28= ';' ) ) ) ) )+ {...}?)
            {
             
            			  getUnorderedGroupHelper().enter(grammarAccess.getTableAccess().getUnorderedGroup());
            			
            // InternalHdbTable.g:325:4: ( ( ({...}? => ( ({...}? => ( ( (lv_schema_1_0= 'table.schemaName' ) ) otherlv_2= '=' ( (lv_schemaName_3_0= RULE_STRING ) ) otherlv_4= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_type_5_0= 'table.tableType' ) ) otherlv_6= '=' ( (lv_typeValue_7_0= RULE_ID ) ) otherlv_8= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_description_9_0= 'table.description' ) ) otherlv_10= '=' ( (lv_descriptionText_11_0= RULE_STRING ) ) otherlv_12= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_columns_13_0= 'table.columns' ) ) otherlv_14= '=' otherlv_15= '[' ( ( (lv_columnsValues_16_0= ruleColumnType ) ) (otherlv_17= ',' ( (lv_columnsValues_18_0= ruleColumnType ) ) )* )? otherlv_19= ']' otherlv_20= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_primaryKeyColumns_21_0= 'table.primaryKey.pkcolumns' ) ) otherlv_22= '=' otherlv_23= '[' ( ( (lv_tablePrimaryKeyColumnsValues_24_0= RULE_STRING ) ) (otherlv_25= ',' ( (lv_tablePrimaryKeyColumnsValues_26_0= RULE_STRING ) ) )* )? otherlv_27= ']' otherlv_28= ';' ) ) ) ) )+ {...}?)
            // InternalHdbTable.g:326:5: ( ({...}? => ( ({...}? => ( ( (lv_schema_1_0= 'table.schemaName' ) ) otherlv_2= '=' ( (lv_schemaName_3_0= RULE_STRING ) ) otherlv_4= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_type_5_0= 'table.tableType' ) ) otherlv_6= '=' ( (lv_typeValue_7_0= RULE_ID ) ) otherlv_8= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_description_9_0= 'table.description' ) ) otherlv_10= '=' ( (lv_descriptionText_11_0= RULE_STRING ) ) otherlv_12= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_columns_13_0= 'table.columns' ) ) otherlv_14= '=' otherlv_15= '[' ( ( (lv_columnsValues_16_0= ruleColumnType ) ) (otherlv_17= ',' ( (lv_columnsValues_18_0= ruleColumnType ) ) )* )? otherlv_19= ']' otherlv_20= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_primaryKeyColumns_21_0= 'table.primaryKey.pkcolumns' ) ) otherlv_22= '=' otherlv_23= '[' ( ( (lv_tablePrimaryKeyColumnsValues_24_0= RULE_STRING ) ) (otherlv_25= ',' ( (lv_tablePrimaryKeyColumnsValues_26_0= RULE_STRING ) ) )* )? otherlv_27= ']' otherlv_28= ';' ) ) ) ) )+ {...}?
            {
            // InternalHdbTable.g:326:5: ( ({...}? => ( ({...}? => ( ( (lv_schema_1_0= 'table.schemaName' ) ) otherlv_2= '=' ( (lv_schemaName_3_0= RULE_STRING ) ) otherlv_4= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_type_5_0= 'table.tableType' ) ) otherlv_6= '=' ( (lv_typeValue_7_0= RULE_ID ) ) otherlv_8= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_description_9_0= 'table.description' ) ) otherlv_10= '=' ( (lv_descriptionText_11_0= RULE_STRING ) ) otherlv_12= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_columns_13_0= 'table.columns' ) ) otherlv_14= '=' otherlv_15= '[' ( ( (lv_columnsValues_16_0= ruleColumnType ) ) (otherlv_17= ',' ( (lv_columnsValues_18_0= ruleColumnType ) ) )* )? otherlv_19= ']' otherlv_20= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_primaryKeyColumns_21_0= 'table.primaryKey.pkcolumns' ) ) otherlv_22= '=' otherlv_23= '[' ( ( (lv_tablePrimaryKeyColumnsValues_24_0= RULE_STRING ) ) (otherlv_25= ',' ( (lv_tablePrimaryKeyColumnsValues_26_0= RULE_STRING ) ) )* )? otherlv_27= ']' otherlv_28= ';' ) ) ) ) )+
            int cnt7=0;
            loop7:
            do {
                int alt7=6;
                int LA7_0 = input.LA(1);

                if ( LA7_0 == 20 && getUnorderedGroupHelper().canSelect(grammarAccess.getTableAccess().getUnorderedGroup(), 0) ) {
                    alt7=1;
                }
                else if ( LA7_0 == 21 && getUnorderedGroupHelper().canSelect(grammarAccess.getTableAccess().getUnorderedGroup(), 1) ) {
                    alt7=2;
                }
                else if ( LA7_0 == 22 && getUnorderedGroupHelper().canSelect(grammarAccess.getTableAccess().getUnorderedGroup(), 2) ) {
                    alt7=3;
                }
                else if ( LA7_0 == 23 && getUnorderedGroupHelper().canSelect(grammarAccess.getTableAccess().getUnorderedGroup(), 3) ) {
                    alt7=4;
                }
                else if ( LA7_0 == 27 && getUnorderedGroupHelper().canSelect(grammarAccess.getTableAccess().getUnorderedGroup(), 4) ) {
                    alt7=5;
                }


                switch (alt7) {
            	case 1 :
            	    // InternalHdbTable.g:327:3: ({...}? => ( ({...}? => ( ( (lv_schema_1_0= 'table.schemaName' ) ) otherlv_2= '=' ( (lv_schemaName_3_0= RULE_STRING ) ) otherlv_4= ';' ) ) ) )
            	    {
            	    // InternalHdbTable.g:327:3: ({...}? => ( ({...}? => ( ( (lv_schema_1_0= 'table.schemaName' ) ) otherlv_2= '=' ( (lv_schemaName_3_0= RULE_STRING ) ) otherlv_4= ';' ) ) ) )
            	    // InternalHdbTable.g:328:4: {...}? => ( ({...}? => ( ( (lv_schema_1_0= 'table.schemaName' ) ) otherlv_2= '=' ( (lv_schemaName_3_0= RULE_STRING ) ) otherlv_4= ';' ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getTableAccess().getUnorderedGroup(), 0) ) {
            	        throw new FailedPredicateException(input, "ruleTable", "getUnorderedGroupHelper().canSelect(grammarAccess.getTableAccess().getUnorderedGroup(), 0)");
            	    }
            	    // InternalHdbTable.g:328:99: ( ({...}? => ( ( (lv_schema_1_0= 'table.schemaName' ) ) otherlv_2= '=' ( (lv_schemaName_3_0= RULE_STRING ) ) otherlv_4= ';' ) ) )
            	    // InternalHdbTable.g:329:5: ({...}? => ( ( (lv_schema_1_0= 'table.schemaName' ) ) otherlv_2= '=' ( (lv_schemaName_3_0= RULE_STRING ) ) otherlv_4= ';' ) )
            	    {

            	    					getUnorderedGroupHelper().select(grammarAccess.getTableAccess().getUnorderedGroup(), 0);
            	    				
            	    // InternalHdbTable.g:332:8: ({...}? => ( ( (lv_schema_1_0= 'table.schemaName' ) ) otherlv_2= '=' ( (lv_schemaName_3_0= RULE_STRING ) ) otherlv_4= ';' ) )
            	    // InternalHdbTable.g:332:9: {...}? => ( ( (lv_schema_1_0= 'table.schemaName' ) ) otherlv_2= '=' ( (lv_schemaName_3_0= RULE_STRING ) ) otherlv_4= ';' )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleTable", "true");
            	    }
            	    // InternalHdbTable.g:332:18: ( ( (lv_schema_1_0= 'table.schemaName' ) ) otherlv_2= '=' ( (lv_schemaName_3_0= RULE_STRING ) ) otherlv_4= ';' )
            	    // InternalHdbTable.g:332:19: ( (lv_schema_1_0= 'table.schemaName' ) ) otherlv_2= '=' ( (lv_schemaName_3_0= RULE_STRING ) ) otherlv_4= ';'
            	    {
            	    // InternalHdbTable.g:332:19: ( (lv_schema_1_0= 'table.schemaName' ) )
            	    // InternalHdbTable.g:333:9: (lv_schema_1_0= 'table.schemaName' )
            	    {
            	    // InternalHdbTable.g:333:9: (lv_schema_1_0= 'table.schemaName' )
            	    // InternalHdbTable.g:334:10: lv_schema_1_0= 'table.schemaName'
            	    {
            	    lv_schema_1_0=(Token)match(input,20,FOLLOW_4); 

            	    										newLeafNode(lv_schema_1_0, grammarAccess.getTableAccess().getSchemaTableSchemaNameKeyword_0_0_0());
            	    									

            	    										if (current==null) {
            	    											current = createModelElement(grammarAccess.getTableRule());
            	    										}
            	    										setWithLastConsumed(current, "schema", true, "table.schemaName");
            	    									

            	    }


            	    }

            	    otherlv_2=(Token)match(input,14,FOLLOW_5); 

            	    								newLeafNode(otherlv_2, grammarAccess.getTableAccess().getEqualsSignKeyword_0_1());
            	    							
            	    // InternalHdbTable.g:350:8: ( (lv_schemaName_3_0= RULE_STRING ) )
            	    // InternalHdbTable.g:351:9: (lv_schemaName_3_0= RULE_STRING )
            	    {
            	    // InternalHdbTable.g:351:9: (lv_schemaName_3_0= RULE_STRING )
            	    // InternalHdbTable.g:352:10: lv_schemaName_3_0= RULE_STRING
            	    {
            	    lv_schemaName_3_0=(Token)match(input,RULE_STRING,FOLLOW_6); 

            	    										newLeafNode(lv_schemaName_3_0, grammarAccess.getTableAccess().getSchemaNameSTRINGTerminalRuleCall_0_2_0());
            	    									

            	    										if (current==null) {
            	    											current = createModelElement(grammarAccess.getTableRule());
            	    										}
            	    										setWithLastConsumed(
            	    											current,
            	    											"schemaName",
            	    											lv_schemaName_3_0,
            	    											"org.eclipse.xtext.common.Terminals.STRING");
            	    									

            	    }


            	    }

            	    otherlv_4=(Token)match(input,15,FOLLOW_12); 

            	    								newLeafNode(otherlv_4, grammarAccess.getTableAccess().getSemicolonKeyword_0_3());
            	    							

            	    }


            	    }

            	     
            	    					getUnorderedGroupHelper().returnFromSelection(grammarAccess.getTableAccess().getUnorderedGroup());
            	    				

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalHdbTable.g:378:3: ({...}? => ( ({...}? => ( ( (lv_type_5_0= 'table.tableType' ) ) otherlv_6= '=' ( (lv_typeValue_7_0= RULE_ID ) ) otherlv_8= ';' ) ) ) )
            	    {
            	    // InternalHdbTable.g:378:3: ({...}? => ( ({...}? => ( ( (lv_type_5_0= 'table.tableType' ) ) otherlv_6= '=' ( (lv_typeValue_7_0= RULE_ID ) ) otherlv_8= ';' ) ) ) )
            	    // InternalHdbTable.g:379:4: {...}? => ( ({...}? => ( ( (lv_type_5_0= 'table.tableType' ) ) otherlv_6= '=' ( (lv_typeValue_7_0= RULE_ID ) ) otherlv_8= ';' ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getTableAccess().getUnorderedGroup(), 1) ) {
            	        throw new FailedPredicateException(input, "ruleTable", "getUnorderedGroupHelper().canSelect(grammarAccess.getTableAccess().getUnorderedGroup(), 1)");
            	    }
            	    // InternalHdbTable.g:379:99: ( ({...}? => ( ( (lv_type_5_0= 'table.tableType' ) ) otherlv_6= '=' ( (lv_typeValue_7_0= RULE_ID ) ) otherlv_8= ';' ) ) )
            	    // InternalHdbTable.g:380:5: ({...}? => ( ( (lv_type_5_0= 'table.tableType' ) ) otherlv_6= '=' ( (lv_typeValue_7_0= RULE_ID ) ) otherlv_8= ';' ) )
            	    {

            	    					getUnorderedGroupHelper().select(grammarAccess.getTableAccess().getUnorderedGroup(), 1);
            	    				
            	    // InternalHdbTable.g:383:8: ({...}? => ( ( (lv_type_5_0= 'table.tableType' ) ) otherlv_6= '=' ( (lv_typeValue_7_0= RULE_ID ) ) otherlv_8= ';' ) )
            	    // InternalHdbTable.g:383:9: {...}? => ( ( (lv_type_5_0= 'table.tableType' ) ) otherlv_6= '=' ( (lv_typeValue_7_0= RULE_ID ) ) otherlv_8= ';' )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleTable", "true");
            	    }
            	    // InternalHdbTable.g:383:18: ( ( (lv_type_5_0= 'table.tableType' ) ) otherlv_6= '=' ( (lv_typeValue_7_0= RULE_ID ) ) otherlv_8= ';' )
            	    // InternalHdbTable.g:383:19: ( (lv_type_5_0= 'table.tableType' ) ) otherlv_6= '=' ( (lv_typeValue_7_0= RULE_ID ) ) otherlv_8= ';'
            	    {
            	    // InternalHdbTable.g:383:19: ( (lv_type_5_0= 'table.tableType' ) )
            	    // InternalHdbTable.g:384:9: (lv_type_5_0= 'table.tableType' )
            	    {
            	    // InternalHdbTable.g:384:9: (lv_type_5_0= 'table.tableType' )
            	    // InternalHdbTable.g:385:10: lv_type_5_0= 'table.tableType'
            	    {
            	    lv_type_5_0=(Token)match(input,21,FOLLOW_4); 

            	    										newLeafNode(lv_type_5_0, grammarAccess.getTableAccess().getTypeTableTableTypeKeyword_1_0_0());
            	    									

            	    										if (current==null) {
            	    											current = createModelElement(grammarAccess.getTableRule());
            	    										}
            	    										setWithLastConsumed(current, "type", true, "table.tableType");
            	    									

            	    }


            	    }

            	    otherlv_6=(Token)match(input,14,FOLLOW_8); 

            	    								newLeafNode(otherlv_6, grammarAccess.getTableAccess().getEqualsSignKeyword_1_1());
            	    							
            	    // InternalHdbTable.g:401:8: ( (lv_typeValue_7_0= RULE_ID ) )
            	    // InternalHdbTable.g:402:9: (lv_typeValue_7_0= RULE_ID )
            	    {
            	    // InternalHdbTable.g:402:9: (lv_typeValue_7_0= RULE_ID )
            	    // InternalHdbTable.g:403:10: lv_typeValue_7_0= RULE_ID
            	    {
            	    lv_typeValue_7_0=(Token)match(input,RULE_ID,FOLLOW_6); 

            	    										newLeafNode(lv_typeValue_7_0, grammarAccess.getTableAccess().getTypeValueIDTerminalRuleCall_1_2_0());
            	    									

            	    										if (current==null) {
            	    											current = createModelElement(grammarAccess.getTableRule());
            	    										}
            	    										setWithLastConsumed(
            	    											current,
            	    											"typeValue",
            	    											lv_typeValue_7_0,
            	    											"org.eclipse.xtext.common.Terminals.ID");
            	    									

            	    }


            	    }

            	    otherlv_8=(Token)match(input,15,FOLLOW_12); 

            	    								newLeafNode(otherlv_8, grammarAccess.getTableAccess().getSemicolonKeyword_1_3());
            	    							

            	    }


            	    }

            	     
            	    					getUnorderedGroupHelper().returnFromSelection(grammarAccess.getTableAccess().getUnorderedGroup());
            	    				

            	    }


            	    }


            	    }
            	    break;
            	case 3 :
            	    // InternalHdbTable.g:429:3: ({...}? => ( ({...}? => ( ( (lv_description_9_0= 'table.description' ) ) otherlv_10= '=' ( (lv_descriptionText_11_0= RULE_STRING ) ) otherlv_12= ';' ) ) ) )
            	    {
            	    // InternalHdbTable.g:429:3: ({...}? => ( ({...}? => ( ( (lv_description_9_0= 'table.description' ) ) otherlv_10= '=' ( (lv_descriptionText_11_0= RULE_STRING ) ) otherlv_12= ';' ) ) ) )
            	    // InternalHdbTable.g:430:4: {...}? => ( ({...}? => ( ( (lv_description_9_0= 'table.description' ) ) otherlv_10= '=' ( (lv_descriptionText_11_0= RULE_STRING ) ) otherlv_12= ';' ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getTableAccess().getUnorderedGroup(), 2) ) {
            	        throw new FailedPredicateException(input, "ruleTable", "getUnorderedGroupHelper().canSelect(grammarAccess.getTableAccess().getUnorderedGroup(), 2)");
            	    }
            	    // InternalHdbTable.g:430:99: ( ({...}? => ( ( (lv_description_9_0= 'table.description' ) ) otherlv_10= '=' ( (lv_descriptionText_11_0= RULE_STRING ) ) otherlv_12= ';' ) ) )
            	    // InternalHdbTable.g:431:5: ({...}? => ( ( (lv_description_9_0= 'table.description' ) ) otherlv_10= '=' ( (lv_descriptionText_11_0= RULE_STRING ) ) otherlv_12= ';' ) )
            	    {

            	    					getUnorderedGroupHelper().select(grammarAccess.getTableAccess().getUnorderedGroup(), 2);
            	    				
            	    // InternalHdbTable.g:434:8: ({...}? => ( ( (lv_description_9_0= 'table.description' ) ) otherlv_10= '=' ( (lv_descriptionText_11_0= RULE_STRING ) ) otherlv_12= ';' ) )
            	    // InternalHdbTable.g:434:9: {...}? => ( ( (lv_description_9_0= 'table.description' ) ) otherlv_10= '=' ( (lv_descriptionText_11_0= RULE_STRING ) ) otherlv_12= ';' )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleTable", "true");
            	    }
            	    // InternalHdbTable.g:434:18: ( ( (lv_description_9_0= 'table.description' ) ) otherlv_10= '=' ( (lv_descriptionText_11_0= RULE_STRING ) ) otherlv_12= ';' )
            	    // InternalHdbTable.g:434:19: ( (lv_description_9_0= 'table.description' ) ) otherlv_10= '=' ( (lv_descriptionText_11_0= RULE_STRING ) ) otherlv_12= ';'
            	    {
            	    // InternalHdbTable.g:434:19: ( (lv_description_9_0= 'table.description' ) )
            	    // InternalHdbTable.g:435:9: (lv_description_9_0= 'table.description' )
            	    {
            	    // InternalHdbTable.g:435:9: (lv_description_9_0= 'table.description' )
            	    // InternalHdbTable.g:436:10: lv_description_9_0= 'table.description'
            	    {
            	    lv_description_9_0=(Token)match(input,22,FOLLOW_4); 

            	    										newLeafNode(lv_description_9_0, grammarAccess.getTableAccess().getDescriptionTableDescriptionKeyword_2_0_0());
            	    									

            	    										if (current==null) {
            	    											current = createModelElement(grammarAccess.getTableRule());
            	    										}
            	    										setWithLastConsumed(current, "description", true, "table.description");
            	    									

            	    }


            	    }

            	    otherlv_10=(Token)match(input,14,FOLLOW_5); 

            	    								newLeafNode(otherlv_10, grammarAccess.getTableAccess().getEqualsSignKeyword_2_1());
            	    							
            	    // InternalHdbTable.g:452:8: ( (lv_descriptionText_11_0= RULE_STRING ) )
            	    // InternalHdbTable.g:453:9: (lv_descriptionText_11_0= RULE_STRING )
            	    {
            	    // InternalHdbTable.g:453:9: (lv_descriptionText_11_0= RULE_STRING )
            	    // InternalHdbTable.g:454:10: lv_descriptionText_11_0= RULE_STRING
            	    {
            	    lv_descriptionText_11_0=(Token)match(input,RULE_STRING,FOLLOW_6); 

            	    										newLeafNode(lv_descriptionText_11_0, grammarAccess.getTableAccess().getDescriptionTextSTRINGTerminalRuleCall_2_2_0());
            	    									

            	    										if (current==null) {
            	    											current = createModelElement(grammarAccess.getTableRule());
            	    										}
            	    										setWithLastConsumed(
            	    											current,
            	    											"descriptionText",
            	    											lv_descriptionText_11_0,
            	    											"org.eclipse.xtext.common.Terminals.STRING");
            	    									

            	    }


            	    }

            	    otherlv_12=(Token)match(input,15,FOLLOW_12); 

            	    								newLeafNode(otherlv_12, grammarAccess.getTableAccess().getSemicolonKeyword_2_3());
            	    							

            	    }


            	    }

            	     
            	    					getUnorderedGroupHelper().returnFromSelection(grammarAccess.getTableAccess().getUnorderedGroup());
            	    				

            	    }


            	    }


            	    }
            	    break;
            	case 4 :
            	    // InternalHdbTable.g:480:3: ({...}? => ( ({...}? => ( ( (lv_columns_13_0= 'table.columns' ) ) otherlv_14= '=' otherlv_15= '[' ( ( (lv_columnsValues_16_0= ruleColumnType ) ) (otherlv_17= ',' ( (lv_columnsValues_18_0= ruleColumnType ) ) )* )? otherlv_19= ']' otherlv_20= ';' ) ) ) )
            	    {
            	    // InternalHdbTable.g:480:3: ({...}? => ( ({...}? => ( ( (lv_columns_13_0= 'table.columns' ) ) otherlv_14= '=' otherlv_15= '[' ( ( (lv_columnsValues_16_0= ruleColumnType ) ) (otherlv_17= ',' ( (lv_columnsValues_18_0= ruleColumnType ) ) )* )? otherlv_19= ']' otherlv_20= ';' ) ) ) )
            	    // InternalHdbTable.g:481:4: {...}? => ( ({...}? => ( ( (lv_columns_13_0= 'table.columns' ) ) otherlv_14= '=' otherlv_15= '[' ( ( (lv_columnsValues_16_0= ruleColumnType ) ) (otherlv_17= ',' ( (lv_columnsValues_18_0= ruleColumnType ) ) )* )? otherlv_19= ']' otherlv_20= ';' ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getTableAccess().getUnorderedGroup(), 3) ) {
            	        throw new FailedPredicateException(input, "ruleTable", "getUnorderedGroupHelper().canSelect(grammarAccess.getTableAccess().getUnorderedGroup(), 3)");
            	    }
            	    // InternalHdbTable.g:481:99: ( ({...}? => ( ( (lv_columns_13_0= 'table.columns' ) ) otherlv_14= '=' otherlv_15= '[' ( ( (lv_columnsValues_16_0= ruleColumnType ) ) (otherlv_17= ',' ( (lv_columnsValues_18_0= ruleColumnType ) ) )* )? otherlv_19= ']' otherlv_20= ';' ) ) )
            	    // InternalHdbTable.g:482:5: ({...}? => ( ( (lv_columns_13_0= 'table.columns' ) ) otherlv_14= '=' otherlv_15= '[' ( ( (lv_columnsValues_16_0= ruleColumnType ) ) (otherlv_17= ',' ( (lv_columnsValues_18_0= ruleColumnType ) ) )* )? otherlv_19= ']' otherlv_20= ';' ) )
            	    {

            	    					getUnorderedGroupHelper().select(grammarAccess.getTableAccess().getUnorderedGroup(), 3);
            	    				
            	    // InternalHdbTable.g:485:8: ({...}? => ( ( (lv_columns_13_0= 'table.columns' ) ) otherlv_14= '=' otherlv_15= '[' ( ( (lv_columnsValues_16_0= ruleColumnType ) ) (otherlv_17= ',' ( (lv_columnsValues_18_0= ruleColumnType ) ) )* )? otherlv_19= ']' otherlv_20= ';' ) )
            	    // InternalHdbTable.g:485:9: {...}? => ( ( (lv_columns_13_0= 'table.columns' ) ) otherlv_14= '=' otherlv_15= '[' ( ( (lv_columnsValues_16_0= ruleColumnType ) ) (otherlv_17= ',' ( (lv_columnsValues_18_0= ruleColumnType ) ) )* )? otherlv_19= ']' otherlv_20= ';' )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleTable", "true");
            	    }
            	    // InternalHdbTable.g:485:18: ( ( (lv_columns_13_0= 'table.columns' ) ) otherlv_14= '=' otherlv_15= '[' ( ( (lv_columnsValues_16_0= ruleColumnType ) ) (otherlv_17= ',' ( (lv_columnsValues_18_0= ruleColumnType ) ) )* )? otherlv_19= ']' otherlv_20= ';' )
            	    // InternalHdbTable.g:485:19: ( (lv_columns_13_0= 'table.columns' ) ) otherlv_14= '=' otherlv_15= '[' ( ( (lv_columnsValues_16_0= ruleColumnType ) ) (otherlv_17= ',' ( (lv_columnsValues_18_0= ruleColumnType ) ) )* )? otherlv_19= ']' otherlv_20= ';'
            	    {
            	    // InternalHdbTable.g:485:19: ( (lv_columns_13_0= 'table.columns' ) )
            	    // InternalHdbTable.g:486:9: (lv_columns_13_0= 'table.columns' )
            	    {
            	    // InternalHdbTable.g:486:9: (lv_columns_13_0= 'table.columns' )
            	    // InternalHdbTable.g:487:10: lv_columns_13_0= 'table.columns'
            	    {
            	    lv_columns_13_0=(Token)match(input,23,FOLLOW_4); 

            	    										newLeafNode(lv_columns_13_0, grammarAccess.getTableAccess().getColumnsTableColumnsKeyword_3_0_0());
            	    									

            	    										if (current==null) {
            	    											current = createModelElement(grammarAccess.getTableRule());
            	    										}
            	    										setWithLastConsumed(current, "columns", true, "table.columns");
            	    									

            	    }


            	    }

            	    otherlv_14=(Token)match(input,14,FOLLOW_13); 

            	    								newLeafNode(otherlv_14, grammarAccess.getTableAccess().getEqualsSignKeyword_3_1());
            	    							
            	    otherlv_15=(Token)match(input,24,FOLLOW_14); 

            	    								newLeafNode(otherlv_15, grammarAccess.getTableAccess().getLeftSquareBracketKeyword_3_2());
            	    							
            	    // InternalHdbTable.g:507:8: ( ( (lv_columnsValues_16_0= ruleColumnType ) ) (otherlv_17= ',' ( (lv_columnsValues_18_0= ruleColumnType ) ) )* )?
            	    int alt4=2;
            	    int LA4_0 = input.LA(1);

            	    if ( (LA4_0==12||(LA4_0>=15 && LA4_0<=18)) ) {
            	        alt4=1;
            	    }
            	    switch (alt4) {
            	        case 1 :
            	            // InternalHdbTable.g:508:9: ( (lv_columnsValues_16_0= ruleColumnType ) ) (otherlv_17= ',' ( (lv_columnsValues_18_0= ruleColumnType ) ) )*
            	            {
            	            // InternalHdbTable.g:508:9: ( (lv_columnsValues_16_0= ruleColumnType ) )
            	            // InternalHdbTable.g:509:10: (lv_columnsValues_16_0= ruleColumnType )
            	            {
            	            // InternalHdbTable.g:509:10: (lv_columnsValues_16_0= ruleColumnType )
            	            // InternalHdbTable.g:510:11: lv_columnsValues_16_0= ruleColumnType
            	            {

            	            											newCompositeNode(grammarAccess.getTableAccess().getColumnsValuesColumnTypeParserRuleCall_3_3_0_0());
            	            										
            	            pushFollow(FOLLOW_15);
            	            lv_columnsValues_16_0=ruleColumnType();

            	            state._fsp--;


            	            											if (current==null) {
            	            												current = createModelElementForParent(grammarAccess.getTableRule());
            	            											}
            	            											add(
            	            												current,
            	            												"columnsValues",
            	            												lv_columnsValues_16_0,
            	            												"com.sap.xsk.models.hdbtable.HdbTable.ColumnType");
            	            											afterParserOrEnumRuleCall();
            	            										

            	            }


            	            }

            	            // InternalHdbTable.g:527:9: (otherlv_17= ',' ( (lv_columnsValues_18_0= ruleColumnType ) ) )*
            	            loop3:
            	            do {
            	                int alt3=2;
            	                int LA3_0 = input.LA(1);

            	                if ( (LA3_0==25) ) {
            	                    alt3=1;
            	                }


            	                switch (alt3) {
            	            	case 1 :
            	            	    // InternalHdbTable.g:528:10: otherlv_17= ',' ( (lv_columnsValues_18_0= ruleColumnType ) )
            	            	    {
            	            	    otherlv_17=(Token)match(input,25,FOLLOW_16); 

            	            	    										newLeafNode(otherlv_17, grammarAccess.getTableAccess().getCommaKeyword_3_3_1_0());
            	            	    									
            	            	    // InternalHdbTable.g:532:10: ( (lv_columnsValues_18_0= ruleColumnType ) )
            	            	    // InternalHdbTable.g:533:11: (lv_columnsValues_18_0= ruleColumnType )
            	            	    {
            	            	    // InternalHdbTable.g:533:11: (lv_columnsValues_18_0= ruleColumnType )
            	            	    // InternalHdbTable.g:534:12: lv_columnsValues_18_0= ruleColumnType
            	            	    {

            	            	    												newCompositeNode(grammarAccess.getTableAccess().getColumnsValuesColumnTypeParserRuleCall_3_3_1_1_0());
            	            	    											
            	            	    pushFollow(FOLLOW_15);
            	            	    lv_columnsValues_18_0=ruleColumnType();

            	            	    state._fsp--;


            	            	    												if (current==null) {
            	            	    													current = createModelElementForParent(grammarAccess.getTableRule());
            	            	    												}
            	            	    												add(
            	            	    													current,
            	            	    													"columnsValues",
            	            	    													lv_columnsValues_18_0,
            	            	    													"com.sap.xsk.models.hdbtable.HdbTable.ColumnType");
            	            	    												afterParserOrEnumRuleCall();
            	            	    											

            	            	    }


            	            	    }


            	            	    }
            	            	    break;

            	            	default :
            	            	    break loop3;
            	                }
            	            } while (true);


            	            }
            	            break;

            	    }

            	    otherlv_19=(Token)match(input,26,FOLLOW_6); 

            	    								newLeafNode(otherlv_19, grammarAccess.getTableAccess().getRightSquareBracketKeyword_3_4());
            	    							
            	    otherlv_20=(Token)match(input,15,FOLLOW_12); 

            	    								newLeafNode(otherlv_20, grammarAccess.getTableAccess().getSemicolonKeyword_3_5());
            	    							

            	    }


            	    }

            	     
            	    					getUnorderedGroupHelper().returnFromSelection(grammarAccess.getTableAccess().getUnorderedGroup());
            	    				

            	    }


            	    }


            	    }
            	    break;
            	case 5 :
            	    // InternalHdbTable.g:567:3: ({...}? => ( ({...}? => ( ( (lv_primaryKeyColumns_21_0= 'table.primaryKey.pkcolumns' ) ) otherlv_22= '=' otherlv_23= '[' ( ( (lv_tablePrimaryKeyColumnsValues_24_0= RULE_STRING ) ) (otherlv_25= ',' ( (lv_tablePrimaryKeyColumnsValues_26_0= RULE_STRING ) ) )* )? otherlv_27= ']' otherlv_28= ';' ) ) ) )
            	    {
            	    // InternalHdbTable.g:567:3: ({...}? => ( ({...}? => ( ( (lv_primaryKeyColumns_21_0= 'table.primaryKey.pkcolumns' ) ) otherlv_22= '=' otherlv_23= '[' ( ( (lv_tablePrimaryKeyColumnsValues_24_0= RULE_STRING ) ) (otherlv_25= ',' ( (lv_tablePrimaryKeyColumnsValues_26_0= RULE_STRING ) ) )* )? otherlv_27= ']' otherlv_28= ';' ) ) ) )
            	    // InternalHdbTable.g:568:4: {...}? => ( ({...}? => ( ( (lv_primaryKeyColumns_21_0= 'table.primaryKey.pkcolumns' ) ) otherlv_22= '=' otherlv_23= '[' ( ( (lv_tablePrimaryKeyColumnsValues_24_0= RULE_STRING ) ) (otherlv_25= ',' ( (lv_tablePrimaryKeyColumnsValues_26_0= RULE_STRING ) ) )* )? otherlv_27= ']' otherlv_28= ';' ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getTableAccess().getUnorderedGroup(), 4) ) {
            	        throw new FailedPredicateException(input, "ruleTable", "getUnorderedGroupHelper().canSelect(grammarAccess.getTableAccess().getUnorderedGroup(), 4)");
            	    }
            	    // InternalHdbTable.g:568:99: ( ({...}? => ( ( (lv_primaryKeyColumns_21_0= 'table.primaryKey.pkcolumns' ) ) otherlv_22= '=' otherlv_23= '[' ( ( (lv_tablePrimaryKeyColumnsValues_24_0= RULE_STRING ) ) (otherlv_25= ',' ( (lv_tablePrimaryKeyColumnsValues_26_0= RULE_STRING ) ) )* )? otherlv_27= ']' otherlv_28= ';' ) ) )
            	    // InternalHdbTable.g:569:5: ({...}? => ( ( (lv_primaryKeyColumns_21_0= 'table.primaryKey.pkcolumns' ) ) otherlv_22= '=' otherlv_23= '[' ( ( (lv_tablePrimaryKeyColumnsValues_24_0= RULE_STRING ) ) (otherlv_25= ',' ( (lv_tablePrimaryKeyColumnsValues_26_0= RULE_STRING ) ) )* )? otherlv_27= ']' otherlv_28= ';' ) )
            	    {

            	    					getUnorderedGroupHelper().select(grammarAccess.getTableAccess().getUnorderedGroup(), 4);
            	    				
            	    // InternalHdbTable.g:572:8: ({...}? => ( ( (lv_primaryKeyColumns_21_0= 'table.primaryKey.pkcolumns' ) ) otherlv_22= '=' otherlv_23= '[' ( ( (lv_tablePrimaryKeyColumnsValues_24_0= RULE_STRING ) ) (otherlv_25= ',' ( (lv_tablePrimaryKeyColumnsValues_26_0= RULE_STRING ) ) )* )? otherlv_27= ']' otherlv_28= ';' ) )
            	    // InternalHdbTable.g:572:9: {...}? => ( ( (lv_primaryKeyColumns_21_0= 'table.primaryKey.pkcolumns' ) ) otherlv_22= '=' otherlv_23= '[' ( ( (lv_tablePrimaryKeyColumnsValues_24_0= RULE_STRING ) ) (otherlv_25= ',' ( (lv_tablePrimaryKeyColumnsValues_26_0= RULE_STRING ) ) )* )? otherlv_27= ']' otherlv_28= ';' )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleTable", "true");
            	    }
            	    // InternalHdbTable.g:572:18: ( ( (lv_primaryKeyColumns_21_0= 'table.primaryKey.pkcolumns' ) ) otherlv_22= '=' otherlv_23= '[' ( ( (lv_tablePrimaryKeyColumnsValues_24_0= RULE_STRING ) ) (otherlv_25= ',' ( (lv_tablePrimaryKeyColumnsValues_26_0= RULE_STRING ) ) )* )? otherlv_27= ']' otherlv_28= ';' )
            	    // InternalHdbTable.g:572:19: ( (lv_primaryKeyColumns_21_0= 'table.primaryKey.pkcolumns' ) ) otherlv_22= '=' otherlv_23= '[' ( ( (lv_tablePrimaryKeyColumnsValues_24_0= RULE_STRING ) ) (otherlv_25= ',' ( (lv_tablePrimaryKeyColumnsValues_26_0= RULE_STRING ) ) )* )? otherlv_27= ']' otherlv_28= ';'
            	    {
            	    // InternalHdbTable.g:572:19: ( (lv_primaryKeyColumns_21_0= 'table.primaryKey.pkcolumns' ) )
            	    // InternalHdbTable.g:573:9: (lv_primaryKeyColumns_21_0= 'table.primaryKey.pkcolumns' )
            	    {
            	    // InternalHdbTable.g:573:9: (lv_primaryKeyColumns_21_0= 'table.primaryKey.pkcolumns' )
            	    // InternalHdbTable.g:574:10: lv_primaryKeyColumns_21_0= 'table.primaryKey.pkcolumns'
            	    {
            	    lv_primaryKeyColumns_21_0=(Token)match(input,27,FOLLOW_4); 

            	    										newLeafNode(lv_primaryKeyColumns_21_0, grammarAccess.getTableAccess().getPrimaryKeyColumnsTablePrimaryKeyPkcolumnsKeyword_4_0_0());
            	    									

            	    										if (current==null) {
            	    											current = createModelElement(grammarAccess.getTableRule());
            	    										}
            	    										setWithLastConsumed(current, "primaryKeyColumns", true, "table.primaryKey.pkcolumns");
            	    									

            	    }


            	    }

            	    otherlv_22=(Token)match(input,14,FOLLOW_13); 

            	    								newLeafNode(otherlv_22, grammarAccess.getTableAccess().getEqualsSignKeyword_4_1());
            	    							
            	    otherlv_23=(Token)match(input,24,FOLLOW_17); 

            	    								newLeafNode(otherlv_23, grammarAccess.getTableAccess().getLeftSquareBracketKeyword_4_2());
            	    							
            	    // InternalHdbTable.g:594:8: ( ( (lv_tablePrimaryKeyColumnsValues_24_0= RULE_STRING ) ) (otherlv_25= ',' ( (lv_tablePrimaryKeyColumnsValues_26_0= RULE_STRING ) ) )* )?
            	    int alt6=2;
            	    int LA6_0 = input.LA(1);

            	    if ( (LA6_0==RULE_STRING) ) {
            	        alt6=1;
            	    }
            	    switch (alt6) {
            	        case 1 :
            	            // InternalHdbTable.g:595:9: ( (lv_tablePrimaryKeyColumnsValues_24_0= RULE_STRING ) ) (otherlv_25= ',' ( (lv_tablePrimaryKeyColumnsValues_26_0= RULE_STRING ) ) )*
            	            {
            	            // InternalHdbTable.g:595:9: ( (lv_tablePrimaryKeyColumnsValues_24_0= RULE_STRING ) )
            	            // InternalHdbTable.g:596:10: (lv_tablePrimaryKeyColumnsValues_24_0= RULE_STRING )
            	            {
            	            // InternalHdbTable.g:596:10: (lv_tablePrimaryKeyColumnsValues_24_0= RULE_STRING )
            	            // InternalHdbTable.g:597:11: lv_tablePrimaryKeyColumnsValues_24_0= RULE_STRING
            	            {
            	            lv_tablePrimaryKeyColumnsValues_24_0=(Token)match(input,RULE_STRING,FOLLOW_15); 

            	            											newLeafNode(lv_tablePrimaryKeyColumnsValues_24_0, grammarAccess.getTableAccess().getTablePrimaryKeyColumnsValuesSTRINGTerminalRuleCall_4_3_0_0());
            	            										

            	            											if (current==null) {
            	            												current = createModelElement(grammarAccess.getTableRule());
            	            											}
            	            											addWithLastConsumed(
            	            												current,
            	            												"tablePrimaryKeyColumnsValues",
            	            												lv_tablePrimaryKeyColumnsValues_24_0,
            	            												"org.eclipse.xtext.common.Terminals.STRING");
            	            										

            	            }


            	            }

            	            // InternalHdbTable.g:613:9: (otherlv_25= ',' ( (lv_tablePrimaryKeyColumnsValues_26_0= RULE_STRING ) ) )*
            	            loop5:
            	            do {
            	                int alt5=2;
            	                int LA5_0 = input.LA(1);

            	                if ( (LA5_0==25) ) {
            	                    alt5=1;
            	                }


            	                switch (alt5) {
            	            	case 1 :
            	            	    // InternalHdbTable.g:614:10: otherlv_25= ',' ( (lv_tablePrimaryKeyColumnsValues_26_0= RULE_STRING ) )
            	            	    {
            	            	    otherlv_25=(Token)match(input,25,FOLLOW_5); 

            	            	    										newLeafNode(otherlv_25, grammarAccess.getTableAccess().getCommaKeyword_4_3_1_0());
            	            	    									
            	            	    // InternalHdbTable.g:618:10: ( (lv_tablePrimaryKeyColumnsValues_26_0= RULE_STRING ) )
            	            	    // InternalHdbTable.g:619:11: (lv_tablePrimaryKeyColumnsValues_26_0= RULE_STRING )
            	            	    {
            	            	    // InternalHdbTable.g:619:11: (lv_tablePrimaryKeyColumnsValues_26_0= RULE_STRING )
            	            	    // InternalHdbTable.g:620:12: lv_tablePrimaryKeyColumnsValues_26_0= RULE_STRING
            	            	    {
            	            	    lv_tablePrimaryKeyColumnsValues_26_0=(Token)match(input,RULE_STRING,FOLLOW_15); 

            	            	    												newLeafNode(lv_tablePrimaryKeyColumnsValues_26_0, grammarAccess.getTableAccess().getTablePrimaryKeyColumnsValuesSTRINGTerminalRuleCall_4_3_1_1_0());
            	            	    											

            	            	    												if (current==null) {
            	            	    													current = createModelElement(grammarAccess.getTableRule());
            	            	    												}
            	            	    												addWithLastConsumed(
            	            	    													current,
            	            	    													"tablePrimaryKeyColumnsValues",
            	            	    													lv_tablePrimaryKeyColumnsValues_26_0,
            	            	    													"org.eclipse.xtext.common.Terminals.STRING");
            	            	    											

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

            	    otherlv_27=(Token)match(input,26,FOLLOW_6); 

            	    								newLeafNode(otherlv_27, grammarAccess.getTableAccess().getRightSquareBracketKeyword_4_4());
            	    							
            	    otherlv_28=(Token)match(input,15,FOLLOW_12); 

            	    								newLeafNode(otherlv_28, grammarAccess.getTableAccess().getSemicolonKeyword_4_5());
            	    							

            	    }


            	    }

            	     
            	    					getUnorderedGroupHelper().returnFromSelection(grammarAccess.getTableAccess().getUnorderedGroup());
            	    				

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt7 >= 1 ) break loop7;
                        EarlyExitException eee =
                            new EarlyExitException(7, input);
                        throw eee;
                }
                cnt7++;
            } while (true);

            if ( ! getUnorderedGroupHelper().canLeave(grammarAccess.getTableAccess().getUnorderedGroup()) ) {
                throw new FailedPredicateException(input, "ruleTable", "getUnorderedGroupHelper().canLeave(grammarAccess.getTableAccess().getUnorderedGroup())");
            }

            }


            }

             
            			  getUnorderedGroupHelper().leave(grammarAccess.getTableAccess().getUnorderedGroup());
            			

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
    // $ANTLR end "ruleTable"

    // Delegated rules


 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000079002L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000008F00002L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000004079000L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000006000000L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000000079000L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000004000010L});

}
