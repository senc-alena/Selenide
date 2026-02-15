package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

public class AccountsTest extends BaseTest {

    @BeforeMethod
    public void openAccountPage() {
        loginPage.openPage();
        loginPage.login();
    }

    @Test
    public void checkAccountsPageOpens() {
        homePage.clickAccountsLink();
        accountPage.pageOpen();
    }

    @Test
    public void checkSpotUpdates() {
        homePage.clickAccountsLink();
        accountPage.pageOpen();

        String totalBefore = accountPage.getTotalValue();
        String chgBefore = accountPage.getChgValue();

        accountPage.clickSpotBtn();

        String totalAfter = accountPage.getTotalValue();
        String chgAfter = accountPage.getChgValue();

        assertNotEquals(totalBefore, totalAfter, "Total не изменился");
        assertNotEquals(chgBefore, chgAfter, "Chg 24h не изменился");
    }

    @Test
    public void checkAlertDisplayed() {
        homePage.clickAccountsLink();
        accountPage.pageOpen();

        accountPage.clickResetBtn();

        assertTrue(accountPage.alertDisplayed(), "Алерт не появился");
    }
}
