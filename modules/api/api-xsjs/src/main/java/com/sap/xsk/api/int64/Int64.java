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
    private long value;

    public Int64(String value){
      this.value = getValue(value);
    }
    public Int64(int value){
       this.value = getValue(String.valueOf(value));
    }
    public Int64(Int64 int64){
      this.value = int64.value;
    }

    public long getValue(String valueAsString) {
        return parseLong(valueAsString);
    }


    public int getLow(Int64 number) {
        return (int) (number.value & 0xFFFFFFFFL);
    }

    public int getHi(Int64 number) {
        return (int) (number.value >> 32);
    }

    public long join(int high, int low) {
        return ((long) high << 32) + low;
    }

    public long compare(Int64 a, Int64 b) {
        return Long.compare(a.value, b.value);
    }
}
