package org.becausecucumber.eclipse.plugin.ui.wizard;

import java.net.URI;

import org.becausecucumber.eclipse.plugin.ui.common.CustomProjectSupport;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;
import org.eclipse.ui.wizards.newresource.BasicNewProjectResourceWizard;

public class NewCucumberAndroidWizard extends Wizard implements INewWizard, IExecutableExtension {

	/*
	 * refer this url:
	 * https://cvalcarcel.wordpress.com/2009/07/26/writing-an-eclipse-plug-in-
	 * part-4-create-a-custom-project-in-eclipse-new-project-wizard-the-
	 * behavior/
	 */

	private WizardNewProjectCreationPage _pageOne;

	private IConfigurationElement _configurationElement;

	public NewCucumberAndroidWizard() {
		// TODO Auto-generated constructor stub
		setWindowTitle("Andriod Cucumber Project");
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addPages() {
		// TODO Auto-generated method stub
		super.addPages();

		_pageOne = new WizardNewProjectCreationPage("New Cucumber Project Wizard");
		_pageOne.setTitle("New Android Project for Cucumber(Still In Development...)");
		_pageOne.setDescription("Create Andriod project from cucumber.");

		addPage(_pageOne);
	}

	@Override
	public void setInitializationData(IConfigurationElement config, String propertyName, Object data)
			throws CoreException {
		// TODO Auto-generated method stub
		_configurationElement = config;

	}

	@Override
	public boolean performFinish() {
		// TODO Auto-generated method stub
		String projectName = _pageOne.getProjectName();
		URI location = null;
		if (!_pageOne.useDefaults()) {
			location = _pageOne.getLocationURI();
			System.err.println("location: " + location.toString()); //$NON-NLS-1$
		} // else l

		CustomProjectSupport.createRubyProject(projectName, location);

		BasicNewProjectResourceWizard.updatePerspective(_configurationElement);
		return true;
	}

}
