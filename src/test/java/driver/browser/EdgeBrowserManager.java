package driver.browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class EdgeBrowserManager {

    public static WebDriver createDriver(){
        WebDriverManager.edgedriver().setup();
        return new EdgeDriver(getOptions());
    }

    private static EdgeOptions getOptions(){
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("--disable-notifications");
        return edgeOptions;
    }
}
