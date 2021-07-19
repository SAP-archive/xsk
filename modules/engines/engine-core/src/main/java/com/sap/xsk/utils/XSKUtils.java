/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2021 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

public class XSKUtils {

  private XSKUtils() {

  }

  public static byte[] objectToByteArray(Object object) throws IOException {
    try (ByteArrayOutputStream bos = new ByteArrayOutputStream(); ObjectOutput out = new ObjectOutputStream(bos)) {
      out.writeObject(object);
      out.flush();

      return bos.toByteArray();
    }
  }

  @SuppressWarnings("unchecked")
  public static <T> T byteArrayToObject(byte[] byteArray) throws IOException, ClassNotFoundException {
    try (ByteArrayInputStream bis = new ByteArrayInputStream(byteArray);
        ObjectInput in = new ObjectInputStream(bis)) {
      return (T) in.readObject();
    }
  }

  public static String convertToFullPath(String filePath) {
    if (!filePath.startsWith("/registry/public")) {
      return "/registry/public" + filePath;
    }
    return filePath;
  }


}
