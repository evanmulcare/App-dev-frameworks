package ie.spring.jdbc.exceptions;

public class DataConflictException extends Exception {
    public DataConflictException(String message) {
        super(message);
    }
}