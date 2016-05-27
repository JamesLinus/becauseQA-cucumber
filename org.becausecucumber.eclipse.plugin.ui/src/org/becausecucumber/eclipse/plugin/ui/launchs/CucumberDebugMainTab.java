package org.becausecucumber.eclipse.plugin.ui.launchs;

import org.becausecucumber.eclipse.plugin.common.CommonCucumberFeatureUtils;
import org.becausecucumber.eclipse.plugin.common.CommonPluginUtils;
import org.becausecucumber.eclipse.plugin.ui.CucumberPeopleActivator;
import org.becausecucumber.eclipse.plugin.ui.preference.Initializer.PreferenceConstants;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.internal.ui.SWTFactory;
import org.eclipse.debug.ui.ILaunchConfigurationTab;
import org.eclipse.jdt.internal.debug.ui.launcher.LauncherMessages;
import org.eclipse.jdt.internal.debug.ui.launcher.SharedJavaMainTab;
import org.eclipse.jdt.launching.IJavaLaunchConfigurationConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;

@SuppressWarnings("restriction")
public class CucumberDebugMainTab extends SharedJavaMainTab implements ILaunchConfigurationTab {

	protected Text featurePathText;
	protected Text gluePathText;
	protected Text glueOptions;
	private WidgetListener listener = new WidgetListener();
	private Button featureButton;
	// private Button glueButton;
	private Button monochromeCheckbox;
	private Button prettyCheckbox;
	private Button jsonCheckbox;
	private Button htmlCheckbox;
	private Button progressCheckbox;
	private Button usageCheckbox;
	private Button junitCheckbox;
	private Button rerunCheckbox;
	private Group grpRubyonlyForRuby;
	private Label lblPath;
	private Label lblOptions;
	private GridData gd_grpFeatureOptions;

	private Button rubybutton;
	// private RubyWidgetListener rubylistener = new RubyWidgetListener();
	private Label lblRubyPath;
	private Text rubypath;
	private Label lblRubyArguments;
	private Text rubyparameter;
	private Button btnIsJavaProject;
	private Composite comp_1;

	private class WidgetListener implements ModifyListener, SelectionListener {

		public void modifyText(ModifyEvent e) {
			updateLaunchConfigurationDialog();
		}

		public void widgetDefaultSelected(SelectionEvent e) {/* do nothing */
		}

		public void widgetSelected(SelectionEvent e) {
			Object source = e.getSource();
			if (source == featureButton) {
				// File standard dialog
				FileDialog fileDialog = new FileDialog(getShell());
				// Set the text
				fileDialog.setText("Select Cucumber Feature File");
				// Set filter on .txt files
				fileDialog.setFilterExtensions(new String[] { "*.feature" });
				// Put in a readable name for the filter
				fileDialog.setFilterNames(new String[] { "Features(*.feature)" });
				// Open Dialog and save result of selection
				fileDialog.setFileName(featurePathText.getText());
				featurePathText.setText(fileDialog.open());
				// System.out.println(selected);
			} else if (source == rubybutton) {
				// TODO
				// File standard dialog
				FileDialog fileDialog = new FileDialog(getShell());
				// Set the text
				fileDialog.setText("Select Ruby Binary File");
				// Set filter on .txt files
				fileDialog.setFilterExtensions(new String[] { "*.exe" });
				// Put in a readable name for the filter
				fileDialog.setFilterNames(new String[] { "RubyBinFile(*.exe)" });
				// Open Dialog and save result of selection
				fileDialog.setFileName(rubypath.getText());
				rubypath.setText(fileDialog.open());
			} else
				updateLaunchConfigurationDialog();
		}
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public void createControl(Composite parent) {
		comp_1 = SWTFactory.createComposite(parent, parent.getFont(), 1, 1, GridData.FILL_BOTH);
		createProjectEditor(comp_1);
		setControl(comp_1);
		createFeaturePathEditor(comp_1);
		createGluePathEditor(comp_1);
		createFormatterOptions(comp_1);
		createRubyOptions(comp_1);

	}

	private void createFeaturePathEditor(Composite comp) {
		Font font = comp.getFont();

		btnIsJavaProject = new Button(comp_1, SWT.CHECK);
		btnIsJavaProject.setEnabled(false);
		btnIsJavaProject.setText("Is Java Project(This only uses for our project settings)");
		btnIsJavaProject.setSelection(false);
		Group group = new Group(comp, SWT.NONE);
		group.setText("Feature Path:");
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		group.setLayoutData(gd);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		group.setLayout(layout);
		group.setFont(font);
		featurePathText = new Text(group, SWT.SINGLE | SWT.BORDER);
		gd = new GridData(GridData.FILL_HORIZONTAL);

		featurePathText.setLayoutData(gd);
		featurePathText.setFont(font);

		featurePathText.addModifyListener(listener);

		featureButton = createPushButton(group, LauncherMessages.AbstractJavaMainTab_1, null);
		new Label(group, SWT.NONE);
		featureButton.addSelectionListener(listener);

	}

	private void createGluePathEditor(Composite comp) {
		Font font = comp.getFont();
		Group grpFeatureOptions = new Group(comp, SWT.NONE);
		grpFeatureOptions.setText("Feature Options:");
		GridData gd;
		gd_grpFeatureOptions = new GridData(GridData.FILL_HORIZONTAL);
		gd_grpFeatureOptions.heightHint = 63;
		gd_grpFeatureOptions.verticalAlignment = SWT.FILL;
		grpFeatureOptions.setLayoutData(gd_grpFeatureOptions);
		GridLayout gl_grpFeatureOptions = new GridLayout();
		gl_grpFeatureOptions.verticalSpacing = 4;
		gl_grpFeatureOptions.numColumns = 2;
		grpFeatureOptions.setLayout(gl_grpFeatureOptions);
		grpFeatureOptions.setFont(font);

		lblPath = new Label(grpFeatureOptions, SWT.NONE);
		lblPath.setText("Path");
		gluePathText = new Text(grpFeatureOptions, SWT.SINGLE | SWT.BORDER);
		gd = new GridData(GridData.FILL_HORIZONTAL);

		lblOptions = new Label(grpFeatureOptions, SWT.NONE);
		lblOptions.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblOptions.setText("Options");

		glueOptions = new Text(grpFeatureOptions, SWT.SINGLE | SWT.BORDER);
		// glueOptions.setText("--expand -r features");
		glueOptions.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		// glueOptions.setLayoutData(gd);
		glueOptions.setFont(font);
		gluePathText.setLayoutData(gd);
		gluePathText.setFont(font);
		new Label(grpFeatureOptions, SWT.NONE);
		new Label(grpFeatureOptions, SWT.NONE);
		new Label(grpFeatureOptions, SWT.NONE);
		new Label(grpFeatureOptions, SWT.NONE);

		gluePathText.addModifyListener(listener);

		// glueButton = createPushButton(group,
		// LauncherMessages.AbstractJavaMainTab_1, null);
		// glueButton.addSelectionListener(listener);
	}

	private void createFormatterOptions(Composite comp) {
		Font font = comp.getFont();
		Group group = new Group(comp, SWT.NONE);
		group.setText("Formatters:");
		group.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		group.setLayout(layout);
		group.setFont(font);

		monochromeCheckbox = new Button(group, SWT.CHECK);
		monochromeCheckbox.addSelectionListener(listener);
		monochromeCheckbox.setText("monochrome");

		prettyCheckbox = new Button(group, SWT.CHECK);
		prettyCheckbox.addSelectionListener(listener);
		prettyCheckbox.setText("pretty");

		jsonCheckbox = new Button(group, SWT.CHECK);
		jsonCheckbox.addSelectionListener(listener);
		jsonCheckbox.setText("JSON");

		progressCheckbox = new Button(group, SWT.CHECK);
		progressCheckbox.addSelectionListener(listener);
		progressCheckbox.setText("progress");

		rerunCheckbox = new Button(group, SWT.CHECK);
		rerunCheckbox.addSelectionListener(listener);
		rerunCheckbox.setText("rerun");

		usageCheckbox = new Button(group, SWT.CHECK);
		usageCheckbox.addSelectionListener(listener);
		usageCheckbox.setText("usage");

		// Need to add option to choose path before can enable

		htmlCheckbox = new Button(group, SWT.CHECK);
		htmlCheckbox.addSelectionListener(listener);
		htmlCheckbox.setText("HTML");
		htmlCheckbox.setVisible(false);

		junitCheckbox = new Button(group, SWT.CHECK);
		junitCheckbox.addSelectionListener(listener);
		junitCheckbox.setText("JUnit");
		junitCheckbox.setVisible(false);

	}

	public void createRubyOptions(Composite comp) {
		Font font = comp.getFont();
		grpRubyonlyForRuby = new Group(comp, SWT.NONE);
		grpRubyonlyForRuby.setLayout(new GridLayout(3, false));
		GridData gd_grpRubyonlyForRuby = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_grpRubyonlyForRuby.widthHint = 715;
		gd_grpRubyonlyForRuby.heightHint = 88;
		grpRubyonlyForRuby.setLayoutData(gd_grpRubyonlyForRuby);
		grpRubyonlyForRuby.setFont(font);
		grpRubyonlyForRuby.setText("Ruby(Only for Ruby Project)");

		lblRubyPath = new Label(grpRubyonlyForRuby, SWT.NONE);
		lblRubyPath.setText("Ruby    Path:");

		rubypath = new Text(grpRubyonlyForRuby, SWT.BORDER);
		GridData gd_rubypath = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_rubypath.widthHint = 274;
		rubypath.setLayoutData(gd_rubypath);
		rubypath.addModifyListener(listener);

		rubybutton = createPushButton(grpRubyonlyForRuby, LauncherMessages.AbstractJavaMainTab_1, null);
		rubybutton.addSelectionListener(listener);

		lblRubyArguments = new Label(grpRubyonlyForRuby, SWT.NONE);
		lblRubyArguments.setText("Rdebug-ide Path:");

		rubyparameter = new Text(grpRubyonlyForRuby, SWT.BORDER);
		// rubyparameter.setText("-EUTF-8 -e
		// $stdout.sync=true;$stderr.sync=true;load($0=ARGV.shift)");
		rubyparameter.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(grpRubyonlyForRuby, SWT.NONE);
		new Label(grpRubyonlyForRuby, SWT.NONE);
	}

	@Override
	public String getName() {
		return CucumberFeatureLaunchConstants.CUCUMBER_FEATURE_RUNNER;
	}

	@Override
	public void performApply(ILaunchConfigurationWorkingCopy config) {
		config.setAttribute(IJavaLaunchConfigurationConstants.ATTR_PROJECT_NAME, fProjText.getText().trim());
		config.setAttribute(CucumberFeatureLaunchConstants.ATTR_FEATURE_PATH, featurePathText.getText().trim());
		config.setAttribute(CucumberFeatureLaunchConstants.ATTR_GLUE_PATH, gluePathText.getText().trim());
		config.setAttribute(CucumberFeatureLaunchConstants.ATTR_GLUE_OPTION, glueOptions.getText().trim());
		config.setAttribute(CucumberFeatureLaunchConstants.ATTR_IS_MONOCHROME, monochromeCheckbox.getSelection());
		config.setAttribute(CucumberFeatureLaunchConstants.ATTR_IS_JSON, jsonCheckbox.getSelection());
		config.setAttribute(CucumberFeatureLaunchConstants.ATTR_IS_PROGRESS, progressCheckbox.getSelection());
		config.setAttribute(CucumberFeatureLaunchConstants.ATTR_IS_PRETTY, prettyCheckbox.getSelection());
		config.setAttribute(CucumberFeatureLaunchConstants.ATTR_IS_HTML, htmlCheckbox.getSelection());
		config.setAttribute(CucumberFeatureLaunchConstants.ATTR_IS_USAGE, usageCheckbox.getSelection());
		config.setAttribute(CucumberFeatureLaunchConstants.ATTR_IS_JUNIT, junitCheckbox.getSelection());
		config.setAttribute(CucumberFeatureLaunchConstants.ATTR_IS_RERUN, rerunCheckbox.getSelection());

		/*
		 * config.setAttribute(CucumberFeatureLaunchConstants.RUBY_PATH,
		 * rubypath.getText().trim());
		 * config.setAttribute(CucumberFeatureLaunchConstants.RUBY_PARAMETER,
		 * rubyparameter.getText().trim());
		 */
		config.setAttribute(CucumberFeatureLaunchConstants.IS_JAVA, btnIsJavaProject.getSelection());
		mapResources(config);

	}

	/*
	 * private String getDefaultGluePath() { return
	 * CucumberFeatureLaunchConstants.DEFAULT_CLASSPATH; }
	 */

	@Override
	public void setDefaults(ILaunchConfigurationWorkingCopy config) {

		IProject javaProject = CommonCucumberFeatureUtils.getProject();
		boolean isjava = false;
		try {
			isjava = javaProject.isNatureEnabled("org.eclipse.jdt.core.javanature");
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String featurePath = CommonCucumberFeatureUtils.getFeaturePath();
		String gluePath = CucumberFeatureLaunchConstants.DEFAULT_CLASSPATH;
		String glueOptions = "--expand";
		// -r features";
		String rubypath = CommonPluginUtils.getRubywPath();

		// init the parameter

		String rubydebugparameter = CucumberPeopleActivator.getInstance().getPreferenceStore()
				.getString(PreferenceConstants.RUBY_DEBUG_PATH);
		// config.getAttribute(CucumberFeatureLaunchConstants.RUBY_PARAMETER,
		// rubyparameter);
		// rubydebugparameter=rubydebugparameter.replaceAll("\\\\", "/");

		System.out.println("Default path is:" + featurePath + "," + gluePath + "," + rubypath);

		if (javaProject != null && featurePath != null && rubypath != null) {
			initializeCucumberProject(isjava, gluePath, glueOptions, featurePath, rubypath, rubydebugparameter,
					javaProject, config);
		} else {
			config.setAttribute(IJavaLaunchConfigurationConstants.ATTR_PROJECT_NAME, "");
			config.setAttribute(CucumberFeatureLaunchConstants.ATTR_FEATURE_PATH, "");
			config.setAttribute(CucumberFeatureLaunchConstants.ATTR_GLUE_PATH, "");
			config.setAttribute(CucumberFeatureLaunchConstants.ATTR_GLUE_OPTION, "");
			config.setAttribute(CucumberFeatureLaunchConstants.ATTR_IS_MONOCHROME, true);
			config.setAttribute(CucumberFeatureLaunchConstants.ATTR_IS_PRETTY, true);
			config.setAttribute(CucumberFeatureLaunchConstants.ATTR_IS_PROGRESS, true);
			config.setAttribute(CucumberFeatureLaunchConstants.ATTR_IS_HTML, true);
			config.setAttribute(CucumberFeatureLaunchConstants.ATTR_IS_JSON, true);
			config.setAttribute(CucumberFeatureLaunchConstants.ATTR_IS_USAGE, true);
			config.setAttribute(CucumberFeatureLaunchConstants.ATTR_IS_JUNIT, true);
			config.setAttribute(CucumberFeatureLaunchConstants.ATTR_IS_RERUN, true);

			config.setAttribute(CucumberFeatureLaunchConstants.RUBY_PATH, "");
			config.setAttribute(CucumberFeatureLaunchConstants.RUBY_PARAMETER, "");
			config.setAttribute(CucumberFeatureLaunchConstants.IS_JAVA, false);
		}

	}

	@Override
	protected void handleSearchButtonSelected() {

	}

	@Override
	public void initializeFrom(ILaunchConfiguration config) {
		// TODO Auto-generated method stub
		/*
		 * try { System.out.println("initialize from function :"
		 * +config.getAttribute(CucumberFeatureLaunchConstants.RUBY_PATH, ""));
		 * } catch (CoreException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */

		super.initializeFrom(config);
		updateProjectNature(config);
		updateFeaturePathFromConfig(config);
		updateGluePathFromConfig(config);
		updateFormattersFromConfig(config);
		updateRubyPathConfig(config);
	}

	private void updateProjectNature(ILaunchConfiguration config) {
		// CommonCucumberFeatureUtils.updateFromConfig(config,
		// CucumberFeatureLaunchConstants.ATTR_FEATURE_PATH, featurePathText);
		boolean isjava = false;
		try {
			isjava = config.getAttribute(CucumberFeatureLaunchConstants.IS_JAVA, false);
		} catch (CoreException e) {
			e.printStackTrace();
		}
		btnIsJavaProject.setSelection(isjava);
	}

	private void updateFeaturePathFromConfig(ILaunchConfiguration config) {
		CommonCucumberFeatureUtils.updateFromConfig(config, CucumberFeatureLaunchConstants.ATTR_FEATURE_PATH,
				featurePathText);
	}

	private void updateGluePathFromConfig(ILaunchConfiguration config) {
		CommonCucumberFeatureUtils.updateFromConfig(config, CucumberFeatureLaunchConstants.ATTR_GLUE_PATH,
				gluePathText);
		CommonCucumberFeatureUtils.updateFromConfig(config, CucumberFeatureLaunchConstants.ATTR_GLUE_OPTION,
				glueOptions);
	}

	private void updateFormattersFromConfig(ILaunchConfiguration config) {
		monochromeCheckbox.setSelection(
				CommonCucumberFeatureUtils.updateFromConfig(config, CucumberFeatureLaunchConstants.ATTR_IS_MONOCHROME));
		jsonCheckbox.setSelection(
				CommonCucumberFeatureUtils.updateFromConfig(config, CucumberFeatureLaunchConstants.ATTR_IS_JSON));
		junitCheckbox.setSelection(
				CommonCucumberFeatureUtils.updateFromConfig(config, CucumberFeatureLaunchConstants.ATTR_IS_JUNIT));
		prettyCheckbox.setSelection(
				CommonCucumberFeatureUtils.updateFromConfig(config, CucumberFeatureLaunchConstants.ATTR_IS_PRETTY));
		progressCheckbox.setSelection(
				CommonCucumberFeatureUtils.updateFromConfig(config, CucumberFeatureLaunchConstants.ATTR_IS_PROGRESS));
		htmlCheckbox.setSelection(
				CommonCucumberFeatureUtils.updateFromConfig(config, CucumberFeatureLaunchConstants.ATTR_IS_HTML));
		usageCheckbox.setSelection(
				CommonCucumberFeatureUtils.updateFromConfig(config, CucumberFeatureLaunchConstants.ATTR_IS_USAGE));
		rerunCheckbox.setSelection(
				CommonCucumberFeatureUtils.updateFromConfig(config, CucumberFeatureLaunchConstants.ATTR_IS_RERUN));

	}

	private void updateRubyPathConfig(ILaunchConfiguration config) {
		CommonCucumberFeatureUtils.updateFromConfig(config, CucumberFeatureLaunchConstants.RUBY_PATH, rubypath);
		CommonCucumberFeatureUtils.updateFromConfig(config, CucumberFeatureLaunchConstants.RUBY_PARAMETER,
				rubyparameter);

	}

	protected void initializeCucumberProject(boolean isjava, String gluePath, String glueOption, String featurePath,
			String rubypath, String rubyparameter, IProject javaProject, ILaunchConfigurationWorkingCopy config) {
		String name = null;
		if (javaProject != null && javaProject.exists()) {
			name = javaProject.getName();
		}

		config.setAttribute(IJavaLaunchConfigurationConstants.ATTR_PROJECT_NAME, name);
		config.setAttribute(CucumberFeatureLaunchConstants.ATTR_FEATURE_PATH, featurePath);
		config.setAttribute(CucumberFeatureLaunchConstants.ATTR_GLUE_PATH, gluePath);
		config.setAttribute(CucumberFeatureLaunchConstants.ATTR_GLUE_OPTION, glueOption);
		config.setAttribute(CucumberFeatureLaunchConstants.ATTR_IS_MONOCHROME, true);
		config.setAttribute(CucumberFeatureLaunchConstants.ATTR_IS_PRETTY, true);
		config.setAttribute(CucumberFeatureLaunchConstants.ATTR_IS_HTML, false);
		config.setAttribute(CucumberFeatureLaunchConstants.ATTR_IS_USAGE, false);
		config.setAttribute(CucumberFeatureLaunchConstants.ATTR_IS_PROGRESS, false);
		config.setAttribute(CucumberFeatureLaunchConstants.ATTR_IS_RERUN, false);
		config.setAttribute(CucumberFeatureLaunchConstants.ATTR_IS_JSON, false);
		config.setAttribute(CucumberFeatureLaunchConstants.ATTR_IS_JUNIT, false);

		config.setAttribute(CucumberFeatureLaunchConstants.RUBY_PATH, rubypath);
		config.setAttribute(CucumberFeatureLaunchConstants.RUBY_PARAMETER, rubyparameter);
		config.setAttribute(CucumberFeatureLaunchConstants.IS_JAVA, isjava);
	}

}
