package driver;

import driver.browser.BrowserType;
import driver.browser.ChromeBrowserManager;
import driver.browser.EdgeBrowserManager;
import driver.browser.FirefoxBrowserManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ThreadGuard;

public class DriverManager {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private DriverManager() {
    }

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            BrowserType browserType = BrowserType.valueOf(
                    System.getProperty("browser", "chrome").toUpperCase());

            driver.set(
                    ThreadGuard.protect(
                            switch (browserType) {
                                case FIREFOX -> FirefoxBrowserManager.createDriver();
                                case EDGE -> EdgeBrowserManager.createDriver();
                                default -> ChromeBrowserManager.createDriver();
                            })
            );
            driver.get().manage().window().maximize();
        }
        return driver.get();
    }

    public static void closeDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }

}
