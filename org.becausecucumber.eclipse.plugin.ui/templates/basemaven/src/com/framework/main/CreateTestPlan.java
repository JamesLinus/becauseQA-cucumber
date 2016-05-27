package com.framework.main;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.framework.testcase.testrail.TestRailImpl;
import com.framework.utilities.PropertiesUtils;

public class CreateTestPlan {

	public static Logger log = Logger.getLogger(CreateTestPlan.class);
	private static TestRailImpl testrailinstance = null;
	private static boolean testRail = false;

	public static String projectname;
	public static String hostname;
	public static String buildnumber;
	public static String buildurl;
	public static String workspace;

	public static String environment;
	public static String browser;

	public static String project;
	public static String testplan;
	public static String milestone;
	public static boolean closeplans;

	public static boolean generatereport;
	public static String email;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		workspace = PropertiesUtils.getEnv("WORKSPACE");
		projectname = PropertiesUtils.getEnv("JOB_NAME");
		hostname = PropertiesUtils.getEnv("NODE_NAME");
		buildnumber = PropertiesUtils.getEnv("BUILD_NUMBER");
		buildurl = PropertiesUtils.getEnv("BUILD_URL");

		environment = PropertiesUtils.getEnv("ENVIRONMENT");
		browser = PropertiesUtils.getEnv("BROWSER_TYPE");

		project = PropertiesUtils.getEnv("RUN_PROJECT_NAME");
		testplan = PropertiesUtils.getEnv("TESTPLAN_NAME");
		milestone = PropertiesUtils.getEnv("MILESTONE_NAME");
		closeplans = Boolean.parseBoolean(PropertiesUtils
				.getEnv("ARCHIVE_PREVIOUS_TESTRUNS"));
		generatereport = Boolean.parseBoolean(PropertiesUtils
				.getEnv("GENERATE_REPORT"));
		email = PropertiesUtils.getEnv("SUBSCRIBLE_EMAIL");

		// caselist = new ArrayList<Long>();

		if (generatereport) {
			testrailinstance = TestRailImpl.getInstance();
			testRail = true;
			if (testRail) {
				log.info("Begin to init the TestRail instance,create test plan or delete old plans.");
				testrailinstance.runjenkinsJob(project, projectname, testplan,
						buildnumber, milestone, closeplans, generatereport);
			}

		}

		// CONFIG the soapui project environment
		log.info("Begin to init the soapui environment,job workspace is: "
				+ workspace);
		try {

			if (workspace != null) {
				Properties props = new Properties();
				File file = new File(workspace + File.separator + "Groovy"
						+ File.separator + "Environment.properties");
				log.info("Environment file path is: " + file.getAbsolutePath());
				if (file.exists()) {
					log.info("Delete Environmentfile: "
							+ file.getAbsolutePath());
					file.delete();
					file.createNewFile();
				}
				props.setProperty("TESTINGENVIRONMENT", environment);
				props.store(
						new FileOutputStream(file),
						"Generated propeties file using BaseCucumber  pom.xml.\nNote: It is important to give correct value-pair to avoid compilation errors.\nValid name values: DEVINT1, DEVINT2, QA3, QA4, QA5");
				log.info("Create a new properties file: "
						+ file.getAbsolutePath());
			}
		} catch (Exception e) {

		}
	}
}
