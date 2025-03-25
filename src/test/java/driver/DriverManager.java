package driver;

import driver.browser.BrowserType;
import driver.browser.ChromeBrowserManager;
import driver.browser.EdgeBrowserManager;
import driver.browser.FirefoxBrowserManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import utils.DriverEventListener;

import java.time.Duration;

public class DriverManager {

    private static final int IMPLICIT_WAIT_TIMEOUT_SECONDS = 5;

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
            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_WAIT_TIMEOUT_SECONDS));

            webDriver = addDriverListener(webDriver);

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

    private static WebDriver addDriverListener(WebDriver driver) {
        DriverEventListener driverEventListener = new DriverEventListener();
        EventFiringDecorator<WebDriver> decorator = new EventFiringDecorator<>(driverEventListener);
        return decorator.decorate(driver);
    }

}
