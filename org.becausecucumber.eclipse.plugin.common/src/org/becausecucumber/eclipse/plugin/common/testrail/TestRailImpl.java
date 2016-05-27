package org.becausecucumber.eclipse.plugin.common.testrail;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;







import java.util.List;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class TestRailImpl {
	
    public static TestRailImpl instance=null;
	public static TestRailAPI api=null;
	
	
	public static TestRailImpl getInstance(){
		if(instance==null){
			instance=new TestRailImpl();
		}
		return instance;
		
	}
	
	public boolean initTestRail(String url,String user,String password,String project ){
		boolean isok=false;
		try{
		  api=new TestRailAPI(url, user, password);
		 // PluginUtils.log(true, "Connect TestRail, Server URL is:"+url+",project name is:"+project+",username is:"+user);
		  boolean findpro=api.getProjectByName(project);
		  
		  boolean findmilestone = api.getLatestMilestone();
		  System.out.println(api.milestoneid);
		  if(findpro&&findmilestone){
		     //PluginUtils.log(true, "Connect TestRail,configured TestRail successfully !You can take to use the TestRail feature now.");
			 isok=true; 
		  }
		}catch(Exception e){
			System.err.println( "Connect TestRail,configured TestRail met unexpected error:please check your testrail credentails,then try again.\nException message is:"+e.getMessage());
			//System.err.println( "From my perspective ,Sometimes TestRail server is shutting down unexpectedly,If you server configuration is ok, please retry it later..");
		}
		return isok;
	}
	
	public boolean updateTestCase(long caseid,String given,String when,String then) throws MalformedURLException, IOException{
		HashMap<String, String> statemap = new HashMap<String,String>();
		
		statemap.put("custom_cucumber_given", given);
		statemap.put("custom_cucumber_when", when);
		statemap.put("custom_cucumber_then", then);
		boolean updated=false;
		updated=api.updateTestCase(null,
                 1,
                null,
                0,caseid, 
                null,statemap);
		return updated;
	}
	
	public boolean updateTestCaseNew(long caseid,String cucumber) throws MalformedURLException, IOException{
		HashMap<String, String> cucumbermap = new HashMap<String,String>();
		
		cucumbermap.put("custom_cucumber", cucumber);
		boolean updated=false;
		updated=api.updateTestCase(null,
                 1,
                null,
                0,caseid, 
                null,cucumbermap);
		return updated;
	}
	
	public void creatNewTestCase(String suitename,String sectionname,String storynumber,String title,String cucumber) throws MalformedURLException, IOException{
		HashMap<String, String> cucumbermap = new HashMap<String,String>();
		
		cucumbermap.put("custom_cucumber", cucumber);
		cucumbermap.put("custom_casedesc", "This test case is created from eclipse plugin for cucumber script.");
		boolean findTestSuite = findTestSuite(suitename);
		boolean findTestSection = findTestSection(sectionname);
		
		if(!findTestSuite){
			api.createTestSuite(suitename, "Create Test Suite from Cucumber Runner");
		}
		if(!findTestSection){
			api.createRecusionTestSection(sectionname);
		}
		long caseid = findTestCase(title);
		if(caseid==0){
			//create the test case
			// 1 means automated
			//4 means must test
			api.createTestCase(title, 1, null, 4, storynumber, cucumbermap);
		}else{
			api.updateTestCase(null,
	                 1,
	                null,
	                0,caseid, 
	                null,cucumbermap);
		}
		
		
	}
	
	public void creatNewTestCaseFromSectionID(String storynumber,String title,String cucumber) throws MalformedURLException, IOException{
		HashMap<String, String> cucumbermap = new HashMap<String,String>();
		
		cucumbermap.put("custom_cucumber", cucumber);
		cucumbermap.put("custom_casedesc", "This test case is created from eclipse plugin for cucumber script.");
		//api.setSuiteid(suiteid);
		//api.setSectionid(sectionid);
	
		long caseid = findTestCase(title);		
		if(caseid==0){
			//create the test case
			// 1 means automated
			//4 means must test
			api.createTestCase(title, 1, "0.5h", 4, storynumber, cucumbermap);
		}else{
			api.updateTestCase(null,
	                 1,
	                null,
	                0,caseid, 
	                null,cucumbermap);
		}
		
		
	}
	
	public String getSectionCases(long testsuite,long sectionid) throws MalformedURLException, IOException{
		api.setSuiteid(testsuite);
		String tempcontent="\t#Below are the Cucumber scripts checking out from Test Rail\n";
				
		JSONArray cases = api.getCaseFieldsList(sectionid);
		if(cases!=null){
				
			for(int caseindex=0;caseindex<cases.size();caseindex++){
					JSONObject currentcase=(JSONObject) cases.get(caseindex);
					
					long id= (Long) currentcase.get("id");
					String title = (String) currentcase.get("title");
					String cucumber=(String) currentcase.get("custom_cucumber");
					if(cucumber!=null){
						if(!cucumber.startsWith("\t\t")){
							//cucumber="\t\t"+cucumber;
						}
						
					}
					if(cucumber!=null&&cucumber!=""){
						  // cucumber=cucumber.replaceAll("\r\n", "\r\n\t\t");
						   tempcontent=tempcontent+"\t"+"@"+id+"\n"+"\t"+"Scenario: "+title+"\n"+cucumber+"\n";
					}else{
						  tempcontent=tempcontent+"\t"+"#No Cucumber step found for current test case,skip it"+"\n"+"\t"+"#@"+id+"\n"+"\t"+"#Scenario: "+title+"\n";
					}
					
			}
				
		}
					
		return tempcontent;
	}
	
	public boolean findTestSuite(String suitename){
		boolean testSuite=false;
		 try {
			 testSuite= api.getTestSuite(suitename);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return testSuite;
	}
	public boolean findTestSection(String sectioname){
		boolean testsection=false;
		try {
			testsection= api.getDeepTestSection(sectioname);
			System.out.println("api section id is:"+api.sectionid);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return testsection;
	}
	
	public  long findTestCase(String casename){
		long testcaseid=0;
		try {
			testcaseid= api.getTestCase(casename,getSectionId());
			System.out.println("case id is:"+testcaseid);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return testcaseid;
	}
	
	public long getSuiteID(){
		return api.suiteid;
	}
	public long getSectionId(){
		return api.sectionid;
	}
	
	
   public void updateCustomeEnvironment(String suitename,List<Integer> envs){
	    api.updateEnvironment(suitename,envs);
   }

}
