package com.xsk.integration.tests.migration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class SeleniumLogger {

  private static final String PREFIX = "selenium";
  private static final String SEPARATOR = "-";
  private int screenshotID = 0;
  private final WebDriver browser;
  private final String testName;

  public SeleniumLogger(WebDriver browser, String testName) {
    this.browser = browser;
    this.testName = testName;
  }

  private String makeFileName(String extension) {
    return PREFIX + "/" + testName + "/" + testName + SEPARATOR + screenshotID + extension;
  }

  void saveScreenshot() throws IOException {
    TakesScreenshot scrShot = ((TakesScreenshot) browser);
    File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
    File DestFile = new File(makeFileName(".jpg"));
    FileUtils.copyFile(SrcFile, DestFile);
    System.out.println("[Selenium - Saved Page Screenshot]: " + DestFile.getAbsolutePath());
  }

  void savePageHtml() throws IOException {
    var source = browser.getPageSource();
    File file = new File(makeFileName(".html"));
    FileUtils.write(file, source, StandardCharsets.UTF_8);
    System.out.println("[Selenium - Saved Page HTML]: " + file.getAbsolutePath());
  }

  void save() throws IOException {
    try {
      screenshotID++;
      saveScreenshot();
      savePageHtml();
    } catch (Throwable exception) {
      System.out.println("[Selenium - Warning]: Could not save snapshot.");
      throw exception;
    }
  }
}