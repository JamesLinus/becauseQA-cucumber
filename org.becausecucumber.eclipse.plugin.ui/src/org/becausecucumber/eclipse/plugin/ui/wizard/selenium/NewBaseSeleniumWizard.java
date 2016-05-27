package org.becausecucumber.eclipse.plugin.ui.wizard.selenium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.maven.archetype.catalog.Archetype;
import org.apache.maven.model.Model;
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
import org.eclipse.m2e.core.ui.internal.Messages;
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

@SuppressWarnings("restriction")
public class NewBaseSeleniumWizard extends AbstractMavenProjectWizard implements INewWizard {

	/** The wizard page for gathering general project information. */
	protected MavenProjectWizardLocationPage locationPage;

	/** The archetype selection page. */
	protected MavenProjectWizardArchetypePage archetypePage;

	/** The wizard page for gathering Maven2 project information. */
	protected MavenProjectWizardCustomArtifactPage artifactPage;

	/** The wizard page for gathering archetype project information. */
	protected MavenProjectWizardArchetypeParametersPage parametersPage;

	protected Button simpleProject;

	protected String seleniumversion;

	/**
	 * Default constructor. Sets the title and image of the wizard.
	 */
	public NewBaseSeleniumWizard() {
		super();
		setWindowTitle("Base Selenium Project for Maven Project");
		setDefaultPageImageDescriptor(MavenImages.WIZ_NEW_PROJECT);
		setNeedsProgressMonitor(true);
	}

	public void addPages() {

		final ImageDescriptor cukeimage = CucumberPeopleActivator.getInstance().getImageRegistry()
				.getDescriptor(CucumberPeopleActivator.CUKE_IMGE);
		locationPage = new MavenProjectWizardLocationPage(importConfiguration, //
				Messages.wizardProjectPageProjectTitle, Messages.wizardProjectPageProjectDescription, workingSets) { //

			protected void createAdditionalControls(Composite container) {
				locationPage.setTitle("Create New Selenium Project ");
				locationPage.setDescription("Just Click the Next Button to continue to create the project.");
				locationPage.setImageDescriptor(cukeimage);

				simpleProject = new Button(container, SWT.CHECK);
				simpleProject.setText("Create a Simple Selenium Project.(Skip archetype selection)");
				simpleProject.setSelection(true);
				// locationPage.setPageComplete(false);
				simpleProject.setEnabled(false);

				simpleProject.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false, 3, 1));
				simpleProject.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e) {
						// boolean isSimpleproject =
						// simpleProject.getSelection();
						// archetypePage.setUsed(!isSimpleproject);
						// parametersPage.setUsed(!isSimpleproject);
						// artifactPage.setUsed(isSimpleproject);
						// getContainer().updateButtons();
						// validate();

					}
				});
				/*
				 * be careful for this code ,as we need for next page
				 */

				boolean isSimpleproject = simpleProject.getSelection();
				archetypePage.setUsed(!isSimpleproject);
				parametersPage.setUsed(!isSimpleproject);
				artifactPage.setUsed(isSimpleproject);
				getContainer().updateButtons();
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
				// getContainer().updateButtons();
				return getPage(simpleProject.getSelection() ? "MavenProjectWizardArtifactPage" //$NON-NLS-1$
						: "MavenProjectWizardArchetypePage"); //$NON-NLS-1$
			}
		};
		locationPage.setLocationPath(SelectionUtil.getSelectedLocation(selection));

		archetypePage = new MavenProjectWizardArchetypePage(importConfiguration);
		parametersPage = new MavenProjectWizardArchetypeParametersPage(importConfiguration);
		artifactPage = new MavenProjectWizardCustomArtifactPage(importConfiguration);

		artifactPage.setTitle("Create New Selenium Project");
		artifactPage.setDescription("Please input some project data for selenium,Then click the Finish button.");

		artifactPage.setImageDescriptor(cukeimage);
		addPage(locationPage);
		addPage(archetypePage);
		addPage(parametersPage);
		addPage(artifactPage);
	}

	/** Adds the listeners after the page controls are created. */
	public void createPageControls(Composite pageContainer) {
		super.createPageControls(pageContainer);

		/*
		 * simpleProject.addListener(SWT.CHANGED, new Listener() {
		 * 
		 * @Override public void handleEvent(Event event) { // TODO
		 * Auto-generated method stub boolean isSimpleproject =
		 * simpleProject.getSelection();
		 * archetypePage.setUsed(!isSimpleproject);
		 * parametersPage.setUsed(!isSimpleproject);
		 * artifactPage.setUsed(isSimpleproject);
		 * getContainer().updateButtons(); } });
		 */
		simpleProject.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				// boolean isSimpleproject = simpleProject.getSelection();
				// archetypePage.setUsed(!isSimpleproject);
				// parametersPage.setUsed(!isSimpleproject);
				// artifactPage.setUsed(isSimpleproject);
				// getContainer().updateButtons();
			}
		});
		/*
		 * this line code for next page finish button
		 */
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
		getContainer().updateButtons();
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
		final Model model = getModel();
		seleniumversion = artifactPage.getSelenium();
		@SuppressWarnings("deprecation")
		final String projectName = importConfiguration.getProjectName(model);
		@SuppressWarnings("deprecation")
		IStatus nameStatus = importConfiguration.validateProjectName(model);
		if (!nameStatus.isOK()) {
			MessageDialog.openError(getShell(), NLS.bind(Messages.wizardProjectJobFailed, projectName),
					nameStatus.getMessage());
			return false;
		}

		IWorkspace workspace = ResourcesPlugin.getWorkspace();

		final IPath location = locationPage.isInWorkspace() ? null : locationPage.getLocationPath();
		final IWorkspaceRoot root = workspace.getRoot();
		@SuppressWarnings("deprecation")
		final IProject project = importConfiguration.getProject(root, model);

		boolean pomExists = (locationPage.isInWorkspace() ? root.getLocation().append(project.getName()) : location)
				.append(IMavenConstants.POM_FILE_NAME).toFile().exists();
		if (pomExists) {
			MessageDialog.openError(getShell(), NLS.bind(Messages.wizardProjectJobFailed, projectName),
					Messages.wizardProjectErrorPomAlreadyExists);
			return false;
		}

		final Job job;

		if (simpleProject.getSelection()) {
			final String[] folders = artifactPage.getFolders();

			job = new AbstractCreateMavenProjectJob(NLS.bind(Messages.wizardProjectJobCreatingProject, projectName),
					workingSets) {
				@Override
				protected List<IProject> doCreateMavenProjects(IProgressMonitor monitor) throws CoreException {
					MavenPlugin.getProjectConfigurationManager().createSimpleProject(project, location, model, folders, //
							importConfiguration, monitor);
					return Arrays.asList(project);
				}
			};

		} else {
			final Archetype archetype = archetypePage.getArchetype();

			final String groupId = model.getGroupId();
			final String artifactId = model.getArtifactId();
			final String version = model.getVersion();
			final String javaPackage = parametersPage.getJavaPackage();
			final Properties properties = parametersPage.getProperties();

			job = new AbstractCreateMavenProjectJob(
					NLS.bind(Messages.wizardProjectJobCreating, archetype.getArtifactId()), workingSets) {
				@Override
				protected List<IProject> doCreateMavenProjects(IProgressMonitor monitor) throws CoreException {
					List<IProject> projects = MavenPlugin.getProjectConfigurationManager().createArchetypeProjects(
							location, archetype, //
							groupId, artifactId, version, javaPackage, properties, importConfiguration, monitor);
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

		// add the file struture
		Map<String, String> paths = new HashMap<String, String>();

		paths.put("src/main/java", "templates/selenium");
		paths.put("src/main/resources", "templates/java/resources");
		paths.put("test-logs", null);
		CustomProjectSupport.createProjectStructure(project, paths);

		// CustomProjectSupport.addProjectFileStructure(project,"templates/selenium/readme.txt","readme.txt");
		CustomProjectSupport.openProjectPOMEditor(project);
		// add the dependencies

		addBaseSeleniumDependency(project, seleniumversion);
		// update the project
		MavenUtils.updateDepenency(project);

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

	public void addBaseSeleniumDependency(IProject newproject, String seleniumversion) {
		MavenUtils.addMavenDependency(newproject, "org.seleniumhq.selenium", "selenium-server", seleniumversion, null,
				null, null);

		MavenUtils.addMavenDependency(newproject, "com.opera", "operadriver", null, null, null, null);
		MavenUtils.addMavenDependency(newproject, "org.testng", "testng", null, null, null, null);
		MavenUtils.addMavenDependency(newproject, "org.apache.logging.log4j", "log4j-api", null, null, null, null);
		MavenUtils.addMavenDependency(newproject, "org.apache.logging.log4j", "log4j-core", null, null, null, null);
		MavenUtils.addMavenDependency(newproject, "log4j", "apache-log4j-extras", null, null, null, null);

		MavenUtils.addMavenDependency(newproject, "net.sourceforge.jexcelapi", "jxl", null, null, null, null);
	}

}
