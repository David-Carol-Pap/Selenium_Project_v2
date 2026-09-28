package HelperMethods;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptMethods {
    WebDriver driver;
    JavascriptExecutor js;

    public JavaScriptMethods(WebDriver driver) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
    }

    public void javaScriptScrollPage(int x, int y) {
        js.executeScript("window.scrollBy(" + x + ", " + y + ")");
    }

    public void forceClick(WebElement element) {
        js.executeScript("arguments[0].click();", element);
    }
}

