package com.framework.utilities;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.apache.maven.project.MavenProject;

public class MavenUtils {

	public static Properties properties = null;

	public static String getProperty(String propertyname) {
		Model model = null;
		FileReader reader = null;
		String property = null;

		if (properties == null) {
			MavenXpp3Reader mavenreader = new MavenXpp3Reader();
			File pomfile = new File("pom.xml");
			try {
				reader = new FileReader(pomfile);
				model = mavenreader.read(reader);
				model.setPomFile(pomfile);
			} catch (Exception ex) {
				System.out.println("met the maven exception here is:" + ex);
			}
			MavenProject project = new MavenProject(model);
			// String artifactId = project.getArtifactId();
			properties = project.getProperties();
		}
		property = properties.getProperty(propertyname);
		return property;
	}

	public static String getProjectName() {
		Model model = null;
		FileReader reader = null;
		String property = null;

		if (property == null) {
			MavenXpp3Reader mavenreader = new MavenXpp3Reader();
			File pomfile = new File("pom.xml");
			try {
				reader = new FileReader(pomfile);
				model = mavenreader.read(reader);
				model.setPomFile(pomfile);
			} catch (Exception ex) {
				System.out.println(ex);
			}
			MavenProject project = new MavenProject(model);
			property = project.getArtifactId();

		}
		// property = properties.getProperty(propertyname);
		return property;
	}

	public static void main(String[] args) {

		Model model = null;
		FileReader reader = null;
		MavenXpp3Reader mavenreader = new MavenXpp3Reader();
		File pomfile = new File("pom.xml");
		try {
			reader = new FileReader(pomfile);
			model = mavenreader.read(reader);
			model.setPomFile(pomfile);
		} catch (Exception ex) {
		}
		MavenProject project = new MavenProject(model);
		Properties properties = project.getProperties();
		String property = properties.getProperty("version.compile.plugin");
		System.out.println(property);
	}
}
