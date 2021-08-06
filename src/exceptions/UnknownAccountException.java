package exceptions;

public class UnknownAccountException extends Exception {
    public UnknownAccountException(String message) {
        super(message);
    }
    public UnknownAccountException(String message, Throwable e) {
        super(message, e);
    }
}
