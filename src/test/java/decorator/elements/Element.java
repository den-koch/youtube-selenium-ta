package decorator.elements;

import driver.DriverWaits;
import org.openqa.selenium.WebElement;

public abstract class Element implements IElement {

    protected WebElement webElement;

    public Element(WebElement webElement) {
        this.webElement = webElement;
    }

    public void waitElementToBeClickable() {
        DriverWaits.waitElementToBeClickable(webElement);
    }

    public void waitElementToBeVisible() {
        DriverWaits.waitVisibilityOf(webElement);
    }

}
