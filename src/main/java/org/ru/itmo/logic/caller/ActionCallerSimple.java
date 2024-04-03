package org.ru.itmo.logic.caller;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

public class ActionCallerSimple implements ActionCaller{
    @Override
    public boolean call(@NotNull AnActionEvent event, String command) {
        Project project = event.getProject();
        assert project != null;
        System.out.println(project.getBasePath() + " Command:" + command);
        return true;
    }
    private void callOpen(){

    }
}
