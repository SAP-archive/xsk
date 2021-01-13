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

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.Lexer;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalHdbtiLexer extends Lexer {
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

    public InternalHdbtiLexer() {;} 
    public InternalHdbtiLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public InternalHdbtiLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "InternalHdbti.g"; }

    // $ANTLR start "T__12"
    public final void mT__12() throws RecognitionException {
        try {
            int _type = T__12;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalHdbti.g:11:7: ( '\"GROUP_TYPE\"' )
            // InternalHdbti.g:11:9: '\"GROUP_TYPE\"'
            {
            match("\"GROUP_TYPE\""); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__12"

    // $ANTLR start "T__13"
    public final void mT__13() throws RecognitionException {
        try {
            int _type = T__13;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalHdbti.g:12:7: ( ':' )
            // InternalHdbti.g:12:9: ':'
            {
            match(':'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__13"

    // $ANTLR start "T__14"
    public final void mT__14() throws RecognitionException {
        try {
            int _type = T__14;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalHdbti.g:13:7: ( '{' )
            // InternalHdbti.g:13:9: '{'
            {
            match('{'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__14"

    // $ANTLR start "T__15"
    public final void mT__15() throws RecognitionException {
        try {
            int _type = T__15;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalHdbti.g:14:7: ( '=' )
            // InternalHdbti.g:14:9: '='
            {
            match('='); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__15"

    // $ANTLR start "T__16"
    public final void mT__16() throws RecognitionException {
        try {
            int _type = T__16;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalHdbti.g:15:7: ( ';' )
            // InternalHdbti.g:15:9: ';'
            {
            match(';'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__16"

    // $ANTLR start "T__17"
    public final void mT__17() throws RecognitionException {
        try {
            int _type = T__17;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalHdbti.g:16:7: ( '[' )
            // InternalHdbti.g:16:9: '['
            {
            match('['); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__17"

    // $ANTLR start "T__18"
    public final void mT__18() throws RecognitionException {
        try {
            int _type = T__18;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalHdbti.g:17:7: ( ']' )
            // InternalHdbti.g:17:9: ']'
            {
            match(']'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__18"

    // $ANTLR start "T__19"
    public final void mT__19() throws RecognitionException {
        try {
            int _type = T__19;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalHdbti.g:18:7: ( ',' )
            // InternalHdbti.g:18:9: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__19"

    // $ANTLR start "T__20"
    public final void mT__20() throws RecognitionException {
        try {
            int _type = T__20;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalHdbti.g:19:7: ( '}' )
            // InternalHdbti.g:19:9: '}'
            {
            match('}'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__20"

    // $ANTLR start "T__21"
    public final void mT__21() throws RecognitionException {
        try {
            int _type = T__21;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalHdbti.g:20:7: ( 'import' )
            // InternalHdbti.g:20:9: 'import'
            {
            match("import"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__21"

    // $ANTLR start "T__22"
    public final void mT__22() throws RecognitionException {
        try {
            int _type = T__22;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalHdbti.g:21:7: ( 'table' )
            // InternalHdbti.g:21:9: 'table'
            {
            match("table"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__22"

    // $ANTLR start "T__23"
    public final void mT__23() throws RecognitionException {
        try {
            int _type = T__23;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalHdbti.g:22:7: ( 'schema' )
            // InternalHdbti.g:22:9: 'schema'
            {
            match("schema"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__23"

    // $ANTLR start "T__24"
    public final void mT__24() throws RecognitionException {
        try {
            int _type = T__24;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalHdbti.g:23:7: ( 'file' )
            // InternalHdbti.g:23:9: 'file'
            {
            match("file"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__24"

    // $ANTLR start "T__25"
    public final void mT__25() throws RecognitionException {
        try {
            int _type = T__25;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalHdbti.g:24:7: ( 'delimField' )
            // InternalHdbti.g:24:9: 'delimField'
            {
            match("delimField"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__25"

    // $ANTLR start "T__26"
    public final void mT__26() throws RecognitionException {
        try {
            int _type = T__26;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalHdbti.g:25:7: ( 'header' )
            // InternalHdbti.g:25:9: 'header'
            {
            match("header"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__26"

    // $ANTLR start "T__27"
    public final void mT__27() throws RecognitionException {
        try {
            int _type = T__27;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalHdbti.g:26:7: ( 'keys' )
            // InternalHdbti.g:26:9: 'keys'
            {
            match("keys"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__27"

    // $ANTLR start "T__28"
    public final void mT__28() throws RecognitionException {
        try {
            int _type = T__28;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalHdbti.g:27:7: ( 'delimEnclosing' )
            // InternalHdbti.g:27:9: 'delimEnclosing'
            {
            match("delimEnclosing"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__28"

    // $ANTLR start "RULE_BOOL"
    public final void mRULE_BOOL() throws RecognitionException {
        try {
            int _type = RULE_BOOL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalHdbti.g:2371:11: ( ( 'true' | 'false' ) )
            // InternalHdbti.g:2371:13: ( 'true' | 'false' )
            {
            // InternalHdbti.g:2371:13: ( 'true' | 'false' )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0=='t') ) {
                alt1=1;
            }
            else if ( (LA1_0=='f') ) {
                alt1=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }
            switch (alt1) {
                case 1 :
                    // InternalHdbti.g:2371:14: 'true'
                    {
                    match("true"); 


                    }
                    break;
                case 2 :
                    // InternalHdbti.g:2371:21: 'false'
                    {
                    match("false"); 


                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_BOOL"

    // $ANTLR start "RULE_ID"
    public final void mRULE_ID() throws RecognitionException {
        try {
            int _type = RULE_ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalHdbti.g:2373:9: ( ( '^' )? ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )* )
            // InternalHdbti.g:2373:11: ( '^' )? ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            {
            // InternalHdbti.g:2373:11: ( '^' )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0=='^') ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // InternalHdbti.g:2373:11: '^'
                    {
                    match('^'); 

                    }
                    break;

            }

            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // InternalHdbti.g:2373:40: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0>='0' && LA3_0<='9')||(LA3_0>='A' && LA3_0<='Z')||LA3_0=='_'||(LA3_0>='a' && LA3_0<='z')) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // InternalHdbti.g:
            	    {
            	    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ID"

    // $ANTLR start "RULE_INT"
    public final void mRULE_INT() throws RecognitionException {
        try {
            int _type = RULE_INT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalHdbti.g:2375:10: ( ( '0' .. '9' )+ )
            // InternalHdbti.g:2375:12: ( '0' .. '9' )+
            {
            // InternalHdbti.g:2375:12: ( '0' .. '9' )+
            int cnt4=0;
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0>='0' && LA4_0<='9')) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // InternalHdbti.g:2375:13: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

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


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_INT"

    // $ANTLR start "RULE_STRING"
    public final void mRULE_STRING() throws RecognitionException {
        try {
            int _type = RULE_STRING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalHdbti.g:2377:13: ( ( '\"' ( '\\\\' . | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' . | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' ) )
            // InternalHdbti.g:2377:15: ( '\"' ( '\\\\' . | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' . | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' )
            {
            // InternalHdbti.g:2377:15: ( '\"' ( '\\\\' . | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' . | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' )
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0=='\"') ) {
                alt7=1;
            }
            else if ( (LA7_0=='\'') ) {
                alt7=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }
            switch (alt7) {
                case 1 :
                    // InternalHdbti.g:2377:16: '\"' ( '\\\\' . | ~ ( ( '\\\\' | '\"' ) ) )* '\"'
                    {
                    match('\"'); 
                    // InternalHdbti.g:2377:20: ( '\\\\' . | ~ ( ( '\\\\' | '\"' ) ) )*
                    loop5:
                    do {
                        int alt5=3;
                        int LA5_0 = input.LA(1);

                        if ( (LA5_0=='\\') ) {
                            alt5=1;
                        }
                        else if ( ((LA5_0>='\u0000' && LA5_0<='!')||(LA5_0>='#' && LA5_0<='[')||(LA5_0>=']' && LA5_0<='\uFFFF')) ) {
                            alt5=2;
                        }


                        switch (alt5) {
                    	case 1 :
                    	    // InternalHdbti.g:2377:21: '\\\\' .
                    	    {
                    	    match('\\'); 
                    	    matchAny(); 

                    	    }
                    	    break;
                    	case 2 :
                    	    // InternalHdbti.g:2377:28: ~ ( ( '\\\\' | '\"' ) )
                    	    {
                    	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='!')||(input.LA(1)>='#' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFF') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;}


                    	    }
                    	    break;

                    	default :
                    	    break loop5;
                        }
                    } while (true);

                    match('\"'); 

                    }
                    break;
                case 2 :
                    // InternalHdbti.g:2377:48: '\\'' ( '\\\\' . | ~ ( ( '\\\\' | '\\'' ) ) )* '\\''
                    {
                    match('\''); 
                    // InternalHdbti.g:2377:53: ( '\\\\' . | ~ ( ( '\\\\' | '\\'' ) ) )*
                    loop6:
                    do {
                        int alt6=3;
                        int LA6_0 = input.LA(1);

                        if ( (LA6_0=='\\') ) {
                            alt6=1;
                        }
                        else if ( ((LA6_0>='\u0000' && LA6_0<='&')||(LA6_0>='(' && LA6_0<='[')||(LA6_0>=']' && LA6_0<='\uFFFF')) ) {
                            alt6=2;
                        }


                        switch (alt6) {
                    	case 1 :
                    	    // InternalHdbti.g:2377:54: '\\\\' .
                    	    {
                    	    match('\\'); 
                    	    matchAny(); 

                    	    }
                    	    break;
                    	case 2 :
                    	    // InternalHdbti.g:2377:61: ~ ( ( '\\\\' | '\\'' ) )
                    	    {
                    	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='&')||(input.LA(1)>='(' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFF') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;}


                    	    }
                    	    break;

                    	default :
                    	    break loop6;
                        }
                    } while (true);

                    match('\''); 

                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_STRING"

    // $ANTLR start "RULE_ML_COMMENT"
    public final void mRULE_ML_COMMENT() throws RecognitionException {
        try {
            int _type = RULE_ML_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalHdbti.g:2379:17: ( '/*' ( options {greedy=false; } : . )* '*/' )
            // InternalHdbti.g:2379:19: '/*' ( options {greedy=false; } : . )* '*/'
            {
            match("/*"); 

            // InternalHdbti.g:2379:24: ( options {greedy=false; } : . )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0=='*') ) {
                    int LA8_1 = input.LA(2);

                    if ( (LA8_1=='/') ) {
                        alt8=2;
                    }
                    else if ( ((LA8_1>='\u0000' && LA8_1<='.')||(LA8_1>='0' && LA8_1<='\uFFFF')) ) {
                        alt8=1;
                    }


                }
                else if ( ((LA8_0>='\u0000' && LA8_0<=')')||(LA8_0>='+' && LA8_0<='\uFFFF')) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // InternalHdbti.g:2379:52: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);

            match("*/"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ML_COMMENT"

    // $ANTLR start "RULE_SL_COMMENT"
    public final void mRULE_SL_COMMENT() throws RecognitionException {
        try {
            int _type = RULE_SL_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalHdbti.g:2381:17: ( '//' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )? )
            // InternalHdbti.g:2381:19: '//' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )?
            {
            match("//"); 

            // InternalHdbti.g:2381:24: (~ ( ( '\\n' | '\\r' ) ) )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( ((LA9_0>='\u0000' && LA9_0<='\t')||(LA9_0>='\u000B' && LA9_0<='\f')||(LA9_0>='\u000E' && LA9_0<='\uFFFF')) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // InternalHdbti.g:2381:24: ~ ( ( '\\n' | '\\r' ) )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='\uFFFF') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);

            // InternalHdbti.g:2381:40: ( ( '\\r' )? '\\n' )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0=='\n'||LA11_0=='\r') ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // InternalHdbti.g:2381:41: ( '\\r' )? '\\n'
                    {
                    // InternalHdbti.g:2381:41: ( '\\r' )?
                    int alt10=2;
                    int LA10_0 = input.LA(1);

                    if ( (LA10_0=='\r') ) {
                        alt10=1;
                    }
                    switch (alt10) {
                        case 1 :
                            // InternalHdbti.g:2381:41: '\\r'
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
        }
        finally {
        }
    }
    // $ANTLR end "RULE_SL_COMMENT"

    // $ANTLR start "RULE_WS"
    public final void mRULE_WS() throws RecognitionException {
        try {
            int _type = RULE_WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalHdbti.g:2383:9: ( ( ' ' | '\\t' | '\\r' | '\\n' )+ )
            // InternalHdbti.g:2383:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            {
            // InternalHdbti.g:2383:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            int cnt12=0;
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( ((LA12_0>='\t' && LA12_0<='\n')||LA12_0=='\r'||LA12_0==' ') ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // InternalHdbti.g:
            	    {
            	    if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    if ( cnt12 >= 1 ) break loop12;
                        EarlyExitException eee =
                            new EarlyExitException(12, input);
                        throw eee;
                }
                cnt12++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_WS"

    // $ANTLR start "RULE_ANY_OTHER"
    public final void mRULE_ANY_OTHER() throws RecognitionException {
        try {
            int _type = RULE_ANY_OTHER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalHdbti.g:2385:16: ( . )
            // InternalHdbti.g:2385:18: .
            {
            matchAny(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ANY_OTHER"

    public void mTokens() throws RecognitionException {
        // InternalHdbti.g:1:8: ( T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | RULE_BOOL | RULE_ID | RULE_INT | RULE_STRING | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_WS | RULE_ANY_OTHER )
        int alt13=25;
        alt13 = dfa13.predict(input);
        switch (alt13) {
            case 1 :
                // InternalHdbti.g:1:10: T__12
                {
                mT__12(); 

                }
                break;
            case 2 :
                // InternalHdbti.g:1:16: T__13
                {
                mT__13(); 

                }
                break;
            case 3 :
                // InternalHdbti.g:1:22: T__14
                {
                mT__14(); 

                }
                break;
            case 4 :
                // InternalHdbti.g:1:28: T__15
                {
                mT__15(); 

                }
                break;
            case 5 :
                // InternalHdbti.g:1:34: T__16
                {
                mT__16(); 

                }
                break;
            case 6 :
                // InternalHdbti.g:1:40: T__17
                {
                mT__17(); 

                }
                break;
            case 7 :
                // InternalHdbti.g:1:46: T__18
                {
                mT__18(); 

                }
                break;
            case 8 :
                // InternalHdbti.g:1:52: T__19
                {
                mT__19(); 

                }
                break;
            case 9 :
                // InternalHdbti.g:1:58: T__20
                {
                mT__20(); 

                }
                break;
            case 10 :
                // InternalHdbti.g:1:64: T__21
                {
                mT__21(); 

                }
                break;
            case 11 :
                // InternalHdbti.g:1:70: T__22
                {
                mT__22(); 

                }
                break;
            case 12 :
                // InternalHdbti.g:1:76: T__23
                {
                mT__23(); 

                }
                break;
            case 13 :
                // InternalHdbti.g:1:82: T__24
                {
                mT__24(); 

                }
                break;
            case 14 :
                // InternalHdbti.g:1:88: T__25
                {
                mT__25(); 

                }
                break;
            case 15 :
                // InternalHdbti.g:1:94: T__26
                {
                mT__26(); 

                }
                break;
            case 16 :
                // InternalHdbti.g:1:100: T__27
                {
                mT__27(); 

                }
                break;
            case 17 :
                // InternalHdbti.g:1:106: T__28
                {
                mT__28(); 

                }
                break;
            case 18 :
                // InternalHdbti.g:1:112: RULE_BOOL
                {
                mRULE_BOOL(); 

                }
                break;
            case 19 :
                // InternalHdbti.g:1:122: RULE_ID
                {
                mRULE_ID(); 

                }
                break;
            case 20 :
                // InternalHdbti.g:1:130: RULE_INT
                {
                mRULE_INT(); 

                }
                break;
            case 21 :
                // InternalHdbti.g:1:139: RULE_STRING
                {
                mRULE_STRING(); 

                }
                break;
            case 22 :
                // InternalHdbti.g:1:151: RULE_ML_COMMENT
                {
                mRULE_ML_COMMENT(); 

                }
                break;
            case 23 :
                // InternalHdbti.g:1:167: RULE_SL_COMMENT
                {
                mRULE_SL_COMMENT(); 

                }
                break;
            case 24 :
                // InternalHdbti.g:1:183: RULE_WS
                {
                mRULE_WS(); 

                }
                break;
            case 25 :
                // InternalHdbti.g:1:191: RULE_ANY_OTHER
                {
                mRULE_ANY_OTHER(); 

                }
                break;

        }

    }


    protected DFA13 dfa13 = new DFA13(this);
    static final String DFA13_eotS =
        "\1\uffff\1\27\10\uffff\7\43\1\27\2\uffff\2\27\14\uffff\1\43\1\uffff\10\43\5\uffff\11\43\1\uffff\2\43\1\107\1\43\1\111\3\43\1\115\1\uffff\1\43\1\120\1\uffff\1\43\1\uffff\1\107\2\43\2\uffff\1\126\1\uffff\1\127\2\43\1\132\3\uffff\2\43\2\uffff\2\43\1\uffff\2\43\1\uffff\1\145\1\43\2\uffff\1\43\1\uffff\1\43\1\uffff\1\43\1\154\1\uffff";
    static final String DFA13_eofS =
        "\155\uffff";
    static final String DFA13_minS =
        "\2\0\10\uffff\1\155\1\141\1\143\1\141\3\145\1\101\2\uffff\1\0\1\52\2\uffff\1\0\11\uffff\1\160\1\uffff\1\142\1\165\1\150\3\154\1\141\1\171\4\uffff\1\0\1\157\1\154\3\145\1\163\1\151\1\144\1\163\1\0\1\162\1\145\1\60\1\155\1\60\1\145\1\155\1\145\1\60\1\0\1\164\1\60\1\uffff\1\141\1\uffff\1\60\1\105\1\162\1\uffff\1\0\1\60\1\uffff\1\60\1\151\1\156\1\60\1\0\2\uffff\1\145\1\143\1\uffff\1\0\2\154\1\0\1\144\1\157\1\0\1\60\1\163\1\0\1\uffff\1\151\1\uffff\1\156\1\uffff\1\147\1\60\1\uffff";
    static final String DFA13_maxS =
        "\2\uffff\10\uffff\1\155\1\162\1\143\1\151\3\145\1\172\2\uffff\1\uffff\1\57\2\uffff\1\uffff\11\uffff\1\160\1\uffff\1\142\1\165\1\150\3\154\1\141\1\171\4\uffff\1\uffff\1\157\1\154\3\145\1\163\1\151\1\144\1\163\1\uffff\1\162\1\145\1\172\1\155\1\172\1\145\1\155\1\145\1\172\1\uffff\1\164\1\172\1\uffff\1\141\1\uffff\1\172\1\106\1\162\1\uffff\1\uffff\1\172\1\uffff\1\172\1\151\1\156\1\172\1\uffff\2\uffff\1\145\1\143\1\uffff\1\uffff\2\154\1\uffff\1\144\1\157\1\uffff\1\172\1\163\1\uffff\1\uffff\1\151\1\uffff\1\156\1\uffff\1\147\1\172\1\uffff";
    static final String DFA13_acceptS =
        "\2\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\10\uffff\1\23\1\24\2\uffff\1\30\1\31\1\uffff\1\25\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\uffff\1\23\10\uffff\1\24\1\26\1\27\1\30\27\uffff\1\22\1\uffff\1\15\3\uffff\1\20\2\uffff\1\13\5\uffff\1\12\1\14\2\uffff\1\17\12\uffff\1\16\1\uffff\1\1\1\uffff\1\1\2\uffff\1\21";
    static final String DFA13_specialS =
        "\1\1\1\14\22\uffff\1\0\3\uffff\1\2\27\uffff\1\3\11\uffff\1\4\11\uffff\1\5\11\uffff\1\6\6\uffff\1\7\5\uffff\1\10\2\uffff\1\11\2\uffff\1\12\2\uffff\1\13\10\uffff}>";
    static final String[] DFA13_transitionS = {
            "\11\27\2\26\2\27\1\26\22\27\1\26\1\27\1\1\4\27\1\24\4\27\1\10\2\27\1\25\12\23\1\2\1\5\1\27\1\4\3\27\32\22\1\6\1\27\1\7\1\21\1\22\1\27\3\22\1\16\1\22\1\15\1\22\1\17\1\12\1\22\1\20\7\22\1\14\1\13\6\22\1\3\1\27\1\11\uff82\27",
            "\107\31\1\30\uffb8\31",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\42",
            "\1\44\20\uffff\1\45",
            "\1\46",
            "\1\50\7\uffff\1\47",
            "\1\51",
            "\1\52",
            "\1\53",
            "\32\43\4\uffff\1\43\1\uffff\32\43",
            "",
            "",
            "\0\31",
            "\1\55\4\uffff\1\56",
            "",
            "",
            "\122\31\1\60\uffad\31",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\61",
            "",
            "\1\62",
            "\1\63",
            "\1\64",
            "\1\65",
            "\1\66",
            "\1\67",
            "\1\70",
            "\1\71",
            "",
            "",
            "",
            "",
            "\117\31\1\72\uffb0\31",
            "\1\73",
            "\1\74",
            "\1\75",
            "\1\76",
            "\1\77",
            "\1\100",
            "\1\101",
            "\1\102",
            "\1\103",
            "\125\31\1\104\uffaa\31",
            "\1\105",
            "\1\106",
            "\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
            "\1\110",
            "\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
            "\1\112",
            "\1\113",
            "\1\114",
            "\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
            "\120\31\1\116\uffaf\31",
            "\1\117",
            "\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
            "",
            "\1\121",
            "",
            "\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
            "\1\123\1\122",
            "\1\124",
            "",
            "\137\31\1\125\uffa0\31",
            "\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
            "",
            "\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
            "\1\130",
            "\1\131",
            "\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
            "\124\31\1\133\uffab\31",
            "",
            "",
            "\1\134",
            "\1\135",
            "",
            "\131\31\1\136\uffa6\31",
            "\1\137",
            "\1\140",
            "\120\31\1\141\uffaf\31",
            "\1\142",
            "\1\143",
            "\105\31\1\144\uffba\31",
            "\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
            "\1\146",
            "\42\31\1\147\uffdd\31",
            "",
            "\1\150",
            "",
            "\1\152",
            "",
            "\1\153",
            "\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
            ""
    };

    static final short[] DFA13_eot = DFA.unpackEncodedString(DFA13_eotS);
    static final short[] DFA13_eof = DFA.unpackEncodedString(DFA13_eofS);
    static final char[] DFA13_min = DFA.unpackEncodedStringToUnsignedChars(DFA13_minS);
    static final char[] DFA13_max = DFA.unpackEncodedStringToUnsignedChars(DFA13_maxS);
    static final short[] DFA13_accept = DFA.unpackEncodedString(DFA13_acceptS);
    static final short[] DFA13_special = DFA.unpackEncodedString(DFA13_specialS);
    static final short[][] DFA13_transition;

    static {
        int numStates = DFA13_transitionS.length;
        DFA13_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA13_transition[i] = DFA.unpackEncodedString(DFA13_transitionS[i]);
        }
    }

    class DFA13 extends DFA {

        public DFA13(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 13;
            this.eot = DFA13_eot;
            this.eof = DFA13_eof;
            this.min = DFA13_min;
            this.max = DFA13_max;
            this.accept = DFA13_accept;
            this.special = DFA13_special;
            this.transition = DFA13_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | RULE_BOOL | RULE_ID | RULE_INT | RULE_STRING | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_WS | RULE_ANY_OTHER );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA13_20 = input.LA(1);

                        s = -1;
                        if ( ((LA13_20>='\u0000' && LA13_20<='\uFFFF')) ) {s = 25;}

                        else s = 23;

                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA13_0 = input.LA(1);

                        s = -1;
                        if ( (LA13_0=='\"') ) {s = 1;}

                        else if ( (LA13_0==':') ) {s = 2;}

                        else if ( (LA13_0=='{') ) {s = 3;}

                        else if ( (LA13_0=='=') ) {s = 4;}

                        else if ( (LA13_0==';') ) {s = 5;}

                        else if ( (LA13_0=='[') ) {s = 6;}

                        else if ( (LA13_0==']') ) {s = 7;}

                        else if ( (LA13_0==',') ) {s = 8;}

                        else if ( (LA13_0=='}') ) {s = 9;}

                        else if ( (LA13_0=='i') ) {s = 10;}

                        else if ( (LA13_0=='t') ) {s = 11;}

                        else if ( (LA13_0=='s') ) {s = 12;}

                        else if ( (LA13_0=='f') ) {s = 13;}

                        else if ( (LA13_0=='d') ) {s = 14;}

                        else if ( (LA13_0=='h') ) {s = 15;}

                        else if ( (LA13_0=='k') ) {s = 16;}

                        else if ( (LA13_0=='^') ) {s = 17;}

                        else if ( ((LA13_0>='A' && LA13_0<='Z')||LA13_0=='_'||(LA13_0>='a' && LA13_0<='c')||LA13_0=='e'||LA13_0=='g'||LA13_0=='j'||(LA13_0>='l' && LA13_0<='r')||(LA13_0>='u' && LA13_0<='z')) ) {s = 18;}

                        else if ( ((LA13_0>='0' && LA13_0<='9')) ) {s = 19;}

                        else if ( (LA13_0=='\'') ) {s = 20;}

                        else if ( (LA13_0=='/') ) {s = 21;}

                        else if ( ((LA13_0>='\t' && LA13_0<='\n')||LA13_0=='\r'||LA13_0==' ') ) {s = 22;}

                        else if ( ((LA13_0>='\u0000' && LA13_0<='\b')||(LA13_0>='\u000B' && LA13_0<='\f')||(LA13_0>='\u000E' && LA13_0<='\u001F')||LA13_0=='!'||(LA13_0>='#' && LA13_0<='&')||(LA13_0>='(' && LA13_0<='+')||(LA13_0>='-' && LA13_0<='.')||LA13_0=='<'||(LA13_0>='>' && LA13_0<='@')||LA13_0=='\\'||LA13_0=='`'||LA13_0=='|'||(LA13_0>='~' && LA13_0<='\uFFFF')) ) {s = 23;}

                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA13_24 = input.LA(1);

                        s = -1;
                        if ( (LA13_24=='R') ) {s = 48;}

                        else if ( ((LA13_24>='\u0000' && LA13_24<='Q')||(LA13_24>='S' && LA13_24<='\uFFFF')) ) {s = 25;}

                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA13_48 = input.LA(1);

                        s = -1;
                        if ( (LA13_48=='O') ) {s = 58;}

                        else if ( ((LA13_48>='\u0000' && LA13_48<='N')||(LA13_48>='P' && LA13_48<='\uFFFF')) ) {s = 25;}

                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA13_58 = input.LA(1);

                        s = -1;
                        if ( (LA13_58=='U') ) {s = 68;}

                        else if ( ((LA13_58>='\u0000' && LA13_58<='T')||(LA13_58>='V' && LA13_58<='\uFFFF')) ) {s = 25;}

                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA13_68 = input.LA(1);

                        s = -1;
                        if ( (LA13_68=='P') ) {s = 78;}

                        else if ( ((LA13_68>='\u0000' && LA13_68<='O')||(LA13_68>='Q' && LA13_68<='\uFFFF')) ) {s = 25;}

                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA13_78 = input.LA(1);

                        s = -1;
                        if ( (LA13_78=='_') ) {s = 85;}

                        else if ( ((LA13_78>='\u0000' && LA13_78<='^')||(LA13_78>='`' && LA13_78<='\uFFFF')) ) {s = 25;}

                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA13_85 = input.LA(1);

                        s = -1;
                        if ( (LA13_85=='T') ) {s = 91;}

                        else if ( ((LA13_85>='\u0000' && LA13_85<='S')||(LA13_85>='U' && LA13_85<='\uFFFF')) ) {s = 25;}

                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA13_91 = input.LA(1);

                        s = -1;
                        if ( (LA13_91=='Y') ) {s = 94;}

                        else if ( ((LA13_91>='\u0000' && LA13_91<='X')||(LA13_91>='Z' && LA13_91<='\uFFFF')) ) {s = 25;}

                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA13_94 = input.LA(1);

                        s = -1;
                        if ( (LA13_94=='P') ) {s = 97;}

                        else if ( ((LA13_94>='\u0000' && LA13_94<='O')||(LA13_94>='Q' && LA13_94<='\uFFFF')) ) {s = 25;}

                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA13_97 = input.LA(1);

                        s = -1;
                        if ( (LA13_97=='E') ) {s = 100;}

                        else if ( ((LA13_97>='\u0000' && LA13_97<='D')||(LA13_97>='F' && LA13_97<='\uFFFF')) ) {s = 25;}

                        if ( s>=0 ) return s;
                        break;
                    case 11 : 
                        int LA13_100 = input.LA(1);

                        s = -1;
                        if ( (LA13_100=='\"') ) {s = 103;}

                        else if ( ((LA13_100>='\u0000' && LA13_100<='!')||(LA13_100>='#' && LA13_100<='\uFFFF')) ) {s = 25;}

                        if ( s>=0 ) return s;
                        break;
                    case 12 : 
                        int LA13_1 = input.LA(1);

                        s = -1;
                        if ( (LA13_1=='G') ) {s = 24;}

                        else if ( ((LA13_1>='\u0000' && LA13_1<='F')||(LA13_1>='H' && LA13_1<='\uFFFF')) ) {s = 25;}

                        else s = 23;

                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 13, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

}