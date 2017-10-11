package itlab.module.exceptions;

public class NonExistingRow extends Exception {
    public NonExistingRow() {
        super();
    }

    public NonExistingRow(String message) {
        super(message);
    }

    public NonExistingRow(String message, Throwable cause) {
        super(message, cause);
    }

    public NonExistingRow(Throwable cause) {
        super(cause);
    }

    protected NonExistingRow(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
