package org.becausecucumber.eclipse.plugin.ui.preference;

import org.becausecucumber.eclipse.plugin.ui.CucumberPeopleActivator;
import org.becausecucumber.eclipse.plugin.ui.preference.Initializer.PreferenceConstants;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

public class JenkinsPreferencePage extends PreferencePage implements IWorkbenchPreferencePage {
	private Text url;
	private Text user;
	private Text password;
	private Table table;

	public JenkinsPreferencePage() {
		// TODO Auto-generated constructor stub
		setPreferenceStore(CucumberPeopleActivator.getInstance().getPreferenceStore());
		setDescription("Jenkins Integration for Your Automation Work");
	}

	@Override
	public void init(IWorkbench workbench) {
		// TODO Auto-generated method stub

	}

	@Override
	protected Control createContents(Composite parent) {
		IPreferenceStore store = CucumberPeopleActivator.getInstance().getPreferenceStore();

		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(1, false));

		Group grpJenkinsSettings = new Group(container, SWT.NONE);
		grpJenkinsSettings.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		grpJenkinsSettings.setLayout(new GridLayout(2, false));
		grpJenkinsSettings.setText("Jenkins Settings");

		Label lblServerUrl = new Label(grpJenkinsSettings, SWT.NONE);
		lblServerUrl.setText("Server URL:");

		url = new Text(grpJenkinsSettings, SWT.BORDER);
		url.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(grpJenkinsSettings, SWT.NONE);
		new Label(grpJenkinsSettings, SWT.NONE);
		url.setText(store.getString(PreferenceConstants.JENKINS_URL));
		url.selectAll();

		Label username = new Label(grpJenkinsSettings, SWT.NONE);
		username.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		username.setText("User Name:");

		user = new Text(grpJenkinsSettings, SWT.BORDER);
		user.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(grpJenkinsSettings, SWT.NONE);
		new Label(grpJenkinsSettings, SWT.NONE);
		user.setText(store.getString(PreferenceConstants.JENKINS_USER));
		user.selectAll();

		Label lblPassword = new Label(grpJenkinsSettings, SWT.HORIZONTAL | SWT.CENTER);
		lblPassword.setTextDirection(33554431);
		lblPassword.setText("Password");

		password = new Text(grpJenkinsSettings, SWT.BORDER | SWT.PASSWORD);
		password.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		password.setText(store.getString(PreferenceConstants.JENKINS_PASSWORD));

		Group grpJenkinsJobList = new Group(container, SWT.NONE);
		grpJenkinsJobList.setLayout(new GridLayout(1, false));
		GridData gd_grpJenkinsJobList = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_grpJenkinsJobList.heightHint = 137;
		grpJenkinsJobList.setLayoutData(gd_grpJenkinsJobList);
		grpJenkinsJobList.setText("Jenkins Job List");

		table = new Table(grpJenkinsJobList, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tblclmnJobName = new TableColumn(table, SWT.NONE);
		tblclmnJobName.setWidth(100);
		tblclmnJobName.setText("Job Name");

		TableColumn tblclmnJobSettings = new TableColumn(table, SWT.NONE);
		tblclmnJobSettings.setWidth(155);
		tblclmnJobSettings.setText("Job Settings");

		TableColumn tblclmnJobUrl = new TableColumn(table, SWT.NONE);
		tblclmnJobUrl.setWidth(100);
		tblclmnJobUrl.setText("Job URL");

		return container;
	}

	@Override
	public boolean performOk() {
		// TODO Auto-generated method stub
		IPreferenceStore store = getPreferenceStore();
		String urlname = url.getText().trim();
		String finalurl = urlname;
		if (!urlname.endsWith("/")) {
			finalurl = urlname + "/";
		}
		store.setValue(PreferenceConstants.JENKINS_URL, finalurl);
		store.setValue(PreferenceConstants.JENKINS_USER, user.getText().trim());
		store.setValue(PreferenceConstants.JENKINS_PASSWORD, password.getText().trim());

		return super.performOk();

	}

	@Override
	protected void performApply() {
		// TODO Auto-generated method stub
		IPreferenceStore store = getPreferenceStore();
		String urlname = url.getText().trim();
		String finalurl = urlname;
		if (!urlname.endsWith("/")) {
			finalurl = urlname + "/";
		}
		store.setValue(PreferenceConstants.JENKINS_URL, finalurl);
		store.setValue(PreferenceConstants.JENKINS_USER, user.getText().trim());
		store.setValue(PreferenceConstants.JENKINS_PASSWORD, password.getText().trim());

		super.performApply();
	}

	@Override
	public boolean performCancel() {
		// TODO Auto-generated method stub
		return super.performCancel();
	}

	@Override
	protected void performDefaults() {
		// TODO Auto-generated method stub
		super.performDefaults();
		IPreferenceStore store = getPreferenceStore();
		url.setText(store.getDefaultString(PreferenceConstants.JENKINS_URL));
		user.setText(store.getDefaultString(PreferenceConstants.JENKINS_USER));
		password.setText(store.getDefaultString(PreferenceConstants.JENKINS_PASSWORD));

	}

}
