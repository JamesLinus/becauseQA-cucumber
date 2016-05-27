package com.framework.main;

import com.framework.recovery.RecoveryScenario;

public class RunTests {

	private static Thread run;
	public static String propertiesfilename = null;

	private RecoveryScenario rs = new RecoveryScenario();

	/*
	 * @SuppressWarnings("static-access") public static void main(String[] args)
	 * {
	 * 
	 * if(args.length>=1){ propertiesfilename=args[0].trim();
	 * if(propertiesfilename.endsWith(".properties")){ int
	 * filestart=propertiesfilename.indexOf(".properties");
	 * propertiesfilename=propertiesfilename.substring(0, filestart); } }else{
	 * System.err.println(
	 * "You need to initize a parameter for the property file loading\n" +
	 * "if running from pom.xml ,add a parameter from the command line: -Dconfig.filename=dev.properties,\n"
	 * +
	 * "if running just from current main class,you can add a argument like dev"
	 * ); System.exit(1); } ExecutionThread thread=new ExecutionThread(); run =
	 * new Thread(thread,"TestNG Thread Start...");
	 * run.setDefaultUncaughtExceptionHandler(new RecoveryScenario());
	 * run.start(); }
	 */
	@SuppressWarnings("static-access")
	public void runNow(String[] args) {
		if (args.length >= 1) {
			propertiesfilename = args[0].trim();
			if (propertiesfilename.endsWith(".properties")) {
				int filestart = propertiesfilename.indexOf(".properties");
				propertiesfilename = propertiesfilename.substring(0, filestart);
			}
		} else {
			System.err
					.println("You need to initize a parameter for the property file loading\n"
							+ "if running from pom.xml ,add a parameter from the command line: -Dconfig.filename=dev.properties,\n"
							+ "if running just from current main class,you can add a argument like dev");
			System.exit(1);
		}
		ExecutionThread thread = new ExecutionThread();
		run = new Thread(thread, "TestNG Thread Start...");
		run.setDefaultUncaughtExceptionHandler(this.getRecovery());
		;
		run.start();

	}

	public RecoveryScenario getRecovery() {
		return rs;
	}

	public void setRecovery(RecoveryScenario rs) {
		this.rs = rs;
	}

}
