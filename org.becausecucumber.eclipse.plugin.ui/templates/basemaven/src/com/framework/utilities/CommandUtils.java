package com.framework.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.common.collect.Lists;

public class CommandUtils {

	private static final Logger logger = Logger.getLogger(CommandUtils.class);

	/**
	 * not recommand
	 * 
	 * @Title: executeCommand
	 * @Description: TODO
	 * @author alterhu2020@gmail.com
	 * @param @param command
	 * @param @return
	 * @return String return type
	 * @throws
	 * @example: Runtime.getRuntime().exec(
	 *           "runas /profile /user:Administrator /savecred \"cmd.exe /c Powrprof.dll,SetSuspendState\""
	 *           );
	 */

	public static String executeCommand(String command) {

		StringBuffer output = new StringBuffer();

		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					p.getInputStream()));

			String line = "";
			while ((line = reader.readLine()) != null) {
				output.append(line + "\n");
			}
			// logger.info("Command output is:"+line);

		} catch (Exception e) {
			System.out
					.println("exception happened running command - here's what I know: ");
			e.printStackTrace();
			System.exit(-1);
		}

		return output.toString();

	}

	/**
	 * not recommand
	 * 
	 * @Title: executeCommand
	 * @Description: TODO
	 * @author alterhu2020@gmail.com
	 * @param @param command
	 * @param @return
	 * @return String return type
	 * @throws
	 * @example: Runtime.getRuntime().exec(
	 *           "runas /profile /user:Administrator /savecred \"cmd.exe /c Powrprof.dll,SetSuspendState\""
	 *           );
	 */

	public static String runCommandwithAdminstrator(String command) {

		StringBuffer output = new StringBuffer();

		Process p;
		try {
			p = Runtime.getRuntime().exec(
					"cmd.exe /c echo password123 | runas /profile /user:Administrator /savecred \""
							+ command + "\"");
			p.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					p.getInputStream()));

			String line = "";
			while ((line = reader.readLine()) != null) {
				output.append(line + "\n");
			}
			logger.info("Command output is:" + line);

		} catch (Exception e) {
			System.out
					.println("exception happened running command - here's what I know: ");
			e.printStackTrace();
			System.exit(-1);
		}

		return output.toString();

	}

	public static String executeCommand(List<String> command) {

		StringBuffer output = new StringBuffer();
		Process process = null;
		try {
			ProcessBuilder pb = new ProcessBuilder(command);
			logger.info("command input is:" + command.toString());
			process = pb.start();
			// process.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					process.getInputStream()));

			String line = "";
			while ((line = reader.readLine()) != null) {
				output.append(line + "\n");
				logger.info(line);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out
					.println("exception happened running command - here's what I know: ");
			e.printStackTrace();
			System.exit(-1);
		}
		return output.toString();
	}

	public static String destoryWindowsProcess(String processname) {

		StringBuffer output = new StringBuffer();
		Process process = null;
		try {
			List<String> command = Lists.newArrayList();
			command.add("taskkill.exe");
			command.add("/F");
			command.add("/IM");
			command.add(processname);
			logger.info("Destory Process Command>>> " + command.toString()
					+ " !");
			ProcessBuilder pb = new ProcessBuilder(command);

			process = pb.start();
			Thread.sleep(1000);
			process.destroy();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					process.getInputStream()));

			String line = "";
			while ((line = reader.readLine()) != null) {
				output.append(line + "\n");
				logger.info(line);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out
					.println("exception happened running command - here's what I know: ");
			e.printStackTrace();
			System.exit(-1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return output.toString();
	}

}
