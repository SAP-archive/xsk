package com.sap.xsk.hdbti.api;

public class XSKTableImportException extends Exception {

    public XSKTableImportException() {
    }

    public XSKTableImportException(String message) {
        super(message);
    }

    public XSKTableImportException(String message, Throwable cause) {
        super(message, cause);
    }

    public XSKTableImportException(Throwable cause) {
        super(cause);
    }
}
