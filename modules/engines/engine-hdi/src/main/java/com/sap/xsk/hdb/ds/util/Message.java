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
package com.sap.xsk.hdb.ds.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Message {

    public final long requestId;
    public final long rowId;
    public final int level;
    public final String type;
    public final String library;
    public final String pluginId;
    public final String path;
    public final String severity;
    public final long messageCode;
    public final String message;
    public final String location;
    public final String locationPath;
    public final Timestamp timestampUTC;


    public Message(ResultSet rs) throws SQLException
    {
      this.requestId = rs.getLong(1);
      this.rowId = rs.getLong(2);
      this.level = rs.getInt(3);
      this.type = rs.getString(4);
      this.library = rs.getString(5);
      this.pluginId = rs.getString(6);
      this.path = rs.getString(7);
      this.severity = rs.getString(8);
      this.messageCode = rs.getLong(9);
      this.message = rs.getString(10);
      this.location = rs.getString(11);
      this.locationPath = rs.getString(12);
      this.timestampUTC = rs.getTimestamp(13);
    }

    public Message(long requestId,
    long rowId,
    int level,
    String type,
    String library,
    String pluginId,
    String path,
    String severity,
    long messageCode,
    String message,
    String location,
    String locationPath,
    Timestamp timestampUTC) {
    super();
    this.requestId = requestId;
    this.rowId = rowId;
    this.level = level;
    this.type = type;
    this.library = library;
    this.pluginId = pluginId;
    this.path = path;
    this.severity = severity;
    this.messageCode = messageCode;
    this.message = message;
    this.location = location;
    this.locationPath = locationPath;
    this.timestampUTC = timestampUTC;
  }

    @Override
    public String toString()
    {
      StringBuilder builder = new StringBuilder();
      for (int i = 0; i < level; ++i)
      {
        builder.append("  ");
      }
      String indent = builder.toString();
      builder.setLength(0);
      builder.append('\n');

      if (!library.isEmpty())
      {
        builder.append(indent);
        builder.append("Library:   ");
        builder.append(library);
        builder.append('\n');
      }
      if (!pluginId.isEmpty())
      {
        builder.append(indent);
        builder.append("Plugin:    ");
        builder.append(pluginId);
        builder.append('\n');
      }
      if (!path.isEmpty())
      {
        builder.append(indent);
        builder.append("Path:      ");
        builder.append(path);
        builder.append('\n');
      }

      builder.append(indent);
      builder.append("Severity:  ");
      builder.append(severity);
      builder.append('\n');
      builder.append(indent);
      builder.append("Message:   ");
      builder.append(message);
      builder.append(" (");
      builder.append(messageCode);
      builder.append(")\n");

      if (!location.equals("0:0") || !locationPath.isEmpty())
      {
        builder.append(indent);
        builder.append("Location: ");
        builder.append(location);
        builder.append(" ");
        builder.append(locationPath);
        builder.append('\n');
      }

      builder.append(indent);
      builder.append("Timestamp: ");
      builder.append(timestampUTC);
      builder.append('\n');

      return builder.toString();
    }
}
