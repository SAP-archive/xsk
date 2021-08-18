package com.sap.cloud.sample.ui5.integrationtest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page object for home page of this sample.
 */
public class ExploreUI5HomePage {
    @FindBy(id = "testTextField")
    private WebElement textField;

    @FindBy(id = "testButton")
    private WebElement button;

    @FindBy(id = "testLabel")
    private WebElement label;

    /**
     * Construct Selenium page object from home page loaded with given web driver from given application URL.
     */
    public ExploreUI5HomePage(WebDriver webDriver, String applicationUrl) {
        webDriver.get(applicationUrl + "/");
        PageFactory.initElements(webDriver, this);
    }

    /**
     * Enter text into text field on this page.
     */
    public void enterText(String text) {
        textField.clear();
        textField.sendKeys(text);
    }

    /**
     * Press button on this page.
     */
    public void pressButton() {
        button.click();
    }

    /**
     * Get text of label on this page.
     */
    public String getLabel() {
        return label.getText();
    }
}