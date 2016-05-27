package org.becausecucumber.eclipse.plugin.ui.common;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;

import org.becausecucumber.eclipse.plugin.common.console.Log;
import org.becausecucumber.eclipse.plugin.ui.launchs.CucumberFeatureLaunchConstants;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.jdt.launching.AbstractJavaLaunchConfigurationDelegate;
import org.eclipse.jdt.launching.IVMInstall;
import org.eclipse.jdt.launching.IVMRunner;
import org.eclipse.jdt.launching.VMRunnerConfiguration;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditor;

@SuppressWarnings("restriction")
public class JavaProjectLaunchUtils extends AbstractJavaLaunchConfigurationDelegate {

	public String featurePath = "";
	public String selectedtext = "";
	public boolean selectedall = false;

	public String mainclass = "cucumber.api.cli.Main";

	@Override
	public void launch(ILaunchConfiguration config, String mode, ILaunch launch, IProgressMonitor monitor)
			throws CoreException {
		// TODO Auto-generated method stub
		if (monitor == null) {
			monitor = new NullProgressMonitor();
		}

		/*
		 * IProject[] allproject = getBuildOrder(config, mode); IProject project
		 * = allproject[0];
		 */
		featurePath = config.getAttribute(CucumberFeatureLaunchConstants.ATTR_FEATURE_PATH, featurePath);

		@SuppressWarnings("unused")
		updateFileThread newthread;
		Display.getDefault().syncExec(newthread = new updateFileThread());
		// newthread.stop();
		if (!selectedtext.equals("")) {
			updateFeaturefile(featurePath, selectedtext);
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

		/*
		 * String rubypath=""; String rubyparameter="";
		 */

		// featurePath =
		// config.getAttribute(CucumberFeatureLaunchConstants.ATTR_FEATURE_PATH,
		// featurePath);
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

		// rubypath=config.getAttribute(CucumberFeatureLaunchConstants.RUBY_PATH,
		// rubypath);
		// rubypath=config.getAttribute(CucumberFeatureLaunchConstants.RUBY_PARAMETER,
		// rubyparameter);

		// System.out.println("Launching ....................... " +
		// featurePath);
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

		String glue = "--glue";
		String formatter = "--plugin";
		Collection<String> args = new ArrayList<String>();

		if (isPretty) {
			args.add(formatter);
			args.add("pretty");
		}

		if (isJson) {
			args.add(formatter);
			args.add("json:target");
		}

		if (isJunit) {
			args.add(formatter);
			args.add("junit:STDOUT");
		}

		if (isProgress) {
			args.add(formatter);
			args.add("progress");
		}

		if (isRerun) {
			args.add(formatter);
			args.add("rerun");
		}

		if (isHtml) {
			args.add(formatter);
			args.add("html:target");
		}

		if (isUsage) {
			args.add(formatter);
			args.add("usage");
		}

		if (isMonochrome)
			args.add("--monochrome");

		args.add(featurePath);
		args.add(glue);
		args.add(gluePath);

		String[] argarrays = args.toArray(new String[0]);
		String argstr = "";
		for (int k = 0; k < argarrays.length; k++) {
			argstr = argstr + argarrays[k] + " ";
		}
		System.out.println("arg str is:" + argstr);

		IVMInstall vm = verifyVMInstall(config);
		IVMRunner runner = vm.getVMRunner(mode);
		String[] classpath = getClasspath(config);
		// CucumberFeatureLaunchConstants.CUCUMBER_API_CLI_MAIN
		VMRunnerConfiguration runConfig = new VMRunnerConfiguration("cucumber.api.cli.Main", classpath);

		File workingDir = verifyWorkingDirectory(config);
		@SuppressWarnings("unused")
		String workingDirName = null;
		if (workingDir != null) {
			workingDirName = workingDir.getAbsolutePath();
		}

		String[] bootpath = getBootpath(config);
		runConfig.setBootClassPath(bootpath);

		runConfig.setProgramArguments(argarrays);
		runner.run(runConfig, launch, monitor);

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
