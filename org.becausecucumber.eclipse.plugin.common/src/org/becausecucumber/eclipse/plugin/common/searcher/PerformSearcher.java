package org.becausecucumber.eclipse.plugin.common.searcher;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.regex.Pattern;

import org.becausecucumber.eclipse.plugin.common.CommonPluginUtils;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.search.core.text.TextSearchEngine;
import org.eclipse.search.core.text.TextSearchMatchAccess;
import org.eclipse.search.core.text.TextSearchRequestor;
import org.eclipse.search.ui.text.FileTextSearchScope;




public class PerformSearcher {



	private Map<IFile, List<Entry>> cache = new HashMap<IFile, List<Entry>>();
	
	
	public void matcher(final String descriptions,final MatcherCollector collector){
		//long start = System.currentTimeMillis();
		cache.clear();
		/*if (!cache.isEmpty()) {
			for (List<Entry> entries : cache.values()) {
				for (Entry entry : entries) {
					if (descriptions.matches(entry.getContent())) {
						collector.foundMatcher(entry.getFile(),entry.getOffset(),entry.getLength());
					}
				}
			}
			return;
		}*/
		
		//CommonPluginUtils.getInstance();
		IProject project = CommonPluginUtils.getActiveProject();
		IResource[] roots=null;
		try {
			roots = project.members();
			if(roots==null){
				roots = ResourcesPlugin.getWorkspace().getRoot().getProjects(); 
			}
		} catch (CoreException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//IFile selectedfile =CommonPluginUtils.getActiveEditorPath();
	    boolean isjava=CommonPluginUtils.isJavaNatureProject(project);
	    String[] fileNamePatterns;
	    if(isjava){
	    	fileNamePatterns=new String[1];
	    	fileNamePatterns[0]="*.java";
	    	//fileNamePatterns[1]="*.java";
	    }else{
	    	fileNamePatterns=new String[1];
	        fileNamePatterns[0]="*.rb";
	    }
		//String[] fileNamePatterns={"*steps.rb","*steps.java","*steps.groovy","*steps.cs","*steps.py"};
	   // FileTextSearchScope.newSearchScope(rootResources, fileNamePattern, visitDerivedResources)
	    
		FileTextSearchScope scope=FileTextSearchScope.newSearchScope(roots, fileNamePatterns, false);
		//scope.
		Pattern pattern;
		
		//String desc = CommonCucumberFeatureUtils.step2Cucumber(descriptions.trim());
		//if(!isproposal){
		   // pattern=Pattern.compile("\\^"+desc+"\\$");
		pattern=Pattern.compile("\\^.*.\\$");
		//}else if(isproposal&&descriptions.trim().equals("")){
		//	pattern=Pattern.compile("\\^.*\\$");
		//}else{
		//	pattern=Pattern.compile("\\^.*\\$");
		//}
		TextSearchRequestor  requestor=new TextSearchRequestor (){
			@Override
			public boolean acceptPatternMatch(TextSearchMatchAccess matchAccess)
					throws CoreException {
				// TODO Auto-generated method stub
				IFile file = matchAccess.getFile();
				if(file.getName().contains("Samplenewbsest")){
					System.out.println("file:"+file.getName());
				}
				
				int matchOffset = matchAccess.getMatchOffset();
				int matchLength = matchAccess.getMatchLength();
				String content=matchAccess.getFileContent(matchOffset,matchLength).trim();
				
				List<Entry> entries=cache.get(file);
				if(entries==null){
					entries = new ArrayList<Entry>();
					cache.put(file, entries);
				}
				// fix this 20150107
				/*boolean isvalid = content.contains("^");
				if(isvalid){
					int startindex = content.indexOf("^");
					String validcontent = content.substring(startindex);*/
					System.out.println("description is:"+descriptions);
					entries.add(new Entry(file,matchOffset,matchLength,content));
					boolean matches=false;
					try{
						matches = descriptions.matches(content);
						
					}catch(Exception e){
						System.err.println(e);
					}
					if(matches){
						collector.foundMatcher(new Entry(file,matchOffset,matchLength,content));
					}
				//}
				
				return super.acceptPatternMatch(matchAccess);
			}
		};
		
		TextSearchEngine.create().search(scope,requestor,pattern,null);
		//System.out.println("Search Cucumber Step takes: " + (System.currentTimeMillis() - start) + "ms");
	}
	

	public List<Entry> findMatchFiles(String description) {
		
		 cache.clear();
		
		 matcher(description,new MatcherCollector() {
			
			@Override
			public void foundMatcher(Entry entry) {
				// TODO Auto-generated method stub
				
			}
		});
	   
		
		List<Entry> files = new LinkedList<Entry>();
		for (List<Entry> entries : cache.values()) {
			for (Entry entry : entries) {				
				//IFile file = entry.getFile();	
				for(Entry oldentry:files){
					if(oldentry.getFile()!=entry.getFile()){
						files.add(entry);
						break;
					}
				}
				
			}
		}
		return files;
	}	
	
	public Collection<String> findProposals(String description) {
		if (cache.isEmpty()) {
		   matcher(description,new MatcherCollector() {
			
			@Override
			public void foundMatcher(Entry entry) {
				// TODO Auto-generated method stub
				
			}
		});
	    }
		
		Collection<String> proposals = new TreeSet<String>();
		for (List<Entry> entries : cache.values()) {
			for (Entry entry : entries) {
				String proposal = entry.getContent();
				//IFile file = entry.getFile();
				if (proposal.charAt(0) == '^') {
					proposal = proposal.substring(1);
				}
				if (proposal.charAt(proposal.length() - 1) == '$') {
					proposal = proposal.substring(0, proposal.length() - 1);
				}
				proposal=proposal.replaceAll("\"\\(.*?\\)\"", "\"\"");
				// for java file defination
				//System.out.println(proposal);
				proposal=proposal.replaceAll("\\\\\"(.*?)\\\"","\"value\"");
				//System.out.println("new is:"+newproposal);
				proposals.add(proposal);
			}
		}
		return proposals;
	}	
	
	public void evict(ICompilationUnit element) {
		//TODO evict only those that match
//		if (element != null) {
//			cache.remove(element);
//			System.out.println(">>> Removed " + element.toString());
//		} else {
			cache.clear();
			System.out.println(">>> Invalidated");
//		}
	}
	
	
	public static interface MatcherCollector{
		void foundMatcher(Entry entry);
	}
	
	/*
	 * the found element content
	 */
	public static class Entry {
		//private String matchedcontent;
		public IFile file;
		public int offset;
		public int length;
		public String content;

		private Entry(IFile file,int offset,int length,String content ) {
			this.file=file;
			this.offset=offset;
			this.length=length;
			this.content=content;
		}
		
		public int getOffset() {
			return offset;
		}
		public int getLength(){
			return length;
		}
		public String getContent(){
			return content;
		}
		
		public IFile getFile(){
			return file;
		}
	}
}
