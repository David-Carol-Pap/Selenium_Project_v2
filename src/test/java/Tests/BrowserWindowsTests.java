package Tests;

import Pages.BrowserWindowsPage;
import Pages.CommonPage;
import Pages.HomePage;
import ShareData.Hooks;
import org.testng.annotations.Test;

public class BrowserWindowsTests extends Hooks{
    public HomePage homePage;
    public CommonPage commonPage;
    public BrowserWindowsPage browserWindowsPage;

    @Test
    public void automationMethod() {
        homePage = new HomePage(getDriver());
        commonPage = new CommonPage(getDriver());
        browserWindowsPage=new BrowserWindowsPage(getDriver());
        homePage.GoToDesiredMeniu("Alerts, Frame & Windows");
        commonPage.GoToDesiredSubMeniu("Browser Windows");
        browserWindowsPage.newTab();
        browserWindowsPage.newWidows();

    }
}
