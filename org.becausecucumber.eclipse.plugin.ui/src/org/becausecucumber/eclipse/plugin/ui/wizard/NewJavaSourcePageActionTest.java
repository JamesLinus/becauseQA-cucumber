package org.becausecucumber.eclipse.plugin.ui.wizard;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.internal.corext.codemanipulation.StubUtility;
import org.eclipse.jdt.internal.corext.template.java.CodeTemplateContextType;
import org.eclipse.jdt.ui.wizards.NewClassWizardPage;

public class NewJavaSourcePageActionTest {

	@SuppressWarnings("unchecked")
	public void createNewClassWizard() {
		String newFileTemplate = "${filecomment}\n${package_declaration}\n\nimport java.util.Map;\n\n${typecomment}\n${type_declaration}";
		StubUtility.setCodeTemplate(CodeTemplateContextType.NEWTYPE_ID, newFileTemplate, null);
		// IPackageFragment
		// pack1=fSourceFolder.createPackageFragment("test1",false,null);
		NewClassWizardPage wizardPage = new NewClassWizardPage();
		// wizardPage.setPackageFragmentRoot(fSourceFolder,true);
		// wizardPage.setPackageFragment(pack1,true);
		wizardPage.setEnclosingTypeSelection(false, true);
		wizardPage.setTypeName("E", true);
		wizardPage.setSuperClass("", true);
		@SuppressWarnings("rawtypes")
		List interfaces = new ArrayList();
		interfaces.add("java.util.List<java.io.File>");
		wizardPage.setSuperInterfaces(interfaces, true);
		wizardPage.setMethodStubSelection(false, false, false, true);
		wizardPage.setAddComments(true, true);
		wizardPage.enableCommentControl(true);
		// wizardPage.createType(null);
		// String
		// actual=wizardPage.getCreatedType().getCompilationUnit().getSource();
		StringBuffer buf = new StringBuffer();
		buf.append("/**\n");
		buf.append(" * File\n");
		buf.append(" */\n");
		buf.append("package test1;\n");
		buf.append("\n");
		buf.append("import java.io.File;\n");
		buf.append("import java.util.List;\n");
		buf.append("import java.util.Map;\n");
		buf.append("\n");
		buf.append("/**\n");
		buf.append(" * Type\n");
		buf.append(" */\n");
		buf.append("public class E implements List<File> {\n");
		buf.append("    /* class body */\n");
		buf.append("}\n");
		@SuppressWarnings("unused")
		String expected = buf.toString();
	}

}
