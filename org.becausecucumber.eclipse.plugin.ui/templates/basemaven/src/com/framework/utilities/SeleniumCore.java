package com.framework.utilities;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.apache.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;

import com.jacob.com.LibraryLoader;
import com.sun.jna.Platform;

/**
 * @ClassName: SeleniumCore
 * @Description: TODO
 * @author alterhu2020@gmail.com
 * @date Apr 9, 2014 5:56:46 PM
 * 
 */

public class SeleniumCore {

	private static Logger logger = Logger.getLogger(SeleniumCore.class);


	/**
	 * @Title: registerAutoItX
	 * @Description: Only for windows operation system,not for linux or mac
	 * @author alterhu2020@gmail.com
	 * @param
	 * @return void return type
	 * @throws
	 */

	public static boolean registerAutoItX() {

		String autoitx64 = "AutoItX3_x64.dll";
		String autoitx86 = "AutoItX3.dll";
		String jacobfilex64 = "jacob-x64.dll";
		String jacobfilex86 = "jacob.dll";
		List<String> regcommand = new ArrayList<String>();
		String jacobdll = null;
		String jacobdllpath = null;
		String autoitxdll = null;
		String commandline = null;
		if (Platform.isWindows() && Platform.is64Bit()) {
			commandline = "C:\\Windows\\SysWOW64\\";
			autoitxdll = new File(SeleniumCore.class.getClassLoader()
					.getResource(autoitx64).getPath()).getAbsolutePath();
			File jacobfile = new File(SeleniumCore.class.getClassLoader()
					.getResource(jacobfilex64).getPath());
			jacobdll = jacobfile.getName();
			jacobdllpath = jacobfile.getAbsolutePath();
			System.setProperty(LibraryLoader.JACOB_DLL_PATH, jacobdllpath);
			System.setProperty(LibraryLoader.JACOB_DLL_NAME, jacobdll);
		} else if (Platform.isWindows() && (!Platform.is64Bit())) {
			commandline = "C:\\Windows\\System32\\";
			autoitxdll = new File(SeleniumCore.class.getClassLoader()
					.getResource(autoitx86).getPath()).getAbsolutePath();
			File jacobfile = new File(SeleniumCore.class.getClassLoader()
					.getResource(jacobfilex86).getPath());
			jacobdll = jacobfile.getName();
			jacobdllpath = jacobfile.getAbsolutePath();
			System.setProperty(LibraryLoader.JACOB_DLL_PATH, jacobdllpath);
			System.setProperty(LibraryLoader.JACOB_DLL_NAME, jacobdll);
		} else {
			logger.error("we cannot register the autoitx library as it's not the windows operation system!");
		}

		/*
		 * regcommand.add(commandline+"runas.exe"); regcommand.add("/profile");
		 * regcommand.add("/user:Administrator"); regcommand.add("/savecred");
		 */

		regcommand.add(commandline + "regsvr32.exe");
		regcommand.add("/s");
		regcommand.add(autoitxdll);

		CommandUtils.executeCommand(regcommand);
		/*
		 * regcommand.clear(); regcommand.add(regtool); regcommand.add("/s");
		 * regcommand.add(jacobdll);
		 */

		System.load(jacobdllpath);
		try {
			//LibraryLoader.loadJacobLibrary();
		} catch (UnsatisfiedLinkError e) {
			System.load(jacobdllpath);
		}

		return true;
	}

	/**
	 * getBrowserType:(get the current running browser type and version number).
	 * 
	 * @param driver
	 *            ---the web driver instance
	 * 
	 * @since JDK 1.6
	 * @return String --- the browser type and version number
	 */
	public static String getBrowserType(WebDriver driver) {
		Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
		String browserName = caps.getBrowserName();
		String browserVersion = caps.getVersion();
		// String platform=caps.getPlatform().toString();
		logger.info("Get the current running browser is:" + browserName
				+ ",the browser version is:" + browserVersion);
		return browserName + " " + browserVersion;
	}

	/**
	 * @Title: browserCommonSettings
	 * @Description: TODO
	 * @author alterhu2020@gmail.com
	 * @param @param capability
	 * @return void return type
	 * @throws
	 */

	public static void browserCommonSettings(DesiredCapabilities capability) {
		capability.setCapability("cssSelectorsEnabled", true);
		capability.setCapability("takesScreenshot", true);
		capability.setCapability("javascriptEnabled", true);
		capability.setCapability("ignoreZoomSetting", true);
		capability.setCapability("ignoreProtectedModeSettings", true);
		capability.setCapability("enablePersistentHover", false); // prevent
		capability.setCapability("EnableNativeEvents", false);

		capability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		capability.setCapability("acceptSslCerts", true); // accept the securty
															// ssl url
		capability.setCapability("unexpectedAlertBehaviour", "accept");
		
		
		// log 
		LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        capability.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

	}

	/**
	 * @Title: browserProxySettings
	 * @Description: TODO
	 * @author alterhu2020@gmail.com
	 * @param @param capability
	 * @param @param proxysettings
	 * @return void return type
	 * @throws
	 */

	public static void browserProxySettings(DesiredCapabilities capability,
			String proxysettings) {
		org.openqa.selenium.Proxy httpproxy = new org.openqa.selenium.Proxy();
		httpproxy.setHttpProxy(proxysettings);
		httpproxy.setSslProxy(proxysettings);

		httpproxy.setNoProxy("localhost");
		capability.setCapability(CapabilityType.PROXY, httpproxy);
	}

	/**
	 * @Title: BrowserIESettings
	 * @Description: TODO
	 * @author alterhu2020@gmail.com
	 * @param @param capability
	 * @return void return type
	 * @throws
	 * @refer 
	 *        http://selenium.googlecode.com/git/docs/api/java/org/openqa/selenium
	 *        /ie/InternetExplorerDriver.html
	 */

	public static void browserIESettings(DesiredCapabilities capability) {

		ClassLoader loader = SeleniumCore.class.getClassLoader();
		String iedriver = null;
		if (System.getProperties().getProperty("os.arch").toLowerCase()
				.equals("x86_64")
				|| System.getProperties().getProperty("os.arch").toLowerCase()
						.equals("amd64")) {
			iedriver = loader.getResource("iedriver/win64/IEDriverServer.exe")
					.getFile();
		} else {
			iedriver = loader.getResource("iedriver/win32/IEDriverServer.exe")
					.getFile();
		}

		System.setProperty("webdriver.ie.driver", iedriver);
		logger.info("Set IE Driver in this location:" + iedriver);
		// SSL url setting
		capability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		capability.setCapability(
				InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, true);
		capability.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
		capability.setCapability("requireWindowFocus", true);
		/*
		 * should set to true :
		 * http://jimevansmusic.blogspot.com/2012/08/youre-doing
		 * -it-wrong-protected-mode-and.html
		 */
		capability
				.setCapability(
						InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
						true);
		capability.setCapability(InternetExplorerDriver.LOG_LEVEL, "TRACE");
		capability.setCapability(InternetExplorerDriver.LOG_FILE,
				GlobalDefinition.Log4J_LOG_DIR);
		// clear session
		capability.setCapability(
				InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);

	}

	/**
	 * set the firefox profile ,like download file automatically .
	 * 
	 * 
	 * @return FirefoxProfile
	 * @throws IOException
	 * 
	 * */
	public static FirefoxProfile browserFirefoxSettings(
			DesiredCapabilities capability) {
		FirefoxProfile fp = new FirefoxProfile();
		// fp.addExtension(extensionToInstall);
		// http://stackoverflow.com/questions/15292972/auto-download-pdf-files-in-firefox
		// http://www.webmaster-toolkit.com/mime-types.shtml
		// for config list see this :
		// http://kb.mozillazine.org/About:config_entries#Profile.
		fp.setAcceptUntrustedCertificates(true);
		fp.setAssumeUntrustedCertificateIssuer(true);
		fp.setEnableNativeEvents(false);
		// fp.setProxyPreferences(proxy);
		// for the download bar to automatically download it

		fp.setPreference("browser.download.manager.showWhenStarting", false);
		String downloaddir = GlobalDefinition.BROWSER_DOWNLOAD_DIR;
		fp.setPreference("browser.download.useDownloadDir", true);
		fp.setPreference("browser.download.dir", downloaddir);
		fp.setPreference("browser.download.lastDir", downloaddir);
		fp.setPreference("browser.download.folderList", 2);

		// fp.setPreference("browser.helperApps.alwaysAsk.force", false);
		fp.setPreference("security.default_personal_cert",
				"Select Automatically");
		fp.setPreference(
				"browser.helperApps.neverAsk.saveToDisk",
				"application/octet-stream,application/x-compressed,application/x-zip-compressed,application/zip,multipart/x-zip");
		// File sslerror=new
		// File(SeleniumCore.getProjectWorkspace()+"resources"+File.separator+"remember_certificate_exception-1.0.0-fx.xpi");
		// fp.addExtension(sslerror);

		// the submits HTTP authentication dialogs ,your proxy is blocking the
		// WebSocket protocol
		fp.setPreference("network.websocket.enabled", false);
		fp.setPreference("signon.autologin.proxy", true);
		// this is the popup show url
		fp.setPreference("network.automatic-ntlm-auth.trusted-uris", ""
				+ "paypal-attheregister.com," + "attheregister.com,"
				+ "moneypak.com," + "nextestate.com," + "necla");
		fp.setPreference("network.automatic-ntlm-auth.allow-proxies", true);
		fp.setPreference("network. negotiate-auth. allow-proxies", true);
		fp.setPreference("browser.ssl_override_behavior", 1);
		fp.setPreference("security.fileuri.strict_origin_policy", false);
		fp.setPreference("security. default_personal_cert",
				"Select Automatically");
		capability.setCapability(FirefoxDriver.PROFILE, fp);
		// logger.info("Had set the firefox profile for current selenium run session");

		return fp;
	}

	/**
	 * @Title: browserChromeSettings
	 * @Description: TODO
	 * @author alterhu2020@gmail.com
	 * @param @return
	 * @return ChromeOptions return type
	 * @throws
	 */

	public static ChromeOptions browserChromeSettings(
			DesiredCapabilities capability) {

		String chromedriver = null;
		/* System.setProperty("webdriver.chrome.driver",chromedriver); */

		ClassLoader loader = SeleniumCore.class.getClassLoader();

		// URL resource =
		// loader.getResource("chromedriver/win32/chromedriver.exe");
		// getClass().getProtectionDomain().getCodeSource().getLocation()

		if (System.getProperties().getProperty("os.arch").toLowerCase()
				.equals("x86_64")
				|| System.getProperties().getProperty("os.arch").toLowerCase()
						.equals("amd64")) {
			if (System.getProperties().getProperty("os.name").toLowerCase()
					.contains("windows")) {
				chromedriver = loader.getResource(
						"chromedriver/win32/chromedriver.exe").getFile();
			} else if (System.getProperties().getProperty("os.name")
					.toLowerCase().contains("mac")) {
				chromedriver = loader.getResource(
						"chromedriver/mac32/chromedriver").getFile();
			} else if (System.getProperties().getProperty("os.name")
					.toLowerCase().contains("linux")) {
				chromedriver = loader.getResource(
						"chromedriver/linux64/chromedriver").getFile();
			}
		} else { // 32 BIT
			if (System.getProperties().getProperty("os.name").toLowerCase()
					.contains("windows")) {
				chromedriver = loader.getResource(
						"chromedriver/win32/chromedriver.exe").getFile();
			} else if (System.getProperties().getProperty("os.name")
					.toLowerCase().contains("mac")) {
				chromedriver = loader.getResource(
						"chromedriver/mac32/chromedriver").getFile();
			} else if (System.getProperties().getProperty("os.name")
					.toLowerCase().contains("linux")) {
				chromedriver = loader.getResource(
						"chromedriver/linux32/chromedriver").getFile();
			}
		}
		System.setProperty("webdriver.chrome.driver", chromedriver);
		logger.info("Chrome Driver location: " + chromedriver);
		Map<String, Object> prefs = new HashMap<String, Object>();

		prefs.put("profile.default_content_settings.popups", 0);
		String downloaddir = GlobalDefinition.BROWSER_DOWNLOAD_DIR;
		prefs.put("download.default_directory", downloaddir);
		prefs.put("download.directory_upgrade", true);
		prefs.put("download.extensions_to_open", "");

		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setExperimentalOption("prefs", prefs);
		chromeOptions.addArguments("start-maximized");
		chromeOptions.addArguments("--always-authorize-plugins=true");
		chromeOptions.addArguments("allow-running-insecure-content");

		capability.setCapability("chrome.switches",
				Arrays.asList("--no-default-browser-check"));
		capability.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
		logger.debug("the chrome Configuration driver path is:" + chromedriver);
		return chromeOptions;

	}

	public static DesiredCapabilities browserChromeEmulationSettings(
			DesiredCapabilities desiredCapabilities, String devicename) {

		String chromedriver = null;
		/* System.setProperty("webdriver.chrome.driver",chromedriver); */

		ClassLoader loader = SeleniumCore.class.getClassLoader();

		// URL resource =
		// loader.getResource("chromedriver/win32/chromedriver.exe");
		// getClass().getProtectionDomain().getCodeSource().getLocation()

		if (System.getProperties().getProperty("os.arch").toLowerCase()
				.equals("x86_64")
				|| System.getProperties().getProperty("os.arch").toLowerCase()
						.equals("amd64")) {
			if (System.getProperties().getProperty("os.name").toLowerCase()
					.contains("windows")) {
				chromedriver = loader.getResource(
						"chromedriver/win32/chromedriver.exe").getFile();
			} else if (System.getProperties().getProperty("os.name")
					.toLowerCase().contains("mac")) {
				chromedriver = loader.getResource(
						"chromedriver/mac32/chromedriver").getFile();
			} else if (System.getProperties().getProperty("os.name")
					.toLowerCase().contains("linux")) {
				chromedriver = loader.getResource(
						"chromedriver/linux64/chromedriver").getFile();
			}
		} else { // 32 BIT
			if (System.getProperties().getProperty("os.name").toLowerCase()
					.contains("windows")) {
				chromedriver = loader.getResource(
						"chromedriver/win32/chromedriver.exe").getFile();
			} else if (System.getProperties().getProperty("os.name")
					.toLowerCase().contains("mac")) {
				chromedriver = loader.getResource(
						"chromedriver/mac32/chromedriver").getFile();
			} else if (System.getProperties().getProperty("os.name")
					.toLowerCase().contains("linux")) {
				chromedriver = loader.getResource(
						"chromedriver/linux32/chromedriver").getFile();
			}
		}
		System.setProperty("webdriver.chrome.driver", chromedriver);
		logger.info("Chrome Driver location: " + chromedriver);

		// desiredCapabilities.setCapability("mobileEmulationEnabled", true);

		Map<String, String> mobileEmulation = new HashMap<String, String>();
		mobileEmulation.put("deviceName", devicename);

		Map<String, Object> prefs = new HashMap<String, Object>();
		/*
		 * prefs.put("profile.default_content_settings.popups", 0); String
		 * downloaddir = GlobalDefinition.BROWSER_DOWNLOAD_DIR;
		 * prefs.put("download.default_directory", downloaddir);
		 * prefs.put("download.directory_upgrade", true);
		 * prefs.put("download.extensions_to_open", false);
		 */
		prefs.put("mobileEmulation", mobileEmulation);

		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		options.addArguments("start-maximized");
		options.addArguments("--test-type");
		options.addArguments("--disable-translate");
		options.addArguments("ignore-certificate-errors");
		options.addArguments("allow-running-insecure-content");
		// options.addArguments("mobileEmulation",
		// String.valueOf(mobileEmulation));

		desiredCapabilities = DesiredCapabilities.chrome();

		desiredCapabilities.setCapability("chrome.switches",
				Arrays.asList("--no-default-browser-check"));
		desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, prefs);

		logger.debug("the chrome Configuration driver path is:" + chromedriver);
		return desiredCapabilities;

	}

	public static void browserSafariSettings(DesiredCapabilities capability) {
		SafariOptions option = new SafariOptions();
		option.setUseCleanSession(true);
		// option.setDriverExtension(driverExtension);
		capability.setCapability("safari.cleanSession", true);
		System.setProperty("webdriver.safari.noinstall", "true");
	}

	public static void browserOperaSettings(DesiredCapabilities capability) {
		capability.setCapability("opera.arguments", "-nowin -nomail");

	}

	/**
	 * @Title: seleniumManager
	 * @Description: TODO
	 * @author alterhu2020@gmail.com
	 * @param @param driver
	 * @return void return type
	 * @throws
	 */

	public static void seleniumManager(WebDriver driver) {
		try {
			driver.manage().deleteAllCookies();

			// page load time
			driver.manage()
					.timeouts()
					.pageLoadTimeout(GlobalDefinition.PAGE_LOADING_TIME,
							TimeUnit.SECONDS);
			// the web element to find time we need to wait
			driver.manage()
					.timeouts()
					.implicitlyWait(GlobalDefinition.WEBELEMENT_LOADING_TIME,
							TimeUnit.SECONDS);
			// the js executor timeout
			driver.manage()
					.timeouts()
					.setScriptTimeout(GlobalDefinition.JS_TIMEOUT,
							TimeUnit.SECONDS);

			driver.manage().window().maximize();

		} catch (TimeoutException e) {
			logger.error("The page or the webelment had been waited for 120 second,it cannot showed ,so the test failed"
					+ ",Exception:" + e.getMessage());
			CucumberAssert
					.fail("Current Web Page, webelment or JAVAScript loading timeout");
		}

	}

}