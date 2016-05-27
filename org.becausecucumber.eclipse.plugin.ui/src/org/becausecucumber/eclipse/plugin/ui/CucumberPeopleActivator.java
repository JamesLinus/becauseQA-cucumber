package org.becausecucumber.eclipse.plugin.ui;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.becausecucumber.eclipse.plugin.common.Activator;
import org.becausecucumber.eclipse.plugin.common.PluginUpdater;
import org.becausecucumber.eclipse.plugin.ui.internal.CucumberActivator;
import org.becausecucumber.eclipse.plugin.ui.preference.Initializer.PreferenceConstants;
import org.becausecucumber.eclipse.plugin.ui.preference.searcherPage.QuickSearchPreferences;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.ui.IStartup;
import org.eclipse.ui.console.IConsolePageParticipant;
import org.osgi.framework.BundleContext;

@SuppressWarnings("restriction")
public class CucumberPeopleActivator extends CucumberActivator implements IStartup {

	private static QuickSearchPreferences prefs = null; // Lazy initialized
	public static final String ORG_CUCUMBERPEOPLE_ECLIPSE_PLUGIN_CUCUMBER = Activator.PLUGIN_ID;

	public static final String RUBY_IMGE = "image.ruby";
	public static final String CUKE_IMGE = "image.cukes";

	public static CucumberPeopleActivator plugin;

	private String updatesite = PreferenceConstants.UPDATE_SITE;
	private String pluginID = PreferenceConstants.UPDATE_PLUGIN_ID;

	@Override
	public void start(BundleContext context) throws Exception {
		// TODO Auto-generated method stub
		super.start(context);
		plugin = this;
		// startupSetting();

	}

	@Override
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	public static void log(Throwable exception) {
		log(createErrorStatus(exception));
	}

	public static void log(IStatus status) {
		// if (logger == null) {
		getInstance().getLog().log(status);
		// }
		// else {
		// logger.logEntry(status);
		// }
	}

	public static IStatus createErrorStatus(Throwable exception) {
		return new Status(IStatus.ERROR, ORG_CUCUMBERPEOPLE_ECLIPSE_PLUGIN_CUCUMBER, 0, exception.getMessage(),
				exception);
	}

	public static org.becausecucumber.eclipse.plugin.ui.preference.searcherPage.QuickSearchPreferences getPreferences() {
		if (prefs == null) {
			prefs = new QuickSearchPreferences(
					InstanceScope.INSTANCE.getNode(ORG_CUCUMBERPEOPLE_ECLIPSE_PLUGIN_CUCUMBER));
		}
		return prefs;
	}

	@Override
	protected void initializeImageRegistry(ImageRegistry registry) {
		super.initializeImageRegistry(registry);
		org.osgi.framework.Bundle bundle = this.getBundle();

		ImageDescriptor myImage = ImageDescriptor
				.createFromURL(FileLocator.find(bundle, new Path("icons/newproruby.png"), null));
		ImageDescriptor cukeImage = ImageDescriptor
				.createFromURL(FileLocator.find(bundle, new Path("icons/cukes.gif"), null));
		registry.put(RUBY_IMGE, myImage);
		registry.put(CUKE_IMGE, cukeImage);
	}

	public static CucumberPeopleActivator getDefault() {
		// TODO Auto-generated constructor stub
		return plugin;
	}

	@Override
	public void earlyStartup() {
		// TODO Auto-generated method stub
		/*
		 * IWebBrowser browser; try { browser =
		 * PlatformUI.getWorkbench().getBrowserSupport().createBrowser(
		 * "cucumber"); browser.openURL(new URL(updatesite)); } catch
		 * (PartInitException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } catch (MalformedURLException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */

		/*
		 * EmbeddedBrowser embeddedBrowser = new
		 * org.eclipse.help.ui.internal.browser.embedded.EmbeddedBrowser();
		 * embeddedBrowser.displayUrl("http://www.google.com");
		 */

		IPreferenceStore store = CucumberPeopleActivator.getInstance().getPreferenceStore();
		boolean checkplugin = store.getBoolean(PreferenceConstants.CHECK_PLUGIN);
		if (checkplugin) {
			new PluginUpdater(updatesite, pluginID).checkForUpdates();
		}

	}

	private Map<StyledText, IConsolePageParticipant> viewers = new HashMap<StyledText, IConsolePageParticipant>();

	public void addViewer(StyledText viewer, IConsolePageParticipant participant) {
		viewers.put(viewer, participant);
	}

	public void removeViewerWithPageParticipant(IConsolePageParticipant participant) {
		Set<StyledText> toRemove = new HashSet<StyledText>();

		for (StyledText viewer : viewers.keySet()) {
			if (viewers.get(viewer) == participant)
				toRemove.add(viewer);
		}

		for (StyledText viewer : toRemove)
			viewers.remove(viewer);
	}
}
