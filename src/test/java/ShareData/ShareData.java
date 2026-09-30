package ShareData;

import ShareData.browser.BrowserFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;
import java.util.Locale;

public class ShareData {

    protected WebDriver driver;

    protected void prepareBrowser() {
        BrowserFactory browserFactory =
                new BrowserFactory();

        driver =
                browserFactory.getBrowserFactory();
    }

    protected void clearBrowser() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    protected WebDriver getDriver() {
        return driver;
    }

    /*
    private WebDriver driver;

    @BeforeMethod
    public void prepareBrowser()
    {
        driver = new BrowserFactory().getBrowserFactory();
    }

    @AfterMethod
    public void cleanBrowser()
    {
        driver.quit();
    }

    public WebDriver getDriver() {
        return driver;
    }
     */
}
