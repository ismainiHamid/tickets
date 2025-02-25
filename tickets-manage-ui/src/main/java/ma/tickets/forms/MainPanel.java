package ma.tickets.forms;

import ma.tickets.frams.MainFrame;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class MainPanel extends JPanel {
    ;
    private final JLabel userLabel;
    private final JButton audit_logsBtn;

    public MainPanel(MainFrame mainFrame) {
        setLayout(new BorderLayout());
        setName("MainPanel");

        JPanel navePanel = new JPanel(new GridBagLayout());
        navePanel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 0, 0);

        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        leftPanel.setBackground(Color.WHITE);

        JButton addTicketBtn = new JButton("Add ticket");
        styleButton(addTicketBtn,
                new Color(255, 255, 255),
                new Color(230, 230, 230),
                Color.BLACK,
                120
        );
        leftPanel.add(addTicketBtn);
        addTicketBtn.addActionListener(e -> {
            removeAll();
            add(navePanel, BorderLayout.NORTH);
            add(new TicketFormPanel(mainFrame), BorderLayout.CENTER);
            revalidate();
            repaint();
        });

        JButton allTicketsBtn = new JButton("All ticket");
        styleButton(allTicketsBtn,
                new Color(255, 255, 255),
                new Color(230, 230, 230),
                Color.BLACK,
                120
        );
        leftPanel.add(allTicketsBtn);
        allTicketsBtn.addActionListener(e -> {
            removeAll();
            add(navePanel, BorderLayout.NORTH);
            add(new TicketFetchPanel(mainFrame), BorderLayout.CENTER);
            revalidate();
            repaint();
        });

        audit_logsBtn = new JButton("Audit logs");
        styleButton(audit_logsBtn,
                new Color(255, 255, 255),
                new Color(230, 230, 230),
                Color.BLACK,
                120
        );
        leftPanel.add(audit_logsBtn);
        audit_logsBtn.addActionListener(e -> {
            removeAll();
            add(navePanel, BorderLayout.NORTH);
            add(new AuditLogFetchPanel(mainFrame), BorderLayout.CENTER);
            revalidate();
            repaint();
        });

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        rightPanel.setBackground(Color.WHITE);

        userLabel = new JLabel();
        userLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));

        JButton logoutButton = new JButton("Logout");
        styleButton(logoutButton,
                new Color(50, 150, 250),
                new Color(30, 130, 230),
                Color.WHITE,
                50
        );

        rightPanel.add(userLabel);
        rightPanel.add(logoutButton);

        gbc.gridx = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        navePanel.add(leftPanel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        navePanel.add(rightPanel, gbc);

        Border bottomBorder = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY);
        navePanel.setBorder(bottomBorder);

        add(navePanel, BorderLayout.NORTH);
        add(new TicketFormPanel(mainFrame), BorderLayout.CENTER);
    }

    public void updateUserInfo(String username, String role) {
        userLabel.setText(username);
        if (Objects.nonNull(role))
            if (!role.equals("ROLE_IT_SUPPORT"))
                audit_logsBtn.setVisible(false);

        revalidate();
        repaint();
    }

    private static void styleButton(JButton button,
                                    Color bgColor,
                                    Color hoverColor,
                                    Color textColor,
                                    int width) {
        button.setBackground(bgColor);
        button.setForeground(textColor);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setPreferredSize(new Dimension(width, 40));

        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                button.setBackground(hoverColor);
            }

            public void mouseExited(MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });
    }
}
