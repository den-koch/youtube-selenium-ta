package pages.youtube.main;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class YoutubeVideoPage extends BasePage {

    @FindBy(xpath = "//*[@id='movie_player']/descendant::button[contains(@class, 'ytp-play-button')]")
    private WebElement playButton;

    @FindBy(xpath = "(//like-button-view-model/descendant::button)[1]")
    private WebElement likeButton;

    @FindBy(css = "#subscribe-button-shape button")
    private WebElement subscribeButton;

    @FindBy(css = "#notification-preference-button button")
    private WebElement subscribedButton;

    @FindBy(css = "#upload-info a")
    private WebElement channelLink;

    @FindBy(id = "leading-section")
    private WebElement commentsSection;

    public YoutubeVideoPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public YoutubeVideoPage openPage(String URL) {
        driver.get(URL);
        return this;
    }

    public YoutubeVideoPage pauseVideo() {
        if (!isVideoPaused()) {
            clickElement(playButton);
        }
        return this;
    }

    public YoutubeVideoPage clickLikeVideo() {
        if (!isVideoLiked()) {
            clickElement(likeButton);
        }
        return this;
    }

    //    How To Handle Animation?
    public YoutubeVideoPage clickSubscribe() {
        if (isSubscribed()) {
            clickElement(subscribeButton);
        }
        return this;
    }

    public String getSubscribeButtonText() {
        String buttonText;
        if (isSubscribed()) {
            buttonText = getTextFromElement(subscribeButton);
        } else {
            buttonText = getTextFromElement(subscribedButton);
        }
        return buttonText;
    }

    public boolean isVideoLiked() {
        return Boolean.parseBoolean(likeButton.getDomAttribute("aria-pressed"));
    }

    public boolean isSubscribed() {
        return subscribeButton.isDisplayed();
    }

    public boolean isVideoPaused() {
        String playerReverseStatus = playButton.getDomAttribute("data-title-no-tooltip");
        return playerReverseStatus.equals("Play");
    }

    public YoutubeChannelPage openChannelPageInNewTab() {
        Actions action = new Actions(driver);
        action.keyDown(Keys.CONTROL)
                .click(channelLink)
                .keyUp(Keys.CONTROL)
                .build()
                .perform();
        return new YoutubeChannelPage(driver);
    }

    public YoutubeVideoCommentsPage scrollToCommentsSection(){
        scrollToElement(commentsSection);
        return new YoutubeVideoCommentsPage(driver);
    }

}
