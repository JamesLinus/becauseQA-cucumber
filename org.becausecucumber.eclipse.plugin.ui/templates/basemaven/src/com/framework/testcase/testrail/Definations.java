package com.framework.testcase.testrail;

public class Definations {

	// The test result status
	public final static int PASSED = 6;

	public final static int FAILED = 7;

	public final static int SKIPPED = 10;

	public final static int CASE_TYPE_AUTOMATION = 1;
	public final static int CASE_TYPE_FUNTIONALITY = 2;
	public final static int CASE_TYPE_PERFFORMANCE = 3;
	public final static int CASE_TYPE_REGRESSION = 4;
	public final static int CASE_TYPE_USABLITY = 5;
	public final static int CASE_TYPE_OTHER = 6;
	public final static int CASE_TYPE_MANUAL = 7;

	public final static String CASE_ESTIMATE = "0.5h";

	public final static String CASE_CUCUMBER_FIELD = "custom_cucumber";
	public final static String CASE_CUCUMBER_GIVEN_FIELD = "custom_cucumber_given";
	public final static String CASE_CUCUMBER_WHEN_FIELD = "custom_cucumber_when";
	public final static String CASE_CUCUMBER_THEN_FIELD = "custom_cucumber_then";

	public final static int CASE_PRIORITY_DONOT_TEST = 1;
	public final static int CASE_PRIORITY_TEST_IF_TIME = 2;
	public final static int CASE_PRIORITY_SHOULD_TEST = 3;
	public final static int CASE_PRIORITY_MUST_TEST = 4;
	public final static int CASE_PRIORITY_URGEN_TEST = 5;

	public final static int CONFIG_ENV_QA3 = 70;
	public final static int CONFIG_ENV_QA4 = 75;
	public final static int CONFIG_ENV_QA5 = 272;
	public final static int CONFIG_ENV_PRODUCTION = 100;

	public final static int CONFIG_BROWSER_IE = 86;
	public final static int CONFIG_BROWSER_CHROME = 85;
	public final static int CONFIG_BROWSER_FIREFOX = 71;
	public final static int CONFIG_BROWSER_SAFARI = 5;

}
