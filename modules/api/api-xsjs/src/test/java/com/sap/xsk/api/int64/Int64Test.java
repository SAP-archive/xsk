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
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Int64Test {
    long value = 9223372036854775802L;
    String hex = "7FFFFFFFFFFFFFFA";
    String valueAsString = "9223372036854775802";

    @Test
    public void joinTest() {
        long sum = Int64.join(-0x12345678, 0x90ABCDEF);
        System.out.println(sum);
        assertEquals(-1311768466735510033L, sum);
    }

    @Test
    public void compareTest() {
        Int64 a = new Int64("9223372036854775802");
        Int64 b = new Int64("7FFFFFFFFFFFFFFA", 16);
        int result = Int64.compare(a, b);
        assertEquals(0, result);
    }

    @Test
    public void getValueFromHEX() {
        Int64 number = new Int64(hex, 16);
        long numLow = Int64.getLow(number);
        long numHi = Int64.getHi(number);
        long join = Int64.join(numHi, numLow);
        assertEquals(number.getValue(), join);
    }

    @Test
    public void getValueFromStringPositive() {
        Int64 int64 = new Int64(value);
        long numLow = Int64.getLow(int64);
        long numHi = Int64.getHi(int64);
        long join = Int64.join(numHi, numLow);
        assertEquals(int64.getValue(), join);
    }


    @Test
    public void getValueFromStringNegative() {
        Int64 int64 = new Int64(valueAsString);
        long numLow = Int64.getLow(int64);
        int numHi = Int64.getHi(int64);
        long join = Int64.join(numHi, numLow);
        assertEquals(int64.getValue(), join);
    }
}

