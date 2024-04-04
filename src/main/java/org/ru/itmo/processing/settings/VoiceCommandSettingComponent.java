package org.ru.itmo.processing.settings;

import com.intellij.ui.components.JBList;
import com.intellij.ui.components.JBTextField;
import com.intellij.ui.table.JBTable;
import org.jetbrains.annotations.NotNull;

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
    private JList commandList = new JBList();

    private JPanel networkSettingsPanel;
    private JPanel shortcutsListPanel;

    public VoiceCommandSettingComponent() {
        setupTable();
        keyField.setFont( getFont(MAIN_TEXT));
        proxyField.setFont( getFont(MAIN_TEXT));
        commandTable.setFont( getFont(MAIN_TEXT));
        addButton.setFont(getFont(TITLE_TEXT));

        networkSettingsPanel = new JPanel(new GridLayout(2, 2));
        networkSettingsPanel.setBorder(buildTitle("Network Settings"));

        JLabel key = new JLabel("OpenAI key: ");
        key.setFont(getFont(MAIN_TEXT));
        networkSettingsPanel.add(key);
        networkSettingsPanel.add(keyField);

        JLabel proxi = new JLabel("Proxy address: ");
        proxi.setFont(getFont(MAIN_TEXT));
        networkSettingsPanel.add(proxi);
        networkSettingsPanel.add(proxyField);

        shortcutsListPanel = new JPanel(new BorderLayout());
        shortcutsListPanel.setBorder(buildTitle("Shortcuts List"));

        shortcutsListPanel.add(new JScrollPane(commandTable), BorderLayout.CENTER);
        shortcutsListPanel.add(addButton, BorderLayout.SOUTH);

        settingsPanel = new JPanel(new BorderLayout());
        settingsPanel.add(networkSettingsPanel, BorderLayout.NORTH);
        settingsPanel.add(shortcutsListPanel, BorderLayout.CENTER);
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

        // TODO: Add a JComboBox to the "Action" column
        String[] actions = {"open", "close", /* add other actions here */};
        JComboBox<String> actionComboBox = new JComboBox<>(actions);

        // Set the JComboBox as the cell editor for the "Action" column
        commandTable.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(actionComboBox));

        // Add an empty row to the table
        model.addRow(new Object[]{"", "", ""});

        // Set up event handlers
        addButton.addActionListener(e -> {
            // Add a new row to the table when the add button is clicked
            model.addRow(new Object[]{"", "", ""});
        });
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

    // Update methods
    public void setKey(String key) {
        keyField.setText(key);
    }

    public void setProxy(String proxy) {
        proxyField.setText(proxy);
    }
}
