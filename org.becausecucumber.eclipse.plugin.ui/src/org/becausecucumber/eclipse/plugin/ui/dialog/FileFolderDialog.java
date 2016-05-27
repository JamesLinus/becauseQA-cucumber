package org.becausecucumber.eclipse.plugin.ui.dialog;

import java.io.File;

import javax.swing.JFileChooser;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.ContainerSelectionDialog;

@SuppressWarnings("restriction")
public class FileFolderDialog {

	public static FileDialog openSaveFileDialog(Shell shell, String path, String defualtfilename) {
		FileDialog dlg = new FileDialog(shell, SWT.SAVE);
		dlg.setFileName(defualtfilename);
		dlg.setFilterPath(path);
		dlg.setFilterExtensions(new String[] { "*.rb", "*.java", "*.*" });
		dlg.setFilterNames(new String[] { "Ruby Files", "Java Files", "All Files (*.*)" });
		return dlg;
	}

	public static DirectoryDialog openDefaultDialog(Shell shell, String title, String description, String filterpath) {
		DirectoryDialog dlg = new DirectoryDialog(shell);
		if (filterpath != null && filterpath != "") {
			dlg.setFilterPath(filterpath);
		} else {
			dlg.setFilterPath(new File(".").getParent());
		}
		dlg.setText(title);
		dlg.setMessage(description);
		return dlg;
	}

	public static JFileChooser openSwingFolder(String title, String filterpath) {

		JFileChooser dlg = new JFileChooser();
		dlg.setCurrentDirectory(new File(filterpath));
		dlg.setDialogTitle(title);
		dlg.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		dlg.setAcceptAllFileFilterUsed(false);
		return dlg;

	}

	public static ContainerSelectionDialog openEclipseFolder(Shell shell, String title, String message,
			String filterpath) {
		IContainer container = null;
		if (filterpath != null || filterpath != "") {
			container = ResourcesPlugin.getWorkspace().getRoot().getContainerForLocation(Path.fromOSString(filterpath));
		}
		ContainerSelectionDialog dlg = new ContainerSelectionDialog(shell, container, true, message);

		dlg.setTitle(title);
		dlg.setMessage(message);
		// dlg.setInitialSelections(selectedElements);

		return dlg;

	}
}
