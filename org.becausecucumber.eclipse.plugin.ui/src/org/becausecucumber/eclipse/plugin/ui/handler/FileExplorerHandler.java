package org.becausecucumber.eclipse.plugin.ui.handler;

import org.becausecucumber.eclipse.plugin.ui.common.FileExplorerUtils;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;

import org.eclipse.core.resources.IResource;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.ide.ResourceUtil;
import org.eclipse.ui.texteditor.AbstractTextEditor;
import org.eclipse.ui.texteditor.ITextEditor;

@SuppressWarnings("restriction")
public class FileExplorerHandler implements IHandler {

	@Override
	public void addHandlerListener(IHandlerListener handlerListener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// TODO Auto-generated method stub
		// Object element=null;
		IEditorPart activeEditor = HandlerUtil.getActiveWorkbenchWindowChecked(event).getActivePage().getActiveEditor();
		if (!(activeEditor instanceof AbstractTextEditor)) {
			return null;
		}

		ITextEditor editor = (ITextEditor) activeEditor;
		IEditorInput editorInput = editor.getEditorInput();
		IResource element = ResourceUtil.getResource(editorInput);
		// IPath element = input instanceof FileEditorInput ?
		// ((FileEditorInput)input).getPath():null;
		if (element != null) {
			FileExplorerUtils.handleSelection(element);
		}
		return null;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isHandled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void removeHandlerListener(IHandlerListener handlerListener) {
		// TODO Auto-generated method stub

	}

}
