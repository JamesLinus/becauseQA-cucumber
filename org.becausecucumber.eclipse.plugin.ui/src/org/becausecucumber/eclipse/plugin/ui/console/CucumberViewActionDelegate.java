package org.becausecucumber.eclipse.plugin.ui.console;

import org.becausecucumber.eclipse.plugin.common.CommandUtils;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;

@SuppressWarnings("restriction")
public class CucumberViewActionDelegate implements IViewActionDelegate {

	@Override
	public void run(IAction action) {
		// TODO Auto-generated method stub

		boolean checked = action.isChecked();
		// boolean enabled = action.isEnabled();
		/*
		 * ImageDescriptor imageDescriptor = action.getImageDescriptor(); String
		 * currentImage = action.getImageDescriptor().toString(); boolean
		 * isCurrentIconEnabled = true; if (currentImage.indexOf("start.png") ==
		 * -1) isCurrentIconEnabled = false;
		 */
		if (checked) { // current status does not match current icon, swap icons
			CommandUtils.stopProcess();
			ImageDescriptor imgDesc = action.getDisabledImageDescriptor();
			action.setDisabledImageDescriptor(action.getImageDescriptor());
			action.setToolTipText("Cucumber Terminate");
			action.setImageDescriptor(imgDesc);
			//
		} else {
			ImageDescriptor imgDesc = action.getDisabledImageDescriptor();
			// ImageDescriptor imageDescriptor = action.getImageDescriptor();
			action.setDisabledImageDescriptor(action.getImageDescriptor());
			action.setToolTipText("Cucumber Start");
			action.setImageDescriptor(imgDesc);
		}
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(IViewPart view) {
		// TODO Auto-generated method stub

	}

}
