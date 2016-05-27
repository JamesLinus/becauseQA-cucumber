package org.becausecucumber.eclipse.plugin.ui.common;

import java.util.Arrays;
import java.util.List;

import org.apache.maven.model.Model;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.project.ProjectImportConfiguration;
import org.eclipse.m2e.core.ui.internal.wizards.AbstractCreateMavenProjectJob;


public class BaseMavenAutomationTemplateProject {

	@SuppressWarnings({ "unchecked" })
	public void createBaseMavenProject(final IProject project, final IPath location,
			@SuppressWarnings("rawtypes") List workingSets, final Model model) {

		final AbstractCreateMavenProjectJob job;
		final String[] folders = {};

		final ProjectImportConfiguration importConfiguration = new ProjectImportConfiguration();
		job = new AbstractCreateMavenProjectJob("Base Automation Project", workingSets) {
			@Override
			protected List<IProject> doCreateMavenProjects(IProgressMonitor monitor) throws CoreException {
				MavenPlugin.getProjectConfigurationManager().createSimpleProject(project, location, model, folders, //
						importConfiguration, monitor);
				return Arrays.asList(project);
			}
		};

		/*
		 * job.addJobChangeListener(new JobChangeAdapter() { public void
		 * done(IJobChangeEvent event) { final IStatus result =
		 * event.getResult(); if(!result.isOK()) {
		 * Display.getDefault().asyncExec(new Runnable() { public void run() {
		 * MessageDialog.openError(getShell(), //
		 * NLS.bind(Messages.wizardProjectJobFailed, projectName),
		 * result.getMessage()); } }); } } });
		 */

		job.setRule(MavenPlugin.getProjectConfigurationManager().getRule());
		job.schedule();

		while (job.getState() == Job.RUNNING) {
			System.out.println("Maven creating job is still running....");
		}
	}
}
