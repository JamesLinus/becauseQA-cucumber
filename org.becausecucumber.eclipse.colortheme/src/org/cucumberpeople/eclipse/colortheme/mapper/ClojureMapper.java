package org.cucumberpeople.eclipse.colortheme.mapper;

import org.cucumberpeople.eclipse.colortheme.ColorThemeMapping;
import org.cucumberpeople.eclipse.colortheme.ColorThemeSetting;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;

/**
 * Eclipse Color Theme Mapper for Clojure/CCW
 * @author Caleb Peterson (caleb.m.peterson@gmail.com)
 */
public class ClojureMapper extends GenericMapper {
    private class Mapping extends ColorThemeMapping {
        public Mapping(String pluginKey, String themeKey) {
            super(pluginKey, themeKey);
        }

        @Override
        public void putPreferences(IEclipsePreferences preferences,
                ColorThemeSetting setting) {
            // Set the color itself
            super.putPreferences(preferences, setting);
            // Enable styling
            preferences.putBoolean(pluginKey + ".enabled", true);
            // Bold?
            preferences.putBoolean(pluginKey + ".bold", setting.isBoldEnabled() != null && setting.isBoldEnabled());
            // Italics?
            preferences.putBoolean(pluginKey + ".italic", setting.isItalicEnabled() != null && setting.isItalicEnabled());
        }
    }

    @Override
    protected ColorThemeMapping createMapping(String pluginKey, String themeKey) {
        return new Mapping(pluginKey, themeKey);
    }
}
