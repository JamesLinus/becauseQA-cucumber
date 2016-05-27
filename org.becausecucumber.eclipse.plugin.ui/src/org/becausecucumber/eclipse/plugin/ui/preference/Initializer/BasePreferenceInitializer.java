package org.becausecucumber.eclipse.plugin.ui.preference.Initializer;

import java.io.File;
import org.becausecucumber.eclipse.plugin.common.CommonPluginUtils;
import org.becausecucumber.eclipse.plugin.ui.CucumberPeopleActivator;
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

public class BasePreferenceInitializer extends AbstractPreferenceInitializer {

	public BasePreferenceInitializer() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initializeDefaultPreferences() {
		// TODO Auto-generated method stub
		IPreferenceStore store = CucumberPeopleActivator.getInstance().getPreferenceStore();
		store.setDefault(PreferenceConstants.TESTRAIL_URL, PreferenceConstants.TESTRAIL_BASEURL);
		// https://gdcqatestrail01/testrail
		store.setDefault(PreferenceConstants.TESTRAIL_PROJECT, PreferenceConstants.TESTRAIL_PROJECTNAME);
		// Green Dot Network
		store.setDefault(PreferenceConstants.TESTRAIL_USER, "");
		// ahu
		store.setDefault(PreferenceConstants.TESTRAIL_PASSWORD, "");

		store.setDefault(PreferenceConstants.CUCUMBER_GIVEN, "");
		store.setDefault(PreferenceConstants.CUCUMBER_THEN, "");
		store.setDefault(PreferenceConstants.CUCUMBER_WHEN, "");

		store.setDefault(PreferenceConstants.TESTRAIL_TESTSUITE, "");
		store.setDefault(PreferenceConstants.TESTRAIL_TESTSECTION, "");

		store.setDefault(PreferenceConstants.TESTRAIL_DONE, false);
		store.setValue(PreferenceConstants.TESTRAIL_DONE, false);

		store.setValue(PreferenceConstants.ENV_VALUE, PreferenceConstants.ENV_VALUE);

		store.setDefault(PreferenceConstants.STEP_DEFINITION_NAME, "step_definitions");
		store.setDefault(PreferenceConstants.DEFAULT_FILE_TYPE, "ruby");
		// store.setDefault(PreferenceConstants.SAVE_TESTRAIL, false);

		store.setDefault(PreferenceConstants.SAVE_RESULT, 3);

		store.setDefault(PreferenceConstants.SRCBIN_BINNAME, "bin");
		store.setDefault(PreferenceConstants.SRCBIN_SRCNAME, "src");

		store.setDefault(PreferenceConstants.CHECK_PLUGIN, true);
		store.setDefault(PreferenceConstants.AUTO_VALIDATOR, false);

		String logfile = System.getProperty("user.home") + File.separator + "cucumber.log";
		store.setDefault(PreferenceConstants.LOG_PATH, logfile);

		// Get the ruby
		String rubyPath = CommonPluginUtils.getRubywPath();
		store.setDefault(PreferenceConstants.RUBY_PATH, rubyPath);
		String rubyGemPath = CommonPluginUtils.getRubyGemPath("ruby-debug-ide");
		store.setDefault(PreferenceConstants.RUBY_DEBUG_PATH, rubyGemPath);
		// String tempstr=rubyGemPath.replaceAll("\\\\", "/");

		// jenkins configuration

		store.setDefault(PreferenceConstants.JENKINS_URL, PreferenceConstants.JENKINS_BASEURL);
		store.setDefault(PreferenceConstants.JENKINS_USER, "");
		store.setDefault(PreferenceConstants.JENKINS_PASSWORD, "");

		// view result configuration
		store.setDefault(PreferenceConstants.VIEW_RESULT, true);
		store.setDefault(PreferenceConstants.BUILD_NUMBER, 0);

	}

}
