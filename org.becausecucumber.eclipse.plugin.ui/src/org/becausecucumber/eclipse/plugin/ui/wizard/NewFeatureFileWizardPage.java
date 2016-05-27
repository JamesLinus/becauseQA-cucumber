package org.becausecucumber.eclipse.plugin.ui.wizard;

import java.io.InputStream;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;

public class NewFeatureFileWizardPage extends WizardNewFileCreationPage {

	private InputStream input;

	public NewFeatureFileWizardPage(IStructuredSelection selection) {
		super("Create a new Cucumber Feature File", selection);
		// TODO Auto-generated constructor stub
		setTitle("New Cucumber Feature File");
		setDescription("Creates a new Feature File");
		setFileExtension("feature");
		setFileName("sample.feature");
		// setVisible(true);
	}

	public InputStream setFileContent(InputStream input) {
		return this.input = input;
	}

	@Override
	protected InputStream getInitialContents() {
		return this.input;

		/*
		 * String xmlTemplate = "<hc-schema>\n" + "  <tables></tables>\n" +
		 * "  <filters></filters>\n" + "  <views></views>\n" + "</hc-schema>\n";
		 * return new ByteArrayInputStream(xmlTemplate.getBytes());
		 */
		// GDNCucumberActivator.getInstance().getBundle().getEntry("templates/sample.feature").openStream();
	}
	/*
	 * @Override protected boolean validatePage() { // TODO Auto-generated
	 * method stub return true; }
	 */

}
