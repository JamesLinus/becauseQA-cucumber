package com.framework.testcase.testrail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.net.ssl.SSLHandshakeException;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;

import com.framework.testcase.TestCaseTool;

public class TestRailImpl implements TestCaseTool {

	public static TestRailImpl instance = null;
	public static TestRailAPI api = null;
	// this is global configs
	public Map<String, Integer> configs = new HashMap<String, Integer>();

	private static Logger log = Logger.getLogger(TestRailImpl.class);

	public static TestRailImpl getInstance() {
		if (instance == null) {
			instance = new TestRailImpl();
		}
		return instance;

	}

	public boolean isTestRail(String uri) {
		APIClient.ignoreCert();

		// PluginUtils.log(true, "Test Rail request url is: "+url);
		// Create the connection object and set the required HTTP method
		// (GET/POST) and headers (content type and basic auth).
		HttpURLConnection conn;
		try {
			URL url = new URL(uri);
			conn = (HttpURLConnection) url.openConnection();
			int status = 0;
			try {
				status = conn.getResponseCode();
				// PluginUtils.log(true, "Request return code is: "+status);
			} catch (SocketTimeoutException e) {
				e.printStackTrace();
				// PluginUtils.log(true,"the request is timeout from the server ,we quite the test");

			} catch (SSLHandshakeException ex) {
				ex.printStackTrace();
				// PluginUtils.log(true,"the request is  Remote host closed connection during handshake");
			}

			InputStream istream;
			if (status != 200) {
				istream = conn.getErrorStream();
				if (istream == null) {
					// PluginUtils.log(true,"TestRail API return HTTP " + status
					// +
					// " (No additional error message received)");

					new Exception("TestRail API return HTTP " + status
							+ " (No additional error message received)");

				}
			} else {
				istream = conn.getInputStream();
			}

			// Read the response body, if any, and deserialize it from JSON.
			String text = "";
			if (istream != null) {
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(istream, "UTF-8"));

				String line;
				while ((line = reader.readLine()) != null) {
					text += line;
					text += System.getProperty("line.separator");
				}

				reader.close();
			}

			if (text.contains("TestRail") || text.contains("testrail")) {
				return true;
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	public boolean initTestRail(String url, String user, String password,
			String project) {
		boolean isok = false;
		try {
			api = new TestRailAPI(url, user, password);
			// PluginUtils.log(true,
			// "Connect TestRail, Server URL is:"+url+",project name is:"+project+",username is:"+user);
			boolean findpro = api.getProjectByName(project);

			boolean findmilestone = api.getLatestMilestone();
			// get the testrail user
			api.getUserID();
			System.out.println(api.milestoneid);
			if (findpro && findmilestone) {
				// PluginUtils.log(true,
				// "Connect TestRail,configured TestRail successfully !You can take to use the TestRail feature now.");
				isok = true;
			}
		} catch (Exception e) {
			// PluginUtils.log(true,
			// "Connect TestRail,configured TestRail met unexpected error:\n"+e.getMessage());
			// PluginUtils.log(true,
			// "From my perspective ,Sometimes TestRail server is shutting down unexpectedly,If you server configuration is ok, please retry it later..");
		}
		return isok;
	}

	public boolean initTestRail(String projectname) {
		boolean isok = false;
		try {
			api = new TestRailAPI();
			// PluginUtils.log(true,
			// "Connect TestRail, Server URL is:"+url+",project name is:"+project+",username is:"+user);
			boolean findpro = api.getProjectByName(projectname);

			boolean findmilestone = api.getLatestMilestone();
			// get the testrail user
			api.getUserID();
			// System.out.println(api.milestoneid);
			if (findpro && findmilestone) {
				// PluginUtils.log(true,
				// "Connect TestRail,configured TestRail successfully !You can take to use the TestRail feature now.");
				isok = true;
			}
		} catch (Exception e) {
			// PluginUtils.log(true,
			// "Connect TestRail,configured TestRail met unexpected error:\n"+e.getMessage());
			// PluginUtils.log(true,
			// "From my perspective ,Sometimes TestRail server is shutting down unexpectedly,If you server configuration is ok, please retry it later..");
		}

		configs.put("qa3", Definations.CONFIG_ENV_QA3);
		configs.put("qa4", Definations.CONFIG_ENV_QA4);
		configs.put("qa5", Definations.CONFIG_ENV_QA5);
		configs.put("production", Definations.CONFIG_ENV_PRODUCTION);
		configs.put("ie", Definations.CONFIG_BROWSER_IE);
		configs.put("chrome", Definations.CONFIG_BROWSER_CHROME);
		configs.put("firefox", Definations.CONFIG_BROWSER_FIREFOX);
		configs.put("safari", Definations.CONFIG_BROWSER_SAFARI);

		return isok;
	}

	public boolean runJenkinsJobNotCreated(String projectname, String planname) {
		boolean findplan = false;
		api = new TestRailAPI();
		try {
			api.getProjectByName(projectname);
			findplan = api.getTestPlan(planname);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return findplan;

	}

	public boolean runjenkinsJob(String testrailproject, String jenkinsproject,
			String testplanName, String buildnumber, String milestone,
			boolean closeoldplans, boolean generatereport) {

		boolean initfine = false;

		api = new TestRailAPI();

		try {

			boolean projectByName = api.getProjectByName(testrailproject);

			if (projectByName) {
				api.getUserID();
				// If milestone is not specified, then use milestone defined
				// from our automation database
				if (milestone.equalsIgnoreCase("DEFAULT")) {
					findDefaultMilestone();
				} else {
					api.getMilestone(milestone);
				}
				// 20150701 Alter: for upstream issue change
				if (testplanName.equalsIgnoreCase("DEFAULT")) {
					testplanName = jenkinsproject + "-Automation-"
							+ buildnumber;
				} else {
					testplanName = testplanName + "-" + buildnumber;
					// testplanName = testplanName;
				}
				// Check whether to archive/close old test rail plans
				if (closeoldplans) {
					String prefix = testplanName.substring(0,
							testplanName.lastIndexOf('-'));
					// testRailAPIV2.closeTestPlans(project, prefix);
					api.closeTestPlan(prefix);
				}

				// Check whether to generate test rail plan or not
				if (generatereport) {
					boolean testPlan = api.getTestPlan(testplanName);
					if (!testPlan) {
						api.createTestPlan(testplanName);
					}

				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// init the environment settings
		configs.put("qa3", Definations.CONFIG_ENV_QA3);
		configs.put("qa4", Definations.CONFIG_ENV_QA4);
		configs.put("qa5", Definations.CONFIG_ENV_QA5);
		configs.put("production", Definations.CONFIG_ENV_PRODUCTION);
		configs.put("ie", Definations.CONFIG_BROWSER_IE);
		configs.put("chrome", Definations.CONFIG_BROWSER_CHROME);
		configs.put("firefox", Definations.CONFIG_BROWSER_FIREFOX);
		configs.put("safari", Definations.CONFIG_BROWSER_SAFARI);

		return initfine;
	}

	/**
	 * @Title: addTestCaseResult
	 * @Description: TODO
	 * @author ahu@greendotcorp.com
	 * @param @param caselist
	 * @param @param env
	 * @param @param browser
	 * @param @param starttime
	 * @param @param status
	 * @param @param version
	 * @param @param rundescription
	 * @return void return type
	 * @throws
	 */

	public void addTestCaseResult(List<Long> caselist, String env,
			String browser, long starttime, int status, String version,
			String rundescription) {
		// verify if the test run is in the test plan

		long casesuiteid = 0l;
		try {
			// api.getTestPlan(62001);
			// api.getTestPlan("AddCash_Cucumber_SmokeTesting");
			casesuiteid = api.getSuiteidByCase(caselist.get(0));
			boolean planEntry = api.getPlanEntry(casesuiteid);

			// create the test plan entry
			if (!planEntry) {
				List<Integer> envs = new ArrayList<Integer>();
				envs.add(Integer.valueOf(configs.get(env.toLowerCase())));
				envs.add(Integer.valueOf(configs.get(browser.toLowerCase())));
				api.addPlanEntry(casesuiteid, envs);
			}

			api.updatePlanEntryForCase(casesuiteid, caselist);

			// add result for case
			api.addTestResultForTestRun(starttime, api.runid,
					caselist.get(caselist.size() - 1), status, version,
					rundescription);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @Title: addTestCaseResult
	 * @Description: TODO
	 * @author ahu@greendotcorp.com
	 * @param @param caselist
	 * @param @param env
	 * @param @param browser
	 * @param @param starttime
	 * @param @param status
	 * @param @param version
	 * @param @param rundescription
	 * @return void return type
	 * @throws
	 */

	public void addTestCaseResultwithUpstreamProject(List<Long> caselist,
			String jenkinsjob, String env, String browser, String planname,
			long starttime, int status, String version, String rundescription) {
		// verify if the test run is in the test plan

		String name = jenkinsjob + "[Environment: " + env + ", Browser: "
				+ browser + "]";
		long casesuiteid = 0l;
		try {
			// api.getTestPlan(62001);
			// api.getTestPlan("AddCash_Cucumber_SmokeTesting");
			casesuiteid = api.getSuiteidByCase(caselist.get(0));
			boolean planEntry = api.getPlanEntryName(name);
			Long lastcaseid = caselist.get(caselist.size() - 1);
			log.info("[Test Result Publish] Case ID[" + lastcaseid
					+ "] Check Test PlanEntry : " + name
					+ " existing in Test Plan: " + planname
					+ ",return value is: " + planEntry);
			// create the test plan entry
			if (!planEntry) {
				List<Integer> envs = new ArrayList<Integer>();
				envs.add(Integer.valueOf(configs.get(env.toLowerCase())));
				envs.add(Integer.valueOf(configs.get(browser.toLowerCase())));
				// api.addPlanEntry(casesuiteid,envs);

				api.addPlanEntryforName(casesuiteid, name, envs);
				log.info("[Test Result Publish] Case ID[" + lastcaseid
						+ "] Create a new Test PlanEntry: " + name
						+ " in Test Plan: " + planname);
			}

			api.updatePlanEntryForCase(casesuiteid, caselist);
			log.info("[Test Result Publish] Case ID[" + lastcaseid
					+ "] Add a new Test Case Suite: " + casesuiteid
					+ ",Case IDs:" + caselist.toString()
					+ " in Test PlanEntry:" + name + "!");
			// add result for case
			api.addTestResultForTestRun(starttime, api.runid,
					caselist.get(caselist.size() - 1), status, version,
					rundescription);
			log.info("[Test Result Publish] Case ID[" + lastcaseid
					+ "] Finished Update Test Result in planEntry: " + name
					+ " ,Case ID:" + caselist.toString() + "!");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String findDefaultMilestone() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT-08:00"));
		String date = simpleDateFormat.format(new Date());
		ReleaseSchedule releaseSchedule = TestRailDB.getReleaseByDate(date);
		String mileStoneName = releaseSchedule.getReleaseName();
		/*
		 * Long dueOn = releaseSchedule.getReleaseDate().getTime()/1000;
		 * HashMap<String, Object> mileStoneParameters = new HashMap<String,
		 * Object>(); mileStoneParameters.put("due_on", dueOn);
		 */
		try {
			boolean milestone = api.getMilestone(mileStoneName);
			if (milestone) {
				return mileStoneName;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mileStoneName;

	}

	public boolean updateTestCase(long caseid, String given, String when,
			String then) throws MalformedURLException, IOException {
		HashMap<String, String> statemap = new HashMap<String, String>();

		statemap.put(Definations.CASE_CUCUMBER_GIVEN_FIELD, given);
		statemap.put(Definations.CASE_CUCUMBER_WHEN_FIELD, when);
		statemap.put(Definations.CASE_CUCUMBER_THEN_FIELD, then);
		boolean updated = false;
		updated = api.updateTestCase(null, Definations.CASE_TYPE_AUTOMATION,
				null, Definations.CASE_PRIORITY_MUST_TEST, caseid, null,
				statemap);
		return updated;
	}

	public boolean updateTestCaseNew(long caseid, String cucumber)
			throws MalformedURLException, IOException {
		HashMap<String, String> cucumbermap = new HashMap<String, String>();

		cucumbermap.put(Definations.CASE_CUCUMBER_FIELD, cucumber);
		boolean updated = false;
		updated = api.updateTestCase(null, Definations.CASE_TYPE_AUTOMATION,
				null, Definations.CASE_PRIORITY_MUST_TEST, caseid, null,
				cucumbermap);
		return updated;
	}

	public void creatNewTestCase(String suitename, String sectionname,
			String storynumber, String title, String cucumber)
			throws MalformedURLException, IOException {
		HashMap<String, String> cucumbermap = new HashMap<String, String>();

		cucumbermap.put(Definations.CASE_CUCUMBER_FIELD, cucumber);
		boolean findTestSuite = findTestSuite(suitename);
		boolean findTestSection = findTestSection(sectionname);

		if (!findTestSuite) {
			api.createTestSuite(suitename,
					"Create Test Suite from Cucumber Runner");
		}
		if (!findTestSection) {
			api.createRecusionTestSection(sectionname);
		}
		long caseid = findTestCase(title);
		if (caseid == 0) {
			// create the test case
			// 1 means automated
			// 4 means must test
			api.createTestCase(title, Definations.CASE_TYPE_AUTOMATION, null,
					Definations.CASE_PRIORITY_MUST_TEST, storynumber,
					cucumbermap);
		}

	}

	public void creatNewTestCaseFromSectionID(long sectionid,
			String storynumber, String title, String cucumber)
			throws MalformedURLException, IOException {
		HashMap<String, String> cucumbermap = new HashMap<String, String>();

		cucumbermap.put(Definations.CASE_CUCUMBER_FIELD, cucumber);
		api.setSectionid(sectionid);
		long caseid = findTestCase(title);
		if (caseid == 0) {
			// create the test case
			// 1 means automated
			// 4 means must test
			api.createTestCase(title, Definations.CASE_TYPE_AUTOMATION,
					Definations.CASE_ESTIMATE,
					Definations.CASE_PRIORITY_MUST_TEST, storynumber,
					cucumbermap);
		}

	}

	public String getSectionCases(long testsuite, long sectionid)
			throws MalformedURLException, IOException {
		api.setSuiteid(testsuite);
		String tempcontent = "#Below are the Cucumber scripts checking out from Test Rail\n";

		JSONArray cases = api.getCaseFieldsList(sectionid);
		if (cases != null) {

			for (int caseindex = 0; caseindex < cases.size(); caseindex++) {
				JSONObject currentcase = (JSONObject) cases.get(caseindex);

				long id = (Long) currentcase.get("id");
				String title = (String) currentcase.get("title");
				String cucumber = (String) currentcase
						.get(Definations.CASE_CUCUMBER_FIELD);
				if (cucumber != null) {
					if (!cucumber.startsWith("\t\t")) {
						cucumber = "\t\t" + cucumber;
					}

				}
				if (cucumber != null && cucumber != "") {
					cucumber = cucumber.replaceAll("\r\n", "\r\n\t\t");
					tempcontent = tempcontent + "\t" + "@" + id + "\n" + "\t"
							+ "Scenario: " + title + "\n" + cucumber + "\n";
				} else {
					tempcontent = tempcontent
							+ "\t"
							+ "#No Cucumber step found for current test case,skip it"
							+ "\n" + "\t" + "#@" + id + "\n" + "\t"
							+ "#Scenario: " + title + "\n";
				}

			}

		}

		return tempcontent;
	}

	public boolean findTestSuite(String suitename) {
		boolean testSuite = false;
		try {
			testSuite = api.getTestSuite(suitename);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return testSuite;
	}

	public boolean findTestSection(String sectioname) {
		boolean testsection = false;
		try {
			testsection = api.getDeepTestSection(sectioname);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return testsection;
	}

	public long findTestCase(String casename) {
		long testcaseid = 0;
		try {
			testcaseid = api.getTestCase(casename, getSectionId());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return testcaseid;
	}

	public long getSuiteID() {
		return api.suiteid;
	}

	public long getSectionId() {
		return api.sectionid;
	}

	public boolean getPlanByName(String planname) {
		try {
			boolean findplan = api.getTestPlan(planname);
			return findplan;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean findProjectByName(String projectname) {
		try {
			boolean findpro = api.getProjectByName(projectname);
			return findpro;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean findTestPlan(String planname) {
		try {
			boolean findpro = api.getTestPlan(planname);
			return findpro;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean findMilestone(String milestone) {
		try {
			boolean findpro = api.getMilestone(milestone);
			return findpro;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public int parseResultStatus(String status) {
		boolean ispassed = status.trim().equalsIgnoreCase("pass")
				|| status.trim().equalsIgnoreCase("passed");
		boolean isfailed = status.trim().equalsIgnoreCase("fail")
				|| status.trim().equalsIgnoreCase("failed");
		boolean isskipped = status.trim().equalsIgnoreCase("skip")
				|| status.trim().equalsIgnoreCase("skipped");

		// all these value is based on your TestRail environment.
		if (ispassed) {
			return Definations.PASSED;
		} else if (isfailed) {
			return Definations.FAILED;
		} else if (isskipped) {
			return Definations.SKIPPED;
		} else {
			return Definations.PASSED;
		}
	}

	@Before
	public void setupTestRail() {
		boolean initTestRail = initTestRail("Green Dot Network");
		log.info("init the testrail instance return code is: " + initTestRail);
	}

	@Test
	public void testMethod() {
		try {
			// api.getTestPlan("Development-GDN-Web-Sanity-Check-Daily-Automation-11");
			// boolean planEntryName =
			// api.getPlanEntryName("R66 - Sprint 1 - Mustang");
			api.closeTestPlan("Development-GDN-Web-Sanity-Check-Daily-Automation");
			// System.out.println("return code is; "+planEntryName);

			/*
			 * List<Long> caselist=Lists.newArrayList(); caselist.add(1980602l);
			 * long starttime=System.currentTimeMillis();
			 * addTestCaseResultwithUpstreamProject(caselist,
			 * "Jenkins Job xpressbet", "QA4", "IE","R66 - Sprint 1 - Mustang",
			 * starttime, 7, "233", "testeee");
			 */
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
