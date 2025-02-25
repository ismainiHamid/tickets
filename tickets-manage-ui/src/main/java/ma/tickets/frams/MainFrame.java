package ma.tickets.frams;

import ma.tickets.forms.LoginFormPanel;
import ma.tickets.forms.MainPanel;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private final CardLayout cardLayout;
    private final JPanel jpanel;
    private MainPanel mainPanel;

    public MainFrame() {
        setTitle("Manage tickets");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        jpanel = new JPanel(cardLayout);

        LoginFormPanel loginFormPanel = new LoginFormPanel(this);
        mainPanel = new MainPanel(this);

        jpanel.add(loginFormPanel, "Login");
        jpanel.add(mainPanel, "Main");

        add(jpanel);

        // Show the login panel initially
        cardLayout.show(jpanel, "Login");
    }

    public void showPanel(String panelName) {
        cardLayout.show(jpanel, panelName);
    }

    public JPanel getPanel(String panelName) {
        if ("Main".equals(panelName)) {
            return mainPanel;
        }
        return null;
    }
}
