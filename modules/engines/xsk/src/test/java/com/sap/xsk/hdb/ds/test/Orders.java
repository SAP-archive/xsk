/**
 * Copyright (c) 2010-2018 SAP and others.
 */
package com.sap.xsk.hdb.ds.test;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Class Orders
 *
 */
@Table(name = "ORDERS")
public class Orders {
	
	/** The id. */
	@Id
	@GeneratedValue
	@Column(name = "KEY", columnDefinition = "VARCHAR", length=32, nullable = false)
	private int id;

}
