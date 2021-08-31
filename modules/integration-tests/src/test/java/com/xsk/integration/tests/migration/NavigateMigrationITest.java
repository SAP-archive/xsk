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
package com.xsk.integration.tests.migration;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;

public class NavigateMigrationITest {

  protected String baseURL = "http://dirigible:dirigible@127.0.0.1:8080/services/v4/web/ide/";
  protected WebDriver chrome;

  @Before
  public void setupChrome() {
    WebDriverManager.chromedriver().setup();
    ChromeOptions options = new ChromeOptions();
    options.setHeadless(true);
    options.addArguments("--no-sandbox");
    options.addArguments("--disable-dev-shm-usage");
    options.addArguments("--window-size=1920,1080");
    options.addArguments("--incognito");
    chrome = new ChromeDriver(options);
    chrome.navigate().to(baseURL);
    chrome.manage().window().maximize();
    chrome.manage().timeouts().implicitlyWait(120, TimeUnit.MILLISECONDS);
  }

  @Test
  public void navigateToMigrationTest() throws Exception {
    WebDriverWait waitChrome = new WebDriverWait(chrome, 5);
    waitChrome.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@title=\"XS Migration\"]")));
    chrome.findElement(By.xpath("//*[@title=\"XS Migration\"]")).click();
    waitChrome.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@src='../ide-migration/migration-launch.html']")));
    waitChrome.until(ExpectedConditions.presenceOfElementLocated(By.id("subaccount")));
    String input = "Some subaccount";
    chrome.findElement(By.id("subaccount")).sendKeys(input);
    String subaccount = chrome.findElement(By.id("subaccount")).getAttribute("value");
    assertEquals(input, subaccount);
  }

  @After
  public void cleanupChrome() {
    if(chrome != null) chrome.quit();
  }
}
