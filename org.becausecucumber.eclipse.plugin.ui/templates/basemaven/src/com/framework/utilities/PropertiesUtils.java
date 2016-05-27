package com.framework.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

public class PropertiesUtils {

	private static Properties properties = new Properties();

	/**
	 * @Title: getString
	 * @Description: TODO
	 * @author alterhu2020@gmail.com
	 * @param @param key
	 * @param @return
	 * @return String return type
	 * @throws
	 */

	public static String getString(String key) {
		String value = null;

		try {
			properties.load(PropertiesUtils.class.getClassLoader()
					.getResourceAsStream(("config.properties")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		value = (String) properties.get(key);

		return value;
	}

	/**
	 * @Title: getPropertiesMap
	 * @Description: TODO
	 * @author alterhu2020@gmail.com
	 * @param @param rootpropertiesfilename
	 * @param @return
	 * @return Map<String,String> return type
	 * @throws
	 */

	public static Map<String, Object> getPropertiesMap(
			String rootpropertiesfilename) {

		Map<String, Object> propertiesmap = new HashMap<String, Object>();
		try {
			File file = new File("test-configs"); // any parent path you
													// specified here

			URL[] urls = { file.toURI().toURL() };
			ClassLoader loader = new URLClassLoader(urls);
			if (rootpropertiesfilename == null || rootpropertiesfilename == "") {
				rootpropertiesfilename = "dev";
			}
			ResourceBundle rb = ResourceBundle.getBundle(
					rootpropertiesfilename, Locale.getDefault(), loader);
			Enumeration<String> es = rb.getKeys();
			while (es.hasMoreElements()) {
				String key = es.nextElement();
				String value = rb.getString(key);
				propertiesmap.put(key, value);
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return propertiesmap;
	}

	public String getpropertyValue(String propertyname) {
		/*
		 * Model model = null; FileReader reader = null; MavenXpp3Reader
		 * mavenreader = new MavenXpp3Reader(); try { reader = new
		 * FileReader(pomfile); model = mavenreader.read(reader);
		 * model.setPomFile(pomfile); }catch(Exception ex){} MavenProject
		 * project = new MavenProject(model);
		 */
		return null;
	}

	/**
	 * @Title: setproperties
	 * @Description: TODO
	 * @author ahu@greendotcorp.com
	 * @param @param config
	 * @return void return type
	 * @throws
	 */

	public static void setproperties(Map<String, String> config) {

		Properties prop = new Properties();
		OutputStream output = null;
		// InputStream input=null;
		try {

			// output = new
			// FileOutputStream("src/main/resources/config.properties");
			output = new FileOutputStream("config.properties");
			// input = new FileInputStream("config.properties");

			// prop.load(input);
			// set the properties value
			for (String key : config.keySet()) {
				prop.setProperty(key, config.get(key));
			}
			// save properties to project root folder
			prop.store(output, null);

		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}

	/**
	 * @Title: getEnv
	 * @Description: TODO
	 * @author ahu@greendotcorp.com
	 * @param @param env
	 * @param @return
	 * @return String return type
	 * @throws
	 */

	public static String getEnv(String env) {
		String findenv = System.getenv(env);
		if (findenv == null) {
			findenv = getString(env);
		}
		return findenv.trim();
	}
}
