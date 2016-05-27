package org.becausecucumber.eclipse.plugin.ui.common;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.xtext.ui.XtextProjectHelper;

@SuppressWarnings("restriction")
public class ProjectNature implements IProjectNature {

	public static final String NATURE_ID = XtextProjectHelper.NATURE_ID;
	// "org.eclipse.xtext.ui.shared.xtextNature"; //$NON-NLS-1$

	@Override
	public void configure() throws CoreException {
		// TODO Auto-generated method stub
	}

	@Override
	public void deconfigure() throws CoreException {
		// TODO Auto-generated method stub
	}

	@Override
	public IProject getProject() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setProject(IProject project) {
		// TODO Auto-generated method stub
	}

}