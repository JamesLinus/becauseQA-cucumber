package org.becausecucumber.eclipse.plugin.ui.dialog;

import org.becausecucumber.eclipse.plugin.ui.CucumberPeopleActivator;
import org.becausecucumber.eclipse.plugin.ui.preference.Initializer.PreferenceConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;

import org.eclipse.wb.swt.ResourceManager;

@SuppressWarnings("restriction")
public class TestRailCheckinDataDialog extends TitleAreaDialog {
	private Text testsuite;

	private String testsuiteid;
	private String testsectionid;

	public TestRailCheckinDataDialog(Shell parentShell) {
		super(parentShell);
		setHelpAvailable(false);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void create() {
		// TODO Auto-generated method stub
		super.create();
		setTitle("Are you sure to upload this feature's cucumber script into TestRail now ? ");
		// setMessage("Test Suite could be the name or the Test Suite URL;\n
		// Test Section could be the name or the Test Section,and each child
		// section seperates with '/' Symbol ", IMessageProvider.INFORMATION);
	}

	@Override
	protected void initializeBounds() {
		// TODO Auto-generated method stub
		super.initializeBounds();
		getShell().setSize(1500, 900);
		getShell().pack();
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		setTitleImage(ResourceManager.getPluginImage(CucumberPeopleActivator.ORG_CUCUMBERPEOPLE_ECLIPSE_PLUGIN_CUCUMBER,
				"icons/cukes.gif"));
		// TODO Auto-generated method stub
		IPreferenceStore store = CucumberPeopleActivator.getInstance().getPreferenceStore();

		Composite container = (Composite) super.createDialogArea(parent);
		container.setEnabled(true);
		GridLayout gridLayout = (GridLayout) container.getLayout();
		gridLayout.verticalSpacing = 2;
		new Label(container, SWT.NONE);

		Label lblTestSuiteName = new Label(container, SWT.NONE);
		lblTestSuiteName.setText("Test Suite Name or Path:");

		testsuite = new Text(container, SWT.BORDER);
		testsuite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		testsuite.setText(store.getString(PreferenceConstants.TESTRAIL_TESTSUITE));
		testsuite.selectAll();

		CLabel lblNewLabel = new CLabel(container, SWT.NONE);
		GridData gd_lblNewLabel = new GridData(SWT.CENTER, SWT.CENTER, true, false, 1, 1);
		gd_lblNewLabel.verticalIndent = -10;
		gd_lblNewLabel.widthHint = 900;
		gd_lblNewLabel.heightHint = 113;
		lblNewLabel.setLayoutData(gd_lblNewLabel);
		lblNewLabel.setText(
				"Test Suite value should be test suite name or url like below:\r\nlhttps://testrailservername/testrail/index.php?/suites/view/21519 \r\n");

		return container;
	}

	// save content of the Text fields because they get disposed
	// as soon as the Dialog closes
	private void saveInput() {
		testsuiteid = testsuite.getText().trim();
		
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
		return testsectionid;
	}

	public void setTestsectionid(String testsectionid) {
		this.testsectionid = testsectionid;
	}

}
