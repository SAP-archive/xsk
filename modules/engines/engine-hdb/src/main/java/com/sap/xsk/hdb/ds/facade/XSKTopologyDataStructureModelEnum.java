/*
 * Copyright (c) 2022 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2022 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.hdb.ds.facade;

public enum XSKTopologyDataStructureModelEnum {
	
	EXECUTE_SCHEMA_CREATE, EXECUTE_TABLE_TYPE_CREATE, EXECUTE_TABLE_CREATE, EXECUTE_VIEW_CREATE,
	EXECUTE_SYNONYM_CREATE, EXECUTE_HDBDD_CREATE, EXECUTE_SEQUENCE_CREATE, EXECUTE_PROCEDURE_CREATE,
	EXECUTE_TABLE_FUNCTION_CREATE, EXECUTE_SCALAR_FUNCTION_CREATE,
	
	EXECUTE_PROCEDURE_DROP, EXECUTE_TABLE_FUNCTION_DROP, EXECUTE_SCALAR_FUNCTION_DROP, EXECUTE_SYNONYM_DROP,
	EXECUTE_VIEW_DROP, EXECUTE_TABLE_DROP, EXECUTE_TABLE_TYPE_DROP, EXECUTE_SCHEMA_DROP

}
