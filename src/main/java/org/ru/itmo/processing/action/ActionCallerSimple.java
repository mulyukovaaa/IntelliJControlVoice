package org.ru.itmo.processing.action;


import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.diagnostic.LogLevel;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.ru.itmo.processing.action.commands.CloseCurrentFile;
import org.ru.itmo.processing.action.commands.OpenDebug;
import org.ru.itmo.processing.action.commands.OpenNewClass;
import org.ru.itmo.processing.action.commands.OpenNextCurrentFile;
import org.ru.itmo.processing.action.commands.OpenPreviousCurrentFile;
import org.ru.itmo.processing.action.commands.OpenProject;
import org.ru.itmo.processing.action.commands.OpenServices;
import org.ru.itmo.processing.action.commands.OpenStructure;
import org.ru.itmo.processing.action.commands.OpenVersionControl;
import com.intellij.openapi.diagnostic.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class ActionCallerSimple implements ActionCaller {
    private static final Logger log = Logger.getInstance(ActionCallerSimple.class);
    private static final Map<String, Consumer<AnActionEvent>> map = new HashMap<>();

    static  {
        map.put("close", ActionCallerSimple::callCloseCurrentFile);
        map.put("debug", ActionCallerSimple::callOpenDebug);
        map.put("new class", ActionCallerSimple::callNewClass);
        map.put("structure", ActionCallerSimple::callOpenStructure);
        map.put("open", ActionCallerSimple::callOpenProject);
        map.put("version control", ActionCallerSimple::callOpenVersionControl);
        map.put("services", ActionCallerSimple::callOpenServices);
        map.put("next", ActionCallerSimple::callNextFile);
        map.put("previous", ActionCallerSimple::callPreviousFile);
    }

    @Override
    public boolean call(@NotNull AnActionEvent event, @NotNull String command) {
        Project project = event.getProject();
        assert project != null;
        System.out.println(project.getBasePath() + " Command:" + command);
        log.info(project.getBasePath() + " Command: " + command);
        log.setLevel(LogLevel.DEBUG);

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

    public static void callOpenVersionControl(@NotNull AnActionEvent event){
        AnAction action = new OpenVersionControl();
        action.actionPerformed(event);
    }

    public static void callOpenServices(@NotNull AnActionEvent event){
        AnAction action = new OpenServices();
        action.actionPerformed(event);
    }

    public static void callNextFile(@NotNull AnActionEvent event){
        AnAction action = new OpenNextCurrentFile();
        action.actionPerformed(event);
    }

    public static void callPreviousFile(@NotNull AnActionEvent event){
        AnAction action = new OpenPreviousCurrentFile();
        action.actionPerformed(event);
    }
}
