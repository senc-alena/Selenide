package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.ex.ElementNotFound;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.codeborne.selenide.Selenide.actions;
import static com.codeborne.selenide.Selenide.sleep;
import static org.testng.Assert.assertEquals;
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
        sleep(1000);

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
        homePage.header.clickProfile();
        homePage.clickAuthenticationBtn();
        homePage.haveConnectAuthentication();
        assertTrue(homePage.haveConnectAuthentication(),
                "Элемент аутентификации не отобразился");
    }

    @Test(priority = 10)
    public void checkTokenSorting() {

        homePage.clickAssetsToken();
        List<String> asc = homePage.getFirstColumnTexts();
        List<String> sortedAsc = new ArrayList<>(asc);
        Collections.sort(sortedAsc);
        assertEquals(asc, sortedAsc, "Не по возрастанию");

        homePage.clickAssetsToken();
        List<String> desc = homePage.getFirstColumnTexts();
        List<String> sortedDesc = new ArrayList<>(desc);
        Collections.sort(sortedDesc);
        Collections.reverse(sortedDesc);
        assertEquals(desc, sortedDesc, "Не по убыванию");
    }

    @Test(priority = 11)
    public void checkLogoRedirectsToHome() {
        String expectedUrl = Configuration.baseUrl;

        homePage.header.clickLogo();

        String actualUrl = WebDriverRunner.url();
        assertEquals(actualUrl, expectedUrl, "Логотип не ведет на главную");
    }
}
