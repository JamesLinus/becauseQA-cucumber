package com.sample.steps;

import com.framework.utilities.BaseSteps;

import cucumber.api.java.en.Given;

public class InitSteps extends BaseSteps {

	public InitSteps() {
		// TODO Auto-generated constructor stub
	}

	
	 /*
	* This step definition is generated,you just put your implement step code below  
	*/
	@Given("^I visit home site$")
	public void I_visit_google_site() throws Throwable {
	//throw new PendingException();
		visitPage("https://www.yahoo.com/");
	}
}
