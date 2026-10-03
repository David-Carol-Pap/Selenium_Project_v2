package Tests;

import Pages.AlertsPage;
import Pages.CommonPage;
import Pages.HomePage;
import ShareData.Hooks;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

public class AlertsTests extends Hooks {
    public HomePage homePage;
    public CommonPage commonPage;
    public AlertsPage alertsPage;

    @Test
    public void automationMethod () {
        commonPage = new CommonPage(getDriver());
        alertsPage = new AlertsPage(getDriver());
        homePage = new HomePage(getDriver());
        homePage.GoToDesiredMeniu("Alerts, Frame & Windows");
        commonPage.GoToDesiredSubMeniu("Alerts");

        JavascriptExecutor javascriptExecutor =
                (JavascriptExecutor) driver;

        javascriptExecutor.executeScript("window.scrollTo(0, 0);");

        alertsPage.alertOkElements();
        alertsPage.alertDelayElements();
        alertsPage.alertConfirmElements();
        alertsPage.promtButtonElementAlert("David-Carol Pap");

    }
}
