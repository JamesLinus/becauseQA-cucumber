package org.becausecucumber.eclipse.plugin.ui.wizard;

import org.becausecucumber.eclipse.plugin.ui.CucumberPeopleActivator;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * 
 * @author sail7551
 */
public class WorkbenchPreferenceCust extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {
	public static final String P_FILE = "filePreference"; //$NON-NLS-1$

	private FileFieldEditor xmlFile;

	public WorkbenchPreferenceCust() {
		super(GRID);
		setPreferenceStore(CucumberPeopleActivator.getInstance().getPreferenceStore());
		setDescription("Custom Cucumber Java Project Template"); //$NON-NLS-1$
		initializeDefaults();
	}

	/**
	 * Sets the default values of the preferences.
	 */
	private void initializeDefaults() {
	}

	/**
	 * Creates the field editors. Field editors are abstractions of the common
	 * GUI blocks needed to manipulate various types of preferences. Each field
	 * editor knows how to save and restore itself.
	 */

	public void createFieldEditors() {
		xmlFile = new FileFieldEditor(P_FILE, Messages.getString("WorkbenchPreferenceCust.file"), //$NON-NLS-1$
				getFieldEditorParent());
		// xmlFile.setEmptyStringAllowed(false);
		xmlFile.setFileExtensions(new String[] { "xml" });
		// xmlFile.set
		addField(xmlFile);
	}

	public void init(IWorkbench workbench) {
	}

	public boolean performOk() {

		IPreferenceStore store = getPreferenceStore();

		store.setValue(P_FILE, xmlFile.getStringValue());

		return true;
	}

}
