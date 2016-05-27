package org.cucumberpeople.eclipse.colortheme.mapper;

import java.util.Map;

import org.cucumberpeople.eclipse.colortheme.ColorThemeMapping;
import org.cucumberpeople.eclipse.colortheme.ColorThemeSetting;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;

public class PerlEditorMapper extends GenericMapper {
    private class Mapping extends ColorThemeMapping {
        public Mapping(String pluginKey, String themeKey) {
            super(pluginKey, themeKey);
        }

        @Override
        public void putPreferences(IEclipsePreferences preferences,
                ColorThemeSetting setting) {
            preferences.put(pluginKey, setting.getColor().asRGB());
            if (setting.isBoldEnabled() != null)
                preferences.putBoolean(pluginKey + "Bold", setting.isBoldEnabled());
        }
    }

    @Override
    protected ColorThemeMapping createMapping(String pluginKey, String themeKey) {
        return new Mapping(pluginKey, themeKey);
    }

    @Override
    public void map(Map<String, ColorThemeSetting> theme, Map<String, ColorThemeMapping> overrideMappings) {
        preferences.putBoolean("AbstractTextEditor.Color.Background.SystemDefault", false);
        preferences.putBoolean("AbstractTextEditor.Color.Foreground.SystemDefault", false);
        super.map(theme, overrideMappings);
    }
}
