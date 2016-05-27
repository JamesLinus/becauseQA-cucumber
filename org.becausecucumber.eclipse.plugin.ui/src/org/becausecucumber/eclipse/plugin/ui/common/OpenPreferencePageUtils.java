package org.becausecucumber.eclipse.plugin.ui.common;

import org.becausecucumber.eclipse.plugin.ui.CucumberPeopleActivator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.PreferencesUtil;

@SuppressWarnings("restriction")
public class OpenPreferencePageUtils {

	private OpenPreferencePageUtils page = null;

	public OpenPreferencePageUtils getInstance() {
		if (page == null) {
			page = new OpenPreferencePageUtils();
		}
		return page;
	}

	public static boolean openMessageDialog(Shell shell, String message) {
		return MessageDialog.openConfirm(shell, CucumberPeopleActivator.ORG_CUCUMBERPEOPLE_ECLIPSE_PLUGIN_CUCUMBER,
				message);
	}

	public static void openPage() {
		/*
		 * there is a bug for openning this page ,will cased the nullpoint
		 * exception , i wil fix it in future
		 */

		// final IPreferenceNode targetNode=new PreferenceNode(id,page);
		// PreferenceManager
		// manager=GDNCucumberActivator.getInstance().getWorkbench().getPreferenceManager();
		// PreferenceManager manager =
		// PlatformUI.getWorkbench().getPreferenceManager();

		/*
		 * IPreferenceNode[] nodes = manager.getRootSubNodes(); if (nodes !=
		 * null) { for (IPreferenceNode node : nodes) { if
		 * (ExtensionIgnoreRules.isIgnored(node)) { manager.remove(node); } } }
		 */
		Shell shell = CucumberPeopleActivator.getInstance().getWorkbench().getActiveWorkbenchWindow().getShell();
		boolean openConfirm = MessageDialog.openConfirm(shell, "Check TestRail Account Settings",
				"TestRail had not been configured before or credentails are invalid,do you want to configure it now ?");
		if (openConfirm) {
			/*
			 * PreferenceDialog dialog = new PreferenceDialog(shell, manager);
			 * dialog.create(); dialog.setMessage(
			 * "Configure the Test Rail Project");
			 * 
			 * dialog.setSelectedNode(
			 * "com.greendot.gdn.Cucumber.ui.accountsettings"); dialog.open();
			 * Display.getCurrent().dispose();
			 */

			openPageWithoutFilter();

		} else {
			MessageDialog.openWarning(shell, "TestRail Account Settings", "You could configure TestRail credentails"
					+ " from the Preferences->Cucumber->Account Settings later,otherwise you cannot use TestRail integrataion feature in eclipse!");
		}

	}

	public static void openPageWithoutFilter() {
		PreferencesUtil
				.createPreferenceDialogOn(new Shell(), "org.becausecucumber.eclipse.plugin.Cucumber.accountsettings",
						new String[] { "org.becausecucumber.eclipse.plugin.Cucumber.accountsettings",
								"org.becausecucumber.eclipse.plugin.Cucumber.coloring",

								"org.becausecucumber.eclip.plugin.Cucumber.refactoring",
								"org.becausecucumber.eclipse.plugin.Cucumber.templates",
								"org.becausecucumber.eclipse.plugin.Cucumber.jenkins" },
						null)
				.open();
	}
}
