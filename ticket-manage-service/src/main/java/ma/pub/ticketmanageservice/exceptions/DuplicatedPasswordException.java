package ma.pub.ticketmanageservice.exceptions;

public class DuplicatedPasswordException extends RuntimeException {
    public DuplicatedPasswordException(String message) {
        super(message);
    }
}
