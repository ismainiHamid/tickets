package ma.tickets.forms;

import ma.tickets.frams.MainFrame;

import javax.swing.*;
import java.awt.*;

public class TicketFormPanel extends JPanel {
    public TicketFormPanel(MainFrame parentFrame) {
        setLayout(new BorderLayout());
        setName("TicketFormPanel");

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        Dimension fieldSize = new Dimension(300, 30);
        Font labelFont = new Font("Arial", Font.PLAIN, 14);

        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel ticketIdLabel = new JLabel("Ticket ID");
        ticketIdLabel.setFont(labelFont);
        formPanel.add(ticketIdLabel, gbc);

        gbc.gridy = 1;
        JTextField ticketIdField = new JTextField(35);
        ticketIdField.setPreferredSize(fieldSize);
        ticketIdField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        formPanel.add(ticketIdField, gbc);

        gbc.gridy = 2;
        JLabel titleLabel = new JLabel("Title");
        titleLabel.setFont(labelFont);
        formPanel.add(titleLabel, gbc);

        gbc.gridy = 3;
        JTextField titleField = new JTextField(35);
        titleField.setPreferredSize(fieldSize);
        titleField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        formPanel.add(titleField, gbc);

        gbc.gridy = 4;
        JLabel priorityLabel = new JLabel("Priority");
        priorityLabel.setFont(labelFont);
        formPanel.add(priorityLabel, gbc);

        gbc.gridy = 5;
        JComboBox<String> priorityComboBox = new JComboBox<>(new String[]{"LOW", "MEDIUM", "HIGH"});
        priorityComboBox.setPreferredSize(new Dimension(300, 30));
        priorityComboBox.setBackground(Color.WHITE);
        priorityComboBox.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        formPanel.add(priorityComboBox, gbc);

        gbc.gridy = 6;
        JLabel categoryLabel = new JLabel("Category");
        categoryLabel.setFont(labelFont);
        formPanel.add(categoryLabel, gbc);

        gbc.gridy = 7;
        JComboBox<String> categoryComboBox = new JComboBox<>(new String[]{"NETWORK", "HARDWARE", "SOFTWARE", "OTHER"});
        categoryComboBox.setPreferredSize(new Dimension(300, 30));
        categoryComboBox.setBackground(Color.WHITE);
        categoryComboBox.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        formPanel.add(categoryComboBox, gbc);

        gbc.gridy = 8;
        JLabel descriptionLabel = new JLabel("Description");
        descriptionLabel.setFont(labelFont);
        formPanel.add(descriptionLabel, gbc);

        gbc.gridy = 9;
        JTextArea descriptionArea = new JTextArea(5, 35);
        descriptionArea.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Padding only
        descriptionArea.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        formPanel.add(descriptionArea, gbc);

        gbc.gridy = 10;
        JPanel buttonPanel = new JPanel();

        buttonPanel.setBackground(Color.WHITE);
        JButton saveButton = new JButton("Save");
        styleButton(saveButton);
        buttonPanel.add(saveButton);

        JButton clearButton = new JButton("Clear");
        styleButton(clearButton);
        buttonPanel.add(clearButton);

        formPanel.add(buttonPanel, gbc);
        add(formPanel, BorderLayout.CENTER);
    }

    private static void styleButton(JButton button) {
        button.setBackground(new Color(50, 150, 250));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setPreferredSize(new Dimension(100, 30));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(30, 130, 230));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(50, 150, 250));
            }
        });

    }
}
