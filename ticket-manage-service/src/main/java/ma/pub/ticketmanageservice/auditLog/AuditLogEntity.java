package ma.pub.ticketmanageservice.auditlog;

import jakarta.persistence.*;
import ma.pub.ticketmanageservice.ticket.TicketEntity;
import ma.pub.ticketmanageservice.user.UserEntity;

import java.util.UUID;

@Entity(name = "audit_logs")
public class AuditLogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, columnDefinition = "CLOB")
    private String action;

    @ManyToOne(cascade = CascadeType.MERGE)
    private UserEntity user;

    @ManyToOne(cascade = CascadeType.MERGE)
    private TicketEntity ticket;

    public AuditLogEntity() {
    }

    public AuditLogEntity(UUID id, String action, UserEntity user, TicketEntity ticket) {
        this.id = id;
        this.action = action;
        this.user = user;
        this.ticket = ticket;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public TicketEntity getTicket() {
        return ticket;
    }

    public void setTicket(TicketEntity ticket) {
        this.ticket = ticket;
    }

    public static final class Builder {
        private UUID id;
        private String action;
        private UserEntity user;
        private TicketEntity ticket;

        private Builder() {
        }

        public static Builder anAuditLogEntity() {
            return new Builder();
        }

        public Builder withId(UUID id) {
            this.id = id;
            return this;
        }

        public Builder withAction(String action) {
            this.action = action;
            return this;
        }

        public Builder withUser(UserEntity user) {
            this.user = user;
            return this;
        }

        public Builder withTicket(TicketEntity ticket) {
            this.ticket = ticket;
            return this;
        }

        public AuditLogEntity build() {
            AuditLogEntity auditLogEntity = new AuditLogEntity();
            auditLogEntity.setId(id);
            auditLogEntity.setAction(action);
            auditLogEntity.setUser(user);
            auditLogEntity.setTicket(ticket);
            return auditLogEntity;
        }
    }
}
