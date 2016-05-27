package org.becausecucumber.eclipse.plugin.ui.preference;

import org.becausecucumber.eclipse.plugin.common.console.Log;
import org.becausecucumber.eclipse.plugin.common.testrail.TestRailImpl;
import org.becausecucumber.eclipse.plugin.ui.CucumberPeopleActivator;
import org.becausecucumber.eclipse.plugin.ui.preference.Initializer.PreferenceConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;

public class AccountPreferencePage extends PreferencePage implements IWorkbenchPreferencePage {
	public Text testrail_url;
	public Text testrail_projectname;
	public Text testrail_user;
	public Text testrail_password;

	public static boolean testrailinit = false;
	private Text alm_url;
	private Text alm_project;
	private Text alm_username;
	private Text alm_password;
	private Text testlink_url;
	private Text testlink_project;
	private Text testlink_username;
	private Text testlink_password;

	public AccountPreferencePage() {
		// super(title);
		// TODO Auto-generated constructor stub
		setPreferenceStore(CucumberPeopleActivator.getInstance().getPreferenceStore());
		setDescription("Account");
	}

	@Override
	public void init(IWorkbench workbench) {
		// TODO Auto-generated method stub
		// setPreferenceStore(getPreferenceStore());

	}

	@Override
	protected Control createContents(Composite parent) {
		// TODO Auto-generated method stub
		IPreferenceStore store = CucumberPeopleActivator.getInstance().getPreferenceStore();

		Composite container = new Composite(parent, SWT.NONE);

		Group grpTestrail = new Group(container, SWT.NONE);
		grpTestrail.setLocation(0, 0);
		grpTestrail.setSize(587, 186);
		grpTestrail.setText("Test Rail");

		Label lblUrl = new Label(grpTestrail, SWT.NONE);
		lblUrl.setBounds(10, 27, 49, 13);
		lblUrl.setText("URL");

		Label lblProjectName = new Label(grpTestrail, SWT.NONE);
		lblProjectName.setBounds(10, 60, 83, 18);
		lblProjectName.setText("Project Name");

		Label lblUser = new Label(grpTestrail, SWT.NONE);
		lblUser.setBounds(10, 98, 68, 13);
		lblUser.setText("User Name");

		Label lblPassword = new Label(grpTestrail, SWT.NONE);
		lblPassword.setBounds(10, 143, 68, 13);
		lblPassword.setText("Password");

		testrail_url = new Text(grpTestrail, SWT.BORDER);
		testrail_url.setToolTipText("This is the Test Rail base url.");
		testrail_url.setBounds(99, 24, 226, 19);
		testrail_url.setText(store.getString(PreferenceConstants.TESTRAIL_URL));
		testrail_url.selectAll();

		testrail_projectname = new Text(grpTestrail, SWT.BORDER);
		testrail_projectname.setBounds(99, 57, 226, 19);
		testrail_projectname.setText(store.getString(PreferenceConstants.TESTRAIL_PROJECT));
		testrail_projectname.selectAll();

		testrail_user = new Text(grpTestrail, SWT.BORDER);
		testrail_user.setBounds(99, 95, 226, 19);
		testrail_user.setText(store.getString(PreferenceConstants.TESTRAIL_USER));
		testrail_user.selectAll();

		testrail_password = new Text(grpTestrail, SWT.BORDER | SWT.PASSWORD);
		testrail_password.setBounds(99, 140, 226, 22);
		testrail_password.setText(store.getString(PreferenceConstants.TESTRAIL_PASSWORD));
		testrail_password.selectAll();

		Button testrailbtnValidation = new Button(grpTestrail, SWT.NONE);
		testrailbtnValidation.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String url = testrail_url.getText().trim();
				String project = testrail_projectname.getText().trim();
				String user = testrail_user.getText().trim();
				String password = testrail_password.getText().trim();
				Log.info("Account Settings - init project:" + project + ",url:" + url + ",user:" + user);
				boolean initok = TestRailImpl.getInstance().initTestRail(url, user, password, project);
				if (initok) {
					testrailinit = true;
					MessageDialog.openInformation(getShell(),
							CucumberPeopleActivator.ORG_CUCUMBERPEOPLE_ECLIPSE_PLUGIN_CUCUMBER, "Done Successfully!");
				} else {
					MessageDialog.openError(getShell(),
							CucumberPeopleActivator.ORG_CUCUMBERPEOPLE_ECLIPSE_PLUGIN_CUCUMBER,
							"Initize TestRail project failed ,"
									+ "please make sure input values are correct and retry it again!");

				}

			}
		});
		testrailbtnValidation.setBounds(342, 139, 112, 23);
		testrailbtnValidation.setText("TestRail Validation");

		Label note = new Label(grpTestrail, SWT.NONE);
		note.setTextDirection(2222);
		note.setBounds(331, 10, 246, 123);
		note.setText(
				"Please Note: You should specify the same\r\nTestRail address you use to access TestRail \r\nwith your web browser\r\n(eg. https://<server-name>.testrail.com/ or \r\nhttp://<server>/testrail/).");

		Group grpTestlink = new Group(container, SWT.NONE);
		grpTestlink.setFont(SWTResourceManager.getFont("Segoe UI Symbol", 9, SWT.NORMAL));
		grpTestlink.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		grpTestlink.setText("Test Link(Not Implement)");
		grpTestlink.setBounds(0, 189, 587, 186);

		Label testlink_urllabel = new Label(grpTestlink, SWT.NONE);
		testlink_urllabel.setText("URL");
		testlink_urllabel.setBounds(10, 27, 49, 13);

		Label testlink_projectlabel = new Label(grpTestlink, SWT.NONE);
		testlink_projectlabel.setText("Project Name");
		testlink_projectlabel.setBounds(10, 60, 83, 18);

		Label testlink_userlabel = new Label(grpTestlink, SWT.NONE);
		testlink_userlabel.setText("User Name");
		testlink_userlabel.setBounds(10, 98, 68, 13);

		Label testlink_passwordlabel = new Label(grpTestlink, SWT.NONE);
		testlink_passwordlabel.setText("Password");
		testlink_passwordlabel.setBounds(10, 143, 68, 13);

		testlink_url = new Text(grpTestlink, SWT.BORDER);
		testlink_url.setToolTipText("This is the Test Rail base url.");
		testlink_url.setText("");
		testlink_url.setBounds(99, 24, 226, 19);

		testlink_project = new Text(grpTestlink, SWT.BORDER);
		testlink_project.setText("");
		testlink_project.setBounds(99, 57, 226, 19);

		testlink_username = new Text(grpTestlink, SWT.BORDER);
		testlink_username.setText("");
		testlink_username.setBounds(99, 95, 226, 19);

		testlink_password = new Text(grpTestlink, SWT.BORDER | SWT.PASSWORD);
		testlink_password.setText("");
		testlink_password.setBounds(99, 140, 226, 22);

		Button testlinkbtnValidation = new Button(grpTestlink, SWT.NONE);
		testlinkbtnValidation.setText("TestLink Validation");
		testlinkbtnValidation.setBounds(342, 139, 112, 23);

		Group grpApplicationLifecycleManagement = new Group(container, SWT.NONE);
		grpApplicationLifecycleManagement.setBounds(0, 393, 587, 186);
		grpApplicationLifecycleManagement.setText("Application Lifecycle Management(Not Implement)");

		Label alm_urllabel = new Label(grpApplicationLifecycleManagement, SWT.NONE);
		alm_urllabel.setText("URL");
		alm_urllabel.setBounds(10, 27, 49, 13);

		Label alm_projectlabel = new Label(grpApplicationLifecycleManagement, SWT.NONE);
		alm_projectlabel.setText("Project Name");
		alm_projectlabel.setBounds(10, 60, 83, 18);

		Label alm_userlabel = new Label(grpApplicationLifecycleManagement, SWT.NONE);
		alm_userlabel.setText("User Name");
		alm_userlabel.setBounds(10, 98, 68, 13);

		Label alm_passwordlabel = new Label(grpApplicationLifecycleManagement, SWT.NONE);
		alm_passwordlabel.setText("Password");
		alm_passwordlabel.setBounds(10, 143, 68, 13);

		alm_url = new Text(grpApplicationLifecycleManagement, SWT.BORDER);
		alm_url.setToolTipText("This is the Test Rail base url.");
		alm_url.setText("");
		alm_url.setBounds(99, 24, 226, 19);

		alm_project = new Text(grpApplicationLifecycleManagement, SWT.BORDER);
		alm_project.setText("");
		alm_project.setBounds(99, 57, 226, 19);

		alm_username = new Text(grpApplicationLifecycleManagement, SWT.BORDER);
		alm_username.setText("");
		alm_username.setBounds(99, 95, 226, 19);

		alm_password = new Text(grpApplicationLifecycleManagement, SWT.BORDER | SWT.PASSWORD);
		alm_password.setText("");
		alm_password.setBounds(99, 140, 226, 22);

		Button almbtnValidation = new Button(grpApplicationLifecycleManagement, SWT.NONE);
		almbtnValidation.setText("ALM Validation");
		almbtnValidation.setBounds(342, 139, 112, 23);
		return container;
	}

	@Override
	protected void performApply() {
		// TODO Auto-generated method stub
		IPreferenceStore store = getPreferenceStore();
		store.setValue(PreferenceConstants.TESTRAIL_URL, testrail_url.getText());
		store.setValue(PreferenceConstants.TESTRAIL_PROJECT, testrail_projectname.getText());
		store.setValue(PreferenceConstants.TESTRAIL_USER, testrail_user.getText());
		store.setValue(PreferenceConstants.TESTRAIL_PASSWORD, testrail_password.getText());
		// store.setValue(PreferenceConstants.SAVE_TESTRAIL,
		// btnSaveResultInto.getSelection());
		super.performApply();
	}

	@Override
	public boolean performOk() {
		// TODO Auto-generated method stub
		// if(testrailinit){
		IPreferenceStore store = getPreferenceStore();
		store.setValue(PreferenceConstants.TESTRAIL_URL, testrail_url.getText());
		store.setValue(PreferenceConstants.TESTRAIL_PROJECT, testrail_projectname.getText());
		store.setValue(PreferenceConstants.TESTRAIL_USER, testrail_user.getText());
		store.setValue(PreferenceConstants.TESTRAIL_PASSWORD, testrail_password.getText());
		// store.setValue(PreferenceConstants.SAVE_TESTRAIL,
		// btnSaveResultInto.getSelection());

		// }
		return super.performOk();
	}

	@Override
	protected void performDefaults() {
		// TODO Auto-generated method stub
		super.performDefaults();
		IPreferenceStore store = getPreferenceStore();
		testrail_url.setText(store.getDefaultString(PreferenceConstants.TESTRAIL_URL));
		testrail_projectname.setText(store.getDefaultString(PreferenceConstants.TESTRAIL_PROJECT));
		testrail_user.setText(store.getDefaultString(PreferenceConstants.TESTRAIL_USER));
		testrail_password.setText(store.getDefaultString(PreferenceConstants.TESTRAIL_PASSWORD));
		// btnSaveResultInto.setSelection(store.getDefaultBoolean(PreferenceConstants.SAVE_TESTRAIL));
	}

	@Override
	public boolean performCancel() {
		// TODO Auto-generated method stub
		return super.performCancel();
	}
}
