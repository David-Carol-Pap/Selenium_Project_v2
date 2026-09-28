package MoreTests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Elements_CheckBox {
    public WebDriver driver;
    @Test

    public void checkBox() {
        driver = new ChromeDriver();
        driver.get("https://demoqa.com/text-box");
        driver.manage().window().maximize();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,400)");

        WebElement textBox = driver.findElement(By.xpath("//span[text()='Check Box']"));
        textBox.click();

        //button[@class='rct-collapse rct-collapse-btn']
        WebElement dropDownListHome = driver.findElement(By.xpath("//button[@title='Toggle']//*[name()='svg']"));
        dropDownListHome.click();

        WebElement dropDownListDesktop = driver.findElement(By.xpath("//li[@class='rct-node rct-node-parent rct-node-expanded']//li[1]//span[1]//button[1]//*[name()='svg']"));
        dropDownListDesktop.click();
        WebElement selectNotes = driver.findElement(By.xpath("//label[@for='tree-node-notes']//span[@class='rct-checkbox']"));
        selectNotes.click();

        WebElement dropDownListDocuments = driver.findElement(By.xpath("//li[2]//span[1]//button[1]//*[name()='svg']"));
        dropDownListDocuments.click();
        WebElement selectOffice = driver.findElement(By.xpath("//label[@for='tree-node-office']//span[@class='rct-checkbox']"));
        selectOffice.click();

        WebElement dropDownListDownlods = driver.findElement(By.xpath("//*[@id='tree-node']/ol/li/ol/li[3]/span/button"));
        dropDownListDownlods.click();
        WebElement selectWordFile = driver.findElement(By.xpath("//span[text()='Word File.doc']"));
        selectWordFile.click();
        WebElement selectExcelFile = driver.findElement(By.xpath("//span[text()='Excel File.doc']"));
        selectExcelFile.click();

    }
}
