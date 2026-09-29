package Tests;

import Pages.CommonPage;
import Pages.FramePage;
import Pages.HomePage;
import ShareData.Hooks;
import org.testng.annotations.Test;

public class FramesTests extends Hooks {
    public FramePage framePage;
    public HomePage homePage;
    public CommonPage commonPage;

    @Test
    public void automationMethod() {

        framePage=new FramePage(getDriver());
        homePage=new HomePage(getDriver());
        commonPage=new CommonPage(getDriver());

        homePage.GoToDesiredMeniu("Alerts, Frame & Windows");
        commonPage.GoToDesiredSubMeniu("Frames");
        framePage.frameOane();
        framePage.frameTwo();

    }
}
