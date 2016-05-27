package org.becausecucumber.eclipse.plugin.ui.contentassist.antlr.internal; 

import java.io.InputStream;
import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.DFA;
import org.becausecucumber.eclipse.plugin.services.CucumberGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalCucumberParser extends AbstractInternalContentAssistParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_WORD", "RULE_INT", "RULE_STRING", "RULE_PLACEHOLDER", "RULE_STEP_KEYWORD", "RULE_TAGNAME", "RULE_EOL", "RULE_TABLE_ROW", "RULE_DOC_STRING", "RULE_NL", "RULE_SL_COMMENT", "RULE_WS", "'Feature:'", "'Background:'", "'Scenario:'", "'Scenario Outline:'", "'Examples:'"
    };
    public static final int RULE_DOC_STRING=12;
    public static final int RULE_WORD=4;
    public static final int RULE_STRING=6;
    public static final int RULE_SL_COMMENT=14;
    public static final int T__19=19;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int EOF=-1;
    public static final int RULE_EOL=10;
    public static final int RULE_WS=15;
    public static final int RULE_TAGNAME=9;
    public static final int RULE_PLACEHOLDER=7;
    public static final int RULE_TABLE_ROW=11;
    public static final int RULE_INT=5;
    public static final int RULE_STEP_KEYWORD=8;
    public static final int RULE_NL=13;
    public static final int T__20=20;

    // delegates
    // delegators


        public InternalCucumberParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalCucumberParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalCucumberParser.tokenNames; }
    public String getGrammarFileName() { return "InternalCucumber.g"; }


     
     	private CucumberGrammarAccess grammarAccess;
     	
        public void setGrammarAccess(CucumberGrammarAccess grammarAccess) {
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




    // $ANTLR start "entryRuleFeature"
    // InternalCucumber.g:60:1: entryRuleFeature : ruleFeature EOF ;
    public final void entryRuleFeature() throws RecognitionException {
        try {
            // InternalCucumber.g:61:1: ( ruleFeature EOF )
            // InternalCucumber.g:62:1: ruleFeature EOF
            {
             before(grammarAccess.getFeatureRule()); 
            pushFollow(FOLLOW_1);
            ruleFeature();

            state._fsp--;

             after(grammarAccess.getFeatureRule()); 
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
    // $ANTLR end "entryRuleFeature"


    // $ANTLR start "ruleFeature"
    // InternalCucumber.g:69:1: ruleFeature : ( ( rule__Feature__Group__0 ) ) ;
    public final void ruleFeature() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:73:2: ( ( ( rule__Feature__Group__0 ) ) )
            // InternalCucumber.g:74:1: ( ( rule__Feature__Group__0 ) )
            {
            // InternalCucumber.g:74:1: ( ( rule__Feature__Group__0 ) )
            // InternalCucumber.g:75:1: ( rule__Feature__Group__0 )
            {
             before(grammarAccess.getFeatureAccess().getGroup()); 
            // InternalCucumber.g:76:1: ( rule__Feature__Group__0 )
            // InternalCucumber.g:76:2: rule__Feature__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Feature__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getFeatureAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleFeature"


    // $ANTLR start "entryRuleBackground"
    // InternalCucumber.g:88:1: entryRuleBackground : ruleBackground EOF ;
    public final void entryRuleBackground() throws RecognitionException {
        try {
            // InternalCucumber.g:89:1: ( ruleBackground EOF )
            // InternalCucumber.g:90:1: ruleBackground EOF
            {
             before(grammarAccess.getBackgroundRule()); 
            pushFollow(FOLLOW_1);
            ruleBackground();

            state._fsp--;

             after(grammarAccess.getBackgroundRule()); 
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
    // $ANTLR end "entryRuleBackground"


    // $ANTLR start "ruleBackground"
    // InternalCucumber.g:97:1: ruleBackground : ( ( rule__Background__Group__0 ) ) ;
    public final void ruleBackground() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:101:2: ( ( ( rule__Background__Group__0 ) ) )
            // InternalCucumber.g:102:1: ( ( rule__Background__Group__0 ) )
            {
            // InternalCucumber.g:102:1: ( ( rule__Background__Group__0 ) )
            // InternalCucumber.g:103:1: ( rule__Background__Group__0 )
            {
             before(grammarAccess.getBackgroundAccess().getGroup()); 
            // InternalCucumber.g:104:1: ( rule__Background__Group__0 )
            // InternalCucumber.g:104:2: rule__Background__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Background__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getBackgroundAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleBackground"


    // $ANTLR start "entryRuleScenario"
    // InternalCucumber.g:116:1: entryRuleScenario : ruleScenario EOF ;
    public final void entryRuleScenario() throws RecognitionException {
        try {
            // InternalCucumber.g:117:1: ( ruleScenario EOF )
            // InternalCucumber.g:118:1: ruleScenario EOF
            {
             before(grammarAccess.getScenarioRule()); 
            pushFollow(FOLLOW_1);
            ruleScenario();

            state._fsp--;

             after(grammarAccess.getScenarioRule()); 
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
    // $ANTLR end "entryRuleScenario"


    // $ANTLR start "ruleScenario"
    // InternalCucumber.g:125:1: ruleScenario : ( ( rule__Scenario__Group__0 ) ) ;
    public final void ruleScenario() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:129:2: ( ( ( rule__Scenario__Group__0 ) ) )
            // InternalCucumber.g:130:1: ( ( rule__Scenario__Group__0 ) )
            {
            // InternalCucumber.g:130:1: ( ( rule__Scenario__Group__0 ) )
            // InternalCucumber.g:131:1: ( rule__Scenario__Group__0 )
            {
             before(grammarAccess.getScenarioAccess().getGroup()); 
            // InternalCucumber.g:132:1: ( rule__Scenario__Group__0 )
            // InternalCucumber.g:132:2: rule__Scenario__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Scenario__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getScenarioAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleScenario"


    // $ANTLR start "entryRuleScenarioOutline"
    // InternalCucumber.g:144:1: entryRuleScenarioOutline : ruleScenarioOutline EOF ;
    public final void entryRuleScenarioOutline() throws RecognitionException {
        try {
            // InternalCucumber.g:145:1: ( ruleScenarioOutline EOF )
            // InternalCucumber.g:146:1: ruleScenarioOutline EOF
            {
             before(grammarAccess.getScenarioOutlineRule()); 
            pushFollow(FOLLOW_1);
            ruleScenarioOutline();

            state._fsp--;

             after(grammarAccess.getScenarioOutlineRule()); 
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
    // $ANTLR end "entryRuleScenarioOutline"


    // $ANTLR start "ruleScenarioOutline"
    // InternalCucumber.g:153:1: ruleScenarioOutline : ( ( rule__ScenarioOutline__Group__0 ) ) ;
    public final void ruleScenarioOutline() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:157:2: ( ( ( rule__ScenarioOutline__Group__0 ) ) )
            // InternalCucumber.g:158:1: ( ( rule__ScenarioOutline__Group__0 ) )
            {
            // InternalCucumber.g:158:1: ( ( rule__ScenarioOutline__Group__0 ) )
            // InternalCucumber.g:159:1: ( rule__ScenarioOutline__Group__0 )
            {
             before(grammarAccess.getScenarioOutlineAccess().getGroup()); 
            // InternalCucumber.g:160:1: ( rule__ScenarioOutline__Group__0 )
            // InternalCucumber.g:160:2: rule__ScenarioOutline__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__ScenarioOutline__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getScenarioOutlineAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleScenarioOutline"


    // $ANTLR start "entryRuleStep"
    // InternalCucumber.g:172:1: entryRuleStep : ruleStep EOF ;
    public final void entryRuleStep() throws RecognitionException {
        try {
            // InternalCucumber.g:173:1: ( ruleStep EOF )
            // InternalCucumber.g:174:1: ruleStep EOF
            {
             before(grammarAccess.getStepRule()); 
            pushFollow(FOLLOW_1);
            ruleStep();

            state._fsp--;

             after(grammarAccess.getStepRule()); 
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
    // $ANTLR end "entryRuleStep"


    // $ANTLR start "ruleStep"
    // InternalCucumber.g:181:1: ruleStep : ( ( rule__Step__Group__0 ) ) ;
    public final void ruleStep() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:185:2: ( ( ( rule__Step__Group__0 ) ) )
            // InternalCucumber.g:186:1: ( ( rule__Step__Group__0 ) )
            {
            // InternalCucumber.g:186:1: ( ( rule__Step__Group__0 ) )
            // InternalCucumber.g:187:1: ( rule__Step__Group__0 )
            {
             before(grammarAccess.getStepAccess().getGroup()); 
            // InternalCucumber.g:188:1: ( rule__Step__Group__0 )
            // InternalCucumber.g:188:2: rule__Step__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Step__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getStepAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleStep"


    // $ANTLR start "entryRuleExamples"
    // InternalCucumber.g:200:1: entryRuleExamples : ruleExamples EOF ;
    public final void entryRuleExamples() throws RecognitionException {
        try {
            // InternalCucumber.g:201:1: ( ruleExamples EOF )
            // InternalCucumber.g:202:1: ruleExamples EOF
            {
             before(grammarAccess.getExamplesRule()); 
            pushFollow(FOLLOW_1);
            ruleExamples();

            state._fsp--;

             after(grammarAccess.getExamplesRule()); 
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
    // $ANTLR end "entryRuleExamples"


    // $ANTLR start "ruleExamples"
    // InternalCucumber.g:209:1: ruleExamples : ( ( rule__Examples__Group__0 ) ) ;
    public final void ruleExamples() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:213:2: ( ( ( rule__Examples__Group__0 ) ) )
            // InternalCucumber.g:214:1: ( ( rule__Examples__Group__0 ) )
            {
            // InternalCucumber.g:214:1: ( ( rule__Examples__Group__0 ) )
            // InternalCucumber.g:215:1: ( rule__Examples__Group__0 )
            {
             before(grammarAccess.getExamplesAccess().getGroup()); 
            // InternalCucumber.g:216:1: ( rule__Examples__Group__0 )
            // InternalCucumber.g:216:2: rule__Examples__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Examples__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getExamplesAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleExamples"


    // $ANTLR start "entryRuleTable"
    // InternalCucumber.g:228:1: entryRuleTable : ruleTable EOF ;
    public final void entryRuleTable() throws RecognitionException {
        try {
            // InternalCucumber.g:229:1: ( ruleTable EOF )
            // InternalCucumber.g:230:1: ruleTable EOF
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
    // InternalCucumber.g:237:1: ruleTable : ( ( rule__Table__Group__0 ) ) ;
    public final void ruleTable() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:241:2: ( ( ( rule__Table__Group__0 ) ) )
            // InternalCucumber.g:242:1: ( ( rule__Table__Group__0 ) )
            {
            // InternalCucumber.g:242:1: ( ( rule__Table__Group__0 ) )
            // InternalCucumber.g:243:1: ( rule__Table__Group__0 )
            {
             before(grammarAccess.getTableAccess().getGroup()); 
            // InternalCucumber.g:244:1: ( rule__Table__Group__0 )
            // InternalCucumber.g:244:2: rule__Table__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Table__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getTableAccess().getGroup()); 

            }


            }

        }
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


    // $ANTLR start "entryRuleDocString"
    // InternalCucumber.g:256:1: entryRuleDocString : ruleDocString EOF ;
    public final void entryRuleDocString() throws RecognitionException {
        try {
            // InternalCucumber.g:257:1: ( ruleDocString EOF )
            // InternalCucumber.g:258:1: ruleDocString EOF
            {
             before(grammarAccess.getDocStringRule()); 
            pushFollow(FOLLOW_1);
            ruleDocString();

            state._fsp--;

             after(grammarAccess.getDocStringRule()); 
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
    // $ANTLR end "entryRuleDocString"


    // $ANTLR start "ruleDocString"
    // InternalCucumber.g:265:1: ruleDocString : ( ( rule__DocString__Group__0 ) ) ;
    public final void ruleDocString() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:269:2: ( ( ( rule__DocString__Group__0 ) ) )
            // InternalCucumber.g:270:1: ( ( rule__DocString__Group__0 ) )
            {
            // InternalCucumber.g:270:1: ( ( rule__DocString__Group__0 ) )
            // InternalCucumber.g:271:1: ( rule__DocString__Group__0 )
            {
             before(grammarAccess.getDocStringAccess().getGroup()); 
            // InternalCucumber.g:272:1: ( rule__DocString__Group__0 )
            // InternalCucumber.g:272:2: rule__DocString__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__DocString__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getDocStringAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleDocString"


    // $ANTLR start "entryRuleTitle"
    // InternalCucumber.g:284:1: entryRuleTitle : ruleTitle EOF ;
    public final void entryRuleTitle() throws RecognitionException {
        try {
            // InternalCucumber.g:285:1: ( ruleTitle EOF )
            // InternalCucumber.g:286:1: ruleTitle EOF
            {
             before(grammarAccess.getTitleRule()); 
            pushFollow(FOLLOW_1);
            ruleTitle();

            state._fsp--;

             after(grammarAccess.getTitleRule()); 
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
    // $ANTLR end "entryRuleTitle"


    // $ANTLR start "ruleTitle"
    // InternalCucumber.g:293:1: ruleTitle : ( ( rule__Title__Group__0 ) ) ;
    public final void ruleTitle() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:297:2: ( ( ( rule__Title__Group__0 ) ) )
            // InternalCucumber.g:298:1: ( ( rule__Title__Group__0 ) )
            {
            // InternalCucumber.g:298:1: ( ( rule__Title__Group__0 ) )
            // InternalCucumber.g:299:1: ( rule__Title__Group__0 )
            {
             before(grammarAccess.getTitleAccess().getGroup()); 
            // InternalCucumber.g:300:1: ( rule__Title__Group__0 )
            // InternalCucumber.g:300:2: rule__Title__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Title__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getTitleAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleTitle"


    // $ANTLR start "entryRuleNarrative"
    // InternalCucumber.g:312:1: entryRuleNarrative : ruleNarrative EOF ;
    public final void entryRuleNarrative() throws RecognitionException {
        try {
            // InternalCucumber.g:313:1: ( ruleNarrative EOF )
            // InternalCucumber.g:314:1: ruleNarrative EOF
            {
             before(grammarAccess.getNarrativeRule()); 
            pushFollow(FOLLOW_1);
            ruleNarrative();

            state._fsp--;

             after(grammarAccess.getNarrativeRule()); 
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
    // $ANTLR end "entryRuleNarrative"


    // $ANTLR start "ruleNarrative"
    // InternalCucumber.g:321:1: ruleNarrative : ( ( ( rule__Narrative__Group__0 ) ) ( ( rule__Narrative__Group__0 )* ) ) ;
    public final void ruleNarrative() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:325:2: ( ( ( ( rule__Narrative__Group__0 ) ) ( ( rule__Narrative__Group__0 )* ) ) )
            // InternalCucumber.g:326:1: ( ( ( rule__Narrative__Group__0 ) ) ( ( rule__Narrative__Group__0 )* ) )
            {
            // InternalCucumber.g:326:1: ( ( ( rule__Narrative__Group__0 ) ) ( ( rule__Narrative__Group__0 )* ) )
            // InternalCucumber.g:327:1: ( ( rule__Narrative__Group__0 ) ) ( ( rule__Narrative__Group__0 )* )
            {
            // InternalCucumber.g:327:1: ( ( rule__Narrative__Group__0 ) )
            // InternalCucumber.g:328:1: ( rule__Narrative__Group__0 )
            {
             before(grammarAccess.getNarrativeAccess().getGroup()); 
            // InternalCucumber.g:329:1: ( rule__Narrative__Group__0 )
            // InternalCucumber.g:329:2: rule__Narrative__Group__0
            {
            pushFollow(FOLLOW_3);
            rule__Narrative__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getNarrativeAccess().getGroup()); 

            }

            // InternalCucumber.g:332:1: ( ( rule__Narrative__Group__0 )* )
            // InternalCucumber.g:333:1: ( rule__Narrative__Group__0 )*
            {
             before(grammarAccess.getNarrativeAccess().getGroup()); 
            // InternalCucumber.g:334:1: ( rule__Narrative__Group__0 )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>=RULE_WORD && LA1_0<=RULE_PLACEHOLDER)) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // InternalCucumber.g:334:2: rule__Narrative__Group__0
            	    {
            	    pushFollow(FOLLOW_3);
            	    rule__Narrative__Group__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

             after(grammarAccess.getNarrativeAccess().getGroup()); 

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleNarrative"


    // $ANTLR start "entryRuleStepDescription"
    // InternalCucumber.g:347:1: entryRuleStepDescription : ruleStepDescription EOF ;
    public final void entryRuleStepDescription() throws RecognitionException {
        try {
            // InternalCucumber.g:348:1: ( ruleStepDescription EOF )
            // InternalCucumber.g:349:1: ruleStepDescription EOF
            {
             before(grammarAccess.getStepDescriptionRule()); 
            pushFollow(FOLLOW_1);
            ruleStepDescription();

            state._fsp--;

             after(grammarAccess.getStepDescriptionRule()); 
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
    // $ANTLR end "entryRuleStepDescription"


    // $ANTLR start "ruleStepDescription"
    // InternalCucumber.g:356:1: ruleStepDescription : ( ( ( rule__StepDescription__Alternatives ) ) ( ( rule__StepDescription__Alternatives )* ) ) ;
    public final void ruleStepDescription() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:360:2: ( ( ( ( rule__StepDescription__Alternatives ) ) ( ( rule__StepDescription__Alternatives )* ) ) )
            // InternalCucumber.g:361:1: ( ( ( rule__StepDescription__Alternatives ) ) ( ( rule__StepDescription__Alternatives )* ) )
            {
            // InternalCucumber.g:361:1: ( ( ( rule__StepDescription__Alternatives ) ) ( ( rule__StepDescription__Alternatives )* ) )
            // InternalCucumber.g:362:1: ( ( rule__StepDescription__Alternatives ) ) ( ( rule__StepDescription__Alternatives )* )
            {
            // InternalCucumber.g:362:1: ( ( rule__StepDescription__Alternatives ) )
            // InternalCucumber.g:363:1: ( rule__StepDescription__Alternatives )
            {
             before(grammarAccess.getStepDescriptionAccess().getAlternatives()); 
            // InternalCucumber.g:364:1: ( rule__StepDescription__Alternatives )
            // InternalCucumber.g:364:2: rule__StepDescription__Alternatives
            {
            pushFollow(FOLLOW_4);
            rule__StepDescription__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getStepDescriptionAccess().getAlternatives()); 

            }

            // InternalCucumber.g:367:1: ( ( rule__StepDescription__Alternatives )* )
            // InternalCucumber.g:368:1: ( rule__StepDescription__Alternatives )*
            {
             before(grammarAccess.getStepDescriptionAccess().getAlternatives()); 
            // InternalCucumber.g:369:1: ( rule__StepDescription__Alternatives )*
            loop2:
            do {
                int alt2=2;
                alt2 = dfa2.predict(input);
                switch (alt2) {
            	case 1 :
            	    // InternalCucumber.g:369:2: rule__StepDescription__Alternatives
            	    {
            	    pushFollow(FOLLOW_4);
            	    rule__StepDescription__Alternatives();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);

             after(grammarAccess.getStepDescriptionAccess().getAlternatives()); 

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleStepDescription"


    // $ANTLR start "entryRuleTag"
    // InternalCucumber.g:382:1: entryRuleTag : ruleTag EOF ;
    public final void entryRuleTag() throws RecognitionException {
        try {
            // InternalCucumber.g:383:1: ( ruleTag EOF )
            // InternalCucumber.g:384:1: ruleTag EOF
            {
             before(grammarAccess.getTagRule()); 
            pushFollow(FOLLOW_1);
            ruleTag();

            state._fsp--;

             after(grammarAccess.getTagRule()); 
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
    // $ANTLR end "entryRuleTag"


    // $ANTLR start "ruleTag"
    // InternalCucumber.g:391:1: ruleTag : ( ( rule__Tag__Group__0 ) ) ;
    public final void ruleTag() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:395:2: ( ( ( rule__Tag__Group__0 ) ) )
            // InternalCucumber.g:396:1: ( ( rule__Tag__Group__0 ) )
            {
            // InternalCucumber.g:396:1: ( ( rule__Tag__Group__0 ) )
            // InternalCucumber.g:397:1: ( rule__Tag__Group__0 )
            {
             before(grammarAccess.getTagAccess().getGroup()); 
            // InternalCucumber.g:398:1: ( rule__Tag__Group__0 )
            // InternalCucumber.g:398:2: rule__Tag__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Tag__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getTagAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleTag"


    // $ANTLR start "rule__Feature__ScenariosAlternatives_6_0"
    // InternalCucumber.g:410:1: rule__Feature__ScenariosAlternatives_6_0 : ( ( ruleScenario ) | ( ruleScenarioOutline ) );
    public final void rule__Feature__ScenariosAlternatives_6_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:414:1: ( ( ruleScenario ) | ( ruleScenarioOutline ) )
            int alt3=2;
            alt3 = dfa3.predict(input);
            switch (alt3) {
                case 1 :
                    // InternalCucumber.g:415:1: ( ruleScenario )
                    {
                    // InternalCucumber.g:415:1: ( ruleScenario )
                    // InternalCucumber.g:416:1: ruleScenario
                    {
                     before(grammarAccess.getFeatureAccess().getScenariosScenarioParserRuleCall_6_0_0()); 
                    pushFollow(FOLLOW_2);
                    ruleScenario();

                    state._fsp--;

                     after(grammarAccess.getFeatureAccess().getScenariosScenarioParserRuleCall_6_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalCucumber.g:421:6: ( ruleScenarioOutline )
                    {
                    // InternalCucumber.g:421:6: ( ruleScenarioOutline )
                    // InternalCucumber.g:422:1: ruleScenarioOutline
                    {
                     before(grammarAccess.getFeatureAccess().getScenariosScenarioOutlineParserRuleCall_6_0_1()); 
                    pushFollow(FOLLOW_2);
                    ruleScenarioOutline();

                    state._fsp--;

                     after(grammarAccess.getFeatureAccess().getScenariosScenarioOutlineParserRuleCall_6_0_1()); 

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
    // $ANTLR end "rule__Feature__ScenariosAlternatives_6_0"


    // $ANTLR start "rule__Title__Alternatives_0"
    // InternalCucumber.g:432:1: rule__Title__Alternatives_0 : ( ( RULE_WORD ) | ( RULE_INT ) | ( RULE_STRING ) | ( RULE_PLACEHOLDER ) );
    public final void rule__Title__Alternatives_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:436:1: ( ( RULE_WORD ) | ( RULE_INT ) | ( RULE_STRING ) | ( RULE_PLACEHOLDER ) )
            int alt4=4;
            switch ( input.LA(1) ) {
            case RULE_WORD:
                {
                alt4=1;
                }
                break;
            case RULE_INT:
                {
                alt4=2;
                }
                break;
            case RULE_STRING:
                {
                alt4=3;
                }
                break;
            case RULE_PLACEHOLDER:
                {
                alt4=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }

            switch (alt4) {
                case 1 :
                    // InternalCucumber.g:437:1: ( RULE_WORD )
                    {
                    // InternalCucumber.g:437:1: ( RULE_WORD )
                    // InternalCucumber.g:438:1: RULE_WORD
                    {
                     before(grammarAccess.getTitleAccess().getWORDTerminalRuleCall_0_0()); 
                    match(input,RULE_WORD,FOLLOW_2); 
                     after(grammarAccess.getTitleAccess().getWORDTerminalRuleCall_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalCucumber.g:443:6: ( RULE_INT )
                    {
                    // InternalCucumber.g:443:6: ( RULE_INT )
                    // InternalCucumber.g:444:1: RULE_INT
                    {
                     before(grammarAccess.getTitleAccess().getINTTerminalRuleCall_0_1()); 
                    match(input,RULE_INT,FOLLOW_2); 
                     after(grammarAccess.getTitleAccess().getINTTerminalRuleCall_0_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalCucumber.g:449:6: ( RULE_STRING )
                    {
                    // InternalCucumber.g:449:6: ( RULE_STRING )
                    // InternalCucumber.g:450:1: RULE_STRING
                    {
                     before(grammarAccess.getTitleAccess().getSTRINGTerminalRuleCall_0_2()); 
                    match(input,RULE_STRING,FOLLOW_2); 
                     after(grammarAccess.getTitleAccess().getSTRINGTerminalRuleCall_0_2()); 

                    }


                    }
                    break;
                case 4 :
                    // InternalCucumber.g:455:6: ( RULE_PLACEHOLDER )
                    {
                    // InternalCucumber.g:455:6: ( RULE_PLACEHOLDER )
                    // InternalCucumber.g:456:1: RULE_PLACEHOLDER
                    {
                     before(grammarAccess.getTitleAccess().getPLACEHOLDERTerminalRuleCall_0_3()); 
                    match(input,RULE_PLACEHOLDER,FOLLOW_2); 
                     after(grammarAccess.getTitleAccess().getPLACEHOLDERTerminalRuleCall_0_3()); 

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
    // $ANTLR end "rule__Title__Alternatives_0"


    // $ANTLR start "rule__Title__Alternatives_1"
    // InternalCucumber.g:466:1: rule__Title__Alternatives_1 : ( ( RULE_WORD ) | ( RULE_INT ) | ( RULE_STRING ) | ( RULE_PLACEHOLDER ) | ( RULE_STEP_KEYWORD ) | ( RULE_TAGNAME ) );
    public final void rule__Title__Alternatives_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:470:1: ( ( RULE_WORD ) | ( RULE_INT ) | ( RULE_STRING ) | ( RULE_PLACEHOLDER ) | ( RULE_STEP_KEYWORD ) | ( RULE_TAGNAME ) )
            int alt5=6;
            switch ( input.LA(1) ) {
            case RULE_WORD:
                {
                alt5=1;
                }
                break;
            case RULE_INT:
                {
                alt5=2;
                }
                break;
            case RULE_STRING:
                {
                alt5=3;
                }
                break;
            case RULE_PLACEHOLDER:
                {
                alt5=4;
                }
                break;
            case RULE_STEP_KEYWORD:
                {
                alt5=5;
                }
                break;
            case RULE_TAGNAME:
                {
                alt5=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }

            switch (alt5) {
                case 1 :
                    // InternalCucumber.g:471:1: ( RULE_WORD )
                    {
                    // InternalCucumber.g:471:1: ( RULE_WORD )
                    // InternalCucumber.g:472:1: RULE_WORD
                    {
                     before(grammarAccess.getTitleAccess().getWORDTerminalRuleCall_1_0()); 
                    match(input,RULE_WORD,FOLLOW_2); 
                     after(grammarAccess.getTitleAccess().getWORDTerminalRuleCall_1_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalCucumber.g:477:6: ( RULE_INT )
                    {
                    // InternalCucumber.g:477:6: ( RULE_INT )
                    // InternalCucumber.g:478:1: RULE_INT
                    {
                     before(grammarAccess.getTitleAccess().getINTTerminalRuleCall_1_1()); 
                    match(input,RULE_INT,FOLLOW_2); 
                     after(grammarAccess.getTitleAccess().getINTTerminalRuleCall_1_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalCucumber.g:483:6: ( RULE_STRING )
                    {
                    // InternalCucumber.g:483:6: ( RULE_STRING )
                    // InternalCucumber.g:484:1: RULE_STRING
                    {
                     before(grammarAccess.getTitleAccess().getSTRINGTerminalRuleCall_1_2()); 
                    match(input,RULE_STRING,FOLLOW_2); 
                     after(grammarAccess.getTitleAccess().getSTRINGTerminalRuleCall_1_2()); 

                    }


                    }
                    break;
                case 4 :
                    // InternalCucumber.g:489:6: ( RULE_PLACEHOLDER )
                    {
                    // InternalCucumber.g:489:6: ( RULE_PLACEHOLDER )
                    // InternalCucumber.g:490:1: RULE_PLACEHOLDER
                    {
                     before(grammarAccess.getTitleAccess().getPLACEHOLDERTerminalRuleCall_1_3()); 
                    match(input,RULE_PLACEHOLDER,FOLLOW_2); 
                     after(grammarAccess.getTitleAccess().getPLACEHOLDERTerminalRuleCall_1_3()); 

                    }


                    }
                    break;
                case 5 :
                    // InternalCucumber.g:495:6: ( RULE_STEP_KEYWORD )
                    {
                    // InternalCucumber.g:495:6: ( RULE_STEP_KEYWORD )
                    // InternalCucumber.g:496:1: RULE_STEP_KEYWORD
                    {
                     before(grammarAccess.getTitleAccess().getSTEP_KEYWORDTerminalRuleCall_1_4()); 
                    match(input,RULE_STEP_KEYWORD,FOLLOW_2); 
                     after(grammarAccess.getTitleAccess().getSTEP_KEYWORDTerminalRuleCall_1_4()); 

                    }


                    }
                    break;
                case 6 :
                    // InternalCucumber.g:501:6: ( RULE_TAGNAME )
                    {
                    // InternalCucumber.g:501:6: ( RULE_TAGNAME )
                    // InternalCucumber.g:502:1: RULE_TAGNAME
                    {
                     before(grammarAccess.getTitleAccess().getTAGNAMETerminalRuleCall_1_5()); 
                    match(input,RULE_TAGNAME,FOLLOW_2); 
                     after(grammarAccess.getTitleAccess().getTAGNAMETerminalRuleCall_1_5()); 

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
    // $ANTLR end "rule__Title__Alternatives_1"


    // $ANTLR start "rule__Narrative__Alternatives_0"
    // InternalCucumber.g:512:1: rule__Narrative__Alternatives_0 : ( ( RULE_WORD ) | ( RULE_INT ) | ( RULE_STRING ) | ( RULE_PLACEHOLDER ) );
    public final void rule__Narrative__Alternatives_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:516:1: ( ( RULE_WORD ) | ( RULE_INT ) | ( RULE_STRING ) | ( RULE_PLACEHOLDER ) )
            int alt6=4;
            switch ( input.LA(1) ) {
            case RULE_WORD:
                {
                alt6=1;
                }
                break;
            case RULE_INT:
                {
                alt6=2;
                }
                break;
            case RULE_STRING:
                {
                alt6=3;
                }
                break;
            case RULE_PLACEHOLDER:
                {
                alt6=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }

            switch (alt6) {
                case 1 :
                    // InternalCucumber.g:517:1: ( RULE_WORD )
                    {
                    // InternalCucumber.g:517:1: ( RULE_WORD )
                    // InternalCucumber.g:518:1: RULE_WORD
                    {
                     before(grammarAccess.getNarrativeAccess().getWORDTerminalRuleCall_0_0()); 
                    match(input,RULE_WORD,FOLLOW_2); 
                     after(grammarAccess.getNarrativeAccess().getWORDTerminalRuleCall_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalCucumber.g:523:6: ( RULE_INT )
                    {
                    // InternalCucumber.g:523:6: ( RULE_INT )
                    // InternalCucumber.g:524:1: RULE_INT
                    {
                     before(grammarAccess.getNarrativeAccess().getINTTerminalRuleCall_0_1()); 
                    match(input,RULE_INT,FOLLOW_2); 
                     after(grammarAccess.getNarrativeAccess().getINTTerminalRuleCall_0_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalCucumber.g:529:6: ( RULE_STRING )
                    {
                    // InternalCucumber.g:529:6: ( RULE_STRING )
                    // InternalCucumber.g:530:1: RULE_STRING
                    {
                     before(grammarAccess.getNarrativeAccess().getSTRINGTerminalRuleCall_0_2()); 
                    match(input,RULE_STRING,FOLLOW_2); 
                     after(grammarAccess.getNarrativeAccess().getSTRINGTerminalRuleCall_0_2()); 

                    }


                    }
                    break;
                case 4 :
                    // InternalCucumber.g:535:6: ( RULE_PLACEHOLDER )
                    {
                    // InternalCucumber.g:535:6: ( RULE_PLACEHOLDER )
                    // InternalCucumber.g:536:1: RULE_PLACEHOLDER
                    {
                     before(grammarAccess.getNarrativeAccess().getPLACEHOLDERTerminalRuleCall_0_3()); 
                    match(input,RULE_PLACEHOLDER,FOLLOW_2); 
                     after(grammarAccess.getNarrativeAccess().getPLACEHOLDERTerminalRuleCall_0_3()); 

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
    // $ANTLR end "rule__Narrative__Alternatives_0"


    // $ANTLR start "rule__Narrative__Alternatives_1"
    // InternalCucumber.g:546:1: rule__Narrative__Alternatives_1 : ( ( RULE_WORD ) | ( RULE_INT ) | ( RULE_STRING ) | ( RULE_PLACEHOLDER ) | ( RULE_STEP_KEYWORD ) | ( RULE_TAGNAME ) );
    public final void rule__Narrative__Alternatives_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:550:1: ( ( RULE_WORD ) | ( RULE_INT ) | ( RULE_STRING ) | ( RULE_PLACEHOLDER ) | ( RULE_STEP_KEYWORD ) | ( RULE_TAGNAME ) )
            int alt7=6;
            switch ( input.LA(1) ) {
            case RULE_WORD:
                {
                alt7=1;
                }
                break;
            case RULE_INT:
                {
                alt7=2;
                }
                break;
            case RULE_STRING:
                {
                alt7=3;
                }
                break;
            case RULE_PLACEHOLDER:
                {
                alt7=4;
                }
                break;
            case RULE_STEP_KEYWORD:
                {
                alt7=5;
                }
                break;
            case RULE_TAGNAME:
                {
                alt7=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }

            switch (alt7) {
                case 1 :
                    // InternalCucumber.g:551:1: ( RULE_WORD )
                    {
                    // InternalCucumber.g:551:1: ( RULE_WORD )
                    // InternalCucumber.g:552:1: RULE_WORD
                    {
                     before(grammarAccess.getNarrativeAccess().getWORDTerminalRuleCall_1_0()); 
                    match(input,RULE_WORD,FOLLOW_2); 
                     after(grammarAccess.getNarrativeAccess().getWORDTerminalRuleCall_1_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalCucumber.g:557:6: ( RULE_INT )
                    {
                    // InternalCucumber.g:557:6: ( RULE_INT )
                    // InternalCucumber.g:558:1: RULE_INT
                    {
                     before(grammarAccess.getNarrativeAccess().getINTTerminalRuleCall_1_1()); 
                    match(input,RULE_INT,FOLLOW_2); 
                     after(grammarAccess.getNarrativeAccess().getINTTerminalRuleCall_1_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalCucumber.g:563:6: ( RULE_STRING )
                    {
                    // InternalCucumber.g:563:6: ( RULE_STRING )
                    // InternalCucumber.g:564:1: RULE_STRING
                    {
                     before(grammarAccess.getNarrativeAccess().getSTRINGTerminalRuleCall_1_2()); 
                    match(input,RULE_STRING,FOLLOW_2); 
                     after(grammarAccess.getNarrativeAccess().getSTRINGTerminalRuleCall_1_2()); 

                    }


                    }
                    break;
                case 4 :
                    // InternalCucumber.g:569:6: ( RULE_PLACEHOLDER )
                    {
                    // InternalCucumber.g:569:6: ( RULE_PLACEHOLDER )
                    // InternalCucumber.g:570:1: RULE_PLACEHOLDER
                    {
                     before(grammarAccess.getNarrativeAccess().getPLACEHOLDERTerminalRuleCall_1_3()); 
                    match(input,RULE_PLACEHOLDER,FOLLOW_2); 
                     after(grammarAccess.getNarrativeAccess().getPLACEHOLDERTerminalRuleCall_1_3()); 

                    }


                    }
                    break;
                case 5 :
                    // InternalCucumber.g:575:6: ( RULE_STEP_KEYWORD )
                    {
                    // InternalCucumber.g:575:6: ( RULE_STEP_KEYWORD )
                    // InternalCucumber.g:576:1: RULE_STEP_KEYWORD
                    {
                     before(grammarAccess.getNarrativeAccess().getSTEP_KEYWORDTerminalRuleCall_1_4()); 
                    match(input,RULE_STEP_KEYWORD,FOLLOW_2); 
                     after(grammarAccess.getNarrativeAccess().getSTEP_KEYWORDTerminalRuleCall_1_4()); 

                    }


                    }
                    break;
                case 6 :
                    // InternalCucumber.g:581:6: ( RULE_TAGNAME )
                    {
                    // InternalCucumber.g:581:6: ( RULE_TAGNAME )
                    // InternalCucumber.g:582:1: RULE_TAGNAME
                    {
                     before(grammarAccess.getNarrativeAccess().getTAGNAMETerminalRuleCall_1_5()); 
                    match(input,RULE_TAGNAME,FOLLOW_2); 
                     after(grammarAccess.getNarrativeAccess().getTAGNAMETerminalRuleCall_1_5()); 

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
    // $ANTLR end "rule__Narrative__Alternatives_1"


    // $ANTLR start "rule__StepDescription__Alternatives"
    // InternalCucumber.g:592:1: rule__StepDescription__Alternatives : ( ( RULE_WORD ) | ( RULE_INT ) | ( RULE_STRING ) | ( RULE_PLACEHOLDER ) | ( RULE_STEP_KEYWORD ) | ( RULE_TAGNAME ) );
    public final void rule__StepDescription__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:596:1: ( ( RULE_WORD ) | ( RULE_INT ) | ( RULE_STRING ) | ( RULE_PLACEHOLDER ) | ( RULE_STEP_KEYWORD ) | ( RULE_TAGNAME ) )
            int alt8=6;
            switch ( input.LA(1) ) {
            case RULE_WORD:
                {
                alt8=1;
                }
                break;
            case RULE_INT:
                {
                alt8=2;
                }
                break;
            case RULE_STRING:
                {
                alt8=3;
                }
                break;
            case RULE_PLACEHOLDER:
                {
                alt8=4;
                }
                break;
            case RULE_STEP_KEYWORD:
                {
                alt8=5;
                }
                break;
            case RULE_TAGNAME:
                {
                alt8=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }

            switch (alt8) {
                case 1 :
                    // InternalCucumber.g:597:1: ( RULE_WORD )
                    {
                    // InternalCucumber.g:597:1: ( RULE_WORD )
                    // InternalCucumber.g:598:1: RULE_WORD
                    {
                     before(grammarAccess.getStepDescriptionAccess().getWORDTerminalRuleCall_0()); 
                    match(input,RULE_WORD,FOLLOW_2); 
                     after(grammarAccess.getStepDescriptionAccess().getWORDTerminalRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalCucumber.g:603:6: ( RULE_INT )
                    {
                    // InternalCucumber.g:603:6: ( RULE_INT )
                    // InternalCucumber.g:604:1: RULE_INT
                    {
                     before(grammarAccess.getStepDescriptionAccess().getINTTerminalRuleCall_1()); 
                    match(input,RULE_INT,FOLLOW_2); 
                     after(grammarAccess.getStepDescriptionAccess().getINTTerminalRuleCall_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalCucumber.g:609:6: ( RULE_STRING )
                    {
                    // InternalCucumber.g:609:6: ( RULE_STRING )
                    // InternalCucumber.g:610:1: RULE_STRING
                    {
                     before(grammarAccess.getStepDescriptionAccess().getSTRINGTerminalRuleCall_2()); 
                    match(input,RULE_STRING,FOLLOW_2); 
                     after(grammarAccess.getStepDescriptionAccess().getSTRINGTerminalRuleCall_2()); 

                    }


                    }
                    break;
                case 4 :
                    // InternalCucumber.g:615:6: ( RULE_PLACEHOLDER )
                    {
                    // InternalCucumber.g:615:6: ( RULE_PLACEHOLDER )
                    // InternalCucumber.g:616:1: RULE_PLACEHOLDER
                    {
                     before(grammarAccess.getStepDescriptionAccess().getPLACEHOLDERTerminalRuleCall_3()); 
                    match(input,RULE_PLACEHOLDER,FOLLOW_2); 
                     after(grammarAccess.getStepDescriptionAccess().getPLACEHOLDERTerminalRuleCall_3()); 

                    }


                    }
                    break;
                case 5 :
                    // InternalCucumber.g:621:6: ( RULE_STEP_KEYWORD )
                    {
                    // InternalCucumber.g:621:6: ( RULE_STEP_KEYWORD )
                    // InternalCucumber.g:622:1: RULE_STEP_KEYWORD
                    {
                     before(grammarAccess.getStepDescriptionAccess().getSTEP_KEYWORDTerminalRuleCall_4()); 
                    match(input,RULE_STEP_KEYWORD,FOLLOW_2); 
                     after(grammarAccess.getStepDescriptionAccess().getSTEP_KEYWORDTerminalRuleCall_4()); 

                    }


                    }
                    break;
                case 6 :
                    // InternalCucumber.g:627:6: ( RULE_TAGNAME )
                    {
                    // InternalCucumber.g:627:6: ( RULE_TAGNAME )
                    // InternalCucumber.g:628:1: RULE_TAGNAME
                    {
                     before(grammarAccess.getStepDescriptionAccess().getTAGNAMETerminalRuleCall_5()); 
                    match(input,RULE_TAGNAME,FOLLOW_2); 
                     after(grammarAccess.getStepDescriptionAccess().getTAGNAMETerminalRuleCall_5()); 

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
    // $ANTLR end "rule__StepDescription__Alternatives"


    // $ANTLR start "rule__Feature__Group__0"
    // InternalCucumber.g:640:1: rule__Feature__Group__0 : rule__Feature__Group__0__Impl rule__Feature__Group__1 ;
    public final void rule__Feature__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:644:1: ( rule__Feature__Group__0__Impl rule__Feature__Group__1 )
            // InternalCucumber.g:645:2: rule__Feature__Group__0__Impl rule__Feature__Group__1
            {
            pushFollow(FOLLOW_5);
            rule__Feature__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Feature__Group__1();

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
    // $ANTLR end "rule__Feature__Group__0"


    // $ANTLR start "rule__Feature__Group__0__Impl"
    // InternalCucumber.g:652:1: rule__Feature__Group__0__Impl : ( ( rule__Feature__TagsAssignment_0 )* ) ;
    public final void rule__Feature__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:656:1: ( ( ( rule__Feature__TagsAssignment_0 )* ) )
            // InternalCucumber.g:657:1: ( ( rule__Feature__TagsAssignment_0 )* )
            {
            // InternalCucumber.g:657:1: ( ( rule__Feature__TagsAssignment_0 )* )
            // InternalCucumber.g:658:1: ( rule__Feature__TagsAssignment_0 )*
            {
             before(grammarAccess.getFeatureAccess().getTagsAssignment_0()); 
            // InternalCucumber.g:659:1: ( rule__Feature__TagsAssignment_0 )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==RULE_TAGNAME) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // InternalCucumber.g:659:2: rule__Feature__TagsAssignment_0
            	    {
            	    pushFollow(FOLLOW_6);
            	    rule__Feature__TagsAssignment_0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);

             after(grammarAccess.getFeatureAccess().getTagsAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Feature__Group__0__Impl"


    // $ANTLR start "rule__Feature__Group__1"
    // InternalCucumber.g:669:1: rule__Feature__Group__1 : rule__Feature__Group__1__Impl rule__Feature__Group__2 ;
    public final void rule__Feature__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:673:1: ( rule__Feature__Group__1__Impl rule__Feature__Group__2 )
            // InternalCucumber.g:674:2: rule__Feature__Group__1__Impl rule__Feature__Group__2
            {
            pushFollow(FOLLOW_7);
            rule__Feature__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Feature__Group__2();

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
    // $ANTLR end "rule__Feature__Group__1"


    // $ANTLR start "rule__Feature__Group__1__Impl"
    // InternalCucumber.g:681:1: rule__Feature__Group__1__Impl : ( 'Feature:' ) ;
    public final void rule__Feature__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:685:1: ( ( 'Feature:' ) )
            // InternalCucumber.g:686:1: ( 'Feature:' )
            {
            // InternalCucumber.g:686:1: ( 'Feature:' )
            // InternalCucumber.g:687:1: 'Feature:'
            {
             before(grammarAccess.getFeatureAccess().getFeatureKeyword_1()); 
            match(input,16,FOLLOW_2); 
             after(grammarAccess.getFeatureAccess().getFeatureKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Feature__Group__1__Impl"


    // $ANTLR start "rule__Feature__Group__2"
    // InternalCucumber.g:700:1: rule__Feature__Group__2 : rule__Feature__Group__2__Impl rule__Feature__Group__3 ;
    public final void rule__Feature__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:704:1: ( rule__Feature__Group__2__Impl rule__Feature__Group__3 )
            // InternalCucumber.g:705:2: rule__Feature__Group__2__Impl rule__Feature__Group__3
            {
            pushFollow(FOLLOW_8);
            rule__Feature__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Feature__Group__3();

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
    // $ANTLR end "rule__Feature__Group__2"


    // $ANTLR start "rule__Feature__Group__2__Impl"
    // InternalCucumber.g:712:1: rule__Feature__Group__2__Impl : ( ( rule__Feature__TitleAssignment_2 ) ) ;
    public final void rule__Feature__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:716:1: ( ( ( rule__Feature__TitleAssignment_2 ) ) )
            // InternalCucumber.g:717:1: ( ( rule__Feature__TitleAssignment_2 ) )
            {
            // InternalCucumber.g:717:1: ( ( rule__Feature__TitleAssignment_2 ) )
            // InternalCucumber.g:718:1: ( rule__Feature__TitleAssignment_2 )
            {
             before(grammarAccess.getFeatureAccess().getTitleAssignment_2()); 
            // InternalCucumber.g:719:1: ( rule__Feature__TitleAssignment_2 )
            // InternalCucumber.g:719:2: rule__Feature__TitleAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__Feature__TitleAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getFeatureAccess().getTitleAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Feature__Group__2__Impl"


    // $ANTLR start "rule__Feature__Group__3"
    // InternalCucumber.g:729:1: rule__Feature__Group__3 : rule__Feature__Group__3__Impl rule__Feature__Group__4 ;
    public final void rule__Feature__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:733:1: ( rule__Feature__Group__3__Impl rule__Feature__Group__4 )
            // InternalCucumber.g:734:2: rule__Feature__Group__3__Impl rule__Feature__Group__4
            {
            pushFollow(FOLLOW_9);
            rule__Feature__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Feature__Group__4();

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
    // $ANTLR end "rule__Feature__Group__3"


    // $ANTLR start "rule__Feature__Group__3__Impl"
    // InternalCucumber.g:741:1: rule__Feature__Group__3__Impl : ( ( ( RULE_EOL ) ) ( ( RULE_EOL )* ) ) ;
    public final void rule__Feature__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:745:1: ( ( ( ( RULE_EOL ) ) ( ( RULE_EOL )* ) ) )
            // InternalCucumber.g:746:1: ( ( ( RULE_EOL ) ) ( ( RULE_EOL )* ) )
            {
            // InternalCucumber.g:746:1: ( ( ( RULE_EOL ) ) ( ( RULE_EOL )* ) )
            // InternalCucumber.g:747:1: ( ( RULE_EOL ) ) ( ( RULE_EOL )* )
            {
            // InternalCucumber.g:747:1: ( ( RULE_EOL ) )
            // InternalCucumber.g:748:1: ( RULE_EOL )
            {
             before(grammarAccess.getFeatureAccess().getEOLTerminalRuleCall_3()); 
            // InternalCucumber.g:749:1: ( RULE_EOL )
            // InternalCucumber.g:749:3: RULE_EOL
            {
            match(input,RULE_EOL,FOLLOW_10); 

            }

             after(grammarAccess.getFeatureAccess().getEOLTerminalRuleCall_3()); 

            }

            // InternalCucumber.g:752:1: ( ( RULE_EOL )* )
            // InternalCucumber.g:753:1: ( RULE_EOL )*
            {
             before(grammarAccess.getFeatureAccess().getEOLTerminalRuleCall_3()); 
            // InternalCucumber.g:754:1: ( RULE_EOL )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==RULE_EOL) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // InternalCucumber.g:754:3: RULE_EOL
            	    {
            	    match(input,RULE_EOL,FOLLOW_10); 

            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);

             after(grammarAccess.getFeatureAccess().getEOLTerminalRuleCall_3()); 

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Feature__Group__3__Impl"


    // $ANTLR start "rule__Feature__Group__4"
    // InternalCucumber.g:765:1: rule__Feature__Group__4 : rule__Feature__Group__4__Impl rule__Feature__Group__5 ;
    public final void rule__Feature__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:769:1: ( rule__Feature__Group__4__Impl rule__Feature__Group__5 )
            // InternalCucumber.g:770:2: rule__Feature__Group__4__Impl rule__Feature__Group__5
            {
            pushFollow(FOLLOW_9);
            rule__Feature__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Feature__Group__5();

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
    // $ANTLR end "rule__Feature__Group__4"


    // $ANTLR start "rule__Feature__Group__4__Impl"
    // InternalCucumber.g:777:1: rule__Feature__Group__4__Impl : ( ( rule__Feature__NarrativeAssignment_4 )? ) ;
    public final void rule__Feature__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:781:1: ( ( ( rule__Feature__NarrativeAssignment_4 )? ) )
            // InternalCucumber.g:782:1: ( ( rule__Feature__NarrativeAssignment_4 )? )
            {
            // InternalCucumber.g:782:1: ( ( rule__Feature__NarrativeAssignment_4 )? )
            // InternalCucumber.g:783:1: ( rule__Feature__NarrativeAssignment_4 )?
            {
             before(grammarAccess.getFeatureAccess().getNarrativeAssignment_4()); 
            // InternalCucumber.g:784:1: ( rule__Feature__NarrativeAssignment_4 )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( ((LA11_0>=RULE_WORD && LA11_0<=RULE_PLACEHOLDER)) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // InternalCucumber.g:784:2: rule__Feature__NarrativeAssignment_4
                    {
                    pushFollow(FOLLOW_2);
                    rule__Feature__NarrativeAssignment_4();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getFeatureAccess().getNarrativeAssignment_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Feature__Group__4__Impl"


    // $ANTLR start "rule__Feature__Group__5"
    // InternalCucumber.g:794:1: rule__Feature__Group__5 : rule__Feature__Group__5__Impl rule__Feature__Group__6 ;
    public final void rule__Feature__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:798:1: ( rule__Feature__Group__5__Impl rule__Feature__Group__6 )
            // InternalCucumber.g:799:2: rule__Feature__Group__5__Impl rule__Feature__Group__6
            {
            pushFollow(FOLLOW_9);
            rule__Feature__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Feature__Group__6();

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
    // $ANTLR end "rule__Feature__Group__5"


    // $ANTLR start "rule__Feature__Group__5__Impl"
    // InternalCucumber.g:806:1: rule__Feature__Group__5__Impl : ( ( rule__Feature__BackgroundAssignment_5 )? ) ;
    public final void rule__Feature__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:810:1: ( ( ( rule__Feature__BackgroundAssignment_5 )? ) )
            // InternalCucumber.g:811:1: ( ( rule__Feature__BackgroundAssignment_5 )? )
            {
            // InternalCucumber.g:811:1: ( ( rule__Feature__BackgroundAssignment_5 )? )
            // InternalCucumber.g:812:1: ( rule__Feature__BackgroundAssignment_5 )?
            {
             before(grammarAccess.getFeatureAccess().getBackgroundAssignment_5()); 
            // InternalCucumber.g:813:1: ( rule__Feature__BackgroundAssignment_5 )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==17) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // InternalCucumber.g:813:2: rule__Feature__BackgroundAssignment_5
                    {
                    pushFollow(FOLLOW_2);
                    rule__Feature__BackgroundAssignment_5();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getFeatureAccess().getBackgroundAssignment_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Feature__Group__5__Impl"


    // $ANTLR start "rule__Feature__Group__6"
    // InternalCucumber.g:823:1: rule__Feature__Group__6 : rule__Feature__Group__6__Impl ;
    public final void rule__Feature__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:827:1: ( rule__Feature__Group__6__Impl )
            // InternalCucumber.g:828:2: rule__Feature__Group__6__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Feature__Group__6__Impl();

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
    // $ANTLR end "rule__Feature__Group__6"


    // $ANTLR start "rule__Feature__Group__6__Impl"
    // InternalCucumber.g:834:1: rule__Feature__Group__6__Impl : ( ( ( rule__Feature__ScenariosAssignment_6 ) ) ( ( rule__Feature__ScenariosAssignment_6 )* ) ) ;
    public final void rule__Feature__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:838:1: ( ( ( ( rule__Feature__ScenariosAssignment_6 ) ) ( ( rule__Feature__ScenariosAssignment_6 )* ) ) )
            // InternalCucumber.g:839:1: ( ( ( rule__Feature__ScenariosAssignment_6 ) ) ( ( rule__Feature__ScenariosAssignment_6 )* ) )
            {
            // InternalCucumber.g:839:1: ( ( ( rule__Feature__ScenariosAssignment_6 ) ) ( ( rule__Feature__ScenariosAssignment_6 )* ) )
            // InternalCucumber.g:840:1: ( ( rule__Feature__ScenariosAssignment_6 ) ) ( ( rule__Feature__ScenariosAssignment_6 )* )
            {
            // InternalCucumber.g:840:1: ( ( rule__Feature__ScenariosAssignment_6 ) )
            // InternalCucumber.g:841:1: ( rule__Feature__ScenariosAssignment_6 )
            {
             before(grammarAccess.getFeatureAccess().getScenariosAssignment_6()); 
            // InternalCucumber.g:842:1: ( rule__Feature__ScenariosAssignment_6 )
            // InternalCucumber.g:842:2: rule__Feature__ScenariosAssignment_6
            {
            pushFollow(FOLLOW_11);
            rule__Feature__ScenariosAssignment_6();

            state._fsp--;


            }

             after(grammarAccess.getFeatureAccess().getScenariosAssignment_6()); 

            }

            // InternalCucumber.g:845:1: ( ( rule__Feature__ScenariosAssignment_6 )* )
            // InternalCucumber.g:846:1: ( rule__Feature__ScenariosAssignment_6 )*
            {
             before(grammarAccess.getFeatureAccess().getScenariosAssignment_6()); 
            // InternalCucumber.g:847:1: ( rule__Feature__ScenariosAssignment_6 )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==RULE_TAGNAME||(LA13_0>=18 && LA13_0<=19)) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // InternalCucumber.g:847:2: rule__Feature__ScenariosAssignment_6
            	    {
            	    pushFollow(FOLLOW_11);
            	    rule__Feature__ScenariosAssignment_6();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop13;
                }
            } while (true);

             after(grammarAccess.getFeatureAccess().getScenariosAssignment_6()); 

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Feature__Group__6__Impl"


    // $ANTLR start "rule__Background__Group__0"
    // InternalCucumber.g:872:1: rule__Background__Group__0 : rule__Background__Group__0__Impl rule__Background__Group__1 ;
    public final void rule__Background__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:876:1: ( rule__Background__Group__0__Impl rule__Background__Group__1 )
            // InternalCucumber.g:877:2: rule__Background__Group__0__Impl rule__Background__Group__1
            {
            pushFollow(FOLLOW_12);
            rule__Background__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Background__Group__1();

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
    // $ANTLR end "rule__Background__Group__0"


    // $ANTLR start "rule__Background__Group__0__Impl"
    // InternalCucumber.g:884:1: rule__Background__Group__0__Impl : ( 'Background:' ) ;
    public final void rule__Background__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:888:1: ( ( 'Background:' ) )
            // InternalCucumber.g:889:1: ( 'Background:' )
            {
            // InternalCucumber.g:889:1: ( 'Background:' )
            // InternalCucumber.g:890:1: 'Background:'
            {
             before(grammarAccess.getBackgroundAccess().getBackgroundKeyword_0()); 
            match(input,17,FOLLOW_2); 
             after(grammarAccess.getBackgroundAccess().getBackgroundKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Background__Group__0__Impl"


    // $ANTLR start "rule__Background__Group__1"
    // InternalCucumber.g:903:1: rule__Background__Group__1 : rule__Background__Group__1__Impl rule__Background__Group__2 ;
    public final void rule__Background__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:907:1: ( rule__Background__Group__1__Impl rule__Background__Group__2 )
            // InternalCucumber.g:908:2: rule__Background__Group__1__Impl rule__Background__Group__2
            {
            pushFollow(FOLLOW_12);
            rule__Background__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Background__Group__2();

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
    // $ANTLR end "rule__Background__Group__1"


    // $ANTLR start "rule__Background__Group__1__Impl"
    // InternalCucumber.g:915:1: rule__Background__Group__1__Impl : ( ( rule__Background__TitleAssignment_1 )? ) ;
    public final void rule__Background__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:919:1: ( ( ( rule__Background__TitleAssignment_1 )? ) )
            // InternalCucumber.g:920:1: ( ( rule__Background__TitleAssignment_1 )? )
            {
            // InternalCucumber.g:920:1: ( ( rule__Background__TitleAssignment_1 )? )
            // InternalCucumber.g:921:1: ( rule__Background__TitleAssignment_1 )?
            {
             before(grammarAccess.getBackgroundAccess().getTitleAssignment_1()); 
            // InternalCucumber.g:922:1: ( rule__Background__TitleAssignment_1 )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( ((LA14_0>=RULE_WORD && LA14_0<=RULE_PLACEHOLDER)) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // InternalCucumber.g:922:2: rule__Background__TitleAssignment_1
                    {
                    pushFollow(FOLLOW_2);
                    rule__Background__TitleAssignment_1();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getBackgroundAccess().getTitleAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Background__Group__1__Impl"


    // $ANTLR start "rule__Background__Group__2"
    // InternalCucumber.g:932:1: rule__Background__Group__2 : rule__Background__Group__2__Impl rule__Background__Group__3 ;
    public final void rule__Background__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:936:1: ( rule__Background__Group__2__Impl rule__Background__Group__3 )
            // InternalCucumber.g:937:2: rule__Background__Group__2__Impl rule__Background__Group__3
            {
            pushFollow(FOLLOW_13);
            rule__Background__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Background__Group__3();

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
    // $ANTLR end "rule__Background__Group__2"


    // $ANTLR start "rule__Background__Group__2__Impl"
    // InternalCucumber.g:944:1: rule__Background__Group__2__Impl : ( ( ( RULE_EOL ) ) ( ( RULE_EOL )* ) ) ;
    public final void rule__Background__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:948:1: ( ( ( ( RULE_EOL ) ) ( ( RULE_EOL )* ) ) )
            // InternalCucumber.g:949:1: ( ( ( RULE_EOL ) ) ( ( RULE_EOL )* ) )
            {
            // InternalCucumber.g:949:1: ( ( ( RULE_EOL ) ) ( ( RULE_EOL )* ) )
            // InternalCucumber.g:950:1: ( ( RULE_EOL ) ) ( ( RULE_EOL )* )
            {
            // InternalCucumber.g:950:1: ( ( RULE_EOL ) )
            // InternalCucumber.g:951:1: ( RULE_EOL )
            {
             before(grammarAccess.getBackgroundAccess().getEOLTerminalRuleCall_2()); 
            // InternalCucumber.g:952:1: ( RULE_EOL )
            // InternalCucumber.g:952:3: RULE_EOL
            {
            match(input,RULE_EOL,FOLLOW_10); 

            }

             after(grammarAccess.getBackgroundAccess().getEOLTerminalRuleCall_2()); 

            }

            // InternalCucumber.g:955:1: ( ( RULE_EOL )* )
            // InternalCucumber.g:956:1: ( RULE_EOL )*
            {
             before(grammarAccess.getBackgroundAccess().getEOLTerminalRuleCall_2()); 
            // InternalCucumber.g:957:1: ( RULE_EOL )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( (LA15_0==RULE_EOL) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // InternalCucumber.g:957:3: RULE_EOL
            	    {
            	    match(input,RULE_EOL,FOLLOW_10); 

            	    }
            	    break;

            	default :
            	    break loop15;
                }
            } while (true);

             after(grammarAccess.getBackgroundAccess().getEOLTerminalRuleCall_2()); 

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Background__Group__2__Impl"


    // $ANTLR start "rule__Background__Group__3"
    // InternalCucumber.g:968:1: rule__Background__Group__3 : rule__Background__Group__3__Impl rule__Background__Group__4 ;
    public final void rule__Background__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:972:1: ( rule__Background__Group__3__Impl rule__Background__Group__4 )
            // InternalCucumber.g:973:2: rule__Background__Group__3__Impl rule__Background__Group__4
            {
            pushFollow(FOLLOW_13);
            rule__Background__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Background__Group__4();

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
    // $ANTLR end "rule__Background__Group__3"


    // $ANTLR start "rule__Background__Group__3__Impl"
    // InternalCucumber.g:980:1: rule__Background__Group__3__Impl : ( ( rule__Background__NarrativeAssignment_3 )? ) ;
    public final void rule__Background__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:984:1: ( ( ( rule__Background__NarrativeAssignment_3 )? ) )
            // InternalCucumber.g:985:1: ( ( rule__Background__NarrativeAssignment_3 )? )
            {
            // InternalCucumber.g:985:1: ( ( rule__Background__NarrativeAssignment_3 )? )
            // InternalCucumber.g:986:1: ( rule__Background__NarrativeAssignment_3 )?
            {
             before(grammarAccess.getBackgroundAccess().getNarrativeAssignment_3()); 
            // InternalCucumber.g:987:1: ( rule__Background__NarrativeAssignment_3 )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( ((LA16_0>=RULE_WORD && LA16_0<=RULE_PLACEHOLDER)) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // InternalCucumber.g:987:2: rule__Background__NarrativeAssignment_3
                    {
                    pushFollow(FOLLOW_2);
                    rule__Background__NarrativeAssignment_3();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getBackgroundAccess().getNarrativeAssignment_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Background__Group__3__Impl"


    // $ANTLR start "rule__Background__Group__4"
    // InternalCucumber.g:997:1: rule__Background__Group__4 : rule__Background__Group__4__Impl ;
    public final void rule__Background__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:1001:1: ( rule__Background__Group__4__Impl )
            // InternalCucumber.g:1002:2: rule__Background__Group__4__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Background__Group__4__Impl();

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
    // $ANTLR end "rule__Background__Group__4"


    // $ANTLR start "rule__Background__Group__4__Impl"
    // InternalCucumber.g:1008:1: rule__Background__Group__4__Impl : ( ( ( rule__Background__StepsAssignment_4 ) ) ( ( rule__Background__StepsAssignment_4 )* ) ) ;
    public final void rule__Background__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:1012:1: ( ( ( ( rule__Background__StepsAssignment_4 ) ) ( ( rule__Background__StepsAssignment_4 )* ) ) )
            // InternalCucumber.g:1013:1: ( ( ( rule__Background__StepsAssignment_4 ) ) ( ( rule__Background__StepsAssignment_4 )* ) )
            {
            // InternalCucumber.g:1013:1: ( ( ( rule__Background__StepsAssignment_4 ) ) ( ( rule__Background__StepsAssignment_4 )* ) )
            // InternalCucumber.g:1014:1: ( ( rule__Background__StepsAssignment_4 ) ) ( ( rule__Background__StepsAssignment_4 )* )
            {
            // InternalCucumber.g:1014:1: ( ( rule__Background__StepsAssignment_4 ) )
            // InternalCucumber.g:1015:1: ( rule__Background__StepsAssignment_4 )
            {
             before(grammarAccess.getBackgroundAccess().getStepsAssignment_4()); 
            // InternalCucumber.g:1016:1: ( rule__Background__StepsAssignment_4 )
            // InternalCucumber.g:1016:2: rule__Background__StepsAssignment_4
            {
            pushFollow(FOLLOW_14);
            rule__Background__StepsAssignment_4();

            state._fsp--;


            }

             after(grammarAccess.getBackgroundAccess().getStepsAssignment_4()); 

            }

            // InternalCucumber.g:1019:1: ( ( rule__Background__StepsAssignment_4 )* )
            // InternalCucumber.g:1020:1: ( rule__Background__StepsAssignment_4 )*
            {
             before(grammarAccess.getBackgroundAccess().getStepsAssignment_4()); 
            // InternalCucumber.g:1021:1: ( rule__Background__StepsAssignment_4 )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==RULE_STEP_KEYWORD) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // InternalCucumber.g:1021:2: rule__Background__StepsAssignment_4
            	    {
            	    pushFollow(FOLLOW_14);
            	    rule__Background__StepsAssignment_4();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop17;
                }
            } while (true);

             after(grammarAccess.getBackgroundAccess().getStepsAssignment_4()); 

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Background__Group__4__Impl"


    // $ANTLR start "rule__Scenario__Group__0"
    // InternalCucumber.g:1042:1: rule__Scenario__Group__0 : rule__Scenario__Group__0__Impl rule__Scenario__Group__1 ;
    public final void rule__Scenario__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:1046:1: ( rule__Scenario__Group__0__Impl rule__Scenario__Group__1 )
            // InternalCucumber.g:1047:2: rule__Scenario__Group__0__Impl rule__Scenario__Group__1
            {
            pushFollow(FOLLOW_15);
            rule__Scenario__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Scenario__Group__1();

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
    // $ANTLR end "rule__Scenario__Group__0"


    // $ANTLR start "rule__Scenario__Group__0__Impl"
    // InternalCucumber.g:1054:1: rule__Scenario__Group__0__Impl : ( ( rule__Scenario__TagsAssignment_0 )* ) ;
    public final void rule__Scenario__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:1058:1: ( ( ( rule__Scenario__TagsAssignment_0 )* ) )
            // InternalCucumber.g:1059:1: ( ( rule__Scenario__TagsAssignment_0 )* )
            {
            // InternalCucumber.g:1059:1: ( ( rule__Scenario__TagsAssignment_0 )* )
            // InternalCucumber.g:1060:1: ( rule__Scenario__TagsAssignment_0 )*
            {
             before(grammarAccess.getScenarioAccess().getTagsAssignment_0()); 
            // InternalCucumber.g:1061:1: ( rule__Scenario__TagsAssignment_0 )*
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( (LA18_0==RULE_TAGNAME) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // InternalCucumber.g:1061:2: rule__Scenario__TagsAssignment_0
            	    {
            	    pushFollow(FOLLOW_6);
            	    rule__Scenario__TagsAssignment_0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop18;
                }
            } while (true);

             after(grammarAccess.getScenarioAccess().getTagsAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Scenario__Group__0__Impl"


    // $ANTLR start "rule__Scenario__Group__1"
    // InternalCucumber.g:1071:1: rule__Scenario__Group__1 : rule__Scenario__Group__1__Impl rule__Scenario__Group__2 ;
    public final void rule__Scenario__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:1075:1: ( rule__Scenario__Group__1__Impl rule__Scenario__Group__2 )
            // InternalCucumber.g:1076:2: rule__Scenario__Group__1__Impl rule__Scenario__Group__2
            {
            pushFollow(FOLLOW_12);
            rule__Scenario__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Scenario__Group__2();

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
    // $ANTLR end "rule__Scenario__Group__1"


    // $ANTLR start "rule__Scenario__Group__1__Impl"
    // InternalCucumber.g:1083:1: rule__Scenario__Group__1__Impl : ( 'Scenario:' ) ;
    public final void rule__Scenario__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:1087:1: ( ( 'Scenario:' ) )
            // InternalCucumber.g:1088:1: ( 'Scenario:' )
            {
            // InternalCucumber.g:1088:1: ( 'Scenario:' )
            // InternalCucumber.g:1089:1: 'Scenario:'
            {
             before(grammarAccess.getScenarioAccess().getScenarioKeyword_1()); 
            match(input,18,FOLLOW_2); 
             after(grammarAccess.getScenarioAccess().getScenarioKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Scenario__Group__1__Impl"


    // $ANTLR start "rule__Scenario__Group__2"
    // InternalCucumber.g:1102:1: rule__Scenario__Group__2 : rule__Scenario__Group__2__Impl rule__Scenario__Group__3 ;
    public final void rule__Scenario__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:1106:1: ( rule__Scenario__Group__2__Impl rule__Scenario__Group__3 )
            // InternalCucumber.g:1107:2: rule__Scenario__Group__2__Impl rule__Scenario__Group__3
            {
            pushFollow(FOLLOW_12);
            rule__Scenario__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Scenario__Group__3();

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
    // $ANTLR end "rule__Scenario__Group__2"


    // $ANTLR start "rule__Scenario__Group__2__Impl"
    // InternalCucumber.g:1114:1: rule__Scenario__Group__2__Impl : ( ( rule__Scenario__TitleAssignment_2 )? ) ;
    public final void rule__Scenario__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:1118:1: ( ( ( rule__Scenario__TitleAssignment_2 )? ) )
            // InternalCucumber.g:1119:1: ( ( rule__Scenario__TitleAssignment_2 )? )
            {
            // InternalCucumber.g:1119:1: ( ( rule__Scenario__TitleAssignment_2 )? )
            // InternalCucumber.g:1120:1: ( rule__Scenario__TitleAssignment_2 )?
            {
             before(grammarAccess.getScenarioAccess().getTitleAssignment_2()); 
            // InternalCucumber.g:1121:1: ( rule__Scenario__TitleAssignment_2 )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( ((LA19_0>=RULE_WORD && LA19_0<=RULE_PLACEHOLDER)) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // InternalCucumber.g:1121:2: rule__Scenario__TitleAssignment_2
                    {
                    pushFollow(FOLLOW_2);
                    rule__Scenario__TitleAssignment_2();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getScenarioAccess().getTitleAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Scenario__Group__2__Impl"


    // $ANTLR start "rule__Scenario__Group__3"
    // InternalCucumber.g:1131:1: rule__Scenario__Group__3 : rule__Scenario__Group__3__Impl rule__Scenario__Group__4 ;
    public final void rule__Scenario__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:1135:1: ( rule__Scenario__Group__3__Impl rule__Scenario__Group__4 )
            // InternalCucumber.g:1136:2: rule__Scenario__Group__3__Impl rule__Scenario__Group__4
            {
            pushFollow(FOLLOW_13);
            rule__Scenario__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Scenario__Group__4();

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
    // $ANTLR end "rule__Scenario__Group__3"


    // $ANTLR start "rule__Scenario__Group__3__Impl"
    // InternalCucumber.g:1143:1: rule__Scenario__Group__3__Impl : ( ( ( RULE_EOL ) ) ( ( RULE_EOL )* ) ) ;
    public final void rule__Scenario__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:1147:1: ( ( ( ( RULE_EOL ) ) ( ( RULE_EOL )* ) ) )
            // InternalCucumber.g:1148:1: ( ( ( RULE_EOL ) ) ( ( RULE_EOL )* ) )
            {
            // InternalCucumber.g:1148:1: ( ( ( RULE_EOL ) ) ( ( RULE_EOL )* ) )
            // InternalCucumber.g:1149:1: ( ( RULE_EOL ) ) ( ( RULE_EOL )* )
            {
            // InternalCucumber.g:1149:1: ( ( RULE_EOL ) )
            // InternalCucumber.g:1150:1: ( RULE_EOL )
            {
             before(grammarAccess.getScenarioAccess().getEOLTerminalRuleCall_3()); 
            // InternalCucumber.g:1151:1: ( RULE_EOL )
            // InternalCucumber.g:1151:3: RULE_EOL
            {
            match(input,RULE_EOL,FOLLOW_10); 

            }

             after(grammarAccess.getScenarioAccess().getEOLTerminalRuleCall_3()); 

            }

            // InternalCucumber.g:1154:1: ( ( RULE_EOL )* )
            // InternalCucumber.g:1155:1: ( RULE_EOL )*
            {
             before(grammarAccess.getScenarioAccess().getEOLTerminalRuleCall_3()); 
            // InternalCucumber.g:1156:1: ( RULE_EOL )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==RULE_EOL) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // InternalCucumber.g:1156:3: RULE_EOL
            	    {
            	    match(input,RULE_EOL,FOLLOW_10); 

            	    }
            	    break;

            	default :
            	    break loop20;
                }
            } while (true);

             after(grammarAccess.getScenarioAccess().getEOLTerminalRuleCall_3()); 

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Scenario__Group__3__Impl"


    // $ANTLR start "rule__Scenario__Group__4"
    // InternalCucumber.g:1167:1: rule__Scenario__Group__4 : rule__Scenario__Group__4__Impl rule__Scenario__Group__5 ;
    public final void rule__Scenario__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:1171:1: ( rule__Scenario__Group__4__Impl rule__Scenario__Group__5 )
            // InternalCucumber.g:1172:2: rule__Scenario__Group__4__Impl rule__Scenario__Group__5
            {
            pushFollow(FOLLOW_13);
            rule__Scenario__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Scenario__Group__5();

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
    // $ANTLR end "rule__Scenario__Group__4"


    // $ANTLR start "rule__Scenario__Group__4__Impl"
    // InternalCucumber.g:1179:1: rule__Scenario__Group__4__Impl : ( ( rule__Scenario__NarrativeAssignment_4 )? ) ;
    public final void rule__Scenario__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:1183:1: ( ( ( rule__Scenario__NarrativeAssignment_4 )? ) )
            // InternalCucumber.g:1184:1: ( ( rule__Scenario__NarrativeAssignment_4 )? )
            {
            // InternalCucumber.g:1184:1: ( ( rule__Scenario__NarrativeAssignment_4 )? )
            // InternalCucumber.g:1185:1: ( rule__Scenario__NarrativeAssignment_4 )?
            {
             before(grammarAccess.getScenarioAccess().getNarrativeAssignment_4()); 
            // InternalCucumber.g:1186:1: ( rule__Scenario__NarrativeAssignment_4 )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( ((LA21_0>=RULE_WORD && LA21_0<=RULE_PLACEHOLDER)) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // InternalCucumber.g:1186:2: rule__Scenario__NarrativeAssignment_4
                    {
                    pushFollow(FOLLOW_2);
                    rule__Scenario__NarrativeAssignment_4();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getScenarioAccess().getNarrativeAssignment_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Scenario__Group__4__Impl"


    // $ANTLR start "rule__Scenario__Group__5"
    // InternalCucumber.g:1196:1: rule__Scenario__Group__5 : rule__Scenario__Group__5__Impl ;
    public final void rule__Scenario__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:1200:1: ( rule__Scenario__Group__5__Impl )
            // InternalCucumber.g:1201:2: rule__Scenario__Group__5__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Scenario__Group__5__Impl();

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
    // $ANTLR end "rule__Scenario__Group__5"


    // $ANTLR start "rule__Scenario__Group__5__Impl"
    // InternalCucumber.g:1207:1: rule__Scenario__Group__5__Impl : ( ( ( rule__Scenario__StepsAssignment_5 ) ) ( ( rule__Scenario__StepsAssignment_5 )* ) ) ;
    public final void rule__Scenario__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:1211:1: ( ( ( ( rule__Scenario__StepsAssignment_5 ) ) ( ( rule__Scenario__StepsAssignment_5 )* ) ) )
            // InternalCucumber.g:1212:1: ( ( ( rule__Scenario__StepsAssignment_5 ) ) ( ( rule__Scenario__StepsAssignment_5 )* ) )
            {
            // InternalCucumber.g:1212:1: ( ( ( rule__Scenario__StepsAssignment_5 ) ) ( ( rule__Scenario__StepsAssignment_5 )* ) )
            // InternalCucumber.g:1213:1: ( ( rule__Scenario__StepsAssignment_5 ) ) ( ( rule__Scenario__StepsAssignment_5 )* )
            {
            // InternalCucumber.g:1213:1: ( ( rule__Scenario__StepsAssignment_5 ) )
            // InternalCucumber.g:1214:1: ( rule__Scenario__StepsAssignment_5 )
            {
             before(grammarAccess.getScenarioAccess().getStepsAssignment_5()); 
            // InternalCucumber.g:1215:1: ( rule__Scenario__StepsAssignment_5 )
            // InternalCucumber.g:1215:2: rule__Scenario__StepsAssignment_5
            {
            pushFollow(FOLLOW_14);
            rule__Scenario__StepsAssignment_5();

            state._fsp--;


            }

             after(grammarAccess.getScenarioAccess().getStepsAssignment_5()); 

            }

            // InternalCucumber.g:1218:1: ( ( rule__Scenario__StepsAssignment_5 )* )
            // InternalCucumber.g:1219:1: ( rule__Scenario__StepsAssignment_5 )*
            {
             before(grammarAccess.getScenarioAccess().getStepsAssignment_5()); 
            // InternalCucumber.g:1220:1: ( rule__Scenario__StepsAssignment_5 )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( (LA22_0==RULE_STEP_KEYWORD) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // InternalCucumber.g:1220:2: rule__Scenario__StepsAssignment_5
            	    {
            	    pushFollow(FOLLOW_14);
            	    rule__Scenario__StepsAssignment_5();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop22;
                }
            } while (true);

             after(grammarAccess.getScenarioAccess().getStepsAssignment_5()); 

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Scenario__Group__5__Impl"


    // $ANTLR start "rule__ScenarioOutline__Group__0"
    // InternalCucumber.g:1243:1: rule__ScenarioOutline__Group__0 : rule__ScenarioOutline__Group__0__Impl rule__ScenarioOutline__Group__1 ;
    public final void rule__ScenarioOutline__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:1247:1: ( rule__ScenarioOutline__Group__0__Impl rule__ScenarioOutline__Group__1 )
            // InternalCucumber.g:1248:2: rule__ScenarioOutline__Group__0__Impl rule__ScenarioOutline__Group__1
            {
            pushFollow(FOLLOW_9);
            rule__ScenarioOutline__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ScenarioOutline__Group__1();

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
    // $ANTLR end "rule__ScenarioOutline__Group__0"


    // $ANTLR start "rule__ScenarioOutline__Group__0__Impl"
    // InternalCucumber.g:1255:1: rule__ScenarioOutline__Group__0__Impl : ( ( rule__ScenarioOutline__TagsAssignment_0 )* ) ;
    public final void rule__ScenarioOutline__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:1259:1: ( ( ( rule__ScenarioOutline__TagsAssignment_0 )* ) )
            // InternalCucumber.g:1260:1: ( ( rule__ScenarioOutline__TagsAssignment_0 )* )
            {
            // InternalCucumber.g:1260:1: ( ( rule__ScenarioOutline__TagsAssignment_0 )* )
            // InternalCucumber.g:1261:1: ( rule__ScenarioOutline__TagsAssignment_0 )*
            {
             before(grammarAccess.getScenarioOutlineAccess().getTagsAssignment_0()); 
            // InternalCucumber.g:1262:1: ( rule__ScenarioOutline__TagsAssignment_0 )*
            loop23:
            do {
                int alt23=2;
                int LA23_0 = input.LA(1);

                if ( (LA23_0==RULE_TAGNAME) ) {
                    alt23=1;
                }


                switch (alt23) {
            	case 1 :
            	    // InternalCucumber.g:1262:2: rule__ScenarioOutline__TagsAssignment_0
            	    {
            	    pushFollow(FOLLOW_6);
            	    rule__ScenarioOutline__TagsAssignment_0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop23;
                }
            } while (true);

             after(grammarAccess.getScenarioOutlineAccess().getTagsAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ScenarioOutline__Group__0__Impl"


    // $ANTLR start "rule__ScenarioOutline__Group__1"
    // InternalCucumber.g:1272:1: rule__ScenarioOutline__Group__1 : rule__ScenarioOutline__Group__1__Impl rule__ScenarioOutline__Group__2 ;
    public final void rule__ScenarioOutline__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:1276:1: ( rule__ScenarioOutline__Group__1__Impl rule__ScenarioOutline__Group__2 )
            // InternalCucumber.g:1277:2: rule__ScenarioOutline__Group__1__Impl rule__ScenarioOutline__Group__2
            {
            pushFollow(FOLLOW_12);
            rule__ScenarioOutline__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ScenarioOutline__Group__2();

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
    // $ANTLR end "rule__ScenarioOutline__Group__1"


    // $ANTLR start "rule__ScenarioOutline__Group__1__Impl"
    // InternalCucumber.g:1284:1: rule__ScenarioOutline__Group__1__Impl : ( 'Scenario Outline:' ) ;
    public final void rule__ScenarioOutline__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:1288:1: ( ( 'Scenario Outline:' ) )
            // InternalCucumber.g:1289:1: ( 'Scenario Outline:' )
            {
            // InternalCucumber.g:1289:1: ( 'Scenario Outline:' )
            // InternalCucumber.g:1290:1: 'Scenario Outline:'
            {
             before(grammarAccess.getScenarioOutlineAccess().getScenarioOutlineKeyword_1()); 
            match(input,19,FOLLOW_2); 
             after(grammarAccess.getScenarioOutlineAccess().getScenarioOutlineKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ScenarioOutline__Group__1__Impl"


    // $ANTLR start "rule__ScenarioOutline__Group__2"
    // InternalCucumber.g:1303:1: rule__ScenarioOutline__Group__2 : rule__ScenarioOutline__Group__2__Impl rule__ScenarioOutline__Group__3 ;
    public final void rule__ScenarioOutline__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:1307:1: ( rule__ScenarioOutline__Group__2__Impl rule__ScenarioOutline__Group__3 )
            // InternalCucumber.g:1308:2: rule__ScenarioOutline__Group__2__Impl rule__ScenarioOutline__Group__3
            {
            pushFollow(FOLLOW_12);
            rule__ScenarioOutline__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ScenarioOutline__Group__3();

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
    // $ANTLR end "rule__ScenarioOutline__Group__2"


    // $ANTLR start "rule__ScenarioOutline__Group__2__Impl"
    // InternalCucumber.g:1315:1: rule__ScenarioOutline__Group__2__Impl : ( ( rule__ScenarioOutline__TitleAssignment_2 )? ) ;
    public final void rule__ScenarioOutline__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:1319:1: ( ( ( rule__ScenarioOutline__TitleAssignment_2 )? ) )
            // InternalCucumber.g:1320:1: ( ( rule__ScenarioOutline__TitleAssignment_2 )? )
            {
            // InternalCucumber.g:1320:1: ( ( rule__ScenarioOutline__TitleAssignment_2 )? )
            // InternalCucumber.g:1321:1: ( rule__ScenarioOutline__TitleAssignment_2 )?
            {
             before(grammarAccess.getScenarioOutlineAccess().getTitleAssignment_2()); 
            // InternalCucumber.g:1322:1: ( rule__ScenarioOutline__TitleAssignment_2 )?
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( ((LA24_0>=RULE_WORD && LA24_0<=RULE_PLACEHOLDER)) ) {
                alt24=1;
            }
            switch (alt24) {
                case 1 :
                    // InternalCucumber.g:1322:2: rule__ScenarioOutline__TitleAssignment_2
                    {
                    pushFollow(FOLLOW_2);
                    rule__ScenarioOutline__TitleAssignment_2();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getScenarioOutlineAccess().getTitleAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ScenarioOutline__Group__2__Impl"


    // $ANTLR start "rule__ScenarioOutline__Group__3"
    // InternalCucumber.g:1332:1: rule__ScenarioOutline__Group__3 : rule__ScenarioOutline__Group__3__Impl rule__ScenarioOutline__Group__4 ;
    public final void rule__ScenarioOutline__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:1336:1: ( rule__ScenarioOutline__Group__3__Impl rule__ScenarioOutline__Group__4 )
            // InternalCucumber.g:1337:2: rule__ScenarioOutline__Group__3__Impl rule__ScenarioOutline__Group__4
            {
            pushFollow(FOLLOW_13);
            rule__ScenarioOutline__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ScenarioOutline__Group__4();

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
    // $ANTLR end "rule__ScenarioOutline__Group__3"


    // $ANTLR start "rule__ScenarioOutline__Group__3__Impl"
    // InternalCucumber.g:1344:1: rule__ScenarioOutline__Group__3__Impl : ( ( ( RULE_EOL ) ) ( ( RULE_EOL )* ) ) ;
    public final void rule__ScenarioOutline__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:1348:1: ( ( ( ( RULE_EOL ) ) ( ( RULE_EOL )* ) ) )
            // InternalCucumber.g:1349:1: ( ( ( RULE_EOL ) ) ( ( RULE_EOL )* ) )
            {
            // InternalCucumber.g:1349:1: ( ( ( RULE_EOL ) ) ( ( RULE_EOL )* ) )
            // InternalCucumber.g:1350:1: ( ( RULE_EOL ) ) ( ( RULE_EOL )* )
            {
            // InternalCucumber.g:1350:1: ( ( RULE_EOL ) )
            // InternalCucumber.g:1351:1: ( RULE_EOL )
            {
             before(grammarAccess.getScenarioOutlineAccess().getEOLTerminalRuleCall_3()); 
            // InternalCucumber.g:1352:1: ( RULE_EOL )
            // InternalCucumber.g:1352:3: RULE_EOL
            {
            match(input,RULE_EOL,FOLLOW_10); 

            }

             after(grammarAccess.getScenarioOutlineAccess().getEOLTerminalRuleCall_3()); 

            }

            // InternalCucumber.g:1355:1: ( ( RULE_EOL )* )
            // InternalCucumber.g:1356:1: ( RULE_EOL )*
            {
             before(grammarAccess.getScenarioOutlineAccess().getEOLTerminalRuleCall_3()); 
            // InternalCucumber.g:1357:1: ( RULE_EOL )*
            loop25:
            do {
                int alt25=2;
                int LA25_0 = input.LA(1);

                if ( (LA25_0==RULE_EOL) ) {
                    alt25=1;
                }


                switch (alt25) {
            	case 1 :
            	    // InternalCucumber.g:1357:3: RULE_EOL
            	    {
            	    match(input,RULE_EOL,FOLLOW_10); 

            	    }
            	    break;

            	default :
            	    break loop25;
                }
            } while (true);

             after(grammarAccess.getScenarioOutlineAccess().getEOLTerminalRuleCall_3()); 

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ScenarioOutline__Group__3__Impl"


    // $ANTLR start "rule__ScenarioOutline__Group__4"
    // InternalCucumber.g:1368:1: rule__ScenarioOutline__Group__4 : rule__ScenarioOutline__Group__4__Impl rule__ScenarioOutline__Group__5 ;
    public final void rule__ScenarioOutline__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:1372:1: ( rule__ScenarioOutline__Group__4__Impl rule__ScenarioOutline__Group__5 )
            // InternalCucumber.g:1373:2: rule__ScenarioOutline__Group__4__Impl rule__ScenarioOutline__Group__5
            {
            pushFollow(FOLLOW_13);
            rule__ScenarioOutline__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ScenarioOutline__Group__5();

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
    // $ANTLR end "rule__ScenarioOutline__Group__4"


    // $ANTLR start "rule__ScenarioOutline__Group__4__Impl"
    // InternalCucumber.g:1380:1: rule__ScenarioOutline__Group__4__Impl : ( ( rule__ScenarioOutline__NarrativeAssignment_4 )? ) ;
    public final void rule__ScenarioOutline__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:1384:1: ( ( ( rule__ScenarioOutline__NarrativeAssignment_4 )? ) )
            // InternalCucumber.g:1385:1: ( ( rule__ScenarioOutline__NarrativeAssignment_4 )? )
            {
            // InternalCucumber.g:1385:1: ( ( rule__ScenarioOutline__NarrativeAssignment_4 )? )
            // InternalCucumber.g:1386:1: ( rule__ScenarioOutline__NarrativeAssignment_4 )?
            {
             before(grammarAccess.getScenarioOutlineAccess().getNarrativeAssignment_4()); 
            // InternalCucumber.g:1387:1: ( rule__ScenarioOutline__NarrativeAssignment_4 )?
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( ((LA26_0>=RULE_WORD && LA26_0<=RULE_PLACEHOLDER)) ) {
                alt26=1;
            }
            switch (alt26) {
                case 1 :
                    // InternalCucumber.g:1387:2: rule__ScenarioOutline__NarrativeAssignment_4
                    {
                    pushFollow(FOLLOW_2);
                    rule__ScenarioOutline__NarrativeAssignment_4();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getScenarioOutlineAccess().getNarrativeAssignment_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ScenarioOutline__Group__4__Impl"


    // $ANTLR start "rule__ScenarioOutline__Group__5"
    // InternalCucumber.g:1397:1: rule__ScenarioOutline__Group__5 : rule__ScenarioOutline__Group__5__Impl rule__ScenarioOutline__Group__6 ;
    public final void rule__ScenarioOutline__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:1401:1: ( rule__ScenarioOutline__Group__5__Impl rule__ScenarioOutline__Group__6 )
            // InternalCucumber.g:1402:2: rule__ScenarioOutline__Group__5__Impl rule__ScenarioOutline__Group__6
            {
            pushFollow(FOLLOW_16);
            rule__ScenarioOutline__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ScenarioOutline__Group__6();

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
    // $ANTLR end "rule__ScenarioOutline__Group__5"


    // $ANTLR start "rule__ScenarioOutline__Group__5__Impl"
    // InternalCucumber.g:1409:1: rule__ScenarioOutline__Group__5__Impl : ( ( ( rule__ScenarioOutline__StepsAssignment_5 ) ) ( ( rule__ScenarioOutline__StepsAssignment_5 )* ) ) ;
    public final void rule__ScenarioOutline__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:1413:1: ( ( ( ( rule__ScenarioOutline__StepsAssignment_5 ) ) ( ( rule__ScenarioOutline__StepsAssignment_5 )* ) ) )
            // InternalCucumber.g:1414:1: ( ( ( rule__ScenarioOutline__StepsAssignment_5 ) ) ( ( rule__ScenarioOutline__StepsAssignment_5 )* ) )
            {
            // InternalCucumber.g:1414:1: ( ( ( rule__ScenarioOutline__StepsAssignment_5 ) ) ( ( rule__ScenarioOutline__StepsAssignment_5 )* ) )
            // InternalCucumber.g:1415:1: ( ( rule__ScenarioOutline__StepsAssignment_5 ) ) ( ( rule__ScenarioOutline__StepsAssignment_5 )* )
            {
            // InternalCucumber.g:1415:1: ( ( rule__ScenarioOutline__StepsAssignment_5 ) )
            // InternalCucumber.g:1416:1: ( rule__ScenarioOutline__StepsAssignment_5 )
            {
             before(grammarAccess.getScenarioOutlineAccess().getStepsAssignment_5()); 
            // InternalCucumber.g:1417:1: ( rule__ScenarioOutline__StepsAssignment_5 )
            // InternalCucumber.g:1417:2: rule__ScenarioOutline__StepsAssignment_5
            {
            pushFollow(FOLLOW_14);
            rule__ScenarioOutline__StepsAssignment_5();

            state._fsp--;


            }

             after(grammarAccess.getScenarioOutlineAccess().getStepsAssignment_5()); 

            }

            // InternalCucumber.g:1420:1: ( ( rule__ScenarioOutline__StepsAssignment_5 )* )
            // InternalCucumber.g:1421:1: ( rule__ScenarioOutline__StepsAssignment_5 )*
            {
             before(grammarAccess.getScenarioOutlineAccess().getStepsAssignment_5()); 
            // InternalCucumber.g:1422:1: ( rule__ScenarioOutline__StepsAssignment_5 )*
            loop27:
            do {
                int alt27=2;
                int LA27_0 = input.LA(1);

                if ( (LA27_0==RULE_STEP_KEYWORD) ) {
                    alt27=1;
                }


                switch (alt27) {
            	case 1 :
            	    // InternalCucumber.g:1422:2: rule__ScenarioOutline__StepsAssignment_5
            	    {
            	    pushFollow(FOLLOW_14);
            	    rule__ScenarioOutline__StepsAssignment_5();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop27;
                }
            } while (true);

             after(grammarAccess.getScenarioOutlineAccess().getStepsAssignment_5()); 

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ScenarioOutline__Group__5__Impl"


    // $ANTLR start "rule__ScenarioOutline__Group__6"
    // InternalCucumber.g:1433:1: rule__ScenarioOutline__Group__6 : rule__ScenarioOutline__Group__6__Impl ;
    public final void rule__ScenarioOutline__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:1437:1: ( rule__ScenarioOutline__Group__6__Impl )
            // InternalCucumber.g:1438:2: rule__ScenarioOutline__Group__6__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ScenarioOutline__Group__6__Impl();

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
    // $ANTLR end "rule__ScenarioOutline__Group__6"


    // $ANTLR start "rule__ScenarioOutline__Group__6__Impl"
    // InternalCucumber.g:1444:1: rule__ScenarioOutline__Group__6__Impl : ( ( rule__ScenarioOutline__ExamplesAssignment_6 ) ) ;
    public final void rule__ScenarioOutline__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:1448:1: ( ( ( rule__ScenarioOutline__ExamplesAssignment_6 ) ) )
            // InternalCucumber.g:1449:1: ( ( rule__ScenarioOutline__ExamplesAssignment_6 ) )
            {
            // InternalCucumber.g:1449:1: ( ( rule__ScenarioOutline__ExamplesAssignment_6 ) )
            // InternalCucumber.g:1450:1: ( rule__ScenarioOutline__ExamplesAssignment_6 )
            {
             before(grammarAccess.getScenarioOutlineAccess().getExamplesAssignment_6()); 
            // InternalCucumber.g:1451:1: ( rule__ScenarioOutline__ExamplesAssignment_6 )
            // InternalCucumber.g:1451:2: rule__ScenarioOutline__ExamplesAssignment_6
            {
            pushFollow(FOLLOW_2);
            rule__ScenarioOutline__ExamplesAssignment_6();

            state._fsp--;


            }

             after(grammarAccess.getScenarioOutlineAccess().getExamplesAssignment_6()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ScenarioOutline__Group__6__Impl"


    // $ANTLR start "rule__Step__Group__0"
    // InternalCucumber.g:1475:1: rule__Step__Group__0 : rule__Step__Group__0__Impl rule__Step__Group__1 ;
    public final void rule__Step__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:1479:1: ( rule__Step__Group__0__Impl rule__Step__Group__1 )
            // InternalCucumber.g:1480:2: rule__Step__Group__0__Impl rule__Step__Group__1
            {
            pushFollow(FOLLOW_17);
            rule__Step__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Step__Group__1();

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
    // $ANTLR end "rule__Step__Group__0"


    // $ANTLR start "rule__Step__Group__0__Impl"
    // InternalCucumber.g:1487:1: rule__Step__Group__0__Impl : ( ( rule__Step__KeywordAssignment_0 ) ) ;
    public final void rule__Step__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:1491:1: ( ( ( rule__Step__KeywordAssignment_0 ) ) )
            // InternalCucumber.g:1492:1: ( ( rule__Step__KeywordAssignment_0 ) )
            {
            // InternalCucumber.g:1492:1: ( ( rule__Step__KeywordAssignment_0 ) )
            // InternalCucumber.g:1493:1: ( rule__Step__KeywordAssignment_0 )
            {
             before(grammarAccess.getStepAccess().getKeywordAssignment_0()); 
            // InternalCucumber.g:1494:1: ( rule__Step__KeywordAssignment_0 )
            // InternalCucumber.g:1494:2: rule__Step__KeywordAssignment_0
            {
            pushFollow(FOLLOW_2);
            rule__Step__KeywordAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getStepAccess().getKeywordAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Step__Group__0__Impl"


    // $ANTLR start "rule__Step__Group__1"
    // InternalCucumber.g:1504:1: rule__Step__Group__1 : rule__Step__Group__1__Impl rule__Step__Group__2 ;
    public final void rule__Step__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:1508:1: ( rule__Step__Group__1__Impl rule__Step__Group__2 )
            // InternalCucumber.g:1509:2: rule__Step__Group__1__Impl rule__Step__Group__2
            {
            pushFollow(FOLLOW_18);
            rule__Step__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Step__Group__2();

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
    // $ANTLR end "rule__Step__Group__1"


    // $ANTLR start "rule__Step__Group__1__Impl"
    // InternalCucumber.g:1516:1: rule__Step__Group__1__Impl : ( ( rule__Step__DescriptionAssignment_1 ) ) ;
    public final void rule__Step__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:1520:1: ( ( ( rule__Step__DescriptionAssignment_1 ) ) )
            // InternalCucumber.g:1521:1: ( ( rule__Step__DescriptionAssignment_1 ) )
            {
            // InternalCucumber.g:1521:1: ( ( rule__Step__DescriptionAssignment_1 ) )
            // InternalCucumber.g:1522:1: ( rule__Step__DescriptionAssignment_1 )
            {
             before(grammarAccess.getStepAccess().getDescriptionAssignment_1()); 
            // InternalCucumber.g:1523:1: ( rule__Step__DescriptionAssignment_1 )
            // InternalCucumber.g:1523:2: rule__Step__DescriptionAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__Step__DescriptionAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getStepAccess().getDescriptionAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Step__Group__1__Impl"


    // $ANTLR start "rule__Step__Group__2"
    // InternalCucumber.g:1533:1: rule__Step__Group__2 : rule__Step__Group__2__Impl rule__Step__Group__3 ;
    public final void rule__Step__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:1537:1: ( rule__Step__Group__2__Impl rule__Step__Group__3 )
            // InternalCucumber.g:1538:2: rule__Step__Group__2__Impl rule__Step__Group__3
            {
            pushFollow(FOLLOW_18);
            rule__Step__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Step__Group__3();

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
    // $ANTLR end "rule__Step__Group__2"


    // $ANTLR start "rule__Step__Group__2__Impl"
    // InternalCucumber.g:1545:1: rule__Step__Group__2__Impl : ( ( RULE_EOL )* ) ;
    public final void rule__Step__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:1549:1: ( ( ( RULE_EOL )* ) )
            // InternalCucumber.g:1550:1: ( ( RULE_EOL )* )
            {
            // InternalCucumber.g:1550:1: ( ( RULE_EOL )* )
            // InternalCucumber.g:1551:1: ( RULE_EOL )*
            {
             before(grammarAccess.getStepAccess().getEOLTerminalRuleCall_2()); 
            // InternalCucumber.g:1552:1: ( RULE_EOL )*
            loop28:
            do {
                int alt28=2;
                int LA28_0 = input.LA(1);

                if ( (LA28_0==RULE_EOL) ) {
                    alt28=1;
                }


                switch (alt28) {
            	case 1 :
            	    // InternalCucumber.g:1552:3: RULE_EOL
            	    {
            	    match(input,RULE_EOL,FOLLOW_10); 

            	    }
            	    break;

            	default :
            	    break loop28;
                }
            } while (true);

             after(grammarAccess.getStepAccess().getEOLTerminalRuleCall_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Step__Group__2__Impl"


    // $ANTLR start "rule__Step__Group__3"
    // InternalCucumber.g:1562:1: rule__Step__Group__3 : rule__Step__Group__3__Impl rule__Step__Group__4 ;
    public final void rule__Step__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:1566:1: ( rule__Step__Group__3__Impl rule__Step__Group__4 )
            // InternalCucumber.g:1567:2: rule__Step__Group__3__Impl rule__Step__Group__4
            {
            pushFollow(FOLLOW_18);
            rule__Step__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Step__Group__4();

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
    // $ANTLR end "rule__Step__Group__3"


    // $ANTLR start "rule__Step__Group__3__Impl"
    // InternalCucumber.g:1574:1: rule__Step__Group__3__Impl : ( ( rule__Step__TablesAssignment_3 )* ) ;
    public final void rule__Step__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:1578:1: ( ( ( rule__Step__TablesAssignment_3 )* ) )
            // InternalCucumber.g:1579:1: ( ( rule__Step__TablesAssignment_3 )* )
            {
            // InternalCucumber.g:1579:1: ( ( rule__Step__TablesAssignment_3 )* )
            // InternalCucumber.g:1580:1: ( rule__Step__TablesAssignment_3 )*
            {
             before(grammarAccess.getStepAccess().getTablesAssignment_3()); 
            // InternalCucumber.g:1581:1: ( rule__Step__TablesAssignment_3 )*
            loop29:
            do {
                int alt29=2;
                int LA29_0 = input.LA(1);

                if ( (LA29_0==RULE_TABLE_ROW) ) {
                    alt29=1;
                }


                switch (alt29) {
            	case 1 :
            	    // InternalCucumber.g:1581:2: rule__Step__TablesAssignment_3
            	    {
            	    pushFollow(FOLLOW_19);
            	    rule__Step__TablesAssignment_3();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop29;
                }
            } while (true);

             after(grammarAccess.getStepAccess().getTablesAssignment_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Step__Group__3__Impl"


    // $ANTLR start "rule__Step__Group__4"
    // InternalCucumber.g:1591:1: rule__Step__Group__4 : rule__Step__Group__4__Impl rule__Step__Group__5 ;
    public final void rule__Step__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:1595:1: ( rule__Step__Group__4__Impl rule__Step__Group__5 )
            // InternalCucumber.g:1596:2: rule__Step__Group__4__Impl rule__Step__Group__5
            {
            pushFollow(FOLLOW_18);
            rule__Step__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Step__Group__5();

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
    // $ANTLR end "rule__Step__Group__4"


    // $ANTLR start "rule__Step__Group__4__Impl"
    // InternalCucumber.g:1603:1: rule__Step__Group__4__Impl : ( ( rule__Step__CodeAssignment_4 )? ) ;
    public final void rule__Step__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:1607:1: ( ( ( rule__Step__CodeAssignment_4 )? ) )
            // InternalCucumber.g:1608:1: ( ( rule__Step__CodeAssignment_4 )? )
            {
            // InternalCucumber.g:1608:1: ( ( rule__Step__CodeAssignment_4 )? )
            // InternalCucumber.g:1609:1: ( rule__Step__CodeAssignment_4 )?
            {
             before(grammarAccess.getStepAccess().getCodeAssignment_4()); 
            // InternalCucumber.g:1610:1: ( rule__Step__CodeAssignment_4 )?
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==RULE_DOC_STRING) ) {
                alt30=1;
            }
            switch (alt30) {
                case 1 :
                    // InternalCucumber.g:1610:2: rule__Step__CodeAssignment_4
                    {
                    pushFollow(FOLLOW_2);
                    rule__Step__CodeAssignment_4();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getStepAccess().getCodeAssignment_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Step__Group__4__Impl"


    // $ANTLR start "rule__Step__Group__5"
    // InternalCucumber.g:1620:1: rule__Step__Group__5 : rule__Step__Group__5__Impl ;
    public final void rule__Step__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:1624:1: ( rule__Step__Group__5__Impl )
            // InternalCucumber.g:1625:2: rule__Step__Group__5__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Step__Group__5__Impl();

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
    // $ANTLR end "rule__Step__Group__5"


    // $ANTLR start "rule__Step__Group__5__Impl"
    // InternalCucumber.g:1631:1: rule__Step__Group__5__Impl : ( ( rule__Step__TablesAssignment_5 )* ) ;
    public final void rule__Step__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:1635:1: ( ( ( rule__Step__TablesAssignment_5 )* ) )
            // InternalCucumber.g:1636:1: ( ( rule__Step__TablesAssignment_5 )* )
            {
            // InternalCucumber.g:1636:1: ( ( rule__Step__TablesAssignment_5 )* )
            // InternalCucumber.g:1637:1: ( rule__Step__TablesAssignment_5 )*
            {
             before(grammarAccess.getStepAccess().getTablesAssignment_5()); 
            // InternalCucumber.g:1638:1: ( rule__Step__TablesAssignment_5 )*
            loop31:
            do {
                int alt31=2;
                int LA31_0 = input.LA(1);

                if ( (LA31_0==RULE_TABLE_ROW) ) {
                    alt31=1;
                }


                switch (alt31) {
            	case 1 :
            	    // InternalCucumber.g:1638:2: rule__Step__TablesAssignment_5
            	    {
            	    pushFollow(FOLLOW_19);
            	    rule__Step__TablesAssignment_5();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop31;
                }
            } while (true);

             after(grammarAccess.getStepAccess().getTablesAssignment_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Step__Group__5__Impl"


    // $ANTLR start "rule__Examples__Group__0"
    // InternalCucumber.g:1660:1: rule__Examples__Group__0 : rule__Examples__Group__0__Impl rule__Examples__Group__1 ;
    public final void rule__Examples__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:1664:1: ( rule__Examples__Group__0__Impl rule__Examples__Group__1 )
            // InternalCucumber.g:1665:2: rule__Examples__Group__0__Impl rule__Examples__Group__1
            {
            pushFollow(FOLLOW_12);
            rule__Examples__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Examples__Group__1();

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
    // $ANTLR end "rule__Examples__Group__0"


    // $ANTLR start "rule__Examples__Group__0__Impl"
    // InternalCucumber.g:1672:1: rule__Examples__Group__0__Impl : ( 'Examples:' ) ;
    public final void rule__Examples__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:1676:1: ( ( 'Examples:' ) )
            // InternalCucumber.g:1677:1: ( 'Examples:' )
            {
            // InternalCucumber.g:1677:1: ( 'Examples:' )
            // InternalCucumber.g:1678:1: 'Examples:'
            {
             before(grammarAccess.getExamplesAccess().getExamplesKeyword_0()); 
            match(input,20,FOLLOW_2); 
             after(grammarAccess.getExamplesAccess().getExamplesKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Examples__Group__0__Impl"


    // $ANTLR start "rule__Examples__Group__1"
    // InternalCucumber.g:1691:1: rule__Examples__Group__1 : rule__Examples__Group__1__Impl rule__Examples__Group__2 ;
    public final void rule__Examples__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:1695:1: ( rule__Examples__Group__1__Impl rule__Examples__Group__2 )
            // InternalCucumber.g:1696:2: rule__Examples__Group__1__Impl rule__Examples__Group__2
            {
            pushFollow(FOLLOW_12);
            rule__Examples__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Examples__Group__2();

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
    // $ANTLR end "rule__Examples__Group__1"


    // $ANTLR start "rule__Examples__Group__1__Impl"
    // InternalCucumber.g:1703:1: rule__Examples__Group__1__Impl : ( ( rule__Examples__TitleAssignment_1 )? ) ;
    public final void rule__Examples__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:1707:1: ( ( ( rule__Examples__TitleAssignment_1 )? ) )
            // InternalCucumber.g:1708:1: ( ( rule__Examples__TitleAssignment_1 )? )
            {
            // InternalCucumber.g:1708:1: ( ( rule__Examples__TitleAssignment_1 )? )
            // InternalCucumber.g:1709:1: ( rule__Examples__TitleAssignment_1 )?
            {
             before(grammarAccess.getExamplesAccess().getTitleAssignment_1()); 
            // InternalCucumber.g:1710:1: ( rule__Examples__TitleAssignment_1 )?
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( ((LA32_0>=RULE_WORD && LA32_0<=RULE_PLACEHOLDER)) ) {
                alt32=1;
            }
            switch (alt32) {
                case 1 :
                    // InternalCucumber.g:1710:2: rule__Examples__TitleAssignment_1
                    {
                    pushFollow(FOLLOW_2);
                    rule__Examples__TitleAssignment_1();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getExamplesAccess().getTitleAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Examples__Group__1__Impl"


    // $ANTLR start "rule__Examples__Group__2"
    // InternalCucumber.g:1720:1: rule__Examples__Group__2 : rule__Examples__Group__2__Impl rule__Examples__Group__3 ;
    public final void rule__Examples__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:1724:1: ( rule__Examples__Group__2__Impl rule__Examples__Group__3 )
            // InternalCucumber.g:1725:2: rule__Examples__Group__2__Impl rule__Examples__Group__3
            {
            pushFollow(FOLLOW_20);
            rule__Examples__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Examples__Group__3();

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
    // $ANTLR end "rule__Examples__Group__2"


    // $ANTLR start "rule__Examples__Group__2__Impl"
    // InternalCucumber.g:1732:1: rule__Examples__Group__2__Impl : ( ( ( RULE_EOL ) ) ( ( RULE_EOL )* ) ) ;
    public final void rule__Examples__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:1736:1: ( ( ( ( RULE_EOL ) ) ( ( RULE_EOL )* ) ) )
            // InternalCucumber.g:1737:1: ( ( ( RULE_EOL ) ) ( ( RULE_EOL )* ) )
            {
            // InternalCucumber.g:1737:1: ( ( ( RULE_EOL ) ) ( ( RULE_EOL )* ) )
            // InternalCucumber.g:1738:1: ( ( RULE_EOL ) ) ( ( RULE_EOL )* )
            {
            // InternalCucumber.g:1738:1: ( ( RULE_EOL ) )
            // InternalCucumber.g:1739:1: ( RULE_EOL )
            {
             before(grammarAccess.getExamplesAccess().getEOLTerminalRuleCall_2()); 
            // InternalCucumber.g:1740:1: ( RULE_EOL )
            // InternalCucumber.g:1740:3: RULE_EOL
            {
            match(input,RULE_EOL,FOLLOW_10); 

            }

             after(grammarAccess.getExamplesAccess().getEOLTerminalRuleCall_2()); 

            }

            // InternalCucumber.g:1743:1: ( ( RULE_EOL )* )
            // InternalCucumber.g:1744:1: ( RULE_EOL )*
            {
             before(grammarAccess.getExamplesAccess().getEOLTerminalRuleCall_2()); 
            // InternalCucumber.g:1745:1: ( RULE_EOL )*
            loop33:
            do {
                int alt33=2;
                int LA33_0 = input.LA(1);

                if ( (LA33_0==RULE_EOL) ) {
                    alt33=1;
                }


                switch (alt33) {
            	case 1 :
            	    // InternalCucumber.g:1745:3: RULE_EOL
            	    {
            	    match(input,RULE_EOL,FOLLOW_10); 

            	    }
            	    break;

            	default :
            	    break loop33;
                }
            } while (true);

             after(grammarAccess.getExamplesAccess().getEOLTerminalRuleCall_2()); 

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Examples__Group__2__Impl"


    // $ANTLR start "rule__Examples__Group__3"
    // InternalCucumber.g:1756:1: rule__Examples__Group__3 : rule__Examples__Group__3__Impl rule__Examples__Group__4 ;
    public final void rule__Examples__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:1760:1: ( rule__Examples__Group__3__Impl rule__Examples__Group__4 )
            // InternalCucumber.g:1761:2: rule__Examples__Group__3__Impl rule__Examples__Group__4
            {
            pushFollow(FOLLOW_20);
            rule__Examples__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Examples__Group__4();

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
    // $ANTLR end "rule__Examples__Group__3"


    // $ANTLR start "rule__Examples__Group__3__Impl"
    // InternalCucumber.g:1768:1: rule__Examples__Group__3__Impl : ( ( rule__Examples__NarrativeAssignment_3 )? ) ;
    public final void rule__Examples__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:1772:1: ( ( ( rule__Examples__NarrativeAssignment_3 )? ) )
            // InternalCucumber.g:1773:1: ( ( rule__Examples__NarrativeAssignment_3 )? )
            {
            // InternalCucumber.g:1773:1: ( ( rule__Examples__NarrativeAssignment_3 )? )
            // InternalCucumber.g:1774:1: ( rule__Examples__NarrativeAssignment_3 )?
            {
             before(grammarAccess.getExamplesAccess().getNarrativeAssignment_3()); 
            // InternalCucumber.g:1775:1: ( rule__Examples__NarrativeAssignment_3 )?
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( ((LA34_0>=RULE_WORD && LA34_0<=RULE_PLACEHOLDER)) ) {
                alt34=1;
            }
            switch (alt34) {
                case 1 :
                    // InternalCucumber.g:1775:2: rule__Examples__NarrativeAssignment_3
                    {
                    pushFollow(FOLLOW_2);
                    rule__Examples__NarrativeAssignment_3();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getExamplesAccess().getNarrativeAssignment_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Examples__Group__3__Impl"


    // $ANTLR start "rule__Examples__Group__4"
    // InternalCucumber.g:1785:1: rule__Examples__Group__4 : rule__Examples__Group__4__Impl ;
    public final void rule__Examples__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:1789:1: ( rule__Examples__Group__4__Impl )
            // InternalCucumber.g:1790:2: rule__Examples__Group__4__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Examples__Group__4__Impl();

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
    // $ANTLR end "rule__Examples__Group__4"


    // $ANTLR start "rule__Examples__Group__4__Impl"
    // InternalCucumber.g:1796:1: rule__Examples__Group__4__Impl : ( ( rule__Examples__TableAssignment_4 ) ) ;
    public final void rule__Examples__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:1800:1: ( ( ( rule__Examples__TableAssignment_4 ) ) )
            // InternalCucumber.g:1801:1: ( ( rule__Examples__TableAssignment_4 ) )
            {
            // InternalCucumber.g:1801:1: ( ( rule__Examples__TableAssignment_4 ) )
            // InternalCucumber.g:1802:1: ( rule__Examples__TableAssignment_4 )
            {
             before(grammarAccess.getExamplesAccess().getTableAssignment_4()); 
            // InternalCucumber.g:1803:1: ( rule__Examples__TableAssignment_4 )
            // InternalCucumber.g:1803:2: rule__Examples__TableAssignment_4
            {
            pushFollow(FOLLOW_2);
            rule__Examples__TableAssignment_4();

            state._fsp--;


            }

             after(grammarAccess.getExamplesAccess().getTableAssignment_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Examples__Group__4__Impl"


    // $ANTLR start "rule__Table__Group__0"
    // InternalCucumber.g:1823:1: rule__Table__Group__0 : rule__Table__Group__0__Impl rule__Table__Group__1 ;
    public final void rule__Table__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:1827:1: ( rule__Table__Group__0__Impl rule__Table__Group__1 )
            // InternalCucumber.g:1828:2: rule__Table__Group__0__Impl rule__Table__Group__1
            {
            pushFollow(FOLLOW_8);
            rule__Table__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Table__Group__1();

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
    // $ANTLR end "rule__Table__Group__0"


    // $ANTLR start "rule__Table__Group__0__Impl"
    // InternalCucumber.g:1835:1: rule__Table__Group__0__Impl : ( ( ( rule__Table__RowsAssignment_0 ) ) ( ( rule__Table__RowsAssignment_0 )* ) ) ;
    public final void rule__Table__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:1839:1: ( ( ( ( rule__Table__RowsAssignment_0 ) ) ( ( rule__Table__RowsAssignment_0 )* ) ) )
            // InternalCucumber.g:1840:1: ( ( ( rule__Table__RowsAssignment_0 ) ) ( ( rule__Table__RowsAssignment_0 )* ) )
            {
            // InternalCucumber.g:1840:1: ( ( ( rule__Table__RowsAssignment_0 ) ) ( ( rule__Table__RowsAssignment_0 )* ) )
            // InternalCucumber.g:1841:1: ( ( rule__Table__RowsAssignment_0 ) ) ( ( rule__Table__RowsAssignment_0 )* )
            {
            // InternalCucumber.g:1841:1: ( ( rule__Table__RowsAssignment_0 ) )
            // InternalCucumber.g:1842:1: ( rule__Table__RowsAssignment_0 )
            {
             before(grammarAccess.getTableAccess().getRowsAssignment_0()); 
            // InternalCucumber.g:1843:1: ( rule__Table__RowsAssignment_0 )
            // InternalCucumber.g:1843:2: rule__Table__RowsAssignment_0
            {
            pushFollow(FOLLOW_19);
            rule__Table__RowsAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getTableAccess().getRowsAssignment_0()); 

            }

            // InternalCucumber.g:1846:1: ( ( rule__Table__RowsAssignment_0 )* )
            // InternalCucumber.g:1847:1: ( rule__Table__RowsAssignment_0 )*
            {
             before(grammarAccess.getTableAccess().getRowsAssignment_0()); 
            // InternalCucumber.g:1848:1: ( rule__Table__RowsAssignment_0 )*
            loop35:
            do {
                int alt35=2;
                int LA35_0 = input.LA(1);

                if ( (LA35_0==RULE_TABLE_ROW) ) {
                    alt35=1;
                }


                switch (alt35) {
            	case 1 :
            	    // InternalCucumber.g:1848:2: rule__Table__RowsAssignment_0
            	    {
            	    pushFollow(FOLLOW_19);
            	    rule__Table__RowsAssignment_0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop35;
                }
            } while (true);

             after(grammarAccess.getTableAccess().getRowsAssignment_0()); 

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group__0__Impl"


    // $ANTLR start "rule__Table__Group__1"
    // InternalCucumber.g:1859:1: rule__Table__Group__1 : rule__Table__Group__1__Impl ;
    public final void rule__Table__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:1863:1: ( rule__Table__Group__1__Impl )
            // InternalCucumber.g:1864:2: rule__Table__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Table__Group__1__Impl();

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
    // $ANTLR end "rule__Table__Group__1"


    // $ANTLR start "rule__Table__Group__1__Impl"
    // InternalCucumber.g:1870:1: rule__Table__Group__1__Impl : ( ( RULE_EOL )* ) ;
    public final void rule__Table__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:1874:1: ( ( ( RULE_EOL )* ) )
            // InternalCucumber.g:1875:1: ( ( RULE_EOL )* )
            {
            // InternalCucumber.g:1875:1: ( ( RULE_EOL )* )
            // InternalCucumber.g:1876:1: ( RULE_EOL )*
            {
             before(grammarAccess.getTableAccess().getEOLTerminalRuleCall_1()); 
            // InternalCucumber.g:1877:1: ( RULE_EOL )*
            loop36:
            do {
                int alt36=2;
                int LA36_0 = input.LA(1);

                if ( (LA36_0==RULE_EOL) ) {
                    alt36=1;
                }


                switch (alt36) {
            	case 1 :
            	    // InternalCucumber.g:1877:3: RULE_EOL
            	    {
            	    match(input,RULE_EOL,FOLLOW_10); 

            	    }
            	    break;

            	default :
            	    break loop36;
                }
            } while (true);

             after(grammarAccess.getTableAccess().getEOLTerminalRuleCall_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group__1__Impl"


    // $ANTLR start "rule__DocString__Group__0"
    // InternalCucumber.g:1891:1: rule__DocString__Group__0 : rule__DocString__Group__0__Impl rule__DocString__Group__1 ;
    public final void rule__DocString__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:1895:1: ( rule__DocString__Group__0__Impl rule__DocString__Group__1 )
            // InternalCucumber.g:1896:2: rule__DocString__Group__0__Impl rule__DocString__Group__1
            {
            pushFollow(FOLLOW_8);
            rule__DocString__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__DocString__Group__1();

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
    // $ANTLR end "rule__DocString__Group__0"


    // $ANTLR start "rule__DocString__Group__0__Impl"
    // InternalCucumber.g:1903:1: rule__DocString__Group__0__Impl : ( ( rule__DocString__ContentAssignment_0 ) ) ;
    public final void rule__DocString__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:1907:1: ( ( ( rule__DocString__ContentAssignment_0 ) ) )
            // InternalCucumber.g:1908:1: ( ( rule__DocString__ContentAssignment_0 ) )
            {
            // InternalCucumber.g:1908:1: ( ( rule__DocString__ContentAssignment_0 ) )
            // InternalCucumber.g:1909:1: ( rule__DocString__ContentAssignment_0 )
            {
             before(grammarAccess.getDocStringAccess().getContentAssignment_0()); 
            // InternalCucumber.g:1910:1: ( rule__DocString__ContentAssignment_0 )
            // InternalCucumber.g:1910:2: rule__DocString__ContentAssignment_0
            {
            pushFollow(FOLLOW_2);
            rule__DocString__ContentAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getDocStringAccess().getContentAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DocString__Group__0__Impl"


    // $ANTLR start "rule__DocString__Group__1"
    // InternalCucumber.g:1920:1: rule__DocString__Group__1 : rule__DocString__Group__1__Impl ;
    public final void rule__DocString__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:1924:1: ( rule__DocString__Group__1__Impl )
            // InternalCucumber.g:1925:2: rule__DocString__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__DocString__Group__1__Impl();

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
    // $ANTLR end "rule__DocString__Group__1"


    // $ANTLR start "rule__DocString__Group__1__Impl"
    // InternalCucumber.g:1931:1: rule__DocString__Group__1__Impl : ( ( RULE_EOL )* ) ;
    public final void rule__DocString__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:1935:1: ( ( ( RULE_EOL )* ) )
            // InternalCucumber.g:1936:1: ( ( RULE_EOL )* )
            {
            // InternalCucumber.g:1936:1: ( ( RULE_EOL )* )
            // InternalCucumber.g:1937:1: ( RULE_EOL )*
            {
             before(grammarAccess.getDocStringAccess().getEOLTerminalRuleCall_1()); 
            // InternalCucumber.g:1938:1: ( RULE_EOL )*
            loop37:
            do {
                int alt37=2;
                int LA37_0 = input.LA(1);

                if ( (LA37_0==RULE_EOL) ) {
                    alt37=1;
                }


                switch (alt37) {
            	case 1 :
            	    // InternalCucumber.g:1938:3: RULE_EOL
            	    {
            	    match(input,RULE_EOL,FOLLOW_10); 

            	    }
            	    break;

            	default :
            	    break loop37;
                }
            } while (true);

             after(grammarAccess.getDocStringAccess().getEOLTerminalRuleCall_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DocString__Group__1__Impl"


    // $ANTLR start "rule__Title__Group__0"
    // InternalCucumber.g:1952:1: rule__Title__Group__0 : rule__Title__Group__0__Impl rule__Title__Group__1 ;
    public final void rule__Title__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:1956:1: ( rule__Title__Group__0__Impl rule__Title__Group__1 )
            // InternalCucumber.g:1957:2: rule__Title__Group__0__Impl rule__Title__Group__1
            {
            pushFollow(FOLLOW_17);
            rule__Title__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Title__Group__1();

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
    // $ANTLR end "rule__Title__Group__0"


    // $ANTLR start "rule__Title__Group__0__Impl"
    // InternalCucumber.g:1964:1: rule__Title__Group__0__Impl : ( ( rule__Title__Alternatives_0 ) ) ;
    public final void rule__Title__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:1968:1: ( ( ( rule__Title__Alternatives_0 ) ) )
            // InternalCucumber.g:1969:1: ( ( rule__Title__Alternatives_0 ) )
            {
            // InternalCucumber.g:1969:1: ( ( rule__Title__Alternatives_0 ) )
            // InternalCucumber.g:1970:1: ( rule__Title__Alternatives_0 )
            {
             before(grammarAccess.getTitleAccess().getAlternatives_0()); 
            // InternalCucumber.g:1971:1: ( rule__Title__Alternatives_0 )
            // InternalCucumber.g:1971:2: rule__Title__Alternatives_0
            {
            pushFollow(FOLLOW_2);
            rule__Title__Alternatives_0();

            state._fsp--;


            }

             after(grammarAccess.getTitleAccess().getAlternatives_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Title__Group__0__Impl"


    // $ANTLR start "rule__Title__Group__1"
    // InternalCucumber.g:1981:1: rule__Title__Group__1 : rule__Title__Group__1__Impl ;
    public final void rule__Title__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:1985:1: ( rule__Title__Group__1__Impl )
            // InternalCucumber.g:1986:2: rule__Title__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Title__Group__1__Impl();

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
    // $ANTLR end "rule__Title__Group__1"


    // $ANTLR start "rule__Title__Group__1__Impl"
    // InternalCucumber.g:1992:1: rule__Title__Group__1__Impl : ( ( rule__Title__Alternatives_1 )* ) ;
    public final void rule__Title__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:1996:1: ( ( ( rule__Title__Alternatives_1 )* ) )
            // InternalCucumber.g:1997:1: ( ( rule__Title__Alternatives_1 )* )
            {
            // InternalCucumber.g:1997:1: ( ( rule__Title__Alternatives_1 )* )
            // InternalCucumber.g:1998:1: ( rule__Title__Alternatives_1 )*
            {
             before(grammarAccess.getTitleAccess().getAlternatives_1()); 
            // InternalCucumber.g:1999:1: ( rule__Title__Alternatives_1 )*
            loop38:
            do {
                int alt38=2;
                int LA38_0 = input.LA(1);

                if ( ((LA38_0>=RULE_WORD && LA38_0<=RULE_TAGNAME)) ) {
                    alt38=1;
                }


                switch (alt38) {
            	case 1 :
            	    // InternalCucumber.g:1999:2: rule__Title__Alternatives_1
            	    {
            	    pushFollow(FOLLOW_4);
            	    rule__Title__Alternatives_1();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop38;
                }
            } while (true);

             after(grammarAccess.getTitleAccess().getAlternatives_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Title__Group__1__Impl"


    // $ANTLR start "rule__Narrative__Group__0"
    // InternalCucumber.g:2013:1: rule__Narrative__Group__0 : rule__Narrative__Group__0__Impl rule__Narrative__Group__1 ;
    public final void rule__Narrative__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:2017:1: ( rule__Narrative__Group__0__Impl rule__Narrative__Group__1 )
            // InternalCucumber.g:2018:2: rule__Narrative__Group__0__Impl rule__Narrative__Group__1
            {
            pushFollow(FOLLOW_21);
            rule__Narrative__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Narrative__Group__1();

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
    // $ANTLR end "rule__Narrative__Group__0"


    // $ANTLR start "rule__Narrative__Group__0__Impl"
    // InternalCucumber.g:2025:1: rule__Narrative__Group__0__Impl : ( ( rule__Narrative__Alternatives_0 ) ) ;
    public final void rule__Narrative__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:2029:1: ( ( ( rule__Narrative__Alternatives_0 ) ) )
            // InternalCucumber.g:2030:1: ( ( rule__Narrative__Alternatives_0 ) )
            {
            // InternalCucumber.g:2030:1: ( ( rule__Narrative__Alternatives_0 ) )
            // InternalCucumber.g:2031:1: ( rule__Narrative__Alternatives_0 )
            {
             before(grammarAccess.getNarrativeAccess().getAlternatives_0()); 
            // InternalCucumber.g:2032:1: ( rule__Narrative__Alternatives_0 )
            // InternalCucumber.g:2032:2: rule__Narrative__Alternatives_0
            {
            pushFollow(FOLLOW_2);
            rule__Narrative__Alternatives_0();

            state._fsp--;


            }

             after(grammarAccess.getNarrativeAccess().getAlternatives_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Narrative__Group__0__Impl"


    // $ANTLR start "rule__Narrative__Group__1"
    // InternalCucumber.g:2042:1: rule__Narrative__Group__1 : rule__Narrative__Group__1__Impl rule__Narrative__Group__2 ;
    public final void rule__Narrative__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:2046:1: ( rule__Narrative__Group__1__Impl rule__Narrative__Group__2 )
            // InternalCucumber.g:2047:2: rule__Narrative__Group__1__Impl rule__Narrative__Group__2
            {
            pushFollow(FOLLOW_21);
            rule__Narrative__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Narrative__Group__2();

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
    // $ANTLR end "rule__Narrative__Group__1"


    // $ANTLR start "rule__Narrative__Group__1__Impl"
    // InternalCucumber.g:2054:1: rule__Narrative__Group__1__Impl : ( ( rule__Narrative__Alternatives_1 )* ) ;
    public final void rule__Narrative__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:2058:1: ( ( ( rule__Narrative__Alternatives_1 )* ) )
            // InternalCucumber.g:2059:1: ( ( rule__Narrative__Alternatives_1 )* )
            {
            // InternalCucumber.g:2059:1: ( ( rule__Narrative__Alternatives_1 )* )
            // InternalCucumber.g:2060:1: ( rule__Narrative__Alternatives_1 )*
            {
             before(grammarAccess.getNarrativeAccess().getAlternatives_1()); 
            // InternalCucumber.g:2061:1: ( rule__Narrative__Alternatives_1 )*
            loop39:
            do {
                int alt39=2;
                int LA39_0 = input.LA(1);

                if ( ((LA39_0>=RULE_WORD && LA39_0<=RULE_TAGNAME)) ) {
                    alt39=1;
                }


                switch (alt39) {
            	case 1 :
            	    // InternalCucumber.g:2061:2: rule__Narrative__Alternatives_1
            	    {
            	    pushFollow(FOLLOW_4);
            	    rule__Narrative__Alternatives_1();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop39;
                }
            } while (true);

             after(grammarAccess.getNarrativeAccess().getAlternatives_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Narrative__Group__1__Impl"


    // $ANTLR start "rule__Narrative__Group__2"
    // InternalCucumber.g:2071:1: rule__Narrative__Group__2 : rule__Narrative__Group__2__Impl ;
    public final void rule__Narrative__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:2075:1: ( rule__Narrative__Group__2__Impl )
            // InternalCucumber.g:2076:2: rule__Narrative__Group__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Narrative__Group__2__Impl();

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
    // $ANTLR end "rule__Narrative__Group__2"


    // $ANTLR start "rule__Narrative__Group__2__Impl"
    // InternalCucumber.g:2082:1: rule__Narrative__Group__2__Impl : ( ( ( RULE_EOL ) ) ( ( RULE_EOL )* ) ) ;
    public final void rule__Narrative__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:2086:1: ( ( ( ( RULE_EOL ) ) ( ( RULE_EOL )* ) ) )
            // InternalCucumber.g:2087:1: ( ( ( RULE_EOL ) ) ( ( RULE_EOL )* ) )
            {
            // InternalCucumber.g:2087:1: ( ( ( RULE_EOL ) ) ( ( RULE_EOL )* ) )
            // InternalCucumber.g:2088:1: ( ( RULE_EOL ) ) ( ( RULE_EOL )* )
            {
            // InternalCucumber.g:2088:1: ( ( RULE_EOL ) )
            // InternalCucumber.g:2089:1: ( RULE_EOL )
            {
             before(grammarAccess.getNarrativeAccess().getEOLTerminalRuleCall_2()); 
            // InternalCucumber.g:2090:1: ( RULE_EOL )
            // InternalCucumber.g:2090:3: RULE_EOL
            {
            match(input,RULE_EOL,FOLLOW_10); 

            }

             after(grammarAccess.getNarrativeAccess().getEOLTerminalRuleCall_2()); 

            }

            // InternalCucumber.g:2093:1: ( ( RULE_EOL )* )
            // InternalCucumber.g:2094:1: ( RULE_EOL )*
            {
             before(grammarAccess.getNarrativeAccess().getEOLTerminalRuleCall_2()); 
            // InternalCucumber.g:2095:1: ( RULE_EOL )*
            loop40:
            do {
                int alt40=2;
                int LA40_0 = input.LA(1);

                if ( (LA40_0==RULE_EOL) ) {
                    alt40=1;
                }


                switch (alt40) {
            	case 1 :
            	    // InternalCucumber.g:2095:3: RULE_EOL
            	    {
            	    match(input,RULE_EOL,FOLLOW_10); 

            	    }
            	    break;

            	default :
            	    break loop40;
                }
            } while (true);

             after(grammarAccess.getNarrativeAccess().getEOLTerminalRuleCall_2()); 

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Narrative__Group__2__Impl"


    // $ANTLR start "rule__Tag__Group__0"
    // InternalCucumber.g:2112:1: rule__Tag__Group__0 : rule__Tag__Group__0__Impl rule__Tag__Group__1 ;
    public final void rule__Tag__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:2116:1: ( rule__Tag__Group__0__Impl rule__Tag__Group__1 )
            // InternalCucumber.g:2117:2: rule__Tag__Group__0__Impl rule__Tag__Group__1
            {
            pushFollow(FOLLOW_8);
            rule__Tag__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Tag__Group__1();

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
    // $ANTLR end "rule__Tag__Group__0"


    // $ANTLR start "rule__Tag__Group__0__Impl"
    // InternalCucumber.g:2124:1: rule__Tag__Group__0__Impl : ( ( rule__Tag__IdAssignment_0 ) ) ;
    public final void rule__Tag__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:2128:1: ( ( ( rule__Tag__IdAssignment_0 ) ) )
            // InternalCucumber.g:2129:1: ( ( rule__Tag__IdAssignment_0 ) )
            {
            // InternalCucumber.g:2129:1: ( ( rule__Tag__IdAssignment_0 ) )
            // InternalCucumber.g:2130:1: ( rule__Tag__IdAssignment_0 )
            {
             before(grammarAccess.getTagAccess().getIdAssignment_0()); 
            // InternalCucumber.g:2131:1: ( rule__Tag__IdAssignment_0 )
            // InternalCucumber.g:2131:2: rule__Tag__IdAssignment_0
            {
            pushFollow(FOLLOW_2);
            rule__Tag__IdAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getTagAccess().getIdAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Tag__Group__0__Impl"


    // $ANTLR start "rule__Tag__Group__1"
    // InternalCucumber.g:2141:1: rule__Tag__Group__1 : rule__Tag__Group__1__Impl ;
    public final void rule__Tag__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:2145:1: ( rule__Tag__Group__1__Impl )
            // InternalCucumber.g:2146:2: rule__Tag__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Tag__Group__1__Impl();

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
    // $ANTLR end "rule__Tag__Group__1"


    // $ANTLR start "rule__Tag__Group__1__Impl"
    // InternalCucumber.g:2152:1: rule__Tag__Group__1__Impl : ( ( RULE_EOL )? ) ;
    public final void rule__Tag__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:2156:1: ( ( ( RULE_EOL )? ) )
            // InternalCucumber.g:2157:1: ( ( RULE_EOL )? )
            {
            // InternalCucumber.g:2157:1: ( ( RULE_EOL )? )
            // InternalCucumber.g:2158:1: ( RULE_EOL )?
            {
             before(grammarAccess.getTagAccess().getEOLTerminalRuleCall_1()); 
            // InternalCucumber.g:2159:1: ( RULE_EOL )?
            int alt41=2;
            int LA41_0 = input.LA(1);

            if ( (LA41_0==RULE_EOL) ) {
                alt41=1;
            }
            switch (alt41) {
                case 1 :
                    // InternalCucumber.g:2159:3: RULE_EOL
                    {
                    match(input,RULE_EOL,FOLLOW_2); 

                    }
                    break;

            }

             after(grammarAccess.getTagAccess().getEOLTerminalRuleCall_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Tag__Group__1__Impl"


    // $ANTLR start "rule__Feature__TagsAssignment_0"
    // InternalCucumber.g:2174:1: rule__Feature__TagsAssignment_0 : ( ruleTag ) ;
    public final void rule__Feature__TagsAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:2178:1: ( ( ruleTag ) )
            // InternalCucumber.g:2179:1: ( ruleTag )
            {
            // InternalCucumber.g:2179:1: ( ruleTag )
            // InternalCucumber.g:2180:1: ruleTag
            {
             before(grammarAccess.getFeatureAccess().getTagsTagParserRuleCall_0_0()); 
            pushFollow(FOLLOW_2);
            ruleTag();

            state._fsp--;

             after(grammarAccess.getFeatureAccess().getTagsTagParserRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Feature__TagsAssignment_0"


    // $ANTLR start "rule__Feature__TitleAssignment_2"
    // InternalCucumber.g:2189:1: rule__Feature__TitleAssignment_2 : ( ruleTitle ) ;
    public final void rule__Feature__TitleAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:2193:1: ( ( ruleTitle ) )
            // InternalCucumber.g:2194:1: ( ruleTitle )
            {
            // InternalCucumber.g:2194:1: ( ruleTitle )
            // InternalCucumber.g:2195:1: ruleTitle
            {
             before(grammarAccess.getFeatureAccess().getTitleTitleParserRuleCall_2_0()); 
            pushFollow(FOLLOW_2);
            ruleTitle();

            state._fsp--;

             after(grammarAccess.getFeatureAccess().getTitleTitleParserRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Feature__TitleAssignment_2"


    // $ANTLR start "rule__Feature__NarrativeAssignment_4"
    // InternalCucumber.g:2204:1: rule__Feature__NarrativeAssignment_4 : ( ruleNarrative ) ;
    public final void rule__Feature__NarrativeAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:2208:1: ( ( ruleNarrative ) )
            // InternalCucumber.g:2209:1: ( ruleNarrative )
            {
            // InternalCucumber.g:2209:1: ( ruleNarrative )
            // InternalCucumber.g:2210:1: ruleNarrative
            {
             before(grammarAccess.getFeatureAccess().getNarrativeNarrativeParserRuleCall_4_0()); 
            pushFollow(FOLLOW_2);
            ruleNarrative();

            state._fsp--;

             after(grammarAccess.getFeatureAccess().getNarrativeNarrativeParserRuleCall_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Feature__NarrativeAssignment_4"


    // $ANTLR start "rule__Feature__BackgroundAssignment_5"
    // InternalCucumber.g:2219:1: rule__Feature__BackgroundAssignment_5 : ( ruleBackground ) ;
    public final void rule__Feature__BackgroundAssignment_5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:2223:1: ( ( ruleBackground ) )
            // InternalCucumber.g:2224:1: ( ruleBackground )
            {
            // InternalCucumber.g:2224:1: ( ruleBackground )
            // InternalCucumber.g:2225:1: ruleBackground
            {
             before(grammarAccess.getFeatureAccess().getBackgroundBackgroundParserRuleCall_5_0()); 
            pushFollow(FOLLOW_2);
            ruleBackground();

            state._fsp--;

             after(grammarAccess.getFeatureAccess().getBackgroundBackgroundParserRuleCall_5_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Feature__BackgroundAssignment_5"


    // $ANTLR start "rule__Feature__ScenariosAssignment_6"
    // InternalCucumber.g:2234:1: rule__Feature__ScenariosAssignment_6 : ( ( rule__Feature__ScenariosAlternatives_6_0 ) ) ;
    public final void rule__Feature__ScenariosAssignment_6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:2238:1: ( ( ( rule__Feature__ScenariosAlternatives_6_0 ) ) )
            // InternalCucumber.g:2239:1: ( ( rule__Feature__ScenariosAlternatives_6_0 ) )
            {
            // InternalCucumber.g:2239:1: ( ( rule__Feature__ScenariosAlternatives_6_0 ) )
            // InternalCucumber.g:2240:1: ( rule__Feature__ScenariosAlternatives_6_0 )
            {
             before(grammarAccess.getFeatureAccess().getScenariosAlternatives_6_0()); 
            // InternalCucumber.g:2241:1: ( rule__Feature__ScenariosAlternatives_6_0 )
            // InternalCucumber.g:2241:2: rule__Feature__ScenariosAlternatives_6_0
            {
            pushFollow(FOLLOW_2);
            rule__Feature__ScenariosAlternatives_6_0();

            state._fsp--;


            }

             after(grammarAccess.getFeatureAccess().getScenariosAlternatives_6_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Feature__ScenariosAssignment_6"


    // $ANTLR start "rule__Background__TitleAssignment_1"
    // InternalCucumber.g:2250:1: rule__Background__TitleAssignment_1 : ( ruleTitle ) ;
    public final void rule__Background__TitleAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:2254:1: ( ( ruleTitle ) )
            // InternalCucumber.g:2255:1: ( ruleTitle )
            {
            // InternalCucumber.g:2255:1: ( ruleTitle )
            // InternalCucumber.g:2256:1: ruleTitle
            {
             before(grammarAccess.getBackgroundAccess().getTitleTitleParserRuleCall_1_0()); 
            pushFollow(FOLLOW_2);
            ruleTitle();

            state._fsp--;

             after(grammarAccess.getBackgroundAccess().getTitleTitleParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Background__TitleAssignment_1"


    // $ANTLR start "rule__Background__NarrativeAssignment_3"
    // InternalCucumber.g:2265:1: rule__Background__NarrativeAssignment_3 : ( ruleNarrative ) ;
    public final void rule__Background__NarrativeAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:2269:1: ( ( ruleNarrative ) )
            // InternalCucumber.g:2270:1: ( ruleNarrative )
            {
            // InternalCucumber.g:2270:1: ( ruleNarrative )
            // InternalCucumber.g:2271:1: ruleNarrative
            {
             before(grammarAccess.getBackgroundAccess().getNarrativeNarrativeParserRuleCall_3_0()); 
            pushFollow(FOLLOW_2);
            ruleNarrative();

            state._fsp--;

             after(grammarAccess.getBackgroundAccess().getNarrativeNarrativeParserRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Background__NarrativeAssignment_3"


    // $ANTLR start "rule__Background__StepsAssignment_4"
    // InternalCucumber.g:2280:1: rule__Background__StepsAssignment_4 : ( ruleStep ) ;
    public final void rule__Background__StepsAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:2284:1: ( ( ruleStep ) )
            // InternalCucumber.g:2285:1: ( ruleStep )
            {
            // InternalCucumber.g:2285:1: ( ruleStep )
            // InternalCucumber.g:2286:1: ruleStep
            {
             before(grammarAccess.getBackgroundAccess().getStepsStepParserRuleCall_4_0()); 
            pushFollow(FOLLOW_2);
            ruleStep();

            state._fsp--;

             after(grammarAccess.getBackgroundAccess().getStepsStepParserRuleCall_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Background__StepsAssignment_4"


    // $ANTLR start "rule__Scenario__TagsAssignment_0"
    // InternalCucumber.g:2295:1: rule__Scenario__TagsAssignment_0 : ( ruleTag ) ;
    public final void rule__Scenario__TagsAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:2299:1: ( ( ruleTag ) )
            // InternalCucumber.g:2300:1: ( ruleTag )
            {
            // InternalCucumber.g:2300:1: ( ruleTag )
            // InternalCucumber.g:2301:1: ruleTag
            {
             before(grammarAccess.getScenarioAccess().getTagsTagParserRuleCall_0_0()); 
            pushFollow(FOLLOW_2);
            ruleTag();

            state._fsp--;

             after(grammarAccess.getScenarioAccess().getTagsTagParserRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Scenario__TagsAssignment_0"


    // $ANTLR start "rule__Scenario__TitleAssignment_2"
    // InternalCucumber.g:2310:1: rule__Scenario__TitleAssignment_2 : ( ruleTitle ) ;
    public final void rule__Scenario__TitleAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:2314:1: ( ( ruleTitle ) )
            // InternalCucumber.g:2315:1: ( ruleTitle )
            {
            // InternalCucumber.g:2315:1: ( ruleTitle )
            // InternalCucumber.g:2316:1: ruleTitle
            {
             before(grammarAccess.getScenarioAccess().getTitleTitleParserRuleCall_2_0()); 
            pushFollow(FOLLOW_2);
            ruleTitle();

            state._fsp--;

             after(grammarAccess.getScenarioAccess().getTitleTitleParserRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Scenario__TitleAssignment_2"


    // $ANTLR start "rule__Scenario__NarrativeAssignment_4"
    // InternalCucumber.g:2325:1: rule__Scenario__NarrativeAssignment_4 : ( ruleNarrative ) ;
    public final void rule__Scenario__NarrativeAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:2329:1: ( ( ruleNarrative ) )
            // InternalCucumber.g:2330:1: ( ruleNarrative )
            {
            // InternalCucumber.g:2330:1: ( ruleNarrative )
            // InternalCucumber.g:2331:1: ruleNarrative
            {
             before(grammarAccess.getScenarioAccess().getNarrativeNarrativeParserRuleCall_4_0()); 
            pushFollow(FOLLOW_2);
            ruleNarrative();

            state._fsp--;

             after(grammarAccess.getScenarioAccess().getNarrativeNarrativeParserRuleCall_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Scenario__NarrativeAssignment_4"


    // $ANTLR start "rule__Scenario__StepsAssignment_5"
    // InternalCucumber.g:2340:1: rule__Scenario__StepsAssignment_5 : ( ruleStep ) ;
    public final void rule__Scenario__StepsAssignment_5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:2344:1: ( ( ruleStep ) )
            // InternalCucumber.g:2345:1: ( ruleStep )
            {
            // InternalCucumber.g:2345:1: ( ruleStep )
            // InternalCucumber.g:2346:1: ruleStep
            {
             before(grammarAccess.getScenarioAccess().getStepsStepParserRuleCall_5_0()); 
            pushFollow(FOLLOW_2);
            ruleStep();

            state._fsp--;

             after(grammarAccess.getScenarioAccess().getStepsStepParserRuleCall_5_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Scenario__StepsAssignment_5"


    // $ANTLR start "rule__ScenarioOutline__TagsAssignment_0"
    // InternalCucumber.g:2355:1: rule__ScenarioOutline__TagsAssignment_0 : ( ruleTag ) ;
    public final void rule__ScenarioOutline__TagsAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:2359:1: ( ( ruleTag ) )
            // InternalCucumber.g:2360:1: ( ruleTag )
            {
            // InternalCucumber.g:2360:1: ( ruleTag )
            // InternalCucumber.g:2361:1: ruleTag
            {
             before(grammarAccess.getScenarioOutlineAccess().getTagsTagParserRuleCall_0_0()); 
            pushFollow(FOLLOW_2);
            ruleTag();

            state._fsp--;

             after(grammarAccess.getScenarioOutlineAccess().getTagsTagParserRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ScenarioOutline__TagsAssignment_0"


    // $ANTLR start "rule__ScenarioOutline__TitleAssignment_2"
    // InternalCucumber.g:2370:1: rule__ScenarioOutline__TitleAssignment_2 : ( ruleTitle ) ;
    public final void rule__ScenarioOutline__TitleAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:2374:1: ( ( ruleTitle ) )
            // InternalCucumber.g:2375:1: ( ruleTitle )
            {
            // InternalCucumber.g:2375:1: ( ruleTitle )
            // InternalCucumber.g:2376:1: ruleTitle
            {
             before(grammarAccess.getScenarioOutlineAccess().getTitleTitleParserRuleCall_2_0()); 
            pushFollow(FOLLOW_2);
            ruleTitle();

            state._fsp--;

             after(grammarAccess.getScenarioOutlineAccess().getTitleTitleParserRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ScenarioOutline__TitleAssignment_2"


    // $ANTLR start "rule__ScenarioOutline__NarrativeAssignment_4"
    // InternalCucumber.g:2385:1: rule__ScenarioOutline__NarrativeAssignment_4 : ( ruleNarrative ) ;
    public final void rule__ScenarioOutline__NarrativeAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:2389:1: ( ( ruleNarrative ) )
            // InternalCucumber.g:2390:1: ( ruleNarrative )
            {
            // InternalCucumber.g:2390:1: ( ruleNarrative )
            // InternalCucumber.g:2391:1: ruleNarrative
            {
             before(grammarAccess.getScenarioOutlineAccess().getNarrativeNarrativeParserRuleCall_4_0()); 
            pushFollow(FOLLOW_2);
            ruleNarrative();

            state._fsp--;

             after(grammarAccess.getScenarioOutlineAccess().getNarrativeNarrativeParserRuleCall_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ScenarioOutline__NarrativeAssignment_4"


    // $ANTLR start "rule__ScenarioOutline__StepsAssignment_5"
    // InternalCucumber.g:2400:1: rule__ScenarioOutline__StepsAssignment_5 : ( ruleStep ) ;
    public final void rule__ScenarioOutline__StepsAssignment_5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:2404:1: ( ( ruleStep ) )
            // InternalCucumber.g:2405:1: ( ruleStep )
            {
            // InternalCucumber.g:2405:1: ( ruleStep )
            // InternalCucumber.g:2406:1: ruleStep
            {
             before(grammarAccess.getScenarioOutlineAccess().getStepsStepParserRuleCall_5_0()); 
            pushFollow(FOLLOW_2);
            ruleStep();

            state._fsp--;

             after(grammarAccess.getScenarioOutlineAccess().getStepsStepParserRuleCall_5_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ScenarioOutline__StepsAssignment_5"


    // $ANTLR start "rule__ScenarioOutline__ExamplesAssignment_6"
    // InternalCucumber.g:2415:1: rule__ScenarioOutline__ExamplesAssignment_6 : ( ruleExamples ) ;
    public final void rule__ScenarioOutline__ExamplesAssignment_6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:2419:1: ( ( ruleExamples ) )
            // InternalCucumber.g:2420:1: ( ruleExamples )
            {
            // InternalCucumber.g:2420:1: ( ruleExamples )
            // InternalCucumber.g:2421:1: ruleExamples
            {
             before(grammarAccess.getScenarioOutlineAccess().getExamplesExamplesParserRuleCall_6_0()); 
            pushFollow(FOLLOW_2);
            ruleExamples();

            state._fsp--;

             after(grammarAccess.getScenarioOutlineAccess().getExamplesExamplesParserRuleCall_6_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ScenarioOutline__ExamplesAssignment_6"


    // $ANTLR start "rule__Step__KeywordAssignment_0"
    // InternalCucumber.g:2430:1: rule__Step__KeywordAssignment_0 : ( RULE_STEP_KEYWORD ) ;
    public final void rule__Step__KeywordAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:2434:1: ( ( RULE_STEP_KEYWORD ) )
            // InternalCucumber.g:2435:1: ( RULE_STEP_KEYWORD )
            {
            // InternalCucumber.g:2435:1: ( RULE_STEP_KEYWORD )
            // InternalCucumber.g:2436:1: RULE_STEP_KEYWORD
            {
             before(grammarAccess.getStepAccess().getKeywordSTEP_KEYWORDTerminalRuleCall_0_0()); 
            match(input,RULE_STEP_KEYWORD,FOLLOW_2); 
             after(grammarAccess.getStepAccess().getKeywordSTEP_KEYWORDTerminalRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Step__KeywordAssignment_0"


    // $ANTLR start "rule__Step__DescriptionAssignment_1"
    // InternalCucumber.g:2445:1: rule__Step__DescriptionAssignment_1 : ( ruleStepDescription ) ;
    public final void rule__Step__DescriptionAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:2449:1: ( ( ruleStepDescription ) )
            // InternalCucumber.g:2450:1: ( ruleStepDescription )
            {
            // InternalCucumber.g:2450:1: ( ruleStepDescription )
            // InternalCucumber.g:2451:1: ruleStepDescription
            {
             before(grammarAccess.getStepAccess().getDescriptionStepDescriptionParserRuleCall_1_0()); 
            pushFollow(FOLLOW_2);
            ruleStepDescription();

            state._fsp--;

             after(grammarAccess.getStepAccess().getDescriptionStepDescriptionParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Step__DescriptionAssignment_1"


    // $ANTLR start "rule__Step__TablesAssignment_3"
    // InternalCucumber.g:2460:1: rule__Step__TablesAssignment_3 : ( ruleTable ) ;
    public final void rule__Step__TablesAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:2464:1: ( ( ruleTable ) )
            // InternalCucumber.g:2465:1: ( ruleTable )
            {
            // InternalCucumber.g:2465:1: ( ruleTable )
            // InternalCucumber.g:2466:1: ruleTable
            {
             before(grammarAccess.getStepAccess().getTablesTableParserRuleCall_3_0()); 
            pushFollow(FOLLOW_2);
            ruleTable();

            state._fsp--;

             after(grammarAccess.getStepAccess().getTablesTableParserRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Step__TablesAssignment_3"


    // $ANTLR start "rule__Step__CodeAssignment_4"
    // InternalCucumber.g:2475:1: rule__Step__CodeAssignment_4 : ( ruleDocString ) ;
    public final void rule__Step__CodeAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:2479:1: ( ( ruleDocString ) )
            // InternalCucumber.g:2480:1: ( ruleDocString )
            {
            // InternalCucumber.g:2480:1: ( ruleDocString )
            // InternalCucumber.g:2481:1: ruleDocString
            {
             before(grammarAccess.getStepAccess().getCodeDocStringParserRuleCall_4_0()); 
            pushFollow(FOLLOW_2);
            ruleDocString();

            state._fsp--;

             after(grammarAccess.getStepAccess().getCodeDocStringParserRuleCall_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Step__CodeAssignment_4"


    // $ANTLR start "rule__Step__TablesAssignment_5"
    // InternalCucumber.g:2490:1: rule__Step__TablesAssignment_5 : ( ruleTable ) ;
    public final void rule__Step__TablesAssignment_5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:2494:1: ( ( ruleTable ) )
            // InternalCucumber.g:2495:1: ( ruleTable )
            {
            // InternalCucumber.g:2495:1: ( ruleTable )
            // InternalCucumber.g:2496:1: ruleTable
            {
             before(grammarAccess.getStepAccess().getTablesTableParserRuleCall_5_0()); 
            pushFollow(FOLLOW_2);
            ruleTable();

            state._fsp--;

             after(grammarAccess.getStepAccess().getTablesTableParserRuleCall_5_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Step__TablesAssignment_5"


    // $ANTLR start "rule__Examples__TitleAssignment_1"
    // InternalCucumber.g:2505:1: rule__Examples__TitleAssignment_1 : ( ruleTitle ) ;
    public final void rule__Examples__TitleAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:2509:1: ( ( ruleTitle ) )
            // InternalCucumber.g:2510:1: ( ruleTitle )
            {
            // InternalCucumber.g:2510:1: ( ruleTitle )
            // InternalCucumber.g:2511:1: ruleTitle
            {
             before(grammarAccess.getExamplesAccess().getTitleTitleParserRuleCall_1_0()); 
            pushFollow(FOLLOW_2);
            ruleTitle();

            state._fsp--;

             after(grammarAccess.getExamplesAccess().getTitleTitleParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Examples__TitleAssignment_1"


    // $ANTLR start "rule__Examples__NarrativeAssignment_3"
    // InternalCucumber.g:2520:1: rule__Examples__NarrativeAssignment_3 : ( ruleNarrative ) ;
    public final void rule__Examples__NarrativeAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:2524:1: ( ( ruleNarrative ) )
            // InternalCucumber.g:2525:1: ( ruleNarrative )
            {
            // InternalCucumber.g:2525:1: ( ruleNarrative )
            // InternalCucumber.g:2526:1: ruleNarrative
            {
             before(grammarAccess.getExamplesAccess().getNarrativeNarrativeParserRuleCall_3_0()); 
            pushFollow(FOLLOW_2);
            ruleNarrative();

            state._fsp--;

             after(grammarAccess.getExamplesAccess().getNarrativeNarrativeParserRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Examples__NarrativeAssignment_3"


    // $ANTLR start "rule__Examples__TableAssignment_4"
    // InternalCucumber.g:2535:1: rule__Examples__TableAssignment_4 : ( ruleTable ) ;
    public final void rule__Examples__TableAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:2539:1: ( ( ruleTable ) )
            // InternalCucumber.g:2540:1: ( ruleTable )
            {
            // InternalCucumber.g:2540:1: ( ruleTable )
            // InternalCucumber.g:2541:1: ruleTable
            {
             before(grammarAccess.getExamplesAccess().getTableTableParserRuleCall_4_0()); 
            pushFollow(FOLLOW_2);
            ruleTable();

            state._fsp--;

             after(grammarAccess.getExamplesAccess().getTableTableParserRuleCall_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Examples__TableAssignment_4"


    // $ANTLR start "rule__Table__RowsAssignment_0"
    // InternalCucumber.g:2550:1: rule__Table__RowsAssignment_0 : ( RULE_TABLE_ROW ) ;
    public final void rule__Table__RowsAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:2554:1: ( ( RULE_TABLE_ROW ) )
            // InternalCucumber.g:2555:1: ( RULE_TABLE_ROW )
            {
            // InternalCucumber.g:2555:1: ( RULE_TABLE_ROW )
            // InternalCucumber.g:2556:1: RULE_TABLE_ROW
            {
             before(grammarAccess.getTableAccess().getRowsTABLE_ROWTerminalRuleCall_0_0()); 
            match(input,RULE_TABLE_ROW,FOLLOW_2); 
             after(grammarAccess.getTableAccess().getRowsTABLE_ROWTerminalRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__RowsAssignment_0"


    // $ANTLR start "rule__DocString__ContentAssignment_0"
    // InternalCucumber.g:2565:1: rule__DocString__ContentAssignment_0 : ( RULE_DOC_STRING ) ;
    public final void rule__DocString__ContentAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:2569:1: ( ( RULE_DOC_STRING ) )
            // InternalCucumber.g:2570:1: ( RULE_DOC_STRING )
            {
            // InternalCucumber.g:2570:1: ( RULE_DOC_STRING )
            // InternalCucumber.g:2571:1: RULE_DOC_STRING
            {
             before(grammarAccess.getDocStringAccess().getContentDOC_STRINGTerminalRuleCall_0_0()); 
            match(input,RULE_DOC_STRING,FOLLOW_2); 
             after(grammarAccess.getDocStringAccess().getContentDOC_STRINGTerminalRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DocString__ContentAssignment_0"


    // $ANTLR start "rule__Tag__IdAssignment_0"
    // InternalCucumber.g:2580:1: rule__Tag__IdAssignment_0 : ( RULE_TAGNAME ) ;
    public final void rule__Tag__IdAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalCucumber.g:2584:1: ( ( RULE_TAGNAME ) )
            // InternalCucumber.g:2585:1: ( RULE_TAGNAME )
            {
            // InternalCucumber.g:2585:1: ( RULE_TAGNAME )
            // InternalCucumber.g:2586:1: RULE_TAGNAME
            {
             before(grammarAccess.getTagAccess().getIdTAGNAMETerminalRuleCall_0_0()); 
            match(input,RULE_TAGNAME,FOLLOW_2); 
             after(grammarAccess.getTagAccess().getIdTAGNAMETerminalRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Tag__IdAssignment_0"

    // Delegated rules


    protected DFA2 dfa2 = new DFA2(this);
    protected DFA3 dfa3 = new DFA3(this);
    static final String dfa_1s = "\12\uffff";
    static final String dfa_2s = "\1\1\1\uffff\1\11\7\uffff";
    static final String dfa_3s = "\1\4\1\uffff\1\4\7\uffff";
    static final String dfa_4s = "\1\24\1\uffff\1\24\7\uffff";
    static final String dfa_5s = "\1\uffff\1\2\1\uffff\7\1";
    static final String dfa_6s = "\12\uffff}>";
    static final String[] dfa_7s = {
            "\4\3\1\2\1\3\3\1\5\uffff\3\1",
            "",
            "\1\4\1\5\1\6\1\7\1\10\4\11\5\uffff\3\11",
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
            return "()* loopback of 369:1: ( rule__StepDescription__Alternatives )*";
        }
    }
    static final String dfa_8s = "\5\uffff";
    static final String dfa_9s = "\2\11\2\uffff\1\11";
    static final String dfa_10s = "\2\23\2\uffff\1\23";
    static final String dfa_11s = "\2\uffff\1\1\1\2\1\uffff";
    static final String dfa_12s = "\5\uffff}>";
    static final String[] dfa_13s = {
            "\1\1\10\uffff\1\2\1\3",
            "\1\1\1\4\7\uffff\1\2\1\3",
            "",
            "",
            "\1\1\10\uffff\1\2\1\3"
    };

    static final short[] dfa_8 = DFA.unpackEncodedString(dfa_8s);
    static final char[] dfa_9 = DFA.unpackEncodedStringToUnsignedChars(dfa_9s);
    static final char[] dfa_10 = DFA.unpackEncodedStringToUnsignedChars(dfa_10s);
    static final short[] dfa_11 = DFA.unpackEncodedString(dfa_11s);
    static final short[] dfa_12 = DFA.unpackEncodedString(dfa_12s);
    static final short[][] dfa_13 = unpackEncodedStringArray(dfa_13s);

    class DFA3 extends DFA {

        public DFA3(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 3;
            this.eot = dfa_8;
            this.eof = dfa_8;
            this.min = dfa_9;
            this.max = dfa_10;
            this.accept = dfa_11;
            this.special = dfa_12;
            this.transition = dfa_13;
        }
        public String getDescription() {
            return "410:1: rule__Feature__ScenariosAlternatives_6_0 : ( ( ruleScenario ) | ( ruleScenarioOutline ) );";
        }
    }
 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x00000000000000F2L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x00000000000003F2L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000000202L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x00000000000000F0L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x00000000000E02F0L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000000402L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x00000000000E02F2L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x00000000000004F0L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x00000000000001F0L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x00000000000001F2L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000000040200L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x00000000000003F0L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000000001C00L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x00000000000008F0L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x00000000000007F0L});

}