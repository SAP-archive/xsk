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
package com.sap.xsk.xsjob.ds.transformer;

import com.sap.xsk.xsjob.ds.model.XSKJobArtifact;
import com.sap.xsk.xsjob.ds.model.XSKJobDefinition;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class XSKJobToXSKJobDefinitionTransformer {

  @Inject
  private XSKCronToQuartzCronTransformer xskCronToQuartzCronTransformer;

  public ArrayList<XSKJobDefinition> transform(XSKJobArtifact xskJobArtifact) throws ParseException {
    ArrayList<XSKJobDefinition> jobDefinitions = new ArrayList<>();
    String[] parseAction = xskJobArtifact.getAction().split("::");
    final int PARTS_OF_ACTION_PROPERTY = 2;

    if(parseAction.length == PARTS_OF_ACTION_PROPERTY){
      String filePath = parseAction[0];
      String functionName = parseAction[1];
      filePath = xskPathToDirigiblePath(filePath);

      for (int i = 0; i < xskJobArtifact.getSchedules().size(); i++) {
        XSKJobDefinition xskJobDefinition = new XSKJobDefinition();
        String xskJobDefinitionName = xskJobArtifact.getAction() + "-" + i;
        String xskCronExpression = xskJobArtifact.getSchedules().get(i).getXscron();
        String quartzCronExpression = xskCronToQuartzCronTransformer.transform(xskCronExpression);

        xskJobDefinition.setName(xskJobDefinitionName);
        xskJobDefinition.setParametersAsMap(xskJobArtifact.getSchedules().get(i).getParameter());
        xskJobDefinition.setDescription(xskJobArtifact.getDescription() + " " + xskJobArtifact.getSchedules().get(i).getDescription());
        xskJobDefinition.setCronExpression(quartzCronExpression);
        xskJobDefinition.setModule(filePath);
        xskJobDefinition.setFunction(functionName);
        jobDefinitions.add(xskJobDefinition);
      }
    }else {
      throw new IllegalStateException("Invalid xsjob artifact definition!");
    }

    return jobDefinitions;
  }

  public String xskPathToDirigiblePath(String xskFilePath) {
    String[] splitXscFilePath = xskFilePath.split(":");
    List<String> splitPackage = Arrays.asList(splitXscFilePath[0].split("\\."));

    return String.join("/", splitPackage) + "/" + splitXscFilePath[1];
  }
}
