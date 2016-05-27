package org.cucumberpeople.eclipse.colortheme.mapper;

import org.cucumberpeople.eclipse.colortheme.ColorThemeMapping;
import org.cucumberpeople.eclipse.colortheme.ColorThemeSetting;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;

public class PythonEditorMapper extends GenericMapper {
    private class Mapping extends ColorThemeMapping {
        public Mapping(String pluginKey, String themeKey) {
            super(pluginKey, themeKey);
        }

        @Override
        public void putPreferences(IEclipsePreferences preferences, ColorThemeSetting setting) {
            preferences.put(pluginKey, setting.getColor().asRGB());
            String styleKey = pluginKey.replaceAll("COLOR", "STYLE");
            int styleVal = 0;
            if (setting.isBoldEnabled() != null && setting.isBoldEnabled()) {
                styleVal += 1;
            }
            if (setting.isItalicEnabled() != null && setting.isItalicEnabled()) {
                styleVal += 2;
            }
            preferences.putInt(styleKey, styleVal);
        }
    }

    @Override
    protected ColorThemeMapping createMapping(String pluginKey, String themeKey) {
        return new Mapping(pluginKey, themeKey);
    }
}
