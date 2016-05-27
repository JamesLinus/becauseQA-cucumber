package org.becausecucumber.eclipse.plugin.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class CommonFileUtils {

	/*
	 * get the string from the file between two lines
	 */
	public static  String getStringFromLine(File file,int startline,int endline){
		int starter,ender;
		if(startline<=endline){
			starter=startline;
			ender=endline;
		}else{
			starter=endline;
			ender=startline;
		}
		BufferedReader reader=null;
	    StringBuffer sb=new StringBuffer();
		int currentline=0;
		boolean isbegin=false;
		boolean continuefind=true;
		 try {
				reader = new BufferedReader(new FileReader(file));
				String temp="";
				while((temp=reader.readLine())!= null&&currentline<=ender){
					currentline=currentline+1;
					
					if(currentline==starter){
						isbegin=true;
					}
					if(isbegin&&(!temp.trim().startsWith("#"))){
						temp=temp+"\n";
						sb.append(temp);
					}
					if(currentline==ender){
						isbegin=false;
						continuefind=false;
					}
					if(!continuefind){
						break;
					}
					
				}
				
		 }catch(Exception e){
			 
		 }finally{
			 try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		return sb.toString();
	}
	
	public void readFileWithLineNumber(String filepath,String containstr){
		
	}
	
	
	/**
	 * Creates a temporary subdirectory in the standard temporary directory.
	 * This will be automatically deleted upon exit.
	 * 
	 * @param prefix
	 *            the prefix used to create the directory, completed by a
	 *            current timestamp. Use for instance your application's name
	 * @return the directory
	 */

	
}
