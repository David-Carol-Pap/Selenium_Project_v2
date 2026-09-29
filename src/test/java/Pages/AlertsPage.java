package Pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AlertsPage extends CommonPage {


    @FindBy(id = "alertButton")
    private WebElement alertButton;

    @FindBy(id = "timerAlertButton")
    private WebElement alertDelayElements;

    @FindBy(id = "confirmButton")
    private WebElement alertConfirmElements;

    @FindBy(id = "promtButton")
    private WebElement promptButtonElements;


    public AlertsPage(WebDriver driver) {
        super(driver);
    }

    public void alertOkElements() {
        elementsMethods.clickOnElements(alertButton);
        alertMethods.interractWithAlertsOK();
    }

    public void alertDelayElements() {
        elementsMethods.clickOnElements(alertDelayElements);
        alertMethods.interrectWithDelayAlert();
    }

    public void alertConfirmElements() {
        elementsMethods.clickOnElements(alertConfirmElements);
        alertMethods.interrectWithConfirmAlert();
    }

    public void promtButtonElementAlert(String name) {
        elementsMethods.clickOnElements(promptButtonElements);
        Alert promptButton = driver.switchTo().alert();
        promptButton.sendKeys(name);
        promptButton.accept();

    }

}
