package pages.youtube.main;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import java.util.List;

@Slf4j
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
        log.info("Opening Youtube search results page");
        driver.get(URL);
        return this;
    }

    public YoutubeVideoPage clickVideo(int index) {
        clickElement(videoElements.get(index));
        log.info("Clicked video by position in list");
        return new YoutubeVideoPage(driver);
    }

    public String getSearchInputText() {
        log.info("Got search text from input field");
        return getPageTitle().replace(" - YouTube", "");
    }


}
