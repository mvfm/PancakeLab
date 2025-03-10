package org.pancakelab.exception;

public class InvalidQuantityException extends ValidationException {
    private final int quantity;

    public InvalidQuantityException(int quantity) {
        super("It is impossible to add zero or a negative number of pancakes: %d".formatted(quantity));

        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }
}
