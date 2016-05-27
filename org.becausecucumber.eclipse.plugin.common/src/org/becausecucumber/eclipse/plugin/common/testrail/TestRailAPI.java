package org.becausecucumber.eclipse.plugin.common.testrail;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;







/**
* @ClassName: TestRailAPI
* @Description: TODO
* @author alterhu2020@gmail.com
* @date Aug 3, 2014 9:26:51 AM
* 
*/


public class TestRailAPI {
	
	
	
	public APIClient client;
	
	public static String base_Url=null;
	public String user=null;
	public String password=null;
	public String apikey="";
	
	public long userid=0;
	public long projectid=0;
	public long milestoneid=0;
	public long planid=0;
	public long runid=0;
	public String entryid=null;
	public long suiteid=0;
	
	public long getProjectid() {
		return projectid;
	}



	public void setProjectid(long projectid) {
		this.projectid = projectid;
	}



	public long getMilestoneid() {
		return milestoneid;
	}



	public void setMilestoneid(long milestoneid) {
		this.milestoneid = milestoneid;
	}



	public long getPlanid() {
		return planid;
	}



	public void setPlanid(long planid) {
		this.planid = planid;
	}



	public long getRunid() {
		return runid;
	}



	public void setRunid(long runid) {
		this.runid = runid;
	}



	public long getSuiteid() {
		return suiteid;
	}



	public void setSuiteid(long suiteid) {
		this.suiteid = suiteid;
	}



	public long getSectionid() {
		return sectionid;
	}



	public void setSectionid(long sectionid) {
		this.sectionid = sectionid;
	}
	public long sectionid=0;
	public ArrayList<JSONObject> testRailsConfigs=null;
	
	
	
	public TestRailAPI(String baseurl,String username,String password){
		base_Url=baseurl;
		this.user=username;
		this.password=password;
		
		client=new APIClient(base_Url);
		client.setUser(user);
        client.setPassword(password);		
	}
	
	/*public TestRailAPI(){
		this.base_Url="https://gdcqatestrail01/testrail";;
		this.user="qa_test_automation@greendotcorp.com";
		this.password="qa_test_automation";
		
		client=new APIClient(base_Url);
		client.setUser(user);
        client.setPassword(password);		
	}*/
	
	
	
	/** 
	* @Title: getProjectByName 
	* @Description: TODO
	* @author alterhu2020@gmail.com
	* @param @param projectname
	* @param @return
	* @param @throws MalformedURLException
	* @param @throws IOException    
	* @return boolean    return type
	* @throws 
	*/ 
	
	public boolean getProjectByName(String projectname) throws MalformedURLException, IOException{	
		boolean findproject=false;
		String url="get_projects";
		Object sendGet = client.sendGet(url);
		if(sendGet instanceof JSONArray){
			JSONArray projects=(JSONArray)sendGet;
			for(int k=0;k<projects.size();k++){
				  JSONObject foundproject=(JSONObject)projects.get(k);
				  if(foundproject.get("name").toString().equalsIgnoreCase(projectname.trim())){
					  this.projectid= (Long) foundproject.get("id");
					  findproject=true;
					  break;
				  }
			}	
			System.out.println("Find the project name:"+projectname+
					" is existing in test rail,return value is:"+findproject);
		}
		return findproject;		
	}
	
	/** 
	* @Title: getPriorities 
	* @Description: TODO
	* @author alterhu2020@gmail.com
	* @param @return
	* @param @throws MalformedURLException
	* @param @throws IOException    
	* @return List<JSONObject>    return type
	* @throws 
	*/ 
	
	public List<JSONObject> getPriorities() throws MalformedURLException, IOException{
		List<JSONObject> prioritieslist=new ArrayList<JSONObject>();
		JSONArray priorities=(JSONArray) client.sendGet("get_priorities");
		for(int k=0;k<priorities.size();k++){
			prioritieslist.add((JSONObject) priorities.get(k));
		}
		return prioritieslist;
	}
	
	/** 
	* @Title: getProjectConfiguration 
	* @Description: TODO
	* @author alterhu2020@gmail.com
	* @param @throws MalformedURLException
	* @param @throws IOException    
	* @return void    return type
	* @throws 
	*/ 
	
	public void getProjectConfiguration() throws MalformedURLException, IOException{
		String confurl=String.format("get_configs/%d", this.projectid);
		JSONArray configs=(JSONArray) client.sendGet(confurl);
		testRailsConfigs=new ArrayList<JSONObject>();
		for(int k=0;k<configs.size();k++){
			JSONObject configroup=(JSONObject) configs.get(k);
			testRailsConfigs.add(configroup);
		}
	}
	
	/** 
	* @Title: createProject 
	* @Description: TODO
	* @author alterhu2020@gmail.com
	* @param @param projectname
	* @param @param description
	* @param @return
	* @param @throws MalformedURLException
	* @param @throws IOException    
	* @return long    return type
	* @throws 
	*/ 
	
	public long createProject(String projectname,String description) throws MalformedURLException, IOException{
		String newurl="add_project";
		HashMap<Object, Object> postData = new HashMap<Object, Object>();
	    postData.put("name", projectname);
	    postData.put("announcement", description);
	    postData.put("show_announcement", true);
	    JSONObject newproject=(JSONObject) client.sendPost(newurl, postData);
	    this.projectid=(Long) newproject.get("id");
	    //System.out.println("Create the test rail new project:"+projectname);
	    
	    return this.projectid;
		
	}
	
	/** 
	* @Title: updateProject 
	* @Description: TODO
	* @author alterhu2020@gmail.com
	* @param @param projectname
	* @param @param description
	* @param @return
	* @param @throws MalformedURLException
	* @param @throws IOException    
	* @return long    return type
	* @throws 
	*/ 
	
	public long updateProject(String projectname,String description) throws MalformedURLException, IOException{
		
		String newurl=String.format("update_project/%d",this.projectid);
		HashMap<Object, Object> postData = new HashMap<Object, Object>();
	    postData.put("name", projectname);
	    postData.put("announcement", description);
	    postData.put("show_announcement", true);
	    JSONObject newproject=(JSONObject) client.sendPost(newurl, postData);
	    this.projectid=(Long) newproject.get("id");
	   // System.out.println("Update the test rail  project:"+projectname);
	    
	    return this.projectid;
		
	}
	
	/** 
	* @Title: getUserID 
	* @Description: TODO
	* @author alterhu2020@gmail.com
	* @param @return
	* @param @throws MalformedURLException
	* @param @throws IOException    
	* @return boolean    return type
	* @throws 
	*/ 
	
	public boolean getUserID() throws MalformedURLException, IOException{
		boolean finduser=false;
		String userurl="get_users";
		JSONArray userinfo=(JSONArray) client.sendGet(userurl);
	    for(int k=0 ;k<userinfo.size();k++){
	    	JSONObject singleuser=(JSONObject) userinfo.get(k);
	    	if(singleuser.get("name").toString().toLowerCase().contains(this.user.toLowerCase())
	    			||singleuser.get("email").toString().toLowerCase().contains(this.user.toLowerCase().trim())){
	    		this.userid=(Long) singleuser.get("id");
	    		finduser=true;
	    		break;
	    	}
	    }
	    System.out.println("Find the username id :"+this.user+
				" created before ,return value is:"+finduser);
		return finduser;
	}
	
	/** 
	* @Title: getMilestone 
	* @Description: TODO
	* @author alterhu2020@gmail.com
	* @param @param milestonename
	* @param @return
	* @param @throws MalformedURLException
	* @param @throws IOException    
	* @return boolean    return type
	* @throws 
	*/ 
	
	public boolean getMilestone(String milestonename) throws MalformedURLException, IOException{
		boolean findmilestone=false;
		String url=String.format("get_milestones/%d", this.projectid);
		JSONArray milestones=(JSONArray) client.sendGet(url);
		for(int k=0;k<milestones.size();k++){
			JSONObject milestone=(JSONObject) milestones.get(k);
			if(milestone.get("name").toString().equalsIgnoreCase(milestonename.trim())
					&&(!(Boolean)milestone.get("is_completed"))){
				this.milestoneid=(Long) milestone.get("id");
				findmilestone=true;
				break;
			}
		}
		return findmilestone;	
	}
	
	/** 
	* @Title: getMilestone 
	* @Description: TODO
	* @author alterhu2020@gmail.com
	* @param @param milestonename
	* @param @return
	* @param @throws MalformedURLException
	* @param @throws IOException    
	* @return boolean    return type
	* @throws 
	*/ 
	
	public boolean getLatestMilestone() throws MalformedURLException, IOException{
		boolean findmilestone=false;
		String url=String.format("get_milestones/%d", this.projectid);
		JSONArray milestones=(JSONArray) client.sendGet(url);
		for(int k=0;k<milestones.size();k++){
			JSONObject milestone=(JSONObject) milestones.get(k);
			if(!(Boolean)milestone.get("is_completed")){
				this.milestoneid=(Long) milestone.get("id");
				findmilestone=true;
				break;
			}
		}
		return findmilestone;	
	}
	/** 
	* @Title: createMilestone 
	* @Description: TODO
	* @author alterhu2020@gmail.com
	* @param @param milestonename
	* @param @param parameters
	* @param @return
	* @param @throws MalformedURLException
	* @param @throws IOException    
	* @return long    return type
	* @throws 
	*/ 
	
	public long createMilestone(String milestonename,String description,String duedate) throws MalformedURLException, IOException{

			String newmilestone=String.format("add_milestone/%d", this.projectid);
			HashMap<Object, Object> postData = new HashMap<Object, Object>();
		    postData.put("name", milestonename);
		    postData.put("description", description);
		    long finalseconds;
		    //this parameter is unix timestamp
			try {
				finalseconds =new SimpleDateFormat("yyyy-MM-dd").parse(duedate).getTime();
				postData.put("due_on", finalseconds);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  
		        
		   
		    JSONObject milestone=(JSONObject) client.sendPost(newmilestone, postData);
		    this.milestoneid=(Long) milestone.get("id");
		  //  return true;
		   return this.milestoneid;
	}
	
	/** 
	* @Title: updateMilestone 
	* @Description: TODO
	* @author alterhu2020@gmail.com
	* @param @param milestonename
	* @param @param description
	* @param @param duedate
	* @param @return
	* @param @throws MalformedURLException
	* @param @throws IOException    
	* @return long    return type
	* @throws 
	*/ 
	
	public long updateMilestone(String milestonename,String description,String duedate) throws MalformedURLException, IOException{

		String newmilestone=String.format("update_milestone/%d", this.milestoneid);
		HashMap<Object, Object> postData = new HashMap<Object, Object>();
	    postData.put("name", milestonename);
	    postData.put("description", description);
	    long finalseconds;
	    try {
	    	finalseconds=new SimpleDateFormat("yyyy-MM-dd").parse(duedate).getTime();
	    	postData.put("due_on", finalseconds);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	        
	   
	    JSONObject milestone=(JSONObject) client.sendPost(newmilestone, postData);
	    this.milestoneid=(Long) milestone.get("id");
	  //  return true;
	   return this.milestoneid;
}

	
	/** 
	* @Title: getTestPlan 
	* @Description: TODO
	* @author alterhu2020@gmail.com
	* @param @param planname
	* @param @return
	* @param @throws MalformedURLException
	* @param @throws IOException    
	* @return boolean    return type
	* @throws 
	*/ 
	
	public boolean getTestPlan(String planname) throws MalformedURLException, IOException{
		boolean iscreated=false;
		String url=String.format("get_plans/%s", this.projectid);
		JSONArray plans=(JSONArray) client.sendGet(url);
		for(int k=0;k<plans.size();k++){
			JSONObject plan=(JSONObject) plans.get(k);
			if(plan.get("name").toString().equalsIgnoreCase(planname.trim())){
			    this.planid=(Long)plan.get("id");
			    iscreated=true;
			    break;
			}
		}
		return iscreated;
	}
	
	public void getTestPlan(long planid) throws MalformedURLException, IOException{
		
		String url=String.format("get_plan/%s", planid);
		JSONObject plans=(JSONObject) client.sendGet(url);
		System.out.println(plans);
		//return iscreated;
	}
	
	public boolean getPlanEntry(String testsuitname) throws MalformedURLException, IOException{
		boolean iscreated=false;
		boolean testSuite = getTestSuite(testsuitname);
		if(testSuite){
			String url=String.format("get_plan/%s", this.planid);
			JSONObject plan=(JSONObject) client.sendGet(url);
			JSONArray entries = (JSONArray) plan.get("entries");
			for(int k=0;k<entries.size();k++){
				JSONObject runs=(JSONObject) entries.get(k);
				
				JSONArray runarray = (JSONArray) runs.get("runs");
				for(int j=0;j<runarray.size();j++){
					JSONObject run = (JSONObject) runarray.get(j);
					long findsuiteid =(Long)run.get("suite_id");
					if(findsuiteid==this.suiteid){
						iscreated=true;
						this.runid = (Long) run.get("id");
						break;
					}
				}
			}
		}
		return iscreated;
	}
	
	public boolean getPlanEntry(long suiteid) throws MalformedURLException, IOException{
		boolean iscreated=false;

		String url=String.format("get_plan/%s", this.planid);
		JSONObject plan=(JSONObject) client.sendGet(url);
		JSONArray entries = (JSONArray) plan.get("entries");
		for(int k=0;k<entries.size();k++){
			JSONObject runs=(JSONObject) entries.get(k);
			
			JSONArray runarray = (JSONArray) runs.get("runs");
			for(int j=0;j<runarray.size();j++){
				JSONObject run = (JSONObject) runarray.get(j);
				long findsuiteid =(Long)run.get("suite_id");
				if(findsuiteid==suiteid){
					iscreated=true;
					this.runid = (Long) run.get("id");
					this.entryid=run.get("entry_id").toString();
					break;
				}
			}
		}
	
		return iscreated;
	}
	

	public boolean addPlanEntry(String testsuitname) throws MalformedURLException, IOException{
		boolean iscreated=false;
		boolean testSuite = getTestSuite(testsuitname);
		if(testSuite){
			String url=String.format("add_plan_entry/%s", this.planid);
			
			HashMap<String, Object> postData = new HashMap<String, Object>();
		    postData.put("suite_id", this.suiteid);
		    postData.put("name", testsuitname);
		    postData.put("include_all", false);
			JSONObject planruns=(JSONObject) client.sendPost(url, postData);
			
			
			this.runid=(Long)planruns.get("id");	
			iscreated=true;
		}
		return iscreated;
	}
	
	public boolean addPlanEntry(long suiteid) throws MalformedURLException, IOException{
		boolean iscreated=false;
	    String testSuite = getTestSuite(suiteid);
		String url=String.format("add_plan_entry/%s", this.planid);
		
		HashMap<String, Object> postData = new HashMap<String, Object>();
	    postData.put("suite_id", suiteid);
	    postData.put("name", testSuite);
	    postData.put("include_all", false);
		JSONObject planruns=(JSONObject) client.sendPost(url, postData);
		
		this.runid=(Long)planruns.get("id");	
		iscreated=true;
	
		return iscreated;
	}
	public boolean addPlanEntry(long suiteid,List<Integer> configs) throws MalformedURLException, IOException{
		boolean iscreated=false;
	    String testSuite = getTestSuite(suiteid);
		String url=String.format("add_plan_entry/%s", this.planid);
		
		HashMap<String, Object> postData = new HashMap<String, Object>();
	    postData.put("suite_id", suiteid);
	    postData.put("name", testSuite);
	    postData.put("assignedto_id", this.userid);
	    postData.put("include_all", true);
	   /* postData.put("config_ids", configs);
	    List<Long> caselist=new ArrayList<Long>();
	    caselist.add(caseid);
	    postData.put("case_ids", caselist); 
	    
	   
	    
	    JSONObject data=new JSONObject();
	   
	    data.put("include_all", true);
	   // data.put("case_ids", caselist);
	    data.put("config_ids", configs);
	    
	    List<JSONObject> runlist=new ArrayList<JSONObject>();
	    runlist.add(data);
	    postData.put("runs", runlist);*/
	    
		JSONObject planruns=(JSONObject) client.sendPost(url, postData);
		
		
		JSONArray runarray = (JSONArray) planruns.get("runs");
		for(int j=0;j<runarray.size();j++){
			JSONObject run = (JSONObject) runarray.get(j);
			long findsuiteid =(Long)run.get("suite_id");
			if(findsuiteid==suiteid){
				iscreated=true;
				this.runid = (Long) run.get("id");
				this.entryid=run.get("entry_id").toString();
				break;
			}
		}
		
		iscreated=true;
	
		return iscreated;
	}
	
	public boolean updatePlanEntryForCase(long suiteid,List<Long> caselist) throws MalformedURLException, IOException{
		boolean isupdate=false;
		String url=String.format("update_plan_entry/%s/%s", this.planid,this.entryid);
		
		HashMap<String, Object> postData = new HashMap<String, Object>();
	   // postData.put("assignedto_id", this.userid);
	    postData.put("include_all", false);
	    postData.put("assignedto_id",this.userid);
	   
	   // caselist.add(1550418l);
	   // caselist.add(1550440l);
	    postData.put("case_ids", caselist);
	     
		JSONObject planruns=(JSONObject) client.sendPost(url, postData);
		
		JSONArray runarray = (JSONArray) planruns.get("runs");
		for(int j=0;j<runarray.size();j++){
			JSONObject run = (JSONObject) runarray.get(j);
			long findsuiteid =(Long)run.get("suite_id");
			if(findsuiteid==suiteid){
				isupdate=true;
				this.runid = (Long) run.get("id");
				break;
			}
		}

		return isupdate;
	}
	
	
	
	/** 
	* @Title: createTestPlan 
	* @Description: TODO
	* @author alterhu2020@gmail.com
	* @param @param planname
	* @param @return
	* @param @throws MalformedURLException
	* @param @throws IOException    
	* @return long    return type
	* @throws 
	*/ 
	
	public long createTestPlan(String planname) throws MalformedURLException, IOException{
		
			  String planurl = String.format("add_plan/%s", this.projectid);
			  HashMap<String, Object> postData = new HashMap<String, Object>();
		      postData.put("name", planname);
		      postData.put("milestone_id", this.milestoneid);
		      JSONObject newplan=(JSONObject) client.sendPost(planurl, postData);
		      this.planid=(Long) newplan.get("id");
			//  return true;
		      return this.planid;
		
	}
	
	/** 
	* @Title: updateTestPlan 
	* @Description: TODO
	* @author alterhu2020@gmail.com
	* @param @param planname
	* @param @return
	* @param @throws MalformedURLException
	* @param @throws IOException    
	* @return long    return type
	* @throws 
	*/ 
	
	public long updateTestPlan(String planname) throws MalformedURLException, IOException{
		
		  String planurl = String.format("update_plan/%d", this.planid);
		  HashMap<String, Object> postData = new HashMap<String, Object>();
	      postData.put("name", planname);
	      postData.put("milestone_id", this.milestoneid);
	      postData.put("is_completed", true);
	      JSONObject newplan=(JSONObject) client.sendPost(planurl, postData);
	      this.planid=(Long) newplan.get("id");
		//  return true;
	      return this.planid;
	
}
	
	/** 
	* @Title: closeTestPlan 
	* @Description: TODO
	* @author alterhu2020@gmail.com
	* @param @param planname
	* @param @return
	* @param @throws MalformedURLException
	* @param @throws IOException    
	* @return boolean    return type
	* @throws 
	*/ 
	
	public void closeTestPlan(String planname) throws MalformedURLException, IOException{
		String url=String.format("get_plans/%d", this.projectid);
		JSONArray plans=(JSONArray) client.sendGet(url);
	/*	this.planid=63933;
		this.milestoneid=825;
		updateTestPlan("jenkins job sample-Automation-333");
		String closeurl2=String.format("delete_plan/%d", 63933);
		client.sendGet(closeurl2);*/
		
		for(int k=0;k<plans.size();k++){
			JSONObject plan=(JSONObject) plans.get(k);
			
			if(plan.get("name").toString().toLowerCase().contains(planname.toLowerCase().trim())){
					//&&((Boolean)plan.get("is_completed"))){
				String closeurl=String.format("close_plan/%s", plan.get("id").toString());
				client.sendPost(closeurl, new JSONObject());
				//return true;
			}
		}
		//return false;
	}
	/** 
	* @Title: getTestRun 
	* @Description: TODO
	* @author alterhu2020@gmail.com
	* @param @param runame
	* @param @return
	* @param @throws MalformedURLException
	* @param @throws IOException    
	* @return boolean    return type
	* @throws 
	*/ 
	
	public boolean getTestRun(String runame) throws MalformedURLException, IOException{
		boolean findrun=false;
		String url=String.format("get_plan/%s", this.planid);
		JSONObject plan=(JSONObject) client.sendGet(url);
		JSONArray entries=(JSONArray) plan.get("entries");
		for(int k=0;k<entries.size();k++){
			JSONObject runs=(JSONObject) entries.get(k);
			String currententry=(String) runs.get("id");
			JSONArray onerun=(JSONArray) runs.get("runs");
			for(int i=0;i<onerun.size();i++){
				JSONObject run=(JSONObject) onerun.get(i);
				if(run.get("name").toString().equalsIgnoreCase(runame.trim())){
				   
				    this.entryid=currententry;
				    this.runid=(Long)run.get("id");
				    findrun=true;
				    break;
				}
			}
			
		}
		return findrun;
	}

	/** 
	* @Title: createTestPlanRun 
	* @Description: TODO
	* @author alterhu2020@gmail.com
	* @param @return
	* @param @throws MalformedURLException
	* @param @throws IOException    
	* @return long    return type
	* @throws 
	*/ 
	
	public long createTestPlanRunForSuite() throws MalformedURLException, IOException{
	   String runurl=String.format("add_plan_entry/%d", this.planid);
	   Map<Object,Object> arguments=new HashMap<Object, Object>();
	   arguments.put("suite_id", this.suiteid);
	   
	   arguments.put("assignedto_id", userid);
	   arguments.put("include_all", true);
	      
	   JSONObject planruns=(JSONObject) client.sendPost(runurl, arguments);
	   
       this.runid=(Long)planruns.get("id");	
      // this.entryid=planruns.get("id");
	   return this.runid;
		
	}
	

	public long createTestPlanRunForSection(String runame) throws MalformedURLException, IOException{
	   String runurl=String.format("add_plan_entry/%d", this.planid);
	   Map<Object,Object> arguments=new HashMap<Object, Object>();
	   
	   arguments.put("name", runame);
	   arguments.put("suite_id", this.suiteid);
	   
	   arguments.put("assignedto_id", userid);
	   arguments.put("include_all", false);
	   long[] cases=getCaseList(this.sectionid);
	   
	   arguments.put("case_ids", cases);
	      
	   JSONObject planruns=(JSONObject) client.sendPost(runurl, arguments);
	   
       this.runid=(Long)planruns.get("id");	
      // this.entryid=planruns.get("id");
	   return this.runid;
		
	}
	
	/** 
	* @Title: updateTestPlanRunForTestSection 
	* @Description: This function is a bug by now ,as metioned in offical document ,so you should use the add_plan_entry intead it
	*               http://forum.gurock.com/topic/1584/adding-a-new-test-run-configuration-to-an-existing-entry/
	*               it's a to-do list for testrail team by 2014/8/3 
	* @deprecated
	* @author alterhu2020@gmail.com
	* @param @param runame
	* @param @param sectionid
	* @param @return
	* @param @throws MalformedURLException
	* @param @throws IOException    
	* @return boolean    return type
	* @throws 
	*/ 
	
	public boolean updateTestPlanRunForTestSection(String runame, long sectionid) throws MalformedURLException, IOException {
		   
		  // getTestRun(runame);
		   String runurl=String.format("update_plan_entry/%d/%s", this.planid,this.entryid);
		   Map<Object,Object> arguments=new HashMap<Object, Object>();
		  // arguments.put("suite_id", this.suiteid);
		   arguments.put("name", runame);
		   arguments.put("assignedto_id", this.userid);
		   arguments.put("include_all", false);
		  
		   long[] cases=getCaseList(sectionid);
		   
		   arguments.put("case_ids", cases);
		      
		   JSONObject planruns=(JSONObject) client.sendPost(runurl, arguments);
		   if(planruns!=null){
			   return true;
		   }
		   return false;
	}
	
	/** 
	* @Title: updateTestPlanRunForCases 
	* @Description: This function is a bug by now ,as metioned in offical document ,so you should use the add_plan_entry intead it
	*               http://forum.gurock.com/topic/1584/adding-a-new-test-run-configuration-to-an-existing-entry/
	*               it's a to-do list for testrail team by 2014/8/3 
	* @deprecated
	* @author alterhu2020@gmail.com
	* @param @param caseids
	* @param @return
	* @param @throws MalformedURLException
	* @param @throws IOException    
	* @return boolean    return type
	* @throws 
	*/ 
	
	public boolean updateTestPlanRunForCases(long[] caseids) throws MalformedURLException, IOException{
		
		   //getTestRun(runame);
		   String runurl=String.format("update_plan_entry/%d/%d", this.planid,this.entryid);
		   Map<Object,Object> arguments=new HashMap<Object, Object>();
		  // arguments.put("suite_id", this.suiteid);
		   
		  // arguments.put("assignedto_id", userid);
		   arguments.put("include_all", false);
		  
		   arguments.put("case_ids", caseids);
		      
		   JSONObject planruns=(JSONObject) client.sendPost(runurl, arguments);
		   if(planruns!=null){
			   return true;
		   }
		return false;
	}
	
	/** 
	* @Title: getTestsInRun 
	* @Description: TODO
	* @author alterhu2020@gmail.com
	* @param @return
	* @param @throws MalformedURLException
	* @param @throws IOException    
	* @return boolean    return type
	* @throws 
	*/ 
	
	public boolean getTestsInRun() throws MalformedURLException, IOException{
		boolean hastests=false;
		String url=String.format("get_tests/%d", this.runid);
		JSONArray tests=(JSONArray) client.sendGet(url);
		if(tests.size()>0){
			hastests=true;
		}
		return hastests;
	}
	/** 
	* @Title: getTestSuite 
	* @Description: TODO
	* @author alterhu2020@gmail.com
	* @param @param suitename
	* @param @return
	* @param @throws MalformedURLException
	* @param @throws IOException    
	* @return boolean    return type
	* @throws 
	*/ 
	
	public boolean getTestSuite(String suitename) throws MalformedURLException, IOException{
		boolean iscreated=false;
		String url = String.format("get_suites/%s", this.projectid);
	    JSONArray suites=(JSONArray) client.sendGet(url);
		for(int k=0;k<suites.size();k++){
			JSONObject suite=(JSONObject) suites.get(k);
			if(suite.get("name").toString().equalsIgnoreCase(suitename.trim())){
				this.suiteid=(Long) suite.get("id");
				iscreated=true;
				break;
			}
		}
		return iscreated;
	}
	
	/** 
	* @Title: getTestSuite 
	* @Description: TODO
	* @author alterhu2020@gmail.com
	* @param @param suitename
	* @param @return
	* @param @throws MalformedURLException
	* @param @throws IOException    
	* @return boolean    return type
	* @throws 
	*/ 
	
	public String getTestSuite(long suiteid) throws MalformedURLException, IOException{
		String suitename=null;
		String url = String.format("get_suites/%s", this.projectid);
	    JSONArray suites=(JSONArray) client.sendGet(url);
		for(int k=0;k<suites.size();k++){
			JSONObject suite=(JSONObject) suites.get(k);
			long findsuiteid=(Long)suite.get("id");
			if(findsuiteid==suiteid){
				suitename=suite.get("name").toString();				
				break;
			}
		}
		return suitename;
	}
	/** 
	* @Title: createTestSuite 
	* @Description: TODO
	* @author alterhu2020@gmail.com
	* @param @param suitename
	* @param @param description
	* @param @return
	* @param @throws MalformedURLException
	* @param @throws IOException    
	* @return long    return type
	* @throws 
	*/ 
	
	public long createTestSuite(String suitename,String description) throws MalformedURLException, IOException{
	
			
	  String newsuiteurl = String.format("add_suite/%s", this.projectid);
	  HashMap<String, Object> postData = new HashMap<String, Object>();
	  postData.put("name", suitename);
	  postData.put("description", description);
	  
	  JSONObject newsuite=(JSONObject) client.sendPost(newsuiteurl, postData);
	  this.suiteid=(Long) newsuite.get("id");
		     
	  return this.suiteid;
	}
	
	/** 
	* @Title: updateTestSuite 
	* @Description: TODO
	* @author alterhu2020@gmail.com
	* @param @param suitename
	* @param @param description
	* @param @return
	* @param @throws MalformedURLException
	* @param @throws IOException    
	* @return long    return type
	* @throws 
	*/ 
	
	public long updateTestSuite(String suitename,String description) throws MalformedURLException, IOException{
		
		
		  String newsuiteurl = String.format("update_suite/%d", this.suiteid);
		  HashMap<String, Object> postData = new HashMap<String, Object>();
		  postData.put("name", suitename);
		  postData.put("description", description);
		  
		  JSONObject newsuite=(JSONObject) client.sendPost(newsuiteurl, postData);
		  this.suiteid=(Long) newsuite.get("id");
			     
		  return this.suiteid;
		}
	
	/** 
	* @Title: getTestSection 
	* @Description: TODO
	* @author alterhu2020@gmail.com
	* @param @param sectionname
	* @param @return
	* @param @throws MalformedURLException
	* @param @throws IOException    
	* @return boolean    return type
	* @throws 
	*/ 
	
	public boolean getTestSection(String sectionname) throws MalformedURLException, IOException{
		boolean iscreated=false;
		String url = String.format("get_sections/%s&suite_id=%s", this.projectid, this.suiteid);
	    JSONArray sections=(JSONArray) client.sendGet(url);
		for(int k=0;k<sections.size();k++){
			JSONObject section=(JSONObject) sections.get(k);
			if(section.get("name").toString().equalsIgnoreCase(sectionname.trim())){
				this.sectionid=(Long) section.get("id");
				iscreated=true;
				break;
			}
		}
		return iscreated;
	}
	

	/** 
	* @Title: getTestSection 
	* @Description: TODO
	* @author alterhu2020@gmail.com
	* @param @param sectionname
	* @param @return
	* @param @throws MalformedURLException
	* @param @throws IOException    
	* @return boolean    return type
	* @throws 
	*/ 
	
	public boolean getDeepTestSection(String sectionname) throws MalformedURLException, IOException{
		boolean iscreated=false;
		String url = String.format("get_sections/%s&suite_id=%s", this.projectid, this.suiteid);
	    JSONArray sections=(JSONArray) client.sendGet(url);
	    List<String> sectionlist=null;
	    boolean moresections=false;
	    if(sectionname.contains("/")){
	    	sectionlist=new LinkedList<String>(Arrays.asList(sectionname.split("/")));
	    	moresections=true;
	    }
	 
	    Object tempparentid=null;
		for(int k=0;k<sections.size();k++){
			JSONObject section=(JSONObject) sections.get(k);
			/*if(section.get("name").equals("ECash - Auth Commit confirmation notification")){
				//System.out.println(section);
			}*/
					
			if(moresections){
				for(int j=0;j<sectionlist.size();j++){
					Object c=section.get("parent_id");				
					if(c==null){
						if(section.get("name").toString().equalsIgnoreCase(sectionlist.get(j))){
							tempparentid=section.get("id").toString();
							this.sectionid=(Long) section.get("id");
							sectionlist.remove(j);
						}
					
					}else{
						if(section.get("name").toString().equalsIgnoreCase(sectionlist.get(j))&&
								section.get("parent_id").toString().equals(tempparentid)){
							tempparentid=section.get("id").toString();
							this.sectionid=(Long) section.get("id");
							sectionlist.remove(j);
						}
					}
					}
					
								
			}else{
				if(section.get("name").toString().equalsIgnoreCase(sectionname.trim())){
					this.sectionid=(Long) section.get("id");
					iscreated=true;
					break;
				}
			}
		}
		return iscreated;
	}
	
	
	/*
	 * without the parent section ,it's at the suite 
	 */
	/** 
	* @Title: createTestSection 
	* @Description: TODO
	* @author alterhu2020@gmail.com
	* @param @param sectionname
	* @param @param parentsectionid
	* @param @return
	* @param @throws MalformedURLException
	* @param @throws IOException    
	* @return long    return type
	* @throws 
	*/ 
	
	public long createTestSection(String sectionname,long parentsectionid) throws MalformedURLException, IOException{
			   //parse the section string 
			    String newsectionurl = String.format("add_section/%s", this.projectid);
		        HashMap<String, Object> postData = new HashMap<String, Object>();
		        postData.put("name", sectionname);
		        postData.put("suite_id", this.suiteid);
		        if(parentsectionid!=0){
		           postData.put("parent_id", parentsectionid);
		        }
		        JSONObject newsection=(JSONObject) client.sendPost(newsectionurl, postData);
		        this.sectionid=(Long) newsection.get("id");
		     //   return true;

		        return this.sectionid;
	}
	/*
	 * without the parent section ,it's at the suite 
	 */
	/** 
	* @Title: createRecusionTestSection 
	* @Description: TODO
	* @author alterhu2020@gmail.com
	* @param @param sectionname
	* @param @return
	* @param @throws MalformedURLException
	* @param @throws IOException    
	* @return long    return type
	* @throws 
	*/ 
	
	public long createRecusionTestSection(String sectionname) throws MalformedURLException, IOException{
			   //parse the section string

		        if(sectionname.contains("/")){
		        
		        	String[] sections=sectionname.split("/");
		        	for(int j=0;j<sections.length;j++){
		        		 boolean sectionexisting= getTestSection(sections[j]);
		        		 if(!sectionexisting){
		        			  createTestSection(sections[j],this.sectionid);
		        		 }
		        	}
		        }else{
		        	createTestSection(sectionname,0);
		        }
			 
		        return this.sectionid;
	}
	
	
	/** 
	* @Title: updateTestSection 
	* @Description: TODO
	* @author alterhu2020@gmail.com
	* @param @param sectionname
	* @param @return
	* @param @throws MalformedURLException
	* @param @throws IOException    
	* @return long    return type
	* @throws 
	*/ 
	
	public long updateTestSection(String sectionname) throws MalformedURLException, IOException{
		   String newsectionurl = String.format("update_section/%d", this.sectionid);
	        HashMap<String, Object> postData = new HashMap<String, Object>();
	        postData.put("name", sectionname);
	     
	        JSONObject newsection=(JSONObject) client.sendPost(newsectionurl, postData);
	        this.sectionid=(Long) newsection.get("id");
	     //   return true;

	        return this.sectionid;
	}
	/** 
	* @Title: getCaseList 
	* @Description: TODO
	* @author alterhu2020@gmail.com
	* @param @param sectionid
	* @param @return
	* @param @throws MalformedURLException
	* @param @throws IOException    
	* @return long[]    return type
	* @throws 
	*/ 
	
	public long[] getCaseList(long sectionid) throws MalformedURLException, IOException{
		String url = String.format("get_cases/%s&suite_id=%s&section_id=%d", this.projectid, this.suiteid, sectionid);
		JSONArray cases=(JSONArray) client.sendGet(url);
		
		long[] caseids=new long[cases.size()]; 
				
		for(int caseindex=0;caseindex<cases.size();caseindex++){
			JSONObject currentcase=(JSONObject) cases.get(caseindex);
			
			caseids[caseindex]=(Long) currentcase.get("id");
			
		}
		
		return caseids;
		
	}
	
	public JSONArray getCaseFieldsList(long sectionid) throws MalformedURLException, IOException{
		
		String url = String.format("get_cases/%s&suite_id=%s&section_id=%d", this.projectid, this.suiteid, sectionid);
		JSONArray cases=(JSONArray) client.sendGet(url);
			
		return cases;
		
	}
	
    public boolean getCase(long caseid) throws MalformedURLException, IOException{
		boolean findcase=false;
		String url = String.format("get_case/%d",caseid);
		JSONObject caseobject=(JSONObject) client.sendGet(url);
		
		if(caseobject!=null){
			 long newcaseid = (Long) caseobject.get("id");
			 if(newcaseid!=0l){
			    findcase= true;
			 }
		}
		return findcase;
		
	}
    
    public long getSuiteidByCase(long caseid) throws MalformedURLException, IOException{
		long suiteid=0;
		String url = String.format("get_case/%d",caseid);
		JSONObject caseobject=(JSONObject) client.sendGet(url);
		
		if(caseobject!=null){
			 long newcaseid = (Long) caseobject.get("suite_id");
			 if(newcaseid!=0l){
				 suiteid=newcaseid;
			 }
		}
		return suiteid;
		
	}
	/** 
	* @Title: getTestCase 
	* @Description: TODO
	* @author alterhu2020@gmail.com
	* @param @param casename
	* @param @param sectionid
	* @param @return
	* @param @throws MalformedURLException
	* @param @throws IOException    
	* @return long    return type
	* @throws 
	*/ 
	
	public long getTestCase(String casename,long sectionid) throws MalformedURLException, IOException{
		long caseid=0;
		String url = String.format("get_cases/%s&suite_id=%s&section_id=%d", this.projectid, this.suiteid, sectionid);
		JSONArray cases=(JSONArray) client.sendGet(url);
		for(int caseindex=0;caseindex<cases.size();caseindex++){
			JSONObject currentcase=(JSONObject) cases.get(caseindex);
			if(currentcase.get("title").toString().equalsIgnoreCase(casename.trim())){
				caseid=(Long) currentcase.get("id");
				break;
			}
		}
		return caseid;
		
	}
	
	/** 
	* @Title: updateTestCase 
	* @Description: TODO
	* @author alterhu2020@gmail.com
	* @param @param caseName
	* @param @param caseId
	* @param @param refs
	* @param @param parameters
	* @param @return
	* @param @throws MalformedURLException
	* @param @throws IOException    
	* @return boolean    return type
	* @throws 
	*/ 
	
	@SuppressWarnings("rawtypes")
	public boolean updateTestCase(String caseName,
			                      long typeid,
			                      String estimate,
			                      long priorityid,long caseId, 
			                      String refs, HashMap parameters) throws MalformedURLException, IOException{
		boolean hadupdated=false;
		String url = String.format("update_case/%d", caseId);
	        
	    HashMap<Object, Object> postData = new HashMap<Object, Object>();
	    if(caseName!=null&&caseName!=""){
	    postData.put("title", caseName);
	    }
	    if(typeid!=0l&&typeid!=0){
	    postData.put("type_id", typeid); // Automated
	    }
	    if(estimate!=null&&estimate!=""){
	    postData.put("estimate", estimate);
	    }
	    if(priorityid!=0l&&priorityid!=0){
	    postData.put("priority_id", priorityid);
	    }
	    
	   /* postData.put("type_id", typeid); // Automated 1
        postData.put("estimate", estimate);
        postData.put("priority_id", priorityid); // 4 means must test
*/       
	    postData.put("custom_cucumber_written", true); // 4 means must test
        postData.put("custom_successfully_automated", true); // 4 means must test
      //  postData.put("milestone_id", this.milestoneid); // Milestone attached to case
	  
	    if (refs != null)  // Add list of reference Id/requirement to test case name if starts with project name
	        postData.put("refs", refs);
	    if(parameters!=null){
		    Set set = parameters.entrySet();
		    Iterator i = set.iterator();
		    while(i.hasNext())
		     {
		            Map.Entry me = (Map.Entry)i.next();
		            postData.put(me.getKey(), me.getValue());
		     }
	    }
	    JSONObject updatedcase=(JSONObject) client.sendPost(url, postData);
	    if(updatedcase!=null){
	    	hadupdated=true;
	    }
		
		return hadupdated;
		
	}
	
	/** 
	* @Title: createTestCase 
	* @Description: TODO
	* @author alterhu2020@gmail.com
	* @param @param caseName
	* @param @param sectionId
	* @param @param refs
	* @param @param parameters
	* @param @return
	* @param @throws MalformedURLException
	* @param @throws IOException    
	* @return long    return type
	* @throws 
	*/ 
	
	@SuppressWarnings("rawtypes")
	public long createTestCase(String caseName,
			                   long typeid,
			                   String estimate,
			                   long priorityid,
			                   String refs, HashMap parameters) throws MalformedURLException, IOException
    {
		long newcaseid=0;
        String url = String.format("add_case/%d", this.sectionid);        
        HashMap<Object, Object> postData = new HashMap<Object, Object>();
        postData.put("title", caseName);
        postData.put("type_id", typeid); // Automated 1
        postData.put("estimate", estimate);
        postData.put("priority_id", priorityid); // 4 means must test
        postData.put("custom_cucumber_written", true); // 4 means must test
        postData.put("custom_successfully_automated", true); // 4 means must test
        postData.put("milestone_id", this.milestoneid); // Milestone attached to case
        if (refs != null)  // Add list of reference Id/requirement to test case name if starts with project name
        	postData.put("refs", refs);
        if(parameters!=null){
	        Set set = parameters.entrySet();
	        Iterator i = set.iterator();
	        while(i.hasNext())
	        {
	            Map.Entry me = (Map.Entry)i.next();
	            postData.put(me.getKey(), me.getValue());
	        }
        }
        
       JSONObject newcase=(JSONObject) client.sendPost(url, postData);
       if(newcase!=null){
    	   newcaseid=(Long) newcase.get("id");
       }
       
       return newcaseid;
    }
	/*
	 * 
		1	Passed
		2	Blocked
		3	Untested
		4	Retest
		5	Failed

	 */
	/** 
	* @Title: runTestCase 
	* @Description: TODO
	* @author alterhu2020@gmail.com
	* @param @param casename
	* @param @param result
	* @param @param rundescription
	* @param @return
	* @param @throws MalformedURLException
	* @param @throws IOException    
	* @return boolean    return type
	* @throws 
	*/ 
	
	public boolean addTestResultForTestRun(long startseconds,long runid,long caseid,int status,String version,String rundescription) throws MalformedURLException, IOException{
		
		if(this.runid!=0&&this.userid!=0){
			 String url = String.format("add_result_for_case/%d/%d",runid,caseid );
		     HashMap<Object, Object> postData = new HashMap<Object, Object>();
		    
		     postData.put("status_id", status);
		     postData.put("assignedto_id", this.userid);
		     postData.put("comment", rundescription);
		   //  String times=null;
		    // times=TimeUtils.howManySeconds(startseconds);
		   //  postData.put("elapsed",times);
		     postData.put("version", version);
		     
		     
		     JSONObject runresult=(JSONObject) client.sendPost(url, postData);
		     if(runresult!=null){
		    	 return true;
		     }
		}
		
		return false;
	       		
	}

	/** 
	* @Title: covertResultStaus 
	* @Description: TODO
	* @author alterhu2020@gmail.com
	* @param @param statusname
	* @param @return    
	* @return int    return type
	* @see http://docs.gurock.com/testrail-api2/reference-statuses
	* @throws 
	*//* 
	
	public int covertResultStaus(String statusname){
		int statuscode=3; //untested
		if(statusname.equalsIgnoreCase("passed")||statusname.equalsIgnoreCase("passed")){
			statuscode=1; //passed
		}else if(statusname.equalsIgnoreCase("fail")||statusname.equalsIgnoreCase("failed")){
			statuscode=5;  //failed
		}else{
			statuscode=3;  //untested
		}
		
	    return statuscode;
	}*/
	/** 
	* @Title: writeAutomationTestCase 
	* @Description: TODO
	* @author alterhu2020@gmail.com
	* @param @param casedescription
	* @param @param casetitle
	* @param @param stepsdescription
	* @param @param expectedresult
	* @param @throws MalformedURLException
	* @param @throws IOException    
	* @return void    return type
	* @throws 
	*/ 
	
	@SuppressWarnings("rawtypes")
	public void writeAutomationTestCase(String casedescription,
			                            String casetitle,
			                            String stepsdescription,
			                            String expectedresult,
			                            long typeid,
			                            String estimate,
			                            long priorityid,
			                            String refs,
			                            HashMap parameters) throws MalformedURLException, IOException{
		
		 long caseid=getTestCase(casetitle, this.sectionid);
		 HashMap<String, String> caseParameters = new HashMap<String, String>();
		 caseParameters.put("custom_preconds", "None");
	     caseParameters.put("custom_steps", stepsdescription);
	     caseParameters.put("custom_expected", expectedresult);
	     caseParameters.put("custom_casedesc", casedescription);
	    
		 if(caseid==0){
			 System.out.println("we had not found this case:"+casetitle+",so we will create a new one");
			 createTestCase(casetitle,
	                    typeid,
	                    estimate,
	                    priorityid,
	                    refs,  parameters);
		 }else{
			 System.out.println("we found this case created before:"+casetitle+",so we just update this case");
			 updateTestCase(casetitle,
					  typeid,
                      estimate,
                      priorityid,
                      caseid, 
                      refs,  parameters);
		 }
		
		
	}
	
	/** 
	* @Title: getTestCase 
	* @Description: TODO
	* @author alterhu2020@gmail.com
	* @param @param casename
	* @param @param sectionid
	* @param @return
	* @param @throws MalformedURLException
	* @param @throws IOException    
	* @return long    return type
	* @throws 
	*/ 
	
	public boolean getTestCaseNew(long caseid) throws MalformedURLException, IOException{
		boolean findcase=false;
		String url = String.format("get_case/%d", caseid);
		JSONObject caseobj=(JSONObject) client.sendGet(url);
		if(caseobj!=null){
			this.sectionid=Long.parseLong(caseobj.get("section_id").toString());
			this.suiteid=Long.parseLong(caseobj.get("suite_id").toString());
			findcase=true;
		}
		return findcase;
		
	}
	
	
	/** 
	* @Title: getTestCase 
	* @Description: TODO
	* @author alterhu2020@gmail.com
	* @param @param casename
	* @param @param sectionid
	* @param @return
	* @param @throws MalformedURLException
	* @param @throws IOException    
	* @return long    return type
	* @throws 
	*/ 
	
	public boolean getTestCase(long caseid) throws MalformedURLException, IOException{
		boolean findcase=false;
		String url = String.format("get_cases/%s&suite_id=%s&section_id=%d", this.projectid, this.suiteid, this.sectionid);
		JSONArray cases=(JSONArray) client.sendGet(url);
		for(int caseindex=0;caseindex<cases.size();caseindex++){
			JSONObject currentcase=(JSONObject) cases.get(caseindex);
			long findcaseid=Long.parseLong(currentcase.get("id").toString());
			if(findcaseid==caseid){
				findcase=true;
				break;
			}
		}
		return findcase;
		
	}
	
	public Long[] getAllSection(String testsuitname){
		List<Long> sectionids=new ArrayList<Long>();
		try {
			boolean testSuite = getTestSuite(testsuitname);
			if(testSuite){
				String url = String.format("get_sections/%s&suite_id=%s", this.projectid, this.suiteid);
			    JSONArray sections=(JSONArray) client.sendGet(url);
				for(int k=0;k<sections.size();k++){
					JSONObject section=(JSONObject) sections.get(k);
					sectionids.add((Long) section.get("id"));					
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//int[] arrayids = new int[sectionids.size()];
		Long[] newsections=sectionids.toArray(new Long[sectionids.size()]);
		return newsections;
	}
	
	 public void updateEnvironment(String testsuitename,List<Integer> envs){
			
		 Long[] allSection = getAllSection(testsuitename);
		  for(int k=0;k<allSection.length;k++){
			  
			  String url = String.format("get_cases/%s&suite_id=%s&section_id=%d", this.projectid, this.suiteid, allSection[k]);
			  JSONArray cases=(JSONArray) client.sendGet(url);
			  if(cases.size()>0){
				for(int caseindex=0;caseindex<cases.size();caseindex++){
					JSONObject currentcase=(JSONObject) cases.get(caseindex);
					Long caseid = (Long) currentcase.get("id");
				    String casename=(String) currentcase.get("title");
					
					
					String caseurl = String.format("update_case/%d", caseid);
			        
				    HashMap<Object, Object> postData = new HashMap<Object, Object>();
				  	/*List<Integer> envs=new ArrayList<Integer>();
				  	envs.add(1);
				  	envs.add(2);
				  	envs.add(6);*/
				    postData.put("custom_test_environments", envs); // 4 means must test
				    
				    try {
						JSONObject updatedcase=(JSONObject) client.sendPost(caseurl, postData);
						System.out.println("Update case id is:"+caseid+",case name is:"+casename+",update status is : "+updatedcase);
					  //  Log.info("Update case id is:"+caseid+",case name is:"+casename+",update status is : "+updatedcase);
				    } catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				   
				}
			  }
		  }
	}

}
