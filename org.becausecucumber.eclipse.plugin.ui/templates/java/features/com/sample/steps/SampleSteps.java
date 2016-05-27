package com.sample.steps;



import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.framework.utilities.BaseSteps;

import cucumber.api.java.en.Then;


/*
* Class Generate from Cucumber Tool
*/

public class SampleSteps extends BaseSteps{
	
	
	@FindBy(how=How.XPATH,using="//*[@id='p_13838465-p']")
	public WebElement searchbox;
	
	
	
	
	public SampleSteps() {
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
	}


	@Then("^I input the search keyword \"(.*)\"$")
	public void I_input_the_search_keyword(String params1) throws Throwable {
						
		inputValue( searchbox,params1);
		inputKey(searchbox, Keys.ENTER);
		
	}

}