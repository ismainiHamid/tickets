package ma.tickets.forms;

import ma.tickets.frams.MainFrame;
import ma.tickets.models.Ticket;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class TicketFetchPanel extends JPanel {

    public TicketFetchPanel(MainFrame mainFrame) {
        setLayout(new BorderLayout());
        setName("TicketFetchPanel");

        List<Ticket> tickets = new ArrayList<>();
        TicketTableModel ticketTableModel = new TicketTableModel(tickets);
        JTable ticketTable = new JTable(ticketTableModel);

        JScrollPane scrollPane = new JScrollPane(ticketTable);
        add(scrollPane, BorderLayout.CENTER);
    }

    public static class TicketTableModel extends AbstractTableModel {
        private final List<Ticket> tickets;
        private final String[] columnNames = {"ID", "Title", "Description", "priority", "category", "Status", "createdAt"};

        public TicketTableModel(List<Ticket> tickets) {
            this.tickets = tickets;
        }

        @Override
        public int getRowCount() {
            return tickets.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Ticket ticket = tickets.get(rowIndex);
            return switch (columnIndex) {
                case 0 -> ticket.getId();
                case 1 -> ticket.getTitle();
                case 2 -> ticket.getDescription();
                case 3 -> ticket.getPriority();
                case 4 -> ticket.getCategory();
                case 5 -> ticket.getStatus();
                case 6 -> ticket.getCreatedAt();
                default -> null;
            };
        }

        @Override
        public String getColumnName(int column) {
            return columnNames[column];
        }
    }
}
