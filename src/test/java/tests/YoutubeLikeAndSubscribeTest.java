package tests;

import lombok.extern.slf4j.Slf4j;
import model.SearchTestData;
import model.UserTestData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.youtube.main.YoutubeChannelPage;
import pages.youtube.menu.YoutubeTopMenuPage;
import pages.youtube.main.YoutubeVideoPage;
import services.SearchTestDataService;
import services.UserTestDataService;

@Slf4j
public class YoutubeLikeAndSubscribeTest extends BaseTest {

    private SearchTestData searchTestData;

    @BeforeMethod(dependsOnMethods = "setUp")
    @Parameters("youtubeUrl")
    public void setUpTest(String youtubeUrl) {
        searchTestData = SearchTestDataService.getTestDataFromProperties();
        UserTestData userTestData = UserTestDataService.getTestDataFromProperties();

        new YoutubeTopMenuPage(driver)
                .openPage(youtubeUrl)
                .clickSingInButton()
                .enterEmail(userTestData.getEmail())
                .clickEmailNextButton()
                .enterPassword(userTestData.getPassword())
                .clickPasswordNextButton();
    }

    @Test(description = "Test video pause works correctly")
    public void testVideoPause() {
        log.info("Starting test : testVideoPause");

        String searchRequest = searchTestData.getSearchRequest();
        Integer videoPosition = searchTestData.getVideoPosition();

        YoutubeVideoPage videoPage = new YoutubeTopMenuPage(driver)
                .enterSearchText(searchRequest)
                .searchSubmit()
                .clickVideo(videoPosition);

        videoPage.pauseVideo();

        Assert.assertTrue(videoPage.isVideoPaused(),
                "Video playing progress should be paused");

        log.info("Assertion passed: video pause works correctly.");
    }

    @Test(description = "Verify like button submits correctly")
    public void testLikeButton() {
        log.info("Starting test : testLikeButton");

        String searchRequest = searchTestData.getSearchRequest();
        Integer videoPosition = searchTestData.getVideoPosition();

        YoutubeVideoPage videoPage = new YoutubeTopMenuPage(driver)
                .enterSearchText(searchRequest)
                .searchSubmit()
                .clickVideo(videoPosition);

        videoPage.clickLikeVideo();

        Assert.assertTrue(videoPage.isVideoLiked(), "Video like button should be pressed");

        log.info("Assertion passed: video like works correctly.");
    }

    @Test(description = "Verify subscribe button submits correctly")
    public void testSubscribeButton() {
        log.info("Starting test : testSubscribeButton");

        String searchRequest = searchTestData.getSearchRequest();
        Integer videoPosition = searchTestData.getVideoPosition();

        YoutubeVideoPage videoPage = new YoutubeTopMenuPage(driver)
                .enterSearchText(searchRequest)
                .searchSubmit()
                .clickVideo(videoPosition);

        videoPage.clickSubscribe();

        String expectedSubscribeButtonText = searchTestData.getSubscribeButtonText();

        Assert.assertEquals(videoPage.getSubscribeButtonText(), expectedSubscribeButtonText,
                String.format("Channel subscription should be active, expected '%s' status", expectedSubscribeButtonText));

        log.info("Assertion passed: channel subscribe works correctly.");
    }

    @Test(description = "Verify subscription on channel page")
    public void testSubscribeButtonOnChannelPage() {
        log.info("Starting test : testSubscribeButtonOnChannelPage");

        String searchRequest = searchTestData.getSearchRequest();
        Integer videoPosition = searchTestData.getVideoPosition();

        YoutubeVideoPage videoPage = new YoutubeTopMenuPage(driver)
                .enterSearchText(searchRequest)
                .searchSubmit()
                .clickVideo(videoPosition);

        YoutubeChannelPage channelPage = videoPage
                .clickSubscribe()
                .openChannelPageInNewTab();
        videoPage.switchToNewTab();

        String expectedSubscribeButtonText = searchTestData.getSubscribeButtonText();

        Assert.assertEquals(channelPage.getSubscribeButtonText(), expectedSubscribeButtonText,
                String.format("Channel subscription should be active, expected '%s' status", expectedSubscribeButtonText));

        log.info("Assertion passed: channel subscription verified.");
    }

}
