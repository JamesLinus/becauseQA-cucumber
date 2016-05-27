package org.becausecucumber.eclipse.plugin.common;

import org.eclipse.equinox.p2.core.IProvisioningAgentProvider;
import org.becausecucumber.eclipse.plugin.common.console.CucumberConsoleFactory;
import org.becausecucumber.eclipse.plugin.common.console.Log;
import org.becausecucumber.eclipse.plugin.common.searcher.QuickSearchPreferences;
import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.preferences.InstanceScope;

import org.eclipse.ui.IStartup;
import org.eclipse.ui.console.MessageConsoleStream;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.Version;

public class Activator implements IStartup, BundleActivator {

	private static BundleContext context;
	private static QuickSearchPreferences prefs = null; // Lazy initialized

	public static MessageConsoleStream out = null;
	public static boolean installedruby = false;

	public static final String PLUGIN_ID = "org.becausecucumber.eclipse.plugin.ui"; //$NON-NLS-1$
	private static Activator plugin;
	@SuppressWarnings("rawtypes")
	private ServiceReference provisioningAgentProviderServiceReference;

	private IProvisioningAgentProvider provisioningAgentProvider;

	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;

		// for the plugin check
		provisioningAgentProviderServiceReference = bundleContext
				.getServiceReference(IProvisioningAgentProvider.SERVICE_NAME);
		if (provisioningAgentProviderServiceReference != null) {
			@SuppressWarnings("unchecked")
			Object service = bundleContext
					.getService(provisioningAgentProviderServiceReference);
			provisioningAgentProvider = (IProvisioningAgentProvider) service;
		}
		plugin = this;

		/*
		 * ConsolePlugin plugin = ConsolePlugin.getDefault(); IConsoleManager
		 * conMan = plugin.getConsoleManager(); IConsole[] existing =
		 * conMan.getConsoles(); MessageConsole myConsole=null; for ( int i = 0;
		 * i < existing.length; i++ ) if ( "Cucumber Console".equals(
		 * existing[i].getName() ) ) myConsole=(MessageConsole) existing[i];
		 * //no console found, so create a new one myConsole = new
		 * MessageConsole( "Cucumber Console", null ); // Display display =
		 * Display.getCurrent(); // Font font = new Font(display, "Courier", 12,
		 * SWT.NORMAL); // org.eclipse.swt.graphics.Color bold =
		 * display.getSystemColor(SWT.COLOR_GRAY);
		 * 
		 * //myConsole.setBackground(bold); // myConsole.setFont(font);
		 * conMan.addConsoles( new IConsole[]{myConsole} );
		 * conMan.showConsoleView(myConsole);
		 * 
		 * 
		 * //conMan. out=myConsole.newMessageStream();
		 * out.setActivateOnWrite(true); Date time =
		 * Calendar.getInstance().getTime(); String sdf=new
		 * SimpleDateFormat("MMM.dd.yyyy EEE 'at' HH:mm:ss z").format(time);
		 */
		// out.println("["+sdf+"]: "+"Activate Cucumber plugin successful...");

		// check the ruby path
		if (PlatformValidator.isWindows()) {
			String rubyPath = CommonPluginUtils.getRubywPath();
			String cucumberPath = CommonPluginUtils.getCucumberPath();
			if (rubyPath != null && cucumberPath != null) {
				installedruby = true;
			} else {
				Log.error("Cucumber plugin cannot find Ruby installation path from your host's environment settings.");
			}
		}else{
			Log.warning("Cucumber Ruby feature not available in : "+Platform.getOS()+" operation system...");
		}
		// PluginUtils.log(true, Colors.green, "color output");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
		provisioningAgentProviderServiceReference = null;
		provisioningAgentProvider = null;
		CucumberConsoleFactory.closeConsole();
		plugin = null;
	}

	public static QuickSearchPreferences getPreferences() {
		if (prefs == null) {
			prefs = new QuickSearchPreferences(
					InstanceScope.INSTANCE
							.getNode("com.greendot.gdn.cucumber.ui.searcher"));
		}
		return prefs;
	}

	public static boolean getRubyPath() {
		return installedruby;
	}

	public static Activator getDefault() {
		return plugin;
	}

	@SuppressWarnings("rawtypes")
	public ServiceReference getAgentProviderServiceReference() {
		return provisioningAgentProviderServiceReference;
	}

	public IProvisioningAgentProvider getProvisioningAgentProvider() {
		return provisioningAgentProvider;
	}

	private ILog getLog() {
		return Platform.getLog(Platform.getBundle(PLUGIN_ID));
	}

	public void log(IStatus status) {
		getLog().log(status);
	}

	public void logErrorStatus(String message, Exception e) {
		log(new Status(Status.ERROR, PLUGIN_ID, message, e));
	}

	@Override
	public void earlyStartup() {
		// TODO Auto-generated method stub
		CucumberConsoleFactory.showConsole();

		Version longversion = Activator.getContext().getBundle().getVersion();

		String versionnumber = String.valueOf(longversion.getMajor())
				+ "."
				+ String.valueOf(longversion.getMinor() + "."
						+ String.valueOf(longversion.getMicro()) + "."
						+ String.valueOf(longversion.getQualifier()));

		Log.info("Started Cucumber plugin "
				+ versionnumber);
	}

}
