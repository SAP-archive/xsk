package com.xsk.integration.tests.migration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class SeleniumLogger {
  private static final String PREFIX = "SeleniumOut";
  private static final String SEPARATOR = "-";
  private int screenshotID = 0;
  private int pageSourceID = 0;
  private WebDriver browser;
  private String testName;

  public SeleniumLogger(WebDriver browser, String testName) {
    this.browser = browser;
    this.testName = testName;
  }

  void saveScreenshot() throws IOException {
    TakesScreenshot scrShot =((TakesScreenshot)browser);
    File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
    String fileName = PREFIX + "/" + PREFIX + SEPARATOR + testName + SEPARATOR + screenshotID + ".jpg";
    File DestFile=new File(fileName);
    System.out.println("[SeleniumOut - Saved Page Screenshot]: " + DestFile.getAbsolutePath());
    FileUtils.copyFile(SrcFile, DestFile);
    screenshotID++;
  }

  void savePageHtml() throws IOException {
    var source = browser.getPageSource();
    String fileName = PREFIX + "/" + PREFIX + SEPARATOR + testName + SEPARATOR + pageSourceID + ".html";
    File file = new File(fileName);
    FileUtils.write(file, source, StandardCharsets.UTF_8);
    System.out.println("[SeleniumOut - Saved Page HTML]: " + file.getAbsolutePath());
    pageSourceID++;
  }

  void save() throws IOException {
    saveScreenshot();
    savePageHtml();
  }
}
