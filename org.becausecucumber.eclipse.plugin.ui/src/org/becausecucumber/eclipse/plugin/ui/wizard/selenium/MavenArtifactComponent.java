package org.becausecucumber.eclipse.plugin.ui.wizard.selenium;

/*******************************************************************************
 * Copyright (c) 2008-2013 Sonatype, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *      Sonatype, Inc. - initial API and implementation
 *******************************************************************************/

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.apache.maven.model.Model;
import org.eclipse.m2e.core.ui.internal.Messages;
import org.eclipse.m2e.core.ui.internal.wizards.WidthGroup;

@SuppressWarnings("restriction")
public class MavenArtifactComponent extends Composite {

	public static final String JAR = "jar"; //$NON-NLS-1$

	public static final String WAR = "war"; //$NON-NLS-1$

	public static final String EAR = "ear"; //$NON-NLS-1$

	public static final String RAR = "rar"; //$NON-NLS-1$

	public static final String POM = "pom"; //$NON-NLS-1$

	// MNGECLIPSE-688 add EJB Support
	public static final String EJB = "ejb"; //$NON-NLS-1$

	public static final String[] PACKAGING_OPTIONS = { JAR, POM, WAR };

	public static final String[] SELENIUM_OPTIONS = { "RELEASE", "2.44.0", "2.43.0" };

	public static final String DEFAULT_PACKAGING = JAR;
	public static final String DEFAULT_SELENIUM = "RELEASE";

	public static final String DEFAULT_VERSION = "0.0.1-SNAPSHOT"; //$NON-NLS-1$

	/** group id text field */
	protected Combo groupIdCombo;

	/** artifact id text field */
	protected Combo artifactIdCombo;

	/** version text field */
	protected Combo versionCombo;

	/** packaging options combobox */
	protected Combo packagingCombo;

	/** name text field */
	protected Combo nameCombo;

	protected Combo seleniumCombo;

	/** description text field */
	protected Text descriptionText;

	private ModifyListener modifyingListener;

	private Label groupIdlabel;

	private Label artifactIdLabel;

	private Label versionLabel;

	private Label packagingLabel;

	private Label nameLabel;

	private Label descriptionLabel;

	private Label seleniumLabel;
	private Label lblNewLabel;
	private Label lblNewLabel_1;
	private Combo combobrowser;
	private Combo comboenv;

	/** Creates a new component. */
	public MavenArtifactComponent(Composite parent, int styles) {
		super(parent, styles);

		GridLayout layout = new GridLayout();
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		layout.numColumns = 2;
		setLayout(layout);

		Group artifactGroup = new Group(this, SWT.NONE);
		artifactGroup.setText(Messages.artifactComponentArtifact);
		GridData gd_artifactGroup = new GridData(SWT.FILL, SWT.FILL, true, true, 3, 1);
		gd_artifactGroup.heightHint = 388;
		artifactGroup.setLayoutData(gd_artifactGroup);
		artifactGroup.setLayout(new GridLayout(2, false));

		nameLabel = new Label(artifactGroup, SWT.NONE);
		nameLabel.setText("Name(Optional):");

		nameCombo = new Combo(artifactGroup, SWT.BORDER);
		nameCombo.setToolTipText("Give the current project's name,can be empty.");
		nameCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		nameCombo.setData("name", "nameCombo");

		groupIdlabel = new Label(artifactGroup, SWT.NONE);
		groupIdlabel.setText(Messages.artifactComponentGroupId);

		groupIdCombo = new Combo(artifactGroup, SWT.BORDER);
		groupIdCombo.setToolTipText("This is the Maven group id,which used for identifying your project from others.");
		groupIdCombo.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
		groupIdCombo.setData("name", "groupIdCombo"); //$NON-NLS-1$ //$NON-NLS-2$

		artifactIdLabel = new Label(artifactGroup, SWT.NONE);
		artifactIdLabel.setText(Messages.artifactComponentArtifactId);

		artifactIdCombo = new Combo(artifactGroup, SWT.BORDER);
		artifactIdCombo.setToolTipText("Actually it's the project name you want to create.");
		artifactIdCombo.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, false, false));
		artifactIdCombo.setData("name", "artifactIdCombo"); //$NON-NLS-1$ //$NON-NLS-2$

		versionLabel = new Label(artifactGroup, SWT.NONE);
		versionLabel.setText(Messages.artifactComponentVersion);

		versionCombo = new Combo(artifactGroup, SWT.BORDER);
		versionCombo.setItems(new String[] { "1.0.0", "2.0.0" });
		versionCombo.setLayoutData(new GridData(150, SWT.DEFAULT));
		versionCombo.setText(DEFAULT_VERSION);
		versionCombo.setData("name", "versionCombo"); //$NON-NLS-1$ //$NON-NLS-2$

		seleniumLabel = new Label(artifactGroup, SWT.NONE);
		seleniumLabel.setText("&Selenium Version : ");

		seleniumCombo = new Combo(artifactGroup, SWT.NONE);
		seleniumCombo.setItems(SELENIUM_OPTIONS);
		seleniumCombo.setText(DEFAULT_SELENIUM);
		seleniumCombo.setLayoutData(new GridData(150, SWT.DEFAULT));
		seleniumCombo.setData("name", "seleniumCombo");

		lblNewLabel = new Label(artifactGroup, SWT.NONE);
		lblNewLabel.setText("&Selenium Browser:");

		combobrowser = new Combo(artifactGroup, SWT.NONE);
		combobrowser.setItems(new String[] { "Firefox", "Chrome", "Internet Explorer", "Safari", "Opera" });
		combobrowser.setLayoutData(new GridData(150, SWT.DEFAULT));
		combobrowser.select(0);

		lblNewLabel_1 = new Label(artifactGroup, SWT.NONE);
		lblNewLabel_1.setText("&Run Environment:");

		comboenv = new Combo(artifactGroup, SWT.NONE);
		comboenv.setItems(new String[] { "QA3", "QA4", "QA5", "DEV_INT", "DEV_INT2", "Production" });
		comboenv.setLayoutData(new GridData(150, SWT.DEFAULT));
		comboenv.select(1);

		packagingLabel = new Label(artifactGroup, SWT.NONE);
		packagingLabel.setText(Messages.artifactComponentPackaging);

		packagingCombo = new Combo(artifactGroup, SWT.NONE);
		packagingCombo.setItems(PACKAGING_OPTIONS);
		packagingCombo.setText(DEFAULT_PACKAGING);
		packagingCombo.setLayoutData(new GridData(150, SWT.DEFAULT));
		packagingCombo.setData("name", "packagingCombo");

		descriptionLabel = new Label(artifactGroup, SWT.NONE);
		descriptionLabel.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, false));
		descriptionLabel.setText(Messages.artifactComponentDescription);

		descriptionText = new Text(artifactGroup, SWT.V_SCROLL | SWT.BORDER | SWT.WRAP);
		descriptionText.setToolTipText("Give some description for your project,then other can understand it well.");
		GridData gd_descriptionText = new GridData(SWT.FILL, SWT.FILL, false, true);
		gd_descriptionText.minimumHeight = 20;
		descriptionText.setLayoutData(gd_descriptionText);
		descriptionText.setData("name", "descriptionText");

	}

	public void setModifyingListener(ModifyListener modifyingListener) {
		this.modifyingListener = modifyingListener;

		groupIdCombo.addModifyListener(modifyingListener);
		artifactIdCombo.addModifyListener(modifyingListener);
		versionCombo.addModifyListener(modifyingListener);
		packagingCombo.addModifyListener(modifyingListener);
		seleniumCombo.addModifyListener(modifyingListener);

		combobrowser.addModifyListener(modifyingListener);
		comboenv.addModifyListener(modifyingListener);
	}

	public void dispose() {
		super.dispose();

		if (modifyingListener != null) {
			groupIdCombo.removeModifyListener(modifyingListener);
			artifactIdCombo.removeModifyListener(modifyingListener);
			versionCombo.removeModifyListener(modifyingListener);
			packagingCombo.removeModifyListener(modifyingListener);
			seleniumCombo.removeModifyListener(modifyingListener);
			combobrowser.removeModifyListener(modifyingListener);
			comboenv.removeModifyListener(modifyingListener);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.swt.widgets.Composite#setFocus()
	 */
	public boolean setFocus() {
		if (groupIdCombo != null) {
			return groupIdCombo.setFocus();
		}
		return super.setFocus();
	}

	public String getModelName() {
		return nameCombo.getText();
	}

	public String getArtifactId() {
		return this.artifactIdCombo.getText();
	}

	public String getGroupId() {
		return this.groupIdCombo.getText();
	}

	public String getVersion() {
		return this.versionCombo.getText();
	}

	public String getPackaging() {
		return packagingCombo.getText();
	}

	public String getSelenium() {
		return seleniumCombo.getText();
	}

	public String getBrowser() {
		return combobrowser.getText();
	}

	public String getEnvironment() {
		return comboenv.getText();
	}

	public String getDescription() {
		return descriptionText.getText();
	}

	public void setModelName(String name) {
		nameCombo.setText(name);
	}

	public void setGroupId(String groupId) {
		this.groupIdCombo.setText(groupId);
	}

	public void setArtifactId(String artifact) {
		this.artifactIdCombo.setText(artifact);
	}

	public void setVersion(String version) {
		versionCombo.setText(version);
	}

	public void setPackagingTypes(String[] packagingTypes) {
		if (packagingCombo != null) {
			packagingCombo.setItems(packagingTypes);
		}
	}

	public void setPackaging(String packaging) {
		if (packagingCombo != null) {
			packagingCombo.setText(packaging);
		}
	}

	public void setDescription(String description) {
		if (descriptionText != null) {
			descriptionText.setText(description);
		}
	}

	public Model getModel() {
		Model model = new Model();
		model.setModelVersion("4.0.0"); //$NON-NLS-1$

		model.setGroupId(getGroupId());
		model.setArtifactId(getArtifactId());
		model.setVersion(getVersion());
		model.setPackaging(getPackaging());

		if (getModelName().length() > 0) {
			model.setName(getModelName());
		}
		if (getDescription().length() > 0) {
			model.setDescription(getDescription());
		}
		return model;
	}

	/** Enables or disables the artifact id text field. */
	public void setArtifactIdEditable(boolean b) {
		artifactIdCombo.setEnabled(b);
	}

	public Combo getGroupIdCombo() {
		return groupIdCombo;
	}

	public Combo getArtifactIdCombo() {
		return artifactIdCombo;
	}

	public Combo getVersionCombo() {
		return versionCombo;
	}

	public Combo getNameCombo() {
		return nameCombo;
	}

	public Combo getSeleniumCombo() {
		return seleniumCombo;
	}

	public void setWidthGroup(WidthGroup widthGroup) {
		widthGroup.addControl(this.groupIdlabel);
		widthGroup.addControl(this.artifactIdLabel);
		widthGroup.addControl(this.versionLabel);
		widthGroup.addControl(this.packagingLabel);
		widthGroup.addControl(this.nameLabel);
		widthGroup.addControl(this.descriptionLabel);
		widthGroup.addControl(this.seleniumCombo);
		widthGroup.addControl(this.combobrowser);
		widthGroup.addControl(this.comboenv);
	}

}
