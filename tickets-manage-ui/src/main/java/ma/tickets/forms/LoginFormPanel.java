package ma.tickets.forms;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import ma.tickets.frams.MainFrame;
import ma.tickets.models.LoginRequest;
import ma.tickets.services.AuthServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class LoginFormPanel extends JPanel {
    private final AuthServiceImpl authService;
    private final ObjectMapper objectMapper;

    public LoginFormPanel(MainFrame parentFrame) {
        this.authService = new AuthServiceImpl();
        this.objectMapper = new ObjectMapper();

        setLayout(new GridBagLayout());
        setBackground(Color.WHITE);
        setName("LoginFormPanel");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 5, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;

        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        JLabel userLabel = new JLabel("Username:");
        userLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        add(userLabel, gbc);

        gbc.gridy++;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JTextField usernameField = new JTextField(35);
        usernameField.setPreferredSize(new Dimension(200, 30));
        usernameField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        usernameField.setBorder(BorderFactory.createCompoundBorder(
                usernameField.getBorder(),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        add(usernameField, gbc);

        gbc.gridy++;
        gbc.fill = GridBagConstraints.NONE;
        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        add(passLabel, gbc);

        gbc.gridy++;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JPasswordField passwordField = new JPasswordField(35);
        passwordField.setPreferredSize(new Dimension(200, 30));
        passwordField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        passwordField.setBorder(BorderFactory.createCompoundBorder(
                passwordField.getBorder(),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        add(passwordField, gbc);

        gbc.gridy++;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JButton loginButton = new JButton("Login");
        loginButton.setBackground(new Color(50, 150, 250));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setBorderPainted(false);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setPreferredSize(new Dimension(100, 30));

        loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loginButton.setBackground(new Color(30, 130, 230));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                loginButton.setBackground(new Color(50, 150, 250));
            }
        });

        loginButton.addActionListener(e -> parentFrame.showPanel("Ticket"));

        add(loginButton, gbc);

        loginButton.addActionListener(event -> {
            try {
                String sessionId = this.authService.authenticate(LoginRequest.Builder.aLoginRequest()
                        .withUsername(usernameField.getText())
                        .withPassword(passwordField.getText())
                        .build()
                );

                if (Objects.nonNull(sessionId)) {
                    JsonNode rootNode = objectMapper.readTree(
                            this.authService.getCurrentUser(sessionId)
                    );

                    MainPanel mainPanel = (MainPanel) parentFrame.getPanel("Main");
                    if (mainPanel != null) {
                        mainPanel.updateUserInfo(
                                rootNode.path("username").asText(),
                                rootNode.path("authorities").get(0).path("authority").asText()
                        );                        parentFrame.showPanel("Main");
                    } else {
                        System.out.println("MainPanel not found");
                    }

                } else
                    JOptionPane.showMessageDialog(parentFrame, "Invalid Login!", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}
