package org.eclipse.jdt.internal.ui.wizards;

import org.eclipse.swt.widgets.Composite;

import org.eclipse.jdt.core.IClasspathAttribute;
import org.eclipse.jdt.core.JavaCore;

import org.eclipse.jdt.ui.wizards.NewElementWizardPage;

public class NewSourceTestFolderCreationWizardPage extends NewElementWizardPage {

	private static final IClasspathAttribute ATTR_IGNORE_OPTIONAL_PROBLEMS_TRUE= JavaCore.newClasspathAttribute(IClasspathAttribute.IGNORE_OPTIONAL_PROBLEMS, "true"); //$NON-NLS-1$

	private static final String PAGE_NAME= "NewSourceTestFolderWizardPage"; //$NON-NLS-1$

	public NewSourceTestFolderCreationWizardPage() {
		super(PAGE_NAME);

		setTitle(NewWizardMessages.NewSourceTestFolderWizardPage_title);
		setDescription(NewWizardMessages.NewSourceTestFolderWizardPage_description);
	}

	@Override
	public void createControl(Composite parent) {
		// TODO Auto-generated method stub

	}

}
