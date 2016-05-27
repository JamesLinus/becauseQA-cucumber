package com.framework.utilities;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.framework.recorder.RecordConfig;
import com.framework.recorder.ScreenRecorder;

public class WebDriverFactory {

	private static Logger log = Logger.getLogger(WebDriverFactory.class);

	private static WebDriverFactory factory;

	public static WebDriverFactory getInstance() {
		if (factory == null) {
			factory = new WebDriverFactory();
		}

		return factory;
	}

	/**
	 * @Title: deleteTestResult
	 * @Description: delete the test result
	 * @author ahu@greendotcorp.com
	 * @param
	 * @return void return type
	 * @throws
	 */

	public void deleteTestResult() {

		FilenameFilter filter = new FilenameFilter() {
			@Override
			public boolean accept(File directory, String fileName) {
				// TODO Auto-generated method stub
				return fileName.endsWith(".mp4");
			}
		};

		File resultfolder = new File(GlobalDefinition.REPORTRESULT_DIR);
		if (resultfolder.exists()) {
			File[] listFiles = resultfolder.listFiles(filter);
			if (listFiles.length >= 1) {
				for (int k = 0; k < listFiles.length; k++) {
					listFiles[k].delete();
				}
			}
			resultfolder.delete();
		}
		resultfolder.mkdir();
	}

	/**
	 * @Title: recordVideo
	 * @Description: TODO
	 * @author ahu@greendotcorp.com
	 * @param @return
	 * @return ScreenRecorder return type
	 * @throws
	 */

	public ScreenRecorder recordVideo() {
		ScreenRecorder recorder = null;
		RecordConfig recConfig = new RecordConfig();
		recConfig.setFramesRate(24);
		String DESKTOP_SCREEN_RECORDER = "Cucumber_Playback_"
				+ new SimpleDateFormat("yyyy-MM-dd-HH-mm-sss").format(Calendar
						.getInstance().getTime()) + ".mp4";
		File recorderfile = new File(GlobalDefinition.REPORTRESULT_DIR,
				DESKTOP_SCREEN_RECORDER);

		recConfig.setVideoFile(recorderfile);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		recConfig.setFrameDimension(new Rectangle(dim));
		String cursefile = WebDriverFactory.class.getClassLoader()
				.getResource(GlobalDefinition.MOUSE_CURSE_ICON).getPath();

		// System.out.println("curse file path is:"+cursefile+",filepath is:"+DESKTOP_SCREEN_RECORDER);
		try {
			recConfig.setCursorImage(ImageIO.read(new File(cursefile)));
			recorder = new ScreenRecorder();
			recorder.startRecording(recConfig);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return recorder;
	}

	/**
	 * @Title: createWebDriver
	 * @Description: TODO
	 * @author ahu@greendotcorp.com
	 * @param @param devicename
	 * @param @return
	 * @return WebDriver return type
	 * @throws
	 */

	public WebDriver createWebDriver(String devicename) {

		WebDriver driver = null;
		DesiredCapabilities capability = new DesiredCapabilities();
		SeleniumCore.browserCommonSettings(capability);
		if (devicename != null) {
			devicename = devicename.trim();
			if (devicename.equalsIgnoreCase("ie")) {
				SeleniumCore.browserIESettings(capability);
				driver = new InternetExplorerDriver(capability);
				// driver=new RemoteWebDriver(new URL(remoteAddress),
				// capability.internetExplorer());
			} else if (devicename.equalsIgnoreCase("firefox")) {
				SeleniumCore.browserFirefoxSettings(capability);
				try {
					driver = new FirefoxDriver(capability);
				} catch (WebDriverException exception) {
					if (exception.getMessage().contains(
							"Cannot find firefox binary in PATH")) {
						log.error("Current execution host:"
								+ HostUtils.getFQDN()
								+ " not installed Firefox Browser ,please install it firstly!");
					} else {
						log.error("Firefox Driver met unexpected error:"
								+ exception);
					}
					// System.exit(1);
				}
			} else if (devicename.equalsIgnoreCase("chrome")) {
				SeleniumCore.browserChromeSettings(capability);
				try {
					driver = new ChromeDriver(capability);
				} catch (WebDriverException exception) {
					if (exception.getMessage().contains(
							"unknown error: cannot find Chrome binary")) {
						log.error("Current execution host:"
								+ HostUtils.getFQDN()
								+ " not installed Chrome Browser ,please install it firstly!");
					} else {
						log.error("Chrome Driver met unexpected error:"
								+ exception);
					}
					// System.exit(1);
				}

			} else if (devicename.equalsIgnoreCase("safari")) {
				SeleniumCore.browserSafariSettings(capability);
				try {
					driver = new SafariDriver(capability);
				} catch (IllegalStateException exception) {
					if (exception
							.getMessage()
							.contains(
									"The expected Safari data directory does not exist")) {
						log.error("Current execution host:"
								+ HostUtils.getFQDN()
								+ " not installed Safari Browser ,please install it firstly!");
					} else {
						log.error("Safari Driver met unexpected error:"
								+ exception);
					}
					// System.exit(1);
				} catch (WebDriverException exception) {
					log.error("Current execution host:"
							+ HostUtils.getFQDN()
							+ " not installed Chrome Browser ,please install it firstly!");
					// System.exit(1);
				} catch (Exception e) {
					System.out.println(e);
				}
			} else if (devicename.equalsIgnoreCase("opera")) {
				SeleniumCore.browserOperaSettings(capability);
				try {
					// driver=new OperaDriver(capability);
				} catch (WebDriverException exception) {
					if (exception.getMessage().contains(
							"Runner threw exception on construction")) {
						log.error("Current execution host:"
								+ HostUtils.getFQDN()
								+ " not installed Opera Browser ,please install it firstly!");
					} else {
						log.error("Opera Driver met unexpected error:"
								+ exception);
					}
					// System.exit(1);
				}
				// emulation for chrome browser
			} else if (devicename.toLowerCase().contains("chrome")
					&& devicename.length() > 8) {
				devicename = devicename.split("-")[1];
				capability = SeleniumCore.browserChromeEmulationSettings(
						capability, devicename);
				try {
					driver = new ChromeDriver(capability);
				} catch (WebDriverException exception) {
					if (exception.getMessage().contains(
							"unknown error: cannot find Chrome binary")) {
						log.error("Current execution host:"
								+ HostUtils.getFQDN()
								+ " not installed Chrome Browser ,please install it firstly!");
					} else {
						log.error("Chrome Driver met unexpected error:"
								+ exception);
					}
					// System.exit(1);
				}
			} else if (devicename.trim().contains("iphone")) {

			} else { // default using firefox browser
				SeleniumCore.browserFirefoxSettings(capability);
				try {
					driver = new FirefoxDriver(capability);
				} catch (WebDriverException exception) {
					if (exception.getMessage().contains(
							"Cannot find firefox binary in PATH")) {
						log.error("Current execution host:"
								+ HostUtils.getFQDN()
								+ " not installed Firefox Browser ,please install it firstly!");
					} else {
						log.error("Firefox Driver met unexpected error:"
								+ exception);
					}
				}
			}
		} else {
			SeleniumCore.browserFirefoxSettings(capability);
			try {
				driver = new FirefoxDriver(capability);
			} catch (WebDriverException exception) {
				if (exception.getMessage().contains(
						"Cannot find firefox binary in PATH")) {
					log.error("Current execution host:"
							+ HostUtils.getFQDN()
							+ " not installed Firefox Browser ,please install it firstly!");
				} else {
					log.error("Firefox Driver met unexpected error:"
							+ exception);
				}
				// System.exit(1);
			}
		}

		// check the browser settings is as desired before
		Capabilities actualCapabilities = ((RemoteWebDriver) driver)
				.getCapabilities();
		String browser = actualCapabilities.getBrowserName();
		boolean emulation = (boolean) actualCapabilities
				.getCapability("mobileEmulationEnabled");
		log.info("Server Capabilities are :\n" + actualCapabilities.toString()
				+ "\nBrowser Name:" + browser + " , is mobile emulation: "
				+ emulation);
		SeleniumCore.seleniumManager(driver);

		return driver;

	}

	/**
	 * @Title: killDriverProcesses
	 * @Description: TODO
	 * @author ahu@greendotcorp.com
	 * @param @return
	 * @return boolean return type
	 * @throws
	 */

	public boolean killDriverProcesses() {
		boolean iswins = HostUtils.getOperatingSystemName().contains("Windows");
		if (iswins) {
			// kill the chromedriver.exe process
			CommandUtils.destoryWindowsProcess("chromedriver.exe");
			CommandUtils.destoryWindowsProcess("IEDriverServer.exe");
			// kill the internetexplorer.exe process
		} else {

		}
		return true;
	}

}
