package decorator.elements;

import driver.DriverWaits;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NullMarked;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsElement;

public abstract class Element implements IElement, WrapsElement {

    protected WebElement webElement;

    public Element(WebElement webElement) {
        this.webElement = webElement;
    }

    public String getDomAttribute(String attributeName) {
        return webElement.getDomAttribute(attributeName);
    }

    public boolean isDisplayed() {
        return webElement.isDisplayed();
    }

    public void waitElementToBeClickable() {
        DriverWaits.waitElementToBeClickable(webElement);
    }

    public void waitElementToBeVisible() {
        DriverWaits.waitVisibilityOf(webElement);
    }

    @Override
    public WebElement getWrappedElement() {
        return webElement;
    }
}
