package org.becausecucumber.eclipse.plugin.ui.handler;

import org.becausecucumber.eclipse.plugin.validation.CucumberJavaValidator;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IResource;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.ui.editor.quickfix.IssueResolutionProvider;
import org.eclipse.xtext.ui.editor.utils.EditorUtils;
import org.eclipse.xtext.ui.editor.validation.AnnotationIssueProcessor;
import org.eclipse.xtext.ui.editor.validation.IValidationIssueProcessor;
import org.eclipse.xtext.ui.editor.validation.MarkerCreator;
import org.eclipse.xtext.ui.editor.validation.MarkerIssueProcessor;
import org.eclipse.xtext.ui.editor.validation.ValidationJob;
import org.eclipse.xtext.ui.validation.MarkerTypeProvider;
import org.eclipse.xtext.validation.CheckMode;
import org.eclipse.xtext.validation.IResourceValidator;

import com.google.inject.Inject;

@SuppressWarnings("restriction")
public class ValidateActionHandler extends AbstractHandler {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.core.commands.IHandler#addHandlerListener(org.eclipse.core.
	 * commands.IHandlerListener)
	 * 
	 * 
	 * see the :
	 * org.cucumberpeople.eclipse.plugin.ui.CucumberExecutableExtensionFactory:
	 * org.eclipse.xtext.ui.editor.handler.ValidateActionHandler
	 * 
	 * org.cucumberpeople.eclipse.plugin.ui.CucumberExecutableExtensionFactory:
	 * org.eclipse.xtext.ui.editor.handler.ValidateActionHandler
	 */

	@Inject
	private IResourceValidator resourceValidator;
	@Inject
	private MarkerCreator markerCreator;
	@Inject
	private MarkerTypeProvider markerTypeProvider;
	@Inject
	private IssueResolutionProvider issueResolutionProvider;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.
	 * commands.ExecutionEvent)
	 * 
	 * @see
	 * https://github.com/search?l=java&q=Diagnostician.INSTANCE.validate&ref=
	 * searchresults&type=Code&utf8=%E2%9C%93
	 * 
	 * @see https://github.com/fikovnik/dsml-drawing-example/blob/
	 * a3299157c687f234c7b722a56c8bee63d333afc2/test/drawing/DrawingModelTest.
	 * java
	 * 
	 * @see
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {

		// TODO Auto-generated method stub
		/*
		 * Resource eResource =
		 * CucumberPackage.eINSTANCE.getCucumberFactory().eResource(); EObject
		 * root = (EObject) eResource.getContents().get(0);
		 * 
		 * Diagnostic validate = Diagnostician.INSTANCE.validate(root); switch
		 * (validate.getSeverity()) { case Diagnostic.ERROR: System.err.println(
		 * "Model has errors: "); break; case Diagnostic.WARNING:
		 * System.err.println("Model has warnings: "); } return null;
		 */
		CucumberJavaValidator.isvalidator = true;

		XtextEditor xtextEditor = EditorUtils.getActiveXtextEditor(event);
		if (xtextEditor != null) {
			IValidationIssueProcessor issueProcessor;
			IXtextDocument xtextDocument = xtextEditor.getDocument();
			IResource resource = xtextEditor.getResource();
			if (resource != null)
				issueProcessor = new MarkerIssueProcessor(resource, markerCreator, markerTypeProvider);
			else
				issueProcessor = new AnnotationIssueProcessor(xtextDocument,
						xtextEditor.getInternalSourceViewer().getAnnotationModel(), issueResolutionProvider);
			ValidationJob validationJob = new ValidationJob(resourceValidator, xtextDocument, issueProcessor,
					CheckMode.ALL);
			validationJob.schedule();
		}
		return this;
	}

}
