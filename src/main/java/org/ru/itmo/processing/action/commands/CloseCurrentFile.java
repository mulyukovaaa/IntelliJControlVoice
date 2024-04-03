package org.ru.itmo.processing.action.commands;

import com.intellij.ide.DataManager;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.Presentation;

public class CloseCurrentFile extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent e) {
        AnAction createProjectAction = ActionManager.getInstance().getAction("CloseContent");
        createProjectAction.actionPerformed(AnActionEvent.createFromDataContext("CloseContent", new Presentation(), DataManager.getInstance().getDataContext()));
    }
}
