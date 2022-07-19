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
package com.sap.xsk.api.int64;

import static java.lang.Long.parseLong;

public class Int64 {

    public static long getValue(String valueAsString){
        return parseLong(valueAsString);
    }

    public static long getLow(Long number){
        return number & 0xFFFFFFFFL;
    }
    public static long getHi(Long number){
        return number >> 32;
    }

    public static long join (long high, long low){
        return (high << 32) + low;
    }
    public static long compare(long a, long b){
        return Math.max(a, b);
    }
}
