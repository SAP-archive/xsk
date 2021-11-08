package com.xsk.integration.tests.migration;

import io.github.bonigarcia.wdm.WebDriverManager;
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
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

class WebBrowser {
  private WebDriver browser;
  private WebDriverWait browserWait;
  private Actions browserActions;
  private JavascriptExecutor jsExecutor;
  private final String baseUrl;
  private final boolean isHeadless;

  WebBrowser(String param, String baseUrl, boolean isHeadless) {
    this.baseUrl = baseUrl;
    this.isHeadless = isHeadless;
    setupBrowser(param);
  }

  private void setupBrowser(String param) {
    if (param.contains("Chrome")) {
      setupChrome();
    } else {
      setupFirefox();
    }
  }

  private void setupBrowserCommon(WebDriver webDriver) {
    browser = webDriver;
    browser.navigate().to(baseUrl);
    browser.manage().window().maximize();
    browser.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    jsExecutor = (JavascriptExecutor) (browser);
    browserWait = new WebDriverWait(browser, 60);
    browserActions = new Actions(browser);
  }

  private void setupChrome() {
    WebDriverManager.chromedriver().setup();
    ChromeOptions options = new ChromeOptions();
    options.setHeadless(isHeadless);
    options.addArguments("--no-sandbox");
    options.addArguments("--disable-dev-shm-usage");
    options.addArguments("--window-size=1920,1080");
    options.addArguments("--incognito");

    setupBrowserCommon(new ChromeDriver(options));
  }

  private void setupFirefox() {
    WebDriverManager.firefoxdriver().setup();
    FirefoxOptions options = new FirefoxOptions();
    options.setHeadless(isHeadless);
    options.addArguments("--height=1080", "--width=1920", "-private");

    setupBrowserCommon(new FirefoxDriver(options));
  }

  void clickItem(By by) {
    browserWait.until(ExpectedConditions.visibilityOfElementLocated(by)).click();
  }

  void switchToIframe(By by) {
    browserWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(by));
  }

  void switchToIframe(WebElement iframe) {
    browserWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframe));
  }

  void enterAndAssertField(By by, String value) {
    var field = browser.findElement(by);
    field.sendKeys(value);
    assertEquals("Input field value doesn't match sent keys.",
        value, field.getAttribute("value"));
  }

  void selectAndAssertDropdown(String listName, String selectionValue) {
    var dropdownList = browserWait.until(
        ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[@ng-repeat=\"option in " + listName + "\"]")));
    WebElement dropdownButton = dropdownList.get(0).findElement(By.xpath("./../../button"));
    browserWait.until(ExpectedConditions.elementToBeClickable(dropdownButton)).click();
    var selection = dropdownList.stream()
        .filter((WebElement it) -> it.getAttribute("innerHTML").contains(selectionValue)).collect(Collectors.toList());
    browserWait.until(ExpectedConditions.elementToBeClickable(selection.get(0)));
    assertEquals("Only one dropdown item should be selected.", 1, selection.size());
    selection.get(0).click();
  }

  void waitForPageWithTitle(String pageTitle) {
    browserWait.until(ExpectedConditions.titleIs(pageTitle));
  }

  void switchToDefaultContent() {
    browser.switchTo().defaultContent();
  }

  void waitForVisibilityOfElement(By by) {
    browserWait.until(ExpectedConditions.visibilityOfElementLocated(by));
  }

  String executeJavascript(String javascript) {
    return (String) jsExecutor.executeScript(javascript);
  }

  void doubleClickVisibleElementBy(By by) {
    var anchor = browserWait.until(ExpectedConditions.visibilityOfElementLocated(by));
    browserActions.doubleClick(anchor).perform();
  }

  List<WebElement> findElementsBy(By by) {
    return browser.findElements(by);
  }

  void sleep(long millis) throws RuntimeException {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  void quit() {
    if (browser != null) {
      browser.quit();
    }
  }
}
