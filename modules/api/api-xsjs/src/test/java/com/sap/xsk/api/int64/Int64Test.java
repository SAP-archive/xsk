///*
// * Copyright (c) 2022 SAP SE or an SAP affiliate company and XSK contributors
// *
// * All rights reserved. This program and the accompanying materials
// * are made available under the terms of the Apache License, v2.0
// * which accompanies this distribution, and is available at
// * http://www.apache.org/licenses/LICENSE-2.0
// *
// * SPDX-FileCopyrightText: 2022 SAP SE or an SAP affiliate company and XSK contributors
// * SPDX-License-Identifier: Apache-2.0
// */
///*
// * Copyright (c) 2022 SAP SE or an SAP affiliate company and XSK contributors
// *
// * All rights reserved. This program and the accompanying materials
// * are made available under the terms of the Apache License, v2.0
// * which accompanies this distribution, and is available at
// * http://www.apache.org/licenses/LICENSE-2.0
// *
// * SPDX-FileCopyrightText: 2022 SAP SE or an SAP affiliate company and XSK contributors
// * SPDX-License-Identifier: Apache-2.0
// */
//package com.sap.xsk.api.int64;
//
//import org.junit.Test;
//
//import static org.junit.Assert.assertEquals;
//
//public class Int64Test {
//    String numberAsString = "1123234345123124341";
//
//    @Test
//    public void getValueFromStringPositive (){
//        long number = new Int64(numberAsString);
//        int numLow = Int64.getLow(number);
//        long numHi = Int64.getHi(number);
//        long join = Int64.join(numHi, numLow);
//        assertEquals(join,number);
//    }
//
//    @Test
//    public void getValueFromStringNegative (){
//        long number = Int64.getValue("-"+numberAsString);
//        int numLow = Int64.getLow(number);
//        long numHi = Int64.getHi(number);
//        long join = Int64.join(numHi, numLow);
//        assertEquals(join,number);
//    }
//
//    @Test
//    public void compareTest(){
//        int max = (int) Int64.compare(new Int64(1232352123123L),new Int64(12323521231123L));
//        assertEquals(1232352123123L,max);
//    }
//
//
//
//
//}
