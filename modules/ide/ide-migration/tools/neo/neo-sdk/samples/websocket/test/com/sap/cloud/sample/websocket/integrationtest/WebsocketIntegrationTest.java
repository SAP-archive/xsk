package com.sap.cloud.sample.websocket.integrationtest;

import java.io.File;
import java.net.URLEncoder;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Integration test using Selenium.
 */
public class WebsocketIntegrationTest {
    /**
     * Store name of currently executed test method in field.
     */
    @Rule
    public static TestName testName = new TestName();

    private static final String TEST_CALCULATION_INPUT = "2;3";
    private static final String EXPECTED_CALCULATION_RESULT = "5";
    private static final String INTEGRATIONTEST_TIMEOUT_PROP_NAME = "integrationtest.timeout";
    private static final int DEFAULT_INTEGRATIONTEST_TIMEOUT = 25;

    private static String serverUrl;
    private static File screenshotFolder;
    private static int timeout;
    private WebDriver webDriver;
    private WebsocketHomePage homePage;
    private WebDriverWait wait;

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

        // Get test timeout
        String timeoutProp = System.getProperty(INTEGRATIONTEST_TIMEOUT_PROP_NAME);
        if (timeoutProp == null) {
            timeout = DEFAULT_INTEGRATIONTEST_TIMEOUT;
        } else {
            try {
                timeout = Integer.valueOf(timeoutProp);
            } catch (NumberFormatException e) {
                System.out.println("Invalid integrationtest.timeout property value: " + timeoutProp + "; using "
                        + "default timeout " + DEFAULT_INTEGRATIONTEST_TIMEOUT + " seconds");
            }
        }

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
        wait = new WebDriverWait(webDriver, timeout);
        homePage = new WebsocketHomePage(webDriver, serverUrl + "/websocket");
    }

    /**
     * Take a final screenshot at the end of each test and close all browser windows.
     */
    @After
    public void cleanup() throws Exception {
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
     * Select annotations radio button and check the default calculation.
     * @see #checkCalculation()
     */
    @Test
    public void testAnnotatedWebsocket() throws Exception {
        homePage.pressAnnotationsRadioButton();
        checkCalculation();
    }

    /**
     * Select programmatic radio button and check the default calculation.
     * @see #checkCalculation()
     */
    @Test
    public void testProgrammaticWebsocket() throws Exception {
        homePage.pressProgramRadioButton();
        checkCalculation();
    }

    /**
     * Enter two numbers separated by a semicolon in the text field, press calculate button and wait for the expected
     * result to be written back in the result field.
     */
    private void checkCalculation() {
        homePage.enterInputText(TEST_CALCULATION_INPUT);
        homePage.pressCalculateButton();
        wait.until(ExpectedConditions.textToBePresentInElementValue(By.id("textarea_output"),
                EXPECTED_CALCULATION_RESULT));
        String result = homePage.getOutputText();
        Assert.assertTrue("Wrong calculation result: expected " + EXPECTED_CALCULATION_RESULT + " but get " + result,
                result.contains(EXPECTED_CALCULATION_RESULT));
    }
}
