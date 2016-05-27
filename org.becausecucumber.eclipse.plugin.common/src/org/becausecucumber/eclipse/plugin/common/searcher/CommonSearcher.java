package org.becausecucumber.eclipse.plugin.common.searcher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.becausecucumber.eclipse.plugin.common.CommonPluginUtils;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.ILazyContentProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.progress.UIJob;



public class CommonSearcher {

	public QuickTextSearcher searcher;
	public QuickSearchContext context;
	
	public ContentProvider contentProvider;
	public QuickTextQuery newFilter;
	
	public CommonSearcher(){
		IWorkbenchWindow window = CommonPluginUtils.getInstance().getActiveWorkbenchWindow();
		context = new QuickSearchContext(window);		
		contentProvider = new ContentProvider();
	}
	
	public List<IFile> getStepFiles(){
		
		return searcher.getStepfile();
		
	}
	public QuickTextQuery getQuickTextQuery(){
		return this.newFilter;
	}
	@SuppressWarnings("unchecked")
	public List<LineItem> validateJob(final String searchContent){
		
	    
		 newFilter=new QuickTextQuery(searchContent, true);
		
    
		if (this.searcher==null) {
			if (!newFilter.isTrivial()) {
				//Create the QuickTextSearcher with the inital query.
				this.searcher = new QuickTextSearcher(newFilter, context.createPriorityFun(), new QuickTextSearchRequestor(){
					 @Override
						public void add(LineItem match) {
						 //add this for regexp to step
						/* String parseStep=searchContent+"\\$";	
						 if(Pattern.compile(parseStep,Pattern.CASE_INSENSITIVE).matcher(match.getText()).find()){
						*/		contentProvider.add(match);								
							    contentProvider.refresh();
						 //  }
						  // System.out.println("Add function:"+searchContent+",search file conten is:"+match.getText());
						    
						}
		        	  
						@Override
						public void clear() {
						
							contentProvider.reset();
							contentProvider.refresh();
						}
						@Override
						public void revoke(LineItem match) {
							 contentProvider.remove(match);
							 contentProvider.refresh();
						}
						@Override
						public void update(LineItem match) {
							contentProvider.refresh();
						}
				});
				
				refresh();				
				
			}
			
		} else {
			//The QuickTextSearcher is already active update the query
			this.searcher.setQuery(newFilter);
		}
		if (progressJob!=null) {
			progressJob.schedule();
		}
		
		int looptime=0;
		while(!(this.searcher.isDone())){
			looptime++;
			System.out.println(searchContent+" --loop time:"+looptime+",status is:"+this.searcher.isDone());
		}
		System.out.println("Search Completed,Size is:"+contentProvider.items.size());
		//System.out.println(searchContent+"---"+contentProvider.items.size()+" ----wait or the search job complete loop time:"+looptime+",status is:"+this.searcher.isDone());
		return contentProvider.items;
	}
	
	public boolean isDone(){
		return this.searcher.isDone();
	}
	   /**
	 * Job that shows a simple busy indicator while a search is active.
	 * The job must be scheduled when a search starts/resumes. It periodically checks the
	 */
	private UIJob progressJob =  new UIJob("Refresh") {
		int animate = 0; // number of dots to display.
		
		@Override
		public IStatus runInUIThread(IProgressMonitor mon) {
			if (!mon.isCanceled()) {
				if (searcher==null || searcher.isDone()) {
					//progressLabel.setText("");
				} else {
					//progressLabel.setText("Searching"+dots(animate));
					animate = (animate+1)%4;
					this.schedule(1);
				}
			}
			return Status.OK_STATUS;
		}
	};
	
	private UIJob refreshJob = new UIJob("Refresh") {
		@Override
		public IStatus runInUIThread(IProgressMonitor monitor) {
			refresh();
			return Status.OK_STATUS;
		}
	};
	
	public void refresh() {
		contentProvider.items.iterator();
		/*if (list != null && !list.getTable().isDisposed()) {
			ScrollBar sb = list.getTable().getVerticalBar();
			int oldScroll = sb.getSelection();
			int itemCount = contentProvider.getNumberOfElements();
			list.setItemCount(itemCount);
			list.refresh(true, false);
			int newScroll = sb.getSelection();
			if (oldScroll!=newScroll) {
				System.out.println("Scroll moved in refresh: "+oldScroll+ " => " + newScroll);
			}*/
			//sb.setSelection((int) Math.floor(oldScroll*sb.getMaximum()));
		}
	/**
	 * Schedule refresh job.
	 */
	public void scheduleRefresh() {
		refreshJob.schedule();
//		list.re
//		refreshCacheJob.cancelAll();
//		refreshCacheJob.schedule();
	}
	
	/**
	 * Collects filtered elements. Contains one synchronized, sorted set for
	 * collecting filtered elements. 
	 * Implementation of <code>ItemsFilter</code> is used to filter elements.
	 * The key function of filter used in to filtering is
	 * <code>matchElement(Object item)</code>.
	 * <p>
	 * The <code>ContentProvider</code> class also provides item filtering
	 * methods. The filtering has been moved from the standard TableView
	 * <code>getFilteredItems()</code> method to content provider, because
	 * <code>ILazyContentProvider</code> and virtual tables are used. This
	 * class is responsible for adding a separator below history items and
	 * marking each items as duplicate if its name repeats more than once on the
	 * filtered list.
	 */
	private class ContentProvider implements IStructuredContentProvider,ILazyContentProvider {

		@SuppressWarnings("rawtypes")
		private List items;

		/**
		 * Creates new instance of <code>ContentProvider</code>.
		 */
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public ContentProvider() {
			this.items = Collections.synchronizedList(new ArrayList(2048));
//			this.duplicates = Collections.synchronizedSet(new HashSet(256));
//			this.lastSortedItems = Collections.synchronizedList(new ArrayList(
//					2048));
		}

		public void remove(LineItem match) {
			this.items.remove(match);
		}

		/**
		 * Removes all content items and resets progress message.
		 */
		public void reset() {
			this.items.clear();
		}

		/**
		 * Adds filtered item.
		 * 
		 * @param match
		 */
		@SuppressWarnings("unchecked")
		public void add(LineItem match) {
			this.items.add(match);
		}

		/**
		 * Refresh dialog.
		 */
		public void refresh() {
			scheduleRefresh();
		}

//		/**
//		 * Removes items from history and refreshes the view.
//		 * 
//		 * @param item
//		 *           to remove
//		 * 
//		 * @return removed item
//		 */
//		public Object removeHistoryElement(Object item) {
//			if (this.selectionHistory != null)
//				this.selectionHistory.remove(item);
//			if (filter == null || filter.getPattern().length() == 0) {
//				items.remove(item);
//				duplicates.remove(item);
//				this.lastSortedItems.remove(item);
//			}
//
//			synchronized (lastSortedItems) {
//				Collections.sort(lastSortedItems, getHistoryComparator());
//			}
//			return item;
//		}

//		/**
//		 * Adds item to history and refresh view.
//		 * 
//		 * @param item
//		 *           to add
//		 */
//		public void addHistoryElement(Object item) {
//			if (this.selectionHistory != null)
//				this.selectionHistory.accessed(item);
//			if (filter == null || !filter.matchItem(item)) {
//				this.items.remove(item);
//				this.duplicates.remove(item);
//				this.lastSortedItems.remove(item);
//			}
//			synchronized (lastSortedItems) {
//				Collections.sort(lastSortedItems, getHistoryComparator());
//			}
//			this.refresh();
//		}

//		/**
//		 * Sets/unsets given item as duplicate.
//		 * 
//		 * @param item
//		 *           item to change
//		 * 
//		 * @param isDuplicate
//		 *           duplicate flag
//		 */
//		public void setDuplicateElement(Object item, boolean isDuplicate) {
//			if (this.items.contains(item)) {
//				if (isDuplicate)
//					this.duplicates.add(item);
//				else
//					this.duplicates.remove(item);
//			}
//		}

//		/**
//		 * Indicates whether given item is a duplicate.
//		 * 
//		 * @param item
//		 *           item to check
//		 * @return <code>true</code> if item is duplicate
//		 */
//		public boolean isDuplicateElement(Object item) {
//			return duplicates.contains(item);
//		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
		 */
		public Object[] getElements(Object inputElement) {
			return items.toArray();
		}

		@SuppressWarnings("unused")
		public int getNumberOfElements() {
			return items.size();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
		 */
		public void dispose() {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer,
		 *     java.lang.Object, java.lang.Object)
		 */
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jface.viewers.ILazyContentProvider#updateElement(int)
		 */
		public void updateElement(int index) {
/*
			QuickSearchDialog.this.list.replace((items
					.size() > index) ? items.get(index) : null,
					index);*/

		}

	}
	
}
