package org.becausecucumber.eclipse.plugin.ui.launchs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.becausecucumber.eclipse.plugin.common.Activator;
import org.becausecucumber.eclipse.plugin.common.CommonPluginUtils;
import org.becausecucumber.eclipse.plugin.common.console.Log;
import org.becausecucumber.eclipse.plugin.ui.common.JavaProjectLaunchUtils;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.core.IStreamListener;
import org.eclipse.debug.core.model.ILaunchConfigurationDelegate;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.debug.core.model.IStreamMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditor;

public class CucumberDebugLaunchConfigurationDelegate implements ILaunchConfigurationDelegate {

	private static final String DEBUGGER_PORT_SWITCH = "--port"; //$NON-NLS-1$
	/**
	 * Switch/arguments that tells ruby/debugger that we're done passing
	 * switches/arguments to it.
	 */
	private static final String END_OF_ARGUMENTS_DELIMETER = "--"; //$NON-NLS-1$

	public String featurePath = "";
	public String selectedtext = "";
	public boolean selectedall = false;

	@SuppressWarnings("deprecation")
	@Override
	public void launch(ILaunchConfiguration config, String mode, ILaunch launch, IProgressMonitor monitor)
			throws CoreException {
		// TODO Auto-generated method stub
		System.out.println("CucumberDebugLaunchConfigurationDelegate.launch()");
		if (monitor.isCanceled()) {
			return;
		}

		boolean isjava = config.getAttribute(CucumberFeatureLaunchConstants.IS_JAVA, false);
		// project.isNatureEnabled("org.eclipse.jdt.core.javanature");
		if (isjava) {
			Log.info("Launch Cucumber java project now...");
			new JavaProjectLaunchUtils().launch(config, mode, launch, monitor);
			return;
		}

		String[] envp = DebugPlugin.getDefault().getLaunchManager().getEnvironment(config);

		if (monitor.isCanceled()) {
			return;
		}

		featurePath = config.getAttribute(CucumberFeatureLaunchConstants.ATTR_FEATURE_PATH, featurePath);
		updateFileThread newthread;
		Display.getDefault().syncExec(newthread = new updateFileThread());
		newthread.stop();
		if (!selectedtext.equals("")) {
			updateFeaturefile(featurePath, selectedtext);
		}

		// the blow is for ruby project
		Log.info("Launch Cucumber Ruby project now.. ");
		boolean rubyfound = Activator.getRubyPath();
		if (!rubyfound) {
			MessageDialog.openError(Display.getCurrent().getActiveShell(), "Find Ruby and Cucumber Configuration path",
					"Cannot find the Ruby or Cucumber installation path,you need to install them together firstly.");
			return;
		}

		if (monitor.isCanceled()) {
			return;
		}

		List<String> commandList = new LinkedList<String>();

		String rubypath = "";
		String rubyparameter = "";

		/*
		 * if (isMonochrome) args.add("--monochrome");
		 */
		rubypath = config.getAttribute(CucumberFeatureLaunchConstants.RUBY_PATH, rubypath);
		// CucumberPeopleActivator.getInstance().getPreferenceStore().getString(PreferenceConstants.RUBY_PATH);
		// config.getAttribute(CucumberFeatureLaunchConstants.RUBY_PATH,
		// rubypath);
		commandList.add(rubypath);
		/*
		 * URL url =CucumberPeopleActivator.getInstance().getBundle().getEntry(
		 * "ruby/sync.rb"); File file = null; try { file=new
		 * File(FileLocator.resolve(url).toURI()); String filePath =
		 * file.getParent(); commandList.add("-I"); //$NON-NLS-1$
		 * commandList.add(filePath); commandList.add("-rsync"); //
		 * commandList.add(END_OF_ARGUMENTS_DELIMETER); } catch
		 * (URISyntaxException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } catch (IOException e) { // TODO Auto-generated
		 * catch block e.printStackTrace(); }
		 */
		commandList.add("-EUTF-8");
		commandList.add("-e");
		commandList.add("$stdout.sync=true;$stderr.sync=true;load($0=ARGV.shift)");
		// commandlist.add(END_OF_ARGUMENTS_DELIMETER);

		rubyparameter = config.getAttribute(CucumberFeatureLaunchConstants.RUBY_PARAMETER, rubyparameter);
		commandList.add(rubyparameter);
		commandList.add(DEBUGGER_PORT_SWITCH);
		int port = -1;
		if (mode.equals(ILaunchManager.DEBUG_MODE)) {
			// TODO Grab port from configuration?
			port = findFreePort();
			if (port == -1) {
				throw new CoreException(new Status(IStatus.ERROR, "Ruby debug mode", 0,
						"Canot find the free port to run ruby ,abort executions", null));
				// abort("Canot find the free port to run ruby ,abort
				// executions", null);
			}
			commandList.add(Integer.toString(port));
			commandList.add(END_OF_ARGUMENTS_DELIMETER);
			// commandList.add("");
		}

		// get the cucumber path
		String cucumberPath = CommonPluginUtils.getCucumberPath();
		if (cucumberPath == null) {
			return;
		}

		commandList.add(cucumberPath);

		featurePath = " \"" + featurePath + "\"";
		commandList.add(featurePath);
		// ----------------------------------------------------------

		String gluePath = "";
		String glueOption = "";

		boolean isMonochrome = false;
		boolean isPretty = false;
		boolean isProgress = false;
		boolean isJunit = false;
		boolean isJson = false;
		boolean isHtml = false;
		boolean isRerun = false;
		boolean isUsage = false;

		gluePath = config.getAttribute(CucumberFeatureLaunchConstants.ATTR_GLUE_PATH, gluePath);
		glueOption = config.getAttribute(CucumberFeatureLaunchConstants.ATTR_GLUE_OPTION, glueOption);
		isMonochrome = config.getAttribute(CucumberFeatureLaunchConstants.ATTR_IS_MONOCHROME, isMonochrome);
		isPretty = config.getAttribute(CucumberFeatureLaunchConstants.ATTR_IS_PRETTY, isPretty);
		isProgress = config.getAttribute(CucumberFeatureLaunchConstants.ATTR_IS_PROGRESS, isProgress);
		isJunit = config.getAttribute(CucumberFeatureLaunchConstants.ATTR_IS_JUNIT, isJunit);
		isJson = config.getAttribute(CucumberFeatureLaunchConstants.ATTR_IS_JSON, isJson);
		isHtml = config.getAttribute(CucumberFeatureLaunchConstants.ATTR_IS_HTML, isHtml);
		isRerun = config.getAttribute(CucumberFeatureLaunchConstants.ATTR_IS_RERUN, isRerun);
		isUsage = config.getAttribute(CucumberFeatureLaunchConstants.ATTR_IS_USAGE, isUsage);

		// CucumberPeopleActivator.getInstance().getPreferenceStore().getString(PreferenceConstants.RUBY_DEBUG_PATH);
		// config.getAttribute(CucumberFeatureLaunchConstants.RUBY_PARAMETER,
		// rubyparameter);
		// rubyparameter=rubyparameter.replaceAll("\\\\", "/");
		// rubyparameter="-EUTF-8 -e
		// $stdout.sync=true;$stderr.sync=true;load($0=ARGV.shift)
		// "+rubyparameter+"--disable-int-handler --port 55852 --dispatcher-port
		// 55853 ";
		System.out.println("Launching ....................... " + featurePath);
		System.out.println("Glueing ....................... " + gluePath);
		System.out.println("Glue paramter......................." + glueOption);
		System.out.println("is monochrome.................." + isMonochrome);
		System.out.println("is pretty.................." + isPretty);
		System.out.println("is progress.................." + isProgress);
		System.out.println("is html.................." + isHtml);
		System.out.println("is json.................." + isJson);
		System.out.println("is junit.................." + isJunit);
		System.out.println("is usage.................." + isUsage);
		System.out.println("is rerun.................." + isRerun);
		System.out.println("ruby path................" + rubypath);
		System.out.println("ruby parameter................" + rubyparameter);

		// String glue = "--glue";
		String formatter = "--format";

		if (isPretty) {
			commandList.add(formatter);
			commandList.add("pretty");
		}

		if (isJson) {
			commandList.add(formatter);
			commandList.add("json");
		}

		if (isJunit) {
			commandList.add(formatter);
			commandList.add("junit:STDOUT");
		}

		if (isProgress) {
			commandList.add(formatter);
			commandList.add("progress");
		}

		if (isRerun) {
			commandList.add(formatter);
			commandList.add("rerun");
		}

		if (isHtml) {
			commandList.add(formatter);
			commandList.add("html:target");
		}

		if (isUsage) {
			commandList.add(formatter);
			commandList.add("usage");
		}

		if (monitor.isCanceled()) {
			return;
		}
		// String[] array = commandList.toArray(new String[commandList.size()]);

		// Now actually launch the process!
		Process process = DebugPlugin.exec(commandList.toArray(new String[commandList.size()]), null, envp);

		// FIXME Build a label from args?
		String label = commandList.get(0);

		// Set process type to "ruby" so our linetracker hyperlink stuff works
		Map<String, String> map = new HashMap<String, String>();
		map.put(IProcess.ATTR_PROCESS_TYPE, "ruby");

		if (process != null) {
			monitor.beginTask("Ruby Debuggger Process is running ...", IProgressMonitor.UNKNOWN);
			IProcess p = DebugPlugin.newProcess(launch, process, label, map);
			if (p == null || process == null) {
				if (p != null)
					process.destroy();
				throw new CoreException(new Status(IStatus.ERROR, "", "", null));
			}

			if (mode.equals(ILaunchManager.DEBUG_MODE)) {
				/*
				 * RubyDebugTarget target = new RubyDebugTarget(launch, null,
				 * port); target.setProcess(p); RubyDebuggerProxy proxy = new
				 * RubyDebuggerProxy(target, true); try { proxy.start();
				 * launch.addDebugTarget(target);
				 * 
				 * } catch (RubyProcessingException e) {
				 * //RubyDebugCorePlugin.log(e); target.terminate(); } catch
				 * (IOException e) { //RubyDebugCorePlugin.log(e);
				 * target.terminate(); }
				 */
				// String content=
				// p.getStreamsProxy().getOutputStreamMonitor().getContents();
				p.getStreamsProxy().getOutputStreamMonitor().addListener(new IStreamListener() {

					@Override
					public void streamAppended(String text, IStreamMonitor monitor) {
						// TODO Auto-generated method stub
						Log.info(text + "\n");
					}
				});

				p.getStreamsProxy().getErrorStreamMonitor().addListener(new IStreamListener() {

					@Override
					public void streamAppended(String text, IStreamMonitor monitor) {
						// TODO Auto-generated method stub
						Log.info(text + "\n");
					}
				});
				try {
					// p = Runtime.getRuntime().exec(command);
					// p.waitFor();
					OutputStream output = process.getOutputStream();
					output.write("text".getBytes());

				} catch (Exception e) {
					e.printStackTrace();
				}

			}

			while (!p.isTerminated()) {
				try {
					if (monitor.isCanceled()) {
						p.terminate();
						break;
					}
					Thread.sleep(50);
				} catch (InterruptedException e) {
				}
			}
		}

		/*
		 * int lastindex = rubypath.lastIndexOf(File.separator); String
		 * cucumber=rubypath.substring(0, lastindex)+File.separator+"cucumber";
		 * String command=rubypath+" "+rubyparameter+" "+cucumber+" \""
		 * +featurePath+"\""+" "+commandList+" "+glueOption;
		 * 
		 * PluginUtils.log(true,"Launch Cucumber Command is:\n"+command);
		 * PluginUtils.log(true,
		 * "************************************************************************************"
		 * );
		 * 
		 * CommandUtils.executeCommand(command); PluginUtils.log(true,
		 * "************************************************************************************"
		 * );
		 */

		// PluginUtils.log("Cucumber Command output is：\n");

	}

	/**
	 * Returns a free port number on localhost, or -1 if unable to find a free
	 * port.
	 * 
	 * @return a free port number on localhost, or -1 if unable to find a free
	 *         port
	 */
	private static int findFreePort() {
		ServerSocket socket = null;
		try {
			socket = new ServerSocket(0);
			return socket.getLocalPort();
		} catch (IOException e) {
		} finally {
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
				}
			}
		}
		return -1;
	}

	class updateFileThread extends Thread {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			IEditorPart part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
			if (part != null) {
				if (part instanceof ITextEditor) {
					final ITextEditor editor = (ITextEditor) part;
					IDocumentProvider prov = editor.getDocumentProvider();
					IDocument doc = prov.getDocument(editor.getEditorInput());
					ISelection sel = editor.getSelectionProvider().getSelection();
					if (sel instanceof TextSelection) {
						final TextSelection textSel = (TextSelection) sel;
						selectedtext = textSel.getText();
						selectedall = selectedtext.equals("");
						if (selectedall) {
							selectedtext = doc.get();
						}
						//
					}
				}
			}
		}

	}

	private void updateFeaturefile(String featurefile, String selectedtext) {

		/*
		 * @Override public void selectionChanged(IWorkbenchPart part,
		 * ISelection selection) { // TODO Auto-generated method stub
		 * if(selection instanceof TextSelection){ final TextSelection
		 * textselection = (TextSelection)selection; String selectedtext =
		 * textselection.getText();
		 */
		// String parentpath=new File(parent).getParent();
		// String filename="temp_"+new
		// SimpleDateFormat("HHmmSSS").format(Calendar.getInstance().getTime())+".feature";
		// String userdir =parentpath+File.separator+"temp.feature";
		File tempfile = new File(featurefile);
		if (tempfile.exists()) {
			// Log.info( "Found file : "+featurefile+",will delete it.");
			tempfile.delete();
		}
		try {
			boolean iscreated = tempfile.createNewFile();
			// Log.info( "Create new file: "+iscreated+",will write the file
			// content then. ");
			if (iscreated) {
				Writer writer = null;

				try {
					writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(featurefile), "utf-8"));
					if (!selectedall) {
						writer.write("Feature: This file is used for exeuction\n");
					}
					writer.write(selectedtext);
				} catch (IOException ex) {
					// report
					Log.error("write file exception: " + ex);
				} finally {
					try {
						writer.close();
					} catch (Exception ex) {
						Log.error("write file exception: " + ex);
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.error("write file exception: " + e);
		}
		// return userdir;

	}

}
