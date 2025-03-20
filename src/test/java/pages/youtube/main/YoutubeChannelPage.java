package pages.youtube.main;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;


public class YoutubeChannelPage extends BasePage {

    @FindBy(xpath = "//div[@id='page-header']//*[contains(@class, 'subscribe-button')]/descendant::button")
    private WebElement subscribeButton;

    public YoutubeChannelPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public YoutubeChannelPage openPage(String URL) {
        driver.get(URL);
        return this;
    }

    public String getSubscribeButtonText() {
        return getTextFromElement(subscribeButton);
    }
}


