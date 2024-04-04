package org.ru.itmo.processing.action.commands;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.EditorFactory;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;

public class MoveCursorToLineAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent event) {
        Project project = event.getProject();
        if (project != null) {
            FileEditorManager fileEditorManager = FileEditorManager.getInstance(project);
            Editor editor = fileEditorManager.getSelectedTextEditor();
            if (editor != null) {
                int lineNumber = 10;
                editor.getCaretModel().moveToLogicalPosition(editor.offsetToLogicalPosition(editor.getDocument().getLineStartOffset(lineNumber - 1)));
            }
        }
    }
}
