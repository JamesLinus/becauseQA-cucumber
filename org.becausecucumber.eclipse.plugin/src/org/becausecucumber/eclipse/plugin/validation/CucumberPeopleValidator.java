package org.becausecucumber.eclipse.plugin.validation;

import java.util.ArrayList;
import java.util.List;

import org.becausecucumber.eclipse.plugin.common.searcher.PerformSearcher;
import org.becausecucumber.eclipse.plugin.common.searcher.PerformSearcher.Entry;
import org.becausecucumber.eclipse.plugin.cucumber.CucumberPackage;
import org.becausecucumber.eclipse.plugin.cucumber.Step;

import org.eclipse.xtext.validation.Check;

import com.google.inject.Inject;

public class CucumberPeopleValidator extends CucumberJavaValidator {

	
	public final static String NO_DEFINATION_STEP="NDS";
	public final static String MULTILE_STEP="MS";

	
	@Inject
	private PerformSearcher matcher;

	@Check
	public void checkStepMatching(Step step) {
		String descriptions = step.getDescription().trim();
	    System.out.println("step check is :"+descriptions);
		final CucumberCounter counter=new CucumberCounter();
		matcher.matcher(descriptions,counter);
		int size = counter.getNumbers();
		List<Entry> files = counter.getFiles();
		if(size==0){
         	warning("No Cucumber Step Definition Found.", 
						CucumberPackage.Literals.STEP__DESCRIPTION,NO_DEFINATION_STEP,descriptions);
         }else if(size>1){
        	String filepath="";
        	for(Entry file:files){
        		filepath=filepath+file.getFile().getFullPath().toOSString()+"\n";
        	}
         	error("found "+size+" Step Definition in these files.\n"+filepath, 
						CucumberPackage.Literals.STEP__DESCRIPTION,MULTILE_STEP,descriptions);
         }else{
         	System.out.println("change the warning or error status to success validation status");
         	//info("good is at we are" + description, 
					//	CucumberPackage.Literals.STEP__DESCRIPTION,MULTILE_STEP,description);
         	
         }
	}

    private final static class CucumberCounter implements PerformSearcher.MatcherCollector{

	   private int count = 0;
	   private List<Entry> files=new ArrayList<Entry>();
		
		public int getNumbers() {
			return count;
		}
		public List<Entry> getFiles(){
			return files;
		}
		
		@Override
		public void foundMatcher(Entry entry) {
			// TODO Auto-generated method stub
			count++;
			files.add(entry);
		}
    	
    }
}
