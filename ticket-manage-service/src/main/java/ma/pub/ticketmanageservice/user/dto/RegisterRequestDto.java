package ma.pub.ticketmanageservice.user.dto;

import ma.pub.ticketmanageservice.user.enums.RoleEnum;

public class RegisterRequestDto {
    private String username;
    private String password;
    private String confirmPassword;
    private RoleEnum role;

    public RegisterRequestDto() {
    }

    public RegisterRequestDto(String username, String password, String confirmPassword, RoleEnum role) {
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.role = role;
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

    public static final class Builder {
        private String username;
        private String password;
        private String confirmPassword;
        private RoleEnum role;

        private Builder() {
        }

        public static Builder aRegisterRequestDto() {
            return new Builder();
        }

        public Builder withUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder withConfirmPassword(String confirmPassword) {
            this.confirmPassword = confirmPassword;
            return this;
        }

        public Builder withRole(RoleEnum role) {
            this.role = role;
            return this;
        }

        public RegisterRequestDto build() {
            RegisterRequestDto registerRequestDto = new RegisterRequestDto();
            registerRequestDto.setUsername(username);
            registerRequestDto.setPassword(password);
            registerRequestDto.setConfirmPassword(confirmPassword);
            registerRequestDto.setRole(role);
            return registerRequestDto;
        }
    }
}
