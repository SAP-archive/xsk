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
  private final MigrationValidation validation;
  private SeleniumLogger debug;

  private final String host = "127.0.0.1";
  private final String port = "8080";
  private final String ide = "/services/v4/web/ide/";
  private final String auth = "dirigible:dirigible";
  private final String baseUrl = "http://" + auth + "@" + host + ":" + port + ide;

  public MigrationITest() throws IOException {
    validation = new MigrationValidation();
  }

  @Test
  @Parameters({"Chrome-Hana1", "Chrome-Hana2", "Firefox-Hana1", "Firefox-Hana2"})
  public void migrationTest(String param) throws InterruptedException, IOException {
    credentials = new MigrationCredentials(param.contains("Hana2"));
    setupBrowser(param);
    debug = new SeleniumLogger(browser, credentials, param);

    try {
      navigateToMigrationPerspective();
      debug.save();
      enterNeoDBTunnelCredentials();
      debug.save();
      enterHanaCredentials();
      debug.save();
      selectDeliveryUnits();
      debug.save();
      goToWorkspace();
      debug.save();
      openFilesFromJstree();
      debug.save();
      validateAllMigratedFileContents();
      debug.save();
      debug.finish();
    } catch(Throwable exception) {
      debug.save();
      debug.finish();
      throw exception;
    }
  }

  private void setupBrowser(String param) {
    if (param.contains("Chrome")) {
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

  private void enterNeoDBTunnelCredentials() throws IOException {
    enterAndAssertField(By.id("subaccount"), credentials.getSubaccount());
    selectAndAssertDropdown("regionList", credentials.getRegion());
    enterAndAssertField(By.id("neo-username"), credentials.getUsername());
    enterAndAssertField(By.id("neo-password"), credentials.getPassword());
    browser.findElement(By.xpath("//*[@ng-click='nextClicked()']")).click();
  }

  private void enterAndAssertField(By by, String value) throws IOException {
    var field = browser.findElement(by);
    field.sendKeys(value);
    assertEquals("Input field value doesn't match sent keys.",
        value, field.getAttribute("value"));
    debug.save();
  }

  private void selectAndAssertDropdown(String listName, String selectionValue) throws IOException {
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
    debug.save();
  }

  private void enterHanaCredentials() throws IOException {
    selectAndAssertDropdown("databasesList", credentials.getSchema());
    enterAndAssertField(By.id("username"), credentials.getHanaUsername());
    enterAndAssertField(By.id("password"), credentials.getHanaPassword());
    browser.findElement(By.xpath("//*[@ng-click='nextClicked()']")).click();
  }

  private void selectDeliveryUnits() throws IOException {
    selectAndAssertDropdown("workspacesList", "workspace");
    selectAndAssertDropdown("deliveryUnitList", "MIGR_TOOLS");
    browser.findElement(By.xpath("//*[@ng-click=\"finishClicked()\"]")).click();
  }

  private void goToWorkspace() throws IOException {
    browserWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@ng-click=\"goToWorkspace()\"]"))).click();
    browserWait.until(ExpectedConditions.titleIs("Workspace | XSK WebIDE"));
    browser.switchTo().defaultContent();
  }

  private void openFilesFromJstree() throws InterruptedException, IOException {
    // Select the workspace Iframe and expand the tree view in jstree
    browser.switchTo().defaultContent();
    browserWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@src='../ide-workspace/workspace.html']")));
    debug.save();
    browserWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("j1_1_anchor")));
    jsExecutor.executeScript("$(\".jstree\").jstree(\"open_all\")");

    // Open all files by double-click
    var xpaths = new ArrayList<By>();
    xpaths.add(By.xpath("//*[text()='" + MigrationValidation.INDEX.getFileName() + "']"));
    xpaths.add(By.xpath("//*[text()='" + MigrationValidation.INDEXUI5.getFileName() + "']"));
    xpaths.add(By.xpath("//*[text()='" + MigrationValidation.XSACCESS.getFileName() + "']"));
    xpaths.add(By.xpath("//*[text()='" + MigrationValidation.XSAPP.getFileName() + "']"));
    xpaths.add(By.xpath("//*[text()='" + MigrationValidation.LOGIC.getFileName() + "']"));
    xpaths.add(By.xpath("//*[text()='" + MigrationValidation.CONTACT_REGULAR.getFileName() + "']"));
    xpaths.add(By.xpath("//*[text()='" + MigrationValidation.CONTACT_HOVER.getFileName() + "']"));
    xpaths.add(By.xpath("//*[text()='" + MigrationValidation.SAPLOGO.getFileName() + "']"));

    var jstreeAnchors = new ArrayList<WebElement>();
    for(var by : xpaths){
      jstreeAnchors.add(browserWait.until(ExpectedConditions.visibilityOfElementLocated(by)));
    }
    debug.save();

    for (var anchor : jstreeAnchors) {
      browserActions.doubleClick(anchor).perform();
      Thread.sleep(500);
      debug.save();
    }

    browser.switchTo().defaultContent();
  }

  private void validateAllMigratedFileContents() throws IOException {

    validateTextFileContent(
        MigrationValidation.XPATH_COMMON + MigrationValidation.XPATH_INDEX,
        validation.getIndex());
    validateTextFileContent(
        MigrationValidation.XPATH_COMMON + MigrationValidation.XPATH_LOGIC,
        validation.getLogic());
    validateTextFileContent(
        MigrationValidation.XPATH_COMMON + MigrationValidation.XPATH_XSAPP,
        validation.getXsapp());
    validateTextFileContent(
        MigrationValidation.XPATH_COMMON + MigrationValidation.XPATH_INDEXUI5,
        validation.getIndexUI5());
    validateTextFileContent(
        MigrationValidation.XPATH_COMMON + MigrationValidation.XPATH_XSACCESS,
        validation.getXsaccess());

    String imagesURL = MigrationValidation.IMAGE_URL_COMMON;
    validateImageFileContent(new URL(imagesURL + MigrationValidation.SAPLOGO.getFileName()), validation.getSapLogo());
    validateImageFileContent(new URL(imagesURL + MigrationValidation.CONTACT_HOVER.getFileName()), validation.getContactHover());
    validateImageFileContent(new URL(imagesURL + MigrationValidation.CONTACT_REGULAR.getFileName()), validation.getContactRegular());
  }

  private void validateTextFileContent(String editorFrameXpath, String validContent) throws IOException {
    browser.switchTo().defaultContent();
    debug.save();
    browserWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath(editorFrameXpath)));
    sleep(3000);
    debug.save();
    var migratedContent = (String) jsExecutor.executeScript("return monaco.editor.getModels().at(0).getValue();");
    assertEquals("File content after migration must match validation content.",
        validContent.replaceAll("\\s", ""), migratedContent.replaceAll("\\s", ""));
    debug.save();
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
