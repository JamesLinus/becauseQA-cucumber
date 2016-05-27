package org.becausecucumber.eclipse.plugin.ui.launchs;

import java.io.BufferedWriter;
import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;

import org.becausecucumber.eclipse.plugin.common.Activator;
import org.becausecucumber.eclipse.plugin.common.CommandUtils;
import org.becausecucumber.eclipse.plugin.common.console.Log;
//import org.cucumberpeople.eclipse.plugin.ui.CucumberPeopleActivator;
import org.becausecucumber.eclipse.plugin.ui.common.JavaProjectLaunchUtils;

//import org.cucumberpeople.eclipse.plugin.ui.preference.Initializer.PreferenceConstants;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.model.ILaunchConfigurationDelegate;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditor;

public class CucumberRunLaunchConfigurationDelegate implements ILaunchConfigurationDelegate {

	public String featurePath = "";
	public String selectedtext = "";
	public boolean selectedall = false;
	public String cucumberjson = "";

	@Override
	public void launch(ILaunchConfiguration config, String mode, ILaunch launch, IProgressMonitor monitor)
			throws CoreException {
		// TODO Auto-generated method stub

		boolean isjava = config.getAttribute(CucumberFeatureLaunchConstants.IS_JAVA, false);

		// project.isNatureEnabled("org.eclipse.jdt.core.javanature");
		if (isjava) {
			Log.info("Launch Cucumber java project now...");
			new JavaProjectLaunchUtils().launch(config, mode, launch, monitor);
			return;
		}

		// the blow is for ruby project
		// Log.info("Launch Cucumber Ruby project now.. ");
		boolean rubyfound = Activator.getRubyPath();
		if (!rubyfound) {
			MessageDialog.openError(Display.getCurrent().getActiveShell(), "Find Ruby and Cucumber Configuration path",
					"Cannot find the Ruby or Cucumber installation path,you need to install them together firstly.");
			return;
		}

		if (monitor == null) {
			monitor = new NullProgressMonitor();
		}

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

		String rubypath = "";
		String rubyparameter = "";

		featurePath = config.getAttribute(CucumberFeatureLaunchConstants.ATTR_FEATURE_PATH, featurePath);
		@SuppressWarnings("unused")
		updateFileThread newthread;
		Display.getDefault().syncExec(newthread = new updateFileThread());
		// newthread.stop();
		if (!selectedtext.equals("")) {
			updateFeaturefile(featurePath, selectedtext);
		}

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

		rubypath = config.getAttribute(CucumberFeatureLaunchConstants.RUBY_PATH, rubypath);
		int lastindex = rubypath.lastIndexOf("\\");
		String cucumber = rubypath.substring(0, lastindex) + File.separator + "cucumber";

		rubyparameter = config.getAttribute(CucumberFeatureLaunchConstants.RUBY_PARAMETER, rubyparameter);

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
		// String output="--out";
		Collection<String> commands = new ArrayList<String>();

		// add the ruby path
		// rubypath,"-EUTF-8","-e","$stdout.sync=true;$stderr.sync=true;load($0=ARGV.shift)"
		commands.add(rubypath);
		commands.add("-EUTF-8");
		commands.add("-e");
		commands.add("$stdout.sync=true;$stderr.sync=true;load($0=ARGV.shift)");

		commands.add(cucumber);
		File runfile = new File(featurePath);
		String featurefile = runfile.getName();
		String parentdir = new File(runfile.getParent()).getParent();
		commands.add("\"features/" + featurefile + "\"");

		if (isPretty) {
			commands.add(formatter);
			commands.add("pretty");
		}

		if (isJson) {
			commands.add(formatter);
			/*
			 * File createTempDirectory =
			 * CommonFileUtils.createTempDirectory("cucumberreport");
			 * cucumberjson=createTempDirectory.getAbsolutePath()+File.separator
			 * +"cucumber.json"; commands.add("json"); commands.add(output);
			 * commands.add(cucumberjson);
			 */
			// + ">\""+cucumberjson+"\"");

		}
		/*
		 * if (isJunit) { commands.add(formatter); commands.add("junit:STDOUT");
		 * }
		 * 
		 * if (isProgress) { commands.add(formatter); commands.add("progress");
		 * }
		 * 
		 * if (isRerun) { commands.add(formatter); commands.add("rerun"); }
		 * 
		 * if (isHtml) { commands.add(formatter); commands.add("html:target"); }
		 * 
		 * if (isUsage) { commands.add(formatter); commands.add("usage"); }
		 */

		/*
		 * if (isMonochrome) args.add("--monochrome");
		 */
		commands.add("--expand");
		commands.add("-r");
		commands.add("features");
		String[] argarrays = commands.toArray(new String[0]);
		String argstr = "";
		for (int k = 0; k < argarrays.length; k++) {
			argstr = argstr + argarrays[k] + " ";
		}

		Log.info("Run Cucumber Feature File from command line now......\n\n" + argstr + "\n");

		// String[] commandpref={"cmd.exe","/c","cd",parentdir};
		// CommandUtils.newExecuteCommand(commandpref);
		Log.info(
				"******************************Below is the Cucumber Execution Result******************************************************\n");
		String newExecuteCommand = CommandUtils.newExecuteCommand(parentdir, argarrays);
		if (newExecuteCommand.contains("cannot load such file -- ruby-debug")) {
			Log.error(
					"Your host had not install Ruby-debug,please take to this Url to intall it,then rerun your cucumber again!\n");
			Log.error("url is: https://rubygems.org/gems/ruby-debug");
		}
		/*
		 * String prefcommand=rubypath+" "+rubyparameter+" "+cucumber+" \""
		 * +featurePath+"\" "+argstr; String
		 * resultcommand=CommandUtils.executeCommand(prefcommand);
		 * if(resultcommand.contains("cannot load such file -- ruby-debug")){
		 * Log.error(
		 * "Your host had not install Ruby-debug,please take to this Url to intall it,then rerun your cucumber again!\n"
		 * ); Log.error("url is: https://rubygems.org/gems/ruby-debug"); }
		 */
		// CommandUtils.executeCommand(command);
		String reportlocation = new File(featurePath).getParent();
		File newreportdir = new File(reportlocation + File.separator + "cucumber report");
		if (!newreportdir.exists()) {
			newreportdir.mkdir();
		}
		// String reportdir=newreportdir.getAbsolutePath();

		// Reporter.report(cucumberjson, newreportdir, "", "", "0");

		Log.info("Detail Execution result could be found in this location:" + newreportdir.getAbsolutePath());
		Log.info(
				"*******************************Above is the Cucumber Execution Result*****************************************************");
		// new File(featurePath).delete();
		/*
		 * if(featurePath.contains("temp.feature")){ new
		 * File(featurePath).delete(); }
		 */
		/*
		 * //PluginUtils.log("Cucumber Command output is：\n"); IPreferenceStore
		 * store = CucumberPeopleActivator.getInstance().getPreferenceStore();
		 * boolean savetestrail =
		 * store.getBoolean(PreferenceConstants.SAVE_TESTRAIL);
		 * if(savetestrail){ //upload the result into testrail }
		 */

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
