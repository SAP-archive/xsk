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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_STRING", "RULE_ID", "RULE_INT", "RULE_BOOL", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'{'", "'name'", "'='", "';'", "'sqlType'", "'length'", "'comment'", "'defaultValue'", "'precision'", "'scale'", "'nullable'", "'}'", "'unique'", "'order'", "'indexColumns'", "'['", "','", "']'", "'table.schemaName'", "'table.tableType'", "'table.description'", "'table.columns'", "'table.indexes'", "'table.primaryKey.pkcolumns'"
    };
    public static final int RULE_STRING=4;
    public static final int RULE_SL_COMMENT=9;
    public static final int T__19=19;
    public static final int T__15=15;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__33=33;
    public static final int T__12=12;
    public static final int T__34=34;
    public static final int T__13=13;
    public static final int T__35=35;
    public static final int T__14=14;
    public static final int EOF=-1;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int RULE_ID=5;
    public static final int RULE_WS=10;
    public static final int RULE_ANY_OTHER=11;
    public static final int RULE_BOOL=7;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int RULE_INT=6;
    public static final int T__29=29;
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
    // InternalHdbTable.g:107:1: ruleColumnType returns [EObject current=null] : ( ( ( ( ({...}? => ( ({...}? => (otherlv_1= '{' otherlv_2= 'name' otherlv_3= '=' ( (lv_columnName_4_0= RULE_STRING ) ) otherlv_5= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'sqlType' otherlv_7= '=' ( (lv_columnSqlType_8_0= RULE_ID ) ) otherlv_9= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'length' otherlv_11= '=' ( (lv_columnLength_12_0= RULE_INT ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'comment' otherlv_15= '=' ( (lv_columnComment_16_0= RULE_STRING ) ) otherlv_17= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_18= 'defaultValue' otherlv_19= '=' ( (lv_columnDefaultValue_20_0= RULE_STRING ) ) otherlv_21= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_22= 'precision' otherlv_23= '=' ( (lv_columnPrecision_24_0= RULE_INT ) ) otherlv_25= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_26= 'scale' otherlv_27= '=' ( (lv_columnScale_28_0= RULE_INT ) ) otherlv_29= ';' ) ) ) ) | ({...}? => ( ({...}? => ( (otherlv_30= 'nullable' otherlv_31= '=' ( (lv_columnNullable_32_0= RULE_BOOL ) ) )? otherlv_33= ';' otherlv_34= '}' ) ) ) ) )+ {...}?) ) ) ;
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
        Token lv_columnComment_16_0=null;
        Token otherlv_17=null;
        Token otherlv_18=null;
        Token otherlv_19=null;
        Token lv_columnDefaultValue_20_0=null;
        Token otherlv_21=null;
        Token otherlv_22=null;
        Token otherlv_23=null;
        Token lv_columnPrecision_24_0=null;
        Token otherlv_25=null;
        Token otherlv_26=null;
        Token otherlv_27=null;
        Token lv_columnScale_28_0=null;
        Token otherlv_29=null;
        Token otherlv_30=null;
        Token otherlv_31=null;
        Token lv_columnNullable_32_0=null;
        Token otherlv_33=null;
        Token otherlv_34=null;


        	enterRule();

        try {
            // InternalHdbTable.g:113:2: ( ( ( ( ( ({...}? => ( ({...}? => (otherlv_1= '{' otherlv_2= 'name' otherlv_3= '=' ( (lv_columnName_4_0= RULE_STRING ) ) otherlv_5= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'sqlType' otherlv_7= '=' ( (lv_columnSqlType_8_0= RULE_ID ) ) otherlv_9= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'length' otherlv_11= '=' ( (lv_columnLength_12_0= RULE_INT ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'comment' otherlv_15= '=' ( (lv_columnComment_16_0= RULE_STRING ) ) otherlv_17= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_18= 'defaultValue' otherlv_19= '=' ( (lv_columnDefaultValue_20_0= RULE_STRING ) ) otherlv_21= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_22= 'precision' otherlv_23= '=' ( (lv_columnPrecision_24_0= RULE_INT ) ) otherlv_25= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_26= 'scale' otherlv_27= '=' ( (lv_columnScale_28_0= RULE_INT ) ) otherlv_29= ';' ) ) ) ) | ({...}? => ( ({...}? => ( (otherlv_30= 'nullable' otherlv_31= '=' ( (lv_columnNullable_32_0= RULE_BOOL ) ) )? otherlv_33= ';' otherlv_34= '}' ) ) ) ) )+ {...}?) ) ) )
            // InternalHdbTable.g:114:2: ( ( ( ( ({...}? => ( ({...}? => (otherlv_1= '{' otherlv_2= 'name' otherlv_3= '=' ( (lv_columnName_4_0= RULE_STRING ) ) otherlv_5= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'sqlType' otherlv_7= '=' ( (lv_columnSqlType_8_0= RULE_ID ) ) otherlv_9= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'length' otherlv_11= '=' ( (lv_columnLength_12_0= RULE_INT ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'comment' otherlv_15= '=' ( (lv_columnComment_16_0= RULE_STRING ) ) otherlv_17= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_18= 'defaultValue' otherlv_19= '=' ( (lv_columnDefaultValue_20_0= RULE_STRING ) ) otherlv_21= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_22= 'precision' otherlv_23= '=' ( (lv_columnPrecision_24_0= RULE_INT ) ) otherlv_25= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_26= 'scale' otherlv_27= '=' ( (lv_columnScale_28_0= RULE_INT ) ) otherlv_29= ';' ) ) ) ) | ({...}? => ( ({...}? => ( (otherlv_30= 'nullable' otherlv_31= '=' ( (lv_columnNullable_32_0= RULE_BOOL ) ) )? otherlv_33= ';' otherlv_34= '}' ) ) ) ) )+ {...}?) ) )
            {
            // InternalHdbTable.g:114:2: ( ( ( ( ({...}? => ( ({...}? => (otherlv_1= '{' otherlv_2= 'name' otherlv_3= '=' ( (lv_columnName_4_0= RULE_STRING ) ) otherlv_5= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'sqlType' otherlv_7= '=' ( (lv_columnSqlType_8_0= RULE_ID ) ) otherlv_9= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'length' otherlv_11= '=' ( (lv_columnLength_12_0= RULE_INT ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'comment' otherlv_15= '=' ( (lv_columnComment_16_0= RULE_STRING ) ) otherlv_17= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_18= 'defaultValue' otherlv_19= '=' ( (lv_columnDefaultValue_20_0= RULE_STRING ) ) otherlv_21= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_22= 'precision' otherlv_23= '=' ( (lv_columnPrecision_24_0= RULE_INT ) ) otherlv_25= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_26= 'scale' otherlv_27= '=' ( (lv_columnScale_28_0= RULE_INT ) ) otherlv_29= ';' ) ) ) ) | ({...}? => ( ({...}? => ( (otherlv_30= 'nullable' otherlv_31= '=' ( (lv_columnNullable_32_0= RULE_BOOL ) ) )? otherlv_33= ';' otherlv_34= '}' ) ) ) ) )+ {...}?) ) )
            // InternalHdbTable.g:115:3: ( ( ( ({...}? => ( ({...}? => (otherlv_1= '{' otherlv_2= 'name' otherlv_3= '=' ( (lv_columnName_4_0= RULE_STRING ) ) otherlv_5= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'sqlType' otherlv_7= '=' ( (lv_columnSqlType_8_0= RULE_ID ) ) otherlv_9= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'length' otherlv_11= '=' ( (lv_columnLength_12_0= RULE_INT ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'comment' otherlv_15= '=' ( (lv_columnComment_16_0= RULE_STRING ) ) otherlv_17= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_18= 'defaultValue' otherlv_19= '=' ( (lv_columnDefaultValue_20_0= RULE_STRING ) ) otherlv_21= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_22= 'precision' otherlv_23= '=' ( (lv_columnPrecision_24_0= RULE_INT ) ) otherlv_25= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_26= 'scale' otherlv_27= '=' ( (lv_columnScale_28_0= RULE_INT ) ) otherlv_29= ';' ) ) ) ) | ({...}? => ( ({...}? => ( (otherlv_30= 'nullable' otherlv_31= '=' ( (lv_columnNullable_32_0= RULE_BOOL ) ) )? otherlv_33= ';' otherlv_34= '}' ) ) ) ) )+ {...}?) )
            {
            // InternalHdbTable.g:115:3: ( ( ( ({...}? => ( ({...}? => (otherlv_1= '{' otherlv_2= 'name' otherlv_3= '=' ( (lv_columnName_4_0= RULE_STRING ) ) otherlv_5= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'sqlType' otherlv_7= '=' ( (lv_columnSqlType_8_0= RULE_ID ) ) otherlv_9= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'length' otherlv_11= '=' ( (lv_columnLength_12_0= RULE_INT ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'comment' otherlv_15= '=' ( (lv_columnComment_16_0= RULE_STRING ) ) otherlv_17= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_18= 'defaultValue' otherlv_19= '=' ( (lv_columnDefaultValue_20_0= RULE_STRING ) ) otherlv_21= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_22= 'precision' otherlv_23= '=' ( (lv_columnPrecision_24_0= RULE_INT ) ) otherlv_25= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_26= 'scale' otherlv_27= '=' ( (lv_columnScale_28_0= RULE_INT ) ) otherlv_29= ';' ) ) ) ) | ({...}? => ( ({...}? => ( (otherlv_30= 'nullable' otherlv_31= '=' ( (lv_columnNullable_32_0= RULE_BOOL ) ) )? otherlv_33= ';' otherlv_34= '}' ) ) ) ) )+ {...}?) )
            // InternalHdbTable.g:116:4: ( ( ({...}? => ( ({...}? => (otherlv_1= '{' otherlv_2= 'name' otherlv_3= '=' ( (lv_columnName_4_0= RULE_STRING ) ) otherlv_5= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'sqlType' otherlv_7= '=' ( (lv_columnSqlType_8_0= RULE_ID ) ) otherlv_9= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'length' otherlv_11= '=' ( (lv_columnLength_12_0= RULE_INT ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'comment' otherlv_15= '=' ( (lv_columnComment_16_0= RULE_STRING ) ) otherlv_17= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_18= 'defaultValue' otherlv_19= '=' ( (lv_columnDefaultValue_20_0= RULE_STRING ) ) otherlv_21= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_22= 'precision' otherlv_23= '=' ( (lv_columnPrecision_24_0= RULE_INT ) ) otherlv_25= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_26= 'scale' otherlv_27= '=' ( (lv_columnScale_28_0= RULE_INT ) ) otherlv_29= ';' ) ) ) ) | ({...}? => ( ({...}? => ( (otherlv_30= 'nullable' otherlv_31= '=' ( (lv_columnNullable_32_0= RULE_BOOL ) ) )? otherlv_33= ';' otherlv_34= '}' ) ) ) ) )+ {...}?)
            {
             
            			  getUnorderedGroupHelper().enter(grammarAccess.getColumnTypeAccess().getUnorderedGroup());
            			
            // InternalHdbTable.g:119:4: ( ( ({...}? => ( ({...}? => (otherlv_1= '{' otherlv_2= 'name' otherlv_3= '=' ( (lv_columnName_4_0= RULE_STRING ) ) otherlv_5= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'sqlType' otherlv_7= '=' ( (lv_columnSqlType_8_0= RULE_ID ) ) otherlv_9= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'length' otherlv_11= '=' ( (lv_columnLength_12_0= RULE_INT ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'comment' otherlv_15= '=' ( (lv_columnComment_16_0= RULE_STRING ) ) otherlv_17= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_18= 'defaultValue' otherlv_19= '=' ( (lv_columnDefaultValue_20_0= RULE_STRING ) ) otherlv_21= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_22= 'precision' otherlv_23= '=' ( (lv_columnPrecision_24_0= RULE_INT ) ) otherlv_25= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_26= 'scale' otherlv_27= '=' ( (lv_columnScale_28_0= RULE_INT ) ) otherlv_29= ';' ) ) ) ) | ({...}? => ( ({...}? => ( (otherlv_30= 'nullable' otherlv_31= '=' ( (lv_columnNullable_32_0= RULE_BOOL ) ) )? otherlv_33= ';' otherlv_34= '}' ) ) ) ) )+ {...}?)
            // InternalHdbTable.g:120:5: ( ({...}? => ( ({...}? => (otherlv_1= '{' otherlv_2= 'name' otherlv_3= '=' ( (lv_columnName_4_0= RULE_STRING ) ) otherlv_5= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'sqlType' otherlv_7= '=' ( (lv_columnSqlType_8_0= RULE_ID ) ) otherlv_9= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'length' otherlv_11= '=' ( (lv_columnLength_12_0= RULE_INT ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'comment' otherlv_15= '=' ( (lv_columnComment_16_0= RULE_STRING ) ) otherlv_17= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_18= 'defaultValue' otherlv_19= '=' ( (lv_columnDefaultValue_20_0= RULE_STRING ) ) otherlv_21= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_22= 'precision' otherlv_23= '=' ( (lv_columnPrecision_24_0= RULE_INT ) ) otherlv_25= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_26= 'scale' otherlv_27= '=' ( (lv_columnScale_28_0= RULE_INT ) ) otherlv_29= ';' ) ) ) ) | ({...}? => ( ({...}? => ( (otherlv_30= 'nullable' otherlv_31= '=' ( (lv_columnNullable_32_0= RULE_BOOL ) ) )? otherlv_33= ';' otherlv_34= '}' ) ) ) ) )+ {...}?
            {
            // InternalHdbTable.g:120:5: ( ({...}? => ( ({...}? => (otherlv_1= '{' otherlv_2= 'name' otherlv_3= '=' ( (lv_columnName_4_0= RULE_STRING ) ) otherlv_5= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'sqlType' otherlv_7= '=' ( (lv_columnSqlType_8_0= RULE_ID ) ) otherlv_9= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'length' otherlv_11= '=' ( (lv_columnLength_12_0= RULE_INT ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'comment' otherlv_15= '=' ( (lv_columnComment_16_0= RULE_STRING ) ) otherlv_17= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_18= 'defaultValue' otherlv_19= '=' ( (lv_columnDefaultValue_20_0= RULE_STRING ) ) otherlv_21= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_22= 'precision' otherlv_23= '=' ( (lv_columnPrecision_24_0= RULE_INT ) ) otherlv_25= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_26= 'scale' otherlv_27= '=' ( (lv_columnScale_28_0= RULE_INT ) ) otherlv_29= ';' ) ) ) ) | ({...}? => ( ({...}? => ( (otherlv_30= 'nullable' otherlv_31= '=' ( (lv_columnNullable_32_0= RULE_BOOL ) ) )? otherlv_33= ';' otherlv_34= '}' ) ) ) ) )+
            int cnt2=0;
            loop2:
            do {
                int alt2=9;
                alt2 = dfa2.predict(input);
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
            	    // InternalHdbTable.g:248:3: ({...}? => ( ({...}? => (otherlv_14= 'comment' otherlv_15= '=' ( (lv_columnComment_16_0= RULE_STRING ) ) otherlv_17= ';' ) ) ) )
            	    {
            	    // InternalHdbTable.g:248:3: ({...}? => ( ({...}? => (otherlv_14= 'comment' otherlv_15= '=' ( (lv_columnComment_16_0= RULE_STRING ) ) otherlv_17= ';' ) ) ) )
            	    // InternalHdbTable.g:249:4: {...}? => ( ({...}? => (otherlv_14= 'comment' otherlv_15= '=' ( (lv_columnComment_16_0= RULE_STRING ) ) otherlv_17= ';' ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 3) ) {
            	        throw new FailedPredicateException(input, "ruleColumnType", "getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 3)");
            	    }
            	    // InternalHdbTable.g:249:104: ( ({...}? => (otherlv_14= 'comment' otherlv_15= '=' ( (lv_columnComment_16_0= RULE_STRING ) ) otherlv_17= ';' ) ) )
            	    // InternalHdbTable.g:250:5: ({...}? => (otherlv_14= 'comment' otherlv_15= '=' ( (lv_columnComment_16_0= RULE_STRING ) ) otherlv_17= ';' ) )
            	    {

            	    					getUnorderedGroupHelper().select(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 3);
            	    				
            	    // InternalHdbTable.g:253:8: ({...}? => (otherlv_14= 'comment' otherlv_15= '=' ( (lv_columnComment_16_0= RULE_STRING ) ) otherlv_17= ';' ) )
            	    // InternalHdbTable.g:253:9: {...}? => (otherlv_14= 'comment' otherlv_15= '=' ( (lv_columnComment_16_0= RULE_STRING ) ) otherlv_17= ';' )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleColumnType", "true");
            	    }
            	    // InternalHdbTable.g:253:18: (otherlv_14= 'comment' otherlv_15= '=' ( (lv_columnComment_16_0= RULE_STRING ) ) otherlv_17= ';' )
            	    // InternalHdbTable.g:253:19: otherlv_14= 'comment' otherlv_15= '=' ( (lv_columnComment_16_0= RULE_STRING ) ) otherlv_17= ';'
            	    {
            	    otherlv_14=(Token)match(input,18,FOLLOW_4); 

            	    								newLeafNode(otherlv_14, grammarAccess.getColumnTypeAccess().getCommentKeyword_3_0());
            	    							
            	    otherlv_15=(Token)match(input,14,FOLLOW_5); 

            	    								newLeafNode(otherlv_15, grammarAccess.getColumnTypeAccess().getEqualsSignKeyword_3_1());
            	    							
            	    // InternalHdbTable.g:261:8: ( (lv_columnComment_16_0= RULE_STRING ) )
            	    // InternalHdbTable.g:262:9: (lv_columnComment_16_0= RULE_STRING )
            	    {
            	    // InternalHdbTable.g:262:9: (lv_columnComment_16_0= RULE_STRING )
            	    // InternalHdbTable.g:263:10: lv_columnComment_16_0= RULE_STRING
            	    {
            	    lv_columnComment_16_0=(Token)match(input,RULE_STRING,FOLLOW_6); 

            	    										newLeafNode(lv_columnComment_16_0, grammarAccess.getColumnTypeAccess().getColumnCommentSTRINGTerminalRuleCall_3_2_0());
            	    									

            	    										if (current==null) {
            	    											current = createModelElement(grammarAccess.getColumnTypeRule());
            	    										}
            	    										setWithLastConsumed(
            	    											current,
            	    											"columnComment",
            	    											lv_columnComment_16_0,
            	    											"org.eclipse.xtext.common.Terminals.STRING");
            	    									

            	    }


            	    }

            	    otherlv_17=(Token)match(input,15,FOLLOW_7); 

            	    								newLeafNode(otherlv_17, grammarAccess.getColumnTypeAccess().getSemicolonKeyword_3_3());
            	    							

            	    }


            	    }

            	     
            	    					getUnorderedGroupHelper().returnFromSelection(grammarAccess.getColumnTypeAccess().getUnorderedGroup());
            	    				

            	    }


            	    }


            	    }
            	    break;
            	case 5 :
            	    // InternalHdbTable.g:289:3: ({...}? => ( ({...}? => (otherlv_18= 'defaultValue' otherlv_19= '=' ( (lv_columnDefaultValue_20_0= RULE_STRING ) ) otherlv_21= ';' ) ) ) )
            	    {
            	    // InternalHdbTable.g:289:3: ({...}? => ( ({...}? => (otherlv_18= 'defaultValue' otherlv_19= '=' ( (lv_columnDefaultValue_20_0= RULE_STRING ) ) otherlv_21= ';' ) ) ) )
            	    // InternalHdbTable.g:290:4: {...}? => ( ({...}? => (otherlv_18= 'defaultValue' otherlv_19= '=' ( (lv_columnDefaultValue_20_0= RULE_STRING ) ) otherlv_21= ';' ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 4) ) {
            	        throw new FailedPredicateException(input, "ruleColumnType", "getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 4)");
            	    }
            	    // InternalHdbTable.g:290:104: ( ({...}? => (otherlv_18= 'defaultValue' otherlv_19= '=' ( (lv_columnDefaultValue_20_0= RULE_STRING ) ) otherlv_21= ';' ) ) )
            	    // InternalHdbTable.g:291:5: ({...}? => (otherlv_18= 'defaultValue' otherlv_19= '=' ( (lv_columnDefaultValue_20_0= RULE_STRING ) ) otherlv_21= ';' ) )
            	    {

            	    					getUnorderedGroupHelper().select(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 4);
            	    				
            	    // InternalHdbTable.g:294:8: ({...}? => (otherlv_18= 'defaultValue' otherlv_19= '=' ( (lv_columnDefaultValue_20_0= RULE_STRING ) ) otherlv_21= ';' ) )
            	    // InternalHdbTable.g:294:9: {...}? => (otherlv_18= 'defaultValue' otherlv_19= '=' ( (lv_columnDefaultValue_20_0= RULE_STRING ) ) otherlv_21= ';' )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleColumnType", "true");
            	    }
            	    // InternalHdbTable.g:294:18: (otherlv_18= 'defaultValue' otherlv_19= '=' ( (lv_columnDefaultValue_20_0= RULE_STRING ) ) otherlv_21= ';' )
            	    // InternalHdbTable.g:294:19: otherlv_18= 'defaultValue' otherlv_19= '=' ( (lv_columnDefaultValue_20_0= RULE_STRING ) ) otherlv_21= ';'
            	    {
            	    otherlv_18=(Token)match(input,19,FOLLOW_4); 

            	    								newLeafNode(otherlv_18, grammarAccess.getColumnTypeAccess().getDefaultValueKeyword_4_0());
            	    							
            	    otherlv_19=(Token)match(input,14,FOLLOW_5); 

            	    								newLeafNode(otherlv_19, grammarAccess.getColumnTypeAccess().getEqualsSignKeyword_4_1());
            	    							
            	    // InternalHdbTable.g:302:8: ( (lv_columnDefaultValue_20_0= RULE_STRING ) )
            	    // InternalHdbTable.g:303:9: (lv_columnDefaultValue_20_0= RULE_STRING )
            	    {
            	    // InternalHdbTable.g:303:9: (lv_columnDefaultValue_20_0= RULE_STRING )
            	    // InternalHdbTable.g:304:10: lv_columnDefaultValue_20_0= RULE_STRING
            	    {
            	    lv_columnDefaultValue_20_0=(Token)match(input,RULE_STRING,FOLLOW_6); 

            	    										newLeafNode(lv_columnDefaultValue_20_0, grammarAccess.getColumnTypeAccess().getColumnDefaultValueSTRINGTerminalRuleCall_4_2_0());
            	    									

            	    										if (current==null) {
            	    											current = createModelElement(grammarAccess.getColumnTypeRule());
            	    										}
            	    										setWithLastConsumed(
            	    											current,
            	    											"columnDefaultValue",
            	    											lv_columnDefaultValue_20_0,
            	    											"org.eclipse.xtext.common.Terminals.STRING");
            	    									

            	    }


            	    }

            	    otherlv_21=(Token)match(input,15,FOLLOW_7); 

            	    								newLeafNode(otherlv_21, grammarAccess.getColumnTypeAccess().getSemicolonKeyword_4_3());
            	    							

            	    }


            	    }

            	     
            	    					getUnorderedGroupHelper().returnFromSelection(grammarAccess.getColumnTypeAccess().getUnorderedGroup());
            	    				

            	    }


            	    }


            	    }
            	    break;
            	case 6 :
            	    // InternalHdbTable.g:330:3: ({...}? => ( ({...}? => (otherlv_22= 'precision' otherlv_23= '=' ( (lv_columnPrecision_24_0= RULE_INT ) ) otherlv_25= ';' ) ) ) )
            	    {
            	    // InternalHdbTable.g:330:3: ({...}? => ( ({...}? => (otherlv_22= 'precision' otherlv_23= '=' ( (lv_columnPrecision_24_0= RULE_INT ) ) otherlv_25= ';' ) ) ) )
            	    // InternalHdbTable.g:331:4: {...}? => ( ({...}? => (otherlv_22= 'precision' otherlv_23= '=' ( (lv_columnPrecision_24_0= RULE_INT ) ) otherlv_25= ';' ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 5) ) {
            	        throw new FailedPredicateException(input, "ruleColumnType", "getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 5)");
            	    }
            	    // InternalHdbTable.g:331:104: ( ({...}? => (otherlv_22= 'precision' otherlv_23= '=' ( (lv_columnPrecision_24_0= RULE_INT ) ) otherlv_25= ';' ) ) )
            	    // InternalHdbTable.g:332:5: ({...}? => (otherlv_22= 'precision' otherlv_23= '=' ( (lv_columnPrecision_24_0= RULE_INT ) ) otherlv_25= ';' ) )
            	    {

            	    					getUnorderedGroupHelper().select(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 5);
            	    				
            	    // InternalHdbTable.g:335:8: ({...}? => (otherlv_22= 'precision' otherlv_23= '=' ( (lv_columnPrecision_24_0= RULE_INT ) ) otherlv_25= ';' ) )
            	    // InternalHdbTable.g:335:9: {...}? => (otherlv_22= 'precision' otherlv_23= '=' ( (lv_columnPrecision_24_0= RULE_INT ) ) otherlv_25= ';' )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleColumnType", "true");
            	    }
            	    // InternalHdbTable.g:335:18: (otherlv_22= 'precision' otherlv_23= '=' ( (lv_columnPrecision_24_0= RULE_INT ) ) otherlv_25= ';' )
            	    // InternalHdbTable.g:335:19: otherlv_22= 'precision' otherlv_23= '=' ( (lv_columnPrecision_24_0= RULE_INT ) ) otherlv_25= ';'
            	    {
            	    otherlv_22=(Token)match(input,20,FOLLOW_4); 

            	    								newLeafNode(otherlv_22, grammarAccess.getColumnTypeAccess().getPrecisionKeyword_5_0());
            	    							
            	    otherlv_23=(Token)match(input,14,FOLLOW_9); 

            	    								newLeafNode(otherlv_23, grammarAccess.getColumnTypeAccess().getEqualsSignKeyword_5_1());
            	    							
            	    // InternalHdbTable.g:343:8: ( (lv_columnPrecision_24_0= RULE_INT ) )
            	    // InternalHdbTable.g:344:9: (lv_columnPrecision_24_0= RULE_INT )
            	    {
            	    // InternalHdbTable.g:344:9: (lv_columnPrecision_24_0= RULE_INT )
            	    // InternalHdbTable.g:345:10: lv_columnPrecision_24_0= RULE_INT
            	    {
            	    lv_columnPrecision_24_0=(Token)match(input,RULE_INT,FOLLOW_6); 

            	    										newLeafNode(lv_columnPrecision_24_0, grammarAccess.getColumnTypeAccess().getColumnPrecisionINTTerminalRuleCall_5_2_0());
            	    									

            	    										if (current==null) {
            	    											current = createModelElement(grammarAccess.getColumnTypeRule());
            	    										}
            	    										setWithLastConsumed(
            	    											current,
            	    											"columnPrecision",
            	    											lv_columnPrecision_24_0,
            	    											"org.eclipse.xtext.common.Terminals.INT");
            	    									

            	    }


            	    }

            	    otherlv_25=(Token)match(input,15,FOLLOW_7); 

            	    								newLeafNode(otherlv_25, grammarAccess.getColumnTypeAccess().getSemicolonKeyword_5_3());
            	    							

            	    }


            	    }

            	     
            	    					getUnorderedGroupHelper().returnFromSelection(grammarAccess.getColumnTypeAccess().getUnorderedGroup());
            	    				

            	    }


            	    }


            	    }
            	    break;
            	case 7 :
            	    // InternalHdbTable.g:371:3: ({...}? => ( ({...}? => (otherlv_26= 'scale' otherlv_27= '=' ( (lv_columnScale_28_0= RULE_INT ) ) otherlv_29= ';' ) ) ) )
            	    {
            	    // InternalHdbTable.g:371:3: ({...}? => ( ({...}? => (otherlv_26= 'scale' otherlv_27= '=' ( (lv_columnScale_28_0= RULE_INT ) ) otherlv_29= ';' ) ) ) )
            	    // InternalHdbTable.g:372:4: {...}? => ( ({...}? => (otherlv_26= 'scale' otherlv_27= '=' ( (lv_columnScale_28_0= RULE_INT ) ) otherlv_29= ';' ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 6) ) {
            	        throw new FailedPredicateException(input, "ruleColumnType", "getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 6)");
            	    }
            	    // InternalHdbTable.g:372:104: ( ({...}? => (otherlv_26= 'scale' otherlv_27= '=' ( (lv_columnScale_28_0= RULE_INT ) ) otherlv_29= ';' ) ) )
            	    // InternalHdbTable.g:373:5: ({...}? => (otherlv_26= 'scale' otherlv_27= '=' ( (lv_columnScale_28_0= RULE_INT ) ) otherlv_29= ';' ) )
            	    {

            	    					getUnorderedGroupHelper().select(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 6);
            	    				
            	    // InternalHdbTable.g:376:8: ({...}? => (otherlv_26= 'scale' otherlv_27= '=' ( (lv_columnScale_28_0= RULE_INT ) ) otherlv_29= ';' ) )
            	    // InternalHdbTable.g:376:9: {...}? => (otherlv_26= 'scale' otherlv_27= '=' ( (lv_columnScale_28_0= RULE_INT ) ) otherlv_29= ';' )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleColumnType", "true");
            	    }
            	    // InternalHdbTable.g:376:18: (otherlv_26= 'scale' otherlv_27= '=' ( (lv_columnScale_28_0= RULE_INT ) ) otherlv_29= ';' )
            	    // InternalHdbTable.g:376:19: otherlv_26= 'scale' otherlv_27= '=' ( (lv_columnScale_28_0= RULE_INT ) ) otherlv_29= ';'
            	    {
            	    otherlv_26=(Token)match(input,21,FOLLOW_4); 

            	    								newLeafNode(otherlv_26, grammarAccess.getColumnTypeAccess().getScaleKeyword_6_0());
            	    							
            	    otherlv_27=(Token)match(input,14,FOLLOW_9); 

            	    								newLeafNode(otherlv_27, grammarAccess.getColumnTypeAccess().getEqualsSignKeyword_6_1());
            	    							
            	    // InternalHdbTable.g:384:8: ( (lv_columnScale_28_0= RULE_INT ) )
            	    // InternalHdbTable.g:385:9: (lv_columnScale_28_0= RULE_INT )
            	    {
            	    // InternalHdbTable.g:385:9: (lv_columnScale_28_0= RULE_INT )
            	    // InternalHdbTable.g:386:10: lv_columnScale_28_0= RULE_INT
            	    {
            	    lv_columnScale_28_0=(Token)match(input,RULE_INT,FOLLOW_6); 

            	    										newLeafNode(lv_columnScale_28_0, grammarAccess.getColumnTypeAccess().getColumnScaleINTTerminalRuleCall_6_2_0());
            	    									

            	    										if (current==null) {
            	    											current = createModelElement(grammarAccess.getColumnTypeRule());
            	    										}
            	    										setWithLastConsumed(
            	    											current,
            	    											"columnScale",
            	    											lv_columnScale_28_0,
            	    											"org.eclipse.xtext.common.Terminals.INT");
            	    									

            	    }


            	    }

            	    otherlv_29=(Token)match(input,15,FOLLOW_7); 

            	    								newLeafNode(otherlv_29, grammarAccess.getColumnTypeAccess().getSemicolonKeyword_6_3());
            	    							

            	    }


            	    }

            	     
            	    					getUnorderedGroupHelper().returnFromSelection(grammarAccess.getColumnTypeAccess().getUnorderedGroup());
            	    				

            	    }


            	    }


            	    }
            	    break;
            	case 8 :
            	    // InternalHdbTable.g:412:3: ({...}? => ( ({...}? => ( (otherlv_30= 'nullable' otherlv_31= '=' ( (lv_columnNullable_32_0= RULE_BOOL ) ) )? otherlv_33= ';' otherlv_34= '}' ) ) ) )
            	    {
            	    // InternalHdbTable.g:412:3: ({...}? => ( ({...}? => ( (otherlv_30= 'nullable' otherlv_31= '=' ( (lv_columnNullable_32_0= RULE_BOOL ) ) )? otherlv_33= ';' otherlv_34= '}' ) ) ) )
            	    // InternalHdbTable.g:413:4: {...}? => ( ({...}? => ( (otherlv_30= 'nullable' otherlv_31= '=' ( (lv_columnNullable_32_0= RULE_BOOL ) ) )? otherlv_33= ';' otherlv_34= '}' ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 7) ) {
            	        throw new FailedPredicateException(input, "ruleColumnType", "getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 7)");
            	    }
            	    // InternalHdbTable.g:413:104: ( ({...}? => ( (otherlv_30= 'nullable' otherlv_31= '=' ( (lv_columnNullable_32_0= RULE_BOOL ) ) )? otherlv_33= ';' otherlv_34= '}' ) ) )
            	    // InternalHdbTable.g:414:5: ({...}? => ( (otherlv_30= 'nullable' otherlv_31= '=' ( (lv_columnNullable_32_0= RULE_BOOL ) ) )? otherlv_33= ';' otherlv_34= '}' ) )
            	    {

            	    					getUnorderedGroupHelper().select(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 7);
            	    				
            	    // InternalHdbTable.g:417:8: ({...}? => ( (otherlv_30= 'nullable' otherlv_31= '=' ( (lv_columnNullable_32_0= RULE_BOOL ) ) )? otherlv_33= ';' otherlv_34= '}' ) )
            	    // InternalHdbTable.g:417:9: {...}? => ( (otherlv_30= 'nullable' otherlv_31= '=' ( (lv_columnNullable_32_0= RULE_BOOL ) ) )? otherlv_33= ';' otherlv_34= '}' )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleColumnType", "true");
            	    }
            	    // InternalHdbTable.g:417:18: ( (otherlv_30= 'nullable' otherlv_31= '=' ( (lv_columnNullable_32_0= RULE_BOOL ) ) )? otherlv_33= ';' otherlv_34= '}' )
            	    // InternalHdbTable.g:417:19: (otherlv_30= 'nullable' otherlv_31= '=' ( (lv_columnNullable_32_0= RULE_BOOL ) ) )? otherlv_33= ';' otherlv_34= '}'
            	    {
            	    // InternalHdbTable.g:417:19: (otherlv_30= 'nullable' otherlv_31= '=' ( (lv_columnNullable_32_0= RULE_BOOL ) ) )?
            	    int alt1=2;
            	    int LA1_0 = input.LA(1);

            	    if ( (LA1_0==22) ) {
            	        alt1=1;
            	    }
            	    switch (alt1) {
            	        case 1 :
            	            // InternalHdbTable.g:418:9: otherlv_30= 'nullable' otherlv_31= '=' ( (lv_columnNullable_32_0= RULE_BOOL ) )
            	            {
            	            otherlv_30=(Token)match(input,22,FOLLOW_4); 

            	            									newLeafNode(otherlv_30, grammarAccess.getColumnTypeAccess().getNullableKeyword_7_0_0());
            	            								
            	            otherlv_31=(Token)match(input,14,FOLLOW_10); 

            	            									newLeafNode(otherlv_31, grammarAccess.getColumnTypeAccess().getEqualsSignKeyword_7_0_1());
            	            								
            	            // InternalHdbTable.g:426:9: ( (lv_columnNullable_32_0= RULE_BOOL ) )
            	            // InternalHdbTable.g:427:10: (lv_columnNullable_32_0= RULE_BOOL )
            	            {
            	            // InternalHdbTable.g:427:10: (lv_columnNullable_32_0= RULE_BOOL )
            	            // InternalHdbTable.g:428:11: lv_columnNullable_32_0= RULE_BOOL
            	            {
            	            lv_columnNullable_32_0=(Token)match(input,RULE_BOOL,FOLLOW_6); 

            	            											newLeafNode(lv_columnNullable_32_0, grammarAccess.getColumnTypeAccess().getColumnNullableBOOLTerminalRuleCall_7_0_2_0());
            	            										

            	            											if (current==null) {
            	            												current = createModelElement(grammarAccess.getColumnTypeRule());
            	            											}
            	            											setWithLastConsumed(
            	            												current,
            	            												"columnNullable",
            	            												lv_columnNullable_32_0,
            	            												"com.sap.xsk.models.hdbtable.HdbTable.BOOL");
            	            										

            	            }


            	            }


            	            }
            	            break;

            	    }

            	    otherlv_33=(Token)match(input,15,FOLLOW_11); 

            	    								newLeafNode(otherlv_33, grammarAccess.getColumnTypeAccess().getSemicolonKeyword_7_1());
            	    							
            	    otherlv_34=(Token)match(input,23,FOLLOW_7); 

            	    								newLeafNode(otherlv_34, grammarAccess.getColumnTypeAccess().getRightCurlyBracketKeyword_7_2());
            	    							

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


    // $ANTLR start "entryRuleIndexType"
    // InternalHdbTable.g:470:1: entryRuleIndexType returns [EObject current=null] : iv_ruleIndexType= ruleIndexType EOF ;
    public final EObject entryRuleIndexType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIndexType = null;


        try {
            // InternalHdbTable.g:470:50: (iv_ruleIndexType= ruleIndexType EOF )
            // InternalHdbTable.g:471:2: iv_ruleIndexType= ruleIndexType EOF
            {
             newCompositeNode(grammarAccess.getIndexTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleIndexType=ruleIndexType();

            state._fsp--;

             current =iv_ruleIndexType; 
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
    // $ANTLR end "entryRuleIndexType"


    // $ANTLR start "ruleIndexType"
    // InternalHdbTable.g:477:1: ruleIndexType returns [EObject current=null] : ( ( ( ( ({...}? => ( ({...}? => (otherlv_1= '{' otherlv_2= 'name' otherlv_3= '=' ( (lv_columnName_4_0= RULE_STRING ) ) otherlv_5= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'unique' otherlv_7= '=' ( (lv_columnUnique_8_0= RULE_BOOL ) ) otherlv_9= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'order' otherlv_11= '=' ( (lv_columnOrder_12_0= RULE_ID ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_indexColumns_14_0= 'indexColumns' ) ) otherlv_15= '=' otherlv_16= '[' ( ( (lv_tableIndexColumnsValues_17_0= RULE_STRING ) ) (otherlv_18= ',' ( (lv_tableIndexColumnsValues_19_0= RULE_STRING ) ) )* )? otherlv_20= ']' otherlv_21= ';' otherlv_22= '}' ) ) ) ) )+ {...}?) ) ) ;
    public final EObject ruleIndexType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token lv_columnName_4_0=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        Token otherlv_7=null;
        Token lv_columnUnique_8_0=null;
        Token otherlv_9=null;
        Token otherlv_10=null;
        Token otherlv_11=null;
        Token lv_columnOrder_12_0=null;
        Token otherlv_13=null;
        Token lv_indexColumns_14_0=null;
        Token otherlv_15=null;
        Token otherlv_16=null;
        Token lv_tableIndexColumnsValues_17_0=null;
        Token otherlv_18=null;
        Token lv_tableIndexColumnsValues_19_0=null;
        Token otherlv_20=null;
        Token otherlv_21=null;
        Token otherlv_22=null;


        	enterRule();

        try {
            // InternalHdbTable.g:483:2: ( ( ( ( ( ({...}? => ( ({...}? => (otherlv_1= '{' otherlv_2= 'name' otherlv_3= '=' ( (lv_columnName_4_0= RULE_STRING ) ) otherlv_5= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'unique' otherlv_7= '=' ( (lv_columnUnique_8_0= RULE_BOOL ) ) otherlv_9= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'order' otherlv_11= '=' ( (lv_columnOrder_12_0= RULE_ID ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_indexColumns_14_0= 'indexColumns' ) ) otherlv_15= '=' otherlv_16= '[' ( ( (lv_tableIndexColumnsValues_17_0= RULE_STRING ) ) (otherlv_18= ',' ( (lv_tableIndexColumnsValues_19_0= RULE_STRING ) ) )* )? otherlv_20= ']' otherlv_21= ';' otherlv_22= '}' ) ) ) ) )+ {...}?) ) ) )
            // InternalHdbTable.g:484:2: ( ( ( ( ({...}? => ( ({...}? => (otherlv_1= '{' otherlv_2= 'name' otherlv_3= '=' ( (lv_columnName_4_0= RULE_STRING ) ) otherlv_5= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'unique' otherlv_7= '=' ( (lv_columnUnique_8_0= RULE_BOOL ) ) otherlv_9= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'order' otherlv_11= '=' ( (lv_columnOrder_12_0= RULE_ID ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_indexColumns_14_0= 'indexColumns' ) ) otherlv_15= '=' otherlv_16= '[' ( ( (lv_tableIndexColumnsValues_17_0= RULE_STRING ) ) (otherlv_18= ',' ( (lv_tableIndexColumnsValues_19_0= RULE_STRING ) ) )* )? otherlv_20= ']' otherlv_21= ';' otherlv_22= '}' ) ) ) ) )+ {...}?) ) )
            {
            // InternalHdbTable.g:484:2: ( ( ( ( ({...}? => ( ({...}? => (otherlv_1= '{' otherlv_2= 'name' otherlv_3= '=' ( (lv_columnName_4_0= RULE_STRING ) ) otherlv_5= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'unique' otherlv_7= '=' ( (lv_columnUnique_8_0= RULE_BOOL ) ) otherlv_9= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'order' otherlv_11= '=' ( (lv_columnOrder_12_0= RULE_ID ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_indexColumns_14_0= 'indexColumns' ) ) otherlv_15= '=' otherlv_16= '[' ( ( (lv_tableIndexColumnsValues_17_0= RULE_STRING ) ) (otherlv_18= ',' ( (lv_tableIndexColumnsValues_19_0= RULE_STRING ) ) )* )? otherlv_20= ']' otherlv_21= ';' otherlv_22= '}' ) ) ) ) )+ {...}?) ) )
            // InternalHdbTable.g:485:3: ( ( ( ({...}? => ( ({...}? => (otherlv_1= '{' otherlv_2= 'name' otherlv_3= '=' ( (lv_columnName_4_0= RULE_STRING ) ) otherlv_5= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'unique' otherlv_7= '=' ( (lv_columnUnique_8_0= RULE_BOOL ) ) otherlv_9= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'order' otherlv_11= '=' ( (lv_columnOrder_12_0= RULE_ID ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_indexColumns_14_0= 'indexColumns' ) ) otherlv_15= '=' otherlv_16= '[' ( ( (lv_tableIndexColumnsValues_17_0= RULE_STRING ) ) (otherlv_18= ',' ( (lv_tableIndexColumnsValues_19_0= RULE_STRING ) ) )* )? otherlv_20= ']' otherlv_21= ';' otherlv_22= '}' ) ) ) ) )+ {...}?) )
            {
            // InternalHdbTable.g:485:3: ( ( ( ({...}? => ( ({...}? => (otherlv_1= '{' otherlv_2= 'name' otherlv_3= '=' ( (lv_columnName_4_0= RULE_STRING ) ) otherlv_5= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'unique' otherlv_7= '=' ( (lv_columnUnique_8_0= RULE_BOOL ) ) otherlv_9= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'order' otherlv_11= '=' ( (lv_columnOrder_12_0= RULE_ID ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_indexColumns_14_0= 'indexColumns' ) ) otherlv_15= '=' otherlv_16= '[' ( ( (lv_tableIndexColumnsValues_17_0= RULE_STRING ) ) (otherlv_18= ',' ( (lv_tableIndexColumnsValues_19_0= RULE_STRING ) ) )* )? otherlv_20= ']' otherlv_21= ';' otherlv_22= '}' ) ) ) ) )+ {...}?) )
            // InternalHdbTable.g:486:4: ( ( ({...}? => ( ({...}? => (otherlv_1= '{' otherlv_2= 'name' otherlv_3= '=' ( (lv_columnName_4_0= RULE_STRING ) ) otherlv_5= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'unique' otherlv_7= '=' ( (lv_columnUnique_8_0= RULE_BOOL ) ) otherlv_9= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'order' otherlv_11= '=' ( (lv_columnOrder_12_0= RULE_ID ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_indexColumns_14_0= 'indexColumns' ) ) otherlv_15= '=' otherlv_16= '[' ( ( (lv_tableIndexColumnsValues_17_0= RULE_STRING ) ) (otherlv_18= ',' ( (lv_tableIndexColumnsValues_19_0= RULE_STRING ) ) )* )? otherlv_20= ']' otherlv_21= ';' otherlv_22= '}' ) ) ) ) )+ {...}?)
            {
             
            			  getUnorderedGroupHelper().enter(grammarAccess.getIndexTypeAccess().getUnorderedGroup());
            			
            // InternalHdbTable.g:489:4: ( ( ({...}? => ( ({...}? => (otherlv_1= '{' otherlv_2= 'name' otherlv_3= '=' ( (lv_columnName_4_0= RULE_STRING ) ) otherlv_5= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'unique' otherlv_7= '=' ( (lv_columnUnique_8_0= RULE_BOOL ) ) otherlv_9= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'order' otherlv_11= '=' ( (lv_columnOrder_12_0= RULE_ID ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_indexColumns_14_0= 'indexColumns' ) ) otherlv_15= '=' otherlv_16= '[' ( ( (lv_tableIndexColumnsValues_17_0= RULE_STRING ) ) (otherlv_18= ',' ( (lv_tableIndexColumnsValues_19_0= RULE_STRING ) ) )* )? otherlv_20= ']' otherlv_21= ';' otherlv_22= '}' ) ) ) ) )+ {...}?)
            // InternalHdbTable.g:490:5: ( ({...}? => ( ({...}? => (otherlv_1= '{' otherlv_2= 'name' otherlv_3= '=' ( (lv_columnName_4_0= RULE_STRING ) ) otherlv_5= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'unique' otherlv_7= '=' ( (lv_columnUnique_8_0= RULE_BOOL ) ) otherlv_9= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'order' otherlv_11= '=' ( (lv_columnOrder_12_0= RULE_ID ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_indexColumns_14_0= 'indexColumns' ) ) otherlv_15= '=' otherlv_16= '[' ( ( (lv_tableIndexColumnsValues_17_0= RULE_STRING ) ) (otherlv_18= ',' ( (lv_tableIndexColumnsValues_19_0= RULE_STRING ) ) )* )? otherlv_20= ']' otherlv_21= ';' otherlv_22= '}' ) ) ) ) )+ {...}?
            {
            // InternalHdbTable.g:490:5: ( ({...}? => ( ({...}? => (otherlv_1= '{' otherlv_2= 'name' otherlv_3= '=' ( (lv_columnName_4_0= RULE_STRING ) ) otherlv_5= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'unique' otherlv_7= '=' ( (lv_columnUnique_8_0= RULE_BOOL ) ) otherlv_9= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'order' otherlv_11= '=' ( (lv_columnOrder_12_0= RULE_ID ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_indexColumns_14_0= 'indexColumns' ) ) otherlv_15= '=' otherlv_16= '[' ( ( (lv_tableIndexColumnsValues_17_0= RULE_STRING ) ) (otherlv_18= ',' ( (lv_tableIndexColumnsValues_19_0= RULE_STRING ) ) )* )? otherlv_20= ']' otherlv_21= ';' otherlv_22= '}' ) ) ) ) )+
            int cnt5=0;
            loop5:
            do {
                int alt5=5;
                int LA5_0 = input.LA(1);

                if ( LA5_0 == 12 && getUnorderedGroupHelper().canSelect(grammarAccess.getIndexTypeAccess().getUnorderedGroup(), 0) ) {
                    alt5=1;
                }
                else if ( LA5_0 == 24 && getUnorderedGroupHelper().canSelect(grammarAccess.getIndexTypeAccess().getUnorderedGroup(), 1) ) {
                    alt5=2;
                }
                else if ( LA5_0 == 25 && getUnorderedGroupHelper().canSelect(grammarAccess.getIndexTypeAccess().getUnorderedGroup(), 2) ) {
                    alt5=3;
                }
                else if ( LA5_0 == 26 && getUnorderedGroupHelper().canSelect(grammarAccess.getIndexTypeAccess().getUnorderedGroup(), 3) ) {
                    alt5=4;
                }


                switch (alt5) {
            	case 1 :
            	    // InternalHdbTable.g:491:3: ({...}? => ( ({...}? => (otherlv_1= '{' otherlv_2= 'name' otherlv_3= '=' ( (lv_columnName_4_0= RULE_STRING ) ) otherlv_5= ';' ) ) ) )
            	    {
            	    // InternalHdbTable.g:491:3: ({...}? => ( ({...}? => (otherlv_1= '{' otherlv_2= 'name' otherlv_3= '=' ( (lv_columnName_4_0= RULE_STRING ) ) otherlv_5= ';' ) ) ) )
            	    // InternalHdbTable.g:492:4: {...}? => ( ({...}? => (otherlv_1= '{' otherlv_2= 'name' otherlv_3= '=' ( (lv_columnName_4_0= RULE_STRING ) ) otherlv_5= ';' ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getIndexTypeAccess().getUnorderedGroup(), 0) ) {
            	        throw new FailedPredicateException(input, "ruleIndexType", "getUnorderedGroupHelper().canSelect(grammarAccess.getIndexTypeAccess().getUnorderedGroup(), 0)");
            	    }
            	    // InternalHdbTable.g:492:103: ( ({...}? => (otherlv_1= '{' otherlv_2= 'name' otherlv_3= '=' ( (lv_columnName_4_0= RULE_STRING ) ) otherlv_5= ';' ) ) )
            	    // InternalHdbTable.g:493:5: ({...}? => (otherlv_1= '{' otherlv_2= 'name' otherlv_3= '=' ( (lv_columnName_4_0= RULE_STRING ) ) otherlv_5= ';' ) )
            	    {

            	    					getUnorderedGroupHelper().select(grammarAccess.getIndexTypeAccess().getUnorderedGroup(), 0);
            	    				
            	    // InternalHdbTable.g:496:8: ({...}? => (otherlv_1= '{' otherlv_2= 'name' otherlv_3= '=' ( (lv_columnName_4_0= RULE_STRING ) ) otherlv_5= ';' ) )
            	    // InternalHdbTable.g:496:9: {...}? => (otherlv_1= '{' otherlv_2= 'name' otherlv_3= '=' ( (lv_columnName_4_0= RULE_STRING ) ) otherlv_5= ';' )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleIndexType", "true");
            	    }
            	    // InternalHdbTable.g:496:18: (otherlv_1= '{' otherlv_2= 'name' otherlv_3= '=' ( (lv_columnName_4_0= RULE_STRING ) ) otherlv_5= ';' )
            	    // InternalHdbTable.g:496:19: otherlv_1= '{' otherlv_2= 'name' otherlv_3= '=' ( (lv_columnName_4_0= RULE_STRING ) ) otherlv_5= ';'
            	    {
            	    otherlv_1=(Token)match(input,12,FOLLOW_3); 

            	    								newLeafNode(otherlv_1, grammarAccess.getIndexTypeAccess().getLeftCurlyBracketKeyword_0_0());
            	    							
            	    otherlv_2=(Token)match(input,13,FOLLOW_4); 

            	    								newLeafNode(otherlv_2, grammarAccess.getIndexTypeAccess().getNameKeyword_0_1());
            	    							
            	    otherlv_3=(Token)match(input,14,FOLLOW_5); 

            	    								newLeafNode(otherlv_3, grammarAccess.getIndexTypeAccess().getEqualsSignKeyword_0_2());
            	    							
            	    // InternalHdbTable.g:508:8: ( (lv_columnName_4_0= RULE_STRING ) )
            	    // InternalHdbTable.g:509:9: (lv_columnName_4_0= RULE_STRING )
            	    {
            	    // InternalHdbTable.g:509:9: (lv_columnName_4_0= RULE_STRING )
            	    // InternalHdbTable.g:510:10: lv_columnName_4_0= RULE_STRING
            	    {
            	    lv_columnName_4_0=(Token)match(input,RULE_STRING,FOLLOW_6); 

            	    										newLeafNode(lv_columnName_4_0, grammarAccess.getIndexTypeAccess().getColumnNameSTRINGTerminalRuleCall_0_3_0());
            	    									

            	    										if (current==null) {
            	    											current = createModelElement(grammarAccess.getIndexTypeRule());
            	    										}
            	    										setWithLastConsumed(
            	    											current,
            	    											"columnName",
            	    											lv_columnName_4_0,
            	    											"org.eclipse.xtext.common.Terminals.STRING");
            	    									

            	    }


            	    }

            	    otherlv_5=(Token)match(input,15,FOLLOW_12); 

            	    								newLeafNode(otherlv_5, grammarAccess.getIndexTypeAccess().getSemicolonKeyword_0_4());
            	    							

            	    }


            	    }

            	     
            	    					getUnorderedGroupHelper().returnFromSelection(grammarAccess.getIndexTypeAccess().getUnorderedGroup());
            	    				

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalHdbTable.g:536:3: ({...}? => ( ({...}? => (otherlv_6= 'unique' otherlv_7= '=' ( (lv_columnUnique_8_0= RULE_BOOL ) ) otherlv_9= ';' ) ) ) )
            	    {
            	    // InternalHdbTable.g:536:3: ({...}? => ( ({...}? => (otherlv_6= 'unique' otherlv_7= '=' ( (lv_columnUnique_8_0= RULE_BOOL ) ) otherlv_9= ';' ) ) ) )
            	    // InternalHdbTable.g:537:4: {...}? => ( ({...}? => (otherlv_6= 'unique' otherlv_7= '=' ( (lv_columnUnique_8_0= RULE_BOOL ) ) otherlv_9= ';' ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getIndexTypeAccess().getUnorderedGroup(), 1) ) {
            	        throw new FailedPredicateException(input, "ruleIndexType", "getUnorderedGroupHelper().canSelect(grammarAccess.getIndexTypeAccess().getUnorderedGroup(), 1)");
            	    }
            	    // InternalHdbTable.g:537:103: ( ({...}? => (otherlv_6= 'unique' otherlv_7= '=' ( (lv_columnUnique_8_0= RULE_BOOL ) ) otherlv_9= ';' ) ) )
            	    // InternalHdbTable.g:538:5: ({...}? => (otherlv_6= 'unique' otherlv_7= '=' ( (lv_columnUnique_8_0= RULE_BOOL ) ) otherlv_9= ';' ) )
            	    {

            	    					getUnorderedGroupHelper().select(grammarAccess.getIndexTypeAccess().getUnorderedGroup(), 1);
            	    				
            	    // InternalHdbTable.g:541:8: ({...}? => (otherlv_6= 'unique' otherlv_7= '=' ( (lv_columnUnique_8_0= RULE_BOOL ) ) otherlv_9= ';' ) )
            	    // InternalHdbTable.g:541:9: {...}? => (otherlv_6= 'unique' otherlv_7= '=' ( (lv_columnUnique_8_0= RULE_BOOL ) ) otherlv_9= ';' )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleIndexType", "true");
            	    }
            	    // InternalHdbTable.g:541:18: (otherlv_6= 'unique' otherlv_7= '=' ( (lv_columnUnique_8_0= RULE_BOOL ) ) otherlv_9= ';' )
            	    // InternalHdbTable.g:541:19: otherlv_6= 'unique' otherlv_7= '=' ( (lv_columnUnique_8_0= RULE_BOOL ) ) otherlv_9= ';'
            	    {
            	    otherlv_6=(Token)match(input,24,FOLLOW_4); 

            	    								newLeafNode(otherlv_6, grammarAccess.getIndexTypeAccess().getUniqueKeyword_1_0());
            	    							
            	    otherlv_7=(Token)match(input,14,FOLLOW_10); 

            	    								newLeafNode(otherlv_7, grammarAccess.getIndexTypeAccess().getEqualsSignKeyword_1_1());
            	    							
            	    // InternalHdbTable.g:549:8: ( (lv_columnUnique_8_0= RULE_BOOL ) )
            	    // InternalHdbTable.g:550:9: (lv_columnUnique_8_0= RULE_BOOL )
            	    {
            	    // InternalHdbTable.g:550:9: (lv_columnUnique_8_0= RULE_BOOL )
            	    // InternalHdbTable.g:551:10: lv_columnUnique_8_0= RULE_BOOL
            	    {
            	    lv_columnUnique_8_0=(Token)match(input,RULE_BOOL,FOLLOW_6); 

            	    										newLeafNode(lv_columnUnique_8_0, grammarAccess.getIndexTypeAccess().getColumnUniqueBOOLTerminalRuleCall_1_2_0());
            	    									

            	    										if (current==null) {
            	    											current = createModelElement(grammarAccess.getIndexTypeRule());
            	    										}
            	    										setWithLastConsumed(
            	    											current,
            	    											"columnUnique",
            	    											lv_columnUnique_8_0,
            	    											"com.sap.xsk.models.hdbtable.HdbTable.BOOL");
            	    									

            	    }


            	    }

            	    otherlv_9=(Token)match(input,15,FOLLOW_12); 

            	    								newLeafNode(otherlv_9, grammarAccess.getIndexTypeAccess().getSemicolonKeyword_1_3());
            	    							

            	    }


            	    }

            	     
            	    					getUnorderedGroupHelper().returnFromSelection(grammarAccess.getIndexTypeAccess().getUnorderedGroup());
            	    				

            	    }


            	    }


            	    }
            	    break;
            	case 3 :
            	    // InternalHdbTable.g:577:3: ({...}? => ( ({...}? => (otherlv_10= 'order' otherlv_11= '=' ( (lv_columnOrder_12_0= RULE_ID ) ) otherlv_13= ';' ) ) ) )
            	    {
            	    // InternalHdbTable.g:577:3: ({...}? => ( ({...}? => (otherlv_10= 'order' otherlv_11= '=' ( (lv_columnOrder_12_0= RULE_ID ) ) otherlv_13= ';' ) ) ) )
            	    // InternalHdbTable.g:578:4: {...}? => ( ({...}? => (otherlv_10= 'order' otherlv_11= '=' ( (lv_columnOrder_12_0= RULE_ID ) ) otherlv_13= ';' ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getIndexTypeAccess().getUnorderedGroup(), 2) ) {
            	        throw new FailedPredicateException(input, "ruleIndexType", "getUnorderedGroupHelper().canSelect(grammarAccess.getIndexTypeAccess().getUnorderedGroup(), 2)");
            	    }
            	    // InternalHdbTable.g:578:103: ( ({...}? => (otherlv_10= 'order' otherlv_11= '=' ( (lv_columnOrder_12_0= RULE_ID ) ) otherlv_13= ';' ) ) )
            	    // InternalHdbTable.g:579:5: ({...}? => (otherlv_10= 'order' otherlv_11= '=' ( (lv_columnOrder_12_0= RULE_ID ) ) otherlv_13= ';' ) )
            	    {

            	    					getUnorderedGroupHelper().select(grammarAccess.getIndexTypeAccess().getUnorderedGroup(), 2);
            	    				
            	    // InternalHdbTable.g:582:8: ({...}? => (otherlv_10= 'order' otherlv_11= '=' ( (lv_columnOrder_12_0= RULE_ID ) ) otherlv_13= ';' ) )
            	    // InternalHdbTable.g:582:9: {...}? => (otherlv_10= 'order' otherlv_11= '=' ( (lv_columnOrder_12_0= RULE_ID ) ) otherlv_13= ';' )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleIndexType", "true");
            	    }
            	    // InternalHdbTable.g:582:18: (otherlv_10= 'order' otherlv_11= '=' ( (lv_columnOrder_12_0= RULE_ID ) ) otherlv_13= ';' )
            	    // InternalHdbTable.g:582:19: otherlv_10= 'order' otherlv_11= '=' ( (lv_columnOrder_12_0= RULE_ID ) ) otherlv_13= ';'
            	    {
            	    otherlv_10=(Token)match(input,25,FOLLOW_4); 

            	    								newLeafNode(otherlv_10, grammarAccess.getIndexTypeAccess().getOrderKeyword_2_0());
            	    							
            	    otherlv_11=(Token)match(input,14,FOLLOW_8); 

            	    								newLeafNode(otherlv_11, grammarAccess.getIndexTypeAccess().getEqualsSignKeyword_2_1());
            	    							
            	    // InternalHdbTable.g:590:8: ( (lv_columnOrder_12_0= RULE_ID ) )
            	    // InternalHdbTable.g:591:9: (lv_columnOrder_12_0= RULE_ID )
            	    {
            	    // InternalHdbTable.g:591:9: (lv_columnOrder_12_0= RULE_ID )
            	    // InternalHdbTable.g:592:10: lv_columnOrder_12_0= RULE_ID
            	    {
            	    lv_columnOrder_12_0=(Token)match(input,RULE_ID,FOLLOW_6); 

            	    										newLeafNode(lv_columnOrder_12_0, grammarAccess.getIndexTypeAccess().getColumnOrderIDTerminalRuleCall_2_2_0());
            	    									

            	    										if (current==null) {
            	    											current = createModelElement(grammarAccess.getIndexTypeRule());
            	    										}
            	    										setWithLastConsumed(
            	    											current,
            	    											"columnOrder",
            	    											lv_columnOrder_12_0,
            	    											"org.eclipse.xtext.common.Terminals.ID");
            	    									

            	    }


            	    }

            	    otherlv_13=(Token)match(input,15,FOLLOW_12); 

            	    								newLeafNode(otherlv_13, grammarAccess.getIndexTypeAccess().getSemicolonKeyword_2_3());
            	    							

            	    }


            	    }

            	     
            	    					getUnorderedGroupHelper().returnFromSelection(grammarAccess.getIndexTypeAccess().getUnorderedGroup());
            	    				

            	    }


            	    }


            	    }
            	    break;
            	case 4 :
            	    // InternalHdbTable.g:618:3: ({...}? => ( ({...}? => ( ( (lv_indexColumns_14_0= 'indexColumns' ) ) otherlv_15= '=' otherlv_16= '[' ( ( (lv_tableIndexColumnsValues_17_0= RULE_STRING ) ) (otherlv_18= ',' ( (lv_tableIndexColumnsValues_19_0= RULE_STRING ) ) )* )? otherlv_20= ']' otherlv_21= ';' otherlv_22= '}' ) ) ) )
            	    {
            	    // InternalHdbTable.g:618:3: ({...}? => ( ({...}? => ( ( (lv_indexColumns_14_0= 'indexColumns' ) ) otherlv_15= '=' otherlv_16= '[' ( ( (lv_tableIndexColumnsValues_17_0= RULE_STRING ) ) (otherlv_18= ',' ( (lv_tableIndexColumnsValues_19_0= RULE_STRING ) ) )* )? otherlv_20= ']' otherlv_21= ';' otherlv_22= '}' ) ) ) )
            	    // InternalHdbTable.g:619:4: {...}? => ( ({...}? => ( ( (lv_indexColumns_14_0= 'indexColumns' ) ) otherlv_15= '=' otherlv_16= '[' ( ( (lv_tableIndexColumnsValues_17_0= RULE_STRING ) ) (otherlv_18= ',' ( (lv_tableIndexColumnsValues_19_0= RULE_STRING ) ) )* )? otherlv_20= ']' otherlv_21= ';' otherlv_22= '}' ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getIndexTypeAccess().getUnorderedGroup(), 3) ) {
            	        throw new FailedPredicateException(input, "ruleIndexType", "getUnorderedGroupHelper().canSelect(grammarAccess.getIndexTypeAccess().getUnorderedGroup(), 3)");
            	    }
            	    // InternalHdbTable.g:619:103: ( ({...}? => ( ( (lv_indexColumns_14_0= 'indexColumns' ) ) otherlv_15= '=' otherlv_16= '[' ( ( (lv_tableIndexColumnsValues_17_0= RULE_STRING ) ) (otherlv_18= ',' ( (lv_tableIndexColumnsValues_19_0= RULE_STRING ) ) )* )? otherlv_20= ']' otherlv_21= ';' otherlv_22= '}' ) ) )
            	    // InternalHdbTable.g:620:5: ({...}? => ( ( (lv_indexColumns_14_0= 'indexColumns' ) ) otherlv_15= '=' otherlv_16= '[' ( ( (lv_tableIndexColumnsValues_17_0= RULE_STRING ) ) (otherlv_18= ',' ( (lv_tableIndexColumnsValues_19_0= RULE_STRING ) ) )* )? otherlv_20= ']' otherlv_21= ';' otherlv_22= '}' ) )
            	    {

            	    					getUnorderedGroupHelper().select(grammarAccess.getIndexTypeAccess().getUnorderedGroup(), 3);
            	    				
            	    // InternalHdbTable.g:623:8: ({...}? => ( ( (lv_indexColumns_14_0= 'indexColumns' ) ) otherlv_15= '=' otherlv_16= '[' ( ( (lv_tableIndexColumnsValues_17_0= RULE_STRING ) ) (otherlv_18= ',' ( (lv_tableIndexColumnsValues_19_0= RULE_STRING ) ) )* )? otherlv_20= ']' otherlv_21= ';' otherlv_22= '}' ) )
            	    // InternalHdbTable.g:623:9: {...}? => ( ( (lv_indexColumns_14_0= 'indexColumns' ) ) otherlv_15= '=' otherlv_16= '[' ( ( (lv_tableIndexColumnsValues_17_0= RULE_STRING ) ) (otherlv_18= ',' ( (lv_tableIndexColumnsValues_19_0= RULE_STRING ) ) )* )? otherlv_20= ']' otherlv_21= ';' otherlv_22= '}' )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleIndexType", "true");
            	    }
            	    // InternalHdbTable.g:623:18: ( ( (lv_indexColumns_14_0= 'indexColumns' ) ) otherlv_15= '=' otherlv_16= '[' ( ( (lv_tableIndexColumnsValues_17_0= RULE_STRING ) ) (otherlv_18= ',' ( (lv_tableIndexColumnsValues_19_0= RULE_STRING ) ) )* )? otherlv_20= ']' otherlv_21= ';' otherlv_22= '}' )
            	    // InternalHdbTable.g:623:19: ( (lv_indexColumns_14_0= 'indexColumns' ) ) otherlv_15= '=' otherlv_16= '[' ( ( (lv_tableIndexColumnsValues_17_0= RULE_STRING ) ) (otherlv_18= ',' ( (lv_tableIndexColumnsValues_19_0= RULE_STRING ) ) )* )? otherlv_20= ']' otherlv_21= ';' otherlv_22= '}'
            	    {
            	    // InternalHdbTable.g:623:19: ( (lv_indexColumns_14_0= 'indexColumns' ) )
            	    // InternalHdbTable.g:624:9: (lv_indexColumns_14_0= 'indexColumns' )
            	    {
            	    // InternalHdbTable.g:624:9: (lv_indexColumns_14_0= 'indexColumns' )
            	    // InternalHdbTable.g:625:10: lv_indexColumns_14_0= 'indexColumns'
            	    {
            	    lv_indexColumns_14_0=(Token)match(input,26,FOLLOW_4); 

            	    										newLeafNode(lv_indexColumns_14_0, grammarAccess.getIndexTypeAccess().getIndexColumnsIndexColumnsKeyword_3_0_0());
            	    									

            	    										if (current==null) {
            	    											current = createModelElement(grammarAccess.getIndexTypeRule());
            	    										}
            	    										setWithLastConsumed(current, "indexColumns", true, "indexColumns");
            	    									

            	    }


            	    }

            	    otherlv_15=(Token)match(input,14,FOLLOW_13); 

            	    								newLeafNode(otherlv_15, grammarAccess.getIndexTypeAccess().getEqualsSignKeyword_3_1());
            	    							
            	    otherlv_16=(Token)match(input,27,FOLLOW_14); 

            	    								newLeafNode(otherlv_16, grammarAccess.getIndexTypeAccess().getLeftSquareBracketKeyword_3_2());
            	    							
            	    // InternalHdbTable.g:645:8: ( ( (lv_tableIndexColumnsValues_17_0= RULE_STRING ) ) (otherlv_18= ',' ( (lv_tableIndexColumnsValues_19_0= RULE_STRING ) ) )* )?
            	    int alt4=2;
            	    int LA4_0 = input.LA(1);

            	    if ( (LA4_0==RULE_STRING) ) {
            	        alt4=1;
            	    }
            	    switch (alt4) {
            	        case 1 :
            	            // InternalHdbTable.g:646:9: ( (lv_tableIndexColumnsValues_17_0= RULE_STRING ) ) (otherlv_18= ',' ( (lv_tableIndexColumnsValues_19_0= RULE_STRING ) ) )*
            	            {
            	            // InternalHdbTable.g:646:9: ( (lv_tableIndexColumnsValues_17_0= RULE_STRING ) )
            	            // InternalHdbTable.g:647:10: (lv_tableIndexColumnsValues_17_0= RULE_STRING )
            	            {
            	            // InternalHdbTable.g:647:10: (lv_tableIndexColumnsValues_17_0= RULE_STRING )
            	            // InternalHdbTable.g:648:11: lv_tableIndexColumnsValues_17_0= RULE_STRING
            	            {
            	            lv_tableIndexColumnsValues_17_0=(Token)match(input,RULE_STRING,FOLLOW_15); 

            	            											newLeafNode(lv_tableIndexColumnsValues_17_0, grammarAccess.getIndexTypeAccess().getTableIndexColumnsValuesSTRINGTerminalRuleCall_3_3_0_0());
            	            										

            	            											if (current==null) {
            	            												current = createModelElement(grammarAccess.getIndexTypeRule());
            	            											}
            	            											addWithLastConsumed(
            	            												current,
            	            												"tableIndexColumnsValues",
            	            												lv_tableIndexColumnsValues_17_0,
            	            												"org.eclipse.xtext.common.Terminals.STRING");
            	            										

            	            }


            	            }

            	            // InternalHdbTable.g:664:9: (otherlv_18= ',' ( (lv_tableIndexColumnsValues_19_0= RULE_STRING ) ) )*
            	            loop3:
            	            do {
            	                int alt3=2;
            	                int LA3_0 = input.LA(1);

            	                if ( (LA3_0==28) ) {
            	                    alt3=1;
            	                }


            	                switch (alt3) {
            	            	case 1 :
            	            	    // InternalHdbTable.g:665:10: otherlv_18= ',' ( (lv_tableIndexColumnsValues_19_0= RULE_STRING ) )
            	            	    {
            	            	    otherlv_18=(Token)match(input,28,FOLLOW_5); 

            	            	    										newLeafNode(otherlv_18, grammarAccess.getIndexTypeAccess().getCommaKeyword_3_3_1_0());
            	            	    									
            	            	    // InternalHdbTable.g:669:10: ( (lv_tableIndexColumnsValues_19_0= RULE_STRING ) )
            	            	    // InternalHdbTable.g:670:11: (lv_tableIndexColumnsValues_19_0= RULE_STRING )
            	            	    {
            	            	    // InternalHdbTable.g:670:11: (lv_tableIndexColumnsValues_19_0= RULE_STRING )
            	            	    // InternalHdbTable.g:671:12: lv_tableIndexColumnsValues_19_0= RULE_STRING
            	            	    {
            	            	    lv_tableIndexColumnsValues_19_0=(Token)match(input,RULE_STRING,FOLLOW_15); 

            	            	    												newLeafNode(lv_tableIndexColumnsValues_19_0, grammarAccess.getIndexTypeAccess().getTableIndexColumnsValuesSTRINGTerminalRuleCall_3_3_1_1_0());
            	            	    											

            	            	    												if (current==null) {
            	            	    													current = createModelElement(grammarAccess.getIndexTypeRule());
            	            	    												}
            	            	    												addWithLastConsumed(
            	            	    													current,
            	            	    													"tableIndexColumnsValues",
            	            	    													lv_tableIndexColumnsValues_19_0,
            	            	    													"org.eclipse.xtext.common.Terminals.STRING");
            	            	    											

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

            	    otherlv_20=(Token)match(input,29,FOLLOW_6); 

            	    								newLeafNode(otherlv_20, grammarAccess.getIndexTypeAccess().getRightSquareBracketKeyword_3_4());
            	    							
            	    otherlv_21=(Token)match(input,15,FOLLOW_11); 

            	    								newLeafNode(otherlv_21, grammarAccess.getIndexTypeAccess().getSemicolonKeyword_3_5());
            	    							
            	    otherlv_22=(Token)match(input,23,FOLLOW_12); 

            	    								newLeafNode(otherlv_22, grammarAccess.getIndexTypeAccess().getRightCurlyBracketKeyword_3_6());
            	    							

            	    }


            	    }

            	     
            	    					getUnorderedGroupHelper().returnFromSelection(grammarAccess.getIndexTypeAccess().getUnorderedGroup());
            	    				

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt5 >= 1 ) break loop5;
                        EarlyExitException eee =
                            new EarlyExitException(5, input);
                        throw eee;
                }
                cnt5++;
            } while (true);

            if ( ! getUnorderedGroupHelper().canLeave(grammarAccess.getIndexTypeAccess().getUnorderedGroup()) ) {
                throw new FailedPredicateException(input, "ruleIndexType", "getUnorderedGroupHelper().canLeave(grammarAccess.getIndexTypeAccess().getUnorderedGroup())");
            }

            }


            }

             
            			  getUnorderedGroupHelper().leave(grammarAccess.getIndexTypeAccess().getUnorderedGroup());
            			

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
    // $ANTLR end "ruleIndexType"


    // $ANTLR start "entryRuleTable"
    // InternalHdbTable.g:718:1: entryRuleTable returns [EObject current=null] : iv_ruleTable= ruleTable EOF ;
    public final EObject entryRuleTable() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTable = null;


        try {
            // InternalHdbTable.g:718:46: (iv_ruleTable= ruleTable EOF )
            // InternalHdbTable.g:719:2: iv_ruleTable= ruleTable EOF
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
    // InternalHdbTable.g:725:1: ruleTable returns [EObject current=null] : ( ( ( ( ({...}? => ( ({...}? => ( ( (lv_schema_1_0= 'table.schemaName' ) ) otherlv_2= '=' ( (lv_schemaName_3_0= RULE_STRING ) ) otherlv_4= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_type_5_0= 'table.tableType' ) ) otherlv_6= '=' ( (lv_typeValue_7_0= RULE_ID ) ) otherlv_8= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_description_9_0= 'table.description' ) ) otherlv_10= '=' ( (lv_descriptionText_11_0= RULE_STRING ) ) otherlv_12= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_columns_13_0= 'table.columns' ) ) otherlv_14= '=' otherlv_15= '[' ( ( (lv_columnsValues_16_0= ruleColumnType ) ) (otherlv_17= ',' ( (lv_columnsValues_18_0= ruleColumnType ) ) )* )? otherlv_19= ']' otherlv_20= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_indexes_21_0= 'table.indexes' ) ) otherlv_22= '=' otherlv_23= '[' ( ( (lv_indexesValues_24_0= ruleIndexType ) ) (otherlv_25= ',' ( (lv_indexesValues_26_0= ruleIndexType ) ) )* )? otherlv_27= ']' otherlv_28= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( ( (lv_primaryKeyColumns_29_0= 'table.primaryKey.pkcolumns' ) ) otherlv_30= '=' otherlv_31= '[' ( ( (lv_tablePrimaryKeyColumnsValues_32_0= RULE_STRING ) ) (otherlv_33= ',' ( (lv_tablePrimaryKeyColumnsValues_34_0= RULE_STRING ) ) )* )? otherlv_35= ']' )? otherlv_36= ';' ) ) ) ) )+ {...}?) ) ) ;
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
        Token lv_indexes_21_0=null;
        Token otherlv_22=null;
        Token otherlv_23=null;
        Token otherlv_25=null;
        Token otherlv_27=null;
        Token otherlv_28=null;
        Token lv_primaryKeyColumns_29_0=null;
        Token otherlv_30=null;
        Token otherlv_31=null;
        Token lv_tablePrimaryKeyColumnsValues_32_0=null;
        Token otherlv_33=null;
        Token lv_tablePrimaryKeyColumnsValues_34_0=null;
        Token otherlv_35=null;
        Token otherlv_36=null;
        EObject lv_columnsValues_16_0 = null;

        EObject lv_columnsValues_18_0 = null;

        EObject lv_indexesValues_24_0 = null;

        EObject lv_indexesValues_26_0 = null;



        	enterRule();

        try {
            // InternalHdbTable.g:731:2: ( ( ( ( ( ({...}? => ( ({...}? => ( ( (lv_schema_1_0= 'table.schemaName' ) ) otherlv_2= '=' ( (lv_schemaName_3_0= RULE_STRING ) ) otherlv_4= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_type_5_0= 'table.tableType' ) ) otherlv_6= '=' ( (lv_typeValue_7_0= RULE_ID ) ) otherlv_8= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_description_9_0= 'table.description' ) ) otherlv_10= '=' ( (lv_descriptionText_11_0= RULE_STRING ) ) otherlv_12= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_columns_13_0= 'table.columns' ) ) otherlv_14= '=' otherlv_15= '[' ( ( (lv_columnsValues_16_0= ruleColumnType ) ) (otherlv_17= ',' ( (lv_columnsValues_18_0= ruleColumnType ) ) )* )? otherlv_19= ']' otherlv_20= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_indexes_21_0= 'table.indexes' ) ) otherlv_22= '=' otherlv_23= '[' ( ( (lv_indexesValues_24_0= ruleIndexType ) ) (otherlv_25= ',' ( (lv_indexesValues_26_0= ruleIndexType ) ) )* )? otherlv_27= ']' otherlv_28= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( ( (lv_primaryKeyColumns_29_0= 'table.primaryKey.pkcolumns' ) ) otherlv_30= '=' otherlv_31= '[' ( ( (lv_tablePrimaryKeyColumnsValues_32_0= RULE_STRING ) ) (otherlv_33= ',' ( (lv_tablePrimaryKeyColumnsValues_34_0= RULE_STRING ) ) )* )? otherlv_35= ']' )? otherlv_36= ';' ) ) ) ) )+ {...}?) ) ) )
            // InternalHdbTable.g:732:2: ( ( ( ( ({...}? => ( ({...}? => ( ( (lv_schema_1_0= 'table.schemaName' ) ) otherlv_2= '=' ( (lv_schemaName_3_0= RULE_STRING ) ) otherlv_4= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_type_5_0= 'table.tableType' ) ) otherlv_6= '=' ( (lv_typeValue_7_0= RULE_ID ) ) otherlv_8= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_description_9_0= 'table.description' ) ) otherlv_10= '=' ( (lv_descriptionText_11_0= RULE_STRING ) ) otherlv_12= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_columns_13_0= 'table.columns' ) ) otherlv_14= '=' otherlv_15= '[' ( ( (lv_columnsValues_16_0= ruleColumnType ) ) (otherlv_17= ',' ( (lv_columnsValues_18_0= ruleColumnType ) ) )* )? otherlv_19= ']' otherlv_20= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_indexes_21_0= 'table.indexes' ) ) otherlv_22= '=' otherlv_23= '[' ( ( (lv_indexesValues_24_0= ruleIndexType ) ) (otherlv_25= ',' ( (lv_indexesValues_26_0= ruleIndexType ) ) )* )? otherlv_27= ']' otherlv_28= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( ( (lv_primaryKeyColumns_29_0= 'table.primaryKey.pkcolumns' ) ) otherlv_30= '=' otherlv_31= '[' ( ( (lv_tablePrimaryKeyColumnsValues_32_0= RULE_STRING ) ) (otherlv_33= ',' ( (lv_tablePrimaryKeyColumnsValues_34_0= RULE_STRING ) ) )* )? otherlv_35= ']' )? otherlv_36= ';' ) ) ) ) )+ {...}?) ) )
            {
            // InternalHdbTable.g:732:2: ( ( ( ( ({...}? => ( ({...}? => ( ( (lv_schema_1_0= 'table.schemaName' ) ) otherlv_2= '=' ( (lv_schemaName_3_0= RULE_STRING ) ) otherlv_4= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_type_5_0= 'table.tableType' ) ) otherlv_6= '=' ( (lv_typeValue_7_0= RULE_ID ) ) otherlv_8= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_description_9_0= 'table.description' ) ) otherlv_10= '=' ( (lv_descriptionText_11_0= RULE_STRING ) ) otherlv_12= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_columns_13_0= 'table.columns' ) ) otherlv_14= '=' otherlv_15= '[' ( ( (lv_columnsValues_16_0= ruleColumnType ) ) (otherlv_17= ',' ( (lv_columnsValues_18_0= ruleColumnType ) ) )* )? otherlv_19= ']' otherlv_20= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_indexes_21_0= 'table.indexes' ) ) otherlv_22= '=' otherlv_23= '[' ( ( (lv_indexesValues_24_0= ruleIndexType ) ) (otherlv_25= ',' ( (lv_indexesValues_26_0= ruleIndexType ) ) )* )? otherlv_27= ']' otherlv_28= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( ( (lv_primaryKeyColumns_29_0= 'table.primaryKey.pkcolumns' ) ) otherlv_30= '=' otherlv_31= '[' ( ( (lv_tablePrimaryKeyColumnsValues_32_0= RULE_STRING ) ) (otherlv_33= ',' ( (lv_tablePrimaryKeyColumnsValues_34_0= RULE_STRING ) ) )* )? otherlv_35= ']' )? otherlv_36= ';' ) ) ) ) )+ {...}?) ) )
            // InternalHdbTable.g:733:3: ( ( ( ({...}? => ( ({...}? => ( ( (lv_schema_1_0= 'table.schemaName' ) ) otherlv_2= '=' ( (lv_schemaName_3_0= RULE_STRING ) ) otherlv_4= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_type_5_0= 'table.tableType' ) ) otherlv_6= '=' ( (lv_typeValue_7_0= RULE_ID ) ) otherlv_8= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_description_9_0= 'table.description' ) ) otherlv_10= '=' ( (lv_descriptionText_11_0= RULE_STRING ) ) otherlv_12= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_columns_13_0= 'table.columns' ) ) otherlv_14= '=' otherlv_15= '[' ( ( (lv_columnsValues_16_0= ruleColumnType ) ) (otherlv_17= ',' ( (lv_columnsValues_18_0= ruleColumnType ) ) )* )? otherlv_19= ']' otherlv_20= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_indexes_21_0= 'table.indexes' ) ) otherlv_22= '=' otherlv_23= '[' ( ( (lv_indexesValues_24_0= ruleIndexType ) ) (otherlv_25= ',' ( (lv_indexesValues_26_0= ruleIndexType ) ) )* )? otherlv_27= ']' otherlv_28= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( ( (lv_primaryKeyColumns_29_0= 'table.primaryKey.pkcolumns' ) ) otherlv_30= '=' otherlv_31= '[' ( ( (lv_tablePrimaryKeyColumnsValues_32_0= RULE_STRING ) ) (otherlv_33= ',' ( (lv_tablePrimaryKeyColumnsValues_34_0= RULE_STRING ) ) )* )? otherlv_35= ']' )? otherlv_36= ';' ) ) ) ) )+ {...}?) )
            {
            // InternalHdbTable.g:733:3: ( ( ( ({...}? => ( ({...}? => ( ( (lv_schema_1_0= 'table.schemaName' ) ) otherlv_2= '=' ( (lv_schemaName_3_0= RULE_STRING ) ) otherlv_4= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_type_5_0= 'table.tableType' ) ) otherlv_6= '=' ( (lv_typeValue_7_0= RULE_ID ) ) otherlv_8= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_description_9_0= 'table.description' ) ) otherlv_10= '=' ( (lv_descriptionText_11_0= RULE_STRING ) ) otherlv_12= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_columns_13_0= 'table.columns' ) ) otherlv_14= '=' otherlv_15= '[' ( ( (lv_columnsValues_16_0= ruleColumnType ) ) (otherlv_17= ',' ( (lv_columnsValues_18_0= ruleColumnType ) ) )* )? otherlv_19= ']' otherlv_20= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_indexes_21_0= 'table.indexes' ) ) otherlv_22= '=' otherlv_23= '[' ( ( (lv_indexesValues_24_0= ruleIndexType ) ) (otherlv_25= ',' ( (lv_indexesValues_26_0= ruleIndexType ) ) )* )? otherlv_27= ']' otherlv_28= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( ( (lv_primaryKeyColumns_29_0= 'table.primaryKey.pkcolumns' ) ) otherlv_30= '=' otherlv_31= '[' ( ( (lv_tablePrimaryKeyColumnsValues_32_0= RULE_STRING ) ) (otherlv_33= ',' ( (lv_tablePrimaryKeyColumnsValues_34_0= RULE_STRING ) ) )* )? otherlv_35= ']' )? otherlv_36= ';' ) ) ) ) )+ {...}?) )
            // InternalHdbTable.g:734:4: ( ( ({...}? => ( ({...}? => ( ( (lv_schema_1_0= 'table.schemaName' ) ) otherlv_2= '=' ( (lv_schemaName_3_0= RULE_STRING ) ) otherlv_4= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_type_5_0= 'table.tableType' ) ) otherlv_6= '=' ( (lv_typeValue_7_0= RULE_ID ) ) otherlv_8= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_description_9_0= 'table.description' ) ) otherlv_10= '=' ( (lv_descriptionText_11_0= RULE_STRING ) ) otherlv_12= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_columns_13_0= 'table.columns' ) ) otherlv_14= '=' otherlv_15= '[' ( ( (lv_columnsValues_16_0= ruleColumnType ) ) (otherlv_17= ',' ( (lv_columnsValues_18_0= ruleColumnType ) ) )* )? otherlv_19= ']' otherlv_20= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_indexes_21_0= 'table.indexes' ) ) otherlv_22= '=' otherlv_23= '[' ( ( (lv_indexesValues_24_0= ruleIndexType ) ) (otherlv_25= ',' ( (lv_indexesValues_26_0= ruleIndexType ) ) )* )? otherlv_27= ']' otherlv_28= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( ( (lv_primaryKeyColumns_29_0= 'table.primaryKey.pkcolumns' ) ) otherlv_30= '=' otherlv_31= '[' ( ( (lv_tablePrimaryKeyColumnsValues_32_0= RULE_STRING ) ) (otherlv_33= ',' ( (lv_tablePrimaryKeyColumnsValues_34_0= RULE_STRING ) ) )* )? otherlv_35= ']' )? otherlv_36= ';' ) ) ) ) )+ {...}?)
            {
             
            			  getUnorderedGroupHelper().enter(grammarAccess.getTableAccess().getUnorderedGroup());
            			
            // InternalHdbTable.g:737:4: ( ( ({...}? => ( ({...}? => ( ( (lv_schema_1_0= 'table.schemaName' ) ) otherlv_2= '=' ( (lv_schemaName_3_0= RULE_STRING ) ) otherlv_4= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_type_5_0= 'table.tableType' ) ) otherlv_6= '=' ( (lv_typeValue_7_0= RULE_ID ) ) otherlv_8= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_description_9_0= 'table.description' ) ) otherlv_10= '=' ( (lv_descriptionText_11_0= RULE_STRING ) ) otherlv_12= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_columns_13_0= 'table.columns' ) ) otherlv_14= '=' otherlv_15= '[' ( ( (lv_columnsValues_16_0= ruleColumnType ) ) (otherlv_17= ',' ( (lv_columnsValues_18_0= ruleColumnType ) ) )* )? otherlv_19= ']' otherlv_20= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_indexes_21_0= 'table.indexes' ) ) otherlv_22= '=' otherlv_23= '[' ( ( (lv_indexesValues_24_0= ruleIndexType ) ) (otherlv_25= ',' ( (lv_indexesValues_26_0= ruleIndexType ) ) )* )? otherlv_27= ']' otherlv_28= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( ( (lv_primaryKeyColumns_29_0= 'table.primaryKey.pkcolumns' ) ) otherlv_30= '=' otherlv_31= '[' ( ( (lv_tablePrimaryKeyColumnsValues_32_0= RULE_STRING ) ) (otherlv_33= ',' ( (lv_tablePrimaryKeyColumnsValues_34_0= RULE_STRING ) ) )* )? otherlv_35= ']' )? otherlv_36= ';' ) ) ) ) )+ {...}?)
            // InternalHdbTable.g:738:5: ( ({...}? => ( ({...}? => ( ( (lv_schema_1_0= 'table.schemaName' ) ) otherlv_2= '=' ( (lv_schemaName_3_0= RULE_STRING ) ) otherlv_4= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_type_5_0= 'table.tableType' ) ) otherlv_6= '=' ( (lv_typeValue_7_0= RULE_ID ) ) otherlv_8= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_description_9_0= 'table.description' ) ) otherlv_10= '=' ( (lv_descriptionText_11_0= RULE_STRING ) ) otherlv_12= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_columns_13_0= 'table.columns' ) ) otherlv_14= '=' otherlv_15= '[' ( ( (lv_columnsValues_16_0= ruleColumnType ) ) (otherlv_17= ',' ( (lv_columnsValues_18_0= ruleColumnType ) ) )* )? otherlv_19= ']' otherlv_20= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_indexes_21_0= 'table.indexes' ) ) otherlv_22= '=' otherlv_23= '[' ( ( (lv_indexesValues_24_0= ruleIndexType ) ) (otherlv_25= ',' ( (lv_indexesValues_26_0= ruleIndexType ) ) )* )? otherlv_27= ']' otherlv_28= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( ( (lv_primaryKeyColumns_29_0= 'table.primaryKey.pkcolumns' ) ) otherlv_30= '=' otherlv_31= '[' ( ( (lv_tablePrimaryKeyColumnsValues_32_0= RULE_STRING ) ) (otherlv_33= ',' ( (lv_tablePrimaryKeyColumnsValues_34_0= RULE_STRING ) ) )* )? otherlv_35= ']' )? otherlv_36= ';' ) ) ) ) )+ {...}?
            {
            // InternalHdbTable.g:738:5: ( ({...}? => ( ({...}? => ( ( (lv_schema_1_0= 'table.schemaName' ) ) otherlv_2= '=' ( (lv_schemaName_3_0= RULE_STRING ) ) otherlv_4= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_type_5_0= 'table.tableType' ) ) otherlv_6= '=' ( (lv_typeValue_7_0= RULE_ID ) ) otherlv_8= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_description_9_0= 'table.description' ) ) otherlv_10= '=' ( (lv_descriptionText_11_0= RULE_STRING ) ) otherlv_12= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_columns_13_0= 'table.columns' ) ) otherlv_14= '=' otherlv_15= '[' ( ( (lv_columnsValues_16_0= ruleColumnType ) ) (otherlv_17= ',' ( (lv_columnsValues_18_0= ruleColumnType ) ) )* )? otherlv_19= ']' otherlv_20= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_indexes_21_0= 'table.indexes' ) ) otherlv_22= '=' otherlv_23= '[' ( ( (lv_indexesValues_24_0= ruleIndexType ) ) (otherlv_25= ',' ( (lv_indexesValues_26_0= ruleIndexType ) ) )* )? otherlv_27= ']' otherlv_28= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( ( (lv_primaryKeyColumns_29_0= 'table.primaryKey.pkcolumns' ) ) otherlv_30= '=' otherlv_31= '[' ( ( (lv_tablePrimaryKeyColumnsValues_32_0= RULE_STRING ) ) (otherlv_33= ',' ( (lv_tablePrimaryKeyColumnsValues_34_0= RULE_STRING ) ) )* )? otherlv_35= ']' )? otherlv_36= ';' ) ) ) ) )+
            int cnt13=0;
            loop13:
            do {
                int alt13=7;
                int LA13_0 = input.LA(1);

                if ( LA13_0 == 30 && getUnorderedGroupHelper().canSelect(grammarAccess.getTableAccess().getUnorderedGroup(), 0) ) {
                    alt13=1;
                }
                else if ( LA13_0 == 31 && getUnorderedGroupHelper().canSelect(grammarAccess.getTableAccess().getUnorderedGroup(), 1) ) {
                    alt13=2;
                }
                else if ( LA13_0 == 32 && getUnorderedGroupHelper().canSelect(grammarAccess.getTableAccess().getUnorderedGroup(), 2) ) {
                    alt13=3;
                }
                else if ( LA13_0 == 33 && getUnorderedGroupHelper().canSelect(grammarAccess.getTableAccess().getUnorderedGroup(), 3) ) {
                    alt13=4;
                }
                else if ( LA13_0 == 34 && getUnorderedGroupHelper().canSelect(grammarAccess.getTableAccess().getUnorderedGroup(), 4) ) {
                    alt13=5;
                }
                else if ( ( LA13_0 == 15 || LA13_0 == 35 ) && getUnorderedGroupHelper().canSelect(grammarAccess.getTableAccess().getUnorderedGroup(), 5) ) {
                    alt13=6;
                }


                switch (alt13) {
            	case 1 :
            	    // InternalHdbTable.g:739:3: ({...}? => ( ({...}? => ( ( (lv_schema_1_0= 'table.schemaName' ) ) otherlv_2= '=' ( (lv_schemaName_3_0= RULE_STRING ) ) otherlv_4= ';' ) ) ) )
            	    {
            	    // InternalHdbTable.g:739:3: ({...}? => ( ({...}? => ( ( (lv_schema_1_0= 'table.schemaName' ) ) otherlv_2= '=' ( (lv_schemaName_3_0= RULE_STRING ) ) otherlv_4= ';' ) ) ) )
            	    // InternalHdbTable.g:740:4: {...}? => ( ({...}? => ( ( (lv_schema_1_0= 'table.schemaName' ) ) otherlv_2= '=' ( (lv_schemaName_3_0= RULE_STRING ) ) otherlv_4= ';' ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getTableAccess().getUnorderedGroup(), 0) ) {
            	        throw new FailedPredicateException(input, "ruleTable", "getUnorderedGroupHelper().canSelect(grammarAccess.getTableAccess().getUnorderedGroup(), 0)");
            	    }
            	    // InternalHdbTable.g:740:99: ( ({...}? => ( ( (lv_schema_1_0= 'table.schemaName' ) ) otherlv_2= '=' ( (lv_schemaName_3_0= RULE_STRING ) ) otherlv_4= ';' ) ) )
            	    // InternalHdbTable.g:741:5: ({...}? => ( ( (lv_schema_1_0= 'table.schemaName' ) ) otherlv_2= '=' ( (lv_schemaName_3_0= RULE_STRING ) ) otherlv_4= ';' ) )
            	    {

            	    					getUnorderedGroupHelper().select(grammarAccess.getTableAccess().getUnorderedGroup(), 0);
            	    				
            	    // InternalHdbTable.g:744:8: ({...}? => ( ( (lv_schema_1_0= 'table.schemaName' ) ) otherlv_2= '=' ( (lv_schemaName_3_0= RULE_STRING ) ) otherlv_4= ';' ) )
            	    // InternalHdbTable.g:744:9: {...}? => ( ( (lv_schema_1_0= 'table.schemaName' ) ) otherlv_2= '=' ( (lv_schemaName_3_0= RULE_STRING ) ) otherlv_4= ';' )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleTable", "true");
            	    }
            	    // InternalHdbTable.g:744:18: ( ( (lv_schema_1_0= 'table.schemaName' ) ) otherlv_2= '=' ( (lv_schemaName_3_0= RULE_STRING ) ) otherlv_4= ';' )
            	    // InternalHdbTable.g:744:19: ( (lv_schema_1_0= 'table.schemaName' ) ) otherlv_2= '=' ( (lv_schemaName_3_0= RULE_STRING ) ) otherlv_4= ';'
            	    {
            	    // InternalHdbTable.g:744:19: ( (lv_schema_1_0= 'table.schemaName' ) )
            	    // InternalHdbTable.g:745:9: (lv_schema_1_0= 'table.schemaName' )
            	    {
            	    // InternalHdbTable.g:745:9: (lv_schema_1_0= 'table.schemaName' )
            	    // InternalHdbTable.g:746:10: lv_schema_1_0= 'table.schemaName'
            	    {
            	    lv_schema_1_0=(Token)match(input,30,FOLLOW_4); 

            	    										newLeafNode(lv_schema_1_0, grammarAccess.getTableAccess().getSchemaTableSchemaNameKeyword_0_0_0());
            	    									

            	    										if (current==null) {
            	    											current = createModelElement(grammarAccess.getTableRule());
            	    										}
            	    										setWithLastConsumed(current, "schema", true, "table.schemaName");
            	    									

            	    }


            	    }

            	    otherlv_2=(Token)match(input,14,FOLLOW_5); 

            	    								newLeafNode(otherlv_2, grammarAccess.getTableAccess().getEqualsSignKeyword_0_1());
            	    							
            	    // InternalHdbTable.g:762:8: ( (lv_schemaName_3_0= RULE_STRING ) )
            	    // InternalHdbTable.g:763:9: (lv_schemaName_3_0= RULE_STRING )
            	    {
            	    // InternalHdbTable.g:763:9: (lv_schemaName_3_0= RULE_STRING )
            	    // InternalHdbTable.g:764:10: lv_schemaName_3_0= RULE_STRING
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

            	    otherlv_4=(Token)match(input,15,FOLLOW_16); 

            	    								newLeafNode(otherlv_4, grammarAccess.getTableAccess().getSemicolonKeyword_0_3());
            	    							

            	    }


            	    }

            	     
            	    					getUnorderedGroupHelper().returnFromSelection(grammarAccess.getTableAccess().getUnorderedGroup());
            	    				

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalHdbTable.g:790:3: ({...}? => ( ({...}? => ( ( (lv_type_5_0= 'table.tableType' ) ) otherlv_6= '=' ( (lv_typeValue_7_0= RULE_ID ) ) otherlv_8= ';' ) ) ) )
            	    {
            	    // InternalHdbTable.g:790:3: ({...}? => ( ({...}? => ( ( (lv_type_5_0= 'table.tableType' ) ) otherlv_6= '=' ( (lv_typeValue_7_0= RULE_ID ) ) otherlv_8= ';' ) ) ) )
            	    // InternalHdbTable.g:791:4: {...}? => ( ({...}? => ( ( (lv_type_5_0= 'table.tableType' ) ) otherlv_6= '=' ( (lv_typeValue_7_0= RULE_ID ) ) otherlv_8= ';' ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getTableAccess().getUnorderedGroup(), 1) ) {
            	        throw new FailedPredicateException(input, "ruleTable", "getUnorderedGroupHelper().canSelect(grammarAccess.getTableAccess().getUnorderedGroup(), 1)");
            	    }
            	    // InternalHdbTable.g:791:99: ( ({...}? => ( ( (lv_type_5_0= 'table.tableType' ) ) otherlv_6= '=' ( (lv_typeValue_7_0= RULE_ID ) ) otherlv_8= ';' ) ) )
            	    // InternalHdbTable.g:792:5: ({...}? => ( ( (lv_type_5_0= 'table.tableType' ) ) otherlv_6= '=' ( (lv_typeValue_7_0= RULE_ID ) ) otherlv_8= ';' ) )
            	    {

            	    					getUnorderedGroupHelper().select(grammarAccess.getTableAccess().getUnorderedGroup(), 1);
            	    				
            	    // InternalHdbTable.g:795:8: ({...}? => ( ( (lv_type_5_0= 'table.tableType' ) ) otherlv_6= '=' ( (lv_typeValue_7_0= RULE_ID ) ) otherlv_8= ';' ) )
            	    // InternalHdbTable.g:795:9: {...}? => ( ( (lv_type_5_0= 'table.tableType' ) ) otherlv_6= '=' ( (lv_typeValue_7_0= RULE_ID ) ) otherlv_8= ';' )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleTable", "true");
            	    }
            	    // InternalHdbTable.g:795:18: ( ( (lv_type_5_0= 'table.tableType' ) ) otherlv_6= '=' ( (lv_typeValue_7_0= RULE_ID ) ) otherlv_8= ';' )
            	    // InternalHdbTable.g:795:19: ( (lv_type_5_0= 'table.tableType' ) ) otherlv_6= '=' ( (lv_typeValue_7_0= RULE_ID ) ) otherlv_8= ';'
            	    {
            	    // InternalHdbTable.g:795:19: ( (lv_type_5_0= 'table.tableType' ) )
            	    // InternalHdbTable.g:796:9: (lv_type_5_0= 'table.tableType' )
            	    {
            	    // InternalHdbTable.g:796:9: (lv_type_5_0= 'table.tableType' )
            	    // InternalHdbTable.g:797:10: lv_type_5_0= 'table.tableType'
            	    {
            	    lv_type_5_0=(Token)match(input,31,FOLLOW_4); 

            	    										newLeafNode(lv_type_5_0, grammarAccess.getTableAccess().getTypeTableTableTypeKeyword_1_0_0());
            	    									

            	    										if (current==null) {
            	    											current = createModelElement(grammarAccess.getTableRule());
            	    										}
            	    										setWithLastConsumed(current, "type", true, "table.tableType");
            	    									

            	    }


            	    }

            	    otherlv_6=(Token)match(input,14,FOLLOW_8); 

            	    								newLeafNode(otherlv_6, grammarAccess.getTableAccess().getEqualsSignKeyword_1_1());
            	    							
            	    // InternalHdbTable.g:813:8: ( (lv_typeValue_7_0= RULE_ID ) )
            	    // InternalHdbTable.g:814:9: (lv_typeValue_7_0= RULE_ID )
            	    {
            	    // InternalHdbTable.g:814:9: (lv_typeValue_7_0= RULE_ID )
            	    // InternalHdbTable.g:815:10: lv_typeValue_7_0= RULE_ID
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

            	    otherlv_8=(Token)match(input,15,FOLLOW_16); 

            	    								newLeafNode(otherlv_8, grammarAccess.getTableAccess().getSemicolonKeyword_1_3());
            	    							

            	    }


            	    }

            	     
            	    					getUnorderedGroupHelper().returnFromSelection(grammarAccess.getTableAccess().getUnorderedGroup());
            	    				

            	    }


            	    }


            	    }
            	    break;
            	case 3 :
            	    // InternalHdbTable.g:841:3: ({...}? => ( ({...}? => ( ( (lv_description_9_0= 'table.description' ) ) otherlv_10= '=' ( (lv_descriptionText_11_0= RULE_STRING ) ) otherlv_12= ';' ) ) ) )
            	    {
            	    // InternalHdbTable.g:841:3: ({...}? => ( ({...}? => ( ( (lv_description_9_0= 'table.description' ) ) otherlv_10= '=' ( (lv_descriptionText_11_0= RULE_STRING ) ) otherlv_12= ';' ) ) ) )
            	    // InternalHdbTable.g:842:4: {...}? => ( ({...}? => ( ( (lv_description_9_0= 'table.description' ) ) otherlv_10= '=' ( (lv_descriptionText_11_0= RULE_STRING ) ) otherlv_12= ';' ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getTableAccess().getUnorderedGroup(), 2) ) {
            	        throw new FailedPredicateException(input, "ruleTable", "getUnorderedGroupHelper().canSelect(grammarAccess.getTableAccess().getUnorderedGroup(), 2)");
            	    }
            	    // InternalHdbTable.g:842:99: ( ({...}? => ( ( (lv_description_9_0= 'table.description' ) ) otherlv_10= '=' ( (lv_descriptionText_11_0= RULE_STRING ) ) otherlv_12= ';' ) ) )
            	    // InternalHdbTable.g:843:5: ({...}? => ( ( (lv_description_9_0= 'table.description' ) ) otherlv_10= '=' ( (lv_descriptionText_11_0= RULE_STRING ) ) otherlv_12= ';' ) )
            	    {

            	    					getUnorderedGroupHelper().select(grammarAccess.getTableAccess().getUnorderedGroup(), 2);
            	    				
            	    // InternalHdbTable.g:846:8: ({...}? => ( ( (lv_description_9_0= 'table.description' ) ) otherlv_10= '=' ( (lv_descriptionText_11_0= RULE_STRING ) ) otherlv_12= ';' ) )
            	    // InternalHdbTable.g:846:9: {...}? => ( ( (lv_description_9_0= 'table.description' ) ) otherlv_10= '=' ( (lv_descriptionText_11_0= RULE_STRING ) ) otherlv_12= ';' )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleTable", "true");
            	    }
            	    // InternalHdbTable.g:846:18: ( ( (lv_description_9_0= 'table.description' ) ) otherlv_10= '=' ( (lv_descriptionText_11_0= RULE_STRING ) ) otherlv_12= ';' )
            	    // InternalHdbTable.g:846:19: ( (lv_description_9_0= 'table.description' ) ) otherlv_10= '=' ( (lv_descriptionText_11_0= RULE_STRING ) ) otherlv_12= ';'
            	    {
            	    // InternalHdbTable.g:846:19: ( (lv_description_9_0= 'table.description' ) )
            	    // InternalHdbTable.g:847:9: (lv_description_9_0= 'table.description' )
            	    {
            	    // InternalHdbTable.g:847:9: (lv_description_9_0= 'table.description' )
            	    // InternalHdbTable.g:848:10: lv_description_9_0= 'table.description'
            	    {
            	    lv_description_9_0=(Token)match(input,32,FOLLOW_4); 

            	    										newLeafNode(lv_description_9_0, grammarAccess.getTableAccess().getDescriptionTableDescriptionKeyword_2_0_0());
            	    									

            	    										if (current==null) {
            	    											current = createModelElement(grammarAccess.getTableRule());
            	    										}
            	    										setWithLastConsumed(current, "description", true, "table.description");
            	    									

            	    }


            	    }

            	    otherlv_10=(Token)match(input,14,FOLLOW_5); 

            	    								newLeafNode(otherlv_10, grammarAccess.getTableAccess().getEqualsSignKeyword_2_1());
            	    							
            	    // InternalHdbTable.g:864:8: ( (lv_descriptionText_11_0= RULE_STRING ) )
            	    // InternalHdbTable.g:865:9: (lv_descriptionText_11_0= RULE_STRING )
            	    {
            	    // InternalHdbTable.g:865:9: (lv_descriptionText_11_0= RULE_STRING )
            	    // InternalHdbTable.g:866:10: lv_descriptionText_11_0= RULE_STRING
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

            	    otherlv_12=(Token)match(input,15,FOLLOW_16); 

            	    								newLeafNode(otherlv_12, grammarAccess.getTableAccess().getSemicolonKeyword_2_3());
            	    							

            	    }


            	    }

            	     
            	    					getUnorderedGroupHelper().returnFromSelection(grammarAccess.getTableAccess().getUnorderedGroup());
            	    				

            	    }


            	    }


            	    }
            	    break;
            	case 4 :
            	    // InternalHdbTable.g:892:3: ({...}? => ( ({...}? => ( ( (lv_columns_13_0= 'table.columns' ) ) otherlv_14= '=' otherlv_15= '[' ( ( (lv_columnsValues_16_0= ruleColumnType ) ) (otherlv_17= ',' ( (lv_columnsValues_18_0= ruleColumnType ) ) )* )? otherlv_19= ']' otherlv_20= ';' ) ) ) )
            	    {
            	    // InternalHdbTable.g:892:3: ({...}? => ( ({...}? => ( ( (lv_columns_13_0= 'table.columns' ) ) otherlv_14= '=' otherlv_15= '[' ( ( (lv_columnsValues_16_0= ruleColumnType ) ) (otherlv_17= ',' ( (lv_columnsValues_18_0= ruleColumnType ) ) )* )? otherlv_19= ']' otherlv_20= ';' ) ) ) )
            	    // InternalHdbTable.g:893:4: {...}? => ( ({...}? => ( ( (lv_columns_13_0= 'table.columns' ) ) otherlv_14= '=' otherlv_15= '[' ( ( (lv_columnsValues_16_0= ruleColumnType ) ) (otherlv_17= ',' ( (lv_columnsValues_18_0= ruleColumnType ) ) )* )? otherlv_19= ']' otherlv_20= ';' ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getTableAccess().getUnorderedGroup(), 3) ) {
            	        throw new FailedPredicateException(input, "ruleTable", "getUnorderedGroupHelper().canSelect(grammarAccess.getTableAccess().getUnorderedGroup(), 3)");
            	    }
            	    // InternalHdbTable.g:893:99: ( ({...}? => ( ( (lv_columns_13_0= 'table.columns' ) ) otherlv_14= '=' otherlv_15= '[' ( ( (lv_columnsValues_16_0= ruleColumnType ) ) (otherlv_17= ',' ( (lv_columnsValues_18_0= ruleColumnType ) ) )* )? otherlv_19= ']' otherlv_20= ';' ) ) )
            	    // InternalHdbTable.g:894:5: ({...}? => ( ( (lv_columns_13_0= 'table.columns' ) ) otherlv_14= '=' otherlv_15= '[' ( ( (lv_columnsValues_16_0= ruleColumnType ) ) (otherlv_17= ',' ( (lv_columnsValues_18_0= ruleColumnType ) ) )* )? otherlv_19= ']' otherlv_20= ';' ) )
            	    {

            	    					getUnorderedGroupHelper().select(grammarAccess.getTableAccess().getUnorderedGroup(), 3);
            	    				
            	    // InternalHdbTable.g:897:8: ({...}? => ( ( (lv_columns_13_0= 'table.columns' ) ) otherlv_14= '=' otherlv_15= '[' ( ( (lv_columnsValues_16_0= ruleColumnType ) ) (otherlv_17= ',' ( (lv_columnsValues_18_0= ruleColumnType ) ) )* )? otherlv_19= ']' otherlv_20= ';' ) )
            	    // InternalHdbTable.g:897:9: {...}? => ( ( (lv_columns_13_0= 'table.columns' ) ) otherlv_14= '=' otherlv_15= '[' ( ( (lv_columnsValues_16_0= ruleColumnType ) ) (otherlv_17= ',' ( (lv_columnsValues_18_0= ruleColumnType ) ) )* )? otherlv_19= ']' otherlv_20= ';' )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleTable", "true");
            	    }
            	    // InternalHdbTable.g:897:18: ( ( (lv_columns_13_0= 'table.columns' ) ) otherlv_14= '=' otherlv_15= '[' ( ( (lv_columnsValues_16_0= ruleColumnType ) ) (otherlv_17= ',' ( (lv_columnsValues_18_0= ruleColumnType ) ) )* )? otherlv_19= ']' otherlv_20= ';' )
            	    // InternalHdbTable.g:897:19: ( (lv_columns_13_0= 'table.columns' ) ) otherlv_14= '=' otherlv_15= '[' ( ( (lv_columnsValues_16_0= ruleColumnType ) ) (otherlv_17= ',' ( (lv_columnsValues_18_0= ruleColumnType ) ) )* )? otherlv_19= ']' otherlv_20= ';'
            	    {
            	    // InternalHdbTable.g:897:19: ( (lv_columns_13_0= 'table.columns' ) )
            	    // InternalHdbTable.g:898:9: (lv_columns_13_0= 'table.columns' )
            	    {
            	    // InternalHdbTable.g:898:9: (lv_columns_13_0= 'table.columns' )
            	    // InternalHdbTable.g:899:10: lv_columns_13_0= 'table.columns'
            	    {
            	    lv_columns_13_0=(Token)match(input,33,FOLLOW_4); 

            	    										newLeafNode(lv_columns_13_0, grammarAccess.getTableAccess().getColumnsTableColumnsKeyword_3_0_0());
            	    									

            	    										if (current==null) {
            	    											current = createModelElement(grammarAccess.getTableRule());
            	    										}
            	    										setWithLastConsumed(current, "columns", true, "table.columns");
            	    									

            	    }


            	    }

            	    otherlv_14=(Token)match(input,14,FOLLOW_13); 

            	    								newLeafNode(otherlv_14, grammarAccess.getTableAccess().getEqualsSignKeyword_3_1());
            	    							
            	    otherlv_15=(Token)match(input,27,FOLLOW_17); 

            	    								newLeafNode(otherlv_15, grammarAccess.getTableAccess().getLeftSquareBracketKeyword_3_2());
            	    							
            	    // InternalHdbTable.g:919:8: ( ( (lv_columnsValues_16_0= ruleColumnType ) ) (otherlv_17= ',' ( (lv_columnsValues_18_0= ruleColumnType ) ) )* )?
            	    int alt7=2;
            	    int LA7_0 = input.LA(1);

            	    if ( (LA7_0==12||(LA7_0>=15 && LA7_0<=22)) ) {
            	        alt7=1;
            	    }
            	    switch (alt7) {
            	        case 1 :
            	            // InternalHdbTable.g:920:9: ( (lv_columnsValues_16_0= ruleColumnType ) ) (otherlv_17= ',' ( (lv_columnsValues_18_0= ruleColumnType ) ) )*
            	            {
            	            // InternalHdbTable.g:920:9: ( (lv_columnsValues_16_0= ruleColumnType ) )
            	            // InternalHdbTable.g:921:10: (lv_columnsValues_16_0= ruleColumnType )
            	            {
            	            // InternalHdbTable.g:921:10: (lv_columnsValues_16_0= ruleColumnType )
            	            // InternalHdbTable.g:922:11: lv_columnsValues_16_0= ruleColumnType
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

            	            // InternalHdbTable.g:939:9: (otherlv_17= ',' ( (lv_columnsValues_18_0= ruleColumnType ) ) )*
            	            loop6:
            	            do {
            	                int alt6=2;
            	                int LA6_0 = input.LA(1);

            	                if ( (LA6_0==28) ) {
            	                    alt6=1;
            	                }


            	                switch (alt6) {
            	            	case 1 :
            	            	    // InternalHdbTable.g:940:10: otherlv_17= ',' ( (lv_columnsValues_18_0= ruleColumnType ) )
            	            	    {
            	            	    otherlv_17=(Token)match(input,28,FOLLOW_18); 

            	            	    										newLeafNode(otherlv_17, grammarAccess.getTableAccess().getCommaKeyword_3_3_1_0());
            	            	    									
            	            	    // InternalHdbTable.g:944:10: ( (lv_columnsValues_18_0= ruleColumnType ) )
            	            	    // InternalHdbTable.g:945:11: (lv_columnsValues_18_0= ruleColumnType )
            	            	    {
            	            	    // InternalHdbTable.g:945:11: (lv_columnsValues_18_0= ruleColumnType )
            	            	    // InternalHdbTable.g:946:12: lv_columnsValues_18_0= ruleColumnType
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
            	            	    break loop6;
            	                }
            	            } while (true);


            	            }
            	            break;

            	    }

            	    otherlv_19=(Token)match(input,29,FOLLOW_6); 

            	    								newLeafNode(otherlv_19, grammarAccess.getTableAccess().getRightSquareBracketKeyword_3_4());
            	    							
            	    otherlv_20=(Token)match(input,15,FOLLOW_16); 

            	    								newLeafNode(otherlv_20, grammarAccess.getTableAccess().getSemicolonKeyword_3_5());
            	    							

            	    }


            	    }

            	     
            	    					getUnorderedGroupHelper().returnFromSelection(grammarAccess.getTableAccess().getUnorderedGroup());
            	    				

            	    }


            	    }


            	    }
            	    break;
            	case 5 :
            	    // InternalHdbTable.g:979:3: ({...}? => ( ({...}? => ( ( (lv_indexes_21_0= 'table.indexes' ) ) otherlv_22= '=' otherlv_23= '[' ( ( (lv_indexesValues_24_0= ruleIndexType ) ) (otherlv_25= ',' ( (lv_indexesValues_26_0= ruleIndexType ) ) )* )? otherlv_27= ']' otherlv_28= ';' ) ) ) )
            	    {
            	    // InternalHdbTable.g:979:3: ({...}? => ( ({...}? => ( ( (lv_indexes_21_0= 'table.indexes' ) ) otherlv_22= '=' otherlv_23= '[' ( ( (lv_indexesValues_24_0= ruleIndexType ) ) (otherlv_25= ',' ( (lv_indexesValues_26_0= ruleIndexType ) ) )* )? otherlv_27= ']' otherlv_28= ';' ) ) ) )
            	    // InternalHdbTable.g:980:4: {...}? => ( ({...}? => ( ( (lv_indexes_21_0= 'table.indexes' ) ) otherlv_22= '=' otherlv_23= '[' ( ( (lv_indexesValues_24_0= ruleIndexType ) ) (otherlv_25= ',' ( (lv_indexesValues_26_0= ruleIndexType ) ) )* )? otherlv_27= ']' otherlv_28= ';' ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getTableAccess().getUnorderedGroup(), 4) ) {
            	        throw new FailedPredicateException(input, "ruleTable", "getUnorderedGroupHelper().canSelect(grammarAccess.getTableAccess().getUnorderedGroup(), 4)");
            	    }
            	    // InternalHdbTable.g:980:99: ( ({...}? => ( ( (lv_indexes_21_0= 'table.indexes' ) ) otherlv_22= '=' otherlv_23= '[' ( ( (lv_indexesValues_24_0= ruleIndexType ) ) (otherlv_25= ',' ( (lv_indexesValues_26_0= ruleIndexType ) ) )* )? otherlv_27= ']' otherlv_28= ';' ) ) )
            	    // InternalHdbTable.g:981:5: ({...}? => ( ( (lv_indexes_21_0= 'table.indexes' ) ) otherlv_22= '=' otherlv_23= '[' ( ( (lv_indexesValues_24_0= ruleIndexType ) ) (otherlv_25= ',' ( (lv_indexesValues_26_0= ruleIndexType ) ) )* )? otherlv_27= ']' otherlv_28= ';' ) )
            	    {

            	    					getUnorderedGroupHelper().select(grammarAccess.getTableAccess().getUnorderedGroup(), 4);
            	    				
            	    // InternalHdbTable.g:984:8: ({...}? => ( ( (lv_indexes_21_0= 'table.indexes' ) ) otherlv_22= '=' otherlv_23= '[' ( ( (lv_indexesValues_24_0= ruleIndexType ) ) (otherlv_25= ',' ( (lv_indexesValues_26_0= ruleIndexType ) ) )* )? otherlv_27= ']' otherlv_28= ';' ) )
            	    // InternalHdbTable.g:984:9: {...}? => ( ( (lv_indexes_21_0= 'table.indexes' ) ) otherlv_22= '=' otherlv_23= '[' ( ( (lv_indexesValues_24_0= ruleIndexType ) ) (otherlv_25= ',' ( (lv_indexesValues_26_0= ruleIndexType ) ) )* )? otherlv_27= ']' otherlv_28= ';' )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleTable", "true");
            	    }
            	    // InternalHdbTable.g:984:18: ( ( (lv_indexes_21_0= 'table.indexes' ) ) otherlv_22= '=' otherlv_23= '[' ( ( (lv_indexesValues_24_0= ruleIndexType ) ) (otherlv_25= ',' ( (lv_indexesValues_26_0= ruleIndexType ) ) )* )? otherlv_27= ']' otherlv_28= ';' )
            	    // InternalHdbTable.g:984:19: ( (lv_indexes_21_0= 'table.indexes' ) ) otherlv_22= '=' otherlv_23= '[' ( ( (lv_indexesValues_24_0= ruleIndexType ) ) (otherlv_25= ',' ( (lv_indexesValues_26_0= ruleIndexType ) ) )* )? otherlv_27= ']' otherlv_28= ';'
            	    {
            	    // InternalHdbTable.g:984:19: ( (lv_indexes_21_0= 'table.indexes' ) )
            	    // InternalHdbTable.g:985:9: (lv_indexes_21_0= 'table.indexes' )
            	    {
            	    // InternalHdbTable.g:985:9: (lv_indexes_21_0= 'table.indexes' )
            	    // InternalHdbTable.g:986:10: lv_indexes_21_0= 'table.indexes'
            	    {
            	    lv_indexes_21_0=(Token)match(input,34,FOLLOW_4); 

            	    										newLeafNode(lv_indexes_21_0, grammarAccess.getTableAccess().getIndexesTableIndexesKeyword_4_0_0());
            	    									

            	    										if (current==null) {
            	    											current = createModelElement(grammarAccess.getTableRule());
            	    										}
            	    										setWithLastConsumed(current, "indexes", true, "table.indexes");
            	    									

            	    }


            	    }

            	    otherlv_22=(Token)match(input,14,FOLLOW_13); 

            	    								newLeafNode(otherlv_22, grammarAccess.getTableAccess().getEqualsSignKeyword_4_1());
            	    							
            	    otherlv_23=(Token)match(input,27,FOLLOW_19); 

            	    								newLeafNode(otherlv_23, grammarAccess.getTableAccess().getLeftSquareBracketKeyword_4_2());
            	    							
            	    // InternalHdbTable.g:1006:8: ( ( (lv_indexesValues_24_0= ruleIndexType ) ) (otherlv_25= ',' ( (lv_indexesValues_26_0= ruleIndexType ) ) )* )?
            	    int alt9=2;
            	    int LA9_0 = input.LA(1);

            	    if ( (LA9_0==12||(LA9_0>=24 && LA9_0<=26)) ) {
            	        alt9=1;
            	    }
            	    switch (alt9) {
            	        case 1 :
            	            // InternalHdbTable.g:1007:9: ( (lv_indexesValues_24_0= ruleIndexType ) ) (otherlv_25= ',' ( (lv_indexesValues_26_0= ruleIndexType ) ) )*
            	            {
            	            // InternalHdbTable.g:1007:9: ( (lv_indexesValues_24_0= ruleIndexType ) )
            	            // InternalHdbTable.g:1008:10: (lv_indexesValues_24_0= ruleIndexType )
            	            {
            	            // InternalHdbTable.g:1008:10: (lv_indexesValues_24_0= ruleIndexType )
            	            // InternalHdbTable.g:1009:11: lv_indexesValues_24_0= ruleIndexType
            	            {

            	            											newCompositeNode(grammarAccess.getTableAccess().getIndexesValuesIndexTypeParserRuleCall_4_3_0_0());
            	            										
            	            pushFollow(FOLLOW_15);
            	            lv_indexesValues_24_0=ruleIndexType();

            	            state._fsp--;


            	            											if (current==null) {
            	            												current = createModelElementForParent(grammarAccess.getTableRule());
            	            											}
            	            											add(
            	            												current,
            	            												"indexesValues",
            	            												lv_indexesValues_24_0,
            	            												"com.sap.xsk.models.hdbtable.HdbTable.IndexType");
            	            											afterParserOrEnumRuleCall();
            	            										

            	            }


            	            }

            	            // InternalHdbTable.g:1026:9: (otherlv_25= ',' ( (lv_indexesValues_26_0= ruleIndexType ) ) )*
            	            loop8:
            	            do {
            	                int alt8=2;
            	                int LA8_0 = input.LA(1);

            	                if ( (LA8_0==28) ) {
            	                    alt8=1;
            	                }


            	                switch (alt8) {
            	            	case 1 :
            	            	    // InternalHdbTable.g:1027:10: otherlv_25= ',' ( (lv_indexesValues_26_0= ruleIndexType ) )
            	            	    {
            	            	    otherlv_25=(Token)match(input,28,FOLLOW_20); 

            	            	    										newLeafNode(otherlv_25, grammarAccess.getTableAccess().getCommaKeyword_4_3_1_0());
            	            	    									
            	            	    // InternalHdbTable.g:1031:10: ( (lv_indexesValues_26_0= ruleIndexType ) )
            	            	    // InternalHdbTable.g:1032:11: (lv_indexesValues_26_0= ruleIndexType )
            	            	    {
            	            	    // InternalHdbTable.g:1032:11: (lv_indexesValues_26_0= ruleIndexType )
            	            	    // InternalHdbTable.g:1033:12: lv_indexesValues_26_0= ruleIndexType
            	            	    {

            	            	    												newCompositeNode(grammarAccess.getTableAccess().getIndexesValuesIndexTypeParserRuleCall_4_3_1_1_0());
            	            	    											
            	            	    pushFollow(FOLLOW_15);
            	            	    lv_indexesValues_26_0=ruleIndexType();

            	            	    state._fsp--;


            	            	    												if (current==null) {
            	            	    													current = createModelElementForParent(grammarAccess.getTableRule());
            	            	    												}
            	            	    												add(
            	            	    													current,
            	            	    													"indexesValues",
            	            	    													lv_indexesValues_26_0,
            	            	    													"com.sap.xsk.models.hdbtable.HdbTable.IndexType");
            	            	    												afterParserOrEnumRuleCall();
            	            	    											

            	            	    }


            	            	    }


            	            	    }
            	            	    break;

            	            	default :
            	            	    break loop8;
            	                }
            	            } while (true);


            	            }
            	            break;

            	    }

            	    otherlv_27=(Token)match(input,29,FOLLOW_6); 

            	    								newLeafNode(otherlv_27, grammarAccess.getTableAccess().getRightSquareBracketKeyword_4_4());
            	    							
            	    otherlv_28=(Token)match(input,15,FOLLOW_16); 

            	    								newLeafNode(otherlv_28, grammarAccess.getTableAccess().getSemicolonKeyword_4_5());
            	    							

            	    }


            	    }

            	     
            	    					getUnorderedGroupHelper().returnFromSelection(grammarAccess.getTableAccess().getUnorderedGroup());
            	    				

            	    }


            	    }


            	    }
            	    break;
            	case 6 :
            	    // InternalHdbTable.g:1066:3: ({...}? => ( ({...}? => ( ( ( (lv_primaryKeyColumns_29_0= 'table.primaryKey.pkcolumns' ) ) otherlv_30= '=' otherlv_31= '[' ( ( (lv_tablePrimaryKeyColumnsValues_32_0= RULE_STRING ) ) (otherlv_33= ',' ( (lv_tablePrimaryKeyColumnsValues_34_0= RULE_STRING ) ) )* )? otherlv_35= ']' )? otherlv_36= ';' ) ) ) )
            	    {
            	    // InternalHdbTable.g:1066:3: ({...}? => ( ({...}? => ( ( ( (lv_primaryKeyColumns_29_0= 'table.primaryKey.pkcolumns' ) ) otherlv_30= '=' otherlv_31= '[' ( ( (lv_tablePrimaryKeyColumnsValues_32_0= RULE_STRING ) ) (otherlv_33= ',' ( (lv_tablePrimaryKeyColumnsValues_34_0= RULE_STRING ) ) )* )? otherlv_35= ']' )? otherlv_36= ';' ) ) ) )
            	    // InternalHdbTable.g:1067:4: {...}? => ( ({...}? => ( ( ( (lv_primaryKeyColumns_29_0= 'table.primaryKey.pkcolumns' ) ) otherlv_30= '=' otherlv_31= '[' ( ( (lv_tablePrimaryKeyColumnsValues_32_0= RULE_STRING ) ) (otherlv_33= ',' ( (lv_tablePrimaryKeyColumnsValues_34_0= RULE_STRING ) ) )* )? otherlv_35= ']' )? otherlv_36= ';' ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getTableAccess().getUnorderedGroup(), 5) ) {
            	        throw new FailedPredicateException(input, "ruleTable", "getUnorderedGroupHelper().canSelect(grammarAccess.getTableAccess().getUnorderedGroup(), 5)");
            	    }
            	    // InternalHdbTable.g:1067:99: ( ({...}? => ( ( ( (lv_primaryKeyColumns_29_0= 'table.primaryKey.pkcolumns' ) ) otherlv_30= '=' otherlv_31= '[' ( ( (lv_tablePrimaryKeyColumnsValues_32_0= RULE_STRING ) ) (otherlv_33= ',' ( (lv_tablePrimaryKeyColumnsValues_34_0= RULE_STRING ) ) )* )? otherlv_35= ']' )? otherlv_36= ';' ) ) )
            	    // InternalHdbTable.g:1068:5: ({...}? => ( ( ( (lv_primaryKeyColumns_29_0= 'table.primaryKey.pkcolumns' ) ) otherlv_30= '=' otherlv_31= '[' ( ( (lv_tablePrimaryKeyColumnsValues_32_0= RULE_STRING ) ) (otherlv_33= ',' ( (lv_tablePrimaryKeyColumnsValues_34_0= RULE_STRING ) ) )* )? otherlv_35= ']' )? otherlv_36= ';' ) )
            	    {

            	    					getUnorderedGroupHelper().select(grammarAccess.getTableAccess().getUnorderedGroup(), 5);
            	    				
            	    // InternalHdbTable.g:1071:8: ({...}? => ( ( ( (lv_primaryKeyColumns_29_0= 'table.primaryKey.pkcolumns' ) ) otherlv_30= '=' otherlv_31= '[' ( ( (lv_tablePrimaryKeyColumnsValues_32_0= RULE_STRING ) ) (otherlv_33= ',' ( (lv_tablePrimaryKeyColumnsValues_34_0= RULE_STRING ) ) )* )? otherlv_35= ']' )? otherlv_36= ';' ) )
            	    // InternalHdbTable.g:1071:9: {...}? => ( ( ( (lv_primaryKeyColumns_29_0= 'table.primaryKey.pkcolumns' ) ) otherlv_30= '=' otherlv_31= '[' ( ( (lv_tablePrimaryKeyColumnsValues_32_0= RULE_STRING ) ) (otherlv_33= ',' ( (lv_tablePrimaryKeyColumnsValues_34_0= RULE_STRING ) ) )* )? otherlv_35= ']' )? otherlv_36= ';' )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleTable", "true");
            	    }
            	    // InternalHdbTable.g:1071:18: ( ( ( (lv_primaryKeyColumns_29_0= 'table.primaryKey.pkcolumns' ) ) otherlv_30= '=' otherlv_31= '[' ( ( (lv_tablePrimaryKeyColumnsValues_32_0= RULE_STRING ) ) (otherlv_33= ',' ( (lv_tablePrimaryKeyColumnsValues_34_0= RULE_STRING ) ) )* )? otherlv_35= ']' )? otherlv_36= ';' )
            	    // InternalHdbTable.g:1071:19: ( ( (lv_primaryKeyColumns_29_0= 'table.primaryKey.pkcolumns' ) ) otherlv_30= '=' otherlv_31= '[' ( ( (lv_tablePrimaryKeyColumnsValues_32_0= RULE_STRING ) ) (otherlv_33= ',' ( (lv_tablePrimaryKeyColumnsValues_34_0= RULE_STRING ) ) )* )? otherlv_35= ']' )? otherlv_36= ';'
            	    {
            	    // InternalHdbTable.g:1071:19: ( ( (lv_primaryKeyColumns_29_0= 'table.primaryKey.pkcolumns' ) ) otherlv_30= '=' otherlv_31= '[' ( ( (lv_tablePrimaryKeyColumnsValues_32_0= RULE_STRING ) ) (otherlv_33= ',' ( (lv_tablePrimaryKeyColumnsValues_34_0= RULE_STRING ) ) )* )? otherlv_35= ']' )?
            	    int alt12=2;
            	    int LA12_0 = input.LA(1);

            	    if ( (LA12_0==35) ) {
            	        alt12=1;
            	    }
            	    switch (alt12) {
            	        case 1 :
            	            // InternalHdbTable.g:1072:9: ( (lv_primaryKeyColumns_29_0= 'table.primaryKey.pkcolumns' ) ) otherlv_30= '=' otherlv_31= '[' ( ( (lv_tablePrimaryKeyColumnsValues_32_0= RULE_STRING ) ) (otherlv_33= ',' ( (lv_tablePrimaryKeyColumnsValues_34_0= RULE_STRING ) ) )* )? otherlv_35= ']'
            	            {
            	            // InternalHdbTable.g:1072:9: ( (lv_primaryKeyColumns_29_0= 'table.primaryKey.pkcolumns' ) )
            	            // InternalHdbTable.g:1073:10: (lv_primaryKeyColumns_29_0= 'table.primaryKey.pkcolumns' )
            	            {
            	            // InternalHdbTable.g:1073:10: (lv_primaryKeyColumns_29_0= 'table.primaryKey.pkcolumns' )
            	            // InternalHdbTable.g:1074:11: lv_primaryKeyColumns_29_0= 'table.primaryKey.pkcolumns'
            	            {
            	            lv_primaryKeyColumns_29_0=(Token)match(input,35,FOLLOW_4); 

            	            											newLeafNode(lv_primaryKeyColumns_29_0, grammarAccess.getTableAccess().getPrimaryKeyColumnsTablePrimaryKeyPkcolumnsKeyword_5_0_0_0());
            	            										

            	            											if (current==null) {
            	            												current = createModelElement(grammarAccess.getTableRule());
            	            											}
            	            											setWithLastConsumed(current, "primaryKeyColumns", true, "table.primaryKey.pkcolumns");
            	            										

            	            }


            	            }

            	            otherlv_30=(Token)match(input,14,FOLLOW_13); 

            	            									newLeafNode(otherlv_30, grammarAccess.getTableAccess().getEqualsSignKeyword_5_0_1());
            	            								
            	            otherlv_31=(Token)match(input,27,FOLLOW_14); 

            	            									newLeafNode(otherlv_31, grammarAccess.getTableAccess().getLeftSquareBracketKeyword_5_0_2());
            	            								
            	            // InternalHdbTable.g:1094:9: ( ( (lv_tablePrimaryKeyColumnsValues_32_0= RULE_STRING ) ) (otherlv_33= ',' ( (lv_tablePrimaryKeyColumnsValues_34_0= RULE_STRING ) ) )* )?
            	            int alt11=2;
            	            int LA11_0 = input.LA(1);

            	            if ( (LA11_0==RULE_STRING) ) {
            	                alt11=1;
            	            }
            	            switch (alt11) {
            	                case 1 :
            	                    // InternalHdbTable.g:1095:10: ( (lv_tablePrimaryKeyColumnsValues_32_0= RULE_STRING ) ) (otherlv_33= ',' ( (lv_tablePrimaryKeyColumnsValues_34_0= RULE_STRING ) ) )*
            	                    {
            	                    // InternalHdbTable.g:1095:10: ( (lv_tablePrimaryKeyColumnsValues_32_0= RULE_STRING ) )
            	                    // InternalHdbTable.g:1096:11: (lv_tablePrimaryKeyColumnsValues_32_0= RULE_STRING )
            	                    {
            	                    // InternalHdbTable.g:1096:11: (lv_tablePrimaryKeyColumnsValues_32_0= RULE_STRING )
            	                    // InternalHdbTable.g:1097:12: lv_tablePrimaryKeyColumnsValues_32_0= RULE_STRING
            	                    {
            	                    lv_tablePrimaryKeyColumnsValues_32_0=(Token)match(input,RULE_STRING,FOLLOW_15); 

            	                    												newLeafNode(lv_tablePrimaryKeyColumnsValues_32_0, grammarAccess.getTableAccess().getTablePrimaryKeyColumnsValuesSTRINGTerminalRuleCall_5_0_3_0_0());
            	                    											

            	                    												if (current==null) {
            	                    													current = createModelElement(grammarAccess.getTableRule());
            	                    												}
            	                    												addWithLastConsumed(
            	                    													current,
            	                    													"tablePrimaryKeyColumnsValues",
            	                    													lv_tablePrimaryKeyColumnsValues_32_0,
            	                    													"org.eclipse.xtext.common.Terminals.STRING");
            	                    											

            	                    }


            	                    }

            	                    // InternalHdbTable.g:1113:10: (otherlv_33= ',' ( (lv_tablePrimaryKeyColumnsValues_34_0= RULE_STRING ) ) )*
            	                    loop10:
            	                    do {
            	                        int alt10=2;
            	                        int LA10_0 = input.LA(1);

            	                        if ( (LA10_0==28) ) {
            	                            alt10=1;
            	                        }


            	                        switch (alt10) {
            	                    	case 1 :
            	                    	    // InternalHdbTable.g:1114:11: otherlv_33= ',' ( (lv_tablePrimaryKeyColumnsValues_34_0= RULE_STRING ) )
            	                    	    {
            	                    	    otherlv_33=(Token)match(input,28,FOLLOW_5); 

            	                    	    											newLeafNode(otherlv_33, grammarAccess.getTableAccess().getCommaKeyword_5_0_3_1_0());
            	                    	    										
            	                    	    // InternalHdbTable.g:1118:11: ( (lv_tablePrimaryKeyColumnsValues_34_0= RULE_STRING ) )
            	                    	    // InternalHdbTable.g:1119:12: (lv_tablePrimaryKeyColumnsValues_34_0= RULE_STRING )
            	                    	    {
            	                    	    // InternalHdbTable.g:1119:12: (lv_tablePrimaryKeyColumnsValues_34_0= RULE_STRING )
            	                    	    // InternalHdbTable.g:1120:13: lv_tablePrimaryKeyColumnsValues_34_0= RULE_STRING
            	                    	    {
            	                    	    lv_tablePrimaryKeyColumnsValues_34_0=(Token)match(input,RULE_STRING,FOLLOW_15); 

            	                    	    													newLeafNode(lv_tablePrimaryKeyColumnsValues_34_0, grammarAccess.getTableAccess().getTablePrimaryKeyColumnsValuesSTRINGTerminalRuleCall_5_0_3_1_1_0());
            	                    	    												

            	                    	    													if (current==null) {
            	                    	    														current = createModelElement(grammarAccess.getTableRule());
            	                    	    													}
            	                    	    													addWithLastConsumed(
            	                    	    														current,
            	                    	    														"tablePrimaryKeyColumnsValues",
            	                    	    														lv_tablePrimaryKeyColumnsValues_34_0,
            	                    	    														"org.eclipse.xtext.common.Terminals.STRING");
            	                    	    												

            	                    	    }


            	                    	    }


            	                    	    }
            	                    	    break;

            	                    	default :
            	                    	    break loop10;
            	                        }
            	                    } while (true);


            	                    }
            	                    break;

            	            }

            	            otherlv_35=(Token)match(input,29,FOLLOW_6); 

            	            									newLeafNode(otherlv_35, grammarAccess.getTableAccess().getRightSquareBracketKeyword_5_0_4());
            	            								

            	            }
            	            break;

            	    }

            	    otherlv_36=(Token)match(input,15,FOLLOW_16); 

            	    								newLeafNode(otherlv_36, grammarAccess.getTableAccess().getSemicolonKeyword_5_1());
            	    							

            	    }


            	    }

            	     
            	    					getUnorderedGroupHelper().returnFromSelection(grammarAccess.getTableAccess().getUnorderedGroup());
            	    				

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt13 >= 1 ) break loop13;
                        EarlyExitException eee =
                            new EarlyExitException(13, input);
                        throw eee;
                }
                cnt13++;
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


    protected DFA2 dfa2 = new DFA2(this);
    static final String dfa_1s = "\12\uffff";
    static final String dfa_2s = "\1\1\11\uffff";
    static final String dfa_3s = "\1\14\11\uffff";
    static final String dfa_4s = "\1\35\11\uffff";
    static final String dfa_5s = "\1\uffff\1\11\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10";
    static final String dfa_6s = "\1\0\11\uffff}>";
    static final String[] dfa_7s = {
            "\1\2\2\uffff\1\11\1\3\1\4\1\5\1\6\1\7\1\10\1\11\5\uffff\2\1",
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

    class DFA2 extends DFA {

        public DFA2(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 2;
            this.eot = dfa_1;
            this.eof = dfa_2;
            this.min = dfa_3;
            this.max = dfa_4;
            this.accept = dfa_5;
            this.special = dfa_6;
            this.transition = dfa_7;
        }
        public String getDescription() {
            return "()+ loopback of 120:5: ( ({...}? => ( ({...}? => (otherlv_1= '{' otherlv_2= 'name' otherlv_3= '=' ( (lv_columnName_4_0= RULE_STRING ) ) otherlv_5= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'sqlType' otherlv_7= '=' ( (lv_columnSqlType_8_0= RULE_ID ) ) otherlv_9= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'length' otherlv_11= '=' ( (lv_columnLength_12_0= RULE_INT ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'comment' otherlv_15= '=' ( (lv_columnComment_16_0= RULE_STRING ) ) otherlv_17= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_18= 'defaultValue' otherlv_19= '=' ( (lv_columnDefaultValue_20_0= RULE_STRING ) ) otherlv_21= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_22= 'precision' otherlv_23= '=' ( (lv_columnPrecision_24_0= RULE_INT ) ) otherlv_25= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_26= 'scale' otherlv_27= '=' ( (lv_columnScale_28_0= RULE_INT ) ) otherlv_29= ';' ) ) ) ) | ({...}? => ( ({...}? => ( (otherlv_30= 'nullable' otherlv_31= '=' ( (lv_columnNullable_32_0= RULE_BOOL ) ) )? otherlv_33= ';' otherlv_34= '}' ) ) ) ) )+";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA2_0 = input.LA(1);

                         
                        int index2_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA2_0==EOF||(LA2_0>=28 && LA2_0<=29)) ) {s = 1;}

                        else if ( LA2_0 == 12 && getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 0) ) {s = 2;}

                        else if ( LA2_0 == 16 && getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 1) ) {s = 3;}

                        else if ( LA2_0 == 17 && getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 2) ) {s = 4;}

                        else if ( LA2_0 == 18 && getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 3) ) {s = 5;}

                        else if ( LA2_0 == 19 && getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 4) ) {s = 6;}

                        else if ( LA2_0 == 20 && getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 5) ) {s = 7;}

                        else if ( LA2_0 == 21 && getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 6) ) {s = 8;}

                        else if ( ( LA2_0 == 15 || LA2_0 == 22 ) && getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 7) ) {s = 9;}

                         
                        input.seek(index2_0);
                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 2, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x00000000007F9002L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000007001002L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000020000010L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000030000000L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000FC0008002L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x00000000207F9000L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x00000000007F9000L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000000027001000L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000000007001000L});

}
