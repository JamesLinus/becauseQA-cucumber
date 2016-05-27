package com.cucumberpeople.sample;

import org.openqa.selenium.firefox.FirefoxDriver;

/*
 * Created from CucumberPeople Tool
 */
public class Sample {

	public Sample() {
		// TODO Auto-generated constructor stub
		
	}
	/*
	 * Description: This is a selenium demo testing you can run it to see the selenium feature,
	 *              make sure you had installed firefox firstly, or you can change to use other 
	 *              selenium browser instance. just CTRL+F11 to run it now.
	 * Author: Alter Hu
	 */
	public static void main(String[] args){
		 FirefoxDriver driver=new FirefoxDriver();
		 driver.manage().window().maximize();
		 driver.get("http://www.google.com");
		 driver.quit();
	}
	

}
