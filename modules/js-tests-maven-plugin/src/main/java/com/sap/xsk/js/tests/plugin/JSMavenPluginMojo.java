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
package com.sap.xsk.js.tests.plugin;

//import com.sap.xsk.engine.XSKJavascriptEngineExecutor;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.project.MavenProject;
import org.eclipse.dirigible.commons.api.module.DirigibleModulesInstallerModule;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mojo(name = "xsk-js-tests", defaultPhase = LifecyclePhase.TEST, requiresDependencyResolution = ResolutionScope.COMPILE_PLUS_RUNTIME)
public class JSMavenPluginMojo extends AbstractMojo {

  @Parameter(defaultValue = "${project}", required = true, readonly = true)
  MavenProject project;

  static {
    DirigibleModulesInstallerModule.configure();
  }

  @Override
  public void execute() throws MojoExecutionException, MojoFailureException {
    List<Path> jsTestFilePaths = collectJSTestFilePaths();
    for (Path jsTestFilePath : jsTestFilePaths) {
      try {
        String jsTestCode = Files.readString(jsTestFilePath, StandardCharsets.UTF_8);
        runJSTest(jsTestCode);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }

  private List<Path> collectJSTestFilePaths() {
    Path mavenModuleDirPath = project.getBasedir().toPath();
    Path jsTestsDirPath = mavenModuleDirPath.resolve("src").resolve("test").resolve("js");

    try {
      return Files.walk(jsTestsDirPath)
          .filter(p -> p.toString().endsWith(".test.js"))
          .collect(Collectors.toList());
    } catch (IOException e) {
      return Collections.emptyList();
    }
  }

  private void runJSTest(String jsSource) {
    try {
      Class<?> klass = Class.forName("com.sap.xsk.engine.XSKJavascriptEngineExecutor");
      Object executor = klass.getDeclaredConstructor().newInstance();
      Method executeServiceCodeMethod = klass.getDeclaredMethod("executeServiceCode", String.class, Map.class);
      executeServiceCodeMethod.invoke(executor, jsSource, new HashMap<>());
    } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
      throw new RuntimeException(e);
    }
  }
}