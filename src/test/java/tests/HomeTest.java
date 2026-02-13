package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.actions;
import static org.testng.Assert.assertTrue;
import static com.codeborne.selenide.Selenide.sleep;

public class HomeTest extends BaseTest {

    @BeforeMethod
    public void login() {
        loginPage.openPage();
        loginPage.login();
    }

    @Test(priority = 1)
    public void checkClickFuturesBtn() {
        homePage.clickFuturesBtn();
    }

    @Test(priority = 2)
    public void checkClickDataChoice() {
        homePage.clickDataChoice();
    }

    @Test(priority = 3)
    public void checkDataSide() {
        homePage.clickDataChoice();
        homePage.haveDataSide();
    }

    @Test(priority = 4)
    public void selectSingleDate() {
        homePage.clickDataChoice();
        homePage.selectCustomDate(4, "January", 2026);
    }

    @Test(priority = 5)
    public void selectDateRange() {
        homePage.clickDataChoice();
        homePage.selectCustomRange(4, "January",
                2026, 16, "January", 2026);
    }

    @Test(priority = 6)
    public void testChartHover() {

        homePage.clickDataChoice();
        homePage.selectCustomRange(5, "January",
                2026, 18, "January", 2026);
        homePage.chart.scrollTo();
        sleep(1000);
        homePage.hoverOverChart();
        homePage.verifyTooltipAppears();

        String date = homePage.getTooltipDate();
        String value = homePage.getTooltipValue();

        System.out.println("Tooltip date: " + date);
        System.out.println("Tooltip value: " + value);
    }

    @Test(priority = 7)
    public void testChartHoverWithOffset() {

        homePage.clickDataChoice();
        homePage.selectCustomRange(5, "January", 2026,
                18, "January", 2026);

        actions().moveToElement(homePage.chart, 50, 10).perform();
        assertTrue(true);
    }
}
