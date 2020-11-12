/**
 * Copyright (c) 2010-2018 SAP
 */
package com.sap.xsk.hdb.ds.model.hdbtable;

/**
 * The Data Structure Table Constraint Check Model.
 */
public class XSKDataStructureHDBTableConstraintCheckModel extends XSKDataStructureHDBTableConstraintModel {

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
