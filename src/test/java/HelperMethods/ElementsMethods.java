package HelperMethods;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.io.File;
import java.time.Duration;
import java.util.List;

public class ElementsMethods {
    private final WebDriver driver;
    private final Actions actions;

    public ElementsMethods(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
    }

    public void clickOnElements(WebElement element) {
        element.click();
    }

    public void enterElement(WebElement element) {
        element.sendKeys(Keys.ENTER);
    }

    public void fillElement(WebElement element, String value) {
        element.sendKeys(value);
    }

    public void fillElement(WebElement element, Keys value) {
        element.sendKeys(value);
    }

    public void clearAndFillElement(
            WebElement element,
            String value
    ) {
        element.click();
        element.sendKeys(Keys.CONTROL, "a");
        element.sendKeys(Keys.BACK_SPACE);
        element.sendKeys(value);
    }

    public void uploadPictures(WebElement element) {
        String file =
                new File("src/test/resources/Emag.png")
                        .getAbsolutePath();

        element.sendKeys(file);
    }

    public void selectElementFromListByText(
            List<WebElement> elementsList,
            String value
    ) {
        for (WebElement element : elementsList) {
            if (element.getText().equals(value)) {
                clickOnElements(element);
                break;
            }
        }
    }

    public void selectTextFromAnElement(WebElement webElement) {
        webElement.getText();
    }

    public void fillWithActions(
            WebElement webElement,
            String value
    ) {
        actions.sendKeys(value).perform();
        waitVisibilityElement(webElement);
        actions.sendKeys(Keys.ENTER).perform();
    }

    public void waitVisibilityElement(WebElement webElement) {
        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(
                ExpectedConditions.visibilityOf(webElement)
        );
    }

    public void fillMultipleValues(
            WebElement webElement,
            List<String> values
    ) {
        for (String value : values) {
            webElement.sendKeys(value);
            waitVisibilityElement(webElement);
            webElement.sendKeys(Keys.ENTER);
        }
    }

    public void clickMultipleValues(
            List<WebElement> webElements,
            List<String> values
    ) {
        for (String value : values) {
            for (WebElement webElement : webElements) {
                if (webElement.getText().equals(value)) {
                    webElement.click();
                    break;
                }
            }
        }
    }
}
