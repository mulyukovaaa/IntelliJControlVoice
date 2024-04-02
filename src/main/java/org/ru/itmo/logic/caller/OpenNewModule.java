package org.ru.itmo.logic.caller;

import com.intellij.ide.DataManager;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.Presentation;

public class OpenNewModule extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent e) {
        AnAction createProjectAction = ActionManager.getInstance().getAction("NewModule");
        createProjectAction.actionPerformed(AnActionEvent.createFromDataContext("NewModule", new Presentation(), DataManager.getInstance().getDataContext()));
    }
}
