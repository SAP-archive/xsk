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
package com.sap.xsk.migration.neo.db.hana;

import com.sap.xsk.migration.neo.db.DeliveryUnit;
import com.sap.xsk.migration.neo.db.DeliveryUnitsExporter;
import org.apache.commons.io.FileUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

public class HanaXSDeliveryUnitsExporter implements DeliveryUnitsExporter {

  @Override
  public List<byte[]> exportDeliveryUnits(String deliveryUnitName, String deliveryUnitVendor) {
    try {
      var projects = new ArrayList<byte[]>();
      String fileURL = System.getenv("CATALINA_HOME") + "/" + System.getenv("ZIP_OUTPUT_URL");
      File file = new File(fileURL);

      file.createNewFile();

      ProcessBuilder pb = new ProcessBuilder("node", System.getenv("CATALINA_HOME") + "/migration-tools/xs-migration-cloud/xs-migration.js",
          deliveryUnitName + "," + deliveryUnitVendor);
      pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
      pb.redirectError(ProcessBuilder.Redirect.INHERIT);
      Process p = pb.start();
      Future<Boolean> identical = p.onExit().thenApply(p1 -> p1.exitValue() == 0);
      if (identical.get()) {
        BufferedReader reader;
        try {
          reader = new BufferedReader(new FileReader(fileURL));
          String line = reader.readLine();
          while (line != null) {
            String fullPath = System.getenv("CATALINA_HOME") + "/" + line;
            File zipFile = new File(fullPath);
            byte[] project = FileUtils.readFileToByteArray(zipFile);
            projects.add(project);
//            processor.importProject(workspace, project);
            line = reader.readLine();
          }
          reader.close();
        } catch (IOException e) {
          e.printStackTrace();
          throw new RuntimeException("Error reading results file!");
        }

        file.delete();
        return projects;
      } else {
        throw new RuntimeException("Migration script failure!");
      }

    } catch (Throwable e) {
      throw new RuntimeException("Migration script failure!", e);
    }
  }
}
