package com.xsk.integration.tests.migration;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.AesKeyStrength;
import net.lingala.zip4j.model.enums.EncryptionMethod;
import org.apache.commons.io.FileUtils;
import org.eclipse.dirigible.commons.config.Configuration;
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
  private final WebDriver browser;
  private final MigrationCredentials credentials;
  private final String testName;
  private final String zipPassword;

  public SeleniumLogger(WebDriver browser, MigrationCredentials credentials, String testName) {
    this.browser = browser;
    this.credentials = credentials;
    this.testName = testName;
    this.zipPassword = Configuration.get("ITESTS_SELENIUM_ARTIFACT_PASSWORD");
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
    source = credentials.removePasswordsFromString(source);
    String fileName = PREFIX + "/" + PREFIX + SEPARATOR + testName + SEPARATOR + pageSourceID + ".html";
    File file = new File(fileName);
    FileUtils.write(file, source, StandardCharsets.UTF_8);
    System.out.println("[SeleniumOut - Saved Page HTML]: " + file.getAbsolutePath());
    pageSourceID++;
  }

  void save() throws IOException {
    try {
      saveScreenshot();
      savePageHtml();
    } catch (Throwable exception) {
      System.out.println("[SeleniumOut - Warning]: Could not save snapshot for some reason.");
      throw exception;
    }
  }

  void finish() throws ZipException {
    ZipParameters zipParameters = new ZipParameters();
    zipParameters.setEncryptFiles(true);
    zipParameters.setEncryptionMethod(EncryptionMethod.AES);
    zipParameters.setAesKeyStrength(AesKeyStrength.KEY_STRENGTH_256);
    ZipFile zipFile = new ZipFile("SeleniumOut.zip", zipPassword.toCharArray());
    zipFile.addFolder(new File("./SeleniumOut"), zipParameters);
    System.out.println("[SeleniumOut - Zipped all output to: " + zipFile.getFile().getAbsolutePath());
  }
}
