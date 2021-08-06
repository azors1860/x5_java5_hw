package exceptions;

public class NotEnoughMoneyException extends Exception {
    public NotEnoughMoneyException(String message) {
        super(message);
    }
    public NotEnoughMoneyException(String message, Throwable e) {
        super(message, e);
    }
}
