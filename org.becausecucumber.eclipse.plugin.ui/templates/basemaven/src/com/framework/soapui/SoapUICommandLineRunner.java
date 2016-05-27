package com.framework.soapui;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;

import com.framework.utilities.CommandUtils;
import com.framework.utilities.PropertiesUtils;
import com.google.common.collect.Lists;

/**
 * @ClassName: SoapUIRunner
 * @Description: TODO
 * @author ahu@greendotcorp.com
 * @date Jul 21, 2015 6:49:32 PM
 * 
 */

public class SoapUICommandLineRunner {

	private String groovydir = PropertiesUtils.getString("soapui.groovy.dir");
	private String soapuiext = PropertiesUtils.getString("soapui.ext.dir");
	private String soapuibin = PropertiesUtils.getString("soapui.bin.dir");

	private Logger log = Logger.getLogger(SoapUICommandLineRunner.class);

	private List<String> commands = Lists.newArrayList();

	public void initRunner() {
		String path = new File("").getAbsolutePath();

		String logpath = path + File.separator + "test-logs" + File.separator
				+ "soapui-logs" + File.separator;
		String outputFolder = path + File.separator + "test-result"
				+ File.separator + "cucumber-soapui-report";

		log.debug("Report Output Folder is: " + outputFolder);
		File groovydirfile = new File(groovydir);
		if (!groovydirfile.exists()) {
			log.warn("Groovy library default path: "
					+ groovydir
					+ " not found in system ,this maybe caused unexpected error.");
		}
		log.info("Groovy library default path is: " + groovydir);

		File extfile = new File(soapuiext);
		if (!extfile.exists()) {
			log.warn("SOAPUI ext library default path: "
					+ soapuiext
					+ " not found in system ,this maybe caused unexpected error.");
		}
		log.info("SOAPUI ext library default path is: " + soapuiext);

		// System.setProperty("soapui.scripting.library", groovydir);
		// SoapUI.getSettings().setString("Script Library",groovydir);

		String soapuiextlib = "-Dsoapui.ext.libraries=" + soapuiext.trim();
		String soapuilibrary = "-Dsoapui.scripting.library=" + groovydir.trim();
		String soapuilog = "-Dsoapui.logroot=" + logpath;

		// runner.setSystemProperties(optionValues);
		String settingsFile = groovydir.trim() + File.separator
				+ "soapui-settings.xml";
		File foundfile = new File(settingsFile);
		if (!foundfile.exists()) {
			settingsFile = System.getProperty("user.dir") + File.separator
					+ "soapui-settings.xml";

		}
		System.setProperty("user.dir", soapuiext);

		String binpath = soapuibin + File.separator + "testrunner.bat";

		// String reportformat="-F\"PDF, XLS, HTML, RTF, CSV, TXT, XML\"";
		String settings = "-t\"" + settingsFile + "\"";
		String ignoreerror = "-I";
		String xmlrun = "-M";
		String savetests = "-S";
		String exportall = "-a";
		String output = "-f\"" + outputFolder + "\"";
		String includecoverage = "-j";
		String openreport = "-o";
		String smallsummary = "-r";

		commands.add(binpath);

		commands.add(soapuiextlib);
		commands.add(soapuilibrary);
		commands.add(soapuilog);

		// commands.add(reportformat);
		commands.add(settings);
		commands.add(ignoreerror);
		commands.add(xmlrun);
		commands.add(savetests);
		commands.add(exportall);
		commands.add(output);
		commands.add(includecoverage);
		commands.add(openreport);
		commands.add(smallsummary);

	}

	public void runProject(String projectname) {
		initRunner();
		// String reportnamecommand="-R\"Project Report\"";
		// commands.add(reportnamecommand);
		commands.add("\"" + projectname + "\"");
		runNow();
	}

	public void runSuite(String projectname, String suitename) {
		initRunner();
		String suitecommand = "-s" + suitename;
		// String reportnamecommand="-R\"Test Suite "+suitename+" Report\"";
		// commands.add(reportnamecommand);
		commands.add(suitecommand);
		commands.add("\"" + projectname + "\"");
		runNow();
	}

	public void runTestCase(String projectname, String suitename,
			String casename) {
		initRunner();
		String suitecommand = "-s" + suitename;
		String casecommand = "-c" + casename;
		// String
		// reportnamecommand="-R\"Test Suite "+suitename+" for Test Case "+casename+" Report\"";
		// commands.add(reportnamecommand);
		commands.add(suitecommand);
		commands.add(casecommand);
		commands.add("\"" + projectname + "\"");
		runNow();
	}

	public void runNow() {
		CommandUtils.executeCommand(commands);
	}
}
