package com.framework.utilities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

public class EmailReportTemplate {

	protected static final Logger log = Logger
			.getLogger(EmailReportTemplate.class);

	// private static String runid;
	/**
	 * @Title: generateHtmlReport
	 * @Description: TODO
	 * @author ahu@greendotcorp.com
	 * @param @param projectname
	 * @param @param hostname
	 * @param @param environment
	 * @param @param buildnumber
	 * @param @param platform
	 * @param @param browser
	 * @param @param runtime
	 * @param @param totalsteps
	 * @param @param failedsteps
	 * @param @param passedsteps
	 * @param @param consoleurl
	 * @param @param totalscenarios
	 * @param @param failedscenarios
	 * @param @param passedscenarios
	 * @param @param cucumberreportpath
	 * @param @param subscribleemail
	 * @return void return type
	 * @throws
	 */

	public static void generateHtmlReport(String projectname,
			String testrailurl, String workspace, String hostname,
			String environment, String buildnumber, String platform,
			String browser, String runtime, int totalsteps, int failedsteps,
			int passedsteps, String consoleurl, int totalscenarios,
			int failedscenarios, int passedscenarios,
			String cucumberreportpath, String subscribleemail) {

		// calcular the percent rate of the execution
		DecimalFormat df = new DecimalFormat("0");

		String PASSED_RATE = df
				.format(((double) passedsteps / totalsteps * 100));
		String FAILED_RATE = df.format((double) failedsteps / totalsteps * 100);

		String TOTAL_PASSED_RATE = df.format((double) passedscenarios
				/ totalscenarios * 100);
		String TOTAL_FAILED_RATE = df.format((double) failedscenarios
				/ totalscenarios * 100);

		BufferedWriter output = null;
		log.info("Generate email report with Jenkins Job URL:"
				+ cucumberreportpath);
		File srcFile = new File(EmailReportTemplate.class.getClassLoader()
				.getResource("logo.png").getFile());
		File destDir = new File("test-result");
		try {
			FileUtils.copyFileToDirectory(srcFile, destDir);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			output = new BufferedWriter(
					new FileWriter("test-result/email.html"));

			// output.write("Hi Subscribers,<br/>                                                                               ");
			// output.write("                                                                                             ");
			output.write("<p class=MsoNormal><span style='font-size:13.5pt;font-family:\"Arial\",\"sans-serif\";           ");
			output.write("mso-fareast-font-family:\"Times New Roman\"'>Project Details</span><span                       ");
			output.write("style='mso-fareast-font-family:\"Times New Roman\"'><o:p></o:p></span><span style='mso-spacerun:yes'>");
			output.write("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>");
			output.write("<span><img width=248 height=50 src=\""
					+ workspace
					+ "\" alt=\"Cannot display the logo ,maybe blocked by security\"></span></p>                     ");
			output.write("                                                                                             ");

			output.write("<table class=MsoNormalTable border=1 cellpadding=0 width=557 style='width:417.75pt;          ");
			output.write(" mso-cellspacing:1.5pt;background:#EBEAFF;mso-yfti-tbllook:1184;mso-padding-alt:             ");
			output.write(" 1.5pt 1.5pt 1.5pt 1.5pt'>                                                                   ");
			output.write(" <tr style='mso-yfti-irow:0;mso-yfti-firstrow:yes'>                                          ");
			output.write("  <td width=188 style='width:141.0pt;padding:1.5pt 1.5pt 1.5pt 1.5pt'>                       ");
			output.write("  <p class=MsoNormal><span style='font-family:\"Arial\",\"sans-serif\";mso-fareast-font-family:  ");
			output.write("  \"Times New Roman\"'>Jenkins Job Name<o:p></o:p></span></p>                                      ");
			output.write("  </td>                                                                                      ");
			output.write("  <td width=349 style='width:261.75pt;background:#FFD987;padding:1.5pt 1.5pt 1.5pt 1.5pt'>   ");
			output.write("  <p class=MsoNormal><span style='font-family:\"Arial\",\"sans-serif\";mso-fareast-font-family:  ");
			output.write("  \"Times New Roman\"'>"
					+ projectname
					+ "<o:p></o:p></span></p>                                     ");
			output.write("  </td>                                                                                      ");
			output.write(" </tr>                                                                                       ");
			output.write("<tr style='mso-yfti-irow:0;mso-yfti-firstrow:yes'>                                           ");
			output.write("  <td width=188 style='width:141.0pt;padding:1.5pt 1.5pt 1.5pt 1.5pt'>                       ");
			output.write("  <p class=MsoNormal><span style='font-family:\"Arial\",\"sans-serif\";mso-fareast-font-family:  ");
			output.write("  \"Times New Roman\"'>TestRail Run Result<o:p></o:p></span></p>                                    ");
			output.write("  </td>                                                                                      ");
			output.write("  <td width=349 style='width:261.75pt;background:#FFD987;padding:1.5pt 1.5pt 1.5pt 1.5pt'>   ");
			output.write("  <p class=MsoNormal><span style='font-family:\"Arial\",\"sans-serif\";mso-fareast-font-family:  ");
			output.write("  \"Times New Roman\"'><a                                                                      ");
			output.write("  href=\""
					+ testrailurl
					+ "\">"
					+ testrailurl
					+ "</a>                                                      ");
			output.write("  <o:p></o:p></span></p>               ");
			output.write("  </td>                                                                                      ");
			output.write(" </tr>                                                                                       ");
			output.write("<tr style='mso-yfti-irow:0;mso-yfti-firstrow:yes'>                                           ");
			output.write("  <td width=188 style='width:141.0pt;padding:1.5pt 1.5pt 1.5pt 1.5pt'>                       ");
			output.write("  <p class=MsoNormal><span style='font-family:\"Arial\",\"sans-serif\";mso-fareast-font-family:  ");
			output.write("  \"Times New Roman\"'>Execution Host<o:p></o:p></span></p>                                    ");
			output.write("  </td>                                                                                      ");
			output.write("  <td width=349 style='width:261.75pt;background:#FFD987;padding:1.5pt 1.5pt 1.5pt 1.5pt'>   ");
			output.write("  <p class=MsoNormal><span style='font-family:\"Arial\",\"sans-serif\";mso-fareast-font-family:  ");
			output.write("  \"Times New Roman\"'>" + hostname
					+ " <o:p></o:p></span></p>                ");
			output.write("  </td>                                                                                      ");
			output.write(" </tr>                                                                                       ");
			output.write(" <tr style='mso-yfti-irow:1'>                                                                ");
			output.write("  <td width=188 style='width:141.0pt;padding:1.5pt 1.5pt 1.5pt 1.5pt'>                       ");
			output.write("  <p class=MsoNormal><span style='font-family:\"Arial\",\"sans-serif\";mso-fareast-font-family:  ");
			output.write("  \"Times New Roman\"'>Environment<o:p></o:p></span></p>                                       ");
			output.write("  </td>                                                                                      ");
			output.write("  <td width=349 style='width:261.75pt;background:#FFD987;padding:1.5pt 1.5pt 1.5pt 1.5pt'>   ");
			output.write("  <p class=MsoNormal><span style='font-family:\"Arial\",\"sans-serif\";mso-fareast-font-family:  ");
			output.write("  \"Times New Roman\"'>"
					+ environment
					+ "<o:p></o:p></span></p>                                      ");
			output.write("  </td>                                                                                      ");
			output.write(" </tr>                                                                                       ");
			output.write(" <tr style='mso-yfti-irow:2'>                                                                ");
			output.write("  <td width=188 style='width:141.0pt;padding:1.5pt 1.5pt 1.5pt 1.5pt'>                       ");
			output.write("  <p class=MsoNormal><span style='font-family:\"Arial\",\"sans-serif\";mso-fareast-font-family:  ");
			output.write("  \"Times New Roman\"'>Platform<o:p></o:p></span></p>                                    ");
			output.write("  </td>                                                                                      ");
			output.write("  <td width=349 style='width:261.75pt;background:#FFD987;padding:1.5pt 1.5pt 1.5pt 1.5pt'>   ");
			output.write("  <p class=MsoNormal><span style='font-family:\"Arial\",\"sans-serif\";mso-fareast-font-family:  ");
			output.write("  \"Times New Roman\"'>"
					+ platform
					+ "<o:p></o:p></span></p>                                     ");
			output.write("  </td>                                                                                      ");
			output.write(" </tr>                                                                                       ");
			output.write(" <tr style='mso-yfti-irow:2'>                                                                ");
			output.write("  <td width=188 style='width:141.0pt;padding:1.5pt 1.5pt 1.5pt 1.5pt'>                       ");
			output.write("  <p class=MsoNormal><span style='font-family:\"Arial\",\"sans-serif\";mso-fareast-font-family:  ");
			output.write("  \"Times New Roman\"'>Browser Type<o:p></o:p></span></p>                                    ");
			output.write("  </td>                                                                                      ");
			output.write("  <td width=349 style='width:261.75pt;background:#FFD987;padding:1.5pt 1.5pt 1.5pt 1.5pt'>   ");
			output.write("  <p class=MsoNormal><span style='font-family:\"Arial\",\"sans-serif\";mso-fareast-font-family:  ");
			output.write("  \"Times New Roman\"'>"
					+ browser
					+ "<o:p></o:p></span></p>                                     ");
			output.write("  </td>                                                                                      ");
			output.write(" </tr>                                                                                       ");
			output.write(" <tr style='mso-yfti-irow:2'>                                                                ");
			output.write("  <td width=188 style='width:141.0pt;padding:1.5pt 1.5pt 1.5pt 1.5pt'>                       ");
			output.write("  <p class=MsoNormal><span style='font-family:\"Arial\",\"sans-serif\";mso-fareast-font-family:  ");
			output.write("  \"Times New Roman\"'>Build Number #<o:p></o:p></span></p>                                    ");
			output.write("  </td>                                                                                      ");
			output.write("  <td width=349 style='width:261.75pt;background:#FFD987;padding:1.5pt 1.5pt 1.5pt 1.5pt'>   ");
			output.write("  <p class=MsoNormal><span style='font-family:\"Arial\",\"sans-serif\";mso-fareast-font-family:  ");
			output.write("  \"Times New Roman\"'>"
					+ buildnumber
					+ "<o:p></o:p></span></p>                                     ");
			output.write("  </td>                                                                                      ");
			output.write(" </tr>                                                                                       ");
			output.write(" <tr style='mso-yfti-irow:3;mso-yfti-lastrow:yes'>                                           ");
			output.write("  <td width=188 style='width:141.0pt;padding:1.5pt 1.5pt 1.5pt 1.5pt'>                       ");
			output.write("  <p class=MsoNormal><span style='font-family:\"Arial\",\"sans-serif\";mso-fareast-font-family:  ");
			output.write("  \"Times New Roman\"'>Execution Time<o:p></o:p></span></p>                                        ");
			output.write("  </td>                                                                                      ");
			output.write("  <td width=349 style='width:261.75pt;background:#FFD987;padding:1.5pt 1.5pt 1.5pt 1.5pt'>   ");
			output.write("  <p class=MsoNormal><span style='font-family:\"Arial\",\"sans-serif\";mso-fareast-font-family:  ");
			output.write("  \"Times New Roman\"'>"
					+ runtime
					+ " <o:p></o:p></span></p>                                      ");
			output.write("  </td>                                                                                      ");
			output.write(" </tr>                                                                                       ");
			output.write("</table>                                                                                     ");
			output.write("                                                                                             ");
			output.write("<p class=MsoNormal><span style='font-family:\"Arial\",\"sans-serif\";mso-fareast-font-family:    ");
			output.write("\"Times New Roman\"'>                                                                          ");
			output.write("<br>                                                                                         ");
			output.write("</span><span style='font-size:13.5pt;font-family:\"Arial\",\"sans-serif\";                       ");
			output.write("mso-fareast-font-family:\"Times New Roman\"'>Cucumber TestStep&nbsp;Summary</span><span                 ");
			output.write("style='mso-fareast-font-family:\"Times New Roman\"'><o:p></o:p></span></p>                     ");
			output.write("                                                                                             ");
			output.write("<table class=MsoNormalTable border=1 cellpadding=0 width=560 style='width:420.0pt;           ");
			output.write(" mso-cellspacing:1.5pt;mso-yfti-tbllook:1184;mso-padding-alt:1.5pt 1.5pt 1.5pt 1.5pt'>       ");
			output.write(" <tr style='mso-yfti-irow:0;mso-yfti-firstrow:yes'>                                          ");
			output.write("  <td width=189 style='width:141.75pt;background:#EBEAFF;padding:1.5pt 1.5pt 1.5pt 1.5pt'>   ");
			output.write("  <p class=MsoNormal><span style='font-family:\"Arial\",\"sans-serif\";mso-fareast-font-family:  ");
			output.write("  \"Times New Roman\"'>Total Cucumber Steps Executed<o:p></o:p></span></p>                         ");
			output.write("  </td>                                                                                      ");
			output.write("  <td width=351 style='width:263.25pt;background:#FFD987;padding:1.5pt 1.5pt 1.5pt 1.5pt'>   ");
			output.write("  <p class=MsoNormal><span style='font-family:\"Arial\",\"sans-serif\";mso-fareast-font-family:  ");
			output.write("  \"Times New Roman\"'>"
					+ totalsteps
					+ "<o:p></o:p></span></p>                                     ");
			output.write("  </td>                                                                                      ");
			output.write(" </tr>                                                                                       ");

			output.write(" <tr style='mso-yfti-irow:2'>                                                                ");
			output.write("  <td width=189 style='width:141.75pt;background:#EBEAFF;padding:1.5pt 1.5pt 1.5pt 1.5pt'>   ");
			output.write("  <p class=MsoNormal><span style='font-family:\"Arial\",\"sans-serif\";mso-fareast-font-family:  ");
			output.write("  \"Times New Roman\"'>Passed Cucumber Steps<o:p></o:p></span></p>                                 ");
			output.write("  </td>                                                                                      ");
			output.write("  <td width=351 style='width:263.25pt;background:#FFD987;padding:1.5pt 1.5pt 1.5pt 1.5pt'>   ");
			output.write("  <p class=MsoNormal><span style='font-family:\"Arial\",\"sans-serif\";mso-fareast-font-family:  ");
			output.write("  \"Times New Roman\"'>"
					+ passedsteps
					+ "<o:p></o:p></span></p>                                    ");
			output.write("  </td>                                                                                      ");
			output.write(" </tr>                                                                                       ");
			output.write(" <tr style='mso-yfti-irow:4'>                                                                ");
			output.write("  <td width=189 style='width:141.75pt;background:#EBEAFF;padding:1.5pt 1.5pt 1.5pt 1.5pt'>   ");
			output.write("  <p class=MsoNormal><span style='font-family:\"Arial\",\"sans-serif\";mso-fareast-font-family:  ");
			output.write("  \"Times New Roman\"'>Passed Rate %<o:p></o:p></span></p>                                          ");
			output.write("  </td>                                                                                      ");
			output.write("  <td width=351 style='width:263.25pt;background:#FFD987;padding:1.5pt 1.5pt 1.5pt 1.5pt'>   ");
			output.write("  <p class=MsoNormal><span style='font-family:\"Arial\",\"sans-serif\";mso-fareast-font-family:  ");
			output.write("  \"Times New Roman\"'>"
					+ PASSED_RATE
					+ " %<o:p></o:p></span></p>                                    ");
			output.write("  </td>                                                                                      ");
			output.write(" </tr>                                                                                       ");
			output.write(" <tr style='mso-yfti-irow:1'>                                                                ");
			output.write("  <td width=189 style='width:141.75pt;background:#EBEAFF;padding:1.5pt 1.5pt 1.5pt 1.5pt'>   ");
			output.write("  <p class=MsoNormal><span style='font-family:\"Arial\",\"sans-serif\";mso-fareast-font-family:  ");
			output.write("  \"Times New Roman\"'>Failed Cucumber Steps<o:p></o:p></span></p>                                 ");
			output.write("  </td>                                                                                      ");
			output.write("  <td width=351 style='width:263.25pt;background:red;padding:1.5pt 1.5pt 1.5pt 1.5pt'>       ");
			output.write("  <p class=MsoNormal><span style='font-family:\"Arial\",\"sans-serif\";mso-fareast-font-family:  ");
			output.write("  \"Times New Roman\"'>"
					+ failedsteps
					+ "<o:p></o:p></span></p>                                    ");
			output.write("  </td>                                                                                      ");
			output.write(" </tr>                                                                                       ");
			output.write(" <tr style='mso-yfti-irow:5;mso-yfti-lastrow:yes'>                                           ");
			output.write("  <td width=189 style='width:141.75pt;background:#EBEAFF;padding:1.5pt 1.5pt 1.5pt 1.5pt'>   ");
			output.write("  <p class=MsoNormal><span style='font-family:\"Arial\",\"sans-serif\";mso-fareast-font-family:  ");
			output.write("  \"Times New Roman\"'>Failed Rate %<o:p></o:p></span></p>                                          ");
			output.write("  </td>                                                                                      ");
			output.write("  <td width=351 style='width:263.25pt;background:red;padding:1.5pt 1.5pt 1.5pt 1.5pt'>       ");
			output.write("  <p class=MsoNormal><span style='font-family:\"Arial\",\"sans-serif\";mso-fareast-font-family:  ");
			output.write("  \"Times New Roman\"'>"
					+ FAILED_RATE
					+ " %<o:p></o:p></span></p>                                    ");
			output.write("  </td>                                                                                      ");
			output.write(" </tr>                                                                                       ");
			output.write("</table>                                                                                     ");
			output.write("                                                                                             ");
			output.write("<p class=MsoNormal><span style='mso-fareast-font-family:\"Times New Roman\"'><br>              ");
			output.write("</span><span style='font-family:\"Arial\",\"sans-serif\";mso-fareast-font-family:                ");
			output.write("\"Times New Roman\"'><br>                                                                      ");
			output.write("</span><span style='font-size:13.5pt;font-family:\"Arial\",\"sans-serif\";                       ");
			output.write("mso-fareast-font-family:\"Times New Roman\"'>Detailed Project Reports</span><span              ");
			output.write("style='mso-fareast-font-family:\"Times New Roman\"'><o:p></o:p></span></p>                     ");
			output.write("                                                                                             ");
			output.write("<table class=MsoNormalTable border=1 cellpadding=0 width=1147 style='width:                  ");
			output.write(" 860.25pt;mso-cellspacing:1.5pt;background:#FFD987;mso-yfti-tbllook:1184;                    ");
			output.write(" mso-padding-alt:1.5pt 1.5pt 1.5pt 1.5pt'>                                                   ");
			output.write(" <tr style='mso-yfti-irow:0;mso-yfti-firstrow:yes'>                                          ");
			output.write("  <td style='padding:1.5pt 1.5pt 1.5pt 1.5pt'>                                               ");
			output.write("  <p class=MsoNormal><b><span style='font-family:\"Arial\",\"sans-serif\";                       ");
			output.write("  mso-fareast-font-family:\"Times New Roman\"'>Project Name<o:p></o:p></span></b></p>");
			output.write("  </td>                                                                                      ");
			output.write("  <td style='padding:1.5pt 1.5pt 1.5pt 1.5pt'>                                               ");
			output.write("  <p class=MsoNormal><b><span style='font-family:\"Arial\",\"sans-serif\";                       ");
			output.write("  mso-fareast-font-family:\"Times New Roman\"'>Total Scenarios<o:p></o:p></span></b></p>       ");
			output.write("  </td>                                                                                      ");

			output.write("  <td style='padding:1.5pt 1.5pt 1.5pt 1.5pt'>                                               ");
			output.write("  <p class=MsoNormal><b><span style='font-family:\"Arial\",\"sans-serif\";                       ");
			output.write("  mso-fareast-font-family:\"Times New Roman\"'>Total Passed Scenarios<o:p></o:p></span></b></p>      ");
			output.write("  </td>                                                                                      ");
			output.write("  <td style='padding:1.5pt 1.5pt 1.5pt 1.5pt'>                                               ");
			output.write("  <p class=MsoNormal><b><span style='font-family:\"Arial\",\"sans-serif\";                       ");
			output.write("  mso-fareast-font-family:\"Times New Roman\"'>Passed Scenario Rate %<o:p></o:p></span></b></p>");
			output.write("  </td>                                                                                      ");
			output.write("  <td style='padding:1.5pt 1.5pt 1.5pt 1.5pt'>                                               ");
			output.write("  <p class=MsoNormal><b><span style='font-family:\"Arial\",\"sans-serif\";                       ");
			output.write("  mso-fareast-font-family:\"Times New Roman\"'>Total Failed Scenarios<o:p></o:p></span></b></p>");
			output.write("  </td>                                                                                      ");
			output.write("  <td style='padding:1.5pt 1.5pt 1.5pt 1.5pt'>                                               ");
			output.write("  <p class=MsoNormal><b><span style='font-family:\"Arial\",\"sans-serif\";                       ");
			output.write("  mso-fareast-font-family:\"Times New Roman\"'>Failed Scenario Rate %<o:p></o:p></span></b></p>   ");
			output.write("  </td>                                                                                      ");

			output.write(" </tr>                                                                                       ");
			output.write(" <tr style='mso-yfti-irow:1;mso-yfti-lastrow:yes'>                                           ");
			output.write("  <td style='padding:1.5pt 1.5pt 1.5pt 1.5pt'>                                               ");
			output.write("  <p class=MsoNormal><span style='font-family:\"Arial\",\"sans-serif\";mso-fareast-font-family:  ");
			output.write("  \"Times New Roman\"'><a                                                                      ");
			output.write("  href=\""
					+ cucumberreportpath
					+ "\">"
					+ projectname
					+ "</a>                                                      ");
			output.write("  <o:p></o:p></span></p>                                                                     ");
			output.write("  </td>                                                                                      ");
			output.write("  <td style='padding:1.5pt 1.5pt 1.5pt 1.5pt'>                                               ");
			output.write("  <p class=MsoNormal><span style='font-family:\"Arial\",\"sans-serif\";mso-fareast-font-family:  ");
			output.write("  \"Times New Roman\"'>"
					+ totalscenarios
					+ "<o:p></o:p></span></p>                                            ");
			output.write("  </td>                                                                                      ");

			output.write("  <td style='padding:1.5pt 1.5pt 1.5pt 1.5pt'>                              ");
			output.write("  <p class=MsoNormal><span style='font-family:\"Arial\",\"sans-serif\";mso-fareast-font-family:  ");
			output.write("  \"Times New Roman\"'>"
					+ passedscenarios
					+ "<o:p></o:p></span></p>                                     ");
			output.write("  </td>                                                                                      ");
			output.write("  <td style='padding:1.5pt 1.5pt 1.5pt 1.5pt'>                                               ");
			output.write("  <p class=MsoNormal><span style='font-family:\"Arial\",\"sans-serif\";mso-fareast-font-family:  ");
			output.write("  \"Times New Roman\"'>" + TOTAL_PASSED_RATE
					+ " %<o:p></o:p></span></p>                              ");
			output.write("  </td>                                                                                      ");

			output.write("  <td style='padding:1.5pt 1.5pt 1.5pt 1.5pt;background: red;'>                              ");
			output.write("  <p class=MsoNormal><span style='font-family:\"Arial\",\"sans-serif\";mso-fareast-font-family:  ");
			output.write("  \"Times New Roman\"'>"
					+ failedscenarios
					+ "<o:p></o:p></span></p>                                     ");
			output.write("  </td>                                                                                      ");
			output.write("  <td style='padding:1.5pt 1.5pt 1.5pt 1.5pt;background: red;'>                              ");
			output.write("  <p class=MsoNormal><span style='font-family:\"Arial\",\"sans-serif\";mso-fareast-font-family:  ");
			output.write("  \"Times New Roman\"'>" + TOTAL_FAILED_RATE
					+ " %<o:p></o:p></span></p>                              ");
			output.write("  </td>                                                                                      ");
			output.write(" </tr>                                                                                       ");
			output.write("</table>                                                                                     ");
			output.write("<br/>                                                                                        ");
			output.write("<b>NOTE: Check more Jenkins console execution logs at <a href=\""
					+ consoleurl + "\">Here</a> .</b>");
			output.write("<br>");
			output.write("<td style=\"padding-top:4px;font-family:arial,sans-serif;color:#636363;font-size:11px\">");
			output.write("<p>You received this email because you're subscribed to receive the update for this group,"
					+ " to unsubscribe from this group or stop receiving emails, please send the email to <a href=\"mailto:"
					+ subscribleemail
					+ "\" target=\"_blank\">"
					+ subscribleemail + "</a>.</p>");
			output.write("</td> ");

			output.write("<div class=MsoNormal align=center style='text-align:center'><span          ");
			output.write("style='mso-fareast-font-family:\"Times New Roman\"'>                         ");
			output.write("                                                                           ");
			output.write("<hr size=2 width=\"100%\" align=center>                                      ");
			output.write("                                                                           ");
			output.write("</span></div>                                                              ");
			output.write("                                                                           ");
			output.write("<p>Confidentiality Notice:&nbsp; The information contained in this email is");
			output.write("confidential and is being transmitted to, and is intended solely for the use");
			output.write("of, the individual(s) to whom it is addressed.&nbsp; If the reader of this  ");
			output.write("message is not the intended recipient, you are hereby advised that any      ");
			output.write("dissemination, distribution or copying of this message is strictly          ");
			output.write("prohibited.&nbsp; If you have received this message in error, please        ");
			output.write("immediately notify the sender and delete this email from your system.</p>   ");
			output.write("                                                                            ");
			output.write("</div>                                                                      ");

			// System.out.println("Complete to write the html file email.html:"+cucumberreportpath+totalscenarios);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("parse to email.html met exception:" + e);
		} finally {
			try {
				output.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out
						.println("parse to email.html met exception,close the file:"
								+ e);
			}
		}

	}

	public void generateReplayReport() {

		BufferedWriter output = null;
		System.out.println("generate the replay video report");

		try {
			output = new BufferedWriter(new FileWriter(
					"test-result/replay.html"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				output.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
