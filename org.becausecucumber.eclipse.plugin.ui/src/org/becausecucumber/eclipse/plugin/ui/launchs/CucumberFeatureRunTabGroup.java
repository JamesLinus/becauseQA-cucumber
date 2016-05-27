package org.becausecucumber.eclipse.plugin.ui.launchs;

import org.eclipse.debug.ui.AbstractLaunchConfigurationTabGroup;
import org.eclipse.debug.ui.CommonTab;
import org.eclipse.debug.ui.ILaunchConfigurationDialog;
import org.eclipse.debug.ui.ILaunchConfigurationTab;
import org.eclipse.debug.ui.ILaunchConfigurationTabGroup;
import org.eclipse.jdt.debug.ui.launchConfigurations.JavaClasspathTab;
import org.eclipse.jdt.debug.ui.launchConfigurations.JavaJRETab;

public class CucumberFeatureRunTabGroup extends AbstractLaunchConfigurationTabGroup
		implements ILaunchConfigurationTabGroup {

	@Override
	public void createTabs(ILaunchConfigurationDialog arg0, String arg1) {
		ILaunchConfigurationTab[] tabs = new ILaunchConfigurationTab[] { new CucumberRunMainTab(),
				// new JavaArgumentsTab(),
				new JavaJRETab(), new JavaClasspathTab(), new CommonTab() };
		setTabs(tabs);
	}
}
