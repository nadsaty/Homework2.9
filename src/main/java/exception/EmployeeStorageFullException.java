package exception;

public class EmployeeStorageFullException extends RuntimeException {
    public EmployeeStorageFullException(String message) {
        super(message);
    }
}