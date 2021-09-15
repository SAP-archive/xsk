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
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@RunWith(JUnitParamsRunner.class)
public class MigrationITest {

  private WebDriver browser;
  private WebDriverWait browserWait;
  private Actions browserActions;
  private JavascriptExecutor jsExecutor;

  MigrationCredentials credentials;
  MigrationValidation validation;

  public MigrationITest() throws IOException {
    credentials = new MigrationCredentials();
    validation = new MigrationValidation();
  }

  @Test
  @Parameters({ "Chrome", "Firefox" })
  public void migrationTest(String browserType) throws InterruptedException {
    setupBrowser(browserType);
    navigateToMigrationPerspective();
    enterNeoDBTunnelCredentials();
    enterHanaCredentials();
    selectDeliveryUnits();
    goToWorkspace();
    openFilesFromJstree();
    validateAllMigratedFileContents();
  }

  private void setupBrowser(String browserType) {
    if (browserType.equals("Chrome")) {
      setupChrome();
    } else {
      setupFirefox();
    }
  }

  private void setupChrome() {
    WebDriverManager.chromedriver().setup();
    ChromeOptions options = new ChromeOptions();
    options.setHeadless(true);
    options.addArguments("--no-sandbox");
    options.addArguments("--disable-dev-shm-usage");
    options.addArguments("--window-size=1920,1080");
    options.addArguments("--incognito");

    browser = new ChromeDriver(options);
    browser.navigate().to("http://dirigible:dirigible@127.0.0.1:8080/services/v4/web/ide/");
    browser.manage().window().maximize();
    browser.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    jsExecutor = (JavascriptExecutor)(browser);
    browserWait = new WebDriverWait(browser, 60);
    browserActions = new Actions(browser);
  }

  private void setupFirefox() {
    WebDriverManager.firefoxdriver().setup();
    FirefoxOptions options = new FirefoxOptions();
    options.setHeadless(true);
    //options.addArguments("--no-sandbox");
    //options.addArguments("--disable-dev-shm-usage");
    options.addArguments("--height=1080", "--width=1920", "-private");

    browser = new FirefoxDriver(options);
    browser.navigate().to("http://dirigible:dirigible@127.0.0.1:8080/services/v4/web/ide/");
    browser.manage().window().maximize();
    browser.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    jsExecutor = (JavascriptExecutor)(browser);
    browserWait = new WebDriverWait(browser, 60);
    browserActions = new Actions(browser);
  }

  private void navigateToMigrationPerspective() {
    browserWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@title=\"XS Migration\"]"))).click();
    browserWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(
        By.xpath("//iframe[@src='../ide-migration/migration-launch.html']")));
  }

  private void enterNeoDBTunnelCredentials() {
    enterAndAssertField(By.id("subaccount"), credentials.getSubaccount());
    selectAndAssertDropdown("regionList", credentials.getRegion());
    enterAndAssertField(By.id("neo-username"), credentials.getUsername());
    enterAndAssertField(By.id("neo-password"), credentials.getPassword());
    browser.findElement(By.xpath("//*[@ng-click='nextClicked()']")).click();
  }

  private void enterAndAssertField(By by, String value) {
    var field = browser.findElement(by);
    field.sendKeys(value);
    assertEquals(field.getAttribute("value"), value);
  }

  private void selectAndAssertDropdown(String listName, String selectionValue) {
    var dropdownItems = browserWait.until(
        ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[@ng-repeat=\"option in "+ listName +"\"]")));
    WebElement dropdownButton = dropdownItems.get(0).findElement(By.xpath("./../../button"));
    browserWait.until(ExpectedConditions.elementToBeClickable(dropdownButton)).click();
    var selection = dropdownItems.stream()
        .filter((WebElement it) -> it.getAttribute("innerHTML").contains(selectionValue)).collect(Collectors.toList());
    browserWait.until(ExpectedConditions.elementToBeClickable(selection.get(0)));
    assertEquals(selection.size(), 1);
    selection.get(0).click();
  }

  private void enterHanaCredentials() {
    selectAndAssertDropdown("databasesList", credentials.getSchema());
    enterAndAssertField(By.id("username"), credentials.getHanaUsername());
    enterAndAssertField(By.id("password"), credentials.getHanaPassword());
    browser.findElement(By.xpath("//*[@ng-click='nextClicked()']")).click();
  }

  private void selectDeliveryUnits() {
    selectAndAssertDropdown("workspacesList", "workspace");
    selectAndAssertDropdown("deliveryUnitList", "MIGR_TOOLS");
    browser.findElement(By.xpath("//*[@ng-click=\"finishClicked()\"]")).click();
  }

  private void goToWorkspace() {
    browserWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@ng-click=\"goToWorkspace()\"]"))).click();
  }

  private void openFilesFromJstree() throws InterruptedException {
    // Select the workspace Iframe and expand the tree view in jstree
    browser.switchTo().defaultContent();
    browserWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@src='../ide-workspace/workspace.html']")));
    browserWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("j1_1_anchor")));
    jsExecutor.executeScript("$(\".jstree\").jstree(\"open_all\")");

    // Open all files by double-click
    var jstreeAnchors = new ArrayList<WebElement>();
    jstreeAnchors.add(browserWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("j1_14_anchor"))));
    jstreeAnchors.add(browserWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("j1_11_anchor"))));
    jstreeAnchors.add(browserWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("j1_7_anchor"))));
    jstreeAnchors.add(browserWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("j1_8_anchor"))));
    jstreeAnchors.add(browserWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("j1_9_anchor"))));
    jstreeAnchors.add(browserWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("j1_13_anchor"))));
    jstreeAnchors.add(browserWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("j1_12_anchor"))));
    jstreeAnchors.add(browserWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("j1_10_anchor"))));

    for (var anchor : jstreeAnchors) {
      browserActions.doubleClick(anchor).perform();
      Thread.sleep(500);
    }

    browser.switchTo().defaultContent();
  }

  private void validateAllMigratedFileContents() {
    var xpathCommon = "//iframe[@src=\"../ide-monaco/editor.html?file=/workspace/";
    validateFileContent(
        xpathCommon + "xsk-test-app/index.html&contentType=text/html\"]",
        validation.getIndex());
    validateFileContent(
        xpathCommon + "xsk-test-app/logic.xsjs&contentType=application/javascript\"]",
        validation.getLogic());
    validateFileContent(
        xpathCommon + "xsk-test-app/.xsapp&contentType=text/plain\"]",
        validation.getXsapp());
    validateFileContent(
        xpathCommon + "xsk-test-app/indexUI5.html&contentType=text/html\"]",
        validation.getIndexUI5());
    validateFileContent(
        xpathCommon + "xsk-test-app/.xsaccess&contentType=text/plain\"]",
        validation.getXsaccess());

    // TODO: Add validation for image files with the new image view
//    validateFileContent(
//        xpathCommon + "xsk-test-app/images/Contact_regular.png&contentType=image/png\"]",
//        validation.getContactRegular());
//    validateFileContent(
//        xpathCommon + "xsk-test-app/images/Contact_hover.png&contentType=image/png\"]",
//        validation.getContactHover());
//    validateFileContent(
//        xpathCommon + "xsk-test-app/images/SAPLogo.gif&contentType=image/gif\"]",
//        validation.getSapLogo());
  }

  private void validateFileContent(String editorFrameXpath, String validContent) {
    browserWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath(editorFrameXpath)));
    var migratedContent = (String) jsExecutor.executeScript("return this.monaco.editor.getModels()[0].getValue()");
    assertEquals("File content after migration must match validation content.",
        migratedContent.replaceAll("\\s",""), validContent.replaceAll("\\s",""));
    browser.switchTo().defaultContent();
  }

  @After
  public void cleanupChrome() {
    if(browser != null) browser.quit();
  }
}
