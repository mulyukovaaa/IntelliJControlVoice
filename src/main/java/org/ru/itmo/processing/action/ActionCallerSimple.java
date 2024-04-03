package org.ru.itmo.processing.action;

import com.github.weisj.jsvg.S;
import com.intellij.icons.ExpUiIcons;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import net.schmizz.sshj.transport.mac.MAC;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

public class ActionCallerSimple implements ActionCaller {
    private final Map<String, Consumer<AnActionEvent>> map = new HashMap<>();

    public ActionCallerSimple() {
        map.put("Close", ActionCallerSimple::callCloseCurrentFile);
        map.put("Debug", ActionCallerSimple::callOpenDebug);
        map.put("New class", ActionCallerSimple::callNewClass);
        map.put("Structure", ActionCallerSimple::callOpenStructure);
        map.put("Open", ActionCallerSimple::callOpenProject);
    }

    @Override
    public boolean call(@NotNull AnActionEvent event, @NotNull String command) {
        Project project = event.getProject();
        assert project != null;
        System.out.println(project.getBasePath() + " Command:" + command);

        Consumer<AnActionEvent> action = map.get(command);
        if (action == null){
            return false;
        }
        action.accept(event);
        return true;
    }

    private static void callCloseCurrentFile(@NotNull AnActionEvent event){
        AnAction action = new CloseCurrentFile();
        action.actionPerformed(event);
    }

    private static void callOpenProject(@NotNull AnActionEvent event){
        AnAction action = new OpenProject();
        action.actionPerformed(event);
    }

    private static void callNewClass(@NotNull AnActionEvent event){
        AnAction action = new OpenNewClass();
        action.actionPerformed(event);
    }

    private static void callOpenStructure(@NotNull AnActionEvent event){
        AnAction action = new OpenStructure();
        action.actionPerformed(event);
    }

    private static void callOpenDebug(@NotNull AnActionEvent event){
        AnAction action = new OpenDebug();
        action.actionPerformed(event);
    }
}
