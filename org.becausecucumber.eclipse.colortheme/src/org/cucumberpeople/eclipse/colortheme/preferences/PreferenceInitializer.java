package org.cucumberpeople.eclipse.colortheme.preferences;

import org.cucumberpeople.eclipse.colortheme.Activator;
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

/** Initialises this plugin's preferences. */
public class PreferenceInitializer extends AbstractPreferenceInitializer {
    @Override
    public void initializeDefaultPreferences() {
        IPreferenceStore store = Activator.getDefault().getPreferenceStore();
        store.setDefault("colorTheme", "Roboticket");
        store.setDefault("forceDefaultBG", false);
    }
}
