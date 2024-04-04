package org.ru.itmo.processing.settings;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.options.Configurable;
import com.intellij.ui.table.JBTable;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Storage("VoiceCommandConfigurable")
public class VoiceCommandConfigurable implements Configurable {
    private JPanel mainPanel;
    private JTextField keyField;
    private JTextField proxyField;
    private JTable commandTable;
    private VoiceCommandSettingComponent settingsPanel;


    // Инициализация компонентов интерфейса
    private void initUI() {
        mainPanel = new JPanel();

        keyField = new JTextField();
        proxyField = new JTextField();

        String[] columnNames = {"Name of the command", "Record command", "Action"};
        Object[][] data = {};
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        commandTable = new JBTable(model);

        mainPanel.add(keyField);
        mainPanel.add(proxyField);
        mainPanel.add(new JScrollPane(commandTable));
    }

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
        boolean isModified = false;

        if (!settings.openAiKey.equals(settingsPanel.getKey())) {
            isModified = true;
        }

        if (!settings.proxyAddress.equals(settingsPanel.getProxy())) {
            isModified = true;
        }

        JTable commandTable1 = settingsPanel.getCommandTable();
        if (settings.commandList.size() != commandTable1.getRowCount()) {
            isModified = true;
        } else {
            for (int i = 0; i < commandTable1.getRowCount(); i++) {
                Map<String, String> map = settings.commandList.get(i);
                if (!map.get("Name of the command").equals(commandTable1.getModel().getValueAt(i, 0)) ||
                        !map.get("Record command").equals(commandTable1.getModel().getValueAt(i, 1)) ||
                        !map.get("Action").equals(commandTable1.getModel().getValueAt(i, 2))) {
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
        settings.commandList = new ArrayList<>();
        JTable commandTable1 = settingsPanel.getCommandTable();
        for (int i = 0; i < commandTable1.getRowCount(); i++) {
            Map<String, String> map = new HashMap<>();
            map.put("Name of the command", commandTable1.getModel().getValueAt(i, 0).toString());
            map.put("Record command", commandTable1.getModel().getValueAt(i, 1).toString());
            map.put("Action", commandTable1.getModel().getValueAt(i, 2).toString());
            settings.commandList.add(map);
        }
    }

    @Override
    public void reset() {
        AppSettingsState settings = AppSettingsState.getInstance();
        settingsPanel.setKey(settings.openAiKey);
        settingsPanel.setProxy(settings.proxyAddress);

        DefaultTableModel model = (DefaultTableModel) settingsPanel.getCommandTable().getModel();
        model.setRowCount(0);

        for (Map<String, String> command : settings.commandList) {
            model.addRow(new Object[]{command.get("Name of the command"), command.get("Record command"), command.get("Action")});
        }
    }

    @Override
    public void disposeUIResources() {
        mainPanel = null;
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

