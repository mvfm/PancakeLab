package org.pancakelab.exception;

public class NoPancakesInOrderException extends ValidationException {
    public NoPancakesInOrderException() {
    }

    public NoPancakesInOrderException(String message) {
        super(message);
    }

    public NoPancakesInOrderException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoPancakesInOrderException(Throwable cause) {
        super(cause);
    }

    public NoPancakesInOrderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
