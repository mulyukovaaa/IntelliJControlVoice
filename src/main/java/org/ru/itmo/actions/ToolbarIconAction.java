package org.ru.itmo.actions;


import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.ru.itmo.logic.audioinvoker.AudioInterface;
import org.ru.itmo.logic.audioinvoker.SimpleAudioInvoker;

import javax.swing.*;
import java.util.logging.Logger;

public class ToolbarIconAction extends AnAction {
    public Logger logger = Logger.getLogger(ToolbarIconAction.class.getName());

    @Override
    public @NotNull ActionUpdateThread getActionUpdateThread() {
        return ActionUpdateThread.BGT;
    }

    public ToolbarIconAction() {
        super();
    }

    /**
     * This constructor is used to support dynamically added menu actions.
     * It sets the text, description to be displayed for the menu item.
     * Otherwise, the default AnAction constructor is used by the IntelliJ Platform.
     *
     * @param text        The text to be displayed as a menu item.
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
//            AnAction createProjectAction = ActionManager.getInstance().getAction("Exit");
//            createProjectAction.actionPerformed(AnActionEvent.createFromDataContext("Exit", new Presentation(), DataManager.getInstance().getDataContext()));

        }
        else {
            showInfoNotification("Recording stopped");
            audioInterface.stop();
//            AnAction stopDebugAction = ActionManager.getInstance().getAction("Stop");
//            stopDebugAction.actionPerformed(AnActionEvent.createFromDataContext("Stop", new Presentation(), DataManager.getInstance().getDataContext()));

        }
    }

    @Override
    public void update(AnActionEvent e) {
        // Set the availability based on whether a project is open
        Project project = e.getProject();
        e.getPresentation().setEnabledAndVisible(project != null);
    }
}
