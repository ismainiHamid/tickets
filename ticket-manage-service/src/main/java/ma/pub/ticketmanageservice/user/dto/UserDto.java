package ma.pub.ticketmanageservice.user.dto;

import ma.pub.ticketmanageservice.user.enums.RoleEnum;

import java.util.UUID;

public class UserDto {
    private UUID id;
    private String username;
    private RoleEnum role;

    public UserDto() {
    }

    public UserDto(UUID id, String username, RoleEnum role) {
        this.id = id;
        this.username = username;
        this.role = role;
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

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

    public static final class Builder {
        private UUID id;
        private String username;
        private RoleEnum role;

        private Builder() {
        }

        public static Builder anUserDto() {
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

        public Builder withRole(RoleEnum role) {
            this.role = role;
            return this;
        }

        public UserDto build() {
            UserDto userDto = new UserDto();
            userDto.setId(id);
            userDto.setUsername(username);
            userDto.setRole(role);
            return userDto;
        }
    }
}
