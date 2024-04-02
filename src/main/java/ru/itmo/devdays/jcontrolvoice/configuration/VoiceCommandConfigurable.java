package ru.itmo.devdays.jcontrolvoice.configuration;

import com.intellij.openapi.options.Configurable;
import com.intellij.ui.table.JBTable;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class VoiceCommandConfigurable implements Configurable {
    private JPanel mainPanel;
    private JTextField keyField;
    private JTextField proxyField;
    private JTable commandTable;
    private VoiceCommandConfigurationPanel mySettingsPanel;


    // Инициализация компонентов интерфейса
    private void initUI() {
        mainPanel = new JPanel();
        // Настройте mainPanel, используя LayoutManager по вашему выбору, например, BorderLayout

        keyField = new JTextField();
        proxyField = new JTextField();

        // Настройка таблицы команд
        String[] columnNames = {"Name of the command", "Record command", "Action"};
        Object[][] data = {}; // Загрузите существующие данные или оставьте пустым
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        commandTable = new JBTable(model);

        // Добавьте элементы на mainPanel
        mainPanel.add(keyField);
        mainPanel.add(proxyField);
        mainPanel.add(new JScrollPane(commandTable)); // Для прокрутки таблицы
    }

    @Override
    public JComponent createComponent() {
        if (mySettingsPanel == null) {
            mySettingsPanel = new VoiceCommandConfigurationPanel();
        }
        return mySettingsPanel;
    }

    @Override
    public boolean isModified() {
        // логика для определения, были ли изменены настройки
        return false;
    }

    @Override
    public void apply() {
        // сохранение настроек
    }

    @Override
    public void reset() {
        // сброс настроек к их первоначальному состоянию
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
        return mySettingsPanel.getPreferredFocusedComponent();
    }
}

