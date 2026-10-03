package ShareData.browser;

import ShareData.browser.service.ChromeBrowserService;
import ShareData.browser.service.EdgeBrowserService;
import configFile.ConfigFile;
import configFile.configNode.ConfigurationNode;
import org.openqa.selenium.WebDriver;

import java.util.Locale;

public class BrowserFactory {

    /*
    public WebDriver getBrowserFactory() {
        boolean isCiCd =
                Boolean.parseBoolean(
                        System.getProperty(
                                "ciCd",
                                "false"
                        )
                );

        ConfigurationNode configurationNode =
                ConfigFile.createConfingNode(
                        ConfigurationNode.class
                );

        String browser;

        if (isCiCd) {
            browser = System.getProperty(
                    "browser",
                    "chrome"
            );

            configurationNode
                    .driverConfigNode
                    .headLess = "--headless";
        } else {
            browser =
                    configurationNode
                            .driverConfigNode
                            .localBrowser;
        }

        if (browser == null
                || browser.isBlank()) {
            throw new IllegalArgumentException(
                    "Browser is not configured."
            );
        }

        browser = browser
                .trim()
                .toLowerCase(Locale.ROOT);

        switch (browser) {
            case BrowserType.BROWSER_CHROME:
                ChromeBrowserService chromeService =
                        new ChromeBrowserService();

                chromeService.openBrowser(
                        configurationNode.driverConfigNode
                );

                return chromeService.getDriver();

            case BrowserType.BROWSER_EDGE:
                EdgeBrowserService edgeService =
                        new EdgeBrowserService();

                edgeService.openBrowser(
                        configurationNode.driverConfigNode
                );

                return edgeService.getDriver();

            default:
                throw new IllegalArgumentException(
                        "Unsupported browser: " + browser
                );
        }
    }
     */

    public WebDriver getBrowserFactory()
    {
        String ciCd = System.getProperty("ciCd");
        String browser = System.getProperty("browser").toLowerCase(Locale.ROOT);

        ConfigurationNode configurationNode = ConfigFile.createConfingNode(ConfigurationNode.class);

        if(Boolean.parseBoolean(ciCd))
        {
            configurationNode.driverConfigNode.headLess = "--headless";
        }
        else {
            browser = configurationNode.driverConfigNode.localBrowser;
        }

        switch (browser)
        {
            case BrowserType.BROWSER_CHROME:
                ChromeBrowserService chromeService = new ChromeBrowserService();
                chromeService.openBrowser(configurationNode.driverConfigNode);
                return chromeService.getDriver();

            case BrowserType.BROWSER_EDGE:
                EdgeBrowserService edgeService = new EdgeBrowserService();
                edgeService.openBrowser(configurationNode.driverConfigNode);
                return edgeService.getDriver();
        }
        return null;
    }
}
