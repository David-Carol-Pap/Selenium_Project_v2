package ShareData.browser.service;

import configFile.configNode.DriverConfigNode;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class ChromeBrowserService implements BrowserService {
    private WebDriver driver;
    @Override
    public void openBrowser(DriverConfigNode driverConfigNode) {
        ChromeOptions options= (ChromeOptions) getBrowserOptions(driverConfigNode);
        driver=new ChromeDriver(options);
        driver.get(driverConfigNode.url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    @Override
    public Object getBrowserOptions(DriverConfigNode driverConfigNode) {
        ChromeOptions options=new ChromeOptions();
        if(!driverConfigNode.headLess.isEmpty()){
            options.addArguments(driverConfigNode.headLess);
        }
       options.addArguments(driverConfigNode.resolution);
       options.addArguments(driverConfigNode.gpu);
       options.addArguments(driverConfigNode.extensions);
       return options;

    }

    public WebDriver getDriver() {
        return driver;
    }
}
