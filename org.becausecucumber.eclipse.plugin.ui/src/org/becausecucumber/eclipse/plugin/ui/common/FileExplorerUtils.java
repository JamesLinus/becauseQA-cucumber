package org.becausecucumber.eclipse.plugin.ui.common;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.internal.core.JarPackageFragmentRoot;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

@SuppressWarnings("restriction")
public class FileExplorerUtils {

	public static void handleSelection(Object element) {
		if (element instanceof IResource)
			handleResource((IResource) element);
		else if (element instanceof IJavaElement) {
			IJavaElement javaElement = (IJavaElement) element;
			IPath path = null;
			IResource resource;
			for (resource = null; path == null && resource == null && javaElement != null;) {
				resource = javaElement.getResource();
				if (resource == null) {
					if (javaElement instanceof JarPackageFragmentRoot)
						path = ((JarPackageFragmentRoot) javaElement).getPath();
					javaElement = javaElement.getParent();
				}
			}

			if (path != null)
				handlePath(path);
			else if (resource != null)
				handleResource(resource);
			else
				showInfo("Sorry, I cannot find an associated resource for " + element);
		} else {
			String clazz = element != null ? element.getClass().toString() : "";
			showInfo("Sorry, I cannot handle the selected element:\n" + clazz + "\n" + element);
		}
	}

	private static void handleResource(IResource resource) {
		IPath path = resource == null ? null : resource.getLocation();
		if (path == null) {
			showInfo("No path for resource " + resource);
			return;
		} else {
			handlePath(path);
			return;
		}
	}

	private static void handlePath(IPath path) {
		System.out.println("ExploreFS is about to open: " + path);
		File file = path.toFile();
		boolean isDir;
		if (file.isFile())
			isDir = false;
		else if (file.isDirectory()) {
			isDir = true;
		} else {
			showInfo("The selection cannot be identified as a file or directory - does it exist in the file system?\n"
					+ path);
			return;
		}
		String osPath = path.toOSString();
		String osName = System.getProperty("os.name").toLowerCase();
		if (osName.indexOf("windows") != -1)
			exploreInWindowsExplorer(isDir, osPath);
		else if (osName.indexOf("mac") != -1)
			executeCommandForceDir("open", osPath, file);
		else if (osName.indexOf("linux") != -1)
			executeLinuxCommand(osPath, file);
		else
			showInfo("Sorry, I do not know how to open this for your operating system:\n" + osName);
	}

	private static void executeLinuxCommand(String osPath, File file) {
		String desktop = System.getProperty("sun.desktop");
		if (desktop == null)
			desktop = "";
		desktop = desktop.toLowerCase();
		if (desktop.indexOf("gnome") != -1)
			executeCommandForceDir("gnome-open", osPath, file);
		else if (desktop.indexOf("konqueror") != -1 || desktop.indexOf("kde") != -1) {
			executeCommandForceDir("konqueror", osPath, file);
		} else {
			String msg = "Sorry, I do not know how to open the file manager for your Linux desktop \"" + desktop
					+ "\".\n" + "I will try to use konqueror.\n"
					+ "Mail this info to markus at junginger biz, so I can be taught how to handle this.";
			showInfo(msg);
			executeCommandForceDir("konqueror", osPath, file);
		}
	}

	private static void executeCommandForceDir(String baseCommand, String osPath, File file) {
		String forceDirectoryPath = osPath;
		if (file.isFile())
			try {
				forceDirectoryPath = file.getParentFile().getCanonicalPath();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		String args[] = { baseCommand, forceDirectoryPath };
		try {
			Runtime.getRuntime().exec(args);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private static void exploreInWindowsExplorer(boolean isDir, String osPath) {
		String command;
		if (isDir)
			command = "explorer.exe ";
		else
			command = "explorer.exe /SELECT,";
		command = command + "\"" + osPath + "\"";
		try {
			Runtime.getRuntime().exec(command);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private static void showInfo(String msg) {
		Shell shell = new Shell();
		MessageDialog.openInformation(shell, "ExploreFS", msg);
	}
}
