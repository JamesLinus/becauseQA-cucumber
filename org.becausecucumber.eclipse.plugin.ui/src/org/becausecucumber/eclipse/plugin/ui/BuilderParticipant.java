package org.becausecucumber.eclipse.plugin.ui;

import org.becausecucumber.eclipse.plugin.common.searcher.PerformSearcher;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

import com.google.inject.Inject;

@SuppressWarnings("restriction")
public class BuilderParticipant extends org.eclipse.xtext.builder.BuilderParticipant {

	@SuppressWarnings("unused")
	@Inject
	private PerformSearcher matcher;

	public void build(final IBuildContext context, IProgressMonitor monitor) throws CoreException {
		super.build(context, monitor);
		/*
		 * if (!context.getResourceSet().getResources().isEmpty()) { //TODO
		 * iterate over the resources to evict only those that have changed
		 * matcher.evict(null); }
		 */
	}
}
