package com.appgros.common.exception;

/**
 * This exception is thrown when an object cannot be created.
 */
public class CreateException extends ApplicationException {

    public CreateException() {
    }

    public CreateException(final String message) {
        super(message);
    }
}
