package ma.pub.ticketmanageservice.auditlog;

import jakarta.persistence.*;
import ma.pub.ticketmanageservice.ticket.TicketEntity;

@Entity(name = "audit_logs")
public class AuditLogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "CLOB")
    private String action;

    @ManyToOne(cascade = CascadeType.MERGE)
    private TicketEntity ticket;

    public AuditLogEntity() {
    }

    public AuditLogEntity(Long id, String action, TicketEntity ticket) {
        this.id = id;
        this.action = action;
        this.ticket = ticket;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public TicketEntity getTicket() {
        return ticket;
    }

    public void setTicket(TicketEntity ticket) {
        this.ticket = ticket;
    }

    public static final class Builder {
        private Long id;
        private String action;
        private TicketEntity ticket;

        private Builder() {
        }

        public static Builder anAuditLogEntity() {
            return new Builder();
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withAction(String action) {
            this.action = action;
            return this;
        }

        public Builder withTicket(TicketEntity ticket) {
            this.ticket = ticket;
            return this;
        }

        public AuditLogEntity build() {
            return new AuditLogEntity(id, action, ticket);
        }
    }
}
