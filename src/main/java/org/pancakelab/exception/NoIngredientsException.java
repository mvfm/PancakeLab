package org.pancakelab.exception;

public class NoIngredientsException extends ValidationException {
    public NoIngredientsException() {
    }

    public NoIngredientsException(String message) {
        super(message);
    }

    public NoIngredientsException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoIngredientsException(Throwable cause) {
        super(cause);
    }

    public NoIngredientsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
