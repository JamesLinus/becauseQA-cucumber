/**
 * This file is licensed under the University of Illinois/NCSA Open Source License. See LICENSE.TXT for details.
 */
package org.becausecucumber.eclipse.plugin.common;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;

import org.becausecucumber.eclipse.plugin.common.console.Log;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.NotEnabledException;
import org.eclipse.core.commands.NotHandledException;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.equinox.p2.core.IProvisioningAgent;
import org.eclipse.equinox.p2.core.IProvisioningAgentProvider;
import org.eclipse.equinox.p2.core.ProvisionException;
import org.eclipse.equinox.p2.engine.IProfile;
import org.eclipse.equinox.p2.engine.IProfileRegistry;
import org.eclipse.equinox.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.p2.operations.ProvisioningSession;
import org.eclipse.equinox.p2.operations.UpdateOperation;
import org.eclipse.equinox.p2.query.IQuery;
import org.eclipse.equinox.p2.query.QueryUtil;
import org.eclipse.equinox.p2.repository.artifact.IArtifactRepositoryManager;
import org.eclipse.equinox.p2.repository.metadata.IMetadataRepositoryManager;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.handlers.IHandlerService;

public class PluginUpdater {

	private String updateSite;

	private String pluginID;
	public String message=null;

	public PluginUpdater(String updateSite, String pluginID) {
		this.updateSite= updateSite;
		this.pluginID= pluginID;
	}

	private URI getUpdateSiteURI(String updateSite) {
		try {
			return new URI(updateSite);
		} catch (URISyntaxException e) {
			Log.error("Invalid update site URI");
		}
		return null;
	}
/*
 * https://github.com/irbull/rt.eclipse.p2/blob/master/bundles/org.eclipse.equinox.p2.ui.sdk/src/org/eclipse/equinox/internal/p2/ui/sdk/UpdateHandler.java
 * 
 */
	public String checkForUpdates() {
		
		Log.info("Checking if available new cucumber plugin to download");
		Log.info("Checking version process maybe takes serveral minutes and depends on your eclipse performance.");
		//try {
			IProvisioningAgentProvider agentProvider= Activator.getDefault().getProvisioningAgentProvider();
			if (agentProvider == null) {
				Log.error("Could not find a provisioning agent provider.");
				return "Could not find a provisioning agent provider.";
			}

			IProvisioningAgent agent=null;
			try {
				agent = agentProvider.createAgent(null);
			} catch (ProvisionException ep) {
				// TODO Auto-generated catch block
				Log.error("Check cucumber plugin version,ProvisionException :  agentProvider.createAgent(null)");
			}

			IMetadataRepositoryManager metadataRepositoryManager= (IMetadataRepositoryManager)agent.getService(IMetadataRepositoryManager.SERVICE_NAME);

			if (metadataRepositoryManager == null) {
				Log.error("Check cucumber plugin version -- Could not find the meta data repository manager.");
				return "Could not find the meta data repository manager.";
			}

			IArtifactRepositoryManager artifactRepositoryManager= (IArtifactRepositoryManager)agent.getService(IArtifactRepositoryManager.SERVICE_NAME);

			if (artifactRepositoryManager == null) {
				Log.error("Check cucumber plugin version -- Could not find the artifact repository manager.");
				return "Could not find the artifact repository manager.";
			}

			metadataRepositoryManager.addRepository(getUpdateSiteURI(updateSite));
			artifactRepositoryManager.addRepository(getUpdateSiteURI(updateSite));

			try {
				metadataRepositoryManager.loadRepository(getUpdateSiteURI(updateSite), new NullProgressMonitor());
			} catch (ProvisionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (OperationCanceledException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			final IProfileRegistry registry= (IProfileRegistry)agent.getService(IProfileRegistry.SERVICE_NAME);

			if (registry == null) {
				Log.error("Check cucumber plugin version,Could not find the profile registry.");
				return "Could not find the profile registry.";
			}

			final IProfile profile= registry.getProfile(IProfileRegistry.SELF);

			if (profile == null) {
				Log.error("Check cucumber plugin version,Could not find the profile.");
				return "Could not find the profile.";
			}

			IQuery<IInstallableUnit> query= QueryUtil.createIUQuery(pluginID);
			Collection<IInstallableUnit> iusToUpdate= profile.query(query, null).toSet();

			ProvisioningSession provisioningSession= new ProvisioningSession(agent);

			final UpdateOperation updateOperation= new UpdateOperation(provisioningSession, iusToUpdate);

			IStatus modalResolution= updateOperation.resolveModal(new NullProgressMonitor());

			int code = modalResolution.getCode();
			if (modalResolution.isOK()) {
				//boolean openConfirm = MessageDialog.openConfirm(Display.getCurrent().getActiveShell(), "Cucumber Plugin Update", "There is a new cucumber plugin version available ,do you want to update it now ?");
				//if(openConfirm){
					Display.getDefault().asyncExec(new Runnable() {
	
						@Override
						public void run() {
							message="Find a new version available for cucumber plugin.";
							Log.info( "Find a new available cucumber plugin,please follow the guideline to install it. ");
							runCommand("org.eclipse.equinox.p2.ui.sdk.update", "Failed to open the check for updates dialog.", null);
						}
					});
				//}

			}
			if(code==10000){
				message=modalResolution.getMessage();
				Log.info("No available Cucumber plugin to download, "+message);
				/*boolean goToSites = MessageDialog.openQuestion(, "Cucumber Plugin Version Check",modalResolution.getMessage());
				if (goToSites) {
					getProvisioningUI().manipulateRepositories(getShell());
				}*/
			}
		//} 
	   
		return message;
	}

	private static void runCommand(String commandId, String errorMessage, Event event) {
		ICommandService commandService= (ICommandService)PlatformUI.getWorkbench().getService(ICommandService.class);
		Command command= commandService.getCommand(commandId);
		if (!command.isDefined()) {
			return;
		}
		IHandlerService handlerService= (IHandlerService)PlatformUI.getWorkbench().getService(IHandlerService.class);
		try {
			handlerService.executeCommand(commandId, event);
		} catch (ExecutionException e) {
			Log.error(errorMessage);
		} catch (NotDefinedException e) {
			Log.error(errorMessage);
		} catch (NotEnabledException e) {
			Log.error(errorMessage);
		} catch (NotHandledException e) {
			Log.error(errorMessage);
		}
	}

}