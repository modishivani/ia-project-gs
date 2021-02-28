package db;

public class ActivityInUseException extends DatabaseException {
    public ActivityInUseException() {
        super();
    }

    public ActivityInUseException(String message) {
        super(message);
    }

    public ActivityInUseException(String message,  Throwable cause) {
        super(message, cause);
    }
}