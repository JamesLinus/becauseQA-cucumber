package org.becausecucumber.eclipse.plugin.common;



import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.internal.ui.packageview.PackageFragmentRootContainer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;


@SuppressWarnings("restriction")
public class PluginUtils {

	public static boolean isjava=false;
	@SuppressWarnings("null")
	public static boolean isJavaProject(){
		
	  /* IWorkspace workspace = ResourcesPlugin.getWorkspace();
	   IProjectNatureDescriptor[] description = workspace.getNatureDescriptors();*/
		
		
		Display display = Display.getCurrent();
		if(display==null){
			display.asyncExec(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					boolean isgoon=true;
					IProject project = null;  
					ISelection selection = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getSelection();
					if (!(selection instanceof IStructuredSelection))
				          isgoon=false;
				    if(isgoon){
						Object element = ((IStructuredSelection)selection).getFirstElement();  
						//Object[] selectedObjects = ((IStructureSelection)selection).toArray(); 
						//http://www.eclipsezone.com/eclipse/forums/t88986.html
			
				        if (element instanceof IResource) {    
				             project= ((IResource)element).getProject();    
				         } else if (element instanceof PackageFragmentRootContainer) {    
				             IJavaProject jProject =     
				                 ((PackageFragmentRootContainer)element).getJavaProject();    
				             project = jProject.getProject();    
				         } else if (element instanceof IJavaElement) {    
				             IJavaProject jProject= ((IJavaElement)element).getJavaProject();    
				             project = jProject.getProject();    
				         }    
				        try {
							isjava=project.isNatureEnabled("org.eclipse.jdt.core.javanature");
						} catch (CoreException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				    }
				}
			});
			
		}else{
			IProject project = null;  
			ISelection selection = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getSelection();
			if (!(selection instanceof IStructuredSelection))
		          return false;
		  
			Object element = ((IStructuredSelection)selection).getFirstElement();  
				//Object[] selectedObjects = ((IStructureSelection)selection).toArray(); 
				//http://www.eclipsezone.com/eclipse/forums/t88986.html
	
		     if (element instanceof IResource) {    
		             project= ((IResource)element).getProject();    
		      } else if (element instanceof PackageFragmentRootContainer) {    
		             IJavaProject jProject =     
		                 ((PackageFragmentRootContainer)element).getJavaProject();    
		             project = jProject.getProject();    
		      } else if (element instanceof IJavaElement) {    
		             IJavaProject jProject= ((IJavaElement)element).getJavaProject();    
		             project = jProject.getProject();    
		       }    
		      try {
					isjava=project.isNatureEnabled("org.eclipse.jdt.core.javanature");
			  } catch (CoreException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			  }
		}
	   return isjava;
	}
	
	public static String getCurrentDate() {
		String currenttime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a")
				.format(new Date());
		// logger.debug("Get current running time is :" + currenttime);
		return currenttime;
	}
}

