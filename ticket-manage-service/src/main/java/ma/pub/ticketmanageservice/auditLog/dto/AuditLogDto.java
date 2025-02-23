package ma.pub.ticketmanageservice.auditlog.dto;

import java.util.UUID;

public class AuditLogDto {
    private UUID id;
    private String action;
    private String username;
    private String ticketId;

    public AuditLogDto() {
    }

    public AuditLogDto(UUID id, String action, String username, String ticketId) {
        this.id = id;
        this.action = action;
        this.username = username;
        this.ticketId = ticketId;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public static final class Builder {
        private UUID id;
        private String action;
        private String username;
        private String ticketId;

        private Builder() {
        }

        public static Builder anAuditLogDto() {
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

        public Builder withUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder withTicketId(String ticketId) {
            this.ticketId = ticketId;
            return this;
        }

        public AuditLogDto build() {
            AuditLogDto auditLogDto = new AuditLogDto();
            auditLogDto.setId(id);
            auditLogDto.setAction(action);
            auditLogDto.setUsername(username);
            auditLogDto.setTicketId(ticketId);
            return auditLogDto;
        }
    }
}
