package driver;

import driver.browser.BrowserType;
import driver.browser.ChromeBrowserManager;
import driver.browser.EdgeBrowserManager;
import driver.browser.FirefoxBrowserManager;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class DriverManager {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private DriverManager() {
    }

    public static WebDriver getDriver() {
        WebDriver webDriver = driver.get();
        if (webDriver == null) {
            BrowserType browserType = BrowserType.valueOf(
                    System.getProperty("browser", "chrome").toUpperCase());

            webDriver = switch (browserType) {
                case FIREFOX -> FirefoxBrowserManager.createDriver();
                case EDGE -> EdgeBrowserManager.createDriver();
                default -> ChromeBrowserManager.createDriver();
            };
            webDriver.manage().window().maximize();
            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

            driver.set(webDriver);
        }
        return webDriver;
    }

    public static void closeDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }

}
