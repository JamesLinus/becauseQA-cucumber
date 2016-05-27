package org.becausecucumber.eclipse.plugin.ui.view;

import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.ControlContribution;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.console.IConsoleConstants;

@SuppressWarnings("restriction")
public class CucumberPerspectiveFactory implements IPerspectiveFactory {

	@Override
	public void createInitialLayout(IPageLayout layout) {
		// TODO Auto-generated method stub
		layout.setEditorAreaVisible(true);

		layout.createFolder("left", IPageLayout.LEFT, 0.2f, IPageLayout.ID_EDITOR_AREA);
		layout.createFolder("right", IPageLayout.RIGHT, 0.6f, IPageLayout.ID_EDITOR_AREA);
		layout.createFolder("bottom", IPageLayout.BOTTOM, 0.7f, IPageLayout.ID_EDITOR_AREA);

	}

	/*
	 * @see http://blog.csdn.net/by84788186/article/details/6332299
	 */
	public void removeToolbar() {
		IWorkbenchPage page = PlatformUI.getWorkbench().getWorkbenchWindows()[0].getPages()[0];
		IViewPart viewpart = page.findView(IConsoleConstants.ID_CONSOLE_VIEW);
		IActionBars actionBar = viewpart.getViewSite().getActionBars();
		IToolBarManager toolbarMgr = actionBar.getToolBarManager();
		IContributionItem[] items = toolbarMgr.getItems();
		for (IContributionItem item : items) {
			if (item instanceof ActionContributionItem) {
				IAction action = ((ActionContributionItem) item).getAction();
				String text = action.getText();
				if (text.equals("Open Console") || text.equals("Select Console")) {
					toolbarMgr.remove(item);
				}
			}
		}

		// actionBar.updateActionBars();
		IContributionItem comboCI = new ControlContribution("test") {
			protected Control createControl(Composite parent) {

				final Combo c = new Combo(parent, SWT.READ_ONLY);
				c.add("one");
				c.add("two");
				c.add("three");
				c.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e) {
						c.add("four");
					}
				});
				return c;
			}
		};

		toolbarMgr.add(comboCI);
		viewpart.getViewSite().getActionBars().updateActionBars();
	}
}
