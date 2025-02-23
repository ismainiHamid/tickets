package ma.pub.ticketmanageservice.ticket;

import jakarta.persistence.*;
import ma.pub.ticketmanageservice.ticket.enums.Category;
import ma.pub.ticketmanageservice.ticket.enums.Priority;
import ma.pub.ticketmanageservice.ticket.enums.Status;
import ma.pub.ticketmanageservice.user.UserEntity;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "tickets")
public class TicketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String ticketId;

    @Column(nullable = false, length = 50)
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

    @ManyToOne(cascade = CascadeType.MERGE)
    private UserEntity user;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public TicketEntity() {
    }

    public TicketEntity(UUID id, String ticketId, String title, String description, Priority priority, Category category, Status status, UserEntity user, LocalDateTime createdAt) {
        this.id = id;
        this.ticketId = ticketId;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.category = category;
        this.status = status;
        this.user = user;
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

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
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
        private UserEntity user;
        private LocalDateTime createdAt;

        private Builder() {
        }

        public static Builder aTicketEntity() {
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

        public Builder withUser(UserEntity user) {
            this.user = user;
            return this;
        }

        public Builder withCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public TicketEntity build() {
            TicketEntity ticketEntity = new TicketEntity();
            ticketEntity.setId(id);
            ticketEntity.setTicketId(ticketId);
            ticketEntity.setTitle(title);
            ticketEntity.setDescription(description);
            ticketEntity.setPriority(priority);
            ticketEntity.setCategory(category);
            ticketEntity.setStatus(status);
            ticketEntity.setUser(user);
            ticketEntity.setCreatedAt(createdAt);
            return ticketEntity;
        }
    }
}
