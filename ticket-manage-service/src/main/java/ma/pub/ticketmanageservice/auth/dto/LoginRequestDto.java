package ma.pub.ticketmanageservice.auth.dto;

public class LoginRequestDto {
    private String username;
    private String password;

    public LoginRequestDto(){}

    public LoginRequestDto(String username, String password) {
        this.username = username;
        this.password = password;
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


    public static final class Builder {
        private String username;
        private String password;

        private Builder() {
        }

        public static Builder aLoginRequest() {
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

        public LoginRequestDto build() {
            LoginRequestDto loginRequestDto = new LoginRequestDto();
            loginRequestDto.setUsername(username);
            loginRequestDto.setPassword(password);
            return loginRequestDto;
        }
    }
}
