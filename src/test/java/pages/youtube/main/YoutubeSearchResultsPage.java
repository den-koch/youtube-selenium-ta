package pages.youtube.main;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import java.util.List;

public class YoutubeSearchResultsPage extends BasePage {

    @FindBy(css = "#filter-button button")
    private WebElement filterButton;

//    @FindBy(xpath = "//div[@id='dismissible']/descendant::a[@id='thumbnail']")
    @FindBy(xpath = "//ytd-video-renderer/descendant::a[@id='thumbnail']")
    private List<WebElement> videoElements;

    public YoutubeSearchResultsPage(WebDriver driver) {
        super(driver);
        waitPageToLoad(filterButton);
    }

    @Override
    public YoutubeSearchResultsPage openPage(String URL) {
        driver.get(URL);
        return this;
    }

    public YoutubeVideoPage clickVideo(int index) {
        clickElement(videoElements.get(index));
        return new YoutubeVideoPage(driver);
    }

    public String getSearchInputText() {
        return getPageTitle().replace(" - YouTube", "");
    }


}
