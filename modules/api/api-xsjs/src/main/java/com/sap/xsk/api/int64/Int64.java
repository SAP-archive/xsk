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

public class Int64 {
    private final long value;

    public Int64(long aLong) {
        this.value = aLong;
    }

    public Int64(String value) {
        this.value = Long.parseLong(value);
    }


    public Int64(String hex, int radix) {
        this.value = Long.parseLong(hex, radix);
    }

    public Int64(Int64 int64) {
        this.value = int64.value;
    }

    public long getValue() {
        return value;
    }

    public static long getLow(Int64 number) {
        return (number.getValue() & 0xFFFFFFFFL);
    }

    public static int getHi(Int64 number) {
        return (int) (number.value >> 32);
    }

    public static long join(long high, long low) {;
        return ((long) high << 32) + low;
    }

    public static int compare(Int64 a, Int64 b) {
        return Long.compare(a.value, b.value);
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }
}
