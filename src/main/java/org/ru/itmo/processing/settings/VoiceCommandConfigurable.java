package org.ru.itmo.processing.settings;

import com.intellij.openapi.components.Storage;
import com.intellij.openapi.options.Configurable;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Map;
import java.util.Objects;

@Storage("VoiceCommandConfigurable")
public class VoiceCommandConfigurable implements Configurable {
    private VoiceCommandSettingComponent settingsPanel;


    @Override
    public JComponent createComponent() {
        if (settingsPanel == null) {
            settingsPanel = new VoiceCommandSettingComponent();
        }
        return settingsPanel.getPanel();
    }

    @Override
    public boolean isModified() {
        AppSettingsState settings = AppSettingsState.getInstance();
        boolean isModified = !settings.openAiKey.equals(settingsPanel.getKey());

        if (!settings.proxyAddress.equals(settingsPanel.getProxy())) {
            isModified = true;
        }

        if (settingsPanel.getTranscriberType().getSelectedItem() != settings.transcriberType) {
            isModified = true;
        }
        if (settingsPanel.getLanguage().getSelectedItem() != settings.language) {
            isModified = true;
        }

        JTable commandTable1 = settingsPanel.getCommandTable();
        if (settings.userCommands.size() != commandTable1.getRowCount()) {
            isModified = true;
        } else {
            for (int i = 0; i < commandTable1.getRowCount(); i++) {
                CommandEntity commandEntity = settings.userCommands.get(i);
                if (Objects.isNull(commandEntity)) {
                    continue;
                }

                if (!commandEntity.id.equals(commandTable1.getModel().getValueAt(i, 0)) ||
                        !commandEntity.command.equals(commandTable1.getModel().getValueAt(i, 1)) ||
                        !commandEntity.action.equals(commandTable1.getModel().getValueAt(i, 2))) {
                    isModified = true;
                    break;
                }
            }
        }

        return isModified;
    }

    @Override
    public void apply() {
        AppSettingsState settings = AppSettingsState.getInstance();
        settings.openAiKey = settingsPanel.getKey();
        settings.proxyAddress = settingsPanel.getProxy();
        settings.transcriberType = (TranscriberType) settingsPanel.getTranscriberType().getSelectedItem();
        settings.language = (Languages) settingsPanel.getLanguage().getSelectedItem();

        JTable commandTable1 = settingsPanel.getCommandTable();
        for (int i = 0; i < commandTable1.getRowCount(); i++) {
            CommandEntity commandEntity = new CommandEntity();
            commandEntity.id = commandTable1.getModel().getValueAt(i, 0).toString();
            commandEntity.command = commandTable1.getModel().getValueAt(i, 1).toString();
            commandEntity.action = commandTable1.getModel().getValueAt(i, 2).toString();

            settings.userCommands.put(commandEntity.command, commandEntity);
        }
    }

    @Override
    public void reset() {
        AppSettingsState settings = AppSettingsState.getInstance();
        settingsPanel.setKey(settings.openAiKey);
        settingsPanel.setProxy(settings.proxyAddress);
        settingsPanel.getTranscriberType().setSelectedItem(settings.transcriberType);
        settingsPanel.getLanguage().setSelectedItem(settings.language);

        DefaultTableModel model = (DefaultTableModel) settingsPanel.getCommandTable().getModel();
        model.setRowCount(0);

        for (Map.Entry<String, CommandEntity> entry : settings.userCommands.entrySet()) {
            model.addRow(new Object[]{entry.getValue().id, entry.getValue().command, entry.getValue().action});
        }
    }

    @Override
    public void disposeUIResources() {
        settingsPanel = null;
    }

    @Override
    public String getDisplayName() {
        return "Voice Commands Configuration";
    }

    @Nullable
    @Override
    public JComponent getPreferredFocusedComponent() {
        return settingsPanel.getPreferredFocusedComponent();
    }
}

