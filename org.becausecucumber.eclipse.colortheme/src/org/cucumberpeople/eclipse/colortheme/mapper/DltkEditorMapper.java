package org.cucumberpeople.eclipse.colortheme.mapper;

import java.util.Map;

import org.cucumberpeople.eclipse.colortheme.ColorThemeMapping;
import org.cucumberpeople.eclipse.colortheme.ColorThemeSetting;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;

public class DltkEditorMapper extends GenericMapper {
    private class Mapping extends ColorThemeMapping {
        public Mapping(String pluginKey, String themeKey) {
            super(pluginKey, themeKey);
        }

        @Override
        public void putPreferences(IEclipsePreferences preferences,
                ColorThemeSetting setting) {
            preferences.put(pluginKey, setting.getColor().asRGB());
            if (setting.isBoldEnabled() != null)
                preferences.putBoolean(pluginKey + "_bold", setting.isBoldEnabled());
            if (setting.isItalicEnabled() != null)
                preferences.putBoolean(pluginKey + "_italic", setting.isItalicEnabled());
            if (setting.isStrikethroughEnabled() != null)
                preferences.putBoolean(pluginKey + "_strikethrough", setting.isStrikethroughEnabled());
            if (setting.isUnderlineEnabled() != null)
                preferences.putBoolean(pluginKey + "_underline", setting.isUnderlineEnabled());
        }

    }

    @Override
    protected ColorThemeMapping createMapping(String pluginKey, String themeKey) {
        return new Mapping(pluginKey, themeKey);
    }

    @Override
    public void map(Map<String, ColorThemeSetting> theme, Map<String, ColorThemeMapping> overrideMappings) {
        preferences.putBoolean("sourceHoverBackgroundColor.SystemDefault", false);
        super.map(theme, overrideMappings);
    }
}
