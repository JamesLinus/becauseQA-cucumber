package org.becausecucumber.eclipse.plugin.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import java.util.Arrays;


import org.becausecucumber.eclipse.plugin.common.console.Log;

public class CommandUtils {

	public static Process p;
	public static Process proc;

	public static String executeCommand(String command) {

		StringBuffer output = new StringBuffer();

		try {
			p = Runtime.getRuntime().exec(command);
			// p.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));

			String line = "";
			while ((line = reader.readLine()) != null) {
				// output.append(line + "\n");
				System.out.println(line + "\n");
				Log.output(line + "\n");
			}

		} catch (Exception e) {
			Log.error(e + "\n");
			e.printStackTrace();
		}

		return output.toString().trim();

	}

	/*
	 * http://stackoverflow.com/questions/17038324/cannot-get-the-
	 * getinputstream- from-runtime-getruntime-exec
	 */
	public static String newExecuteCommand(String directory, String[] command) {

		StringBuffer output = new StringBuffer();
		//Log.info("Run Command: " + Arrays.toString(command));
		try {
			ProcessBuilder pb = new ProcessBuilder(command);
			if (directory != null) {
				pb.directory(new File(directory));
			}
			pb.redirectErrorStream(true);

			/* Start the process */
			proc = pb.start();
			System.out.println("Process started !");
			// p.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream(), "UTF-8"));

			String line = "";
			while ((line = reader.readLine()) != null) {
				output.append(line + "\n");
				// System.out.println("command line output is: "+line+"\n");
			}

			/* Clean-up */

		} catch (Exception e) {
			Log.error("Exception:" + e + "\n");
		}

		return output.toString().trim();

	}

	public static void stopProcess() {
		proc.destroy();
		System.out.println("Process ended !");
	}

	/**
	 * Returns the exit code of the process. If timeout passes, we return early.
	 * If forceKill is specified and timeout elapses, we will call destroy on
	 * the process before returning. Returns -1 if we were uanble to get the
	 * real exiit code.
	 * 
	 * @param process
	 * @param timeout
	 * @param forceKillAfterTimeout
	 * @return
	 */
	public static int waitForProcess(Process process, final long timeout, boolean forceKillAfterTimeout) {
		final Thread waitingThread = Thread.currentThread();
		Thread timeoutThread = new Thread() {
			public void run() {
				try {
					Thread.sleep(timeout);
					waitingThread.interrupt();
				} catch (InterruptedException ignore) {
				}
			}
		};

		int exitcode = -1;
		if (timeout > 0) {
			try {
				timeoutThread.start();
				exitcode = process.waitFor();
			} catch (InterruptedException e) {
				Thread.interrupted();
			} finally {
				timeoutThread.interrupt();
			}
			if (forceKillAfterTimeout) {
				process.destroy();
			}
		}
		try {
			exitcode = process.waitFor();
		} catch (InterruptedException e) {
		}
		return exitcode;
	}

}
