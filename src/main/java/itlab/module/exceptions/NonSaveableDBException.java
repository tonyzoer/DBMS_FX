package itlab.module.exceptions;

public class NonSaveableDBException extends Exception {
    public NonSaveableDBException() {
        super();
    }

    public NonSaveableDBException(String message) {
        super(message);
    }

    public NonSaveableDBException(String message, Throwable cause) {
        super(message, cause);
    }

    public NonSaveableDBException(Throwable cause) {
        super(cause);
    }

    protected NonSaveableDBException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
