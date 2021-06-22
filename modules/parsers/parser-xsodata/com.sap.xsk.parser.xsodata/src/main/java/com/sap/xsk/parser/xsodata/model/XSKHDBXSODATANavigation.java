/*
 * Copyright (c) 2019-2021 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2019-2021 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.parser.xsodata.model;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * Representing association property of a service. For example:
 * <pre>{@code
 * service {
 *      "namespace.name::customer" as "Customers"
 *          navigates ("Orders_Customers" as "HisOrders");
 *      "namespace.name::order" as "Orders";
 *      association "Orders_Customers" with referential constraint
 *           dependent  "Customers"("OrderID") multiplicity "*"
 *           principal "Orders"("ID") multiplicity "1";
 *  }
 * }</pre>
 * In the example, the association is "Orders_Customers" and the alias is "HisOrders".
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Accessors(chain = true)
public class XSKHDBXSODATANavigation {

    private String association;
    private String aliasNavigation;
    private XSKHDBXSODATABindingType fromBindingType;
}
