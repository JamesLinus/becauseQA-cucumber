package org.becausecucumber.eclipse.plugin.ui.wizard;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.ide.IDE;

public class NewRubyStepDefinationFileWizard extends Wizard implements INewWizard {

	private NewRubyStepDefinationFileWizardPage rubywizardpage;

	private IStructuredSelection selection;
	private IWorkbench workbench;

	private InputStream input;

	public NewRubyStepDefinationFileWizard() {
		// TODO Auto-generated constructor stub
		setWindowTitle("Create a Ruby Step Defination file");

	}

	public void addInputStream(String content) {
		this.input = new ByteArrayInputStream(content.getBytes());
	}

	@Override
	public void addPages() {
		// TODO Auto-generated method stub
		rubywizardpage = new NewRubyStepDefinationFileWizardPage(selection);
		rubywizardpage.setFileContent(this.input);

		addPage(rubywizardpage);
	}

	@Override
	public boolean performFinish() {
		// TODO Auto-generated method stub
		IFile newfile = rubywizardpage.createNewFile();

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
