package pages;

import driver.DriverWaits;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.Set;
import java.util.TreeSet;

public abstract class BasePage {

    protected WebDriver driver;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected abstract BasePage openPage(String URL);

    protected void clickElement(WebElement element) {
        DriverWaits.waitElementToBeClickable(element)
                .click();
    }

    protected void enterText(WebElement element, String text) {
        DriverWaits.waitElementToBeClickable(element)
                .sendKeys(text);
    }

    protected String getTextFromElement(WebElement element) {
        return DriverWaits.waitVisibilityOf(element).getText();
    }

    protected void scrollToElement(WebElement element) {
        DriverWaits.waitVisibilityOf(element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    protected void waitPageToLoad(WebElement element){
        DriverWaits.waitVisibilityOf(element);
    }

    public String getPageTitle(){
        return driver.getTitle();
    }

    public void switchToNewTab() {
        Set<String> tabs = driver.getWindowHandles();
        driver.switchTo().window( (String) tabs.toArray()[1]);
    }

}
