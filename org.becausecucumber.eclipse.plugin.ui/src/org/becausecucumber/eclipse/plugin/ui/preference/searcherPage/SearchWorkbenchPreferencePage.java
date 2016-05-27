package org.becausecucumber.eclipse.plugin.ui.preference.searcherPage;

import java.util.ArrayList;
import java.util.List;

public class SearchWorkbenchPreferencePage extends PreferencePageWithSections {

	@Override
	protected List<PrefsPageSection> createSections() {
		List<PrefsPageSection> sections = new ArrayList<PrefsPageSection>();
		sections.add(new QuickSearchIgnoreSection(this));
		return sections;
	}

}