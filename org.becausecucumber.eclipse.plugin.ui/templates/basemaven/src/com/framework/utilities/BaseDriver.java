package com.framework.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.framework.recorder.ScreenRecorder;
import com.framework.testcase.testrail.TestRailAPI;
import com.framework.testcase.testrail.TestRailImpl;

/**
 * @ClassName: BaseDriver
 * @Description: TODO
 * @author ahu@greendotcorp.com
 * @date Jun 29, 2015 1:26:43 AM
 * 
 */

public abstract class BaseDriver {

	public static WebDriver driver;
	@Rule
	public ScreenshotTestRule screenshotTestRule = new ScreenshotTestRule();

	private static ScreenRecorder recorder;
	private static CucumberReporter report;
	private static String starttime;
	private static WebDriverFactory factory;
	protected static final Logger log = Logger.getLogger(BaseDriver.class);

	//public abstract void initEnvironment();
	/**
	 * @Title: setupDriver
	 * @Description: TODO
	 * @author ahu@greendotcorp.com
	 * @param
	 * @return void return type
	 * @throws
	 */

	@Before
	public void setupDriver() {
		String browsername = PropertiesUtils.getEnv("BROWSER_TYPE");
		factory = WebDriverFactory.getInstance();
		factory.killDriverProcesses();
		factory.deleteTestResult();
		driver = factory.createWebDriver(browsername);

		//register the autoitx 
		SeleniumCore.registerAutoItX();
		String needrecord = PropertiesUtils.getEnv("RECORD_VIDEO");
		if (needrecord != null) {
			if (needrecord.equalsIgnoreCase("true")) {
				//recorder = factory.recordVideo();
			}
		}

	}

	/**
	 * @Title: teardownDriver
	 * @Description: TODO
	 * @author ahu@greendotcorp.com
	 * @param
	 * @return void return type
	 * @throws
	 */

	@After
	public void teardownDriver() {
		// existTests();
		log.info("BaseDriver Teardown the driver...");
		if (recorder != null) {
			/*try {
				recorder.stopRecording();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		}
		generateEmailAndExitTests();
	}

	/**
	 * @Title: run
	 * @Description: TODO
	 * @author ahu@greendotcorp.com
	 * @param @param classzz
	 * @return void return type
	 * @throws
	 */

	@SuppressWarnings("rawtypes")
	public void run(Class classzz) {
		starttime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar
				.getInstance().getTime());
		report = new CucumberReporter();
		boolean findplan = report.initTestCaseTool();
		if (findplan) {
			report.cucumberRunner(classzz, report);
		}
		
		generateEmailAndExitTests();
	}

	/**
	 * @ClassName: ScreenshotTestRule
	 * @Description: TODO
	 * @author ahu@greendotcorp.com
	 * @date Jun 29, 2015 1:26:15 AM
	 * 
	 */

	class ScreenshotTestRule implements MethodRule {

		public Statement apply(final Statement statement,
				final FrameworkMethod frameworkMethod, final Object o) {
			return new Statement() {
				@Override
				public void evaluate() throws Throwable {
					try {
						statement.evaluate();
					} catch (Throwable t) {
						// t.printStackTrace();

						log.error("JUnit Capture Screenshot Invoke: "+t.getLocalizedMessage());
						// log.error("Framework Exception Catched,will Capture the error scenario screenshot now ......"
						// + frameworkMethod.getName());
						captureScreenshot(frameworkMethod.getName());
						if (!t.getLocalizedMessage().contains("AssertionError")) {
							generateEmailAndExitTests();
						}

						// throw t;
					}
				}

			};
		}
	}

	/**
	 * @Title: captureScreenshot
	 * @Description: TODO
	 * @author ahu@greendotcorp.com
	 * @param @param fileName
	 * @return void return type
	 * @throws
	 */

	public static void captureScreenshot(String fileName) {

		new File(GlobalDefinition.REPORTRESULT_DIR).mkdirs(); // Insure

		String shorttime = new SimpleDateFormat("yyyyMMddHHmmsss")
				.format(Calendar.getInstance().getTime());
		FileOutputStream out;
		try {
			byte[] screenshotAs = ((TakesScreenshot) driver)
					.getScreenshotAs(OutputType.BYTES);
			report.embedding("image/png", screenshotAs);
			out = new FileOutputStream(GlobalDefinition.REPORTRESULT_DIR
					+ File.separator + "screenshot-" + fileName + "-"
					+ shorttime + ".png");

			out.write(screenshotAs);
			out.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WebDriverException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @Title: existTests
	 * @Description: TODO
	 * @author ahu@greendotcorp.com
	 * @param
	 * @return void return type
	 * @throws
	 */

	public static void generateEmailAndExitTests() {

		int passCount = report.getPassCount().intValue();
		int failedCount = report.getFailureCount().intValue();
		int skipCount = report.getSkipCount().intValue();

		int spassCount = report.getSpassCount().intValue();
		int sfailedCount = report.getSfailureCount().intValue();
		int sskipCount = report.getSskipCount().intValue();

		int totalsteps = passCount + failedCount + skipCount;
		int totalscenarios = spassCount + sfailedCount + sskipCount;

		String consoleurl = report.getBuildurl() + "console";
		String cucumberreportpath = report.getBuildurl()
				+ "cucumber-html-reports";
		String emaillogo = report.getWorkspace() + "ws/test-result/logo.png";

		Capabilities actualCapabilities = ((RemoteWebDriver) driver)
				.getCapabilities();
		String platform = HostUtils.getOperatingSystemName() + ","
				+ HostUtils.getOperatingSystemVersion() + "("
				+ HostUtils.getOSType() + ")";
		String browser = actualCapabilities.getBrowserName().toUpperCase()
				+ " " + actualCapabilities.getVersion();
		String endtime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(Calendar.getInstance().getTime());
		String lasttime = TimeUtils.howManyMinutes(starttime, endtime);
		log.info("Begin to parse the email report file and send email notification with content template file: email.html");

		boolean generatereport = Boolean.parseBoolean(PropertiesUtils
				.getEnv("GENERATE_REPORT"));
		String runurl = null;
		if (generatereport) {
			TestRailAPI testrailapi = TestRailImpl.api;
			runurl = testrailapi.getBase_Url() + "/index.php?/runs/view/"
					+ String.valueOf(testrailapi.getRunid());
		}
		// String runid = String.valueOf(TestRailImpl.api.getRunid());
		EmailReportTemplate.generateHtmlReport(report.getProjectname(), runurl,
				emaillogo, report.getHostname(), report.getEnvironment()
						.toUpperCase(), report.getBuildnumber(), platform,
				browser, lasttime, totalsteps, failedCount, passCount,
				consoleurl, totalscenarios, sfailedCount, spassCount,
				cucumberreportpath, report.getEmail());

		try {
			if (driver != null) {
				//driver.close();
				factory.killDriverProcesses();
			}
		} catch (Exception e) {
			log.warn(e);
		}
	}

}
