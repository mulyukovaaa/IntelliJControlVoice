package org.ru.itmo.processing.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.diagnostic.LogLevel;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.ru.itmo.processing.action.commands.CloseAllExceptCurrentFile;
import org.ru.itmo.processing.action.commands.CloseCurrentFile;
import org.ru.itmo.processing.action.commands.Exit;
import org.ru.itmo.processing.action.commands.HideDebug;
import org.ru.itmo.processing.action.commands.HideMergeRequests;
import org.ru.itmo.processing.action.commands.HideProject;
import org.ru.itmo.processing.action.commands.HidePullRequests;
import org.ru.itmo.processing.action.commands.HideRun;
import org.ru.itmo.processing.action.commands.HideServices;
import org.ru.itmo.processing.action.commands.HideStructure;
import org.ru.itmo.processing.action.commands.HideTerminalAction;
import org.ru.itmo.processing.action.commands.HideVersionControl;
import org.ru.itmo.processing.action.commands.MoveCursorToLineAction;
import org.ru.itmo.processing.action.commands.OpenDebug;
import org.ru.itmo.processing.action.commands.OpenMergeRequests;
import org.ru.itmo.processing.action.commands.OpenNewClass;
import org.ru.itmo.processing.action.commands.OpenNewModule;
import org.ru.itmo.processing.action.commands.OpenNewProject;
import org.ru.itmo.processing.action.commands.OpenNextCurrentFile;
import org.ru.itmo.processing.action.commands.OpenPreviousCurrentFile;
import org.ru.itmo.processing.action.commands.OpenProject;
import org.ru.itmo.processing.action.commands.OpenPullRequests;
import org.ru.itmo.processing.action.commands.OpenRun;
import org.ru.itmo.processing.action.commands.OpenServices;
import org.ru.itmo.processing.action.commands.OpenStructure;
import org.ru.itmo.processing.action.commands.OpenTerminalAction;
import org.ru.itmo.processing.action.commands.OpenVersionControl;
import com.intellij.openapi.diagnostic.Logger;
import org.ru.itmo.processing.action.commands.StartDebugProcess;
import org.ru.itmo.processing.action.commands.StartRunProcess;
import org.ru.itmo.processing.action.commands.StopProcess;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class ActionCallerSimple implements ActionCaller {
    private static final Logger log = Logger.getInstance(ActionCallerSimple.class);
    private static final Map<String, Consumer<AnActionEvent>> map = new HashMap<>();

    static {
        map.put("close", ActionCallerSimple::callCloseCurrentFile);
        map.put("debug", ActionCallerSimple::callOpenDebug);
        map.put("new class", ActionCallerSimple::callOpenNewClass);
        map.put("structure", ActionCallerSimple::callOpenStructure);
        map.put("open", ActionCallerSimple::callOpenProject);
        map.put("version control", ActionCallerSimple::callOpenVersionControl);
        map.put("services", ActionCallerSimple::callOpenServices);
        map.put("next", ActionCallerSimple::callOpenNextCurrentFile);
        map.put("previous", ActionCallerSimple::callOpenPreviousCurrentFile);
        map.put("close all except current", ActionCallerSimple::callCloseAllExceptCurrentFile);
        map.put("exit", ActionCallerSimple::callExit);
        map.put("hide debug", ActionCallerSimple::callHideDebug);
        map.put("hide merge requests", ActionCallerSimple::callHideMergeRequests);
        map.put("hide project", ActionCallerSimple::callHideProject);
        map.put("hide pull requests", ActionCallerSimple::callHidePullRequests);
        map.put("hide run", ActionCallerSimple::callHideRun);
        map.put("hide services", ActionCallerSimple::callHideServices);
        map.put("hide structure", ActionCallerSimple::callHideStructure);
        map.put("hide terminal action", ActionCallerSimple::callHideTerminalAction);
        map.put("hide version control", ActionCallerSimple::callHideVersionControl);
        map.put("move cursor to line", ActionCallerSimple::callMoveCursorToLineAction);
        map.put("open debug", ActionCallerSimple::callOpenDebug);
        map.put("open merge requests", ActionCallerSimple::callOpenMergeRequests);
        map.put("open new class", ActionCallerSimple::callOpenNewClass);
        map.put("open new module", ActionCallerSimple::callOpenNewModule);
        map.put("open new project", ActionCallerSimple::callOpenNewProject);
        map.put("open pull requests", ActionCallerSimple::callOpenPullRequests);
        map.put("open run", ActionCallerSimple::callOpenRun);
        map.put("open services", ActionCallerSimple::callOpenServices);
        map.put("open structure", ActionCallerSimple::callOpenStructure);
        map.put("open terminal action", ActionCallerSimple::callOpenTerminalAction);
        map.put("open version control", ActionCallerSimple::callOpenVersionControl);
        map.put("start debug process", ActionCallerSimple::callStartDebugProcess);
        map.put("start run process", ActionCallerSimple::callStartRunProcess);
        map.put("stop process", ActionCallerSimple::callStopProcess);
        map.put("create new class", ActionCallerSimple::callNewClass);
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

    private static void callCloseAllExceptCurrentFile(@NotNull AnActionEvent event){
        AnAction action = new CloseAllExceptCurrentFile();
        action.actionPerformed(event);
    }


    private static void callExit(@NotNull AnActionEvent event){
        AnAction action = new Exit();
        action.actionPerformed(event);
    }

    private static void callHideDebug(@NotNull AnActionEvent event){
        AnAction action = new HideDebug();
        action.actionPerformed(event);
    }

    private static void callHideMergeRequests(@NotNull AnActionEvent event){
        AnAction action = new HideMergeRequests();
        action.actionPerformed(event);
    }

    private static void callHideProject(@NotNull AnActionEvent event){
        AnAction action = new HideProject();
        action.actionPerformed(event);
    }

    private static void callHidePullRequests(@NotNull AnActionEvent event){
        AnAction action = new HidePullRequests();
        action.actionPerformed(event);
    }

    private static void callHideRun(@NotNull AnActionEvent event){
        AnAction action = new HideRun();
        action.actionPerformed(event);
    }

    private static void callHideServices(@NotNull AnActionEvent event){
        AnAction action = new HideServices();
        action.actionPerformed(event);
    }

    private static void callHideStructure(@NotNull AnActionEvent event){
        AnAction action = new HideStructure();
        action.actionPerformed(event);
    }

    private static void callHideTerminalAction(@NotNull AnActionEvent event){
        AnAction action = new HideTerminalAction();
        action.actionPerformed(event);
    }

    private static void callHideVersionControl(@NotNull AnActionEvent event){
        AnAction action = new HideVersionControl();
        action.actionPerformed(event);
    }

    private static void callMoveCursorToLineAction(@NotNull AnActionEvent event){
        AnAction action = new MoveCursorToLineAction();
        action.actionPerformed(event);
    }


    private static void callOpenMergeRequests(@NotNull AnActionEvent event){
        AnAction action = new OpenMergeRequests();
        action.actionPerformed(event);
    }

    private static void callOpenNewClass(@NotNull AnActionEvent event){
        AnAction action = new OpenNewClass();
        action.actionPerformed(event);
    }

    private static void callOpenNewModule(@NotNull AnActionEvent event){
        AnAction action = new OpenNewModule();
        action.actionPerformed(event);
    }

    private static void callOpenNewProject(@NotNull AnActionEvent event){
        AnAction action = new OpenNewProject();
        action.actionPerformed(event);
    }

    private static void callOpenNextCurrentFile(@NotNull AnActionEvent event){
        AnAction action = new OpenNextCurrentFile();
        action.actionPerformed(event);
    }

    private static void callOpenPreviousCurrentFile(@NotNull AnActionEvent event){
        AnAction action = new OpenPreviousCurrentFile();
        action.actionPerformed(event);
    }


    private static void callOpenPullRequests(@NotNull AnActionEvent event){
        AnAction action = new OpenPullRequests();
        action.actionPerformed(event);
    }

    private static void callOpenRun(@NotNull AnActionEvent event){
        AnAction action = new OpenRun();
        action.actionPerformed(event);
    }


    private static void callOpenTerminalAction(@NotNull AnActionEvent event){
        AnAction action = new OpenTerminalAction();
        action.actionPerformed(event);
    }

    private static void callStartDebugProcess(@NotNull AnActionEvent event){
        AnAction action = new StartDebugProcess();
        action.actionPerformed(event);
    }

    private static void callStartRunProcess(@NotNull AnActionEvent event){
        AnAction action = new StartRunProcess();
        action.actionPerformed(event);
    }

    private static void callStopProcess(@NotNull AnActionEvent event){
        AnAction action = new StopProcess();
        action.actionPerformed(event);
    }
}
