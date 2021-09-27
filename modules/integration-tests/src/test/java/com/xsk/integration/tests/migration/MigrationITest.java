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
import static org.junit.Assert.assertTrue;

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
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Base64;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@RunWith(JUnitParamsRunner.class)
public class MigrationITest {

  private WebDriver browser;
  private WebDriverWait browserWait;
  private Actions browserActions;
  private JavascriptExecutor jsExecutor;

  private MigrationCredentials credentials;
  private MigrationValidation validation;

  private final String host = "127.0.0.1";
  private final String port = "8080";
  private final String ide = "/services/v4/web/ide/";
  private final String auth = "dirigible:dirigible";
  private final String baseUrl = "http://" + auth + "@" + host + ":" + port + ide;

  public MigrationITest() throws IOException {
    credentials = new MigrationCredentials();
    validation = new MigrationValidation();
  }

  @Test
  @Parameters({"Chrome", "Firefox"})
  public void migrationTest(String browserType) throws InterruptedException, IOException {
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

    setupBrowser(new ChromeDriver(options));
  }

  private void setupFirefox() {
    WebDriverManager.firefoxdriver().setup();
    FirefoxOptions options = new FirefoxOptions();
    options.setHeadless(true);
    options.addArguments("--height=1080", "--width=1920", "-private");

    setupBrowser(new FirefoxDriver(options));
  }

  private void setupBrowser(WebDriver webDriver) {
    browser = webDriver;
    browser.navigate().to(baseUrl);
    browser.manage().window().maximize();
    browser.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    jsExecutor = (JavascriptExecutor) (browser);
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
    assertEquals("Input field value doesn't match sent keys.",
        value, field.getAttribute("value"));
  }

  private void selectAndAssertDropdown(String listName, String selectionValue) {
    var dropdownList = browserWait.until(
        ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[@ng-repeat=\"option in " + listName + "\"]")));
    WebElement dropdownButton = dropdownList.get(0).findElement(By.xpath("./../../button"));
    browserWait.until(ExpectedConditions.elementToBeClickable(dropdownButton)).click();
    var selection = dropdownList.stream()
        .filter((WebElement it) -> it.getAttribute("innerHTML").contains(selectionValue)).collect(Collectors.toList());
    browserWait.until(ExpectedConditions.elementToBeClickable(selection.get(0)));
    assertEquals("Only one dropdown item should be selected.",
        1, selection.size());
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

  private void validateAllMigratedFileContents() throws IOException {
    var xpathCommon = "//iframe[@src=\"../ide-monaco/editor.html?file=/workspace/xsk-test-app/";
    validateTextFileContent(
        xpathCommon + "index.html&contentType=text/html\"]",
        validation.getIndex());
    validateTextFileContent(
        xpathCommon + "logic.xsjs&contentType=application/javascript\"]",
        validation.getLogic());
    validateTextFileContent(
        xpathCommon + ".xsapp&contentType=text/plain\"]",
        validation.getXsapp());
    validateTextFileContent(
        xpathCommon + "indexUI5.html&contentType=text/html\"]",
        validation.getIndexUI5());
    validateTextFileContent(
        xpathCommon + ".xsaccess&contentType=text/plain\"]",
        validation.getXsaccess());

    String imagesURL = "http://127.0.0.1:8080/services/v4/ide/workspaces/workspace/xsk-test-app/images/";
    validateImageFileContent(new URL(imagesURL + "SAPLogo.gif"), validation.getSapLogo());
    validateImageFileContent(new URL(imagesURL + "Contact_hover.png"), validation.getContactHover());
    validateImageFileContent(new URL(imagesURL + "Contact_regular.png"), validation.getContactRegular());
  }

  private void validateTextFileContent(String editorFrameXpath, String validContent) {
    browser.switchTo().defaultContent();
    browserWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath(editorFrameXpath)));
    sleep(3000);
    var migratedContent = (String) jsExecutor.executeScript("return window._editor.getValue();");
    assertEquals("File content after migration must match validation content.",
        validContent.replaceAll("\\s", ""), migratedContent.replaceAll("\\s", ""));
    browser.switchTo().defaultContent();
  }

  private void sleep(long millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  private void validateImageFileContent(URL url, BufferedImage validImage) throws IOException {
    URLConnection connection = url.openConnection();

    String basicAuth = "Basic " + new String(Base64.getEncoder().encode(auth.getBytes()));
    connection.setRequestProperty("Authorization", basicAuth);
    BufferedImage migratedImage = ImageIO.read(connection.getInputStream());
    assertTrue("Image data and size must be equal.", compareImages(migratedImage, validImage));
  }

  private static boolean compareImages(BufferedImage first, BufferedImage second) {
    if (first.getWidth() != second.getWidth()
        || first.getHeight() != second.getHeight()) {
      return false;
    }

    for (int y = 0; y < first.getHeight(); y++) {
      for (int x = 0; x < first.getWidth(); x++) {
        if (first.getRGB(x, y) != second.getRGB(x, y)) {
          return false;
        }
      }
    }

    return true;
  }

  @After
  public void quitBrowser() {
    if (browser != null) {
      browser.quit();
    }
  }
}
