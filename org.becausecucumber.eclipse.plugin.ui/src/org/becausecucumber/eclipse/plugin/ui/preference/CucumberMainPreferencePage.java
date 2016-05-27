package org.becausecucumber.eclipse.plugin.ui.preference;

import java.net.MalformedURLException;
import java.net.URL;

import org.becausecucumber.eclipse.plugin.common.PluginUpdater;
import org.becausecucumber.eclipse.plugin.validation.CucumberJavaValidator;
import org.becausecucumber.eclipse.plugin.ui.CucumberPeopleActivator;
import org.becausecucumber.eclipse.plugin.ui.preference.Initializer.PreferenceConstants;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import org.eclipse.swt.widgets.Label;
import org.osgi.framework.Version;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;

public class CucumberMainPreferencePage extends PreferencePage implements IWorkbenchPreferencePage {
	// private Text txtFirst;

	private String updatesite = PreferenceConstants.UPDATE_SITE;
	private String pluginID = PreferenceConstants.UPDATE_PLUGIN_ID;
	private Composite parent;
	private Button btnBrowser;

	private Text logpath;
	private Button btnCheckLatestCucumber;
	private Button btnSuspendAllThe;

	public String msg;

	private WidgetListener listener = new WidgetListener();

	/*
	 * no default button
	 * http://stackoverflow.com/questions/1853532/how-to-remove-restore-defaults
	 * -and-apply-button-in-custom-rcp-preference-pag
	 */
	public CucumberMainPreferencePage() {
		// TODO Auto-generated constructor stub
		setPreferenceStore(CucumberPeopleActivator.getInstance().getPreferenceStore());

		setMessage("BecauseCucumber");
		setDescription("BecauseCucumber is an eclipse plugin for Cucumber,support ruby,java jvm and python.etc.");
		// this.noDefaultAndApplyButton();
	}

	@Override
	public void init(IWorkbench workbench) {
		// TODO Auto-generated method stub

	}

	@Override
	protected Control createContents(Composite comp) {
		// TODO Auto-generated method stub
		final IPreferenceStore store = CucumberPeopleActivator.getInstance().getPreferenceStore();

		// noDefaultAndApplyButton();
		parent = new Composite(comp, SWT.NONE);
		parent.setSize(100, 500);
		GridLayout gl_parent = new GridLayout(5, false);
		gl_parent.horizontalSpacing = 10;
		parent.setLayout(gl_parent);

		Label lblCucumberPluginVersion = new Label(parent, SWT.NONE);
		lblCucumberPluginVersion.setText("Installed Version:");

		Label version = new Label(parent, SWT.NONE);
		version.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		Version longversion = CucumberPeopleActivator.getInstance().getBundle().getVersion();

		String versionnumber = String.valueOf(longversion.getMajor()) + "." + String.valueOf(longversion.getMinor()
				+ "." + String.valueOf(longversion.getMicro()) + "." + String.valueOf(longversion.getQualifier()));
		version.setText(versionnumber);
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);

		Label lblAuthor = new Label(parent, SWT.NONE);
		GridData gd_lblAuthor = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblAuthor.minimumHeight = 30;
		lblAuthor.setLayoutData(gd_lblAuthor);
		lblAuthor.setText("Author:");

		Label lblName = new Label(parent, SWT.NONE);
		lblName.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblName.setText("Alter Hu");
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);

		Label lblNewLabel = new Label(parent, SWT.NONE);
		lblNewLabel.setText("Support: ");

		Link cukesite = new Link(parent, SWT.NONE);
		cukesite.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		cukesite.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				try {
					// Open default external browser
					// PlatformUI.getWorkbench().getBrowserSupport().createBrowser(null);
					// http://blog.roweware.com/2007/07/24/open-url-in-eclipse/
					PlatformUI.getWorkbench().getBrowserSupport().getExternalBrowser().openURL(new URL(e.text));
				} catch (PartInitException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				} catch (MalformedURLException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
			}
		});
		cukesite.setText("<a href=\"" + PreferenceConstants.UPDATE_SITE + "\">" + PreferenceConstants.CONTACT + "</a>");
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);

		btnCheckLatestCucumber = new Button(parent, SWT.CHECK);
		btnCheckLatestCucumber.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 3, 1));
		btnCheckLatestCucumber.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean selection = btnCheckLatestCucumber.getSelection();
				store.setValue(PreferenceConstants.CHECK_PLUGIN, selection);

			}
		});
		btnCheckLatestCucumber.setSelection(store.getBoolean(PreferenceConstants.CHECK_PLUGIN));
		btnCheckLatestCucumber.setText("Download available latest version on startup");

		Button btnCheckNow = new Button(parent, SWT.NONE);
		btnCheckNow.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		btnCheckNow.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				msg = "Checking available latest CucumberPeople plugin......";
				// message.setText(msg);
				Display.getCurrent().syncExec(new Runnable() {
					public void run() {

						PluginUpdater pluginUpdater = new PluginUpdater(updatesite, pluginID);
						pluginUpdater.checkForUpdates();
						msg = pluginUpdater.message;
						// message.setText(msg);

					}
				});

			}
		});
		btnCheckNow.setText("Check Available Version Now");

		btnSuspendAllThe = new Button(parent, SWT.CHECK);
		btnSuspendAllThe.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 5, 1));
		btnSuspendAllThe.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean isauto = btnSuspendAllThe.getSelection();
				store.setValue(PreferenceConstants.AUTO_VALIDATOR, isauto);
				CucumberJavaValidator.isvalidator = isauto;
			}
		});
		btnSuspendAllThe.setSelection(true);
		btnSuspendAllThe.setText(
				"Allow cucumber validation automatically(maybe have performance issue for large feature file)");

		Label lblApplicationLogPath = new Label(parent, SWT.NONE);
		lblApplicationLogPath.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblApplicationLogPath.setText("Cucumber Log Path: ");

		logpath = new Text(parent, SWT.BORDER);
		GridData gd_logpath = new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1);
		gd_logpath.widthHint = 129;
		logpath.setLayoutData(gd_logpath);
		// String log=store.getString(PreferenceConstants.LOG_PATH);
		logpath.setText(store.getString(PreferenceConstants.LOG_PATH));
		logpath.addModifyListener(listener);

		btnBrowser = new Button(parent, SWT.NONE);
		btnBrowser.addSelectionListener(listener);
		btnBrowser.setText("Browser...");

		Label note = new Label(parent, SWT.NONE);
		GridData gd_note = new GridData(SWT.LEFT, SWT.CENTER, false, false, 5, 1);
		gd_note.verticalIndent = 3;
		note.setLayoutData(gd_note);
		note.setFont(SWTResourceManager.getFont("Imprint MT Shadow", 9, SWT.NORMAL));
		note.setText("Note: You can send this log file to developer for investigating the cucumber exception.");
		initDataBindings();
		return parent;
	}

	private class WidgetListener implements ModifyListener, SelectionListener {

		@Override
		public void widgetSelected(SelectionEvent e) {
			// TODO Auto-generated method stub
			Object source = e.getSource();
			if (source == btnBrowser) {
				// File standard dialog
				FileDialog fileDialog = new FileDialog(getShell());
				// Set the text
				fileDialog.setText("Select a Log File");
				// Set filter on .txt files
				fileDialog.setFilterExtensions(new String[] { "*.log" });
				// Put in a readable name for the filter
				fileDialog.setFilterNames(new String[] { "logfile(*.log)" });
				// Open Dialog and save result of selection
				fileDialog.setFileName(logpath.getText());
				if (fileDialog.open() != null) {
					logpath.setText(fileDialog.open());
				}
				// System.out.println(selected);
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

	@Override
	protected void performApply() {
		// TODO Auto-generated method stub
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
		IPreferenceStore store = CucumberPeopleActivator.getInstance().getPreferenceStore();
		btnCheckLatestCucumber.setSelection(store.getDefaultBoolean(PreferenceConstants.CHECK_PLUGIN));
		btnSuspendAllThe.setSelection(store.getDefaultBoolean(PreferenceConstants.AUTO_VALIDATOR));
		logpath.setText(store.getDefaultString(PreferenceConstants.LOG_PATH));
	}

	@Override
	public boolean performOk() {
		// TODO Auto-generated method stub
		IPreferenceStore store = CucumberPeopleActivator.getInstance().getPreferenceStore();
		store.setValue(PreferenceConstants.CHECK_PLUGIN, btnCheckLatestCucumber.getSelection());
		store.setValue(PreferenceConstants.AUTO_VALIDATOR, btnSuspendAllThe.getSelection());
		store.setValue(PreferenceConstants.LOG_PATH, logpath.getText().trim());
		return super.performOk();
	}

	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		return bindingContext;
	}
}
