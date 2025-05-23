package tests;

import com.epam.reportportal.testng.ReportPortalTestNGListener;
import driver.DriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import utils.TestListener;

@Slf4j
@Listeners({
        TestListener.class,
        ReportPortalTestNGListener.class
})
public class BaseTest {

    protected ThreadLocal<WebDriver> driver;

    @BeforeMethod(groups = "setup")
    public void setUp() {
        log.info("Initializing the WebDriver...");
        driver = DriverManager.getDriver();
        log.info("WebDriver initialized successfully.");
    }

    @AfterMethod
    public void tearDown() {
        log.info("Tearing down the WebDriver...");
        DriverManager.closeDriver();
    }

}
