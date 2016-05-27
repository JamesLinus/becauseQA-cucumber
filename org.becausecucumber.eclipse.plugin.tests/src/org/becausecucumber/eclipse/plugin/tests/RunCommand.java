package org.becausecucumber.eclipse.plugin.tests;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RunCommand {

	public static void main(String[] args) {
		
		String[] command={"cmd.exe","/c","dir"};
		//CommandUtils.newExecuteCommand("c:\\temp",command);
		
		String reg="\\\"(.*)\\\"";
		String str="The printout page's header right is \"(.*)\" logo";
		/*int flags=Pattern.DOTALL;
		Pattern p =Pattern.compile(reg,flags);
		
		Matcher matcher = p.matcher(str);
		while(matcher.find()){
			System.out.println(matcher.group(0));
		}
		*/
		String s2=str.replaceAll("\\\"(.*?)\\\"", "\" \"");
		
		System.out.println("replace is:"+s2);
	}
}
