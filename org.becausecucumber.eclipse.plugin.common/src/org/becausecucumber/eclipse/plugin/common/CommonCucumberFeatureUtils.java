package org.becausecucumber.eclipse.plugin.common;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.becausecucumber.eclipse.plugin.common.console.Log;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.texteditor.ITextEditor;

public class CommonCucumberFeatureUtils {

	private CommonCucumberFeatureUtils() {
		// NO ENTRY NO INSTANCES
	}
	
    public static String step2Cucumber(String step){
    	 String tempdes=step;
    	  if(Pattern.compile("\"(.*?)\"").matcher(step).find()||
           		Pattern.compile("\"\"\"(.*?)\"\"\"").matcher(step).find()||
           		Pattern.compile("\'(.*?)\'").matcher(step).find()){
           	tempdes=step.replaceAll("\"(.*?)\"", "\"*.*\"");
           	tempdes=tempdes.replaceAll("\'(.*?)\'", "\'*.*\'");
           	tempdes=tempdes.replaceAll("\"\"\"(.*?)\"\"\"", "\"*.*\"");
           	
           }          
           //if this is a number :\d\b
           // if this is a docstr
          // tempdes=tempdes;
    	//step.
    	return tempdes;
    }
    
    
    public static String newstep2RubyDefination(String keyword,String step,boolean findtable,int count){
	   	 String tempdes=step;
	   	 //int count=0;
	   //	Matcher quote1 = Pattern.compile("\"(.*?)\"").matcher(step);
	   //	Matcher quote2 = Pattern.compile("\'(.*?)\'").matcher(step);
	   //	Matcher docstr = Pattern.compile("\"\"\"").matcher(tempdes);
	  /*  while(docstr.find()){        	
          	tempdes=tempdes.replaceAll("\"\"\"", "\""); 
          	count=count+1;
        } */
		Matcher quote1 = Pattern.compile("\"(.*?)\"").matcher(tempdes);
	   	 while(quote1.find()){
	   		tempdes=step.replaceAll("\"(.*?)\"", "\"(.*)\"");
	   		count=count+1;
	   	  }
	 	 Matcher quote2 = Pattern.compile("\'(.*?)\'").matcher(tempdes);
	   	 while(quote2.find()){
	   		    System.out.println(tempdes);
	    		tempdes=step.replaceAll("\'(.*?)\'", "\'(.*)\'");
	    		count=count+1;
	    }
	             
	          //if this is a number :\d\b
	          // if this is a docstr
	     String parameter="|";
	     for(int k=0;k<count;k++){
	    	 parameter=parameter+"params"+(k+1)+",";
	     }
	     parameter=(count==0)?"\n#pending\n\nend":parameter.substring(0, parameter.length()-1)+"|\n#pending\n\nend";
	     
	     
	     tempdes="\n"+keyword+" /^"+tempdes+"$/ do "+parameter;
	   	//step.
	   	return tempdes;
   }
   
    public static String Newstep2JavaDefination(String keyword,String step,boolean findtable,int count){
    	String tempdes=step;
	   	//int count=0;
	   	String method="";
	   	Matcher quote1 = Pattern.compile("\"(.*?)\"").matcher(step);
	   	Matcher quote2 = Pattern.compile("\'(.*?)\'").matcher(step);
	  // 	Matcher docstr = Pattern.compile("\"\"\"(.*?)\"\"\"").matcher(step);
	   	 while(quote1.find()){
	   		tempdes=step.replaceAll("\"(.*?)\"", "\"(.*)\"");
	   		count=count+1;
	   		
	   	  }
	   	 while(quote2.find()){
	   		   // System.out.println(tempdes);
	    		tempdes=step.replaceAll("\'(.*?)\'", "\'(.*)\'");
	    		count=count+1;
	    	//	method=step.replaceAll("\'(.*?)\'", " ").replaceAll("\\s*|\t|\r|\n","_");
	    }
	    /* while(docstr.find()){        	
	          	tempdes=tempdes.replaceAll("\"\"\"(.*?)\"\"\"", "\"(.*)\""); 
	          	count=count+1;
	
	      }  */
	     method=step.replaceAll("\"(.*?)\"", " ").replaceAll("\'(.*?)\'", " ").replaceAll("\"\"\"(.*?)\"\"\"", " ");
	     method=method.replaceAll("\\s|\t"," ");
	     method=method.replaceAll(" +", " ").trim().replaceAll(" ","_").replaceAll("\"", "").replaceAll("'", "");
	     
	     
	          //if this is a number :\d\b
	          // if this is a docstr
	     method=method.substring(method.length()-1)=="_"?method.substring(0, method.length()-1):method;
	     String parameter="";
	     if(findtable){
	    	 parameter=parameter+"DataTable table,";
	    	 count=count-1;
	     }
	     for(int k=0;k<count;k++){
	    	 parameter=parameter+"String params"+(k+1)+",";
	     }
	    
	     
	     parameter=(count==0)? "()  {\n\t\t//put the step definition here.\n\n\t}\n\n":
	    	 "("+parameter.substring(0, parameter.length()-1)+") {\n\t\t//put the step definition here.\n\n\t}\n\n";
	     
	     
	     tempdes="\t@"+keyword+"(\"^"+tempdes+"$\")\n"
	    	+"\tpublic void "+method+parameter;
	   	//step.
      	return tempdes;
      }
    
    public static String appendstep2JavaDefination(String keyword,String step,boolean findtable,int count){
    	String tempdes=step;
	   //	int count=0;
	   	String method="";
	   	Matcher quote1 = Pattern.compile("\"(.*?)\"").matcher(step);
	   	Matcher quote2 = Pattern.compile("\'(.*?)\'").matcher(step);
	   //	Matcher docstr = Pattern.compile("\"\"\"(.*?)\"\"\"").matcher(step);
	   	 while(quote1.find()){
	   		tempdes=step.replaceAll("\"(.*?)\"", "\"(.*)\"");
	   		count=count+1;
	   		
	   	  }
	   	 while(quote2.find()){
	   		   // System.out.println(tempdes);
	    		tempdes=step.replaceAll("\'(.*?)\'", "\'(.*)\'");
	    		count=count+1;
	    	//	method=step.replaceAll("\'(.*?)\'", " ").replaceAll("\\s*|\t|\r|\n","_");
	    }
	     /*while(docstr.find()){        	
	          	tempdes=tempdes.replaceAll("\"\"\"(.*?)\"\"\"", "\"(.*)\""); 
	          	count=count+1;
	
	      }  */
	     method=step.replaceAll("\"(.*?)\"", " ").replaceAll("\'(.*?)\'", " ").replaceAll("\"\"\"(.*?)\"\"\"", " ");
	     method=method.replaceAll("\\s|\t"," ");
	     method=method.replaceAll(" +", " ").trim();
	     method=method.replaceAll(" ","_");
	     
	          //if this is a number :\d\b
	          // if this is a docstr
	     method=method.substring(method.length()-1)=="_"?method.substring(0, method.length()-1):method;
	     String parameter="(";
	     
	     if(findtable){
    		 parameter=parameter+"DataTable table,";
    		 count=count-1;
    	 }
	     for(int k=0;k<count;k++){
	    	
	    	 parameter=parameter+"String params"+(k+1)+",";
	     }
	   
	     
	     parameter=(count==0)? "() {\n\t\t//put the step definition here.\n\n\t}\n\n":
	    	 parameter.substring(0, parameter.length()-1)+")  {\n\t\t//put the step definition here.\n\n\t}\n\n";
	     
	     
	     tempdes="\n\t@"+keyword+"(\"^"+tempdes+"$\")\n"
	    	+"\tpublic void "+method+parameter;
	   	//step.
      	return tempdes;
      }
    
	public static IProject getProject() {
		IProject activeProject=null;
		IWorkbenchPage page =  PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
				//JDIDebugUIPlugin.getActivePage();
		if (page != null) {
			IEditorPart part = page.getActiveEditor();
			if (part != null) {
				IFileEditorInput input = (IFileEditorInput) part.getEditorInput();
				IFile file = input.getFile();
				activeProject = file.getProject();
				//return activeProject;
			}
		}
		return activeProject;
	}

	public static String getFeaturePath() {
		String featurePath="";
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
				//JDIDebugUIPlugin.getActivePage();
		if (page != null) {
			IEditorPart part = page.getActiveEditor();
			if (part != null) {
				if ( part instanceof ITextEditor ) {
			        final ITextEditor editor = (ITextEditor)part;
			       // IDocumentProvider prov = editor.getDocumentProvider();
			        //IDocument doc = prov.getDocument( editor.getEditorInput() );
			        ISelection sel = editor.getSelectionProvider().getSelection();
			        if ( sel instanceof TextSelection ) {
			            final TextSelection textSel = (TextSelection)sel;
			            String selectedtext = textSel.getText();
			            IFileEditorInput input = (IFileEditorInput) part.getEditorInput();
			            String parent =input.getFile().getLocation().toString();
			            if(selectedtext==null||selectedtext.equals("")){
			            	featurePath= newFeaturefile(true,parent,selectedtext);
			            	
			            }else{
			            	featurePath= newFeaturefile(false,parent,selectedtext);
			            	
			            }
			        }
			    }
				
			}
		}
		return featurePath;
	}
	
	public static String getFeatureFolder() {
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
				//JDIDebugUIPlugin.getActivePage();
		if (page != null) {
			IEditorPart part = page.getActiveEditor();
			if (part != null) {
				IFileEditorInput input = (IFileEditorInput) part.getEditorInput();
				return input.getFile().getParent().getLocation().toOSString();
			}
		}
		return null;
	}


	protected static String getRubyPath(){
	   String rubypath=CommonPluginUtils.getRubywPath();
		return rubypath;
	}
	
	public static void updateFromConfig(ILaunchConfiguration config, String attrib, Text text) {
		String s = "";
		try {
			s = config.getAttribute(attrib, "");
		} catch (CoreException e) {
			e.printStackTrace();
		}
		text.setText(s);
	}
	
	public static boolean updateFromConfig(ILaunchConfiguration config, String attrib) {
		boolean b = false;
		try {
			b = config.getAttribute(attrib, b);
		} catch (CoreException e) {
			e.printStackTrace();
		}
		return b;
	}
	
	@SuppressWarnings("unused")
	private TextSelection getTextSelection() {
		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		ISelectionService service = window.getSelectionService();
		
		if (service instanceof TextSelection) return  (TextSelection)  service.getSelection();
		else return null;
	}
	
	  private static String newFeaturefile(boolean isfullfile,String parent,String selectedtext){

			/*@Override
			public void selectionChanged(IWorkbenchPart part, ISelection selection) {
				// TODO Auto-generated method stub
				if(selection instanceof TextSelection){
					final TextSelection textselection = (TextSelection)selection;
					String selectedtext = textselection.getText();*/
		            String parentpath=new File(parent).getParent();
		          //  String filename="temp_"+new SimpleDateFormat("HHmmSSS").format(Calendar.getInstance().getTime())+".feature";
					String userdir =parentpath+File.separator+"temp.feature";
					File tempfile = new File(userdir);
					if(tempfile.exists()){
						Log.info( "Found file : "+userdir+",will delete it.");
						tempfile.delete();
					}
					try {
						boolean iscreated=tempfile.createNewFile();
						//Log.info( "Create new file: "+iscreated+",will write the file content then. ");
						if(iscreated){
							Writer writer = null;

							try {
							    writer = new BufferedWriter(new OutputStreamWriter(
							          new FileOutputStream(userdir), "utf-8"));
							    if(!isfullfile){
							      writer.write("Feature: This file is used for exeuction\n");
							      writer.write(selectedtext);
							    }
							    
							} catch (IOException ex) {
							  // report
								Log.error( "write file exception: "+ex);
							} finally {
							   try {writer.close();} catch (Exception ex) {Log.error( "write file exception: "+ex);}
							}
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						Log.error( "write file exception: "+e);
					}
			return userdir;

		 }


}
