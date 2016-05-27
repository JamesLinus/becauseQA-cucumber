package org.becausecucumber.eclipse.plugin.ui.common;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import org.becausecucumber.eclipse.plugin.common.CommonFileUtils;
import org.becausecucumber.eclipse.plugin.common.CommonPluginUtils;
import org.becausecucumber.eclipse.plugin.common.PluginUtils;
import org.becausecucumber.eclipse.plugin.common.console.Log;
import org.becausecucumber.eclipse.plugin.common.testrail.TestRailAPI;
import org.becausecucumber.eclipse.plugin.common.testrail.TestRailImpl;
import org.becausecucumber.eclipse.plugin.cucumber.Step;
import org.becausecucumber.eclipse.plugin.ui.CucumberPeopleActivator;

import org.becausecucumber.eclipse.plugin.ui.preference.Initializer.PreferenceConstants;
import org.becausecucumber.eclipse.plugin.ui.wizard.NewRubyStepDefinationFileWizard;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourceAttributes;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.ui.actions.OpenNewClassWizardAction;
import org.eclipse.jdt.ui.wizards.NewClassWizardPage;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.texteditor.ITextEditor;



/**
 * @ClassName: CucumberUtils
 * @Description: TODO
 * @author alterhu2020@gmail.com
 * @date Jun 27, 2015 8:13:37 PM
 * 
 */

@SuppressWarnings("restriction")
public class CucumberUtils {

	// public static boolean initbefore=false;
	public static void needTestRailUpdate(String testsuiteid,String filepath) {
		IPreferenceStore store = CucumberPeopleActivator.getInstance().getPreferenceStore();
		// Shell shell =
		// CucumberPeopleActivator.getInstance().getWorkbench().getActiveWorkbenchWindow().getShell();
		String url = store.getString(PreferenceConstants.TESTRAIL_URL);
		String project = store.getString(PreferenceConstants.TESTRAIL_PROJECT);
		String user = store.getString(PreferenceConstants.TESTRAIL_USER);
		String password = store.getString(PreferenceConstants.TESTRAIL_PASSWORD);
		boolean initbefore = store.getBoolean(PreferenceConstants.TESTRAIL_DONE);
		if (!initbefore) {
			boolean initTestRail = TestRailImpl.getInstance().initTestRail(url, user, password, project);
			if (initTestRail) {

				parseCucumberFeature(filepath, testsuiteid);

			} else {
				OpenPreferencePageUtils.openPage();
			}
		} else {

			parseCucumberFeature(filepath, testsuiteid);

		}
	}

	public static void parseCucumberFeature(String filepath, String testsuiteid) {
		File file = new File(filepath);
		BufferedReader reader = null;
		LinkedList<Integer> scenariolines = new LinkedList<Integer>();

		LinkedList<Object> tagcontainer = new LinkedList<Object>();
		// LinkedList<Integer> scenariotitle=new LinkedList<Integer>();
		int eachfeaturelines = 0;
		String[] caseids;
		
		String testsectinname="";
		try {
			reader = new BufferedReader(new FileReader(file));
			String temp = "";
			String previousline = "";
			while ((temp = reader.readLine()) != null) {

				eachfeaturelines = eachfeaturelines + 1;
				String featuretag="Feature:";
				if(temp.trim().startsWith(featuretag)){
					testsectinname=temp.substring(featuretag.length()).trim();
				}
				if (temp.contains("Scenario:")
						|| temp.contains("Scenario Outline:") && (!temp.toString().trim().startsWith("#"))) {

					scenariolines.add(eachfeaturelines);
					// this is a valid scenario tag
					if (previousline.startsWith("@") && (!previousline.startsWith("#"))) {
						try {
							caseids = previousline.split("@");
							List<String> tags = new ArrayList<String>();
							for (int k = 1; k < caseids.length; k++) {
								tags.add(caseids[k].trim());
							}

							tagcontainer.add(tags);
						} catch (Exception e) {
							/*
							 * tagiscaseid=false; tagcontainer.add(-1);
							 */
							System.err.println("Ignore here, not a valid case id tag in scenario tag:" + previousline
									+ ",scenario title: " + temp);
						}
					} else {
						// we will create this scenario test case into testrail
						tagcontainer.add(-1);
					}

				}
				previousline = temp.trim();
			}

			scenariolines.add(eachfeaturelines + 2);

			String scenariocontent = "";
			int updatenumber = scenariolines.size() - 1; // remove the last line
															// end tag
			int totalupdated = 0;
			for (int k = 0; k < updatenumber; k++) {
				int startline = scenariolines.get(k);
				int endline = scenariolines.get(k + 1);

				Object tagobject = tagcontainer.get(k);

				scenariocontent = CommonFileUtils.getStringFromLine(file, startline + 1, endline - 2);

				String scenariotitle = CommonFileUtils.getStringFromLine(file, startline, startline).split(":")[1];

				if (tagobject instanceof List) { // this scenario have a tag id
					// updatenumber=updatenumber+1;

					@SuppressWarnings("unchecked")
					List<String> tags = (List<String>) tagobject;

					for (String tag : tags) {
						try {
							long tagid = Long.parseLong(tag);
							Log.info("Check into TestRail- Update existing Test Case ID:" + tagid);
							// if the test case existing in testrail ,just
							// update it
							long suiteid = TestRailImpl.api.getSuiteidByCase(tagid);
							// getTestCase(tagid);
							// Log.info("Check into TestRail-
							// api:"+TestRailImpl.api+",section
							// id:"+TestRailImpl.api.sectionid);
							if (suiteid > 0) {
								TestRailImpl.getInstance().updateTestCaseNew(tagid, scenariocontent);
								totalupdated = totalupdated + 1;
								Log.info("Check into TestRail Done- Update existing Test Case ID:" + tagid
										+ " completed! visit the TestRail to see detail changes!");

							} else {
								Log.info("Check into TestRail Done- find if existing Test Case ID:" + tagid
										+ " completed!cannot find this test case from testrail,please remove the tagid \"@"
										+ tagid + "\" and try again!");

							}
						} catch (NumberFormatException e) {

						}
					}

				} else {

					/*
					 * Log.info("Check into TestRail- Scenario title: "
					 * +scenariotitle+
					 * " no TAG ID which is the test case id for TestRail and will use section url to create a new test case..."
					 * );
					 * TestRailImpl.getInstance().creatNewTestCaseFromSectionID(
					 * suiteid,sectionid,null,scenariotitle,scenariocontent);
					 * totalupdated=totalupdated+1; Log.info(
					 * "Check into TestRail Done- Had create New Test Case: "
					 * +scenariotitle+",in this test suite id: "+suiteid+
					 * ",test section id: "+sectionid); }else{
					 */
					if(!testsectinname.equals("")){
					Log.info("Check into TestRail- Scenario title: " + scenariotitle
							+ " no TAG ID which is the test case id for TestRail and will use section name to create a new test case...");
					String suitename="";
					if (testsuiteid.contains(TestRailAPI.base_Url) && testsuiteid.contains("/")) {
						String[] httpscope = testsuiteid.split("/");
						try {
							long newsuiteid = Long.parseLong(httpscope[httpscope.length - 1]);
							suitename=TestRailImpl.api.getTestSuite(newsuiteid);
						} catch (NumberFormatException e) {
							Log.error("not a valid http number for test suite");
						}

					} else{
						suitename=testsuiteid;
					}
					
					TestRailImpl.getInstance().creatNewTestCase(suitename,testsectinname,null,scenariotitle,scenariocontent);
					totalupdated = totalupdated + 1;
					Log.info("Check into TestRail Done- Scenario title: " + scenariotitle
							+ " no TAG ID with test case id in testrail ,so will create a new test case...");
					}
				}

			}

			Shell shell = CucumberPeopleActivator.getInstance().getWorkbench().getActiveWorkbenchWindow().getShell();
			Log.info(
					"All scenarios uploaded into TestRail, Total updated or created test cases into TestRail Number are : "
							+ totalupdated);
			MessageDialog.openInformation(shell, "Cucumber Script updated in Test Rail",
					"Check-in cucumber script into TestRail,updated or created scenarios number in testrail are : "
							+ totalupdated);
		} catch (Exception e) {
			Log.error("met the unexpected error in this function parseCucumberFeature:" + e);
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static void findCucumberFeature(String filepath) {

		Pattern compile = Pattern.compile("Scenario:");
		// File file = new
		// File("E:\\workspaces\\Eclipse_workspace_mars\\popui.alter\\src\\demo.feature");
		BufferedReader reader = null;
		int tempfeaturetagindex = 0;
		int scenarionumber = 0;
		StringBuffer cucumber = new StringBuffer();
		// cucumber.
		// StringBuffer given=new StringBuffer();

		// StringBuffer when=new StringBuffer();

		// StringBuffer then=new StringBuffer();
		try {
			reader = new BufferedReader(new FileReader(filepath));

			String everyline = null;
			String previousline = null;
			long caseid = 0;
			int featureline = 0;

			boolean findtag = false;
			boolean beginfind = false;

			int totalgiven = 0;
			int totalwhen = 0;
			int totalthen = 0;

			while ((everyline = reader.readLine()) != null) {
				featureline = featureline + 1;
				findtag = compile.matcher(everyline).find();
				if (findtag) { // find the scenario tag
					tempfeaturetagindex = featureline;
					beginfind = true;
					scenarionumber = scenarionumber + 1;
					boolean validscenario = (totalthen > 0 || totalgiven > 0 || totalwhen > 0);
					if (previousline.contains("http") || previousline.contains("/") && previousline.contains("@")) {
						String[] httpscope = previousline.split("/");
						try {

							if (caseid != 0 && validscenario) {
								TestRailImpl.getInstance().updateTestCaseNew(caseid, cucumber.toString());
								cucumber.setLength(0);
								totalgiven = 0;
								totalwhen = 0;
								totalthen = 0;
							}
							caseid = Integer.parseInt(httpscope[httpscope.length - 1]);
						} catch (NumberFormatException e) {
							System.out.println("not a valid http number");
						}
					} else if (previousline.contains("@") && previousline.trim().length() > 1) {
						try {
							if (caseid != 0 && validscenario) {
								TestRailImpl.getInstance().updateTestCaseNew(caseid, cucumber.toString());
								cucumber.setLength(0);
								totalgiven = 0;
								totalwhen = 0;
								totalthen = 0;
							}
							caseid = Integer.parseInt(previousline.trim().substring(1));
						} catch (NumberFormatException e) {
							System.out.println("not a number case id");
						}
					} else {
						System.out.println("scenario tag error");
					}

				} else {
					if ((featureline > tempfeaturetagindex) && beginfind) {
						// System.out.print("case id is:"+caseid);
						if (everyline.trim().length() > 4) {
							if (everyline.trim().toLowerCase().trim().substring(0, 5).contains("given")) {
								System.out.println("case id is:" + caseid + " Update Case Given is:" + everyline);
								totalgiven = totalgiven + 1;
								cucumber.append(everyline + "\n");
								// TestRailImpl.getInstance().updateTestCase(caseid,"custom_cucumber_given",everyline);
							}
							if (everyline.trim().toLowerCase().trim().substring(0, 4).contains("when")) {
								System.out.println("case id is:" + caseid + " Update Case  when is:" + everyline);
								// TestRailImpl.getInstance().updateTestCase(caseid,"custom_cucumber_when",everyline);
								cucumber.append(everyline + "\n");
								totalwhen = totalwhen + 1;
							}
							if (everyline.trim().toLowerCase().trim().substring(0, 4).contains("then")) {
								System.out.println("case id is:" + caseid + " Update Case  then is:" + everyline);
								// TestRailImpl.getInstance().updateTestCase(caseid,"custom_cucumber_then",everyline);
								cucumber.append(everyline + "\n");
								totalthen = totalthen + 1;
							}
						}
					}

				}
				previousline = everyline;

			} // end while

			boolean validscenario = (totalthen > 0 || totalgiven > 0 || totalwhen > 0);
			if (caseid != 0 && validscenario && !findtag) {

				TestRailImpl.getInstance().updateTestCaseNew(caseid, cucumber.toString());
				cucumber.setLength(0);
				totalgiven = 0;
				totalwhen = 0;
				totalthen = 0;
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} // try statement

		Shell shell = CucumberPeopleActivator.getInstance().getWorkbench().getActiveWorkbenchWindow().getShell();
		MessageDialog.openInformation(shell, "Cucumber Script updated in Test Rail",
				"Updated Scenarios Number are :" + scenarionumber);
	}

	public static void createRubyFile(IWorkbench workbench, Step step) {

		NewRubyStepDefinationFileWizard wizard = new NewRubyStepDefinationFileWizard();
		String content = CucumberStepCreatorUtils.createRubyStepMethod(step);
		wizard.addInputStream(content);
		// IJavaElement javaElement =
		// org.eclipse.jdt.internal.ui.javaeditor.EditorUtility.getActiveEditorJavaInput();

		// ISelection selection=
		// workbench.getActiveWorkbenchWindow().getActivePage().getSelection();
		StructuredSelection selectionToPass = (StructuredSelection) CommonPluginUtils.getValidSelection();
		Shell shell = workbench.getActiveWorkbenchWindow().getShell();
		wizard.init(workbench, selectionToPass);
		WizardDialog wd = new WizardDialog(shell, wizard);
		wd.setTitle(wizard.getWindowTitle());
		wd.open();

	}

	public static void createJavaFile(IJavaProject project, Step step) {
		// CommonFileWizard fileWizard = new CommonFileWizard(2);
		// String stepdefination
		// =CommonCucumberFeatureUtils.Newstep2JavaDefination(keyword,content,findtable,count);

		Map<String, String> data = CucumberStepCreatorUtils.createJavaStepMethod(step);

		String comment = data.get("comments");
		String keyword = data.get("keyword");
		String method = data.get("method");
		String annotation = data.get("annotation");
		boolean findtable = Boolean.parseBoolean(data.get("hastable"));

		OpenNewClassWizardAction wizard = new OpenNewClassWizardAction();
		wizard.setOpenEditorOnFinish(true);
		NewClassWizardPage page = new NewClassWizardPage();

		// boolean pageComplete=false;

		try {

			// IPackageFragmentRoot rootpackage =
			// project.getPackageFragmentRoot("src/test/java");
			IPath rootpath = project.getProject().getFolder("src/main/java").getFullPath();
			IPackageFragmentRoot rootpackage = null;
			if (rootpath != null) {
				rootpackage = project.findPackageFragmentRoot(rootpath);
			} else {
				rootpackage = project.getPackageFragmentRoots()[0];
			}

			// IPackageFragment pack =
			// rootpackage.getPackageFragment("step_definations");

			IPath packpath = project.getProject().getFolder("src/main/java/step_definations").getFullPath();
			IPackageFragment pack = null;
			if (packpath != null) {
				pack = project.findPackageFragment(packpath);
			} else {
				pack = project.getPackageFragments()[0];
			}

			page.setPackageFragmentRoot(rootpackage, true);
			page.setPackageFragment(pack, true);
			page.setEnclosingTypeSelection(false, true);
			page.setTitle("Java Class for Cucumber");
			page.setMessage("This is a Java Class File with Cucumber Step Defination Defined");
			page.setDescription("Insert Cucumber Step:\n" + method);
			page.setTypeName("SampleSteps", true);
			page.setSuperClass("com.framework.selenium.BaseSteps", true);

			page.setMethodStubSelection(true, false, false, true);
			page.setAddComments(true, true);
			page.enableCommentControl(true);

			// page.createType(null);
			// page.init(selection);
		} catch (JavaModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wizard.setConfiguredWizardPage(page);
		wizard.run();

		// will create the java class

		IJavaElement createdElement = wizard.getCreatedElement();
		if (createdElement != null) {

			IPackageFragment selectedpack = page.getPackageFragment();

			String typename = page.getTypeName();

			String selectedpackage = "";
			if (selectedpack.getElementName() != null || selectedpack.getElementName() != "") {
				selectedpackage = "package " + selectedpack.getElementName() + ";\n\n";
			}

			String inserttable = "";
			if (findtable) {
				inserttable = "import java.util.List;\nimport java.util.Map;\n";
			}

			/**
			 * @ClassName: CucumberUtils
			 * @Description: TODO
			 * @author alterhu2020@gmail.com
			 * @date Jun 27, 2015 8:13:37 PM
			 * 
			 */
			String date = PluginUtils.getCurrentDate();
			String filedefination = selectedpackage + "import cucumber.api.java.en." + keyword + ";\n"
					+ "import org.openqa.selenium.support.PageFactory;\n"
					+ "//import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;\n"
					+ "import com.framework.selenium.BaseSteps;\n" + inserttable + "/**\n" + "* @ClassName: "
					+ typename + "\n" + "* @Description: TODO\n" + "* @author: TODO\n" + "* @date: " + date + "\n"
					+ "* \n" + "*/\n\n";

			StringBuffer buf = new StringBuffer();
			buf.append(filedefination);
			buf.append("public class " + typename + " extends BaseSteps {\n");
			buf.append("\n\tpublic " + typename + "(){\n");
			buf.append("\t\t// if current impletmented page is ajax page ,you need a make it as a timeout page\n");
			buf.append("\t\t//AjaxElementLocatorFactory factory=new AjaxElementLocatorFactory(driver, 1000);\n");
			buf.append("\t\t//PageFactory.initElements(factory,this);\n");
			buf.append("\t\t// if current impletmented is for mobile device app or browser like android or iphone device .etc\n");
			buf.append("\t\t//AppiumFieldDecorator mobiledecorator=new AppiumFieldDecorator(driver, new TimeOutDuration(500, TimeUnit.SECONDS))\n");
			buf.append("\t\t//PageFactory.initElements(mobiledecorator, this);\n");
			
			buf.append("\t}\n\n");
			buf.append(comment);
			buf.append(annotation);
			buf.append(method);
			buf.append("}\n");

			try {
				selectedpack.createCompilationUnit(typename + ".java", buf.toString(), true, null);
			} catch (JavaModelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void addJavaFileContent(IWorkbenchPage page, IFile file, Step step) {

		Map<String, String> data = CucumberStepCreatorUtils.createJavaStepMethod(step);

		String comment = data.get("comments");
		// String keyword=data.get("keyword");
		String method = data.get("method");
		String annotation = data.get("annotation");
		// boolean findtable=Boolean.parseBoolean(data.get("hastable"));

		String methodcontent = comment + annotation + method;

		int numberOfLines = 0;
		try {
			InputStream contents = file.getContents();
			// cover to a new inputstream
			BufferedReader in = new BufferedReader(new InputStreamReader(contents));
			String line = null;

			StringBuilder responseData = new StringBuilder();
			while ((line = in.readLine()) != null) {
				responseData.append(line + "\n");
			}
			String responsedata = responseData.toString().trim();
			if (responsedata == null || responsedata == "") {
				MessageDialog.openError(Display.getCurrent().getActiveShell(), "Java Content Checker",
						"The java source file content is empty!");
				return;
			}
			int lastIndexOf = responsedata.lastIndexOf("}");
			String modifiedcontent = "";
			if (lastIndexOf > 0 && responsedata.length() > 0) {
				// String tempstr = responsedata.substring(0, lastIndexOf-1);
				modifiedcontent = responsedata.substring(0, lastIndexOf - 1) + methodcontent + "}";
			} else {
				modifiedcontent = responsedata + methodcontent;
			}

			// change the readonly file to write
			ResourceAttributes resAttr = new ResourceAttributes();
			resAttr.setReadOnly(false);
			file.setResourceAttributes(resAttr);

			file.setContents(new ByteArrayInputStream(modifiedcontent.getBytes()), true, true, null);

			IEditorPart openEditor = IDE.openEditor(page, file);

			if (openEditor instanceof ITextEditor) {
				ITextEditor textEditor = (ITextEditor) openEditor;
				IDocument document = textEditor.getDocumentProvider().getDocument(textEditor.getEditorInput());
				numberOfLines = document.getNumberOfLines() - 6;
				IRegion region = document.getLineInformation(numberOfLines);
				int offset = region.getOffset();
				int length = region.getLength();
				textEditor.selectAndReveal(offset, length);
			}
		} catch (PartInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CoreException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void addRubyFileContent(IWorkbenchPage page, IFile file, Step step) {

		String stepcontent = CucumberStepCreatorUtils.createRubyStepMethod(step);
		int numberOfLines = 0;
		try {
			InputStream contents = file.getContents();
			// cover to a new inputstream
			BufferedReader in = new BufferedReader(new InputStreamReader(contents));
			String line = null;

			StringBuilder responseData = new StringBuilder();
			while ((line = in.readLine()) != null) {
				responseData.append(line + "\n");
			}

			String modifiedcontent = responseData.toString().trim() + stepcontent;

			file.setContents(new ByteArrayInputStream(modifiedcontent.getBytes()), true, true, null);

			IEditorPart openEditor = IDE.openEditor(page, file);

			if (openEditor instanceof ITextEditor) {
				ITextEditor textEditor = (ITextEditor) openEditor;
				IDocument document = textEditor.getDocumentProvider().getDocument(textEditor.getEditorInput());
				numberOfLines = document.getNumberOfLines() - 4;
				IRegion region = document.getLineInformation(numberOfLines);
				int offset = region.getOffset();
				int length = region.getLength();
				textEditor.selectAndReveal(offset, length);
			}
		} catch (PartInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CoreException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			System.out.println("bad line :" + numberOfLines);
			e.printStackTrace();
		}
	}

}
