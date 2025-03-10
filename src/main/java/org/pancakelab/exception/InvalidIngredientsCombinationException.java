package org.pancakelab.exception;

public class InvalidIngredientsCombinationException extends Exception {
    public InvalidIngredientsCombinationException() {
    }

    public InvalidIngredientsCombinationException(String message) {
        super(message);
    }

    public InvalidIngredientsCombinationException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidIngredientsCombinationException(Throwable cause) {
        super(cause);
    }

    public InvalidIngredientsCombinationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
