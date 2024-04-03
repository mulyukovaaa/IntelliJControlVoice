package org.ru.itmo.audio.ui;


import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.ru.itmo.audio.AudioInterface;
import org.ru.itmo.audio.SimpleAudioInvoker;
import org.ru.itmo.processing.action.ActionCaller;
import org.ru.itmo.processing.action.ActionCallerSimple;

import javax.swing.*;
import java.util.logging.Logger;

public class ToolbarIconAction extends AnAction {
    public Logger logger = Logger.getLogger(ToolbarIconAction.class.getName());
    private ActionCaller actionCaller = new ActionCallerSimple();

    @Override
    public @NotNull ActionUpdateThread getActionUpdateThread() {
        return ActionUpdateThread.BGT;
    }

    public ToolbarIconAction() {
        super();
    }

    /**
     * This constructor is used to support dynamically added menu audio.
     * It sets the stt, description to be displayed for the menu item.
     * Otherwise, the default AnAction constructor is used by the IntelliJ Platform.
     *
     * @param text        The stt to be displayed as a menu item.
     * @param description The description of the menu item.
     * @param icon        The icon to be used with the menu item.
     */
    public ToolbarIconAction(@Nullable String text, @Nullable String description, @Nullable Icon icon) {
        super(text, description, icon);
    }

    private void showPopup(@NotNull AnActionEvent event, String title, String message){
        Project currentProject = event.getProject();
        Messages.showMessageDialog(
                currentProject,
                message,
                title,
                Messages.getInformationIcon());
    }

    private void showErrorNotification(String message){
        Notification notification = new Notification(
                "JControl",
                message,
                NotificationType.ERROR
        );

        Notifications.Bus.notify(notification);
    }

    private void showInfoNotification(String message){
        Notification notification = new Notification(
                "JControl",
                message,
                NotificationType.INFORMATION
        );
        notification.addAction(new PopupDialogAction());
        Notifications.Bus.notify(notification);
    }

    private static final AudioInterface audioInterface = new SimpleAudioInvoker();
    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {

        if (!audioInterface.isRunning()){
            showInfoNotification("Recording started");
            audioInterface.start();
        }
        else {
            showInfoNotification("Recording stopped");
            audioInterface.stop();
            String pathToRecord = audioInterface.getPath();
//            showInfoNotification("<h1>Path to audio:</h1><br>" +
//                    pathToRecord);
            actionCaller.call(event, "Open");
        }

    }

    @Override
    public void update(AnActionEvent e) {
        // Set the availability based on whether a project is open
        Project project = e.getProject();
        e.getPresentation().setEnabledAndVisible(project != null);
    }
}
