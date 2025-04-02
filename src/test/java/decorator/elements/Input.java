package decorator.elements;

import org.openqa.selenium.WebElement;

public class Input extends Element {

    public Input(WebElement webElement) {
        super(webElement);
    }

    public void enterText(String text) {
        waitElementToBeClickable();
        webElement.sendKeys(text);
    }
}
