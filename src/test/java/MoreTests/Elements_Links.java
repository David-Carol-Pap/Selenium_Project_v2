package MoreTests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Elements_Links {
    public WebDriver driver;

    @Test
    public void links() {
        driver = new ChromeDriver();
        driver.get("https://demoqa.com/links");
        driver.manage().window().maximize();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,400)");

        WebElement home = driver.findElement(By.id("simpleLink"));
        home.click();

//        WebElement homeDynamic=driver.findElement(By.id("dynamicLink"));
//        homeDynamic.click();
//        WebElement homeDynamic = driver.findElement(By.xpath("//*[@id='linkWrapper']/p[2]/a"));
//        homeDynamic.click();
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[@id='linkWrapper']/p[2]/a")));

//        WebElement created=driver.findElement(By.xpath("//*[@id='linkWrapper']/p[3]/a[text()='Created']"));
//        created.click();
        driver.findElement(By.xpath("//*[@id='linkWrapper']/p[3]/a[text()='Created']")).click();
        driver.findElement(By.xpath("//*[@id='linkWrapper']/p[4]/a[text()='No Content']")).click();
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[@id='linkWrapper']/p[5]/a[text()='Moved']")));
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[@id='linkWrapper']/p[6]/a[text()='Bad Request']")));
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[@id='linkWrapper']/p[7]/a[text()='Unauthorized']")));
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[@id='linkWrapper']/p[8]/a[text()='Forbidden']")));
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[@id='linkWrapper']/p[9]/a[text()='Not Found']")));






    }
}
