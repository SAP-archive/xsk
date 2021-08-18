package com.sap.cloud.sample.ui5.integrationtest;

import static org.hamcrest.Matchers.containsString;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Integration test using Selenium.
 */
public class ExploreUI5IntegrationTest {
    /**
     * Store name of currently executed test method in field.
     */
    @Rule
    public static TestName testName = new TestName();

    private static String serverUrl;
    private static File screenshotFolder;

    private WebDriver webDriver;
    private ExploreUI5HomePage homePage;

    /**
     * Take a provided server URL usually fed in from outside through the build process or default to the local server
     * as provided through Eclipse to run the integration test against.
     */
    @BeforeClass
    public static void setupSuite() throws Exception {
        // Get integration test server URL
        serverUrl = System.getProperty("integration.test.server.url");
        if (serverUrl == null) {
            serverUrl = "http://localhost:8080";
        }
        System.out.println("Running against " + serverUrl);

        // Get screenshot folder
        String screenshotPath = System.getProperty("integration.test.screenshot.path");
        if (screenshotPath == null) {
            screenshotPath = ".";
        }
        screenshotFolder = new File(screenshotPath, URLEncoder.encode(serverUrl, "utf-8"));
        if (!screenshotFolder.exists()) {
            screenshotFolder.mkdirs();
        }
        screenshotFolder = screenshotFolder.getCanonicalFile();
        System.out.println("Saving screenshots in " + screenshotFolder.getAbsolutePath());
    }

    /**
     * Open the sample home page for each test.
     */
    @Before
    public void setupTest() throws Exception {
        // Open home page on Firefox, i.e. installed web browser Firefox required as described in readme.txt
        DesiredCapabilities capabilities = new DesiredCapabilities();
        String proxyHost = System.getProperty("https.proxyHost");
        String proxyPort = System.getProperty("https.proxyPort");
        if (proxyHost != null) {
            System.out.println("Configuring Firefox Selenium web driver with proxy " + proxyHost
                    + (proxyPort == null ? "" : ":" + proxyPort) + " (requires Firefox browser)");
            Proxy proxy = new Proxy();
            String proxyString = proxyHost + (proxyPort == null ? "" : ":" + proxyPort);
            proxy.setHttpProxy(proxyString).setSslProxy(proxyString);
            proxy.setNoProxy("localhost");
            capabilities.setCapability(CapabilityType.PROXY, proxy);
        } else {
            System.out.println("Configuring Firefox Selenium web driver without proxy (requires Firefox browser)");
        }
        webDriver = null;
        int retryCount = 0;
        do {
            // Under load the Selenium Firefox driver may fail to connect, so let's try a couple of times
            try {
                webDriver = new FirefoxDriver(capabilities);
            } catch (WebDriverException exception) {
                if (++retryCount == 3) {
                    throw new RuntimeException("Failed to create web driver/connect to browser for the third time",
                            exception);
                }
            }
        } while (webDriver == null);
        homePage = new ExploreUI5HomePage(webDriver, serverUrl + "/explore-ui5");
    }

    /**
     * Take a final screenshot at the end of each test and close all browser windows.
     */
    @After
    public void cleanup() throws IOException {
        // Take final screenshot
        File tempFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
        String targetName = getClass().getSimpleName() + "." + testName.getMethodName() + ".png";
        File targetFile = new File(screenshotFolder, targetName);
        FileUtils.copyFile(tempFile, targetFile);
        System.out.println("Screenshot for test " + testName.getMethodName() + " saved in "
                + targetFile.getAbsolutePath());

        // Close the web driver, closing all windows
        webDriver.quit();
    }

    /**
     * Enter text into text field, press button to see it as label, check label for the entered text.
     */
    @Test
    public void testExploreUI5HomePage() throws Exception {
        String text = "Hello World!";
        homePage.enterText(text);
        homePage.pressButton();
        Assert.assertThat(homePage.getLabel(), containsString(text));
    }
}