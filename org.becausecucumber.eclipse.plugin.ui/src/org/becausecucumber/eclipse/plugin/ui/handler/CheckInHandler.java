package org.becausecucumber.eclipse.plugin.ui.handler;

import org.becausecucumber.eclipse.plugin.common.console.Log;
import org.becausecucumber.eclipse.plugin.ui.CucumberPeopleActivator;
import org.becausecucumber.eclipse.plugin.ui.common.CucumberUtils;
import org.becausecucumber.eclipse.plugin.ui.dialog.TestRailCheckinDataDialog;
import org.becausecucumber.eclipse.plugin.ui.preference.Initializer.PreferenceConstants;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.texteditor.AbstractTextEditor;
import org.eclipse.ui.texteditor.ITextEditor;

@SuppressWarnings("restriction")
public class CheckInHandler implements IHandler {

	@Override
	public void addHandlerListener(IHandlerListener handlerListener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// TODO Auto-generated method stub
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		IWorkbenchPage page = window.getActivePage();

		IEditorPart activeEditor = page.getActiveEditor();
		if (!(activeEditor instanceof AbstractTextEditor)) {
			return null;
		}

		ITextEditor editor = (ITextEditor) activeEditor;
		IEditorInput input = editor.getEditorInput();

		IPath path = null;
		if (input instanceof FileEditorInput) {
			path = ((FileEditorInput) input).getPath();
		}

		// IPath path = input instanceof FileEditorInput ?
		// ((FileEditorInput)input).getPath():null;
		// if the test case existing in testrail ,just update it
		// 2015-12-15 add the support for upload for no-existing cucumber into
		// testrail
		Shell shell = HandlerUtil.getActiveWorkbenchWindowChecked(event).getShell();
		IPreferenceStore store = CucumberPeopleActivator.getInstance().getPreferenceStore();
		
		TestRailCheckinDataDialog testRailDataDialog = new TestRailCheckinDataDialog(shell);
		testRailDataDialog.create();
		// testRailDataDialog.getReturnCode()
		if (testRailDataDialog.open() == Window.OK) {
			Log.info("Check into TestRail now, please wait a few minutes to complete the feature update...");
			String testsuiteid = testRailDataDialog.getTestsuiteid();
	
			store.setValue(PreferenceConstants.TESTRAIL_TESTSUITE, testsuiteid);

			CucumberUtils.needTestRailUpdate(testsuiteid,path.toFile().getAbsolutePath());
		}
		return null;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isHandled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void removeHandlerListener(IHandlerListener handlerListener) {
		// TODO Auto-generated method stub

	}

}
