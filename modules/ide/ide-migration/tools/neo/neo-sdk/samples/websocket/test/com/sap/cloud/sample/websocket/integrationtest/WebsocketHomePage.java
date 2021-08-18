package com.sap.cloud.sample.websocket.integrationtest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page object for home page of this sample.
 */
public class WebsocketHomePage {
    @FindBy(id = "radio_annotations")
    private WebElement annotationsRadioButton;

    @FindBy(id = "radio_programmatic")
    private WebElement programRadioButton;

    @FindBy(id = "textarea_input")
    private WebElement inputField;

    @FindBy(id = "textarea_output")
    private WebElement outputField;

    @FindBy(id = "button_calculate")
    private WebElement calculateButton;

    /**
     * Construct Selenium page object from home page loaded with given web driver from given application URL.
     */
    public WebsocketHomePage(WebDriver webDriver, String applicationUrl) {
        webDriver.get(applicationUrl + "/");
        PageFactory.initElements(webDriver, this);
    }

    /**
     * Enter text into the input field on this page.
     */
    public void enterInputText(String text) {
        inputField.click();
        inputField.clear();
        inputField.sendKeys(text);
    }

    /**
     * Get text of the output field on this page.
     */
    public String getOutputText() {
        return outputField.getAttribute("value");
    }

    /**
     * Press calculate button on this page.
     */
    public void pressCalculateButton() {
        calculateButton.click();
    }

    /**
     * Press program radio button on this page.
     */
    public void pressProgramRadioButton() {
        programRadioButton.click();
    }

    /**
     * Press annotations radio button on this page.
     */
    public void pressAnnotationsRadioButton() {
        annotationsRadioButton.click();
    }

    /**
     * Check if annotations radio button on this page is selected.
     */
    public boolean isAnnotationsRadioButtonSelected() {
        return annotationsRadioButton.isSelected();
    }
}
