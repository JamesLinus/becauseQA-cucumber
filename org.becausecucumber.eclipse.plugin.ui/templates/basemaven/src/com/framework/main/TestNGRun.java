package com.framework.main;

/*
 import java.io.File;
 import java.util.Arrays;
 import java.util.HashMap;
 import java.util.LinkedList;
 import java.util.List;
 import java.util.Map;

 import org.apache.log4j.Logger;


 import com.framework.listeners.AnnotationListener;
 import com.framework.testcase.TestCaseTool;*/

public class TestNGRun {

	/*
	 * private static TestNG tests=null; private static List<XmlSuite>
	 * suitelist=new LinkedList<XmlSuite>();
	 * 
	 * private static Logger logger=Logger.getLogger(TestNGRun.class);
	 * 
	 * public static void runXMLSteps(){
	 * 
	 * 
	 * 
	 * tests=new TestNG();
	 * 
	 * tests.setPreserveOrder(true); tests.setVerbose(10);
	 * tests.setDefaultSuiteName("Automation Testsuite-Default");
	 * tests.setParallel("none");
	 * tests.setDefaultTestName("Execution Tests-Default");
	 * tests.setUseDefaultListeners(false);
	 * tests.setConfigFailurePolicy("continue");
	 * 
	 * // listeners tests.setAnnotationTransformer(new AnnotationListener());
	 * 
	 * // all the parameters Map<String,String> parameters=new
	 * HashMap<String,String>();
	 * 
	 * String browsertype="firefox"; String
	 * foundbrowsertype=TestCaseTool.browser_name; String
	 * proxysettings=TestCaseTool.proxy_setting;
	 * if(foundbrowsertype!=""&&foundbrowsertype!=null){
	 * browsertype=foundbrowsertype; }
	 * parameters.put("browsername",browsertype);
	 * 
	 * if(proxysettings!=null&&proxysettings!=""){ parameters.put("proxy",
	 * proxysettings); } //test suite xml file String
	 * testsuitehoder="testsuite-dev";
	 * if(TestCaseTool.run_environment.contains("pro")){
	 * testsuitehoder="testsuite-pro"; }
	 * 
	 * File suitefiles=new File(testsuitehoder); File[]
	 * allfiles=suitefiles.listFiles(); if(allfiles.length>0){ for(File
	 * suitefile:allfiles){ String runsuite=suitefile.getAbsolutePath();
	 * XmlSuite suite=new XmlSuite();
	 * suite.setSuiteFiles(Arrays.asList(runsuite));
	 * suite.setParameters(parameters); suitelist.add(suite); }
	 * 
	 * }else{ logger.error(
	 * "You had not set the test suite in the testsuite-pro folder,please place at least one test suite xml file(testng format) in this folder"
	 * ); System.exit(1); }
	 * 
	 * tests.setXmlSuites(suitelist);
	 * 
	 * //run tests tests.run();
	 * 
	 * }
	 */
}
