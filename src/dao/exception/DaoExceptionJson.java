package dao.exception;

public class DaoExceptionJson extends Exception {

    public DaoExceptionJson(String message) {
        super(message);
    }

    public DaoExceptionJson(String message, Throwable e) {
        super(message, e);
    }
}
