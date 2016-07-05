package org.becausecucumber.eclipse.plugin.ui.wizard.selenium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.becausecucumber.eclipse.plugin.ui.CucumberPeopleActivator;
import org.becausecucumber.eclipse.plugin.ui.common.CustomProjectSupport;
import org.becausecucumber.eclipse.plugin.ui.common.MavenUtils;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.internal.IMavenConstants;
import org.eclipse.m2e.core.ui.internal.MavenImages;
import org.eclipse.m2e.core.ui.internal.actions.SelectionUtil;
import org.eclipse.m2e.core.ui.internal.wizards.AbstractCreateMavenProjectJob;
import org.eclipse.m2e.core.ui.internal.wizards.AbstractMavenProjectWizard;
import org.eclipse.m2e.core.ui.internal.wizards.MavenProjectWizardArchetypePage;
import org.eclipse.m2e.core.ui.internal.wizards.MavenProjectWizardArchetypeParametersPage;
//import org.eclipse.m2e.core.ui.internal.wizards.MavenProjectWizardArtifactPage;
import org.eclipse.m2e.core.ui.internal.wizards.MavenProjectWizardLocationPage;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.INewWizard;
import org.apache.maven.archetype.catalog.Archetype;
import org.apache.maven.model.Model;
import org.eclipse.m2e.core.ui.internal.Messages;

@SuppressWarnings("restriction")
public class NewCucumberJavaMavenWizard extends AbstractMavenProjectWizard implements INewWizard {

	/** The wizard page for gathering general project information. */
	protected MavenProjectWizardLocationPage locationPage;

	/** The archetype selection page. */
	protected MavenProjectWizardArchetypePage archetypePage;

	/** The wizard page for gathering Maven2 project information. */
	protected MavenProjectWizardCustomArtifactPage artifactPage;

	/** The wizard page for gathering archetype project information. */
	protected MavenProjectWizardArchetypeParametersPage parametersPage;

	protected Button simpleProject;

	public boolean createdbefore = false;

	protected String seleniumversion;

	protected String env;
	protected String browser;

	/**
	 * Default constructor. Sets the title and image of the wizard.
	 */
	public NewCucumberJavaMavenWizard() {
		super();
		setWindowTitle("Cucumber Project from Maven for Java");
		setDefaultPageImageDescriptor(MavenImages.WIZ_NEW_PROJECT);
		setNeedsProgressMonitor(true);
	}

	public void addPages() {
		final ImageDescriptor cukeimage = CucumberPeopleActivator.getInstance().getImageRegistry()
				.getDescriptor(CucumberPeopleActivator.CUKE_IMGE);
		locationPage = new MavenProjectWizardLocationPage(importConfiguration, //
				Messages.wizardProjectPageProjectTitle, Messages.wizardProjectPageProjectDescription, workingSets) { //

			protected void createAdditionalControls(Composite container) {
				locationPage.setTitle("Create New Selenium Project");
				locationPage.setDescription("Just Click the Next Button to continue to create the project.");
				locationPage.setImageDescriptor(cukeimage);

				simpleProject = new Button(container, SWT.CHECK);
				simpleProject.setText(Messages.wizardProjectPageProjectSimpleProject);
				simpleProject.setSelection(true);
				simpleProject.setEnabled(false);
				simpleProject.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false, 3, 1));
				simpleProject.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e) {
						validate();
					}
				});

				validate();

				Label label = new Label(container, SWT.NONE);
				GridData labelData = new GridData(SWT.FILL, SWT.TOP, false, false, 3, 1);
				labelData.heightHint = 10;
				label.setLayoutData(labelData);
			}

			/**
			 * Skips the archetype selection page if the user chooses a simple
			 * project.
			 */
			public IWizardPage getNextPage() {
				return getPage(simpleProject.getSelection() ? "MavenProjectWizardArtifactPage" //$NON-NLS-1$
						: "MavenProjectWizardArchetypePage"); //$NON-NLS-1$
			}
		};
		locationPage.setLocationPath(SelectionUtil.getSelectedLocation(selection));

		archetypePage = new MavenProjectWizardArchetypePage(importConfiguration);
		parametersPage = new MavenProjectWizardArchetypeParametersPage(importConfiguration);
		artifactPage = new MavenProjectWizardCustomArtifactPage(importConfiguration);

		artifactPage.setTitle("Create New Selenium Project");
		artifactPage.setDescription(
				"Please input some project data for selenium,Then click the Finish button you need to wait a few time to complete the maven project.");

		artifactPage.setImageDescriptor(cukeimage);

		addPage(locationPage);
		addPage(archetypePage);
		addPage(parametersPage);
		addPage(artifactPage);
	}

	/** Adds the listeners after the page controls are created. */
	public void createPageControls(Composite pageContainer) {
		super.createPageControls(pageContainer);

		simpleProject.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				boolean isSimpleproject = simpleProject.getSelection();
				archetypePage.setUsed(!isSimpleproject);
				parametersPage.setUsed(!isSimpleproject);
				artifactPage.setUsed(isSimpleproject);
				getContainer().updateButtons();
			}
		});

		boolean isSimpleproject = simpleProject.getSelection();
		archetypePage.setUsed(!isSimpleproject);
		parametersPage.setUsed(!isSimpleproject);
		artifactPage.setUsed(isSimpleproject);
		getContainer().updateButtons();

		archetypePage.addArchetypeSelectionListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent selectionchangedevent) {
				parametersPage.setArchetype(archetypePage.getArchetype());
				getContainer().updateButtons();
			}
		});

		// locationPage.addProjectNameListener(new ModifyListener() {
		// public void modifyText(ModifyEvent e) {
		// parametersPage.setProjectName(locationPage.getProjectName());
		// artifactPage.setProjectName(locationPage.getProjectName());
		// }
		// });
	}

	/** Returns the model. */
	public Model getModel() {
		if (simpleProject.getSelection()) {
			return artifactPage.getModel();
		}
		return parametersPage.getModel();
	}

	/**
	 * To perform the actual project creation, an operation is created and run
	 * using this wizard as execution context. That way, messages about the
	 * progress of the project creation are displayed inside the wizard.
	 */
	public boolean performFinish() {
		// First of all, we extract all the information from the wizard pages.
		// Note that this should not be done inside the operation we will run
		// since many of the wizard pages' methods can only be invoked from
		// within
		// the SWT event dispatcher thread. However, the operation spawns a new
		// separate thread to perform the actual work, i.e. accessing SWT
		// elements
		// from within that thread would lead to an exception.

		// final IProject project = locationPage.getProjectHandle();
		// final String projectName = locationPage.getProjectName();

		// Get the location where to create the project. For some reason, when
		// using
		// the default workspace location for a project, we have to pass null
		// instead of the actual location.

		// if created the baseautomation project before

		// IProject[] projects =
		// ResourcesPlugin.getWorkspace().getRoot().getProjects();

		final Model model = getModel();
		seleniumversion = artifactPage.getSelenium();
		browser = artifactPage.getBrowser();
		env = artifactPage.getEnvironment();

		final Model basemodel = getBaseFrameworkModel();
		@SuppressWarnings("deprecation")
		final String projectName = importConfiguration.getProjectName(model);
		@SuppressWarnings("deprecation")
		final String baseProjectName = importConfiguration.getProjectName(basemodel);

		IProject[] projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
		for (IProject findproject : projects) {
			if (findproject.getName().equals(baseProjectName)) {
				createdbefore = true;
				break;
			}
		}

		@SuppressWarnings("deprecation")
		IStatus nameStatus = importConfiguration.validateProjectName(model);
		@SuppressWarnings("deprecation")
		IStatus namebaseStatus = importConfiguration.validateProjectName(basemodel);
		if (!nameStatus.isOK()) {
			MessageDialog.openError(getShell(), NLS.bind(Messages.wizardProjectJobFailed, projectName),
					nameStatus.getMessage());
			return false;
		}
		if (!namebaseStatus.isOK()) {
			MessageDialog.openWarning(getShell(), NLS.bind(Messages.wizardProjectJobFailed, baseProjectName),
					"Validate Selenium Framework Step 1: BaseAutomation Project for Selenium framework Created Before,So we will not created it again!");
			// return false;
		}

		IWorkspace workspace = ResourcesPlugin.getWorkspace();

		final IPath location = locationPage.isInWorkspace() ? null : locationPage.getLocationPath();
		final IWorkspaceRoot root = workspace.getRoot();
		@SuppressWarnings("deprecation")
		final IProject newproject = importConfiguration.getProject(root, model);

		@SuppressWarnings("deprecation")
		final IProject baseproject = importConfiguration.getProject(root, basemodel);

		boolean pomExists = (locationPage.isInWorkspace() ? root.getLocation().append(newproject.getName()) : location)
				.append(IMavenConstants.POM_FILE_NAME).toFile().exists();
		if (pomExists) {
			MessageDialog.openError(getShell(), NLS.bind(Messages.wizardProjectJobFailed, projectName),
					Messages.wizardProjectErrorPomAlreadyExists);
			return false;
		}

		// boolean basepomExists = (locationPage.isInWorkspace() ?
		// root.getLocation().append(baseproject.getName()) : location)
		// .append(IMavenConstants.POM_FILE_NAME).toFile().exists();
		/*
		 * if(basepomExists) { MessageDialog.openWarning(getShell(),
		 * NLS.bind(Messages.wizardProjectJobCreatingProject, baseProjectName),
		 * "Validate Selenium Framework Step 2: 'BaseAutomation' Project for Selenium framework had Created Before,So we will not created it again!"
		 * ); //return false; }
		 */

		final AbstractCreateMavenProjectJob job;
		// final Job basejob;

		final IProject[] returnproject = { newproject, baseproject };

		if (simpleProject.getSelection()) {
			final String[] folders = artifactPage.getFolders();

			job = new AbstractCreateMavenProjectJob(NLS.bind(Messages.wizardProjectJobCreatingProject, projectName),
					workingSets) {
				@Override
				protected List<IProject> doCreateMavenProjects(IProgressMonitor monitor) throws CoreException {
					MavenPlugin.getProjectConfigurationManager().createSimpleProject(newproject, location, model,
							folders, //
							importConfiguration, monitor);
					if (!createdbefore) {
						MavenPlugin.getProjectConfigurationManager().createSimpleProject(baseproject, location,
								basemodel, folders, //
								importConfiguration, monitor);
					}

					return Arrays.asList(returnproject);
				}
			};

		} else {
			final Archetype archetype = archetypePage.getArchetype();
			final String[] folders = artifactPage.getFolders();

			final String groupId = model.getGroupId();
			final String artifactId = model.getArtifactId();
			final String version = model.getVersion();
			final String javaPackage = parametersPage.getJavaPackage();
			final Properties properties = parametersPage.getProperties();

			/*
			 * final String basegroupId = basemodel.getGroupId(); final String
			 * baseartifactId = basemodel.getArtifactId(); final String
			 * baseversion = basemodel.getVersion(); final String
			 * basejavaPackage = basemodel.getPackaging();
			 */
			// final Properties properties = parametersPage.getProperties();

			job = new AbstractCreateMavenProjectJob(
					NLS.bind(Messages.wizardProjectJobCreating, archetype.getArtifactId()), workingSets) {
				@Override
				protected List<IProject> doCreateMavenProjects(IProgressMonitor monitor) throws CoreException {
					List<IProject> projects = MavenPlugin.getProjectConfigurationManager().createArchetypeProjects(
							location, archetype, //
							groupId, artifactId, version, javaPackage, properties, importConfiguration, monitor);

					if (!createdbefore) {
						MavenPlugin.getProjectConfigurationManager().createSimpleProject(baseproject, location,
								basemodel, folders, //
								importConfiguration, monitor);
						projects.add(baseproject);
					}
					return projects;

				}
			};

		}

		job.addJobChangeListener(new JobChangeAdapter() {
			public void done(IJobChangeEvent event) {
				final IStatus result = event.getResult();
				if (!result.isOK()) {
					Display.getDefault().asyncExec(new Runnable() {
						public void run() {
							MessageDialog.openError(getShell(), //
									NLS.bind(Messages.wizardProjectJobFailed, projectName), result.getMessage());
						}
					});
				}
			}
		});

		job.setRule(MavenPlugin.getProjectConfigurationManager().getRule());
		job.schedule();

		while (job.getState() == Job.RUNNING) {
			System.out.println("Maven creating job is still running....");
		}

		Map<String, String> paths = new HashMap<String, String>();

		paths.put("src/test/resources", "templates/java/test-resources");
		paths.put("src/test/java", "templates/java/features");
		paths.put("src/main/resources", "templates/java/resources");

		CustomProjectSupport.createProjectStructure(newproject, paths);

		// CustomProjectSupport.addProjectFileStructure(newproject,"templates/sample.feature","readme.txt");
		CustomProjectSupport.openProjectPOMEditor(newproject);

		// add the dependencies
		addProjectDependency(newproject, env, browser, "true");

		// add the xtext nature for the project
		try {
			CustomProjectSupport.addNature(newproject);
		} catch (CoreException e1) {
			// TODO Auto-generated catch block
			System.out.println("add the xtext nature error:" + e1.getMessage());
		}

		// update the project
		MavenUtils.updateDepenency(newproject);

		if (!createdbefore) {
			Map<String, String> basepaths = new HashMap<String, String>();

			basepaths.put("src/main/java", "templates/basemaven/src");
			basepaths.put("src/main/resources", "templates/basemaven/resources");
			// basepaths.put("test-logs", null);

			CustomProjectSupport.createProjectStructure(baseproject, basepaths);

			// seleniumversion=artifactPage.getSelenium();
			//addBaseSeleniumDependency(baseproject, seleniumversion);
			MavenUtils.updateDepenency(baseproject);
		}

		// ProjectListener listener = new ProjectListener();
		// workspace.addResourceChangeListener(listener,
		// IResourceChangeEvent.POST_CHANGE);
		// try {
		// job.setRule(plugin.getProjectConfigurationManager().getRule());
		// job.schedule();
		//
		// // MNGECLIPSE-766 wait until new project is created
		// while(listener.getNewProject() == null && (job.getState() &
		// (Job.WAITING | Job.RUNNING)) > 0) {
		// try {
		// Thread.sleep(100L);
		// } catch (InterruptedException ex) {
		// // ignore
		// }
		// }
		//
		// } finally {
		// workspace.removeResourceChangeListener(listener);
		// }

		return true;
	}

	// static class ProjectListener implements IResourceChangeListener {
	// private IProject newProject = null;
	//
	// public void resourceChanged(IResourceChangeEvent event) {
	// IResourceDelta root = event.getDelta();
	// IResourceDelta[] projectDeltas = root.getAffectedChildren();
	// for (int i = 0; i < projectDeltas.length; i++) {
	// IResourceDelta delta = projectDeltas[i];
	// IResource resource = delta.getResource();
	// if (delta.getKind() == IResourceDelta.ADDED) {
	// newProject = (IProject)resource;
	// }
	// }
	// }
	// /**
	// * Gets the newProject.
	// * @return Returns a IProject
	// */
	// public IProject getNewProject() {
	// return newProject;
	// }
	// }

	public void addProjectDependency(IProject newproject, String env, String browser, String record) {

		addProperties(newproject);
		addNewPlugins(newproject);

		// addnewProperties(newproject,env,browser,record);
		// add the base automation project for using
		MavenUtils.addMavenDependency(newproject, "com.github.becausetesting", "commons", "RELEASE", null, null, null);

		/*
		 * MavenUtils.addMavenDependency(newproject,"info.cukes","cucumber-java"
		 * ,null,null,null,null);
		 * MavenUtils.addMavenDependency(newproject,"info.cukes",
		 * "cucumber-junit",null,null,null,null);
		 * 
		 * MavenUtils.addMavenDependency(newproject,"com.sun.mail","javax.mail",
		 * null,null,null,null);
		 */

	}

	public void addBaseSeleniumDependency(IProject newproject, String seleniumversion) {

		addProperties(newproject);
		/*
		 * MavenUtils.addMavenreParents(newproject, "groupId", "");
		 * MavenUtils.addMavenreParents(newproject, "", "");
		 * MavenUtils.addMavenreParents(newproject, "", "");
		 */

		addPlugins(newproject);

		MavenUtils.addMavenDependency(newproject, "org.apache.maven", "maven-model", null, null, null, null);
		MavenUtils.addMavenDependency(newproject, "org.apache.maven", "maven-core", null, null, null, null);

		MavenUtils.addMavenDependency(newproject, "org.seleniumhq.selenium", "selenium-server", seleniumversion, null,
				null, null);

		// MavenUtils.addMavenDependency(newproject,"com.opera","operadriver",null,null,null,null);
		// for opera browser
		/*
		 * <exclusion> <groupId>org.apache.commons</groupId>
		 * <artifactId>commons-exec</artifactId> </exclusion>
		 */

		// MavenUtils.addMavenDependency(newproject,"org.testng","testng",null,null,null,null);
		MavenUtils.addMavenDependency(newproject, "org.apache.logging.log4j", "log4j-api", null, null, null, null);
		MavenUtils.addMavenDependency(newproject, "org.apache.logging.log4j", "log4j-core", null, null, null, null);
		MavenUtils.addMavenDependency(newproject, "log4j", "apache-log4j-extras", null, null, null, null);

		MavenUtils.addMavenDependency(newproject, "com.googlecode.json-simple", "json-simple", null, null, null, null);

		MavenUtils.addMavenDependency(newproject, "info.cukes", "cucumber-java", null, null, null, null);
		MavenUtils.addMavenDependency(newproject, "info.cukes", "cucumber-junit", null, null, null, null);

		MavenUtils.addMavenDependency(newproject, "org.jcodec", "jcodec", "0.1.6-3", null, null, null);
		MavenUtils.addMavenDependency(newproject, "net.sf.jacob-project", "jacob", "1.14.3", null, null, null);
		MavenUtils.addMavenDependency(newproject, "net.java.dev.jna", "jna-platform", "4.1.0", null, null, null);

		MavenUtils.addMavenDependency(newproject, "net.sourceforge.jexcelapi", "jxl", null, null, null, null);

		MavenUtils.addMavenDependency(newproject, "javax.mail", "mail", null, null, null, null);

		MavenUtils.addMavenDependency(newproject, "net.sourceforge.jtds", "jtds", null, null, null, null);
		MavenUtils.addMavenDependency(newproject, "io.selendroid", "selendroid-client", null, null, null, null);

		// correct the import org.hamcrest.MatcherAssert error
		MavenUtils.addMavenDependencyManagement(newproject, "junit", "junit", "4.11");

	}

	public void addProperties(IProject newproject) {
		MavenUtils.addMavenProperty(newproject, "project.build.sourceEncoding", "UTF-8");
		MavenUtils.addMavenProperty(newproject, "project.reporting.outputEncoding", "UTF-8");
		MavenUtils.addMavenProperty(newproject, "version.compile.plugin", "3.2");
		MavenUtils.addMavenProperty(newproject, "version.jar.plugin", "2.5");

		MavenUtils.addMavenProperty(newproject, "maven.compiler.target", "1.6");
		MavenUtils.addMavenProperty(newproject, "maven.compiler.source", "1.6");

		// specified environment

	}

	public void addnewProperties(IProject newproject, String env, String browser, String record) {
		MavenUtils.addMavenProperty(newproject, "ENVIRONMENT", env);
		MavenUtils.addMavenProperty(newproject, "BROWSER_TYPE", browser);
		MavenUtils.addMavenProperty(newproject, "RECORD_VIDEO", record);
		// specified environment

	}

	public void addPlugins(IProject newproject) {
		MavenUtils.addMavenPlugin(newproject, "org.apache.maven.plugins", "maven-compiler-plugin",
				"${version.compile.plugin}");
		MavenUtils.addMavenPlugin(newproject, "org.apache.maven.plugins", "maven-jar-plugin", "${version.jar.plugin}");
	}

	public void addNewPlugins(IProject newproject) {
		MavenUtils.addMavenPlugin(newproject, "org.apache.maven.plugins", "maven-compiler-plugin",
				"${version.compile.plugin}");
		MavenUtils.addMavenPlugin(newproject, "org.apache.maven.plugins", "maven-jar-plugin", "${version.jar.plugin}");
		MavenUtils.addMavenJunitPlugin(newproject, "org.apache.maven.plugins", "maven-surefire-plugin", "2.18.1");
	}

	public Model getBaseFrameworkModel() {
		Model model = new Model();
		model.setModelVersion("4.0.0");
		model.setGroupId("com.framework");
		model.setVersion("1.2.0");
		model.setArtifactId("BaseCucumber");
		model.setPackaging("jar");
		model.setName("Base GUI Automation Solution Framework for Cucumber");
		model.setDescription(
				"This is a Reused Base Cucumber framework,any cucumber and selenium based project can use this template's features.");
		return model;
	}

}
