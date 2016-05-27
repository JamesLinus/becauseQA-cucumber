package com.framework.utilities;

import java.io.File;
import java.util.Calendar;

/**
 * @ClassName: GlobalDefinition
 * @Description: TODO
 * @author alterhu2020@gmail.com
 * @date Apr 9, 2014 5:56:22 PM
 * 
 */

public class GlobalDefinition {

	public final static String PROJECT_DIR = new File("").getAbsolutePath()
			+ File.separator; // like "C:\workspace\Micro2_Automation_Selenium\"

	// additional folder path settings
	public final static String Log4J_LOG_DIR = "test-logs"; // IE console log
															// folder
	public final static String REPORTRESULT_DIR = "test-result";
	public final static String TESTNG_REPORT_DIR = "test-output";
	public final static String RESOURCES_DIR = "test-resources";
	public final static String SELENIUM_DRIVER_PATH = "test-drivers";

	// resource folder
	public final static String MOUSE_CURSE_ICON = "cursor_white.png";
	public final static String GLOBAL_EXCEL_FILE = "TestData.xls";
	public final static String REPORT_TEMPLATE_FILE = "report_template.htm";
	public final static String EMAIL_LOG_FILE = "logo.png";
	public final static String AUTOITX_FILE = "AutoItX3.dll";

	// selenium page settings
	public static int PAGE_LOADING_TIME = 150;
	public static long WEBELEMENT_LOADING_TIME = 6;
	public static long JS_TIMEOUT = 30000L;

	// script started time
	public static String CURRENT_TIME = TimeUtils.getCurrentTime(Calendar
			.getInstance().getTime());

	// browser download folder settings
	public static String BROWSER_DOWNLOAD_DIR = PROJECT_DIR + "Downloads";

}
