package org.becausecucumber.eclipse.plugin.ui;

import org.eclipse.xtext.diagnostics.Diagnostic;
import org.eclipse.xtext.nodemodel.SyntaxErrorMessage;
import org.eclipse.xtext.parser.antlr.ISyntaxErrorMessageProvider;

public class SyntaxErrorMessageProvider implements ISyntaxErrorMessageProvider {

	public SyntaxErrorMessage getSyntaxErrorMessage(IParserErrorContext context) {
		return new SyntaxErrorMessage("Syntax error for parser", Diagnostic.SYNTAX_DIAGNOSTIC);
	}

	public SyntaxErrorMessage getSyntaxErrorMessage(IValueConverterErrorContext context) {
		return new SyntaxErrorMessage("Syntax error for value converter", Diagnostic.SYNTAX_DIAGNOSTIC);
	}

}
