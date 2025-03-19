package tests;

import driver.DriverManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.youtube.YoutubeChannelPage;
import pages.youtube.YoutubeTopMenuPage;
import pages.youtube.YoutubeVideoPage;

public class YoutubeLikeAndSubscribeTest extends BaseTest {

    @BeforeMethod(dependsOnMethods = "setUp")
    public void setUpTest() {
        new YoutubeTopMenuPage(driver)
                .openPage("https://www.youtube.com/")
                .clickSingInButton()
                .enterEmail("mepadav875@excederm.com")
                .clickEmailNextButton()
                .enterPassword("dev_mepadav875@excederm.com")
                .clickPasswordNextButton();
    }

    @Test(description = "Test video pause works correctly")
    public void testVideoPause() {
        String searchRequest = "Java Testing with Selenium Course";
        YoutubeVideoPage videoPage = new YoutubeTopMenuPage(driver)
                .enterSearchText(searchRequest)
                .searchSubmit()
                .clickVideo(0);

        videoPage.pauseVideo();

        Assert.assertTrue(videoPage.isVideoPaused(),
                "Video playing progress should be paused");
    }

    @Test(description = "Verify like button submits correctly")
    public void testLikeButton() {
        String searchRequest = "Java Testing with Selenium Course";

        YoutubeVideoPage videoPage = new YoutubeTopMenuPage(driver)
                .enterSearchText(searchRequest)
                .searchSubmit()
                .clickVideo(0);

        videoPage.clickLikeVideo();

        Assert.assertTrue(videoPage.isVideoLiked(), "Video like button should be pressed");
    }

    @Test(description = "Verify subscribe button submits correctly")
    public void testSubscribeButton() {
        String searchRequest = "Java Testing with Selenium Course";

        YoutubeVideoPage videoPage = new YoutubeTopMenuPage(driver)
                .enterSearchText(searchRequest)
                .searchSubmit()
                .clickVideo(0);

        String expectedSubscribeButtonText = "Subscribed";
        videoPage.clickSubscribe();

        Assert.assertEquals(videoPage.getSubscribeButtonText(), expectedSubscribeButtonText,
                "Channel subscription should be active, expected 'Subscribed' status");
    }

    @Test(description = "Verify subscription on channel page")
    public void testSubscribeButtonOnChannelPage() {
        String searchRequest = "Java Testing with Selenium Course";

        YoutubeVideoPage videoPage = new YoutubeTopMenuPage(driver)
                .enterSearchText(searchRequest)
                .searchSubmit()
                .clickVideo(0);

        YoutubeChannelPage channelPage = videoPage
                .clickSubscribe()
                .openChannelPageInNewTab();
        videoPage.switchToNewTab();

        String expectedSubscribeButtonText = "Subscribed";

        Assert.assertEquals(channelPage.getSubscribeButtonText(), expectedSubscribeButtonText,
                "Channel subscription should be active, expected 'Subscribed' status");
    }

}
