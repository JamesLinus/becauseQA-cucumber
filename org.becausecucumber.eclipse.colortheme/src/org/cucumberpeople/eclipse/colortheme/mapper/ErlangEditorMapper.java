package org.cucumberpeople.eclipse.colortheme.mapper;

import org.cucumberpeople.eclipse.colortheme.ColorThemeMapping;
import org.cucumberpeople.eclipse.colortheme.ColorThemeSetting;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.osgi.service.prefs.Preferences;

public class ErlangEditorMapper extends GenericMapper {
    private class Mapping extends ColorThemeMapping {
        public Mapping(String pluginKey, String themeKey) {
            super(pluginKey, themeKey);
        }

        @Override
        public void putPreferences(IEclipsePreferences preferences, ColorThemeSetting setting) {
            Preferences pref = preferences.parent().node("org.erlide.ui/" + pluginKey);
            int styleVal = 0;
            if (setting.isBoldEnabled() != null && setting.isBoldEnabled()) {
                styleVal += 1;
            }
            if (setting.isItalicEnabled() != null && setting.isItalicEnabled()) {
                styleVal += 2;
            }
            if (setting.isStrikethroughEnabled() != null && setting.isStrikethroughEnabled()) {
                styleVal += 536870912;
            }
            if (setting.isUnderlineEnabled() != null && setting.isUnderlineEnabled()) {
                styleVal += 1073741824;
            }
            pref.put("color", setting.getColor().asRGB());
            pref.putInt("style", styleVal);
        }
    }

    @Override
    protected ColorThemeMapping createMapping(String pluginKey, String themeKey) {
        return new Mapping(pluginKey, themeKey);
    }
}
