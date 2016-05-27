package org.becausecucumber.eclipse.plugin.ui.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.becausecucumber.eclipse.plugin.common.console.Log;
import org.becausecucumber.eclipse.plugin.common.testrail.TestRailImpl;
import org.becausecucumber.eclipse.plugin.ui.CucumberPeopleActivator;
import org.becausecucumber.eclipse.plugin.ui.common.OpenPreferencePageUtils;
import org.becausecucumber.eclipse.plugin.ui.dialog.EnvChangeDialog;
import org.becausecucumber.eclipse.plugin.ui.preference.Initializer.PreferenceConstants;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;

@SuppressWarnings("restriction")
public class EnvChangeHandler implements IHandler {

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
		// environment mapping

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
		EnvChangeDialog envdialog = new EnvChangeDialog(shell);
		envdialog.create();
		// testRailDataDialog.getReturnCode()
		if (envdialog.open() == Window.OK) {
			String testsuiteid = envdialog.getTestsuiteid();
			String environmentvalue = envdialog.getTestsectionid();

			store.setValue(PreferenceConstants.TESTRAIL_TESTSUITE, testsuiteid);
			store.setValue(PreferenceConstants.ENV_VALUE, environmentvalue);

			// if(environmentvalue.toLowerCase().matches("(qa3|qa4|qa5|dev-int|dev-int2|production)")){
			Map<String, Integer> envsmap = new HashMap<String, Integer>();
			/*
			 * envsmap.put("QA3", 1); envsmap.put("QA4", 2); envsmap.put("QA5",
			 * 3); envsmap.put("DEV-INT", 4); envsmap.put("DEV-INT2", 5);
			 * envsmap.put("Production", 6);
			 */
			envsmap.put("qa3", 1);
			envsmap.put("qa4", 2);
			envsmap.put("qa5", 3);
			envsmap.put("dev-int", 4);
			envsmap.put("dev-int2", 5);
			envsmap.put("production", 6);
			if (environmentvalue.contains(",")) {
				String[] envs = environmentvalue.split(",");
				List<Integer> parsenvs = new ArrayList<Integer>();
				for (int k = 0; k < envs.length; k++) {
					String everyenvalue = envs[k].toLowerCase().trim();
					if (everyenvalue.matches("(qa3|qa4|qa5|dev-int|dev-int2|production)")) {
						Integer mapkey = envsmap.get(everyenvalue);
						parsenvs.add(mapkey);
					}
				}
				if (parsenvs.isEmpty()) {
					Log.error(
							"Test Environment only could be [ QA3,QA4,QA5,DEV-INT,DEV-INT2,Production ] these values,please check the value you had inputed.");
					MessageDialog.openError(shell, "Test Rail Environment Configuration",
							"Test Environment only could be [ QA3,QA4,QA5,DEV-INT,DEV-INT2,Production ] these values,please check the value you had inputed.");

					return null;
				} else {
					TestRailImpl.getInstance().updateCustomeEnvironment(testsuiteid, parsenvs);
				}
			} else {
				// if this is only for one environment
				if (environmentvalue.toLowerCase().matches("(qa3|qa4|qa5|dev-int|dev-int2|production)")) {
					List<Integer> parsenvs = new ArrayList<Integer>();
					Integer mapkey = envsmap.get(environmentvalue.toLowerCase());
					parsenvs.add(mapkey);

					TestRailImpl.getInstance().updateCustomeEnvironment(testsuiteid, parsenvs);
				} else {
					Log.error(
							"Test Environment only could be [ QA3,QA4,QA5,DEV-INT,DEV-INT2,Production ] these values,please check the value you had inputed.");
					MessageDialog.openError(shell, "Test Rail Environment Configuration",
							"Test Environment only could be [ QA3,QA4,QA5,DEV-INT,DEV-INT2,Production ] these values,please check the value you had inputed.");

					return null;
				}
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
