package org.ru.itmo.processing.action.commands;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;

import java.util.Arrays;
import java.util.List;

public class CloseAllExceptCurrentFile extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent e) {
        Project project = e.getProject();
        if (project != null) {
            FileEditorManager editorManager = FileEditorManager.getInstance(project);
            VirtualFile[] openFiles = editorManager.getOpenFiles();
            if (openFiles.length > 1) {
                VirtualFile currentFile = editorManager.getSelectedFiles()[0];
                for (VirtualFile file : openFiles) {
                    if (!file.equals(currentFile)) {
                        editorManager.closeFile(file);
                    }
                }
            }
        }
    }
}
