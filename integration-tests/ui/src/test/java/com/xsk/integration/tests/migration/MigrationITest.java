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

import com.google.common.base.Strings;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RunWith(JUnitParamsRunner.class)
public class MigrationITest {

  private ExpectedContentProvider expectedContentProvider = ExpectedContentProvider.getInstance();
  private WebBrowser webBrowser;
  private MigrationCredentials credentials;
  private List<ExpectedContent> expectedContentList;

  @Test
  @Parameters({"Migration-Chrome-Hana1", /*"Migration-Chrome-Hana2",*/ "Migration-Firefox-Hana1", /*"Migration-Firefox-Hana2"*/})
  public void migrationTest(String param) throws IOException {
    setup(param);
    loginIfNecessary();
    navigateToMigrationPerspective();
    enterNeoDBTunnelCredentials();
    enterHanaCredentials();
    selectDeliveryUnits();
    goToWorkspace();
    openFilesFromJstree();
    validateAllMigratedFileContents();
  }

  private void setup(String param) {
    webBrowser = new WebBrowser(param, DirigibleConnectionProperties.BASE_URL, false);
    credentials = new MigrationCredentials(param.contains("Hana2"));
    expectedContentList = expectedContentProvider.getExpectedContentList();
  }

  private void loginIfNecessary() {
    try {
      webBrowser.waitForVisibilityOfElement(By.xpath("/html/body/div/div/h3[text() = 'Sign in to Eclipse Dirigible']"));
    } catch (Throwable t) {
      return; // assume we're already logged in
    }

    webBrowser.enterAndAssertField(By.xpath("//input[@placeholder='Username']"), DirigibleConnectionProperties.AUTH_USERNAME);
    webBrowser.enterAndAssertField(By.xpath("//input[@placeholder='Password']"), DirigibleConnectionProperties.AUTH_PASSWORD);
    webBrowser.clickItem(By.xpath("//button[text() = 'Log in']"));
  }

  private void navigateToMigrationPerspective() {
    webBrowser.clickItem(By.xpath("//*[@title=\"SAP HANA XS Classic Migration\"]"));
    webBrowser.waitForPageWithTitle("SAP HANA XS Classic Migration | XSK WebIDE");
    webBrowser.switchToDefaultContent();
    webBrowser.switchToIframe(By.xpath("//iframe[@src='../ide-migration/migration-launch.html']"));
    webBrowser.clickItem(By.xpath("//*[@ng-click='showMigrationScreen()']"));
    webBrowser.log();
  }

  private void enterNeoDBTunnelCredentials() {
    webBrowser.enterAndAssertField(By.id("subaccount"), credentials.getSubaccount());
    webBrowser.selectAndAssertDropdown("regionList", (item) -> item.contains(credentials.getRegion()));
    webBrowser.enterAndAssertField(By.id("neo-username"), credentials.getUsername());
    webBrowser.enterAndAssertField(By.id("neo-password"), credentials.getPassword());
    webBrowser.log();
    webBrowser.clickItem(By.xpath("//*[@ng-click='nextClicked()']"));
  }

  private void enterHanaCredentials() {
    webBrowser.selectAndAssertDropdown("databasesList", (item) -> item.equals(credentials.getSchema()));
    webBrowser.enterAndAssertField(By.id("username"), credentials.getHanaUsername());
    webBrowser.enterAndAssertField(By.id("password"), credentials.getHanaPassword());
    webBrowser.log();
    webBrowser.clickItem(By.xpath("//*[@ng-click='nextClicked()']"));
  }

  private void selectDeliveryUnits() {
    webBrowser.selectAndAssertDropdown("workspacesList", (item) -> item.equals(expectedContentProvider.getExpectedWorkspaceName()));
    webBrowser.selectAndAssertDropdown("deliveryUnitList", (item) -> item.equals(expectedContentProvider.getExpectedDeliveryUnitName()));
    webBrowser.clickItem(By.xpath("//*[@ng-disabled=\"duDropdownDisabled\"]"));
    webBrowser.log();
    webBrowser.clickItem(By.xpath("//*[@ng-click=\"finishClicked()\"]"));
  }

  private void goToWorkspace() {
    webBrowser.clickItem(By.xpath("//*[@ng-click=\"goToWorkspace()\"]"));
    webBrowser.waitForPageWithTitle("Workspace | XSK WebIDE");
    webBrowser.switchToDefaultContent();
    webBrowser.log();
  }

  private void openFilesFromJstree() {
    // Select the workspace iframe and expand the tree view in jstree
    webBrowser.switchToDefaultContent();
    webBrowser.switchToIframe(By.xpath("//iframe[@src='../ide-workspace/workspace.html']"));
    webBrowser.waitForVisibilityOfElement(By.id("j1_1_anchor"));
    webBrowser.log();
    webBrowser.executeJavascript("$(\".jstree\").jstree(\"open_all\")");

    // Double-click all jstree anchors by their file name (text content).
    for (var expectedContent : expectedContentList) {
      var fileName = Paths.get(expectedContent.getFilePath()).getFileName();
      By anchorXpath = By.xpath("//*[text()='" + fileName + "']");
      webBrowser.doubleClickVisibleElementBy(anchorXpath);
      webBrowser.sleep(500);
      webBrowser.log();
    }

    webBrowser.switchToDefaultContent();
  }

  private void validateAllMigratedFileContents() throws IOException {
    var workspaceUrl = "http://"
        + DirigibleConnectionProperties.HOST
        + ":" + DirigibleConnectionProperties.PORT
        + "/services/v4/ide/workspaces/"
        + expectedContentProvider.getExpectedWorkspaceName()
        + "/";

    var projectUrl = workspaceUrl + expectedContentProvider.getExpectedProjectName();
    var iframes = webBrowser.findElementsBy(By.tagName("iframe"));
    int assertedFilesCount = 0;

    for (var expectedContent : expectedContentList) {
      var expectedFilePath = expectedContent.getFilePath();
      var expectedFileContent = expectedContent.getContent();
      var expectedFileName = Paths.get(expectedFilePath).getFileName().toString();

      for (var iframe : iframes) {
        var srcAttribute = iframe.getAttribute("src");
        var parsedSrc = splitQuery(new URL(srcAttribute));
        var fileQueryParameter = parsedSrc.get("file");
        if (collectionHasElementEndingWith(fileQueryParameter, expectedFilePath)) {
          if (isImageFile(expectedFileName)) {
            var imageUrl = projectUrl + expectedFilePath;
            assertImageFileEquals(imageUrl, expectedFileContent);
          } else {
            assertMonacoTextFileEquals(iframe, expectedFileContent);
          }
          assertedFilesCount++;
        }
      }
    }

    assertEquals(assertedFilesCount, expectedContentList.size());
  }

  private Map<String, List<String>> splitQuery(URL url) {
    if (Strings.isNullOrEmpty(url.getQuery())) {
      return Collections.emptyMap();
    }
    return Arrays.stream(url.getQuery().split("&"))
        .map(this::splitQueryParameter)
        .collect(Collectors
            .groupingBy(SimpleImmutableEntry::getKey, LinkedHashMap::new, Collectors.mapping(Map.Entry::getValue, Collectors.toList())));
  }

  private SimpleImmutableEntry<String, String> splitQueryParameter(String it) {
    final int idx = it.indexOf("=");
    final String key = idx > 0 ? it.substring(0, idx) : it;
    final String value = idx > 0 && it.length() > idx + 1 ? it.substring(idx + 1) : null;
    return new SimpleImmutableEntry<>(
        URLDecoder.decode(key, StandardCharsets.UTF_8),
        URLDecoder.decode(value, StandardCharsets.UTF_8)
    );
  }

  private boolean isImageFile(String fileName) {
    return fileName.toLowerCase().endsWith(".jpg")
        || fileName.toLowerCase().endsWith(".png")
        || fileName.toLowerCase().endsWith(".gif");
  }

  void assertImageFileEquals(String imageURL, byte[] expectedFileContent) throws IOException {
    var migratedImage = getImageFileContent(new URL(imageURL));

    assertArrayEquals("Images after migration must match expected content",
        expectedFileContent, migratedImage);
  }

  void assertMonacoTextFileEquals(WebElement monacoTabIframe, byte[] expectedFileContent) {
    var migratedTextFile = getTextFileContent(monacoTabIframe)
        .replaceAll("\\s", "");
    var expectedTextFile = new String(expectedFileContent, StandardCharsets.UTF_8)
        .replaceAll("\\s", "");

    assertEquals("Text files after migration must match expected content ",
        expectedTextFile, migratedTextFile);
  }

  private byte[] getImageFileContent(URL imageUrl) throws IOException {
    URLConnection connection = imageUrl.openConnection();
    String basicAuth = "Basic " + new String(Base64.getEncoder().encode(DirigibleConnectionProperties.AUTH.getBytes()));
    connection.setRequestProperty("Authorization", basicAuth);
    BufferedInputStream migratedImageStream = new BufferedInputStream(connection.getInputStream());

    return migratedImageStream.readAllBytes();
  }

  String getTextFileContent(WebElement iframe) {
    webBrowser.switchToDefaultContent();
    webBrowser.switchToIframe(iframe);
    webBrowser.sleep(3000);
    var migratedText = webBrowser.executeJavascript("return monaco.editor.getModels().at(0).getValue();");
    webBrowser.switchToDefaultContent();

    return migratedText;
  }

  private static <T> boolean collectionHasElementEndingWith(List<String> list, String endingWith) {
    if (list == null || list.isEmpty()) {
      return false;
    }

    return list.stream().anyMatch(x -> x.endsWith(endingWith));
  }

  @After
  public void afterTest() {
    webBrowser.quit();
  }
}
