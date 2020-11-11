package com.sap.xsk.models.hdbdd.ide.contentassist.antlr.internal;

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
import com.sap.xsk.models.hdbdd.services.ModelGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalModelParser extends AbstractInternalContentAssistParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_INT", "RULE_STRING", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'namespace'", "';'", "'.'", "'@Schema'", "':'", "'context'", "'{'", "'}'", "'type'", "'('", "')'", "'entity'", "'[*]'", "'to'", "'on'", "'='", "'key'"
    };
    public static final int RULE_STRING=6;
    public static final int RULE_SL_COMMENT=8;
    public static final int T__19=19;
    public static final int T__15=15;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__11=11;
    public static final int T__12=12;
    public static final int T__13=13;
    public static final int T__14=14;
    public static final int EOF=-1;
    public static final int RULE_ID=4;
    public static final int RULE_WS=9;
    public static final int RULE_ANY_OTHER=10;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int RULE_INT=5;
    public static final int T__22=22;
    public static final int RULE_ML_COMMENT=7;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__20=20;
    public static final int T__21=21;

    // delegates
    // delegators


        public InternalModelParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalModelParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalModelParser.tokenNames; }
    public String getGrammarFileName() { return "InternalModel.g"; }


    	private ModelGrammarAccess grammarAccess;

    	public void setGrammarAccess(ModelGrammarAccess grammarAccess) {
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



    // $ANTLR start "entryRuleHdbDD"
    // InternalModel.g:53:1: entryRuleHdbDD : ruleHdbDD EOF ;
    public final void entryRuleHdbDD() throws RecognitionException {
        try {
            // InternalModel.g:54:1: ( ruleHdbDD EOF )
            // InternalModel.g:55:1: ruleHdbDD EOF
            {
             before(grammarAccess.getHdbDDRule()); 
            pushFollow(FOLLOW_1);
            ruleHdbDD();

            state._fsp--;

             after(grammarAccess.getHdbDDRule()); 
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
    // $ANTLR end "entryRuleHdbDD"


    // $ANTLR start "ruleHdbDD"
    // InternalModel.g:62:1: ruleHdbDD : ( ( rule__HdbDD__ElementsAssignment )* ) ;
    public final void ruleHdbDD() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:66:2: ( ( ( rule__HdbDD__ElementsAssignment )* ) )
            // InternalModel.g:67:2: ( ( rule__HdbDD__ElementsAssignment )* )
            {
            // InternalModel.g:67:2: ( ( rule__HdbDD__ElementsAssignment )* )
            // InternalModel.g:68:3: ( rule__HdbDD__ElementsAssignment )*
            {
             before(grammarAccess.getHdbDDAccess().getElementsAssignment()); 
            // InternalModel.g:69:3: ( rule__HdbDD__ElementsAssignment )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==11||LA1_0==14||LA1_0==16) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // InternalModel.g:69:4: rule__HdbDD__ElementsAssignment
            	    {
            	    pushFollow(FOLLOW_3);
            	    rule__HdbDD__ElementsAssignment();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

             after(grammarAccess.getHdbDDAccess().getElementsAssignment()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleHdbDD"


    // $ANTLR start "entryRuleType"
    // InternalModel.g:78:1: entryRuleType : ruleType EOF ;
    public final void entryRuleType() throws RecognitionException {
        try {
            // InternalModel.g:79:1: ( ruleType EOF )
            // InternalModel.g:80:1: ruleType EOF
            {
             before(grammarAccess.getTypeRule()); 
            pushFollow(FOLLOW_1);
            ruleType();

            state._fsp--;

             after(grammarAccess.getTypeRule()); 
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
    // $ANTLR end "entryRuleType"


    // $ANTLR start "ruleType"
    // InternalModel.g:87:1: ruleType : ( ( rule__Type__Alternatives ) ) ;
    public final void ruleType() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:91:2: ( ( ( rule__Type__Alternatives ) ) )
            // InternalModel.g:92:2: ( ( rule__Type__Alternatives ) )
            {
            // InternalModel.g:92:2: ( ( rule__Type__Alternatives ) )
            // InternalModel.g:93:3: ( rule__Type__Alternatives )
            {
             before(grammarAccess.getTypeAccess().getAlternatives()); 
            // InternalModel.g:94:3: ( rule__Type__Alternatives )
            // InternalModel.g:94:4: rule__Type__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__Type__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getTypeAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleType"


    // $ANTLR start "entryRuleNamespace"
    // InternalModel.g:103:1: entryRuleNamespace : ruleNamespace EOF ;
    public final void entryRuleNamespace() throws RecognitionException {
        try {
            // InternalModel.g:104:1: ( ruleNamespace EOF )
            // InternalModel.g:105:1: ruleNamespace EOF
            {
             before(grammarAccess.getNamespaceRule()); 
            pushFollow(FOLLOW_1);
            ruleNamespace();

            state._fsp--;

             after(grammarAccess.getNamespaceRule()); 
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
    // $ANTLR end "entryRuleNamespace"


    // $ANTLR start "ruleNamespace"
    // InternalModel.g:112:1: ruleNamespace : ( ( rule__Namespace__Group__0 ) ) ;
    public final void ruleNamespace() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:116:2: ( ( ( rule__Namespace__Group__0 ) ) )
            // InternalModel.g:117:2: ( ( rule__Namespace__Group__0 ) )
            {
            // InternalModel.g:117:2: ( ( rule__Namespace__Group__0 ) )
            // InternalModel.g:118:3: ( rule__Namespace__Group__0 )
            {
             before(grammarAccess.getNamespaceAccess().getGroup()); 
            // InternalModel.g:119:3: ( rule__Namespace__Group__0 )
            // InternalModel.g:119:4: rule__Namespace__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Namespace__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getNamespaceAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleNamespace"


    // $ANTLR start "entryRuleQualifiedName"
    // InternalModel.g:128:1: entryRuleQualifiedName : ruleQualifiedName EOF ;
    public final void entryRuleQualifiedName() throws RecognitionException {
        try {
            // InternalModel.g:129:1: ( ruleQualifiedName EOF )
            // InternalModel.g:130:1: ruleQualifiedName EOF
            {
             before(grammarAccess.getQualifiedNameRule()); 
            pushFollow(FOLLOW_1);
            ruleQualifiedName();

            state._fsp--;

             after(grammarAccess.getQualifiedNameRule()); 
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
    // $ANTLR end "entryRuleQualifiedName"


    // $ANTLR start "ruleQualifiedName"
    // InternalModel.g:137:1: ruleQualifiedName : ( ( rule__QualifiedName__Group__0 ) ) ;
    public final void ruleQualifiedName() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:141:2: ( ( ( rule__QualifiedName__Group__0 ) ) )
            // InternalModel.g:142:2: ( ( rule__QualifiedName__Group__0 ) )
            {
            // InternalModel.g:142:2: ( ( rule__QualifiedName__Group__0 ) )
            // InternalModel.g:143:3: ( rule__QualifiedName__Group__0 )
            {
             before(grammarAccess.getQualifiedNameAccess().getGroup()); 
            // InternalModel.g:144:3: ( rule__QualifiedName__Group__0 )
            // InternalModel.g:144:4: rule__QualifiedName__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__QualifiedName__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getQualifiedNameAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleQualifiedName"


    // $ANTLR start "entryRuleSchema"
    // InternalModel.g:153:1: entryRuleSchema : ruleSchema EOF ;
    public final void entryRuleSchema() throws RecognitionException {
        try {
            // InternalModel.g:154:1: ( ruleSchema EOF )
            // InternalModel.g:155:1: ruleSchema EOF
            {
             before(grammarAccess.getSchemaRule()); 
            pushFollow(FOLLOW_1);
            ruleSchema();

            state._fsp--;

             after(grammarAccess.getSchemaRule()); 
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
    // $ANTLR end "entryRuleSchema"


    // $ANTLR start "ruleSchema"
    // InternalModel.g:162:1: ruleSchema : ( ( rule__Schema__Group__0 ) ) ;
    public final void ruleSchema() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:166:2: ( ( ( rule__Schema__Group__0 ) ) )
            // InternalModel.g:167:2: ( ( rule__Schema__Group__0 ) )
            {
            // InternalModel.g:167:2: ( ( rule__Schema__Group__0 ) )
            // InternalModel.g:168:3: ( rule__Schema__Group__0 )
            {
             before(grammarAccess.getSchemaAccess().getGroup()); 
            // InternalModel.g:169:3: ( rule__Schema__Group__0 )
            // InternalModel.g:169:4: rule__Schema__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Schema__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getSchemaAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleSchema"


    // $ANTLR start "entryRuleContext"
    // InternalModel.g:178:1: entryRuleContext : ruleContext EOF ;
    public final void entryRuleContext() throws RecognitionException {
        try {
            // InternalModel.g:179:1: ( ruleContext EOF )
            // InternalModel.g:180:1: ruleContext EOF
            {
             before(grammarAccess.getContextRule()); 
            pushFollow(FOLLOW_1);
            ruleContext();

            state._fsp--;

             after(grammarAccess.getContextRule()); 
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
    // $ANTLR end "entryRuleContext"


    // $ANTLR start "ruleContext"
    // InternalModel.g:187:1: ruleContext : ( ( rule__Context__Group__0 ) ) ;
    public final void ruleContext() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:191:2: ( ( ( rule__Context__Group__0 ) ) )
            // InternalModel.g:192:2: ( ( rule__Context__Group__0 ) )
            {
            // InternalModel.g:192:2: ( ( rule__Context__Group__0 ) )
            // InternalModel.g:193:3: ( rule__Context__Group__0 )
            {
             before(grammarAccess.getContextAccess().getGroup()); 
            // InternalModel.g:194:3: ( rule__Context__Group__0 )
            // InternalModel.g:194:4: rule__Context__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Context__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getContextAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleContext"


    // $ANTLR start "entryRuleTypeDefinition"
    // InternalModel.g:203:1: entryRuleTypeDefinition : ruleTypeDefinition EOF ;
    public final void entryRuleTypeDefinition() throws RecognitionException {
        try {
            // InternalModel.g:204:1: ( ruleTypeDefinition EOF )
            // InternalModel.g:205:1: ruleTypeDefinition EOF
            {
             before(grammarAccess.getTypeDefinitionRule()); 
            pushFollow(FOLLOW_1);
            ruleTypeDefinition();

            state._fsp--;

             after(grammarAccess.getTypeDefinitionRule()); 
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
    // $ANTLR end "entryRuleTypeDefinition"


    // $ANTLR start "ruleTypeDefinition"
    // InternalModel.g:212:1: ruleTypeDefinition : ( ( rule__TypeDefinition__Group__0 ) ) ;
    public final void ruleTypeDefinition() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:216:2: ( ( ( rule__TypeDefinition__Group__0 ) ) )
            // InternalModel.g:217:2: ( ( rule__TypeDefinition__Group__0 ) )
            {
            // InternalModel.g:217:2: ( ( rule__TypeDefinition__Group__0 ) )
            // InternalModel.g:218:3: ( rule__TypeDefinition__Group__0 )
            {
             before(grammarAccess.getTypeDefinitionAccess().getGroup()); 
            // InternalModel.g:219:3: ( rule__TypeDefinition__Group__0 )
            // InternalModel.g:219:4: rule__TypeDefinition__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__TypeDefinition__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getTypeDefinitionAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleTypeDefinition"


    // $ANTLR start "entryRuleEntity"
    // InternalModel.g:228:1: entryRuleEntity : ruleEntity EOF ;
    public final void entryRuleEntity() throws RecognitionException {
        try {
            // InternalModel.g:229:1: ( ruleEntity EOF )
            // InternalModel.g:230:1: ruleEntity EOF
            {
             before(grammarAccess.getEntityRule()); 
            pushFollow(FOLLOW_1);
            ruleEntity();

            state._fsp--;

             after(grammarAccess.getEntityRule()); 
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
    // $ANTLR end "entryRuleEntity"


    // $ANTLR start "ruleEntity"
    // InternalModel.g:237:1: ruleEntity : ( ( rule__Entity__Group__0 ) ) ;
    public final void ruleEntity() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:241:2: ( ( ( rule__Entity__Group__0 ) ) )
            // InternalModel.g:242:2: ( ( rule__Entity__Group__0 ) )
            {
            // InternalModel.g:242:2: ( ( rule__Entity__Group__0 ) )
            // InternalModel.g:243:3: ( rule__Entity__Group__0 )
            {
             before(grammarAccess.getEntityAccess().getGroup()); 
            // InternalModel.g:244:3: ( rule__Entity__Group__0 )
            // InternalModel.g:244:4: rule__Entity__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Entity__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getEntityAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleEntity"


    // $ANTLR start "entryRuleField"
    // InternalModel.g:253:1: entryRuleField : ruleField EOF ;
    public final void entryRuleField() throws RecognitionException {
        try {
            // InternalModel.g:254:1: ( ruleField EOF )
            // InternalModel.g:255:1: ruleField EOF
            {
             before(grammarAccess.getFieldRule()); 
            pushFollow(FOLLOW_1);
            ruleField();

            state._fsp--;

             after(grammarAccess.getFieldRule()); 
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
    // $ANTLR end "entryRuleField"


    // $ANTLR start "ruleField"
    // InternalModel.g:262:1: ruleField : ( ( rule__Field__Alternatives ) ) ;
    public final void ruleField() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:266:2: ( ( ( rule__Field__Alternatives ) ) )
            // InternalModel.g:267:2: ( ( rule__Field__Alternatives ) )
            {
            // InternalModel.g:267:2: ( ( rule__Field__Alternatives ) )
            // InternalModel.g:268:3: ( rule__Field__Alternatives )
            {
             before(grammarAccess.getFieldAccess().getAlternatives()); 
            // InternalModel.g:269:3: ( rule__Field__Alternatives )
            // InternalModel.g:269:4: rule__Field__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__Field__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getFieldAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleField"


    // $ANTLR start "entryRuleFieldPrimitive"
    // InternalModel.g:278:1: entryRuleFieldPrimitive : ruleFieldPrimitive EOF ;
    public final void entryRuleFieldPrimitive() throws RecognitionException {
        try {
            // InternalModel.g:279:1: ( ruleFieldPrimitive EOF )
            // InternalModel.g:280:1: ruleFieldPrimitive EOF
            {
             before(grammarAccess.getFieldPrimitiveRule()); 
            pushFollow(FOLLOW_1);
            ruleFieldPrimitive();

            state._fsp--;

             after(grammarAccess.getFieldPrimitiveRule()); 
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
    // $ANTLR end "entryRuleFieldPrimitive"


    // $ANTLR start "ruleFieldPrimitive"
    // InternalModel.g:287:1: ruleFieldPrimitive : ( ( rule__FieldPrimitive__Group__0 ) ) ;
    public final void ruleFieldPrimitive() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:291:2: ( ( ( rule__FieldPrimitive__Group__0 ) ) )
            // InternalModel.g:292:2: ( ( rule__FieldPrimitive__Group__0 ) )
            {
            // InternalModel.g:292:2: ( ( rule__FieldPrimitive__Group__0 ) )
            // InternalModel.g:293:3: ( rule__FieldPrimitive__Group__0 )
            {
             before(grammarAccess.getFieldPrimitiveAccess().getGroup()); 
            // InternalModel.g:294:3: ( rule__FieldPrimitive__Group__0 )
            // InternalModel.g:294:4: rule__FieldPrimitive__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__FieldPrimitive__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getFieldPrimitiveAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleFieldPrimitive"


    // $ANTLR start "entryRuleFieldType"
    // InternalModel.g:303:1: entryRuleFieldType : ruleFieldType EOF ;
    public final void entryRuleFieldType() throws RecognitionException {
        try {
            // InternalModel.g:304:1: ( ruleFieldType EOF )
            // InternalModel.g:305:1: ruleFieldType EOF
            {
             before(grammarAccess.getFieldTypeRule()); 
            pushFollow(FOLLOW_1);
            ruleFieldType();

            state._fsp--;

             after(grammarAccess.getFieldTypeRule()); 
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
    // $ANTLR end "entryRuleFieldType"


    // $ANTLR start "ruleFieldType"
    // InternalModel.g:312:1: ruleFieldType : ( ( rule__FieldType__Group__0 ) ) ;
    public final void ruleFieldType() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:316:2: ( ( ( rule__FieldType__Group__0 ) ) )
            // InternalModel.g:317:2: ( ( rule__FieldType__Group__0 ) )
            {
            // InternalModel.g:317:2: ( ( rule__FieldType__Group__0 ) )
            // InternalModel.g:318:3: ( rule__FieldType__Group__0 )
            {
             before(grammarAccess.getFieldTypeAccess().getGroup()); 
            // InternalModel.g:319:3: ( rule__FieldType__Group__0 )
            // InternalModel.g:319:4: rule__FieldType__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__FieldType__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getFieldTypeAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleFieldType"


    // $ANTLR start "entryRuleFieldReference"
    // InternalModel.g:328:1: entryRuleFieldReference : ruleFieldReference EOF ;
    public final void entryRuleFieldReference() throws RecognitionException {
        try {
            // InternalModel.g:329:1: ( ruleFieldReference EOF )
            // InternalModel.g:330:1: ruleFieldReference EOF
            {
             before(grammarAccess.getFieldReferenceRule()); 
            pushFollow(FOLLOW_1);
            ruleFieldReference();

            state._fsp--;

             after(grammarAccess.getFieldReferenceRule()); 
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
    // $ANTLR end "entryRuleFieldReference"


    // $ANTLR start "ruleFieldReference"
    // InternalModel.g:337:1: ruleFieldReference : ( ( rule__FieldReference__Group__0 ) ) ;
    public final void ruleFieldReference() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:341:2: ( ( ( rule__FieldReference__Group__0 ) ) )
            // InternalModel.g:342:2: ( ( rule__FieldReference__Group__0 ) )
            {
            // InternalModel.g:342:2: ( ( rule__FieldReference__Group__0 ) )
            // InternalModel.g:343:3: ( rule__FieldReference__Group__0 )
            {
             before(grammarAccess.getFieldReferenceAccess().getGroup()); 
            // InternalModel.g:344:3: ( rule__FieldReference__Group__0 )
            // InternalModel.g:344:4: rule__FieldReference__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__FieldReference__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getFieldReferenceAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleFieldReference"


    // $ANTLR start "rule__Type__Alternatives"
    // InternalModel.g:352:1: rule__Type__Alternatives : ( ( ruleNamespace ) | ( ruleSchema ) | ( ruleContext ) );
    public final void rule__Type__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:356:1: ( ( ruleNamespace ) | ( ruleSchema ) | ( ruleContext ) )
            int alt2=3;
            switch ( input.LA(1) ) {
            case 11:
                {
                alt2=1;
                }
                break;
            case 14:
                {
                alt2=2;
                }
                break;
            case 16:
                {
                alt2=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }

            switch (alt2) {
                case 1 :
                    // InternalModel.g:357:2: ( ruleNamespace )
                    {
                    // InternalModel.g:357:2: ( ruleNamespace )
                    // InternalModel.g:358:3: ruleNamespace
                    {
                     before(grammarAccess.getTypeAccess().getNamespaceParserRuleCall_0()); 
                    pushFollow(FOLLOW_2);
                    ruleNamespace();

                    state._fsp--;

                     after(grammarAccess.getTypeAccess().getNamespaceParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalModel.g:363:2: ( ruleSchema )
                    {
                    // InternalModel.g:363:2: ( ruleSchema )
                    // InternalModel.g:364:3: ruleSchema
                    {
                     before(grammarAccess.getTypeAccess().getSchemaParserRuleCall_1()); 
                    pushFollow(FOLLOW_2);
                    ruleSchema();

                    state._fsp--;

                     after(grammarAccess.getTypeAccess().getSchemaParserRuleCall_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalModel.g:369:2: ( ruleContext )
                    {
                    // InternalModel.g:369:2: ( ruleContext )
                    // InternalModel.g:370:3: ruleContext
                    {
                     before(grammarAccess.getTypeAccess().getContextParserRuleCall_2()); 
                    pushFollow(FOLLOW_2);
                    ruleContext();

                    state._fsp--;

                     after(grammarAccess.getTypeAccess().getContextParserRuleCall_2()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Type__Alternatives"


    // $ANTLR start "rule__Field__Alternatives"
    // InternalModel.g:379:1: rule__Field__Alternatives : ( ( ruleFieldPrimitive ) | ( ruleFieldReference ) | ( ruleFieldType ) );
    public final void rule__Field__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:383:1: ( ( ruleFieldPrimitive ) | ( ruleFieldReference ) | ( ruleFieldType ) )
            int alt3=3;
            alt3 = dfa3.predict(input);
            switch (alt3) {
                case 1 :
                    // InternalModel.g:384:2: ( ruleFieldPrimitive )
                    {
                    // InternalModel.g:384:2: ( ruleFieldPrimitive )
                    // InternalModel.g:385:3: ruleFieldPrimitive
                    {
                     before(grammarAccess.getFieldAccess().getFieldPrimitiveParserRuleCall_0()); 
                    pushFollow(FOLLOW_2);
                    ruleFieldPrimitive();

                    state._fsp--;

                     after(grammarAccess.getFieldAccess().getFieldPrimitiveParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalModel.g:390:2: ( ruleFieldReference )
                    {
                    // InternalModel.g:390:2: ( ruleFieldReference )
                    // InternalModel.g:391:3: ruleFieldReference
                    {
                     before(grammarAccess.getFieldAccess().getFieldReferenceParserRuleCall_1()); 
                    pushFollow(FOLLOW_2);
                    ruleFieldReference();

                    state._fsp--;

                     after(grammarAccess.getFieldAccess().getFieldReferenceParserRuleCall_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalModel.g:396:2: ( ruleFieldType )
                    {
                    // InternalModel.g:396:2: ( ruleFieldType )
                    // InternalModel.g:397:3: ruleFieldType
                    {
                     before(grammarAccess.getFieldAccess().getFieldTypeParserRuleCall_2()); 
                    pushFollow(FOLLOW_2);
                    ruleFieldType();

                    state._fsp--;

                     after(grammarAccess.getFieldAccess().getFieldTypeParserRuleCall_2()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Field__Alternatives"


    // $ANTLR start "rule__Namespace__Group__0"
    // InternalModel.g:406:1: rule__Namespace__Group__0 : rule__Namespace__Group__0__Impl rule__Namespace__Group__1 ;
    public final void rule__Namespace__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:410:1: ( rule__Namespace__Group__0__Impl rule__Namespace__Group__1 )
            // InternalModel.g:411:2: rule__Namespace__Group__0__Impl rule__Namespace__Group__1
            {
            pushFollow(FOLLOW_4);
            rule__Namespace__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Namespace__Group__1();

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
    // $ANTLR end "rule__Namespace__Group__0"


    // $ANTLR start "rule__Namespace__Group__0__Impl"
    // InternalModel.g:418:1: rule__Namespace__Group__0__Impl : ( 'namespace' ) ;
    public final void rule__Namespace__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:422:1: ( ( 'namespace' ) )
            // InternalModel.g:423:1: ( 'namespace' )
            {
            // InternalModel.g:423:1: ( 'namespace' )
            // InternalModel.g:424:2: 'namespace'
            {
             before(grammarAccess.getNamespaceAccess().getNamespaceKeyword_0()); 
            match(input,11,FOLLOW_2); 
             after(grammarAccess.getNamespaceAccess().getNamespaceKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Namespace__Group__0__Impl"


    // $ANTLR start "rule__Namespace__Group__1"
    // InternalModel.g:433:1: rule__Namespace__Group__1 : rule__Namespace__Group__1__Impl rule__Namespace__Group__2 ;
    public final void rule__Namespace__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:437:1: ( rule__Namespace__Group__1__Impl rule__Namespace__Group__2 )
            // InternalModel.g:438:2: rule__Namespace__Group__1__Impl rule__Namespace__Group__2
            {
            pushFollow(FOLLOW_5);
            rule__Namespace__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Namespace__Group__2();

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
    // $ANTLR end "rule__Namespace__Group__1"


    // $ANTLR start "rule__Namespace__Group__1__Impl"
    // InternalModel.g:445:1: rule__Namespace__Group__1__Impl : ( ( rule__Namespace__NameAssignment_1 ) ) ;
    public final void rule__Namespace__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:449:1: ( ( ( rule__Namespace__NameAssignment_1 ) ) )
            // InternalModel.g:450:1: ( ( rule__Namespace__NameAssignment_1 ) )
            {
            // InternalModel.g:450:1: ( ( rule__Namespace__NameAssignment_1 ) )
            // InternalModel.g:451:2: ( rule__Namespace__NameAssignment_1 )
            {
             before(grammarAccess.getNamespaceAccess().getNameAssignment_1()); 
            // InternalModel.g:452:2: ( rule__Namespace__NameAssignment_1 )
            // InternalModel.g:452:3: rule__Namespace__NameAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__Namespace__NameAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getNamespaceAccess().getNameAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Namespace__Group__1__Impl"


    // $ANTLR start "rule__Namespace__Group__2"
    // InternalModel.g:460:1: rule__Namespace__Group__2 : rule__Namespace__Group__2__Impl ;
    public final void rule__Namespace__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:464:1: ( rule__Namespace__Group__2__Impl )
            // InternalModel.g:465:2: rule__Namespace__Group__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Namespace__Group__2__Impl();

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
    // $ANTLR end "rule__Namespace__Group__2"


    // $ANTLR start "rule__Namespace__Group__2__Impl"
    // InternalModel.g:471:1: rule__Namespace__Group__2__Impl : ( ';' ) ;
    public final void rule__Namespace__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:475:1: ( ( ';' ) )
            // InternalModel.g:476:1: ( ';' )
            {
            // InternalModel.g:476:1: ( ';' )
            // InternalModel.g:477:2: ';'
            {
             before(grammarAccess.getNamespaceAccess().getSemicolonKeyword_2()); 
            match(input,12,FOLLOW_2); 
             after(grammarAccess.getNamespaceAccess().getSemicolonKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Namespace__Group__2__Impl"


    // $ANTLR start "rule__QualifiedName__Group__0"
    // InternalModel.g:487:1: rule__QualifiedName__Group__0 : rule__QualifiedName__Group__0__Impl rule__QualifiedName__Group__1 ;
    public final void rule__QualifiedName__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:491:1: ( rule__QualifiedName__Group__0__Impl rule__QualifiedName__Group__1 )
            // InternalModel.g:492:2: rule__QualifiedName__Group__0__Impl rule__QualifiedName__Group__1
            {
            pushFollow(FOLLOW_6);
            rule__QualifiedName__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__QualifiedName__Group__1();

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
    // $ANTLR end "rule__QualifiedName__Group__0"


    // $ANTLR start "rule__QualifiedName__Group__0__Impl"
    // InternalModel.g:499:1: rule__QualifiedName__Group__0__Impl : ( RULE_ID ) ;
    public final void rule__QualifiedName__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:503:1: ( ( RULE_ID ) )
            // InternalModel.g:504:1: ( RULE_ID )
            {
            // InternalModel.g:504:1: ( RULE_ID )
            // InternalModel.g:505:2: RULE_ID
            {
             before(grammarAccess.getQualifiedNameAccess().getIDTerminalRuleCall_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getQualifiedNameAccess().getIDTerminalRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__QualifiedName__Group__0__Impl"


    // $ANTLR start "rule__QualifiedName__Group__1"
    // InternalModel.g:514:1: rule__QualifiedName__Group__1 : rule__QualifiedName__Group__1__Impl ;
    public final void rule__QualifiedName__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:518:1: ( rule__QualifiedName__Group__1__Impl )
            // InternalModel.g:519:2: rule__QualifiedName__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__QualifiedName__Group__1__Impl();

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
    // $ANTLR end "rule__QualifiedName__Group__1"


    // $ANTLR start "rule__QualifiedName__Group__1__Impl"
    // InternalModel.g:525:1: rule__QualifiedName__Group__1__Impl : ( ( rule__QualifiedName__Group_1__0 )* ) ;
    public final void rule__QualifiedName__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:529:1: ( ( ( rule__QualifiedName__Group_1__0 )* ) )
            // InternalModel.g:530:1: ( ( rule__QualifiedName__Group_1__0 )* )
            {
            // InternalModel.g:530:1: ( ( rule__QualifiedName__Group_1__0 )* )
            // InternalModel.g:531:2: ( rule__QualifiedName__Group_1__0 )*
            {
             before(grammarAccess.getQualifiedNameAccess().getGroup_1()); 
            // InternalModel.g:532:2: ( rule__QualifiedName__Group_1__0 )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==13) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // InternalModel.g:532:3: rule__QualifiedName__Group_1__0
            	    {
            	    pushFollow(FOLLOW_7);
            	    rule__QualifiedName__Group_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);

             after(grammarAccess.getQualifiedNameAccess().getGroup_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__QualifiedName__Group__1__Impl"


    // $ANTLR start "rule__QualifiedName__Group_1__0"
    // InternalModel.g:541:1: rule__QualifiedName__Group_1__0 : rule__QualifiedName__Group_1__0__Impl rule__QualifiedName__Group_1__1 ;
    public final void rule__QualifiedName__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:545:1: ( rule__QualifiedName__Group_1__0__Impl rule__QualifiedName__Group_1__1 )
            // InternalModel.g:546:2: rule__QualifiedName__Group_1__0__Impl rule__QualifiedName__Group_1__1
            {
            pushFollow(FOLLOW_4);
            rule__QualifiedName__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__QualifiedName__Group_1__1();

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
    // $ANTLR end "rule__QualifiedName__Group_1__0"


    // $ANTLR start "rule__QualifiedName__Group_1__0__Impl"
    // InternalModel.g:553:1: rule__QualifiedName__Group_1__0__Impl : ( '.' ) ;
    public final void rule__QualifiedName__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:557:1: ( ( '.' ) )
            // InternalModel.g:558:1: ( '.' )
            {
            // InternalModel.g:558:1: ( '.' )
            // InternalModel.g:559:2: '.'
            {
             before(grammarAccess.getQualifiedNameAccess().getFullStopKeyword_1_0()); 
            match(input,13,FOLLOW_2); 
             after(grammarAccess.getQualifiedNameAccess().getFullStopKeyword_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__QualifiedName__Group_1__0__Impl"


    // $ANTLR start "rule__QualifiedName__Group_1__1"
    // InternalModel.g:568:1: rule__QualifiedName__Group_1__1 : rule__QualifiedName__Group_1__1__Impl ;
    public final void rule__QualifiedName__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:572:1: ( rule__QualifiedName__Group_1__1__Impl )
            // InternalModel.g:573:2: rule__QualifiedName__Group_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__QualifiedName__Group_1__1__Impl();

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
    // $ANTLR end "rule__QualifiedName__Group_1__1"


    // $ANTLR start "rule__QualifiedName__Group_1__1__Impl"
    // InternalModel.g:579:1: rule__QualifiedName__Group_1__1__Impl : ( RULE_ID ) ;
    public final void rule__QualifiedName__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:583:1: ( ( RULE_ID ) )
            // InternalModel.g:584:1: ( RULE_ID )
            {
            // InternalModel.g:584:1: ( RULE_ID )
            // InternalModel.g:585:2: RULE_ID
            {
             before(grammarAccess.getQualifiedNameAccess().getIDTerminalRuleCall_1_1()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getQualifiedNameAccess().getIDTerminalRuleCall_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__QualifiedName__Group_1__1__Impl"


    // $ANTLR start "rule__Schema__Group__0"
    // InternalModel.g:595:1: rule__Schema__Group__0 : rule__Schema__Group__0__Impl rule__Schema__Group__1 ;
    public final void rule__Schema__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:599:1: ( rule__Schema__Group__0__Impl rule__Schema__Group__1 )
            // InternalModel.g:600:2: rule__Schema__Group__0__Impl rule__Schema__Group__1
            {
            pushFollow(FOLLOW_8);
            rule__Schema__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Schema__Group__1();

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
    // $ANTLR end "rule__Schema__Group__0"


    // $ANTLR start "rule__Schema__Group__0__Impl"
    // InternalModel.g:607:1: rule__Schema__Group__0__Impl : ( '@Schema' ) ;
    public final void rule__Schema__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:611:1: ( ( '@Schema' ) )
            // InternalModel.g:612:1: ( '@Schema' )
            {
            // InternalModel.g:612:1: ( '@Schema' )
            // InternalModel.g:613:2: '@Schema'
            {
             before(grammarAccess.getSchemaAccess().getSchemaKeyword_0()); 
            match(input,14,FOLLOW_2); 
             after(grammarAccess.getSchemaAccess().getSchemaKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Schema__Group__0__Impl"


    // $ANTLR start "rule__Schema__Group__1"
    // InternalModel.g:622:1: rule__Schema__Group__1 : rule__Schema__Group__1__Impl rule__Schema__Group__2 ;
    public final void rule__Schema__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:626:1: ( rule__Schema__Group__1__Impl rule__Schema__Group__2 )
            // InternalModel.g:627:2: rule__Schema__Group__1__Impl rule__Schema__Group__2
            {
            pushFollow(FOLLOW_4);
            rule__Schema__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Schema__Group__2();

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
    // $ANTLR end "rule__Schema__Group__1"


    // $ANTLR start "rule__Schema__Group__1__Impl"
    // InternalModel.g:634:1: rule__Schema__Group__1__Impl : ( ':' ) ;
    public final void rule__Schema__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:638:1: ( ( ':' ) )
            // InternalModel.g:639:1: ( ':' )
            {
            // InternalModel.g:639:1: ( ':' )
            // InternalModel.g:640:2: ':'
            {
             before(grammarAccess.getSchemaAccess().getColonKeyword_1()); 
            match(input,15,FOLLOW_2); 
             after(grammarAccess.getSchemaAccess().getColonKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Schema__Group__1__Impl"


    // $ANTLR start "rule__Schema__Group__2"
    // InternalModel.g:649:1: rule__Schema__Group__2 : rule__Schema__Group__2__Impl ;
    public final void rule__Schema__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:653:1: ( rule__Schema__Group__2__Impl )
            // InternalModel.g:654:2: rule__Schema__Group__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Schema__Group__2__Impl();

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
    // $ANTLR end "rule__Schema__Group__2"


    // $ANTLR start "rule__Schema__Group__2__Impl"
    // InternalModel.g:660:1: rule__Schema__Group__2__Impl : ( ( rule__Schema__NameAssignment_2 ) ) ;
    public final void rule__Schema__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:664:1: ( ( ( rule__Schema__NameAssignment_2 ) ) )
            // InternalModel.g:665:1: ( ( rule__Schema__NameAssignment_2 ) )
            {
            // InternalModel.g:665:1: ( ( rule__Schema__NameAssignment_2 ) )
            // InternalModel.g:666:2: ( rule__Schema__NameAssignment_2 )
            {
             before(grammarAccess.getSchemaAccess().getNameAssignment_2()); 
            // InternalModel.g:667:2: ( rule__Schema__NameAssignment_2 )
            // InternalModel.g:667:3: rule__Schema__NameAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__Schema__NameAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getSchemaAccess().getNameAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Schema__Group__2__Impl"


    // $ANTLR start "rule__Context__Group__0"
    // InternalModel.g:676:1: rule__Context__Group__0 : rule__Context__Group__0__Impl rule__Context__Group__1 ;
    public final void rule__Context__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:680:1: ( rule__Context__Group__0__Impl rule__Context__Group__1 )
            // InternalModel.g:681:2: rule__Context__Group__0__Impl rule__Context__Group__1
            {
            pushFollow(FOLLOW_4);
            rule__Context__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Context__Group__1();

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
    // $ANTLR end "rule__Context__Group__0"


    // $ANTLR start "rule__Context__Group__0__Impl"
    // InternalModel.g:688:1: rule__Context__Group__0__Impl : ( 'context' ) ;
    public final void rule__Context__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:692:1: ( ( 'context' ) )
            // InternalModel.g:693:1: ( 'context' )
            {
            // InternalModel.g:693:1: ( 'context' )
            // InternalModel.g:694:2: 'context'
            {
             before(grammarAccess.getContextAccess().getContextKeyword_0()); 
            match(input,16,FOLLOW_2); 
             after(grammarAccess.getContextAccess().getContextKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Context__Group__0__Impl"


    // $ANTLR start "rule__Context__Group__1"
    // InternalModel.g:703:1: rule__Context__Group__1 : rule__Context__Group__1__Impl rule__Context__Group__2 ;
    public final void rule__Context__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:707:1: ( rule__Context__Group__1__Impl rule__Context__Group__2 )
            // InternalModel.g:708:2: rule__Context__Group__1__Impl rule__Context__Group__2
            {
            pushFollow(FOLLOW_9);
            rule__Context__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Context__Group__2();

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
    // $ANTLR end "rule__Context__Group__1"


    // $ANTLR start "rule__Context__Group__1__Impl"
    // InternalModel.g:715:1: rule__Context__Group__1__Impl : ( ( rule__Context__NameAssignment_1 ) ) ;
    public final void rule__Context__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:719:1: ( ( ( rule__Context__NameAssignment_1 ) ) )
            // InternalModel.g:720:1: ( ( rule__Context__NameAssignment_1 ) )
            {
            // InternalModel.g:720:1: ( ( rule__Context__NameAssignment_1 ) )
            // InternalModel.g:721:2: ( rule__Context__NameAssignment_1 )
            {
             before(grammarAccess.getContextAccess().getNameAssignment_1()); 
            // InternalModel.g:722:2: ( rule__Context__NameAssignment_1 )
            // InternalModel.g:722:3: rule__Context__NameAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__Context__NameAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getContextAccess().getNameAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Context__Group__1__Impl"


    // $ANTLR start "rule__Context__Group__2"
    // InternalModel.g:730:1: rule__Context__Group__2 : rule__Context__Group__2__Impl rule__Context__Group__3 ;
    public final void rule__Context__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:734:1: ( rule__Context__Group__2__Impl rule__Context__Group__3 )
            // InternalModel.g:735:2: rule__Context__Group__2__Impl rule__Context__Group__3
            {
            pushFollow(FOLLOW_10);
            rule__Context__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Context__Group__3();

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
    // $ANTLR end "rule__Context__Group__2"


    // $ANTLR start "rule__Context__Group__2__Impl"
    // InternalModel.g:742:1: rule__Context__Group__2__Impl : ( '{' ) ;
    public final void rule__Context__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:746:1: ( ( '{' ) )
            // InternalModel.g:747:1: ( '{' )
            {
            // InternalModel.g:747:1: ( '{' )
            // InternalModel.g:748:2: '{'
            {
             before(grammarAccess.getContextAccess().getLeftCurlyBracketKeyword_2()); 
            match(input,17,FOLLOW_2); 
             after(grammarAccess.getContextAccess().getLeftCurlyBracketKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Context__Group__2__Impl"


    // $ANTLR start "rule__Context__Group__3"
    // InternalModel.g:757:1: rule__Context__Group__3 : rule__Context__Group__3__Impl rule__Context__Group__4 ;
    public final void rule__Context__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:761:1: ( rule__Context__Group__3__Impl rule__Context__Group__4 )
            // InternalModel.g:762:2: rule__Context__Group__3__Impl rule__Context__Group__4
            {
            pushFollow(FOLLOW_10);
            rule__Context__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Context__Group__4();

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
    // $ANTLR end "rule__Context__Group__3"


    // $ANTLR start "rule__Context__Group__3__Impl"
    // InternalModel.g:769:1: rule__Context__Group__3__Impl : ( ( rule__Context__TypesAssignment_3 )* ) ;
    public final void rule__Context__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:773:1: ( ( ( rule__Context__TypesAssignment_3 )* ) )
            // InternalModel.g:774:1: ( ( rule__Context__TypesAssignment_3 )* )
            {
            // InternalModel.g:774:1: ( ( rule__Context__TypesAssignment_3 )* )
            // InternalModel.g:775:2: ( rule__Context__TypesAssignment_3 )*
            {
             before(grammarAccess.getContextAccess().getTypesAssignment_3()); 
            // InternalModel.g:776:2: ( rule__Context__TypesAssignment_3 )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==19) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // InternalModel.g:776:3: rule__Context__TypesAssignment_3
            	    {
            	    pushFollow(FOLLOW_11);
            	    rule__Context__TypesAssignment_3();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);

             after(grammarAccess.getContextAccess().getTypesAssignment_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Context__Group__3__Impl"


    // $ANTLR start "rule__Context__Group__4"
    // InternalModel.g:784:1: rule__Context__Group__4 : rule__Context__Group__4__Impl rule__Context__Group__5 ;
    public final void rule__Context__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:788:1: ( rule__Context__Group__4__Impl rule__Context__Group__5 )
            // InternalModel.g:789:2: rule__Context__Group__4__Impl rule__Context__Group__5
            {
            pushFollow(FOLLOW_10);
            rule__Context__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Context__Group__5();

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
    // $ANTLR end "rule__Context__Group__4"


    // $ANTLR start "rule__Context__Group__4__Impl"
    // InternalModel.g:796:1: rule__Context__Group__4__Impl : ( ( rule__Context__EntitiesAssignment_4 )* ) ;
    public final void rule__Context__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:800:1: ( ( ( rule__Context__EntitiesAssignment_4 )* ) )
            // InternalModel.g:801:1: ( ( rule__Context__EntitiesAssignment_4 )* )
            {
            // InternalModel.g:801:1: ( ( rule__Context__EntitiesAssignment_4 )* )
            // InternalModel.g:802:2: ( rule__Context__EntitiesAssignment_4 )*
            {
             before(grammarAccess.getContextAccess().getEntitiesAssignment_4()); 
            // InternalModel.g:803:2: ( rule__Context__EntitiesAssignment_4 )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==22) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // InternalModel.g:803:3: rule__Context__EntitiesAssignment_4
            	    {
            	    pushFollow(FOLLOW_12);
            	    rule__Context__EntitiesAssignment_4();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);

             after(grammarAccess.getContextAccess().getEntitiesAssignment_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Context__Group__4__Impl"


    // $ANTLR start "rule__Context__Group__5"
    // InternalModel.g:811:1: rule__Context__Group__5 : rule__Context__Group__5__Impl rule__Context__Group__6 ;
    public final void rule__Context__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:815:1: ( rule__Context__Group__5__Impl rule__Context__Group__6 )
            // InternalModel.g:816:2: rule__Context__Group__5__Impl rule__Context__Group__6
            {
            pushFollow(FOLLOW_5);
            rule__Context__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Context__Group__6();

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
    // $ANTLR end "rule__Context__Group__5"


    // $ANTLR start "rule__Context__Group__5__Impl"
    // InternalModel.g:823:1: rule__Context__Group__5__Impl : ( '}' ) ;
    public final void rule__Context__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:827:1: ( ( '}' ) )
            // InternalModel.g:828:1: ( '}' )
            {
            // InternalModel.g:828:1: ( '}' )
            // InternalModel.g:829:2: '}'
            {
             before(grammarAccess.getContextAccess().getRightCurlyBracketKeyword_5()); 
            match(input,18,FOLLOW_2); 
             after(grammarAccess.getContextAccess().getRightCurlyBracketKeyword_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Context__Group__5__Impl"


    // $ANTLR start "rule__Context__Group__6"
    // InternalModel.g:838:1: rule__Context__Group__6 : rule__Context__Group__6__Impl ;
    public final void rule__Context__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:842:1: ( rule__Context__Group__6__Impl )
            // InternalModel.g:843:2: rule__Context__Group__6__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Context__Group__6__Impl();

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
    // $ANTLR end "rule__Context__Group__6"


    // $ANTLR start "rule__Context__Group__6__Impl"
    // InternalModel.g:849:1: rule__Context__Group__6__Impl : ( ';' ) ;
    public final void rule__Context__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:853:1: ( ( ';' ) )
            // InternalModel.g:854:1: ( ';' )
            {
            // InternalModel.g:854:1: ( ';' )
            // InternalModel.g:855:2: ';'
            {
             before(grammarAccess.getContextAccess().getSemicolonKeyword_6()); 
            match(input,12,FOLLOW_2); 
             after(grammarAccess.getContextAccess().getSemicolonKeyword_6()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Context__Group__6__Impl"


    // $ANTLR start "rule__TypeDefinition__Group__0"
    // InternalModel.g:865:1: rule__TypeDefinition__Group__0 : rule__TypeDefinition__Group__0__Impl rule__TypeDefinition__Group__1 ;
    public final void rule__TypeDefinition__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:869:1: ( rule__TypeDefinition__Group__0__Impl rule__TypeDefinition__Group__1 )
            // InternalModel.g:870:2: rule__TypeDefinition__Group__0__Impl rule__TypeDefinition__Group__1
            {
            pushFollow(FOLLOW_4);
            rule__TypeDefinition__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__TypeDefinition__Group__1();

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
    // $ANTLR end "rule__TypeDefinition__Group__0"


    // $ANTLR start "rule__TypeDefinition__Group__0__Impl"
    // InternalModel.g:877:1: rule__TypeDefinition__Group__0__Impl : ( 'type' ) ;
    public final void rule__TypeDefinition__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:881:1: ( ( 'type' ) )
            // InternalModel.g:882:1: ( 'type' )
            {
            // InternalModel.g:882:1: ( 'type' )
            // InternalModel.g:883:2: 'type'
            {
             before(grammarAccess.getTypeDefinitionAccess().getTypeKeyword_0()); 
            match(input,19,FOLLOW_2); 
             after(grammarAccess.getTypeDefinitionAccess().getTypeKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TypeDefinition__Group__0__Impl"


    // $ANTLR start "rule__TypeDefinition__Group__1"
    // InternalModel.g:892:1: rule__TypeDefinition__Group__1 : rule__TypeDefinition__Group__1__Impl rule__TypeDefinition__Group__2 ;
    public final void rule__TypeDefinition__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:896:1: ( rule__TypeDefinition__Group__1__Impl rule__TypeDefinition__Group__2 )
            // InternalModel.g:897:2: rule__TypeDefinition__Group__1__Impl rule__TypeDefinition__Group__2
            {
            pushFollow(FOLLOW_8);
            rule__TypeDefinition__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__TypeDefinition__Group__2();

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
    // $ANTLR end "rule__TypeDefinition__Group__1"


    // $ANTLR start "rule__TypeDefinition__Group__1__Impl"
    // InternalModel.g:904:1: rule__TypeDefinition__Group__1__Impl : ( ( rule__TypeDefinition__NameAssignment_1 ) ) ;
    public final void rule__TypeDefinition__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:908:1: ( ( ( rule__TypeDefinition__NameAssignment_1 ) ) )
            // InternalModel.g:909:1: ( ( rule__TypeDefinition__NameAssignment_1 ) )
            {
            // InternalModel.g:909:1: ( ( rule__TypeDefinition__NameAssignment_1 ) )
            // InternalModel.g:910:2: ( rule__TypeDefinition__NameAssignment_1 )
            {
             before(grammarAccess.getTypeDefinitionAccess().getNameAssignment_1()); 
            // InternalModel.g:911:2: ( rule__TypeDefinition__NameAssignment_1 )
            // InternalModel.g:911:3: rule__TypeDefinition__NameAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__TypeDefinition__NameAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getTypeDefinitionAccess().getNameAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TypeDefinition__Group__1__Impl"


    // $ANTLR start "rule__TypeDefinition__Group__2"
    // InternalModel.g:919:1: rule__TypeDefinition__Group__2 : rule__TypeDefinition__Group__2__Impl rule__TypeDefinition__Group__3 ;
    public final void rule__TypeDefinition__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:923:1: ( rule__TypeDefinition__Group__2__Impl rule__TypeDefinition__Group__3 )
            // InternalModel.g:924:2: rule__TypeDefinition__Group__2__Impl rule__TypeDefinition__Group__3
            {
            pushFollow(FOLLOW_4);
            rule__TypeDefinition__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__TypeDefinition__Group__3();

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
    // $ANTLR end "rule__TypeDefinition__Group__2"


    // $ANTLR start "rule__TypeDefinition__Group__2__Impl"
    // InternalModel.g:931:1: rule__TypeDefinition__Group__2__Impl : ( ':' ) ;
    public final void rule__TypeDefinition__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:935:1: ( ( ':' ) )
            // InternalModel.g:936:1: ( ':' )
            {
            // InternalModel.g:936:1: ( ':' )
            // InternalModel.g:937:2: ':'
            {
             before(grammarAccess.getTypeDefinitionAccess().getColonKeyword_2()); 
            match(input,15,FOLLOW_2); 
             after(grammarAccess.getTypeDefinitionAccess().getColonKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TypeDefinition__Group__2__Impl"


    // $ANTLR start "rule__TypeDefinition__Group__3"
    // InternalModel.g:946:1: rule__TypeDefinition__Group__3 : rule__TypeDefinition__Group__3__Impl rule__TypeDefinition__Group__4 ;
    public final void rule__TypeDefinition__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:950:1: ( rule__TypeDefinition__Group__3__Impl rule__TypeDefinition__Group__4 )
            // InternalModel.g:951:2: rule__TypeDefinition__Group__3__Impl rule__TypeDefinition__Group__4
            {
            pushFollow(FOLLOW_13);
            rule__TypeDefinition__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__TypeDefinition__Group__4();

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
    // $ANTLR end "rule__TypeDefinition__Group__3"


    // $ANTLR start "rule__TypeDefinition__Group__3__Impl"
    // InternalModel.g:958:1: rule__TypeDefinition__Group__3__Impl : ( ( rule__TypeDefinition__FieldTypeAssignment_3 ) ) ;
    public final void rule__TypeDefinition__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:962:1: ( ( ( rule__TypeDefinition__FieldTypeAssignment_3 ) ) )
            // InternalModel.g:963:1: ( ( rule__TypeDefinition__FieldTypeAssignment_3 ) )
            {
            // InternalModel.g:963:1: ( ( rule__TypeDefinition__FieldTypeAssignment_3 ) )
            // InternalModel.g:964:2: ( rule__TypeDefinition__FieldTypeAssignment_3 )
            {
             before(grammarAccess.getTypeDefinitionAccess().getFieldTypeAssignment_3()); 
            // InternalModel.g:965:2: ( rule__TypeDefinition__FieldTypeAssignment_3 )
            // InternalModel.g:965:3: rule__TypeDefinition__FieldTypeAssignment_3
            {
            pushFollow(FOLLOW_2);
            rule__TypeDefinition__FieldTypeAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getTypeDefinitionAccess().getFieldTypeAssignment_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TypeDefinition__Group__3__Impl"


    // $ANTLR start "rule__TypeDefinition__Group__4"
    // InternalModel.g:973:1: rule__TypeDefinition__Group__4 : rule__TypeDefinition__Group__4__Impl rule__TypeDefinition__Group__5 ;
    public final void rule__TypeDefinition__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:977:1: ( rule__TypeDefinition__Group__4__Impl rule__TypeDefinition__Group__5 )
            // InternalModel.g:978:2: rule__TypeDefinition__Group__4__Impl rule__TypeDefinition__Group__5
            {
            pushFollow(FOLLOW_13);
            rule__TypeDefinition__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__TypeDefinition__Group__5();

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
    // $ANTLR end "rule__TypeDefinition__Group__4"


    // $ANTLR start "rule__TypeDefinition__Group__4__Impl"
    // InternalModel.g:985:1: rule__TypeDefinition__Group__4__Impl : ( ( rule__TypeDefinition__Group_4__0 )? ) ;
    public final void rule__TypeDefinition__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:989:1: ( ( ( rule__TypeDefinition__Group_4__0 )? ) )
            // InternalModel.g:990:1: ( ( rule__TypeDefinition__Group_4__0 )? )
            {
            // InternalModel.g:990:1: ( ( rule__TypeDefinition__Group_4__0 )? )
            // InternalModel.g:991:2: ( rule__TypeDefinition__Group_4__0 )?
            {
             before(grammarAccess.getTypeDefinitionAccess().getGroup_4()); 
            // InternalModel.g:992:2: ( rule__TypeDefinition__Group_4__0 )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==20) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // InternalModel.g:992:3: rule__TypeDefinition__Group_4__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__TypeDefinition__Group_4__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getTypeDefinitionAccess().getGroup_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TypeDefinition__Group__4__Impl"


    // $ANTLR start "rule__TypeDefinition__Group__5"
    // InternalModel.g:1000:1: rule__TypeDefinition__Group__5 : rule__TypeDefinition__Group__5__Impl ;
    public final void rule__TypeDefinition__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:1004:1: ( rule__TypeDefinition__Group__5__Impl )
            // InternalModel.g:1005:2: rule__TypeDefinition__Group__5__Impl
            {
            pushFollow(FOLLOW_2);
            rule__TypeDefinition__Group__5__Impl();

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
    // $ANTLR end "rule__TypeDefinition__Group__5"


    // $ANTLR start "rule__TypeDefinition__Group__5__Impl"
    // InternalModel.g:1011:1: rule__TypeDefinition__Group__5__Impl : ( ';' ) ;
    public final void rule__TypeDefinition__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:1015:1: ( ( ';' ) )
            // InternalModel.g:1016:1: ( ';' )
            {
            // InternalModel.g:1016:1: ( ';' )
            // InternalModel.g:1017:2: ';'
            {
             before(grammarAccess.getTypeDefinitionAccess().getSemicolonKeyword_5()); 
            match(input,12,FOLLOW_2); 
             after(grammarAccess.getTypeDefinitionAccess().getSemicolonKeyword_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TypeDefinition__Group__5__Impl"


    // $ANTLR start "rule__TypeDefinition__Group_4__0"
    // InternalModel.g:1027:1: rule__TypeDefinition__Group_4__0 : rule__TypeDefinition__Group_4__0__Impl rule__TypeDefinition__Group_4__1 ;
    public final void rule__TypeDefinition__Group_4__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:1031:1: ( rule__TypeDefinition__Group_4__0__Impl rule__TypeDefinition__Group_4__1 )
            // InternalModel.g:1032:2: rule__TypeDefinition__Group_4__0__Impl rule__TypeDefinition__Group_4__1
            {
            pushFollow(FOLLOW_14);
            rule__TypeDefinition__Group_4__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__TypeDefinition__Group_4__1();

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
    // $ANTLR end "rule__TypeDefinition__Group_4__0"


    // $ANTLR start "rule__TypeDefinition__Group_4__0__Impl"
    // InternalModel.g:1039:1: rule__TypeDefinition__Group_4__0__Impl : ( '(' ) ;
    public final void rule__TypeDefinition__Group_4__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:1043:1: ( ( '(' ) )
            // InternalModel.g:1044:1: ( '(' )
            {
            // InternalModel.g:1044:1: ( '(' )
            // InternalModel.g:1045:2: '('
            {
             before(grammarAccess.getTypeDefinitionAccess().getLeftParenthesisKeyword_4_0()); 
            match(input,20,FOLLOW_2); 
             after(grammarAccess.getTypeDefinitionAccess().getLeftParenthesisKeyword_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TypeDefinition__Group_4__0__Impl"


    // $ANTLR start "rule__TypeDefinition__Group_4__1"
    // InternalModel.g:1054:1: rule__TypeDefinition__Group_4__1 : rule__TypeDefinition__Group_4__1__Impl rule__TypeDefinition__Group_4__2 ;
    public final void rule__TypeDefinition__Group_4__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:1058:1: ( rule__TypeDefinition__Group_4__1__Impl rule__TypeDefinition__Group_4__2 )
            // InternalModel.g:1059:2: rule__TypeDefinition__Group_4__1__Impl rule__TypeDefinition__Group_4__2
            {
            pushFollow(FOLLOW_15);
            rule__TypeDefinition__Group_4__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__TypeDefinition__Group_4__2();

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
    // $ANTLR end "rule__TypeDefinition__Group_4__1"


    // $ANTLR start "rule__TypeDefinition__Group_4__1__Impl"
    // InternalModel.g:1066:1: rule__TypeDefinition__Group_4__1__Impl : ( ( rule__TypeDefinition__FieldLengthAssignment_4_1 ) ) ;
    public final void rule__TypeDefinition__Group_4__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:1070:1: ( ( ( rule__TypeDefinition__FieldLengthAssignment_4_1 ) ) )
            // InternalModel.g:1071:1: ( ( rule__TypeDefinition__FieldLengthAssignment_4_1 ) )
            {
            // InternalModel.g:1071:1: ( ( rule__TypeDefinition__FieldLengthAssignment_4_1 ) )
            // InternalModel.g:1072:2: ( rule__TypeDefinition__FieldLengthAssignment_4_1 )
            {
             before(grammarAccess.getTypeDefinitionAccess().getFieldLengthAssignment_4_1()); 
            // InternalModel.g:1073:2: ( rule__TypeDefinition__FieldLengthAssignment_4_1 )
            // InternalModel.g:1073:3: rule__TypeDefinition__FieldLengthAssignment_4_1
            {
            pushFollow(FOLLOW_2);
            rule__TypeDefinition__FieldLengthAssignment_4_1();

            state._fsp--;


            }

             after(grammarAccess.getTypeDefinitionAccess().getFieldLengthAssignment_4_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TypeDefinition__Group_4__1__Impl"


    // $ANTLR start "rule__TypeDefinition__Group_4__2"
    // InternalModel.g:1081:1: rule__TypeDefinition__Group_4__2 : rule__TypeDefinition__Group_4__2__Impl ;
    public final void rule__TypeDefinition__Group_4__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:1085:1: ( rule__TypeDefinition__Group_4__2__Impl )
            // InternalModel.g:1086:2: rule__TypeDefinition__Group_4__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__TypeDefinition__Group_4__2__Impl();

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
    // $ANTLR end "rule__TypeDefinition__Group_4__2"


    // $ANTLR start "rule__TypeDefinition__Group_4__2__Impl"
    // InternalModel.g:1092:1: rule__TypeDefinition__Group_4__2__Impl : ( ')' ) ;
    public final void rule__TypeDefinition__Group_4__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:1096:1: ( ( ')' ) )
            // InternalModel.g:1097:1: ( ')' )
            {
            // InternalModel.g:1097:1: ( ')' )
            // InternalModel.g:1098:2: ')'
            {
             before(grammarAccess.getTypeDefinitionAccess().getRightParenthesisKeyword_4_2()); 
            match(input,21,FOLLOW_2); 
             after(grammarAccess.getTypeDefinitionAccess().getRightParenthesisKeyword_4_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TypeDefinition__Group_4__2__Impl"


    // $ANTLR start "rule__Entity__Group__0"
    // InternalModel.g:1108:1: rule__Entity__Group__0 : rule__Entity__Group__0__Impl rule__Entity__Group__1 ;
    public final void rule__Entity__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:1112:1: ( rule__Entity__Group__0__Impl rule__Entity__Group__1 )
            // InternalModel.g:1113:2: rule__Entity__Group__0__Impl rule__Entity__Group__1
            {
            pushFollow(FOLLOW_4);
            rule__Entity__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Entity__Group__1();

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
    // $ANTLR end "rule__Entity__Group__0"


    // $ANTLR start "rule__Entity__Group__0__Impl"
    // InternalModel.g:1120:1: rule__Entity__Group__0__Impl : ( 'entity' ) ;
    public final void rule__Entity__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:1124:1: ( ( 'entity' ) )
            // InternalModel.g:1125:1: ( 'entity' )
            {
            // InternalModel.g:1125:1: ( 'entity' )
            // InternalModel.g:1126:2: 'entity'
            {
             before(grammarAccess.getEntityAccess().getEntityKeyword_0()); 
            match(input,22,FOLLOW_2); 
             after(grammarAccess.getEntityAccess().getEntityKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Entity__Group__0__Impl"


    // $ANTLR start "rule__Entity__Group__1"
    // InternalModel.g:1135:1: rule__Entity__Group__1 : rule__Entity__Group__1__Impl rule__Entity__Group__2 ;
    public final void rule__Entity__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:1139:1: ( rule__Entity__Group__1__Impl rule__Entity__Group__2 )
            // InternalModel.g:1140:2: rule__Entity__Group__1__Impl rule__Entity__Group__2
            {
            pushFollow(FOLLOW_9);
            rule__Entity__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Entity__Group__2();

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
    // $ANTLR end "rule__Entity__Group__1"


    // $ANTLR start "rule__Entity__Group__1__Impl"
    // InternalModel.g:1147:1: rule__Entity__Group__1__Impl : ( ( rule__Entity__NameAssignment_1 ) ) ;
    public final void rule__Entity__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:1151:1: ( ( ( rule__Entity__NameAssignment_1 ) ) )
            // InternalModel.g:1152:1: ( ( rule__Entity__NameAssignment_1 ) )
            {
            // InternalModel.g:1152:1: ( ( rule__Entity__NameAssignment_1 ) )
            // InternalModel.g:1153:2: ( rule__Entity__NameAssignment_1 )
            {
             before(grammarAccess.getEntityAccess().getNameAssignment_1()); 
            // InternalModel.g:1154:2: ( rule__Entity__NameAssignment_1 )
            // InternalModel.g:1154:3: rule__Entity__NameAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__Entity__NameAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getEntityAccess().getNameAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Entity__Group__1__Impl"


    // $ANTLR start "rule__Entity__Group__2"
    // InternalModel.g:1162:1: rule__Entity__Group__2 : rule__Entity__Group__2__Impl rule__Entity__Group__3 ;
    public final void rule__Entity__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:1166:1: ( rule__Entity__Group__2__Impl rule__Entity__Group__3 )
            // InternalModel.g:1167:2: rule__Entity__Group__2__Impl rule__Entity__Group__3
            {
            pushFollow(FOLLOW_16);
            rule__Entity__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Entity__Group__3();

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
    // $ANTLR end "rule__Entity__Group__2"


    // $ANTLR start "rule__Entity__Group__2__Impl"
    // InternalModel.g:1174:1: rule__Entity__Group__2__Impl : ( '{' ) ;
    public final void rule__Entity__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:1178:1: ( ( '{' ) )
            // InternalModel.g:1179:1: ( '{' )
            {
            // InternalModel.g:1179:1: ( '{' )
            // InternalModel.g:1180:2: '{'
            {
             before(grammarAccess.getEntityAccess().getLeftCurlyBracketKeyword_2()); 
            match(input,17,FOLLOW_2); 
             after(grammarAccess.getEntityAccess().getLeftCurlyBracketKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Entity__Group__2__Impl"


    // $ANTLR start "rule__Entity__Group__3"
    // InternalModel.g:1189:1: rule__Entity__Group__3 : rule__Entity__Group__3__Impl rule__Entity__Group__4 ;
    public final void rule__Entity__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:1193:1: ( rule__Entity__Group__3__Impl rule__Entity__Group__4 )
            // InternalModel.g:1194:2: rule__Entity__Group__3__Impl rule__Entity__Group__4
            {
            pushFollow(FOLLOW_16);
            rule__Entity__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Entity__Group__4();

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
    // $ANTLR end "rule__Entity__Group__3"


    // $ANTLR start "rule__Entity__Group__3__Impl"
    // InternalModel.g:1201:1: rule__Entity__Group__3__Impl : ( ( rule__Entity__FieldsAssignment_3 )* ) ;
    public final void rule__Entity__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:1205:1: ( ( ( rule__Entity__FieldsAssignment_3 )* ) )
            // InternalModel.g:1206:1: ( ( rule__Entity__FieldsAssignment_3 )* )
            {
            // InternalModel.g:1206:1: ( ( rule__Entity__FieldsAssignment_3 )* )
            // InternalModel.g:1207:2: ( rule__Entity__FieldsAssignment_3 )*
            {
             before(grammarAccess.getEntityAccess().getFieldsAssignment_3()); 
            // InternalModel.g:1208:2: ( rule__Entity__FieldsAssignment_3 )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==RULE_ID||LA8_0==27) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // InternalModel.g:1208:3: rule__Entity__FieldsAssignment_3
            	    {
            	    pushFollow(FOLLOW_17);
            	    rule__Entity__FieldsAssignment_3();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);

             after(grammarAccess.getEntityAccess().getFieldsAssignment_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Entity__Group__3__Impl"


    // $ANTLR start "rule__Entity__Group__4"
    // InternalModel.g:1216:1: rule__Entity__Group__4 : rule__Entity__Group__4__Impl rule__Entity__Group__5 ;
    public final void rule__Entity__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:1220:1: ( rule__Entity__Group__4__Impl rule__Entity__Group__5 )
            // InternalModel.g:1221:2: rule__Entity__Group__4__Impl rule__Entity__Group__5
            {
            pushFollow(FOLLOW_5);
            rule__Entity__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Entity__Group__5();

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
    // $ANTLR end "rule__Entity__Group__4"


    // $ANTLR start "rule__Entity__Group__4__Impl"
    // InternalModel.g:1228:1: rule__Entity__Group__4__Impl : ( '}' ) ;
    public final void rule__Entity__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:1232:1: ( ( '}' ) )
            // InternalModel.g:1233:1: ( '}' )
            {
            // InternalModel.g:1233:1: ( '}' )
            // InternalModel.g:1234:2: '}'
            {
             before(grammarAccess.getEntityAccess().getRightCurlyBracketKeyword_4()); 
            match(input,18,FOLLOW_2); 
             after(grammarAccess.getEntityAccess().getRightCurlyBracketKeyword_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Entity__Group__4__Impl"


    // $ANTLR start "rule__Entity__Group__5"
    // InternalModel.g:1243:1: rule__Entity__Group__5 : rule__Entity__Group__5__Impl ;
    public final void rule__Entity__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:1247:1: ( rule__Entity__Group__5__Impl )
            // InternalModel.g:1248:2: rule__Entity__Group__5__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Entity__Group__5__Impl();

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
    // $ANTLR end "rule__Entity__Group__5"


    // $ANTLR start "rule__Entity__Group__5__Impl"
    // InternalModel.g:1254:1: rule__Entity__Group__5__Impl : ( ';' ) ;
    public final void rule__Entity__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:1258:1: ( ( ';' ) )
            // InternalModel.g:1259:1: ( ';' )
            {
            // InternalModel.g:1259:1: ( ';' )
            // InternalModel.g:1260:2: ';'
            {
             before(grammarAccess.getEntityAccess().getSemicolonKeyword_5()); 
            match(input,12,FOLLOW_2); 
             after(grammarAccess.getEntityAccess().getSemicolonKeyword_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Entity__Group__5__Impl"


    // $ANTLR start "rule__FieldPrimitive__Group__0"
    // InternalModel.g:1270:1: rule__FieldPrimitive__Group__0 : rule__FieldPrimitive__Group__0__Impl rule__FieldPrimitive__Group__1 ;
    public final void rule__FieldPrimitive__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:1274:1: ( rule__FieldPrimitive__Group__0__Impl rule__FieldPrimitive__Group__1 )
            // InternalModel.g:1275:2: rule__FieldPrimitive__Group__0__Impl rule__FieldPrimitive__Group__1
            {
            pushFollow(FOLLOW_18);
            rule__FieldPrimitive__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__FieldPrimitive__Group__1();

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
    // $ANTLR end "rule__FieldPrimitive__Group__0"


    // $ANTLR start "rule__FieldPrimitive__Group__0__Impl"
    // InternalModel.g:1282:1: rule__FieldPrimitive__Group__0__Impl : ( ( rule__FieldPrimitive__KeyAssignment_0 )? ) ;
    public final void rule__FieldPrimitive__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:1286:1: ( ( ( rule__FieldPrimitive__KeyAssignment_0 )? ) )
            // InternalModel.g:1287:1: ( ( rule__FieldPrimitive__KeyAssignment_0 )? )
            {
            // InternalModel.g:1287:1: ( ( rule__FieldPrimitive__KeyAssignment_0 )? )
            // InternalModel.g:1288:2: ( rule__FieldPrimitive__KeyAssignment_0 )?
            {
             before(grammarAccess.getFieldPrimitiveAccess().getKeyAssignment_0()); 
            // InternalModel.g:1289:2: ( rule__FieldPrimitive__KeyAssignment_0 )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==27) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // InternalModel.g:1289:3: rule__FieldPrimitive__KeyAssignment_0
                    {
                    pushFollow(FOLLOW_2);
                    rule__FieldPrimitive__KeyAssignment_0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getFieldPrimitiveAccess().getKeyAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FieldPrimitive__Group__0__Impl"


    // $ANTLR start "rule__FieldPrimitive__Group__1"
    // InternalModel.g:1297:1: rule__FieldPrimitive__Group__1 : rule__FieldPrimitive__Group__1__Impl rule__FieldPrimitive__Group__2 ;
    public final void rule__FieldPrimitive__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:1301:1: ( rule__FieldPrimitive__Group__1__Impl rule__FieldPrimitive__Group__2 )
            // InternalModel.g:1302:2: rule__FieldPrimitive__Group__1__Impl rule__FieldPrimitive__Group__2
            {
            pushFollow(FOLLOW_8);
            rule__FieldPrimitive__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__FieldPrimitive__Group__2();

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
    // $ANTLR end "rule__FieldPrimitive__Group__1"


    // $ANTLR start "rule__FieldPrimitive__Group__1__Impl"
    // InternalModel.g:1309:1: rule__FieldPrimitive__Group__1__Impl : ( ( rule__FieldPrimitive__NameAssignment_1 ) ) ;
    public final void rule__FieldPrimitive__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:1313:1: ( ( ( rule__FieldPrimitive__NameAssignment_1 ) ) )
            // InternalModel.g:1314:1: ( ( rule__FieldPrimitive__NameAssignment_1 ) )
            {
            // InternalModel.g:1314:1: ( ( rule__FieldPrimitive__NameAssignment_1 ) )
            // InternalModel.g:1315:2: ( rule__FieldPrimitive__NameAssignment_1 )
            {
             before(grammarAccess.getFieldPrimitiveAccess().getNameAssignment_1()); 
            // InternalModel.g:1316:2: ( rule__FieldPrimitive__NameAssignment_1 )
            // InternalModel.g:1316:3: rule__FieldPrimitive__NameAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__FieldPrimitive__NameAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getFieldPrimitiveAccess().getNameAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FieldPrimitive__Group__1__Impl"


    // $ANTLR start "rule__FieldPrimitive__Group__2"
    // InternalModel.g:1324:1: rule__FieldPrimitive__Group__2 : rule__FieldPrimitive__Group__2__Impl rule__FieldPrimitive__Group__3 ;
    public final void rule__FieldPrimitive__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:1328:1: ( rule__FieldPrimitive__Group__2__Impl rule__FieldPrimitive__Group__3 )
            // InternalModel.g:1329:2: rule__FieldPrimitive__Group__2__Impl rule__FieldPrimitive__Group__3
            {
            pushFollow(FOLLOW_4);
            rule__FieldPrimitive__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__FieldPrimitive__Group__3();

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
    // $ANTLR end "rule__FieldPrimitive__Group__2"


    // $ANTLR start "rule__FieldPrimitive__Group__2__Impl"
    // InternalModel.g:1336:1: rule__FieldPrimitive__Group__2__Impl : ( ':' ) ;
    public final void rule__FieldPrimitive__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:1340:1: ( ( ':' ) )
            // InternalModel.g:1341:1: ( ':' )
            {
            // InternalModel.g:1341:1: ( ':' )
            // InternalModel.g:1342:2: ':'
            {
             before(grammarAccess.getFieldPrimitiveAccess().getColonKeyword_2()); 
            match(input,15,FOLLOW_2); 
             after(grammarAccess.getFieldPrimitiveAccess().getColonKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FieldPrimitive__Group__2__Impl"


    // $ANTLR start "rule__FieldPrimitive__Group__3"
    // InternalModel.g:1351:1: rule__FieldPrimitive__Group__3 : rule__FieldPrimitive__Group__3__Impl rule__FieldPrimitive__Group__4 ;
    public final void rule__FieldPrimitive__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:1355:1: ( rule__FieldPrimitive__Group__3__Impl rule__FieldPrimitive__Group__4 )
            // InternalModel.g:1356:2: rule__FieldPrimitive__Group__3__Impl rule__FieldPrimitive__Group__4
            {
            pushFollow(FOLLOW_19);
            rule__FieldPrimitive__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__FieldPrimitive__Group__4();

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
    // $ANTLR end "rule__FieldPrimitive__Group__3"


    // $ANTLR start "rule__FieldPrimitive__Group__3__Impl"
    // InternalModel.g:1363:1: rule__FieldPrimitive__Group__3__Impl : ( ( rule__FieldPrimitive__FieldTypeAssignment_3 ) ) ;
    public final void rule__FieldPrimitive__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:1367:1: ( ( ( rule__FieldPrimitive__FieldTypeAssignment_3 ) ) )
            // InternalModel.g:1368:1: ( ( rule__FieldPrimitive__FieldTypeAssignment_3 ) )
            {
            // InternalModel.g:1368:1: ( ( rule__FieldPrimitive__FieldTypeAssignment_3 ) )
            // InternalModel.g:1369:2: ( rule__FieldPrimitive__FieldTypeAssignment_3 )
            {
             before(grammarAccess.getFieldPrimitiveAccess().getFieldTypeAssignment_3()); 
            // InternalModel.g:1370:2: ( rule__FieldPrimitive__FieldTypeAssignment_3 )
            // InternalModel.g:1370:3: rule__FieldPrimitive__FieldTypeAssignment_3
            {
            pushFollow(FOLLOW_2);
            rule__FieldPrimitive__FieldTypeAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getFieldPrimitiveAccess().getFieldTypeAssignment_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FieldPrimitive__Group__3__Impl"


    // $ANTLR start "rule__FieldPrimitive__Group__4"
    // InternalModel.g:1378:1: rule__FieldPrimitive__Group__4 : rule__FieldPrimitive__Group__4__Impl rule__FieldPrimitive__Group__5 ;
    public final void rule__FieldPrimitive__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:1382:1: ( rule__FieldPrimitive__Group__4__Impl rule__FieldPrimitive__Group__5 )
            // InternalModel.g:1383:2: rule__FieldPrimitive__Group__4__Impl rule__FieldPrimitive__Group__5
            {
            pushFollow(FOLLOW_19);
            rule__FieldPrimitive__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__FieldPrimitive__Group__5();

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
    // $ANTLR end "rule__FieldPrimitive__Group__4"


    // $ANTLR start "rule__FieldPrimitive__Group__4__Impl"
    // InternalModel.g:1390:1: rule__FieldPrimitive__Group__4__Impl : ( ( rule__FieldPrimitive__Group_4__0 )? ) ;
    public final void rule__FieldPrimitive__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:1394:1: ( ( ( rule__FieldPrimitive__Group_4__0 )? ) )
            // InternalModel.g:1395:1: ( ( rule__FieldPrimitive__Group_4__0 )? )
            {
            // InternalModel.g:1395:1: ( ( rule__FieldPrimitive__Group_4__0 )? )
            // InternalModel.g:1396:2: ( rule__FieldPrimitive__Group_4__0 )?
            {
             before(grammarAccess.getFieldPrimitiveAccess().getGroup_4()); 
            // InternalModel.g:1397:2: ( rule__FieldPrimitive__Group_4__0 )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==20) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // InternalModel.g:1397:3: rule__FieldPrimitive__Group_4__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__FieldPrimitive__Group_4__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getFieldPrimitiveAccess().getGroup_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FieldPrimitive__Group__4__Impl"


    // $ANTLR start "rule__FieldPrimitive__Group__5"
    // InternalModel.g:1405:1: rule__FieldPrimitive__Group__5 : rule__FieldPrimitive__Group__5__Impl rule__FieldPrimitive__Group__6 ;
    public final void rule__FieldPrimitive__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:1409:1: ( rule__FieldPrimitive__Group__5__Impl rule__FieldPrimitive__Group__6 )
            // InternalModel.g:1410:2: rule__FieldPrimitive__Group__5__Impl rule__FieldPrimitive__Group__6
            {
            pushFollow(FOLLOW_19);
            rule__FieldPrimitive__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__FieldPrimitive__Group__6();

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
    // $ANTLR end "rule__FieldPrimitive__Group__5"


    // $ANTLR start "rule__FieldPrimitive__Group__5__Impl"
    // InternalModel.g:1417:1: rule__FieldPrimitive__Group__5__Impl : ( ( rule__FieldPrimitive__FieldPredefinedTypeAssignment_5 )? ) ;
    public final void rule__FieldPrimitive__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:1421:1: ( ( ( rule__FieldPrimitive__FieldPredefinedTypeAssignment_5 )? ) )
            // InternalModel.g:1422:1: ( ( rule__FieldPrimitive__FieldPredefinedTypeAssignment_5 )? )
            {
            // InternalModel.g:1422:1: ( ( rule__FieldPrimitive__FieldPredefinedTypeAssignment_5 )? )
            // InternalModel.g:1423:2: ( rule__FieldPrimitive__FieldPredefinedTypeAssignment_5 )?
            {
             before(grammarAccess.getFieldPrimitiveAccess().getFieldPredefinedTypeAssignment_5()); 
            // InternalModel.g:1424:2: ( rule__FieldPrimitive__FieldPredefinedTypeAssignment_5 )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==RULE_STRING) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // InternalModel.g:1424:3: rule__FieldPrimitive__FieldPredefinedTypeAssignment_5
                    {
                    pushFollow(FOLLOW_2);
                    rule__FieldPrimitive__FieldPredefinedTypeAssignment_5();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getFieldPrimitiveAccess().getFieldPredefinedTypeAssignment_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FieldPrimitive__Group__5__Impl"


    // $ANTLR start "rule__FieldPrimitive__Group__6"
    // InternalModel.g:1432:1: rule__FieldPrimitive__Group__6 : rule__FieldPrimitive__Group__6__Impl ;
    public final void rule__FieldPrimitive__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:1436:1: ( rule__FieldPrimitive__Group__6__Impl )
            // InternalModel.g:1437:2: rule__FieldPrimitive__Group__6__Impl
            {
            pushFollow(FOLLOW_2);
            rule__FieldPrimitive__Group__6__Impl();

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
    // $ANTLR end "rule__FieldPrimitive__Group__6"


    // $ANTLR start "rule__FieldPrimitive__Group__6__Impl"
    // InternalModel.g:1443:1: rule__FieldPrimitive__Group__6__Impl : ( ';' ) ;
    public final void rule__FieldPrimitive__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:1447:1: ( ( ';' ) )
            // InternalModel.g:1448:1: ( ';' )
            {
            // InternalModel.g:1448:1: ( ';' )
            // InternalModel.g:1449:2: ';'
            {
             before(grammarAccess.getFieldPrimitiveAccess().getSemicolonKeyword_6()); 
            match(input,12,FOLLOW_2); 
             after(grammarAccess.getFieldPrimitiveAccess().getSemicolonKeyword_6()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FieldPrimitive__Group__6__Impl"


    // $ANTLR start "rule__FieldPrimitive__Group_4__0"
    // InternalModel.g:1459:1: rule__FieldPrimitive__Group_4__0 : rule__FieldPrimitive__Group_4__0__Impl rule__FieldPrimitive__Group_4__1 ;
    public final void rule__FieldPrimitive__Group_4__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:1463:1: ( rule__FieldPrimitive__Group_4__0__Impl rule__FieldPrimitive__Group_4__1 )
            // InternalModel.g:1464:2: rule__FieldPrimitive__Group_4__0__Impl rule__FieldPrimitive__Group_4__1
            {
            pushFollow(FOLLOW_14);
            rule__FieldPrimitive__Group_4__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__FieldPrimitive__Group_4__1();

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
    // $ANTLR end "rule__FieldPrimitive__Group_4__0"


    // $ANTLR start "rule__FieldPrimitive__Group_4__0__Impl"
    // InternalModel.g:1471:1: rule__FieldPrimitive__Group_4__0__Impl : ( '(' ) ;
    public final void rule__FieldPrimitive__Group_4__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:1475:1: ( ( '(' ) )
            // InternalModel.g:1476:1: ( '(' )
            {
            // InternalModel.g:1476:1: ( '(' )
            // InternalModel.g:1477:2: '('
            {
             before(grammarAccess.getFieldPrimitiveAccess().getLeftParenthesisKeyword_4_0()); 
            match(input,20,FOLLOW_2); 
             after(grammarAccess.getFieldPrimitiveAccess().getLeftParenthesisKeyword_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FieldPrimitive__Group_4__0__Impl"


    // $ANTLR start "rule__FieldPrimitive__Group_4__1"
    // InternalModel.g:1486:1: rule__FieldPrimitive__Group_4__1 : rule__FieldPrimitive__Group_4__1__Impl rule__FieldPrimitive__Group_4__2 ;
    public final void rule__FieldPrimitive__Group_4__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:1490:1: ( rule__FieldPrimitive__Group_4__1__Impl rule__FieldPrimitive__Group_4__2 )
            // InternalModel.g:1491:2: rule__FieldPrimitive__Group_4__1__Impl rule__FieldPrimitive__Group_4__2
            {
            pushFollow(FOLLOW_15);
            rule__FieldPrimitive__Group_4__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__FieldPrimitive__Group_4__2();

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
    // $ANTLR end "rule__FieldPrimitive__Group_4__1"


    // $ANTLR start "rule__FieldPrimitive__Group_4__1__Impl"
    // InternalModel.g:1498:1: rule__FieldPrimitive__Group_4__1__Impl : ( ( rule__FieldPrimitive__FieldLengthAssignment_4_1 ) ) ;
    public final void rule__FieldPrimitive__Group_4__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:1502:1: ( ( ( rule__FieldPrimitive__FieldLengthAssignment_4_1 ) ) )
            // InternalModel.g:1503:1: ( ( rule__FieldPrimitive__FieldLengthAssignment_4_1 ) )
            {
            // InternalModel.g:1503:1: ( ( rule__FieldPrimitive__FieldLengthAssignment_4_1 ) )
            // InternalModel.g:1504:2: ( rule__FieldPrimitive__FieldLengthAssignment_4_1 )
            {
             before(grammarAccess.getFieldPrimitiveAccess().getFieldLengthAssignment_4_1()); 
            // InternalModel.g:1505:2: ( rule__FieldPrimitive__FieldLengthAssignment_4_1 )
            // InternalModel.g:1505:3: rule__FieldPrimitive__FieldLengthAssignment_4_1
            {
            pushFollow(FOLLOW_2);
            rule__FieldPrimitive__FieldLengthAssignment_4_1();

            state._fsp--;


            }

             after(grammarAccess.getFieldPrimitiveAccess().getFieldLengthAssignment_4_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FieldPrimitive__Group_4__1__Impl"


    // $ANTLR start "rule__FieldPrimitive__Group_4__2"
    // InternalModel.g:1513:1: rule__FieldPrimitive__Group_4__2 : rule__FieldPrimitive__Group_4__2__Impl ;
    public final void rule__FieldPrimitive__Group_4__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:1517:1: ( rule__FieldPrimitive__Group_4__2__Impl )
            // InternalModel.g:1518:2: rule__FieldPrimitive__Group_4__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__FieldPrimitive__Group_4__2__Impl();

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
    // $ANTLR end "rule__FieldPrimitive__Group_4__2"


    // $ANTLR start "rule__FieldPrimitive__Group_4__2__Impl"
    // InternalModel.g:1524:1: rule__FieldPrimitive__Group_4__2__Impl : ( ')' ) ;
    public final void rule__FieldPrimitive__Group_4__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:1528:1: ( ( ')' ) )
            // InternalModel.g:1529:1: ( ')' )
            {
            // InternalModel.g:1529:1: ( ')' )
            // InternalModel.g:1530:2: ')'
            {
             before(grammarAccess.getFieldPrimitiveAccess().getRightParenthesisKeyword_4_2()); 
            match(input,21,FOLLOW_2); 
             after(grammarAccess.getFieldPrimitiveAccess().getRightParenthesisKeyword_4_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FieldPrimitive__Group_4__2__Impl"


    // $ANTLR start "rule__FieldType__Group__0"
    // InternalModel.g:1540:1: rule__FieldType__Group__0 : rule__FieldType__Group__0__Impl rule__FieldType__Group__1 ;
    public final void rule__FieldType__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:1544:1: ( rule__FieldType__Group__0__Impl rule__FieldType__Group__1 )
            // InternalModel.g:1545:2: rule__FieldType__Group__0__Impl rule__FieldType__Group__1
            {
            pushFollow(FOLLOW_18);
            rule__FieldType__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__FieldType__Group__1();

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
    // $ANTLR end "rule__FieldType__Group__0"


    // $ANTLR start "rule__FieldType__Group__0__Impl"
    // InternalModel.g:1552:1: rule__FieldType__Group__0__Impl : ( ( rule__FieldType__KeyAssignment_0 )? ) ;
    public final void rule__FieldType__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:1556:1: ( ( ( rule__FieldType__KeyAssignment_0 )? ) )
            // InternalModel.g:1557:1: ( ( rule__FieldType__KeyAssignment_0 )? )
            {
            // InternalModel.g:1557:1: ( ( rule__FieldType__KeyAssignment_0 )? )
            // InternalModel.g:1558:2: ( rule__FieldType__KeyAssignment_0 )?
            {
             before(grammarAccess.getFieldTypeAccess().getKeyAssignment_0()); 
            // InternalModel.g:1559:2: ( rule__FieldType__KeyAssignment_0 )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==27) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // InternalModel.g:1559:3: rule__FieldType__KeyAssignment_0
                    {
                    pushFollow(FOLLOW_2);
                    rule__FieldType__KeyAssignment_0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getFieldTypeAccess().getKeyAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FieldType__Group__0__Impl"


    // $ANTLR start "rule__FieldType__Group__1"
    // InternalModel.g:1567:1: rule__FieldType__Group__1 : rule__FieldType__Group__1__Impl rule__FieldType__Group__2 ;
    public final void rule__FieldType__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:1571:1: ( rule__FieldType__Group__1__Impl rule__FieldType__Group__2 )
            // InternalModel.g:1572:2: rule__FieldType__Group__1__Impl rule__FieldType__Group__2
            {
            pushFollow(FOLLOW_8);
            rule__FieldType__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__FieldType__Group__2();

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
    // $ANTLR end "rule__FieldType__Group__1"


    // $ANTLR start "rule__FieldType__Group__1__Impl"
    // InternalModel.g:1579:1: rule__FieldType__Group__1__Impl : ( ( rule__FieldType__NameAssignment_1 ) ) ;
    public final void rule__FieldType__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:1583:1: ( ( ( rule__FieldType__NameAssignment_1 ) ) )
            // InternalModel.g:1584:1: ( ( rule__FieldType__NameAssignment_1 ) )
            {
            // InternalModel.g:1584:1: ( ( rule__FieldType__NameAssignment_1 ) )
            // InternalModel.g:1585:2: ( rule__FieldType__NameAssignment_1 )
            {
             before(grammarAccess.getFieldTypeAccess().getNameAssignment_1()); 
            // InternalModel.g:1586:2: ( rule__FieldType__NameAssignment_1 )
            // InternalModel.g:1586:3: rule__FieldType__NameAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__FieldType__NameAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getFieldTypeAccess().getNameAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FieldType__Group__1__Impl"


    // $ANTLR start "rule__FieldType__Group__2"
    // InternalModel.g:1594:1: rule__FieldType__Group__2 : rule__FieldType__Group__2__Impl rule__FieldType__Group__3 ;
    public final void rule__FieldType__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:1598:1: ( rule__FieldType__Group__2__Impl rule__FieldType__Group__3 )
            // InternalModel.g:1599:2: rule__FieldType__Group__2__Impl rule__FieldType__Group__3
            {
            pushFollow(FOLLOW_20);
            rule__FieldType__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__FieldType__Group__3();

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
    // $ANTLR end "rule__FieldType__Group__2"


    // $ANTLR start "rule__FieldType__Group__2__Impl"
    // InternalModel.g:1606:1: rule__FieldType__Group__2__Impl : ( ':' ) ;
    public final void rule__FieldType__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:1610:1: ( ( ':' ) )
            // InternalModel.g:1611:1: ( ':' )
            {
            // InternalModel.g:1611:1: ( ':' )
            // InternalModel.g:1612:2: ':'
            {
             before(grammarAccess.getFieldTypeAccess().getColonKeyword_2()); 
            match(input,15,FOLLOW_2); 
             after(grammarAccess.getFieldTypeAccess().getColonKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FieldType__Group__2__Impl"


    // $ANTLR start "rule__FieldType__Group__3"
    // InternalModel.g:1621:1: rule__FieldType__Group__3 : rule__FieldType__Group__3__Impl rule__FieldType__Group__4 ;
    public final void rule__FieldType__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:1625:1: ( rule__FieldType__Group__3__Impl rule__FieldType__Group__4 )
            // InternalModel.g:1626:2: rule__FieldType__Group__3__Impl rule__FieldType__Group__4
            {
            pushFollow(FOLLOW_20);
            rule__FieldType__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__FieldType__Group__4();

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
    // $ANTLR end "rule__FieldType__Group__3"


    // $ANTLR start "rule__FieldType__Group__3__Impl"
    // InternalModel.g:1633:1: rule__FieldType__Group__3__Impl : ( ( rule__FieldType__FieldPredefinedTypeAssignment_3 )? ) ;
    public final void rule__FieldType__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:1637:1: ( ( ( rule__FieldType__FieldPredefinedTypeAssignment_3 )? ) )
            // InternalModel.g:1638:1: ( ( rule__FieldType__FieldPredefinedTypeAssignment_3 )? )
            {
            // InternalModel.g:1638:1: ( ( rule__FieldType__FieldPredefinedTypeAssignment_3 )? )
            // InternalModel.g:1639:2: ( rule__FieldType__FieldPredefinedTypeAssignment_3 )?
            {
             before(grammarAccess.getFieldTypeAccess().getFieldPredefinedTypeAssignment_3()); 
            // InternalModel.g:1640:2: ( rule__FieldType__FieldPredefinedTypeAssignment_3 )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==RULE_STRING) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // InternalModel.g:1640:3: rule__FieldType__FieldPredefinedTypeAssignment_3
                    {
                    pushFollow(FOLLOW_2);
                    rule__FieldType__FieldPredefinedTypeAssignment_3();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getFieldTypeAccess().getFieldPredefinedTypeAssignment_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FieldType__Group__3__Impl"


    // $ANTLR start "rule__FieldType__Group__4"
    // InternalModel.g:1648:1: rule__FieldType__Group__4 : rule__FieldType__Group__4__Impl ;
    public final void rule__FieldType__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:1652:1: ( rule__FieldType__Group__4__Impl )
            // InternalModel.g:1653:2: rule__FieldType__Group__4__Impl
            {
            pushFollow(FOLLOW_2);
            rule__FieldType__Group__4__Impl();

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
    // $ANTLR end "rule__FieldType__Group__4"


    // $ANTLR start "rule__FieldType__Group__4__Impl"
    // InternalModel.g:1659:1: rule__FieldType__Group__4__Impl : ( ';' ) ;
    public final void rule__FieldType__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:1663:1: ( ( ';' ) )
            // InternalModel.g:1664:1: ( ';' )
            {
            // InternalModel.g:1664:1: ( ';' )
            // InternalModel.g:1665:2: ';'
            {
             before(grammarAccess.getFieldTypeAccess().getSemicolonKeyword_4()); 
            match(input,12,FOLLOW_2); 
             after(grammarAccess.getFieldTypeAccess().getSemicolonKeyword_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FieldType__Group__4__Impl"


    // $ANTLR start "rule__FieldReference__Group__0"
    // InternalModel.g:1675:1: rule__FieldReference__Group__0 : rule__FieldReference__Group__0__Impl rule__FieldReference__Group__1 ;
    public final void rule__FieldReference__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:1679:1: ( rule__FieldReference__Group__0__Impl rule__FieldReference__Group__1 )
            // InternalModel.g:1680:2: rule__FieldReference__Group__0__Impl rule__FieldReference__Group__1
            {
            pushFollow(FOLLOW_8);
            rule__FieldReference__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__FieldReference__Group__1();

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
    // $ANTLR end "rule__FieldReference__Group__0"


    // $ANTLR start "rule__FieldReference__Group__0__Impl"
    // InternalModel.g:1687:1: rule__FieldReference__Group__0__Impl : ( ( rule__FieldReference__NameAssignment_0 ) ) ;
    public final void rule__FieldReference__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:1691:1: ( ( ( rule__FieldReference__NameAssignment_0 ) ) )
            // InternalModel.g:1692:1: ( ( rule__FieldReference__NameAssignment_0 ) )
            {
            // InternalModel.g:1692:1: ( ( rule__FieldReference__NameAssignment_0 ) )
            // InternalModel.g:1693:2: ( rule__FieldReference__NameAssignment_0 )
            {
             before(grammarAccess.getFieldReferenceAccess().getNameAssignment_0()); 
            // InternalModel.g:1694:2: ( rule__FieldReference__NameAssignment_0 )
            // InternalModel.g:1694:3: rule__FieldReference__NameAssignment_0
            {
            pushFollow(FOLLOW_2);
            rule__FieldReference__NameAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getFieldReferenceAccess().getNameAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FieldReference__Group__0__Impl"


    // $ANTLR start "rule__FieldReference__Group__1"
    // InternalModel.g:1702:1: rule__FieldReference__Group__1 : rule__FieldReference__Group__1__Impl rule__FieldReference__Group__2 ;
    public final void rule__FieldReference__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:1706:1: ( rule__FieldReference__Group__1__Impl rule__FieldReference__Group__2 )
            // InternalModel.g:1707:2: rule__FieldReference__Group__1__Impl rule__FieldReference__Group__2
            {
            pushFollow(FOLLOW_4);
            rule__FieldReference__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__FieldReference__Group__2();

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
    // $ANTLR end "rule__FieldReference__Group__1"


    // $ANTLR start "rule__FieldReference__Group__1__Impl"
    // InternalModel.g:1714:1: rule__FieldReference__Group__1__Impl : ( ':' ) ;
    public final void rule__FieldReference__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:1718:1: ( ( ':' ) )
            // InternalModel.g:1719:1: ( ':' )
            {
            // InternalModel.g:1719:1: ( ':' )
            // InternalModel.g:1720:2: ':'
            {
             before(grammarAccess.getFieldReferenceAccess().getColonKeyword_1()); 
            match(input,15,FOLLOW_2); 
             after(grammarAccess.getFieldReferenceAccess().getColonKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FieldReference__Group__1__Impl"


    // $ANTLR start "rule__FieldReference__Group__2"
    // InternalModel.g:1729:1: rule__FieldReference__Group__2 : rule__FieldReference__Group__2__Impl rule__FieldReference__Group__3 ;
    public final void rule__FieldReference__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:1733:1: ( rule__FieldReference__Group__2__Impl rule__FieldReference__Group__3 )
            // InternalModel.g:1734:2: rule__FieldReference__Group__2__Impl rule__FieldReference__Group__3
            {
            pushFollow(FOLLOW_21);
            rule__FieldReference__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__FieldReference__Group__3();

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
    // $ANTLR end "rule__FieldReference__Group__2"


    // $ANTLR start "rule__FieldReference__Group__2__Impl"
    // InternalModel.g:1741:1: rule__FieldReference__Group__2__Impl : ( ( rule__FieldReference__ReferenceTypeAssignment_2 ) ) ;
    public final void rule__FieldReference__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:1745:1: ( ( ( rule__FieldReference__ReferenceTypeAssignment_2 ) ) )
            // InternalModel.g:1746:1: ( ( rule__FieldReference__ReferenceTypeAssignment_2 ) )
            {
            // InternalModel.g:1746:1: ( ( rule__FieldReference__ReferenceTypeAssignment_2 ) )
            // InternalModel.g:1747:2: ( rule__FieldReference__ReferenceTypeAssignment_2 )
            {
             before(grammarAccess.getFieldReferenceAccess().getReferenceTypeAssignment_2()); 
            // InternalModel.g:1748:2: ( rule__FieldReference__ReferenceTypeAssignment_2 )
            // InternalModel.g:1748:3: rule__FieldReference__ReferenceTypeAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__FieldReference__ReferenceTypeAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getFieldReferenceAccess().getReferenceTypeAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FieldReference__Group__2__Impl"


    // $ANTLR start "rule__FieldReference__Group__3"
    // InternalModel.g:1756:1: rule__FieldReference__Group__3 : rule__FieldReference__Group__3__Impl rule__FieldReference__Group__4 ;
    public final void rule__FieldReference__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:1760:1: ( rule__FieldReference__Group__3__Impl rule__FieldReference__Group__4 )
            // InternalModel.g:1761:2: rule__FieldReference__Group__3__Impl rule__FieldReference__Group__4
            {
            pushFollow(FOLLOW_22);
            rule__FieldReference__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__FieldReference__Group__4();

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
    // $ANTLR end "rule__FieldReference__Group__3"


    // $ANTLR start "rule__FieldReference__Group__3__Impl"
    // InternalModel.g:1768:1: rule__FieldReference__Group__3__Impl : ( '[*]' ) ;
    public final void rule__FieldReference__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:1772:1: ( ( '[*]' ) )
            // InternalModel.g:1773:1: ( '[*]' )
            {
            // InternalModel.g:1773:1: ( '[*]' )
            // InternalModel.g:1774:2: '[*]'
            {
             before(grammarAccess.getFieldReferenceAccess().getLeftSquareBracketAsteriskRightSquareBracketKeyword_3()); 
            match(input,23,FOLLOW_2); 
             after(grammarAccess.getFieldReferenceAccess().getLeftSquareBracketAsteriskRightSquareBracketKeyword_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FieldReference__Group__3__Impl"


    // $ANTLR start "rule__FieldReference__Group__4"
    // InternalModel.g:1783:1: rule__FieldReference__Group__4 : rule__FieldReference__Group__4__Impl rule__FieldReference__Group__5 ;
    public final void rule__FieldReference__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:1787:1: ( rule__FieldReference__Group__4__Impl rule__FieldReference__Group__5 )
            // InternalModel.g:1788:2: rule__FieldReference__Group__4__Impl rule__FieldReference__Group__5
            {
            pushFollow(FOLLOW_4);
            rule__FieldReference__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__FieldReference__Group__5();

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
    // $ANTLR end "rule__FieldReference__Group__4"


    // $ANTLR start "rule__FieldReference__Group__4__Impl"
    // InternalModel.g:1795:1: rule__FieldReference__Group__4__Impl : ( 'to' ) ;
    public final void rule__FieldReference__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:1799:1: ( ( 'to' ) )
            // InternalModel.g:1800:1: ( 'to' )
            {
            // InternalModel.g:1800:1: ( 'to' )
            // InternalModel.g:1801:2: 'to'
            {
             before(grammarAccess.getFieldReferenceAccess().getToKeyword_4()); 
            match(input,24,FOLLOW_2); 
             after(grammarAccess.getFieldReferenceAccess().getToKeyword_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FieldReference__Group__4__Impl"


    // $ANTLR start "rule__FieldReference__Group__5"
    // InternalModel.g:1810:1: rule__FieldReference__Group__5 : rule__FieldReference__Group__5__Impl rule__FieldReference__Group__6 ;
    public final void rule__FieldReference__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:1814:1: ( rule__FieldReference__Group__5__Impl rule__FieldReference__Group__6 )
            // InternalModel.g:1815:2: rule__FieldReference__Group__5__Impl rule__FieldReference__Group__6
            {
            pushFollow(FOLLOW_23);
            rule__FieldReference__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__FieldReference__Group__6();

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
    // $ANTLR end "rule__FieldReference__Group__5"


    // $ANTLR start "rule__FieldReference__Group__5__Impl"
    // InternalModel.g:1822:1: rule__FieldReference__Group__5__Impl : ( ( rule__FieldReference__FieldReferenceEntityAssignment_5 ) ) ;
    public final void rule__FieldReference__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:1826:1: ( ( ( rule__FieldReference__FieldReferenceEntityAssignment_5 ) ) )
            // InternalModel.g:1827:1: ( ( rule__FieldReference__FieldReferenceEntityAssignment_5 ) )
            {
            // InternalModel.g:1827:1: ( ( rule__FieldReference__FieldReferenceEntityAssignment_5 ) )
            // InternalModel.g:1828:2: ( rule__FieldReference__FieldReferenceEntityAssignment_5 )
            {
             before(grammarAccess.getFieldReferenceAccess().getFieldReferenceEntityAssignment_5()); 
            // InternalModel.g:1829:2: ( rule__FieldReference__FieldReferenceEntityAssignment_5 )
            // InternalModel.g:1829:3: rule__FieldReference__FieldReferenceEntityAssignment_5
            {
            pushFollow(FOLLOW_2);
            rule__FieldReference__FieldReferenceEntityAssignment_5();

            state._fsp--;


            }

             after(grammarAccess.getFieldReferenceAccess().getFieldReferenceEntityAssignment_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FieldReference__Group__5__Impl"


    // $ANTLR start "rule__FieldReference__Group__6"
    // InternalModel.g:1837:1: rule__FieldReference__Group__6 : rule__FieldReference__Group__6__Impl rule__FieldReference__Group__7 ;
    public final void rule__FieldReference__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:1841:1: ( rule__FieldReference__Group__6__Impl rule__FieldReference__Group__7 )
            // InternalModel.g:1842:2: rule__FieldReference__Group__6__Impl rule__FieldReference__Group__7
            {
            pushFollow(FOLLOW_4);
            rule__FieldReference__Group__6__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__FieldReference__Group__7();

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
    // $ANTLR end "rule__FieldReference__Group__6"


    // $ANTLR start "rule__FieldReference__Group__6__Impl"
    // InternalModel.g:1849:1: rule__FieldReference__Group__6__Impl : ( 'on' ) ;
    public final void rule__FieldReference__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:1853:1: ( ( 'on' ) )
            // InternalModel.g:1854:1: ( 'on' )
            {
            // InternalModel.g:1854:1: ( 'on' )
            // InternalModel.g:1855:2: 'on'
            {
             before(grammarAccess.getFieldReferenceAccess().getOnKeyword_6()); 
            match(input,25,FOLLOW_2); 
             after(grammarAccess.getFieldReferenceAccess().getOnKeyword_6()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FieldReference__Group__6__Impl"


    // $ANTLR start "rule__FieldReference__Group__7"
    // InternalModel.g:1864:1: rule__FieldReference__Group__7 : rule__FieldReference__Group__7__Impl rule__FieldReference__Group__8 ;
    public final void rule__FieldReference__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:1868:1: ( rule__FieldReference__Group__7__Impl rule__FieldReference__Group__8 )
            // InternalModel.g:1869:2: rule__FieldReference__Group__7__Impl rule__FieldReference__Group__8
            {
            pushFollow(FOLLOW_24);
            rule__FieldReference__Group__7__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__FieldReference__Group__8();

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
    // $ANTLR end "rule__FieldReference__Group__7"


    // $ANTLR start "rule__FieldReference__Group__7__Impl"
    // InternalModel.g:1876:1: rule__FieldReference__Group__7__Impl : ( ( rule__FieldReference__FieldReferenceEntityLocalIdAssignment_7 ) ) ;
    public final void rule__FieldReference__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:1880:1: ( ( ( rule__FieldReference__FieldReferenceEntityLocalIdAssignment_7 ) ) )
            // InternalModel.g:1881:1: ( ( rule__FieldReference__FieldReferenceEntityLocalIdAssignment_7 ) )
            {
            // InternalModel.g:1881:1: ( ( rule__FieldReference__FieldReferenceEntityLocalIdAssignment_7 ) )
            // InternalModel.g:1882:2: ( rule__FieldReference__FieldReferenceEntityLocalIdAssignment_7 )
            {
             before(grammarAccess.getFieldReferenceAccess().getFieldReferenceEntityLocalIdAssignment_7()); 
            // InternalModel.g:1883:2: ( rule__FieldReference__FieldReferenceEntityLocalIdAssignment_7 )
            // InternalModel.g:1883:3: rule__FieldReference__FieldReferenceEntityLocalIdAssignment_7
            {
            pushFollow(FOLLOW_2);
            rule__FieldReference__FieldReferenceEntityLocalIdAssignment_7();

            state._fsp--;


            }

             after(grammarAccess.getFieldReferenceAccess().getFieldReferenceEntityLocalIdAssignment_7()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FieldReference__Group__7__Impl"


    // $ANTLR start "rule__FieldReference__Group__8"
    // InternalModel.g:1891:1: rule__FieldReference__Group__8 : rule__FieldReference__Group__8__Impl rule__FieldReference__Group__9 ;
    public final void rule__FieldReference__Group__8() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:1895:1: ( rule__FieldReference__Group__8__Impl rule__FieldReference__Group__9 )
            // InternalModel.g:1896:2: rule__FieldReference__Group__8__Impl rule__FieldReference__Group__9
            {
            pushFollow(FOLLOW_4);
            rule__FieldReference__Group__8__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__FieldReference__Group__9();

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
    // $ANTLR end "rule__FieldReference__Group__8"


    // $ANTLR start "rule__FieldReference__Group__8__Impl"
    // InternalModel.g:1903:1: rule__FieldReference__Group__8__Impl : ( '=' ) ;
    public final void rule__FieldReference__Group__8__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:1907:1: ( ( '=' ) )
            // InternalModel.g:1908:1: ( '=' )
            {
            // InternalModel.g:1908:1: ( '=' )
            // InternalModel.g:1909:2: '='
            {
             before(grammarAccess.getFieldReferenceAccess().getEqualsSignKeyword_8()); 
            match(input,26,FOLLOW_2); 
             after(grammarAccess.getFieldReferenceAccess().getEqualsSignKeyword_8()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FieldReference__Group__8__Impl"


    // $ANTLR start "rule__FieldReference__Group__9"
    // InternalModel.g:1918:1: rule__FieldReference__Group__9 : rule__FieldReference__Group__9__Impl rule__FieldReference__Group__10 ;
    public final void rule__FieldReference__Group__9() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:1922:1: ( rule__FieldReference__Group__9__Impl rule__FieldReference__Group__10 )
            // InternalModel.g:1923:2: rule__FieldReference__Group__9__Impl rule__FieldReference__Group__10
            {
            pushFollow(FOLLOW_5);
            rule__FieldReference__Group__9__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__FieldReference__Group__10();

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
    // $ANTLR end "rule__FieldReference__Group__9"


    // $ANTLR start "rule__FieldReference__Group__9__Impl"
    // InternalModel.g:1930:1: rule__FieldReference__Group__9__Impl : ( ( rule__FieldReference__FieldReferenceEntityForeignIdAssignment_9 ) ) ;
    public final void rule__FieldReference__Group__9__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:1934:1: ( ( ( rule__FieldReference__FieldReferenceEntityForeignIdAssignment_9 ) ) )
            // InternalModel.g:1935:1: ( ( rule__FieldReference__FieldReferenceEntityForeignIdAssignment_9 ) )
            {
            // InternalModel.g:1935:1: ( ( rule__FieldReference__FieldReferenceEntityForeignIdAssignment_9 ) )
            // InternalModel.g:1936:2: ( rule__FieldReference__FieldReferenceEntityForeignIdAssignment_9 )
            {
             before(grammarAccess.getFieldReferenceAccess().getFieldReferenceEntityForeignIdAssignment_9()); 
            // InternalModel.g:1937:2: ( rule__FieldReference__FieldReferenceEntityForeignIdAssignment_9 )
            // InternalModel.g:1937:3: rule__FieldReference__FieldReferenceEntityForeignIdAssignment_9
            {
            pushFollow(FOLLOW_2);
            rule__FieldReference__FieldReferenceEntityForeignIdAssignment_9();

            state._fsp--;


            }

             after(grammarAccess.getFieldReferenceAccess().getFieldReferenceEntityForeignIdAssignment_9()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FieldReference__Group__9__Impl"


    // $ANTLR start "rule__FieldReference__Group__10"
    // InternalModel.g:1945:1: rule__FieldReference__Group__10 : rule__FieldReference__Group__10__Impl ;
    public final void rule__FieldReference__Group__10() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:1949:1: ( rule__FieldReference__Group__10__Impl )
            // InternalModel.g:1950:2: rule__FieldReference__Group__10__Impl
            {
            pushFollow(FOLLOW_2);
            rule__FieldReference__Group__10__Impl();

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
    // $ANTLR end "rule__FieldReference__Group__10"


    // $ANTLR start "rule__FieldReference__Group__10__Impl"
    // InternalModel.g:1956:1: rule__FieldReference__Group__10__Impl : ( ';' ) ;
    public final void rule__FieldReference__Group__10__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:1960:1: ( ( ';' ) )
            // InternalModel.g:1961:1: ( ';' )
            {
            // InternalModel.g:1961:1: ( ';' )
            // InternalModel.g:1962:2: ';'
            {
             before(grammarAccess.getFieldReferenceAccess().getSemicolonKeyword_10()); 
            match(input,12,FOLLOW_2); 
             after(grammarAccess.getFieldReferenceAccess().getSemicolonKeyword_10()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FieldReference__Group__10__Impl"


    // $ANTLR start "rule__HdbDD__ElementsAssignment"
    // InternalModel.g:1972:1: rule__HdbDD__ElementsAssignment : ( ruleType ) ;
    public final void rule__HdbDD__ElementsAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:1976:1: ( ( ruleType ) )
            // InternalModel.g:1977:2: ( ruleType )
            {
            // InternalModel.g:1977:2: ( ruleType )
            // InternalModel.g:1978:3: ruleType
            {
             before(grammarAccess.getHdbDDAccess().getElementsTypeParserRuleCall_0()); 
            pushFollow(FOLLOW_2);
            ruleType();

            state._fsp--;

             after(grammarAccess.getHdbDDAccess().getElementsTypeParserRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__HdbDD__ElementsAssignment"


    // $ANTLR start "rule__Namespace__NameAssignment_1"
    // InternalModel.g:1987:1: rule__Namespace__NameAssignment_1 : ( ruleQualifiedName ) ;
    public final void rule__Namespace__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:1991:1: ( ( ruleQualifiedName ) )
            // InternalModel.g:1992:2: ( ruleQualifiedName )
            {
            // InternalModel.g:1992:2: ( ruleQualifiedName )
            // InternalModel.g:1993:3: ruleQualifiedName
            {
             before(grammarAccess.getNamespaceAccess().getNameQualifiedNameParserRuleCall_1_0()); 
            pushFollow(FOLLOW_2);
            ruleQualifiedName();

            state._fsp--;

             after(grammarAccess.getNamespaceAccess().getNameQualifiedNameParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Namespace__NameAssignment_1"


    // $ANTLR start "rule__Schema__NameAssignment_2"
    // InternalModel.g:2002:1: rule__Schema__NameAssignment_2 : ( RULE_ID ) ;
    public final void rule__Schema__NameAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:2006:1: ( ( RULE_ID ) )
            // InternalModel.g:2007:2: ( RULE_ID )
            {
            // InternalModel.g:2007:2: ( RULE_ID )
            // InternalModel.g:2008:3: RULE_ID
            {
             before(grammarAccess.getSchemaAccess().getNameIDTerminalRuleCall_2_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getSchemaAccess().getNameIDTerminalRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Schema__NameAssignment_2"


    // $ANTLR start "rule__Context__NameAssignment_1"
    // InternalModel.g:2017:1: rule__Context__NameAssignment_1 : ( RULE_ID ) ;
    public final void rule__Context__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:2021:1: ( ( RULE_ID ) )
            // InternalModel.g:2022:2: ( RULE_ID )
            {
            // InternalModel.g:2022:2: ( RULE_ID )
            // InternalModel.g:2023:3: RULE_ID
            {
             before(grammarAccess.getContextAccess().getNameIDTerminalRuleCall_1_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getContextAccess().getNameIDTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Context__NameAssignment_1"


    // $ANTLR start "rule__Context__TypesAssignment_3"
    // InternalModel.g:2032:1: rule__Context__TypesAssignment_3 : ( ruleTypeDefinition ) ;
    public final void rule__Context__TypesAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:2036:1: ( ( ruleTypeDefinition ) )
            // InternalModel.g:2037:2: ( ruleTypeDefinition )
            {
            // InternalModel.g:2037:2: ( ruleTypeDefinition )
            // InternalModel.g:2038:3: ruleTypeDefinition
            {
             before(grammarAccess.getContextAccess().getTypesTypeDefinitionParserRuleCall_3_0()); 
            pushFollow(FOLLOW_2);
            ruleTypeDefinition();

            state._fsp--;

             after(grammarAccess.getContextAccess().getTypesTypeDefinitionParserRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Context__TypesAssignment_3"


    // $ANTLR start "rule__Context__EntitiesAssignment_4"
    // InternalModel.g:2047:1: rule__Context__EntitiesAssignment_4 : ( ruleEntity ) ;
    public final void rule__Context__EntitiesAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:2051:1: ( ( ruleEntity ) )
            // InternalModel.g:2052:2: ( ruleEntity )
            {
            // InternalModel.g:2052:2: ( ruleEntity )
            // InternalModel.g:2053:3: ruleEntity
            {
             before(grammarAccess.getContextAccess().getEntitiesEntityParserRuleCall_4_0()); 
            pushFollow(FOLLOW_2);
            ruleEntity();

            state._fsp--;

             after(grammarAccess.getContextAccess().getEntitiesEntityParserRuleCall_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Context__EntitiesAssignment_4"


    // $ANTLR start "rule__TypeDefinition__NameAssignment_1"
    // InternalModel.g:2062:1: rule__TypeDefinition__NameAssignment_1 : ( RULE_ID ) ;
    public final void rule__TypeDefinition__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:2066:1: ( ( RULE_ID ) )
            // InternalModel.g:2067:2: ( RULE_ID )
            {
            // InternalModel.g:2067:2: ( RULE_ID )
            // InternalModel.g:2068:3: RULE_ID
            {
             before(grammarAccess.getTypeDefinitionAccess().getNameIDTerminalRuleCall_1_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getTypeDefinitionAccess().getNameIDTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TypeDefinition__NameAssignment_1"


    // $ANTLR start "rule__TypeDefinition__FieldTypeAssignment_3"
    // InternalModel.g:2077:1: rule__TypeDefinition__FieldTypeAssignment_3 : ( RULE_ID ) ;
    public final void rule__TypeDefinition__FieldTypeAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:2081:1: ( ( RULE_ID ) )
            // InternalModel.g:2082:2: ( RULE_ID )
            {
            // InternalModel.g:2082:2: ( RULE_ID )
            // InternalModel.g:2083:3: RULE_ID
            {
             before(grammarAccess.getTypeDefinitionAccess().getFieldTypeIDTerminalRuleCall_3_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getTypeDefinitionAccess().getFieldTypeIDTerminalRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TypeDefinition__FieldTypeAssignment_3"


    // $ANTLR start "rule__TypeDefinition__FieldLengthAssignment_4_1"
    // InternalModel.g:2092:1: rule__TypeDefinition__FieldLengthAssignment_4_1 : ( RULE_INT ) ;
    public final void rule__TypeDefinition__FieldLengthAssignment_4_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:2096:1: ( ( RULE_INT ) )
            // InternalModel.g:2097:2: ( RULE_INT )
            {
            // InternalModel.g:2097:2: ( RULE_INT )
            // InternalModel.g:2098:3: RULE_INT
            {
             before(grammarAccess.getTypeDefinitionAccess().getFieldLengthINTTerminalRuleCall_4_1_0()); 
            match(input,RULE_INT,FOLLOW_2); 
             after(grammarAccess.getTypeDefinitionAccess().getFieldLengthINTTerminalRuleCall_4_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TypeDefinition__FieldLengthAssignment_4_1"


    // $ANTLR start "rule__Entity__NameAssignment_1"
    // InternalModel.g:2107:1: rule__Entity__NameAssignment_1 : ( RULE_ID ) ;
    public final void rule__Entity__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:2111:1: ( ( RULE_ID ) )
            // InternalModel.g:2112:2: ( RULE_ID )
            {
            // InternalModel.g:2112:2: ( RULE_ID )
            // InternalModel.g:2113:3: RULE_ID
            {
             before(grammarAccess.getEntityAccess().getNameIDTerminalRuleCall_1_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getEntityAccess().getNameIDTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Entity__NameAssignment_1"


    // $ANTLR start "rule__Entity__FieldsAssignment_3"
    // InternalModel.g:2122:1: rule__Entity__FieldsAssignment_3 : ( ruleField ) ;
    public final void rule__Entity__FieldsAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:2126:1: ( ( ruleField ) )
            // InternalModel.g:2127:2: ( ruleField )
            {
            // InternalModel.g:2127:2: ( ruleField )
            // InternalModel.g:2128:3: ruleField
            {
             before(grammarAccess.getEntityAccess().getFieldsFieldParserRuleCall_3_0()); 
            pushFollow(FOLLOW_2);
            ruleField();

            state._fsp--;

             after(grammarAccess.getEntityAccess().getFieldsFieldParserRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Entity__FieldsAssignment_3"


    // $ANTLR start "rule__FieldPrimitive__KeyAssignment_0"
    // InternalModel.g:2137:1: rule__FieldPrimitive__KeyAssignment_0 : ( ( 'key' ) ) ;
    public final void rule__FieldPrimitive__KeyAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:2141:1: ( ( ( 'key' ) ) )
            // InternalModel.g:2142:2: ( ( 'key' ) )
            {
            // InternalModel.g:2142:2: ( ( 'key' ) )
            // InternalModel.g:2143:3: ( 'key' )
            {
             before(grammarAccess.getFieldPrimitiveAccess().getKeyKeyKeyword_0_0()); 
            // InternalModel.g:2144:3: ( 'key' )
            // InternalModel.g:2145:4: 'key'
            {
             before(grammarAccess.getFieldPrimitiveAccess().getKeyKeyKeyword_0_0()); 
            match(input,27,FOLLOW_2); 
             after(grammarAccess.getFieldPrimitiveAccess().getKeyKeyKeyword_0_0()); 

            }

             after(grammarAccess.getFieldPrimitiveAccess().getKeyKeyKeyword_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FieldPrimitive__KeyAssignment_0"


    // $ANTLR start "rule__FieldPrimitive__NameAssignment_1"
    // InternalModel.g:2156:1: rule__FieldPrimitive__NameAssignment_1 : ( RULE_ID ) ;
    public final void rule__FieldPrimitive__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:2160:1: ( ( RULE_ID ) )
            // InternalModel.g:2161:2: ( RULE_ID )
            {
            // InternalModel.g:2161:2: ( RULE_ID )
            // InternalModel.g:2162:3: RULE_ID
            {
             before(grammarAccess.getFieldPrimitiveAccess().getNameIDTerminalRuleCall_1_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getFieldPrimitiveAccess().getNameIDTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FieldPrimitive__NameAssignment_1"


    // $ANTLR start "rule__FieldPrimitive__FieldTypeAssignment_3"
    // InternalModel.g:2171:1: rule__FieldPrimitive__FieldTypeAssignment_3 : ( RULE_ID ) ;
    public final void rule__FieldPrimitive__FieldTypeAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:2175:1: ( ( RULE_ID ) )
            // InternalModel.g:2176:2: ( RULE_ID )
            {
            // InternalModel.g:2176:2: ( RULE_ID )
            // InternalModel.g:2177:3: RULE_ID
            {
             before(grammarAccess.getFieldPrimitiveAccess().getFieldTypeIDTerminalRuleCall_3_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getFieldPrimitiveAccess().getFieldTypeIDTerminalRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FieldPrimitive__FieldTypeAssignment_3"


    // $ANTLR start "rule__FieldPrimitive__FieldLengthAssignment_4_1"
    // InternalModel.g:2186:1: rule__FieldPrimitive__FieldLengthAssignment_4_1 : ( RULE_INT ) ;
    public final void rule__FieldPrimitive__FieldLengthAssignment_4_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:2190:1: ( ( RULE_INT ) )
            // InternalModel.g:2191:2: ( RULE_INT )
            {
            // InternalModel.g:2191:2: ( RULE_INT )
            // InternalModel.g:2192:3: RULE_INT
            {
             before(grammarAccess.getFieldPrimitiveAccess().getFieldLengthINTTerminalRuleCall_4_1_0()); 
            match(input,RULE_INT,FOLLOW_2); 
             after(grammarAccess.getFieldPrimitiveAccess().getFieldLengthINTTerminalRuleCall_4_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FieldPrimitive__FieldLengthAssignment_4_1"


    // $ANTLR start "rule__FieldPrimitive__FieldPredefinedTypeAssignment_5"
    // InternalModel.g:2201:1: rule__FieldPrimitive__FieldPredefinedTypeAssignment_5 : ( RULE_STRING ) ;
    public final void rule__FieldPrimitive__FieldPredefinedTypeAssignment_5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:2205:1: ( ( RULE_STRING ) )
            // InternalModel.g:2206:2: ( RULE_STRING )
            {
            // InternalModel.g:2206:2: ( RULE_STRING )
            // InternalModel.g:2207:3: RULE_STRING
            {
             before(grammarAccess.getFieldPrimitiveAccess().getFieldPredefinedTypeSTRINGTerminalRuleCall_5_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getFieldPrimitiveAccess().getFieldPredefinedTypeSTRINGTerminalRuleCall_5_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FieldPrimitive__FieldPredefinedTypeAssignment_5"


    // $ANTLR start "rule__FieldType__KeyAssignment_0"
    // InternalModel.g:2216:1: rule__FieldType__KeyAssignment_0 : ( ( 'key' ) ) ;
    public final void rule__FieldType__KeyAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:2220:1: ( ( ( 'key' ) ) )
            // InternalModel.g:2221:2: ( ( 'key' ) )
            {
            // InternalModel.g:2221:2: ( ( 'key' ) )
            // InternalModel.g:2222:3: ( 'key' )
            {
             before(grammarAccess.getFieldTypeAccess().getKeyKeyKeyword_0_0()); 
            // InternalModel.g:2223:3: ( 'key' )
            // InternalModel.g:2224:4: 'key'
            {
             before(grammarAccess.getFieldTypeAccess().getKeyKeyKeyword_0_0()); 
            match(input,27,FOLLOW_2); 
             after(grammarAccess.getFieldTypeAccess().getKeyKeyKeyword_0_0()); 

            }

             after(grammarAccess.getFieldTypeAccess().getKeyKeyKeyword_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FieldType__KeyAssignment_0"


    // $ANTLR start "rule__FieldType__NameAssignment_1"
    // InternalModel.g:2235:1: rule__FieldType__NameAssignment_1 : ( RULE_ID ) ;
    public final void rule__FieldType__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:2239:1: ( ( RULE_ID ) )
            // InternalModel.g:2240:2: ( RULE_ID )
            {
            // InternalModel.g:2240:2: ( RULE_ID )
            // InternalModel.g:2241:3: RULE_ID
            {
             before(grammarAccess.getFieldTypeAccess().getNameIDTerminalRuleCall_1_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getFieldTypeAccess().getNameIDTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FieldType__NameAssignment_1"


    // $ANTLR start "rule__FieldType__FieldPredefinedTypeAssignment_3"
    // InternalModel.g:2250:1: rule__FieldType__FieldPredefinedTypeAssignment_3 : ( RULE_STRING ) ;
    public final void rule__FieldType__FieldPredefinedTypeAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:2254:1: ( ( RULE_STRING ) )
            // InternalModel.g:2255:2: ( RULE_STRING )
            {
            // InternalModel.g:2255:2: ( RULE_STRING )
            // InternalModel.g:2256:3: RULE_STRING
            {
             before(grammarAccess.getFieldTypeAccess().getFieldPredefinedTypeSTRINGTerminalRuleCall_3_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getFieldTypeAccess().getFieldPredefinedTypeSTRINGTerminalRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FieldType__FieldPredefinedTypeAssignment_3"


    // $ANTLR start "rule__FieldReference__NameAssignment_0"
    // InternalModel.g:2265:1: rule__FieldReference__NameAssignment_0 : ( RULE_ID ) ;
    public final void rule__FieldReference__NameAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:2269:1: ( ( RULE_ID ) )
            // InternalModel.g:2270:2: ( RULE_ID )
            {
            // InternalModel.g:2270:2: ( RULE_ID )
            // InternalModel.g:2271:3: RULE_ID
            {
             before(grammarAccess.getFieldReferenceAccess().getNameIDTerminalRuleCall_0_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getFieldReferenceAccess().getNameIDTerminalRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FieldReference__NameAssignment_0"


    // $ANTLR start "rule__FieldReference__ReferenceTypeAssignment_2"
    // InternalModel.g:2280:1: rule__FieldReference__ReferenceTypeAssignment_2 : ( RULE_ID ) ;
    public final void rule__FieldReference__ReferenceTypeAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:2284:1: ( ( RULE_ID ) )
            // InternalModel.g:2285:2: ( RULE_ID )
            {
            // InternalModel.g:2285:2: ( RULE_ID )
            // InternalModel.g:2286:3: RULE_ID
            {
             before(grammarAccess.getFieldReferenceAccess().getReferenceTypeIDTerminalRuleCall_2_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getFieldReferenceAccess().getReferenceTypeIDTerminalRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FieldReference__ReferenceTypeAssignment_2"


    // $ANTLR start "rule__FieldReference__FieldReferenceEntityAssignment_5"
    // InternalModel.g:2295:1: rule__FieldReference__FieldReferenceEntityAssignment_5 : ( RULE_ID ) ;
    public final void rule__FieldReference__FieldReferenceEntityAssignment_5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:2299:1: ( ( RULE_ID ) )
            // InternalModel.g:2300:2: ( RULE_ID )
            {
            // InternalModel.g:2300:2: ( RULE_ID )
            // InternalModel.g:2301:3: RULE_ID
            {
             before(grammarAccess.getFieldReferenceAccess().getFieldReferenceEntityIDTerminalRuleCall_5_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getFieldReferenceAccess().getFieldReferenceEntityIDTerminalRuleCall_5_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FieldReference__FieldReferenceEntityAssignment_5"


    // $ANTLR start "rule__FieldReference__FieldReferenceEntityLocalIdAssignment_7"
    // InternalModel.g:2310:1: rule__FieldReference__FieldReferenceEntityLocalIdAssignment_7 : ( ruleQualifiedName ) ;
    public final void rule__FieldReference__FieldReferenceEntityLocalIdAssignment_7() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:2314:1: ( ( ruleQualifiedName ) )
            // InternalModel.g:2315:2: ( ruleQualifiedName )
            {
            // InternalModel.g:2315:2: ( ruleQualifiedName )
            // InternalModel.g:2316:3: ruleQualifiedName
            {
             before(grammarAccess.getFieldReferenceAccess().getFieldReferenceEntityLocalIdQualifiedNameParserRuleCall_7_0()); 
            pushFollow(FOLLOW_2);
            ruleQualifiedName();

            state._fsp--;

             after(grammarAccess.getFieldReferenceAccess().getFieldReferenceEntityLocalIdQualifiedNameParserRuleCall_7_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FieldReference__FieldReferenceEntityLocalIdAssignment_7"


    // $ANTLR start "rule__FieldReference__FieldReferenceEntityForeignIdAssignment_9"
    // InternalModel.g:2325:1: rule__FieldReference__FieldReferenceEntityForeignIdAssignment_9 : ( RULE_ID ) ;
    public final void rule__FieldReference__FieldReferenceEntityForeignIdAssignment_9() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel.g:2329:1: ( ( RULE_ID ) )
            // InternalModel.g:2330:2: ( RULE_ID )
            {
            // InternalModel.g:2330:2: ( RULE_ID )
            // InternalModel.g:2331:3: RULE_ID
            {
             before(grammarAccess.getFieldReferenceAccess().getFieldReferenceEntityForeignIdIDTerminalRuleCall_9_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getFieldReferenceAccess().getFieldReferenceEntityForeignIdIDTerminalRuleCall_9_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FieldReference__FieldReferenceEntityForeignIdAssignment_9"

    // Delegated rules


    protected DFA3 dfa3 = new DFA3(this);
    static final String dfa_1s = "\12\uffff";
    static final String dfa_2s = "\2\4\2\17\2\4\1\uffff\1\6\2\uffff";
    static final String dfa_3s = "\1\33\1\4\2\17\2\14\1\uffff\1\27\2\uffff";
    static final String dfa_4s = "\6\uffff\1\3\1\uffff\1\1\1\2";
    static final String dfa_5s = "\12\uffff}>";
    static final String[] dfa_6s = {
            "\1\2\26\uffff\1\1",
            "\1\3",
            "\1\4",
            "\1\5",
            "\1\7\1\uffff\1\6\5\uffff\1\6",
            "\1\10\1\uffff\1\6\5\uffff\1\6",
            "",
            "\1\10\5\uffff\1\10\7\uffff\1\10\2\uffff\1\11",
            "",
            ""
    };

    static final short[] dfa_1 = DFA.unpackEncodedString(dfa_1s);
    static final char[] dfa_2 = DFA.unpackEncodedStringToUnsignedChars(dfa_2s);
    static final char[] dfa_3 = DFA.unpackEncodedStringToUnsignedChars(dfa_3s);
    static final short[] dfa_4 = DFA.unpackEncodedString(dfa_4s);
    static final short[] dfa_5 = DFA.unpackEncodedString(dfa_5s);
    static final short[][] dfa_6 = unpackEncodedStringArray(dfa_6s);

    class DFA3 extends DFA {

        public DFA3(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 3;
            this.eot = dfa_1;
            this.eof = dfa_1;
            this.min = dfa_2;
            this.max = dfa_3;
            this.accept = dfa_4;
            this.special = dfa_5;
            this.transition = dfa_6;
        }
        public String getDescription() {
            return "379:1: rule__Field__Alternatives : ( ( ruleFieldPrimitive ) | ( ruleFieldReference ) | ( ruleFieldType ) );";
        }
    }
 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000014802L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x00000000004C0000L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000400002L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000000101000L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000008040010L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000008000012L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000008000010L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000000000101040L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000000000001040L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x0000000004000000L});

}