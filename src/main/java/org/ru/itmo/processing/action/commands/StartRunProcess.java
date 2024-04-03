package org.ru.itmo.processing.action.commands;

import com.intellij.ide.DataManager;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.Presentation;

public class StartRunProcess extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent e) {
        AnAction createProjectAction = ActionManager.getInstance().getAction("Run");
        createProjectAction.actionPerformed(AnActionEvent.createFromDataContext("Run", new Presentation(), DataManager.getInstance().getDataContext()));
    }
}
