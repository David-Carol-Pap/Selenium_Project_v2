package MoreTests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class Elements_Buttons {
    WebDriver driver;

    @Test
    public void buttons() {
        driver = new ChromeDriver();
        driver.get("https://demoqa.com/elements");
        driver.manage().window().maximize();
        // Implicit wait for elements to load
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,400)");
        WebElement listButtonField = driver.findElement(By.xpath("//li[@id='item-4']//span[normalize-space()='Buttons']"));
        listButtonField.click();
        

        WebElement buttonDoubleClickField = driver.findElement(By.id("doubleClickBtn"));
        WebElement buttonRightClickField = driver.findElement(By.id("rightClickBtn"));

        // Use WebDriverWait to wait for the element
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement dynamicButtonField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='mt-4']//button[text()='Click Me']")));
        js.executeScript("arguments[0].scrollIntoView(true);", buttonDoubleClickField);
        js.executeScript("arguments[0].scrollIntoView(true);", buttonRightClickField);
        js.executeScript("arguments[0].scrollIntoView(true);", dynamicButtonField);

        // Perform click action
        Actions actions = new Actions(driver);
        actions.doubleClick(buttonDoubleClickField).perform();
        actions.contextClick(buttonRightClickField).perform();
        js.executeScript("arguments[0].click();", dynamicButtonField);
        // dynamicButtonField.click();


    }

}
