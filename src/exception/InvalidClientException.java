package exception;

public class InvalidClientException extends Exception {
    public InvalidClientException() {
        super("Invalid client details provided.");
    }

    public InvalidClientException(String message) {
        super(message);
    }
}
