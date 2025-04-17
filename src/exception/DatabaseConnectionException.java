package exception;

public class DatabaseConnectionException extends Exception {
    public DatabaseConnectionException() {
        super("Error establishing a connection to the database.");
    }

    public DatabaseConnectionException(String message) {
        super(message);
    }
}
