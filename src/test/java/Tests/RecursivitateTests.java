package Tests;

import ShareData.Hooks;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.List;

public class RecursivitateTests extends Hooks {
    public WebDriver driver;

    @Test
    public void parcurgereLista() {
        driver = new ChromeDriver();
        driver.get("https://demoqa.com/sortable");
        driver.manage().window().maximize();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 400)");
        List<WebElement> listaElement = driver.findElements(By.xpath("//div[@id='demo-tabpane-list']//div[@class='list-group-item list-group-item-action']"));

       // Printeaza o lista cu elemente
        for (int i = 0; i < listaElement.size() - 1; i++) {
            WebElement webElement = listaElement.get(i);
            System.out.println("Numarul elementului este:" + webElement.getText());
        }
    }

}


