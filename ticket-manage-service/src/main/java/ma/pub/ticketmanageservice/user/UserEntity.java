package ma.pub.ticketmanageservice.user;

import jakarta.persistence.*;
import ma.pub.ticketmanageservice.ticket.TicketEntity;
import ma.pub.ticketmanageservice.user.enums.RoleEnum;

import java.util.List;
import java.util.UUID;

@Entity(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleEnum role;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<TicketEntity> tickets;

    public UserEntity() {
    }

    public UserEntity(UUID id, String username, String password, RoleEnum role, List<TicketEntity> tickets) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.tickets = tickets;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

    public List<TicketEntity> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketEntity> tickets) {
        this.tickets = tickets;
    }


    public static final class Builder {
        private UUID id;
        private String username;
        private String password;
        private RoleEnum role;
        private List<TicketEntity> tickets;

        private Builder() {
        }

        public static Builder anUserEntity() {
            return new Builder();
        }

        public Builder withId(UUID id) {
            this.id = id;
            return this;
        }

        public Builder withUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder withRole(RoleEnum role) {
            this.role = role;
            return this;
        }

        public Builder withTickets(List<TicketEntity> tickets) {
            this.tickets = tickets;
            return this;
        }

        public UserEntity build() {
            UserEntity userEntity = new UserEntity();
            userEntity.setId(id);
            userEntity.setUsername(username);
            userEntity.setPassword(password);
            userEntity.setRole(role);
            userEntity.setTickets(tickets);
            return userEntity;
        }
    }
}
