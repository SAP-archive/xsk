/**
 * Copyright (c) 2010-2018 SAP
 */
package com.sap.xsk.hdb.ds.model;

/**
 * The Data Structure Table Constraint Check Model.
 */
public class XSKDataStructureTableConstraintCheckModel extends XSKDataStructureTableConstraintModel {

	private String expression;

	/**
	 * Gets the expression.
	 *
	 * @return the expression
	 */
	public String getExpression() {
		return expression;
	}

	/**
	 * Sets the expression.
	 *
	 * @param expression the new expression
	 */
	public void setExpression(String expression) {
		this.expression = expression;
	}

}
