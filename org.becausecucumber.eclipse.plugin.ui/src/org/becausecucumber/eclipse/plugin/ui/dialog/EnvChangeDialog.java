package org.becausecucumber.eclipse.plugin.ui.dialog;

import org.becausecucumber.eclipse.plugin.ui.CucumberPeopleActivator;
import org.becausecucumber.eclipse.plugin.ui.preference.Initializer.PreferenceConstants;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;

import org.eclipse.wb.swt.ResourceManager;

@SuppressWarnings("restriction")
public class EnvChangeDialog extends TitleAreaDialog {
	private Text testsuite;
	private Text envalues;

	private String testsuiteid;
	private String environmentvalues;

	public EnvChangeDialog(Shell parentShell) {
		super(parentShell);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void create() {
		// TODO Auto-generated method stub
		super.create();
		setTitle(
				"Input the Test Suite Name ,then it will update the whole test suite pointing to specified environment?");
		setMessage("Test Environment value should be [ QA3,QA4,QA5,DEV-INT,DEV-INT2,Production ] these values.",
				IMessageProvider.INFORMATION);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		setTitleImage(ResourceManager.getPluginImage(CucumberPeopleActivator.ORG_CUCUMBERPEOPLE_ECLIPSE_PLUGIN_CUCUMBER,
				"icons/cukes.gif"));
		// TODO Auto-generated method stub
		IPreferenceStore store = CucumberPeopleActivator.getInstance().getPreferenceStore();

		Composite container = (Composite) super.createDialogArea(parent);
		GridLayout gridLayout = (GridLayout) container.getLayout();
		gridLayout.verticalSpacing = 2;
		new Label(container, SWT.NONE);

		Label lblTestSuiteName = new Label(container, SWT.NONE);
		lblTestSuiteName.setText("Test Suite Name : ");

		testsuite = new Text(container, SWT.BORDER);
		testsuite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		testsuite.setText(store.getString(PreferenceConstants.TESTRAIL_TESTSUITE));
		testsuite.selectAll();

		Label lblTestEnvLabel = new Label(container, SWT.NONE);
		lblTestEnvLabel
				.setText("Environment List :(Multiply environments should be seperate with , Symbol. like QA3,QA4 )");

		envalues = new Text(container, SWT.BORDER);
		envalues.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		envalues.setText(store.getString(PreferenceConstants.ENV_VALUE));
		envalues.selectAll();

		return container;
	}

	// save content of the Text fields because they get disposed
	// as soon as the Dialog closes
	private void saveInput() {
		testsuiteid = testsuite.getText().trim();
		environmentvalues = envalues.getText().trim();
		// IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		// Shell shell =
		// Activator.getDefault().getWorkbench().getActiveWorkbenchWindow().getShell();

	}

	@Override
	protected void okPressed() {
		// TODO Auto-generated method stub
		saveInput();
		super.okPressed();

	}

	/*
	 * @Override protected Control createContents(Composite parent) { // TODO
	 * Auto-generated method stub return super.createContents(parent); }
	 */
	@Override
	protected void cancelPressed() {
		// TODO Auto-generated method stub
		super.cancelPressed();
		// IPreferenceStore store =
		// GDNCucumberActivator.getInstance().getPreferenceStore();
		// store.setValue(PreferenceConstants.TESTRAIL_TESTSUITE,
		// testsuite.getText().trim());
		// store.setValue(PreferenceConstants.TESTRAIL_TESTSECTION,
		// testsection.getText().trim());
	}

	public String getTestsuiteid() {
		return testsuiteid;
	}

	public void setTestsuiteid(String testsuiteid) {
		this.testsuiteid = testsuiteid;
	}

	public String getTestsectionid() {
		return environmentvalues;
	}

	public void setTestsectionid(String testsectionid) {
		this.environmentvalues = testsectionid;
	}

}
