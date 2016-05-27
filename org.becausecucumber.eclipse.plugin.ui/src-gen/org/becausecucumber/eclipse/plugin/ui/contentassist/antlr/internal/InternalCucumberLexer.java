package org.becausecucumber.eclipse.plugin.ui.contentassist.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.Lexer;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalCucumberLexer extends Lexer {
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

    public InternalCucumberLexer() {;} 
    public InternalCucumberLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public InternalCucumberLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "InternalCucumber.g"; }

    // $ANTLR start "T__16"
    public final void mT__16() throws RecognitionException {
        try {
            int _type = T__16;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalCucumber.g:11:7: ( 'Feature:' )
            // InternalCucumber.g:11:9: 'Feature:'
            {
            match("Feature:"); 


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
            // InternalCucumber.g:12:7: ( 'Background:' )
            // InternalCucumber.g:12:9: 'Background:'
            {
            match("Background:"); 


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
            // InternalCucumber.g:13:7: ( 'Scenario:' )
            // InternalCucumber.g:13:9: 'Scenario:'
            {
            match("Scenario:"); 


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
            // InternalCucumber.g:14:7: ( 'Scenario Outline:' )
            // InternalCucumber.g:14:9: 'Scenario Outline:'
            {
            match("Scenario Outline:"); 


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
            // InternalCucumber.g:15:7: ( 'Examples:' )
            // InternalCucumber.g:15:9: 'Examples:'
            {
            match("Examples:"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__20"

    // $ANTLR start "RULE_INT"
    public final void mRULE_INT() throws RecognitionException {
        try {
            int _type = RULE_INT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalCucumber.g:2596:10: ( ( '-' )? ( '0' .. '9' )+ ( '.' ( '0' .. '9' )+ )? )
            // InternalCucumber.g:2596:12: ( '-' )? ( '0' .. '9' )+ ( '.' ( '0' .. '9' )+ )?
            {
            // InternalCucumber.g:2596:12: ( '-' )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0=='-') ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // InternalCucumber.g:2596:12: '-'
                    {
                    match('-'); 

                    }
                    break;

            }

            // InternalCucumber.g:2596:17: ( '0' .. '9' )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>='0' && LA2_0<='9')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // InternalCucumber.g:2596:18: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

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

            // InternalCucumber.g:2596:29: ( '.' ( '0' .. '9' )+ )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0=='.') ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // InternalCucumber.g:2596:30: '.' ( '0' .. '9' )+
                    {
                    match('.'); 
                    // InternalCucumber.g:2596:34: ( '0' .. '9' )+
                    int cnt3=0;
                    loop3:
                    do {
                        int alt3=2;
                        int LA3_0 = input.LA(1);

                        if ( ((LA3_0>='0' && LA3_0<='9')) ) {
                            alt3=1;
                        }


                        switch (alt3) {
                    	case 1 :
                    	    // InternalCucumber.g:2596:35: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt3 >= 1 ) break loop3;
                                EarlyExitException eee =
                                    new EarlyExitException(3, input);
                                throw eee;
                        }
                        cnt3++;
                    } while (true);


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
    // $ANTLR end "RULE_INT"

    // $ANTLR start "RULE_STEP_KEYWORD"
    public final void mRULE_STEP_KEYWORD() throws RecognitionException {
        try {
            int _type = RULE_STEP_KEYWORD;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalCucumber.g:2598:19: ( ( 'Given' | 'When' | 'Then' | 'And' | 'But' ) ( ' ' | '\\t' )+ )
            // InternalCucumber.g:2598:21: ( 'Given' | 'When' | 'Then' | 'And' | 'But' ) ( ' ' | '\\t' )+
            {
            // InternalCucumber.g:2598:21: ( 'Given' | 'When' | 'Then' | 'And' | 'But' )
            int alt5=5;
            switch ( input.LA(1) ) {
            case 'G':
                {
                alt5=1;
                }
                break;
            case 'W':
                {
                alt5=2;
                }
                break;
            case 'T':
                {
                alt5=3;
                }
                break;
            case 'A':
                {
                alt5=4;
                }
                break;
            case 'B':
                {
                alt5=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }

            switch (alt5) {
                case 1 :
                    // InternalCucumber.g:2598:22: 'Given'
                    {
                    match("Given"); 


                    }
                    break;
                case 2 :
                    // InternalCucumber.g:2598:30: 'When'
                    {
                    match("When"); 


                    }
                    break;
                case 3 :
                    // InternalCucumber.g:2598:37: 'Then'
                    {
                    match("Then"); 


                    }
                    break;
                case 4 :
                    // InternalCucumber.g:2598:44: 'And'
                    {
                    match("And"); 


                    }
                    break;
                case 5 :
                    // InternalCucumber.g:2598:50: 'But'
                    {
                    match("But"); 


                    }
                    break;

            }

            // InternalCucumber.g:2598:57: ( ' ' | '\\t' )+
            int cnt6=0;
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0=='\t'||LA6_0==' ') ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // InternalCucumber.g:
            	    {
            	    if ( input.LA(1)=='\t'||input.LA(1)==' ' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


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

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_STEP_KEYWORD"

    // $ANTLR start "RULE_PLACEHOLDER"
    public final void mRULE_PLACEHOLDER() throws RecognitionException {
        try {
            int _type = RULE_PLACEHOLDER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalCucumber.g:2600:18: ( '<' (~ ( ( '>' | ' ' | '\\t' | '\\n' | '\\r' ) ) )+ '>' )
            // InternalCucumber.g:2600:20: '<' (~ ( ( '>' | ' ' | '\\t' | '\\n' | '\\r' ) ) )+ '>'
            {
            match('<'); 
            // InternalCucumber.g:2600:24: (~ ( ( '>' | ' ' | '\\t' | '\\n' | '\\r' ) ) )+
            int cnt7=0;
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( ((LA7_0>='\u0000' && LA7_0<='\b')||(LA7_0>='\u000B' && LA7_0<='\f')||(LA7_0>='\u000E' && LA7_0<='\u001F')||(LA7_0>='!' && LA7_0<='=')||(LA7_0>='?' && LA7_0<='\uFFFF')) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // InternalCucumber.g:2600:24: ~ ( ( '>' | ' ' | '\\t' | '\\n' | '\\r' ) )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\b')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='\u001F')||(input.LA(1)>='!' && input.LA(1)<='=')||(input.LA(1)>='?' && input.LA(1)<='\uFFFF') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


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

            match('>'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_PLACEHOLDER"

    // $ANTLR start "RULE_TABLE_ROW"
    public final void mRULE_TABLE_ROW() throws RecognitionException {
        try {
            int _type = RULE_TABLE_ROW;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalCucumber.g:2602:16: ( '|' ( (~ ( ( '|' | '\\n' | '\\r' ) ) )* '|' )+ ( ' ' | '\\t' )* RULE_NL )
            // InternalCucumber.g:2602:18: '|' ( (~ ( ( '|' | '\\n' | '\\r' ) ) )* '|' )+ ( ' ' | '\\t' )* RULE_NL
            {
            match('|'); 
            // InternalCucumber.g:2602:22: ( (~ ( ( '|' | '\\n' | '\\r' ) ) )* '|' )+
            int cnt9=0;
            loop9:
            do {
                int alt9=2;
                alt9 = dfa9.predict(input);
                switch (alt9) {
            	case 1 :
            	    // InternalCucumber.g:2602:23: (~ ( ( '|' | '\\n' | '\\r' ) ) )* '|'
            	    {
            	    // InternalCucumber.g:2602:23: (~ ( ( '|' | '\\n' | '\\r' ) ) )*
            	    loop8:
            	    do {
            	        int alt8=2;
            	        int LA8_0 = input.LA(1);

            	        if ( ((LA8_0>='\u0000' && LA8_0<='\t')||(LA8_0>='\u000B' && LA8_0<='\f')||(LA8_0>='\u000E' && LA8_0<='{')||(LA8_0>='}' && LA8_0<='\uFFFF')) ) {
            	            alt8=1;
            	        }


            	        switch (alt8) {
            	    	case 1 :
            	    	    // InternalCucumber.g:2602:23: ~ ( ( '|' | '\\n' | '\\r' ) )
            	    	    {
            	    	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='{')||(input.LA(1)>='}' && input.LA(1)<='\uFFFF') ) {
            	    	        input.consume();

            	    	    }
            	    	    else {
            	    	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	    	        recover(mse);
            	    	        throw mse;}


            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop8;
            	        }
            	    } while (true);

            	    match('|'); 

            	    }
            	    break;

            	default :
            	    if ( cnt9 >= 1 ) break loop9;
                        EarlyExitException eee =
                            new EarlyExitException(9, input);
                        throw eee;
                }
                cnt9++;
            } while (true);

            // InternalCucumber.g:2602:49: ( ' ' | '\\t' )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0=='\t'||LA10_0==' ') ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // InternalCucumber.g:
            	    {
            	    if ( input.LA(1)=='\t'||input.LA(1)==' ' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);

            mRULE_NL(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_TABLE_ROW"

    // $ANTLR start "RULE_DOC_STRING"
    public final void mRULE_DOC_STRING() throws RecognitionException {
        try {
            int _type = RULE_DOC_STRING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalCucumber.g:2604:17: ( ( '\"\"\"' ( options {greedy=false; } : . )* '\"\"\"' | '\\'\\'\\'' ( options {greedy=false; } : . )* '\\'\\'\\'' ) RULE_NL )
            // InternalCucumber.g:2604:19: ( '\"\"\"' ( options {greedy=false; } : . )* '\"\"\"' | '\\'\\'\\'' ( options {greedy=false; } : . )* '\\'\\'\\'' ) RULE_NL
            {
            // InternalCucumber.g:2604:19: ( '\"\"\"' ( options {greedy=false; } : . )* '\"\"\"' | '\\'\\'\\'' ( options {greedy=false; } : . )* '\\'\\'\\'' )
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0=='\"') ) {
                alt13=1;
            }
            else if ( (LA13_0=='\'') ) {
                alt13=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;
            }
            switch (alt13) {
                case 1 :
                    // InternalCucumber.g:2604:20: '\"\"\"' ( options {greedy=false; } : . )* '\"\"\"'
                    {
                    match("\"\"\""); 

                    // InternalCucumber.g:2604:26: ( options {greedy=false; } : . )*
                    loop11:
                    do {
                        int alt11=2;
                        int LA11_0 = input.LA(1);

                        if ( (LA11_0=='\"') ) {
                            int LA11_1 = input.LA(2);

                            if ( (LA11_1=='\"') ) {
                                int LA11_3 = input.LA(3);

                                if ( (LA11_3=='\"') ) {
                                    alt11=2;
                                }
                                else if ( ((LA11_3>='\u0000' && LA11_3<='!')||(LA11_3>='#' && LA11_3<='\uFFFF')) ) {
                                    alt11=1;
                                }


                            }
                            else if ( ((LA11_1>='\u0000' && LA11_1<='!')||(LA11_1>='#' && LA11_1<='\uFFFF')) ) {
                                alt11=1;
                            }


                        }
                        else if ( ((LA11_0>='\u0000' && LA11_0<='!')||(LA11_0>='#' && LA11_0<='\uFFFF')) ) {
                            alt11=1;
                        }


                        switch (alt11) {
                    	case 1 :
                    	    // InternalCucumber.g:2604:54: .
                    	    {
                    	    matchAny(); 

                    	    }
                    	    break;

                    	default :
                    	    break loop11;
                        }
                    } while (true);

                    match("\"\"\""); 


                    }
                    break;
                case 2 :
                    // InternalCucumber.g:2604:64: '\\'\\'\\'' ( options {greedy=false; } : . )* '\\'\\'\\''
                    {
                    match("'''"); 

                    // InternalCucumber.g:2604:73: ( options {greedy=false; } : . )*
                    loop12:
                    do {
                        int alt12=2;
                        int LA12_0 = input.LA(1);

                        if ( (LA12_0=='\'') ) {
                            int LA12_1 = input.LA(2);

                            if ( (LA12_1=='\'') ) {
                                int LA12_3 = input.LA(3);

                                if ( (LA12_3=='\'') ) {
                                    alt12=2;
                                }
                                else if ( ((LA12_3>='\u0000' && LA12_3<='&')||(LA12_3>='(' && LA12_3<='\uFFFF')) ) {
                                    alt12=1;
                                }


                            }
                            else if ( ((LA12_1>='\u0000' && LA12_1<='&')||(LA12_1>='(' && LA12_1<='\uFFFF')) ) {
                                alt12=1;
                            }


                        }
                        else if ( ((LA12_0>='\u0000' && LA12_0<='&')||(LA12_0>='(' && LA12_0<='\uFFFF')) ) {
                            alt12=1;
                        }


                        switch (alt12) {
                    	case 1 :
                    	    // InternalCucumber.g:2604:101: .
                    	    {
                    	    matchAny(); 

                    	    }
                    	    break;

                    	default :
                    	    break loop12;
                        }
                    } while (true);

                    match("'''"); 


                    }
                    break;

            }

            mRULE_NL(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_DOC_STRING"

    // $ANTLR start "RULE_STRING"
    public final void mRULE_STRING() throws RecognitionException {
        try {
            int _type = RULE_STRING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalCucumber.g:2606:13: ( ( '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' | '\\r' | '\\n' ) ) )* '\"' | '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' | '\\r' | '\\n' ) ) )* '\\'' ) )
            // InternalCucumber.g:2606:15: ( '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' | '\\r' | '\\n' ) ) )* '\"' | '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' | '\\r' | '\\n' ) ) )* '\\'' )
            {
            // InternalCucumber.g:2606:15: ( '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' | '\\r' | '\\n' ) ) )* '\"' | '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' | '\\r' | '\\n' ) ) )* '\\'' )
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0=='\"') ) {
                alt16=1;
            }
            else if ( (LA16_0=='\'') ) {
                alt16=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 16, 0, input);

                throw nvae;
            }
            switch (alt16) {
                case 1 :
                    // InternalCucumber.g:2606:16: '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' | '\\r' | '\\n' ) ) )* '\"'
                    {
                    match('\"'); 
                    // InternalCucumber.g:2606:20: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' | '\\r' | '\\n' ) ) )*
                    loop14:
                    do {
                        int alt14=3;
                        int LA14_0 = input.LA(1);

                        if ( (LA14_0=='\\') ) {
                            alt14=1;
                        }
                        else if ( ((LA14_0>='\u0000' && LA14_0<='\t')||(LA14_0>='\u000B' && LA14_0<='\f')||(LA14_0>='\u000E' && LA14_0<='!')||(LA14_0>='#' && LA14_0<='[')||(LA14_0>=']' && LA14_0<='\uFFFF')) ) {
                            alt14=2;
                        }


                        switch (alt14) {
                    	case 1 :
                    	    // InternalCucumber.g:2606:21: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' )
                    	    {
                    	    match('\\'); 
                    	    if ( input.LA(1)=='\"'||input.LA(1)=='\''||input.LA(1)=='\\'||input.LA(1)=='b'||input.LA(1)=='f'||input.LA(1)=='n'||input.LA(1)=='r'||(input.LA(1)>='t' && input.LA(1)<='u') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;}


                    	    }
                    	    break;
                    	case 2 :
                    	    // InternalCucumber.g:2606:66: ~ ( ( '\\\\' | '\"' | '\\r' | '\\n' ) )
                    	    {
                    	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='!')||(input.LA(1)>='#' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFF') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;}


                    	    }
                    	    break;

                    	default :
                    	    break loop14;
                        }
                    } while (true);

                    match('\"'); 

                    }
                    break;
                case 2 :
                    // InternalCucumber.g:2606:96: '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' | '\\r' | '\\n' ) ) )* '\\''
                    {
                    match('\''); 
                    // InternalCucumber.g:2606:101: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' | '\\r' | '\\n' ) ) )*
                    loop15:
                    do {
                        int alt15=3;
                        int LA15_0 = input.LA(1);

                        if ( (LA15_0=='\\') ) {
                            alt15=1;
                        }
                        else if ( ((LA15_0>='\u0000' && LA15_0<='\t')||(LA15_0>='\u000B' && LA15_0<='\f')||(LA15_0>='\u000E' && LA15_0<='&')||(LA15_0>='(' && LA15_0<='[')||(LA15_0>=']' && LA15_0<='\uFFFF')) ) {
                            alt15=2;
                        }


                        switch (alt15) {
                    	case 1 :
                    	    // InternalCucumber.g:2606:102: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' )
                    	    {
                    	    match('\\'); 
                    	    if ( input.LA(1)=='\"'||input.LA(1)=='\''||input.LA(1)=='\\'||input.LA(1)=='b'||input.LA(1)=='f'||input.LA(1)=='n'||input.LA(1)=='r'||(input.LA(1)>='t' && input.LA(1)<='u') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;}


                    	    }
                    	    break;
                    	case 2 :
                    	    // InternalCucumber.g:2606:147: ~ ( ( '\\\\' | '\\'' | '\\r' | '\\n' ) )
                    	    {
                    	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='&')||(input.LA(1)>='(' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFF') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;}


                    	    }
                    	    break;

                    	default :
                    	    break loop15;
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

    // $ANTLR start "RULE_SL_COMMENT"
    public final void mRULE_SL_COMMENT() throws RecognitionException {
        try {
            int _type = RULE_SL_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalCucumber.g:2608:17: ( '#' (~ ( ( '\\n' | '\\r' ) ) )* RULE_NL )
            // InternalCucumber.g:2608:19: '#' (~ ( ( '\\n' | '\\r' ) ) )* RULE_NL
            {
            match('#'); 
            // InternalCucumber.g:2608:23: (~ ( ( '\\n' | '\\r' ) ) )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( ((LA17_0>='\u0000' && LA17_0<='\t')||(LA17_0>='\u000B' && LA17_0<='\f')||(LA17_0>='\u000E' && LA17_0<='\uFFFF')) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // InternalCucumber.g:2608:23: ~ ( ( '\\n' | '\\r' ) )
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
            	    break loop17;
                }
            } while (true);

            mRULE_NL(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_SL_COMMENT"

    // $ANTLR start "RULE_TAGNAME"
    public final void mRULE_TAGNAME() throws RecognitionException {
        try {
            int _type = RULE_TAGNAME;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalCucumber.g:2610:14: ( '@' (~ ( ( ' ' | '\\t' | '\\n' | '\\r' ) ) )+ )
            // InternalCucumber.g:2610:16: '@' (~ ( ( ' ' | '\\t' | '\\n' | '\\r' ) ) )+
            {
            match('@'); 
            // InternalCucumber.g:2610:20: (~ ( ( ' ' | '\\t' | '\\n' | '\\r' ) ) )+
            int cnt18=0;
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( ((LA18_0>='\u0000' && LA18_0<='\b')||(LA18_0>='\u000B' && LA18_0<='\f')||(LA18_0>='\u000E' && LA18_0<='\u001F')||(LA18_0>='!' && LA18_0<='\uFFFF')) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // InternalCucumber.g:2610:20: ~ ( ( ' ' | '\\t' | '\\n' | '\\r' ) )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\b')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='\u001F')||(input.LA(1)>='!' && input.LA(1)<='\uFFFF') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


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


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_TAGNAME"

    // $ANTLR start "RULE_WORD"
    public final void mRULE_WORD() throws RecognitionException {
        try {
            int _type = RULE_WORD;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalCucumber.g:2612:11: (~ ( ( '@' | '|' | ' ' | '\\t' | '\\n' | '\\r' ) ) (~ ( ( ' ' | '\\t' | '\\n' | '\\r' ) ) )* )
            // InternalCucumber.g:2612:13: ~ ( ( '@' | '|' | ' ' | '\\t' | '\\n' | '\\r' ) ) (~ ( ( ' ' | '\\t' | '\\n' | '\\r' ) ) )*
            {
            if ( (input.LA(1)>='\u0000' && input.LA(1)<='\b')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='\u001F')||(input.LA(1)>='!' && input.LA(1)<='?')||(input.LA(1)>='A' && input.LA(1)<='{')||(input.LA(1)>='}' && input.LA(1)<='\uFFFF') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // InternalCucumber.g:2612:45: (~ ( ( ' ' | '\\t' | '\\n' | '\\r' ) ) )*
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( ((LA19_0>='\u0000' && LA19_0<='\b')||(LA19_0>='\u000B' && LA19_0<='\f')||(LA19_0>='\u000E' && LA19_0<='\u001F')||(LA19_0>='!' && LA19_0<='\uFFFF')) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // InternalCucumber.g:2612:45: ~ ( ( ' ' | '\\t' | '\\n' | '\\r' ) )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\b')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='\u001F')||(input.LA(1)>='!' && input.LA(1)<='\uFFFF') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop19;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_WORD"

    // $ANTLR start "RULE_WS"
    public final void mRULE_WS() throws RecognitionException {
        try {
            int _type = RULE_WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalCucumber.g:2614:9: ( ( ' ' | '\\t' ) )
            // InternalCucumber.g:2614:11: ( ' ' | '\\t' )
            {
            if ( input.LA(1)=='\t'||input.LA(1)==' ' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_WS"

    // $ANTLR start "RULE_EOL"
    public final void mRULE_EOL() throws RecognitionException {
        try {
            int _type = RULE_EOL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalCucumber.g:2616:10: ( RULE_NL )
            // InternalCucumber.g:2616:12: RULE_NL
            {
            mRULE_NL(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_EOL"

    // $ANTLR start "RULE_NL"
    public final void mRULE_NL() throws RecognitionException {
        try {
            // InternalCucumber.g:2618:18: ( ( '\\r' )? ( '\\n' )? )
            // InternalCucumber.g:2618:20: ( '\\r' )? ( '\\n' )?
            {
            // InternalCucumber.g:2618:20: ( '\\r' )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0=='\r') ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // InternalCucumber.g:2618:20: '\\r'
                    {
                    match('\r'); 

                    }
                    break;

            }

            // InternalCucumber.g:2618:26: ( '\\n' )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0=='\n') ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // InternalCucumber.g:2618:26: '\\n'
                    {
                    match('\n'); 

                    }
                    break;

            }


            }

        }
        finally {
        }
    }
    // $ANTLR end "RULE_NL"

    public void mTokens() throws RecognitionException {
        // InternalCucumber.g:1:8: ( T__16 | T__17 | T__18 | T__19 | T__20 | RULE_INT | RULE_STEP_KEYWORD | RULE_PLACEHOLDER | RULE_TABLE_ROW | RULE_DOC_STRING | RULE_STRING | RULE_SL_COMMENT | RULE_TAGNAME | RULE_WORD | RULE_WS | RULE_EOL )
        int alt22=16;
        alt22 = dfa22.predict(input);
        switch (alt22) {
            case 1 :
                // InternalCucumber.g:1:10: T__16
                {
                mT__16(); 

                }
                break;
            case 2 :
                // InternalCucumber.g:1:16: T__17
                {
                mT__17(); 

                }
                break;
            case 3 :
                // InternalCucumber.g:1:22: T__18
                {
                mT__18(); 

                }
                break;
            case 4 :
                // InternalCucumber.g:1:28: T__19
                {
                mT__19(); 

                }
                break;
            case 5 :
                // InternalCucumber.g:1:34: T__20
                {
                mT__20(); 

                }
                break;
            case 6 :
                // InternalCucumber.g:1:40: RULE_INT
                {
                mRULE_INT(); 

                }
                break;
            case 7 :
                // InternalCucumber.g:1:49: RULE_STEP_KEYWORD
                {
                mRULE_STEP_KEYWORD(); 

                }
                break;
            case 8 :
                // InternalCucumber.g:1:67: RULE_PLACEHOLDER
                {
                mRULE_PLACEHOLDER(); 

                }
                break;
            case 9 :
                // InternalCucumber.g:1:84: RULE_TABLE_ROW
                {
                mRULE_TABLE_ROW(); 

                }
                break;
            case 10 :
                // InternalCucumber.g:1:99: RULE_DOC_STRING
                {
                mRULE_DOC_STRING(); 

                }
                break;
            case 11 :
                // InternalCucumber.g:1:115: RULE_STRING
                {
                mRULE_STRING(); 

                }
                break;
            case 12 :
                // InternalCucumber.g:1:127: RULE_SL_COMMENT
                {
                mRULE_SL_COMMENT(); 

                }
                break;
            case 13 :
                // InternalCucumber.g:1:143: RULE_TAGNAME
                {
                mRULE_TAGNAME(); 

                }
                break;
            case 14 :
                // InternalCucumber.g:1:156: RULE_WORD
                {
                mRULE_WORD(); 

                }
                break;
            case 15 :
                // InternalCucumber.g:1:166: RULE_WS
                {
                mRULE_WS(); 

                }
                break;
            case 16 :
                // InternalCucumber.g:1:174: RULE_EOL
                {
                mRULE_EOL(); 

                }
                break;

        }

    }


    protected DFA9 dfa9 = new DFA9(this);
    protected DFA22 dfa22 = new DFA22(this);
    static final String DFA9_eotS =
        "\2\2\2\uffff";
    static final String DFA9_eofS =
        "\4\uffff";
    static final String DFA9_minS =
        "\2\0\2\uffff";
    static final String DFA9_maxS =
        "\2\uffff\2\uffff";
    static final String DFA9_acceptS =
        "\2\uffff\1\2\1\1";
    static final String DFA9_specialS =
        "\1\0\1\1\2\uffff}>";
    static final String[] DFA9_transitionS = {
            "\11\3\1\1\1\uffff\2\3\1\uffff\22\3\1\1\uffdf\3",
            "\11\3\1\1\1\uffff\2\3\1\uffff\22\3\1\1\uffdf\3",
            "",
            ""
    };

    static final short[] DFA9_eot = DFA.unpackEncodedString(DFA9_eotS);
    static final short[] DFA9_eof = DFA.unpackEncodedString(DFA9_eofS);
    static final char[] DFA9_min = DFA.unpackEncodedStringToUnsignedChars(DFA9_minS);
    static final char[] DFA9_max = DFA.unpackEncodedStringToUnsignedChars(DFA9_maxS);
    static final short[] DFA9_accept = DFA.unpackEncodedString(DFA9_acceptS);
    static final short[] DFA9_special = DFA.unpackEncodedString(DFA9_specialS);
    static final short[][] DFA9_transition;

    static {
        int numStates = DFA9_transitionS.length;
        DFA9_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA9_transition[i] = DFA.unpackEncodedString(DFA9_transitionS[i]);
        }
    }

    class DFA9 extends DFA {

        public DFA9(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 9;
            this.eot = DFA9_eot;
            this.eof = DFA9_eof;
            this.min = DFA9_min;
            this.max = DFA9_max;
            this.accept = DFA9_accept;
            this.special = DFA9_special;
            this.transition = DFA9_transition;
        }
        public String getDescription() {
            return "()+ loopback of 2602:22: ( (~ ( ( '|' | '\\n' | '\\r' ) ) )* '|' )+";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA9_0 = input.LA(1);

                        s = -1;
                        if ( (LA9_0=='\t'||LA9_0==' ') ) {s = 1;}

                        else if ( ((LA9_0>='\u0000' && LA9_0<='\b')||(LA9_0>='\u000B' && LA9_0<='\f')||(LA9_0>='\u000E' && LA9_0<='\u001F')||(LA9_0>='!' && LA9_0<='\uFFFF')) ) {s = 3;}

                        else s = 2;

                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA9_1 = input.LA(1);

                        s = -1;
                        if ( ((LA9_1>='\u0000' && LA9_1<='\b')||(LA9_1>='\u000B' && LA9_1<='\f')||(LA9_1>='\u000E' && LA9_1<='\u001F')||(LA9_1>='!' && LA9_1<='\uFFFF')) ) {s = 3;}

                        else if ( (LA9_1=='\t'||LA9_1==' ') ) {s = 1;}

                        else s = 2;

                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 9, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA22_eotS =
        "\1\23\5\21\1\33\5\21\1\uffff\2\21\1\51\4\uffff\5\21\1\33\1\21\1\uffff\5\21\1\44\2\21\1\uffff\1\44\2\21\1\51\1\uffff\5\21\1\33\4\21\1\103\2\21\1\44\2\21\1\44\2\21\1\uffff\5\21\1\uffff\1\21\1\uffff\16\21\2\105\4\21\1\136\3\21\1\uffff\1\21\1\144\1\uffff\1\145\1\21\2\uffff\1\147\1\uffff";
    static final String DFA22_eofS =
        "\150\uffff";
    static final String DFA22_minS =
        "\1\0\1\145\1\141\1\143\1\170\1\60\1\0\1\151\2\150\1\156\1\0\1\uffff\3\0\4\uffff\1\141\1\143\1\164\1\145\1\141\1\0\1\60\1\uffff\1\166\2\145\1\144\2\0\1\42\1\0\1\uffff\1\0\1\42\2\0\1\uffff\1\164\1\153\1\11\1\156\1\155\1\0\1\145\2\156\1\11\7\0\1\165\1\147\1\uffff\1\141\1\160\1\156\2\11\1\uffff\1\0\1\uffff\3\0\3\162\1\154\1\11\2\0\1\145\1\157\1\151\1\145\2\0\1\72\1\165\1\157\1\163\1\0\1\156\1\40\1\72\1\uffff\1\144\1\0\1\uffff\1\0\1\72\2\uffff\1\0\1\uffff";
    static final String DFA22_maxS =
        "\1\uffff\1\145\1\165\1\143\1\170\1\71\1\uffff\1\151\2\150\1\156\1\uffff\1\uffff\3\uffff\4\uffff\1\141\1\143\1\164\1\145\1\141\1\uffff\1\71\1\uffff\1\166\2\145\1\144\2\uffff\1\165\1\uffff\1\uffff\1\uffff\1\165\2\uffff\1\uffff\1\164\1\153\1\40\1\156\1\155\1\uffff\1\145\2\156\1\40\7\uffff\1\165\1\147\1\uffff\1\141\1\160\1\156\2\40\1\uffff\1\uffff\1\uffff\3\uffff\3\162\1\154\1\40\2\uffff\1\145\1\157\1\151\1\145\2\uffff\1\72\1\165\1\157\1\163\1\uffff\1\156\2\72\1\uffff\1\144\1\uffff\1\uffff\1\uffff\1\72\2\uffff\1\uffff\1\uffff";
    static final String DFA22_acceptS =
        "\14\uffff\1\11\3\uffff\1\15\1\16\1\17\1\20\7\uffff\1\6\10\uffff\1\13\4\uffff\1\14\23\uffff\1\7\5\uffff\1\10\1\uffff\1\12\30\uffff\1\1\2\uffff\1\4\2\uffff\1\3\1\5\1\uffff\1\2";
    static final String DFA22_specialS =
        "\1\7\5\uffff\1\22\4\uffff\1\37\1\uffff\1\21\1\6\1\20\11\uffff\1\2\6\uffff\1\1\1\13\1\uffff\1\5\1\uffff\1\23\1\uffff\1\36\1\30\6\uffff\1\32\4\uffff\1\3\1\10\1\4\1\26\1\24\1\34\1\31\11\uffff\1\15\1\uffff\1\25\1\35\1\40\5\uffff\1\11\1\27\4\uffff\1\16\1\0\4\uffff\1\12\5\uffff\1\17\1\uffff\1\33\3\uffff\1\14\1\uffff}>";
    static final String[] DFA22_transitionS = {
            "\11\21\1\22\1\uffff\2\21\1\uffff\22\21\1\22\1\21\1\15\1\17\3\21\1\16\5\21\1\5\2\21\12\6\2\21\1\13\3\21\1\20\1\12\1\2\2\21\1\4\1\1\1\7\13\21\1\3\1\11\2\21\1\10\44\21\1\14\uff83\21",
            "\1\24",
            "\1\25\23\uffff\1\26",
            "\1\27",
            "\1\30",
            "\12\31",
            "\11\21\2\uffff\2\21\1\uffff\22\21\1\uffff\15\21\1\32\1\21\12\31\uffc6\21",
            "\1\34",
            "\1\35",
            "\1\36",
            "\1\37",
            "\11\40\2\uffff\2\40\1\uffff\22\40\1\uffff\35\40\1\uffff\uffc1\40",
            "",
            "\11\43\1\44\1\uffff\2\43\1\uffff\22\43\1\44\1\43\1\41\71\43\1\42\uffa3\43",
            "\11\47\1\44\1\uffff\2\47\1\uffff\22\47\1\44\6\47\1\45\64\47\1\46\uffa3\47",
            "\11\50\2\uffff\2\50\1\uffff\22\50\1\uffff\uffdf\50",
            "",
            "",
            "",
            "",
            "\1\52",
            "\1\53",
            "\1\54",
            "\1\55",
            "\1\56",
            "\11\21\2\uffff\2\21\1\uffff\22\21\1\uffff\15\21\1\32\1\21\12\31\uffc6\21",
            "\12\57",
            "",
            "\1\60",
            "\1\61",
            "\1\62",
            "\1\63",
            "\11\40\2\uffff\2\40\1\uffff\22\40\1\uffff\35\40\1\64\uffc1\40",
            "\11\21\2\uffff\2\21\1\uffff\22\21\1\uffff\1\21\1\65\uffdd\21",
            "\1\66\4\uffff\1\66\64\uffff\1\66\5\uffff\1\66\3\uffff\1\66\7\uffff\1\66\3\uffff\1\66\1\uffff\2\66",
            "\11\43\1\44\1\uffff\2\43\1\uffff\22\43\1\44\1\43\1\67\71\43\1\42\uffa3\43",
            "",
            "\11\21\2\uffff\2\21\1\uffff\22\21\1\uffff\6\21\1\70\uffd8\21",
            "\1\71\4\uffff\1\71\64\uffff\1\71\5\uffff\1\71\3\uffff\1\71\7\uffff\1\71\3\uffff\1\71\1\uffff\2\71",
            "\11\47\1\44\1\uffff\2\47\1\uffff\22\47\1\44\6\47\1\72\64\47\1\46\uffa3\47",
            "\11\50\2\uffff\2\50\1\uffff\22\50\1\uffff\uffdf\50",
            "",
            "\1\73",
            "\1\74",
            "\1\75\26\uffff\1\75",
            "\1\76",
            "\1\77",
            "\11\21\2\uffff\2\21\1\uffff\22\21\1\uffff\17\21\12\57\uffc6\21",
            "\1\100",
            "\1\101",
            "\1\102",
            "\1\75\26\uffff\1\75",
            "\11\21\2\uffff\2\21\1\uffff\22\21\1\uffff\uffdf\21",
            "\11\106\2\105\2\106\1\105\22\106\1\105\1\106\1\104\uffdd\106",
            "\11\43\1\44\1\uffff\2\43\1\uffff\22\43\1\44\1\43\1\67\71\43\1\42\uffa3\43",
            "\11\21\2\uffff\2\21\1\uffff\22\21\1\uffff\uffdf\21",
            "\11\110\2\105\2\110\1\105\22\110\1\105\6\110\1\107\uffd8\110",
            "\11\47\1\44\1\uffff\2\47\1\uffff\22\47\1\44\6\47\1\72\64\47\1\46\uffa3\47",
            "\11\21\2\uffff\2\21\1\uffff\22\21\1\uffff\uffdf\21",
            "\1\111",
            "\1\112",
            "",
            "\1\113",
            "\1\114",
            "\1\115",
            "\1\75\26\uffff\1\75",
            "\1\75\26\uffff\1\75",
            "",
            "\11\106\2\105\2\106\1\105\22\106\1\105\1\106\1\116\uffdd\106",
            "",
            "\11\106\2\105\2\106\1\105\22\106\1\105\1\106\1\104\uffdd\106",
            "\11\110\2\105\2\110\1\105\22\110\1\105\6\110\1\117\uffd8\110",
            "\11\110\2\105\2\110\1\105\22\110\1\105\6\110\1\107\uffd8\110",
            "\1\120",
            "\1\121",
            "\1\122",
            "\1\123",
            "\1\75\26\uffff\1\75",
            "\11\106\2\105\2\106\1\105\22\106\1\105\1\106\1\124\uffdd\106",
            "\11\110\2\105\2\110\1\105\22\110\1\105\6\110\1\125\uffd8\110",
            "\1\126",
            "\1\127",
            "\1\130",
            "\1\131",
            "\11\106\2\uffff\2\106\1\uffff\22\106\1\uffff\1\106\1\124\uffdd\106",
            "\11\110\2\uffff\2\110\1\uffff\22\110\1\uffff\6\110\1\125\uffd8\110",
            "\1\132",
            "\1\133",
            "\1\134",
            "\1\135",
            "\11\21\2\uffff\2\21\1\uffff\22\21\1\uffff\uffdf\21",
            "\1\137",
            "\1\141\31\uffff\1\140",
            "\1\142",
            "",
            "\1\143",
            "\11\21\2\uffff\2\21\1\uffff\22\21\1\uffff\uffdf\21",
            "",
            "\11\21\2\uffff\2\21\1\uffff\22\21\1\uffff\uffdf\21",
            "\1\146",
            "",
            "",
            "\11\21\2\uffff\2\21\1\uffff\22\21\1\uffff\uffdf\21",
            ""
    };

    static final short[] DFA22_eot = DFA.unpackEncodedString(DFA22_eotS);
    static final short[] DFA22_eof = DFA.unpackEncodedString(DFA22_eofS);
    static final char[] DFA22_min = DFA.unpackEncodedStringToUnsignedChars(DFA22_minS);
    static final char[] DFA22_max = DFA.unpackEncodedStringToUnsignedChars(DFA22_maxS);
    static final short[] DFA22_accept = DFA.unpackEncodedString(DFA22_acceptS);
    static final short[] DFA22_special = DFA.unpackEncodedString(DFA22_specialS);
    static final short[][] DFA22_transition;

    static {
        int numStates = DFA22_transitionS.length;
        DFA22_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA22_transition[i] = DFA.unpackEncodedString(DFA22_transitionS[i]);
        }
    }

    class DFA22 extends DFA {

        public DFA22(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 22;
            this.eot = DFA22_eot;
            this.eof = DFA22_eof;
            this.min = DFA22_min;
            this.max = DFA22_max;
            this.accept = DFA22_accept;
            this.special = DFA22_special;
            this.transition = DFA22_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__16 | T__17 | T__18 | T__19 | T__20 | RULE_INT | RULE_STEP_KEYWORD | RULE_PLACEHOLDER | RULE_TABLE_ROW | RULE_DOC_STRING | RULE_STRING | RULE_SL_COMMENT | RULE_TAGNAME | RULE_WORD | RULE_WS | RULE_EOL );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA22_85 = input.LA(1);

                        s = -1;
                        if ( (LA22_85=='\'') ) {s = 85;}

                        else if ( ((LA22_85>='\u0000' && LA22_85<='\b')||(LA22_85>='\u000B' && LA22_85<='\f')||(LA22_85>='\u000E' && LA22_85<='\u001F')||(LA22_85>='!' && LA22_85<='&')||(LA22_85>='(' && LA22_85<='\uFFFF')) ) {s = 72;}

                        else s = 69;

                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA22_32 = input.LA(1);

                        s = -1;
                        if ( (LA22_32=='>') ) {s = 52;}

                        else if ( ((LA22_32>='\u0000' && LA22_32<='\b')||(LA22_32>='\u000B' && LA22_32<='\f')||(LA22_32>='\u000E' && LA22_32<='\u001F')||(LA22_32>='!' && LA22_32<='=')||(LA22_32>='?' && LA22_32<='\uFFFF')) ) {s = 32;}

                        else s = 17;

                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA22_25 = input.LA(1);

                        s = -1;
                        if ( (LA22_25=='.') ) {s = 26;}

                        else if ( ((LA22_25>='0' && LA22_25<='9')) ) {s = 25;}

                        else if ( ((LA22_25>='\u0000' && LA22_25<='\b')||(LA22_25>='\u000B' && LA22_25<='\f')||(LA22_25>='\u000E' && LA22_25<='\u001F')||(LA22_25>='!' && LA22_25<='-')||LA22_25=='/'||(LA22_25>=':' && LA22_25<='\uFFFF')) ) {s = 17;}

                        else s = 27;

                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA22_52 = input.LA(1);

                        s = -1;
                        if ( ((LA22_52>='\u0000' && LA22_52<='\b')||(LA22_52>='\u000B' && LA22_52<='\f')||(LA22_52>='\u000E' && LA22_52<='\u001F')||(LA22_52>='!' && LA22_52<='\uFFFF')) ) {s = 17;}

                        else s = 67;

                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA22_54 = input.LA(1);

                        s = -1;
                        if ( (LA22_54=='\"') ) {s = 55;}

                        else if ( (LA22_54=='\\') ) {s = 34;}

                        else if ( ((LA22_54>='\u0000' && LA22_54<='\b')||(LA22_54>='\u000B' && LA22_54<='\f')||(LA22_54>='\u000E' && LA22_54<='\u001F')||LA22_54=='!'||(LA22_54>='#' && LA22_54<='[')||(LA22_54>=']' && LA22_54<='\uFFFF')) ) {s = 35;}

                        else if ( (LA22_54=='\t'||LA22_54==' ') ) {s = 36;}

                        else s = 17;

                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA22_35 = input.LA(1);

                        s = -1;
                        if ( (LA22_35=='\"') ) {s = 55;}

                        else if ( (LA22_35=='\\') ) {s = 34;}

                        else if ( ((LA22_35>='\u0000' && LA22_35<='\b')||(LA22_35>='\u000B' && LA22_35<='\f')||(LA22_35>='\u000E' && LA22_35<='\u001F')||LA22_35=='!'||(LA22_35>='#' && LA22_35<='[')||(LA22_35>=']' && LA22_35<='\uFFFF')) ) {s = 35;}

                        else if ( (LA22_35=='\t'||LA22_35==' ') ) {s = 36;}

                        else s = 17;

                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA22_14 = input.LA(1);

                        s = -1;
                        if ( (LA22_14=='\'') ) {s = 37;}

                        else if ( (LA22_14=='\\') ) {s = 38;}

                        else if ( ((LA22_14>='\u0000' && LA22_14<='\b')||(LA22_14>='\u000B' && LA22_14<='\f')||(LA22_14>='\u000E' && LA22_14<='\u001F')||(LA22_14>='!' && LA22_14<='&')||(LA22_14>='(' && LA22_14<='[')||(LA22_14>=']' && LA22_14<='\uFFFF')) ) {s = 39;}

                        else if ( (LA22_14=='\t'||LA22_14==' ') ) {s = 36;}

                        else s = 17;

                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA22_0 = input.LA(1);

                        s = -1;
                        if ( (LA22_0=='F') ) {s = 1;}

                        else if ( (LA22_0=='B') ) {s = 2;}

                        else if ( (LA22_0=='S') ) {s = 3;}

                        else if ( (LA22_0=='E') ) {s = 4;}

                        else if ( (LA22_0=='-') ) {s = 5;}

                        else if ( ((LA22_0>='0' && LA22_0<='9')) ) {s = 6;}

                        else if ( (LA22_0=='G') ) {s = 7;}

                        else if ( (LA22_0=='W') ) {s = 8;}

                        else if ( (LA22_0=='T') ) {s = 9;}

                        else if ( (LA22_0=='A') ) {s = 10;}

                        else if ( (LA22_0=='<') ) {s = 11;}

                        else if ( (LA22_0=='|') ) {s = 12;}

                        else if ( (LA22_0=='\"') ) {s = 13;}

                        else if ( (LA22_0=='\'') ) {s = 14;}

                        else if ( (LA22_0=='#') ) {s = 15;}

                        else if ( (LA22_0=='@') ) {s = 16;}

                        else if ( ((LA22_0>='\u0000' && LA22_0<='\b')||(LA22_0>='\u000B' && LA22_0<='\f')||(LA22_0>='\u000E' && LA22_0<='\u001F')||LA22_0=='!'||(LA22_0>='$' && LA22_0<='&')||(LA22_0>='(' && LA22_0<=',')||(LA22_0>='.' && LA22_0<='/')||(LA22_0>=':' && LA22_0<=';')||(LA22_0>='=' && LA22_0<='?')||(LA22_0>='C' && LA22_0<='D')||(LA22_0>='H' && LA22_0<='R')||(LA22_0>='U' && LA22_0<='V')||(LA22_0>='X' && LA22_0<='{')||(LA22_0>='}' && LA22_0<='\uFFFF')) ) {s = 17;}

                        else if ( (LA22_0=='\t'||LA22_0==' ') ) {s = 18;}

                        else s = 19;

                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA22_53 = input.LA(1);

                        s = -1;
                        if ( (LA22_53=='\"') ) {s = 68;}

                        else if ( ((LA22_53>='\t' && LA22_53<='\n')||LA22_53=='\r'||LA22_53==' ') ) {s = 69;}

                        else if ( ((LA22_53>='\u0000' && LA22_53<='\b')||(LA22_53>='\u000B' && LA22_53<='\f')||(LA22_53>='\u000E' && LA22_53<='\u001F')||LA22_53=='!'||(LA22_53>='#' && LA22_53<='\uFFFF')) ) {s = 70;}

                        else s = 17;

                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA22_78 = input.LA(1);

                        s = -1;
                        if ( (LA22_78=='\"') ) {s = 84;}

                        else if ( ((LA22_78>='\u0000' && LA22_78<='\b')||(LA22_78>='\u000B' && LA22_78<='\f')||(LA22_78>='\u000E' && LA22_78<='\u001F')||LA22_78=='!'||(LA22_78>='#' && LA22_78<='\uFFFF')) ) {s = 70;}

                        else if ( ((LA22_78>='\t' && LA22_78<='\n')||LA22_78=='\r'||LA22_78==' ') ) {s = 69;}

                        else s = 17;

                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA22_90 = input.LA(1);

                        s = -1;
                        if ( ((LA22_90>='\u0000' && LA22_90<='\b')||(LA22_90>='\u000B' && LA22_90<='\f')||(LA22_90>='\u000E' && LA22_90<='\u001F')||(LA22_90>='!' && LA22_90<='\uFFFF')) ) {s = 17;}

                        else s = 94;

                        if ( s>=0 ) return s;
                        break;
                    case 11 : 
                        int LA22_33 = input.LA(1);

                        s = -1;
                        if ( (LA22_33=='\"') ) {s = 53;}

                        else if ( ((LA22_33>='\u0000' && LA22_33<='\b')||(LA22_33>='\u000B' && LA22_33<='\f')||(LA22_33>='\u000E' && LA22_33<='\u001F')||LA22_33=='!'||(LA22_33>='#' && LA22_33<='\uFFFF')) ) {s = 17;}

                        else s = 36;

                        if ( s>=0 ) return s;
                        break;
                    case 12 : 
                        int LA22_102 = input.LA(1);

                        s = -1;
                        if ( ((LA22_102>='\u0000' && LA22_102<='\b')||(LA22_102>='\u000B' && LA22_102<='\f')||(LA22_102>='\u000E' && LA22_102<='\u001F')||(LA22_102>='!' && LA22_102<='\uFFFF')) ) {s = 17;}

                        else s = 103;

                        if ( s>=0 ) return s;
                        break;
                    case 13 : 
                        int LA22_68 = input.LA(1);

                        s = -1;
                        if ( (LA22_68=='\"') ) {s = 78;}

                        else if ( ((LA22_68>='\u0000' && LA22_68<='\b')||(LA22_68>='\u000B' && LA22_68<='\f')||(LA22_68>='\u000E' && LA22_68<='\u001F')||LA22_68=='!'||(LA22_68>='#' && LA22_68<='\uFFFF')) ) {s = 70;}

                        else if ( ((LA22_68>='\t' && LA22_68<='\n')||LA22_68=='\r'||LA22_68==' ') ) {s = 69;}

                        else s = 17;

                        if ( s>=0 ) return s;
                        break;
                    case 14 : 
                        int LA22_84 = input.LA(1);

                        s = -1;
                        if ( (LA22_84=='\"') ) {s = 84;}

                        else if ( ((LA22_84>='\u0000' && LA22_84<='\b')||(LA22_84>='\u000B' && LA22_84<='\f')||(LA22_84>='\u000E' && LA22_84<='\u001F')||LA22_84=='!'||(LA22_84>='#' && LA22_84<='\uFFFF')) ) {s = 70;}

                        else s = 69;

                        if ( s>=0 ) return s;
                        break;
                    case 15 : 
                        int LA22_96 = input.LA(1);

                        s = -1;
                        if ( ((LA22_96>='\u0000' && LA22_96<='\b')||(LA22_96>='\u000B' && LA22_96<='\f')||(LA22_96>='\u000E' && LA22_96<='\u001F')||(LA22_96>='!' && LA22_96<='\uFFFF')) ) {s = 17;}

                        else s = 100;

                        if ( s>=0 ) return s;
                        break;
                    case 16 : 
                        int LA22_15 = input.LA(1);

                        s = -1;
                        if ( ((LA22_15>='\u0000' && LA22_15<='\b')||(LA22_15>='\u000B' && LA22_15<='\f')||(LA22_15>='\u000E' && LA22_15<='\u001F')||(LA22_15>='!' && LA22_15<='\uFFFF')) ) {s = 40;}

                        else s = 41;

                        if ( s>=0 ) return s;
                        break;
                    case 17 : 
                        int LA22_13 = input.LA(1);

                        s = -1;
                        if ( (LA22_13=='\"') ) {s = 33;}

                        else if ( (LA22_13=='\\') ) {s = 34;}

                        else if ( ((LA22_13>='\u0000' && LA22_13<='\b')||(LA22_13>='\u000B' && LA22_13<='\f')||(LA22_13>='\u000E' && LA22_13<='\u001F')||LA22_13=='!'||(LA22_13>='#' && LA22_13<='[')||(LA22_13>=']' && LA22_13<='\uFFFF')) ) {s = 35;}

                        else if ( (LA22_13=='\t'||LA22_13==' ') ) {s = 36;}

                        else s = 17;

                        if ( s>=0 ) return s;
                        break;
                    case 18 : 
                        int LA22_6 = input.LA(1);

                        s = -1;
                        if ( (LA22_6=='.') ) {s = 26;}

                        else if ( ((LA22_6>='0' && LA22_6<='9')) ) {s = 25;}

                        else if ( ((LA22_6>='\u0000' && LA22_6<='\b')||(LA22_6>='\u000B' && LA22_6<='\f')||(LA22_6>='\u000E' && LA22_6<='\u001F')||(LA22_6>='!' && LA22_6<='-')||LA22_6=='/'||(LA22_6>=':' && LA22_6<='\uFFFF')) ) {s = 17;}

                        else s = 27;

                        if ( s>=0 ) return s;
                        break;
                    case 19 : 
                        int LA22_37 = input.LA(1);

                        s = -1;
                        if ( (LA22_37=='\'') ) {s = 56;}

                        else if ( ((LA22_37>='\u0000' && LA22_37<='\b')||(LA22_37>='\u000B' && LA22_37<='\f')||(LA22_37>='\u000E' && LA22_37<='\u001F')||(LA22_37>='!' && LA22_37<='&')||(LA22_37>='(' && LA22_37<='\uFFFF')) ) {s = 17;}

                        else s = 36;

                        if ( s>=0 ) return s;
                        break;
                    case 20 : 
                        int LA22_56 = input.LA(1);

                        s = -1;
                        if ( (LA22_56=='\'') ) {s = 71;}

                        else if ( ((LA22_56>='\t' && LA22_56<='\n')||LA22_56=='\r'||LA22_56==' ') ) {s = 69;}

                        else if ( ((LA22_56>='\u0000' && LA22_56<='\b')||(LA22_56>='\u000B' && LA22_56<='\f')||(LA22_56>='\u000E' && LA22_56<='\u001F')||(LA22_56>='!' && LA22_56<='&')||(LA22_56>='(' && LA22_56<='\uFFFF')) ) {s = 72;}

                        else s = 17;

                        if ( s>=0 ) return s;
                        break;
                    case 21 : 
                        int LA22_70 = input.LA(1);

                        s = -1;
                        if ( (LA22_70=='\"') ) {s = 68;}

                        else if ( ((LA22_70>='\u0000' && LA22_70<='\b')||(LA22_70>='\u000B' && LA22_70<='\f')||(LA22_70>='\u000E' && LA22_70<='\u001F')||LA22_70=='!'||(LA22_70>='#' && LA22_70<='\uFFFF')) ) {s = 70;}

                        else if ( ((LA22_70>='\t' && LA22_70<='\n')||LA22_70=='\r'||LA22_70==' ') ) {s = 69;}

                        else s = 17;

                        if ( s>=0 ) return s;
                        break;
                    case 22 : 
                        int LA22_55 = input.LA(1);

                        s = -1;
                        if ( ((LA22_55>='\u0000' && LA22_55<='\b')||(LA22_55>='\u000B' && LA22_55<='\f')||(LA22_55>='\u000E' && LA22_55<='\u001F')||(LA22_55>='!' && LA22_55<='\uFFFF')) ) {s = 17;}

                        else s = 36;

                        if ( s>=0 ) return s;
                        break;
                    case 23 : 
                        int LA22_79 = input.LA(1);

                        s = -1;
                        if ( (LA22_79=='\'') ) {s = 85;}

                        else if ( ((LA22_79>='\u0000' && LA22_79<='\b')||(LA22_79>='\u000B' && LA22_79<='\f')||(LA22_79>='\u000E' && LA22_79<='\u001F')||(LA22_79>='!' && LA22_79<='&')||(LA22_79>='(' && LA22_79<='\uFFFF')) ) {s = 72;}

                        else if ( ((LA22_79>='\t' && LA22_79<='\n')||LA22_79=='\r'||LA22_79==' ') ) {s = 69;}

                        else s = 17;

                        if ( s>=0 ) return s;
                        break;
                    case 24 : 
                        int LA22_40 = input.LA(1);

                        s = -1;
                        if ( ((LA22_40>='\u0000' && LA22_40<='\b')||(LA22_40>='\u000B' && LA22_40<='\f')||(LA22_40>='\u000E' && LA22_40<='\u001F')||(LA22_40>='!' && LA22_40<='\uFFFF')) ) {s = 40;}

                        else s = 41;

                        if ( s>=0 ) return s;
                        break;
                    case 25 : 
                        int LA22_58 = input.LA(1);

                        s = -1;
                        if ( ((LA22_58>='\u0000' && LA22_58<='\b')||(LA22_58>='\u000B' && LA22_58<='\f')||(LA22_58>='\u000E' && LA22_58<='\u001F')||(LA22_58>='!' && LA22_58<='\uFFFF')) ) {s = 17;}

                        else s = 36;

                        if ( s>=0 ) return s;
                        break;
                    case 26 : 
                        int LA22_47 = input.LA(1);

                        s = -1;
                        if ( ((LA22_47>='0' && LA22_47<='9')) ) {s = 47;}

                        else if ( ((LA22_47>='\u0000' && LA22_47<='\b')||(LA22_47>='\u000B' && LA22_47<='\f')||(LA22_47>='\u000E' && LA22_47<='\u001F')||(LA22_47>='!' && LA22_47<='/')||(LA22_47>=':' && LA22_47<='\uFFFF')) ) {s = 17;}

                        else s = 27;

                        if ( s>=0 ) return s;
                        break;
                    case 27 : 
                        int LA22_98 = input.LA(1);

                        s = -1;
                        if ( ((LA22_98>='\u0000' && LA22_98<='\b')||(LA22_98>='\u000B' && LA22_98<='\f')||(LA22_98>='\u000E' && LA22_98<='\u001F')||(LA22_98>='!' && LA22_98<='\uFFFF')) ) {s = 17;}

                        else s = 101;

                        if ( s>=0 ) return s;
                        break;
                    case 28 : 
                        int LA22_57 = input.LA(1);

                        s = -1;
                        if ( (LA22_57=='\'') ) {s = 58;}

                        else if ( (LA22_57=='\\') ) {s = 38;}

                        else if ( ((LA22_57>='\u0000' && LA22_57<='\b')||(LA22_57>='\u000B' && LA22_57<='\f')||(LA22_57>='\u000E' && LA22_57<='\u001F')||(LA22_57>='!' && LA22_57<='&')||(LA22_57>='(' && LA22_57<='[')||(LA22_57>=']' && LA22_57<='\uFFFF')) ) {s = 39;}

                        else if ( (LA22_57=='\t'||LA22_57==' ') ) {s = 36;}

                        else s = 17;

                        if ( s>=0 ) return s;
                        break;
                    case 29 : 
                        int LA22_71 = input.LA(1);

                        s = -1;
                        if ( (LA22_71=='\'') ) {s = 79;}

                        else if ( ((LA22_71>='\u0000' && LA22_71<='\b')||(LA22_71>='\u000B' && LA22_71<='\f')||(LA22_71>='\u000E' && LA22_71<='\u001F')||(LA22_71>='!' && LA22_71<='&')||(LA22_71>='(' && LA22_71<='\uFFFF')) ) {s = 72;}

                        else if ( ((LA22_71>='\t' && LA22_71<='\n')||LA22_71=='\r'||LA22_71==' ') ) {s = 69;}

                        else s = 17;

                        if ( s>=0 ) return s;
                        break;
                    case 30 : 
                        int LA22_39 = input.LA(1);

                        s = -1;
                        if ( (LA22_39=='\'') ) {s = 58;}

                        else if ( (LA22_39=='\\') ) {s = 38;}

                        else if ( ((LA22_39>='\u0000' && LA22_39<='\b')||(LA22_39>='\u000B' && LA22_39<='\f')||(LA22_39>='\u000E' && LA22_39<='\u001F')||(LA22_39>='!' && LA22_39<='&')||(LA22_39>='(' && LA22_39<='[')||(LA22_39>=']' && LA22_39<='\uFFFF')) ) {s = 39;}

                        else if ( (LA22_39=='\t'||LA22_39==' ') ) {s = 36;}

                        else s = 17;

                        if ( s>=0 ) return s;
                        break;
                    case 31 : 
                        int LA22_11 = input.LA(1);

                        s = -1;
                        if ( ((LA22_11>='\u0000' && LA22_11<='\b')||(LA22_11>='\u000B' && LA22_11<='\f')||(LA22_11>='\u000E' && LA22_11<='\u001F')||(LA22_11>='!' && LA22_11<='=')||(LA22_11>='?' && LA22_11<='\uFFFF')) ) {s = 32;}

                        else s = 17;

                        if ( s>=0 ) return s;
                        break;
                    case 32 : 
                        int LA22_72 = input.LA(1);

                        s = -1;
                        if ( (LA22_72=='\'') ) {s = 71;}

                        else if ( ((LA22_72>='\u0000' && LA22_72<='\b')||(LA22_72>='\u000B' && LA22_72<='\f')||(LA22_72>='\u000E' && LA22_72<='\u001F')||(LA22_72>='!' && LA22_72<='&')||(LA22_72>='(' && LA22_72<='\uFFFF')) ) {s = 72;}

                        else if ( ((LA22_72>='\t' && LA22_72<='\n')||LA22_72=='\r'||LA22_72==' ') ) {s = 69;}

                        else s = 17;

                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 22, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

}