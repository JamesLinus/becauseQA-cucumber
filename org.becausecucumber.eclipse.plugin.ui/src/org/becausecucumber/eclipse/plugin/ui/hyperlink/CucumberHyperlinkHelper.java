package org.becausecucumber.eclipse.plugin.ui.hyperlink;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.becausecucumber.eclipse.plugin.common.searcher.CucumberHyperlink;
import org.becausecucumber.eclipse.plugin.common.searcher.PerformSearcher;
import org.becausecucumber.eclipse.plugin.common.searcher.PerformSearcher.Entry;
import org.becausecucumber.eclipse.plugin.cucumber.Step;
import org.becausecucumber.eclipse.plugin.ui.CucumberPeopleActivator;
import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.hyperlink.IHyperlink;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.impl.CompositeNode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.parser.IParseResult;
import org.eclipse.xtext.resource.EObjectAtOffsetHelper;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.hyperlinking.HyperlinkHelper;
import org.eclipse.xtext.ui.editor.hyperlinking.HyperlinkLabelProvider;
import org.eclipse.xtext.ui.editor.hyperlinking.IHyperlinkAcceptor;
import org.eclipse.xtext.ui.editor.hyperlinking.XtextHyperlink;
import org.eclipse.xtext.util.ITextRegion;
import org.eclipse.xtext.util.TextRegion;
import org.eclipse.emf.common.util.URI;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class CucumberHyperlinkHelper extends HyperlinkHelper {

	@Inject
	@HyperlinkLabelProvider
	private ILabelProvider labelProvider;

	@Inject
	private Provider<XtextHyperlink> hyperlinkProvider;
	@Inject
	private EObjectAtOffsetHelper eObjectAtOffsetHelper;

	@Inject
	private PerformSearcher matcher;

	protected Provider<XtextHyperlink> getHyperlinkProvider() {
		return hyperlinkProvider;
	}

	protected ILabelProvider getLabelProvider() {
		return labelProvider;
	}

	protected static class HyperlinkAcceptor implements IHyperlinkAcceptor {

		private final List<IHyperlink> links;

		public HyperlinkAcceptor(List<IHyperlink> links) {
			this.links = links;
		}

		public void accept(IHyperlink hyperlink) {
			if (hyperlink != null)
				links.add(hyperlink);
		}

	}

	/*
	 * @Override public IHyperlink[] createHyperlinksByOffset(XtextResource
	 * resource, int offset, boolean createMultipleHyperlinks) {
	 * List<IHyperlink> links = Lists.newArrayList(); IHyperlinkAcceptor
	 * acceptor = new HyperlinkAcceptor(links);
	 * 
	 * createHyperlinksByOffset(resource, offset, acceptor); if
	 * (!links.isEmpty()) return Iterables.toArray(links, IHyperlink.class);
	 * return null; }
	 */

	public void createHyperlinksByOffset_new(XtextResource resource, int offset, IHyperlinkAcceptor acceptor) {
		INode crossRefNode = eObjectAtOffsetHelper.getCrossReferenceNode(resource, new TextRegion(offset, 0));
		if (crossRefNode == null)
			// System.o
			return;
		EObject crossLinkedEObject = eObjectAtOffsetHelper.getCrossReferencedElement(crossRefNode);
		if (crossLinkedEObject != null && !crossLinkedEObject.eIsProxy()) {
			createHyperlinksTo(resource, crossRefNode, crossLinkedEObject, acceptor);
		}
	}

	/**
	 * Produces hyperlinks for the given {@code node} which is associated with a
	 * cross reference that points to the referenced {@code target}.
	 * 
	 * @see #createHyperlinksTo(XtextResource, Region, EObject,
	 *      IHyperlinkAcceptor)
	 * 
	 * @since 2.4
	 */
	protected void createHyperlinksTo(XtextResource resource, INode node, EObject target, IHyperlinkAcceptor acceptor) {
		ITextRegion textRegion = node.getTextRegion();
		Region region = new Region(textRegion.getOffset(), textRegion.getLength());
		createHyperlinksTo(resource, node, region, target, acceptor);
	}

	/**
	 * Produces hyperlinks for the given {@code region} that point to the
	 * referenced {@code target}.
	 */
	public void createHyperlinksTo(XtextResource from, INode node, Region region, EObject target,
			IHyperlinkAcceptor acceptor) {
		final URIConverter uriConverter = from.getResourceSet().getURIConverter();
		final String hyperlinkText = "Go to step file for this step:" + labelProvider.getText(target);
		final URI uri = EcoreUtil.getURI(target);
		final URI normalized = uri.isPlatformResource() ? uri : uriConverter.normalize(uri);

		Step step = (Step) target;
		String description = step.getDescription().trim();
		/*
		 * XtextHyperlink result = hyperlinkProvider.get();
		 * result.setHyperlinkRegion(region); result.setURI(normalized);
		 * result.setHyperlinkText(hyperlinkText); acceptor.accept(result);
		 */
		// node.getText().trim().length();

		if (description != null && description != "") {

			// String
			// newstep=CommonCucumberFeatureUtils.step2Cucumber(description)+"$";
			Collection<Entry> findMatchFiles = matcher.findMatchFiles(description);
			if (findMatchFiles.size() == 1) {
				Entry item = findMatchFiles.iterator().next();
				IFile file = item.getFile();
				int offset = item.getOffset();
				int length = item.getLength();
				CucumberHyperlink hyperlink = new CucumberHyperlink(node,
						CucumberPeopleActivator.getInstance().getWorkbench().getActiveWorkbenchWindow(), file, offset,
						length);
				hyperlink.setHyperlinkRegion(region);
				hyperlink.setHyperlinkText(hyperlinkText);
				hyperlink.setURI(normalized);
				acceptor.accept(hyperlink);
				/*
				 * LineItem item = searchlist.get(0); QuickTextQuery query =
				 * searcher.getQuickTextQuery(); TextRange findFirst =
				 * query.findFirst(newstep); acceptor.accept(new
				 * CucumberHyperlink("Go to Step File:"
				 * +item.getFile().getName(), item,findFirst,
				 * GDNCucumberActivator.getInstance().getWorkbench().
				 * getActiveWorkbenchWindow()));
				 */

			}
		}
	}

	/*
	 * (Not Javadoc) this is what we used for testing <p>Title:
	 * createHyperlinksByOffset</p> <p>Description: </p>
	 * 
	 * @param resource
	 * 
	 * @param offset
	 * 
	 * @param acceptor
	 * 
	 * @see org.eclipse.xtext.ui.editor.hyperlinking.HyperlinkHelper#
	 * createHyperlinksByOffset(org.eclipse.xtext.resource.XtextResource, int,
	 * org.eclipse.xtext.ui.editor.hyperlinking.IHyperlinkAcceptor)
	 */
	@Override
	public void createHyperlinksByOffset(XtextResource resource, int offset, IHyperlinkAcceptor acceptor) {
		// TODO Auto-generated method stub
		IParseResult parseResult = resource.getParseResult();

		if (parseResult != null && parseResult.getRootNode() != null) {

			INode node = NodeModelUtils.findLeafNodeAtOffset(resource.getParseResult().getRootNode(), offset);
			if (!(node != null && node instanceof CompositeNode && node.getSemanticElement() instanceof Step)) {
				node = node.getParent();
			}
			ITextRegion textRegion = node.getTextRegion();
			Region region = new Region(textRegion.getOffset(), textRegion.getLength());

			Step step = (Step) node.getSemanticElement();
			String description = step.getDescription().trim();

			final URIConverter uriConverter = resource.getResourceSet().getURIConverter();
			final URI uri = EcoreUtil.getURI(step);
			final URI normalized = uri.isPlatformResource() ? uri : uriConverter.normalize(uri);

			/*
			 * JdtHyperlink result = hyperlinkProvider.get();
			 * 
			 * result.setTypeLabel("Navigate to generated step code.");
			 * result.setHyperlinkText();
			 */

			if (description != null && description != "") {
				final CucumberCounter counter = new CucumberCounter();
				matcher.matcher(description, counter);
				int size = counter.getNumbers();
				for (int k = 0; k < size; k++) {
					Entry item = counter.getFiles().get(k);
					IFile file = item.getFile();
					int sizeoffset = item.getOffset();
					int length = item.getLength();
					CucumberHyperlink hyperlink = new CucumberHyperlink(node,
							CucumberPeopleActivator.getInstance().getWorkbench().getActiveWorkbenchWindow(), file,
							sizeoffset, length);
					hyperlink.setHyperlinkRegion(region);
					hyperlink.setTypeLabel("Navigate to Step defination : " + description);
					hyperlink.setHyperlinkText("Go to step defination : " + description);
					hyperlink.setURI(normalized);
					acceptor.accept(hyperlink);
					/*
					 * LineItem item = searchlist.get(0); QuickTextQuery query =
					 * searcher.getQuickTextQuery(); TextRange findFirst =
					 * query.findFirst(newstep); acceptor.accept(new
					 * CucumberHyperlink("Go to Step File:"
					 * +item.getFile().getName(), item,findFirst,
					 * GDNCucumberActivator.getInstance().getWorkbench().
					 * getActiveWorkbenchWindow()));
					 */

				} /*
					 * else{ Log.error("Navigate to the step \""+description+
					 * "\" met exception, step defination size found in current workspace is: "
					 * +size); }
					 */
			}

			super.createHyperlinksByOffset(resource, offset, acceptor);
		}
	} /*
		 * @Override public IHyperlink[] createHyperlinksByOffset(XtextResource
		 * resource, int offset, boolean createMultipleHyperlinks) {
		 * IHyperlink[] defaults = super.createHyperlinksByOffset(resource,
		 * offset, createMultipleHyperlinks); List<IHyperlink> hyperlinks =
		 * (defaults == null ? new ArrayList<IHyperlink>() :
		 * Arrays.asList(defaults));
		 * 
		 * EObject eObject = helper.resolveElementAt(resource, offset); if
		 * (eObject instanceof Step) { IParseResult parseResult =
		 * resource.getParseResult(); INode node =
		 * NodeModelUtils.findLeafNodeAtOffset(parseResult.getRootNode(),
		 * offset); while (!(node instanceof CompositeNode &&
		 * node.getSemanticElement() instanceof Step)) { node =
		 * node.getParent(); } String description = ((Step)
		 * eObject).getDescription().trim();
		 * hyperlinks.addAll(findLinkTargets(description, new
		 * Region(node.getOffset(), node.getText().trim().length()))); } return
		 * hyperlinks.isEmpty() ? null : Iterables.toArray(hyperlinks,
		 * IHyperlink.class); }
		 */
	/*
	 * private Collection<? extends IHyperlink> findLinkTargets(String
	 * description, final Region region) { final List<IHyperlink> results = new
	 * ArrayList<IHyperlink>();
	 * 
	 * CommonSearcher searcher=new CommonSearcher(); List<LineItem>
	 * searchlist=null; if(description!=null&&description!=""){ searchlist =
	 * searcher.validateJob(description); } if(searchlist.size()==1){
	 * results.add(new JavaHyperlink("Open definition for step: "+description ,
	 * null, region)); } matcher.findMatches(description.trim(), new
	 * JavaAnnotationMatcher.Command() {
	 * 
	 * public void match(String annotationValue, IMethod method) {
	 * results.add(new JavaHyperlink("Open definition " + annotationValue,
	 * method, region));
	 * 
	 * } }); return results; }
	 */

	private final static class CucumberCounter implements PerformSearcher.MatcherCollector {

		private int count = 0;
		private List<Entry> files = new ArrayList<Entry>();

		public int getNumbers() {
			return count;
		}

		public List<Entry> getFiles() {
			return files;
		}

		@Override
		public void foundMatcher(Entry entry) {
			// TODO Auto-generated method stub
			count++;
			files.add(entry);
		}

	}

}
