package org.ru.itmo.processing.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.diagnostic.LogLevel;
import com.intellij.openapi.diagnostic.Logger;
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
import org.ru.itmo.processing.action.commands.StartDebugProcess;
import org.ru.itmo.processing.action.commands.StartRunProcess;
import org.ru.itmo.processing.action.commands.StopProcess;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class ActionCallerSimple implements ActionCaller {
    private static final Logger log = Logger.getInstance(ActionCallerSimple.class);
    private static final Map<String, Consumer<AnActionEvent>> commands2actions = new HashMap<>();

    static {
        commands2actions.put("close", ActionCallerSimple::callCloseCurrentFile);
        commands2actions.put("debug", ActionCallerSimple::callOpenDebug);
        commands2actions.put("new class", ActionCallerSimple::callOpenNewClass);
        commands2actions.put("structure", ActionCallerSimple::callOpenStructure);
        commands2actions.put("open", ActionCallerSimple::callOpenProject);
        commands2actions.put("version control", ActionCallerSimple::callOpenVersionControl);
        commands2actions.put("services", ActionCallerSimple::callOpenServices);
        commands2actions.put("next", ActionCallerSimple::callOpenNextCurrentFile);
        commands2actions.put("previous", ActionCallerSimple::callOpenPreviousCurrentFile);
        commands2actions.put("close all except current", ActionCallerSimple::callCloseAllExceptCurrentFile);
        commands2actions.put("exit", ActionCallerSimple::callExit);
        commands2actions.put("hide debug", ActionCallerSimple::callHideDebug);
        commands2actions.put("hide merge requests", ActionCallerSimple::callHideMergeRequests);
        commands2actions.put("hide project", ActionCallerSimple::callHideProject);
        commands2actions.put("hide pull requests", ActionCallerSimple::callHidePullRequests);
        commands2actions.put("hide run", ActionCallerSimple::callHideRun);
        commands2actions.put("hide services", ActionCallerSimple::callHideServices);
        commands2actions.put("hide structure", ActionCallerSimple::callHideStructure);
        commands2actions.put("hide terminal action", ActionCallerSimple::callHideTerminalAction);
        commands2actions.put("hide version control", ActionCallerSimple::callHideVersionControl);
        commands2actions.put("move cursor to line", ActionCallerSimple::callMoveCursorToLineAction);
        commands2actions.put("open debug", ActionCallerSimple::callOpenDebug);
        commands2actions.put("open merge requests", ActionCallerSimple::callOpenMergeRequests);
        commands2actions.put("open new class", ActionCallerSimple::callOpenNewClass);
        commands2actions.put("open new module", ActionCallerSimple::callOpenNewModule);
        commands2actions.put("open new project", ActionCallerSimple::callOpenNewProject);
        commands2actions.put("open pull requests", ActionCallerSimple::callOpenPullRequests);
        commands2actions.put("open run", ActionCallerSimple::callOpenRun);
        commands2actions.put("open services", ActionCallerSimple::callOpenServices);
        commands2actions.put("open structure", ActionCallerSimple::callOpenStructure);
        commands2actions.put("open terminal action", ActionCallerSimple::callOpenTerminalAction);
        commands2actions.put("open version control", ActionCallerSimple::callOpenVersionControl);
        commands2actions.put("start debug process", ActionCallerSimple::callStartDebugProcess);
        commands2actions.put("start run process", ActionCallerSimple::callStartRunProcess);
        commands2actions.put("stop process", ActionCallerSimple::callStopProcess);
        commands2actions.put("create new class", ActionCallerSimple::callNewClass);
    }


    @Override
    public boolean call(@NotNull AnActionEvent event, @NotNull String command) {
        Project project = event.getProject();
        assert project != null;
        System.out.println(project.getBasePath() + " Command:" + command);
        log.info(project.getBasePath() + " Command: " + command);
        log.setLevel(LogLevel.DEBUG);

        Consumer<AnActionEvent> action = commands2actions.get(command);
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

    public static String[] getCommands() {
        return commands2actions.keySet().toArray(new String[0]);
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
