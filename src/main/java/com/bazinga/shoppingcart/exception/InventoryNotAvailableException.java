package com.bazinga.shoppingcart.exception;

public class InventoryNotAvailableException extends RuntimeException {

    public InventoryNotAvailableException() {
        super();
    }

    public InventoryNotAvailableException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public InventoryNotAvailableException(final String message) {
        super(message);
    }

    public InventoryNotAvailableException(final Throwable cause) {
        super(cause);
    }
}
