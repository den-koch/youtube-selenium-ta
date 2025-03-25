package pages.youtube.menu;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;
import pages.google.GoogleLoginPage;
import pages.youtube.main.YoutubeSearchResultsPage;


@Slf4j
public class YoutubeTopMenuPage extends BasePage {

    @FindBy(xpath = "//div[@id='buttons']/descendant::a[contains(@href, 'ServiceLogin')]")
    private WebElement googleSignInButton;

    @FindBy(css = "#center input")
    private WebElement searchInput;

    public YoutubeTopMenuPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public YoutubeTopMenuPage openPage(String URL) {
        driver.get(URL);
        log.info("Opening Youtube top menu page");
        return this;
    }

    public GoogleLoginPage clickSingInButton(){
        clickElement(googleSignInButton);
        log.info("Clicked Google SignIn button");
        return new GoogleLoginPage(driver);
    }

    public YoutubeTopMenuPage enterSearchText(String searchText){
        enterText(searchInput, searchText);
        log.info("Filled search text input");
        return this;
    }

    public YoutubeSearchResultsPage searchSubmit(){
        Actions action = new Actions(driver);
        action.sendKeys(searchInput, Keys.ENTER);
        action.perform();

        log.info("Search request submitted");
        return new YoutubeSearchResultsPage(driver);
    }



}
