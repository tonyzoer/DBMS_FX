package itlab.module.exceptions;

public class UnavailableCharacterInValue extends Exception {
    public UnavailableCharacterInValue() {
    }

    public UnavailableCharacterInValue(String message) {
        super(message);
    }

    public UnavailableCharacterInValue(String message, Throwable cause) {
        super(message, cause);
    }

    public UnavailableCharacterInValue(Throwable cause) {
        super(cause);
    }

    public UnavailableCharacterInValue(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
