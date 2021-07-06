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
import com.sap.xsk.migration.neo.db.DeliveryUnitsExportConfig;
import com.sap.xsk.migration.neo.db.DeliveryUnitsExporter;
import org.apache.commons.io.FileUtils;
import org.eclipse.dirigible.runtime.transport.processor.TransportProcessor;
import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

public class HanaXSDeliveryUnitsExporter implements DeliveryUnitsExporter {

  private final TransportProcessor transportProcessor;

  @Inject
  public HanaXSDeliveryUnitsExporter(TransportProcessor transportProcessor){
    this.transportProcessor = transportProcessor;
  }

  @Override
  public void exportDeliveryUnits(DeliveryUnitsExportConfig config) {
    try {
      String fileURL = System.getenv("CATALINA_HOME") + "/" + System.getenv("ZIP_OUTPUT_URL");
      File file = new File(fileURL);

      file.createNewFile();

      ProcessBuilder pb = new ProcessBuilder(createProcessArguments(config));
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
//            projects.add(project);
            byte[] project2 = Files.readAllBytes(Paths.get(fullPath));
            transportProcessor.importProject(config.getWorkspace(), project);
            line = reader.readLine();
          }
          reader.close();
        } catch (IOException e) {
          e.printStackTrace();
          throw new RuntimeException("Error reading results file!");
        }

        file.delete();
//        return projects;
      } else {
        throw new RuntimeException("Migration script failure!");
      }

    } catch (Throwable e) {
      throw new RuntimeException("Migration script failure!", e);
    }
  }

  private List<String> createProcessArguments(DeliveryUnitsExportConfig config) {
    var commandArgs = new ArrayList<String>();
    commandArgs.add("node");

    if (shouldEnableNodeDebug()) {
      commandArgs.add("--inspect-brk=0.0.0.0");
    }

    commandArgs.add(System.getenv("CATALINA_HOME") + "/migration-tools/xs-migration-cloud/xs-migration.js");
    commandArgs.add(config.getDeliveryUnitName() + "," + config.getDeliveryUnitVendor());
    commandArgs.add(config.getDbHost());
    commandArgs.add(config.getDbPort());
    commandArgs.add(config.getDbUser());
    commandArgs.add(config.getDbPassword());

    return commandArgs;
  }

  private boolean shouldEnableNodeDebug() {
    return "1".equals(System.getenv("NODE_INSPECT_BRK"));
  }
}
