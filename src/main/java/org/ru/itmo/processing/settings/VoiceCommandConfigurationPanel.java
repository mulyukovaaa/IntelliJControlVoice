package org.ru.itmo.processing.settings;

import com.intellij.ui.table.JBTable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VoiceCommandConfigurationPanel extends JPanel {

    private JTextField keyField = new JTextField(15);
    private JTextField proxyField = new JTextField(15);
    private JTable commandTable = new JBTable(); // Предполагается, что модель таблицы будет настроена отдельно
    private JButton addButton = new JButton("Add Command");
    private JPanel panel1;
    private JLabel openai;
    private JList CommandList;
    private JLabel proxi;

    public VoiceCommandConfigurationPanel() {
        setLayout(new GridBagLayout());
        setupUI();
    }

    private void setupUI() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(4, 4, 4, 4);

        add(new JLabel("Enter the key:"), gbc);
        add(keyField, gbc);

        add(new JLabel("Enter the address of proxy:"), gbc);
        add(proxyField, gbc);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        add(new JScrollPane(commandTable), gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(addButton, gbc);

        setupTable();
    }

    private void setupTable() {
        // Set up the table model
        String[] columnNames = {"Name of the command", "Record command", "Action"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        commandTable.setModel(model);

        // Set up event handlers
        addButton.addActionListener(e -> {
            // Add a new row to the table when the add button is clicked
            model.addRow(new Object[]{"", "", ""});
        });
    }

    public JComponent getPreferredFocusedComponent() {
        return keyField;
    }

    // Accessor methods
    public String getKey() {
        return keyField.getText();
    }

    public String getProxy() {
        return proxyField.getText();
    }

    // Update methods
    public void setKey(String key) {
        keyField.setText(key);
    }

    public void setProxy(String proxy) {
        proxyField.setText(proxy);
    }
}
