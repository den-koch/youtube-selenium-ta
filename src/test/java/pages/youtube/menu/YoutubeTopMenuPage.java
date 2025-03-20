package pages.youtube.menu;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;
import pages.google.GoogleLoginPage;
import pages.youtube.main.YoutubeSearchResultsPage;


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
        return this;
    }

    public GoogleLoginPage clickSingInButton(){
        clickElement(googleSignInButton);
        return new GoogleLoginPage(driver);
    }

    public YoutubeTopMenuPage enterSearchText(String searchText){
        enterText(searchInput, searchText);
        return this;
    }

    public YoutubeSearchResultsPage searchSubmit(){
        Actions action = new Actions(driver);
        action.sendKeys(searchInput, Keys.ENTER);
        action.perform();
        return new YoutubeSearchResultsPage(driver);
    }



}
