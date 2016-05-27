package com.framework.utilities;

import gherkin.formatter.Formatter;
import gherkin.formatter.Reporter;
import gherkin.formatter.model.Background;
import gherkin.formatter.model.Examples;
import gherkin.formatter.model.Feature;
import gherkin.formatter.model.Match;
import gherkin.formatter.model.Result;
import gherkin.formatter.model.Scenario;
import gherkin.formatter.model.ScenarioOutline;
import gherkin.formatter.model.Step;
import gherkin.formatter.model.Tag;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.framework.testcase.testrail.TestRailImpl;
import com.google.common.collect.Lists;

import cucumber.api.StepDefinitionReporter;
import cucumber.runtime.ClassFinder;
import cucumber.runtime.CucumberException;
import cucumber.runtime.RuntimeGlue;
import cucumber.runtime.RuntimeOptions;
import cucumber.runtime.RuntimeOptionsFactory;
import cucumber.runtime.UndefinedStepsTracker;
import cucumber.runtime.io.MultiLoader;
import cucumber.runtime.io.ResourceLoader;
import cucumber.runtime.io.ResourceLoaderClassFinder;
import cucumber.runtime.model.CucumberFeature;
import cucumber.runtime.xstream.LocalizedXStreams;

public class CucumberReporter implements Formatter, Reporter {

	protected static final Logger log = Logger.getLogger(BaseSteps.class);

	public static WebDriver driver = BaseDriver.driver;

	public static AtomicInteger failureCount;
	public static AtomicInteger skipCount;
	public static AtomicInteger passCount;

	public static AtomicInteger sfailureCount;
	public static AtomicInteger sskipCount;
	public static AtomicInteger spassCount;

	private LinkedList<Step> steps = new LinkedList<Step>();

	private String currentscenariostatus = "passed";

	// private String runstarttime;

	private long starttime = 0;

	// testrail tool
	private static TestRailImpl testrailinstance = null;
	private boolean testRail = false;
	// private long testrailrun = 0;
	private String message = null;

	public List<Long> caselist = null;

	// jenkins
	public static String projectname;
	public static String hostname;
	public static String buildnumber;
	public static String buildurl;
	private static String workspace;

	public static String environment;
	public static String browser;

	public static String project;
	public static String testplan;
	public static String milestone;
	public static boolean closeplans;

	public static boolean generatereport;
	public static String email;
	
	

	// public static Scenario scenario;

	public CucumberReporter() {
	}

	public boolean initTestCaseTool() {

		failureCount = new AtomicInteger(0);
		skipCount = new AtomicInteger(0);
		passCount = new AtomicInteger(0);

		sfailureCount = new AtomicInteger(0);
		sskipCount = new AtomicInteger(0);
		spassCount = new AtomicInteger(0);

		projectname = PropertiesUtils.getEnv("JOB_NAME");
		hostname = PropertiesUtils.getEnv("NODE_NAME");
		buildnumber = PropertiesUtils.getEnv("BUILD_NUMBER");
		buildurl = PropertiesUtils.getEnv("BUILD_URL");
		workspace = PropertiesUtils.getEnv("JOB_URL");

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

		caselist = new ArrayList<Long>();

		if (generatereport) {
			testrailinstance = TestRailImpl.getInstance();
			testRail = true;
			if (testRail) {
				// log.info("Begin to init the TestRail instance,create test plan or delete old plans.");
				// testrailinstance.runjenkinsJob(project, projectname,
				// testplan,
				// buildnumber, milestone, closeplans, generatereport);
				testrailinstance.initTestRail(project);
				boolean planByName = testrailinstance.getPlanByName(testplan);

				log.info("Begin to init the TestRail instance,create test plan or delete old plans."
						+ ",Project name:"
						+ project
						+ ",Jenkins Job Name is: "
						+ projectname
						+ ",Test Plan name is: "
						+ testplan
						+ ",build number is: "
						+ buildnumber
						+ "milestone is: "
						+ milestone + closeplans + generatereport);

				// boolean planByName =
				// testrailinstance.runJenkinsJobNotCreated(project, testplan);
				if (!planByName) {
					log.info("Not find the test plan name:" + testplan
							+ ",from testrail ,exit test!");
					return false;
				}
				log.info("Execution will be run in Test Plan: " + testplan
						+ ",Browser Type is: " + browser + "\n ");
			}

		}
		return true;

	}

	@Override
	public void result(Result result) {
		// TODO Auto-generated method stub
		// System.out.println("result method called: "+result.getStatus()+",result :"+result.getDuration());
		// setStarttime(result.getDuration());

		if (Result.FAILED.equals(result.getStatus())) {

			failureCount.incrementAndGet();
			currentscenariostatus = "failed";
			message = result.getErrorMessage();
			byte[] screenshotAs = ((TakesScreenshot) driver)
					.getScreenshotAs(OutputType.BYTES);
			this.embedding("image/png", screenshotAs);
		} else if (Result.SKIPPED.equals(result)) {

			skipCount.incrementAndGet();
			currentscenariostatus = "skipped";
		} else if (Result.UNDEFINED.equals(result)) {

			failureCount.incrementAndGet();
			currentscenariostatus = "failed";
			message = result.getErrorMessage();
			byte[] screenshotAs = ((TakesScreenshot) driver)
					.getScreenshotAs(OutputType.BYTES);
			this.embedding("image/png", screenshotAs);
		} else {
			passCount.incrementAndGet();
			currentscenariostatus = "passed";
		}

	}

	@Override
	public void endOfScenarioLifeCycle(Scenario scenario) {
		// TODO Auto-generated method stub
		if (currentscenariostatus.equalsIgnoreCase("passed")) {
			spassCount.incrementAndGet();
		} else if (currentscenariostatus.equalsIgnoreCase("failed")) {
			sfailureCount.incrementAndGet();
		} else if (currentscenariostatus.equalsIgnoreCase("skipped")) {
			sskipCount.incrementAndGet();
		}
		log.info("Scenario Result: "+currentscenariostatus);
		log.info("[Test Result Publish] Whether or not to Upload the Scenario result into TestRail: "
				+ generatereport);
		// get the scenario tag and upload the test result
		if (generatereport) {

			// /List<Tag> tags = scenario.getTags();
			List<Tag> alltags = scenario.getTags();
			for (Tag tag : alltags) {
				String tagname = tag.getName().substring(1);
				long caseid = 0;
				if (testRail) {
					caseid = Long.parseLong(tagname);
					caselist.add(caseid);

					int runstatus = testrailinstance
							.parseResultStatus(currentscenariostatus);
					if (message == null) {
						String note = "This test has been marked as '"
								+ currentscenariostatus + "'.\n\nNote:\n"
								+ "Jenkins Job build Url: " + buildurl
								+ ",\nJenkins Job Name: " + projectname
								+ ",\nBrowser Type is: " + browser
								+ ",\nEnvironment is: " + environment
								+ ",\nRun Host in: " + hostname;

						message = note;
					}

					log.info("[Test Result Publish] Case ID["
							+ caseid
							+ "] Begin to Update Cucumber Scenario TestCase into TestRail ,Cucumber Scenario Tags List(Test Case IDs):"
							+ caselist.toString() + ",Execution Result: "
							+ currentscenariostatus);

					// find the suite id from the test case
					// 20150701 Alter: support for the upstream change
					/*
					 * testrailinstance.addTestCaseResult(caselist,
					 * environment.toLowerCase(), browser, starttime, runstatus,
					 * buildnumber, message);
					 */
					testrailinstance.addTestCaseResultwithUpstreamProject(
							caselist, projectname.trim(),
							environment.toUpperCase(), browser, testplan,
							getStarttime(), runstatus, buildnumber, message);

				}
			}

		}

	}

	/**
	 * @Title: cucumberRunner
	 * @Description: TODO
	 * @author ahu@greendotcorp.com
	 * @param @param classzz
	 * @param @param reporter
	 * @return void return type
	 * @throws
	 */

	@SuppressWarnings("rawtypes")
	public void cucumberRunner(Class classzz, CucumberReporter reporter) {

		ClassLoader classLoader = classzz.getClassLoader();
		ResourceLoader resourceLoader = new MultiLoader(classLoader);

		RuntimeOptionsFactory runtimeOptionsFactory = new RuntimeOptionsFactory(
				classzz);

		RuntimeOptions runtimeOptions = runtimeOptionsFactory.create();

		// remove duplicates from glue path.
		List<String> uniqueGlue = new ArrayList<String>(new HashSet<String>(
				runtimeOptions.getGlue()));
		runtimeOptions.getGlue().clear();
		runtimeOptions.getGlue().addAll(uniqueGlue);

		runtimeOptions.addPlugin(reporter);

		ClassFinder classFinder = new ResourceLoaderClassFinder(resourceLoader,
				classLoader);
		cucumber.runtime.Runtime runtime = new cucumber.runtime.Runtime(
				resourceLoader, classFinder, classLoader, runtimeOptions);

		Formatter formatter = runtimeOptions.formatter(classLoader);
		Reporter foundreport = runtimeOptions.reporter(classLoader);
		RuntimeGlue glue = new RuntimeGlue(new UndefinedStepsTracker(),
				new LocalizedXStreams(classLoader));

		StepDefinitionReporter stepDefinitionReporter = runtimeOptions
				.stepDefinitionReporter(classLoader);

		glue.reportStepDefinitions(stepDefinitionReporter);

		List<CucumberFeature> cucumberFeatures = null;
		String featurepath = PropertiesUtils.getEnv("FEATURE_PATH").trim();
		String classname = classzz.getName().toLowerCase();
		if (featurepath == null || featurepath.equals("")
				|| classname.contains("test") || classname.contains("draft")) {
			cucumberFeatures = runtimeOptions.cucumberFeatures(resourceLoader);
		} else {
			// List<Object> filters = new ArrayList<Object>();
			List<String> featurePaths = Lists.newArrayList();
			featurePaths.add(featurepath);
			List<Object> filters = runtimeOptions.getFilters();
			cucumberFeatures = CucumberFeature.load(resourceLoader,
					featurePaths, filters);
		}
		log.info("Cucumber features Run List:");
		log.info("----------------------------------------------------------------------------");
		for (CucumberFeature cucumberFeature : cucumberFeatures) {
			log.info("" + cucumberFeature.getPath());
		}
		log.info("----------------------------------------------------------------------------");
		// List<FeatureRunner> children = new ArrayList<FeatureRunner>();
		for (CucumberFeature cucumberFeature : cucumberFeatures) {
			cucumberFeature.run(formatter, foundreport, runtime);

		}
		formatter.done();
		formatter.close();
		runtime.printSummary();
		if (!runtime.getErrors().isEmpty()) {
			Throwable errorcode = runtime.getErrors().get(0);
			// if(!errorcode.getLocalizedMessage().contains("AssertionError")){
			throw new CucumberException(errorcode);
			// }

		} else if (runtime.exitStatus() != 0x00) {
			throw new CucumberException("There are pending or undefined steps.");
		}
		

	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
	}

	@Override
	public void background(Background background) {
		// TODO Auto-generated method stub
		// System.out.println("background method called: "+background.getName()+",background description is: "+background.getDescription());
	}

	@Override
	public void scenario(Scenario scenario) {
		// TODO Auto-generated method stub
		// CucumberReporter.scenario=scenario;

		// currentscenarioname=scenario.getName();
		// scenariosCount.incrementAndGet();
		// System.out.println("scenario method called: "+scenario.getName()+",scenario description is: "+scenario.getDescription());
	}

	@Override
	public void step(Step step) {
		// TODO Auto-generated method stub
		// System.out.println("step method called: "+step.getName()+",step line is: "+step.getLine());
		log.info(step.getKeyword() + step.getName());
		steps.add(step);

	}

	@Override
	public void done() {
		// TODO Auto-generated method stub
		steps.clear();
	}

	@Override
	public void eof() {
		// TODO Auto-generated method stub

	}

	@Override
	public void after(Match match, Result result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void match(Match match) {
		// TODO Auto-generated method stub

	}

	@Override
	public void embedding(String mimeType, byte[] data) {
		// TODO Auto-generated method stub

	}

	@Override
	public void write(String text) {
		// TODO Auto-generated method stub

	}

	@Override
	public void syntaxError(String state, String event,
			List<String> legalEvents, String uri, Integer line) {
		// TODO Auto-generated method stub

	}

	@Override
	public void uri(String uri) {
		// TODO Auto-generated method stub

	}

	@Override
	public void feature(Feature feature) {
		// TODO Auto-generated method stub
		log.info("Feature: " + feature.getName() + ""
				+ feature.getDescription());
	}

	@Override
	public void scenarioOutline(ScenarioOutline scenarioOutline) {
		// TODO Auto-generated method stub

	}

	@Override
	public void examples(Examples examples) {
		// TODO Auto-generated method stub

	}

	@Override
	public void startOfScenarioLifeCycle(Scenario scenario) {
		// TODO Auto-generated method stub
		log.info("\n----------------------------------------------------------------------------");
		log.info("Scenario: " + scenario.getName() + ""
				+ scenario.getDescription());
		setStarttime(System.currentTimeMillis());
	}

	@Override
	public void before(Match match, Result result) {
		// TODO Auto-generated method stub
		// System.out.println("cucumber before method invoked");

	}

	public AtomicInteger getFailureCount() {
		return failureCount;
	}

	public AtomicInteger getSkipCount() {
		return skipCount;
	}

	public AtomicInteger getPassCount() {
		return passCount;
	}

	public AtomicInteger getSfailureCount() {
		return sfailureCount;
	}

	public AtomicInteger getSskipCount() {
		return sskipCount;
	}

	public AtomicInteger getSpassCount() {
		return spassCount;
	}

	public String getProjectname() {
		return projectname;
	}

	public String getHostname() {
		return hostname;
	}

	public String getBuildnumber() {
		return buildnumber;
	}

	public String getBuildurl() {
		return buildurl;
	}

	public String getEnvironment() {
		return environment;
	}

	public String getBrowser() {
		return browser;
	}

	public String getProject() {
		return project;
	}

	public String getTestplan() {
		return testplan;
	}

	public String getMilestone() {
		return milestone;
	}

	public boolean isCloseplans() {
		return closeplans;
	}

	public boolean isGeneratereport() {
		return generatereport;
	}

	public String getEmail() {
		return email;
	}

	public long getStarttime() {
		return starttime;
	}

	public void setStarttime(long starttime) {
		this.starttime = starttime;
	}

	public String getWorkspace() {
		return workspace;
	}

}
