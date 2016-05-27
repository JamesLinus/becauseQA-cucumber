package org.becausecucumber.eclipse.plugin.ui.dialog;

import org.becausecucumber.eclipse.plugin.ui.CucumberPeopleActivator;
import org.becausecucumber.eclipse.plugin.ui.preference.Initializer.PreferenceConstants;
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
import org.eclipse.swt.custom.CLabel;

@SuppressWarnings("restriction")
public class TestRailCheckOutDataDialog extends TitleAreaDialog {
	private Text testsuite;
	private Text testsection;

	private String testsuiteid;
	private String testsectionid;

	public TestRailCheckOutDataDialog(Shell parentShell) {
		// parentShell.setSize(500, 900);
		super(parentShell);
		setHelpAvailable(false);
		// parentShell.sets
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initializeBounds() {
		// TODO Auto-generated method stub
		super.initializeBounds();
		getShell().setSize(1500, 900);
		getShell().pack();
	}

	@Override
	public void create() {
		// TODO Auto-generated method stub
		super.create();

		setTitle("Are you sure to check out the cucumber script from TestRail to current opened Editor ?");
		// setMessage("Test Suite could be the name or test suite url,like :
		// https://gdcqatestrail01/testrail/index.php?/suites/view/21519 ;\n
		// Test Section could be the name in this test suite or test section
		// url,like:
		// https://gdcqatestrail01/testrail/index.php?/suites/view/21519/223935
		// ", IMessageProvider.INFORMATION);
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
		lblTestSuiteName.setText("Test Suite Url :");

		testsuite = new Text(container, SWT.BORDER);
		testsuite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		testsuite.setText(store.getString(PreferenceConstants.TESTRAIL_TESTSUITE));
		testsuite.selectAll();

		Label lblTestSectionId = new Label(container, SWT.NONE);
		lblTestSectionId.setText("Test Section Url :  ");

		testsection = new Text(container, SWT.BORDER);
		testsection.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		testsection.setText(store.getString(PreferenceConstants.TESTRAIL_TESTSECTION));
		testsection.selectAll();
		new Label(container, SWT.NONE);

		CLabel lblNewLabel = new CLabel(container, SWT.NONE);
		GridData gd_lblNewLabel = new GridData(SWT.CENTER, SWT.CENTER, true, false, 1, 1);
		gd_lblNewLabel.verticalIndent = -15;
		gd_lblNewLabel.widthHint = 701;
		gd_lblNewLabel.heightHint = 113;
		lblNewLabel.setLayoutData(gd_lblNewLabel);
		lblNewLabel.setText(
				"Test suite value should be test suite url in testrail,\r\nlike : https://testrailservername/testrail/index.php?/suites/view/21519 \r\nTest Section value should be test section url in testrail,\r\nlike: https://testrailservername/testrail/index.php?/suites/view/21519/223935 ");

		return container;
	}

	// save content of the Text fields because they get disposed
	// as soon as the Dialog closes
	private void saveInput() {
		testsuiteid = testsuite.getText().trim();
		testsectionid = testsection.getText().trim();
		// IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		// Shell shell =
		// Activator.getDefault().getWorkbench().getActiveWorkbenchWindow().getShell();

	}

	@Override
	protected void okPressed() {
		// TODO Auto-generated method stub
		// if(testsuiteid.equals("")
		// ||testsectionid.equals("")
		// ){
		/// setMessage("please input valid test suite and test section
		// value,then try again.");
		// }else{
		saveInput();
		// this.close();
		// }
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
