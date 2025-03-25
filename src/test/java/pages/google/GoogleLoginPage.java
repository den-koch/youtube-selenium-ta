package pages.google;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

@Slf4j
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
        log.info("Opening Google login page");
        driver.get(URL);
        return this;
    }

    public GoogleLoginPage enterEmail(String email){
        enterText(emailInput, email);
        log.info("Filled user email input");
        return this;
    }

    public GoogleLoginPage enterPassword(String password){
        enterText(passwordInput, password);
        log.info("Filled user password input");
        return this;
    }

    public GoogleLoginPage clickEmailNextButton(){
        clickElement(emailNextButton);
        log.info("Clicked next button on email page");
        return this;
    }

    public GoogleLoginPage clickPasswordNextButton(){
        clickElement(passwordNextButton);
        log.info("Clicked next button on password page");
        return this;
    }

}
