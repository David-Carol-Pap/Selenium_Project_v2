package HelperMethods;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AlertMethods {
    WebDriver driver;

    public AlertMethods(WebDriver driver) {
        this.driver = driver;
    }

    public void interractWithAlertsOK() {
        Alert alertOK = driver.switchTo().alert();  // ne multam cu focusul pe alerta
        alertOK.accept();
    }

    public void explicitAlertWait() {
        //definim un wait explicit ca sa astepte dupa alerta
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());
    }

    public void interrectWithDelayAlert() {
        explicitAlertWait();
        Alert alertDelay = driver.switchTo().alert();
        alertDelay.accept();

    }

    public void interrectWithConfirmAlert() {
        Alert alertConfirm = driver.switchTo().alert();
        alertConfirm.dismiss();
    }
}


