package org.becausecucumber.eclipse.plugin.ui.common;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.becausecucumber.eclipse.plugin.common.PluginUtils;
import org.becausecucumber.eclipse.plugin.cucumber.DocString;
import org.becausecucumber.eclipse.plugin.cucumber.Step;

public class CucumberStepCreatorUtils {

	public static String keyword;
	public static String description;
	public static int tables;
	public static DocString docstring;

	public static void main(String[] args) {

		keyword = "Given";
		// String descvariable="I want to test_abcde/acddd's
		// ？@#$%^&*()_+!<>?{}:~(dsds) \"2222\" for \"kdsdsdsdhu\" dsdsdsdsdsd:";
		String des2 = "i test \"alter\" with password \"hu\"";

		tables = 1;
		description = des2;

		String createRubyStepMethod = createRubyStepMethod(null);
		System.out.println("\n\n method is:\n " + createRubyStepMethod);

		String[] split = des2.split(" ");
		System.out.println(split.toString());
	}

	/**
	 * @Title: createJavaStepMethod @Description: TODO @author
	 * alterhu2020@gmail.com @param @param element @param @return @return
	 * Map<String,String> return type @throws
	 */

	public static Map<String, String> createJavaStepMethod(Step element) {
		// List<Map<String, String>> table
		int strparametersize = 0;
		String importtable = "false";

		Map<String, String> cucumber = new HashMap<String, String>();

		keyword = element.getKeyword().trim();
		description = element.getDescription().trim();
		tables = element.getTables().size();
		docstring = element.getCode();

		strparametersize = (docstring != null ? strparametersize + 1 : strparametersize);

		String annotation = description;
		String methodname = description;
		String parameters = "";

		int intindex = 1;
		Matcher parameterquote = Pattern.compile("\"(.*?)\"").matcher(description);
		Matcher valuequote = Pattern.compile("\'(.*?)\'").matcher(description);
		while (parameterquote.find()) {
			String foundstr = parameterquote.group();
			annotation = annotation.replaceAll(foundstr, "\\\\\"(.*)\\\\\"");

			methodname = methodname.replaceAll(foundstr, "");
			strparametersize = strparametersize + 1;
		}
		while (valuequote.find()) {
			String foundstr = valuequote.group();
			try {
				String tempfoundstr = foundstr.substring(1, foundstr.length() - 1);
				Integer.parseInt(tempfoundstr);
				annotation = annotation.replaceAll(foundstr, "'(\\\\\\\\d+)'");

				parameters = parameters + "int param" + intindex + ",";
				methodname = methodname.replaceAll(foundstr, "");
				intindex = intindex + 1;
			} catch (NumberFormatException e) {
				// TODO: handle exception
			}

		}

		if (description.contains(" ")) {
			String[] splitdesc = description.split(" ");

			for (int k = 0; k < splitdesc.length; k++) {
				try {
					String tempfoundstr = splitdesc[k];
					Integer.parseInt(tempfoundstr);
					annotation = annotation.replaceAll(tempfoundstr, "(\\\\\\\\d+)");

					parameters = parameters + "int param" + intindex + ",";
					methodname = methodname.replaceAll(tempfoundstr, "");
					intindex = intindex + 1;
				} catch (NumberFormatException e) {
					// TODO: handle exception
				}
			}

		}

		annotation = "\t@" + keyword + "(\"^" + annotation + "$\")\n";

		for (int argindex = 1; argindex <= strparametersize; argindex++) {
			parameters = parameters + "String arg" + argindex + ",";
		}
		if (tables > 0) {
			parameters = parameters + "List<Map<String, String>> table,";
			importtable = "true";
		}

		parameters = (parameters.equals("")) ? "()" : "(" + parameters.substring(0, parameters.length() - 1) + ")";

		methodname = methodname
				.replaceAll("\\s|\t|\\(|\\_|\\)|\\:|\\?|\\\"|\\'|\\/|#|？|@|\\#|"
						+ "\\$|%|\\^|&|\\*|\\(|\\)|\\+|\\!|<|>|\\?|\\{|\\}|:|~", " ")
				.trim().replaceAll(" ", "_").trim();
		String date = PluginUtils.getCurrentDate();
		String comments = "\n\t/**\n" + "\t* @MethodName: " + methodname + "\n" + "\t* @Description: TODO\n"
				+ "\t* @author: TODO\n" + "\t* @date: " + date + "\n" + "\t* \n" + "\t*/\n";

		methodname = "\tpublic void " + methodname + parameters
				+ "{\n\t// Write code here that turns the phrase above into concrete actions\n\n\t}\n\n";

		cucumber.put("comments", comments);
		cucumber.put("keyword", keyword);
		cucumber.put("method", methodname);
		cucumber.put("annotation", annotation);
		cucumber.put("hastable", importtable);

		return cucumber;
	}

	/**
	 * @Title: createRubyStepMethod @Description: TODO @author
	 * alterhu2020@gmail.com @param @param element @param @return @return String
	 * return type @throws
	 */

	public static String createRubyStepMethod(Step element) {
		// List<Map<String, String>> table
		int strparametersize = 0;
		String importtable = "false";

		/*
		 * keyword = element.getKeyword(); description =
		 * element.getDescription(); tables = element.getTables().size();
		 * docstring = element.getCode();
		 */

		strparametersize = (docstring != null ? strparametersize + 1 : strparametersize);

		String annotation = description;
		String methodname = description;
		String parameters = "|";

		Matcher parameterquote = Pattern.compile("\"(.*?)\"").matcher(description);
		Matcher valuequote = Pattern.compile("\'(.*?)\'").matcher(description);
		while (parameterquote.find()) {
			String foundstr = parameterquote.group();
			annotation = annotation.replaceAll(foundstr, "\\\\\"(.*)\\\\\"");

			methodname = methodname.replaceAll(foundstr, "");
			strparametersize = strparametersize + 1;
		}
		while (valuequote.find()) {
			String foundstr = valuequote.group();
			try {
				String tempfoundstr = foundstr.substring(1, foundstr.length() - 1);
				Integer.parseInt(tempfoundstr);
				annotation = annotation.replaceAll(foundstr, "\'(.*)\'");

				methodname = methodname.replaceAll(foundstr, "");
				strparametersize = strparametersize + 1;
			} catch (NumberFormatException e) {
				// TODO: handle exception
			}

		}

		// annotation="\t\t@"+keyword+"(\"^"+annotation+"$\")\n";

		for (int argindex = 1; argindex <= strparametersize; argindex++) {
			parameters = parameters + "params arg" + argindex + ",";
		}
		if (tables > 0) {
			// parameters=parameters+"List<Map<String, String>> table,";
			importtable = "true";
		}

		parameters = (parameters.equals("")) ? "" : parameters.substring(0, parameters.length() - 1) + "|";

		String comments = "\n\t\t/**\n" + "\t\t* @ClassName: " + methodname + "\n" + "\t\t* @Description: TODO\n"
				+ "\t\t* @author: TODO\n" + "\t\t* @date: TODO\n" + "\t\t* \n" + "\t\t*/\n";
		System.out.println(comments + importtable);
		methodname = "\n" + keyword + " /^" + annotation + "$/ do " + parameters;

		return methodname;
	}

}
