package org.cucumberpeople.eclipse.colortheme.mapper;

import org.cucumberpeople.eclipse.colortheme.ColorThemeMapping;
import org.cucumberpeople.eclipse.colortheme.ColorThemeSetting;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;

/**
 * Maps color themes to preferences for Eclipse's SQL editor. Based on Data
 * Tools Platform 1.8.1
 */
public class SqlEditorMapper extends GenericMapper {
    @Override
    protected ColorThemeMapping createMapping(String pluginKey, String themeKey) {
        return new Mapping(pluginKey, themeKey);
    }

    private class Mapping extends ColorThemeMapping {
        public Mapping(String pluginKey, String themeKey) {
            super(pluginKey, themeKey);
        }

        @Override
        public void putPreferences(IEclipsePreferences preferences,
                ColorThemeSetting setting) {
            // TODO: Add support for bold, italic, etc.
            String value = "0,0,0,0,0," + setting.getColor().asRGB();
            preferences.put(pluginKey, value);
        }
    }
}
