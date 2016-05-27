package com.sample.runner;




import org.junit.Test;


import com.framework.utilities.BaseDriver;


import cucumber.api.CucumberOptions;




@CucumberOptions(
		dryRun=false,
		strict=true,
		monochrome=true,
		features={"src/test/resources/samples"},
		glue={"com.sample.steps"},
		plugin={"html:test-result/cucumber-html-report",
				"json:test-result/cucumber-json-report/cucumber.json",
				"junit:test-result/cucumber-junit-report/cucumber.xml",
				"rerun:test-result/cucumber-failed-report/failed,rerun.txt"}
				)
public class SampleRunner extends BaseDriver{
	
	@Test
	public void runTests() {
	 //  BaseDriver.driver;
	    run(SampleRunner.class);  
		//System.out.println("testing is now");
	    

	  
	}
	
	
	
}
