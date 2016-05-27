package com.framework.utilities;

import java.io.File;

public class PathUtils {

	public static String filePath(String projectName) {

		ClassLoader classLoader = PathUtils.class.getClassLoader();
		File classpathRoot = new File(classLoader.getResource("").getPath());

		File filepathRoot = new File(classpathRoot.getAbsolutePath());
		String classesPath = filepathRoot.getParent();
		File targetPath = new File(new File(classesPath).getAbsolutePath());
		String projectFolder = targetPath.getParent();
		File filePath = new File(new File(projectFolder).getAbsolutePath());
		return filePath.getParent() + File.separator + projectName;
	}

}
