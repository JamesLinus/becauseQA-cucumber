package org.becausecucumber.eclipse.plugin.ui.handler;

import java.io.IOException;

import org.becausecucumber.eclipse.plugin.common.console.Log;
import org.becausecucumber.eclipse.plugin.common.testrail.TestRailAPI;
import org.becausecucumber.eclipse.plugin.common.testrail.TestRailImpl;
import org.becausecucumber.eclipse.plugin.ui.CucumberPeopleActivator;
import org.becausecucumber.eclipse.plugin.ui.common.OpenPreferencePageUtils;
import org.becausecucumber.eclipse.plugin.ui.dialog.TestRailCheckOutDataDialog;
import org.becausecucumber.eclipse.plugin.ui.preference.Initializer.PreferenceConstants;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.texteditor.AbstractTextEditor;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditor;

@SuppressWarnings("restriction")
public class CheckoutHandler implements IHandler {

	private int lineOffset = 0;
	// private boolean firstupdate=true;
	private String originalcontent = "";

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
		long suiteid = 0;
		long sectionid = 0;

		Shell shell = HandlerUtil.getActiveWorkbenchWindowChecked(event).getShell();
		IPreferenceStore store = CucumberPeopleActivator.getInstance().getPreferenceStore();
		String url = store.getString(PreferenceConstants.TESTRAIL_URL);
		String project = store.getString(PreferenceConstants.TESTRAIL_PROJECT);
		String user = store.getString(PreferenceConstants.TESTRAIL_USER);
		String password = store.getString(PreferenceConstants.TESTRAIL_PASSWORD);
		boolean initbefore = store.getBoolean(PreferenceConstants.TESTRAIL_DONE);
		if (!initbefore) {
			boolean initTestRail = TestRailImpl.getInstance().initTestRail(url, user, password, project);
			if (initTestRail) {
				store.setValue(PreferenceConstants.TESTRAIL_DONE, true);
				// parseCucumberFeature(filepath);
			} else {
				OpenPreferencePageUtils.openPage();
				return null;
			}
		}

		// shell.setSize(100, 100);
		TestRailCheckOutDataDialog testRailDataDialog = new TestRailCheckOutDataDialog(shell);
		testRailDataDialog.create();
		// testRailDataDialog.getReturnCode()
		if (testRailDataDialog.open() == Window.OK) {
			String testsuiteid = testRailDataDialog.getTestsuiteid();
			String testsectionid = testRailDataDialog.getTestsectionid();

			store.setValue(PreferenceConstants.TESTRAIL_TESTSUITE, testsuiteid);
			store.setValue(PreferenceConstants.TESTRAIL_TESTSECTION, testsectionid);

			IWorkbenchPage page = HandlerUtil.getActiveWorkbenchWindowChecked(event).getActivePage();
			if (page != null) {
				IEditorPart activeEditor = page.getActiveEditor();
				if (!(activeEditor instanceof AbstractTextEditor))
					return null;
				ITextEditor editor = (ITextEditor) activeEditor;

				IDocumentProvider dp = editor.getDocumentProvider();
				IEditorInput input = editor.getEditorInput();

				IDocument doc = dp.getDocument(input);
				int numberOfLines = doc.getNumberOfLines();
				try {
					lineOffset = doc.getLineOffset(numberOfLines - 1);
					System.out.println("the last line offset is:" + lineOffset);
				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					Log.error("Check out from TestRail exception:" + e1);
				}
				try {
					//
					// if(firstupdate){
					originalcontent = doc.get();
					// firstupdate=false;
					// }

					// call the test rail api

					if (testsuiteid.contains(TestRailAPI.base_Url) && testsuiteid.contains("/")) {
						String[] httpscope = testsuiteid.split("/");
						try {
							suiteid = Long.parseLong(httpscope[httpscope.length - 1]);
							// TestRailImpl.getInstance().api.setSuiteid(tempsuiteid);
						} catch (NumberFormatException e) {
							Log.error("not a valid http number for test suite");
						}

					} else if (testsuiteid != null && testsuiteid != "") {
						// test

						boolean getsuite = TestRailImpl.getInstance().findTestSuite(testsuiteid);
						if (getsuite) {
							suiteid = TestRailImpl.getInstance().getSuiteID();
						}
					} else {
						Log.error("not get the correct test suite name you had input");

					}

					if (testsectionid.contains(TestRailAPI.base_Url) && testsectionid.contains("/")) {
						String[] httpscope = testsectionid.split("/");
						try {
							sectionid = Long.parseLong(httpscope[httpscope.length - 1]);
						} catch (NumberFormatException e) {
							Log.error("not a valid http number for test suite");
						}

					} else if (testsectionid != null && testsectionid != "") {

						boolean getsection = TestRailImpl.getInstance().findTestSection(testsectionid);
						if (getsection) {
							sectionid = TestRailImpl.getInstance().getSectionId();
						}
					} else {
						Log.error("not get the correct test suite name you had input");

					}

					if (suiteid != 0 && sectionid != 0) {
						String insertcontent = TestRailImpl.getInstance().getSectionCases(suiteid, sectionid);
						doc.set(originalcontent + "\n\n" + insertcontent + "\n\n");
						// doc.replace(lineOffset+1, 0, insertcontent+"\n");
						Log.info("Check out all the code into current editor.");
					} else {
						MessageDialog.openError(shell, "Check out Cucumbe code from Test Rail",
								"Cannot check out the code from Test Rail," + "the test suite id is:" + suiteid
										+ ",the test section id is:" + sectionid);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				editor.doSave(new NullProgressMonitor());
			}
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
