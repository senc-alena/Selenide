package tests;

import com.codeborne.selenide.ex.ElementNotFound;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.actions;
import static org.testng.Assert.assertTrue;

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
        assertTrue(homePage.haveDataSide(), "Календарь не раскрылся");
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
        homePage.hoverOverChart();
        assertTrue(homePage.verifyTooltipAppears(), "Тултип не появился");
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

    @DataProvider(name = "invalidDates")
    public Object[][] invalidDates() {
        return new Object[][]{
                {31, "February", 2026},
                {31, "April", 2026},
                {31, "June", 2026},
        };
    }

    @Test(priority = 8, dataProvider = "invalidDates",
            expectedExceptions = ElementNotFound.class)
    public void selectInvalidDateNegative(int day, String month, int year) {
        homePage.clickDataChoice();
        homePage.selectCustomDate(day, month, year);
    }

    @Test(priority = 9)
    public void checkHaveConnectAuthentication() {
        homePage.clickProfileBtn();
        homePage.clickAuthenticationBtn();
        homePage.haveConnectAuthentication();
        assertTrue(homePage.haveConnectAuthentication(),
                "Элемент аутентификации не отобразился");
    }
}
