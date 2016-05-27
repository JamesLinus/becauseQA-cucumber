package com.framework.soapui;

import java.io.File;

import com.eviware.soapui.SoapUIPro;
import com.framework.utilities.PropertiesUtils;

/**
 * @ClassName: TestCaseRunner
 * @Description: TODO
 * @author ahu@greendotcorp.com
 * @date Jul 21, 2015 6:52:24 PM
 * 
 */

public class TestCaseRunner {

	private String groovydir = PropertiesUtils.getString("soapui.groovy.dir");
	private String soapuiext = PropertiesUtils.getString("soapui.ext.dir");

	private AbstractCucumberCaseRunner runner;

	private String[] globalProperties;
	private String[] projectProperties;

	private String environment;

	protected void init(String projectname) {
		// JUnitReportCollector collector = new JUnitReportCollector();
		String path = new File("").getAbsolutePath();
		String logpath = path + File.separator + "test-logs" + File.separator
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

		// System.setProperty("soapui.scripting.library", groovydir);
		// SoapUI.getSettings().setString("Script Library",groovydir);
		String[] optionValues = new String[] {
				"soapui.ext.libraries=" + soapuiext.trim(),
				"soapui.scripting.library=" + groovydir.trim(),
				"soapui.logroot=" + logpath };

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

		if (this.environment != null) {
			runner.setEnvironment(this.environment);
		}

		runner.setProjectFile(projectname);
		runner.setSystemProperties(optionValues);
		runner.setOutputFolder(outputFolder);
		runner.setPrintReport(true);
		runner.setExportAll(true);
		runner.setJUnitReport(true);
		runner.setEnableUI(false);
		runner.setOpenReport(true);
		runner.setIgnoreError(false);
		runner.setSaveAfterRun(true);

		// runner.initCoverageBuilder();
		if (this.globalProperties != null) {
			runner.setGlobalProperties(this.globalProperties);
		}
		if (this.projectProperties != null) {
			runner.setProjectProperties(this.projectProperties);
		}
		runner.setReportName("SOAPUI Cucumber Runner Report");

		String reportFormat = "PDF, XLS, HTML, RTF,CSV, TXT, XML";
		runner.setReportFormats(reportFormat.split(","));

	}

	public void runProject(String projectname) {
		init(projectname);
		try {
			runner.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getContext() {

	}

	public void runSuite(String projectname, String testSuite) {
		init(projectname);
		runner.setTestSuite(testSuite);
		try {
			runner.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void runTestCase(String projectname, String testSuite,
			String testCase) {
		init(projectname);
		runner.setTestSuite(testSuite);
		runner.setTestCase(testCase);

		try {
			runner.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setGlobalProperties(String[] globalProperties) {
		this.globalProperties = globalProperties;
	}

	public void setProjectProperties(String[] projectProperties) {
		this.projectProperties = projectProperties;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

}
