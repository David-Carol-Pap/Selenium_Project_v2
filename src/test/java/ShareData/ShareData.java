package ShareData;

import ShareData.browser.BrowserFactory;
import logger.LoggerUtility;
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
        LoggerUtility.infoLog("The browser was opened with success");
    }

    protected void clearBrowser() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
        LoggerUtility.infoLog("The browser was closed with success");
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
