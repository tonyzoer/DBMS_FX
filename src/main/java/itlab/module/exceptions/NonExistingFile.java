package itlab.module.exceptions;

public class NonExistingFile extends Exception {
    public NonExistingFile() {
        super();
    }

    public NonExistingFile(String message) {
        super(message);
    }

    public NonExistingFile(String message, Throwable cause) {
        super(message, cause);
    }

    public NonExistingFile(Throwable cause) {
        super(cause);
    }

    protected NonExistingFile(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
