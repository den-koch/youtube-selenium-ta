package pages.google;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class GoogleLoginPage extends BasePage {

    @FindBy(css = "#identifierId")
    private WebElement emailInput;

    @FindBy(css = "#password input")
    private WebElement passwordInput;

    @FindBy(css = "#identifierNext button")
    private WebElement emailNextButton;

    @FindBy(css = "#passwordNext button")
    private WebElement passwordNextButton;

    public GoogleLoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public GoogleLoginPage openPage(String URL){
        driver.get(URL);
        return this;
    }

    public GoogleLoginPage enterEmail(String email){
        enterText(emailInput, email);
        return this;
    }

    public GoogleLoginPage enterPassword(String password){
        enterText(passwordInput, password);
        return this;
    }

    public GoogleLoginPage clickEmailNextButton(){
        clickElement(emailNextButton);
        return this;
    }

    public GoogleLoginPage clickPasswordNextButton(){
        clickElement(passwordNextButton);
        return this;
    }

}
