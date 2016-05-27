package com.framework.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.Logger;
import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

public class ScreenshotTestRule implements MethodRule {

	protected static final Logger logger = Logger
			.getLogger(ScreenshotTestRule.class);
	private WebDriver driver;

	public ScreenshotTestRule(WebDriver driver) {
		this.driver = driver;
	}

	public Statement apply(final Statement statement,
			final FrameworkMethod frameworkMethod, final Object o) {
		return new Statement() {
			@Override
			public void evaluate() throws Throwable {
				try {
					statement.evaluate();
				} catch (Throwable t) {
					logger.error("Capture the error scenario screenshot now ......"
							+ frameworkMethod.getName());
					captureScreenshot(frameworkMethod.getName());
					throw t; // rethrow to allow the failure to be reported to
								// JUnit
				}
			}

			public void captureScreenshot(String fileName) {

				new File(GlobalDefinition.REPORTRESULT_DIR).mkdirs(); // Insure
																		// directory
																		// is
																		// there
				String shorttime = new SimpleDateFormat("yyyyMMddHHmmsss")
						.format(Calendar.getInstance().getTime());
				FileOutputStream out;
				try {
					out = new FileOutputStream(
							GlobalDefinition.REPORTRESULT_DIR + File.separator
									+ "screenshot-" + fileName + "-"
									+ shorttime + ".png");
					String title = driver.toString();
					System.out.println("title is :" + title);
					out.write(((TakesScreenshot) driver)
							.getScreenshotAs(OutputType.BYTES));
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
		};
	}
}
