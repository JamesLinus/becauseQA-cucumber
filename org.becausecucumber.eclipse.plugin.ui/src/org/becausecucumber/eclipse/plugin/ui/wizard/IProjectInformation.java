/*
 * Created on 17 avr. 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.becausecucumber.eclipse.plugin.ui.wizard;

import java.util.Collection;
import java.util.Vector;

import org.eclipse.core.resources.IProject;

/**
 * @author sail7551
 *
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
@SuppressWarnings("restriction")
public class IProjectInformation {

	/**
	 * project reference
	 */
	private IProject project;
	/**
	 * list of source folder
	 */
	@SuppressWarnings("rawtypes")
	private Collection srcDirs;
	/**
	 * name of output folder
	 */
	private String outputDir;
	/**
	 * base folder of project
	 */
	private String basePath;

	/**
	 * Construct a new IProjectInformation with a IProject reference
	 * 
	 * @param p
	 *            project reference
	 */
	public IProjectInformation(IProject p) {
		super();
		project = p;
	}

	/**
	 * Construct a new IProjectInformation
	 * 
	 */
	@SuppressWarnings("rawtypes")
	public IProjectInformation() {
		srcDirs = new Vector();
		outputDir = null;
	}

	/**
	 * Add a source folder
	 * 
	 * @param dir
	 *            path of folder
	 */
	@SuppressWarnings("unchecked")
	public void addSrcDir(String dir) {
		srcDirs.add(dir);
	}

	@SuppressWarnings("rawtypes")
	public Collection getSrcDirs() {
		return srcDirs;
	}

	public void setOutputDir(String dir) {
		outputDir = dir;
	}

	public String getOutputDir() {
		return outputDir;
	}

	/**
	 * @return Returns the project.
	 */
	public IProject getProject() {
		return project;
	}

	/**
	 * @return Returns the basePath of project.
	 */
	public String getBasePath() {
		return basePath;
	}

	/**
	 * @param basePath
	 *            The basePath to set.
	 */
	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}
}
