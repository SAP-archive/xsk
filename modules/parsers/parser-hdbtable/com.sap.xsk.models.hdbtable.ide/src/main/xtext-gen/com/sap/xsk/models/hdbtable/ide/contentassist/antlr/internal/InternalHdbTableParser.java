/*
 * Copyright (c) 2019-2021 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2019-2021 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.models.hdbtable.ide.contentassist.antlr.internal;

import java.io.InputStream;
import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.DFA;
import com.sap.xsk.models.hdbtable.services.HdbTableGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalHdbTableParser extends AbstractInternalContentAssistParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_STRING", "RULE_ID", "RULE_INT", "RULE_BOOL", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'{'", "'name'", "'='", "';'", "'sqlType'", "'length'", "'comment'", "'defaultValue'", "'precision'", "'scale'", "'}'", "'nullable'", "'unique'", "'order'", "'['", "']'", "','", "'indexColumns'", "'table.schemaName'", "'table.tableType'", "'table.description'", "'table.columns'", "'table.indexes'", "'table.primaryKey.pkcolumns'"
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

    	public void setGrammarAccess(HdbTableGrammarAccess grammarAccess) {
    		this.grammarAccess = grammarAccess;
    	}

    	@Override
    	protected Grammar getGrammar() {
    		return grammarAccess.getGrammar();
    	}

    	@Override
    	protected String getValueForTokenName(String tokenName) {
    		return tokenName;
    	}



    // $ANTLR start "entryRuleHdbTableModel"
    // InternalHdbTable.g:53:1: entryRuleHdbTableModel : ruleHdbTableModel EOF ;
    public final void entryRuleHdbTableModel() throws RecognitionException {
        try {
            // InternalHdbTable.g:54:1: ( ruleHdbTableModel EOF )
            // InternalHdbTable.g:55:1: ruleHdbTableModel EOF
            {
             before(grammarAccess.getHdbTableModelRule()); 
            pushFollow(FOLLOW_1);
            ruleHdbTableModel();

            state._fsp--;

             after(grammarAccess.getHdbTableModelRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleHdbTableModel"


    // $ANTLR start "ruleHdbTableModel"
    // InternalHdbTable.g:62:1: ruleHdbTableModel : ( ( rule__HdbTableModel__TableElementAssignment ) ) ;
    public final void ruleHdbTableModel() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:66:2: ( ( ( rule__HdbTableModel__TableElementAssignment ) ) )
            // InternalHdbTable.g:67:2: ( ( rule__HdbTableModel__TableElementAssignment ) )
            {
            // InternalHdbTable.g:67:2: ( ( rule__HdbTableModel__TableElementAssignment ) )
            // InternalHdbTable.g:68:3: ( rule__HdbTableModel__TableElementAssignment )
            {
             before(grammarAccess.getHdbTableModelAccess().getTableElementAssignment()); 
            // InternalHdbTable.g:69:3: ( rule__HdbTableModel__TableElementAssignment )
            // InternalHdbTable.g:69:4: rule__HdbTableModel__TableElementAssignment
            {
            pushFollow(FOLLOW_2);
            rule__HdbTableModel__TableElementAssignment();

            state._fsp--;


            }

             after(grammarAccess.getHdbTableModelAccess().getTableElementAssignment()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleHdbTableModel"


    // $ANTLR start "entryRuleColumnType"
    // InternalHdbTable.g:78:1: entryRuleColumnType : ruleColumnType EOF ;
    public final void entryRuleColumnType() throws RecognitionException {
        try {
            // InternalHdbTable.g:79:1: ( ruleColumnType EOF )
            // InternalHdbTable.g:80:1: ruleColumnType EOF
            {
             before(grammarAccess.getColumnTypeRule()); 
            pushFollow(FOLLOW_1);
            ruleColumnType();

            state._fsp--;

             after(grammarAccess.getColumnTypeRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleColumnType"


    // $ANTLR start "ruleColumnType"
    // InternalHdbTable.g:87:1: ruleColumnType : ( ( rule__ColumnType__UnorderedGroup ) ) ;
    public final void ruleColumnType() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:91:2: ( ( ( rule__ColumnType__UnorderedGroup ) ) )
            // InternalHdbTable.g:92:2: ( ( rule__ColumnType__UnorderedGroup ) )
            {
            // InternalHdbTable.g:92:2: ( ( rule__ColumnType__UnorderedGroup ) )
            // InternalHdbTable.g:93:3: ( rule__ColumnType__UnorderedGroup )
            {
             before(grammarAccess.getColumnTypeAccess().getUnorderedGroup()); 
            // InternalHdbTable.g:94:3: ( rule__ColumnType__UnorderedGroup )
            // InternalHdbTable.g:94:4: rule__ColumnType__UnorderedGroup
            {
            pushFollow(FOLLOW_2);
            rule__ColumnType__UnorderedGroup();

            state._fsp--;


            }

             after(grammarAccess.getColumnTypeAccess().getUnorderedGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleColumnType"


    // $ANTLR start "entryRuleIndexType"
    // InternalHdbTable.g:103:1: entryRuleIndexType : ruleIndexType EOF ;
    public final void entryRuleIndexType() throws RecognitionException {
        try {
            // InternalHdbTable.g:104:1: ( ruleIndexType EOF )
            // InternalHdbTable.g:105:1: ruleIndexType EOF
            {
             before(grammarAccess.getIndexTypeRule()); 
            pushFollow(FOLLOW_1);
            ruleIndexType();

            state._fsp--;

             after(grammarAccess.getIndexTypeRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleIndexType"


    // $ANTLR start "ruleIndexType"
    // InternalHdbTable.g:112:1: ruleIndexType : ( ( rule__IndexType__UnorderedGroup ) ) ;
    public final void ruleIndexType() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:116:2: ( ( ( rule__IndexType__UnorderedGroup ) ) )
            // InternalHdbTable.g:117:2: ( ( rule__IndexType__UnorderedGroup ) )
            {
            // InternalHdbTable.g:117:2: ( ( rule__IndexType__UnorderedGroup ) )
            // InternalHdbTable.g:118:3: ( rule__IndexType__UnorderedGroup )
            {
             before(grammarAccess.getIndexTypeAccess().getUnorderedGroup()); 
            // InternalHdbTable.g:119:3: ( rule__IndexType__UnorderedGroup )
            // InternalHdbTable.g:119:4: rule__IndexType__UnorderedGroup
            {
            pushFollow(FOLLOW_2);
            rule__IndexType__UnorderedGroup();

            state._fsp--;


            }

             after(grammarAccess.getIndexTypeAccess().getUnorderedGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleIndexType"


    // $ANTLR start "entryRuleTable"
    // InternalHdbTable.g:128:1: entryRuleTable : ruleTable EOF ;
    public final void entryRuleTable() throws RecognitionException {
        try {
            // InternalHdbTable.g:129:1: ( ruleTable EOF )
            // InternalHdbTable.g:130:1: ruleTable EOF
            {
             before(grammarAccess.getTableRule()); 
            pushFollow(FOLLOW_1);
            ruleTable();

            state._fsp--;

             after(grammarAccess.getTableRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleTable"


    // $ANTLR start "ruleTable"
    // InternalHdbTable.g:137:1: ruleTable : ( ( rule__Table__UnorderedGroup ) ) ;
    public final void ruleTable() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:141:2: ( ( ( rule__Table__UnorderedGroup ) ) )
            // InternalHdbTable.g:142:2: ( ( rule__Table__UnorderedGroup ) )
            {
            // InternalHdbTable.g:142:2: ( ( rule__Table__UnorderedGroup ) )
            // InternalHdbTable.g:143:3: ( rule__Table__UnorderedGroup )
            {
             before(grammarAccess.getTableAccess().getUnorderedGroup()); 
            // InternalHdbTable.g:144:3: ( rule__Table__UnorderedGroup )
            // InternalHdbTable.g:144:4: rule__Table__UnorderedGroup
            {
            pushFollow(FOLLOW_2);
            rule__Table__UnorderedGroup();

            state._fsp--;


            }

             after(grammarAccess.getTableAccess().getUnorderedGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleTable"


    // $ANTLR start "rule__ColumnType__Group_0__0"
    // InternalHdbTable.g:152:1: rule__ColumnType__Group_0__0 : rule__ColumnType__Group_0__0__Impl rule__ColumnType__Group_0__1 ;
    public final void rule__ColumnType__Group_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:156:1: ( rule__ColumnType__Group_0__0__Impl rule__ColumnType__Group_0__1 )
            // InternalHdbTable.g:157:2: rule__ColumnType__Group_0__0__Impl rule__ColumnType__Group_0__1
            {
            pushFollow(FOLLOW_3);
            rule__ColumnType__Group_0__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ColumnType__Group_0__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__Group_0__0"


    // $ANTLR start "rule__ColumnType__Group_0__0__Impl"
    // InternalHdbTable.g:164:1: rule__ColumnType__Group_0__0__Impl : ( '{' ) ;
    public final void rule__ColumnType__Group_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:168:1: ( ( '{' ) )
            // InternalHdbTable.g:169:1: ( '{' )
            {
            // InternalHdbTable.g:169:1: ( '{' )
            // InternalHdbTable.g:170:2: '{'
            {
             before(grammarAccess.getColumnTypeAccess().getLeftCurlyBracketKeyword_0_0()); 
            match(input,12,FOLLOW_2); 
             after(grammarAccess.getColumnTypeAccess().getLeftCurlyBracketKeyword_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__Group_0__0__Impl"


    // $ANTLR start "rule__ColumnType__Group_0__1"
    // InternalHdbTable.g:179:1: rule__ColumnType__Group_0__1 : rule__ColumnType__Group_0__1__Impl rule__ColumnType__Group_0__2 ;
    public final void rule__ColumnType__Group_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:183:1: ( rule__ColumnType__Group_0__1__Impl rule__ColumnType__Group_0__2 )
            // InternalHdbTable.g:184:2: rule__ColumnType__Group_0__1__Impl rule__ColumnType__Group_0__2
            {
            pushFollow(FOLLOW_4);
            rule__ColumnType__Group_0__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ColumnType__Group_0__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__Group_0__1"


    // $ANTLR start "rule__ColumnType__Group_0__1__Impl"
    // InternalHdbTable.g:191:1: rule__ColumnType__Group_0__1__Impl : ( 'name' ) ;
    public final void rule__ColumnType__Group_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:195:1: ( ( 'name' ) )
            // InternalHdbTable.g:196:1: ( 'name' )
            {
            // InternalHdbTable.g:196:1: ( 'name' )
            // InternalHdbTable.g:197:2: 'name'
            {
             before(grammarAccess.getColumnTypeAccess().getNameKeyword_0_1()); 
            match(input,13,FOLLOW_2); 
             after(grammarAccess.getColumnTypeAccess().getNameKeyword_0_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__Group_0__1__Impl"


    // $ANTLR start "rule__ColumnType__Group_0__2"
    // InternalHdbTable.g:206:1: rule__ColumnType__Group_0__2 : rule__ColumnType__Group_0__2__Impl rule__ColumnType__Group_0__3 ;
    public final void rule__ColumnType__Group_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:210:1: ( rule__ColumnType__Group_0__2__Impl rule__ColumnType__Group_0__3 )
            // InternalHdbTable.g:211:2: rule__ColumnType__Group_0__2__Impl rule__ColumnType__Group_0__3
            {
            pushFollow(FOLLOW_5);
            rule__ColumnType__Group_0__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ColumnType__Group_0__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__Group_0__2"


    // $ANTLR start "rule__ColumnType__Group_0__2__Impl"
    // InternalHdbTable.g:218:1: rule__ColumnType__Group_0__2__Impl : ( '=' ) ;
    public final void rule__ColumnType__Group_0__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:222:1: ( ( '=' ) )
            // InternalHdbTable.g:223:1: ( '=' )
            {
            // InternalHdbTable.g:223:1: ( '=' )
            // InternalHdbTable.g:224:2: '='
            {
             before(grammarAccess.getColumnTypeAccess().getEqualsSignKeyword_0_2()); 
            match(input,14,FOLLOW_2); 
             after(grammarAccess.getColumnTypeAccess().getEqualsSignKeyword_0_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__Group_0__2__Impl"


    // $ANTLR start "rule__ColumnType__Group_0__3"
    // InternalHdbTable.g:233:1: rule__ColumnType__Group_0__3 : rule__ColumnType__Group_0__3__Impl rule__ColumnType__Group_0__4 ;
    public final void rule__ColumnType__Group_0__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:237:1: ( rule__ColumnType__Group_0__3__Impl rule__ColumnType__Group_0__4 )
            // InternalHdbTable.g:238:2: rule__ColumnType__Group_0__3__Impl rule__ColumnType__Group_0__4
            {
            pushFollow(FOLLOW_6);
            rule__ColumnType__Group_0__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ColumnType__Group_0__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__Group_0__3"


    // $ANTLR start "rule__ColumnType__Group_0__3__Impl"
    // InternalHdbTable.g:245:1: rule__ColumnType__Group_0__3__Impl : ( ( rule__ColumnType__ColumnNameAssignment_0_3 ) ) ;
    public final void rule__ColumnType__Group_0__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:249:1: ( ( ( rule__ColumnType__ColumnNameAssignment_0_3 ) ) )
            // InternalHdbTable.g:250:1: ( ( rule__ColumnType__ColumnNameAssignment_0_3 ) )
            {
            // InternalHdbTable.g:250:1: ( ( rule__ColumnType__ColumnNameAssignment_0_3 ) )
            // InternalHdbTable.g:251:2: ( rule__ColumnType__ColumnNameAssignment_0_3 )
            {
             before(grammarAccess.getColumnTypeAccess().getColumnNameAssignment_0_3()); 
            // InternalHdbTable.g:252:2: ( rule__ColumnType__ColumnNameAssignment_0_3 )
            // InternalHdbTable.g:252:3: rule__ColumnType__ColumnNameAssignment_0_3
            {
            pushFollow(FOLLOW_2);
            rule__ColumnType__ColumnNameAssignment_0_3();

            state._fsp--;


            }

             after(grammarAccess.getColumnTypeAccess().getColumnNameAssignment_0_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__Group_0__3__Impl"


    // $ANTLR start "rule__ColumnType__Group_0__4"
    // InternalHdbTable.g:260:1: rule__ColumnType__Group_0__4 : rule__ColumnType__Group_0__4__Impl ;
    public final void rule__ColumnType__Group_0__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:264:1: ( rule__ColumnType__Group_0__4__Impl )
            // InternalHdbTable.g:265:2: rule__ColumnType__Group_0__4__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ColumnType__Group_0__4__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__Group_0__4"


    // $ANTLR start "rule__ColumnType__Group_0__4__Impl"
    // InternalHdbTable.g:271:1: rule__ColumnType__Group_0__4__Impl : ( ';' ) ;
    public final void rule__ColumnType__Group_0__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:275:1: ( ( ';' ) )
            // InternalHdbTable.g:276:1: ( ';' )
            {
            // InternalHdbTable.g:276:1: ( ';' )
            // InternalHdbTable.g:277:2: ';'
            {
             before(grammarAccess.getColumnTypeAccess().getSemicolonKeyword_0_4()); 
            match(input,15,FOLLOW_2); 
             after(grammarAccess.getColumnTypeAccess().getSemicolonKeyword_0_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__Group_0__4__Impl"


    // $ANTLR start "rule__ColumnType__Group_1__0"
    // InternalHdbTable.g:287:1: rule__ColumnType__Group_1__0 : rule__ColumnType__Group_1__0__Impl rule__ColumnType__Group_1__1 ;
    public final void rule__ColumnType__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:291:1: ( rule__ColumnType__Group_1__0__Impl rule__ColumnType__Group_1__1 )
            // InternalHdbTable.g:292:2: rule__ColumnType__Group_1__0__Impl rule__ColumnType__Group_1__1
            {
            pushFollow(FOLLOW_4);
            rule__ColumnType__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ColumnType__Group_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__Group_1__0"


    // $ANTLR start "rule__ColumnType__Group_1__0__Impl"
    // InternalHdbTable.g:299:1: rule__ColumnType__Group_1__0__Impl : ( 'sqlType' ) ;
    public final void rule__ColumnType__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:303:1: ( ( 'sqlType' ) )
            // InternalHdbTable.g:304:1: ( 'sqlType' )
            {
            // InternalHdbTable.g:304:1: ( 'sqlType' )
            // InternalHdbTable.g:305:2: 'sqlType'
            {
             before(grammarAccess.getColumnTypeAccess().getSqlTypeKeyword_1_0()); 
            match(input,16,FOLLOW_2); 
             after(grammarAccess.getColumnTypeAccess().getSqlTypeKeyword_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__Group_1__0__Impl"


    // $ANTLR start "rule__ColumnType__Group_1__1"
    // InternalHdbTable.g:314:1: rule__ColumnType__Group_1__1 : rule__ColumnType__Group_1__1__Impl rule__ColumnType__Group_1__2 ;
    public final void rule__ColumnType__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:318:1: ( rule__ColumnType__Group_1__1__Impl rule__ColumnType__Group_1__2 )
            // InternalHdbTable.g:319:2: rule__ColumnType__Group_1__1__Impl rule__ColumnType__Group_1__2
            {
            pushFollow(FOLLOW_7);
            rule__ColumnType__Group_1__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ColumnType__Group_1__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__Group_1__1"


    // $ANTLR start "rule__ColumnType__Group_1__1__Impl"
    // InternalHdbTable.g:326:1: rule__ColumnType__Group_1__1__Impl : ( '=' ) ;
    public final void rule__ColumnType__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:330:1: ( ( '=' ) )
            // InternalHdbTable.g:331:1: ( '=' )
            {
            // InternalHdbTable.g:331:1: ( '=' )
            // InternalHdbTable.g:332:2: '='
            {
             before(grammarAccess.getColumnTypeAccess().getEqualsSignKeyword_1_1()); 
            match(input,14,FOLLOW_2); 
             after(grammarAccess.getColumnTypeAccess().getEqualsSignKeyword_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__Group_1__1__Impl"


    // $ANTLR start "rule__ColumnType__Group_1__2"
    // InternalHdbTable.g:341:1: rule__ColumnType__Group_1__2 : rule__ColumnType__Group_1__2__Impl rule__ColumnType__Group_1__3 ;
    public final void rule__ColumnType__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:345:1: ( rule__ColumnType__Group_1__2__Impl rule__ColumnType__Group_1__3 )
            // InternalHdbTable.g:346:2: rule__ColumnType__Group_1__2__Impl rule__ColumnType__Group_1__3
            {
            pushFollow(FOLLOW_6);
            rule__ColumnType__Group_1__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ColumnType__Group_1__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__Group_1__2"


    // $ANTLR start "rule__ColumnType__Group_1__2__Impl"
    // InternalHdbTable.g:353:1: rule__ColumnType__Group_1__2__Impl : ( ( rule__ColumnType__ColumnSqlTypeAssignment_1_2 ) ) ;
    public final void rule__ColumnType__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:357:1: ( ( ( rule__ColumnType__ColumnSqlTypeAssignment_1_2 ) ) )
            // InternalHdbTable.g:358:1: ( ( rule__ColumnType__ColumnSqlTypeAssignment_1_2 ) )
            {
            // InternalHdbTable.g:358:1: ( ( rule__ColumnType__ColumnSqlTypeAssignment_1_2 ) )
            // InternalHdbTable.g:359:2: ( rule__ColumnType__ColumnSqlTypeAssignment_1_2 )
            {
             before(grammarAccess.getColumnTypeAccess().getColumnSqlTypeAssignment_1_2()); 
            // InternalHdbTable.g:360:2: ( rule__ColumnType__ColumnSqlTypeAssignment_1_2 )
            // InternalHdbTable.g:360:3: rule__ColumnType__ColumnSqlTypeAssignment_1_2
            {
            pushFollow(FOLLOW_2);
            rule__ColumnType__ColumnSqlTypeAssignment_1_2();

            state._fsp--;


            }

             after(grammarAccess.getColumnTypeAccess().getColumnSqlTypeAssignment_1_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__Group_1__2__Impl"


    // $ANTLR start "rule__ColumnType__Group_1__3"
    // InternalHdbTable.g:368:1: rule__ColumnType__Group_1__3 : rule__ColumnType__Group_1__3__Impl ;
    public final void rule__ColumnType__Group_1__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:372:1: ( rule__ColumnType__Group_1__3__Impl )
            // InternalHdbTable.g:373:2: rule__ColumnType__Group_1__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ColumnType__Group_1__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__Group_1__3"


    // $ANTLR start "rule__ColumnType__Group_1__3__Impl"
    // InternalHdbTable.g:379:1: rule__ColumnType__Group_1__3__Impl : ( ';' ) ;
    public final void rule__ColumnType__Group_1__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:383:1: ( ( ';' ) )
            // InternalHdbTable.g:384:1: ( ';' )
            {
            // InternalHdbTable.g:384:1: ( ';' )
            // InternalHdbTable.g:385:2: ';'
            {
             before(grammarAccess.getColumnTypeAccess().getSemicolonKeyword_1_3()); 
            match(input,15,FOLLOW_2); 
             after(grammarAccess.getColumnTypeAccess().getSemicolonKeyword_1_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__Group_1__3__Impl"


    // $ANTLR start "rule__ColumnType__Group_2__0"
    // InternalHdbTable.g:395:1: rule__ColumnType__Group_2__0 : rule__ColumnType__Group_2__0__Impl rule__ColumnType__Group_2__1 ;
    public final void rule__ColumnType__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:399:1: ( rule__ColumnType__Group_2__0__Impl rule__ColumnType__Group_2__1 )
            // InternalHdbTable.g:400:2: rule__ColumnType__Group_2__0__Impl rule__ColumnType__Group_2__1
            {
            pushFollow(FOLLOW_4);
            rule__ColumnType__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ColumnType__Group_2__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__Group_2__0"


    // $ANTLR start "rule__ColumnType__Group_2__0__Impl"
    // InternalHdbTable.g:407:1: rule__ColumnType__Group_2__0__Impl : ( 'length' ) ;
    public final void rule__ColumnType__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:411:1: ( ( 'length' ) )
            // InternalHdbTable.g:412:1: ( 'length' )
            {
            // InternalHdbTable.g:412:1: ( 'length' )
            // InternalHdbTable.g:413:2: 'length'
            {
             before(grammarAccess.getColumnTypeAccess().getLengthKeyword_2_0()); 
            match(input,17,FOLLOW_2); 
             after(grammarAccess.getColumnTypeAccess().getLengthKeyword_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__Group_2__0__Impl"


    // $ANTLR start "rule__ColumnType__Group_2__1"
    // InternalHdbTable.g:422:1: rule__ColumnType__Group_2__1 : rule__ColumnType__Group_2__1__Impl rule__ColumnType__Group_2__2 ;
    public final void rule__ColumnType__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:426:1: ( rule__ColumnType__Group_2__1__Impl rule__ColumnType__Group_2__2 )
            // InternalHdbTable.g:427:2: rule__ColumnType__Group_2__1__Impl rule__ColumnType__Group_2__2
            {
            pushFollow(FOLLOW_8);
            rule__ColumnType__Group_2__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ColumnType__Group_2__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__Group_2__1"


    // $ANTLR start "rule__ColumnType__Group_2__1__Impl"
    // InternalHdbTable.g:434:1: rule__ColumnType__Group_2__1__Impl : ( '=' ) ;
    public final void rule__ColumnType__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:438:1: ( ( '=' ) )
            // InternalHdbTable.g:439:1: ( '=' )
            {
            // InternalHdbTable.g:439:1: ( '=' )
            // InternalHdbTable.g:440:2: '='
            {
             before(grammarAccess.getColumnTypeAccess().getEqualsSignKeyword_2_1()); 
            match(input,14,FOLLOW_2); 
             after(grammarAccess.getColumnTypeAccess().getEqualsSignKeyword_2_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__Group_2__1__Impl"


    // $ANTLR start "rule__ColumnType__Group_2__2"
    // InternalHdbTable.g:449:1: rule__ColumnType__Group_2__2 : rule__ColumnType__Group_2__2__Impl rule__ColumnType__Group_2__3 ;
    public final void rule__ColumnType__Group_2__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:453:1: ( rule__ColumnType__Group_2__2__Impl rule__ColumnType__Group_2__3 )
            // InternalHdbTable.g:454:2: rule__ColumnType__Group_2__2__Impl rule__ColumnType__Group_2__3
            {
            pushFollow(FOLLOW_6);
            rule__ColumnType__Group_2__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ColumnType__Group_2__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__Group_2__2"


    // $ANTLR start "rule__ColumnType__Group_2__2__Impl"
    // InternalHdbTable.g:461:1: rule__ColumnType__Group_2__2__Impl : ( ( rule__ColumnType__ColumnLengthAssignment_2_2 ) ) ;
    public final void rule__ColumnType__Group_2__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:465:1: ( ( ( rule__ColumnType__ColumnLengthAssignment_2_2 ) ) )
            // InternalHdbTable.g:466:1: ( ( rule__ColumnType__ColumnLengthAssignment_2_2 ) )
            {
            // InternalHdbTable.g:466:1: ( ( rule__ColumnType__ColumnLengthAssignment_2_2 ) )
            // InternalHdbTable.g:467:2: ( rule__ColumnType__ColumnLengthAssignment_2_2 )
            {
             before(grammarAccess.getColumnTypeAccess().getColumnLengthAssignment_2_2()); 
            // InternalHdbTable.g:468:2: ( rule__ColumnType__ColumnLengthAssignment_2_2 )
            // InternalHdbTable.g:468:3: rule__ColumnType__ColumnLengthAssignment_2_2
            {
            pushFollow(FOLLOW_2);
            rule__ColumnType__ColumnLengthAssignment_2_2();

            state._fsp--;


            }

             after(grammarAccess.getColumnTypeAccess().getColumnLengthAssignment_2_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__Group_2__2__Impl"


    // $ANTLR start "rule__ColumnType__Group_2__3"
    // InternalHdbTable.g:476:1: rule__ColumnType__Group_2__3 : rule__ColumnType__Group_2__3__Impl ;
    public final void rule__ColumnType__Group_2__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:480:1: ( rule__ColumnType__Group_2__3__Impl )
            // InternalHdbTable.g:481:2: rule__ColumnType__Group_2__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ColumnType__Group_2__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__Group_2__3"


    // $ANTLR start "rule__ColumnType__Group_2__3__Impl"
    // InternalHdbTable.g:487:1: rule__ColumnType__Group_2__3__Impl : ( ';' ) ;
    public final void rule__ColumnType__Group_2__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:491:1: ( ( ';' ) )
            // InternalHdbTable.g:492:1: ( ';' )
            {
            // InternalHdbTable.g:492:1: ( ';' )
            // InternalHdbTable.g:493:2: ';'
            {
             before(grammarAccess.getColumnTypeAccess().getSemicolonKeyword_2_3()); 
            match(input,15,FOLLOW_2); 
             after(grammarAccess.getColumnTypeAccess().getSemicolonKeyword_2_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__Group_2__3__Impl"


    // $ANTLR start "rule__ColumnType__Group_3__0"
    // InternalHdbTable.g:503:1: rule__ColumnType__Group_3__0 : rule__ColumnType__Group_3__0__Impl rule__ColumnType__Group_3__1 ;
    public final void rule__ColumnType__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:507:1: ( rule__ColumnType__Group_3__0__Impl rule__ColumnType__Group_3__1 )
            // InternalHdbTable.g:508:2: rule__ColumnType__Group_3__0__Impl rule__ColumnType__Group_3__1
            {
            pushFollow(FOLLOW_4);
            rule__ColumnType__Group_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ColumnType__Group_3__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__Group_3__0"


    // $ANTLR start "rule__ColumnType__Group_3__0__Impl"
    // InternalHdbTable.g:515:1: rule__ColumnType__Group_3__0__Impl : ( 'comment' ) ;
    public final void rule__ColumnType__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:519:1: ( ( 'comment' ) )
            // InternalHdbTable.g:520:1: ( 'comment' )
            {
            // InternalHdbTable.g:520:1: ( 'comment' )
            // InternalHdbTable.g:521:2: 'comment'
            {
             before(grammarAccess.getColumnTypeAccess().getCommentKeyword_3_0()); 
            match(input,18,FOLLOW_2); 
             after(grammarAccess.getColumnTypeAccess().getCommentKeyword_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__Group_3__0__Impl"


    // $ANTLR start "rule__ColumnType__Group_3__1"
    // InternalHdbTable.g:530:1: rule__ColumnType__Group_3__1 : rule__ColumnType__Group_3__1__Impl rule__ColumnType__Group_3__2 ;
    public final void rule__ColumnType__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:534:1: ( rule__ColumnType__Group_3__1__Impl rule__ColumnType__Group_3__2 )
            // InternalHdbTable.g:535:2: rule__ColumnType__Group_3__1__Impl rule__ColumnType__Group_3__2
            {
            pushFollow(FOLLOW_5);
            rule__ColumnType__Group_3__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ColumnType__Group_3__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__Group_3__1"


    // $ANTLR start "rule__ColumnType__Group_3__1__Impl"
    // InternalHdbTable.g:542:1: rule__ColumnType__Group_3__1__Impl : ( '=' ) ;
    public final void rule__ColumnType__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:546:1: ( ( '=' ) )
            // InternalHdbTable.g:547:1: ( '=' )
            {
            // InternalHdbTable.g:547:1: ( '=' )
            // InternalHdbTable.g:548:2: '='
            {
             before(grammarAccess.getColumnTypeAccess().getEqualsSignKeyword_3_1()); 
            match(input,14,FOLLOW_2); 
             after(grammarAccess.getColumnTypeAccess().getEqualsSignKeyword_3_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__Group_3__1__Impl"


    // $ANTLR start "rule__ColumnType__Group_3__2"
    // InternalHdbTable.g:557:1: rule__ColumnType__Group_3__2 : rule__ColumnType__Group_3__2__Impl rule__ColumnType__Group_3__3 ;
    public final void rule__ColumnType__Group_3__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:561:1: ( rule__ColumnType__Group_3__2__Impl rule__ColumnType__Group_3__3 )
            // InternalHdbTable.g:562:2: rule__ColumnType__Group_3__2__Impl rule__ColumnType__Group_3__3
            {
            pushFollow(FOLLOW_6);
            rule__ColumnType__Group_3__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ColumnType__Group_3__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__Group_3__2"


    // $ANTLR start "rule__ColumnType__Group_3__2__Impl"
    // InternalHdbTable.g:569:1: rule__ColumnType__Group_3__2__Impl : ( ( rule__ColumnType__ColumnCommentAssignment_3_2 ) ) ;
    public final void rule__ColumnType__Group_3__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:573:1: ( ( ( rule__ColumnType__ColumnCommentAssignment_3_2 ) ) )
            // InternalHdbTable.g:574:1: ( ( rule__ColumnType__ColumnCommentAssignment_3_2 ) )
            {
            // InternalHdbTable.g:574:1: ( ( rule__ColumnType__ColumnCommentAssignment_3_2 ) )
            // InternalHdbTable.g:575:2: ( rule__ColumnType__ColumnCommentAssignment_3_2 )
            {
             before(grammarAccess.getColumnTypeAccess().getColumnCommentAssignment_3_2()); 
            // InternalHdbTable.g:576:2: ( rule__ColumnType__ColumnCommentAssignment_3_2 )
            // InternalHdbTable.g:576:3: rule__ColumnType__ColumnCommentAssignment_3_2
            {
            pushFollow(FOLLOW_2);
            rule__ColumnType__ColumnCommentAssignment_3_2();

            state._fsp--;


            }

             after(grammarAccess.getColumnTypeAccess().getColumnCommentAssignment_3_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__Group_3__2__Impl"


    // $ANTLR start "rule__ColumnType__Group_3__3"
    // InternalHdbTable.g:584:1: rule__ColumnType__Group_3__3 : rule__ColumnType__Group_3__3__Impl ;
    public final void rule__ColumnType__Group_3__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:588:1: ( rule__ColumnType__Group_3__3__Impl )
            // InternalHdbTable.g:589:2: rule__ColumnType__Group_3__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ColumnType__Group_3__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__Group_3__3"


    // $ANTLR start "rule__ColumnType__Group_3__3__Impl"
    // InternalHdbTable.g:595:1: rule__ColumnType__Group_3__3__Impl : ( ';' ) ;
    public final void rule__ColumnType__Group_3__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:599:1: ( ( ';' ) )
            // InternalHdbTable.g:600:1: ( ';' )
            {
            // InternalHdbTable.g:600:1: ( ';' )
            // InternalHdbTable.g:601:2: ';'
            {
             before(grammarAccess.getColumnTypeAccess().getSemicolonKeyword_3_3()); 
            match(input,15,FOLLOW_2); 
             after(grammarAccess.getColumnTypeAccess().getSemicolonKeyword_3_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__Group_3__3__Impl"


    // $ANTLR start "rule__ColumnType__Group_4__0"
    // InternalHdbTable.g:611:1: rule__ColumnType__Group_4__0 : rule__ColumnType__Group_4__0__Impl rule__ColumnType__Group_4__1 ;
    public final void rule__ColumnType__Group_4__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:615:1: ( rule__ColumnType__Group_4__0__Impl rule__ColumnType__Group_4__1 )
            // InternalHdbTable.g:616:2: rule__ColumnType__Group_4__0__Impl rule__ColumnType__Group_4__1
            {
            pushFollow(FOLLOW_4);
            rule__ColumnType__Group_4__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ColumnType__Group_4__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__Group_4__0"


    // $ANTLR start "rule__ColumnType__Group_4__0__Impl"
    // InternalHdbTable.g:623:1: rule__ColumnType__Group_4__0__Impl : ( 'defaultValue' ) ;
    public final void rule__ColumnType__Group_4__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:627:1: ( ( 'defaultValue' ) )
            // InternalHdbTable.g:628:1: ( 'defaultValue' )
            {
            // InternalHdbTable.g:628:1: ( 'defaultValue' )
            // InternalHdbTable.g:629:2: 'defaultValue'
            {
             before(grammarAccess.getColumnTypeAccess().getDefaultValueKeyword_4_0()); 
            match(input,19,FOLLOW_2); 
             after(grammarAccess.getColumnTypeAccess().getDefaultValueKeyword_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__Group_4__0__Impl"


    // $ANTLR start "rule__ColumnType__Group_4__1"
    // InternalHdbTable.g:638:1: rule__ColumnType__Group_4__1 : rule__ColumnType__Group_4__1__Impl rule__ColumnType__Group_4__2 ;
    public final void rule__ColumnType__Group_4__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:642:1: ( rule__ColumnType__Group_4__1__Impl rule__ColumnType__Group_4__2 )
            // InternalHdbTable.g:643:2: rule__ColumnType__Group_4__1__Impl rule__ColumnType__Group_4__2
            {
            pushFollow(FOLLOW_5);
            rule__ColumnType__Group_4__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ColumnType__Group_4__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__Group_4__1"


    // $ANTLR start "rule__ColumnType__Group_4__1__Impl"
    // InternalHdbTable.g:650:1: rule__ColumnType__Group_4__1__Impl : ( '=' ) ;
    public final void rule__ColumnType__Group_4__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:654:1: ( ( '=' ) )
            // InternalHdbTable.g:655:1: ( '=' )
            {
            // InternalHdbTable.g:655:1: ( '=' )
            // InternalHdbTable.g:656:2: '='
            {
             before(grammarAccess.getColumnTypeAccess().getEqualsSignKeyword_4_1()); 
            match(input,14,FOLLOW_2); 
             after(grammarAccess.getColumnTypeAccess().getEqualsSignKeyword_4_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__Group_4__1__Impl"


    // $ANTLR start "rule__ColumnType__Group_4__2"
    // InternalHdbTable.g:665:1: rule__ColumnType__Group_4__2 : rule__ColumnType__Group_4__2__Impl rule__ColumnType__Group_4__3 ;
    public final void rule__ColumnType__Group_4__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:669:1: ( rule__ColumnType__Group_4__2__Impl rule__ColumnType__Group_4__3 )
            // InternalHdbTable.g:670:2: rule__ColumnType__Group_4__2__Impl rule__ColumnType__Group_4__3
            {
            pushFollow(FOLLOW_6);
            rule__ColumnType__Group_4__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ColumnType__Group_4__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__Group_4__2"


    // $ANTLR start "rule__ColumnType__Group_4__2__Impl"
    // InternalHdbTable.g:677:1: rule__ColumnType__Group_4__2__Impl : ( ( rule__ColumnType__ColumnDefaultValueAssignment_4_2 ) ) ;
    public final void rule__ColumnType__Group_4__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:681:1: ( ( ( rule__ColumnType__ColumnDefaultValueAssignment_4_2 ) ) )
            // InternalHdbTable.g:682:1: ( ( rule__ColumnType__ColumnDefaultValueAssignment_4_2 ) )
            {
            // InternalHdbTable.g:682:1: ( ( rule__ColumnType__ColumnDefaultValueAssignment_4_2 ) )
            // InternalHdbTable.g:683:2: ( rule__ColumnType__ColumnDefaultValueAssignment_4_2 )
            {
             before(grammarAccess.getColumnTypeAccess().getColumnDefaultValueAssignment_4_2()); 
            // InternalHdbTable.g:684:2: ( rule__ColumnType__ColumnDefaultValueAssignment_4_2 )
            // InternalHdbTable.g:684:3: rule__ColumnType__ColumnDefaultValueAssignment_4_2
            {
            pushFollow(FOLLOW_2);
            rule__ColumnType__ColumnDefaultValueAssignment_4_2();

            state._fsp--;


            }

             after(grammarAccess.getColumnTypeAccess().getColumnDefaultValueAssignment_4_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__Group_4__2__Impl"


    // $ANTLR start "rule__ColumnType__Group_4__3"
    // InternalHdbTable.g:692:1: rule__ColumnType__Group_4__3 : rule__ColumnType__Group_4__3__Impl ;
    public final void rule__ColumnType__Group_4__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:696:1: ( rule__ColumnType__Group_4__3__Impl )
            // InternalHdbTable.g:697:2: rule__ColumnType__Group_4__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ColumnType__Group_4__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__Group_4__3"


    // $ANTLR start "rule__ColumnType__Group_4__3__Impl"
    // InternalHdbTable.g:703:1: rule__ColumnType__Group_4__3__Impl : ( ';' ) ;
    public final void rule__ColumnType__Group_4__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:707:1: ( ( ';' ) )
            // InternalHdbTable.g:708:1: ( ';' )
            {
            // InternalHdbTable.g:708:1: ( ';' )
            // InternalHdbTable.g:709:2: ';'
            {
             before(grammarAccess.getColumnTypeAccess().getSemicolonKeyword_4_3()); 
            match(input,15,FOLLOW_2); 
             after(grammarAccess.getColumnTypeAccess().getSemicolonKeyword_4_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__Group_4__3__Impl"


    // $ANTLR start "rule__ColumnType__Group_5__0"
    // InternalHdbTable.g:719:1: rule__ColumnType__Group_5__0 : rule__ColumnType__Group_5__0__Impl rule__ColumnType__Group_5__1 ;
    public final void rule__ColumnType__Group_5__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:723:1: ( rule__ColumnType__Group_5__0__Impl rule__ColumnType__Group_5__1 )
            // InternalHdbTable.g:724:2: rule__ColumnType__Group_5__0__Impl rule__ColumnType__Group_5__1
            {
            pushFollow(FOLLOW_4);
            rule__ColumnType__Group_5__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ColumnType__Group_5__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__Group_5__0"


    // $ANTLR start "rule__ColumnType__Group_5__0__Impl"
    // InternalHdbTable.g:731:1: rule__ColumnType__Group_5__0__Impl : ( 'precision' ) ;
    public final void rule__ColumnType__Group_5__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:735:1: ( ( 'precision' ) )
            // InternalHdbTable.g:736:1: ( 'precision' )
            {
            // InternalHdbTable.g:736:1: ( 'precision' )
            // InternalHdbTable.g:737:2: 'precision'
            {
             before(grammarAccess.getColumnTypeAccess().getPrecisionKeyword_5_0()); 
            match(input,20,FOLLOW_2); 
             after(grammarAccess.getColumnTypeAccess().getPrecisionKeyword_5_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__Group_5__0__Impl"


    // $ANTLR start "rule__ColumnType__Group_5__1"
    // InternalHdbTable.g:746:1: rule__ColumnType__Group_5__1 : rule__ColumnType__Group_5__1__Impl rule__ColumnType__Group_5__2 ;
    public final void rule__ColumnType__Group_5__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:750:1: ( rule__ColumnType__Group_5__1__Impl rule__ColumnType__Group_5__2 )
            // InternalHdbTable.g:751:2: rule__ColumnType__Group_5__1__Impl rule__ColumnType__Group_5__2
            {
            pushFollow(FOLLOW_8);
            rule__ColumnType__Group_5__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ColumnType__Group_5__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__Group_5__1"


    // $ANTLR start "rule__ColumnType__Group_5__1__Impl"
    // InternalHdbTable.g:758:1: rule__ColumnType__Group_5__1__Impl : ( '=' ) ;
    public final void rule__ColumnType__Group_5__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:762:1: ( ( '=' ) )
            // InternalHdbTable.g:763:1: ( '=' )
            {
            // InternalHdbTable.g:763:1: ( '=' )
            // InternalHdbTable.g:764:2: '='
            {
             before(grammarAccess.getColumnTypeAccess().getEqualsSignKeyword_5_1()); 
            match(input,14,FOLLOW_2); 
             after(grammarAccess.getColumnTypeAccess().getEqualsSignKeyword_5_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__Group_5__1__Impl"


    // $ANTLR start "rule__ColumnType__Group_5__2"
    // InternalHdbTable.g:773:1: rule__ColumnType__Group_5__2 : rule__ColumnType__Group_5__2__Impl rule__ColumnType__Group_5__3 ;
    public final void rule__ColumnType__Group_5__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:777:1: ( rule__ColumnType__Group_5__2__Impl rule__ColumnType__Group_5__3 )
            // InternalHdbTable.g:778:2: rule__ColumnType__Group_5__2__Impl rule__ColumnType__Group_5__3
            {
            pushFollow(FOLLOW_6);
            rule__ColumnType__Group_5__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ColumnType__Group_5__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__Group_5__2"


    // $ANTLR start "rule__ColumnType__Group_5__2__Impl"
    // InternalHdbTable.g:785:1: rule__ColumnType__Group_5__2__Impl : ( ( rule__ColumnType__ColumnPrecisionAssignment_5_2 ) ) ;
    public final void rule__ColumnType__Group_5__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:789:1: ( ( ( rule__ColumnType__ColumnPrecisionAssignment_5_2 ) ) )
            // InternalHdbTable.g:790:1: ( ( rule__ColumnType__ColumnPrecisionAssignment_5_2 ) )
            {
            // InternalHdbTable.g:790:1: ( ( rule__ColumnType__ColumnPrecisionAssignment_5_2 ) )
            // InternalHdbTable.g:791:2: ( rule__ColumnType__ColumnPrecisionAssignment_5_2 )
            {
             before(grammarAccess.getColumnTypeAccess().getColumnPrecisionAssignment_5_2()); 
            // InternalHdbTable.g:792:2: ( rule__ColumnType__ColumnPrecisionAssignment_5_2 )
            // InternalHdbTable.g:792:3: rule__ColumnType__ColumnPrecisionAssignment_5_2
            {
            pushFollow(FOLLOW_2);
            rule__ColumnType__ColumnPrecisionAssignment_5_2();

            state._fsp--;


            }

             after(grammarAccess.getColumnTypeAccess().getColumnPrecisionAssignment_5_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__Group_5__2__Impl"


    // $ANTLR start "rule__ColumnType__Group_5__3"
    // InternalHdbTable.g:800:1: rule__ColumnType__Group_5__3 : rule__ColumnType__Group_5__3__Impl ;
    public final void rule__ColumnType__Group_5__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:804:1: ( rule__ColumnType__Group_5__3__Impl )
            // InternalHdbTable.g:805:2: rule__ColumnType__Group_5__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ColumnType__Group_5__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__Group_5__3"


    // $ANTLR start "rule__ColumnType__Group_5__3__Impl"
    // InternalHdbTable.g:811:1: rule__ColumnType__Group_5__3__Impl : ( ';' ) ;
    public final void rule__ColumnType__Group_5__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:815:1: ( ( ';' ) )
            // InternalHdbTable.g:816:1: ( ';' )
            {
            // InternalHdbTable.g:816:1: ( ';' )
            // InternalHdbTable.g:817:2: ';'
            {
             before(grammarAccess.getColumnTypeAccess().getSemicolonKeyword_5_3()); 
            match(input,15,FOLLOW_2); 
             after(grammarAccess.getColumnTypeAccess().getSemicolonKeyword_5_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__Group_5__3__Impl"


    // $ANTLR start "rule__ColumnType__Group_6__0"
    // InternalHdbTable.g:827:1: rule__ColumnType__Group_6__0 : rule__ColumnType__Group_6__0__Impl rule__ColumnType__Group_6__1 ;
    public final void rule__ColumnType__Group_6__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:831:1: ( rule__ColumnType__Group_6__0__Impl rule__ColumnType__Group_6__1 )
            // InternalHdbTable.g:832:2: rule__ColumnType__Group_6__0__Impl rule__ColumnType__Group_6__1
            {
            pushFollow(FOLLOW_4);
            rule__ColumnType__Group_6__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ColumnType__Group_6__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__Group_6__0"


    // $ANTLR start "rule__ColumnType__Group_6__0__Impl"
    // InternalHdbTable.g:839:1: rule__ColumnType__Group_6__0__Impl : ( 'scale' ) ;
    public final void rule__ColumnType__Group_6__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:843:1: ( ( 'scale' ) )
            // InternalHdbTable.g:844:1: ( 'scale' )
            {
            // InternalHdbTable.g:844:1: ( 'scale' )
            // InternalHdbTable.g:845:2: 'scale'
            {
             before(grammarAccess.getColumnTypeAccess().getScaleKeyword_6_0()); 
            match(input,21,FOLLOW_2); 
             after(grammarAccess.getColumnTypeAccess().getScaleKeyword_6_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__Group_6__0__Impl"


    // $ANTLR start "rule__ColumnType__Group_6__1"
    // InternalHdbTable.g:854:1: rule__ColumnType__Group_6__1 : rule__ColumnType__Group_6__1__Impl rule__ColumnType__Group_6__2 ;
    public final void rule__ColumnType__Group_6__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:858:1: ( rule__ColumnType__Group_6__1__Impl rule__ColumnType__Group_6__2 )
            // InternalHdbTable.g:859:2: rule__ColumnType__Group_6__1__Impl rule__ColumnType__Group_6__2
            {
            pushFollow(FOLLOW_8);
            rule__ColumnType__Group_6__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ColumnType__Group_6__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__Group_6__1"


    // $ANTLR start "rule__ColumnType__Group_6__1__Impl"
    // InternalHdbTable.g:866:1: rule__ColumnType__Group_6__1__Impl : ( '=' ) ;
    public final void rule__ColumnType__Group_6__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:870:1: ( ( '=' ) )
            // InternalHdbTable.g:871:1: ( '=' )
            {
            // InternalHdbTable.g:871:1: ( '=' )
            // InternalHdbTable.g:872:2: '='
            {
             before(grammarAccess.getColumnTypeAccess().getEqualsSignKeyword_6_1()); 
            match(input,14,FOLLOW_2); 
             after(grammarAccess.getColumnTypeAccess().getEqualsSignKeyword_6_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__Group_6__1__Impl"


    // $ANTLR start "rule__ColumnType__Group_6__2"
    // InternalHdbTable.g:881:1: rule__ColumnType__Group_6__2 : rule__ColumnType__Group_6__2__Impl rule__ColumnType__Group_6__3 ;
    public final void rule__ColumnType__Group_6__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:885:1: ( rule__ColumnType__Group_6__2__Impl rule__ColumnType__Group_6__3 )
            // InternalHdbTable.g:886:2: rule__ColumnType__Group_6__2__Impl rule__ColumnType__Group_6__3
            {
            pushFollow(FOLLOW_6);
            rule__ColumnType__Group_6__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ColumnType__Group_6__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__Group_6__2"


    // $ANTLR start "rule__ColumnType__Group_6__2__Impl"
    // InternalHdbTable.g:893:1: rule__ColumnType__Group_6__2__Impl : ( ( rule__ColumnType__ColumnScaleAssignment_6_2 ) ) ;
    public final void rule__ColumnType__Group_6__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:897:1: ( ( ( rule__ColumnType__ColumnScaleAssignment_6_2 ) ) )
            // InternalHdbTable.g:898:1: ( ( rule__ColumnType__ColumnScaleAssignment_6_2 ) )
            {
            // InternalHdbTable.g:898:1: ( ( rule__ColumnType__ColumnScaleAssignment_6_2 ) )
            // InternalHdbTable.g:899:2: ( rule__ColumnType__ColumnScaleAssignment_6_2 )
            {
             before(grammarAccess.getColumnTypeAccess().getColumnScaleAssignment_6_2()); 
            // InternalHdbTable.g:900:2: ( rule__ColumnType__ColumnScaleAssignment_6_2 )
            // InternalHdbTable.g:900:3: rule__ColumnType__ColumnScaleAssignment_6_2
            {
            pushFollow(FOLLOW_2);
            rule__ColumnType__ColumnScaleAssignment_6_2();

            state._fsp--;


            }

             after(grammarAccess.getColumnTypeAccess().getColumnScaleAssignment_6_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__Group_6__2__Impl"


    // $ANTLR start "rule__ColumnType__Group_6__3"
    // InternalHdbTable.g:908:1: rule__ColumnType__Group_6__3 : rule__ColumnType__Group_6__3__Impl ;
    public final void rule__ColumnType__Group_6__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:912:1: ( rule__ColumnType__Group_6__3__Impl )
            // InternalHdbTable.g:913:2: rule__ColumnType__Group_6__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ColumnType__Group_6__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__Group_6__3"


    // $ANTLR start "rule__ColumnType__Group_6__3__Impl"
    // InternalHdbTable.g:919:1: rule__ColumnType__Group_6__3__Impl : ( ';' ) ;
    public final void rule__ColumnType__Group_6__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:923:1: ( ( ';' ) )
            // InternalHdbTable.g:924:1: ( ';' )
            {
            // InternalHdbTable.g:924:1: ( ';' )
            // InternalHdbTable.g:925:2: ';'
            {
             before(grammarAccess.getColumnTypeAccess().getSemicolonKeyword_6_3()); 
            match(input,15,FOLLOW_2); 
             after(grammarAccess.getColumnTypeAccess().getSemicolonKeyword_6_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__Group_6__3__Impl"


    // $ANTLR start "rule__ColumnType__Group_7__0"
    // InternalHdbTable.g:935:1: rule__ColumnType__Group_7__0 : rule__ColumnType__Group_7__0__Impl rule__ColumnType__Group_7__1 ;
    public final void rule__ColumnType__Group_7__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:939:1: ( rule__ColumnType__Group_7__0__Impl rule__ColumnType__Group_7__1 )
            // InternalHdbTable.g:940:2: rule__ColumnType__Group_7__0__Impl rule__ColumnType__Group_7__1
            {
            pushFollow(FOLLOW_6);
            rule__ColumnType__Group_7__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ColumnType__Group_7__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__Group_7__0"


    // $ANTLR start "rule__ColumnType__Group_7__0__Impl"
    // InternalHdbTable.g:947:1: rule__ColumnType__Group_7__0__Impl : ( ( rule__ColumnType__Group_7_0__0 )? ) ;
    public final void rule__ColumnType__Group_7__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:951:1: ( ( ( rule__ColumnType__Group_7_0__0 )? ) )
            // InternalHdbTable.g:952:1: ( ( rule__ColumnType__Group_7_0__0 )? )
            {
            // InternalHdbTable.g:952:1: ( ( rule__ColumnType__Group_7_0__0 )? )
            // InternalHdbTable.g:953:2: ( rule__ColumnType__Group_7_0__0 )?
            {
             before(grammarAccess.getColumnTypeAccess().getGroup_7_0()); 
            // InternalHdbTable.g:954:2: ( rule__ColumnType__Group_7_0__0 )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==23) ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // InternalHdbTable.g:954:3: rule__ColumnType__Group_7_0__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ColumnType__Group_7_0__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getColumnTypeAccess().getGroup_7_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__Group_7__0__Impl"


    // $ANTLR start "rule__ColumnType__Group_7__1"
    // InternalHdbTable.g:962:1: rule__ColumnType__Group_7__1 : rule__ColumnType__Group_7__1__Impl rule__ColumnType__Group_7__2 ;
    public final void rule__ColumnType__Group_7__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:966:1: ( rule__ColumnType__Group_7__1__Impl rule__ColumnType__Group_7__2 )
            // InternalHdbTable.g:967:2: rule__ColumnType__Group_7__1__Impl rule__ColumnType__Group_7__2
            {
            pushFollow(FOLLOW_9);
            rule__ColumnType__Group_7__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ColumnType__Group_7__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__Group_7__1"


    // $ANTLR start "rule__ColumnType__Group_7__1__Impl"
    // InternalHdbTable.g:974:1: rule__ColumnType__Group_7__1__Impl : ( ';' ) ;
    public final void rule__ColumnType__Group_7__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:978:1: ( ( ';' ) )
            // InternalHdbTable.g:979:1: ( ';' )
            {
            // InternalHdbTable.g:979:1: ( ';' )
            // InternalHdbTable.g:980:2: ';'
            {
             before(grammarAccess.getColumnTypeAccess().getSemicolonKeyword_7_1()); 
            match(input,15,FOLLOW_2); 
             after(grammarAccess.getColumnTypeAccess().getSemicolonKeyword_7_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__Group_7__1__Impl"


    // $ANTLR start "rule__ColumnType__Group_7__2"
    // InternalHdbTable.g:989:1: rule__ColumnType__Group_7__2 : rule__ColumnType__Group_7__2__Impl ;
    public final void rule__ColumnType__Group_7__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:993:1: ( rule__ColumnType__Group_7__2__Impl )
            // InternalHdbTable.g:994:2: rule__ColumnType__Group_7__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ColumnType__Group_7__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__Group_7__2"


    // $ANTLR start "rule__ColumnType__Group_7__2__Impl"
    // InternalHdbTable.g:1000:1: rule__ColumnType__Group_7__2__Impl : ( '}' ) ;
    public final void rule__ColumnType__Group_7__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:1004:1: ( ( '}' ) )
            // InternalHdbTable.g:1005:1: ( '}' )
            {
            // InternalHdbTable.g:1005:1: ( '}' )
            // InternalHdbTable.g:1006:2: '}'
            {
             before(grammarAccess.getColumnTypeAccess().getRightCurlyBracketKeyword_7_2()); 
            match(input,22,FOLLOW_2); 
             after(grammarAccess.getColumnTypeAccess().getRightCurlyBracketKeyword_7_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__Group_7__2__Impl"


    // $ANTLR start "rule__ColumnType__Group_7_0__0"
    // InternalHdbTable.g:1016:1: rule__ColumnType__Group_7_0__0 : rule__ColumnType__Group_7_0__0__Impl rule__ColumnType__Group_7_0__1 ;
    public final void rule__ColumnType__Group_7_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:1020:1: ( rule__ColumnType__Group_7_0__0__Impl rule__ColumnType__Group_7_0__1 )
            // InternalHdbTable.g:1021:2: rule__ColumnType__Group_7_0__0__Impl rule__ColumnType__Group_7_0__1
            {
            pushFollow(FOLLOW_4);
            rule__ColumnType__Group_7_0__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ColumnType__Group_7_0__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__Group_7_0__0"


    // $ANTLR start "rule__ColumnType__Group_7_0__0__Impl"
    // InternalHdbTable.g:1028:1: rule__ColumnType__Group_7_0__0__Impl : ( 'nullable' ) ;
    public final void rule__ColumnType__Group_7_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:1032:1: ( ( 'nullable' ) )
            // InternalHdbTable.g:1033:1: ( 'nullable' )
            {
            // InternalHdbTable.g:1033:1: ( 'nullable' )
            // InternalHdbTable.g:1034:2: 'nullable'
            {
             before(grammarAccess.getColumnTypeAccess().getNullableKeyword_7_0_0()); 
            match(input,23,FOLLOW_2); 
             after(grammarAccess.getColumnTypeAccess().getNullableKeyword_7_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__Group_7_0__0__Impl"


    // $ANTLR start "rule__ColumnType__Group_7_0__1"
    // InternalHdbTable.g:1043:1: rule__ColumnType__Group_7_0__1 : rule__ColumnType__Group_7_0__1__Impl rule__ColumnType__Group_7_0__2 ;
    public final void rule__ColumnType__Group_7_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:1047:1: ( rule__ColumnType__Group_7_0__1__Impl rule__ColumnType__Group_7_0__2 )
            // InternalHdbTable.g:1048:2: rule__ColumnType__Group_7_0__1__Impl rule__ColumnType__Group_7_0__2
            {
            pushFollow(FOLLOW_10);
            rule__ColumnType__Group_7_0__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ColumnType__Group_7_0__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__Group_7_0__1"


    // $ANTLR start "rule__ColumnType__Group_7_0__1__Impl"
    // InternalHdbTable.g:1055:1: rule__ColumnType__Group_7_0__1__Impl : ( '=' ) ;
    public final void rule__ColumnType__Group_7_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:1059:1: ( ( '=' ) )
            // InternalHdbTable.g:1060:1: ( '=' )
            {
            // InternalHdbTable.g:1060:1: ( '=' )
            // InternalHdbTable.g:1061:2: '='
            {
             before(grammarAccess.getColumnTypeAccess().getEqualsSignKeyword_7_0_1()); 
            match(input,14,FOLLOW_2); 
             after(grammarAccess.getColumnTypeAccess().getEqualsSignKeyword_7_0_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__Group_7_0__1__Impl"


    // $ANTLR start "rule__ColumnType__Group_7_0__2"
    // InternalHdbTable.g:1070:1: rule__ColumnType__Group_7_0__2 : rule__ColumnType__Group_7_0__2__Impl ;
    public final void rule__ColumnType__Group_7_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:1074:1: ( rule__ColumnType__Group_7_0__2__Impl )
            // InternalHdbTable.g:1075:2: rule__ColumnType__Group_7_0__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ColumnType__Group_7_0__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__Group_7_0__2"


    // $ANTLR start "rule__ColumnType__Group_7_0__2__Impl"
    // InternalHdbTable.g:1081:1: rule__ColumnType__Group_7_0__2__Impl : ( ( rule__ColumnType__ColumnNullableAssignment_7_0_2 ) ) ;
    public final void rule__ColumnType__Group_7_0__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:1085:1: ( ( ( rule__ColumnType__ColumnNullableAssignment_7_0_2 ) ) )
            // InternalHdbTable.g:1086:1: ( ( rule__ColumnType__ColumnNullableAssignment_7_0_2 ) )
            {
            // InternalHdbTable.g:1086:1: ( ( rule__ColumnType__ColumnNullableAssignment_7_0_2 ) )
            // InternalHdbTable.g:1087:2: ( rule__ColumnType__ColumnNullableAssignment_7_0_2 )
            {
             before(grammarAccess.getColumnTypeAccess().getColumnNullableAssignment_7_0_2()); 
            // InternalHdbTable.g:1088:2: ( rule__ColumnType__ColumnNullableAssignment_7_0_2 )
            // InternalHdbTable.g:1088:3: rule__ColumnType__ColumnNullableAssignment_7_0_2
            {
            pushFollow(FOLLOW_2);
            rule__ColumnType__ColumnNullableAssignment_7_0_2();

            state._fsp--;


            }

             after(grammarAccess.getColumnTypeAccess().getColumnNullableAssignment_7_0_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__Group_7_0__2__Impl"


    // $ANTLR start "rule__IndexType__Group_0__0"
    // InternalHdbTable.g:1097:1: rule__IndexType__Group_0__0 : rule__IndexType__Group_0__0__Impl rule__IndexType__Group_0__1 ;
    public final void rule__IndexType__Group_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:1101:1: ( rule__IndexType__Group_0__0__Impl rule__IndexType__Group_0__1 )
            // InternalHdbTable.g:1102:2: rule__IndexType__Group_0__0__Impl rule__IndexType__Group_0__1
            {
            pushFollow(FOLLOW_3);
            rule__IndexType__Group_0__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__IndexType__Group_0__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IndexType__Group_0__0"


    // $ANTLR start "rule__IndexType__Group_0__0__Impl"
    // InternalHdbTable.g:1109:1: rule__IndexType__Group_0__0__Impl : ( '{' ) ;
    public final void rule__IndexType__Group_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:1113:1: ( ( '{' ) )
            // InternalHdbTable.g:1114:1: ( '{' )
            {
            // InternalHdbTable.g:1114:1: ( '{' )
            // InternalHdbTable.g:1115:2: '{'
            {
             before(grammarAccess.getIndexTypeAccess().getLeftCurlyBracketKeyword_0_0()); 
            match(input,12,FOLLOW_2); 
             after(grammarAccess.getIndexTypeAccess().getLeftCurlyBracketKeyword_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IndexType__Group_0__0__Impl"


    // $ANTLR start "rule__IndexType__Group_0__1"
    // InternalHdbTable.g:1124:1: rule__IndexType__Group_0__1 : rule__IndexType__Group_0__1__Impl rule__IndexType__Group_0__2 ;
    public final void rule__IndexType__Group_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:1128:1: ( rule__IndexType__Group_0__1__Impl rule__IndexType__Group_0__2 )
            // InternalHdbTable.g:1129:2: rule__IndexType__Group_0__1__Impl rule__IndexType__Group_0__2
            {
            pushFollow(FOLLOW_4);
            rule__IndexType__Group_0__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__IndexType__Group_0__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IndexType__Group_0__1"


    // $ANTLR start "rule__IndexType__Group_0__1__Impl"
    // InternalHdbTable.g:1136:1: rule__IndexType__Group_0__1__Impl : ( 'name' ) ;
    public final void rule__IndexType__Group_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:1140:1: ( ( 'name' ) )
            // InternalHdbTable.g:1141:1: ( 'name' )
            {
            // InternalHdbTable.g:1141:1: ( 'name' )
            // InternalHdbTable.g:1142:2: 'name'
            {
             before(grammarAccess.getIndexTypeAccess().getNameKeyword_0_1()); 
            match(input,13,FOLLOW_2); 
             after(grammarAccess.getIndexTypeAccess().getNameKeyword_0_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IndexType__Group_0__1__Impl"


    // $ANTLR start "rule__IndexType__Group_0__2"
    // InternalHdbTable.g:1151:1: rule__IndexType__Group_0__2 : rule__IndexType__Group_0__2__Impl rule__IndexType__Group_0__3 ;
    public final void rule__IndexType__Group_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:1155:1: ( rule__IndexType__Group_0__2__Impl rule__IndexType__Group_0__3 )
            // InternalHdbTable.g:1156:2: rule__IndexType__Group_0__2__Impl rule__IndexType__Group_0__3
            {
            pushFollow(FOLLOW_5);
            rule__IndexType__Group_0__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__IndexType__Group_0__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IndexType__Group_0__2"


    // $ANTLR start "rule__IndexType__Group_0__2__Impl"
    // InternalHdbTable.g:1163:1: rule__IndexType__Group_0__2__Impl : ( '=' ) ;
    public final void rule__IndexType__Group_0__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:1167:1: ( ( '=' ) )
            // InternalHdbTable.g:1168:1: ( '=' )
            {
            // InternalHdbTable.g:1168:1: ( '=' )
            // InternalHdbTable.g:1169:2: '='
            {
             before(grammarAccess.getIndexTypeAccess().getEqualsSignKeyword_0_2()); 
            match(input,14,FOLLOW_2); 
             after(grammarAccess.getIndexTypeAccess().getEqualsSignKeyword_0_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IndexType__Group_0__2__Impl"


    // $ANTLR start "rule__IndexType__Group_0__3"
    // InternalHdbTable.g:1178:1: rule__IndexType__Group_0__3 : rule__IndexType__Group_0__3__Impl rule__IndexType__Group_0__4 ;
    public final void rule__IndexType__Group_0__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:1182:1: ( rule__IndexType__Group_0__3__Impl rule__IndexType__Group_0__4 )
            // InternalHdbTable.g:1183:2: rule__IndexType__Group_0__3__Impl rule__IndexType__Group_0__4
            {
            pushFollow(FOLLOW_6);
            rule__IndexType__Group_0__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__IndexType__Group_0__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IndexType__Group_0__3"


    // $ANTLR start "rule__IndexType__Group_0__3__Impl"
    // InternalHdbTable.g:1190:1: rule__IndexType__Group_0__3__Impl : ( ( rule__IndexType__ColumnNameAssignment_0_3 ) ) ;
    public final void rule__IndexType__Group_0__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:1194:1: ( ( ( rule__IndexType__ColumnNameAssignment_0_3 ) ) )
            // InternalHdbTable.g:1195:1: ( ( rule__IndexType__ColumnNameAssignment_0_3 ) )
            {
            // InternalHdbTable.g:1195:1: ( ( rule__IndexType__ColumnNameAssignment_0_3 ) )
            // InternalHdbTable.g:1196:2: ( rule__IndexType__ColumnNameAssignment_0_3 )
            {
             before(grammarAccess.getIndexTypeAccess().getColumnNameAssignment_0_3()); 
            // InternalHdbTable.g:1197:2: ( rule__IndexType__ColumnNameAssignment_0_3 )
            // InternalHdbTable.g:1197:3: rule__IndexType__ColumnNameAssignment_0_3
            {
            pushFollow(FOLLOW_2);
            rule__IndexType__ColumnNameAssignment_0_3();

            state._fsp--;


            }

             after(grammarAccess.getIndexTypeAccess().getColumnNameAssignment_0_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IndexType__Group_0__3__Impl"


    // $ANTLR start "rule__IndexType__Group_0__4"
    // InternalHdbTable.g:1205:1: rule__IndexType__Group_0__4 : rule__IndexType__Group_0__4__Impl ;
    public final void rule__IndexType__Group_0__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:1209:1: ( rule__IndexType__Group_0__4__Impl )
            // InternalHdbTable.g:1210:2: rule__IndexType__Group_0__4__Impl
            {
            pushFollow(FOLLOW_2);
            rule__IndexType__Group_0__4__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IndexType__Group_0__4"


    // $ANTLR start "rule__IndexType__Group_0__4__Impl"
    // InternalHdbTable.g:1216:1: rule__IndexType__Group_0__4__Impl : ( ';' ) ;
    public final void rule__IndexType__Group_0__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:1220:1: ( ( ';' ) )
            // InternalHdbTable.g:1221:1: ( ';' )
            {
            // InternalHdbTable.g:1221:1: ( ';' )
            // InternalHdbTable.g:1222:2: ';'
            {
             before(grammarAccess.getIndexTypeAccess().getSemicolonKeyword_0_4()); 
            match(input,15,FOLLOW_2); 
             after(grammarAccess.getIndexTypeAccess().getSemicolonKeyword_0_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IndexType__Group_0__4__Impl"


    // $ANTLR start "rule__IndexType__Group_1__0"
    // InternalHdbTable.g:1232:1: rule__IndexType__Group_1__0 : rule__IndexType__Group_1__0__Impl rule__IndexType__Group_1__1 ;
    public final void rule__IndexType__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:1236:1: ( rule__IndexType__Group_1__0__Impl rule__IndexType__Group_1__1 )
            // InternalHdbTable.g:1237:2: rule__IndexType__Group_1__0__Impl rule__IndexType__Group_1__1
            {
            pushFollow(FOLLOW_4);
            rule__IndexType__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__IndexType__Group_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IndexType__Group_1__0"


    // $ANTLR start "rule__IndexType__Group_1__0__Impl"
    // InternalHdbTable.g:1244:1: rule__IndexType__Group_1__0__Impl : ( 'unique' ) ;
    public final void rule__IndexType__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:1248:1: ( ( 'unique' ) )
            // InternalHdbTable.g:1249:1: ( 'unique' )
            {
            // InternalHdbTable.g:1249:1: ( 'unique' )
            // InternalHdbTable.g:1250:2: 'unique'
            {
             before(grammarAccess.getIndexTypeAccess().getUniqueKeyword_1_0()); 
            match(input,24,FOLLOW_2); 
             after(grammarAccess.getIndexTypeAccess().getUniqueKeyword_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IndexType__Group_1__0__Impl"


    // $ANTLR start "rule__IndexType__Group_1__1"
    // InternalHdbTable.g:1259:1: rule__IndexType__Group_1__1 : rule__IndexType__Group_1__1__Impl rule__IndexType__Group_1__2 ;
    public final void rule__IndexType__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:1263:1: ( rule__IndexType__Group_1__1__Impl rule__IndexType__Group_1__2 )
            // InternalHdbTable.g:1264:2: rule__IndexType__Group_1__1__Impl rule__IndexType__Group_1__2
            {
            pushFollow(FOLLOW_10);
            rule__IndexType__Group_1__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__IndexType__Group_1__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IndexType__Group_1__1"


    // $ANTLR start "rule__IndexType__Group_1__1__Impl"
    // InternalHdbTable.g:1271:1: rule__IndexType__Group_1__1__Impl : ( '=' ) ;
    public final void rule__IndexType__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:1275:1: ( ( '=' ) )
            // InternalHdbTable.g:1276:1: ( '=' )
            {
            // InternalHdbTable.g:1276:1: ( '=' )
            // InternalHdbTable.g:1277:2: '='
            {
             before(grammarAccess.getIndexTypeAccess().getEqualsSignKeyword_1_1()); 
            match(input,14,FOLLOW_2); 
             after(grammarAccess.getIndexTypeAccess().getEqualsSignKeyword_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IndexType__Group_1__1__Impl"


    // $ANTLR start "rule__IndexType__Group_1__2"
    // InternalHdbTable.g:1286:1: rule__IndexType__Group_1__2 : rule__IndexType__Group_1__2__Impl rule__IndexType__Group_1__3 ;
    public final void rule__IndexType__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:1290:1: ( rule__IndexType__Group_1__2__Impl rule__IndexType__Group_1__3 )
            // InternalHdbTable.g:1291:2: rule__IndexType__Group_1__2__Impl rule__IndexType__Group_1__3
            {
            pushFollow(FOLLOW_6);
            rule__IndexType__Group_1__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__IndexType__Group_1__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IndexType__Group_1__2"


    // $ANTLR start "rule__IndexType__Group_1__2__Impl"
    // InternalHdbTable.g:1298:1: rule__IndexType__Group_1__2__Impl : ( ( rule__IndexType__ColumnUniqueAssignment_1_2 ) ) ;
    public final void rule__IndexType__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:1302:1: ( ( ( rule__IndexType__ColumnUniqueAssignment_1_2 ) ) )
            // InternalHdbTable.g:1303:1: ( ( rule__IndexType__ColumnUniqueAssignment_1_2 ) )
            {
            // InternalHdbTable.g:1303:1: ( ( rule__IndexType__ColumnUniqueAssignment_1_2 ) )
            // InternalHdbTable.g:1304:2: ( rule__IndexType__ColumnUniqueAssignment_1_2 )
            {
             before(grammarAccess.getIndexTypeAccess().getColumnUniqueAssignment_1_2()); 
            // InternalHdbTable.g:1305:2: ( rule__IndexType__ColumnUniqueAssignment_1_2 )
            // InternalHdbTable.g:1305:3: rule__IndexType__ColumnUniqueAssignment_1_2
            {
            pushFollow(FOLLOW_2);
            rule__IndexType__ColumnUniqueAssignment_1_2();

            state._fsp--;


            }

             after(grammarAccess.getIndexTypeAccess().getColumnUniqueAssignment_1_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IndexType__Group_1__2__Impl"


    // $ANTLR start "rule__IndexType__Group_1__3"
    // InternalHdbTable.g:1313:1: rule__IndexType__Group_1__3 : rule__IndexType__Group_1__3__Impl ;
    public final void rule__IndexType__Group_1__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:1317:1: ( rule__IndexType__Group_1__3__Impl )
            // InternalHdbTable.g:1318:2: rule__IndexType__Group_1__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__IndexType__Group_1__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IndexType__Group_1__3"


    // $ANTLR start "rule__IndexType__Group_1__3__Impl"
    // InternalHdbTable.g:1324:1: rule__IndexType__Group_1__3__Impl : ( ';' ) ;
    public final void rule__IndexType__Group_1__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:1328:1: ( ( ';' ) )
            // InternalHdbTable.g:1329:1: ( ';' )
            {
            // InternalHdbTable.g:1329:1: ( ';' )
            // InternalHdbTable.g:1330:2: ';'
            {
             before(grammarAccess.getIndexTypeAccess().getSemicolonKeyword_1_3()); 
            match(input,15,FOLLOW_2); 
             after(grammarAccess.getIndexTypeAccess().getSemicolonKeyword_1_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IndexType__Group_1__3__Impl"


    // $ANTLR start "rule__IndexType__Group_2__0"
    // InternalHdbTable.g:1340:1: rule__IndexType__Group_2__0 : rule__IndexType__Group_2__0__Impl rule__IndexType__Group_2__1 ;
    public final void rule__IndexType__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:1344:1: ( rule__IndexType__Group_2__0__Impl rule__IndexType__Group_2__1 )
            // InternalHdbTable.g:1345:2: rule__IndexType__Group_2__0__Impl rule__IndexType__Group_2__1
            {
            pushFollow(FOLLOW_4);
            rule__IndexType__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__IndexType__Group_2__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IndexType__Group_2__0"


    // $ANTLR start "rule__IndexType__Group_2__0__Impl"
    // InternalHdbTable.g:1352:1: rule__IndexType__Group_2__0__Impl : ( 'order' ) ;
    public final void rule__IndexType__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:1356:1: ( ( 'order' ) )
            // InternalHdbTable.g:1357:1: ( 'order' )
            {
            // InternalHdbTable.g:1357:1: ( 'order' )
            // InternalHdbTable.g:1358:2: 'order'
            {
             before(grammarAccess.getIndexTypeAccess().getOrderKeyword_2_0()); 
            match(input,25,FOLLOW_2); 
             after(grammarAccess.getIndexTypeAccess().getOrderKeyword_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IndexType__Group_2__0__Impl"


    // $ANTLR start "rule__IndexType__Group_2__1"
    // InternalHdbTable.g:1367:1: rule__IndexType__Group_2__1 : rule__IndexType__Group_2__1__Impl rule__IndexType__Group_2__2 ;
    public final void rule__IndexType__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:1371:1: ( rule__IndexType__Group_2__1__Impl rule__IndexType__Group_2__2 )
            // InternalHdbTable.g:1372:2: rule__IndexType__Group_2__1__Impl rule__IndexType__Group_2__2
            {
            pushFollow(FOLLOW_7);
            rule__IndexType__Group_2__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__IndexType__Group_2__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IndexType__Group_2__1"


    // $ANTLR start "rule__IndexType__Group_2__1__Impl"
    // InternalHdbTable.g:1379:1: rule__IndexType__Group_2__1__Impl : ( '=' ) ;
    public final void rule__IndexType__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:1383:1: ( ( '=' ) )
            // InternalHdbTable.g:1384:1: ( '=' )
            {
            // InternalHdbTable.g:1384:1: ( '=' )
            // InternalHdbTable.g:1385:2: '='
            {
             before(grammarAccess.getIndexTypeAccess().getEqualsSignKeyword_2_1()); 
            match(input,14,FOLLOW_2); 
             after(grammarAccess.getIndexTypeAccess().getEqualsSignKeyword_2_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IndexType__Group_2__1__Impl"


    // $ANTLR start "rule__IndexType__Group_2__2"
    // InternalHdbTable.g:1394:1: rule__IndexType__Group_2__2 : rule__IndexType__Group_2__2__Impl rule__IndexType__Group_2__3 ;
    public final void rule__IndexType__Group_2__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:1398:1: ( rule__IndexType__Group_2__2__Impl rule__IndexType__Group_2__3 )
            // InternalHdbTable.g:1399:2: rule__IndexType__Group_2__2__Impl rule__IndexType__Group_2__3
            {
            pushFollow(FOLLOW_6);
            rule__IndexType__Group_2__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__IndexType__Group_2__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IndexType__Group_2__2"


    // $ANTLR start "rule__IndexType__Group_2__2__Impl"
    // InternalHdbTable.g:1406:1: rule__IndexType__Group_2__2__Impl : ( ( rule__IndexType__ColumnOrderAssignment_2_2 ) ) ;
    public final void rule__IndexType__Group_2__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:1410:1: ( ( ( rule__IndexType__ColumnOrderAssignment_2_2 ) ) )
            // InternalHdbTable.g:1411:1: ( ( rule__IndexType__ColumnOrderAssignment_2_2 ) )
            {
            // InternalHdbTable.g:1411:1: ( ( rule__IndexType__ColumnOrderAssignment_2_2 ) )
            // InternalHdbTable.g:1412:2: ( rule__IndexType__ColumnOrderAssignment_2_2 )
            {
             before(grammarAccess.getIndexTypeAccess().getColumnOrderAssignment_2_2()); 
            // InternalHdbTable.g:1413:2: ( rule__IndexType__ColumnOrderAssignment_2_2 )
            // InternalHdbTable.g:1413:3: rule__IndexType__ColumnOrderAssignment_2_2
            {
            pushFollow(FOLLOW_2);
            rule__IndexType__ColumnOrderAssignment_2_2();

            state._fsp--;


            }

             after(grammarAccess.getIndexTypeAccess().getColumnOrderAssignment_2_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IndexType__Group_2__2__Impl"


    // $ANTLR start "rule__IndexType__Group_2__3"
    // InternalHdbTable.g:1421:1: rule__IndexType__Group_2__3 : rule__IndexType__Group_2__3__Impl ;
    public final void rule__IndexType__Group_2__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:1425:1: ( rule__IndexType__Group_2__3__Impl )
            // InternalHdbTable.g:1426:2: rule__IndexType__Group_2__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__IndexType__Group_2__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IndexType__Group_2__3"


    // $ANTLR start "rule__IndexType__Group_2__3__Impl"
    // InternalHdbTable.g:1432:1: rule__IndexType__Group_2__3__Impl : ( ';' ) ;
    public final void rule__IndexType__Group_2__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:1436:1: ( ( ';' ) )
            // InternalHdbTable.g:1437:1: ( ';' )
            {
            // InternalHdbTable.g:1437:1: ( ';' )
            // InternalHdbTable.g:1438:2: ';'
            {
             before(grammarAccess.getIndexTypeAccess().getSemicolonKeyword_2_3()); 
            match(input,15,FOLLOW_2); 
             after(grammarAccess.getIndexTypeAccess().getSemicolonKeyword_2_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IndexType__Group_2__3__Impl"


    // $ANTLR start "rule__IndexType__Group_3__0"
    // InternalHdbTable.g:1448:1: rule__IndexType__Group_3__0 : rule__IndexType__Group_3__0__Impl rule__IndexType__Group_3__1 ;
    public final void rule__IndexType__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:1452:1: ( rule__IndexType__Group_3__0__Impl rule__IndexType__Group_3__1 )
            // InternalHdbTable.g:1453:2: rule__IndexType__Group_3__0__Impl rule__IndexType__Group_3__1
            {
            pushFollow(FOLLOW_4);
            rule__IndexType__Group_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__IndexType__Group_3__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IndexType__Group_3__0"


    // $ANTLR start "rule__IndexType__Group_3__0__Impl"
    // InternalHdbTable.g:1460:1: rule__IndexType__Group_3__0__Impl : ( ( rule__IndexType__IndexColumnsAssignment_3_0 ) ) ;
    public final void rule__IndexType__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:1464:1: ( ( ( rule__IndexType__IndexColumnsAssignment_3_0 ) ) )
            // InternalHdbTable.g:1465:1: ( ( rule__IndexType__IndexColumnsAssignment_3_0 ) )
            {
            // InternalHdbTable.g:1465:1: ( ( rule__IndexType__IndexColumnsAssignment_3_0 ) )
            // InternalHdbTable.g:1466:2: ( rule__IndexType__IndexColumnsAssignment_3_0 )
            {
             before(grammarAccess.getIndexTypeAccess().getIndexColumnsAssignment_3_0()); 
            // InternalHdbTable.g:1467:2: ( rule__IndexType__IndexColumnsAssignment_3_0 )
            // InternalHdbTable.g:1467:3: rule__IndexType__IndexColumnsAssignment_3_0
            {
            pushFollow(FOLLOW_2);
            rule__IndexType__IndexColumnsAssignment_3_0();

            state._fsp--;


            }

             after(grammarAccess.getIndexTypeAccess().getIndexColumnsAssignment_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IndexType__Group_3__0__Impl"


    // $ANTLR start "rule__IndexType__Group_3__1"
    // InternalHdbTable.g:1475:1: rule__IndexType__Group_3__1 : rule__IndexType__Group_3__1__Impl rule__IndexType__Group_3__2 ;
    public final void rule__IndexType__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:1479:1: ( rule__IndexType__Group_3__1__Impl rule__IndexType__Group_3__2 )
            // InternalHdbTable.g:1480:2: rule__IndexType__Group_3__1__Impl rule__IndexType__Group_3__2
            {
            pushFollow(FOLLOW_11);
            rule__IndexType__Group_3__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__IndexType__Group_3__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IndexType__Group_3__1"


    // $ANTLR start "rule__IndexType__Group_3__1__Impl"
    // InternalHdbTable.g:1487:1: rule__IndexType__Group_3__1__Impl : ( '=' ) ;
    public final void rule__IndexType__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:1491:1: ( ( '=' ) )
            // InternalHdbTable.g:1492:1: ( '=' )
            {
            // InternalHdbTable.g:1492:1: ( '=' )
            // InternalHdbTable.g:1493:2: '='
            {
             before(grammarAccess.getIndexTypeAccess().getEqualsSignKeyword_3_1()); 
            match(input,14,FOLLOW_2); 
             after(grammarAccess.getIndexTypeAccess().getEqualsSignKeyword_3_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IndexType__Group_3__1__Impl"


    // $ANTLR start "rule__IndexType__Group_3__2"
    // InternalHdbTable.g:1502:1: rule__IndexType__Group_3__2 : rule__IndexType__Group_3__2__Impl rule__IndexType__Group_3__3 ;
    public final void rule__IndexType__Group_3__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:1506:1: ( rule__IndexType__Group_3__2__Impl rule__IndexType__Group_3__3 )
            // InternalHdbTable.g:1507:2: rule__IndexType__Group_3__2__Impl rule__IndexType__Group_3__3
            {
            pushFollow(FOLLOW_12);
            rule__IndexType__Group_3__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__IndexType__Group_3__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IndexType__Group_3__2"


    // $ANTLR start "rule__IndexType__Group_3__2__Impl"
    // InternalHdbTable.g:1514:1: rule__IndexType__Group_3__2__Impl : ( '[' ) ;
    public final void rule__IndexType__Group_3__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:1518:1: ( ( '[' ) )
            // InternalHdbTable.g:1519:1: ( '[' )
            {
            // InternalHdbTable.g:1519:1: ( '[' )
            // InternalHdbTable.g:1520:2: '['
            {
             before(grammarAccess.getIndexTypeAccess().getLeftSquareBracketKeyword_3_2()); 
            match(input,26,FOLLOW_2); 
             after(grammarAccess.getIndexTypeAccess().getLeftSquareBracketKeyword_3_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IndexType__Group_3__2__Impl"


    // $ANTLR start "rule__IndexType__Group_3__3"
    // InternalHdbTable.g:1529:1: rule__IndexType__Group_3__3 : rule__IndexType__Group_3__3__Impl rule__IndexType__Group_3__4 ;
    public final void rule__IndexType__Group_3__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:1533:1: ( rule__IndexType__Group_3__3__Impl rule__IndexType__Group_3__4 )
            // InternalHdbTable.g:1534:2: rule__IndexType__Group_3__3__Impl rule__IndexType__Group_3__4
            {
            pushFollow(FOLLOW_12);
            rule__IndexType__Group_3__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__IndexType__Group_3__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IndexType__Group_3__3"


    // $ANTLR start "rule__IndexType__Group_3__3__Impl"
    // InternalHdbTable.g:1541:1: rule__IndexType__Group_3__3__Impl : ( ( rule__IndexType__Group_3_3__0 )? ) ;
    public final void rule__IndexType__Group_3__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:1545:1: ( ( ( rule__IndexType__Group_3_3__0 )? ) )
            // InternalHdbTable.g:1546:1: ( ( rule__IndexType__Group_3_3__0 )? )
            {
            // InternalHdbTable.g:1546:1: ( ( rule__IndexType__Group_3_3__0 )? )
            // InternalHdbTable.g:1547:2: ( rule__IndexType__Group_3_3__0 )?
            {
             before(grammarAccess.getIndexTypeAccess().getGroup_3_3()); 
            // InternalHdbTable.g:1548:2: ( rule__IndexType__Group_3_3__0 )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==RULE_STRING) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // InternalHdbTable.g:1548:3: rule__IndexType__Group_3_3__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__IndexType__Group_3_3__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getIndexTypeAccess().getGroup_3_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IndexType__Group_3__3__Impl"


    // $ANTLR start "rule__IndexType__Group_3__4"
    // InternalHdbTable.g:1556:1: rule__IndexType__Group_3__4 : rule__IndexType__Group_3__4__Impl rule__IndexType__Group_3__5 ;
    public final void rule__IndexType__Group_3__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:1560:1: ( rule__IndexType__Group_3__4__Impl rule__IndexType__Group_3__5 )
            // InternalHdbTable.g:1561:2: rule__IndexType__Group_3__4__Impl rule__IndexType__Group_3__5
            {
            pushFollow(FOLLOW_6);
            rule__IndexType__Group_3__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__IndexType__Group_3__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IndexType__Group_3__4"


    // $ANTLR start "rule__IndexType__Group_3__4__Impl"
    // InternalHdbTable.g:1568:1: rule__IndexType__Group_3__4__Impl : ( ']' ) ;
    public final void rule__IndexType__Group_3__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:1572:1: ( ( ']' ) )
            // InternalHdbTable.g:1573:1: ( ']' )
            {
            // InternalHdbTable.g:1573:1: ( ']' )
            // InternalHdbTable.g:1574:2: ']'
            {
             before(grammarAccess.getIndexTypeAccess().getRightSquareBracketKeyword_3_4()); 
            match(input,27,FOLLOW_2); 
             after(grammarAccess.getIndexTypeAccess().getRightSquareBracketKeyword_3_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IndexType__Group_3__4__Impl"


    // $ANTLR start "rule__IndexType__Group_3__5"
    // InternalHdbTable.g:1583:1: rule__IndexType__Group_3__5 : rule__IndexType__Group_3__5__Impl rule__IndexType__Group_3__6 ;
    public final void rule__IndexType__Group_3__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:1587:1: ( rule__IndexType__Group_3__5__Impl rule__IndexType__Group_3__6 )
            // InternalHdbTable.g:1588:2: rule__IndexType__Group_3__5__Impl rule__IndexType__Group_3__6
            {
            pushFollow(FOLLOW_9);
            rule__IndexType__Group_3__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__IndexType__Group_3__6();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IndexType__Group_3__5"


    // $ANTLR start "rule__IndexType__Group_3__5__Impl"
    // InternalHdbTable.g:1595:1: rule__IndexType__Group_3__5__Impl : ( ';' ) ;
    public final void rule__IndexType__Group_3__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:1599:1: ( ( ';' ) )
            // InternalHdbTable.g:1600:1: ( ';' )
            {
            // InternalHdbTable.g:1600:1: ( ';' )
            // InternalHdbTable.g:1601:2: ';'
            {
             before(grammarAccess.getIndexTypeAccess().getSemicolonKeyword_3_5()); 
            match(input,15,FOLLOW_2); 
             after(grammarAccess.getIndexTypeAccess().getSemicolonKeyword_3_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IndexType__Group_3__5__Impl"


    // $ANTLR start "rule__IndexType__Group_3__6"
    // InternalHdbTable.g:1610:1: rule__IndexType__Group_3__6 : rule__IndexType__Group_3__6__Impl ;
    public final void rule__IndexType__Group_3__6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:1614:1: ( rule__IndexType__Group_3__6__Impl )
            // InternalHdbTable.g:1615:2: rule__IndexType__Group_3__6__Impl
            {
            pushFollow(FOLLOW_2);
            rule__IndexType__Group_3__6__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IndexType__Group_3__6"


    // $ANTLR start "rule__IndexType__Group_3__6__Impl"
    // InternalHdbTable.g:1621:1: rule__IndexType__Group_3__6__Impl : ( '}' ) ;
    public final void rule__IndexType__Group_3__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:1625:1: ( ( '}' ) )
            // InternalHdbTable.g:1626:1: ( '}' )
            {
            // InternalHdbTable.g:1626:1: ( '}' )
            // InternalHdbTable.g:1627:2: '}'
            {
             before(grammarAccess.getIndexTypeAccess().getRightCurlyBracketKeyword_3_6()); 
            match(input,22,FOLLOW_2); 
             after(grammarAccess.getIndexTypeAccess().getRightCurlyBracketKeyword_3_6()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IndexType__Group_3__6__Impl"


    // $ANTLR start "rule__IndexType__Group_3_3__0"
    // InternalHdbTable.g:1637:1: rule__IndexType__Group_3_3__0 : rule__IndexType__Group_3_3__0__Impl rule__IndexType__Group_3_3__1 ;
    public final void rule__IndexType__Group_3_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:1641:1: ( rule__IndexType__Group_3_3__0__Impl rule__IndexType__Group_3_3__1 )
            // InternalHdbTable.g:1642:2: rule__IndexType__Group_3_3__0__Impl rule__IndexType__Group_3_3__1
            {
            pushFollow(FOLLOW_13);
            rule__IndexType__Group_3_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__IndexType__Group_3_3__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IndexType__Group_3_3__0"


    // $ANTLR start "rule__IndexType__Group_3_3__0__Impl"
    // InternalHdbTable.g:1649:1: rule__IndexType__Group_3_3__0__Impl : ( ( rule__IndexType__TableIndexColumnsValuesAssignment_3_3_0 ) ) ;
    public final void rule__IndexType__Group_3_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:1653:1: ( ( ( rule__IndexType__TableIndexColumnsValuesAssignment_3_3_0 ) ) )
            // InternalHdbTable.g:1654:1: ( ( rule__IndexType__TableIndexColumnsValuesAssignment_3_3_0 ) )
            {
            // InternalHdbTable.g:1654:1: ( ( rule__IndexType__TableIndexColumnsValuesAssignment_3_3_0 ) )
            // InternalHdbTable.g:1655:2: ( rule__IndexType__TableIndexColumnsValuesAssignment_3_3_0 )
            {
             before(grammarAccess.getIndexTypeAccess().getTableIndexColumnsValuesAssignment_3_3_0()); 
            // InternalHdbTable.g:1656:2: ( rule__IndexType__TableIndexColumnsValuesAssignment_3_3_0 )
            // InternalHdbTable.g:1656:3: rule__IndexType__TableIndexColumnsValuesAssignment_3_3_0
            {
            pushFollow(FOLLOW_2);
            rule__IndexType__TableIndexColumnsValuesAssignment_3_3_0();

            state._fsp--;


            }

             after(grammarAccess.getIndexTypeAccess().getTableIndexColumnsValuesAssignment_3_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IndexType__Group_3_3__0__Impl"


    // $ANTLR start "rule__IndexType__Group_3_3__1"
    // InternalHdbTable.g:1664:1: rule__IndexType__Group_3_3__1 : rule__IndexType__Group_3_3__1__Impl ;
    public final void rule__IndexType__Group_3_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:1668:1: ( rule__IndexType__Group_3_3__1__Impl )
            // InternalHdbTable.g:1669:2: rule__IndexType__Group_3_3__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__IndexType__Group_3_3__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IndexType__Group_3_3__1"


    // $ANTLR start "rule__IndexType__Group_3_3__1__Impl"
    // InternalHdbTable.g:1675:1: rule__IndexType__Group_3_3__1__Impl : ( ( rule__IndexType__Group_3_3_1__0 )* ) ;
    public final void rule__IndexType__Group_3_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:1679:1: ( ( ( rule__IndexType__Group_3_3_1__0 )* ) )
            // InternalHdbTable.g:1680:1: ( ( rule__IndexType__Group_3_3_1__0 )* )
            {
            // InternalHdbTable.g:1680:1: ( ( rule__IndexType__Group_3_3_1__0 )* )
            // InternalHdbTable.g:1681:2: ( rule__IndexType__Group_3_3_1__0 )*
            {
             before(grammarAccess.getIndexTypeAccess().getGroup_3_3_1()); 
            // InternalHdbTable.g:1682:2: ( rule__IndexType__Group_3_3_1__0 )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==28) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // InternalHdbTable.g:1682:3: rule__IndexType__Group_3_3_1__0
            	    {
            	    pushFollow(FOLLOW_14);
            	    rule__IndexType__Group_3_3_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);

             after(grammarAccess.getIndexTypeAccess().getGroup_3_3_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IndexType__Group_3_3__1__Impl"


    // $ANTLR start "rule__IndexType__Group_3_3_1__0"
    // InternalHdbTable.g:1691:1: rule__IndexType__Group_3_3_1__0 : rule__IndexType__Group_3_3_1__0__Impl rule__IndexType__Group_3_3_1__1 ;
    public final void rule__IndexType__Group_3_3_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:1695:1: ( rule__IndexType__Group_3_3_1__0__Impl rule__IndexType__Group_3_3_1__1 )
            // InternalHdbTable.g:1696:2: rule__IndexType__Group_3_3_1__0__Impl rule__IndexType__Group_3_3_1__1
            {
            pushFollow(FOLLOW_5);
            rule__IndexType__Group_3_3_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__IndexType__Group_3_3_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IndexType__Group_3_3_1__0"


    // $ANTLR start "rule__IndexType__Group_3_3_1__0__Impl"
    // InternalHdbTable.g:1703:1: rule__IndexType__Group_3_3_1__0__Impl : ( ',' ) ;
    public final void rule__IndexType__Group_3_3_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:1707:1: ( ( ',' ) )
            // InternalHdbTable.g:1708:1: ( ',' )
            {
            // InternalHdbTable.g:1708:1: ( ',' )
            // InternalHdbTable.g:1709:2: ','
            {
             before(grammarAccess.getIndexTypeAccess().getCommaKeyword_3_3_1_0()); 
            match(input,28,FOLLOW_2); 
             after(grammarAccess.getIndexTypeAccess().getCommaKeyword_3_3_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IndexType__Group_3_3_1__0__Impl"


    // $ANTLR start "rule__IndexType__Group_3_3_1__1"
    // InternalHdbTable.g:1718:1: rule__IndexType__Group_3_3_1__1 : rule__IndexType__Group_3_3_1__1__Impl ;
    public final void rule__IndexType__Group_3_3_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:1722:1: ( rule__IndexType__Group_3_3_1__1__Impl )
            // InternalHdbTable.g:1723:2: rule__IndexType__Group_3_3_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__IndexType__Group_3_3_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IndexType__Group_3_3_1__1"


    // $ANTLR start "rule__IndexType__Group_3_3_1__1__Impl"
    // InternalHdbTable.g:1729:1: rule__IndexType__Group_3_3_1__1__Impl : ( ( rule__IndexType__TableIndexColumnsValuesAssignment_3_3_1_1 ) ) ;
    public final void rule__IndexType__Group_3_3_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:1733:1: ( ( ( rule__IndexType__TableIndexColumnsValuesAssignment_3_3_1_1 ) ) )
            // InternalHdbTable.g:1734:1: ( ( rule__IndexType__TableIndexColumnsValuesAssignment_3_3_1_1 ) )
            {
            // InternalHdbTable.g:1734:1: ( ( rule__IndexType__TableIndexColumnsValuesAssignment_3_3_1_1 ) )
            // InternalHdbTable.g:1735:2: ( rule__IndexType__TableIndexColumnsValuesAssignment_3_3_1_1 )
            {
             before(grammarAccess.getIndexTypeAccess().getTableIndexColumnsValuesAssignment_3_3_1_1()); 
            // InternalHdbTable.g:1736:2: ( rule__IndexType__TableIndexColumnsValuesAssignment_3_3_1_1 )
            // InternalHdbTable.g:1736:3: rule__IndexType__TableIndexColumnsValuesAssignment_3_3_1_1
            {
            pushFollow(FOLLOW_2);
            rule__IndexType__TableIndexColumnsValuesAssignment_3_3_1_1();

            state._fsp--;


            }

             after(grammarAccess.getIndexTypeAccess().getTableIndexColumnsValuesAssignment_3_3_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IndexType__Group_3_3_1__1__Impl"


    // $ANTLR start "rule__Table__Group_0__0"
    // InternalHdbTable.g:1745:1: rule__Table__Group_0__0 : rule__Table__Group_0__0__Impl rule__Table__Group_0__1 ;
    public final void rule__Table__Group_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:1749:1: ( rule__Table__Group_0__0__Impl rule__Table__Group_0__1 )
            // InternalHdbTable.g:1750:2: rule__Table__Group_0__0__Impl rule__Table__Group_0__1
            {
            pushFollow(FOLLOW_4);
            rule__Table__Group_0__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Table__Group_0__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_0__0"


    // $ANTLR start "rule__Table__Group_0__0__Impl"
    // InternalHdbTable.g:1757:1: rule__Table__Group_0__0__Impl : ( ( rule__Table__SchemaAssignment_0_0 ) ) ;
    public final void rule__Table__Group_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:1761:1: ( ( ( rule__Table__SchemaAssignment_0_0 ) ) )
            // InternalHdbTable.g:1762:1: ( ( rule__Table__SchemaAssignment_0_0 ) )
            {
            // InternalHdbTable.g:1762:1: ( ( rule__Table__SchemaAssignment_0_0 ) )
            // InternalHdbTable.g:1763:2: ( rule__Table__SchemaAssignment_0_0 )
            {
             before(grammarAccess.getTableAccess().getSchemaAssignment_0_0()); 
            // InternalHdbTable.g:1764:2: ( rule__Table__SchemaAssignment_0_0 )
            // InternalHdbTable.g:1764:3: rule__Table__SchemaAssignment_0_0
            {
            pushFollow(FOLLOW_2);
            rule__Table__SchemaAssignment_0_0();

            state._fsp--;


            }

             after(grammarAccess.getTableAccess().getSchemaAssignment_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_0__0__Impl"


    // $ANTLR start "rule__Table__Group_0__1"
    // InternalHdbTable.g:1772:1: rule__Table__Group_0__1 : rule__Table__Group_0__1__Impl rule__Table__Group_0__2 ;
    public final void rule__Table__Group_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:1776:1: ( rule__Table__Group_0__1__Impl rule__Table__Group_0__2 )
            // InternalHdbTable.g:1777:2: rule__Table__Group_0__1__Impl rule__Table__Group_0__2
            {
            pushFollow(FOLLOW_5);
            rule__Table__Group_0__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Table__Group_0__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_0__1"


    // $ANTLR start "rule__Table__Group_0__1__Impl"
    // InternalHdbTable.g:1784:1: rule__Table__Group_0__1__Impl : ( '=' ) ;
    public final void rule__Table__Group_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:1788:1: ( ( '=' ) )
            // InternalHdbTable.g:1789:1: ( '=' )
            {
            // InternalHdbTable.g:1789:1: ( '=' )
            // InternalHdbTable.g:1790:2: '='
            {
             before(grammarAccess.getTableAccess().getEqualsSignKeyword_0_1()); 
            match(input,14,FOLLOW_2); 
             after(grammarAccess.getTableAccess().getEqualsSignKeyword_0_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_0__1__Impl"


    // $ANTLR start "rule__Table__Group_0__2"
    // InternalHdbTable.g:1799:1: rule__Table__Group_0__2 : rule__Table__Group_0__2__Impl rule__Table__Group_0__3 ;
    public final void rule__Table__Group_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:1803:1: ( rule__Table__Group_0__2__Impl rule__Table__Group_0__3 )
            // InternalHdbTable.g:1804:2: rule__Table__Group_0__2__Impl rule__Table__Group_0__3
            {
            pushFollow(FOLLOW_6);
            rule__Table__Group_0__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Table__Group_0__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_0__2"


    // $ANTLR start "rule__Table__Group_0__2__Impl"
    // InternalHdbTable.g:1811:1: rule__Table__Group_0__2__Impl : ( ( rule__Table__SchemaNameAssignment_0_2 ) ) ;
    public final void rule__Table__Group_0__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:1815:1: ( ( ( rule__Table__SchemaNameAssignment_0_2 ) ) )
            // InternalHdbTable.g:1816:1: ( ( rule__Table__SchemaNameAssignment_0_2 ) )
            {
            // InternalHdbTable.g:1816:1: ( ( rule__Table__SchemaNameAssignment_0_2 ) )
            // InternalHdbTable.g:1817:2: ( rule__Table__SchemaNameAssignment_0_2 )
            {
             before(grammarAccess.getTableAccess().getSchemaNameAssignment_0_2()); 
            // InternalHdbTable.g:1818:2: ( rule__Table__SchemaNameAssignment_0_2 )
            // InternalHdbTable.g:1818:3: rule__Table__SchemaNameAssignment_0_2
            {
            pushFollow(FOLLOW_2);
            rule__Table__SchemaNameAssignment_0_2();

            state._fsp--;


            }

             after(grammarAccess.getTableAccess().getSchemaNameAssignment_0_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_0__2__Impl"


    // $ANTLR start "rule__Table__Group_0__3"
    // InternalHdbTable.g:1826:1: rule__Table__Group_0__3 : rule__Table__Group_0__3__Impl ;
    public final void rule__Table__Group_0__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:1830:1: ( rule__Table__Group_0__3__Impl )
            // InternalHdbTable.g:1831:2: rule__Table__Group_0__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Table__Group_0__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_0__3"


    // $ANTLR start "rule__Table__Group_0__3__Impl"
    // InternalHdbTable.g:1837:1: rule__Table__Group_0__3__Impl : ( ';' ) ;
    public final void rule__Table__Group_0__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:1841:1: ( ( ';' ) )
            // InternalHdbTable.g:1842:1: ( ';' )
            {
            // InternalHdbTable.g:1842:1: ( ';' )
            // InternalHdbTable.g:1843:2: ';'
            {
             before(grammarAccess.getTableAccess().getSemicolonKeyword_0_3()); 
            match(input,15,FOLLOW_2); 
             after(grammarAccess.getTableAccess().getSemicolonKeyword_0_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_0__3__Impl"


    // $ANTLR start "rule__Table__Group_1__0"
    // InternalHdbTable.g:1853:1: rule__Table__Group_1__0 : rule__Table__Group_1__0__Impl rule__Table__Group_1__1 ;
    public final void rule__Table__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:1857:1: ( rule__Table__Group_1__0__Impl rule__Table__Group_1__1 )
            // InternalHdbTable.g:1858:2: rule__Table__Group_1__0__Impl rule__Table__Group_1__1
            {
            pushFollow(FOLLOW_4);
            rule__Table__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Table__Group_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_1__0"


    // $ANTLR start "rule__Table__Group_1__0__Impl"
    // InternalHdbTable.g:1865:1: rule__Table__Group_1__0__Impl : ( ( rule__Table__TypeAssignment_1_0 ) ) ;
    public final void rule__Table__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:1869:1: ( ( ( rule__Table__TypeAssignment_1_0 ) ) )
            // InternalHdbTable.g:1870:1: ( ( rule__Table__TypeAssignment_1_0 ) )
            {
            // InternalHdbTable.g:1870:1: ( ( rule__Table__TypeAssignment_1_0 ) )
            // InternalHdbTable.g:1871:2: ( rule__Table__TypeAssignment_1_0 )
            {
             before(grammarAccess.getTableAccess().getTypeAssignment_1_0()); 
            // InternalHdbTable.g:1872:2: ( rule__Table__TypeAssignment_1_0 )
            // InternalHdbTable.g:1872:3: rule__Table__TypeAssignment_1_0
            {
            pushFollow(FOLLOW_2);
            rule__Table__TypeAssignment_1_0();

            state._fsp--;


            }

             after(grammarAccess.getTableAccess().getTypeAssignment_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_1__0__Impl"


    // $ANTLR start "rule__Table__Group_1__1"
    // InternalHdbTable.g:1880:1: rule__Table__Group_1__1 : rule__Table__Group_1__1__Impl rule__Table__Group_1__2 ;
    public final void rule__Table__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:1884:1: ( rule__Table__Group_1__1__Impl rule__Table__Group_1__2 )
            // InternalHdbTable.g:1885:2: rule__Table__Group_1__1__Impl rule__Table__Group_1__2
            {
            pushFollow(FOLLOW_7);
            rule__Table__Group_1__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Table__Group_1__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_1__1"


    // $ANTLR start "rule__Table__Group_1__1__Impl"
    // InternalHdbTable.g:1892:1: rule__Table__Group_1__1__Impl : ( '=' ) ;
    public final void rule__Table__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:1896:1: ( ( '=' ) )
            // InternalHdbTable.g:1897:1: ( '=' )
            {
            // InternalHdbTable.g:1897:1: ( '=' )
            // InternalHdbTable.g:1898:2: '='
            {
             before(grammarAccess.getTableAccess().getEqualsSignKeyword_1_1()); 
            match(input,14,FOLLOW_2); 
             after(grammarAccess.getTableAccess().getEqualsSignKeyword_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_1__1__Impl"


    // $ANTLR start "rule__Table__Group_1__2"
    // InternalHdbTable.g:1907:1: rule__Table__Group_1__2 : rule__Table__Group_1__2__Impl rule__Table__Group_1__3 ;
    public final void rule__Table__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:1911:1: ( rule__Table__Group_1__2__Impl rule__Table__Group_1__3 )
            // InternalHdbTable.g:1912:2: rule__Table__Group_1__2__Impl rule__Table__Group_1__3
            {
            pushFollow(FOLLOW_6);
            rule__Table__Group_1__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Table__Group_1__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_1__2"


    // $ANTLR start "rule__Table__Group_1__2__Impl"
    // InternalHdbTable.g:1919:1: rule__Table__Group_1__2__Impl : ( ( rule__Table__TypeValueAssignment_1_2 ) ) ;
    public final void rule__Table__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:1923:1: ( ( ( rule__Table__TypeValueAssignment_1_2 ) ) )
            // InternalHdbTable.g:1924:1: ( ( rule__Table__TypeValueAssignment_1_2 ) )
            {
            // InternalHdbTable.g:1924:1: ( ( rule__Table__TypeValueAssignment_1_2 ) )
            // InternalHdbTable.g:1925:2: ( rule__Table__TypeValueAssignment_1_2 )
            {
             before(grammarAccess.getTableAccess().getTypeValueAssignment_1_2()); 
            // InternalHdbTable.g:1926:2: ( rule__Table__TypeValueAssignment_1_2 )
            // InternalHdbTable.g:1926:3: rule__Table__TypeValueAssignment_1_2
            {
            pushFollow(FOLLOW_2);
            rule__Table__TypeValueAssignment_1_2();

            state._fsp--;


            }

             after(grammarAccess.getTableAccess().getTypeValueAssignment_1_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_1__2__Impl"


    // $ANTLR start "rule__Table__Group_1__3"
    // InternalHdbTable.g:1934:1: rule__Table__Group_1__3 : rule__Table__Group_1__3__Impl ;
    public final void rule__Table__Group_1__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:1938:1: ( rule__Table__Group_1__3__Impl )
            // InternalHdbTable.g:1939:2: rule__Table__Group_1__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Table__Group_1__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_1__3"


    // $ANTLR start "rule__Table__Group_1__3__Impl"
    // InternalHdbTable.g:1945:1: rule__Table__Group_1__3__Impl : ( ';' ) ;
    public final void rule__Table__Group_1__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:1949:1: ( ( ';' ) )
            // InternalHdbTable.g:1950:1: ( ';' )
            {
            // InternalHdbTable.g:1950:1: ( ';' )
            // InternalHdbTable.g:1951:2: ';'
            {
             before(grammarAccess.getTableAccess().getSemicolonKeyword_1_3()); 
            match(input,15,FOLLOW_2); 
             after(grammarAccess.getTableAccess().getSemicolonKeyword_1_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_1__3__Impl"


    // $ANTLR start "rule__Table__Group_2__0"
    // InternalHdbTable.g:1961:1: rule__Table__Group_2__0 : rule__Table__Group_2__0__Impl rule__Table__Group_2__1 ;
    public final void rule__Table__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:1965:1: ( rule__Table__Group_2__0__Impl rule__Table__Group_2__1 )
            // InternalHdbTable.g:1966:2: rule__Table__Group_2__0__Impl rule__Table__Group_2__1
            {
            pushFollow(FOLLOW_4);
            rule__Table__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Table__Group_2__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_2__0"


    // $ANTLR start "rule__Table__Group_2__0__Impl"
    // InternalHdbTable.g:1973:1: rule__Table__Group_2__0__Impl : ( ( rule__Table__DescriptionAssignment_2_0 ) ) ;
    public final void rule__Table__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:1977:1: ( ( ( rule__Table__DescriptionAssignment_2_0 ) ) )
            // InternalHdbTable.g:1978:1: ( ( rule__Table__DescriptionAssignment_2_0 ) )
            {
            // InternalHdbTable.g:1978:1: ( ( rule__Table__DescriptionAssignment_2_0 ) )
            // InternalHdbTable.g:1979:2: ( rule__Table__DescriptionAssignment_2_0 )
            {
             before(grammarAccess.getTableAccess().getDescriptionAssignment_2_0()); 
            // InternalHdbTable.g:1980:2: ( rule__Table__DescriptionAssignment_2_0 )
            // InternalHdbTable.g:1980:3: rule__Table__DescriptionAssignment_2_0
            {
            pushFollow(FOLLOW_2);
            rule__Table__DescriptionAssignment_2_0();

            state._fsp--;


            }

             after(grammarAccess.getTableAccess().getDescriptionAssignment_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_2__0__Impl"


    // $ANTLR start "rule__Table__Group_2__1"
    // InternalHdbTable.g:1988:1: rule__Table__Group_2__1 : rule__Table__Group_2__1__Impl rule__Table__Group_2__2 ;
    public final void rule__Table__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:1992:1: ( rule__Table__Group_2__1__Impl rule__Table__Group_2__2 )
            // InternalHdbTable.g:1993:2: rule__Table__Group_2__1__Impl rule__Table__Group_2__2
            {
            pushFollow(FOLLOW_5);
            rule__Table__Group_2__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Table__Group_2__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_2__1"


    // $ANTLR start "rule__Table__Group_2__1__Impl"
    // InternalHdbTable.g:2000:1: rule__Table__Group_2__1__Impl : ( '=' ) ;
    public final void rule__Table__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:2004:1: ( ( '=' ) )
            // InternalHdbTable.g:2005:1: ( '=' )
            {
            // InternalHdbTable.g:2005:1: ( '=' )
            // InternalHdbTable.g:2006:2: '='
            {
             before(grammarAccess.getTableAccess().getEqualsSignKeyword_2_1()); 
            match(input,14,FOLLOW_2); 
             after(grammarAccess.getTableAccess().getEqualsSignKeyword_2_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_2__1__Impl"


    // $ANTLR start "rule__Table__Group_2__2"
    // InternalHdbTable.g:2015:1: rule__Table__Group_2__2 : rule__Table__Group_2__2__Impl rule__Table__Group_2__3 ;
    public final void rule__Table__Group_2__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:2019:1: ( rule__Table__Group_2__2__Impl rule__Table__Group_2__3 )
            // InternalHdbTable.g:2020:2: rule__Table__Group_2__2__Impl rule__Table__Group_2__3
            {
            pushFollow(FOLLOW_6);
            rule__Table__Group_2__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Table__Group_2__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_2__2"


    // $ANTLR start "rule__Table__Group_2__2__Impl"
    // InternalHdbTable.g:2027:1: rule__Table__Group_2__2__Impl : ( ( rule__Table__DescriptionTextAssignment_2_2 ) ) ;
    public final void rule__Table__Group_2__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:2031:1: ( ( ( rule__Table__DescriptionTextAssignment_2_2 ) ) )
            // InternalHdbTable.g:2032:1: ( ( rule__Table__DescriptionTextAssignment_2_2 ) )
            {
            // InternalHdbTable.g:2032:1: ( ( rule__Table__DescriptionTextAssignment_2_2 ) )
            // InternalHdbTable.g:2033:2: ( rule__Table__DescriptionTextAssignment_2_2 )
            {
             before(grammarAccess.getTableAccess().getDescriptionTextAssignment_2_2()); 
            // InternalHdbTable.g:2034:2: ( rule__Table__DescriptionTextAssignment_2_2 )
            // InternalHdbTable.g:2034:3: rule__Table__DescriptionTextAssignment_2_2
            {
            pushFollow(FOLLOW_2);
            rule__Table__DescriptionTextAssignment_2_2();

            state._fsp--;


            }

             after(grammarAccess.getTableAccess().getDescriptionTextAssignment_2_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_2__2__Impl"


    // $ANTLR start "rule__Table__Group_2__3"
    // InternalHdbTable.g:2042:1: rule__Table__Group_2__3 : rule__Table__Group_2__3__Impl ;
    public final void rule__Table__Group_2__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:2046:1: ( rule__Table__Group_2__3__Impl )
            // InternalHdbTable.g:2047:2: rule__Table__Group_2__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Table__Group_2__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_2__3"


    // $ANTLR start "rule__Table__Group_2__3__Impl"
    // InternalHdbTable.g:2053:1: rule__Table__Group_2__3__Impl : ( ';' ) ;
    public final void rule__Table__Group_2__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:2057:1: ( ( ';' ) )
            // InternalHdbTable.g:2058:1: ( ';' )
            {
            // InternalHdbTable.g:2058:1: ( ';' )
            // InternalHdbTable.g:2059:2: ';'
            {
             before(grammarAccess.getTableAccess().getSemicolonKeyword_2_3()); 
            match(input,15,FOLLOW_2); 
             after(grammarAccess.getTableAccess().getSemicolonKeyword_2_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_2__3__Impl"


    // $ANTLR start "rule__Table__Group_3__0"
    // InternalHdbTable.g:2069:1: rule__Table__Group_3__0 : rule__Table__Group_3__0__Impl rule__Table__Group_3__1 ;
    public final void rule__Table__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:2073:1: ( rule__Table__Group_3__0__Impl rule__Table__Group_3__1 )
            // InternalHdbTable.g:2074:2: rule__Table__Group_3__0__Impl rule__Table__Group_3__1
            {
            pushFollow(FOLLOW_4);
            rule__Table__Group_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Table__Group_3__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_3__0"


    // $ANTLR start "rule__Table__Group_3__0__Impl"
    // InternalHdbTable.g:2081:1: rule__Table__Group_3__0__Impl : ( ( rule__Table__ColumnsAssignment_3_0 ) ) ;
    public final void rule__Table__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:2085:1: ( ( ( rule__Table__ColumnsAssignment_3_0 ) ) )
            // InternalHdbTable.g:2086:1: ( ( rule__Table__ColumnsAssignment_3_0 ) )
            {
            // InternalHdbTable.g:2086:1: ( ( rule__Table__ColumnsAssignment_3_0 ) )
            // InternalHdbTable.g:2087:2: ( rule__Table__ColumnsAssignment_3_0 )
            {
             before(grammarAccess.getTableAccess().getColumnsAssignment_3_0()); 
            // InternalHdbTable.g:2088:2: ( rule__Table__ColumnsAssignment_3_0 )
            // InternalHdbTable.g:2088:3: rule__Table__ColumnsAssignment_3_0
            {
            pushFollow(FOLLOW_2);
            rule__Table__ColumnsAssignment_3_0();

            state._fsp--;


            }

             after(grammarAccess.getTableAccess().getColumnsAssignment_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_3__0__Impl"


    // $ANTLR start "rule__Table__Group_3__1"
    // InternalHdbTable.g:2096:1: rule__Table__Group_3__1 : rule__Table__Group_3__1__Impl rule__Table__Group_3__2 ;
    public final void rule__Table__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:2100:1: ( rule__Table__Group_3__1__Impl rule__Table__Group_3__2 )
            // InternalHdbTable.g:2101:2: rule__Table__Group_3__1__Impl rule__Table__Group_3__2
            {
            pushFollow(FOLLOW_11);
            rule__Table__Group_3__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Table__Group_3__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_3__1"


    // $ANTLR start "rule__Table__Group_3__1__Impl"
    // InternalHdbTable.g:2108:1: rule__Table__Group_3__1__Impl : ( '=' ) ;
    public final void rule__Table__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:2112:1: ( ( '=' ) )
            // InternalHdbTable.g:2113:1: ( '=' )
            {
            // InternalHdbTable.g:2113:1: ( '=' )
            // InternalHdbTable.g:2114:2: '='
            {
             before(grammarAccess.getTableAccess().getEqualsSignKeyword_3_1()); 
            match(input,14,FOLLOW_2); 
             after(grammarAccess.getTableAccess().getEqualsSignKeyword_3_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_3__1__Impl"


    // $ANTLR start "rule__Table__Group_3__2"
    // InternalHdbTable.g:2123:1: rule__Table__Group_3__2 : rule__Table__Group_3__2__Impl rule__Table__Group_3__3 ;
    public final void rule__Table__Group_3__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:2127:1: ( rule__Table__Group_3__2__Impl rule__Table__Group_3__3 )
            // InternalHdbTable.g:2128:2: rule__Table__Group_3__2__Impl rule__Table__Group_3__3
            {
            pushFollow(FOLLOW_15);
            rule__Table__Group_3__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Table__Group_3__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_3__2"


    // $ANTLR start "rule__Table__Group_3__2__Impl"
    // InternalHdbTable.g:2135:1: rule__Table__Group_3__2__Impl : ( '[' ) ;
    public final void rule__Table__Group_3__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:2139:1: ( ( '[' ) )
            // InternalHdbTable.g:2140:1: ( '[' )
            {
            // InternalHdbTable.g:2140:1: ( '[' )
            // InternalHdbTable.g:2141:2: '['
            {
             before(grammarAccess.getTableAccess().getLeftSquareBracketKeyword_3_2()); 
            match(input,26,FOLLOW_2); 
             after(grammarAccess.getTableAccess().getLeftSquareBracketKeyword_3_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_3__2__Impl"


    // $ANTLR start "rule__Table__Group_3__3"
    // InternalHdbTable.g:2150:1: rule__Table__Group_3__3 : rule__Table__Group_3__3__Impl rule__Table__Group_3__4 ;
    public final void rule__Table__Group_3__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:2154:1: ( rule__Table__Group_3__3__Impl rule__Table__Group_3__4 )
            // InternalHdbTable.g:2155:2: rule__Table__Group_3__3__Impl rule__Table__Group_3__4
            {
            pushFollow(FOLLOW_15);
            rule__Table__Group_3__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Table__Group_3__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_3__3"


    // $ANTLR start "rule__Table__Group_3__3__Impl"
    // InternalHdbTable.g:2162:1: rule__Table__Group_3__3__Impl : ( ( rule__Table__Group_3_3__0 )? ) ;
    public final void rule__Table__Group_3__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:2166:1: ( ( ( rule__Table__Group_3_3__0 )? ) )
            // InternalHdbTable.g:2167:1: ( ( rule__Table__Group_3_3__0 )? )
            {
            // InternalHdbTable.g:2167:1: ( ( rule__Table__Group_3_3__0 )? )
            // InternalHdbTable.g:2168:2: ( rule__Table__Group_3_3__0 )?
            {
             before(grammarAccess.getTableAccess().getGroup_3_3()); 
            // InternalHdbTable.g:2169:2: ( rule__Table__Group_3_3__0 )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==12||(LA4_0>=15 && LA4_0<=21)||LA4_0==23) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // InternalHdbTable.g:2169:3: rule__Table__Group_3_3__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Table__Group_3_3__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getTableAccess().getGroup_3_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_3__3__Impl"


    // $ANTLR start "rule__Table__Group_3__4"
    // InternalHdbTable.g:2177:1: rule__Table__Group_3__4 : rule__Table__Group_3__4__Impl rule__Table__Group_3__5 ;
    public final void rule__Table__Group_3__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:2181:1: ( rule__Table__Group_3__4__Impl rule__Table__Group_3__5 )
            // InternalHdbTable.g:2182:2: rule__Table__Group_3__4__Impl rule__Table__Group_3__5
            {
            pushFollow(FOLLOW_6);
            rule__Table__Group_3__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Table__Group_3__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_3__4"


    // $ANTLR start "rule__Table__Group_3__4__Impl"
    // InternalHdbTable.g:2189:1: rule__Table__Group_3__4__Impl : ( ']' ) ;
    public final void rule__Table__Group_3__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:2193:1: ( ( ']' ) )
            // InternalHdbTable.g:2194:1: ( ']' )
            {
            // InternalHdbTable.g:2194:1: ( ']' )
            // InternalHdbTable.g:2195:2: ']'
            {
             before(grammarAccess.getTableAccess().getRightSquareBracketKeyword_3_4()); 
            match(input,27,FOLLOW_2); 
             after(grammarAccess.getTableAccess().getRightSquareBracketKeyword_3_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_3__4__Impl"


    // $ANTLR start "rule__Table__Group_3__5"
    // InternalHdbTable.g:2204:1: rule__Table__Group_3__5 : rule__Table__Group_3__5__Impl ;
    public final void rule__Table__Group_3__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:2208:1: ( rule__Table__Group_3__5__Impl )
            // InternalHdbTable.g:2209:2: rule__Table__Group_3__5__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Table__Group_3__5__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_3__5"


    // $ANTLR start "rule__Table__Group_3__5__Impl"
    // InternalHdbTable.g:2215:1: rule__Table__Group_3__5__Impl : ( ';' ) ;
    public final void rule__Table__Group_3__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:2219:1: ( ( ';' ) )
            // InternalHdbTable.g:2220:1: ( ';' )
            {
            // InternalHdbTable.g:2220:1: ( ';' )
            // InternalHdbTable.g:2221:2: ';'
            {
             before(grammarAccess.getTableAccess().getSemicolonKeyword_3_5()); 
            match(input,15,FOLLOW_2); 
             after(grammarAccess.getTableAccess().getSemicolonKeyword_3_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_3__5__Impl"


    // $ANTLR start "rule__Table__Group_3_3__0"
    // InternalHdbTable.g:2231:1: rule__Table__Group_3_3__0 : rule__Table__Group_3_3__0__Impl rule__Table__Group_3_3__1 ;
    public final void rule__Table__Group_3_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:2235:1: ( rule__Table__Group_3_3__0__Impl rule__Table__Group_3_3__1 )
            // InternalHdbTable.g:2236:2: rule__Table__Group_3_3__0__Impl rule__Table__Group_3_3__1
            {
            pushFollow(FOLLOW_13);
            rule__Table__Group_3_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Table__Group_3_3__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_3_3__0"


    // $ANTLR start "rule__Table__Group_3_3__0__Impl"
    // InternalHdbTable.g:2243:1: rule__Table__Group_3_3__0__Impl : ( ( rule__Table__ColumnsValuesAssignment_3_3_0 ) ) ;
    public final void rule__Table__Group_3_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:2247:1: ( ( ( rule__Table__ColumnsValuesAssignment_3_3_0 ) ) )
            // InternalHdbTable.g:2248:1: ( ( rule__Table__ColumnsValuesAssignment_3_3_0 ) )
            {
            // InternalHdbTable.g:2248:1: ( ( rule__Table__ColumnsValuesAssignment_3_3_0 ) )
            // InternalHdbTable.g:2249:2: ( rule__Table__ColumnsValuesAssignment_3_3_0 )
            {
             before(grammarAccess.getTableAccess().getColumnsValuesAssignment_3_3_0()); 
            // InternalHdbTable.g:2250:2: ( rule__Table__ColumnsValuesAssignment_3_3_0 )
            // InternalHdbTable.g:2250:3: rule__Table__ColumnsValuesAssignment_3_3_0
            {
            pushFollow(FOLLOW_2);
            rule__Table__ColumnsValuesAssignment_3_3_0();

            state._fsp--;


            }

             after(grammarAccess.getTableAccess().getColumnsValuesAssignment_3_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_3_3__0__Impl"


    // $ANTLR start "rule__Table__Group_3_3__1"
    // InternalHdbTable.g:2258:1: rule__Table__Group_3_3__1 : rule__Table__Group_3_3__1__Impl ;
    public final void rule__Table__Group_3_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:2262:1: ( rule__Table__Group_3_3__1__Impl )
            // InternalHdbTable.g:2263:2: rule__Table__Group_3_3__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Table__Group_3_3__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_3_3__1"


    // $ANTLR start "rule__Table__Group_3_3__1__Impl"
    // InternalHdbTable.g:2269:1: rule__Table__Group_3_3__1__Impl : ( ( rule__Table__Group_3_3_1__0 )* ) ;
    public final void rule__Table__Group_3_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:2273:1: ( ( ( rule__Table__Group_3_3_1__0 )* ) )
            // InternalHdbTable.g:2274:1: ( ( rule__Table__Group_3_3_1__0 )* )
            {
            // InternalHdbTable.g:2274:1: ( ( rule__Table__Group_3_3_1__0 )* )
            // InternalHdbTable.g:2275:2: ( rule__Table__Group_3_3_1__0 )*
            {
             before(grammarAccess.getTableAccess().getGroup_3_3_1()); 
            // InternalHdbTable.g:2276:2: ( rule__Table__Group_3_3_1__0 )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==28) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // InternalHdbTable.g:2276:3: rule__Table__Group_3_3_1__0
            	    {
            	    pushFollow(FOLLOW_14);
            	    rule__Table__Group_3_3_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);

             after(grammarAccess.getTableAccess().getGroup_3_3_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_3_3__1__Impl"


    // $ANTLR start "rule__Table__Group_3_3_1__0"
    // InternalHdbTable.g:2285:1: rule__Table__Group_3_3_1__0 : rule__Table__Group_3_3_1__0__Impl rule__Table__Group_3_3_1__1 ;
    public final void rule__Table__Group_3_3_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:2289:1: ( rule__Table__Group_3_3_1__0__Impl rule__Table__Group_3_3_1__1 )
            // InternalHdbTable.g:2290:2: rule__Table__Group_3_3_1__0__Impl rule__Table__Group_3_3_1__1
            {
            pushFollow(FOLLOW_16);
            rule__Table__Group_3_3_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Table__Group_3_3_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_3_3_1__0"


    // $ANTLR start "rule__Table__Group_3_3_1__0__Impl"
    // InternalHdbTable.g:2297:1: rule__Table__Group_3_3_1__0__Impl : ( ',' ) ;
    public final void rule__Table__Group_3_3_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:2301:1: ( ( ',' ) )
            // InternalHdbTable.g:2302:1: ( ',' )
            {
            // InternalHdbTable.g:2302:1: ( ',' )
            // InternalHdbTable.g:2303:2: ','
            {
             before(grammarAccess.getTableAccess().getCommaKeyword_3_3_1_0()); 
            match(input,28,FOLLOW_2); 
             after(grammarAccess.getTableAccess().getCommaKeyword_3_3_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_3_3_1__0__Impl"


    // $ANTLR start "rule__Table__Group_3_3_1__1"
    // InternalHdbTable.g:2312:1: rule__Table__Group_3_3_1__1 : rule__Table__Group_3_3_1__1__Impl ;
    public final void rule__Table__Group_3_3_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:2316:1: ( rule__Table__Group_3_3_1__1__Impl )
            // InternalHdbTable.g:2317:2: rule__Table__Group_3_3_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Table__Group_3_3_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_3_3_1__1"


    // $ANTLR start "rule__Table__Group_3_3_1__1__Impl"
    // InternalHdbTable.g:2323:1: rule__Table__Group_3_3_1__1__Impl : ( ( rule__Table__ColumnsValuesAssignment_3_3_1_1 ) ) ;
    public final void rule__Table__Group_3_3_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:2327:1: ( ( ( rule__Table__ColumnsValuesAssignment_3_3_1_1 ) ) )
            // InternalHdbTable.g:2328:1: ( ( rule__Table__ColumnsValuesAssignment_3_3_1_1 ) )
            {
            // InternalHdbTable.g:2328:1: ( ( rule__Table__ColumnsValuesAssignment_3_3_1_1 ) )
            // InternalHdbTable.g:2329:2: ( rule__Table__ColumnsValuesAssignment_3_3_1_1 )
            {
             before(grammarAccess.getTableAccess().getColumnsValuesAssignment_3_3_1_1()); 
            // InternalHdbTable.g:2330:2: ( rule__Table__ColumnsValuesAssignment_3_3_1_1 )
            // InternalHdbTable.g:2330:3: rule__Table__ColumnsValuesAssignment_3_3_1_1
            {
            pushFollow(FOLLOW_2);
            rule__Table__ColumnsValuesAssignment_3_3_1_1();

            state._fsp--;


            }

             after(grammarAccess.getTableAccess().getColumnsValuesAssignment_3_3_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_3_3_1__1__Impl"


    // $ANTLR start "rule__Table__Group_4__0"
    // InternalHdbTable.g:2339:1: rule__Table__Group_4__0 : rule__Table__Group_4__0__Impl rule__Table__Group_4__1 ;
    public final void rule__Table__Group_4__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:2343:1: ( rule__Table__Group_4__0__Impl rule__Table__Group_4__1 )
            // InternalHdbTable.g:2344:2: rule__Table__Group_4__0__Impl rule__Table__Group_4__1
            {
            pushFollow(FOLLOW_4);
            rule__Table__Group_4__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Table__Group_4__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_4__0"


    // $ANTLR start "rule__Table__Group_4__0__Impl"
    // InternalHdbTable.g:2351:1: rule__Table__Group_4__0__Impl : ( ( rule__Table__IndexesAssignment_4_0 ) ) ;
    public final void rule__Table__Group_4__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:2355:1: ( ( ( rule__Table__IndexesAssignment_4_0 ) ) )
            // InternalHdbTable.g:2356:1: ( ( rule__Table__IndexesAssignment_4_0 ) )
            {
            // InternalHdbTable.g:2356:1: ( ( rule__Table__IndexesAssignment_4_0 ) )
            // InternalHdbTable.g:2357:2: ( rule__Table__IndexesAssignment_4_0 )
            {
             before(grammarAccess.getTableAccess().getIndexesAssignment_4_0()); 
            // InternalHdbTable.g:2358:2: ( rule__Table__IndexesAssignment_4_0 )
            // InternalHdbTable.g:2358:3: rule__Table__IndexesAssignment_4_0
            {
            pushFollow(FOLLOW_2);
            rule__Table__IndexesAssignment_4_0();

            state._fsp--;


            }

             after(grammarAccess.getTableAccess().getIndexesAssignment_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_4__0__Impl"


    // $ANTLR start "rule__Table__Group_4__1"
    // InternalHdbTable.g:2366:1: rule__Table__Group_4__1 : rule__Table__Group_4__1__Impl rule__Table__Group_4__2 ;
    public final void rule__Table__Group_4__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:2370:1: ( rule__Table__Group_4__1__Impl rule__Table__Group_4__2 )
            // InternalHdbTable.g:2371:2: rule__Table__Group_4__1__Impl rule__Table__Group_4__2
            {
            pushFollow(FOLLOW_11);
            rule__Table__Group_4__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Table__Group_4__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_4__1"


    // $ANTLR start "rule__Table__Group_4__1__Impl"
    // InternalHdbTable.g:2378:1: rule__Table__Group_4__1__Impl : ( '=' ) ;
    public final void rule__Table__Group_4__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:2382:1: ( ( '=' ) )
            // InternalHdbTable.g:2383:1: ( '=' )
            {
            // InternalHdbTable.g:2383:1: ( '=' )
            // InternalHdbTable.g:2384:2: '='
            {
             before(grammarAccess.getTableAccess().getEqualsSignKeyword_4_1()); 
            match(input,14,FOLLOW_2); 
             after(grammarAccess.getTableAccess().getEqualsSignKeyword_4_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_4__1__Impl"


    // $ANTLR start "rule__Table__Group_4__2"
    // InternalHdbTable.g:2393:1: rule__Table__Group_4__2 : rule__Table__Group_4__2__Impl rule__Table__Group_4__3 ;
    public final void rule__Table__Group_4__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:2397:1: ( rule__Table__Group_4__2__Impl rule__Table__Group_4__3 )
            // InternalHdbTable.g:2398:2: rule__Table__Group_4__2__Impl rule__Table__Group_4__3
            {
            pushFollow(FOLLOW_17);
            rule__Table__Group_4__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Table__Group_4__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_4__2"


    // $ANTLR start "rule__Table__Group_4__2__Impl"
    // InternalHdbTable.g:2405:1: rule__Table__Group_4__2__Impl : ( '[' ) ;
    public final void rule__Table__Group_4__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:2409:1: ( ( '[' ) )
            // InternalHdbTable.g:2410:1: ( '[' )
            {
            // InternalHdbTable.g:2410:1: ( '[' )
            // InternalHdbTable.g:2411:2: '['
            {
             before(grammarAccess.getTableAccess().getLeftSquareBracketKeyword_4_2()); 
            match(input,26,FOLLOW_2); 
             after(grammarAccess.getTableAccess().getLeftSquareBracketKeyword_4_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_4__2__Impl"


    // $ANTLR start "rule__Table__Group_4__3"
    // InternalHdbTable.g:2420:1: rule__Table__Group_4__3 : rule__Table__Group_4__3__Impl rule__Table__Group_4__4 ;
    public final void rule__Table__Group_4__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:2424:1: ( rule__Table__Group_4__3__Impl rule__Table__Group_4__4 )
            // InternalHdbTable.g:2425:2: rule__Table__Group_4__3__Impl rule__Table__Group_4__4
            {
            pushFollow(FOLLOW_17);
            rule__Table__Group_4__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Table__Group_4__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_4__3"


    // $ANTLR start "rule__Table__Group_4__3__Impl"
    // InternalHdbTable.g:2432:1: rule__Table__Group_4__3__Impl : ( ( rule__Table__Group_4_3__0 )? ) ;
    public final void rule__Table__Group_4__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:2436:1: ( ( ( rule__Table__Group_4_3__0 )? ) )
            // InternalHdbTable.g:2437:1: ( ( rule__Table__Group_4_3__0 )? )
            {
            // InternalHdbTable.g:2437:1: ( ( rule__Table__Group_4_3__0 )? )
            // InternalHdbTable.g:2438:2: ( rule__Table__Group_4_3__0 )?
            {
             before(grammarAccess.getTableAccess().getGroup_4_3()); 
            // InternalHdbTable.g:2439:2: ( rule__Table__Group_4_3__0 )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==12||(LA6_0>=24 && LA6_0<=25)||LA6_0==29) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // InternalHdbTable.g:2439:3: rule__Table__Group_4_3__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Table__Group_4_3__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getTableAccess().getGroup_4_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_4__3__Impl"


    // $ANTLR start "rule__Table__Group_4__4"
    // InternalHdbTable.g:2447:1: rule__Table__Group_4__4 : rule__Table__Group_4__4__Impl rule__Table__Group_4__5 ;
    public final void rule__Table__Group_4__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:2451:1: ( rule__Table__Group_4__4__Impl rule__Table__Group_4__5 )
            // InternalHdbTable.g:2452:2: rule__Table__Group_4__4__Impl rule__Table__Group_4__5
            {
            pushFollow(FOLLOW_6);
            rule__Table__Group_4__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Table__Group_4__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_4__4"


    // $ANTLR start "rule__Table__Group_4__4__Impl"
    // InternalHdbTable.g:2459:1: rule__Table__Group_4__4__Impl : ( ']' ) ;
    public final void rule__Table__Group_4__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:2463:1: ( ( ']' ) )
            // InternalHdbTable.g:2464:1: ( ']' )
            {
            // InternalHdbTable.g:2464:1: ( ']' )
            // InternalHdbTable.g:2465:2: ']'
            {
             before(grammarAccess.getTableAccess().getRightSquareBracketKeyword_4_4()); 
            match(input,27,FOLLOW_2); 
             after(grammarAccess.getTableAccess().getRightSquareBracketKeyword_4_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_4__4__Impl"


    // $ANTLR start "rule__Table__Group_4__5"
    // InternalHdbTable.g:2474:1: rule__Table__Group_4__5 : rule__Table__Group_4__5__Impl ;
    public final void rule__Table__Group_4__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:2478:1: ( rule__Table__Group_4__5__Impl )
            // InternalHdbTable.g:2479:2: rule__Table__Group_4__5__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Table__Group_4__5__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_4__5"


    // $ANTLR start "rule__Table__Group_4__5__Impl"
    // InternalHdbTable.g:2485:1: rule__Table__Group_4__5__Impl : ( ';' ) ;
    public final void rule__Table__Group_4__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:2489:1: ( ( ';' ) )
            // InternalHdbTable.g:2490:1: ( ';' )
            {
            // InternalHdbTable.g:2490:1: ( ';' )
            // InternalHdbTable.g:2491:2: ';'
            {
             before(grammarAccess.getTableAccess().getSemicolonKeyword_4_5()); 
            match(input,15,FOLLOW_2); 
             after(grammarAccess.getTableAccess().getSemicolonKeyword_4_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_4__5__Impl"


    // $ANTLR start "rule__Table__Group_4_3__0"
    // InternalHdbTable.g:2501:1: rule__Table__Group_4_3__0 : rule__Table__Group_4_3__0__Impl rule__Table__Group_4_3__1 ;
    public final void rule__Table__Group_4_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:2505:1: ( rule__Table__Group_4_3__0__Impl rule__Table__Group_4_3__1 )
            // InternalHdbTable.g:2506:2: rule__Table__Group_4_3__0__Impl rule__Table__Group_4_3__1
            {
            pushFollow(FOLLOW_13);
            rule__Table__Group_4_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Table__Group_4_3__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_4_3__0"


    // $ANTLR start "rule__Table__Group_4_3__0__Impl"
    // InternalHdbTable.g:2513:1: rule__Table__Group_4_3__0__Impl : ( ( rule__Table__IndexesValuesAssignment_4_3_0 ) ) ;
    public final void rule__Table__Group_4_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:2517:1: ( ( ( rule__Table__IndexesValuesAssignment_4_3_0 ) ) )
            // InternalHdbTable.g:2518:1: ( ( rule__Table__IndexesValuesAssignment_4_3_0 ) )
            {
            // InternalHdbTable.g:2518:1: ( ( rule__Table__IndexesValuesAssignment_4_3_0 ) )
            // InternalHdbTable.g:2519:2: ( rule__Table__IndexesValuesAssignment_4_3_0 )
            {
             before(grammarAccess.getTableAccess().getIndexesValuesAssignment_4_3_0()); 
            // InternalHdbTable.g:2520:2: ( rule__Table__IndexesValuesAssignment_4_3_0 )
            // InternalHdbTable.g:2520:3: rule__Table__IndexesValuesAssignment_4_3_0
            {
            pushFollow(FOLLOW_2);
            rule__Table__IndexesValuesAssignment_4_3_0();

            state._fsp--;


            }

             after(grammarAccess.getTableAccess().getIndexesValuesAssignment_4_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_4_3__0__Impl"


    // $ANTLR start "rule__Table__Group_4_3__1"
    // InternalHdbTable.g:2528:1: rule__Table__Group_4_3__1 : rule__Table__Group_4_3__1__Impl ;
    public final void rule__Table__Group_4_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:2532:1: ( rule__Table__Group_4_3__1__Impl )
            // InternalHdbTable.g:2533:2: rule__Table__Group_4_3__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Table__Group_4_3__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_4_3__1"


    // $ANTLR start "rule__Table__Group_4_3__1__Impl"
    // InternalHdbTable.g:2539:1: rule__Table__Group_4_3__1__Impl : ( ( rule__Table__Group_4_3_1__0 )* ) ;
    public final void rule__Table__Group_4_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:2543:1: ( ( ( rule__Table__Group_4_3_1__0 )* ) )
            // InternalHdbTable.g:2544:1: ( ( rule__Table__Group_4_3_1__0 )* )
            {
            // InternalHdbTable.g:2544:1: ( ( rule__Table__Group_4_3_1__0 )* )
            // InternalHdbTable.g:2545:2: ( rule__Table__Group_4_3_1__0 )*
            {
             before(grammarAccess.getTableAccess().getGroup_4_3_1()); 
            // InternalHdbTable.g:2546:2: ( rule__Table__Group_4_3_1__0 )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==28) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // InternalHdbTable.g:2546:3: rule__Table__Group_4_3_1__0
            	    {
            	    pushFollow(FOLLOW_14);
            	    rule__Table__Group_4_3_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);

             after(grammarAccess.getTableAccess().getGroup_4_3_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_4_3__1__Impl"


    // $ANTLR start "rule__Table__Group_4_3_1__0"
    // InternalHdbTable.g:2555:1: rule__Table__Group_4_3_1__0 : rule__Table__Group_4_3_1__0__Impl rule__Table__Group_4_3_1__1 ;
    public final void rule__Table__Group_4_3_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:2559:1: ( rule__Table__Group_4_3_1__0__Impl rule__Table__Group_4_3_1__1 )
            // InternalHdbTable.g:2560:2: rule__Table__Group_4_3_1__0__Impl rule__Table__Group_4_3_1__1
            {
            pushFollow(FOLLOW_18);
            rule__Table__Group_4_3_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Table__Group_4_3_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_4_3_1__0"


    // $ANTLR start "rule__Table__Group_4_3_1__0__Impl"
    // InternalHdbTable.g:2567:1: rule__Table__Group_4_3_1__0__Impl : ( ',' ) ;
    public final void rule__Table__Group_4_3_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:2571:1: ( ( ',' ) )
            // InternalHdbTable.g:2572:1: ( ',' )
            {
            // InternalHdbTable.g:2572:1: ( ',' )
            // InternalHdbTable.g:2573:2: ','
            {
             before(grammarAccess.getTableAccess().getCommaKeyword_4_3_1_0()); 
            match(input,28,FOLLOW_2); 
             after(grammarAccess.getTableAccess().getCommaKeyword_4_3_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_4_3_1__0__Impl"


    // $ANTLR start "rule__Table__Group_4_3_1__1"
    // InternalHdbTable.g:2582:1: rule__Table__Group_4_3_1__1 : rule__Table__Group_4_3_1__1__Impl ;
    public final void rule__Table__Group_4_3_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:2586:1: ( rule__Table__Group_4_3_1__1__Impl )
            // InternalHdbTable.g:2587:2: rule__Table__Group_4_3_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Table__Group_4_3_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_4_3_1__1"


    // $ANTLR start "rule__Table__Group_4_3_1__1__Impl"
    // InternalHdbTable.g:2593:1: rule__Table__Group_4_3_1__1__Impl : ( ( rule__Table__IndexesValuesAssignment_4_3_1_1 ) ) ;
    public final void rule__Table__Group_4_3_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:2597:1: ( ( ( rule__Table__IndexesValuesAssignment_4_3_1_1 ) ) )
            // InternalHdbTable.g:2598:1: ( ( rule__Table__IndexesValuesAssignment_4_3_1_1 ) )
            {
            // InternalHdbTable.g:2598:1: ( ( rule__Table__IndexesValuesAssignment_4_3_1_1 ) )
            // InternalHdbTable.g:2599:2: ( rule__Table__IndexesValuesAssignment_4_3_1_1 )
            {
             before(grammarAccess.getTableAccess().getIndexesValuesAssignment_4_3_1_1()); 
            // InternalHdbTable.g:2600:2: ( rule__Table__IndexesValuesAssignment_4_3_1_1 )
            // InternalHdbTable.g:2600:3: rule__Table__IndexesValuesAssignment_4_3_1_1
            {
            pushFollow(FOLLOW_2);
            rule__Table__IndexesValuesAssignment_4_3_1_1();

            state._fsp--;


            }

             after(grammarAccess.getTableAccess().getIndexesValuesAssignment_4_3_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_4_3_1__1__Impl"


    // $ANTLR start "rule__Table__Group_5__0"
    // InternalHdbTable.g:2609:1: rule__Table__Group_5__0 : rule__Table__Group_5__0__Impl rule__Table__Group_5__1 ;
    public final void rule__Table__Group_5__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:2613:1: ( rule__Table__Group_5__0__Impl rule__Table__Group_5__1 )
            // InternalHdbTable.g:2614:2: rule__Table__Group_5__0__Impl rule__Table__Group_5__1
            {
            pushFollow(FOLLOW_6);
            rule__Table__Group_5__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Table__Group_5__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_5__0"


    // $ANTLR start "rule__Table__Group_5__0__Impl"
    // InternalHdbTable.g:2621:1: rule__Table__Group_5__0__Impl : ( ( rule__Table__Group_5_0__0 )? ) ;
    public final void rule__Table__Group_5__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:2625:1: ( ( ( rule__Table__Group_5_0__0 )? ) )
            // InternalHdbTable.g:2626:1: ( ( rule__Table__Group_5_0__0 )? )
            {
            // InternalHdbTable.g:2626:1: ( ( rule__Table__Group_5_0__0 )? )
            // InternalHdbTable.g:2627:2: ( rule__Table__Group_5_0__0 )?
            {
             before(grammarAccess.getTableAccess().getGroup_5_0()); 
            // InternalHdbTable.g:2628:2: ( rule__Table__Group_5_0__0 )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==35) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // InternalHdbTable.g:2628:3: rule__Table__Group_5_0__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Table__Group_5_0__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getTableAccess().getGroup_5_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_5__0__Impl"


    // $ANTLR start "rule__Table__Group_5__1"
    // InternalHdbTable.g:2636:1: rule__Table__Group_5__1 : rule__Table__Group_5__1__Impl ;
    public final void rule__Table__Group_5__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:2640:1: ( rule__Table__Group_5__1__Impl )
            // InternalHdbTable.g:2641:2: rule__Table__Group_5__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Table__Group_5__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_5__1"


    // $ANTLR start "rule__Table__Group_5__1__Impl"
    // InternalHdbTable.g:2647:1: rule__Table__Group_5__1__Impl : ( ';' ) ;
    public final void rule__Table__Group_5__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:2651:1: ( ( ';' ) )
            // InternalHdbTable.g:2652:1: ( ';' )
            {
            // InternalHdbTable.g:2652:1: ( ';' )
            // InternalHdbTable.g:2653:2: ';'
            {
             before(grammarAccess.getTableAccess().getSemicolonKeyword_5_1()); 
            match(input,15,FOLLOW_2); 
             after(grammarAccess.getTableAccess().getSemicolonKeyword_5_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_5__1__Impl"


    // $ANTLR start "rule__Table__Group_5_0__0"
    // InternalHdbTable.g:2663:1: rule__Table__Group_5_0__0 : rule__Table__Group_5_0__0__Impl rule__Table__Group_5_0__1 ;
    public final void rule__Table__Group_5_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:2667:1: ( rule__Table__Group_5_0__0__Impl rule__Table__Group_5_0__1 )
            // InternalHdbTable.g:2668:2: rule__Table__Group_5_0__0__Impl rule__Table__Group_5_0__1
            {
            pushFollow(FOLLOW_4);
            rule__Table__Group_5_0__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Table__Group_5_0__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_5_0__0"


    // $ANTLR start "rule__Table__Group_5_0__0__Impl"
    // InternalHdbTable.g:2675:1: rule__Table__Group_5_0__0__Impl : ( ( rule__Table__PrimaryKeyColumnsAssignment_5_0_0 ) ) ;
    public final void rule__Table__Group_5_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:2679:1: ( ( ( rule__Table__PrimaryKeyColumnsAssignment_5_0_0 ) ) )
            // InternalHdbTable.g:2680:1: ( ( rule__Table__PrimaryKeyColumnsAssignment_5_0_0 ) )
            {
            // InternalHdbTable.g:2680:1: ( ( rule__Table__PrimaryKeyColumnsAssignment_5_0_0 ) )
            // InternalHdbTable.g:2681:2: ( rule__Table__PrimaryKeyColumnsAssignment_5_0_0 )
            {
             before(grammarAccess.getTableAccess().getPrimaryKeyColumnsAssignment_5_0_0()); 
            // InternalHdbTable.g:2682:2: ( rule__Table__PrimaryKeyColumnsAssignment_5_0_0 )
            // InternalHdbTable.g:2682:3: rule__Table__PrimaryKeyColumnsAssignment_5_0_0
            {
            pushFollow(FOLLOW_2);
            rule__Table__PrimaryKeyColumnsAssignment_5_0_0();

            state._fsp--;


            }

             after(grammarAccess.getTableAccess().getPrimaryKeyColumnsAssignment_5_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_5_0__0__Impl"


    // $ANTLR start "rule__Table__Group_5_0__1"
    // InternalHdbTable.g:2690:1: rule__Table__Group_5_0__1 : rule__Table__Group_5_0__1__Impl rule__Table__Group_5_0__2 ;
    public final void rule__Table__Group_5_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:2694:1: ( rule__Table__Group_5_0__1__Impl rule__Table__Group_5_0__2 )
            // InternalHdbTable.g:2695:2: rule__Table__Group_5_0__1__Impl rule__Table__Group_5_0__2
            {
            pushFollow(FOLLOW_11);
            rule__Table__Group_5_0__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Table__Group_5_0__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_5_0__1"


    // $ANTLR start "rule__Table__Group_5_0__1__Impl"
    // InternalHdbTable.g:2702:1: rule__Table__Group_5_0__1__Impl : ( '=' ) ;
    public final void rule__Table__Group_5_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:2706:1: ( ( '=' ) )
            // InternalHdbTable.g:2707:1: ( '=' )
            {
            // InternalHdbTable.g:2707:1: ( '=' )
            // InternalHdbTable.g:2708:2: '='
            {
             before(grammarAccess.getTableAccess().getEqualsSignKeyword_5_0_1()); 
            match(input,14,FOLLOW_2); 
             after(grammarAccess.getTableAccess().getEqualsSignKeyword_5_0_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_5_0__1__Impl"


    // $ANTLR start "rule__Table__Group_5_0__2"
    // InternalHdbTable.g:2717:1: rule__Table__Group_5_0__2 : rule__Table__Group_5_0__2__Impl rule__Table__Group_5_0__3 ;
    public final void rule__Table__Group_5_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:2721:1: ( rule__Table__Group_5_0__2__Impl rule__Table__Group_5_0__3 )
            // InternalHdbTable.g:2722:2: rule__Table__Group_5_0__2__Impl rule__Table__Group_5_0__3
            {
            pushFollow(FOLLOW_12);
            rule__Table__Group_5_0__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Table__Group_5_0__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_5_0__2"


    // $ANTLR start "rule__Table__Group_5_0__2__Impl"
    // InternalHdbTable.g:2729:1: rule__Table__Group_5_0__2__Impl : ( '[' ) ;
    public final void rule__Table__Group_5_0__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:2733:1: ( ( '[' ) )
            // InternalHdbTable.g:2734:1: ( '[' )
            {
            // InternalHdbTable.g:2734:1: ( '[' )
            // InternalHdbTable.g:2735:2: '['
            {
             before(grammarAccess.getTableAccess().getLeftSquareBracketKeyword_5_0_2()); 
            match(input,26,FOLLOW_2); 
             after(grammarAccess.getTableAccess().getLeftSquareBracketKeyword_5_0_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_5_0__2__Impl"


    // $ANTLR start "rule__Table__Group_5_0__3"
    // InternalHdbTable.g:2744:1: rule__Table__Group_5_0__3 : rule__Table__Group_5_0__3__Impl rule__Table__Group_5_0__4 ;
    public final void rule__Table__Group_5_0__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:2748:1: ( rule__Table__Group_5_0__3__Impl rule__Table__Group_5_0__4 )
            // InternalHdbTable.g:2749:2: rule__Table__Group_5_0__3__Impl rule__Table__Group_5_0__4
            {
            pushFollow(FOLLOW_12);
            rule__Table__Group_5_0__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Table__Group_5_0__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_5_0__3"


    // $ANTLR start "rule__Table__Group_5_0__3__Impl"
    // InternalHdbTable.g:2756:1: rule__Table__Group_5_0__3__Impl : ( ( rule__Table__Group_5_0_3__0 )? ) ;
    public final void rule__Table__Group_5_0__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:2760:1: ( ( ( rule__Table__Group_5_0_3__0 )? ) )
            // InternalHdbTable.g:2761:1: ( ( rule__Table__Group_5_0_3__0 )? )
            {
            // InternalHdbTable.g:2761:1: ( ( rule__Table__Group_5_0_3__0 )? )
            // InternalHdbTable.g:2762:2: ( rule__Table__Group_5_0_3__0 )?
            {
             before(grammarAccess.getTableAccess().getGroup_5_0_3()); 
            // InternalHdbTable.g:2763:2: ( rule__Table__Group_5_0_3__0 )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==RULE_STRING) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // InternalHdbTable.g:2763:3: rule__Table__Group_5_0_3__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Table__Group_5_0_3__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getTableAccess().getGroup_5_0_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_5_0__3__Impl"


    // $ANTLR start "rule__Table__Group_5_0__4"
    // InternalHdbTable.g:2771:1: rule__Table__Group_5_0__4 : rule__Table__Group_5_0__4__Impl ;
    public final void rule__Table__Group_5_0__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:2775:1: ( rule__Table__Group_5_0__4__Impl )
            // InternalHdbTable.g:2776:2: rule__Table__Group_5_0__4__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Table__Group_5_0__4__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_5_0__4"


    // $ANTLR start "rule__Table__Group_5_0__4__Impl"
    // InternalHdbTable.g:2782:1: rule__Table__Group_5_0__4__Impl : ( ']' ) ;
    public final void rule__Table__Group_5_0__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:2786:1: ( ( ']' ) )
            // InternalHdbTable.g:2787:1: ( ']' )
            {
            // InternalHdbTable.g:2787:1: ( ']' )
            // InternalHdbTable.g:2788:2: ']'
            {
             before(grammarAccess.getTableAccess().getRightSquareBracketKeyword_5_0_4()); 
            match(input,27,FOLLOW_2); 
             after(grammarAccess.getTableAccess().getRightSquareBracketKeyword_5_0_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_5_0__4__Impl"


    // $ANTLR start "rule__Table__Group_5_0_3__0"
    // InternalHdbTable.g:2798:1: rule__Table__Group_5_0_3__0 : rule__Table__Group_5_0_3__0__Impl rule__Table__Group_5_0_3__1 ;
    public final void rule__Table__Group_5_0_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:2802:1: ( rule__Table__Group_5_0_3__0__Impl rule__Table__Group_5_0_3__1 )
            // InternalHdbTable.g:2803:2: rule__Table__Group_5_0_3__0__Impl rule__Table__Group_5_0_3__1
            {
            pushFollow(FOLLOW_13);
            rule__Table__Group_5_0_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Table__Group_5_0_3__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_5_0_3__0"


    // $ANTLR start "rule__Table__Group_5_0_3__0__Impl"
    // InternalHdbTable.g:2810:1: rule__Table__Group_5_0_3__0__Impl : ( ( rule__Table__TablePrimaryKeyColumnsValuesAssignment_5_0_3_0 ) ) ;
    public final void rule__Table__Group_5_0_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:2814:1: ( ( ( rule__Table__TablePrimaryKeyColumnsValuesAssignment_5_0_3_0 ) ) )
            // InternalHdbTable.g:2815:1: ( ( rule__Table__TablePrimaryKeyColumnsValuesAssignment_5_0_3_0 ) )
            {
            // InternalHdbTable.g:2815:1: ( ( rule__Table__TablePrimaryKeyColumnsValuesAssignment_5_0_3_0 ) )
            // InternalHdbTable.g:2816:2: ( rule__Table__TablePrimaryKeyColumnsValuesAssignment_5_0_3_0 )
            {
             before(grammarAccess.getTableAccess().getTablePrimaryKeyColumnsValuesAssignment_5_0_3_0()); 
            // InternalHdbTable.g:2817:2: ( rule__Table__TablePrimaryKeyColumnsValuesAssignment_5_0_3_0 )
            // InternalHdbTable.g:2817:3: rule__Table__TablePrimaryKeyColumnsValuesAssignment_5_0_3_0
            {
            pushFollow(FOLLOW_2);
            rule__Table__TablePrimaryKeyColumnsValuesAssignment_5_0_3_0();

            state._fsp--;


            }

             after(grammarAccess.getTableAccess().getTablePrimaryKeyColumnsValuesAssignment_5_0_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_5_0_3__0__Impl"


    // $ANTLR start "rule__Table__Group_5_0_3__1"
    // InternalHdbTable.g:2825:1: rule__Table__Group_5_0_3__1 : rule__Table__Group_5_0_3__1__Impl ;
    public final void rule__Table__Group_5_0_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:2829:1: ( rule__Table__Group_5_0_3__1__Impl )
            // InternalHdbTable.g:2830:2: rule__Table__Group_5_0_3__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Table__Group_5_0_3__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_5_0_3__1"


    // $ANTLR start "rule__Table__Group_5_0_3__1__Impl"
    // InternalHdbTable.g:2836:1: rule__Table__Group_5_0_3__1__Impl : ( ( rule__Table__Group_5_0_3_1__0 )* ) ;
    public final void rule__Table__Group_5_0_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:2840:1: ( ( ( rule__Table__Group_5_0_3_1__0 )* ) )
            // InternalHdbTable.g:2841:1: ( ( rule__Table__Group_5_0_3_1__0 )* )
            {
            // InternalHdbTable.g:2841:1: ( ( rule__Table__Group_5_0_3_1__0 )* )
            // InternalHdbTable.g:2842:2: ( rule__Table__Group_5_0_3_1__0 )*
            {
             before(grammarAccess.getTableAccess().getGroup_5_0_3_1()); 
            // InternalHdbTable.g:2843:2: ( rule__Table__Group_5_0_3_1__0 )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==28) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // InternalHdbTable.g:2843:3: rule__Table__Group_5_0_3_1__0
            	    {
            	    pushFollow(FOLLOW_14);
            	    rule__Table__Group_5_0_3_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);

             after(grammarAccess.getTableAccess().getGroup_5_0_3_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_5_0_3__1__Impl"


    // $ANTLR start "rule__Table__Group_5_0_3_1__0"
    // InternalHdbTable.g:2852:1: rule__Table__Group_5_0_3_1__0 : rule__Table__Group_5_0_3_1__0__Impl rule__Table__Group_5_0_3_1__1 ;
    public final void rule__Table__Group_5_0_3_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:2856:1: ( rule__Table__Group_5_0_3_1__0__Impl rule__Table__Group_5_0_3_1__1 )
            // InternalHdbTable.g:2857:2: rule__Table__Group_5_0_3_1__0__Impl rule__Table__Group_5_0_3_1__1
            {
            pushFollow(FOLLOW_5);
            rule__Table__Group_5_0_3_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Table__Group_5_0_3_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_5_0_3_1__0"


    // $ANTLR start "rule__Table__Group_5_0_3_1__0__Impl"
    // InternalHdbTable.g:2864:1: rule__Table__Group_5_0_3_1__0__Impl : ( ',' ) ;
    public final void rule__Table__Group_5_0_3_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:2868:1: ( ( ',' ) )
            // InternalHdbTable.g:2869:1: ( ',' )
            {
            // InternalHdbTable.g:2869:1: ( ',' )
            // InternalHdbTable.g:2870:2: ','
            {
             before(grammarAccess.getTableAccess().getCommaKeyword_5_0_3_1_0()); 
            match(input,28,FOLLOW_2); 
             after(grammarAccess.getTableAccess().getCommaKeyword_5_0_3_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_5_0_3_1__0__Impl"


    // $ANTLR start "rule__Table__Group_5_0_3_1__1"
    // InternalHdbTable.g:2879:1: rule__Table__Group_5_0_3_1__1 : rule__Table__Group_5_0_3_1__1__Impl ;
    public final void rule__Table__Group_5_0_3_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:2883:1: ( rule__Table__Group_5_0_3_1__1__Impl )
            // InternalHdbTable.g:2884:2: rule__Table__Group_5_0_3_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Table__Group_5_0_3_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_5_0_3_1__1"


    // $ANTLR start "rule__Table__Group_5_0_3_1__1__Impl"
    // InternalHdbTable.g:2890:1: rule__Table__Group_5_0_3_1__1__Impl : ( ( rule__Table__TablePrimaryKeyColumnsValuesAssignment_5_0_3_1_1 ) ) ;
    public final void rule__Table__Group_5_0_3_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:2894:1: ( ( ( rule__Table__TablePrimaryKeyColumnsValuesAssignment_5_0_3_1_1 ) ) )
            // InternalHdbTable.g:2895:1: ( ( rule__Table__TablePrimaryKeyColumnsValuesAssignment_5_0_3_1_1 ) )
            {
            // InternalHdbTable.g:2895:1: ( ( rule__Table__TablePrimaryKeyColumnsValuesAssignment_5_0_3_1_1 ) )
            // InternalHdbTable.g:2896:2: ( rule__Table__TablePrimaryKeyColumnsValuesAssignment_5_0_3_1_1 )
            {
             before(grammarAccess.getTableAccess().getTablePrimaryKeyColumnsValuesAssignment_5_0_3_1_1()); 
            // InternalHdbTable.g:2897:2: ( rule__Table__TablePrimaryKeyColumnsValuesAssignment_5_0_3_1_1 )
            // InternalHdbTable.g:2897:3: rule__Table__TablePrimaryKeyColumnsValuesAssignment_5_0_3_1_1
            {
            pushFollow(FOLLOW_2);
            rule__Table__TablePrimaryKeyColumnsValuesAssignment_5_0_3_1_1();

            state._fsp--;


            }

             after(grammarAccess.getTableAccess().getTablePrimaryKeyColumnsValuesAssignment_5_0_3_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group_5_0_3_1__1__Impl"


    // $ANTLR start "rule__ColumnType__UnorderedGroup"
    // InternalHdbTable.g:2906:1: rule__ColumnType__UnorderedGroup : rule__ColumnType__UnorderedGroup__0 {...}?;
    public final void rule__ColumnType__UnorderedGroup() throws RecognitionException {

        		int stackSize = keepStackSize();
        		getUnorderedGroupHelper().enter(grammarAccess.getColumnTypeAccess().getUnorderedGroup());
        	
        try {
            // InternalHdbTable.g:2911:1: ( rule__ColumnType__UnorderedGroup__0 {...}?)
            // InternalHdbTable.g:2912:2: rule__ColumnType__UnorderedGroup__0 {...}?
            {
            pushFollow(FOLLOW_2);
            rule__ColumnType__UnorderedGroup__0();

            state._fsp--;

            if ( ! getUnorderedGroupHelper().canLeave(grammarAccess.getColumnTypeAccess().getUnorderedGroup()) ) {
                throw new FailedPredicateException(input, "rule__ColumnType__UnorderedGroup", "getUnorderedGroupHelper().canLeave(grammarAccess.getColumnTypeAccess().getUnorderedGroup())");
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	getUnorderedGroupHelper().leave(grammarAccess.getColumnTypeAccess().getUnorderedGroup());
            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__UnorderedGroup"


    // $ANTLR start "rule__ColumnType__UnorderedGroup__Impl"
    // InternalHdbTable.g:2920:1: rule__ColumnType__UnorderedGroup__Impl : ( ({...}? => ( ( ( rule__ColumnType__Group_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ColumnType__Group_1__0 ) ) ) ) | ({...}? => ( ( ( rule__ColumnType__Group_2__0 ) ) ) ) | ({...}? => ( ( ( rule__ColumnType__Group_3__0 ) ) ) ) | ({...}? => ( ( ( rule__ColumnType__Group_4__0 ) ) ) ) | ({...}? => ( ( ( rule__ColumnType__Group_5__0 ) ) ) ) | ({...}? => ( ( ( rule__ColumnType__Group_6__0 ) ) ) ) | ({...}? => ( ( ( rule__ColumnType__Group_7__0 ) ) ) ) ) ;
    public final void rule__ColumnType__UnorderedGroup__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        		boolean selected = false;
        	
        try {
            // InternalHdbTable.g:2925:1: ( ( ({...}? => ( ( ( rule__ColumnType__Group_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ColumnType__Group_1__0 ) ) ) ) | ({...}? => ( ( ( rule__ColumnType__Group_2__0 ) ) ) ) | ({...}? => ( ( ( rule__ColumnType__Group_3__0 ) ) ) ) | ({...}? => ( ( ( rule__ColumnType__Group_4__0 ) ) ) ) | ({...}? => ( ( ( rule__ColumnType__Group_5__0 ) ) ) ) | ({...}? => ( ( ( rule__ColumnType__Group_6__0 ) ) ) ) | ({...}? => ( ( ( rule__ColumnType__Group_7__0 ) ) ) ) ) )
            // InternalHdbTable.g:2926:3: ( ({...}? => ( ( ( rule__ColumnType__Group_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ColumnType__Group_1__0 ) ) ) ) | ({...}? => ( ( ( rule__ColumnType__Group_2__0 ) ) ) ) | ({...}? => ( ( ( rule__ColumnType__Group_3__0 ) ) ) ) | ({...}? => ( ( ( rule__ColumnType__Group_4__0 ) ) ) ) | ({...}? => ( ( ( rule__ColumnType__Group_5__0 ) ) ) ) | ({...}? => ( ( ( rule__ColumnType__Group_6__0 ) ) ) ) | ({...}? => ( ( ( rule__ColumnType__Group_7__0 ) ) ) ) )
            {
            // InternalHdbTable.g:2926:3: ( ({...}? => ( ( ( rule__ColumnType__Group_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ColumnType__Group_1__0 ) ) ) ) | ({...}? => ( ( ( rule__ColumnType__Group_2__0 ) ) ) ) | ({...}? => ( ( ( rule__ColumnType__Group_3__0 ) ) ) ) | ({...}? => ( ( ( rule__ColumnType__Group_4__0 ) ) ) ) | ({...}? => ( ( ( rule__ColumnType__Group_5__0 ) ) ) ) | ({...}? => ( ( ( rule__ColumnType__Group_6__0 ) ) ) ) | ({...}? => ( ( ( rule__ColumnType__Group_7__0 ) ) ) ) )
            int alt11=8;
            int LA11_0 = input.LA(1);

            if ( LA11_0 == 12 && getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 0) ) {
                alt11=1;
            }
            else if ( LA11_0 == 16 && getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 1) ) {
                alt11=2;
            }
            else if ( LA11_0 == 17 && getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 2) ) {
                alt11=3;
            }
            else if ( LA11_0 == 18 && getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 3) ) {
                alt11=4;
            }
            else if ( LA11_0 == 19 && getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 4) ) {
                alt11=5;
            }
            else if ( LA11_0 == 20 && getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 5) ) {
                alt11=6;
            }
            else if ( LA11_0 == 21 && getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 6) ) {
                alt11=7;
            }
            else if ( ( LA11_0 == 15 || LA11_0 == 23 ) && getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 7) ) {
                alt11=8;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;
            }
            switch (alt11) {
                case 1 :
                    // InternalHdbTable.g:2927:3: ({...}? => ( ( ( rule__ColumnType__Group_0__0 ) ) ) )
                    {
                    // InternalHdbTable.g:2927:3: ({...}? => ( ( ( rule__ColumnType__Group_0__0 ) ) ) )
                    // InternalHdbTable.g:2928:4: {...}? => ( ( ( rule__ColumnType__Group_0__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 0) ) {
                        throw new FailedPredicateException(input, "rule__ColumnType__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 0)");
                    }
                    // InternalHdbTable.g:2928:104: ( ( ( rule__ColumnType__Group_0__0 ) ) )
                    // InternalHdbTable.g:2929:5: ( ( rule__ColumnType__Group_0__0 ) )
                    {

                    					getUnorderedGroupHelper().select(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 0);
                    				

                    					selected = true;
                    				
                    // InternalHdbTable.g:2935:5: ( ( rule__ColumnType__Group_0__0 ) )
                    // InternalHdbTable.g:2936:6: ( rule__ColumnType__Group_0__0 )
                    {
                     before(grammarAccess.getColumnTypeAccess().getGroup_0()); 
                    // InternalHdbTable.g:2937:6: ( rule__ColumnType__Group_0__0 )
                    // InternalHdbTable.g:2937:7: rule__ColumnType__Group_0__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ColumnType__Group_0__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getColumnTypeAccess().getGroup_0()); 

                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalHdbTable.g:2942:3: ({...}? => ( ( ( rule__ColumnType__Group_1__0 ) ) ) )
                    {
                    // InternalHdbTable.g:2942:3: ({...}? => ( ( ( rule__ColumnType__Group_1__0 ) ) ) )
                    // InternalHdbTable.g:2943:4: {...}? => ( ( ( rule__ColumnType__Group_1__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 1) ) {
                        throw new FailedPredicateException(input, "rule__ColumnType__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 1)");
                    }
                    // InternalHdbTable.g:2943:104: ( ( ( rule__ColumnType__Group_1__0 ) ) )
                    // InternalHdbTable.g:2944:5: ( ( rule__ColumnType__Group_1__0 ) )
                    {

                    					getUnorderedGroupHelper().select(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 1);
                    				

                    					selected = true;
                    				
                    // InternalHdbTable.g:2950:5: ( ( rule__ColumnType__Group_1__0 ) )
                    // InternalHdbTable.g:2951:6: ( rule__ColumnType__Group_1__0 )
                    {
                     before(grammarAccess.getColumnTypeAccess().getGroup_1()); 
                    // InternalHdbTable.g:2952:6: ( rule__ColumnType__Group_1__0 )
                    // InternalHdbTable.g:2952:7: rule__ColumnType__Group_1__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ColumnType__Group_1__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getColumnTypeAccess().getGroup_1()); 

                    }


                    }


                    }


                    }
                    break;
                case 3 :
                    // InternalHdbTable.g:2957:3: ({...}? => ( ( ( rule__ColumnType__Group_2__0 ) ) ) )
                    {
                    // InternalHdbTable.g:2957:3: ({...}? => ( ( ( rule__ColumnType__Group_2__0 ) ) ) )
                    // InternalHdbTable.g:2958:4: {...}? => ( ( ( rule__ColumnType__Group_2__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 2) ) {
                        throw new FailedPredicateException(input, "rule__ColumnType__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 2)");
                    }
                    // InternalHdbTable.g:2958:104: ( ( ( rule__ColumnType__Group_2__0 ) ) )
                    // InternalHdbTable.g:2959:5: ( ( rule__ColumnType__Group_2__0 ) )
                    {

                    					getUnorderedGroupHelper().select(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 2);
                    				

                    					selected = true;
                    				
                    // InternalHdbTable.g:2965:5: ( ( rule__ColumnType__Group_2__0 ) )
                    // InternalHdbTable.g:2966:6: ( rule__ColumnType__Group_2__0 )
                    {
                     before(grammarAccess.getColumnTypeAccess().getGroup_2()); 
                    // InternalHdbTable.g:2967:6: ( rule__ColumnType__Group_2__0 )
                    // InternalHdbTable.g:2967:7: rule__ColumnType__Group_2__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ColumnType__Group_2__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getColumnTypeAccess().getGroup_2()); 

                    }


                    }


                    }


                    }
                    break;
                case 4 :
                    // InternalHdbTable.g:2972:3: ({...}? => ( ( ( rule__ColumnType__Group_3__0 ) ) ) )
                    {
                    // InternalHdbTable.g:2972:3: ({...}? => ( ( ( rule__ColumnType__Group_3__0 ) ) ) )
                    // InternalHdbTable.g:2973:4: {...}? => ( ( ( rule__ColumnType__Group_3__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 3) ) {
                        throw new FailedPredicateException(input, "rule__ColumnType__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 3)");
                    }
                    // InternalHdbTable.g:2973:104: ( ( ( rule__ColumnType__Group_3__0 ) ) )
                    // InternalHdbTable.g:2974:5: ( ( rule__ColumnType__Group_3__0 ) )
                    {

                    					getUnorderedGroupHelper().select(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 3);
                    				

                    					selected = true;
                    				
                    // InternalHdbTable.g:2980:5: ( ( rule__ColumnType__Group_3__0 ) )
                    // InternalHdbTable.g:2981:6: ( rule__ColumnType__Group_3__0 )
                    {
                     before(grammarAccess.getColumnTypeAccess().getGroup_3()); 
                    // InternalHdbTable.g:2982:6: ( rule__ColumnType__Group_3__0 )
                    // InternalHdbTable.g:2982:7: rule__ColumnType__Group_3__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ColumnType__Group_3__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getColumnTypeAccess().getGroup_3()); 

                    }


                    }


                    }


                    }
                    break;
                case 5 :
                    // InternalHdbTable.g:2987:3: ({...}? => ( ( ( rule__ColumnType__Group_4__0 ) ) ) )
                    {
                    // InternalHdbTable.g:2987:3: ({...}? => ( ( ( rule__ColumnType__Group_4__0 ) ) ) )
                    // InternalHdbTable.g:2988:4: {...}? => ( ( ( rule__ColumnType__Group_4__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 4) ) {
                        throw new FailedPredicateException(input, "rule__ColumnType__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 4)");
                    }
                    // InternalHdbTable.g:2988:104: ( ( ( rule__ColumnType__Group_4__0 ) ) )
                    // InternalHdbTable.g:2989:5: ( ( rule__ColumnType__Group_4__0 ) )
                    {

                    					getUnorderedGroupHelper().select(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 4);
                    				

                    					selected = true;
                    				
                    // InternalHdbTable.g:2995:5: ( ( rule__ColumnType__Group_4__0 ) )
                    // InternalHdbTable.g:2996:6: ( rule__ColumnType__Group_4__0 )
                    {
                     before(grammarAccess.getColumnTypeAccess().getGroup_4()); 
                    // InternalHdbTable.g:2997:6: ( rule__ColumnType__Group_4__0 )
                    // InternalHdbTable.g:2997:7: rule__ColumnType__Group_4__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ColumnType__Group_4__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getColumnTypeAccess().getGroup_4()); 

                    }


                    }


                    }


                    }
                    break;
                case 6 :
                    // InternalHdbTable.g:3002:3: ({...}? => ( ( ( rule__ColumnType__Group_5__0 ) ) ) )
                    {
                    // InternalHdbTable.g:3002:3: ({...}? => ( ( ( rule__ColumnType__Group_5__0 ) ) ) )
                    // InternalHdbTable.g:3003:4: {...}? => ( ( ( rule__ColumnType__Group_5__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 5) ) {
                        throw new FailedPredicateException(input, "rule__ColumnType__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 5)");
                    }
                    // InternalHdbTable.g:3003:104: ( ( ( rule__ColumnType__Group_5__0 ) ) )
                    // InternalHdbTable.g:3004:5: ( ( rule__ColumnType__Group_5__0 ) )
                    {

                    					getUnorderedGroupHelper().select(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 5);
                    				

                    					selected = true;
                    				
                    // InternalHdbTable.g:3010:5: ( ( rule__ColumnType__Group_5__0 ) )
                    // InternalHdbTable.g:3011:6: ( rule__ColumnType__Group_5__0 )
                    {
                     before(grammarAccess.getColumnTypeAccess().getGroup_5()); 
                    // InternalHdbTable.g:3012:6: ( rule__ColumnType__Group_5__0 )
                    // InternalHdbTable.g:3012:7: rule__ColumnType__Group_5__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ColumnType__Group_5__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getColumnTypeAccess().getGroup_5()); 

                    }


                    }


                    }


                    }
                    break;
                case 7 :
                    // InternalHdbTable.g:3017:3: ({...}? => ( ( ( rule__ColumnType__Group_6__0 ) ) ) )
                    {
                    // InternalHdbTable.g:3017:3: ({...}? => ( ( ( rule__ColumnType__Group_6__0 ) ) ) )
                    // InternalHdbTable.g:3018:4: {...}? => ( ( ( rule__ColumnType__Group_6__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 6) ) {
                        throw new FailedPredicateException(input, "rule__ColumnType__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 6)");
                    }
                    // InternalHdbTable.g:3018:104: ( ( ( rule__ColumnType__Group_6__0 ) ) )
                    // InternalHdbTable.g:3019:5: ( ( rule__ColumnType__Group_6__0 ) )
                    {

                    					getUnorderedGroupHelper().select(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 6);
                    				

                    					selected = true;
                    				
                    // InternalHdbTable.g:3025:5: ( ( rule__ColumnType__Group_6__0 ) )
                    // InternalHdbTable.g:3026:6: ( rule__ColumnType__Group_6__0 )
                    {
                     before(grammarAccess.getColumnTypeAccess().getGroup_6()); 
                    // InternalHdbTable.g:3027:6: ( rule__ColumnType__Group_6__0 )
                    // InternalHdbTable.g:3027:7: rule__ColumnType__Group_6__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ColumnType__Group_6__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getColumnTypeAccess().getGroup_6()); 

                    }


                    }


                    }


                    }
                    break;
                case 8 :
                    // InternalHdbTable.g:3032:3: ({...}? => ( ( ( rule__ColumnType__Group_7__0 ) ) ) )
                    {
                    // InternalHdbTable.g:3032:3: ({...}? => ( ( ( rule__ColumnType__Group_7__0 ) ) ) )
                    // InternalHdbTable.g:3033:4: {...}? => ( ( ( rule__ColumnType__Group_7__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 7) ) {
                        throw new FailedPredicateException(input, "rule__ColumnType__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 7)");
                    }
                    // InternalHdbTable.g:3033:104: ( ( ( rule__ColumnType__Group_7__0 ) ) )
                    // InternalHdbTable.g:3034:5: ( ( rule__ColumnType__Group_7__0 ) )
                    {

                    					getUnorderedGroupHelper().select(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 7);
                    				

                    					selected = true;
                    				
                    // InternalHdbTable.g:3040:5: ( ( rule__ColumnType__Group_7__0 ) )
                    // InternalHdbTable.g:3041:6: ( rule__ColumnType__Group_7__0 )
                    {
                     before(grammarAccess.getColumnTypeAccess().getGroup_7()); 
                    // InternalHdbTable.g:3042:6: ( rule__ColumnType__Group_7__0 )
                    // InternalHdbTable.g:3042:7: rule__ColumnType__Group_7__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ColumnType__Group_7__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getColumnTypeAccess().getGroup_7()); 

                    }


                    }


                    }


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	if (selected)
            		getUnorderedGroupHelper().returnFromSelection(grammarAccess.getColumnTypeAccess().getUnorderedGroup());
            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__UnorderedGroup__Impl"


    // $ANTLR start "rule__ColumnType__UnorderedGroup__0"
    // InternalHdbTable.g:3055:1: rule__ColumnType__UnorderedGroup__0 : rule__ColumnType__UnorderedGroup__Impl ( rule__ColumnType__UnorderedGroup__1 )? ;
    public final void rule__ColumnType__UnorderedGroup__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:3059:1: ( rule__ColumnType__UnorderedGroup__Impl ( rule__ColumnType__UnorderedGroup__1 )? )
            // InternalHdbTable.g:3060:2: rule__ColumnType__UnorderedGroup__Impl ( rule__ColumnType__UnorderedGroup__1 )?
            {
            pushFollow(FOLLOW_19);
            rule__ColumnType__UnorderedGroup__Impl();

            state._fsp--;

            // InternalHdbTable.g:3061:2: ( rule__ColumnType__UnorderedGroup__1 )?
            int alt12=2;
            alt12 = dfa12.predict(input);
            switch (alt12) {
                case 1 :
                    // InternalHdbTable.g:3061:2: rule__ColumnType__UnorderedGroup__1
                    {
                    pushFollow(FOLLOW_2);
                    rule__ColumnType__UnorderedGroup__1();

                    state._fsp--;


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__UnorderedGroup__0"


    // $ANTLR start "rule__ColumnType__UnorderedGroup__1"
    // InternalHdbTable.g:3067:1: rule__ColumnType__UnorderedGroup__1 : rule__ColumnType__UnorderedGroup__Impl ( rule__ColumnType__UnorderedGroup__2 )? ;
    public final void rule__ColumnType__UnorderedGroup__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:3071:1: ( rule__ColumnType__UnorderedGroup__Impl ( rule__ColumnType__UnorderedGroup__2 )? )
            // InternalHdbTable.g:3072:2: rule__ColumnType__UnorderedGroup__Impl ( rule__ColumnType__UnorderedGroup__2 )?
            {
            pushFollow(FOLLOW_19);
            rule__ColumnType__UnorderedGroup__Impl();

            state._fsp--;

            // InternalHdbTable.g:3073:2: ( rule__ColumnType__UnorderedGroup__2 )?
            int alt13=2;
            alt13 = dfa13.predict(input);
            switch (alt13) {
                case 1 :
                    // InternalHdbTable.g:3073:2: rule__ColumnType__UnorderedGroup__2
                    {
                    pushFollow(FOLLOW_2);
                    rule__ColumnType__UnorderedGroup__2();

                    state._fsp--;


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__UnorderedGroup__1"


    // $ANTLR start "rule__ColumnType__UnorderedGroup__2"
    // InternalHdbTable.g:3079:1: rule__ColumnType__UnorderedGroup__2 : rule__ColumnType__UnorderedGroup__Impl ( rule__ColumnType__UnorderedGroup__3 )? ;
    public final void rule__ColumnType__UnorderedGroup__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:3083:1: ( rule__ColumnType__UnorderedGroup__Impl ( rule__ColumnType__UnorderedGroup__3 )? )
            // InternalHdbTable.g:3084:2: rule__ColumnType__UnorderedGroup__Impl ( rule__ColumnType__UnorderedGroup__3 )?
            {
            pushFollow(FOLLOW_19);
            rule__ColumnType__UnorderedGroup__Impl();

            state._fsp--;

            // InternalHdbTable.g:3085:2: ( rule__ColumnType__UnorderedGroup__3 )?
            int alt14=2;
            alt14 = dfa14.predict(input);
            switch (alt14) {
                case 1 :
                    // InternalHdbTable.g:3085:2: rule__ColumnType__UnorderedGroup__3
                    {
                    pushFollow(FOLLOW_2);
                    rule__ColumnType__UnorderedGroup__3();

                    state._fsp--;


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__UnorderedGroup__2"


    // $ANTLR start "rule__ColumnType__UnorderedGroup__3"
    // InternalHdbTable.g:3091:1: rule__ColumnType__UnorderedGroup__3 : rule__ColumnType__UnorderedGroup__Impl ( rule__ColumnType__UnorderedGroup__4 )? ;
    public final void rule__ColumnType__UnorderedGroup__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:3095:1: ( rule__ColumnType__UnorderedGroup__Impl ( rule__ColumnType__UnorderedGroup__4 )? )
            // InternalHdbTable.g:3096:2: rule__ColumnType__UnorderedGroup__Impl ( rule__ColumnType__UnorderedGroup__4 )?
            {
            pushFollow(FOLLOW_19);
            rule__ColumnType__UnorderedGroup__Impl();

            state._fsp--;

            // InternalHdbTable.g:3097:2: ( rule__ColumnType__UnorderedGroup__4 )?
            int alt15=2;
            alt15 = dfa15.predict(input);
            switch (alt15) {
                case 1 :
                    // InternalHdbTable.g:3097:2: rule__ColumnType__UnorderedGroup__4
                    {
                    pushFollow(FOLLOW_2);
                    rule__ColumnType__UnorderedGroup__4();

                    state._fsp--;


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__UnorderedGroup__3"


    // $ANTLR start "rule__ColumnType__UnorderedGroup__4"
    // InternalHdbTable.g:3103:1: rule__ColumnType__UnorderedGroup__4 : rule__ColumnType__UnorderedGroup__Impl ( rule__ColumnType__UnorderedGroup__5 )? ;
    public final void rule__ColumnType__UnorderedGroup__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:3107:1: ( rule__ColumnType__UnorderedGroup__Impl ( rule__ColumnType__UnorderedGroup__5 )? )
            // InternalHdbTable.g:3108:2: rule__ColumnType__UnorderedGroup__Impl ( rule__ColumnType__UnorderedGroup__5 )?
            {
            pushFollow(FOLLOW_19);
            rule__ColumnType__UnorderedGroup__Impl();

            state._fsp--;

            // InternalHdbTable.g:3109:2: ( rule__ColumnType__UnorderedGroup__5 )?
            int alt16=2;
            alt16 = dfa16.predict(input);
            switch (alt16) {
                case 1 :
                    // InternalHdbTable.g:3109:2: rule__ColumnType__UnorderedGroup__5
                    {
                    pushFollow(FOLLOW_2);
                    rule__ColumnType__UnorderedGroup__5();

                    state._fsp--;


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__UnorderedGroup__4"


    // $ANTLR start "rule__ColumnType__UnorderedGroup__5"
    // InternalHdbTable.g:3115:1: rule__ColumnType__UnorderedGroup__5 : rule__ColumnType__UnorderedGroup__Impl ( rule__ColumnType__UnorderedGroup__6 )? ;
    public final void rule__ColumnType__UnorderedGroup__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:3119:1: ( rule__ColumnType__UnorderedGroup__Impl ( rule__ColumnType__UnorderedGroup__6 )? )
            // InternalHdbTable.g:3120:2: rule__ColumnType__UnorderedGroup__Impl ( rule__ColumnType__UnorderedGroup__6 )?
            {
            pushFollow(FOLLOW_19);
            rule__ColumnType__UnorderedGroup__Impl();

            state._fsp--;

            // InternalHdbTable.g:3121:2: ( rule__ColumnType__UnorderedGroup__6 )?
            int alt17=2;
            alt17 = dfa17.predict(input);
            switch (alt17) {
                case 1 :
                    // InternalHdbTable.g:3121:2: rule__ColumnType__UnorderedGroup__6
                    {
                    pushFollow(FOLLOW_2);
                    rule__ColumnType__UnorderedGroup__6();

                    state._fsp--;


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__UnorderedGroup__5"


    // $ANTLR start "rule__ColumnType__UnorderedGroup__6"
    // InternalHdbTable.g:3127:1: rule__ColumnType__UnorderedGroup__6 : rule__ColumnType__UnorderedGroup__Impl ( rule__ColumnType__UnorderedGroup__7 )? ;
    public final void rule__ColumnType__UnorderedGroup__6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:3131:1: ( rule__ColumnType__UnorderedGroup__Impl ( rule__ColumnType__UnorderedGroup__7 )? )
            // InternalHdbTable.g:3132:2: rule__ColumnType__UnorderedGroup__Impl ( rule__ColumnType__UnorderedGroup__7 )?
            {
            pushFollow(FOLLOW_19);
            rule__ColumnType__UnorderedGroup__Impl();

            state._fsp--;

            // InternalHdbTable.g:3133:2: ( rule__ColumnType__UnorderedGroup__7 )?
            int alt18=2;
            alt18 = dfa18.predict(input);
            switch (alt18) {
                case 1 :
                    // InternalHdbTable.g:3133:2: rule__ColumnType__UnorderedGroup__7
                    {
                    pushFollow(FOLLOW_2);
                    rule__ColumnType__UnorderedGroup__7();

                    state._fsp--;


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__UnorderedGroup__6"


    // $ANTLR start "rule__ColumnType__UnorderedGroup__7"
    // InternalHdbTable.g:3139:1: rule__ColumnType__UnorderedGroup__7 : rule__ColumnType__UnorderedGroup__Impl ;
    public final void rule__ColumnType__UnorderedGroup__7() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:3143:1: ( rule__ColumnType__UnorderedGroup__Impl )
            // InternalHdbTable.g:3144:2: rule__ColumnType__UnorderedGroup__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ColumnType__UnorderedGroup__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__UnorderedGroup__7"


    // $ANTLR start "rule__IndexType__UnorderedGroup"
    // InternalHdbTable.g:3151:1: rule__IndexType__UnorderedGroup : rule__IndexType__UnorderedGroup__0 {...}?;
    public final void rule__IndexType__UnorderedGroup() throws RecognitionException {

        		int stackSize = keepStackSize();
        		getUnorderedGroupHelper().enter(grammarAccess.getIndexTypeAccess().getUnorderedGroup());
        	
        try {
            // InternalHdbTable.g:3156:1: ( rule__IndexType__UnorderedGroup__0 {...}?)
            // InternalHdbTable.g:3157:2: rule__IndexType__UnorderedGroup__0 {...}?
            {
            pushFollow(FOLLOW_2);
            rule__IndexType__UnorderedGroup__0();

            state._fsp--;

            if ( ! getUnorderedGroupHelper().canLeave(grammarAccess.getIndexTypeAccess().getUnorderedGroup()) ) {
                throw new FailedPredicateException(input, "rule__IndexType__UnorderedGroup", "getUnorderedGroupHelper().canLeave(grammarAccess.getIndexTypeAccess().getUnorderedGroup())");
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	getUnorderedGroupHelper().leave(grammarAccess.getIndexTypeAccess().getUnorderedGroup());
            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IndexType__UnorderedGroup"


    // $ANTLR start "rule__IndexType__UnorderedGroup__Impl"
    // InternalHdbTable.g:3165:1: rule__IndexType__UnorderedGroup__Impl : ( ({...}? => ( ( ( rule__IndexType__Group_0__0 ) ) ) ) | ({...}? => ( ( ( rule__IndexType__Group_1__0 ) ) ) ) | ({...}? => ( ( ( rule__IndexType__Group_2__0 ) ) ) ) | ({...}? => ( ( ( rule__IndexType__Group_3__0 ) ) ) ) ) ;
    public final void rule__IndexType__UnorderedGroup__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        		boolean selected = false;
        	
        try {
            // InternalHdbTable.g:3170:1: ( ( ({...}? => ( ( ( rule__IndexType__Group_0__0 ) ) ) ) | ({...}? => ( ( ( rule__IndexType__Group_1__0 ) ) ) ) | ({...}? => ( ( ( rule__IndexType__Group_2__0 ) ) ) ) | ({...}? => ( ( ( rule__IndexType__Group_3__0 ) ) ) ) ) )
            // InternalHdbTable.g:3171:3: ( ({...}? => ( ( ( rule__IndexType__Group_0__0 ) ) ) ) | ({...}? => ( ( ( rule__IndexType__Group_1__0 ) ) ) ) | ({...}? => ( ( ( rule__IndexType__Group_2__0 ) ) ) ) | ({...}? => ( ( ( rule__IndexType__Group_3__0 ) ) ) ) )
            {
            // InternalHdbTable.g:3171:3: ( ({...}? => ( ( ( rule__IndexType__Group_0__0 ) ) ) ) | ({...}? => ( ( ( rule__IndexType__Group_1__0 ) ) ) ) | ({...}? => ( ( ( rule__IndexType__Group_2__0 ) ) ) ) | ({...}? => ( ( ( rule__IndexType__Group_3__0 ) ) ) ) )
            int alt19=4;
            int LA19_0 = input.LA(1);

            if ( LA19_0 == 12 && getUnorderedGroupHelper().canSelect(grammarAccess.getIndexTypeAccess().getUnorderedGroup(), 0) ) {
                alt19=1;
            }
            else if ( LA19_0 == 24 && getUnorderedGroupHelper().canSelect(grammarAccess.getIndexTypeAccess().getUnorderedGroup(), 1) ) {
                alt19=2;
            }
            else if ( LA19_0 == 25 && getUnorderedGroupHelper().canSelect(grammarAccess.getIndexTypeAccess().getUnorderedGroup(), 2) ) {
                alt19=3;
            }
            else if ( LA19_0 == 29 && getUnorderedGroupHelper().canSelect(grammarAccess.getIndexTypeAccess().getUnorderedGroup(), 3) ) {
                alt19=4;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 19, 0, input);

                throw nvae;
            }
            switch (alt19) {
                case 1 :
                    // InternalHdbTable.g:3172:3: ({...}? => ( ( ( rule__IndexType__Group_0__0 ) ) ) )
                    {
                    // InternalHdbTable.g:3172:3: ({...}? => ( ( ( rule__IndexType__Group_0__0 ) ) ) )
                    // InternalHdbTable.g:3173:4: {...}? => ( ( ( rule__IndexType__Group_0__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getIndexTypeAccess().getUnorderedGroup(), 0) ) {
                        throw new FailedPredicateException(input, "rule__IndexType__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getIndexTypeAccess().getUnorderedGroup(), 0)");
                    }
                    // InternalHdbTable.g:3173:103: ( ( ( rule__IndexType__Group_0__0 ) ) )
                    // InternalHdbTable.g:3174:5: ( ( rule__IndexType__Group_0__0 ) )
                    {

                    					getUnorderedGroupHelper().select(grammarAccess.getIndexTypeAccess().getUnorderedGroup(), 0);
                    				

                    					selected = true;
                    				
                    // InternalHdbTable.g:3180:5: ( ( rule__IndexType__Group_0__0 ) )
                    // InternalHdbTable.g:3181:6: ( rule__IndexType__Group_0__0 )
                    {
                     before(grammarAccess.getIndexTypeAccess().getGroup_0()); 
                    // InternalHdbTable.g:3182:6: ( rule__IndexType__Group_0__0 )
                    // InternalHdbTable.g:3182:7: rule__IndexType__Group_0__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__IndexType__Group_0__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getIndexTypeAccess().getGroup_0()); 

                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalHdbTable.g:3187:3: ({...}? => ( ( ( rule__IndexType__Group_1__0 ) ) ) )
                    {
                    // InternalHdbTable.g:3187:3: ({...}? => ( ( ( rule__IndexType__Group_1__0 ) ) ) )
                    // InternalHdbTable.g:3188:4: {...}? => ( ( ( rule__IndexType__Group_1__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getIndexTypeAccess().getUnorderedGroup(), 1) ) {
                        throw new FailedPredicateException(input, "rule__IndexType__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getIndexTypeAccess().getUnorderedGroup(), 1)");
                    }
                    // InternalHdbTable.g:3188:103: ( ( ( rule__IndexType__Group_1__0 ) ) )
                    // InternalHdbTable.g:3189:5: ( ( rule__IndexType__Group_1__0 ) )
                    {

                    					getUnorderedGroupHelper().select(grammarAccess.getIndexTypeAccess().getUnorderedGroup(), 1);
                    				

                    					selected = true;
                    				
                    // InternalHdbTable.g:3195:5: ( ( rule__IndexType__Group_1__0 ) )
                    // InternalHdbTable.g:3196:6: ( rule__IndexType__Group_1__0 )
                    {
                     before(grammarAccess.getIndexTypeAccess().getGroup_1()); 
                    // InternalHdbTable.g:3197:6: ( rule__IndexType__Group_1__0 )
                    // InternalHdbTable.g:3197:7: rule__IndexType__Group_1__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__IndexType__Group_1__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getIndexTypeAccess().getGroup_1()); 

                    }


                    }


                    }


                    }
                    break;
                case 3 :
                    // InternalHdbTable.g:3202:3: ({...}? => ( ( ( rule__IndexType__Group_2__0 ) ) ) )
                    {
                    // InternalHdbTable.g:3202:3: ({...}? => ( ( ( rule__IndexType__Group_2__0 ) ) ) )
                    // InternalHdbTable.g:3203:4: {...}? => ( ( ( rule__IndexType__Group_2__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getIndexTypeAccess().getUnorderedGroup(), 2) ) {
                        throw new FailedPredicateException(input, "rule__IndexType__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getIndexTypeAccess().getUnorderedGroup(), 2)");
                    }
                    // InternalHdbTable.g:3203:103: ( ( ( rule__IndexType__Group_2__0 ) ) )
                    // InternalHdbTable.g:3204:5: ( ( rule__IndexType__Group_2__0 ) )
                    {

                    					getUnorderedGroupHelper().select(grammarAccess.getIndexTypeAccess().getUnorderedGroup(), 2);
                    				

                    					selected = true;
                    				
                    // InternalHdbTable.g:3210:5: ( ( rule__IndexType__Group_2__0 ) )
                    // InternalHdbTable.g:3211:6: ( rule__IndexType__Group_2__0 )
                    {
                     before(grammarAccess.getIndexTypeAccess().getGroup_2()); 
                    // InternalHdbTable.g:3212:6: ( rule__IndexType__Group_2__0 )
                    // InternalHdbTable.g:3212:7: rule__IndexType__Group_2__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__IndexType__Group_2__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getIndexTypeAccess().getGroup_2()); 

                    }


                    }


                    }


                    }
                    break;
                case 4 :
                    // InternalHdbTable.g:3217:3: ({...}? => ( ( ( rule__IndexType__Group_3__0 ) ) ) )
                    {
                    // InternalHdbTable.g:3217:3: ({...}? => ( ( ( rule__IndexType__Group_3__0 ) ) ) )
                    // InternalHdbTable.g:3218:4: {...}? => ( ( ( rule__IndexType__Group_3__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getIndexTypeAccess().getUnorderedGroup(), 3) ) {
                        throw new FailedPredicateException(input, "rule__IndexType__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getIndexTypeAccess().getUnorderedGroup(), 3)");
                    }
                    // InternalHdbTable.g:3218:103: ( ( ( rule__IndexType__Group_3__0 ) ) )
                    // InternalHdbTable.g:3219:5: ( ( rule__IndexType__Group_3__0 ) )
                    {

                    					getUnorderedGroupHelper().select(grammarAccess.getIndexTypeAccess().getUnorderedGroup(), 3);
                    				

                    					selected = true;
                    				
                    // InternalHdbTable.g:3225:5: ( ( rule__IndexType__Group_3__0 ) )
                    // InternalHdbTable.g:3226:6: ( rule__IndexType__Group_3__0 )
                    {
                     before(grammarAccess.getIndexTypeAccess().getGroup_3()); 
                    // InternalHdbTable.g:3227:6: ( rule__IndexType__Group_3__0 )
                    // InternalHdbTable.g:3227:7: rule__IndexType__Group_3__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__IndexType__Group_3__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getIndexTypeAccess().getGroup_3()); 

                    }


                    }


                    }


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	if (selected)
            		getUnorderedGroupHelper().returnFromSelection(grammarAccess.getIndexTypeAccess().getUnorderedGroup());
            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IndexType__UnorderedGroup__Impl"


    // $ANTLR start "rule__IndexType__UnorderedGroup__0"
    // InternalHdbTable.g:3240:1: rule__IndexType__UnorderedGroup__0 : rule__IndexType__UnorderedGroup__Impl ( rule__IndexType__UnorderedGroup__1 )? ;
    public final void rule__IndexType__UnorderedGroup__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:3244:1: ( rule__IndexType__UnorderedGroup__Impl ( rule__IndexType__UnorderedGroup__1 )? )
            // InternalHdbTable.g:3245:2: rule__IndexType__UnorderedGroup__Impl ( rule__IndexType__UnorderedGroup__1 )?
            {
            pushFollow(FOLLOW_20);
            rule__IndexType__UnorderedGroup__Impl();

            state._fsp--;

            // InternalHdbTable.g:3246:2: ( rule__IndexType__UnorderedGroup__1 )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( LA20_0 == 12 && getUnorderedGroupHelper().canSelect(grammarAccess.getIndexTypeAccess().getUnorderedGroup(), 0) ) {
                alt20=1;
            }
            else if ( LA20_0 == 24 && getUnorderedGroupHelper().canSelect(grammarAccess.getIndexTypeAccess().getUnorderedGroup(), 1) ) {
                alt20=1;
            }
            else if ( LA20_0 == 25 && getUnorderedGroupHelper().canSelect(grammarAccess.getIndexTypeAccess().getUnorderedGroup(), 2) ) {
                alt20=1;
            }
            else if ( LA20_0 == 29 && getUnorderedGroupHelper().canSelect(grammarAccess.getIndexTypeAccess().getUnorderedGroup(), 3) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // InternalHdbTable.g:3246:2: rule__IndexType__UnorderedGroup__1
                    {
                    pushFollow(FOLLOW_2);
                    rule__IndexType__UnorderedGroup__1();

                    state._fsp--;


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IndexType__UnorderedGroup__0"


    // $ANTLR start "rule__IndexType__UnorderedGroup__1"
    // InternalHdbTable.g:3252:1: rule__IndexType__UnorderedGroup__1 : rule__IndexType__UnorderedGroup__Impl ( rule__IndexType__UnorderedGroup__2 )? ;
    public final void rule__IndexType__UnorderedGroup__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:3256:1: ( rule__IndexType__UnorderedGroup__Impl ( rule__IndexType__UnorderedGroup__2 )? )
            // InternalHdbTable.g:3257:2: rule__IndexType__UnorderedGroup__Impl ( rule__IndexType__UnorderedGroup__2 )?
            {
            pushFollow(FOLLOW_20);
            rule__IndexType__UnorderedGroup__Impl();

            state._fsp--;

            // InternalHdbTable.g:3258:2: ( rule__IndexType__UnorderedGroup__2 )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( LA21_0 == 12 && getUnorderedGroupHelper().canSelect(grammarAccess.getIndexTypeAccess().getUnorderedGroup(), 0) ) {
                alt21=1;
            }
            else if ( LA21_0 == 24 && getUnorderedGroupHelper().canSelect(grammarAccess.getIndexTypeAccess().getUnorderedGroup(), 1) ) {
                alt21=1;
            }
            else if ( LA21_0 == 25 && getUnorderedGroupHelper().canSelect(grammarAccess.getIndexTypeAccess().getUnorderedGroup(), 2) ) {
                alt21=1;
            }
            else if ( LA21_0 == 29 && getUnorderedGroupHelper().canSelect(grammarAccess.getIndexTypeAccess().getUnorderedGroup(), 3) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // InternalHdbTable.g:3258:2: rule__IndexType__UnorderedGroup__2
                    {
                    pushFollow(FOLLOW_2);
                    rule__IndexType__UnorderedGroup__2();

                    state._fsp--;


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IndexType__UnorderedGroup__1"


    // $ANTLR start "rule__IndexType__UnorderedGroup__2"
    // InternalHdbTable.g:3264:1: rule__IndexType__UnorderedGroup__2 : rule__IndexType__UnorderedGroup__Impl ( rule__IndexType__UnorderedGroup__3 )? ;
    public final void rule__IndexType__UnorderedGroup__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:3268:1: ( rule__IndexType__UnorderedGroup__Impl ( rule__IndexType__UnorderedGroup__3 )? )
            // InternalHdbTable.g:3269:2: rule__IndexType__UnorderedGroup__Impl ( rule__IndexType__UnorderedGroup__3 )?
            {
            pushFollow(FOLLOW_20);
            rule__IndexType__UnorderedGroup__Impl();

            state._fsp--;

            // InternalHdbTable.g:3270:2: ( rule__IndexType__UnorderedGroup__3 )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( LA22_0 == 12 && getUnorderedGroupHelper().canSelect(grammarAccess.getIndexTypeAccess().getUnorderedGroup(), 0) ) {
                alt22=1;
            }
            else if ( LA22_0 == 24 && getUnorderedGroupHelper().canSelect(grammarAccess.getIndexTypeAccess().getUnorderedGroup(), 1) ) {
                alt22=1;
            }
            else if ( LA22_0 == 25 && getUnorderedGroupHelper().canSelect(grammarAccess.getIndexTypeAccess().getUnorderedGroup(), 2) ) {
                alt22=1;
            }
            else if ( LA22_0 == 29 && getUnorderedGroupHelper().canSelect(grammarAccess.getIndexTypeAccess().getUnorderedGroup(), 3) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // InternalHdbTable.g:3270:2: rule__IndexType__UnorderedGroup__3
                    {
                    pushFollow(FOLLOW_2);
                    rule__IndexType__UnorderedGroup__3();

                    state._fsp--;


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IndexType__UnorderedGroup__2"


    // $ANTLR start "rule__IndexType__UnorderedGroup__3"
    // InternalHdbTable.g:3276:1: rule__IndexType__UnorderedGroup__3 : rule__IndexType__UnorderedGroup__Impl ;
    public final void rule__IndexType__UnorderedGroup__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:3280:1: ( rule__IndexType__UnorderedGroup__Impl )
            // InternalHdbTable.g:3281:2: rule__IndexType__UnorderedGroup__Impl
            {
            pushFollow(FOLLOW_2);
            rule__IndexType__UnorderedGroup__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IndexType__UnorderedGroup__3"


    // $ANTLR start "rule__Table__UnorderedGroup"
    // InternalHdbTable.g:3288:1: rule__Table__UnorderedGroup : rule__Table__UnorderedGroup__0 {...}?;
    public final void rule__Table__UnorderedGroup() throws RecognitionException {

        		int stackSize = keepStackSize();
        		getUnorderedGroupHelper().enter(grammarAccess.getTableAccess().getUnorderedGroup());
        	
        try {
            // InternalHdbTable.g:3293:1: ( rule__Table__UnorderedGroup__0 {...}?)
            // InternalHdbTable.g:3294:2: rule__Table__UnorderedGroup__0 {...}?
            {
            pushFollow(FOLLOW_2);
            rule__Table__UnorderedGroup__0();

            state._fsp--;

            if ( ! getUnorderedGroupHelper().canLeave(grammarAccess.getTableAccess().getUnorderedGroup()) ) {
                throw new FailedPredicateException(input, "rule__Table__UnorderedGroup", "getUnorderedGroupHelper().canLeave(grammarAccess.getTableAccess().getUnorderedGroup())");
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	getUnorderedGroupHelper().leave(grammarAccess.getTableAccess().getUnorderedGroup());
            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__UnorderedGroup"


    // $ANTLR start "rule__Table__UnorderedGroup__Impl"
    // InternalHdbTable.g:3302:1: rule__Table__UnorderedGroup__Impl : ( ({...}? => ( ( ( rule__Table__Group_0__0 ) ) ) ) | ({...}? => ( ( ( rule__Table__Group_1__0 ) ) ) ) | ({...}? => ( ( ( rule__Table__Group_2__0 ) ) ) ) | ({...}? => ( ( ( rule__Table__Group_3__0 ) ) ) ) | ({...}? => ( ( ( rule__Table__Group_4__0 ) ) ) ) | ({...}? => ( ( ( rule__Table__Group_5__0 ) ) ) ) ) ;
    public final void rule__Table__UnorderedGroup__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        		boolean selected = false;
        	
        try {
            // InternalHdbTable.g:3307:1: ( ( ({...}? => ( ( ( rule__Table__Group_0__0 ) ) ) ) | ({...}? => ( ( ( rule__Table__Group_1__0 ) ) ) ) | ({...}? => ( ( ( rule__Table__Group_2__0 ) ) ) ) | ({...}? => ( ( ( rule__Table__Group_3__0 ) ) ) ) | ({...}? => ( ( ( rule__Table__Group_4__0 ) ) ) ) | ({...}? => ( ( ( rule__Table__Group_5__0 ) ) ) ) ) )
            // InternalHdbTable.g:3308:3: ( ({...}? => ( ( ( rule__Table__Group_0__0 ) ) ) ) | ({...}? => ( ( ( rule__Table__Group_1__0 ) ) ) ) | ({...}? => ( ( ( rule__Table__Group_2__0 ) ) ) ) | ({...}? => ( ( ( rule__Table__Group_3__0 ) ) ) ) | ({...}? => ( ( ( rule__Table__Group_4__0 ) ) ) ) | ({...}? => ( ( ( rule__Table__Group_5__0 ) ) ) ) )
            {
            // InternalHdbTable.g:3308:3: ( ({...}? => ( ( ( rule__Table__Group_0__0 ) ) ) ) | ({...}? => ( ( ( rule__Table__Group_1__0 ) ) ) ) | ({...}? => ( ( ( rule__Table__Group_2__0 ) ) ) ) | ({...}? => ( ( ( rule__Table__Group_3__0 ) ) ) ) | ({...}? => ( ( ( rule__Table__Group_4__0 ) ) ) ) | ({...}? => ( ( ( rule__Table__Group_5__0 ) ) ) ) )
            int alt23=6;
            int LA23_0 = input.LA(1);

            if ( LA23_0 == 30 && getUnorderedGroupHelper().canSelect(grammarAccess.getTableAccess().getUnorderedGroup(), 0) ) {
                alt23=1;
            }
            else if ( LA23_0 == 31 && getUnorderedGroupHelper().canSelect(grammarAccess.getTableAccess().getUnorderedGroup(), 1) ) {
                alt23=2;
            }
            else if ( LA23_0 == 32 && getUnorderedGroupHelper().canSelect(grammarAccess.getTableAccess().getUnorderedGroup(), 2) ) {
                alt23=3;
            }
            else if ( LA23_0 == 33 && getUnorderedGroupHelper().canSelect(grammarAccess.getTableAccess().getUnorderedGroup(), 3) ) {
                alt23=4;
            }
            else if ( LA23_0 == 34 && getUnorderedGroupHelper().canSelect(grammarAccess.getTableAccess().getUnorderedGroup(), 4) ) {
                alt23=5;
            }
            else if ( ( LA23_0 == 15 || LA23_0 == 35 ) && getUnorderedGroupHelper().canSelect(grammarAccess.getTableAccess().getUnorderedGroup(), 5) ) {
                alt23=6;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 23, 0, input);

                throw nvae;
            }
            switch (alt23) {
                case 1 :
                    // InternalHdbTable.g:3309:3: ({...}? => ( ( ( rule__Table__Group_0__0 ) ) ) )
                    {
                    // InternalHdbTable.g:3309:3: ({...}? => ( ( ( rule__Table__Group_0__0 ) ) ) )
                    // InternalHdbTable.g:3310:4: {...}? => ( ( ( rule__Table__Group_0__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getTableAccess().getUnorderedGroup(), 0) ) {
                        throw new FailedPredicateException(input, "rule__Table__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getTableAccess().getUnorderedGroup(), 0)");
                    }
                    // InternalHdbTable.g:3310:99: ( ( ( rule__Table__Group_0__0 ) ) )
                    // InternalHdbTable.g:3311:5: ( ( rule__Table__Group_0__0 ) )
                    {

                    					getUnorderedGroupHelper().select(grammarAccess.getTableAccess().getUnorderedGroup(), 0);
                    				

                    					selected = true;
                    				
                    // InternalHdbTable.g:3317:5: ( ( rule__Table__Group_0__0 ) )
                    // InternalHdbTable.g:3318:6: ( rule__Table__Group_0__0 )
                    {
                     before(grammarAccess.getTableAccess().getGroup_0()); 
                    // InternalHdbTable.g:3319:6: ( rule__Table__Group_0__0 )
                    // InternalHdbTable.g:3319:7: rule__Table__Group_0__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Table__Group_0__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getTableAccess().getGroup_0()); 

                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalHdbTable.g:3324:3: ({...}? => ( ( ( rule__Table__Group_1__0 ) ) ) )
                    {
                    // InternalHdbTable.g:3324:3: ({...}? => ( ( ( rule__Table__Group_1__0 ) ) ) )
                    // InternalHdbTable.g:3325:4: {...}? => ( ( ( rule__Table__Group_1__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getTableAccess().getUnorderedGroup(), 1) ) {
                        throw new FailedPredicateException(input, "rule__Table__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getTableAccess().getUnorderedGroup(), 1)");
                    }
                    // InternalHdbTable.g:3325:99: ( ( ( rule__Table__Group_1__0 ) ) )
                    // InternalHdbTable.g:3326:5: ( ( rule__Table__Group_1__0 ) )
                    {

                    					getUnorderedGroupHelper().select(grammarAccess.getTableAccess().getUnorderedGroup(), 1);
                    				

                    					selected = true;
                    				
                    // InternalHdbTable.g:3332:5: ( ( rule__Table__Group_1__0 ) )
                    // InternalHdbTable.g:3333:6: ( rule__Table__Group_1__0 )
                    {
                     before(grammarAccess.getTableAccess().getGroup_1()); 
                    // InternalHdbTable.g:3334:6: ( rule__Table__Group_1__0 )
                    // InternalHdbTable.g:3334:7: rule__Table__Group_1__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Table__Group_1__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getTableAccess().getGroup_1()); 

                    }


                    }


                    }


                    }
                    break;
                case 3 :
                    // InternalHdbTable.g:3339:3: ({...}? => ( ( ( rule__Table__Group_2__0 ) ) ) )
                    {
                    // InternalHdbTable.g:3339:3: ({...}? => ( ( ( rule__Table__Group_2__0 ) ) ) )
                    // InternalHdbTable.g:3340:4: {...}? => ( ( ( rule__Table__Group_2__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getTableAccess().getUnorderedGroup(), 2) ) {
                        throw new FailedPredicateException(input, "rule__Table__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getTableAccess().getUnorderedGroup(), 2)");
                    }
                    // InternalHdbTable.g:3340:99: ( ( ( rule__Table__Group_2__0 ) ) )
                    // InternalHdbTable.g:3341:5: ( ( rule__Table__Group_2__0 ) )
                    {

                    					getUnorderedGroupHelper().select(grammarAccess.getTableAccess().getUnorderedGroup(), 2);
                    				

                    					selected = true;
                    				
                    // InternalHdbTable.g:3347:5: ( ( rule__Table__Group_2__0 ) )
                    // InternalHdbTable.g:3348:6: ( rule__Table__Group_2__0 )
                    {
                     before(grammarAccess.getTableAccess().getGroup_2()); 
                    // InternalHdbTable.g:3349:6: ( rule__Table__Group_2__0 )
                    // InternalHdbTable.g:3349:7: rule__Table__Group_2__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Table__Group_2__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getTableAccess().getGroup_2()); 

                    }


                    }


                    }


                    }
                    break;
                case 4 :
                    // InternalHdbTable.g:3354:3: ({...}? => ( ( ( rule__Table__Group_3__0 ) ) ) )
                    {
                    // InternalHdbTable.g:3354:3: ({...}? => ( ( ( rule__Table__Group_3__0 ) ) ) )
                    // InternalHdbTable.g:3355:4: {...}? => ( ( ( rule__Table__Group_3__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getTableAccess().getUnorderedGroup(), 3) ) {
                        throw new FailedPredicateException(input, "rule__Table__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getTableAccess().getUnorderedGroup(), 3)");
                    }
                    // InternalHdbTable.g:3355:99: ( ( ( rule__Table__Group_3__0 ) ) )
                    // InternalHdbTable.g:3356:5: ( ( rule__Table__Group_3__0 ) )
                    {

                    					getUnorderedGroupHelper().select(grammarAccess.getTableAccess().getUnorderedGroup(), 3);
                    				

                    					selected = true;
                    				
                    // InternalHdbTable.g:3362:5: ( ( rule__Table__Group_3__0 ) )
                    // InternalHdbTable.g:3363:6: ( rule__Table__Group_3__0 )
                    {
                     before(grammarAccess.getTableAccess().getGroup_3()); 
                    // InternalHdbTable.g:3364:6: ( rule__Table__Group_3__0 )
                    // InternalHdbTable.g:3364:7: rule__Table__Group_3__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Table__Group_3__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getTableAccess().getGroup_3()); 

                    }


                    }


                    }


                    }
                    break;
                case 5 :
                    // InternalHdbTable.g:3369:3: ({...}? => ( ( ( rule__Table__Group_4__0 ) ) ) )
                    {
                    // InternalHdbTable.g:3369:3: ({...}? => ( ( ( rule__Table__Group_4__0 ) ) ) )
                    // InternalHdbTable.g:3370:4: {...}? => ( ( ( rule__Table__Group_4__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getTableAccess().getUnorderedGroup(), 4) ) {
                        throw new FailedPredicateException(input, "rule__Table__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getTableAccess().getUnorderedGroup(), 4)");
                    }
                    // InternalHdbTable.g:3370:99: ( ( ( rule__Table__Group_4__0 ) ) )
                    // InternalHdbTable.g:3371:5: ( ( rule__Table__Group_4__0 ) )
                    {

                    					getUnorderedGroupHelper().select(grammarAccess.getTableAccess().getUnorderedGroup(), 4);
                    				

                    					selected = true;
                    				
                    // InternalHdbTable.g:3377:5: ( ( rule__Table__Group_4__0 ) )
                    // InternalHdbTable.g:3378:6: ( rule__Table__Group_4__0 )
                    {
                     before(grammarAccess.getTableAccess().getGroup_4()); 
                    // InternalHdbTable.g:3379:6: ( rule__Table__Group_4__0 )
                    // InternalHdbTable.g:3379:7: rule__Table__Group_4__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Table__Group_4__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getTableAccess().getGroup_4()); 

                    }


                    }


                    }


                    }
                    break;
                case 6 :
                    // InternalHdbTable.g:3384:3: ({...}? => ( ( ( rule__Table__Group_5__0 ) ) ) )
                    {
                    // InternalHdbTable.g:3384:3: ({...}? => ( ( ( rule__Table__Group_5__0 ) ) ) )
                    // InternalHdbTable.g:3385:4: {...}? => ( ( ( rule__Table__Group_5__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getTableAccess().getUnorderedGroup(), 5) ) {
                        throw new FailedPredicateException(input, "rule__Table__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getTableAccess().getUnorderedGroup(), 5)");
                    }
                    // InternalHdbTable.g:3385:99: ( ( ( rule__Table__Group_5__0 ) ) )
                    // InternalHdbTable.g:3386:5: ( ( rule__Table__Group_5__0 ) )
                    {

                    					getUnorderedGroupHelper().select(grammarAccess.getTableAccess().getUnorderedGroup(), 5);
                    				

                    					selected = true;
                    				
                    // InternalHdbTable.g:3392:5: ( ( rule__Table__Group_5__0 ) )
                    // InternalHdbTable.g:3393:6: ( rule__Table__Group_5__0 )
                    {
                     before(grammarAccess.getTableAccess().getGroup_5()); 
                    // InternalHdbTable.g:3394:6: ( rule__Table__Group_5__0 )
                    // InternalHdbTable.g:3394:7: rule__Table__Group_5__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Table__Group_5__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getTableAccess().getGroup_5()); 

                    }


                    }


                    }


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	if (selected)
            		getUnorderedGroupHelper().returnFromSelection(grammarAccess.getTableAccess().getUnorderedGroup());
            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__UnorderedGroup__Impl"


    // $ANTLR start "rule__Table__UnorderedGroup__0"
    // InternalHdbTable.g:3407:1: rule__Table__UnorderedGroup__0 : rule__Table__UnorderedGroup__Impl ( rule__Table__UnorderedGroup__1 )? ;
    public final void rule__Table__UnorderedGroup__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:3411:1: ( rule__Table__UnorderedGroup__Impl ( rule__Table__UnorderedGroup__1 )? )
            // InternalHdbTable.g:3412:2: rule__Table__UnorderedGroup__Impl ( rule__Table__UnorderedGroup__1 )?
            {
            pushFollow(FOLLOW_21);
            rule__Table__UnorderedGroup__Impl();

            state._fsp--;

            // InternalHdbTable.g:3413:2: ( rule__Table__UnorderedGroup__1 )?
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( LA24_0 == 30 && getUnorderedGroupHelper().canSelect(grammarAccess.getTableAccess().getUnorderedGroup(), 0) ) {
                alt24=1;
            }
            else if ( LA24_0 == 31 && getUnorderedGroupHelper().canSelect(grammarAccess.getTableAccess().getUnorderedGroup(), 1) ) {
                alt24=1;
            }
            else if ( LA24_0 == 32 && getUnorderedGroupHelper().canSelect(grammarAccess.getTableAccess().getUnorderedGroup(), 2) ) {
                alt24=1;
            }
            else if ( LA24_0 == 33 && getUnorderedGroupHelper().canSelect(grammarAccess.getTableAccess().getUnorderedGroup(), 3) ) {
                alt24=1;
            }
            else if ( LA24_0 == 34 && getUnorderedGroupHelper().canSelect(grammarAccess.getTableAccess().getUnorderedGroup(), 4) ) {
                alt24=1;
            }
            else if ( ( LA24_0 == 15 || LA24_0 == 35 ) && getUnorderedGroupHelper().canSelect(grammarAccess.getTableAccess().getUnorderedGroup(), 5) ) {
                alt24=1;
            }
            switch (alt24) {
                case 1 :
                    // InternalHdbTable.g:3413:2: rule__Table__UnorderedGroup__1
                    {
                    pushFollow(FOLLOW_2);
                    rule__Table__UnorderedGroup__1();

                    state._fsp--;


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__UnorderedGroup__0"


    // $ANTLR start "rule__Table__UnorderedGroup__1"
    // InternalHdbTable.g:3419:1: rule__Table__UnorderedGroup__1 : rule__Table__UnorderedGroup__Impl ( rule__Table__UnorderedGroup__2 )? ;
    public final void rule__Table__UnorderedGroup__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:3423:1: ( rule__Table__UnorderedGroup__Impl ( rule__Table__UnorderedGroup__2 )? )
            // InternalHdbTable.g:3424:2: rule__Table__UnorderedGroup__Impl ( rule__Table__UnorderedGroup__2 )?
            {
            pushFollow(FOLLOW_21);
            rule__Table__UnorderedGroup__Impl();

            state._fsp--;

            // InternalHdbTable.g:3425:2: ( rule__Table__UnorderedGroup__2 )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( LA25_0 == 30 && getUnorderedGroupHelper().canSelect(grammarAccess.getTableAccess().getUnorderedGroup(), 0) ) {
                alt25=1;
            }
            else if ( LA25_0 == 31 && getUnorderedGroupHelper().canSelect(grammarAccess.getTableAccess().getUnorderedGroup(), 1) ) {
                alt25=1;
            }
            else if ( LA25_0 == 32 && getUnorderedGroupHelper().canSelect(grammarAccess.getTableAccess().getUnorderedGroup(), 2) ) {
                alt25=1;
            }
            else if ( LA25_0 == 33 && getUnorderedGroupHelper().canSelect(grammarAccess.getTableAccess().getUnorderedGroup(), 3) ) {
                alt25=1;
            }
            else if ( LA25_0 == 34 && getUnorderedGroupHelper().canSelect(grammarAccess.getTableAccess().getUnorderedGroup(), 4) ) {
                alt25=1;
            }
            else if ( ( LA25_0 == 15 || LA25_0 == 35 ) && getUnorderedGroupHelper().canSelect(grammarAccess.getTableAccess().getUnorderedGroup(), 5) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // InternalHdbTable.g:3425:2: rule__Table__UnorderedGroup__2
                    {
                    pushFollow(FOLLOW_2);
                    rule__Table__UnorderedGroup__2();

                    state._fsp--;


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__UnorderedGroup__1"


    // $ANTLR start "rule__Table__UnorderedGroup__2"
    // InternalHdbTable.g:3431:1: rule__Table__UnorderedGroup__2 : rule__Table__UnorderedGroup__Impl ( rule__Table__UnorderedGroup__3 )? ;
    public final void rule__Table__UnorderedGroup__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:3435:1: ( rule__Table__UnorderedGroup__Impl ( rule__Table__UnorderedGroup__3 )? )
            // InternalHdbTable.g:3436:2: rule__Table__UnorderedGroup__Impl ( rule__Table__UnorderedGroup__3 )?
            {
            pushFollow(FOLLOW_21);
            rule__Table__UnorderedGroup__Impl();

            state._fsp--;

            // InternalHdbTable.g:3437:2: ( rule__Table__UnorderedGroup__3 )?
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( LA26_0 == 30 && getUnorderedGroupHelper().canSelect(grammarAccess.getTableAccess().getUnorderedGroup(), 0) ) {
                alt26=1;
            }
            else if ( LA26_0 == 31 && getUnorderedGroupHelper().canSelect(grammarAccess.getTableAccess().getUnorderedGroup(), 1) ) {
                alt26=1;
            }
            else if ( LA26_0 == 32 && getUnorderedGroupHelper().canSelect(grammarAccess.getTableAccess().getUnorderedGroup(), 2) ) {
                alt26=1;
            }
            else if ( LA26_0 == 33 && getUnorderedGroupHelper().canSelect(grammarAccess.getTableAccess().getUnorderedGroup(), 3) ) {
                alt26=1;
            }
            else if ( LA26_0 == 34 && getUnorderedGroupHelper().canSelect(grammarAccess.getTableAccess().getUnorderedGroup(), 4) ) {
                alt26=1;
            }
            else if ( ( LA26_0 == 15 || LA26_0 == 35 ) && getUnorderedGroupHelper().canSelect(grammarAccess.getTableAccess().getUnorderedGroup(), 5) ) {
                alt26=1;
            }
            switch (alt26) {
                case 1 :
                    // InternalHdbTable.g:3437:2: rule__Table__UnorderedGroup__3
                    {
                    pushFollow(FOLLOW_2);
                    rule__Table__UnorderedGroup__3();

                    state._fsp--;


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__UnorderedGroup__2"


    // $ANTLR start "rule__Table__UnorderedGroup__3"
    // InternalHdbTable.g:3443:1: rule__Table__UnorderedGroup__3 : rule__Table__UnorderedGroup__Impl ( rule__Table__UnorderedGroup__4 )? ;
    public final void rule__Table__UnorderedGroup__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:3447:1: ( rule__Table__UnorderedGroup__Impl ( rule__Table__UnorderedGroup__4 )? )
            // InternalHdbTable.g:3448:2: rule__Table__UnorderedGroup__Impl ( rule__Table__UnorderedGroup__4 )?
            {
            pushFollow(FOLLOW_21);
            rule__Table__UnorderedGroup__Impl();

            state._fsp--;

            // InternalHdbTable.g:3449:2: ( rule__Table__UnorderedGroup__4 )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( LA27_0 == 30 && getUnorderedGroupHelper().canSelect(grammarAccess.getTableAccess().getUnorderedGroup(), 0) ) {
                alt27=1;
            }
            else if ( LA27_0 == 31 && getUnorderedGroupHelper().canSelect(grammarAccess.getTableAccess().getUnorderedGroup(), 1) ) {
                alt27=1;
            }
            else if ( LA27_0 == 32 && getUnorderedGroupHelper().canSelect(grammarAccess.getTableAccess().getUnorderedGroup(), 2) ) {
                alt27=1;
            }
            else if ( LA27_0 == 33 && getUnorderedGroupHelper().canSelect(grammarAccess.getTableAccess().getUnorderedGroup(), 3) ) {
                alt27=1;
            }
            else if ( LA27_0 == 34 && getUnorderedGroupHelper().canSelect(grammarAccess.getTableAccess().getUnorderedGroup(), 4) ) {
                alt27=1;
            }
            else if ( ( LA27_0 == 15 || LA27_0 == 35 ) && getUnorderedGroupHelper().canSelect(grammarAccess.getTableAccess().getUnorderedGroup(), 5) ) {
                alt27=1;
            }
            switch (alt27) {
                case 1 :
                    // InternalHdbTable.g:3449:2: rule__Table__UnorderedGroup__4
                    {
                    pushFollow(FOLLOW_2);
                    rule__Table__UnorderedGroup__4();

                    state._fsp--;


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__UnorderedGroup__3"


    // $ANTLR start "rule__Table__UnorderedGroup__4"
    // InternalHdbTable.g:3455:1: rule__Table__UnorderedGroup__4 : rule__Table__UnorderedGroup__Impl ( rule__Table__UnorderedGroup__5 )? ;
    public final void rule__Table__UnorderedGroup__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:3459:1: ( rule__Table__UnorderedGroup__Impl ( rule__Table__UnorderedGroup__5 )? )
            // InternalHdbTable.g:3460:2: rule__Table__UnorderedGroup__Impl ( rule__Table__UnorderedGroup__5 )?
            {
            pushFollow(FOLLOW_21);
            rule__Table__UnorderedGroup__Impl();

            state._fsp--;

            // InternalHdbTable.g:3461:2: ( rule__Table__UnorderedGroup__5 )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( LA28_0 == 30 && getUnorderedGroupHelper().canSelect(grammarAccess.getTableAccess().getUnorderedGroup(), 0) ) {
                alt28=1;
            }
            else if ( LA28_0 == 31 && getUnorderedGroupHelper().canSelect(grammarAccess.getTableAccess().getUnorderedGroup(), 1) ) {
                alt28=1;
            }
            else if ( LA28_0 == 32 && getUnorderedGroupHelper().canSelect(grammarAccess.getTableAccess().getUnorderedGroup(), 2) ) {
                alt28=1;
            }
            else if ( LA28_0 == 33 && getUnorderedGroupHelper().canSelect(grammarAccess.getTableAccess().getUnorderedGroup(), 3) ) {
                alt28=1;
            }
            else if ( LA28_0 == 34 && getUnorderedGroupHelper().canSelect(grammarAccess.getTableAccess().getUnorderedGroup(), 4) ) {
                alt28=1;
            }
            else if ( ( LA28_0 == 15 || LA28_0 == 35 ) && getUnorderedGroupHelper().canSelect(grammarAccess.getTableAccess().getUnorderedGroup(), 5) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // InternalHdbTable.g:3461:2: rule__Table__UnorderedGroup__5
                    {
                    pushFollow(FOLLOW_2);
                    rule__Table__UnorderedGroup__5();

                    state._fsp--;


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__UnorderedGroup__4"


    // $ANTLR start "rule__Table__UnorderedGroup__5"
    // InternalHdbTable.g:3467:1: rule__Table__UnorderedGroup__5 : rule__Table__UnorderedGroup__Impl ;
    public final void rule__Table__UnorderedGroup__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:3471:1: ( rule__Table__UnorderedGroup__Impl )
            // InternalHdbTable.g:3472:2: rule__Table__UnorderedGroup__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Table__UnorderedGroup__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__UnorderedGroup__5"


    // $ANTLR start "rule__HdbTableModel__TableElementAssignment"
    // InternalHdbTable.g:3479:1: rule__HdbTableModel__TableElementAssignment : ( ruleTable ) ;
    public final void rule__HdbTableModel__TableElementAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:3483:1: ( ( ruleTable ) )
            // InternalHdbTable.g:3484:2: ( ruleTable )
            {
            // InternalHdbTable.g:3484:2: ( ruleTable )
            // InternalHdbTable.g:3485:3: ruleTable
            {
             before(grammarAccess.getHdbTableModelAccess().getTableElementTableParserRuleCall_0()); 
            pushFollow(FOLLOW_2);
            ruleTable();

            state._fsp--;

             after(grammarAccess.getHdbTableModelAccess().getTableElementTableParserRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__HdbTableModel__TableElementAssignment"


    // $ANTLR start "rule__ColumnType__ColumnNameAssignment_0_3"
    // InternalHdbTable.g:3494:1: rule__ColumnType__ColumnNameAssignment_0_3 : ( RULE_STRING ) ;
    public final void rule__ColumnType__ColumnNameAssignment_0_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:3498:1: ( ( RULE_STRING ) )
            // InternalHdbTable.g:3499:2: ( RULE_STRING )
            {
            // InternalHdbTable.g:3499:2: ( RULE_STRING )
            // InternalHdbTable.g:3500:3: RULE_STRING
            {
             before(grammarAccess.getColumnTypeAccess().getColumnNameSTRINGTerminalRuleCall_0_3_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getColumnTypeAccess().getColumnNameSTRINGTerminalRuleCall_0_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__ColumnNameAssignment_0_3"


    // $ANTLR start "rule__ColumnType__ColumnSqlTypeAssignment_1_2"
    // InternalHdbTable.g:3509:1: rule__ColumnType__ColumnSqlTypeAssignment_1_2 : ( RULE_ID ) ;
    public final void rule__ColumnType__ColumnSqlTypeAssignment_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:3513:1: ( ( RULE_ID ) )
            // InternalHdbTable.g:3514:2: ( RULE_ID )
            {
            // InternalHdbTable.g:3514:2: ( RULE_ID )
            // InternalHdbTable.g:3515:3: RULE_ID
            {
             before(grammarAccess.getColumnTypeAccess().getColumnSqlTypeIDTerminalRuleCall_1_2_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getColumnTypeAccess().getColumnSqlTypeIDTerminalRuleCall_1_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__ColumnSqlTypeAssignment_1_2"


    // $ANTLR start "rule__ColumnType__ColumnLengthAssignment_2_2"
    // InternalHdbTable.g:3524:1: rule__ColumnType__ColumnLengthAssignment_2_2 : ( RULE_INT ) ;
    public final void rule__ColumnType__ColumnLengthAssignment_2_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:3528:1: ( ( RULE_INT ) )
            // InternalHdbTable.g:3529:2: ( RULE_INT )
            {
            // InternalHdbTable.g:3529:2: ( RULE_INT )
            // InternalHdbTable.g:3530:3: RULE_INT
            {
             before(grammarAccess.getColumnTypeAccess().getColumnLengthINTTerminalRuleCall_2_2_0()); 
            match(input,RULE_INT,FOLLOW_2); 
             after(grammarAccess.getColumnTypeAccess().getColumnLengthINTTerminalRuleCall_2_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__ColumnLengthAssignment_2_2"


    // $ANTLR start "rule__ColumnType__ColumnCommentAssignment_3_2"
    // InternalHdbTable.g:3539:1: rule__ColumnType__ColumnCommentAssignment_3_2 : ( RULE_STRING ) ;
    public final void rule__ColumnType__ColumnCommentAssignment_3_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:3543:1: ( ( RULE_STRING ) )
            // InternalHdbTable.g:3544:2: ( RULE_STRING )
            {
            // InternalHdbTable.g:3544:2: ( RULE_STRING )
            // InternalHdbTable.g:3545:3: RULE_STRING
            {
             before(grammarAccess.getColumnTypeAccess().getColumnCommentSTRINGTerminalRuleCall_3_2_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getColumnTypeAccess().getColumnCommentSTRINGTerminalRuleCall_3_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__ColumnCommentAssignment_3_2"


    // $ANTLR start "rule__ColumnType__ColumnDefaultValueAssignment_4_2"
    // InternalHdbTable.g:3554:1: rule__ColumnType__ColumnDefaultValueAssignment_4_2 : ( RULE_STRING ) ;
    public final void rule__ColumnType__ColumnDefaultValueAssignment_4_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:3558:1: ( ( RULE_STRING ) )
            // InternalHdbTable.g:3559:2: ( RULE_STRING )
            {
            // InternalHdbTable.g:3559:2: ( RULE_STRING )
            // InternalHdbTable.g:3560:3: RULE_STRING
            {
             before(grammarAccess.getColumnTypeAccess().getColumnDefaultValueSTRINGTerminalRuleCall_4_2_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getColumnTypeAccess().getColumnDefaultValueSTRINGTerminalRuleCall_4_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__ColumnDefaultValueAssignment_4_2"


    // $ANTLR start "rule__ColumnType__ColumnPrecisionAssignment_5_2"
    // InternalHdbTable.g:3569:1: rule__ColumnType__ColumnPrecisionAssignment_5_2 : ( RULE_INT ) ;
    public final void rule__ColumnType__ColumnPrecisionAssignment_5_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:3573:1: ( ( RULE_INT ) )
            // InternalHdbTable.g:3574:2: ( RULE_INT )
            {
            // InternalHdbTable.g:3574:2: ( RULE_INT )
            // InternalHdbTable.g:3575:3: RULE_INT
            {
             before(grammarAccess.getColumnTypeAccess().getColumnPrecisionINTTerminalRuleCall_5_2_0()); 
            match(input,RULE_INT,FOLLOW_2); 
             after(grammarAccess.getColumnTypeAccess().getColumnPrecisionINTTerminalRuleCall_5_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__ColumnPrecisionAssignment_5_2"


    // $ANTLR start "rule__ColumnType__ColumnScaleAssignment_6_2"
    // InternalHdbTable.g:3584:1: rule__ColumnType__ColumnScaleAssignment_6_2 : ( RULE_INT ) ;
    public final void rule__ColumnType__ColumnScaleAssignment_6_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:3588:1: ( ( RULE_INT ) )
            // InternalHdbTable.g:3589:2: ( RULE_INT )
            {
            // InternalHdbTable.g:3589:2: ( RULE_INT )
            // InternalHdbTable.g:3590:3: RULE_INT
            {
             before(grammarAccess.getColumnTypeAccess().getColumnScaleINTTerminalRuleCall_6_2_0()); 
            match(input,RULE_INT,FOLLOW_2); 
             after(grammarAccess.getColumnTypeAccess().getColumnScaleINTTerminalRuleCall_6_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__ColumnScaleAssignment_6_2"


    // $ANTLR start "rule__ColumnType__ColumnNullableAssignment_7_0_2"
    // InternalHdbTable.g:3599:1: rule__ColumnType__ColumnNullableAssignment_7_0_2 : ( RULE_BOOL ) ;
    public final void rule__ColumnType__ColumnNullableAssignment_7_0_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:3603:1: ( ( RULE_BOOL ) )
            // InternalHdbTable.g:3604:2: ( RULE_BOOL )
            {
            // InternalHdbTable.g:3604:2: ( RULE_BOOL )
            // InternalHdbTable.g:3605:3: RULE_BOOL
            {
             before(grammarAccess.getColumnTypeAccess().getColumnNullableBOOLTerminalRuleCall_7_0_2_0()); 
            match(input,RULE_BOOL,FOLLOW_2); 
             after(grammarAccess.getColumnTypeAccess().getColumnNullableBOOLTerminalRuleCall_7_0_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ColumnType__ColumnNullableAssignment_7_0_2"


    // $ANTLR start "rule__IndexType__ColumnNameAssignment_0_3"
    // InternalHdbTable.g:3614:1: rule__IndexType__ColumnNameAssignment_0_3 : ( RULE_STRING ) ;
    public final void rule__IndexType__ColumnNameAssignment_0_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:3618:1: ( ( RULE_STRING ) )
            // InternalHdbTable.g:3619:2: ( RULE_STRING )
            {
            // InternalHdbTable.g:3619:2: ( RULE_STRING )
            // InternalHdbTable.g:3620:3: RULE_STRING
            {
             before(grammarAccess.getIndexTypeAccess().getColumnNameSTRINGTerminalRuleCall_0_3_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getIndexTypeAccess().getColumnNameSTRINGTerminalRuleCall_0_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IndexType__ColumnNameAssignment_0_3"


    // $ANTLR start "rule__IndexType__ColumnUniqueAssignment_1_2"
    // InternalHdbTable.g:3629:1: rule__IndexType__ColumnUniqueAssignment_1_2 : ( RULE_BOOL ) ;
    public final void rule__IndexType__ColumnUniqueAssignment_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:3633:1: ( ( RULE_BOOL ) )
            // InternalHdbTable.g:3634:2: ( RULE_BOOL )
            {
            // InternalHdbTable.g:3634:2: ( RULE_BOOL )
            // InternalHdbTable.g:3635:3: RULE_BOOL
            {
             before(grammarAccess.getIndexTypeAccess().getColumnUniqueBOOLTerminalRuleCall_1_2_0()); 
            match(input,RULE_BOOL,FOLLOW_2); 
             after(grammarAccess.getIndexTypeAccess().getColumnUniqueBOOLTerminalRuleCall_1_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IndexType__ColumnUniqueAssignment_1_2"


    // $ANTLR start "rule__IndexType__ColumnOrderAssignment_2_2"
    // InternalHdbTable.g:3644:1: rule__IndexType__ColumnOrderAssignment_2_2 : ( RULE_ID ) ;
    public final void rule__IndexType__ColumnOrderAssignment_2_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:3648:1: ( ( RULE_ID ) )
            // InternalHdbTable.g:3649:2: ( RULE_ID )
            {
            // InternalHdbTable.g:3649:2: ( RULE_ID )
            // InternalHdbTable.g:3650:3: RULE_ID
            {
             before(grammarAccess.getIndexTypeAccess().getColumnOrderIDTerminalRuleCall_2_2_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getIndexTypeAccess().getColumnOrderIDTerminalRuleCall_2_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IndexType__ColumnOrderAssignment_2_2"


    // $ANTLR start "rule__IndexType__IndexColumnsAssignment_3_0"
    // InternalHdbTable.g:3659:1: rule__IndexType__IndexColumnsAssignment_3_0 : ( ( 'indexColumns' ) ) ;
    public final void rule__IndexType__IndexColumnsAssignment_3_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:3663:1: ( ( ( 'indexColumns' ) ) )
            // InternalHdbTable.g:3664:2: ( ( 'indexColumns' ) )
            {
            // InternalHdbTable.g:3664:2: ( ( 'indexColumns' ) )
            // InternalHdbTable.g:3665:3: ( 'indexColumns' )
            {
             before(grammarAccess.getIndexTypeAccess().getIndexColumnsIndexColumnsKeyword_3_0_0()); 
            // InternalHdbTable.g:3666:3: ( 'indexColumns' )
            // InternalHdbTable.g:3667:4: 'indexColumns'
            {
             before(grammarAccess.getIndexTypeAccess().getIndexColumnsIndexColumnsKeyword_3_0_0()); 
            match(input,29,FOLLOW_2); 
             after(grammarAccess.getIndexTypeAccess().getIndexColumnsIndexColumnsKeyword_3_0_0()); 

            }

             after(grammarAccess.getIndexTypeAccess().getIndexColumnsIndexColumnsKeyword_3_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IndexType__IndexColumnsAssignment_3_0"


    // $ANTLR start "rule__IndexType__TableIndexColumnsValuesAssignment_3_3_0"
    // InternalHdbTable.g:3678:1: rule__IndexType__TableIndexColumnsValuesAssignment_3_3_0 : ( RULE_STRING ) ;
    public final void rule__IndexType__TableIndexColumnsValuesAssignment_3_3_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:3682:1: ( ( RULE_STRING ) )
            // InternalHdbTable.g:3683:2: ( RULE_STRING )
            {
            // InternalHdbTable.g:3683:2: ( RULE_STRING )
            // InternalHdbTable.g:3684:3: RULE_STRING
            {
             before(grammarAccess.getIndexTypeAccess().getTableIndexColumnsValuesSTRINGTerminalRuleCall_3_3_0_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getIndexTypeAccess().getTableIndexColumnsValuesSTRINGTerminalRuleCall_3_3_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IndexType__TableIndexColumnsValuesAssignment_3_3_0"


    // $ANTLR start "rule__IndexType__TableIndexColumnsValuesAssignment_3_3_1_1"
    // InternalHdbTable.g:3693:1: rule__IndexType__TableIndexColumnsValuesAssignment_3_3_1_1 : ( RULE_STRING ) ;
    public final void rule__IndexType__TableIndexColumnsValuesAssignment_3_3_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:3697:1: ( ( RULE_STRING ) )
            // InternalHdbTable.g:3698:2: ( RULE_STRING )
            {
            // InternalHdbTable.g:3698:2: ( RULE_STRING )
            // InternalHdbTable.g:3699:3: RULE_STRING
            {
             before(grammarAccess.getIndexTypeAccess().getTableIndexColumnsValuesSTRINGTerminalRuleCall_3_3_1_1_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getIndexTypeAccess().getTableIndexColumnsValuesSTRINGTerminalRuleCall_3_3_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IndexType__TableIndexColumnsValuesAssignment_3_3_1_1"


    // $ANTLR start "rule__Table__SchemaAssignment_0_0"
    // InternalHdbTable.g:3708:1: rule__Table__SchemaAssignment_0_0 : ( ( 'table.schemaName' ) ) ;
    public final void rule__Table__SchemaAssignment_0_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:3712:1: ( ( ( 'table.schemaName' ) ) )
            // InternalHdbTable.g:3713:2: ( ( 'table.schemaName' ) )
            {
            // InternalHdbTable.g:3713:2: ( ( 'table.schemaName' ) )
            // InternalHdbTable.g:3714:3: ( 'table.schemaName' )
            {
             before(grammarAccess.getTableAccess().getSchemaTableSchemaNameKeyword_0_0_0()); 
            // InternalHdbTable.g:3715:3: ( 'table.schemaName' )
            // InternalHdbTable.g:3716:4: 'table.schemaName'
            {
             before(grammarAccess.getTableAccess().getSchemaTableSchemaNameKeyword_0_0_0()); 
            match(input,30,FOLLOW_2); 
             after(grammarAccess.getTableAccess().getSchemaTableSchemaNameKeyword_0_0_0()); 

            }

             after(grammarAccess.getTableAccess().getSchemaTableSchemaNameKeyword_0_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__SchemaAssignment_0_0"


    // $ANTLR start "rule__Table__SchemaNameAssignment_0_2"
    // InternalHdbTable.g:3727:1: rule__Table__SchemaNameAssignment_0_2 : ( RULE_STRING ) ;
    public final void rule__Table__SchemaNameAssignment_0_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:3731:1: ( ( RULE_STRING ) )
            // InternalHdbTable.g:3732:2: ( RULE_STRING )
            {
            // InternalHdbTable.g:3732:2: ( RULE_STRING )
            // InternalHdbTable.g:3733:3: RULE_STRING
            {
             before(grammarAccess.getTableAccess().getSchemaNameSTRINGTerminalRuleCall_0_2_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getTableAccess().getSchemaNameSTRINGTerminalRuleCall_0_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__SchemaNameAssignment_0_2"


    // $ANTLR start "rule__Table__TypeAssignment_1_0"
    // InternalHdbTable.g:3742:1: rule__Table__TypeAssignment_1_0 : ( ( 'table.tableType' ) ) ;
    public final void rule__Table__TypeAssignment_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:3746:1: ( ( ( 'table.tableType' ) ) )
            // InternalHdbTable.g:3747:2: ( ( 'table.tableType' ) )
            {
            // InternalHdbTable.g:3747:2: ( ( 'table.tableType' ) )
            // InternalHdbTable.g:3748:3: ( 'table.tableType' )
            {
             before(grammarAccess.getTableAccess().getTypeTableTableTypeKeyword_1_0_0()); 
            // InternalHdbTable.g:3749:3: ( 'table.tableType' )
            // InternalHdbTable.g:3750:4: 'table.tableType'
            {
             before(grammarAccess.getTableAccess().getTypeTableTableTypeKeyword_1_0_0()); 
            match(input,31,FOLLOW_2); 
             after(grammarAccess.getTableAccess().getTypeTableTableTypeKeyword_1_0_0()); 

            }

             after(grammarAccess.getTableAccess().getTypeTableTableTypeKeyword_1_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__TypeAssignment_1_0"


    // $ANTLR start "rule__Table__TypeValueAssignment_1_2"
    // InternalHdbTable.g:3761:1: rule__Table__TypeValueAssignment_1_2 : ( RULE_ID ) ;
    public final void rule__Table__TypeValueAssignment_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:3765:1: ( ( RULE_ID ) )
            // InternalHdbTable.g:3766:2: ( RULE_ID )
            {
            // InternalHdbTable.g:3766:2: ( RULE_ID )
            // InternalHdbTable.g:3767:3: RULE_ID
            {
             before(grammarAccess.getTableAccess().getTypeValueIDTerminalRuleCall_1_2_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getTableAccess().getTypeValueIDTerminalRuleCall_1_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__TypeValueAssignment_1_2"


    // $ANTLR start "rule__Table__DescriptionAssignment_2_0"
    // InternalHdbTable.g:3776:1: rule__Table__DescriptionAssignment_2_0 : ( ( 'table.description' ) ) ;
    public final void rule__Table__DescriptionAssignment_2_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:3780:1: ( ( ( 'table.description' ) ) )
            // InternalHdbTable.g:3781:2: ( ( 'table.description' ) )
            {
            // InternalHdbTable.g:3781:2: ( ( 'table.description' ) )
            // InternalHdbTable.g:3782:3: ( 'table.description' )
            {
             before(grammarAccess.getTableAccess().getDescriptionTableDescriptionKeyword_2_0_0()); 
            // InternalHdbTable.g:3783:3: ( 'table.description' )
            // InternalHdbTable.g:3784:4: 'table.description'
            {
             before(grammarAccess.getTableAccess().getDescriptionTableDescriptionKeyword_2_0_0()); 
            match(input,32,FOLLOW_2); 
             after(grammarAccess.getTableAccess().getDescriptionTableDescriptionKeyword_2_0_0()); 

            }

             after(grammarAccess.getTableAccess().getDescriptionTableDescriptionKeyword_2_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__DescriptionAssignment_2_0"


    // $ANTLR start "rule__Table__DescriptionTextAssignment_2_2"
    // InternalHdbTable.g:3795:1: rule__Table__DescriptionTextAssignment_2_2 : ( RULE_STRING ) ;
    public final void rule__Table__DescriptionTextAssignment_2_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:3799:1: ( ( RULE_STRING ) )
            // InternalHdbTable.g:3800:2: ( RULE_STRING )
            {
            // InternalHdbTable.g:3800:2: ( RULE_STRING )
            // InternalHdbTable.g:3801:3: RULE_STRING
            {
             before(grammarAccess.getTableAccess().getDescriptionTextSTRINGTerminalRuleCall_2_2_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getTableAccess().getDescriptionTextSTRINGTerminalRuleCall_2_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__DescriptionTextAssignment_2_2"


    // $ANTLR start "rule__Table__ColumnsAssignment_3_0"
    // InternalHdbTable.g:3810:1: rule__Table__ColumnsAssignment_3_0 : ( ( 'table.columns' ) ) ;
    public final void rule__Table__ColumnsAssignment_3_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:3814:1: ( ( ( 'table.columns' ) ) )
            // InternalHdbTable.g:3815:2: ( ( 'table.columns' ) )
            {
            // InternalHdbTable.g:3815:2: ( ( 'table.columns' ) )
            // InternalHdbTable.g:3816:3: ( 'table.columns' )
            {
             before(grammarAccess.getTableAccess().getColumnsTableColumnsKeyword_3_0_0()); 
            // InternalHdbTable.g:3817:3: ( 'table.columns' )
            // InternalHdbTable.g:3818:4: 'table.columns'
            {
             before(grammarAccess.getTableAccess().getColumnsTableColumnsKeyword_3_0_0()); 
            match(input,33,FOLLOW_2); 
             after(grammarAccess.getTableAccess().getColumnsTableColumnsKeyword_3_0_0()); 

            }

             after(grammarAccess.getTableAccess().getColumnsTableColumnsKeyword_3_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__ColumnsAssignment_3_0"


    // $ANTLR start "rule__Table__ColumnsValuesAssignment_3_3_0"
    // InternalHdbTable.g:3829:1: rule__Table__ColumnsValuesAssignment_3_3_0 : ( ruleColumnType ) ;
    public final void rule__Table__ColumnsValuesAssignment_3_3_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:3833:1: ( ( ruleColumnType ) )
            // InternalHdbTable.g:3834:2: ( ruleColumnType )
            {
            // InternalHdbTable.g:3834:2: ( ruleColumnType )
            // InternalHdbTable.g:3835:3: ruleColumnType
            {
             before(grammarAccess.getTableAccess().getColumnsValuesColumnTypeParserRuleCall_3_3_0_0()); 
            pushFollow(FOLLOW_2);
            ruleColumnType();

            state._fsp--;

             after(grammarAccess.getTableAccess().getColumnsValuesColumnTypeParserRuleCall_3_3_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__ColumnsValuesAssignment_3_3_0"


    // $ANTLR start "rule__Table__ColumnsValuesAssignment_3_3_1_1"
    // InternalHdbTable.g:3844:1: rule__Table__ColumnsValuesAssignment_3_3_1_1 : ( ruleColumnType ) ;
    public final void rule__Table__ColumnsValuesAssignment_3_3_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:3848:1: ( ( ruleColumnType ) )
            // InternalHdbTable.g:3849:2: ( ruleColumnType )
            {
            // InternalHdbTable.g:3849:2: ( ruleColumnType )
            // InternalHdbTable.g:3850:3: ruleColumnType
            {
             before(grammarAccess.getTableAccess().getColumnsValuesColumnTypeParserRuleCall_3_3_1_1_0()); 
            pushFollow(FOLLOW_2);
            ruleColumnType();

            state._fsp--;

             after(grammarAccess.getTableAccess().getColumnsValuesColumnTypeParserRuleCall_3_3_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__ColumnsValuesAssignment_3_3_1_1"


    // $ANTLR start "rule__Table__IndexesAssignment_4_0"
    // InternalHdbTable.g:3859:1: rule__Table__IndexesAssignment_4_0 : ( ( 'table.indexes' ) ) ;
    public final void rule__Table__IndexesAssignment_4_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:3863:1: ( ( ( 'table.indexes' ) ) )
            // InternalHdbTable.g:3864:2: ( ( 'table.indexes' ) )
            {
            // InternalHdbTable.g:3864:2: ( ( 'table.indexes' ) )
            // InternalHdbTable.g:3865:3: ( 'table.indexes' )
            {
             before(grammarAccess.getTableAccess().getIndexesTableIndexesKeyword_4_0_0()); 
            // InternalHdbTable.g:3866:3: ( 'table.indexes' )
            // InternalHdbTable.g:3867:4: 'table.indexes'
            {
             before(grammarAccess.getTableAccess().getIndexesTableIndexesKeyword_4_0_0()); 
            match(input,34,FOLLOW_2); 
             after(grammarAccess.getTableAccess().getIndexesTableIndexesKeyword_4_0_0()); 

            }

             after(grammarAccess.getTableAccess().getIndexesTableIndexesKeyword_4_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__IndexesAssignment_4_0"


    // $ANTLR start "rule__Table__IndexesValuesAssignment_4_3_0"
    // InternalHdbTable.g:3878:1: rule__Table__IndexesValuesAssignment_4_3_0 : ( ruleIndexType ) ;
    public final void rule__Table__IndexesValuesAssignment_4_3_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:3882:1: ( ( ruleIndexType ) )
            // InternalHdbTable.g:3883:2: ( ruleIndexType )
            {
            // InternalHdbTable.g:3883:2: ( ruleIndexType )
            // InternalHdbTable.g:3884:3: ruleIndexType
            {
             before(grammarAccess.getTableAccess().getIndexesValuesIndexTypeParserRuleCall_4_3_0_0()); 
            pushFollow(FOLLOW_2);
            ruleIndexType();

            state._fsp--;

             after(grammarAccess.getTableAccess().getIndexesValuesIndexTypeParserRuleCall_4_3_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__IndexesValuesAssignment_4_3_0"


    // $ANTLR start "rule__Table__IndexesValuesAssignment_4_3_1_1"
    // InternalHdbTable.g:3893:1: rule__Table__IndexesValuesAssignment_4_3_1_1 : ( ruleIndexType ) ;
    public final void rule__Table__IndexesValuesAssignment_4_3_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:3897:1: ( ( ruleIndexType ) )
            // InternalHdbTable.g:3898:2: ( ruleIndexType )
            {
            // InternalHdbTable.g:3898:2: ( ruleIndexType )
            // InternalHdbTable.g:3899:3: ruleIndexType
            {
             before(grammarAccess.getTableAccess().getIndexesValuesIndexTypeParserRuleCall_4_3_1_1_0()); 
            pushFollow(FOLLOW_2);
            ruleIndexType();

            state._fsp--;

             after(grammarAccess.getTableAccess().getIndexesValuesIndexTypeParserRuleCall_4_3_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__IndexesValuesAssignment_4_3_1_1"


    // $ANTLR start "rule__Table__PrimaryKeyColumnsAssignment_5_0_0"
    // InternalHdbTable.g:3908:1: rule__Table__PrimaryKeyColumnsAssignment_5_0_0 : ( ( 'table.primaryKey.pkcolumns' ) ) ;
    public final void rule__Table__PrimaryKeyColumnsAssignment_5_0_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:3912:1: ( ( ( 'table.primaryKey.pkcolumns' ) ) )
            // InternalHdbTable.g:3913:2: ( ( 'table.primaryKey.pkcolumns' ) )
            {
            // InternalHdbTable.g:3913:2: ( ( 'table.primaryKey.pkcolumns' ) )
            // InternalHdbTable.g:3914:3: ( 'table.primaryKey.pkcolumns' )
            {
             before(grammarAccess.getTableAccess().getPrimaryKeyColumnsTablePrimaryKeyPkcolumnsKeyword_5_0_0_0()); 
            // InternalHdbTable.g:3915:3: ( 'table.primaryKey.pkcolumns' )
            // InternalHdbTable.g:3916:4: 'table.primaryKey.pkcolumns'
            {
             before(grammarAccess.getTableAccess().getPrimaryKeyColumnsTablePrimaryKeyPkcolumnsKeyword_5_0_0_0()); 
            match(input,35,FOLLOW_2); 
             after(grammarAccess.getTableAccess().getPrimaryKeyColumnsTablePrimaryKeyPkcolumnsKeyword_5_0_0_0()); 

            }

             after(grammarAccess.getTableAccess().getPrimaryKeyColumnsTablePrimaryKeyPkcolumnsKeyword_5_0_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__PrimaryKeyColumnsAssignment_5_0_0"


    // $ANTLR start "rule__Table__TablePrimaryKeyColumnsValuesAssignment_5_0_3_0"
    // InternalHdbTable.g:3927:1: rule__Table__TablePrimaryKeyColumnsValuesAssignment_5_0_3_0 : ( RULE_STRING ) ;
    public final void rule__Table__TablePrimaryKeyColumnsValuesAssignment_5_0_3_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:3931:1: ( ( RULE_STRING ) )
            // InternalHdbTable.g:3932:2: ( RULE_STRING )
            {
            // InternalHdbTable.g:3932:2: ( RULE_STRING )
            // InternalHdbTable.g:3933:3: RULE_STRING
            {
             before(grammarAccess.getTableAccess().getTablePrimaryKeyColumnsValuesSTRINGTerminalRuleCall_5_0_3_0_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getTableAccess().getTablePrimaryKeyColumnsValuesSTRINGTerminalRuleCall_5_0_3_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__TablePrimaryKeyColumnsValuesAssignment_5_0_3_0"


    // $ANTLR start "rule__Table__TablePrimaryKeyColumnsValuesAssignment_5_0_3_1_1"
    // InternalHdbTable.g:3942:1: rule__Table__TablePrimaryKeyColumnsValuesAssignment_5_0_3_1_1 : ( RULE_STRING ) ;
    public final void rule__Table__TablePrimaryKeyColumnsValuesAssignment_5_0_3_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbTable.g:3946:1: ( ( RULE_STRING ) )
            // InternalHdbTable.g:3947:2: ( RULE_STRING )
            {
            // InternalHdbTable.g:3947:2: ( RULE_STRING )
            // InternalHdbTable.g:3948:3: RULE_STRING
            {
             before(grammarAccess.getTableAccess().getTablePrimaryKeyColumnsValuesSTRINGTerminalRuleCall_5_0_3_1_1_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getTableAccess().getTablePrimaryKeyColumnsValuesSTRINGTerminalRuleCall_5_0_3_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__TablePrimaryKeyColumnsValuesAssignment_5_0_3_1_1"

    // Delegated rules


    protected DFA12 dfa12 = new DFA12(this);
    protected DFA13 dfa13 = new DFA13(this);
    protected DFA14 dfa14 = new DFA14(this);
    protected DFA15 dfa15 = new DFA15(this);
    protected DFA16 dfa16 = new DFA16(this);
    protected DFA17 dfa17 = new DFA17(this);
    protected DFA18 dfa18 = new DFA18(this);
    static final String dfa_1s = "\12\uffff";
    static final String dfa_2s = "\1\11\11\uffff";
    static final String dfa_3s = "\1\14\11\uffff";
    static final String dfa_4s = "\1\34\11\uffff";
    static final String dfa_5s = "\1\uffff\10\1\1\2";
    static final String dfa_6s = "\1\0\11\uffff}>";
    static final String[] dfa_7s = {
            "\1\1\2\uffff\1\10\1\2\1\3\1\4\1\5\1\6\1\7\1\uffff\1\10\3\uffff\2\11",
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

    class DFA12 extends DFA {

        public DFA12(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 12;
            this.eot = dfa_1;
            this.eof = dfa_2;
            this.min = dfa_3;
            this.max = dfa_4;
            this.accept = dfa_5;
            this.special = dfa_6;
            this.transition = dfa_7;
        }
        public String getDescription() {
            return "3061:2: ( rule__ColumnType__UnorderedGroup__1 )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA12_0 = input.LA(1);

                         
                        int index12_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( LA12_0 == 12 && getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 0) ) {s = 1;}

                        else if ( LA12_0 == 16 && getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 1) ) {s = 2;}

                        else if ( LA12_0 == 17 && getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 2) ) {s = 3;}

                        else if ( LA12_0 == 18 && getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 3) ) {s = 4;}

                        else if ( LA12_0 == 19 && getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 4) ) {s = 5;}

                        else if ( LA12_0 == 20 && getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 5) ) {s = 6;}

                        else if ( LA12_0 == 21 && getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 6) ) {s = 7;}

                        else if ( ( LA12_0 == 15 || LA12_0 == 23 ) && getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 7) ) {s = 8;}

                        else if ( (LA12_0==EOF||(LA12_0>=27 && LA12_0<=28)) ) {s = 9;}

                         
                        input.seek(index12_0);
                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 12, _s, input);
            error(nvae);
            throw nvae;
        }
    }

    class DFA13 extends DFA {

        public DFA13(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 13;
            this.eot = dfa_1;
            this.eof = dfa_2;
            this.min = dfa_3;
            this.max = dfa_4;
            this.accept = dfa_5;
            this.special = dfa_6;
            this.transition = dfa_7;
        }
        public String getDescription() {
            return "3073:2: ( rule__ColumnType__UnorderedGroup__2 )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA13_0 = input.LA(1);

                         
                        int index13_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( LA13_0 == 12 && getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 0) ) {s = 1;}

                        else if ( LA13_0 == 16 && getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 1) ) {s = 2;}

                        else if ( LA13_0 == 17 && getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 2) ) {s = 3;}

                        else if ( LA13_0 == 18 && getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 3) ) {s = 4;}

                        else if ( LA13_0 == 19 && getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 4) ) {s = 5;}

                        else if ( LA13_0 == 20 && getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 5) ) {s = 6;}

                        else if ( LA13_0 == 21 && getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 6) ) {s = 7;}

                        else if ( ( LA13_0 == 15 || LA13_0 == 23 ) && getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 7) ) {s = 8;}

                        else if ( (LA13_0==EOF||(LA13_0>=27 && LA13_0<=28)) ) {s = 9;}

                         
                        input.seek(index13_0);
                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 13, _s, input);
            error(nvae);
            throw nvae;
        }
    }

    class DFA14 extends DFA {

        public DFA14(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 14;
            this.eot = dfa_1;
            this.eof = dfa_2;
            this.min = dfa_3;
            this.max = dfa_4;
            this.accept = dfa_5;
            this.special = dfa_6;
            this.transition = dfa_7;
        }
        public String getDescription() {
            return "3085:2: ( rule__ColumnType__UnorderedGroup__3 )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA14_0 = input.LA(1);

                         
                        int index14_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( LA14_0 == 12 && getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 0) ) {s = 1;}

                        else if ( LA14_0 == 16 && getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 1) ) {s = 2;}

                        else if ( LA14_0 == 17 && getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 2) ) {s = 3;}

                        else if ( LA14_0 == 18 && getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 3) ) {s = 4;}

                        else if ( LA14_0 == 19 && getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 4) ) {s = 5;}

                        else if ( LA14_0 == 20 && getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 5) ) {s = 6;}

                        else if ( LA14_0 == 21 && getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 6) ) {s = 7;}

                        else if ( ( LA14_0 == 15 || LA14_0 == 23 ) && getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 7) ) {s = 8;}

                        else if ( (LA14_0==EOF||(LA14_0>=27 && LA14_0<=28)) ) {s = 9;}

                         
                        input.seek(index14_0);
                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 14, _s, input);
            error(nvae);
            throw nvae;
        }
    }

    class DFA15 extends DFA {

        public DFA15(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 15;
            this.eot = dfa_1;
            this.eof = dfa_2;
            this.min = dfa_3;
            this.max = dfa_4;
            this.accept = dfa_5;
            this.special = dfa_6;
            this.transition = dfa_7;
        }
        public String getDescription() {
            return "3097:2: ( rule__ColumnType__UnorderedGroup__4 )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA15_0 = input.LA(1);

                         
                        int index15_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( LA15_0 == 12 && getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 0) ) {s = 1;}

                        else if ( LA15_0 == 16 && getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 1) ) {s = 2;}

                        else if ( LA15_0 == 17 && getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 2) ) {s = 3;}

                        else if ( LA15_0 == 18 && getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 3) ) {s = 4;}

                        else if ( LA15_0 == 19 && getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 4) ) {s = 5;}

                        else if ( LA15_0 == 20 && getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 5) ) {s = 6;}

                        else if ( LA15_0 == 21 && getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 6) ) {s = 7;}

                        else if ( ( LA15_0 == 15 || LA15_0 == 23 ) && getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 7) ) {s = 8;}

                        else if ( (LA15_0==EOF||(LA15_0>=27 && LA15_0<=28)) ) {s = 9;}

                         
                        input.seek(index15_0);
                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 15, _s, input);
            error(nvae);
            throw nvae;
        }
    }

    class DFA16 extends DFA {

        public DFA16(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 16;
            this.eot = dfa_1;
            this.eof = dfa_2;
            this.min = dfa_3;
            this.max = dfa_4;
            this.accept = dfa_5;
            this.special = dfa_6;
            this.transition = dfa_7;
        }
        public String getDescription() {
            return "3109:2: ( rule__ColumnType__UnorderedGroup__5 )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA16_0 = input.LA(1);

                         
                        int index16_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( LA16_0 == 12 && getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 0) ) {s = 1;}

                        else if ( LA16_0 == 16 && getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 1) ) {s = 2;}

                        else if ( LA16_0 == 17 && getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 2) ) {s = 3;}

                        else if ( LA16_0 == 18 && getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 3) ) {s = 4;}

                        else if ( LA16_0 == 19 && getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 4) ) {s = 5;}

                        else if ( LA16_0 == 20 && getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 5) ) {s = 6;}

                        else if ( LA16_0 == 21 && getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 6) ) {s = 7;}

                        else if ( ( LA16_0 == 15 || LA16_0 == 23 ) && getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 7) ) {s = 8;}

                        else if ( (LA16_0==EOF||(LA16_0>=27 && LA16_0<=28)) ) {s = 9;}

                         
                        input.seek(index16_0);
                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 16, _s, input);
            error(nvae);
            throw nvae;
        }
    }

    class DFA17 extends DFA {

        public DFA17(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 17;
            this.eot = dfa_1;
            this.eof = dfa_2;
            this.min = dfa_3;
            this.max = dfa_4;
            this.accept = dfa_5;
            this.special = dfa_6;
            this.transition = dfa_7;
        }
        public String getDescription() {
            return "3121:2: ( rule__ColumnType__UnorderedGroup__6 )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA17_0 = input.LA(1);

                         
                        int index17_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( LA17_0 == 12 && getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 0) ) {s = 1;}

                        else if ( LA17_0 == 16 && getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 1) ) {s = 2;}

                        else if ( LA17_0 == 17 && getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 2) ) {s = 3;}

                        else if ( LA17_0 == 18 && getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 3) ) {s = 4;}

                        else if ( LA17_0 == 19 && getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 4) ) {s = 5;}

                        else if ( LA17_0 == 20 && getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 5) ) {s = 6;}

                        else if ( LA17_0 == 21 && getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 6) ) {s = 7;}

                        else if ( ( LA17_0 == 15 || LA17_0 == 23 ) && getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 7) ) {s = 8;}

                        else if ( (LA17_0==EOF||(LA17_0>=27 && LA17_0<=28)) ) {s = 9;}

                         
                        input.seek(index17_0);
                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 17, _s, input);
            error(nvae);
            throw nvae;
        }
    }

    class DFA18 extends DFA {

        public DFA18(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 18;
            this.eot = dfa_1;
            this.eof = dfa_2;
            this.min = dfa_3;
            this.max = dfa_4;
            this.accept = dfa_5;
            this.special = dfa_6;
            this.transition = dfa_7;
        }
        public String getDescription() {
            return "3133:2: ( rule__ColumnType__UnorderedGroup__7 )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA18_0 = input.LA(1);

                         
                        int index18_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( LA18_0 == 12 && getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 0) ) {s = 1;}

                        else if ( LA18_0 == 16 && getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 1) ) {s = 2;}

                        else if ( LA18_0 == 17 && getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 2) ) {s = 3;}

                        else if ( LA18_0 == 18 && getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 3) ) {s = 4;}

                        else if ( LA18_0 == 19 && getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 4) ) {s = 5;}

                        else if ( LA18_0 == 20 && getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 5) ) {s = 6;}

                        else if ( LA18_0 == 21 && getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 6) ) {s = 7;}

                        else if ( ( LA18_0 == 15 || LA18_0 == 23 ) && getUnorderedGroupHelper().canSelect(grammarAccess.getColumnTypeAccess().getUnorderedGroup(), 7) ) {s = 8;}

                        else if ( (LA18_0==EOF||(LA18_0>=27 && LA18_0<=28)) ) {s = 9;}

                         
                        input.seek(index18_0);
                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 18, _s, input);
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
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000008000010L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000010000002L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000008BF9000L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000000BF9000L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x000000002B001000L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000023001000L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000000000BF9002L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000000023001002L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0000000FC0008002L});

}
