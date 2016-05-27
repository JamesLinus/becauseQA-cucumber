package org.becausecucumber.eclipse.plugin.ui.launchs;

import org.becausecucumber.eclipse.plugin.common.CommonCucumberFeatureUtils;
import org.becausecucumber.eclipse.plugin.common.CommonPluginUtils;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.ILaunchShortcut2;
import org.eclipse.jdt.launching.IJavaLaunchConfigurationConstants;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.pde.ui.launcher.AbstractLaunchShortcut;

import org.eclipse.ui.IEditorPart;

public class CucumberRunFeatureLaunchShortcut extends AbstractLaunchShortcut implements ILaunchShortcut2 {

	private String newLaunchConfigurationName;

	@Override
	public void launch(ISelection selection, String arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void launch(IEditorPart part, String mode) {
		newLaunchConfigurationName = part.getTitle();
		/*
		 * if ( part instanceof ITextEditor ) { final ITextEditor editor =
		 * (ITextEditor)part; // IDocumentProvider prov =
		 * editor.getDocumentProvider(); //IDocument doc = prov.getDocument(
		 * editor.getEditorInput() ); ISelection sel =
		 * editor.getSelectionProvider().getSelection(); if ( sel instanceof
		 * TextSelection ) { final TextSelection textSel = (TextSelection)sel;
		 * String selctedtext = textSel.getText();
		 * if(selctedtext!=null&&selctedtext!=""){ featurePath=
		 * newFeaturefile(textSel.getText()); } } }
		 */
		launch(mode);
	}

	@Override
	public ILaunchConfiguration[] getLaunchConfigurations(ISelection arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ILaunchConfiguration[] getLaunchConfigurations(IEditorPart arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IResource getLaunchableResource(ISelection arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IResource getLaunchableResource(IEditorPart arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getLaunchConfigurationTypeName() {
		return CucumberFeatureLaunchConstants.CUCUMBER_FEATURE_LAUNCH_CONFIG_TYPE_RUN;
	}

	@Override
	protected void initializeConfiguration(ILaunchConfigurationWorkingCopy config) {
		IProject project = CommonCucumberFeatureUtils.getProject();
		boolean isjava = false;
		try {
			isjava = project.isNatureEnabled("org.eclipse.jdt.core.javanature");
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String rubypath = CommonPluginUtils.getRubyPath();
		config.setAttribute(IJavaLaunchConfigurationConstants.ATTR_PROJECT_NAME, project.getName());
		config.setAttribute(CucumberFeatureLaunchConstants.ATTR_FEATURE_PATH,
				CommonCucumberFeatureUtils.getFeaturePath());
		// config.setAttribute(CucumberFeatureLaunchConstants.ATTR_FEATURE_PATH,
		// CommonCucumberFeatureUtils.getFeaturePath());
		config.setAttribute(CucumberFeatureLaunchConstants.ATTR_GLUE_PATH,
				CucumberFeatureLaunchConstants.DEFAULT_CLASSPATH);
		config.setAttribute(CucumberFeatureLaunchConstants.ATTR_GLUE_OPTION, "--expand");
		// -r features");
		config.setAttribute(CucumberFeatureLaunchConstants.ATTR_IS_MONOCHROME, true);
		config.setAttribute(CucumberFeatureLaunchConstants.ATTR_IS_PRETTY, true);
		config.setAttribute(CucumberFeatureLaunchConstants.ATTR_IS_HTML, false);
		config.setAttribute(CucumberFeatureLaunchConstants.ATTR_IS_PROGRESS, false);
		config.setAttribute(CucumberFeatureLaunchConstants.ATTR_IS_JSON, true); // for
																				// report
		config.setAttribute(CucumberFeatureLaunchConstants.ATTR_IS_JUNIT, false);
		config.setAttribute(CucumberFeatureLaunchConstants.ATTR_IS_RERUN, false);
		config.setAttribute(CucumberFeatureLaunchConstants.ATTR_IS_USAGE, false);
		config.setAttribute(CucumberFeatureLaunchConstants.RUBY_PATH, rubypath);
		config.setAttribute(CucumberFeatureLaunchConstants.RUBY_PARAMETER,
				"-EUTF-8 -e $stdout.sync=true;$stderr.sync=true;load($0=ARGV.shift)");

		config.setAttribute(CucumberFeatureLaunchConstants.IS_JAVA, isjava);
	}

	@Override
	protected String getName(ILaunchConfigurationType type) {
		if (newLaunchConfigurationName != null) {
			return newLaunchConfigurationName;
		}
		return super.getName(type);
	}

	@Override
	protected boolean isGoodMatch(ILaunchConfiguration configuration) {
		boolean goodType = isGoodType(configuration);
		boolean goodName = isGoodName(configuration);
		return goodType && goodName;
	}

	private boolean isGoodName(ILaunchConfiguration configuration) {
		return configuration.getName().equals(newLaunchConfigurationName);
	}

	private boolean isGoodType(ILaunchConfiguration configuration) {
		try {
			String identifier = configuration.getType().getIdentifier();
			return CucumberFeatureLaunchConstants.CUCUMBER_FEATURE_LAUNCH_CONFIG_TYPE_RUN.equals(identifier);
		} catch (CoreException e) {
			return false;
		}
	}

}
