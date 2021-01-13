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
package com.sap.xsk.models.hdbti.ide.contentassist.antlr.internal;

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
import com.sap.xsk.models.hdbti.services.HdbtiGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalHdbtiParser extends AbstractInternalContentAssistParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_STRING", "RULE_BOOL", "RULE_ID", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'\"GROUP_TYPE\"'", "':'", "'{'", "'='", "';'", "'['", "']'", "','", "'}'", "'import'", "'table'", "'schema'", "'file'", "'delimField'", "'header'", "'keys'", "'delimEnclosing'"
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

    	public void setGrammarAccess(HdbtiGrammarAccess grammarAccess) {
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



    // $ANTLR start "entryRuleHdbdtiModel"
    // InternalHdbti.g:53:1: entryRuleHdbdtiModel : ruleHdbdtiModel EOF ;
    public final void entryRuleHdbdtiModel() throws RecognitionException {
        try {
            // InternalHdbti.g:54:1: ( ruleHdbdtiModel EOF )
            // InternalHdbti.g:55:1: ruleHdbdtiModel EOF
            {
             before(grammarAccess.getHdbdtiModelRule()); 
            pushFollow(FOLLOW_1);
            ruleHdbdtiModel();

            state._fsp--;

             after(grammarAccess.getHdbdtiModelRule()); 
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
    // $ANTLR end "entryRuleHdbdtiModel"


    // $ANTLR start "ruleHdbdtiModel"
    // InternalHdbti.g:62:1: ruleHdbdtiModel : ( ( rule__HdbdtiModel__ImportElementAssignment ) ) ;
    public final void ruleHdbdtiModel() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:66:2: ( ( ( rule__HdbdtiModel__ImportElementAssignment ) ) )
            // InternalHdbti.g:67:2: ( ( rule__HdbdtiModel__ImportElementAssignment ) )
            {
            // InternalHdbti.g:67:2: ( ( rule__HdbdtiModel__ImportElementAssignment ) )
            // InternalHdbti.g:68:3: ( rule__HdbdtiModel__ImportElementAssignment )
            {
             before(grammarAccess.getHdbdtiModelAccess().getImportElementAssignment()); 
            // InternalHdbti.g:69:3: ( rule__HdbdtiModel__ImportElementAssignment )
            // InternalHdbti.g:69:4: rule__HdbdtiModel__ImportElementAssignment
            {
            pushFollow(FOLLOW_2);
            rule__HdbdtiModel__ImportElementAssignment();

            state._fsp--;


            }

             after(grammarAccess.getHdbdtiModelAccess().getImportElementAssignment()); 

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
    // $ANTLR end "ruleHdbdtiModel"


    // $ANTLR start "entryRuleGroupType"
    // InternalHdbti.g:78:1: entryRuleGroupType : ruleGroupType EOF ;
    public final void entryRuleGroupType() throws RecognitionException {
        try {
            // InternalHdbti.g:79:1: ( ruleGroupType EOF )
            // InternalHdbti.g:80:1: ruleGroupType EOF
            {
             before(grammarAccess.getGroupTypeRule()); 
            pushFollow(FOLLOW_1);
            ruleGroupType();

            state._fsp--;

             after(grammarAccess.getGroupTypeRule()); 
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
    // $ANTLR end "entryRuleGroupType"


    // $ANTLR start "ruleGroupType"
    // InternalHdbti.g:87:1: ruleGroupType : ( ( rule__GroupType__Group__0 ) ) ;
    public final void ruleGroupType() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:91:2: ( ( ( rule__GroupType__Group__0 ) ) )
            // InternalHdbti.g:92:2: ( ( rule__GroupType__Group__0 ) )
            {
            // InternalHdbti.g:92:2: ( ( rule__GroupType__Group__0 ) )
            // InternalHdbti.g:93:3: ( rule__GroupType__Group__0 )
            {
             before(grammarAccess.getGroupTypeAccess().getGroup()); 
            // InternalHdbti.g:94:3: ( rule__GroupType__Group__0 )
            // InternalHdbti.g:94:4: rule__GroupType__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__GroupType__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getGroupTypeAccess().getGroup()); 

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
    // $ANTLR end "ruleGroupType"


    // $ANTLR start "entryRuleImportConfig"
    // InternalHdbti.g:103:1: entryRuleImportConfig : ruleImportConfig EOF ;
    public final void entryRuleImportConfig() throws RecognitionException {
        try {
            // InternalHdbti.g:104:1: ( ruleImportConfig EOF )
            // InternalHdbti.g:105:1: ruleImportConfig EOF
            {
             before(grammarAccess.getImportConfigRule()); 
            pushFollow(FOLLOW_1);
            ruleImportConfig();

            state._fsp--;

             after(grammarAccess.getImportConfigRule()); 
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
    // $ANTLR end "entryRuleImportConfig"


    // $ANTLR start "ruleImportConfig"
    // InternalHdbti.g:112:1: ruleImportConfig : ( ( rule__ImportConfig__UnorderedGroup ) ) ;
    public final void ruleImportConfig() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:116:2: ( ( ( rule__ImportConfig__UnorderedGroup ) ) )
            // InternalHdbti.g:117:2: ( ( rule__ImportConfig__UnorderedGroup ) )
            {
            // InternalHdbti.g:117:2: ( ( rule__ImportConfig__UnorderedGroup ) )
            // InternalHdbti.g:118:3: ( rule__ImportConfig__UnorderedGroup )
            {
             before(grammarAccess.getImportConfigAccess().getUnorderedGroup()); 
            // InternalHdbti.g:119:3: ( rule__ImportConfig__UnorderedGroup )
            // InternalHdbti.g:119:4: rule__ImportConfig__UnorderedGroup
            {
            pushFollow(FOLLOW_2);
            rule__ImportConfig__UnorderedGroup();

            state._fsp--;


            }

             after(grammarAccess.getImportConfigAccess().getUnorderedGroup()); 

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
    // $ANTLR end "ruleImportConfig"


    // $ANTLR start "entryRuleImport"
    // InternalHdbti.g:128:1: entryRuleImport : ruleImport EOF ;
    public final void entryRuleImport() throws RecognitionException {
        try {
            // InternalHdbti.g:129:1: ( ruleImport EOF )
            // InternalHdbti.g:130:1: ruleImport EOF
            {
             before(grammarAccess.getImportRule()); 
            pushFollow(FOLLOW_1);
            ruleImport();

            state._fsp--;

             after(grammarAccess.getImportRule()); 
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
    // $ANTLR end "entryRuleImport"


    // $ANTLR start "ruleImport"
    // InternalHdbti.g:137:1: ruleImport : ( ( rule__Import__Group__0 ) ) ;
    public final void ruleImport() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:141:2: ( ( ( rule__Import__Group__0 ) ) )
            // InternalHdbti.g:142:2: ( ( rule__Import__Group__0 ) )
            {
            // InternalHdbti.g:142:2: ( ( rule__Import__Group__0 ) )
            // InternalHdbti.g:143:3: ( rule__Import__Group__0 )
            {
             before(grammarAccess.getImportAccess().getGroup()); 
            // InternalHdbti.g:144:3: ( rule__Import__Group__0 )
            // InternalHdbti.g:144:4: rule__Import__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Import__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getImportAccess().getGroup()); 

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
    // $ANTLR end "ruleImport"


    // $ANTLR start "rule__GroupType__Group__0"
    // InternalHdbti.g:152:1: rule__GroupType__Group__0 : rule__GroupType__Group__0__Impl rule__GroupType__Group__1 ;
    public final void rule__GroupType__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:156:1: ( rule__GroupType__Group__0__Impl rule__GroupType__Group__1 )
            // InternalHdbti.g:157:2: rule__GroupType__Group__0__Impl rule__GroupType__Group__1
            {
            pushFollow(FOLLOW_3);
            rule__GroupType__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__GroupType__Group__1();

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
    // $ANTLR end "rule__GroupType__Group__0"


    // $ANTLR start "rule__GroupType__Group__0__Impl"
    // InternalHdbti.g:164:1: rule__GroupType__Group__0__Impl : ( '\"GROUP_TYPE\"' ) ;
    public final void rule__GroupType__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:168:1: ( ( '\"GROUP_TYPE\"' ) )
            // InternalHdbti.g:169:1: ( '\"GROUP_TYPE\"' )
            {
            // InternalHdbti.g:169:1: ( '\"GROUP_TYPE\"' )
            // InternalHdbti.g:170:2: '\"GROUP_TYPE\"'
            {
             before(grammarAccess.getGroupTypeAccess().getGROUP_TYPEKeyword_0()); 
            match(input,12,FOLLOW_2); 
             after(grammarAccess.getGroupTypeAccess().getGROUP_TYPEKeyword_0()); 

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
    // $ANTLR end "rule__GroupType__Group__0__Impl"


    // $ANTLR start "rule__GroupType__Group__1"
    // InternalHdbti.g:179:1: rule__GroupType__Group__1 : rule__GroupType__Group__1__Impl rule__GroupType__Group__2 ;
    public final void rule__GroupType__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:183:1: ( rule__GroupType__Group__1__Impl rule__GroupType__Group__2 )
            // InternalHdbti.g:184:2: rule__GroupType__Group__1__Impl rule__GroupType__Group__2
            {
            pushFollow(FOLLOW_4);
            rule__GroupType__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__GroupType__Group__2();

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
    // $ANTLR end "rule__GroupType__Group__1"


    // $ANTLR start "rule__GroupType__Group__1__Impl"
    // InternalHdbti.g:191:1: rule__GroupType__Group__1__Impl : ( ':' ) ;
    public final void rule__GroupType__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:195:1: ( ( ':' ) )
            // InternalHdbti.g:196:1: ( ':' )
            {
            // InternalHdbti.g:196:1: ( ':' )
            // InternalHdbti.g:197:2: ':'
            {
             before(grammarAccess.getGroupTypeAccess().getColonKeyword_1()); 
            match(input,13,FOLLOW_2); 
             after(grammarAccess.getGroupTypeAccess().getColonKeyword_1()); 

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
    // $ANTLR end "rule__GroupType__Group__1__Impl"


    // $ANTLR start "rule__GroupType__Group__2"
    // InternalHdbti.g:206:1: rule__GroupType__Group__2 : rule__GroupType__Group__2__Impl ;
    public final void rule__GroupType__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:210:1: ( rule__GroupType__Group__2__Impl )
            // InternalHdbti.g:211:2: rule__GroupType__Group__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__GroupType__Group__2__Impl();

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
    // $ANTLR end "rule__GroupType__Group__2"


    // $ANTLR start "rule__GroupType__Group__2__Impl"
    // InternalHdbti.g:217:1: rule__GroupType__Group__2__Impl : ( ( rule__GroupType__ValueAssignment_2 ) ) ;
    public final void rule__GroupType__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:221:1: ( ( ( rule__GroupType__ValueAssignment_2 ) ) )
            // InternalHdbti.g:222:1: ( ( rule__GroupType__ValueAssignment_2 ) )
            {
            // InternalHdbti.g:222:1: ( ( rule__GroupType__ValueAssignment_2 ) )
            // InternalHdbti.g:223:2: ( rule__GroupType__ValueAssignment_2 )
            {
             before(grammarAccess.getGroupTypeAccess().getValueAssignment_2()); 
            // InternalHdbti.g:224:2: ( rule__GroupType__ValueAssignment_2 )
            // InternalHdbti.g:224:3: rule__GroupType__ValueAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__GroupType__ValueAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getGroupTypeAccess().getValueAssignment_2()); 

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
    // $ANTLR end "rule__GroupType__Group__2__Impl"


    // $ANTLR start "rule__ImportConfig__Group_0__0"
    // InternalHdbti.g:233:1: rule__ImportConfig__Group_0__0 : rule__ImportConfig__Group_0__0__Impl rule__ImportConfig__Group_0__1 ;
    public final void rule__ImportConfig__Group_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:237:1: ( rule__ImportConfig__Group_0__0__Impl rule__ImportConfig__Group_0__1 )
            // InternalHdbti.g:238:2: rule__ImportConfig__Group_0__0__Impl rule__ImportConfig__Group_0__1
            {
            pushFollow(FOLLOW_5);
            rule__ImportConfig__Group_0__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ImportConfig__Group_0__1();

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
    // $ANTLR end "rule__ImportConfig__Group_0__0"


    // $ANTLR start "rule__ImportConfig__Group_0__0__Impl"
    // InternalHdbti.g:245:1: rule__ImportConfig__Group_0__0__Impl : ( '{' ) ;
    public final void rule__ImportConfig__Group_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:249:1: ( ( '{' ) )
            // InternalHdbti.g:250:1: ( '{' )
            {
            // InternalHdbti.g:250:1: ( '{' )
            // InternalHdbti.g:251:2: '{'
            {
             before(grammarAccess.getImportConfigAccess().getLeftCurlyBracketKeyword_0_0()); 
            match(input,14,FOLLOW_2); 
             after(grammarAccess.getImportConfigAccess().getLeftCurlyBracketKeyword_0_0()); 

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
    // $ANTLR end "rule__ImportConfig__Group_0__0__Impl"


    // $ANTLR start "rule__ImportConfig__Group_0__1"
    // InternalHdbti.g:260:1: rule__ImportConfig__Group_0__1 : rule__ImportConfig__Group_0__1__Impl rule__ImportConfig__Group_0__2 ;
    public final void rule__ImportConfig__Group_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:264:1: ( rule__ImportConfig__Group_0__1__Impl rule__ImportConfig__Group_0__2 )
            // InternalHdbti.g:265:2: rule__ImportConfig__Group_0__1__Impl rule__ImportConfig__Group_0__2
            {
            pushFollow(FOLLOW_6);
            rule__ImportConfig__Group_0__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ImportConfig__Group_0__2();

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
    // $ANTLR end "rule__ImportConfig__Group_0__1"


    // $ANTLR start "rule__ImportConfig__Group_0__1__Impl"
    // InternalHdbti.g:272:1: rule__ImportConfig__Group_0__1__Impl : ( ( rule__ImportConfig__TableAssignment_0_1 ) ) ;
    public final void rule__ImportConfig__Group_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:276:1: ( ( ( rule__ImportConfig__TableAssignment_0_1 ) ) )
            // InternalHdbti.g:277:1: ( ( rule__ImportConfig__TableAssignment_0_1 ) )
            {
            // InternalHdbti.g:277:1: ( ( rule__ImportConfig__TableAssignment_0_1 ) )
            // InternalHdbti.g:278:2: ( rule__ImportConfig__TableAssignment_0_1 )
            {
             before(grammarAccess.getImportConfigAccess().getTableAssignment_0_1()); 
            // InternalHdbti.g:279:2: ( rule__ImportConfig__TableAssignment_0_1 )
            // InternalHdbti.g:279:3: rule__ImportConfig__TableAssignment_0_1
            {
            pushFollow(FOLLOW_2);
            rule__ImportConfig__TableAssignment_0_1();

            state._fsp--;


            }

             after(grammarAccess.getImportConfigAccess().getTableAssignment_0_1()); 

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
    // $ANTLR end "rule__ImportConfig__Group_0__1__Impl"


    // $ANTLR start "rule__ImportConfig__Group_0__2"
    // InternalHdbti.g:287:1: rule__ImportConfig__Group_0__2 : rule__ImportConfig__Group_0__2__Impl rule__ImportConfig__Group_0__3 ;
    public final void rule__ImportConfig__Group_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:291:1: ( rule__ImportConfig__Group_0__2__Impl rule__ImportConfig__Group_0__3 )
            // InternalHdbti.g:292:2: rule__ImportConfig__Group_0__2__Impl rule__ImportConfig__Group_0__3
            {
            pushFollow(FOLLOW_4);
            rule__ImportConfig__Group_0__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ImportConfig__Group_0__3();

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
    // $ANTLR end "rule__ImportConfig__Group_0__2"


    // $ANTLR start "rule__ImportConfig__Group_0__2__Impl"
    // InternalHdbti.g:299:1: rule__ImportConfig__Group_0__2__Impl : ( '=' ) ;
    public final void rule__ImportConfig__Group_0__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:303:1: ( ( '=' ) )
            // InternalHdbti.g:304:1: ( '=' )
            {
            // InternalHdbti.g:304:1: ( '=' )
            // InternalHdbti.g:305:2: '='
            {
             before(grammarAccess.getImportConfigAccess().getEqualsSignKeyword_0_2()); 
            match(input,15,FOLLOW_2); 
             after(grammarAccess.getImportConfigAccess().getEqualsSignKeyword_0_2()); 

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
    // $ANTLR end "rule__ImportConfig__Group_0__2__Impl"


    // $ANTLR start "rule__ImportConfig__Group_0__3"
    // InternalHdbti.g:314:1: rule__ImportConfig__Group_0__3 : rule__ImportConfig__Group_0__3__Impl rule__ImportConfig__Group_0__4 ;
    public final void rule__ImportConfig__Group_0__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:318:1: ( rule__ImportConfig__Group_0__3__Impl rule__ImportConfig__Group_0__4 )
            // InternalHdbti.g:319:2: rule__ImportConfig__Group_0__3__Impl rule__ImportConfig__Group_0__4
            {
            pushFollow(FOLLOW_7);
            rule__ImportConfig__Group_0__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ImportConfig__Group_0__4();

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
    // $ANTLR end "rule__ImportConfig__Group_0__3"


    // $ANTLR start "rule__ImportConfig__Group_0__3__Impl"
    // InternalHdbti.g:326:1: rule__ImportConfig__Group_0__3__Impl : ( ( rule__ImportConfig__TableValueAssignment_0_3 ) ) ;
    public final void rule__ImportConfig__Group_0__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:330:1: ( ( ( rule__ImportConfig__TableValueAssignment_0_3 ) ) )
            // InternalHdbti.g:331:1: ( ( rule__ImportConfig__TableValueAssignment_0_3 ) )
            {
            // InternalHdbti.g:331:1: ( ( rule__ImportConfig__TableValueAssignment_0_3 ) )
            // InternalHdbti.g:332:2: ( rule__ImportConfig__TableValueAssignment_0_3 )
            {
             before(grammarAccess.getImportConfigAccess().getTableValueAssignment_0_3()); 
            // InternalHdbti.g:333:2: ( rule__ImportConfig__TableValueAssignment_0_3 )
            // InternalHdbti.g:333:3: rule__ImportConfig__TableValueAssignment_0_3
            {
            pushFollow(FOLLOW_2);
            rule__ImportConfig__TableValueAssignment_0_3();

            state._fsp--;


            }

             after(grammarAccess.getImportConfigAccess().getTableValueAssignment_0_3()); 

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
    // $ANTLR end "rule__ImportConfig__Group_0__3__Impl"


    // $ANTLR start "rule__ImportConfig__Group_0__4"
    // InternalHdbti.g:341:1: rule__ImportConfig__Group_0__4 : rule__ImportConfig__Group_0__4__Impl ;
    public final void rule__ImportConfig__Group_0__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:345:1: ( rule__ImportConfig__Group_0__4__Impl )
            // InternalHdbti.g:346:2: rule__ImportConfig__Group_0__4__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ImportConfig__Group_0__4__Impl();

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
    // $ANTLR end "rule__ImportConfig__Group_0__4"


    // $ANTLR start "rule__ImportConfig__Group_0__4__Impl"
    // InternalHdbti.g:352:1: rule__ImportConfig__Group_0__4__Impl : ( ';' ) ;
    public final void rule__ImportConfig__Group_0__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:356:1: ( ( ';' ) )
            // InternalHdbti.g:357:1: ( ';' )
            {
            // InternalHdbti.g:357:1: ( ';' )
            // InternalHdbti.g:358:2: ';'
            {
             before(grammarAccess.getImportConfigAccess().getSemicolonKeyword_0_4()); 
            match(input,16,FOLLOW_2); 
             after(grammarAccess.getImportConfigAccess().getSemicolonKeyword_0_4()); 

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
    // $ANTLR end "rule__ImportConfig__Group_0__4__Impl"


    // $ANTLR start "rule__ImportConfig__Group_1__0"
    // InternalHdbti.g:368:1: rule__ImportConfig__Group_1__0 : rule__ImportConfig__Group_1__0__Impl rule__ImportConfig__Group_1__1 ;
    public final void rule__ImportConfig__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:372:1: ( rule__ImportConfig__Group_1__0__Impl rule__ImportConfig__Group_1__1 )
            // InternalHdbti.g:373:2: rule__ImportConfig__Group_1__0__Impl rule__ImportConfig__Group_1__1
            {
            pushFollow(FOLLOW_6);
            rule__ImportConfig__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ImportConfig__Group_1__1();

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
    // $ANTLR end "rule__ImportConfig__Group_1__0"


    // $ANTLR start "rule__ImportConfig__Group_1__0__Impl"
    // InternalHdbti.g:380:1: rule__ImportConfig__Group_1__0__Impl : ( ( rule__ImportConfig__SchemaAssignment_1_0 ) ) ;
    public final void rule__ImportConfig__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:384:1: ( ( ( rule__ImportConfig__SchemaAssignment_1_0 ) ) )
            // InternalHdbti.g:385:1: ( ( rule__ImportConfig__SchemaAssignment_1_0 ) )
            {
            // InternalHdbti.g:385:1: ( ( rule__ImportConfig__SchemaAssignment_1_0 ) )
            // InternalHdbti.g:386:2: ( rule__ImportConfig__SchemaAssignment_1_0 )
            {
             before(grammarAccess.getImportConfigAccess().getSchemaAssignment_1_0()); 
            // InternalHdbti.g:387:2: ( rule__ImportConfig__SchemaAssignment_1_0 )
            // InternalHdbti.g:387:3: rule__ImportConfig__SchemaAssignment_1_0
            {
            pushFollow(FOLLOW_2);
            rule__ImportConfig__SchemaAssignment_1_0();

            state._fsp--;


            }

             after(grammarAccess.getImportConfigAccess().getSchemaAssignment_1_0()); 

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
    // $ANTLR end "rule__ImportConfig__Group_1__0__Impl"


    // $ANTLR start "rule__ImportConfig__Group_1__1"
    // InternalHdbti.g:395:1: rule__ImportConfig__Group_1__1 : rule__ImportConfig__Group_1__1__Impl rule__ImportConfig__Group_1__2 ;
    public final void rule__ImportConfig__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:399:1: ( rule__ImportConfig__Group_1__1__Impl rule__ImportConfig__Group_1__2 )
            // InternalHdbti.g:400:2: rule__ImportConfig__Group_1__1__Impl rule__ImportConfig__Group_1__2
            {
            pushFollow(FOLLOW_4);
            rule__ImportConfig__Group_1__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ImportConfig__Group_1__2();

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
    // $ANTLR end "rule__ImportConfig__Group_1__1"


    // $ANTLR start "rule__ImportConfig__Group_1__1__Impl"
    // InternalHdbti.g:407:1: rule__ImportConfig__Group_1__1__Impl : ( '=' ) ;
    public final void rule__ImportConfig__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:411:1: ( ( '=' ) )
            // InternalHdbti.g:412:1: ( '=' )
            {
            // InternalHdbti.g:412:1: ( '=' )
            // InternalHdbti.g:413:2: '='
            {
             before(grammarAccess.getImportConfigAccess().getEqualsSignKeyword_1_1()); 
            match(input,15,FOLLOW_2); 
             after(grammarAccess.getImportConfigAccess().getEqualsSignKeyword_1_1()); 

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
    // $ANTLR end "rule__ImportConfig__Group_1__1__Impl"


    // $ANTLR start "rule__ImportConfig__Group_1__2"
    // InternalHdbti.g:422:1: rule__ImportConfig__Group_1__2 : rule__ImportConfig__Group_1__2__Impl rule__ImportConfig__Group_1__3 ;
    public final void rule__ImportConfig__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:426:1: ( rule__ImportConfig__Group_1__2__Impl rule__ImportConfig__Group_1__3 )
            // InternalHdbti.g:427:2: rule__ImportConfig__Group_1__2__Impl rule__ImportConfig__Group_1__3
            {
            pushFollow(FOLLOW_7);
            rule__ImportConfig__Group_1__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ImportConfig__Group_1__3();

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
    // $ANTLR end "rule__ImportConfig__Group_1__2"


    // $ANTLR start "rule__ImportConfig__Group_1__2__Impl"
    // InternalHdbti.g:434:1: rule__ImportConfig__Group_1__2__Impl : ( ( rule__ImportConfig__SchemaValueAssignment_1_2 ) ) ;
    public final void rule__ImportConfig__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:438:1: ( ( ( rule__ImportConfig__SchemaValueAssignment_1_2 ) ) )
            // InternalHdbti.g:439:1: ( ( rule__ImportConfig__SchemaValueAssignment_1_2 ) )
            {
            // InternalHdbti.g:439:1: ( ( rule__ImportConfig__SchemaValueAssignment_1_2 ) )
            // InternalHdbti.g:440:2: ( rule__ImportConfig__SchemaValueAssignment_1_2 )
            {
             before(grammarAccess.getImportConfigAccess().getSchemaValueAssignment_1_2()); 
            // InternalHdbti.g:441:2: ( rule__ImportConfig__SchemaValueAssignment_1_2 )
            // InternalHdbti.g:441:3: rule__ImportConfig__SchemaValueAssignment_1_2
            {
            pushFollow(FOLLOW_2);
            rule__ImportConfig__SchemaValueAssignment_1_2();

            state._fsp--;


            }

             after(grammarAccess.getImportConfigAccess().getSchemaValueAssignment_1_2()); 

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
    // $ANTLR end "rule__ImportConfig__Group_1__2__Impl"


    // $ANTLR start "rule__ImportConfig__Group_1__3"
    // InternalHdbti.g:449:1: rule__ImportConfig__Group_1__3 : rule__ImportConfig__Group_1__3__Impl ;
    public final void rule__ImportConfig__Group_1__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:453:1: ( rule__ImportConfig__Group_1__3__Impl )
            // InternalHdbti.g:454:2: rule__ImportConfig__Group_1__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ImportConfig__Group_1__3__Impl();

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
    // $ANTLR end "rule__ImportConfig__Group_1__3"


    // $ANTLR start "rule__ImportConfig__Group_1__3__Impl"
    // InternalHdbti.g:460:1: rule__ImportConfig__Group_1__3__Impl : ( ';' ) ;
    public final void rule__ImportConfig__Group_1__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:464:1: ( ( ';' ) )
            // InternalHdbti.g:465:1: ( ';' )
            {
            // InternalHdbti.g:465:1: ( ';' )
            // InternalHdbti.g:466:2: ';'
            {
             before(grammarAccess.getImportConfigAccess().getSemicolonKeyword_1_3()); 
            match(input,16,FOLLOW_2); 
             after(grammarAccess.getImportConfigAccess().getSemicolonKeyword_1_3()); 

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
    // $ANTLR end "rule__ImportConfig__Group_1__3__Impl"


    // $ANTLR start "rule__ImportConfig__Group_2__0"
    // InternalHdbti.g:476:1: rule__ImportConfig__Group_2__0 : rule__ImportConfig__Group_2__0__Impl rule__ImportConfig__Group_2__1 ;
    public final void rule__ImportConfig__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:480:1: ( rule__ImportConfig__Group_2__0__Impl rule__ImportConfig__Group_2__1 )
            // InternalHdbti.g:481:2: rule__ImportConfig__Group_2__0__Impl rule__ImportConfig__Group_2__1
            {
            pushFollow(FOLLOW_6);
            rule__ImportConfig__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ImportConfig__Group_2__1();

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
    // $ANTLR end "rule__ImportConfig__Group_2__0"


    // $ANTLR start "rule__ImportConfig__Group_2__0__Impl"
    // InternalHdbti.g:488:1: rule__ImportConfig__Group_2__0__Impl : ( ( rule__ImportConfig__FileAssignment_2_0 ) ) ;
    public final void rule__ImportConfig__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:492:1: ( ( ( rule__ImportConfig__FileAssignment_2_0 ) ) )
            // InternalHdbti.g:493:1: ( ( rule__ImportConfig__FileAssignment_2_0 ) )
            {
            // InternalHdbti.g:493:1: ( ( rule__ImportConfig__FileAssignment_2_0 ) )
            // InternalHdbti.g:494:2: ( rule__ImportConfig__FileAssignment_2_0 )
            {
             before(grammarAccess.getImportConfigAccess().getFileAssignment_2_0()); 
            // InternalHdbti.g:495:2: ( rule__ImportConfig__FileAssignment_2_0 )
            // InternalHdbti.g:495:3: rule__ImportConfig__FileAssignment_2_0
            {
            pushFollow(FOLLOW_2);
            rule__ImportConfig__FileAssignment_2_0();

            state._fsp--;


            }

             after(grammarAccess.getImportConfigAccess().getFileAssignment_2_0()); 

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
    // $ANTLR end "rule__ImportConfig__Group_2__0__Impl"


    // $ANTLR start "rule__ImportConfig__Group_2__1"
    // InternalHdbti.g:503:1: rule__ImportConfig__Group_2__1 : rule__ImportConfig__Group_2__1__Impl rule__ImportConfig__Group_2__2 ;
    public final void rule__ImportConfig__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:507:1: ( rule__ImportConfig__Group_2__1__Impl rule__ImportConfig__Group_2__2 )
            // InternalHdbti.g:508:2: rule__ImportConfig__Group_2__1__Impl rule__ImportConfig__Group_2__2
            {
            pushFollow(FOLLOW_4);
            rule__ImportConfig__Group_2__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ImportConfig__Group_2__2();

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
    // $ANTLR end "rule__ImportConfig__Group_2__1"


    // $ANTLR start "rule__ImportConfig__Group_2__1__Impl"
    // InternalHdbti.g:515:1: rule__ImportConfig__Group_2__1__Impl : ( '=' ) ;
    public final void rule__ImportConfig__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:519:1: ( ( '=' ) )
            // InternalHdbti.g:520:1: ( '=' )
            {
            // InternalHdbti.g:520:1: ( '=' )
            // InternalHdbti.g:521:2: '='
            {
             before(grammarAccess.getImportConfigAccess().getEqualsSignKeyword_2_1()); 
            match(input,15,FOLLOW_2); 
             after(grammarAccess.getImportConfigAccess().getEqualsSignKeyword_2_1()); 

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
    // $ANTLR end "rule__ImportConfig__Group_2__1__Impl"


    // $ANTLR start "rule__ImportConfig__Group_2__2"
    // InternalHdbti.g:530:1: rule__ImportConfig__Group_2__2 : rule__ImportConfig__Group_2__2__Impl rule__ImportConfig__Group_2__3 ;
    public final void rule__ImportConfig__Group_2__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:534:1: ( rule__ImportConfig__Group_2__2__Impl rule__ImportConfig__Group_2__3 )
            // InternalHdbti.g:535:2: rule__ImportConfig__Group_2__2__Impl rule__ImportConfig__Group_2__3
            {
            pushFollow(FOLLOW_7);
            rule__ImportConfig__Group_2__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ImportConfig__Group_2__3();

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
    // $ANTLR end "rule__ImportConfig__Group_2__2"


    // $ANTLR start "rule__ImportConfig__Group_2__2__Impl"
    // InternalHdbti.g:542:1: rule__ImportConfig__Group_2__2__Impl : ( ( rule__ImportConfig__FileValueAssignment_2_2 ) ) ;
    public final void rule__ImportConfig__Group_2__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:546:1: ( ( ( rule__ImportConfig__FileValueAssignment_2_2 ) ) )
            // InternalHdbti.g:547:1: ( ( rule__ImportConfig__FileValueAssignment_2_2 ) )
            {
            // InternalHdbti.g:547:1: ( ( rule__ImportConfig__FileValueAssignment_2_2 ) )
            // InternalHdbti.g:548:2: ( rule__ImportConfig__FileValueAssignment_2_2 )
            {
             before(grammarAccess.getImportConfigAccess().getFileValueAssignment_2_2()); 
            // InternalHdbti.g:549:2: ( rule__ImportConfig__FileValueAssignment_2_2 )
            // InternalHdbti.g:549:3: rule__ImportConfig__FileValueAssignment_2_2
            {
            pushFollow(FOLLOW_2);
            rule__ImportConfig__FileValueAssignment_2_2();

            state._fsp--;


            }

             after(grammarAccess.getImportConfigAccess().getFileValueAssignment_2_2()); 

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
    // $ANTLR end "rule__ImportConfig__Group_2__2__Impl"


    // $ANTLR start "rule__ImportConfig__Group_2__3"
    // InternalHdbti.g:557:1: rule__ImportConfig__Group_2__3 : rule__ImportConfig__Group_2__3__Impl ;
    public final void rule__ImportConfig__Group_2__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:561:1: ( rule__ImportConfig__Group_2__3__Impl )
            // InternalHdbti.g:562:2: rule__ImportConfig__Group_2__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ImportConfig__Group_2__3__Impl();

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
    // $ANTLR end "rule__ImportConfig__Group_2__3"


    // $ANTLR start "rule__ImportConfig__Group_2__3__Impl"
    // InternalHdbti.g:568:1: rule__ImportConfig__Group_2__3__Impl : ( ';' ) ;
    public final void rule__ImportConfig__Group_2__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:572:1: ( ( ';' ) )
            // InternalHdbti.g:573:1: ( ';' )
            {
            // InternalHdbti.g:573:1: ( ';' )
            // InternalHdbti.g:574:2: ';'
            {
             before(grammarAccess.getImportConfigAccess().getSemicolonKeyword_2_3()); 
            match(input,16,FOLLOW_2); 
             after(grammarAccess.getImportConfigAccess().getSemicolonKeyword_2_3()); 

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
    // $ANTLR end "rule__ImportConfig__Group_2__3__Impl"


    // $ANTLR start "rule__ImportConfig__Group_3__0"
    // InternalHdbti.g:584:1: rule__ImportConfig__Group_3__0 : rule__ImportConfig__Group_3__0__Impl rule__ImportConfig__Group_3__1 ;
    public final void rule__ImportConfig__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:588:1: ( rule__ImportConfig__Group_3__0__Impl rule__ImportConfig__Group_3__1 )
            // InternalHdbti.g:589:2: rule__ImportConfig__Group_3__0__Impl rule__ImportConfig__Group_3__1
            {
            pushFollow(FOLLOW_6);
            rule__ImportConfig__Group_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ImportConfig__Group_3__1();

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
    // $ANTLR end "rule__ImportConfig__Group_3__0"


    // $ANTLR start "rule__ImportConfig__Group_3__0__Impl"
    // InternalHdbti.g:596:1: rule__ImportConfig__Group_3__0__Impl : ( ( rule__ImportConfig__DelimFieldAssignment_3_0 ) ) ;
    public final void rule__ImportConfig__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:600:1: ( ( ( rule__ImportConfig__DelimFieldAssignment_3_0 ) ) )
            // InternalHdbti.g:601:1: ( ( rule__ImportConfig__DelimFieldAssignment_3_0 ) )
            {
            // InternalHdbti.g:601:1: ( ( rule__ImportConfig__DelimFieldAssignment_3_0 ) )
            // InternalHdbti.g:602:2: ( rule__ImportConfig__DelimFieldAssignment_3_0 )
            {
             before(grammarAccess.getImportConfigAccess().getDelimFieldAssignment_3_0()); 
            // InternalHdbti.g:603:2: ( rule__ImportConfig__DelimFieldAssignment_3_0 )
            // InternalHdbti.g:603:3: rule__ImportConfig__DelimFieldAssignment_3_0
            {
            pushFollow(FOLLOW_2);
            rule__ImportConfig__DelimFieldAssignment_3_0();

            state._fsp--;


            }

             after(grammarAccess.getImportConfigAccess().getDelimFieldAssignment_3_0()); 

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
    // $ANTLR end "rule__ImportConfig__Group_3__0__Impl"


    // $ANTLR start "rule__ImportConfig__Group_3__1"
    // InternalHdbti.g:611:1: rule__ImportConfig__Group_3__1 : rule__ImportConfig__Group_3__1__Impl rule__ImportConfig__Group_3__2 ;
    public final void rule__ImportConfig__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:615:1: ( rule__ImportConfig__Group_3__1__Impl rule__ImportConfig__Group_3__2 )
            // InternalHdbti.g:616:2: rule__ImportConfig__Group_3__1__Impl rule__ImportConfig__Group_3__2
            {
            pushFollow(FOLLOW_4);
            rule__ImportConfig__Group_3__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ImportConfig__Group_3__2();

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
    // $ANTLR end "rule__ImportConfig__Group_3__1"


    // $ANTLR start "rule__ImportConfig__Group_3__1__Impl"
    // InternalHdbti.g:623:1: rule__ImportConfig__Group_3__1__Impl : ( '=' ) ;
    public final void rule__ImportConfig__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:627:1: ( ( '=' ) )
            // InternalHdbti.g:628:1: ( '=' )
            {
            // InternalHdbti.g:628:1: ( '=' )
            // InternalHdbti.g:629:2: '='
            {
             before(grammarAccess.getImportConfigAccess().getEqualsSignKeyword_3_1()); 
            match(input,15,FOLLOW_2); 
             after(grammarAccess.getImportConfigAccess().getEqualsSignKeyword_3_1()); 

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
    // $ANTLR end "rule__ImportConfig__Group_3__1__Impl"


    // $ANTLR start "rule__ImportConfig__Group_3__2"
    // InternalHdbti.g:638:1: rule__ImportConfig__Group_3__2 : rule__ImportConfig__Group_3__2__Impl rule__ImportConfig__Group_3__3 ;
    public final void rule__ImportConfig__Group_3__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:642:1: ( rule__ImportConfig__Group_3__2__Impl rule__ImportConfig__Group_3__3 )
            // InternalHdbti.g:643:2: rule__ImportConfig__Group_3__2__Impl rule__ImportConfig__Group_3__3
            {
            pushFollow(FOLLOW_7);
            rule__ImportConfig__Group_3__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ImportConfig__Group_3__3();

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
    // $ANTLR end "rule__ImportConfig__Group_3__2"


    // $ANTLR start "rule__ImportConfig__Group_3__2__Impl"
    // InternalHdbti.g:650:1: rule__ImportConfig__Group_3__2__Impl : ( ( rule__ImportConfig__DelimFieldValueAssignment_3_2 ) ) ;
    public final void rule__ImportConfig__Group_3__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:654:1: ( ( ( rule__ImportConfig__DelimFieldValueAssignment_3_2 ) ) )
            // InternalHdbti.g:655:1: ( ( rule__ImportConfig__DelimFieldValueAssignment_3_2 ) )
            {
            // InternalHdbti.g:655:1: ( ( rule__ImportConfig__DelimFieldValueAssignment_3_2 ) )
            // InternalHdbti.g:656:2: ( rule__ImportConfig__DelimFieldValueAssignment_3_2 )
            {
             before(grammarAccess.getImportConfigAccess().getDelimFieldValueAssignment_3_2()); 
            // InternalHdbti.g:657:2: ( rule__ImportConfig__DelimFieldValueAssignment_3_2 )
            // InternalHdbti.g:657:3: rule__ImportConfig__DelimFieldValueAssignment_3_2
            {
            pushFollow(FOLLOW_2);
            rule__ImportConfig__DelimFieldValueAssignment_3_2();

            state._fsp--;


            }

             after(grammarAccess.getImportConfigAccess().getDelimFieldValueAssignment_3_2()); 

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
    // $ANTLR end "rule__ImportConfig__Group_3__2__Impl"


    // $ANTLR start "rule__ImportConfig__Group_3__3"
    // InternalHdbti.g:665:1: rule__ImportConfig__Group_3__3 : rule__ImportConfig__Group_3__3__Impl ;
    public final void rule__ImportConfig__Group_3__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:669:1: ( rule__ImportConfig__Group_3__3__Impl )
            // InternalHdbti.g:670:2: rule__ImportConfig__Group_3__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ImportConfig__Group_3__3__Impl();

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
    // $ANTLR end "rule__ImportConfig__Group_3__3"


    // $ANTLR start "rule__ImportConfig__Group_3__3__Impl"
    // InternalHdbti.g:676:1: rule__ImportConfig__Group_3__3__Impl : ( ';' ) ;
    public final void rule__ImportConfig__Group_3__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:680:1: ( ( ';' ) )
            // InternalHdbti.g:681:1: ( ';' )
            {
            // InternalHdbti.g:681:1: ( ';' )
            // InternalHdbti.g:682:2: ';'
            {
             before(grammarAccess.getImportConfigAccess().getSemicolonKeyword_3_3()); 
            match(input,16,FOLLOW_2); 
             after(grammarAccess.getImportConfigAccess().getSemicolonKeyword_3_3()); 

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
    // $ANTLR end "rule__ImportConfig__Group_3__3__Impl"


    // $ANTLR start "rule__ImportConfig__Group_4__0"
    // InternalHdbti.g:692:1: rule__ImportConfig__Group_4__0 : rule__ImportConfig__Group_4__0__Impl rule__ImportConfig__Group_4__1 ;
    public final void rule__ImportConfig__Group_4__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:696:1: ( rule__ImportConfig__Group_4__0__Impl rule__ImportConfig__Group_4__1 )
            // InternalHdbti.g:697:2: rule__ImportConfig__Group_4__0__Impl rule__ImportConfig__Group_4__1
            {
            pushFollow(FOLLOW_6);
            rule__ImportConfig__Group_4__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ImportConfig__Group_4__1();

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
    // $ANTLR end "rule__ImportConfig__Group_4__0"


    // $ANTLR start "rule__ImportConfig__Group_4__0__Impl"
    // InternalHdbti.g:704:1: rule__ImportConfig__Group_4__0__Impl : ( ( rule__ImportConfig__HeaderAssignment_4_0 ) ) ;
    public final void rule__ImportConfig__Group_4__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:708:1: ( ( ( rule__ImportConfig__HeaderAssignment_4_0 ) ) )
            // InternalHdbti.g:709:1: ( ( rule__ImportConfig__HeaderAssignment_4_0 ) )
            {
            // InternalHdbti.g:709:1: ( ( rule__ImportConfig__HeaderAssignment_4_0 ) )
            // InternalHdbti.g:710:2: ( rule__ImportConfig__HeaderAssignment_4_0 )
            {
             before(grammarAccess.getImportConfigAccess().getHeaderAssignment_4_0()); 
            // InternalHdbti.g:711:2: ( rule__ImportConfig__HeaderAssignment_4_0 )
            // InternalHdbti.g:711:3: rule__ImportConfig__HeaderAssignment_4_0
            {
            pushFollow(FOLLOW_2);
            rule__ImportConfig__HeaderAssignment_4_0();

            state._fsp--;


            }

             after(grammarAccess.getImportConfigAccess().getHeaderAssignment_4_0()); 

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
    // $ANTLR end "rule__ImportConfig__Group_4__0__Impl"


    // $ANTLR start "rule__ImportConfig__Group_4__1"
    // InternalHdbti.g:719:1: rule__ImportConfig__Group_4__1 : rule__ImportConfig__Group_4__1__Impl rule__ImportConfig__Group_4__2 ;
    public final void rule__ImportConfig__Group_4__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:723:1: ( rule__ImportConfig__Group_4__1__Impl rule__ImportConfig__Group_4__2 )
            // InternalHdbti.g:724:2: rule__ImportConfig__Group_4__1__Impl rule__ImportConfig__Group_4__2
            {
            pushFollow(FOLLOW_8);
            rule__ImportConfig__Group_4__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ImportConfig__Group_4__2();

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
    // $ANTLR end "rule__ImportConfig__Group_4__1"


    // $ANTLR start "rule__ImportConfig__Group_4__1__Impl"
    // InternalHdbti.g:731:1: rule__ImportConfig__Group_4__1__Impl : ( '=' ) ;
    public final void rule__ImportConfig__Group_4__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:735:1: ( ( '=' ) )
            // InternalHdbti.g:736:1: ( '=' )
            {
            // InternalHdbti.g:736:1: ( '=' )
            // InternalHdbti.g:737:2: '='
            {
             before(grammarAccess.getImportConfigAccess().getEqualsSignKeyword_4_1()); 
            match(input,15,FOLLOW_2); 
             after(grammarAccess.getImportConfigAccess().getEqualsSignKeyword_4_1()); 

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
    // $ANTLR end "rule__ImportConfig__Group_4__1__Impl"


    // $ANTLR start "rule__ImportConfig__Group_4__2"
    // InternalHdbti.g:746:1: rule__ImportConfig__Group_4__2 : rule__ImportConfig__Group_4__2__Impl rule__ImportConfig__Group_4__3 ;
    public final void rule__ImportConfig__Group_4__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:750:1: ( rule__ImportConfig__Group_4__2__Impl rule__ImportConfig__Group_4__3 )
            // InternalHdbti.g:751:2: rule__ImportConfig__Group_4__2__Impl rule__ImportConfig__Group_4__3
            {
            pushFollow(FOLLOW_7);
            rule__ImportConfig__Group_4__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ImportConfig__Group_4__3();

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
    // $ANTLR end "rule__ImportConfig__Group_4__2"


    // $ANTLR start "rule__ImportConfig__Group_4__2__Impl"
    // InternalHdbti.g:758:1: rule__ImportConfig__Group_4__2__Impl : ( ( rule__ImportConfig__HeaderValueAssignment_4_2 ) ) ;
    public final void rule__ImportConfig__Group_4__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:762:1: ( ( ( rule__ImportConfig__HeaderValueAssignment_4_2 ) ) )
            // InternalHdbti.g:763:1: ( ( rule__ImportConfig__HeaderValueAssignment_4_2 ) )
            {
            // InternalHdbti.g:763:1: ( ( rule__ImportConfig__HeaderValueAssignment_4_2 ) )
            // InternalHdbti.g:764:2: ( rule__ImportConfig__HeaderValueAssignment_4_2 )
            {
             before(grammarAccess.getImportConfigAccess().getHeaderValueAssignment_4_2()); 
            // InternalHdbti.g:765:2: ( rule__ImportConfig__HeaderValueAssignment_4_2 )
            // InternalHdbti.g:765:3: rule__ImportConfig__HeaderValueAssignment_4_2
            {
            pushFollow(FOLLOW_2);
            rule__ImportConfig__HeaderValueAssignment_4_2();

            state._fsp--;


            }

             after(grammarAccess.getImportConfigAccess().getHeaderValueAssignment_4_2()); 

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
    // $ANTLR end "rule__ImportConfig__Group_4__2__Impl"


    // $ANTLR start "rule__ImportConfig__Group_4__3"
    // InternalHdbti.g:773:1: rule__ImportConfig__Group_4__3 : rule__ImportConfig__Group_4__3__Impl ;
    public final void rule__ImportConfig__Group_4__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:777:1: ( rule__ImportConfig__Group_4__3__Impl )
            // InternalHdbti.g:778:2: rule__ImportConfig__Group_4__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ImportConfig__Group_4__3__Impl();

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
    // $ANTLR end "rule__ImportConfig__Group_4__3"


    // $ANTLR start "rule__ImportConfig__Group_4__3__Impl"
    // InternalHdbti.g:784:1: rule__ImportConfig__Group_4__3__Impl : ( ';' ) ;
    public final void rule__ImportConfig__Group_4__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:788:1: ( ( ';' ) )
            // InternalHdbti.g:789:1: ( ';' )
            {
            // InternalHdbti.g:789:1: ( ';' )
            // InternalHdbti.g:790:2: ';'
            {
             before(grammarAccess.getImportConfigAccess().getSemicolonKeyword_4_3()); 
            match(input,16,FOLLOW_2); 
             after(grammarAccess.getImportConfigAccess().getSemicolonKeyword_4_3()); 

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
    // $ANTLR end "rule__ImportConfig__Group_4__3__Impl"


    // $ANTLR start "rule__ImportConfig__Group_5__0"
    // InternalHdbti.g:800:1: rule__ImportConfig__Group_5__0 : rule__ImportConfig__Group_5__0__Impl rule__ImportConfig__Group_5__1 ;
    public final void rule__ImportConfig__Group_5__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:804:1: ( rule__ImportConfig__Group_5__0__Impl rule__ImportConfig__Group_5__1 )
            // InternalHdbti.g:805:2: rule__ImportConfig__Group_5__0__Impl rule__ImportConfig__Group_5__1
            {
            pushFollow(FOLLOW_6);
            rule__ImportConfig__Group_5__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ImportConfig__Group_5__1();

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
    // $ANTLR end "rule__ImportConfig__Group_5__0"


    // $ANTLR start "rule__ImportConfig__Group_5__0__Impl"
    // InternalHdbti.g:812:1: rule__ImportConfig__Group_5__0__Impl : ( ( rule__ImportConfig__KeysAssignment_5_0 ) ) ;
    public final void rule__ImportConfig__Group_5__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:816:1: ( ( ( rule__ImportConfig__KeysAssignment_5_0 ) ) )
            // InternalHdbti.g:817:1: ( ( rule__ImportConfig__KeysAssignment_5_0 ) )
            {
            // InternalHdbti.g:817:1: ( ( rule__ImportConfig__KeysAssignment_5_0 ) )
            // InternalHdbti.g:818:2: ( rule__ImportConfig__KeysAssignment_5_0 )
            {
             before(grammarAccess.getImportConfigAccess().getKeysAssignment_5_0()); 
            // InternalHdbti.g:819:2: ( rule__ImportConfig__KeysAssignment_5_0 )
            // InternalHdbti.g:819:3: rule__ImportConfig__KeysAssignment_5_0
            {
            pushFollow(FOLLOW_2);
            rule__ImportConfig__KeysAssignment_5_0();

            state._fsp--;


            }

             after(grammarAccess.getImportConfigAccess().getKeysAssignment_5_0()); 

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
    // $ANTLR end "rule__ImportConfig__Group_5__0__Impl"


    // $ANTLR start "rule__ImportConfig__Group_5__1"
    // InternalHdbti.g:827:1: rule__ImportConfig__Group_5__1 : rule__ImportConfig__Group_5__1__Impl rule__ImportConfig__Group_5__2 ;
    public final void rule__ImportConfig__Group_5__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:831:1: ( rule__ImportConfig__Group_5__1__Impl rule__ImportConfig__Group_5__2 )
            // InternalHdbti.g:832:2: rule__ImportConfig__Group_5__1__Impl rule__ImportConfig__Group_5__2
            {
            pushFollow(FOLLOW_9);
            rule__ImportConfig__Group_5__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ImportConfig__Group_5__2();

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
    // $ANTLR end "rule__ImportConfig__Group_5__1"


    // $ANTLR start "rule__ImportConfig__Group_5__1__Impl"
    // InternalHdbti.g:839:1: rule__ImportConfig__Group_5__1__Impl : ( '=' ) ;
    public final void rule__ImportConfig__Group_5__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:843:1: ( ( '=' ) )
            // InternalHdbti.g:844:1: ( '=' )
            {
            // InternalHdbti.g:844:1: ( '=' )
            // InternalHdbti.g:845:2: '='
            {
             before(grammarAccess.getImportConfigAccess().getEqualsSignKeyword_5_1()); 
            match(input,15,FOLLOW_2); 
             after(grammarAccess.getImportConfigAccess().getEqualsSignKeyword_5_1()); 

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
    // $ANTLR end "rule__ImportConfig__Group_5__1__Impl"


    // $ANTLR start "rule__ImportConfig__Group_5__2"
    // InternalHdbti.g:854:1: rule__ImportConfig__Group_5__2 : rule__ImportConfig__Group_5__2__Impl rule__ImportConfig__Group_5__3 ;
    public final void rule__ImportConfig__Group_5__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:858:1: ( rule__ImportConfig__Group_5__2__Impl rule__ImportConfig__Group_5__3 )
            // InternalHdbti.g:859:2: rule__ImportConfig__Group_5__2__Impl rule__ImportConfig__Group_5__3
            {
            pushFollow(FOLLOW_10);
            rule__ImportConfig__Group_5__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ImportConfig__Group_5__3();

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
    // $ANTLR end "rule__ImportConfig__Group_5__2"


    // $ANTLR start "rule__ImportConfig__Group_5__2__Impl"
    // InternalHdbti.g:866:1: rule__ImportConfig__Group_5__2__Impl : ( '[' ) ;
    public final void rule__ImportConfig__Group_5__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:870:1: ( ( '[' ) )
            // InternalHdbti.g:871:1: ( '[' )
            {
            // InternalHdbti.g:871:1: ( '[' )
            // InternalHdbti.g:872:2: '['
            {
             before(grammarAccess.getImportConfigAccess().getLeftSquareBracketKeyword_5_2()); 
            match(input,17,FOLLOW_2); 
             after(grammarAccess.getImportConfigAccess().getLeftSquareBracketKeyword_5_2()); 

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
    // $ANTLR end "rule__ImportConfig__Group_5__2__Impl"


    // $ANTLR start "rule__ImportConfig__Group_5__3"
    // InternalHdbti.g:881:1: rule__ImportConfig__Group_5__3 : rule__ImportConfig__Group_5__3__Impl rule__ImportConfig__Group_5__4 ;
    public final void rule__ImportConfig__Group_5__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:885:1: ( rule__ImportConfig__Group_5__3__Impl rule__ImportConfig__Group_5__4 )
            // InternalHdbti.g:886:2: rule__ImportConfig__Group_5__3__Impl rule__ImportConfig__Group_5__4
            {
            pushFollow(FOLLOW_10);
            rule__ImportConfig__Group_5__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ImportConfig__Group_5__4();

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
    // $ANTLR end "rule__ImportConfig__Group_5__3"


    // $ANTLR start "rule__ImportConfig__Group_5__3__Impl"
    // InternalHdbti.g:893:1: rule__ImportConfig__Group_5__3__Impl : ( ( rule__ImportConfig__Group_5_3__0 )? ) ;
    public final void rule__ImportConfig__Group_5__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:897:1: ( ( ( rule__ImportConfig__Group_5_3__0 )? ) )
            // InternalHdbti.g:898:1: ( ( rule__ImportConfig__Group_5_3__0 )? )
            {
            // InternalHdbti.g:898:1: ( ( rule__ImportConfig__Group_5_3__0 )? )
            // InternalHdbti.g:899:2: ( rule__ImportConfig__Group_5_3__0 )?
            {
             before(grammarAccess.getImportConfigAccess().getGroup_5_3()); 
            // InternalHdbti.g:900:2: ( rule__ImportConfig__Group_5_3__0 )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==12) ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // InternalHdbti.g:900:3: rule__ImportConfig__Group_5_3__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ImportConfig__Group_5_3__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getImportConfigAccess().getGroup_5_3()); 

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
    // $ANTLR end "rule__ImportConfig__Group_5__3__Impl"


    // $ANTLR start "rule__ImportConfig__Group_5__4"
    // InternalHdbti.g:908:1: rule__ImportConfig__Group_5__4 : rule__ImportConfig__Group_5__4__Impl rule__ImportConfig__Group_5__5 ;
    public final void rule__ImportConfig__Group_5__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:912:1: ( rule__ImportConfig__Group_5__4__Impl rule__ImportConfig__Group_5__5 )
            // InternalHdbti.g:913:2: rule__ImportConfig__Group_5__4__Impl rule__ImportConfig__Group_5__5
            {
            pushFollow(FOLLOW_7);
            rule__ImportConfig__Group_5__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ImportConfig__Group_5__5();

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
    // $ANTLR end "rule__ImportConfig__Group_5__4"


    // $ANTLR start "rule__ImportConfig__Group_5__4__Impl"
    // InternalHdbti.g:920:1: rule__ImportConfig__Group_5__4__Impl : ( ']' ) ;
    public final void rule__ImportConfig__Group_5__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:924:1: ( ( ']' ) )
            // InternalHdbti.g:925:1: ( ']' )
            {
            // InternalHdbti.g:925:1: ( ']' )
            // InternalHdbti.g:926:2: ']'
            {
             before(grammarAccess.getImportConfigAccess().getRightSquareBracketKeyword_5_4()); 
            match(input,18,FOLLOW_2); 
             after(grammarAccess.getImportConfigAccess().getRightSquareBracketKeyword_5_4()); 

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
    // $ANTLR end "rule__ImportConfig__Group_5__4__Impl"


    // $ANTLR start "rule__ImportConfig__Group_5__5"
    // InternalHdbti.g:935:1: rule__ImportConfig__Group_5__5 : rule__ImportConfig__Group_5__5__Impl ;
    public final void rule__ImportConfig__Group_5__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:939:1: ( rule__ImportConfig__Group_5__5__Impl )
            // InternalHdbti.g:940:2: rule__ImportConfig__Group_5__5__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ImportConfig__Group_5__5__Impl();

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
    // $ANTLR end "rule__ImportConfig__Group_5__5"


    // $ANTLR start "rule__ImportConfig__Group_5__5__Impl"
    // InternalHdbti.g:946:1: rule__ImportConfig__Group_5__5__Impl : ( ';' ) ;
    public final void rule__ImportConfig__Group_5__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:950:1: ( ( ';' ) )
            // InternalHdbti.g:951:1: ( ';' )
            {
            // InternalHdbti.g:951:1: ( ';' )
            // InternalHdbti.g:952:2: ';'
            {
             before(grammarAccess.getImportConfigAccess().getSemicolonKeyword_5_5()); 
            match(input,16,FOLLOW_2); 
             after(grammarAccess.getImportConfigAccess().getSemicolonKeyword_5_5()); 

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
    // $ANTLR end "rule__ImportConfig__Group_5__5__Impl"


    // $ANTLR start "rule__ImportConfig__Group_5_3__0"
    // InternalHdbti.g:962:1: rule__ImportConfig__Group_5_3__0 : rule__ImportConfig__Group_5_3__0__Impl rule__ImportConfig__Group_5_3__1 ;
    public final void rule__ImportConfig__Group_5_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:966:1: ( rule__ImportConfig__Group_5_3__0__Impl rule__ImportConfig__Group_5_3__1 )
            // InternalHdbti.g:967:2: rule__ImportConfig__Group_5_3__0__Impl rule__ImportConfig__Group_5_3__1
            {
            pushFollow(FOLLOW_11);
            rule__ImportConfig__Group_5_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ImportConfig__Group_5_3__1();

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
    // $ANTLR end "rule__ImportConfig__Group_5_3__0"


    // $ANTLR start "rule__ImportConfig__Group_5_3__0__Impl"
    // InternalHdbti.g:974:1: rule__ImportConfig__Group_5_3__0__Impl : ( ( rule__ImportConfig__HeaderValuesAssignment_5_3_0 ) ) ;
    public final void rule__ImportConfig__Group_5_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:978:1: ( ( ( rule__ImportConfig__HeaderValuesAssignment_5_3_0 ) ) )
            // InternalHdbti.g:979:1: ( ( rule__ImportConfig__HeaderValuesAssignment_5_3_0 ) )
            {
            // InternalHdbti.g:979:1: ( ( rule__ImportConfig__HeaderValuesAssignment_5_3_0 ) )
            // InternalHdbti.g:980:2: ( rule__ImportConfig__HeaderValuesAssignment_5_3_0 )
            {
             before(grammarAccess.getImportConfigAccess().getHeaderValuesAssignment_5_3_0()); 
            // InternalHdbti.g:981:2: ( rule__ImportConfig__HeaderValuesAssignment_5_3_0 )
            // InternalHdbti.g:981:3: rule__ImportConfig__HeaderValuesAssignment_5_3_0
            {
            pushFollow(FOLLOW_2);
            rule__ImportConfig__HeaderValuesAssignment_5_3_0();

            state._fsp--;


            }

             after(grammarAccess.getImportConfigAccess().getHeaderValuesAssignment_5_3_0()); 

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
    // $ANTLR end "rule__ImportConfig__Group_5_3__0__Impl"


    // $ANTLR start "rule__ImportConfig__Group_5_3__1"
    // InternalHdbti.g:989:1: rule__ImportConfig__Group_5_3__1 : rule__ImportConfig__Group_5_3__1__Impl ;
    public final void rule__ImportConfig__Group_5_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:993:1: ( rule__ImportConfig__Group_5_3__1__Impl )
            // InternalHdbti.g:994:2: rule__ImportConfig__Group_5_3__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ImportConfig__Group_5_3__1__Impl();

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
    // $ANTLR end "rule__ImportConfig__Group_5_3__1"


    // $ANTLR start "rule__ImportConfig__Group_5_3__1__Impl"
    // InternalHdbti.g:1000:1: rule__ImportConfig__Group_5_3__1__Impl : ( ( rule__ImportConfig__Group_5_3_1__0 )* ) ;
    public final void rule__ImportConfig__Group_5_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:1004:1: ( ( ( rule__ImportConfig__Group_5_3_1__0 )* ) )
            // InternalHdbti.g:1005:1: ( ( rule__ImportConfig__Group_5_3_1__0 )* )
            {
            // InternalHdbti.g:1005:1: ( ( rule__ImportConfig__Group_5_3_1__0 )* )
            // InternalHdbti.g:1006:2: ( rule__ImportConfig__Group_5_3_1__0 )*
            {
             before(grammarAccess.getImportConfigAccess().getGroup_5_3_1()); 
            // InternalHdbti.g:1007:2: ( rule__ImportConfig__Group_5_3_1__0 )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==19) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // InternalHdbti.g:1007:3: rule__ImportConfig__Group_5_3_1__0
            	    {
            	    pushFollow(FOLLOW_12);
            	    rule__ImportConfig__Group_5_3_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);

             after(grammarAccess.getImportConfigAccess().getGroup_5_3_1()); 

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
    // $ANTLR end "rule__ImportConfig__Group_5_3__1__Impl"


    // $ANTLR start "rule__ImportConfig__Group_5_3_1__0"
    // InternalHdbti.g:1016:1: rule__ImportConfig__Group_5_3_1__0 : rule__ImportConfig__Group_5_3_1__0__Impl rule__ImportConfig__Group_5_3_1__1 ;
    public final void rule__ImportConfig__Group_5_3_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:1020:1: ( rule__ImportConfig__Group_5_3_1__0__Impl rule__ImportConfig__Group_5_3_1__1 )
            // InternalHdbti.g:1021:2: rule__ImportConfig__Group_5_3_1__0__Impl rule__ImportConfig__Group_5_3_1__1
            {
            pushFollow(FOLLOW_13);
            rule__ImportConfig__Group_5_3_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ImportConfig__Group_5_3_1__1();

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
    // $ANTLR end "rule__ImportConfig__Group_5_3_1__0"


    // $ANTLR start "rule__ImportConfig__Group_5_3_1__0__Impl"
    // InternalHdbti.g:1028:1: rule__ImportConfig__Group_5_3_1__0__Impl : ( ',' ) ;
    public final void rule__ImportConfig__Group_5_3_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:1032:1: ( ( ',' ) )
            // InternalHdbti.g:1033:1: ( ',' )
            {
            // InternalHdbti.g:1033:1: ( ',' )
            // InternalHdbti.g:1034:2: ','
            {
             before(grammarAccess.getImportConfigAccess().getCommaKeyword_5_3_1_0()); 
            match(input,19,FOLLOW_2); 
             after(grammarAccess.getImportConfigAccess().getCommaKeyword_5_3_1_0()); 

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
    // $ANTLR end "rule__ImportConfig__Group_5_3_1__0__Impl"


    // $ANTLR start "rule__ImportConfig__Group_5_3_1__1"
    // InternalHdbti.g:1043:1: rule__ImportConfig__Group_5_3_1__1 : rule__ImportConfig__Group_5_3_1__1__Impl ;
    public final void rule__ImportConfig__Group_5_3_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:1047:1: ( rule__ImportConfig__Group_5_3_1__1__Impl )
            // InternalHdbti.g:1048:2: rule__ImportConfig__Group_5_3_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ImportConfig__Group_5_3_1__1__Impl();

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
    // $ANTLR end "rule__ImportConfig__Group_5_3_1__1"


    // $ANTLR start "rule__ImportConfig__Group_5_3_1__1__Impl"
    // InternalHdbti.g:1054:1: rule__ImportConfig__Group_5_3_1__1__Impl : ( ( rule__ImportConfig__HeaderValuesAssignment_5_3_1_1 ) ) ;
    public final void rule__ImportConfig__Group_5_3_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:1058:1: ( ( ( rule__ImportConfig__HeaderValuesAssignment_5_3_1_1 ) ) )
            // InternalHdbti.g:1059:1: ( ( rule__ImportConfig__HeaderValuesAssignment_5_3_1_1 ) )
            {
            // InternalHdbti.g:1059:1: ( ( rule__ImportConfig__HeaderValuesAssignment_5_3_1_1 ) )
            // InternalHdbti.g:1060:2: ( rule__ImportConfig__HeaderValuesAssignment_5_3_1_1 )
            {
             before(grammarAccess.getImportConfigAccess().getHeaderValuesAssignment_5_3_1_1()); 
            // InternalHdbti.g:1061:2: ( rule__ImportConfig__HeaderValuesAssignment_5_3_1_1 )
            // InternalHdbti.g:1061:3: rule__ImportConfig__HeaderValuesAssignment_5_3_1_1
            {
            pushFollow(FOLLOW_2);
            rule__ImportConfig__HeaderValuesAssignment_5_3_1_1();

            state._fsp--;


            }

             after(grammarAccess.getImportConfigAccess().getHeaderValuesAssignment_5_3_1_1()); 

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
    // $ANTLR end "rule__ImportConfig__Group_5_3_1__1__Impl"


    // $ANTLR start "rule__ImportConfig__Group_6__0"
    // InternalHdbti.g:1070:1: rule__ImportConfig__Group_6__0 : rule__ImportConfig__Group_6__0__Impl rule__ImportConfig__Group_6__1 ;
    public final void rule__ImportConfig__Group_6__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:1074:1: ( rule__ImportConfig__Group_6__0__Impl rule__ImportConfig__Group_6__1 )
            // InternalHdbti.g:1075:2: rule__ImportConfig__Group_6__0__Impl rule__ImportConfig__Group_6__1
            {
            pushFollow(FOLLOW_6);
            rule__ImportConfig__Group_6__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ImportConfig__Group_6__1();

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
    // $ANTLR end "rule__ImportConfig__Group_6__0"


    // $ANTLR start "rule__ImportConfig__Group_6__0__Impl"
    // InternalHdbti.g:1082:1: rule__ImportConfig__Group_6__0__Impl : ( ( rule__ImportConfig__DelimEnclosingAssignment_6_0 ) ) ;
    public final void rule__ImportConfig__Group_6__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:1086:1: ( ( ( rule__ImportConfig__DelimEnclosingAssignment_6_0 ) ) )
            // InternalHdbti.g:1087:1: ( ( rule__ImportConfig__DelimEnclosingAssignment_6_0 ) )
            {
            // InternalHdbti.g:1087:1: ( ( rule__ImportConfig__DelimEnclosingAssignment_6_0 ) )
            // InternalHdbti.g:1088:2: ( rule__ImportConfig__DelimEnclosingAssignment_6_0 )
            {
             before(grammarAccess.getImportConfigAccess().getDelimEnclosingAssignment_6_0()); 
            // InternalHdbti.g:1089:2: ( rule__ImportConfig__DelimEnclosingAssignment_6_0 )
            // InternalHdbti.g:1089:3: rule__ImportConfig__DelimEnclosingAssignment_6_0
            {
            pushFollow(FOLLOW_2);
            rule__ImportConfig__DelimEnclosingAssignment_6_0();

            state._fsp--;


            }

             after(grammarAccess.getImportConfigAccess().getDelimEnclosingAssignment_6_0()); 

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
    // $ANTLR end "rule__ImportConfig__Group_6__0__Impl"


    // $ANTLR start "rule__ImportConfig__Group_6__1"
    // InternalHdbti.g:1097:1: rule__ImportConfig__Group_6__1 : rule__ImportConfig__Group_6__1__Impl rule__ImportConfig__Group_6__2 ;
    public final void rule__ImportConfig__Group_6__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:1101:1: ( rule__ImportConfig__Group_6__1__Impl rule__ImportConfig__Group_6__2 )
            // InternalHdbti.g:1102:2: rule__ImportConfig__Group_6__1__Impl rule__ImportConfig__Group_6__2
            {
            pushFollow(FOLLOW_4);
            rule__ImportConfig__Group_6__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ImportConfig__Group_6__2();

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
    // $ANTLR end "rule__ImportConfig__Group_6__1"


    // $ANTLR start "rule__ImportConfig__Group_6__1__Impl"
    // InternalHdbti.g:1109:1: rule__ImportConfig__Group_6__1__Impl : ( '=' ) ;
    public final void rule__ImportConfig__Group_6__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:1113:1: ( ( '=' ) )
            // InternalHdbti.g:1114:1: ( '=' )
            {
            // InternalHdbti.g:1114:1: ( '=' )
            // InternalHdbti.g:1115:2: '='
            {
             before(grammarAccess.getImportConfigAccess().getEqualsSignKeyword_6_1()); 
            match(input,15,FOLLOW_2); 
             after(grammarAccess.getImportConfigAccess().getEqualsSignKeyword_6_1()); 

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
    // $ANTLR end "rule__ImportConfig__Group_6__1__Impl"


    // $ANTLR start "rule__ImportConfig__Group_6__2"
    // InternalHdbti.g:1124:1: rule__ImportConfig__Group_6__2 : rule__ImportConfig__Group_6__2__Impl rule__ImportConfig__Group_6__3 ;
    public final void rule__ImportConfig__Group_6__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:1128:1: ( rule__ImportConfig__Group_6__2__Impl rule__ImportConfig__Group_6__3 )
            // InternalHdbti.g:1129:2: rule__ImportConfig__Group_6__2__Impl rule__ImportConfig__Group_6__3
            {
            pushFollow(FOLLOW_7);
            rule__ImportConfig__Group_6__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ImportConfig__Group_6__3();

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
    // $ANTLR end "rule__ImportConfig__Group_6__2"


    // $ANTLR start "rule__ImportConfig__Group_6__2__Impl"
    // InternalHdbti.g:1136:1: rule__ImportConfig__Group_6__2__Impl : ( ( rule__ImportConfig__DelimEnclosingValueAssignment_6_2 ) ) ;
    public final void rule__ImportConfig__Group_6__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:1140:1: ( ( ( rule__ImportConfig__DelimEnclosingValueAssignment_6_2 ) ) )
            // InternalHdbti.g:1141:1: ( ( rule__ImportConfig__DelimEnclosingValueAssignment_6_2 ) )
            {
            // InternalHdbti.g:1141:1: ( ( rule__ImportConfig__DelimEnclosingValueAssignment_6_2 ) )
            // InternalHdbti.g:1142:2: ( rule__ImportConfig__DelimEnclosingValueAssignment_6_2 )
            {
             before(grammarAccess.getImportConfigAccess().getDelimEnclosingValueAssignment_6_2()); 
            // InternalHdbti.g:1143:2: ( rule__ImportConfig__DelimEnclosingValueAssignment_6_2 )
            // InternalHdbti.g:1143:3: rule__ImportConfig__DelimEnclosingValueAssignment_6_2
            {
            pushFollow(FOLLOW_2);
            rule__ImportConfig__DelimEnclosingValueAssignment_6_2();

            state._fsp--;


            }

             after(grammarAccess.getImportConfigAccess().getDelimEnclosingValueAssignment_6_2()); 

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
    // $ANTLR end "rule__ImportConfig__Group_6__2__Impl"


    // $ANTLR start "rule__ImportConfig__Group_6__3"
    // InternalHdbti.g:1151:1: rule__ImportConfig__Group_6__3 : rule__ImportConfig__Group_6__3__Impl ;
    public final void rule__ImportConfig__Group_6__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:1155:1: ( rule__ImportConfig__Group_6__3__Impl )
            // InternalHdbti.g:1156:2: rule__ImportConfig__Group_6__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ImportConfig__Group_6__3__Impl();

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
    // $ANTLR end "rule__ImportConfig__Group_6__3"


    // $ANTLR start "rule__ImportConfig__Group_6__3__Impl"
    // InternalHdbti.g:1162:1: rule__ImportConfig__Group_6__3__Impl : ( ';' ) ;
    public final void rule__ImportConfig__Group_6__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:1166:1: ( ( ';' ) )
            // InternalHdbti.g:1167:1: ( ';' )
            {
            // InternalHdbti.g:1167:1: ( ';' )
            // InternalHdbti.g:1168:2: ';'
            {
             before(grammarAccess.getImportConfigAccess().getSemicolonKeyword_6_3()); 
            match(input,16,FOLLOW_2); 
             after(grammarAccess.getImportConfigAccess().getSemicolonKeyword_6_3()); 

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
    // $ANTLR end "rule__ImportConfig__Group_6__3__Impl"


    // $ANTLR start "rule__ImportConfig__Group_7__0"
    // InternalHdbti.g:1178:1: rule__ImportConfig__Group_7__0 : rule__ImportConfig__Group_7__0__Impl rule__ImportConfig__Group_7__1 ;
    public final void rule__ImportConfig__Group_7__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:1182:1: ( rule__ImportConfig__Group_7__0__Impl rule__ImportConfig__Group_7__1 )
            // InternalHdbti.g:1183:2: rule__ImportConfig__Group_7__0__Impl rule__ImportConfig__Group_7__1
            {
            pushFollow(FOLLOW_6);
            rule__ImportConfig__Group_7__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ImportConfig__Group_7__1();

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
    // $ANTLR end "rule__ImportConfig__Group_7__0"


    // $ANTLR start "rule__ImportConfig__Group_7__0__Impl"
    // InternalHdbti.g:1190:1: rule__ImportConfig__Group_7__0__Impl : ( ( rule__ImportConfig__DistinguishEmptyFromNullAssignment_7_0 ) ) ;
    public final void rule__ImportConfig__Group_7__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:1194:1: ( ( ( rule__ImportConfig__DistinguishEmptyFromNullAssignment_7_0 ) ) )
            // InternalHdbti.g:1195:1: ( ( rule__ImportConfig__DistinguishEmptyFromNullAssignment_7_0 ) )
            {
            // InternalHdbti.g:1195:1: ( ( rule__ImportConfig__DistinguishEmptyFromNullAssignment_7_0 ) )
            // InternalHdbti.g:1196:2: ( rule__ImportConfig__DistinguishEmptyFromNullAssignment_7_0 )
            {
             before(grammarAccess.getImportConfigAccess().getDistinguishEmptyFromNullAssignment_7_0()); 
            // InternalHdbti.g:1197:2: ( rule__ImportConfig__DistinguishEmptyFromNullAssignment_7_0 )
            // InternalHdbti.g:1197:3: rule__ImportConfig__DistinguishEmptyFromNullAssignment_7_0
            {
            pushFollow(FOLLOW_2);
            rule__ImportConfig__DistinguishEmptyFromNullAssignment_7_0();

            state._fsp--;


            }

             after(grammarAccess.getImportConfigAccess().getDistinguishEmptyFromNullAssignment_7_0()); 

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
    // $ANTLR end "rule__ImportConfig__Group_7__0__Impl"


    // $ANTLR start "rule__ImportConfig__Group_7__1"
    // InternalHdbti.g:1205:1: rule__ImportConfig__Group_7__1 : rule__ImportConfig__Group_7__1__Impl rule__ImportConfig__Group_7__2 ;
    public final void rule__ImportConfig__Group_7__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:1209:1: ( rule__ImportConfig__Group_7__1__Impl rule__ImportConfig__Group_7__2 )
            // InternalHdbti.g:1210:2: rule__ImportConfig__Group_7__1__Impl rule__ImportConfig__Group_7__2
            {
            pushFollow(FOLLOW_8);
            rule__ImportConfig__Group_7__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ImportConfig__Group_7__2();

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
    // $ANTLR end "rule__ImportConfig__Group_7__1"


    // $ANTLR start "rule__ImportConfig__Group_7__1__Impl"
    // InternalHdbti.g:1217:1: rule__ImportConfig__Group_7__1__Impl : ( '=' ) ;
    public final void rule__ImportConfig__Group_7__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:1221:1: ( ( '=' ) )
            // InternalHdbti.g:1222:1: ( '=' )
            {
            // InternalHdbti.g:1222:1: ( '=' )
            // InternalHdbti.g:1223:2: '='
            {
             before(grammarAccess.getImportConfigAccess().getEqualsSignKeyword_7_1()); 
            match(input,15,FOLLOW_2); 
             after(grammarAccess.getImportConfigAccess().getEqualsSignKeyword_7_1()); 

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
    // $ANTLR end "rule__ImportConfig__Group_7__1__Impl"


    // $ANTLR start "rule__ImportConfig__Group_7__2"
    // InternalHdbti.g:1232:1: rule__ImportConfig__Group_7__2 : rule__ImportConfig__Group_7__2__Impl rule__ImportConfig__Group_7__3 ;
    public final void rule__ImportConfig__Group_7__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:1236:1: ( rule__ImportConfig__Group_7__2__Impl rule__ImportConfig__Group_7__3 )
            // InternalHdbti.g:1237:2: rule__ImportConfig__Group_7__2__Impl rule__ImportConfig__Group_7__3
            {
            pushFollow(FOLLOW_7);
            rule__ImportConfig__Group_7__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ImportConfig__Group_7__3();

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
    // $ANTLR end "rule__ImportConfig__Group_7__2"


    // $ANTLR start "rule__ImportConfig__Group_7__2__Impl"
    // InternalHdbti.g:1244:1: rule__ImportConfig__Group_7__2__Impl : ( ( rule__ImportConfig__DistinguishEmptyFromNullValueAssignment_7_2 ) ) ;
    public final void rule__ImportConfig__Group_7__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:1248:1: ( ( ( rule__ImportConfig__DistinguishEmptyFromNullValueAssignment_7_2 ) ) )
            // InternalHdbti.g:1249:1: ( ( rule__ImportConfig__DistinguishEmptyFromNullValueAssignment_7_2 ) )
            {
            // InternalHdbti.g:1249:1: ( ( rule__ImportConfig__DistinguishEmptyFromNullValueAssignment_7_2 ) )
            // InternalHdbti.g:1250:2: ( rule__ImportConfig__DistinguishEmptyFromNullValueAssignment_7_2 )
            {
             before(grammarAccess.getImportConfigAccess().getDistinguishEmptyFromNullValueAssignment_7_2()); 
            // InternalHdbti.g:1251:2: ( rule__ImportConfig__DistinguishEmptyFromNullValueAssignment_7_2 )
            // InternalHdbti.g:1251:3: rule__ImportConfig__DistinguishEmptyFromNullValueAssignment_7_2
            {
            pushFollow(FOLLOW_2);
            rule__ImportConfig__DistinguishEmptyFromNullValueAssignment_7_2();

            state._fsp--;


            }

             after(grammarAccess.getImportConfigAccess().getDistinguishEmptyFromNullValueAssignment_7_2()); 

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
    // $ANTLR end "rule__ImportConfig__Group_7__2__Impl"


    // $ANTLR start "rule__ImportConfig__Group_7__3"
    // InternalHdbti.g:1259:1: rule__ImportConfig__Group_7__3 : rule__ImportConfig__Group_7__3__Impl ;
    public final void rule__ImportConfig__Group_7__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:1263:1: ( rule__ImportConfig__Group_7__3__Impl )
            // InternalHdbti.g:1264:2: rule__ImportConfig__Group_7__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ImportConfig__Group_7__3__Impl();

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
    // $ANTLR end "rule__ImportConfig__Group_7__3"


    // $ANTLR start "rule__ImportConfig__Group_7__3__Impl"
    // InternalHdbti.g:1270:1: rule__ImportConfig__Group_7__3__Impl : ( ';' ) ;
    public final void rule__ImportConfig__Group_7__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:1274:1: ( ( ';' ) )
            // InternalHdbti.g:1275:1: ( ';' )
            {
            // InternalHdbti.g:1275:1: ( ';' )
            // InternalHdbti.g:1276:2: ';'
            {
             before(grammarAccess.getImportConfigAccess().getSemicolonKeyword_7_3()); 
            match(input,16,FOLLOW_2); 
             after(grammarAccess.getImportConfigAccess().getSemicolonKeyword_7_3()); 

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
    // $ANTLR end "rule__ImportConfig__Group_7__3__Impl"


    // $ANTLR start "rule__ImportConfig__Group_8__0"
    // InternalHdbti.g:1286:1: rule__ImportConfig__Group_8__0 : rule__ImportConfig__Group_8__0__Impl rule__ImportConfig__Group_8__1 ;
    public final void rule__ImportConfig__Group_8__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:1290:1: ( rule__ImportConfig__Group_8__0__Impl rule__ImportConfig__Group_8__1 )
            // InternalHdbti.g:1291:2: rule__ImportConfig__Group_8__0__Impl rule__ImportConfig__Group_8__1
            {
            pushFollow(FOLLOW_14);
            rule__ImportConfig__Group_8__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ImportConfig__Group_8__1();

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
    // $ANTLR end "rule__ImportConfig__Group_8__0"


    // $ANTLR start "rule__ImportConfig__Group_8__0__Impl"
    // InternalHdbti.g:1298:1: rule__ImportConfig__Group_8__0__Impl : ( ( rule__ImportConfig__Group_8_0__0 )? ) ;
    public final void rule__ImportConfig__Group_8__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:1302:1: ( ( ( rule__ImportConfig__Group_8_0__0 )? ) )
            // InternalHdbti.g:1303:1: ( ( rule__ImportConfig__Group_8_0__0 )? )
            {
            // InternalHdbti.g:1303:1: ( ( rule__ImportConfig__Group_8_0__0 )? )
            // InternalHdbti.g:1304:2: ( rule__ImportConfig__Group_8_0__0 )?
            {
             before(grammarAccess.getImportConfigAccess().getGroup_8_0()); 
            // InternalHdbti.g:1305:2: ( rule__ImportConfig__Group_8_0__0 )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==28) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // InternalHdbti.g:1305:3: rule__ImportConfig__Group_8_0__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ImportConfig__Group_8_0__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getImportConfigAccess().getGroup_8_0()); 

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
    // $ANTLR end "rule__ImportConfig__Group_8__0__Impl"


    // $ANTLR start "rule__ImportConfig__Group_8__1"
    // InternalHdbti.g:1313:1: rule__ImportConfig__Group_8__1 : rule__ImportConfig__Group_8__1__Impl ;
    public final void rule__ImportConfig__Group_8__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:1317:1: ( rule__ImportConfig__Group_8__1__Impl )
            // InternalHdbti.g:1318:2: rule__ImportConfig__Group_8__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ImportConfig__Group_8__1__Impl();

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
    // $ANTLR end "rule__ImportConfig__Group_8__1"


    // $ANTLR start "rule__ImportConfig__Group_8__1__Impl"
    // InternalHdbti.g:1324:1: rule__ImportConfig__Group_8__1__Impl : ( '}' ) ;
    public final void rule__ImportConfig__Group_8__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:1328:1: ( ( '}' ) )
            // InternalHdbti.g:1329:1: ( '}' )
            {
            // InternalHdbti.g:1329:1: ( '}' )
            // InternalHdbti.g:1330:2: '}'
            {
             before(grammarAccess.getImportConfigAccess().getRightCurlyBracketKeyword_8_1()); 
            match(input,20,FOLLOW_2); 
             after(grammarAccess.getImportConfigAccess().getRightCurlyBracketKeyword_8_1()); 

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
    // $ANTLR end "rule__ImportConfig__Group_8__1__Impl"


    // $ANTLR start "rule__ImportConfig__Group_8_0__0"
    // InternalHdbti.g:1340:1: rule__ImportConfig__Group_8_0__0 : rule__ImportConfig__Group_8_0__0__Impl rule__ImportConfig__Group_8_0__1 ;
    public final void rule__ImportConfig__Group_8_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:1344:1: ( rule__ImportConfig__Group_8_0__0__Impl rule__ImportConfig__Group_8_0__1 )
            // InternalHdbti.g:1345:2: rule__ImportConfig__Group_8_0__0__Impl rule__ImportConfig__Group_8_0__1
            {
            pushFollow(FOLLOW_6);
            rule__ImportConfig__Group_8_0__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ImportConfig__Group_8_0__1();

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
    // $ANTLR end "rule__ImportConfig__Group_8_0__0"


    // $ANTLR start "rule__ImportConfig__Group_8_0__0__Impl"
    // InternalHdbti.g:1352:1: rule__ImportConfig__Group_8_0__0__Impl : ( ( rule__ImportConfig__UseHeaderNamesAssignment_8_0_0 ) ) ;
    public final void rule__ImportConfig__Group_8_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:1356:1: ( ( ( rule__ImportConfig__UseHeaderNamesAssignment_8_0_0 ) ) )
            // InternalHdbti.g:1357:1: ( ( rule__ImportConfig__UseHeaderNamesAssignment_8_0_0 ) )
            {
            // InternalHdbti.g:1357:1: ( ( rule__ImportConfig__UseHeaderNamesAssignment_8_0_0 ) )
            // InternalHdbti.g:1358:2: ( rule__ImportConfig__UseHeaderNamesAssignment_8_0_0 )
            {
             before(grammarAccess.getImportConfigAccess().getUseHeaderNamesAssignment_8_0_0()); 
            // InternalHdbti.g:1359:2: ( rule__ImportConfig__UseHeaderNamesAssignment_8_0_0 )
            // InternalHdbti.g:1359:3: rule__ImportConfig__UseHeaderNamesAssignment_8_0_0
            {
            pushFollow(FOLLOW_2);
            rule__ImportConfig__UseHeaderNamesAssignment_8_0_0();

            state._fsp--;


            }

             after(grammarAccess.getImportConfigAccess().getUseHeaderNamesAssignment_8_0_0()); 

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
    // $ANTLR end "rule__ImportConfig__Group_8_0__0__Impl"


    // $ANTLR start "rule__ImportConfig__Group_8_0__1"
    // InternalHdbti.g:1367:1: rule__ImportConfig__Group_8_0__1 : rule__ImportConfig__Group_8_0__1__Impl rule__ImportConfig__Group_8_0__2 ;
    public final void rule__ImportConfig__Group_8_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:1371:1: ( rule__ImportConfig__Group_8_0__1__Impl rule__ImportConfig__Group_8_0__2 )
            // InternalHdbti.g:1372:2: rule__ImportConfig__Group_8_0__1__Impl rule__ImportConfig__Group_8_0__2
            {
            pushFollow(FOLLOW_8);
            rule__ImportConfig__Group_8_0__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ImportConfig__Group_8_0__2();

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
    // $ANTLR end "rule__ImportConfig__Group_8_0__1"


    // $ANTLR start "rule__ImportConfig__Group_8_0__1__Impl"
    // InternalHdbti.g:1379:1: rule__ImportConfig__Group_8_0__1__Impl : ( '=' ) ;
    public final void rule__ImportConfig__Group_8_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:1383:1: ( ( '=' ) )
            // InternalHdbti.g:1384:1: ( '=' )
            {
            // InternalHdbti.g:1384:1: ( '=' )
            // InternalHdbti.g:1385:2: '='
            {
             before(grammarAccess.getImportConfigAccess().getEqualsSignKeyword_8_0_1()); 
            match(input,15,FOLLOW_2); 
             after(grammarAccess.getImportConfigAccess().getEqualsSignKeyword_8_0_1()); 

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
    // $ANTLR end "rule__ImportConfig__Group_8_0__1__Impl"


    // $ANTLR start "rule__ImportConfig__Group_8_0__2"
    // InternalHdbti.g:1394:1: rule__ImportConfig__Group_8_0__2 : rule__ImportConfig__Group_8_0__2__Impl rule__ImportConfig__Group_8_0__3 ;
    public final void rule__ImportConfig__Group_8_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:1398:1: ( rule__ImportConfig__Group_8_0__2__Impl rule__ImportConfig__Group_8_0__3 )
            // InternalHdbti.g:1399:2: rule__ImportConfig__Group_8_0__2__Impl rule__ImportConfig__Group_8_0__3
            {
            pushFollow(FOLLOW_7);
            rule__ImportConfig__Group_8_0__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ImportConfig__Group_8_0__3();

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
    // $ANTLR end "rule__ImportConfig__Group_8_0__2"


    // $ANTLR start "rule__ImportConfig__Group_8_0__2__Impl"
    // InternalHdbti.g:1406:1: rule__ImportConfig__Group_8_0__2__Impl : ( ( rule__ImportConfig__UseHeaderNamesValueAssignment_8_0_2 ) ) ;
    public final void rule__ImportConfig__Group_8_0__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:1410:1: ( ( ( rule__ImportConfig__UseHeaderNamesValueAssignment_8_0_2 ) ) )
            // InternalHdbti.g:1411:1: ( ( rule__ImportConfig__UseHeaderNamesValueAssignment_8_0_2 ) )
            {
            // InternalHdbti.g:1411:1: ( ( rule__ImportConfig__UseHeaderNamesValueAssignment_8_0_2 ) )
            // InternalHdbti.g:1412:2: ( rule__ImportConfig__UseHeaderNamesValueAssignment_8_0_2 )
            {
             before(grammarAccess.getImportConfigAccess().getUseHeaderNamesValueAssignment_8_0_2()); 
            // InternalHdbti.g:1413:2: ( rule__ImportConfig__UseHeaderNamesValueAssignment_8_0_2 )
            // InternalHdbti.g:1413:3: rule__ImportConfig__UseHeaderNamesValueAssignment_8_0_2
            {
            pushFollow(FOLLOW_2);
            rule__ImportConfig__UseHeaderNamesValueAssignment_8_0_2();

            state._fsp--;


            }

             after(grammarAccess.getImportConfigAccess().getUseHeaderNamesValueAssignment_8_0_2()); 

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
    // $ANTLR end "rule__ImportConfig__Group_8_0__2__Impl"


    // $ANTLR start "rule__ImportConfig__Group_8_0__3"
    // InternalHdbti.g:1421:1: rule__ImportConfig__Group_8_0__3 : rule__ImportConfig__Group_8_0__3__Impl ;
    public final void rule__ImportConfig__Group_8_0__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:1425:1: ( rule__ImportConfig__Group_8_0__3__Impl )
            // InternalHdbti.g:1426:2: rule__ImportConfig__Group_8_0__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ImportConfig__Group_8_0__3__Impl();

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
    // $ANTLR end "rule__ImportConfig__Group_8_0__3"


    // $ANTLR start "rule__ImportConfig__Group_8_0__3__Impl"
    // InternalHdbti.g:1432:1: rule__ImportConfig__Group_8_0__3__Impl : ( ';' ) ;
    public final void rule__ImportConfig__Group_8_0__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:1436:1: ( ( ';' ) )
            // InternalHdbti.g:1437:1: ( ';' )
            {
            // InternalHdbti.g:1437:1: ( ';' )
            // InternalHdbti.g:1438:2: ';'
            {
             before(grammarAccess.getImportConfigAccess().getSemicolonKeyword_8_0_3()); 
            match(input,16,FOLLOW_2); 
             after(grammarAccess.getImportConfigAccess().getSemicolonKeyword_8_0_3()); 

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
    // $ANTLR end "rule__ImportConfig__Group_8_0__3__Impl"


    // $ANTLR start "rule__Import__Group__0"
    // InternalHdbti.g:1448:1: rule__Import__Group__0 : rule__Import__Group__0__Impl rule__Import__Group__1 ;
    public final void rule__Import__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:1452:1: ( rule__Import__Group__0__Impl rule__Import__Group__1 )
            // InternalHdbti.g:1453:2: rule__Import__Group__0__Impl rule__Import__Group__1
            {
            pushFollow(FOLLOW_6);
            rule__Import__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Import__Group__1();

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
    // $ANTLR end "rule__Import__Group__0"


    // $ANTLR start "rule__Import__Group__0__Impl"
    // InternalHdbti.g:1460:1: rule__Import__Group__0__Impl : ( 'import' ) ;
    public final void rule__Import__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:1464:1: ( ( 'import' ) )
            // InternalHdbti.g:1465:1: ( 'import' )
            {
            // InternalHdbti.g:1465:1: ( 'import' )
            // InternalHdbti.g:1466:2: 'import'
            {
             before(grammarAccess.getImportAccess().getImportKeyword_0()); 
            match(input,21,FOLLOW_2); 
             after(grammarAccess.getImportAccess().getImportKeyword_0()); 

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
    // $ANTLR end "rule__Import__Group__0__Impl"


    // $ANTLR start "rule__Import__Group__1"
    // InternalHdbti.g:1475:1: rule__Import__Group__1 : rule__Import__Group__1__Impl rule__Import__Group__2 ;
    public final void rule__Import__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:1479:1: ( rule__Import__Group__1__Impl rule__Import__Group__2 )
            // InternalHdbti.g:1480:2: rule__Import__Group__1__Impl rule__Import__Group__2
            {
            pushFollow(FOLLOW_9);
            rule__Import__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Import__Group__2();

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
    // $ANTLR end "rule__Import__Group__1"


    // $ANTLR start "rule__Import__Group__1__Impl"
    // InternalHdbti.g:1487:1: rule__Import__Group__1__Impl : ( '=' ) ;
    public final void rule__Import__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:1491:1: ( ( '=' ) )
            // InternalHdbti.g:1492:1: ( '=' )
            {
            // InternalHdbti.g:1492:1: ( '=' )
            // InternalHdbti.g:1493:2: '='
            {
             before(grammarAccess.getImportAccess().getEqualsSignKeyword_1()); 
            match(input,15,FOLLOW_2); 
             after(grammarAccess.getImportAccess().getEqualsSignKeyword_1()); 

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
    // $ANTLR end "rule__Import__Group__1__Impl"


    // $ANTLR start "rule__Import__Group__2"
    // InternalHdbti.g:1502:1: rule__Import__Group__2 : rule__Import__Group__2__Impl rule__Import__Group__3 ;
    public final void rule__Import__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:1506:1: ( rule__Import__Group__2__Impl rule__Import__Group__3 )
            // InternalHdbti.g:1507:2: rule__Import__Group__2__Impl rule__Import__Group__3
            {
            pushFollow(FOLLOW_15);
            rule__Import__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Import__Group__3();

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
    // $ANTLR end "rule__Import__Group__2"


    // $ANTLR start "rule__Import__Group__2__Impl"
    // InternalHdbti.g:1514:1: rule__Import__Group__2__Impl : ( '[' ) ;
    public final void rule__Import__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:1518:1: ( ( '[' ) )
            // InternalHdbti.g:1519:1: ( '[' )
            {
            // InternalHdbti.g:1519:1: ( '[' )
            // InternalHdbti.g:1520:2: '['
            {
             before(grammarAccess.getImportAccess().getLeftSquareBracketKeyword_2()); 
            match(input,17,FOLLOW_2); 
             after(grammarAccess.getImportAccess().getLeftSquareBracketKeyword_2()); 

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
    // $ANTLR end "rule__Import__Group__2__Impl"


    // $ANTLR start "rule__Import__Group__3"
    // InternalHdbti.g:1529:1: rule__Import__Group__3 : rule__Import__Group__3__Impl rule__Import__Group__4 ;
    public final void rule__Import__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:1533:1: ( rule__Import__Group__3__Impl rule__Import__Group__4 )
            // InternalHdbti.g:1534:2: rule__Import__Group__3__Impl rule__Import__Group__4
            {
            pushFollow(FOLLOW_15);
            rule__Import__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Import__Group__4();

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
    // $ANTLR end "rule__Import__Group__3"


    // $ANTLR start "rule__Import__Group__3__Impl"
    // InternalHdbti.g:1541:1: rule__Import__Group__3__Impl : ( ( rule__Import__Group_3__0 )? ) ;
    public final void rule__Import__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:1545:1: ( ( ( rule__Import__Group_3__0 )? ) )
            // InternalHdbti.g:1546:1: ( ( rule__Import__Group_3__0 )? )
            {
            // InternalHdbti.g:1546:1: ( ( rule__Import__Group_3__0 )? )
            // InternalHdbti.g:1547:2: ( rule__Import__Group_3__0 )?
            {
             before(grammarAccess.getImportAccess().getGroup_3()); 
            // InternalHdbti.g:1548:2: ( rule__Import__Group_3__0 )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==14||LA4_0==20||(LA4_0>=23 && LA4_0<=28)) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // InternalHdbti.g:1548:3: rule__Import__Group_3__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Import__Group_3__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getImportAccess().getGroup_3()); 

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
    // $ANTLR end "rule__Import__Group__3__Impl"


    // $ANTLR start "rule__Import__Group__4"
    // InternalHdbti.g:1556:1: rule__Import__Group__4 : rule__Import__Group__4__Impl rule__Import__Group__5 ;
    public final void rule__Import__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:1560:1: ( rule__Import__Group__4__Impl rule__Import__Group__5 )
            // InternalHdbti.g:1561:2: rule__Import__Group__4__Impl rule__Import__Group__5
            {
            pushFollow(FOLLOW_7);
            rule__Import__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Import__Group__5();

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
    // $ANTLR end "rule__Import__Group__4"


    // $ANTLR start "rule__Import__Group__4__Impl"
    // InternalHdbti.g:1568:1: rule__Import__Group__4__Impl : ( ']' ) ;
    public final void rule__Import__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:1572:1: ( ( ']' ) )
            // InternalHdbti.g:1573:1: ( ']' )
            {
            // InternalHdbti.g:1573:1: ( ']' )
            // InternalHdbti.g:1574:2: ']'
            {
             before(grammarAccess.getImportAccess().getRightSquareBracketKeyword_4()); 
            match(input,18,FOLLOW_2); 
             after(grammarAccess.getImportAccess().getRightSquareBracketKeyword_4()); 

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
    // $ANTLR end "rule__Import__Group__4__Impl"


    // $ANTLR start "rule__Import__Group__5"
    // InternalHdbti.g:1583:1: rule__Import__Group__5 : rule__Import__Group__5__Impl ;
    public final void rule__Import__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:1587:1: ( rule__Import__Group__5__Impl )
            // InternalHdbti.g:1588:2: rule__Import__Group__5__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Import__Group__5__Impl();

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
    // $ANTLR end "rule__Import__Group__5"


    // $ANTLR start "rule__Import__Group__5__Impl"
    // InternalHdbti.g:1594:1: rule__Import__Group__5__Impl : ( ';' ) ;
    public final void rule__Import__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:1598:1: ( ( ';' ) )
            // InternalHdbti.g:1599:1: ( ';' )
            {
            // InternalHdbti.g:1599:1: ( ';' )
            // InternalHdbti.g:1600:2: ';'
            {
             before(grammarAccess.getImportAccess().getSemicolonKeyword_5()); 
            match(input,16,FOLLOW_2); 
             after(grammarAccess.getImportAccess().getSemicolonKeyword_5()); 

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
    // $ANTLR end "rule__Import__Group__5__Impl"


    // $ANTLR start "rule__Import__Group_3__0"
    // InternalHdbti.g:1610:1: rule__Import__Group_3__0 : rule__Import__Group_3__0__Impl rule__Import__Group_3__1 ;
    public final void rule__Import__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:1614:1: ( rule__Import__Group_3__0__Impl rule__Import__Group_3__1 )
            // InternalHdbti.g:1615:2: rule__Import__Group_3__0__Impl rule__Import__Group_3__1
            {
            pushFollow(FOLLOW_11);
            rule__Import__Group_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Import__Group_3__1();

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
    // $ANTLR end "rule__Import__Group_3__0"


    // $ANTLR start "rule__Import__Group_3__0__Impl"
    // InternalHdbti.g:1622:1: rule__Import__Group_3__0__Impl : ( ( rule__Import__ValuesAssignment_3_0 ) ) ;
    public final void rule__Import__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:1626:1: ( ( ( rule__Import__ValuesAssignment_3_0 ) ) )
            // InternalHdbti.g:1627:1: ( ( rule__Import__ValuesAssignment_3_0 ) )
            {
            // InternalHdbti.g:1627:1: ( ( rule__Import__ValuesAssignment_3_0 ) )
            // InternalHdbti.g:1628:2: ( rule__Import__ValuesAssignment_3_0 )
            {
             before(grammarAccess.getImportAccess().getValuesAssignment_3_0()); 
            // InternalHdbti.g:1629:2: ( rule__Import__ValuesAssignment_3_0 )
            // InternalHdbti.g:1629:3: rule__Import__ValuesAssignment_3_0
            {
            pushFollow(FOLLOW_2);
            rule__Import__ValuesAssignment_3_0();

            state._fsp--;


            }

             after(grammarAccess.getImportAccess().getValuesAssignment_3_0()); 

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
    // $ANTLR end "rule__Import__Group_3__0__Impl"


    // $ANTLR start "rule__Import__Group_3__1"
    // InternalHdbti.g:1637:1: rule__Import__Group_3__1 : rule__Import__Group_3__1__Impl ;
    public final void rule__Import__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:1641:1: ( rule__Import__Group_3__1__Impl )
            // InternalHdbti.g:1642:2: rule__Import__Group_3__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Import__Group_3__1__Impl();

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
    // $ANTLR end "rule__Import__Group_3__1"


    // $ANTLR start "rule__Import__Group_3__1__Impl"
    // InternalHdbti.g:1648:1: rule__Import__Group_3__1__Impl : ( ( rule__Import__Group_3_1__0 )* ) ;
    public final void rule__Import__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:1652:1: ( ( ( rule__Import__Group_3_1__0 )* ) )
            // InternalHdbti.g:1653:1: ( ( rule__Import__Group_3_1__0 )* )
            {
            // InternalHdbti.g:1653:1: ( ( rule__Import__Group_3_1__0 )* )
            // InternalHdbti.g:1654:2: ( rule__Import__Group_3_1__0 )*
            {
             before(grammarAccess.getImportAccess().getGroup_3_1()); 
            // InternalHdbti.g:1655:2: ( rule__Import__Group_3_1__0 )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==19) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // InternalHdbti.g:1655:3: rule__Import__Group_3_1__0
            	    {
            	    pushFollow(FOLLOW_12);
            	    rule__Import__Group_3_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);

             after(grammarAccess.getImportAccess().getGroup_3_1()); 

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
    // $ANTLR end "rule__Import__Group_3__1__Impl"


    // $ANTLR start "rule__Import__Group_3_1__0"
    // InternalHdbti.g:1664:1: rule__Import__Group_3_1__0 : rule__Import__Group_3_1__0__Impl rule__Import__Group_3_1__1 ;
    public final void rule__Import__Group_3_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:1668:1: ( rule__Import__Group_3_1__0__Impl rule__Import__Group_3_1__1 )
            // InternalHdbti.g:1669:2: rule__Import__Group_3_1__0__Impl rule__Import__Group_3_1__1
            {
            pushFollow(FOLLOW_16);
            rule__Import__Group_3_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Import__Group_3_1__1();

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
    // $ANTLR end "rule__Import__Group_3_1__0"


    // $ANTLR start "rule__Import__Group_3_1__0__Impl"
    // InternalHdbti.g:1676:1: rule__Import__Group_3_1__0__Impl : ( ',' ) ;
    public final void rule__Import__Group_3_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:1680:1: ( ( ',' ) )
            // InternalHdbti.g:1681:1: ( ',' )
            {
            // InternalHdbti.g:1681:1: ( ',' )
            // InternalHdbti.g:1682:2: ','
            {
             before(grammarAccess.getImportAccess().getCommaKeyword_3_1_0()); 
            match(input,19,FOLLOW_2); 
             after(grammarAccess.getImportAccess().getCommaKeyword_3_1_0()); 

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
    // $ANTLR end "rule__Import__Group_3_1__0__Impl"


    // $ANTLR start "rule__Import__Group_3_1__1"
    // InternalHdbti.g:1691:1: rule__Import__Group_3_1__1 : rule__Import__Group_3_1__1__Impl ;
    public final void rule__Import__Group_3_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:1695:1: ( rule__Import__Group_3_1__1__Impl )
            // InternalHdbti.g:1696:2: rule__Import__Group_3_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Import__Group_3_1__1__Impl();

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
    // $ANTLR end "rule__Import__Group_3_1__1"


    // $ANTLR start "rule__Import__Group_3_1__1__Impl"
    // InternalHdbti.g:1702:1: rule__Import__Group_3_1__1__Impl : ( ( rule__Import__ValuesAssignment_3_1_1 ) ) ;
    public final void rule__Import__Group_3_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:1706:1: ( ( ( rule__Import__ValuesAssignment_3_1_1 ) ) )
            // InternalHdbti.g:1707:1: ( ( rule__Import__ValuesAssignment_3_1_1 ) )
            {
            // InternalHdbti.g:1707:1: ( ( rule__Import__ValuesAssignment_3_1_1 ) )
            // InternalHdbti.g:1708:2: ( rule__Import__ValuesAssignment_3_1_1 )
            {
             before(grammarAccess.getImportAccess().getValuesAssignment_3_1_1()); 
            // InternalHdbti.g:1709:2: ( rule__Import__ValuesAssignment_3_1_1 )
            // InternalHdbti.g:1709:3: rule__Import__ValuesAssignment_3_1_1
            {
            pushFollow(FOLLOW_2);
            rule__Import__ValuesAssignment_3_1_1();

            state._fsp--;


            }

             after(grammarAccess.getImportAccess().getValuesAssignment_3_1_1()); 

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
    // $ANTLR end "rule__Import__Group_3_1__1__Impl"


    // $ANTLR start "rule__ImportConfig__UnorderedGroup"
    // InternalHdbti.g:1718:1: rule__ImportConfig__UnorderedGroup : rule__ImportConfig__UnorderedGroup__0 {...}?;
    public final void rule__ImportConfig__UnorderedGroup() throws RecognitionException {

        		int stackSize = keepStackSize();
        		getUnorderedGroupHelper().enter(grammarAccess.getImportConfigAccess().getUnorderedGroup());
        	
        try {
            // InternalHdbti.g:1723:1: ( rule__ImportConfig__UnorderedGroup__0 {...}?)
            // InternalHdbti.g:1724:2: rule__ImportConfig__UnorderedGroup__0 {...}?
            {
            pushFollow(FOLLOW_2);
            rule__ImportConfig__UnorderedGroup__0();

            state._fsp--;

            if ( ! getUnorderedGroupHelper().canLeave(grammarAccess.getImportConfigAccess().getUnorderedGroup()) ) {
                throw new FailedPredicateException(input, "rule__ImportConfig__UnorderedGroup", "getUnorderedGroupHelper().canLeave(grammarAccess.getImportConfigAccess().getUnorderedGroup())");
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	getUnorderedGroupHelper().leave(grammarAccess.getImportConfigAccess().getUnorderedGroup());
            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ImportConfig__UnorderedGroup"


    // $ANTLR start "rule__ImportConfig__UnorderedGroup__Impl"
    // InternalHdbti.g:1732:1: rule__ImportConfig__UnorderedGroup__Impl : ( ({...}? => ( ( ( rule__ImportConfig__Group_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ImportConfig__Group_1__0 ) ) ) ) | ({...}? => ( ( ( rule__ImportConfig__Group_2__0 ) ) ) ) | ({...}? => ( ( ( rule__ImportConfig__Group_3__0 ) ) ) ) | ({...}? => ( ( ( rule__ImportConfig__Group_4__0 ) ) ) ) | ({...}? => ( ( ( rule__ImportConfig__Group_5__0 ) ) ) ) | ({...}? => ( ( ( rule__ImportConfig__Group_6__0 ) ) ) ) | ({...}? => ( ( ( rule__ImportConfig__Group_7__0 ) ) ) ) | ({...}? => ( ( ( rule__ImportConfig__Group_8__0 ) ) ) ) ) ;
    public final void rule__ImportConfig__UnorderedGroup__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        		boolean selected = false;
        	
        try {
            // InternalHdbti.g:1737:1: ( ( ({...}? => ( ( ( rule__ImportConfig__Group_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ImportConfig__Group_1__0 ) ) ) ) | ({...}? => ( ( ( rule__ImportConfig__Group_2__0 ) ) ) ) | ({...}? => ( ( ( rule__ImportConfig__Group_3__0 ) ) ) ) | ({...}? => ( ( ( rule__ImportConfig__Group_4__0 ) ) ) ) | ({...}? => ( ( ( rule__ImportConfig__Group_5__0 ) ) ) ) | ({...}? => ( ( ( rule__ImportConfig__Group_6__0 ) ) ) ) | ({...}? => ( ( ( rule__ImportConfig__Group_7__0 ) ) ) ) | ({...}? => ( ( ( rule__ImportConfig__Group_8__0 ) ) ) ) ) )
            // InternalHdbti.g:1738:3: ( ({...}? => ( ( ( rule__ImportConfig__Group_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ImportConfig__Group_1__0 ) ) ) ) | ({...}? => ( ( ( rule__ImportConfig__Group_2__0 ) ) ) ) | ({...}? => ( ( ( rule__ImportConfig__Group_3__0 ) ) ) ) | ({...}? => ( ( ( rule__ImportConfig__Group_4__0 ) ) ) ) | ({...}? => ( ( ( rule__ImportConfig__Group_5__0 ) ) ) ) | ({...}? => ( ( ( rule__ImportConfig__Group_6__0 ) ) ) ) | ({...}? => ( ( ( rule__ImportConfig__Group_7__0 ) ) ) ) | ({...}? => ( ( ( rule__ImportConfig__Group_8__0 ) ) ) ) )
            {
            // InternalHdbti.g:1738:3: ( ({...}? => ( ( ( rule__ImportConfig__Group_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ImportConfig__Group_1__0 ) ) ) ) | ({...}? => ( ( ( rule__ImportConfig__Group_2__0 ) ) ) ) | ({...}? => ( ( ( rule__ImportConfig__Group_3__0 ) ) ) ) | ({...}? => ( ( ( rule__ImportConfig__Group_4__0 ) ) ) ) | ({...}? => ( ( ( rule__ImportConfig__Group_5__0 ) ) ) ) | ({...}? => ( ( ( rule__ImportConfig__Group_6__0 ) ) ) ) | ({...}? => ( ( ( rule__ImportConfig__Group_7__0 ) ) ) ) | ({...}? => ( ( ( rule__ImportConfig__Group_8__0 ) ) ) ) )
            int alt6=9;
            alt6 = dfa6.predict(input);
            switch (alt6) {
                case 1 :
                    // InternalHdbti.g:1739:3: ({...}? => ( ( ( rule__ImportConfig__Group_0__0 ) ) ) )
                    {
                    // InternalHdbti.g:1739:3: ({...}? => ( ( ( rule__ImportConfig__Group_0__0 ) ) ) )
                    // InternalHdbti.g:1740:4: {...}? => ( ( ( rule__ImportConfig__Group_0__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 0) ) {
                        throw new FailedPredicateException(input, "rule__ImportConfig__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 0)");
                    }
                    // InternalHdbti.g:1740:106: ( ( ( rule__ImportConfig__Group_0__0 ) ) )
                    // InternalHdbti.g:1741:5: ( ( rule__ImportConfig__Group_0__0 ) )
                    {

                    					getUnorderedGroupHelper().select(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 0);
                    				

                    					selected = true;
                    				
                    // InternalHdbti.g:1747:5: ( ( rule__ImportConfig__Group_0__0 ) )
                    // InternalHdbti.g:1748:6: ( rule__ImportConfig__Group_0__0 )
                    {
                     before(grammarAccess.getImportConfigAccess().getGroup_0()); 
                    // InternalHdbti.g:1749:6: ( rule__ImportConfig__Group_0__0 )
                    // InternalHdbti.g:1749:7: rule__ImportConfig__Group_0__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ImportConfig__Group_0__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getImportConfigAccess().getGroup_0()); 

                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalHdbti.g:1754:3: ({...}? => ( ( ( rule__ImportConfig__Group_1__0 ) ) ) )
                    {
                    // InternalHdbti.g:1754:3: ({...}? => ( ( ( rule__ImportConfig__Group_1__0 ) ) ) )
                    // InternalHdbti.g:1755:4: {...}? => ( ( ( rule__ImportConfig__Group_1__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 1) ) {
                        throw new FailedPredicateException(input, "rule__ImportConfig__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 1)");
                    }
                    // InternalHdbti.g:1755:106: ( ( ( rule__ImportConfig__Group_1__0 ) ) )
                    // InternalHdbti.g:1756:5: ( ( rule__ImportConfig__Group_1__0 ) )
                    {

                    					getUnorderedGroupHelper().select(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 1);
                    				

                    					selected = true;
                    				
                    // InternalHdbti.g:1762:5: ( ( rule__ImportConfig__Group_1__0 ) )
                    // InternalHdbti.g:1763:6: ( rule__ImportConfig__Group_1__0 )
                    {
                     before(grammarAccess.getImportConfigAccess().getGroup_1()); 
                    // InternalHdbti.g:1764:6: ( rule__ImportConfig__Group_1__0 )
                    // InternalHdbti.g:1764:7: rule__ImportConfig__Group_1__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ImportConfig__Group_1__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getImportConfigAccess().getGroup_1()); 

                    }


                    }


                    }


                    }
                    break;
                case 3 :
                    // InternalHdbti.g:1769:3: ({...}? => ( ( ( rule__ImportConfig__Group_2__0 ) ) ) )
                    {
                    // InternalHdbti.g:1769:3: ({...}? => ( ( ( rule__ImportConfig__Group_2__0 ) ) ) )
                    // InternalHdbti.g:1770:4: {...}? => ( ( ( rule__ImportConfig__Group_2__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 2) ) {
                        throw new FailedPredicateException(input, "rule__ImportConfig__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 2)");
                    }
                    // InternalHdbti.g:1770:106: ( ( ( rule__ImportConfig__Group_2__0 ) ) )
                    // InternalHdbti.g:1771:5: ( ( rule__ImportConfig__Group_2__0 ) )
                    {

                    					getUnorderedGroupHelper().select(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 2);
                    				

                    					selected = true;
                    				
                    // InternalHdbti.g:1777:5: ( ( rule__ImportConfig__Group_2__0 ) )
                    // InternalHdbti.g:1778:6: ( rule__ImportConfig__Group_2__0 )
                    {
                     before(grammarAccess.getImportConfigAccess().getGroup_2()); 
                    // InternalHdbti.g:1779:6: ( rule__ImportConfig__Group_2__0 )
                    // InternalHdbti.g:1779:7: rule__ImportConfig__Group_2__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ImportConfig__Group_2__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getImportConfigAccess().getGroup_2()); 

                    }


                    }


                    }


                    }
                    break;
                case 4 :
                    // InternalHdbti.g:1784:3: ({...}? => ( ( ( rule__ImportConfig__Group_3__0 ) ) ) )
                    {
                    // InternalHdbti.g:1784:3: ({...}? => ( ( ( rule__ImportConfig__Group_3__0 ) ) ) )
                    // InternalHdbti.g:1785:4: {...}? => ( ( ( rule__ImportConfig__Group_3__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 3) ) {
                        throw new FailedPredicateException(input, "rule__ImportConfig__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 3)");
                    }
                    // InternalHdbti.g:1785:106: ( ( ( rule__ImportConfig__Group_3__0 ) ) )
                    // InternalHdbti.g:1786:5: ( ( rule__ImportConfig__Group_3__0 ) )
                    {

                    					getUnorderedGroupHelper().select(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 3);
                    				

                    					selected = true;
                    				
                    // InternalHdbti.g:1792:5: ( ( rule__ImportConfig__Group_3__0 ) )
                    // InternalHdbti.g:1793:6: ( rule__ImportConfig__Group_3__0 )
                    {
                     before(grammarAccess.getImportConfigAccess().getGroup_3()); 
                    // InternalHdbti.g:1794:6: ( rule__ImportConfig__Group_3__0 )
                    // InternalHdbti.g:1794:7: rule__ImportConfig__Group_3__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ImportConfig__Group_3__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getImportConfigAccess().getGroup_3()); 

                    }


                    }


                    }


                    }
                    break;
                case 5 :
                    // InternalHdbti.g:1799:3: ({...}? => ( ( ( rule__ImportConfig__Group_4__0 ) ) ) )
                    {
                    // InternalHdbti.g:1799:3: ({...}? => ( ( ( rule__ImportConfig__Group_4__0 ) ) ) )
                    // InternalHdbti.g:1800:4: {...}? => ( ( ( rule__ImportConfig__Group_4__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 4) ) {
                        throw new FailedPredicateException(input, "rule__ImportConfig__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 4)");
                    }
                    // InternalHdbti.g:1800:106: ( ( ( rule__ImportConfig__Group_4__0 ) ) )
                    // InternalHdbti.g:1801:5: ( ( rule__ImportConfig__Group_4__0 ) )
                    {

                    					getUnorderedGroupHelper().select(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 4);
                    				

                    					selected = true;
                    				
                    // InternalHdbti.g:1807:5: ( ( rule__ImportConfig__Group_4__0 ) )
                    // InternalHdbti.g:1808:6: ( rule__ImportConfig__Group_4__0 )
                    {
                     before(grammarAccess.getImportConfigAccess().getGroup_4()); 
                    // InternalHdbti.g:1809:6: ( rule__ImportConfig__Group_4__0 )
                    // InternalHdbti.g:1809:7: rule__ImportConfig__Group_4__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ImportConfig__Group_4__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getImportConfigAccess().getGroup_4()); 

                    }


                    }


                    }


                    }
                    break;
                case 6 :
                    // InternalHdbti.g:1814:3: ({...}? => ( ( ( rule__ImportConfig__Group_5__0 ) ) ) )
                    {
                    // InternalHdbti.g:1814:3: ({...}? => ( ( ( rule__ImportConfig__Group_5__0 ) ) ) )
                    // InternalHdbti.g:1815:4: {...}? => ( ( ( rule__ImportConfig__Group_5__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 5) ) {
                        throw new FailedPredicateException(input, "rule__ImportConfig__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 5)");
                    }
                    // InternalHdbti.g:1815:106: ( ( ( rule__ImportConfig__Group_5__0 ) ) )
                    // InternalHdbti.g:1816:5: ( ( rule__ImportConfig__Group_5__0 ) )
                    {

                    					getUnorderedGroupHelper().select(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 5);
                    				

                    					selected = true;
                    				
                    // InternalHdbti.g:1822:5: ( ( rule__ImportConfig__Group_5__0 ) )
                    // InternalHdbti.g:1823:6: ( rule__ImportConfig__Group_5__0 )
                    {
                     before(grammarAccess.getImportConfigAccess().getGroup_5()); 
                    // InternalHdbti.g:1824:6: ( rule__ImportConfig__Group_5__0 )
                    // InternalHdbti.g:1824:7: rule__ImportConfig__Group_5__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ImportConfig__Group_5__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getImportConfigAccess().getGroup_5()); 

                    }


                    }


                    }


                    }
                    break;
                case 7 :
                    // InternalHdbti.g:1829:3: ({...}? => ( ( ( rule__ImportConfig__Group_6__0 ) ) ) )
                    {
                    // InternalHdbti.g:1829:3: ({...}? => ( ( ( rule__ImportConfig__Group_6__0 ) ) ) )
                    // InternalHdbti.g:1830:4: {...}? => ( ( ( rule__ImportConfig__Group_6__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 6) ) {
                        throw new FailedPredicateException(input, "rule__ImportConfig__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 6)");
                    }
                    // InternalHdbti.g:1830:106: ( ( ( rule__ImportConfig__Group_6__0 ) ) )
                    // InternalHdbti.g:1831:5: ( ( rule__ImportConfig__Group_6__0 ) )
                    {

                    					getUnorderedGroupHelper().select(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 6);
                    				

                    					selected = true;
                    				
                    // InternalHdbti.g:1837:5: ( ( rule__ImportConfig__Group_6__0 ) )
                    // InternalHdbti.g:1838:6: ( rule__ImportConfig__Group_6__0 )
                    {
                     before(grammarAccess.getImportConfigAccess().getGroup_6()); 
                    // InternalHdbti.g:1839:6: ( rule__ImportConfig__Group_6__0 )
                    // InternalHdbti.g:1839:7: rule__ImportConfig__Group_6__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ImportConfig__Group_6__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getImportConfigAccess().getGroup_6()); 

                    }


                    }


                    }


                    }
                    break;
                case 8 :
                    // InternalHdbti.g:1844:3: ({...}? => ( ( ( rule__ImportConfig__Group_7__0 ) ) ) )
                    {
                    // InternalHdbti.g:1844:3: ({...}? => ( ( ( rule__ImportConfig__Group_7__0 ) ) ) )
                    // InternalHdbti.g:1845:4: {...}? => ( ( ( rule__ImportConfig__Group_7__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 7) ) {
                        throw new FailedPredicateException(input, "rule__ImportConfig__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 7)");
                    }
                    // InternalHdbti.g:1845:106: ( ( ( rule__ImportConfig__Group_7__0 ) ) )
                    // InternalHdbti.g:1846:5: ( ( rule__ImportConfig__Group_7__0 ) )
                    {

                    					getUnorderedGroupHelper().select(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 7);
                    				

                    					selected = true;
                    				
                    // InternalHdbti.g:1852:5: ( ( rule__ImportConfig__Group_7__0 ) )
                    // InternalHdbti.g:1853:6: ( rule__ImportConfig__Group_7__0 )
                    {
                     before(grammarAccess.getImportConfigAccess().getGroup_7()); 
                    // InternalHdbti.g:1854:6: ( rule__ImportConfig__Group_7__0 )
                    // InternalHdbti.g:1854:7: rule__ImportConfig__Group_7__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ImportConfig__Group_7__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getImportConfigAccess().getGroup_7()); 

                    }


                    }


                    }


                    }
                    break;
                case 9 :
                    // InternalHdbti.g:1859:3: ({...}? => ( ( ( rule__ImportConfig__Group_8__0 ) ) ) )
                    {
                    // InternalHdbti.g:1859:3: ({...}? => ( ( ( rule__ImportConfig__Group_8__0 ) ) ) )
                    // InternalHdbti.g:1860:4: {...}? => ( ( ( rule__ImportConfig__Group_8__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 8) ) {
                        throw new FailedPredicateException(input, "rule__ImportConfig__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 8)");
                    }
                    // InternalHdbti.g:1860:106: ( ( ( rule__ImportConfig__Group_8__0 ) ) )
                    // InternalHdbti.g:1861:5: ( ( rule__ImportConfig__Group_8__0 ) )
                    {

                    					getUnorderedGroupHelper().select(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 8);
                    				

                    					selected = true;
                    				
                    // InternalHdbti.g:1867:5: ( ( rule__ImportConfig__Group_8__0 ) )
                    // InternalHdbti.g:1868:6: ( rule__ImportConfig__Group_8__0 )
                    {
                     before(grammarAccess.getImportConfigAccess().getGroup_8()); 
                    // InternalHdbti.g:1869:6: ( rule__ImportConfig__Group_8__0 )
                    // InternalHdbti.g:1869:7: rule__ImportConfig__Group_8__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ImportConfig__Group_8__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getImportConfigAccess().getGroup_8()); 

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
            		getUnorderedGroupHelper().returnFromSelection(grammarAccess.getImportConfigAccess().getUnorderedGroup());
            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ImportConfig__UnorderedGroup__Impl"


    // $ANTLR start "rule__ImportConfig__UnorderedGroup__0"
    // InternalHdbti.g:1882:1: rule__ImportConfig__UnorderedGroup__0 : rule__ImportConfig__UnorderedGroup__Impl ( rule__ImportConfig__UnorderedGroup__1 )? ;
    public final void rule__ImportConfig__UnorderedGroup__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:1886:1: ( rule__ImportConfig__UnorderedGroup__Impl ( rule__ImportConfig__UnorderedGroup__1 )? )
            // InternalHdbti.g:1887:2: rule__ImportConfig__UnorderedGroup__Impl ( rule__ImportConfig__UnorderedGroup__1 )?
            {
            pushFollow(FOLLOW_17);
            rule__ImportConfig__UnorderedGroup__Impl();

            state._fsp--;

            // InternalHdbti.g:1888:2: ( rule__ImportConfig__UnorderedGroup__1 )?
            int alt7=2;
            alt7 = dfa7.predict(input);
            switch (alt7) {
                case 1 :
                    // InternalHdbti.g:1888:2: rule__ImportConfig__UnorderedGroup__1
                    {
                    pushFollow(FOLLOW_2);
                    rule__ImportConfig__UnorderedGroup__1();

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
    // $ANTLR end "rule__ImportConfig__UnorderedGroup__0"


    // $ANTLR start "rule__ImportConfig__UnorderedGroup__1"
    // InternalHdbti.g:1894:1: rule__ImportConfig__UnorderedGroup__1 : rule__ImportConfig__UnorderedGroup__Impl ( rule__ImportConfig__UnorderedGroup__2 )? ;
    public final void rule__ImportConfig__UnorderedGroup__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:1898:1: ( rule__ImportConfig__UnorderedGroup__Impl ( rule__ImportConfig__UnorderedGroup__2 )? )
            // InternalHdbti.g:1899:2: rule__ImportConfig__UnorderedGroup__Impl ( rule__ImportConfig__UnorderedGroup__2 )?
            {
            pushFollow(FOLLOW_17);
            rule__ImportConfig__UnorderedGroup__Impl();

            state._fsp--;

            // InternalHdbti.g:1900:2: ( rule__ImportConfig__UnorderedGroup__2 )?
            int alt8=2;
            alt8 = dfa8.predict(input);
            switch (alt8) {
                case 1 :
                    // InternalHdbti.g:1900:2: rule__ImportConfig__UnorderedGroup__2
                    {
                    pushFollow(FOLLOW_2);
                    rule__ImportConfig__UnorderedGroup__2();

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
    // $ANTLR end "rule__ImportConfig__UnorderedGroup__1"


    // $ANTLR start "rule__ImportConfig__UnorderedGroup__2"
    // InternalHdbti.g:1906:1: rule__ImportConfig__UnorderedGroup__2 : rule__ImportConfig__UnorderedGroup__Impl ( rule__ImportConfig__UnorderedGroup__3 )? ;
    public final void rule__ImportConfig__UnorderedGroup__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:1910:1: ( rule__ImportConfig__UnorderedGroup__Impl ( rule__ImportConfig__UnorderedGroup__3 )? )
            // InternalHdbti.g:1911:2: rule__ImportConfig__UnorderedGroup__Impl ( rule__ImportConfig__UnorderedGroup__3 )?
            {
            pushFollow(FOLLOW_17);
            rule__ImportConfig__UnorderedGroup__Impl();

            state._fsp--;

            // InternalHdbti.g:1912:2: ( rule__ImportConfig__UnorderedGroup__3 )?
            int alt9=2;
            alt9 = dfa9.predict(input);
            switch (alt9) {
                case 1 :
                    // InternalHdbti.g:1912:2: rule__ImportConfig__UnorderedGroup__3
                    {
                    pushFollow(FOLLOW_2);
                    rule__ImportConfig__UnorderedGroup__3();

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
    // $ANTLR end "rule__ImportConfig__UnorderedGroup__2"


    // $ANTLR start "rule__ImportConfig__UnorderedGroup__3"
    // InternalHdbti.g:1918:1: rule__ImportConfig__UnorderedGroup__3 : rule__ImportConfig__UnorderedGroup__Impl ( rule__ImportConfig__UnorderedGroup__4 )? ;
    public final void rule__ImportConfig__UnorderedGroup__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:1922:1: ( rule__ImportConfig__UnorderedGroup__Impl ( rule__ImportConfig__UnorderedGroup__4 )? )
            // InternalHdbti.g:1923:2: rule__ImportConfig__UnorderedGroup__Impl ( rule__ImportConfig__UnorderedGroup__4 )?
            {
            pushFollow(FOLLOW_17);
            rule__ImportConfig__UnorderedGroup__Impl();

            state._fsp--;

            // InternalHdbti.g:1924:2: ( rule__ImportConfig__UnorderedGroup__4 )?
            int alt10=2;
            alt10 = dfa10.predict(input);
            switch (alt10) {
                case 1 :
                    // InternalHdbti.g:1924:2: rule__ImportConfig__UnorderedGroup__4
                    {
                    pushFollow(FOLLOW_2);
                    rule__ImportConfig__UnorderedGroup__4();

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
    // $ANTLR end "rule__ImportConfig__UnorderedGroup__3"


    // $ANTLR start "rule__ImportConfig__UnorderedGroup__4"
    // InternalHdbti.g:1930:1: rule__ImportConfig__UnorderedGroup__4 : rule__ImportConfig__UnorderedGroup__Impl ( rule__ImportConfig__UnorderedGroup__5 )? ;
    public final void rule__ImportConfig__UnorderedGroup__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:1934:1: ( rule__ImportConfig__UnorderedGroup__Impl ( rule__ImportConfig__UnorderedGroup__5 )? )
            // InternalHdbti.g:1935:2: rule__ImportConfig__UnorderedGroup__Impl ( rule__ImportConfig__UnorderedGroup__5 )?
            {
            pushFollow(FOLLOW_17);
            rule__ImportConfig__UnorderedGroup__Impl();

            state._fsp--;

            // InternalHdbti.g:1936:2: ( rule__ImportConfig__UnorderedGroup__5 )?
            int alt11=2;
            alt11 = dfa11.predict(input);
            switch (alt11) {
                case 1 :
                    // InternalHdbti.g:1936:2: rule__ImportConfig__UnorderedGroup__5
                    {
                    pushFollow(FOLLOW_2);
                    rule__ImportConfig__UnorderedGroup__5();

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
    // $ANTLR end "rule__ImportConfig__UnorderedGroup__4"


    // $ANTLR start "rule__ImportConfig__UnorderedGroup__5"
    // InternalHdbti.g:1942:1: rule__ImportConfig__UnorderedGroup__5 : rule__ImportConfig__UnorderedGroup__Impl ( rule__ImportConfig__UnorderedGroup__6 )? ;
    public final void rule__ImportConfig__UnorderedGroup__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:1946:1: ( rule__ImportConfig__UnorderedGroup__Impl ( rule__ImportConfig__UnorderedGroup__6 )? )
            // InternalHdbti.g:1947:2: rule__ImportConfig__UnorderedGroup__Impl ( rule__ImportConfig__UnorderedGroup__6 )?
            {
            pushFollow(FOLLOW_17);
            rule__ImportConfig__UnorderedGroup__Impl();

            state._fsp--;

            // InternalHdbti.g:1948:2: ( rule__ImportConfig__UnorderedGroup__6 )?
            int alt12=2;
            alt12 = dfa12.predict(input);
            switch (alt12) {
                case 1 :
                    // InternalHdbti.g:1948:2: rule__ImportConfig__UnorderedGroup__6
                    {
                    pushFollow(FOLLOW_2);
                    rule__ImportConfig__UnorderedGroup__6();

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
    // $ANTLR end "rule__ImportConfig__UnorderedGroup__5"


    // $ANTLR start "rule__ImportConfig__UnorderedGroup__6"
    // InternalHdbti.g:1954:1: rule__ImportConfig__UnorderedGroup__6 : rule__ImportConfig__UnorderedGroup__Impl ( rule__ImportConfig__UnorderedGroup__7 )? ;
    public final void rule__ImportConfig__UnorderedGroup__6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:1958:1: ( rule__ImportConfig__UnorderedGroup__Impl ( rule__ImportConfig__UnorderedGroup__7 )? )
            // InternalHdbti.g:1959:2: rule__ImportConfig__UnorderedGroup__Impl ( rule__ImportConfig__UnorderedGroup__7 )?
            {
            pushFollow(FOLLOW_17);
            rule__ImportConfig__UnorderedGroup__Impl();

            state._fsp--;

            // InternalHdbti.g:1960:2: ( rule__ImportConfig__UnorderedGroup__7 )?
            int alt13=2;
            alt13 = dfa13.predict(input);
            switch (alt13) {
                case 1 :
                    // InternalHdbti.g:1960:2: rule__ImportConfig__UnorderedGroup__7
                    {
                    pushFollow(FOLLOW_2);
                    rule__ImportConfig__UnorderedGroup__7();

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
    // $ANTLR end "rule__ImportConfig__UnorderedGroup__6"


    // $ANTLR start "rule__ImportConfig__UnorderedGroup__7"
    // InternalHdbti.g:1966:1: rule__ImportConfig__UnorderedGroup__7 : rule__ImportConfig__UnorderedGroup__Impl ( rule__ImportConfig__UnorderedGroup__8 )? ;
    public final void rule__ImportConfig__UnorderedGroup__7() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:1970:1: ( rule__ImportConfig__UnorderedGroup__Impl ( rule__ImportConfig__UnorderedGroup__8 )? )
            // InternalHdbti.g:1971:2: rule__ImportConfig__UnorderedGroup__Impl ( rule__ImportConfig__UnorderedGroup__8 )?
            {
            pushFollow(FOLLOW_17);
            rule__ImportConfig__UnorderedGroup__Impl();

            state._fsp--;

            // InternalHdbti.g:1972:2: ( rule__ImportConfig__UnorderedGroup__8 )?
            int alt14=2;
            alt14 = dfa14.predict(input);
            switch (alt14) {
                case 1 :
                    // InternalHdbti.g:1972:2: rule__ImportConfig__UnorderedGroup__8
                    {
                    pushFollow(FOLLOW_2);
                    rule__ImportConfig__UnorderedGroup__8();

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
    // $ANTLR end "rule__ImportConfig__UnorderedGroup__7"


    // $ANTLR start "rule__ImportConfig__UnorderedGroup__8"
    // InternalHdbti.g:1978:1: rule__ImportConfig__UnorderedGroup__8 : rule__ImportConfig__UnorderedGroup__Impl ;
    public final void rule__ImportConfig__UnorderedGroup__8() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:1982:1: ( rule__ImportConfig__UnorderedGroup__Impl )
            // InternalHdbti.g:1983:2: rule__ImportConfig__UnorderedGroup__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ImportConfig__UnorderedGroup__Impl();

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
    // $ANTLR end "rule__ImportConfig__UnorderedGroup__8"


    // $ANTLR start "rule__HdbdtiModel__ImportElementAssignment"
    // InternalHdbti.g:1990:1: rule__HdbdtiModel__ImportElementAssignment : ( ruleImport ) ;
    public final void rule__HdbdtiModel__ImportElementAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:1994:1: ( ( ruleImport ) )
            // InternalHdbti.g:1995:2: ( ruleImport )
            {
            // InternalHdbti.g:1995:2: ( ruleImport )
            // InternalHdbti.g:1996:3: ruleImport
            {
             before(grammarAccess.getHdbdtiModelAccess().getImportElementImportParserRuleCall_0()); 
            pushFollow(FOLLOW_2);
            ruleImport();

            state._fsp--;

             after(grammarAccess.getHdbdtiModelAccess().getImportElementImportParserRuleCall_0()); 

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
    // $ANTLR end "rule__HdbdtiModel__ImportElementAssignment"


    // $ANTLR start "rule__GroupType__ValueAssignment_2"
    // InternalHdbti.g:2005:1: rule__GroupType__ValueAssignment_2 : ( RULE_STRING ) ;
    public final void rule__GroupType__ValueAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:2009:1: ( ( RULE_STRING ) )
            // InternalHdbti.g:2010:2: ( RULE_STRING )
            {
            // InternalHdbti.g:2010:2: ( RULE_STRING )
            // InternalHdbti.g:2011:3: RULE_STRING
            {
             before(grammarAccess.getGroupTypeAccess().getValueSTRINGTerminalRuleCall_2_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getGroupTypeAccess().getValueSTRINGTerminalRuleCall_2_0()); 

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
    // $ANTLR end "rule__GroupType__ValueAssignment_2"


    // $ANTLR start "rule__ImportConfig__TableAssignment_0_1"
    // InternalHdbti.g:2020:1: rule__ImportConfig__TableAssignment_0_1 : ( ( 'table' ) ) ;
    public final void rule__ImportConfig__TableAssignment_0_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:2024:1: ( ( ( 'table' ) ) )
            // InternalHdbti.g:2025:2: ( ( 'table' ) )
            {
            // InternalHdbti.g:2025:2: ( ( 'table' ) )
            // InternalHdbti.g:2026:3: ( 'table' )
            {
             before(grammarAccess.getImportConfigAccess().getTableTableKeyword_0_1_0()); 
            // InternalHdbti.g:2027:3: ( 'table' )
            // InternalHdbti.g:2028:4: 'table'
            {
             before(grammarAccess.getImportConfigAccess().getTableTableKeyword_0_1_0()); 
            match(input,22,FOLLOW_2); 
             after(grammarAccess.getImportConfigAccess().getTableTableKeyword_0_1_0()); 

            }

             after(grammarAccess.getImportConfigAccess().getTableTableKeyword_0_1_0()); 

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
    // $ANTLR end "rule__ImportConfig__TableAssignment_0_1"


    // $ANTLR start "rule__ImportConfig__TableValueAssignment_0_3"
    // InternalHdbti.g:2039:1: rule__ImportConfig__TableValueAssignment_0_3 : ( RULE_STRING ) ;
    public final void rule__ImportConfig__TableValueAssignment_0_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:2043:1: ( ( RULE_STRING ) )
            // InternalHdbti.g:2044:2: ( RULE_STRING )
            {
            // InternalHdbti.g:2044:2: ( RULE_STRING )
            // InternalHdbti.g:2045:3: RULE_STRING
            {
             before(grammarAccess.getImportConfigAccess().getTableValueSTRINGTerminalRuleCall_0_3_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getImportConfigAccess().getTableValueSTRINGTerminalRuleCall_0_3_0()); 

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
    // $ANTLR end "rule__ImportConfig__TableValueAssignment_0_3"


    // $ANTLR start "rule__ImportConfig__SchemaAssignment_1_0"
    // InternalHdbti.g:2054:1: rule__ImportConfig__SchemaAssignment_1_0 : ( ( 'schema' ) ) ;
    public final void rule__ImportConfig__SchemaAssignment_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:2058:1: ( ( ( 'schema' ) ) )
            // InternalHdbti.g:2059:2: ( ( 'schema' ) )
            {
            // InternalHdbti.g:2059:2: ( ( 'schema' ) )
            // InternalHdbti.g:2060:3: ( 'schema' )
            {
             before(grammarAccess.getImportConfigAccess().getSchemaSchemaKeyword_1_0_0()); 
            // InternalHdbti.g:2061:3: ( 'schema' )
            // InternalHdbti.g:2062:4: 'schema'
            {
             before(grammarAccess.getImportConfigAccess().getSchemaSchemaKeyword_1_0_0()); 
            match(input,23,FOLLOW_2); 
             after(grammarAccess.getImportConfigAccess().getSchemaSchemaKeyword_1_0_0()); 

            }

             after(grammarAccess.getImportConfigAccess().getSchemaSchemaKeyword_1_0_0()); 

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
    // $ANTLR end "rule__ImportConfig__SchemaAssignment_1_0"


    // $ANTLR start "rule__ImportConfig__SchemaValueAssignment_1_2"
    // InternalHdbti.g:2073:1: rule__ImportConfig__SchemaValueAssignment_1_2 : ( RULE_STRING ) ;
    public final void rule__ImportConfig__SchemaValueAssignment_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:2077:1: ( ( RULE_STRING ) )
            // InternalHdbti.g:2078:2: ( RULE_STRING )
            {
            // InternalHdbti.g:2078:2: ( RULE_STRING )
            // InternalHdbti.g:2079:3: RULE_STRING
            {
             before(grammarAccess.getImportConfigAccess().getSchemaValueSTRINGTerminalRuleCall_1_2_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getImportConfigAccess().getSchemaValueSTRINGTerminalRuleCall_1_2_0()); 

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
    // $ANTLR end "rule__ImportConfig__SchemaValueAssignment_1_2"


    // $ANTLR start "rule__ImportConfig__FileAssignment_2_0"
    // InternalHdbti.g:2088:1: rule__ImportConfig__FileAssignment_2_0 : ( ( 'file' ) ) ;
    public final void rule__ImportConfig__FileAssignment_2_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:2092:1: ( ( ( 'file' ) ) )
            // InternalHdbti.g:2093:2: ( ( 'file' ) )
            {
            // InternalHdbti.g:2093:2: ( ( 'file' ) )
            // InternalHdbti.g:2094:3: ( 'file' )
            {
             before(grammarAccess.getImportConfigAccess().getFileFileKeyword_2_0_0()); 
            // InternalHdbti.g:2095:3: ( 'file' )
            // InternalHdbti.g:2096:4: 'file'
            {
             before(grammarAccess.getImportConfigAccess().getFileFileKeyword_2_0_0()); 
            match(input,24,FOLLOW_2); 
             after(grammarAccess.getImportConfigAccess().getFileFileKeyword_2_0_0()); 

            }

             after(grammarAccess.getImportConfigAccess().getFileFileKeyword_2_0_0()); 

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
    // $ANTLR end "rule__ImportConfig__FileAssignment_2_0"


    // $ANTLR start "rule__ImportConfig__FileValueAssignment_2_2"
    // InternalHdbti.g:2107:1: rule__ImportConfig__FileValueAssignment_2_2 : ( RULE_STRING ) ;
    public final void rule__ImportConfig__FileValueAssignment_2_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:2111:1: ( ( RULE_STRING ) )
            // InternalHdbti.g:2112:2: ( RULE_STRING )
            {
            // InternalHdbti.g:2112:2: ( RULE_STRING )
            // InternalHdbti.g:2113:3: RULE_STRING
            {
             before(grammarAccess.getImportConfigAccess().getFileValueSTRINGTerminalRuleCall_2_2_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getImportConfigAccess().getFileValueSTRINGTerminalRuleCall_2_2_0()); 

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
    // $ANTLR end "rule__ImportConfig__FileValueAssignment_2_2"


    // $ANTLR start "rule__ImportConfig__DelimFieldAssignment_3_0"
    // InternalHdbti.g:2122:1: rule__ImportConfig__DelimFieldAssignment_3_0 : ( ( 'delimField' ) ) ;
    public final void rule__ImportConfig__DelimFieldAssignment_3_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:2126:1: ( ( ( 'delimField' ) ) )
            // InternalHdbti.g:2127:2: ( ( 'delimField' ) )
            {
            // InternalHdbti.g:2127:2: ( ( 'delimField' ) )
            // InternalHdbti.g:2128:3: ( 'delimField' )
            {
             before(grammarAccess.getImportConfigAccess().getDelimFieldDelimFieldKeyword_3_0_0()); 
            // InternalHdbti.g:2129:3: ( 'delimField' )
            // InternalHdbti.g:2130:4: 'delimField'
            {
             before(grammarAccess.getImportConfigAccess().getDelimFieldDelimFieldKeyword_3_0_0()); 
            match(input,25,FOLLOW_2); 
             after(grammarAccess.getImportConfigAccess().getDelimFieldDelimFieldKeyword_3_0_0()); 

            }

             after(grammarAccess.getImportConfigAccess().getDelimFieldDelimFieldKeyword_3_0_0()); 

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
    // $ANTLR end "rule__ImportConfig__DelimFieldAssignment_3_0"


    // $ANTLR start "rule__ImportConfig__DelimFieldValueAssignment_3_2"
    // InternalHdbti.g:2141:1: rule__ImportConfig__DelimFieldValueAssignment_3_2 : ( RULE_STRING ) ;
    public final void rule__ImportConfig__DelimFieldValueAssignment_3_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:2145:1: ( ( RULE_STRING ) )
            // InternalHdbti.g:2146:2: ( RULE_STRING )
            {
            // InternalHdbti.g:2146:2: ( RULE_STRING )
            // InternalHdbti.g:2147:3: RULE_STRING
            {
             before(grammarAccess.getImportConfigAccess().getDelimFieldValueSTRINGTerminalRuleCall_3_2_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getImportConfigAccess().getDelimFieldValueSTRINGTerminalRuleCall_3_2_0()); 

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
    // $ANTLR end "rule__ImportConfig__DelimFieldValueAssignment_3_2"


    // $ANTLR start "rule__ImportConfig__HeaderAssignment_4_0"
    // InternalHdbti.g:2156:1: rule__ImportConfig__HeaderAssignment_4_0 : ( ( 'header' ) ) ;
    public final void rule__ImportConfig__HeaderAssignment_4_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:2160:1: ( ( ( 'header' ) ) )
            // InternalHdbti.g:2161:2: ( ( 'header' ) )
            {
            // InternalHdbti.g:2161:2: ( ( 'header' ) )
            // InternalHdbti.g:2162:3: ( 'header' )
            {
             before(grammarAccess.getImportConfigAccess().getHeaderHeaderKeyword_4_0_0()); 
            // InternalHdbti.g:2163:3: ( 'header' )
            // InternalHdbti.g:2164:4: 'header'
            {
             before(grammarAccess.getImportConfigAccess().getHeaderHeaderKeyword_4_0_0()); 
            match(input,26,FOLLOW_2); 
             after(grammarAccess.getImportConfigAccess().getHeaderHeaderKeyword_4_0_0()); 

            }

             after(grammarAccess.getImportConfigAccess().getHeaderHeaderKeyword_4_0_0()); 

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
    // $ANTLR end "rule__ImportConfig__HeaderAssignment_4_0"


    // $ANTLR start "rule__ImportConfig__HeaderValueAssignment_4_2"
    // InternalHdbti.g:2175:1: rule__ImportConfig__HeaderValueAssignment_4_2 : ( RULE_BOOL ) ;
    public final void rule__ImportConfig__HeaderValueAssignment_4_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:2179:1: ( ( RULE_BOOL ) )
            // InternalHdbti.g:2180:2: ( RULE_BOOL )
            {
            // InternalHdbti.g:2180:2: ( RULE_BOOL )
            // InternalHdbti.g:2181:3: RULE_BOOL
            {
             before(grammarAccess.getImportConfigAccess().getHeaderValueBOOLTerminalRuleCall_4_2_0()); 
            match(input,RULE_BOOL,FOLLOW_2); 
             after(grammarAccess.getImportConfigAccess().getHeaderValueBOOLTerminalRuleCall_4_2_0()); 

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
    // $ANTLR end "rule__ImportConfig__HeaderValueAssignment_4_2"


    // $ANTLR start "rule__ImportConfig__KeysAssignment_5_0"
    // InternalHdbti.g:2190:1: rule__ImportConfig__KeysAssignment_5_0 : ( ( 'keys' ) ) ;
    public final void rule__ImportConfig__KeysAssignment_5_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:2194:1: ( ( ( 'keys' ) ) )
            // InternalHdbti.g:2195:2: ( ( 'keys' ) )
            {
            // InternalHdbti.g:2195:2: ( ( 'keys' ) )
            // InternalHdbti.g:2196:3: ( 'keys' )
            {
             before(grammarAccess.getImportConfigAccess().getKeysKeysKeyword_5_0_0()); 
            // InternalHdbti.g:2197:3: ( 'keys' )
            // InternalHdbti.g:2198:4: 'keys'
            {
             before(grammarAccess.getImportConfigAccess().getKeysKeysKeyword_5_0_0()); 
            match(input,27,FOLLOW_2); 
             after(grammarAccess.getImportConfigAccess().getKeysKeysKeyword_5_0_0()); 

            }

             after(grammarAccess.getImportConfigAccess().getKeysKeysKeyword_5_0_0()); 

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
    // $ANTLR end "rule__ImportConfig__KeysAssignment_5_0"


    // $ANTLR start "rule__ImportConfig__HeaderValuesAssignment_5_3_0"
    // InternalHdbti.g:2209:1: rule__ImportConfig__HeaderValuesAssignment_5_3_0 : ( ruleGroupType ) ;
    public final void rule__ImportConfig__HeaderValuesAssignment_5_3_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:2213:1: ( ( ruleGroupType ) )
            // InternalHdbti.g:2214:2: ( ruleGroupType )
            {
            // InternalHdbti.g:2214:2: ( ruleGroupType )
            // InternalHdbti.g:2215:3: ruleGroupType
            {
             before(grammarAccess.getImportConfigAccess().getHeaderValuesGroupTypeParserRuleCall_5_3_0_0()); 
            pushFollow(FOLLOW_2);
            ruleGroupType();

            state._fsp--;

             after(grammarAccess.getImportConfigAccess().getHeaderValuesGroupTypeParserRuleCall_5_3_0_0()); 

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
    // $ANTLR end "rule__ImportConfig__HeaderValuesAssignment_5_3_0"


    // $ANTLR start "rule__ImportConfig__HeaderValuesAssignment_5_3_1_1"
    // InternalHdbti.g:2224:1: rule__ImportConfig__HeaderValuesAssignment_5_3_1_1 : ( ruleGroupType ) ;
    public final void rule__ImportConfig__HeaderValuesAssignment_5_3_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:2228:1: ( ( ruleGroupType ) )
            // InternalHdbti.g:2229:2: ( ruleGroupType )
            {
            // InternalHdbti.g:2229:2: ( ruleGroupType )
            // InternalHdbti.g:2230:3: ruleGroupType
            {
             before(grammarAccess.getImportConfigAccess().getHeaderValuesGroupTypeParserRuleCall_5_3_1_1_0()); 
            pushFollow(FOLLOW_2);
            ruleGroupType();

            state._fsp--;

             after(grammarAccess.getImportConfigAccess().getHeaderValuesGroupTypeParserRuleCall_5_3_1_1_0()); 

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
    // $ANTLR end "rule__ImportConfig__HeaderValuesAssignment_5_3_1_1"


    // $ANTLR start "rule__ImportConfig__DelimEnclosingAssignment_6_0"
    // InternalHdbti.g:2239:1: rule__ImportConfig__DelimEnclosingAssignment_6_0 : ( ( 'delimEnclosing' ) ) ;
    public final void rule__ImportConfig__DelimEnclosingAssignment_6_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:2243:1: ( ( ( 'delimEnclosing' ) ) )
            // InternalHdbti.g:2244:2: ( ( 'delimEnclosing' ) )
            {
            // InternalHdbti.g:2244:2: ( ( 'delimEnclosing' ) )
            // InternalHdbti.g:2245:3: ( 'delimEnclosing' )
            {
             before(grammarAccess.getImportConfigAccess().getDelimEnclosingDelimEnclosingKeyword_6_0_0()); 
            // InternalHdbti.g:2246:3: ( 'delimEnclosing' )
            // InternalHdbti.g:2247:4: 'delimEnclosing'
            {
             before(grammarAccess.getImportConfigAccess().getDelimEnclosingDelimEnclosingKeyword_6_0_0()); 
            match(input,28,FOLLOW_2); 
             after(grammarAccess.getImportConfigAccess().getDelimEnclosingDelimEnclosingKeyword_6_0_0()); 

            }

             after(grammarAccess.getImportConfigAccess().getDelimEnclosingDelimEnclosingKeyword_6_0_0()); 

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
    // $ANTLR end "rule__ImportConfig__DelimEnclosingAssignment_6_0"


    // $ANTLR start "rule__ImportConfig__DelimEnclosingValueAssignment_6_2"
    // InternalHdbti.g:2258:1: rule__ImportConfig__DelimEnclosingValueAssignment_6_2 : ( RULE_STRING ) ;
    public final void rule__ImportConfig__DelimEnclosingValueAssignment_6_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:2262:1: ( ( RULE_STRING ) )
            // InternalHdbti.g:2263:2: ( RULE_STRING )
            {
            // InternalHdbti.g:2263:2: ( RULE_STRING )
            // InternalHdbti.g:2264:3: RULE_STRING
            {
             before(grammarAccess.getImportConfigAccess().getDelimEnclosingValueSTRINGTerminalRuleCall_6_2_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getImportConfigAccess().getDelimEnclosingValueSTRINGTerminalRuleCall_6_2_0()); 

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
    // $ANTLR end "rule__ImportConfig__DelimEnclosingValueAssignment_6_2"


    // $ANTLR start "rule__ImportConfig__DistinguishEmptyFromNullAssignment_7_0"
    // InternalHdbti.g:2273:1: rule__ImportConfig__DistinguishEmptyFromNullAssignment_7_0 : ( ( 'delimEnclosing' ) ) ;
    public final void rule__ImportConfig__DistinguishEmptyFromNullAssignment_7_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:2277:1: ( ( ( 'delimEnclosing' ) ) )
            // InternalHdbti.g:2278:2: ( ( 'delimEnclosing' ) )
            {
            // InternalHdbti.g:2278:2: ( ( 'delimEnclosing' ) )
            // InternalHdbti.g:2279:3: ( 'delimEnclosing' )
            {
             before(grammarAccess.getImportConfigAccess().getDistinguishEmptyFromNullDelimEnclosingKeyword_7_0_0()); 
            // InternalHdbti.g:2280:3: ( 'delimEnclosing' )
            // InternalHdbti.g:2281:4: 'delimEnclosing'
            {
             before(grammarAccess.getImportConfigAccess().getDistinguishEmptyFromNullDelimEnclosingKeyword_7_0_0()); 
            match(input,28,FOLLOW_2); 
             after(grammarAccess.getImportConfigAccess().getDistinguishEmptyFromNullDelimEnclosingKeyword_7_0_0()); 

            }

             after(grammarAccess.getImportConfigAccess().getDistinguishEmptyFromNullDelimEnclosingKeyword_7_0_0()); 

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
    // $ANTLR end "rule__ImportConfig__DistinguishEmptyFromNullAssignment_7_0"


    // $ANTLR start "rule__ImportConfig__DistinguishEmptyFromNullValueAssignment_7_2"
    // InternalHdbti.g:2292:1: rule__ImportConfig__DistinguishEmptyFromNullValueAssignment_7_2 : ( RULE_BOOL ) ;
    public final void rule__ImportConfig__DistinguishEmptyFromNullValueAssignment_7_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:2296:1: ( ( RULE_BOOL ) )
            // InternalHdbti.g:2297:2: ( RULE_BOOL )
            {
            // InternalHdbti.g:2297:2: ( RULE_BOOL )
            // InternalHdbti.g:2298:3: RULE_BOOL
            {
             before(grammarAccess.getImportConfigAccess().getDistinguishEmptyFromNullValueBOOLTerminalRuleCall_7_2_0()); 
            match(input,RULE_BOOL,FOLLOW_2); 
             after(grammarAccess.getImportConfigAccess().getDistinguishEmptyFromNullValueBOOLTerminalRuleCall_7_2_0()); 

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
    // $ANTLR end "rule__ImportConfig__DistinguishEmptyFromNullValueAssignment_7_2"


    // $ANTLR start "rule__ImportConfig__UseHeaderNamesAssignment_8_0_0"
    // InternalHdbti.g:2307:1: rule__ImportConfig__UseHeaderNamesAssignment_8_0_0 : ( ( 'delimEnclosing' ) ) ;
    public final void rule__ImportConfig__UseHeaderNamesAssignment_8_0_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:2311:1: ( ( ( 'delimEnclosing' ) ) )
            // InternalHdbti.g:2312:2: ( ( 'delimEnclosing' ) )
            {
            // InternalHdbti.g:2312:2: ( ( 'delimEnclosing' ) )
            // InternalHdbti.g:2313:3: ( 'delimEnclosing' )
            {
             before(grammarAccess.getImportConfigAccess().getUseHeaderNamesDelimEnclosingKeyword_8_0_0_0()); 
            // InternalHdbti.g:2314:3: ( 'delimEnclosing' )
            // InternalHdbti.g:2315:4: 'delimEnclosing'
            {
             before(grammarAccess.getImportConfigAccess().getUseHeaderNamesDelimEnclosingKeyword_8_0_0_0()); 
            match(input,28,FOLLOW_2); 
             after(grammarAccess.getImportConfigAccess().getUseHeaderNamesDelimEnclosingKeyword_8_0_0_0()); 

            }

             after(grammarAccess.getImportConfigAccess().getUseHeaderNamesDelimEnclosingKeyword_8_0_0_0()); 

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
    // $ANTLR end "rule__ImportConfig__UseHeaderNamesAssignment_8_0_0"


    // $ANTLR start "rule__ImportConfig__UseHeaderNamesValueAssignment_8_0_2"
    // InternalHdbti.g:2326:1: rule__ImportConfig__UseHeaderNamesValueAssignment_8_0_2 : ( RULE_BOOL ) ;
    public final void rule__ImportConfig__UseHeaderNamesValueAssignment_8_0_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:2330:1: ( ( RULE_BOOL ) )
            // InternalHdbti.g:2331:2: ( RULE_BOOL )
            {
            // InternalHdbti.g:2331:2: ( RULE_BOOL )
            // InternalHdbti.g:2332:3: RULE_BOOL
            {
             before(grammarAccess.getImportConfigAccess().getUseHeaderNamesValueBOOLTerminalRuleCall_8_0_2_0()); 
            match(input,RULE_BOOL,FOLLOW_2); 
             after(grammarAccess.getImportConfigAccess().getUseHeaderNamesValueBOOLTerminalRuleCall_8_0_2_0()); 

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
    // $ANTLR end "rule__ImportConfig__UseHeaderNamesValueAssignment_8_0_2"


    // $ANTLR start "rule__Import__ValuesAssignment_3_0"
    // InternalHdbti.g:2341:1: rule__Import__ValuesAssignment_3_0 : ( ruleImportConfig ) ;
    public final void rule__Import__ValuesAssignment_3_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:2345:1: ( ( ruleImportConfig ) )
            // InternalHdbti.g:2346:2: ( ruleImportConfig )
            {
            // InternalHdbti.g:2346:2: ( ruleImportConfig )
            // InternalHdbti.g:2347:3: ruleImportConfig
            {
             before(grammarAccess.getImportAccess().getValuesImportConfigParserRuleCall_3_0_0()); 
            pushFollow(FOLLOW_2);
            ruleImportConfig();

            state._fsp--;

             after(grammarAccess.getImportAccess().getValuesImportConfigParserRuleCall_3_0_0()); 

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
    // $ANTLR end "rule__Import__ValuesAssignment_3_0"


    // $ANTLR start "rule__Import__ValuesAssignment_3_1_1"
    // InternalHdbti.g:2356:1: rule__Import__ValuesAssignment_3_1_1 : ( ruleImportConfig ) ;
    public final void rule__Import__ValuesAssignment_3_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbti.g:2360:1: ( ( ruleImportConfig ) )
            // InternalHdbti.g:2361:2: ( ruleImportConfig )
            {
            // InternalHdbti.g:2361:2: ( ruleImportConfig )
            // InternalHdbti.g:2362:3: ruleImportConfig
            {
             before(grammarAccess.getImportAccess().getValuesImportConfigParserRuleCall_3_1_1_0()); 
            pushFollow(FOLLOW_2);
            ruleImportConfig();

            state._fsp--;

             after(grammarAccess.getImportAccess().getValuesImportConfigParserRuleCall_3_1_1_0()); 

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
    // $ANTLR end "rule__Import__ValuesAssignment_3_1_1"

    // Delegated rules


    protected DFA6 dfa6 = new DFA6(this);
    protected DFA7 dfa7 = new DFA7(this);
    protected DFA8 dfa8 = new DFA8(this);
    protected DFA9 dfa9 = new DFA9(this);
    protected DFA10 dfa10 = new DFA10(this);
    protected DFA11 dfa11 = new DFA11(this);
    protected DFA12 dfa12 = new DFA12(this);
    protected DFA13 dfa13 = new DFA13(this);
    protected DFA14 dfa14 = new DFA14(this);
    static final String dfa_1s = "\16\uffff";
    static final String dfa_2s = "\1\16\6\uffff\1\17\1\uffff\1\4\1\20\1\uffff\1\0\1\uffff";
    static final String dfa_3s = "\1\34\6\uffff\1\17\1\uffff\1\5\1\20\1\uffff\1\0\1\uffff";
    static final String dfa_4s = "\1\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\uffff\1\11\2\uffff\1\7\1\uffff\1\10";
    static final String dfa_5s = "\1\3\6\uffff\1\0\1\uffff\1\2\1\4\1\uffff\1\1\1\uffff}>";
    static final String[] dfa_6s = {
            "\1\1\5\uffff\1\10\2\uffff\1\2\1\3\1\4\1\5\1\6\1\7",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\11",
            "",
            "\1\13\1\12",
            "\1\14",
            "",
            "\1\uffff",
            ""
    };

    static final short[] dfa_1 = DFA.unpackEncodedString(dfa_1s);
    static final char[] dfa_2 = DFA.unpackEncodedStringToUnsignedChars(dfa_2s);
    static final char[] dfa_3 = DFA.unpackEncodedStringToUnsignedChars(dfa_3s);
    static final short[] dfa_4 = DFA.unpackEncodedString(dfa_4s);
    static final short[] dfa_5 = DFA.unpackEncodedString(dfa_5s);
    static final short[][] dfa_6 = unpackEncodedStringArray(dfa_6s);

    class DFA6 extends DFA {

        public DFA6(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 6;
            this.eot = dfa_1;
            this.eof = dfa_1;
            this.min = dfa_2;
            this.max = dfa_3;
            this.accept = dfa_4;
            this.special = dfa_5;
            this.transition = dfa_6;
        }
        public String getDescription() {
            return "1738:3: ( ({...}? => ( ( ( rule__ImportConfig__Group_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ImportConfig__Group_1__0 ) ) ) ) | ({...}? => ( ( ( rule__ImportConfig__Group_2__0 ) ) ) ) | ({...}? => ( ( ( rule__ImportConfig__Group_3__0 ) ) ) ) | ({...}? => ( ( ( rule__ImportConfig__Group_4__0 ) ) ) ) | ({...}? => ( ( ( rule__ImportConfig__Group_5__0 ) ) ) ) | ({...}? => ( ( ( rule__ImportConfig__Group_6__0 ) ) ) ) | ({...}? => ( ( ( rule__ImportConfig__Group_7__0 ) ) ) ) | ({...}? => ( ( ( rule__ImportConfig__Group_8__0 ) ) ) ) )";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA6_7 = input.LA(1);

                         
                        int index6_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( LA6_7 == 15 && ( getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 6) || getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 8) || getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 7) ) ) {s = 9;}

                         
                        input.seek(index6_7);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA6_12 = input.LA(1);

                         
                        int index6_12 = input.index();
                        input.rewind();
                        s = -1;
                        if ( getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 7) ) {s = 13;}

                        else if ( getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 8) ) {s = 8;}

                         
                        input.seek(index6_12);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA6_9 = input.LA(1);

                         
                        int index6_9 = input.index();
                        input.rewind();
                        s = -1;
                        if ( LA6_9 == RULE_BOOL && ( getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 8) || getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 7) ) ) {s = 10;}

                        else if ( LA6_9 == RULE_STRING && getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 6) ) {s = 11;}

                         
                        input.seek(index6_9);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA6_0 = input.LA(1);

                         
                        int index6_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( LA6_0 == 14 && getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 0) ) {s = 1;}

                        else if ( LA6_0 == 23 && getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 1) ) {s = 2;}

                        else if ( LA6_0 == 24 && getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 2) ) {s = 3;}

                        else if ( LA6_0 == 25 && getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 3) ) {s = 4;}

                        else if ( LA6_0 == 26 && getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 4) ) {s = 5;}

                        else if ( LA6_0 == 27 && getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 5) ) {s = 6;}

                        else if ( LA6_0 == 28 && ( getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 6) || getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 8) || getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 7) ) ) {s = 7;}

                        else if ( LA6_0 == 20 && getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 8) ) {s = 8;}

                         
                        input.seek(index6_0);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA6_10 = input.LA(1);

                         
                        int index6_10 = input.index();
                        input.rewind();
                        s = -1;
                        if ( LA6_10 == 16 && ( getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 8) || getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 7) ) ) {s = 12;}

                         
                        input.seek(index6_10);
                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 6, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String dfa_7s = "\12\uffff";
    static final String dfa_8s = "\1\11\11\uffff";
    static final String dfa_9s = "\1\16\11\uffff";
    static final String dfa_10s = "\1\34\11\uffff";
    static final String dfa_11s = "\1\uffff\10\1\1\2";
    static final String dfa_12s = "\1\0\11\uffff}>";
    static final String[] dfa_13s = {
            "\1\1\3\uffff\2\11\1\10\2\uffff\1\2\1\3\1\4\1\5\1\6\1\7",
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

    static final short[] dfa_7 = DFA.unpackEncodedString(dfa_7s);
    static final short[] dfa_8 = DFA.unpackEncodedString(dfa_8s);
    static final char[] dfa_9 = DFA.unpackEncodedStringToUnsignedChars(dfa_9s);
    static final char[] dfa_10 = DFA.unpackEncodedStringToUnsignedChars(dfa_10s);
    static final short[] dfa_11 = DFA.unpackEncodedString(dfa_11s);
    static final short[] dfa_12 = DFA.unpackEncodedString(dfa_12s);
    static final short[][] dfa_13 = unpackEncodedStringArray(dfa_13s);

    class DFA7 extends DFA {

        public DFA7(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 7;
            this.eot = dfa_7;
            this.eof = dfa_8;
            this.min = dfa_9;
            this.max = dfa_10;
            this.accept = dfa_11;
            this.special = dfa_12;
            this.transition = dfa_13;
        }
        public String getDescription() {
            return "1888:2: ( rule__ImportConfig__UnorderedGroup__1 )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA7_0 = input.LA(1);

                         
                        int index7_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( LA7_0 == 14 && getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 0) ) {s = 1;}

                        else if ( LA7_0 == 23 && getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 1) ) {s = 2;}

                        else if ( LA7_0 == 24 && getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 2) ) {s = 3;}

                        else if ( LA7_0 == 25 && getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 3) ) {s = 4;}

                        else if ( LA7_0 == 26 && getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 4) ) {s = 5;}

                        else if ( LA7_0 == 27 && getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 5) ) {s = 6;}

                        else if ( LA7_0 == 28 && ( getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 6) || getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 8) || getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 7) ) ) {s = 7;}

                        else if ( LA7_0 == 20 && getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 8) ) {s = 8;}

                        else if ( (LA7_0==EOF||(LA7_0>=18 && LA7_0<=19)) ) {s = 9;}

                         
                        input.seek(index7_0);
                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 7, _s, input);
            error(nvae);
            throw nvae;
        }
    }

    class DFA8 extends DFA {

        public DFA8(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 8;
            this.eot = dfa_7;
            this.eof = dfa_8;
            this.min = dfa_9;
            this.max = dfa_10;
            this.accept = dfa_11;
            this.special = dfa_12;
            this.transition = dfa_13;
        }
        public String getDescription() {
            return "1900:2: ( rule__ImportConfig__UnorderedGroup__2 )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA8_0 = input.LA(1);

                         
                        int index8_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( LA8_0 == 14 && getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 0) ) {s = 1;}

                        else if ( LA8_0 == 23 && getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 1) ) {s = 2;}

                        else if ( LA8_0 == 24 && getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 2) ) {s = 3;}

                        else if ( LA8_0 == 25 && getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 3) ) {s = 4;}

                        else if ( LA8_0 == 26 && getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 4) ) {s = 5;}

                        else if ( LA8_0 == 27 && getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 5) ) {s = 6;}

                        else if ( LA8_0 == 28 && ( getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 6) || getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 8) || getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 7) ) ) {s = 7;}

                        else if ( LA8_0 == 20 && getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 8) ) {s = 8;}

                        else if ( (LA8_0==EOF||(LA8_0>=18 && LA8_0<=19)) ) {s = 9;}

                         
                        input.seek(index8_0);
                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 8, _s, input);
            error(nvae);
            throw nvae;
        }
    }

    class DFA9 extends DFA {

        public DFA9(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 9;
            this.eot = dfa_7;
            this.eof = dfa_8;
            this.min = dfa_9;
            this.max = dfa_10;
            this.accept = dfa_11;
            this.special = dfa_12;
            this.transition = dfa_13;
        }
        public String getDescription() {
            return "1912:2: ( rule__ImportConfig__UnorderedGroup__3 )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA9_0 = input.LA(1);

                         
                        int index9_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( LA9_0 == 14 && getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 0) ) {s = 1;}

                        else if ( LA9_0 == 23 && getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 1) ) {s = 2;}

                        else if ( LA9_0 == 24 && getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 2) ) {s = 3;}

                        else if ( LA9_0 == 25 && getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 3) ) {s = 4;}

                        else if ( LA9_0 == 26 && getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 4) ) {s = 5;}

                        else if ( LA9_0 == 27 && getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 5) ) {s = 6;}

                        else if ( LA9_0 == 28 && ( getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 6) || getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 8) || getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 7) ) ) {s = 7;}

                        else if ( LA9_0 == 20 && getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 8) ) {s = 8;}

                        else if ( (LA9_0==EOF||(LA9_0>=18 && LA9_0<=19)) ) {s = 9;}

                         
                        input.seek(index9_0);
                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 9, _s, input);
            error(nvae);
            throw nvae;
        }
    }

    class DFA10 extends DFA {

        public DFA10(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 10;
            this.eot = dfa_7;
            this.eof = dfa_8;
            this.min = dfa_9;
            this.max = dfa_10;
            this.accept = dfa_11;
            this.special = dfa_12;
            this.transition = dfa_13;
        }
        public String getDescription() {
            return "1924:2: ( rule__ImportConfig__UnorderedGroup__4 )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA10_0 = input.LA(1);

                         
                        int index10_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( LA10_0 == 14 && getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 0) ) {s = 1;}

                        else if ( LA10_0 == 23 && getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 1) ) {s = 2;}

                        else if ( LA10_0 == 24 && getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 2) ) {s = 3;}

                        else if ( LA10_0 == 25 && getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 3) ) {s = 4;}

                        else if ( LA10_0 == 26 && getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 4) ) {s = 5;}

                        else if ( LA10_0 == 27 && getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 5) ) {s = 6;}

                        else if ( LA10_0 == 28 && ( getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 6) || getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 8) || getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 7) ) ) {s = 7;}

                        else if ( LA10_0 == 20 && getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 8) ) {s = 8;}

                        else if ( (LA10_0==EOF||(LA10_0>=18 && LA10_0<=19)) ) {s = 9;}

                         
                        input.seek(index10_0);
                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 10, _s, input);
            error(nvae);
            throw nvae;
        }
    }

    class DFA11 extends DFA {

        public DFA11(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 11;
            this.eot = dfa_7;
            this.eof = dfa_8;
            this.min = dfa_9;
            this.max = dfa_10;
            this.accept = dfa_11;
            this.special = dfa_12;
            this.transition = dfa_13;
        }
        public String getDescription() {
            return "1936:2: ( rule__ImportConfig__UnorderedGroup__5 )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA11_0 = input.LA(1);

                         
                        int index11_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( LA11_0 == 14 && getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 0) ) {s = 1;}

                        else if ( LA11_0 == 23 && getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 1) ) {s = 2;}

                        else if ( LA11_0 == 24 && getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 2) ) {s = 3;}

                        else if ( LA11_0 == 25 && getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 3) ) {s = 4;}

                        else if ( LA11_0 == 26 && getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 4) ) {s = 5;}

                        else if ( LA11_0 == 27 && getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 5) ) {s = 6;}

                        else if ( LA11_0 == 28 && ( getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 6) || getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 8) || getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 7) ) ) {s = 7;}

                        else if ( LA11_0 == 20 && getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 8) ) {s = 8;}

                        else if ( (LA11_0==EOF||(LA11_0>=18 && LA11_0<=19)) ) {s = 9;}

                         
                        input.seek(index11_0);
                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 11, _s, input);
            error(nvae);
            throw nvae;
        }
    }

    class DFA12 extends DFA {

        public DFA12(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 12;
            this.eot = dfa_7;
            this.eof = dfa_8;
            this.min = dfa_9;
            this.max = dfa_10;
            this.accept = dfa_11;
            this.special = dfa_12;
            this.transition = dfa_13;
        }
        public String getDescription() {
            return "1948:2: ( rule__ImportConfig__UnorderedGroup__6 )?";
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
                        if ( LA12_0 == 14 && getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 0) ) {s = 1;}

                        else if ( LA12_0 == 23 && getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 1) ) {s = 2;}

                        else if ( LA12_0 == 24 && getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 2) ) {s = 3;}

                        else if ( LA12_0 == 25 && getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 3) ) {s = 4;}

                        else if ( LA12_0 == 26 && getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 4) ) {s = 5;}

                        else if ( LA12_0 == 27 && getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 5) ) {s = 6;}

                        else if ( LA12_0 == 28 && ( getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 6) || getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 8) || getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 7) ) ) {s = 7;}

                        else if ( LA12_0 == 20 && getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 8) ) {s = 8;}

                        else if ( (LA12_0==EOF||(LA12_0>=18 && LA12_0<=19)) ) {s = 9;}

                         
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
            this.eot = dfa_7;
            this.eof = dfa_8;
            this.min = dfa_9;
            this.max = dfa_10;
            this.accept = dfa_11;
            this.special = dfa_12;
            this.transition = dfa_13;
        }
        public String getDescription() {
            return "1960:2: ( rule__ImportConfig__UnorderedGroup__7 )?";
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
                        if ( LA13_0 == 14 && getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 0) ) {s = 1;}

                        else if ( LA13_0 == 23 && getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 1) ) {s = 2;}

                        else if ( LA13_0 == 24 && getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 2) ) {s = 3;}

                        else if ( LA13_0 == 25 && getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 3) ) {s = 4;}

                        else if ( LA13_0 == 26 && getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 4) ) {s = 5;}

                        else if ( LA13_0 == 27 && getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 5) ) {s = 6;}

                        else if ( LA13_0 == 28 && ( getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 6) || getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 8) || getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 7) ) ) {s = 7;}

                        else if ( LA13_0 == 20 && getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 8) ) {s = 8;}

                        else if ( (LA13_0==EOF||(LA13_0>=18 && LA13_0<=19)) ) {s = 9;}

                         
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
            this.eot = dfa_7;
            this.eof = dfa_8;
            this.min = dfa_9;
            this.max = dfa_10;
            this.accept = dfa_11;
            this.special = dfa_12;
            this.transition = dfa_13;
        }
        public String getDescription() {
            return "1972:2: ( rule__ImportConfig__UnorderedGroup__8 )?";
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
                        if ( LA14_0 == 14 && getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 0) ) {s = 1;}

                        else if ( LA14_0 == 23 && getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 1) ) {s = 2;}

                        else if ( LA14_0 == 24 && getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 2) ) {s = 3;}

                        else if ( LA14_0 == 25 && getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 3) ) {s = 4;}

                        else if ( LA14_0 == 26 && getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 4) ) {s = 5;}

                        else if ( LA14_0 == 27 && getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 5) ) {s = 6;}

                        else if ( LA14_0 == 28 && ( getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 6) || getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 8) || getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 7) ) ) {s = 7;}

                        else if ( LA14_0 == 20 && getUnorderedGroupHelper().canSelect(grammarAccess.getImportConfigAccess().getUnorderedGroup(), 8) ) {s = 8;}

                        else if ( (LA14_0==EOF||(LA14_0>=18 && LA14_0<=19)) ) {s = 9;}

                         
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
 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000041000L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x000000001F944000L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x000000001F904000L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x000000001F904002L});

}
