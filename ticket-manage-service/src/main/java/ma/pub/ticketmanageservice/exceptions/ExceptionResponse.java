package ma.pub.ticketmanageservice.exceptions;

import java.time.LocalDateTime;

public class ExceptionResponse {
    private short code;
    private String error;
    private String errorMessage;
    private LocalDateTime timestamp;

    public ExceptionResponse() {
    }

    public ExceptionResponse(short code, String error, String errorMessage, LocalDateTime timestamp) {
        this.code = code;
        this.error = error;
        this.errorMessage = errorMessage;
        this.timestamp = timestamp;
    }

    public short getCode() {
        return code;
    }

    public void setCode(short code) {
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public static final class Builder {
        private short code;
        private String error;
        private String errorMessage;
        private LocalDateTime timestamp;

        private Builder() {
        }

        public static Builder anExceptionResponse() {
            return new Builder();
        }

        public Builder withCode(short code) {
            this.code = code;
            return this;
        }

        public Builder withError(String error) {
            this.error = error;
            return this;
        }

        public Builder withErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
            return this;
        }

        public Builder withTimestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public ExceptionResponse build() {
            return new ExceptionResponse(code, error, errorMessage, timestamp);
        }
    }
}
