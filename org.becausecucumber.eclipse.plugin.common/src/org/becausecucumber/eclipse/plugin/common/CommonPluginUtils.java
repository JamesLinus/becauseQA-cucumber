package org.becausecucumber.eclipse.plugin.common;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import org.becausecucumber.eclipse.plugin.common.console.Log;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.IClassFile;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IOpenable;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.internal.corext.util.JavaModelUtil;
import org.eclipse.jdt.internal.ui.JavaPlugin;
import org.eclipse.jdt.internal.ui.packageview.PackageFragmentRootContainer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.texteditor.AbstractTextEditor;
import org.eclipse.ui.texteditor.ITextEditor;

@SuppressWarnings("restriction")
public class CommonPluginUtils {

	public static IWorkbench instance;

	public static IWorkbenchWindow activeWindow;
	public static IWorkbenchPage activePage;

	public static IWorkbench getInstance() {

		if (instance == null) {
			instance = PlatformUI.getWorkbench();
		}
		return instance;
	}

	public static IWorkbenchWindow getActiveWindow() {

		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				activeWindow = getInstance().getActiveWorkbenchWindow();
			}
		});

		return activeWindow;
	}

	public static IWorkbenchPage getActivePage() {

		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				activePage = getActiveWindow().getActivePage();
			}
		});

		return activePage;
	}

	public static IFile getActiveEditorPath() {

		IEditorPart activeEditor = getActivePage().getActiveEditor();

		if (!(activeEditor instanceof AbstractTextEditor)) {
			return null;
		}
		ITextEditor editor = (ITextEditor) activeEditor;

		IEditorInput input = editor.getEditorInput();

		IFile path = input instanceof FileEditorInput ? ((FileEditorInput) input).getFile() : null;

		return path;
	}

	public static IResource getActiveEditorPath2() {
		// IWorkbenchWindow activeWorkbenchWindow =
		// instance.getActiveWorkbenchWindow();
		IEditorPart activeEditor = getActivePage().getActiveEditor();

		if (!(activeEditor instanceof AbstractTextEditor)) {
			return null;
		}
		ITextEditor editor = (ITextEditor) activeEditor;

		IEditorInput input = editor.getEditorInput();

		IResource resource = (IResource) input.getAdapter(IFile.class);
		if (resource == null) {
			resource = (IResource) input.getAdapter(IResource.class);
		}
		/*
		 * IPath path = input instanceof FileEditorInput ?
		 * ((FileEditorInput)input).getPath():null; IResource findpath =
		 * ResourcesPlugin.getWorkspace().getRoot().findMember(path); IResource
		 * findMember =( path!=null ?
		 * ResourcesPlugin.getWorkspace().getRoot().findMember(path):null);
		 */
		return resource;
	}

	public static IPath getEditorHandler(ExecutionEvent event) throws ExecutionException {
		// Shell shell = HandlerUtil.getActiveShell(event);

		IWorkbenchWindow windows = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		IEditorPart activeEditor = windows.getActivePage().getActiveEditor();

		if (!(activeEditor instanceof AbstractTextEditor)) {
			return null;
		}
		ITextEditor editor = (ITextEditor) activeEditor;

		IEditorInput input = editor.getEditorInput();

		IPath path = input instanceof FileEditorInput ? ((FileEditorInput) input).getPath() : null;
		return path;
	}

	public static Object getTreeExplorerHandler(ExecutionEvent event) throws ExecutionException {
		Object element = null;
		ISelectionService selection = HandlerUtil.getActiveWorkbenchWindowChecked(event).getSelectionService();
		if (selection instanceof IStructuredSelection) {
			element = ((IStructuredSelection) selection).getFirstElement();
		}
		return element;
	}

	public static Shell getShell() {
		Shell shell = instance.getActiveWorkbenchWindow().getShell();
		return shell;
	}

	public static IPreferenceStore getPerferenceStore() {
		IPreferenceStore preferenceStore = PlatformUI.getPreferenceStore();
		return preferenceStore;
	}

	public static IStructuredSelection getActiveIStructuredSelection() {

		ISelectionService selectionService = getActiveWindow().getSelectionService();
		IStructuredSelection selection = (selectionService instanceof IStructuredSelection)
				? (IStructuredSelection) selectionService : null;
		return selection;
	}

	public static IProject getActiveProject() {
		IProject project = null;
		try {
			IWorkbenchWindow activeWorkbenchWindow = getActiveWindow();
			IWorkbenchPage activePage = getActivePage();
			ISelectionService selectionService = activeWorkbenchWindow.getSelectionService();
			IEditorPart activeEditor = activePage.getActiveEditor();

			if (activeEditor != null) {
				IFileEditorInput input = (IFileEditorInput) activeEditor.getEditorInput();
				project = input.getFile().getProject();
			}

			if (selectionService instanceof IStructuredSelection) {
				Object element = ((IStructuredSelection) selectionService).getFirstElement();

				if (element instanceof IResource) {
					project = ((IResource) element).getProject();
				} else if (element instanceof PackageFragmentRootContainer) {
					IJavaProject jProject = ((PackageFragmentRootContainer) element).getJavaProject();
					project = jProject.getProject();
				} else if (element instanceof IJavaElement) {
					IJavaProject jProject = ((IJavaElement) element).getJavaProject();
					project = jProject.getProject();
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			Log.error("getActiveProject method exception,please make current project active,exception log: " + e);
		}
		return project;
	}

	public static boolean isJavaNatureProject() {
		boolean isjava = false;
		IWorkbenchWindow activeWorkbenchWindow = getActiveWindow();
		IWorkbenchPage activePage = getActivePage();
		ISelectionService selectionService = activeWorkbenchWindow.getSelectionService();
		IEditorPart activeEditor = activePage.getActiveEditor();
		IProject project = null;
		if (activeEditor != null) {
			IFileEditorInput input = (IFileEditorInput) activeEditor.getEditorInput();
			project = input.getFile().getProject();
		}

		if (selectionService instanceof IStructuredSelection) {
			Object element = ((IStructuredSelection) selectionService).getFirstElement();

			if (element instanceof IResource) {
				project = ((IResource) element).getProject();
			} else if (element instanceof PackageFragmentRootContainer) {
				IJavaProject jProject = ((PackageFragmentRootContainer) element).getJavaProject();
				project = jProject.getProject();
			} else if (element instanceof IJavaElement) {
				IJavaProject jProject = ((IJavaElement) element).getJavaProject();
				project = jProject.getProject();
			}
		}
		try {
			isjava = project.isNatureEnabled("org.eclipse.jdt.core.javanature");
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isjava;
	}

	public static boolean isJavaNatureProject(IProject project) {
		boolean isjava = false;
		try {
			isjava = project.isNatureEnabled("org.eclipse.jdt.core.javanature");
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("is java project:" + isjava);
		return isjava;
	}

	public static IJavaProject getJavaProject(IProject project) {
		return JavaCore.create(project);
	}

	public static boolean isJavaNatureProject(ExecutionEvent event) throws ExecutionException {
		boolean isjava = false;
		IWorkbenchPage activePage = HandlerUtil.getActiveWorkbenchWindowChecked(event).getActivePage();
		IEditorPart activeEditor = activePage.getActiveEditor();
		ISelectionService selectionService = HandlerUtil.getActiveWorkbenchWindowChecked(event).getSelectionService();
		ISelection selection = selectionService.getSelection();

		IProject project = null;
		if (activeEditor != null) {
			IFileEditorInput input = (IFileEditorInput) activeEditor.getEditorInput();
			project = input.getFile().getProject();
		}

		if (selection instanceof IStructuredSelection) {
			Object element = ((IStructuredSelection) selection).getFirstElement();

			if (element instanceof IResource) {
				project = ((IResource) element).getProject();
			} else if (element instanceof PackageFragmentRootContainer) {

				IJavaProject jProject = ((PackageFragmentRootContainer) element).getJavaProject();
				project = jProject.getProject();
			} else if (element instanceof IJavaElement) {
				IJavaProject jProject = ((IJavaElement) element).getJavaProject();
				project = jProject.getProject();
			}
		}
		try {
			isjava = project.isNatureEnabled("org.eclipse.jdt.core.javanature");
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * File filepath=new File("c:\\SDK_INSTALL.log.PAS-VXI-142_000.log");
		 * IFileStore fileStore =
		 * EFS.getLocalFileSystem().getStore(filepath.toURI() ); try {
		 * IDE.openEditorOnFileStore( activePage, fileStore ); //IDE.o } catch
		 * (PartInitException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
		return isjava;

	}

	/*
	 * https://dev.vaadin.com/svn/integration/eclipse/plugins/com.vaadin.
	 * integration .
	 * eclipse/src/com/vaadin/integration/eclipse/wizards/DirectoryPackageWizard
	 * .java
	 */

	public static IStructuredSelection getValidSelection() {
		ISelection currentSelection = JavaPlugin.getActiveWorkbenchWindow().getSelectionService().getSelection();
		if (currentSelection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) currentSelection;
			List<IJavaProject> selectedElements = new ArrayList<IJavaProject>(structuredSelection.size());
			@SuppressWarnings("unchecked")
			Iterator<Object> iter = structuredSelection.iterator();
			while (iter.hasNext()) {
				Object selectedElement = iter.next();
				if (selectedElement instanceof IProject) {
					addProject(selectedElements, (IProject) selectedElement);
				} else if (selectedElement instanceof IResource) {
					addProject(selectedElements, ((IResource) selectedElement).getProject());
				} else if (selectedElement instanceof IJavaElement) {
					addJavaElement(selectedElements, (IJavaElement) selectedElement);
				}
			}
			return new StructuredSelection(selectedElements);
		} else {
			return StructuredSelection.EMPTY;
		}
	}

	@SuppressWarnings("unchecked")
	private static void addProject(@SuppressWarnings("rawtypes") List selectedElements, IProject project) {
		try {
			if (project.hasNature(JavaCore.NATURE_ID))
				selectedElements.add(JavaCore.create(project));
		} catch (CoreException ex) {
			// ignore selected element
		}
	}

	@SuppressWarnings("unchecked")
	public static void addJavaElement(@SuppressWarnings("rawtypes") List selectedElements, IJavaElement je) {
		if (je.getElementType() == IJavaElement.COMPILATION_UNIT)
			selectedElements.add(je);
		else if (je.getElementType() == IJavaElement.CLASS_FILE)
			selectedElements.add(je);
		else if (je.getElementType() == IJavaElement.JAVA_PROJECT)
			selectedElements.add(je);
		else if (je.getElementType() == IJavaElement.PACKAGE_FRAGMENT) {
			if (!isInArchiveOrExternal(je))
				selectedElements.add(je);
		} else if (je.getElementType() == IJavaElement.PACKAGE_FRAGMENT_ROOT) {
			if (!isInArchiveOrExternal(je))
				selectedElements.add(je);
		} else {
			IOpenable openable = je.getOpenable();
			if (openable instanceof ICompilationUnit)
				selectedElements.add(((ICompilationUnit) openable).getPrimary());
			else if (openable instanceof IClassFile && !isInArchiveOrExternal(je))
				selectedElements.add(openable);
		}
	}

	private static boolean isInArchiveOrExternal(IJavaElement element) {
		IPackageFragmentRoot root = JavaModelUtil.getPackageFragmentRoot(element);
		return root != null && (root.isArchive() || root.isExternal());
	}

	public static ArrayList<IResource> getAllFilesInProject() {
		ArrayList<IResource> allCFiles = new ArrayList<IResource>();

		IWorkspaceRoot myWorkspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		IProject project = CommonPluginUtils.getActiveProject();
		IPath roots = null;

		roots = project.getLocation();
		if (roots == null) {
			IProject[] projects = myWorkspaceRoot.getProjects();
			for (int k = 0; k < projects.length; k++) {
				IPath path = projects[k].getLocation();
				recursiveFindFiles(allCFiles, path, myWorkspaceRoot);
			}
		} else {
			recursiveFindFiles(allCFiles, roots, myWorkspaceRoot);
		}

		return allCFiles;
	}

	private static void recursiveFindFiles(ArrayList<IResource> allCFiles, IPath path, IWorkspaceRoot myWorkspaceRoot) {

		IContainer container = myWorkspaceRoot.getContainerForLocation(path);
		// if the project is open and accessible
		if (container.isAccessible()) {
			try {
				IResource[] iResources;
				iResources = container.members();
				for (IResource iR : iResources) {
					// for c files
					boolean javaNatureProject = isJavaNatureProject();
					String fileextension = "rb";
					if (javaNatureProject) {
						fileextension = "^*.*steps.java$";
					} else {
						fileextension = "^*.*steps.rb$";
					}
					Pattern compile = Pattern.compile(fileextension, Pattern.CASE_INSENSITIVE);
					boolean found = compile.matcher(iR.getName()).find();
					if (found)
						allCFiles.add(iR);
					if (iR.getType() == IResource.FOLDER) {
						IPath tempPath = iR.getLocation();
						recursiveFindFiles(allCFiles, tempPath, myWorkspaceRoot);
					}
				}
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static String getRubywPath() {
		String rubyfile = null;
		String env = System.getenv("path");
		if (env != null) {
			String[] envs = env.split(";");
			for (int k = 0; k < envs.length; k++) {
				rubyfile = envs[k] + File.separator + "rubyw.exe";
				File ruby = new File(rubyfile);
				if (ruby.exists()) {
					break;
				}
			}
		}
		return rubyfile;
	}

	public static String getRubyPath() {
		String rubyfile = null;
		String env = System.getenv("path");
		if (env != null) {
			String[] envs = env.split(";");
			for (int k = 0; k < envs.length; k++) {
				rubyfile = envs[k] + File.separator + "ruby.exe";
				File ruby = new File(rubyfile);
				if (ruby.exists()) {
					break;
				}
			}
		}

		return rubyfile;
	}

	public static String getCucumberPath() {
		String rubyfile = null;
		String cucumberpath = null;

		String env = System.getenv("path");
		if (env != null) {
			String[] envs = env.split(";");
			for (int k = 0; k < envs.length; k++) {
				rubyfile = envs[k] + File.separator + "rubyw.exe";
				File ruby = new File(rubyfile);
				if (ruby.exists()) {
					String cucumber = ruby.getParent() + File.separator + "cucumber";
					if (new File(cucumber).exists()) {
						cucumberpath = cucumber;
					}
					break;
				}
			}
		}

		return cucumberpath;
	}

	public static String getRubyGemPath(String gemname) {

		String gempath = "";
		Log.info("Check ruby debug tool rdebug-ide installed or not...");
		boolean iswins = PlatformValidator.isWindows();
		if (iswins) {
			String[] command = { "cmd.exe", "/c", "gem", "which", gemname };
			gempath = CommandUtils.newExecuteCommand(null, command);
			// CommandUtils.waitForProcess(CommandUtils.p, 25000, true);
			boolean exists = new File(gempath).exists();
			if (exists) {
				gempath = new File(new File(gempath).getParent()).getParent() + File.separator + "bin" + File.separator
						+ "rdebug-ide";
			}
			// System.out.println(file);
		} else {
			Log.error("For the Ruby-ide we only support for Windows System,please contact the author for help.");
		}

		return gempath;
	}

}
