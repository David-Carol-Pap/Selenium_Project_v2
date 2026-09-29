package ShareData;

import ShareData.browser.BrowserFactory;
import org.openqa.selenium.WebDriver;

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
}
