package org.becausecucumber.eclipse.plugin.ui.wizard;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;

import org.becausecucumber.eclipse.plugin.ui.CucumberPeopleActivator;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;
import org.w3c.dom.Document;

public class NewCucumberJavaWizard extends Wizard implements INewWizard, IExecutableExtension {

	private WizardNewProjectCreationPage fMainPage;
	@SuppressWarnings("unused")
	private IConfigurationElement fConfigElement;

	public NewCucumberJavaWizard() {
		// TODO Auto-generated constructor stub
		super();
		try {

			@SuppressWarnings("deprecation")
			URL base = CucumberPeopleActivator.getInstance().getDescriptor().getInstallURL();
			setDefaultPageImageDescriptor(ImageDescriptor.createFromURL(new URL(base, "icons/newprojava.png")));
			setDialogSettings(CucumberPeopleActivator.getInstance().getDialogSettings());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setInitializationData(IConfigurationElement config, String propertyName, Object data)
			throws CoreException {
		// TODO Auto-generated method stub
		this.fConfigElement = config;

	}

	@Override
	public void addPages() {
		super.addPages();

		this.fMainPage = new WizardNewProjectCreationPage("WizardNewProjectCreationPage");
		this.fMainPage.setTitle("Cucumber projet from Java");
		this.fMainPage.setDescription("Create a Simple Java Project for Cucumber");
		addPage(this.fMainPage);
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean performFinish() {
		// TODO Auto-generated method stub
		final String nomProjet;
		final IPath nomRep;
		IRunnableWithProgress op = null;
		try {
			nomProjet = fMainPage.getProjectName();
			nomRep = fMainPage.getLocationPath();

			final boolean useDefaults = fMainPage.useDefaults();

			op = new IRunnableWithProgress() {
				public void run(IProgressMonitor monitor) throws InvocationTargetException {
					try {

						doFinish(useDefaults, nomProjet, nomRep, monitor);
					} catch (CoreException e) {
						throw new InvocationTargetException(e);
					} finally {
						monitor.done();
					}
				}
			};
		} catch (NullPointerException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			getContainer().run(true, false, op);
		} catch (InterruptedException e) {
			return false;
		} catch (InvocationTargetException e) {
			Throwable realException = e.getTargetException();
			MessageDialog.openError(getShell(), "Error !", realException //$NON-NLS-1$
					.getMessage());
			return false;
		}
		return true;
	}

	private void doFinish(boolean useDefaults, String nomProjet, IPath nomRep, IProgressMonitor monitor)
			throws CoreException {

		try {
			IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();

			IPreferenceStore store = CucumberPeopleActivator.getInstance().getPreferenceStore();
			String xmlFileName = store.getString(WorkbenchPreferenceCust.P_FILE);

			String projectDir = null;

			if (useDefaults) {
				projectDir = nomRep.toOSString() + "\\" + nomProjet; //$NON-NLS-1$
			} else {
				projectDir = nomRep.toOSString(); // $NON-NLS-1$
			}

			// creation du IPath en fonction du chemin saisi
			IPath monIPath = new Path(projectDir);
			File dir = new File(projectDir);
			dir.mkdirs();

			// creation du File correspondant
			// ertoire qui contiendra le fichier .project
			// ayant pour nom celui du projet
			monIPath.toFile().mkdir();

			System.out.println(" -> " + monIPath.toString()); //$NON-NLS-1$

			IProjectInformation projectInfo = new IProjectInformation();
			projectInfo.setBasePath(projectDir.toString());

			boolean def = false;

			if ((xmlFileName != null) && (!xmlFileName.equals(""))) { //$NON-NLS-1$
				System.out.println("fileName : " + xmlFileName); //$NON-NLS-1$
				try {
					ProjectCreationUtils.createAllDir(projectInfo, xmlFileName);
					Document docClassPath = ProjectCreationUtils.createClassPathFile(projectInfo);
					ProjectCreationUtils.writeFile(projectDir.toString() + "\\.classpath", docClassPath); //$NON-NLS-1$
				} catch (Exception e) {
					System.out.println("-> pb ?la creaton des reps : "); //$NON-NLS-1$
					e.printStackTrace();
					def = true;
				}
			} else {
				def = true;
			}

			IProject project = root.getProject(nomProjet);
			IProjectDescription desc = project.getWorkspace().newProjectDescription(project.getName());
			if (useDefaults) {
				desc.setLocation(null);
			} else {
				desc.setLocation(monIPath);
			}

			// Creation du .project
			Document docProject = ProjectCreationUtils.createJavaProjectFile(project);
			ProjectCreationUtils.writeFile(projectDir.toString() + "\\.project", docProject); //$NON-NLS-1$

			if (def) {
				File srcDir = new File(projectDir.toString() + "\\src"); //$NON-NLS-1$
				srcDir.mkdir();

				Document docClassPath = ProjectCreationUtils.createClassPathFile();
				ProjectCreationUtils.writeFile(projectDir.toString() + "\\.classpath", docClassPath); //$NON-NLS-1$
			}

			project.create(desc, monitor);

			project.open(monitor);
			monitor = null;

			/*
			 * le container qu'on essaye de r閏up閞er n'existe pas, il faut donc
			 * create .project correspondant.
			 */

			/*
			 * IContainer container = root.getContainerForLocation(monIPath);
			 * 
			 * IPath cheminRep = (IPath) monIPath; final IFolder folderProjet;
			 * if (container != null) { folderProjet =root.getFolder(monIPath);
			 * } else folderProjet = null;
			 * 
			 * folderProjet.createLink(cheminRep,
			 * IFolder.ALLOW_MISSING_LOCAL,monitor);
			 */

			// monitor.worked(1);
			// monitor.setTaskName("Java Project Creating Task"); //$NON-NLS-1$
			// monitor.worked(1);
		} catch (CoreException e2) {
			System.out.println("****Exception :"); //$NON-NLS-1$
			e2.printStackTrace();
		} catch (Exception e2) {
			System.out.println("****Exception :"); //$NON-NLS-1$
			e2.printStackTrace();
		}
	}

}
