package org.ru.itmo.processing.action;

import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;

public interface ActionCaller {

    public boolean call(@NotNull AnActionEvent event, String command);
}
