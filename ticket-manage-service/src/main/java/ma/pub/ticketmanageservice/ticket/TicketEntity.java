package ma.pub.ticketmanageservice.ticket;

import jakarta.persistence.*;
import ma.pub.ticketmanageservice.ticket.enums.Category;
import ma.pub.ticketmanageservice.ticket.enums.Priority;
import ma.pub.ticketmanageservice.ticket.enums.Status;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity(name = "tickets")
public class TicketEntity {
    @Id
    private String id;

    @Column(nullable = false, unique = true, length = 50)
    private String title;

    @Column(nullable = false, columnDefinition = "CLOB")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Priority priority;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public TicketEntity() {
    }

    public TicketEntity(String id, String title, String description, Priority priority, Category category) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.category = category;
        this.status = Status.NEW;
        this.createdAt = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        private String id;
        private String title;
        private String description;
        private Priority priority;
        private Category category;
        private Status status;
        private LocalDateTime createdAt;

        private Builder() {
        }

        public static Builder aTicketEntity() {
            return new Builder();
        }

        public Builder withId(String id) {
            this.id = id;
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

        public TicketEntity build() {
            TicketEntity ticketEntity = new TicketEntity(id, title, description, priority, category);
            ticketEntity.createdAt = this.createdAt;
            ticketEntity.status = this.status;
            return ticketEntity;
        }
    }
}
