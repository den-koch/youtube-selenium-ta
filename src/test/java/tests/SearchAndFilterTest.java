package tests;

import lombok.extern.slf4j.Slf4j;
import model.SearchTestData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.youtube.main.YoutubeSearchResultsPage;
import pages.youtube.menu.YoutubeTopMenuPage;
import services.SearchTestDataService;

@Slf4j
public class SearchAndFilterTest extends BaseTest {

    private SearchTestData searchTestData;

    @BeforeMethod
    @Parameters("youtubeUrl")
    public void setUpTest(String youtubeUrl) {
        searchTestData = SearchTestDataService.getTestDataFromProperties();
        new YoutubeTopMenuPage(driver.get())
                .openPage(youtubeUrl);
    }

    @Test(description = "Test search request match on results page")
    public void testSearchRequestMatch() {
        log.info("Starting test : testSearchRequestMatch");

        String expectedSearchRequest = searchTestData.getSearchRequest();

        YoutubeSearchResultsPage searchResultsPage = new YoutubeTopMenuPage(driver.get())
                .enterSearchText(expectedSearchRequest)
                .searchSubmit();

        String actualSearchRequest = searchResultsPage.getSearchInputText();

        Assert.assertEquals(actualSearchRequest, expectedSearchRequest,
                "Search request text did not match");

        log.info("Assertion passed: search request matches on result page.");
    }

}
