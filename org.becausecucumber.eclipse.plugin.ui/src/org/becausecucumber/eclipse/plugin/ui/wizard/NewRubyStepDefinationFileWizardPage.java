package org.becausecucumber.eclipse.plugin.ui.wizard;

import java.io.InputStream;

import org.becausecucumber.eclipse.plugin.common.CommonPluginUtils;
import org.becausecucumber.eclipse.plugin.ui.CucumberPeopleActivator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;

/*
 * 
 * as this method from the editor so it cannot get the IStructuredSelection correctly
 */

public class NewRubyStepDefinationFileWizardPage extends WizardNewFileCreationPage {

	private InputStream input;

	public NewRubyStepDefinationFileWizardPage(IStructuredSelection selection) {
		super("Create a new Cucumber Ruby File", selection);
		// TODO Auto-generated constructor stub
		setTitle("New Cucumber Ruby File");
		setDescription("Creates a new Ruby Defination File");
		setFileExtension("rb");
		setFileName("new_steps.rb");

		if (selection.isEmpty()) {
			CommonPluginUtils.instance = CucumberPeopleActivator.getInstance().getWorkbench();
			IPath path = CommonPluginUtils.getActiveEditorPath2().getParent().getFullPath();
			setContainerFullPath(path);
		}

		ImageDescriptor rubyimage = CucumberPeopleActivator.getInstance().getImageRegistry()
				.getDescriptor(CucumberPeopleActivator.RUBY_IMGE);
		setImageDescriptor(rubyimage);
		// setVisible(true);
	}

	public InputStream setFileContent(InputStream input) {
		return this.input = input;
	}

	@Override
	protected InputStream getInitialContents() {
		// TODO Auto-generated method stub
		return this.input;
		// return super.getInitialContents();
	}

}
