package org.ru.itmo.logic.caller;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;

import java.util.Arrays;
import java.util.List;

public class OpenPreviousCurrentFile extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent e) {
        Project project = e.getProject();
        if (project != null) {
            FileEditorManager editorManager = FileEditorManager.getInstance(project);
            VirtualFile[] openFiles = editorManager.getOpenFiles();
//            System.out.println(Arrays.toString(openFiles));
            if (openFiles.length > 1) {
                VirtualFile currentFile = editorManager.getSelectedFiles()[0];
                List<VirtualFile> fileList = Arrays.asList(openFiles);
                int currentIndex = fileList.indexOf(currentFile);
                int nextIndex = (currentIndex - 1) % openFiles.length;
                VirtualFile nextFile = openFiles[nextIndex];
                editorManager.openFile(nextFile);
            }
        }
    }
}
