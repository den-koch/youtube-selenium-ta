package pages;

import driver.DriverWaits;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {

    protected WebDriver driver;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected abstract BasePage openPage();

    protected void clickElement(WebElement element) {
        DriverWaits.waitElementToBeClickable(element)
                .click();
    }

    protected void enterText(WebElement element, String text) {
        DriverWaits.waitElementToBeClickable(element)
                .sendKeys(text);
    }

    protected void scrollToElement(WebElement element) {
        DriverWaits.waitVisibilityOf(element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }


}
