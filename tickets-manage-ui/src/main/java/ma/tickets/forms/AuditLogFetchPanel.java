package ma.tickets.forms;

import ma.tickets.frams.MainFrame;

import javax.swing.*;
import java.awt.*;

public class AuditLogFetchPanel extends JPanel {
    public AuditLogFetchPanel(MainFrame mainFrame) {
        setLayout(new BorderLayout());
        setName("AuditLogFetchPanel");

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
    }
}
