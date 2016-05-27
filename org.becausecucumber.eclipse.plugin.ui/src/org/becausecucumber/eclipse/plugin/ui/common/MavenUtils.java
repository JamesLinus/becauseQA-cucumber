package org.becausecucumber.eclipse.plugin.ui.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;

import static org.eclipse.m2e.core.ui.internal.editing.PomEdits.ARTIFACT_ID;
import static org.eclipse.m2e.core.ui.internal.editing.PomEdits.CLASSIFIER;
import static org.eclipse.m2e.core.ui.internal.editing.PomEdits.DEPENDENCIES;
import static org.eclipse.m2e.core.ui.internal.editing.PomEdits.DEPENDENCY;
import static org.eclipse.m2e.core.ui.internal.editing.PomEdits.GROUP_ID;
import static org.eclipse.m2e.core.ui.internal.editing.PomEdits.SCOPE;
import static org.eclipse.m2e.core.ui.internal.editing.PomEdits.TYPE;
import static org.eclipse.m2e.core.ui.internal.editing.PomEdits.VERSION;
import static org.eclipse.m2e.core.ui.internal.editing.PomEdits.PLUGIN;
import static org.eclipse.m2e.core.ui.internal.editing.PomEdits.childEquals;
import static org.eclipse.m2e.core.ui.internal.editing.PomEdits.findChild;
import static org.eclipse.m2e.core.ui.internal.editing.PomEdits.getChild;
import static org.eclipse.m2e.core.ui.internal.editing.PomEdits.performOnDOMDocument;
import static org.eclipse.m2e.core.ui.internal.editing.PomEdits.setText;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.m2e.core.ui.internal.UpdateMavenProjectJob;

import org.eclipse.m2e.core.ui.internal.editing.PomEdits.Operation;
import org.eclipse.m2e.core.ui.internal.editing.PomEdits.OperationTuple;
import org.eclipse.m2e.core.ui.internal.editing.PomHelper;
import org.eclipse.m2e.core.ui.internal.wizards.MavenProjectWizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkingSet;

import org.eclipse.wst.xml.core.internal.provisional.format.FormatProcessorXML;
import org.w3c.dom.Document;

@SuppressWarnings("restriction")
public class MavenUtils extends MavenProjectWizard implements INewWizard {

	@SuppressWarnings("unused")
	private IWorkbench workbench;
	private IStructuredSelection selection = null;
	// private MavenProjectWizard maven;
	public static final String BUILD = "build";//$NON-NLS-1$
	public static final String PLUGINS = "plugins";//$NON-NLS-1$

	public MavenUtils() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		// TODO Auto-generated method stub
		// super.init(workbench, selection);
		this.workbench = workbench;
		this.selection = selection;
		setWindowTitle("Cucumber Java Project Wizard");

	}

	@Override
	public void addPages() {
		// TODO Auto-generated method stub

		super.addPages();
	}

	@Override
	public boolean performFinish() {
		// TODO Auto-generated method stub
		/*
		 * addCucumberDependency("org.seleniumhq.selenium","selenium-server",
		 * null,null,null);
		 * addCucumberDependency("info.cukes","cucumber-java",null,null,null);
		 * addCucumberDependency("info.cukes","cucumber-junit",null,null,null);
		 * addCucumberDependency("org.testng","testng",null,null,null);
		 * addCucumberDependency("org.apache.logging.log4j","log4j-api",null,
		 * null,null);
		 * addCucumberDependency("org.apache.logging.log4j","log4j-core",null,
		 * null,null);
		 * addCucumberDependency("log4j","apache-log4j-extras",null,null,null);
		 */
		// return super.performFinish();
		// addCucumberDependency("info.cukes","cucumber-java",null,null,null);
		System.out.println("maven done now");
		return super.performFinish();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		/*
		 * addCucumberDependency("org.seleniumhq.selenium","selenium-server",
		 * null,null,null);
		 * addCucumberDependency("info.cukes","cucumber-java",null,null,null);
		 * addCucumberDependency("info.cukes","cucumber-junit",null,null,null);
		 * addCucumberDependency("org.testng","testng",null,null,null);
		 * addCucumberDependency("org.apache.logging.log4j","log4j-api",null,
		 * null,null);
		 * addCucumberDependency("org.apache.logging.log4j","log4j-core",null,
		 * null,null);
		 * addCucumberDependency("log4j","apache-log4j-extras",null,null,null);
		 */
		// System.out.println("dispose wizard now :");
		super.dispose();
		// addCucumberDependency("info.cukes","cucumber-java",null,null,null);
	}

	/*
	 * @Override public IWizardPage getNextPage(IWizardPage page) { // TODO
	 * Auto-generated method stub maven=new MavenProjectWizard();
	 * maven.init(workbench, selection); WizardDialog dialog=new
	 * WizardDialog(workbench.getDisplay().getActiveShell(), maven);
	 * dialog.open(); return super.getNextPage(page); }
	 */

	public static void updateDepenency(IProject project) {
		IProject[] projects = { project };
		(new UpdateMavenProjectJob(projects, false, true, true, true, true)).schedule();

		// UpdateMavenProjectCommandHandler.openUpdateProjectsDialog(workbench.getActiveWorkbenchWindow().getShell(),
		// getProjects(true));
	}

	public static void addMavenProperty(IProject project, final String propertyname, final String propertyvalue) {
		IFile file = project.getFile("pom.xml");
		if (file == null) {
			return;
		}

		try {
			performOnDOMDocument(new OperationTuple(file, new Operation() {

				@Override
				public void process(Document document) {
					// TODO Auto-generated method stub
					Element properties = getChild(document.getDocumentElement(), "properties");
					Element property = findChild(properties, propertyname);
					if (property == null) {
						if (propertyvalue == null) {
							property = createElement(properties, propertyname);
						} else {
							property = createElement(properties, propertyname);
						}
					}
					setText(property, propertyvalue);
					format(property);
				}

			}));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void addMavenRepositories(IProject project, final String propertyname, final String propertyvalue) {
		IFile file = project.getFile("pom.xml");
		if (file == null) {
			return;
		}

		try {
			performOnDOMDocument(new OperationTuple(file, new Operation() {

				@Override
				public void process(Document document) {
					// TODO Auto-generated method stub
					Element properties = getChild(document.getDocumentElement(), "properties");
					Element property = findChild(properties, propertyname);
					if (property == null) {
						if (propertyvalue == null) {
							property = createElement(properties, propertyname);
						} else {
							property = createElement(properties, propertyname);
						}
					}
					setText(property, propertyvalue);
					format(property);
				}

			}));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void addMavenreParents(IProject project, final String propertyname, final String propertyvalue) {
		IFile file = project.getFile("pom.xml");
		if (file == null) {
			return;
		}

		try {
			performOnDOMDocument(new OperationTuple(file, new Operation() {

				@Override
				public void process(Document document) {
					// TODO Auto-generated method stub
					Element properties = getChild(document.getDocumentElement(), "parent");
					Element property = findChild(properties, propertyname);
					if (property == null) {
						if (propertyvalue == null) {
							property = createElement(properties, propertyname);
						} else {
							property = createElement(properties, propertyname);
						}
					}
					setText(property, propertyvalue);
					format(property);
				}

			}));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void addMavenDependencyManagement(IProject project, final String groupid, final String artifact_id,
			final String version) {

		IFile file = project.getFile("pom.xml");
		if (file == null) {
			return;
		}

		try {
			performOnDOMDocument(new OperationTuple(file, new Operation() {
				public void process(Document document) {
					Element pluginsEl = getChild(document.getDocumentElement(), "dependencyManagement", "dependencies");
					// PomHelper.createPlugin(pluginsEl, groupid, artifact_id,
					// version);
					createDependencyNode(pluginsEl, groupid, artifact_id, version);
				}
			}));
		} catch (Exception ex) {
			// log.error("Can't add plugin to " + file, ex); //$NON-NLS-1$
		}

	}

	/**
	 * formats the node (and content). please make sure to only format the node
	 * you have created..
	 * 
	 * @param newNode
	 */
	public static void format(Node newNode) {
		if (newNode.getParentNode() != null && newNode.equals(newNode.getParentNode().getLastChild())) {
			// add a new line to get the newly generated content correctly
			// formatted.
			newNode.getParentNode().appendChild(newNode.getParentNode().getOwnerDocument().createTextNode("\n")); //$NON-NLS-1$
		}
		FormatProcessorXML formatProcessor = new FormatProcessorXML();
		// ignore any line width settings, causes wrong formatting of
		// <foo>bar</foo>
		formatProcessor.getFormatPreferences().setLineWidth(2000);
		formatProcessor.formatNode(newNode);
	}

	/**
	 * helper method, creates a subelement, does not format result.
	 * 
	 * @param parent
	 *            the parent element
	 * @param name
	 *            the name of the new element
	 * @return the created element
	 */
	public static Element createElement(Element parent, String name) {
		Document doc = parent.getOwnerDocument();
		Element newElement = doc.createElement(name);
		parent.appendChild(newElement);
		return newElement;
	}

	/**
	 * helper method, creates a subelement with text embedded. does not format
	 * the result. primarily to be used in cases like
	 * <code>&lt;goals&gt;&lt;goal&gt;xxx&lt;/goal&gt;&lt;/goals&gt;</code>
	 * 
	 * @param parent
	 * @param name
	 * @param value
	 * @return
	 */
	public static Element createElementWithText(Element parent, String name, String value) {
		Document doc = parent.getOwnerDocument();
		Element newElement = doc.createElement(name);
		parent.appendChild(newElement);
		newElement.appendChild(doc.createTextNode(value));
		return newElement;
	}

	public static void addMavenDependency(IProject project, final String groupid, final String artifact_id,
			final String version, final String type, final String Classifier, final String scope) {
		IFile file = project.getFile("pom.xml");
		if (file == null) {
			return;
		}

		try {
			performOnDOMDocument(new OperationTuple(file, new Operation() {

				@Override
				public void process(Document document) {
					// TODO Auto-generated method stub
					Element depsEl = getChild(document.getDocumentElement(), DEPENDENCIES);
					Element dep = findChild(depsEl, DEPENDENCY, childEquals(GROUP_ID, groupid),
							childEquals(ARTIFACT_ID, artifact_id));

					if (dep == null) {
						if (version != null) {
							dep = PomHelper.createDependency(depsEl, groupid, artifact_id, version);
						} else {
							dep = PomHelper.createDependency(depsEl, groupid, artifact_id, "RELEASE");
						}

					} else {
						// only set version if already exists
						if (version != null) {
							setText(getChild(dep, VERSION), version);

						} else {
							setText(getChild(dep, VERSION), "RELEASE");
						}

					}

					if (type != null //
							&& !"jar".equals(type) // //$NON-NLS-1$
							&& !"null".equals(type)) { // guard //$NON-NLS-1$
														// against
														// MNGECLIPSE-622

						setText(getChild(dep, TYPE), type);
					}

					if (Classifier != null) {
						setText(getChild(dep, CLASSIFIER), Classifier);
					}

					if (scope != null && !"compile".equals(scope)) { //$NON-NLS-1$
						setText(getChild(dep, SCOPE), scope);
					}

				}

			}));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// action folder for these
	public static void addMavenPlugin(IProject project, final String groupid, final String artifact_id,
			final String version) {
		IFile file = project.getFile("pom.xml");
		if (file == null) {
			return;
		}

		try {
			performOnDOMDocument(new OperationTuple(file, new Operation() {
				public void process(Document document) {
					Element pluginsEl = getChild(document.getDocumentElement(), BUILD, PLUGINS);
					PomHelper.createPlugin(pluginsEl, groupid, artifact_id, version);
				}
			}));
		} catch (Exception ex) {
			// log.error("Can't add plugin to " + file, ex); //$NON-NLS-1$
		}

	}

	// action folder for these
	public static void addMavenRunPlugin(IProject project, final String groupid, final String artifact_id,
			final String version, final String mainclassname) {

		final String EXECUTIONS = "executions";
		final String EXECUTION = "execution";
		final String PHASE = "phase";
		final String GOALS = "goals";
		final String GOAL = "goal";
		final String CONFIGURATION = "configuration";
		final String MAINCLASS = "mainClass";
		final String CLEANUPDAEMON = "cleanupDaemonThreads";
		// final String ARGUMENTS="arguments";
		// final String ARGUMENT="argument";

		IFile file = project.getFile("pom.xml");
		if (file == null) {
			return;
		}

		try {
			performOnDOMDocument(new OperationTuple(file, new Operation() {
				public void process(Document document) {
					Element pluginsEl = getChild(document.getDocumentElement(), BUILD, PLUGINS);
					/*
					 * PomHelper.createPlugin(pluginsEl, groupid, artifact_id,
					 * version);
					 */

					// create the basic elements
					Document doc = pluginsEl.getOwnerDocument();
					Element plug = doc.createElement(PLUGIN);
					pluginsEl.appendChild(plug);

					createElementWithText(plug, GROUP_ID, groupid);

					createElementWithText(plug, ARTIFACT_ID, artifact_id);

					createElementWithText(plug, VERSION, version);

					format(plug);

					// create the specified element
					createElement(plug, EXECUTIONS);
					Element executionselement = getChild(plug, EXECUTIONS);
					createElement(executionselement, EXECUTION);

					Element executionelement = getChild(executionselement, EXECUTION);
					createElementWithText(executionelement, PHASE, "package");
					createElement(executionelement, GOALS);
					Element goalselement = getChild(executionelement, GOALS);

					// Element goalelement = getChild(goalselement, GOAL);
					createElementWithText(goalselement, GOAL, "java");

					createElement(executionelement, CONFIGURATION);
					Element configurationelement = getChild(executionelement, CONFIGURATION);

					createElementWithText(configurationelement, MAINCLASS, mainclassname);
					createElementWithText(configurationelement, CLEANUPDAEMON, "false");

					/// add the argument for the main class

				}

			}));
		} catch (Exception ex) {
			// log.error("Can't add plugin to " + file, ex); //$NON-NLS-1$
		}

	}

	public static void addMavenJunitPlugin(IProject project, final String groupid, final String artifact_id,
			final String version) {

		final String CONFIGURATION = "configuration";
		final String skipTests = "skipTests";
		final String testFailureIgnore = "testFailureIgnore";
		final String reuseForks = "reuseForks";
		final String forkCount = "forkCount";

		IFile file = project.getFile("pom.xml");
		if (file == null) {
			return;
		}

		try {
			performOnDOMDocument(new OperationTuple(file, new Operation() {
				public void process(Document document) {
					Element pluginsEl = getChild(document.getDocumentElement(), BUILD, PLUGINS);
					/*
					 * PomHelper.createPlugin(pluginsEl, groupid, artifact_id,
					 * version);
					 */

					// create the basic elements
					Document doc = pluginsEl.getOwnerDocument();
					Element plug = doc.createElement(PLUGIN);
					pluginsEl.appendChild(plug);

					createElementWithText(plug, GROUP_ID, groupid);

					createElementWithText(plug, ARTIFACT_ID, artifact_id);

					createElementWithText(plug, VERSION, version);

					format(plug);

					createElement(plug, CONFIGURATION);
					Element configurationelement = getChild(plug, CONFIGURATION);

					createElementWithText(configurationelement, skipTests, "false");
					createElementWithText(configurationelement, testFailureIgnore, "false");
					createElementWithText(configurationelement, reuseForks, "true");
					createElementWithText(configurationelement, forkCount, "0");

					/// add the argument for the main class

				}

			}));
		} catch (Exception ex) {
			// log.error("Can't add plugin to " + file, ex); //$NON-NLS-1$
		}
	}

	/**
	 * Returns all the Maven projects found in the given selection. If no
	 * projects are found in the selection and <code>includeAll</code> is true,
	 * all workspace projects are returned.
	 * 
	 * @param selection
	 * @param includeAll
	 *            flag to return all workspace projects if selection doesn't
	 *            contain any Maven projects.
	 * @return an array of {@link IProject} containing all the Maven projects
	 *         found in the given selection, or all the workspace projects if no
	 *         Maven project was found and <code>includeAll</code> is true.
	 * @since 1.4.0
	 */
	public IProject[] getProjects(boolean includeAll) {
		ArrayList<IProject> projectList = new ArrayList<IProject>();

		for (Iterator<?> it = selection.iterator(); it.hasNext();) {
			Object o = it.next();
			if (o instanceof IProject) {
				safeAdd((IProject) o, projectList);
			} else if (o instanceof IWorkingSet) {
				IWorkingSet workingSet = (IWorkingSet) o;
				for (IAdaptable adaptable : workingSet.getElements()) {
					IProject project = (IProject) adaptable.getAdapter(IProject.class);
					safeAdd(project, projectList);
				}
			} else if (o instanceof IResource) {
				safeAdd(((IResource) o).getProject(), projectList);
			} else if (o instanceof IAdaptable) {
				IAdaptable adaptable = (IAdaptable) o;
				IProject project = (IProject) adaptable.getAdapter(IProject.class);
				safeAdd(project, projectList);
			}
		} // for loop

		if (projectList.isEmpty() && includeAll) {
			return ResourcesPlugin.getWorkspace().getRoot().getProjects();
		}
		return projectList.toArray(new IProject[projectList.size()]);
	}

	private void safeAdd(IProject project, List<IProject> projectList) {
		try {
			if (project != null && project.isAccessible() && project.hasNature("org.eclipse.m2e.core.maven2Nature")
					&& !projectList.contains(project)) {
				projectList.add(project);
			}
		} catch (CoreException ex) {
			// log.error(ex.getMessage(), ex);
		}
	}

	/**
	 * creates and adds new plugin to the parent. Formats the result.
	 * 
	 * @param parentList
	 * @param groupId
	 *            null or value
	 * @param artifactId
	 *            never null
	 * @param version
	 *            null or value
	 * @return
	 */
	public static Element createDependencyNode(Element parentList, String groupId, String artifactId, String version) {
		Document doc = parentList.getOwnerDocument();
		Element plug = doc.createElement("dependency");
		parentList.appendChild(plug);

		if (groupId != null) {
			createElementWithText(plug, GROUP_ID, groupId);
		}
		createElementWithText(plug, ARTIFACT_ID, artifactId);
		if (version != null) {
			createElementWithText(plug, VERSION, version);
		}
		format(plug);
		return plug;
	}

}
