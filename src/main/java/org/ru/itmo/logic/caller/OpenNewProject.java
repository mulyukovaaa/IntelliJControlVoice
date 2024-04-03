package org.ru.itmo.logic.caller;

import com.intellij.ide.DataManager;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.Presentation;

public class OpenNewProject extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent e) {
        AnAction createProjectAction = ActionManager.getInstance().getAction("NewProject");
        createProjectAction.actionPerformed(AnActionEvent.createFromDataContext("NewProject", new Presentation(), DataManager.getInstance().getDataContext()));
    }
}
