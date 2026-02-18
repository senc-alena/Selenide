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
        homePage.header.goToAccounts();
    }

    @Test
    public void checkAccountsPageOpens() {
        homePage.header.goToAccounts();
        accountPage.pageOpen();
    }

    @Test
    public void checkSpotUpdates() {
        homePage.header.goToAccounts();
        accountPage.pageOpen();

        String totalBefore = accountPage.getTotalValue();
        accountPage.clickSpotBtn();

        String totalAfter = accountPage.getTotalValue();

        assertNotEquals(totalBefore, totalAfter, "Total не изменился");
    }

    @Test
    public void checkAlertDisplayed() {
        accountPage.pageOpen();

        accountPage.clickResetBtn();

        assertTrue(accountPage.alertDisplayed(), "Алерт не появился");
    }
}
