package MoreTests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class Elements_TextBox {
    public WebDriver driver;

    @Test
    public void textBox() {
        driver = new EdgeDriver();
        driver.get("https://demoqa.com/");
        driver.manage().window().maximize();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,400)");
        WebElement elementsField = driver.findElement(By.xpath("//h5[text()='Elements']"));
        elementsField.click();
        WebElement textBoxField = driver.findElement(By.xpath("//span[text()='Text Box']"));
        textBoxField.click();

        WebElement fullNameFiled = driver.findElement(By.id("userName"));
        String fullNameValue = "Radu Chindris";
        String expectedNameResult = "Name:" + fullNameValue;

        fullNameFiled.sendKeys(fullNameValue);
        WebElement emailField = driver.findElement(By.id("userEmail"));
        String emailValue = "r@r.com";
        emailField.sendKeys(emailValue);
        String expectedEmailResult = "Email:" + emailValue;

        WebElement currentAddressField = driver.findElement(By.id("currentAddress"));
        String currentAddressValue = "Fabricii de Zahar nr 3";
        currentAddressField.sendKeys(currentAddressValue);
        String expectedCurrentAdressResult = "Current Address :" + currentAddressValue;

        WebElement permanentAddressField = driver.findElement(By.id("permanentAddress"));
        String permanentAddressValue = "Oasului 321";
        permanentAddressField.sendKeys(permanentAddressValue);
        String expectedPermanentAdressResult = "Permananet Address :" + permanentAddressValue;

        WebElement submitButton = driver.findElement(By.id("submit"));
        js.executeScript("arguments[0].click();", submitButton);
        //  submitButton.click();

        List<String> expectedValues = Arrays.asList(expectedNameResult, expectedEmailResult, expectedCurrentAdressResult, expectedPermanentAdressResult);
        List<WebElement> lista = driver.findElements(By.xpath("//div[@class='border col-md-12 col-sm-12']/p"));
        for (int i = 0; i <= 3; i++) {
            WebElement coloanaLabels1 = lista.get(i);
            System.out.println("Lable: " + coloanaLabels1.getText());
            Assert.assertEquals(coloanaLabels1.getText(), expectedValues.get(i));
        }
    }
}



