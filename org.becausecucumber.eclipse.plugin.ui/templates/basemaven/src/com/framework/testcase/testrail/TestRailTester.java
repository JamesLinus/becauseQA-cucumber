package com.framework.testcase.testrail;

import java.io.IOException;


public class TestRailTester {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		/*
		 * long caseid=1270579;
		 * 
		 * long caseid2=1270583;
		 * 
		 * String project="Green Dot Network"; String env="qa3"; String
		 * browser="ie"; long starttime=2323; int status=2; String
		 * version="2222"; String rundescription="testing";
		 * 
		 * List<Long> caselist=new ArrayList<Long>(); caselist.add(caseid);
		 * 
		 * TestRailImpl t=new TestRailImpl(); boolean initTestRail =
		 * t.initTestRail(project);
		 * 
		 * 
		 * String jenkinsProjectName = "jenkins job sample"; String planName =
		 * "DEFAULT"; String mileStoneName = "default"; String
		 * generateTestRailRun = "true"; String archivePreviousTestPlans
		 * ="true"; String upstreamBuildNumber = "333";
		 * 
		 * if(initTestRail){ //t.api.getUserID(); // If milestone is not
		 * specified, then use milestone defined from our automation database if
		 * (mileStoneName.equalsIgnoreCase("DEFAULT")) {
		 * t.findDefaultMilestone(); } else { t.api.getMilestone(mileStoneName);
		 * }
		 * 
		 * if (planName.equalsIgnoreCase("DEFAULT")) { planName =
		 * jenkinsProjectName + "-Automation-" + upstreamBuildNumber; } else {
		 * planName = planName + "-" + upstreamBuildNumber; }
		 * 
		 * // Check whether to archive/close old test rail plans if
		 * (archivePreviousTestPlans == null ||
		 * archivePreviousTestPlans.equalsIgnoreCase("") ||
		 * archivePreviousTestPlans.equalsIgnoreCase("true")) { String prefix =
		 * planName.substring(0, planName.lastIndexOf('-'));
		 * //testRailAPIV2.closeTestPlans(project, prefix);
		 * t.api.closeTestPlan(prefix); }
		 * 
		 * // Check whether to generate test rail plan or not if
		 * (generateTestRailRun == null ||
		 * generateTestRailRun.equalsIgnoreCase("") ||
		 * generateTestRailRun.equalsIgnoreCase("true")) { boolean testPlan =
		 * t.api.getTestPlan(planName); if(!testPlan){
		 * t.api.createTestPlan(planName); }
		 * //testRailAPIV2.findOrCreateTestPlan(project, mileStone, planName); }
		 * }
		 */
		/*
		 * t.addTestCaseResult(caselist, env, browser, starttime, status,
		 * version, rundescription);
		 * 
		 * caselist.add(caseid2); t.addTestCaseResult(caselist, env, browser,
		 * starttime, status, version, rundescription);
		 */

		/*long caseid = 1270579;

		long caseid2 = 1270583;

		String project = "Green Dot Network";
		String env = "qa3";
		String browser = "ie";
		long starttime = 2323;
		int status = 2;
		String version = "2222";
		String rundescription = "testing";

		List<Long> caselist = new ArrayList<Long>();
		caselist.add(caseid);

		TestRailImpl t = new TestRailImpl();
		boolean initTestRail = t.initTestRail(project);*/
		//System.out.println(initTestRail);
	}

}
