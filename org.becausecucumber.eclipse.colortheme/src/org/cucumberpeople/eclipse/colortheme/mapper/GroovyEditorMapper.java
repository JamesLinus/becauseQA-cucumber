package org.cucumberpeople.eclipse.colortheme.mapper;

import java.util.Map;

import org.cucumberpeople.eclipse.colortheme.ColorThemeMapping;
import org.cucumberpeople.eclipse.colortheme.ColorThemeSetting;

public class GroovyEditorMapper extends GenericMapper {
    @Override
    public void map(Map<String, ColorThemeSetting> theme, Map<String, ColorThemeMapping> overrideMappings) {
        preferences.putBoolean("groovy.editor.groovyDoc.tag.enabled", true);
        preferences.putBoolean("groovy.editor.groovyDoc.keyword.enabled", true);
        preferences.putBoolean("groovy.editor.groovyDoc.link.enabled", true);
        super.map(theme, overrideMappings);
    }
}
