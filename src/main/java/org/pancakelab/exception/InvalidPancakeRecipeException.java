package org.pancakelab.exception;

public class InvalidPancakeRecipeException extends ValidationException {
    public InvalidPancakeRecipeException() {
    }

    public InvalidPancakeRecipeException(String message) {
        super(message);
    }

    public InvalidPancakeRecipeException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidPancakeRecipeException(Throwable cause) {
        super(cause);
    }

    public InvalidPancakeRecipeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
