package org.becausecucumber.eclipse.plugin.ui.dialog;

import org.becausecucumber.eclipse.plugin.common.CommonCucumberFeatureUtils;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.ui.dialogs.ContainerSelectionDialog;
import org.eclipse.wb.swt.ResourceManager;

@SuppressWarnings("restriction")
public class NewStepDialog extends TitleAreaDialog {
	private Text filename_text;
	private Text filepath_text;
	private Combo combo_language;

	private String filename;
	private String filepath;
	private String filetype;

	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 */
	public NewStepDialog(Shell parentShell) {
		super(parentShell);
	}

	/**
	 * Create contents of the dialog.
	 * 
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(final Composite parent) {
		setTitleImage(ResourceManager.getPluginImage("com.greendot.gdn.cucumber.ui", "icons/cukes.gif"));
		setMessage("In this file will create the current step definition method based on the below settings");
		setTitle("Create a step defination file");
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayoutData(new GridData(GridData.FILL_BOTH));

		Label lblFileName = new Label(container, SWT.NONE);
		lblFileName.setBounds(10, 10, 49, 13);
		lblFileName.setText("File Name:");

		Label lblFilePath = new Label(container, SWT.NONE);
		lblFilePath.setBounds(10, 42, 49, 13);
		lblFilePath.setText("File Path:");

		filename_text = new Text(container, SWT.BORDER);
		filename_text.setText("Sample_steps");
		filename_text.selectAll();
		filename_text.setBounds(72, 10, 272, 19);

		filepath_text = new Text(container, SWT.BORDER);
		filepath_text.setBounds(72, 42, 288, 19);
		String featureFolder = CommonCucumberFeatureUtils.getFeatureFolder();
		filepath_text.setText(featureFolder);

		Button openfolder = new Button(container, SWT.NONE);
		openfolder.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				/*
				 * JFileChooser chooser=new JFileChooser();
				 * chooser.setCurrentDirectory(new java.io.File("."));
				 * chooser.setDialogTitle("Choose the Step folder");
				 * chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				 * chooser.setAcceptAllFileFilterUsed(false);
				 * 
				 * if (chooser.showOpenDialog(null) ==
				 * JFileChooser.APPROVE_OPTION) { System.out.println(
				 * "getCurrentDirectory(): " + chooser.getCurrentDirectory());
				 * System.out.println("getSelectedFile() : " +
				 * chooser.getSelectedFile()); } else { System.out.println(
				 * "No Selection "); }
				 */
				/*
				 * String getfilepath=filepath_text.getText().trim();
				 * DirectoryDialog dlg =
				 * FolderDialog.openDefaultDialog(parent.getShell(),
				 * "Select the step defination folder",
				 * "Please select a step defination directory and click OK",
				 * getfilepath);
				 * 
				 * String selectedfolder=dlg.open(); if(selectedfolder!=null){
				 * filepath_text.setText(selectedfolder);
				 * filepath_text.selectAll(); }
				 */

				String getfilepath = filepath_text.getText().trim();
				ContainerSelectionDialog dlg = FileFolderDialog.openEclipseFolder(parent.getShell(),
						"Select the step defination folder", "Please select a step defination directory and click OK",
						getfilepath);

				if (dlg.open() == ContainerSelectionDialog.OK) {
					// Object[] result2 = dlg.getResult();
					Path result = (Path) dlg.getResult()[0];
					filepath_text.setText(result.toOSString());
				}

			}
		});
		openfolder.setBounds(366, 42, 68, 23);
		openfolder.setText("...");

		Label lblFileType = new Label(container, SWT.NONE);
		lblFileType.setBounds(10, 102, 49, 13);
		lblFileType.setText("File Type:");

		Label lblSeparatorLine = new Label(container, SWT.SEPARATOR | SWT.HORIZONTAL);
		lblSeparatorLine.setText("separator line");
		lblSeparatorLine.setBounds(10, 82, 424, 2);

		combo_language = new Combo(container, SWT.NONE);
		combo_language.setItems(new String[] { "Ruby", "Java" });
		combo_language.setBounds(76, 102, 92, 21);
		combo_language.select(0);

		return area;
	}

	/**
	 * Create contents of the button bar.
	 * 
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
	}

	public void saveInput() {
		filename = filename_text.getText().trim();
		filepath = filepath_text.getText().trim();
		filetype = combo_language.getItem(combo_language.getSelectionIndex());

	}

	@Override
	protected void okPressed() {
		// TODO Auto-generated method stub
		saveInput();
		super.okPressed();
	}

	@Override
	protected void cancelPressed() {
		// TODO Auto-generated method stub
		super.cancelPressed();
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(450, 300);
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public String getFiletype() {
		return filetype;
	}

	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}
}
