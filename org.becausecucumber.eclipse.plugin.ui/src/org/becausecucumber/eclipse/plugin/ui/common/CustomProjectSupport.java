package org.becausecucumber.eclipse.plugin.ui.common;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.becausecucumber.eclipse.plugin.ui.CucumberPeopleActivator;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.ide.IDE;

@SuppressWarnings("restriction")
public class CustomProjectSupport {

	static List<String> filelist = new ArrayList<String>();

	/**
	 * For this marvelous project we need to: - create the default Eclipse
	 * project - add the custom project nature - create the folder structure
	 *
	 * @param projectName
	 * @param location
	 * @param natureId
	 * @return
	 */

	public static IProject createRubyProject(String projectName, URI location) {
		Assert.isNotNull(projectName);
		// Assert.isTrue(projectName.trim().length(), 0);

		IProject project = createBaseProject(projectName, location);
		try {
			addNature(project);

			// String[] paths = {
			// "features/step_definitions","features/support"}; //$NON-NLS-1$
			// //$NON-NLS-2$

			Map<String, String> newpaths = new HashMap<String, String>();
			// newpaths.put("features/step_definations", "");
			newpaths.put("features", "templates/ruby");
			createProjectStructure(project, newpaths);

			// addProjectFolderStructure(project, paths);
			// createRubyFilesStructure(project);
		} catch (CoreException e) {
			e.printStackTrace();
			project = null;
		}

		return project;
	}

	public static void createProjectStructure(IProject project, Map<String, String> paths) {
		Iterator<String> iterator = paths.keySet().iterator();
		while (iterator.hasNext()) {
			String setprojectfolder = iterator.next();

			String pluginfolder = paths.get(setprojectfolder);

			if (pluginfolder != null) {

				// String path =
				// CucumberPeopleActivator.getInstance().getBundle().getEntry(currentdestination).getPath();

				// String
				// plugin=CucumberPeopleActivator.ORG_CUCUMBERPEOPLE_ECLIPSE_PLUGIN_CUCUMBER;

				File file = null;
				try {

					// http://blog.vogella.com/2010/07/06/reading-resources-from-plugin/
					// first option

					// URL url= new
					// URL("platform:/plugin/"+plugin+"/"+pluginfolder);
					// second option
					URL url = CucumberPeopleActivator.getInstance().getBundle().getEntry(pluginfolder);
					file = new File(FileLocator.resolve(url).toURI());
					filelist.clear();
					List<String> listFileRelatePath = listFileRelatePath(pluginfolder, file);

					for (String pluginfile : listFileRelatePath) {

						String fromfile = null;
						String tofile = null;

						if (setprojectfolder.endsWith(File.separator) && pluginfile.startsWith(File.separator)) {
							fromfile = pluginfile.substring(1);
							tofile = setprojectfolder + pluginfile.substring(1);

						} else {
							fromfile = pluginfile;
							int length = pluginfolder.length();
							if (pluginfile.endsWith(".old")) {
								pluginfile = pluginfile.substring(0, pluginfile.length() - 4);
							}
							tofile = setprojectfolder + pluginfile.substring(length);
						}

						String parent = new File(tofile).getParent();
						IFolder newprojectfolder = project.getFolder(parent);
						createFolder(newprojectfolder);

						InputStream orginalfile = CucumberPeopleActivator.getInstance().getBundle().getEntry(fromfile)
								.openStream();

						IFile newtofile = project.getFile(tofile);
						if (!newtofile.exists()) {
							newtofile.create(orginalfile, true, null);
						}
					}

				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (CoreException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				/*
				 * IResource[] members = etcFolders.members(); for(IResource
				 * resource:members){ if(resource instanceof IFile){
				 * 
				 * IFile foundfile=(IFile) resource; String filename =
				 * foundfile.getName(); String fromfile=null; String
				 * tofile=null; if
				 * (currentfolder.substring(currentfolder.length()-1)==File.
				 * pathSeparator||
				 * currentdestination.substring(currentdestination.length()-1)==
				 * File.pathSeparator){ fromfile=currentfolder+filename;
				 * tofile=currentdestination+filename;
				 * 
				 * }else{ fromfile=currentfolder+File.pathSeparator+filename;
				 * tofile=currentdestination+File.pathSeparator+filename; }
				 * 
				 * InputStream orginalfile =
				 * CucumberPeopleActivator.getInstance().getBundle().getEntry(
				 * fromfile).openStream();
				 * 
				 * IFile newtofile = project.getFile(tofile);
				 * newtofile.create(orginalfile, true, null);
				 * 
				 * } } } catch (CoreException e) { // TODO Auto-generated catch
				 * block e.printStackTrace(); } catch (IOException e) { // TODO
				 * Auto-generated catch block e.printStackTrace(); }
				 */

			} else {
				IFolder newprojectfolder = project.getFolder(setprojectfolder);
				createFolder(newprojectfolder);
			}
		}
	}

	public static List<String> listFileRelatePath(String relatefile, File file) throws IOException {
		// File[] baselistfiles = file.listFiles();

		for (File basefile : file.listFiles()) {
			if (basefile.isDirectory()) {
				listFileRelatePath(relatefile, basefile);
			} else {
				String absolutePath = basefile.getAbsolutePath();
				absolutePath = absolutePath.replace(File.separator, "/");
				int firststart = absolutePath.indexOf(relatefile);
				// int length = relatefile.length();
				String substrings = absolutePath.substring(firststart);
				filelist.add(substrings);
			}
		}
		return filelist;
	}

	/**
	 * Just do the basics: create a basic project.
	 *
	 * @param location
	 * @param projectName
	 */
	private static IProject createBaseProject(String projectName, URI location) {
		// it is acceptable to use the ResourcesPlugin class
		IProject newProject = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);

		if (!newProject.exists()) {
			URI projectLocation = location;
			IProjectDescription desc = newProject.getWorkspace().newProjectDescription(newProject.getName());
			if (location != null && ResourcesPlugin.getWorkspace().getRoot().getLocationURI().equals(location)) {
				projectLocation = null;
			}

			desc.setLocationURI(projectLocation);
			try {
				newProject.create(desc, null);
				if (!newProject.isOpen()) {
					newProject.open(null);
				}
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}

		return newProject;
	}

	private static void createFolder(IFolder folder) {
		IContainer parent = folder.getParent();
		if (parent instanceof IFolder) {
			IFolder parentfolder = (IFolder) parent;
			if (!parentfolder.exists()) {
				createFolder(parentfolder);
			}
		}
		if (!folder.exists()) {
			try {
				folder.create(true, true, null);
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}
		}
		// folder.
	}

	/**
	 * Create a folder structure with a parent root, overlay, and a few child
	 * folders.
	 *
	 * @param newProject
	 * @param paths
	 * @throws CoreException
	 */
	public static void addProjectFolderStructure(IProject newProject, String[] paths) throws CoreException {
		for (String path : paths) {
			IFolder etcFolders = newProject.getFolder(path);
			createFolder(etcFolders);
		}
	}

	public static void addProjectFileStructure(IProject newProject, String pluginfile, String newprojectfile) {
		try {
			InputStream samplefile = CucumberPeopleActivator.getInstance().getBundle().getEntry(pluginfile)
					.openStream();

			IFile sample = newProject.getFile(newprojectfile);
			sample.create(samplefile, true, null);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void openProjectPOMEditor(IProject newProject) {
		IFile pomfile = newProject.getFile("pom.xml");
		IWorkbench workbench = CucumberPeopleActivator.getInstance().getWorkbench();
		if (pomfile != null) {
			try {
				IDE.openEditor(workbench.getActiveWorkbenchWindow().getActivePage(), pomfile);
			} catch (PartInitException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	/**
	 * Create a folder structure with a parent root, overlay, and a few child
	 * folders.
	 *
	 * @param newProject
	 * @param paths
	 * @throws CoreException
	 */
	public static void createJavaMavenFilesStructure(IProject newProject) throws CoreException {
		try {
			InputStream readmefile = CucumberPeopleActivator.getInstance().getBundle()
					.getEntry("templates/java/readme.txt").openStream();
			InputStream featurefile = CucumberPeopleActivator.getInstance().getBundle()
					.getEntry("templates/sample.feature").openStream();

			InputStream log4jfile = CucumberPeopleActivator.getInstance().getBundle()
					.getEntry("templates/java/resources/log4j.xml").openStream();
			/*
			 * InputStream auto3infofile =
			 * CucumberPeopleActivator.getInstance().getBundle().getEntry(
			 * "templates/java/resources/Au3Info.exe").openStream(); InputStream
			 * autoitxchmlfile =
			 * CucumberPeopleActivator.getInstance().getBundle().getEntry(
			 * "templates/java/resources/AutoItX.chm").openStream(); InputStream
			 * autoit3file =
			 * CucumberPeopleActivator.getInstance().getBundle().getEntry(
			 * "templates/java/resources/AutoItX3.dll").openStream();
			 * InputStream autoit364ile =
			 * CucumberPeopleActivator.getInstance().getBundle().getEntry(
			 * "templates/java/resources/AutoItX3_x64.dll").openStream();
			 * InputStream jacobfile =
			 * CucumberPeopleActivator.getInstance().getBundle().getEntry(
			 * "templates/java/resources/jacob.dll").openStream(); InputStream
			 * jacob64file =
			 * CucumberPeopleActivator.getInstance().getBundle().getEntry(
			 * "templates/java/resources/jacob-x64.dll").openStream();
			 * 
			 * 
			 * InputStream basesetupfile =
			 * CucumberPeopleActivator.getInstance().getBundle().getEntry(
			 * "templates/java/runner/BaseSetup.java").openStream(); InputStream
			 * runjvmfile =
			 * CucumberPeopleActivator.getInstance().getBundle().getEntry(
			 * "templates/java/runner/RunCukesJVMRunner.java").openStream();
			 * InputStream cuketestfile =
			 * CucumberPeopleActivator.getInstance().getBundle().getEntry(
			 * "templates/java/runner/RunCukesTest.java").openStream();
			 * InputStream cukejunitfile =
			 * CucumberPeopleActivator.getInstance().getBundle().getEntry(
			 * "templates/java/runner/RunCukesTestJUnit.java").openStream();
			 */

			IFile pomfile = newProject.getFile("pom.xml");
			IFile readme = newProject.getFile("readme.txt");
			IFile feature = newProject.getFile("src/test/resources/features/sample.feature");

			IFile log4j = newProject.getFile("src/main/resources/log4j.xml");
			/*
			 * IFile auto3info =
			 * newProject.getFile("src/main/resources/Au3Info.exe"); IFile
			 * autoitxchm =
			 * newProject.getFile("src/main/resources/AutoItX.chm"); IFile
			 * autoit3 = newProject.getFile("src/main/resources/AutoItX3.dll");
			 * IFile autoit364 =
			 * newProject.getFile("src/main/resources/AutoItX3_x64.dll"); IFile
			 * jacob = newProject.getFile("src/main/resources/jacob.dll"); IFile
			 * jacob64 = newProject.getFile("src/main/resources/jacob-x64.dll");
			 * 
			 * IFile basesetup =
			 * newProject.getFile("src/test/java/cucumber_runner/BaseSetup.java"
			 * ); IFile runjvm = newProject.getFile(
			 * "src/test/java/cucumber_runner/RunCukesJVMRunner.java"); IFile
			 * cuketest = newProject.getFile(
			 * "src/test/java/cucumber_runner/RunCukesTest.java"); IFile
			 * cukejunit = newProject.getFile(
			 * "src/test/java/cucumber_runner/RunCukesTestJUnit.java");
			 */

			feature.create(featurefile, true, null);
			readme.create(readmefile, true, null);

			log4j.create(log4jfile, true, null);
			/*
			 * auto3info.create(auto3infofile, true, null);
			 * autoitxchm.create(autoitxchmlfile, true, null);
			 * autoit3.create(autoit3file, true, null);
			 * autoit364.create(autoit364ile, true, null);
			 * jacob.create(jacobfile, true, null); jacob64.create(jacob64file,
			 * true, null);
			 * 
			 * basesetup.create(basesetupfile, true, null);
			 * runjvm.create(runjvmfile, true, null);
			 * cuketest.create(cuketestfile, true, null);
			 * cukejunit.create(cukejunitfile, true, null);
			 */

			IWorkbench workbench = CucumberPeopleActivator.getInstance().getWorkbench();
			if (pomfile != null) {
				try {
					IDE.openEditor(workbench.getActiveWorkbenchWindow().getActivePage(), pomfile);
				} catch (PartInitException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void addNature(IProject project) throws CoreException {
		if (!project.hasNature(ProjectNature.NATURE_ID)) {
			IProjectDescription description = project.getDescription();
			String[] prevNatures = description.getNatureIds();
			String[] newNatures = new String[prevNatures.length + 1];
			System.arraycopy(prevNatures, 0, newNatures, 0, prevNatures.length);
			newNatures[prevNatures.length] = ProjectNature.NATURE_ID;
			description.setNatureIds(newNatures);

			IProgressMonitor monitor = null;
			project.setDescription(description, monitor);
		}
	}

	public static void main(String[] args) {
		// try {
		/*
		 * List a=listFileRelatePath("templates",new File(
		 * "D:\\Downloads\\fourthversion\\org.cucumberpeople.eclipse.plugin.ui\\templates"
		 * )); System.out.println(a.toString());
		 * 
		 * String b="templates/ruby/support/env.rb.old"; String
		 * c="templates/ruby"; // int d =c.length(); String substring =
		 * b.substring(0,b.length()-4); System.out.println(substring); } catch
		 * (IOException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
	}
}