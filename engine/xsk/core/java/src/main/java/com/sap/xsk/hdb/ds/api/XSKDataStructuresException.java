/**
 * Copyright (c) 2010-2018 SAP
 */
package com.sap.xsk.hdb.ds.api;

/**
 * The Data Structures Exception.
 */
public class XSKDataStructuresException extends Exception {

	private static final long serialVersionUID = 5800180600419241248L;

	/**
	 * Instantiates a new data structures exception.
	 */
	public XSKDataStructuresException() {
		super();
	}

	/**
	 * Instantiates a new data structures exception.
	 *
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 * @param enableSuppression
	 *            the enable suppression
	 * @param writableStackTrace
	 *            the writable stack trace
	 */
	public XSKDataStructuresException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * Instantiates a new data structures exception.
	 *
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 */
	public XSKDataStructuresException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new data structures exception.
	 *
	 * @param message
	 *            the message
	 */
	public XSKDataStructuresException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new data structures exception.
	 *
	 * @param cause
	 *            the cause
	 */
	public XSKDataStructuresException(Throwable cause) {
		super(cause);
	}

}
