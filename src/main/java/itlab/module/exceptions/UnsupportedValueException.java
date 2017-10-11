package itlab.module.exceptions;

public class UnsupportedValueException extends Exception {
    public UnsupportedValueException() {
        super();
    }

    public UnsupportedValueException(String message) {
        super(message);
    }

    public UnsupportedValueException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnsupportedValueException(Throwable cause) {
        super(cause);
    }

    protected UnsupportedValueException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
