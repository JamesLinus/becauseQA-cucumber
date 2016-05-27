package org.becausecucumber.eclipse.plugin.ui.wizard;

import java.io.IOException;

import org.becausecucumber.eclipse.plugin.ui.CucumberPeopleActivator;
import org.eclipse.core.resources.IFile;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.ide.IDE;

public class NewFeatureFileWizard extends Wizard implements INewWizard {

	private NewFeatureFileWizardPage featurewizardpage;

	private IStructuredSelection selection;
	private IWorkbench workbench;

	public NewFeatureFileWizard() {
		// TODO Auto-generated constructor stub
		setWindowTitle("New Cucumber Feature File");

	}

	@Override
	public void addPages() {
		// TODO Auto-generated method stub

		featurewizardpage = new NewFeatureFileWizardPage(selection);
		try {
			featurewizardpage.setFileContent(CucumberPeopleActivator.getInstance().getBundle()
					.getEntry("templates/sample.feature").openStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		addPage(featurewizardpage);
	}

	@Override
	public boolean performFinish() {
		// TODO Auto-generated method stub
		IFile newfile = featurewizardpage.createNewFile();

		if (newfile != null) {
			try {
				IDE.openEditor(workbench.getActiveWorkbenchWindow().getActivePage(), newfile);
			} catch (PartInitException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		// TODO Auto-generated method stub
		this.workbench = workbench;
		this.selection = selection;

	}

}
