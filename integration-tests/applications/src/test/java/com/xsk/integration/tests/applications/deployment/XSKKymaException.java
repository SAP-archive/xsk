package com.xsk.integration.tests.applications.deployment;

public class XSKKymaException extends RuntimeException {
    public XSKKymaException(String message) {
        super(message);
    }

    public XSKKymaException(String message, Throwable cause) {
        super(message, cause);
    }
}
