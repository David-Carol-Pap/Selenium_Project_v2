package Pages;

import HelperMethods.ElementsMethods;
import HelperMethods.JavaScriptMethods;
import logger.LoggerUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.NoSuchElementException;

public class HomePage extends CommonPage {
    //Indetificam WebElemente-le specifice pt pagina asta
    @FindBy(xpath = "//h5")
   private List<WebElement> elements;

    @FindBy(xpath = "//p[text()='Consent']")
   private WebElement consentElement;

//    WebDriver driver;
//    ElementsMethods elementsMethods;
//    JavaScriptMethods javaScriptMethods;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void GoToDesiredMeniu(String menu) {

        javaScriptMethods.javaScriptScrollPage(0, 400);
        LoggerUtility.infoLog("The user scrolls down the page");
        elementsMethods.selectElementFromListByText(elements, menu);
        LoggerUtility.infoLog("The user selects from menu the option with the value: " + menu);
    }
}
