package com.engineer.lrogozinski.exceptions;

public class ServiceException extends RuntimeException {
    public ServiceException() {
        super();
    }

    public ServiceException(final String message) {
        super(message);
    }

    public ServiceException(final String message, Throwable e) {
        super(message, e);
    }
}
