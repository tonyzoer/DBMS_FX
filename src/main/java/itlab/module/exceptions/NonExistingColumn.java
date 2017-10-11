package itlab.module.exceptions;

public class NonExistingColumn extends Exception {
    public NonExistingColumn() {
        super();
    }

    public NonExistingColumn(String message) {
        super(message);
    }

    public NonExistingColumn(String message, Throwable cause) {
        super(message, cause);
    }

    public NonExistingColumn(Throwable cause) {
        super(cause);
    }

    protected NonExistingColumn(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
