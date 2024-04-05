package org.ru.itmo.processing.settings;

import com.intellij.openapi.ui.ComboBox;
import com.intellij.ui.components.JBTextField;
import com.intellij.ui.table.JBTable;
import org.jetbrains.annotations.NotNull;
import org.ru.itmo.processing.action.ActionCallerSimple;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VoiceCommandSettingComponent {
    private static final String FONT_NAME = "JetBrains Mono";
    private static final int MAIN_TEXT = 12;
    private static final int TITLE_TEXT = 14;

    private JPanel settingsPanel;
    private final JBTextField keyField = new JBTextField(15);
    private JTextField proxyField = new JBTextField(15);
    private JTable commandTable = new JBTable();
    private JButton addButton = new JButton("Add Command");
    private JComboBox<TranscriberType> transcriberType = new ComboBox<>(TranscriberType.values());
    private JComboBox<Languages> language = new ComboBox<>(Languages.values());

    private JPanel networkSettingsPanel;
    private JPanel shortcutsListPanel;

    public VoiceCommandSettingComponent() {
        transcriberType.setSelectedIndex(0);

        setupTable();

        keyField.setFont(getFont(MAIN_TEXT));
        proxyField.setFont(getFont(MAIN_TEXT));
        commandTable.setFont(getFont(MAIN_TEXT));
        addButton.setFont(getFont(TITLE_TEXT));

        networkSettingsPanel = new JPanel(new GridLayout(4, 2));
        networkSettingsPanel.setBorder(buildTitle("Transcribing Settings"));

        initTranscribingSettings();

        shortcutsListPanel = new JPanel(new BorderLayout());
        shortcutsListPanel.setBorder(buildTitle("Shortcuts List"));

        shortcutsListPanel.add(new JScrollPane(commandTable), BorderLayout.CENTER);
        shortcutsListPanel.add(addButton, BorderLayout.SOUTH);

        settingsPanel = new JPanel(new BorderLayout());
        settingsPanel.add(networkSettingsPanel, BorderLayout.NORTH);
        settingsPanel.add(shortcutsListPanel, BorderLayout.CENTER);
    }

    private void initTranscribingSettings() {
        JLabel type = new JLabel("Transcriber type: ");
        type.setFont(getFont(MAIN_TEXT));
        networkSettingsPanel.add(type);
        networkSettingsPanel.add(transcriberType);

        JLabel key = new JLabel("OpenAI key: ");
        key.setFont(getFont(MAIN_TEXT));
        networkSettingsPanel.add(key);
        networkSettingsPanel.add(keyField);

        JLabel proxi = new JLabel("Proxy address: ");
        proxi.setFont(getFont(MAIN_TEXT));
        networkSettingsPanel.add(proxi);
        networkSettingsPanel.add(proxyField);

        JLabel lang = new JLabel("Command main language: ");
        lang.setFont(getFont(MAIN_TEXT));
        networkSettingsPanel.add(lang);
        networkSettingsPanel.add(language);
    }

    @NotNull
    private static TitledBorder buildTitle(String titleName) {
        TitledBorder commandsTitle = new TitledBorder(titleName);
        commandsTitle.setTitleFont(getFont(TITLE_TEXT));
        commandsTitle.setTitlePosition(TitledBorder.CENTER);
        return commandsTitle;
    }

    @NotNull
    private static Font getFont(int size) {
        return new Font(FONT_NAME, Font.PLAIN, size);
    }

    private void setupTable() {
        String[] columnNames = {"Name of the command", "Record command", "Action"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        commandTable.setModel(model);
        commandTable.setFont(getFont(MAIN_TEXT));
        commandTable.setRowHeight(30);

        JComboBox<String> actionComboBox = new ComboBox<>(ActionCallerSimple.getCommands());

        commandTable.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(actionComboBox));

        model.addRow(new Object[]{"", "", ""});

        addButton.addActionListener(e -> model.addRow(new Object[]{"", "", ""}));
    }

    public JPanel getPanel() {
        return settingsPanel;
    }

    public JComponent getPreferredFocusedComponent() {
        return keyField;
    }

    public String getKey() {
        return keyField.getText();
    }

    public String getProxy() {
        return proxyField.getText();
    }

    public JTable getCommandTable() {
        return commandTable;
    }

    public void setKey(String key) {
        keyField.setText(key);
    }

    public void setProxy(String proxy) {
        proxyField.setText(proxy);
    }

    public JComboBox<TranscriberType> getTranscriberType() {
        return transcriberType;
    }

    public void setTranscriberType(JComboBox<TranscriberType> transcriberType) {
        this.transcriberType = transcriberType;
    }

    public JComboBox<Languages> getLanguage() {
        return language;
    }
}
