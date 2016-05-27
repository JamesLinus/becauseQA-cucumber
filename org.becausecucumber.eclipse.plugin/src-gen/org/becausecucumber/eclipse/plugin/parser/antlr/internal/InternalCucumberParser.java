package org.becausecucumber.eclipse.plugin.parser.antlr.internal; 

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import org.becausecucumber.eclipse.plugin.services.CucumberGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalCucumberParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_EOL", "RULE_STEP_KEYWORD", "RULE_TABLE_ROW", "RULE_DOC_STRING", "RULE_WORD", "RULE_INT", "RULE_STRING", "RULE_PLACEHOLDER", "RULE_TAGNAME", "RULE_NL", "RULE_SL_COMMENT", "RULE_WS", "'Feature:'", "'Background:'", "'Scenario:'", "'Scenario Outline:'", "'Examples:'"
    };
    public static final int RULE_DOC_STRING=7;
    public static final int RULE_WORD=8;
    public static final int RULE_STRING=10;
    public static final int RULE_SL_COMMENT=14;
    public static final int T__19=19;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int EOF=-1;
    public static final int RULE_EOL=4;
    public static final int RULE_WS=15;
    public static final int RULE_TAGNAME=12;
    public static final int RULE_PLACEHOLDER=11;
    public static final int RULE_TABLE_ROW=6;
    public static final int RULE_INT=9;
    public static final int RULE_STEP_KEYWORD=5;
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
     	
        public InternalCucumberParser(TokenStream input, CucumberGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }
        
        @Override
        protected String getFirstRuleName() {
        	return "Feature";	
       	}
       	
       	@Override
       	protected CucumberGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}



    // $ANTLR start "entryRuleFeature"
    // InternalCucumber.g:67:1: entryRuleFeature returns [EObject current=null] : iv_ruleFeature= ruleFeature EOF ;
    public final EObject entryRuleFeature() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFeature = null;


        try {
            // InternalCucumber.g:68:2: (iv_ruleFeature= ruleFeature EOF )
            // InternalCucumber.g:69:2: iv_ruleFeature= ruleFeature EOF
            {
             newCompositeNode(grammarAccess.getFeatureRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleFeature=ruleFeature();

            state._fsp--;

             current =iv_ruleFeature; 
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
    // $ANTLR end "entryRuleFeature"


    // $ANTLR start "ruleFeature"
    // InternalCucumber.g:76:1: ruleFeature returns [EObject current=null] : ( ( (lv_tags_0_0= ruleTag ) )* otherlv_1= 'Feature:' ( (lv_title_2_0= ruleTitle ) ) (this_EOL_3= RULE_EOL )+ ( (lv_narrative_4_0= ruleNarrative ) )? ( (lv_background_5_0= ruleBackground ) )? ( ( (lv_scenarios_6_1= ruleScenario | lv_scenarios_6_2= ruleScenarioOutline ) ) )+ ) ;
    public final EObject ruleFeature() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token this_EOL_3=null;
        EObject lv_tags_0_0 = null;

        AntlrDatatypeRuleToken lv_title_2_0 = null;

        AntlrDatatypeRuleToken lv_narrative_4_0 = null;

        EObject lv_background_5_0 = null;

        EObject lv_scenarios_6_1 = null;

        EObject lv_scenarios_6_2 = null;


         enterRule(); 
            
        try {
            // InternalCucumber.g:79:28: ( ( ( (lv_tags_0_0= ruleTag ) )* otherlv_1= 'Feature:' ( (lv_title_2_0= ruleTitle ) ) (this_EOL_3= RULE_EOL )+ ( (lv_narrative_4_0= ruleNarrative ) )? ( (lv_background_5_0= ruleBackground ) )? ( ( (lv_scenarios_6_1= ruleScenario | lv_scenarios_6_2= ruleScenarioOutline ) ) )+ ) )
            // InternalCucumber.g:80:1: ( ( (lv_tags_0_0= ruleTag ) )* otherlv_1= 'Feature:' ( (lv_title_2_0= ruleTitle ) ) (this_EOL_3= RULE_EOL )+ ( (lv_narrative_4_0= ruleNarrative ) )? ( (lv_background_5_0= ruleBackground ) )? ( ( (lv_scenarios_6_1= ruleScenario | lv_scenarios_6_2= ruleScenarioOutline ) ) )+ )
            {
            // InternalCucumber.g:80:1: ( ( (lv_tags_0_0= ruleTag ) )* otherlv_1= 'Feature:' ( (lv_title_2_0= ruleTitle ) ) (this_EOL_3= RULE_EOL )+ ( (lv_narrative_4_0= ruleNarrative ) )? ( (lv_background_5_0= ruleBackground ) )? ( ( (lv_scenarios_6_1= ruleScenario | lv_scenarios_6_2= ruleScenarioOutline ) ) )+ )
            // InternalCucumber.g:80:2: ( (lv_tags_0_0= ruleTag ) )* otherlv_1= 'Feature:' ( (lv_title_2_0= ruleTitle ) ) (this_EOL_3= RULE_EOL )+ ( (lv_narrative_4_0= ruleNarrative ) )? ( (lv_background_5_0= ruleBackground ) )? ( ( (lv_scenarios_6_1= ruleScenario | lv_scenarios_6_2= ruleScenarioOutline ) ) )+
            {
            // InternalCucumber.g:80:2: ( (lv_tags_0_0= ruleTag ) )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==RULE_TAGNAME) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // InternalCucumber.g:81:1: (lv_tags_0_0= ruleTag )
            	    {
            	    // InternalCucumber.g:81:1: (lv_tags_0_0= ruleTag )
            	    // InternalCucumber.g:82:3: lv_tags_0_0= ruleTag
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getFeatureAccess().getTagsTagParserRuleCall_0_0()); 
            	    	    
            	    pushFollow(FOLLOW_3);
            	    lv_tags_0_0=ruleTag();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getFeatureRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"tags",
            	            		lv_tags_0_0, 
            	            		"org.becausecucumber.eclipse.plugin.Cucumber.Tag");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

            otherlv_1=(Token)match(input,16,FOLLOW_4); 

                	newLeafNode(otherlv_1, grammarAccess.getFeatureAccess().getFeatureKeyword_1());
                
            // InternalCucumber.g:102:1: ( (lv_title_2_0= ruleTitle ) )
            // InternalCucumber.g:103:1: (lv_title_2_0= ruleTitle )
            {
            // InternalCucumber.g:103:1: (lv_title_2_0= ruleTitle )
            // InternalCucumber.g:104:3: lv_title_2_0= ruleTitle
            {
             
            	        newCompositeNode(grammarAccess.getFeatureAccess().getTitleTitleParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_5);
            lv_title_2_0=ruleTitle();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getFeatureRule());
            	        }
                   		set(
                   			current, 
                   			"title",
                    		lv_title_2_0, 
                    		"org.becausecucumber.eclipse.plugin.Cucumber.Title");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // InternalCucumber.g:120:2: (this_EOL_3= RULE_EOL )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==RULE_EOL) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // InternalCucumber.g:120:3: this_EOL_3= RULE_EOL
            	    {
            	    this_EOL_3=(Token)match(input,RULE_EOL,FOLLOW_6); 
            	     
            	        newLeafNode(this_EOL_3, grammarAccess.getFeatureAccess().getEOLTerminalRuleCall_3()); 
            	        

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

            // InternalCucumber.g:124:3: ( (lv_narrative_4_0= ruleNarrative ) )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( ((LA3_0>=RULE_WORD && LA3_0<=RULE_PLACEHOLDER)) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // InternalCucumber.g:125:1: (lv_narrative_4_0= ruleNarrative )
                    {
                    // InternalCucumber.g:125:1: (lv_narrative_4_0= ruleNarrative )
                    // InternalCucumber.g:126:3: lv_narrative_4_0= ruleNarrative
                    {
                     
                    	        newCompositeNode(grammarAccess.getFeatureAccess().getNarrativeNarrativeParserRuleCall_4_0()); 
                    	    
                    pushFollow(FOLLOW_7);
                    lv_narrative_4_0=ruleNarrative();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getFeatureRule());
                    	        }
                           		set(
                           			current, 
                           			"narrative",
                            		lv_narrative_4_0, 
                            		"org.becausecucumber.eclipse.plugin.Cucumber.Narrative");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }
                    break;

            }

            // InternalCucumber.g:142:3: ( (lv_background_5_0= ruleBackground ) )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==17) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // InternalCucumber.g:143:1: (lv_background_5_0= ruleBackground )
                    {
                    // InternalCucumber.g:143:1: (lv_background_5_0= ruleBackground )
                    // InternalCucumber.g:144:3: lv_background_5_0= ruleBackground
                    {
                     
                    	        newCompositeNode(grammarAccess.getFeatureAccess().getBackgroundBackgroundParserRuleCall_5_0()); 
                    	    
                    pushFollow(FOLLOW_7);
                    lv_background_5_0=ruleBackground();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getFeatureRule());
                    	        }
                           		set(
                           			current, 
                           			"background",
                            		lv_background_5_0, 
                            		"org.becausecucumber.eclipse.plugin.Cucumber.Background");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }
                    break;

            }

            // InternalCucumber.g:160:3: ( ( (lv_scenarios_6_1= ruleScenario | lv_scenarios_6_2= ruleScenarioOutline ) ) )+
            int cnt6=0;
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==RULE_TAGNAME||(LA6_0>=18 && LA6_0<=19)) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // InternalCucumber.g:161:1: ( (lv_scenarios_6_1= ruleScenario | lv_scenarios_6_2= ruleScenarioOutline ) )
            	    {
            	    // InternalCucumber.g:161:1: ( (lv_scenarios_6_1= ruleScenario | lv_scenarios_6_2= ruleScenarioOutline ) )
            	    // InternalCucumber.g:162:1: (lv_scenarios_6_1= ruleScenario | lv_scenarios_6_2= ruleScenarioOutline )
            	    {
            	    // InternalCucumber.g:162:1: (lv_scenarios_6_1= ruleScenario | lv_scenarios_6_2= ruleScenarioOutline )
            	    int alt5=2;
            	    alt5 = dfa5.predict(input);
            	    switch (alt5) {
            	        case 1 :
            	            // InternalCucumber.g:163:3: lv_scenarios_6_1= ruleScenario
            	            {
            	             
            	            	        newCompositeNode(grammarAccess.getFeatureAccess().getScenariosScenarioParserRuleCall_6_0_0()); 
            	            	    
            	            pushFollow(FOLLOW_8);
            	            lv_scenarios_6_1=ruleScenario();

            	            state._fsp--;


            	            	        if (current==null) {
            	            	            current = createModelElementForParent(grammarAccess.getFeatureRule());
            	            	        }
            	                   		add(
            	                   			current, 
            	                   			"scenarios",
            	                    		lv_scenarios_6_1, 
            	                    		"org.becausecucumber.eclipse.plugin.Cucumber.Scenario");
            	            	        afterParserOrEnumRuleCall();
            	            	    

            	            }
            	            break;
            	        case 2 :
            	            // InternalCucumber.g:178:8: lv_scenarios_6_2= ruleScenarioOutline
            	            {
            	             
            	            	        newCompositeNode(grammarAccess.getFeatureAccess().getScenariosScenarioOutlineParserRuleCall_6_0_1()); 
            	            	    
            	            pushFollow(FOLLOW_8);
            	            lv_scenarios_6_2=ruleScenarioOutline();

            	            state._fsp--;


            	            	        if (current==null) {
            	            	            current = createModelElementForParent(grammarAccess.getFeatureRule());
            	            	        }
            	                   		add(
            	                   			current, 
            	                   			"scenarios",
            	                    		lv_scenarios_6_2, 
            	                    		"org.becausecucumber.eclipse.plugin.Cucumber.ScenarioOutline");
            	            	        afterParserOrEnumRuleCall();
            	            	    

            	            }
            	            break;

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt6 >= 1 ) break loop6;
                        EarlyExitException eee =
                            new EarlyExitException(6, input);
                        throw eee;
                }
                cnt6++;
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
    // $ANTLR end "ruleFeature"


    // $ANTLR start "entryRuleBackground"
    // InternalCucumber.g:204:1: entryRuleBackground returns [EObject current=null] : iv_ruleBackground= ruleBackground EOF ;
    public final EObject entryRuleBackground() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBackground = null;


        try {
            // InternalCucumber.g:205:2: (iv_ruleBackground= ruleBackground EOF )
            // InternalCucumber.g:206:2: iv_ruleBackground= ruleBackground EOF
            {
             newCompositeNode(grammarAccess.getBackgroundRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleBackground=ruleBackground();

            state._fsp--;

             current =iv_ruleBackground; 
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
    // $ANTLR end "entryRuleBackground"


    // $ANTLR start "ruleBackground"
    // InternalCucumber.g:213:1: ruleBackground returns [EObject current=null] : (otherlv_0= 'Background:' ( (lv_title_1_0= ruleTitle ) )? (this_EOL_2= RULE_EOL )+ ( (lv_narrative_3_0= ruleNarrative ) )? ( (lv_steps_4_0= ruleStep ) )+ ) ;
    public final EObject ruleBackground() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token this_EOL_2=null;
        AntlrDatatypeRuleToken lv_title_1_0 = null;

        AntlrDatatypeRuleToken lv_narrative_3_0 = null;

        EObject lv_steps_4_0 = null;


         enterRule(); 
            
        try {
            // InternalCucumber.g:216:28: ( (otherlv_0= 'Background:' ( (lv_title_1_0= ruleTitle ) )? (this_EOL_2= RULE_EOL )+ ( (lv_narrative_3_0= ruleNarrative ) )? ( (lv_steps_4_0= ruleStep ) )+ ) )
            // InternalCucumber.g:217:1: (otherlv_0= 'Background:' ( (lv_title_1_0= ruleTitle ) )? (this_EOL_2= RULE_EOL )+ ( (lv_narrative_3_0= ruleNarrative ) )? ( (lv_steps_4_0= ruleStep ) )+ )
            {
            // InternalCucumber.g:217:1: (otherlv_0= 'Background:' ( (lv_title_1_0= ruleTitle ) )? (this_EOL_2= RULE_EOL )+ ( (lv_narrative_3_0= ruleNarrative ) )? ( (lv_steps_4_0= ruleStep ) )+ )
            // InternalCucumber.g:217:3: otherlv_0= 'Background:' ( (lv_title_1_0= ruleTitle ) )? (this_EOL_2= RULE_EOL )+ ( (lv_narrative_3_0= ruleNarrative ) )? ( (lv_steps_4_0= ruleStep ) )+
            {
            otherlv_0=(Token)match(input,17,FOLLOW_9); 

                	newLeafNode(otherlv_0, grammarAccess.getBackgroundAccess().getBackgroundKeyword_0());
                
            // InternalCucumber.g:221:1: ( (lv_title_1_0= ruleTitle ) )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( ((LA7_0>=RULE_WORD && LA7_0<=RULE_PLACEHOLDER)) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // InternalCucumber.g:222:1: (lv_title_1_0= ruleTitle )
                    {
                    // InternalCucumber.g:222:1: (lv_title_1_0= ruleTitle )
                    // InternalCucumber.g:223:3: lv_title_1_0= ruleTitle
                    {
                     
                    	        newCompositeNode(grammarAccess.getBackgroundAccess().getTitleTitleParserRuleCall_1_0()); 
                    	    
                    pushFollow(FOLLOW_5);
                    lv_title_1_0=ruleTitle();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getBackgroundRule());
                    	        }
                           		set(
                           			current, 
                           			"title",
                            		lv_title_1_0, 
                            		"org.becausecucumber.eclipse.plugin.Cucumber.Title");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }
                    break;

            }

            // InternalCucumber.g:239:3: (this_EOL_2= RULE_EOL )+
            int cnt8=0;
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==RULE_EOL) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // InternalCucumber.g:239:4: this_EOL_2= RULE_EOL
            	    {
            	    this_EOL_2=(Token)match(input,RULE_EOL,FOLLOW_10); 
            	     
            	        newLeafNode(this_EOL_2, grammarAccess.getBackgroundAccess().getEOLTerminalRuleCall_2()); 
            	        

            	    }
            	    break;

            	default :
            	    if ( cnt8 >= 1 ) break loop8;
                        EarlyExitException eee =
                            new EarlyExitException(8, input);
                        throw eee;
                }
                cnt8++;
            } while (true);

            // InternalCucumber.g:243:3: ( (lv_narrative_3_0= ruleNarrative ) )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( ((LA9_0>=RULE_WORD && LA9_0<=RULE_PLACEHOLDER)) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // InternalCucumber.g:244:1: (lv_narrative_3_0= ruleNarrative )
                    {
                    // InternalCucumber.g:244:1: (lv_narrative_3_0= ruleNarrative )
                    // InternalCucumber.g:245:3: lv_narrative_3_0= ruleNarrative
                    {
                     
                    	        newCompositeNode(grammarAccess.getBackgroundAccess().getNarrativeNarrativeParserRuleCall_3_0()); 
                    	    
                    pushFollow(FOLLOW_11);
                    lv_narrative_3_0=ruleNarrative();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getBackgroundRule());
                    	        }
                           		set(
                           			current, 
                           			"narrative",
                            		lv_narrative_3_0, 
                            		"org.becausecucumber.eclipse.plugin.Cucumber.Narrative");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }
                    break;

            }

            // InternalCucumber.g:261:3: ( (lv_steps_4_0= ruleStep ) )+
            int cnt10=0;
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==RULE_STEP_KEYWORD) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // InternalCucumber.g:262:1: (lv_steps_4_0= ruleStep )
            	    {
            	    // InternalCucumber.g:262:1: (lv_steps_4_0= ruleStep )
            	    // InternalCucumber.g:263:3: lv_steps_4_0= ruleStep
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getBackgroundAccess().getStepsStepParserRuleCall_4_0()); 
            	    	    
            	    pushFollow(FOLLOW_12);
            	    lv_steps_4_0=ruleStep();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getBackgroundRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"steps",
            	            		lv_steps_4_0, 
            	            		"org.becausecucumber.eclipse.plugin.Cucumber.Step");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt10 >= 1 ) break loop10;
                        EarlyExitException eee =
                            new EarlyExitException(10, input);
                        throw eee;
                }
                cnt10++;
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
    // $ANTLR end "ruleBackground"


    // $ANTLR start "entryRuleScenario"
    // InternalCucumber.g:287:1: entryRuleScenario returns [EObject current=null] : iv_ruleScenario= ruleScenario EOF ;
    public final EObject entryRuleScenario() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleScenario = null;


        try {
            // InternalCucumber.g:288:2: (iv_ruleScenario= ruleScenario EOF )
            // InternalCucumber.g:289:2: iv_ruleScenario= ruleScenario EOF
            {
             newCompositeNode(grammarAccess.getScenarioRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleScenario=ruleScenario();

            state._fsp--;

             current =iv_ruleScenario; 
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
    // $ANTLR end "entryRuleScenario"


    // $ANTLR start "ruleScenario"
    // InternalCucumber.g:296:1: ruleScenario returns [EObject current=null] : ( ( (lv_tags_0_0= ruleTag ) )* otherlv_1= 'Scenario:' ( (lv_title_2_0= ruleTitle ) )? (this_EOL_3= RULE_EOL )+ ( (lv_narrative_4_0= ruleNarrative ) )? ( (lv_steps_5_0= ruleStep ) )+ ) ;
    public final EObject ruleScenario() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token this_EOL_3=null;
        EObject lv_tags_0_0 = null;

        AntlrDatatypeRuleToken lv_title_2_0 = null;

        AntlrDatatypeRuleToken lv_narrative_4_0 = null;

        EObject lv_steps_5_0 = null;


         enterRule(); 
            
        try {
            // InternalCucumber.g:299:28: ( ( ( (lv_tags_0_0= ruleTag ) )* otherlv_1= 'Scenario:' ( (lv_title_2_0= ruleTitle ) )? (this_EOL_3= RULE_EOL )+ ( (lv_narrative_4_0= ruleNarrative ) )? ( (lv_steps_5_0= ruleStep ) )+ ) )
            // InternalCucumber.g:300:1: ( ( (lv_tags_0_0= ruleTag ) )* otherlv_1= 'Scenario:' ( (lv_title_2_0= ruleTitle ) )? (this_EOL_3= RULE_EOL )+ ( (lv_narrative_4_0= ruleNarrative ) )? ( (lv_steps_5_0= ruleStep ) )+ )
            {
            // InternalCucumber.g:300:1: ( ( (lv_tags_0_0= ruleTag ) )* otherlv_1= 'Scenario:' ( (lv_title_2_0= ruleTitle ) )? (this_EOL_3= RULE_EOL )+ ( (lv_narrative_4_0= ruleNarrative ) )? ( (lv_steps_5_0= ruleStep ) )+ )
            // InternalCucumber.g:300:2: ( (lv_tags_0_0= ruleTag ) )* otherlv_1= 'Scenario:' ( (lv_title_2_0= ruleTitle ) )? (this_EOL_3= RULE_EOL )+ ( (lv_narrative_4_0= ruleNarrative ) )? ( (lv_steps_5_0= ruleStep ) )+
            {
            // InternalCucumber.g:300:2: ( (lv_tags_0_0= ruleTag ) )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==RULE_TAGNAME) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // InternalCucumber.g:301:1: (lv_tags_0_0= ruleTag )
            	    {
            	    // InternalCucumber.g:301:1: (lv_tags_0_0= ruleTag )
            	    // InternalCucumber.g:302:3: lv_tags_0_0= ruleTag
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getScenarioAccess().getTagsTagParserRuleCall_0_0()); 
            	    	    
            	    pushFollow(FOLLOW_13);
            	    lv_tags_0_0=ruleTag();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getScenarioRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"tags",
            	            		lv_tags_0_0, 
            	            		"org.becausecucumber.eclipse.plugin.Cucumber.Tag");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);

            otherlv_1=(Token)match(input,18,FOLLOW_9); 

                	newLeafNode(otherlv_1, grammarAccess.getScenarioAccess().getScenarioKeyword_1());
                
            // InternalCucumber.g:322:1: ( (lv_title_2_0= ruleTitle ) )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( ((LA12_0>=RULE_WORD && LA12_0<=RULE_PLACEHOLDER)) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // InternalCucumber.g:323:1: (lv_title_2_0= ruleTitle )
                    {
                    // InternalCucumber.g:323:1: (lv_title_2_0= ruleTitle )
                    // InternalCucumber.g:324:3: lv_title_2_0= ruleTitle
                    {
                     
                    	        newCompositeNode(grammarAccess.getScenarioAccess().getTitleTitleParserRuleCall_2_0()); 
                    	    
                    pushFollow(FOLLOW_5);
                    lv_title_2_0=ruleTitle();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getScenarioRule());
                    	        }
                           		set(
                           			current, 
                           			"title",
                            		lv_title_2_0, 
                            		"org.becausecucumber.eclipse.plugin.Cucumber.Title");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }
                    break;

            }

            // InternalCucumber.g:340:3: (this_EOL_3= RULE_EOL )+
            int cnt13=0;
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==RULE_EOL) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // InternalCucumber.g:340:4: this_EOL_3= RULE_EOL
            	    {
            	    this_EOL_3=(Token)match(input,RULE_EOL,FOLLOW_10); 
            	     
            	        newLeafNode(this_EOL_3, grammarAccess.getScenarioAccess().getEOLTerminalRuleCall_3()); 
            	        

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

            // InternalCucumber.g:344:3: ( (lv_narrative_4_0= ruleNarrative ) )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( ((LA14_0>=RULE_WORD && LA14_0<=RULE_PLACEHOLDER)) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // InternalCucumber.g:345:1: (lv_narrative_4_0= ruleNarrative )
                    {
                    // InternalCucumber.g:345:1: (lv_narrative_4_0= ruleNarrative )
                    // InternalCucumber.g:346:3: lv_narrative_4_0= ruleNarrative
                    {
                     
                    	        newCompositeNode(grammarAccess.getScenarioAccess().getNarrativeNarrativeParserRuleCall_4_0()); 
                    	    
                    pushFollow(FOLLOW_11);
                    lv_narrative_4_0=ruleNarrative();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getScenarioRule());
                    	        }
                           		set(
                           			current, 
                           			"narrative",
                            		lv_narrative_4_0, 
                            		"org.becausecucumber.eclipse.plugin.Cucumber.Narrative");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }
                    break;

            }

            // InternalCucumber.g:362:3: ( (lv_steps_5_0= ruleStep ) )+
            int cnt15=0;
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( (LA15_0==RULE_STEP_KEYWORD) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // InternalCucumber.g:363:1: (lv_steps_5_0= ruleStep )
            	    {
            	    // InternalCucumber.g:363:1: (lv_steps_5_0= ruleStep )
            	    // InternalCucumber.g:364:3: lv_steps_5_0= ruleStep
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getScenarioAccess().getStepsStepParserRuleCall_5_0()); 
            	    	    
            	    pushFollow(FOLLOW_12);
            	    lv_steps_5_0=ruleStep();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getScenarioRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"steps",
            	            		lv_steps_5_0, 
            	            		"org.becausecucumber.eclipse.plugin.Cucumber.Step");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt15 >= 1 ) break loop15;
                        EarlyExitException eee =
                            new EarlyExitException(15, input);
                        throw eee;
                }
                cnt15++;
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
    // $ANTLR end "ruleScenario"


    // $ANTLR start "entryRuleScenarioOutline"
    // InternalCucumber.g:388:1: entryRuleScenarioOutline returns [EObject current=null] : iv_ruleScenarioOutline= ruleScenarioOutline EOF ;
    public final EObject entryRuleScenarioOutline() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleScenarioOutline = null;


        try {
            // InternalCucumber.g:389:2: (iv_ruleScenarioOutline= ruleScenarioOutline EOF )
            // InternalCucumber.g:390:2: iv_ruleScenarioOutline= ruleScenarioOutline EOF
            {
             newCompositeNode(grammarAccess.getScenarioOutlineRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleScenarioOutline=ruleScenarioOutline();

            state._fsp--;

             current =iv_ruleScenarioOutline; 
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
    // $ANTLR end "entryRuleScenarioOutline"


    // $ANTLR start "ruleScenarioOutline"
    // InternalCucumber.g:397:1: ruleScenarioOutline returns [EObject current=null] : ( ( (lv_tags_0_0= ruleTag ) )* otherlv_1= 'Scenario Outline:' ( (lv_title_2_0= ruleTitle ) )? (this_EOL_3= RULE_EOL )+ ( (lv_narrative_4_0= ruleNarrative ) )? ( (lv_steps_5_0= ruleStep ) )+ ( (lv_examples_6_0= ruleExamples ) ) ) ;
    public final EObject ruleScenarioOutline() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token this_EOL_3=null;
        EObject lv_tags_0_0 = null;

        AntlrDatatypeRuleToken lv_title_2_0 = null;

        AntlrDatatypeRuleToken lv_narrative_4_0 = null;

        EObject lv_steps_5_0 = null;

        EObject lv_examples_6_0 = null;


         enterRule(); 
            
        try {
            // InternalCucumber.g:400:28: ( ( ( (lv_tags_0_0= ruleTag ) )* otherlv_1= 'Scenario Outline:' ( (lv_title_2_0= ruleTitle ) )? (this_EOL_3= RULE_EOL )+ ( (lv_narrative_4_0= ruleNarrative ) )? ( (lv_steps_5_0= ruleStep ) )+ ( (lv_examples_6_0= ruleExamples ) ) ) )
            // InternalCucumber.g:401:1: ( ( (lv_tags_0_0= ruleTag ) )* otherlv_1= 'Scenario Outline:' ( (lv_title_2_0= ruleTitle ) )? (this_EOL_3= RULE_EOL )+ ( (lv_narrative_4_0= ruleNarrative ) )? ( (lv_steps_5_0= ruleStep ) )+ ( (lv_examples_6_0= ruleExamples ) ) )
            {
            // InternalCucumber.g:401:1: ( ( (lv_tags_0_0= ruleTag ) )* otherlv_1= 'Scenario Outline:' ( (lv_title_2_0= ruleTitle ) )? (this_EOL_3= RULE_EOL )+ ( (lv_narrative_4_0= ruleNarrative ) )? ( (lv_steps_5_0= ruleStep ) )+ ( (lv_examples_6_0= ruleExamples ) ) )
            // InternalCucumber.g:401:2: ( (lv_tags_0_0= ruleTag ) )* otherlv_1= 'Scenario Outline:' ( (lv_title_2_0= ruleTitle ) )? (this_EOL_3= RULE_EOL )+ ( (lv_narrative_4_0= ruleNarrative ) )? ( (lv_steps_5_0= ruleStep ) )+ ( (lv_examples_6_0= ruleExamples ) )
            {
            // InternalCucumber.g:401:2: ( (lv_tags_0_0= ruleTag ) )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==RULE_TAGNAME) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // InternalCucumber.g:402:1: (lv_tags_0_0= ruleTag )
            	    {
            	    // InternalCucumber.g:402:1: (lv_tags_0_0= ruleTag )
            	    // InternalCucumber.g:403:3: lv_tags_0_0= ruleTag
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getScenarioOutlineAccess().getTagsTagParserRuleCall_0_0()); 
            	    	    
            	    pushFollow(FOLLOW_14);
            	    lv_tags_0_0=ruleTag();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getScenarioOutlineRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"tags",
            	            		lv_tags_0_0, 
            	            		"org.becausecucumber.eclipse.plugin.Cucumber.Tag");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    break loop16;
                }
            } while (true);

            otherlv_1=(Token)match(input,19,FOLLOW_9); 

                	newLeafNode(otherlv_1, grammarAccess.getScenarioOutlineAccess().getScenarioOutlineKeyword_1());
                
            // InternalCucumber.g:423:1: ( (lv_title_2_0= ruleTitle ) )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( ((LA17_0>=RULE_WORD && LA17_0<=RULE_PLACEHOLDER)) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // InternalCucumber.g:424:1: (lv_title_2_0= ruleTitle )
                    {
                    // InternalCucumber.g:424:1: (lv_title_2_0= ruleTitle )
                    // InternalCucumber.g:425:3: lv_title_2_0= ruleTitle
                    {
                     
                    	        newCompositeNode(grammarAccess.getScenarioOutlineAccess().getTitleTitleParserRuleCall_2_0()); 
                    	    
                    pushFollow(FOLLOW_5);
                    lv_title_2_0=ruleTitle();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getScenarioOutlineRule());
                    	        }
                           		set(
                           			current, 
                           			"title",
                            		lv_title_2_0, 
                            		"org.becausecucumber.eclipse.plugin.Cucumber.Title");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }
                    break;

            }

            // InternalCucumber.g:441:3: (this_EOL_3= RULE_EOL )+
            int cnt18=0;
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( (LA18_0==RULE_EOL) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // InternalCucumber.g:441:4: this_EOL_3= RULE_EOL
            	    {
            	    this_EOL_3=(Token)match(input,RULE_EOL,FOLLOW_10); 
            	     
            	        newLeafNode(this_EOL_3, grammarAccess.getScenarioOutlineAccess().getEOLTerminalRuleCall_3()); 
            	        

            	    }
            	    break;

            	default :
            	    if ( cnt18 >= 1 ) break loop18;
                        EarlyExitException eee =
                            new EarlyExitException(18, input);
                        throw eee;
                }
                cnt18++;
            } while (true);

            // InternalCucumber.g:445:3: ( (lv_narrative_4_0= ruleNarrative ) )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( ((LA19_0>=RULE_WORD && LA19_0<=RULE_PLACEHOLDER)) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // InternalCucumber.g:446:1: (lv_narrative_4_0= ruleNarrative )
                    {
                    // InternalCucumber.g:446:1: (lv_narrative_4_0= ruleNarrative )
                    // InternalCucumber.g:447:3: lv_narrative_4_0= ruleNarrative
                    {
                     
                    	        newCompositeNode(grammarAccess.getScenarioOutlineAccess().getNarrativeNarrativeParserRuleCall_4_0()); 
                    	    
                    pushFollow(FOLLOW_11);
                    lv_narrative_4_0=ruleNarrative();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getScenarioOutlineRule());
                    	        }
                           		set(
                           			current, 
                           			"narrative",
                            		lv_narrative_4_0, 
                            		"org.becausecucumber.eclipse.plugin.Cucumber.Narrative");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }
                    break;

            }

            // InternalCucumber.g:463:3: ( (lv_steps_5_0= ruleStep ) )+
            int cnt20=0;
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==RULE_STEP_KEYWORD) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // InternalCucumber.g:464:1: (lv_steps_5_0= ruleStep )
            	    {
            	    // InternalCucumber.g:464:1: (lv_steps_5_0= ruleStep )
            	    // InternalCucumber.g:465:3: lv_steps_5_0= ruleStep
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getScenarioOutlineAccess().getStepsStepParserRuleCall_5_0()); 
            	    	    
            	    pushFollow(FOLLOW_15);
            	    lv_steps_5_0=ruleStep();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getScenarioOutlineRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"steps",
            	            		lv_steps_5_0, 
            	            		"org.becausecucumber.eclipse.plugin.Cucumber.Step");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt20 >= 1 ) break loop20;
                        EarlyExitException eee =
                            new EarlyExitException(20, input);
                        throw eee;
                }
                cnt20++;
            } while (true);

            // InternalCucumber.g:481:3: ( (lv_examples_6_0= ruleExamples ) )
            // InternalCucumber.g:482:1: (lv_examples_6_0= ruleExamples )
            {
            // InternalCucumber.g:482:1: (lv_examples_6_0= ruleExamples )
            // InternalCucumber.g:483:3: lv_examples_6_0= ruleExamples
            {
             
            	        newCompositeNode(grammarAccess.getScenarioOutlineAccess().getExamplesExamplesParserRuleCall_6_0()); 
            	    
            pushFollow(FOLLOW_2);
            lv_examples_6_0=ruleExamples();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getScenarioOutlineRule());
            	        }
                   		set(
                   			current, 
                   			"examples",
                    		lv_examples_6_0, 
                    		"org.becausecucumber.eclipse.plugin.Cucumber.Examples");
            	        afterParserOrEnumRuleCall();
            	    

            }


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
    // $ANTLR end "ruleScenarioOutline"


    // $ANTLR start "entryRuleStep"
    // InternalCucumber.g:507:1: entryRuleStep returns [EObject current=null] : iv_ruleStep= ruleStep EOF ;
    public final EObject entryRuleStep() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleStep = null;


        try {
            // InternalCucumber.g:508:2: (iv_ruleStep= ruleStep EOF )
            // InternalCucumber.g:509:2: iv_ruleStep= ruleStep EOF
            {
             newCompositeNode(grammarAccess.getStepRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleStep=ruleStep();

            state._fsp--;

             current =iv_ruleStep; 
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
    // $ANTLR end "entryRuleStep"


    // $ANTLR start "ruleStep"
    // InternalCucumber.g:516:1: ruleStep returns [EObject current=null] : ( ( (lv_keyword_0_0= RULE_STEP_KEYWORD ) ) ( (lv_description_1_0= ruleStepDescription ) ) (this_EOL_2= RULE_EOL )* ( (lv_tables_3_0= ruleTable ) )* ( (lv_code_4_0= ruleDocString ) )? ( (lv_tables_5_0= ruleTable ) )* ) ;
    public final EObject ruleStep() throws RecognitionException {
        EObject current = null;

        Token lv_keyword_0_0=null;
        Token this_EOL_2=null;
        AntlrDatatypeRuleToken lv_description_1_0 = null;

        EObject lv_tables_3_0 = null;

        EObject lv_code_4_0 = null;

        EObject lv_tables_5_0 = null;


         enterRule(); 
            
        try {
            // InternalCucumber.g:519:28: ( ( ( (lv_keyword_0_0= RULE_STEP_KEYWORD ) ) ( (lv_description_1_0= ruleStepDescription ) ) (this_EOL_2= RULE_EOL )* ( (lv_tables_3_0= ruleTable ) )* ( (lv_code_4_0= ruleDocString ) )? ( (lv_tables_5_0= ruleTable ) )* ) )
            // InternalCucumber.g:520:1: ( ( (lv_keyword_0_0= RULE_STEP_KEYWORD ) ) ( (lv_description_1_0= ruleStepDescription ) ) (this_EOL_2= RULE_EOL )* ( (lv_tables_3_0= ruleTable ) )* ( (lv_code_4_0= ruleDocString ) )? ( (lv_tables_5_0= ruleTable ) )* )
            {
            // InternalCucumber.g:520:1: ( ( (lv_keyword_0_0= RULE_STEP_KEYWORD ) ) ( (lv_description_1_0= ruleStepDescription ) ) (this_EOL_2= RULE_EOL )* ( (lv_tables_3_0= ruleTable ) )* ( (lv_code_4_0= ruleDocString ) )? ( (lv_tables_5_0= ruleTable ) )* )
            // InternalCucumber.g:520:2: ( (lv_keyword_0_0= RULE_STEP_KEYWORD ) ) ( (lv_description_1_0= ruleStepDescription ) ) (this_EOL_2= RULE_EOL )* ( (lv_tables_3_0= ruleTable ) )* ( (lv_code_4_0= ruleDocString ) )? ( (lv_tables_5_0= ruleTable ) )*
            {
            // InternalCucumber.g:520:2: ( (lv_keyword_0_0= RULE_STEP_KEYWORD ) )
            // InternalCucumber.g:521:1: (lv_keyword_0_0= RULE_STEP_KEYWORD )
            {
            // InternalCucumber.g:521:1: (lv_keyword_0_0= RULE_STEP_KEYWORD )
            // InternalCucumber.g:522:3: lv_keyword_0_0= RULE_STEP_KEYWORD
            {
            lv_keyword_0_0=(Token)match(input,RULE_STEP_KEYWORD,FOLLOW_16); 

            			newLeafNode(lv_keyword_0_0, grammarAccess.getStepAccess().getKeywordSTEP_KEYWORDTerminalRuleCall_0_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getStepRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"keyword",
                    		lv_keyword_0_0, 
                    		"org.becausecucumber.eclipse.plugin.Cucumber.STEP_KEYWORD");
            	    

            }


            }

            // InternalCucumber.g:538:2: ( (lv_description_1_0= ruleStepDescription ) )
            // InternalCucumber.g:539:1: (lv_description_1_0= ruleStepDescription )
            {
            // InternalCucumber.g:539:1: (lv_description_1_0= ruleStepDescription )
            // InternalCucumber.g:540:3: lv_description_1_0= ruleStepDescription
            {
             
            	        newCompositeNode(grammarAccess.getStepAccess().getDescriptionStepDescriptionParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_17);
            lv_description_1_0=ruleStepDescription();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getStepRule());
            	        }
                   		set(
                   			current, 
                   			"description",
                    		lv_description_1_0, 
                    		"org.becausecucumber.eclipse.plugin.Cucumber.StepDescription");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // InternalCucumber.g:556:2: (this_EOL_2= RULE_EOL )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( (LA21_0==RULE_EOL) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // InternalCucumber.g:556:3: this_EOL_2= RULE_EOL
            	    {
            	    this_EOL_2=(Token)match(input,RULE_EOL,FOLLOW_17); 
            	     
            	        newLeafNode(this_EOL_2, grammarAccess.getStepAccess().getEOLTerminalRuleCall_2()); 
            	        

            	    }
            	    break;

            	default :
            	    break loop21;
                }
            } while (true);

            // InternalCucumber.g:560:3: ( (lv_tables_3_0= ruleTable ) )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( (LA22_0==RULE_TABLE_ROW) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // InternalCucumber.g:561:1: (lv_tables_3_0= ruleTable )
            	    {
            	    // InternalCucumber.g:561:1: (lv_tables_3_0= ruleTable )
            	    // InternalCucumber.g:562:3: lv_tables_3_0= ruleTable
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getStepAccess().getTablesTableParserRuleCall_3_0()); 
            	    	    
            	    pushFollow(FOLLOW_18);
            	    lv_tables_3_0=ruleTable();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getStepRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"tables",
            	            		lv_tables_3_0, 
            	            		"org.becausecucumber.eclipse.plugin.Cucumber.Table");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    break loop22;
                }
            } while (true);

            // InternalCucumber.g:578:3: ( (lv_code_4_0= ruleDocString ) )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==RULE_DOC_STRING) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // InternalCucumber.g:579:1: (lv_code_4_0= ruleDocString )
                    {
                    // InternalCucumber.g:579:1: (lv_code_4_0= ruleDocString )
                    // InternalCucumber.g:580:3: lv_code_4_0= ruleDocString
                    {
                     
                    	        newCompositeNode(grammarAccess.getStepAccess().getCodeDocStringParserRuleCall_4_0()); 
                    	    
                    pushFollow(FOLLOW_19);
                    lv_code_4_0=ruleDocString();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getStepRule());
                    	        }
                           		set(
                           			current, 
                           			"code",
                            		lv_code_4_0, 
                            		"org.becausecucumber.eclipse.plugin.Cucumber.DocString");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }
                    break;

            }

            // InternalCucumber.g:596:3: ( (lv_tables_5_0= ruleTable ) )*
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( (LA24_0==RULE_TABLE_ROW) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // InternalCucumber.g:597:1: (lv_tables_5_0= ruleTable )
            	    {
            	    // InternalCucumber.g:597:1: (lv_tables_5_0= ruleTable )
            	    // InternalCucumber.g:598:3: lv_tables_5_0= ruleTable
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getStepAccess().getTablesTableParserRuleCall_5_0()); 
            	    	    
            	    pushFollow(FOLLOW_19);
            	    lv_tables_5_0=ruleTable();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getStepRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"tables",
            	            		lv_tables_5_0, 
            	            		"org.becausecucumber.eclipse.plugin.Cucumber.Table");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    break loop24;
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
    // $ANTLR end "ruleStep"


    // $ANTLR start "entryRuleExamples"
    // InternalCucumber.g:622:1: entryRuleExamples returns [EObject current=null] : iv_ruleExamples= ruleExamples EOF ;
    public final EObject entryRuleExamples() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExamples = null;


        try {
            // InternalCucumber.g:623:2: (iv_ruleExamples= ruleExamples EOF )
            // InternalCucumber.g:624:2: iv_ruleExamples= ruleExamples EOF
            {
             newCompositeNode(grammarAccess.getExamplesRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleExamples=ruleExamples();

            state._fsp--;

             current =iv_ruleExamples; 
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
    // $ANTLR end "entryRuleExamples"


    // $ANTLR start "ruleExamples"
    // InternalCucumber.g:631:1: ruleExamples returns [EObject current=null] : (otherlv_0= 'Examples:' ( (lv_title_1_0= ruleTitle ) )? (this_EOL_2= RULE_EOL )+ ( (lv_narrative_3_0= ruleNarrative ) )? ( (lv_table_4_0= ruleTable ) ) ) ;
    public final EObject ruleExamples() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token this_EOL_2=null;
        AntlrDatatypeRuleToken lv_title_1_0 = null;

        AntlrDatatypeRuleToken lv_narrative_3_0 = null;

        EObject lv_table_4_0 = null;


         enterRule(); 
            
        try {
            // InternalCucumber.g:634:28: ( (otherlv_0= 'Examples:' ( (lv_title_1_0= ruleTitle ) )? (this_EOL_2= RULE_EOL )+ ( (lv_narrative_3_0= ruleNarrative ) )? ( (lv_table_4_0= ruleTable ) ) ) )
            // InternalCucumber.g:635:1: (otherlv_0= 'Examples:' ( (lv_title_1_0= ruleTitle ) )? (this_EOL_2= RULE_EOL )+ ( (lv_narrative_3_0= ruleNarrative ) )? ( (lv_table_4_0= ruleTable ) ) )
            {
            // InternalCucumber.g:635:1: (otherlv_0= 'Examples:' ( (lv_title_1_0= ruleTitle ) )? (this_EOL_2= RULE_EOL )+ ( (lv_narrative_3_0= ruleNarrative ) )? ( (lv_table_4_0= ruleTable ) ) )
            // InternalCucumber.g:635:3: otherlv_0= 'Examples:' ( (lv_title_1_0= ruleTitle ) )? (this_EOL_2= RULE_EOL )+ ( (lv_narrative_3_0= ruleNarrative ) )? ( (lv_table_4_0= ruleTable ) )
            {
            otherlv_0=(Token)match(input,20,FOLLOW_9); 

                	newLeafNode(otherlv_0, grammarAccess.getExamplesAccess().getExamplesKeyword_0());
                
            // InternalCucumber.g:639:1: ( (lv_title_1_0= ruleTitle ) )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( ((LA25_0>=RULE_WORD && LA25_0<=RULE_PLACEHOLDER)) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // InternalCucumber.g:640:1: (lv_title_1_0= ruleTitle )
                    {
                    // InternalCucumber.g:640:1: (lv_title_1_0= ruleTitle )
                    // InternalCucumber.g:641:3: lv_title_1_0= ruleTitle
                    {
                     
                    	        newCompositeNode(grammarAccess.getExamplesAccess().getTitleTitleParserRuleCall_1_0()); 
                    	    
                    pushFollow(FOLLOW_5);
                    lv_title_1_0=ruleTitle();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getExamplesRule());
                    	        }
                           		set(
                           			current, 
                           			"title",
                            		lv_title_1_0, 
                            		"org.becausecucumber.eclipse.plugin.Cucumber.Title");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }
                    break;

            }

            // InternalCucumber.g:657:3: (this_EOL_2= RULE_EOL )+
            int cnt26=0;
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( (LA26_0==RULE_EOL) ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // InternalCucumber.g:657:4: this_EOL_2= RULE_EOL
            	    {
            	    this_EOL_2=(Token)match(input,RULE_EOL,FOLLOW_20); 
            	     
            	        newLeafNode(this_EOL_2, grammarAccess.getExamplesAccess().getEOLTerminalRuleCall_2()); 
            	        

            	    }
            	    break;

            	default :
            	    if ( cnt26 >= 1 ) break loop26;
                        EarlyExitException eee =
                            new EarlyExitException(26, input);
                        throw eee;
                }
                cnt26++;
            } while (true);

            // InternalCucumber.g:661:3: ( (lv_narrative_3_0= ruleNarrative ) )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( ((LA27_0>=RULE_WORD && LA27_0<=RULE_PLACEHOLDER)) ) {
                alt27=1;
            }
            switch (alt27) {
                case 1 :
                    // InternalCucumber.g:662:1: (lv_narrative_3_0= ruleNarrative )
                    {
                    // InternalCucumber.g:662:1: (lv_narrative_3_0= ruleNarrative )
                    // InternalCucumber.g:663:3: lv_narrative_3_0= ruleNarrative
                    {
                     
                    	        newCompositeNode(grammarAccess.getExamplesAccess().getNarrativeNarrativeParserRuleCall_3_0()); 
                    	    
                    pushFollow(FOLLOW_21);
                    lv_narrative_3_0=ruleNarrative();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getExamplesRule());
                    	        }
                           		set(
                           			current, 
                           			"narrative",
                            		lv_narrative_3_0, 
                            		"org.becausecucumber.eclipse.plugin.Cucumber.Narrative");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }
                    break;

            }

            // InternalCucumber.g:679:3: ( (lv_table_4_0= ruleTable ) )
            // InternalCucumber.g:680:1: (lv_table_4_0= ruleTable )
            {
            // InternalCucumber.g:680:1: (lv_table_4_0= ruleTable )
            // InternalCucumber.g:681:3: lv_table_4_0= ruleTable
            {
             
            	        newCompositeNode(grammarAccess.getExamplesAccess().getTableTableParserRuleCall_4_0()); 
            	    
            pushFollow(FOLLOW_2);
            lv_table_4_0=ruleTable();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getExamplesRule());
            	        }
                   		set(
                   			current, 
                   			"table",
                    		lv_table_4_0, 
                    		"org.becausecucumber.eclipse.plugin.Cucumber.Table");
            	        afterParserOrEnumRuleCall();
            	    

            }


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
    // $ANTLR end "ruleExamples"


    // $ANTLR start "entryRuleTable"
    // InternalCucumber.g:705:1: entryRuleTable returns [EObject current=null] : iv_ruleTable= ruleTable EOF ;
    public final EObject entryRuleTable() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTable = null;


        try {
            // InternalCucumber.g:706:2: (iv_ruleTable= ruleTable EOF )
            // InternalCucumber.g:707:2: iv_ruleTable= ruleTable EOF
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
    // InternalCucumber.g:714:1: ruleTable returns [EObject current=null] : ( ( (lv_rows_0_0= RULE_TABLE_ROW ) )+ (this_EOL_1= RULE_EOL )* ) ;
    public final EObject ruleTable() throws RecognitionException {
        EObject current = null;

        Token lv_rows_0_0=null;
        Token this_EOL_1=null;

         enterRule(); 
            
        try {
            // InternalCucumber.g:717:28: ( ( ( (lv_rows_0_0= RULE_TABLE_ROW ) )+ (this_EOL_1= RULE_EOL )* ) )
            // InternalCucumber.g:718:1: ( ( (lv_rows_0_0= RULE_TABLE_ROW ) )+ (this_EOL_1= RULE_EOL )* )
            {
            // InternalCucumber.g:718:1: ( ( (lv_rows_0_0= RULE_TABLE_ROW ) )+ (this_EOL_1= RULE_EOL )* )
            // InternalCucumber.g:718:2: ( (lv_rows_0_0= RULE_TABLE_ROW ) )+ (this_EOL_1= RULE_EOL )*
            {
            // InternalCucumber.g:718:2: ( (lv_rows_0_0= RULE_TABLE_ROW ) )+
            int cnt28=0;
            loop28:
            do {
                int alt28=2;
                int LA28_0 = input.LA(1);

                if ( (LA28_0==RULE_TABLE_ROW) ) {
                    alt28=1;
                }


                switch (alt28) {
            	case 1 :
            	    // InternalCucumber.g:719:1: (lv_rows_0_0= RULE_TABLE_ROW )
            	    {
            	    // InternalCucumber.g:719:1: (lv_rows_0_0= RULE_TABLE_ROW )
            	    // InternalCucumber.g:720:3: lv_rows_0_0= RULE_TABLE_ROW
            	    {
            	    lv_rows_0_0=(Token)match(input,RULE_TABLE_ROW,FOLLOW_22); 

            	    			newLeafNode(lv_rows_0_0, grammarAccess.getTableAccess().getRowsTABLE_ROWTerminalRuleCall_0_0()); 
            	    		

            	    	        if (current==null) {
            	    	            current = createModelElement(grammarAccess.getTableRule());
            	    	        }
            	           		addWithLastConsumed(
            	           			current, 
            	           			"rows",
            	            		lv_rows_0_0, 
            	            		"org.becausecucumber.eclipse.plugin.Cucumber.TABLE_ROW");
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt28 >= 1 ) break loop28;
                        EarlyExitException eee =
                            new EarlyExitException(28, input);
                        throw eee;
                }
                cnt28++;
            } while (true);

            // InternalCucumber.g:736:3: (this_EOL_1= RULE_EOL )*
            loop29:
            do {
                int alt29=2;
                int LA29_0 = input.LA(1);

                if ( (LA29_0==RULE_EOL) ) {
                    alt29=1;
                }


                switch (alt29) {
            	case 1 :
            	    // InternalCucumber.g:736:4: this_EOL_1= RULE_EOL
            	    {
            	    this_EOL_1=(Token)match(input,RULE_EOL,FOLLOW_23); 
            	     
            	        newLeafNode(this_EOL_1, grammarAccess.getTableAccess().getEOLTerminalRuleCall_1()); 
            	        

            	    }
            	    break;

            	default :
            	    break loop29;
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
    // $ANTLR end "ruleTable"


    // $ANTLR start "entryRuleDocString"
    // InternalCucumber.g:748:1: entryRuleDocString returns [EObject current=null] : iv_ruleDocString= ruleDocString EOF ;
    public final EObject entryRuleDocString() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDocString = null;


        try {
            // InternalCucumber.g:749:2: (iv_ruleDocString= ruleDocString EOF )
            // InternalCucumber.g:750:2: iv_ruleDocString= ruleDocString EOF
            {
             newCompositeNode(grammarAccess.getDocStringRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleDocString=ruleDocString();

            state._fsp--;

             current =iv_ruleDocString; 
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
    // $ANTLR end "entryRuleDocString"


    // $ANTLR start "ruleDocString"
    // InternalCucumber.g:757:1: ruleDocString returns [EObject current=null] : ( ( (lv_content_0_0= RULE_DOC_STRING ) ) (this_EOL_1= RULE_EOL )* ) ;
    public final EObject ruleDocString() throws RecognitionException {
        EObject current = null;

        Token lv_content_0_0=null;
        Token this_EOL_1=null;

         enterRule(); 
            
        try {
            // InternalCucumber.g:760:28: ( ( ( (lv_content_0_0= RULE_DOC_STRING ) ) (this_EOL_1= RULE_EOL )* ) )
            // InternalCucumber.g:761:1: ( ( (lv_content_0_0= RULE_DOC_STRING ) ) (this_EOL_1= RULE_EOL )* )
            {
            // InternalCucumber.g:761:1: ( ( (lv_content_0_0= RULE_DOC_STRING ) ) (this_EOL_1= RULE_EOL )* )
            // InternalCucumber.g:761:2: ( (lv_content_0_0= RULE_DOC_STRING ) ) (this_EOL_1= RULE_EOL )*
            {
            // InternalCucumber.g:761:2: ( (lv_content_0_0= RULE_DOC_STRING ) )
            // InternalCucumber.g:762:1: (lv_content_0_0= RULE_DOC_STRING )
            {
            // InternalCucumber.g:762:1: (lv_content_0_0= RULE_DOC_STRING )
            // InternalCucumber.g:763:3: lv_content_0_0= RULE_DOC_STRING
            {
            lv_content_0_0=(Token)match(input,RULE_DOC_STRING,FOLLOW_23); 

            			newLeafNode(lv_content_0_0, grammarAccess.getDocStringAccess().getContentDOC_STRINGTerminalRuleCall_0_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getDocStringRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"content",
                    		lv_content_0_0, 
                    		"org.becausecucumber.eclipse.plugin.Cucumber.DOC_STRING");
            	    

            }


            }

            // InternalCucumber.g:779:2: (this_EOL_1= RULE_EOL )*
            loop30:
            do {
                int alt30=2;
                int LA30_0 = input.LA(1);

                if ( (LA30_0==RULE_EOL) ) {
                    alt30=1;
                }


                switch (alt30) {
            	case 1 :
            	    // InternalCucumber.g:779:3: this_EOL_1= RULE_EOL
            	    {
            	    this_EOL_1=(Token)match(input,RULE_EOL,FOLLOW_23); 
            	     
            	        newLeafNode(this_EOL_1, grammarAccess.getDocStringAccess().getEOLTerminalRuleCall_1()); 
            	        

            	    }
            	    break;

            	default :
            	    break loop30;
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
    // $ANTLR end "ruleDocString"


    // $ANTLR start "entryRuleTitle"
    // InternalCucumber.g:791:1: entryRuleTitle returns [String current=null] : iv_ruleTitle= ruleTitle EOF ;
    public final String entryRuleTitle() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleTitle = null;


        try {
            // InternalCucumber.g:792:2: (iv_ruleTitle= ruleTitle EOF )
            // InternalCucumber.g:793:2: iv_ruleTitle= ruleTitle EOF
            {
             newCompositeNode(grammarAccess.getTitleRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleTitle=ruleTitle();

            state._fsp--;

             current =iv_ruleTitle.getText(); 
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
    // $ANTLR end "entryRuleTitle"


    // $ANTLR start "ruleTitle"
    // InternalCucumber.g:800:1: ruleTitle returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( (this_WORD_0= RULE_WORD | this_INT_1= RULE_INT | this_STRING_2= RULE_STRING | this_PLACEHOLDER_3= RULE_PLACEHOLDER ) (this_WORD_4= RULE_WORD | this_INT_5= RULE_INT | this_STRING_6= RULE_STRING | this_PLACEHOLDER_7= RULE_PLACEHOLDER | this_STEP_KEYWORD_8= RULE_STEP_KEYWORD | this_TAGNAME_9= RULE_TAGNAME )* ) ;
    public final AntlrDatatypeRuleToken ruleTitle() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_WORD_0=null;
        Token this_INT_1=null;
        Token this_STRING_2=null;
        Token this_PLACEHOLDER_3=null;
        Token this_WORD_4=null;
        Token this_INT_5=null;
        Token this_STRING_6=null;
        Token this_PLACEHOLDER_7=null;
        Token this_STEP_KEYWORD_8=null;
        Token this_TAGNAME_9=null;

         enterRule(); 
            
        try {
            // InternalCucumber.g:803:28: ( ( (this_WORD_0= RULE_WORD | this_INT_1= RULE_INT | this_STRING_2= RULE_STRING | this_PLACEHOLDER_3= RULE_PLACEHOLDER ) (this_WORD_4= RULE_WORD | this_INT_5= RULE_INT | this_STRING_6= RULE_STRING | this_PLACEHOLDER_7= RULE_PLACEHOLDER | this_STEP_KEYWORD_8= RULE_STEP_KEYWORD | this_TAGNAME_9= RULE_TAGNAME )* ) )
            // InternalCucumber.g:804:1: ( (this_WORD_0= RULE_WORD | this_INT_1= RULE_INT | this_STRING_2= RULE_STRING | this_PLACEHOLDER_3= RULE_PLACEHOLDER ) (this_WORD_4= RULE_WORD | this_INT_5= RULE_INT | this_STRING_6= RULE_STRING | this_PLACEHOLDER_7= RULE_PLACEHOLDER | this_STEP_KEYWORD_8= RULE_STEP_KEYWORD | this_TAGNAME_9= RULE_TAGNAME )* )
            {
            // InternalCucumber.g:804:1: ( (this_WORD_0= RULE_WORD | this_INT_1= RULE_INT | this_STRING_2= RULE_STRING | this_PLACEHOLDER_3= RULE_PLACEHOLDER ) (this_WORD_4= RULE_WORD | this_INT_5= RULE_INT | this_STRING_6= RULE_STRING | this_PLACEHOLDER_7= RULE_PLACEHOLDER | this_STEP_KEYWORD_8= RULE_STEP_KEYWORD | this_TAGNAME_9= RULE_TAGNAME )* )
            // InternalCucumber.g:804:2: (this_WORD_0= RULE_WORD | this_INT_1= RULE_INT | this_STRING_2= RULE_STRING | this_PLACEHOLDER_3= RULE_PLACEHOLDER ) (this_WORD_4= RULE_WORD | this_INT_5= RULE_INT | this_STRING_6= RULE_STRING | this_PLACEHOLDER_7= RULE_PLACEHOLDER | this_STEP_KEYWORD_8= RULE_STEP_KEYWORD | this_TAGNAME_9= RULE_TAGNAME )*
            {
            // InternalCucumber.g:804:2: (this_WORD_0= RULE_WORD | this_INT_1= RULE_INT | this_STRING_2= RULE_STRING | this_PLACEHOLDER_3= RULE_PLACEHOLDER )
            int alt31=4;
            switch ( input.LA(1) ) {
            case RULE_WORD:
                {
                alt31=1;
                }
                break;
            case RULE_INT:
                {
                alt31=2;
                }
                break;
            case RULE_STRING:
                {
                alt31=3;
                }
                break;
            case RULE_PLACEHOLDER:
                {
                alt31=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 31, 0, input);

                throw nvae;
            }

            switch (alt31) {
                case 1 :
                    // InternalCucumber.g:804:7: this_WORD_0= RULE_WORD
                    {
                    this_WORD_0=(Token)match(input,RULE_WORD,FOLLOW_24); 

                    		current.merge(this_WORD_0);
                        
                     
                        newLeafNode(this_WORD_0, grammarAccess.getTitleAccess().getWORDTerminalRuleCall_0_0()); 
                        

                    }
                    break;
                case 2 :
                    // InternalCucumber.g:812:10: this_INT_1= RULE_INT
                    {
                    this_INT_1=(Token)match(input,RULE_INT,FOLLOW_24); 

                    		current.merge(this_INT_1);
                        
                     
                        newLeafNode(this_INT_1, grammarAccess.getTitleAccess().getINTTerminalRuleCall_0_1()); 
                        

                    }
                    break;
                case 3 :
                    // InternalCucumber.g:820:10: this_STRING_2= RULE_STRING
                    {
                    this_STRING_2=(Token)match(input,RULE_STRING,FOLLOW_24); 

                    		current.merge(this_STRING_2);
                        
                     
                        newLeafNode(this_STRING_2, grammarAccess.getTitleAccess().getSTRINGTerminalRuleCall_0_2()); 
                        

                    }
                    break;
                case 4 :
                    // InternalCucumber.g:828:10: this_PLACEHOLDER_3= RULE_PLACEHOLDER
                    {
                    this_PLACEHOLDER_3=(Token)match(input,RULE_PLACEHOLDER,FOLLOW_24); 

                    		current.merge(this_PLACEHOLDER_3);
                        
                     
                        newLeafNode(this_PLACEHOLDER_3, grammarAccess.getTitleAccess().getPLACEHOLDERTerminalRuleCall_0_3()); 
                        

                    }
                    break;

            }

            // InternalCucumber.g:835:2: (this_WORD_4= RULE_WORD | this_INT_5= RULE_INT | this_STRING_6= RULE_STRING | this_PLACEHOLDER_7= RULE_PLACEHOLDER | this_STEP_KEYWORD_8= RULE_STEP_KEYWORD | this_TAGNAME_9= RULE_TAGNAME )*
            loop32:
            do {
                int alt32=7;
                switch ( input.LA(1) ) {
                case RULE_WORD:
                    {
                    alt32=1;
                    }
                    break;
                case RULE_INT:
                    {
                    alt32=2;
                    }
                    break;
                case RULE_STRING:
                    {
                    alt32=3;
                    }
                    break;
                case RULE_PLACEHOLDER:
                    {
                    alt32=4;
                    }
                    break;
                case RULE_STEP_KEYWORD:
                    {
                    alt32=5;
                    }
                    break;
                case RULE_TAGNAME:
                    {
                    alt32=6;
                    }
                    break;

                }

                switch (alt32) {
            	case 1 :
            	    // InternalCucumber.g:835:7: this_WORD_4= RULE_WORD
            	    {
            	    this_WORD_4=(Token)match(input,RULE_WORD,FOLLOW_24); 

            	    		current.merge(this_WORD_4);
            	        
            	     
            	        newLeafNode(this_WORD_4, grammarAccess.getTitleAccess().getWORDTerminalRuleCall_1_0()); 
            	        

            	    }
            	    break;
            	case 2 :
            	    // InternalCucumber.g:843:10: this_INT_5= RULE_INT
            	    {
            	    this_INT_5=(Token)match(input,RULE_INT,FOLLOW_24); 

            	    		current.merge(this_INT_5);
            	        
            	     
            	        newLeafNode(this_INT_5, grammarAccess.getTitleAccess().getINTTerminalRuleCall_1_1()); 
            	        

            	    }
            	    break;
            	case 3 :
            	    // InternalCucumber.g:851:10: this_STRING_6= RULE_STRING
            	    {
            	    this_STRING_6=(Token)match(input,RULE_STRING,FOLLOW_24); 

            	    		current.merge(this_STRING_6);
            	        
            	     
            	        newLeafNode(this_STRING_6, grammarAccess.getTitleAccess().getSTRINGTerminalRuleCall_1_2()); 
            	        

            	    }
            	    break;
            	case 4 :
            	    // InternalCucumber.g:859:10: this_PLACEHOLDER_7= RULE_PLACEHOLDER
            	    {
            	    this_PLACEHOLDER_7=(Token)match(input,RULE_PLACEHOLDER,FOLLOW_24); 

            	    		current.merge(this_PLACEHOLDER_7);
            	        
            	     
            	        newLeafNode(this_PLACEHOLDER_7, grammarAccess.getTitleAccess().getPLACEHOLDERTerminalRuleCall_1_3()); 
            	        

            	    }
            	    break;
            	case 5 :
            	    // InternalCucumber.g:867:10: this_STEP_KEYWORD_8= RULE_STEP_KEYWORD
            	    {
            	    this_STEP_KEYWORD_8=(Token)match(input,RULE_STEP_KEYWORD,FOLLOW_24); 

            	    		current.merge(this_STEP_KEYWORD_8);
            	        
            	     
            	        newLeafNode(this_STEP_KEYWORD_8, grammarAccess.getTitleAccess().getSTEP_KEYWORDTerminalRuleCall_1_4()); 
            	        

            	    }
            	    break;
            	case 6 :
            	    // InternalCucumber.g:875:10: this_TAGNAME_9= RULE_TAGNAME
            	    {
            	    this_TAGNAME_9=(Token)match(input,RULE_TAGNAME,FOLLOW_24); 

            	    		current.merge(this_TAGNAME_9);
            	        
            	     
            	        newLeafNode(this_TAGNAME_9, grammarAccess.getTitleAccess().getTAGNAMETerminalRuleCall_1_5()); 
            	        

            	    }
            	    break;

            	default :
            	    break loop32;
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
    // $ANTLR end "ruleTitle"


    // $ANTLR start "entryRuleNarrative"
    // InternalCucumber.g:890:1: entryRuleNarrative returns [String current=null] : iv_ruleNarrative= ruleNarrative EOF ;
    public final String entryRuleNarrative() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleNarrative = null;


        try {
            // InternalCucumber.g:891:2: (iv_ruleNarrative= ruleNarrative EOF )
            // InternalCucumber.g:892:2: iv_ruleNarrative= ruleNarrative EOF
            {
             newCompositeNode(grammarAccess.getNarrativeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleNarrative=ruleNarrative();

            state._fsp--;

             current =iv_ruleNarrative.getText(); 
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
    // $ANTLR end "entryRuleNarrative"


    // $ANTLR start "ruleNarrative"
    // InternalCucumber.g:899:1: ruleNarrative returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( (this_WORD_0= RULE_WORD | this_INT_1= RULE_INT | this_STRING_2= RULE_STRING | this_PLACEHOLDER_3= RULE_PLACEHOLDER ) (this_WORD_4= RULE_WORD | this_INT_5= RULE_INT | this_STRING_6= RULE_STRING | this_PLACEHOLDER_7= RULE_PLACEHOLDER | this_STEP_KEYWORD_8= RULE_STEP_KEYWORD | this_TAGNAME_9= RULE_TAGNAME )* (this_EOL_10= RULE_EOL )+ )+ ;
    public final AntlrDatatypeRuleToken ruleNarrative() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_WORD_0=null;
        Token this_INT_1=null;
        Token this_STRING_2=null;
        Token this_PLACEHOLDER_3=null;
        Token this_WORD_4=null;
        Token this_INT_5=null;
        Token this_STRING_6=null;
        Token this_PLACEHOLDER_7=null;
        Token this_STEP_KEYWORD_8=null;
        Token this_TAGNAME_9=null;
        Token this_EOL_10=null;

         enterRule(); 
            
        try {
            // InternalCucumber.g:902:28: ( ( (this_WORD_0= RULE_WORD | this_INT_1= RULE_INT | this_STRING_2= RULE_STRING | this_PLACEHOLDER_3= RULE_PLACEHOLDER ) (this_WORD_4= RULE_WORD | this_INT_5= RULE_INT | this_STRING_6= RULE_STRING | this_PLACEHOLDER_7= RULE_PLACEHOLDER | this_STEP_KEYWORD_8= RULE_STEP_KEYWORD | this_TAGNAME_9= RULE_TAGNAME )* (this_EOL_10= RULE_EOL )+ )+ )
            // InternalCucumber.g:903:1: ( (this_WORD_0= RULE_WORD | this_INT_1= RULE_INT | this_STRING_2= RULE_STRING | this_PLACEHOLDER_3= RULE_PLACEHOLDER ) (this_WORD_4= RULE_WORD | this_INT_5= RULE_INT | this_STRING_6= RULE_STRING | this_PLACEHOLDER_7= RULE_PLACEHOLDER | this_STEP_KEYWORD_8= RULE_STEP_KEYWORD | this_TAGNAME_9= RULE_TAGNAME )* (this_EOL_10= RULE_EOL )+ )+
            {
            // InternalCucumber.g:903:1: ( (this_WORD_0= RULE_WORD | this_INT_1= RULE_INT | this_STRING_2= RULE_STRING | this_PLACEHOLDER_3= RULE_PLACEHOLDER ) (this_WORD_4= RULE_WORD | this_INT_5= RULE_INT | this_STRING_6= RULE_STRING | this_PLACEHOLDER_7= RULE_PLACEHOLDER | this_STEP_KEYWORD_8= RULE_STEP_KEYWORD | this_TAGNAME_9= RULE_TAGNAME )* (this_EOL_10= RULE_EOL )+ )+
            int cnt36=0;
            loop36:
            do {
                int alt36=2;
                int LA36_0 = input.LA(1);

                if ( ((LA36_0>=RULE_WORD && LA36_0<=RULE_PLACEHOLDER)) ) {
                    alt36=1;
                }


                switch (alt36) {
            	case 1 :
            	    // InternalCucumber.g:903:2: (this_WORD_0= RULE_WORD | this_INT_1= RULE_INT | this_STRING_2= RULE_STRING | this_PLACEHOLDER_3= RULE_PLACEHOLDER ) (this_WORD_4= RULE_WORD | this_INT_5= RULE_INT | this_STRING_6= RULE_STRING | this_PLACEHOLDER_7= RULE_PLACEHOLDER | this_STEP_KEYWORD_8= RULE_STEP_KEYWORD | this_TAGNAME_9= RULE_TAGNAME )* (this_EOL_10= RULE_EOL )+
            	    {
            	    // InternalCucumber.g:903:2: (this_WORD_0= RULE_WORD | this_INT_1= RULE_INT | this_STRING_2= RULE_STRING | this_PLACEHOLDER_3= RULE_PLACEHOLDER )
            	    int alt33=4;
            	    switch ( input.LA(1) ) {
            	    case RULE_WORD:
            	        {
            	        alt33=1;
            	        }
            	        break;
            	    case RULE_INT:
            	        {
            	        alt33=2;
            	        }
            	        break;
            	    case RULE_STRING:
            	        {
            	        alt33=3;
            	        }
            	        break;
            	    case RULE_PLACEHOLDER:
            	        {
            	        alt33=4;
            	        }
            	        break;
            	    default:
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 33, 0, input);

            	        throw nvae;
            	    }

            	    switch (alt33) {
            	        case 1 :
            	            // InternalCucumber.g:903:7: this_WORD_0= RULE_WORD
            	            {
            	            this_WORD_0=(Token)match(input,RULE_WORD,FOLLOW_25); 

            	            		current.merge(this_WORD_0);
            	                
            	             
            	                newLeafNode(this_WORD_0, grammarAccess.getNarrativeAccess().getWORDTerminalRuleCall_0_0()); 
            	                

            	            }
            	            break;
            	        case 2 :
            	            // InternalCucumber.g:911:10: this_INT_1= RULE_INT
            	            {
            	            this_INT_1=(Token)match(input,RULE_INT,FOLLOW_25); 

            	            		current.merge(this_INT_1);
            	                
            	             
            	                newLeafNode(this_INT_1, grammarAccess.getNarrativeAccess().getINTTerminalRuleCall_0_1()); 
            	                

            	            }
            	            break;
            	        case 3 :
            	            // InternalCucumber.g:919:10: this_STRING_2= RULE_STRING
            	            {
            	            this_STRING_2=(Token)match(input,RULE_STRING,FOLLOW_25); 

            	            		current.merge(this_STRING_2);
            	                
            	             
            	                newLeafNode(this_STRING_2, grammarAccess.getNarrativeAccess().getSTRINGTerminalRuleCall_0_2()); 
            	                

            	            }
            	            break;
            	        case 4 :
            	            // InternalCucumber.g:927:10: this_PLACEHOLDER_3= RULE_PLACEHOLDER
            	            {
            	            this_PLACEHOLDER_3=(Token)match(input,RULE_PLACEHOLDER,FOLLOW_25); 

            	            		current.merge(this_PLACEHOLDER_3);
            	                
            	             
            	                newLeafNode(this_PLACEHOLDER_3, grammarAccess.getNarrativeAccess().getPLACEHOLDERTerminalRuleCall_0_3()); 
            	                

            	            }
            	            break;

            	    }

            	    // InternalCucumber.g:934:2: (this_WORD_4= RULE_WORD | this_INT_5= RULE_INT | this_STRING_6= RULE_STRING | this_PLACEHOLDER_7= RULE_PLACEHOLDER | this_STEP_KEYWORD_8= RULE_STEP_KEYWORD | this_TAGNAME_9= RULE_TAGNAME )*
            	    loop34:
            	    do {
            	        int alt34=7;
            	        switch ( input.LA(1) ) {
            	        case RULE_WORD:
            	            {
            	            alt34=1;
            	            }
            	            break;
            	        case RULE_INT:
            	            {
            	            alt34=2;
            	            }
            	            break;
            	        case RULE_STRING:
            	            {
            	            alt34=3;
            	            }
            	            break;
            	        case RULE_PLACEHOLDER:
            	            {
            	            alt34=4;
            	            }
            	            break;
            	        case RULE_STEP_KEYWORD:
            	            {
            	            alt34=5;
            	            }
            	            break;
            	        case RULE_TAGNAME:
            	            {
            	            alt34=6;
            	            }
            	            break;

            	        }

            	        switch (alt34) {
            	    	case 1 :
            	    	    // InternalCucumber.g:934:7: this_WORD_4= RULE_WORD
            	    	    {
            	    	    this_WORD_4=(Token)match(input,RULE_WORD,FOLLOW_25); 

            	    	    		current.merge(this_WORD_4);
            	    	        
            	    	     
            	    	        newLeafNode(this_WORD_4, grammarAccess.getNarrativeAccess().getWORDTerminalRuleCall_1_0()); 
            	    	        

            	    	    }
            	    	    break;
            	    	case 2 :
            	    	    // InternalCucumber.g:942:10: this_INT_5= RULE_INT
            	    	    {
            	    	    this_INT_5=(Token)match(input,RULE_INT,FOLLOW_25); 

            	    	    		current.merge(this_INT_5);
            	    	        
            	    	     
            	    	        newLeafNode(this_INT_5, grammarAccess.getNarrativeAccess().getINTTerminalRuleCall_1_1()); 
            	    	        

            	    	    }
            	    	    break;
            	    	case 3 :
            	    	    // InternalCucumber.g:950:10: this_STRING_6= RULE_STRING
            	    	    {
            	    	    this_STRING_6=(Token)match(input,RULE_STRING,FOLLOW_25); 

            	    	    		current.merge(this_STRING_6);
            	    	        
            	    	     
            	    	        newLeafNode(this_STRING_6, grammarAccess.getNarrativeAccess().getSTRINGTerminalRuleCall_1_2()); 
            	    	        

            	    	    }
            	    	    break;
            	    	case 4 :
            	    	    // InternalCucumber.g:958:10: this_PLACEHOLDER_7= RULE_PLACEHOLDER
            	    	    {
            	    	    this_PLACEHOLDER_7=(Token)match(input,RULE_PLACEHOLDER,FOLLOW_25); 

            	    	    		current.merge(this_PLACEHOLDER_7);
            	    	        
            	    	     
            	    	        newLeafNode(this_PLACEHOLDER_7, grammarAccess.getNarrativeAccess().getPLACEHOLDERTerminalRuleCall_1_3()); 
            	    	        

            	    	    }
            	    	    break;
            	    	case 5 :
            	    	    // InternalCucumber.g:966:10: this_STEP_KEYWORD_8= RULE_STEP_KEYWORD
            	    	    {
            	    	    this_STEP_KEYWORD_8=(Token)match(input,RULE_STEP_KEYWORD,FOLLOW_25); 

            	    	    		current.merge(this_STEP_KEYWORD_8);
            	    	        
            	    	     
            	    	        newLeafNode(this_STEP_KEYWORD_8, grammarAccess.getNarrativeAccess().getSTEP_KEYWORDTerminalRuleCall_1_4()); 
            	    	        

            	    	    }
            	    	    break;
            	    	case 6 :
            	    	    // InternalCucumber.g:974:10: this_TAGNAME_9= RULE_TAGNAME
            	    	    {
            	    	    this_TAGNAME_9=(Token)match(input,RULE_TAGNAME,FOLLOW_25); 

            	    	    		current.merge(this_TAGNAME_9);
            	    	        
            	    	     
            	    	        newLeafNode(this_TAGNAME_9, grammarAccess.getNarrativeAccess().getTAGNAMETerminalRuleCall_1_5()); 
            	    	        

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop34;
            	        }
            	    } while (true);

            	    // InternalCucumber.g:981:3: (this_EOL_10= RULE_EOL )+
            	    int cnt35=0;
            	    loop35:
            	    do {
            	        int alt35=2;
            	        int LA35_0 = input.LA(1);

            	        if ( (LA35_0==RULE_EOL) ) {
            	            alt35=1;
            	        }


            	        switch (alt35) {
            	    	case 1 :
            	    	    // InternalCucumber.g:981:8: this_EOL_10= RULE_EOL
            	    	    {
            	    	    this_EOL_10=(Token)match(input,RULE_EOL,FOLLOW_26); 

            	    	    		current.merge(this_EOL_10);
            	    	        
            	    	     
            	    	        newLeafNode(this_EOL_10, grammarAccess.getNarrativeAccess().getEOLTerminalRuleCall_2()); 
            	    	        

            	    	    }
            	    	    break;

            	    	default :
            	    	    if ( cnt35 >= 1 ) break loop35;
            	                EarlyExitException eee =
            	                    new EarlyExitException(35, input);
            	                throw eee;
            	        }
            	        cnt35++;
            	    } while (true);


            	    }
            	    break;

            	default :
            	    if ( cnt36 >= 1 ) break loop36;
                        EarlyExitException eee =
                            new EarlyExitException(36, input);
                        throw eee;
                }
                cnt36++;
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
    // $ANTLR end "ruleNarrative"


    // $ANTLR start "entryRuleStepDescription"
    // InternalCucumber.g:996:1: entryRuleStepDescription returns [String current=null] : iv_ruleStepDescription= ruleStepDescription EOF ;
    public final String entryRuleStepDescription() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleStepDescription = null;


        try {
            // InternalCucumber.g:997:2: (iv_ruleStepDescription= ruleStepDescription EOF )
            // InternalCucumber.g:998:2: iv_ruleStepDescription= ruleStepDescription EOF
            {
             newCompositeNode(grammarAccess.getStepDescriptionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleStepDescription=ruleStepDescription();

            state._fsp--;

             current =iv_ruleStepDescription.getText(); 
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
    // $ANTLR end "entryRuleStepDescription"


    // $ANTLR start "ruleStepDescription"
    // InternalCucumber.g:1005:1: ruleStepDescription returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_WORD_0= RULE_WORD | this_INT_1= RULE_INT | this_STRING_2= RULE_STRING | this_PLACEHOLDER_3= RULE_PLACEHOLDER | this_STEP_KEYWORD_4= RULE_STEP_KEYWORD | this_TAGNAME_5= RULE_TAGNAME )+ ;
    public final AntlrDatatypeRuleToken ruleStepDescription() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_WORD_0=null;
        Token this_INT_1=null;
        Token this_STRING_2=null;
        Token this_PLACEHOLDER_3=null;
        Token this_STEP_KEYWORD_4=null;
        Token this_TAGNAME_5=null;

         enterRule(); 
            
        try {
            // InternalCucumber.g:1008:28: ( (this_WORD_0= RULE_WORD | this_INT_1= RULE_INT | this_STRING_2= RULE_STRING | this_PLACEHOLDER_3= RULE_PLACEHOLDER | this_STEP_KEYWORD_4= RULE_STEP_KEYWORD | this_TAGNAME_5= RULE_TAGNAME )+ )
            // InternalCucumber.g:1009:1: (this_WORD_0= RULE_WORD | this_INT_1= RULE_INT | this_STRING_2= RULE_STRING | this_PLACEHOLDER_3= RULE_PLACEHOLDER | this_STEP_KEYWORD_4= RULE_STEP_KEYWORD | this_TAGNAME_5= RULE_TAGNAME )+
            {
            // InternalCucumber.g:1009:1: (this_WORD_0= RULE_WORD | this_INT_1= RULE_INT | this_STRING_2= RULE_STRING | this_PLACEHOLDER_3= RULE_PLACEHOLDER | this_STEP_KEYWORD_4= RULE_STEP_KEYWORD | this_TAGNAME_5= RULE_TAGNAME )+
            int cnt37=0;
            loop37:
            do {
                int alt37=7;
                switch ( input.LA(1) ) {
                case RULE_TAGNAME:
                    {
                    alt37=6;
                    }
                    break;
                case RULE_STEP_KEYWORD:
                    {
                    alt37=5;
                    }
                    break;
                case RULE_WORD:
                    {
                    alt37=1;
                    }
                    break;
                case RULE_INT:
                    {
                    alt37=2;
                    }
                    break;
                case RULE_STRING:
                    {
                    alt37=3;
                    }
                    break;
                case RULE_PLACEHOLDER:
                    {
                    alt37=4;
                    }
                    break;

                }

                switch (alt37) {
            	case 1 :
            	    // InternalCucumber.g:1009:6: this_WORD_0= RULE_WORD
            	    {
            	    this_WORD_0=(Token)match(input,RULE_WORD,FOLLOW_24); 

            	    		current.merge(this_WORD_0);
            	        
            	     
            	        newLeafNode(this_WORD_0, grammarAccess.getStepDescriptionAccess().getWORDTerminalRuleCall_0()); 
            	        

            	    }
            	    break;
            	case 2 :
            	    // InternalCucumber.g:1017:10: this_INT_1= RULE_INT
            	    {
            	    this_INT_1=(Token)match(input,RULE_INT,FOLLOW_24); 

            	    		current.merge(this_INT_1);
            	        
            	     
            	        newLeafNode(this_INT_1, grammarAccess.getStepDescriptionAccess().getINTTerminalRuleCall_1()); 
            	        

            	    }
            	    break;
            	case 3 :
            	    // InternalCucumber.g:1025:10: this_STRING_2= RULE_STRING
            	    {
            	    this_STRING_2=(Token)match(input,RULE_STRING,FOLLOW_24); 

            	    		current.merge(this_STRING_2);
            	        
            	     
            	        newLeafNode(this_STRING_2, grammarAccess.getStepDescriptionAccess().getSTRINGTerminalRuleCall_2()); 
            	        

            	    }
            	    break;
            	case 4 :
            	    // InternalCucumber.g:1033:10: this_PLACEHOLDER_3= RULE_PLACEHOLDER
            	    {
            	    this_PLACEHOLDER_3=(Token)match(input,RULE_PLACEHOLDER,FOLLOW_24); 

            	    		current.merge(this_PLACEHOLDER_3);
            	        
            	     
            	        newLeafNode(this_PLACEHOLDER_3, grammarAccess.getStepDescriptionAccess().getPLACEHOLDERTerminalRuleCall_3()); 
            	        

            	    }
            	    break;
            	case 5 :
            	    // InternalCucumber.g:1041:10: this_STEP_KEYWORD_4= RULE_STEP_KEYWORD
            	    {
            	    this_STEP_KEYWORD_4=(Token)match(input,RULE_STEP_KEYWORD,FOLLOW_24); 

            	    		current.merge(this_STEP_KEYWORD_4);
            	        
            	     
            	        newLeafNode(this_STEP_KEYWORD_4, grammarAccess.getStepDescriptionAccess().getSTEP_KEYWORDTerminalRuleCall_4()); 
            	        

            	    }
            	    break;
            	case 6 :
            	    // InternalCucumber.g:1049:10: this_TAGNAME_5= RULE_TAGNAME
            	    {
            	    this_TAGNAME_5=(Token)match(input,RULE_TAGNAME,FOLLOW_24); 

            	    		current.merge(this_TAGNAME_5);
            	        
            	     
            	        newLeafNode(this_TAGNAME_5, grammarAccess.getStepDescriptionAccess().getTAGNAMETerminalRuleCall_5()); 
            	        

            	    }
            	    break;

            	default :
            	    if ( cnt37 >= 1 ) break loop37;
                        EarlyExitException eee =
                            new EarlyExitException(37, input);
                        throw eee;
                }
                cnt37++;
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
    // $ANTLR end "ruleStepDescription"


    // $ANTLR start "entryRuleTag"
    // InternalCucumber.g:1064:1: entryRuleTag returns [EObject current=null] : iv_ruleTag= ruleTag EOF ;
    public final EObject entryRuleTag() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTag = null;


        try {
            // InternalCucumber.g:1065:2: (iv_ruleTag= ruleTag EOF )
            // InternalCucumber.g:1066:2: iv_ruleTag= ruleTag EOF
            {
             newCompositeNode(grammarAccess.getTagRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleTag=ruleTag();

            state._fsp--;

             current =iv_ruleTag; 
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
    // $ANTLR end "entryRuleTag"


    // $ANTLR start "ruleTag"
    // InternalCucumber.g:1073:1: ruleTag returns [EObject current=null] : ( ( (lv_id_0_0= RULE_TAGNAME ) ) (this_EOL_1= RULE_EOL )? ) ;
    public final EObject ruleTag() throws RecognitionException {
        EObject current = null;

        Token lv_id_0_0=null;
        Token this_EOL_1=null;

         enterRule(); 
            
        try {
            // InternalCucumber.g:1076:28: ( ( ( (lv_id_0_0= RULE_TAGNAME ) ) (this_EOL_1= RULE_EOL )? ) )
            // InternalCucumber.g:1077:1: ( ( (lv_id_0_0= RULE_TAGNAME ) ) (this_EOL_1= RULE_EOL )? )
            {
            // InternalCucumber.g:1077:1: ( ( (lv_id_0_0= RULE_TAGNAME ) ) (this_EOL_1= RULE_EOL )? )
            // InternalCucumber.g:1077:2: ( (lv_id_0_0= RULE_TAGNAME ) ) (this_EOL_1= RULE_EOL )?
            {
            // InternalCucumber.g:1077:2: ( (lv_id_0_0= RULE_TAGNAME ) )
            // InternalCucumber.g:1078:1: (lv_id_0_0= RULE_TAGNAME )
            {
            // InternalCucumber.g:1078:1: (lv_id_0_0= RULE_TAGNAME )
            // InternalCucumber.g:1079:3: lv_id_0_0= RULE_TAGNAME
            {
            lv_id_0_0=(Token)match(input,RULE_TAGNAME,FOLLOW_23); 

            			newLeafNode(lv_id_0_0, grammarAccess.getTagAccess().getIdTAGNAMETerminalRuleCall_0_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getTagRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"id",
                    		lv_id_0_0, 
                    		"org.becausecucumber.eclipse.plugin.Cucumber.TAGNAME");
            	    

            }


            }

            // InternalCucumber.g:1095:2: (this_EOL_1= RULE_EOL )?
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==RULE_EOL) ) {
                alt38=1;
            }
            switch (alt38) {
                case 1 :
                    // InternalCucumber.g:1095:3: this_EOL_1= RULE_EOL
                    {
                    this_EOL_1=(Token)match(input,RULE_EOL,FOLLOW_2); 
                     
                        newLeafNode(this_EOL_1, grammarAccess.getTagAccess().getEOLTerminalRuleCall_1()); 
                        

                    }
                    break;

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
    // $ANTLR end "ruleTag"

    // Delegated rules


    protected DFA5 dfa5 = new DFA5(this);
    static final String dfa_1s = "\5\uffff";
    static final String dfa_2s = "\1\14\1\4\2\uffff\1\14";
    static final String dfa_3s = "\2\23\2\uffff\1\23";
    static final String dfa_4s = "\2\uffff\1\1\1\2\1\uffff";
    static final String dfa_5s = "\5\uffff}>";
    static final String[] dfa_6s = {
            "\1\1\5\uffff\1\2\1\3",
            "\1\4\7\uffff\1\1\5\uffff\1\2\1\3",
            "",
            "",
            "\1\1\5\uffff\1\2\1\3"
    };

    static final short[] dfa_1 = DFA.unpackEncodedString(dfa_1s);
    static final char[] dfa_2 = DFA.unpackEncodedStringToUnsignedChars(dfa_2s);
    static final char[] dfa_3 = DFA.unpackEncodedStringToUnsignedChars(dfa_3s);
    static final short[] dfa_4 = DFA.unpackEncodedString(dfa_4s);
    static final short[] dfa_5 = DFA.unpackEncodedString(dfa_5s);
    static final short[][] dfa_6 = unpackEncodedStringArray(dfa_6s);

    class DFA5 extends DFA {

        public DFA5(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 5;
            this.eot = dfa_1;
            this.eof = dfa_1;
            this.min = dfa_2;
            this.max = dfa_3;
            this.accept = dfa_4;
            this.special = dfa_5;
            this.transition = dfa_6;
        }
        public String getDescription() {
            return "162:1: (lv_scenarios_6_1= ruleScenario | lv_scenarios_6_2= ruleScenarioOutline )";
        }
    }
 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000011000L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000000F00L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x00000000000F1F10L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x00000000000F1F00L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x00000000000F1F02L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000000F10L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000000F30L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000000F20L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000000F22L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000000051000L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000000091000L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000000100F20L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000000001F20L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x00000000000000D2L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x00000000000000C2L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000000000000042L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000000000000F50L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x0000000000000052L});
    public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x0000000000001F22L});
    public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x0000000000001F30L});
    public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x0000000000000F12L});

}