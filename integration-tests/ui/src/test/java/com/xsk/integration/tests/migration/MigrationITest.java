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
package com.xsk.integration.tests.migration;

import com.google.common.base.Strings;
import com.sap.xsk.integration.tests.core.client.http.XSKHttpClient;
import com.sap.xsk.integration.tests.core.client.http.local.LocalXSKHttpClient;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.util.EntityUtils;
import org.eclipse.dirigible.commons.config.Configuration;
import org.junit.After;
import org.junit.Test;

import static com.xsk.integration.tests.migration.DirigibleConnectionProperties.LOCALHOST_URI;

import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@RunWith(JUnitParamsRunner.class)
public class MigrationITest {

  private final ExpectedContentProvider expectedContentProvider = ExpectedContentProvider.getInstance();
  private WebBrowser webBrowser;
  private MigrationCredentials credentials;
  private Map<String, List<ExpectedContent>> expectedContentList;
  private final Map<String, Boolean> contentComparisons = new HashMap<>();

  @Test
  @Parameters({"Chrome", /* "Firefox" */})
  public void migrationTest(String param) throws IOException {
    setup(param);
    loginIfNecessary();
    navigateToMigrationPerspective();
    enterNeoDBTunnelCredentials();
    enterHanaCredentials();
    selectDeliveryUnits();
    approveChanges();
    goToWorkspace();
    validateProjectFIles();
    assertComparisonResultsTrue();
  }

  private void setup(String param) {
    boolean isHeadless = Configuration.get("ITESTS_SELENIUM_MODE").equals("headless");
    webBrowser = new WebBrowser(param, DirigibleConnectionProperties.BASE_URL, isHeadless);
    credentials = new MigrationCredentials();
    expectedContentList = expectedContentProvider.getExpectedContentList();
  }

  private void loginIfNecessary() {
    try {
      webBrowser.waitForVisibilityOfElement(By.xpath("/html/body/div/div/h3[text() = 'Sign in to SAP XSK']"));
    } catch (Throwable t) {
      return; // assume we're already logged in
    }

    webBrowser.log();
    webBrowser.switchToDefaultContent();
    webBrowser.enterAndAssertField(By.name("j_username"), DirigibleConnectionProperties.AUTH_USERNAME);
    webBrowser.enterAndAssertField(By.name("j_password"), DirigibleConnectionProperties.AUTH_PASSWORD);
    webBrowser.log();
    webBrowser.submitForm(By.name("loginForm"));
  }

  private void navigateToMigrationPerspective() {
    webBrowser.clickItem(By.xpath("//*[@title=\"SAP HANA XS Classic Migration\"]"));
    webBrowser.waitForPageWithTitle("SAP HANA XS Classic Migration | XSK WebIDE");
    webBrowser.switchToDefaultContent();
    webBrowser.switchToIframe(By.xpath("//iframe[@src='../ide-migration/migration-launch.html']"));
    webBrowser.clickItem(By.xpath("//*[@ng-click='selectLiveMigration()']"));
    webBrowser.log();
  }

  private void enterNeoDBTunnelCredentials() {
    webBrowser.enterAndAssertField(By.id("subaccount"), credentials.getSubaccount());
    webBrowser.selectAndAssertDropdown("regionList", (item) -> item.contains(credentials.getRegion()));
    webBrowser.enterAndAssertField(By.id("neo-username"), credentials.getUsername());
    webBrowser.enterAndAssertField(By.id("neo-password"), credentials.getPassword());
    webBrowser.log();
    webBrowser.clickItem(By.xpath("//*[@ng-click='goForward()']"));
  }

  private void enterHanaCredentials() {
    webBrowser.selectAndAssertDropdown("databasesList", (item) -> item.equals(credentials.getSchema()));
    webBrowser.enterAndAssertField(By.id("username"), credentials.getHanaUsername());
    webBrowser.enterAndAssertField(By.id("password"), credentials.getHanaPassword());
    webBrowser.log();
    webBrowser.clickItem(By.xpath("//*[@ng-click='goForward()']"));
  }

  private void selectDeliveryUnits() {
    webBrowser.selectAndAssertDropdown("workspacesList", (item) -> item.equals(expectedContentProvider.getExpectedWorkspaceName()));
    webBrowser.selectAndAssertDropdown("deliveryUnitList", (item) -> item.equals(expectedContentProvider.getExpectedDeliveryUnitName()));
    webBrowser.clickItem(By.xpath("//*[@ng-disabled=\"duDropdownDisabled\"]"));
    webBrowser.log();
    webBrowser.clickItem(By.xpath("//*[@ng-click=\"goForward()\"]"));
  }

  private void approveChanges() {
    webBrowser.log();
    webBrowser.clickItem(By.xpath("//*[@ng-click=\"continueMigration()\"]"));
  }

  private void goToWorkspace() {
    webBrowser.clickItem(By.xpath("//*[@ng-click=\"goToWorkspace()\"]"));
    webBrowser.waitForPageWithTitle("Workbench | XSK");
    webBrowser.switchToDefaultContent();
    webBrowser.log();
  }

  private void validateProjectFIles() throws IOException {
    expandJsTree();
    for (var projectName : expectedContentList.keySet()) {
      for (var file : expectedContentList.get(projectName)) {
        switchToWorkspaceFrame();
        openFileInJsTree(file);
        validateProjectFile(file);
        webBrowser.log();
        closeFileTab(file);
      }
      webBrowser.log();
    }
  }

  private void expandJsTree() {
    switchToWorkspaceFrame();
    webBrowser.executeJavascript("$(\".jstree\").jstree(\"open_all\")");
    webBrowser.log();
  }

  private void switchToWorkspaceFrame() {
    webBrowser.switchToDefaultContent();
    webBrowser.switchToIframe(By.xpath("//iframe[@src='../ide-projects/projects.html']"));
    webBrowser.waitForVisibilityOfElement(By.id("j1_1_anchor"));
  }

  void openFileInJsTree(ExpectedContent file) {
    var projectName = file.getProject();
    var fileName = Paths.get(file.getFilePath()).getFileName();

    // Find the project folder's jstree node.
    By projectAnchorXpath = By.xpath("//*[text()='" + projectName + "']");
    var projectAnchors = webBrowser.findAllVisibleWebElements(projectAnchorXpath);
    if (projectAnchors.size() != 1) {
      throw new RuntimeException("Selenium test error: zero or multiple jstree anchors for project found.");
    }
    var projectAnchor = projectAnchors.get(0).findElement(By.xpath("./.."));

    // Find the file's jstree node.
    var fileAnchors = projectAnchor.findElements(By.xpath(".//*[text()='" + fileName + "']"));
    if (fileAnchors.size() != 1) {
      throw new RuntimeException("Selenium test error: zero or multiple jstree anchors for file " + fileName + " found.");
    }
    var fileAnchor = fileAnchors.get(0);

    // Open the file by clicking on the jstree node or via context menu.
    if (isNonTextEditorFile(file.getFilePath())) {
      webBrowser.scrollIntoView(fileAnchor);
      webBrowser.contextClick(fileAnchor);
      webBrowser.switchToDefaultContent();

      String openWithMenuItemXpath = "//*[text()='Open With']";
      String codeEditorMenuItemXpath = "//*[text()='Code Editor']";

      webBrowser.moveTo(By.xpath(openWithMenuItemXpath));
      webBrowser.clickItem(By.xpath(openWithMenuItemXpath));

      webBrowser.moveTo(By.xpath(codeEditorMenuItemXpath));
      webBrowser.clickItem(By.xpath(codeEditorMenuItemXpath));

      webBrowser.switchToIframe(By.xpath("//iframe[@src='../ide-projects/projects.html']"));
      webBrowser.waitForVisibilityOfElement(By.id("j1_1_anchor"));
    } else {
      webBrowser.scrollIntoView(fileAnchor);
      webBrowser.doubleClickItem(fileAnchor);
    }
  }

  private void validateProjectFile(ExpectedContent file) throws IOException {
    webBrowser.switchToDefaultContent();

    var filePath = file.getFilePath();
    var iframes = webBrowser.findElementsBy(By.tagName("iframe"));

    for (var iframe : iframes) {
      var srcAttribute = iframe.getAttribute("src");
      var parsedSrc = splitQuery(new URL(srcAttribute));
      var fileQueryParameter = parsedSrc.get("file");

      if (collectionHasElementEndingWith(fileQueryParameter, filePath)) {
        if (isImageFile(filePath)) {
          assertImageFileEquals(iframe, file);
        } else {
          assertMonacoTextFileEquals(iframe, file);
        }
      }
    }
  }

  private void closeFileTab(ExpectedContent file) {
    webBrowser.switchToDefaultContent();
    var fileName = Paths.get(file.getFilePath()).getFileName().toString();
    var tabs = webBrowser.findElementsBy(By.xpath("//*[@title='" + fileName + "']"));
    tabs.forEach(tab -> {
      var closeTabs = tab.findElements(By.className("lm_close_tab"));
      closeTabs.forEach(WebElement::click);
    });
    webBrowser.switchToDefaultContent();
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

  private boolean isImageFile(String filePath) {
    return filePath.toLowerCase().endsWith(".jpg")
        || filePath.toLowerCase().endsWith(".png")
        || filePath.toLowerCase().endsWith(".gif");
  }

  private boolean isNonTextEditorFile(String filePath) {
    // The following files use a gui editor and don't open by default as text.
    return filePath.toLowerCase().endsWith(".csv")
        || filePath.toLowerCase().endsWith(".hdi");
  }

  void assertImageFileEquals(WebElement imageTabIframe, ExpectedContent file) throws IOException {
    var migratedImage = getImageFileContent(file.getFilePath(), imageTabIframe);
    var expectedFileContent = file.getContent();

    boolean comparisonResult = Arrays.equals(expectedFileContent, migratedImage);
    contentComparisons.put(file.getFilePath(), comparisonResult);
    if(!comparisonResult) {
      System.out.println(
              "\n"
            + "[MigrationITest] Unexpected image file content! "
            + file.getFilePath()
            + "\n Expected Byte Length: \n"
            + expectedFileContent.length
            + "\n Actual Byte Length: \n"
            + migratedImage.length + "\n"
      );
    }
  }

  void assertMonacoTextFileEquals(WebElement monacoTabIframe, ExpectedContent file) {
    var migratedTextFile = getTextFileContent(monacoTabIframe)
        .replaceAll("\\s", "");
    var expectedTextFile = new String(file.getContent(), StandardCharsets.UTF_8)
        .replaceAll("\\s", "");

    boolean comparisonResult = expectedTextFile.equals(migratedTextFile);
    contentComparisons.put(file.getFilePath(), comparisonResult);
    if(!comparisonResult) {
      System.out.println(
            "\n"
          + "[MigrationITest]  Unexpected text file content! "
          + file.getFilePath()
          + "\n Expected: \n"
          + expectedTextFile
          + "\n Actual: \n"
          + migratedTextFile + "\n"
      );
    }
  }

  private byte[] getImageFileContent(String filePath, WebElement imageTabIframe) throws IOException {
    webBrowser.switchToDefaultContent();
    webBrowser.switchToIframe(imageTabIframe);
    var imageUrl = new URL(
        "http://"
            + DirigibleConnectionProperties.HOST
            + ":" + DirigibleConnectionProperties.PORT
            + "/services/v4/ide/workspaces/"
            + expectedContentProvider.getExpectedWorkspaceName()
            + filePath
    );

    XSKHttpClient client = LocalXSKHttpClient.create(LOCALHOST_URI);
    try {
      HttpUriRequest request = RequestBuilder.get(imageUrl.toURI()).build();
      var response = client.executeRequestAsync(request).get();
      HttpEntity entity = response.getEntity();
      webBrowser.retryJavascriptWithTimeout(
          "var image = document.getElementById('image-view');\n"
          + "var isLoaded = image.complete && image.naturalHeight !== 0; \n"
              + "isLoaded ? true : new Error(\"Image is not loaded.\");",
          10000,
          100
      );
      webBrowser.switchToDefaultContent();
      return EntityUtils.toByteArray(entity);
    } catch (URISyntaxException | InterruptedException | ExecutionException e) {
      throw new RuntimeException(e);
    }
  }

  String getTextFileContent(WebElement monacoTabIframe) {
    webBrowser.switchToDefaultContent();
    webBrowser.switchToIframe(monacoTabIframe);
    String migratedText = webBrowser.retryJavascriptWithTimeout(
        "return monaco.editor.getModels().at(0).getValue()",
        10000,
        100
    );
    webBrowser.switchToDefaultContent();
    return migratedText;
  }

  private static <T> boolean collectionHasElementEndingWith(List<String> list, String endingWith) {
    if (list == null || list.isEmpty()) {
      return false;
    }

    return list.stream().anyMatch(x -> x.endsWith(endingWith));
  }

  private void assertComparisonResultsTrue() {
    boolean finalResult = true;
    for(var fileResult : contentComparisons.entrySet()) {
      if(!fileResult.getValue()) {
        finalResult = false;
        System.out.println(
            "SELENIUM CONTENT VALIDATION ERROR: '"
            + fileResult.getKey()
            + "' MIGRATED WITH WRONG CONTENT!");
      }
    }

    if(finalResult) {
      System.out.println("Selenium Migration was successful!");
    } else {
      throw new RuntimeException("Selenium Migration FAILED due to unexpected content!");
    }
  }

  @After
  public void afterTest() {
    boolean quitAfterTest = Configuration.get("ITESTS_SELENIUM_MODE").equals("headless");
    if(quitAfterTest) {
      webBrowser.quit();
    }
  }
}
