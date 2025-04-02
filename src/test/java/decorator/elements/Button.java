package decorator.elements;

import org.openqa.selenium.WebElement;

public class Button extends Element { // implements WrapsElement

    public Button(WebElement webElement) {
        super(webElement);
    }

    public void click() {
        waitElementToBeClickable();
        webElement.click();
    }

}
