package org.becausecucumber.eclipse.plugin.ui.dialog;

import org.becausecucumber.eclipse.plugin.common.Activator;
import org.becausecucumber.eclipse.plugin.ui.CucumberPeopleActivator;
import org.eclipse.ui.IStartup;

/*import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWebBrowser;*/
/*import org.eclipse.ui.browser.IWorkbenchBrowserSupport;
import org.eclipse.ui.internal.browser.WorkbenchBrowserSupport;*/
/*import org.eclipse.ui.browser.IWorkbenchBrowserSupport;
import org.eclipse.ui.internal.browser.WorkbenchBrowserSupport;
*/
@SuppressWarnings("restriction")
public class StartUpPage implements IStartup {

	// private String
	// updatesite="http://pas-vxi-142.nextestate.com:8888/cucumber-plugin-site/";
	// private String
	// pluginID="org.cucumberpeople.eclipse.plugin.feature.feature.group";

	@Override
	public void earlyStartup() {
		// TODO Auto-generated method stub

		/*
		 * int style = IWorkbenchBrowserSupport.AS_EDITOR |
		 * IWorkbenchBrowserSupport.LOCATION_BAR |
		 * IWorkbenchBrowserSupport.STATUS; IWebBrowser browser; try { browser =
		 * WorkbenchBrowserSupport.getInstance().createBrowser(style,
		 * "Cucumber", "Cucumber Plugin", "Cucumber Helper");
		 * browser.openURL(new URL("http://www.google.com")); } catch
		 * (PartInitException | MalformedURLException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		/*
		 * IWebBrowser browser; try { browser =
		 * PlatformUI.getWorkbench().getBrowserSupport().createBrowser(
		 * "cucumber"); browser.openURL(new URL(updatesite)); } catch
		 * (PartInitException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } catch (MalformedURLException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		new Activator().earlyStartup();
		new CucumberPeopleActivator().earlyStartup();

		/*
		 * EmbeddedBrowser embeddedBrowser = new
		 * org.eclipse.help.ui.internal.browser.embedded.EmbeddedBrowser();
		 * embeddedBrowser.displayUrl("http://www.google.com");
		 */

		// IPreferenceStore store =
		// CucumberPeopleActivator.getInstance().getPreferenceStore();
		// boolean checkplugin =
		// store.getBoolean(PreferenceConstants.CHECK_PLUGIN);
		// if(checkplugin){
		// new PluginUpdater(updatesite, pluginID).checkForUpdates();
		// }
	}

}
