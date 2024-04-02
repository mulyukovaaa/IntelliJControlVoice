package org.ru.itmo.actions;


import com.github.weisj.jsvg.S;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.actionSystem.ActionUpdateThread;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.jsonProtocol.Optional;

import javax.management.NotificationFilter;
import javax.swing.*;

public class ToolbarIconAction extends AnAction {

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

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
//        showErrorNotification("TestMessage");
        showInfoNotification("TestMessage");

    }

    @Override
    public void update(AnActionEvent e) {
        // Set the availability based on whether a project is open
        Project project = e.getProject();
        e.getPresentation().setEnabledAndVisible(project != null);
    }
}
