package Tests;

import ObjectData.WebTableEntity;
import Pages.CommonPage;
import Pages.HomePage;
import Pages.WebTablePage;
import ShareData.Hooks;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.JsonReader;

import java.util.List;

public class WebTablesTests extends Hooks {

    public HomePage homePage;
    public CommonPage commonPage;
    public WebTablePage webTablePage;

    @Test
    public void addAndVerifyEntitiesonPages() {
        HomePage homePage =
                new HomePage(getDriver());

        CommonPage commonPage =
                new CommonPage(getDriver());

        WebTablePage webTablePage =
                new WebTablePage(getDriver());

        homePage.GoToDesiredMeniu("Elements");
        commonPage.GoToDesiredSubMeniu("Web Tables");

        int initialNumberOfRows =
                webTablePage.getNumberOfRowsOnCurrentPage();

        List<WebTableEntity> entities =
                JsonReader.readWebTableEntities(
                        "src/test/resources/inputData/webTableData.json"
                );

        int expectedTotalEntities =
                initialNumberOfRows + entities.size();

        int expectedTotalPages =
                webTablePage.getExpectedNumberOfPages(
                        expectedTotalEntities
                );

        webTablePage.addEntities(entities);

        Assert.assertEquals(
                webTablePage.getTotalPages(),
                expectedTotalPages,
                "The total number of pages is incorrect"
        );

        Assert.assertEquals(
                webTablePage.getCurrentPageNumber(),
                expectedTotalPages,
                "The driver is not on the last page"
        );

        int expectedRowsOnLastPage =
                expectedTotalEntities % WebTablePage.ROWS_PER_PAGE;

        if (expectedRowsOnLastPage == 0) {
            expectedRowsOnLastPage =
                    WebTablePage.ROWS_PER_PAGE;
        }

        Assert.assertEquals(
                webTablePage.getNumberOfRowsOnCurrentPage(),
                expectedRowsOnLastPage,
                "The last page has an incorrect number of rows"
        );

        WebTableEntity lastEntity =
                entities.get(entities.size() - 1);

        Assert.assertTrue(
                webTablePage.isEntityDisplayed(lastEntity),
                "The last entity is not displayed on the last page"
        );
        }
}

