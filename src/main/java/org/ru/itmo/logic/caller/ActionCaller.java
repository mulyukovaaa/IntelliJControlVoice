package org.ru.itmo.logic.caller;

import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;

public interface ActionCaller {

    public boolean call(@NotNull AnActionEvent event, String command);
}
