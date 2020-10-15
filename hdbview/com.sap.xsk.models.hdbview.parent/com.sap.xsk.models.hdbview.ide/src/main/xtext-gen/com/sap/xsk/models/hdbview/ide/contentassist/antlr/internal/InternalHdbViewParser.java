package com.sap.xsk.models.hdbview.ide.contentassist.antlr.internal;

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
import com.sap.xsk.models.hdbview.services.HdbViewGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalHdbViewParser extends AbstractInternalContentAssistParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_STRING", "RULE_BOOL", "RULE_ID", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'='", "';'", "'['", "']'", "','", "'schema'", "'query'", "'depends_on'"
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

    	public void setGrammarAccess(HdbViewGrammarAccess grammarAccess) {
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



    // $ANTLR start "entryRuleHdbViewModel"
    // InternalHdbView.g:53:1: entryRuleHdbViewModel : ruleHdbViewModel EOF ;
    public final void entryRuleHdbViewModel() throws RecognitionException {
        try {
            // InternalHdbView.g:54:1: ( ruleHdbViewModel EOF )
            // InternalHdbView.g:55:1: ruleHdbViewModel EOF
            {
             before(grammarAccess.getHdbViewModelRule()); 
            pushFollow(FOLLOW_1);
            ruleHdbViewModel();

            state._fsp--;

             after(grammarAccess.getHdbViewModelRule()); 
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
    // $ANTLR end "entryRuleHdbViewModel"


    // $ANTLR start "ruleHdbViewModel"
    // InternalHdbView.g:62:1: ruleHdbViewModel : ( ( rule__HdbViewModel__ViewElementAssignment ) ) ;
    public final void ruleHdbViewModel() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbView.g:66:2: ( ( ( rule__HdbViewModel__ViewElementAssignment ) ) )
            // InternalHdbView.g:67:2: ( ( rule__HdbViewModel__ViewElementAssignment ) )
            {
            // InternalHdbView.g:67:2: ( ( rule__HdbViewModel__ViewElementAssignment ) )
            // InternalHdbView.g:68:3: ( rule__HdbViewModel__ViewElementAssignment )
            {
             before(grammarAccess.getHdbViewModelAccess().getViewElementAssignment()); 
            // InternalHdbView.g:69:3: ( rule__HdbViewModel__ViewElementAssignment )
            // InternalHdbView.g:69:4: rule__HdbViewModel__ViewElementAssignment
            {
            pushFollow(FOLLOW_2);
            rule__HdbViewModel__ViewElementAssignment();

            state._fsp--;


            }

             after(grammarAccess.getHdbViewModelAccess().getViewElementAssignment()); 

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
    // $ANTLR end "ruleHdbViewModel"


    // $ANTLR start "entryRuleView"
    // InternalHdbView.g:78:1: entryRuleView : ruleView EOF ;
    public final void entryRuleView() throws RecognitionException {
        try {
            // InternalHdbView.g:79:1: ( ruleView EOF )
            // InternalHdbView.g:80:1: ruleView EOF
            {
             before(grammarAccess.getViewRule()); 
            pushFollow(FOLLOW_1);
            ruleView();

            state._fsp--;

             after(grammarAccess.getViewRule()); 
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
    // $ANTLR end "entryRuleView"


    // $ANTLR start "ruleView"
    // InternalHdbView.g:87:1: ruleView : ( ( rule__View__UnorderedGroup ) ) ;
    public final void ruleView() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbView.g:91:2: ( ( ( rule__View__UnorderedGroup ) ) )
            // InternalHdbView.g:92:2: ( ( rule__View__UnorderedGroup ) )
            {
            // InternalHdbView.g:92:2: ( ( rule__View__UnorderedGroup ) )
            // InternalHdbView.g:93:3: ( rule__View__UnorderedGroup )
            {
             before(grammarAccess.getViewAccess().getUnorderedGroup()); 
            // InternalHdbView.g:94:3: ( rule__View__UnorderedGroup )
            // InternalHdbView.g:94:4: rule__View__UnorderedGroup
            {
            pushFollow(FOLLOW_2);
            rule__View__UnorderedGroup();

            state._fsp--;


            }

             after(grammarAccess.getViewAccess().getUnorderedGroup()); 

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
    // $ANTLR end "ruleView"


    // $ANTLR start "rule__View__Group_0__0"
    // InternalHdbView.g:102:1: rule__View__Group_0__0 : rule__View__Group_0__0__Impl rule__View__Group_0__1 ;
    public final void rule__View__Group_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbView.g:106:1: ( rule__View__Group_0__0__Impl rule__View__Group_0__1 )
            // InternalHdbView.g:107:2: rule__View__Group_0__0__Impl rule__View__Group_0__1
            {
            pushFollow(FOLLOW_3);
            rule__View__Group_0__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__View__Group_0__1();

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
    // $ANTLR end "rule__View__Group_0__0"


    // $ANTLR start "rule__View__Group_0__0__Impl"
    // InternalHdbView.g:114:1: rule__View__Group_0__0__Impl : ( ( rule__View__SchemaAssignment_0_0 ) ) ;
    public final void rule__View__Group_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbView.g:118:1: ( ( ( rule__View__SchemaAssignment_0_0 ) ) )
            // InternalHdbView.g:119:1: ( ( rule__View__SchemaAssignment_0_0 ) )
            {
            // InternalHdbView.g:119:1: ( ( rule__View__SchemaAssignment_0_0 ) )
            // InternalHdbView.g:120:2: ( rule__View__SchemaAssignment_0_0 )
            {
             before(grammarAccess.getViewAccess().getSchemaAssignment_0_0()); 
            // InternalHdbView.g:121:2: ( rule__View__SchemaAssignment_0_0 )
            // InternalHdbView.g:121:3: rule__View__SchemaAssignment_0_0
            {
            pushFollow(FOLLOW_2);
            rule__View__SchemaAssignment_0_0();

            state._fsp--;


            }

             after(grammarAccess.getViewAccess().getSchemaAssignment_0_0()); 

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
    // $ANTLR end "rule__View__Group_0__0__Impl"


    // $ANTLR start "rule__View__Group_0__1"
    // InternalHdbView.g:129:1: rule__View__Group_0__1 : rule__View__Group_0__1__Impl rule__View__Group_0__2 ;
    public final void rule__View__Group_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbView.g:133:1: ( rule__View__Group_0__1__Impl rule__View__Group_0__2 )
            // InternalHdbView.g:134:2: rule__View__Group_0__1__Impl rule__View__Group_0__2
            {
            pushFollow(FOLLOW_4);
            rule__View__Group_0__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__View__Group_0__2();

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
    // $ANTLR end "rule__View__Group_0__1"


    // $ANTLR start "rule__View__Group_0__1__Impl"
    // InternalHdbView.g:141:1: rule__View__Group_0__1__Impl : ( '=' ) ;
    public final void rule__View__Group_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbView.g:145:1: ( ( '=' ) )
            // InternalHdbView.g:146:1: ( '=' )
            {
            // InternalHdbView.g:146:1: ( '=' )
            // InternalHdbView.g:147:2: '='
            {
             before(grammarAccess.getViewAccess().getEqualsSignKeyword_0_1()); 
            match(input,12,FOLLOW_2); 
             after(grammarAccess.getViewAccess().getEqualsSignKeyword_0_1()); 

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
    // $ANTLR end "rule__View__Group_0__1__Impl"


    // $ANTLR start "rule__View__Group_0__2"
    // InternalHdbView.g:156:1: rule__View__Group_0__2 : rule__View__Group_0__2__Impl rule__View__Group_0__3 ;
    public final void rule__View__Group_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbView.g:160:1: ( rule__View__Group_0__2__Impl rule__View__Group_0__3 )
            // InternalHdbView.g:161:2: rule__View__Group_0__2__Impl rule__View__Group_0__3
            {
            pushFollow(FOLLOW_5);
            rule__View__Group_0__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__View__Group_0__3();

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
    // $ANTLR end "rule__View__Group_0__2"


    // $ANTLR start "rule__View__Group_0__2__Impl"
    // InternalHdbView.g:168:1: rule__View__Group_0__2__Impl : ( ( rule__View__SchemaNameAssignment_0_2 ) ) ;
    public final void rule__View__Group_0__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbView.g:172:1: ( ( ( rule__View__SchemaNameAssignment_0_2 ) ) )
            // InternalHdbView.g:173:1: ( ( rule__View__SchemaNameAssignment_0_2 ) )
            {
            // InternalHdbView.g:173:1: ( ( rule__View__SchemaNameAssignment_0_2 ) )
            // InternalHdbView.g:174:2: ( rule__View__SchemaNameAssignment_0_2 )
            {
             before(grammarAccess.getViewAccess().getSchemaNameAssignment_0_2()); 
            // InternalHdbView.g:175:2: ( rule__View__SchemaNameAssignment_0_2 )
            // InternalHdbView.g:175:3: rule__View__SchemaNameAssignment_0_2
            {
            pushFollow(FOLLOW_2);
            rule__View__SchemaNameAssignment_0_2();

            state._fsp--;


            }

             after(grammarAccess.getViewAccess().getSchemaNameAssignment_0_2()); 

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
    // $ANTLR end "rule__View__Group_0__2__Impl"


    // $ANTLR start "rule__View__Group_0__3"
    // InternalHdbView.g:183:1: rule__View__Group_0__3 : rule__View__Group_0__3__Impl ;
    public final void rule__View__Group_0__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbView.g:187:1: ( rule__View__Group_0__3__Impl )
            // InternalHdbView.g:188:2: rule__View__Group_0__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__View__Group_0__3__Impl();

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
    // $ANTLR end "rule__View__Group_0__3"


    // $ANTLR start "rule__View__Group_0__3__Impl"
    // InternalHdbView.g:194:1: rule__View__Group_0__3__Impl : ( ';' ) ;
    public final void rule__View__Group_0__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbView.g:198:1: ( ( ';' ) )
            // InternalHdbView.g:199:1: ( ';' )
            {
            // InternalHdbView.g:199:1: ( ';' )
            // InternalHdbView.g:200:2: ';'
            {
             before(grammarAccess.getViewAccess().getSemicolonKeyword_0_3()); 
            match(input,13,FOLLOW_2); 
             after(grammarAccess.getViewAccess().getSemicolonKeyword_0_3()); 

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
    // $ANTLR end "rule__View__Group_0__3__Impl"


    // $ANTLR start "rule__View__Group_1__0"
    // InternalHdbView.g:210:1: rule__View__Group_1__0 : rule__View__Group_1__0__Impl rule__View__Group_1__1 ;
    public final void rule__View__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbView.g:214:1: ( rule__View__Group_1__0__Impl rule__View__Group_1__1 )
            // InternalHdbView.g:215:2: rule__View__Group_1__0__Impl rule__View__Group_1__1
            {
            pushFollow(FOLLOW_3);
            rule__View__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__View__Group_1__1();

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
    // $ANTLR end "rule__View__Group_1__0"


    // $ANTLR start "rule__View__Group_1__0__Impl"
    // InternalHdbView.g:222:1: rule__View__Group_1__0__Impl : ( ( rule__View__QueryAssignment_1_0 ) ) ;
    public final void rule__View__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbView.g:226:1: ( ( ( rule__View__QueryAssignment_1_0 ) ) )
            // InternalHdbView.g:227:1: ( ( rule__View__QueryAssignment_1_0 ) )
            {
            // InternalHdbView.g:227:1: ( ( rule__View__QueryAssignment_1_0 ) )
            // InternalHdbView.g:228:2: ( rule__View__QueryAssignment_1_0 )
            {
             before(grammarAccess.getViewAccess().getQueryAssignment_1_0()); 
            // InternalHdbView.g:229:2: ( rule__View__QueryAssignment_1_0 )
            // InternalHdbView.g:229:3: rule__View__QueryAssignment_1_0
            {
            pushFollow(FOLLOW_2);
            rule__View__QueryAssignment_1_0();

            state._fsp--;


            }

             after(grammarAccess.getViewAccess().getQueryAssignment_1_0()); 

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
    // $ANTLR end "rule__View__Group_1__0__Impl"


    // $ANTLR start "rule__View__Group_1__1"
    // InternalHdbView.g:237:1: rule__View__Group_1__1 : rule__View__Group_1__1__Impl rule__View__Group_1__2 ;
    public final void rule__View__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbView.g:241:1: ( rule__View__Group_1__1__Impl rule__View__Group_1__2 )
            // InternalHdbView.g:242:2: rule__View__Group_1__1__Impl rule__View__Group_1__2
            {
            pushFollow(FOLLOW_4);
            rule__View__Group_1__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__View__Group_1__2();

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
    // $ANTLR end "rule__View__Group_1__1"


    // $ANTLR start "rule__View__Group_1__1__Impl"
    // InternalHdbView.g:249:1: rule__View__Group_1__1__Impl : ( '=' ) ;
    public final void rule__View__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbView.g:253:1: ( ( '=' ) )
            // InternalHdbView.g:254:1: ( '=' )
            {
            // InternalHdbView.g:254:1: ( '=' )
            // InternalHdbView.g:255:2: '='
            {
             before(grammarAccess.getViewAccess().getEqualsSignKeyword_1_1()); 
            match(input,12,FOLLOW_2); 
             after(grammarAccess.getViewAccess().getEqualsSignKeyword_1_1()); 

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
    // $ANTLR end "rule__View__Group_1__1__Impl"


    // $ANTLR start "rule__View__Group_1__2"
    // InternalHdbView.g:264:1: rule__View__Group_1__2 : rule__View__Group_1__2__Impl rule__View__Group_1__3 ;
    public final void rule__View__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbView.g:268:1: ( rule__View__Group_1__2__Impl rule__View__Group_1__3 )
            // InternalHdbView.g:269:2: rule__View__Group_1__2__Impl rule__View__Group_1__3
            {
            pushFollow(FOLLOW_5);
            rule__View__Group_1__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__View__Group_1__3();

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
    // $ANTLR end "rule__View__Group_1__2"


    // $ANTLR start "rule__View__Group_1__2__Impl"
    // InternalHdbView.g:276:1: rule__View__Group_1__2__Impl : ( ( rule__View__QueryValueAssignment_1_2 ) ) ;
    public final void rule__View__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbView.g:280:1: ( ( ( rule__View__QueryValueAssignment_1_2 ) ) )
            // InternalHdbView.g:281:1: ( ( rule__View__QueryValueAssignment_1_2 ) )
            {
            // InternalHdbView.g:281:1: ( ( rule__View__QueryValueAssignment_1_2 ) )
            // InternalHdbView.g:282:2: ( rule__View__QueryValueAssignment_1_2 )
            {
             before(grammarAccess.getViewAccess().getQueryValueAssignment_1_2()); 
            // InternalHdbView.g:283:2: ( rule__View__QueryValueAssignment_1_2 )
            // InternalHdbView.g:283:3: rule__View__QueryValueAssignment_1_2
            {
            pushFollow(FOLLOW_2);
            rule__View__QueryValueAssignment_1_2();

            state._fsp--;


            }

             after(grammarAccess.getViewAccess().getQueryValueAssignment_1_2()); 

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
    // $ANTLR end "rule__View__Group_1__2__Impl"


    // $ANTLR start "rule__View__Group_1__3"
    // InternalHdbView.g:291:1: rule__View__Group_1__3 : rule__View__Group_1__3__Impl ;
    public final void rule__View__Group_1__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbView.g:295:1: ( rule__View__Group_1__3__Impl )
            // InternalHdbView.g:296:2: rule__View__Group_1__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__View__Group_1__3__Impl();

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
    // $ANTLR end "rule__View__Group_1__3"


    // $ANTLR start "rule__View__Group_1__3__Impl"
    // InternalHdbView.g:302:1: rule__View__Group_1__3__Impl : ( ';' ) ;
    public final void rule__View__Group_1__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbView.g:306:1: ( ( ';' ) )
            // InternalHdbView.g:307:1: ( ';' )
            {
            // InternalHdbView.g:307:1: ( ';' )
            // InternalHdbView.g:308:2: ';'
            {
             before(grammarAccess.getViewAccess().getSemicolonKeyword_1_3()); 
            match(input,13,FOLLOW_2); 
             after(grammarAccess.getViewAccess().getSemicolonKeyword_1_3()); 

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
    // $ANTLR end "rule__View__Group_1__3__Impl"


    // $ANTLR start "rule__View__Group_2__0"
    // InternalHdbView.g:318:1: rule__View__Group_2__0 : rule__View__Group_2__0__Impl rule__View__Group_2__1 ;
    public final void rule__View__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbView.g:322:1: ( rule__View__Group_2__0__Impl rule__View__Group_2__1 )
            // InternalHdbView.g:323:2: rule__View__Group_2__0__Impl rule__View__Group_2__1
            {
            pushFollow(FOLLOW_3);
            rule__View__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__View__Group_2__1();

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
    // $ANTLR end "rule__View__Group_2__0"


    // $ANTLR start "rule__View__Group_2__0__Impl"
    // InternalHdbView.g:330:1: rule__View__Group_2__0__Impl : ( ( rule__View__DependsOnAssignment_2_0 ) ) ;
    public final void rule__View__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbView.g:334:1: ( ( ( rule__View__DependsOnAssignment_2_0 ) ) )
            // InternalHdbView.g:335:1: ( ( rule__View__DependsOnAssignment_2_0 ) )
            {
            // InternalHdbView.g:335:1: ( ( rule__View__DependsOnAssignment_2_0 ) )
            // InternalHdbView.g:336:2: ( rule__View__DependsOnAssignment_2_0 )
            {
             before(grammarAccess.getViewAccess().getDependsOnAssignment_2_0()); 
            // InternalHdbView.g:337:2: ( rule__View__DependsOnAssignment_2_0 )
            // InternalHdbView.g:337:3: rule__View__DependsOnAssignment_2_0
            {
            pushFollow(FOLLOW_2);
            rule__View__DependsOnAssignment_2_0();

            state._fsp--;


            }

             after(grammarAccess.getViewAccess().getDependsOnAssignment_2_0()); 

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
    // $ANTLR end "rule__View__Group_2__0__Impl"


    // $ANTLR start "rule__View__Group_2__1"
    // InternalHdbView.g:345:1: rule__View__Group_2__1 : rule__View__Group_2__1__Impl rule__View__Group_2__2 ;
    public final void rule__View__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbView.g:349:1: ( rule__View__Group_2__1__Impl rule__View__Group_2__2 )
            // InternalHdbView.g:350:2: rule__View__Group_2__1__Impl rule__View__Group_2__2
            {
            pushFollow(FOLLOW_6);
            rule__View__Group_2__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__View__Group_2__2();

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
    // $ANTLR end "rule__View__Group_2__1"


    // $ANTLR start "rule__View__Group_2__1__Impl"
    // InternalHdbView.g:357:1: rule__View__Group_2__1__Impl : ( '=' ) ;
    public final void rule__View__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbView.g:361:1: ( ( '=' ) )
            // InternalHdbView.g:362:1: ( '=' )
            {
            // InternalHdbView.g:362:1: ( '=' )
            // InternalHdbView.g:363:2: '='
            {
             before(grammarAccess.getViewAccess().getEqualsSignKeyword_2_1()); 
            match(input,12,FOLLOW_2); 
             after(grammarAccess.getViewAccess().getEqualsSignKeyword_2_1()); 

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
    // $ANTLR end "rule__View__Group_2__1__Impl"


    // $ANTLR start "rule__View__Group_2__2"
    // InternalHdbView.g:372:1: rule__View__Group_2__2 : rule__View__Group_2__2__Impl rule__View__Group_2__3 ;
    public final void rule__View__Group_2__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbView.g:376:1: ( rule__View__Group_2__2__Impl rule__View__Group_2__3 )
            // InternalHdbView.g:377:2: rule__View__Group_2__2__Impl rule__View__Group_2__3
            {
            pushFollow(FOLLOW_7);
            rule__View__Group_2__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__View__Group_2__3();

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
    // $ANTLR end "rule__View__Group_2__2"


    // $ANTLR start "rule__View__Group_2__2__Impl"
    // InternalHdbView.g:384:1: rule__View__Group_2__2__Impl : ( '[' ) ;
    public final void rule__View__Group_2__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbView.g:388:1: ( ( '[' ) )
            // InternalHdbView.g:389:1: ( '[' )
            {
            // InternalHdbView.g:389:1: ( '[' )
            // InternalHdbView.g:390:2: '['
            {
             before(grammarAccess.getViewAccess().getLeftSquareBracketKeyword_2_2()); 
            match(input,14,FOLLOW_2); 
             after(grammarAccess.getViewAccess().getLeftSquareBracketKeyword_2_2()); 

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
    // $ANTLR end "rule__View__Group_2__2__Impl"


    // $ANTLR start "rule__View__Group_2__3"
    // InternalHdbView.g:399:1: rule__View__Group_2__3 : rule__View__Group_2__3__Impl rule__View__Group_2__4 ;
    public final void rule__View__Group_2__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbView.g:403:1: ( rule__View__Group_2__3__Impl rule__View__Group_2__4 )
            // InternalHdbView.g:404:2: rule__View__Group_2__3__Impl rule__View__Group_2__4
            {
            pushFollow(FOLLOW_7);
            rule__View__Group_2__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__View__Group_2__4();

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
    // $ANTLR end "rule__View__Group_2__3"


    // $ANTLR start "rule__View__Group_2__3__Impl"
    // InternalHdbView.g:411:1: rule__View__Group_2__3__Impl : ( ( rule__View__Group_2_3__0 )? ) ;
    public final void rule__View__Group_2__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbView.g:415:1: ( ( ( rule__View__Group_2_3__0 )? ) )
            // InternalHdbView.g:416:1: ( ( rule__View__Group_2_3__0 )? )
            {
            // InternalHdbView.g:416:1: ( ( rule__View__Group_2_3__0 )? )
            // InternalHdbView.g:417:2: ( rule__View__Group_2_3__0 )?
            {
             before(grammarAccess.getViewAccess().getGroup_2_3()); 
            // InternalHdbView.g:418:2: ( rule__View__Group_2_3__0 )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==RULE_STRING) ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // InternalHdbView.g:418:3: rule__View__Group_2_3__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__View__Group_2_3__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getViewAccess().getGroup_2_3()); 

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
    // $ANTLR end "rule__View__Group_2__3__Impl"


    // $ANTLR start "rule__View__Group_2__4"
    // InternalHdbView.g:426:1: rule__View__Group_2__4 : rule__View__Group_2__4__Impl rule__View__Group_2__5 ;
    public final void rule__View__Group_2__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbView.g:430:1: ( rule__View__Group_2__4__Impl rule__View__Group_2__5 )
            // InternalHdbView.g:431:2: rule__View__Group_2__4__Impl rule__View__Group_2__5
            {
            pushFollow(FOLLOW_5);
            rule__View__Group_2__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__View__Group_2__5();

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
    // $ANTLR end "rule__View__Group_2__4"


    // $ANTLR start "rule__View__Group_2__4__Impl"
    // InternalHdbView.g:438:1: rule__View__Group_2__4__Impl : ( ']' ) ;
    public final void rule__View__Group_2__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbView.g:442:1: ( ( ']' ) )
            // InternalHdbView.g:443:1: ( ']' )
            {
            // InternalHdbView.g:443:1: ( ']' )
            // InternalHdbView.g:444:2: ']'
            {
             before(grammarAccess.getViewAccess().getRightSquareBracketKeyword_2_4()); 
            match(input,15,FOLLOW_2); 
             after(grammarAccess.getViewAccess().getRightSquareBracketKeyword_2_4()); 

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
    // $ANTLR end "rule__View__Group_2__4__Impl"


    // $ANTLR start "rule__View__Group_2__5"
    // InternalHdbView.g:453:1: rule__View__Group_2__5 : rule__View__Group_2__5__Impl ;
    public final void rule__View__Group_2__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbView.g:457:1: ( rule__View__Group_2__5__Impl )
            // InternalHdbView.g:458:2: rule__View__Group_2__5__Impl
            {
            pushFollow(FOLLOW_2);
            rule__View__Group_2__5__Impl();

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
    // $ANTLR end "rule__View__Group_2__5"


    // $ANTLR start "rule__View__Group_2__5__Impl"
    // InternalHdbView.g:464:1: rule__View__Group_2__5__Impl : ( ';' ) ;
    public final void rule__View__Group_2__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbView.g:468:1: ( ( ';' ) )
            // InternalHdbView.g:469:1: ( ';' )
            {
            // InternalHdbView.g:469:1: ( ';' )
            // InternalHdbView.g:470:2: ';'
            {
             before(grammarAccess.getViewAccess().getSemicolonKeyword_2_5()); 
            match(input,13,FOLLOW_2); 
             after(grammarAccess.getViewAccess().getSemicolonKeyword_2_5()); 

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
    // $ANTLR end "rule__View__Group_2__5__Impl"


    // $ANTLR start "rule__View__Group_2_3__0"
    // InternalHdbView.g:480:1: rule__View__Group_2_3__0 : rule__View__Group_2_3__0__Impl rule__View__Group_2_3__1 ;
    public final void rule__View__Group_2_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbView.g:484:1: ( rule__View__Group_2_3__0__Impl rule__View__Group_2_3__1 )
            // InternalHdbView.g:485:2: rule__View__Group_2_3__0__Impl rule__View__Group_2_3__1
            {
            pushFollow(FOLLOW_8);
            rule__View__Group_2_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__View__Group_2_3__1();

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
    // $ANTLR end "rule__View__Group_2_3__0"


    // $ANTLR start "rule__View__Group_2_3__0__Impl"
    // InternalHdbView.g:492:1: rule__View__Group_2_3__0__Impl : ( ( rule__View__DependsOnValuesAssignment_2_3_0 ) ) ;
    public final void rule__View__Group_2_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbView.g:496:1: ( ( ( rule__View__DependsOnValuesAssignment_2_3_0 ) ) )
            // InternalHdbView.g:497:1: ( ( rule__View__DependsOnValuesAssignment_2_3_0 ) )
            {
            // InternalHdbView.g:497:1: ( ( rule__View__DependsOnValuesAssignment_2_3_0 ) )
            // InternalHdbView.g:498:2: ( rule__View__DependsOnValuesAssignment_2_3_0 )
            {
             before(grammarAccess.getViewAccess().getDependsOnValuesAssignment_2_3_0()); 
            // InternalHdbView.g:499:2: ( rule__View__DependsOnValuesAssignment_2_3_0 )
            // InternalHdbView.g:499:3: rule__View__DependsOnValuesAssignment_2_3_0
            {
            pushFollow(FOLLOW_2);
            rule__View__DependsOnValuesAssignment_2_3_0();

            state._fsp--;


            }

             after(grammarAccess.getViewAccess().getDependsOnValuesAssignment_2_3_0()); 

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
    // $ANTLR end "rule__View__Group_2_3__0__Impl"


    // $ANTLR start "rule__View__Group_2_3__1"
    // InternalHdbView.g:507:1: rule__View__Group_2_3__1 : rule__View__Group_2_3__1__Impl ;
    public final void rule__View__Group_2_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbView.g:511:1: ( rule__View__Group_2_3__1__Impl )
            // InternalHdbView.g:512:2: rule__View__Group_2_3__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__View__Group_2_3__1__Impl();

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
    // $ANTLR end "rule__View__Group_2_3__1"


    // $ANTLR start "rule__View__Group_2_3__1__Impl"
    // InternalHdbView.g:518:1: rule__View__Group_2_3__1__Impl : ( ( rule__View__Group_2_3_1__0 )* ) ;
    public final void rule__View__Group_2_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbView.g:522:1: ( ( ( rule__View__Group_2_3_1__0 )* ) )
            // InternalHdbView.g:523:1: ( ( rule__View__Group_2_3_1__0 )* )
            {
            // InternalHdbView.g:523:1: ( ( rule__View__Group_2_3_1__0 )* )
            // InternalHdbView.g:524:2: ( rule__View__Group_2_3_1__0 )*
            {
             before(grammarAccess.getViewAccess().getGroup_2_3_1()); 
            // InternalHdbView.g:525:2: ( rule__View__Group_2_3_1__0 )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==16) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // InternalHdbView.g:525:3: rule__View__Group_2_3_1__0
            	    {
            	    pushFollow(FOLLOW_9);
            	    rule__View__Group_2_3_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);

             after(grammarAccess.getViewAccess().getGroup_2_3_1()); 

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
    // $ANTLR end "rule__View__Group_2_3__1__Impl"


    // $ANTLR start "rule__View__Group_2_3_1__0"
    // InternalHdbView.g:534:1: rule__View__Group_2_3_1__0 : rule__View__Group_2_3_1__0__Impl rule__View__Group_2_3_1__1 ;
    public final void rule__View__Group_2_3_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbView.g:538:1: ( rule__View__Group_2_3_1__0__Impl rule__View__Group_2_3_1__1 )
            // InternalHdbView.g:539:2: rule__View__Group_2_3_1__0__Impl rule__View__Group_2_3_1__1
            {
            pushFollow(FOLLOW_4);
            rule__View__Group_2_3_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__View__Group_2_3_1__1();

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
    // $ANTLR end "rule__View__Group_2_3_1__0"


    // $ANTLR start "rule__View__Group_2_3_1__0__Impl"
    // InternalHdbView.g:546:1: rule__View__Group_2_3_1__0__Impl : ( ',' ) ;
    public final void rule__View__Group_2_3_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbView.g:550:1: ( ( ',' ) )
            // InternalHdbView.g:551:1: ( ',' )
            {
            // InternalHdbView.g:551:1: ( ',' )
            // InternalHdbView.g:552:2: ','
            {
             before(grammarAccess.getViewAccess().getCommaKeyword_2_3_1_0()); 
            match(input,16,FOLLOW_2); 
             after(grammarAccess.getViewAccess().getCommaKeyword_2_3_1_0()); 

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
    // $ANTLR end "rule__View__Group_2_3_1__0__Impl"


    // $ANTLR start "rule__View__Group_2_3_1__1"
    // InternalHdbView.g:561:1: rule__View__Group_2_3_1__1 : rule__View__Group_2_3_1__1__Impl ;
    public final void rule__View__Group_2_3_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbView.g:565:1: ( rule__View__Group_2_3_1__1__Impl )
            // InternalHdbView.g:566:2: rule__View__Group_2_3_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__View__Group_2_3_1__1__Impl();

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
    // $ANTLR end "rule__View__Group_2_3_1__1"


    // $ANTLR start "rule__View__Group_2_3_1__1__Impl"
    // InternalHdbView.g:572:1: rule__View__Group_2_3_1__1__Impl : ( ( rule__View__DependsOnValuesAssignment_2_3_1_1 ) ) ;
    public final void rule__View__Group_2_3_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbView.g:576:1: ( ( ( rule__View__DependsOnValuesAssignment_2_3_1_1 ) ) )
            // InternalHdbView.g:577:1: ( ( rule__View__DependsOnValuesAssignment_2_3_1_1 ) )
            {
            // InternalHdbView.g:577:1: ( ( rule__View__DependsOnValuesAssignment_2_3_1_1 ) )
            // InternalHdbView.g:578:2: ( rule__View__DependsOnValuesAssignment_2_3_1_1 )
            {
             before(grammarAccess.getViewAccess().getDependsOnValuesAssignment_2_3_1_1()); 
            // InternalHdbView.g:579:2: ( rule__View__DependsOnValuesAssignment_2_3_1_1 )
            // InternalHdbView.g:579:3: rule__View__DependsOnValuesAssignment_2_3_1_1
            {
            pushFollow(FOLLOW_2);
            rule__View__DependsOnValuesAssignment_2_3_1_1();

            state._fsp--;


            }

             after(grammarAccess.getViewAccess().getDependsOnValuesAssignment_2_3_1_1()); 

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
    // $ANTLR end "rule__View__Group_2_3_1__1__Impl"


    // $ANTLR start "rule__View__UnorderedGroup"
    // InternalHdbView.g:588:1: rule__View__UnorderedGroup : rule__View__UnorderedGroup__0 {...}?;
    public final void rule__View__UnorderedGroup() throws RecognitionException {

        		int stackSize = keepStackSize();
        		getUnorderedGroupHelper().enter(grammarAccess.getViewAccess().getUnorderedGroup());
        	
        try {
            // InternalHdbView.g:593:1: ( rule__View__UnorderedGroup__0 {...}?)
            // InternalHdbView.g:594:2: rule__View__UnorderedGroup__0 {...}?
            {
            pushFollow(FOLLOW_2);
            rule__View__UnorderedGroup__0();

            state._fsp--;

            if ( ! getUnorderedGroupHelper().canLeave(grammarAccess.getViewAccess().getUnorderedGroup()) ) {
                throw new FailedPredicateException(input, "rule__View__UnorderedGroup", "getUnorderedGroupHelper().canLeave(grammarAccess.getViewAccess().getUnorderedGroup())");
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	getUnorderedGroupHelper().leave(grammarAccess.getViewAccess().getUnorderedGroup());
            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__View__UnorderedGroup"


    // $ANTLR start "rule__View__UnorderedGroup__Impl"
    // InternalHdbView.g:602:1: rule__View__UnorderedGroup__Impl : ( ({...}? => ( ( ( rule__View__Group_0__0 ) ) ) ) | ({...}? => ( ( ( rule__View__Group_1__0 ) ) ) ) | ({...}? => ( ( ( rule__View__Group_2__0 ) ) ) ) ) ;
    public final void rule__View__UnorderedGroup__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        		boolean selected = false;
        	
        try {
            // InternalHdbView.g:607:1: ( ( ({...}? => ( ( ( rule__View__Group_0__0 ) ) ) ) | ({...}? => ( ( ( rule__View__Group_1__0 ) ) ) ) | ({...}? => ( ( ( rule__View__Group_2__0 ) ) ) ) ) )
            // InternalHdbView.g:608:3: ( ({...}? => ( ( ( rule__View__Group_0__0 ) ) ) ) | ({...}? => ( ( ( rule__View__Group_1__0 ) ) ) ) | ({...}? => ( ( ( rule__View__Group_2__0 ) ) ) ) )
            {
            // InternalHdbView.g:608:3: ( ({...}? => ( ( ( rule__View__Group_0__0 ) ) ) ) | ({...}? => ( ( ( rule__View__Group_1__0 ) ) ) ) | ({...}? => ( ( ( rule__View__Group_2__0 ) ) ) ) )
            int alt3=3;
            int LA3_0 = input.LA(1);

            if ( LA3_0 == 17 && getUnorderedGroupHelper().canSelect(grammarAccess.getViewAccess().getUnorderedGroup(), 0) ) {
                alt3=1;
            }
            else if ( LA3_0 == 18 && getUnorderedGroupHelper().canSelect(grammarAccess.getViewAccess().getUnorderedGroup(), 1) ) {
                alt3=2;
            }
            else if ( LA3_0 == 19 && getUnorderedGroupHelper().canSelect(grammarAccess.getViewAccess().getUnorderedGroup(), 2) ) {
                alt3=3;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }
            switch (alt3) {
                case 1 :
                    // InternalHdbView.g:609:3: ({...}? => ( ( ( rule__View__Group_0__0 ) ) ) )
                    {
                    // InternalHdbView.g:609:3: ({...}? => ( ( ( rule__View__Group_0__0 ) ) ) )
                    // InternalHdbView.g:610:4: {...}? => ( ( ( rule__View__Group_0__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getViewAccess().getUnorderedGroup(), 0) ) {
                        throw new FailedPredicateException(input, "rule__View__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getViewAccess().getUnorderedGroup(), 0)");
                    }
                    // InternalHdbView.g:610:98: ( ( ( rule__View__Group_0__0 ) ) )
                    // InternalHdbView.g:611:5: ( ( rule__View__Group_0__0 ) )
                    {

                    					getUnorderedGroupHelper().select(grammarAccess.getViewAccess().getUnorderedGroup(), 0);
                    				

                    					selected = true;
                    				
                    // InternalHdbView.g:617:5: ( ( rule__View__Group_0__0 ) )
                    // InternalHdbView.g:618:6: ( rule__View__Group_0__0 )
                    {
                     before(grammarAccess.getViewAccess().getGroup_0()); 
                    // InternalHdbView.g:619:6: ( rule__View__Group_0__0 )
                    // InternalHdbView.g:619:7: rule__View__Group_0__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__View__Group_0__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getViewAccess().getGroup_0()); 

                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalHdbView.g:624:3: ({...}? => ( ( ( rule__View__Group_1__0 ) ) ) )
                    {
                    // InternalHdbView.g:624:3: ({...}? => ( ( ( rule__View__Group_1__0 ) ) ) )
                    // InternalHdbView.g:625:4: {...}? => ( ( ( rule__View__Group_1__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getViewAccess().getUnorderedGroup(), 1) ) {
                        throw new FailedPredicateException(input, "rule__View__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getViewAccess().getUnorderedGroup(), 1)");
                    }
                    // InternalHdbView.g:625:98: ( ( ( rule__View__Group_1__0 ) ) )
                    // InternalHdbView.g:626:5: ( ( rule__View__Group_1__0 ) )
                    {

                    					getUnorderedGroupHelper().select(grammarAccess.getViewAccess().getUnorderedGroup(), 1);
                    				

                    					selected = true;
                    				
                    // InternalHdbView.g:632:5: ( ( rule__View__Group_1__0 ) )
                    // InternalHdbView.g:633:6: ( rule__View__Group_1__0 )
                    {
                     before(grammarAccess.getViewAccess().getGroup_1()); 
                    // InternalHdbView.g:634:6: ( rule__View__Group_1__0 )
                    // InternalHdbView.g:634:7: rule__View__Group_1__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__View__Group_1__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getViewAccess().getGroup_1()); 

                    }


                    }


                    }


                    }
                    break;
                case 3 :
                    // InternalHdbView.g:639:3: ({...}? => ( ( ( rule__View__Group_2__0 ) ) ) )
                    {
                    // InternalHdbView.g:639:3: ({...}? => ( ( ( rule__View__Group_2__0 ) ) ) )
                    // InternalHdbView.g:640:4: {...}? => ( ( ( rule__View__Group_2__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getViewAccess().getUnorderedGroup(), 2) ) {
                        throw new FailedPredicateException(input, "rule__View__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getViewAccess().getUnorderedGroup(), 2)");
                    }
                    // InternalHdbView.g:640:98: ( ( ( rule__View__Group_2__0 ) ) )
                    // InternalHdbView.g:641:5: ( ( rule__View__Group_2__0 ) )
                    {

                    					getUnorderedGroupHelper().select(grammarAccess.getViewAccess().getUnorderedGroup(), 2);
                    				

                    					selected = true;
                    				
                    // InternalHdbView.g:647:5: ( ( rule__View__Group_2__0 ) )
                    // InternalHdbView.g:648:6: ( rule__View__Group_2__0 )
                    {
                     before(grammarAccess.getViewAccess().getGroup_2()); 
                    // InternalHdbView.g:649:6: ( rule__View__Group_2__0 )
                    // InternalHdbView.g:649:7: rule__View__Group_2__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__View__Group_2__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getViewAccess().getGroup_2()); 

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
            		getUnorderedGroupHelper().returnFromSelection(grammarAccess.getViewAccess().getUnorderedGroup());
            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__View__UnorderedGroup__Impl"


    // $ANTLR start "rule__View__UnorderedGroup__0"
    // InternalHdbView.g:662:1: rule__View__UnorderedGroup__0 : rule__View__UnorderedGroup__Impl ( rule__View__UnorderedGroup__1 )? ;
    public final void rule__View__UnorderedGroup__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbView.g:666:1: ( rule__View__UnorderedGroup__Impl ( rule__View__UnorderedGroup__1 )? )
            // InternalHdbView.g:667:2: rule__View__UnorderedGroup__Impl ( rule__View__UnorderedGroup__1 )?
            {
            pushFollow(FOLLOW_10);
            rule__View__UnorderedGroup__Impl();

            state._fsp--;

            // InternalHdbView.g:668:2: ( rule__View__UnorderedGroup__1 )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( LA4_0 == 17 && getUnorderedGroupHelper().canSelect(grammarAccess.getViewAccess().getUnorderedGroup(), 0) ) {
                alt4=1;
            }
            else if ( LA4_0 == 18 && getUnorderedGroupHelper().canSelect(grammarAccess.getViewAccess().getUnorderedGroup(), 1) ) {
                alt4=1;
            }
            else if ( LA4_0 == 19 && getUnorderedGroupHelper().canSelect(grammarAccess.getViewAccess().getUnorderedGroup(), 2) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // InternalHdbView.g:668:2: rule__View__UnorderedGroup__1
                    {
                    pushFollow(FOLLOW_2);
                    rule__View__UnorderedGroup__1();

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
    // $ANTLR end "rule__View__UnorderedGroup__0"


    // $ANTLR start "rule__View__UnorderedGroup__1"
    // InternalHdbView.g:674:1: rule__View__UnorderedGroup__1 : rule__View__UnorderedGroup__Impl ( rule__View__UnorderedGroup__2 )? ;
    public final void rule__View__UnorderedGroup__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbView.g:678:1: ( rule__View__UnorderedGroup__Impl ( rule__View__UnorderedGroup__2 )? )
            // InternalHdbView.g:679:2: rule__View__UnorderedGroup__Impl ( rule__View__UnorderedGroup__2 )?
            {
            pushFollow(FOLLOW_10);
            rule__View__UnorderedGroup__Impl();

            state._fsp--;

            // InternalHdbView.g:680:2: ( rule__View__UnorderedGroup__2 )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( LA5_0 == 17 && getUnorderedGroupHelper().canSelect(grammarAccess.getViewAccess().getUnorderedGroup(), 0) ) {
                alt5=1;
            }
            else if ( LA5_0 == 18 && getUnorderedGroupHelper().canSelect(grammarAccess.getViewAccess().getUnorderedGroup(), 1) ) {
                alt5=1;
            }
            else if ( LA5_0 == 19 && getUnorderedGroupHelper().canSelect(grammarAccess.getViewAccess().getUnorderedGroup(), 2) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // InternalHdbView.g:680:2: rule__View__UnorderedGroup__2
                    {
                    pushFollow(FOLLOW_2);
                    rule__View__UnorderedGroup__2();

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
    // $ANTLR end "rule__View__UnorderedGroup__1"


    // $ANTLR start "rule__View__UnorderedGroup__2"
    // InternalHdbView.g:686:1: rule__View__UnorderedGroup__2 : rule__View__UnorderedGroup__Impl ;
    public final void rule__View__UnorderedGroup__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbView.g:690:1: ( rule__View__UnorderedGroup__Impl )
            // InternalHdbView.g:691:2: rule__View__UnorderedGroup__Impl
            {
            pushFollow(FOLLOW_2);
            rule__View__UnorderedGroup__Impl();

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
    // $ANTLR end "rule__View__UnorderedGroup__2"


    // $ANTLR start "rule__HdbViewModel__ViewElementAssignment"
    // InternalHdbView.g:698:1: rule__HdbViewModel__ViewElementAssignment : ( ruleView ) ;
    public final void rule__HdbViewModel__ViewElementAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbView.g:702:1: ( ( ruleView ) )
            // InternalHdbView.g:703:2: ( ruleView )
            {
            // InternalHdbView.g:703:2: ( ruleView )
            // InternalHdbView.g:704:3: ruleView
            {
             before(grammarAccess.getHdbViewModelAccess().getViewElementViewParserRuleCall_0()); 
            pushFollow(FOLLOW_2);
            ruleView();

            state._fsp--;

             after(grammarAccess.getHdbViewModelAccess().getViewElementViewParserRuleCall_0()); 

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
    // $ANTLR end "rule__HdbViewModel__ViewElementAssignment"


    // $ANTLR start "rule__View__SchemaAssignment_0_0"
    // InternalHdbView.g:713:1: rule__View__SchemaAssignment_0_0 : ( ( 'schema' ) ) ;
    public final void rule__View__SchemaAssignment_0_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbView.g:717:1: ( ( ( 'schema' ) ) )
            // InternalHdbView.g:718:2: ( ( 'schema' ) )
            {
            // InternalHdbView.g:718:2: ( ( 'schema' ) )
            // InternalHdbView.g:719:3: ( 'schema' )
            {
             before(grammarAccess.getViewAccess().getSchemaSchemaKeyword_0_0_0()); 
            // InternalHdbView.g:720:3: ( 'schema' )
            // InternalHdbView.g:721:4: 'schema'
            {
             before(grammarAccess.getViewAccess().getSchemaSchemaKeyword_0_0_0()); 
            match(input,17,FOLLOW_2); 
             after(grammarAccess.getViewAccess().getSchemaSchemaKeyword_0_0_0()); 

            }

             after(grammarAccess.getViewAccess().getSchemaSchemaKeyword_0_0_0()); 

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
    // $ANTLR end "rule__View__SchemaAssignment_0_0"


    // $ANTLR start "rule__View__SchemaNameAssignment_0_2"
    // InternalHdbView.g:732:1: rule__View__SchemaNameAssignment_0_2 : ( RULE_STRING ) ;
    public final void rule__View__SchemaNameAssignment_0_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbView.g:736:1: ( ( RULE_STRING ) )
            // InternalHdbView.g:737:2: ( RULE_STRING )
            {
            // InternalHdbView.g:737:2: ( RULE_STRING )
            // InternalHdbView.g:738:3: RULE_STRING
            {
             before(grammarAccess.getViewAccess().getSchemaNameSTRINGTerminalRuleCall_0_2_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getViewAccess().getSchemaNameSTRINGTerminalRuleCall_0_2_0()); 

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
    // $ANTLR end "rule__View__SchemaNameAssignment_0_2"


    // $ANTLR start "rule__View__QueryAssignment_1_0"
    // InternalHdbView.g:747:1: rule__View__QueryAssignment_1_0 : ( ( 'query' ) ) ;
    public final void rule__View__QueryAssignment_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbView.g:751:1: ( ( ( 'query' ) ) )
            // InternalHdbView.g:752:2: ( ( 'query' ) )
            {
            // InternalHdbView.g:752:2: ( ( 'query' ) )
            // InternalHdbView.g:753:3: ( 'query' )
            {
             before(grammarAccess.getViewAccess().getQueryQueryKeyword_1_0_0()); 
            // InternalHdbView.g:754:3: ( 'query' )
            // InternalHdbView.g:755:4: 'query'
            {
             before(grammarAccess.getViewAccess().getQueryQueryKeyword_1_0_0()); 
            match(input,18,FOLLOW_2); 
             after(grammarAccess.getViewAccess().getQueryQueryKeyword_1_0_0()); 

            }

             after(grammarAccess.getViewAccess().getQueryQueryKeyword_1_0_0()); 

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
    // $ANTLR end "rule__View__QueryAssignment_1_0"


    // $ANTLR start "rule__View__QueryValueAssignment_1_2"
    // InternalHdbView.g:766:1: rule__View__QueryValueAssignment_1_2 : ( RULE_STRING ) ;
    public final void rule__View__QueryValueAssignment_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbView.g:770:1: ( ( RULE_STRING ) )
            // InternalHdbView.g:771:2: ( RULE_STRING )
            {
            // InternalHdbView.g:771:2: ( RULE_STRING )
            // InternalHdbView.g:772:3: RULE_STRING
            {
             before(grammarAccess.getViewAccess().getQueryValueSTRINGTerminalRuleCall_1_2_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getViewAccess().getQueryValueSTRINGTerminalRuleCall_1_2_0()); 

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
    // $ANTLR end "rule__View__QueryValueAssignment_1_2"


    // $ANTLR start "rule__View__DependsOnAssignment_2_0"
    // InternalHdbView.g:781:1: rule__View__DependsOnAssignment_2_0 : ( ( 'depends_on' ) ) ;
    public final void rule__View__DependsOnAssignment_2_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbView.g:785:1: ( ( ( 'depends_on' ) ) )
            // InternalHdbView.g:786:2: ( ( 'depends_on' ) )
            {
            // InternalHdbView.g:786:2: ( ( 'depends_on' ) )
            // InternalHdbView.g:787:3: ( 'depends_on' )
            {
             before(grammarAccess.getViewAccess().getDependsOnDepends_onKeyword_2_0_0()); 
            // InternalHdbView.g:788:3: ( 'depends_on' )
            // InternalHdbView.g:789:4: 'depends_on'
            {
             before(grammarAccess.getViewAccess().getDependsOnDepends_onKeyword_2_0_0()); 
            match(input,19,FOLLOW_2); 
             after(grammarAccess.getViewAccess().getDependsOnDepends_onKeyword_2_0_0()); 

            }

             after(grammarAccess.getViewAccess().getDependsOnDepends_onKeyword_2_0_0()); 

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
    // $ANTLR end "rule__View__DependsOnAssignment_2_0"


    // $ANTLR start "rule__View__DependsOnValuesAssignment_2_3_0"
    // InternalHdbView.g:800:1: rule__View__DependsOnValuesAssignment_2_3_0 : ( RULE_STRING ) ;
    public final void rule__View__DependsOnValuesAssignment_2_3_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbView.g:804:1: ( ( RULE_STRING ) )
            // InternalHdbView.g:805:2: ( RULE_STRING )
            {
            // InternalHdbView.g:805:2: ( RULE_STRING )
            // InternalHdbView.g:806:3: RULE_STRING
            {
             before(grammarAccess.getViewAccess().getDependsOnValuesSTRINGTerminalRuleCall_2_3_0_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getViewAccess().getDependsOnValuesSTRINGTerminalRuleCall_2_3_0_0()); 

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
    // $ANTLR end "rule__View__DependsOnValuesAssignment_2_3_0"


    // $ANTLR start "rule__View__DependsOnValuesAssignment_2_3_1_1"
    // InternalHdbView.g:815:1: rule__View__DependsOnValuesAssignment_2_3_1_1 : ( RULE_STRING ) ;
    public final void rule__View__DependsOnValuesAssignment_2_3_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalHdbView.g:819:1: ( ( RULE_STRING ) )
            // InternalHdbView.g:820:2: ( RULE_STRING )
            {
            // InternalHdbView.g:820:2: ( RULE_STRING )
            // InternalHdbView.g:821:3: RULE_STRING
            {
             before(grammarAccess.getViewAccess().getDependsOnValuesSTRINGTerminalRuleCall_2_3_1_1_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getViewAccess().getDependsOnValuesSTRINGTerminalRuleCall_2_3_1_1_0()); 

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
    // $ANTLR end "rule__View__DependsOnValuesAssignment_2_3_1_1"

    // Delegated rules


 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000008010L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x00000000000E0002L});

}
