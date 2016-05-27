package org.cucumberpeople.eclipse.colortheme.mapper;

import java.util.Map;

import org.cucumberpeople.eclipse.colortheme.ColorThemeMapping;
import org.cucumberpeople.eclipse.colortheme.ColorThemeSetting;

public class CEditorMapper extends GenericMapper {
    @Override
    public void map(Map<String, ColorThemeSetting> theme, Map<String, ColorThemeMapping> overrideMappings) {
        preferences.putBoolean("sourceHoverBackgroundColor.SystemDefault", false);
        super.map(theme, overrideMappings);
    }
}
