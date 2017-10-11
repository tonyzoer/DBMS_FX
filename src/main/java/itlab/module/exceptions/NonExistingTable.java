package itlab.module.exceptions;

public class NonExistingTable extends Exception {
    public NonExistingTable() {
        super();
    }

    public NonExistingTable(String message) {
        super(message);
    }

    public NonExistingTable(String message, Throwable cause) {
        super(message, cause);
    }

    public NonExistingTable(Throwable cause) {
        super(cause);
    }

    protected NonExistingTable(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
