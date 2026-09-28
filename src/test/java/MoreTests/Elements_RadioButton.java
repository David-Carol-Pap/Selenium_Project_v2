package MoreTests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Elements_RadioButton {

    public WebDriver driver;
    @Test

    public void radioButton(){
        driver=new ChromeDriver();
        driver.get("https://demoqa.com/elements");
        driver.manage().window().maximize();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,400)");

        WebElement radioButton=driver.findElement(By.xpath("//span[text()='Radio Button']"));
        radioButton.click();

       // WebElement yesButton=driver.findElement(By.xpath("//label[@for='yesRadio']"));
        WebElement impressiveButton=driver.findElement(By.xpath("//label[@for='impressiveRadio']"));
        impressiveButton.click();
    }
}
