package org.becausecucumber.eclipse.plugin.ui.preference;

import org.becausecucumber.eclipse.plugin.ui.CucumberPeopleActivator;
import org.becausecucumber.eclipse.plugin.ui.preference.Initializer.PreferenceConstants;
import org.eclipse.debug.internal.ui.SWTFactory;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.List;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.SelectionAdapter;

@SuppressWarnings("restriction")
public class RunResultPreferencePage extends PreferencePage implements IWorkbenchPreferencePage {

	private WidgetListener listener = new WidgetListener();
	private Button rubypathbutton;
	private Button rubydebugbutton;
	private Text rubypathvalue;
	private Text rubydebugvalue;
	private Group grpResultIntegration;
	private Label lblUploadResultTo;
	private List resultlist;
	private Label lblNoteUploadResult;
	private Group grpEmailNotification;
	private Button btnSendEmailAfter;
	private Group groupemail;
	private Label lblSmpt;
	private Text smtp;
	private Label lblNewLabelport;
	private Text port;
	private Label lblFrom;
	private Text from;
	private Label lblTo;
	private Text to;
	private Label lblSubject;
	private Text subject;
	private Button btnCheckView;

	private class WidgetListener implements ModifyListener, SelectionListener {

		@Override
		public void widgetSelected(SelectionEvent e) {
			// TODO Auto-generated method stub
			Object source = e.getSource();
			if (source == rubypathbutton) {
				// File standard dialog
				FileDialog fileDialog = new FileDialog(getShell());
				// Set the text
				fileDialog.setText("Select Ruby File");
				// Set filter on .txt files
				fileDialog.setFilterExtensions(new String[] { "*.exe" });
				// Put in a readable name for the filter
				fileDialog.setFilterNames(new String[] { "RubyBinaryFile(*.exe)" });
				// Open Dialog and save result of selection
				fileDialog.setFileName(rubypathvalue.getText());
				if (fileDialog.open() != null) {
					rubypathvalue.setText(fileDialog.open());
				}
				// System.out.println(selected);
			} else if (source == rubydebugbutton) {
				// TODO
				// File standard dialog
				FileDialog fileDialog = new FileDialog(getShell());
				// Set the text
				fileDialog.setText("Select ruby-debug-ide File");
				// Set filter on .txt files
				// fileDialog.setFilterExtensions(new String[] { "*.exe" });
				// Put in a readable name for the filter
				fileDialog.setFilterNames(new String[] { "Rdebug-ide File(rdebug-ide.*)" });
				// Open Dialog and save result of selection
				fileDialog.setFileName(rubydebugvalue.getText());
				if (fileDialog.open() != null) {
					rubydebugvalue.setText(fileDialog.open());
				}
			}
		}

		@Override
		public void widgetDefaultSelected(SelectionEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void modifyText(ModifyEvent e) {
			// TODO Auto-generated method stub

		}

	}

	public RunResultPreferencePage() {

		// TODO Auto-generated constructor stub
		setPreferenceStore(CucumberPeopleActivator.getInstance().getPreferenceStore());
		setDescription("Run/Result");
	}

	@Override
	public void init(IWorkbench workbench) {
		// TODO Auto-generated method stub

	}

	@Override
	protected Control createContents(Composite parent) {
		// TODO Auto-generated method stub
		IPreferenceStore store = CucumberPeopleActivator.getInstance().getPreferenceStore();

		Composite container = SWTFactory.createComposite(parent, parent.getFont(), 1, 1, GridData.FILL_BOTH);
		// container.setLayout(new GridLayout(1, false));

		Group grpRubyRunSettings = new Group(container, SWT.NONE);
		grpRubyRunSettings.setLayout(new GridLayout(3, false));
		// gd_grpRubyRunSettings.widthHint = 379;
		GridData gd_grpRubyRunSettings = new GridData(GridData.FILL_HORIZONTAL);
		gd_grpRubyRunSettings.heightHint = 88;
		grpRubyRunSettings.setLayoutData(gd_grpRubyRunSettings);
		grpRubyRunSettings.setText("Ruby Run Settings");

		Label lblRubyPath = new Label(grpRubyRunSettings, SWT.NONE);
		lblRubyPath.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblRubyPath.setText("Ruby Path : ");

		rubypathvalue = new Text(grpRubyRunSettings, SWT.BORDER);
		rubypathvalue.addModifyListener(listener);
		rubypathvalue.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		rubypathvalue.setText(store.getString(PreferenceConstants.RUBY_PATH));

		rubypathbutton = SWTFactory.createPushButton(grpRubyRunSettings, "...", null);
		rubypathbutton.addSelectionListener(listener);

		Label lblRubyDebug = new Label(grpRubyRunSettings, SWT.NONE);
		lblRubyDebug.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblRubyDebug.setText("Ruby Debug:");

		rubydebugvalue = new Text(grpRubyRunSettings, SWT.BORDER);
		rubydebugvalue.addModifyListener(listener);
		rubydebugvalue.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		rubydebugvalue.setText(store.getString(PreferenceConstants.RUBY_DEBUG_PATH));

		rubydebugbutton = SWTFactory.createPushButton(grpRubyRunSettings, "...", null);
		new Label(container, SWT.NONE);

		grpResultIntegration = new Group(container, SWT.NONE);
		GridData gd_grpResultIntegration = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_grpResultIntegration.heightHint = 79;
		grpResultIntegration.setLayoutData(gd_grpResultIntegration);
		grpResultIntegration.setText("Execution Result Integration");

		lblUploadResultTo = new Label(grpResultIntegration, SWT.NONE);
		lblUploadResultTo.setBounds(10, 24, 96, 15);
		lblUploadResultTo.setText("Upload Result To: ");

		resultlist = new List(grpResultIntegration, SWT.BORDER);
		resultlist.setTouchEnabled(true);
		resultlist.setItems(new String[] { "TestRail", "TestLink", "ALM" });
		resultlist.setBounds(122, 19, 114, 68);
		resultlist.setSelection(store.getInt(PreferenceConstants.SAVE_RESULT));

		lblNoteUploadResult = new Label(grpResultIntegration, SWT.WRAP);
		lblNoteUploadResult.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblNoteUploadResult.setBounds(265, 19, 315, 68);
		lblNoteUploadResult.setText(
				"Note: Upload result only ocurred  for cucumber runner, It will not upload the result for cucumber debugger.");
		new Label(container, SWT.NONE);

		grpEmailNotification = new Group(container, SWT.NONE);
		grpEmailNotification.setLayout(new GridLayout(1, false));
		GridData gd_grpEmailNotification = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_grpEmailNotification.heightHint = 298;
		grpEmailNotification.setLayoutData(gd_grpEmailNotification);
		grpEmailNotification.setText("E-Mail Notification");

		btnSendEmailAfter = new Button(grpEmailNotification, SWT.CHECK);
		btnSendEmailAfter.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean visible = groupemail.getVisible();
				if (visible) {
					groupemail.setVisible(false);
				} else {
					groupemail.setVisible(true);
				}
			}
		});
		btnSendEmailAfter.setText("Send Email After Cucumber Runner");

		groupemail = new Group(grpEmailNotification, SWT.NONE);
		GridData gd_groupemail = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_groupemail.heightHint = 140;
		groupemail.setLayoutData(gd_groupemail);
		groupemail.setLayout(new GridLayout(3, false));
		groupemail.setVisible(true);

		lblSmpt = new Label(groupemail, SWT.NONE);
		lblSmpt.setText("SMTP Server :");
		new Label(groupemail, SWT.NONE);

		smtp = new Text(groupemail, SWT.BORDER);
		smtp.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		lblNewLabelport = new Label(groupemail, SWT.NONE);
		lblNewLabelport.setText("Port : ");
		new Label(groupemail, SWT.NONE);

		port = new Text(groupemail, SWT.BORDER);
		port.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		lblFrom = new Label(groupemail, SWT.NONE);
		lblFrom.setText("From :");
		new Label(groupemail, SWT.NONE);

		from = new Text(groupemail, SWT.BORDER);
		from.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		lblTo = new Label(groupemail, SWT.NONE);
		lblTo.setText("To :");
		new Label(groupemail, SWT.NONE);

		to = new Text(groupemail, SWT.BORDER);
		to.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		lblSubject = new Label(groupemail, SWT.NONE);
		lblSubject.setText("Subject :");
		new Label(groupemail, SWT.NONE);

		subject = new Text(groupemail, SWT.BORDER);
		subject.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(grpEmailNotification, SWT.NONE);

		btnCheckView = new Button(grpEmailNotification, SWT.CHECK);
		btnCheckView.setSelection(store.getBoolean(PreferenceConstants.VIEW_RESULT));
		btnCheckView.setText("View result after execution");
		rubydebugbutton.addSelectionListener(listener);

		return container;
	}

	@Override
	protected void performApply() {
		// TODO Auto-generated method stub
		IPreferenceStore store = getPreferenceStore();
		store.setValue(PreferenceConstants.RUBY_PATH, rubypathvalue.getText().trim());
		store.setValue(PreferenceConstants.RUBY_DEBUG_PATH, rubydebugvalue.getText().trim());
		store.setValue(PreferenceConstants.SAVE_RESULT, resultlist.getSelectionIndex());
		store.setValue(PreferenceConstants.VIEW_RESULT, btnCheckView.getSelection());
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
		rubypathvalue.setText(store.getDefaultString(PreferenceConstants.RUBY_PATH));

		rubydebugvalue.setText(store.getDefaultString(PreferenceConstants.RUBY_DEBUG_PATH));

		resultlist.setSelection(store.getDefaultInt(PreferenceConstants.SAVE_RESULT));

		btnCheckView.setSelection(store.getDefaultBoolean(PreferenceConstants.VIEW_RESULT));
	}

	@Override
	public boolean performOk() {
		// TODO Auto-generated method stub
		IPreferenceStore store = getPreferenceStore();
		store.setValue(PreferenceConstants.RUBY_PATH, rubypathvalue.getText().trim());
		store.setValue(PreferenceConstants.RUBY_DEBUG_PATH, rubydebugvalue.getText().trim());
		store.setValue(PreferenceConstants.SAVE_RESULT, resultlist.getSelectionIndex());

		store.setValue(PreferenceConstants.VIEW_RESULT, btnCheckView.getSelection());
		return super.performOk();
	}

	@Override
	public void setPreferenceStore(IPreferenceStore store) {
		// TODO Auto-generated method stub

		super.setPreferenceStore(store);
	}

}
