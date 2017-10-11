package itlab.module.exceptions;

public class TableAlreadyExsists extends Exception {
    public TableAlreadyExsists() {
        super();
    }

    public TableAlreadyExsists(String message) {
        super(message);
    }

    public TableAlreadyExsists(String message, Throwable cause) {
        super(message, cause);
    }

    public TableAlreadyExsists(Throwable cause) {
        super(cause);
    }

    protected TableAlreadyExsists(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
