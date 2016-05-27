package org.becausecucumber.eclipse.plugin.common.searcher;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.search.internal.ui.text.EditorOpener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.ui.editor.hyperlinking.XtextHyperlink;

@SuppressWarnings("restriction")
public class CucumberHyperlink extends XtextHyperlink{

	public IWorkbenchWindow window;
	public IFile file;
	public int offset;
	public int length;
	public INode node;
	
	
	public CucumberHyperlink(INode node,IWorkbenchWindow window,IFile file,int offset,int length) {
		// TODO Auto-generated constructor stub
		this.window=window;
		this.node=node;
		this.offset=offset;
		this.length=length;
		this.file=file;
	}
	@Override
	public void open() {
		// TODO Auto-generated method stub
		//super.open();
		EditorOpener opener = new EditorOpener();
		IWorkbenchPage page = window.getActivePage();
		if (page!=null) {
			try {
				opener.openAndSelect(page,file,offset,length, true);
			} catch (PartInitException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public URI getURI() {
		// TODO Auto-generated method stub
		return super.getURI();
	}

	@Override
	public void setURI(URI uri) {
		// TODO Auto-generated method stub
		super.setURI(uri);
	}

	
	/*public TextRange range;
	public String text;
	public IWorkbenchWindow window;
	public LineItem item;
	public CucumberHyperlink(String text,LineItem item,TextRange range,IWorkbenchWindow window) {
		// TODO Auto-generated constructor stub
		this.text=text;
		this.window=window;
		this.item=item;
		this.range=range;
	}
	
	@Override
	public IRegion getHyperlinkRegion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTypeLabel() {
		// TODO Auto-generated method stub
		return "Navigate to generated step code.";
	}

	@Override
	public String getHyperlinkText() {
		// TODO Auto-generated method stub
		return text;
	}

	@SuppressWarnings("restriction")
	@Override
	public void open() {
		// TODO Auto-generated method stub
		EditorOpener opener = new EditorOpener();
		IWorkbenchPage page = window.getActivePage();
		if (page!=null) {
			try {
				opener.openAndSelect(page,item.getFile(), range.getOffset()+item.getOffset(), 
						range.getLength(), true);
			} catch (PartInitException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}*/

}
