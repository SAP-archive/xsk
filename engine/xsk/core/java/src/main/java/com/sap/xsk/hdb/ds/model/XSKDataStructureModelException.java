/**
 * Copyright (c) 2010-2018 SAP
 */
package com.sap.xsk.hdb.ds.model;

/**
 * Specialized exception for the data models.
 */
public class XSKDataStructureModelException extends Exception {

	private static final long serialVersionUID = 8008932847050301958L;

	/**
	 * The default constructor.
	 */
	public XSKDataStructureModelException() {
		super();
	}

	/**
	 * Overloaded constructor.
	 *
	 * @param message            the message
	 * @param cause            the cause
	 * @param enableSuppression            whether to enable suppression
	 * @param writableStackTrace            whether to enable writable stack trace
	 */
	public XSKDataStructureModelException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * Overloaded constructor.
	 *
	 * @param message            the message
	 * @param cause            the cause
	 */
	public XSKDataStructureModelException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Overloaded constructor.
	 *
	 * @param message            the message
	 */
	public XSKDataStructureModelException(String message) {
		super(message);
	}

	/**
	 * Overloaded constructor.
	 *
	 * @param cause            the cause
	 */
	public XSKDataStructureModelException(Throwable cause) {
		super(cause);
	}

}
