package org.cucumberpeople.eclipse.colortheme.mapper;

import java.util.Map;

import org.cucumberpeople.eclipse.colortheme.ColorThemeMapping;
import org.cucumberpeople.eclipse.colortheme.ColorThemeSetting;

public class TextEditorMapper extends GenericMapper {
    @Override
    public void map(Map<String, ColorThemeSetting> theme, Map<String, ColorThemeMapping> overrideMappings) {
        preferences.putBoolean("AbstractTextEditor.Color.Background.SystemDefault", false);
        preferences.putBoolean("AbstractTextEditor.Color.Foreground.SystemDefault", false);
        preferences.putBoolean("AbstractTextEditor.Color.SelectionBackground.SystemDefault", false);
        preferences.putBoolean("AbstractTextEditor.Color.SelectionForeground.SystemDefault", false);
        super.map(theme, overrideMappings);
    }
}
