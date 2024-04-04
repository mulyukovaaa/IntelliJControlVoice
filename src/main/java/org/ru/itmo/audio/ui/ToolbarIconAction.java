package org.ru.itmo.audio.ui;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationDisplayType;
import com.intellij.notification.NotificationGroup;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.actionSystem.ActionUpdateThread;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.ru.itmo.VoiceMatchToCommand.VoiceMatchToCommand;
import org.ru.itmo.audio.AudioInterface;
import org.ru.itmo.audio.SimpleAudioInvoker;
import org.ru.itmo.processing.action.ActionCaller;
import org.ru.itmo.processing.action.ActionCallerSimple;
import com.intellij.openapi.diagnostic.Logger;

import javax.swing.*;


public class ToolbarIconAction extends AnAction {
        public Logger log = Logger.getInstance(ToolbarIconAction.class.getName());
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

    private void showPopup(@NotNull AnActionEvent event, String title, String message) {
        Project currentProject = event.getProject();
        Messages.showMessageDialog(
                currentProject,
                message,
                title,
                Messages.getInformationIcon());
    }

    private void showErrorNotification(String message) {
        NotificationGroup group = new NotificationGroup("JControl", NotificationDisplayType.BALLOON, true);
        Notification notification = new Notification(
                "JControl",
                message,
                NotificationType.ERROR
        );

        Notifications.Bus.notify(notification);
    }

    private void showInfoNotification(String message) {
        NotificationGroup group = new NotificationGroup("JControl", NotificationDisplayType.BALLOON, true);
        Notification notification = group.createNotification(
                "JControl",
                message,
                NotificationType.INFORMATION
        );
        Notifications.Bus.notify(notification);
    }

    private static final AudioInterface audioInterface = new SimpleAudioInvoker();

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        if (!audioInterface.isRunning()) {
            showInfoNotification("<h3>Recording started</h3>Press again to stop");
            audioInterface.start();
        } else {
            showInfoNotification("Recording stopped");
            audioInterface.stop();
            String pathToRecord = audioInterface.getPath();
            String parsedCommand;
            try{
                parsedCommand = VoiceMatchToCommand.math(pathToRecord);
                System.out.println(parsedCommand);

                boolean flag = actionCaller.call(event, parsedCommand);
                if (flag) {
                    showInfoNotification("Running " + parsedCommand);
                } else {
                    showErrorNotification("Command " + parsedCommand + " not found");
                }
            } catch (Exception e){
                showErrorNotification("An error occurred: " + e.getMessage());
            }
        }

    }

    @Override
    public void update(AnActionEvent e) {
        // Set the availability based on whether a project is open
        Project project = e.getProject();
        e.getPresentation().setEnabledAndVisible(project != null);
    }
}
