package com.bazinga.shoppingcart.exception;

public class BazingaRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 5861310537366287163L;

    public BazingaRuntimeException() {
        super();
    }

    public BazingaRuntimeException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public BazingaRuntimeException(final String message) {
        super(message);
    }

    public BazingaRuntimeException(final Throwable cause) {
        super(cause);
    }
}
