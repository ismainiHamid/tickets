package ma.pub.ticketmanageservice.ticket.dto;

import ma.pub.ticketmanageservice.ticket.enums.Category;
import ma.pub.ticketmanageservice.ticket.enums.Priority;
import ma.pub.ticketmanageservice.ticket.enums.Status;

import java.time.LocalDateTime;
import java.util.UUID;

public class TicketDto {
    private UUID id;
    private String ticketId;
    private String title;
    private String description;
    private Priority priority;
    private Category category;
    private Status status;
    private LocalDateTime createdAt;

    public TicketDto() {
    }

    public TicketDto(UUID id, String ticketId, String title, String description, Priority priority, Category category, Status status, LocalDateTime createdAt) {
        this.id = id;
        this.ticketId = ticketId;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.category = category;
        this.status = status;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public static final class Builder {
        private UUID id;
        private String ticketId;
        private String title;
        private String description;
        private Priority priority;
        private Category category;
        private Status status;
        private LocalDateTime createdAt;

        private Builder() {
        }

        public static Builder aTicketDto() {
            return new Builder();
        }

        public Builder withId(UUID id) {
            this.id = id;
            return this;
        }

        public Builder withTicketId(String ticketId) {
            this.ticketId = ticketId;
            return this;
        }

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withPriority(Priority priority) {
            this.priority = priority;
            return this;
        }

        public Builder withCategory(Category category) {
            this.category = category;
            return this;
        }

        public Builder withStatus(Status status) {
            this.status = status;
            return this;
        }

        public Builder withCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public TicketDto build() {
            TicketDto ticketDto = new TicketDto();
            ticketDto.setId(id);
            ticketDto.setTicketId(ticketId);
            ticketDto.setTitle(title);
            ticketDto.setDescription(description);
            ticketDto.setPriority(priority);
            ticketDto.setCategory(category);
            ticketDto.setStatus(status);
            ticketDto.setCreatedAt(createdAt);
            return ticketDto;
        }
    }
}
