package com.framework.soapui;

import java.io.File;

import org.junit.Test;

import com.eviware.soapui.SoapUIPro;
import com.eviware.soapui.impl.wsdl.WsdlProject;
import com.eviware.soapui.impl.wsdl.WsdlProjectPro;
import com.eviware.soapui.impl.wsdl.WsdlTestSuite;
import com.eviware.soapui.impl.wsdl.testcase.WsdlTestCase;
import com.eviware.soapui.model.ModelItem;
import com.eviware.soapui.report.JUnitReportCollector;
import com.eviware.soapui.reporting.engine.junit.JUnitReportEngine;
import com.framework.utilities.PropertiesUtils;

/**
 * @ClassName: OldSoapUIProRunner
 * @Description: TODO
 * @author ahu@greendotcorp.com
 * @date Jul 20, 2015 9:59:18 PM
 *
 */

public class SoapUIRunner {

	// private Logger log = Logger.getLogger(SoapUIRunner.class);

	private static SoapUIRunner classrunner;
	private String groovydir = PropertiesUtils.getString("soapui.groovy.dir");
	private String soapuiext = PropertiesUtils.getString("soapui.ext.dir");

	private AbstractCucumberCaseRunner runner;
	private String output;

	/**
	 * @Title: getInstance
	 * @Description: TODO
	 * @author ahu@greendotcorp.com
	 * @param @return
	 * @return SoapUIProRunner return type
	 * @throws
	 */

	public static SoapUIRunner getInstance() {
		if (classrunner == null) {
			classrunner = new SoapUIRunner();
		}

		return classrunner;

	}

	public WsdlProjectPro initProject(String projectname) {

		String path = new File("").getAbsolutePath();
		String logdir = path + File.separator + "test-logs" + File.separator
				+ "soapui-logs" + File.separator;
		String outputFolder = path + File.separator + "test-result"
				+ File.separator + "cucumber-soapui-report";

		runner = new AbstractCucumberCaseRunner(SoapUIPro.SOAPUI_PRO_TITLE
				+ " Cucumber TestCase Runner");
		runner.getLog().debug("Report Output Folder is: " + outputFolder);
		File groovydirfile = new File(groovydir);
		if (!groovydirfile.exists()) {
			runner.getLog()
					.warn("Groovy library default path: "
							+ groovydir
							+ " not found in system ,this maybe caused unexpected error.");
		}
		runner.getLog().info("Groovy library default path is: " + groovydir);

		File extfile = new File(soapuiext);
		if (!extfile.exists()) {
			runner.getLog()
					.warn("SOAPUI ext library default path: "
							+ soapuiext
							+ " not found in system ,this maybe caused unexpected error.");
		}
		runner.getLog()
				.info("SOAPUI ext library default path is: " + soapuiext);

		System.setProperty("soapui.scripting.library", groovydir.trim());
		System.setProperty("soapui.ext.libraries", soapuiext.trim());
		// SoapUI.getSettings().setString("Script Library",groovydir);
		String[] optionValues = new String[] {
				"soapui.ext.libraries=" + soapuiext.trim(),
				"soapui.scripting.library=" + groovydir.trim(),
				"soapui.logroot=" + logdir };

		String settingsFile = this.getClass().getClassLoader()
				.getResource("soapui-settings.xml").getPath();
		File foundfile = new File(settingsFile);
		if (!foundfile.exists()) {
			settingsFile = System.getProperty("user.dir") + File.separator
					+ "soapui-settings.xml";
			foundfile = new File(settingsFile);
			if (foundfile.exists()) {
				runner.setSettingsFile(settingsFile);
			}
		} else {
			runner.setSettingsFile(settingsFile);
		}
		// this is for our soapui framework
		System.setProperty("user.dir", soapuiext);

		runner.setProjectFile(projectname);
		runner.setSystemProperties(optionValues);
		runner.setOutputFolder(outputFolder);
		runner.setIgnoreError(true);
		runner.setJUnitReport(true);
		runner.setPrintReport(true);
		runner.setSaveAfterRun(true);
		runner.setOpenReport(true);
		runner.setProjectFile(projectname);

		new SoapUIPro.SoapUIProCore(true, settingsFile, "");

		WsdlProjectPro project = new WsdlProjectPro(projectname);
		project.setScriptLibrary(groovydir);

		return project;
	}

	public void setProjectProperties(String properties[]) {
		runner.setProjectProperties(properties);

	}

	public void runProject(String projectname) {
		WsdlProject project = initProject(projectname);

		JUnitReportCollector junit = new JUnitReportCollector();
		project.addProjectRunListener(junit);

		long startTime = System.nanoTime();
		runner.runProject(project);
		long timeTaken = (System.nanoTime() - startTime) / 1000000L;
		runner.printReport(timeTaken);
		this.exportNewJunitReport(junit, project);

	}

	public void runTestSuite(String projectname, String suitename) {

		WsdlProject project = initProject(projectname);

		JUnitReportCollector junit = new JUnitReportCollector();
		WsdlTestSuite testSuiteByName = project.getTestSuiteByName(suitename);
		testSuiteByName.addTestSuiteRunListener(junit);

		long startTime = System.nanoTime();
		runner.runSuite(testSuiteByName);
		long timeTaken = (System.nanoTime() - startTime) / 1000000L;
		runner.printReport(timeTaken);
		this.exportNewJunitReport(junit, testSuiteByName);

	}

	public void runTestCase(String projectname, String suitename,
			String casename) {
		WsdlProject project = initProject(projectname);

		JUnitReportCollector junit = new JUnitReportCollector();
		WsdlTestSuite testSuiteByName = project.getTestSuiteByName(suitename);
		WsdlTestCase testCaseByName = testSuiteByName
				.getTestCaseByName(casename);
		testCaseByName.addTestRunListener(junit);

		long startTime = System.nanoTime();
		runner.runTestCase(testCaseByName);
		long timeTaken = (System.nanoTime() - startTime) / 1000000L;
		runner.printReport(timeTaken);
		this.exportNewJunitReport(junit, testCaseByName);

	}

	protected void exportNewJunitReport(JUnitReportCollector junit,
			ModelItem model) {
		try {
			junit.saveReports((output == null) ? "" : output);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JUnitReportEngine.createReport(output, junit, model,
				JUnitReportEngine.MULTIPLE_PAGES_REPORT_FORMAT);

	}

	@Test
	public void testmethod() {
		// initRunner();
		// runProject("C:\\Users\\ahu\\Desktop\\SoapUIDemo","DemoWeather-soapui-project");
		// //runProject("C:\\Users\\ahu\\Desktop\\SoapUIDemo","Alter-NetworkPartnerAPIService-soapui-project");
		/*
		 * String settingsFile=groovydir+File.separator+"soapui-settings.xml";
		 * File foundfile=new File(settingsFile); if( !foundfile.exists()){
		 * settingsFile
		 * =System.getProperty("user.dir")+File.separator+"soapui-settings.xml";
		 * foundfile=new File(settingsFile); if(foundfile.exists()){ //core =
		 * new StandaloneSoapUICore(settingfile); } }
		 */

		String projectname = "C:\\Users\\ahu\\Desktop\\SoapUIDemo\\Cucumber-NetworkPartnerAPI-EcashRunner-soapui-project";
		// runProject(projectname);

		projectname = "C:\\Users\\ahu\\Desktop\\SoapUIDemo\\Cucumber-NetworkPartnerAPI-EcashRunner-soapui-project";
		// String
		// projectname="C:\\p4v\\QA\\SOAPUI\\Projects\\GDN\\SwipeReload\\NetworkPartnerAPIService_SR";
		// String outputFolder = new
		// File("").getAbsolutePath()+File.separator+"test-result"+File.separator+"cucumber-soapui-report";
		// outputFolder = outputFolder

		// String testSuite="TS01_AuthCommit_Request";
		// String testCase="Auth TestCase";
		TestCaseRunner runner2 = new TestCaseRunner();
		runner2.runProject(projectname);
		// mytest(projectname);
		// SoapUIProTestCaseRunner tcase=new SoapUIProTestCaseRunner();
		// SoapUIRunner runner=new SoapUIRunner();
		// runner.runProject(projectname);
		// runner.runTestCase(projectname, testSuite,testCase);

	}

}
