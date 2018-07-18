package dao.Exceptions;

public class AlreadyExistException extends Exception {
    public AlreadyExistException() { super(); }
    public AlreadyExistException(String message) { super(message); }
    public AlreadyExistException(String message, Throwable cause) { super(message, cause); }
    public AlreadyExistException(Throwable cause) { super(cause); }
}
