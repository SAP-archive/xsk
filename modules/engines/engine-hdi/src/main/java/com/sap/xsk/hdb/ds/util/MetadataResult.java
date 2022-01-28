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
package com.sap.xsk.hdb.ds.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class MetadataResult {

    public final String path;
    public final String createUserName;
    public final String createAUserName;
    public final Timestamp createTimestampUTC;
    public final String modificationUserName;
    public final String modificationAUserName;
    public final Timestamp modificationTimestampUTC;
    public final long size;
    public final String sha256;

    public MetadataResult(ResultSet rs) throws SQLException
    {
      super();
      this.path = rs.getString(1);
      this.createUserName = rs.getString(2);
      this.createAUserName = rs.getString(3);
      this.createTimestampUTC = rs.getTimestamp(4);
      this.modificationUserName = rs.getString(5);
      this.modificationAUserName = rs.getString(6);
      this.modificationTimestampUTC = rs.getTimestamp(7);
      this.size = rs.getLong(8);
      this.sha256 = rs.getString(9);
    }

    @Override
    public String toString ()
    {
      return "\nPath:                     " + path + "\n" +
          "createUserName:           " + createUserName + "\n" +
          "createAppUserName:        " + createAUserName + "\n" +
          "createTimestampUTC:       " + createTimestampUTC + "\n" +
          "modificationUserName:     " + modificationUserName + "\n" +
          "modificationAppUserName:  " + modificationAUserName + "\n" +
          "modificationTimestampUTC: " + modificationTimestampUTC + "\n" +
          "Size:                     " + size + "\n" +
          "SHA256:                   " + sha256 + "\n";
    }
  }
